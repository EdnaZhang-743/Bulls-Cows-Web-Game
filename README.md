# Bulls & Cows Web Game (Full Stack)

A full-stack **Bulls & Cows** single-player web game.

- **Backend:** Java Spring Boot (Gradle) REST API
- **Frontend:** Next.js (TypeScript) web UI

The frontend calls the Java API to create a new game and validate guesses.

---

## Features

### Gameplay
- Bulls & Cows rules (4-digit secret code, **no repeated digits**)
  - **Bulls** = correct digit in the correct position
  - **Cows** = correct digit in the wrong position
- Multiple difficulty levels (e.g., easy/medium/hard)
- Limited rounds per game (UI shows current round and max rounds)
- Game history list (each guess with bulls/cows feedback)
- “New Game” button to reset and start again

### Full-stack integration
- Frontend communicates with backend via REST API
- Environment-based API base URL (local development)

---

## Prerequisites

### Backend
- **Java 17** (JDK 17)

Check:
```bash
java -version
```
### Frontend
- **Node.js 18+** and npm
Check:
```bash
node -v
npm -v
```
## Running Locally (Development)

You need two terminals: one for backend, one for frontend.

### 1) Start Backend (Spring Boot)

Open a terminal in the project root (where build.gradle is):

Windows (PowerShell / CMD)
```bash
.\gradlew.bat clean bootRun
```
macOS / Linux
```bash
./gradlew clean bootRun
```
Backend default URL (example):
http://localhost:8080

If your backend runs on a different port, update the frontend env config accordingly.

### 2) Configure Frontend API Base URL

Create or edit this file:

frontend/.env.local

Example:
```bash
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
```
After changing .env.local, you must restart the frontend dev server.

### 3) Start Frontend (Next.js)

Open another terminal:
```bash
cd frontend
npm install
npm run dev
```
Frontend default URL:
http://localhost:3000

## How to Play

### 1.Open the frontend in your browser:
http://localhost:3000

### 2.Click New Game to start.

### 3.Enter a 4-digit guess (no repeated digits).

### 4.Press Guess to submit.

### 5.The game shows:

- Bulls and Cows
- current Round
- full History of guesses

### 6.Win by getting 4 bulls within the allowed rounds.

## Troubleshooting

### Frontend starts but shows a warning about benchmarking file I/O

You may see a message like:
- Failed to benchmark file I/O ... (os error 3)

If the UI loads normally, this is usually a non-blocking warning on Windows and can be ignored.

If you want to avoid Turbopack in dev mode, you can run:
```bash
cd frontend
npx next dev --no-turbo
```
### Frontend cannot call backend (API errors)

- Ensure backend is running on the expected port (commonly 8080)
- Ensure frontend/.env.local points to the correct backend URL:
- - NEXT_PUBLIC_API_BASE_URL=http://localhost:8080
- Restart the frontend dev server after changing .env.local

### Port already in use

If 3000 or 8080 is in use, stop the existing process or change the port:

Frontend: set PORT before running (PowerShell example)
```bash
$env:PORT=3001; npm run dev
```

## Build (Optional)
### Backend build
```bash
.\gradlew.bat clean build
```
### Frontend production build
```bash
cd frontend
npm run build
npm run start
```

## License

This project is intended for learning and portfolio demonstration.
Add a LICENSE file (e.g., MIT) if you want an official open-source license.
