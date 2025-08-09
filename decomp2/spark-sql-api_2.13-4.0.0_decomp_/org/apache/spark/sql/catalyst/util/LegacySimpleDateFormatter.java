package org.apache.spark.sql.catalyst.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3A\u0001C\u0005\u0001-!A\u0011\u0005\u0001B\u0001B\u0003%!\u0005\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003/\u0011\u0015)\u0004\u0001\"\u00017\u0011!Q\u0004\u0001#b\u0001\n\u0013Y\u0004\"\u0002$\u0001\t\u0003:\u0005\"B'\u0001\t\u0003r\u0005\"B)\u0001\t\u0003\u0012&!\u0007'fO\u0006\u001c\u0017pU5na2,G)\u0019;f\r>\u0014X.\u0019;uKJT!AC\u0006\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u00195\t\u0001bY1uC2L8\u000f\u001e\u0006\u0003\u001d=\t1a]9m\u0015\t\u0001\u0012#A\u0003ta\u0006\u00148N\u0003\u0002\u0013'\u00051\u0011\r]1dQ\u0016T\u0011\u0001F\u0001\u0004_J<7\u0001A\n\u0004\u0001]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g\r\u0005\u0002\u001f?5\t\u0011\"\u0003\u0002!\u0013\t\u0019B*Z4bGf$\u0015\r^3G_Jl\u0017\r\u001e;fe\u00069\u0001/\u0019;uKJt\u0007CA\u0012+\u001d\t!\u0003\u0006\u0005\u0002&35\taE\u0003\u0002(+\u00051AH]8pizJ!!K\r\u0002\rA\u0013X\rZ3g\u0013\tYCF\u0001\u0004TiJLgn\u001a\u0006\u0003Se\ta\u0001\\8dC2,\u0007CA\u00184\u001b\u0005\u0001$B\u0001\u00062\u0015\u0005\u0011\u0014\u0001\u00026bm\u0006L!\u0001\u000e\u0019\u0003\r1{7-\u00197f\u0003\u0019a\u0014N\\5u}Q\u0019q\u0007O\u001d\u0011\u0005y\u0001\u0001\"B\u0011\u0004\u0001\u0004\u0011\u0003\"B\u0017\u0004\u0001\u0004q\u0013aA:eMV\tA\b\u0005\u0002>\u00016\taH\u0003\u0002@c\u0005!A/\u001a=u\u0013\t\teH\u0001\tTS6\u0004H.\u001a#bi\u00164uN]7bi\"\u0012Aa\u0011\t\u00031\u0011K!!R\r\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018a\u00039beN,Gk\u001c#bi\u0016$\"\u0001S&\u0011\u0005=J\u0015B\u0001&1\u0005\u0011!\u0015\r^3\t\u000b1+\u0001\u0019\u0001\u0012\u0002\u0003M\faAZ8s[\u0006$HC\u0001\u0012P\u0011\u0015\u0001f\u00011\u0001I\u0003\u0005!\u0017!\u0006<bY&$\u0017\r^3QCR$XM\u001d8TiJLgn\u001a\u000b\u0002'B\u0011\u0001\u0004V\u0005\u0003+f\u0011A!\u00168ji\u0002"
)
public class LegacySimpleDateFormatter implements LegacyDateFormatter {
   private transient SimpleDateFormat sdf;
   private final String pattern;
   private final Locale locale;
   private transient volatile boolean bitmap$trans$0;

   public int parse(final String s) {
      return LegacyDateFormatter.parse$(this, s);
   }

   public String format(final int days) {
      return LegacyDateFormatter.format$(this, days);
   }

   public String format(final LocalDate localDate) {
      return LegacyDateFormatter.format$(this, localDate);
   }

   private SimpleDateFormat sdf$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.sdf = new SimpleDateFormat(this.pattern, this.locale);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.sdf;
   }

   private SimpleDateFormat sdf() {
      return !this.bitmap$trans$0 ? this.sdf$lzycompute() : this.sdf;
   }

   public Date parseToDate(final String s) {
      return this.sdf().parse(s);
   }

   public String format(final Date d) {
      return this.sdf().format(d);
   }

   public void validatePatternString() {
      this.sdf();
   }

   public LegacySimpleDateFormatter(final String pattern, final Locale locale) {
      this.pattern = pattern;
      this.locale = locale;
      LegacyDateFormatter.$init$(this);
   }
}
