package jakarta.validation.executable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

public interface ExecutableValidator {
   Set validateParameters(Object var1, Method var2, Object[] var3, Class... var4);

   Set validateReturnValue(Object var1, Method var2, Object var3, Class... var4);

   Set validateConstructorParameters(Constructor var1, Object[] var2, Class... var3);

   Set validateConstructorReturnValue(Constructor var1, Object var2, Class... var3);
}
