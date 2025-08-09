package org.glassfish.jersey.server.internal.process;

import java.util.function.Function;
import org.glassfish.jersey.internal.inject.InjectionManager;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.internal.util.collection.Refs;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.process.internal.ChainableStage;
import org.glassfish.jersey.process.internal.Stage;
import org.glassfish.jersey.server.AsyncContext;
import org.glassfish.jersey.server.CloseableService;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.internal.monitoring.RequestEventBuilder;
import org.glassfish.jersey.server.internal.routing.RoutingContext;
import org.glassfish.jersey.server.internal.routing.UriRoutingContext;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;

public final class RequestProcessingContext implements RespondingContext {
   private final InjectionManager injectionManager;
   private final ContainerRequest request;
   private final UriRoutingContext routingContext;
   private final RespondingContext respondingContext;
   private final CloseableService closeableService;
   private final RequestEventBuilder monitoringEventBuilder;
   private final RequestEventListener monitoringEventListener;
   private final Ref asyncContextValueRef;

   public RequestProcessingContext(InjectionManager injectionManager, ContainerRequest request, UriRoutingContext routingContext, RequestEventBuilder monitoringEventBuilder, RequestEventListener monitoringEventListener) {
      this.injectionManager = injectionManager;
      this.request = request;
      this.routingContext = routingContext;
      this.respondingContext = new DefaultRespondingContext();
      this.closeableService = new DefaultCloseableService();
      this.monitoringEventBuilder = monitoringEventBuilder;
      this.monitoringEventListener = monitoringEventListener;
      this.asyncContextValueRef = Refs.threadSafe(Values.empty());
   }

   public ContainerRequest request() {
      return this.request;
   }

   public RoutingContext routingContext() {
      return this.routingContext;
   }

   UriRoutingContext uriRoutingContext() {
      return this.routingContext;
   }

   public CloseableService closeableService() {
      return this.closeableService;
   }

   public void initAsyncContext(Value lazyContextValue) {
      this.asyncContextValueRef.set(Values.lazy(lazyContextValue));
   }

   public AsyncContext asyncContext() {
      return (AsyncContext)((Value)this.asyncContextValueRef.get()).get();
   }

   public Value asyncContextValue() {
      return (Value)this.asyncContextValueRef.get();
   }

   public InjectionManager injectionManager() {
      return this.injectionManager;
   }

   public RequestEventBuilder monitoringEventBuilder() {
      return this.monitoringEventBuilder;
   }

   public void triggerEvent(RequestEvent.Type eventType) {
      if (this.monitoringEventListener != null) {
         this.monitoringEventListener.onEvent(this.monitoringEventBuilder.build(eventType));
      }

   }

   public void push(Function responseTransformation) {
      this.respondingContext.push(responseTransformation);
   }

   public void push(ChainableStage stage) {
      this.respondingContext.push(stage);
   }

   public Stage createRespondingRoot() {
      return this.respondingContext.createRespondingRoot();
   }
}
