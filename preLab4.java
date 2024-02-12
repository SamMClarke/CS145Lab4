/*
Sam Clarke
2/12/2024
CS145

This program plays a hangman game with the user (while cheating)
*/

//Creates a new HangmanManager object given a dictionary, a word length, and a max tries.
public HangmanManager(List<String> dictionary, int length, int max)
//If the length of the word is less than 1, or the max tries is less than 0,
//Throw an exception. Set length and max variables to the passed values.
//Run thorugh the given set, removing any words that don't match the length
//And then save the new set in a private variable.

//Gives the remaining words that could be the answer
public Set<String> words()
//Simply returns the Set of words.

//Gives the number of guesses the user has left
public int guessesLeft()
//Simply returns the max variable minus the number of guesses the user has had 
//This is done by counting how many times record() method is called.

//Gives the letters the user has guesses before
public SortedSet<Character> guesses()
//When the user guesses a letter, add it to a new sorted character set,
//Then when this method is called, return that set.

//Shows the classic hangman pattern, with unguessed letters as dashes, and guessed letters filled in
public String pattern()
//If the set of words is empty, throw an exception. Otherwise, read out the pattern for the hangman.
//Printing out dashes (-) for empty spaces, and the letters for correctly guessed letters in the word.

//Lets the user record another guess for the hangman
public int record(char guess)
//Throws exceptions if guessesLeft is less than 1, if the set of words is empty, 
//and if the the letter that was guessed, was already guessed previously. 
//Updates the guessesLeft variable buy using minus minus from max guesses.
//Updates the set of words, removing words that don't have that letter and
//Words that have the letter in a non-common place. Returns the number of that letter
//In the word.