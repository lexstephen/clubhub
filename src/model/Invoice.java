package model;
/****************************************************************************************************
* Project: ClubHub
* Author(s): A. Dicks-Stephen, B. Lamaa, J. Thiessen
* Student Number: 100563954, 100911472, 100898311
* Date: Feb 03, 2016
* Description: Invoice model
****************************************************************************************************/
public class Invoice {
	/*
	 * 
		request.setAttribute("userID", request.getParameter("userID"));
		request.setAttribute("invDate", request.getParameter("invDate"));
		request.setAttribute("charge01", request.getParameter("charge01"));
		request.setAttribute("charge01qty", request.getParameter("charge01qty"));
		request.setAttribute("charge01subtotal", request.getParameter("charge01subtotal"));
		request.setAttribute("charge02", request.getParameter("charge02"));
		request.setAttribute("charge02qty", request.getParameter("charge02qty"));
		request.setAttribute("charge02subtotal", request.getParameter("charge02subtotal"));
		request.setAttribute("charge03", request.getParameter("charge03"));
		request.setAttribute("charge03qty", request.getParameter("charge03qty"));
		request.setAttribute("charge03subtotal", request.getParameter("charge03subtotal"));
		request.setAttribute("charge04", request.getParameter("charge04"));
		request.setAttribute("charge04qty", request.getParameter("charge04qty"));
		request.setAttribute("charge04subtotal", request.getParameter("charge04subtotal"));
		request.setAttribute("charge05", request.getParameter("charge05"));
		request.setAttribute("charge05qty", request.getParameter("charge05qty"));
		request.setAttribute("charge05subtotal", request.getParameter("charge05subtotal"));
		request.setAttribute("result", request.getParameter("result"));
		request.setAttribute("taxes", request.getParameter("taxes"));
		request.setAttribute("finalresult", request.getParameter("finalresult"));
	 * 
	 */
	private String id, invDate, status, userID;
	private String charge01, charge02, charge03, charge04, charge05;
	private String charge01qty, charge02qty, charge03qty, charge04qty, charge05qty;
	private String result, taxes, finalresult;
	
	public String getResult() {
		return result;
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

	public void setUserID(String userid) {
		this.userID = userID;
	}
}
