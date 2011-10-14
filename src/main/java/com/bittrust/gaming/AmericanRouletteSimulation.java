/**
 * @author William Speirs <bill.speirs@gmail.com>
 */
package com.bittrust.gaming;

import com.bittrust.gaming.roulette.American;
import com.bittrust.gaming.roulette.American.Color;

/**
 * @author wspeirs
 *
 */
public class AmericanRouletteSimulation {

	
	private static final int RUN_SIZE = 20;

	public static void main(String[] args) {
		int curBet = 5;
		int startMoney = (int) (curBet * Math.pow(2, 6));
		int money = startMoney;
		int curRun = 1;
		int counter = 0;
		American.Color runColor = null;

		int[] redRuns = new int[RUN_SIZE];
		int[] blackRuns = new int[RUN_SIZE];
		int[] greenRuns = new int[RUN_SIZE];
		
		American roulette = new American();

		System.out.println("START MONEY: " + startMoney);
		
		while(money > 0 && money < startMoney * 2) {
			
			counter++;
			
			// make the bet
			money -= curBet;
			
			int curNum = roulette.roll();
			Color curColor = American.numToColor(curNum); 
			
			// compute the runs
			if(runColor == null) {
				runColor = curColor;
				curRun = 1;
			} else if(runColor != curColor) {
				if(runColor == Color.RED) {
					++redRuns[curRun];
					curRun = 1;
					runColor = curColor;
				} else if(runColor == Color.BLACK) {
					++blackRuns[curRun];
					curRun = 1;
					runColor = curColor;
				} else {
					++greenRuns[curRun];
					curRun = 1;
					runColor = curColor;
				}
			} else {
				++curRun;
			}
			
			// compute the money
			if(curColor == Color.BLACK) {
				money += curBet * 2;
				System.out.println("WIN  " + curNum + " " + curColor + " " + curBet + " " + money);
				curBet = 5;
			} else {
				System.out.println("LOSE " + curNum + " " + curColor + " " + curBet + " " + money);
				curBet = curBet * 2;
			}
		}
		
		if(money < 0) {
			System.out.println("*** BUSTED ***");
		}
		
		System.out.println("ROLLS: " + counter);
		System.out.println();
		
		// print out the runs
		for(int i=1; i < RUN_SIZE; ++i) {
			System.out.println(i + " RED " + redRuns[i] + " BLACK " + blackRuns[i] + " GREEN " + greenRuns[i]);
		}

	}

}
