package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.attribute.AttributeGroup;
import org.apache.spark.ml.attribute.AttributeGroup$;
import org.apache.spark.ml.param.BooleanParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rf\u0001B\r\u001b\u0001\u0015B\u0001b\u000e\u0001\u0003\u0006\u0004%\t\u0005\u000f\u0005\t\u001f\u0002\u0011\t\u0011)A\u0005s!)\u0011\u000b\u0001C\u0001%\")\u0011\u000b\u0001C\u0001/\")\u0011\f\u0001C\u00015\")q\f\u0001C\u0001A\")1\r\u0001C\u0001I\")A\u000e\u0001C\u0001[\")\u0001\u000f\u0001C\u0001c\")q\u000f\u0001C\u0001q\")1\u0010\u0001C!y\"9\u0011\u0011\u0003\u0001\u0005B\u0005M\u0001bBA\u001e\u0001\u0011\u0005\u0013QH\u0004\b\u0003'R\u0002\u0012AA+\r\u0019I\"\u0004#\u0001\u0002X!1\u0011k\u0004C\u0001\u0003kB\u0011\"a\u001e\u0010\u0005\u0004%\tA\u0007\u001d\t\u000f\u0005et\u0002)A\u0005s!I\u00111P\bC\u0002\u0013\u0005!\u0004\u000f\u0005\b\u0003{z\u0001\u0015!\u0003:\u0011)\tyh\u0004b\u0001\n\u0003Q\u0012\u0011\u0011\u0005\b\u0003\u0007{\u0001\u0015!\u0003h\u0011\u001d\t)i\u0004C!\u0003\u000fC\u0011\"a$\u0010\u0003\u0003%I!!%\u0003\u001b=sW\rS8u\u000b:\u001cw\u000eZ3s\u0015\tYB$A\u0004gK\u0006$XO]3\u000b\u0005uq\u0012AA7m\u0015\ty\u0002%A\u0003ta\u0006\u00148N\u0003\u0002\"E\u00051\u0011\r]1dQ\u0016T\u0011aI\u0001\u0004_J<7\u0001A\n\u0005\u0001\u0019r\u0013\u0007E\u0002(Q)j\u0011\u0001H\u0005\u0003Sq\u0011\u0011\"R:uS6\fGo\u001c:\u0011\u0005-bS\"\u0001\u000e\n\u00055R\"AE(oK\"{G/\u00128d_\u0012,'/T8eK2\u0004\"aK\u0018\n\u0005AR\"!E(oK\"{G/\u00128d_\u0012,'OQ1tKB\u0011!'N\u0007\u0002g)\u0011A\u0007H\u0001\u0005kRLG.\u0003\u00027g\t)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\t\u0011\b\u0005\u0002;\u0007:\u00111(\u0011\t\u0003y}j\u0011!\u0010\u0006\u0003}\u0011\na\u0001\u0010:p_Rt$\"\u0001!\u0002\u000bM\u001c\u0017\r\\1\n\u0005\t{\u0014A\u0002)sK\u0012,g-\u0003\u0002E\u000b\n11\u000b\u001e:j]\u001eT!AQ )\u0007\u00059U\n\u0005\u0002I\u00176\t\u0011J\u0003\u0002K=\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u00051K%!B*j]\u000e,\u0017%\u0001(\u0002\u000bMr\u0003G\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005\u001dk\u0015A\u0002\u001fj]&$h\b\u0006\u0002T)B\u00111\u0006\u0001\u0005\u0006o\r\u0001\r!\u000f\u0015\u0004)\u001ek\u0005fA\u0002H\u001bR\t1\u000bK\u0002\u0005\u000f6\u000b1b]3u\u0013:\u0004X\u000f^\"pYR\u00111\fX\u0007\u0002\u0001!)Q,\u0002a\u0001s\u0005)a/\u00197vK\"\u001aQaR'\u0002\u0019M,GoT;uaV$8i\u001c7\u0015\u0005m\u000b\u0007\"B/\u0007\u0001\u0004I\u0004f\u0001\u0004H\u001b\u0006a1/\u001a;J]B,HoQ8mgR\u00111,\u001a\u0005\u0006M\u001e\u0001\raZ\u0001\u0007m\u0006dW/Z:\u0011\u0007!L\u0017(D\u0001@\u0013\tQwHA\u0003BeJ\f\u0017\u0010K\u0002\b\u000f6\u000bQb]3u\u001fV$\b/\u001e;D_2\u001cHCA.o\u0011\u00151\u0007\u00021\u0001hQ\rAq)T\u0001\fg\u0016$HI]8q\u0019\u0006\u001cH\u000f\u0006\u0002\\e\")Q,\u0003a\u0001gB\u0011\u0001\u000e^\u0005\u0003k~\u0012qAQ8pY\u0016\fg\u000eK\u0002\n\u000f6\u000b\u0001c]3u\u0011\u0006tG\r\\3J]Z\fG.\u001b3\u0015\u0005mK\b\"B/\u000b\u0001\u0004I\u0004f\u0001\u0006H\u001b\u0006yAO]1og\u001a|'/\\*dQ\u0016l\u0017\rF\u0002~\u0003\u0017\u00012A`A\u0004\u001b\u0005y(\u0002BA\u0001\u0003\u0007\tQ\u0001^=qKNT1!!\u0002\u001f\u0003\r\u0019\u0018\u000f\\\u0005\u0004\u0003\u0013y(AC*ueV\u001cG\u000fV=qK\"1\u0011QB\u0006A\u0002u\faa]2iK6\f\u0007fA\u0006H\u001b\u0006\u0019a-\u001b;\u0015\u0007)\n)\u0002C\u0004\u0002\u00181\u0001\r!!\u0007\u0002\u000f\u0011\fG/Y:fiB\"\u00111DA\u0014!\u0019\ti\"a\b\u0002$5\u0011\u00111A\u0005\u0005\u0003C\t\u0019AA\u0004ECR\f7/\u001a;\u0011\t\u0005\u0015\u0012q\u0005\u0007\u0001\t1\tI#!\u0006\u0002\u0002\u0003\u0005)\u0011AA\u0016\u0005\ryF%M\t\u0005\u0003[\t\u0019\u0004E\u0002i\u0003_I1!!\r@\u0005\u001dqu\u000e\u001e5j]\u001e\u00042\u0001[A\u001b\u0013\r\t9d\u0010\u0002\u0004\u0003:L\bf\u0001\u0007H\u001b\u0006!1m\u001c9z)\r\u0019\u0016q\b\u0005\b\u0003\u0003j\u0001\u0019AA\"\u0003\u0015)\u0007\u0010\u001e:b!\u0011\t)%a\u0013\u000e\u0005\u0005\u001d#bAA%9\u0005)\u0001/\u0019:b[&!\u0011QJA$\u0005!\u0001\u0016M]1n\u001b\u0006\u0004\bfA\u0007H\u001b\"\u001a\u0001aR'\u0002\u001b=sW\rS8u\u000b:\u001cw\u000eZ3s!\tYsbE\u0004\u0010\u00033\ny&!\u001a\u0011\u0007!\fY&C\u0002\u0002^}\u0012a!\u00118z%\u00164\u0007\u0003\u0002\u001a\u0002bMK1!a\u00194\u0005U!UMZ1vYR\u0004\u0016M]1ngJ+\u0017\rZ1cY\u0016\u0004B!a\u001a\u0002r5\u0011\u0011\u0011\u000e\u0006\u0005\u0003W\ni'\u0001\u0002j_*\u0011\u0011qN\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002t\u0005%$\u0001D*fe&\fG.\u001b>bE2,GCAA+\u00031YU)\u0012)`\u0013:3\u0016\tT%E\u00035YU)\u0012)`\u0013:3\u0016\tT%EA\u0005iQI\u0015*P%~KeJV!M\u0013\u0012\u000ba\"\u0012*S\u001fJ{\u0016J\u0014,B\u0019&#\u0005%A\ftkB\u0004xN\u001d;fI\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5egV\tq-\u0001\rtkB\u0004xN\u001d;fI\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5eg\u0002\nA\u0001\\8bIR\u00191+!#\t\r\u0005-u\u00031\u0001:\u0003\u0011\u0001\u0018\r\u001e5)\u0007]9U*\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u0002\u0014B!\u0011QSAN\u001b\t\t9J\u0003\u0003\u0002\u001a\u00065\u0014\u0001\u00027b]\u001eLA!!(\u0002\u0018\n1qJ\u00196fGRD3aD$NQ\rqq)\u0014"
)
public class OneHotEncoder extends Estimator implements OneHotEncoderBase, DefaultParamsWritable {
   private final String uid;
   private Param handleInvalid;
   private BooleanParam dropLast;
   private StringArrayParam outputCols;
   private Param outputCol;
   private StringArrayParam inputCols;
   private Param inputCol;

   public static OneHotEncoder load(final String path) {
      return OneHotEncoder$.MODULE$.load(path);
   }

   public static MLReader read() {
      return OneHotEncoder$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public boolean getDropLast() {
      return OneHotEncoderBase.getDropLast$(this);
   }

   public Tuple2 getInOutCols() {
      return OneHotEncoderBase.getInOutCols$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean dropLast, final boolean keepInvalid) {
      return OneHotEncoderBase.validateAndTransformSchema$(this, schema, dropLast, keepInvalid);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public final BooleanParam dropLast() {
      return this.dropLast;
   }

   public void org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public final void org$apache$spark$ml$feature$OneHotEncoderBase$_setter_$dropLast_$eq(final BooleanParam x$1) {
      this.dropLast = x$1;
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
   }

   public final Param inputCol() {
      return this.inputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCol$_setter_$inputCol_$eq(final Param x$1) {
      this.inputCol = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public String uid() {
      return this.uid;
   }

   public OneHotEncoder setInputCol(final String value) {
      return (OneHotEncoder)this.set(this.inputCol(), value);
   }

   public OneHotEncoder setOutputCol(final String value) {
      return (OneHotEncoder)this.set(this.outputCol(), value);
   }

   public OneHotEncoder setInputCols(final String[] values) {
      return (OneHotEncoder)this.set(this.inputCols(), values);
   }

   public OneHotEncoder setOutputCols(final String[] values) {
      return (OneHotEncoder)this.set(this.outputCols(), values);
   }

   public OneHotEncoder setDropLast(final boolean value) {
      return (OneHotEncoder)this.set(this.dropLast(), BoxesRunTime.boxToBoolean(value));
   }

   public OneHotEncoder setHandleInvalid(final String value) {
      return (OneHotEncoder)this.set(this.handleInvalid(), value);
   }

   public StructType transformSchema(final StructType schema) {
      boolean var4;
      label17: {
         label16: {
            Object var10000 = this.$(this.handleInvalid());
            String var3 = OneHotEncoder$.MODULE$.KEEP_INVALID();
            if (var10000 == null) {
               if (var3 == null) {
                  break label16;
               }
            } else if (var10000.equals(var3)) {
               break label16;
            }

            var4 = false;
            break label17;
         }

         var4 = true;
      }

      boolean keepInvalid = var4;
      return this.validateAndTransformSchema(schema, BoxesRunTime.unboxToBoolean(this.$(this.dropLast())), keepInvalid);
   }

   public OneHotEncoderModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema());
      Tuple2 var4 = this.getInOutCols();
      if (var4 != null) {
         String[] inputColumns = (String[])var4._1();
         String[] outputColumns = (String[])var4._2();
         Tuple2 var3 = new Tuple2(inputColumns, outputColumns);
         String[] inputColumns = (String[])var3._1();
         String[] outputColumns = (String[])var3._2();
         StructType transformedSchema = this.validateAndTransformSchema(dataset.schema(), false, false);
         int[] categorySizes = new int[outputColumns.length];
         int[] columnToScanIndices = (int[]).MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])outputColumns))), (x0$1) -> {
            if (x0$1 != null) {
               String outputColName = (String)x0$1._1();
               int idx = x0$1._2$mcI$sp();
               int numOfAttrs = AttributeGroup$.MODULE$.fromStructField(transformedSchema.apply(outputColName)).size();
               if (numOfAttrs < 0) {
                  return new Some(BoxesRunTime.boxToInteger(idx));
               } else {
                  categorySizes[idx] = numOfAttrs;
                  return scala.None..MODULE$;
               }
            } else {
               throw new MatchError(x0$1);
            }
         }, scala.reflect.ClassTag..MODULE$.Int());
         if (columnToScanIndices.length > 0) {
            String[] inputColNames = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(columnToScanIndices), (x$4) -> $anonfun$fit$2(inputColumns, BoxesRunTime.unboxToInt(x$4)), scala.reflect.ClassTag..MODULE$.apply(String.class));
            String[] outputColNames = (String[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps(columnToScanIndices), (x$5) -> $anonfun$fit$3(outputColumns, BoxesRunTime.unboxToInt(x$5)), scala.reflect.ClassTag..MODULE$.apply(String.class));
            Seq attrGroups = OneHotEncoderCommon$.MODULE$.getOutputAttrGroupFromData(dataset, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(inputColNames).toImmutableArraySeq(), org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(outputColNames).toImmutableArraySeq(), false);
            ((IterableOnceOps)attrGroups.zip(scala.Predef..MODULE$.wrapIntArray(columnToScanIndices))).foreach((x0$2) -> {
               $anonfun$fit$4(categorySizes, x0$2);
               return BoxedUnit.UNIT;
            });
         }

         OneHotEncoderModel model = (OneHotEncoderModel)(new OneHotEncoderModel(this.uid(), categorySizes)).setParent(this);
         return (OneHotEncoderModel)this.copyValues(model, this.copyValues$default$2());
      } else {
         throw new MatchError(var4);
      }
   }

   public OneHotEncoder copy(final ParamMap extra) {
      return (OneHotEncoder)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final String $anonfun$fit$2(final String[] inputColumns$1, final int x$4) {
      return inputColumns$1[x$4];
   }

   // $FF: synthetic method
   public static final String $anonfun$fit$3(final String[] outputColumns$1, final int x$5) {
      return outputColumns$1[x$5];
   }

   // $FF: synthetic method
   public static final void $anonfun$fit$4(final int[] categorySizes$1, final Tuple2 x0$2) {
      if (x0$2 != null) {
         AttributeGroup attrGroup = (AttributeGroup)x0$2._1();
         int idx = x0$2._2$mcI$sp();
         categorySizes$1[idx] = attrGroup.size();
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$2);
      }
   }

   public OneHotEncoder(final String uid) {
      this.uid = uid;
      HasHandleInvalid.$init$(this);
      HasInputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasOutputCols.$init$(this);
      OneHotEncoderBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public OneHotEncoder() {
      this(Identifiable$.MODULE$.randomUID("oneHotEncoder"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
