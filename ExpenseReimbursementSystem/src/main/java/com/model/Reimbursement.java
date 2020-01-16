package com.model;

//The object representation of a reimbursement ticket
public class Reimbursement {
		
private int id;
private int amount;
private String submitted;
private String resolved;
private String description;

//Database ID
private int author;
private int resolver;
private int status;
private int type;

//String name that corresponds to database ID 
private String author_str;
private String resolver_str;
private String status_str;
private String type_str;

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


	public int getId() {
		return id;
	}


	public int getAmount() {
		return amount;
	}


	public String getSubmitted() {
		return submitted;
	}


	public String getResolved() {
		return resolved;
	}


	public String getDescription() {
		return description;
	}


	public int getAuthor() {
		return author;
	}


	public int getResolver() {
		return resolver;
	}


	public int getStatus() {
		return status;
	}


	public int getType() {
		return type;
	}


	public String getAuthor_str() {
		return author_str;
	}


	public String getResolver_str() {
		return resolver_str;
	}


	public String getStatus_str() {
		return status_str;
	}


	public String getType_str() {
		return type_str;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}


	public void setResolved(String resolved) {
		this.resolved = resolved;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setAuthor(int author) {
		this.author = author;
	}


	public void setResolver(int resolver) {
		this.resolver = resolver;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public void setType(int type) {
		this.type = type;
	}


	public void setAuthor_str(String author_str) {
		this.author_str = author_str;
	}


	public void setResolver_str(String resolver_str) {
		this.resolver_str = resolver_str;
	}


	public void setStatus_str(String status_str) {
		this.status_str = status_str;
	}


	public void setType_str(String type_str) {
		this.type_str = type_str;
	}

	

}
