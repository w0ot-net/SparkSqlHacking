package org.sparkproject.dmg.pmml.scorecard;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field ATTRIBUTE_EXTENSIONS = ReflectionUtil.getField(Attribute.class, "extensions");
   Field ATTRIBUTE_PREDICATE = ReflectionUtil.getField(Attribute.class, "predicate");
   Field ATTRIBUTE_COMPLEXPARTIALSCORE = ReflectionUtil.getField(Attribute.class, "complexPartialScore");
   Field CHARACTERISTIC_EXTENSIONS = ReflectionUtil.getField(Characteristic.class, "extensions");
   Field CHARACTERISTIC_ATTRIBUTES = ReflectionUtil.getField(Characteristic.class, "attributes");
   Field CHARACTERISTICS_EXTENSIONS = ReflectionUtil.getField(Characteristics.class, "extensions");
   Field CHARACTERISTICS_CHARACTERISTICS = ReflectionUtil.getField(Characteristics.class, "characteristics");
   Field COMPLEXPARTIALSCORE_EXTENSIONS = ReflectionUtil.getField(ComplexPartialScore.class, "extensions");
   Field COMPLEXPARTIALSCORE_EXPRESSION = ReflectionUtil.getField(ComplexPartialScore.class, "expression");
   Field SCORECARD_EXTENSIONS = ReflectionUtil.getField(Scorecard.class, "extensions");
   Field SCORECARD_MININGSCHEMA = ReflectionUtil.getField(Scorecard.class, "miningSchema");
   Field SCORECARD_OUTPUT = ReflectionUtil.getField(Scorecard.class, "output");
   Field SCORECARD_MODELSTATS = ReflectionUtil.getField(Scorecard.class, "modelStats");
   Field SCORECARD_MODELEXPLANATION = ReflectionUtil.getField(Scorecard.class, "modelExplanation");
   Field SCORECARD_TARGETS = ReflectionUtil.getField(Scorecard.class, "targets");
   Field SCORECARD_LOCALTRANSFORMATIONS = ReflectionUtil.getField(Scorecard.class, "localTransformations");
   Field SCORECARD_CHARACTERISTICS = ReflectionUtil.getField(Scorecard.class, "characteristics");
   Field SCORECARD_MODELVERIFICATION = ReflectionUtil.getField(Scorecard.class, "modelVerification");
}
