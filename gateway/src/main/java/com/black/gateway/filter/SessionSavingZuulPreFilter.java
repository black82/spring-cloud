package com.black.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class SessionSavingZuulPreFilter extends ZuulFilter {


    @Autowired
    private SessionRepository repository;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpSession httpSession = context.getRequest().getSession();
        Session session = repository.getSession(httpSession.getId());
        System.out.println("***************************************");
        System.out.println(httpSession);
        System.out.println(session);
        if (session == null) {
            Session session1 = repository.createSession();
            repository.save(session1);
            context.addZuulRequestHeader("Cookie", "SESSION=" + session1);
        } else
            context.addZuulRequestHeader("Cookie", "SESSION=" + httpSession.getId());

        return context;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }
}
