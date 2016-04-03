<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="cache-control" content="no-cache" />
         <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
        <title>TRUCO ONLINE</title>
    </head>
    <body>
       
       
        <% 
        
        String exito = (String) session.getAttribute("apodo");
        if(exito!=null){%>
        	
         
        
      <h1>BIENVENIDO <%=session.getAttribute("apodo") %> !!!!!</h1>
      
      
       <%}else{%>
        
        
       <h1>NO HEMOS PODIDO ENCONTRAR TU APODO (COOKIE), NO PODRAS USAR LAS FUNCIONES TEMPORALMENTE
       RECUERDA PERMITIR LAS COOKIES EN EL NAVEGADOR </h1>
       <% }%>
      
      
      <div>
            <input type="text" id="messageinput"/>
        </div>
        <div>
            <button type="button" onclick="openSocket();" >Open</button>
            <button type="button" onclick="send();" >Mostrar Usuarios Conectados</button>
            <button type="button" onclick="closeSocket();" >Close</button>
            
        </div>
        <!-- Server responses get written here -->
        <div id="messages"></div>
       
        <!-- Script to utilise the WebSocket -->
        <script type="text/javascript">
                       
            var webSocket;
            var messages = document.getElementById("messages");
            var apodo = <%=session.getAttribute("apodo")%>;
           
            function openSocket(){
                // Ensures only one connection is open at a time
                if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                   writeResponse("WebSocket is already opened.");
                    return;
                }
                // Create a new instance of the websocket
                webSocket = new WebSocket("ws://localhost:8080/CLIENTE_RMI/connect");
                 
                /**
                 * Binds functions to the listeners for the websocket.
                 */
                webSocket.onopen = function(event){
                    // For reasons I can't determine, onopen gets called twice
                    // and the first time event.data is undefined.
                    // Leave a comment if you know the answer.
                    if(event.data === undefined)
                        return;
 
                    writeResponse(event.data);
                    sendUsr();
                    //Generate();
                    
                };
 
                webSocket.onmessage = function(event){
                    writeResponse(event.data);
                    //Generate();
                   
                   var response = JSON.parse(event.data);
                   
                   if (response.action === "usuariosConectados") {
                	   
                	   Generate(response);
                        }
                    
            };
 
                webSocket.onclose = function(event){
                    writeResponse("Connection closed");
                };
            
            }
            /**
             * Sends the value of the text input to the server
             */
             function sendUsr(){
            	 document.getElementById("messages").innerHTML = "apodo send: "+apodo;
                 webSocket.send(apodo);
             }
             
             
             function send(){
                //var text = document.getElementById("messageinput").value;
                //webSocket.send(text);
                
                var m = '${apodo}';
               
                
                //writeResponse(m);
               webSocket.send(m);
            }
           
            function closeSocket(){
                webSocket.close();
            }
 
            function writeResponse(text){
                messages.innerHTML += "<br/>" + text;
            }
           
            function Generate(response){
            	
            	//var json = [{"User_Name":"John Doe","score":"10","team":"1"},{"User_Name":"Jane Smith","score":"15","team":"2"},{"User_Name":"Chuck Berry","score":"12","team":"2"}];
                     var tr;
                
                	tr = $('<tr/>');
                    tr.append("<td>" + "n" + "</td>");
                    tr.append("<td>" + response.apodo + "</td>");
                    tr.append("<td>" + response.estado + "</td>");
                    
                    $('table').append(tr);
                
            }
               
        </script>
   
  
  
      <table>
    
   <tr>
       <th>ID</th>
       <th>APODO</th>
       <th>ESTADO</th>
   </tr>
 
   </table>
      
      
      

      
      
      
      
      <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="js/jput.min.js"></script>
      
    </body>
</html>
