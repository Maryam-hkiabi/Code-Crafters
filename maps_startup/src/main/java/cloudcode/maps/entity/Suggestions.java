package cloudcode.maps.entity;

import java.util.List;

public class Suggestions {

    private final List<String> suggestions;

    public Suggestions(List<String> categories) {
        this.suggestions = categories;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }
}
