package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005u4A!\u0004\b\u00017!A\u0011\u0006\u0001B\u0001B\u0003%!\u0006\u0003\u00056\u0001\t\u0005\t\u0015!\u00037\u0011!i\u0004A!A!\u0002\u0013q\u0004\u0002C#\u0001\u0005\u0003\u0005\u000b\u0011\u0002$\t\u000b%\u0003A\u0011\u0001&\t\u0011A\u0003\u0001R1A\u0005\nEC\u0001B\u0018\u0001\t\u0006\u0004%\tb\u0018\u0005\u0006C\u0002!\tE\u0019\u0005\u0006+\u0002!\t\u0005\u001b\u0005\u0006+\u0002!\te\u001c\u0005\u0006+\u0002!\tE\u001d\u0005\u0006q\u0002!\t%\u001f\u0002\u0015\u0013N|\u0007H\u000e\u00192\t\u0006$XMR8s[\u0006$H/\u001a:\u000b\u0005=\u0001\u0012\u0001B;uS2T!!\u0005\n\u0002\u0011\r\fG/\u00197zgRT!a\u0005\u000b\u0002\u0007M\fHN\u0003\u0002\u0016-\u0005)1\u000f]1sW*\u0011q\u0003G\u0001\u0007CB\f7\r[3\u000b\u0003e\t1a\u001c:h\u0007\u0001\u0019B\u0001\u0001\u000f#MA\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\u0004\"a\t\u0013\u000e\u00039I!!\n\b\u0003\u001b\u0011\u000bG/\u001a$pe6\fG\u000f^3s!\t\u0019s%\u0003\u0002)\u001d\t9B)\u0019;f)&lWMR8s[\u0006$H/\u001a:IK2\u0004XM]\u0001\ba\u0006$H/\u001a:o!\tY#G\u0004\u0002-aA\u0011QFH\u0007\u0002])\u0011qFG\u0001\u0007yI|w\u000e\u001e \n\u0005Er\u0012A\u0002)sK\u0012,g-\u0003\u00024i\t11\u000b\u001e:j]\u001eT!!\r\u0010\u0002\r1|7-\u00197f!\t94(D\u00019\u0015\ty\u0011HC\u0001;\u0003\u0011Q\u0017M^1\n\u0005qB$A\u0002'pG\u0006dW-\u0001\u0007mK\u001e\f7-\u001f$pe6\fG\u000f\u0005\u0002@\u0005:\u00111\u0005Q\u0005\u0003\u0003:\t\u0011\u0003T3hC\u000eLH)\u0019;f\r>\u0014X.\u0019;t\u0013\t\u0019EI\u0001\tMK\u001e\f7-\u001f#bi\u00164uN]7bi*\u0011\u0011ID\u0001\nSN\u0004\u0016M]:j]\u001e\u0004\"!H$\n\u0005!s\"a\u0002\"p_2,\u0017M\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b-cUJT(\u0011\u0005\r\u0002\u0001\"B\u0015\u0006\u0001\u0004Q\u0003\"B\u001b\u0006\u0001\u00041\u0004\"B\u001f\u0006\u0001\u0004q\u0004\"B#\u0006\u0001\u00041\u0015!\u00034pe6\fG\u000f^3s+\u0005\u0011\u0006CA*Y\u001b\u0005!&BA+W\u0003\u00191wN]7bi*\u0011q+O\u0001\u0005i&lW-\u0003\u0002Z)\n\tB)\u0019;f)&lWMR8s[\u0006$H/\u001a:)\u0005\u0019Y\u0006CA\u000f]\u0013\tifDA\u0005ue\u0006t7/[3oi\u0006yA.Z4bGf4uN]7biR,'/F\u0001#Q\t91,A\u0003qCJ\u001cX\r\u0006\u0002dMB\u0011Q\u0004Z\u0005\u0003Kz\u00111!\u00138u\u0011\u00159\u0007\u00021\u0001+\u0003\u0005\u0019HC\u0001\u0016j\u0011\u0015Q\u0017\u00021\u0001l\u0003%awnY1m\t\u0006$X\r\u0005\u0002m[6\ta+\u0003\u0002o-\nIAj\\2bY\u0012\u000bG/\u001a\u000b\u0003UADQ!\u001d\u0006A\u0002\r\fA\u0001Z1zgR\u0011!f\u001d\u0005\u0006i.\u0001\r!^\u0001\u0005I\u0006$X\r\u0005\u00028m&\u0011q\u000f\u000f\u0002\u0005\t\u0006$X-A\u000bwC2LG-\u0019;f!\u0006$H/\u001a:o'R\u0014\u0018N\\4\u0015\u0003i\u0004\"!H>\n\u0005qt\"\u0001B+oSR\u0004"
)
public class Iso8601DateFormatter implements DateFormatter, DateTimeFormatterHelper {
   private transient DateTimeFormatter formatter;
   private transient DateFormatter legacyFormatter;
   private final String pattern;
   private final Locale locale;
   private final Enumeration.Value legacyFormat;
   private final boolean isParsing;
   private transient volatile byte bitmap$trans$0;

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

   private DateTimeFormatter formatter() {
      return (byte)(this.bitmap$trans$0 & 1) == 0 ? this.formatter$lzycompute() : this.formatter;
   }

   private DateFormatter legacyFormatter$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$trans$0 & 2) == 0) {
            this.legacyFormatter = DateFormatter$.MODULE$.getLegacyFormatter(this.pattern, this.locale, this.legacyFormat);
            this.bitmap$trans$0 = (byte)(this.bitmap$trans$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.legacyFormatter;
   }

   public DateFormatter legacyFormatter() {
      return (byte)(this.bitmap$trans$0 & 2) == 0 ? this.legacyFormatter$lzycompute() : this.legacyFormatter;
   }

   public int parse(final String s) {
      int var10000;
      try {
         LocalDate localDate = this.toLocalDate(this.formatter().parse(s));
         var10000 = SparkDateTimeUtils$.MODULE$.localDateToDays(localDate);
      } catch (Throwable var5) {
         PartialFunction catchExpr$1 = this.checkParsedDiff(s, (sx) -> BoxesRunTime.boxToInteger($anonfun$parse$1(this, sx)));
         if (!catchExpr$1.isDefinedAt(var5)) {
            throw var5;
         }

         var10000 = BoxesRunTime.unboxToInt(catchExpr$1.apply(var5));
      }

      return var10000;
   }

   public String format(final LocalDate localDate) {
      String var10000;
      try {
         var10000 = localDate.format(this.formatter());
      } catch (Throwable var4) {
         PartialFunction catchExpr$2 = this.checkFormattedDiff(SparkDateTimeUtils$.MODULE$.toJavaDate(SparkDateTimeUtils$.MODULE$.localDateToDays(localDate)), (d) -> this.format(d));
         if (!catchExpr$2.isDefinedAt(var4)) {
            throw var4;
         }

         var10000 = (String)catchExpr$2.apply(var4);
      }

      return var10000;
   }

   public String format(final int days) {
      return this.format(LocalDate.ofEpochDay((long)days));
   }

   public String format(final Date date) {
      return this.legacyFormatter().format(date);
   }

   public void validatePatternString() {
      try {
         this.formatter();
      } catch (Throwable var3) {
         PartialFunction catchExpr$3 = this.checkLegacyFormatter(this.pattern, (JFunction0.mcV.sp)() -> this.legacyFormatter().validatePatternString());
         if (!catchExpr$3.isDefinedAt(var3)) {
            throw var3;
         }

         catchExpr$3.apply(var3);
      }

   }

   // $FF: synthetic method
   public static final int $anonfun$parse$1(final Iso8601DateFormatter $this, final String s) {
      return $this.legacyFormatter().parse(s);
   }

   public Iso8601DateFormatter(final String pattern, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      this.pattern = pattern;
      this.locale = locale;
      this.legacyFormat = legacyFormat;
      this.isParsing = isParsing;
      DateTimeFormatterHelper.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
