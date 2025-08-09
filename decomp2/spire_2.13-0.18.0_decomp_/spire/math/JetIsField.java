package spire.math;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0013\u0002!\tE\u0013\u0005\u0006!\u0002!\t!\u0015\u0002\u000b\u0015\u0016$\u0018j\u001d$jK2$'B\u0001\u0004\b\u0003\u0011i\u0017\r\u001e5\u000b\u0003!\tQa\u001d9je\u0016,\"AC\f\u0014\t\u0001Y\u0011\u0003\u000e\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0007I\u0019R#D\u0001\u0006\u0013\t!RA\u0001\nKKRL5/R;dY&$W-\u00198SS:<\u0007C\u0001\f\u0018\u0019\u0001!\u0011\u0002\u0007\u0001!\u0002\u0003\u0005)\u0019\u0001\u000e\u0003\u0003Q\u001b\u0001!\u0005\u0002\u001c=A\u0011A\u0002H\u0005\u0003;5\u0011qAT8uQ&tw\r\u0005\u0002\r?%\u0011\u0001%\u0004\u0002\u0004\u0003:L\b\u0006B\f#K=\u0002\"\u0001D\u0012\n\u0005\u0011j!aC:qK\u000eL\u0017\r\\5{K\u0012\fTa\t\u0014(S!r!\u0001D\u0014\n\u0005!j\u0011!\u0002$m_\u0006$\u0018\u0007\u0002\u0013+]9q!a\u000b\u0018\u000e\u00031R!!L\r\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0011'B\u00121cM\u0012dB\u0001\u00072\u0013\t\u0011T\"\u0001\u0004E_V\u0014G.Z\u0019\u0005I)rc\u0002E\u00026}\u0005s!AN\u001e\u000f\u0005]JdBA\u00169\u0013\u0005A\u0011B\u0001\u001e\b\u0003\u001d\tGnZ3ce\u0006L!\u0001P\u001f\u0002\u000fA\f7m[1hK*\u0011!hB\u0005\u0003\u007f\u0001\u0013QAR5fY\u0012T!\u0001P\u001f\u0011\u0007I\u0011U#\u0003\u0002D\u000b\t\u0019!*\u001a;\u0002\r\u0011Jg.\u001b;%)\u00051\u0005C\u0001\u0007H\u0013\tAUB\u0001\u0003V]&$\u0018A\u00034s_6$u.\u001e2mKR\u0011\u0011i\u0013\u0005\u0006\u0019\n\u0001\r!T\u0001\u0002]B\u0011ABT\u0005\u0003\u001f6\u0011a\u0001R8vE2,\u0017a\u00013jmR\u0019\u0011I\u0015+\t\u000bM\u001b\u0001\u0019A!\u0002\u0003\u0005DQ!V\u0002A\u0002\u0005\u000b\u0011A\u0019"
)
public interface JetIsField extends JetIsEuclideanRing, Field {
   // $FF: synthetic method
   static Jet fromDouble$(final JetIsField $this, final double n) {
      return $this.fromDouble(n);
   }

   default Jet fromDouble(final double n) {
      return Jet$.MODULE$.apply(this.f().fromDouble(n), this.c(), this.d(), this.f());
   }

   // $FF: synthetic method
   static Jet div$(final JetIsField $this, final Jet a, final Jet b) {
      return $this.div(a, b);
   }

   default Jet div(final Jet a, final Jet b) {
      return a.$div(b, this.f(), this.v());
   }

   // $FF: synthetic method
   static Jet fromDouble$mcD$sp$(final JetIsField $this, final double n) {
      return $this.fromDouble$mcD$sp(n);
   }

   default Jet fromDouble$mcD$sp(final double n) {
      return this.fromDouble(n);
   }

   // $FF: synthetic method
   static Jet fromDouble$mcF$sp$(final JetIsField $this, final double n) {
      return $this.fromDouble$mcF$sp(n);
   }

   default Jet fromDouble$mcF$sp(final double n) {
      return this.fromDouble(n);
   }

   // $FF: synthetic method
   static Jet div$mcD$sp$(final JetIsField $this, final Jet a, final Jet b) {
      return $this.div$mcD$sp(a, b);
   }

   default Jet div$mcD$sp(final Jet a, final Jet b) {
      return this.div(a, b);
   }

   // $FF: synthetic method
   static Jet div$mcF$sp$(final JetIsField $this, final Jet a, final Jet b) {
      return $this.div$mcF$sp(a, b);
   }

   default Jet div$mcF$sp(final Jet a, final Jet b) {
      return this.div(a, b);
   }

   static void $init$(final JetIsField $this) {
   }
}
