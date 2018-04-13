package com.saviorru.comsserver.domain.model;

import com.saviorru.comsserver.localization.SingletonResourceBundle;
import com.saviorru.comsserver.domain.MatchState;
import com.saviorru.comsserver.exceptions.PlayMatchException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class OneOnOneMatch implements Match {

    private LocalDateTime date;
    private Score score;
    private Player firstSide, secondSide;
    private MatchState matchState = MatchState.NOTPLAYED;
    private Location location;

    public OneOnOneMatch(Player firstSide, Player secondSide, Location location, LocalDateTime date){
        if (firstSide == null || secondSide == null || location == null || date == null)
            throw new NullPointerException("Missing parameter");
        if(firstSide.equals(secondSide)) throw new IllegalArgumentException("Player can't be for 2 sides simultaneously");
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.location = location;
        this.score = new Score();
        this.date = date;
    }

    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public Player getWinner(){
        if (!isPlayed()) throw new PlayMatchException("Match didn't played");
        return (this.score.getPointsFirstSide() > this.score.getPointsSecondSide()) ? this.firstSide : this.secondSide;
    }

    @Override
    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }

    @Override
    public boolean isPlayed() {
        return this.matchState == MatchState.PLAYED;
    }

    @Override
    public Player getFirstSide() {
        return this.firstSide;
    }

    @Override
    public Player getSecondSide() {
        return this.secondSide;
    }

    @Override
    public int getPointsFirstSide() {
        return this.score.getPointsFirstSide();
    }

    @Override
    public int getPointsSecondSide() {
        return this.score.getPointsSecondSide();
    }

    @Override
    public void setPoints(int pointsFirstSide, int pointsSecondSide){
            if (!isPlayed()) this.score.setPoints(pointsFirstSide, pointsSecondSide);
            else throw new PlayMatchException("Match is already played");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneOnOneMatch that = (OneOnOneMatch) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(firstSide, that.firstSide) &&
                Objects.equals(secondSide, that.secondSide) ||
                (Objects.equals(firstSide, that.secondSide) &&
                Objects.equals(secondSide, that.firstSide));
    }
    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {

        String result = "";
        result += SingletonResourceBundle.getResourceBundle().getString("dateMatch") + ": " + getDate().format(DateTimeFormatter.ofPattern("dd.LL.yyyy HH:mm")) + "\n";
        result += SingletonResourceBundle.getResourceBundle().getString("match.location") + ": " + getLocation().getPlace() + "\n";
        result += SingletonResourceBundle.getResourceBundle().getString("statusMatch") + ": ";
        if (isPlayed())
            result += SingletonResourceBundle.getResourceBundle().getString("text.played") + "\n";
        else
            result += SingletonResourceBundle.getResourceBundle().getString("text.notPlayed") + "\n";
        result += getFirstSide().toString() + "  :  " + getPointsFirstSide() + "\n";
        result += getSecondSide().toString() + "  :  " + getPointsSecondSide() + "\n";
        return result;
    }
}
