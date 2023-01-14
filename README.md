# Wordle
## A Java game based on the game Wordle from https://wordlegame.org/

## How does it work?

This is based on using 'System.out.print' from Java.
More specifically, it tries to read in a file in the same folder titled `words.txt`, then loads in the game by using that file, checking for 5-letter words in each line to take in.

Then, the AI randomizes a number based on how many words are in `words.txt` and picks out a word on the line based on the randomized number, but doesn't show that chosen word to the user.

After that, the AI gives the user an option to enter in a 5-letter word. The user has 6 tries to guess the correct chosen word by the AI.

## How does anyone play?

If the user matches the 5-letter chosen word by the AI, they win the game and how many extra tries they had remaining.

If the user does not match the 5-letter chosen word by the AI, they use up one of their 6 tries they had from the beginning, assuming that word guessed is not one of the user's previous guessed words.

If the user matches some letters of the chosen word, they are given hints of what letters match directly to the chosen word first, then are given hints of what letters match to the chosen word, but are in the wrong character position.

If the user runs out of tries, then they have lost the game and are given the chosen word.

## More specifics on the game

If the user enters in a 5-letter word that matches the `words.txt` list, they use one of their 6 tries. If they do not match the first 5 letters of a 5-letter word in `words.txt`, they are asked to guess again and a try is not used.

If the user enters in more than 5 letters, but the first 5 letters they inputted matches a 5-letter word in `words.txt`, they are given an option of "Yes", "Y", "N", or "No" if they wish to use the 5-letter word portion of their word guessed as their actual word guessed. If they say yes, it will use up a try. If they say no, it will not use up a try.

## Compiling

This program uses solely Java to compile.
