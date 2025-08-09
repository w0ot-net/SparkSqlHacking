package jakarta.ws.rs.ext;

import jakarta.ws.rs.core.MediaType;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface Providers {
   MessageBodyReader getMessageBodyReader(Class var1, Type var2, Annotation[] var3, MediaType var4);

   MessageBodyWriter getMessageBodyWriter(Class var1, Type var2, Annotation[] var3, MediaType var4);

   ExceptionMapper getExceptionMapper(Class var1);

   ContextResolver getContextResolver(Class var1, MediaType var2);
}
