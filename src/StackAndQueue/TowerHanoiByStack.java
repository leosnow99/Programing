package StackAndQueue;

public class TowerHanoiByStack {
	public static int hanoiProblemRecursion(int num, String left, String mid, String right) {
		if (num < 1) {
			return 0;
		}
		return process(num, left, right, left, right);
	}
	
	public static int process(int num, String left, String right, String from, String to) {
		return 0;
	}
}
