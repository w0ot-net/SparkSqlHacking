package org.joda.time.format;

interface InternalParser {
   int estimateParsedLength();

   int parseInto(DateTimeParserBucket var1, CharSequence var2, int var3);
}
