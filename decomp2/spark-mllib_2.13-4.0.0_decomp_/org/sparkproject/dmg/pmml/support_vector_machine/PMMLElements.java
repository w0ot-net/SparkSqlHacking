package org.sparkproject.dmg.pmml.support_vector_machine;

import java.lang.reflect.Field;
import org.sparkproject.jpmml.model.ReflectionUtil;

public interface PMMLElements {
   Field COEFFICIENT_EXTENSIONS = ReflectionUtil.getField(Coefficient.class, "extensions");
   Field COEFFICIENTS_EXTENSIONS = ReflectionUtil.getField(Coefficients.class, "extensions");
   Field COEFFICIENTS_COEFFICIENTS = ReflectionUtil.getField(Coefficients.class, "coefficients");
   Field LINEARKERNEL_EXTENSIONS = ReflectionUtil.getField(LinearKernel.class, "extensions");
   Field POLYNOMIALKERNEL_EXTENSIONS = ReflectionUtil.getField(PolynomialKernel.class, "extensions");
   Field RADIALBASISKERNEL_EXTENSIONS = ReflectionUtil.getField(RadialBasisKernel.class, "extensions");
   Field SIGMOIDKERNEL_EXTENSIONS = ReflectionUtil.getField(SigmoidKernel.class, "extensions");
   Field SUPPORTVECTOR_EXTENSIONS = ReflectionUtil.getField(SupportVector.class, "extensions");
   Field SUPPORTVECTORMACHINE_EXTENSIONS = ReflectionUtil.getField(SupportVectorMachine.class, "extensions");
   Field SUPPORTVECTORMACHINE_SUPPORTVECTORS = ReflectionUtil.getField(SupportVectorMachine.class, "supportVectors");
   Field SUPPORTVECTORMACHINE_COEFFICIENTS = ReflectionUtil.getField(SupportVectorMachine.class, "coefficients");
   Field SUPPORTVECTORMACHINEMODEL_EXTENSIONS = ReflectionUtil.getField(SupportVectorMachineModel.class, "extensions");
   Field SUPPORTVECTORMACHINEMODEL_MININGSCHEMA = ReflectionUtil.getField(SupportVectorMachineModel.class, "miningSchema");
   Field SUPPORTVECTORMACHINEMODEL_OUTPUT = ReflectionUtil.getField(SupportVectorMachineModel.class, "output");
   Field SUPPORTVECTORMACHINEMODEL_MODELSTATS = ReflectionUtil.getField(SupportVectorMachineModel.class, "modelStats");
   Field SUPPORTVECTORMACHINEMODEL_MODELEXPLANATION = ReflectionUtil.getField(SupportVectorMachineModel.class, "modelExplanation");
   Field SUPPORTVECTORMACHINEMODEL_TARGETS = ReflectionUtil.getField(SupportVectorMachineModel.class, "targets");
   Field SUPPORTVECTORMACHINEMODEL_LOCALTRANSFORMATIONS = ReflectionUtil.getField(SupportVectorMachineModel.class, "localTransformations");
   Field SUPPORTVECTORMACHINEMODEL_KERNEL = ReflectionUtil.getField(SupportVectorMachineModel.class, "kernel");
   Field SUPPORTVECTORMACHINEMODEL_VECTORDICTIONARY = ReflectionUtil.getField(SupportVectorMachineModel.class, "vectorDictionary");
   Field SUPPORTVECTORMACHINEMODEL_SUPPORTVECTORMACHINES = ReflectionUtil.getField(SupportVectorMachineModel.class, "supportVectorMachines");
   Field SUPPORTVECTORMACHINEMODEL_MODELVERIFICATION = ReflectionUtil.getField(SupportVectorMachineModel.class, "modelVerification");
   Field SUPPORTVECTORS_EXTENSIONS = ReflectionUtil.getField(SupportVectors.class, "extensions");
   Field SUPPORTVECTORS_SUPPORTVECTORS = ReflectionUtil.getField(SupportVectors.class, "supportVectors");
   Field VECTORDICTIONARY_EXTENSIONS = ReflectionUtil.getField(VectorDictionary.class, "extensions");
   Field VECTORDICTIONARY_VECTORFIELDS = ReflectionUtil.getField(VectorDictionary.class, "vectorFields");
   Field VECTORDICTIONARY_VECTORINSTANCES = ReflectionUtil.getField(VectorDictionary.class, "vectorInstances");
   Field VECTORFIELDS_EXTENSIONS = ReflectionUtil.getField(VectorFields.class, "extensions");
   Field VECTORFIELDS_CONTENT = ReflectionUtil.getField(VectorFields.class, "content");
   Field VECTORINSTANCE_EXTENSIONS = ReflectionUtil.getField(VectorInstance.class, "extensions");
   Field VECTORINSTANCE_REALSPARSEARRAY = ReflectionUtil.getField(VectorInstance.class, "realSparseArray");
   Field VECTORINSTANCE_ARRAY = ReflectionUtil.getField(VectorInstance.class, "array");
}
