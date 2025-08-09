package org.glassfish.jersey.spi;

import jakarta.ws.rs.ext.ExceptionMapper;

public interface ExtendedExceptionMapper extends ExceptionMapper {
   boolean isMappable(Throwable var1);
}
