package mySQL_project;

public class MedicalRecord {
	private String patientsID;
	private String visitDate;
	private String medicalSubject;
	private String doctor;
	private String symptom;
	
	public MedicalRecord(String patientsID, String visitDate, String medicalSubject, String doctor, String symptom) {
		super();
		this.patientsID = patientsID;
		this.visitDate = visitDate;
		this.medicalSubject = medicalSubject;
		this.doctor = doctor;
		this.symptom = symptom;
	}

	//getters
	public String getPatientsID() {
		return patientsID;
	}
	
	public String getVisitDate() {
		return visitDate;
	}
	
	public String getMedicalSubject() {
		return medicalSubject;
	}

	public String getDoctor() {
		return doctor;
	}


	public String getSymptom() {
		return symptom;
	}
	
	//setters
	public void setPatientsID(String patientsID) {
		this.patientsID = patientsID;
	}
	
	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}
	
	public void setMedicalSubject(String medicalSubject) {
		this.medicalSubject = medicalSubject;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}


	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	@Override
	public String toString() {
		return patientsID + "\t" + visitDate + "\t"
				+ medicalSubject + "\t" + doctor + "\t" + symptom;
	}
	

}
