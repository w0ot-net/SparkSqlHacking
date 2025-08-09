package jakarta.ws.rs.ext;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public interface MessageBodyReader {
   boolean isReadable(Class var1, Type var2, Annotation[] var3, MediaType var4);

   Object readFrom(Class var1, Type var2, Annotation[] var3, MediaType var4, MultivaluedMap var5, InputStream var6) throws IOException, WebApplicationException;
}
