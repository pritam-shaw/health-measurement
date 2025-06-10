## 🔗 Important Links
- [🔗 ESP32 Code](./esp32/final.ino)
## 📌 Features

- REST API for data ingestion and retrieval
- Support for real-time health parameters (Voltage, Current, Power, Temperature, etc.)
- Data persistence using a relational database PostgreSQL

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL (configurable)
- Lombok (for cleaner code)
- Hibernate

## 🏗️ Project Structure
HEALTH-MEASUREMENT<br>
├─src/<br>
│ &nbsp;├─ main/<br>
│ &nbsp;│ &nbsp;├─ java/<br>
│ &nbsp;│ &nbsp;│ &nbsp;└─ com/health/measurement/<br>
│ &nbsp;│ &nbsp;│ &nbsp;├─ controller/<br>
│ &nbsp;│ &nbsp;│ &nbsp;├─ config/ <br>
│ &nbsp;│ &nbsp;│ &nbsp;├─ model/<br>
│ &nbsp;│ &nbsp;│ &nbsp;├─ repository/<br>
│ &nbsp;│ &nbsp;│ &nbsp;└─ service/<br>
│ &nbsp;│ &nbsp;└─ resources/<br>
│ &nbsp;│ &nbsp;├─ application.properties<br>
│ &nbsp;└─ test/<br>
├─esp32/<br>
├─screenshots

## 📡 API Endpoints
| Method | Endpoint                  | Description                 |
| ------ | ------------------------- | --------------------------- |
| POST   | `/measurementdata`        | Submit new sensor data      |
| GET    | `last20measurementdatalist`        | Get the latest 20 measurements |
| GET    | `/measurementdata/{id}`   | Get measurement by ID       |
| GET    | `/measurementdatalist` | Get all measurement records  |

🧪 Sample JSON
<pre>
{
    "id": 2391,
    "acsCurrent": 3.809,
    "temperature": 19.34,
    "pzemVoltage": 232.8,
    "pzemCurrent": 0.037,
    "power": 8.2,
    "energy": 0.034,
    "frequency": 50.0,
    "powerFactor": 0.95
}
</pre>


## UI Screen
![ UI Screen ](./screenshots/ui_screen.png)
## Network tab responses
![ Network Tab ](./screenshots/network_tab.png)
## Database schema
![ Database schema ](./screenshots/schema.png)
## Data in postgres 
![ Network Tab ](./screenshots/database.png)