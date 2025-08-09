package org.apache.derby.impl.services.locks;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.derby.iapi.services.locks.Latch;

class LockTableVTI implements Enumeration {
   private final Iterator outerControl;
   private Control control;
   private ListIterator grantedList;
   private ListIterator waitingList;
   private Latch nextLock;

   LockTableVTI(Map var1) {
      this.outerControl = var1.values().iterator();
   }

   public boolean hasMoreElements() {
      if (this.nextLock != null) {
         return true;
      } else {
         while(true) {
            if (this.control == null) {
               if (!this.outerControl.hasNext()) {
                  return false;
               }

               this.control = (Control)this.outerControl.next();
               List var1 = this.control.getGranted();
               if (var1 != null) {
                  this.grantedList = var1.listIterator();
               }

               List var2 = this.control.getWaiting();
               if (var2 != null) {
                  this.waitingList = var2.listIterator();
               }

               this.nextLock = this.control.getFirstGrant();
               if (this.nextLock == null) {
                  this.nextLock = this.getNextLock(this.control);
               }
            } else {
               this.nextLock = this.getNextLock(this.control);
            }

            if (this.nextLock != null) {
               return true;
            }

            this.control = null;
         }
      }
   }

   private Latch getNextLock(Control var1) {
      Latch var2 = null;
      if (this.grantedList != null) {
         if (this.grantedList.hasNext()) {
            var2 = (Latch)this.grantedList.next();
         } else {
            this.grantedList = null;
         }
      }

      if (var2 == null && this.waitingList != null) {
         if (this.waitingList.hasNext()) {
            var2 = (Latch)this.waitingList.next();
         } else {
            this.waitingList = null;
         }
      }

      return var2;
   }

   public Object nextElement() {
      if (!this.hasMoreElements()) {
         throw new NoSuchElementException();
      } else {
         Latch var1 = this.nextLock;
         this.nextLock = null;
         return var1;
      }
   }
}
