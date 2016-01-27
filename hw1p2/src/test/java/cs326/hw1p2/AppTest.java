package cs326.hw1p2;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import twitter4j.Status;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import cs326.hw1p2.Analysis.BestTweets;
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
}
