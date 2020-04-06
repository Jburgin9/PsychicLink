package org.quietlip.guesswhatwho;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Game {
    @PrimaryKey
    private int round;

    @ColumnInfo(name = "user_guess")
    private int userGuess;

    @ColumnInfo(name = "cpu_guess")
    private int cpuGuess;

    @ColumnInfo(name = "wins")
    private int wins;

    @ColumnInfo(name = "result")
    private int result;

    public Game(int userGuess, int cpuGuess, int wins, int result) {
        this.userGuess = userGuess;
        this.cpuGuess = cpuGuess;
        this.wins = wins;
        this.result = result;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(int userGuess) {
        this.userGuess = userGuess;
    }

    public int getCpuGuess() {
        return cpuGuess;
    }

    public void setCpuGuess(int cpuGuess) {
        this.cpuGuess = cpuGuess;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
