package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f\u001d\u0002!\u0019!C\u0002Q\tqa\t\\8bi&s7\u000f^1oG\u0016\u001c(B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0005\u0001i\u0001C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002+A\u0011aBF\u0005\u0003/=\u0011A!\u00168ji\u0006Q2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012|%\u000fZ3s\r>\u0014h\t\\8biV\t!DE\u0002\u001c;\u00112A\u0001\b\u0001\u00015\taAH]3gS:,W.\u001a8u}A\u0019adH\u0011\u000e\u0003\u001dI!\u0001I\u0004\u0003\u000b=\u0013H-\u001a:\u0011\u00059\u0011\u0013BA\u0012\u0010\u0005\u00151En\\1u!\rqR%I\u0005\u0003M\u001d\u0011A\u0001S1tQ\u0006Q2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012<%o\\;q\r>\u0014h\t\\8biV\t\u0011\u0006E\u0002\u001fU\u0005J!aK\u0004\u0003!\r{W.\\;uCRLg/Z$s_V\u0004\b"
)
public interface FloatInstances {
   void cats$kernel$instances$FloatInstances$_setter_$catsKernelStdOrderForFloat_$eq(final Order x$1);

   void cats$kernel$instances$FloatInstances$_setter_$catsKernelStdGroupForFloat_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForFloat();

   CommutativeGroup catsKernelStdGroupForFloat();

   static void $init$(final FloatInstances $this) {
      $this.cats$kernel$instances$FloatInstances$_setter_$catsKernelStdOrderForFloat_$eq(new FloatOrder());
      $this.cats$kernel$instances$FloatInstances$_setter_$catsKernelStdGroupForFloat_$eq(new FloatGroup());
   }
}
