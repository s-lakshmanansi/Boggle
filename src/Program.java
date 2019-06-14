//Created by Sidharth Lakshmanan
//APCS P.5 Mr.Peterson
//
//This class creates a board and prints all possible word on that board arranged by size
public class Program {

	public static void main(String[] args) {
		System.out.println("Welcome to Boggle, by Sidharth Lakshmanan");
		WordList wordList = new WordList("WordList.txt", 3, 8);
		int boardSize = 4;
		Board board = new Board(wordList, boardSize);
		System.out.println("\n" + board);
		WordComparator words = new WordComparator(board.find(), wordList);
		words.printWords();
	}
}
