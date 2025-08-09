package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class DateTimePrinterInternalPrinter implements InternalPrinter {
   private final DateTimePrinter underlying;

   static InternalPrinter of(DateTimePrinter var0) {
      if (var0 instanceof InternalPrinterDateTimePrinter) {
         return (InternalPrinter)var0;
      } else {
         return var0 == null ? null : new DateTimePrinterInternalPrinter(var0);
      }
   }

   private DateTimePrinterInternalPrinter(DateTimePrinter var1) {
      this.underlying = var1;
   }

   DateTimePrinter getUnderlying() {
      return this.underlying;
   }

   public int estimatePrintedLength() {
      return this.underlying.estimatePrintedLength();
   }

   public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
      if (var1 instanceof StringBuffer) {
         StringBuffer var8 = (StringBuffer)var1;
         this.underlying.printTo(var8, var2, var4, var5, var6, var7);
      } else if (var1 instanceof Writer) {
         Writer var9 = (Writer)var1;
         this.underlying.printTo(var9, var2, var4, var5, var6, var7);
      } else {
         StringBuffer var10 = new StringBuffer(this.estimatePrintedLength());
         this.underlying.printTo(var10, var2, var4, var5, var6, var7);
         var1.append(var10);
      }

   }

   public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
      if (var1 instanceof StringBuffer) {
         StringBuffer var4 = (StringBuffer)var1;
         this.underlying.printTo(var4, var2, var3);
      } else if (var1 instanceof Writer) {
         Writer var5 = (Writer)var1;
         this.underlying.printTo(var5, var2, var3);
      } else {
         StringBuffer var6 = new StringBuffer(this.estimatePrintedLength());
         this.underlying.printTo(var6, var2, var3);
         var1.append(var6);
      }

   }
}
