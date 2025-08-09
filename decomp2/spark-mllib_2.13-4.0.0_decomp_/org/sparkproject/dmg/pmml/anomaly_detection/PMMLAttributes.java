package org.sparkproject.dmg.pmml.anomaly_detection;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field ANOMALYDETECTIONMODEL_MODELNAME = ReflectionUtil.getField(AnomalyDetectionModel.class, "modelName");
   Field ANOMALYDETECTIONMODEL_ALGORITHMNAME = ReflectionUtil.getField(AnomalyDetectionModel.class, "algorithmName");
   Field ANOMALYDETECTIONMODEL_MININGFUNCTION = ReflectionUtil.getField(AnomalyDetectionModel.class, "miningFunction");
   Field ANOMALYDETECTIONMODEL_ALGORITHMTYPE = ReflectionUtil.getField(AnomalyDetectionModel.class, "algorithmType");
   Field ANOMALYDETECTIONMODEL_SAMPLEDATASIZE = ReflectionUtil.getField(AnomalyDetectionModel.class, "sampleDataSize");
   Field ANOMALYDETECTIONMODEL_SCORABLE = ReflectionUtil.getField(AnomalyDetectionModel.class, "scorable");
   Field ANOMALYDETECTIONMODEL_MATHCONTEXT = ReflectionUtil.getField(AnomalyDetectionModel.class, "mathContext");
}
