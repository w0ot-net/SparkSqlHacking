package breeze.optimize;

import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.CoordinateField;
import breeze.math.InnerProductModule;
import breeze.math.MutableInnerProductModule;
import breeze.math.MutablizingAdaptor;
import breeze.math.MutablizingAdaptor$;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015aaB\u0006\r!\u0003\r\t#\u0005\u0005\u00061\u0001!\t!\u0007\u0004\u0005;\u0001\u0001a\u0004\u0003\u00053\u0005\t\u0005\t\u0015a\u00034\u0011!a$A!A!\u0002\u0017i\u0004\u0002C#\u0003\u0005\u0003\u0005\u000b1\u0002$\t\u0011%\u0013!\u0011!Q\u0001\f)C\u0001\"\u0014\u0002\u0003\u0002\u0003\u0006YA\u0014\u0005\u0006)\n!\t!\u0016\u0005\u0006=\n!\ta\u0018\u0005\u0006Y\u0002!\u0019!\u001c\u0002 \u001fB$\u0018.\\5{CRLwN\u001c)bG.\fw-\u001a'poB\u0013\u0018n\u001c:jif\u0014$BA\u0007\u000f\u0003!y\u0007\u000f^5nSj,'\"A\b\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019\"\u0001\u0001\n\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0004\u0005\u0002\u00147%\u0011A\u0004\u0006\u0002\u0005+:LGO\u0001\u0014J[6,H/\u00192mK\u001aK'o\u001d;Pe\u0012,'o\u00149uS6L'0\u0019;j_:\u0004\u0016mY6bO\u0016,2a\b\u00141'\r\u0011!\u0003\t\t\u0005C\t\"s&D\u0001\r\u0013\t\u0019CBA\nPaRLW.\u001b>bi&|g\u000eU1dW\u0006<W\r\u0005\u0002&M1\u0001A!B\u0014\u0003\u0005\u0004A#A\u0001#G#\tIC\u0006\u0005\u0002\u0014U%\u00111\u0006\u0006\u0002\b\u001d>$\b.\u001b8h!\t\u0019R&\u0003\u0002/)\t\u0019\u0011I\\=\u0011\u0005\u0015\u0002D!B\u0019\u0003\u0005\u0004A#A\u0002,fGR|'/A\u0003ta\u0006\u001cW\r\u0005\u00035o=JT\"A\u001b\u000b\u0005Yr\u0011\u0001B7bi\"L!\u0001O\u001b\u0003\u001f\r{wN\u001d3j]\u0006$XMR5fY\u0012\u0004\"a\u0005\u001e\n\u0005m\"\"A\u0002#pk\ndW-\u0001\u0006dC:LE/\u001a:bi\u0016\u0004BAP\"0s5\tqH\u0003\u0002A\u0003\u000691/\u001e9q_J$(B\u0001\"\u000f\u0003\u0019a\u0017N\\1mO&\u0011Ai\u0010\u0002\u0012\u0007\u0006tGK]1wKJ\u001cXMV1mk\u0016\u001c\u0018AB2b]6\u000b\u0007\u000f\u0005\u0004?\u000f>J\u0014hL\u0005\u0003\u0011~\u0012AbQ1o\u001b\u0006\u0004h+\u00197vKN\f\u0011bY1o5&\u0004X*\u00199\u0011\ryZu&O\u001d0\u0013\tauHA\bDC:T\u0016\u000e]'baZ\u000bG.^3t\u0003\t!g\r\u0005\u0003\u0014\u001f\u0012\n\u0016B\u0001)\u0015\u0005A!C.Z:tI\r|Gn\u001c8%Y\u0016\u001c8\u000fE\u0002\"%>J!a\u0015\u0007\u0003\u0019\u0011KgM\u001a$v]\u000e$\u0018n\u001c8\u0002\rqJg.\u001b;?)\u00051FCB,Z5ncV\f\u0005\u0003Y\u0005\u0011zS\"\u0001\u0001\t\u000bIB\u00019A\u001a\t\u000bqB\u00019A\u001f\t\u000b\u0015C\u00019\u0001$\t\u000b%C\u00019\u0001&\t\u000b5C\u00019\u0001(\u0002\u00115Lg.[7ju\u0016$Ba\f1cI\")\u0011-\u0003a\u0001I\u0005\u0011aM\u001c\u0005\u0006G&\u0001\raL\u0001\u0005S:LG\u000fC\u0003f\u0013\u0001\u0007a-A\u0004paRLwN\\:\u0011\u0007M9\u0017.\u0003\u0002i)\tQAH]3qK\u0006$X\r\u001a \u0011\u0005\u0005R\u0017BA6\r\u0005Iy\u0005\u000f^5nSj\fG/[8o\u001fB$\u0018n\u001c8\u0002'%lg)\u001b:ti>\u0013H-\u001a:QC\u000e\\\u0017mZ3\u0016\u00079\f8\u000f\u0006\u0004piZD(\u0010 \t\u00051\n\u0001(\u000f\u0005\u0002&c\u0012)qE\u0003b\u0001QA\u0011Qe\u001d\u0003\u0006c)\u0011\r\u0001\u000b\u0005\u0006e)\u0001\u001d!\u001e\t\u0005i]\u0012\u0018\bC\u0003=\u0015\u0001\u000fq\u000f\u0005\u0003?\u0007JL\u0004\"B#\u000b\u0001\bI\bC\u0002 HefJ$\u000fC\u0003J\u0015\u0001\u000f1\u0010\u0005\u0004?\u0017JL\u0014H\u001d\u0005\u0006\u001b*\u0001\u001d! \t\u0005'=\u0003h\u0010E\u0002\"%JL3\u0001AA\u0001\u0013\r\t\u0019\u0001\u0004\u0002\u001f\u001fB$\u0018.\\5{CRLwN\u001c)bG.\fw-\u001a'poB\u0013\u0018n\u001c:jif\u0004"
)
public interface OptimizationPackageLowPriority2 {
   // $FF: synthetic method
   static ImmutableFirstOrderOptimizationPackage imFirstOrderPackage$(final OptimizationPackageLowPriority2 $this, final CoordinateField space, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap, final .less.colon.less df) {
      return $this.imFirstOrderPackage(space, canIterate, canMap, canZipMap, df);
   }

   default ImmutableFirstOrderOptimizationPackage imFirstOrderPackage(final CoordinateField space, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap, final .less.colon.less df) {
      return new ImmutableFirstOrderOptimizationPackage(space, canIterate, canMap, canZipMap, df);
   }

   static void $init$(final OptimizationPackageLowPriority2 $this) {
   }

   public class ImmutableFirstOrderOptimizationPackage implements OptimizationPackage {
      private final CoordinateField space;
      private final CanTraverseValues canIterate;
      private final CanMapValues canMap;
      private final CanZipMapValues canZipMap;
      private final .less.colon.less df;
      // $FF: synthetic field
      public final OptimizationPackageLowPriority2 $outer;

      public Object minimize(final Object fn, final Object init, final Seq options) {
         MutablizingAdaptor mut = MutablizingAdaptor$.MODULE$.ensureMutable(this.space, this.canIterate, this.canMap, this.canZipMap);
         DiffFunction wrapped = ((DiffFunction)this.df.apply(fn)).throughLens(mut.isomorphism());
         FirstOrderMinimizer.OptParams params = (FirstOrderMinimizer.OptParams)options.foldLeft(new FirstOrderMinimizer.OptParams(FirstOrderMinimizer.OptParams$.MODULE$.apply$default$1(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$2(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$3(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$4(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$5(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$6(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$7(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$8()), (a, b) -> (FirstOrderMinimizer.OptParams)b.apply(a));
         scala.Predef..MODULE$.require(!params.useL1(), () -> "Sorry, we can't use L1 with immutable objects right now...");
         double x$1 = params.tolerance();
         int x$2 = params.maxIterations();
         int x$3 = LBFGS$.MODULE$.$lessinit$greater$default$2();
         LBFGS lbfgs = new LBFGS(x$2, x$3, x$1, (MutableInnerProductModule)mut.mutaVspace());
         Object res = lbfgs.minimize(DiffFunction$.MODULE$.withL2Regularization(wrapped, params.regularization(), (InnerProductModule)mut.mutaVspace()), mut.wrap(init));
         return mut.unwrap(res);
      }

      // $FF: synthetic method
      public OptimizationPackageLowPriority2 breeze$optimize$OptimizationPackageLowPriority2$ImmutableFirstOrderOptimizationPackage$$$outer() {
         return this.$outer;
      }

      public ImmutableFirstOrderOptimizationPackage(final CoordinateField space, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap, final .less.colon.less df) {
         this.space = space;
         this.canIterate = canIterate;
         this.canMap = canMap;
         this.canZipMap = canZipMap;
         this.df = df;
         if (OptimizationPackageLowPriority2.this == null) {
            throw null;
         } else {
            this.$outer = OptimizationPackageLowPriority2.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
