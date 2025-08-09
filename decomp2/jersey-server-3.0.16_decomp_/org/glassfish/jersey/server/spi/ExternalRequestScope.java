package org.glassfish.jersey.server.spi;

import org.glassfish.jersey.internal.inject.InjectionManager;

public interface ExternalRequestScope extends AutoCloseable {
   ExternalRequestContext open(InjectionManager var1);

   void suspend(ExternalRequestContext var1, InjectionManager var2);

   void resume(ExternalRequestContext var1, InjectionManager var2);

   void close();
}
