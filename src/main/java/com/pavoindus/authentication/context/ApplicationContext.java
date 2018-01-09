package com.pavoindus.authentication.context;

import com.pavoindus.authentication.model.ApiKey;
import com.pavoindus.authentication.model.Member;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;

public class ApplicationContext {

    protected static final Log logger = LogFactory.getLog(ApplicationContext.class);

    public ApplicationContext() {
    }

    private static ThreadLocal<ApplicationContext> applicationContext = new ThreadLocal<>();

    private ApiKey apiKey;

    private static Constructor ctor;

    public static void init() {
        logger.info("Initializing Application Context");
        Constructor[] ctors = ApplicationContext.class.getDeclaredConstructors();
        for(Constructor c : ctors) {
            if(c.getGenericParameterTypes().length == 0) {
                ctor = c;
                logger.info("Application Context initialized");
                break;
            }
        }
    }

    public static void create() {
        logger.info("Creating Application Context for request");
        if(applicationContext.get() != null) {
            throw new RuntimeException("Application Context already created");
        }
        ApplicationContext context = null;
        if(ctor != null) {
            try {
                context = (ApplicationContext) ctor.newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            applicationContext.set(context);
        } else {
            throw new RuntimeException("Application Context not initialized");
        }
        logger.info("Application Context created for request");
    }

    public static ApplicationContext get() {
        if(applicationContext.get() == null) {
            throw new RuntimeException("Application Context not created or destroyed");
        }
        return applicationContext.get();
    }

    public static void destroy() {
        if(applicationContext.get() == null) {
            throw new RuntimeException("Application Context already destoryed");
        }
        applicationContext.remove();
    }

    public ApiKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(ApiKey apiKey) {
        this.apiKey = apiKey;
    }
}
