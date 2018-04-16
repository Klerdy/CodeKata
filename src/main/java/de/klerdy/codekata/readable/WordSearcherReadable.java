package de.klerdy.codekata.readable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WordSearcherReadable implements WordSearcher {
    private static final String EMPY_COLLECTION = "The source-collection is empty. Can't extract a collection with wordlength: ";

    @Override
    public List<String> getCollectionWithSpecificWordLength(final Collection<String> collection, final int length) {
        if (collection.isEmpty()) {
            throw new IllegalStateException(EMPY_COLLECTION + length);
        }
        return collection.stream().filter(word -> word.toString().length() == length)
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<ArrayList<String>> getListsOfWordsGroupedByLength(final Collection<String> collection, final int length) {
        final ArrayList<ArrayList<String>> collections = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            collections.add((ArrayList<String>) getCollectionWithSpecificWordLength(collection, i+1));
        }
        return collections;
    }

    @Override
    public boolean isWordInList(final Collection<String> collection, final String word) {
        return collection.contains(word);
    }
}
