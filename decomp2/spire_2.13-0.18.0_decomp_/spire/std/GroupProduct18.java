package spire.std;

import cats.kernel.Group;
import scala.Tuple18;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=d\u0001C\u000b\u0017!\u0003\r\t\u0001\u0007\u000e\t\u000ba\u0004A\u0011A=\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\r\u0007\tY\u0005C\u0004\u0002P\u00011\u0019!!\u0015\t\u000f\u0005U\u0003Ab\u0001\u0002X!9\u00111\f\u0001\u0007\u0004\u0005u\u0003bBA1\u0001\u0019\r\u00111\r\u0005\b\u0003O\u0002A\u0011AA5\u000599%o\\;q!J|G-^2ucaR!a\u0006\r\u0002\u0007M$HMC\u0001\u001a\u0003\u0015\u0019\b/\u001b:f+MY\u0002HQ#I\u0017:\u000bFk\u0016.^A\u000e4\u0017\u000e\\8s'\u0011\u0001AD\t;\u0011\u0005u\u0001S\"\u0001\u0010\u000b\u0003}\tQa]2bY\u0006L!!\t\u0010\u0003\r\u0005s\u0017PU3g!\r\u0019\u0003g\r\b\u0003I5r!!J\u0016\u000f\u0005\u0019RS\"A\u0014\u000b\u0005!J\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003eI!\u0001\f\r\u0002\u000f\u0005dw-\u001a2sC&\u0011afL\u0001\ba\u0006\u001c7.Y4f\u0015\ta\u0003$\u0003\u00022e\t)qI]8va*\u0011af\f\t\u0015;Q2\u0014\tR$K\u001bB\u001bf+\u0017/`E\u0016D7N\\9\n\u0005Ur\"a\u0002+va2,\u0017\u0007\u000f\t\u0003oab\u0001\u0001B\u0003:\u0001\t\u0007!HA\u0001B#\tYd\b\u0005\u0002\u001ey%\u0011QH\b\u0002\b\u001d>$\b.\u001b8h!\tir(\u0003\u0002A=\t\u0019\u0011I\\=\u0011\u0005]\u0012E!B\"\u0001\u0005\u0004Q$!\u0001\"\u0011\u0005]*E!\u0002$\u0001\u0005\u0004Q$!A\"\u0011\u0005]BE!B%\u0001\u0005\u0004Q$!\u0001#\u0011\u0005]ZE!\u0002'\u0001\u0005\u0004Q$!A#\u0011\u0005]rE!B(\u0001\u0005\u0004Q$!\u0001$\u0011\u0005]\nF!\u0002*\u0001\u0005\u0004Q$!A$\u0011\u0005]\"F!B+\u0001\u0005\u0004Q$!\u0001%\u0011\u0005]:F!\u0002-\u0001\u0005\u0004Q$!A%\u0011\u0005]RF!B.\u0001\u0005\u0004Q$!\u0001&\u0011\u0005]jF!\u00020\u0001\u0005\u0004Q$!A&\u0011\u0005]\u0002G!B1\u0001\u0005\u0004Q$!\u0001'\u0011\u0005]\u001aG!\u00023\u0001\u0005\u0004Q$!A'\u0011\u0005]2G!B4\u0001\u0005\u0004Q$!\u0001(\u0011\u0005]JG!\u00026\u0001\u0005\u0004Q$!A(\u0011\u0005]bG!B7\u0001\u0005\u0004Q$!\u0001)\u0011\u0005]zG!\u00029\u0001\u0005\u0004Q$!A)\u0011\u0005]\u0012H!B:\u0001\u0005\u0004Q$!\u0001*\u0011)U4h'\u0011#H\u00156\u00036KV-]?\n,\u0007n\u001b8r\u001b\u00051\u0012BA<\u0017\u0005=iuN\\8jIB\u0013x\u000eZ;diFB\u0014A\u0002\u0013j]&$H\u0005F\u0001{!\ti20\u0003\u0002}=\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002\u007fB\u00191\u0005\r\u001c\u0002\u0015M$(/^2ukJ,''\u0006\u0002\u0002\u0006A\u00191\u0005M!\u0002\u0015M$(/^2ukJ,7'\u0006\u0002\u0002\fA\u00191\u0005\r#\u0002\u0015M$(/^2ukJ,G'\u0006\u0002\u0002\u0012A\u00191\u0005M$\u0002\u0015M$(/^2ukJ,W'\u0006\u0002\u0002\u0018A\u00191\u0005\r&\u0002\u0015M$(/^2ukJ,g'\u0006\u0002\u0002\u001eA\u00191\u0005M'\u0002\u0015M$(/^2ukJ,w'\u0006\u0002\u0002$A\u00191\u0005\r)\u0002\u0015M$(/^2ukJ,\u0007(\u0006\u0002\u0002*A\u00191\u0005M*\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u00020A\u00191\u0005\r,\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003k\u00012a\t\u0019Z\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005m\u0002cA\u001219\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\t\t\u0005E\u0002$a}\u000b1b\u001d;sk\u000e$XO]32gU\u0011\u0011q\t\t\u0004GA\u0012\u0017aC:ueV\u001cG/\u001e:fcQ*\"!!\u0014\u0011\u0007\r\u0002T-A\u0006tiJ,8\r^;sKF*TCAA*!\r\u0019\u0003\u0007[\u0001\fgR\u0014Xo\u0019;ve\u0016\fd'\u0006\u0002\u0002ZA\u00191\u0005M6\u0002\u0017M$(/^2ukJ,\u0017gN\u000b\u0003\u0003?\u00022a\t\u0019o\u0003-\u0019HO];diV\u0014X-\r\u001d\u0016\u0005\u0005\u0015\u0004cA\u00121c\u00069\u0011N\u001c<feN,GcA\u001a\u0002l!1\u0011Q\u000e\u000bA\u0002M\n!\u0001\u001f\u0019"
)
public interface GroupProduct18 extends Group, MonoidProduct18 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   Group structure9();

   Group structure10();

   Group structure11();

   Group structure12();

   Group structure13();

   Group structure14();

   Group structure15();

   Group structure16();

   Group structure17();

   Group structure18();

   // $FF: synthetic method
   static Tuple18 inverse$(final GroupProduct18 $this, final Tuple18 x0) {
      return $this.inverse(x0);
   }

   default Tuple18 inverse(final Tuple18 x0) {
      return new Tuple18(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()), this.structure15().inverse(x0._15()), this.structure16().inverse(x0._16()), this.structure17().inverse(x0._17()), this.structure18().inverse(x0._18()));
   }

   static void $init$(final GroupProduct18 $this) {
   }
}
