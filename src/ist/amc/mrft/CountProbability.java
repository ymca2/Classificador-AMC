package ist.amc.mrft;

import ist.amc.dataset.DataSet;

public class CountProbability {
	
	private static final double delta = 0.2;
	
	public static double calculateOrdinaryEdgeProbability(int positionI, int positionJ, int valueI, int valueJ, DataSet fiber) {
		return (double) (fiber.countDependences(positionI, positionJ, valueI, valueJ) + delta) / (fiber.countVariable(positionI, valueI) + delta*fiber.getDomainLength(positionJ));
	}
	
	public static double calculateSpecialEdgeProbability(int positionI, int positionJ, int valueI, int valueJ, DataSet fiber) {
		return (double) (fiber.countDependences(positionI, positionJ, valueI, valueJ) + delta) / (fiber.getLineNumber() + delta*fiber.getDomainLength(positionJ)*fiber.getDomainLength(positionI));
	}

}
