package uk.ac.brighton.uni.na3.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class EventRequest {
    private final Event event;
    private final User sender, receiver;
    private final EventRequestResponseType response; //TODO: Maybe remove this

    public EventRequest(@JsonProperty("event") Event event,
                        @JsonProperty("sender") User sender,
                        @JsonProperty("receiver") User receiver,
                        @JsonProperty("response") EventRequestResponseType response) {
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
