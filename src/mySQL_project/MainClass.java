package mySQL_project;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class MainClass {

	public static Scanner scanner = new Scanner(System.in);
	public static final int PRINT_PATIENTS = 1;
	public static final int PRINT_MEDICAL_RECORD = 2;
	public static final int SEARCH_MEDICAL_RECORD = 3;
	public static final int INSERT_PATIENTS = 4;
	public static final int INSERT_MEDICAL_RECORD = 5;
	public static final int UPDATE = 6;
	public static final int DELETE = 7;
	public static final int EXIT = 8;
	
	public static void main(String[] args) {
		boolean flag = true;
		
		while(flag) {
			System.out.print("\n\n\n1.환자명부출력 2.진료기록출력 3.진료기록검색 4.환자추가 5.진료기록 추가 6.데이터수정 7.데이터삭제 8.종료 >>");
			int answer = scanner.nextInt();
			switch (answer) {
			case PRINT_PATIENTS: 		printPatientsTBL(); 	break;
			case PRINT_MEDICAL_RECORD:	printMedicalRecordTBL();break;
			case SEARCH_MEDICAL_RECORD: searchMedicalRecord(); 	break;
			case INSERT_PATIENTS:		insertPatients(); 		break;
			case INSERT_MEDICAL_RECORD:	insertMedicalRecord(); 	break;
			case UPDATE:				updateTBL();			break;
			case DELETE:				deleteTBL();			break;
			case EXIT: 					flag = false; 			break;
			default: System.out.println("1~5번 중에 재입력 해주세요."); break;
			}//end of switch
			
		}//end of while
		
	}//end of main
	
	

	private static void deleteTBL() {
		boolean flag = true;
		while(flag) {
			System.out.println("1.환자명부삭제 2.진료기록삭제 3.종료");
			int answer = scanner.nextInt();
			switch (answer) {
				case 1: patientsDelete(); 		break;
				case 2: medicalRecordDelete(); 	break;
				case 3: flag = false; 			break;
			default: System.out.println("1~3번 중에 재입력 부탁드립니다."); break;
			}//end of switch
			
		}//end of while
		
	}

	private static void medicalRecordDelete() {
		System.out.print("환자의 아이디를 압력하세요 >>");
		String patientsID = scanner.next();
		
		MedicalRecord medicalRecord = QueryClass.selectQueryMedicalRecordWherePatientsID(patientsID);
		
		if(medicalRecord != null) {
			System.out.print("방문날짜를 입력하세요 >>");
			String visitDate = scanner.next();
			
			boolean returnValue = QueryClass.deleteMedicalRecordTBL(visitDate);
			if(returnValue == false) {
				System.out.println("삭제에 실패했습니다.");
			}else {
				System.out.println(returnValue);
			}
			
		}else {
			System.out.println("해당 환자아이디는 존재하지 않습니다.");
		}
		
	}

	private static void patientsDelete() {
		try {
			System.out.print("지우고자 하는 환자의 아이디를 압력하세요 >>");
			String patientsID = scanner.next();
			
			boolean returnValue = QueryClass.deleteQueryPatientsTBL(patientsID);
			if(returnValue == true) {
				System.out.println("삭제를 성공하였습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private static void updateTBL() {
		boolean flag = true;
		while(flag) {
			System.out.println("1.환자명부수정(전화번호, 주소) 2.진료기록수정(증상) 3.종료");
			int answer = scanner.nextInt();
			switch (answer) {
				case 1: patientsUpdate(); 		break;
				case 2: medicalRecordUpdate(); 	break;
				case 3: flag = false; 			break;
			default: System.out.println("1~3번 중에 재입력 부탁드립니다."); break;
			}//end of switch
			break;
		}//end of while
		
	}

	//진료날짜를 찾는 함수를 만들어야 함
	private static void medicalRecordUpdate() {
		System.out.print("수정할 환자의 아이디를 입력하세요 >>");
		String patientsID = scanner.next();
		MedicalRecord medicalRecord = QueryClass.selectQueryMedicalRecordWherePatientsID(patientsID);
		
		if(medicalRecord != null) {
			System.out.print("진료날짜를 입력하세요 >>");
			String visitDate = scanner.next();
			MedicalRecord medicalRedcord = QueryClass.selectQueryMedicalRecordWhereVisitDate(visitDate);
			System.out.print("원래 진료기록: " + medicalRecord.getSymptom() + "//\t" + "수정할 진료기록 >>");
			String symptom = scanner.next();

			medicalRecord.setSymptom(symptom);
			
			boolean returnValue = QueryClass.updateMedicalRecordTBL(medicalRecord);
			if(returnValue == false) {
				System.out.println("수정에 실패했습니다.");
			}else {
				System.out.println(returnValue);
			}
			
		}else {
			System.out.println("해당 진료날짜는 존재하지 않습니다.");
		}
		
	}

	private static void patientsUpdate() {
		System.out.print("수정할 환자의 아이디를 입력하세요 >>");
		String patientsID = scanner.next();
		Patients patients = QueryClass.selectQueryPatientsWherePatientsID(patientsID);
		if(patients != null) {
			System.out.println(patients);
			System.out.print("원래 전화번호:" + patients.getPatientsPhone() + "\t" + "수정할 전화번호 >>");
			String patientsPhone = scanner.next();
			System.out.print("원래 주소:" + patients.getPatientsAddress() + "\t" + "수정할 주소 >>");
			String patientsAddress = scanner.next();
			
			patients.setPatientsPhone(patientsPhone);
			patients.setPatientsAddress(patientsAddress);
			
			boolean returnValue = QueryClass.updatePatientsTBL(patients);
			if(returnValue == false) {
				System.out.println("수정에 실패했습니다.");
			}else {
				printPatientsTBL();
			}
			
		}else {
			System.out.println("찾는 환자 아이디가 없습니다.");
		}
		
	}

	private static void insertMedicalRecord() {
		boolean flag = false;
		MedicalRecord medicalRecord = null;
		MedicalRecord insertMedicalRecord = null;
		
		while(!flag) {
			System.out.print("진료기록을 추가할 환자의 아이디를 입력하세요 >>");
			try {
				String patientsID = scanner.next();
				medicalRecord = QueryClass.selectQueryMedicalRecordWherePatientsID(patientsID);
//				if(medicalRecord != null && medicalRecord.getPatientsID() == patientsID) {
//					System.out.println("중복된 환자 아이디입니다.");
//					continue;
//				}
				
//				System.out.print("환자 아이디 >>");
//				String patientsID = scanner.next();
				
				System.out.print("환자 방문날짜(예.20011230) >>");
				String visitDate = scanner.next();				
				System.out.print("환자 진료과목 >>");
				String medicalSubject = scanner.next();				
				System.out.print("담당의사 >>");
				String doctor = scanner.next();				
				System.out.print("증상 >>");
				String symptom = scanner.next();
				
				insertMedicalRecord = new MedicalRecord(patientsID, visitDate, medicalSubject, doctor, symptom);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("정확한 환자 아이디 입력부탁드립니다.");
			}
		}//end of while
		
		boolean returnValue = QueryClass.insertQueryMedicalRecord(insertMedicalRecord);
		if(returnValue == true) {
			System.out.println("환자 진료기록 명단 추가가 잘 이루어졌습니다.");
		}else {
			System.out.println("환자 진료기록 추가에 실패했습니다.");
		}
		
	}

	private static void insertPatients() {
		boolean flag = false;
		Patients patients = null;
		Patients insertPatients = null;
		
		while(!flag) {
			System.out.print("추가할 환자의 아이디를 입력하세요 >>");
			try {
				String patientsID = scanner.next();
				patients = QueryClass.selectQueryPatientsWherePatientsID(patientsID);
				if(patients != null && patients.getPatientsID() == patientsID) {
					System.out.println("중복된 환자 아이디입니다.");
					continue;
				}
				
//				System.out.print("환자 아이디 >>");
//				String patientsID = scanner.next();
				System.out.print("환자 이름 >>");
				String patientsName = scanner.next();
				System.out.print("환자 생년월일(예.20011230) >>");
				String patientsBirth = scanner.next();
				System.out.print("환자 전화번호 >>");
				String patientsPhone = scanner.next();
				System.out.print("환자 집주소 >>");
				String patientsAddress = scanner.next();
				
				insertPatients = new Patients(patientsID, patientsName, patientsBirth, patientsPhone, patientsAddress);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("정확한 환자 아이디 입력부탁드립니다.");
			}
		}//end of while
		
		boolean returnValue = QueryClass.insertQueryPatients(insertPatients);
		if(returnValue == true) {
			System.out.println("환자 명단 추가가 잘 이루어졌습니다.");
		}else {
			System.out.println("환자 명단 추가에 실패했습니다.");
		}
	}

	///////////////////////////////////////
	private static void searchMedicalRecord() {
		boolean flag = true;
		try {
			while(flag) {
				System.out.print("환자 아이디를 입력하세요 (종료:-1)>>");
				String patientsID = scanner.next();
				if(patientsID.equals(-1)) {
					flag = false;
					break;
				}
				
				HashSet<MedicalRecord> medicalRecord = QueryClass.selectQueryMedicalRecord(patientsID);
				if(medicalRecord != null) {
					System.out.println(medicalRecord);
				}else {
					System.out.println("없는 환자 아이디입니다.");
				}
				break;
			}//end of while
		} catch (Exception e) {
			System.out.println("MainClass searchMedicalRecord error");
		}
		
	}
	
	private static void printMedicalRecordTBL() {
		HashSet<MedicalRecord> hashSet = new HashSet<>();
		hashSet.addAll(QueryClass.selectQueryMedicalRecord());
		
		if(hashSet != null) {
			Iterator<MedicalRecord> iterator = hashSet.iterator();
			System.out.println("아이디 \t 진료일자\t\t 진료과목 \t 담당의 \t 증상");
			while(iterator.hasNext()) {
				MedicalRecord medicalRecord = iterator.next();
				System.out.println(medicalRecord);
			}//end of while
		}else {
			System.out.println("MainClass, printMedicalRecordTBL error");
		}//end of if	
			
	}

	private static void printPatientsTBL() {
		HashSet<Patients> hashSet = new HashSet<Patients>();
		hashSet.addAll(QueryClass.selectQueryPatients());
		
		if(hashSet != null) {
			Iterator<Patients> iterator = hashSet.iterator();
			System.out.println("아이디 \t 이름 \t 생년월일 \t\t 연락처 \t\t 주소");
			while(iterator.hasNext()) {
				Patients patients = iterator.next();
				System.out.println(patients);
			}//end of while
		}else {
			System.out.println("MainClass, printPatientsTBL error");
		}//end of if
	}

	
	
}//end of program

