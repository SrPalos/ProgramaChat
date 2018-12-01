package PruebaSocket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;

/*
 * Código realizado por
 * César Augusto Palos Sandoval
 * y
 * Alan Fernando Rivera Nava 
 */

public class Cliente extends JFrame implements Runnable{
	Socket cli;
	ServerSocket ss;
	Socket so;
	int puerto = 1024;
	int puerto2 =1025;
	

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField txtMensaje;
	private JTextField txtIpDestino;
	private JTextField txtTxtipservidor;
	private JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Cliente() {
		setBackground(Color.WHITE);
		setTitle("Chat Cliente");
		Thread mihilo = new Thread(this);
		mihilo.start();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 370);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 11, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblMensaje = new JLabel("Mensaje:");
		lblMensaje.setBounds(10, 36, 46, 14);
		contentPane.add(lblMensaje);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(66, 8, 130, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtMensaje = new JTextField();
		txtMensaje.setBounds(66, 33, 207, 20);
		contentPane.add(txtMensaje);
		txtMensaje.setColumns(10);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					InetAddress direccion = InetAddress.getLocalHost();
					String ip = direccion.getHostAddress();
					cli = new Socket(txtIpDestino.getText(), puerto);
					Paquete pack = new Paquete();
					pack.setUsuario(txtUsuario.getText());
					pack.setMensaje(txtMensaje.getText());
					pack.setIpDestino(txtIpDestino.getText());
					pack.setIpOrigen(ip);
					ObjectOutputStream out = new ObjectOutputStream(cli.getOutputStream());
					out.writeObject(pack);
					cli.close();
				}catch (Exception e) {
					Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
				}
			}
		});
		btnEnviar.setBounds(283, 32, 89, 23);
		contentPane.add(btnEnviar);
		
		JLabel lblIpDestino = new JLabel("IP Destino:");
		lblIpDestino.setBounds(10, 275, 65, 14);
		contentPane.add(lblIpDestino);
		
		JLabel lblIpServidor = new JLabel("IP Servidor:");
		lblIpServidor.setBounds(215, 275, 65, 14);
		contentPane.add(lblIpServidor);
		
		txtIpDestino = new JTextField();
		txtIpDestino.setBounds(10, 300, 160, 20);
		contentPane.add(txtIpDestino);
		txtIpDestino.setColumns(10);
		
		txtTxtipservidor = new JTextField();
		txtTxtipservidor.setBounds(215, 300, 160, 20);
		contentPane.add(txtTxtipservidor);
		txtTxtipservidor.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 65, 362, 199);
		contentPane.add(textArea);
	}

	@Override
	public void run() {
		try {
			ss= new ServerSocket(puerto2);
			String ipd, ipo, user, msj;
			Paquete pack;
			while(true) {
				so = ss.accept();
				ObjectInputStream in = new ObjectInputStream(so.getInputStream());
				pack = (Paquete) in.readObject();
				ipd = pack.getIpDestino();
				ipo = pack.getIpOrigen();
				user = pack.getUsuario();
				msj = pack.getMensaje();
				textArea.append(ipo + " : " + ipd + "\n");
				textArea.append(user + " : " + msj + "\n");
				so.close();
			}
		}catch(Exception e) {
			
		}
	}
}
