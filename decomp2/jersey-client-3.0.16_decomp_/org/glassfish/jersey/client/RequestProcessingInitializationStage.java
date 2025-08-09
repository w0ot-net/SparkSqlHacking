package org.glassfish.jersey.client;

import jakarta.inject.Provider;
import jakarta.ws.rs.ext.ReaderInterceptor;
import jakarta.ws.rs.ext.WriterInterceptor;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.model.internal.RankedComparator;

public class RequestProcessingInitializationStage implements Function {
   private final Provider requestRefProvider;
   private final MessageBodyWorkers workersProvider;
   private final Iterable writerInterceptors;
   private final Iterable readerInterceptors;

   public RequestProcessingInitializationStage(Provider requestRefProvider, MessageBodyWorkers workersProvider, InjectionManager injectionManager) {
      this.requestRefProvider = requestRefProvider;
      this.workersProvider = workersProvider;
      this.writerInterceptors = Collections.unmodifiableList((List)StreamSupport.stream(Providers.getAllProviders(injectionManager, WriterInterceptor.class, new RankedComparator()).spliterator(), false).collect(Collectors.toList()));
      this.readerInterceptors = Collections.unmodifiableList((List)StreamSupport.stream(Providers.getAllProviders(injectionManager, ReaderInterceptor.class, new RankedComparator()).spliterator(), false).collect(Collectors.toList()));
   }

   public ClientRequest apply(ClientRequest requestContext) {
      ((Ref)this.requestRefProvider.get()).set(requestContext);
      requestContext.setWorkers(this.workersProvider);
      requestContext.setWriterInterceptors(this.writerInterceptors);
      requestContext.setReaderInterceptors(this.readerInterceptors);
      return requestContext;
   }
}
