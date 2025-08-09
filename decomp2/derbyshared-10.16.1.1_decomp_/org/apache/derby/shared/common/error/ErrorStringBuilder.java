package org.apache.derby.shared.common.error;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import org.apache.derby.shared.common.stream.PrintWriterGetHeader;

public class ErrorStringBuilder {
   private StringWriter stringWriter;
   private PrintWriter printWriter;
   private PrintWriterGetHeader headerGetter;

   public ErrorStringBuilder(PrintWriterGetHeader var1) {
      this.headerGetter = var1;
      this.stringWriter = new StringWriter();
      this.printWriter = new PrintWriter(this.stringWriter);
   }

   public void append(String var1) {
      if (this.headerGetter != null) {
         this.printWriter.print(this.headerGetter.getHeader());
      }

      this.printWriter.print(var1);
   }

   public void appendln(String var1) {
      if (this.headerGetter != null) {
         this.printWriter.print(this.headerGetter.getHeader());
      }

      this.printWriter.println(var1);
   }

   public void stackTrace(Throwable var1) {
      for(int var2 = 0; var1 != null; ++var2) {
         if (var2 > 0) {
            this.printWriter.println("============= begin nested exception, level (" + var2 + ") ===========");
         }

         ((Throwable)var1).printStackTrace(this.printWriter);
         if (var1 instanceof SQLException) {
            SQLException var3 = ((SQLException)var1).getNextException();
            var1 = var3 == null ? ((Throwable)var1).getCause() : var3;
         } else {
            var1 = ((Throwable)var1).getCause();
         }

         if (var2 > 0) {
            this.printWriter.println("============= end nested exception, level (" + var2 + ") ===========");
         }
      }

   }

   public void reset() {
      this.stringWriter.getBuffer().setLength(0);
   }

   public StringBuffer get() {
      return this.stringWriter.getBuffer();
   }
}
