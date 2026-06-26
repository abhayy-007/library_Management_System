package com.library.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Eagerly initialize the connection pool on startup
        MongoDBManager.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MongoDBManager.getInstance().close();
    }
}
