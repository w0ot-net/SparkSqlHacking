package jakarta.validation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

public interface ParameterNameProvider {
   List getParameterNames(Constructor var1);

   List getParameterNames(Method var1);
}
