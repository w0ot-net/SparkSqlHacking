package org.glassfish.jersey.message;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.ext.MessageBodyReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import org.glassfish.jersey.message.internal.MessageBodyFactory;

public final class ReaderModel extends AbstractEntityProviderModel {
   public ReaderModel(MessageBodyReader provider, List types, Boolean custom) {
      super(provider, types, custom, MessageBodyReader.class);
   }

   public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
      return MessageBodyFactory.isReadable((MessageBodyReader)super.provider(), type, genericType, annotations, mediaType);
   }
}
