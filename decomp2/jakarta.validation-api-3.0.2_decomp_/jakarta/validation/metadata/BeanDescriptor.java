package jakarta.validation.metadata;

import java.util.Set;

public interface BeanDescriptor extends ElementDescriptor {
   boolean isBeanConstrained();

   PropertyDescriptor getConstraintsForProperty(String var1);

   Set getConstrainedProperties();

   MethodDescriptor getConstraintsForMethod(String var1, Class... var2);

   Set getConstrainedMethods(MethodType var1, MethodType... var2);

   ConstructorDescriptor getConstraintsForConstructor(Class... var1);

   Set getConstrainedConstructors();
}
