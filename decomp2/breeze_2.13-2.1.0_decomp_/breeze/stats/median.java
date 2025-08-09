package breeze.stats;

import breeze.generic.UFunc;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Es!B\u0006\r\u0011\u0003\tb!B\n\r\u0011\u0003!\u0002\"B\u0011\u0002\t\u0003\u0011\u0003\"B\u0012\u0002\t\u0007!\u0003\"B\u0018\u0002\t\u0007\u0001\u0004\"\u0002\u001c\u0002\t\u00079\u0004\"B\u001f\u0002\t\u0007q\u0004\"\u0002#\u0002\t\u0007)\u0005\"B6\u0002\t\u0007a\u0007bBA\u0004\u0003\u0011\r\u0011\u0011\u0002\u0005\b\u0003s\tA1AA\u001e\u0003\u0019iW\rZ5b]*\u0011QBD\u0001\u0006gR\fGo\u001d\u0006\u0002\u001f\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u0013\u00035\tAB\u0001\u0004nK\u0012L\u0017M\\\n\u0004\u0003UY\u0002C\u0001\f\u001a\u001b\u00059\"\"\u0001\r\u0002\u000bM\u001c\u0017\r\\1\n\u0005i9\"AB!osJ+g\r\u0005\u0002\u001d?5\tQD\u0003\u0002\u001f\u001d\u00059q-\u001a8fe&\u001c\u0017B\u0001\u0011\u001e\u0005\u0015)f)\u001e8d\u0003\u0019a\u0014N\\5u}Q\t\u0011#A\bsK\u0012,8-Z!se\u0006Lx,\u00138u+\u0005)\u0003\u0003\u0002\u0014(S1r!A\u0005\u0001\n\u0005!z\"\u0001B%na2\u00042A\u0006\u0016-\u0013\tYsCA\u0003BeJ\f\u0017\u0010\u0005\u0002\u0017[%\u0011af\u0006\u0002\u0004\u0013:$\u0018\u0001\u0005:fIV\u001cW-\u0011:sCf|Fj\u001c8h+\u0005\t\u0004\u0003\u0002\u0014(eM\u00022A\u0006\u00164!\t1B'\u0003\u00026/\t!Aj\u001c8h\u0003I\u0011X\rZ;dK\u0006\u0013(/Y=`\t>,(\r\\3\u0016\u0003a\u0002BAJ\u0014:uA\u0019aC\u000b\u001e\u0011\u0005YY\u0014B\u0001\u001f\u0018\u0005\u0019!u.\u001e2mK\u0006\t\"/\u001a3vG\u0016\f%O]1z?\u001acw.\u0019;\u0016\u0003}\u0002BAJ\u0014A\u0003B\u0019aCK!\u0011\u0005Y\u0011\u0015BA\"\u0018\u0005\u00151En\\1u\u0003\u0005\u0012X\rZ;dK\u0006\u0013(/Y=Ge>l\u0017+^5dWN,G.Z2u\u0003:$W*Z1o+\t15\n\u0006\u0003H)~+\u0007\u0003\u0002\u0014(\u0011&\u00032A\u0006\u0016J!\tQ5\n\u0004\u0001\u0005\u000b1;!\u0019A'\u0003\u0003Q\u000b\"AT)\u0011\u0005Yy\u0015B\u0001)\u0018\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0006*\n\u0005M;\"aA!os\")Qk\u0002a\u0002-\u0006\u0011\u0011o\u001d\t\u0006/vCE&\u0013\b\u00031nk\u0011!\u0017\u0006\u00035:\tA!\u001e;jY&\u0011A,W\u0001\fcVL7m[*fY\u0016\u001cG/\u0003\u0002_?\t)\u0011*\u001c9me!)\u0001m\u0002a\u0002C\u0006\u0019\u0011o]5\u0011\u000b\tl\u0006\nL%\u000f\u0005a\u001b\u0017B\u00013Z\u0003=\tX/[2l'\u0016dWm\u0019;J[Bd\u0007\"\u00024\b\u0001\b9\u0017AA7o!\u0015AW,S%J\u001d\t\u0011\u0012.\u0003\u0002k\u0019\u0005!Q.Z1o\u0003\u0019\u0011X\rZ;dKV\u0011QN\u001e\u000b\u0004]^|\b\u0003\u0002\u0014(_V\u00042\u0001]:v\u001b\u0005\t(B\u0001:\u000f\u0003\u0019a\u0017N\\1mO&\u0011A/\u001d\u0002\f\t\u0016t7/\u001a,fGR|'\u000f\u0005\u0002Km\u0012)A\n\u0003b\u0001\u001b\"9\u0001\u0010CA\u0001\u0002\bI\u0018AC3wS\u0012,gnY3%cA\u0019!0`;\u000e\u0003mT!\u0001`\f\u0002\u000fI,g\r\\3di&\u0011ap\u001f\u0002\t\u00072\f7o\u001d+bO\"9\u0011\u0011\u0001\u0005A\u0004\u0005\r\u0011aB1se&k\u0007\u000f\u001c\t\u0006M\u001d\n)!\u001e\t\u0004-)*\u0018!\u0003:fIV\u001cWmU3r+\u0011\tY!a\u000b\u0015\r\u00055\u0011QFA\u001a!\u001d\tyaJA\t\u0003Si\u0011!\u0001\t\u0007\u0003'\t\u0019#!\u000b\u000f\t\u0005U\u0011q\u0004\b\u0005\u0003/\ti\"\u0004\u0002\u0002\u001a)\u0019\u00111\u0004\t\u0002\rq\u0012xn\u001c;?\u0013\u0005A\u0012bAA\u0011/\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0013\u0003O\u00111aU3r\u0015\r\t\tc\u0006\t\u0004\u0015\u0006-B!\u0002'\n\u0005\u0004i\u0005\"CA\u0018\u0013\u0005\u0005\t9AA\u0019\u0003))g/\u001b3f]\u000e,GE\r\t\u0005uv\fI\u0003C\u0004\u0002\u0002%\u0001\u001d!!\u000e\u0011\r\u0019:\u0013qGA\u0015!\u00111\"&!\u000b\u0002\u000fI,G-^2f\u001bV!\u0011QHA%)\u0011\ty$a\u0013\u0011\u000f\u0005=q%!\u0011\u0002HA)\u0001/a\u0011\u0002H%\u0019\u0011QI9\u0003\u0017\u0011+gn]3NCR\u0014\u0018\u000e\u001f\t\u0004\u0015\u0006%C!\u0002'\u000b\u0005\u0004i\u0005bBA\u0001\u0015\u0001\u000f\u0011Q\n\t\u0007M\u001d\ny%a\u0012\u0011\tYQ\u0013q\t"
)
public final class median {
   public static UFunc.UImpl reduceM(final UFunc.UImpl arrImpl) {
      return median$.MODULE$.reduceM(arrImpl);
   }

   public static UFunc.UImpl reduceSeq(final ClassTag evidence$2, final UFunc.UImpl arrImpl) {
      return median$.MODULE$.reduceSeq(evidence$2, arrImpl);
   }

   public static UFunc.UImpl reduce(final ClassTag evidence$1, final UFunc.UImpl arrImpl) {
      return median$.MODULE$.reduce(evidence$1, arrImpl);
   }

   public static UFunc.UImpl reduceArrayFromQuickselectAndMean(final UFunc.UImpl2 qs, final UFunc.UImpl2 qsi, final UFunc.UImpl2 mn) {
      return median$.MODULE$.reduceArrayFromQuickselectAndMean(qs, qsi, mn);
   }

   public static UFunc.UImpl reduceArray_Float() {
      return median$.MODULE$.reduceArray_Float();
   }

   public static UFunc.UImpl reduceArray_Double() {
      return median$.MODULE$.reduceArray_Double();
   }

   public static UFunc.UImpl reduceArray_Long() {
      return median$.MODULE$.reduceArray_Long();
   }

   public static UFunc.UImpl reduceArray_Int() {
      return median$.MODULE$.reduceArray_Int();
   }

   public static Object withSink(final Object s) {
      return median$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return median$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return median$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return median$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return median$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return median$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return median$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return median$.MODULE$.apply(v, impl);
   }
}
