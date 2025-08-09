package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.util.Isomorphism;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple4;
import scala.Predef.ArrowAssoc.;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005)4Aa\u0003\u0007\u0001#!A\u0001\u0006\u0001B\u0001B\u0003%\u0011\u0004\u0003\u0005*\u0001\t\r\t\u0015a\u0003+\u0011\u0015\u0011\u0004\u0001\"\u00014\u0011\u0015A\u0004\u0001\"\u0011:\u0011\u0015i\u0005\u0001\"\u0011O\u0011\u001d!\u0006\u00011A\u0005\nUCq!\u0017\u0001A\u0002\u0013%!\f\u0003\u0004a\u0001\u0001\u0006KA\u0016\u0005\u0006C\u0002!\tA\u0019\u0005\u0006G\u0002!\t\u0005\u001a\u0002\u0018\u0007\u0006\u001c\u0007.\u001a3CCR\u001c\u0007\u000eR5gM\u001a+hn\u0019;j_:T!!\u0004\b\u0002\u0011=\u0004H/[7ju\u0016T\u0011aD\u0001\u0007EJ,WM_3\u0004\u0001U\u0011!cH\n\u0004\u0001MI\u0002C\u0001\u000b\u0018\u001b\u0005)\"\"\u0001\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005a)\"AB!osJ+g\rE\u0002\u001b7ui\u0011\u0001D\u0005\u000391\u0011\u0011CQ1uG\"$\u0015N\u001a4Gk:\u001cG/[8o!\tqr\u0004\u0004\u0001\u0005\u000b\u0001\u0002!\u0019A\u0011\u0003\u0003Q\u000b\"AI\u0013\u0011\u0005Q\u0019\u0013B\u0001\u0013\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0006\u0014\n\u0005\u001d*\"aA!os\u0006\u0019qN\u00196\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$#\u0007E\u0002,aui\u0011\u0001\f\u0006\u0003[9\nqa];qa>\u0014HO\u0003\u00020\u001d\u00051A.\u001b8bY\u001eL!!\r\u0017\u0003\u000f\r\u000bgnQ8qs\u00061A(\u001b8jiz\"\"\u0001N\u001c\u0015\u0005U2\u0004c\u0001\u000e\u0001;!)\u0011f\u0001a\u0002U!)\u0001f\u0001a\u00013\u0005QqM]1eS\u0016tG/\u0011;\u0015\u0007uQD\bC\u0003<\t\u0001\u0007Q$A\u0001y\u0011\u0015iD\u00011\u0001?\u0003\u0015\u0011\u0018M\\4f!\rytI\u0013\b\u0003\u0001\u0016s!!\u0011#\u000e\u0003\tS!a\u0011\t\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0012B\u0001$\u0016\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001S%\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u0002G+A\u0011AcS\u0005\u0003\u0019V\u00111!\u00138u\u0003\u001d1\u0018\r\\;f\u0003R$2a\u0014*T!\t!\u0002+\u0003\u0002R+\t1Ai\\;cY\u0016DQaO\u0003A\u0002uAQ!P\u0003A\u0002y\n\u0001\u0002\\1ti\u0012\u000bG/Y\u000b\u0002-B1AcV\u000fP;yJ!\u0001W\u000b\u0003\rQ+\b\u000f\\35\u00031a\u0017m\u001d;ECR\fw\fJ3r)\tYf\f\u0005\u0002\u00159&\u0011Q,\u0006\u0002\u0005+:LG\u000fC\u0004`\u000f\u0005\u0005\t\u0019\u0001,\u0002\u0007a$\u0013'A\u0005mCN$H)\u0019;bA\u0005Ia-\u001e7m%\u0006tw-Z\u000b\u0002}\u0005I1-\u00197dk2\fG/\u001a\u000b\u0004K\"L\u0007\u0003\u0002\u000bg\u001fvI!aZ\u000b\u0003\rQ+\b\u000f\\33\u0011\u0015Y$\u00021\u0001\u001e\u0011\u0015i$\u00021\u0001?\u0001"
)
public class CachedBatchDiffFunction implements BatchDiffFunction {
   private final BatchDiffFunction obj;
   private final CanCopy evidence$2;
   private Tuple4 lastData;

   public Tuple2 calculate(final Object x) {
      return BatchDiffFunction.calculate$(this, x);
   }

   public double valueAt(final Object x) {
      return BatchDiffFunction.valueAt$(this, x);
   }

   public Object gradientAt(final Object x) {
      return BatchDiffFunction.gradientAt$(this, x);
   }

   public double apply(final Object x, final IndexedSeq batch) {
      return BatchDiffFunction.apply$(this, x, batch);
   }

   public DiffFunction cached(final CanCopy copy) {
      return BatchDiffFunction.cached$(this, copy);
   }

   public StochasticDiffFunction withRandomBatches(final int size) {
      return BatchDiffFunction.withRandomBatches$(this, size);
   }

   public StochasticDiffFunction withScanningBatches(final int size) {
      return BatchDiffFunction.withScanningBatches$(this, size);
   }

   public BatchDiffFunction groupItems(final int groupSize) {
      return BatchDiffFunction.groupItems$(this, groupSize);
   }

   public BatchDiffFunction throughLens(final Isomorphism l) {
      return BatchDiffFunction.throughLens$(this, l);
   }

   public boolean apply$mcZDD$sp(final double v1, final double v2) {
      return Function2.apply$mcZDD$sp$(this, v1, v2);
   }

   public double apply$mcDDD$sp(final double v1, final double v2) {
      return Function2.apply$mcDDD$sp$(this, v1, v2);
   }

   public float apply$mcFDD$sp(final double v1, final double v2) {
      return Function2.apply$mcFDD$sp$(this, v1, v2);
   }

   public int apply$mcIDD$sp(final double v1, final double v2) {
      return Function2.apply$mcIDD$sp$(this, v1, v2);
   }

   public long apply$mcJDD$sp(final double v1, final double v2) {
      return Function2.apply$mcJDD$sp$(this, v1, v2);
   }

   public void apply$mcVDD$sp(final double v1, final double v2) {
      Function2.apply$mcVDD$sp$(this, v1, v2);
   }

   public boolean apply$mcZDI$sp(final double v1, final int v2) {
      return Function2.apply$mcZDI$sp$(this, v1, v2);
   }

   public double apply$mcDDI$sp(final double v1, final int v2) {
      return Function2.apply$mcDDI$sp$(this, v1, v2);
   }

   public float apply$mcFDI$sp(final double v1, final int v2) {
      return Function2.apply$mcFDI$sp$(this, v1, v2);
   }

   public int apply$mcIDI$sp(final double v1, final int v2) {
      return Function2.apply$mcIDI$sp$(this, v1, v2);
   }

   public long apply$mcJDI$sp(final double v1, final int v2) {
      return Function2.apply$mcJDI$sp$(this, v1, v2);
   }

   public void apply$mcVDI$sp(final double v1, final int v2) {
      Function2.apply$mcVDI$sp$(this, v1, v2);
   }

   public boolean apply$mcZDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcZDJ$sp$(this, v1, v2);
   }

   public double apply$mcDDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcDDJ$sp$(this, v1, v2);
   }

   public float apply$mcFDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcFDJ$sp$(this, v1, v2);
   }

   public int apply$mcIDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcIDJ$sp$(this, v1, v2);
   }

   public long apply$mcJDJ$sp(final double v1, final long v2) {
      return Function2.apply$mcJDJ$sp$(this, v1, v2);
   }

   public void apply$mcVDJ$sp(final double v1, final long v2) {
      Function2.apply$mcVDJ$sp$(this, v1, v2);
   }

   public boolean apply$mcZID$sp(final int v1, final double v2) {
      return Function2.apply$mcZID$sp$(this, v1, v2);
   }

   public double apply$mcDID$sp(final int v1, final double v2) {
      return Function2.apply$mcDID$sp$(this, v1, v2);
   }

   public float apply$mcFID$sp(final int v1, final double v2) {
      return Function2.apply$mcFID$sp$(this, v1, v2);
   }

   public int apply$mcIID$sp(final int v1, final double v2) {
      return Function2.apply$mcIID$sp$(this, v1, v2);
   }

   public long apply$mcJID$sp(final int v1, final double v2) {
      return Function2.apply$mcJID$sp$(this, v1, v2);
   }

   public void apply$mcVID$sp(final int v1, final double v2) {
      Function2.apply$mcVID$sp$(this, v1, v2);
   }

   public boolean apply$mcZII$sp(final int v1, final int v2) {
      return Function2.apply$mcZII$sp$(this, v1, v2);
   }

   public double apply$mcDII$sp(final int v1, final int v2) {
      return Function2.apply$mcDII$sp$(this, v1, v2);
   }

   public float apply$mcFII$sp(final int v1, final int v2) {
      return Function2.apply$mcFII$sp$(this, v1, v2);
   }

   public int apply$mcIII$sp(final int v1, final int v2) {
      return Function2.apply$mcIII$sp$(this, v1, v2);
   }

   public long apply$mcJII$sp(final int v1, final int v2) {
      return Function2.apply$mcJII$sp$(this, v1, v2);
   }

   public void apply$mcVII$sp(final int v1, final int v2) {
      Function2.apply$mcVII$sp$(this, v1, v2);
   }

   public boolean apply$mcZIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcZIJ$sp$(this, v1, v2);
   }

   public double apply$mcDIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcDIJ$sp$(this, v1, v2);
   }

   public float apply$mcFIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcFIJ$sp$(this, v1, v2);
   }

   public int apply$mcIIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcIIJ$sp$(this, v1, v2);
   }

   public long apply$mcJIJ$sp(final int v1, final long v2) {
      return Function2.apply$mcJIJ$sp$(this, v1, v2);
   }

   public void apply$mcVIJ$sp(final int v1, final long v2) {
      Function2.apply$mcVIJ$sp$(this, v1, v2);
   }

   public boolean apply$mcZJD$sp(final long v1, final double v2) {
      return Function2.apply$mcZJD$sp$(this, v1, v2);
   }

   public double apply$mcDJD$sp(final long v1, final double v2) {
      return Function2.apply$mcDJD$sp$(this, v1, v2);
   }

   public float apply$mcFJD$sp(final long v1, final double v2) {
      return Function2.apply$mcFJD$sp$(this, v1, v2);
   }

   public int apply$mcIJD$sp(final long v1, final double v2) {
      return Function2.apply$mcIJD$sp$(this, v1, v2);
   }

   public long apply$mcJJD$sp(final long v1, final double v2) {
      return Function2.apply$mcJJD$sp$(this, v1, v2);
   }

   public void apply$mcVJD$sp(final long v1, final double v2) {
      Function2.apply$mcVJD$sp$(this, v1, v2);
   }

   public boolean apply$mcZJI$sp(final long v1, final int v2) {
      return Function2.apply$mcZJI$sp$(this, v1, v2);
   }

   public double apply$mcDJI$sp(final long v1, final int v2) {
      return Function2.apply$mcDJI$sp$(this, v1, v2);
   }

   public float apply$mcFJI$sp(final long v1, final int v2) {
      return Function2.apply$mcFJI$sp$(this, v1, v2);
   }

   public int apply$mcIJI$sp(final long v1, final int v2) {
      return Function2.apply$mcIJI$sp$(this, v1, v2);
   }

   public long apply$mcJJI$sp(final long v1, final int v2) {
      return Function2.apply$mcJJI$sp$(this, v1, v2);
   }

   public void apply$mcVJI$sp(final long v1, final int v2) {
      Function2.apply$mcVJI$sp$(this, v1, v2);
   }

   public boolean apply$mcZJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcZJJ$sp$(this, v1, v2);
   }

   public double apply$mcDJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcDJJ$sp$(this, v1, v2);
   }

   public float apply$mcFJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcFJJ$sp$(this, v1, v2);
   }

   public int apply$mcIJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcIJJ$sp$(this, v1, v2);
   }

   public long apply$mcJJJ$sp(final long v1, final long v2) {
      return Function2.apply$mcJJJ$sp$(this, v1, v2);
   }

   public void apply$mcVJJ$sp(final long v1, final long v2) {
      Function2.apply$mcVJJ$sp$(this, v1, v2);
   }

   public Function1 curried() {
      return Function2.curried$(this);
   }

   public Function1 tupled() {
      return Function2.tupled$(this);
   }

   public String toString() {
      return Function2.toString$(this);
   }

   public DiffFunction repr() {
      return DiffFunction.repr$(this);
   }

   public final double apply(final Object x) {
      return StochasticDiffFunction.apply$(this, x);
   }

   public final Object $plus(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$plus$(this, b, op);
   }

   public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$eq$(this, b, op);
   }

   public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$plus$eq$(this, b, op);
   }

   public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$times$eq$(this, b, op);
   }

   public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$plus$eq$(this, b, op);
   }

   public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$times$eq$(this, b, op);
   }

   public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$minus$eq$(this, b, op);
   }

   public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$percent$eq$(this, b, op);
   }

   public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$percent$eq$(this, b, op);
   }

   public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$minus$eq$(this, b, op);
   }

   public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$div$eq$(this, b, op);
   }

   public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$eq$(this, b, op);
   }

   public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$div$eq$(this, b, op);
   }

   public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$less$(this, b, op);
   }

   public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$less$colon$eq$(this, b, op);
   }

   public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$greater$(this, b, op);
   }

   public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
      return NumericOps.$greater$colon$eq$(this, b, op);
   }

   public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$amp$eq$(this, b, op);
   }

   public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$bar$eq$(this, b, op);
   }

   public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$colon$up$up$eq$(this, b, op);
   }

   public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$amp$eq$(this, b, op);
   }

   public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$bar$eq$(this, b, op);
   }

   public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
      return NumericOps.$up$up$eq$(this, b, op);
   }

   public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
   }

   public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$colon$times$(this, b, op);
   }

   public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
   }

   public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
   }

   public final Object unary_$minus(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$minus$(this, op);
   }

   public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
   }

   public final Object $minus(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$minus$(this, b, op);
   }

   public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
   }

   public final Object $percent(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$percent$(this, b, op);
   }

   public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$colon$div$(this, b, op);
   }

   public final Object $div(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$div$(this, b, op);
   }

   public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$colon$up$(this, b, op);
   }

   public final Object dot(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.dot$(this, b, op);
   }

   public final Object unary_$bang(final UFunc.UImpl op) {
      return ImmutableNumericOps.unary_$bang$(this, op);
   }

   public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
   }

   public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
   }

   public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
   }

   public final Object $amp(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$amp$(this, b, op);
   }

   public final Object $bar(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bar$(this, b, op);
   }

   public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$up$up$(this, b, op);
   }

   public final Object $times(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$times$(this, b, op);
   }

   public final Object t(final CanTranspose op) {
      return ImmutableNumericOps.t$(this, op);
   }

   public Object $bslash(final Object b, final UFunc.UImpl2 op) {
      return ImmutableNumericOps.$bslash$(this, b, op);
   }

   public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
      return ImmutableNumericOps.t$(this, a, b, op, canSlice);
   }

   public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
      return ImmutableNumericOps.t$(this, a, op, canSlice);
   }

   public boolean apply$mcZD$sp(final double v1) {
      return Function1.apply$mcZD$sp$(this, v1);
   }

   public double apply$mcDD$sp(final double v1) {
      return Function1.apply$mcDD$sp$(this, v1);
   }

   public float apply$mcFD$sp(final double v1) {
      return Function1.apply$mcFD$sp$(this, v1);
   }

   public int apply$mcID$sp(final double v1) {
      return Function1.apply$mcID$sp$(this, v1);
   }

   public long apply$mcJD$sp(final double v1) {
      return Function1.apply$mcJD$sp$(this, v1);
   }

   public void apply$mcVD$sp(final double v1) {
      Function1.apply$mcVD$sp$(this, v1);
   }

   public boolean apply$mcZF$sp(final float v1) {
      return Function1.apply$mcZF$sp$(this, v1);
   }

   public double apply$mcDF$sp(final float v1) {
      return Function1.apply$mcDF$sp$(this, v1);
   }

   public float apply$mcFF$sp(final float v1) {
      return Function1.apply$mcFF$sp$(this, v1);
   }

   public int apply$mcIF$sp(final float v1) {
      return Function1.apply$mcIF$sp$(this, v1);
   }

   public long apply$mcJF$sp(final float v1) {
      return Function1.apply$mcJF$sp$(this, v1);
   }

   public void apply$mcVF$sp(final float v1) {
      Function1.apply$mcVF$sp$(this, v1);
   }

   public boolean apply$mcZI$sp(final int v1) {
      return Function1.apply$mcZI$sp$(this, v1);
   }

   public double apply$mcDI$sp(final int v1) {
      return Function1.apply$mcDI$sp$(this, v1);
   }

   public float apply$mcFI$sp(final int v1) {
      return Function1.apply$mcFI$sp$(this, v1);
   }

   public int apply$mcII$sp(final int v1) {
      return Function1.apply$mcII$sp$(this, v1);
   }

   public long apply$mcJI$sp(final int v1) {
      return Function1.apply$mcJI$sp$(this, v1);
   }

   public void apply$mcVI$sp(final int v1) {
      Function1.apply$mcVI$sp$(this, v1);
   }

   public boolean apply$mcZJ$sp(final long v1) {
      return Function1.apply$mcZJ$sp$(this, v1);
   }

   public double apply$mcDJ$sp(final long v1) {
      return Function1.apply$mcDJ$sp$(this, v1);
   }

   public float apply$mcFJ$sp(final long v1) {
      return Function1.apply$mcFJ$sp$(this, v1);
   }

   public int apply$mcIJ$sp(final long v1) {
      return Function1.apply$mcIJ$sp$(this, v1);
   }

   public long apply$mcJJ$sp(final long v1) {
      return Function1.apply$mcJJ$sp$(this, v1);
   }

   public void apply$mcVJ$sp(final long v1) {
      Function1.apply$mcVJ$sp$(this, v1);
   }

   public Function1 compose(final Function1 g) {
      return Function1.compose$(this, g);
   }

   public Function1 andThen(final Function1 g) {
      return Function1.andThen$(this, g);
   }

   public Object gradientAt(final Object x, final IndexedSeq range) {
      return this.calculate(x, range)._2();
   }

   public double valueAt(final Object x, final IndexedSeq range) {
      return this.calculate(x, range)._1$mcD$sp();
   }

   private Tuple4 lastData() {
      return this.lastData;
   }

   private void lastData_$eq(final Tuple4 x$1) {
      this.lastData = x$1;
   }

   public IndexedSeq fullRange() {
      return this.obj.fullRange();
   }

   public Tuple2 calculate(final Object x, final IndexedSeq range) {
      Tuple4 ld;
      label25: {
         ld = this.lastData();
         if (ld != null) {
            label23: {
               Object var5 = ld._4();
               if (range == null) {
                  if (var5 != null) {
                     break label23;
                  }
               } else if (!range.equals(var5)) {
                  break label23;
               }

               if (BoxesRunTime.equals(x, ld._1())) {
                  break label25;
               }
            }
         }

         Tuple2 newData = this.obj.calculate(x, range);
         ld = new Tuple4(breeze.linalg.package$.MODULE$.copy(x, this.evidence$2), BoxesRunTime.boxToDouble(newData._1$mcD$sp()), newData._2(), range);
         this.lastData_$eq(ld);
      }

      if (ld != null) {
         double v = BoxesRunTime.unboxToDouble(ld._2());
         Object g = ld._3();
         Tuple2 var3 = new Tuple2(BoxesRunTime.boxToDouble(v), g);
         double v = var3._1$mcD$sp();
         Object g = var3._2();
         return .MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(BoxesRunTime.boxToDouble(v)), g);
      } else {
         throw new MatchError(ld);
      }
   }

   public CachedBatchDiffFunction(final BatchDiffFunction obj, final CanCopy evidence$2) {
      this.obj = obj;
      this.evidence$2 = evidence$2;
      Function1.$init$(this);
      ImmutableNumericOps.$init$(this);
      NumericOps.$init$(this);
      StochasticDiffFunction.$init$(this);
      DiffFunction.$init$(this);
      Function2.$init$(this);
      BatchDiffFunction.$init$(this);
      this.lastData = null;
   }
}
