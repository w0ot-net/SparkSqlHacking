package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.ParamValidators$;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\t3\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005a\u0001\u0005\u0005\u0006M\u0001!\t\u0001\u000b\u0005\bY\u0001\u0011\r\u0011\"\u0002.\u0011\u0015\t\u0004\u0001\"\u00013\u0011\u00151\u0004\u0001\"\u00058\u0005%\u00016)\u0011)be\u0006l7O\u0003\u0002\b\u0011\u00059a-Z1ukJ,'BA\u0005\u000b\u0003\tiGN\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h'\u0015\u0001\u0011cF\u000f$!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fMB\u0011\u0001dG\u0007\u00023)\u0011!\u0004C\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u00039e\u0011a\u0001U1sC6\u001c\bC\u0001\u0010\"\u001b\u0005y\"B\u0001\u0011\u001a\u0003\u0019\u0019\b.\u0019:fI&\u0011!e\b\u0002\f\u0011\u0006\u001c\u0018J\u001c9vi\u000e{G\u000e\u0005\u0002\u001fI%\u0011Qe\b\u0002\r\u0011\u0006\u001cx*\u001e;qkR\u001cu\u000e\\\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\t\u0011\u0006\u0005\u0002\u0013U%\u00111f\u0005\u0002\u0005+:LG/A\u0001l+\u0005q\u0003C\u0001\r0\u0013\t\u0001\u0014D\u0001\u0005J]R\u0004\u0016M]1n\u0003\u00119W\r^&\u0016\u0003M\u0002\"A\u0005\u001b\n\u0005U\u001a\"aA%oi\u0006Qb/\u00197jI\u0006$X-\u00118e)J\fgn\u001d4pe6\u001c6\r[3nCR\u0011\u0001\b\u0011\t\u0003syj\u0011A\u000f\u0006\u0003wq\nQ\u0001^=qKNT!!\u0010\u0006\u0002\u0007M\fH.\u0003\u0002@u\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000b\u0005#\u0001\u0019\u0001\u001d\u0002\rM\u001c\u0007.Z7b\u0001"
)
public interface PCAParams extends HasInputCol, HasOutputCol {
   void org$apache$spark$ml$feature$PCAParams$_setter_$k_$eq(final IntParam x$1);

   IntParam k();

   // $FF: synthetic method
   static int getK$(final PCAParams $this) {
      return $this.getK();
   }

   default int getK() {
      return BoxesRunTime.unboxToInt(this.$(this.k()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final PCAParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      .MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.outputCol())), () -> "Output column " + this.$(this.outputCol()) + " already exists.");
      return SchemaUtils$.MODULE$.updateAttributeGroupSize(schema, (String)this.$(this.outputCol()), BoxesRunTime.unboxToInt(this.$(this.k())));
   }

   static void $init$(final PCAParams $this) {
      $this.org$apache$spark$ml$feature$PCAParams$_setter_$k_$eq(new IntParam($this, "k", "the number of principal components (> 0)", ParamValidators$.MODULE$.gt((double)0.0F)));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
