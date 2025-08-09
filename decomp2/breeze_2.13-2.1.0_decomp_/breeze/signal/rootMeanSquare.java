package breeze.signal;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\taB]8pi6+\u0017M\\*rk\u0006\u0014XM\u0003\u0002\u0007\u000f\u000511/[4oC2T\u0011\u0001C\u0001\u0007EJ,WM_3\u0004\u0001A\u00111\"A\u0007\u0002\u000b\tq!o\\8u\u001b\u0016\fgnU9vCJ,7cA\u0001\u000f)A\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t1\u0011I\\=SK\u001a\u0004\"!\u0006\r\u000e\u0003YQ!aF\u0004\u0002\u000f\u001d,g.\u001a:jG&\u0011\u0011D\u0006\u0002\u0006+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003)\tQA]7tc\u0011+\"AH\u0013\u0015\u0007}\tt\b\u0005\u0003!C\rrcBA\u0006\u0001\u0013\t\u0011\u0003D\u0001\u0003J[Bd\u0007C\u0001\u0013&\u0019\u0001!QAJ\u0002C\u0002\u001d\u00121AV3d#\tA3\u0006\u0005\u0002\u0010S%\u0011!\u0006\u0005\u0002\b\u001d>$\b.\u001b8h!\tyA&\u0003\u0002.!\t\u0019\u0011I\\=\u0011\u0005=y\u0013B\u0001\u0019\u0011\u0005\u0019!u.\u001e2mK\")!g\u0001a\u0002g\u0005Aan\u001c:n\u00136\u0004H\u000eE\u00035u\rbdF\u0004\u00026q5\taG\u0003\u00028\u000f\u00051A.\u001b8bY\u001eL!!\u000f\u001c\u0002\t9|'/\\\u0005\u0003wa\u0011Q!S7qYJ\u0002\"aD\u001f\n\u0005y\u0002\"aA%oi\")\u0001i\u0001a\u0002\u0003\u00069A-[7J[Bd\u0007\u0003\u0002\"\"Gqr!!N\"\n\u0005\u00113\u0014a\u00013j[\u0002"
)
public final class rootMeanSquare {
   public static UFunc.UImpl rms1D(final UFunc.UImpl2 normImpl, final UFunc.UImpl dimImpl) {
      return rootMeanSquare$.MODULE$.rms1D(normImpl, dimImpl);
   }

   public static Object withSink(final Object s) {
      return rootMeanSquare$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return rootMeanSquare$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return rootMeanSquare$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return rootMeanSquare$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return rootMeanSquare$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return rootMeanSquare$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return rootMeanSquare$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return rootMeanSquare$.MODULE$.apply(v, impl);
   }
}
