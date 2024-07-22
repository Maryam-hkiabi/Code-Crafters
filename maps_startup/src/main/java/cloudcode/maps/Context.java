package cloudcode.maps;

import com.google.maps.GeoApiContext;

public class Context {

    protected static final String key = "AIzaSyAHh-dlNGXf-xhTCfO9ZBbB3RtR3YZ-kNk";

    public static GeoApiContext context = new GeoApiContext.Builder()
            .apiKey(key)
            .build();
}