package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\r;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\t\u0001\u0003^1oS6|Go\u001c#jgR\fgnY3\u000b\u0005\u00199\u0011A\u00027j]\u0006dwMC\u0001\t\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001CA\u0006\u0002\u001b\u0005)!\u0001\u0005;b]&lw\u000e^8ESN$\u0018M\\2f'\r\ta\u0002\u0006\t\u0003\u001fIi\u0011\u0001\u0005\u0006\u0002#\u0005)1oY1mC&\u00111\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0011\u0005UAR\"\u0001\f\u000b\u0005]9\u0011aB4f]\u0016\u0014\u0018nY\u0005\u00033Y\u0011Q!\u0016$v]\u000e\fa\u0001P5oSRtD#\u0001\u0006\u0002=Q\fg.[7pi>$\u0015n\u001d;b]\u000e,gI]8n\t>$\bK]8ek\u000e$Xc\u0001\u0010&_Q!q\u0004N\u001fA!\u0015\u0001\u0013e\t\u00182\u001b\u0005\t\u0011B\u0001\u0012\u0019\u0005\u0015IU\u000e\u001d73!\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u001a!\u0019A\u0014\u0003\u0003Q\u000b\"\u0001K\u0016\u0011\u0005=I\u0013B\u0001\u0016\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"a\u0004\u0017\n\u00055\u0002\"aA!osB\u0011Ae\f\u0003\u0006a\r\u0011\ra\n\u0002\u0002+B\u0011qBM\u0005\u0003gA\u0011a\u0001R8vE2,\u0007\"B\u001b\u0004\u0001\b1\u0014!\u00023piR+\u0006#B\u001c\"G9\ndB\u0001\u001d<\u001b\u0005I$B\u0001\u001e\u0006\u0003%y\u0007/\u001a:bi>\u00148/\u0003\u0002=s\u0005Qq\n]'vY&sg.\u001a:\t\u000by\u001a\u00019A \u0002\u000b\u0011|G\u000f\u0016+\u0011\u000b]\n3eI\u0019\t\u000b\u0005\u001b\u00019\u0001\"\u0002\u000b\u0011|G/V+\u0011\u000b]\ncFL\u0019"
)
public final class tanimotoDistance {
   public static UFunc.UImpl2 tanimotoDistanceFromDotProduct(final UFunc.UImpl2 dotTU, final UFunc.UImpl2 dotTT, final UFunc.UImpl2 dotUU) {
      return tanimotoDistance$.MODULE$.tanimotoDistanceFromDotProduct(dotTU, dotTT, dotUU);
   }

   public static Object withSink(final Object s) {
      return tanimotoDistance$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return tanimotoDistance$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return tanimotoDistance$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return tanimotoDistance$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return tanimotoDistance$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return tanimotoDistance$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return tanimotoDistance$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return tanimotoDistance$.MODULE$.apply(v, impl);
   }
}
