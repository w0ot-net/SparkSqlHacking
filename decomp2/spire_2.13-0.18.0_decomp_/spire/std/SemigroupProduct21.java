package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ue\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u00111\u0002\u0001\u0007\u0004\u00055\u0001bBA\t\u0001\u0019\r\u00111\u0003\u0005\b\u0003/\u0001a1AA\r\u0011\u001d\ti\u0002\u0001D\u0002\u0003?Aq!a\t\u0001\r\u0007\t)\u0003C\u0004\u0002*\u00011\u0019!a\u000b\t\u000f\u0005=\u0002Ab\u0001\u00022!9\u0011Q\u0007\u0001\u0007\u0004\u0005]\u0002bBA\u001e\u0001\u0019\r\u0011Q\b\u0005\b\u0003\u0003\u0002a1AA\"\u0011\u001d\t9\u0005\u0001D\u0002\u0003\u0013Bq!!\u0014\u0001\r\u0007\ty\u0005C\u0004\u0002T\u00011\u0019!!\u0016\t\u000f\u0005e\u0003Ab\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0004\u0005\u0005\u0004bBA3\u0001\u0019\r\u0011q\r\u0005\b\u0003W\u0002a1AA7\u0011\u001d\t\t\b\u0001D\u0002\u0003gBq!a\u001e\u0001\r\u0007\tI\bC\u0004\u0002~\u00011\u0019!a \t\u000f\u0005\r\u0005Ab\u0001\u0002\u0006\"9\u0011\u0011\u0012\u0001\u0005\u0002\u0005-%AE*f[&<'o\\;q!J|G-^2ueER!AG\u000e\u0002\u0007M$HMC\u0001\u001d\u0003\u0015\u0019\b/\u001b:f+Yq2(\u0012%L\u001dF#vKW/aG\u001aLGn\u001c:vqnt8c\u0001\u0001 KA\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\u00042AJ\u001a7\u001d\t9\u0003G\u0004\u0002)]9\u0011\u0011&L\u0007\u0002U)\u00111\u0006L\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tA$\u0003\u000207\u00059\u0011\r\\4fEJ\f\u0017BA\u00193\u0003\u001d\u0001\u0018mY6bO\u0016T!aL\u000e\n\u0005Q*$!C*f[&<'o\\;q\u0015\t\t$\u0007E\f!oe\"uIS'Q'ZKFl\u00182fQ.t\u0017\u000f^<{{&\u0011\u0001(\t\u0002\b)V\u0004H.\u001a\u001a2!\tQ4\b\u0004\u0001\u0005\u000bq\u0002!\u0019A\u001f\u0003\u0003\u0005\u000b\"AP!\u0011\u0005\u0001z\u0014B\u0001!\"\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\t\"\n\u0005\r\u000b#aA!osB\u0011!(\u0012\u0003\u0006\r\u0002\u0011\r!\u0010\u0002\u0002\u0005B\u0011!\b\u0013\u0003\u0006\u0013\u0002\u0011\r!\u0010\u0002\u0002\u0007B\u0011!h\u0013\u0003\u0006\u0019\u0002\u0011\r!\u0010\u0002\u0002\tB\u0011!H\u0014\u0003\u0006\u001f\u0002\u0011\r!\u0010\u0002\u0002\u000bB\u0011!(\u0015\u0003\u0006%\u0002\u0011\r!\u0010\u0002\u0002\rB\u0011!\b\u0016\u0003\u0006+\u0002\u0011\r!\u0010\u0002\u0002\u000fB\u0011!h\u0016\u0003\u00061\u0002\u0011\r!\u0010\u0002\u0002\u0011B\u0011!H\u0017\u0003\u00067\u0002\u0011\r!\u0010\u0002\u0002\u0013B\u0011!(\u0018\u0003\u0006=\u0002\u0011\r!\u0010\u0002\u0002\u0015B\u0011!\b\u0019\u0003\u0006C\u0002\u0011\r!\u0010\u0002\u0002\u0017B\u0011!h\u0019\u0003\u0006I\u0002\u0011\r!\u0010\u0002\u0002\u0019B\u0011!H\u001a\u0003\u0006O\u0002\u0011\r!\u0010\u0002\u0002\u001bB\u0011!(\u001b\u0003\u0006U\u0002\u0011\r!\u0010\u0002\u0002\u001dB\u0011!\b\u001c\u0003\u0006[\u0002\u0011\r!\u0010\u0002\u0002\u001fB\u0011!h\u001c\u0003\u0006a\u0002\u0011\r!\u0010\u0002\u0002!B\u0011!H\u001d\u0003\u0006g\u0002\u0011\r!\u0010\u0002\u0002#B\u0011!(\u001e\u0003\u0006m\u0002\u0011\r!\u0010\u0002\u0002%B\u0011!\b\u001f\u0003\u0006s\u0002\u0011\r!\u0010\u0002\u0002'B\u0011!h\u001f\u0003\u0006y\u0002\u0011\r!\u0010\u0002\u0002)B\u0011!H \u0003\u0006\u007f\u0002\u0011\r!\u0010\u0002\u0002+\u00061A%\u001b8ji\u0012\"\"!!\u0002\u0011\u0007\u0001\n9!C\u0002\u0002\n\u0005\u0012A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0005\u0005=\u0001c\u0001\u00144s\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0005\u0005U\u0001c\u0001\u00144\t\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0005\u0005m\u0001c\u0001\u00144\u000f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005\u0005\u0002c\u0001\u00144\u0015\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005\u001d\u0002c\u0001\u00144\u001b\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u00055\u0002c\u0001\u00144!\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005M\u0002c\u0001\u00144'\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005e\u0002c\u0001\u00144-\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005}\u0002c\u0001\u001443\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t)\u0005E\u0002'gq\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\n\t\u0004MMz\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0015\u0011\u0007\u0019\u001a$-A\u0006tiJ,8\r^;sKF\u001aTCAA,!\r13'Z\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002^A\u0019ae\r5\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u0003G\u00022AJ\u001al\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005%\u0004c\u0001\u00144]\u0006Y1\u000f\u001e:vGR,(/Z\u00198+\t\ty\u0007E\u0002'gE\f1b\u001d;sk\u000e$XO]32qU\u0011\u0011Q\u000f\t\u0004MM\"\u0018aC:ueV\u001cG/\u001e:fce*\"!a\u001f\u0011\u0007\u0019\u001at/A\u0006tiJ,8\r^;sKJ\u0002TCAAA!\r13G_\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014\u0014'\u0006\u0002\u0002\bB\u0019aeM?\u0002\u000f\r|WNY5oKR)a'!$\u0002\u0012\"1\u0011qR\fA\u0002Y\n!\u0001\u001f\u0019\t\r\u0005Mu\u00031\u00017\u0003\tA\u0018\u0007"
)
public interface SemigroupProduct21 extends Semigroup {
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

   Semigroup structure19();

   Semigroup structure20();

   Semigroup structure21();

   // $FF: synthetic method
   static Tuple21 combine$(final SemigroupProduct21 $this, final Tuple21 x0, final Tuple21 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple21 combine(final Tuple21 x0, final Tuple21 x1) {
      return new Tuple21(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()), this.structure15().combine(x0._15(), x1._15()), this.structure16().combine(x0._16(), x1._16()), this.structure17().combine(x0._17(), x1._17()), this.structure18().combine(x0._18(), x1._18()), this.structure19().combine(x0._19(), x1._19()), this.structure20().combine(x0._20(), x1._20()), this.structure21().combine(x0._21(), x1._21()));
   }

   static void $init$(final SemigroupProduct21 $this) {
   }
}
