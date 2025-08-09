package algebra.instances;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0004\u001e\u0001\t\u0007I1\u0001\u0010\u0003'\tKw\rR3dS6\fG.\u00138ti\u0006t7-Z:\u000b\u0005\u00151\u0011!C5ogR\fgnY3t\u0015\u00059\u0011aB1mO\u0016\u0014'/Y\u0002\u0001'\r\u0001!\u0002\u0005\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0011\u0005E9R\"\u0001\n\u000b\u0005\u0015\u0019\"B\u0001\u000b\u0016\u0003\u0019YWM\u001d8fY*\ta#\u0001\u0003dCR\u001c\u0018BA\u0002\u0013\u0003\u0019!\u0013N\\5uIQ\t!\u0004\u0005\u0002\f7%\u0011A\u0004\u0004\u0002\u0005+:LG/A\tcS\u001e$UmY5nC2\fEnZ3ce\u0006,\u0012a\b\t\u0003A\u0005j\u0011\u0001B\u0005\u0003E\u0011\u0011\u0011CQ5h\t\u0016\u001c\u0017.\\1m\u00032<WM\u0019:b\u0001"
)
public interface BigDecimalInstances extends cats.kernel.instances.BigDecimalInstances {
   void algebra$instances$BigDecimalInstances$_setter_$bigDecimalAlgebra_$eq(final BigDecimalAlgebra x$1);

   BigDecimalAlgebra bigDecimalAlgebra();

   static void $init$(final BigDecimalInstances $this) {
      $this.algebra$instances$BigDecimalInstances$_setter_$bigDecimalAlgebra_$eq(new BigDecimalAlgebra());
   }
}
