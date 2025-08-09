package org.glassfish.jersey.servlet.spi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AsyncContextDelegateProvider {
   AsyncContextDelegate createDelegate(HttpServletRequest var1, HttpServletResponse var2);
}
