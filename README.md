# Wordle
## A Java game based on the game Wordle from https://wordlegame.org/

## How does anyone play?

Anyone should be aiming to see the game that looks like this:\
![wordle_start](https://user-images.githubusercontent.com/22280271/213863975-60c5df76-2c08-4fae-a6b9-774a258ec19b.jpg)

First, all the files must be downloaded, where [Wordle.java](https://github.com/bluelightspirit/Wordle/blob/main/Wordle.java) and [words.txt](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) are the most important to be in the same folder. To do that, it is suggested to download the ZIP file from [Releases](https://github.com/bluelightspirit/Wordle/releases) or copy the repo from a console like Git. After that, it is suggested to double check that [Wordle.java](https://github.com/bluelightspirit/Wordle/blob/main/Wordle.java) and [words.txt](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) are in the same folder. Then, [Wordle.java](https://github.com/bluelightspirit/Wordle/blob/main/Wordle.java) should be compiled. After that, [Wordle.java](https://github.com/bluelightspirit/Wordle/blob/main/Wordle.java)'s main method should be ran using any IDE that supports Java such as [IntelliJ](https://www.jetbrains.com/idea/download/) or [BlueJ](https://www.bluej.org/).

Note: It is optional for the player to use their own words list, as long as [words.txt](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) and [Wordle.java](https://github.com/bluelightspirit/Wordle/blob/main/Wordle.java) are in the same folder and the list follows the rule of having a 5-letter word per every line with no spaces.

After that, the player should guess a 5-letter word that's valid from the [words.txt](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) file that they believe is the randomly chosen word that the AI randomly chose as the goal is to match the AI's randomly chosen word to win the game. They will repeat this up to 6 times and be given hints if they match any characters guessed to the AI's randomly chosen word.

The game should look like this after guessing a valid 5-letter word:\
![wordle_guess](https://user-images.githubusercontent.com/22280271/213863980-1f2e3e47-35cd-4022-8352-73d71e189d0f.jpg)

## More specifics on the game

If the player matches the 5-letter chosen word by the AI, they win the game and how many extra tries they had remaining.

If the player does not match the 5-letter chosen word by the AI, they use up one of their 6 tries they had from the beginning, assuming that word guessed is not one of the player's previous guessed words.

If the player matches some letters of the chosen word, they are given hints of what letters match directly to the chosen word first, then are given hints of what letters match to the chosen word, but are in the wrong character position.

If the player runs out of tries, then they have lost the game and are given the chosen word.

If the player enters in a 5-letter word that matches the [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) list, they use one of their 6 tries. If they do not match the first 5 letters of a 5-letter word in [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt), they are asked to guess again and a try is not used.

If the player enters in more than 5 letters, but the first 5 letters they inputted matches a 5-letter word in [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt), they are given an option of "Yes", "Y", "N", or "No" if they wish to use the 5-letter word portion of their word guessed as their actual word guessed. If they say yes, it will use up a try. If they say no, it will not use up a try.

## How does it work?

This is based on using the terminal which uses `System.out.print` from Java.
More specifically, it tries to read in a file in the same folder titled [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt), then loads in the game by using that file, checking for 5-letter words in each line to take in.

Then, the AI randomizes a number based on how many words are in [`words.txt`](https://github.com/bluelightspirit/Wordle/blob/main/words.txt) and picks out a word on the line based on the randomized number, but doesn't show that chosen word to the player.

After that, the AI gives the player an option to enter in a 5-letter word. The player has 6 tries to guess the correct chosen word by the AI.

## What did I learn?

1) How to read a .txt file specifically looking for 5-character lines per line in the file.
2) I can shortcut seraching through an ArrayList by doing .contains().
3) Another way of escaping a while loop in the main method by setting tries to -1 before checking a winner.

## Compiling

This program uses solely Java to compile.
