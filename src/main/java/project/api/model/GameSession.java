package project.api.model;

public class GameSession {
    private final String gameId;
    private final String secret;
    private final int maxRounds;

    private int round;        // 1-based
    private String status;    // playing / won / lost
    private boolean finished;

    public GameSession(String gameId, String secret, int maxRounds) {
        this.gameId = gameId;
        this.secret = secret;
        this.maxRounds = maxRounds;
        this.round = 1;
        this.status = "playing";
        this.finished = false;
    }

    public String getGameId() { return gameId; }
    public String getSecret() { return secret; }
    public int getMaxRounds() { return maxRounds; }

    public int getRound() { return round; }
    public void nextRound() { this.round++; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public boolean isFinished() { return finished; }
    public void setFinished(boolean finished) { this.finished = finished; }
}
