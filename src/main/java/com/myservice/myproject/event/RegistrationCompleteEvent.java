package com.myservice.myproject.event;

import com.myservice.myproject.entiry.User;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class RegistrationCompleteEvent extends ApplicationEvent {

    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user= user;
        this.applicationUrl=applicationUrl;
    }
}
