package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.Rand$;
import breeze.util.Isomorphism;
import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.compat.immutable.package.;
import scala.collection.immutable.ArraySeq;
import scala.collection.immutable.IndexedSeq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}baB\n\u0015!\u0003\r\t!\u0007\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u0015\u0002!\ta\u0013\u0005\u0006!\u0002!\t!\u0015\u0005\u0006)\u00021\t!\u0016\u0005\u0006)\u0002!\te\u0017\u0005\u0006!\u0002!\t%\u0018\u0005\u0006\u0015\u0002!\te\u0018\u0005\u0006C\u0002!\tA\u0019\u0005\u0006K\u0002!\tE\u001a\u0005\u0006c\u00021\tA\u001d\u0005\u0006g\u0002!\t\u0001\u001e\u0005\u0006u\u0002!\ta\u001f\u0005\u0006{\u0002!\tA \u0005\b\u0003\u000b\u0001A\u0011IA\u0004\u000f\u001d\t\u0019\u0003\u0006E\u0001\u0003K1aa\u0005\u000b\t\u0002\u0005\u001d\u0002bBA\u0015!\u0011\u0005\u00111\u0006\u0005\b\u0003[\u0001B\u0011AA\u0018\u0005E\u0011\u0015\r^2i\t&4gMR;oGRLwN\u001c\u0006\u0003+Y\t\u0001b\u001c9uS6L'0\u001a\u0006\u0002/\u00051!M]3fu\u0016\u001c\u0001!\u0006\u0002\u001bOM!\u0001aG\u00111!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fMB\u0019!eI\u0013\u000e\u0003QI!\u0001\n\u000b\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\u0011\u0005\u0019:C\u0002\u0001\u0003\u0006Q\u0001\u0011\r!\u000b\u0002\u0002)F\u0011!&\f\t\u00039-J!\u0001L\u000f\u0003\u000f9{G\u000f[5oOB\u0011ADL\u0005\u0003_u\u00111!\u00118z!\u0015a\u0012'J\u001aC\u0013\t\u0011TDA\u0005Gk:\u001cG/[8oeA\u0019A\u0007P \u000f\u0005URdB\u0001\u001c:\u001b\u00059$B\u0001\u001d\u0019\u0003\u0019a$o\\8u}%\ta$\u0003\u0002<;\u00059\u0001/Y2lC\u001e,\u0017BA\u001f?\u0005)Ie\u000eZ3yK\u0012\u001cV-\u001d\u0006\u0003wu\u0001\"\u0001\b!\n\u0005\u0005k\"aA%oiB\u0011AdQ\u0005\u0003\tv\u0011a\u0001R8vE2,\u0017A\u0002\u0013j]&$H\u0005F\u0001H!\ta\u0002*\u0003\u0002J;\t!QK\\5u\u0003)9'/\u00193jK:$\u0018\t\u001e\u000b\u0004K1s\u0005\"B'\u0003\u0001\u0004)\u0013!\u0001=\t\u000b=\u0013\u0001\u0019A\u001a\u0002\u000b\t\fGo\u00195\u0002\u000fY\fG.^3BiR\u0019!IU*\t\u000b5\u001b\u0001\u0019A\u0013\t\u000b=\u001b\u0001\u0019A\u001a\u0002\u0013\r\fGnY;mCR,Gc\u0001,Z5B!Ad\u0016\"&\u0013\tAVD\u0001\u0004UkBdWM\r\u0005\u0006\u001b\u0012\u0001\r!\n\u0005\u0006\u001f\u0012\u0001\ra\r\u000b\u0003-rCQ!T\u0003A\u0002\u0015\"\"A\u00110\t\u000b53\u0001\u0019A\u0013\u0015\u0005\u0015\u0002\u0007\"B'\b\u0001\u0004)\u0013!B1qa2LHc\u0001\"dI\")Q\n\u0003a\u0001K!)q\n\u0003a\u0001g\u000511-Y2iK\u0012$\"!I4\t\u000b!L\u00019A5\u0002\t\r|\u0007/\u001f\t\u0004U>,S\"A6\u000b\u00051l\u0017aB:vaB|'\u000f\u001e\u0006\u0003]Z\ta\u0001\\5oC2<\u0017B\u00019l\u0005\u001d\u0019\u0015M\\\"paf\f\u0011BZ;mYJ\u000bgnZ3\u0016\u0003M\n\u0011c^5uQJ\u000bg\u000eZ8n\u0005\u0006$8\r[3t)\t)\b\u0010E\u0002#m\u0016J!a\u001e\u000b\u0003-M#xn\u00195bgRL7\rR5gM\u001a+hn\u0019;j_:DQ!_\u0006A\u0002}\nAa]5{K\u0006\u0019r/\u001b;i'\u000e\fgN\\5oO\n\u000bGo\u00195fgR\u0011Q\u000f \u0005\u0006s2\u0001\raP\u0001\u000bOJ|W\u000f]%uK6\u001cHcA@\u0002\u0002A\u0019!\u0005A\u0013\t\r\u0005\rQ\u00021\u0001@\u0003%9'o\\;q'&TX-A\u0006uQJ|Wo\u001a5MK:\u001cX\u0003BA\u0005\u0003\u001f!B!a\u0003\u0002\u0014A!!\u0005AA\u0007!\r1\u0013q\u0002\u0003\u0007\u0003#q!\u0019A\u0015\u0003\u0003UCq!!\u0006\u000f\u0001\b\t9\"A\u0001m!\u001d\tI\"a\b&\u0003\u001bi!!a\u0007\u000b\u0007\u0005ua#\u0001\u0003vi&d\u0017\u0002BA\u0011\u00037\u00111\"S:p[>\u0014\b\u000f[5t[\u0006\t\")\u0019;dQ\u0012KgM\u001a$v]\u000e$\u0018n\u001c8\u0011\u0005\t\u00022C\u0001\t\u001c\u0003\u0019a\u0014N\\5u}Q\u0011\u0011QE\u0001\u0005oJ\f\u0007/\u0006\u0003\u00022\u0005]B\u0003BA\u001a\u0003s\u0001BA\t\u0001\u00026A\u0019a%a\u000e\u0005\u000b!\u0012\"\u0019A\u0015\t\u000f\u0005m\"\u00031\u0001\u0002>\u0005\ta\r\u0005\u0003#G\u0005U\u0002"
)
public interface BatchDiffFunction extends DiffFunction, Function2 {
   static BatchDiffFunction wrap(final DiffFunction f) {
      return BatchDiffFunction$.MODULE$.wrap(f);
   }

   default Object gradientAt(final Object x, final IndexedSeq batch) {
      return this.calculate(x, batch)._2();
   }

   default double valueAt(final Object x, final IndexedSeq batch) {
      return this.calculate(x, batch)._1$mcD$sp();
   }

   Tuple2 calculate(final Object x, final IndexedSeq batch);

   default Tuple2 calculate(final Object x) {
      return this.calculate(x, this.fullRange());
   }

   default double valueAt(final Object x) {
      return this.valueAt(x, this.fullRange());
   }

   default Object gradientAt(final Object x) {
      return this.gradientAt(x, this.fullRange());
   }

   default double apply(final Object x, final IndexedSeq batch) {
      return this.valueAt(x, batch);
   }

   default DiffFunction cached(final CanCopy copy) {
      return (DiffFunction)(this instanceof CachedBatchDiffFunction ? this : new CachedBatchDiffFunction(this, copy));
   }

   IndexedSeq fullRange();

   default StochasticDiffFunction withRandomBatches(final int size) {
      return new StochasticDiffFunction(size) {
         private final Rand rand;
         // $FF: synthetic field
         private final BatchDiffFunction $outer;

         public StochasticDiffFunction repr() {
            return StochasticDiffFunction.repr$(this);
         }

         public Object gradientAt(final Object x) {
            return StochasticDiffFunction.gradientAt$(this, x);
         }

         public double valueAt(final Object x) {
            return StochasticDiffFunction.valueAt$(this, x);
         }

         public final double apply(final Object x) {
            return StochasticDiffFunction.apply$(this, x);
         }

         public StochasticDiffFunction throughLens(final Isomorphism l) {
            return StochasticDiffFunction.throughLens$(this, l);
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

         public String toString() {
            return Function1.toString$(this);
         }

         private Rand rand() {
            return this.rand;
         }

         public Tuple2 calculate(final Object x) {
            return this.$outer.calculate(x, (IndexedSeq)this.rand().draw());
         }

         public {
            if (BatchDiffFunction.this == null) {
               throw null;
            } else {
               this.$outer = BatchDiffFunction.this;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
               this.rand = Rand$.MODULE$.subsetsOfSize(BatchDiffFunction.this.fullRange(), size$1);
            }
         }
      };
   }

   default StochasticDiffFunction withScanningBatches(final int size) {
      return new StochasticDiffFunction(size) {
         private int lastStop;
         // $FF: synthetic field
         private final BatchDiffFunction $outer;
         private final int size$2;

         public StochasticDiffFunction repr() {
            return StochasticDiffFunction.repr$(this);
         }

         public Object gradientAt(final Object x) {
            return StochasticDiffFunction.gradientAt$(this, x);
         }

         public double valueAt(final Object x) {
            return StochasticDiffFunction.valueAt$(this, x);
         }

         public final double apply(final Object x) {
            return StochasticDiffFunction.apply$(this, x);
         }

         public StochasticDiffFunction throughLens(final Isomorphism l) {
            return StochasticDiffFunction.throughLens$(this, l);
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

         public String toString() {
            return Function1.toString$(this);
         }

         private int lastStop() {
            return this.lastStop;
         }

         private void lastStop_$eq(final int x$1) {
            this.lastStop = x$1;
         }

         private synchronized ArraySeq nextBatch() {
            int start = this.lastStop();
            this.lastStop_$eq(this.lastStop() + this.size$2);
            this.lastStop_$eq(this.lastStop() % this.$outer.fullRange().size());
            return .MODULE$.ArraySeq().unsafeWrapArray(scala.Array..MODULE$.tabulate(this.size$2, (JFunction1.mcII.sp)(i) -> BoxesRunTime.unboxToInt(this.$outer.fullRange().apply((i + start) % this.$outer.fullRange().size())), scala.reflect.ClassTag..MODULE$.Int()));
         }

         public Tuple2 calculate(final Object x) {
            return this.$outer.calculate(x, this.nextBatch());
         }

         public {
            if (BatchDiffFunction.this == null) {
               throw null;
            } else {
               this.$outer = BatchDiffFunction.this;
               this.size$2 = size$2;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
               this.lastStop = 0;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   default BatchDiffFunction groupItems(final int groupSize) {
      return new BatchDiffFunction(groupSize) {
         private final int numGroups;
         private final IndexedSeq[] groups;
         // $FF: synthetic field
         private final BatchDiffFunction $outer;

         public Tuple2 calculate(final Object x) {
            return BatchDiffFunction.super.calculate(x);
         }

         public double valueAt(final Object x) {
            return BatchDiffFunction.super.valueAt(x);
         }

         public Object gradientAt(final Object x) {
            return BatchDiffFunction.super.gradientAt(x);
         }

         public double apply(final Object x, final IndexedSeq batch) {
            return BatchDiffFunction.super.apply(x, batch);
         }

         public DiffFunction cached(final CanCopy copy) {
            return BatchDiffFunction.super.cached(copy);
         }

         public StochasticDiffFunction withRandomBatches(final int size) {
            return BatchDiffFunction.super.withRandomBatches(size);
         }

         public StochasticDiffFunction withScanningBatches(final int size) {
            return BatchDiffFunction.super.withScanningBatches(size);
         }

         public BatchDiffFunction groupItems(final int groupSize) {
            return BatchDiffFunction.super.groupItems(groupSize);
         }

         public BatchDiffFunction throughLens(final Isomorphism l) {
            return BatchDiffFunction.super.throughLens(l);
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

         private int numGroups() {
            return this.numGroups;
         }

         private IndexedSeq[] groups() {
            return this.groups;
         }

         public Tuple2 calculate(final Object x, final IndexedSeq batch) {
            return this.$outer.calculate(x, (IndexedSeq)batch.flatMap(scala.Predef..MODULE$.wrapRefArray((Object[])this.groups())));
         }

         public Object gradientAt(final Object x, final IndexedSeq batch) {
            return this.$outer.gradientAt(x, (IndexedSeq)batch.flatMap(scala.Predef..MODULE$.wrapRefArray((Object[])this.groups())));
         }

         public double valueAt(final Object x, final IndexedSeq batch) {
            return this.$outer.valueAt(x, (IndexedSeq)batch.flatMap(scala.Predef..MODULE$.wrapRefArray((Object[])this.groups())));
         }

         public IndexedSeq fullRange() {
            return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.groups().length);
         }

         // $FF: synthetic method
         public static final IndexedSeq $anonfun$groups$1(final Object $this, final int i) {
            return scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(i), $this.$outer.fullRange().length()).by($this.numGroups()).map($this.$outer.fullRange());
         }

         public {
            if (BatchDiffFunction.this == null) {
               throw null;
            } else {
               this.$outer = BatchDiffFunction.this;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
               DiffFunction.$init$(this);
               Function2.$init$(this);
               BatchDiffFunction.$init$(this);
               this.numGroups = (BatchDiffFunction.this.fullRange().size() + groupSize$1 - 1) / groupSize$1;
               this.groups = (IndexedSeq[])scala.Array..MODULE$.tabulate(this.numGroups(), (i) -> $anonfun$groups$1(this, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(IndexedSeq.class));
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   default BatchDiffFunction throughLens(final Isomorphism l) {
      return new BatchDiffFunction(l) {
         // $FF: synthetic field
         private final BatchDiffFunction $outer;
         private final Isomorphism l$1;

         public Object gradientAt(final Object x, final IndexedSeq batch) {
            return BatchDiffFunction.super.gradientAt(x, batch);
         }

         public double valueAt(final Object x, final IndexedSeq batch) {
            return BatchDiffFunction.super.valueAt(x, batch);
         }

         public double valueAt(final Object x) {
            return BatchDiffFunction.super.valueAt(x);
         }

         public Object gradientAt(final Object x) {
            return BatchDiffFunction.super.gradientAt(x);
         }

         public double apply(final Object x, final IndexedSeq batch) {
            return BatchDiffFunction.super.apply(x, batch);
         }

         public DiffFunction cached(final CanCopy copy) {
            return BatchDiffFunction.super.cached(copy);
         }

         public StochasticDiffFunction withRandomBatches(final int size) {
            return BatchDiffFunction.super.withRandomBatches(size);
         }

         public StochasticDiffFunction withScanningBatches(final int size) {
            return BatchDiffFunction.super.withScanningBatches(size);
         }

         public BatchDiffFunction groupItems(final int groupSize) {
            return BatchDiffFunction.super.groupItems(groupSize);
         }

         public BatchDiffFunction throughLens(final Isomorphism l) {
            return BatchDiffFunction.super.throughLens(l);
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

         public Tuple2 calculate(final Object u, final IndexedSeq batch) {
            Object t = this.l$1.backward(u);
            Tuple2 var6 = this.$outer.calculate(t, batch);
            if (var6 != null) {
               double obj = var6._1$mcD$sp();
               Object gu = var6._2();
               Tuple2 var3 = new Tuple2(BoxesRunTime.boxToDouble(obj), gu);
               double objx = var3._1$mcD$sp();
               Object gu = var3._2();
               return new Tuple2(BoxesRunTime.boxToDouble(objx), this.l$1.forward(gu));
            } else {
               throw new MatchError(var6);
            }
         }

         public IndexedSeq fullRange() {
            return this.$outer.fullRange();
         }

         public Tuple2 calculate(final Object u) {
            Object t = this.l$1.backward(u);
            Tuple2 var5 = this.$outer.calculate(t);
            if (var5 != null) {
               double obj = var5._1$mcD$sp();
               Object gu = var5._2();
               Tuple2 var2 = new Tuple2(BoxesRunTime.boxToDouble(obj), gu);
               double objx = var2._1$mcD$sp();
               Object gu = var2._2();
               return new Tuple2(BoxesRunTime.boxToDouble(objx), this.l$1.forward(gu));
            } else {
               throw new MatchError(var5);
            }
         }

         public {
            if (BatchDiffFunction.this == null) {
               throw null;
            } else {
               this.$outer = BatchDiffFunction.this;
               this.l$1 = l$1;
               Function1.$init$(this);
               ImmutableNumericOps.$init$(this);
               NumericOps.$init$(this);
               StochasticDiffFunction.$init$(this);
               DiffFunction.$init$(this);
               Function2.$init$(this);
               BatchDiffFunction.$init$(this);
            }
         }
      };
   }

   static void $init$(final BatchDiffFunction $this) {
   }
}
