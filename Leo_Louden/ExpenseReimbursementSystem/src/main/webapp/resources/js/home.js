/**
 * 
 */


window.onload = function(){ 
	console.log("jquery works");
	getUser();
	document.getElementById("allreimb").addEventListener("click", addToTable);
	document.getElementById("profile-tab").addEventListener("click", showForm);
	document.getElementById("btn-butt").addEventListener("click", createReimbursement);

	
	


}


function createReimbursement(){
	type = document.getElementById("exampleFormControlSelect2").value;
	amount = document.getElementById("cash").value;
	descrip = document.getElementById("exampleFormControlTextarea1").value;
	
	
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			
			var data = new FormData();
			data.append('type', type);
			data.append('amount', amount);
			data.append('descrip', descrip);
				
			xhttp.open("POST", "http://localhost:9005/ExpenseReimbursementSystem/newre");

			xhttp.send(data);

		}

	}

	
	
	
	
	
	
	
	
	
}









function getUser() {

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			let user = JSON.parse(xhttp.responseText);

			//console.log(user);
			addToTable(user);

		}

	}

	xhttp.open("POST", "http://localhost:9005/ExpenseReimbursementSystem/jason");

	xhttp.send();

}


function addToTable(user){
	
	let reimbursements = user.reimbursements;
	
	var mytable = document.getElementById("my-table");
	
	for (const element of reimbursements) {
		  console.log(element);
	
	var tr = document.createElement("tr");
	
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
	

}













function showTable(){
	console.log("IN SHOW TABLE");
	
	var blah = document.getElementById("blah");

	blah.style.display = "none";
	
	var table = document.getElementById("resptable");
	
	table.style.display = "";
	
}



function showForm(){
	console.log("IN SHOW FORM");
	
	var table = document.getElementById("resptable");
	table.style.display = "none";
	
	var blah = document.getElementById("blah");

	blah.style.display = "block";
	
	
}







function getSW(){
	//console.log("button clicked!");
	
	//we're going to be using the XMLHttpRequest object (aka xhttp)
	
	//get the value from the text field
	let swId = document.getElementById('swId').value;
	//console.log(swId);
	
	//STEP 1: create the XMLHttpRequest object
	//this object allows us to make requests and get back data.
	//in short, this is our data retriever object (it calls APIs)
	let xhttp = new XMLHttpRequest();
	
	//STEP 2: create the callback function for readystate changes
	/*
	 * The XMLHttpRequest object has serveral states we need to know about
	 * 
	 * READY STATE
	 * 		State 0:	not initialized
	 * 		State 1:	server connection established
	 * 		State 2:	request received
	 * 		State 3:	processing request
	 * 		State 4:	complete, request finished and response is ready
	 */
	xhttp.onreadystatechange= function(){
		//console.log("ready state has changed!!!!");
		
		if(xhttp.readyState==4 && xhttp.status==200){z
			//console.log("state=4 and status is successful");
			
			let sw = JSON.parse(xhttp.responseText);
			//later, we can turn JS objects into JSONs using the
			// "stringify" function
			console.log(sw);
			ourDOMManipulation(sw);
		}
	}
	
	//STEP 3: create and open a connection
	//xhttp.open(http method, url)
	//OR xhttp.open(http method, url, boolean async?)
	xhttp.open("GET", 'https://swapi.co/api/people/'+swId);
	
	//STEP 4: send the request
	xhttp.send();
	
}


function ourDOMManipulation(ourJSON){
	document.getElementById('swName').innerText=ourJSON.name;
	document.getElementById('swBirthYear').innerText=ourJSON.birth_year;
}

























