package org.sparkproject.dmg.pmml.baseline;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field BASELINEMODEL_MODELNAME = ReflectionUtil.getField(BaselineModel.class, "modelName");
   Field BASELINEMODEL_MININGFUNCTION = ReflectionUtil.getField(BaselineModel.class, "miningFunction");
   Field BASELINEMODEL_ALGORITHMNAME = ReflectionUtil.getField(BaselineModel.class, "algorithmName");
   Field BASELINEMODEL_SCORABLE = ReflectionUtil.getField(BaselineModel.class, "scorable");
   Field BASELINEMODEL_MATHCONTEXT = ReflectionUtil.getField(BaselineModel.class, "mathContext");
   Field COUNTTABLE_SAMPLE = ReflectionUtil.getField(CountTable.class, "sample");
   Field FIELDVALUE_FIELD = ReflectionUtil.getField(FieldValue.class, "field");
   Field FIELDVALUE_VALUE = ReflectionUtil.getField(FieldValue.class, "value");
   Field FIELDVALUECOUNT_FIELD = ReflectionUtil.getField(FieldValueCount.class, "field");
   Field FIELDVALUECOUNT_VALUE = ReflectionUtil.getField(FieldValueCount.class, "value");
   Field FIELDVALUECOUNT_COUNT = ReflectionUtil.getField(FieldValueCount.class, "count");
   Field TESTDISTRIBUTIONS_FIELD = ReflectionUtil.getField(TestDistributions.class, "field");
   Field TESTDISTRIBUTIONS_TESTSTATISTIC = ReflectionUtil.getField(TestDistributions.class, "testStatistic");
   Field TESTDISTRIBUTIONS_RESETVALUE = ReflectionUtil.getField(TestDistributions.class, "resetValue");
   Field TESTDISTRIBUTIONS_WINDOWSIZE = ReflectionUtil.getField(TestDistributions.class, "windowSize");
   Field TESTDISTRIBUTIONS_WEIGHTFIELD = ReflectionUtil.getField(TestDistributions.class, "weightField");
   Field TESTDISTRIBUTIONS_NORMALIZATIONSCHEME = ReflectionUtil.getField(TestDistributions.class, "normalizationScheme");
}
