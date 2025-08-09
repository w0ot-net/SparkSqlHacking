package org.sparkproject.jetty.server.session;

public abstract class AbstractSessionDataStoreFactory implements SessionDataStoreFactory {
   int _gracePeriodSec = 3600;
   int _savePeriodSec = 0;

   public int getGracePeriodSec() {
      return this._gracePeriodSec;
   }

   public void setGracePeriodSec(int gracePeriodSec) {
      this._gracePeriodSec = gracePeriodSec;
   }

   public int getSavePeriodSec() {
      return this._savePeriodSec;
   }

   public void setSavePeriodSec(int savePeriodSec) {
      this._savePeriodSec = savePeriodSec;
   }
}
