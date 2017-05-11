package uk.ac.brighton.uni.na3.model;

public class EventRequest {
    private final Event event;
    private final User sender, receiver;
    private final EventRequestResponseType response; //TODO: Maybe remove this

    public EventRequest(Event event, User sender, User receiver, EventRequestResponseType response) {
        this.event = event;
        this.sender = sender;
        this.receiver = receiver;
        this.response = response;
    }

    public Event getEvent() {
        return event;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public EventRequestResponseType getResponse() {
        return response;
    }
}
