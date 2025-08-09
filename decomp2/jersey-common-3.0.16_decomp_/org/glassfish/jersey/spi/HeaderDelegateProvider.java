package org.glassfish.jersey.spi;

import jakarta.ws.rs.ext.RuntimeDelegate;

@Contract
public interface HeaderDelegateProvider extends RuntimeDelegate.HeaderDelegate {
   boolean supports(Class var1);
}
