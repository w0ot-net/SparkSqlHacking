package org.sparkproject.jetty.util.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopLifeCycle extends AbstractLifeCycle implements LifeCycle.Listener {
   private static final Logger LOG = LoggerFactory.getLogger(StopLifeCycle.class);
   private final LifeCycle _lifecycle;

   public StopLifeCycle(LifeCycle lifecycle) {
      this._lifecycle = lifecycle;
      this.addEventListener(this);
   }

   public void lifeCycleStarted(LifeCycle lifecycle) {
      try {
         this._lifecycle.stop();
      } catch (Exception e) {
         LOG.warn("Unable to stop", e);
      }

   }
}
