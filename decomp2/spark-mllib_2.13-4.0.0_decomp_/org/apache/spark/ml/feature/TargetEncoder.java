package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.StringArrayParam;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasInputCols;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.param.shared.HasOutputCols;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.functions.;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.mutable.Map;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uh\u0001B\u0014)\u0001MB\u0001\"\u0012\u0001\u0003\u0006\u0004%\tE\u0012\u0005\t;\u0002\u0011\t\u0011)A\u0005\u000f\")q\f\u0001C\u0001A\")q\f\u0001C\u0001K\")q\r\u0001C\u0001Q\")Q\u000e\u0001C\u0001]\")\u0011\u000f\u0001C\u0001e\")Q\u000f\u0001C\u0001m\")a\u0010\u0001C\u0001\u007f\"9\u0011Q\u0001\u0001\u0005\u0002\u0005\u001d\u0001bBA\u0007\u0001\u0011\u0005\u0011q\u0002\u0005\b\u0003+\u0001A\u0011AA\f\u0011\u001d\t\u0019\u0003\u0001C!\u0003KAq!!\u0010\u0001\t\u0013\ty\u0004C\u0004\u0002R\u0001!I!a\u0015\t\u000f\u0005]\u0003\u0001\"\u0011\u0002Z!9\u0011q\u0010\u0001\u0005B\u0005\u0005uaBALQ!\u0005\u0011\u0011\u0014\u0004\u0007O!B\t!a'\t\r}\u001bB\u0011AA]\u0011%\tYl\u0005b\u0001\n\u0003Ac\tC\u0004\u0002>N\u0001\u000b\u0011B$\t\u0013\u0005}6C1A\u0005\u0002!2\u0005bBAa'\u0001\u0006Ia\u0012\u0005\u000b\u0003\u0007\u001c\"\u0019!C\u0001Q\u0005\u0015\u0007bBAd'\u0001\u0006I!\u001f\u0005\n\u0003\u0013\u001c\"\u0019!C\u0001Q\u0019Cq!a3\u0014A\u0003%q\tC\u0005\u0002NN\u0011\r\u0011\"\u0001)\r\"9\u0011qZ\n!\u0002\u00139\u0005BCAi'\t\u0007I\u0011\u0001\u0015\u0002F\"9\u00111[\n!\u0002\u0013I\bBCAk'\t\u0007I\u0011\u0001\u0015\u0002X\"A\u0011\u0011\\\n!\u0002\u0013\tY\u0002\u0003\u0006\u0002\\N\u0011\r\u0011\"\u0001)\u0003/D\u0001\"!8\u0014A\u0003%\u00111\u0004\u0005\b\u0003?\u001cB\u0011IAq\u0011%\tIoEA\u0001\n\u0013\tYOA\u0007UCJ<W\r^#oG>$WM\u001d\u0006\u0003S)\nqAZ3biV\u0014XM\u0003\u0002,Y\u0005\u0011Q\u000e\u001c\u0006\u0003[9\nQa\u001d9be.T!a\f\u0019\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0014aA8sO\u000e\u00011\u0003\u0002\u00015y}\u00022!\u000e\u001c9\u001b\u0005Q\u0013BA\u001c+\u0005%)5\u000f^5nCR|'\u000f\u0005\u0002:u5\t\u0001&\u0003\u0002<Q\t\u0011B+\u0019:hKR,enY8eKJlu\u000eZ3m!\tIT(\u0003\u0002?Q\t\tB+\u0019:hKR,enY8eKJ\u0014\u0015m]3\u0011\u0005\u0001\u001bU\"A!\u000b\u0005\tS\u0013\u0001B;uS2L!\u0001R!\u0003+\u0011+g-Y;miB\u000b'/Y7t/JLG/\u00192mK\u0006\u0019Q/\u001b3\u0016\u0003\u001d\u0003\"\u0001S)\u000f\u0005%{\u0005C\u0001&N\u001b\u0005Y%B\u0001'3\u0003\u0019a$o\\8u})\ta*A\u0003tG\u0006d\u0017-\u0003\u0002Q\u001b\u00061\u0001K]3eK\u001aL!AU*\u0003\rM#(/\u001b8h\u0015\t\u0001V\nK\u0002\u0002+n\u0003\"AV-\u000e\u0003]S!\u0001\u0017\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002[/\n)1+\u001b8dK\u0006\nA,A\u00035]Ar\u0003'\u0001\u0003vS\u0012\u0004\u0003f\u0001\u0002V7\u00061A(\u001b8jiz\"\"!\u00192\u0011\u0005e\u0002\u0001\"B#\u0004\u0001\u00049\u0005f\u00012V7\"\u001a1!V.\u0015\u0003\u0005D3\u0001B+\\\u0003-\u0019X\r\u001e'bE\u0016d7i\u001c7\u0015\u0005%TW\"\u0001\u0001\t\u000b-,\u0001\u0019A$\u0002\u000bY\fG.^3)\u0007\u0015)6,A\u0006tKRLe\u000e];u\u0007>dGCA5p\u0011\u0015Yg\u00011\u0001HQ\r1QkW\u0001\rg\u0016$x*\u001e;qkR\u001cu\u000e\u001c\u000b\u0003SNDQa[\u0004A\u0002\u001dC3aB+\\\u00031\u0019X\r^%oaV$8i\u001c7t)\tIw\u000fC\u0003y\u0011\u0001\u0007\u00110\u0001\u0004wC2,Xm\u001d\t\u0004un<U\"A'\n\u0005ql%!B!se\u0006L\bf\u0001\u0005V7\u0006i1/\u001a;PkR\u0004X\u000f^\"pYN$2![A\u0001\u0011\u0015A\u0018\u00021\u0001zQ\rIQkW\u0001\u0011g\u0016$\b*\u00198eY\u0016LeN^1mS\u0012$2![A\u0005\u0011\u0015Y'\u00021\u0001HQ\rQQkW\u0001\u000eg\u0016$H+\u0019:hKR$\u0016\u0010]3\u0015\u0007%\f\t\u0002C\u0003l\u0017\u0001\u0007q\tK\u0002\f+n\u000bAb]3u'6|w\u000e\u001e5j]\u001e$2![A\r\u0011\u0019YG\u00021\u0001\u0002\u001cA\u0019!0!\b\n\u0007\u0005}QJ\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u0019U[\u0016a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005\u001d\u0012q\u0007\t\u0005\u0003S\t\u0019$\u0004\u0002\u0002,)!\u0011QFA\u0018\u0003\u0015!\u0018\u0010]3t\u0015\r\t\t\u0004L\u0001\u0004gFd\u0017\u0002BA\u001b\u0003W\u0011!b\u0015;sk\u000e$H+\u001f9f\u0011\u001d\tI$\u0004a\u0001\u0003O\taa]2iK6\f\u0007fA\u0007V7\u0006aQ\r\u001f;sC\u000e$H*\u00192fYR1\u0011\u0011IA%\u0003\u001b\u0002B!a\u0011\u0002F5\u0011\u0011qF\u0005\u0005\u0003\u000f\nyC\u0001\u0004D_2,XN\u001c\u0005\u0007\u0003\u0017r\u0001\u0019A$\u0002\t9\fW.\u001a\u0005\u0007\u0003\u001fr\u0001\u0019A$\u0002\u0015Q\f'oZ3u)f\u0004X-\u0001\u0007fqR\u0014\u0018m\u0019;WC2,X\r\u0006\u0003\u0002B\u0005U\u0003BBA&\u001f\u0001\u0007q)A\u0002gSR$2\u0001OA.\u0011\u001d\ti\u0006\u0005a\u0001\u0003?\nq\u0001Z1uCN,G\u000f\r\u0003\u0002b\u0005-\u0004CBA\"\u0003G\n9'\u0003\u0003\u0002f\u0005=\"a\u0002#bi\u0006\u001cX\r\u001e\t\u0005\u0003S\nY\u0007\u0004\u0001\u0005\u0019\u00055\u00141LA\u0001\u0002\u0003\u0015\t!a\u001c\u0003\u0007}#\u0013'\u0005\u0003\u0002r\u0005]\u0004c\u0001>\u0002t%\u0019\u0011QO'\u0003\u000f9{G\u000f[5oOB\u0019!0!\u001f\n\u0007\u0005mTJA\u0002B]fD3\u0001E+\\\u0003\u0011\u0019w\u000e]=\u0015\u0007\u0005\f\u0019\tC\u0004\u0002\u0006F\u0001\r!a\"\u0002\u000b\u0015DHO]1\u0011\t\u0005%\u0015qR\u0007\u0003\u0003\u0017S1!!$+\u0003\u0015\u0001\u0018M]1n\u0013\u0011\t\t*a#\u0003\u0011A\u000b'/Y7NCBD3!E+\\Q\r\u0001QkW\u0001\u000e)\u0006\u0014x-\u001a;F]\u000e|G-\u001a:\u0011\u0005e\u001a2cB\n\u0002\u001e\u0006\r\u0016\u0011\u0016\t\u0004u\u0006}\u0015bAAQ\u001b\n1\u0011I\\=SK\u001a\u0004B\u0001QASC&\u0019\u0011qU!\u0003+\u0011+g-Y;miB\u000b'/Y7t%\u0016\fG-\u00192mKB!\u00111VA[\u001b\t\tiK\u0003\u0003\u00020\u0006E\u0016AA5p\u0015\t\t\u0019,\u0001\u0003kCZ\f\u0017\u0002BA\\\u0003[\u0013AbU3sS\u0006d\u0017N_1cY\u0016$\"!!'\u0002\u0019-+U\tU0J\u001dZ\u000bE*\u0013#\u0002\u001b-+U\tU0J\u001dZ\u000bE*\u0013#!\u00035)%KU(S?&se+\u0011'J\t\u0006qQI\u0015*P%~KeJV!M\u0013\u0012\u0003\u0013aF:vaB|'\u000f^3e\u0011\u0006tG\r\\3J]Z\fG.\u001b3t+\u0005I\u0018\u0001G:vaB|'\u000f^3e\u0011\u0006tG\r\\3J]Z\fG.\u001b3tA\u0005iA+\u0011*H\u000bR{&)\u0013(B%f\u000ba\u0002V!S\u000f\u0016#vLQ%O\u0003JK\u0006%A\tU\u0003J;U\tV0D\u001f:#\u0016JT+P+N\u000b!\u0003V!S\u000f\u0016#vlQ(O)&sUkT+TA\u0005!2/\u001e9q_J$X\r\u001a+be\u001e,G\u000fV=qKN\fQc];qa>\u0014H/\u001a3UCJ<W\r\u001e+za\u0016\u001c\b%A\bV\u001dN+UIT0D\u0003R+ui\u0014*Z+\t\tY\"\u0001\tV\u001dN+UIT0D\u0003R+ui\u0014*ZA\u0005ia*\u0016'M?\u000e\u000bE+R$P%f\u000baBT+M\u0019~\u001b\u0015\tV#H\u001fJK\u0006%\u0001\u0003m_\u0006$GcA1\u0002d\"1\u0011Q]\u0013A\u0002\u001d\u000bA\u0001]1uQ\"\u001aQ%V.\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u00055\b\u0003BAx\u0003kl!!!=\u000b\t\u0005M\u0018\u0011W\u0001\u0005Y\u0006tw-\u0003\u0003\u0002x\u0006E(AB(cU\u0016\u001cG\u000fK\u0002\u0014+nC3AE+\\\u0001"
)
public class TargetEncoder extends Estimator implements TargetEncoderBase, DefaultParamsWritable {
   private final String uid;
   private Param handleInvalid;
   private Param targetType;
   private DoubleParam smoothing;
   private StringArrayParam outputCols;
   private Param outputCol;
   private StringArrayParam inputCols;
   private Param inputCol;
   private Param labelCol;

   public static TargetEncoder load(final String path) {
      return TargetEncoder$.MODULE$.load(path);
   }

   public static MLReader read() {
      return TargetEncoder$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public final String getTargetType() {
      return TargetEncoderBase.getTargetType$(this);
   }

   public final double getSmoothing() {
      return TargetEncoderBase.getSmoothing$(this);
   }

   public String[] inputFeatures() {
      return TargetEncoderBase.inputFeatures$(this);
   }

   public String[] outputFeatures() {
      return TargetEncoderBase.outputFeatures$(this);
   }

   public StructType validateSchema(final StructType schema, final boolean fitting) {
      return TargetEncoderBase.validateSchema$(this, schema, fitting);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
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

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public Param targetType() {
      return this.targetType;
   }

   public DoubleParam smoothing() {
      return this.smoothing;
   }

   public void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$targetType_$eq(final Param x$1) {
      this.targetType = x$1;
   }

   public void org$apache$spark$ml$feature$TargetEncoderBase$_setter_$smoothing_$eq(final DoubleParam x$1) {
      this.smoothing = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
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

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public TargetEncoder setLabelCol(final String value) {
      return (TargetEncoder)this.set(this.labelCol(), value);
   }

   public TargetEncoder setInputCol(final String value) {
      return (TargetEncoder)this.set(this.inputCol(), value);
   }

   public TargetEncoder setOutputCol(final String value) {
      return (TargetEncoder)this.set(this.outputCol(), value);
   }

   public TargetEncoder setInputCols(final String[] values) {
      return (TargetEncoder)this.set(this.inputCols(), values);
   }

   public TargetEncoder setOutputCols(final String[] values) {
      return (TargetEncoder)this.set(this.outputCols(), values);
   }

   public TargetEncoder setHandleInvalid(final String value) {
      return (TargetEncoder)this.set(this.handleInvalid(), value);
   }

   public TargetEncoder setTargetType(final String value) {
      return (TargetEncoder)this.set(this.targetType(), value);
   }

   public TargetEncoder setSmoothing(final double value) {
      return (TargetEncoder)this.set(this.smoothing(), BoxesRunTime.boxToDouble(value));
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateSchema(schema, true);
   }

   private Column extractLabel(final String name, final String targetType) {
      Column c = .MODULE$.col(name).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
      String var10000 = TargetEncoder$.MODULE$.TARGET_BINARY();
      if (var10000 == null) {
         if (targetType == null) {
            return .MODULE$.when(c.$eq$eq$eq(BoxesRunTime.boxToInteger(0)).$bar$bar(c.$eq$eq$eq(BoxesRunTime.boxToInteger(1))), c).when(c.isNull().$bar$bar(c.isNaN()), c).otherwise(.MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Labels for TARGET_BINARY must be {0, 1}, but got "), c})))));
         }
      } else if (var10000.equals(targetType)) {
         return .MODULE$.when(c.$eq$eq$eq(BoxesRunTime.boxToInteger(0)).$bar$bar(c.$eq$eq$eq(BoxesRunTime.boxToInteger(1))), c).when(c.isNull().$bar$bar(c.isNaN()), c).otherwise(.MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Labels for TARGET_BINARY must be {0, 1}, but got "), c})))));
      }

      var10000 = TargetEncoder$.MODULE$.TARGET_CONTINUOUS();
      if (var10000 == null) {
         if (targetType == null) {
            return c;
         }
      } else if (var10000.equals(targetType)) {
         return c;
      }

      throw new MatchError(targetType);
   }

   private Column extractValue(final String name) {
      Column c = .MODULE$.col(name).cast(org.apache.spark.sql.types.DoubleType..MODULE$);
      return .MODULE$.when(c.$greater$eq(BoxesRunTime.boxToInteger(0)).$amp$amp(c.$eq$eq$eq(c.cast(org.apache.spark.sql.types.IntegerType..MODULE$))), c).when(c.isNull(), .MODULE$.lit(BoxesRunTime.boxToDouble(TargetEncoder$.MODULE$.NULL_CATEGORY()))).when(c.isNaN(), .MODULE$.raise_error(.MODULE$.lit("Values MUST NOT be NaN"))).otherwise(.MODULE$.raise_error(.MODULE$.concat(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.lit("Values MUST be non-negative integers, but got "), c})))));
   }

   public TargetEncoderModel fit(final Dataset dataset) {
      int numFeatures;
      Dataset checked;
      Column var14;
      label29: {
         label32: {
            this.validateSchema(dataset.schema(), true);
            numFeatures = this.inputFeatures().length;
            Column arrayCol = .MODULE$.array(scala.collection.ArrayOps..MODULE$.toIndexedSeq$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.$colon$plus$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.inputFeatures()), (v) -> this.extractValue(v), scala.reflect.ClassTag..MODULE$.apply(Column.class))), .MODULE$.lit(BoxesRunTime.boxToDouble(TargetEncoder$.MODULE$.UNSEEN_CATEGORY())), scala.reflect.ClassTag..MODULE$.apply(Column.class)))));
            checked = dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{this.extractLabel((String)this.$(this.labelCol()), (String)this.$(this.targetType())).as("label"), arrayCol.as("array")}))).where(.MODULE$.col("label").isNaN().unary_$bang().$amp$amp(.MODULE$.col("label").isNull().unary_$bang())).select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{.MODULE$.col("label"), .MODULE$.posexplode(.MODULE$.col("array")).as(new scala.collection.immutable..colon.colon("index", new scala.collection.immutable..colon.colon("value", scala.collection.immutable.Nil..MODULE$)))})));
            String var7 = (String)this.$(this.targetType());
            String var10000 = TargetEncoder$.MODULE$.TARGET_BINARY();
            if (var10000 == null) {
               if (var7 == null) {
                  break label32;
               }
            } else if (var10000.equals(var7)) {
               break label32;
            }

            var10000 = TargetEncoder$.MODULE$.TARGET_CONTINUOUS();
            if (var10000 == null) {
               if (var7 != null) {
                  throw new MatchError(var7);
               }
            } else if (!var10000.equals(var7)) {
               throw new MatchError(var7);
            }

            var14 = .MODULE$.avg(.MODULE$.col("label"));
            break label29;
         }

         var14 = .MODULE$.count_if(.MODULE$.col("label").$eq$eq$eq(BoxesRunTime.boxToInteger(1)));
      }

      Column statCol = var14;
      Dataset aggregated = checked.groupBy("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"value"}))).agg(.MODULE$.count(.MODULE$.lit(BoxesRunTime.boxToInteger(1))).cast(org.apache.spark.sql.types.DoubleType..MODULE$).as("count"), scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{statCol.cast(org.apache.spark.sql.types.DoubleType..MODULE$).as("stat")})));
      Map[] stats = (Map[])scala.Array..MODULE$.fill(numFeatures, () -> (Map)scala.collection.mutable.Map..MODULE$.empty(), scala.reflect.ClassTag..MODULE$.apply(Map.class));
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(aggregated.select("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"value", "count", "stat"}))).collect()), (x0$1) -> {
         $anonfun$fit$3(numFeatures, stats, x0$1);
         return BoxedUnit.UNIT;
      });
      TargetEncoderModel model = (TargetEncoderModel)(new TargetEncoderModel(this.uid(), (scala.collection.immutable.Map[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])stats), (x$1) -> x$1.toMap(scala..less.colon.less..MODULE$.refl()), scala.reflect.ClassTag..MODULE$.apply(scala.collection.immutable.Map.class)))).setParent(this);
      return (TargetEncoderModel)this.copyValues(model, this.copyValues$default$2());
   }

   public TargetEncoder copy(final ParamMap extra) {
      return (TargetEncoder)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final void $anonfun$fit$4(final double x4$1, final double x5$1, final double x6$1, final Map s) {
      s.update(BoxesRunTime.boxToDouble(x4$1), new Tuple2.mcDD.sp(x5$1, x6$1));
   }

   // $FF: synthetic method
   public static final void $anonfun$fit$3(final int numFeatures$1, final Map[] stats$1, final Row x0$1) {
      if (x0$1 != null) {
         Some var5 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
         if (!var5.isEmpty() && var5.get() != null && ((SeqOps)var5.get()).lengthCompare(4) == 0) {
            Object index = ((SeqOps)var5.get()).apply(0);
            Object value = ((SeqOps)var5.get()).apply(1);
            Object count = ((SeqOps)var5.get()).apply(2);
            Object stat = ((SeqOps)var5.get()).apply(3);
            if (index instanceof Integer) {
               int var10 = BoxesRunTime.unboxToInt(index);
               if (value instanceof Double) {
                  double var11 = BoxesRunTime.unboxToDouble(value);
                  if (count instanceof Double) {
                     double var13 = BoxesRunTime.unboxToDouble(count);
                     if (stat instanceof Double) {
                        double var15 = BoxesRunTime.unboxToDouble(stat);
                        if (var10 < numFeatures$1) {
                           stats$1[var10].update(BoxesRunTime.boxToDouble(var11), new Tuple2.mcDD.sp(var13, var15));
                           BoxedUnit var17 = BoxedUnit.UNIT;
                           return;
                        }

                        scala.Predef..MODULE$.assert(var11 == TargetEncoder$.MODULE$.UNSEEN_CATEGORY());
                        scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])stats$1), (s) -> {
                           $anonfun$fit$4(var11, var13, var15, s);
                           return BoxedUnit.UNIT;
                        });
                        BoxedUnit var10000 = BoxedUnit.UNIT;
                        return;
                     }
                  }
               }
            }
         }
      }

      throw new MatchError(x0$1);
   }

   public TargetEncoder(final String uid) {
      this.uid = uid;
      HasLabelCol.$init$(this);
      HasInputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCol.$init$(this);
      HasOutputCols.$init$(this);
      HasHandleInvalid.$init$(this);
      TargetEncoderBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public TargetEncoder() {
      this(Identifiable$.MODULE$.randomUID("TargetEncoder"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
