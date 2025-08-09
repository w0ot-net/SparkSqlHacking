package org.apache.spark.sql.catalyst.util;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.time.FastDateFormat;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3A\u0001C\u0005\u0001-!A\u0011\u0005\u0001B\u0001B\u0003%!\u0005\u0003\u0005.\u0001\t\u0005\t\u0015!\u0003/\u0011\u0015)\u0004\u0001\"\u00017\u0011!Q\u0004\u0001#b\u0001\n\u0013Y\u0004\"\u0002&\u0001\t\u0003Z\u0005\"B)\u0001\t\u0003\u0012\u0006\"B+\u0001\t\u00032&a\u0006'fO\u0006\u001c\u0017PR1ti\u0012\u000bG/\u001a$pe6\fG\u000f^3s\u0015\tQ1\"\u0001\u0003vi&d'B\u0001\u0007\u000e\u0003!\u0019\u0017\r^1msN$(B\u0001\b\u0010\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003!E\tQa\u001d9be.T!AE\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005!\u0012aA8sO\u000e\u00011c\u0001\u0001\u0018;A\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0010\u000e\u0003%I!\u0001I\u0005\u0003'1+w-Y2z\t\u0006$XMR8s[\u0006$H/\u001a:\u0002\u000fA\fG\u000f^3s]B\u00111E\u000b\b\u0003I!\u0002\"!J\r\u000e\u0003\u0019R!aJ\u000b\u0002\rq\u0012xn\u001c;?\u0013\tI\u0013$\u0001\u0004Qe\u0016$WMZ\u0005\u0003W1\u0012aa\u0015;sS:<'BA\u0015\u001a\u0003\u0019awnY1mKB\u0011qfM\u0007\u0002a)\u0011!\"\r\u0006\u0002e\u0005!!.\u0019<b\u0013\t!\u0004G\u0001\u0004M_\u000e\fG.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007]B\u0014\b\u0005\u0002\u001f\u0001!)\u0011e\u0001a\u0001E!)Qf\u0001a\u0001]\u0005\u0019a\r\u001a4\u0016\u0003q\u0002\"!\u0010#\u000e\u0003yR!a\u0010!\u0002\tQLW.\u001a\u0006\u0003\u0003\n\u000bQ\u0001\\1oONR!aQ\t\u0002\u000f\r|W.\\8og&\u0011QI\u0010\u0002\u000f\r\u0006\u001cH\u000fR1uK\u001a{'/\\1uQ\t!q\t\u0005\u0002\u0019\u0011&\u0011\u0011*\u0007\u0002\niJ\fgn]5f]R\f1\u0002]1sg\u0016$v\u000eR1uKR\u0011Aj\u0014\t\u0003_5K!A\u0014\u0019\u0003\t\u0011\u000bG/\u001a\u0005\u0006!\u0016\u0001\rAI\u0001\u0002g\u00061am\u001c:nCR$\"AI*\t\u000bQ3\u0001\u0019\u0001'\u0002\u0003\u0011\fQC^1mS\u0012\fG/\u001a)biR,'O\\*ue&tw\rF\u0001X!\tA\u0002,\u0003\u0002Z3\t!QK\\5u\u0001"
)
public class LegacyFastDateFormatter implements LegacyDateFormatter {
   private transient FastDateFormat fdf;
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

   private FastDateFormat fdf$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            this.fdf = FastDateFormat.getInstance(this.pattern, this.locale);
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.fdf;
   }

   private FastDateFormat fdf() {
      return !this.bitmap$trans$0 ? this.fdf$lzycompute() : this.fdf;
   }

   public Date parseToDate(final String s) {
      return this.fdf().parse(s);
   }

   public String format(final Date d) {
      return this.fdf().format(d);
   }

   public void validatePatternString() {
      this.fdf();
   }

   public LegacyFastDateFormatter(final String pattern, final Locale locale) {
      this.pattern = pattern;
      this.locale = locale;
      LegacyDateFormatter.$init$(this);
   }
}
