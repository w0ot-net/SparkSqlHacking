package spire.std;

import cats.kernel.Eq;
import scala.Tuple21;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005me\u0001\u0003\r\u001a!\u0003\r\taG\u000f\t\u000f\u0005\u0005\u0001\u0001\"\u0001\u0002\u0004!9\u00111\u0002\u0001\u0007\u0004\u00055\u0001bBA\t\u0001\u0019\r\u00111\u0003\u0005\b\u0003/\u0001a1AA\r\u0011\u001d\ti\u0002\u0001D\u0002\u0003?Aq!a\t\u0001\r\u0007\t)\u0003C\u0004\u0002*\u00011\u0019!a\u000b\t\u000f\u0005=\u0002Ab\u0001\u00022!9\u0011Q\u0007\u0001\u0007\u0004\u0005]\u0002bBA\u001e\u0001\u0019\r\u0011Q\b\u0005\b\u0003\u0003\u0002a1AA\"\u0011\u001d\t9\u0005\u0001D\u0002\u0003\u0013Bq!!\u0014\u0001\r\u0007\ty\u0005C\u0004\u0002T\u00011\u0019!!\u0016\t\u000f\u0005e\u0003Ab\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0004\u0005\u0005\u0004bBA3\u0001\u0019\r\u0011q\r\u0005\b\u0003W\u0002a1AA7\u0011\u001d\t\t\b\u0001D\u0002\u0003gBq!a\u001e\u0001\r\u0007\tI\bC\u0004\u0002~\u00011\u0019!a \t\u000f\u0005\r\u0005Ab\u0001\u0002\u0006\"9\u0011\u0011\u0012\u0001\u0005\u0002\u0005-%aC#r!J|G-^2ueER!AG\u000e\u0002\u0007M$HMC\u0001\u001d\u0003\u0015\u0019\b/\u001b:f+Yq2(\u0012%L\u001dF#vKW/aG\u001aLGn\u001c:vqnt8c\u0001\u0001 KA\u0011\u0001eI\u0007\u0002C)\t!%A\u0003tG\u0006d\u0017-\u0003\u0002%C\t1\u0011I\\=SK\u001a\u00042AJ\u001a7\u001d\t9\u0003G\u0004\u0002)]9\u0011\u0011&L\u0007\u0002U)\u00111\u0006L\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tA$\u0003\u000207\u00059\u0011\r\\4fEJ\f\u0017BA\u00193\u0003\u001d\u0001\u0018mY6bO\u0016T!aL\u000e\n\u0005Q*$AA#r\u0015\t\t$\u0007E\f!oe\"uIS'Q'ZKFl\u00182fQ.t\u0017\u000f^<{{&\u0011\u0001(\t\u0002\b)V\u0004H.\u001a\u001a2!\tQ4\b\u0004\u0001\u0005\u000bq\u0002!\u0019A\u001f\u0003\u0003\u0005\u000b\"AP!\u0011\u0005\u0001z\u0014B\u0001!\"\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\t\"\n\u0005\r\u000b#aA!osB\u0011!(\u0012\u0003\u0006\r\u0002\u0011\r!\u0010\u0002\u0002\u0005B\u0011!\b\u0013\u0003\u0006\u0013\u0002\u0011\r!\u0010\u0002\u0002\u0007B\u0011!h\u0013\u0003\u0006\u0019\u0002\u0011\r!\u0010\u0002\u0002\tB\u0011!H\u0014\u0003\u0006\u001f\u0002\u0011\r!\u0010\u0002\u0002\u000bB\u0011!(\u0015\u0003\u0006%\u0002\u0011\r!\u0010\u0002\u0002\rB\u0011!\b\u0016\u0003\u0006+\u0002\u0011\r!\u0010\u0002\u0002\u000fB\u0011!h\u0016\u0003\u00061\u0002\u0011\r!\u0010\u0002\u0002\u0011B\u0011!H\u0017\u0003\u00067\u0002\u0011\r!\u0010\u0002\u0002\u0013B\u0011!(\u0018\u0003\u0006=\u0002\u0011\r!\u0010\u0002\u0002\u0015B\u0011!\b\u0019\u0003\u0006C\u0002\u0011\r!\u0010\u0002\u0002\u0017B\u0011!h\u0019\u0003\u0006I\u0002\u0011\r!\u0010\u0002\u0002\u0019B\u0011!H\u001a\u0003\u0006O\u0002\u0011\r!\u0010\u0002\u0002\u001bB\u0011!(\u001b\u0003\u0006U\u0002\u0011\r!\u0010\u0002\u0002\u001dB\u0011!\b\u001c\u0003\u0006[\u0002\u0011\r!\u0010\u0002\u0002\u001fB\u0011!h\u001c\u0003\u0006a\u0002\u0011\r!\u0010\u0002\u0002!B\u0011!H\u001d\u0003\u0006g\u0002\u0011\r!\u0010\u0002\u0002#B\u0011!(\u001e\u0003\u0006m\u0002\u0011\r!\u0010\u0002\u0002%B\u0011!\b\u001f\u0003\u0006s\u0002\u0011\r!\u0010\u0002\u0002'B\u0011!h\u001f\u0003\u0006y\u0002\u0011\r!\u0010\u0002\u0002)B\u0011!H \u0003\u0006\u007f\u0002\u0011\r!\u0010\u0002\u0002+\u00061A%\u001b8ji\u0012\"\"!!\u0002\u0011\u0007\u0001\n9!C\u0002\u0002\n\u0005\u0012A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0005\u0005=\u0001c\u0001\u00144s\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0005\u0005U\u0001c\u0001\u00144\t\u0006Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0005\u0005m\u0001c\u0001\u00144\u000f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0005\u0005\u0005\u0002c\u0001\u00144\u0015\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0005\u0005\u001d\u0002c\u0001\u00144\u001b\u0006Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0005\u00055\u0002c\u0001\u00144!\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0005\u0005M\u0002c\u0001\u00144'\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005e\u0002c\u0001\u00144-\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005}\u0002c\u0001\u001443\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t)\u0005E\u0002'gq\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\n\t\u0004MMz\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0015\u0011\u0007\u0019\u001a$-A\u0006tiJ,8\r^;sKF\u001aTCAA,!\r13'Z\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002^A\u0019ae\r5\u0002\u0017M$(/^2ukJ,\u0017'N\u000b\u0003\u0003G\u00022AJ\u001al\u0003-\u0019HO];diV\u0014X-\r\u001c\u0016\u0005\u0005%\u0004c\u0001\u00144]\u0006Y1\u000f\u001e:vGR,(/Z\u00198+\t\ty\u0007E\u0002'gE\f1b\u001d;sk\u000e$XO]32qU\u0011\u0011Q\u000f\t\u0004MM\"\u0018aC:ueV\u001cG/\u001e:fce*\"!a\u001f\u0011\u0007\u0019\u001at/A\u0006tiJ,8\r^;sKJ\u0002TCAAA!\r13G_\u0001\fgR\u0014Xo\u0019;ve\u0016\u0014\u0014'\u0006\u0002\u0002\bB\u0019aeM?\u0002\u0007\u0015\fh\u000f\u0006\u0004\u0002\u000e\u0006M\u0015q\u0013\t\u0004A\u0005=\u0015bAAIC\t9!i\\8mK\u0006t\u0007BBAK/\u0001\u0007a'\u0001\u0002ya!1\u0011\u0011T\fA\u0002Y\n!\u0001_\u0019"
)
public interface EqProduct21 extends Eq {
   Eq structure1();

   Eq structure2();

   Eq structure3();

   Eq structure4();

   Eq structure5();

   Eq structure6();

   Eq structure7();

   Eq structure8();

   Eq structure9();

   Eq structure10();

   Eq structure11();

   Eq structure12();

   Eq structure13();

   Eq structure14();

   Eq structure15();

   Eq structure16();

   Eq structure17();

   Eq structure18();

   Eq structure19();

   Eq structure20();

   Eq structure21();

   // $FF: synthetic method
   static boolean eqv$(final EqProduct21 $this, final Tuple21 x0, final Tuple21 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple21 x0, final Tuple21 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3()) && this.structure4().eqv(x0._4(), x1._4()) && this.structure5().eqv(x0._5(), x1._5()) && this.structure6().eqv(x0._6(), x1._6()) && this.structure7().eqv(x0._7(), x1._7()) && this.structure8().eqv(x0._8(), x1._8()) && this.structure9().eqv(x0._9(), x1._9()) && this.structure10().eqv(x0._10(), x1._10()) && this.structure11().eqv(x0._11(), x1._11()) && this.structure12().eqv(x0._12(), x1._12()) && this.structure13().eqv(x0._13(), x1._13()) && this.structure14().eqv(x0._14(), x1._14()) && this.structure15().eqv(x0._15(), x1._15()) && this.structure16().eqv(x0._16(), x1._16()) && this.structure17().eqv(x0._17(), x1._17()) && this.structure18().eqv(x0._18(), x1._18()) && this.structure19().eqv(x0._19(), x1._19()) && this.structure20().eqv(x0._20(), x1._20()) && this.structure21().eqv(x0._21(), x1._21());
   }

   static void $init$(final EqProduct21 $this) {
   }
}
