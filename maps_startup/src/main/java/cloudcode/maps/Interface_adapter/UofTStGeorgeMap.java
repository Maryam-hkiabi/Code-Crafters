package cloudcode.maps.Interface_adapter;
import java.util.ArrayList;
import java.util.List;

public class UofTStGeorgeMap {
    public static void main(String[] args) {
        List<Place> places = new ArrayList<>();

        Object[][] buildings = {
                {"Convocation Hall", "31 King's College Cir, Toronto, ON, M5S 1A1", 43.6607, -79.3950},
                {"Robarts Library", "130 St. George St, Toronto, ON, M5S 1A5", 43.6645, -79.3999},
                {"University College", "15 King's College Cir, Toronto, ON, M5S 3H7", 43.6618, -79.3952},
                {"Hart House", "7 Hart House Cir, Toronto, ON, M5S 3H3", 43.6620, -79.3945},
                {"Bahen Centre for Information Technology", "40 St. George St, Toronto, ON, M5S 2E4", 43.6597, -79.3973},
                {"Trinity College", "6 Hoskin Ave, Toronto, ON, M5S 1H8", 43.6673, -79.3926},
                {"Victoria College", "91 Charles St W, Toronto, ON, M5S 1K7", 43.6677, -79.3929},
                {"Munk School of Global Affairs", "1 Devonshire Pl, Toronto, ON, M5S 3K7", 43.6687, -79.3992},
                {"Banting and Best Department of Medical Research", "112 College St, Toronto, ON, M5G 1L6", 43.6596, -79.3894},
                {"Medical Sciences Building", "1 King's College Cir, Toronto, ON, M5S 1A8", 43.6604, -79.3963},
                {"Earth Sciences Centre", "22 Russell St, Toronto, ON, M5S 3B1", 43.6588, -79.3985},
                {"Sandford Fleming Building", "10 King's College Rd, Toronto, ON, M5S 3G4", 43.6595, -79.3976},
                {"Myhal Centre for Engineering Innovation & Entrepreneurship", "55 St. George St, Toronto, ON, M5S 0C9", 43.6591, -79.3983},
                {"Galbraith Building", "35 St. George St, Toronto, ON, M5S 1A4", 43.6585, -79.3979},
                {"Goldring Centre for High Performance Sport", "100 Devonshire Pl, Toronto, ON, M5S 2C9", 43.6671, -79.4000},
                {"Koffler Student Services Centre", "214 College St, Toronto, ON, M5T 2Z9", 43.6573, -79.3930},
                {"Leslie L. Dan Faculty of Pharmacy", "144 College St, Toronto, ON, M5S 3M2", 43.6579, -79.3913},
                {"Lassonde Mining Building", "170 College St, Toronto, ON, M5S 3E3", 43.6578, -79.3902},
                {"Innis College", "2 Sussex Ave, Toronto, ON, M5S 1J5", 43.6662, -79.4006},
                {"Woodsworth College", "119 St. George St, Toronto, ON, M5S 1A9", 43.6678, -79.3997},
                {"Rotman School of Management", "105 St. George St, Toronto, ON, M5S 3E6", 43.6674, -79.3990},
                {"Jackman Humanities Building", "170 St. George St, Toronto, ON, M5R 2M8", 43.6673, -79.3969},
                {"Faculty of Law", "78 Queen's Park, Toronto, ON, M5S 2C5", 43.6661, -79.3936},
                {"George Ignatieff Theatre", "15 Devonshire Pl, Toronto, ON, M5S 2C8", 43.6675, -79.3985},
                {"William Doo Auditorium", "45 Willcocks St, Toronto, ON, M5S 1C7", 43.6609, -79.3998},
                {"John P. Robarts Research Library", "130 St. George St, Toronto, ON, M5S 1A5", 43.6645, -79.3999},
                {"Toronto School of Theology", "47 Queen's Park Cres E, Toronto, ON, M5S 2C3", 43.6650, -79.3934},
                {"New College", "40 Willcocks St, Toronto, ON, M5S 1C6", 43.6612, -79.3993},
                {"Bloor Street Culture Corridor", "Bloor St W, Toronto, ON, M5S 1V6", 43.6687, -79.3993},
                {"Astronomy and Astrophysics", "50 St George St, Toronto, ON M5S 3H4", 43.66060700295829, -79.39750482611738},
                {"Enrolment Services", "172 St George St, Toronto, ON M5R 0A3", 43.66829239245289, -79.4003679859145},
                {"Muzzo Family Alumni Hall", "121 St Joseph St, Toronto, ON M5S 3C2", 43.665090976910356, -79.39017430310447},
                {"Annesley Hall", "95 Queens Park, Toronto, ON M5S 2C7", 43.667793886524564, -79.39265051659811},
                {"Anthropology Building", "19 Russell St, Toronto, ON M5S 2S2", 43.660028097691416, -79.398379187763},
                {"John H. Daniels Faculty of Architecture, Landscape and Design", "1 Spadina Cres, Toronto, ON M5S 2J5", 43.65990306872996, -79.40023781659852},
                {"Birge-Carnegie Library", "75a Queens Park, Toronto, ON M5S 2C5", 43.66724826623394, -79.392859174269},
                {"Bancroft Building", "4 Bancroft Ave, Toronto, ON M5S 1C1", 43.66106048063408, -79.3996907724212},
                {"Banting Institute", "100 College St, Toronto, ON M5G 1L5", 43.66063329016546, -79.38867760310475},
                {"Claude T. Bissell Building", "140 St George St, Toronto, ON M5S 3G6", 43.665159054460226, -79.39909726077546},
                {"Clara Benson Building", "320 Huron St, Toronto, ON M5S 3G3", 43.66319734392028, -79.40026850310461},
                {"Brennan Hall", "81 A St Mary St, Toronto, ON M5S 1J4", 43.66655267256367, -79.3896949300918},
                {"St. basil's Church", "50 St Joseph St, Toronto, ON M5S 1J4", 43.6665003289216, -79.38916203194},
                {"Isabel Bader Theatre", "93 Charles St W, Toronto, ON M5S 2C7", 43.6674711265366, -79.39250043009177},
                {"Burwash Dining Hall", "89 Charles St W, Toronto, ON M5S 1K6", 43.667644112540344, -79.39165465892722},
                {"Campus Coop Daycare", "370 Huron St, Toronto, ON M5S 2G4", 43.66467465577546, -79.39994706558538},
                {"Carr Hall", "100 St Joseph St, Toronto, ON M5S 2C4", 43.665388053838356, -79.3905474165982},
                {"School of Continuing Studies", "158 St George St, Toronto, ON M5S 2V8", 43.66687874979096, -79.39998477611711},
                {"E.J. Pratt Library", "71 Queen's Park Cres E, Toronto, ON M5S 1K7", 43.66647318993968, -79.3912538165981},
                {"Thomas Fisher Rare Book Library", "120 St George St, Toronto, ON M5S 1A5", 43.66425984062353, -79.39898380310457},
                {"Gerstein Science Information Centre", "9 King's College Cir, Toronto, ON M5S 1A5", 43.66220330098059, -79.3931210883198},
                {"John M. Kelly Library", "113 St Joseph St, Toronto, ON M5S 3C2", 43.665133832278435, -79.38929888221845},
                {"Lash Miller Chemical Laboratories", "80 St George St, Toronto, ON M5S 3H6", 43.661916317103845, -79.3970014846236},
                {"Mechanical & Industrial Engineering", "5 King's College Rd, Toronto, ON M5S 3G8", 43.66002885057769, -79.39352498831988},
                {"Mclennan Physical Laboratories", "255 Huron St, Toronto, ON M5S 1A7", 43.66106220393106, -79.39813171715527},
                {"Ontario Institute for Studies in Education", "252 Bloor St W, Toronto, ON M5S 1V6", 43.66808762132367, -79.3974986674334}
        };

        for (int i = 0; i < buildings.length; i++) {
            String name = (String) buildings[i][0];
            String address = (String) buildings[i][1];
            double latitude = (double) buildings[i][2];
            double longitude = (double) buildings[i][3];
            places.add(new Place(name, address, latitude, longitude));
        }

        for (Place place : places) {
            System.out.println(place);
        }
    }
}
