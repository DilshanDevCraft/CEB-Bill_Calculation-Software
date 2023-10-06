package CEB;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Sanmark {

	private JFrame frame;
	private JTextField txtAcc;
	private JTextField txtdate;
	private JTextField txtunit;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sanmark window = new Sanmark();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sanmark() {
		Connect();
		initialize();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst; 	
	ResultSet rs;
	private JTextField txtname;
	private JTextField txtCusName;
	private JTextField txtBillDate;
	private JTextField txtCalUnit;
	private JTextField txtTotalBill;
	
	public void Connect() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost/sanmark", "root", "");
	    } catch (ClassNotFoundException ex) {
	        ex.printStackTrace(); // Print the exception stack trace for debugging
	    } catch (SQLException ex) {
	        ex.printStackTrace(); // Print the exception stack trace for debugging
	    }
	}

	
	
	public void table_load() {
		
		try {
			
			pst=con.prepareStatement("select * from ceb");
			rs=pst.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Customer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 59, 271, 172);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Acc No");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(10, 29, 93, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(10, 122, 93, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Unit");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(10, 91, 93, 23);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(10, 60, 93, 23);
		panel.add(lblNewLabel_1_1_1_1);
		
		txtAcc = new JTextField();
		txtAcc.setBounds(113, 32, 148, 20);
		panel.add(txtAcc);
		txtAcc.setColumns(10);
		
		txtdate = new JTextField();
		txtdate.setColumns(10);
		txtdate.setBounds(113, 125, 148, 20);
		panel.add(txtdate);
		
		txtunit = new JTextField();
		txtunit.setColumns(10);
		txtunit.setBounds(113, 94, 148, 20);
		panel.add(txtunit);
		
		
		txtname = new JTextField();
		txtname.setColumns(10);
		txtname.setBounds(113, 63, 148, 20);
		panel.add(txtname);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Acc,date,unit,cusName;
				
				Acc=txtAcc.getText();
				date=txtdate.getText();
				unit=txtunit.getText();
				cusName=txtname.getText();
				
				
				try {
					
					pst=con.prepareStatement ("INSERT INTO ceb (Acc_No, Date, unit,Cus_name) values (?,?,?,?)");
					
					pst.setString(1,Acc);
					pst.setString(2,date);
					pst.setString(3,unit);
					pst.setString(4, cusName);
					pst.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Record added!..");
					table_load();
					
					
					txtAcc.setText("");
					txtdate.setText("");
					txtunit.setText("");
					txtname.setText("");
					txtAcc.requestFocus();
					
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(37, 242, 83, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
				
			}
		});
		btnExit.setBounds(130, 242, 83, 39);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtCusName.setText("");
				txtBillDate.setText("");
				txtCalUnit.setText("");
				txtTotalBill.setText("");
				txtAcc.requestFocus();
				
				
				
				
			}
		});
		btnClear.setBounds(223, 242, 83, 39);
		frame.getContentPane().add(btnClear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(37, 292, 271, 62);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
					
					String accNo= txtSearch.getText();
					
					pst=con.prepareStatement("select Cus_name, date,unit,tot_Amount from ceb where Acc_No=?");
					pst.setString(1, accNo);
					ResultSet rs = pst.executeQuery();
					
					
					if(rs.next()==true) {
						String name=rs.getString(1);
						String date=rs.getString(2);
						String unit=rs.getString(3);
						String amount=rs.getString(4);
						
						
						txtCusName.setText(name);
						txtBillDate.setText(date);
						txtCalUnit.setText(unit);
						txtTotalBill.setText(amount);
						
						
					
					}
					
					else {
						txtCusName.setText("");
						txtBillDate.setText("");
						txtCalUnit.setText("");
						txtTotalBill.setText("");
					}
							
					
				} catch (SQLException ex) {
				
				}
				
				
				
			}
		});
		txtSearch.setBounds(113, 22, 129, 20);
		txtSearch.setColumns(10);
		panel_1.add(txtSearch);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Account No");
		lblNewLabel_1_1_2.setBounds(10, 22, 81, 17);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel = new JLabel("CEB APP - Sanmark");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(178, 11, 235, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Name");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_1.setBounds(344, 59, 93, 23);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		txtCusName = new JTextField();
		txtCusName.setColumns(10);
		txtCusName.setBounds(437, 62, 121, 20);
		frame.getContentPane().add(txtCusName);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Last Bill Date");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_3.setBounds(344, 93, 93, 23);
		frame.getContentPane().add(lblNewLabel_1_1_3);
		
		txtBillDate = new JTextField();
		txtBillDate.setColumns(10);
		txtBillDate.setBounds(437, 94, 121, 20);
		frame.getContentPane().add(txtBillDate);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Unit");
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_2.setBounds(344, 127, 93, 23);
		frame.getContentPane().add(lblNewLabel_1_1_1_2);
		
		txtCalUnit = new JTextField();
		txtCalUnit.setColumns(10);
		txtCalUnit.setBounds(437, 126, 121, 20);
		frame.getContentPane().add(txtCalUnit);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Total Bill");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1_2.setBounds(344, 157, 93, 23);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_2);
		
		txtTotalBill = new JTextField();
		txtTotalBill.setColumns(10);
		txtTotalBill.setBounds(437, 157, 121, 20);
		frame.getContentPane().add(txtTotalBill);
		
		JButton btnGenerateBill = new JButton("Generate Bill");
		btnGenerateBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String accNo = txtSearch.getText();
				
			
				

		        try {
		            pst = con.prepareStatement("SELECT  unit FROM ceb WHERE Acc_No = ?");
		            pst.setString(1, accNo);
		            ResultSet rs = pst.executeQuery();

		            if (rs.next()) {
		                //double unit = Double.parseDouble(rs.getString(1));
		                double unit = rs.getDouble("unit");
		                //Double date = rs.getDouble("Date");
		             
		               
		                double First_Range = (25*20); //days *charge per unit
		                double Second_Range= (50*35);
		             //   double Third_Range = 40+1500;
		                
		                
		                if(unit<=35) {
		                	
		                	double totalBill = unit * First_Range+500;
		                	txtTotalBill.setText(String.valueOf(totalBill));
		                	
		                }
		                else if(unit >= 50) {
		                	
		                	double totalBill = First_Range+ Second_Range + 1500;
		                	txtTotalBill.setText(String.valueOf(totalBill));
		                	
		                }
		                
		              
		                
		                
		               
		            } else {
		                txtTotalBill.setText("0.00"); 
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
			     			    
			
			
			
			
			
			}	
				
				
			
		});
		btnGenerateBill.setBounds(410, 292, 129, 39);
		frame.getContentPane().add(btnGenerateBill);
		
		
		
		
		
	}
}


