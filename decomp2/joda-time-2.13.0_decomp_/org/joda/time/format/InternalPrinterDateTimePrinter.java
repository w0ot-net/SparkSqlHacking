package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import org.joda.time.Chronology;
import org.joda.time.DateTimeZone;
import org.joda.time.ReadablePartial;

class InternalPrinterDateTimePrinter implements DateTimePrinter, InternalPrinter {
   private final InternalPrinter underlying;

   static DateTimePrinter of(InternalPrinter var0) {
      if (var0 instanceof DateTimePrinterInternalPrinter) {
         return ((DateTimePrinterInternalPrinter)var0).getUnderlying();
      } else if (var0 instanceof DateTimePrinter) {
         return (DateTimePrinter)var0;
      } else {
         return var0 == null ? null : new InternalPrinterDateTimePrinter(var0);
      }
   }

   private InternalPrinterDateTimePrinter(InternalPrinter var1) {
      this.underlying = var1;
   }

   public int estimatePrintedLength() {
      return this.underlying.estimatePrintedLength();
   }

   public void printTo(StringBuffer var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) {
      try {
         this.underlying.printTo(var1, var2, var4, var5, var6, var7);
      } catch (IOException var9) {
      }

   }

   public void printTo(Writer var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
      this.underlying.printTo(var1, var2, var4, var5, var6, var7);
   }

   public void printTo(Appendable var1, long var2, Chronology var4, int var5, DateTimeZone var6, Locale var7) throws IOException {
      this.underlying.printTo(var1, var2, var4, var5, var6, var7);
   }

   public void printTo(StringBuffer var1, ReadablePartial var2, Locale var3) {
      try {
         this.underlying.printTo(var1, var2, var3);
      } catch (IOException var5) {
      }

   }

   public void printTo(Writer var1, ReadablePartial var2, Locale var3) throws IOException {
      this.underlying.printTo(var1, var2, var3);
   }

   public void printTo(Appendable var1, ReadablePartial var2, Locale var3) throws IOException {
      this.underlying.printTo(var1, var2, var3);
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof InternalPrinterDateTimePrinter) {
         InternalPrinterDateTimePrinter var2 = (InternalPrinterDateTimePrinter)var1;
         return this.underlying.equals(var2.underlying);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.underlying.hashCode();
   }
}
