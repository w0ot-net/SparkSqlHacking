package cats.kernel.instances;

import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0004\u0018\u0001\t\u0007I1\u0001\r\u0003!\t{w\u000e\\3b]&s7\u000f^1oG\u0016\u001c(BA\u0003\u0007\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\b\u0011\u000511.\u001a:oK2T\u0011!C\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0005\u0001a\u0001CA\u0007\u0011\u001b\u0005q!\"A\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Eq!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002)A\u0011Q\"F\u0005\u0003-9\u0011A!\u00168ji\u0006a2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012|%\u000fZ3s\r>\u0014(i\\8mK\u0006tW#A\r\u0013\tia2E\n\u0004\u00057\u0001\u0001\u0011D\u0001\u0007=e\u00164\u0017N\\3nK:$h\bE\u0002\u001e=\u0001j\u0011AB\u0005\u0003?\u0019\u0011Qa\u0014:eKJ\u0004\"!D\u0011\n\u0005\tr!a\u0002\"p_2,\u0017M\u001c\t\u0004;\u0011\u0002\u0013BA\u0013\u0007\u0005\u0011A\u0015m\u001d5\u0011\u0007u9\u0003%\u0003\u0002)\r\t\t\"i\\;oI\u0016$WI\\;nKJ\f'\r\\3"
)
public interface BooleanInstances {
   void cats$kernel$instances$BooleanInstances$_setter_$catsKernelStdOrderForBoolean_$eq(final Order x$1);

   Order catsKernelStdOrderForBoolean();

   static void $init$(final BooleanInstances $this) {
      $this.cats$kernel$instances$BooleanInstances$_setter_$catsKernelStdOrderForBoolean_$eq(new BooleanOrder());
   }
}
