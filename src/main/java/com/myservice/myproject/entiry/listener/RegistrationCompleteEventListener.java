package com.myservice.myproject.entiry.listener;

import com.myservice.myproject.entiry.User;
import com.myservice.myproject.event.RegistrationCompleteEvent;
import com.myservice.myproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {


    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        //create verification link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        ///sent link
        String url = event.getApplicationUrl()+"/verifyUser?token="
                + token;

        //sent verification link
        log.info("Click the link to verify our account: {}",
                url);
    }
}
