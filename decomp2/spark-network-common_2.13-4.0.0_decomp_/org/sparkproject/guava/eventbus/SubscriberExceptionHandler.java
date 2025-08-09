package org.sparkproject.guava.eventbus;

@ElementTypesAreNonnullByDefault
public interface SubscriberExceptionHandler {
   void handleException(Throwable exception, SubscriberExceptionContext context);
}
