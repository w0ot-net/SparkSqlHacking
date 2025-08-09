package org.sparkproject.dmg.pmml.clustering;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field CENTERFIELDS_DERIVEDFIELDS = ReflectionUtil.getField(CenterFields.class, "derivedFields");
   Field CLUSTER_EXTENSIONS = ReflectionUtil.getField(Cluster.class, "extensions");
   Field CLUSTER_KOHONENMAP = ReflectionUtil.getField(Cluster.class, "kohonenMap");
   Field CLUSTER_ARRAY = ReflectionUtil.getField(Cluster.class, "array");
   Field CLUSTER_PARTITION = ReflectionUtil.getField(Cluster.class, "partition");
   Field CLUSTER_COVARIANCES = ReflectionUtil.getField(Cluster.class, "covariances");
   Field CLUSTERINGFIELD_EXTENSIONS = ReflectionUtil.getField(ClusteringField.class, "extensions");
   Field CLUSTERINGFIELD_COMPARISONS = ReflectionUtil.getField(ClusteringField.class, "comparisons");
   Field CLUSTERINGMODEL_EXTENSIONS = ReflectionUtil.getField(ClusteringModel.class, "extensions");
   Field CLUSTERINGMODEL_MININGSCHEMA = ReflectionUtil.getField(ClusteringModel.class, "miningSchema");
   Field CLUSTERINGMODEL_OUTPUT = ReflectionUtil.getField(ClusteringModel.class, "output");
   Field CLUSTERINGMODEL_MODELSTATS = ReflectionUtil.getField(ClusteringModel.class, "modelStats");
   Field CLUSTERINGMODEL_MODELEXPLANATION = ReflectionUtil.getField(ClusteringModel.class, "modelExplanation");
   Field CLUSTERINGMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(ClusteringModel.class, "localTransformations");
   Field CLUSTERINGMODEL_COMPARISONMEASURE = ReflectionUtil.getField(ClusteringModel.class, "comparisonMeasure");
   Field CLUSTERINGMODEL_CLUSTERINGFIELDS = ReflectionUtil.getField(ClusteringModel.class, "clusteringFields");
   Field CLUSTERINGMODEL_CENTERFIELDS = ReflectionUtil.getField(ClusteringModel.class, "centerFields");
   Field CLUSTERINGMODEL_MISSINGVALUEWEIGHTS = ReflectionUtil.getField(ClusteringModel.class, "missingValueWeights");
   Field CLUSTERINGMODEL_CLUSTERS = ReflectionUtil.getField(ClusteringModel.class, "clusters");
   Field CLUSTERINGMODEL_MODELVERIFICATION = ReflectionUtil.getField(ClusteringModel.class, "modelVerification");
   Field COMPARISONS_EXTENSIONS = ReflectionUtil.getField(Comparisons.class, "extensions");
   Field COMPARISONS_MATRIX = ReflectionUtil.getField(Comparisons.class, "matrix");
   Field COVARIANCES_EXTENSIONS = ReflectionUtil.getField(Covariances.class, "extensions");
   Field COVARIANCES_MATRIX = ReflectionUtil.getField(Covariances.class, "matrix");
   Field KOHONENMAP_EXTENSIONS = ReflectionUtil.getField(KohonenMap.class, "extensions");
   Field MISSINGVALUEWEIGHTS_EXTENSIONS = ReflectionUtil.getField(MissingValueWeights.class, "extensions");
   Field MISSINGVALUEWEIGHTS_ARRAY = ReflectionUtil.getField(MissingValueWeights.class, "array");
}
