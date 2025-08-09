package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.sql.types.DayTimeIntervalType$;
import org.apache.spark.sql.types.YearMonthIntervalType$;
import org.apache.spark.unsafe.types.CalendarInterval;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eha\u0002\u001d:!\u0003\r\tA\u0012\u0005\u0006\u001b\u0002!\tA\u0014\u0005\b%\u0002\u0011\r\u0011\"\u0005T\u0011\u001d9\u0006A1A\u0005\u0012MCq\u0001\u0017\u0001C\u0002\u0013E1\u000bC\u0004Z\u0001\t\u0007I\u0011C*\t\u000fi\u0003!\u0019!C\t'\"91\f\u0001b\u0001\n\u001b\u0019\u0006\"\u0002/\u0001\t\u0003i\u0006\"\u0002/\u0001\t\u0003A\u0007\"B8\u0001\t\u0003\u0001\b\"B8\u0001\t\u0003I\b\"\u0002?\u0001\t\u0003i\bbBA\u0001\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003\u0013\u0001A\u0011AA\u0006\u0011\u001d\t9\u0003\u0001C\u0001\u0003SAq!a\u001d\u0001\t\u0003\t)\bC\u0004\u0002\u0000\u0001!\t\"!!\t\u0013\u0005\u001d\u0005A1A\u0005\u0012\u0005%\u0005\"CAF\u0001\t\u0007I\u0011CAE\u0011%\ti\t\u0001b\u0001\n#\tI\tC\u0005\u0002\u0010\u0002\u0011\r\u0011\"\u0005\u0002\n\"I\u0011\u0011\u0013\u0001C\u0002\u0013E\u0011\u0011\u0012\u0005\n\u0003'\u0003!\u0019!C\t\u0003\u0013C\u0011\"!&\u0001\u0005\u0004%\t\"!#\t\u0013\u0005]\u0005A1A\u0005\u0012\u0005%\u0005\"CAM\u0001\t\u0007I\u0011CAE\u0011%\tY\n\u0001b\u0001\n#\tI\tC\u0005\u0002\u001e\u0002\u0011\r\u0011\"\u0005\u0002\n\u001e9\u0011q\u0014\u0001\t\n\u0005\u0005faBAS\u0001!%\u0011q\u0015\u0005\b\u0003_sB\u0011AAY\u000b\u0019\t)K\b\u0001\u00024\"I\u00111\u0018\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003\u007fs\u0002\u0015!\u0003\u00024\"I\u0011\u0011\u0019\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003\u0007t\u0002\u0015!\u0003\u00024\"I\u0011Q\u0019\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003\u000ft\u0002\u0015!\u0003\u00024\"I\u0011\u0011\u001a\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003\u0017t\u0002\u0015!\u0003\u00024\"I\u0011Q\u001a\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003\u001ft\u0002\u0015!\u0003\u00024\"I\u0011\u0011\u001b\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003't\u0002\u0015!\u0003\u00024\"I\u0011Q\u001b\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003/t\u0002\u0015!\u0003\u00024\"I\u0011\u0011\u001c\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u00037t\u0002\u0015!\u0003\u00024\"I\u0011Q\u001c\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003?t\u0002\u0015!\u0003\u00024\"I\u0011\u0011\u001d\u0010C\u0002\u0013\u0005\u0011Q\u0018\u0005\t\u0003Gt\u0002\u0015!\u0003\u00024\u001e9\u0011Q]\u001d\t\u0002\u0005\u001dhA\u0002\u001d:\u0011\u0003\tY\u000fC\u0004\u00020Z\"\t!a<\u0003%M\u0003\u0018M]6J]R,'O^1m+RLGn\u001d\u0006\u0003um\nA!\u001e;jY*\u0011A(P\u0001\tG\u0006$\u0018\r\\=ti*\u0011ahP\u0001\u0004gFd'B\u0001!B\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00115)\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\t\u0006\u0019qN]4\u0004\u0001M\u0011\u0001a\u0012\t\u0003\u0011.k\u0011!\u0013\u0006\u0002\u0015\u0006)1oY1mC&\u0011A*\u0013\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005y\u0005C\u0001%Q\u0013\t\t\u0016J\u0001\u0003V]&$\u0018aB'B1~#\u0015)W\u000b\u0002)B\u0011\u0001*V\u0005\u0003-&\u0013A\u0001T8oO\u0006AQ*\u0011-`\u0011>+&+\u0001\u0006N\u0003b{V*\u0013(V)\u0016\u000b!\"T!Y?N+5i\u0014(E\u0003)i\u0015JT0T\u000b\u000e{e\nR\u0001\u0013[&tG)\u001e:bi&|gnU3d_:$7/\u0001\tekJ\fG/[8o)>l\u0015n\u0019:pgR\u0011AK\u0018\u0005\u0006?\"\u0001\r\u0001Y\u0001\tIV\u0014\u0018\r^5p]B\u0011\u0011MZ\u0007\u0002E*\u00111\rZ\u0001\u0005i&lWMC\u0001f\u0003\u0011Q\u0017M^1\n\u0005\u001d\u0014'\u0001\u0003#ve\u0006$\u0018n\u001c8\u0015\u0007QK'\u000eC\u0003`\u0013\u0001\u0007\u0001\rC\u0003l\u0013\u0001\u0007A.\u0001\u0005f]\u00124\u0015.\u001a7e!\tAU.\u0003\u0002o\u0013\n!!)\u001f;f\u00039\u0001XM]5pIR{Wj\u001c8uQN$\"!\u001d;\u0011\u0005!\u0013\u0018BA:J\u0005\rIe\u000e\u001e\u0005\u0006k*\u0001\rA^\u0001\u0007a\u0016\u0014\u0018n\u001c3\u0011\u0005\u0005<\u0018B\u0001=c\u0005\u0019\u0001VM]5pIR\u0019\u0011O_>\t\u000bU\\\u0001\u0019\u0001<\t\u000b-\\\u0001\u0019\u00017\u0002!5L7M]8t)>$UO]1uS>tGC\u00011\u007f\u0011\u0015yH\u00021\u0001U\u0003\u0019i\u0017n\u0019:pg\u0006qQn\u001c8uQN$v\u000eU3sS>$Gc\u0001<\u0002\u0006!1\u0011qA\u0007A\u0002E\fa!\\8oi\"\u001c\u0018\u0001E:ue&tw\rV8J]R,'O^1m)\u0011\ti!!\b\u0011\t\u0005=\u0011\u0011D\u0007\u0003\u0003#QA!a\u0005\u0002\u0016\u0005)A/\u001f9fg*\u0019\u0011qC \u0002\rUt7/\u00194f\u0013\u0011\tY\"!\u0005\u0003!\r\u000bG.\u001a8eCJLe\u000e^3sm\u0006d\u0007bBA\u0010\u001d\u0001\u0007\u0011\u0011E\u0001\u0006S:\u0004X\u000f\u001e\t\u0005\u0003\u001f\t\u0019#\u0003\u0003\u0002&\u0005E!AC+U\rb\u001aFO]5oO\u0006IBo\\-fCJluN\u001c;i\u0013:$XM\u001d<bYN#(/\u001b8h))\tY#!\u0011\u0002D\u00055\u0014\u0011\u000f\t\u0005\u0003[\tYD\u0004\u0003\u00020\u0005]\u0002cAA\u0019\u00136\u0011\u00111\u0007\u0006\u0004\u0003k)\u0015A\u0002\u001fs_>$h(C\u0002\u0002:%\u000ba\u0001\u0015:fI\u00164\u0017\u0002BA\u001f\u0003\u007f\u0011aa\u0015;sS:<'bAA\u001d\u0013\"1\u0011qA\bA\u0002EDq!!\u0012\u0010\u0001\u0004\t9%A\u0003tifdW\r\u0005\u0003\u0002J\u0005\u001dd\u0002BA&\u0003GrA!!\u0014\u0002b9!\u0011qJA0\u001d\u0011\t\t&!\u0018\u000f\t\u0005M\u00131\f\b\u0005\u0003+\nIF\u0004\u0003\u00022\u0005]\u0013\"\u0001#\n\u0005\t\u001b\u0015B\u0001!B\u0013\tqt(\u0003\u0002={%\u0011!hO\u0005\u0004\u0003KJ\u0014\u0001F%oi\u0016\u0014h/\u00197TiJLgnZ*us2,7/\u0003\u0003\u0002j\u0005-$!D%oi\u0016\u0014h/\u00197TifdWMC\u0002\u0002feBa!a\u001c\u0010\u0001\u0004a\u0017AC:uCJ$h)[3mI\")1n\u0004a\u0001Y\u00069Bo\u001c#bsRKW.Z%oi\u0016\u0014h/\u00197TiJLgn\u001a\u000b\u000b\u0003W\t9(!\u001f\u0002|\u0005u\u0004\"B@\u0011\u0001\u0004!\u0006bBA#!\u0001\u0007\u0011q\t\u0005\u0007\u0003_\u0002\u0002\u0019\u00017\t\u000b-\u0004\u0002\u0019\u00017\u0002\u0015Ut\u0017\u000e\u001e+p+R4\u0007\b\u0006\u0003\u0002\"\u0005\r\u0005bBAC#\u0001\u0007\u00111F\u0001\u0005k:LG/A\u0006j]R,'O^1m'R\u0014XCAA\u0011\u0003\u001dIX-\u0019:TiJ\f\u0001\"\\8oi\"\u001cFO]\u0001\bo\u0016,7n\u0015;s\u0003\u0019!\u0017-_*ue\u00069\u0001n\\;s'R\u0014\u0018!C7j]V$Xm\u0015;s\u0003%\u0019XmY8oIN#(/A\u0005nS2d\u0017n]*ue\u0006IQ.[2s_N\u001cFO]\u0001\t]\u0006twn]*ue\u0006Q\u0001+\u0019:tKN#\u0018\r^3\u0011\u0007\u0005\rf$D\u0001\u0001\u0005)\u0001\u0016M]:f'R\fG/Z\n\u0004=\u0005%\u0006c\u0001%\u0002,&\u0019\u0011QV%\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\u0005\u0005\u0006\u0003BA[\u0003ok\u0011AH\u0005\u0005\u0003s\u000bYKA\u0003WC2,X-\u0001\u0004Q%\u00163\u0015\nW\u000b\u0003\u0003g\u000bq\u0001\u0015*F\r&C\u0006%\u0001\tU%&kuLQ#G\u001fJ+ulU%H\u001d\u0006\tBKU%N?\n+ei\u0014*F?NKuI\u0014\u0011\u0002\tMKuIT\u0001\u0006'&;e\nI\u0001\u0012)JKUj\u0018\"F\r>\u0013Vi\u0018,B\u0019V+\u0015A\u0005+S\u00136{&)\u0012$P%\u0016{f+\u0011'V\u000b\u0002\nQAV!M+\u0016\u000baAV!M+\u0016\u0003\u0013!\u0006,B\u0019V+uL\u0012*B\u0007RKuJT!M?B\u000b%\u000bV\u0001\u0017-\u0006cU+R0G%\u0006\u001bE+S(O\u00032{\u0006+\u0011*UA\u0005\u0001BKU%N?\n+ei\u0014*F?Vs\u0015\nV\u0001\u0012)JKUj\u0018\"F\r>\u0013ViX+O\u0013R\u0003\u0013AC+O\u0013R{&)R$J\u001d\u0006YQKT%U?\n+u)\u0013(!\u0003-)f*\u0013+`'V3e)\u0013-\u0002\u0019Us\u0015\nV0T+\u001a3\u0015\n\u0017\u0011\u0002\u0011Us\u0015\nV0F\u001d\u0012\u000b\u0011\"\u0016(J)~+e\n\u0012\u0011\u0002%M\u0003\u0018M]6J]R,'O^1m+RLGn\u001d\t\u0004\u0003S4T\"A\u001d\u0014\tY:\u0015Q\u001e\t\u0004\u0003S\u0004ACAAt\u0001"
)
public interface SparkIntervalUtils {
   ParseState$ org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState();

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_DAY_$eq(final long x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_HOUR_$eq(final long x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_MINUTE_$eq(final long x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_SECOND_$eq(final long x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MIN_SECOND_$eq(final long x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds_$eq(final long x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$intervalStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$yearStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$monthStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$weekStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$dayStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$hourStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$minuteStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$secondStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$millisStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$microsStr_$eq(final UTF8String x$1);

   void org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$nanosStr_$eq(final UTF8String x$1);

   long MAX_DAY();

   long MAX_HOUR();

   long MAX_MINUTE();

   long MAX_SECOND();

   long MIN_SECOND();

   long org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds();

   // $FF: synthetic method
   static long durationToMicros$(final SparkIntervalUtils $this, final Duration duration) {
      return $this.durationToMicros(duration);
   }

   default long durationToMicros(final Duration duration) {
      return this.durationToMicros(duration, DayTimeIntervalType$.MODULE$.SECOND());
   }

   // $FF: synthetic method
   static long durationToMicros$(final SparkIntervalUtils $this, final Duration duration, final byte endField) {
      return $this.durationToMicros(duration, endField);
   }

   default long durationToMicros(final Duration duration, final byte endField) {
      long seconds = duration.getSeconds();
      long var10000;
      if (seconds == this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds()) {
         long microsInSeconds = (this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds() + 1L) * 1000000L;
         int nanoAdjustment = duration.getNano();
         .MODULE$.assert(0 <= nanoAdjustment && (long)nanoAdjustment < 1000000000L, () -> "Duration.getNano() must return the adjustment to the seconds field in the range from 0 to 999999999 nanoseconds, inclusive.");
         var10000 = Math.addExact(microsInSeconds, ((long)nanoAdjustment - 1000000000L) / 1000L);
      } else {
         long microsInSeconds = Math.multiplyExact(seconds, 1000000L);
         var10000 = Math.addExact(microsInSeconds, (long)duration.getNano() / 1000L);
      }

      long micros = var10000;
      if (DayTimeIntervalType$.MODULE$.DAY() == endField) {
         return micros - micros % 86400000000L;
      } else if (DayTimeIntervalType$.MODULE$.HOUR() == endField) {
         return micros - micros % 3600000000L;
      } else if (DayTimeIntervalType$.MODULE$.MINUTE() == endField) {
         return micros - micros % 60000000L;
      } else if (DayTimeIntervalType$.MODULE$.SECOND() == endField) {
         return micros;
      } else {
         throw new MatchError(BoxesRunTime.boxToByte(endField));
      }
   }

   // $FF: synthetic method
   static int periodToMonths$(final SparkIntervalUtils $this, final Period period) {
      return $this.periodToMonths(period);
   }

   default int periodToMonths(final Period period) {
      return this.periodToMonths(period, YearMonthIntervalType$.MODULE$.MONTH());
   }

   // $FF: synthetic method
   static int periodToMonths$(final SparkIntervalUtils $this, final Period period, final byte endField) {
      return $this.periodToMonths(period, endField);
   }

   default int periodToMonths(final Period period, final byte endField) {
      int monthsInYears = Math.multiplyExact(period.getYears(), 12);
      int months = Math.addExact(monthsInYears, period.getMonths());
      return endField == YearMonthIntervalType$.MODULE$.YEAR() ? months - months % 12 : months;
   }

   // $FF: synthetic method
   static Duration microsToDuration$(final SparkIntervalUtils $this, final long micros) {
      return $this.microsToDuration(micros);
   }

   default Duration microsToDuration(final long micros) {
      return Duration.of(micros, ChronoUnit.MICROS);
   }

   // $FF: synthetic method
   static Period monthsToPeriod$(final SparkIntervalUtils $this, final int months) {
      return $this.monthsToPeriod(months);
   }

   default Period monthsToPeriod(final int months) {
      return Period.ofMonths(months).normalized();
   }

   // $FF: synthetic method
   static CalendarInterval stringToInterval$(final SparkIntervalUtils $this, final UTF8String input) {
      return $this.stringToInterval(input);
   }

   default CalendarInterval stringToInterval(final UTF8String input) {
      if (input == null) {
         throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INPUT_IS_NULL", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), "null")}))));
      } else {
         UTF8String s = input.trimAll().toLowerCase();
         byte[] bytes = s.getBytes();
         if (scala.collection.ArrayOps..MODULE$.isEmpty$extension(.MODULE$.byteArrayOps(bytes))) {
            throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INPUT_IS_EMPTY", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString())}))));
         } else {
            ObjectRef state = ObjectRef.create(this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().PREFIX());
            IntRef i = IntRef.create(0);
            long currentValue = 0L;
            boolean isNegative = false;
            int months = 0;
            int days = 0;
            long microseconds = 0L;
            int fractionScale = 0;
            int initialFractionScale = 100000000;
            int fraction = 0;
            boolean pointPrefixed = false;

            while(true) {
               byte b;
               Enumeration.Value var24;
               while(true) {
                  while(true) {
                     while(true) {
                        while(true) {
                           while(true) {
                              if (i.elem >= bytes.length) {
                                 Enumeration.Value var54;
                                 boolean var92;
                                 label286: {
                                    label414: {
                                       var54 = (Enumeration.Value)state.elem;
                                       Enumeration.Value var89 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_SUFFIX();
                                       if (var89 == null) {
                                          if (var54 == null) {
                                             break label414;
                                          }
                                       } else if (var89.equals(var54)) {
                                          break label414;
                                       }

                                       label415: {
                                          var89 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_END();
                                          if (var89 == null) {
                                             if (var54 == null) {
                                                break label415;
                                             }
                                          } else if (var89.equals(var54)) {
                                             break label415;
                                          }

                                          label271: {
                                             var89 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_SIGN();
                                             if (var89 == null) {
                                                if (var54 == null) {
                                                   break label271;
                                                }
                                             } else if (var89.equals(var54)) {
                                                break label271;
                                             }

                                             var92 = false;
                                             break label286;
                                          }

                                          var92 = true;
                                          break label286;
                                       }

                                       var92 = true;
                                       break label286;
                                    }

                                    var92 = true;
                                 }

                                 if (var92) {
                                    CalendarInterval result = new CalendarInterval(months, days, microseconds);
                                    return result;
                                 }

                                 Enumeration.Value var93 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_VALUE();
                                 if (var93 == null) {
                                    if (var54 == null) {
                                       throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.MISSING_NUMBER", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("word"), currentWord$1(s, i))}))));
                                    }
                                 } else if (var93.equals(var54)) {
                                    throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.MISSING_NUMBER", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("word"), currentWord$1(s, i))}))));
                                 }

                                 label256: {
                                    label417: {
                                       var93 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().VALUE();
                                       if (var93 == null) {
                                          if (var54 == null) {
                                             break label417;
                                          }
                                       } else if (var93.equals(var54)) {
                                          break label417;
                                       }

                                       label248: {
                                          var93 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().VALUE_FRACTIONAL_PART();
                                          if (var93 == null) {
                                             if (var54 == null) {
                                                break label248;
                                             }
                                          } else if (var93.equals(var54)) {
                                             break label248;
                                          }

                                          var96 = false;
                                          break label256;
                                       }

                                       var96 = true;
                                       break label256;
                                    }

                                    var96 = true;
                                 }

                                 if (var96) {
                                    throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.MISSING_UNIT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("word"), currentWord$1(s, i))}))));
                                 }

                                 throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.UNKNOWN_PARSING_ERROR", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("word"), currentWord$1(s, i))}))));
                              }

                              b = bytes[i.elem];
                              var24 = (Enumeration.Value)state.elem;
                              Enumeration.Value var10000 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().PREFIX();
                              if (var10000 == null) {
                                 if (var24 != null) {
                                    break;
                                 }
                              } else if (!var10000.equals(var24)) {
                                 break;
                              }

                              if (s.startsWith(this.intervalStr())) {
                                 if (s.numBytes() == this.intervalStr().numBytes()) {
                                    throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INPUT_IS_EMPTY", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString())}))));
                                 }

                                 if (!Character.isWhitespace(bytes[i.elem + this.intervalStr().numBytes()])) {
                                    throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_PREFIX", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("prefix"), currentWord$1(s, i))}))));
                                 }

                                 i.elem += this.intervalStr().numBytes() + 1;
                              }

                              state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_SIGN();
                              BoxedUnit var63 = BoxedUnit.UNIT;
                           }

                           Enumeration.Value var64 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_SIGN();
                           if (var64 == null) {
                              if (var24 != null) {
                                 break;
                              }
                           } else if (!var64.equals(var24)) {
                              break;
                           }

                           trimToNextState$1(b, this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().SIGN(), i, state);
                           BoxedUnit var65 = BoxedUnit.UNIT;
                        }

                        Enumeration.Value var66 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().SIGN();
                        if (var66 == null) {
                           if (var24 != null) {
                              break;
                           }
                        } else if (!var66.equals(var24)) {
                           break;
                        }

                        currentValue = 0L;
                        fraction = 0;
                        state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_VALUE();
                        fractionScale = -1;
                        pointPrefixed = false;
                        if (45 == b) {
                           isNegative = true;
                           ++i.elem;
                           BoxedUnit var67 = BoxedUnit.UNIT;
                        } else if (43 == b) {
                           isNegative = false;
                           ++i.elem;
                           BoxedUnit var68 = BoxedUnit.UNIT;
                        } else if (48 <= b && b <= 57) {
                           isNegative = false;
                           BoxedUnit var70 = BoxedUnit.UNIT;
                        } else {
                           if (46 != b) {
                              throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.UNRECOGNIZED_NUMBER", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("number"), currentWord$1(s, i))}))));
                           }

                           isNegative = false;
                           fractionScale = initialFractionScale;
                           pointPrefixed = true;
                           ++i.elem;
                           state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().VALUE_FRACTIONAL_PART();
                           BoxedUnit var69 = BoxedUnit.UNIT;
                        }

                        BoxedUnit var71 = BoxedUnit.UNIT;
                     }

                     Enumeration.Value var72 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_VALUE();
                     if (var72 == null) {
                        if (var24 != null) {
                           break;
                        }
                     } else if (!var72.equals(var24)) {
                        break;
                     }

                     trimToNextState$1(b, this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().VALUE(), i, state);
                     BoxedUnit var73 = BoxedUnit.UNIT;
                  }

                  Enumeration.Value var74 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().VALUE();
                  if (var74 == null) {
                     if (var24 != null) {
                        break;
                     }
                  } else if (!var74.equals(var24)) {
                     break;
                  }

                  if (48 <= b && b <= 57) {
                     try {
                        currentValue = Math.addExact(Math.multiplyExact(10L, currentValue), (long)(b - 48));
                        BoxedUnit var77 = BoxedUnit.UNIT;
                     } catch (ArithmeticException var61) {
                        throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.ARITHMETIC_EXCEPTION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString())}))));
                     }
                  } else if (Character.isWhitespace(b)) {
                     state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_UNIT();
                     BoxedUnit var75 = BoxedUnit.UNIT;
                  } else {
                     if (46 != b) {
                        throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_VALUE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("value"), currentWord$1(s, i))}))));
                     }

                     fractionScale = initialFractionScale;
                     state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().VALUE_FRACTIONAL_PART();
                     BoxedUnit var76 = BoxedUnit.UNIT;
                  }

                  ++i.elem;
                  BoxedUnit var78 = BoxedUnit.UNIT;
               }

               label420: {
                  Enumeration.Value var79 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().VALUE_FRACTIONAL_PART();
                  if (var79 == null) {
                     if (var24 == null) {
                        break label420;
                     }
                  } else if (var79.equals(var24)) {
                     break label420;
                  }

                  label421: {
                     var79 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_UNIT();
                     if (var79 == null) {
                        if (var24 == null) {
                           break label421;
                        }
                     } else if (var79.equals(var24)) {
                        break label421;
                     }

                     label422: {
                        var79 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_BEGIN();
                        if (var79 == null) {
                           if (var24 == null) {
                              break label422;
                           }
                        } else if (var79.equals(var24)) {
                           break label422;
                        }

                        label443: {
                           var79 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_SUFFIX();
                           if (var79 == null) {
                              if (var24 == null) {
                                 break label443;
                              }
                           } else if (var79.equals(var24)) {
                              break label443;
                           }

                           var79 = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_END();
                           if (var79 == null) {
                              if (var24 != null) {
                                 throw new MatchError(var24);
                              }
                           } else if (!var79.equals(var24)) {
                              throw new MatchError(var24);
                           }

                           if (!Character.isWhitespace(b)) {
                              throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_UNIT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("unit"), currentWord$1(s, i))}))));
                           }

                           ++i.elem;
                           state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_SIGN();
                           BoxedUnit var84 = BoxedUnit.UNIT;
                           continue;
                        }

                        switch (b) {
                           case 115:
                              state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_END();
                              break;
                           default:
                              if (!Character.isWhitespace(b)) {
                                 throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_UNIT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("unit"), currentWord$1(s, i))}))));
                              }

                              state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_SIGN();
                        }

                        ++i.elem;
                        BoxedUnit var85 = BoxedUnit.UNIT;
                        continue;
                     }

                     if (b != 115 && fractionScale >= 0) {
                        throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_FRACTION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("unit"), currentWord$1(s, i))}))));
                     }

                     if (isNegative) {
                        currentValue = -currentValue;
                        fraction = -fraction;
                     }

                     try {
                        label362: {
                           switch (b) {
                              case 100:
                                 if (s.matchAt(this.dayStr(), i.elem)) {
                                    days = Math.addExact(days, Math.toIntExact(currentValue));
                                    i.elem += this.dayStr().numBytes();
                                    break label362;
                                 }
                                 break;
                              case 104:
                                 if (s.matchAt(this.hourStr(), i.elem)) {
                                    long hoursUs = Math.multiplyExact(currentValue, 3600000000L);
                                    microseconds = Math.addExact(microseconds, hoursUs);
                                    i.elem += this.hourStr().numBytes();
                                    break label362;
                                 }
                                 break;
                              case 109:
                                 if (s.matchAt(this.monthStr(), i.elem)) {
                                    months = Math.addExact(months, Math.toIntExact(currentValue));
                                    i.elem += this.monthStr().numBytes();
                                 } else if (s.matchAt(this.minuteStr(), i.elem)) {
                                    long minutesUs = Math.multiplyExact(currentValue, 60000000L);
                                    microseconds = Math.addExact(microseconds, minutesUs);
                                    i.elem += this.minuteStr().numBytes();
                                 } else if (s.matchAt(this.millisStr(), i.elem)) {
                                    long millisUs = SparkDateTimeUtils$.MODULE$.millisToMicros(currentValue);
                                    microseconds = Math.addExact(microseconds, millisUs);
                                    i.elem += this.millisStr().numBytes();
                                 } else {
                                    if (!s.matchAt(this.microsStr(), i.elem)) {
                                       throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_UNIT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("unit"), currentWord$1(s, i))}))));
                                    }

                                    microseconds = Math.addExact(microseconds, currentValue);
                                    i.elem += this.microsStr().numBytes();
                                 }
                                 break label362;
                              case 115:
                                 if (s.matchAt(this.secondStr(), i.elem)) {
                                    long secondsUs = Math.multiplyExact(currentValue, 1000000L);
                                    microseconds = Math.addExact(Math.addExact(microseconds, secondsUs), (long)fraction);
                                    i.elem += this.secondStr().numBytes();
                                    break label362;
                                 }
                                 break;
                              case 119:
                                 if (s.matchAt(this.weekStr(), i.elem)) {
                                    long daysInWeeks = Math.multiplyExact(7L, currentValue);
                                    days = Math.toIntExact(Math.addExact((long)days, daysInWeeks));
                                    i.elem += this.weekStr().numBytes();
                                    break label362;
                                 }
                                 break;
                              case 121:
                                 if (s.matchAt(this.yearStr(), i.elem)) {
                                    long monthsInYears = Math.multiplyExact(12L, currentValue);
                                    months = Math.toIntExact(Math.addExact((long)months, monthsInYears));
                                    i.elem += this.yearStr().numBytes();
                                    break label362;
                                 }
                           }

                           throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_UNIT", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("unit"), currentWord$1(s, i))}))));
                        }
                     } catch (ArithmeticException var62) {
                        throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.ARITHMETIC_EXCEPTION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString())}))));
                     }

                     state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_SUFFIX();
                     BoxedUnit var86 = BoxedUnit.UNIT;
                     continue;
                  }

                  trimToNextState$1(b, this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().UNIT_BEGIN(), i, state);
                  BoxedUnit var87 = BoxedUnit.UNIT;
                  continue;
               }

               if (48 <= b && b <= 57 && fractionScale > 0) {
                  fraction += (b - 48) * fractionScale;
                  fractionScale /= 10;
               } else {
                  if (!Character.isWhitespace(b) || pointPrefixed && fractionScale >= initialFractionScale) {
                     if (48 <= b && b <= 57) {
                        throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_PRECISION", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("value"), currentWord$1(s, i))}))));
                     }

                     throw new SparkIllegalArgumentException("INVALID_INTERVAL_FORMAT.INVALID_VALUE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("input"), input.toString()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("value"), currentWord$1(s, i))}))));
                  }

                  fraction /= 1000;
                  state.elem = this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$ParseState().TRIM_BEFORE_UNIT();
               }

               ++i.elem;
               BoxedUnit var88 = BoxedUnit.UNIT;
            }
         }
      }
   }

   // $FF: synthetic method
   static String toYearMonthIntervalString$(final SparkIntervalUtils $this, final int months, final Enumeration.Value style, final byte startField, final byte endField) {
      return $this.toYearMonthIntervalString(months, style, startField, endField);
   }

   default String toYearMonthIntervalString(final int months, final Enumeration.Value style, final byte startField, final byte endField) {
      String sign = "";
      long absMonths = (long)months;
      if (months < 0) {
         sign = "-";
         absMonths = -absMonths;
      }

      String year;
      String yearAndMonth;
      label55: {
         year = sign + absMonths / 12L;
         yearAndMonth = year + "-" + absMonths % 12L;
         Enumeration.Value var10000 = IntervalStringStyles$.MODULE$.ANSI_STYLE();
         if (var10000 == null) {
            if (style == null) {
               break label55;
            }
         } else if (var10000.equals(style)) {
            break label55;
         }

         var10000 = IntervalStringStyles$.MODULE$.HIVE_STYLE();
         if (var10000 == null) {
            if (style == null) {
               return String.valueOf(yearAndMonth);
            }
         } else if (var10000.equals(style)) {
            return String.valueOf(yearAndMonth);
         }

         throw new MatchError(style);
      }

      StringBuilder formatBuilder = new StringBuilder("INTERVAL '");
      if (startField == endField) {
         if (YearMonthIntervalType$.MODULE$.YEAR() == startField) {
            formatBuilder.append(year + "' YEAR");
         } else {
            if (YearMonthIntervalType$.MODULE$.MONTH() != startField) {
               throw new MatchError(BoxesRunTime.boxToByte(startField));
            }

            formatBuilder.append(months + "' MONTH");
         }
      } else {
         formatBuilder.append(yearAndMonth + "' YEAR TO MONTH");
      }

      return formatBuilder.toString();
   }

   // $FF: synthetic method
   static String toDayTimeIntervalString$(final SparkIntervalUtils $this, final long micros, final Enumeration.Value style, final byte startField, final byte endField) {
      return $this.toDayTimeIntervalString(micros, style, startField, endField);
   }

   default String toDayTimeIntervalString(final long micros, final Enumeration.Value style, final byte startField, final byte endField) {
      String sign = "";
      long rest = micros;
      String from = DayTimeIntervalType$.MODULE$.fieldToString(startField).toUpperCase();
      String to = DayTimeIntervalType$.MODULE$.fieldToString(endField).toUpperCase();
      String prefix = "INTERVAL '";
      String var10000 = startField == endField ? from : from + " TO " + to;
      String postfix = "' " + var10000;
      if (micros < 0L) {
         if (micros == Long.MIN_VALUE) {
            label179: {
               String baseStr;
               label180: {
                  baseStr = "-106751991 04:00:54.775808000";
                  Enumeration.Value var61 = IntervalStringStyles$.MODULE$.ANSI_STYLE();
                  if (var61 == null) {
                     if (style == null) {
                        break label180;
                     }
                  } else if (var61.equals(style)) {
                     break label180;
                  }

                  var61 = IntervalStringStyles$.MODULE$.HIVE_STYLE();
                  if (var61 == null) {
                     if (style != null) {
                        throw new MatchError(style);
                     }
                  } else if (!var61.equals(style)) {
                     throw new MatchError(style);
                  }

                  var10000 = baseStr;
                  break label179;
               }

               if (DayTimeIntervalType$.MODULE$.DAY() == startField) {
                  var10000 = "-" + this.MAX_DAY();
               } else if (DayTimeIntervalType$.MODULE$.HOUR() == startField) {
                  var10000 = "-" + this.MAX_HOUR();
               } else if (DayTimeIntervalType$.MODULE$.MINUTE() == startField) {
                  var10000 = "-" + this.MAX_MINUTE();
               } else {
                  if (DayTimeIntervalType$.MODULE$.SECOND() != startField) {
                     throw new MatchError(BoxesRunTime.boxToByte(startField));
                  }

                  var10000 = "-" + this.MAX_SECOND() + ".775808";
               }

               String firstStr = var10000;
               if (startField == endField) {
                  var10000 = "";
               } else {
                  byte var66;
                  if (DayTimeIntervalType$.MODULE$.DAY() == startField) {
                     var66 = 10;
                  } else if (DayTimeIntervalType$.MODULE$.HOUR() == startField) {
                     var66 = 13;
                  } else {
                     if (DayTimeIntervalType$.MODULE$.MINUTE() != startField) {
                        throw new MatchError(BoxesRunTime.boxToByte(startField));
                     }

                     var66 = 16;
                  }

                  int substrStart = var66;
                  if (DayTimeIntervalType$.MODULE$.HOUR() == endField) {
                     var66 = 13;
                  } else if (DayTimeIntervalType$.MODULE$.MINUTE() == endField) {
                     var66 = 16;
                  } else {
                     if (DayTimeIntervalType$.MODULE$.SECOND() != endField) {
                        throw new MatchError(BoxesRunTime.boxToByte(endField));
                     }

                     var66 = 26;
                  }

                  int substrEnd = var66;
                  var10000 = baseStr.substring(substrStart, substrEnd);
               }

               String followingStr = var10000;
               var10000 = prefix + firstStr + followingStr + postfix;
            }

            String minIntervalString = var10000;
            return minIntervalString;
         }

         sign = "-";
         rest = -micros;
      }

      label191: {
         label183: {
            Enumeration.Value var54 = IntervalStringStyles$.MODULE$.ANSI_STYLE();
            if (var54 == null) {
               if (style == null) {
                  break label183;
               }
            } else if (var54.equals(style)) {
               break label183;
            }

            var54 = IntervalStringStyles$.MODULE$.HIVE_STYLE();
            if (var54 == null) {
               if (style != null) {
                  throw new MatchError(style);
               }
            } else if (!var54.equals(style)) {
               throw new MatchError(style);
            }

            long secondsWithFraction = rest % 60000000L;
            rest /= 60000000L;
            long minutes = rest % 60L;
            rest /= 60L;
            long hours = rest % 24L;
            long days = rest / 24L;
            long seconds = secondsWithFraction / 1000000L;
            long nanos = secondsWithFraction % 1000000L * 1000L;
            var10000 = scala.collection.StringOps..MODULE$.format$extension("%s%s %02d:%02d:%02d.%09d", scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{sign, BoxesRunTime.boxToLong(days), BoxesRunTime.boxToLong(hours), BoxesRunTime.boxToLong(minutes), BoxesRunTime.boxToLong(seconds), BoxesRunTime.boxToLong(nanos)}));
            break label191;
         }

         StringBuilder formatBuilder = new StringBuilder(sign);
         ArrayBuffer formatArgs = new ArrayBuffer();
         if (DayTimeIntervalType$.MODULE$.DAY() == startField) {
            formatBuilder.append(rest / 86400000000L);
            rest %= 86400000000L;
            BoxedUnit var57 = BoxedUnit.UNIT;
         } else if (DayTimeIntervalType$.MODULE$.HOUR() == startField) {
            formatBuilder.append("%02d");
            formatArgs.append(BoxesRunTime.boxToLong(rest / 3600000000L));
            rest %= 3600000000L;
            BoxedUnit var58 = BoxedUnit.UNIT;
         } else if (DayTimeIntervalType$.MODULE$.MINUTE() == startField) {
            formatBuilder.append("%02d");
            formatArgs.append(BoxesRunTime.boxToLong(rest / 60000000L));
            rest %= 60000000L;
            BoxedUnit var59 = BoxedUnit.UNIT;
         } else {
            if (DayTimeIntervalType$.MODULE$.SECOND() != startField) {
               throw new MatchError(BoxesRunTime.boxToByte(startField));
            }

            String leadZero = rest < 10000000L ? "0" : "";
            formatBuilder.append(leadZero + BigDecimal.valueOf(rest, 6).stripTrailingZeros().toPlainString());
         }

         if (startField < DayTimeIntervalType$.MODULE$.HOUR() && DayTimeIntervalType$.MODULE$.HOUR() <= endField) {
            formatBuilder.append(" %02d");
            formatArgs.append(BoxesRunTime.boxToLong(rest / 3600000000L));
            rest %= 3600000000L;
         }

         if (startField < DayTimeIntervalType$.MODULE$.MINUTE() && DayTimeIntervalType$.MODULE$.MINUTE() <= endField) {
            formatBuilder.append(":%02d");
            formatArgs.append(BoxesRunTime.boxToLong(rest / 60000000L));
            rest %= 60000000L;
         }

         if (startField < DayTimeIntervalType$.MODULE$.SECOND() && DayTimeIntervalType$.MODULE$.SECOND() <= endField) {
            String leadZero = rest < 10000000L ? "0" : "";
            formatBuilder.append(":" + leadZero + BigDecimal.valueOf(rest, 6).stripTrailingZeros().toPlainString());
         } else {
            BoxedUnit var60 = BoxedUnit.UNIT;
         }

         var10000 = prefix + scala.collection.StringOps..MODULE$.format$extension(.MODULE$.augmentString(formatBuilder.toString()), formatArgs.toSeq()) + postfix;
      }

      String intervalString = var10000;
      return intervalString;
   }

   // $FF: synthetic method
   static UTF8String unitToUtf8$(final SparkIntervalUtils $this, final String unit) {
      return $this.unitToUtf8(unit);
   }

   default UTF8String unitToUtf8(final String unit) {
      return UTF8String.fromString(unit);
   }

   UTF8String intervalStr();

   UTF8String yearStr();

   UTF8String monthStr();

   UTF8String weekStr();

   UTF8String dayStr();

   UTF8String hourStr();

   UTF8String minuteStr();

   UTF8String secondStr();

   UTF8String millisStr();

   UTF8String microsStr();

   UTF8String nanosStr();

   private static void trimToNextState$1(final byte b, final Enumeration.Value next, final IntRef i$1, final ObjectRef state$1) {
      if (Character.isWhitespace(b)) {
         ++i$1.elem;
      } else {
         state$1.elem = next;
      }
   }

   private static String currentWord$1(final UTF8String s$1, final IntRef i$1) {
      String sep = "\\s+";
      String[] strings = s$1.toString().split(sep);
      int lenRight = s$1.substring(i$1.elem, s$1.numBytes()).toString().split(sep).length;
      return strings[strings.length - lenRight];
   }

   static void $init$(final SparkIntervalUtils $this) {
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_DAY_$eq(106751991L);
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_HOUR_$eq(2562047788L);
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_MINUTE_$eq(153722867280L);
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MAX_SECOND_$eq(9223372036854L);
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$MIN_SECOND_$eq(-9223372036854L);
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$org$apache$spark$sql$catalyst$util$SparkIntervalUtils$$minDurationSeconds_$eq(Math.floorDiv(Long.MIN_VALUE, 1000000L));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$intervalStr_$eq($this.unitToUtf8("interval"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$yearStr_$eq($this.unitToUtf8("year"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$monthStr_$eq($this.unitToUtf8("month"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$weekStr_$eq($this.unitToUtf8("week"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$dayStr_$eq($this.unitToUtf8("day"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$hourStr_$eq($this.unitToUtf8("hour"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$minuteStr_$eq($this.unitToUtf8("minute"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$secondStr_$eq($this.unitToUtf8("second"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$millisStr_$eq($this.unitToUtf8("millisecond"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$microsStr_$eq($this.unitToUtf8("microsecond"));
      $this.org$apache$spark$sql$catalyst$util$SparkIntervalUtils$_setter_$nanosStr_$eq($this.unitToUtf8("nanosecond"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }

   private class ParseState$ extends Enumeration {
      private final Enumeration.Value PREFIX = this.Value();
      private final Enumeration.Value TRIM_BEFORE_SIGN = this.Value();
      private final Enumeration.Value SIGN = this.Value();
      private final Enumeration.Value TRIM_BEFORE_VALUE = this.Value();
      private final Enumeration.Value VALUE = this.Value();
      private final Enumeration.Value VALUE_FRACTIONAL_PART = this.Value();
      private final Enumeration.Value TRIM_BEFORE_UNIT = this.Value();
      private final Enumeration.Value UNIT_BEGIN = this.Value();
      private final Enumeration.Value UNIT_SUFFIX = this.Value();
      private final Enumeration.Value UNIT_END = this.Value();

      public Enumeration.Value PREFIX() {
         return this.PREFIX;
      }

      public Enumeration.Value TRIM_BEFORE_SIGN() {
         return this.TRIM_BEFORE_SIGN;
      }

      public Enumeration.Value SIGN() {
         return this.SIGN;
      }

      public Enumeration.Value TRIM_BEFORE_VALUE() {
         return this.TRIM_BEFORE_VALUE;
      }

      public Enumeration.Value VALUE() {
         return this.VALUE;
      }

      public Enumeration.Value VALUE_FRACTIONAL_PART() {
         return this.VALUE_FRACTIONAL_PART;
      }

      public Enumeration.Value TRIM_BEFORE_UNIT() {
         return this.TRIM_BEFORE_UNIT;
      }

      public Enumeration.Value UNIT_BEGIN() {
         return this.UNIT_BEGIN;
      }

      public Enumeration.Value UNIT_SUFFIX() {
         return this.UNIT_SUFFIX;
      }

      public Enumeration.Value UNIT_END() {
         return this.UNIT_END;
      }

      public ParseState$() {
      }
   }
}
