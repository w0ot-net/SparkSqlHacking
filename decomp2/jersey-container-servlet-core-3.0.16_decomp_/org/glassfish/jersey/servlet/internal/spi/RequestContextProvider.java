package org.glassfish.jersey.servlet.internal.spi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface RequestContextProvider {
   HttpServletRequest getHttpServletRequest();

   HttpServletResponse getHttpServletResponse();
}
