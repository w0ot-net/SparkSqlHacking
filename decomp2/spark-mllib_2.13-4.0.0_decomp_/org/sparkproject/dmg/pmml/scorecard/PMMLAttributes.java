package org.sparkproject.dmg.pmml.scorecard;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field ATTRIBUTE_REASONCODE = ReflectionUtil.getField(Attribute.class, "reasonCode");
   Field ATTRIBUTE_PARTIALSCORE = ReflectionUtil.getField(Attribute.class, "partialScore");
   Field CHARACTERISTIC_NAME = ReflectionUtil.getField(Characteristic.class, "name");
   Field CHARACTERISTIC_REASONCODE = ReflectionUtil.getField(Characteristic.class, "reasonCode");
   Field CHARACTERISTIC_BASELINESCORE = ReflectionUtil.getField(Characteristic.class, "baselineScore");
   Field SCORECARD_MODELNAME = ReflectionUtil.getField(Scorecard.class, "modelName");
   Field SCORECARD_MININGFUNCTION = ReflectionUtil.getField(Scorecard.class, "miningFunction");
   Field SCORECARD_ALGORITHMNAME = ReflectionUtil.getField(Scorecard.class, "algorithmName");
   Field SCORECARD_INITIALSCORE = ReflectionUtil.getField(Scorecard.class, "initialScore");
   Field SCORECARD_USEREASONCODES = ReflectionUtil.getField(Scorecard.class, "useReasonCodes");
   Field SCORECARD_REASONCODEALGORITHM = ReflectionUtil.getField(Scorecard.class, "reasonCodeAlgorithm");
   Field SCORECARD_BASELINESCORE = ReflectionUtil.getField(Scorecard.class, "baselineScore");
   Field SCORECARD_BASELINEMETHOD = ReflectionUtil.getField(Scorecard.class, "baselineMethod");
   Field SCORECARD_SCORABLE = ReflectionUtil.getField(Scorecard.class, "scorable");
   Field SCORECARD_MATHCONTEXT = ReflectionUtil.getField(Scorecard.class, "mathContext");
}
