package algebra.ring;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3q\u0001C\u0005\u0011\u0002\u0007\u0005a\u0002C\u0003#\u0001\u0011\u00051\u0005C\u0003(\u0001\u0011\u0015\u0003fB\u0003,\u0013!\u0005AFB\u0003\t\u0013!\u0005Q\u0006C\u0003A\t\u0011\u0005\u0011\tC\u0003C\t\u0011\u00151\tC\u0004O\t\u0005\u0005I\u0011B(\u0003\u000f\t{w\u000e\u001c*oO*\u0011!bC\u0001\u0005e&twMC\u0001\r\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u00109M\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\u0007\u0005s\u0017\u0010E\u0002\u00181ii\u0011!C\u0005\u00033%\u0011abQ8n[V$\u0018\r^5wKJsw\r\u0005\u0002\u001c91\u0001A!B\u000f\u0001\u0005\u0004q\"!A!\u0012\u0005}\u0001\u0002CA\t!\u0013\t\t#CA\u0004O_RD\u0017N\\4\u0002\r\u0011Jg.\u001b;%)\u0005!\u0003CA\t&\u0013\t1#C\u0001\u0003V]&$\u0018A\u00028fO\u0006$X\r\u0006\u0002\u001bS!)!F\u0001a\u00015\u0005\t\u00010A\u0004C_>d'K\\4\u0011\u0005]!1#\u0002\u0003/cUB\u0004CA\t0\u0013\t\u0001$C\u0001\u0004B]f\u0014VM\u001a\t\u0004/I\"\u0014BA\u001a\n\u0005Y\tE\rZ5uSZ,wI]8va\u001a+hn\u0019;j_:\u001c\bCA\f\u0001!\r9b\u0007N\u0005\u0003o%\u0011\u0001%T;mi&\u0004H.[2bi&4XmU3nS\u001e\u0014x.\u001e9Gk:\u001cG/[8ogB\u0011\u0011HP\u0007\u0002u)\u00111\bP\u0001\u0003S>T\u0011!P\u0001\u0005U\u00064\u0018-\u0003\u0002@u\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012\u0001L\u0001\u0006CB\u0004H._\u000b\u0003\t\u001e#\"!\u0012%\u0011\u0007]\u0001a\t\u0005\u0002\u001c\u000f\u0012)QD\u0002b\u0001=!)\u0011J\u0002a\u0002\u000b\u0006\t!\u000f\u000b\u0002\u0007\u0017B\u0011\u0011\u0003T\u0005\u0003\u001bJ\u0011a!\u001b8mS:,\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001)\u0011\u0005E#V\"\u0001*\u000b\u0005Mc\u0014\u0001\u00027b]\u001eL!!\u0016*\u0003\r=\u0013'.Z2u\u0001"
)
public interface BoolRng extends CommutativeRng {
   static BoolRng apply(final BoolRng r) {
      return BoolRng$.MODULE$.apply(r);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return BoolRng$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return BoolRng$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static Object negate$(final BoolRng $this, final Object x) {
      return $this.negate(x);
   }

   default Object negate(final Object x) {
      return x;
   }

   static void $init$(final BoolRng $this) {
   }
}
