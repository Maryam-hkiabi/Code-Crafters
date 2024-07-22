package cloudcode.maps.appsuggestedUseCase;

import com.google.maps.errors.ApiException;
import java.io.IOException;

public class APICallTest {

    public static void main(String[] args) throws IOException, InterruptedException, ApiException {
        AppSuggestedAPICall apicall = new AppSuggestedAPICall("cafe");
        System.out.println(apicall.execute());
    }
}
