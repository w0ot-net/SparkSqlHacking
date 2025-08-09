package org.sparkproject.dmg.pmml.naive_bayes;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field BAYESINPUT_EXTENSIONS = ReflectionUtil.getField(BayesInput.class, "extensions");
   Field BAYESINPUT_TARGETVALUESTATS = ReflectionUtil.getField(BayesInput.class, "targetValueStats");
   Field BAYESINPUT_DERIVEDFIELD = ReflectionUtil.getField(BayesInput.class, "derivedField");
   Field BAYESINPUT_PAIRCOUNTS = ReflectionUtil.getField(BayesInput.class, "pairCounts");
   Field BAYESINPUTS_EXTENSIONS = ReflectionUtil.getField(BayesInputs.class, "extensions");
   Field BAYESINPUTS_BAYESINPUTS = ReflectionUtil.getField(BayesInputs.class, "bayesInputs");
   Field BAYESOUTPUT_EXTENSIONS = ReflectionUtil.getField(BayesOutput.class, "extensions");
   Field BAYESOUTPUT_TARGETVALUECOUNTS = ReflectionUtil.getField(BayesOutput.class, "targetValueCounts");
   Field NAIVEBAYESMODEL_EXTENSIONS = ReflectionUtil.getField(NaiveBayesModel.class, "extensions");
   Field NAIVEBAYESMODEL_MININGSCHEMA = ReflectionUtil.getField(NaiveBayesModel.class, "miningSchema");
   Field NAIVEBAYESMODEL_OUTPUT = ReflectionUtil.getField(NaiveBayesModel.class, "output");
   Field NAIVEBAYESMODEL_MODELSTATS = ReflectionUtil.getField(NaiveBayesModel.class, "modelStats");
   Field NAIVEBAYESMODEL_MODELEXPLANATION = ReflectionUtil.getField(NaiveBayesModel.class, "modelExplanation");
   Field NAIVEBAYESMODEL_TARGETS = ReflectionUtil.getField(NaiveBayesModel.class, "targets");
   Field NAIVEBAYESMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(NaiveBayesModel.class, "localTransformations");
   Field NAIVEBAYESMODEL_BAYESINPUTS = ReflectionUtil.getField(NaiveBayesModel.class, "bayesInputs");
   Field NAIVEBAYESMODEL_BAYESOUTPUT = ReflectionUtil.getField(NaiveBayesModel.class, "bayesOutput");
   Field NAIVEBAYESMODEL_MODELVERIFICATION = ReflectionUtil.getField(NaiveBayesModel.class, "modelVerification");
   Field PAIRCOUNTS_EXTENSIONS = ReflectionUtil.getField(PairCounts.class, "extensions");
   Field PAIRCOUNTS_TARGETVALUECOUNTS = ReflectionUtil.getField(PairCounts.class, "targetValueCounts");
   Field TARGETVALUECOUNT_EXTENSIONS = ReflectionUtil.getField(TargetValueCount.class, "extensions");
   Field TARGETVALUECOUNTS_EXTENSIONS = ReflectionUtil.getField(TargetValueCounts.class, "extensions");
   Field TARGETVALUECOUNTS_TARGETVALUECOUNTS = ReflectionUtil.getField(TargetValueCounts.class, "targetValueCounts");
   Field TARGETVALUESTAT_EXTENSIONS = ReflectionUtil.getField(TargetValueStat.class, "extensions");
   Field TARGETVALUESTAT_CONTINUOUSDISTRIBUTION = ReflectionUtil.getField(TargetValueStat.class, "continuousDistribution");
   Field TARGETVALUESTATS_EXTENSIONS = ReflectionUtil.getField(TargetValueStats.class, "extensions");
   Field TARGETVALUESTATS_TARGETVALUESTATS = ReflectionUtil.getField(TargetValueStats.class, "targetValueStats");
}
