package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.ScalarOf;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mr!B\u0005\u000b\u0011\u0003ya!B\t\u000b\u0011\u0003\u0011\u0002\"\u0002\u0012\u0002\t\u0003\u0019\u0003\"\u0002\u0013\u0002\t\u0007)\u0003\"\u0002'\u0002\t\u0007i\u0005\"\u00023\u0002\t\u0007)\u0007\"\u0002:\u0002\t\u0007\u0019\b\"\u0002@\u0002\t\u0007y\bbBA\f\u0003\u0011\r\u0011\u0011D\u0001\n]>\u0014X.\u00197ju\u0016T!a\u0003\u0007\u0002\r1Lg.\u00197h\u0015\u0005i\u0011A\u00022sK\u0016TXm\u0001\u0001\u0011\u0005A\tQ\"\u0001\u0006\u0003\u00139|'/\\1mSj,7\u0003B\u0001\u00143}\u0001\"\u0001F\f\u000e\u0003UQ\u0011AF\u0001\u0006g\u000e\fG.Y\u0005\u00031U\u0011a!\u00118z%\u00164\u0007C\u0001\u000e\u001e\u001b\u0005Y\"B\u0001\u000f\r\u0003\u001d9WM\\3sS\u000eL!AH\u000e\u0003\u000bU3UO\\2\u0011\u0005A\u0001\u0013BA\u0011\u000b\u0005AqwN]7bY&TX\rT8x!JLw.\u0001\u0004=S:LGO\u0010\u000b\u0002\u001f\u0005\u0019bn\u001c:nC2L'0\u001a#pk\ndW-S7qYV\u0019a%\f\u001e\u0015\u0007\u001djd\tE\u0003)S-2\u0014(D\u0001\u0002\u0013\tQSDA\u0003J[Bd'\u0007\u0005\u0002-[1\u0001A!\u0002\u0018\u0004\u0005\u0004y#!\u0001+\u0012\u0005A\u001a\u0004C\u0001\u000b2\u0013\t\u0011TCA\u0004O_RD\u0017N\\4\u0011\u0005Q!\u0014BA\u001b\u0016\u0005\r\te.\u001f\t\u0003)]J!\u0001O\u000b\u0003\r\u0011{WO\u00197f!\ta#\bB\u0003<\u0007\t\u0007AHA\u0001V#\tY3\u0007C\u0003?\u0007\u0001\u000fq(A\u0002eSZ\u0004R\u0001Q\u0015,mer!!\u0011#\u000e\u0003\tS!a\u0011\u0006\u0002\u0013=\u0004XM]1u_J\u001c\u0018BA#C\u0003\u0015y\u0005\u000fR5w\u0011\u001595\u0001q\u0001I\u0003\u001d\u0019\u0017M\u001c(pe6\u0004R!S\u0015,mYr!\u0001\u0005&\n\u0005-S\u0011\u0001\u00028pe6\f!C\\8s[\u0006d\u0017N_3GY>\fG/S7qYV\u0019a*\u0015,\u0015\t=C\u0006M\u0019\t\u0006Q%\u0002&+\u0016\t\u0003YE#QA\f\u0003C\u0002=\u0002\"\u0001F*\n\u0005Q+\"!\u0002$m_\u0006$\bC\u0001\u0017W\t\u0015YDA1\u0001X#\t\u00016\u0007C\u0003Z\t\u0001\u000f!,\u0001\u0005tG\u0006d\u0017M](g!\u0011Yf\f\u0015*\u000e\u0003qS!!\u0018\u0006\u0002\u000fM,\b\u000f]8si&\u0011q\f\u0018\u0002\t'\u000e\fG.\u0019:PM\")a\b\u0002a\u0002CB)\u0001)\u000b)S+\")q\t\u0002a\u0002GB)\u0011*\u000b)7m\u0005Qbn\u001c:nC2L'0Z%o!2\f7-\u001a#pk\ndW-S7qYV\u0019am\u001b9\u0015\u0007\u001ddg\u000e\u0005\u0003)Q*4\u0014BA5\u001e\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d73!\ta3\u000eB\u0003/\u000b\t\u0007q\u0006C\u0003?\u000b\u0001\u000fQ\u000e\u0005\u0003AQ*4\u0004\"B$\u0006\u0001\by\u0007#B%*UZ2D!B\u001e\u0006\u0005\u0004\t\u0018C\u000164\u0003eqwN]7bY&TX-\u00138QY\u0006\u001cWM\u00127pCRLU\u000e\u001d7\u0016\u0007Q<H\u0010F\u0002vqj\u0004B\u0001\u000b5w%B\u0011Af\u001e\u0003\u0006]\u0019\u0011\ra\f\u0005\u0006}\u0019\u0001\u001d!\u001f\t\u0005\u0001\"4(\u000bC\u0003H\r\u0001\u000f1\u0010E\u0003JSY\u0014&\u000bB\u0003<\r\t\u0007Q0\u0005\u0002wg\u0005ian\u001c:nC2L'0Z%na2,b!!\u0001\u0002\f\u0005=A\u0003BA\u0002\u0003#\u0001r\u0001KA\u0003\u0003\u0013\ti!C\u0002\u0002\bu\u0011A!S7qYB\u0019A&a\u0003\u0005\u000b9:!\u0019A\u0018\u0011\u00071\ny\u0001B\u0003<\u000f\t\u0007q\u0006C\u0004\u0002\u0014\u001d\u0001\u001d!!\u0006\u0002\t%l\u0007\u000f\u001c\t\bQ%\nIANA\u0007\u0003AqwN]7bY&TX-\u00138u\u00136\u0004H.\u0006\u0004\u0002\u001c\u0005\u0005\u00121\u0006\u000b\u0005\u0003;\ty\u0003\u0005\u0005)S\u0005}\u00111EA\u0015!\ra\u0013\u0011\u0005\u0003\u0006]!\u0011\ra\f\t\u0004)\u0005\u0015\u0012bAA\u0014+\t\u0019\u0011J\u001c;\u0011\u00071\nY\u0003\u0002\u0004<\u0011\t\u0007\u0011QF\t\u0004\u0003?\u0019\u0004bBA\n\u0011\u0001\u000f\u0011\u0011\u0007\t\bQ%\nyBNA\u0015\u0001"
)
public final class normalize {
   public static UFunc.UImpl2 normalizeIntImpl(final UFunc.UImpl2 impl) {
      return normalize$.MODULE$.normalizeIntImpl(impl);
   }

   public static UFunc.UImpl normalizeImpl(final UFunc.UImpl2 impl) {
      return normalize$.MODULE$.normalizeImpl(impl);
   }

   public static UFunc.InPlaceImpl2 normalizeInPlaceFloatImpl(final UFunc.InPlaceImpl2 div, final UFunc.UImpl2 canNorm) {
      return normalize$.MODULE$.normalizeInPlaceFloatImpl(div, canNorm);
   }

   public static UFunc.InPlaceImpl2 normalizeInPlaceDoubleImpl(final UFunc.InPlaceImpl2 div, final UFunc.UImpl2 canNorm) {
      return normalize$.MODULE$.normalizeInPlaceDoubleImpl(div, canNorm);
   }

   public static UFunc.UImpl2 normalizeFloatImpl(final ScalarOf scalarOf, final UFunc.UImpl2 div, final UFunc.UImpl2 canNorm) {
      return normalize$.MODULE$.normalizeFloatImpl(scalarOf, div, canNorm);
   }

   public static UFunc.UImpl2 normalizeDoubleImpl(final UFunc.UImpl2 div, final UFunc.UImpl2 canNorm) {
      return normalize$.MODULE$.normalizeDoubleImpl(div, canNorm);
   }

   public static UFunc.UImpl normalizeImplForFloat(final UFunc.UImpl2 impl) {
      return normalize$.MODULE$.normalizeImplForFloat(impl);
   }

   public static Object withSink(final Object s) {
      return normalize$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return normalize$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return normalize$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return normalize$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return normalize$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return normalize$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return normalize$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return normalize$.MODULE$.apply(v, impl);
   }
}
