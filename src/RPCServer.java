import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

public class RPCServer {

    private RPCServer() {
        try {
            WebServer ws = new WebServer(8185); 
            XmlRpcServer servidor = ws.getXmlRpcServer();

            PropertyHandlerMapping phm = new PropertyHandlerMapping();
            phm.addHandler("Calculator", Calculator.class);

            servidor.setHandlerMapping(phm);
		
            ws.start(); 
			System.out.println("The server is up!");
		
        } catch (Exception exception) {
            System.err.println("Error: " + exception);
        }
    }

    public static void main(String[] args) {
		new RPCServer();
    }
}
