package cloudcode.maps.entity;

import java.util.List;

/** Object holding suggested place categories for user
 *
 */
public class Suggestions {

    private final List<String> suggestions;

    /** Constructs a Suggestions object
     *
     * @param categories List of strings of categories
     */
    public Suggestions(List<String> categories) {
        this.suggestions = categories;
    }

    /** Gets suggested cateogories
     *
     * @return List of strings of categories
     */
    public List<String> getSuggestions() {
        return suggestions;
    }
}
