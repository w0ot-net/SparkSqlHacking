package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.SparkIllegalArgumentException;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.LinearSeqOps;
import scala.collection.StringOps.;
import scala.collection.immutable.List;
import scala.collection.immutable.Set;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing;
import scala.util.matching.Regex;

public final class DateTimeFormatterHelper$ {
   public static final DateTimeFormatterHelper$ MODULE$ = new DateTimeFormatterHelper$();
   private static DateTimeFormatter fractionFormatter;
   private static final Map org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$cache = Collections.synchronizedMap(new LinkedHashMap() {
      public boolean removeEldestEntry(final Map.Entry eldest) {
         return this.size() > 128;
      }
   });
   private static final Regex extractor;
   private static final Set weekBasedLetters;
   private static final Set unsupportedLetters;
   private static final Set unsupportedLettersForParsing;
   private static final Set unsupportedPatternLengths;
   private static volatile boolean bitmap$0;

   static {
      extractor = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("^([^S]*)(S*)(.*)$"));
      weekBasedLetters = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapCharArray(new char[]{'Y', 'W', 'w', 'u', 'e', 'c'}));
      unsupportedLetters = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapCharArray(new char[]{'A', 'B', 'n', 'N', 'p'}));
      unsupportedLettersForParsing = (Set)scala.Predef..MODULE$.Set().apply(scala.runtime.ScalaRunTime..MODULE$.wrapCharArray(new char[]{'E', 'F', 'q', 'Q'}));
      unsupportedPatternLengths = ((IterableOnceOps)((IterableOps)(new scala.collection.immutable..colon.colon("G", new scala.collection.immutable..colon.colon("M", new scala.collection.immutable..colon.colon("L", new scala.collection.immutable..colon.colon("E", new scala.collection.immutable..colon.colon("Q", new scala.collection.immutable..colon.colon("q", scala.collection.immutable.Nil..MODULE$))))))).map((x$2) -> .MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(x$2), 5))).$plus$plus((IterableOnce)(new scala.collection.immutable..colon.colon("y", scala.collection.immutable.Nil..MODULE$)).map((x$3) -> .MODULE$.$times$extension(scala.Predef..MODULE$.augmentString(x$3), 7)))).toSet();
   }

   public Map org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$cache() {
      return org$apache$spark$sql$catalyst$util$DateTimeFormatterHelper$$cache;
   }

   public final Regex extractor() {
      return extractor;
   }

   public DateTimeFormatterBuilder createBuilder() {
      return (new DateTimeFormatterBuilder()).parseCaseInsensitive();
   }

   public DateTimeFormatter toFormatter(final DateTimeFormatterBuilder builder, final Locale locale) {
      return builder.toFormatter(locale).withChronology(IsoChronology.INSTANCE).withResolverStyle(ResolverStyle.STRICT);
   }

   public DateTimeFormatterBuilder createBuilderWithVarLengthSecondFraction(final String pattern) {
      DateTimeFormatterBuilder builder = this.createBuilder();
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])pattern.split("'")))), (x0$1) -> {
         if (x0$1 != null) {
            String var6 = (String)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            if ("".equals(var6) && idx != 0) {
               return builder.appendLiteral("'");
            }
         }

         if (x0$1 != null) {
            String patternPart = (String)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            if (idx % 2 == 0) {
               String rest = patternPart;

               while(true) {
                  if (!.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(rest))) {
                     return BoxedUnit.UNIT;
                  }

                  if (rest == null) {
                     break;
                  }

                  Option var12 = MODULE$.extractor().unapplySeq(rest);
                  if (var12.isEmpty() || var12.get() == null || ((List)var12.get()).lengthCompare(3) != 0) {
                     break;
                  }

                  String prefix = (String)((LinearSeqOps)var12.get()).apply(0);
                  String secondFraction = (String)((LinearSeqOps)var12.get()).apply(1);
                  String suffix = (String)((LinearSeqOps)var12.get()).apply(2);
                  builder.appendPattern(prefix);
                  if (.MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(secondFraction))) {
                     builder.appendFraction(ChronoField.NANO_OF_SECOND, 1, secondFraction.length(), false);
                  } else {
                     BoxedUnit var10000 = BoxedUnit.UNIT;
                  }

                  rest = suffix;
                  BoxedUnit var17 = BoxedUnit.UNIT;
               }

               throw new SparkIllegalArgumentException("INVALID_DATETIME_PATTERN.SECONDS_FRACTION", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pattern"), pattern)}))));
            }
         }

         if (x0$1 != null) {
            String patternPart = (String)x0$1._1();
            return builder.appendLiteral(patternPart);
         } else {
            throw new MatchError(x0$1);
         }
      });
      return builder;
   }

   public DateTimeFormatter buildFormatter(final String pattern, final Locale locale, final boolean varLenEnabled) {
      DateTimeFormatterBuilder builder = varLenEnabled ? this.createBuilderWithVarLengthSecondFraction(pattern) : this.createBuilder().appendPattern(pattern);
      return this.toFormatter(builder, locale);
   }

   private DateTimeFormatter fractionFormatter$lzycompute() {
      synchronized(this){}

      try {
         if (!bitmap$0) {
            DateTimeFormatterBuilder builder = this.createBuilder().append(DateTimeFormatter.ISO_LOCAL_DATE).appendLiteral(' ').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral(':').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral(':').appendValue(ChronoField.SECOND_OF_MINUTE, 2).appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true);
            fractionFormatter = this.toFormatter(builder, TimestampFormatter$.MODULE$.defaultLocale());
            bitmap$0 = true;
         }
      } catch (Throwable var4) {
         throw var4;
      }

      return fractionFormatter;
   }

   public DateTimeFormatter fractionFormatter() {
      return !bitmap$0 ? this.fractionFormatter$lzycompute() : fractionFormatter;
   }

   public final Set weekBasedLetters() {
      return weekBasedLetters;
   }

   public final Set unsupportedLetters() {
      return unsupportedLetters;
   }

   public final Set unsupportedLettersForParsing() {
      return unsupportedLettersForParsing;
   }

   public final Set unsupportedPatternLengths() {
      return unsupportedPatternLengths;
   }

   public String convertIncompatiblePattern(final String pattern, final boolean isParsing) {
      boolean eraDesignatorContained = scala.collection.ArrayOps..MODULE$.exists$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])pattern.split("'")))), (x0$1) -> BoxesRunTime.boxToBoolean($anonfun$convertIncompatiblePattern$1(x0$1)));
      return .MODULE$.stripSuffix$extension(scala.Predef..MODULE$.augmentString(scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])(pattern + " ").split("'")))), (x0$2) -> {
         if (x0$2 != null) {
            String patternPart = (String)x0$2._1();
            int index = x0$2._2$mcI$sp();
            if (index % 2 == 0) {
               .MODULE$.withFilter$extension(scala.Predef..MODULE$.augmentString(patternPart), (c) -> BoxesRunTime.boxToBoolean($anonfun$convertIncompatiblePattern$3(BoxesRunTime.unboxToChar(c)))).foreach((c) -> $anonfun$convertIncompatiblePattern$4(BoxesRunTime.unboxToChar(c)));
               .MODULE$.withFilter$extension(scala.Predef..MODULE$.augmentString(patternPart), (c) -> BoxesRunTime.boxToBoolean($anonfun$convertIncompatiblePattern$5(isParsing, BoxesRunTime.unboxToChar(c)))).foreach((c) -> $anonfun$convertIncompatiblePattern$6(pattern, BoxesRunTime.unboxToChar(c)));
               MODULE$.unsupportedPatternLengths().withFilter((style) -> BoxesRunTime.boxToBoolean($anonfun$convertIncompatiblePattern$7(patternPart, style))).foreach((style) -> {
                  throw new SparkIllegalArgumentException("INVALID_DATETIME_PATTERN.LENGTH", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pattern"), style)}))));
               });
               return !eraDesignatorContained ? patternPart.replace("y", "u") : patternPart;
            } else {
               return patternPart;
            }
         } else {
            throw new MatchError(x0$2);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("'")), " ");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertIncompatiblePattern$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         String patternPart = (String)x0$1._1();
         int index = x0$1._2$mcI$sp();
         return index % 2 == 0 && patternPart.contains("G");
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertIncompatiblePattern$3(final char c) {
      return MODULE$.weekBasedLetters().contains(BoxesRunTime.boxToCharacter(c));
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$convertIncompatiblePattern$4(final char c) {
      throw new SparkIllegalArgumentException("INCONSISTENT_BEHAVIOR_CROSS_VERSION.DATETIME_WEEK_BASED_PATTERN", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("c"), Character.toString(c))}))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertIncompatiblePattern$5(final boolean isParsing$1, final char c) {
      return MODULE$.unsupportedLetters().contains(BoxesRunTime.boxToCharacter(c)) || isParsing$1 && MODULE$.unsupportedLettersForParsing().contains(BoxesRunTime.boxToCharacter(c));
   }

   // $FF: synthetic method
   public static final Nothing $anonfun$convertIncompatiblePattern$6(final String pattern$4, final char c) {
      throw new SparkIllegalArgumentException("INVALID_DATETIME_PATTERN.ILLEGAL_CHARACTER", (scala.collection.immutable.Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("c"), Character.toString(c)), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("pattern"), pattern$4)}))));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$convertIncompatiblePattern$7(final String patternPart$1, final String style) {
      return patternPart$1.contains(style);
   }

   private DateTimeFormatterHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
