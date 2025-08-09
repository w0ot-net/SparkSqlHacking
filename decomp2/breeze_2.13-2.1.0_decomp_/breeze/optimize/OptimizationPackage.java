package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.support.CanMapValues;
import breeze.linalg.support.CanTraverseValues;
import breeze.linalg.support.CanZipMapValues;
import breeze.math.CoordinateField;
import breeze.math.MutableFiniteCoordinateField;
import breeze.util.Implicits$;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t]da\u0002\r\u001a!\u0003\r\nA\b\u0005\u0006M\u00011\taJ\u0004\u0006\u0007fA\t\u0001\u0012\u0004\u00061eA\t!\u0012\u0005\u0006\u0013\u000e!\tA\u0013\u0004\u0005\u0017\u000e\u0001A\n\u0003\u0005`\u000b\t\u0005\t\u0015a\u0003a\u0011!iWA!A!\u0002\u0017q\u0007\"B%\u0006\t\u0003i\bB\u0002\u0014\u0006\t\u0003\ty\u0001C\u0004\u0002\u0018\u0015!\t%!\u0007\t\u000f\u0005e2\u0001b\u0001\u0002<\u00191\u0011\u0011L\u0002\u0001\u00037B\u0011b\u0018\u0007\u0003\u0002\u0003\u0006Y!!\"\t\r%cA\u0011AAH\u0011\u00191C\u0002\"\u0001\u0002 \"9\u0011q\u0003\u0007\u0005B\u0005\u001d\u0006bBAa\u0007\u0011\r\u00111\u0019\u0004\u0007\u00033\u001c\u0001!a7\t\u0013}\u0013\"\u0011!Q\u0001\f\t}\u0001BB%\u0013\t\u0003\u0011I\u0003\u0003\u0004'%\u0011\u0005!\u0011\b\u0005\b\u0003/\u0011B\u0011\tB!\u0011\u001d\u0011yf\u0001C\u0002\u0005C\u00121c\u00149uS6L'0\u0019;j_:\u0004\u0016mY6bO\u0016T!AG\u000e\u0002\u0011=\u0004H/[7ju\u0016T\u0011\u0001H\u0001\u0007EJ,WM_3\u0004\u0001U\u0019qD\u000e\u0016\u0014\u0005\u0001\u0001\u0003CA\u0011%\u001b\u0005\u0011#\"A\u0012\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0015\u0012#AB!osJ+g-\u0001\u0005nS:LW.\u001b>f)\u0011A3\u0007\u000f\u001e\u0011\u0005%RC\u0002\u0001\u0003\u0006W\u0001\u0011\r\u0001\f\u0002\u0007-\u0016\u001cGo\u001c:\u0012\u00055\u0002\u0004CA\u0011/\u0013\ty#EA\u0004O_RD\u0017N\\4\u0011\u0005\u0005\n\u0014B\u0001\u001a#\u0005\r\te.\u001f\u0005\u0006i\u0005\u0001\r!N\u0001\u0003M:\u0004\"!\u000b\u001c\u0005\u000b]\u0002!\u0019\u0001\u0017\u0003\u0011\u0019+hn\u0019;j_:DQ!O\u0001A\u0002!\nA!\u001b8ji\")1(\u0001a\u0001y\u00059q\u000e\u001d;j_:\u001c\bcA\u0011>\u007f%\u0011aH\t\u0002\u000byI,\u0007/Z1uK\u0012t\u0004C\u0001!B\u001b\u0005I\u0012B\u0001\"\u001a\u0005Iy\u0005\u000f^5nSj\fG/[8o\u001fB$\u0018n\u001c8\u0002'=\u0003H/[7ju\u0006$\u0018n\u001c8QC\u000e\\\u0017mZ3\u0011\u0005\u0001\u001b1cA\u0002!\rB\u0011\u0001iR\u0005\u0003\u0011f\u0011ad\u00149uS6L'0\u0019;j_:\u0004\u0016mY6bO\u0016dun\u001e)sS>\u0014\u0018\u000e^=\u0002\rqJg.\u001b;?)\u0005!%AH*fG>tGm\u0014:eKJ|\u0005\u000f^5nSj\fG/[8o!\u0006\u001c7.Y4f+\riUkV\n\u0004\u000b\u0001r\u0005#\u0002!P#RK\u0016B\u0001)\u001a\u0005mIE/\u001a:bE2,w\n\u001d;j[&T\u0018\r^5p]B\u000b7m[1hKB!\u0001I\u0015+W\u0013\t\u0019\u0016DA\nTK\u000e|g\u000eZ(sI\u0016\u0014h)\u001e8di&|g\u000e\u0005\u0002*+\u0012)1&\u0002b\u0001YA\u0011\u0011f\u0016\u0003\u00061\u0016\u0011\r\u0001\f\u0002\b\u0011\u0016\u001c8/[1o!\tQV\f\u0005\u0003A7R3\u0016B\u0001/\u001a\u0005a!&/\u001e8dCR,GMT3xi>tW*\u001b8j[&TXM]\u0005\u0003=n\u0013Qa\u0015;bi\u0016\fQa\u001d9bG\u0016\u0004$!\u00195\u0011\u000b\t,Gk\u001a6\u000e\u0003\rT!\u0001Z\u000e\u0002\t5\fG\u000f[\u0005\u0003M\u000e\u0014A$T;uC\ndWMR5oSR,7i\\8sI&t\u0017\r^3GS\u0016dG\r\u0005\u0002*Q\u0012I\u0011NBA\u0001\u0002\u0003\u0015\t\u0001\f\u0002\u0004?\u0012\n\u0004CA\u0011l\u0013\ta'E\u0001\u0004E_V\u0014G.Z\u0001\u0005[VdG\u000fE\u0003poZ#FK\u0004\u0002qk6\t\u0011O\u0003\u0002sg\u0006Iq\u000e]3sCR|'o\u001d\u0006\u0003in\ta\u0001\\5oC2<\u0017B\u0001<r\u0003-y\u0005/T;m\u001b\u0006$(/\u001b=\n\u0005aL(!B%na2\u0014\u0014B\u0001>|\u0005\u0015)f)\u001e8d\u0015\ta8$A\u0004hK:,'/[2\u0015\u0003y$Ra`A\u0002\u0003\u001b\u0001R!!\u0001\u0006)Zk\u0011a\u0001\u0005\u0007?\"\u0001\u001d!!\u00021\t\u0005\u001d\u00111\u0002\t\u0007E\u0016$\u0016\u0011\u00026\u0011\u0007%\nY\u0001\u0002\u0006j\u0003\u0007\t\t\u0011!A\u0003\u00021BQ!\u001c\u0005A\u00049$r\u0001VA\t\u0003'\t)\u0002C\u00035\u0013\u0001\u0007\u0011\u000bC\u0003:\u0013\u0001\u0007A\u000bC\u0003<\u0013\u0001\u0007A(\u0001\u0006ji\u0016\u0014\u0018\r^5p]N$\u0002\"a\u0007\u00024\u0005U\u0012q\u0007\t\u0006\u0003;\ti#\u0017\b\u0005\u0003?\tIC\u0004\u0003\u0002\"\u0005\u001dRBAA\u0012\u0015\r\t)#H\u0001\u0007yI|w\u000e\u001e \n\u0003\rJ1!a\u000b#\u0003\u001d\u0001\u0018mY6bO\u0016LA!a\f\u00022\tA\u0011\n^3sCR|'OC\u0002\u0002,\tBQ\u0001\u000e\u0006A\u0002ECQ!\u000f\u0006A\u0002QCQa\u000f\u0006A\u0002q\n!c]3d_:$wJ\u001d3feB\u000b7m[1hKV1\u0011QHA\"\u0003\u000f\"b!a\u0010\u0002J\u0005U\u0003cBA\u0001\u000b\u0005\u0005\u0013Q\t\t\u0004S\u0005\rC!B\u0016\f\u0005\u0004a\u0003cA\u0015\u0002H\u0011)\u0001l\u0003b\u0001Y!1ql\u0003a\u0002\u0003\u0017\u0002D!!\u0014\u0002RA9!-ZA!\u0003\u001fR\u0007cA\u0015\u0002R\u0011Y\u00111KA%\u0003\u0003\u0005\tQ!\u0001-\u0005\ryFE\r\u0005\u0007[.\u0001\u001d!a\u0016\u0011\u0011=<\u0018QIA!\u0003\u0003\u0012qER5sgR|%\u000fZ3s'R|7\r[1ti&\u001cw\n\u001d;j[&T\u0018\r^5p]B\u000b7m[1hKV!\u0011QLA5'\u0011a\u0001%a\u0018\u0011\u0011\u0001{\u0015\u0011MA4\u0003W\u0002R\u0001QA2\u0003OJ1!!\u001a\u001a\u0005Y\u0019Fo\\2iCN$\u0018n\u0019#jM\u001a4UO\\2uS>t\u0007cA\u0015\u0002j\u0011)1\u0006\u0004b\u0001YA2\u0011QNA>\u0003\u0003\u0003\"\"a\u001c\u0002v\u0005\u001d\u0014\u0011PA@\u001d\r\u0001\u0015\u0011O\u0005\u0004\u0003gJ\u0012a\u0005$jeN$xJ\u001d3fe6Kg.[7ju\u0016\u0014\u0018b\u00010\u0002x)\u0019\u00111O\r\u0011\u0007%\nY\b\u0002\u0006\u0002~1\t\t\u0011!A\u0003\u00021\u00121a\u0018\u00135!\rI\u0013\u0011\u0011\u0003\u000b\u0003\u0007c\u0011\u0011!A\u0001\u0006\u0003a#aA0%kA\"\u0011qQAF!\u001d\u0011W-a\u001a\u0002\n*\u00042!KAF\t)\ti)DA\u0001\u0002\u0003\u0015\t\u0001\f\u0002\u0004?\u0012\u001aDCAAI)\u0011\t\u0019*!&\u0011\u000b\u0005\u0005A\"a\u001a\t\r}s\u00019AALa\u0011\tI*!(\u0011\u000f\t,\u0017qMANUB\u0019\u0011&!(\u0005\u0017\u00055\u0015QSA\u0001\u0002\u0003\u0015\t\u0001\f\u000b\t\u0003O\n\t+a)\u0002&\"1Ag\u0004a\u0001\u0003CBa!O\bA\u0002\u0005\u001d\u0004\"B\u001e\u0010\u0001\u0004aD\u0003CAU\u0003w\u000bi,a0\u0011\r\u0005u\u0011QFAVa\u0019\ti+!-\u00028BQ\u0011qNA;\u0003O\ny+!.\u0011\u0007%\n\t\f\u0002\u0006\u00024B\t\t\u0011!A\u0003\u00021\u00121a\u0018\u00137!\rI\u0013q\u0017\u0003\u000b\u0003s\u0003\u0012\u0011!A\u0001\u0006\u0003a#aA0%o!1A\u0007\u0005a\u0001\u0003CBa!\u000f\tA\u0002\u0005\u001d\u0004\"B\u001e\u0011\u0001\u0004a\u0014a\u00074jeN$xJ\u001d3feN#xn\u00195bgRL7\rU1dW\u0006<W-\u0006\u0003\u0002F\u0006-G\u0003BAd\u0003\u001b\u0004R!!\u0001\r\u0003\u0013\u00042!KAf\t\u0015Y\u0013C1\u0001-\u0011\u0019y\u0016\u0003q\u0001\u0002PB\"\u0011\u0011[Ak!\u001d\u0011W-!3\u0002T*\u00042!KAk\t-\t9.!4\u0002\u0002\u0003\u0005)\u0011\u0001\u0017\u0003\u0007}#\u0003H\u0001\u0012GSJ\u001cHo\u0014:eKJ\u0014\u0015\r^2i\u001fB$\u0018.\\5{CRLwN\u001c)bG.\fw-Z\u000b\u0005\u0003;\fIo\u0005\u0003\u0013A\u0005}\u0007\u0003\u0003!P\u0003C\f9/a;\u0011\u000b\u0001\u000b\u0019/a:\n\u0007\u0005\u0015\u0018DA\tCCR\u001c\u0007\u000eR5gM\u001a+hn\u0019;j_:\u00042!KAu\t\u0015Y#C1\u0001-a\u0011\ti/!>\u0011\u0015\u0005=\u0014QOAt\u0003_\u0014I\u0002\u0005\u0003\u0002r\nEa\u0002BAz\u0005\u001b\u00012!KA{\t-\t9\u0010AA\u0001\u0002\u0003\u0015\t!!@\u0003\u000f}\u000bd\u0006^=qK&\u0019\u00111`\r\u0003'\u0019K'o\u001d;Pe\u0012,'/T5oS6L'0\u001a:\u0012\u00075\nyP\u0005\u0004\u0003\u0002\t\u0015!q\u0001\u0004\u0007\u0005\u0007\u0001\u0001!a@\u0003\u0019q\u0012XMZ5oK6,g\u000e\u001e \u0011\u000f\u0001\u000bI0a:\u0002bB\u0019\u0011E!\u0003\n\u0007\t-!EA\u0005TS:<G.\u001a;p]&!!qBA}\u0003A\u0019wN\u001c<fe\u001e,gnY3DQ\u0016\u001c7.\u0003\u0003\u0003\u0014\tU!\u0001B%oM>LAAa\u0006\u0002x\t\u00012i\u001c8wKJ<WM\\2f\u0007\",7m\u001b\t\u0005\u0003g\u0014Y\"\u0003\u0003\u0003\u001e\u0005e(a\u0002%jgR|'/\u001f\u0019\u0005\u0005C\u0011)\u0003E\u0004cK\u0006\u001d(1\u00056\u0011\u0007%\u0012)\u0003\u0002\u0006\u0003(M\t\t\u0011!A\u0003\u00021\u00121a\u0018\u0013:)\t\u0011Y\u0003\u0006\u0003\u0003.\t=\u0002#BA\u0001%\u0005\u001d\bBB0\u0015\u0001\b\u0011\t\u0004\r\u0003\u00034\t]\u0002c\u00022f\u0003O\u0014)D\u001b\t\u0004S\t]Ba\u0003B\u0014\u0005_\t\t\u0011!A\u0003\u00021\"\u0002\"a:\u0003<\tu\"q\b\u0005\u0007iU\u0001\r!!9\t\re*\u0002\u0019AAt\u0011\u0015YT\u00031\u0001=)!\u0011\u0019E!\u0017\u0003\\\tu\u0003CBA\u000f\u0003[\u0011)\u0005\r\u0003\u0003H\t=\u0003CCA8\u0003k\n9O!\u0013\u0003XA!!1\nB\t\u001d\u0011\u0011iE!\u0004\u0011\u0007%\u0012y\u0005B\u0006\u0002x\u0002\t\t\u0011!A\u0003\u0002\tE\u0013cA\u0017\u0003TI1!Q\u000bB\u0003\u0005\u000f1aAa\u0001\u0001\u0001\tM\u0003\u0003\u0002B'\u00057Aa\u0001\u000e\fA\u0002\u0005\u0005\bBB\u001d\u0017\u0001\u0004\t9\u000fC\u0003<-\u0001\u0007A(\u0001\fgSJ\u001cHo\u0014:eKJ\u0014\u0015\r^2i!\u0006\u001c7.Y4f+\u0011\u0011\u0019G!\u001b\u0015\t\t\u0015$1\u000e\t\u0006\u0003\u0003\u0011\"q\r\t\u0004S\t%D!B\u0016\u0018\u0005\u0004a\u0003BB0\u0018\u0001\b\u0011i\u0007\r\u0003\u0003p\tM\u0004c\u00022f\u0005O\u0012\tH\u001b\t\u0004S\tMDa\u0003B;\u0005W\n\t\u0011!A\u0003\u00021\u0012Aa\u0018\u00132a\u0001"
)
public interface OptimizationPackage {
   static FirstOrderBatchOptimizationPackage firstOrderBatchPackage(final MutableFiniteCoordinateField space) {
      return OptimizationPackage$.MODULE$.firstOrderBatchPackage(space);
   }

   static FirstOrderStochasticOptimizationPackage firstOrderStochasticPackage(final MutableFiniteCoordinateField space) {
      return OptimizationPackage$.MODULE$.firstOrderStochasticPackage(space);
   }

   static SecondOrderOptimizationPackage secondOrderPackage(final MutableFiniteCoordinateField space, final UFunc.UImpl2 mult) {
      return OptimizationPackage$.MODULE$.secondOrderPackage(space, mult);
   }

   static OptimizationPackageLowPriority.LBFGSMinimizationPackage lbfgsMinimizationPackage(final MutableFiniteCoordinateField space, final .less.colon.less df) {
      return OptimizationPackage$.MODULE$.lbfgsMinimizationPackage(space, df);
   }

   static OptimizationPackageLowPriority2.ImmutableFirstOrderOptimizationPackage imFirstOrderPackage(final CoordinateField space, final CanTraverseValues canIterate, final CanMapValues canMap, final CanZipMapValues canZipMap, final .less.colon.less df) {
      return OptimizationPackage$.MODULE$.imFirstOrderPackage(space, canIterate, canMap, canZipMap, df);
   }

   Object minimize(final Object fn, final Object init, final Seq options);

   public static class SecondOrderOptimizationPackage implements IterableOptimizationPackage {
      private final MutableFiniteCoordinateField space;
      private final UFunc.UImpl2 mult;

      public Object minimize(final SecondOrderFunction fn, final Object init, final Seq options) {
         return ((TruncatedNewtonMinimizer.State)Implicits$.MODULE$.scEnrichIterator(this.iterations(fn, init, options)).last()).x();
      }

      public Iterator iterations(final SecondOrderFunction fn, final Object init, final Seq options) {
         FirstOrderMinimizer.OptParams params = (FirstOrderMinimizer.OptParams)options.foldLeft(new FirstOrderMinimizer.OptParams(FirstOrderMinimizer.OptParams$.MODULE$.apply$default$1(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$2(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$3(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$4(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$5(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$6(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$7(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$8()), (a, b) -> (FirstOrderMinimizer.OptParams)b.apply(a));
         if (params.useL1()) {
            throw new UnsupportedOperationException("Can't use L1 with second order optimizer right now");
         } else {
            TruncatedNewtonMinimizer minimizer = new TruncatedNewtonMinimizer(params.maxIterations(), params.tolerance(), params.regularization(), TruncatedNewtonMinimizer$.MODULE$.$lessinit$greater$default$4(), this.space, this.mult);
            return minimizer.iterations(fn, init);
         }
      }

      public SecondOrderOptimizationPackage(final MutableFiniteCoordinateField space, final UFunc.UImpl2 mult) {
         this.space = space;
         this.mult = mult;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class FirstOrderStochasticOptimizationPackage implements IterableOptimizationPackage {
      private final MutableFiniteCoordinateField space;

      public Object minimize(final StochasticDiffFunction fn, final Object init, final Seq options) {
         return ((FirstOrderMinimizer.State)Implicits$.MODULE$.scEnrichIterator(this.iterations(fn, init, options)).last()).x();
      }

      public Iterator iterations(final StochasticDiffFunction fn, final Object init, final Seq options) {
         return ((FirstOrderMinimizer.OptParams)options.foldLeft(new FirstOrderMinimizer.OptParams(FirstOrderMinimizer.OptParams$.MODULE$.apply$default$1(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$2(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$3(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$4(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$5(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$6(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$7(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$8()), (a, b) -> (FirstOrderMinimizer.OptParams)b.apply(a))).iterations(fn, init, this.space);
      }

      public FirstOrderStochasticOptimizationPackage(final MutableFiniteCoordinateField space) {
         this.space = space;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public static class FirstOrderBatchOptimizationPackage implements IterableOptimizationPackage {
      private final MutableFiniteCoordinateField space;

      public Object minimize(final BatchDiffFunction fn, final Object init, final Seq options) {
         return ((FirstOrderMinimizer.State)Implicits$.MODULE$.scEnrichIterator(this.iterations(fn, init, options)).last()).x();
      }

      public Iterator iterations(final BatchDiffFunction fn, final Object init, final Seq options) {
         return ((FirstOrderMinimizer.OptParams)options.foldLeft(new FirstOrderMinimizer.OptParams(FirstOrderMinimizer.OptParams$.MODULE$.apply$default$1(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$2(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$3(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$4(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$5(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$6(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$7(), FirstOrderMinimizer.OptParams$.MODULE$.apply$default$8()), (a, b) -> (FirstOrderMinimizer.OptParams)b.apply(a))).iterations((BatchDiffFunction)(new CachedBatchDiffFunction(fn, this.space.copy())), init, (MutableFiniteCoordinateField)this.space);
      }

      public FirstOrderBatchOptimizationPackage(final MutableFiniteCoordinateField space) {
         this.space = space;
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
