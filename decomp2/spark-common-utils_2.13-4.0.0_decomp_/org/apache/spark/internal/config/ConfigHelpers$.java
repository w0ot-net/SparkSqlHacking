package org.apache.spark.internal.config;

import java.util.concurrent.TimeUnit;
import java.util.regex.PatternSyntaxException;
import org.apache.spark.network.util.ByteUnit;
import org.apache.spark.network.util.JavaUtils;
import org.apache.spark.util.SparkStringUtils$;
import scala.Function1;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

public final class ConfigHelpers$ {
   public static final ConfigHelpers$ MODULE$ = new ConfigHelpers$();

   public Object toNumber(final String s, final Function1 converter, final String key, final String configType) {
      try {
         return converter.apply(s.trim());
      } catch (NumberFormatException var5) {
         throw new IllegalArgumentException(key + " should be " + configType + ", but was " + s);
      }
   }

   public boolean toBoolean(final String s, final String key) {
      try {
         return .MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(s.trim()));
      } catch (IllegalArgumentException var3) {
         throw new IllegalArgumentException(key + " should be boolean, but was " + s);
      }
   }

   public Seq stringToSeq(final String str, final Function1 converter) {
      return (Seq)SparkStringUtils$.MODULE$.stringToSeq(str).map(converter);
   }

   public String seqToString(final Seq v, final Function1 stringConverter) {
      return ((IterableOnceOps)v.map(stringConverter)).mkString(",");
   }

   public long timeFromString(final String str, final TimeUnit unit) {
      return JavaUtils.timeStringAs(str, unit);
   }

   public String timeToString(final long v, final TimeUnit unit) {
      long var10000 = TimeUnit.MILLISECONDS.convert(v, unit);
      return var10000 + "ms";
   }

   public long byteFromString(final String str, final ByteUnit unit) {
      Tuple2 var5 = str.length() > 0 && str.charAt(0) == '-' ? new Tuple2(str.substring(1), BoxesRunTime.boxToInteger(-1)) : new Tuple2(str, BoxesRunTime.boxToInteger(1));
      if (var5 != null) {
         String input = (String)var5._1();
         int multiplier = var5._2$mcI$sp();
         Tuple2 var4 = new Tuple2(input, BoxesRunTime.boxToInteger(multiplier));
         String input = (String)var4._1();
         int multiplier = var4._2$mcI$sp();
         return (long)multiplier * JavaUtils.byteStringAs(input, unit);
      } else {
         throw new MatchError(var5);
      }
   }

   public String byteToString(final long v, final ByteUnit unit) {
      long var10000 = unit.convertTo(v, ByteUnit.BYTE);
      return var10000 + "b";
   }

   public Regex regexFromString(final String str, final String key) {
      try {
         return .MODULE$.r$extension(scala.Predef..MODULE$.augmentString(str));
      } catch (PatternSyntaxException var4) {
         throw new IllegalArgumentException(key + " should be a regex, but was " + str, var4);
      }
   }

   private ConfigHelpers$() {
   }
}
