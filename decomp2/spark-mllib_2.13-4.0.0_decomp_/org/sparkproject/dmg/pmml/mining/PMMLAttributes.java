package org.sparkproject.dmg.pmml.mining;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field MININGMODEL_MODELNAME = ReflectionUtil.getField(MiningModel.class, "modelName");
   Field MININGMODEL_MININGFUNCTION = ReflectionUtil.getField(MiningModel.class, "miningFunction");
   Field MININGMODEL_ALGORITHMNAME = ReflectionUtil.getField(MiningModel.class, "algorithmName");
   Field MININGMODEL_SCORABLE = ReflectionUtil.getField(MiningModel.class, "scorable");
   Field MININGMODEL_MATHCONTEXT = ReflectionUtil.getField(MiningModel.class, "mathContext");
   Field SEGMENT_ID = ReflectionUtil.getField(Segment.class, "id");
   Field SEGMENT_WEIGHT = ReflectionUtil.getField(Segment.class, "weight");
   Field SEGMENTATION_MULTIPLEMODELMETHOD = ReflectionUtil.getField(Segmentation.class, "multipleModelMethod");
   Field SEGMENTATION_MISSINGPREDICTIONTREATMENT = ReflectionUtil.getField(Segmentation.class, "missingPredictionTreatment");
   Field SEGMENTATION_MISSINGTHRESHOLD = ReflectionUtil.getField(Segmentation.class, "missingThreshold");
   Field VARIABLEWEIGHT_FIELD = ReflectionUtil.getField(VariableWeight.class, "field");
}
