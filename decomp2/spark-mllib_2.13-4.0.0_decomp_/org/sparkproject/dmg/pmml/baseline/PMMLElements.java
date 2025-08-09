package org.sparkproject.dmg.pmml.baseline;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field ALTERNATE_CONTINUOUSDISTRIBUTION = ReflectionUtil.getField(Alternate.class, "continuousDistribution");
   Field BASELINE_CONTINUOUSDISTRIBUTION = ReflectionUtil.getField(Baseline.class, "continuousDistribution");
   Field BASELINE_COUNTTABLE = ReflectionUtil.getField(Baseline.class, "countTable");
   Field BASELINE_NORMALIZEDCOUNTTABLE = ReflectionUtil.getField(Baseline.class, "normalizedCountTable");
   Field BASELINE_FIELDREFS = ReflectionUtil.getField(Baseline.class, "fieldRefs");
   Field BASELINEMODEL_EXTENSIONS = ReflectionUtil.getField(BaselineModel.class, "extensions");
   Field BASELINEMODEL_MININGSCHEMA = ReflectionUtil.getField(BaselineModel.class, "miningSchema");
   Field BASELINEMODEL_OUTPUT = ReflectionUtil.getField(BaselineModel.class, "output");
   Field BASELINEMODEL_MODELSTATS = ReflectionUtil.getField(BaselineModel.class, "modelStats");
   Field BASELINEMODEL_MODELEXPLANATION = ReflectionUtil.getField(BaselineModel.class, "modelExplanation");
   Field BASELINEMODEL_TARGETS = ReflectionUtil.getField(BaselineModel.class, "targets");
   Field BASELINEMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(BaselineModel.class, "localTransformations");
   Field BASELINEMODEL_TESTDISTRIBUTIONS = ReflectionUtil.getField(BaselineModel.class, "testDistributions");
   Field BASELINEMODEL_MODELVERIFICATION = ReflectionUtil.getField(BaselineModel.class, "modelVerification");
   Field COUNTTABLE_EXTENSIONS = ReflectionUtil.getField(CountTable.class, "extensions");
   Field COUNTTABLE_FIELDVALUES = ReflectionUtil.getField(CountTable.class, "fieldValues");
   Field COUNTTABLE_FIELDVALUECOUNTS = ReflectionUtil.getField(CountTable.class, "fieldValueCounts");
   Field FIELDVALUE_EXTENSIONS = ReflectionUtil.getField(FieldValue.class, "extensions");
   Field FIELDVALUE_FIELDVALUES = ReflectionUtil.getField(FieldValue.class, "fieldValues");
   Field FIELDVALUE_FIELDVALUECOUNTS = ReflectionUtil.getField(FieldValue.class, "fieldValueCounts");
   Field FIELDVALUECOUNT_EXTENSIONS = ReflectionUtil.getField(FieldValueCount.class, "extensions");
   Field TESTDISTRIBUTIONS_EXTENSIONS = ReflectionUtil.getField(TestDistributions.class, "extensions");
   Field TESTDISTRIBUTIONS_BASELINE = ReflectionUtil.getField(TestDistributions.class, "baseline");
   Field TESTDISTRIBUTIONS_ALTERNATE = ReflectionUtil.getField(TestDistributions.class, "alternate");
}
