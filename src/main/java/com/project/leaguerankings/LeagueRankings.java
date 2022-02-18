package com.project.leaguerankings;


import com.project.leaguerankings.Helper.Helper;
import com.project.leaguerankings.Pojo.ClubObject;

import java.util.*;

import static com.project.leaguerankings.Globals.Constants.*;

/* Driver class */
public class LeagueRankings{

    public LeagueRankings(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 's' to start ('q' to quit)");
        String input = scanner.nextLine().toLowerCase().trim();

        while(!input.equalsIgnoreCase(QUIT_COMMAND) && !input.isEmpty()){
            switch (input.toUpperCase()){
                case START_COMMAND:
                case YES_COMMAND:
                    System.out.println("1. Copy and paste input data for match results and press enter: ");
                    System.out.println("2. Enter 'd' when done ('q' to quit): ");

                    List<String> inputData = Helper.getInputData();

                    Map<String, Integer> clubPoints = Helper.generateClubPoints(inputData);

                    List<ClubObject> sorted = Helper.sortedClubsByPoints(clubPoints);

                    Helper.prettyPrint(sorted);

                    break;
                default:
                    scanner.close();
                    System.exit(0);
                    break;

            }

            System.out.println("Again? (y/n)");
            input = scanner.nextLine().toLowerCase().trim();
        }

    }

    public static void main(String[] args){
        new LeagueRankings();
    }
}