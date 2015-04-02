package Stats;

import java.util.ArrayList;
import java.util.List;

public class MulticlassStat implements Statistician {
	List<BinaryStat> _classStats;
	int _totalCount;

	public MulticlassStat(List<Integer> classCounts, List<Integer> classPrediction, List<Integer> classWellPredicted, int totalCount)
	{
		_totalCount = totalCount;
		_classStats = new ArrayList<BinaryStat>();
		int classCount  = classCounts.size();
		for(int i = 0; i < classCount; ++i)
		{
			_classStats.add(new BinaryStat(totalCount, classCounts.get(i), classPrediction.get(i), classWellPredicted.get(i)));
		}
	}

	public static MulticlassStat makeMulticlassStat(List<List<Integer>> confusionMatrix)
	{
		if(confusionMatrix == null || confusionMatrix.size() == 0 || confusionMatrix.get(0).size() == 0)
			return null;
		//else
		return new MulticlassStat(classCounts(confusionMatrix), classPrediction(confusionMatrix), classWellPredicted(confusionMatrix), totalCount(confusionMatrix));
	}
	
	public static MulticlassStat makeMulticlassStat(String csvFile)
	{
		return makeMulticlassStat(csv.Reader.readCSV(csvFile));
	}
	
	private static int totalCount(List<List<Integer>> confusionMatrix)
	{
		//sum of all elements
		int sum = 0;
		for(List<Integer> l : confusionMatrix)
		{
			for(Integer i : l)
			{
				sum += i;
			}
		}
		return sum;
	}

	private static List<Integer> classCounts(List<List<Integer>> confusionMatrix)
	{
		List<Integer> counts = new ArrayList<Integer>();
		for(List<Integer> l : confusionMatrix)
		{
			//sum of every prediction made on class l
			int count = 0;
			for(Integer i : l)
			{
				count += i;
			}
			counts.add(count);
		}
		return counts;
	}
	
	private static List<Integer> classPrediction(List<List<Integer>> confusionMatrix)
	{
		List<Integer> predictions = new ArrayList<Integer>();
		int sizex = confusionMatrix.size();
		int sizey = confusionMatrix.get(0).size();
		for(int j = 0; j < sizey; ++j)
		{
			//sum of every item predicted of class j
			int count = 0;
			for(int i = 0; i < sizex; ++i)
			{
				count += confusionMatrix.get(i).get(j);
			}
			predictions.add(count);
		}
		return predictions;
	}
	
	private static List<Integer> classWellPredicted(List<List<Integer>> confusionMatrix)
	{
		List<Integer> goodPredictions = new ArrayList<Integer>();
		for(int i = 0; i < confusionMatrix.size(); ++i)
		{
			//adds number of class i predicted i
			goodPredictions.add(confusionMatrix.get(i).get(i));
		}
		return goodPredictions;
	}

	@Override
	public float precision() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).precision();
		}
		return sum / classCount;
	}

	@Override
	public float recall() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).recall();
		}
		return sum / classCount;
	}

	@Override
	public float specificity() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).specificity();
		}
		return sum / classCount;
	}

	@Override
	public float silence() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).silence();
		}
		return sum / classCount;
	}

	@Override
	public float fallout() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).fallout();
		}
		return sum / classCount;
	}

	@Override
	public float falseAlarm() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).falseAlarm();
		}
		return sum / classCount;
	}

	@Override
	public float falseReject() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).falseReject();
		}
		return sum / classCount;
	}

	@Override
	public float accuracy() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).wellPredicted();
		}
		return sum / _totalCount;
	}

	@Override
	public float errorRate() {
		int classCount  = _classStats.size();
		float sum = 0;
		for(int i = 0; i< classCount; ++i)
		{
			sum += _classStats.get(i).wronglyPredicted();
		}
		return sum / _totalCount;
	}

	@Override
	public float fMeasure() {
		float precision = precision();
		float recall = recall();
		
		return 2 * (precision * recall) / (precision + recall);
	}

}
