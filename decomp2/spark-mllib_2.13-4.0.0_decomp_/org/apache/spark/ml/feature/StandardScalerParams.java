package org.apache.spark.ml.feature;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.ParamPair;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00193\u0001b\u0002\u0005\u0011\u0002\u0007\u0005\u0001B\u0005\u0005\u0006Q\u0001!\tA\u000b\u0005\b]\u0001\u0011\r\u0011\"\u00010\u0011\u0015\u0019\u0004\u0001\"\u00015\u0011\u001dA\u0004A1A\u0005\u0002=BQ!\u000f\u0001\u0005\u0002QBQA\u000f\u0001\u0005\u0012m\u0012Ac\u0015;b]\u0012\f'\u000fZ*dC2,'\u000fU1sC6\u001c(BA\u0005\u000b\u0003\u001d1W-\u0019;ve\u0016T!a\u0003\u0007\u0002\u00055d'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0014\u000b\u0001\u0019\u0012dH\u0013\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQR$D\u0001\u001c\u0015\ta\"\"A\u0003qCJ\fW.\u0003\u0002\u001f7\t1\u0001+\u0019:b[N\u0004\"\u0001I\u0012\u000e\u0003\u0005R!AI\u000e\u0002\rMD\u0017M]3e\u0013\t!\u0013EA\u0006ICNLe\u000e];u\u0007>d\u0007C\u0001\u0011'\u0013\t9\u0013E\u0001\u0007ICN|U\u000f\u001e9vi\u000e{G.\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005Y\u0003C\u0001\u000b-\u0013\tiSC\u0001\u0003V]&$\u0018\u0001C<ji\"lU-\u00198\u0016\u0003A\u0002\"AG\u0019\n\u0005IZ\"\u0001\u0004\"p_2,\u0017M\u001c)be\u0006l\u0017aC4fi^KG\u000f['fC:,\u0012!\u000e\t\u0003)YJ!aN\u000b\u0003\u000f\t{w\u000e\\3b]\u00069q/\u001b;i'R$\u0017AC4fi^KG\u000f[*uI\u0006Qb/\u00197jI\u0006$X-\u00118e)J\fgn\u001d4pe6\u001c6\r[3nCR\u0011A\b\u0012\t\u0003{\tk\u0011A\u0010\u0006\u0003\u007f\u0001\u000bQ\u0001^=qKNT!!\u0011\u0007\u0002\u0007M\fH.\u0003\u0002D}\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000b\u00153\u0001\u0019\u0001\u001f\u0002\rM\u001c\u0007.Z7b\u0001"
)
public interface StandardScalerParams extends HasInputCol, HasOutputCol {
   void org$apache$spark$ml$feature$StandardScalerParams$_setter_$withMean_$eq(final BooleanParam x$1);

   void org$apache$spark$ml$feature$StandardScalerParams$_setter_$withStd_$eq(final BooleanParam x$1);

   BooleanParam withMean();

   // $FF: synthetic method
   static boolean getWithMean$(final StandardScalerParams $this) {
      return $this.getWithMean();
   }

   default boolean getWithMean() {
      return BoxesRunTime.unboxToBoolean(this.$(this.withMean()));
   }

   BooleanParam withStd();

   // $FF: synthetic method
   static boolean getWithStd$(final StandardScalerParams $this) {
      return $this.getWithStd();
   }

   default boolean getWithStd() {
      return BoxesRunTime.unboxToBoolean(this.$(this.withStd()));
   }

   // $FF: synthetic method
   static StructType validateAndTransformSchema$(final StandardScalerParams $this, final StructType schema) {
      return $this.validateAndTransformSchema(schema);
   }

   default StructType validateAndTransformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      .MODULE$.require(!scala.collection.ArrayOps..MODULE$.contains$extension(.MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.outputCol())), () -> "Output column " + this.$(this.outputCol()) + " already exists.");
      StructField[] outputFields = (StructField[])scala.collection.ArrayOps..MODULE$.$colon$plus$extension(.MODULE$.refArrayOps((Object[])schema.fields()), new StructField((String)this.$(this.outputCol()), new VectorUDT(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
      return new StructType(outputFields);
   }

   static void $init$(final StandardScalerParams $this) {
      $this.org$apache$spark$ml$feature$StandardScalerParams$_setter_$withMean_$eq(new BooleanParam($this, "withMean", "Whether to center data with mean"));
      $this.org$apache$spark$ml$feature$StandardScalerParams$_setter_$withStd_$eq(new BooleanParam($this, "withStd", "Whether to scale the data to unit standard deviation"));
      $this.setDefault(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new ParamPair[]{$this.withMean().$minus$greater(BoxesRunTime.boxToBoolean(false)), $this.withStd().$minus$greater(BoxesRunTime.boxToBoolean(true))}));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
