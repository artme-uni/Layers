public class Main {
    public static void main(String[] args) {
        int a = 0;
        int b = 10;

        Layer layer;

        layer = new Layer(50, a, b, 0.25, 0.5, Type.FORWARD);
        layer = new Layer(50, a, b, 0.25, 1, Type.FORWARD);

        layer = new Layer(100, a, b, 0.25, 0.5, Type.BACK);
        layer = new Layer(100, a, b, 0.5, 0.5, Type.BACK);
        layer = new Layer(100, a, b, 1, 0.5, Type.BACK);
        layer = new Layer(100, a, b, 1.25, 0.5, Type.BACK);

        layer = new Layer(100, a, b, 0.25, 1, Type.BACK);
        layer = new Layer(100, a, b, 0.5, 1, Type.BACK);
        layer = new Layer(100, a, b, 1, 1, Type.BACK);
        layer = new Layer(100, a, b, 1.25, 1, Type.BACK);
    }
}
