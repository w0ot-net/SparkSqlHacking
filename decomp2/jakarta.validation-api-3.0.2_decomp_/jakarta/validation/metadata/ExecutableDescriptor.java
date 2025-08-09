package jakarta.validation.metadata;

import java.util.List;
import java.util.Set;

public interface ExecutableDescriptor extends ElementDescriptor {
   String getName();

   List getParameterDescriptors();

   CrossParameterDescriptor getCrossParameterDescriptor();

   ReturnValueDescriptor getReturnValueDescriptor();

   boolean hasConstrainedParameters();

   boolean hasConstrainedReturnValue();

   boolean hasConstraints();

   Set getConstraintDescriptors();

   ElementDescriptor.ConstraintFinder findConstraints();
}
