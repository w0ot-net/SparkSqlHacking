package org.glassfish.jersey.server.internal.monitoring;

import java.util.List;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

public class CompositeRequestEventListener implements RequestEventListener {
   private final List requestEventListeners;

   public CompositeRequestEventListener(List requestEventListeners) {
      this.requestEventListeners = requestEventListeners;
   }

   public void onEvent(RequestEvent event) {
      for(RequestEventListener requestEventListener : this.requestEventListeners) {
         requestEventListener.onEvent(event);
      }

   }
}
