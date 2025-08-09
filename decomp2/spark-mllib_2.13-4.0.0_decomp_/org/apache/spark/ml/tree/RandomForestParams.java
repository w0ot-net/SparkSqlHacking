package org.apache.spark.ml.tree;

import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\r3\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011\"\u0005\u0005\u00069\u0001!\tA\b\u0005\bE\u0001\u0011\r\u0011\"\u0002$\u0011\u0015Q\u0003\u0001\"\u0002,\u0011\u001dy\u0003A1A\u0005\u0006ABQ!\u0010\u0001\u0005\u0006y\u0012!CU1oI>lgi\u001c:fgR\u0004\u0016M]1ng*\u0011\u0001\"C\u0001\u0005iJ,WM\u0003\u0002\u000b\u0017\u0005\u0011Q\u000e\u001c\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\tI\"$D\u0001\b\u0013\tYrA\u0001\nUe\u0016,WI\\:f[\ndW\rU1sC6\u001c\u0018A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003}\u0001\"a\u0005\u0011\n\u0005\u0005\"\"\u0001B+oSR\f\u0001B\\;n)J,Wm]\u000b\u0002IA\u0011Q\u0005K\u0007\u0002M)\u0011q%C\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003S\u0019\u0012\u0001\"\u00138u!\u0006\u0014\u0018-\\\u0001\fO\u0016$h*^7Ue\u0016,7/F\u0001-!\t\u0019R&\u0003\u0002/)\t\u0019\u0011J\u001c;\u0002\u0013\t|w\u000e^:ue\u0006\u0004X#A\u0019\u0011\u0005\u0015\u0012\u0014BA\u001a'\u00051\u0011un\u001c7fC:\u0004\u0016M]1nQ\r!Qg\u000f\t\u0003mej\u0011a\u000e\u0006\u0003q-\t!\"\u00198o_R\fG/[8o\u0013\tQtGA\u0003TS:\u001cW-I\u0001=\u0003\u0015\u0019d\u0006\r\u00181\u000319W\r\u001e\"p_R\u001cHO]1q+\u0005y\u0004CA\nA\u0013\t\tECA\u0004C_>dW-\u00198)\u0007\u0015)4\b"
)
public interface RandomForestParams extends TreeEnsembleParams {
   void org$apache$spark$ml$tree$RandomForestParams$_setter_$numTrees_$eq(final IntParam x$1);

   void org$apache$spark$ml$tree$RandomForestParams$_setter_$bootstrap_$eq(final BooleanParam x$1);

   IntParam numTrees();

   // $FF: synthetic method
   static int getNumTrees$(final RandomForestParams $this) {
      return $this.getNumTrees();
   }

   default int getNumTrees() {
      return BoxesRunTime.unboxToInt(this.$(this.numTrees()));
   }

   BooleanParam bootstrap();

   // $FF: synthetic method
   static boolean getBootstrap$(final RandomForestParams $this) {
      return $this.getBootstrap();
   }

   default boolean getBootstrap() {
      return BoxesRunTime.unboxToBoolean(this.$(this.bootstrap()));
   }

   static void $init$(final RandomForestParams $this) {
      $this.org$apache$spark$ml$tree$RandomForestParams$_setter_$numTrees_$eq(new IntParam($this, "numTrees", "Number of trees to train (at least 1)", ParamValidators$.MODULE$.gtEq((double)1.0F)));
      $this.org$apache$spark$ml$tree$RandomForestParams$_setter_$bootstrap_$eq(new BooleanParam($this, "bootstrap", "Whether bootstrap samples are used when building trees."));
      $this.setDefault(.MODULE$.wrapRefArray(new ParamPair[]{$this.numTrees().$minus$greater(BoxesRunTime.boxToInteger(20)), $this.bootstrap().$minus$greater(BoxesRunTime.boxToBoolean(true))}));
   }
}
