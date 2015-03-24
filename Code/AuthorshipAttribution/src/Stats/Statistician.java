package Stats;

public interface Statistician {
	public float precision();
	public float recall();
	public float specificity();
	public float silence();
	public float fallout();
	public float falseAlarm();
	public float falseReject();
	public float accuracy();
	public float errorRate();
	public float fMeasure();
}
