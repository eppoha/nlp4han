package com.lc.nlp4han.clustering;

import org.junit.Test;

public class TestDistanceCalculator
{
	@Test
	public void testGetDistance_1()
	{
		Text t1 = null;
		Text t2 = null;
		
		FeatureGenerator fg = null;
		
		t1.generateSample(fg);
		t2.generateSample(fg);
		
		DistanceCalculator dis = null;
		double d = dis.getDistance(t1, t2);  //Text与Text间的距离
	}
	
	@Test
	public void testGetDistance_2()
	{
		Group g1 = null;
		Text t1 = null;
		
		FeatureGenerator fg = null;
		t1.generateSample(fg);
		
		g1.updateCenter();
		
		DistanceCalculator dis = null;
		double d = dis.getDistance(t1, g1);  //Text与Group间的距离
	}
	
	@Test
	public void testGetDistance_3()
	{
		Group g1 = null;
		Group g2 = null;
		
		
		DistanceCalculator dis = null;
		double d = dis.getDistance(g1, g2);  //Group与Group间的距离
	}
}