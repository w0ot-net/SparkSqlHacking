package org.sparkproject.dmg.pmml.mining;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field MININGMODEL_EXTENSIONS = ReflectionUtil.getField(MiningModel.class, "extensions");
   Field MININGMODEL_MININGSCHEMA = ReflectionUtil.getField(MiningModel.class, "miningSchema");
   Field MININGMODEL_OUTPUT = ReflectionUtil.getField(MiningModel.class, "output");
   Field MININGMODEL_MODELSTATS = ReflectionUtil.getField(MiningModel.class, "modelStats");
   Field MININGMODEL_MODELEXPLANATION = ReflectionUtil.getField(MiningModel.class, "modelExplanation");
   Field MININGMODEL_TARGETS = ReflectionUtil.getField(MiningModel.class, "targets");
   Field MININGMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(MiningModel.class, "localTransformations");
   Field MININGMODEL_EMBEDDEDMODELS = ReflectionUtil.getField(MiningModel.class, "embeddedModels");
   Field MININGMODEL_SEGMENTATION = ReflectionUtil.getField(MiningModel.class, "segmentation");
   Field MININGMODEL_MODELVERIFICATION = ReflectionUtil.getField(MiningModel.class, "modelVerification");
   Field SEGMENT_EXTENSIONS = ReflectionUtil.getField(Segment.class, "extensions");
   Field SEGMENT_PREDICATE = ReflectionUtil.getField(Segment.class, "predicate");
   Field SEGMENT_MODEL = ReflectionUtil.getField(Segment.class, "model");
   Field SEGMENT_VARIABLEWEIGHT = ReflectionUtil.getField(Segment.class, "variableWeight");
   Field SEGMENTATION_EXTENSIONS = ReflectionUtil.getField(Segmentation.class, "extensions");
   Field SEGMENTATION_LOCALTRANSFORMATIONS = ReflectionUtil.getField(Segmentation.class, "localTransformations");
   Field SEGMENTATION_SEGMENTS = ReflectionUtil.getField(Segmentation.class, "segments");
   Field VARIABLEWEIGHT_EXTENSIONS = ReflectionUtil.getField(VariableWeight.class, "extensions");
}
