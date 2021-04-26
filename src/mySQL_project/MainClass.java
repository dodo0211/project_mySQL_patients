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
			System.out.print("\n\n\n1.ȯ�ڸ����� 2.��������� 3.�����ϰ˻� 4.ȯ���߰� 5.������ �߰� 6.�����ͼ��� 7.�����ͻ��� 8.���� >>");
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
			default: System.out.println("1~5�� �߿� ���Է� ���ּ���."); break;
			}//end of switch
			
		}//end of while
		
	}//end of main
	
	

	private static void deleteTBL() {
		boolean flag = true;
		while(flag) {
			System.out.println("1.ȯ�ڸ�λ��� 2.�����ϻ��� 3.����");
			int answer = scanner.nextInt();
			switch (answer) {
				case 1: patientsDelete(); 		break;
				case 2: medicalRecordDelete(); 	break;
				case 3: flag = false; 			break;
			default: System.out.println("1~3�� �߿� ���Է� ��Ź�帳�ϴ�."); break;
			}//end of switch
			
		}//end of while
		
	}

	private static void medicalRecordDelete() {
		System.out.print("ȯ���� ���̵� �з��ϼ��� >>");
		String patientsID = scanner.next();
		
		MedicalRecord medicalRecord = QueryClass.selectQueryMedicalRecordWherePatientsID(patientsID);
		
		if(medicalRecord != null) {
			System.out.print("�湮��¥�� �Է��ϼ��� >>");
			String visitDate = scanner.next();
			
			boolean returnValue = QueryClass.deleteMedicalRecordTBL(visitDate);
			if(returnValue == false) {
				System.out.println("������ �����߽��ϴ�.");
			}else {
				System.out.println(returnValue);
			}
			
		}else {
			System.out.println("�ش� ȯ�ھ��̵�� �������� �ʽ��ϴ�.");
		}
		
	}

	private static void patientsDelete() {
		try {
			System.out.print("������� �ϴ� ȯ���� ���̵� �з��ϼ��� >>");
			String patientsID = scanner.next();
			
			boolean returnValue = QueryClass.deleteQueryPatientsTBL(patientsID);
			if(returnValue == true) {
				System.out.println("������ �����Ͽ����ϴ�.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private static void updateTBL() {
		boolean flag = true;
		while(flag) {
			System.out.println("1.ȯ�ڸ�μ���(��ȭ��ȣ, �ּ�) 2.�����ϼ���(����) 3.����");
			int answer = scanner.nextInt();
			switch (answer) {
				case 1: patientsUpdate(); 		break;
				case 2: medicalRecordUpdate(); 	break;
				case 3: flag = false; 			break;
			default: System.out.println("1~3�� �߿� ���Է� ��Ź�帳�ϴ�."); break;
			}//end of switch
			break;
		}//end of while
		
	}

	//���ᳯ¥�� ã�� �Լ��� ������ ��
	private static void medicalRecordUpdate() {
		System.out.print("������ ȯ���� ���̵� �Է��ϼ��� >>");
		String patientsID = scanner.next();
		MedicalRecord medicalRecord = QueryClass.selectQueryMedicalRecordWherePatientsID(patientsID);
		
		if(medicalRecord != null) {
			System.out.print("���ᳯ¥�� �Է��ϼ��� >>");
			String visitDate = scanner.next();
			MedicalRecord medicalRedcord = QueryClass.selectQueryMedicalRecordWhereVisitDate(visitDate);
			System.out.print("���� ������: " + medicalRecord.getSymptom() + "//\t" + "������ ������ >>");
			String symptom = scanner.next();

			medicalRecord.setSymptom(symptom);
			
			boolean returnValue = QueryClass.updateMedicalRecordTBL(medicalRecord);
			if(returnValue == false) {
				System.out.println("������ �����߽��ϴ�.");
			}else {
				System.out.println(returnValue);
			}
			
		}else {
			System.out.println("�ش� ���ᳯ¥�� �������� �ʽ��ϴ�.");
		}
		
	}

	private static void patientsUpdate() {
		System.out.print("������ ȯ���� ���̵� �Է��ϼ��� >>");
		String patientsID = scanner.next();
		Patients patients = QueryClass.selectQueryPatientsWherePatientsID(patientsID);
		if(patients != null) {
			System.out.println(patients);
			System.out.print("���� ��ȭ��ȣ:" + patients.getPatientsPhone() + "\t" + "������ ��ȭ��ȣ >>");
			String patientsPhone = scanner.next();
			System.out.print("���� �ּ�:" + patients.getPatientsAddress() + "\t" + "������ �ּ� >>");
			String patientsAddress = scanner.next();
			
			patients.setPatientsPhone(patientsPhone);
			patients.setPatientsAddress(patientsAddress);
			
			boolean returnValue = QueryClass.updatePatientsTBL(patients);
			if(returnValue == false) {
				System.out.println("������ �����߽��ϴ�.");
			}else {
				printPatientsTBL();
			}
			
		}else {
			System.out.println("ã�� ȯ�� ���̵� �����ϴ�.");
		}
		
	}

	private static void insertMedicalRecord() {
		boolean flag = false;
		MedicalRecord medicalRecord = null;
		MedicalRecord insertMedicalRecord = null;
		
		while(!flag) {
			System.out.print("�������� �߰��� ȯ���� ���̵� �Է��ϼ��� >>");
			try {
				String patientsID = scanner.next();
				medicalRecord = QueryClass.selectQueryMedicalRecordWherePatientsID(patientsID);
//				if(medicalRecord != null && medicalRecord.getPatientsID() == patientsID) {
//					System.out.println("�ߺ��� ȯ�� ���̵��Դϴ�.");
//					continue;
//				}
				
//				System.out.print("ȯ�� ���̵� >>");
//				String patientsID = scanner.next();
				
				System.out.print("ȯ�� �湮��¥(��.20011230) >>");
				String visitDate = scanner.next();				
				System.out.print("ȯ�� ������� >>");
				String medicalSubject = scanner.next();				
				System.out.print("����ǻ� >>");
				String doctor = scanner.next();				
				System.out.print("���� >>");
				String symptom = scanner.next();
				
				insertMedicalRecord = new MedicalRecord(patientsID, visitDate, medicalSubject, doctor, symptom);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��Ȯ�� ȯ�� ���̵� �Էº�Ź�帳�ϴ�.");
			}
		}//end of while
		
		boolean returnValue = QueryClass.insertQueryMedicalRecord(insertMedicalRecord);
		if(returnValue == true) {
			System.out.println("ȯ�� ������ ��� �߰��� �� �̷�������ϴ�.");
		}else {
			System.out.println("ȯ�� ������ �߰��� �����߽��ϴ�.");
		}
		
	}

	private static void insertPatients() {
		boolean flag = false;
		Patients patients = null;
		Patients insertPatients = null;
		
		while(!flag) {
			System.out.print("�߰��� ȯ���� ���̵� �Է��ϼ��� >>");
			try {
				String patientsID = scanner.next();
				patients = QueryClass.selectQueryPatientsWherePatientsID(patientsID);
				if(patients != null && patients.getPatientsID() == patientsID) {
					System.out.println("�ߺ��� ȯ�� ���̵��Դϴ�.");
					continue;
				}
				
//				System.out.print("ȯ�� ���̵� >>");
//				String patientsID = scanner.next();
				System.out.print("ȯ�� �̸� >>");
				String patientsName = scanner.next();
				System.out.print("ȯ�� �������(��.20011230) >>");
				String patientsBirth = scanner.next();
				System.out.print("ȯ�� ��ȭ��ȣ >>");
				String patientsPhone = scanner.next();
				System.out.print("ȯ�� ���ּ� >>");
				String patientsAddress = scanner.next();
				
				insertPatients = new Patients(patientsID, patientsName, patientsBirth, patientsPhone, patientsAddress);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("��Ȯ�� ȯ�� ���̵� �Էº�Ź�帳�ϴ�.");
			}
		}//end of while
		
		boolean returnValue = QueryClass.insertQueryPatients(insertPatients);
		if(returnValue == true) {
			System.out.println("ȯ�� ��� �߰��� �� �̷�������ϴ�.");
		}else {
			System.out.println("ȯ�� ��� �߰��� �����߽��ϴ�.");
		}
	}

	///////////////////////////////////////
	private static void searchMedicalRecord() {
		boolean flag = true;
		try {
			while(flag) {
				System.out.print("ȯ�� ���̵� �Է��ϼ��� (����:-1)>>");
				String patientsID = scanner.next();
				if(patientsID.equals(-1)) {
					flag = false;
					break;
				}
				
				HashSet<MedicalRecord> medicalRecord = QueryClass.selectQueryMedicalRecord(patientsID);
				if(medicalRecord != null) {
					System.out.println(medicalRecord);
				}else {
					System.out.println("���� ȯ�� ���̵��Դϴ�.");
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
			System.out.println("���̵� \t ��������\t\t ������� \t ����� \t ����");
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
			System.out.println("���̵� \t �̸� \t ������� \t\t ����ó \t\t �ּ�");
			while(iterator.hasNext()) {
				Patients patients = iterator.next();
				System.out.println(patients);
			}//end of while
		}else {
			System.out.println("MainClass, printPatientsTBL error");
		}//end of if
	}

	
	
}//end of program

