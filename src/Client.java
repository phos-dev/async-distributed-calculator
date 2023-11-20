import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Client {
    private Connection connection;
    private Channel channel;
    private static final String QUEUE_NAME = "calculator_queue";
    
    public Client() {
        try {
        	ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("rabbitmq");
            factory.setUsername("user");  
            factory.setPassword("password"); 
            
           
            connection = factory.newConnection();
            channel = connection.createChannel();
            
        } catch (Exception exception) {
            System.err.println("Error: " + exception);
        }
    }

    private void sendMessageAsync(String methodName, Object... params) {
	 try {
         channel.queueDeclare(QUEUE_NAME, true, false, false, null);

         StringBuilder message = new StringBuilder(methodName + "(");
         for (Object arg : params) {
             message.append(arg).append(", ");
         }
         // Remove the trailing ", " if there are arguments
         if (params.length > 0) {
             message.delete(message.length() - 2, message.length());
         }
         message.append(")");

         // Publish the message to the RabbitMQ queue
         channel.basicPublish("", QUEUE_NAME, null, message.toString().getBytes());
         System.out.println("Sent message: " + message.toString());
     }
	  catch (Exception exception) {
	     exception.printStackTrace();
	 }
    }
    
    public void naturalLogarithm(double x) {
        sendMessageAsync("naturalLogarithm", x);
    }

    public void base10Logarithm(double x) {
        sendMessageAsync("base10Logarithm", x);
    }

    public void exponentiation(double base, double exponent) {
        sendMessageAsync("exponentiation", base, exponent);
    }

    public void sine(double angle) {
        sendMessageAsync("sine", angle);
    }

    public void cosine(double angle) {
        sendMessageAsync("cosine", angle);
    }

    public void squareRoot(double x) {
        sendMessageAsync("squareRoot", x);
    }

    public void addition(int x, int y) {
        sendMessageAsync("addition", x, y);
    }

    public void subtraction(int x, int y) {
        sendMessageAsync("subtraction", x, y);
    }

    public void multiplication(int x, int y) {
        sendMessageAsync("multiplication", x, y);
    }

    public void division(int x, int y) {
        sendMessageAsync("division", x, y);
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();

        client.naturalLogarithm(2.0);
        client.base10Logarithm(100.0);
        client.exponentiation(2.0, 3.0);
        client.sine(45.0);
        client.cosine(60.0);
        client.squareRoot(25.0);
        client.addition(5, 3);
        client.subtraction(8, 4);
        client.multiplication(6, 7);
        client.division(20, 4);
    }
}
