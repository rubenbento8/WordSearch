package rbento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GridHard {
	
	private int GRID_SIZE;
	private char[][] contents;
	private List<Coordinate> coordinates = new ArrayList<>();
	
	private enum Direction{
		HORIZONTAL,
		VERTICAL,
		DIAGONAL;
	}
	
	@SuppressWarnings("unused")
	private class Coordinate {
		int x;
		int y;
		Coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public GridHard(int gridSize) {
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
	
	private Direction wordFit(String word, Coordinate coordinate) {
		List<Direction> directions = Arrays.asList(Direction.values());
		Collections.shuffle(directions);
		for (Direction direction : directions) {
			if(ifFits(word, coordinate, direction)) {
				return direction;
			}
		}
		return null;	
	}
			
			
			
			
	private boolean ifFits(String word, Coordinate coordinate, Direction direction) {		
		int wordLength = word.length();
		switch (direction) {
		
			case HORIZONTAL:
				if(coordinate.y + wordLength > GRID_SIZE) return false;
				for(int i = 0; i < wordLength; i++) {
					
				}
					
			case VERTICAL:
				if(coordinate.x + wordLength > GRID_SIZE) return false;
				
			case DIAGONAL:
				if(coordinate.x + wordLength > GRID_SIZE || coordinate.y + wordLength > GRID_SIZE) return false;
				
		}
		
		
		
		
		
		if(coordinate.y + wordLength < GRID_SIZE) {
			for(int i = 0; i < wordLength; i++) {
				if (contents[coordinate.x][coordinate.y + i] != '_') return false;
			}
			return true;
		}
		return false;
	}
	
}
