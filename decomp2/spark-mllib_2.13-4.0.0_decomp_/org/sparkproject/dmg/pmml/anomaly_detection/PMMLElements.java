package org.sparkproject.dmg.pmml.anomaly_detection;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field ANOMALYDETECTIONMODEL_EXTENSIONS = ReflectionUtil.getField(AnomalyDetectionModel.class, "extensions");
   Field ANOMALYDETECTIONMODEL_MININGSCHEMA = ReflectionUtil.getField(AnomalyDetectionModel.class, "miningSchema");
   Field ANOMALYDETECTIONMODEL_OUTPUT = ReflectionUtil.getField(AnomalyDetectionModel.class, "output");
   Field ANOMALYDETECTIONMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(AnomalyDetectionModel.class, "localTransformations");
   Field ANOMALYDETECTIONMODEL_MODELVERIFICATION = ReflectionUtil.getField(AnomalyDetectionModel.class, "modelVerification");
   Field ANOMALYDETECTIONMODEL_MODEL = ReflectionUtil.getField(AnomalyDetectionModel.class, "model");
   Field ANOMALYDETECTIONMODEL_MEANCLUSTERDISTANCES = ReflectionUtil.getField(AnomalyDetectionModel.class, "meanClusterDistances");
   Field MEANCLUSTERDISTANCES_EXTENSIONS = ReflectionUtil.getField(MeanClusterDistances.class, "extensions");
   Field MEANCLUSTERDISTANCES_ARRAY = ReflectionUtil.getField(MeanClusterDistances.class, "array");
}
