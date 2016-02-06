package cs326.hw1p2.Analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import twitter4j.Status;

public class BestWord {
	public int mostCommonWord(List<Status> list) {
		HashMap<String, Integer> wordCount = new HashMap<String, Integer>();
		Scanner tweetScanner = null;
		String word = null;
		int highestCount = -1;
		for( Status s : list ) {
			tweetScanner = new Scanner(s.getText());
			while( tweetScanner.hasNext()) {
				word = tweetScanner.next();
				if( !wordCount.containsKey(word)) {
					wordCount.put(word, 1);
				} else {
					wordCount.put(word, wordCount.get(word) + 1);
				}
			}
		}
		for(Entry<String, Integer> entry : wordCount.entrySet()) {
		    Integer value = entry.getValue();

		    if( value > highestCount ) {
		    	highestCount = value;
		    }
		}
		return highestCount;
	}
}
