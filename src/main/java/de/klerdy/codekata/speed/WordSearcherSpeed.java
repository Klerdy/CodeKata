package de.klerdy.codekata.speed;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WordSearcherSpeed implements WordSearcher {

    private static final String EMPY_COLLECTION =
            "The source-collection is empty. Can't extract a collection with wordlength: ";

    @Override
    public List<String> getCollectionWithSpecificWordLength(final Collection<String> collection,
            final int length) {
        if (collection.isEmpty()) {
            throw new IllegalStateException(EMPY_COLLECTION + length);
        }
        return collection.stream().filter(word -> word.length() == length)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findWordsOfSpecificLengthAndConcatenatedSmallerWords(
            final Collection<String> wordWithFixedLength, final Map<String, Short> allKnownWords) {
        return wordWithFixedLength.stream().map(word -> this.getRightWordPairs(allKnownWords, word))
                .filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    /**
     * This method will be used to map each words to a {@link Optional<String>} object. The object
     * contains the matched word (word + concatenated smaller words) if we find the word and at
     * least one concatenated combination in the hash map.
     * 
     * @param wordsInHashMap the hash map with all words that can match.
     * @param word a specific word of length {@link de.klerdy.codekata.constants.CodeKataConstants#WORD_LENGTH}.
     * @return a {@link Optional} object.
     */
    private Optional<String> getRightWordPairs(final Map<String, Short> wordsInHashMap,
            final String word) {
        final char[] wordToChars = word.toCharArray();
        final StringBuilder left = new StringBuilder();
        final StringBuilder right = new StringBuilder(word);
        Optional<String> opt = Optional.empty();
        final String matchedWord;
        boolean isRight = false;

        for (int i = 0; i < wordToChars.length - 1 && !isRight; i++) {
            final char currentChar = wordToChars[i];
            left.append(currentChar);
            right.deleteCharAt(0);
            isRight = wordsInHashMap.containsKey(left.toString())
                    && wordsInHashMap.containsKey(right.toString());
        }

        if (isRight) {
            matchedWord = word + " -> '" + left + ", " + right + "'";
            opt = Optional.of(matchedWord);
        }

        return opt;
    }
}
