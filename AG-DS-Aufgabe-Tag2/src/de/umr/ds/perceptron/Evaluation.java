package de.umr.ds.perceptron;

public class Evaluation {

	/**
	 * Applies the model to each data vector in the dataset and computes the
	 * accuracy.
	 * 
	 * @return accuracy
	 */
	public static double accuracy(Perceptron model, Dataset dataset) {
		double total = dataset.size();
		double correct = 0;
		for (DataPoint p : dataset){
			if(model.predict(p) == p.getLabel()){
				correct += 1;
			}
		}

		return correct/total;
	}

}
