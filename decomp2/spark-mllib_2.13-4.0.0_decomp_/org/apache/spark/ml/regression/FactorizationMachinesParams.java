package org.apache.spark.ml.regression;

import org.apache.spark.ml.PredictorParams;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasFitIntercept;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasRegParam;
import org.apache.spark.ml.param.shared.HasSeed;
import org.apache.spark.ml.param.shared.HasSolver;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasTol;
import org.apache.spark.ml.param.shared.HasWeightCol;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001C\u0006\r!\u0003\r\tA\u0004\f\t\u000by\u0002A\u0011\u0001!\t\u000f\u0011\u0003!\u0019!C\u0003\u000b\")1\u000b\u0001C\u0003)\"9\u0011\f\u0001b\u0001\n\u000bQ\u0006\"B0\u0001\t\u000b\u0001\u0007bB3\u0001\u0005\u0004%)A\u001a\u0005\u0006W\u0002!)\u0001\u001c\u0005\bc\u0002\u0011\r\u0011\"\u0002g\u0011\u0015\u0019\b\u0001\"\u0002m\u0011\u001d)\bA1A\u0005FY\u00141DR1di>\u0014\u0018N_1uS>tW*Y2iS:,7\u000fU1sC6\u001c(BA\u0007\u000f\u0003)\u0011Xm\u001a:fgNLwN\u001c\u0006\u0003\u001fA\t!!\u001c7\u000b\u0005E\u0011\u0012!B:qCJ\\'BA\n\u0015\u0003\u0019\t\u0007/Y2iK*\tQ#A\u0002pe\u001e\u001c2\u0002A\f\u001eC%bsFM\u001b9wA\u0011\u0001dG\u0007\u00023)\t!$A\u0003tG\u0006d\u0017-\u0003\u0002\u001d3\t1\u0011I\\=SK\u001a\u0004\"AH\u0010\u000e\u00039I!\u0001\t\b\u0003\u001fA\u0013X\rZ5di>\u0014\b+\u0019:b[N\u0004\"AI\u0014\u000e\u0003\rR!\u0001J\u0013\u0002\rMD\u0017M]3e\u0015\t1c\"A\u0003qCJ\fW.\u0003\u0002)G\tQ\u0001*Y:NCbLE/\u001a:\u0011\u0005\tR\u0013BA\u0016$\u0005-A\u0015m]*uKB\u001c\u0016N_3\u0011\u0005\tj\u0013B\u0001\u0018$\u0005\u0019A\u0015m\u001d+pYB\u0011!\u0005M\u0005\u0003c\r\u0012\u0011\u0002S1t'>dg/\u001a:\u0011\u0005\t\u001a\u0014B\u0001\u001b$\u0005\u001dA\u0015m]*fK\u0012\u0004\"A\t\u001c\n\u0005]\u001a#a\u0004%bg\u001aKG/\u00138uKJ\u001cW\r\u001d;\u0011\u0005\tJ\u0014B\u0001\u001e$\u0005-A\u0015m\u001d*fOB\u000b'/Y7\u0011\u0005\tb\u0014BA\u001f$\u00051A\u0015m],fS\u001eDGoQ8m\u0003\u0019!\u0013N\\5uI\r\u0001A#A!\u0011\u0005a\u0011\u0015BA\"\u001a\u0005\u0011)f.\u001b;\u0002\u0015\u0019\f7\r^8s'&TX-F\u0001G!\t9\u0005*D\u0001&\u0013\tIUE\u0001\u0005J]R\u0004\u0016M]1nQ\r\u00111*\u0015\t\u0003\u0019>k\u0011!\u0014\u0006\u0003\u001dB\t!\"\u00198o_R\fG/[8o\u0013\t\u0001VJA\u0003TS:\u001cW-I\u0001S\u0003\u0015\u0019d\u0006\r\u00181\u000359W\r\u001e$bGR|'oU5{KV\tQ\u000b\u0005\u0002\u0019-&\u0011q+\u0007\u0002\u0004\u0013:$\bfA\u0002L#\u0006Ia-\u001b;MS:,\u0017M]\u000b\u00027B\u0011q\tX\u0005\u0003;\u0016\u0012ABQ8pY\u0016\fg\u000eU1sC6D3\u0001B&R\u000319W\r\u001e$ji2Kg.Z1s+\u0005\t\u0007C\u0001\rc\u0013\t\u0019\u0017DA\u0004C_>dW-\u00198)\u0007\u0015Y\u0015+A\tnS:L')\u0019;dQ\u001a\u0013\u0018m\u0019;j_:,\u0012a\u001a\t\u0003\u000f\"L!![\u0013\u0003\u0017\u0011{WO\u00197f!\u0006\u0014\u0018-\u001c\u0015\u0004\r-\u000b\u0016\u0001F4fi6Kg.\u001b\"bi\u000eDgI]1di&|g.F\u0001n!\tAb.\u0003\u0002p3\t1Ai\\;cY\u0016D3aB&R\u0003\u001dIg.\u001b;Ti\u0012D3\u0001C&R\u0003)9W\r^%oSR\u001cF\u000f\u001a\u0015\u0004\u0013-\u000b\u0016AB:pYZ,'/F\u0001x!\r9\u0005P_\u0005\u0003s\u0016\u0012Q\u0001U1sC6\u00042a_A\u0003\u001d\ra\u0018\u0011\u0001\t\u0003{fi\u0011A \u0006\u0003\u007f~\na\u0001\u0010:p_Rt\u0014bAA\u00023\u00051\u0001K]3eK\u001aLA!a\u0002\u0002\n\t11\u000b\u001e:j]\u001eT1!a\u0001\u001aQ\rQ1*\u0015"
)
public interface FactorizationMachinesParams extends PredictorParams, HasMaxIter, HasStepSize, HasTol, HasSolver, HasSeed, HasFitIntercept, HasRegParam, HasWeightCol {
   void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$factorSize_$eq(final IntParam x$1);

   void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$fitLinear_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$miniBatchFraction_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$initStd_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$solver_$eq(final Param x$1);

   IntParam factorSize();

   // $FF: synthetic method
   static int getFactorSize$(final FactorizationMachinesParams $this) {
      return $this.getFactorSize();
   }

   default int getFactorSize() {
      return BoxesRunTime.unboxToInt(this.$(this.factorSize()));
   }

   BooleanParam fitLinear();

   // $FF: synthetic method
   static boolean getFitLinear$(final FactorizationMachinesParams $this) {
      return $this.getFitLinear();
   }

   default boolean getFitLinear() {
      return BoxesRunTime.unboxToBoolean(this.$(this.fitLinear()));
   }

   DoubleParam miniBatchFraction();

   // $FF: synthetic method
   static double getMiniBatchFraction$(final FactorizationMachinesParams $this) {
      return $this.getMiniBatchFraction();
   }

   default double getMiniBatchFraction() {
      return BoxesRunTime.unboxToDouble(this.$(this.miniBatchFraction()));
   }

   DoubleParam initStd();

   // $FF: synthetic method
   static double getInitStd$(final FactorizationMachinesParams $this) {
      return $this.getInitStd();
   }

   default double getInitStd() {
      return BoxesRunTime.unboxToDouble(this.$(this.initStd()));
   }

   Param solver();

   static void $init$(final FactorizationMachinesParams $this) {
      $this.org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$factorSize_$eq(new IntParam($this, "factorSize", "Dimensionality of the factor vectors, which are used to get pairwise interactions between variables", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$fitLinear_$eq(new BooleanParam($this, "fitLinear", "whether to fit linear term (aka 1-way term)"));
      $this.org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$miniBatchFraction_$eq(new DoubleParam($this, "miniBatchFraction", "fraction of the input data set that should be used for one iteration of gradient descent", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F, false, true)));
      $this.org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$initStd_$eq(new DoubleParam($this, "initStd", "standard deviation of initial coefficients", ParamValidators$.MODULE$.gt((double)0.0F)));
      $this.org$apache$spark$ml$regression$FactorizationMachinesParams$_setter_$solver_$eq(new Param($this, "solver", "The solver algorithm for optimization. Supported options: " + .MODULE$.wrapRefArray((Object[])FactorizationMachines$.MODULE$.supportedSolvers()).mkString(", ") + ". (Default adamW)", ParamValidators$.MODULE$.inArray((Object)FactorizationMachines$.MODULE$.supportedSolvers()), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.factorSize().$minus$greater(BoxesRunTime.boxToInteger(8)), $this.fitIntercept().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.fitLinear().$minus$greater(BoxesRunTime.boxToBoolean(true)), $this.regParam().$minus$greater(BoxesRunTime.boxToDouble((double)0.0F)), $this.miniBatchFraction().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F)), $this.initStd().$minus$greater(BoxesRunTime.boxToDouble(0.01)), $this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(100)), $this.stepSize().$minus$greater(BoxesRunTime.boxToDouble((double)1.0F)), $this.tol().$minus$greater(BoxesRunTime.boxToDouble(1.0E-6)), $this.solver().$minus$greater(FactorizationMachines$.MODULE$.AdamW())}));
   }
}
