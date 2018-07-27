
window.onload= function getAllReimbursment(){
	
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
	
	xhttp.open("GET", "http://localhost:9005/project1/html/getFinanceReimbursments.d", true); 
	xhttp.send()
	console.log(xhttp);
}

function insertRows(reimbs){
	
	let status = ['PENDING', 'APPROVED', 'DENIED'];
	let type = ['LODGING', 'FOOD', 'TRAVEL', 'OTHER'];
	
	let row = document.createElement("tr");
	document.getElementById("body").appendChild(row);
	row.className = "table-row row-" + reimbs.reimb_status_id;
	row.id="filter";
	
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
	if (reimbs.reimb_resolved == null) {
		resolved.innerHTML = "";
	}
	else {
		resolved.innerHTML = new Date(reimbs.reimb_resolved);
	}
	

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
	
	
	let moreI = document.createElement('td');
	let moreInfo = document.createElement('button');
	moreInfo.classList.add('btn');
	moreInfo.classList.add('btn-success');
	moreI.appendChild(moreInfo);
	row.appendChild(moreI);
	moreInfo.id="myModal";
	moreInfo.innerHTML = "More Info";
	moreInfo.onclick = function(){
		//moreInfo.modal('show');
		//$('#myModal').modal('show');
		alert("Really? All the information is already in the table....WHAT MORE COULD YOU ASK FOR?");

	}
	
	
	
	let approvee = document.createElement('td');
	let approve = document.createElement("button");
	approve.classList.add('btn');
	approve.classList.add('btn-primary');
	approvee.appendChild(approve);
	row.appendChild(approvee);
	approve.id = "app";
	approve.value= reimbs.reimb_ID;
	approve.innerHTML = "Approve";
	approve.onclick = function(){
		let xhttp = new XMLHttpRequest();
		let reimbID = reimbs.reimb_ID;
		console.log(reimbID);
		xhttp.onreadystatechange = function(){
			
			if(xhttp.readyState ==4 && xhttp.status==200){
				window.location.href="financemgrhome.html";
			}
			
			
		}
		
		xhttp.open("POST", "updateReimb.do", true); 
		xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhttp.send("reimbID="+reimbID);
		
	
	};
	let denyy = document.createElement('td');
	let deny = document.createElement("button");
	deny.classList.add('btn');
	deny.classList.add('btn-danger');
	denyy.appendChild(deny);
	row.appendChild(denyy);
	deny.id = "deny";
	deny.value= reimbs.reimb_ID;
	deny.innerHTML = "Deny";
	deny.onclick = function(){
		let xhttp = new XMLHttpRequest();
		let reimbID = reimbs.reimb_ID;
		console.log(reimbID);
		xhttp.onreadystatechange = function(){
			
			if(xhttp.readyState ==4 && xhttp.status==200){
				window.location.href="financemgrhome.html";
			}
			
			
		}
		
		xhttp.open("POST", "denyReimb.do", true); 
		xhttp.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhttp.send("reimbID="+reimbID);
		
	
	};
	

		let y = document.getElementById("filterpending");
		
		y.onclick=function showPending(){
		    
		    allRows = document.getElementsByClassName("table-row");
		    pendingRows = document.getElementsByClassName("row-0");
		    
		    for (let row of allRows)
		        row.style.display = "none";
		    
		    for (let row of pendingRows)
		        row.style.display = "table-row";
		    
		}
		
		let x = document.getElementById("filterapp");

		x.onclick=function showApproved(){
		    
		    allRows = document.getElementsByClassName("table-row");
		    approvedRows = document.getElementsByClassName("row-1");
		    
		    for (let row of allRows)
		        row.style.display = "none";
		    
		    for (let row of approvedRows)
		        row.style.display = "table-row";
		}
		
		let z = document.getElementById("filterdeny");
		
		z.onclick=function showDeclined(){
		    allRows = document.getElementsByClassName("table-row");
		    declinedRows = document.getElementsByClassName("row-2");
		    
		    for (let row of allRows)
		        row.style.display = "none";
		    
		    for (let row of declinedRows)
		        row.style.display = "table-row";
		}
		
		let b = document.getElementById("filterall")
		
		b.onclick=function showAll(){
		    allRows = document.getElementsByClassName("table-row");
		    for (let row of allRows)
		        row.style.display = "table-row";
		}
		
	}
	
	

	







