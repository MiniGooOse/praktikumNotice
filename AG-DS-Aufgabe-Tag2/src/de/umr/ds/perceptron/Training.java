package de.umr.ds.perceptron;

import java.util.Collections;

public class Training {

	private static final double alpha = 0.05; // learning rate
	private static final int epochs = 10; // number of epochs
	private static double maxAccuracy = 0;
	private static Perceptron best = null;

	/**
	 * A perceptron is trained on a dataset. After each epoch the perceptron's
	 * parameters are updated, the dataset is shuffled and the accuracy is computed.
	 * 
	 * @param perceptron
	 * @param dataset
	 */
	private static void train(Perceptron perceptron, Dataset dataset) {
		Collections.shuffle(dataset);

		int dim = perceptron.getDim();
		double[] wCurrent = perceptron.getW();
		double[] wDelta = new double[dim];
		double[] wNext = new double[dim];
		double newB = perceptron.getB();
		int count = 0;
		Visualization v = new Visualization(dataset, new Vector(wCurrent), newB);

		while(count < epochs){
			for(DataPoint point : dataset){
				int theo = perceptron.predict(point);
				int prak = point.getLabel();
				for(int i = 0; i < dim; i++){
					wDelta[i] = alpha * (theo - prak) * point.getDim(i);
					if(theo -prak < 0){
						System.out.println("...............");
					}
					System.out.print(theo);
					System.out.print("    ");
					System.out.print(prak);
					System.out.print("    ");

					System.out.println(wDelta[i]);
					wNext[i] = wDelta[i] + wCurrent[i];
					double deltaB = alpha * (prak - theo);
					newB += deltaB;
					//System.out.println(wNext[i]);

				}
				newB += alpha * (theo - prak);
				v.update(new Vector(wNext), newB, count);
				wCurrent = wNext;
			}
			Perceptron p = new Perceptron();
			p.setW(wCurrent);
			p.setB(newB);
			Evaluation e = new Evaluation();
			double accuracy = e.accuracy(p,dataset);
			System.out.println(accuracy);
			if(accuracy > maxAccuracy){
				maxAccuracy = accuracy;
				Perceptron per = new Perceptron();
				per.setB(newB);
				per.setW(wCurrent);
				best = per;
			}
			count++;
			Collections.shuffle(dataset);
		}
	}

	public static void main(String[] args) {

		Dataset dataset = new Dataset(10);
		Perceptron perceptron = new Perceptron();
		train(perceptron, dataset);
	}

}
