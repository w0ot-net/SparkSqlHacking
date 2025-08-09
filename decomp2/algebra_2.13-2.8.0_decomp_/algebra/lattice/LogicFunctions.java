package algebra.lattice;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\u0005!\u0004C\u0003K\u0001\u0011\u00051\nC\u0003[\u0001\u0011\u00051\fC\u0003j\u0001\u0011\u0005!N\u0001\bM_\u001eL7MR;oGRLwN\\:\u000b\u0005!I\u0011a\u00027biRL7-\u001a\u0006\u0002\u0015\u00059\u0011\r\\4fEJ\f7\u0001A\u000b\u0003\u001by\u001a\"\u0001\u0001\b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\ta\u0003\u0005\u0002\u0010/%\u0011\u0001\u0004\u0005\u0002\u0005+:LG/\u0001\u0006d_6\u0004H.Z7f]R,\"aG\u0010\u0015\u0005qAECA\u000f<!\tqr\u0004\u0004\u0001\u0005\u0013\u0001\u0012\u0001\u0015!A\u0001\u0006\u0004\t#!A!\u0012\u0005\t*\u0003CA\b$\u0013\t!\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=1\u0013BA\u0014\u0011\u0005\r\te.\u001f\u0015\u0005?%bc\u0007\u0005\u0002\u0010U%\u00111\u0006\u0005\u0002\fgB,7-[1mSj,G-M\u0003$[9\u0002tF\u0004\u0002\u0010]%\u0011q\u0006E\u0001\u0004\u0013:$\u0018\u0007\u0002\u00132kEq!AM\u001b\u000e\u0003MR!\u0001N\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012'B\u00128qiJdBA\b9\u0013\tI\u0004#\u0001\u0003M_:<\u0017\u0007\u0002\u00132kEAQ\u0001\u0010\u0002A\u0004u\n!!\u001a<\u0011\u0007yqT\u0004B\u0003@\u0001\t\u0007\u0001IA\u0001I+\t\tu)\u0005\u0002#\u0005B\u00191\t\u0012$\u000e\u0003\u001dI!!R\u0004\u0003\u000b1{w-[2\u0011\u0005y9E!\u0002\u0011?\u0005\u0004\t\u0003\"B%\u0003\u0001\u0004i\u0012!\u0001=\u0002\u00079|'/\u0006\u0002M\u001fR\u0019Qj\u0016-\u0015\u00059+\u0006C\u0001\u0010P\t%\u00013\u0001)A\u0001\u0002\u000b\u0007\u0011\u0005\u000b\u0003PSE\u001b\u0016'B\u0012.]I{\u0013\u0007\u0002\u00132kE\tTaI\u001c9)f\nD\u0001J\u00196#!)Ah\u0001a\u0002-B\u0019aD\u0010(\t\u000b%\u001b\u0001\u0019\u0001(\t\u000be\u001b\u0001\u0019\u0001(\u0002\u0003e\fAA\u001c=peV\u0011Al\u0018\u000b\u0004;\u001eDGC\u00010f!\tqr\fB\u0005!\t\u0001\u0006\t\u0011!b\u0001C!\"q,K1dc\u0015\u0019SF\f20c\u0011!\u0013'N\t2\u000b\r:\u0004\bZ\u001d2\t\u0011\nT'\u0005\u0005\u0006y\u0011\u0001\u001dA\u001a\t\u0004=yr\u0006\"B%\u0005\u0001\u0004q\u0006\"B-\u0005\u0001\u0004q\u0016\u0001\u00028b]\u0012,\"a\u001b8\u0015\u000714x\u000f\u0006\u0002niB\u0011aD\u001c\u0003\nA\u0015\u0001\u000b\u0011!AC\u0002\u0005BCA\\\u0015qeF*1%\f\u0018r_E\"A%M\u001b\u0012c\u0015\u0019s\u0007O::c\u0011!\u0013'N\t\t\u000bq*\u00019A;\u0011\u0007yqT\u000eC\u0003J\u000b\u0001\u0007Q\u000eC\u0003Z\u000b\u0001\u0007Q\u000e"
)
public interface LogicFunctions {
   // $FF: synthetic method
   static Object complement$(final LogicFunctions $this, final Object x, final Logic ev) {
      return $this.complement(x, ev);
   }

   default Object complement(final Object x, final Logic ev) {
      return ev.not(x);
   }

   // $FF: synthetic method
   static Object nor$(final LogicFunctions $this, final Object x, final Object y, final Logic ev) {
      return $this.nor(x, y, ev);
   }

   default Object nor(final Object x, final Object y, final Logic ev) {
      return ev.nor(x, y);
   }

   // $FF: synthetic method
   static Object nxor$(final LogicFunctions $this, final Object x, final Object y, final Logic ev) {
      return $this.nxor(x, y, ev);
   }

   default Object nxor(final Object x, final Object y, final Logic ev) {
      return ev.nxor(x, y);
   }

   // $FF: synthetic method
   static Object nand$(final LogicFunctions $this, final Object x, final Object y, final Logic ev) {
      return $this.nand(x, y, ev);
   }

   default Object nand(final Object x, final Object y, final Logic ev) {
      return ev.nand(x, y);
   }

   // $FF: synthetic method
   static int complement$mIc$sp$(final LogicFunctions $this, final int x, final Logic ev) {
      return $this.complement$mIc$sp(x, ev);
   }

   default int complement$mIc$sp(final int x, final Logic ev) {
      return ev.not$mcI$sp(x);
   }

   // $FF: synthetic method
   static long complement$mJc$sp$(final LogicFunctions $this, final long x, final Logic ev) {
      return $this.complement$mJc$sp(x, ev);
   }

   default long complement$mJc$sp(final long x, final Logic ev) {
      return ev.not$mcJ$sp(x);
   }

   // $FF: synthetic method
   static int nor$mIc$sp$(final LogicFunctions $this, final int x, final int y, final Logic ev) {
      return $this.nor$mIc$sp(x, y, ev);
   }

   default int nor$mIc$sp(final int x, final int y, final Logic ev) {
      return ev.nor$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long nor$mJc$sp$(final LogicFunctions $this, final long x, final long y, final Logic ev) {
      return $this.nor$mJc$sp(x, y, ev);
   }

   default long nor$mJc$sp(final long x, final long y, final Logic ev) {
      return ev.nor$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static int nxor$mIc$sp$(final LogicFunctions $this, final int x, final int y, final Logic ev) {
      return $this.nxor$mIc$sp(x, y, ev);
   }

   default int nxor$mIc$sp(final int x, final int y, final Logic ev) {
      return ev.nxor$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long nxor$mJc$sp$(final LogicFunctions $this, final long x, final long y, final Logic ev) {
      return $this.nxor$mJc$sp(x, y, ev);
   }

   default long nxor$mJc$sp(final long x, final long y, final Logic ev) {
      return ev.nxor$mcJ$sp(x, y);
   }

   // $FF: synthetic method
   static int nand$mIc$sp$(final LogicFunctions $this, final int x, final int y, final Logic ev) {
      return $this.nand$mIc$sp(x, y, ev);
   }

   default int nand$mIc$sp(final int x, final int y, final Logic ev) {
      return ev.nand$mcI$sp(x, y);
   }

   // $FF: synthetic method
   static long nand$mJc$sp$(final LogicFunctions $this, final long x, final long y, final Logic ev) {
      return $this.nand$mJc$sp(x, y, ev);
   }

   default long nand$mJc$sp(final long x, final long y, final Logic ev) {
      return ev.nand$mcJ$sp(x, y);
   }

   static void $init$(final LogicFunctions $this) {
   }
}
