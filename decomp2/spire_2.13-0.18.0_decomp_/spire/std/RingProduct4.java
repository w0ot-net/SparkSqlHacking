package spire.std;

import algebra.ring.Ring;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m3\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0004\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006\r\u00021\u0019a\u0012\u0005\u0006\u0013\u00021\u0019A\u0013\u0005\u0006\u0019\u00021\u0019!\u0014\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006%\u0002!\te\u0015\u0005\u00063\u0002!\tA\u0017\u0002\r%&tw\r\u0015:pIV\u001cG\u000f\u000e\u0006\u0003\u0015-\t1a\u001d;e\u0015\u0005a\u0011!B:qSJ,W#\u0002\b,kaZ4\u0003\u0002\u0001\u0010+u\u0002\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f$M9\u0011q\u0003\t\b\u00031yq!!G\u000f\u000e\u0003iQ!a\u0007\u000f\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001D\u0005\u0003?-\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\"E\u00059\u0001/Y2lC\u001e,'BA\u0010\f\u0013\t!SE\u0001\u0003SS:<'BA\u0011#!\u0019\u0001r%\u000b\u001b8u%\u0011\u0001&\u0005\u0002\u0007)V\u0004H.\u001a\u001b\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\u0001\u0011\r!\f\u0002\u0002\u0003F\u0011a&\r\t\u0003!=J!\u0001M\t\u0003\u000f9{G\u000f[5oOB\u0011\u0001CM\u0005\u0003gE\u00111!\u00118z!\tQS\u0007B\u00037\u0001\t\u0007QFA\u0001C!\tQ\u0003\bB\u0003:\u0001\t\u0007QFA\u0001D!\tQ3\bB\u0003=\u0001\t\u0007QFA\u0001E!\u0019qt(\u000b\u001b8u5\t\u0011\"\u0003\u0002A\u0013\tY!K\\4Qe>$Wo\u0019;5\u0003\u0019!\u0013N\\5uIQ\t1\t\u0005\u0002\u0011\t&\u0011Q)\u0005\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012\u0001\u0013\t\u0004-\rJ\u0013AC:ueV\u001cG/\u001e:feU\t1\nE\u0002\u0017GQ\n!b\u001d;sk\u000e$XO]34+\u0005q\u0005c\u0001\f$o\u0005Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0003E\u00032AF\u0012;\u0003\u001d1'o\\7J]R$\"A\n+\t\u000bU3\u0001\u0019\u0001,\u0002\u0005a\u0004\u0004C\u0001\tX\u0013\tA\u0016CA\u0002J]R\f1a\u001c8f+\u00051\u0003"
)
public interface RingProduct4 extends Ring, RngProduct4 {
   Ring structure1();

   Ring structure2();

   Ring structure3();

   Ring structure4();

   // $FF: synthetic method
   static Tuple4 fromInt$(final RingProduct4 $this, final int x0) {
      return $this.fromInt(x0);
   }

   default Tuple4 fromInt(final int x0) {
      return new Tuple4(this.structure1().fromInt(x0), this.structure2().fromInt(x0), this.structure3().fromInt(x0), this.structure4().fromInt(x0));
   }

   // $FF: synthetic method
   static Tuple4 one$(final RingProduct4 $this) {
      return $this.one();
   }

   default Tuple4 one() {
      return new Tuple4(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one());
   }

   static void $init$(final RingProduct4 $this) {
   }
}
