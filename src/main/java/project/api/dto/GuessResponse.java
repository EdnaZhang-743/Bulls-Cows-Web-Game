package project.api.dto;

public class GuessResponse {
    private int bulls;
    private int cows;
    private int round;
    private int maxRounds;
    private String status;
    private String message;

    private String answer; // ✅ must be inside the class

    public GuessResponse(int bulls, int cows, int round, int maxRounds, String status, String message) {
        this.bulls = bulls;
        this.cows = cows;
        this.round = round;
        this.maxRounds = maxRounds;
        this.status = status;
        this.message = message;
        this.answer = null;
    }

    public int getBulls() { return bulls; }
    public int getCows() { return cows; }
    public int getRound() { return round; }
    public int getMaxRounds() { return maxRounds; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
}