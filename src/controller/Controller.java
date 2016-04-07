package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import dal.*;

public class Controller {

	private DataAccessLayer dal;
	private String sqlString;
	private int numberOfAs;
	private int numberOfGrades;

	public Controller() {
		dal = new DataAccessLayer();
	}

	/**
	 * STUDENT METHODS
	 */

	// Get specific Student
	public ResultSet getStudent(String pnr) throws SQLException {
		return dal.getStudent(pnr);
	}

	// Get all Students
	public ResultSet getAllStudents() throws SQLException {
		return dal.getAllStudents();
	}

	// Add new Student
	public void addStudent(String pnr, String firstName, String lastName,
			String email) throws SQLException {
		dal.addStudent(pnr, firstName, lastName, email);
	}

	// Delete specific Student
	public void deleteStudent(String pnr) throws SQLException {
		dal.deleteStudent(pnr);
	}

	// Get specific Student to table
	public DefaultTableModel getStudentToTable(String studentSearch)
			throws SQLException {

		sqlString = "select pnr, concat(firstName, ' ' , lastName)";
		sqlString += " from Student";
		sqlString += " where pnr like '" + studentSearch + "%'";
		sqlString += " or firstName like '" + studentSearch + "%'";
		sqlString += " or lastName like '" + studentSearch + "%'";

		String[] columnIdentifiers = new String[] { "Pnr", "Namn" };

		return dal.getTable(sqlString, columnIdentifiers);

	}

	// Get all Student to table
	public DefaultTableModel getAllStudentsToTable() throws SQLException {
		sqlString = "select pnr, concat(firstName, ' ' , lastName), email";
		sqlString += " from Student";

		String[] columnIdentifiers = new String[] { "Pnr", "Namn" };

		return dal.getTable(sqlString, columnIdentifiers);
	}

	// Get Students on specific Course
	public DefaultTableModel getStudentsOnCourse(String courseCode)
			throws SQLException {
		sqlString = " select student.pnr, concat(firstName, ' ', lastName), email";
		sqlString += " from Student";
		sqlString += " inner join Studies on Student.pnr=Studies.pnr";
		sqlString += " and courseCode='" + courseCode + "'";

		String[] columnIdentifiers = new String[] { "Pnr", "Namn", "Email" };

		return dal.getTable(sqlString, columnIdentifiers);
	}

	/**
	 * COURSE METHODS
	 */

	// Get Course
	public ResultSet getCourse(String courseCode) throws SQLException {
		return dal.getCourse(courseCode);
	}

	// Get all Courses
	public ResultSet getAllCourses() throws SQLException {
		return dal.getAllCourses();
	}

	// Add new Course
	public void addCourse(String courseCode, String courseName, int points)
			throws SQLException {
		dal.addCourse(courseCode, courseName, points);
	}

	// Delete specific Course
	public void deleteCourse(String courseCode) throws SQLException {
		dal.deleteCourse(courseCode);
	}

	// Get all Courses to table
	public DefaultTableModel getAllCoursesToTable() throws SQLException {
		sqlString = "select *";
		sqlString += " from Course";

		String[] columnIdentifiers = new String[] { "Kurskod", "KursNamn",
				"Poäng" };

		return dal.getTable(sqlString, columnIdentifiers);
	}

	// Get specific Course to table
	public DefaultTableModel getCourseToTable(String courseSearch)
			throws SQLException {
		sqlString = "select *";
		sqlString += " from Course";
		sqlString += " where courseCode like '" + courseSearch + "%'";
		sqlString += " or courseName like '" + courseSearch + "%'";

		String[] columnIdentifiers = new String[] { "Kurskod", "KursNamn",
				"Poäng" };

		return dal.getTable(sqlString, columnIdentifiers);
	}

	/***
	 ** STUDIES METHODS
	 **/

	// Add new Student to Course
	public void addStudentToCourse(String pnr, String courseCode)
			throws SQLException {
		dal.addStudentToCourse(pnr, courseCode);
	}

	// Delete Student from Course
	public void deleteStudentFromCourse(String pnr, String courseCode)
			throws SQLException {
		dal.deleteStudentFromCourse(pnr, courseCode);
	}

	// Check if Student already Studies
	public boolean checkIfStudentAlreadyStudies(String pnr, String courseCode)
			throws SQLException {
		return dal.checkIfStudentAlreadyStudies(pnr, courseCode);
	}

	/**
	 * STUDIED METHODS
	 */

	// Add new Result
	public void addResult(String pnr, String courseCode, String grade)
			throws SQLException {
		dal.addResult(pnr, courseCode, grade);
	}

	// Delete specific Result
	public void deleteResult(String pnr, String courseCode) throws SQLException {
		dal.deleteResult(pnr, courseCode);
	}

	// Check if Student already Studied
	public boolean checkIfStudentAlreadyStudied(String pnr, String courseCode)
			throws SQLException {
		return dal.checkIfStudentAlreadyStudied(pnr, courseCode);
	}

	/**
	 * OTHER METHODS
	 */

	public DefaultTableModel getStudentCourseGrade(String pnr,
			String courseCode, String grade) throws SQLException {

		sqlString = "select s.pnr,concat(s.firstName,' ',s.lastName),c.courseCode,c.courseName,sd.grade,sd.enddate";
		sqlString += " from Student s";
		sqlString += " inner join Studied sd on sd.pnr=s.pnr";
		sqlString += " inner join Course c on c.courseCode=sd.courseCode";
		sqlString += " where s.pnr like '" + pnr + "%'";
		sqlString += " and c.courseCode like '" + courseCode + "%'";
		sqlString += " and sd.grade like '" + grade + "%'";

		String[] columnIdentifiers = new String[] { "Personnr", "Namn",
				"Kurskod", "Kursnamn", "Betyg", "Datum" };

		return dal.getTable(sqlString, columnIdentifiers);
	}

	// Get Top5 Courses
	public DefaultTableModel getTop5Courses() throws SQLException {
		sqlString = "select top 5 Course.courseCode, courseName, count(*) studentspassed";
		sqlString += " from Course";
		sqlString += " inner join Studied on Studied.courseCode=Course.courseCode";
		sqlString += " where Studied.grade!='U'";
		sqlString += " group by Course.courseCode, courseName, points";
		sqlString += " order by studentspassed desc";

		String[] columnIdentifiers = new String[] { "Kurskod", "Kursnamn",
				"Antal godkända" };

		return dal.getTable(sqlString, columnIdentifiers);
	}

	// Get Students current Courses to table
	public DefaultTableModel getStudiesToTable(String pnr) throws SQLException {
		sqlString = "select Course.courseCode, courseName, points from Studies";
		sqlString += " inner join Course on Course.courseCode=Studies.courseCode";
		sqlString += " where pnr = '" + pnr + "'";

		String[] columns = new String[] { "Kurskod", "Kursnamn", "Poäng" };

		return dal.getTable(sqlString, columns);
	}

	// Get Students finished Courses to table
	public DefaultTableModel getStudiedToTable(String pnr) throws SQLException {
		sqlString = "select courseName, points, grade, endDate from Studied";
		sqlString += " inner join Course on Course.courseCode=Studied.courseCode";
		sqlString += " where pnr = '" + pnr + "'";

		String[] columns = new String[] { "Kursnamn", "Poäng", "Betyg", "Datum" };

		return dal.getTable(sqlString, columns);
	}

	// Max 45 HP
	public boolean checkMax45HP(String pnr, int newPoints) throws SQLException {
		return dal.checkMax45HP(pnr, newPoints);
	}

	// Check if Student already Studied with grade U
	public boolean checkIfStudentAlreadyStudiedGradeU(String pnr,
			String courseCode) throws SQLException {
		return dal.checkIfStudentAlreadyStudiedGradeU(pnr, courseCode);
	}

	// Get number of Grades
	public int getNumberOfGradesOnCourse(String courseCode) throws SQLException {
		numberOfGrades = dal.getNumberOfGradesOnCourse(courseCode);
		return numberOfGrades;
	}

	// Get number of As
	public int getNumberOfAsOnCourse(String courseCode) throws SQLException {
		numberOfAs = dal.getNumberOfAsOnCourse(courseCode);
		return numberOfAs;
	}

}