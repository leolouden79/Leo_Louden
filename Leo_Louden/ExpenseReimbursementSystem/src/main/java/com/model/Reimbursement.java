package com.model;

public class Reimbursement {
	
	//keep imbursement id in the json when you update the status you up date the right one in the database
	
public int id;
public int amount;
public String submitted;
public String resolved;
public String description;
public int author;
public int resolver;
public int status;
public int type;

public String author_str;
public String resolver_str;
public String status_str;
public String type_str;

	public Reimbursement(int parseInt, int parseInt2, String string, String string2, String string3, int parseInt3,
			int parseInt4, int parseInt5, int parseInt6) {
		
		this.id = parseInt;
		this.amount = parseInt2;
		this.submitted = string;
		this.resolved = string2;
		this.description = string3;
		this.author = parseInt3;
		this.resolver = parseInt4;
		this.status = parseInt5;
		this.type = parseInt6;
		
	}
	
	public void setRemaingFields(String auth, String resolv, String stat, String typ){
		this.author_str = auth;
		this.resolver_str = resolv;
		this.status_str = stat;
		this.type_str = typ;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + ", author_str=" + author_str + ", resolver_str=" + resolver_str
				+ ", status_str=" + status_str + ", type_str=" + type_str + "]";
	}
	
	
	

}
