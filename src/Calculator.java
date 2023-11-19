public class Calculator {

    public int addition(int x, int y) {
        return x + y;
    }

    public int subtraction(int x, int y) {
        return x - y;
    }

    public int multiplication(int x, int y) {
        return x * y;
    }

    public double division(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException("Não pode dividir por zero");
        }
        return (double) x / y;
    }

    public double naturalLogarithm(double x) {
        if (x <= 0) {
            throw new ArithmeticException("O logaritmo é indefinido para números não positivos");
        }
        return Math.log(x);
    }

    public double base10Logarithm(double x) {
        if (x <= 0) {
            throw new ArithmeticException("O logaritmo é indefinido para números não positivos");
        }
        return Math.log10(x);
    }

    public double exponentiation(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public double sine(double angle) {
        return Math.sin(angle);
    }

    public double cosine(double angle) {
        return Math.cos(angle);
    }

    public double squareRoot(double x) {
        if (x < 0) {
            throw new ArithmeticException("A raiz quadrada para números negativos é indefinida");
        }
        return Math.sqrt(x);
    }
}
