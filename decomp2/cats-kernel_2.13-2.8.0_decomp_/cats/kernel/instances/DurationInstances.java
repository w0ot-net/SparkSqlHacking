package cats.kernel.instances;

import cats.kernel.CommutativeGroup;
import cats.kernel.Order;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0004\u0019\u0001\t\u0007I1A\r\t\u000fI\u0002!\u0019!C\u0002g\t\tB)\u001e:bi&|g.\u00138ti\u0006t7-Z:\u000b\u0005\u00199\u0011!C5ogR\fgnY3t\u0015\tA\u0011\"\u0001\u0004lKJtW\r\u001c\u0006\u0002\u0015\u0005!1-\u0019;t\u0007\u0001\u0019\"\u0001A\u0007\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\u0003\u0005\u0002\u000f-%\u0011qc\u0004\u0002\u0005+:LG/A\u000fdCR\u001c8*\u001a:oK2\u001cF\u000fZ(sI\u0016\u0014hi\u001c:EkJ\fG/[8o+\u0005Q\"#B\u000e\u001eS1zc\u0001\u0002\u000f\u0001\u0001i\u0011A\u0002\u0010:fM&tW-\\3oiz\u00022AH\u0010\"\u001b\u00059\u0011B\u0001\u0011\b\u0005\u0015y%\u000fZ3s!\t\u0011s%D\u0001$\u0015\t!S%\u0001\u0005ekJ\fG/[8o\u0015\t1s\"\u0001\u0006d_:\u001cWO\u001d:f]RL!\u0001K\u0012\u0003\u0011\u0011+(/\u0019;j_:\u00042A\b\u0016\"\u0013\tYsA\u0001\u0003ICND\u0007c\u0001\u0010.C%\u0011af\u0002\u0002\r\u0019><XM\u001d\"pk:$W\r\u001a\t\u0004=A\n\u0013BA\u0019\b\u00051)\u0006\u000f]3s\u0005>,h\u000eZ3e\u0003u\u0019\u0017\r^:LKJtW\r\\*uI\u001e\u0013x.\u001e9G_J$UO]1uS>tW#\u0001\u001b\u0011\u0007y)\u0014%\u0003\u00027\u000f\t\u00012i\\7nkR\fG/\u001b<f\u000fJ|W\u000f\u001d"
)
public interface DurationInstances {
   void cats$kernel$instances$DurationInstances$_setter_$catsKernelStdOrderForDuration_$eq(final Order x$1);

   void cats$kernel$instances$DurationInstances$_setter_$catsKernelStdGroupForDuration_$eq(final CommutativeGroup x$1);

   Order catsKernelStdOrderForDuration();

   CommutativeGroup catsKernelStdGroupForDuration();

   static void $init$(final DurationInstances $this) {
      $this.cats$kernel$instances$DurationInstances$_setter_$catsKernelStdOrderForDuration_$eq(new DurationOrder());
      $this.cats$kernel$instances$DurationInstances$_setter_$catsKernelStdGroupForDuration_$eq(new DurationGroup());
   }
}
