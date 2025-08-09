package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Eq;
import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.NotGiven$;
import spire.algebra.NRoot;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u0005i4q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\rq\u0003C\u0003m\u0001\u0011\rQN\u0001\u0007KKRLen\u001d;b]\u000e,7O\u0003\u0002\u0007\u000f\u0005!Q.\u0019;i\u0015\u0005A\u0011!B:qSJ,7\u0001A\n\u0003\u0001-\u0001\"\u0001D\b\u000e\u00035Q\u0011AD\u0001\u0006g\u000e\fG.Y\u0005\u0003!5\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001\u0014!\taA#\u0003\u0002\u0016\u001b\t!QK\\5u\u0003)QU\r^!mO\u0016\u0014'/Y\u000b\u00031}!\u0002\"G\u001eD\u0011^k&m\u001a\t\u00045miR\"A\u0003\n\u0005q)!A\u0003&fi\u0006cw-\u001a2sCB\u0011ad\b\u0007\u0001\t%\u0001#\u0001)A\u0001\u0002\u000b\u0007\u0011EA\u0001U#\t\u0011S\u0005\u0005\u0002\rG%\u0011A%\u0004\u0002\b\u001d>$\b.\u001b8h!\taa%\u0003\u0002(\u001b\t\u0019\u0011I\\=)\t}ICF\u000e\t\u0003\u0019)J!aK\u0007\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G5r\u0003g\f\b\u0003\u00199J!aL\u0007\u0002\u000b\u0019cw.\u0019;2\t\u0011\nTG\u0004\b\u0003eUj\u0011a\r\u0006\u0003i%\ta\u0001\u0010:p_Rt\u0014\"\u0001\b2\u000b\r:\u0004HO\u001d\u000f\u00051A\u0014BA\u001d\u000e\u0003\u0019!u.\u001e2mKF\"A%M\u001b\u000f\u0011\u0015a$\u0001q\u0001>\u0003\u0005\u0019\u0007c\u0001 B;5\tqH\u0003\u0002A\u001b\u00059!/\u001a4mK\u000e$\u0018B\u0001\"@\u0005!\u0019E.Y:t)\u0006<\u0007\"\u0002#\u0003\u0001\b)\u0015!\u00013\u0011\u0005i1\u0015BA$\u0006\u0005\u0019QU\r\u001e#j[\")\u0011J\u0001a\u0002\u0015\u0006\ta\rE\u0002L)vq!\u0001T)\u000f\u00055{eB\u0001\u001aO\u0013\u0005A\u0011B\u0001)\b\u0003\u001d\tGnZ3ce\u0006L!AU*\u0002\u000fA\f7m[1hK*\u0011\u0001kB\u0005\u0003+Z\u0013QAR5fY\u0012T!AU*\t\u000ba\u0013\u00019A-\u0002\u00039\u00042AW.\u001e\u001b\u0005\u0019\u0016B\u0001/T\u0005\u0015q%k\\8u\u0011\u0015q&\u0001q\u0001`\u0003\u0005y\u0007cA&a;%\u0011\u0011M\u0016\u0002\u0006\u001fJ$WM\u001d\u0005\u0006G\n\u0001\u001d\u0001Z\u0001\u0002gB\u00191*Z\u000f\n\u0005\u00194&AB*jO:,G\rC\u0003i\u0005\u0001\u000f\u0011.A\u0001u!\rQ&.H\u0005\u0003WN\u0013A\u0001\u0016:jO\u0006)!*\u001a;FcV\u0011aN\u001e\u000b\u0003_^\u00042a\u00139s\u0013\t\thK\u0001\u0002FcB\u0019!d];\n\u0005Q,!a\u0001&fiB\u0011aD\u001e\u0003\u0006A\r\u0011\r!\t\u0005\bq\u000e\t\t\u0011q\u0001z\u0003))g/\u001b3f]\u000e,G%\r\t\u0004\u0017B,\b"
)
public interface JetInstances {
   // $FF: synthetic method
   static JetAlgebra JetAlgebra$(final JetInstances $this, final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return $this.JetAlgebra(c, d, f, n, o, s, t);
   }

   default JetAlgebra JetAlgebra(final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return new JetAlgebra(c, d, o, f, n, o, t, s, spire.std.package.array$.MODULE$.ArrayVectorSpace(NotGiven$.MODULE$.noImplicit0(), c, f));
   }

   // $FF: synthetic method
   static Eq JetEq$(final JetInstances $this, final Eq evidence$1) {
      return $this.JetEq(evidence$1);
   }

   default Eq JetEq(final Eq evidence$1) {
      return new JetEq(evidence$1);
   }

   // $FF: synthetic method
   static JetAlgebra JetAlgebra$mDc$sp$(final JetInstances $this, final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return $this.JetAlgebra$mDc$sp(c, d, f, n, o, s, t);
   }

   default JetAlgebra JetAlgebra$mDc$sp(final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return new JetAlgebra$mcD$sp(c, d, o, f, n, o, t, s, spire.std.package.array$.MODULE$.ArrayVectorSpace$mDc$sp(NotGiven$.MODULE$.noImplicit0(), c, f));
   }

   // $FF: synthetic method
   static JetAlgebra JetAlgebra$mFc$sp$(final JetInstances $this, final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return $this.JetAlgebra$mFc$sp(c, d, f, n, o, s, t);
   }

   default JetAlgebra JetAlgebra$mFc$sp(final ClassTag c, final JetDim d, final Field f, final NRoot n, final Order o, final Signed s, final Trig t) {
      return new JetAlgebra$mcF$sp(c, d, o, f, n, o, t, s, spire.std.package.array$.MODULE$.ArrayVectorSpace$mFc$sp(NotGiven$.MODULE$.noImplicit0(), c, f));
   }

   static void $init$(final JetInstances $this) {
   }
}
