package org.apache.spark.sql.catalyst.util;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3A!\u0002\u0004\u0001'!A\u0001\u0004\u0001B\u0001B\u0003%\u0011\u0004C\u0003\"\u0001\u0011\u0005!\u0005\u0003\u0005&\u0001!\u0015\r\u0011\"\u0015'\u0011\u0015Q\u0003\u0001\"\u00115\u0005i1%/Y2uS>tG+[7fgR\fW\u000e\u001d$pe6\fG\u000f^3s\u0015\t9\u0001\"\u0001\u0003vi&d'BA\u0005\u000b\u0003!\u0019\u0017\r^1msN$(BA\u0006\r\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sO\u000e\u00011C\u0001\u0001\u0015!\t)b#D\u0001\u0007\u0013\t9bAA\rJg>Dd\u0007M\u0019US6,7\u000f^1na\u001a{'/\\1ui\u0016\u0014\u0018A\u0002>p]\u0016LE\r\u0005\u0002\u001b?5\t1D\u0003\u0002\u001d;\u0005!A/[7f\u0015\u0005q\u0012\u0001\u00026bm\u0006L!\u0001I\u000e\u0003\ri{g.Z%e\u0003\u0019a\u0014N\\5u}Q\u00111\u0005\n\t\u0003+\u0001AQ\u0001\u0007\u0002A\u0002e\t\u0011BZ8s[\u0006$H/\u001a:\u0016\u0003\u001d\u0002\"\u0001K\u0016\u000e\u0003%R!AK\u000e\u0002\r\u0019|'/\\1u\u0013\ta\u0013FA\tECR,G+[7f\r>\u0014X.\u0019;uKJD#a\u0001\u0018\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0003E\nQa]2bY\u0006L!a\r\u0019\u0003\u0013Q\u0014\u0018M\\:jK:$HCA\u001bA!\t1TH\u0004\u00028wA\u0011\u0001\bM\u0007\u0002s)\u0011!HE\u0001\u0007yI|w\u000e\u001e \n\u0005q\u0002\u0014A\u0002)sK\u0012,g-\u0003\u0002?\u007f\t11\u000b\u001e:j]\u001eT!\u0001\u0010\u0019\t\u000b\u0005#\u0001\u0019\u0001\"\u0002\u0005Q\u001c\bCA\"F\u001b\u0005!%BA\u0006\u001e\u0013\t1EIA\u0005US6,7\u000f^1na\u0002"
)
public class FractionTimestampFormatter extends Iso8601TimestampFormatter {
   private transient DateTimeFormatter formatter;
   private transient volatile boolean bitmap$trans$0;

   private DateTimeFormatter formatter$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.formatter = DateTimeFormatterHelper$.MODULE$.fractionFormatter();
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.formatter;
   }

   public DateTimeFormatter formatter() {
      return !this.bitmap$trans$0 ? this.formatter$lzycompute() : this.formatter;
   }

   public String format(final Timestamp ts) {
      String formatted = this.legacyFormatter().format(ts);
      int nanos = ts.getNanos();
      if (nanos == 0) {
         return formatted;
      } else {
         int fracLen;
         for(fracLen = 9; nanos % 10 == 0; --fracLen) {
            nanos /= 10;
         }

         int fracOffset = formatted.length() + 1;
         int totalLen = fracOffset + fracLen;
         char[] buf = new char[totalLen];
         formatted.getChars(0, formatted.length(), buf, 0);
         buf[formatted.length()] = '.';
         int i = totalLen;

         do {
            --i;
            buf[i] = (char)(48 + nanos % 10);
            nanos /= 10;
         } while(i > fracOffset);

         return new String(buf);
      }
   }

   public FractionTimestampFormatter(final ZoneId zoneId) {
      super(TimestampFormatter$.MODULE$.defaultPattern(), zoneId, TimestampFormatter$.MODULE$.defaultLocale(), LegacyDateFormats$.MODULE$.FAST_DATE_FORMAT(), false);
   }
}
