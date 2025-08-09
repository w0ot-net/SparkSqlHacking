package org.apache.derby.impl.services.stream;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import org.apache.derby.shared.common.stream.HeaderPrintWriter;
import org.apache.derby.shared.common.stream.PrintWriterGetHeader;

class BasicHeaderPrintWriter extends PrintWriter implements HeaderPrintWriter {
   private final PrintWriterGetHeader headerGetter;
   private final boolean canClose;
   private final String name;

   BasicHeaderPrintWriter(OutputStream var1, PrintWriterGetHeader var2, boolean var3, String var4) {
      super(var1, true);
      this.headerGetter = var2;
      this.canClose = var3;
      this.name = var4;
   }

   BasicHeaderPrintWriter(Writer var1, PrintWriterGetHeader var2, boolean var3, String var4) {
      super(var1, true);
      this.headerGetter = var2;
      this.canClose = var3;
      this.name = var4;
   }

   public synchronized void printlnWithHeader(String var1) {
      this.print(this.headerGetter.getHeader());
      this.println(var1);
   }

   public PrintWriterGetHeader getHeader() {
      return this.headerGetter;
   }

   public PrintWriter getPrintWriter() {
      return this;
   }

   public String getName() {
      return this.name;
   }

   void complete() {
      this.flush();
      if (this.canClose) {
         this.close();
      }

   }
}
