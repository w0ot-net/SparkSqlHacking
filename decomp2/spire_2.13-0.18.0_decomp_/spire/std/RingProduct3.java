package spire.std;

import algebra.ring.Ring;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!\u0002\u0004\u0005\u0006{\u0001!\tA\u0010\u0005\u0006\u0005\u00021\u0019a\u0011\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u0017\u0002!\t\u0005\u0014\u0005\u0006%\u0002!\ta\u0015\u0002\r%&tw\r\u0015:pIV\u001cGo\r\u0006\u0003\u0013)\t1a\u001d;e\u0015\u0005Y\u0011!B:qSJ,W\u0003B\u0007+i]\u001aB\u0001\u0001\b\u0015sA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u00042!\u0006\u0012&\u001d\t1rD\u0004\u0002\u0018;9\u0011\u0001\u0004H\u0007\u00023)\u0011!dG\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t1\"\u0003\u0002\u001f\u0015\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0011\"\u0003\u001d\u0001\u0018mY6bO\u0016T!A\b\u0006\n\u0005\r\"#\u0001\u0002*j]\u001eT!\u0001I\u0011\u0011\u000b=1\u0003f\r\u001c\n\u0005\u001d\u0002\"A\u0002+va2,7\u0007\u0005\u0002*U1\u0001A!B\u0016\u0001\u0005\u0004a#!A!\u0012\u00055\u0002\u0004CA\b/\u0013\ty\u0003CA\u0004O_RD\u0017N\\4\u0011\u0005=\t\u0014B\u0001\u001a\u0011\u0005\r\te.\u001f\t\u0003SQ\"Q!\u000e\u0001C\u00021\u0012\u0011A\u0011\t\u0003S]\"Q\u0001\u000f\u0001C\u00021\u0012\u0011a\u0011\t\u0006umB3GN\u0007\u0002\u0011%\u0011A\b\u0003\u0002\f%:<\u0007K]8ek\u000e$8'\u0001\u0004%S:LG\u000f\n\u000b\u0002\u007fA\u0011q\u0002Q\u0005\u0003\u0003B\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003\u0011\u00032!\u0006\u0012)\u0003)\u0019HO];diV\u0014XMM\u000b\u0002\u000fB\u0019QCI\u001a\u0002\u0015M$(/^2ukJ,7'F\u0001K!\r)\"EN\u0001\bMJ|W.\u00138u)\t)S\nC\u0003O\u000b\u0001\u0007q*\u0001\u0002yaA\u0011q\u0002U\u0005\u0003#B\u00111!\u00138u\u0003\ryg.Z\u000b\u0002K\u0001"
)
public interface RingProduct3 extends Ring, RngProduct3 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   // $FF: synthetic method
   static Tuple3 fromInt$(final RingProduct3 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple3 fromInt(final int x0) {
      return new Tuple3(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple3 one$(final RingProduct3 $this) {
      return $this.one();
   }

   default Tuple3 one() {
      return new Tuple3(this.structure1().one(), this.structure2().one(), this.structure3().one());
   }

   static void $init$(final RingProduct3 $this) {
   }
}
