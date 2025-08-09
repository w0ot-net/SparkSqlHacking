package org.apache.spark.ml.param.shared;

import org.apache.spark.ml.param.Params;
import org.apache.spark.ml.param.StringArrayParam;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]2q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0004#\u0001\t\u0007IQA\u0012\t\u000b\u001d\u0002AQ\u0001\u0015\u0003\u0019!\u000b7/\u00138qkR\u001cu\u000e\\:\u000b\u0005\u00199\u0011AB:iCJ,GM\u0003\u0002\t\u0013\u0005)\u0001/\u0019:b[*\u0011!bC\u0001\u0003[2T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\u0002\u0001'\r\u00011#\u0007\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0011\u0005iYR\"A\u0004\n\u0005q9!A\u0002)be\u0006l7/\u0001\u0004%S:LG\u000f\n\u000b\u0002?A\u0011A\u0003I\u0005\u0003CU\u0011A!\u00168ji\u0006I\u0011N\u001c9vi\u000e{Gn]\u000b\u0002IA\u0011!$J\u0005\u0003M\u001d\u0011\u0001c\u0015;sS:<\u0017I\u001d:bsB\u000b'/Y7\u0002\u0019\u001d,G/\u00138qkR\u001cu\u000e\\:\u0016\u0003%\u00022\u0001\u0006\u0016-\u0013\tYSCA\u0003BeJ\f\u0017\u0010\u0005\u0002.i9\u0011aF\r\t\u0003_Ui\u0011\u0001\r\u0006\u0003cE\ta\u0001\u0010:p_Rt\u0014BA\u001a\u0016\u0003\u0019\u0001&/\u001a3fM&\u0011QG\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005M*\u0002"
)
public interface HasInputCols extends Params {
   void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1);

   StringArrayParam inputCols();

   // $FF: synthetic method
   static String[] getInputCols$(final HasInputCols $this) {
      return $this.getInputCols();
   }

   default String[] getInputCols() {
      return (String[])this.$(this.inputCols());
   }

   static void $init$(final HasInputCols $this) {
      $this.org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(new StringArrayParam($this, "inputCols", "input column names"));
   }
}
