package websockets;



import java.util.HashSet;
import java.util.Set;
import javax.websocket.Session;


public  class SessionHandler {
    
	private final static Set<Session> sessions = new HashSet<>();
	private static SessionHandler session;
	private SessionHandler(){};
	
	public static SessionHandler getInstance(){
		
		if(session!=null){
			return session;
		}else{
			session = new SessionHandler();
			return session;
		}
		
	}
	
	
   public Set<Session> getSessions(){
	   
	   return sessions;
   }
	
	public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
}