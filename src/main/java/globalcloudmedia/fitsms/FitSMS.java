package globalcloudmedia.fitsms;

import okhttp3.*;
import java.io.IOException;
import com.google.gson.Gson;

public class FitSMS {
    private final String apiToken;
    private final String senderId;
    private final String baseUrl = "https://app.fitsms.lk/api/v4";
    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public FitSMS(String apiToken, String senderId) {
        this.apiToken = apiToken;
        this.senderId = senderId;
    }

    private String executeRequest(String endpoint, String method, RequestBody body) throws IOException {
        Request.Builder builder = new Request.Builder()
                .url(baseUrl + endpoint)
                .addHeader("Authorization", "Bearer " + apiToken)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json");

        if (method.equalsIgnoreCase("POST")) {
            builder.post(body);
        } else {
            builder.get();
        }

        try (Response response = client.newCall(builder.build()).execute()) {
            return response.body() != null ? response.body().string() : "Error";
        }
    }

    private String formatNumber(String number) {
        String clean = number.replaceAll("[^0-9]", "");
        if (clean.startsWith("0"))
            clean = clean.substring(1);
        if (!clean.startsWith("94"))
            clean = "94" + clean;
        return clean;
    }

    public String send(String recipient, String message, String smsType) throws IOException {
        if (!smsType.equals("unicode") && !smsType.equals("plain")) {
            return "{\"status\":\"error\",\"message\":\"Invalid SMS type. Use 'plain' or 'unicode'.\"}";
        }

        SmsPayload data = new SmsPayload(formatNumber(recipient), senderId, smsType, message);
        String jsonPayload = gson.toJson(data);

        RequestBody body = RequestBody.create(jsonPayload, MediaType.get("application/json; charset=utf-8"));

        return executeRequest("/sms/send", "POST", body);
    }

    public String getStatus(String ruid, String recipient) throws IOException {
        String url = "/sms/" + ruid + "?recipient=" + formatNumber(recipient);
        return executeRequest(url, "GET", null);
    }

    public String getBalance() throws IOException {
        return executeRequest("/balance", "GET", null);
    }

    public String getProfile() throws IOException {
        return executeRequest("/me", "GET", null);
    }

    public void close() {
        this.client.dispatcher().executorService().shutdown();
        this.client.connectionPool().evictAll();
    }
}