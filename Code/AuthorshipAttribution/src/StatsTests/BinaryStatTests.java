package StatsTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Stats.BinaryStat;

public class BinaryStatTests {
	BinaryStat _stat;
	float expectedPrecision;
	float expectedRecall;
	float expectedSpecificity;
	float expectedSilence;
	float expectedFallout;
	float expectedFalseAlarm;
	float expectedFalseReject;
	float expectedAccuracy;
	float expectedErrorRate;
	float expectedFMeasure;
	
	private static final float floatDelta = 0.0001f;
	
	@Before
	public void init()
	{
		_stat = new BinaryStat(10, 4, 5, 3);
		float s = 10;
		float tP = 3;
		float fP = 2;
		float tN = 4;
		float fN = 1;
		expectedPrecision = tP / (tP + fP);
		expectedRecall = tP / (tP + fN);
		expectedSpecificity = tN / (tN + fP);
		expectedSilence = fN / (tP + fN);
		expectedFallout = fP / (fP + tN);
		expectedFalseAlarm = fP / (tP + fP);
		expectedFalseReject = fN / (tN + fN);
		expectedAccuracy = (tP + tN) / s;
		expectedErrorRate = (fP + fN) / s;
		expectedFMeasure = 2*expectedPrecision*expectedRecall/(expectedPrecision + expectedRecall);
	}

	@Test
	public void binaryPrecision() { 
		assertEquals(expectedPrecision, _stat.precision(), floatDelta);
	}
	@Test
	public void binaryRecall() {
		assertEquals(expectedRecall, _stat.recall(), floatDelta);
	}
	@Test
	public void binarySpecificity() {
		assertEquals(expectedSpecificity, _stat.specificity(), floatDelta);
	}
	@Test
	public void binarySilence() {
		assertEquals(expectedSilence, _stat.silence(), floatDelta);
	}
	@Test
	public void binaryFallout() {
		assertEquals(expectedFallout, _stat.fallout(), floatDelta);
	}
	@Test
	public void binaryFalseAlarm() {
		assertEquals(expectedFalseAlarm, _stat.falseAlarm(), floatDelta);
	}
	@Test
	public void binaryFalseReject() {
		assertEquals(expectedFalseReject, _stat.falseReject(), floatDelta);
	}
	@Test
	public void binaryAccuracy() {
		assertEquals(expectedAccuracy, _stat.accuracy(), floatDelta);
	}
	@Test
	public void binaryErrorRate() {
		assertEquals(expectedErrorRate, _stat.errorRate(), floatDelta);
	}
	@Test
	public void binaryFMeasure() {
		assertEquals(expectedFMeasure, _stat.fMeasure(), floatDelta);
	}

}
