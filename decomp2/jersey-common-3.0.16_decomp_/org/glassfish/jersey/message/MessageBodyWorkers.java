package org.glassfish.jersey.message;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.MessageBodyWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import org.glassfish.jersey.internal.PropertiesDelegate;

public interface MessageBodyWorkers {
   Map getReaders(MediaType var1);

   Map getWriters(MediaType var1);

   String readersToString(Map var1);

   String writersToString(Map var1);

   MessageBodyReader getMessageBodyReader(Class var1, Type var2, Annotation[] var3, MediaType var4);

   MessageBodyReader getMessageBodyReader(Class var1, Type var2, Annotation[] var3, MediaType var4, PropertiesDelegate var5);

   MessageBodyWriter getMessageBodyWriter(Class var1, Type var2, Annotation[] var3, MediaType var4);

   MessageBodyWriter getMessageBodyWriter(Class var1, Type var2, Annotation[] var3, MediaType var4, PropertiesDelegate var5);

   List getMessageBodyReaderMediaTypes(Class var1, Type var2, Annotation[] var3);

   List getMessageBodyReaderMediaTypesByType(Class var1);

   List getMessageBodyReadersForType(Class var1);

   List getReaderModelsForType(Class var1);

   List getMessageBodyWriterMediaTypes(Class var1, Type var2, Annotation[] var3);

   List getMessageBodyWriterMediaTypesByType(Class var1);

   List getMessageBodyWritersForType(Class var1);

   List getWritersModelsForType(Class var1);

   MediaType getMessageBodyWriterMediaType(Class var1, Type var2, Annotation[] var3, List var4);

   Object readFrom(Class var1, Type var2, Annotation[] var3, MediaType var4, MultivaluedMap var5, PropertiesDelegate var6, InputStream var7, Iterable var8, boolean var9) throws WebApplicationException, IOException;

   OutputStream writeTo(Object var1, Class var2, Type var3, Annotation[] var4, MediaType var5, MultivaluedMap var6, PropertiesDelegate var7, OutputStream var8, Iterable var9) throws IOException, WebApplicationException;
}
