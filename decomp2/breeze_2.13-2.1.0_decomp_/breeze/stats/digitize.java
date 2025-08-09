package breeze.stats;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mq!\u0002\u0006\f\u0011\u0003\u0001b!\u0002\n\f\u0011\u0003\u0019\u0002\"\u0002\u0011\u0002\t\u0003\t\u0003\"\u0002\u0012\u0002\t\u0007\u0019\u0003\"\u0002&\u0002\t\u0007Y\u0005\"\u00024\u0002\t\u00079\u0007\"B7\u0002\t\u0007q\u0007\"\u0002;\u0002\t\u0007)\b\"B<\u0002\t\u0007A\b\"\u0002@\u0002\t\u0013y\u0018\u0001\u00033jO&$\u0018N_3\u000b\u00051i\u0011!B:uCR\u001c(\"\u0001\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"!E\u0001\u000e\u0003-\u0011\u0001\u0002Z5hSRL'0Z\n\u0004\u0003QQ\u0002CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\r\u0005\u0002\u001c=5\tAD\u0003\u0002\u001e\u001b\u00059q-\u001a8fe&\u001c\u0017BA\u0010\u001d\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t\u0001#\u0001\u0007beJ\f\u0017PV3sg&|g.F\u0002%]e\"\"!J \u0011\u000b\u0019:\u0013fN\u001e\u000e\u0003\u0005I!\u0001\u000b\u0010\u0003\u000b%k\u0007\u000f\u001c\u001a\u0011\u0007UQC&\u0003\u0002,-\t)\u0011I\u001d:bsB\u0011QF\f\u0007\u0001\t\u0015y3A1\u00011\u0005\u0005!\u0016CA\u00195!\t)\"'\u0003\u00024-\t9aj\u001c;iS:<\u0007CA\u000b6\u0013\t1dCA\u0002B]f\u00042!\u0006\u00169!\ti\u0013\bB\u0003;\u0007\t\u0007\u0001GA\u0001V!\r)\"\u0006\u0010\t\u0003+uJ!A\u0010\f\u0003\u0007%sG\u000fC\u0003A\u0007\u0001\u000f\u0011)\u0001\u0003cCN,\u0007#\u0002\u0014(\u0005\"K\u0005cA\"GY5\tAI\u0003\u0002F\u001b\u00051A.\u001b8bY\u001eL!a\u0012#\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\t\u0004\u0007\u001aC\u0004cA\"Gy\u0005qaM]8n\u0007>l\u0007/\u0019:jg>tWc\u0001'Q'R\u0019Q\n\u00161\u0011\u000b\u0019:c*U%\u0011\u0007\r3u\n\u0005\u0002.!\u0012)q\u0006\u0002b\u0001aA\u00191I\u0012*\u0011\u00055\u001aF!\u0002\u001e\u0005\u0005\u0004\u0001\u0004\"B+\u0005\u0001\b1\u0016a\u00017uKB)qkJ(S;:\u0011\u0001lW\u0007\u00023*\u0011!\fR\u0001\n_B,'/\u0019;peNL!\u0001X-\u0002\u000b=\u0003H\nV#\u0011\u0005Uq\u0016BA0\u0017\u0005\u001d\u0011un\u001c7fC:DQ!\u0019\u0003A\u0004\t\fA\u0001\u001c;V+B)1m\n*S;:\u0011\u0001\fZ\u0005\u0003Kf\u000bAa\u00149M)\u0006qa/Z2WKJ\u001c\u0018n\u001c8`\u0013:$X#\u00015\u0011\u000b\u0019:\u0013*[%\u0011\u0007\r3%\u000e\u0005\u0002\u0016W&\u0011AN\u0006\u0002\u0007\t>,(\r\\3\u0002\u001fY,7MV3sg&|gn\u0018'p]\u001e,\u0012a\u001c\t\u0006M\u001d\u0002\u0018.\u0013\t\u0004\u0007\u001a\u000b\bCA\u000bs\u0013\t\u0019hC\u0001\u0003M_:<\u0017!\u0005<fGZ+'o]5p]~#u.\u001e2mKV\ta\u000fE\u0003'O%L\u0017*\u0001\twK\u000e4VM]:j_:|f\t\\8biV\t\u0011\u0010E\u0003'OiL\u0017\nE\u0002D\rn\u0004\"!\u0006?\n\u0005u4\"!\u0002$m_\u0006$\u0018AD3se>\u00148\t[3dW\nKgn]\u000b\u0005\u0003\u0003\t\u0019\u0002\u0006\u0003\u0002\u0004\u0005UA\u0003BA\u0003\u0003\u0017\u00012!FA\u0004\u0013\r\tIA\u0006\u0002\u0005+:LG\u000fC\u0004\u0002\u000e%\u0001\u001d!a\u0004\u0002\u00051$\bcB2(\u0003#\t\t\"\u0018\t\u0004[\u0005MA!\u0002\u001e\n\u0005\u0004\u0001\u0004bBA\f\u0013\u0001\u0007\u0011\u0011D\u0001\u0005E&t7\u000f\u0005\u0003D\r\u0006E\u0001"
)
public final class digitize {
   public static UFunc.UImpl2 vecVersion_Float() {
      return digitize$.MODULE$.vecVersion_Float();
   }

   public static UFunc.UImpl2 vecVersion_Double() {
      return digitize$.MODULE$.vecVersion_Double();
   }

   public static UFunc.UImpl2 vecVersion_Long() {
      return digitize$.MODULE$.vecVersion_Long();
   }

   public static UFunc.UImpl2 vecVersion_Int() {
      return digitize$.MODULE$.vecVersion_Int();
   }

   public static UFunc.UImpl2 fromComparison(final UFunc.UImpl2 lte, final UFunc.UImpl2 ltUU) {
      return digitize$.MODULE$.fromComparison(lte, ltUU);
   }

   public static UFunc.UImpl2 arrayVersion(final UFunc.UImpl2 base) {
      return digitize$.MODULE$.arrayVersion(base);
   }

   public static Object withSink(final Object s) {
      return digitize$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return digitize$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return digitize$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return digitize$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return digitize$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return digitize$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return digitize$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return digitize$.MODULE$.apply(v, impl);
   }
}
