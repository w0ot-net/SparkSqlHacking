package jakarta.ws.rs.ext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface ParamConverterProvider {
   ParamConverter getConverter(Class var1, Type var2, Annotation[] var3);
}
