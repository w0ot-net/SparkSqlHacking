package org.glassfish.jersey.server.internal.routing;

import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotSupportedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.internal.guava.Primitives;
import org.glassfish.jersey.internal.routing.CombinedMediaType;
import org.glassfish.jersey.internal.routing.ContentTypeDeterminer;
import org.glassfish.jersey.internal.routing.RequestSpecificConsumesProducesAcceptor;
import org.glassfish.jersey.message.MessageBodyWorkers;
import org.glassfish.jersey.message.ReaderModel;
import org.glassfish.jersey.message.WriterModel;
import org.glassfish.jersey.message.internal.AcceptableMediaType;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.model.Parameter.Source;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.internal.LocalizationMessages;
import org.glassfish.jersey.server.internal.process.RequestProcessingContext;
import org.glassfish.jersey.server.model.Invocable;
import org.glassfish.jersey.server.model.Parameter;
import org.glassfish.jersey.server.model.ResourceMethod;

abstract class AbstractMethodSelectingRouter extends ContentTypeDeterminer implements Router {
   private static final Logger LOGGER = Logger.getLogger(AbstractMethodSelectingRouter.class.getName());
   private static final Comparator CONSUMES_PRODUCES_ACCEPTOR_COMPARATOR = new Comparator() {
      public int compare(ConsumesProducesAcceptor o1, ConsumesProducesAcceptor o2) {
         ResourceMethod model1 = o1.methodRouting.method;
         ResourceMethod model2 = o2.methodRouting.method;
         int compared = this.compare(model1.getConsumedTypes(), model2.getConsumedTypes());
         if (compared != 0) {
            return compared;
         } else {
            compared = this.compare(model1.getProducedTypes(), model2.getProducedTypes());
            if (compared != 0) {
               return compared;
            } else {
               compared = MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(o1.consumes.getMediaType(), o2.consumes.getMediaType());
               return compared != 0 ? compared : MediaTypes.PARTIAL_ORDER_COMPARATOR.compare(o1.produces.getMediaType(), o2.produces.getMediaType());
            }
         }
      }

      private int compare(List mediaTypeList1, List mediaTypeList2) {
         mediaTypeList1 = mediaTypeList1.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : mediaTypeList1;
         mediaTypeList2 = mediaTypeList2.isEmpty() ? MediaTypes.WILDCARD_TYPE_SINGLETON_LIST : mediaTypeList2;
         return MediaTypes.MEDIA_TYPE_LIST_COMPARATOR.compare(mediaTypeList1, mediaTypeList2);
      }
   };
   private final Map consumesProducesAcceptors = new HashMap();
   private final Router router;

   AbstractMethodSelectingRouter(MessageBodyWorkers workers, List methodRoutings) {
      super(workers);
      Set<String> httpMethods = new HashSet();

      for(MethodRouting methodRouting : methodRoutings) {
         String httpMethod = methodRouting.method.getHttpMethod();
         httpMethods.add(httpMethod);
         List<ConsumesProducesAcceptor> httpMethodBoundAcceptors = (List)this.consumesProducesAcceptors.get(httpMethod);
         if (httpMethodBoundAcceptors == null) {
            httpMethodBoundAcceptors = new LinkedList();
            this.consumesProducesAcceptors.put(httpMethod, httpMethodBoundAcceptors);
         }

         this.addAllConsumesProducesCombinations(httpMethodBoundAcceptors, methodRouting);
      }

      for(String httpMethod : httpMethods) {
         Collections.sort((List)this.consumesProducesAcceptors.get(httpMethod), CONSUMES_PRODUCES_ACCEPTOR_COMPARATOR);
      }

      if (!this.consumesProducesAcceptors.containsKey("HEAD")) {
         this.router = this.createHeadEnrichedRouter();
      } else {
         this.router = this.createInternalRouter();
      }

   }

   Set getHttpMethods() {
      return this.consumesProducesAcceptors.keySet();
   }

   protected abstract ConsumesProducesAcceptor createConsumesProducesAcceptor(CombinedMediaType.EffectiveMediaType var1, CombinedMediaType.EffectiveMediaType var2, MethodRouting var3);

   private Router createInternalRouter() {
      return new Router() {
         public Router.Continuation apply(RequestProcessingContext requestContext) {
            return Router.Continuation.of(requestContext, (Iterable)AbstractMethodSelectingRouter.this.getMethodRouter(requestContext));
         }
      };
   }

   public Router.Continuation apply(RequestProcessingContext requestContext) {
      return this.router.apply(requestContext);
   }

   private void addAllConsumesProducesCombinations(List acceptors, MethodRouting methodRouting) {
      ResourceMethod resourceMethod = methodRouting.method;
      Set<MediaType> effectiveInputTypes = new LinkedHashSet();
      boolean consumesFromWorkers = this.fillMediaTypes(effectiveInputTypes, resourceMethod, resourceMethod.getConsumedTypes(), true);
      Set<MediaType> effectiveOutputTypes = new LinkedHashSet();
      boolean producesFromWorkers = this.fillMediaTypes(effectiveOutputTypes, resourceMethod, resourceMethod.getProducedTypes(), false);
      Set<ConsumesProducesAcceptor> acceptorSet = new HashSet();

      for(MediaType consumes : effectiveInputTypes) {
         for(MediaType produces : effectiveOutputTypes) {
            acceptorSet.add(this.createConsumesProducesAcceptor(new CombinedMediaType.EffectiveMediaType(consumes, consumesFromWorkers), new CombinedMediaType.EffectiveMediaType(produces, producesFromWorkers), methodRouting));
         }
      }

      acceptors.addAll(acceptorSet);
   }

   private boolean fillMediaTypes(Set effectiveTypes, ResourceMethod resourceMethod, List methodTypes, boolean inputTypes) {
      if (methodTypes.size() > 1 || !methodTypes.contains(MediaType.WILDCARD_TYPE)) {
         effectiveTypes.addAll(methodTypes);
      }

      boolean mediaTypesFromWorkers = effectiveTypes.isEmpty();
      if (mediaTypesFromWorkers) {
         Invocable invocableMethod = resourceMethod.getInvocable();
         if (inputTypes) {
            this.fillInputTypesFromWorkers(effectiveTypes, invocableMethod);
         } else {
            this.fillOutputTypesFromWorkers(effectiveTypes, invocableMethod.getRawResponseType());
         }

         mediaTypesFromWorkers = !effectiveTypes.isEmpty();
         if (!mediaTypesFromWorkers) {
            if (inputTypes) {
               effectiveTypes.addAll(this.workers.getMessageBodyReaderMediaTypesByType(Object.class));
            } else {
               effectiveTypes.addAll(this.workers.getMessageBodyWriterMediaTypesByType(Object.class));
            }

            mediaTypesFromWorkers = true;
         }

         boolean noEntityArgInResourceMethod = inputTypes && this.getEntityParam(invocableMethod) == null;
         boolean voidReturnType = !inputTypes && invocableMethod.getRawResponseType() == Void.TYPE;
         if (noEntityArgInResourceMethod || voidReturnType) {
            effectiveTypes.add(MediaType.WILDCARD_TYPE);
         }
      }

      return mediaTypesFromWorkers;
   }

   private void fillOutputTypesFromWorkers(Set effectiveOutputTypes, Class returnEntityType) {
      effectiveOutputTypes.addAll(this.workers.getMessageBodyWriterMediaTypesByType(returnEntityType));
   }

   private void fillInputTypesFromWorkers(Set effectiveInputTypes, Invocable invocableMethod) {
      for(Parameter p : invocableMethod.getParameters()) {
         if (p.getSource() == Source.ENTITY) {
            effectiveInputTypes.addAll(this.workers.getMessageBodyReaderMediaTypesByType(p.getRawType()));
            break;
         }
      }

   }

   private Parameter getEntityParam(Invocable invocable) {
      for(Parameter parameter : invocable.getParameters()) {
         if (parameter.getSource() == Source.ENTITY && !ContainerRequestContext.class.isAssignableFrom(parameter.getRawType())) {
            return parameter;
         }
      }

      return null;
   }

   private List getMethodRouter(RequestProcessingContext context) {
      final ContainerRequest request = context.request();
      List<ConsumesProducesAcceptor> acceptors = (List)this.consumesProducesAcceptors.get(request.getMethod());
      if (acceptors == null) {
         throw new NotAllowedException(Response.status(Status.METHOD_NOT_ALLOWED).allow(this.consumesProducesAcceptors.keySet()).build());
      } else {
         List<ConsumesProducesAcceptor> satisfyingAcceptors = new LinkedList();
         Set<ResourceMethod> differentInvokableMethods = Collections.newSetFromMap(new IdentityHashMap());
         MediaType requestContentType = request.getMediaType();

         for(ConsumesProducesAcceptor cpi : acceptors) {
            if (cpi.isConsumable(requestContentType)) {
               satisfyingAcceptors.add(cpi);
               differentInvokableMethods.add(cpi.methodRouting.method);
            }
         }

         if (satisfyingAcceptors.isEmpty()) {
            throw new NotSupportedException();
         } else {
            final List<AcceptableMediaType> acceptableMediaTypes = request.getQualifiedAcceptableMediaTypes();
            MediaType effectiveContentType = requestContentType == null ? MediaType.WILDCARD_TYPE : requestContentType;
            final MethodSelector methodSelector = this.selectMethod(acceptableMediaTypes, satisfyingAcceptors, effectiveContentType, differentInvokableMethods.size() == 1);
            if (methodSelector.selected != null) {
               RequestSpecificConsumesProducesAcceptor<MethodRouting> selected = methodSelector.selected;
               if (methodSelector.sameFitnessAcceptors != null) {
                  this.reportMethodSelectionAmbiguity(acceptableMediaTypes, methodSelector.selected, methodSelector.sameFitnessAcceptors);
               }

               context.push(new Function() {
                  public ContainerResponse apply(ContainerResponse responseContext) {
                     if (responseContext.getMediaType() == null && (responseContext.hasEntity() || "HEAD".equals(request.getMethod()))) {
                        MediaType effectiveResponseType = AbstractMethodSelectingRouter.this.determineResponseMediaType(responseContext.getEntityClass(), responseContext.getEntityType(), methodSelector.selected, acceptableMediaTypes);
                        if (MediaTypes.isWildcard(effectiveResponseType)) {
                           if (!effectiveResponseType.isWildcardType() && !"application".equalsIgnoreCase(effectiveResponseType.getType())) {
                              throw new NotAcceptableException();
                           }

                           effectiveResponseType = MediaType.APPLICATION_OCTET_STREAM_TYPE;
                        }

                        responseContext.setMediaType(effectiveResponseType);
                     }

                     return responseContext;
                  }
               });
               return ((MethodRouting)selected.getMethodRouting()).routers;
            } else {
               throw new NotAcceptableException();
            }
         }
      }
   }

   private MediaType determineResponseMediaType(Class entityClass, Type entityType, RequestSpecificConsumesProducesAcceptor selectedMethod, List acceptableMediaTypes) {
      if (usePreSelectedMediaType(selectedMethod, acceptableMediaTypes)) {
         return selectedMethod.getProduces().getCombinedType();
      } else {
         ResourceMethod resourceMethod = ((MethodRouting)selectedMethod.getMethodRouting()).method;
         Invocable invocable = resourceMethod.getInvocable();
         Class<?> responseEntityClass = entityClass == null ? invocable.getRawRoutingResponseType() : entityClass;
         Method handlingMethod = invocable.getHandlingMethod();
         List<MediaType> methodProducesTypes = !resourceMethod.getProducedTypes().isEmpty() ? resourceMethod.getProducedTypes() : Collections.singletonList(MediaType.WILDCARD_TYPE);
         return super.determineResponseMediaType(responseEntityClass, entityType, selectedMethod, acceptableMediaTypes, methodProducesTypes, handlingMethod.getDeclaredAnnotations());
      }
   }

   private static boolean usePreSelectedMediaType(RequestSpecificConsumesProducesAcceptor selectedMethod, List acceptableMediaTypes) {
      if (!selectedMethod.producesFromProviders() && ((MethodRouting)selectedMethod.getMethodRouting()).method.getProducedTypes().size() == 1) {
         return true;
      } else {
         return acceptableMediaTypes.size() == 1 && !MediaTypes.isWildcard((MediaType)acceptableMediaTypes.get(0));
      }
   }

   private boolean isWriteable(RequestSpecificConsumesProducesAcceptor candidate) {
      Invocable invocable = ((MethodRouting)candidate.getMethodRouting()).method.getInvocable();
      Class<?> responseType = Primitives.wrap(invocable.getRawRoutingResponseType());
      if (!Response.class.isAssignableFrom(responseType) && !Void.class.isAssignableFrom(responseType)) {
         Type genericType = invocable.getRoutingResponseType();
         Type genericReturnType = genericType instanceof GenericType ? ((GenericType)genericType).getType() : genericType;

         for(WriterModel model : this.workers.getWritersModelsForType(responseType)) {
            if (model.isWriteable(responseType, genericReturnType, invocable.getHandlingMethod().getDeclaredAnnotations(), candidate.getProduces().getCombinedType())) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private boolean isReadable(RequestSpecificConsumesProducesAcceptor candidate) {
      Invocable invocable = ((MethodRouting)candidate.getMethodRouting()).method.getInvocable();
      Method handlingMethod = invocable.getHandlingMethod();
      Parameter entityParam = this.getEntityParam(invocable);
      if (entityParam == null) {
         return true;
      } else {
         Class<?> entityType = entityParam.getRawType();

         for(ReaderModel model : this.workers.getReaderModelsForType(entityType)) {
            if (model.isReadable(entityType, entityParam.getType(), handlingMethod.getDeclaredAnnotations(), candidate.getConsumes().getCombinedType())) {
               return true;
            }
         }

         return false;
      }
   }

   private MethodSelector selectMethod(List acceptableMediaTypes, List satisfyingAcceptors, MediaType effectiveContentType, boolean singleInvokableMethod) {
      MethodSelector method = new MethodSelector((RequestSpecificConsumesProducesAcceptor)null);
      MethodSelector alternative = new MethodSelector((RequestSpecificConsumesProducesAcceptor)null);

      for(MediaType acceptableMediaType : acceptableMediaTypes) {
         for(ConsumesProducesAcceptor satisfiable : satisfyingAcceptors) {
            CombinedMediaType produces = CombinedMediaType.create(acceptableMediaType, satisfiable.produces);
            if (produces != CombinedMediaType.NO_MATCH) {
               CombinedMediaType consumes = CombinedMediaType.create(effectiveContentType, satisfiable.consumes);
               RequestSpecificConsumesProducesAcceptor<MethodRouting> candidate = new RequestSpecificConsumesProducesAcceptor(consumes, produces, satisfiable.produces.isDerived(), satisfiable.methodRouting);
               if (singleInvokableMethod) {
                  return new MethodSelector(candidate);
               }

               if (candidate.compareTo(method.selected) < 0) {
                  if (method.selected != null && ((MethodRouting)candidate.getMethodRouting()).method == ((MethodRouting)method.selected.getMethodRouting()).method) {
                     method.consider(candidate);
                  } else if (this.isReadable(candidate) && this.isWriteable(candidate)) {
                     method.consider(candidate);
                  } else {
                     alternative.consider(candidate);
                  }
               }
            }
         }
      }

      return method.selected != null ? method : alternative;
   }

   private void reportMethodSelectionAmbiguity(List acceptableTypes, RequestSpecificConsumesProducesAcceptor selected, List sameFitnessAcceptors) {
      if (LOGGER.isLoggable(Level.WARNING)) {
         StringBuilder msgBuilder = (new StringBuilder(LocalizationMessages.AMBIGUOUS_RESOURCE_METHOD(acceptableTypes))).append('\n');
         msgBuilder.append('\t').append(((MethodRouting)selected.getMethodRouting()).method).append('\n');
         Set<ResourceMethod> reportedMethods = new HashSet();
         reportedMethods.add(((MethodRouting)selected.getMethodRouting()).method);

         for(RequestSpecificConsumesProducesAcceptor i : sameFitnessAcceptors) {
            if (!reportedMethods.contains(((MethodRouting)i.getMethodRouting()).method)) {
               msgBuilder.append('\t').append(((MethodRouting)i.getMethodRouting()).method).append('\n');
            }

            reportedMethods.add(((MethodRouting)i.getMethodRouting()).method);
         }

         LOGGER.log(Level.WARNING, msgBuilder.toString());
      }

   }

   private Router createHeadEnrichedRouter() {
      return new Router() {
         public Router.Continuation apply(RequestProcessingContext context) {
            ContainerRequest request = context.request();
            if ("HEAD".equals(request.getMethod())) {
               request.setMethodWithoutException("GET");
               context.push(new Function() {
                  public ContainerResponse apply(ContainerResponse responseContext) {
                     responseContext.getRequestContext().setMethodWithoutException("HEAD");
                     return responseContext;
                  }
               });
            }

            return Router.Continuation.of(context, (Iterable)AbstractMethodSelectingRouter.this.getMethodRouter(context));
         }
      };
   }

   protected abstract static class ConsumesProducesAcceptor {
      final CombinedMediaType.EffectiveMediaType consumes;
      final CombinedMediaType.EffectiveMediaType produces;
      final MethodRouting methodRouting;

      protected ConsumesProducesAcceptor(CombinedMediaType.EffectiveMediaType consumes, CombinedMediaType.EffectiveMediaType produces, MethodRouting methodRouting) {
         this.methodRouting = methodRouting;
         this.consumes = consumes;
         this.produces = produces;
      }

      abstract boolean isConsumable(MediaType var1);

      public String toString() {
         return String.format("%s->%s:%s", this.consumes.getMediaType(), this.produces.getMediaType(), this.methodRouting);
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else if (!(o instanceof ConsumesProducesAcceptor)) {
            return false;
         } else {
            ConsumesProducesAcceptor that = (ConsumesProducesAcceptor)o;
            if (this.consumes != null) {
               if (!this.consumes.equals(that.consumes)) {
                  return false;
               }
            } else if (that.consumes != null) {
               return false;
            }

            if (this.methodRouting != null) {
               if (!this.methodRouting.equals(that.methodRouting)) {
                  return false;
               }
            } else if (that.methodRouting != null) {
               return false;
            }

            if (this.produces != null) {
               if (!this.produces.equals(that.produces)) {
                  return false;
               }
            } else if (that.produces != null) {
               return false;
            }

            return true;
         }
      }

      public int hashCode() {
         int result = this.consumes != null ? this.consumes.hashCode() : 0;
         result = 31 * result + (this.produces != null ? this.produces.hashCode() : 0);
         result = 31 * result + (this.methodRouting != null ? this.methodRouting.hashCode() : 0);
         return result;
      }
   }

   private static class MethodSelector {
      RequestSpecificConsumesProducesAcceptor selected;
      List sameFitnessAcceptors;

      MethodSelector(RequestSpecificConsumesProducesAcceptor i) {
         this.selected = i;
         this.sameFitnessAcceptors = null;
      }

      void consider(RequestSpecificConsumesProducesAcceptor i) {
         int theLessTheBetter = i.compareTo(this.selected);
         if (theLessTheBetter < 0) {
            this.selected = i;
            this.sameFitnessAcceptors = null;
         } else if (theLessTheBetter == 0 && this.selected.getMethodRouting() != i.getMethodRouting()) {
            this.getSameFitnessList().add(i);
         }

      }

      List getSameFitnessList() {
         if (this.sameFitnessAcceptors == null) {
            this.sameFitnessAcceptors = new LinkedList();
         }

         return this.sameFitnessAcceptors;
      }
   }
}
