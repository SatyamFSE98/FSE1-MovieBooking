package com.moviebooking.auth.payload;
public class ResetRequest {
	
	private String username;
	private String newPassword;
	private String secQuestion;
	private String secAnswer;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getSecQuestion() {
		return secQuestion;
	}
	public void setSecQuestion(String secQuestion) {
		this.secQuestion = secQuestion;
	}
	public String getSecAnswer() {
		return secAnswer;
	}
	public void setSecAnswer(String secAnswer) {
		this.secAnswer = secAnswer;
	}	

}
