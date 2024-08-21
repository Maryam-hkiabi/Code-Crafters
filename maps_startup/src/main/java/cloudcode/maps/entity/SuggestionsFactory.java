package cloudcode.maps.entity;

import java.util.List;

/** Factory class that creates Suggestions object
 *
*/
public class SuggestionsFactory {

    /** Creates a Suggestions object
     *
     * @param suggestions List of strings representing suggestions
     * @return Suggestions object
     */
    public static Suggestions createSuggestions(List<String> suggestions) { return new Suggestions(suggestions); }

    public static void addCategory(Suggestions suggestions, String category) {
        suggestions.getSuggestions().add(category);
    }

    /** Adds categories to Suggestions object
     *
     * @param suggestions Suggestions object
     * @param categories List of strings representing categories
     */
    public static void addCategories(Suggestions suggestions, List<String> categories) {
        suggestions.getSuggestions().addAll(categories);
    }

    /** Removes categories from Suggestions object
     *
     * @param suggestions Suggestions object
     * @param categories List of strings representing categories
     */
    public static void removeCategories(Suggestions suggestions, List<String> categories) {
        suggestions.getSuggestions().removeAll(categories);
    }
}
