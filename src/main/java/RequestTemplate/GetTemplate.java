package RequestTemplate;

import com.example.apitemplate.Response.StructureTemplateGetResponse;
import com.example.apitemplate.Response.ValidGetResponse;
import com.example.apitemplate.Structure.ValidStructure;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GetTemplate {
    public static void main(String[] args) {
        String url = "http://localhost:8080/StructureTemplate";
        try {
            Get(url, StructureTemplateGetResponse.getResponseClass());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public static void Get(String url, Class<? extends ValidGetResponse> responseForm) throws URISyntaxException {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL obj = new URI(url).toURL();

            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                StringBuilder response = new StringBuilder();

                try(BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                }
                ValidGetResponse responseEntity = objectMapper.readValue(response.toString(), responseForm);
                System.out.println("Response: " + responseEntity);
            }

            else{
                System.out.println("Error " + connection.getResponseCode());
            }
            connection.disconnect();

        }catch (IOException e){
            System.out.println(e);
        }
}
}
