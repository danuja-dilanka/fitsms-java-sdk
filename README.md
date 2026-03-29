# FitSMS Java SDK (v4)
[![Maven Central](https://img.shields.io/maven-central/v/lk.globalcloudmedia/fitsms.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/lk.globalcloudmedia/fitsms)

The official Java SDK for the [FitSMS.lk](https://fitsms.lk) gateway, maintained by Global Cloud Media. This package provides a high-performance, industrial-grade way to integrate SMS capabilities into Java applications, Android apps, and automated server-side tasks.

---

## 🚀 Features

- **Auto-Formatting**: Converts Sri Lankan numbers into `947XXXXXXXX` format.
- **v4 Support**: Fully compatible with FitSMS v4 REST API (JSON-based).
- **Asynchronous Ready**: Built on OkHttp for high-concurrency environments.
- **Type-Safe**: Uses GSON for JSON serialization and DTO-based payloads.

---

## 📦 Installation (Maven)

Add this dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>globalcloudmedia</groupId>
    <artifactId>fitsms</artifactId>
    <version>1.0.0</version>
</dependency>
```

> **Note**: Requires Java 15 or higher.

---

## ⚡ Quick Start

## Prerequisites

Before integrating the FitSMS Java SDK, ensure you have the following:

1. **Java 15+**: Verify with `java -version`.
2. **Maven**: For dependency management.
3. **API Credentials**:
   - Log in to [FitSMS Dashboard](https://app.fitsms.lk).
   - Generate your **API Token**.
   - Ensure your **Sender ID** is active.

---

### 1. Initialize the Client

```java
import globalcloudmedia.fitsms.FitSMS;

String apiToken = "YOUR_V4_API_TOKEN";
String senderId = "YOUR_SENDER_ID";

FitSMS sms = new FitSMS(apiToken, senderId);
```

---

### 2. Send an SMS

```java
try {
    String response = sms.send(
        "0771234567",
        "Hello from Global Cloud Media!",
        "plain"
    );

    System.out.println("API Response: " + response);

} catch (IOException e) {
    System.err.println("Network Error: " + e.getMessage());
}
```

---

### 3. Check Account Balance

```java
try {
    String balance = sms.getBalance();
    System.out.println("Remaining Balance: " + balance);
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## 📖 API Reference

| Method       | Parameters                  | Return Type   | Description |
|-------------|---------------------------|--------------|------------|
| send()      | recipient, message, type  | String (JSON)| Send SMS |
| getBalance()| none                      | String (JSON)| Get SMS balance |
| getStatus() | ruid, recipient           | String (JSON)| Check delivery status |
| getProfile()| none                      | String (JSON) | Get account details |

---

## ⚙️ Advanced Usage

### 🇱🇰 Sri Lankan Number Formatting

The SDK automatically formats numbers:

```
0771234567 → 94771234567
771234567  → 94771234567
+94771234567 → 94771234567
```

---

## 🔔 Webhook Integration (Recommended)

FitSMS v4 uses webhooks for real-time delivery updates.

### Sample Webhook Payload

```json
{
    "status": "success",
    "data": {
        "to": "94770000000",
        "from": "GCM_TECH",
        "message": "Transaction Alert",
        "ruid": "fe424939fc3c4b6dbcc876994517d712",
        "received_at": "2026-03-29T15:00:00+05:30"
    }
}
```

---

## 🛡 Best Practices

- Use environment variables:
```java
String apiToken = System.getenv("FITSMS_TOKEN");
```

- Store and index `ruid` in your database
- Log all API responses
- Ensure VPS has updated SSL certificates

---

## 📄 License

This project is licensed under the MIT License.

---

## 🤝 Contributing

Contributions and bug reports are welcome via GitHub issues.

---

## 👨‍💻 Maintainer

Maintained by [Global Cloud Media (pvt) Ltd.](https://globalcloudmedia.lk)