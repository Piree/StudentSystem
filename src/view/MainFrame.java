package view;

import controller.Controller;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainFrame {

	public JFrame mainFrame = new JFrame("StudentSystem V.1.A");

	// Connect Controller
	private Controller controller = new Controller();

	// Panels
	private JPanel panelStudent = new JPanel();
	private JPanel panelRegisterNewStudent = new JPanel();
	private JPanel panelCourse = new JPanel();
	private JPanel panelRegisterNewCourse = new JPanel();
	private JPanel panelStudentSearch = new JPanel();
	private JPanel panelGrade = new JPanel();

	// Labels
	private JLabel lblRegGrade = new JLabel("Betyg:");
	private JLabel lblPnrStudent = new JLabel("Personnummer:");
	private JLabel lblFirstNameStudent = new JLabel("Förnamn:");
	private JLabel lblEmailStudent = new JLabel("E-mail:");
	private JLabel lblLastNameStudent = new JLabel("Efternamn: ");
	private JLabel lblPercentageA = new JLabel("Andel A: ");
	private JLabel lblAddStudentEmail = new JLabel("Epost:");
	private JLabel lblAddStudentFirstName = new JLabel("Förnamn:");
	private JLabel lblAddPnr = new JLabel("Personnr:");
	private JLabel lblNewCoursePoints = new JLabel("Poäng:");
	private JLabel lblNewCourseName = new JLabel("Kursnamn:");
	private JLabel lblNewCourseCode = new JLabel("Kurskod:");
	private JLabel lblAddStudentToCourse = new JLabel("Registrera på ny kurs:");
	private JLabel lblStudiedCourses = new JLabel("Lästa kurser:");
	private JLabel lblStudiesCourses = new JLabel("Läser kurser:");
	private JLabel lblAddStudentLastName = new JLabel("Efternamn:");
	private JLabel lblAsInPercent = new JLabel("");
	private JLabel lblPnrSearchResult = new JLabel("Personnr:");

	// Textfields
	private JTextField textFieldSearchStudent = new JTextField();
	private JTextField textFieldPnrStudent = new JTextField();
	private JTextField textFieldFirstNameStudent = new JTextField();
	private JTextField textFieldEmailStudent = new JTextField();
	private JTextField textFieldLastNameStudent = new JTextField();
	private JTextField textFieldSearchCourseOnStudent = new JTextField();
	private JTextField textFieldAddStudentPnr = new JTextField();
	private JTextField textFieldAddStudentFirstName = new JTextField();
	private JTextField textFieldAddStudentLastName = new JTextField();
	private JTextField textFieldAddStudentEmail = new JTextField();
	private JTextField textFieldNewCourseCode = new JTextField();
	private JTextField textFieldNewCourseName = new JTextField();
	private JTextField textFieldNewCoursePoints = new JTextField();
	private JTextField textFieldPnrResult = new JTextField();

	// Buttons
	private JButton btnAddNewStudent = new JButton("Lägg till student");
	private JButton btnShowRegisteredStudentsOnCourse = new JButton(
			"Reg.studenter");
	private JButton btnShowStudentResultsOnCourse = new JButton("Resultat");
	private JButton btnDeleteStudent = new JButton("Ta bort");
	private JButton btnDeleteStudiesCourse = new JButton("Ta bort");
	private JButton btnShowCoursePanel = new JButton("Visa kurs");
	private JButton btnDeleteCourse = new JButton("Ta bort kurs");
	private JButton btnShowStudent = new JButton("Välj");
	private JButton btnSearchStudent = new JButton("Sök");
	private JButton btnAddNewCourse = new JButton("Lägg till kurs");
	private JButton btnSaveGrade = new JButton("Spara");
	private JButton btnSearchCourse = new JButton("Sök");
	private JButton btnAddStudentToCourse = new JButton("Lägg till student");
	private JButton btnTopCourses = new JButton("Topp 5 Kurser");
	private JButton btnSaveGradeByCourse = new JButton("Spara");
	private JButton btnSearchPnrResult = new JButton("Sök");

	// Tables
	private JTable tableStudied = new JTable();
	private JTable tableCourse = new JTable();
	private JTable tableStudent = new JTable();
	private JTable tableStudies = new JTable();
	private JTable tableCourseInfo = new JTable();

	// Scrollpanes
	private JScrollPane scrollPaneStudiedTable = new JScrollPane();
	private JScrollPane scrollPaneCourseInfoTable = new JScrollPane();
	private JScrollPane scrollPaneCourseTable = new JScrollPane();
	private JScrollPane scrollPaneStudiesTable = new JScrollPane();
	private JScrollPane scrollPaneStudentTable = new JScrollPane();

	// Combobox
	private JComboBox<String> comboBoxGrade = new JComboBox<String>();
	private JComboBox<String> comboBoxGradeByCourse = new JComboBox<String>();

	/**
	 * Create the application.
	 */
	private MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		panelCourse.setVisible(false);
		panelGrade.setVisible(false);

		// Mainframe settings & layout
		mainFrame.setBounds(0, 0, 1300, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.getContentPane().add(panelStudent);
		mainFrame.getContentPane().add(panelStudentSearch);
		mainFrame.getContentPane().add(panelCourse);
		mainFrame.getContentPane().add(panelRegisterNewCourse);
		mainFrame.getContentPane().add(panelRegisterNewStudent);
		mainFrame.getContentPane().add(panelGrade);
		
		// Panel settings
		panelStudent.setBounds(271, 12, 466, 506);
		panelStudent.setBorder(new TitledBorder(UIManager
						.getBorder("TitledBorder.border"), "Student",
						TitledBorder.CENTER, TitledBorder.TOP, null, new Color(
								0, 0, 0)));
		panelStudent.setLayout(null);
		panelStudentSearch.setBounds(10, 12, 251, 506);
		panelStudentSearch.setBorder(new TitledBorder(null, "S\u00F6k student",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelStudentSearch.setLayout(null);
		panelCourse.setBounds(532, 529, 711, 210);
		panelCourse.setBorder(new TitledBorder(null, "Kursinfo",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelCourse.setLayout(null);
		panelRegisterNewCourse.setBounds(271, 529, 251, 210);
		panelRegisterNewCourse.setBorder(new TitledBorder(null,
				"Lägg till ny kurs", TitledBorder.CENTER, TitledBorder.TOP,
				null, null));
		panelRegisterNewCourse.setLayout(null);
		panelRegisterNewStudent.setBounds(10, 529, 251, 210);
		panelRegisterNewStudent.setBorder(new TitledBorder(null,
				"Lägg till ny student", TitledBorder.CENTER, TitledBorder.TOP,
				null, null));
		panelRegisterNewStudent.setLayout(null);
		panelGrade.setBorder(new TitledBorder(null, "Gradering",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelGrade.setBounds(747, 12, 496, 506);
		panelGrade.setLayout(null);
		
		// Add objects
		panelStudent.add(lblPnrStudent);
		panelStudent.add(lblFirstNameStudent);
		panelStudent.add(lblEmailStudent);
		panelStudent.add(textFieldPnrStudent);
		panelStudent.add(textFieldEmailStudent);
		panelStudent.add(lblLastNameStudent);
		panelStudent.add(textFieldFirstNameStudent);
		panelStudent.add(textFieldLastNameStudent);
		panelStudent.add(textFieldLastNameStudent);
		panelStudent.add(scrollPaneCourseTable);
		panelStudent.add(lblAddStudentToCourse);
		panelStudent.add(btnAddStudentToCourse);
		panelStudent.add(btnShowCoursePanel);
		panelStudent.add(textFieldSearchCourseOnStudent);
		panelStudent.add(btnSearchCourse);
		panelStudent.add(btnDeleteCourse);
		panelRegisterNewCourse.add(lblNewCoursePoints);
		panelRegisterNewCourse.add(btnAddNewCourse);
		panelRegisterNewCourse.add(lblNewCourseName);
		panelRegisterNewCourse.add(lblNewCourseCode);
		panelRegisterNewCourse.add(textFieldNewCourseCode);
		panelRegisterNewCourse.add(textFieldNewCourseName);
		panelRegisterNewCourse.add(textFieldNewCoursePoints);
		panelRegisterNewStudent.add(lblAddStudentFirstName);
		panelRegisterNewStudent.add(lblAddStudentLastName);
		panelRegisterNewStudent.add(lblAddStudentEmail);
		panelRegisterNewStudent.add(textFieldAddStudentPnr);
		panelRegisterNewStudent.add(textFieldAddStudentFirstName);
		panelRegisterNewStudent.add(textFieldAddStudentLastName);
		panelRegisterNewStudent.add(textFieldAddStudentEmail);
		panelRegisterNewStudent.add(btnAddNewStudent);
		panelRegisterNewStudent.add(lblAddPnr);
		panelCourse.add(scrollPaneCourseInfoTable);
		panelCourse.add(btnShowStudentResultsOnCourse);
		panelCourse.add(btnShowRegisteredStudentsOnCourse);
		panelCourse.add(lblPercentageA);
		panelCourse.add(lblAsInPercent);
		panelCourse.add(textFieldPnrResult);
		panelCourse.add(btnSearchPnrResult);
		panelCourse.add(lblPnrSearchResult);
		panelStudentSearch.add(textFieldSearchStudent);
		panelStudentSearch.add(btnSearchStudent);
		panelStudentSearch.add(scrollPaneStudentTable);
		panelStudentSearch.add(btnDeleteStudent);
		panelStudentSearch.add(btnShowStudent);
		panelCourse.add(comboBoxGradeByCourse);
		panelCourse.add(btnSaveGradeByCourse);
		panelStudent.add(btnTopCourses);
		panelGrade.add(btnSaveGrade);
		panelGrade.add(comboBoxGrade);
		panelGrade.add(btnDeleteStudiesCourse);
		panelGrade.add(lblRegGrade);
		panelGrade.add(lblStudiesCourses);
		panelGrade.add(lblStudiedCourses);
		panelGrade.add(scrollPaneStudiedTable);
		panelGrade.add(scrollPaneStudiesTable);
		
		// Label settings
		lblPnrStudent.setBounds(28, 62, 93, 14);
		lblFirstNameStudent.setBounds(28, 90, 93, 14);
		lblEmailStudent.setBounds(28, 150, 86, 14);
		lblLastNameStudent.setBounds(28, 118, 93, 14);
		lblAddStudentToCourse.setFont(new Font("Verdana", Font.BOLD, 12));
		lblAddStudentToCourse.setBounds(28, 201, 169, 14);
		lblNewCourseCode.setBounds(10, 37, 70, 14);
		lblNewCourseName.setBounds(10, 80, 70, 14);
		lblNewCoursePoints.setBounds(10, 123, 70, 14);
		lblAddPnr.setBounds(10, 37, 70, 14);
		lblAddStudentFirstName.setBounds(10, 71, 55, 14);
		lblAddStudentLastName.setBounds(10, 105, 70, 14);
		lblAddStudentEmail.setBounds(10, 139, 70, 14);
		lblPercentageA.setBounds(558, 25, 51, 14);
		lblAsInPercent.setBounds(607, 25, 46, 14);
		lblPnrSearchResult.setBounds(558, 121, 78, 14);
		lblPercentageA.setVisible(false);
		lblAsInPercent.setVisible(true);
		lblPnrSearchResult.setVisible(false);
		lblRegGrade.setBounds(245, 223, 66, 14);
		lblRegGrade.setFont(new Font("Verdana", Font.BOLD, 12));
		lblStudiesCourses.setBounds(43, 21, 134, 14);
		lblStudiesCourses.setFont(new Font("Verdana", Font.BOLD, 12));
		lblStudiedCourses.setBounds(43, 269, 134, 14);
		lblStudiedCourses.setFont(new Font("Verdana", Font.BOLD, 12));
		
		// Textfield Settings
		textFieldPnrStudent.setBounds(131, 62, 150, 20);
		textFieldPnrStudent.setEditable(false);
		textFieldFirstNameStudent.setBounds(131, 90, 150, 20);
		textFieldFirstNameStudent.setEditable(false);
		textFieldEmailStudent.setBounds(131, 150, 150, 20);
		textFieldEmailStudent.setEditable(false);
		textFieldLastNameStudent.setBounds(131, 118, 150, 20);
		textFieldLastNameStudent.setEditable(false);
		textFieldSearchCourseOnStudent.setBounds(28, 233, 99, 20);
		textFieldAddStudentPnr.setBounds(90, 37, 128, 20);
		textFieldAddStudentPnr.setColumns(10);
		textFieldAddStudentFirstName.setColumns(10);
		textFieldAddStudentFirstName.setBounds(90, 71, 128, 20);
		textFieldAddStudentLastName.setColumns(10);
		textFieldAddStudentLastName.setBounds(90, 105, 128, 20);
		textFieldAddStudentEmail.setColumns(10);
		textFieldAddStudentEmail.setBounds(90, 139, 128, 20);
		textFieldSearchStudent.setBounds(10, 34, 86, 20);
		textFieldSearchStudent.setColumns(10);
		textFieldNewCourseCode.setBounds(85, 34, 128, 20);
		textFieldNewCourseName.setBounds(85, 77, 128, 20);
		textFieldNewCoursePoints.setBounds(85, 120, 128, 20);
		textFieldPnrResult.setBounds(618, 121, 66, 20);
		textFieldPnrResult.setColumns(10);
		textFieldPnrResult.setVisible(false);
		
		// ScrollPane settings
		scrollPaneCourseTable.setBounds(28, 264, 410, 197);
		scrollPaneCourseTable.setViewportView(tableCourse);
		scrollPaneStudentTable.setBounds(10, 65, 231, 396);
		scrollPaneStudentTable.setViewportView(tableStudent);
		scrollPaneCourseInfoTable.setBounds(10, 25, 521, 174);
		scrollPaneCourseInfoTable.setViewportView(tableCourseInfo);
		scrollPaneStudiedTable.setBounds(43, 290, 410, 166);
		scrollPaneStudiedTable.setViewportView(tableStudied);
		scrollPaneStudiesTable.setBounds(43, 46, 410, 166);
		scrollPaneStudiesTable.setViewportView(tableStudies);
		
		//Table settings
		tableStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStudent.setShowGrid(false);
		tableCourse.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCourse.setShowGrid(false);
		tableStudied.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStudied.setShowGrid(false);
		tableStudies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStudies.setShowGrid(false);
		tableCourseInfo.setShowGrid(false);
		tableCourseInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Button settings
		btnAddStudentToCourse.setBounds(28, 472, 124, 23);
		btnSearchCourse.setBounds(145, 230, 85, 23);
		btnAddNewCourse.setBounds(10, 174, 145, 22);
		btnAddNewStudent.setBounds(10, 174, 145, 22);
		btnShowStudentResultsOnCourse.setBounds(558, 53, 126, 23);
		btnShowRegisteredStudentsOnCourse.setBounds(558, 87, 126, 23);
		btnSearchPnrResult.setBounds(558, 152, 78, 23);
		btnSearchPnrResult.setVisible(false);
		btnSaveGradeByCourse.setBounds(558, 152, 78, 23);
		btnSearchStudent.setBounds(106, 33, 57, 23);
		btnShowStudent.setBounds(10, 472, 62, 23);
		btnShowCoursePanel.setBounds(225, 472, 99, 23);
		btnDeleteStudent.setBounds(106, 472, 75, 23);
		btnDeleteCourse.setBounds(335, 472, 103, 23);
		btnTopCourses.setBounds(312, 232, 126, 23);
		btnSaveGrade.setBounds(375, 220, 78, 23);
		btnDeleteStudiesCourse.setBounds(376, 254, 77, 23);
		
		// Combobox settings
		comboBoxGradeByCourse.setBounds(558, 121, 50, 20);
		comboBoxGradeByCourse.setModel(new DefaultComboBoxModel<String>(
				new String[] { "U", "E", "D", "C", "B", "A" }));
		comboBoxGrade.setModel(new DefaultComboBoxModel<String>(new String[] {
				"U", "E", "D", "C", "B", "A" }));
		comboBoxGrade.setBounds(307, 221, 50, 20);

		/**
		 * BUTTON METHODS
		 */

		// Delete from Studies
		btnDeleteStudiesCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteStudiesCourse();
			}
		});

		// Set result to a student/course
		btnSaveGrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setResultToStudent();
			}
		});

		// Show top courses
		btnTopCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableCourse.setModel(controller.getTop5Courses());
					lblPercentageA.setVisible(false);
					lblAsInPercent.setVisible(false);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Show student
		btnShowStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tableStudent.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Markera en Student!");
				} else {
					panelGrade.setVisible(true);
					fillStudentInfo();
					panelStudent.setBorder(new TitledBorder(null, "Student: "
							+ textFieldFirstNameStudent.getText() + " "
							+ textFieldLastNameStudent.getText() + "",
							TitledBorder.CENTER, TitledBorder.TOP, null, null));
					panelGrade.setBorder(new TitledBorder(null,
							"Betygsättning: "
									+ textFieldFirstNameStudent.getText() + " "
									+ textFieldLastNameStudent.getText() + "",
							TitledBorder.CENTER, TitledBorder.TOP, null, null));
				}
			}
		});

		// Add Student
		btnAddNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addStudent();
			}
		});

		// Add Course
		btnAddNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourse();
			}
		});

		// Search Student
		btnSearchStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableStudent.setModel(controller
							.getStudentToTable(textFieldSearchStudent.getText()));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Search Course
		btnSearchCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tableCourse.setModel(controller
							.getCourseToTable(textFieldSearchCourseOnStudent
									.getText()));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Search Result
		btnSearchPnrResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row = tableCourse.getSelectedRow();
					String selectedCourseCode = tableCourse.getValueAt(row, 0)
							.toString();
					String pnr = textFieldPnrResult.getText();
					tableCourseInfo.setModel(controller.getStudentCourseGrade(
							pnr, selectedCourseCode, ""));
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Add Student to Course
		btnAddStudentToCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addStudentToCourse();
			}
		});

		// Set result to a student/course in courseinfo
		btnSaveGradeByCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setResultToStudentByCourse();
			}
		});

		// Delete Student
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteStudent();
			}
		});

		// Delete Course
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deleteCourse();
			}
		});

		// Show courseinfo
		btnShowCoursePanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = tableCourse.getSelectedRow();
				try {
					if (row != -1) {
						String selectedCourseName = tableCourse.getValueAt(row,
								1).toString();
						String selectedCourseCode = tableCourse.getValueAt(row,
								0).toString();
						btnSearchPnrResult.setVisible(false);
						lblPnrSearchResult.setVisible(false);
						textFieldPnrResult.setVisible(false);
						comboBoxGradeByCourse.setVisible(true);
						btnSaveGradeByCourse.setVisible(true);
						lblPercentageA.setVisible(false);
						lblAsInPercent.setVisible(false);
						panelCourse.setVisible(true);
						panelCourse.setBorder(new TitledBorder(null,
								"Registrerade studenter på kurs: "
										+ selectedCourseName + "",
								TitledBorder.CENTER, TitledBorder.TOP, null,
								null));
						tableCourseInfo.setModel(controller
								.getStudentsOnCourse(selectedCourseCode));
					} else {
						JOptionPane.showMessageDialog(null, "Markera en Kurs!");
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Show results of a Course
		btnShowStudentResultsOnCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int row = tableCourse.getSelectedRow();
					if (row != -1) {
						String selectedCourseCode = tableCourse.getValueAt(row,
								0).toString();
						String selectedCourseName = tableCourse.getValueAt(row,
								1).toString();
						btnSearchPnrResult.setVisible(true);
						lblPnrSearchResult.setVisible(true);
						textFieldPnrResult.setVisible(true);
						tableCourseInfo.setModel(controller
								.getStudentCourseGrade("", selectedCourseCode,
										""));
						panelCourse.setBorder(new TitledBorder(null,
								"Studenters resultat på kurs: "
										+ selectedCourseName + "",
								TitledBorder.CENTER, TitledBorder.TOP, null,
								null));
						comboBoxGradeByCourse.setVisible(false);
						btnSaveGradeByCourse.setVisible(false);
						calcAs();
					} else {
						JOptionPane.showMessageDialog(null,
								"Ingen kurs är vald");
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}
		});

		// Show registered Students on a Course
		btnShowRegisteredStudentsOnCourse
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int row = tableCourse.getSelectedRow();
							if (row != -1) {
								String selectedCourseCode = tableCourse
										.getValueAt(row, 0).toString();
								String selectedCourseName = tableCourse
										.getValueAt(row, 1).toString();
								tableCourseInfo.setModel(controller
										.getStudentsOnCourse(selectedCourseCode));
								btnSearchPnrResult.setVisible(false);
								lblPnrSearchResult.setVisible(false);
								textFieldPnrResult.setVisible(false);
								lblPercentageA.setVisible(false);
								lblAsInPercent.setVisible(false);
								comboBoxGradeByCourse.setVisible(true);
								btnSaveGradeByCourse.setVisible(true);
								panelCourse.setBorder(new TitledBorder(null,
										"Registrerade studenter på kurs: "
												+ selectedCourseName + "",
										TitledBorder.CENTER, TitledBorder.TOP,
										null, null));
							} else {
								JOptionPane.showMessageDialog(null,
										"Ingen kurs är vald");
							}
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage());
						}
					}
				});

		// Set tablemodels at start
		setModelsAndClear();

	} // End of initialize

	/**
	 * ADD/DELETE METHODS
	 */

	// Add(Register) new Student
	private void addStudent() {
		String studentPnr = textFieldAddStudentPnr.getText();
		String studentFirstName = textFieldAddStudentFirstName.getText();
		String studentLastName = textFieldAddStudentLastName.getText();
		String studentEmail = textFieldAddStudentEmail.getText();

		try {
			// Check if fields are empty
			if (!checkIfAddStudentFieldsAreEmpty()) {
				JOptionPane.showMessageDialog(null, "Fyll i samtliga fält!");
			} else if (!checkIfStudentExists(studentPnr)) {
				controller.addStudent(studentPnr, studentFirstName,
						studentLastName, studentEmail);
				tableStudent.setModel(controller.getAllStudentsToTable());
				clearAddStudentTextFields();
			} else {
				JOptionPane
						.showMessageDialog(null,
								"Finns redan en student med detta personnummer registrerad!");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	// Add(Register) new Course
	private void addCourse() {
		String coursePoints = textFieldNewCoursePoints.getText();
		String courseCode = textFieldNewCourseCode.getText();
		String courseName = textFieldNewCourseName.getText();

		try {
			// Check if fields are empty
			if (!checkIfCourseFieldsAreEmpty()) {
				JOptionPane.showMessageDialog(null, "Fyll i samtliga fält!");
			}
			// check if newcoursepointsnotstring
			else if (!isInteger(coursePoints)) {
				JOptionPane.showMessageDialog(null,
						"Mata in siffror på kurspoäng!");
			} // Check if course already exists
			else if (checkIfCourseExists(courseCode)) {
				JOptionPane.showMessageDialog(null,
						"En kurs med denna kurskod finns redan registrerad!");
			} else {
				controller.addCourse(courseCode, courseName,
						Integer.parseInt(coursePoints));
				tableCourse.setModel(controller.getAllCoursesToTable());
				clearNewCourseTextFields();
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	// Delete Student
	private void deleteStudent() {
		int row = tableStudent.getSelectedRow();
		try {
			// Check if a Student is selected
			if (row != -1) {
				String selectedStudentName = tableStudent.getValueAt(row, 1)
						.toString();
				String selectedCourseCode = tableStudent.getValueAt(row, 0)
						.toString();
				// Confirm student deletion
				Object[] options = { "Ja", "Nej" };
				int n = JOptionPane
						.showOptionDialog(null,
								"Är du säker på att du vill ta bort studenten "
										+ selectedStudentName
										+ " och alla dess Kurser ?", "",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == 0) {
					controller.deleteStudent(selectedCourseCode);
					tableStudent.setModel(controller.getAllStudentsToTable());
					setModelsAndClear();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Markera en Student!");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	// Delete Course
	private void deleteCourse() {
		int row = tableCourse.getSelectedRow();
		try {
			// Check if Course is selected
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Markera en Kurs!");
			} else {
				String selectedCourseName = tableCourse.getValueAt(row, 1)
						.toString();
				String selectedCourseCode = tableCourse.getValueAt(row, 0)
						.toString();

				// Confirm deletion of Course from Student
				Object[] options = { "Ja", "Nej" };
				int n = JOptionPane
						.showOptionDialog(null,
								"Är du säker på att du vill ta bort kursen "
										+ selectedCourseName
										+ " och alla dess Studenter?", "",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == 0) {
					controller.deleteCourse(selectedCourseCode);
					tableCourse.setModel(controller.getAllCoursesToTable());
					tableCourseInfo
							.setModel(controller.getStudentsOnCourse(""));
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	// Add student to Course (Studies)
	private void addStudentToCourse() {
		int row = tableCourse.getSelectedRow();
		if (row != -1) {
			try {
				String selectedCourseCode = tableCourse.getValueAt(row, 0)
						.toString();
				String selectedCourseName = tableCourse.getValueAt(row, 1)
						.toString();
				String selectedCoursePoints = tableCourse.getValueAt(row, 2)
						.toString();
				String studentPnr = textFieldPnrStudent.getText();
				String studentFirstName = textFieldFirstNameStudent.getText();

				if (textFieldPnrStudent.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"Ingen Student är vald!");
				} else {

					if (!controller.checkIfStudentAlreadyStudies(studentPnr,
							selectedCourseCode)) {
						JOptionPane.showMessageDialog(null, studentFirstName
								+ " är redan registrerad på "
								+ selectedCourseName);
					} else if (controller.checkMax45HP(studentPnr, Integer
							.parseInt(selectedCoursePoints.replaceAll(
									"([0-9])\\.0+([^0-9]|$)", "$1$2")))) {
						JOptionPane.showMessageDialog(null,
								"Högskolepoängen för " + studentFirstName
										+ " överstiger 45HP");
					} else if (!controller.checkIfStudentAlreadyStudied(
							studentPnr, selectedCourseCode)) {
						JOptionPane
								.showMessageDialog(
										null,
										studentFirstName
												+ " har redan läst och blivit betygsatt i kursen");
					} else {
						controller.addStudentToCourse(studentPnr,
								selectedCourseCode);
						tableStudies.setModel(controller
								.getStudiesToTable(studentPnr));
						tableCourseInfo.setModel(controller
								.getStudentsOnCourse(selectedCourseCode));
						panelCourse.setBorder(new TitledBorder(null,
								"Registrerade studenter på kurs: "
										+ selectedCourseName + "",
								TitledBorder.CENTER, TitledBorder.TOP, null,
								null));
					}
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Markera en Kurs!");
		}
	}

	// Delete a course from a Student (Studies)
	private void deleteStudiesCourse() {
		int row = tableStudies.getSelectedRow();
		if (row != -1) {
			try {
				String selectedCourseNameStudies = tableStudies.getValueAt(row,
						1).toString();
				String selectedCourseCodeStudies = tableStudies.getValueAt(row,
						0).toString();
				String studentPnr = textFieldPnrStudent.getText();
				String studentFirstName = textFieldFirstNameStudent.getText();

				// Confirm deletion of Course from Student
				Object[] options = { "Ja", "Nej" };
				int n = JOptionPane
						.showOptionDialog(null,
								"Är du säker på att du vill ta bort "
										+ selectedCourseNameStudies
										+ " från studenten " + studentFirstName
										+ "?", "", JOptionPane.DEFAULT_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (n == 0) {
					controller.deleteStudentFromCourse(studentPnr,
							selectedCourseCodeStudies);
					fillStudentInfo();
				}

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Markera en Kurs!");
		}
	}

	// Set result to Students (Studied)
	private void setResultToStudent() {
		int row = tableStudies.getSelectedRow();
		if (row != -1) {
			String studentPnr = textFieldPnrStudent.getText();
			String selectedCourseCodeStudies = tableStudies.getValueAt(row, 0)
					.toString();
			String grade = (String) comboBoxGrade.getSelectedItem();
			try {
				if (!controller.checkIfStudentAlreadyStudiedGradeU(studentPnr,
						selectedCourseCodeStudies)) {
					controller.deleteResult(studentPnr,
							selectedCourseCodeStudies);
				}
				controller.addResult(studentPnr, selectedCourseCodeStudies,
						grade);
				fillStudentInfo();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Markera en Kurs!");
		}
	}

	// Set result to Students from Courseinfobox (Studied)
	private void setResultToStudentByCourse() {
		if (tableCourseInfo.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "Markera en Student!");
		} else {
			try {
				int row1 = tableCourseInfo.getSelectedRow();
				int row2 = tableCourse.getSelectedRow();
				String selectedStudentPnr = tableCourseInfo.getValueAt(row1, 0)
						.toString();
				String selectedCourseCode = tableCourse.getValueAt(row2, 0)
						.toString();
				String grade = (String) comboBoxGradeByCourse.getSelectedItem();

				if (!controller.checkIfStudentAlreadyStudiedGradeU(
						selectedStudentPnr, selectedCourseCode)) {
					controller.deleteResult(selectedStudentPnr,
							selectedCourseCode);
				}
				controller.addResult(selectedStudentPnr, selectedCourseCode,
						grade);
				tableCourseInfo.setModel(controller
						.getStudentsOnCourse(selectedCourseCode));

				if (selectedStudentPnr.equals(textFieldPnrStudent.getText())) {
					tableStudied.setModel(controller
							.getStudiedToTable(selectedStudentPnr));
					tableStudies.setModel(controller
							.getStudiesToTable(selectedStudentPnr));
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}

	// Fill Studentinfo
	private void fillStudentInfo() {
		try {
			int row = tableStudent.getSelectedRow();
			if (row != -1) {
				String selectedStudent = tableStudent.getValueAt(row, 0)
						.toString();
				ResultSet rs = controller.getStudent(selectedStudent);
				rs.next();
				textFieldPnrStudent.setText(rs.getString("pnr"));
				textFieldFirstNameStudent.setText(rs.getString("firstName"));
				textFieldLastNameStudent.setText(rs.getString("lastName"));
				textFieldEmailStudent.setText(rs.getString("email"));
				tableStudies.setModel(controller.getStudiesToTable(rs
						.getString("pnr")));
				tableStudied.setModel(controller.getStudiedToTable(rs
						.getString("pnr")));
				textFieldPnrResult.setText(rs.getString("pnr"));
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	// Calculate percentage of A in course
	private void calcAs() {
		try {
			int row = tableCourse.getSelectedRow();
			if (row != -1) {
				String selectedCourseCode = tableCourse.getValueAt(row, 0)
						.toString();
				double percentAs = ((double) controller
						.getNumberOfAsOnCourse(selectedCourseCode) / (double) controller
						.getNumberOfGradesOnCourse(selectedCourseCode));
				percentAs = Math.round(percentAs * 100);
				lblAsInPercent.setText((percentAs + "%"));
				lblPercentageA.setVisible(true);
				lblAsInPercent.setVisible(true);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	private void setModelsAndClear() {
		textFieldPnrStudent.setText("");
		textFieldFirstNameStudent.setText("");
		textFieldLastNameStudent.setText("");
		textFieldEmailStudent.setText("");
		textFieldSearchCourseOnStudent.setText("");
		try {
			tableStudent.setModel(controller.getAllStudentsToTable());
			tableCourse.setModel(controller.getAllCoursesToTable());
			tableCourseInfo.setModel(controller.getStudentsOnCourse(""));
			tableStudies.setModel(controller.getStudiesToTable(""));
			tableStudied.setModel(controller.getStudiedToTable(""));
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	// Clear Textfields when register student
	private void clearAddStudentTextFields() {
		textFieldAddStudentPnr.setText("");
		textFieldAddStudentFirstName.setText("");
		textFieldAddStudentLastName.setText("");
		textFieldAddStudentEmail.setText("");
	}

	// Check if fields are empty
	private boolean checkIfAddStudentFieldsAreEmpty() {
		if (textFieldAddStudentPnr.getText().equals("")
				|| textFieldAddStudentFirstName.getText().equals("")
				|| textFieldAddStudentLastName.getText().equals("")
				|| textFieldAddStudentEmail.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	// Check if Student Exists
	private boolean checkIfStudentExists(String pnr) {
		try {
			if (controller.getStudent(pnr).next()) {
				return true;
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return false;
	}

	// Clear Textfields when register course
	private void clearNewCourseTextFields() {
		textFieldNewCourseCode.setText("");
		textFieldNewCourseName.setText("");
		textFieldNewCoursePoints.setText("");

	}

	// Check if CourseFields are empty
	private boolean checkIfCourseFieldsAreEmpty() {
		if (textFieldNewCourseCode.getText().equals("")
				|| textFieldNewCourseName.getText().equals("")
				|| textFieldNewCoursePoints.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	// Check if input is Integer
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		// if exception isn't thrown, then it is an integer
		return true;
	}

	// Check if Course Exists
	private boolean checkIfCourseExists(String courseCode) {
		try {
			if (controller.getCourse(courseCode).next()) {
				return true;
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return false;
	}

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// long startTime = System.currentTimeMillis();

					MainFrame window = new MainFrame();
					window.mainFrame.setVisible(true);

					// long endTime = System.currentTimeMillis();
					// long totalTime = endTime - startTime;
					// System.out.println(totalTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
