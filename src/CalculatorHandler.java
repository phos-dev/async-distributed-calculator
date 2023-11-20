import java.io.UnsupportedEncodingException;

import com.rabbitmq.client.*;

public class CalculatorHandler {

    private static final String QUEUE_NAME = "calculator_queue";

    public static void main(String[] argv) throws Exception {
    	ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("rabbitmq");
        factory.setUsername("user");  
        factory.setPassword("password"); 

        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
                    String message = new String(body, "UTF-8");
                    processMessage(message);
                }
            };

            channel.basicConsume(QUEUE_NAME, true, consumer);

            System.out.println("CalculatorHandler is listening!");
           
            while (true) {
                Thread.sleep(1000);
            }
        }
    }

    private static void processMessage(String message) {
        try {
            String[] parts = message.split("\\(");
            String methodName = parts[0];
            String[] argStrings = parts[1].replaceAll("\\)", "").split(", ");
            Object[] args = new Object[argStrings.length];

           for (int i = 0; i < argStrings.length; i++) {
                if (argStrings[i].contains(".")) {
                    args[i] = (double) Double.parseDouble(argStrings[i]);
                } else {
                    args[i] = (int) Integer.parseInt(argStrings[i]);
                }
            }

            Calculator calculator = new Calculator();

            Object result = Calculator.class.getMethod(methodName, getArgTypes(args)).invoke(calculator, args);

            System.out.println(message + ": " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?>[] getArgTypes(Object[] args) {
        Class<?>[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = toPrimitiveType(args[i].getClass());
        }
        return argTypes;
    }

    private static Class<?> toPrimitiveType(Class<?> type) {
        if (type == Double.class) {
            return double.class;
        } else if (type == Integer.class) {
            return int.class;
        } 

        return type;
    }

}
