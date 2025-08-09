package org.sparkproject.dmg.pmml.support_vector_machine;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLAttributes {
   Field COEFFICIENT_VALUE = ReflectionUtil.getField(Coefficient.class, "value");
   Field COEFFICIENTS_NUMBEROFCOEFFICIENTS = ReflectionUtil.getField(Coefficients.class, "numberOfCoefficients");
   Field COEFFICIENTS_ABSOLUTEVALUE = ReflectionUtil.getField(Coefficients.class, "absoluteValue");
   Field LINEARKERNEL_DESCRIPTION = ReflectionUtil.getField(LinearKernel.class, "description");
   Field POLYNOMIALKERNEL_DESCRIPTION = ReflectionUtil.getField(PolynomialKernel.class, "description");
   Field POLYNOMIALKERNEL_GAMMA = ReflectionUtil.getField(PolynomialKernel.class, "gamma");
   Field POLYNOMIALKERNEL_COEF0 = ReflectionUtil.getField(PolynomialKernel.class, "coef0");
   Field POLYNOMIALKERNEL_DEGREE = ReflectionUtil.getField(PolynomialKernel.class, "degree");
   Field RADIALBASISKERNEL_DESCRIPTION = ReflectionUtil.getField(RadialBasisKernel.class, "description");
   Field RADIALBASISKERNEL_GAMMA = ReflectionUtil.getField(RadialBasisKernel.class, "gamma");
   Field SIGMOIDKERNEL_DESCRIPTION = ReflectionUtil.getField(SigmoidKernel.class, "description");
   Field SIGMOIDKERNEL_GAMMA = ReflectionUtil.getField(SigmoidKernel.class, "gamma");
   Field SIGMOIDKERNEL_COEF0 = ReflectionUtil.getField(SigmoidKernel.class, "coef0");
   Field SUPPORTVECTOR_VECTORID = ReflectionUtil.getField(SupportVector.class, "vectorId");
   Field SUPPORTVECTORMACHINE_TARGETCATEGORY = ReflectionUtil.getField(SupportVectorMachine.class, "targetCategory");
   Field SUPPORTVECTORMACHINE_ALTERNATETARGETCATEGORY = ReflectionUtil.getField(SupportVectorMachine.class, "alternateTargetCategory");
   Field SUPPORTVECTORMACHINE_THRESHOLD = ReflectionUtil.getField(SupportVectorMachine.class, "threshold");
   Field SUPPORTVECTORMACHINEMODEL_MODELNAME = ReflectionUtil.getField(SupportVectorMachineModel.class, "modelName");
   Field SUPPORTVECTORMACHINEMODEL_MININGFUNCTION = ReflectionUtil.getField(SupportVectorMachineModel.class, "miningFunction");
   Field SUPPORTVECTORMACHINEMODEL_ALGORITHMNAME = ReflectionUtil.getField(SupportVectorMachineModel.class, "algorithmName");
   Field SUPPORTVECTORMACHINEMODEL_THRESHOLD = ReflectionUtil.getField(SupportVectorMachineModel.class, "threshold");
   Field SUPPORTVECTORMACHINEMODEL_REPRESENTATION = ReflectionUtil.getField(SupportVectorMachineModel.class, "representation");
   Field SUPPORTVECTORMACHINEMODEL_ALTERNATEBINARYTARGETCATEGORY = ReflectionUtil.getField(SupportVectorMachineModel.class, "alternateBinaryTargetCategory");
   Field SUPPORTVECTORMACHINEMODEL_CLASSIFICATIONMETHOD = ReflectionUtil.getField(SupportVectorMachineModel.class, "classificationMethod");
   Field SUPPORTVECTORMACHINEMODEL_MAXWINS = ReflectionUtil.getField(SupportVectorMachineModel.class, "maxWins");
   Field SUPPORTVECTORMACHINEMODEL_SCORABLE = ReflectionUtil.getField(SupportVectorMachineModel.class, "scorable");
   Field SUPPORTVECTORMACHINEMODEL_MATHCONTEXT = ReflectionUtil.getField(SupportVectorMachineModel.class, "mathContext");
   Field SUPPORTVECTORS_NUMBEROFSUPPORTVECTORS = ReflectionUtil.getField(SupportVectors.class, "numberOfSupportVectors");
   Field SUPPORTVECTORS_NUMBEROFATTRIBUTES = ReflectionUtil.getField(SupportVectors.class, "numberOfAttributes");
   Field VECTORDICTIONARY_NUMBEROFVECTORS = ReflectionUtil.getField(VectorDictionary.class, "numberOfVectors");
   Field VECTORFIELDS_NUMBEROFFIELDS = ReflectionUtil.getField(VectorFields.class, "numberOfFields");
   Field VECTORINSTANCE_ID = ReflectionUtil.getField(VectorInstance.class, "id");
}
