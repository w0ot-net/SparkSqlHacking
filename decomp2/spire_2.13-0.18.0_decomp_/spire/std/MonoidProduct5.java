package spire.std;

import cats.kernel.Monoid;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0004\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0013\u00021\u0019A\u0013\u0005\u0006\u0019\u00021\u0019!\u0014\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u0002!\t!\u0017\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;6\u0015\tQ1\"A\u0002ti\u0012T\u0011\u0001D\u0001\u0006gBL'/Z\u000b\u0007\u001d-*\u0004h\u000f \u0014\t\u0001yQ\u0003\u0011\t\u0003!Mi\u0011!\u0005\u0006\u0002%\u0005)1oY1mC&\u0011A#\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0007Y\u0019cE\u0004\u0002\u0018A9\u0011\u0001D\b\b\u00033ui\u0011A\u0007\u0006\u00037q\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002\u0019%\u0011qdC\u0001\bC2<WM\u0019:b\u0013\t\t#%A\u0004qC\u000e\\\u0017mZ3\u000b\u0005}Y\u0011B\u0001\u0013&\u0005\u0019iuN\\8jI*\u0011\u0011E\t\t\b!\u001dJCg\u000e\u001e>\u0013\tA\u0013C\u0001\u0004UkBdW-\u000e\t\u0003U-b\u0001\u0001B\u0003-\u0001\t\u0007QFA\u0001B#\tq\u0013\u0007\u0005\u0002\u0011_%\u0011\u0001'\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0001\"'\u0003\u00024#\t\u0019\u0011I\\=\u0011\u0005)*D!\u0002\u001c\u0001\u0005\u0004i#!\u0001\"\u0011\u0005)BD!B\u001d\u0001\u0005\u0004i#!A\"\u0011\u0005)ZD!\u0002\u001f\u0001\u0005\u0004i#!\u0001#\u0011\u0005)rD!B \u0001\u0005\u0004i#!A#\u0011\u000f\u0005\u0013\u0015\u0006N\u001c;{5\t\u0011\"\u0003\u0002D\u0013\t\t2+Z7jOJ|W\u000f\u001d)s_\u0012,8\r^\u001b\u0002\r\u0011Jg.\u001b;%)\u00051\u0005C\u0001\tH\u0013\tA\u0015C\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\nE\u0002\u0017G%\n!b\u001d;sk\u000e$XO]33+\u0005q\u0005c\u0001\f$i\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003E\u00032AF\u00128\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002)B\u0019ac\t\u001e\u0002\u0015M$(/^2ukJ,W'F\u0001X!\r12%P\u0001\u0006K6\u0004H/_\u000b\u0002M\u0001"
)
public interface MonoidProduct5 extends Monoid, SemigroupProduct5 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   // $FF: synthetic method
   static Tuple5 empty$(final MonoidProduct5 $this) {
      return $this.empty();
   }

   default Tuple5 empty() {
      return new Tuple5(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty());
   }

   static void $init$(final MonoidProduct5 $this) {
   }
}
