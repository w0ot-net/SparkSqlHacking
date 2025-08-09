package org.apache.spark.ml.tree;

import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasMaxIter;
import org.apache.spark.ml.param.shared.HasStepSize;
import org.apache.spark.ml.param.shared.HasValidationIndicatorCol;
import org.apache.spark.mllib.tree.configuration.BoostingStrategy;
import org.apache.spark.mllib.tree.configuration.Strategy;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.impurity.Variance$;
import org.apache.spark.mllib.tree.loss.Loss;
import scala.Enumeration;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=a\u0001\u0003\u0005\n!\u0003\r\taC\n\t\u000b1\u0002A\u0011\u0001\u0018\t\u000fI\u0002!\u0019!C\u0003g!)\u0011\t\u0001C\u0003\u0005\"9q\t\u0001b\u0001\n\u000b\u001a\u0004B\u0002%\u0001\t\u0003Y\u0011\n\u0003\u0004m\u0001\u0019\u00051\"\u001c\u0005\ri\u0002\u0001\n1!A\u0001\n\u0013)\u00181\u0002\u0002\n\u000f\n#\u0006+\u0019:b[NT!AC\u0006\u0002\tQ\u0014X-\u001a\u0006\u0003\u00195\t!!\u001c7\u000b\u00059y\u0011!B:qCJ\\'B\u0001\t\u0012\u0003\u0019\t\u0007/Y2iK*\t!#A\u0002pe\u001e\u001cb\u0001\u0001\u000b\u001b=\u0019J\u0003CA\u000b\u0019\u001b\u00051\"\"A\f\u0002\u000bM\u001c\u0017\r\\1\n\u0005e1\"AB!osJ+g\r\u0005\u0002\u001c95\t\u0011\"\u0003\u0002\u001e\u0013\t\u0011BK]3f\u000b:\u001cX-\u001c2mKB\u000b'/Y7t!\tyB%D\u0001!\u0015\t\t#%\u0001\u0004tQ\u0006\u0014X\r\u001a\u0006\u0003G-\tQ\u0001]1sC6L!!\n\u0011\u0003\u0015!\u000b7/T1y\u0013R,'\u000f\u0005\u0002 O%\u0011\u0001\u0006\t\u0002\f\u0011\u0006\u001c8\u000b^3q'&TX\r\u0005\u0002 U%\u00111\u0006\t\u0002\u001a\u0011\u0006\u001ch+\u00197jI\u0006$\u0018n\u001c8J]\u0012L7-\u0019;pe\u000e{G.\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005y\u0003CA\u000b1\u0013\t\tdC\u0001\u0003V]&$\u0018!\u0004<bY&$\u0017\r^5p]R{G.F\u00015!\t)d'D\u0001#\u0013\t9$EA\u0006E_V\u0014G.\u001a)be\u0006l\u0007f\u0001\u0002:\u007fA\u0011!(P\u0007\u0002w)\u0011A(D\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001 <\u0005\u0015\u0019\u0016N\\2fC\u0005\u0001\u0015!\u0002\u001a/i9\u0002\u0014\u0001E4fiZ\u000bG.\u001b3bi&|g\u000eV8m+\u0005\u0019\u0005CA\u000bE\u0013\t)eC\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0007ez\u0014\u0001C:uKB\u001c\u0016N_3\u0002-\u001d,Go\u00147e\u0005>|7\u000f^5oON#(/\u0019;fOf$2AS*d!\tY\u0015+D\u0001M\u0015\tie*A\u0007d_:4\u0017nZ;sCRLwN\u001c\u0006\u0003\u0015=S!\u0001U\u0007\u0002\u000b5dG.\u001b2\n\u0005Ic%\u0001\u0005\"p_N$\u0018N\\4TiJ\fG/Z4z\u0011\u0015!V\u00011\u0001V\u0003M\u0019\u0017\r^3h_JL7-\u00197GK\u0006$XO]3t!\u00111V\f\u00191\u000f\u0005][\u0006C\u0001-\u0017\u001b\u0005I&B\u0001..\u0003\u0019a$o\\8u}%\u0011ALF\u0001\u0007!J,G-\u001a4\n\u0005y{&aA'ba*\u0011AL\u0006\t\u0003+\u0005L!A\u0019\f\u0003\u0007%sG\u000fC\u0003e\u000b\u0001\u0007Q-A\u0004pY\u0012\fEnZ8\u0011\u0005\u0019LgBA&h\u0013\tAG*\u0001\u0003BY\u001e|\u0017B\u00016l\u0005\u0011\tEnZ8\u000b\u0005!d\u0015AD4fi>cG\rT8tgRK\b/Z\u000b\u0002]B\u0011qN]\u0007\u0002a*\u0011\u0011OT\u0001\u0005Y>\u001c8/\u0003\u0002ta\n!Aj\\:t\u0003Q\u0019X\u000f]3sI\u001d,Go\u00147e'R\u0014\u0018\r^3hsR)a/\u001f>}{B\u00111j^\u0005\u0003q2\u0013\u0001b\u0015;sCR,w-\u001f\u0005\u0006)\u001e\u0001\r!\u0016\u0005\u0006w\u001e\u0001\r\u0001Y\u0001\u000b]Vl7\t\\1tg\u0016\u001c\b\"\u00023\b\u0001\u0004)\u0007\"\u0002@\b\u0001\u0004y\u0018aC8mI&k\u0007/\u001e:jif\u0004B!!\u0001\u0002\b5\u0011\u00111\u0001\u0006\u0004\u0003\u000bq\u0015\u0001C5naV\u0014\u0018\u000e^=\n\t\u0005%\u00111\u0001\u0002\t\u00136\u0004XO]5us&\u0019\u0011Q\u0002\u000f\u0002\u001d\u001d,Go\u00147e'R\u0014\u0018\r^3hs\u0002"
)
public interface GBTParams extends TreeEnsembleParams, HasMaxIter, HasStepSize, HasValidationIndicatorCol {
   void org$apache$spark$ml$tree$GBTParams$_setter_$validationTol_$eq(final DoubleParam x$1);

   void org$apache$spark$ml$tree$GBTParams$_setter_$stepSize_$eq(final DoubleParam x$1);

   // $FF: synthetic method
   Strategy org$apache$spark$ml$tree$GBTParams$$super$getOldStrategy(final Map categoricalFeatures, final int numClasses, final Enumeration.Value oldAlgo, final Impurity oldImpurity);

   DoubleParam validationTol();

   // $FF: synthetic method
   static double getValidationTol$(final GBTParams $this) {
      return $this.getValidationTol();
   }

   default double getValidationTol() {
      return BoxesRunTime.unboxToDouble(this.$(this.validationTol()));
   }

   DoubleParam stepSize();

   // $FF: synthetic method
   static BoostingStrategy getOldBoostingStrategy$(final GBTParams $this, final Map categoricalFeatures, final Enumeration.Value oldAlgo) {
      return $this.getOldBoostingStrategy(categoricalFeatures, oldAlgo);
   }

   default BoostingStrategy getOldBoostingStrategy(final Map categoricalFeatures, final Enumeration.Value oldAlgo) {
      Strategy strategy = this.org$apache$spark$ml$tree$GBTParams$$super$getOldStrategy(categoricalFeatures, 2, oldAlgo, Variance$.MODULE$);
      return new BoostingStrategy(strategy, this.getOldLossType(), this.getMaxIter(), this.getStepSize(), this.getValidationTol());
   }

   Loss getOldLossType();

   static void $init$(final GBTParams $this) {
      $this.org$apache$spark$ml$tree$GBTParams$_setter_$validationTol_$eq(new DoubleParam($this, "validationTol", "Threshold for stopping early when fit with validation is used.If the error rate on the validation input changes by less than the validationTol,then learning will stop early (before `maxIter`).This parameter is ignored when fit without validation is used.", ParamValidators$.MODULE$.gtEq((double)0.0F)));
      $this.org$apache$spark$ml$tree$GBTParams$_setter_$stepSize_$eq(new DoubleParam($this, "stepSize", "Step size (a.k.a. learning rate) in interval (0, 1] for shrinking the contribution of each estimator.", ParamValidators$.MODULE$.inRange((double)0.0F, (double)1.0F, false, true)));
      $this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{$this.maxIter().$minus$greater(BoxesRunTime.boxToInteger(20)), $this.stepSize().$minus$greater(BoxesRunTime.boxToDouble(0.1)), $this.validationTol().$minus$greater(BoxesRunTime.boxToDouble(0.01)), $this.featureSubsetStrategy().$minus$greater("all")}));
   }
}
