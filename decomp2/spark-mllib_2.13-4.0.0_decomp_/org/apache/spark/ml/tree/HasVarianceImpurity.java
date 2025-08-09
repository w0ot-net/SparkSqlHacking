package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.Params;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.impurity.Variance$;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054\u0001b\u0003\u0007\u0011\u0002\u0007\u0005aB\u0006\u0005\u0006G\u0001!\t!\n\u0005\bS\u0001\u0011\r\u0011\"\u0002+\u0011\u0015I\u0004\u0001\"\u0002;\u0011\u0019Y\u0004\u0001\"\u0001\u000fy\u001d1Q\t\u0004E\u0001\u001d\u00193aa\u0003\u0007\t\u00029A\u0005\"B)\u0007\t\u0003\u0011\u0006bB*\u0007\u0005\u0004%)\u0001\u0016\u0005\u00071\u001a\u0001\u000bQB+\t\u000fe3\u0011\u0011!C\u00055\n\u0019\u0002*Y:WCJL\u0017M\\2f\u00136\u0004XO]5us*\u0011QBD\u0001\u0005iJ,WM\u0003\u0002\u0010!\u0005\u0011Q\u000e\u001c\u0006\u0003#I\tQa\u001d9be.T!a\u0005\u000b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0012aA8sON\u0019\u0001aF\u000f\u0011\u0005aYR\"A\r\u000b\u0003i\tQa]2bY\u0006L!\u0001H\r\u0003\r\u0005s\u0017PU3g!\tq\u0012%D\u0001 \u0015\t\u0001c\"A\u0003qCJ\fW.\u0003\u0002#?\t1\u0001+\u0019:b[N\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002MA\u0011\u0001dJ\u0005\u0003Qe\u0011A!\u00168ji\u0006A\u0011.\u001c9ve&$\u00180F\u0001,!\rqBFL\u0005\u0003[}\u0011Q\u0001U1sC6\u0004\"a\f\u001c\u000f\u0005A\"\u0004CA\u0019\u001a\u001b\u0005\u0011$BA\u001a%\u0003\u0019a$o\\8u}%\u0011Q'G\u0001\u0007!J,G-\u001a4\n\u0005]B$AB*ue&twM\u0003\u000263\u0005Yq-\u001a;J[B,(/\u001b;z+\u0005q\u0013AD4fi>cG-S7qkJLG/_\u000b\u0002{A\u0011ahQ\u0007\u0002\u007f)\u0011\u0011\u0006\u0011\u0006\u0003\u001b\u0005S!A\u0011\t\u0002\u000b5dG.\u001b2\n\u0005\u0011{$\u0001C%naV\u0014\u0018\u000e^=\u0002'!\u000b7OV1sS\u0006t7-Z%naV\u0014\u0018\u000e^=\u0011\u0005\u001d3Q\"\u0001\u0007\u0014\u0007\u00199\u0012\n\u0005\u0002K\u001f6\t1J\u0003\u0002M\u001b\u0006\u0011\u0011n\u001c\u0006\u0002\u001d\u0006!!.\u0019<b\u0013\t\u00016J\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0004=S:LGO\u0010\u000b\u0002\r\u0006\u00192/\u001e9q_J$X\rZ%naV\u0014\u0018\u000e^5fgV\tQ\u000bE\u0002\u0019-:J!aV\r\u0003\u000b\u0005\u0013(/Y=\u0002)M,\b\u000f]8si\u0016$\u0017*\u001c9ve&$\u0018.Z:!\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005Y\u0006C\u0001/`\u001b\u0005i&B\u00010N\u0003\u0011a\u0017M\\4\n\u0005\u0001l&AB(cU\u0016\u001cG\u000f"
)
public interface HasVarianceImpurity extends Params {
   static String[] supportedImpurities() {
      return HasVarianceImpurity$.MODULE$.supportedImpurities();
   }

   void org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(final Param x$1);

   Param impurity();

   // $FF: synthetic method
   static String getImpurity$(final HasVarianceImpurity $this) {
      return $this.getImpurity();
   }

   default String getImpurity() {
      return ((String)this.$(this.impurity())).toLowerCase(Locale.ROOT);
   }

   // $FF: synthetic method
   static Impurity getOldImpurity$(final HasVarianceImpurity $this) {
      return $this.getOldImpurity();
   }

   default Impurity getOldImpurity() {
      String var2 = this.getImpurity();
      switch (var2 == null ? 0 : var2.hashCode()) {
         case -1249575311:
            if ("variance".equals(var2)) {
               return Variance$.MODULE$;
            }
         default:
            throw new RuntimeException("TreeRegressorParams was given unrecognized impurity: " + this.impurity());
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$impurity$2(final String value) {
      return .MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])HasVarianceImpurity$.MODULE$.supportedImpurities()), value.toLowerCase(Locale.ROOT));
   }

   static void $init$(final HasVarianceImpurity $this) {
      $this.org$apache$spark$ml$tree$HasVarianceImpurity$_setter_$impurity_$eq(new Param($this, "impurity", "Criterion used for information gain calculation (case-insensitive). Supported options: " + scala.Predef..MODULE$.wrapRefArray((Object[])HasVarianceImpurity$.MODULE$.supportedImpurities()).mkString(", "), (value) -> BoxesRunTime.boxToBoolean($anonfun$impurity$2(value)), scala.reflect.ClassTag..MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.impurity().$minus$greater("variance")}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
