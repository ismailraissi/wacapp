//00fb0158113e4b223c8064f484b43ac2

const el = node => document.querySelector(node)

function initPage(){
	fetch('https://ipapi.co/json')
	.then(response => response.json())
	.then(function(myJson) {
		let data = myJson;
		window.sessionStorage.setItem("dataMijnLocatie", JSON.stringify(data));
		document.getElementById("stad").append(myJson.city);
		document.getElementById("mijnLocatie").append(myJson.city);
		document.getElementById("regio").append(myJson.region);
		document.getElementById("land").append(myJson.country_name);
		document.getElementById("continent").append(myJson.continent_code);
		document.getElementById("postcode").append(myJson.postal);
		document.getElementById("latitude").append(myJson.latitude);
		document.getElementById("longitude").append(myJson.longitude);
		document.getElementById("tijdzone").append(myJson.timezone);
		document.getElementById("currency").append(myJson.currency);
		document.getElementById("stad").addEventListener("click", function(event){showWeather(myJson.city)})
		showWeather(myJson.latitude, myJson.longitude, myJson.city);
	})
}	

function toevoegen(){
	  var modalToevoegen = document.getElementById("myModalToevoegen");
	  modalToevoegen.style.display = "block";
}

function showWeather(latitude, longitude, city){
	let item = window.sessionStorage.getItem("weer_" + city);
	let timeset = window.sessionStorage.getItem("timeset_" + city);
	if (item != null) {
		item = JSON.parse(item);	
	}
	if (item == null || new Date() > new Date(timeset)) {
		fetch(`https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=00fb0158113e4b223c8064f484b43ac2`)
		.then(response => response.json())
		.then(function(weerJson) {
			let data = weerJson;
			 document.getElementById("weerin").innerHTML = "Het weer in " + city;
			 document.getElementById("temperatuur").innerHTML = "Temperatuur: " + Math.round(weerJson.main.temp - 273) + " Celcius";
			 document.getElementById("luchtvochtigheid").innerHTML = "Luchtvochtigheid: " + weerJson.main.humidity;
			 document.getElementById("windsnelheid").innerHTML = "windsnelheid: " + weerJson.wind.speed;
			 let windrichting = "";
			 let deg = weerJson.wind.deg;
			 if(deg >= 316 && deg <= 45){
				 windrichting = "Noord";
			 } if(deg >= 46 && deg <= 135){
				 windrichting = "Oost";
			 } if(deg >= 136 && deg <= 225){
				 windrichting = "Zuid";
			 } if(deg >= 226 && deg <= 315) {
				 windrichting = "West"
			 }
			 document.getElementById("windrichting").innerHTML = "Windrichting: " + windrichting;
			 let timeStamp = weerJson.sys.sunrise;
			 let date = new Date(timeStamp*1000);
			 let hours = date.getHours();
			 let minutes = "0" + date.getMinutes();
			 let seconds = "0" + date.getSeconds();
			 let formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
			 document.getElementById("zonsopgang").innerHTML = "Zonsopgang: " + formattedTime;
			 timeStamp = weerJson.sys.sunset;
			 date = new Date(timeStamp*1000);
			 hours = date.getHours();
			 minutes = "0" + date.getMinutes();
			 seconds = "0" + date.getSeconds();
			 formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
			 document.getElementById("zonsondergang").innerHTML = "Zonsondergang: " + formattedTime;
			 window.sessionStorage.setItem("weer_" + city, JSON.stringify(data));
			 let datum = new Date();
			 window.sessionStorage.setItem("timeset_" + city, datum.getTime() + 10*60000);
		})
	} else {
		 document.getElementById("weerin").innerHTML = "Het weer in " + city;
		 document.getElementById("temperatuur").innerHTML = "Temperatuur: " + Math.round(item.main.temp - 273) + " Celcius";
		 document.getElementById("luchtvochtigheid").innerHTML = "Luchtvochtigheid: " + item.main.humidity;
		 document.getElementById("windsnelheid").innerHTML = "windsnelheid: " + item.wind.speed;
		 let windrichting = "";
		 let deg = item.wind.deg;
		 if(deg >= 316 && deg <= 45){
			 windrichting = "Noord";
		 } if(deg >= 46 && deg <= 135){
			 windrichting = "Oost";
		 } if(deg >= 136 && deg <= 225){
			 windrichting = "Zuid";
		 } if(deg >= 226 && deg <= 315) {
			 windrichting = "West"
		 }
		 document.getElementById("windrichting").innerHTML = "Windrichting: " + windrichting;
		 let timeStamp = item.sys.sunrise;
		 let date = new Date(timeStamp*1000);
		 let hours = date.getHours();
		 let minutes = "0" + date.getMinutes();
		 let seconds = "0" + date.getSeconds();
		 let formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
		 document.getElementById("zonsopgang").innerHTML = "Zonsopgang: " + formattedTime;
		 timeStamp = item.sys.sunset;
		 date = new Date(timeStamp*1000);
		 hours = date.getHours();
		 minutes = "0" + date.getMinutes();
		 seconds = "0" + date.getSeconds();
		 formattedTime = hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
		 document.getElementById("zonsondergang").innerHTML = "Zonsondergang: " + formattedTime;
	}

}
function getCountryData() {
	  fetch('restservices/countries')
	    .then(res => res.json())
	    .then(renderCountryDataToDom)
	}

	function renderCountryDataToDom(data) {
	  const countryContainer = el('#country-list table')

	  data
	  	.sort()
	    .forEach(country => {
	      const { code, name, capital, surface, government, region, population, lat, lng} = country
	      countryContainer.innerHTML += `
	        <tr>
	          <td class="land" data-lat="${lat}" data-lon="${lng}" data-city="${capital}">${name}</td>
	          <td>${capital}</td>
	          <td>${region}</td>
	          <td>${surface}</td>
	          <td>${population}</td>
	          <td><button class="wijzigbtn" data-code="${code}" data-land="${name}" data-city="${capital}" data-region="${region}" data-surface="${surface}" data-popu="${population}">wijzig</button></td>
	          <td><button class="deletebtn" data-code="${code}">delete</button></td>	
	        </tr>
	      `
	    })
	    
	    document.querySelectorAll('.land')
	    	.forEach(btn => {
	    		btn.addEventListener('click', e => {
	    			e.stopPropagation();
	    			const { lat, lon, city } = e.target.dataset
	    			showWeather(lat, lon, city)
	    		})
	    	})
	    	
	    document.querySelectorAll(".deletebtn")
	    	.forEach(btn => {
	    		btn.addEventListener('click', e => {
	    			e.stopPropagation();
	    			let code = e.target.dataset.code
	    			console.log(code);
	    			let fetchoptions = {
	    					method: 'DELETE',
	    					headers: {
	    						'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
	    					}
	    				}
	    			fetch('restservices/countries/'+code, fetchoptions)
	    				.then(function(response) {
	    					if(response.ok) {
	    						console.log("verwijderd") 
	    						countryContainer.innerHTML = `       
	    						<tr class="tr-heading">
						          <th>Land</th>
						          <th>Hoofdstad</th>
						          <th>Regio</th>
						          <th>Oppervlakte</th>
						          <th>Inwoners</th>
						        </tr>`;
	    						getCountryData();
	    					}
	    					else if(response.status == 404) console.log("not found")
	    					else console.log("errorr")
	    				})
	    		})
	    	})
	    	
	    document.querySelectorAll('.wijzigbtn')
	    	.forEach(btn => {
	    		btn.addEventListener('click', e => {
	    			e.stopPropagation();
	    			let code = e.target.dataset.code
	    			console.log("wijzig: " + code);	    			
	    			  var modal = document.getElementById("myModal");
	    			  let closeBtn = document.getElementById("close");
	    				  document.getElementById("land_in_id").value = e.target.dataset.land;
	    				  document.getElementById("hoofdstad_in_id").value = e.target.dataset.city;
	    				  document.getElementById("regio_in_id").value = e.target.dataset.region;
	    				  document.getElementById("oppervlakte_in_id").value = e.target.dataset.surface;
	    				  document.getElementById("inwoners_in_id").value = e.target.dataset.popu;
	    				  modal.style.display = "block"
	    			  closeBtn.addEventListener('click', function(){modal.style.display = "none"})
	    			  window.addEventListener('click', function(event){
	    				  if(event.target == modal)
	    				  	{modal.style.display="none"}
	    			  	  })
	    			  let saveBtn = document.getElementById("submit");
	    			  saveBtn.addEventListener('click', function(){
	    				  var formData = new FormData(document.querySelector('#formId'));
	    				  var encData = new URLSearchParams(formData.entries());
	    					let fetchoptions = {
	    							method: 'PUT',
	    							body: encData,
	    							headers: {
	    								'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
	    							}
	    						}
	    				  fetch("restservices/countries/"+code, fetchoptions)
	    				  	.then(function(response){
	    				  		if(response.ok){
	    				  			console.log('saved')
	    				  		} 
		    					else if(response.status == 404) console.log("not found")
		    					else console.log("errorr")
	    				  	})
	    			  })
	    		})
	    	})
	    	




	}

	window.addEventListener('load', function() {
		  initPage()
		  getCountryData()
		})
	let loginbtn = document.getElementById('loginButton');
	loginbtn.addEventListener('click', function(){
		console.log("open");
		let modalInloggen = document.getElementById('myModalInloggen');
		modalInloggen.style.display="block";
	})
	let modalToevoegen = document.getElementById('myModalToevoegen')
	let toevoegBtn = document.getElementById('toevoegen');
	toevoegBtn.addEventListener('click', function(){toevoegen();})
	let closeToevoegen = document.getElementById('closeToevoegen');
	closeToevoegen.addEventListener('click', function(){modalToevoegen.style.display = "none"})
	window.addEventListener('click', function(event){
	if(event.target == modalToevoegen)
	  	{modalToevoegen.style.display="none"}
	})
	let saveBtn = document.getElementById('submitToevoegen');
	saveBtn.addEventListener('click', function(){
		let formData = new FormData(document.getElementById('formIdToevoegen'));
		let encData = new URLSearchParams(formData.entries());
		let fetchoptions = {
				method: 'POST',
				body: encData,
				headers: {
					'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
				}
			}
		fetch('restservices/countries/toevoegen', fetchoptions)
	    .then(function(response){
	    	if(response.ok){
	    		console.log('saved');
	    		modalToevoegen.style.display="none";
	    	} 
			else if(response.status == 404) console.log("not found")
			else console.log("errorr")
	   	})
	})
	let inlogSubmit = document.getElementById('submitLogin');
	inlogSubmit.addEventListener('click', function(){
		let formData = new FormData(document.getElementById('formIdInloggen'));
		let encData = new URLSearchParams(formData.entries());
		fetch('restservices/authentication', {method: 'POST', body: encData})
		.then(function(response){
			if(response.ok){
				let modalInloggen = document.getElementById('myModalInloggen');
				modalInloggen.style.display="none";
				return response.json()}
			else throw "Wrong username/password";
		})
		.then(myJson => window.sessionStorage.setItem("sessionToken", myJson.JWT))
		.catch(error => console.log(error));
	})

