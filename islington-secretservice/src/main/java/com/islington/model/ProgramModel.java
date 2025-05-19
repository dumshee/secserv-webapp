package com.islington.model;

public class ProgramModel{
	private String program_name;
	private String program_description;
	public ProgramModel(String program_name, String program_description) {
		super();
		this.program_name = program_name;
		this.program_description = program_description;
	}
	public ProgramModel(String program_name) {
		this.program_name = program_name;
	}
	public String getProgram_name() {
		return program_name;
	}
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	public String getProgram_description() {
		return program_description;
	}
	public void setProgram_description(String program_description) {
		this.program_description = program_description;
	}
	
}