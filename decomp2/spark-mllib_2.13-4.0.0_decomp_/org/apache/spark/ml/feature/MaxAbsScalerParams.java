package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0004\u0005\u0006I\u0001!\tA\n\u0005\u0006U\u0001!\tb\u000b\u0002\u0013\u001b\u0006D\u0018IY:TG\u0006dWM\u001d)be\u0006l7O\u0003\u0002\u0006\r\u00059a-Z1ukJ,'BA\u0004\t\u0003\tiGN\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\u0015\u0001q\"F\u000e\"!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0011a#G\u0007\u0002/)\u0011\u0001DB\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u00035]\u0011a\u0001U1sC6\u001c\bC\u0001\u000f \u001b\u0005i\"B\u0001\u0010\u0018\u0003\u0019\u0019\b.\u0019:fI&\u0011\u0001%\b\u0002\f\u0011\u0006\u001c\u0018J\u001c9vi\u000e{G\u000e\u0005\u0002\u001dE%\u00111%\b\u0002\r\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tq\u0005\u0005\u0002\u0011Q%\u0011\u0011&\u0005\u0002\u0005+:LG/\u0001\u000ewC2LG-\u0019;f\u0003:$GK]1og\u001a|'/\\*dQ\u0016l\u0017\r\u0006\u0002-iA\u0011QFM\u0007\u0002])\u0011q\u0006M\u0001\u0006if\u0004Xm\u001d\u0006\u0003c!\t1a]9m\u0013\t\u0019dF\u0001\u0006TiJ,8\r\u001e+za\u0016DQ!\u000e\u0002A\u00021\naa]2iK6\f\u0007"
)
public interface MaxAbsScalerParams extends HasInputCol, HasOutputCol {
   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final MaxAbsScalerParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      .MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.outputCol())), () -> "Output column " + this.$(this.outputCol()) + " already exists.");
      StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), new StructField((String)this.$(this.outputCol()), new VectorUDT(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
      return new StructType(outputFields);
   }

   static void $init$(final MaxAbsScalerParams $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
