package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d\u0001C\u000b\u0017!\u0003\r\t\u0001\u0007\u000e\t\u000bQ\u0004A\u0011A;\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\r\u0007\t9\u0004C\u0004\u0002<\u00011\u0019!!\u0010\t\u000f\u0005\u0005\u0003Ab\u0001\u0002D!9\u0011q\t\u0001\u0007\u0004\u0005%\u0003bBA'\u0001\u0019\r\u0011q\n\u0005\b\u0003'\u0002a1AA+\u0011\u001d\tI\u0006\u0001D\u0002\u00037Bq!a\u0018\u0001\t\u0003\t\tG\u0001\nTK6LwM]8vaB\u0013x\u000eZ;diFB$BA\f\u0019\u0003\r\u0019H\u000f\u001a\u0006\u00023\u0005)1\u000f]5sKV\u00192\u0004\u000f\"F\u0011.s\u0015\u000bV,[;\u0002\u001cg-\u001b7peN\u0019\u0001\u0001\b\u0012\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\r\u0005s\u0017PU3g!\r\u0019\u0003g\r\b\u0003I5r!!J\u0016\u000f\u0005\u0019RS\"A\u0014\u000b\u0005!J\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003eI!\u0001\f\r\u0002\u000f\u0005dw-\u001a2sC&\u0011afL\u0001\ba\u0006\u001c7.Y4f\u0015\ta\u0003$\u0003\u00022e\tI1+Z7jOJ|W\u000f\u001d\u0006\u0003]=\u0002B#\b\u001b7\u0003\u0012;%*\u0014)T-fcvLY3iW:\f\u0018BA\u001b\u001f\u0005\u001d!V\u000f\u001d7fca\u0002\"a\u000e\u001d\r\u0001\u0011)\u0011\b\u0001b\u0001u\t\t\u0011)\u0005\u0002<}A\u0011Q\u0004P\u0005\u0003{y\u0011qAT8uQ&tw\r\u0005\u0002\u001e\u007f%\u0011\u0001I\b\u0002\u0004\u0003:L\bCA\u001cC\t\u0015\u0019\u0005A1\u0001;\u0005\u0005\u0011\u0005CA\u001cF\t\u00151\u0005A1\u0001;\u0005\u0005\u0019\u0005CA\u001cI\t\u0015I\u0005A1\u0001;\u0005\u0005!\u0005CA\u001cL\t\u0015a\u0005A1\u0001;\u0005\u0005)\u0005CA\u001cO\t\u0015y\u0005A1\u0001;\u0005\u00051\u0005CA\u001cR\t\u0015\u0011\u0006A1\u0001;\u0005\u00059\u0005CA\u001cU\t\u0015)\u0006A1\u0001;\u0005\u0005A\u0005CA\u001cX\t\u0015A\u0006A1\u0001;\u0005\u0005I\u0005CA\u001c[\t\u0015Y\u0006A1\u0001;\u0005\u0005Q\u0005CA\u001c^\t\u0015q\u0006A1\u0001;\u0005\u0005Y\u0005CA\u001ca\t\u0015\t\u0007A1\u0001;\u0005\u0005a\u0005CA\u001cd\t\u0015!\u0007A1\u0001;\u0005\u0005i\u0005CA\u001cg\t\u00159\u0007A1\u0001;\u0005\u0005q\u0005CA\u001cj\t\u0015Q\u0007A1\u0001;\u0005\u0005y\u0005CA\u001cm\t\u0015i\u0007A1\u0001;\u0005\u0005\u0001\u0006CA\u001cp\t\u0015\u0001\bA1\u0001;\u0005\u0005\t\u0006CA\u001cs\t\u0015\u0019\bA1\u0001;\u0005\u0005\u0011\u0016A\u0002\u0013j]&$H\u0005F\u0001w!\tir/\u0003\u0002y=\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002wB\u00191\u0005\r\u001c\u0002\u0015M$(/^2ukJ,''F\u0001\u007f!\r\u0019\u0003'Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cTCAA\u0002!\r\u0019\u0003\u0007R\u0001\u000bgR\u0014Xo\u0019;ve\u0016$TCAA\u0005!\r\u0019\u0003gR\u0001\u000bgR\u0014Xo\u0019;ve\u0016,TCAA\b!\r\u0019\u0003GS\u0001\u000bgR\u0014Xo\u0019;ve\u00164TCAA\u000b!\r\u0019\u0003'T\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u000e!\r\u0019\u0003\u0007U\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA\u0011!\r\u0019\u0003gU\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA\u0014!\r\u0019\u0003GV\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u0002.A\u00191\u0005M-\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u0003g\u00012a\t\u0019]\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005e\u0002cA\u00121?\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\ty\u0004E\u0002$a\t\f1b\u001d;sk\u000e$XO]32iU\u0011\u0011Q\t\t\u0004GA*\u0017aC:ueV\u001cG/\u001e:fcU*\"!a\u0013\u0011\u0007\r\u0002\u0004.A\u0006tiJ,8\r^;sKF2TCAA)!\r\u0019\u0003g[\u0001\fgR\u0014Xo\u0019;ve\u0016\ft'\u0006\u0002\u0002XA\u00191\u0005\r8\u0002\u0017M$(/^2ukJ,\u0017\u0007O\u000b\u0003\u0003;\u00022a\t\u0019r\u0003\u001d\u0019w.\u001c2j]\u0016$RaMA2\u0003OBa!!\u001a\u0015\u0001\u0004\u0019\u0014A\u0001=1\u0011\u0019\tI\u0007\u0006a\u0001g\u0005\u0011\u00010\r"
)
public interface SemigroupProduct18 extends Semigroup {
   Semigroup structure1();

   Semigroup structure2();

   Semigroup structure3();

   Semigroup structure4();

   Semigroup structure5();

   Semigroup structure6();

   Semigroup structure7();

   Semigroup structure8();

   Semigroup structure9();

   Semigroup structure10();

   Semigroup structure11();

   Semigroup structure12();

   Semigroup structure13();

   Semigroup structure14();

   Semigroup structure15();

   Semigroup structure16();

   Semigroup structure17();

   Semigroup structure18();

   // $FF: synthetic method
   static Tuple18 combine$(final SemigroupProduct18 $this, final Tuple18 x0, final Tuple18 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple18 combine(final Tuple18 x0, final Tuple18 x1) {
      return new Tuple18(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()), this.structure15().combine(x0._15(), x1._15()), this.structure16().combine(x0._16(), x1._16()), this.structure17().combine(x0._17(), x1._17()), this.structure18().combine(x0._18(), x1._18()));
   }

   static void $init$(final SemigroupProduct18 $this) {
   }
}
