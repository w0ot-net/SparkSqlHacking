package org.sparkproject.dmg.pmml.nearest_neighbor;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field INSTANCEFIELD_EXTENSIONS = ReflectionUtil.getField(InstanceField.class, "extensions");
   Field INSTANCEFIELDS_EXTENSIONS = ReflectionUtil.getField(InstanceFields.class, "extensions");
   Field INSTANCEFIELDS_INSTANCEFIELDS = ReflectionUtil.getField(InstanceFields.class, "instanceFields");
   Field KNNINPUT_EXTENSIONS = ReflectionUtil.getField(KNNInput.class, "extensions");
   Field KNNINPUTS_EXTENSIONS = ReflectionUtil.getField(KNNInputs.class, "extensions");
   Field KNNINPUTS_KNNINPUTS = ReflectionUtil.getField(KNNInputs.class, "knnInputs");
   Field NEARESTNEIGHBORMODEL_EXTENSIONS = ReflectionUtil.getField(NearestNeighborModel.class, "extensions");
   Field NEARESTNEIGHBORMODEL_MININGSCHEMA = ReflectionUtil.getField(NearestNeighborModel.class, "miningSchema");
   Field NEARESTNEIGHBORMODEL_OUTPUT = ReflectionUtil.getField(NearestNeighborModel.class, "output");
   Field NEARESTNEIGHBORMODEL_MODELSTATS = ReflectionUtil.getField(NearestNeighborModel.class, "modelStats");
   Field NEARESTNEIGHBORMODEL_MODELEXPLANATION = ReflectionUtil.getField(NearestNeighborModel.class, "modelExplanation");
   Field NEARESTNEIGHBORMODEL_TARGETS = ReflectionUtil.getField(NearestNeighborModel.class, "targets");
   Field NEARESTNEIGHBORMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(NearestNeighborModel.class, "localTransformations");
   Field NEARESTNEIGHBORMODEL_TRAININGINSTANCES = ReflectionUtil.getField(NearestNeighborModel.class, "trainingInstances");
   Field NEARESTNEIGHBORMODEL_COMPARISONMEASURE = ReflectionUtil.getField(NearestNeighborModel.class, "comparisonMeasure");
   Field NEARESTNEIGHBORMODEL_KNNINPUTS = ReflectionUtil.getField(NearestNeighborModel.class, "knnInputs");
   Field NEARESTNEIGHBORMODEL_MODELVERIFICATION = ReflectionUtil.getField(NearestNeighborModel.class, "modelVerification");
   Field TRAININGINSTANCES_EXTENSIONS = ReflectionUtil.getField(TrainingInstances.class, "extensions");
   Field TRAININGINSTANCES_INSTANCEFIELDS = ReflectionUtil.getField(TrainingInstances.class, "instanceFields");
   Field TRAININGINSTANCES_TABLELOCATOR = ReflectionUtil.getField(TrainingInstances.class, "tableLocator");
   Field TRAININGINSTANCES_INLINETABLE = ReflectionUtil.getField(TrainingInstances.class, "inlineTable");
}
