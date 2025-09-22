# 🏠 Smart Home Application

This project is a **Smart Home simulator** that demonstrates how to coordinate various devices (lights, alarm system, heating, coffee maker, etc.) using well-known **object-oriented design patterns**.  
It models real-world events (like *Arriving Home*, *Movement*, *Changing to Holiday/Working Day*) and shows how the system reacts by controlling devices in a consistent and extensible way.

---

## 🚀 Features

- Simulates **smart home events**:
  - `GoingHome`
  - `ArrivesHome`
  - `Movement`
  - `ChangeToHoliday`
  - `ChangeToWorkingDay`
- Manages **devices**:
  - Alarm System
  - Heating System (with adapter for legacy API)
  - Lights
  - Front Door
  - Coffee Maker (supports weak & strong coffee modes)
- Provides **console logs** for every device action and controller decision
- Demonstrates multiple **design patterns** working together:
  - **Strategy** – different coffee creation strategies
  - **Observer** – devices notify observers about actions
  - **Mediator** – HomeController coordinates devices
  - **Factory** – creates event commands
  - **Command** – encapsulates each event as an executable action
  - **Builder** – constructs the HomeController with its devices
  - **Adapter** – integrates the legacy heating system

---

## 🛠️ How It Works

1. **Initialization**  
   - Devices start in default states:  
     - Light OFF, Alarm OFF, Heating OFF, Door CLOSED, CoffeeMaker = **Strong mode**  

2. **Event Handling**  
   - Events are dispatched to the `HomeController` through command objects.  
   - The controller decides what action to take (or logs *“nothing to do”* if already in correct state).  

3. **Device Actions**  
   - Each device logs its actions, for example:  
     ```
     [Light] turn on
     [CoffeeMaker] create coffee with 20mg caffeine
     [HomeController] nothing to do (alarm system is already turned off)
     ```

---

## Prerequisites

JDK 21

Maven 3.8+
Check:
```java
java -version
mvn -v
```
## Run
```java
mvn clean compile exec:java -Dexec.mainClass="smarthome.Application"
```
## Javadoc
```java
mvn -DskipTests javadoc:javadoc
# macOS:
open target/site/apidocs/index.html
# Depending on your plugin
open target/reports/apidocs/index.html
# Linux:
xdg-open target/site/apidocs/index.html
# Windows:
start target\site\apidocs\index.html
```


