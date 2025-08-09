package org.apache.spark.ml.tuning;

import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.ParamValidators$;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011\"\u0005\u0005\u00069\u0001!\tA\b\u0005\bE\u0001\u0011\r\u0011\"\u0001$\u0011\u0015Q\u0003\u0001\"\u0001,\u0011\u001dy\u0003A1A\u0005\u0002ABQa\u0010\u0001\u0005\u0002\u0001\u0013Ac\u0011:pgN4\u0016\r\\5eCR|'\u000fU1sC6\u001c(B\u0001\u0005\n\u0003\u0019!XO\\5oO*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\r\u0005\u0002\u001a55\tq!\u0003\u0002\u001c\u000f\tya+\u00197jI\u0006$xN\u001d)be\u0006l7/\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005y\u0002CA\n!\u0013\t\tCC\u0001\u0003V]&$\u0018\u0001\u00038v[\u001a{G\u000eZ:\u0016\u0003\u0011\u0002\"!\n\u0015\u000e\u0003\u0019R!aJ\u0005\u0002\u000bA\f'/Y7\n\u0005%2#\u0001C%oiB\u000b'/Y7\u0002\u0017\u001d,GOT;n\r>dGm]\u000b\u0002YA\u00111#L\u0005\u0003]Q\u00111!\u00138u\u0003\u001d1w\u000e\u001c3D_2,\u0012!\r\t\u0004KI\"\u0014BA\u001a'\u0005\u0015\u0001\u0016M]1n!\t)DH\u0004\u00027uA\u0011q\u0007F\u0007\u0002q)\u0011\u0011(H\u0001\u0007yI|w\u000e\u001e \n\u0005m\"\u0012A\u0002)sK\u0012,g-\u0003\u0002>}\t11\u000b\u001e:j]\u001eT!a\u000f\u000b\u0002\u0015\u001d,GOR8mI\u000e{G.F\u00015\u0001"
)
public interface CrossValidatorParams extends ValidatorParams {
   void org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$numFolds_$eq(final IntParam x$1);

   void org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$foldCol_$eq(final Param x$1);

   IntParam numFolds();

   // $FF: synthetic method
   static int getNumFolds$(final CrossValidatorParams $this) {
      return $this.getNumFolds();
   }

   default int getNumFolds() {
      return BoxesRunTime.unboxToInt(this.$(this.numFolds()));
   }

   Param foldCol();

   // $FF: synthetic method
   static String getFoldCol$(final CrossValidatorParams $this) {
      return $this.getFoldCol();
   }

   default String getFoldCol() {
      return (String)this.$(this.foldCol());
   }

   static void $init$(final CrossValidatorParams $this) {
      $this.org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$numFolds_$eq(new IntParam($this, "numFolds", "number of folds for cross validation (>= 2)", ParamValidators$.MODULE$.gtEq((double)2.0F)));
      $this.org$apache$spark$ml$tuning$CrossValidatorParams$_setter_$foldCol_$eq(new Param($this, "foldCol", "the column name of user specified fold number", .MODULE$.apply(String.class)));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.foldCol().$minus$greater(""), $this.numFolds().$minus$greater(BoxesRunTime.boxToInteger(3))}));
   }
}
