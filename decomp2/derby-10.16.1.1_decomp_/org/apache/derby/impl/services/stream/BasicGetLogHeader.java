package org.apache.derby.impl.services.stream;

import java.util.Date;
import org.apache.derby.shared.common.stream.PrintWriterGetHeader;

class BasicGetLogHeader implements PrintWriterGetHeader {
   private boolean doThreadId;
   private boolean doTimeStamp;
   private String tag;

   BasicGetLogHeader(boolean var1, boolean var2, String var3) {
      this.doThreadId = var1;
      this.doTimeStamp = var2;
      this.tag = var3;
   }

   public String getHeader() {
      StringBuffer var1 = new StringBuffer(48);
      if (this.tag != null) {
         var1.append(this.tag);
         var1.append(' ');
      }

      if (this.doTimeStamp) {
         var1.append(new Date());
         var1.append(' ');
      }

      if (this.doThreadId) {
         var1.append(Thread.currentThread().toString());
         var1.append(' ');
      }

      return var1.toString();
   }
}
