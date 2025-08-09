package org.apache.spark.ml;

import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.expressions.UserDefinedFunction;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Function1;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.reflect.api.TypeTags;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%d!\u0002\u0007\u000e\u0003\u00031\u0002\u0002C\u0017\u0001\u0005\u0007\u0005\u000b1\u0002\u0018\t\u0011]\u0003!1!Q\u0001\faCQ\u0001\u0018\u0001\u0005\u0002uCQA\u001a\u0001\u0005\u0002\u001dDQA\u001d\u0001\u0005\u0002MDQ!\u001e\u0001\u0007\u0012YDQA\u001f\u0001\u0007\u0012mDq!!\u0003\u0001\t#\tY\u0001C\u0004\u0002\u0018\u0001!\t%!\u0007\t\u000f\u0005\u0015\u0002\u0001\"\u0011\u0002(!9\u0011\u0011\f\u0001\u0005B\u0005m#\u0001E+oCJLHK]1og\u001a|'/\\3s\u0015\tqq\"\u0001\u0002nY*\u0011\u0001#E\u0001\u0006gB\f'o\u001b\u0006\u0003%M\ta!\u00199bG\",'\"\u0001\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0016\t]i%,Y\n\u0006\u0001aaBe\n\t\u00033ii\u0011!D\u0005\u000375\u00111\u0002\u0016:b]N4wN]7feB\u0011QDI\u0007\u0002=)\u0011q\u0004I\u0001\u0007g\"\f'/\u001a3\u000b\u0005\u0005j\u0011!\u00029be\u0006l\u0017BA\u0012\u001f\u0005-A\u0015m]%oaV$8i\u001c7\u0011\u0005u)\u0013B\u0001\u0014\u001f\u00051A\u0015m](viB,HoQ8m!\tA3&D\u0001*\u0015\tQs\"\u0001\u0005j]R,'O\\1m\u0013\ta\u0013FA\u0004M_\u001e<\u0017N\\4\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u00020\u000b.s!\u0001\r\"\u000f\u0005EzdB\u0001\u001a=\u001d\t\u0019\u0014H\u0004\u00025o5\tQG\u0003\u00027+\u00051AH]8pizJ\u0011\u0001O\u0001\u0006g\u000e\fG.Y\u0005\u0003um\nqA]3gY\u0016\u001cGOC\u00019\u0013\tid(A\u0004sk:$\u0018.\\3\u000b\u0005iZ\u0014B\u0001!B\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u0010 \n\u0005\r#\u0015\u0001C;oSZ,'o]3\u000b\u0005\u0001\u000b\u0015B\u0001$H\u0005\u001d!\u0016\u0010]3UC\u001eL!\u0001S%\u0003\u0011QK\b/\u001a+bONT!A\u0013 \u0002\u0007\u0005\u0004\u0018\u000e\u0005\u0002M\u001b2\u0001A!\u0002(\u0001\u0005\u0004y%AA%O#\t\u0001F\u000b\u0005\u0002R%6\t1(\u0003\u0002Tw\t9aj\u001c;iS:<\u0007CA)V\u0013\t16HA\u0002B]f\f!\"\u001a<jI\u0016t7-\u001a\u00133!\ryS)\u0017\t\u0003\u0019j#Qa\u0017\u0001C\u0002=\u00131aT+U\u0003\u0019a\u0014N\\5u}Q\ta\fF\u0002`I\u0016\u0004R!\u0007\u0001L3\u0002\u0004\"\u0001T1\u0005\u000b\t\u0004!\u0019A2\u0003\u0003Q\u000b\"\u0001U0\t\u000b5\u001a\u00019\u0001\u0018\t\u000b]\u001b\u00019\u0001-\u0002\u0017M,G/\u00138qkR\u001cu\u000e\u001c\u000b\u0003A\"DQ!\u001b\u0003A\u0002)\fQA^1mk\u0016\u0004\"a[8\u000f\u00051l\u0007C\u0001\u001b<\u0013\tq7(\u0001\u0004Qe\u0016$WMZ\u0005\u0003aF\u0014aa\u0015;sS:<'B\u00018<\u00031\u0019X\r^(viB,HoQ8m)\t\u0001G\u000fC\u0003j\u000b\u0001\u0007!.A\nde\u0016\fG/\u001a+sC:\u001chm\u001c:n\rVt7-F\u0001x!\u0011\t\u0006pS-\n\u0005e\\$!\u0003$v]\u000e$\u0018n\u001c82\u00039yW\u000f\u001e9vi\u0012\u000bG/\u0019+za\u0016,\u0012\u0001 \t\u0004{\u0006\u0015Q\"\u0001@\u000b\u0007}\f\t!A\u0003usB,7OC\u0002\u0002\u0004=\t1a]9m\u0013\r\t9A \u0002\t\t\u0006$\u0018\rV=qK\u0006\tb/\u00197jI\u0006$X-\u00138qkR$\u0016\u0010]3\u0015\t\u00055\u00111\u0003\t\u0004#\u0006=\u0011bAA\tw\t!QK\\5u\u0011\u0019\t)\u0002\u0003a\u0001y\u0006I\u0011N\u001c9viRK\b/Z\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u00111DA\u0011!\ri\u0018QD\u0005\u0004\u0003?q(AC*ueV\u001cG\u000fV=qK\"9\u00111E\u0005A\u0002\u0005m\u0011AB:dQ\u0016l\u0017-A\u0005ue\u0006t7OZ8s[R!\u0011\u0011FA#!\u0011\tY#a\u0010\u000f\t\u00055\u0012Q\b\b\u0005\u0003_\tYD\u0004\u0003\u00022\u0005eb\u0002BA\u001a\u0003oq1\u0001NA\u001b\u0013\u0005!\u0012B\u0001\n\u0014\u0013\t\u0001\u0012#C\u0002\u0002\u0004=I1\u0001QA\u0001\u0013\u0011\t\t%a\u0011\u0003\u0013\u0011\u000bG/\u0019$sC6,'b\u0001!\u0002\u0002!9\u0011q\t\u0006A\u0002\u0005%\u0013a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003\u0017\n)\u0006\u0005\u0004\u0002N\u0005=\u00131K\u0007\u0003\u0003\u0003IA!!\u0015\u0002\u0002\t9A)\u0019;bg\u0016$\bc\u0001'\u0002V\u0011Y\u0011qKA#\u0003\u0003\u0005\tQ!\u0001P\u0005\ryFEN\u0001\u0005G>\u0004\u0018\u0010F\u0002a\u0003;Bq!a\u0018\f\u0001\u0004\t\t'A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002d\u0005\u0015T\"\u0001\u0011\n\u0007\u0005\u001d\u0004E\u0001\u0005QCJ\fW.T1q\u0001"
)
public abstract class UnaryTransformer extends Transformer implements HasInputCol, HasOutputCol {
   private final TypeTags.TypeTag evidence$1;
   private final TypeTags.TypeTag evidence$2;
   private Param outputCol;
   private Param inputCol;

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public UnaryTransformer setInputCol(final String value) {
      return (UnaryTransformer)this.set(this.inputCol(), value);
   }

   public UnaryTransformer setOutputCol(final String value) {
      return (UnaryTransformer)this.set(this.outputCol(), value);
   }

   public abstract Function1 createTransformFunc();

   public abstract DataType outputDataType();

   public void validateInputType(final DataType inputType) {
   }

   public StructType transformSchema(final StructType schema) {
      DataType inputType = SchemaUtils$.MODULE$.getSchemaFieldType(schema, (String)this.$(this.inputCol()));
      this.validateInputType(inputType);
      if (.MODULE$.contains$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fieldNames()), this.$(this.outputCol()))) {
         throw new IllegalArgumentException("Output column " + this.$(this.outputCol()) + " already exists.");
      } else {
         StructField[] outputFields = (StructField[]).MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps((Object[])schema.fields()), new StructField((String)this.$(this.outputCol()), this.outputDataType(), false, org.apache.spark.sql.types.StructField..MODULE$.apply$default$4()), scala.reflect.ClassTag..MODULE$.apply(StructField.class));
         return new StructType(outputFields);
      }
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      UserDefinedFunction transformUDF = org.apache.spark.sql.functions..MODULE$.udf(this.createTransformFunc(), this.evidence$2, this.evidence$1);
      return dataset.withColumn((String)this.$(this.outputCol()), transformUDF.apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{dataset.apply((String)this.$(this.inputCol()))}))), outputSchema.apply((String)this.$(this.outputCol())).metadata());
   }

   public UnaryTransformer copy(final ParamMap extra) {
      return (UnaryTransformer)this.defaultCopy(extra);
   }

   public UnaryTransformer(final TypeTags.TypeTag evidence$1, final TypeTags.TypeTag evidence$2) {
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      Statics.releaseFence();
   }
}
