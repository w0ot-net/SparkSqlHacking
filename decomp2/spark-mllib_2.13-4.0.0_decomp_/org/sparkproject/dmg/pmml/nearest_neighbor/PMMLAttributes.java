package org.sparkproject.dmg.pmml.nearest_neighbor;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field INSTANCEFIELD_FIELD = ReflectionUtil.getField(InstanceField.class, "field");
   Field INSTANCEFIELD_COLUMN = ReflectionUtil.getField(InstanceField.class, "column");
   Field KNNINPUT_FIELD = ReflectionUtil.getField(KNNInput.class, "field");
   Field KNNINPUT_FIELDWEIGHT = ReflectionUtil.getField(KNNInput.class, "fieldWeight");
   Field KNNINPUT_COMPAREFUNCTION = ReflectionUtil.getField(KNNInput.class, "compareFunction");
   Field NEARESTNEIGHBORMODEL_MODELNAME = ReflectionUtil.getField(NearestNeighborModel.class, "modelName");
   Field NEARESTNEIGHBORMODEL_MININGFUNCTION = ReflectionUtil.getField(NearestNeighborModel.class, "miningFunction");
   Field NEARESTNEIGHBORMODEL_ALGORITHMNAME = ReflectionUtil.getField(NearestNeighborModel.class, "algorithmName");
   Field NEARESTNEIGHBORMODEL_NUMBEROFNEIGHBORS = ReflectionUtil.getField(NearestNeighborModel.class, "numberOfNeighbors");
   Field NEARESTNEIGHBORMODEL_CONTINUOUSSCORINGMETHOD = ReflectionUtil.getField(NearestNeighborModel.class, "continuousScoringMethod");
   Field NEARESTNEIGHBORMODEL_CATEGORICALSCORINGMETHOD = ReflectionUtil.getField(NearestNeighborModel.class, "categoricalScoringMethod");
   Field NEARESTNEIGHBORMODEL_INSTANCEIDVARIABLE = ReflectionUtil.getField(NearestNeighborModel.class, "instanceIdVariable");
   Field NEARESTNEIGHBORMODEL_THRESHOLD = ReflectionUtil.getField(NearestNeighborModel.class, "threshold");
   Field NEARESTNEIGHBORMODEL_SCORABLE = ReflectionUtil.getField(NearestNeighborModel.class, "scorable");
   Field NEARESTNEIGHBORMODEL_MATHCONTEXT = ReflectionUtil.getField(NearestNeighborModel.class, "mathContext");
   Field TRAININGINSTANCES_TRANSFORMED = ReflectionUtil.getField(TrainingInstances.class, "transformed");
   Field TRAININGINSTANCES_RECORDCOUNT = ReflectionUtil.getField(TrainingInstances.class, "recordCount");
   Field TRAININGINSTANCES_FIELDCOUNT = ReflectionUtil.getField(TrainingInstances.class, "fieldCount");
}
