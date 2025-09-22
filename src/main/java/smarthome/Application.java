package smarthome;

import java.util.List;

import smarthome.commands.EventCommand;
import smarthome.commands.EventCommandFactory;
import smarthome.commands.EventCommandType;
import smarthome.controller.HomeController;
import smarthome.domain.observer.MessageObserver;
import smarthome.controller.HomeControllerBuilder;

public class Application {

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    private void run() {
        // 1. Create MessageObserver
        MessageObserver messageObserver = new MessageObserver();
        
        // 2. Build HomeController with default devices
        HomeController homeController = new HomeControllerBuilder(messageObserver)
                .build();
        
        // 3. Create EventCommandFactory
        EventCommandFactory factory = new EventCommandFactory(homeController);
        
        // 4. Execute the sequence of events from specification
        executeEvent(factory, EventCommandType.CHANGE_TO_HOLIDAY, "Change to holiday event");
        executeEvent(factory, EventCommandType.GOING_HOME, "Going home event");
        executeEvent(factory, EventCommandType.MOVEMENT, "Movement event");
        executeEvent(factory, EventCommandType.ARRIVES_HOME, "Arrive home event");
        executeEvent(factory, EventCommandType.MOVEMENT, "Movement event");
        executeEvent(factory, EventCommandType.CHANGE_TO_WORKING_DAY, "Change to working day event");
        executeEvent(factory, EventCommandType.GOING_HOME, "Going home event");
        executeEvent(factory, EventCommandType.ARRIVES_HOME, "Arrive home event");
        executeEvent(factory, EventCommandType.MOVEMENT, "Movement event");
        
        // 5. Print all collected messages from devices
        System.out.println();
        System.out.println("all messages dispatched by the devices:");
        List<String> allMessages = messageObserver.getMessages();
        for (String message : allMessages) {
            System.out.println(message);
        }
    }
    
    private void executeEvent(EventCommandFactory factory, EventCommandType eventType, String eventDescription) {
        System.out.println("--> " + eventDescription);
        EventCommand command = factory.createEventCommand(eventType);
        command.execute();
        System.out.println();
    }
}