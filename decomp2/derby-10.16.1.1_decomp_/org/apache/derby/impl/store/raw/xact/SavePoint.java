package org.apache.derby.impl.store.raw.xact;

import org.apache.derby.iapi.store.raw.log.LogInstant;

class SavePoint {
   private LogInstant savePoint;
   private final String name;
   private Object kindOfSavepoint;

   SavePoint(String var1, Object var2) {
      this.name = var1;
      this.kindOfSavepoint = var2;
   }

   void setSavePoint(LogInstant var1) {
      this.savePoint = var1;
   }

   LogInstant getSavePoint() {
      return this.savePoint;
   }

   String getName() {
      return this.name;
   }

   boolean isThisUserDefinedsavepoint() {
      return this.kindOfSavepoint != null;
   }

   Object getKindOfSavepoint() {
      return this.kindOfSavepoint;
   }
}
