package spire.std;

import algebra.ring.Ring;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%4\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0004\u0005\u0006\u0013\u0002!\tA\u0013\u0005\u0006\u001d\u00021\u0019a\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u0002!\t%\u0019\u0005\u0006O\u0002!\t\u0001\u001b\u0002\r%&tw\r\u0015:pIV\u001cGO\u000e\u0006\u0003\u00195\t1a\u001d;e\u0015\u0005q\u0011!B:qSJ,Wc\u0002\t.oij\u0004iQ\n\u0005\u0001E9R\t\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VM\u001a\t\u00041\u0015BcBA\r#\u001d\tQ\u0002E\u0004\u0002\u001c?5\tAD\u0003\u0002\u001e=\u00051AH]8piz\u001a\u0001!C\u0001\u000f\u0013\t\tS\"A\u0004bY\u001e,'M]1\n\u0005\r\"\u0013a\u00029bG.\fw-\u001a\u0006\u0003C5I!AJ\u0014\u0003\tIKgn\u001a\u0006\u0003G\u0011\u0002\u0002BE\u0015,mebtHQ\u0005\u0003UM\u0011a\u0001V;qY\u00164\u0004C\u0001\u0017.\u0019\u0001!QA\f\u0001C\u0002=\u0012\u0011!Q\t\u0003aM\u0002\"AE\u0019\n\u0005I\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003%QJ!!N\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002-o\u0011)\u0001\b\u0001b\u0001_\t\t!\t\u0005\u0002-u\u0011)1\b\u0001b\u0001_\t\t1\t\u0005\u0002-{\u0011)a\b\u0001b\u0001_\t\tA\t\u0005\u0002-\u0001\u0012)\u0011\t\u0001b\u0001_\t\tQ\t\u0005\u0002-\u0007\u0012)A\t\u0001b\u0001_\t\ta\t\u0005\u0005G\u000f.2\u0014\bP C\u001b\u0005Y\u0011B\u0001%\f\u0005-\u0011fn\u001a)s_\u0012,8\r\u001e\u001c\u0002\r\u0011Jg.\u001b;%)\u0005Y\u0005C\u0001\nM\u0013\ti5C\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t\u0001\u000bE\u0002\u0019K-\n!b\u001d;sk\u000e$XO]33+\u0005\u0019\u0006c\u0001\r&m\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003Y\u00032\u0001G\u0013:\u0003)\u0019HO];diV\u0014X\rN\u000b\u00023B\u0019\u0001$\n\u001f\u0002\u0015M$(/^2ukJ,W'F\u0001]!\rAReP\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#A0\u0011\u0007a)#)A\u0004ge>l\u0017J\u001c;\u0015\u0005!\u0012\u0007\"B2\t\u0001\u0004!\u0017A\u0001=1!\t\u0011R-\u0003\u0002g'\t\u0019\u0011J\u001c;\u0002\u0007=tW-F\u0001)\u0001"
)
public interface RingProduct6 extends Ring, RngProduct6 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   Ring structure5();

   Ring structure6();

   // $FF: synthetic method
   static Tuple6 fromInt$(final RingProduct6 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple6 fromInt(final int x0) {
      return new Tuple6(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0), this.structure5().fromInt(x0), this.structure6().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple6 one$(final RingProduct6 $this) {
      return $this.one();
   }

   default Tuple6 one() {
      return new Tuple6(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one());
   }

   static void $init$(final RingProduct6 $this) {
   }
}
