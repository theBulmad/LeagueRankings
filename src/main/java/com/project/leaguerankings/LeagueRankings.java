package com.project.leaguerankings;


import com.project.leaguerankings.Helper.Helper;
import com.project.leaguerankings.Pojo.ClubObject;

import java.util.*;

/* Driver class */
public class LeagueRankings{

    public LeagueRankings(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 's' to start ('q' to quit)");
        String input = scanner.nextLine().toLowerCase().trim();

        while(!input.equals("q") && !input.isEmpty()){
            switch (input){
                case "s":
                case "y":
                    System.out.println("1. Copy and paste input data for match results and press enter: ");
                    System.out.println("2. Enter 'd' when done ('q' to quit): ");

                    List<String> inputData = Helper.getInputData();
                    Map<String, Integer> clubPoints = Helper.generateClubPoints(inputData);

                    List<ClubObject> sorted = Helper.sortedClubsByPoints(clubPoints);

                    Helper.PrettyPrint(sorted);
                    break;

                case "q":
                case "n":
                    scanner.close();
                    System.exit(0);

            }

            System.out.println("Again? (y/n)");
            input = scanner.nextLine().toLowerCase().trim();
        }

    }

    public static void main(String[] args){
        new LeagueRankings();
    }
}