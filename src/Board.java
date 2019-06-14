//Created by Sidharth Lakshmanan
//APCS P.5 Mr.Peterson
//
//This class creates the board and finds all of the words on that board
import java.util.*;
public class Board {
	private String[][] board;
	private WordList wordList;
	
	//this constructor creates the board
	public Board(WordList wordList, int size) {
		this.wordList = wordList;
		Random rand = new Random();
		this.board = new String[size][size];
		
		//board is populated using distribution of all letters in word list (nextLetter() method)
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length ; j++) {
				this.board[i][j] = wordList.nextLetter();
				
			}
		}
		
		//randomly places a suffix
		String[] suffixes = {"ED", "EST", "ING"};
		int choose = rand.nextInt(suffixes.length);
		int x = rand.nextInt(this.board.length);
		int y = rand.nextInt(this.board.length);
		this.board[x][y] = suffixes[choose];
		
		//randomly places a prefix
		String[] prefixes = {"RE", "STR", "UN"};
		int choose1 = rand.nextInt(prefixes.length);
		int a = rand.nextInt(this.board.length);
		int b = rand.nextInt(this.board.length);
		while (a == x && b == y) {
			a = rand.nextInt(this.board.length);
			b = rand.nextInt(this.board.length);
		}
		this.board[a][b] = prefixes[choose1];
	}
	
	//this method finds all possible words in board using recursion
	public ArrayList<String> find() {
		ArrayList<String> list = new ArrayList<String>();
		String word = "";
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				
				//calls the helper method
				explore(list, this.wordList.getLongestWordLength(), word, i, j);
			}
		}
		return list;
	}
	
	//this is the helper method to the find method
	private void explore(ArrayList<String> list, int max, String word, int i, int j) {
		
		//if position falls off the board or exceeds word length
		if(max <= 0 || i>=this.board.length || j>=this.board.length || j<0 || i<0) {
				checkAdd(word, list);
		}
		
		//if suffix, stop going further
		else if(this.board[i][j].equals(" ") || this.board[i][j].equals("ED") || this.board[i][j].equals("EST") || this.board[i][j].equals("ING")) {
			if(this.board[i][j].equals(" ")) {
				checkAdd(word, list);
			}
			else{
					checkAdd(word + this.board[i][j], list);
			}
		}
		
		//if prefix, the word can only start there
		else if(max != this.wordList.getLongestWordLength() && (this.board[i][j].equals("RE") || this.board[i][j].equals("STR") || this.board[i][j].equals("UN"))) {
			checkAdd(word, list);
		}
		
		//if it is a weird combination of letters, abandon search
		else if(isWeirdWord(word + this.board[i][j])) {
			//abandon search
		}
		
		//does the recursive step
		else {
			//Stores a temp variable
			String c = this.board[i][j];
			this.board[i][j] = " ";
			
			//Explores possibilities
			for (int a = -1; a <= 1; a++) {
				for (int b = -1; b <= 1; b++) {
					if(!(a==0 && b==0)) {
						explore(list, max-c.length(), word + c, i+a, j+b);
					}
				}
			}
			
			//backtracks
			this.board[i][j] = c;
		}
	}
	
	//this checks if the word looks too weird for any real words to be formed
	private boolean isWeirdWord(String word) {
		if(word.length() >= 3) {
			String t = "";
			for (int i = 0; i < 3; i++) {
				t+=word.charAt(word.length()-1);
			}
			if(word.substring(word.length()-3, word.length()).equals(t)) {
				return true;
			}
		}
		if(word.length()>=2) {
			return array(word.charAt(word.length()-1), word.charAt(word.length()-2));
		}
		return false;
	}
	
	//This method checks if the next letter is not viable, this was calculated through analysis of the word list
	private boolean array(char x, char y) {
		String[] array = {"C","JVX","D","X","F","QVXZ","G","QX","H","XZ","J","BFGLMQSTVWXYZ","K","QXZ",
				"M","X","P","QX","Q","BCDFGHJKLMNPQWXZ","S","X","V","CFJKMPQWX","W","JX","X","ZY","Z","FNRX"};
		for(int i=0; i<array.length; i+=2) {
			if(array[i].equals(x + "")) {
				for(int j = 0; j<array[i+1].length(); j++) {
					if(array[i+1].charAt(j) == y) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void checkAdd(String word, ArrayList<String> list) {
		if(this.wordList.contains(word) && !list.contains(word)) {
			list.add(word);
		}
	}
	
	//this method displays the board elegantly
	public String toString() {
		String t = "";
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if(this.board[i][j].length() > 1) {
					if(this.board[i][j].equals("ED") || this.board[i][j].equals("EST") || this.board[i][j].equals("ING")) {
						if(this.board[i][j].length()==2) {
							t +="-" + this.board[i][j] + "  ";
						}
						else {
							t+="-" + this.board[i][j] + " ";
						}
					}
					else {
						if(this.board[i][j].length()==2) {
							t +=this.board[i][j] + "-  ";
						}
						else {
							t+=this.board[i][j] + "- ";
						}
					}
				}
				else {
					t += this.board[i][j] + "    ";
				}
			}
			t+= "\n\n";
		}
		return t;
	}
}
