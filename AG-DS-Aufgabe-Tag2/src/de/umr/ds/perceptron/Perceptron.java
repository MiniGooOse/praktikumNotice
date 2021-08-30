package de.umr.ds.perceptron;

import java.util.Random;

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
        double sum = 0;
        for(int i = 0; i < this.dim; i++){
            sum += v.getDim(i) * this.w[i];
        }
        if (sum + this.b > 0){
            return 1;
        } else {
            return 0;
        }
    }
}
