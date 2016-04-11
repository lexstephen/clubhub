package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: Invoice model
****************************************************************************************************/
public class Invoice {
	private String id, invDate, status, userID, username, firstName, lastName;	
	private String charge01, charge02, charge03, charge04, charge05;
	private String charge01qty, charge02qty, charge03qty, charge04qty, charge05qty;
	private String charge01subtotal, charge02subtotal, charge03subtotal, charge04subtotal, charge05subtotal;
	private String result, taxes, finalresult;
	

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

	public String getResult() {
		return result;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCharge01subtotal() {
		return charge01subtotal;
	}

	public void setCharge01subtotal(String charge01subtotal) {
		this.charge01subtotal = charge01subtotal;
	}

	public String getCharge02subtotal() {
		return charge02subtotal;
	}

	public void setCharge02subtotal(String charge02subtotal) {
		this.charge02subtotal = charge02subtotal;
	}

	public String getCharge03subtotal() {
		return charge03subtotal;
	}

	public void setCharge03subtotal(String charge03subtotal) {
		this.charge03subtotal = charge03subtotal;
	}

	public String getCharge04subtotal() {
		return charge04subtotal;
	}

	public void setCharge04subtotal(String charge04subtotal) {
		this.charge04subtotal = charge04subtotal;
	}

	public String getCharge05subtotal() {
		return charge05subtotal;
	}

	public void setCharge05subtotal(String charge05subtotal) {
		this.charge05subtotal = charge05subtotal;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public String getFinalresult() {
		return finalresult;
	}

	public void setFinalresult(String finalresult) {
		this.finalresult = finalresult;
	}

	public String getCharge01() {
		return charge01;
	}

	public void setCharge01(String charge01) {
		this.charge01 = charge01;
	}

	public String getCharge02() {
		return charge02;
	}

	public void setCharge02(String charge02) {
		this.charge02 = charge02;
	}

	public String getCharge03() {
		return charge03;
	}

	public void setCharge03(String charge03) {
		this.charge03 = charge03;
	}

	public String getCharge04() {
		return charge04;
	}

	public void setCharge04(String charge04) {
		this.charge04 = charge04;
	}

	public String getCharge05() {
		return charge05;
	}

	public void setCharge05(String charge05) {
		this.charge05 = charge05;
	}

	public String getCharge01qty() {
		return charge01qty;
	}

	public void setCharge01qty(String charge01qty) {
		this.charge01qty = charge01qty;
	}

	public String getCharge02qty() {
		return charge02qty;
	}

	public void setCharge02qty(String charge02qty) {
		this.charge02qty = charge02qty;
	}

	public String getCharge03qty() {
		return charge03qty;
	}

	public void setCharge03qty(String charge03qty) {
		this.charge03qty = charge03qty;
	}

	public String getCharge04qty() {
		return charge04qty;
	}

	public void setCharge04qty(String charge04qty) {
		this.charge04qty = charge04qty;
	}

	public String getCharge05qty() {
		return charge05qty;
	}

	public void setCharge05qty(String charge05qty) {
		this.charge05qty = charge05qty;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
