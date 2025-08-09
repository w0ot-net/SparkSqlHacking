package breeze.optimize;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterator;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\ra\u0001\u0002\u000e\u001c\u0001\u0001B\u0001b\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\t_\u0001\u0011\t\u0011)A\u0005a!A1\u0007\u0001B\u0001B\u0003%A\u0006\u0003\u00055\u0001\t\u0005\t\u0015!\u0003-\u0011!)\u0004A!A!\u0002\u0013a\u0003\u0002\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u0011]\u0002!\u0011!Q\u0001\n1B\u0001\u0002\u000f\u0001\u0003\u0002\u0003\u0006I\u0001\f\u0005\ts\u0001\u0011\t\u0011)A\u0005u!AQ\b\u0001B\u0001B\u0003%!\bC\u0003?\u0001\u0011\u0005q\bC\u0003L\u0001\u0011\u0005A\nC\u0004e\u0001E\u0005I\u0011A3\b\u000fA\\\u0012\u0011!E\u0001c\u001a9!dGA\u0001\u0012\u0003\u0011\b\"\u0002 \u0010\t\u0003\u0019\bb\u0002;\u0010#\u0003%\t!\u001e\u0005\bo>\t\n\u0011\"\u0001f\u0011\u001dAx\"%A\u0005\u0002\u0015Dq!_\b\u0012\u0002\u0013\u0005Q\rC\u0004{\u001fE\u0005I\u0011A3\t\u000fm|\u0011\u0013!C\u0001K\"9ApDI\u0001\n\u0003)\u0007bB?\u0010#\u0003%\tA \u0005\t\u0003\u0003y\u0011\u0013!C\u0001}\n1\")Y2liJ\f7m[5oO2Kg.Z*fCJ\u001c\u0007N\u0003\u0002\u001d;\u0005Aq\u000e\u001d;j[&TXMC\u0001\u001f\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\"OA\u0011!%J\u0007\u0002G)\tA%A\u0003tG\u0006d\u0017-\u0003\u0002'G\t1\u0011I\\=SK\u001a\u0004\"\u0001K\u0015\u000e\u0003mI!AK\u000e\u0003+\u0005\u0003\bO]8yS6\fG/\u001a'j]\u0016\u001cV-\u0019:dQ\u0006A\u0011N\\5uMZ\fG\u000e\u0005\u0002#[%\u0011af\t\u0002\u0007\t>,(\r\\3\u0002\u001b5\f\u00070\u0013;fe\u0006$\u0018n\u001c8t!\t\u0011\u0013'\u0003\u00023G\t\u0019\u0011J\u001c;\u0002\u0015MD'/\u001b8l'R,\u0007/\u0001\u0005he><8\u000b^3q\u0003\u001d\u0019\u0017I]7jU>\faaY,pY\u001a,\u0017\u0001C7j]\u0006c\u0007\u000f[1\u0002\u00115\f\u00070\u00117qQ\u0006\fa#\u001a8g_J\u001cWmV8mM\u0016\u001cuN\u001c3ji&|gn\u001d\t\u0003EmJ!\u0001P\u0012\u0003\u000f\t{w\u000e\\3b]\u0006aRM\u001c4pe\u000e,7\u000b\u001e:p]\u001e<v\u000e\u001c4f\u0007>tG-\u001b;j_:\u001c\u0018A\u0002\u001fj]&$h\bF\u0006A\u0003\n\u001bE)\u0012$H\u0011&S\u0005C\u0001\u0015\u0001\u0011\u0015Y3\u00021\u0001-\u0011\u001dy3\u0002%AA\u0002ABqaM\u0006\u0011\u0002\u0003\u0007A\u0006C\u00045\u0017A\u0005\t\u0019\u0001\u0017\t\u000fUZ\u0001\u0013!a\u0001Y!9ag\u0003I\u0001\u0002\u0004a\u0003bB\u001c\f!\u0003\u0005\r\u0001\f\u0005\bq-\u0001\n\u00111\u0001-\u0011\u001dI4\u0002%AA\u0002iBq!P\u0006\u0011\u0002\u0003\u0007!(\u0001\u0006ji\u0016\u0014\u0018\r^5p]N$2!T/c!\rqe+\u0017\b\u0003\u001fRs!\u0001U*\u000e\u0003ES!AU\u0010\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0013BA+$\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0016-\u0003\u0011%#XM]1u_JT!!V\u0012\u0011\u0005i[V\"\u0001\u0001\n\u0005qK#!B*uCR,\u0007\"\u00020\r\u0001\u0004y\u0016!\u00014\u0011\u0007!\u0002G&\u0003\u0002b7\taA)\u001b4g\rVt7\r^5p]\"91\r\u0004I\u0001\u0002\u0004a\u0013\u0001B5oSR\fA#\u001b;fe\u0006$\u0018n\u001c8tI\u0011,g-Y;mi\u0012\u0012T#\u00014+\u00051:7&\u00015\u0011\u0005%tW\"\u00016\u000b\u0005-d\u0017!C;oG\",7m[3e\u0015\ti7%\u0001\u0006b]:|G/\u0019;j_:L!a\u001c6\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\fCC\u000e\\GO]1dW&tw\rT5oKN+\u0017M]2i!\tAsb\u0005\u0002\u0010CQ\t\u0011/A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HEM\u000b\u0002m*\u0012\u0001gZ\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001a\u00027\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00135\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%k\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIY\n1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012:\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$\u0003(A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H%O\u000b\u0002\u007f*\u0012!hZ\u0001\u001dI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u00191\u0001"
)
public class BacktrackingLineSearch implements ApproximateLineSearch {
   private final double initfval;
   private final int maxIterations;
   private final double shrinkStep;
   private final double growStep;
   private final double cArmijo;
   private final double cWolfe;
   private final double minAlpha;
   private final double maxAlpha;
   private final boolean enforceWolfeConditions;
   private final boolean enforceStrongWolfeConditions;
   private volatile ApproximateLineSearch.State$ State$module;

   public static boolean $lessinit$greater$default$10() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$10();
   }

   public static boolean $lessinit$greater$default$9() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$9();
   }

   public static double $lessinit$greater$default$8() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$8();
   }

   public static double $lessinit$greater$default$7() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$7();
   }

   public static double $lessinit$greater$default$6() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$6();
   }

   public static double $lessinit$greater$default$5() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$5();
   }

   public static double $lessinit$greater$default$4() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$4();
   }

   public static double $lessinit$greater$default$3() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$3();
   }

   public static int $lessinit$greater$default$2() {
      return BacktrackingLineSearch$.MODULE$.$lessinit$greater$default$2();
   }

   public double minimize(final DiffFunction f, final double init) {
      return ApproximateLineSearch.minimize$(this, f, init);
   }

   public double minimize$default$2() {
      return ApproximateLineSearch.minimize$default$2$(this);
   }

   public ApproximateLineSearch.State$ State() {
      if (this.State$module == null) {
         this.State$lzycompute$1();
      }

      return this.State$module;
   }

   public Iterator iterations(final DiffFunction f, final double init) {
      Tuple2 var6 = f.calculate(BoxesRunTime.boxToDouble((double)0.0F));
      if (var6 != null) {
         double f0 = var6._1$mcD$sp();
         double df0 = var6._2$mcD$sp();
         Tuple2.mcDD.sp var4 = new Tuple2.mcDD.sp(f0, df0);
         double f0 = ((Tuple2)var4)._1$mcD$sp();
         double df0 = ((Tuple2)var4)._2$mcD$sp();
         double initfderiv = f.calculate(BoxesRunTime.boxToDouble(init))._2$mcD$sp();
         return .MODULE$.Iterator().iterate(new Tuple3(new ApproximateLineSearch.State(init, this.initfval, initfderiv), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToInteger(0)), (x0$1) -> {
            if (x0$1 != null) {
               ApproximateLineSearch.State state = (ApproximateLineSearch.State)x0$1._1();
               int iter = BoxesRunTime.unboxToInt(x0$1._3());
               if (state != null) {
                  double alpha = state.alpha();
                  double fval = state.value();
                  double fderiv = state.deriv();
                  double multiplier = fval > f0 + alpha * df0 * this.cArmijo ? this.shrinkStep : (this.enforceWolfeConditions && fderiv < this.cWolfe * df0 ? this.growStep : (this.enforceStrongWolfeConditions && fderiv > -this.cWolfe * df0 ? this.shrinkStep : (double)1.0F));
                  Tuple3 var10000;
                  if (multiplier == (double)1.0F) {
                     var10000 = new Tuple3(state, BoxesRunTime.boxToBoolean(true), BoxesRunTime.boxToInteger(iter));
                  } else {
                     double newAlpha = alpha * multiplier;
                     if (iter >= this.maxIterations) {
                        throw new LineSearchFailed((double)0.0F, (double)0.0F);
                     }

                     if (newAlpha < this.minAlpha) {
                        throw new StepSizeUnderflow();
                     }

                     if (newAlpha > this.maxAlpha) {
                        throw new StepSizeOverflow();
                     }

                     Tuple2 var23 = f.calculate(BoxesRunTime.boxToDouble(newAlpha));
                     if (var23 == null) {
                        throw new MatchError(var23);
                     }

                     double fvalnew = var23._1$mcD$sp();
                     double fderivnew = var23._2$mcD$sp();
                     Tuple2.mcDD.sp var8 = new Tuple2.mcDD.sp(fvalnew, fderivnew);
                     double fvalnewx = ((Tuple2)var8)._1$mcD$sp();
                     double fderivnewx = ((Tuple2)var8)._2$mcD$sp();
                     var10000 = new Tuple3(this.new State(newAlpha, fvalnewx, fderivnewx), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToInteger(iter + 1));
                  }

                  Tuple3 var7 = var10000;
                  return var7;
               }
            }

            throw new MatchError(x0$1);
         }).takeWhile((triple) -> BoxesRunTime.boxToBoolean($anonfun$iterations$2(this, triple))).map((x$3) -> (ApproximateLineSearch.State)x$3._1());
      } else {
         throw new MatchError(var6);
      }
   }

   public double iterations$default$2() {
      return (double)1.0F;
   }

   private final void State$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.State$module == null) {
            this.State$module = new ApproximateLineSearch.State$();
         }
      } catch (Throwable var3) {
         throw var3;
      }

   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterations$2(final BacktrackingLineSearch $this, final Tuple3 triple) {
      return !BoxesRunTime.unboxToBoolean(triple._2()) && BoxesRunTime.unboxToInt(triple._3()) < $this.maxIterations;
   }

   public BacktrackingLineSearch(final double initfval, final int maxIterations, final double shrinkStep, final double growStep, final double cArmijo, final double cWolfe, final double minAlpha, final double maxAlpha, final boolean enforceWolfeConditions, final boolean enforceStrongWolfeConditions) {
      this.initfval = initfval;
      this.maxIterations = maxIterations;
      this.shrinkStep = shrinkStep;
      this.growStep = growStep;
      this.cArmijo = cArmijo;
      this.cWolfe = cWolfe;
      this.minAlpha = minAlpha;
      this.maxAlpha = maxAlpha;
      this.enforceWolfeConditions = enforceWolfeConditions;
      this.enforceStrongWolfeConditions = enforceStrongWolfeConditions;
      ApproximateLineSearch.$init$(this);
      scala.Predef..MODULE$.require(shrinkStep * growStep != (double)1.0F, () -> "Can't do a line search with growStep * shrinkStep == 1.0");
      scala.Predef..MODULE$.require(cArmijo < (double)0.5F);
      scala.Predef..MODULE$.require(cArmijo > (double)0.0F);
      scala.Predef..MODULE$.require(cWolfe > cArmijo);
      scala.Predef..MODULE$.require(cWolfe < (double)1.0F);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
