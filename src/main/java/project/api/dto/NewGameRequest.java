package project.api.dto;

public class NewGameRequest {
    private String difficulty; // 先留着，MVP可不使用

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
}
