package breeze.linalg;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A;Q\u0001B\u0003\t\u0002)1Q\u0001D\u0003\t\u00025AQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0004u\taA^:qY&$(B\u0001\u0004\b\u0003\u0019a\u0017N\\1mO*\t\u0001\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\tY\u0011!D\u0001\u0006\u0005\u001918\u000f\u001d7jiN\u0019\u0011A\u0004\u000b\u0011\u0005=\u0011R\"\u0001\t\u000b\u0003E\tQa]2bY\u0006L!a\u0005\t\u0003\r\u0005s\u0017PU3g!\t)\u0002$D\u0001\u0017\u0015\t9r!A\u0004hK:,'/[2\n\u0005e1\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\u000b\u0003)IW\u000e\u001d7J]Rl\u0015\r^\u000b\u0003=!\"2a\b!I!\u0015\u0001\u0013eI\u00195\u001b\u0005\t\u0011B\u0001\u0012\u0019\u0005\u0015IU\u000e\u001d73!\rYAEJ\u0005\u0003K\u0015\u00111\u0002R3og\u0016l\u0015\r\u001e:jqB\u0011q\u0005\u000b\u0007\u0001\t\u0015I3A1\u0001+\u0005\u0005!\u0016CA\u0016/!\tyA&\u0003\u0002.!\t9aj\u001c;iS:<\u0007CA\b0\u0013\t\u0001\u0004CA\u0002B]f\u0004\"a\u0004\u001a\n\u0005M\u0002\"aA%oiB\u0019Q'P\u0012\u000f\u0005YZdBA\u001c;\u001b\u0005A$BA\u001d\n\u0003\u0019a$o\\8u}%\t\u0011#\u0003\u0002=!\u00059\u0001/Y2lC\u001e,\u0017B\u0001 @\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0006\u0003yAAq!Q\u0002\u0002\u0002\u0003\u000f!)\u0001\u0006fm&$WM\\2fI]\u00022a\u0011$'\u001b\u0005!%BA#\u0011\u0003\u001d\u0011XM\u001a7fGRL!a\u0012#\u0003\u0011\rc\u0017m]:UC\u001eDQ!S\u0002A\u0004)\u000bAA_3s_B\u00191J\u0014\u0014\u000e\u00031S!!T\u0004\u0002\u000fM$xN]1hK&\u0011q\n\u0014\u0002\u00055\u0016\u0014x\u000e"
)
public final class vsplit {
   public static UFunc.UImpl2 implIntMat(final ClassTag evidence$7, final Zero zero) {
      return vsplit$.MODULE$.implIntMat(evidence$7, zero);
   }

   public static Object withSink(final Object s) {
      return vsplit$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return vsplit$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return vsplit$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return vsplit$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return vsplit$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return vsplit$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return vsplit$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return vsplit$.MODULE$.apply(v, impl);
   }
}
