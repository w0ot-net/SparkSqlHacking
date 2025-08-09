package spire.math;

import algebra.ring.Field;
import algebra.ring.Signed;
import cats.kernel.Order;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;
import spire.algebra.Trig;
import spire.algebra.VectorSpace;

@ScalaSignature(
   bytes = "\u0006\u0005m4\u0001\"\u0004\b\u0011\u0002\u0007\u0005aB\u0005\u0005\u0006a\u0001!\t!\r\u0005\u0006k\u00011\u0019A\u000e\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006O\u0002!\t\u0001\u001b\u0005\u0006a\u0002!\t%\u001d\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006g\u0002!\t\u0001\u001f\u0002\u000b\u0015\u0016$\u0018j\u001d(S_>$(BA\b\u0011\u0003\u0011i\u0017\r\u001e5\u000b\u0003E\tQa\u001d9je\u0016,\"a\u0005\u0014\u0014\u0007\u0001!\"\u0004\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f\u0014VM\u001a\t\u00047y\u0001S\"\u0001\u000f\u000b\u0005u\u0001\u0012aB1mO\u0016\u0014'/Y\u0005\u0003?q\u0011QA\u0014*p_R\u00042!\t\u0012%\u001b\u0005q\u0011BA\u0012\u000f\u0005\rQU\r\u001e\t\u0003K\u0019b\u0001\u0001B\u0003(\u0001\t\u0007\u0011FA\u0001U\u0007\u0001\t\"AK\u0017\u0011\u0005UY\u0013B\u0001\u0017\u0017\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!\u0006\u0018\n\u0005=2\"aA!os\u00061A%\u001b8ji\u0012\"\u0012A\r\t\u0003+MJ!\u0001\u000e\f\u0003\tUs\u0017\u000e^\u0001\u0002MV\tq\u0007E\u00029\u0005\u0012r!!\u000f!\u000f\u0005izdBA\u001e?\u001b\u0005a$BA\u001f)\u0003\u0019a$o\\8u}%\t\u0011#\u0003\u0002\u001e!%\u0011\u0011\tH\u0001\ba\u0006\u001c7.Y4f\u0013\t\u0019EIA\u0003GS\u0016dGM\u0003\u0002B9\u0005\ta.F\u0001H!\rYb\u0004J\u0001\u0002_V\t!\nE\u00029\u0017\u0012J!\u0001\u0014#\u0003\u000b=\u0013H-\u001a:\u0002\u0003Q,\u0012a\u0014\t\u00047A#\u0013BA)\u001d\u0005\u0011!&/[4\u0002\u0003M,\u0012\u0001\u0016\t\u0004qU#\u0013B\u0001,E\u0005\u0019\u0019\u0016n\u001a8fI\u0006\t1-F\u0001Z!\rQV\fJ\u0007\u00027*\u0011ALF\u0001\be\u00164G.Z2u\u0013\tq6L\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u00051X#A1\u0011\tm\u0011G\rJ\u0005\u0003Gr\u00111BV3di>\u00148\u000b]1dKB\u0019Q#\u001a\u0013\n\u0005\u00194\"!B!se\u0006L\u0018!\u00028s_>$Hc\u0001\u0011jW\")!.\u0003a\u0001A\u0005\t\u0011\rC\u0003m\u0013\u0001\u0007Q.A\u0001l!\t)b.\u0003\u0002p-\t\u0019\u0011J\u001c;\u0002\tM\f(\u000f\u001e\u000b\u0003AIDQA\u001b\u0006A\u0002\u0001\nAA\u001a9poR\u0019\u0001%\u001e<\t\u000b)\\\u0001\u0019\u0001\u0011\t\u000b]\\\u0001\u0019\u0001\u0011\u0002\u0003\t$2\u0001I={\u0011\u0015QG\u00021\u0001%\u0011\u00159H\u00021\u0001!\u0001"
)
public interface JetIsNRoot extends NRoot {
   Field f();

   NRoot n();

   Order o();

   Trig t();

   Signed s();

   ClassTag c();

   VectorSpace v();

   // $FF: synthetic method
   static Jet nroot$(final JetIsNRoot $this, final Jet a, final int k) {
      return $this.nroot(a, k);
   }

   default Jet nroot(final Jet a, final int k) {
      return a.nroot(k, this.f(), this.o(), this.s(), this.t(), this.v());
   }

   // $FF: synthetic method
   static Jet sqrt$(final JetIsNRoot $this, final Jet a) {
      return $this.sqrt(a);
   }

   default Jet sqrt(final Jet a) {
      return a.sqrt(this.f(), this.n(), this.v());
   }

   // $FF: synthetic method
   static Jet fpow$(final JetIsNRoot $this, final Jet a, final Jet b) {
      return $this.fpow(a, b);
   }

   default Jet fpow(final Jet a, final Jet b) {
      return a.pow(b, this.c(), this.f(), this.v(), this.o(), this.s(), this.t());
   }

   // $FF: synthetic method
   static Jet fpow$(final JetIsNRoot $this, final Object a, final Jet b) {
      return $this.fpow(a, b);
   }

   default Jet fpow(final Object a, final Jet b) {
      return b.powScalarToJet(a, this.c(), this.f(), this.v(), this.o(), this.s(), this.t());
   }

   static void $init$(final JetIsNRoot $this) {
   }
}
