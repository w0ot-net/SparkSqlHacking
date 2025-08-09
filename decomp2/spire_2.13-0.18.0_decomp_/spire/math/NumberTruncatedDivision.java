package spire.math;

import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005I3\u0001BB\u0004\u0011\u0002\u0007\u0005qa\u0003\u0005\u0006U\u0001!\ta\u000b\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0017\u0002!\t\u0005\u0014\u0002\u0018\u001dVl'-\u001a:UeVt7-\u0019;fI\u0012Kg/[:j_:T!\u0001C\u0005\u0002\t5\fG\u000f\u001b\u0006\u0002\u0015\u0005)1\u000f]5sKN!\u0001\u0001\u0004\n(!\ti\u0001#D\u0001\u000f\u0015\u0005y\u0011!B:dC2\f\u0017BA\t\u000f\u0005\u0019\te.\u001f*fMB\u00191\u0003I\u0012\u000f\u0005QibBA\u000b\u001c\u001d\t1\"$D\u0001\u0018\u0015\tA\u0012$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Q\u0011B\u0001\u000f\n\u0003\u001d\tGnZ3ce\u0006L!AH\u0010\u0002\u000fA\f7m[1hK*\u0011A$C\u0005\u0003C\t\u0012a\u0003\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0007JKgn\u001a\u0006\u0003=}\u0001\"\u0001J\u0013\u000e\u0003\u001dI!AJ\u0004\u0003\r9+XNY3s!\t!\u0003&\u0003\u0002*\u000f\taa*^7cKJ\u001c\u0016n\u001a8fI\u00061A%\u001b8ji\u0012\"\u0012\u0001\f\t\u0003\u001b5J!A\f\b\u0003\tUs\u0017\u000e^\u0001\fi>\u0014\u0015nZ%oi>\u0003H\u000f\u0006\u00022\u007fA\u0019!'N\u001c\u000e\u0003MR!\u0001N\u0005\u0002\tU$\u0018\u000e\\\u0005\u0003mM\u00121a\u00149u!\tADH\u0004\u0002:w9\u0011aCO\u0005\u0002\u001f%\u0011aDD\u0005\u0003{y\u0012aAQ5h\u0013:$(B\u0001\u0010\u000f\u0011\u0015\u0001%\u00011\u0001$\u0003\u0005\t\u0017!\u0002;rk>$HcA\u0012D\u000b\")Ai\u0001a\u0001G\u0005\t\u0001\u0010C\u0003G\u0007\u0001\u00071%A\u0001z\u0003\u0011!Xn\u001c3\u0015\u0007\rJ%\nC\u0003E\t\u0001\u00071\u0005C\u0003G\t\u0001\u00071%\u0001\u0005ucV|G/\\8e)\ri\u0005+\u0015\t\u0005\u001b9\u001b3%\u0003\u0002P\u001d\t1A+\u001e9mKJBQ\u0001R\u0003A\u0002\rBQAR\u0003A\u0002\r\u0002"
)
public interface NumberTruncatedDivision extends TruncatedDivision.forCommutativeRing, NumberSigned {
   // $FF: synthetic method
   static BigInt toBigIntOpt$(final NumberTruncatedDivision $this, final Number a) {
      return $this.toBigIntOpt(a);
   }

   default BigInt toBigIntOpt(final Number a) {
      return a.isWhole() ? (BigInt).MODULE$.apply(a.toBigInt()) : (BigInt).MODULE$.empty();
   }

   // $FF: synthetic method
   static Number tquot$(final NumberTruncatedDivision $this, final Number x, final Number y) {
      return $this.tquot(x, y);
   }

   default Number tquot(final Number x, final Number y) {
      return x.tquot(y);
   }

   // $FF: synthetic method
   static Number tmod$(final NumberTruncatedDivision $this, final Number x, final Number y) {
      return $this.tmod(x, y);
   }

   default Number tmod(final Number x, final Number y) {
      return x.tmod(y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$(final NumberTruncatedDivision $this, final Number x, final Number y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final Number x, final Number y) {
      return x.tquotmod(y);
   }

   static void $init$(final NumberTruncatedDivision $this) {
   }
}
