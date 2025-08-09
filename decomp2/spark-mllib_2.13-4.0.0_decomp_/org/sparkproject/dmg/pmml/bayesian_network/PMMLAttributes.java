package org.sparkproject.dmg.pmml.bayesian_network;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field BAYESIANNETWORKMODEL_MODELNAME = ReflectionUtil.getField(BayesianNetworkModel.class, "modelName");
   Field BAYESIANNETWORKMODEL_MININGFUNCTION = ReflectionUtil.getField(BayesianNetworkModel.class, "miningFunction");
   Field BAYESIANNETWORKMODEL_ALGORITHMNAME = ReflectionUtil.getField(BayesianNetworkModel.class, "algorithmName");
   Field BAYESIANNETWORKMODEL_MODELTYPE = ReflectionUtil.getField(BayesianNetworkModel.class, "modelType");
   Field BAYESIANNETWORKMODEL_INFERENCEMETHOD = ReflectionUtil.getField(BayesianNetworkModel.class, "inferenceMethod");
   Field BAYESIANNETWORKMODEL_SCORABLE = ReflectionUtil.getField(BayesianNetworkModel.class, "scorable");
   Field BAYESIANNETWORKMODEL_MATHCONTEXT = ReflectionUtil.getField(BayesianNetworkModel.class, "mathContext");
   Field CONTINUOUSCONDITIONALPROBABILITY_COUNT = ReflectionUtil.getField(ContinuousConditionalProbability.class, "count");
   Field CONTINUOUSNODE_NAME = ReflectionUtil.getField(ContinuousNode.class, "name");
   Field CONTINUOUSNODE_COUNT = ReflectionUtil.getField(ContinuousNode.class, "count");
   Field DISCRETECONDITIONALPROBABILITY_COUNT = ReflectionUtil.getField(DiscreteConditionalProbability.class, "count");
   Field DISCRETENODE_NAME = ReflectionUtil.getField(DiscreteNode.class, "name");
   Field DISCRETENODE_COUNT = ReflectionUtil.getField(DiscreteNode.class, "count");
   Field PARENTVALUE_PARENT = ReflectionUtil.getField(ParentValue.class, "parent");
   Field PARENTVALUE_VALUE = ReflectionUtil.getField(ParentValue.class, "value");
   Field VALUEPROBABILITY_VALUE = ReflectionUtil.getField(ValueProbability.class, "value");
   Field VALUEPROBABILITY_PROBABILITY = ReflectionUtil.getField(ValueProbability.class, "probability");
}
