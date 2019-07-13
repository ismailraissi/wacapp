let ingevoerd_getal = "";
let operator;
let display_waarde;
let getal1 = "0";

let display = document.getElementById("display");
document.getElementById("btn_1").addEventListener("click", function(){add_getal(1)});
document.getElementById("btn_2").addEventListener("click", function(){add_getal(2)});
document.getElementById("btn_3").addEventListener("click", function(){add_getal(3)});
document.getElementById("btn_4").addEventListener("click", function(){add_getal(4)});
document.getElementById("btn_5").addEventListener("click", function(){add_getal(5)});
document.getElementById("btn_6").addEventListener("click", function(){add_getal(6)});
document.getElementById("btn_7").addEventListener("click", function(){add_getal(7)});
document.getElementById("btn_8").addEventListener("click", function(){add_getal(8)});
document.getElementById("btn_9").addEventListener("click", function(){add_getal(9)});
document.getElementById("btn_0").addEventListener("click", function(){add_getal(0)});
document.getElementById("btn_prod").addEventListener("click", function(){add_operator("*")});
document.getElementById("btn_plus").addEventListener("click", function(){add_operator("+")});
document.getElementById("btn_eq").addEventListener("click", function(){eq(ingevoerd_getal)});
document.getElementById("btn_min").addEventListener("click", function(){add_operator("-")});
document.getElementById("btn_clear").addEventListener("click", function(){clear()});
document.getElementById("btn_div").addEventListener("click", function(){add_operator("/")});

function add_getal(x) {
	ingevoerd_getal += x.toString();
	display.innerHTML = ingevoerd_getal;
}

function add_operator(x){
	if(x == "+"){
		plus(ingevoerd_getal);
	} else if (x == "-"){
		min(ingevoerd_getal);
	} else if (x == "/"){
		div(ingevoerd_getal);
	} else if (x == "*"){
		keer(ingevoerd_getal);
	}
	ingevoerd_getal = "";
}

function plus(data){
	operator = "+";
	if (getal1 == "0"){
		getal1 = data;
		display.innerHTML = "+";
	} else {
		ingevoerd_getal = parseInt(getal1, 10) + parseInt(data, 10);
		getal1 = ingevoerd_getal;
		display.innerHTML = ingevoerd_getal;
	}
}

function min(data){
	operator = "-";
	if (getal1 == "0"){
		getal1 = data;
		display.innerHTML = "-";
	} else {
		ingevoerd_getal = parseInt(getal1, 10) - parseInt(data, 10);
		getal1 = ingevoerd_getal;
		display.innerHTML = ingevoerd_getal;
	}
}

function div(data){
	operator = "/";
	if (getal1 == "0"){
		getal1 = data;
		display.innerHTML = "/";
	} else {
		ingevoerd_getal = parseInt(getal1, 10) / parseInt(data, 10);
		getal1 = ingevoerd_getal;
		display.innerHTML = ingevoerd_getal;
	}
}

function keer(data){
	operator = "*";
	if (getal1 == "0"){
		getal1 = data;
		display.innerHTML = "*";
	} else {
		ingevoerd_getal = parseInt(getal1, 10) * parseInt(data, 10);
		getal1 = ingevoerd_getal;
		display.innerHTML = ingevoerd_getal;
	}
}


function eq(data){
	if (operator == "+"){
		ingevoerd_getal = parseInt(getal1, 10) + parseInt(data, 10);
		getal1 = "0";
		display.innerHTML = ingevoerd_getal;
		operator == "";
	} else if (operator == "-"){
		ingevoerd_getal = parseInt(getal1, 10) - parseInt(data, 10);
		getal1 = "0";
		display.innerHTML = ingevoerd_getal;
		operator == "";
	} else if (operator == "/"){
		ingevoerd_getal = parseInt(getal1, 10) / parseInt(data, 10);
		getal1 = "0";
		display.innerHTML = ingevoerd_getal;
		operator == "";
	} else if (operator == "*"){
		ingevoerd_getal = parseInt(getal1, 10) * parseInt(data, 10);
		getal1 = "0";
		display.innerHTML = ingevoerd_getal;
		operator == "";
	}
}

function clear(){
	operator = "";
	ingevoerd_getal = "";
	getal1 = "0";
	display.innerHTML = "0";
}



