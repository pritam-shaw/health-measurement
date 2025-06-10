#include <WiFi.h>
#include <HTTPClient.h>
#include <PZEM004Tv30.h>

// WiFi credentials
const char* ssid = "Pritam";
const char* password = "oooooooo";

// Server
const char* postURL = "http://192.168.43.73:8080/measurementdata";

// Pins
const int currentSensorPin = 35;
const int temperatureSensorPin = 33;
const int buzzerPin = 27;

// ADC settings
const float ADC_Resolution = 4095.0;
const float Vref = 3.3;

// ACS712 calibration
const float ACS712_Sensitivity = 0.185;

// Thresholds
const float currentThreshold = 5.0;
const float temperatureThreshold = 50.0;

// PZEM004T setup
#define RXD2 16
#define TXD2 17
PZEM004Tv30 pzem(Serial2, RXD2, TXD2);

void connectToWiFi() {
  Serial.print("Connecting to WiFi: ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  unsigned long startTime = millis();
  const unsigned long timeout = 15000; // 15 seconds timeout

  while (WiFi.status() != WL_CONNECTED && millis() - startTime < timeout) {
    delay(500);
    Serial.print(".");
  }

  if (WiFi.status() == WL_CONNECTED) {
    Serial.println("\nWiFi connected!");
    Serial.print("ESP32 IP Address: ");
    Serial.println(WiFi.localIP());
  } else {
    Serial.println("\nFailed to connect to WiFi. Please check credentials or router.");
  }
}

void setup() {
  Serial.begin(115200);
  delay(1000);
  Serial.println("Merged Sensor Monitoring System with WiFi + POST Request");

  // Connect to WiFi
  connectToWiFi();

  // Initialize PZEM-004T
  Serial2.begin(9600, SERIAL_8N1, RXD2, TXD2);

  IPAddress pzemAddress = pzem.getAddress();
  Serial.print("PZEM Address: ");
  Serial.println(pzemAddress);

  pinMode(buzzerPin, OUTPUT);
  digitalWrite(buzzerPin, LOW);
}

void loop() {
  // --- Read ACS712 Current Sensor ---
  int adcCurrent = analogRead(currentSensorPin);
  float voltageCurrent = (adcCurrent / ADC_Resolution) * Vref;
  float current = (voltageCurrent - (Vref / 2)) / ACS712_Sensitivity;

  // --- Read LM35 Temperature Sensor ---
  int adcTemp = analogRead(temperatureSensorPin);
  float voltageTemp = (adcTemp / ADC_Resolution) * Vref;
  float temperatureC = voltageTemp * 100.0;

  // --- Read PZEM-004T Data ---
  float voltage = pzem.voltage();
  float pzemCurrent = pzem.current();
  float power = pzem.power();
  float energy = pzem.energy();
  float frequency = pzem.frequency();
  float pf = pzem.pf();

  // --- Print data to Serial ---
  Serial.print("ACS712 Current: ");
  Serial.print(current, 3);
  Serial.print(" A\t");

  Serial.print("LM35 Temperature: ");
  Serial.print(temperatureC, 2);
  Serial.println(" °C");

  if (!isnan(voltage)) {
    Serial.print("PZEM Voltage: ");
    Serial.print(voltage);
    Serial.println(" V");
  }

  if (!isnan(pzemCurrent)) {
    Serial.print("PZEM Current: ");
    Serial.print(pzemCurrent, 3);
    Serial.println(" A");
  }

  if (!isnan(power)) {
    Serial.print("PZEM Power: ");
    Serial.print(power, 2);
    Serial.println(" W");
  }

  if (!isnan(energy)) {
    Serial.print("PZEM Energy: ");
    Serial.print(energy, 3);
    Serial.println(" Wh");
  }

  if (!isnan(frequency)) {
    Serial.print("PZEM Frequency: ");
    Serial.print(frequency, 1);
    Serial.println(" Hz");
  }

  if (!isnan(pf)) {
    Serial.print("PZEM Power Factor: ");
    Serial.println(pf, 2);
  }

  Serial.println("------------------------------");

  // --- Buzzer control ---
  if (current > currentThreshold || temperatureC > temperatureThreshold) {
    digitalWrite(buzzerPin, HIGH);
  } else {
    digitalWrite(buzzerPin, LOW);
  }

  // --- Send POST request ---
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;

    Serial.println("Sending POST request...");
    http.begin(postURL);
    http.addHeader("Content-Type", "application/json");

    String postData = "{";
    postData += "\"acsCurrent\":" + String(current, 3) + ",";
    postData += "\"temperature\":" + String(temperatureC, 2) + ",";
    postData += "\"pzemVoltage\":" + String(voltage, 2) + ",";
    postData += "\"pzemCurrent\":" + String(pzemCurrent, 3) + ",";
    postData += "\"power\":" + String(power, 2) + ",";
    postData += "\"energy\":" + String(energy, 3) + ",";
    postData += "\"frequency\":" + String(frequency, 1) + ",";
    postData += "\"powerFactor\":" + String(pf, 2);
    postData += "}";
    Serial.println(postData);
    int postCode = http.POST(postData);
    Serial.print("POST request status code: ");
    Serial.println(postCode);
    if (postCode > 0) {
      String response = http.getString();
      Serial.println("POST Response: " + response);
    } else {
      Serial.println("POST request failed: " + http.errorToString(postCode));
    }
    http.end();
  } else {
    Serial.println("WiFi not connected. Skipping POST request.");
    connectToWiFi(); // Try reconnecting
  }

  delay(5000); // Wait before next reading
}
