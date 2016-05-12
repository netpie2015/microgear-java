package Example;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Microgear.Microgear;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class Example3 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JButton BTconnect;
	static JButton BTchat;
	static JButton BTSetalias;
	private JTextField gearname;
	private JTextField chat;
	private JTextField setalias;
	static JButton disconnect;
	static boolean retain;
	public static JTable table;
	public static boolean auto = true;
	private JPanel panel_1;
	final static String appID = "AppID";
	final static String Key = "Key";
	final static String Secret = "Secret";

	Example3() {
		intiFrame();

		JPanel panel = new JPanel();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 247, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGap(0, 124, Short.MAX_VALUE));
		panel.setLayout(null);

		table = new JTable();

		panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		BTconnect = new JButton("Connect");
		BTconnect.setFont(new Font("TH Sarabun New", Font.BOLD, 18));

		disconnect = new JButton("Disconnect");
		disconnect.setEnabled(false);
		disconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Microgear().Disconnect();
				BTconnect.setEnabled(true);
				disconnect.setEnabled(false);
			}
		});
		disconnect.setFont(new Font("TH Sarabun New", Font.BOLD, 18));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		setalias = new JTextField();
		setalias.setColumns(10);

		BTSetalias = new JButton("Set");
		BTSetalias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Microgear con = new Microgear();
				con.Setalias(setalias.getText());
			}
		});
		BTSetalias.setFont(new Font("TH Sarabun New", Font.BOLD, 18));

		JLabel lblAlias = new JLabel("   Alias");
		lblAlias.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
		getContentPane().setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JLabel lblGearname = new JLabel("   Name");
		lblGearname.setFont(new Font("TH Sarabun New", Font.BOLD, 18));

		gearname = new JTextField();
		gearname.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
		gearname.setColumns(10);

		BTchat = new JButton("Send");
		BTchat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Microgear con = new Microgear();
				con.Chat(gearname.getText(), chat.getText());

			}
		});
		BTchat.setFont(new Font("TH Sarabun New", Font.BOLD, 18));

		chat = new JTextField();
		chat.setFont(new Font("TH Sarabun New", Font.PLAIN, 18));
		chat.setColumns(10);

		JLabel lblMessage = new JLabel("   Message");
		lblMessage.setFont(new Font("TH Sarabun New", Font.BOLD, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
								.addComponent(panel_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(20)
						.addComponent(BTconnect, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE).addGap(29)
						.addComponent(disconnect, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(96, Short.MAX_VALUE)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(BTconnect, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(disconnect, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGap(82)));
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblSetalias = new JLabel("Setalias");
		lblSetalias.setForeground(Color.GRAY);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblAlias, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblSetalias, GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
					.addGap(23)
					.addComponent(setalias, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(BTSetalias, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(lblSetalias)
					.addPreferredGap(ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(BTSetalias)
						.addComponent(setalias, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAlias))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setForeground(Color.GRAY);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addComponent(lblGearname, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMessage))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_3.createSequentialGroup()
									.addComponent(chat, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(BTchat, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
								.addComponent(gearname, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblChat)))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblChat)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(gearname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGearname))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(BTchat)
								.addComponent(chat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addGap(16)
							.addComponent(lblMessage, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		getContentPane().setLayout(groupLayout);
		BTconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Microgear().connect(appID, Key, Secret);
				if (arg0.getSource() != null) {
					BTconnect.setEnabled(false);
					disconnect.setEnabled(true);
				} else {
					BTconnect.setEnabled(true);
				}
			}
		});

	}

	void intiFrame() {
		setTitle("Java Microgear Example.(GUI)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(500, 20);
		setSize(402, 318);
		ImageIcon icon = new ImageIcon("netpie.jpg");
		setIconImage(icon.getImage());
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		new Example3();

	}
}
