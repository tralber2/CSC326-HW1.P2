package cs326.hw1p2.Analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import twitter4j.MediaEntity;
import twitter4j.Status;

public class MostTweetedPhoto {
	public String mostTweetedPhoto(List<Status> list) {

		MediaEntity mediaEntities[] = null;
		HashMap<String, Integer> photoCount = new HashMap<String, Integer>();
		for (Status s: list ) {
			mediaEntities = s.getMediaEntities();
			for (int j = 0; j < mediaEntities.length; j++) {
				if (mediaEntities[j].getType().equals("photo")) {
					String currentURL = mediaEntities[j].getMediaURL();
					if( photoCount.containsKey(currentURL) ) {
						photoCount.put(currentURL, photoCount.get(currentURL) + s.getRetweetCount() + 1 );
					} else {
						photoCount.put(currentURL, s.getRetweetCount() + 1);
					}
				}
			}
		}
		String maxURL = null;
		int highestCount = -1;
		for(Entry<String, Integer> entry : photoCount.entrySet()) {
		    Integer value = entry.getValue();

		    if( value > highestCount ) {
		    	highestCount = value;
		        maxURL = entry.getKey();
		    }
		}
		return maxURL;
	}
}