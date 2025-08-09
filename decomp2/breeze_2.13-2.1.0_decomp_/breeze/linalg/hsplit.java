package breeze.linalg;

import breeze.generic.UFunc;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u00055<QAB\u0004\t\u000211QAD\u0004\t\u0002=AQ\u0001H\u0001\u0005\u0002uAQAH\u0001\u0005\u0004}AQAS\u0001\u0005\u0004-CQ\u0001W\u0001\u0005\u0004e\u000ba\u0001[:qY&$(B\u0001\u0005\n\u0003\u0019a\u0017N\\1mO*\t!\"\u0001\u0004ce\u0016,'0Z\u0002\u0001!\ti\u0011!D\u0001\b\u0005\u0019A7\u000f\u001d7jiN\u0019\u0011\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\r\u0005s\u0017PU3g!\t9\"$D\u0001\u0019\u0015\tI\u0012\"A\u0004hK:,'/[2\n\u0005mA\"!B+Gk:\u001c\u0017A\u0002\u001fj]&$h\bF\u0001\r\u0003)IW\u000e\u001d7J]R4VmY\u000b\u0003A)\"\"!\t\"\u0011\u000b\t\u001aSe\r\u001c\u000e\u0003\u0005I!\u0001\n\u000e\u0003\u000b%k\u0007\u000f\u001c\u001a\u0011\u000751\u0003&\u0003\u0002(\u000f\tYA)\u001a8tKZ+7\r^8s!\tI#\u0006\u0004\u0001\u0005\u000b-\u001a!\u0019\u0001\u0017\u0003\u0003Q\u000b\"!\f\u0019\u0011\u0005Eq\u0013BA\u0018\u0013\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"!E\u0019\n\u0005I\u0012\"aA!osB\u0011\u0011\u0003N\u0005\u0003kI\u00111!\u00138u!\r9t(\n\b\u0003qur!!\u000f\u001f\u000e\u0003iR!aO\u0006\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0012B\u0001 \u0013\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001Q!\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u0002?%!91iAA\u0001\u0002\b!\u0015AC3wS\u0012,gnY3%iA\u0019Q\t\u0013\u0015\u000e\u0003\u0019S!a\u0012\n\u0002\u000fI,g\r\\3di&\u0011\u0011J\u0012\u0002\t\u00072\f7o\u001d+bO\u0006Q\u0011.\u001c9m'\u0016\fh+Z2\u0016\u00051\u0003FCA'V!\u0015\u00113ET)U!\riae\u0014\t\u0003SA#Qa\u000b\u0003C\u00021\u00022a\u000e*4\u0013\t\u0019\u0016IA\u0002TKF\u00042aN O\u0011\u001d1F!!AA\u0004]\u000b!\"\u001a<jI\u0016t7-\u001a\u00136!\r)\u0005jT\u0001\u000bS6\u0004H.\u00138u\u001b\u0006$XC\u0001.a)\rY&-\u001a\t\u0006E\rb6'\u0019\t\u0004\u001bu{\u0016B\u00010\b\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\u0005%\u0002G!B\u0016\u0006\u0005\u0004a\u0003cA\u001c@9\"91-BA\u0001\u0002\b!\u0017AC3wS\u0012,gnY3%mA\u0019Q\tS0\t\u000b\u0019,\u00019A4\u0002\ti,'o\u001c\t\u0004Q.|V\"A5\u000b\u0005)L\u0011aB:u_J\fw-Z\u0005\u0003Y&\u0014AAW3s_\u0002"
)
public final class hsplit {
   public static UFunc.UImpl2 implIntMat(final ClassTag evidence$6, final Zero zero) {
      return hsplit$.MODULE$.implIntMat(evidence$6, zero);
   }

   public static UFunc.UImpl2 implSeqVec(final ClassTag evidence$5) {
      return hsplit$.MODULE$.implSeqVec(evidence$5);
   }

   public static UFunc.UImpl2 implIntVec(final ClassTag evidence$4) {
      return hsplit$.MODULE$.implIntVec(evidence$4);
   }

   public static Object withSink(final Object s) {
      return hsplit$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return hsplit$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return hsplit$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return hsplit$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return hsplit$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return hsplit$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return hsplit$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return hsplit$.MODULE$.apply(v, impl);
   }
}
