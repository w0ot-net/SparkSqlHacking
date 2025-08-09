package jakarta.validation;

import jakarta.validation.executable.ExecutableValidator;
import jakarta.validation.metadata.BeanDescriptor;
import java.util.Set;

public interface Validator {
   Set validate(Object var1, Class... var2);

   Set validateProperty(Object var1, String var2, Class... var3);

   Set validateValue(Class var1, String var2, Object var3, Class... var4);

   BeanDescriptor getConstraintsForClass(Class var1);

   Object unwrap(Class var1);

   ExecutableValidator forExecutables();
}
