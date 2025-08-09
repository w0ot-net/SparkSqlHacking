package org.sparkproject.dmg.pmml.clustering;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field CLUSTER_ID = ReflectionUtil.getField(Cluster.class, "id");
   Field CLUSTER_NAME = ReflectionUtil.getField(Cluster.class, "name");
   Field CLUSTER_SIZE = ReflectionUtil.getField(Cluster.class, "size");
   Field CLUSTERINGFIELD_FIELD = ReflectionUtil.getField(ClusteringField.class, "field");
   Field CLUSTERINGFIELD_CENTERFIELD = ReflectionUtil.getField(ClusteringField.class, "centerField");
   Field CLUSTERINGFIELD_FIELDWEIGHT = ReflectionUtil.getField(ClusteringField.class, "fieldWeight");
   Field CLUSTERINGFIELD_SIMILARITYSCALE = ReflectionUtil.getField(ClusteringField.class, "similarityScale");
   Field CLUSTERINGFIELD_COMPAREFUNCTION = ReflectionUtil.getField(ClusteringField.class, "compareFunction");
   Field CLUSTERINGMODEL_MODELNAME = ReflectionUtil.getField(ClusteringModel.class, "modelName");
   Field CLUSTERINGMODEL_MININGFUNCTION = ReflectionUtil.getField(ClusteringModel.class, "miningFunction");
   Field CLUSTERINGMODEL_ALGORITHMNAME = ReflectionUtil.getField(ClusteringModel.class, "algorithmName");
   Field CLUSTERINGMODEL_MODELCLASS = ReflectionUtil.getField(ClusteringModel.class, "modelClass");
   Field CLUSTERINGMODEL_NUMBEROFCLUSTERS = ReflectionUtil.getField(ClusteringModel.class, "numberOfClusters");
   Field CLUSTERINGMODEL_SCORABLE = ReflectionUtil.getField(ClusteringModel.class, "scorable");
   Field CLUSTERINGMODEL_MATHCONTEXT = ReflectionUtil.getField(ClusteringModel.class, "mathContext");
   Field KOHONENMAP_COORD1 = ReflectionUtil.getField(KohonenMap.class, "coord1");
   Field KOHONENMAP_COORD2 = ReflectionUtil.getField(KohonenMap.class, "coord2");
   Field KOHONENMAP_COORD3 = ReflectionUtil.getField(KohonenMap.class, "coord3");
}
