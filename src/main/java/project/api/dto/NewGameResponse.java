package project.api.dto;

public class NewGameResponse {
    private String gameId;
    private int maxRounds;
    private int round;

    public NewGameResponse(String gameId, int maxRounds, int round) {
        this.gameId = gameId;
        this.maxRounds = maxRounds;
        this.round = round;
    }

    public String getGameId() { return gameId; }
    public int getMaxRounds() { return maxRounds; }
    public int getRound() { return round; }
}
