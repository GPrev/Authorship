package Stats;

public class Statistician {
	int _totalCount;
	int _actualPositives;
	int _predictedPositive;
	int _wellPredictedPositive;

	public Statistician(int totalCount,
			int actualPositives,
			int predictedPositive,
			int wellPredictedPositive)
	{
		_totalCount = totalCount;
		_actualPositives = actualPositives;
		_predictedPositive = predictedPositive;
		_wellPredictedPositive = wellPredictedPositive;
	}
	
	private int actualNegatives()
	{
		return _totalCount - _actualPositives;
	}
	
	private int predictedNegatives() //considering that all have been predicted
	{
		return _totalCount - _predictedPositive;
	}

	private int wronglyPredictedPositive()
	{
		return _predictedPositive - _wellPredictedPositive;
	}
	
	private int wronglyPredictedNegative()
	{
		return _actualPositives - _wellPredictedPositive;
	}
	
	private int wellPredictedNegatives()
	{
		return predictedNegatives() - wronglyPredictedNegative();
	}
	
	private int wellPredicted()
	{
		return _wellPredictedPositive + wellPredictedNegatives();
	}
	
	private int wronglyPredicted()
	{
		return wronglyPredictedPositive() + wronglyPredictedNegative();
	}

	public float precision()
	{
		return _wellPredictedPositive / _predictedPositive;
	}
	
	public float recall()
	{
		return _wellPredictedPositive / _actualPositives;
	}
	
	public float specificity() //precision for the negatives
	{
		return wellPredictedNegatives() / predictedNegatives();
	}
	
	public float silence()
	{
		return wronglyPredictedNegative() / _actualPositives;
	}
	
	public float fallout()
	{
		return wronglyPredictedPositive() / actualNegatives();
	}

	public float falseAlarm()
	{
		return wronglyPredictedPositive() / _predictedPositive;
	}
	
	public float falseReject()
	{
		return wronglyPredictedNegative() / predictedNegatives();
	}
	
	public float accuracy() //considering that all have been predicted
	{
		return wellPredicted() / _totalCount;
	}

	public float errorRate()
	{
		return wronglyPredicted() / _totalCount;
	}

	public float fMeasure()
	{
		float precision = precision();
		float recall = recall();
		
		return 2 * (precision * recall) / (precision + recall);
	}
}
