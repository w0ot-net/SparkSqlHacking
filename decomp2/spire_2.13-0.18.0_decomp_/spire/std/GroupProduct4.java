package spire.std;

import cats.kernel.Group;
import scala.Tuple4;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3\u0001b\u0002\u0005\u0011\u0002\u0007\u0005!\u0002\u0004\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u0017\u00021\u0019\u0001\u0014\u0005\u0006\u001d\u00021\u0019a\u0014\u0005\u0006#\u0002!\tA\u0015\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r\u001e\u001b\u000b\u0005%Q\u0011aA:uI*\t1\"A\u0003ta&\u0014X-F\u0003\u000eUQ:$h\u0005\u0003\u0001\u001dQa\u0004CA\b\u0013\u001b\u0005\u0001\"\"A\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005M\u0001\"AB!osJ+g\rE\u0002\u0016E\u0015r!AF\u0010\u000f\u0005]ibB\u0001\r\u001d\u001b\u0005I\"B\u0001\u000e\u001c\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0006\n\u0005yQ\u0011aB1mO\u0016\u0014'/Y\u0005\u0003A\u0005\nq\u0001]1dW\u0006<WM\u0003\u0002\u001f\u0015%\u00111\u0005\n\u0002\u0006\u000fJ|W\u000f\u001d\u0006\u0003A\u0005\u0002ba\u0004\u0014)gYJ\u0014BA\u0014\u0011\u0005\u0019!V\u000f\u001d7fiA\u0011\u0011F\u000b\u0007\u0001\t\u0015Y\u0003A1\u0001-\u0005\u0005\t\u0015CA\u00171!\tya&\u0003\u00020!\t9aj\u001c;iS:<\u0007CA\b2\u0013\t\u0011\u0004CA\u0002B]f\u0004\"!\u000b\u001b\u0005\u000bU\u0002!\u0019\u0001\u0017\u0003\u0003\t\u0003\"!K\u001c\u0005\u000ba\u0002!\u0019\u0001\u0017\u0003\u0003\r\u0003\"!\u000b\u001e\u0005\u000bm\u0002!\u0019\u0001\u0017\u0003\u0003\u0011\u0003b!\u0010 )gYJT\"\u0001\u0005\n\u0005}B!AD'p]>LG\r\u0015:pIV\u001cG\u000fN\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\t\u0003\"aD\"\n\u0005\u0011\u0003\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u00059\u0005cA\u000b#Q\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003)\u00032!\u0006\u00124\u0003)\u0019HO];diV\u0014XmM\u000b\u0002\u001bB\u0019QC\t\u001c\u0002\u0015M$(/^2ukJ,G'F\u0001Q!\r)\"%O\u0001\bS:4XM]:f)\t)3\u000bC\u0003U\r\u0001\u0007Q%\u0001\u0002ya\u0001"
)
public interface GroupProduct4 extends Group, MonoidProduct4 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   // $FF: synthetic method
   static Tuple4 inverse$(final GroupProduct4 $this, final Tuple4 x0) {
      return $this.inverse(x0);
   }

   default Tuple4 inverse(final Tuple4 x0) {
      return new Tuple4(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()));
   }

   static void $init$(final GroupProduct4 $this) {
   }
}
