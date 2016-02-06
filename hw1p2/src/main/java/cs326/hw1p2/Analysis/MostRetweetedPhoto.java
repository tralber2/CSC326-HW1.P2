package cs326.hw1p2.Analysis;

import java.util.List;

import twitter4j.MediaEntity;
import twitter4j.Status;

public class MostRetweetedPhoto {
	public String mostRetweetedPhoto(List<Status> list)
	{
		int max = 0;
		int photoIndex = -1;
		Status maxPhoto = null;
		MediaEntity maxMediaEntities[] = null;
		int i = 0;
		for( i = 0; i < list.size(); i++ ) {
			if( list.get( i ).getRetweetCount() > max ) {
				maxMediaEntities = list.get( i ).getMediaEntities();
				for( int j = 0; j < maxMediaEntities.length; j++ ) {
					if ( maxMediaEntities[j].getType().equals("photo") ) {
						maxPhoto = list.get( i );
						photoIndex = j;
						max = maxPhoto.getRetweetCount();
						break;
					}
				}
			}
		}
		return maxPhoto.getMediaEntities()[photoIndex].getMediaURL();
	}
}
