package org.apache.spark.sql.catalyst.util;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.QueryContext;
import org.apache.spark.sql.errors.ExecutionErrors$;
import scala.Function0;
import scala.collection.StringOps.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

public final class MathUtils$ {
   public static final MathUtils$ MODULE$ = new MathUtils$();

   public int addExact(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.addExact(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public int addExact(final int a, final int b, final QueryContext context) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.addExact(a, b), "try_add", context));
   }

   public long addExact(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.addExact(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public long addExact(final long a, final long b, final QueryContext context) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.addExact(a, b), "try_add", context));
   }

   public int subtractExact(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.subtractExact(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public int subtractExact(final int a, final int b, final QueryContext context) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.subtractExact(a, b), "try_subtract", context));
   }

   public long subtractExact(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.subtractExact(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public long subtractExact(final long a, final long b, final QueryContext context) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.subtractExact(a, b), "try_subtract", context));
   }

   public int multiplyExact(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.multiplyExact(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public int multiplyExact(final int a, final int b, final QueryContext context) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.multiplyExact(a, b), "try_multiply", context));
   }

   public long multiplyExact(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.multiplyExact(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public long multiplyExact(final long a, final long b, final QueryContext context) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.multiplyExact(a, b), "try_multiply", context));
   }

   public byte negateExact(final byte a) {
      if (a == -128) {
         throw ExecutionErrors$.MODULE$.arithmeticOverflowError("byte overflow", ExecutionErrors$.MODULE$.arithmeticOverflowError$default$2(), ExecutionErrors$.MODULE$.arithmeticOverflowError$default$3());
      } else {
         return (byte)(-a);
      }
   }

   public short negateExact(final short a) {
      if (a == Short.MIN_VALUE) {
         throw ExecutionErrors$.MODULE$.arithmeticOverflowError("short overflow", ExecutionErrors$.MODULE$.arithmeticOverflowError$default$2(), ExecutionErrors$.MODULE$.arithmeticOverflowError$default$3());
      } else {
         return (short)(-a);
      }
   }

   public int negateExact(final int a) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.negateExact(a), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public long negateExact(final long a) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.negateExact(a), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public int toIntExact(final long a) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.toIntExact(a), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public int floorDiv(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.floorDiv(a, b), "try_divide", this.withOverflow$default$3()));
   }

   public long floorDiv(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.floorDiv(a, b), "try_divide", this.withOverflow$default$3()));
   }

   public int floorMod(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.withOverflow((JFunction0.mcI.sp)() -> Math.floorMod(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public long floorMod(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.withOverflow((JFunction0.mcJ.sp)() -> Math.floorMod(a, b), this.withOverflow$default$2(), this.withOverflow$default$3()));
   }

   public Object withOverflow(final Function0 f, final String hint, final QueryContext context) {
      try {
         return f.apply();
      } catch (ArithmeticException var5) {
         throw ExecutionErrors$.MODULE$.arithmeticOverflowError(var5.getMessage(), hint, context);
      }
   }

   public String withOverflow$default$2() {
      return "";
   }

   public QueryContext withOverflow$default$3() {
      return null;
   }

   public String withOverflowCode(final String evalCode, final String context) {
      return .MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n       |try {\n       |  " + evalCode + "\n       |} catch (ArithmeticException e) {\n       |  throw QueryExecutionErrors.arithmeticOverflowError(e.getMessage(), \"\", " + context + ");\n       |}\n       |"));
   }

   private MathUtils$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
