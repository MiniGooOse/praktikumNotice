package de.umr.ds.perceptron;

import java.util.Random;
import java.util.function.Predicate;

/**
 * A Perceptron holds weights and bias and can be applied to a data vector to
 * predict its class. Weights and bias are initialized randomly.
 */
public class Perceptron {
    private double[] w;
    private double b;
    private final int dim;

    public double[] getW() {
        return w;
    }

    public void setW(double[] w) {
        this.w = w;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public int getDim() {
        return dim;
    }

    public Perceptron(int dim){
        this.dim = dim;
        this.w = new double[dim];
        this.b =  new Random().nextDouble();
        for(int i = 0; i < dim; i++){
            w[i] = new Random().nextDouble();
        }
    }

    public Perceptron(){
        this.dim = 2;
        this.w = new double[dim];
        this.b =  new Random().nextDouble();
        for(int i = 0; i < dim; i++){
            w[i] = new Random().nextDouble();
        }
    }

    public int predict(Vector v){
        double sum = v.dot(new Vector(w));

        if (sum + this.b > 0){
            return 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Perceptron p = new Perceptron();
        Dataset dataset = new Dataset(10);

        double[] wCurrent = p.getW();
        double[] wDelta = new double[2];
        double[] wNext = new double[2];
        double newB = p.getB();

        for(DataPoint point : dataset){
            int theo = p.predict(point);
            int prak = point.getLabel();
            for(int i = 0; i < 2; i++){
                wDelta[i] = 0.05 * (theo - prak) * point.getDim(i);
                System.out.println(wCurrent.toString());
                System.out.print(theo);
                System.out.print("    ");
                System.out.print(prak);
                System.out.print("    ");

                System.out.println(wDelta[i]);
                wNext[i] = wDelta[i] + wCurrent[i];
                double deltaB = 0.05 * (prak - theo);
                newB += deltaB;
                System.out.println(deltaB);
                System.out.println(wNext[i]);

            }
            newB += 0.05 * (theo - prak);
            wCurrent = wNext;
        }
        System.out.println();
    }
}
