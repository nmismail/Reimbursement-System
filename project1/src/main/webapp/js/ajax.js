
window.onload= function getReimbursment(){
	
	let xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function(){
		
		if(xhttp.readyState ==4 && xhttp.status==200){
			
			let reimb = JSON.parse(xhttp.responseText);
			for(let x=0; x<reimb.length;x++)
				{
				insertRows(reimb[x]);
				}
		}
	}
	
	xhttp.open("GET", "http://localhost:9005/project1/html/getReimbursments.d", true); 
	xhttp.send()
	console.log(xhttp);
}



function insertRows(reimbs){
	
	let status = ['PENDING', 'APPROVED', 'DENIED'];
	let type = ['LODGING', 'FOOD', 'TRAVEL', 'OTHER'];
	
	let row = document.createElement("tr");
	document.getElementById("body").appendChild(row);
	
	let id = document.createElement("td");
	row.appendChild(id);
	id.innerHTML = reimbs.reimb_ID;
	
	let amount = document.createElement("td");
	row.appendChild(amount);
	amount.innerHTML = "$" + reimbs.reimb_amount;
	
	let submitted = document.createElement("td");
	row.appendChild(submitted);
	submitted.innerHTML =new Date(reimbs.reimb_submitted);
	
	let resolved = document.createElement("td");
	row.appendChild(resolved);
	resolved.innerHTML = new Date(reimbs.reimb_resolved);
	

	let description = document.createElement("td");
	row.appendChild(description);
	description.innerHTML = reimbs.reimb_description;
	
	let author = document.createElement("td");
	row.appendChild(author);
	author.innerHTML = reimbs.reimb_author;
	
	let resolver = document.createElement("td");
	row.appendChild(resolver);
	resolver.innerHTML = reimbs.reimb_resolver;
	
	let statusId = document.createElement("td");
	row.appendChild(statusId);
	statusId.innerHTML = status[reimbs.reimb_status_id];
	
	let typeId = document.createElement("td");
	row.appendChild(typeId);
	typeId.innerHTML = type[reimbs.reimb_type_id-1];
	
	
	
}