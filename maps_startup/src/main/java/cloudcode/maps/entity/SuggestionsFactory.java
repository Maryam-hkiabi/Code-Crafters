package cloudcode.maps.entity;

import java.util.List;

public class SuggestionsFactory {

    public static Suggestions createSuggestions(List<String> suggestions) { return new Suggestions(suggestions); }

    public static void addCategories(Suggestions suggestions, List<String> categories) {
        suggestions.getSuggestions().addAll(categories);
    }

    public static void removeCategories(Suggestions suggestions, List<String> categories) {
        suggestions.getSuggestions().removeAll(categories);
    }
}
