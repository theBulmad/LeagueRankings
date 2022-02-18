package com.project.leaguerankings.Globals;

public class Constants {

    private Constants(){
        throw new IllegalStateException("Constants Class");
    }

    public static final String QUIT_COMMAND = "Q";
    public static final String YES_COMMAND = "Y";
    public static final String START_COMMAND = "S";
    public static final String DONE_COMMAND = "D";
    public static final int WIN = 3;
    public static final int DRAW = 1;
    public static final String CLUBNAME_MULTIPLE_WHITESPACE_REGEX = "[a-zA-Z]+\\s+[a-zA-Z]+ \\d"; //FC Awesome 3 - more than 1 space when split by whitespace
}
