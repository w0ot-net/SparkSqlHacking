package spire.std;

import algebra.ring.Rng;
import scala.Tuple19;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ud\u0001\u0003\f\u0018!\u0003\r\t!G\u000e\t\u000bq\u0004A\u0011A?\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u00011\u0019!a\u0015\t\u000f\u0005]\u0003Ab\u0001\u0002Z!9\u0011Q\f\u0001\u0007\u0004\u0005}\u0003bBA2\u0001\u0019\r\u0011Q\r\u0005\b\u0003S\u0002a1AA6\u0011\u001d\ty\u0007\u0001D\u0002\u0003cBq!!\u001e\u0001\t\u0003\t9H\u0001\u0007S]\u001e\u0004&o\u001c3vGR\f\u0014H\u0003\u0002\u00193\u0005\u00191\u000f\u001e3\u000b\u0003i\tQa\u001d9je\u0016,B\u0003H\u001dD\r&cuJU+Y7z\u000bGm\u001a6naN48\u0003\u0002\u0001\u001eGa\u0004\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011a!\u00118z%\u00164\u0007c\u0001\u00132i9\u0011QE\f\b\u0003M1r!aJ\u0016\u000e\u0003!R!!\u000b\u0016\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AG\u0005\u0003[e\tq!\u00197hK\n\u0014\u0018-\u0003\u00020a\u00059\u0001/Y2lC\u001e,'BA\u0017\u001a\u0013\t\u00114GA\u0002S]\u001eT!a\f\u0019\u0011+y)tGQ#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8sk&\u0011ag\b\u0002\b)V\u0004H.Z\u0019:!\tA\u0014\b\u0004\u0001\u0005\u000bi\u0002!\u0019A\u001e\u0003\u0003\u0005\u000b\"\u0001P \u0011\u0005yi\u0014B\u0001  \u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\b!\n\u0005\u0005{\"aA!osB\u0011\u0001h\u0011\u0003\u0006\t\u0002\u0011\ra\u000f\u0002\u0002\u0005B\u0011\u0001H\u0012\u0003\u0006\u000f\u0002\u0011\ra\u000f\u0002\u0002\u0007B\u0011\u0001(\u0013\u0003\u0006\u0015\u0002\u0011\ra\u000f\u0002\u0002\tB\u0011\u0001\b\u0014\u0003\u0006\u001b\u0002\u0011\ra\u000f\u0002\u0002\u000bB\u0011\u0001h\u0014\u0003\u0006!\u0002\u0011\ra\u000f\u0002\u0002\rB\u0011\u0001H\u0015\u0003\u0006'\u0002\u0011\ra\u000f\u0002\u0002\u000fB\u0011\u0001(\u0016\u0003\u0006-\u0002\u0011\ra\u000f\u0002\u0002\u0011B\u0011\u0001\b\u0017\u0003\u00063\u0002\u0011\ra\u000f\u0002\u0002\u0013B\u0011\u0001h\u0017\u0003\u00069\u0002\u0011\ra\u000f\u0002\u0002\u0015B\u0011\u0001H\u0018\u0003\u0006?\u0002\u0011\ra\u000f\u0002\u0002\u0017B\u0011\u0001(\u0019\u0003\u0006E\u0002\u0011\ra\u000f\u0002\u0002\u0019B\u0011\u0001\b\u001a\u0003\u0006K\u0002\u0011\ra\u000f\u0002\u0002\u001bB\u0011\u0001h\u001a\u0003\u0006Q\u0002\u0011\ra\u000f\u0002\u0002\u001dB\u0011\u0001H\u001b\u0003\u0006W\u0002\u0011\ra\u000f\u0002\u0002\u001fB\u0011\u0001(\u001c\u0003\u0006]\u0002\u0011\ra\u000f\u0002\u0002!B\u0011\u0001\b\u001d\u0003\u0006c\u0002\u0011\ra\u000f\u0002\u0002#B\u0011\u0001h\u001d\u0003\u0006i\u0002\u0011\ra\u000f\u0002\u0002%B\u0011\u0001H\u001e\u0003\u0006o\u0002\u0011\ra\u000f\u0002\u0002'B)\u0012P_\u001cC\u000b\"[e*\u0015+X5v\u00037MZ5m_J,X\"A\f\n\u0005m<\"!E*f[&\u0014\u0018N\\4Qe>$Wo\u0019;2s\u00051A%\u001b8ji\u0012\"\u0012A \t\u0003=}L1!!\u0001 \u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'\u0006\u0002\u0002\bA\u0019A%M\u001c\u0002\u0015M$(/^2ukJ,''\u0006\u0002\u0002\u000eA\u0019A%\r\"\u0002\u0015M$(/^2ukJ,7'\u0006\u0002\u0002\u0014A\u0019A%M#\u0002\u0015M$(/^2ukJ,G'\u0006\u0002\u0002\u001aA\u0019A%\r%\u0002\u0015M$(/^2ukJ,W'\u0006\u0002\u0002 A\u0019A%M&\u0002\u0015M$(/^2ukJ,g'\u0006\u0002\u0002&A\u0019A%\r(\u0002\u0015M$(/^2ukJ,w'\u0006\u0002\u0002,A\u0019A%M)\u0002\u0015M$(/^2ukJ,\u0007(\u0006\u0002\u00022A\u0019A%\r+\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u00028A\u0019A%M,\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003{\u00012\u0001J\u0019[\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005\r\u0003c\u0001\u00132;\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\tI\u0005E\u0002%c\u0001\f1b\u001d;sk\u000e$XO]32gU\u0011\u0011q\n\t\u0004IE\u001a\u0017aC:ueV\u001cG/\u001e:fcQ*\"!!\u0016\u0011\u0007\u0011\nd-A\u0006tiJ,8\r^;sKF*TCAA.!\r!\u0013'[\u0001\fgR\u0014Xo\u0019;ve\u0016\fd'\u0006\u0002\u0002bA\u0019A%\r7\u0002\u0017M$(/^2ukJ,\u0017gN\u000b\u0003\u0003O\u00022\u0001J\u0019p\u0003-\u0019HO];diV\u0014X-\r\u001d\u0016\u0005\u00055\u0004c\u0001\u00132e\u0006Y1\u000f\u001e:vGR,(/Z\u0019:+\t\t\u0019\bE\u0002%cU\faA\\3hCR,Gc\u0001\u001b\u0002z!1\u00111P\u000bA\u0002Q\n!\u0001\u001f\u0019"
)
public interface RngProduct19 extends Rng, SemiringProduct19 {
   Rng structure1();

   Rng structure2();

   Rng structure3();

   Rng structure4();

   Rng structure5();

   Rng structure6();

   Rng structure7();

   Rng structure8();

   Rng structure9();

   Rng structure10();

   Rng structure11();

   Rng structure12();

   Rng structure13();

   Rng structure14();

   Rng structure15();

   Rng structure16();

   Rng structure17();

   Rng structure18();

   Rng structure19();

   // $FF: synthetic method
   static Tuple19 negate$(final RngProduct19 $this, final Tuple19 x0) {
      return $this.negate(x0);
   }

   default Tuple19 negate(final Tuple19 x0) {
      return new Tuple19(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()), this.structure4().negate(x0._4()), this.structure5().negate(x0._5()), this.structure6().negate(x0._6()), this.structure7().negate(x0._7()), this.structure8().negate(x0._8()), this.structure9().negate(x0._9()), this.structure10().negate(x0._10()), this.structure11().negate(x0._11()), this.structure12().negate(x0._12()), this.structure13().negate(x0._13()), this.structure14().negate(x0._14()), this.structure15().negate(x0._15()), this.structure16().negate(x0._16()), this.structure17().negate(x0._17()), this.structure18().negate(x0._18()), this.structure19().negate(x0._19()));
   }

   static void $init$(final RngProduct19 $this) {
   }
}
