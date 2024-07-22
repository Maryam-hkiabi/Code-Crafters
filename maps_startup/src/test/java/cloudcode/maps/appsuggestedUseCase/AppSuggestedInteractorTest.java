package cloudcode.maps.appsuggestedUseCase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppSuggestedInteractorTest {

    @Test
    void successTest() {
        AppSuggestedInputData inputData = new AppSuggestedInputData("Student Services");

        AppSuggestedOutputBoundary successPresenter = new AppSuggestedOutputBoundary() {
            @Override
            public void prepareOutput(AppSuggestedOutputData appsuggestedOutputData) {
                assertEquals([[
                "Koffler Student Services Centre", "214 College St, Toronto, ON M5T 3A1, Canada", "ChIJoYudP8c0K4gROrDimky2mzU"], [
                "SGS Student Services", "63 St George St, Toronto, ON M5S 2Z9, Canada", "ChIJL2A8Lb80K4gRp9FVUcrQoNQ"], [
                "Bennett Centre for Student Services", "99 Ian MacDonald Blvd, North York, ON M3J 1P3, Canada", "ChIJ9ym3YSMuK4gRYVu0ychg638"], [
                "Academic Success Centre", "Koffler Student Services Centre, First Floor, 214 College St, Toronto, ON M5T 2Z9, Canada", "ChIJSb7XScc0K4gRVEcnWVOGgKA"], [
                "Centre for International Experience", "Cumberland House, 33 St George St, Toronto, ON M5S 1A4, Canada", "ChIJrWzGXMc0K4gRoBH7p3OzPiU"], [
                "Toronto Metropolitan University Student Centre", "55 Gould St, Toronto, ON M5B 1E9, Canada", "ChIJMxfWYjXL1IkRz3fHCuldM5M"], [
                "Toronto Metropolitan University - Career, Co-op \u0026 Student Success Centre", "350 Victoria Street, Podium - 60, Toronto, ON M5B 2K3, Canada", "ChIJGWalsso0K4gR7QZ8Rdwbrz0"]],
                appsuggestedOutputData.getPlaces());
                ;
            }
    }
}