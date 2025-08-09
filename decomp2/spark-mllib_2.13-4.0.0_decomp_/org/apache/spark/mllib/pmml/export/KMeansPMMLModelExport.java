package org.apache.spark.mllib.pmml.export;

import java.lang.invoke.SerializedLambda;
import java.time.format.DateTimeFormatter;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.sparkproject.dmg.pmml.Array;
import org.sparkproject.dmg.pmml.CompareFunction;
import org.sparkproject.dmg.pmml.ComparisonMeasure;
import org.sparkproject.dmg.pmml.DataDictionary;
import org.sparkproject.dmg.pmml.DataField;
import org.sparkproject.dmg.pmml.DataType;
import org.sparkproject.dmg.pmml.MiningField;
import org.sparkproject.dmg.pmml.MiningFunction;
import org.sparkproject.dmg.pmml.MiningSchema;
import org.sparkproject.dmg.pmml.OpType;
import org.sparkproject.dmg.pmml.PMML;
import org.sparkproject.dmg.pmml.SquaredEuclidean;
import org.sparkproject.dmg.pmml.clustering.Cluster;
import org.sparkproject.dmg.pmml.clustering.ClusteringField;
import org.sparkproject.dmg.pmml.clustering.ClusteringModel;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u000592Q\u0001B\u0003\u0001\u0013EA\u0001\u0002\b\u0001\u0003\u0002\u0003\u0006IA\b\u0005\u0006I\u0001!\t!\n\u0005\u0006Q\u0001!I!\u000b\u0002\u0016\u00176+\u0017M\\:Q\u001b6cUj\u001c3fY\u0016C\bo\u001c:u\u0015\t1q!\u0001\u0004fqB|'\u000f\u001e\u0006\u0003\u0011%\tA\u0001]7nY*\u0011!bC\u0001\u0006[2d\u0017N\u0019\u0006\u0003\u00195\tQa\u001d9be.T!AD\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0001\u0012aA8sON\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\tI\"$D\u0001\u0006\u0013\tYRAA\bQ\u001b6cUj\u001c3fY\u0016C\bo\u001c:u\u0003\u0015iw\u000eZ3m\u0007\u0001\u0001\"a\b\u0012\u000e\u0003\u0001R!!I\u0005\u0002\u0015\rdWo\u001d;fe&tw-\u0003\u0002$A\tY1*T3b]Nlu\u000eZ3m\u0003\u0019a\u0014N\\5u}Q\u0011ae\n\t\u00033\u0001AQ\u0001\b\u0002A\u0002y\t!\u0003]8qk2\fG/Z&NK\u0006t7\u000fU'N\u0019R\u0011!&\f\t\u0003'-J!\u0001\f\u000b\u0003\tUs\u0017\u000e\u001e\u0005\u00069\r\u0001\rA\b"
)
public class KMeansPMMLModelExport implements PMMLModelExport {
   private DateTimeFormatter org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER;
   private PMML pmml;

   public PMML getPmml() {
      return PMMLModelExport.getPmml$(this);
   }

   public DateTimeFormatter org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER() {
      return this.org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER;
   }

   public PMML pmml() {
      return this.pmml;
   }

   public final void org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER_$eq(final DateTimeFormatter x$1) {
      this.org$apache$spark$mllib$pmml$export$PMMLModelExport$$DATE_TIME_FORMATTER = x$1;
   }

   public void org$apache$spark$mllib$pmml$export$PMMLModelExport$_setter_$pmml_$eq(final PMML x$1) {
      this.pmml = x$1;
   }

   private void populateKMeansPMML(final KMeansModel model) {
      this.pmml().getHeader().setDescription("k-means clustering");
      if (model.clusterCenters().length > 0) {
         Vector clusterCenter = model.clusterCenters()[0];
         String[] fields = new String[clusterCenter.size()];
         DataDictionary dataDictionary = new DataDictionary();
         MiningSchema miningSchema = new MiningSchema();
         ComparisonMeasure comparisonMeasure = (new ComparisonMeasure()).setKind(ComparisonMeasure.Kind.DISTANCE).setMeasure(new SquaredEuclidean());
         ClusteringModel clusteringModel = (new ClusteringModel()).setModelName("k-means").setMiningSchema(miningSchema).setComparisonMeasure(comparisonMeasure).setMiningFunction(MiningFunction.CLUSTERING).setModelClass(ClusteringModel.ModelClass.CENTER_BASED).setNumberOfClusters(.MODULE$.int2Integer(model.clusterCenters().length));
         scala.runtime.RichInt..MODULE$.until$extension(.MODULE$.intWrapper(0), clusterCenter.size()).foreach((i) -> $anonfun$populateKMeansPMML$1(fields, dataDictionary, miningSchema, clusteringModel, BoxesRunTime.unboxToInt(i)));
         dataDictionary.setNumberOfFields(.MODULE$.int2Integer(dataDictionary.getDataFields().size()));
         scala.collection.ArrayOps..MODULE$.indices$extension(.MODULE$.refArrayOps(model.clusterCenters())).foreach((i) -> $anonfun$populateKMeansPMML$2(clusterCenter, model, clusteringModel, BoxesRunTime.unboxToInt(i)));
         this.pmml().setDataDictionary(dataDictionary);
         this.pmml().addModels(clusteringModel);
      }
   }

   // $FF: synthetic method
   public static final ClusteringModel $anonfun$populateKMeansPMML$1(final String[] fields$1, final DataDictionary dataDictionary$1, final MiningSchema miningSchema$1, final ClusteringModel clusteringModel$1, final int i) {
      fields$1[i] = "field_" + i;
      dataDictionary$1.addDataFields(new DataField(fields$1[i], OpType.CONTINUOUS, DataType.DOUBLE));
      miningSchema$1.addMiningFields((new MiningField(fields$1[i])).setUsageType(MiningField.UsageType.ACTIVE));
      return clusteringModel$1.addClusteringFields((new ClusteringField(fields$1[i])).setCompareFunction(CompareFunction.ABS_DIFF));
   }

   // $FF: synthetic method
   public static final ClusteringModel $anonfun$populateKMeansPMML$2(final Vector clusterCenter$1, final KMeansModel model$1, final ClusteringModel clusteringModel$1, final int i) {
      Cluster cluster = (new Cluster()).setName("cluster_" + i).setArray((new Array()).setType(Array.Type.REAL).setN(.MODULE$.int2Integer(clusterCenter$1.size())).setValue(.MODULE$.wrapDoubleArray(model$1.clusterCenters()[i].toArray()).mkString(" ")));
      return clusteringModel$1.addClusters(cluster);
   }

   public KMeansPMMLModelExport(final KMeansModel model) {
      PMMLModelExport.$init$(this);
      this.populateKMeansPMML(model);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
