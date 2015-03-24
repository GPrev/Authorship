package Stats;

public class BinaryStat implements Statistician {
	int _totalCount;
	int _actualPositives;
	int _predictedPositive;
	int _wellPredictedPositive;

	public BinaryStat(int totalCount,
			int actualPositives,
			int predictedPositive,
			int wellPredictedPositive)
	{
		_totalCount = totalCount;
		_actualPositives = actualPositives;
		_predictedPositive = predictedPositive;
		_wellPredictedPositive = wellPredictedPositive;
	}
	
	int actualNegatives()
	{
		return _totalCount - _actualPositives;
	}
	
	int predictedNegatives() //considering that all have been predicted
	{
		return _totalCount - _predictedPositive;
	}

	int wronglyPredictedPositive()
	{
		return _predictedPositive - _wellPredictedPositive;
	}
	
	int wronglyPredictedNegative()
	{
		return _actualPositives - _wellPredictedPositive;
	}
	
	int wellPredictedNegatives()
	{
		return predictedNegatives() - wronglyPredictedNegative();
	}
	
	int wellPredicted()
	{
		return _wellPredictedPositive + wellPredictedNegatives();
	}
	
	int wronglyPredicted()
	{
		return wronglyPredictedPositive() + wronglyPredictedNegative();
	}

	public float precision()
	{
		return (float) _wellPredictedPositive / _predictedPositive;
	}
	
	public float recall()
	{
		return (float) _wellPredictedPositive / _actualPositives;
	}
	
	public float specificity() //precision for the negatives
	{
		return (float) wellPredictedNegatives() / actualNegatives();
	}
	
	public float silence()
	{
		return (float) wronglyPredictedNegative() / _actualPositives;
	}
	
	public float fallout()
	{
		return (float) wronglyPredictedPositive() / actualNegatives();
	}

	public float falseAlarm()
	{
		return (float) wronglyPredictedPositive() / _predictedPositive;
	}
	
	public float falseReject()
	{
		return (float) wronglyPredictedNegative() / predictedNegatives();
	}
	
	public float accuracy() //considering that all have been predicted
	{
		return (float) wellPredicted() / _totalCount;
	}

	public float errorRate()
	{
		return (float) wronglyPredicted() / _totalCount;
	}

	public float fMeasure()
	{
		float precision = precision();
		float recall = recall();
		
		return 2 * (precision * recall) / (precision + recall);
	}
}
