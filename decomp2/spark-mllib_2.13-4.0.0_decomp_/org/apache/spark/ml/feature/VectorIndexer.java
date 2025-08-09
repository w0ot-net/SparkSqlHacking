package org.apache.spark.ml.feature;

import java.io.IOException;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.Estimator;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.VectorUDT;
import org.apache.spark.ml.param.IntParam;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.param.ParamMap;
import org.apache.spark.ml.param.shared.HasHandleInvalid;
import org.apache.spark.ml.param.shared.HasInputCol;
import org.apache.spark.ml.param.shared.HasOutputCol;
import org.apache.spark.ml.util.DatasetUtils$;
import org.apache.spark.ml.util.DefaultParamsWritable;
import org.apache.spark.ml.util.Identifiable$;
import org.apache.spark.ml.util.MLReader;
import org.apache.spark.ml.util.MLWritable;
import org.apache.spark.ml.util.MLWriter;
import org.apache.spark.ml.util.SchemaUtils$;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.util.collection.OpenHashSet;
import scala.Function2;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t5c\u0001\u0002\u0014(\u0001IB\u0001\u0002\u0012\u0001\u0003\u0006\u0004%\t%\u0012\u0005\t9\u0002\u0011\t\u0011)A\u0005\r\")a\f\u0001C\u0001?\")a\f\u0001C\u0001I\")a\r\u0001C\u0001O\")\u0001\u000f\u0001C\u0001c\")A\u000f\u0001C\u0001k\")\u0001\u0010\u0001C\u0001s\")a\u0010\u0001C!\u007f\"9\u0011q\u0006\u0001\u0005B\u0005E\u0002bBA#\u0001\u0011\u0005\u0013qI\u0004\b\u0003C:\u0003\u0012AA2\r\u00191s\u0005#\u0001\u0002f!1a,\u0004C\u0001\u0003\u0007C\u0011\"!\"\u000e\u0005\u0004%\taJ#\t\u000f\u0005\u001dU\u0002)A\u0005\r\"I\u0011\u0011R\u0007C\u0002\u0013\u0005q%\u0012\u0005\b\u0003\u0017k\u0001\u0015!\u0003G\u0011%\ti)\u0004b\u0001\n\u00039S\tC\u0004\u0002\u00106\u0001\u000b\u0011\u0002$\t\u0015\u0005EUB1A\u0005\u0002\u001d\n\u0019\n\u0003\u0005\u0002\u001c6\u0001\u000b\u0011BAK\u0011\u001d\ti*\u0004C!\u0003?3a!a+\u000e\t\u00055\u0006BCA`1\t\u0015\r\u0011\"\u0003\u0002B\"I\u00111\u0019\r\u0003\u0002\u0003\u0006Ia\u001b\u0005\u000b\u0003\u000bD\"Q1A\u0005\n\u0005\u0005\u0007\"CAd1\t\u0005\t\u0015!\u0003l\u0011\u0019q\u0006\u0004\"\u0001\u0002J\"I\u00111\u001b\rC\u0002\u0013%\u0011Q\u001b\u0005\t\u0003[D\u0002\u0015!\u0003\u0002X\"9\u0011q\u001e\r\u0005\u0002\u0005E\bbBA|1\u0011\u0005\u0011\u0011 \u0005\b\u0005#AB\u0011\u0001B\n\u0011\u001d\u0011i\u0002\u0007C\u0005\u0005?AqAa\u000b\u0019\t\u0013\u0011i\u0003C\u0005\u0003:5\t\t\u0011\"\u0003\u0003<\tia+Z2u_JLe\u000eZ3yKJT!\u0001K\u0015\u0002\u000f\u0019,\u0017\r^;sK*\u0011!fK\u0001\u0003[2T!\u0001L\u0017\u0002\u000bM\u0004\u0018M]6\u000b\u00059z\u0013AB1qC\u000eDWMC\u00011\u0003\ry'oZ\u0002\u0001'\u0011\u00011g\u000f \u0011\u0007Q*t'D\u0001*\u0013\t1\u0014FA\u0005FgRLW.\u0019;peB\u0011\u0001(O\u0007\u0002O%\u0011!h\n\u0002\u0013-\u0016\u001cGo\u001c:J]\u0012,\u00070\u001a:N_\u0012,G\u000e\u0005\u00029y%\u0011Qh\n\u0002\u0014-\u0016\u001cGo\u001c:J]\u0012,\u00070\u001a:QCJ\fWn\u001d\t\u0003\u007f\tk\u0011\u0001\u0011\u0006\u0003\u0003&\nA!\u001e;jY&\u00111\t\u0011\u0002\u0016\t\u00164\u0017-\u001e7u!\u0006\u0014\u0018-\\:Xe&$\u0018M\u00197f\u0003\r)\u0018\u000eZ\u000b\u0002\rB\u0011q\t\u0015\b\u0003\u0011:\u0003\"!\u0013'\u000e\u0003)S!aS\u0019\u0002\rq\u0012xn\u001c;?\u0015\u0005i\u0015!B:dC2\f\u0017BA(M\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011K\u0015\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005=c\u0005fA\u0001U5B\u0011Q\u000bW\u0007\u0002-*\u0011qkK\u0001\u000bC:tw\u000e^1uS>t\u0017BA-W\u0005\u0015\u0019\u0016N\\2fC\u0005Y\u0016!B\u0019/i9\u0002\u0014\u0001B;jI\u0002B3A\u0001+[\u0003\u0019a\u0014N\\5u}Q\u0011\u0001-\u0019\t\u0003q\u0001AQ\u0001R\u0002A\u0002\u0019C3!\u0019+[Q\r\u0019AK\u0017\u000b\u0002A\"\u001aA\u0001\u0016.\u0002!M,G/T1y\u0007\u0006$XmZ8sS\u0016\u001cHC\u00015j\u001b\u0005\u0001\u0001\"\u00026\u0006\u0001\u0004Y\u0017!\u0002<bYV,\u0007C\u00017n\u001b\u0005a\u0015B\u00018M\u0005\rIe\u000e\u001e\u0015\u0004\u000bQS\u0016aC:fi&s\u0007/\u001e;D_2$\"\u0001\u001b:\t\u000b)4\u0001\u0019\u0001$)\u0007\u0019!&,\u0001\u0007tKR|U\u000f\u001e9vi\u000e{G\u000e\u0006\u0002im\")!n\u0002a\u0001\r\"\u001aq\u0001\u0016.\u0002!M,G\u000fS1oI2,\u0017J\u001c<bY&$GC\u00015{\u0011\u0015Q\u0007\u00021\u0001GQ\rAA\u000b`\u0011\u0002{\u0006)!GL\u001a/a\u0005\u0019a-\u001b;\u0015\u0007]\n\t\u0001C\u0004\u0002\u0004%\u0001\r!!\u0002\u0002\u000f\u0011\fG/Y:fiB\"\u0011qAA\f!\u0019\tI!a\u0004\u0002\u00145\u0011\u00111\u0002\u0006\u0004\u0003\u001bY\u0013aA:rY&!\u0011\u0011CA\u0006\u0005\u001d!\u0015\r^1tKR\u0004B!!\u0006\u0002\u00181\u0001A\u0001DA\r\u0003\u0003\t\t\u0011!A\u0003\u0002\u0005m!aA0%cE!\u0011QDA\u0012!\ra\u0017qD\u0005\u0004\u0003Ca%a\u0002(pi\"Lgn\u001a\t\u0004Y\u0006\u0015\u0012bAA\u0014\u0019\n\u0019\u0011I\\=)\t%!\u00161F\u0011\u0003\u0003[\tQA\r\u00181]A\nq\u0002\u001e:b]N4wN]7TG\",W.\u0019\u000b\u0005\u0003g\ty\u0004\u0005\u0003\u00026\u0005mRBAA\u001c\u0015\u0011\tI$a\u0003\u0002\u000bQL\b/Z:\n\t\u0005u\u0012q\u0007\u0002\u000b'R\u0014Xo\u0019;UsB,\u0007bBA!\u0015\u0001\u0007\u00111G\u0001\u0007g\u000eDW-\\1)\u0007)!&,\u0001\u0003d_BLHc\u00011\u0002J!9\u00111J\u0006A\u0002\u00055\u0013!B3yiJ\f\u0007\u0003BA(\u0003+j!!!\u0015\u000b\u0007\u0005M\u0013&A\u0003qCJ\fW.\u0003\u0003\u0002X\u0005E#\u0001\u0003)be\u0006lW*\u00199)\t-!\u00161L\u0011\u0003\u0003;\nQ!\r\u00185]EB3\u0001\u0001+[\u000351Vm\u0019;pe&sG-\u001a=feB\u0011\u0001(D\n\b\u001b\u0005\u001d\u0014QNA:!\ra\u0017\u0011N\u0005\u0004\u0003Wb%AB!osJ+g\r\u0005\u0003@\u0003_\u0002\u0017bAA9\u0001\n)B)\u001a4bk2$\b+\u0019:b[N\u0014V-\u00193bE2,\u0007\u0003BA;\u0003\u007fj!!a\u001e\u000b\t\u0005e\u00141P\u0001\u0003S>T!!! \u0002\t)\fg/Y\u0005\u0005\u0003\u0003\u000b9H\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002d\u0005a1kS%Q?&se+\u0011'J\t\u0006i1kS%Q?&se+\u0011'J\t\u0002\nQ\"\u0012*S\u001fJ{\u0016J\u0014,B\u0019&#\u0015AD#S%>\u0013v,\u0013(W\u00032KE\tI\u0001\r\u0017\u0016+\u0005kX%O-\u0006c\u0015\nR\u0001\u000e\u0017\u0016+\u0005kX%O-\u0006c\u0015\n\u0012\u0011\u0002/M,\b\u000f]8si\u0016$\u0007*\u00198eY\u0016LeN^1mS\u0012\u001cXCAAK!\u0011a\u0017q\u0013$\n\u0007\u0005eEJA\u0003BeJ\f\u00170\u0001\rtkB\u0004xN\u001d;fI\"\u000bg\u000e\u001a7f\u0013:4\u0018\r\\5eg\u0002\nA\u0001\\8bIR\u0019\u0001-!)\t\r\u0005\rv\u00031\u0001G\u0003\u0011\u0001\u0018\r\u001e5)\t]!\u0016qU\u0011\u0003\u0003S\u000bQ!\r\u00187]A\u0012QbQ1uK\u001e|'/_*uCR\u001c8#\u0002\r\u0002h\u0005=\u0006\u0003BAY\u0003wsA!a-\u00028:\u0019\u0011*!.\n\u00035K1!!/M\u0003\u001d\u0001\u0018mY6bO\u0016LA!!!\u0002>*\u0019\u0011\u0011\u0018'\u0002\u00179,XNR3biV\u0014Xm]\u000b\u0002W\u0006aa.^7GK\u0006$XO]3tA\u0005iQ.\u0019=DCR,wm\u001c:jKN\fa\"\\1y\u0007\u0006$XmZ8sS\u0016\u001c\b\u0005\u0006\u0004\u0002L\u0006=\u0017\u0011\u001b\t\u0004\u0003\u001bDR\"A\u0007\t\r\u0005}V\u00041\u0001l\u0011\u0019\t)-\ba\u0001W\u0006\u0001b-Z1ukJ,g+\u00197vKN+Go]\u000b\u0003\u0003/\u0004R\u0001\\AL\u00033\u0004b!a7\u0002d\u0006\u001dXBAAo\u0015\u0011\ty.!9\u0002\u0015\r|G\u000e\\3di&|gN\u0003\u0002BW%!\u0011Q]Ao\u0005-y\u0005/\u001a8ICND7+\u001a;\u0011\u00071\fI/C\u0002\u0002l2\u0013a\u0001R8vE2,\u0017!\u00054fCR,(/\u001a,bYV,7+\u001a;tA\u0005)Q.\u001a:hKR!\u00111ZAz\u0011\u001d\t)\u0010\ta\u0001\u0003\u0017\fQa\u001c;iKJ\f\u0011\"\u00193e-\u0016\u001cGo\u001c:\u0015\t\u0005m(\u0011\u0001\t\u0004Y\u0006u\u0018bAA\u0000\u0019\n!QK\\5u\u0011\u001d\u0011\u0019!\ta\u0001\u0005\u000b\t\u0011A\u001e\t\u0005\u0005\u000f\u0011i!\u0004\u0002\u0003\n)\u0019!1B\u0015\u0002\r1Lg.\u00197h\u0013\u0011\u0011yA!\u0003\u0003\rY+7\r^8s\u0003=9W\r^\"bi\u0016<wN]=NCB\u001cXC\u0001B\u000b!\u00199%qC6\u0003\u001c%\u0019!\u0011\u0004*\u0003\u00075\u000b\u0007\u000f\u0005\u0004H\u0005/\t9o[\u0001\u000fC\u0012$G)\u001a8tKZ+7\r^8s)\u0011\tYP!\t\t\u000f\t\r2\u00051\u0001\u0003&\u0005\u0011AM\u001e\t\u0005\u0005\u000f\u00119#\u0003\u0003\u0003*\t%!a\u0003#f]N,g+Z2u_J\fq\"\u00193e'B\f'o]3WK\u000e$xN\u001d\u000b\u0005\u0003w\u0014y\u0003C\u0004\u00032\u0011\u0002\rAa\r\u0002\u0005M4\b\u0003\u0002B\u0004\u0005kIAAa\u000e\u0003\n\ta1\u000b]1sg\u00164Vm\u0019;pe\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011!Q\b\t\u0005\u0005\u007f\u0011)%\u0004\u0002\u0003B)!!1IA>\u0003\u0011a\u0017M\\4\n\t\t\u001d#\u0011\t\u0002\u0007\u001f\nTWm\u0019;)\t5!\u0016q\u0015\u0015\u0005\u0019Q\u000b9\u000b"
)
public class VectorIndexer extends Estimator implements VectorIndexerParams, DefaultParamsWritable {
   private final String uid;
   private Param handleInvalid;
   private IntParam maxCategories;
   private Param outputCol;
   private Param inputCol;

   public static VectorIndexer load(final String path) {
      return VectorIndexer$.MODULE$.load(path);
   }

   public static MLReader read() {
      return VectorIndexer$.MODULE$.read();
   }

   public MLWriter write() {
      return DefaultParamsWritable.write$(this);
   }

   public void save(final String path) throws IOException {
      MLWritable.save$(this, path);
   }

   public int getMaxCategories() {
      return VectorIndexerParams.getMaxCategories$(this);
   }

   public final String getHandleInvalid() {
      return HasHandleInvalid.getHandleInvalid$(this);
   }

   public final String getOutputCol() {
      return HasOutputCol.getOutputCol$(this);
   }

   public final String getInputCol() {
      return HasInputCol.getInputCol$(this);
   }

   public Param handleInvalid() {
      return this.handleInvalid;
   }

   public IntParam maxCategories() {
      return this.maxCategories;
   }

   public void org$apache$spark$ml$feature$VectorIndexerParams$_setter_$handleInvalid_$eq(final Param x$1) {
      this.handleInvalid = x$1;
   }

   public void org$apache$spark$ml$feature$VectorIndexerParams$_setter_$maxCategories_$eq(final IntParam x$1) {
      this.maxCategories = x$1;
   }

   public void org$apache$spark$ml$param$shared$HasHandleInvalid$_setter_$handleInvalid_$eq(final Param x$1) {
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

   public String uid() {
      return this.uid;
   }

   public VectorIndexer setMaxCategories(final int value) {
      return (VectorIndexer)this.set(this.maxCategories(), BoxesRunTime.boxToInteger(value));
   }

   public VectorIndexer setInputCol(final String value) {
      return (VectorIndexer)this.set(this.inputCol(), value);
   }

   public VectorIndexer setOutputCol(final String value) {
      return (VectorIndexer)this.set(this.outputCol(), value);
   }

   public VectorIndexer setHandleInvalid(final String value) {
      return (VectorIndexer)this.set(this.handleInvalid(), value);
   }

   public VectorIndexerModel fit(final Dataset dataset) {
      this.transformSchema(dataset.schema(), true);
      int numFeatures = DatasetUtils$.MODULE$.getNumFeatures(dataset, (String)this.$(this.inputCol()));
      RDD vectorDataset = dataset.select((String)this.$(this.inputCol()), .MODULE$).rdd().map((x0$1) -> {
         if (x0$1 != null) {
            Some var3 = org.apache.spark.sql.Row..MODULE$.unapplySeq(x0$1);
            if (!var3.isEmpty() && var3.get() != null && ((SeqOps)var3.get()).lengthCompare(1) == 0) {
               Object v = ((SeqOps)var3.get()).apply(0);
               if (v instanceof Vector) {
                  Vector var5 = (Vector)v;
                  return var5;
               }
            }
         }

         throw new MatchError(x0$1);
      }, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      int maxCats = BoxesRunTime.unboxToInt(this.$(this.maxCategories()));
      RDD qual$1 = vectorDataset.mapPartitions((iter) -> {
         CategoryStats localCatStats = new CategoryStats(numFeatures, maxCats);
         iter.foreach((v) -> {
            $anonfun$fit$3(localCatStats, v);
            return BoxedUnit.UNIT;
         });
         return scala.package..MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new CategoryStats[]{localCatStats}));
      }, vectorDataset.mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(CategoryStats.class));
      Function2 x$1 = (stats1, stats2) -> stats1.merge(stats2);
      int x$2 = qual$1.treeReduce$default$2();
      CategoryStats categoryStats = (CategoryStats)qual$1.treeReduce(x$1, x$2);
      VectorIndexerModel model = (VectorIndexerModel)(new VectorIndexerModel(this.uid(), numFeatures, categoryStats.getCategoryMaps())).setParent(this);
      return (VectorIndexerModel)this.copyValues(model, this.copyValues$default$2());
   }

   public StructType transformSchema(final StructType schema) {
      VectorUDT dataType = new VectorUDT();
      scala.Predef..MODULE$.require(this.isDefined(this.inputCol()), () -> "VectorIndexer requires input column parameter: " + this.inputCol());
      scala.Predef..MODULE$.require(this.isDefined(this.outputCol()), () -> "VectorIndexer requires output column parameter: " + this.outputCol());
      SchemaUtils$.MODULE$.checkColumnType(schema, (String)this.$(this.inputCol()), dataType, SchemaUtils$.MODULE$.checkColumnType$default$4());
      return SchemaUtils$.MODULE$.appendColumn(schema, (String)this.$(this.outputCol()), dataType, SchemaUtils$.MODULE$.appendColumn$default$4());
   }

   public VectorIndexer copy(final ParamMap extra) {
      return (VectorIndexer)this.defaultCopy(extra);
   }

   // $FF: synthetic method
   public static final void $anonfun$fit$3(final CategoryStats localCatStats$1, final Vector v) {
      localCatStats$1.addVector(v);
   }

   public VectorIndexer(final String uid) {
      this.uid = uid;
      HasInputCol.$init$(this);
      HasOutputCol.$init$(this);
      HasHandleInvalid.$init$(this);
      VectorIndexerParams.$init$(this);
      MLWritable.$init$(this);
      DefaultParamsWritable.$init$(this);
      Statics.releaseFence();
   }

   public VectorIndexer() {
      this(Identifiable$.MODULE$.randomUID("vecIdx"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class CategoryStats implements Serializable {
      private final int numFeatures;
      private final int maxCategories;
      private final OpenHashSet[] featureValueSets;

      private int numFeatures() {
         return this.numFeatures;
      }

      private int maxCategories() {
         return this.maxCategories;
      }

      private OpenHashSet[] featureValueSets() {
         return this.featureValueSets;
      }

      public CategoryStats merge(final CategoryStats other) {
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zip$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.featureValueSets()), scala.Predef..MODULE$.wrapRefArray((Object[])other.featureValueSets()))), (x0$1) -> {
            $anonfun$merge$1(this, x0$1);
            return BoxedUnit.UNIT;
         });
         return this;
      }

      public void addVector(final Vector v) {
         scala.Predef..MODULE$.require(v.size() == this.numFeatures(), () -> {
            int var10000 = this.numFeatures();
            return "VectorIndexer expected " + var10000 + " features but found vector of size " + v.size() + ".";
         });
         if (v instanceof DenseVector var4) {
            this.addDenseVector(var4);
            BoxedUnit var6 = BoxedUnit.UNIT;
         } else if (v instanceof SparseVector var5) {
            this.addSparseVector(var5);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(v);
         }
      }

      public Map getCategoryMaps() {
         return scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps((Object[])this.featureValueSets()))), (x$1) -> BoxesRunTime.boxToBoolean($anonfun$getCategoryMaps$1(this, x$1)))), (x0$1) -> {
            if (x0$1 != null) {
               OpenHashSet featureValues = (OpenHashSet)x0$1._1();
               int featureIndex = x0$1._2$mcI$sp();
               if (featureValues != null && true) {
                  double[] sortedFeatureValues = (double[])scala.collection.ArrayOps..MODULE$.sorted$extension(scala.Predef..MODULE$.doubleArrayOps((double[])featureValues.iterator().filter((JFunction1.mcZD.sp)(x$2) -> x$2 != (double)0.0F).toArray(scala.reflect.ClassTag..MODULE$.Double())), scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$);
                  boolean zeroExists = sortedFeatureValues.length + 1 == featureValues.size();
                  if (zeroExists) {
                     sortedFeatureValues = (double[])scala.collection.ArrayOps..MODULE$.$plus$colon$extension(scala.Predef..MODULE$.doubleArrayOps(sortedFeatureValues), BoxesRunTime.boxToDouble((double)0.0F), scala.reflect.ClassTag..MODULE$.Double());
                  }

                  Map categoryMap = org.apache.spark.util.collection.Utils..MODULE$.toMapWithIndex(scala.Predef..MODULE$.wrapDoubleArray(sortedFeatureValues));
                  return new Tuple2(BoxesRunTime.boxToInteger(featureIndex), categoryMap);
               }
            }

            throw new MatchError(x0$1);
         }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class))).toMap(scala..less.colon.less..MODULE$.refl());
      }

      private void addDenseVector(final DenseVector dv) {
         int i = 0;

         for(int size = dv.size(); i < size; ++i) {
            if (this.featureValueSets()[i].size() <= this.maxCategories()) {
               this.featureValueSets()[i].add$mcD$sp(dv.apply(i));
            }
         }

      }

      private void addSparseVector(final SparseVector sv) {
         int vecIndex = 0;
         int k = 0;

         for(int size = sv.size(); vecIndex < size; ++vecIndex) {
            double var10000;
            if (k < sv.indices().length && vecIndex == sv.indices()[k]) {
               ++k;
               var10000 = sv.values()[k - 1];
            } else {
               var10000 = (double)0.0F;
            }

            double featureValue = var10000;
            if (this.featureValueSets()[vecIndex].size() <= this.maxCategories()) {
               this.featureValueSets()[vecIndex].add$mcD$sp(featureValue);
            }
         }

      }

      // $FF: synthetic method
      public static final void $anonfun$merge$1(final CategoryStats $this, final Tuple2 x0$1) {
         if (x0$1 != null) {
            OpenHashSet thisValSet = (OpenHashSet)x0$1._1();
            OpenHashSet otherValSet = (OpenHashSet)x0$1._2();
            otherValSet.iterator().foreach((JFunction1.mcVD.sp)(x) -> {
               if (thisValSet.size() <= $this.maxCategories()) {
                  thisValSet.add$mcD$sp(x);
               }
            });
            BoxedUnit var10000 = BoxedUnit.UNIT;
         } else {
            throw new MatchError(x0$1);
         }
      }

      // $FF: synthetic method
      public static final boolean $anonfun$getCategoryMaps$1(final CategoryStats $this, final Tuple2 x$1) {
         return ((OpenHashSet)x$1._1()).size() <= $this.maxCategories();
      }

      public CategoryStats(final int numFeatures, final int maxCategories) {
         this.numFeatures = numFeatures;
         this.maxCategories = maxCategories;
         this.featureValueSets = (OpenHashSet[])scala.Array..MODULE$.fill(numFeatures, () -> new OpenHashSet.mcD.sp(scala.reflect.ClassTag..MODULE$.Double()), scala.reflect.ClassTag..MODULE$.apply(OpenHashSet.class));
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
