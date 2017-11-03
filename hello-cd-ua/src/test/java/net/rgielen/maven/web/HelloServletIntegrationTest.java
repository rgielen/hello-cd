package net.rgielen.maven.web;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HelloServletIntegrationTest {

    private static String baseUrl;

    @BeforeClass
    public static void init() {
        baseUrl = "http://" + System.getProperty("server.host") + ":" + System.getProperty("server.port");
    }


    @Test
    public void serviceSaysHello() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseUrl+"/hello")
                .build();

        Response response = client.newCall(request).execute();
        assertEquals("Hallo!\n", response.body().string());
    }

}