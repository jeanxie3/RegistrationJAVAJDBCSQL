package Registration;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Registration {

	private JFrame frame;
	private JTextField textName;
	private JTextField textmoblie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection con;
	PreparedStatement pst;
	String name,mobile,gender,course;
	private JRadioButton rbmale;
	private JRadioButton rbfemale;
	private JComboBox textcourse;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	

	public void Connect() {
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gschool","root","");
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Create the application.
	 */
	public Registration() {
		initialize();
		Connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 681, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textName = new JTextField();
		textName.setBounds(312, 95, 238, 34);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		textmoblie = new JTextField();
		textmoblie.setBounds(312, 176, 238, 39);
		frame.getContentPane().add(textmoblie);
		textmoblie.setColumns(10);
		
		JButton Save = new JButton("Save");
		Save.setFont(new Font("Verdana", Font.PLAIN, 20));
		Save.setBounds(508, 358, 111, 43);
		frame.getContentPane().add(Save);
		
		rbmale = new JRadioButton("Male");
		rbmale.setFont(new Font("Verdana", Font.PLAIN, 20));
		rbmale.setBounds(312, 270, 105, 23);
		frame.getContentPane().add(rbmale);
		
		rbfemale = new JRadioButton("Female\r\n");
		rbfemale.setFont(new Font("Verdana", Font.PLAIN, 20));
		rbfemale.setBounds(465, 266, 130, 27);
		frame.getContentPane().add(rbfemale);
		
		textcourse = new JComboBox();
		textcourse.setModel(new DefaultComboBoxModel(new String[] {"a", "b", "c", "d"}));
		textcourse.setBounds(321, 337, 111, 33);
		frame.getContentPane().add(textcourse);
		
		lblNewLabel = new JLabel("Registration");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		lblNewLabel.setBounds(229, 27, 238, 51);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Name\r\n");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(80, 104, 91, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Moblie");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(80, 188, 91, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Gender\r\n");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(80, 268, 80, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(80, 316, 26, -2);
		frame.getContentPane().add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Course\r\n");
		lblNewLabel_5.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(83, 347, 88, 23);
		frame.getContentPane().add(lblNewLabel_5);
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				name = textName.getText();
				mobile = textmoblie.getText();
				
			if(rbmale.isSelected()) {
				gender = "Male";
			}
			
			if(rbfemale.isSelected()) {
				gender = "Female";
			}
			
			course = textcourse.getSelectedItem().toString();
				try {
					pst = con.prepareStatement("insert into student(name,mobile,gender,course)values(?,?,?,?)");
					pst.setString(1,  name);
					pst.setString(2, mobile);
					pst.setString(3,  gender);
					pst.setString(4, course);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,  "Record Added!");
					
					textName.setText("");
					textmoblie.setText("");
					textcourse.setSelectedIndex(-1);
					
					textName.requestFocus();				
					}
					catch(SQLException e1) {
						
						e1.printStackTrace();
					}
				
			}
		});
		
	}

}
