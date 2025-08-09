package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\ta\u0001\\8hI\u0016$(B\u0001\u0004\b\u0003\u0019a\u0017N\\1mO*\t\u0001\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tY\u0011!D\u0001\u0006\u0005\u0019awn\u001a3fiN\u0019\u0011A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)\u0002$D\u0001\u0017\u0015\t9r!A\u0004hK:,'/[2\n\u0005e1\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u000b\u00035\u0019\u0017M\u001c#fiV\u001b\u0018N\\4M+V\u0011a$\n\u000b\u0003?Q\u0002B\u0001I\u0011$]5\t\u0011!\u0003\u0002#1\t!\u0011*\u001c9m!\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u001a!\u0019A\u0014\u0003\u0003Q\u000b\"\u0001K\u0016\u0011\u0005=I\u0013B\u0001\u0016\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0004\u0017\n\u00055\u0002\"aA!osB!qbL\u00192\u0013\t\u0001\u0004C\u0001\u0004UkBdWM\r\t\u0003\u001fIJ!a\r\t\u0003\r\u0011{WO\u00197f\u0011\u0015)4\u0001q\u00017\u0003\u0019aW/S7qYB!q'I\u0012?\u001d\tA4H\u0004\u0002\fs%\u0011!(B\u0001\u0003\u0019VK!\u0001P\u001f\u0002\u0013A\u0014\u0018.\\5uSZ,'B\u0001\u001e\u0006!\u0011yqf\u0010\"\u0011\u0007-\u0001\u0015'\u0003\u0002B\u000b\tYA)\u001a8tK6\u000bGO]5y!\ry1)R\u0005\u0003\tB\u0011Q!\u0011:sCf\u0004\"a\u0004$\n\u0005\u001d\u0003\"aA%oi\u0002"
)
public final class logdet {
   public static UFunc.UImpl canDetUsingLU(final UFunc.UImpl luImpl) {
      return logdet$.MODULE$.canDetUsingLU(luImpl);
   }

   public static Object withSink(final Object s) {
      return logdet$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return logdet$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return logdet$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return logdet$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return logdet$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return logdet$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return logdet$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return logdet$.MODULE$.apply(v, impl);
   }
}
