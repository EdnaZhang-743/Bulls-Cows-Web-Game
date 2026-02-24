# 6-Letter Word Game (Wordle-style)

A lightweight Wordle-style guessing game built with vanilla **HTML/CSS/JavaScript**.  
Guess the hidden **6-letter** word within a limited number of attempts. The game supports both **physical keyboard input** and an **on-screen keyboard**, and saves progress using **localStorage**.

---

## Features
- Wordle-style **6-letter** guessing gameplay with instant feedback:
  - **Correct** (right letter, right position)
  - **Present** (letter exists, wrong position)
  - **Absent** (letter not in the word)
- Supports **physical keyboard** and **on-screen keyboard**
- Input rules:
  - Only allows submission when **6 letters** are entered
  - Supports **Backspace** to delete and **Enter** to submit
- Validates guesses using an online dictionary API
- Saves game progress automatically using **localStorage** (refresh won’t lose your current game)

---

## Tech Stack
- **HTML5**
- **CSS3** (Flexbox layout)
- **JavaScript (Vanilla)**: DOM manipulation, events
- **Fetch API** for REST requests
- **localStorage** for persistence

---

## API Used
This project uses the public word API from **words.trex-sandwich.com**:

- Random 6-letter word (target word):  
  `https://words.trex-sandwich.com/?count=1&length=6`

- Validate a guessed word (example):  
  `https://words.trex-sandwich.com/<word>`

> Note: An internet connection is required to fetch and validate words. If the API is unavailable, gameplay may be affected.

---

## How to Run (Local)

### Option 1: Open directly (quick start)
1. Download or clone this repository.
2. Open `index.html` in your browser.

✅ This works in most browsers.

### Option 2: Run with a local server (recommended)
Using a local server can avoid browser restrictions in some setups.

**Using VS Code Live Server**
1. Install the **Live Server** extension in VS Code.
2. Right-click `index.html` → **Open with Live Server**.

**Using Python**
```bash
# from the project root directory
python -m http.server 8000


## Features
- Type letters on your keyboard or click the on-screen keys.
- Press Enter to submit a guess (only after entering 6 letters).
- Press Backspace to remove the last letter.
- The board and keyboard will update colors after each guess.
- Your progress is saved automatically.

---

## Tech Stack
- **HTML5**
- **CSS3** (Flexbox layout)
- **JavaScript (Vanilla)**: DOM manipulation, events
- **Fetch API** for REST requests
- **localStorage** for persistence

---

## API Used
This project uses the public word API from **words.trex-sandwich.com**:

- Random 6-letter word (target word):  
  `https://words.trex-sandwich.com/?count=1&length=6`

- Validate a guessed word (example):  
  `https://words.trex-sandwich.com/<word>`

> Note: An internet connection is required to fetch and validate words. If the API is unavailable, gameplay may be affected.
