package breeze.linalg;

import breeze.generic.UFunc;
import breeze.linalg.support.CanTraverseValues;
import breeze.math.Semiring;
import breeze.storage.Zero;
import scala.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}q!B\u0007\u000f\u0011\u0003\u0019b!B\u000b\u000f\u0011\u00031\u0002\"B\u0012\u0002\t\u0003!S\u0001B\u0013\u0002A\u0019BQ\u0001L\u0001\u0005\u00045BQ!T\u0001\u0005\u00049CQ\u0001W\u0001\u0005\u0004eCQaY\u0001\u0005\u0004\u0011DQA\\\u0001\u0005\u0004=Dq!a\u0001\u0002\t\u0007\t)\u0001C\u0004\u0002\u000e\u0005!\u0019!a\u0004\t\u000f\u0005M\u0011\u0001b\u0001\u0002\u0016!9\u0011\u0011D\u0001\u0005\u0004\u0005m\u0011aA:v[*\u0011q\u0002E\u0001\u0007Y&t\u0017\r\\4\u000b\u0003E\taA\u0019:fKj,7\u0001\u0001\t\u0003)\u0005i\u0011A\u0004\u0002\u0004gVl7\u0003B\u0001\u0018;\u0001\u0002\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007C\u0001\u000b\u001f\u0013\tybBA\u000bWK\u000e$xN]5{K\u0012\u0014V\rZ;dKV3UO\\2\u0011\u0005Q\t\u0013B\u0001\u0012\u000f\u0005)\u0019X/\u001c'poB\u0013\u0018n\\\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003M\u0011!a\u00149\u000f\u0005\u001dRS\"\u0001\u0015\u000b\u0005%r\u0011!C8qKJ\fGo\u001c:t\u0013\tY\u0003&A\u0003Pa\u0006#G-\u0001\u0006sK\u0012,8-Z0J]R,\"AL\u001d\u0015\u0005=*\u0005\u0003\u0002\u00192o\tk\u0011!A\u0005\u0003eM\u0012A!S7qY&\u0011A'\u000e\u0002\u0006+\u001a+hn\u0019\u0006\u0003mA\tqaZ3oKJL7\r\u0005\u00029s1\u0001A!\u0002\u001e\u0005\u0005\u0004Y$!\u0001+\u0012\u0005qz\u0004C\u0001\r>\u0013\tq\u0014DA\u0004O_RD\u0017N\\4\u0011\u0005a\u0001\u0015BA!\u001a\u0005\r\te.\u001f\t\u00031\rK!\u0001R\r\u0003\u0007%sG\u000fC\u0003G\t\u0001\u000fq)\u0001\u0003ji\u0016\u0014\b\u0003\u0002%Lo\tk\u0011!\u0013\u0006\u0003\u0015:\tqa];qa>\u0014H/\u0003\u0002M\u0013\n\t2)\u00198Ue\u00064XM]:f-\u0006dW/Z:\u0002\u001bI,G-^2f?\u0012{WO\u00197f+\ty%\u000b\u0006\u0002Q-B!\u0001'M)T!\tA$\u000bB\u0003;\u000b\t\u00071\b\u0005\u0002\u0019)&\u0011Q+\u0007\u0002\u0007\t>,(\r\\3\t\u000b\u0019+\u00019A,\u0011\t![\u0015kU\u0001\re\u0016$WoY3`\r2|\u0017\r^\u000b\u00035v#\"aW1\u0011\tA\nDL\u0018\t\u0003qu#QA\u000f\u0004C\u0002m\u0002\"\u0001G0\n\u0005\u0001L\"!\u0002$m_\u0006$\b\"\u0002$\u0007\u0001\b\u0011\u0007\u0003\u0002%L9z\u000b1B]3ek\u000e,w\fT8oOV\u0011Q\r\u001b\u000b\u0003M2\u0004B\u0001M\u0019hSB\u0011\u0001\b\u001b\u0003\u0006u\u001d\u0011\ra\u000f\t\u00031)L!a[\r\u0003\t1{gn\u001a\u0005\u0006\r\u001e\u0001\u001d!\u001c\t\u0005\u0011.;\u0017.\u0001\bsK\u0012,8-Z*f[&\u0014\u0018N\\4\u0016\u0007A\u001cX\u000fF\u0002rof\u0004B\u0001M\u0019siB\u0011\u0001h\u001d\u0003\u0006u!\u0011\ra\u000f\t\u0003qU$QA\u001e\u0005C\u0002m\u0012\u0011a\u0015\u0005\u0006\r\"\u0001\u001d\u0001\u001f\t\u0005\u0011.\u0013H\u000fC\u0003{\u0011\u0001\u000f10\u0001\u0005tK6L'/\u001b8h!\rax\u0010^\u0007\u0002{*\u0011a\u0010E\u0001\u0005[\u0006$\b.C\u0002\u0002\u0002u\u0014\u0001bU3nSJLgnZ\u0001\u000bQ\u0016d\u0007/\u001a:`\u0013:$XCAA\u0004!\u0011\u0001\u0014\u0011\u0002\"\n\u0007\u0005-aDA\bWK\u000e$xN]5{K\"+G\u000e]3s\u00031AW\r\u001c9fe~3En\\1u+\t\t\t\u0002\u0005\u00031\u0003\u0013q\u0016a\u00035fYB,'o\u0018'p]\u001e,\"!a\u0006\u0011\tA\nI![\u0001\u000eQ\u0016d\u0007/\u001a:`\t>,(\r\\3\u0016\u0005\u0005u\u0001\u0003\u0002\u0019\u0002\nM\u0003"
)
public final class sum {
   public static VectorizedReduceUFunc.VectorizeHelper helper_Double() {
      return sum$.MODULE$.helper_Double();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Long() {
      return sum$.MODULE$.helper_Long();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Float() {
      return sum$.MODULE$.helper_Float();
   }

   public static VectorizedReduceUFunc.VectorizeHelper helper_Int() {
      return sum$.MODULE$.helper_Int();
   }

   public static UFunc.UImpl reduceSemiring(final CanTraverseValues iter, final Semiring semiring) {
      return sum$.MODULE$.reduceSemiring(iter, semiring);
   }

   public static UFunc.UImpl reduce_Long(final CanTraverseValues iter) {
      return sum$.MODULE$.reduce_Long(iter);
   }

   public static UFunc.UImpl reduce_Float(final CanTraverseValues iter) {
      return sum$.MODULE$.reduce_Float(iter);
   }

   public static UFunc.UImpl reduce_Double(final CanTraverseValues iter) {
      return sum$.MODULE$.reduce_Double(iter);
   }

   public static UFunc.UImpl reduce_Int(final CanTraverseValues iter) {
      return sum$.MODULE$.reduce_Int(iter);
   }

   public static UFunc.UImpl sumIterator(final UFunc.UImpl2 tSum) {
      return sum$.MODULE$.sumIterator(tSum);
   }

   public static UFunc.UImpl sumSummableThings(final .less.colon.less view, final UFunc.UImpl2 tSum) {
      return sum$.MODULE$.sumSummableThings(view, tSum);
   }

   public static UFunc.UImpl2 vectorizeCols2_Long(final UFunc.UImpl2 impl2) {
      return sum$.MODULE$.vectorizeCols2_Long(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Int(final UFunc.UImpl2 impl2) {
      return sum$.MODULE$.vectorizeCols2_Int(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Float(final UFunc.UImpl2 impl2) {
      return sum$.MODULE$.vectorizeCols2_Float(impl2);
   }

   public static UFunc.UImpl2 vectorizeCols2_Double(final UFunc.UImpl2 impl2) {
      return sum$.MODULE$.vectorizeCols2_Double(impl2);
   }

   public static UFunc.UImpl vectorizeCols_Long(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return sum$.MODULE$.vectorizeCols_Long(helper);
   }

   public static UFunc.UImpl vectorizeCols_Int(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return sum$.MODULE$.vectorizeCols_Int(helper);
   }

   public static UFunc.UImpl vectorizeCols_Float(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return sum$.MODULE$.vectorizeCols_Float(helper);
   }

   public static UFunc.UImpl vectorizeCols_Double(final VectorizedReduceUFunc.VectorizeHelper helper) {
      return sum$.MODULE$.vectorizeCols_Double(helper);
   }

   public static UFunc.UImpl2 vectorizeRows2(final ClassTag evidence$2, final Zero evidence$3, final UFunc.UImpl2 baseOp) {
      return sum$.MODULE$.vectorizeRows2(evidence$2, evidence$3, baseOp);
   }

   public static UFunc.UImpl vectorizeRows(final ClassTag evidence$1, final VectorizedReduceUFunc.VectorizeHelper helper, final UFunc.InPlaceImpl2 baseOp) {
      return sum$.MODULE$.vectorizeRows(evidence$1, helper, baseOp);
   }

   public static Object withSink(final Object s) {
      return sum$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return sum$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return sum$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return sum$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return sum$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return sum$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return sum$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return sum$.MODULE$.apply(v, impl);
   }
}
