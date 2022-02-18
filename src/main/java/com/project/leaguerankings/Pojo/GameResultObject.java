package com.project.leaguerankings.Pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class GameResultObject {

    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private boolean isDraw = false;

    public GameResultObject(String homeTeam, int homeScore, String awayTeam, int awayScore){
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
            this.homeScore = homeScore;
            this.awayScore = awayScore;
            if(homeScore == awayScore){
                isDraw = true;
            }

    }

    public String getWinner(){
        return this.homeScore > this.awayScore ? this.homeTeam : this.awayTeam;
    }

    public String getLoser(){
        return this.homeScore < this.awayScore ? this.homeTeam : this.awayTeam;
    }

    @Override
    public String toString() {
        return "GameResultObject{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                ", winner='" + getWinner() + '\'' +
                ", loser='" + getLoser() + '\'' +
                ", isDraw=" + isDraw +
                '}';
    }
}
