package rbento;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GridHard {
	
	private int GRID_SIZE;
	private char[][] contents;
	private List<Coordinate> coordinates = new ArrayList<>();
	
	private enum Direction{
		HORIZONTAL,
		HORIZONTAL_INVERTED,
		VERTICAL,
		VERTICAL_INVERTED,
		DIAGONAL_X_Y,			//Upper Left to Bottom Right
		DIAGONAL_INVX_INVY,		//Bottom Right to Upper Left
		DIAGONAL_X_INVY,		//Upper Right to Bottom Left  
		DIAGONAL_INVX_Y;		//Bottom Left to Upper Right
	}
	
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
		Collections.shuffle(coordinates);
		for(String word: words) {
			for(Coordinate coordinate: coordinates) {
				int x = coordinate.x;
				int y = coordinate.y;
				Direction direction = fitDirection(word, coordinate);
				
				if(direction != null) {
					switch (direction) {
					
						case HORIZONTAL:
							for (char c: word.toCharArray()) {
								contents[x][y++]= c;
							}
							break;
						case HORIZONTAL_INVERTED:
							for (char c: word.toCharArray()) {
								contents[x][y--]= c;
							}
							break;
							
						case VERTICAL:
							for (char c: word.toCharArray()) {
								contents[x++][y]= c;
							}
							break;
							
						case VERTICAL_INVERTED:
							for (char c: word.toCharArray()) {
								contents[x--][y]= c;
							}
							break;
						
						case DIAGONAL_X_Y:
							for (char c: word.toCharArray()) {
								contents[x++][y++]= c;
							}
							break;
						case DIAGONAL_INVX_INVY:
							for (char c: word.toCharArray()) {
								contents[x--][y--]= c;
							}
							break;
						case DIAGONAL_X_INVY:
							for (char c: word.toCharArray()) {
								contents[x++][y--]= c;
							}
							break;
						case DIAGONAL_INVX_Y:
							for (char c: word.toCharArray()) {
								contents[x--][y++]= c;
							}
							break;
					}
					break;
				}
			}
		}
		fillRandomLetters();
	}
	
	private Direction fitDirection(String word, Coordinate coordinate) {
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
					if(contents[coordinate.x][coordinate.y +i] != '_') return false;
				}
				break;
				
			case HORIZONTAL_INVERTED:
				if(coordinate.y - wordLength < 0) return false;
				for(int i = 0; i < wordLength; i++) {
					if(contents[coordinate.x][coordinate.y -i] != '_') return false;
				}
				break;
					
			case VERTICAL:
				if(coordinate.x + wordLength > GRID_SIZE) return false;
				for(int i = 0; i < wordLength; i++) {
					if(contents[coordinate.x + i][coordinate.y] != '_') return false;
				}
				break;
				
			case VERTICAL_INVERTED:
				if(coordinate.x - wordLength < 0) return false;
				for(int i = 0; i < wordLength; i++) {
					if(contents[coordinate.x - i][coordinate.y] != '_') return false;
				}
				break;
				
			case DIAGONAL_X_Y:
				if(coordinate.x + wordLength > GRID_SIZE || coordinate.y + wordLength > GRID_SIZE) return false;
				for(int i = 0; i < wordLength; i++) {
					if(contents[coordinate.x + i][coordinate.y + i] != '_') return false;
				}	
				break;
				
			case DIAGONAL_INVX_INVY:
				if(coordinate.x - wordLength < 0 || coordinate.y - wordLength < 0) return false;
				for(int i = 0; i < wordLength; i++) {
					if(contents[coordinate.x - i][coordinate.y - i] != '_') return false;
				}	
				break;
			case DIAGONAL_X_INVY:
				if(coordinate.x + wordLength > GRID_SIZE || coordinate.y - wordLength < 0) return false;
				for(int i = 0; i < wordLength; i++) {
					if(contents[coordinate.x + i][coordinate.y - i] != '_') return false;
				}	
				break;
			case DIAGONAL_INVX_Y:
				if(coordinate.x - wordLength < 0 || coordinate.y + wordLength > GRID_SIZE) return false;
				for(int i = 0; i < wordLength; i++) {
					if(contents[coordinate.x - i][coordinate.y + i] != '_') return false;
				}	
				break;
		}
		
		return true;
	}
	
	private void fillRandomLetters() {
		for(int i = 0; i < GRID_SIZE; i++) {
			for(int j = 0; j < GRID_SIZE; j++) {
				if(contents[i][j] == '_') {
					Random rnd = new Random();
					char c = (char)('A'+ rnd.nextInt(26));
					contents[i][j] = c;
				}
			}
		}
		
		
	}
}
