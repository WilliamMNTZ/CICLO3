//EVENTOS O FUNCIONALIDADES PARA TABLA GASTOS


//Funcion limpiar campos del formulario

function limpiar_formulario(){
	if (confirm("Esta seguro que desea limpiar el formulario?")){

		var campoTextoID = document.getElementById("codigo");
		var campoTextoBrand = document.getElementById("brand");
		var campoTextoRooms = document.getElementById("rooms");
		var campoTextoCategory_ID = document.getElementById("category_id");
		var campoTextoName = document.getElementById("name");
		var divResultado = document.getElementById("resultado");
		
		campoTextoID.value = "";
		campoTextoBrand.value = "";
		campoTextoRooms.value = "";
		campoTextoCategory_ID.value = "";
		campoTextoName.value = "";
		divResultado.innerHTML = ""
		
		//Otra forma de limpiar las cajas del html
		
		/*
		$("#codigo").val("");
		$("#name").val("");
		$("#fecha").val("");
		$("#valor").val("");
		$("#desc").val("");
		$("#user").val("");
		*/
	}
}


//Funcion (GET) consultar o traer toda la informacion o registro de la tabla gastos
function consultar_todo(){
    $.ajax({
        url:"https://g75e0482780f990-mybd.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/cabin/cabin",
        type:"GET",
        datatype:"json",
		
		error: function(xhr, status){
			alert('ha ocurrido un problema, intentalo nuevamente, ' + xhr.status);
		},
		
		complete: function(xhr, status){
			alert('Resultado de comprobacion -- cod. estado: ' + xhr.status);
		},	
		
        success:function(json){
            //console.log(respuesta);
            //crearRespuestaGastos(respuesta.items)
			
			$("#resultado").empty();
			tabla = "<center> <table border='1'> <tr> <th>ID:</th> <th>BRAND:</th> <th>ROOMS:</th> <th>CATEGORY_ID: </th> <th>NAME:</th> </tr>"
			filas = ""
			for (i=0; i<json.items.length; i++){
				filas += "<tr>";
				filas += "<td>" + json.items[i].id + "</td>";
				filas += "<td>" + json.items[i].brand + "</td>";
				filas += "<td>" + json.items[i].rooms + "</td>";
				filas += "<td>" + json.items[i].category_id + "</td>";
				filas += "<td>" + json.items[i].name + "</td>";
				filas += "<td> <button onclick='borrar_registro2("+json.items[i].id+")'>Borrar</button>";//se agrega el boton y este tiene la funcion borrar registro:
				filas += "</tr>";
			}
			filas += "</table>"
			$("#resultado").append(tabla + filas + "</center>")
			console.log(json)
			
			
        }

    });
}


//Otra forma de construir la anterior consultar o traer resultado de la tabla gastos es:
//Tiene que descomentar las lineas 20 y 21 de la funcion consultar o traer informacion
//Tambien eliminar todas las lineas de la 23 hasta la linea 41 y descomente esta funcion:

/* 
function crearRespuestaGastos(items){

    let myTable ="<table border='1'>";
    for(i=0;i<items.length;i++){
        myTable+="<tr>";
        myTable+="<td>"+items[i].id+"</td>";
        myTable+="<td>"+items[i].nombre+"</td>";
        myTable+="<td>"+items[i].fecha+"</td>";
        myTable+="<td>"+items[i].valor+"</td>";
        myTable+="<td>"+items[i].descripcion+"</td>";
		myTable+="<td>"+items[i].nombre_usuario+"</td>";
        myTable+="<td> <button onclick='borrarElementoRoom("+items[i].id+")'>Borrar</button>";
        myTable+="</tr>";
    }
    myTable+="</table>";
    $("#resultado").append(myTable);

}
*/


function validarCampo(campoTexto){
	if(campoTexto.val() != ""){
		return true;
	}
	else{
		return false;
	}
}



//Funcion (GET) para buscar o Consultar por ID

function consultaID(codigo){

	let data={
		id:codigo
    };
    let dataToSend=JSON.stringify(data);
	
	 	$.ajax({

			url:"https://g75e0482780f990-mybd.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/cabin/cabin/:id",
			type: 'GET',
			datatype:"JSON",
			data:dataToSend,
			contentType:"application/JSON",


			success: function(json){
				tabla = "<center><table border='1'>";
				filas = "";
				if (json.items.length > 0){

					$("#resultado").empty();
					filas += "<tr><th>ID:<td>" + json.items[0].id
					filas += "<tr><th>BRAND:<td>" + json.items[0].brand
					filas += "<tr><th>ROOMS:<td>" + json.items[0].rooms
					filas += "<tr><th>CATEGORY_ID:<td>" + json.items[0].category_id
					filas += "<tr><th>NAME<td>" + json.items[0].name
					$("#resultado").append(tabla + filas + "</center>")

				}
				
			},

			error: function(xhr, status){
				alert('ha ocurrido un problema, Error: ' + xhr.status);
			},
			
			complete: function(xhr, status){
				alert('La peticion ha sido realizada,' + xhr.status);
				
			}		

		});
	
}





//Funcion (POST) Registrar o Guardar toda la informacion en la tabla Gastos

function guardarInformacion(){
	
	if(!validarCampo($("#name"))){
		alert("Debe ingresar un nombre");
		return;
	}
	
	if(!validarCampo($("#brand"))){
		alert("Debe ingresar un tipo");
		return;
	}	

	if(!validarCampo($("#rooms"))){
		alert("Debe ingresar un número de habitación");
		return;
	}

	if(!validarCampo($("#category_id"))){
		alert("Debe ingresar un id categorico");
		return;
	}

	let codigo=$("#codigo").val();
	let brand=$("#brand").val();
	let rooms=$("#rooms").val();
	let category_id=$("#category_id").val();
	let name=$("#name").val();

	let data={
		id:codigo,
		brand:brand,
		rooms:rooms,
		category_id:category_id,
		name:name
	};

	let dataToSend=JSON.stringify(data);
	
	$.ajax({    
		url : 'https://g75e0482780f990-mybd.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/cabin/cabin',
		type : 'POST',
	 	dataType : 'json',
		data:dataToSend,
		contentType:'application/json',
		success : function(resultado) {
			$("#codigo").val("");
			$("#brand").val("");
			$("#rooms").val("");
			$("#category_id").val("");
			$("#name").val("");
		},
		error : function(xhr, status) {
	        //alert('ha sucedido un problema');
		},
		complete: function(){
			limpiar_formulario();
			consultar_todo();
			
		}
    });
}








//Funcion (PUT) Editar o Actualizar registro de la tabla Gastos
function editar_Informacion(){

	let codigo=$("#codigo").val();
	let brand=$("#brand").val();
	let rooms=$("#rooms").val();
	let category_id=$("#category_id").val();
	let name=$("#name").val();

    let data={
		id:codigo,
		brand:brand,
		rooms:rooms,
		category_id:category_id,
		name:name
    };
    console.log(data);
    let dataToSend = JSON.stringify(data);
	
	if (confirm("Está seguro de actualizar el registro:  " + $("#codigo").val() + "  ??")){
		
		$.ajax({
			url:"https://g75e0482780f990-mybd.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/cabin/cabin",
			type:"PUT",
			data:dataToSend,
			contentType:"application/JSON",
			datatype:"JSON",
			success:function(respuesta){

				$("#codigo").val(""),
				$("#brand").val(""),
				$("#rooms").val(""),			
				$("#category_id").val(""),
				$("#name").val(""),
				
				consultar_todo();
				alert("se ha realizado la Actualicion del registro correctamente")
			}
		});
	}
}






//Funcion (DELETE) Borrar o Eliminar registro de la tabla Gastos
function borrar_registro(){

	let codigo=$("#codigo").val();

    let data={
		id:codigo
    };
    let dataToSend=JSON.stringify(data);
	
	
	if (confirm("Está seguro de eliminar el registro:  " + codigo + "??")){
	
		$.ajax({
			url: 'https://g75e0482780f990-mybd.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/cabin/cabin',
			type: 'DELETE',
			datatype:"JSON",
			data:dataToSend,
			contentType:"application/JSON",

			success:function(respuesta){

				$("#codigo").val(""),
				$("#brand").val(""),
				$("#rooms").val(""),			
				$("#category_id").val(""),
				$("#name").val(""),

				alert("El registro se ha Eliminado correctamente.")	
			},
			error : function(xhr, status) {
				//     alert('ha sucedido un problema');
			},
			complete: function(){
				limpiar_formulario();
				consultar_todo();
			}
		});
	}
}


function borrar_registro2(codigo){

    let data={
		id:codigo
    };
    let dataToSend=JSON.stringify(data);
	
	
	if (confirm("Está seguro de eliminar el registro:  " + codigo + "??")){
	
		$.ajax({
			url: 'https://g75e0482780f990-mybd.adb.us-phoenix-1.oraclecloudapps.com/ords/admin/cabin/cabin',
			type: 'DELETE',
			datatype:"JSON",
			data:dataToSend,
			contentType:"application/JSON",

			success:function(respuesta){

				$("#codigo").val(""),
				$("#brand").val(""),
				$("#rooms").val(""),			
				$("#category_id").val(""),
				$("#name").val(""),

				alert("El registro se ha Eliminado correctamente.")	
			},
			error : function(xhr, status) {
				//     alert('ha sucedido un problema');
			},
			complete: function(){
				limpiar_formulario();
				consultar_todo();
			}
		});
	}
}