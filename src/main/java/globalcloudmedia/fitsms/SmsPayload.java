package globalcloudmedia.fitsms;

public class SmsPayload {
    String recipient;
    String sender_id;
    String type;
    String message;

    SmsPayload(String recipient, String senderId, String type, String message) {
        this.recipient = recipient;
        this.sender_id = senderId;
        this.type = type;
        this.message = message;
    }
}
