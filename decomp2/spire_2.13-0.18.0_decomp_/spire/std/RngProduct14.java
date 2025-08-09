package spire.std;

import algebra.ring.Rng;
import scala.Tuple14;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001C\t\u0013!\u0003\r\t\u0001\u0006\f\t\u000b!\u0004A\u0011A5\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001C\u0001\u0003c\u0011AB\u00158h!J|G-^2ucQR!a\u0005\u000b\u0002\u0007M$HMC\u0001\u0016\u0003\u0015\u0019\b/\u001b:f+=9BGP!E\u000f*k\u0005k\u0015,Z9~\u00137\u0003\u0002\u0001\u0019=\u0011\u0004\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0007cA\u0010-_9\u0011\u0001%\u000b\b\u0003C\u001dr!A\t\u0014\u000e\u0003\rR!\u0001J\u0013\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011!F\u0005\u0003QQ\tq!\u00197hK\n\u0014\u0018-\u0003\u0002+W\u00059\u0001/Y2lC\u001e,'B\u0001\u0015\u0015\u0013\ticFA\u0002S]\u001eT!AK\u0016\u0011!e\u0001$'\u0010!D\r&cuJU+Y7z\u000b\u0017BA\u0019\u001b\u0005\u001d!V\u000f\u001d7fcQ\u0002\"a\r\u001b\r\u0001\u0011)Q\u0007\u0001b\u0001m\t\t\u0011)\u0005\u00028uA\u0011\u0011\u0004O\u0005\u0003si\u0011qAT8uQ&tw\r\u0005\u0002\u001aw%\u0011AH\u0007\u0002\u0004\u0003:L\bCA\u001a?\t\u0015y\u0004A1\u00017\u0005\u0005\u0011\u0005CA\u001aB\t\u0015\u0011\u0005A1\u00017\u0005\u0005\u0019\u0005CA\u001aE\t\u0015)\u0005A1\u00017\u0005\u0005!\u0005CA\u001aH\t\u0015A\u0005A1\u00017\u0005\u0005)\u0005CA\u001aK\t\u0015Y\u0005A1\u00017\u0005\u00051\u0005CA\u001aN\t\u0015q\u0005A1\u00017\u0005\u00059\u0005CA\u001aQ\t\u0015\t\u0006A1\u00017\u0005\u0005A\u0005CA\u001aT\t\u0015!\u0006A1\u00017\u0005\u0005I\u0005CA\u001aW\t\u00159\u0006A1\u00017\u0005\u0005Q\u0005CA\u001aZ\t\u0015Q\u0006A1\u00017\u0005\u0005Y\u0005CA\u001a]\t\u0015i\u0006A1\u00017\u0005\u0005a\u0005CA\u001a`\t\u0015\u0001\u0007A1\u00017\u0005\u0005i\u0005CA\u001ac\t\u0015\u0019\u0007A1\u00017\u0005\u0005q\u0005\u0003E3geu\u00025IR%M\u001fJ+\u0006l\u00170b\u001b\u0005\u0011\u0012BA4\u0013\u0005E\u0019V-\\5sS:<\u0007K]8ek\u000e$\u0018\u0007N\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003)\u0004\"!G6\n\u00051T\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005y\u0007cA\u0010-e\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003I\u00042a\b\u0017>\u0003)\u0019HO];diV\u0014XmM\u000b\u0002kB\u0019q\u0004\f!\u0002\u0015M$(/^2ukJ,G'F\u0001y!\ryBfQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#A>\u0011\u0007}ac)\u0001\u0006tiJ,8\r^;sKZ*\u0012A \t\u0004?1J\u0015AC:ueV\u001cG/\u001e:foU\u0011\u00111\u0001\t\u0004?1b\u0015AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\u0002\t\u0004?1z\u0015AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\u0002\t\u0004?1\u0012\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\u0006\u0011\u0007}aS+A\u0006tiJ,8\r^;sKF\nTCAA\u000e!\ryB\u0006W\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002\"A\u0019q\u0004L.\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003O\u00012a\b\u0017_\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u00055\u0002cA\u0010-C\u00061a.Z4bi\u0016$2aLA\u001a\u0011\u0019\t)\u0004\u0005a\u0001_\u0005\u0011\u0001\u0010\r"
)
public interface RngProduct14 extends Rng, SemiringProduct14 {
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

   // $FF: synthetic method
   static Tuple14 negate$(final RngProduct14 $this, final Tuple14 x0) {
      return $this.negate(x0);
   }

   default Tuple14 negate(final Tuple14 x0) {
      return new Tuple14(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()), this.structure4().negate(x0._4()), this.structure5().negate(x0._5()), this.structure6().negate(x0._6()), this.structure7().negate(x0._7()), this.structure8().negate(x0._8()), this.structure9().negate(x0._9()), this.structure10().negate(x0._10()), this.structure11().negate(x0._11()), this.structure12().negate(x0._12()), this.structure13().negate(x0._13()), this.structure14().negate(x0._14()));
   }

   static void $init$(final RngProduct14 $this) {
   }
}
