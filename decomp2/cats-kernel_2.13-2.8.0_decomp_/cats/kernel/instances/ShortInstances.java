package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000f)\u0002!\u0019!C\u0002W\tq1\u000b[8si&s7\u000f^1oG\u0016\u001c(B\u0001\u0004\b\u0003%Ign\u001d;b]\u000e,7O\u0003\u0002\t\u0013\u000511.\u001a:oK2T\u0011AC\u0001\u0005G\u0006$8o\u0001\u0001\u0014\u0005\u0001i\u0001C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002+A\u0011aBF\u0005\u0003/=\u0011A!\u00168ji\u0006Q2-\u0019;t\u0017\u0016\u0014h.\u001a7Ti\u0012|%\u000fZ3s\r>\u00148\u000b[8siV\t!D\u0005\u0003\u001c;\u0011:c\u0001\u0002\u000f\u0001\u0001i\u0011A\u0002\u0010:fM&tW-\\3oiz\u00022AH\u0010\"\u001b\u00059\u0011B\u0001\u0011\b\u0005\u0015y%\u000fZ3s!\tq!%\u0003\u0002$\u001f\t)1\u000b[8siB\u0019a$J\u0011\n\u0005\u0019:!\u0001\u0002%bg\"\u00042A\b\u0015\"\u0013\tIsAA\tC_VtG-\u001a3F]VlWM]1cY\u0016\f!dY1ug.+'O\\3m'R$wI]8va\u001a{'o\u00155peR,\u0012\u0001\f\t\u0004=5\n\u0013B\u0001\u0018\b\u0005A\u0019u.\\7vi\u0006$\u0018N^3He>,\b\u000f"
)
public interface ShortInstances {
   void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdOrderForShort_$eq(final Order x$1);

   void cats$kernel$instances$ShortInstances$_setter_$catsKernelStdGroupForShort_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForShort();

   CommutativeGroup catsKernelStdGroupForShort();

   static void $init$(final ShortInstances $this) {
      $this.cats$kernel$instances$ShortInstances$_setter_$catsKernelStdOrderForShort_$eq(new ShortOrder());
      $this.cats$kernel$instances$ShortInstances$_setter_$catsKernelStdGroupForShort_$eq(new ShortGroup());
   }
}
