package spire.std;

import cats.kernel.Eq;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-4\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0004\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u0002!\ta\u0019\u0002\u000b\u000bF\u0004&o\u001c3vGR<$B\u0001\u0007\u000e\u0003\r\u0019H\u000f\u001a\u0006\u0002\u001d\u0005)1\u000f]5sKVA\u0001#L\u001c;{\u0001\u001beiE\u0002\u0001#]\u0001\"AE\u000b\u000e\u0003MQ\u0011\u0001F\u0001\u0006g\u000e\fG.Y\u0005\u0003-M\u0011a!\u00118z%\u00164\u0007c\u0001\r&Q9\u0011\u0011D\t\b\u00035\u0001r!aG\u0010\u000e\u0003qQ!!\b\u0010\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AD\u0005\u0003C5\tq!\u00197hK\n\u0014\u0018-\u0003\u0002$I\u00059\u0001/Y2lC\u001e,'BA\u0011\u000e\u0013\t1sE\u0001\u0002Fc*\u00111\u0005\n\t\n%%Zc'\u000f\u001f@\u0005\u0016K!AK\n\u0003\rQ+\b\u000f\\38!\taS\u0006\u0004\u0001\u0005\u000b9\u0002!\u0019A\u0018\u0003\u0003\u0005\u000b\"\u0001M\u001a\u0011\u0005I\t\u0014B\u0001\u001a\u0014\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0005\u001b\n\u0005U\u001a\"aA!osB\u0011Af\u000e\u0003\u0006q\u0001\u0011\ra\f\u0002\u0002\u0005B\u0011AF\u000f\u0003\u0006w\u0001\u0011\ra\f\u0002\u0002\u0007B\u0011A&\u0010\u0003\u0006}\u0001\u0011\ra\f\u0002\u0002\tB\u0011A\u0006\u0011\u0003\u0006\u0003\u0002\u0011\ra\f\u0002\u0002\u000bB\u0011Af\u0011\u0003\u0006\t\u0002\u0011\ra\f\u0002\u0002\rB\u0011AF\u0012\u0003\u0006\u000f\u0002\u0011\ra\f\u0002\u0002\u000f\u00061A%\u001b8ji\u0012\"\u0012A\u0013\t\u0003%-K!\u0001T\n\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#A(\u0011\u0007a)3&\u0001\u0006tiJ,8\r^;sKJ*\u0012A\u0015\t\u00041\u00152\u0014AC:ueV\u001cG/\u001e:fgU\tQ\u000bE\u0002\u0019Ke\n!b\u001d;sk\u000e$XO]35+\u0005A\u0006c\u0001\r&y\u0005Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0003m\u00032\u0001G\u0013@\u0003)\u0019HO];diV\u0014XMN\u000b\u0002=B\u0019\u0001$\n\"\u0002\u0015M$(/^2ukJ,w'F\u0001b!\rAR%R\u0001\u0004KF4Hc\u00013hSB\u0011!#Z\u0005\u0003MN\u0011qAQ8pY\u0016\fg\u000eC\u0003i\u0013\u0001\u0007\u0001&\u0001\u0002ya!)!.\u0003a\u0001Q\u0005\u0011\u00010\r"
)
public interface EqProduct7 extends Eq {
   Eq structure1();

   Eq structure2();

   Eq structure3();

   Eq structure4();

   Eq structure5();

   Eq structure6();

   Eq structure7();

   // $FF: synthetic method
   static boolean eqv$(final EqProduct7 $this, final Tuple7 x0, final Tuple7 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple7 x0, final Tuple7 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3()) && this.structure4().eqv(x0._4(), x1._4()) && this.structure5().eqv(x0._5(), x1._5()) && this.structure6().eqv(x0._6(), x1._6()) && this.structure7().eqv(x0._7(), x1._7());
   }

   static void $init$(final EqProduct7 $this) {
   }
}
