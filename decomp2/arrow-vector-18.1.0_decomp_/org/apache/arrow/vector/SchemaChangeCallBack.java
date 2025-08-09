package org.apache.arrow.vector;

import org.apache.arrow.vector.util.CallBack;

public class SchemaChangeCallBack implements CallBack {
   private boolean schemaChanged = false;

   public void doWork() {
      this.schemaChanged = true;
   }

   public boolean getSchemaChangedAndReset() {
      boolean current = this.schemaChanged;
      this.schemaChanged = false;
      return current;
   }
}
