package mySQL_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

public class QueryClass {

	//환자명부 출력(select)
	public static HashSet<Patients> selectQueryPatients() {
		Patients patients = null;
		HashSet<Patients> hashSet = new HashSet<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from patientsTBL";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String patientsID = resultSet.getString(1);
				String patientsName = resultSet.getString(2);
				String patientsBirth = resultSet.getString(3);
				String patientsPhone = resultSet.getString(4);
				String patientsAddress = resultSet.getString(5);
				
				patients = new Patients(patientsID, patientsName, patientsBirth, patientsPhone, patientsAddress);
				hashSet.add(patients);
			}//end of while		
		} catch (SQLException e) {
			System.out.println("QueryClass.selectQueryPatients error");
			e.printStackTrace();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
		}//end of finally
		
		return hashSet;
	}
	
	public static HashSet<MedicalRecord> selectQueryMedicalRecord() {
		MedicalRecord medicalRecord = null;
		HashSet<MedicalRecord> hashSet = new HashSet<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from MedicalRecordTBL";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String patientsID = resultSet.getString(1);
				String visitDate = resultSet.getString(2);
				String medicalSubject = resultSet.getString(3);
				String doctor = resultSet.getString(4);
				String symptom = resultSet.getString(5);
				
				medicalRecord = new MedicalRecord(patientsID, visitDate, medicalSubject, doctor, symptom);
				hashSet.add(medicalRecord);
			}//end of while		
		} catch (SQLException e) {
			System.out.println("QueryClass.selectQueryPatients error");
			e.printStackTrace();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
		}//end of finally
		
		return hashSet;
	}

	//진료기록 검색해서 출력(select)
	public static HashSet<MedicalRecord> selectQueryMedicalRecord(String patientsID) {
		HashSet<MedicalRecord> hashSet = new HashSet<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from medicalRecordTBL where PatientsID = ?";
		
		MedicalRecord medicalRecord = null;
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, patientsID);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String patientsID2 = resultSet.getString(1);
				String visitDate = resultSet.getString(2);
				String medicalSubject = resultSet.getString(3);
				String doctor = resultSet.getString(4);
				String symptom = resultSet.getString(5);
				
				medicalRecord = new MedicalRecord(patientsID, visitDate, medicalSubject, doctor, symptom);
				hashSet.add(medicalRecord);
			}//end of while
		} catch (Exception e) {
			System.out.println("QueryClass MedicalRecord error" + e.toString());
		} finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
		}
		
		
		return hashSet;
	}

	//환자테이블에서 환자아이디 찾기
	public static Patients selectQueryPatientsWherePatientsID(String patientsID) {
		Patients patients = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "    select * from PatientsTBL where patientsID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, patientsID);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String patientsID2 = resultSet.getString(1);
				String patientsName = resultSet.getString(2);
				String patientsBirth = resultSet.getString(3);
				String patientsPhone = resultSet.getString(4);
				String patientsAddress = resultSet.getString(5);
				
				patients = new Patients(patientsID, patientsName, patientsBirth, patientsPhone, patientsAddress);
			}//end of while
			
		} catch (Exception e) {
			System.out.println("QueryClass selectQueryPatientsWherePatientsID" +e.toString());
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
		}//end of finally
		
		return patients;
	}

	//환자테이블 삽입
	public static boolean insertQueryPatients(Patients insertPatients) {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String insertQuery = "insert into patientsTBL	values(?, ?, ?, ?, ?)";

		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, insertPatients.getPatientsID());
			preparedStatement.setString(2, insertPatients.getPatientsName());
			preparedStatement.setString(3, insertPatients.getPatientsBirth());
			preparedStatement.setString(4, insertPatients.getPatientsPhone());
			preparedStatement.setString(5, insertPatients.getPatientsAddress());
			
			int count = preparedStatement.executeUpdate();
			
			if(count == 1) {
				System.out.println(insertPatients.getPatientsName() + "님 삽입 성공");
				flag = true;
			}else {
				System.out.println(insertPatients.getPatientsName() + "님 삽입 실패");
			}		
		} catch (Exception e) {
			System.out.println("QueryClass.insertQueryPatients error");
			e.toString();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(preparedStatement != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
		}
		
		return true;
	}

	
	public static MedicalRecord selectQueryMedicalRecordWherePatientsID(String patientsID) {
		MedicalRecord medicalRecord = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from medicalRecordTBL where patientsID = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, patientsID);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String patientsID2 = resultSet.getString(1);
				String visitDate = resultSet.getString(2);
				String medicalSubject = resultSet.getString(3);
				String doctor = resultSet.getString(4);
				String symptom = resultSet.getString(5);
				
				medicalRecord = new MedicalRecord(patientsID, visitDate, medicalSubject, doctor, symptom);
			}//end of while
			
		} catch (Exception e) {
			System.out.println("QueryClass selectQueryMedicalRecordWherePatientsID" +e.toString());
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
		}//end of finally
		
		return medicalRecord;
	}

	//진료기록 삽입
	public static boolean insertQueryMedicalRecord(MedicalRecord insertMedicalRecord) {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String insertQuery = "insert into medicalRecordTBL	values(?, ?, ?, ?, ?)";

		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(insertQuery);
			preparedStatement.setString(1, insertMedicalRecord.getPatientsID());
			preparedStatement.setString(2, insertMedicalRecord.getVisitDate());
			preparedStatement.setString(3, insertMedicalRecord.getMedicalSubject());
			preparedStatement.setString(4, insertMedicalRecord.getDoctor());
			preparedStatement.setString(5, insertMedicalRecord.getSymptom());
			
			int count = preparedStatement.executeUpdate();
			
			if(count == 1) {
				System.out.println(insertMedicalRecord.getPatientsID() + "님 삽입 성공");
				flag = true;
			}else {
				System.out.println(insertMedicalRecord.getPatientsID() + "님 삽입 실패");
			}		
		} catch (Exception e) {
			System.out.println("QueryClass.insertQueryMedicalRecord error");
			e.toString();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(preparedStatement != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
		}
		
		return true;
	}

	public static boolean updatePatientsTBL(Patients patients) {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String updateQuery = "update patientsTBL set patientsPhone = ?, patientsAddress = ? where patientsID = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, patients.getPatientsPhone());
			preparedStatement.setString(2, patients.getPatientsAddress());
			preparedStatement.setString(3, patients.getPatientsID());
			
			int count = preparedStatement.executeUpdate();
			
			if(count != 0) {
				System.out.println(patients.getPatientsID() + "님 환자명부 수정 성공");
				flag = true;
			}else {
				System.out.println(patients.getPatientsID() + "님 환자명부 수정 실패");
			}
			
		} catch (Exception e) {
			System.out.println("QueryClass.updatePatientsTBL error");
			e.toString();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(preparedStatement != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//end of finally
		
		return flag;
	}

	public static MedicalRecord selectQueryMedicalRecordWhereVisitDate(String visitDate) {
		MedicalRecord medicalRecord = null;
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = null;
		String selectQuery = "select * from medicalRecordTBL where patientsID = ? and visitDate = ?";
		
		try {
			preparedStatement = connection.prepareStatement(selectQuery);
			preparedStatement.setString(1, visitDate);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String patientsID = resultSet.getString(1);
				String visitDate2 = resultSet.getString(2);
				String medicalSubject = resultSet.getString(3);
				String doctor = resultSet.getString(4);
				String symptom = resultSet.getString(5);
				
				medicalRecord = new MedicalRecord(patientsID, visitDate, medicalSubject, doctor, symptom);
			}//end of while
			
		} catch (Exception e) {
			System.out.println("QueryClass selectQueryMedicalRecordWherePatientsID" +e.toString());
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
			
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}//end of if
		}//end of finally
		
		return medicalRecord;
	}

	public static boolean updateMedicalRecordTBL(MedicalRecord medicalRecord) {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String updateQuery = "update medicalRecordTBL set symptom = ? where patientsID = ? and visitDate = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(updateQuery);
			preparedStatement.setString(1, medicalRecord.getSymptom());
			preparedStatement.setString(2, medicalRecord.getPatientsID());
			preparedStatement.setString(3, medicalRecord.getVisitDate());
			
			int count = preparedStatement.executeUpdate();
			
			if(count != 0) {
				System.out.println(medicalRecord.getPatientsID() + "님 환자명부 수정 성공");
				flag = true;
			}else {
				System.out.println(medicalRecord.getPatientsID() + "님 환자명부 수정 실패");
			}
			
		} catch (Exception e) {
			System.out.println("QueryClass.updateMedicalRecordTBL error" + e.toString());
			e.toString();
		}finally {
			if(preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(preparedStatement != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}//end of finally
				
		return flag;
			
	}

	public static boolean deleteQueryPatientsTBL(String patientsID) {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String deleteQuerypatientsTBL = "delete from patientsTBL where patientsID = ?";
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(deleteQuerypatientsTBL);
			preparedStatement.setString(1, patientsID);
			
			int count = preparedStatement.executeUpdate();
			if(count == 1) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		return true;
	}

	public static boolean deleteMedicalRecordTBL(String visitDate) {
		boolean flag = false;
		Connection connection = DBUtil.getConnection();
		String deleteQueryMedicalRecordTBL = "delete from medicalRecord where visitDate = ?";
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(deleteQueryMedicalRecordTBL);
			preparedStatement.setString(1, visitDate);
			
			int count = preparedStatement.executeUpdate();
			if(count == 1) {
				System.out.println("삭제 성공");
			}else {
				System.out.println("삭제 실패");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
			
		return true;
	}

	

}//end of class
