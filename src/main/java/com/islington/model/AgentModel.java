package com.islington.model;

import java.time.LocalDate;

public class AgentModel{
	private int agentId;
    private String firstName;
    private String lastName;
    private String codename;
    private LocalDate dob;
    private String gender;
    private String email;
    private String number;
    private String password;
    private int programId;
    private String ImageUrl;

	public AgentModel(int agentId, String firstName, String lastName, String codename, LocalDate dob, String gender,
			String email, String number, String password, int programId) {
		super();
		this.agentId = agentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.codename = codename;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.number = number;
		this.password = password;
		this.programId = programId;
	}
	public AgentModel(String firstName, String lastName, String codename, LocalDate dob, String gender,
			String email, String number, String password, ProgramModel programModel) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.codename = codename;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.number = number;
		this.password = password;
	}
	public AgentModel(String codename, String password) {
		// TODO Auto-generated constructor stub
		this.codename = codename;
		this.password = password;
	}
	public AgentModel(int agent_id, String firstName , String lastName , String codename, String email, int programId ) {
		// TODO Auto-generated constructor stub
		this.firstName = firstName;
		this.lastName = lastName;
		this.codename = codename;
		this.email = email;
		this.programId = programId;
	}
	public AgentModel(Integer agentId, String codename, String email, String number) {
		// TODO Auto-generated constructor stub
		this.agentId = agentId;
		this.codename = codename;
		this.email = email;
		this.number = number;
	}
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCodename() {
		return codename;
	}
	public void setCodename(String codename) {
		this.codename = codename;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getImageUrl() {
		return ImageUrl;
	}
	public void setImageUrl(String imageUrl) {
		ImageUrl = imageUrl;
	}
    
}