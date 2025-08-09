package org.joda.time.format;

class DateTimeParserInternalParser implements InternalParser {
   private final DateTimeParser underlying;

   static InternalParser of(DateTimeParser var0) {
      if (var0 instanceof InternalParserDateTimeParser) {
         return (InternalParser)var0;
      } else {
         return var0 == null ? null : new DateTimeParserInternalParser(var0);
      }
   }

   private DateTimeParserInternalParser(DateTimeParser var1) {
      this.underlying = var1;
   }

   DateTimeParser getUnderlying() {
      return this.underlying;
   }

   public int estimateParsedLength() {
      return this.underlying.estimateParsedLength();
   }

   public int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3) {
      return this.underlying.parseInto(var1, var2.toString(), var3);
   }
}
