package com.uaes.esw.entity.participant;

import java.io.Serializable;

public class AdOU implements Serializable {
	private static final long serialVersionUID = -8821407129939931386L;
	private AdAccount account;
	private String ou;

	public AdAccount getAccount() {
		return account;
	}

	public void setAccount(AdAccount account) {
		this.account = account;
	}

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

}
