package com.example.leaguerankings;

import com.project.leaguerankings.Abstracts.DefaultAbstract;
import com.project.leaguerankings.Helper.Helper;
import com.project.leaguerankings.Pojo.ClubObject;
import com.project.leaguerankings.Pojo.GameResultObject;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class LeagueRankingsTest extends DefaultAbstract {


    @Test
    public void testGenerateClubPoints(){
        List<String> gameResultsTest = new ArrayList<>();
        gameResultsTest.add("Lions 3, Snakes 3");
        gameResultsTest.add("Tarantulas 1, FC Awesome 0");
        gameResultsTest.add("Lions 1, FC Awesome 1");
        gameResultsTest.add("Lions 4, Grouches 0");

        Map<String, Integer> clubPointsMap = Helper.generateClubPoints(gameResultsTest);

        logger.info("Club Points HashMap: "+Helper.generateClubPoints(gameResultsTest).toString());
        assertEquals(3, (int) clubPointsMap.get("Tarantulas"));

    }

    @Test
    public void testSortedClubsByPoints(){
        Map<String, Integer> clubPoints = new HashMap<>();
        clubPoints.put("Team B" , 3);
        clubPoints.put("Team A" , 3);
        clubPoints.put("Team C" , 9);
        clubPoints.put("Team D" , 0);

        List<ClubObject> sorted = Helper.sortedClubsByPoints(clubPoints);

        logger.info("Sorted Clubs by Points: "+ Arrays.toString(sorted.toArray()));
        assertEquals("Team C", Helper.sortedClubsByPoints(clubPoints).get(0).getName());

    }

    @Test
    public void testGameResultsWinner(){
        GameResultObject gameResultObject = new GameResultObject("Team A" , 3 , "Team B" , 2);
        logger.info("Team A 3, Team B 2 : "+gameResultObject.getWinner());
        assertEquals("Team A", gameResultObject.getWinner());
    }

    @Test
    public void testGameResultsDraw(){
        GameResultObject gameResultObject = new GameResultObject("Team C" , 1 , "Team B" , 1);
        logger.info("Team A 1, Team B 1 : Draw");
        assertTrue(gameResultObject.isDraw());
    }




}
