package org.apache.derby.impl.store.raw.data;

import org.apache.derby.iapi.services.io.DynamicByteArrayOutputStream;
import org.apache.derby.shared.common.error.StandardException;

public class LongColumnException extends StandardException {
   protected DynamicByteArrayOutputStream logBuffer;
   protected int nextColumn;
   protected int realSpaceOnPage;
   protected Object column;

   public LongColumnException() {
      super("lngcl.U");
   }

   public void setColumn(Object var1) {
      this.column = var1;
   }

   public void setExceptionInfo(DynamicByteArrayOutputStream var1, int var2, int var3) {
      this.logBuffer = var1;
      this.nextColumn = var2;
      this.realSpaceOnPage = var3;
   }

   public Object getColumn() {
      return this.column;
   }

   public DynamicByteArrayOutputStream getLogBuffer() {
      return this.logBuffer;
   }

   public int getNextColumn() {
      return this.nextColumn;
   }

   public int getRealSpaceOnPage() {
      return this.realSpaceOnPage;
   }
}
