package ru.croc.javaschool2024.soloveva.task10;

import java.util.*;

public class BlackListFilterImpl implements BlackListFilter {
    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        ArrayList<String> filteredComments = (ArrayList<String>) comments;
        ListIterator<String> iterator = filteredComments.listIterator();

        while(iterator.hasNext()) {
            if(containsBlacklistedWords(iterator.next(), blackList)){
                iterator.remove();
            }
        }
    }

    private boolean containsBlacklistedWords(String comment, Set<String> blackList) {
        String[] commentWords = comment.replaceAll("\\p{Punct}", "").split("\\s+");

        for(String word : commentWords) {
            for(String blackword : blackList){
                if(word.equalsIgnoreCase(blackword)){
                    return true;
                }
            }
        }

        return false;
    }
}

