package lv.dium.riskclient;

import java.util.ArrayList;

public class GameRisk {
    public Number id;
    public String scenarioName;
    public Integer maxPlayers;

    public String currentPlayer;
    public String currentPhase;

    public ArrayList<GameArea> areas = new ArrayList<GameArea>();
    public ArrayList<GamePlayer> players = new ArrayList<GamePlayer>();

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(String currentPhase) {
        this.currentPhase = currentPhase;
    }

    public ArrayList<GameArea> getAreas() {
        return areas;
    }

    public void setAreas(ArrayList<GameArea> areas) {
        this.areas = areas;
    }

    public ArrayList<GamePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<GamePlayer> players) {
        this.players = players;
    }
}
