package org.sparkproject.dmg.pmml.naive_bayes;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field BAYESINPUT_FIELD = ReflectionUtil.getField(BayesInput.class, "field");
   Field BAYESOUTPUT_TARGETFIELD = ReflectionUtil.getField(BayesOutput.class, "targetField");
   Field NAIVEBAYESMODEL_MODELNAME = ReflectionUtil.getField(NaiveBayesModel.class, "modelName");
   Field NAIVEBAYESMODEL_THRESHOLD = ReflectionUtil.getField(NaiveBayesModel.class, "threshold");
   Field NAIVEBAYESMODEL_MININGFUNCTION = ReflectionUtil.getField(NaiveBayesModel.class, "miningFunction");
   Field NAIVEBAYESMODEL_ALGORITHMNAME = ReflectionUtil.getField(NaiveBayesModel.class, "algorithmName");
   Field NAIVEBAYESMODEL_SCORABLE = ReflectionUtil.getField(NaiveBayesModel.class, "scorable");
   Field NAIVEBAYESMODEL_MATHCONTEXT = ReflectionUtil.getField(NaiveBayesModel.class, "mathContext");
   Field PAIRCOUNTS_VALUE = ReflectionUtil.getField(PairCounts.class, "value");
   Field TARGETVALUECOUNT_VALUE = ReflectionUtil.getField(TargetValueCount.class, "value");
   Field TARGETVALUECOUNT_COUNT = ReflectionUtil.getField(TargetValueCount.class, "count");
   Field TARGETVALUESTAT_VALUE = ReflectionUtil.getField(TargetValueStat.class, "value");
}
