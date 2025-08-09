package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u;Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0004\u0001BQ\u0001P\u0001\u0005\u0004uBQaR\u0001\u0005\u0004!CQAU\u0001\u0005\u0004M\u000b1\u0001\u001d;q\u0015\tI!\"\u0001\u0004mS:\fGn\u001a\u0006\u0002\u0017\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u000f\u00035\t\u0001BA\u0002qiB\u001c2!A\t\u0018!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0001dG\u0007\u00023)\u0011!DC\u0001\bO\u0016tWM]5d\u0013\ta\u0012DA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002\u001b\u0005Q!/\u001a3vG\u0016|\u0016J\u001c;\u0016\u0005\u0005BCC\u0001\u00125!\u0011\u0019CEJ\u0019\u000e\u0003\u0005I!!J\u000e\u0003\t%k\u0007\u000f\u001c\t\u0003O!b\u0001\u0001B\u0003*\u0007\t\u0007!FA\u0001U#\tYc\u0006\u0005\u0002\u0013Y%\u0011Qf\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0011r&\u0003\u00021'\t\u0019\u0011I\\=\u0011\u0005I\u0011\u0014BA\u001a\u0014\u0005\rIe\u000e\u001e\u0005\u0006k\r\u0001\u001dAN\u0001\u0005SR,'\u000f\u0005\u00038u\u0019\nT\"\u0001\u001d\u000b\u0005eB\u0011aB:vaB|'\u000f^\u0005\u0003wa\u0012\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t\u00035\u0011X\rZ;dK~#u.\u001e2mKV\u0011a(\u0011\u000b\u0003\u007f\u0015\u0003Ba\t\u0013A\u0005B\u0011q%\u0011\u0003\u0006S\u0011\u0011\rA\u000b\t\u0003%\rK!\u0001R\n\u0003\r\u0011{WO\u00197f\u0011\u0015)D\u0001q\u0001G!\u00119$\b\u0011\"\u0002\u0019I,G-^2f?\u001acw.\u0019;\u0016\u0005%cEC\u0001&Q!\u0011\u0019CeS'\u0011\u0005\u001dbE!B\u0015\u0006\u0005\u0004Q\u0003C\u0001\nO\u0013\ty5CA\u0003GY>\fG\u000fC\u00036\u000b\u0001\u000f\u0011\u000b\u0005\u00038u-k\u0015a\u0003:fIV\u001cWm\u0018'p]\u001e,\"\u0001V,\u0015\u0005U[\u0006\u0003B\u0012%-b\u0003\"aJ,\u0005\u000b%2!\u0019\u0001\u0016\u0011\u0005II\u0016B\u0001.\u0014\u0005\u0011auN\\4\t\u000bU2\u00019\u0001/\u0011\t]Rd\u000b\u0017"
)
public final class ptp {
   public static UFunc.UImpl reduce_Long(final CanTraverseValues iter) {
      return ptp$.MODULE$.reduce_Long(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return ptp$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return ptp$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return ptp$.MODULE$.reduce_Int(iter);
   }

   public static Object withSink(final Object s) {
      return ptp$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return ptp$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return ptp$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return ptp$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return ptp$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return ptp$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return ptp$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return ptp$.MODULE$.apply(v, impl);
   }
}
