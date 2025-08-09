package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Date;
import java.util.Locale;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.TimestampNTZType$;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.Option;
import scala.PartialFunction;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dg\u0001\u0002\u000e\u001c\u0001!B\u0001B\u000e\u0001\u0003\u0002\u0003\u0006Ia\u000e\u0005\t\u0005\u0002\u0011\t\u0011)A\u0005\u0007\"A1\n\u0001B\u0001B\u0003%A\n\u0003\u0005R\u0001\t\u0005\t\u0015!\u0003S\u0011!)\u0007A!A!\u0002\u00131\u0007\"B5\u0001\t\u0003Q\u0007\u0002C9\u0001\u0011\u000b\u0007I\u0011\u0003:\t\u0011u\u0004\u0001R1A\u0005\nID\u0011b \u0001\t\u0006\u0004%\t\"!\u0001\t\u000f\u0005\u0015\u0001\u0001\"\u0011\u0002\b!9\u0011\u0011\u0004\u0001\u0005\n\u0005m\u0001bBA\u0017\u0001\u0011\u0005\u0013q\u0006\u0005\b\u0003g\u0001A\u0011IA\u001b\u0011\u001d\ti\u0004\u0001C\u0005\u0003\u007fAq!a\u0012\u0001\t\u0003\nI\u0005\u0003\u0004w\u0001\u0011\u0005\u0013q\n\u0005\u0007m\u0002!\t%a\u0017\t\rY\u0004A\u0011IA1\u0011\u00191\b\u0001\"\u0011\u0002r!9\u0011Q\u0010\u0001\u0005B\u0005}t!CAF7\u0005\u0005\t\u0012AAG\r!Q2$!A\t\u0002\u0005=\u0005BB5\u0017\t\u0003\ti\nC\u0005\u0002 Z\t\n\u0011\"\u0001\u0002\"\"I\u0011q\u0017\f\u0002\u0002\u0013%\u0011\u0011\u0018\u0002\u001a\u0013N|\u0007H\u000e\u00192)&lWm\u001d;b[B4uN]7biR,'O\u0003\u0002\u001d;\u0005!Q\u000f^5m\u0015\tqr$\u0001\u0005dCR\fG._:u\u0015\t\u0001\u0013%A\u0002tc2T!AI\u0012\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u0011*\u0013AB1qC\u000eDWMC\u0001'\u0003\ry'oZ\u0002\u0001'\u0011\u0001\u0011fL\u001a\u0011\u0005)jS\"A\u0016\u000b\u00031\nQa]2bY\u0006L!AL\u0016\u0003\r\u0005s\u0017PU3g!\t\u0001\u0014'D\u0001\u001c\u0013\t\u00114D\u0001\nUS6,7\u000f^1na\u001a{'/\\1ui\u0016\u0014\bC\u0001\u00195\u0013\t)4DA\fECR,G+[7f\r>\u0014X.\u0019;uKJDU\r\u001c9fe\u00069\u0001/\u0019;uKJt\u0007C\u0001\u001d@\u001d\tIT\b\u0005\u0002;W5\t1H\u0003\u0002=O\u00051AH]8pizJ!AP\u0016\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0015I\u0001\u0004TiJLgn\u001a\u0006\u0003}-\naA_8oK&#\u0007C\u0001#J\u001b\u0005)%B\u0001$H\u0003\u0011!\u0018.\\3\u000b\u0003!\u000bAA[1wC&\u0011!*\u0012\u0002\u00075>tW-\u00133\u0002\r1|7-\u00197f!\tiu*D\u0001O\u0015\tar)\u0003\u0002Q\u001d\n1Aj\\2bY\u0016\fA\u0002\\3hC\u000eLhi\u001c:nCR\u0004\"a\u00152\u000f\u0005Q\u0003gBA+`\u001d\t1fL\u0004\u0002X;:\u0011\u0001\f\u0018\b\u00033ns!A\u000f.\n\u0003\u0019J!\u0001J\u0013\n\u0005\t\u001a\u0013B\u0001\u0011\"\u0013\tqr$\u0003\u0002\u001d;%\u0011\u0011mG\u0001\u0012\u0019\u0016<\u0017mY=ECR,gi\u001c:nCR\u001c\u0018BA2e\u0005AaUmZ1ds\u0012\u000bG/\u001a$pe6\fGO\u0003\u0002b7\u0005I\u0011n\u001d)beNLgn\u001a\t\u0003U\u001dL!\u0001[\u0016\u0003\u000f\t{w\u000e\\3b]\u00061A(\u001b8jiz\"ba\u001b7n]>\u0004\bC\u0001\u0019\u0001\u0011\u00151d\u00011\u00018\u0011\u0015\u0011e\u00011\u0001D\u0011\u0015Ye\u00011\u0001M\u0011\u001d\tf\u0001%AA\u0002ICQ!\u001a\u0004A\u0002\u0019\f\u0011BZ8s[\u0006$H/\u001a:\u0016\u0003M\u0004\"\u0001^<\u000e\u0003UT!A^#\u0002\r\u0019|'/\\1u\u0013\tAXOA\tECR,G+[7f\r>\u0014X.\u0019;uKJD#a\u0002>\u0011\u0005)Z\u0018B\u0001?,\u0005%!(/\u00198tS\u0016tG/\u0001\b{_:,GMR8s[\u0006$H/\u001a:)\u0005!Q\u0018a\u00047fO\u0006\u001c\u0017PR8s[\u0006$H/\u001a:\u0016\u0003=B#!\u0003>\u0002\u001bA\f'o]3PaRLwN\\1m)\u0011\tI!!\u0006\u0011\u000b)\nY!a\u0004\n\u0007\u000551F\u0001\u0004PaRLwN\u001c\t\u0004U\u0005E\u0011bAA\nW\t!Aj\u001c8h\u0011\u0019\t9B\u0003a\u0001o\u0005\t1/A\u0007fqR\u0014\u0018m\u0019;NS\u000e\u0014xn\u001d\u000b\u0005\u0003\u001f\ti\u0002C\u0004\u0002 -\u0001\r!!\t\u0002\rA\f'o]3e!\u0011\t\u0019#!\u000b\u000e\u0005\u0005\u0015\"bAA\u0014\u000b\u0006AA/Z7q_J\fG.\u0003\u0003\u0002,\u0005\u0015\"\u0001\u0005+f[B|'/\u00197BG\u000e,7o]8s\u0003\u0015\u0001\u0018M]:f)\u0011\ty!!\r\t\r\u0005]A\u00021\u00018\u0003q\u0001\u0018M]:f/&$\bn\\;u)&lWMW8oK>\u0003H/[8oC2$b!!\u0003\u00028\u0005e\u0002BBA\f\u001b\u0001\u0007q\u0007\u0003\u0004\u0002<5\u0001\rAZ\u0001\u000eC2dwn\u001e+j[\u0016TvN\\3\u0002!\u0015DHO]1di6K7M]8t\u001dRSF\u0003CA\b\u0003\u0003\n\u0019%!\u0012\t\r\u0005]a\u00021\u00018\u0011\u001d\tyB\u0004a\u0001\u0003CAa!a\u000f\u000f\u0001\u00041\u0017\u0001\u00069beN,w+\u001b;i_V$H+[7f5>tW\r\u0006\u0004\u0002\u0010\u0005-\u0013Q\n\u0005\u0007\u0003/y\u0001\u0019A\u001c\t\r\u0005mr\u00021\u0001g)\r9\u0014\u0011\u000b\u0005\b\u0003'\u0002\u0002\u0019AA+\u0003\u001dIgn\u001d;b]R\u00042\u0001RA,\u0013\r\tI&\u0012\u0002\b\u0013:\u001cH/\u00198u)\r9\u0014Q\f\u0005\b\u0003?\n\u0002\u0019AA\b\u0003\t)8\u000fF\u00028\u0003GBq!!\u001a\u0013\u0001\u0004\t9'\u0001\u0002ugB!\u0011\u0011NA7\u001b\t\tYG\u0003\u0002!\u000f&!\u0011qNA6\u0005%!\u0016.\\3ti\u0006l\u0007\u000fF\u00028\u0003gBq!!\u001e\u0014\u0001\u0004\t9(A\u0007m_\u000e\fG\u000eR1uKRKW.\u001a\t\u0004\t\u0006e\u0014bAA>\u000b\niAj\\2bY\u0012\u000bG/\u001a+j[\u0016\fQC^1mS\u0012\fG/\u001a)biR,'O\\*ue&tw\r\u0006\u0003\u0002\u0002\u0006\u001d\u0005c\u0001\u0016\u0002\u0004&\u0019\u0011QQ\u0016\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003\u0013#\u0002\u0019\u00014\u0002\u0017\rDWmY6MK\u001e\f7-_\u0001\u001a\u0013N|\u0007H\u000e\u00192)&lWm\u001d;b[B4uN]7biR,'\u000f\u0005\u00021-M!a#KAI!\u0011\t\u0019*!'\u000e\u0005\u0005U%bAAL\u000f\u0006\u0011\u0011n\\\u0005\u0005\u00037\u000b)J\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002\u000e\u0006YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*\"!a)+\u0007I\u000b)k\u000b\u0002\u0002(B!\u0011\u0011VAZ\u001b\t\tYK\u0003\u0003\u0002.\u0006=\u0016!C;oG\",7m[3e\u0015\r\t\tlK\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA[\u0003W\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\tY\f\u0005\u0003\u0002>\u0006\rWBAA`\u0015\r\t\tmR\u0001\u0005Y\u0006tw-\u0003\u0003\u0002F\u0006}&AB(cU\u0016\u001cG\u000f"
)
public class Iso8601TimestampFormatter implements TimestampFormatter, DateTimeFormatterHelper {
   private transient DateTimeFormatter formatter;
   private transient DateTimeFormatter zonedFormatter;
   private transient TimestampFormatter legacyFormatter;
   private final String pattern;
   private final ZoneId zoneId;
   private final Locale locale;
   private final Enumeration.Value legacyFormat;
   private final boolean isParsing;
   private transient volatile byte bitmap$trans$0;

   public static Enumeration.Value $lessinit$greater$default$4() {
      return Iso8601TimestampFormatter$.MODULE$.$lessinit$greater$default$4();
   }

   public LocalDate toLocalDate(final TemporalAccessor accessor) {
      return DateTimeFormatterHelper.toLocalDate$(this, accessor);
   }

   public LocalTime toLocalTime(final TemporalAccessor accessor) {
      return DateTimeFormatterHelper.toLocalTime$(this, accessor);
   }

   public ZonedDateTime toZonedDateTime(final TemporalAccessor accessor, final ZoneId zoneId) {
      return DateTimeFormatterHelper.toZonedDateTime$(this, accessor, zoneId);
   }

   public DateTimeFormatter getOrCreateFormatter(final String pattern, final Locale locale, final boolean isParsing) {
      return DateTimeFormatterHelper.getOrCreateFormatter$(this, pattern, locale, isParsing);
   }

   public PartialFunction checkParsedDiff(final String s, final Function1 legacyParseFunc) {
      return DateTimeFormatterHelper.checkParsedDiff$(this, s, legacyParseFunc);
   }

   public PartialFunction checkFormattedDiff(final Date d, final Function1 legacyFormatFunc) {
      return DateTimeFormatterHelper.checkFormattedDiff$(this, d, legacyFormatFunc);
   }

   public PartialFunction checkLegacyFormatter(final String pattern, final Function0 tryLegacyFormatter) {
      return DateTimeFormatterHelper.checkLegacyFormatter$(this, pattern, tryLegacyFormatter);
   }

   public PartialFunction checkInvalidPattern(final String pattern) {
      return DateTimeFormatterHelper.checkInvalidPattern$(this, pattern);
   }

   public final long parseWithoutTimeZone(final String s) throws ParseException, DateTimeParseException, DateTimeException, IllegalStateException {
      return TimestampFormatter.parseWithoutTimeZone$(this, s);
   }

   private DateTimeFormatter formatter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 1) == 0) {
            this.formatter = this.getOrCreateFormatter(this.pattern, this.locale, this.isParsing);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.formatter;
   }

   public DateTimeFormatter formatter() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.formatter$lzycompute() : this.formatter;
   }

   private DateTimeFormatter zonedFormatter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.zonedFormatter = this.formatter().withZone(this.zoneId);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.zonedFormatter;
   }

   private DateTimeFormatter zonedFormatter() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.zonedFormatter$lzycompute() : this.zonedFormatter;
   }

   private TimestampFormatter legacyFormatter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 4) == 0) {
            this.legacyFormatter = TimestampFormatter$.MODULE$.getLegacyFormatter(this.pattern, this.zoneId, this.locale, this.legacyFormat);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.legacyFormatter;
   }

   public TimestampFormatter legacyFormatter() {
      return (byte)(this.bitmap$trans$0 & 4) == 0 ? this.legacyFormatter$lzycompute() : this.legacyFormatter;
   }

   public Option parseOptional(final String s) {
      Object var10000;
      try {
         ParsePosition parsePosition = new ParsePosition(0);
         TemporalAccessor parsed = this.formatter().parseUnresolved(s, parsePosition);
         var10000 = parsed != null && s.length() == parsePosition.getIndex() ? new Some(BoxesRunTime.boxToLong(this.extractMicros(parsed))) : .MODULE$;
      } catch (Throwable var8) {
         if (var8 != null) {
            Option var7 = scala.util.control.NonFatal..MODULE$.unapply(var8);
            if (!var7.isEmpty()) {
               var10000 = .MODULE$;
               return (Option)var10000;
            }
         }

         throw var8;
      }

      return (Option)var10000;
   }

   private long extractMicros(final TemporalAccessor parsed) {
      ZoneId parsedZoneId = (ZoneId)parsed.query(TemporalQueries.zone());
      ZoneId timeZoneId = parsedZoneId == null ? this.zoneId : parsedZoneId;
      ZonedDateTime zonedDateTime = this.toZonedDateTime(parsed, timeZoneId);
      long epochSeconds = zonedDateTime.toEpochSecond();
      int microsOfSecond = zonedDateTime.get(ChronoField.MICRO_OF_SECOND);
      return Math.addExact(Math.multiplyExact(epochSeconds, 1000000L), (long)microsOfSecond);
   }

   public long parse(final String s) {
      long var10000;
      try {
         TemporalAccessor parsed = this.formatter().parse(s);
         var10000 = this.extractMicros(parsed);
      } catch (Throwable var5) {
         PartialFunction catchExpr$1 = this.checkParsedDiff(s, (sx) -> BoxesRunTime.boxToLong($anonfun$parse$1(this, sx)));
         if (!catchExpr$1.isDefinedAt(var5)) {
            throw var5;
         }

         var10000 = BoxesRunTime.unboxToLong(catchExpr$1.apply(var5));
      }

      return var10000;
   }

   public Option parseWithoutTimeZoneOptional(final String s, final boolean allowTimeZone) {
      Object var10000;
      try {
         ParsePosition parsePosition = new ParsePosition(0);
         TemporalAccessor parsed = this.formatter().parseUnresolved(s, parsePosition);
         var10000 = parsed != null && s.length() == parsePosition.getIndex() ? new Some(BoxesRunTime.boxToLong(this.extractMicrosNTZ(s, parsed, allowTimeZone))) : .MODULE$;
      } catch (Throwable var9) {
         if (var9 != null) {
            Option var8 = scala.util.control.NonFatal..MODULE$.unapply(var9);
            if (!var8.isEmpty()) {
               var10000 = .MODULE$;
               return (Option)var10000;
            }
         }

         throw var9;
      }

      return (Option)var10000;
   }

   private long extractMicrosNTZ(final String s, final TemporalAccessor parsed, final boolean allowTimeZone) {
      if (!allowTimeZone && parsed.query(TemporalQueries.zone()) != null) {
         throw ExecutionErrors$.MODULE$.cannotParseStringAsDataTypeError(this.pattern, s, TimestampNTZType$.MODULE$);
      } else {
         LocalDate localDate = this.toLocalDate(parsed);
         LocalTime localTime = this.toLocalTime(parsed);
         return SparkDateTimeUtils$.MODULE$.localDateTimeToMicros(LocalDateTime.of(localDate, localTime));
      }
   }

   public long parseWithoutTimeZone(final String s, final boolean allowTimeZone) {
      long var10000;
      try {
         TemporalAccessor parsed = this.formatter().parse(s);
         var10000 = this.extractMicrosNTZ(s, parsed, allowTimeZone);
      } catch (Throwable var6) {
         PartialFunction catchExpr$2 = this.checkParsedDiff(s, (sx) -> BoxesRunTime.boxToLong($anonfun$parseWithoutTimeZone$1(this, sx)));
         if (!catchExpr$2.isDefinedAt(var6)) {
            throw var6;
         }

         var10000 = BoxesRunTime.unboxToLong(catchExpr$2.apply(var6));
      }

      return var10000;
   }

   public String format(final Instant instant) {
      String var10000;
      try {
         var10000 = this.zonedFormatter().format(instant);
      } catch (Throwable var4) {
         PartialFunction catchExpr$3 = this.checkFormattedDiff(SparkDateTimeUtils$.MODULE$.toJavaTimestamp(SparkDateTimeUtils$.MODULE$.instantToMicros(instant)), (t) -> this.format(t));
         if (!catchExpr$3.isDefinedAt(var4)) {
            throw var4;
         }

         var10000 = (String)catchExpr$3.apply(var4);
      }

      return var10000;
   }

   public String format(final long us) {
      Instant instant = SparkDateTimeUtils$.MODULE$.microsToInstant(us);
      return this.format(instant);
   }

   public String format(final Timestamp ts) {
      return this.legacyFormatter().format(ts);
   }

   public String format(final LocalDateTime localDateTime) {
      return localDateTime.format(this.formatter());
   }

   public void validatePatternString(final boolean checkLegacy) {
      if (checkLegacy) {
         try {
            this.formatter();
         } catch (Throwable var6) {
            PartialFunction catchExpr$4 = this.checkLegacyFormatter(this.pattern, (JFunction0.mcV.sp)() -> this.legacyFormatter().validatePatternString(true));
            if (!catchExpr$4.isDefinedAt(var6)) {
               throw var6;
            }

            catchExpr$4.apply(var6);
         }

      } else {
         try {
            this.formatter();
         } catch (Throwable var7) {
            PartialFunction catchExpr$5 = this.checkInvalidPattern(this.pattern);
            if (!catchExpr$5.isDefinedAt(var7)) {
               throw var7;
            }

            catchExpr$5.apply(var7);
         }

      }
   }

   // $FF: synthetic method
   public static final long $anonfun$parse$1(final Iso8601TimestampFormatter $this, final String s) {
      return $this.legacyFormatter().parse(s);
   }

   // $FF: synthetic method
   public static final long $anonfun$parseWithoutTimeZone$1(final Iso8601TimestampFormatter $this, final String s) {
      return $this.legacyFormatter().parse(s);
   }

   public Iso8601TimestampFormatter(final String pattern, final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      this.pattern = pattern;
      this.zoneId = zoneId;
      this.locale = locale;
      this.legacyFormat = legacyFormat;
      this.isParsing = isParsing;
      TimestampFormatter.$init$(this);
      DateTimeFormatterHelper.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
