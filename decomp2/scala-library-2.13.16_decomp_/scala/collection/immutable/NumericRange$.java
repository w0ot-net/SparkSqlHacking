package scala.collection.immutable;

import java.io.Serializable;
import scala.Predef;
import scala.Tuple2;
import scala.math.BigDecimal;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime$;

public final class NumericRange$ implements Serializable {
   public static final NumericRange$ MODULE$ = new NumericRange$();
   private static final Map defaultOrdering;

   static {
      Map$ var10000 = Map$.MODULE$;
      ScalaRunTime$ var10001 = ScalaRunTime$.MODULE$;
      Tuple2[] var10002 = new Tuple2[7];
      Predef.ArrowAssoc$ var10005 = Predef.ArrowAssoc$.MODULE$;
      Ordering.BigInt$ $minus$greater$extension_y = Ordering.BigInt$.MODULE$;
      Object $minus$greater$extension_$this = Numeric.BigIntIsIntegral$.MODULE$;
      Tuple2 var28 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      $minus$greater$extension_y = null;
      var10002[0] = var28;
      Predef.ArrowAssoc$ var29 = Predef.ArrowAssoc$.MODULE$;
      Ordering.Int$ $minus$greater$extension_y = Ordering.Int$.MODULE$;
      Object $minus$greater$extension_$this = Numeric.IntIsIntegral$.MODULE$;
      Tuple2 var30 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      $minus$greater$extension_y = null;
      var10002[1] = var30;
      Predef.ArrowAssoc$ var31 = Predef.ArrowAssoc$.MODULE$;
      Ordering.Short$ $minus$greater$extension_y = Ordering.Short$.MODULE$;
      Object $minus$greater$extension_$this = Numeric.ShortIsIntegral$.MODULE$;
      Tuple2 var32 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      $minus$greater$extension_y = null;
      var10002[2] = var32;
      Predef.ArrowAssoc$ var33 = Predef.ArrowAssoc$.MODULE$;
      Ordering.Byte$ $minus$greater$extension_y = Ordering.Byte$.MODULE$;
      Object $minus$greater$extension_$this = Numeric.ByteIsIntegral$.MODULE$;
      Tuple2 var34 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      $minus$greater$extension_y = null;
      var10002[3] = var34;
      Predef.ArrowAssoc$ var35 = Predef.ArrowAssoc$.MODULE$;
      Ordering.Char$ $minus$greater$extension_y = Ordering.Char$.MODULE$;
      Object $minus$greater$extension_$this = Numeric.CharIsIntegral$.MODULE$;
      Tuple2 var36 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      $minus$greater$extension_y = null;
      var10002[4] = var36;
      Predef.ArrowAssoc$ var37 = Predef.ArrowAssoc$.MODULE$;
      Ordering.Long$ $minus$greater$extension_y = Ordering.Long$.MODULE$;
      Object $minus$greater$extension_$this = Numeric.LongIsIntegral$.MODULE$;
      Tuple2 var38 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      $minus$greater$extension_y = null;
      var10002[5] = var38;
      Predef.ArrowAssoc$ var39 = Predef.ArrowAssoc$.MODULE$;
      Ordering.BigDecimal$ $minus$greater$extension_y = Ordering.BigDecimal$.MODULE$;
      Object $minus$greater$extension_$this = Numeric.BigDecimalAsIfIntegral$.MODULE$;
      Tuple2 var40 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      $minus$greater$extension_y = null;
      var10002[6] = var40;
      defaultOrdering = var10000.from(var10001.wrapRefArray(var10002));
   }

   private void bigDecimalCheckUnderflow(final Object start, final Object end, final Object step, final Integral num) {
      if (!BoxesRunTime.equals(num.minus(num.plus(start, step), start), step)) {
         FAIL$1(start, step);
      }

      if (!BoxesRunTime.equals(num.minus(end, num.minus(end, step)), step)) {
         FAIL$1(end, step);
      }
   }

   public int count(final Object start, final Object end, final Object step, final boolean isInclusive, final Integral num) {
      Object zero = num.zero();
      boolean upward = num.lt(start, end);
      boolean posStep = num.gt(step, zero);
      if (BoxesRunTime.equals(step, zero)) {
         throw new IllegalArgumentException("step cannot be 0.");
      } else if (BoxesRunTime.equals(start, end)) {
         return isInclusive ? 1 : 0;
      } else if (upward != posStep) {
         return 0;
      } else {
         int startint = num.toInt(start);
         if (BoxesRunTime.equals(start, num.fromInt(startint))) {
            int endint = num.toInt(end);
            if (BoxesRunTime.equals(end, num.fromInt(endint))) {
               int stepint = num.toInt(step);
               if (BoxesRunTime.equals(step, num.fromInt(stepint))) {
                  if (isInclusive) {
                     Range$ var28 = Range$.MODULE$;
                     return (new Range.Inclusive(startint, endint, stepint)).length();
                  }

                  Range$ var10000 = Range$.MODULE$;
                  return (new Range.Exclusive(startint, endint, stepint)).length();
               }
            }
         }

         if (num instanceof Numeric.BigDecimalAsIfIntegral) {
            this.bigDecimalCheckUnderflow(start, end, step, num);
         }

         Object one = num.one();
         Object limit = num.fromInt(Integer.MAX_VALUE);
         Object startside = num.sign(start);
         Object endside = num.sign(end);
         Object var10001;
         if (num.gteq(num.times(startside, endside), zero)) {
            Object diff = num.minus(end, start);
            Object quotient = check$1(num.quot(diff, step), num, limit);
            Object remainder = num.minus(diff, num.times(quotient, step));
            var10001 = !isInclusive && BoxesRunTime.equals(zero, remainder) ? quotient : check$1(num.plus(quotient, one), num, limit);
         } else {
            Object negone = num.fromInt(-1);
            Object startlim = posStep ? negone : one;
            Object startdiff = (!posStep || !num.lt(startlim, start)) && (posStep || !num.gt(startlim, start)) ? num.minus(startlim, start) : start;
            Object startq = check$1(num.quot(startdiff, step), num, limit);
            Object waypointA = BoxesRunTime.equals(startq, zero) ? start : num.plus(start, num.times(startq, step));
            Object waypointB = num.plus(waypointA, step);
            if (num.lt(waypointB, end) != upward) {
               var10001 = isInclusive && BoxesRunTime.equals(waypointB, end) ? num.plus(startq, num.fromInt(2)) : num.plus(startq, one);
            } else {
               Object enddiff = num.minus(end, waypointB);
               Object endq = check$1(num.quot(enddiff, step), num, limit);
               Object last = BoxesRunTime.equals(endq, zero) ? waypointB : num.plus(waypointB, num.times(endq, step));
               var10001 = num.plus(startq, num.plus(endq, !isInclusive && BoxesRunTime.equals(last, end) ? one : num.fromInt(2)));
            }

            var10001 = check$1(var10001, num, limit);
         }

         return num.toInt(var10001);
      }
   }

   public NumericRange.Exclusive apply(final Object start, final Object end, final Object step, final Integral num) {
      return new NumericRange.Exclusive(start, end, step, num);
   }

   public NumericRange.Inclusive inclusive(final Object start, final Object end, final Object step, final Integral num) {
      return new NumericRange.Inclusive(start, end, step, num);
   }

   public Map defaultOrdering() {
      return defaultOrdering;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(NumericRange$.class);
   }

   private static final void FAIL$1(final Object boundary, final Object step) {
      String var10000;
      if (boundary instanceof BigDecimal) {
         BigDecimal var3 = (BigDecimal)boundary;
         var10000 = (new StringBuilder(10)).append("Precision ").append(var3.mc().getPrecision()).toString();
      } else {
         var10000 = "Precision";
      }

      String msg = var10000;
      throw new IllegalArgumentException((new StringBuilder(45)).append(msg).append(" inadequate to represent steps of size ").append(step).append(" near ").append(boundary).toString());
   }

   private static final Object check$1(final Object t, final Integral num$1, final Object limit$1) {
      if (num$1.gt(t, limit$1)) {
         throw new IllegalArgumentException("More than Int.MaxValue elements.");
      } else {
         return t;
      }
   }

   private NumericRange$() {
   }
}
