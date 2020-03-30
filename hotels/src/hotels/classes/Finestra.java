package hotels.classes;

import java.awt.Color;
import hotels.classes.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.ModuleLayer.Controller;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class Finestra extends JFrame {
	JPanel panellgestio, panellclients, panellback;
	Hotel hotel = new Hotel(this.getName());
	ArrayList<Reserves> reservahotel = new ArrayList<Reserves>();
	ArrayList<Habitacions> habitacionshotel = new ArrayList<Habitacions>();
	ArrayList<Clients> clientshotel = new ArrayList<Clients>();

	// variables per a gestió
	JLabel titolgestio, pendentsgestio, confirmadesgestio;
	DefaultTableModel reserves, confirmades;
	JTable taula, taula2;
	JDateChooser mostrardatareserves;
	// Fi variables per a gestió

	// Inici variables per a panell de clients
	JLabel titolclients, edni, enom, ecog, enumpe, enumni, dataentrada, dniicon, nomicon, cogicon, numpeicon, numniicon;
	JTextField idni, inom, icog, inumpe, inumni;
	JCalendar jcdata;
	JButton reservar;
	ImageIcon iireducedfalse, iireducedtrue;
	boolean dnicorrecte = false;
	boolean nomcorrecte = false;
	boolean cogcorrecte = false;
	boolean numnicorrecte = false;
	boolean numpecorrecte = false;
	// Fi variables per a panell de clients

	// Inici variables per a panell de back
	JLabel titolback, enomhotel, eregnouhabi, enumreghabi, eperreghabi, econsulreser, enomclient;
	JTextField inomhotel, inumreghabi, iperreghabi, inomclient;
	JButton jbguanomhotel, jbregnouhabi, eliminareserva;
	JList<String> nompersonreser; // Nom de les persones que han reservat habitacionss
	JList<String> habireserper;
	// Fi variables per a panell de back

	public Finestra() {
		this.setVisible(true);
		this.setDefaultCloseOperation(3);
		this.setSize(1200, 700);
		this.setTitle("Hotels");
		this.setLocationRelativeTo(null);
		this.setMinimumSize(new Dimension(200, 100));
		this.getContentPane().setBackground(Color.BLACK);
		this.setLayout(null);
		inicialitzaComponents();
	}

	private void inicialitzaComponents() {
		// Principals
		posarPanells();
		posarTitols();
		
		// Per a Gestio
		posarTotgestio();
		confirmarreserves();

		// Per el panell de clients
		posarTotclients();
		crearicones();
		verificarusuarireserva();

		//per el panell de back
		posarTotback();
		canviartitol();
		afegirhabitacions();

	}

	private void posarPanells() {
		panellgestio = new JPanel();
		panellgestio.setLayout(null);
		panellgestio.setBackground(Color.white);
		panellgestio.setBounds(0, 0, 400, 700);
		this.getContentPane().add(panellgestio);

		panellclients = new JPanel();
		panellclients.setLayout(null);
		panellclients.setBackground(Color.white);
		panellclients.setBounds(401, 0, 400, 700);
		this.getContentPane().add(panellclients);

		panellback = new JPanel();
		panellback.setLayout(null);
		panellback.setBackground(Color.white);
		panellback.setBounds(802, 0, 400, 700);
		this.getContentPane().add(panellback);
	}
	

	private void posarTitols() {
		titolgestio = new JLabel();
		titolgestio.setText("Gestió");
		titolgestio.setLayout(null);
		titolgestio.setBounds(150, 20, 150, 50);
		titolgestio.setFont(new Font("Chilanka", Font.BOLD, 30));
		panellgestio.add(titolgestio);

		titolclients = new JLabel();
		titolclients.setText("Clients");
		titolclients.setLayout(null);
		titolclients.setBounds(150, 20, 150, 50);
		titolclients.setFont(new Font("Chilanka", Font.BOLD, 30));
		panellclients.add(titolclients);

		titolback = new JLabel();
		titolback.setText("Back");
		titolback.setLayout(null);
		titolback.setBounds(150, 20, 150, 50);
		titolback.setFont(new Font("Chilanka", Font.BOLD, 30));
		panellback.add(titolback);
	}

	// Aqui posarem tot per el panell de Gesti�.
	private void posarTotgestio() {
		pendentsgestio = new JLabel("Reserves Pendents");
		pendentsgestio.setLayout(null);
		pendentsgestio.setBounds(20, 100, 200, 50);
		pendentsgestio.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellgestio.add(pendentsgestio);

		reserves = new DefaultTableModel();
		reserves.addColumn("Reserva");
		reserves.addColumn("Dia");
		reserves.addColumn("Persones");
		reserves.addColumn("Habitaci�");
		taula = new JTable(reserves);
		taula.setBounds(20, 150, 360, 200);
		taula.setDefaultEditor(Object.class, null);
		panellgestio.add(taula);
		JScrollPane scroll = new JScrollPane(taula, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(20, 150, 360, 200);
		panellgestio.add(scroll);

		confirmadesgestio = new JLabel("Reserves Confirmades");
		confirmadesgestio.setLayout(null);
		confirmadesgestio.setBounds(20, 360, 200, 50);
		confirmadesgestio.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellgestio.add(confirmadesgestio);

		mostrardatareserves = new JDateChooser();
		mostrardatareserves.setBounds(220, 372, 160, 25);
		panellgestio.add(mostrardatareserves);

		confirmades = new DefaultTableModel();
		confirmades.addColumn("dni");
		confirmades.addColumn("nom");
		confirmades.addColumn("cognom");
		confirmades.addColumn("Habitaci�");
		taula2 = new JTable(confirmades);
		taula2.setBounds(20, 410, 360, 200);
		taula2.setDefaultEditor(Object.class, null);
		panellgestio.add(taula2);
		JScrollPane scroll2 = new JScrollPane(taula2, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(20, 410, 360, 200);
		panellgestio.add(scroll2);
	}
	
	private void confirmarreserves() {
		MouseListener dobleclic = new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					String[] opcionsreserva = new String[] {"Confirmar-la", "Descartar-la", "Cancelar"};
					JOptionPane opcrese = new JOptionPane();
					int eleccioreserva = opcrese.showOptionDialog(null, "Que vols fer amb aquesta reserva?", "Confirmaci�",
				             JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				             null, opcionsreserva, opcionsreserva[0]);
					switch(eleccioreserva) {
					case 0:
						Functions.pasarloaconfirmades(hotel.getPendents(), hotel.getConfirmades(), taula.getSelectedRow(), reserves, confirmades);
						break;
					case 1:
						
						break;
					case 2:
						
						break;
					}
				}
			}
		};
		taula.addMouseListener(dobleclic);
	}

	// Aqui posarem tot per el panell de clients.
	private void posarTotclients() {
		edni = new JLabel("DNI: ");
		edni.setLayout(null);
		edni.setBounds(20, 100, 100, 50);
		edni.setFont(new Font("Chilanka", Font.PLAIN, 17));
		panellclients.add(edni);

		idni = new JTextField();
		idni.setLayout(null);
		idni.setName("dniintroduit");
		idni.setBounds(130, 115, 200, 25);
		panellclients.add(idni);

		dniicon = new JLabel();
		dniicon.setBounds(340, 115, 22, 22);
		panellclients.add(dniicon);

		enom = new JLabel("Nom: ");
		enom.setLayout(null);
		enom.setBounds(20, 140, 100, 50);
		enom.setFont(new Font("Chilanka", Font.PLAIN, 17));
		panellclients.add(enom);

		inom = new JTextField();
		inom.setLayout(null);
		inom.setName("nomintroduit");
		inom.setBounds(130, 150, 200, 25);
		panellclients.add(inom);

		nomicon = new JLabel();
		nomicon.setBounds(340, 150, 22, 22);
		panellclients.add(nomicon);

		ecog = new JLabel("Cognoms: ");
		ecog.setLayout(null);
		ecog.setBounds(20, 180, 100, 50);
		ecog.setFont(new Font("Chilanka", Font.PLAIN, 17));
		panellclients.add(ecog);

		icog = new JTextField();
		icog.setLayout(null);
		icog.setName("cognomintroduit");
		icog.setBounds(130, 192, 200, 25);
		panellclients.add(icog);

		cogicon = new JLabel();
		cogicon.setBounds(340, 192, 22, 22);
		panellclients.add(cogicon);

		enumpe = new JLabel("Num. Persones: ");
		enumpe.setLayout(null);
		enumpe.setBounds(20, 220, 100, 50);
		enumpe.setFont(new Font("Chilanka", Font.PLAIN, 12));
		panellclients.add(enumpe);

		inumpe = new JTextField();
		inumpe.setLayout(null);
		inumpe.setName("numpersones");
		inumpe.setBounds(130, 230, 50, 25);
		panellclients.add(inumpe);

		numpeicon = new JLabel();
		numpeicon.setBounds(300, 230, 22, 22);
		panellclients.add(numpeicon);

		enumni = new JLabel("Num. nits: ");
		enumni.setLayout(null);
		enumni.setBounds(20, 250, 100, 50);
		enumni.setFont(new Font("Chilanka", Font.PLAIN, 12));
		panellclients.add(enumni);

		inumni = new JTextField();
		inumni.setLayout(null);
		inumni.setName("numnits");
		inumni.setBounds(130, 260, 50, 25);
		panellclients.add(inumni);

		numniicon = new JLabel();
		numniicon.setBounds(300, 260, 22, 22);
		panellclients.add(numniicon);

		dataentrada = new JLabel("Data d'entrada: ");
		dataentrada.setLayout(null);
		dataentrada.setBounds(20, 280, 150, 100);
		dataentrada.setFont(new Font("Chilanka", Font.PLAIN, 17));
		panellclients.add(dataentrada);

		jcdata = new JCalendar();
		jcdata.setBounds(50, 350, 300, 200);
		panellclients.add(jcdata);

		reservar = new JButton("Reserva");
		reservar.setBounds(150, 600, 100, 30);
		reservar.setMnemonic('a');
		reservar.setEnabled(false);
		panellclients.add(reservar);
	}

	// Crear icones i ferles petites
	private void crearicones() {
		ImageIcon iifalse = new ImageIcon("Imatges/cruz.png");
		ImageIcon iitrue = new ImageIcon("Imatges/tick.png");
		iireducedfalse = new ImageIcon(
				iifalse.getImage().getScaledInstance(dniicon.getWidth(), dniicon.getHeight(), Image.SCALE_SMOOTH));
		iireducedtrue = new ImageIcon(
				iitrue.getImage().getScaledInstance(dniicon.getWidth(), dniicon.getHeight(), Image.SCALE_SMOOTH));
	}

	// Aqui farem un listener el qual ens permetra saber si un camp esta malament o
	// be.
	private void verificarusuarireserva() {

		KeyListener verificarcontingut = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getComponent().getName().equals("numnits")) {
					if (inumni.getText().matches("[0-9]")) {
						numniicon.setIcon(iireducedtrue);
						numnicorrecte = true;
					} else {
						numniicon.setIcon(iireducedfalse);
						numnicorrecte = false;
					}
				}
				if (e.getComponent().getName().equals("numpersones")) {
					if (inumpe.getText().matches("[0-9]")) {
						numpeicon.setIcon(iireducedtrue);
						numpecorrecte = true;
					} else {
						numpeicon.setIcon(iireducedfalse);
						numpecorrecte = false;
					}
				}
				if (e.getComponent().getName().equals("dniintroduit")) {
					if (idni.getText().matches("[0-9]{8}[A-Za-z]")) {
						dniicon.setIcon(iireducedtrue);
						dnicorrecte = true;
					} else if (idni.getText().matches("[A-Za-z][0-9]{7}[A-Za-z]")) {
						dniicon.setIcon(iireducedtrue);
						dnicorrecte = true;
					} else {
						dniicon.setIcon(iireducedfalse);
						dnicorrecte = false;
					}
				}
				if (e.getComponent().getName().equals("nomintroduit")) {
					if (inom.getText().matches("[a-zA-Z]{2,20}")) {
						nomicon.setIcon(iireducedtrue);
						nomcorrecte = true;
					} else {
						nomicon.setIcon(iireducedfalse);
						nomcorrecte = false;
					}
				}
				if (e.getComponent().getName().equals("cognomintroduit")) {
					if (icog.getText().matches("[a-zA-Z]{2,20}")) {
						cogicon.setIcon(iireducedtrue);
						cogcorrecte = true;
					} else {
						cogicon.setIcon(iireducedfalse);
						cogcorrecte = false;

					}
				}
				if (dnicorrecte && nomcorrecte && cogcorrecte && numnicorrecte && numpecorrecte) {
					reservar.setEnabled(true);
				} else {
					reservar.setEnabled(false);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		};

		ActionListener botoreserva = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				hotel.afegirpendents(idni.getText(), hotel, jcdata, inumpe.getText(), reservahotel, reserves,
						inom.getText(), icog.getText(), clientshotel, inumni.getText());

				idni.setText("");
				inom.setText("");
				icog.setText("");
				inumni.setText("");
				inumpe.setText("");
				jcdata.setDate(Date.valueOf(LocalDate.now()));
				dniicon.setIcon(null);
				nomicon.setIcon(null);
				cogicon.setIcon(null);
				numniicon.setIcon(null);
				numpeicon.setIcon(null);
				reservar.setEnabled(false);

			}
		};

		reservar.addActionListener(botoreserva);

		idni.addKeyListener(verificarcontingut);
		inom.addKeyListener(verificarcontingut);
		icog.addKeyListener(verificarcontingut);
		inumpe.addKeyListener(verificarcontingut);
		inumni.addKeyListener(verificarcontingut);

	}

	// Aqui introduim totes les coses per el back
	private void posarTotback() {
		enomhotel = new JLabel("Nom Hotel:");
		enomhotel.setLayout(null);
		enomhotel.setBounds(20, 100, 200, 50);
		enomhotel.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellback.add(enomhotel);

		inomhotel = new JTextField();
		inomhotel.setLayout(null);
		inomhotel.setBounds(160, 115, 220, 20);
		panellback.add(inomhotel);

		jbguanomhotel = new JButton("Guarda!");
		jbguanomhotel.setBounds(150, 150, 100, 30);
		jbguanomhotel.setMnemonic('a');
		panellback.add(jbguanomhotel);

		eregnouhabi = new JLabel("Registre nova habitació");
		eregnouhabi.setLayout(null);
		eregnouhabi.setBounds(20, 180, 200, 50);
		eregnouhabi.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellback.add(eregnouhabi);

		enumreghabi = new JLabel("Num.");
		enumreghabi.setLayout(null);
		enumreghabi.setBounds(20, 210, 100, 50);
		enumreghabi.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellback.add(enumreghabi);

		inumreghabi = new JTextField();
		inumreghabi.setLayout(null);
		inumreghabi.setBounds(70, 225, 70, 20);
		panellback.add(inumreghabi);

		eperreghabi = new JLabel("Pers.");
		eperreghabi.setLayout(null);
		eperreghabi.setBounds(190, 210, 100, 50);
		eperreghabi.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellback.add(eperreghabi);

		iperreghabi = new JTextField();
		iperreghabi.setLayout(null);
		iperreghabi.setBounds(240, 225, 70, 20);
		panellback.add(iperreghabi);

		jbregnouhabi = new JButton("Guarda!");
		jbregnouhabi.setBounds(150, 260, 100, 30);
		jbregnouhabi.setMnemonic('a');
		panellback.add(jbregnouhabi);

		econsulreser = new JLabel("Consulta Reserva");
		econsulreser.setLayout(null);
		econsulreser.setBounds(20, 300, 150, 50);
		econsulreser.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellback.add(econsulreser);

		enomclient = new JLabel("Nom Client:");
		enomclient.setLayout(null);
		enomclient.setBounds(20, 330, 150, 50);
		enomclient.setFont(new Font("Chilanka", Font.PLAIN, 15));
		panellback.add(enomclient);

		inomclient = new JTextField();
		inomclient.setLayout(null);
		inomclient.setBounds(120, 343, 150, 20);
		panellback.add(inomclient);

		DefaultListModel<String> model = new DefaultListModel<String>();
		nompersonreser = new JList<String>(model);
		nompersonreser.setBounds(20, 400, 150, 125);
		panellback.add(nompersonreser);
		JScrollPane scroll = new JScrollPane(nompersonreser, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBounds(20, 400, 150, 125);
		panellback.add(scroll);

		DefaultListModel<String> model2 = new DefaultListModel<String>();
		habireserper = new JList<String>(model2);
		habireserper.setBounds(225, 400, 150, 125);
		panellback.add(habireserper);
		JScrollPane scroll2 = new JScrollPane(habireserper, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll2.setBounds(225, 400, 150, 125);
		panellback.add(scroll2);

		eliminareserva = new JButton("Elimina!");
		eliminareserva.setBounds(150, 550, 100, 30);
		eliminareserva.setMnemonic('a');
		panellback.add(eliminareserva);
	}

	// Aquesta funcio fara que al clicar el boto "Guarda!" de nom hotel, es canvi el
	// nom de la finestra.
	private void canviartitol() {
		ActionListener canviarnom = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setTitle(inomhotel.getText());
				hotel.setNomhotel(inomhotel.getText());

			}
		};
		jbguanomhotel.addActionListener(canviarnom);
	}
	private void afegirhabitacions() {
		ActionListener crearhabitacio = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Functions.existeixlhabitacio(hotel, inumreghabi.getText())) {
					String[] opcioshabi = new String[] {"Yes", "No", "Cancel"};
					JOptionPane escriurehabi = new JOptionPane();
					int opcio=escriurehabi.showOptionDialog(null, "Aquesta Habitacio te capacitat per: "+Functions.retornarnumhabitacio(hotel, inumreghabi.getText())+" Vols actualitzar aquesta informacio?", "Atencio",
				             JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
				             null, opcioshabi, opcioshabi[0]);
					switch(opcio) {
						case 0:						
							Functions.actualitzahabitacio(hotel, iperreghabi.getText(), inumreghabi.getText());
							String[] ok = new String[] {"Ok"};
							JOptionPane actualitzat = new JOptionPane();
							actualitzat.showOptionDialog(null, "S'ha actualitzat la capacitat a: "+Functions.retornarnumhabitacio(hotel, inumreghabi.getText()), "Actualitzat",
						             JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
						             null, ok, ok[0]);
							break;
						case 1:
							
							break;
						case 2:
							
							break;
					}
				}
				else {
					Habitacions reghabi = new Habitacions();
					reghabi.setNumhabitacio(Integer.parseInt(inumreghabi.getText()));
					reghabi.setCapacitat(Integer.parseInt(iperreghabi.getText()));
					habitacionshotel.add(reghabi);
					hotel.setHabitacions(habitacionshotel);
				}
				
				inumreghabi.setText(null);
				iperreghabi.setText(null);
				
			}
		};
		jbregnouhabi.addActionListener(crearhabitacio);
		
		
	}
}