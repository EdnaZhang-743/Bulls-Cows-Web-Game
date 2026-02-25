package project.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.api.dto.*;
import project.api.model.GameSession;
import project.api.service.GameService;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = {"http://localhost:3000"})
public class GameController {

    private final GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("/new")
    public NewGameResponse newGame(@RequestBody(required = false) NewGameRequest req) {
        String diff = (req == null || req.getDifficulty() == null) ? "easy" : req.getDifficulty();
        GameSession s = service.newGame(diff);
        return new NewGameResponse(s.getGameId(), s.getMaxRounds(), s.getRound());
    }

    @PostMapping("/{gameId}/guess")
    public GuessResponse guess(@PathVariable String gameId, @RequestBody GuessRequest req) {
        return service.guess(gameId, req.getGuess());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBad(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
