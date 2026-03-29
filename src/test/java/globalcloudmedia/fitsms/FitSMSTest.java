package globalcloudmedia.fitsms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FitSMSTest {

    private final String apiToken = "YOUR_API_TOKEN_HERE";
    private final String senderId = "YOUR_SENDER_ID_HERE";
    private final String recipientNumber = "947XXXXXXX";

    @Test
    public void testSmsSend() {

        FitSMS sdk = new FitSMS(apiToken, senderId);

        try {
            String response = sdk.send(recipientNumber, "Hello from Java SDK", "plain");

            System.out.println("Response: " + response);

            // Basic check: Ensure we got a response back
            assertNotNull(response);
            assertTrue(response.contains("status"));

        } catch (Exception e) {
            fail("SDK threw an exception: " + e.getMessage());
        }
    }

    @Test
    public void testBalanceCheck() {
        FitSMS sdk = new FitSMS(apiToken, senderId);
        try {
            String balance = sdk.getBalance();
            System.out.println("Balance: " + balance);
            assertNotNull(balance);
        } catch (Exception e) {
            fail("Balance check failed");
        }
    }

    @Test
    public void testProfileCheck() {
        FitSMS sdk = new FitSMS(apiToken, senderId);
        try {
            String profile = sdk.getProfile();
            System.out.println("Profile: " + profile);
            assertNotNull(profile);
        } catch (Exception e) {
            fail("Profile check failed");
        }
    }
}