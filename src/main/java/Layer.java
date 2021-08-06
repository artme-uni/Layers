import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Layer {

    private final int n;
    private final double a;
    private final double h;
    private final double r;
    private final double tau;
    private final double t;

    private final double eps = 0.00001;

    ArrayList<Double> points = new ArrayList<>();

    Type lastCalculatedType;

    public Layer(int n, double a, double b, double r, double t, Type type) {
        this.n = n;
        this.a = a;
        this.r = r;
        this.t = t;

        this.h = (b - a) / n;
        this.tau = r * h;

        calculate(type);
        System.out.println(this);
        System.out.println("");
    }

    public double func(double argument) {
        if (argument < 1.0) {
            return 3;
        } else if (argument > 1.0) {
            return 1;
        } else {
            return 2;
        }
    }

    public void initPoints() {
        for (int j = 0; j <= n; j++) {
            points.add(func(a + j * h));
        }
    }

    public double getNextPoint(int index, List<Double> points, Type type) {
        switch (type) {
            case FORWARD:
                return (index == n) ? points.get(index) : (1 + r) * points.get(index) - r * points.get(index + 1);
            case BACK:
                return (index == 0) ? points.get(index) : (1 - r) * points.get(index) + r * points.get(index - 1);
            case SUM:
                return (index == 0 || index == n) ? points.get(index) :
                        points.get(index) + r * (points.get(index - 1) - points.get(index + 1));
            default:
                return 0;
        }
    }

    public void calculate(Type type) {
        initPoints();
        double currentT = 0;
        lastCalculatedType = type;

        int count = 0;
        ArrayList<Double> currentPoints = new ArrayList<>();
        while (!(Math.abs(currentT - t) < eps || currentT > t)) {
            currentPoints.clear();
            currentPoints.addAll(points);

            for (int j = 0; j <= n; j++) {
                points.set(j, getNextPoint(j, currentPoints, type));
            }
            currentT += tau;
            count++;
        }

    }

    @Override
    public String toString() {
        System.out.println(" \t");

        if(lastCalculatedType == null){
            return "Calculate before print!";
        }

        String name;
        switch (lastCalculatedType){
            case FORWARD:
                name = "Разность вперед";
                break;
            case BACK:
                name = "Разность назад";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + lastCalculatedType);
        }

        System.out.println(" \t" + name + ", N=" + n + ", r=" + r + ", T=" + t);

        StringBuilder result = new StringBuilder();
        for(int j = 0; j <= n; j++){
            result.append(String.format(Locale.FRANCE,"%.1f", a + j * h))
                    .append("\t")
                    .append(String.format(Locale.FRANCE, "%.5f", points.get(j)))
                    .append("\n");

/*            result.append(String.format("%.1f", a + j * h))
                    .append(",")
                    .append(String.format("%.5f", points.get(j)))
                    .append("\n");*/
        }

        return result.toString();
    }
}