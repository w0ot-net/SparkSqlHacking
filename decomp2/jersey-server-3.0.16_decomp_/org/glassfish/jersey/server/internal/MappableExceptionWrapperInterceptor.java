package org.glassfish.jersey.server.internal;

import jakarta.annotation.Priority;
import jakarta.inject.Singleton;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.ReaderInterceptorContext;
import jakarta.ws.rs.ext.WriterInterceptor;
import jakarta.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.ClassBinding;
import org.glassfish.jersey.message.internal.MessageBodyProviderNotFoundException;
import org.glassfish.jersey.server.internal.process.MappableException;

@Priority(10)
@Singleton
public class MappableExceptionWrapperInterceptor implements ReaderInterceptor, WriterInterceptor {
   public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
      try {
         return context.proceed();
      } catch (MappableException | MessageBodyProviderNotFoundException | WebApplicationException e) {
         throw e;
      } catch (Exception e) {
         throw new MappableException(e);
      }
   }

   public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
      try {
         context.proceed();
      } catch (MappableException | WebApplicationException e) {
         throw e;
      } catch (MessageBodyProviderNotFoundException nfe) {
         throw new InternalServerErrorException(nfe);
      } catch (Exception e) {
         throw new MappableException(e);
      }
   }

   public static class Binder extends AbstractBinder {
      protected void configure() {
         ((ClassBinding)((ClassBinding)this.bind(MappableExceptionWrapperInterceptor.class).to(ReaderInterceptor.class)).to(WriterInterceptor.class)).in(Singleton.class);
      }
   }
}
