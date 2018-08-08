package com.uiFramework.companyName.projectName.helper.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationDBQuery {
	
	public int getStutentClassNo(int sno) throws SQLException {
		int classNo = 0;
		String dbQuery = "select * from t_student where sno = " + sno + ";";
		ResultSet result = DataBaseHelper.getResultSet(dbQuery);
		while(result.next()) {
			classNo = Integer.parseInt(result.getString("classno"));
		}
		
		return classNo;
	}
	
	public static void main(String[] args) throws SQLException {
		ApplicationDBQuery applicationDBQuery = new ApplicationDBQuery();
		int num = applicationDBQuery.getStutentClassNo(8);
		System.out.println(num);
	}

}
