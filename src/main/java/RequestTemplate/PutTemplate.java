package RequestTemplate;

import com.example.apitemplate.Response.ValidDeleteResponse;
import com.example.apitemplate.Response.ValidPutResponse;
import com.example.apitemplate.Structure.StructureTemplate;
import com.example.apitemplate.Structure.ValidStructure;
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
public class PutTemplate {
    public static void main(String[] args) {
        String url = "http://localhost:8080/StructureTemplate/change";

        StructureTemplate dataToSend = new StructureTemplate("d0035f81c21440ffb892e446ca99486f", "new text");
        try {
            put(url, dataToSend);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }


    public static void put(String url, ValidStructure structureTemplate) throws URISyntaxException{
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL obj = new URI(url).toURL();

            HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            try(DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())){
                outputStream.writeBytes(objectMapper.writeValueAsString(structureTemplate));
                outputStream.flush();
            }
            int responseCode  = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();

                try(BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))){
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        response.append(line);
                    }
                }
                ValidPutResponse responseEntity = objectMapper.readValue(response.toString(), structureTemplate.validPutResponseType());

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
