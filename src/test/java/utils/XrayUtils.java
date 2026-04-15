package utils;

import io.restassured.response.Response;
import java.io.File;

import static io.restassured.RestAssured.*;

public class XrayUtils {

    public static void uploadResults() {

        try {

            // 🔐 Step 1: Authenticate
            String token =
                given()
                    .header("Content-Type", "application/json")
                    .body("{ \"client_id\": \"5F845EDD11F74B90AF9FF788C549FF7A\", \"client_secret\": \"554fdcb2871aba3e59c7019b34a46e029d54494fd5448338947b1fcb5242cb59\" }")
                .when()
                    .post("https://xray.cloud.getxray.app/api/v2/authenticate")
                .then()
                    .extract()
                    .asString();

            token = token.replace("\"", "");

            // 📁 Step 2: Upload Cucumber JSON (FINAL FIX)
            File file = new File("target/cucumber-reports/cucumber.json");

            Response response =
                given()
                    .header("Authorization", "Bearer " + token)
                    .multiPart("results", file)   // ✅ CORRECT WAY
                .when()
                    .post("https://xray.cloud.getxray.app/api/v2/import/execution/cucumber");

            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response: " + response.asString());

        } catch (Exception e) {
            System.out.println("Xray Upload Failed ❌: " + e.getMessage());
        }
    }
}



















