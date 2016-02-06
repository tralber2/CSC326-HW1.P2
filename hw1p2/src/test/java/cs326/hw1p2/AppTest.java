package cs326.hw1p2;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import twitter4j.MediaEntity;
import twitter4j.Status;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import cs326.hw1p2.Analysis.BestTweets;
import cs326.hw1p2.Analysis.WorstTweets;
import cs326.hw1p2.Analysis.MostRetweetedPhoto;
import cs326.hw1p2.Analysis.MostTweetedPhoto;
import cs326.hw1p2.Analysis.BestWord;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void BestTweets()
    {
    	BestTweets best = new BestTweets();

    	List<Status> statuses = new ArrayList<Status>();
    	Status testStatus;
    	
    	testStatus = Mockito.mock(Status.class);
    	
    	when(testStatus.getId()).thenReturn(900000L);
    	when(testStatus.getRetweetCount()).thenReturn(33);
    	
    	statuses.add(testStatus);
    	
    	testStatus = Mockito.mock(Status.class);
    	
    	when(testStatus.getId()).thenReturn(100000L);
    	when(testStatus.getRetweetCount()).thenReturn(3);
    	statuses.add(testStatus);
    	
    	Status bestTweet = best.mostRetweeted(statuses);
    	assertEquals(bestTweet.getId(), 900000L );
       	assertEquals(bestTweet.getRetweetCount(), 33 );
       	
       	
    }
    @Test
    public void WorstTweets() {
    	WorstTweets worst = new WorstTweets();
    	List<Status> statuses = new ArrayList<Status>();
    	Status testStatus;
    	
    	testStatus = Mockito.mock(Status.class);
    	
    	when(testStatus.getId()).thenReturn(100000L);
    	when(testStatus.getRetweetCount()).thenReturn(3);
    	
    	statuses.add(testStatus);
    	
    	testStatus = Mockito.mock(Status.class);
    	
    	when(testStatus.getId()).thenReturn(900000L);
    	when(testStatus.getRetweetCount()).thenReturn(33);
    	statuses.add(testStatus);
    	
    	Status worstTweet = worst.leastRetweeted(statuses);
    	assertEquals(worstTweet.getId(), 100000L );
       	assertEquals(worstTweet.getRetweetCount(), 3 );  	
    }
    
    @Test
    public void MostRetweetedPhotoPhoto() {
    	MostRetweetedPhoto photo = new MostRetweetedPhoto();
    	
    	List<Status> statuses = new ArrayList<Status>();
    	Status testStatus;
    	MediaEntity firstMediaEntities[] = new MediaEntity[3];
    	MediaEntity secondMediaEntities[] = new MediaEntity[3];
    	MediaEntity photoEntity;
    	MediaEntity videoEntity;
    	
    	testStatus = Mockito.mock(Status.class);
    	photoEntity = Mockito.mock(MediaEntity.class);
    	videoEntity = Mockito.mock(MediaEntity.class);
    	
    	when(photoEntity.getType()).thenReturn("photo");
    	when(photoEntity.getMediaURL()).thenReturn("fail");
    	when(videoEntity.getType()).thenReturn("video");
    	when(videoEntity.getMediaURL()).thenReturn("fail");
    	
    	firstMediaEntities[0] = photoEntity;
    	firstMediaEntities[1] = videoEntity;
    	firstMediaEntities[2] = photoEntity;
    	
    	secondMediaEntities[0] = videoEntity;
    	secondMediaEntities[1] = videoEntity;
    	secondMediaEntities[2] = photoEntity;
    	
    	when(testStatus.getMediaEntities()).thenReturn(firstMediaEntities);
    	when(testStatus.getRetweetCount()).thenReturn(3);
    	
    	
    	statuses.add(testStatus);
    	
    	testStatus = Mockito.mock(Status.class);
    	photoEntity = Mockito.mock(MediaEntity.class);
    	videoEntity = Mockito.mock(MediaEntity.class);
    	
    	when(photoEntity.getType()).thenReturn("photo");
    	when(photoEntity.getMediaURL()).thenReturn("pass");
    	when(videoEntity.getType()).thenReturn("video");
    	when(videoEntity.getMediaURL()).thenReturn("fail");
    	
    	secondMediaEntities[0] = videoEntity;
    	secondMediaEntities[1] = videoEntity;
    	secondMediaEntities[2] = photoEntity;
    	
    	when(testStatus.getMediaEntities()).thenReturn(secondMediaEntities);
    	when(testStatus.getRetweetCount()).thenReturn(33);
    	statuses.add(testStatus);
    	
       	
       	String bestPhotoURL = photo.mostRetweetedPhoto(statuses);
       	assertTrue(bestPhotoURL.equals("pass"));
    }
    @Test
    public void MostTweetedPhotoPhoto() {
    	MostTweetedPhoto photo = new MostTweetedPhoto();
    	
    	List<Status> statuses = new ArrayList<Status>();
    	Status testStatus;
    	MediaEntity firstMediaEntities[] = new MediaEntity[3];
    	MediaEntity secondMediaEntities[] = new MediaEntity[3];
    	MediaEntity thirdMediaEntities[] = new MediaEntity[3];
    	MediaEntity goodPhoto;
    	MediaEntity badPhoto;
    	
    	testStatus = Mockito.mock(Status.class);
    	goodPhoto = Mockito.mock(MediaEntity.class);
    	badPhoto = Mockito.mock(MediaEntity.class);
    	
    	when(goodPhoto.getType()).thenReturn("photo");
    	when(goodPhoto.getMediaURL()).thenReturn("pass");
    	when(badPhoto.getType()).thenReturn("photo");
    	when(badPhoto.getMediaURL()).thenReturn("fail");
    	
    	firstMediaEntities[0] = goodPhoto;
    	firstMediaEntities[1] = badPhoto;
    	firstMediaEntities[2] = goodPhoto;
    	
    	when(testStatus.getMediaEntities()).thenReturn(firstMediaEntities);
    	when(testStatus.getRetweetCount()).thenReturn(3);
    	
    	
    	statuses.add(testStatus);
    	
    	testStatus = Mockito.mock(Status.class);
    	goodPhoto = Mockito.mock(MediaEntity.class);
    	badPhoto = Mockito.mock(MediaEntity.class);
    	
    	when(goodPhoto.getType()).thenReturn("photo");
    	when(goodPhoto.getMediaURL()).thenReturn("pass");
    	when(badPhoto.getType()).thenReturn("photo");
    	when(badPhoto.getMediaURL()).thenReturn("fail");
    	
    	secondMediaEntities[0] = badPhoto;
    	secondMediaEntities[1] = goodPhoto;
    	secondMediaEntities[2] = goodPhoto;
    	
    	when(testStatus.getMediaEntities()).thenReturn(secondMediaEntities);
    	when(testStatus.getRetweetCount()).thenReturn(33);
    	
    	statuses.add(testStatus);
    	
    	testStatus = Mockito.mock(Status.class);
    	goodPhoto = Mockito.mock(MediaEntity.class);
    	badPhoto = Mockito.mock(MediaEntity.class);
    	
    	when(goodPhoto.getType()).thenReturn("photo");
    	when(goodPhoto.getMediaURL()).thenReturn("pass");
    	when(badPhoto.getType()).thenReturn("photo");
    	when(badPhoto.getMediaURL()).thenReturn("fail");
    	
    	thirdMediaEntities[0] = badPhoto;
    	thirdMediaEntities[1] = badPhoto;
    	thirdMediaEntities[2] = goodPhoto;
    	
    	when(testStatus.getMediaEntities()).thenReturn(thirdMediaEntities);
    	when(testStatus.getRetweetCount()).thenReturn(13);
    	
    	statuses.add(testStatus);
    	
       	
       	String bestPhotoURL = photo.mostTweetedPhoto(statuses);
       	assertTrue(bestPhotoURL.equals("pass"));
    }
    @Test
    public void BestWord() {
    	BestWord best = new BestWord();

    	List<Status> statuses = new ArrayList<Status>();
    	Status testStatus;
    	
    	testStatus = Mockito.mock(Status.class);
    	
    	when(testStatus.getText()).thenReturn("a a b");
    	
    	statuses.add(testStatus);
    	
    	testStatus = Mockito.mock(Status.class);
    	
    	when(testStatus.getText()).thenReturn("c a b");
    	
    	statuses.add(testStatus);
    	
    	assertEquals(best.mostCommonWord( statuses ), 3 );
    }
}
