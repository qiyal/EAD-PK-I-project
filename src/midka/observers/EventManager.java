package midka.observers;

import midka.observers.listeners.AddMotorbikeListener;
import midka.observers.listeners.ChangePriceMotorbike;
import midka.observers.listeners.EventListener;

import java.util.*;

public class EventManager {
    Map<String, HashSet<EventListener>> listeners = new HashMap<>();

    public EventManager() {
        listeners.put("add", new HashSet<>());
        listeners.put("changePrice", new HashSet<>());
    }

    public void subscribe(String eventType, EventListener listener) {
        HashSet<EventListener> users = listeners.get(eventType);

        for (EventListener user : users) {
            if(eventType.equals("add")) {
                AddMotorbikeListener l1 = (AddMotorbikeListener) listener;
                AddMotorbikeListener l2 = (AddMotorbikeListener) user;
                if (l1.email.equals(l2.email) && l1.motorbikeName.equals(l2.motorbikeName)) {
                    System.out.println("\nAlready available! (Add) " + l1.motorbikeName);
                    return;
                }
            } else {
                ChangePriceMotorbike l1 = (ChangePriceMotorbike) listener;
                ChangePriceMotorbike l2 = (ChangePriceMotorbike) user;
                if (l1.email.equals(l2.email) && l1.motorbikeName.equals(l2.motorbikeName)) {
                    System.out.println("\nAlready available!");
                    return;
                }
            }
        }
        System.out.println("\nNew listener added!");
        listeners.get(eventType).add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        HashSet<EventListener> users = listeners.get(eventType);

        for (EventListener user : users) {
            if(eventType.equals("add")) {
                AddMotorbikeListener l1 = (AddMotorbikeListener) listener;
                AddMotorbikeListener l2 = (AddMotorbikeListener) user;
                if (l1.email.equals(l2.email) && l1.motorbikeName.equals(l2.motorbikeName)) {
                    listeners.get(eventType).remove(l2);
                    System.out.println("\nListener deleted!");
                    return;
                }
            } else {
                ChangePriceMotorbike l1 = (ChangePriceMotorbike) listener;
                ChangePriceMotorbike l2 = (ChangePriceMotorbike) user;
                if (l1.email.equals(l2.email) && l1.motorbikeName.equals(l2.motorbikeName)) {
                    listeners.get(eventType).remove(l2);
                    System.out.println("\nListener deleted!");
                    return;
                }
            }
        }
        System.out.println("\nCan not found!");
    }

    public void notifyUsers(String motorbikeName, String event) {
        HashSet<EventListener> users = listeners.get(event);
        for (EventListener listener : users) {
            listener.update(motorbikeName);
        }
    }
}
