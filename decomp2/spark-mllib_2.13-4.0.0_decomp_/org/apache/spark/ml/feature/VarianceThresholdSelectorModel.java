package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.fs.Path;
import org.apache.spark.ml.Model;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.DoubleParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasFeaturesCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.ml.util.DefaultParamsReader$;
import org.apache.spark.ml.util.DefaultParamsWriter$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.Some;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tmf\u0001\u0002\u001a4\u0001yB\u0001B\u0014\u0001\u0003\u0006\u0004%\te\u0014\u0005\tM\u0002\u0011\t\u0011)A\u0005!\"A\u0001\u000e\u0001BC\u0002\u0013\u0005\u0011\u000e\u0003\u0005s\u0001\t\u0005\t\u0015!\u0003k\u0011\u0019!\b\u0001\"\u00016k\"1A\u000f\u0001C\u0001kiDQa\u001f\u0001\u0005\u0002qDq!a\u0001\u0001\t\u0003\t)\u0001C\u0004\u0002\f\u0001!\t%!\u0004\t\u000f\u0005]\u0003\u0001\"\u0011\u0002Z!9\u0011Q\u000e\u0001\u0005B\u0005=\u0004bBAB\u0001\u0011\u0005\u0013Q\u0011\u0005\b\u0003\u001f\u0003A\u0011IAI\u000f\u001d\t9j\rE\u0001\u000333aAM\u001a\t\u0002\u0005m\u0005B\u0002;\u0010\t\u0003\tI\fC\u0004\u0002<>!\t%!0\t\u000f\u0005\u001dw\u0002\"\u0011\u0002J\u001a9\u0011\u0011[\b\u0001\u001f\u0005M\u0007\"CAk'\t\u0005\t\u0015!\u0003D\u0011\u0019!8\u0003\"\u0001\u0002X\u001a1\u0011q\\\nE\u0003CD\u0011\u0002\u001b\f\u0003\u0016\u0004%\t!a>\t\u0013I4\"\u0011#Q\u0001\n\u0005e\bB\u0002;\u0017\t\u0003\ty\u0010C\u0005\u0002nY\t\t\u0011\"\u0001\u0003\b!I!1\u0002\f\u0012\u0002\u0013\u0005!Q\u0002\u0005\n\u0005C1\u0012\u0011!C!\u0005GA\u0011Ba\f\u0017\u0003\u0003%\tA!\r\t\u0013\tMb#!A\u0005\u0002\tU\u0002\"\u0003B\u001e-\u0005\u0005I\u0011\tB\u001f\u0011%\u0011YEFA\u0001\n\u0003\u0011i\u0005C\u0005\u0003XY\t\t\u0011\"\u0011\u0003Z!I!Q\f\f\u0002\u0002\u0013\u0005#q\f\u0005\n\u0003\u001f3\u0012\u0011!C!\u0005CB\u0011Ba\u0019\u0017\u0003\u0003%\tE!\u001a\b\u0013\t%4#!A\t\n\t-d!CAp'\u0005\u0005\t\u0012\u0002B7\u0011\u0019!h\u0005\"\u0001\u0003|!I\u0011q\u0012\u0014\u0002\u0002\u0013\u0015#\u0011\r\u0005\n\u0005{2\u0013\u0011!CA\u0005\u007fB\u0011Ba!'\u0003\u0003%\tI!\"\t\u000f\tE5\u0003\"\u0015\u0003\u0014\u001a1!QT\b\u0005\u0005?Ca\u0001\u001e\u0017\u0005\u0002\t\u0005\u0006\"\u0003BSY\t\u0007I\u0011\u0002B\u0012\u0011!\u00119\u000b\fQ\u0001\n\t\u0015\u0002bBAdY\u0011\u0005#\u0011\u0016\u0005\n\u0005[{\u0011\u0011!C\u0005\u0005_\u0013aDV1sS\u0006t7-\u001a+ie\u0016\u001c\bn\u001c7e'\u0016dWm\u0019;pe6{G-\u001a7\u000b\u0005Q*\u0014a\u00024fCR,(/\u001a\u0006\u0003m]\n!!\u001c7\u000b\u0005aJ\u0014!B:qCJ\\'B\u0001\u001e<\u0003\u0019\t\u0007/Y2iK*\tA(A\u0002pe\u001e\u001c\u0001a\u0005\u0003\u0001\u007f\u0015C\u0005c\u0001!B\u00076\tQ'\u0003\u0002Ck\t)Qj\u001c3fYB\u0011A\tA\u0007\u0002gA\u0011AIR\u0005\u0003\u000fN\u0012qDV1sS\u0006t7-\u001a+ie\u0016\u001c\bn\u001c7e'\u0016dWm\u0019;peB\u000b'/Y7t!\tIE*D\u0001K\u0015\tYU'\u0001\u0003vi&d\u0017BA'K\u0005)iEj\u0016:ji\u0006\u0014G.Z\u0001\u0004k&$W#\u0001)\u0011\u0005ESfB\u0001*Y!\t\u0019f+D\u0001U\u0015\t)V(\u0001\u0004=e>|GO\u0010\u0006\u0002/\u0006)1oY1mC&\u0011\u0011LV\u0001\u0007!J,G-\u001a4\n\u0005mc&AB*ue&twM\u0003\u0002Z-\"\u001a\u0011A\u00183\u0011\u0005}\u0013W\"\u00011\u000b\u0005\u0005<\u0014AC1o]>$\u0018\r^5p]&\u00111\r\u0019\u0002\u0006'&t7-Z\u0011\u0002K\u0006)1GL\u0019/a\u0005!Q/\u001b3!Q\r\u0011a\fZ\u0001\u0011g\u0016dWm\u0019;fI\u001a+\u0017\r^;sKN,\u0012A\u001b\t\u0004W2tW\"\u0001,\n\u000554&!B!se\u0006L\bCA6p\u0013\t\u0001hKA\u0002J]RD3a\u00010e\u0003E\u0019X\r\\3di\u0016$g)Z1ukJ,7\u000f\t\u0015\u0004\ty#\u0017A\u0002\u001fj]&$h\bF\u0002DmbDQAT\u0003A\u0002AC3A\u001e0e\u0011\u0015AW\u00011\u0001kQ\rAh\f\u001a\u000b\u0002\u0007\u0006q1/\u001a;GK\u0006$XO]3t\u0007>dGCA?\u007f\u001b\u0005\u0001\u0001\"B@\b\u0001\u0004\u0001\u0016!\u0002<bYV,\u0007fA\u0004_I\u0006a1/\u001a;PkR\u0004X\u000f^\"pYR\u0019Q0a\u0002\t\u000b}D\u0001\u0019\u0001))\u0007!qF-A\u0005ue\u0006t7OZ8s[R!\u0011qBA\u0019!\u0011\t\t\"a\u000b\u000f\t\u0005M\u0011Q\u0005\b\u0005\u0003+\t\tC\u0004\u0003\u0002\u0018\u0005}a\u0002BA\r\u0003;q1aUA\u000e\u0013\u0005a\u0014B\u0001\u001e<\u0013\tA\u0014(C\u0002\u0002$]\n1a]9m\u0013\u0011\t9#!\u000b\u0002\u000fA\f7m[1hK*\u0019\u00111E\u001c\n\t\u00055\u0012q\u0006\u0002\n\t\u0006$\u0018M\u0012:b[\u0016TA!a\n\u0002*!9\u00111G\u0005A\u0002\u0005U\u0012a\u00023bi\u0006\u001cX\r\u001e\u0019\u0005\u0003o\t\u0019\u0005\u0005\u0004\u0002:\u0005m\u0012qH\u0007\u0003\u0003SIA!!\u0010\u0002*\t9A)\u0019;bg\u0016$\b\u0003BA!\u0003\u0007b\u0001\u0001\u0002\u0007\u0002F\u0005E\u0012\u0011!A\u0001\u0006\u0003\t9EA\u0002`II\nB!!\u0013\u0002PA\u00191.a\u0013\n\u0007\u00055cKA\u0004O_RD\u0017N\\4\u0011\u0007-\f\t&C\u0002\u0002TY\u00131!\u00118zQ\rIa\fZ\u0001\u0010iJ\fgn\u001d4pe6\u001c6\r[3nCR!\u00111LA4!\u0011\ti&a\u0019\u000e\u0005\u0005}#\u0002BA1\u0003S\tQ\u0001^=qKNLA!!\u001a\u0002`\tQ1\u000b\u001e:vGR$\u0016\u0010]3\t\u000f\u0005%$\u00021\u0001\u0002\\\u000511o\u00195f[\u0006D3A\u00030e\u0003\u0011\u0019w\u000e]=\u0015\u0007\r\u000b\t\bC\u0004\u0002t-\u0001\r!!\u001e\u0002\u000b\u0015DHO]1\u0011\t\u0005]\u0014QP\u0007\u0003\u0003sR1!a\u001f6\u0003\u0015\u0001\u0018M]1n\u0013\u0011\ty(!\u001f\u0003\u0011A\u000b'/Y7NCBD3a\u00030e\u0003\u00159(/\u001b;f+\t\t9\tE\u0002J\u0003\u0013K1!a#K\u0005!iEj\u0016:ji\u0016\u0014\bf\u0001\u0007_I\u0006AAo\\*ue&tw\rF\u0001QQ\ria\f\u001a\u0015\u0004\u0001y#\u0017A\b,be&\fgnY3UQJ,7\u000f[8mIN+G.Z2u_Jlu\u000eZ3m!\t!ubE\u0004\u0010\u0003;\u000b\u0019+!+\u0011\u0007-\fy*C\u0002\u0002\"Z\u0013a!\u00118z%\u00164\u0007\u0003B%\u0002&\u000eK1!a*K\u0005)iEJU3bI\u0006\u0014G.\u001a\t\u0005\u0003W\u000b),\u0004\u0002\u0002.*!\u0011qVAY\u0003\tIwN\u0003\u0002\u00024\u0006!!.\u0019<b\u0013\u0011\t9,!,\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005e\u0015\u0001\u0002:fC\u0012,\"!a0\u0011\t%\u000b\tmQ\u0005\u0004\u0003\u0007T%\u0001C'M%\u0016\fG-\u001a:)\u0007EqF-\u0001\u0003m_\u0006$GcA\"\u0002L\"1\u0011Q\u001a\nA\u0002A\u000bA\u0001]1uQ\"\u001a!C\u00183\u0003?Y\u000b'/[1oG\u0016$\u0006N]3tQ>dGmU3mK\u000e$xN],sSR,'oE\u0002\u0014\u0003\u000f\u000b\u0001\"\u001b8ti\u0006t7-\u001a\u000b\u0005\u00033\fi\u000eE\u0002\u0002\\Ni\u0011a\u0004\u0005\u0007\u0003+,\u0002\u0019A\"\u0003\t\u0011\u000bG/Y\n\b-\u0005u\u00151]Au!\rY\u0017Q]\u0005\u0004\u0003O4&a\u0002)s_\u0012,8\r\u001e\t\u0005\u0003W\f\u0019P\u0004\u0003\u0002n\u0006EhbA*\u0002p&\tq+C\u0002\u0002(YKA!a.\u0002v*\u0019\u0011q\u0005,\u0016\u0005\u0005e\b#BAv\u0003wt\u0017\u0002BA\u007f\u0003k\u00141aU3r)\u0011\u0011\tA!\u0002\u0011\u0007\t\ra#D\u0001\u0014\u0011\u0019A\u0017\u00041\u0001\u0002zR!!\u0011\u0001B\u0005\u0011!A'\u0004%AA\u0002\u0005e\u0018AD2paf$C-\u001a4bk2$H%M\u000b\u0003\u0005\u001fQC!!?\u0003\u0012-\u0012!1\u0003\t\u0005\u0005+\u0011i\"\u0004\u0002\u0003\u0018)!!\u0011\u0004B\u000e\u0003%)hn\u00195fG.,GM\u0003\u0002b-&!!q\u0004B\f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\t\u0015\u0002\u0003\u0002B\u0014\u0005[i!A!\u000b\u000b\t\t-\u0012\u0011W\u0001\u0005Y\u0006tw-C\u0002\\\u0005S\tA\u0002\u001d:pIV\u001cG/\u0011:jif,\u0012A\\\u0001\u000faJ|G-^2u\u000b2,W.\u001a8u)\u0011\tyEa\u000e\t\u0011\teb$!AA\u00029\f1\u0001\u001f\u00132\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B !\u0019\u0011\tEa\u0012\u0002P5\u0011!1\t\u0006\u0004\u0005\u000b2\u0016AC2pY2,7\r^5p]&!!\u0011\nB\"\u0005!IE/\u001a:bi>\u0014\u0018\u0001C2b]\u0016\u000bX/\u00197\u0015\t\t=#Q\u000b\t\u0004W\nE\u0013b\u0001B*-\n9!i\\8mK\u0006t\u0007\"\u0003B\u001dA\u0005\u0005\t\u0019AA(\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\t\u0015\"1\f\u0005\t\u0005s\t\u0013\u0011!a\u0001]\u0006A\u0001.Y:i\u0007>$W\rF\u0001o)\t\u0011)#\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005\u001f\u00129\u0007C\u0005\u0003:\u0011\n\t\u00111\u0001\u0002P\u0005!A)\u0019;b!\r\u0011\u0019AJ\n\u0006M\t=\u0014\u0011\u0016\t\t\u0005c\u00129(!?\u0003\u00025\u0011!1\u000f\u0006\u0004\u0005k2\u0016a\u0002:v]RLW.Z\u0005\u0005\u0005s\u0012\u0019HA\tBEN$(/Y2u\rVt7\r^5p]F\"\"Aa\u001b\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\t\t\u0005!\u0011\u0011\u0005\u0007Q&\u0002\r!!?\u0002\u000fUt\u0017\r\u001d9msR!!q\u0011BG!\u0015Y'\u0011RA}\u0013\r\u0011YI\u0016\u0002\u0007\u001fB$\u0018n\u001c8\t\u0013\t=%&!AA\u0002\t\u0005\u0011a\u0001=%a\u0005A1/\u0019<f\u00136\u0004H\u000e\u0006\u0003\u0003\u0016\nm\u0005cA6\u0003\u0018&\u0019!\u0011\u0014,\u0003\tUs\u0017\u000e\u001e\u0005\u0007\u0003\u001b\\\u0003\u0019\u0001)\u0003IY\u000b'/[1oG\u0016$\u0006N]3tQ>dGmU3mK\u000e$xN]'pI\u0016d'+Z1eKJ\u001c2\u0001LA`)\t\u0011\u0019\u000bE\u0002\u0002\\2\n\u0011b\u00197bgNt\u0015-\\3\u0002\u0015\rd\u0017m]:OC6,\u0007\u0005F\u0002D\u0005WCa!!41\u0001\u0004\u0001\u0016\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001BY!\u0011\u00119Ca-\n\t\tU&\u0011\u0006\u0002\u0007\u001f\nTWm\u0019;)\u0007=qF\rK\u0002\u000f=\u0012\u0004"
)
public class VarianceThresholdSelectorModel extends Model implements VarianceThresholdSelectorParams, MLWritable {
   private final String uid;
   private final int[] selectedFeatures;
   private DoubleParam varianceThreshold;
   private Param outputCol;
   private Param featuresCol;

   public static VarianceThresholdSelectorModel load(final String path) {
      return VarianceThresholdSelectorModel$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VarianceThresholdSelectorModel$.MODULE$.read();
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public double getVarianceThreshold() {
      return VarianceThresholdSelectorParams.getVarianceThreshold$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getFeaturesCol() {
      return HasFeaturesCol.getFeaturesCol$(this);
   }

   public final DoubleParam varianceThreshold() {
      return this.varianceThreshold;
   }

   public final void org$apache$spark$ml$feature$VarianceThresholdSelectorParams$_setter_$varianceThreshold_$eq(final DoubleParam x$1) {
      this.varianceThreshold = x$1;
   }

   public final Param outputCol() {
      return this.outputCol;
   }

   public final void org$apache$spark$ml$param$shared$HasOutputCol$_setter_$outputCol_$eq(final Param x$1) {
      this.outputCol = x$1;
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

   public VarianceThresholdSelectorModel setFeaturesCol(final String value) {
      return (VarianceThresholdSelectorModel)this.set(this.featuresCol(), value);
   }

   public VarianceThresholdSelectorModel setOutputCol(final String value) {
      return (VarianceThresholdSelectorModel)this.set(this.outputCol(), value);
   }

   public Dataset transform(final Dataset dataset) {
      StructType outputSchema = this.transformSchema(dataset.schema(), true);
      return SelectorModel$.MODULE$.transform(dataset, this.selectedFeatures(), outputSchema, (String)this.$(this.outputCol()), (String)this.$(this.featuresCol()));
   }

   public StructType transformSchema(final StructType schema) {
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.featuresCol()), new VectorUDT(), SchemaUtils$.MODULE$.checkColumnType$default$4());
      StructField newField = SelectorModel$.MODULE$.prepOutputField(schema, this.selectedFeatures(), (String)this.$(this.outputCol()), (String)this.$(this.featuresCol()), true);
      return SchemaUtils$.MODULE$.appendColumn(schema, newField);
   }

   public VarianceThresholdSelectorModel copy(final ParamMap extra) {
      VarianceThresholdSelectorModel copied = (VarianceThresholdSelectorModel)(new VarianceThresholdSelectorModel(this.uid(), this.selectedFeatures())).setParent(this.parent());
      return (VarianceThresholdSelectorModel)this.copyValues(copied, extra);
   }

   public MLWriter write() {
      return new VarianceThresholdSelectorWriter(this);
   }

   public String toString() {
      String var10000 = this.uid();
      return "VarianceThresholdSelectorModel: uid=" + var10000 + ", numSelectedFeatures=" + this.selectedFeatures().length;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$1(final int[] l) {
      return l[0] < l[1];
   }

   public VarianceThresholdSelectorModel(final String uid, final int[] selectedFeatures) {
      this.uid = uid;
      this.selectedFeatures = selectedFeatures;
      HasFeaturesCol.$init$(this);
      HasOutputCol.$init$(this);
      VarianceThresholdSelectorParams.$init$(this);
      MLWritable.$init$(this);
      if (selectedFeatures.length >= 2) {
         Predef var10000 = .MODULE$;
         Object qual$1 = .MODULE$.intArrayOps(selectedFeatures);
         int x$1 = 2;
         int x$2 = scala.collection.ArrayOps..MODULE$.sliding$default$2$extension(qual$1);
         var10000.require(scala.collection.ArrayOps..MODULE$.sliding$extension(qual$1, 2, x$2).forall((l) -> BoxesRunTime.boxToBoolean($anonfun$new$1(l))), () -> "Index should be strictly increasing.");
      }

      Statics.releaseFence();
   }

   public VarianceThresholdSelectorModel() {
      this("", scala.Array..MODULE$.emptyIntArray());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public static class VarianceThresholdSelectorWriter extends MLWriter {
      private volatile Data$ Data$module;
      private final VarianceThresholdSelectorModel instance;

      private Data$ Data() {
         if (this.Data$module == null) {
            this.Data$lzycompute$1();
         }

         return this.Data$module;
      }

      public void saveImpl(final String path) {
         DefaultParamsWriter$.MODULE$.saveMetadata(this.instance, path, this.sparkSession());
         Data data = new Data(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(this.instance.selectedFeatures()).toImmutableArraySeq());
         String dataPath = (new Path(path, "data")).toString();
         SparkSession var10000 = this.sparkSession();
         scala.collection.immutable..colon.colon var10001 = new scala.collection.immutable..colon.colon(data, scala.collection.immutable.Nil..MODULE$);
         JavaUniverse $u = scala.reflect.runtime.package..MODULE$.universe();
         JavaUniverse.JavaMirror $m = scala.reflect.runtime.package..MODULE$.universe().runtimeMirror(VarianceThresholdSelectorWriter.class.getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticClass("org.apache.spark.ml.feature.VarianceThresholdSelectorModel.VarianceThresholdSelectorWriter")), $u.internal().reificationSupport().selectType($m$untyped.staticClass("org.apache.spark.ml.feature.VarianceThresholdSelectorModel.VarianceThresholdSelectorWriter"), "Data"), scala.collection.immutable.Nil..MODULE$);
            }

            public $typecreator1$1() {
            }
         }

         var10000.createDataFrame(var10001, ((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())).write().parquet(dataPath);
      }

      private final void Data$lzycompute$1() {
         synchronized(this){}

         try {
            if (this.Data$module == null) {
               this.Data$module = new Data$();
            }
         } catch (Throwable var3) {
            throw var3;
         }

      }

      public VarianceThresholdSelectorWriter(final VarianceThresholdSelectorModel instance) {
         this.instance = instance;
      }

      private class Data implements Product, Serializable {
         private final Seq selectedFeatures;
         // $FF: synthetic field
         public final VarianceThresholdSelectorWriter $outer;

         public Iterator productElementNames() {
            return Product.productElementNames$(this);
         }

         public Seq selectedFeatures() {
            return this.selectedFeatures;
         }

         public Data copy(final Seq selectedFeatures) {
            return this.org$apache$spark$ml$feature$VarianceThresholdSelectorModel$VarianceThresholdSelectorWriter$Data$$$outer().new Data(selectedFeatures);
         }

         public Seq copy$default$1() {
            return this.selectedFeatures();
         }

         public String productPrefix() {
            return "Data";
         }

         public int productArity() {
            return 1;
         }

         public Object productElement(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return this.selectedFeatures();
               }
               default -> {
                  return Statics.ioobe(x$1);
               }
            }
         }

         public Iterator productIterator() {
            return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
         }

         public boolean canEqual(final Object x$1) {
            return x$1 instanceof Data;
         }

         public String productElementName(final int x$1) {
            switch (x$1) {
               case 0 -> {
                  return "selectedFeatures";
               }
               default -> {
                  return (String)Statics.ioobe(x$1);
               }
            }
         }

         public int hashCode() {
            return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
         }

         public String toString() {
            return scala.runtime.ScalaRunTime..MODULE$._toString(this);
         }

         public boolean equals(final Object x$1) {
            boolean var6;
            if (this != x$1) {
               label52: {
                  if (x$1 instanceof Data && ((Data)x$1).org$apache$spark$ml$feature$VarianceThresholdSelectorModel$VarianceThresholdSelectorWriter$Data$$$outer() == this.org$apache$spark$ml$feature$VarianceThresholdSelectorModel$VarianceThresholdSelectorWriter$Data$$$outer()) {
                     label42: {
                        Data var4 = (Data)x$1;
                        Seq var10000 = this.selectedFeatures();
                        Seq var5 = var4.selectedFeatures();
                        if (var10000 == null) {
                           if (var5 != null) {
                              break label42;
                           }
                        } else if (!var10000.equals(var5)) {
                           break label42;
                        }

                        if (var4.canEqual(this)) {
                           break label52;
                        }
                     }
                  }

                  var6 = false;
                  return var6;
               }
            }

            var6 = true;
            return var6;
         }

         // $FF: synthetic method
         public VarianceThresholdSelectorWriter org$apache$spark$ml$feature$VarianceThresholdSelectorModel$VarianceThresholdSelectorWriter$Data$$$outer() {
            return this.$outer;
         }

         public Data(final Seq selectedFeatures) {
            this.selectedFeatures = selectedFeatures;
            if (VarianceThresholdSelectorWriter.this == null) {
               throw null;
            } else {
               this.$outer = VarianceThresholdSelectorWriter.this;
               super();
               Product.$init$(this);
            }
         }
      }

      private class Data$ extends AbstractFunction1 implements Serializable {
         // $FF: synthetic field
         private final VarianceThresholdSelectorWriter $outer;

         public final String toString() {
            return "Data";
         }

         public Data apply(final Seq selectedFeatures) {
            return this.$outer.new Data(selectedFeatures);
         }

         public Option unapply(final Data x$0) {
            return (Option)(x$0 == null ? scala.None..MODULE$ : new Some(x$0.selectedFeatures()));
         }

         public Data$() {
            if (VarianceThresholdSelectorWriter.this == null) {
               throw null;
            } else {
               this.$outer = VarianceThresholdSelectorWriter.this;
               super();
            }
         }
      }
   }

   private static class VarianceThresholdSelectorModelReader extends MLReader {
      private final String className = VarianceThresholdSelectorModel.class.getName();

      private String className() {
         return this.className;
      }

      public VarianceThresholdSelectorModel load(final String path) {
         DefaultParamsReader.Metadata metadata = DefaultParamsReader$.MODULE$.loadMetadata(path, this.sparkSession(), this.className());
         String dataPath = (new Path(path, "data")).toString();
         Row data = (Row)this.sparkSession().read().parquet(dataPath).select("selectedFeatures", scala.collection.immutable.Nil..MODULE$).head();
         int[] selectedFeatures = (int[])((IterableOnceOps)data.getAs(0)).toArray(scala.reflect.ClassTag..MODULE$.Int());
         VarianceThresholdSelectorModel model = new VarianceThresholdSelectorModel(metadata.uid(), selectedFeatures);
         metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
         return model;
      }

      public VarianceThresholdSelectorModelReader() {
      }
   }
}
