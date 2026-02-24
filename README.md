# 6-Letter Word Game (Wordle-style)

A lightweight Wordle-style guessing game built with vanilla **HTML/CSS/JavaScript**.  
Guess the hidden **6-letter** word within a limited number of attempts. The game supports both **physical keyboard input** and an **on-screen keyboard**, and saves progress using **localStorage**.

---

## Features
- **6-letter word** guessing gameplay (Wordle-style feedback)
- **On-screen keyboard** + **physical keyboard** support
- Input rules: only submit when 6 letters are entered; supports Backspace and Enter
- Real-time tile feedback:
  - **Correct**: right letter, right position
  - **Present**: letter exists but in a different position
  - **Absent**: letter not in the word
- Uses a public word API for:
  - **Random target word** generation
  - **Dictionary validation** for guesses
- **Progress persistence** via `localStorage` (refresh won’t lose the current game)

---

## Tech Stack
- **HTML5**
- **CSS3** (Flexbox layout)
- **JavaScript (Vanilla)**: DOM manipulation, event handling
- **Fetch API** for REST requests
- **localStorage** for saving game state

---

## API Used
This project uses the public word API from **words.trex-sandwich.com**:

- Get a random 6-letter word:  
  `https://words.trex-sandwich.com/?count=1&length=6`

- Validate a guessed word (example):  
  `https://words.trex-sandwich.com/<word>`

> Note: The game requires an internet connection to fetch and validate words.

---

## Project Structure
.
├── index.html
├── css/
│ └── style.css
└── js/
└── app.js

---

## How to Run (Local)

### Option 1: Open directly (quick start)
1. Download or clone this repo.
2. Open `index.html` in your browser.

✅ This is enough for most browsers.

### Option 2: Run with a local server (recommended)
Some environments work better with a local server.

**Using VS Code Live Server**
1. Install the **Live Server** extension.
2. Right-click `index.html` → **Open with Live Server**.

**Using Python**
```bash
# from the project root
python -m http.server 8000
Then open: http://localhost:8000

How to Play

Type letters on your keyboard or click the on-screen keys.

Press Enter to submit a guess (only when 6 letters are filled).

Press Backspace to delete a letter.

The board and keyboard colors update after each guess.

The game saves your progress automatically.

Notes

Word validation and random word generation depend on the external API. If the API is unavailable, gameplay may be affected.

Game state is stored in the browser via localStorage. Clearing browser data will reset your progress.

License

This project is for learning and demonstration purposes.
