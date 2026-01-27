package RequestTemplate;

import com.example.apitemplate.Response.*;
import com.example.apitemplate.Structure.StructureTemplate;
import com.example.apitemplate.Structure.ValidStructure;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class OutTemplate {
    public static void main(String[] args) {
        String url = "http://localhost:8080/StructureTemplate/change";
        /*
        When using Post, do not try to send ID
         */
        StructureTemplate dataToSend = new StructureTemplate( "sample text");
        try {
            requestOut(url, dataToSend, "post");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


    }


    public static void requestOut(String url, ValidStructure structureTemplate, String method) throws URISyntaxException{
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            URL obj = new URI(url).toURL();

            HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
            connection.setRequestMethod(method.toUpperCase());
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            try(DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())){
                outputStream.writeBytes(objectMapper.writeValueAsString(structureTemplate));
                outputStream.flush();
            }
            int responseCode  = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK ||
                    responseCode == HttpURLConnection.HTTP_CREATED
            ) {
                StringBuilder response = new StringBuilder();

                try(BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()))){
                    String line;
                    while ((line = bufferedReader.readLine()) != null){
                        response.append(line);
                    }
                }
                ValidOutResponse responseEntity = objectMapper.readValue(response.toString(), getResponseType(method));

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
    private static Class<? extends ValidOutResponse> getResponseType(String method){
        return switch (method) {
            case "post" -> StructureTemplatePostResponse.getResponseClass();
            case "put" -> StructureTemplatePutResponse.getResponseClass();
            case "Delete" -> StructureTemplateDeleteResponse.getResponseClass();
            default -> null;
        };
        }

    }


