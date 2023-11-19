import java.net.URL;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class RPCClient {

    private static final String SERVER_URL = "http://localhost:8185";
    private static final String SERVER_HANDLER = "Calculator";
    private XmlRpcClient client;

    public RPCClient() {
        try {
            XmlRpcClientConfigImpl clientConfig = new XmlRpcClientConfigImpl();
            clientConfig.setServerURL(new URL(SERVER_URL));

            client = new XmlRpcClient();
            client.setConfig(clientConfig);
        } catch (Exception exception) {
            System.err.println("Error: " + exception);
        }
    }

    private String buildCommand(String methodName) {
        return SERVER_HANDLER + "." + methodName;
    }

    public double naturalLogarithm(double x) throws Exception {
        Object[] params = new Object[]{Double.valueOf(x)};
        Double result = (Double) client.execute(buildCommand("naturalLogarithm"), params);
        return result;
    }

    public double base10Logarithm(double x) throws Exception {
        Object[] params = new Object[]{Double.valueOf(x)};
        Double result = (Double) client.execute(buildCommand("base10Logarithm"), params);
        return result;
    }

    public double exponentiation(double base, double exponent) throws Exception {
        Object[] params = new Object[]{Double.valueOf(base), Double.valueOf(exponent)};
        Double result = (Double) client.execute(buildCommand("exponentiation"), params);
        return result;
    }

    public double sine(double angle) throws Exception {
        Object[] params = new Object[]{Double.valueOf(angle)};
        Double result = (Double) client.execute(buildCommand("sine"), params);
        return result;
    }

    public double cosine(double angle) throws Exception {
        Object[] params = new Object[]{Double.valueOf(angle)};
        Double result = (Double) client.execute(buildCommand("cosine"), params);
        return result;
    }

    public double squareRoot(double x) throws Exception {
        Object[] params = new Object[]{Double.valueOf(x)};
        Double result = (Double) client.execute(buildCommand("squareRoot"), params);
        return result;
    }

    public int addition(int x, int y) throws Exception {
        Object[] params = new Object[]{Integer.valueOf(x), Integer.valueOf(y)};
        Integer result = (Integer) client.execute(buildCommand("addition"), params);
        return result;
    }

    public int subtraction(int x, int y) throws Exception {
        Object[] params = new Object[]{Integer.valueOf(x), Integer.valueOf(y)};
        Integer result = (Integer) client.execute(buildCommand("subtraction"), params);
        return result;
    }

    public int multiplication(int x, int y) throws Exception {
        Object[] params = new Object[]{Integer.valueOf(x), Integer.valueOf(y)};
        Integer result = (Integer) client.execute(buildCommand("multiplication"), params);
        return result;
    }

    public double division(int x, int y) throws Exception {
        Object[] params = new Object[]{Integer.valueOf(x), Integer.valueOf(y)};
        Double result = (Double) client.execute(buildCommand("division"), params);
        return result;
    }

    public static void main(String[] args) throws Exception {
        RPCClient client = new RPCClient();

        System.out.println("Logaritmo Natural: " + client.naturalLogarithm(2.0));
        System.out.println("Logaritmo na Base 10: " + client.base10Logarithm(100.0));
        System.out.println("Exponenciacao: " + client.exponentiation(2.0, 3.0));
        System.out.println("Seno: " + client.sine(45.0));
        System.out.println("Cosseno: " + client.cosine(60.0));
        System.out.println("Raiz Quadrada: " + client.squareRoot(25.0));
        System.out.println("Adicao: " + client.addition(5, 3));
        System.out.println("Subtracao: " + client.subtraction(8, 4));
        System.out.println("Multiplicacao: " + client.multiplication(6, 7));
        System.out.println("Divisao: " + client.division(20, 4));
    }
}
