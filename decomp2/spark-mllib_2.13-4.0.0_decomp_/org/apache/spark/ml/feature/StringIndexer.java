package org.apache.spark.ml.feature;

import java.io.IOException;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
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
import org.apache.spark.sql.Column;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005c\u0001\u0002\u0015*\u0001QB\u0001B\u0012\u0001\u0003\u0006\u0004%\te\u0012\u0005\t=\u0002\u0011\t\u0011)A\u0005\u0011\")\u0001\r\u0001C\u0001C\")\u0001\r\u0001C\u0001M\")\u0001\u000e\u0001C\u0001S\")\u0001\u000f\u0001C\u0001c\")a\u000f\u0001C\u0001o\")!\u0010\u0001C\u0001w\")a\u0010\u0001C\u0001\u007f\"9\u0011\u0011\u0003\u0001\u0005\u0002\u0005M\u0001bBA\r\u0001\u0011%\u00111\u0004\u0005\b\u0003G\u0002A\u0011BA3\u0011\u001d\ty\b\u0001C\u0005\u0003\u0003Cq!!%\u0001\t\u0003\n\u0019\nC\u0004\u0002(\u0002!\t%!+\t\u000f\u0005u\u0006\u0001\"\u0011\u0002@\u001e9\u0011\u0011\\\u0015\t\u0002\u0005mgA\u0002\u0015*\u0011\u0003\ti\u000e\u0003\u0004a%\u0011\u0005\u00111 \u0005\n\u0003{\u0014\"\u0019!C\u0001S\u001dCq!a@\u0013A\u0003%\u0001\nC\u0005\u0003\u0002I\u0011\r\u0011\"\u0001*\u000f\"9!1\u0001\n!\u0002\u0013A\u0005\"\u0003B\u0003%\t\u0007I\u0011A\u0015H\u0011\u001d\u00119A\u0005Q\u0001\n!C!B!\u0003\u0013\u0005\u0004%\t!\u000bB\u0006\u0011!\u0011iA\u0005Q\u0001\n\u0005\r\u0001\"\u0003B\b%\t\u0007I\u0011A\u0015H\u0011\u001d\u0011\tB\u0005Q\u0001\n!C\u0011Ba\u0005\u0013\u0005\u0004%\t!K$\t\u000f\tU!\u0003)A\u0005\u0011\"I!q\u0003\nC\u0002\u0013\u0005\u0011f\u0012\u0005\b\u00053\u0011\u0002\u0015!\u0003I\u0011%\u0011YB\u0005b\u0001\n\u0003Is\tC\u0004\u0003\u001eI\u0001\u000b\u0011\u0002%\t\u0015\t}!C1A\u0005\u0002%\u0012Y\u0001\u0003\u0005\u0003\"I\u0001\u000b\u0011BA\u0002\u0011\u001d\u0011\u0019C\u0005C!\u0005KA\u0011B!\f\u0013\u0003\u0003%IAa\f\u0003\u001bM#(/\u001b8h\u0013:$W\r_3s\u0015\tQ3&A\u0004gK\u0006$XO]3\u000b\u00051j\u0013AA7m\u0015\tqs&A\u0003ta\u0006\u00148N\u0003\u00021c\u00051\u0011\r]1dQ\u0016T\u0011AM\u0001\u0004_J<7\u0001A\n\u0005\u0001Uj\u0004\tE\u00027oej\u0011aK\u0005\u0003q-\u0012\u0011\"R:uS6\fGo\u001c:\u0011\u0005iZT\"A\u0015\n\u0005qJ#AE*ue&tw-\u00138eKb,'/T8eK2\u0004\"A\u000f \n\u0005}J#!E*ue&tw-\u00138eKb,'OQ1tKB\u0011\u0011\tR\u0007\u0002\u0005*\u00111iK\u0001\u0005kRLG.\u0003\u0002F\u0005\n)B)\u001a4bk2$\b+\u0019:b[N<&/\u001b;bE2,\u0017aA;jIV\t\u0001\n\u0005\u0002J%:\u0011!\n\u0015\t\u0003\u0017:k\u0011\u0001\u0014\u0006\u0003\u001bN\na\u0001\u0010:p_Rt$\"A(\u0002\u000bM\u001c\u0017\r\\1\n\u0005Es\u0015A\u0002)sK\u0012,g-\u0003\u0002T)\n11\u000b\u001e:j]\u001eT!!\u0015()\u0007\u00051F\f\u0005\u0002X56\t\u0001L\u0003\u0002Z[\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005mC&!B*j]\u000e,\u0017%A/\u0002\u000bErCG\f\u0019\u0002\tULG\r\t\u0015\u0004\u0005Yc\u0016A\u0002\u001fj]&$h\b\u0006\u0002cGB\u0011!\b\u0001\u0005\u0006\r\u000e\u0001\r\u0001\u0013\u0015\u0004GZc\u0006fA\u0002W9R\t!\rK\u0002\u0005-r\u000b\u0001c]3u\u0011\u0006tG\r\\3J]Z\fG.\u001b3\u0015\u0005)\\W\"\u0001\u0001\t\u000b1,\u0001\u0019\u0001%\u0002\u000bY\fG.^3)\u0007\u00151f.I\u0001p\u0003\u0015\tdF\u000e\u00181\u0003I\u0019X\r^*ue&twm\u0014:eKJ$\u0016\u0010]3\u0015\u0005)\u0014\b\"\u00027\u0007\u0001\u0004A\u0005f\u0001\u0004Wi\u0006\nQ/A\u00033]Mr\u0003'A\u0006tKRLe\u000e];u\u0007>dGC\u00016y\u0011\u0015aw\u00011\u0001IQ\r9a\u000bX\u0001\rg\u0016$x*\u001e;qkR\u001cu\u000e\u001c\u000b\u0003UrDQ\u0001\u001c\u0005A\u0002!C3\u0001\u0003,]\u00031\u0019X\r^%oaV$8i\u001c7t)\rQ\u0017\u0011\u0001\u0005\u0007Y&\u0001\r!a\u0001\u0011\u000b\u0005\u0015\u0011q\u0001%\u000e\u00039K1!!\u0003O\u0005\u0015\t%O]1zQ\u0011Ia+!\u0004\"\u0005\u0005=\u0011!B\u001a/a9\u0002\u0014!D:fi>+H\u000f];u\u0007>d7\u000fF\u0002k\u0003+Aa\u0001\u001c\u0006A\u0002\u0005\r\u0001\u0006\u0002\u0006W\u0003\u001b\tqbZ3u'\u0016dWm\u0019;fI\u000e{Gn\u001d\u000b\u0007\u0003;\tY$!\u0018\u0011\r\u0005}\u0011\u0011FA\u0018\u001d\u0011\t\t#!\n\u000f\u0007-\u000b\u0019#C\u0001P\u0013\r\t9CT\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tY#!\f\u0003\u0007M+\u0017OC\u0002\u0002(9\u0003B!!\r\u000285\u0011\u00111\u0007\u0006\u0004\u0003ki\u0013aA:rY&!\u0011\u0011HA\u001a\u0005\u0019\u0019u\u000e\\;n]\"9\u0011QH\u0006A\u0002\u0005}\u0012a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003\u0003\nY\u0005\u0005\u0004\u00022\u0005\r\u0013qI\u0005\u0005\u0003\u000b\n\u0019DA\u0004ECR\f7/\u001a;\u0011\t\u0005%\u00131\n\u0007\u0001\t1\ti%a\u000f\u0002\u0002\u0003\u0005)\u0011AA(\u0005\ryF%M\t\u0005\u0003#\n9\u0006\u0005\u0003\u0002\u0006\u0005M\u0013bAA+\u001d\n9aj\u001c;iS:<\u0007\u0003BA\u0003\u00033J1!a\u0017O\u0005\r\te.\u001f\u0005\b\u0003?Z\u0001\u0019AA1\u0003%Ig\u000e];u\u0007>d7\u000fE\u0003\u0002 \u0005%\u0002*\u0001\u0006t_J$()\u001f$sKF$b!a\u001a\u0002j\u0005U\u0004CBA\u0003\u0003\u000f\t\u0019\u0001C\u0004\u0002>1\u0001\r!a\u001b1\t\u00055\u0014\u0011\u000f\t\u0007\u0003c\t\u0019%a\u001c\u0011\t\u0005%\u0013\u0011\u000f\u0003\r\u0003g\nI'!A\u0001\u0002\u000b\u0005\u0011q\n\u0002\u0004?\u0012\u0012\u0004bBA<\u0019\u0001\u0007\u0011\u0011P\u0001\nCN\u001cWM\u001c3j]\u001e\u0004B!!\u0002\u0002|%\u0019\u0011Q\u0010(\u0003\u000f\t{w\u000e\\3b]\u0006q1o\u001c:u\u0005f\fE\u000e\u001d5bE\u0016$HCBA4\u0003\u0007\u000by\tC\u0004\u0002>5\u0001\r!!\"1\t\u0005\u001d\u00151\u0012\t\u0007\u0003c\t\u0019%!#\u0011\t\u0005%\u00131\u0012\u0003\r\u0003\u001b\u000b\u0019)!A\u0001\u0002\u000b\u0005\u0011q\n\u0002\u0004?\u0012\u001a\u0004bBA<\u001b\u0001\u0007\u0011\u0011P\u0001\u0004M&$HcA\u001d\u0002\u0016\"9\u0011Q\b\bA\u0002\u0005]\u0005\u0007BAM\u0003;\u0003b!!\r\u0002D\u0005m\u0005\u0003BA%\u0003;#A\"a(\u0002\u0016\u0006\u0005\t\u0011!B\u0001\u0003\u001f\u00121a\u0018\u00135Q\u0011qa+a)\"\u0005\u0005\u0015\u0016!\u0002\u001a/a9\u0002\u0014a\u0004;sC:\u001chm\u001c:n'\u000eDW-\\1\u0015\t\u0005-\u0016q\u0017\t\u0005\u0003[\u000b\u0019,\u0004\u0002\u00020*!\u0011\u0011WA\u001a\u0003\u0015!\u0018\u0010]3t\u0013\u0011\t),a,\u0003\u0015M#(/^2u)f\u0004X\rC\u0004\u0002:>\u0001\r!a+\u0002\rM\u001c\u0007.Z7bQ\rya\u000bX\u0001\u0005G>\u0004\u0018\u0010F\u0002c\u0003\u0003Dq!a1\u0011\u0001\u0004\t)-A\u0003fqR\u0014\u0018\r\u0005\u0003\u0002H\u00065WBAAe\u0015\r\tYmK\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0005\u0003\u001f\fIM\u0001\u0005QCJ\fW.T1qQ\u0011\u0001b+a5\"\u0005\u0005U\u0017!B\u0019/i9\n\u0004f\u0001\u0001W9\u0006i1\u000b\u001e:j]\u001eLe\u000eZ3yKJ\u0004\"A\u000f\n\u0014\u000fI\ty.!:\u0002lB!\u0011QAAq\u0013\r\t\u0019O\u0014\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005\u000b9OY\u0005\u0004\u0003S\u0014%!\u0006#fM\u0006,H\u000e\u001e)be\u0006l7OU3bI\u0006\u0014G.\u001a\t\u0005\u0003[\f90\u0004\u0002\u0002p*!\u0011\u0011_Az\u0003\tIwN\u0003\u0002\u0002v\u0006!!.\u0019<b\u0013\u0011\tI0a<\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005m\u0017\u0001D*L\u0013B{\u0016J\u0014,B\u0019&#\u0015!D*L\u0013B{\u0016J\u0014,B\u0019&#\u0005%A\u0007F%J{%kX%O-\u0006c\u0015\nR\u0001\u000f\u000bJ\u0013vJU0J\u001dZ\u000bE*\u0013#!\u00031YU)\u0012)`\u0013:3\u0016\tT%E\u00035YU)\u0012)`\u0013:3\u0016\tT%EA\u000592/\u001e9q_J$X\r\u001a%b]\u0012dW-\u00138wC2LGm]\u000b\u0003\u0003\u0007\t\u0001d];qa>\u0014H/\u001a3IC:$G.Z%om\u0006d\u0017\u000eZ:!\u000351'/Z9vK:\u001c\u0017\u0010R3tG\u0006qaM]3rk\u0016t7-\u001f#fg\u000e\u0004\u0013\u0001\u00044sKF,XM\\2z\u0003N\u001c\u0017!\u00044sKF,XM\\2z\u0003N\u001c\u0007%\u0001\u0007bYBD\u0017MY3u\t\u0016\u001c8-A\u0007bYBD\u0017MY3u\t\u0016\u001c8\rI\u0001\fC2\u0004\b.\u00192fi\u0006\u001b8-\u0001\u0007bYBD\u0017MY3u\u0003N\u001c\u0007%\u0001\rtkB\u0004xN\u001d;fIN#(/\u001b8h\u001fJ$WM\u001d+za\u0016\f\u0011d];qa>\u0014H/\u001a3TiJLgnZ(sI\u0016\u0014H+\u001f9fA\u0005!An\\1e)\r\u0011'q\u0005\u0005\u0007\u0005S1\u0003\u0019\u0001%\u0002\tA\fG\u000f\u001b\u0015\u0004MYs\u0017\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B\u0019!\u0011\u0011\u0019D!\u000f\u000e\u0005\tU\"\u0002\u0002B\u001c\u0003g\fA\u0001\\1oO&!!1\bB\u001b\u0005\u0019y%M[3di\"\u001a!C\u00168)\u0007E1f\u000e"
)
public class StringIndexer extends Estimator implements StringIndexerBase, DefaultParamsWritable {
   private final String uid;
   private Param handleInvalid;
   private Param stringOrderType;
   private StringArrayParam outputCols;
   private StringArrayParam inputCols;
   private Param outputCol;
   private Param inputCol;

   public static StringIndexer load(final String path) {
      return StringIndexer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return StringIndexer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public String getStringOrderType() {
      return StringIndexerBase.getStringOrderType$(this);
   }

   public Tuple2 getInOutCols() {
      return StringIndexerBase.getInOutCols$(this);
   }

   public StructType validateAndTransformSchema(final StructType schema, final boolean skipNonExistsCol) {
      return StringIndexerBase.validateAndTransformSchema$(this, schema, skipNonExistsCol);
   }

   public boolean validateAndTransformSchema$default$2() {
      return StringIndexerBase.validateAndTransformSchema$default$2$(this);
   }

   public final String[] getOutputCols() {
      return HasOutputCols.getOutputCols$(this);
   }

   public final String[] getInputCols() {
      return HasInputCols.getInputCols$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
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

   public final Param stringOrderType() {
      return this.stringOrderType;
   }

   public void org$apache$spark$ml$feature$StringIndexerBase$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public final void org$apache$spark$ml$feature$StringIndexerBase$_setter_$stringOrderType_$eq(final Param x$1) {
      this.stringOrderType = x$1;
   }

   public final StringArrayParam outputCols() {
      return this.outputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCols$_setter_$outputCols_$eq(final StringArrayParam x$1) {
      this.outputCols = x$1;
   }

   public final StringArrayParam inputCols() {
      return this.inputCols;
   }

   public final void org$apache$spark$ml$param$shared$HasInputCols$_setter_$inputCols_$eq(final StringArrayParam x$1) {
      this.inputCols = x$1;
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

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
   }

   public String uid() {
      return this.uid;
   }

   public StringIndexer setHandleInvalid(final String value) {
      return (StringIndexer)this.set(this.handleInvalid(), value);
   }

   public StringIndexer setStringOrderType(final String value) {
      return (StringIndexer)this.set(this.stringOrderType(), value);
   }

   public StringIndexer setInputCol(final String value) {
      return (StringIndexer)this.set(this.inputCol(), value);
   }

   public StringIndexer setOutputCol(final String value) {
      return (StringIndexer)this.set(this.outputCol(), value);
   }

   public StringIndexer setInputCols(final String[] value) {
      return (StringIndexer)this.set(this.inputCols(), value);
   }

   public StringIndexer setOutputCols(final String[] value) {
      return (StringIndexer)this.set(this.outputCols(), value);
   }

   private Seq getSelectedCols(final Dataset dataset, final Seq inputCols) {
      return (Seq)inputCols.map((colName) -> {
         Column col = dataset.col(colName);
         Seq fpTypes = (Seq)(new .colon.colon(org.apache.spark.sql.types.DoubleType..MODULE$, new .colon.colon(org.apache.spark.sql.types.FloatType..MODULE$, scala.collection.immutable.Nil..MODULE$))).map((x$3) -> x$3.catalogString());
         return org.apache.spark.sql.functions..MODULE$.when(org.apache.spark.sql.functions..MODULE$.typeof(col).isin(fpTypes).$amp$amp(org.apache.spark.sql.functions..MODULE$.isnan(col)), org.apache.spark.sql.functions..MODULE$.lit((Object)null)).otherwise(col).cast(org.apache.spark.sql.types.StringType..MODULE$);
      });
   }

   private String[][] sortByFreq(final Dataset dataset, final boolean ascending) {
      Tuple2 var5 = this.getInOutCols();
      if (var5 != null) {
         String[] inputCols = (String[])var5._1();
         Seq selectedCols = this.getSelectedCols(dataset, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(inputCols).toImmutableArraySeq());
         int numCols = inputCols.length;
         Column countCol = ascending ? org.apache.spark.sql.functions..MODULE$.count(org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToInteger(1))) : org.apache.spark.sql.functions..MODULE$.negate(org.apache.spark.sql.functions..MODULE$.count(org.apache.spark.sql.functions..MODULE$.lit(BoxesRunTime.boxToInteger(1))));
         String[][] result = (String[][])scala.Array..MODULE$.fill(numCols, () -> (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.posexplode(org.apache.spark.sql.functions..MODULE$.array(selectedCols)).as(new .colon.colon("index", new .colon.colon("value", scala.collection.immutable.Nil..MODULE$)))}))).where(org.apache.spark.sql.functions..MODULE$.col("value").isNotNull()).groupBy("index", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"value"}))).agg(countCol.as("count"), scala.collection.immutable.Nil..MODULE$).groupBy("index", scala.collection.immutable.Nil..MODULE$).agg(org.apache.spark.sql.functions..MODULE$.sort_array(org.apache.spark.sql.functions..MODULE$.collect_list(org.apache.spark.sql.functions..MODULE$.struct("count", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"value"}))))).getField("value"), scala.collection.immutable.Nil..MODULE$).collect()), (r) -> {
            $anonfun$sortByFreq$2(result, r);
            return BoxedUnit.UNIT;
         });
         return result;
      } else {
         throw new MatchError(var5);
      }
   }

   private String[][] sortByAlphabet(final Dataset dataset, final boolean ascending) {
      Tuple2 var5 = this.getInOutCols();
      if (var5 != null) {
         String[] inputCols = (String[])var5._1();
         Seq selectedCols = this.getSelectedCols(dataset, org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(inputCols).toImmutableArraySeq());
         int numCols = inputCols.length;
         String[][] result = (String[][])scala.Array..MODULE$.fill(numCols, () -> (String[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(String.class)), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(String.class)));
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(dataset.select(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Column[]{org.apache.spark.sql.functions..MODULE$.posexplode(org.apache.spark.sql.functions..MODULE$.array(selectedCols)).as(new .colon.colon("index", new .colon.colon("value", scala.collection.immutable.Nil..MODULE$)))}))).where(org.apache.spark.sql.functions..MODULE$.col("value").isNotNull()).groupBy("index", scala.collection.immutable.Nil..MODULE$).agg(org.apache.spark.sql.functions..MODULE$.sort_array(org.apache.spark.sql.functions..MODULE$.collect_set("value"), ascending), scala.collection.immutable.Nil..MODULE$).collect()), (r) -> {
            $anonfun$sortByAlphabet$2(result, r);
            return BoxedUnit.UNIT;
         });
         return result;
      } else {
         throw new MatchError(var5);
      }
   }

   public StringIndexerModel fit(final Dataset dataset) {
      String[][] var12;
      label55: {
         label58: {
            this.transformSchema(dataset.schema(), true);
            String var4 = (String)this.$(this.stringOrderType());
            var12 = StringIndexer$.MODULE$.frequencyDesc();
            if (var12 == null) {
               if (var4 == null) {
                  break label58;
               }
            } else if (var12.equals(var4)) {
               break label58;
            }

            label59: {
               var12 = StringIndexer$.MODULE$.frequencyAsc();
               if (var12 == null) {
                  if (var4 == null) {
                     break label59;
                  }
               } else if (var12.equals(var4)) {
                  break label59;
               }

               label60: {
                  var12 = StringIndexer$.MODULE$.alphabetDesc();
                  if (var12 == null) {
                     if (var4 == null) {
                        break label60;
                     }
                  } else if (var12.equals(var4)) {
                     break label60;
                  }

                  var12 = StringIndexer$.MODULE$.alphabetAsc();
                  if (var12 == null) {
                     if (var4 != null) {
                        throw new MatchError(var4);
                     }
                  } else if (!var12.equals(var4)) {
                     throw new MatchError(var4);
                  }

                  var12 = this.sortByAlphabet(dataset, true);
                  break label55;
               }

               var12 = this.sortByAlphabet(dataset, false);
               break label55;
            }

            var12 = this.sortByFreq(dataset, true);
            break label55;
         }

         var12 = this.sortByFreq(dataset, false);
      }

      String[][] labelsArray = var12;
      return (StringIndexerModel)this.copyValues((new StringIndexerModel(this.uid(), labelsArray)).setParent(this), this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      return this.validateAndTransformSchema(schema, this.validateAndTransformSchema$default$2());
   }

   public StringIndexer copy(final ParamMap extra) {
      return (StringIndexer)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final void $anonfun$sortByFreq$2(final String[][] result$1, final Row r) {
      result$1[r.getInt(0)] = (String[])r.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   // $FF: synthetic method
   public static final void $anonfun$sortByAlphabet$2(final String[][] result$2, final Row r) {
      result$2[r.getInt(0)] = (String[])r.getSeq(1).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class));
   }

   public StringIndexer(final String uid) {
      this.uid = uid;
      HasHandleInvalid.$init$(this);
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasInputCols.$init$(this);
      HasOutputCols.$init$(this);
      StringIndexerBase.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public StringIndexer() {
      this(Identifiable$.MODULE$.randomUID("strIdx"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
