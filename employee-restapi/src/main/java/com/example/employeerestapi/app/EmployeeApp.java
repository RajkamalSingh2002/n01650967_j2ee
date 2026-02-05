package com.example.employeerestapi.app;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class EmployeeApp extends Application {
    // Scans for @Path-annotated classes in the package
}

