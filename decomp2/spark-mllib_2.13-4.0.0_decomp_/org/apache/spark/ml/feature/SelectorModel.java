package org.apache.spark.ml.feature;

import java.io.IOException;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasLabelCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Tuple2;
import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mfa\u0002\n\u0014\u0003\u0003)Rd\t\u0005\tu\u0001\u0011)\u0019!C\u0001w!A\u0001\u000b\u0001B\u0001B\u0003%A\b\u0003\u0005S\u0001\t\u0015\r\u0011\"\u0001T\u0011!Y\u0006A!A!\u0002\u0013!\u0006\"B/\u0001\t\u0003q\u0006\"B2\u0001\t\u0003!\u0007\"B5\u0001\t\u0003Q\u0007\"B7\u0001\t#q\u0007\"\u0002:\u0001\t\u0003\u001a\bbBA\u0015\u0001\u0011\u0005\u00131F\u0004\t\u0003\u0003\u001a\u0002\u0012A\n\u0002D\u00199!c\u0005E\u0001'\u0005\u0015\u0003BB/\r\t\u0003\ti\u0006\u0003\u0004s\u0019\u0011\u0005\u0011q\f\u0005\b\u0003wbA\u0011AA?\u0011\u001d\ty\t\u0004C\u0001\u0003#C\u0011\"a+\r\u0003\u0003%I!!,\u0003\u001bM+G.Z2u_Jlu\u000eZ3m\u0015\t!R#A\u0004gK\u0006$XO]3\u000b\u0005Y9\u0012AA7m\u0015\tA\u0012$A\u0003ta\u0006\u00148N\u0003\u0002\u001b7\u00051\u0011\r]1dQ\u0016T\u0011\u0001H\u0001\u0004_J<WC\u0001\u0010&'\u0011\u0001q$\r\u001b\u0011\u0007\u0001\n3%D\u0001\u0016\u0013\t\u0011SCA\u0003N_\u0012,G\u000e\u0005\u0002%K1\u0001A!\u0002\u0014\u0001\u0005\u0004A#!\u0001+\u0004\u0001E\u0011\u0011f\f\t\u0003U5j\u0011a\u000b\u0006\u0002Y\u0005)1oY1mC&\u0011af\u000b\u0002\b\u001d>$\b.\u001b8h!\r\u0001\u0004aI\u0007\u0002'A\u0011\u0001GM\u0005\u0003gM\u0011abU3mK\u000e$xN\u001d)be\u0006l7\u000f\u0005\u00026q5\taG\u0003\u00028+\u0005!Q\u000f^5m\u0013\tIdG\u0001\u0006N\u0019^\u0013\u0018\u000e^1cY\u0016\f1!^5e+\u0005a\u0004CA\u001fE\u001d\tq$\t\u0005\u0002@W5\t\u0001I\u0003\u0002BO\u00051AH]8pizJ!aQ\u0016\u0002\rA\u0013X\rZ3g\u0013\t)eI\u0001\u0004TiJLgn\u001a\u0006\u0003\u0007.B3!\u0001%O!\tIE*D\u0001K\u0015\tYu#\u0001\u0006b]:|G/\u0019;j_:L!!\u0014&\u0003\u000bMKgnY3\"\u0003=\u000bQa\r\u00182]A\nA!^5eA!\u001a!\u0001\u0013(\u0002!M,G.Z2uK\u00124U-\u0019;ve\u0016\u001cX#\u0001+\u0011\u0007)*v+\u0003\u0002WW\t)\u0011I\u001d:bsB\u0011!\u0006W\u0005\u00033.\u00121!\u00138uQ\r\u0019\u0001JT\u0001\u0012g\u0016dWm\u0019;fI\u001a+\u0017\r^;sKN\u0004\u0003f\u0001\u0003I\u001d\u00061A(\u001b8jiz\"2aL0b\u0011\u0015QT\u00011\u0001=Q\ry\u0006J\u0014\u0005\u0006%\u0016\u0001\r\u0001\u0016\u0015\u0004C\"s\u0015AD:fi\u001a+\u0017\r^;sKN\u001cu\u000e\u001c\u000b\u0003K\u001al\u0011\u0001\u0001\u0005\u0006O\u001a\u0001\r\u0001P\u0001\u0006m\u0006dW/\u001a\u0015\u0004\r!s\u0015\u0001D:fi>+H\u000f];u\u0007>dGCA3l\u0011\u00159w\u00011\u0001=Q\r9\u0001JT\u0001\u0013SNtU/\\3sS\u000e\fE\u000f\u001e:jEV$X-F\u0001p!\tQ\u0003/\u0003\u0002rW\t9!i\\8mK\u0006t\u0017!\u0003;sC:\u001chm\u001c:n)\r!\u00181\u0002\t\u0004k\u0006\u0015aB\u0001<\u0000\u001d\t9XP\u0004\u0002yy:\u0011\u0011p\u001f\b\u0003\u007fiL\u0011\u0001H\u0005\u00035mI!\u0001G\r\n\u0005y<\u0012aA:rY&!\u0011\u0011AA\u0002\u0003\u001d\u0001\u0018mY6bO\u0016T!A`\f\n\t\u0005\u001d\u0011\u0011\u0002\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!!\u0001\u0002\u0004!9\u0011QB\u0005A\u0002\u0005=\u0011a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003#\tY\u0002\u0005\u0004\u0002\u0014\u0005U\u0011\u0011D\u0007\u0003\u0003\u0007IA!a\u0006\u0002\u0004\t9A)\u0019;bg\u0016$\bc\u0001\u0013\u0002\u001c\u0011a\u0011QDA\u0006\u0003\u0003\u0005\tQ!\u0001\u0002 \t\u0019q\f\n\u001a\u0012\u0007%\n\t\u0003E\u0002+\u0003GI1!!\n,\u0005\r\te.\u001f\u0015\u0004\u0013!s\u0015a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u00055\u0012\u0011\b\t\u0005\u0003_\t)$\u0004\u0002\u00022)!\u00111GA\u0002\u0003\u0015!\u0018\u0010]3t\u0013\u0011\t9$!\r\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002<)\u0001\r!!\f\u0002\rM\u001c\u0007.Z7bQ\rQ\u0001J\u0014\u0015\u0004\u0001!s\u0015!D*fY\u0016\u001cGo\u001c:N_\u0012,G\u000e\u0005\u00021\u0019M)A\"a\u0012\u0002NA\u0019!&!\u0013\n\u0007\u0005-3F\u0001\u0004B]f\u0014VM\u001a\t\u0005\u0003\u001f\nI&\u0004\u0002\u0002R)!\u00111KA+\u0003\tIwN\u0003\u0002\u0002X\u0005!!.\u0019<b\u0013\u0011\tY&!\u0015\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005\rCc\u0003;\u0002b\u00055\u0014qNA:\u0003oBq!!\u0004\u000f\u0001\u0004\t\u0019\u0007\r\u0003\u0002f\u0005%\u0004CBA\n\u0003+\t9\u0007E\u0002%\u0003S\"A\"a\u001b\u0002b\u0005\u0005\t\u0011!B\u0001\u0003?\u00111a\u0018\u00134\u0011\u0015\u0011f\u00021\u0001U\u0011\u001d\t\tH\u0004a\u0001\u0003[\tAb\\;uaV$8k\u00195f[\u0006Da!!\u001e\u000f\u0001\u0004a\u0014!C8viB,HoQ8m\u0011\u0019\tIH\u0004a\u0001y\u0005Ya-Z1ukJ,7oQ8m\u0003=\u0001(/\u001a9PkR\u0004X\u000f\u001e$jK2$G\u0003DA@\u0003\u000b\u000b9)!#\u0002\f\u00065\u0005\u0003BA\u0018\u0003\u0003KA!a!\u00022\tY1\u000b\u001e:vGR4\u0015.\u001a7e\u0011\u001d\tYd\u0004a\u0001\u0003[AQAU\bA\u0002QCa!!\u001e\u0010\u0001\u0004a\u0004BBA=\u001f\u0001\u0007A\bC\u0003n\u001f\u0001\u0007q.\u0001\bd_6\u0004(/Z:t'B\f'o]3\u0015\u0011\u0005M\u0015\u0011UAS\u0003S\u0003bAKAK)\u0006e\u0015bAALW\t1A+\u001e9mKJ\u0002BAK+\u0002\u001cB\u0019!&!(\n\u0007\u0005}5F\u0001\u0004E_V\u0014G.\u001a\u0005\u0007\u0003G\u0003\u0002\u0019\u0001+\u0002\u000f%tG-[2fg\"9\u0011q\u0015\tA\u0002\u0005e\u0015A\u0002<bYV,7\u000fC\u0003S!\u0001\u0007A+\u0001\u0007xe&$XMU3qY\u0006\u001cW\r\u0006\u0002\u00020B!\u0011\u0011WA\\\u001b\t\t\u0019L\u0003\u0003\u00026\u0006U\u0013\u0001\u00027b]\u001eLA!!/\u00024\n1qJ\u00196fGR\u0004"
)
public abstract class SelectorModel extends Model implements SelectorParams, MLWritable {
   private final String uid;
   private final int[] selectedFeatures;
   private IntParam numTopFeatures;
   private DoubleParam percentile;
   private DoubleParam fpr;
   private DoubleParam fdr;
   private DoubleParam fwe;
   private Param selectorType;
   private Param outputCol;
   private Param labelCol;
   private Param featuresCol;

   public static Tuple2 compressSparse(final int[] indices, final double[] values, final int[] selectedFeatures) {
      return SelectorModel$.MODULE$.compressSparse(indices, values, selectedFeatures);
   }

   public static StructField prepOutputField(final StructType schema, final int[] selectedFeatures, final String outputCol, final String featuresCol, final boolean isNumericAttribute) {
      return SelectorModel$.MODULE$.prepOutputField(schema, selectedFeatures, outputCol, featuresCol, isNumericAttribute);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getNumTopFeatures() {
      return SelectorParams.getNumTopFeatures$(this);
   }

   public double getPercentile() {
      return SelectorParams.getPercentile$(this);
   }

   public double getFpr() {
      return SelectorParams.getFpr$(this);
   }

   public double getFdr() {
      return SelectorParams.getFdr$(this);
   }

   public double getFwe() {
      return SelectorParams.getFwe$(this);
   }

   public String getSelectorType() {
      return SelectorParams.getSelectorType$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getLabelCol() {
      return HasLabelCol.getLabelCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final IntParam numTopFeatures() {
      return this.numTopFeatures;
   }

   public final DoubleParam percentile() {
      return this.percentile;
   }

   public final DoubleParam fpr() {
      return this.fpr;
   }

   public final DoubleParam fdr() {
      return this.fdr;
   }

   public final DoubleParam fwe() {
      return this.fwe;
   }

   public final Param selectorType() {
      return this.selectorType;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$numTopFeatures_$eq(final IntParam x$1) {
      this.numTopFeatures = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$percentile_$eq(final DoubleParam x$1) {
      this.percentile = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$fpr_$eq(final DoubleParam x$1) {
      this.fpr = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$fdr_$eq(final DoubleParam x$1) {
      this.fdr = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$fwe_$eq(final DoubleParam x$1) {
      this.fwe = x$1;
   }

   public final void org$apache$spark$ml$feature$SelectorParams$_setter_$selectorType_$eq(final Param x$1) {
      this.selectorType = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
   }

   public final Param labelCol() {
      return this.labelCol;
   }

   public final void org$apache$spark$ml$param$shared$HasLabelCol$_setter_$labelCol_$eq(final Param x$1) {
      this.labelCol = x$1;
   }

   public final Param featuresCol() {
      return this.featuresCol;
   }

   public final void org$apache$spark$ml$param$shared$HasFeaturesCol$_setter_$featuresCol_$eq(final Param x$1) {
      this.featuresCol = x$1;
   }

   public String uid() {
      return this.uid;
   }

   public int[] selectedFeatures() {
      return this.selectedFeatures;
   }

   public SelectorModel setFeaturesCol(final String value) {
      return (SelectorModel)this.set(this.featuresCol(), value);
   }

   public SelectorModel setOutputCol(final String value) {
      return (SelectorModel)this.set(this.outputCol(), value);
   }

   public boolean isNumericAttribute() {
      return true;
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      return SelectorModel$.MODULE$.transform(dataset, (int[]).MODULE$.sorted$extension(scala.Predef..MODULE$.intArrayOps(this.selectedFeatures()), scala.math.Ordering.Int..MODULE$), outputSchema, (String)this.$(this.outputCol()), (String)this.$(this.featuresCol()));
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      StructField newField = SelectorModel$.MODULE$.prepOutputField(schema, this.selectedFeatures(), (String)this.$(this.outputCol()), (String)this.$(this.featuresCol()), this.isNumericAttribute());
      return SchemaUtils$.MODULE$.appendColumn(schema, newField);
   }

   public SelectorModel(final String uid, final int[] selectedFeatures) {
      this.uid = uid;
      this.selectedFeatures = selectedFeatures;
      HasFeaturesCol.$init$(this);
      HasLabelCol.$init$(this);
      HasOutputCol.$init$(this);
      SelectorParams.$init$(this);
      MLWritable.$init$(this);
      Statics.releaseFence();
   }
}
