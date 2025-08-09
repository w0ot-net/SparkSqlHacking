package spire.std;

import cats.kernel.Eq;
import scala.Tuple10;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a\u0001C\u0007\u000f!\u0003\r\t\u0001\u0005\n\t\u000bQ\u0003A\u0011A+\t\u000be\u0003a1\u0001.\t\u000bq\u0003a1A/\t\u000b}\u0003a1\u00011\t\u000b\t\u0004a1A2\t\u000b\u0015\u0004a1\u00014\t\u000b!\u0004a1A5\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004A\u0011\u0001=\u0003\u0017\u0015\u000b\bK]8ek\u000e$\u0018\u0007\r\u0006\u0003\u001fA\t1a\u001d;e\u0015\u0005\t\u0012!B:qSJ,WcC\n1uu\u00025IR%M\u001fJ\u001b2\u0001\u0001\u000b\u001b!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fMB\u00191\u0004K\u0016\u000f\u0005q)cBA\u000f$\u001d\tq\"%D\u0001 \u0015\t\u0001\u0013%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\t\u0012B\u0001\u0013\u0011\u0003\u001d\tGnZ3ce\u0006L!AJ\u0014\u0002\u000fA\f7m[1hK*\u0011A\u0005E\u0005\u0003S)\u0012!!R9\u000b\u0005\u0019:\u0003\u0003D\u000b-]ebtHQ#I\u0017:\u000b\u0016BA\u0017\u0017\u0005\u001d!V\u000f\u001d7fcA\u0002\"a\f\u0019\r\u0001\u0011)\u0011\u0007\u0001b\u0001e\t\t\u0011)\u0005\u00024mA\u0011Q\u0003N\u0005\u0003kY\u0011qAT8uQ&tw\r\u0005\u0002\u0016o%\u0011\u0001H\u0006\u0002\u0004\u0003:L\bCA\u0018;\t\u0015Y\u0004A1\u00013\u0005\u0005\u0011\u0005CA\u0018>\t\u0015q\u0004A1\u00013\u0005\u0005\u0019\u0005CA\u0018A\t\u0015\t\u0005A1\u00013\u0005\u0005!\u0005CA\u0018D\t\u0015!\u0005A1\u00013\u0005\u0005)\u0005CA\u0018G\t\u00159\u0005A1\u00013\u0005\u00051\u0005CA\u0018J\t\u0015Q\u0005A1\u00013\u0005\u00059\u0005CA\u0018M\t\u0015i\u0005A1\u00013\u0005\u0005A\u0005CA\u0018P\t\u0015\u0001\u0006A1\u00013\u0005\u0005I\u0005CA\u0018S\t\u0015\u0019\u0006A1\u00013\u0005\u0005Q\u0015A\u0002\u0013j]&$H\u0005F\u0001W!\t)r+\u0003\u0002Y-\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u00027B\u00191\u0004\u000b\u0018\u0002\u0015M$(/^2ukJ,''F\u0001_!\rY\u0002&O\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A1\u0011\u0007mAC(\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u001a\t\u00047!z\u0014AC:ueV\u001cG/\u001e:fkU\tq\rE\u0002\u001cQ\t\u000b!b\u001d;sk\u000e$XO]37+\u0005Q\u0007cA\u000e)\u000b\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u00035\u00042a\u0007\u0015I\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002aB\u00191\u0004K&\u0002\u0015M$(/^2ukJ,\u0017(F\u0001t!\rY\u0002FT\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'F\u0001w!\rY\u0002&U\u0001\u0004KF4HcA=}}B\u0011QC_\u0005\u0003wZ\u0011qAQ8pY\u0016\fg\u000eC\u0003~\u0019\u0001\u00071&\u0001\u0002ya!)q\u0010\u0004a\u0001W\u0005\u0011\u00010\r"
)
public interface EqProduct10 extends Eq {
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

   // $FF: synthetic method
   static boolean eqv$(final EqProduct10 $this, final Tuple10 x0, final Tuple10 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple10 x0, final Tuple10 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3()) && this.structure4().eqv(x0._4(), x1._4()) && this.structure5().eqv(x0._5(), x1._5()) && this.structure6().eqv(x0._6(), x1._6()) && this.structure7().eqv(x0._7(), x1._7()) && this.structure8().eqv(x0._8(), x1._8()) && this.structure9().eqv(x0._9(), x1._9()) && this.structure10().eqv(x0._10(), x1._10());
   }

   static void $init$(final EqProduct10 $this) {
   }
}
