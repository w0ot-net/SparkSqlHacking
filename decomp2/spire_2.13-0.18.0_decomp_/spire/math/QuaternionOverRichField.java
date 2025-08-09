package spire.math;

import scala.reflect.ScalaSignature;
import spire.algebra.InnerProductSpace;
import spire.algebra.NRoot;
import spire.algebra.Trig;

@ScalaSignature(
   bytes = "\u0006\u000593\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001\u0002\u0004\u0005\u0006a\u0001!\t!\r\u0005\u0006k\u00011\u0019A\u000e\u0005\u0006q\u00011\u0019!\u000f\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\r\u0002!\te\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0002\u0018#V\fG/\u001a:oS>twJ^3s%&\u001c\u0007NR5fY\u0012T!!\u0003\u0006\u0002\t5\fG\u000f\u001b\u0006\u0002\u0017\u0005)1\u000f]5sKV\u0011QBG\n\u0006\u00019!B%\f\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007U1\u0002$D\u0001\t\u0013\t9\u0002BA\nRk\u0006$XM\u001d8j_:|e/\u001a:GS\u0016dG\r\u0005\u0002\u001a51\u0001A!B\u000e\u0001\u0005\u0004i\"!A!\u0004\u0001E\u0011a$\t\t\u0003\u001f}I!\u0001\t\t\u0003\u000f9{G\u000f[5oOB\u0011qBI\u0005\u0003GA\u00111!\u00118z!\r)\u0003FK\u0007\u0002M)\u0011qEC\u0001\bC2<WM\u0019:b\u0013\tIcEA\u0003O%>|G\u000fE\u0002\u0016WaI!\u0001\f\u0005\u0003\u0015E+\u0018\r^3s]&|g\u000e\u0005\u0003&])B\u0012BA\u0018'\u0005EIeN\\3s!J|G-^2u'B\f7-Z\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003I\u0002\"aD\u001a\n\u0005Q\u0002\"\u0001B+oSR\f\u0011A\\\u000b\u0002oA\u0019Q\u0005\u000b\r\u0002\u0003Q,\u0012A\u000f\t\u0004KmB\u0012B\u0001\u001f'\u0005\u0011!&/[4\u0002\u000b9\u0014xn\u001c;\u0015\u0007)z\u0014\tC\u0003A\t\u0001\u0007!&A\u0001b\u0011\u0015\u0011E\u00011\u0001D\u0003\u0005Y\u0007CA\bE\u0013\t)\u0005CA\u0002J]R\fAa]9siR\u0011!\u0006\u0013\u0005\u0006\u0001\u0016\u0001\rAK\u0001\u0005MB|w\u000fF\u0002+\u00172CQ\u0001\u0011\u0004A\u0002)BQ!\u0014\u0004A\u0002)\n\u0011A\u0019"
)
public interface QuaternionOverRichField extends QuaternionOverField, NRoot, InnerProductSpace {
   NRoot n();

   Trig t();

   // $FF: synthetic method
   static Quaternion nroot$(final QuaternionOverRichField $this, final Quaternion a, final int k) {
      return $this.nroot(a, k);
   }

   default Quaternion nroot(final Quaternion a, final int k) {
      return a.nroot(k, this.scalar(), this.n(), this.o(), this.s(), this.t());
   }

   // $FF: synthetic method
   static Quaternion sqrt$(final QuaternionOverRichField $this, final Quaternion a) {
      return $this.sqrt(a);
   }

   default Quaternion sqrt(final Quaternion a) {
      return a.sqrt(this.scalar(), this.n(), this.s());
   }

   // $FF: synthetic method
   static Quaternion fpow$(final QuaternionOverRichField $this, final Quaternion a, final Quaternion b) {
      return $this.fpow(a, b);
   }

   default Quaternion fpow(final Quaternion a, final Quaternion b) {
      return a.fpow(b.r(), this.scalar(), this.n(), this.o(), this.s(), this.t());
   }

   static void $init$(final QuaternionOverRichField $this) {
   }
}
