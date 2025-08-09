package org.sparkproject.dmg.pmml.gaussian_process;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field ABSOLUTEEXPONENTIALKERNEL_DESCRIPTION = ReflectionUtil.getField(AbsoluteExponentialKernel.class, "description");
   Field ABSOLUTEEXPONENTIALKERNEL_GAMMA = ReflectionUtil.getField(AbsoluteExponentialKernel.class, "gamma");
   Field ABSOLUTEEXPONENTIALKERNEL_NOISEVARIANCE = ReflectionUtil.getField(AbsoluteExponentialKernel.class, "noiseVariance");
   Field ARDSQUAREDEXPONENTIALKERNEL_DESCRIPTION = ReflectionUtil.getField(ARDSquaredExponentialKernel.class, "description");
   Field ARDSQUAREDEXPONENTIALKERNEL_GAMMA = ReflectionUtil.getField(ARDSquaredExponentialKernel.class, "gamma");
   Field ARDSQUAREDEXPONENTIALKERNEL_NOISEVARIANCE = ReflectionUtil.getField(ARDSquaredExponentialKernel.class, "noiseVariance");
   Field GAUSSIANPROCESSMODEL_MODELNAME = ReflectionUtil.getField(GaussianProcessModel.class, "modelName");
   Field GAUSSIANPROCESSMODEL_MININGFUNCTION = ReflectionUtil.getField(GaussianProcessModel.class, "miningFunction");
   Field GAUSSIANPROCESSMODEL_ALGORITHMNAME = ReflectionUtil.getField(GaussianProcessModel.class, "algorithmName");
   Field GAUSSIANPROCESSMODEL_OPTIMIZER = ReflectionUtil.getField(GaussianProcessModel.class, "optimizer");
   Field GAUSSIANPROCESSMODEL_SCORABLE = ReflectionUtil.getField(GaussianProcessModel.class, "scorable");
   Field GAUSSIANPROCESSMODEL_MATHCONTEXT = ReflectionUtil.getField(GaussianProcessModel.class, "mathContext");
   Field GENERALIZEDEXPONENTIALKERNEL_DESCRIPTION = ReflectionUtil.getField(GeneralizedExponentialKernel.class, "description");
   Field GENERALIZEDEXPONENTIALKERNEL_GAMMA = ReflectionUtil.getField(GeneralizedExponentialKernel.class, "gamma");
   Field GENERALIZEDEXPONENTIALKERNEL_NOISEVARIANCE = ReflectionUtil.getField(GeneralizedExponentialKernel.class, "noiseVariance");
   Field GENERALIZEDEXPONENTIALKERNEL_DEGREE = ReflectionUtil.getField(GeneralizedExponentialKernel.class, "degree");
   Field RADIALBASISKERNEL_DESCRIPTION = ReflectionUtil.getField(RadialBasisKernel.class, "description");
   Field RADIALBASISKERNEL_GAMMA = ReflectionUtil.getField(RadialBasisKernel.class, "gamma");
   Field RADIALBASISKERNEL_NOISEVARIANCE = ReflectionUtil.getField(RadialBasisKernel.class, "noiseVariance");
   Field RADIALBASISKERNEL_LAMBDA = ReflectionUtil.getField(RadialBasisKernel.class, "lambda");
}
