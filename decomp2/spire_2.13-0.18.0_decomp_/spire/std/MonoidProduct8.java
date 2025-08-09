package spire.std;

import cats.kernel.Monoid;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0005\u0005\u0006!\u0002!\t!\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u00021\u0019\u0001\u0018\u0005\u0006=\u00021\u0019a\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u00021\u0019\u0001\u001b\u0005\u0006U\u00021\u0019a\u001b\u0005\u0006[\u0002!\tA\u001c\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;9\u0015\tia\"A\u0002ti\u0012T\u0011aD\u0001\u0006gBL'/Z\u000b\n#9B4HP!E\u000f*\u001bB\u0001\u0001\n\u0019\u0019B\u00111CF\u0007\u0002))\tQ#A\u0003tG\u0006d\u0017-\u0003\u0002\u0018)\t1\u0011I\\=SK\u001a\u00042!\u0007\u0014*\u001d\tQ2E\u0004\u0002\u001cC9\u0011A\u0004I\u0007\u0002;)\u0011adH\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq\"\u0003\u0002#\u001d\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u0013&\u0003\u001d\u0001\u0018mY6bO\u0016T!A\t\b\n\u0005\u001dB#AB'p]>LGM\u0003\u0002%KAQ1C\u000b\u00178uu\u00025IR%\n\u0005-\"\"A\u0002+va2,\u0007\b\u0005\u0002.]1\u0001A!B\u0018\u0001\u0005\u0004\u0001$!A!\u0012\u0005E\"\u0004CA\n3\u0013\t\u0019DCA\u0004O_RD\u0017N\\4\u0011\u0005M)\u0014B\u0001\u001c\u0015\u0005\r\te.\u001f\t\u0003[a\"Q!\u000f\u0001C\u0002A\u0012\u0011A\u0011\t\u0003[m\"Q\u0001\u0010\u0001C\u0002A\u0012\u0011a\u0011\t\u0003[y\"Qa\u0010\u0001C\u0002A\u0012\u0011\u0001\u0012\t\u0003[\u0005#QA\u0011\u0001C\u0002A\u0012\u0011!\u0012\t\u0003[\u0011#Q!\u0012\u0001C\u0002A\u0012\u0011A\u0012\t\u0003[\u001d#Q\u0001\u0013\u0001C\u0002A\u0012\u0011a\u0012\t\u0003[)#Qa\u0013\u0001C\u0002A\u0012\u0011\u0001\u0013\t\u000b\u001b:csGO\u001fA\u0007\u001aKU\"\u0001\u0007\n\u0005=c!!E*f[&<'o\\;q!J|G-^2uq\u00051A%\u001b8ji\u0012\"\u0012A\u0015\t\u0003'MK!\u0001\u0016\u000b\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#A,\u0011\u0007e1C&\u0001\u0006tiJ,8\r^;sKJ*\u0012A\u0017\t\u00043\u0019:\u0014AC:ueV\u001cG/\u001e:fgU\tQ\fE\u0002\u001aMi\n!b\u001d;sk\u000e$XO]35+\u0005\u0001\u0007cA\r'{\u0005Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0003\r\u00042!\u0007\u0014A\u0003)\u0019HO];diV\u0014XMN\u000b\u0002MB\u0019\u0011DJ\"\u0002\u0015M$(/^2ukJ,w'F\u0001j!\rIbER\u0001\u000bgR\u0014Xo\u0019;ve\u0016DT#\u00017\u0011\u0007e1\u0013*A\u0003f[B$\u00180F\u0001*\u0001"
)
public interface MonoidProduct8 extends Monoid, SemigroupProduct8 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   Monoid structure8();

   // $FF: synthetic method
   static Tuple8 empty$(final MonoidProduct8 $this) {
      return $this.empty();
   }

   default Tuple8 empty() {
      return new Tuple8(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty(), this.structure8().empty());
   }

   static void $init$(final MonoidProduct8 $this) {
   }
}
