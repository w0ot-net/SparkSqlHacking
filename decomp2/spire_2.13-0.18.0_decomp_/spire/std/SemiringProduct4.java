package spire.std;

import algebra.ring.Semiring;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r4\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0004\u0005\u0006\u007f\u0001!\t\u0001\u0011\u0005\u0006\t\u00021\u0019!\u0012\u0005\u0006\u000f\u00021\u0019\u0001\u0013\u0005\u0006\u0015\u00021\u0019a\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006!\u0002!\t!\u0015\u0005\u0006%\u0002!\ta\u0015\u0005\u00061\u0002!\t!\u0017\u0005\u00069\u0002!\t%\u0018\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;diRR!\u0001D\u0007\u0002\u0007M$HMC\u0001\u000f\u0003\u0015\u0019\b/\u001b:f+\u0015\u0001Rf\u000e\u001e>'\r\u0001\u0011c\u0006\t\u0003%Ui\u0011a\u0005\u0006\u0002)\u0005)1oY1mC&\u0011ac\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007a)\u0003F\u0004\u0002\u001aE9\u0011!\u0004\t\b\u00037}i\u0011\u0001\b\u0006\u0003;y\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u001d%\u0011\u0011%D\u0001\bC2<WM\u0019:b\u0013\t\u0019C%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0005j\u0011B\u0001\u0014(\u0005!\u0019V-\\5sS:<'BA\u0012%!\u0019\u0011\u0012f\u000b\u001c:y%\u0011!f\u0005\u0002\u0007)V\u0004H.\u001a\u001b\u0011\u00051jC\u0002\u0001\u0003\u0006]\u0001\u0011\ra\f\u0002\u0002\u0003F\u0011\u0001g\r\t\u0003%EJ!AM\n\u0003\u000f9{G\u000f[5oOB\u0011!\u0003N\u0005\u0003kM\u00111!\u00118z!\tas\u0007B\u00039\u0001\t\u0007qFA\u0001C!\ta#\bB\u0003<\u0001\t\u0007qFA\u0001D!\taS\bB\u0003?\u0001\t\u0007qFA\u0001E\u0003\u0019!\u0013N\\5uIQ\t\u0011\t\u0005\u0002\u0013\u0005&\u00111i\u0005\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012A\u0012\t\u00041\u0015Z\u0013AC:ueV\u001cG/\u001e:feU\t\u0011\nE\u0002\u0019KY\n!b\u001d;sk\u000e$XO]34+\u0005a\u0005c\u0001\r&s\u0005Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0003=\u00032\u0001G\u0013=\u0003\u0011QXM]8\u0016\u0003!\nA\u0001\u001d7vgR\u0019\u0001\u0006\u0016,\t\u000bU;\u0001\u0019\u0001\u0015\u0002\u0005a\u0004\u0004\"B,\b\u0001\u0004A\u0013A\u0001=2\u0003\u0015!\u0018.\\3t)\rA#l\u0017\u0005\u0006+\"\u0001\r\u0001\u000b\u0005\u0006/\"\u0001\r\u0001K\u0001\u0004a><Hc\u0001\u0015_?\")Q+\u0003a\u0001Q!)q+\u0003a\u0001AB\u0011!#Y\u0005\u0003EN\u00111!\u00138u\u0001"
)
public interface SemiringProduct4 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   Semiring structure4();

   // $FF: synthetic method
   static Tuple4 zero$(final SemiringProduct4 $this) {
      return $this.zero();
   }

   default Tuple4 zero() {
      return new Tuple4(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero());
   }

   // $FF: synthetic method
   static Tuple4 plus$(final SemiringProduct4 $this, final Tuple4 x0, final Tuple4 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple4 plus(final Tuple4 x0, final Tuple4 x1) {
      return new Tuple4(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()));
   }

   // $FF: synthetic method
   static Tuple4 times$(final SemiringProduct4 $this, final Tuple4 x0, final Tuple4 x1) {
      return $this.times(x0, x1);
   }

   default Tuple4 times(final Tuple4 x0, final Tuple4 x1) {
      return new Tuple4(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()));
   }

   // $FF: synthetic method
   static Tuple4 pow$(final SemiringProduct4 $this, final Tuple4 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple4 pow(final Tuple4 x0, final int x1) {
      return new Tuple4(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1));
   }

   static void $init$(final SemiringProduct4 $this) {
   }
}
