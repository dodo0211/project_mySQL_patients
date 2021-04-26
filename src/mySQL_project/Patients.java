package mySQL_project;

public class Patients {
	private String patientsID;
	private String patientsName;
	private String patientsBirth;
	private String patientsPhone;
	private String patientsAddress;
	
	public Patients(String patientsID, String patientsName, String patientsBirth, String patientsPhone,
			String patientsAddress) {
		super();
		this.patientsID = patientsID;
		this.patientsName = patientsName;
		this.patientsBirth = patientsBirth;
		this.patientsPhone = patientsPhone;
		this.patientsAddress = patientsAddress;
	}

	//getters
	public String getPatientsID() {
		return patientsID;
	}
	
	public String getPatientsName() {
		return patientsName;
	}
	
	public String getPatientsBirth() {
		return patientsBirth;
	}
	
	public String getPatientsPhone() {
		return patientsPhone;
	}

	public String getPatientsAddress() {
		return patientsAddress;
	}
	
	//setters
	public void setPatientsID(String patientsID) {
		this.patientsID = patientsID;
	}

	public void setPatientsName(String patientsName) {
		this.patientsName = patientsName;
	}

	public void setPatientsBirth(String patientsBirth) {
		this.patientsBirth = patientsBirth;
	}

	public void setPatientsPhone(String patientsPhone) {
		this.patientsPhone = patientsPhone;
	}

	public void setPatientsAddress(String patientsAddress) {
		this.patientsAddress = patientsAddress;
	}

	
	
	@Override
	public String toString() {
		return patientsID + "\t" + patientsName + "\t"
				+ patientsBirth + "\t" + patientsPhone + "\t" + patientsAddress;
	}

	@Override
	public int hashCode() {
		return patientsID.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Patients) {
			Patients patients = (Patients) obj;
			if(patients.patientsID == this.patientsID) {
				return true;
			}
		}
		return false;
	}
	
}//end of class
