package org.glassfish.jersey;

import jakarta.ws.rs.core.Application;

public interface ApplicationSupplier {
   Application getApplication();
}
