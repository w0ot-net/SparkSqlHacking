package spire.std;

import algebra.ring.Field;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.NormedVectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005y3qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\bBeJ\f\u00170\u00138ti\u0006t7-Z:4\u0015\t)a!A\u0002ti\u0012T\u0011aB\u0001\u0006gBL'/Z\u0002\u0001'\r\u0001!\u0002\u0005\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E\u0011R\"\u0001\u0003\n\u0005M!!aD!se\u0006L\u0018J\\:uC:\u001cWm\u001d\u001a\u0002\r\u0011Jg.\u001b;%)\u00051\u0002CA\u0006\u0018\u0013\tABB\u0001\u0003V]&$\u0018AF!se\u0006Lhj\u001c:nK\u00124Vm\u0019;peN\u0003\u0018mY3\u0016\u0005m9C\u0003\u0002\u000fD!V\u0003B!\b\u0011#K5\taD\u0003\u0002 \r\u00059\u0011\r\\4fEJ\f\u0017BA\u0011\u001f\u0005EquN]7fIZ+7\r^8s'B\f7-\u001a\t\u0004\u0017\r*\u0013B\u0001\u0013\r\u0005\u0015\t%O]1z!\t1s\u0005\u0004\u0001\u0005\u0013!\u0012\u0001\u0015!A\u0001\u0006\u0004I#!A!\u0012\u0005)j\u0003CA\u0006,\u0013\taCBA\u0004O_RD\u0017N\\4\u0011\u0005-q\u0013BA\u0018\r\u0005\r\te.\u001f\u0015\u0005OE\"d\b\u0005\u0002\fe%\u00111\u0007\u0004\u0002\fgB,7-[1mSj,G-M\u0003$kYBtG\u0004\u0002\fm%\u0011q\u0007D\u0001\u0006\r2|\u0017\r^\u0019\u0005IejTB\u0004\u0002;{5\t1H\u0003\u0002=\u0011\u00051AH]8pizJ\u0011!D\u0019\u0006G}\u0002%)\u0011\b\u0003\u0017\u0001K!!\u0011\u0007\u0002\r\u0011{WO\u00197fc\u0011!\u0013(P\u0007\t\u000f\u0011\u0013\u0011\u0011!a\u0002\u000b\u0006YQM^5eK:\u001cW\r\n\u001a3!\r1U*\n\b\u0003\u000f.s!\u0001\u0013&\u000f\u0005iJ\u0015\"A\u0004\n\u0005}1\u0011B\u0001'\u001f\u0003\u001d\u0001\u0018mY6bO\u0016L!AT(\u0003\u000b\u0019KW\r\u001c3\u000b\u00051s\u0002bB)\u0003\u0003\u0003\u0005\u001dAU\u0001\fKZLG-\u001a8dK\u0012\u00124\u0007E\u0002\u001e'\u0016J!\u0001\u0016\u0010\u0003\u000b9\u0013vn\u001c;\t\u000fY\u0013\u0011\u0011!a\u0002/\u0006YQM^5eK:\u001cW\r\n\u001a5!\rA6,\n\b\u00033jk\u0011AB\u0005\u0003\u0019\u001aI!\u0001X/\u0003\u0011\rc\u0017m]:UC\u001eT!\u0001\u0014\u0004"
)
public interface ArrayInstances3 extends ArrayInstances2 {
   // $FF: synthetic method
   static NormedVectorSpace ArrayNormedVectorSpace$(final ArrayInstances3 $this, final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return $this.ArrayNormedVectorSpace(evidence$22, evidence$23, evidence$24);
   }

   default NormedVectorSpace ArrayNormedVectorSpace(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return this.ArrayInnerProductSpace(evidence$22, evidence$24).normed(evidence$23);
   }

   // $FF: synthetic method
   static NormedVectorSpace ArrayNormedVectorSpace$mDc$sp$(final ArrayInstances3 $this, final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return $this.ArrayNormedVectorSpace$mDc$sp(evidence$22, evidence$23, evidence$24);
   }

   default NormedVectorSpace ArrayNormedVectorSpace$mDc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return this.ArrayInnerProductSpace$mDc$sp(evidence$22, evidence$24).normed$mcD$sp(evidence$23);
   }

   // $FF: synthetic method
   static NormedVectorSpace ArrayNormedVectorSpace$mFc$sp$(final ArrayInstances3 $this, final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return $this.ArrayNormedVectorSpace$mFc$sp(evidence$22, evidence$23, evidence$24);
   }

   default NormedVectorSpace ArrayNormedVectorSpace$mFc$sp(final Field evidence$22, final NRoot evidence$23, final ClassTag evidence$24) {
      return this.ArrayInnerProductSpace$mFc$sp(evidence$22, evidence$24).normed$mcF$sp(evidence$23);
   }

   static void $init$(final ArrayInstances3 $this) {
   }
}
