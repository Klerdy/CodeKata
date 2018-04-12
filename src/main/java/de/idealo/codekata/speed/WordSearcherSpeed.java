package de.idealo.codekata.speed;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class WordSearcherSpeed implements WordSearcher {

    private static final String EMPY_COLLECTION = "The source-collection is empty. Can't extract a collection with wordlength: ";

    @Override
    public List<String> getCollectionWithSpecificWordLength(Collection<String> collection, int length) {
        if(collection.isEmpty()){
            throw new IllegalStateException(EMPY_COLLECTION + length);
        }
        return collection.stream().filter(word -> word.length() == length)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findWordsOfSpecificLengthAndCombination(Collection<String> wordWithFixedLength, Map<String, Integer> allKnownWords) {
        return wordWithFixedLength.stream().map(word -> this.getRightWordPairs(allKnownWords, word))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<String> getRightWordPairs(Map<String, Integer> allKnownWords, String word) {
        final char[] wordToChars = word.toCharArray();
        final StringBuilder left = new StringBuilder();
        final StringBuilder right = new StringBuilder(word);
        Optional<String> opt = Optional.empty();
        final String matchedWord;
        boolean isRight = false;

        for (int i = 0; i < wordToChars.length-1 && !isRight; i++) {
            final char currentChar = wordToChars[i];
            left.append(currentChar);
            right.deleteCharAt(0);
            isRight = allKnownWords.containsKey(left.toString()) && allKnownWords.containsKey(right.toString());
        }

        if(isRight){
            matchedWord = word + " -> '" + left + ", " + right + "'";
            opt = Optional.of(matchedWord);
        }

        return opt;
    }
}
