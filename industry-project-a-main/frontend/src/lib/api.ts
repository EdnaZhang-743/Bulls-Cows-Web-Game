export type NewGameResponse = {
  gameId: string;
  maxRounds: number;
  round: number;
};

export type GuessResponse = {
  bulls: number;
  cows: number;
  round: number;
  maxRounds: number;
  status: "playing" | "won" | "lost";
  message: string;
  answer?: string | null;   // ✅新增
};

const API_BASE = process.env.NEXT_PUBLIC_API_BASE || "http://localhost:8080";

async function postJson<T>(url: string, body: unknown): Promise<T> {
  const res = await fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(body),
  });

  if (!res.ok) {
    const text = await res.text();
    throw new Error(text || `Request failed: ${res.status}`);
  }

  return res.json() as Promise<T>;
}

export function newGame(difficulty: "easy" | "medium" | "hard" = "easy") {
  return postJson<NewGameResponse>(`${API_BASE}/api/game/new`, { difficulty });
}

export function guess(gameId: string, guessStr: string) {
  return postJson<GuessResponse>(`${API_BASE}/api/game/${gameId}/guess`, {
    guess: guessStr,
  });
}
