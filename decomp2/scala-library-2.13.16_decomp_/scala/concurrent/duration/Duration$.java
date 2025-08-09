package scala.concurrent.duration;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import scala.$less$colon$less$;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.StringOps$;
import scala.collection.StringParsers$;
import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil$;
import scala.collection.mutable.ArraySeq;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.RichChar$;
import scala.runtime.RichDouble$;
import scala.runtime.Statics;

public final class Duration$ implements Serializable {
   public static final Duration$ MODULE$ = new Duration$();
   private static final List timeUnitLabels;
   private static final Map timeUnitName;
   private static final Map timeUnit;
   private static final FiniteDuration Zero;
   private static final Duration.Infinite Undefined;
   private static final Duration.Infinite Inf;
   private static final Duration.Infinite MinusInf;

   static {
      Predef.ArrowAssoc$ var10002 = Predef.ArrowAssoc$.MODULE$;
      String $minus$greater$extension_y = "d day";
      Object $minus$greater$extension_$this = TimeUnit.DAYS;
      Tuple2 var38 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var20 = null;
      Predef.ArrowAssoc$ var10005 = Predef.ArrowAssoc$.MODULE$;
      String $minus$greater$extension_y = "h hr hour";
      Object $minus$greater$extension_$this = TimeUnit.HOURS;
      Tuple2 var39 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var22 = null;
      Predef.ArrowAssoc$ var10008 = Predef.ArrowAssoc$.MODULE$;
      String $minus$greater$extension_y = "m min minute";
      Object $minus$greater$extension_$this = TimeUnit.MINUTES;
      Tuple2 var40 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var24 = null;
      Predef.ArrowAssoc$ var10011 = Predef.ArrowAssoc$.MODULE$;
      String $minus$greater$extension_y = "s sec second";
      Object $minus$greater$extension_$this = TimeUnit.SECONDS;
      Tuple2 var41 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var26 = null;
      Predef.ArrowAssoc$ var10014 = Predef.ArrowAssoc$.MODULE$;
      String $minus$greater$extension_y = "ms milli millisecond";
      Object $minus$greater$extension_$this = TimeUnit.MILLISECONDS;
      Tuple2 var42 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var28 = null;
      Predef.ArrowAssoc$ var10017 = Predef.ArrowAssoc$.MODULE$;
      String $minus$greater$extension_y = "µs micro microsecond";
      Object $minus$greater$extension_$this = TimeUnit.MICROSECONDS;
      Tuple2 var43 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var30 = null;
      Predef.ArrowAssoc$ var10020 = Predef.ArrowAssoc$.MODULE$;
      String $minus$greater$extension_y = "ns nano nanosecond";
      Object $minus$greater$extension_$this = TimeUnit.NANOSECONDS;
      Tuple2 var44 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var32 = null;
      timeUnitLabels = new $colon$colon(var38, new $colon$colon(var39, new $colon$colon(var40, new $colon$colon(var41, new $colon$colon(var42, new $colon$colon(var43, new $colon$colon(var44, Nil$.MODULE$)))))));
      timeUnitName = timeUnitLabels.toMap($less$colon$less$.MODULE$.refl()).view().mapValues((s) -> (String)MODULE$.words(s).last()).toMap($less$colon$less$.MODULE$.refl());
      List flatMap_rest = timeUnitLabels;
      $colon$colon flatMap_h = null;

      $colon$colon flatMap_nx;
      for($colon$colon flatMap_t = null; flatMap_rest != Nil$.MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
         for(Iterator flatMap_it = $anonfun$timeUnit$1((Tuple2)flatMap_rest.head()).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
            flatMap_nx = new $colon$colon(flatMap_it.next(), Nil$.MODULE$);
            if (flatMap_t == null) {
               flatMap_h = flatMap_nx;
            } else {
               flatMap_t.next_$eq(flatMap_nx);
            }
         }
      }

      Object var10000;
      if (flatMap_h == null) {
         var10000 = Nil$.MODULE$;
      } else {
         Statics.releaseFence();
         var10000 = flatMap_h;
      }

      flatMap_rest = null;
      flatMap_h = null;
      Object var35 = null;
      Object var36 = null;
      flatMap_nx = null;
      timeUnit = ((List)var10000).toMap($less$colon$less$.MODULE$.refl());
      Zero = new FiniteDuration(0L, TimeUnit.DAYS);
      Undefined = new Duration.Infinite() {
         public String toString() {
            return "Duration.Undefined";
         }

         public boolean equals(final Object other) {
            return false;
         }

         public Duration $plus(final Duration other) {
            return this;
         }

         public Duration $minus(final Duration other) {
            return this;
         }

         public Duration $times(final double factor) {
            return this;
         }

         public Duration $div(final double factor) {
            return this;
         }

         public double $div(final Duration other) {
            return Double.NaN;
         }

         public int compare(final Duration other) {
            return other == this ? 0 : 1;
         }

         public Duration unary_$minus() {
            return this;
         }

         public double toUnit(final TimeUnit unit) {
            return Double.NaN;
         }

         private Object readResolve() {
            return Duration$.MODULE$.Undefined();
         }
      };
      Inf = new Duration.Infinite() {
         public String toString() {
            return "Duration.Inf";
         }

         public int compare(final Duration other) {
            if (other == Duration$.MODULE$.Undefined()) {
               return -1;
            } else {
               return other == this ? 0 : 1;
            }
         }

         public Duration unary_$minus() {
            return Duration$.MODULE$.MinusInf();
         }

         public double toUnit(final TimeUnit unit) {
            return Double.POSITIVE_INFINITY;
         }

         private Object readResolve() {
            return Duration$.MODULE$.Inf();
         }
      };
      MinusInf = new Duration.Infinite() {
         public String toString() {
            return "Duration.MinusInf";
         }

         public int compare(final Duration other) {
            return other == this ? 0 : -1;
         }

         public Duration unary_$minus() {
            return Duration$.MODULE$.Inf();
         }

         public double toUnit(final TimeUnit unit) {
            return Double.NEGATIVE_INFINITY;
         }

         private Object readResolve() {
            return Duration$.MODULE$.MinusInf();
         }
      };
   }

   public Duration apply(final double length, final TimeUnit unit) {
      return this.fromNanos((double)unit.toNanos(1L) * length);
   }

   public FiniteDuration apply(final long length, final TimeUnit unit) {
      return new FiniteDuration(length, unit);
   }

   public FiniteDuration apply(final long length, final String unit) {
      return new FiniteDuration(length, (TimeUnit)this.timeUnit().apply(unit));
   }

   public Duration apply(final String s) {
      int filterNot$extension_filter$extension_len = s.length();
      StringBuilder filterNot$extension_filter$extension_sb = new StringBuilder(filterNot$extension_filter$extension_len);

      for(int filterNot$extension_filter$extension_i = 0; filterNot$extension_filter$extension_i < filterNot$extension_filter$extension_len; ++filterNot$extension_filter$extension_i) {
         char filterNot$extension_filter$extension_x = s.charAt(filterNot$extension_filter$extension_i);
         if (!$anonfun$apply$1(filterNot$extension_filter$extension_x)) {
            filterNot$extension_filter$extension_sb.append(filterNot$extension_filter$extension_x);
         }
      }

      String var10000 = filterNot$extension_filter$extension_len == filterNot$extension_filter$extension_sb.length() ? s : filterNot$extension_filter$extension_sb.toString();
      Object var19 = null;
      String s1 = var10000;
      switch (s1 == null ? 0 : s1.hashCode()) {
         case -1772306297:
            if ("Duration.Inf".equals(s1)) {
               return this.Inf();
            }
            break;
         case -1283193487:
            if ("MinusInf".equals(s1)) {
               return this.MinusInf();
            }
            break;
         case 73665:
            if ("Inf".equals(s1)) {
               return this.Inf();
            }
            break;
         case 1354678:
            if ("+Inf".equals(s1)) {
               return this.Inf();
            }
            break;
         case 1414260:
            if ("-Inf".equals(s1)) {
               return this.MinusInf();
            }
            break;
         case 784790902:
            if ("Duration.Undefined".equals(s1)) {
               return this.Undefined();
            }
            break;
         case 927723627:
            if ("Duration.MinusInf".equals(s1)) {
               return this.MinusInf();
            }
            break;
         case 1189350343:
            if ("PlusInf".equals(s1)) {
               return this.Inf();
            }
      }

      StringOps$ var22 = StringOps$.MODULE$;
      String takeWhile$extension_$this = StringOps$.MODULE$.reverse$extension(s1);
      int takeWhile$extension_indexWhere$extension_from = 0;
      int takeWhile$extension_indexWhere$extension_len = takeWhile$extension_$this.length();
      int takeWhile$extension_indexWhere$extension_i = takeWhile$extension_indexWhere$extension_from;

      int var10001;
      while(true) {
         if (takeWhile$extension_indexWhere$extension_i >= takeWhile$extension_indexWhere$extension_len) {
            var10001 = -1;
            break;
         }

         if (!$anonfun$apply$2(takeWhile$extension_$this.charAt(takeWhile$extension_indexWhere$extension_i))) {
            var10001 = takeWhile$extension_indexWhere$extension_i;
            break;
         }

         ++takeWhile$extension_indexWhere$extension_i;
      }

      int var14 = var10001;
      String var25;
      switch (var14) {
         case -1:
            var25 = takeWhile$extension_$this;
            break;
         default:
            var25 = takeWhile$extension_$this.substring(0, var14);
      }

      Object var21 = null;
      String unitName = var22.reverse$extension(var25);
      Option var4 = this.timeUnit().get(unitName);
      if (var4 instanceof Some) {
         TimeUnit unit = (TimeUnit)((Some)var4).value();
         String valueStr = StringOps$.MODULE$.dropRight$extension(s1, unitName.length());
         Option var23 = StringParsers$.MODULE$.parseLong(valueStr);
         if (var23 == null) {
            throw null;
         } else {
            Option map_this = var23;
            if (map_this.isEmpty()) {
               var23 = None$.MODULE$;
            } else {
               Object var18 = map_this.get();
               var23 = new Some($anonfun$apply$3(unit, BoxesRunTime.unboxToLong(var18)));
            }

            Object var20 = null;
            Option getOrElse_this = var23;
            return (Duration)(getOrElse_this.isEmpty() ? $anonfun$apply$4(valueStr, unit) : getOrElse_this.get());
         }
      } else {
         throw new NumberFormatException((new StringBuilder(13)).append("format error ").append(s).toString());
      }
   }

   private List words(final String s) {
      ArraySeq.ofRef var10000 = Predef$.MODULE$.wrapRefArray(s.trim().split("\\s+"));
      if (var10000 == null) {
         throw null;
      } else {
         return IterableOnceOps.toList$(var10000);
      }
   }

   private List expandLabels(final String labels) {
      List var2 = this.words(labels);
      if (!(var2 instanceof $colon$colon)) {
         throw new MatchError(var2);
      } else {
         $colon$colon var3 = ($colon$colon)var2;
         String hd = (String)var3.head();
         List rest = var3.next$access$1();
         if (rest == null) {
            throw null;
         } else {
            List flatMap_rest = rest;
            $colon$colon flatMap_h = null;

            $colon$colon flatMap_nx;
            for($colon$colon flatMap_t = null; flatMap_rest != Nil$.MODULE$; flatMap_rest = (List)flatMap_rest.tail()) {
               for(Iterator flatMap_it = $anonfun$expandLabels$1((String)flatMap_rest.head()).iterator(); flatMap_it.hasNext(); flatMap_t = flatMap_nx) {
                  flatMap_nx = new $colon$colon(flatMap_it.next(), Nil$.MODULE$);
                  if (flatMap_t == null) {
                     flatMap_h = flatMap_nx;
                  } else {
                     flatMap_t.next_$eq(flatMap_nx);
                  }
               }
            }

            Object var10000;
            if (flatMap_h == null) {
               var10000 = Nil$.MODULE$;
            } else {
               Statics.releaseFence();
               var10000 = flatMap_h;
            }

            flatMap_rest = null;
            flatMap_h = null;
            Object var14 = null;
            Object var15 = null;
            flatMap_nx = null;
            List $colon$colon_this = (List)var10000;
            return new $colon$colon(hd, $colon$colon_this);
         }
      }
   }

   public Map timeUnitName() {
      return timeUnitName;
   }

   public Map timeUnit() {
      return timeUnit;
   }

   public Option unapply(final String s) {
      Object var10000;
      try {
         var10000 = new Some(this.apply(s));
      } catch (RuntimeException var2) {
         var10000 = None$.MODULE$;
      }

      return ((Option)var10000).flatMap((d) -> MODULE$.unapply(d));
   }

   public Option unapply(final Duration d) {
      return (Option)(d.isFinite() ? new Some(new Tuple2(d.length(), d.unit())) : None$.MODULE$);
   }

   public Duration fromNanos(final double nanos) {
      if (Double.isInfinite(nanos)) {
         return nanos > (double)0 ? this.Inf() : this.MinusInf();
      } else if (Double.isNaN(nanos)) {
         return this.Undefined();
      } else if (!(nanos > (double)Long.MAX_VALUE) && !(nanos < (double)Long.MIN_VALUE)) {
         RichDouble$ var10001 = RichDouble$.MODULE$;
         scala.math.package$ var3 = scala.math.package$.MODULE$;
         return this.fromNanos(Math.round(nanos));
      } else {
         throw new IllegalArgumentException((new StringBuilder(46)).append("trying to construct too large duration with ").append(nanos).append("ns").toString());
      }
   }

   public FiniteDuration fromNanos(final long nanos) {
      if (nanos % 86400000000000L == 0L) {
         long var26 = nanos / 86400000000000L;
         TimeUnit apply_unit = TimeUnit.DAYS;
         long apply_length = var26;
         return new FiniteDuration(apply_length, apply_unit);
      } else if (nanos % 3600000000000L == 0L) {
         long var25 = nanos / 3600000000000L;
         TimeUnit apply_unit = TimeUnit.HOURS;
         long apply_length = var25;
         return new FiniteDuration(apply_length, apply_unit);
      } else if (nanos % 60000000000L == 0L) {
         long var24 = nanos / 60000000000L;
         TimeUnit apply_unit = TimeUnit.MINUTES;
         long apply_length = var24;
         return new FiniteDuration(apply_length, apply_unit);
      } else if (nanos % 1000000000L == 0L) {
         long var23 = nanos / 1000000000L;
         TimeUnit apply_unit = TimeUnit.SECONDS;
         long apply_length = var23;
         return new FiniteDuration(apply_length, apply_unit);
      } else if (nanos % 1000000L == 0L) {
         long var22 = nanos / 1000000L;
         TimeUnit apply_unit = TimeUnit.MILLISECONDS;
         long apply_length = var22;
         return new FiniteDuration(apply_length, apply_unit);
      } else if (nanos % 1000L == 0L) {
         long var10000 = nanos / 1000L;
         TimeUnit apply_unit = TimeUnit.MICROSECONDS;
         long apply_length = var10000;
         return new FiniteDuration(apply_length, apply_unit);
      } else {
         TimeUnit apply_unit = TimeUnit.NANOSECONDS;
         return new FiniteDuration(nanos, apply_unit);
      }
   }

   public FiniteDuration Zero() {
      return Zero;
   }

   public Duration.Infinite Undefined() {
      return Undefined;
   }

   public Duration.Infinite Inf() {
      return Inf;
   }

   public Duration.Infinite MinusInf() {
      return MinusInf;
   }

   public FiniteDuration create(final long length, final TimeUnit unit) {
      return new FiniteDuration(length, unit);
   }

   public Duration create(final double length, final TimeUnit unit) {
      return this.apply(length, unit);
   }

   public FiniteDuration create(final long length, final String unit) {
      return this.apply(length, unit);
   }

   public Duration create(final String s) {
      return this.apply(s);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Duration$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$1(final char x$1) {
      RichChar$ var10000 = RichChar$.MODULE$;
      return Character.isWhitespace(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$apply$2(final char x$2) {
      RichChar$ var10000 = RichChar$.MODULE$;
      return Character.isLetter(x$2);
   }

   // $FF: synthetic method
   public static final FiniteDuration $anonfun$apply$3(final TimeUnit unit$1, final long x$3) {
      return MODULE$.apply(x$3, unit$1);
   }

   // $FF: synthetic method
   public static final Duration $anonfun$apply$4(final String valueStr$1, final TimeUnit unit$1) {
      return MODULE$.apply(Double.parseDouble(valueStr$1), unit$1);
   }

   // $FF: synthetic method
   public static final List $anonfun$expandLabels$1(final String s) {
      return new $colon$colon(s, new $colon$colon((new StringBuilder(1)).append(s).append("s").toString(), Nil$.MODULE$));
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$timeUnit$2(final TimeUnit unit$2, final String x$5) {
      Predef.ArrowAssoc$ var10000 = Predef.ArrowAssoc$.MODULE$;
      return new Tuple2(x$5, unit$2);
   }

   // $FF: synthetic method
   public static final List $anonfun$timeUnit$1(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError((Object)null);
      } else {
         TimeUnit unit = (TimeUnit)x0$1._1();
         String names = (String)x0$1._2();
         List var10000 = MODULE$.expandLabels(names);
         if (var10000 == null) {
            throw null;
         } else {
            List map_this = var10000;
            if (map_this == Nil$.MODULE$) {
               return Nil$.MODULE$;
            } else {
               String var8 = (String)map_this.head();
               $colon$colon map_h = new $colon$colon($anonfun$timeUnit$2(unit, var8), Nil$.MODULE$);
               $colon$colon map_t = map_h;

               for(List map_rest = (List)map_this.tail(); map_rest != Nil$.MODULE$; map_rest = (List)map_rest.tail()) {
                  var8 = (String)map_rest.head();
                  $colon$colon map_nx = new $colon$colon($anonfun$timeUnit$2(unit, var8), Nil$.MODULE$);
                  map_t.next_$eq(map_nx);
                  map_t = map_nx;
               }

               Statics.releaseFence();
               return map_h;
            }
         }
      }
   }

   private Duration$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$apply$1$adapted(final Object x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$apply$1(BoxesRunTime.unboxToChar(x$1)));
   }

   // $FF: synthetic method
   public static final Object $anonfun$apply$2$adapted(final Object x$2) {
      return BoxesRunTime.boxToBoolean($anonfun$apply$2(BoxesRunTime.unboxToChar(x$2)));
   }

   // $FF: synthetic method
   public static final FiniteDuration $anonfun$apply$3$adapted(final TimeUnit unit$1, final Object x$3) {
      return $anonfun$apply$3(unit$1, BoxesRunTime.unboxToLong(x$3));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
