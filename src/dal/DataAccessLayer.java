package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DataAccessLayer {

	private Connection con;
	private String sqlString;
	private int columnCount;
	private ResultSetMetaData metaData;

	public Connection connect() throws SQLException {
		try {
			con = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;databaseName=SYSB13-1;user=sysb13-1;password=123");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return con;
	}

	/**
	 * STUDENT METHODS
	 */

	// Get Student
	public ResultSet getStudent(String pnr) throws SQLException {
		sqlString = "select * from Student where pnr='" + pnr + "'";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		return ps.executeQuery();
	}

	// Get All Students
	public ResultSet getAllStudents() throws SQLException {
		sqlString = "select * from Student";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		return ps.executeQuery();
	}

	// Add New Student
	public void addStudent(String pnr, String firstName, String lastName,
			String email) throws SQLException {
		sqlString = "insert into Student values('" + pnr + "','" + firstName
				+ "','" + lastName + "','" + email + "')";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		ps.executeUpdate();
	}

	// Delete Student
		public void deleteStudent(String pnr) throws SQLException {
			sqlString = "delete from Student where pnr='" + pnr + "'";
			PreparedStatement ps = connect().prepareStatement(sqlString);
			ps.executeUpdate();
		}

	/**
	 * COURSE METHODS
	 */

	// Get Course
	public ResultSet getCourse(String courseCode) throws SQLException {
		sqlString = "select * from Course where courseCode='" + courseCode
				+ "'";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		return ps.executeQuery();
	}

	// Get All Courses
	public ResultSet getAllCourses() throws SQLException {
		sqlString = "select * from Course";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		return ps.executeQuery();
	}

	// Add New Course
	public void addCourse(String courseCode, String courseName, int points)
			throws SQLException {
		sqlString = "insert into Course values('" + courseCode + "','"
				+ courseName + "','" + points + "')";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		ps.executeUpdate();
	}

	// Delete Course
	public void deleteCourse(String courseCode) throws SQLException {
		sqlString = "delete from Course where courseCode='" + courseCode + "'";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		ps.executeUpdate();
	}

	/**
	 * STUDIES METHODS
	 */

	// Add Student to Course
	public void addStudentToCourse(String pnr, String courseCode)
			throws SQLException {
		sqlString = "insert into Studies values('" + pnr + "','" + courseCode
				+ "',GETDATE())";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		ps.executeUpdate();
	}

	// Delete Student from Course
	public void deleteStudentFromCourse(String pnr, String courseCode)
			throws SQLException {
		sqlString = "delete from Studies where pnr='" + pnr
				+ "' and courseCode='" + courseCode + "'";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		ps.executeUpdate();
	}

	/**
	 * STUDIED METHODS
	 */

	// Add new Result
	public void addResult(String pnr, String courseCode, String grade)
			throws SQLException {
		sqlString = "insert into Studied";
		sqlString += " values('" + pnr + "','" + courseCode + "','" + grade
				+ "',GETDATE())";
		PreparedStatement ps1 = connect().prepareStatement(sqlString);
		ps1.executeUpdate();

		sqlString = "delete from Studies";
		sqlString += " where pnr='" + pnr + "'";
		sqlString += " and courseCode='" + courseCode + "'";
		PreparedStatement ps2 = connect().prepareStatement(sqlString);
		ps2.executeUpdate();
	}

	// Delete specific Result
	public void deleteResult(String pnr, String courseCode) throws SQLException {
		sqlString = "delete from Studied";
		sqlString += " where pnr='" + pnr + "'";
		sqlString += " and courseCode='" + courseCode + "'";
		PreparedStatement ps = connect().prepareStatement(sqlString);
		ps.executeUpdate();
	}

	/**
	 * OTHER METHODS
	 */

	// Default Table Model
	public DefaultTableModel getTable(String sqlString,
			String[] columnIdentifiers) throws SQLException {

		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		Vector<String> columnNames = new Vector<String>();

		data.clear();
		columnNames.clear();

		PreparedStatement ps = connect().prepareStatement(sqlString);
		ResultSet rs = ps.executeQuery();

		metaData = rs.getMetaData();
		columnCount = metaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			columnNames.add(metaData.getColumnName(i));
		}

		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableModel.setColumnIdentifiers(columnIdentifiers);

		return tableModel;

	}

	// Max 45 HP
	public boolean checkMax45HP(String pnr, int newPoints) throws SQLException {
		sqlString = "select SUM(points) as totalpoints";
		sqlString += " from Student";
		sqlString += " inner join Studies on Studies.pnr=Student.pnr";
		sqlString += " inner join Course on Course.courseCode=Studies.courseCode";
		sqlString += " where Student.pnr='" + pnr + "'";
		sqlString += " group by Student.pnr;";

		PreparedStatement ps = connect().prepareStatement(sqlString);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			int currentPoints = rs.getInt("totalpoints");
			if (currentPoints + newPoints > 45) {
				return true;
			}
		}
		return false;
	}

	// Check if Student already studied course with grade U
	public boolean checkIfStudentAlreadyStudiedGradeU(String pnr,
			String courseCode) throws SQLException {
		sqlString = "select *";
		sqlString += " from Studied";
		sqlString += " where pnr='" + pnr + "'";
		;
		sqlString += " and courseCode='" + courseCode + "'";
		sqlString += " and grade='U'";

		PreparedStatement ps = connect().prepareStatement(sqlString);
		ResultSet rs = ps.executeQuery();

		if (!rs.isBeforeFirst()) {
			return true;
		}
		return false;
	}

	// Check if Student already studied course
	public boolean checkIfStudentAlreadyStudied(String pnr, String courseCode)
			throws SQLException {
		sqlString = "select *";
		sqlString += " from Studied";
		sqlString += " where pnr='" + pnr + "'";
		sqlString += " and courseCode='" + courseCode + "'";
		sqlString += " and grade!='U'";

		PreparedStatement ps = connect().prepareStatement(sqlString);
		ResultSet rs = ps.executeQuery();

		if (!rs.isBeforeFirst()) {
			return true;
		}
		return false;
	}

	// Check if Student already studies on course
	public boolean checkIfStudentAlreadyStudies(String pnr, String courseCode)
			throws SQLException {
		sqlString = "select *";
		sqlString += " from Studies";
		sqlString += " where pnr='" + pnr + "'";
		sqlString += " and courseCode='" + courseCode + "'";

		PreparedStatement ps = connect().prepareStatement(sqlString);
		ResultSet rs = ps.executeQuery();

		if (!rs.isBeforeFirst()) {
			return true;
		}
		return false;
	}

	// Get number of A:s on specific Course
	public int getNumberOfAsOnCourse(String courseCode) throws SQLException {
		sqlString = "select COUNT(grade) as [total As]";
		sqlString += " from Studied";
		sqlString += " where courseCode='" + courseCode + "'";
		sqlString += " and grade='A'";

		PreparedStatement ps = connect().prepareStatement(sqlString);
		ResultSet rs = ps.executeQuery();

		rs.next();

		int numberOfAs = rs.getInt("total As");
		if (numberOfAs != 0) {
			return numberOfAs;
		}
		return 0;
	}

	// Get number of students with grade on specific Course
	public int getNumberOfGradesOnCourse(String courseCode) throws SQLException {
		sqlString = "select COUNT(grade) as [total grades]";
		sqlString += " from Studied";
		sqlString += " where courseCode='" + courseCode + "'";

		PreparedStatement ps = connect().prepareStatement(sqlString);
		ResultSet rs = ps.executeQuery();

		rs.next();

		int numberOfGrades = rs.getInt("total grades");
		if (numberOfGrades != 0) {
			return numberOfGrades;
		}
		return 0;
	}

}
