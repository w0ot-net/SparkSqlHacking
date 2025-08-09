package org.datanucleus.store.autostart;

public abstract class AbstractAutoStartMechanism implements AutoStartMechanism {
   protected AutoStartMechanism.Mode mode;
   protected boolean open = false;

   public AutoStartMechanism.Mode getMode() {
      return this.mode;
   }

   public void setMode(AutoStartMechanism.Mode mode) {
      this.mode = mode;
   }

   public void open() {
      this.open = true;
   }

   public boolean isOpen() {
      return this.open;
   }

   public void close() {
      this.open = false;
   }
}
