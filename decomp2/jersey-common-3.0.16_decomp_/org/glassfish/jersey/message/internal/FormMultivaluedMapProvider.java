package org.glassfish.jersey.message.internal;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.glassfish.jersey.internal.util.collection.NullableMultivaluedHashMap;

@Produces({"application/x-www-form-urlencoded"})
@Consumes({"application/x-www-form-urlencoded"})
@Singleton
public final class FormMultivaluedMapProvider extends AbstractFormProvider {
   private final Type mapType;

   public FormMultivaluedMapProvider() {
      ParameterizedType iface = (ParameterizedType)this.getClass().getGenericSuperclass();
      this.mapType = iface.getActualTypeArguments()[0];
   }

   public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
      return type == MultivaluedMap.class && (type == genericType || this.mapType.equals(genericType));
   }

   public MultivaluedMap readFrom(Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, InputStream entityStream) throws IOException {
      return this.readFrom(new NullableMultivaluedHashMap(), mediaType, true, entityStream);
   }

   public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
      return MultivaluedMap.class.isAssignableFrom(type);
   }

   public void writeTo(MultivaluedMap t, Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, OutputStream entityStream) throws IOException {
      this.writeTo(t, mediaType, entityStream);
   }
}
