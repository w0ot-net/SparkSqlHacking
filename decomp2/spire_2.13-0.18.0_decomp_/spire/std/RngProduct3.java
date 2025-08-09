package spire.std;

import algebra.ring.Rng;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0003\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u00021\u0019A\u0011\u0005\u0006\t\u00021\u0019!\u0012\u0005\u0006\u000f\u00021\u0019\u0001\u0013\u0005\u0006\u0015\u0002!\ta\u0013\u0002\f%:<\u0007K]8ek\u000e$8G\u0003\u0002\t\u0013\u0005\u00191\u000f\u001e3\u000b\u0003)\tQa\u001d9je\u0016,B\u0001D\u00154mM!\u0001!D\n9!\tq\u0011#D\u0001\u0010\u0015\u0005\u0001\u0012!B:dC2\f\u0017B\u0001\n\u0010\u0005\u0019\te.\u001f*fMB\u0019A#\t\u0013\u000f\u0005UqbB\u0001\f\u001d\u001d\t92$D\u0001\u0019\u0015\tI\"$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Q\u0011BA\u000f\n\u0003\u001d\tGnZ3ce\u0006L!a\b\u0011\u0002\u000fA\f7m[1hK*\u0011Q$C\u0005\u0003E\r\u00121A\u00158h\u0015\ty\u0002\u0005E\u0003\u000fK\u001d\u0012T'\u0003\u0002'\u001f\t1A+\u001e9mKN\u0002\"\u0001K\u0015\r\u0001\u0011)!\u0006\u0001b\u0001W\t\t\u0011)\u0005\u0002-_A\u0011a\"L\u0005\u0003]=\u0011qAT8uQ&tw\r\u0005\u0002\u000fa%\u0011\u0011g\u0004\u0002\u0004\u0003:L\bC\u0001\u00154\t\u0015!\u0004A1\u0001,\u0005\u0005\u0011\u0005C\u0001\u00157\t\u00159\u0004A1\u0001,\u0005\u0005\u0019\u0005#B\u001d;OI*T\"A\u0004\n\u0005m:!\u0001E*f[&\u0014\u0018N\\4Qe>$Wo\u0019;4\u0003\u0019!\u0013N\\5uIQ\ta\b\u0005\u0002\u000f\u007f%\u0011\u0001i\u0004\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012a\u0011\t\u0004)\u0005:\u0013AC:ueV\u001cG/\u001e:feU\ta\tE\u0002\u0015CI\n!b\u001d;sk\u000e$XO]34+\u0005I\u0005c\u0001\u000b\"k\u00051a.Z4bi\u0016$\"\u0001\n'\t\u000b5+\u0001\u0019\u0001\u0013\u0002\u0005a\u0004\u0004"
)
public interface RngProduct3 extends Rng, SemiringProduct3 {
   Rng structure1();

   Rng structure2();

   Rng structure3();

   // $FF: synthetic method
   static Tuple3 negate$(final RngProduct3 $this, final Tuple3 x0) {
      return $this.negate(x0);
   }

   default Tuple3 negate(final Tuple3 x0) {
      return new Tuple3(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()));
   }

   static void $init$(final RngProduct3 $this) {
   }
}
