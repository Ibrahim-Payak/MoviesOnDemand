package com.main.MoviesOnDemand.Helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class SessionHelper {

    public void removeMessageSessionAttribute(){
        HttpSession httpSession = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        httpSession.removeAttribute("message");

//        System.out.println("removed message attribute from session");
    }
}
