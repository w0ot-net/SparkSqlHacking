package org.apache.hadoop.hive.ql.udf.generic;

import java.util.Arrays;
import org.apache.hadoop.hive.ql.exec.DefaultUDAFEvaluatorResolver;
import org.apache.hadoop.hive.ql.exec.HiveFunctionRegistryUtils;
import org.apache.hadoop.hive.ql.exec.SparkDefaultUDAFEvaluatorResolver;
import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluatorResolver;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.parse.SemanticException;
import org.apache.hadoop.hive.serde2.typeinfo.TypeInfo;

public class SparkGenericUDAFBridge extends GenericUDAFBridge {
   public SparkGenericUDAFBridge(UDAF udaf) {
      super(udaf);
   }

   public GenericUDAFEvaluator getEvaluator(TypeInfo[] parameters) throws SemanticException {
      UDAFEvaluatorResolver resolver = this.udaf.getResolver();
      if (resolver instanceof DefaultUDAFEvaluatorResolver) {
         resolver = new SparkDefaultUDAFEvaluatorResolver((DefaultUDAFEvaluatorResolver)resolver);
      }

      Class<? extends UDAFEvaluator> udafEvaluatorClass = resolver.getEvaluatorClass(Arrays.asList(parameters));
      return new SparkGenericUDAFBridgeEvaluator(udafEvaluatorClass);
   }

   public static class SparkGenericUDAFBridgeEvaluator extends GenericUDAFBridge.GenericUDAFBridgeEvaluator {
      private static final long serialVersionUID = 1L;

      public SparkGenericUDAFBridgeEvaluator() {
      }

      public SparkGenericUDAFBridgeEvaluator(Class udafEvaluator) {
         super(udafEvaluator);
      }

      public void iterate(GenericUDAFEvaluator.AggregationBuffer agg, Object[] parameters) throws HiveException {
         HiveFunctionRegistryUtils.invoke(this.iterateMethod, ((GenericUDAFBridge.GenericUDAFBridgeEvaluator.UDAFAgg)agg).ueObject, this.conversionHelper.convertIfNecessary(parameters));
      }

      public void merge(GenericUDAFEvaluator.AggregationBuffer agg, Object partial) throws HiveException {
         HiveFunctionRegistryUtils.invoke(this.mergeMethod, ((GenericUDAFBridge.GenericUDAFBridgeEvaluator.UDAFAgg)agg).ueObject, this.conversionHelper.convertIfNecessary(new Object[]{partial}));
      }

      public Object terminate(GenericUDAFEvaluator.AggregationBuffer agg) throws HiveException {
         return HiveFunctionRegistryUtils.invoke(this.terminateMethod, ((GenericUDAFBridge.GenericUDAFBridgeEvaluator.UDAFAgg)agg).ueObject);
      }

      public Object terminatePartial(GenericUDAFEvaluator.AggregationBuffer agg) throws HiveException {
         return HiveFunctionRegistryUtils.invoke(this.terminatePartialMethod, ((GenericUDAFBridge.GenericUDAFBridgeEvaluator.UDAFAgg)agg).ueObject);
      }
   }
}
