package org.joda.time.format;

class InternalParserDateTimeParser implements DateTimeParser, InternalParser {
   private final InternalParser underlying;

   static DateTimeParser of(InternalParser var0) {
      if (var0 instanceof DateTimeParserInternalParser) {
         return ((DateTimeParserInternalParser)var0).getUnderlying();
      } else if (var0 instanceof DateTimeParser) {
         return (DateTimeParser)var0;
      } else {
         return var0 == null ? null : new InternalParserDateTimeParser(var0);
      }
   }

   private InternalParserDateTimeParser(InternalParser var1) {
      this.underlying = var1;
   }

   public int estimateParsedLength() {
      return this.underlying.estimateParsedLength();
   }

   public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
      return this.underlying.parseInto(var1, var2, var3);
   }

   public int parseInto(DateTimeParserBucket var1, String var2, int var3) {
      return this.underlying.parseInto(var1, var2, var3);
   }

   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof InternalParserDateTimeParser) {
         InternalParserDateTimeParser var2 = (InternalParserDateTimeParser)var1;
         return this.underlying.equals(var2.underlying);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.underlying.hashCode();
   }
}
