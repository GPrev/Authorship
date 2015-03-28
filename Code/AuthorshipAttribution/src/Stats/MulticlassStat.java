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
