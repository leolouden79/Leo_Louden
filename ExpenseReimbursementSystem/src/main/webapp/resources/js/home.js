

window.onload = function(){ 
	
	getUser();
	document.getElementById("contact-tab").addEventListener("click", logout);
	
	//Stop form from refreshing page on submit
	$("#my-form").submit(function(e) {
	    e.preventDefault(); 
	});


}

//Create reimbursement ticket request
function createReimbursement(){
	
	type = document.getElementById("exampleFormControlSelect2").value;
	amount = document.getElementById("cash").value;
	descrip = document.getElementById("exampleFormControlTextarea1").value;
	
	console.log(type);
	console.log(amount);
	console.log(descrip);
	
	var typeId;
	
	switch(type) {
	
	  case "Rental Car":
		  typeId = "1";
	    break;
	    
	  case "Fuel Delivery":
		  typeId = "2";
	    break;
	    
	  case "Locksmith":
		  typeId = "3";
		    break;
		    
	  case "Car Tow":
		  typeId = "4";
		    break;
	} 
	
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			
			console.log(JSON.parse(xhttp.responseText));
			
			window.location.reload(true);
			
		}

	}
	

	var params = ('typeId=' + typeId + '&' + 'amount=' + amount + '&' + 'descrip=' + descrip);
		
	xhttp.open("POST", "http://localhost:9005/ExpenseReimbursementSystem/newre");
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	console.log(params);
	xhttp.send(params);

	
	
}

//End user session
function logout() {
	
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange= function(){
			
		if(xhttp.readyState==4 && xhttp.status==200){
					
			window.location.replace("http://localhost:9005/ExpenseReimbursementSystem/");
		}
	}
	
	
	xhttp.open("POST", 'http://localhost:9005/ExpenseReimbursementSystem/logout');
	xhttp.send();
	
	
}



//Retrieve user object and corresponding list of reimbursements
function getUser() {

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			let user = JSON.parse(xhttp.responseText);

			addToTable(user);

		}

	}

	xhttp.open("POST", "http://localhost:9005/ExpenseReimbursementSystem/json");

	xhttp.send();
	

}

//Populate table with reimbursements
function addToTable(user){
	
	let reimbursements = user.reimbursements;
	
	var mytable = document.getElementById("my-table");
	
	for (const element of reimbursements) {
		
	
	var tr = document.createElement("tr");
	tr.setAttribute("data-status", "button");
	
	var th = document.createElement("th");
	th.innerHTML = element.author_str;
	
	var td1 = document.createElement("td");
	td1.innerHTML = "$" + element.amount;
	
	var td2 = document.createElement("td");
	td2.innerHTML = element.submitted;
	
	var td3 = document.createElement("td");
	td3.innerHTML = element.resolved;
	
	var td4 = document.createElement("td");
	td4.innerHTML = element.description;
	
	var td5 = document.createElement("td");
	td5.innerHTML = element.resolver_str;
	
	var td6 = document.createElement("td");
	td6.innerHTML = element.type_str;
	
	var td7 = document.createElement("td");
	td7.innerHTML = element.status_str;
	

	//add approve and deny buttons for Policy Manager only
	if (user.role === "Policy Manager" && element.status_str === "Pending"){
		
		var b1 = document.createElement("button");
		b1.setAttribute("type", "button");
		b1.setAttribute("class", "btn btn-success");
		b1.innerHTML = "Approve";
		b1.setAttribute("data-id", element.id);
		b1.setAttribute("data-status", 1);
		b1.setAttribute("onClick", "UpdateReimbursement(this)");
		
		var b2 = document.createElement("button");
		b2.setAttribute("type", "button");
		b2.setAttribute("class", "btn btn-danger");
		b2.innerHTML = "Deny";
		b2.setAttribute("data-id", element.id);
		b2.setAttribute("data-status", "2");
		b2.setAttribute("onClick", "UpdateReimbursement(this)");
		
		var br1 = document.createElement("br");
		var br2 = document.createElement("br");
		
		td7.appendChild(br1);
		td7.appendChild(b1);
		td7.appendChild(br2);
		td7.appendChild(b2);	
	
	}
	
	tr.appendChild(th);
	tr.appendChild(td1);
	tr.appendChild(td2);
	tr.appendChild(td3);
	tr.appendChild(td4);
	tr.appendChild(td5);
	tr.appendChild(td6);
	tr.appendChild(td7);
	
	mytable.appendChild(tr);
	
	
	}
	
	//add this even listener after users have been added
	document.getElementById("home-tab").addEventListener("click", showTable); 
	
	if (user.role  === "Insured") {
		document.getElementById("profile-tab").addEventListener("click", showForm);
		document.getElementById("btn-butt").addEventListener("click", createReimbursement);
	}
	

}


//Approve or deny reimbursement
function UpdateReimbursement(button){
	
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			let resp = JSON.parse(xhttp.responseText);
			
			if(resp == "Success"){
				window.location.reload(true);
				
			}
			

		}

	}
	
	
	var params = ('reimbId=' + button.getAttribute('data-id') + '&' + 'reimbStatus=' + button.getAttribute('data-status'));
		
	xhttp.open("POST", "http://localhost:9005/ExpenseReimbursementSystem/update");
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send(params);

	
	
}


//Show table of reimbursements when Home button is clicked
function showTable(){
	
	var form_container = document.getElementById("form_container");
	form_container.style.display = "none";
	
	var table = document.getElementById("resptable");
	table.style.display = "";
	
}


//Show reimbursement form when "Create Reimbursement" tab is clicked
function showForm(){
	
	var table = document.getElementById("resptable");
	table.style.display = "none";
	
	var form_container = document.getElementById("form_container");
	form_container.style.display = "block";
	
	
}