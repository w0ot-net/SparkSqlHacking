package spire.algebra;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.math.Rational;
import spire.math.Rational$;

@ScalaSignature(
   bytes = "\u0006\u0005Q4q!\u0004\b\u0011\u0002\u0007\u00051\u0003C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003-\u0001\u0011\u0005Q\u0006C\u00031\u0001\u0011\u0005\u0011\u0007C\u00034\u0001\u0011\u0005A\u0007C\u00037\u0001\u0011\u0005q\u0007C\u0003=\u0001\u0019\u0005Q\bC\u0003L\u0001\u0011\u0005AjB\u0003U\u001d!\u0005QKB\u0003\u000e\u001d!\u0005a\u000bC\u0003c\u0013\u0011\u00051\rC\u0003e\u0013\u0011\u0005Q\rC\u0004m\u0013\u0005\u0005I\u0011B7\u0003\u0015%\u001b\u0018J\u001c;fOJ\fGN\u0003\u0002\u0010!\u00059\u0011\r\\4fEJ\f'\"A\t\u0002\u000bM\u0004\u0018N]3\u0004\u0001U\u0011A#I\n\u0004\u0001UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"aA!osB\u0019A$H\u0010\u000e\u00039I!A\b\b\u0003\u0015%\u001b(+\u0019;j_:\fG\u000e\u0005\u0002!C1\u0001A!\u0002\u0012\u0001\u0005\u0004\u0019#!A!\u0012\u0005\u0011*\u0002C\u0001\f&\u0013\t1sCA\u0004O_RD\u0017N\\4\u0002\r\u0011Jg.\u001b;%)\u0005I\u0003C\u0001\f+\u0013\tYsC\u0001\u0003V]&$\u0018\u0001B2fS2$\"a\b\u0018\t\u000b=\u0012\u0001\u0019A\u0010\u0002\u0003\u0005\fQA\u001a7p_J$\"a\b\u001a\t\u000b=\u001a\u0001\u0019A\u0010\u0002\u000bI|WO\u001c3\u0015\u0005})\u0004\"B\u0018\u0005\u0001\u0004y\u0012aB5t/\"|G.\u001a\u000b\u0003qm\u0002\"AF\u001d\n\u0005i:\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006_\u0015\u0001\raH\u0001\ti>\u0014\u0015nZ%oiR\u0011aH\u0013\t\u0003\u007f\u001ds!\u0001Q#\u000f\u0005\u0005#U\"\u0001\"\u000b\u0005\r\u0013\u0012A\u0002\u001fs_>$h(C\u0001\u0019\u0013\t1u#A\u0004qC\u000e\\\u0017mZ3\n\u0005!K%A\u0002\"jO&sGO\u0003\u0002G/!)qF\u0002a\u0001?\u0005QAo\u001c*bi&|g.\u00197\u0015\u00055\u001b\u0006C\u0001(R\u001b\u0005y%B\u0001)\u0011\u0003\u0011i\u0017\r\u001e5\n\u0005I{%\u0001\u0003*bi&|g.\u00197\t\u000b=:\u0001\u0019A\u0010\u0002\u0015%\u001b\u0018J\u001c;fOJ\fG\u000e\u0005\u0002\u001d\u0013M\u0019\u0011b\u0016.\u0011\u0005YA\u0016BA-\u0018\u0005\u0019\te.\u001f*fMB\u00111\fY\u0007\u00029*\u0011QLX\u0001\u0003S>T\u0011aX\u0001\u0005U\u00064\u0018-\u0003\u0002b9\na1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!V\u0001\u0006CB\u0004H._\u000b\u0003M&$\"a\u001a6\u0011\u0007q\u0001\u0001\u000e\u0005\u0002!S\u0012)!e\u0003b\u0001G!)1n\u0003a\u0002O\u0006\t\u0011)\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001o!\ty'/D\u0001q\u0015\t\th,\u0001\u0003mC:<\u0017BA:q\u0005\u0019y%M[3di\u0002"
)
public interface IsIntegral extends IsRational {
   static IsIntegral apply(final IsIntegral A) {
      return IsIntegral$.MODULE$.apply(A);
   }

   // $FF: synthetic method
   static Object ceil$(final IsIntegral $this, final Object a) {
      return $this.ceil(a);
   }

   default Object ceil(final Object a) {
      return a;
   }

   // $FF: synthetic method
   static Object floor$(final IsIntegral $this, final Object a) {
      return $this.floor(a);
   }

   default Object floor(final Object a) {
      return a;
   }

   // $FF: synthetic method
   static Object round$(final IsIntegral $this, final Object a) {
      return $this.round(a);
   }

   default Object round(final Object a) {
      return a;
   }

   // $FF: synthetic method
   static boolean isWhole$(final IsIntegral $this, final Object a) {
      return $this.isWhole(a);
   }

   default boolean isWhole(final Object a) {
      return true;
   }

   BigInt toBigInt(final Object a);

   // $FF: synthetic method
   static Rational toRational$(final IsIntegral $this, final Object a) {
      return $this.toRational(a);
   }

   default Rational toRational(final Object a) {
      return Rational$.MODULE$.apply(this.toBigInt(a));
   }

   static void $init$(final IsIntegral $this) {
   }
}
