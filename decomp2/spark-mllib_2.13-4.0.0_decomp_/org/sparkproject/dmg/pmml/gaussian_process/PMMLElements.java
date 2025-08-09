package org.sparkproject.dmg.pmml.gaussian_process;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field ABSOLUTEEXPONENTIALKERNEL_EXTENSIONS = ReflectionUtil.getField(AbsoluteExponentialKernel.class, "extensions");
   Field ABSOLUTEEXPONENTIALKERNEL_LAMBDAS = ReflectionUtil.getField(AbsoluteExponentialKernel.class, "lambdas");
   Field ARDSQUAREDEXPONENTIALKERNEL_EXTENSIONS = ReflectionUtil.getField(ARDSquaredExponentialKernel.class, "extensions");
   Field ARDSQUAREDEXPONENTIALKERNEL_LAMBDAS = ReflectionUtil.getField(ARDSquaredExponentialKernel.class, "lambdas");
   Field GAUSSIANPROCESSMODEL_EXTENSIONS = ReflectionUtil.getField(GaussianProcessModel.class, "extensions");
   Field GAUSSIANPROCESSMODEL_MININGSCHEMA = ReflectionUtil.getField(GaussianProcessModel.class, "miningSchema");
   Field GAUSSIANPROCESSMODEL_OUTPUT = ReflectionUtil.getField(GaussianProcessModel.class, "output");
   Field GAUSSIANPROCESSMODEL_MODELSTATS = ReflectionUtil.getField(GaussianProcessModel.class, "modelStats");
   Field GAUSSIANPROCESSMODEL_MODELEXPLANATION = ReflectionUtil.getField(GaussianProcessModel.class, "modelExplanation");
   Field GAUSSIANPROCESSMODEL_TARGETS = ReflectionUtil.getField(GaussianProcessModel.class, "targets");
   Field GAUSSIANPROCESSMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(GaussianProcessModel.class, "localTransformations");
   Field GAUSSIANPROCESSMODEL_RADIALBASISKERNEL = ReflectionUtil.getField(GaussianProcessModel.class, "radialBasisKernel");
   Field GAUSSIANPROCESSMODEL_ARDSQUAREDEXPONENTIALKERNEL = ReflectionUtil.getField(GaussianProcessModel.class, "ardSquaredExponentialKernel");
   Field GAUSSIANPROCESSMODEL_ABSOLUTEEXPONENTIALKERNEL = ReflectionUtil.getField(GaussianProcessModel.class, "absoluteExponentialKernel");
   Field GAUSSIANPROCESSMODEL_GENERALIZEDEXPONENTIALKERNEL = ReflectionUtil.getField(GaussianProcessModel.class, "generalizedExponentialKernel");
   Field GAUSSIANPROCESSMODEL_TRAININGINSTANCES = ReflectionUtil.getField(GaussianProcessModel.class, "trainingInstances");
   Field GAUSSIANPROCESSMODEL_MODELVERIFICATION = ReflectionUtil.getField(GaussianProcessModel.class, "modelVerification");
   Field GENERALIZEDEXPONENTIALKERNEL_EXTENSIONS = ReflectionUtil.getField(GeneralizedExponentialKernel.class, "extensions");
   Field GENERALIZEDEXPONENTIALKERNEL_LAMBDAS = ReflectionUtil.getField(GeneralizedExponentialKernel.class, "lambdas");
   Field LAMBDA_EXTENSIONS = ReflectionUtil.getField(Lambda.class, "extensions");
   Field LAMBDA_ARRAY = ReflectionUtil.getField(Lambda.class, "array");
   Field RADIALBASISKERNEL_EXTENSIONS = ReflectionUtil.getField(RadialBasisKernel.class, "extensions");
}
