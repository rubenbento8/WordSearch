package rbento;

import java.util.Arrays;
import java.util.List;

public class MainWord {
	public static void main(String args[]) {
		List<String> words = Arrays.asList("one", "two", "three", "four");
		
		GridHard grid = new GridHard(20);
		
		grid.fillGrid(words);
		grid.displayGrid();
	}
}
