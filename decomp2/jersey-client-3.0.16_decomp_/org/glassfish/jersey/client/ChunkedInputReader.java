package org.glassfish.jersey.client;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.ws.rs.ConstrainedTo;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import org.glassfish.jersey.internal.PropertiesDelegate;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.message.internal.ReaderInterceptorExecutor;

@ConstrainedTo(RuntimeType.CLIENT)
class ChunkedInputReader implements MessageBodyReader {
   private final Provider messageBodyWorkers;
   private final Provider propertiesDelegateProvider;

   @Inject
   public ChunkedInputReader(@Context Provider messageBodyWorkers, @Context Provider propertiesDelegateProvider) {
      this.messageBodyWorkers = messageBodyWorkers;
      this.propertiesDelegateProvider = propertiesDelegateProvider;
   }

   public boolean isReadable(Class aClass, Type type, Annotation[] annotations, MediaType mediaType) {
      return aClass.equals(ChunkedInput.class);
   }

   public ChunkedInput readFrom(Class chunkedInputClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap headers, InputStream inputStream) throws IOException, WebApplicationException {
      Type chunkType = ReflectionHelper.getTypeArgument(type, 0);
      return new ChunkedInput(chunkType, ReaderInterceptorExecutor.closeableInputStream(inputStream), annotations, mediaType, headers, (MessageBodyWorkers)this.messageBodyWorkers.get(), (PropertiesDelegate)this.propertiesDelegateProvider.get());
   }
}
