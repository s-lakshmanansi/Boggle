//Created by Sidharth Lakshmanan
//APCS P.5 Mr.Peterson
//
//This class arranges and prints the list of words found on the Boggle Board.
import java.util.*;
public class WordComparator {
	private ArrayList<String> list;
	private WordList total;
	
	//this constructor preps the list to be analyzed and sorted
	public WordComparator(ArrayList<String> list, WordList total) {
		Collections.sort(list);
		this.list = list;
		this.total = total;
	}
	
	//this method prints the words found based on size
	public void printWords() {
		int n = this.total.getLongestWordLength();
		System.out.println("Found " + this.list.size() + " word(s)\n");
		print(n);
	}
	
	//this method uses recursion to sort the words and print them based on size
	private void print(int n) {
		if(n == 0) {
			System.out.println("");
		}
		else {
			if(checkWords(n) != -1) {
				System.out.println(n + " letter words");
				for(int i = 0; i < this.list.size(); i++) {
					if (list.get(i).length() == n) {
						System.out.println(list.get(i));
					}
				}
				System.out.println("");
			}
			print(n-1);
		}
	}
	
	//this checks to see if a word is of the desired length
	private int checkWords(int n) {
		int count = -1;
		for(int i = 0; i<this.list.size(); i++) {
			if (list.get(i).length() == n) {
				count++;
				return count;
			}
		}
		return count;
	}
}
