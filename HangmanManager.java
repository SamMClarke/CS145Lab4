import java.util.*;


//NEVER USE GUESS IN generatePattern() method


public class HangmanManager
{
    private int attemptsLeft;
    private Set<String> words;
    private Set<Character> guessesMade;
    private String currentPattern;

    private static final char EMPTY_CHAR = '-';

    public HangmanManager(List<String> dictionary, int length, int max)
    {
        if (length < 1 || max < 0)
        {
            throw new IllegalArgumentException("Length should be longer than 1 "
                                            + "and max must be bigger than 0.");
        }
        this.attemptsLeft = max;
        this.words = new TreeSet<>();
        this.guessesMade = new TreeSet<>();
        currentPattern = "";
        for (String word : dictionary)
        {
            if (word.length() == length)
            {
                words.add(word);
            }
        }
        for (int i = 0; i < length; i++)
        {
            this.currentPattern += EMPTY_CHAR;
        }
    }

    public Set<String> words()
    {
        return Collections.unmodifiableSet(words);
    }

    public int guessesLeft()
    {
        return attemptsLeft;
    }

    public Set<Character> guesses()
    {
        return Collections.unmodifiableSet(guessesMade);
    }

    public String pattern()
    {
        if (words.isEmpty())
        {
            throw new IllegalStateException("The set of words is empty.");
        }
        return String.join(" ", currentPattern.split(""));
    }

    public int record(char guess)
    {
        if (words.isEmpty() || attemptsLeft < 1)
        {
            throw new IllegalStateException("Either no guesses left or the set of words is empty");
        }
        if (guessesMade.contains(guess))
        {
            throw new IllegalArgumentException("The character has already been guessed.");
        }

        guessesMade.add(guess);

        Map<String, Set<String>> patterns = new TreeMap<String, Set<String>>();
        for (String word : words)
        {
            String pattern = generatePattern(word);
            if (!patterns.containsKey(pattern));
            {
                patterns.put(pattern, new TreeSet<>());
            }
            patterns.get(pattern).add(word);
        }

        String bestPattern = selectBestPattern(patterns);
        words = patterns.get(bestPattern);
        if (!currentPattern.equals(bestPattern))
        {
            currentPattern = bestPattern;
        }
        else
        {
            attemptsLeft--;
        }
        return numberOfCharInString(currentPattern, guess); 
    }

    private String generatePattern(String word)
    {
        String pattern = "";
        for (int i = 0; i < word.length(); i++)
        {
            char letter = word.charAt(i);
            if (guessesMade.contains(letter))
            {
                pattern += letter;
            } 
            else if (currentPattern.charAt(i) != EMPTY_CHAR)
            {
                pattern += currentPattern.charAt(i);
            }
            else
            {
                pattern += EMPTY_CHAR;
            }
        }
        return pattern;
    }

    private String selectBestPattern(Map<String, Set<String>> patterns)
    {
        int maxPatternsCount = 0;
        String bestPattern = currentPattern;
        for (String pattern : patterns.keySet())
        {
            Set<String> wordSet = patterns.get(pattern);
            if (wordSet.size() > maxPatternsCount)
            {
                maxPatternsCount = wordSet.size();
                bestPattern = pattern;
            }
        }
        return bestPattern;
    }

    private int numberOfCharInString(String s, char c)
    {
        int count = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == c)
            {
                count++;
            }
        }
        return count;
    }
}