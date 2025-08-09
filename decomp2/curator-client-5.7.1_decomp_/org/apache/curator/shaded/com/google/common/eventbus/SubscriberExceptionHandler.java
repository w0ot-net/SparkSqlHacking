package org.apache.curator.shaded.com.google.common.eventbus;

@ElementTypesAreNonnullByDefault
public interface SubscriberExceptionHandler {
   void handleException(Throwable exception, SubscriberExceptionContext context);
}
