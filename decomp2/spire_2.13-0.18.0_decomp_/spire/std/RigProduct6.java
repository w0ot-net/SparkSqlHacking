package spire.std;

import algebra.ring.Rig;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006\u0011\u0002!\t!\u0013\u0005\u0006\u001b\u00021\u0019A\u0014\u0005\u0006!\u00021\u0019!\u0015\u0005\u0006'\u00021\u0019\u0001\u0016\u0005\u0006-\u00021\u0019a\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u0002!\t\u0001\u0019\u0002\f%&<\u0007K]8ek\u000e$hG\u0003\u0002\f\u0019\u0005\u00191\u000f\u001e3\u000b\u00035\tQa\u001d9je\u0016,ra\u0004\u00177sqz$i\u0005\u0003\u0001!Y!\u0005CA\t\u0015\u001b\u0005\u0011\"\"A\n\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0011\"AB!osJ+g\rE\u0002\u0018I\u001dr!\u0001G\u0011\u000f\u0005eybB\u0001\u000e\u001f\u001b\u0005Y\"B\u0001\u000f\u001e\u0003\u0019a$o\\8u}\r\u0001\u0011\"A\u0007\n\u0005\u0001b\u0011aB1mO\u0016\u0014'/Y\u0005\u0003E\r\nq\u0001]1dW\u0006<WM\u0003\u0002!\u0019%\u0011QE\n\u0002\u0004%&<'B\u0001\u0012$!!\t\u0002FK\u001b9wy\n\u0015BA\u0015\u0013\u0005\u0019!V\u000f\u001d7fmA\u00111\u0006\f\u0007\u0001\t\u0015i\u0003A1\u0001/\u0005\u0005\t\u0015CA\u00183!\t\t\u0002'\u0003\u00022%\t9aj\u001c;iS:<\u0007CA\t4\u0013\t!$CA\u0002B]f\u0004\"a\u000b\u001c\u0005\u000b]\u0002!\u0019\u0001\u0018\u0003\u0003\t\u0003\"aK\u001d\u0005\u000bi\u0002!\u0019\u0001\u0018\u0003\u0003\r\u0003\"a\u000b\u001f\u0005\u000bu\u0002!\u0019\u0001\u0018\u0003\u0003\u0011\u0003\"aK \u0005\u000b\u0001\u0003!\u0019\u0001\u0018\u0003\u0003\u0015\u0003\"a\u000b\"\u0005\u000b\r\u0003!\u0019\u0001\u0018\u0003\u0003\u0019\u0003\u0002\"\u0012$+kaZd(Q\u0007\u0002\u0015%\u0011qI\u0003\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;diZ\na\u0001J5oSR$C#\u0001&\u0011\u0005EY\u0015B\u0001'\u0013\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001P!\r9BEK\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#\u0001*\u0011\u0007]!S'\u0001\u0006tiJ,8\r^;sKN*\u0012!\u0016\t\u0004/\u0011B\u0014AC:ueV\u001cG/\u001e:fiU\t\u0001\fE\u0002\u0018Im\n!b\u001d;sk\u000e$XO]36+\u0005Y\u0006cA\f%}\u0005Q1\u000f\u001e:vGR,(/\u001a\u001c\u0016\u0003y\u00032a\u0006\u0013B\u0003\ryg.Z\u000b\u0002O\u0001"
)
public interface RigProduct6 extends Rig, SemiringProduct6 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   Rig structure6();

   // $FF: synthetic method
   static Tuple6 one$(final RigProduct6 $this) {
      return $this.one();
   }

   default Tuple6 one() {
      return new Tuple6(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one());
   }

   static void $init$(final RigProduct6 $this) {
   }
}
