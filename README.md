# Bulls and Cows

A classic number guessing game where the player attempts to guess a secret code.

## Game Rules

The game generates a secret code using unique symbols. The player tries to guess this code by entering symbol combinations.

### Scoring System

- **Bull**: Correct symbol in the correct position
- **Cow**: Correct symbol in the wrong position

### Example
```
Secret code: 4271
Your guess:  1234

Result: 1 bull, 2 cows
- Bull: The digit 2 is at position 3 (correct)
- Cows: The digits 1 and 4 are in the secret code but at wrong positions
```

## Installation

1. Clone the repository:
   ```bash
   git clone [repository-url]
   cd bulls-and-cows
   ```

2. Compile and run:
   ```bash
   javac bullscows/*.java
   java bullscows.Main
   ```

## Gameplay

1. Enter the desired length of the secret code (1-36)
2. Enter the number of possible symbols (must be >= code length, max 36)
3. The game generates a random code using symbols 0-9 and a-z
4. Make your guesses and receive feedback
5. Continue until you guess the correct code

## Requirements

- Java 8 or higher
- No additional libraries required

## Features

- Configurable code length (1-36 characters)
- Configurable symbol set (0-9, a-z up to 36 total symbols)
- Input validation with error handling
- Turn counter
- Proper grammar for singular/plural bulls and cows

## Project Structure

```
bullscows/
├── Main.java        # Entry point
├── Grader.java      # Game logic and flow control
└── README.md        # This file
```

## Symbol Range

- **Numbers only**: 0-9 (for up to 10 possible symbols)
- **Numbers + Letters**: 0-9, a-z (for 11-36 possible symbols)
