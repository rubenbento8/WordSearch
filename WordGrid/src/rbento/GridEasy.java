package rbento;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class GridEasy {
	
	private int GRID_SIZE;
	private char[][] contents;
	private List<Coordinate> coordinates = new ArrayList<>();
	
	private class Coordinate {
		int x;
		int y;
		Coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public GridEasy(int gridSize) {
		GRID_SIZE = gridSize;
		contents = new char[gridSize][gridSize];
		
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				coordinates.add(new Coordinate(i, j));
				contents[i][j] = '_';
			}
		}
	}
	
	
	
	public void displayGrid() {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				System.out.print(contents[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	public void fillGrid(List<String> words) {
		for (String word: words) {
			Collections.shuffle(coordinates);
			
			for(Coordinate coordinate: coordinates) {
				int x = coordinate.x;
				int y = coordinate.y;
			
				if(wordFit(word, coordinate)) {
					for (char c: word.toCharArray()) {
						contents[x][y++]= c;
					}
					break;
				}
			}
		}
	}
	
	private boolean wordFit(String word, Coordinate coordinate) {
		if(coordinate.y + word.length() < GRID_SIZE) {
			for(int i = 0; i < word.length(); i++) {
				if (contents[coordinate.x][coordinate.y + i] != '_') return false;
			}
			return true;
		}
		return false;
	}
	
}
