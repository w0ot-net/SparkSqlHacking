package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-t!B\u000f\u001f\u0011\u0003\u0019c!B\u0013\u001f\u0011\u00031\u0003\"B\u001d\u0002\t\u0003QT\u0001B\u001e\u0002\u0001qBq!P\u0001C\u0002\u0013\ra\b\u0003\u0004F\u0003\u0001\u0006Ia\u0010\u0005\b\r\u0006\u0011\r\u0011b\u0001H\u0011\u0019a\u0015\u0001)A\u0005\u0011\"9Q*\u0001b\u0001\n\u0007q\u0005BB*\u0002A\u0003%q\nC\u0004U\u0003\t\u0007I1A+\t\ri\u000b\u0001\u0015!\u0003W\u0011\u001dY\u0016A1A\u0005\u0004qCa\u0001Y\u0001!\u0002\u0013i\u0006bB1\u0002\u0005\u0004%\u0019A\u0019\u0005\u0007I\u0006\u0001\u000b\u0011B2\t\u000f\u0015\f!\u0019!C\u0002M\"1\u0001.\u0001Q\u0001\n\u001dDq![\u0001C\u0002\u0013\r!\u000e\u0003\u0004m\u0003\u0001\u0006Ia\u001b\u0005\u0006[\u0006!\u0019A\u001c\u0005\b\u0003\u001b\tA1AA\b\u0011\u001d\ti\"\u0001C\u0002\u0003?Aq!!\f\u0002\t\u0007\ty\u0003C\u0004\u0002>\u0005!\u0019!a\u0010\t\u000f\u0005\u001d\u0013\u0001b\u0001\u0002J!9\u0011QJ\u0001\u0005\u0004\u0005=\u0003bBA*\u0003\u0011\r\u0011Q\u000b\u0005\b\u00033\nA\u0011AA.\u0003\ri\u0017\r\u001f\u0006\u0003?\u0001\na\u0001\\5oC2<'\"A\u0011\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0001\"\u0001J\u0001\u000e\u0003y\u00111!\\1y'\u0015\tq%L\u001a7!\tA3&D\u0001*\u0015\u0005Q\u0013!B:dC2\f\u0017B\u0001\u0017*\u0005\u0019\te.\u001f*fMB\u0011a&M\u0007\u0002_)\u0011\u0001\u0007I\u0001\bO\u0016tWM]5d\u0013\t\u0011tFA\u0003V\rVt7\r\u0005\u0002%i%\u0011QG\b\u0002\u000b[\u0006DHj\\<Qe&|\u0007C\u0001\u00138\u0013\tAdDA\u000bWK\u000e$xN]5{K\u0012\u0014V\rZ;dKV3UO\\2\u0002\rqJg.\u001b;?)\u0005\u0019#AA(q\u001b\u0005\t\u0011\u0001D7bq&k\u0007\u000f\u001c\u001a`\u0013:$X#A \u0011\u000bq\u0002%I\u0011\"\n\u0005\u0005\u000b$!B%na2\u0014\u0004C\u0001\u0015D\u0013\t!\u0015FA\u0002J]R\fQ\"\\1y\u00136\u0004HNM0J]R\u0004\u0013aD7bq&k\u0007\u000f\u001c\u001a`\t>,(\r\\3\u0016\u0003!\u0003R\u0001\u0010!J\u0013&\u0003\"\u0001\u000b&\n\u0005-K#A\u0002#pk\ndW-\u0001\tnCbLU\u000e\u001d73?\u0012{WO\u00197fA\u0005qQ.\u0019=J[Bd'g\u0018$m_\u0006$X#A(\u0011\u000bq\u0002\u0005\u000b\u0015)\u0011\u0005!\n\u0016B\u0001**\u0005\u00151En\\1u\u0003=i\u0017\r_%na2\u0014tL\u00127pCR\u0004\u0013!D7bq&k\u0007\u000f\u001c\u001a`\u0019>tw-F\u0001W!\u0015a\u0004iV,X!\tA\u0003,\u0003\u0002ZS\t!Aj\u001c8h\u00039i\u0017\r_%na2\u0014t\fT8oO\u0002\nA\"\\1y\u00136\u0004HnM0J]R,\u0012!\u0018\t\u0007yy\u0013%I\u0011\"\n\u0005}\u000b$!B%na2\u001c\u0014!D7bq&k\u0007\u000f\\\u001a`\u0013:$\b%A\bnCbLU\u000e\u001d74?\u0012{WO\u00197f+\u0005\u0019\u0007C\u0002\u001f_\u0013&K\u0015*\u0001\tnCbLU\u000e\u001d74?\u0012{WO\u00197fA\u0005qQ.\u0019=J[Bd7g\u0018$m_\u0006$X#A4\u0011\rqr\u0006\u000b\u0015)Q\u0003=i\u0017\r_%na2\u001ctL\u00127pCR\u0004\u0013!D7bq&k\u0007\u000f\\\u001a`\u0019>tw-F\u0001l!\u0019adlV,X/\u0006qQ.\u0019=J[Bd7g\u0018'p]\u001e\u0004\u0013A\u0003:fIV\u001cWmX%oiV\u0011q.\u001e\u000b\u0003az\u0004B\u0001P9t\u0005&\u0011!/\r\u0002\u0005\u00136\u0004H\u000e\u0005\u0002uk2\u0001A!\u0002<\u0015\u0005\u00049(!\u0001+\u0012\u0005a\\\bC\u0001\u0015z\u0013\tQ\u0018FA\u0004O_RD\u0017N\\4\u0011\u0005!b\u0018BA?*\u0005\r\te.\u001f\u0005\u0007\u007fR\u0001\u001d!!\u0001\u0002\t%$XM\u001d\t\u0007\u0003\u0007\tIa\u001d\"\u000e\u0005\u0005\u0015!bAA\u0004=\u000591/\u001e9q_J$\u0018\u0002BA\u0006\u0003\u000b\u0011\u0011cQ1o)J\fg/\u001a:tKZ\u000bG.^3t\u00035\u0011X\rZ;dK~#u.\u001e2mKV!\u0011\u0011CA\f)\u0011\t\u0019\"!\u0007\u0011\u000bq\n\u0018QC%\u0011\u0007Q\f9\u0002B\u0003w+\t\u0007q\u000f\u0003\u0004\u0000+\u0001\u000f\u00111\u0004\t\b\u0003\u0007\tI!!\u0006J\u00031\u0011X\rZ;dK~3En\\1u+\u0011\t\t#a\n\u0015\t\u0005\r\u0012\u0011\u0006\t\u0006yE\f)\u0003\u0015\t\u0004i\u0006\u001dB!\u0002<\u0017\u0005\u00049\bBB@\u0017\u0001\b\tY\u0003E\u0004\u0002\u0004\u0005%\u0011Q\u0005)\u0002\u0017I,G-^2f?2{gnZ\u000b\u0005\u0003c\t9\u0004\u0006\u0003\u00024\u0005e\u0002#\u0002\u001fr\u0003k9\u0006c\u0001;\u00028\u0011)ao\u0006b\u0001o\"1qp\u0006a\u0002\u0003w\u0001r!a\u0001\u0002\n\u0005Ur+\u0001\u0006iK2\u0004XM]0J]R,\"!!\u0011\u0011\tq\n\u0019EQ\u0005\u0004\u0003\u000b:$a\u0004,fGR|'/\u001b>f\u0011\u0016d\u0007/\u001a:\u0002\u0019!,G\u000e]3s?\u001acw.\u0019;\u0016\u0005\u0005-\u0003\u0003\u0002\u001f\u0002DA\u000b1\u0002[3ma\u0016\u0014x\fT8oOV\u0011\u0011\u0011\u000b\t\u0005y\u0005\rs+A\u0007iK2\u0004XM]0E_V\u0014G.Z\u000b\u0003\u0003/\u0002B\u0001PA\"\u0013\u0006)\u0011M\u001d:bsR)\u0011*!\u0018\u0002h!9\u0011q\f\u000fA\u0002\u0005\u0005\u0014aA1seB!\u0001&a\u0019J\u0013\r\t)'\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\u0005\u0007\u0003Sb\u0002\u0019\u0001\"\u0002\r1,gn\u001a;i\u0001"
)
public final class max {
   public static double array(final double[] arr, final int length) {
      return max$.MODULE$.array(arr, length);
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Double() {
      return max$.MODULE$.helper_Double();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Long() {
      return max$.MODULE$.helper_Long();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Float() {
      return max$.MODULE$.helper_Float();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Int() {
      return max$.MODULE$.helper_Int();
   }

   public static UFunc.UImpl reduce_Long(final CanTraverseValues iter) {
      return max$.MODULE$.reduce_Long(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return max$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return max$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return max$.MODULE$.reduce_Int(iter);
   }

   public static UFunc.UImpl3 maxImpl3_Long() {
      return max$.MODULE$.maxImpl3_Long();
   }

   public static UFunc.UImpl3 maxImpl3_Float() {
      return max$.MODULE$.maxImpl3_Float();
   }

   public static UFunc.UImpl3 maxImpl3_Double() {
      return max$.MODULE$.maxImpl3_Double();
   }

   public static UFunc.UImpl3 maxImpl3_Int() {
      return max$.MODULE$.maxImpl3_Int();
   }

   public static UFunc.UImpl2 maxImpl2_Long() {
      return max$.MODULE$.maxImpl2_Long();
   }

   public static UFunc.UImpl2 maxImpl2_Float() {
      return max$.MODULE$.maxImpl2_Float();
   }

   public static UFunc.UImpl2 maxImpl2_Double() {
      return max$.MODULE$.maxImpl2_Double();
   }

   public static UFunc.UImpl2 maxImpl2_Int() {
      return max$.MODULE$.maxImpl2_Int();
   }

   public static UFunc.UImpl2 vectorizeCols2_Long(final UFunc.UImpl2 impl2) {
      return max$.MODULE$.vectorizeCols2_Long(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Int(final UFunc.UImpl2 impl2) {
      return max$.MODULE$.vectorizeCols2_Int(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Float(final UFunc.UImpl2 impl2) {
      return max$.MODULE$.vectorizeCols2_Float(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Double(final UFunc.UImpl2 impl2) {
      return max$.MODULE$.vectorizeCols2_Double(impl2);
   }

   public static UFunc.UImpl vectorizeCols_Long(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return max$.MODULE$.vectorizeCols_Long(helper);
   }

   public static UFunc.UImpl vectorizeCols_Int(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return max$.MODULE$.vectorizeCols_Int(helper);
   }

   public static UFunc.UImpl vectorizeCols_Float(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return max$.MODULE$.vectorizeCols_Float(helper);
   }

   public static UFunc.UImpl vectorizeCols_Double(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return max$.MODULE$.vectorizeCols_Double(helper);
   }

   public static UFunc.UImpl2 vectorizeRows2(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return max$.MODULE$.vectorizeRows2(evidence$2, evidence$3, baseOp);
   }

   public static UFunc.UImpl vectorizeRows(final ClassTag evidence$1, final VectorizedReduceUFunc.VectorizeHelper helper, final UFunc.InPlaceImpl2 baseOp) {
      return max$.MODULE$.vectorizeRows(evidence$1, helper, baseOp);
   }

   public static UFunc.UImpl2 maxVS(final ScalarOf cmvH, final UFunc.UImpl2 maxImpl, final CanMapValues cmv) {
      return max$.MODULE$.maxVS(cmvH, maxImpl, cmv);
   }

   public static Object withSink(final Object s) {
      return max$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return max$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return max$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return max$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return max$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return max$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return max$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return max$.MODULE$.apply(v, impl);
   }
}
