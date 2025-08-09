package org.glassfish.jersey.spi;

import jakarta.ws.rs.ext.ExceptionMapper;

public interface ExceptionMappers {
   ExceptionMapper find(Class var1);

   ExceptionMapper findMapping(Throwable var1);
}
