package spire.std;

import cats.kernel.Monoid;
import scala.Tuple13;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015b\u0001\u0003\t\u0012!\u0003\r\taE\u000b\t\u000b\u0011\u0004A\u0011A3\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\t\u0003\t\u0019CA\bN_:|\u0017\u000e\u001a)s_\u0012,8\r^\u00194\u0015\t\u00112#A\u0002ti\u0012T\u0011\u0001F\u0001\u0006gBL'/Z\u000b\u000f-Mj\u0004i\u0011$J\u0019>\u0013V\u000bW._'\u0011\u0001q#\b1\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\rq2F\f\b\u0003?!r!\u0001\t\u0014\u000f\u0005\u0005*S\"\u0001\u0012\u000b\u0005\r\"\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003QI!aJ\n\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011FK\u0001\ba\u0006\u001c7.Y4f\u0015\t93#\u0003\u0002-[\t1Qj\u001c8pS\u0012T!!\u000b\u0016\u0011\u001fay\u0013\u0007P C\u000b\"[e*\u0015+X5vK!\u0001M\r\u0003\u000fQ+\b\u000f\\32gA\u0011!g\r\u0007\u0001\t\u0015!\u0004A1\u00016\u0005\u0005\t\u0015C\u0001\u001c:!\tAr'\u0003\u000293\t9aj\u001c;iS:<\u0007C\u0001\r;\u0013\tY\u0014DA\u0002B]f\u0004\"AM\u001f\u0005\u000by\u0002!\u0019A\u001b\u0003\u0003\t\u0003\"A\r!\u0005\u000b\u0005\u0003!\u0019A\u001b\u0003\u0003\r\u0003\"AM\"\u0005\u000b\u0011\u0003!\u0019A\u001b\u0003\u0003\u0011\u0003\"A\r$\u0005\u000b\u001d\u0003!\u0019A\u001b\u0003\u0003\u0015\u0003\"AM%\u0005\u000b)\u0003!\u0019A\u001b\u0003\u0003\u0019\u0003\"A\r'\u0005\u000b5\u0003!\u0019A\u001b\u0003\u0003\u001d\u0003\"AM(\u0005\u000bA\u0003!\u0019A\u001b\u0003\u0003!\u0003\"A\r*\u0005\u000bM\u0003!\u0019A\u001b\u0003\u0003%\u0003\"AM+\u0005\u000bY\u0003!\u0019A\u001b\u0003\u0003)\u0003\"A\r-\u0005\u000be\u0003!\u0019A\u001b\u0003\u0003-\u0003\"AM.\u0005\u000bq\u0003!\u0019A\u001b\u0003\u00031\u0003\"A\r0\u0005\u000b}\u0003!\u0019A\u001b\u0003\u00035\u0003r\"\u001922y}\u0012U\tS&O#R;&,X\u0007\u0002#%\u00111-\u0005\u0002\u0013'\u0016l\u0017n\u001a:pkB\u0004&o\u001c3vGR\f4'\u0001\u0004%S:LG\u000f\n\u000b\u0002MB\u0011\u0001dZ\u0005\u0003Qf\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003-\u00042AH\u00162\u0003)\u0019HO];diV\u0014XMM\u000b\u0002]B\u0019ad\u000b\u001f\u0002\u0015M$(/^2ukJ,7'F\u0001r!\rq2fP\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u0001;\u0011\u0007yY#)\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001e\t\u0004=-*\u0015AC:ueV\u001cG/\u001e:fmU\t!\u0010E\u0002\u001fW!\u000b!b\u001d;sk\u000e$XO]38+\u0005i\bc\u0001\u0010,\u0017\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005\u0005\u0001c\u0001\u0010,\u001d\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005\u001d\u0001c\u0001\u0010,#\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\ti\u0001E\u0002\u001fWQ\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\u0003\t\u0004=-:\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0007\u0011\u0007yY#,A\u0006tiJ,8\r^;sKF\u001aTCAA\u0010!\rq2&X\u0001\u0006K6\u0004H/_\u000b\u0002]\u0001"
)
public interface MonoidProduct13 extends Monoid, SemigroupProduct13 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   Monoid structure8();

   Monoid structure9();

   Monoid structure10();

   Monoid structure11();

   Monoid structure12();

   Monoid structure13();

   // $FF: synthetic method
   static Tuple13 empty$(final MonoidProduct13 $this) {
      return $this.empty();
   }

   default Tuple13 empty() {
      return new Tuple13(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty(), this.structure9().empty(), this.structure10().empty(), this.structure11().empty(), this.structure12().empty(), this.structure13().empty());
   }

   static void $init$(final MonoidProduct13 $this) {
   }
}
