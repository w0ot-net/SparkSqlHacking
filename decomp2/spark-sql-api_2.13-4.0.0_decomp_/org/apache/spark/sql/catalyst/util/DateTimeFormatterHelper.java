package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Date;
import java.util.Locale;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.internal.LegacyBehaviorPolicy$;
import org.apache.spark.sql.internal.SqlApiConf$;
import scala.Enumeration;
import scala.Function0;
import scala.Function1;
import scala.PartialFunction;
import scala.Tuple3;
import scala.collection.StringOps.;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005caB\u0012%!\u0003\r\t!\r\u0005\u0006q\u0001!\t!\u000f\u0005\u0006{\u0001!IA\u0010\u0005\u0006+\u0002!IA\u0016\u0005\u0006?\u0002!\t\u0002\u0019\u0005\u0006E\u0002!\tb\u0019\u0005\u0006Q\u0002!\t\"\u001b\u0005\u0006g\u0002!\t\u0002\u001e\u0005\b\u0003S\u0001A\u0011BA\u0016\u0011\u001d\t\u0019\u0005\u0001C\t\u0003\u000bBq!a\u001d\u0001\t#\t)\bC\u0004\u0002\u0012\u0002!\t\"a%\t\u000f\u0005\r\u0006\u0001\"\u0005\u0002&\u001e9\u00111\u0016\u0013\t\n\u00055fAB\u0012%\u0011\u0013\t\t\fC\u0004\u00024:!\t!!.\u0006\r\u0005]f\u0002BA]\u0011%\tyL\u0004b\u0001\n\u0013\t\t\r\u0003\u0005\u0002N:\u0001\u000b\u0011BAb\u0011%\tyM\u0004b\u0001\n\u000b\t\t\u000e\u0003\u0005\u0002b:\u0001\u000bQBAj\u0011\u001d\t\u0019O\u0004C\u0001\u0003KDq!!<\u000f\t\u0003\ty\u000fC\u0004\u0002x:!\t!!?\t\u000f\u0005uh\u0002\"\u0001\u0002\u0000\"Q!\u0011\u0002\b\t\u0006\u0004%\tAa\u0003\t\u0013\t5aB1A\u0005\u0006\t=\u0001\u0002\u0003B\u0014\u001d\u0001\u0006iA!\u0005\t\u0013\t%bB1A\u0005\u0006\t=\u0001\u0002\u0003B\u0016\u001d\u0001\u0006iA!\u0005\t\u0013\t5bB1A\u0005\u0006\t=\u0001\u0002\u0003B\u0018\u001d\u0001\u0006iA!\u0005\t\u0013\tEbB1A\u0005\u0006\tM\u0002\u0002\u0003B\u001c\u001d\u0001\u0006iA!\u000e\t\u000f\teb\u0002\"\u0001\u0003<\t9B)\u0019;f)&lWMR8s[\u0006$H/\u001a:IK2\u0004XM\u001d\u0006\u0003K\u0019\nA!\u001e;jY*\u0011q\u0005K\u0001\tG\u0006$\u0018\r\\=ti*\u0011\u0011FK\u0001\u0004gFd'BA\u0016-\u0003\u0015\u0019\b/\u0019:l\u0015\tic&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002_\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\r\t\u0003gYj\u0011\u0001\u000e\u0006\u0002k\u0005)1oY1mC&\u0011q\u0007\u000e\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005Q\u0004CA\u001a<\u0013\taDG\u0001\u0003V]&$\u0018\u0001D4fi>\u0013H)\u001a4bk2$H\u0003B C\u001dN\u0003\"a\r!\n\u0005\u0005#$aA%oi\")1I\u0001a\u0001\t\u0006A\u0011mY2fgN|'\u000f\u0005\u0002F\u00196\taI\u0003\u0002H\u0011\u0006AA/Z7q_J\fGN\u0003\u0002J\u0015\u0006!A/[7f\u0015\u0005Y\u0015\u0001\u00026bm\u0006L!!\u0014$\u0003!Q+W\u000e]8sC2\f5mY3tg>\u0014\b\"B(\u0003\u0001\u0004\u0001\u0016!\u00024jK2$\u0007CA#R\u0013\t\u0011fIA\u0006DQJ|gn\u001c$jK2$\u0007\"\u0002+\u0003\u0001\u0004y\u0014a\u00023fM\u0006,H\u000e^\u0001\u0010m\u0016\u0014\u0018NZ=M_\u000e\fG\u000eR1uKR!!h\u0016-Z\u0011\u0015\u00195\u00011\u0001E\u0011\u0015y5\u00011\u0001Q\u0011\u0015Q6\u00011\u0001\\\u0003%\u0019\u0017M\u001c3jI\u0006$X\r\u0005\u0002];6\t\u0001*\u0003\u0002_\u0011\nIAj\\2bY\u0012\u000bG/Z\u0001\fi>dunY1m\t\u0006$X\r\u0006\u0002\\C\")1\t\u0002a\u0001\t\u0006YAo\u001c'pG\u0006dG+[7f)\t!w\r\u0005\u0002]K&\u0011a\r\u0013\u0002\n\u0019>\u001c\u0017\r\u001c+j[\u0016DQaQ\u0003A\u0002\u0011\u000bq\u0002^8[_:,G\rR1uKRKW.\u001a\u000b\u0004U6t\u0007C\u0001/l\u0013\ta\u0007JA\u0007[_:,G\rR1uKRKW.\u001a\u0005\u0006\u0007\u001a\u0001\r\u0001\u0012\u0005\u0006_\u001a\u0001\r\u0001]\u0001\u0007u>tW-\u00133\u0011\u0005q\u000b\u0018B\u0001:I\u0005\u0019QvN\\3JI\u0006!r-\u001a;Pe\u000e\u0013X-\u0019;f\r>\u0014X.\u0019;uKJ$b!^>\u0002\u0012\u0005}\u0001C\u0001<z\u001b\u00059(B\u0001=I\u0003\u00191wN]7bi&\u0011!p\u001e\u0002\u0012\t\u0006$X\rV5nK\u001a{'/\\1ui\u0016\u0014\b\"\u0002?\b\u0001\u0004i\u0018a\u00029biR,'O\u001c\t\u0004}\u0006-abA@\u0002\bA\u0019\u0011\u0011\u0001\u001b\u000e\u0005\u0005\r!bAA\u0003a\u00051AH]8pizJ1!!\u00035\u0003\u0019\u0001&/\u001a3fM&!\u0011QBA\b\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011\u0002\u001b\t\u000f\u0005Mq\u00011\u0001\u0002\u0016\u00051An\\2bY\u0016\u0004B!a\u0006\u0002\u001c5\u0011\u0011\u0011\u0004\u0006\u0003K)KA!!\b\u0002\u001a\t1Aj\\2bY\u0016Dq!!\t\b\u0001\u0004\t\u0019#A\u0005jgB\u000b'o]5oOB\u00191'!\n\n\u0007\u0005\u001dBGA\u0004C_>dW-\u00198\u0002E9,W\rZ\"p]Z,'\u000f\u001e+p'B\f'o[+qOJ\fG-Z#yG\u0016\u0004H/[8o)\u0011\t\u0019#!\f\t\u000f\u0005=\u0002\u00021\u0001\u00022\u0005\tQ\r\u0005\u0003\u00024\u0005ub\u0002BA\u001b\u0003sqA!!\u0001\u00028%\tQ'C\u0002\u0002<Q\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002@\u0005\u0005#!\u0003+ie><\u0018M\u00197f\u0015\r\tY\u0004N\u0001\u0010G\",7m\u001b)beN,G\rR5gMV!\u0011qIA*)\u0019\tI%!\u001a\u0002jA91'a\u0013\u00022\u0005=\u0013bAA'i\ty\u0001+\u0019:uS\u0006dg)\u001e8di&|g\u000e\u0005\u0003\u0002R\u0005MC\u0002\u0001\u0003\b\u0003+J!\u0019AA,\u0005\u0005!\u0016\u0003BA-\u0003?\u00022aMA.\u0013\r\ti\u0006\u000e\u0002\b\u001d>$\b.\u001b8h!\r\u0019\u0014\u0011M\u0005\u0004\u0003G\"$aA!os\"1\u0011qM\u0005A\u0002u\f\u0011a\u001d\u0005\b\u0003WJ\u0001\u0019AA7\u0003=aWmZ1dsB\u000b'o]3Gk:\u001c\u0007CB\u001a\u0002pu\fy%C\u0002\u0002rQ\u0012\u0011BR;oGRLwN\\\u0019\u0002%\rDWmY6G_Jl\u0017\r\u001e;fI\u0012KgMZ\u000b\u0005\u0003o\n\t\t\u0006\u0004\u0002z\u0005m\u00141\u0012\t\u0007g\u0005-\u0013\u0011G?\t\u000f\u0005u$\u00021\u0001\u0002\u0000\u0005\tA\r\u0005\u0003\u0002R\u0005\u0005EaBA+\u0015\t\u0007\u00111Q\t\u0005\u00033\n)\t\u0005\u0003\u0002\u0018\u0005\u001d\u0015\u0002BAE\u00033\u0011A\u0001R1uK\"9\u0011Q\u0012\u0006A\u0002\u0005=\u0015\u0001\u00057fO\u0006\u001c\u0017PR8s[\u0006$h)\u001e8d!\u0019\u0019\u0014qNA@{\u0006!2\r[3dW2+w-Y2z\r>\u0014X.\u0019;uKJ$b!!&\u0002\u0018\u0006e\u0005CB\u001a\u0002L\u0005ER\u000fC\u0003}\u0017\u0001\u0007Q\u0010\u0003\u0005\u0002\u001c.!\t\u0019AAO\u0003I!(/\u001f'fO\u0006\u001c\u0017PR8s[\u0006$H/\u001a:\u0011\tM\nyJO\u0005\u0004\u0003C#$\u0001\u0003\u001fcs:\fW.\u001a \u0002'\rDWmY6J]Z\fG.\u001b3QCR$XM\u001d8\u0015\t\u0005\u001d\u0016\u0011\u0016\t\bg\u0005-\u0013\u0011GA-\u0011\u0015aH\u00021\u0001~\u0003]!\u0015\r^3US6,gi\u001c:nCR$XM\u001d%fYB,'\u000fE\u0002\u00020:i\u0011\u0001J\n\u0003\u001dI\na\u0001P5oSRtDCAAW\u0005!\u0019\u0015m\u00195f\u0017\u0016L\b\u0003C\u001a\u0002<v\f)\"a\t\n\u0007\u0005uFG\u0001\u0004UkBdWmM\u0001\u0006G\u0006\u001c\u0007.Z\u000b\u0003\u0003\u0007\u0004r!a\u0006\u0002F\u0006%W/\u0003\u0003\u0002H\u0006e!aA'baB\u0019\u00111\u001a\t\u000e\u00039\taaY1dQ\u0016\u0004\u0013!C3yiJ\f7\r^8s+\t\t\u0019\u000e\u0005\u0003\u0002V\u0006uWBAAl\u0015\u0011\tI.a7\u0002\u00115\fGo\u00195j]\u001eT!!\n\u001b\n\t\u0005}\u0017q\u001b\u0002\u0006%\u0016<W\r_\u0001\u000bKb$(/Y2u_J\u0004\u0013!D2sK\u0006$XMQ;jY\u0012,'\u000f\u0006\u0002\u0002hB\u0019a/!;\n\u0007\u0005-xO\u0001\rECR,G+[7f\r>\u0014X.\u0019;uKJ\u0014U/\u001b7eKJ\f1\u0002^8G_Jl\u0017\r\u001e;feR)Q/!=\u0002v\"9\u00111\u001f\fA\u0002\u0005\u001d\u0018a\u00022vS2$WM\u001d\u0005\b\u0003'1\u0002\u0019AA\u000b\u0003!\u001a'/Z1uK\n+\u0018\u000e\u001c3fe^KG\u000f\u001b,be2+gn\u001a;i'\u0016\u001cwN\u001c3Ge\u0006\u001cG/[8o)\u0011\t9/a?\t\u000bq<\u0002\u0019A?\u0002\u001d\t,\u0018\u000e\u001c3G_Jl\u0017\r\u001e;feR9QO!\u0001\u0003\u0004\t\u0015\u0001\"\u0002?\u0019\u0001\u0004i\bbBA\n1\u0001\u0007\u0011Q\u0003\u0005\b\u0005\u000fA\u0002\u0019AA\u0012\u000351\u0018M\u001d'f]\u0016s\u0017M\u00197fI\u0006\tbM]1di&|gNR8s[\u0006$H/\u001a:\u0016\u0003U\f\u0001c^3fW\n\u000b7/\u001a3MKR$XM]:\u0016\u0005\tE\u0001C\u0002B\n\u0005;\u0011\t#\u0004\u0002\u0003\u0016)!!q\u0003B\r\u0003%IW.\\;uC\ndWMC\u0002\u0003\u001cQ\n!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011yB!\u0006\u0003\u0007M+G\u000fE\u00024\u0005GI1A!\n5\u0005\u0011\u0019\u0005.\u0019:\u0002#],Wm\u001b\"bg\u0016$G*\u001a;uKJ\u001c\b%\u0001\nv]N,\b\u000f]8si\u0016$G*\u001a;uKJ\u001c\u0018aE;ogV\u0004\bo\u001c:uK\u0012dU\r\u001e;feN\u0004\u0013\u0001H;ogV\u0004\bo\u001c:uK\u0012dU\r\u001e;feN4uN\u001d)beNLgnZ\u0001\u001ek:\u001cX\u000f\u001d9peR,G\rT3ui\u0016\u00148OR8s!\u0006\u00148/\u001b8hA\u0005IRO\\:vaB|'\u000f^3e!\u0006$H/\u001a:o\u0019\u0016tw\r\u001e5t+\t\u0011)\u0004E\u0003\u0003\u0014\tuQ0\u0001\u000ev]N,\b\u000f]8si\u0016$\u0007+\u0019;uKJtG*\u001a8hi\"\u001c\b%\u0001\u000ed_:4XM\u001d;J]\u000e|W\u000e]1uS\ndW\rU1ui\u0016\u0014h\u000eF\u0003~\u0005{\u0011y\u0004C\u0003}E\u0001\u0007Q\u0010C\u0004\u0002\"\t\u0002\r!a\t"
)
public interface DateTimeFormatterHelper {
   static String convertIncompatiblePattern(final String pattern, final boolean isParsing) {
      return DateTimeFormatterHelper$.MODULE$.convertIncompatiblePattern(pattern, isParsing);
   }

   static Set unsupportedPatternLengths() {
      return DateTimeFormatterHelper$.MODULE$.unsupportedPatternLengths();
   }

   static Set unsupportedLettersForParsing() {
      return DateTimeFormatterHelper$.MODULE$.unsupportedLettersForParsing();
   }

   static Set unsupportedLetters() {
      return DateTimeFormatterHelper$.MODULE$.unsupportedLetters();
   }

   static Set weekBasedLetters() {
      return DateTimeFormatterHelper$.MODULE$.weekBasedLetters();
   }

   static DateTimeFormatter fractionFormatter() {
      return DateTimeFormatterHelper$.MODULE$.fractionFormatter();
   }

   static DateTimeFormatter buildFormatter(final String pattern, final Locale locale, final boolean varLenEnabled) {
      return DateTimeFormatterHelper$.MODULE$.buildFormatter(pattern, locale, varLenEnabled);
   }

   static DateTimeFormatterBuilder createBuilderWithVarLengthSecondFraction(final String pattern) {
      return DateTimeFormatterHelper$.MODULE$.createBuilderWithVarLengthSecondFraction(pattern);
   }

   static DateTimeFormatter toFormatter(final DateTimeFormatterBuilder builder, final Locale locale) {
      return DateTimeFormatterHelper$.MODULE$.toFormatter(builder, locale);
   }

   static DateTimeFormatterBuilder createBuilder() {
      return DateTimeFormatterHelper$.MODULE$.createBuilder();
   }

   static Regex extractor() {
      return DateTimeFormatterHelper$.MODULE$.extractor();
   }

   private int getOrDefault(final TemporalAccessor accessor, final ChronoField field, final int default) {
      return accessor.isSupported(field) ? accessor.get(field) : default;
   }

   private void verifyLocalDate(final TemporalAccessor accessor, final ChronoField field, final LocalDate candidate) {
      if (accessor.isSupported(field)) {
         int actual = accessor.get(field);
         int expected = candidate.get(field);
         if (actual != expected) {
            throw ExecutionErrors$.MODULE$.fieldDiffersFromDerivedLocalDateError(field, actual, expected, candidate);
         }
      }
   }

   // $FF: synthetic method
   static LocalDate toLocalDate$(final DateTimeFormatterHelper $this, final TemporalAccessor accessor) {
      return $this.toLocalDate(accessor);
   }

   default LocalDate toLocalDate(final TemporalAccessor accessor) {
      LocalDate localDate = (LocalDate)accessor.query(TemporalQueries.localDate());
      if (localDate != null) {
         return localDate;
      } else {
         int year = this.getOrDefault(accessor, ChronoField.YEAR, 1970);
         if (accessor.isSupported(ChronoField.DAY_OF_YEAR)) {
            int dayOfYear = accessor.get(ChronoField.DAY_OF_YEAR);
            LocalDate date = LocalDate.ofYearDay(year, dayOfYear);
            this.verifyLocalDate(accessor, ChronoField.MONTH_OF_YEAR, date);
            this.verifyLocalDate(accessor, ChronoField.DAY_OF_MONTH, date);
            return date;
         } else {
            int month = this.getOrDefault(accessor, ChronoField.MONTH_OF_YEAR, 1);
            int day = this.getOrDefault(accessor, ChronoField.DAY_OF_MONTH, 1);
            return LocalDate.of(year, month, day);
         }
      }
   }

   // $FF: synthetic method
   static LocalTime toLocalTime$(final DateTimeFormatterHelper $this, final TemporalAccessor accessor) {
      return $this.toLocalTime(accessor);
   }

   default LocalTime toLocalTime(final TemporalAccessor accessor) {
      LocalTime localTime = (LocalTime)accessor.query(TemporalQueries.localTime());
      if (localTime != null) {
         return localTime;
      } else {
         int hour = accessor.isSupported(ChronoField.HOUR_OF_DAY) ? accessor.get(ChronoField.HOUR_OF_DAY) : (accessor.isSupported(ChronoField.HOUR_OF_AMPM) ? accessor.get(ChronoField.HOUR_OF_AMPM) : (accessor.isSupported(ChronoField.AMPM_OF_DAY) && accessor.get(ChronoField.AMPM_OF_DAY) == 1 ? 12 : 0));
         int minute = this.getOrDefault(accessor, ChronoField.MINUTE_OF_HOUR, 0);
         int second = this.getOrDefault(accessor, ChronoField.SECOND_OF_MINUTE, 0);
         int nanoSecond = this.getOrDefault(accessor, ChronoField.NANO_OF_SECOND, 0);
         return LocalTime.of(hour, minute, second, nanoSecond);
      }
   }

   // $FF: synthetic method
   static ZonedDateTime toZonedDateTime$(final DateTimeFormatterHelper $this, final TemporalAccessor accessor, final ZoneId zoneId) {
      return $this.toZonedDateTime(accessor, zoneId);
   }

   default ZonedDateTime toZonedDateTime(final TemporalAccessor accessor, final ZoneId zoneId) {
      LocalDate localDate = this.toLocalDate(accessor);
      LocalTime localTime = this.toLocalTime(accessor);
      return ZonedDateTime.of(localDate, localTime, zoneId);
   }

   // $FF: synthetic method
   static DateTimeFormatter getOrCreateFormatter$(final DateTimeFormatterHelper $this, final String pattern, final Locale locale, final boolean isParsing) {
      return $this.getOrCreateFormatter(pattern, locale, isParsing);
   }

   default DateTimeFormatter getOrCreateFormatter(final String pattern, final Locale locale, final boolean isParsing) {
      String newPattern = DateTimeFormatterHelper$.MODULE$.convertIncompatiblePattern(pattern, isParsing);
      boolean useVarLen = isParsing && .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(newPattern), 'S');
      Tuple3 key = new Tuple3(newPattern, locale, BoxesRunTime.boxToBoolean(useVarLen));
      return (DateTimeFormatter)DateTimeFormatterHelper$.MODULE$.org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$cache().computeIfAbsent(key, (x$1) -> DateTimeFormatterHelper$.MODULE$.buildFormatter(newPattern, locale, useVarLen));
   }

   // $FF: synthetic method
   static boolean org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$needConvertToSparkUpgradeException$(final DateTimeFormatterHelper $this, final Throwable e) {
      return $this.org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$needConvertToSparkUpgradeException(e);
   }

   default boolean org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$needConvertToSparkUpgradeException(final Throwable e) {
      if (e instanceof DateTimeException) {
         Enumeration.Value var10000 = SqlApiConf$.MODULE$.get().legacyTimeParserPolicy();
         Enumeration.Value var4 = LegacyBehaviorPolicy$.MODULE$.EXCEPTION();
         if (var10000 == null) {
            if (var4 == null) {
               return true;
            }
         } else if (var10000.equals(var4)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   static PartialFunction checkParsedDiff$(final DateTimeFormatterHelper $this, final String s, final Function1 legacyParseFunc) {
      return $this.checkParsedDiff(s, legacyParseFunc);
   }

   default PartialFunction checkParsedDiff(final String s, final Function1 legacyParseFunc) {
      return new Serializable(legacyParseFunc, s) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final DateTimeFormatterHelper $outer;
         private final Function1 legacyParseFunc$1;
         private final String s$1;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (this.$outer.org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$needConvertToSparkUpgradeException(x1)) {
               try {
                  this.legacyParseFunc$1.apply(this.s$1);
               } catch (Throwable var5) {
                  throw x1;
               }

               throw ExecutionErrors$.MODULE$.failToParseDateTimeInNewParserError(this.s$1, x1);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return this.$outer.org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$needConvertToSparkUpgradeException(x1);
         }

         public {
            if (DateTimeFormatterHelper.this == null) {
               throw null;
            } else {
               this.$outer = DateTimeFormatterHelper.this;
               this.legacyParseFunc$1 = legacyParseFunc$1;
               this.s$1 = s$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialFunction checkFormattedDiff$(final DateTimeFormatterHelper $this, final Date d, final Function1 legacyFormatFunc) {
      return $this.checkFormattedDiff(d, legacyFormatFunc);
   }

   default PartialFunction checkFormattedDiff(final Date d, final Function1 legacyFormatFunc) {
      return new Serializable(legacyFormatFunc, d) {
         private static final long serialVersionUID = 0L;
         // $FF: synthetic field
         private final DateTimeFormatterHelper $outer;
         private final Function1 legacyFormatFunc$1;
         private final Date d$1;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (this.$outer.org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$needConvertToSparkUpgradeException(x1)) {
               String var10000;
               try {
                  var10000 = (String)this.legacyFormatFunc$1.apply(this.d$1);
               } catch (Throwable var6) {
                  throw x1;
               }

               String resultCandidate = var10000;
               throw ExecutionErrors$.MODULE$.failToParseDateTimeInNewParserError(resultCandidate, x1);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return this.$outer.org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$needConvertToSparkUpgradeException(x1);
         }

         public {
            if (DateTimeFormatterHelper.this == null) {
               throw null;
            } else {
               this.$outer = DateTimeFormatterHelper.this;
               this.legacyFormatFunc$1 = legacyFormatFunc$1;
               this.d$1 = d$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static PartialFunction checkLegacyFormatter$(final DateTimeFormatterHelper $this, final String pattern, final Function0 tryLegacyFormatter) {
      return $this.checkLegacyFormatter(pattern, tryLegacyFormatter);
   }

   default PartialFunction checkLegacyFormatter(final String pattern, final Function0 tryLegacyFormatter) {
      return new Serializable(tryLegacyFormatter, pattern) {
         private static final long serialVersionUID = 0L;
         private final Function0 tryLegacyFormatter$1;
         private final String pattern$1;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (x1 instanceof IllegalArgumentException var5) {
               try {
                  this.tryLegacyFormatter$1.apply$mcV$sp();
               } catch (Throwable var6) {
                  throw var5;
               }

               throw ExecutionErrors$.MODULE$.failToRecognizePatternAfterUpgradeError(this.pattern$1, var5);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return x1 instanceof IllegalArgumentException;
         }

         public {
            this.tryLegacyFormatter$1 = tryLegacyFormatter$1;
            this.pattern$1 = pattern$1;
         }
      };
   }

   // $FF: synthetic method
   static PartialFunction checkInvalidPattern$(final DateTimeFormatterHelper $this, final String pattern) {
      return $this.checkInvalidPattern(pattern);
   }

   default PartialFunction checkInvalidPattern(final String pattern) {
      return new Serializable(pattern) {
         private static final long serialVersionUID = 0L;
         private final String pattern$2;

         public final Object applyOrElse(final Throwable x1, final Function1 default) {
            if (x1 instanceof IllegalArgumentException var5) {
               throw ExecutionErrors$.MODULE$.failToRecognizePatternError(this.pattern$2, var5);
            } else {
               return default.apply(x1);
            }
         }

         public final boolean isDefinedAt(final Throwable x1) {
            return x1 instanceof IllegalArgumentException;
         }

         public {
            this.pattern$2 = pattern$2;
         }
      };
   }

   static void $init$(final DateTimeFormatterHelper $this) {
   }
}
