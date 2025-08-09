package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.spark.SparkIllegalArgumentException;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001\u0002\b\u0010\u0001qA\u0001b\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\tg\u0001\u0011\t\u0011)A\u0005i!AA\b\u0001B\u0001B\u0003%Q\bC\u0003C\u0001\u0011\u00051\t\u0003\u0005I\u0001!\u0015\r\u0011\"\u0003J\u0011!9\u0006\u0001#b\u0001\n\u0013A\u0006\"B/\u0001\t\u0003r\u0006\"\u00023\u0001\t\u0003*\u0007\"\u00026\u0001\t\u0013Y\u0007\"B7\u0001\t\u0003r\u0007\"B7\u0001\t\u0003\n\b\"B7\u0001\t\u0003J\bBB@\u0001\t\u0003\n\tA\u0001\u000fMK\u001e\f7-\u001f$bgR$\u0016.\\3ti\u0006l\u0007OR8s[\u0006$H/\u001a:\u000b\u0005A\t\u0012\u0001B;uS2T!AE\n\u0002\u0011\r\fG/\u00197zgRT!\u0001F\u000b\u0002\u0007M\fHN\u0003\u0002\u0017/\u0005)1\u000f]1sW*\u0011\u0001$G\u0001\u0007CB\f7\r[3\u000b\u0003i\t1a\u001c:h\u0007\u0001\u00192\u0001A\u000f$!\tq\u0012%D\u0001 \u0015\u0005\u0001\u0013!B:dC2\f\u0017B\u0001\u0012 \u0005\u0019\te.\u001f*fMB\u0011A%J\u0007\u0002\u001f%\u0011ae\u0004\u0002\u0013)&lWm\u001d;b[B4uN]7biR,'/A\u0004qCR$XM\u001d8\u0011\u0005%\u0002dB\u0001\u0016/!\tYs$D\u0001-\u0015\ti3$\u0001\u0004=e>|GOP\u0005\u0003_}\ta\u0001\u0015:fI\u00164\u0017BA\u00193\u0005\u0019\u0019FO]5oO*\u0011qfH\u0001\u0007u>tW-\u00133\u0011\u0005URT\"\u0001\u001c\u000b\u0005]B\u0014\u0001\u0002;j[\u0016T\u0011!O\u0001\u0005U\u00064\u0018-\u0003\u0002<m\t1!l\u001c8f\u0013\u0012\fa\u0001\\8dC2,\u0007C\u0001 A\u001b\u0005y$B\u0001\t9\u0013\t\tuH\u0001\u0004M_\u000e\fG.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\t\u0011+ei\u0012\t\u0003I\u0001AQa\n\u0003A\u0002!BQa\r\u0003A\u0002QBQ\u0001\u0010\u0003A\u0002u\naBZ1ti\u0012\u000bG/\u001a$pe6\fG/F\u0001K!\tY\u0015+D\u0001M\u0015\t9TJ\u0003\u0002O\u001f\u0006)A.\u00198hg)\u0011\u0001kF\u0001\bG>lWn\u001c8t\u0013\t\u0011FJ\u0001\bGCN$H)\u0019;f\r>\u0014X.\u0019;)\u0005\u0015!\u0006C\u0001\u0010V\u0013\t1vDA\u0005ue\u0006t7/[3oi\u0006\u00191-\u00197\u0016\u0003e\u0003\"\u0001\n.\n\u0005m{!AD'jGJ|7oQ1mK:$\u0017M\u001d\u0015\u0003\rQ\u000bQ\u0001]1sg\u0016$\"a\u00182\u0011\u0005y\u0001\u0017BA1 \u0005\u0011auN\\4\t\u000b\r<\u0001\u0019\u0001\u0015\u0002\u0003M\fQ\u0002]1sg\u0016|\u0005\u000f^5p]\u0006dGC\u00014j!\rqrmX\u0005\u0003Q~\u0011aa\u00149uS>t\u0007\"B2\t\u0001\u0004A\u0013!D3yiJ\f7\r^'jGJ|7\u000f\u0006\u0002`Y\")q+\u0003a\u00013\u00061am\u001c:nCR$\"\u0001K8\t\u000bAT\u0001\u0019A0\u0002\u0013QLW.Z:uC6\u0004HC\u0001\u0015s\u0011\u0015\u00198\u00021\u0001u\u0003\t!8\u000f\u0005\u0002vo6\taO\u0003\u0002\u0015q%\u0011\u0001P\u001e\u0002\n)&lWm\u001d;b[B$\"\u0001\u000b>\t\u000bmd\u0001\u0019\u0001?\u0002\u000f%t7\u000f^1oiB\u0011Q'`\u0005\u0003}Z\u0012q!\u00138ti\u0006tG/A\u000bwC2LG-\u0019;f!\u0006$H/\u001a:o'R\u0014\u0018N\\4\u0015\t\u0005\r\u0011\u0011\u0002\t\u0004=\u0005\u0015\u0011bAA\u0004?\t!QK\\5u\u0011\u001d\tY!\u0004a\u0001\u0003\u001b\t1b\u00195fG.dUmZ1dsB\u0019a$a\u0004\n\u0007\u0005EqDA\u0004C_>dW-\u00198"
)
public class LegacyFastTimestampFormatter implements TimestampFormatter {
   private transient FastDateFormat fastDateFormat;
   private transient MicrosCalendar cal;
   private final String pattern;
   private final ZoneId zoneId;
   private final Locale locale;
   private transient volatile byte bitmap$trans$0;

   public long parseWithoutTimeZone(final String s, final boolean allowTimeZone) throws ParseException, DateTimeParseException, DateTimeException, IllegalStateException {
      return TimestampFormatter.parseWithoutTimeZone$(this, s, allowTimeZone);
   }

   public Option parseWithoutTimeZoneOptional(final String s, final boolean allowTimeZone) throws ParseException, DateTimeParseException, DateTimeException, IllegalStateException {
      return TimestampFormatter.parseWithoutTimeZoneOptional$(this, s, allowTimeZone);
   }

   public final long parseWithoutTimeZone(final String s) throws ParseException, DateTimeParseException, DateTimeException, IllegalStateException {
      return TimestampFormatter.parseWithoutTimeZone$(this, s);
   }

   public String format(final LocalDateTime localDateTime) throws IllegalStateException {
      return TimestampFormatter.format$(this, localDateTime);
   }

   private FastDateFormat fastDateFormat$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.fastDateFormat = FastDateFormat.getInstance(this.pattern, TimeZone.getTimeZone(this.zoneId), this.locale);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.fastDateFormat;
   }

   private FastDateFormat fastDateFormat() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.fastDateFormat$lzycompute() : this.fastDateFormat;
   }

   private MicrosCalendar cal$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.cal = new MicrosCalendar(this.fastDateFormat().getTimeZone(), .MODULE$.count$extension(scala.Predef..MODULE$.augmentString(this.fastDateFormat().getPattern()), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$cal$1(BoxesRunTime.unboxToChar(x$1)))));
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.cal;
   }

   private MicrosCalendar cal() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.cal$lzycompute() : this.cal;
   }

   public long parse(final String s) {
      this.cal().clear();
      if (!this.fastDateFormat().parse(s, new ParsePosition(0), this.cal())) {
         throw new SparkIllegalArgumentException("_LEGACY_ERROR_TEMP_3260", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("s"), s)}))));
      } else {
         return this.extractMicros(this.cal());
      }
   }

   public Option parseOptional(final String s) {
      this.cal().clear();

      Object var10000;
      try {
         var10000 = this.fastDateFormat().parse(s, new ParsePosition(0), this.cal()) ? new Some(BoxesRunTime.boxToLong(this.extractMicros(this.cal()))) : scala.None..MODULE$;
      } catch (Throwable var6) {
         if (var6 != null) {
            Option var5 = scala.util.control.NonFatal..MODULE$.unapply(var6);
            if (!var5.isEmpty()) {
               var10000 = scala.None..MODULE$;
               return (Option)var10000;
            }
         }

         throw var6;
      }

      return (Option)var10000;
   }

   private long extractMicros(final MicrosCalendar cal) {
      long micros = cal.getMicros();
      cal.set(14, 0);
      long julianMicros = Math.addExact(SparkDateTimeUtils$.MODULE$.millisToMicros(cal.getTimeInMillis()), micros);
      return RebaseDateTime$.MODULE$.rebaseJulianToGregorianMicros(TimeZone.getTimeZone(this.zoneId), julianMicros);
   }

   public String format(final long timestamp) {
      long julianMicros = RebaseDateTime$.MODULE$.rebaseGregorianToJulianMicros(TimeZone.getTimeZone(this.zoneId), timestamp);
      this.cal().setTimeInMillis(Math.floorDiv(julianMicros, 1000000L) * 1000L);
      this.cal().setMicros(Math.floorMod(julianMicros, 1000000L));
      return this.fastDateFormat().format(this.cal());
   }

   public String format(final Timestamp ts) {
      return ts.getNanos() == 0 ? this.fastDateFormat().format(ts) : this.format(SparkDateTimeUtils$.MODULE$.fromJavaTimestamp(this.zoneId.getId(), ts));
   }

   public String format(final Instant instant) {
      return this.format(SparkDateTimeUtils$.MODULE$.instantToMicros(instant));
   }

   public void validatePatternString(final boolean checkLegacy) {
      this.fastDateFormat();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$cal$1(final char x$1) {
      return x$1 == 'S';
   }

   public LegacyFastTimestampFormatter(final String pattern, final ZoneId zoneId, final Locale locale) {
      this.pattern = pattern;
      this.zoneId = zoneId;
      this.locale = locale;
      TimestampFormatter.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
