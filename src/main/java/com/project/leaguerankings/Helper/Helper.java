package com.project.leaguerankings.Helper;

import com.project.leaguerankings.Pojo.ClubObject;
import com.project.leaguerankings.Pojo.GameResultObject;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.project.leaguerankings.Globals.Constants.*;

/* General Helper Class to deal with bulk of the work */
public class Helper {

    private Helper(){
        throw new IllegalStateException("Helper class");
    }

    /* Handles input from stdin into list of lines */
    public static List<String> getInputData(){
        Scanner scanner = new Scanner(System.in);

        List<String> inputData = new ArrayList<>();

        String inputLine;
        while(!(inputLine = scanner.nextLine()).isEmpty()){
            if(inputLine.equalsIgnoreCase(DONE_COMMAND)){
                break;
            }
            else if(inputLine.equalsIgnoreCase(QUIT_COMMAND)){
                scanner.close();
                System.exit(0);
            }
            inputData.add(inputLine.trim());
        }

        return inputData;
    }

    /* Generates a HashMap of clubpoints where key is clubname and value is total clubpoints*/
    public static Map<String, Integer> generateClubPoints(List<String> gameResults){

        Map<String, Integer> clubPointsMap = new HashMap<>();

        for(String line: gameResults){ //reading game results input data into gameresult obj
            String[] game = line.split(",");

            Pattern pattern = Pattern.compile(CLUBNAME_MULTIPLE_WHITESPACE_REGEX); //helps in situations where there is teams with FC or United

            Matcher checkHomeString = pattern.matcher(game[0].trim());
            String[] home = getClubAndPointsArr(checkHomeString, game, 0);


            Matcher checkAwayString = pattern.matcher(game[1].trim());
            String[] away = getClubAndPointsArr(checkAwayString, game, 1);

            GameResultObject gameResultObject = new GameResultObject(home[0], Integer.parseInt(home[1]), away[0], Integer.parseInt(away[1]));

            //add points for winners and draws
            if(gameResultObject.isDraw()){
                updateDraw(clubPointsMap , home[0]);
                updateDraw(clubPointsMap, away[0]);
            }
            else{
                String winner = gameResultObject.getWinner();
                if(clubPointsMap.containsKey(winner)){
                    clubPointsMap.put(winner , clubPointsMap.get(winner) + WIN);
                }
                else{
                    clubPointsMap.put(winner , WIN);
                }

                String loser = gameResultObject.getLoser();
                clubPointsMap.putIfAbsent(loser, 0);

            }

        }

        return clubPointsMap;

    }

    /* Returns a sorted list of clubs by points desc then name asc */
    public static List<ClubObject> sortedClubsByPoints(Map<String, Integer> clubPoints){
        List<ClubObject> clubs = new ArrayList<>();
        for(Map.Entry<String, Integer> e: clubPoints.entrySet()){
            clubs.add(new ClubObject(e.getKey() , e.getValue()));
        }

        Comparator<ClubObject> compareByPoints = Comparator.comparing(ClubObject::getPoints).reversed()
                .thenComparing(ClubObject::getName);

        return clubs.stream().sorted(compareByPoints).collect(Collectors.toList());

    }

    /* Prints sorted list of clubs in the desired output format */
    public static void prettyPrint(List<ClubObject> sorted){
        int pos = 1; //stores current rank
        int prevPoints = -1; //stores previous points
        int counter = 0; //stores position counter to skip by when previous clubs have the same points/position
        for(ClubObject obj: sorted){
            String ptsSuffix = "pts";
            if(obj.getPoints() == 1){
                ptsSuffix = "pt";
            }

            if(prevPoints == obj.getPoints()){
                counter++;
                pos = pos - 1;
            }else{
                pos = pos+counter;
                counter = 0;
            }

            prevPoints = obj.getPoints();

            System.out.printf("%d. %s, %d %s%n", pos, obj.getName(), obj.getPoints(), ptsSuffix);

            pos++;

        }
    }

    private static String []getClubAndPointsArr(Matcher checkString, String[] game, int indx){
        String[] clubAndPoints;
        if(checkString.find()){
            clubAndPoints = game[indx].trim().split(" ");
            clubAndPoints[0] = clubAndPoints[0] + " " + clubAndPoints[1];
            clubAndPoints[1] = clubAndPoints[clubAndPoints.length - 1];
        }else{
            clubAndPoints = game[indx].trim().split(" ");
        }

        return clubAndPoints;
    }

    private static void updateDraw(Map<String, Integer> clubPointsMap , String clubAndPoints){
        if(clubPointsMap.containsKey(clubAndPoints)){
            clubPointsMap.put(clubAndPoints , clubPointsMap.get(clubAndPoints) + DRAW);
        }else{
            clubPointsMap.put(clubAndPoints , 1);
        }
    }





}
