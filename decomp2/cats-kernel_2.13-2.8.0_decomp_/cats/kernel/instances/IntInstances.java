package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f)\u0002!\u0019!C\u0002W\ta\u0011J\u001c;J]N$\u0018M\\2fg*\u0011aaB\u0001\nS:\u001cH/\u00198dKNT!\u0001C\u0005\u0002\r-,'O\\3m\u0015\u0005Q\u0011\u0001B2biN\u001c\u0001a\u0005\u0002\u0001\u001bA\u0011a\"E\u0007\u0002\u001f)\t\u0001#A\u0003tG\u0006d\u0017-\u0003\u0002\u0013\u001f\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#A\u000b\u0011\u000591\u0012BA\f\u0010\u0005\u0011)f.\u001b;\u00021\r\fGo]&fe:,Gn\u0015;e\u001fJ$WM\u001d$pe&sG/F\u0001\u001b%\u0011YR\u0004J\u0014\u0007\tq\u0001\u0001A\u0007\u0002\ryI,g-\u001b8f[\u0016tGO\u0010\t\u0004=}\tS\"A\u0004\n\u0005\u0001:!!B(sI\u0016\u0014\bC\u0001\b#\u0013\t\u0019sBA\u0002J]R\u00042AH\u0013\"\u0013\t1sA\u0001\u0003ICND\u0007c\u0001\u0010)C%\u0011\u0011f\u0002\u0002\u0012\u0005>,h\u000eZ3e\u000b:,X.\u001a:bE2,\u0017\u0001G2biN\\UM\u001d8fYN#Hm\u0012:pkB4uN]%oiV\tA\u0006E\u0002\u001f[\u0005J!AL\u0004\u0003!\r{W.\\;uCRLg/Z$s_V\u0004\b"
)
public interface IntInstances {
   void cats$kernel$instances$IntInstances$_setter_$catsKernelStdOrderForInt_$eq(final Order x$1);

   void cats$kernel$instances$IntInstances$_setter_$catsKernelStdGroupForInt_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForInt();

   CommutativeGroup catsKernelStdGroupForInt();

   static void $init$(final IntInstances $this) {
      $this.cats$kernel$instances$IntInstances$_setter_$catsKernelStdOrderForInt_$eq(new IntOrder());
      $this.cats$kernel$instances$IntInstances$_setter_$catsKernelStdGroupForInt_$eq(new IntGroup());
   }
}
