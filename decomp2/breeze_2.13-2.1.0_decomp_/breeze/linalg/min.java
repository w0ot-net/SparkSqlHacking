package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.ScalarOf;
import breeze.storage.Zero;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ms!B\u000e\u001d\u0011\u0003\tc!B\u0012\u001d\u0011\u0003!\u0003\"B\u001c\u0002\t\u0003A\u0004bB\u001d\u0002\u0005\u0004%\u0019A\u000f\u0005\u0007\u0005\u0006\u0001\u000b\u0011B\u001e\t\u000f\r\u000b!\u0019!C\u0002\t\"1\u0011*\u0001Q\u0001\n\u0015CqAS\u0001C\u0002\u0013\r1\n\u0003\u0004Q\u0003\u0001\u0006I\u0001\u0014\u0005\b#\u0006\u0011\r\u0011b\u0001S\u0011\u00199\u0016\u0001)A\u0005'\"9\u0001,\u0001b\u0001\n\u0007I\u0006BB/\u0002A\u0003%!\fC\u0004_\u0003\t\u0007I1A0\t\r\u0005\f\u0001\u0015!\u0003a\u0011\u001d\u0011\u0017A1A\u0005\u0004\rDa!Z\u0001!\u0002\u0013!\u0007b\u00024\u0002\u0005\u0004%\u0019a\u001a\u0005\u0007S\u0006\u0001\u000b\u0011\u00025\t\u000b)\fA1A6\t\u000f\u0005\u001d\u0011\u0001b\u0001\u0002\n!9\u0011qC\u0001\u0005\u0004\u0005e\u0001bBA\u0014\u0003\u0011\r\u0011\u0011\u0006\u0005\b\u0003o\tA1AA\u001d\u0011\u001d\t\t%\u0001C\u0002\u0003\u0007Bq!a\u0012\u0002\t\u0007\tI\u0005C\u0004\u0002N\u0005!\u0019!a\u0014\u0002\u00075LgN\u0003\u0002\u001e=\u00051A.\u001b8bY\u001eT\u0011aH\u0001\u0007EJ,WM_3\u0004\u0001A\u0011!%A\u0007\u00029\t\u0019Q.\u001b8\u0014\u000b\u0005)3&\r\u001b\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g!\tas&D\u0001.\u0015\tqc$A\u0004hK:,'/[2\n\u0005Aj#!B+Gk:\u001c\u0007C\u0001\u00123\u0013\t\u0019DD\u0001\u0006nS:dun\u001e)sS>\u0004\"AI\u001b\n\u0005Yb\"!\u0006,fGR|'/\u001b>fIJ+G-^2f+\u001a+hnY\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u0005\nA\"\\5o\u00136\u0004HNM0J]R,\u0012a\u000f\t\u0006yuzthP\u0007\u0002\u0003%\u0011ah\f\u0002\u0006\u00136\u0004HN\r\t\u0003M\u0001K!!Q\u0014\u0003\u0007%sG/A\u0007nS:LU\u000e\u001d73?&sG\u000fI\u0001\u0010[&t\u0017*\u001c9me}#u.\u001e2mKV\tQ\tE\u0003={\u00193e\t\u0005\u0002'\u000f&\u0011\u0001j\n\u0002\u0007\t>,(\r\\3\u0002!5Lg.S7qYJzFi\\;cY\u0016\u0004\u0013AD7j]&k\u0007\u000f\u001c\u001a`\r2|\u0017\r^\u000b\u0002\u0019B)A(P'N\u001bB\u0011aET\u0005\u0003\u001f\u001e\u0012QA\u00127pCR\fq\"\\5o\u00136\u0004HNM0GY>\fG\u000fI\u0001\u000e[&t\u0017*\u001c9me}cuN\\4\u0016\u0003M\u0003R\u0001P\u001fU)R\u0003\"AJ+\n\u0005Y;#\u0001\u0002'p]\u001e\fa\"\\5o\u00136\u0004HNM0M_:<\u0007%\u0001\u0007nS:LU\u000e\u001d74?&sG/F\u0001[!\u0019a4lP @\u007f%\u0011Al\f\u0002\u0006\u00136\u0004HnM\u0001\u000e[&t\u0017*\u001c9mg}Ke\u000e\u001e\u0011\u0002\u001f5Lg.S7qYNzFi\\;cY\u0016,\u0012\u0001\u0019\t\u0007ym3eI\u0012$\u0002!5Lg.S7qYNzFi\\;cY\u0016\u0004\u0013AD7j]&k\u0007\u000f\\\u001a`\r2|\u0017\r^\u000b\u0002IB1AhW'N\u001b6\u000bq\"\\5o\u00136\u0004HnM0GY>\fG\u000fI\u0001\u000e[&t\u0017*\u001c9mg}cuN\\4\u0016\u0003!\u0004b\u0001P.U)R#\u0016AD7j]&k\u0007\u000f\\\u001a`\u0019>tw\rI\u0001\u000be\u0016$WoY3`\u0013:$XC\u00017s)\ti7\u0010\u0005\u0003=]B|\u0014BA80\u0005\u0011IU\u000e\u001d7\u0011\u0005E\u0014H\u0002\u0001\u0003\u0006gN\u0011\r\u0001\u001e\u0002\u0002)F\u0011Q\u000f\u001f\t\u0003MYL!a^\u0014\u0003\u000f9{G\u000f[5oOB\u0011a%_\u0005\u0003u\u001e\u00121!\u00118z\u0011\u0015a8\u0003q\u0001~\u0003\u0011IG/\u001a:\u0011\u000by\f\u0019\u0001] \u000e\u0003}T1!!\u0001\u001d\u0003\u001d\u0019X\u000f\u001d9peRL1!!\u0002\u0000\u0005E\u0019\u0015M\u001c+sCZ,'o]3WC2,Xm]\u0001\u000ee\u0016$WoY3`\t>,(\r\\3\u0016\t\u0005-\u0011\u0011\u0003\u000b\u0005\u0003\u001b\t\u0019\u0002E\u0003=]\u0006=a\tE\u0002r\u0003#!Qa\u001d\u000bC\u0002QDa\u0001 \u000bA\u0004\u0005U\u0001C\u0002@\u0002\u0004\u0005=a)\u0001\u0007sK\u0012,8-Z0GY>\fG/\u0006\u0003\u0002\u001c\u0005\u0005B\u0003BA\u000f\u0003G\u0001R\u0001\u00108\u0002 5\u00032!]A\u0011\t\u0015\u0019XC1\u0001u\u0011\u0019aX\u0003q\u0001\u0002&A1a0a\u0001\u0002 5\u000b1B]3ek\u000e,w\fT8oOV!\u00111FA\u0019)\u0011\ti#a\r\u0011\u000bqr\u0017q\u0006+\u0011\u0007E\f\t\u0004B\u0003t-\t\u0007A\u000f\u0003\u0004}-\u0001\u000f\u0011Q\u0007\t\u0007}\u0006\r\u0011q\u0006+\u0002\u0015!,G\u000e]3s?&sG/\u0006\u0002\u0002<A!A(!\u0010@\u0013\r\ty$\u000e\u0002\u0010-\u0016\u001cGo\u001c:ju\u0016DU\r\u001c9fe\u0006a\u0001.\u001a7qKJ|f\t\\8biV\u0011\u0011Q\t\t\u0005y\u0005uR*A\u0006iK2\u0004XM]0M_:<WCAA&!\u0011a\u0014Q\b+\u0002\u001b!,G\u000e]3s?\u0012{WO\u00197f+\t\t\t\u0006\u0005\u0003=\u0003{1\u0005"
)
public final class min {
   public static VectorizedReduceUFunc.VectorizeHelper helper_Double() {
      return min$.MODULE$.helper_Double();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Long() {
      return min$.MODULE$.helper_Long();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Float() {
      return min$.MODULE$.helper_Float();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Int() {
      return min$.MODULE$.helper_Int();
   }

   public static UFunc.UImpl reduce_Long(final CanTraverseValues iter) {
      return min$.MODULE$.reduce_Long(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return min$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return min$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return min$.MODULE$.reduce_Int(iter);
   }

   public static UFunc.UImpl3 minImpl3_Long() {
      return min$.MODULE$.minImpl3_Long();
   }

   public static UFunc.UImpl3 minImpl3_Float() {
      return min$.MODULE$.minImpl3_Float();
   }

   public static UFunc.UImpl3 minImpl3_Double() {
      return min$.MODULE$.minImpl3_Double();
   }

   public static UFunc.UImpl3 minImpl3_Int() {
      return min$.MODULE$.minImpl3_Int();
   }

   public static UFunc.UImpl2 minImpl2_Long() {
      return min$.MODULE$.minImpl2_Long();
   }

   public static UFunc.UImpl2 minImpl2_Float() {
      return min$.MODULE$.minImpl2_Float();
   }

   public static UFunc.UImpl2 minImpl2_Double() {
      return min$.MODULE$.minImpl2_Double();
   }

   public static UFunc.UImpl2 minImpl2_Int() {
      return min$.MODULE$.minImpl2_Int();
   }

   public static UFunc.UImpl2 vectorizeCols2_Long(final UFunc.UImpl2 impl2) {
      return min$.MODULE$.vectorizeCols2_Long(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Int(final UFunc.UImpl2 impl2) {
      return min$.MODULE$.vectorizeCols2_Int(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Float(final UFunc.UImpl2 impl2) {
      return min$.MODULE$.vectorizeCols2_Float(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Double(final UFunc.UImpl2 impl2) {
      return min$.MODULE$.vectorizeCols2_Double(impl2);
   }

   public static UFunc.UImpl vectorizeCols_Long(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return min$.MODULE$.vectorizeCols_Long(helper);
   }

   public static UFunc.UImpl vectorizeCols_Int(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return min$.MODULE$.vectorizeCols_Int(helper);
   }

   public static UFunc.UImpl vectorizeCols_Float(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return min$.MODULE$.vectorizeCols_Float(helper);
   }

   public static UFunc.UImpl vectorizeCols_Double(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return min$.MODULE$.vectorizeCols_Double(helper);
   }

   public static UFunc.UImpl2 vectorizeRows2(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return min$.MODULE$.vectorizeRows2(evidence$2, evidence$3, baseOp);
   }

   public static UFunc.UImpl vectorizeRows(final ClassTag evidence$1, final VectorizedReduceUFunc.VectorizeHelper helper, final UFunc.InPlaceImpl2 baseOp) {
      return min$.MODULE$.vectorizeRows(evidence$1, helper, baseOp);
   }

   public static UFunc.UImpl2 minVS(final ScalarOf cmvH, final UFunc.UImpl2 minImpl, final CanMapValues cmv) {
      return min$.MODULE$.minVS(cmvH, minImpl, cmv);
   }

   public static Object withSink(final Object s) {
      return min$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return min$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return min$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return min$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return min$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return min$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return min$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return min$.MODULE$.apply(v, impl);
   }
}
