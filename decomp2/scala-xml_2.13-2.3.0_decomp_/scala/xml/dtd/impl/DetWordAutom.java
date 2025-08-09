package scala.xml.dtd.impl;

import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.mutable.Map;
import scala.collection.mutable.StringBuilder;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005a3aAC\u0006\u0002\u00025\u0019\u0002\"B\r\u0001\t\u0003Y\u0002b\u0002\u0014\u0001\u0005\u00045\ta\n\u0005\bW\u0001\u0011\rQ\"\u0001-\u0011\u001d\u0001\u0004A1A\u0007\u0002EBqa\u000f\u0001C\u0002\u001b\u0005A\u0006C\u0003=\u0001\u0011\u0005Q\bC\u0003D\u0001\u0011\u0005A\tC\u0003G\u0001\u0011\u0005q\tC\u0003L\u0001\u0011\u0005CJ\u0001\u0007EKR<vN\u001d3BkR|WN\u0003\u0002\r\u001b\u0005!\u0011.\u001c9m\u0015\tqq\"A\u0002ei\u0012T!\u0001E\t\u0002\u0007alGNC\u0001\u0013\u0003\u0015\u00198-\u00197b+\t!\u0002e\u0005\u0002\u0001+A\u0011acF\u0007\u0002#%\u0011\u0001$\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001\b\t\u0004;\u0001qR\"A\u0006\u0011\u0005}\u0001C\u0002\u0001\u0003\u0006C\u0001\u0011\rA\t\u0002\u0002)F\u00111%\u0006\t\u0003-\u0011J!!J\t\u0003\u000f9{G\u000f[5oO\u00069an\u001d;bi\u0016\u001cX#\u0001\u0015\u0011\u0005YI\u0013B\u0001\u0016\u0012\u0005\rIe\u000e^\u0001\u0007M&t\u0017\r\\:\u0016\u00035\u00022A\u0006\u0018)\u0013\ty\u0013CA\u0003BeJ\f\u00170A\u0003eK2$\u0018-F\u00013!\r1bf\r\t\u0005ier\u0002&D\u00016\u0015\t1t'A\u0004nkR\f'\r\\3\u000b\u0005a\n\u0012AC2pY2,7\r^5p]&\u0011!(\u000e\u0002\u0004\u001b\u0006\u0004\u0018a\u00023fM\u0006,H\u000e^\u0001\bSN4\u0015N\\1m)\tq\u0014\t\u0005\u0002\u0017\u007f%\u0011\u0001)\u0005\u0002\b\u0005>|G.Z1o\u0011\u0015\u0011e\u00011\u0001)\u0003\u0005\t\u0018AB5t'&t7\u000e\u0006\u0002?\u000b\")!i\u0002a\u0001Q\u0005!a.\u001a=u)\rA\u0003*\u0013\u0005\u0006\u0005\"\u0001\r\u0001\u000b\u0005\u0006\u0015\"\u0001\rAH\u0001\u0006Y\u0006\u0014W\r\\\u0001\ti>\u001cFO]5oOR\tQ\n\u0005\u0002O+:\u0011qj\u0015\t\u0003!Fi\u0011!\u0015\u0006\u0003%j\ta\u0001\u0010:p_Rt\u0014B\u0001+\u0012\u0003\u0019\u0001&/\u001a3fM&\u0011ak\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Q\u000b\u0002"
)
public abstract class DetWordAutom {
   public abstract int nstates();

   public abstract int[] finals();

   public abstract Map[] delta();

   public abstract int[] default();

   public boolean isFinal(final int q) {
      return this.finals()[q] != 0;
   }

   public boolean isSink(final int q) {
      return this.delta()[q].isEmpty() && this.default()[q] == q;
   }

   public int next(final int q, final Object label) {
      return BoxesRunTime.unboxToInt(this.delta()[q].getOrElse(label, (JFunction0.mcI.sp)() -> this.default()[q]));
   }

   public String toString() {
      scala.collection.immutable.Map map = .MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(.MODULE$.intArrayOps(this.finals()))), (x$1) -> x$1.swap$mcII$sp(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      StringBuilder sb = new StringBuilder((new java.lang.StringBuilder(39)).append("[DetWordAutom  nstates=").append(this.nstates()).append(" finals=").append(map).append(" delta=\n").toString());
      scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), this.nstates()).foreach((i) -> $anonfun$toString$2(this, sb, BoxesRunTime.unboxToInt(i)));
      return sb.toString();
   }

   // $FF: synthetic method
   public static final Object $anonfun$toString$2(final DetWordAutom $this, final StringBuilder sb$1, final int i) {
      sb$1.append((new java.lang.StringBuilder(3)).append(i).append("->").append($this.delta()[i]).append("\n").toString());
      return i < $this.default().length ? sb$1.append((new java.lang.StringBuilder(3)).append("_>").append($this.default()[i]).append("\n").toString()) : BoxedUnit.UNIT;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
