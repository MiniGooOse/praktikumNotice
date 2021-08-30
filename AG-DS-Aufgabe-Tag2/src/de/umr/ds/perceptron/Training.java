package de.umr.ds.perceptron;

import java.util.Collections;

public class Training {

	private static final double alpha = 0.05; // learning rate
	private static final int epochs = 100; // number of epochs

	/**
	 * A perceptron is trained on a dataset. After each epoch the perceptron's
	 * parameters are updated, the dataset is shuffled and the accuracy is computed.
	 * 
	 * @param perceptron
	 * @param dataset
	 */
	private static void train(Perceptron perceptron, Dataset dataset) {
		int dim = perceptron.getDim();
		double[] wCurrent = perceptron.getW();
		double[] wDelta = new double[dim];
		double[] wNext = new double[dim];
		int count = 0;
		Visualization v = new Visualization(dataset, new Vector(wCurrent), perceptron.getB());


		for(DataPoint point :dataset){
			int theo = perceptron.predict(point);
			int prak = point.getLabel();
			for(int i = 0; i < dim; i++){
				wDelta[i] = alpha * (theo - prak) * point.getDim(i);
				wNext[i] = wDelta[i] + wCurrent[i];
			}
			v.update(new Vector(wNext), perceptron.getB(),count);
			v.paintComponents(v);
			count++;
			Collections.shuffle(dataset);
		}



	}

	public static void main(String[] args) {

		Dataset dataset = new Dataset(1000);
		Perceptron perceptron = new Perceptron();
		train(perceptron, dataset);

	}

}
