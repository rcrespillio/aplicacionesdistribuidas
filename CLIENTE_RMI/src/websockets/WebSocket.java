package websockets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.simple.JSONObject;

import repo.LogeadoDTO;
import utils.Json;
import controlador.Controlador;
    
@ServerEndpoint("/connect")
public class WebSocket {

	
	/**tener en cuenta que cada vez que se conecta un usuario (open session), esta clase crea un thread aparte,
	 * asi que entre sessiones manejan diferentes variables, el arraylist compartido de sessiones esta en SessionHandler.
	 * **/
	 
	
	boolean ya_iniciado = false;
	
	
    @OnOpen
        public void open(Session session) {
   
    	 System.out.println(session.getId() + " has opened a connection"); 
         try {
             
        	
        	 session.getBasicRemote().sendText("Connection Established");
        	 SessionHandler.getInstance().addSession(session);
        	 
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }

    @OnClose
        public void close(Session session) {
    	 System.out.println("Session " +session.getId()+" has ended");
    	 SessionHandler.getInstance().removeSession(session);
    }

    @OnError
        public void onError(Throwable error) {
    }

    @OnMessage
        public void handleMessage(String message, Session session) throws IOException {
    	 System.out.println("Message from " + session.getId() + ": " + message);
         
    	if(session!=null){
    		boolean resultado = Controlador.getControlador().vincularSessionUsuario(session.getId(),message);
    	    if(resultado){
    	    	//vinculo session con usuario, REFRESCO LA TABLA DE SESSIONES DE USUARIOS CONECTADOS!
    	    	
    	    	System.out.println("websocket/handlemessage: resultado true, vincule session");
    	    	 try {
    	    		Set<Session> sessions = SessionHandler.getInstance().getSessions();
    	    		 for(Session s: sessions){
    	    		LogeadoDTO logeado = Controlador.getControlador().obtenerUsuarioLogeado(s.getId());
    	    		if(logeado!=null){
    	    		
    	    		JSONObject json = Json.crearJSON(logeado);
    	    		if(json!=null){
    	    		System.out.println("json parceado:"+json.toJSONString());
    	    		 
    	    		for(Session ss: sessions){ 
    	                
    	    			System.out.println("ssgetid: "+ss.getId()+"this session id: "+session.getId()+" ya iniciado: "+ya_iniciado);
    	    			if((ss.getId()==session.getId()) && (this.ya_iniciado==true)){
    	    				System.out.println("salgo del bucle");
    	    				continue;
    	                	
    	                }
    	    			ss.getBasicRemote().sendText(json.toJSONString());
    	    			System.out.println("send message json correctly to: "+session.getId());
    	    		}
    	               
    	               
    	        
    	    		}else{//json null
       	    		 System.out.println("json null o mal parseado");
       	    	     }
    	    		
    	    		
    	    		}else{
    	    			System.out.println("websocket/handlemessage: no encontre el usuario logeado en el servidor");	
    	    		}
    	    		 
    	    		 
    	    		 }//fin for
    	    		 this.ya_iniciado = true; 
    	    		} catch (Exception ex) {
    	             ex.printStackTrace();
    	         }
    	    	 
    	    }else{
    	    	System.out.println("websocket: error vinculando sesion con usr, cierro session");
    	    	SessionHandler.getInstance().removeSession(session);
    	    }
    	}
    	
    	
    	}
}    