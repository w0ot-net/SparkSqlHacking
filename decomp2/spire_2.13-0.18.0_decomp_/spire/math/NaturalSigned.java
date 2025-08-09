package spire.math;

import algebra.ring.Signed;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0003\u0005\u0006Q\u0001!\t!\u000b\u0005\u0006[\u0001!\tA\f\u0005\u0006a\u0001!\t!\r\u0002\u000e\u001d\u0006$XO]1m'&<g.\u001a3\u000b\u0005\u00199\u0011\u0001B7bi\"T\u0011\u0001C\u0001\u0006gBL'/Z\n\u0005\u0001)\u0001B\u0003\u0005\u0002\f\u001d5\tABC\u0001\u000e\u0003\u0015\u00198-\u00197b\u0013\tyAB\u0001\u0004B]f\u0014VM\u001a\t\u0003#Ii\u0011!B\u0005\u0003'\u0015\u0011ABT1ukJ\fGn\u0014:eKJ\u00042!\u0006\u0012&\u001d\t1rD\u0004\u0002\u0018;9\u0011\u0001\u0004H\u0007\u00023)\u0011!dG\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t\u0001\"\u0003\u0002\u001f\u000f\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0011\"\u0003\u001d\u0001\u0018mY6bO\u0016T!AH\u0004\n\u0005\r\"#!F*jO:,G-\u00113eSRLg/Z\"N_:|\u0017\u000e\u001a\u0006\u0003A\u0005\u0002\"!\u0005\u0014\n\u0005\u001d*!a\u0002(biV\u0014\u0018\r\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003)\u0002\"aC\u0016\n\u00051b!\u0001B+oSR\fQa\u001c:eKJ,\u0012a\f\t\u0003#\u0001\t1!\u00192t)\t)#\u0007C\u00034\u0007\u0001\u0007Q%A\u0001y\u0001"
)
public interface NaturalSigned extends NaturalOrder, Signed.forAdditiveCommutativeMonoid {
   // $FF: synthetic method
   static NaturalSigned order$(final NaturalSigned $this) {
      return $this.order();
   }

   default NaturalSigned order() {
      return this;
   }

   // $FF: synthetic method
   static Natural abs$(final NaturalSigned $this, final Natural x) {
      return $this.abs(x);
   }

   default Natural abs(final Natural x) {
      return x;
   }

   static void $init$(final NaturalSigned $this) {
   }
}
