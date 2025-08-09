package spire.std;

import cats.kernel.Monoid;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!4\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0004\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u0002!\ta\u001a\u0002\u000f\u001b>tw.\u001b3Qe>$Wo\u0019;8\u0015\taQ\"A\u0002ti\u0012T\u0011AD\u0001\u0006gBL'/Z\u000b\t!5:$(\u0010!D\rN!\u0001!E\fI!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0019\u0001$\n\u0015\u000f\u0005e\u0011cB\u0001\u000e!\u001d\tYr$D\u0001\u001d\u0015\tib$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005q\u0011BA\u0011\u000e\u0003\u001d\tGnZ3ce\u0006L!a\t\u0013\u0002\u000fA\f7m[1hK*\u0011\u0011%D\u0005\u0003M\u001d\u0012a!T8o_&$'BA\u0012%!%\u0011\u0012f\u000b\u001c:y}\u0012U)\u0003\u0002+'\t1A+\u001e9mK^\u0002\"\u0001L\u0017\r\u0001\u0011)a\u0006\u0001b\u0001_\t\t\u0011)\u0005\u00021gA\u0011!#M\u0005\u0003eM\u0011qAT8uQ&tw\r\u0005\u0002\u0013i%\u0011Qg\u0005\u0002\u0004\u0003:L\bC\u0001\u00178\t\u0015A\u0004A1\u00010\u0005\u0005\u0011\u0005C\u0001\u0017;\t\u0015Y\u0004A1\u00010\u0005\u0005\u0019\u0005C\u0001\u0017>\t\u0015q\u0004A1\u00010\u0005\u0005!\u0005C\u0001\u0017A\t\u0015\t\u0005A1\u00010\u0005\u0005)\u0005C\u0001\u0017D\t\u0015!\u0005A1\u00010\u0005\u00051\u0005C\u0001\u0017G\t\u00159\u0005A1\u00010\u0005\u00059\u0005#C%KWYJDh\u0010\"F\u001b\u0005Y\u0011BA&\f\u0005E\u0019V-\\5he>,\b\u000f\u0015:pIV\u001cGoN\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00039\u0003\"AE(\n\u0005A\u001b\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005\u0019\u0006c\u0001\r&W\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003Y\u00032\u0001G\u00137\u0003)\u0019HO];diV\u0014XmM\u000b\u00023B\u0019\u0001$J\u001d\u0002\u0015M$(/^2ukJ,G'F\u0001]!\rAR\u0005P\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#A0\u0011\u0007a)s(\u0001\u0006tiJ,8\r^;sKZ*\u0012A\u0019\t\u00041\u0015\u0012\u0015AC:ueV\u001cG/\u001e:foU\tQ\rE\u0002\u0019K\u0015\u000bQ!Z7qif,\u0012\u0001\u000b"
)
public interface MonoidProduct7 extends Monoid, SemigroupProduct7 {
   Monoid structure1();

   Monoid structure2();

   Monoid structure3();

   Monoid structure4();

   Monoid structure5();

   Monoid structure6();

   Monoid structure7();

   // $FF: synthetic method
   static Tuple7 empty$(final MonoidProduct7 $this) {
      return $this.empty();
   }

   default Tuple7 empty() {
      return new Tuple7(this.structure1().empty(), this.structure2().empty(), this.structure3().empty(), this.structure4().empty(), this.structure5().empty(), this.structure6().empty(), this.structure7().empty());
   }

   static void $init$(final MonoidProduct7 $this) {
   }
}
