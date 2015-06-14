package graphic;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JScrollPane;

import controllers.Application;
import controllers.Session;

public class Calif extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6292082986974427278L;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calif frame = new Calif(null,null,true);
					frame.setVisible(true);
					frame.pack();
					frame.setSize(900, 602);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calif(final Application instance, final Session session, final boolean language) {
		
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US"); 
		}else{
			currentLocale = new Locale("es","AR");
		}
		ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 
		
		setTitle("TreckApp"); //$NON-NLS-1$
		setBounds(0, 0, 900, 601);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panel = new ImagePanel(new ImageIcon("Calif.jpg").getImage()); //$NON-NLS-1$
		panel.setBackground(new Color(25, 25, 112));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		final JLabel lblCalifMembers = new JLabel();
		lblCalifMembers.setForeground(Color.BLACK);
		lblCalifMembers.setFont(new Font("Tahoma", Font.PLAIN, 19)); //$NON-NLS-1$
		lblCalifMembers.setBounds(249, 131, 353, 39);
		panel.add(lblCalifMembers);
		
		LinkedList<String> options = new LinkedList<String>();
		
		final Choice requests = new Choice();
		requests.setBackground(Color.WHITE);
		requests.setBounds(370, 226, 255, 20);
		panel.add(requests);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(145, 181, 190, 120);
		panel.add(scrollPane);
		
		final DefaultListModel profiles = new DefaultListModel();
		
		LinkedList<String> profileCol = new LinkedList<String>();
		//profileCol = instance.getGroupController().
		for(String each : profileCol){
			profiles.addElement(each);
		}
		
		final JList list = new JList(profiles);
		scrollPane.setViewportView(list);
		
		final JButton btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options frame = new Options(instance,session,language);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setBounds(726, 518, 93, 23);
		panel.add(btnBack);
		
		final JButton btnAccept = new JButton();
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getModel().getSize() == 1){
					btnAccept.setEnabled(false);
					requests.setEnabled(false);
				}
				if(list.getSelectedValue() != null){
					System.out.println(list.getSelectedValue() + " " + requests.getSelectedItem()); //$NON-NLS-1$
					profiles.remove(list.getSelectedIndex());//si no se selecciona un objeto tira null pointer exception
				}
			}
		});
		btnAccept.setBounds(652, 226, 89, 23);
		panel.add(btnAccept);
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calif frame = new Calif(instance,session,false);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});
		ImageIcon imageS = new ImageIcon("SpanishFlag.jpg");  //$NON-NLS-1$
		panel.add(img);
		img.setIcon(imageS); 
		img.setSize(22,18); 
		img.setLocation(796,11); 
		img.setVisible(true); 
		
		JButton img2 = new JButton();
		img2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calif frame = new Calif(instance,session,true);
				frame.setVisible(true);
				frame.pack();
				frame.setSize(900, 602);
				close();
			}
		});

		ImageIcon imageE = new ImageIcon("EnglishFlag.jpg");  //$NON-NLS-1$
		panel.add(img2);
		img2.setIcon(imageE); 
		img2.setSize(22,18); 
		img2.setLocation(760,11); 
		img2.setVisible(true); 
		
		lblCalifMembers.setText(messages.getString("Calif.6")); //$NON-NLS-1$
		btnAccept.setText(messages.getString("Calif.7")); //$NON-NLS-1$
		btnBack.setText(messages.getString("Calif.8")); //$NON-NLS-1$
		options.add(messages.getString("Calif.9")); //$NON-NLS-1$
		options.add(messages.getString("Calif.10")); //$NON-NLS-1$
		options.add(messages.getString("Calif.11")); //$NON-NLS-1$
		options.add(messages.getString("Calif.12")); //$NON-NLS-1$
		options.add(messages.getString("Calif.13")); //$NON-NLS-1$
		
		for(int i = 0; i < options.size(); i++){
			requests.add(options.get(i));
		}
	}
	
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
