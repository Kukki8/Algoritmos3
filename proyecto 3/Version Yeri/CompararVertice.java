import java.util.Comparator;

public class CompararVertice implements Comparator<Vertice> {
    @Override
    public int compare(Vertice v1, Vertice v2) {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (v1.obtenerCosto() < v2.obtenerCosto()) {
            return -1;
        }
        if (v1.obtenerCosto() > v2.obtenerCosto()) {
            return 1;
        }
        return 0;
    }
}