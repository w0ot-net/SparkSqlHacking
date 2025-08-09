package breeze.linalg;

import breeze.generic.UFunc;
import breeze.stats.distributions.RandBasis;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);Q!\u0003\u0006\t\u0002=1Q!\u0005\u0006\t\u0002IAQaH\u0001\u0005\u0002\u0001BQ!I\u0001\u0005\u0012\tBQ\u0001M\u0001\u0005\u0012EBq\u0001O\u0001C\u0002\u0013E\u0011\b\u0003\u0004A\u0003\u0001\u0006IA\u000f\u0005\b\u0003\u0006\u0011\r\u0011\"\u0005C\u0011\u0019I\u0015\u0001)A\u0005\u0007\u0006I!/\u00198e_6Le\u000e\u001e\u0006\u0003\u00171\ta\u0001\\5oC2<'\"A\u0007\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"\u0001E\u0001\u000e\u0003)\u0011\u0011B]1oI>l\u0017J\u001c;\u0014\u0007\u0005\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u0004!ia\u0012BA\u000e\u000b\u0005Q\u0011\u0016M\u001c3p[\u001e+g.\u001a:bi>\u0014XKR;oGB\u0011A#H\u0005\u0003=U\u00111!\u00138u\u0003\u0019a\u0014N\\5u}Q\tq\"A\u0002hK:$\"aI\u0016\u0011\u0007\u0011JC$D\u0001&\u0015\t1s%A\u0007eSN$(/\u001b2vi&|gn\u001d\u0006\u0003Q1\tQa\u001d;biNL!AK\u0013\u0003\tI\u000bg\u000e\u001a\u0005\u0006Y\r\u0001\u001d!L\u0001\u0006E\u0006\u001c\u0018n\u001d\t\u0003I9J!aL\u0013\u0003\u0013I\u000bg\u000e\u001a\"bg&\u001c\u0018\u0001C4f]J\u000bgnZ3\u0015\u0007I\"d\u0007\u0006\u0002$g!)A\u0006\u0002a\u0002[!)Q\u0007\u0002a\u00019\u0005\u0019An\\<\t\u000b]\"\u0001\u0019\u0001\u000f\u0002\t!Lw\r[\u0001\n?\u000ed\u0017m]:UC\u001e,\u0012A\u000f\t\u0004wybR\"\u0001\u001f\u000b\u0005u*\u0012a\u0002:fM2,7\r^\u0005\u0003\u007fq\u0012\u0001b\u00117bgN$\u0016mZ\u0001\u000b?\u000ed\u0017m]:UC\u001e\u0004\u0013!B0{KJ|W#A\"\u0011\u0007\u0011;E$D\u0001F\u0015\t1E\"A\u0004ti>\u0014\u0018mZ3\n\u0005!+%\u0001\u0002.fe>\faa\u0018>fe>\u0004\u0003"
)
public final class randomInt {
   public static UFunc.UImpl2 implRandomT_2DRange(final RandBasis basis) {
      return randomInt$.MODULE$.implRandomT_2DRange(basis);
   }

   public static UFunc.UImpl implRandomT_2D(final RandBasis basis) {
      return randomInt$.MODULE$.implRandomT_2D(basis);
   }

   public static UFunc.UImpl2 implRandomT_1DRange(final RandBasis basis) {
      return randomInt$.MODULE$.implRandomT_1DRange(basis);
   }

   public static UFunc.UImpl implRandomT_1D(final RandBasis basis) {
      return randomInt$.MODULE$.implRandomT_1D(basis);
   }

   public static Object apply(final RandBasis basis) {
      return randomInt$.MODULE$.apply(basis);
   }

   public static Object withSink(final Object s) {
      return randomInt$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return randomInt$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return randomInt$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return randomInt$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return randomInt$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return randomInt$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return randomInt$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return randomInt$.MODULE$.apply(v, impl);
   }
}
