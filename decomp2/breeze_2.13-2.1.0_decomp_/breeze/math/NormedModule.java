package breeze.math;

import breeze.generic.UFunc;
import breeze.linalg.norm$;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003(\u0001\u0011\u0005\u0001\u0006C\u0003-\u0001\u0011\rQ\u0006C\u0003?\u0001\u0011\u0005qH\u0001\u0007O_JlW\rZ'pIVdWM\u0003\u0002\u0007\u000f\u0005!Q.\u0019;i\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0007-A\"e\u0005\u0003\u0001\u0019I!\u0003CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g\r\u0005\u0003\u0014)Y\tS\"A\u0003\n\u0005U)!AB'pIVdW\r\u0005\u0002\u001811\u0001A!B\r\u0001\u0005\u0004Q\"!\u0001,\u0012\u0005mq\u0002CA\u0007\u001d\u0013\tibBA\u0004O_RD\u0017N\\4\u0011\u00055y\u0012B\u0001\u0011\u000f\u0005\r\te.\u001f\t\u0003/\t\"Qa\t\u0001C\u0002i\u0011\u0011a\u0015\t\u0004'\u00152\u0012B\u0001\u0014\u0006\u0005\u0019quN]7fI\u00061A%\u001b8ji\u0012\"\u0012!\u000b\t\u0003\u001b)J!a\u000b\b\u0003\tUs\u0017\u000e^\u0001\u000bg\u000e\fG.\u0019:O_JlW#\u0001\u0018\u0011\t=*\u0014e\u000f\b\u0003aMj\u0011!\r\u0006\u0003e\u001d\ta\u0001\\5oC2<\u0017B\u0001\u001b2\u0003\u0011qwN]7\n\u0005Y:$\u0001B%na2L!\u0001O\u001d\u0003\u000bU3UO\\2\u000b\u0005i:\u0011aB4f]\u0016\u0014\u0018n\u0019\t\u0003\u001bqJ!!\u0010\b\u0003\r\u0011{WO\u00197f\u0003\u0015\u0019Gn\\:f)\u0011\u00015)R$\u0011\u00055\t\u0015B\u0001\"\u000f\u0005\u001d\u0011un\u001c7fC:DQ\u0001R\u0002A\u0002Y\t\u0011!\u0019\u0005\u0006\r\u000e\u0001\rAF\u0001\u0002E\")\u0001j\u0001a\u0001w\u0005IAo\u001c7fe\u0006t7-\u001a"
)
public interface NormedModule extends Module, Normed {
   // $FF: synthetic method
   static UFunc.UImpl scalarNorm$(final NormedModule $this) {
      return $this.scalarNorm();
   }

   default UFunc.UImpl scalarNorm() {
      return this.scalars().normImpl();
   }

   // $FF: synthetic method
   static boolean close$(final NormedModule $this, final Object a, final Object b, final double tolerance) {
      return $this.close(a, b, tolerance);
   }

   default boolean close(final Object a, final Object b, final double tolerance) {
      return BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(this.subVV().apply(a, b), this.normImpl())) <= tolerance * .MODULE$.max((double)1.0F, .MODULE$.max(BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(a, this.normImpl())), BoxesRunTime.unboxToDouble(norm$.MODULE$.apply(b, this.normImpl()))));
   }

   static void $init$(final NormedModule $this) {
   }
}
