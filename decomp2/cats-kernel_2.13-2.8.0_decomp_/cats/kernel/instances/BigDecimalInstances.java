package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000fA\u0002!\u0019!C\u0002c\t\u0019\")[4EK\u000eLW.\u00197J]N$\u0018M\\2fg*\u0011aaB\u0001\nS:\u001cH/\u00198dKNT!\u0001C\u0005\u0002\r-,'O\\3m\u0015\u0005Q\u0011\u0001B2biN\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\u000b\u0011\u000591\u0012BA\f\u0010\u0005\u0011)f.\u001b;\u0002?\r\fGo]&fe:,Gn\u0015;e\u001fJ$WM\u001d$pe\nKw\rR3dS6\fG.F\u0001\u001b%\rYR$\f\u0004\u00059\u0001\u0001!D\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002\u001f?\u0005j\u0011aB\u0005\u0003A\u001d\u0011Qa\u0014:eKJ\u0004\"A\t\u0016\u000f\u0005\rBcB\u0001\u0013(\u001b\u0005)#B\u0001\u0014\f\u0003\u0019a$o\\8u}%\t\u0001#\u0003\u0002*\u001f\u00059\u0001/Y2lC\u001e,\u0017BA\u0016-\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\u001c\u0006\u0003S=\u00012A\b\u0018\"\u0013\tysA\u0001\u0003ICND\u0017aH2biN\\UM\u001d8fYN#Hm\u0012:pkB4uN\u001d\"jO\u0012+7-[7bYV\t!\u0007E\u0002\u001fg\u0005J!\u0001N\u0004\u0003!\r{W.\\;uCRLg/Z$s_V\u0004\b"
)
public interface BigDecimalInstances {
   void cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdOrderForBigDecimal_$eq(final Order x$1);

   void cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdGroupForBigDecimal_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForBigDecimal();

   CommutativeGroup catsKernelStdGroupForBigDecimal();

   static void $init$(final BigDecimalInstances $this) {
      $this.cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdOrderForBigDecimal_$eq(new BigDecimalOrder());
      $this.cats$kernel$instances$BigDecimalInstances$_setter_$catsKernelStdGroupForBigDecimal_$eq(new BigDecimalGroup());
   }
}
