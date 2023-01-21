# Wordle
## A Java game based on the game Wordle from https://wordlegame.org/

## How does anyone play?

If the user matches the 5-letter chosen word by the AI, they win the game and how many extra tries they had remaining.

If the user does not match the 5-letter chosen word by the AI, they use up one of their 6 tries they had from the beginning, assuming that word guessed is not one of the user's previous guessed words.

If the user matches some letters of the chosen word, they are given hints of what letters match directly to the chosen word first, then are given hints of what letters match to the chosen word, but are in the wrong character position.

If the user runs out of tries, then they have lost the game and are given the chosen word.

The game should look like this:\
![wordle_start](https://user-images.githubusercontent.com/22280271/213863975-60c5df76-2c08-4fae-a6b9-774a258ec19b.jpg)
![wordle_guess](https://user-images.githubusercontent.com/22280271/213863980-1f2e3e47-35cd-4022-8352-73d71e189d0f.jpg)


## More specifics on the game

If the user enters in a 5-letter word that matches the [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) list, they use one of their 6 tries. If they do not match the first 5 letters of a 5-letter word in [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt), they are asked to guess again and a try is not used.

If the user enters in more than 5 letters, but the first 5 letters they inputted matches a 5-letter word in [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt), they are given an option of "Yes", "Y", "N", or "No" if they wish to use the 5-letter word portion of their word guessed as their actual word guessed. If they say yes, it will use up a try. If they say no, it will not use up a try.

## How does it work?

This is based on using the terminal which uses `System.out.print` from Java.
More specifically, it tries to read in a file in the same folder titled [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt), then loads in the game by using that file, checking for 5-letter words in each line to take in.

Then, the AI randomizes a number based on how many words are in [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) and picks out a word on the line based on the randomized number, but doesn't show that chosen word to the user.

After that, the AI gives the user an option to enter in a 5-letter word. The user has 6 tries to guess the correct chosen word by the AI.

## What did I learn?

1) How to read a .txt file specifically looking for 5-character lines per line in the file.
2) I can shortcut seraching through an ArrayList by doing .contains().
3) Another way of escaping a while loop in the main method by setting tries to -1 before checking a winner.

## Compiling

This program uses solely Java to compile.
