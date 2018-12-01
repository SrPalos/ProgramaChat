package PruebaSocket;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;

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

public class Servidor extends JFrame implements Runnable {
	Socket ens;
	ServerSocket ss;
	Socket s;
	int puerto = 1024;
	int puerto2 = 1025;

	private JPanel contentPane;
	private JTextField txtIpServidor;
	private JTextArea textAreaServ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servidor frame = new Servidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Servidor() {
		setTitle("Servidor");
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		Thread hilo = new Thread(this);
		hilo.start();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textAreaServ = new JTextArea();
		textAreaServ.setBounds(10, 57, 414, 193);
		contentPane.add(textAreaServ);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(10, 30, 19, 14);
		contentPane.add(lblIp);

		txtIpServidor = new JTextField();
		txtIpServidor.setBounds(35, 27, 121, 20);
		contentPane.add(txtIpServidor);
		txtIpServidor.setColumns(10);
	}

	@Override
	public void run() {
		try {
			ss = new ServerSocket(puerto);
			String ipd, ipo, user, msj;
			Paquete pack;
			while(true) {
				InetAddress direccion = InetAddress.getLocalHost();
				String ipSer = direccion.getHostAddress();
				s = ss.accept();
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());
				pack = (Paquete) in.readObject();
				ipd = pack.getIpDestino();
				ipo = pack.getIpOrigen();
				user = pack.getUsuario();
				msj = pack.getMensaje();
				txtIpServidor.setText(ipSer);
				textAreaServ.append(ipo + " --> " + ipd + "\n");
				textAreaServ.append(user + " : " + msj + "\n");
				ens = new Socket(ipd, puerto2);
				ObjectOutputStream out = new ObjectOutputStream(ens.getOutputStream());
				out.writeObject(pack);
				s.close();
			}
		} catch (IOException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		} catch (ClassNotFoundException e) {
			Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
		}
	}

}
