package com.bittrust.gaming.roulette;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.math.random.MersenneTwister;
import org.apache.commons.math.random.RandomGenerator;



public class American {
	
	public enum Color {
		RED,
		BLACK,
		GREEN
	}

	private static final Integer[] redNums = new Integer[] { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
	private static final Integer[] blackNums = new Integer[] { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35 };

	private static final Set<Integer> reds = new TreeSet<Integer>(Arrays.asList(redNums));
	private static final Set<Integer> blacks = new TreeSet<Integer>(Arrays.asList(blackNums));
	
	private MersenneTwister rnd;

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {

		
	}
	
	public int roll() {
		if(rnd == null) {
			rnd = new MersenneTwister();;
		}
		
		return roll(rnd);
	}
	
	public int roll(RandomGenerator randomGenerator) {
		return randomGenerator.nextInt(redNums.length + blackNums.length + 2);
	}
	
	public static Color numToColor(int num) {
		if(num == 0 || num == 38)
			return Color.GREEN;
		
		return reds.contains(num) ? Color.RED : Color.BLACK;
	}

}
