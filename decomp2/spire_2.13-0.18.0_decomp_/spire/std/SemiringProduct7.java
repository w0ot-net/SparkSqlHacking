package spire.std;

import algebra.ring.Semiring;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4\u0001\"\u0004\b\u0011\u0002\u0007\u0005\u0001C\u0005\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u0002!\tA\u001a\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006[\u0002!\tA\u001c\u0005\u0006c\u0002!\tE\u001d\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;di^R!a\u0004\t\u0002\u0007M$HMC\u0001\u0012\u0003\u0015\u0019\b/\u001b:f+!\u0019\u0002GO\u001fA\u0007\u001aK5c\u0001\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u00042a\u0007\u0015,\u001d\taRE\u0004\u0002\u001eG9\u0011aDI\u0007\u0002?)\u0011\u0001%I\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0011#\u0003\u0002%!\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0014(\u0003\u001d\u0001\u0018mY6bO\u0016T!\u0001\n\t\n\u0005%R#\u0001C*f[&\u0014\u0018N\\4\u000b\u0005\u0019:\u0003#C\u000b-]ebtHQ#I\u0013\ticC\u0001\u0004UkBdWm\u000e\t\u0003_Ab\u0001\u0001B\u00032\u0001\t\u0007!GA\u0001B#\t\u0019d\u0007\u0005\u0002\u0016i%\u0011QG\u0006\u0002\b\u001d>$\b.\u001b8h!\t)r'\u0003\u00029-\t\u0019\u0011I\\=\u0011\u0005=RD!B\u001e\u0001\u0005\u0004\u0011$!\u0001\"\u0011\u0005=jD!\u0002 \u0001\u0005\u0004\u0011$!A\"\u0011\u0005=\u0002E!B!\u0001\u0005\u0004\u0011$!\u0001#\u0011\u0005=\u001aE!\u0002#\u0001\u0005\u0004\u0011$!A#\u0011\u0005=2E!B$\u0001\u0005\u0004\u0011$!\u0001$\u0011\u0005=JE!\u0002&\u0001\u0005\u0004\u0011$!A$\u0002\r\u0011Jg.\u001b;%)\u0005i\u0005CA\u000bO\u0013\tyeC\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t!\u000bE\u0002\u001cQ9\n!b\u001d;sk\u000e$XO]33+\u0005)\u0006cA\u000e)s\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003a\u00032a\u0007\u0015=\u0003)\u0019HO];diV\u0014X\rN\u000b\u00027B\u00191\u0004K \u0002\u0015M$(/^2ukJ,W'F\u0001_!\rY\u0002FQ\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#A1\u0011\u0007mAS)\u0001\u0006tiJ,8\r^;sK^*\u0012\u0001\u001a\t\u00047!B\u0015\u0001\u0002>fe>,\u0012aK\u0001\u0005a2,8\u000fF\u0002,S.DQA\u001b\u0006A\u0002-\n!\u0001\u001f\u0019\t\u000b1T\u0001\u0019A\u0016\u0002\u0005a\f\u0014!\u0002;j[\u0016\u001cHcA\u0016pa\")!n\u0003a\u0001W!)An\u0003a\u0001W\u0005\u0019\u0001o\\<\u0015\u0007-\u001aH\u000fC\u0003k\u0019\u0001\u00071\u0006C\u0003m\u0019\u0001\u0007Q\u000f\u0005\u0002\u0016m&\u0011qO\u0006\u0002\u0004\u0013:$\b"
)
public interface SemiringProduct7 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   Semiring structure4();

   Semiring structure5();

   Semiring structure6();

   Semiring structure7();

   // $FF: synthetic method
   static Tuple7 zero$(final SemiringProduct7 $this) {
      return $this.zero();
   }

   default Tuple7 zero() {
      return new Tuple7(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero());
   }

   // $FF: synthetic method
   static Tuple7 plus$(final SemiringProduct7 $this, final Tuple7 x0, final Tuple7 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple7 plus(final Tuple7 x0, final Tuple7 x1) {
      return new Tuple7(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()));
   }

   // $FF: synthetic method
   static Tuple7 times$(final SemiringProduct7 $this, final Tuple7 x0, final Tuple7 x1) {
      return $this.times(x0, x1);
   }

   default Tuple7 times(final Tuple7 x0, final Tuple7 x1) {
      return new Tuple7(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()));
   }

   // $FF: synthetic method
   static Tuple7 pow$(final SemiringProduct7 $this, final Tuple7 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple7 pow(final Tuple7 x0, final int x1) {
      return new Tuple7(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1));
   }

   static void $init$(final SemiringProduct7 $this) {
   }
}
