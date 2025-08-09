package org.apache.spark.sql.catalyst.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc\u0001\u0002\n\u0014\u0001\u0001B\u0001b\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\to\u0001\u0011\t\u0011)A\u0005q!A\u0001\t\u0001B\u0001B\u0003%\u0011\t\u0003\u0005G\u0001\t\u0005\t\u0015!\u0003H\u0011\u0015Q\u0005\u0001\"\u0001L\u0011!\t\u0006\u0001#b\u0001\n\u0013\u0011\u0006\"B/\u0001\t\u0003r\u0006\"\u00023\u0001\t\u0003*\u0007\"\u00026\u0001\t\u0003Z\u0007\"\u00026\u0001\t\u0003r\u0007\"\u00026\u0001\t\u00032\b\"\u0002?\u0001\t\u0003jx!CA\u0004'\u0005\u0005\t\u0012AA\u0005\r!\u00112#!A\t\u0002\u0005-\u0001B\u0002&\u000f\t\u0003\tI\u0002C\u0005\u0002\u001c9\t\n\u0011\"\u0001\u0002\u001e!I\u00111\u0007\b\u0002\u0002\u0013%\u0011Q\u0007\u0002\u001f\u0019\u0016<\u0017mY=TS6\u0004H.\u001a+j[\u0016\u001cH/Y7q\r>\u0014X.\u0019;uKJT!\u0001F\u000b\u0002\tU$\u0018\u000e\u001c\u0006\u0003-]\t\u0001bY1uC2L8\u000f\u001e\u0006\u00031e\t1a]9m\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7\u0001A\n\u0004\u0001\u0005:\u0003C\u0001\u0012&\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#AB!osJ+g\r\u0005\u0002)S5\t1#\u0003\u0002+'\t\u0011B+[7fgR\fW\u000e\u001d$pe6\fG\u000f^3s\u0003\u001d\u0001\u0018\r\u001e;fe:\u0004\"!\f\u001b\u000f\u00059\u0012\u0004CA\u0018$\u001b\u0005\u0001$BA\u0019 \u0003\u0019a$o\\8u}%\u00111gI\u0001\u0007!J,G-\u001a4\n\u0005U2$AB*ue&twM\u0003\u00024G\u00051!p\u001c8f\u0013\u0012\u0004\"!\u000f \u000e\u0003iR!a\u000f\u001f\u0002\tQLW.\u001a\u0006\u0002{\u0005!!.\u0019<b\u0013\ty$H\u0001\u0004[_:,\u0017\nZ\u0001\u0007Y>\u001c\u0017\r\\3\u0011\u0005\t#U\"A\"\u000b\u0005Qa\u0014BA#D\u0005\u0019aunY1mK\u00069A.\u001a8jK:$\bC\u0001\u0012I\u0013\tI5EA\u0004C_>dW-\u00198\u0002\rqJg.\u001b;?)\u0015aUJT(Q!\tA\u0003\u0001C\u0003,\u000b\u0001\u0007A\u0006C\u00038\u000b\u0001\u0007\u0001\bC\u0003A\u000b\u0001\u0007\u0011\tC\u0004G\u000bA\u0005\t\u0019A$\u0002\u0007M$g-F\u0001T!\t!v+D\u0001V\u0015\t1F(\u0001\u0003uKb$\u0018B\u0001-V\u0005A\u0019\u0016.\u001c9mK\u0012\u000bG/\u001a$pe6\fG\u000f\u000b\u0002\u00075B\u0011!eW\u0005\u00039\u000e\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u000bA\f'o]3\u0015\u0005}\u0013\u0007C\u0001\u0012a\u0013\t\t7E\u0001\u0003M_:<\u0007\"B2\b\u0001\u0004a\u0013!A:\u0002\u001bA\f'o]3PaRLwN\\1m)\t1\u0017\u000eE\u0002#O~K!\u0001[\u0012\u0003\r=\u0003H/[8o\u0011\u0015\u0019\u0007\u00021\u0001-\u0003\u00191wN]7biR\u0011A\u0006\u001c\u0005\u0006[&\u0001\raX\u0001\u0003kN$\"\u0001L8\t\u000bAT\u0001\u0019A9\u0002\u0005Q\u001c\bC\u0001:u\u001b\u0005\u0019(B\u0001\r=\u0013\t)8OA\u0005US6,7\u000f^1naR\u0011Af\u001e\u0005\u0006q.\u0001\r!_\u0001\bS:\u001cH/\u00198u!\tI$0\u0003\u0002|u\t9\u0011J\\:uC:$\u0018!\u0006<bY&$\u0017\r^3QCR$XM\u001d8TiJLgn\u001a\u000b\u0004}\u0006\r\u0001C\u0001\u0012\u0000\u0013\r\t\ta\t\u0002\u0005+:LG\u000f\u0003\u0004\u0002\u00061\u0001\raR\u0001\fG\",7m\u001b'fO\u0006\u001c\u00170\u0001\u0010MK\u001e\f7-_*j[BdW\rV5nKN$\u0018-\u001c9G_Jl\u0017\r\u001e;feB\u0011\u0001FD\n\u0005\u001d\u0005\ni\u0001\u0005\u0003\u0002\u0010\u0005UQBAA\t\u0015\r\t\u0019\u0002P\u0001\u0003S>LA!a\u0006\u0002\u0012\ta1+\u001a:jC2L'0\u00192mKR\u0011\u0011\u0011B\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u0005}!fA$\u0002\"-\u0012\u00111\u0005\t\u0005\u0003K\ty#\u0004\u0002\u0002()!\u0011\u0011FA\u0016\u0003%)hn\u00195fG.,GMC\u0002\u0002.\r\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t$a\n\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00028A!\u0011\u0011HA \u001b\t\tYDC\u0002\u0002>q\nA\u0001\\1oO&!\u0011\u0011IA\u001e\u0005\u0019y%M[3di\u0002"
)
public class LegacySimpleTimestampFormatter implements TimestampFormatter {
   private transient SimpleDateFormat sdf;
   private final String pattern;
   private final ZoneId zoneId;
   private final Locale locale;
   private final boolean lenient;
   private transient volatile boolean bitmap$trans$0;

   public static boolean $lessinit$greater$default$4() {
      return LegacySimpleTimestampFormatter$.MODULE$.$lessinit$greater$default$4();
   }

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

   private SimpleDateFormat sdf$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$trans$0) {
            SimpleDateFormat formatter = new SimpleDateFormat(this.pattern, this.locale);
            formatter.setTimeZone(TimeZone.getTimeZone(this.zoneId));
            formatter.setLenient(this.lenient);
            this.sdf = formatter;
            this.bitmap$trans$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return this.sdf;
   }

   private SimpleDateFormat sdf() {
      return !this.bitmap$trans$0 ? this.sdf$lzycompute() : this.sdf;
   }

   public long parse(final String s) {
      return SparkDateTimeUtils$.MODULE$.fromJavaTimestamp(this.zoneId.getId(), new Timestamp(this.sdf().parse(s).getTime()));
   }

   public Option parseOptional(final String s) {
      Date date = this.sdf().parse(s, new ParsePosition(0));
      return (Option)(date == null ? .MODULE$ : new Some(BoxesRunTime.boxToLong(SparkDateTimeUtils$.MODULE$.fromJavaTimestamp(this.zoneId.getId(), new Timestamp(date.getTime())))));
   }

   public String format(final long us) {
      return this.sdf().format(SparkDateTimeUtils$.MODULE$.toJavaTimestamp(this.zoneId.getId(), us));
   }

   public String format(final Timestamp ts) {
      return this.sdf().format(ts);
   }

   public String format(final Instant instant) {
      return this.format(SparkDateTimeUtils$.MODULE$.instantToMicros(instant));
   }

   public void validatePatternString(final boolean checkLegacy) {
      this.sdf();
   }

   public LegacySimpleTimestampFormatter(final String pattern, final ZoneId zoneId, final Locale locale, final boolean lenient) {
      this.pattern = pattern;
      this.zoneId = zoneId;
      this.locale = locale;
      this.lenient = lenient;
      TimestampFormatter.$init$(this);
   }
}
