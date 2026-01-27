package RequestTemplate;

import com.example.apitemplate.Response.ValidPostResponse;
import com.example.apitemplate.Structure.StructureTemplate;
import com.example.apitemplate.Structure.ValidStructure;
import com.fasterxml.jackson.annotation.JsonInclude;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Deprecated
public class PostTemplate {
    public static void main(String[] args) {
        String url = "http://localhost:8080/StructureTemplate/write";

        StructureTemplate dataToSend = new StructureTemplate("sampleText");
        try {
            post(url, dataToSend);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }


    public static void post(String url, ValidStructure structureTemplate) throws URISyntaxException{
        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(structureTemplate);
        try{
            URL obj = new URI(url).toURL();

            HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            try(DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())){
                outputStream.writeBytes(objectMapper.writeValueAsString(structureTemplate));
                outputStream.flush();
            }
            int responseCode  = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                StringBuilder response = new StringBuilder();

                try(BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))){
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        response.append(line);
                    }
                }
                ValidPostResponse responseEntity = objectMapper.readValue(response.toString(), structureTemplate.validPostResponseType());

                System.out.println("Response: " + responseEntity);
            }
            else {
                System.out.println("HTTP error: " + responseCode);
            }
            connection.disconnect();
        }
        catch(IOException e){
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}