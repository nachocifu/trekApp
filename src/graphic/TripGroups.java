package graphic;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;

import controllers.Application;
import controllers.CurrentProfileController;
import controllers.GroupController;
import controllers.MyTripController;
import domain.ControllerNotLoadedException;
import controllers.Session;
import domain.SessionNotActiveException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TripGroups extends JFrame {

	private static JPanel panel;
	private JTable table;
	private JLabel lblGroups;
	private JButton btnBack;
	private CurrentProfileController currentProfile;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TripGroups frame = new TripGroups(null, null,true);
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
	 * Create the Frame
	 * @param instance
	 * @param session
	 * @param language
	 */
	public TripGroups(final Application instance, final Session session, final boolean language){
		
		if(instance != null){
			try {
				currentProfile = instance.getCurrentProfileController();
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			}
		}
		
		Locale currentLocale;
		if(language){
			currentLocale = new Locale("en","US"); 
		}else{
			currentLocale = new Locale("es","AR");
		}
		final ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", currentLocale); 
		
		panel = new ImagePanel(new ImageIcon("TripGroups.jpg").getImage()); //$NON-NLS-1$
		setTitle("TrekApp"); //$NON-NLS-1$
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 901, 602);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setLayout(null);
		setContentPane(panel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(68, 146, 751, 272);
		panel.add(scrollPane_1);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model){
	        /**
			 * 
			 */
			private static final long serialVersionUID = -4125478354676472603L;

			public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };

	    if(instance != null){
	    	ArrayList<GroupController> groupArray = null;
			try {
				groupArray = new ArrayList<GroupController>(currentProfile.getGroups());
			} catch (SessionNotActiveException e2) {
				e2.printStackTrace();
			} catch (ControllerNotLoadedException e2) {
				e2.printStackTrace();
			}
			final ArrayList<GroupController> groupArrayaux = groupArray;
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try{
						if (arg0.getClickCount() == 2 ) {
							String admin = null;
							if(instance != null){
								try {
									admin = groupArrayaux.get(table.getSelectedRow()).getAdmin().getUsername();
								} catch (SessionNotActiveException e) {
									e.printStackTrace();
								} catch (ControllerNotLoadedException e) {
									e.printStackTrace();
								}
								if( groupArrayaux.get(table.getSelectedRow()) == null ){
									
								}else if(currentProfile.getUsername().equals(admin) && instance != null){
									Group frame = new Group(1, (MyTripController)groupArrayaux.get(table.getSelectedRow()).getTripController(), null, null, instance, session, groupArrayaux.get(table.getSelectedRow()), language);
									frame.setVisible(true);
									frame.pack();
									frame.setSize(900, 602);
									close();
								}else{
									Group frame = new Group(2, null, groupArrayaux.get(table.getSelectedRow()).getTripController(), null, instance, session, groupArrayaux.get(table.getSelectedRow()),language);
									frame.setVisible(true);
									frame.pack();
									frame.setSize(900, 602);
									close();
								}
							}
						}
					}catch(IndexOutOfBoundsException e){
						e.printStackTrace();
					} catch (SessionNotActiveException e) {
						e.printStackTrace();
					} catch (ControllerNotLoadedException e) {
						e.printStackTrace();
					}
				}
			});
			
			model.addColumn(messages.getString("TripGroups.5"));
		    model.addColumn(messages.getString("TripGroups.6"));
		    model.addColumn( messages.getString("TripGroups.7"));
		    model.addColumn(messages.getString("TripGroups.8"));
		    model.addColumn(messages.getString("TripGroups.9"));
			
			try {
				int i = -1;
				for(GroupController each : groupArray){
					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					if(each != null){
						model.addRow(new Object[] { null, null,null,null,null});
						i++;
					}
					model.setValueAt(each.getGroupName(), i, 0);
					model.setValueAt(df.format(each.getTripController().getStartDate()), i, 1);
					model.setValueAt(df.format(each.getTripController().getEndDate()), i, 2);
					model.setValueAt(each.getTripController().getOriginCity(), i, 3);
					model.setValueAt(each.getTripController().getEndCity(), i, 4);
			}
			} catch (SessionNotActiveException e1) {
				e1.printStackTrace();
			} catch (ControllerNotLoadedException e1) {
				e1.printStackTrace();
			}
	    }
	    table.getTableHeader().setReorderingAllowed(false);
		table.setEnabled(true);
		table.setCellSelectionEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(20);
		scrollPane_1.setViewportView(table);
		table.setGridColor(Color.WHITE);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSurrendersFocusOnKeystroke(true);
	    
		table.setBorder(UIManager.getBorder("ScrollPane.border")); //$NON-NLS-1$
		table.setForeground(Color.WHITE);
		table.setBackground(new Color(0, 0, 128));
		table.setToolTipText(""); //$NON-NLS-1$
		
		lblGroups = new JLabel();
		lblGroups.setForeground(Color.WHITE);
		lblGroups.setFont(new Font("Tahoma", Font.PLAIN, 22)); //$NON-NLS-1$
		lblGroups.setBounds(330, 42, 310, 46);
		panel.add(lblGroups);
		
		btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile frame = new Profile(instance, 1, session, null,language);
				frame.setVisible(true);
			    frame.pack();
			    frame.setSize(900, 620);
			    close();
			}
		});
		btnBack.setText(messages.getString("TripGroups.13")); //$NON-NLS-1$
		btnBack.setBounds(726, 518, 93, 23);
		panel.add(btnBack);
		
		JButton img = new JButton();
		img.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TripGroups frame = new TripGroups(instance, session,false);
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
				TripGroups frame = new TripGroups(instance, session,true);
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
		
		lblGroups.setText(messages.getString("TripGroups.16")); //$NON-NLS-1$
		
	}
	
	/**
	 * Close a frame after an event
	 */
	public void close(){
		WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
	}
}
