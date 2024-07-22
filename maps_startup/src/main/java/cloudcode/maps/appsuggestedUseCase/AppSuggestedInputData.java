package cloudcode.maps.appsuggestedUseCase;

public class AppSuggestedInputData {

    final private String selectedcategory;

    public AppSuggestedInputData(String selectedcategory) {
        this.selectedcategory = selectedcategory;
    }

    String getSelectedcategory() {
        return selectedcategory;
    }
}