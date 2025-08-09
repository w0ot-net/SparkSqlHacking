package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.time.ZoneId;
import java.util.Locale;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.TimestampNTZType$;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Enumeration;
import scala.Option;
import scala.PartialFunction;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-b\u0001B\b\u0011\u0001uA\u0001B\t\u0001\u0003\u0002\u0003\u0006Ia\t\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!A\u0011\u0007\u0001B\u0001B\u0003%!\u0007\u0003\u0005I\u0001\t\u0005\t\u0015!\u0003J\u0011\u0015y\u0005\u0001\"\u0001Q\u0011\u00151\u0006\u0001\"\u0011X\u0011\u0015)\u0007\u0001\"\u0011g\u0011\u0015Y\u0007\u0001\"\u0011m\u0011\u0015\u0001\b\u0001\"\u0011r\u000f\u001d!\b#!A\t\u0002U4qa\u0004\t\u0002\u0002#\u0005a\u000f\u0003\u0004P\u0017\u0011\u0005\u0011\u0011\u0001\u0005\n\u0003\u0007Y\u0011\u0013!C\u0001\u0003\u000bA\u0011\"a\u0007\f\u0003\u0003%I!!\b\u00033\u0011+g-Y;miRKW.Z:uC6\u0004hi\u001c:nCR$XM\u001d\u0006\u0003#I\tA!\u001e;jY*\u00111\u0003F\u0001\tG\u0006$\u0018\r\\=ti*\u0011QCF\u0001\u0004gFd'BA\f\u0019\u0003\u0015\u0019\b/\u0019:l\u0015\tI\"$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00027\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\b\t\u0003?\u0001j\u0011\u0001E\u0005\u0003CA\u0011\u0011$S:pqY\u0002\u0014\u0007V5nKN$\u0018-\u001c9G_Jl\u0017\r\u001e;fe\u00061!p\u001c8f\u0013\u0012\u0004\"\u0001J\u0015\u000e\u0003\u0015R!AJ\u0014\u0002\tQLW.\u001a\u0006\u0002Q\u0005!!.\u0019<b\u0013\tQSE\u0001\u0004[_:,\u0017\nZ\u0001\u0007Y>\u001c\u0017\r\\3\u0011\u00055zS\"\u0001\u0018\u000b\u0005E9\u0013B\u0001\u0019/\u0005\u0019aunY1mK\u0006aA.Z4bGf4uN]7biB\u00111'\u0012\b\u0003i\rs!!\u000e\"\u000f\u0005Y\neBA\u001cA\u001d\tAtH\u0004\u0002:}9\u0011!(P\u0007\u0002w)\u0011A\bH\u0001\u0007yI|w\u000e\u001e \n\u0003mI!!\u0007\u000e\n\u0005]A\u0012BA\u000b\u0017\u0013\t\u0019B#\u0003\u0002\u0012%%\u0011A\tE\u0001\u0012\u0019\u0016<\u0017mY=ECR,gi\u001c:nCR\u001c\u0018B\u0001$H\u0005AaUmZ1ds\u0012\u000bG/\u001a$pe6\fGO\u0003\u0002E!\u0005I\u0011n\u001d)beNLgn\u001a\t\u0003\u00156k\u0011a\u0013\u0006\u0002\u0019\u0006)1oY1mC&\u0011aj\u0013\u0002\b\u0005>|G.Z1o\u0003\u0019a\u0014N\\5u}Q)\u0011KU*U+B\u0011q\u0004\u0001\u0005\u0006E\u0015\u0001\ra\t\u0005\u0006W\u0015\u0001\r\u0001\f\u0005\bc\u0015\u0001\n\u00111\u00013\u0011\u0015AU\u00011\u0001J\u0003\u0015\u0001\u0018M]:f)\tA6\f\u0005\u0002K3&\u0011!l\u0013\u0002\u0005\u0019>tw\rC\u0003]\r\u0001\u0007Q,A\u0001t!\tq&M\u0004\u0002`AB\u0011!hS\u0005\u0003C.\u000ba\u0001\u0015:fI\u00164\u0017BA2e\u0005\u0019\u0019FO]5oO*\u0011\u0011mS\u0001\u000ea\u0006\u00148/Z(qi&|g.\u00197\u0015\u0005\u001dT\u0007c\u0001&i1&\u0011\u0011n\u0013\u0002\u0007\u001fB$\u0018n\u001c8\t\u000bq;\u0001\u0019A/\u0002)A\f'o]3XSRDw.\u001e;US6,'l\u001c8f)\rAVN\u001c\u0005\u00069\"\u0001\r!\u0018\u0005\u0006_\"\u0001\r!S\u0001\u000eC2dwn\u001e+j[\u0016TvN\\3\u00029A\f'o]3XSRDw.\u001e;US6,'l\u001c8f\u001fB$\u0018n\u001c8bYR\u0019qM]:\t\u000bqK\u0001\u0019A/\t\u000b=L\u0001\u0019A%\u00023\u0011+g-Y;miRKW.Z:uC6\u0004hi\u001c:nCR$XM\u001d\t\u0003?-\u00192aC<{!\tQ\u00050\u0003\u0002z\u0017\n1\u0011I\\=SK\u001a\u0004\"a\u001f@\u000e\u0003qT!!`\u0014\u0002\u0005%|\u0017BA@}\u00051\u0019VM]5bY&T\u0018M\u00197f)\u0005)\u0018a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$3'\u0006\u0002\u0002\b)\u001a!'!\u0003,\u0005\u0005-\u0001\u0003BA\u0007\u0003/i!!a\u0004\u000b\t\u0005E\u00111C\u0001\nk:\u001c\u0007.Z2lK\u0012T1!!\u0006L\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u00033\tyAA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!a\b\u0011\t\u0005\u0005\u0012qE\u0007\u0003\u0003GQ1!!\n(\u0003\u0011a\u0017M\\4\n\t\u0005%\u00121\u0005\u0002\u0007\u001f\nTWm\u0019;"
)
public class DefaultTimestampFormatter extends Iso8601TimestampFormatter {
   private final ZoneId zoneId;

   public static Enumeration.Value $lessinit$greater$default$3() {
      return DefaultTimestampFormatter$.MODULE$.$lessinit$greater$default$3();
   }

   public long parse(final String s) {
      long var10000;
      try {
         var10000 = SparkDateTimeUtils$.MODULE$.stringToTimestampAnsi(UTF8String.fromString(s), this.zoneId, SparkDateTimeUtils$.MODULE$.stringToTimestampAnsi$default$3());
      } catch (Throwable var4) {
         PartialFunction catchExpr$6 = this.checkParsedDiff(s, (sx) -> BoxesRunTime.boxToLong($anonfun$parse$2(this, sx)));
         if (!catchExpr$6.isDefinedAt(var4)) {
            throw var4;
         }

         var10000 = BoxesRunTime.unboxToLong(catchExpr$6.apply(var4));
      }

      return var10000;
   }

   public Option parseOptional(final String s) {
      return SparkDateTimeUtils$.MODULE$.stringToTimestamp(UTF8String.fromString(s), this.zoneId);
   }

   public long parseWithoutTimeZone(final String s, final boolean allowTimeZone) {
      long var10000;
      try {
         UTF8String utf8Value = UTF8String.fromString(s);
         var10000 = BoxesRunTime.unboxToLong(SparkDateTimeUtils$.MODULE$.stringToTimestampWithoutTimeZone(utf8Value, allowTimeZone).getOrElse(() -> {
            throw ExecutionErrors$.MODULE$.cannotParseStringAsDataTypeError(TimestampFormatter$.MODULE$.defaultPattern(), s, TimestampNTZType$.MODULE$);
         }));
      } catch (Throwable var6) {
         PartialFunction catchExpr$7 = this.checkParsedDiff(s, (sx) -> BoxesRunTime.boxToLong($anonfun$parseWithoutTimeZone$3(this, sx)));
         if (!catchExpr$7.isDefinedAt(var6)) {
            throw var6;
         }

         var10000 = BoxesRunTime.unboxToLong(catchExpr$7.apply(var6));
      }

      return var10000;
   }

   public Option parseWithoutTimeZoneOptional(final String s, final boolean allowTimeZone) {
      UTF8String utf8Value = UTF8String.fromString(s);
      return SparkDateTimeUtils$.MODULE$.stringToTimestampWithoutTimeZone(utf8Value, allowTimeZone);
   }

   // $FF: synthetic method
   public static final long $anonfun$parse$2(final DefaultTimestampFormatter $this, final String s) {
      return $this.legacyFormatter().parse(s);
   }

   // $FF: synthetic method
   public static final long $anonfun$parseWithoutTimeZone$3(final DefaultTimestampFormatter $this, final String s) {
      return $this.legacyFormatter().parse(s);
   }

   public DefaultTimestampFormatter(final ZoneId zoneId, final Locale locale, final Enumeration.Value legacyFormat, final boolean isParsing) {
      super(TimestampFormatter$.MODULE$.defaultPattern(), zoneId, locale, legacyFormat, isParsing);
      this.zoneId = zoneId;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
