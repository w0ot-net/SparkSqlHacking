package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.math.Ordered;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ma\u0001\u0002\u000b\u0016\u0005qA\u0001b\f\u0001\u0003\u0006\u0004%\t\u0001\r\u0005\ti\u0001\u0011\t\u0011)A\u0005c!aQ\u0007\u0001C\u0001\u0002\u0003\u0005\t\u0011!C\u0005m!)\u0001\b\u0001C\u0001s!)A\b\u0001C!{!9a\tAA\u0001\n\u0003:\u0005b\u0002%\u0001\u0003\u0003%\t%S\u0004\u0006%VA\ta\u0015\u0004\u0006)UA\t\u0001\u0016\u0005\u0006k%!\ta\u0017\u0005\u00079&\u0001\u000b\u0011\u0002 \t\u000buKA\u0011\u00020\t\u000b!LA\u0011B5\t\u000bMLA\u0011\u0001;\t\u000bMLA\u0011\u0001<\t\u000baLAQA=\t\u000byLAQA@\t\u0013\u0005\r\u0011\"!A\u0005\u0006\u0005\u0015\u0001\"CA\u0005\u0013\u0005\u0005IQAA\u0006\u0005)\u0001&/Z2fI\u0016t7-\u001a\u0006\u0003-]\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u00031e\tqA]3gY\u0016\u001cGOC\u0001\u001b\u0003\u0015\u00198-\u00197b\u0007\u0001\u00192\u0001A\u000f\"!\tqr$D\u0001\u001a\u0013\t\u0001\u0013D\u0001\u0004B]f4\u0016\r\u001c\t\u0004E)jcBA\u0012)\u001d\t!s%D\u0001&\u0015\t13$\u0001\u0004=e>|GOP\u0005\u00025%\u0011\u0011&G\u0001\ba\u0006\u001c7.Y4f\u0013\tYCFA\u0004Pe\u0012,'/\u001a3\u000b\u0005%J\u0002C\u0001\u0018\u0001\u001b\u0005)\u0012!\u00027fm\u0016dW#A\u0019\u0011\u0005y\u0011\u0014BA\u001a\u001a\u0005\rIe\u000e^\u0001\u0007Y\u00164X\r\u001c\u0011\u0002\rqJg.\u001b;?)\tis\u0007C\u00030\u0007\u0001\u0007\u0011'A\u0004d_6\u0004\u0018M]3\u0015\u0005ER\u0004\"B\u001e\u0005\u0001\u0004i\u0013\u0001\u0002;iCR\f\u0001\u0002^8TiJLgn\u001a\u000b\u0002}A\u0011q\bR\u0007\u0002\u0001*\u0011\u0011IQ\u0001\u0005Y\u0006twMC\u0001D\u0003\u0011Q\u0017M^1\n\u0005\u0015\u0003%AB*ue&tw-\u0001\u0005iCND7i\u001c3f)\u0005\t\u0014AB3rk\u0006d7\u000f\u0006\u0002K\u001bB\u0011adS\u0005\u0003\u0019f\u0011qAQ8pY\u0016\fg\u000eC\u0004O\u000f\u0005\u0005\t\u0019A(\u0002\u0007a$\u0013\u0007\u0005\u0002\u001f!&\u0011\u0011+\u0007\u0002\u0004\u0003:L\u0018A\u0003)sK\u000e,G-\u001a8dKB\u0011a&C\n\u0004\u0013UC\u0006C\u0001\u0010W\u0013\t9\u0016D\u0001\u0004B]f\u0014VM\u001a\t\u0005=e\u000bT&\u0003\u0002[3\tIa)\u001e8di&|g.\r\u000b\u0002'\u0006IQI\u001d:pe:\u000bW.Z\u0001\u000fSN\f5o]5h]6,g\u000e^(q)\tQu\fC\u0003a\u0019\u0001\u0007\u0011-\u0001\u0003oC6,\u0007C\u00012g\u001d\t\u0019G\r\u0005\u0002%3%\u0011Q-G\u0001\u0007!J,G-\u001a4\n\u0005\u0015;'BA3\u001a\u0003%1\u0017N]:u\u0007\"\f'\u000f\u0006\u0002.U\")1.\u0004a\u0001Y\u0006\t1\r\u0005\u0002na:\u0011aF\\\u0005\u0003_V\tQa\u00115beNL!!\u001d:\u0003\u0013\r{G-\u001a)pS:$(BA8\u0016\u0003\u0015\t\u0007\u000f\u001d7z)\tiS\u000fC\u00030\u001d\u0001\u0007\u0011\u0007\u0006\u0002.o\")\u0001m\u0004a\u0001C\u0006\t2m\\7qCJ,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005idHCA\u0019|\u0011\u0015Y\u0004\u00031\u0001.\u0011\u0015i\b\u00031\u0001.\u0003\u0015!C\u000f[5t\u0003I!xn\u0015;sS:<G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0007u\n\t\u0001C\u0003~#\u0001\u0007Q&\u0001\niCND7i\u001c3fI\u0015DH/\u001a8tS>tGcA$\u0002\b!)QP\u0005a\u0001[\u0005\u0001R-];bYN$S\r\u001f;f]NLwN\u001c\u000b\u0005\u0003\u001b\t\t\u0002F\u0002K\u0003\u001fAqAT\n\u0002\u0002\u0003\u0007q\nC\u0003~'\u0001\u0007Q\u0006"
)
public final class Precedence implements Ordered {
   private final int level;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return Precedence$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      Precedence$ var10000 = Precedence$.MODULE$;
      return Integer.hashCode($this);
   }

   public static String toString$extension(final int $this) {
      return Precedence$.MODULE$.toString$extension($this);
   }

   public static int compare$extension(final int $this, final int that) {
      return Precedence$.MODULE$.compare$extension($this, that);
   }

   public static int apply(final String name) {
      return Precedence$.MODULE$.apply(name);
   }

   public static int apply(final int level) {
      Precedence$ var10000 = Precedence$.MODULE$;
      return level;
   }

   public static Function1 andThen(final Function1 g) {
      return Function1::$anonfun$andThen$1;
   }

   public static Function1 compose(final Function1 g) {
      return Function1::$anonfun$compose$1;
   }

   public boolean $less(final Object that) {
      return Ordered.$less$(this, that);
   }

   public boolean $greater(final Object that) {
      return Ordered.$greater$(this, that);
   }

   public boolean $less$eq(final Object that) {
      return Ordered.$less$eq$(this, that);
   }

   public boolean $greater$eq(final Object that) {
      return Ordered.$greater$eq$(this, that);
   }

   public int compareTo(final Object that) {
      return Ordered.compareTo$(this, that);
   }

   public int level() {
      return this.level;
   }

   public int compare(final int that) {
      return Precedence$.MODULE$.compare$extension(this.level(), that);
   }

   public String toString() {
      return Precedence$.MODULE$.toString$extension(this.level());
   }

   public int hashCode() {
      Precedence$ var10000 = Precedence$.MODULE$;
      return Integer.hashCode(this.level());
   }

   public boolean equals(final Object x$1) {
      return Precedence$.MODULE$.equals$extension(this.level(), x$1);
   }

   public Precedence(final int level) {
      this.level = level;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
