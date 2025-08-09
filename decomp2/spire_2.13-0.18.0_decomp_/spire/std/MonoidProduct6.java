package spire.std;

import cats.kernel.Monoid;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u0002!\t\u0001\u0019\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;7\u0015\tYA\"A\u0002ti\u0012T\u0011!D\u0001\u0006gBL'/Z\u000b\b\u001f12\u0014\bP C'\u0011\u0001\u0001C\u0006#\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\r9Be\n\b\u00031\u0005r!!G\u0010\u000f\u0005iqR\"A\u000e\u000b\u0005qi\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u00035I!\u0001\t\u0007\u0002\u000f\u0005dw-\u001a2sC&\u0011!eI\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0001C\"\u0003\u0002&M\t1Qj\u001c8pS\u0012T!AI\u0012\u0011\u0011EA#&\u000e\u001d<}\u0005K!!\u000b\n\u0003\rQ+\b\u000f\\37!\tYC\u0006\u0004\u0001\u0005\u000b5\u0002!\u0019\u0001\u0018\u0003\u0003\u0005\u000b\"a\f\u001a\u0011\u0005E\u0001\u0014BA\u0019\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!E\u001a\n\u0005Q\u0012\"aA!osB\u00111F\u000e\u0003\u0006o\u0001\u0011\rA\f\u0002\u0002\u0005B\u00111&\u000f\u0003\u0006u\u0001\u0011\rA\f\u0002\u0002\u0007B\u00111\u0006\u0010\u0003\u0006{\u0001\u0011\rA\f\u0002\u0002\tB\u00111f\u0010\u0003\u0006\u0001\u0002\u0011\rA\f\u0002\u0002\u000bB\u00111F\u0011\u0003\u0006\u0007\u0002\u0011\rA\f\u0002\u0002\rBAQI\u0012\u00166qmr\u0014)D\u0001\u000b\u0013\t9%BA\tTK6LwM]8vaB\u0013x\u000eZ;diZ\na\u0001J5oSR$C#\u0001&\u0011\u0005EY\u0015B\u0001'\u0013\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001P!\r9BEK\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#\u0001*\u0011\u0007]!S'\u0001\u0006tiJ,8\r^;sKN*\u0012!\u0016\t\u0004/\u0011B\u0014AC:ueV\u001cG/\u001e:fiU\t\u0001\fE\u0002\u0018Im\n!b\u001d;sk\u000e$XO]36+\u0005Y\u0006cA\f%}\u0005Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0003y\u00032a\u0006\u0013B\u0003\u0015)W\u000e\u001d;z+\u00059\u0003"
)
public interface MonoidProduct6 extends Monoid, SemigroupProduct6 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   // $FF: synthetic method
   static Tuple6 empty$(final MonoidProduct6 $this) {
      return $this.empty();
   }

   default Tuple6 empty() {
      return new Tuple6(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty());
   }

   static void $init$(final MonoidProduct6 $this) {
   }
}
