package org.sparkproject.jetty.util.component;

public class HaltLifeCycleListener implements LifeCycle.Listener {
   public void lifeCycleStarted(LifeCycle lifecycle) {
      Runtime.getRuntime().halt(0);
   }
}
