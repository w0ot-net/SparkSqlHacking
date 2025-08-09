package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\tQ\u0001\u001e:bG\u0016T!AB\u0004\u0002\r1Lg.\u00197h\u0015\u0005A\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005-\tQ\"A\u0003\u0003\u000bQ\u0014\u0018mY3\u0014\u0007\u0005qA\u0003\u0005\u0002\u0010%5\t\u0001CC\u0001\u0012\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0002C\u0001\u0004B]f\u0014VM\u001a\t\u0003+ai\u0011A\u0006\u0006\u0003/\u001d\tqaZ3oKJL7-\u0003\u0002\u001a-\t)QKR;oG\u00061A(\u001b8jiz\"\u0012AC\u0001\u001eS6\u0004Hn\u0018;sC\u000e,w,^:j]\u001e|F-[1h?\u0006tGmX:v[V!a$\n\u001d0)\ry\u0012G\u000f\t\u0005A\u0005\u001ac&D\u0001\u0002\u0013\t\u0011\u0003D\u0001\u0003J[Bd\u0007C\u0001\u0013&\u0019\u0001!QAJ\u0002C\u0002\u001d\u0012\u0011\u0001V\t\u0003Q-\u0002\"aD\u0015\n\u0005)\u0002\"a\u0002(pi\"Lgn\u001a\t\u0003\u001f1J!!\f\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002%_\u0011)\u0001g\u0001b\u0001O\t\ta\u000bC\u00033\u0007\u0001\u000f1'\u0001\u0005eS\u0006<\u0017*\u001c9m!\u0011!\u0014eI\u001c\u000f\u0005-)\u0014B\u0001\u001c\u0006\u0003\u0011!\u0017.Y4\u0011\u0005\u0011BD!B\u001d\u0004\u0005\u00049#!A+\t\u000bm\u001a\u00019\u0001\u001f\u0002\u000fM,X.S7qYB!Q(I\u001c/\u001d\tYa(\u0003\u0002@\u000b\u0005\u00191/^7"
)
public final class trace {
   public static UFunc.UImpl impl_trace_using_diag_and_sum(final UFunc.UImpl diagImpl, final UFunc.UImpl sumImpl) {
      return trace$.MODULE$.impl_trace_using_diag_and_sum(diagImpl, sumImpl);
   }

   public static Object withSink(final Object s) {
      return trace$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return trace$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return trace$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return trace$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return trace$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return trace$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return trace$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return trace$.MODULE$.apply(v, impl);
   }
}
