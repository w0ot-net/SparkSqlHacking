package org.apache.derby.iapi.services.monitor;

import java.util.ArrayList;

public class DerbyObservable {
   private boolean _hasChanged = false;
   private ArrayList _observers = new ArrayList();

   public synchronized void addObserver(DerbyObserver var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Null arguments not allowed.");
      } else {
         if (!this._observers.contains(var1)) {
            this._observers.add(var1);
         }

      }
   }

   public synchronized int countObservers() {
      return this._observers.size();
   }

   public synchronized void deleteObserver(DerbyObserver var1) {
      this._observers.remove(var1);
   }

   public void notifyObservers() {
      this.notifyObservers((Object)null);
   }

   public void notifyObservers(Object var1) {
      DerbyObserver[] var2;
      synchronized(this) {
         if (!this._hasChanged) {
            return;
         }

         var2 = new DerbyObserver[this._observers.size()];
         this._observers.toArray(var2);
         this._hasChanged = false;
      }

      int var3 = var2.length - 1;

      for(int var4 = var3; var4 >= 0; --var4) {
         var2[var4].update(this, var1);
      }

   }

   protected synchronized void setChanged() {
      this._hasChanged = true;
   }
}
