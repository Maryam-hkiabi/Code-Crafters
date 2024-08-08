package cloudcode.maps.Interface_adapter;
import java.util.ArrayList;
import java.util.List;

public class UofTStGeorgeMap {
    public static void main(String[] args) {
        List<Place> places = new ArrayList<>();

        String[][] buildings = {
                {"Convocation Hall", "31 King's College Cir, Toronto, ON, M5S 1A1"},
                {"Robarts Library", "130 St. George St, Toronto, ON, M5S 1A5"},
                {"University College", "15 King's College Cir, Toronto, ON, M5S 3H7"},
                {"Hart House", "7 Hart House Cir, Toronto, ON, M5S 3H3"},
                {"Bahen Centre for Information Technology", "40 St. George St, Toronto, ON, M5S 2E4"},
                {"Trinity College", "6 Hoskin Ave, Toronto, ON, M5S 1H8"},
                {"Victoria College", "91 Charles St W, Toronto, ON, M5S 1K7"},
                {"Munk School of Global Affairs", "1 Devonshire Pl, Toronto, ON, M5S 3K7"},
                {"Banting and Best Department of Medical Research", "112 College St, Toronto, ON, M5G 1L6"},
                {"Medical Sciences Building", "1 King's College Cir, Toronto, ON, M5S 1A8"},
                {"Earth Sciences Centre", "22 Russell St, Toronto, ON, M5S 3B1"},
                {"Sandford Fleming Building", "10 King's College Rd, Toronto, ON, M5S 3G4"},
                {"Myhal Centre for Engineering Innovation & Entrepreneurship", "55 St. George St, Toronto, ON, M5S 0C9"},
                {"Galbraith Building", "35 St. George St, Toronto, ON, M5S 1A4"},
                {"Goldring Centre for High Performance Sport", "100 Devonshire Pl, Toronto, ON, M5S 2C9"},
                {"Koffler Student Services Centre", "214 College St, Toronto, ON, M5T 2Z9"},
                {"Leslie L. Dan Faculty of Pharmacy", "144 College St, Toronto, ON, M5S 3M2"},
                {"Lassonde Mining Building", "170 College St, Toronto, ON, M5S 3E3"},
                {"Innis College", "2 Sussex Ave, Toronto, ON, M5S 1J5"},
                {"Woodsworth College", "119 St. George St, Toronto, ON, M5S 1A9"},
                {"Rotman School of Management", "105 St. George St, Toronto, ON, M5S 3E6"},
                {"Jackman Humanities Building", "170 St. George St, Toronto, ON, M5R 2M8"},
                {"Faculty of Law", "78 Queen's Park, Toronto, ON, M5S 2C5"},
                {"George Ignatieff Theatre", "15 Devonshire Pl, Toronto, ON, M5S 2C8"},
                {"William Doo Auditorium", "45 Willcocks St, Toronto, ON, M5S 1C7"},
                {"John P. Robarts Research Library", "130 St. George St, Toronto, ON, M5S 1A5"},
                {"Toronto School of Theology", "47 Queen's Park Cres E, Toronto, ON, M5S 2C3"},
                {"New College", "40 Willcocks St, Toronto, ON, M5S 1C6"},
                {"Bloor Street Culture Corridor", "Bloor St W, Toronto, ON, M5S 1V6"},
                {"Astronomy and Astrophysics", "50 St George St, Toronto, ON M5S 3H4"},
                {"Enrolment Services", "172 St George St, Toronto, ON M5R 0A3"},
                {"Muzzo Family Alumni Hall", "121 St Joseph St, Toronto, ON M5S 3C2"},
                {"Annesley Hall", "95 Queens Park, Toronto, ON M5S 2C7"},
                {"Anthropology Building", "19 Russell St, Toronto, ON M5S 2S2"},
                {"John H. Daniels Faculty of Architecture, Landscape and Design", "1 Spadina Cres, Toronto, ON M5S 2J5"},
                {"Birge-Carnegie Library", "75a Queens Park, Toronto, ON M5S 2C5"},
                {"Bancroft Building", "4 Bancroft Ave, Toronto, ON M5S 1C1"},
                {"Banting Institute", "100 College St, Toronto, ON M5G 1L5"},
                {"Claude T. Bissell Building", "140 St George St, Toronto, ON M5S 3G6"},
                {"Clara Benson Building", "320 Huron St, Toronto, ON M5S 3G3"},
                {"Brennan Hall", "81 A St Mary St, Toronto, ON M5S 1J4"},
                {"St. basil's Church", "50 St Joseph St, Toronto, ON M5S 1J4"},
                {"Isabel Bader Theatre", "93 Charles St W, Toronto, ON M5S 2C7"},
                {"Burwash Dining Hall", "89 Charles St W, Toronto, ON M5S 1K6"},
                {"Campus Coop Daycare", "370 Huron St, Toronto, ON M5S 2G4"},
                {"Carr Hall", "100 St Joseph St, Toronto, ON M5S 2C4"},
                {"School of Continuing Studies", "158 St George St, Toronto, ON M5S 2V8"},
                {"E.J. Pratt Library", "71 Queen's Park Cres E, Toronto, ON M5S 1K7"},
                {"Thomas Fisher Rare Book Library", "120 St George St, Toronto, ON M5S 1A5"},
                {"Gerstein Science Information Centre", "9 King's College Cir, Toronto, ON M5S 1A5"},
                {"John M. Kelly Library", "113 St Joseph St, Toronto, ON M5S 3C2"},
                {"Lash Miller Chemical Laboratories", "80 St George St, Toronto, ON M5S 3H6"},
                {"Mechanical & Industrial Engineering", "5 King's College Rd, Toronto, ON M5S 3G8"},
                {"Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7"},
                {"Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6"}

        };

        for (int i = 0; i < buildings.length; i++) {
            String name = buildings[i][0];
            String address = buildings[i][1];
            places.add(new Place(name, address));
        }

        for (Place place : places) {
            System.out.println(place);
        }
    }
}
