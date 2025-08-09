package breeze.generic;

import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanZipMapValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dq!\u0002\u0005\n\u0011\u0003qa!\u0002\t\n\u0011\u0003\t\u0002\"\u0002\u0010\u0002\t\u0003y\u0002\"\u0002\u0011\u0002\t\u0007\t\u0003\"\u0002\u001d\u0002\t\u0007I\u0004\"\u0002%\u0002\t\u0007I\u0005\"B1\u0002\t\u0007\u0011\u0007\"\u0002:\u0002\t\u0007\u0019\u0018\u0001D,sCB\u0004X\rZ+Gk:\u001c'B\u0001\u0006\f\u0003\u001d9WM\\3sS\u000eT\u0011\u0001D\u0001\u0007EJ,WM_3\u0004\u0001A\u0011q\"A\u0007\u0002\u0013\taqK]1qa\u0016$WKR;oGN!\u0011A\u0005\r\u001c!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0011q\"G\u0005\u00035%\u0011Q!\u0016$v]\u000e\u0004\"a\u0004\u000f\n\u0005uI!aE,sCB\u0004X\rZ+Gk:\u001cGj\\<Qe&|\u0017A\u0002\u001fj]&$h\bF\u0001\u000f\u0003-\u0019\u0018.\u001c9mK\u0006\u0003\b\u000f\\=\u0016\u0007\tbc'F\u0001$!\u0015!Se\n\u00166\u001b\u0005\t\u0011B\u0001\u0014\u001a\u0005\u0015IU\u000e\u001d73!\u0011y\u0001FK\u001b\n\u0005%J!!D,sCB\u0004X\rZ+Gk:\u001c\u0017\u0007\u0005\u0002,Y1\u0001A!B\u0017\u0004\u0005\u0004q#AA!2#\ty#\u0007\u0005\u0002\u0014a%\u0011\u0011\u0007\u0006\u0002\b\u001d>$\b.\u001b8h!\t\u00192'\u0003\u00025)\t\u0019\u0011I\\=\u0011\u0005-2D!B\u001c\u0004\u0005\u0004q#!\u0001*\u0002\u0019MLW\u000e\u001d7f\u0003B\u0004H.\u001f\u001a\u0016\ti\u0012EiR\u000b\u0002wA1A\u0005\u0010 B\u0007\u001aK!!P\r\u0003\u000b%k\u0007\u000f\\\u001a\u0011\u000b=y\u0014i\u0011$\n\u0005\u0001K!!D,sCB\u0004X\rZ+Gk:\u001c'\u0007\u0005\u0002,\u0005\u0012)Q\u0006\u0002b\u0001]A\u00111\u0006\u0012\u0003\u0006\u000b\u0012\u0011\rA\f\u0002\u0003\u0003J\u0002\"aK$\u0005\u000b]\"!\u0019\u0001\u0018\u0002\r\u0005\u0004\b\u000f\\=2+\u0015Q%K\u0014)V)\tYu\u000bE\u0003%K1\u000bF\u000b\u0005\u0003\u0010Q5{\u0005CA\u0016O\t\u0015iSA1\u0001/!\tY\u0003\u000bB\u00038\u000b\t\u0007a\u0006\u0005\u0002,%\u0012)1+\u0002b\u0001]\t\ta\u000b\u0005\u0002,+\u0012)a+\u0002b\u0001]\t\u0011aK\r\u0005\u00061\u0016\u0001\u001d!W\u0001\u0004G64\bC\u0002.`#6{E+D\u0001\\\u0015\taV,A\u0004tkB\u0004xN\u001d;\u000b\u0005y[\u0011A\u00027j]\u0006dw-\u0003\u0002a7\na1)\u00198NCB4\u0016\r\\;fg\u00061\u0011\r\u001d9msJ*RaY6hS6$\"\u0001\u001a8\u0011\r\u0011bTM\u001b6m!\u0015yqH\u001a4i!\tYs\rB\u0003.\r\t\u0007a\u0006\u0005\u0002,S\u0012)qG\u0002b\u0001]A\u00111f\u001b\u0003\u0006'\u001a\u0011\rA\f\t\u0003W5$QA\u0016\u0004C\u00029BQ\u0001\u0017\u0004A\u0004=\u0004bA\u00179kM\"d\u0017BA9\\\u0005=\u0019\u0015M\u001c.ja6\u000b\u0007OV1mk\u0016\u001c\u0018aB1qa2L('Y\u000b\bizD(\u0010`A\u0001)\r)\u00181\u0001\t\u0007Iq2X0_@\u0011\u000b=yt/_>\u0011\u0005-BH!B\u0017\b\u0005\u0004q\u0003CA\u0016{\t\u0015)uA1\u0001/!\tYC\u0010B\u00038\u000f\t\u0007a\u0006\u0005\u0002,}\u0012)1k\u0002b\u0001]A\u00191&!\u0001\u0005\u000bY;!\u0019\u0001\u0018\t\ra;\u00019AA\u0003!\u0019Qv,`<|\u007f\u0002"
)
public final class WrappedUFunc {
   public static UFunc.UImpl3 apply2a(final CanMapValues cmv) {
      return WrappedUFunc$.MODULE$.apply2a(cmv);
   }

   public static UFunc.UImpl3 apply2(final CanZipMapValues cmv) {
      return WrappedUFunc$.MODULE$.apply2(cmv);
   }

   public static UFunc.UImpl2 apply1(final CanMapValues cmv) {
      return WrappedUFunc$.MODULE$.apply1(cmv);
   }

   public static UFunc.UImpl3 simpleApply2() {
      return WrappedUFunc$.MODULE$.simpleApply2();
   }

   public static UFunc.UImpl2 simpleApply() {
      return WrappedUFunc$.MODULE$.simpleApply();
   }

   public static UFunc.UImpl3 apply2b(final CanMapValues cmv) {
      return WrappedUFunc$.MODULE$.apply2b(cmv);
   }

   public static Object withSink(final Object s) {
      return WrappedUFunc$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return WrappedUFunc$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return WrappedUFunc$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return WrappedUFunc$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return WrappedUFunc$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return WrappedUFunc$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return WrappedUFunc$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return WrappedUFunc$.MODULE$.apply(v, impl);
   }
}
