package org.sparkproject.dmg.pmml.rule_set;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field COMPOUNDRULE_EXTENSIONS = ReflectionUtil.getField(CompoundRule.class, "extensions");
   Field COMPOUNDRULE_PREDICATE = ReflectionUtil.getField(CompoundRule.class, "predicate");
   Field COMPOUNDRULE_RULES = ReflectionUtil.getField(CompoundRule.class, "rules");
   Field RULESELECTIONMETHOD_EXTENSIONS = ReflectionUtil.getField(RuleSelectionMethod.class, "extensions");
   Field RULESET_EXTENSIONS = ReflectionUtil.getField(RuleSet.class, "extensions");
   Field RULESET_RULESELECTIONMETHODS = ReflectionUtil.getField(RuleSet.class, "ruleSelectionMethods");
   Field RULESET_SCOREDISTRIBUTIONS = ReflectionUtil.getField(RuleSet.class, "scoreDistributions");
   Field RULESET_RULES = ReflectionUtil.getField(RuleSet.class, "rules");
   Field RULESETMODEL_EXTENSIONS = ReflectionUtil.getField(RuleSetModel.class, "extensions");
   Field RULESETMODEL_MININGSCHEMA = ReflectionUtil.getField(RuleSetModel.class, "miningSchema");
   Field RULESETMODEL_OUTPUT = ReflectionUtil.getField(RuleSetModel.class, "output");
   Field RULESETMODEL_MODELSTATS = ReflectionUtil.getField(RuleSetModel.class, "modelStats");
   Field RULESETMODEL_MODELEXPLANATION = ReflectionUtil.getField(RuleSetModel.class, "modelExplanation");
   Field RULESETMODEL_TARGETS = ReflectionUtil.getField(RuleSetModel.class, "targets");
   Field RULESETMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(RuleSetModel.class, "localTransformations");
   Field RULESETMODEL_RULESET = ReflectionUtil.getField(RuleSetModel.class, "ruleSet");
   Field RULESETMODEL_MODELVERIFICATION = ReflectionUtil.getField(RuleSetModel.class, "modelVerification");
   Field SIMPLERULE_EXTENSIONS = ReflectionUtil.getField(SimpleRule.class, "extensions");
   Field SIMPLERULE_PREDICATE = ReflectionUtil.getField(SimpleRule.class, "predicate");
   Field SIMPLERULE_SCOREDISTRIBUTIONS = ReflectionUtil.getField(SimpleRule.class, "scoreDistributions");
}
