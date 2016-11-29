package ro.ionutpetre.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ro.ionutpetre.controller.InstructorService;
import ro.ionutpetre.controller.InstructorServiceImpl;
import ro.ionutpetre.model.Instructor;
import ro.ionutpetre.model.User;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblId, lblUsername, lblPassword, lblTitle, lblInstructors;
	private JTextField txtFldId, txtFldUsername, txtFldPassword, txtFldTitle;
	private JButton btnCreate, btnUpdate, btnDelete, btnGetNoOfInstructors;
	private JScrollPane lstScrollPane;
	private JList<String> lstInstructors;

	private InstructorService instructorService = null;
	private List<Instructor> instructorList = null;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		try {
			instructorService = new InstructorServiceImpl();
			instructorList = instructorService.getAllInstructors();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		initializeComponent();
		setInstructorListData(instructorList);
		lstInstructors.setSelectedIndex(0);
	}

	private void setInstructorListData(List<Instructor> instructors) {
		String[] lstInstrData = new String[instructors.size()];
		for (int i = 0; i < instructors.size(); i++) {
			lstInstrData[i] = instructors.get(i).toString();
		}
		lstInstructors.setListData(lstInstrData);
	}

	private void setSelectedInstructor() {
		Instructor selectedInstructor = instructorList.get(lstInstructors
				.getSelectedIndex());
		txtFldId.setText(selectedInstructor.getId());
		txtFldUsername.setText(selectedInstructor.getUsername());
		txtFldPassword.setText(selectedInstructor.getPassword());
		txtFldTitle.setText(selectedInstructor.getTitle());
	}

	private void onClickOnCreateBtn() {
		try {
			instructorService.createInstructor(new Instructor(txtFldId
					.getText(), txtFldUsername.getText(), txtFldPassword
					.getText(), txtFldTitle.getText()));
			instructorList = instructorService.getAllInstructors();
			setInstructorListData(instructorList);
			lstInstructors.setSelectedIndex(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void onClickOnUpdateBtn() {
		try {
			instructorService.updateInstructor(new Instructor(txtFldId
					.getText(), txtFldUsername.getText(), txtFldPassword
					.getText(), txtFldTitle.getText()));
			instructorList = instructorService.getAllInstructors();
			lstInstructors.setSelectedIndex(0);
			setInstructorListData(instructorList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void onClickOnDeleteBtn() {
		try {
			String instructorId = instructorList.get(
					lstInstructors.getSelectedIndex()).getId();
			instructorService.deleteInstructor(instructorId);
			instructorList = instructorService.getAllInstructors();
			setInstructorListData(instructorList);
			lstInstructors.setSelectedIndex(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onClickOnGetNoOfInstructorsBtn() {
		try {
			int noOfInstructors = instructorService.getNoOfInstructors();
			String dialogMsg = "PL/SQL: There are " + noOfInstructors
					+ " instructors";
			JOptionPane.showMessageDialog(lstScrollPane, dialogMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initializeComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 250, 553, 462);
		setTitle("Instructor's Form");
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		lblId = new JLabel("Id : ");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblId.setBounds(21, 21, 89, 20);
		lblId.setSize(100, 20);
		contentPane.add(lblId, BorderLayout.NORTH);

		txtFldId = new JTextField();
		txtFldId.setColumns(10);
		txtFldId.setBounds(20, 39, 333, 23);
		contentPane.add(txtFldId, BorderLayout.NORTH);

		btnCreate = new JButton("Create");
		btnCreate.setBounds(383, 59, 139, 23);
		contentPane.add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOnCreateBtn();
			}
		});

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(383, 93, 139, 23);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOnUpdateBtn();
			}
		});

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(383, 127, 139, 23);
		contentPane.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOnDeleteBtn();
			}
		});

		lblInstructors = new JLabel("Instructors :");
		lblInstructors.setBounds(21, 205, 120, 14);
		lblInstructors.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblInstructors);

		lstInstructors = new JList<String>();
		lstInstructors.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				setSelectedInstructor();
			}
		});

		lstScrollPane = new JScrollPane();
		lstScrollPane.setBounds(21, 230, 502, 180);
		lstScrollPane.setViewportView(lstInstructors);
		contentPane.add(lstScrollPane);

		txtFldUsername = new JTextField();
		txtFldUsername.setColumns(10);
		txtFldUsername.setBounds(21, 85, 333, 23);
		contentPane.add(txtFldUsername);

		lblUsername = new JLabel("Username : ");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(21, 62, 89, 20);
		contentPane.add(lblUsername);

		lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(21, 107, 89, 20);
		contentPane.add(lblPassword);

		txtFldPassword = new JTextField();
		txtFldPassword.setColumns(10);
		txtFldPassword.setBounds(21, 127, 333, 23);
		contentPane.add(txtFldPassword);

		txtFldTitle = new JTextField();
		txtFldTitle.setColumns(10);
		txtFldTitle.setBounds(21, 171, 333, 23);
		contentPane.add(txtFldTitle);

		lblTitle = new JLabel("Title : ");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTitle.setBounds(21, 150, 89, 20);
		contentPane.add(lblTitle);

		btnGetNoOfInstructors = new JButton("No of instructors");
		btnGetNoOfInstructors.setBounds(383, 161, 139, 23);
		btnGetNoOfInstructors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickOnGetNoOfInstructorsBtn();
			}
		});
		contentPane.add(btnGetNoOfInstructors);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					instructorService.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
				    frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
