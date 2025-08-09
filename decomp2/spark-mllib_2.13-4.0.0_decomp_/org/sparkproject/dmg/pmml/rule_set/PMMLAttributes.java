package org.sparkproject.dmg.pmml.rule_set;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field RULESELECTIONMETHOD_CRITERION = ReflectionUtil.getField(RuleSelectionMethod.class, "criterion");
   Field RULESET_RECORDCOUNT = ReflectionUtil.getField(RuleSet.class, "recordCount");
   Field RULESET_NBCORRECT = ReflectionUtil.getField(RuleSet.class, "nbCorrect");
   Field RULESET_DEFAULTSCORE = ReflectionUtil.getField(RuleSet.class, "defaultScore");
   Field RULESET_DEFAULTCONFIDENCE = ReflectionUtil.getField(RuleSet.class, "defaultConfidence");
   Field RULESETMODEL_MODELNAME = ReflectionUtil.getField(RuleSetModel.class, "modelName");
   Field RULESETMODEL_MININGFUNCTION = ReflectionUtil.getField(RuleSetModel.class, "miningFunction");
   Field RULESETMODEL_ALGORITHMNAME = ReflectionUtil.getField(RuleSetModel.class, "algorithmName");
   Field RULESETMODEL_SCORABLE = ReflectionUtil.getField(RuleSetModel.class, "scorable");
   Field RULESETMODEL_MATHCONTEXT = ReflectionUtil.getField(RuleSetModel.class, "mathContext");
   Field SIMPLERULE_ID = ReflectionUtil.getField(SimpleRule.class, "id");
   Field SIMPLERULE_SCORE = ReflectionUtil.getField(SimpleRule.class, "score");
   Field SIMPLERULE_RECORDCOUNT = ReflectionUtil.getField(SimpleRule.class, "recordCount");
   Field SIMPLERULE_NBCORRECT = ReflectionUtil.getField(SimpleRule.class, "nbCorrect");
   Field SIMPLERULE_CONFIDENCE = ReflectionUtil.getField(SimpleRule.class, "confidence");
   Field SIMPLERULE_WEIGHT = ReflectionUtil.getField(SimpleRule.class, "weight");
}
