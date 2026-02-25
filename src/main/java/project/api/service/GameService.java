package project.api.service;

import org.springframework.stereotype.Service;
import project.CodeHelpers;
import project.api.dto.GuessResponse;
import project.api.model.GameSession;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final Map<String, GameSession> sessions = new ConcurrentHashMap<>();

    private String generateSecret() {
        while (true) {
            int n = (int) (Math.random() * 10000);
            String s = String.format("%04d", n);
            if (CodeHelpers.isValidInput(s)) return s;
        }
    }

    public GameSession newGame(String difficulty) {
        String id = UUID.randomUUID().toString();
        String secret = generateSecret();

        int maxRounds = switch ((difficulty == null ? "" : difficulty.toLowerCase())) {
            case "easy" -> 10;
            case "medium" -> 8;
            case "hard" -> 7;
            default -> 7;
        };

        GameSession s = new GameSession(id, secret, maxRounds);
        sessions.put(id, s);
        return s;
    }

    public GuessResponse guess(String gameId, String guess) {
        GameSession s = sessions.get(gameId);
        if (s == null) throw new IllegalArgumentException("Game not found.");

        // already finished
        if (s.isFinished()) {
            GuessResponse res = new GuessResponse(
                    0, 0, s.getRound(), s.getMaxRounds(), s.getStatus(),
                    "Game already finished. Please start a new game."
            );
            // return answer when finished
            if ("won".equals(s.getStatus()) || "lost".equals(s.getStatus())) {
                res.setAnswer(s.getSecret());
            }
            return res;
        }

        if (!CodeHelpers.isValidInput(guess)) {
            throw new IllegalArgumentException("Invalid guess. Must be 4 digits with no repeats.");
        }

        int[] bc = CodeHelpers.compareTo(guess, s.getSecret());
        int bulls = bc[0];
        int cows = bc[1];
        String msg = bulls + " Bull and " + cows + " Cow";

        // update session status/round first
        if (bulls == 4) {
            s.setFinished(true);
            s.setStatus("won");
        } else if (s.getRound() >= s.getMaxRounds()) {
            s.setFinished(true);
            s.setStatus("lost");
        } else {
            s.nextRound();
        }

        GuessResponse res = new GuessResponse(
                bulls, cows, s.getRound(), s.getMaxRounds(), s.getStatus(), msg
        );

        if (s.isFinished()) {
            res.setAnswer(s.getSecret());
        }

        return res;
    }
}