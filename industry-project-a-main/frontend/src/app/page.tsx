"use client";

import { useMemo, useState } from "react";
import { guess as apiGuess, newGame as apiNewGame } from "@/lib/api";
import type { GuessResponse } from "@/lib/api";

type GuessRecord = GuessResponse & { guess: string };

function isValidGuess(input: string) {
  if (!/^\d{4}$/.test(input)) return false;
  return new Set(input.split("")).size === 4;
}

export default function Page() {
  const [loading, setLoading] = useState(false);

  const [gameId, setGameId] = useState<string | null>(null);
  const [maxRounds, setMaxRounds] = useState<number>(7);
  const [round, setRound] = useState<number>(1);
  const [status, setStatus] = useState<"idle" | "playing" | "won" | "lost">("idle");

  const [difficulty, setDifficulty] = useState<"easy" | "medium" | "hard">("easy");
  const [guess, setGuess] = useState("");
  const [error, setError] = useState<string | null>(null);
  const [modalOpen, setModalOpen] = useState(false);
  const [revealAnswer, setRevealAnswer] = useState<string | null>(null);

  const [history, setHistory] = useState<GuessRecord[]>([]);

  const canSubmit = useMemo(() => {
    return status === "playing" && isValidGuess(guess) && !loading;
  }, [status, guess, loading]);

  async function startNewGame() {
    setError(null);
    setLoading(true);
    try {
      const res = await apiNewGame(difficulty);
      setGameId(res.gameId);
      setMaxRounds(res.maxRounds);
      setRound(res.round);
      setStatus("playing");
      setHistory([]);
      setGuess("");
    } catch (e: any) {
      setError(e?.message ?? "Failed to start new game.");
    } finally {
      setLoading(false);
    }
      setModalOpen(false);
      setRevealAnswer(null);
  }

  async function submitGuess() {
    if (!gameId) return;
    setError(null);

    const g = guess.trim();
    if (!isValidGuess(g)) {
      setError("Invalid guess: must be 4 digits with no repeats.");
      return;
    }

    setLoading(true);
    try {
      const res = await apiGuess(gameId, g);

      setRound(res.round);
      setMaxRounds(res.maxRounds);
      setStatus(res.status);

      setHistory((prev) => [{ ...res, guess: g }, ...prev]);
      setGuess("");

      // ✅ Game finished => open modal and show answer (if provided by backend)
      if (res.status === "won" || res.status === "lost") {
      setRevealAnswer(res.answer ?? null);
      setModalOpen(true);
    }
    } catch (e: unknown) {
      const msg =
        e instanceof Error ? e.message :
        typeof e === "string" ? e :
        "Guess failed.";
      setError(msg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <main style={{ maxWidth: 920, margin: "0 auto", padding: 24, fontFamily: "ui-sans-serif, system-ui" }}>
      <header style={{ display: "flex", justifyContent: "space-between", alignItems: "center", gap: 12 }}>
        <div>
          <h1 style={{ margin: 0, fontSize: 28 }}>Bulls & Cows (Single Player)</h1>
          <p style={{ marginTop: 6, color: "#666" }}>
            Guess the 4-digit secret code (no repeated digits). Frontend calls your Java API.
          </p>
        </div>

        <button
          onClick={startNewGame}
          disabled={loading}
          style={{
            padding: "10px 14px",
            borderRadius: 12,
            border: "1px solid #111",
            background: "#111",
            color: "#fff",
            fontWeight: 700,
            cursor: loading ? "not-allowed" : "pointer",
          }}
        >
          {status === "playing" ? "Restart" : "New Game"}
        </button>
      </header>

      <section style={{ marginTop: 18, padding: 16, border: "1px solid #eee", borderRadius: 16 }}>
        <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", gap: 10, flexWrap: "wrap" }}>
          <div>
            <strong>Status:</strong>{" "}
            {status === "idle" && "Not started"}
            {status === "playing" && "Playing"}
            {status === "won" && "🎉 You won!"}
            {status === "lost" && "😢 You lost!"}
            {status === "lost" && history[0]?.answer && (
              <div style={{ marginTop: 8, padding: 10, borderRadius: 12, background: "#fff7e6", border: "1px solid #ffe2ad" }}>
                <strong>Answer:</strong> <code style={{ fontSize: 16 }}>{history[0].answer}</code>
              </div>
            )}
          </div>

          <div>
            <strong>Round:</strong> {Math.min(round, maxRounds)}/{maxRounds}
          </div>

          <div style={{ display: "flex", gap: 8, alignItems: "center" }}>
            <span style={{ color: "#666", fontSize: 13 }}>Difficulty</span>
            <select
              value={difficulty}
              onChange={(e) => setDifficulty(e.target.value as any)}
              disabled={status === "playing" || loading}
              style={{ padding: "8px 10px", borderRadius: 10, border: "1px solid #ddd" }}
            >
              <option value="easy">easy</option>
              <option value="medium">medium</option>
              <option value="hard">hard</option>
            </select>
          </div>
        </div>

        {gameId && (
          <div style={{ marginTop: 10, color: "#888", fontSize: 12 }}>
            gameId: <code>{gameId}</code>
          </div>
        )}

        <div style={{ display: "flex", gap: 10, marginTop: 14 }}>
          <input
            value={guess}
            onChange={(e) => setGuess(e.target.value)}
            placeholder="Enter 4 digits (e.g. 1234)"
            inputMode="numeric"
            disabled={status !== "playing" || loading}
            style={{
              flex: 1,
              padding: "12px 14px",
              borderRadius: 12,
              border: "1px solid #ddd",
              fontSize: 16,
            }}
            onKeyDown={(e) => {
              if (e.key === "Enter") submitGuess();
            }}
          />
          <button
            onClick={submitGuess}
            disabled={!canSubmit}
            style={{
              padding: "12px 14px",
              borderRadius: 12,
              border: "1px solid #ddd",
              background: canSubmit ? "#fff" : "#f6f6f6",
              cursor: canSubmit ? "pointer" : "not-allowed",
              fontWeight: 800,
            }}
          >
            Guess
          </button>
        </div>

        <div style={{ marginTop: 10, fontSize: 13, color: isValidGuess(guess) || guess === "" ? "#888" : "#c00" }}>
          Rule: Bulls = correct digit + correct position. Cows = correct digit + wrong position. (4 unique digits).
        </div>

        {error && (
          <div style={{ marginTop: 10, padding: 12, borderRadius: 12, background: "#fff3f3", border: "1px solid #ffd0d0" }}>
            <strong style={{ color: "#c00" }}>Error:</strong> {error}
          </div>
        )}
      </section>

      <section style={{ marginTop: 18 }}>
        <h2 style={{ margin: "0 0 10px 0", fontSize: 18 }}>History</h2>

        {history.length === 0 ? (
          <div style={{ color: "#888" }}>
            {status === "idle" ? "Click New Game to start." : "No guesses yet."}
          </div>
        ) : (
          <div style={{ display: "grid", gap: 10 }}>
            {history.map((h, idx) => (
              <div
                key={`${h.guess}-${idx}`}
                style={{
                  padding: 14,
                  borderRadius: 14,
                  border: "1px solid #eee",
                  display: "flex",
                  justifyContent: "space-between",
                  alignItems: "center",
                }}
              >
                <div style={{ display: "flex", gap: 12, alignItems: "baseline" }}>
                  <div style={{ fontWeight: 900, letterSpacing: 2 }}>{h.guess}</div>
                  <div style={{ color: "#666" }}>
                    ✅ Bulls: {h.bulls} &nbsp;|&nbsp; 🐄 Cows: {h.cows}
                  </div>
                </div>
                <div style={{ color: "#888", fontSize: 12 }}>Round {idx + 1}</div>
              </div>
            ))}
          </div>
        )}
      </section>
    </main>
  );
}
