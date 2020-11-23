package cow.primary.special;

public class PaperFolding {
	public static void printAllFolds(int N) {
	
	}
	
	public static void process(int i, int N, boolean down) {
		if (i > N) {
			return;
		}
		process(i + 1, N, true);
		System.out.println(down ? "down " : "up ");
		process(i + 1, N, false);
	}
}
