package de.idealo.codekata.readable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class WordSearcherReadable implements WordSearcher {
    private static final String EMPY_COLLECTION = "The source-collection is empty. Can't extract a collection with wordlength: ";

    @Override
    public <T> List<T> getCollectionWithSpecificWordLength(final Collection<T> collection, final int length) {
        if(collection.isEmpty()){
            throw new IllegalStateException(EMPY_COLLECTION + length);
        }
        return collection.stream().filter(word -> word.toString().length() == length)
                .collect(Collectors.toList());
    }

    @Override
    public ArrayList<ArrayList<String>> getCollectionWithWordsGroupedByLength(final Collection<String> collection, final int length) {
        ArrayList<ArrayList<String>> collections = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            collections.add((ArrayList<String>) getCollectionWithSpecificWordLength(collection, i+1));
        }
        return collections;
    }
}
