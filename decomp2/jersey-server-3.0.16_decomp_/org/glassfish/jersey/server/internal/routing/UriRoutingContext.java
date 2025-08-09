package org.glassfish.jersey.server.internal.routing;

import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriBuilder;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;
import org.glassfish.jersey.internal.util.collection.ImmutableMultivaluedMap;
import org.glassfish.jersey.internal.util.collection.LazyValue;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.message.internal.TracingLogger;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.internal.ServerTraceEvent;
import org.glassfish.jersey.server.internal.process.Endpoint;
import org.glassfish.jersey.server.model.Resource;
import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.model.ResourceMethodInvoker;
import org.glassfish.jersey.server.model.RuntimeResource;
import org.glassfish.jersey.uri.UriComponent;
import org.glassfish.jersey.uri.UriTemplate;
import org.glassfish.jersey.uri.UriComponent.Type;
import org.glassfish.jersey.uri.internal.JerseyUriBuilder;

public class UriRoutingContext implements RoutingContext {
   private final LinkedList matchResults = new LinkedList();
   private final LinkedList matchedResources = new LinkedList();
   private final LinkedList templates = new LinkedList();
   private final MultivaluedHashMap encodedTemplateValues = new MultivaluedHashMap();
   private final ImmutableMultivaluedMap encodedTemplateValuesView;
   private final LinkedList paths;
   private final LinkedList matchedRuntimeResources;
   private final LinkedList matchedLocators;
   private final LinkedList locatorSubResources;
   private final LazyValue tracingLogger;
   private volatile ResourceMethod matchedResourceMethod;
   private volatile Throwable mappedThrowable;
   private Endpoint endpoint;
   private MultivaluedHashMap decodedTemplateValues;
   private ImmutableMultivaluedMap decodedTemplateValuesView;
   private ImmutableMultivaluedMap encodedQueryParamsView;
   private ImmutableMultivaluedMap decodedQueryParamsView;
   private final ContainerRequest requestContext;
   private static final Function PATH_DECODER = (input) -> UriComponent.decode(input, Type.PATH);

   public UriRoutingContext(ContainerRequest requestContext) {
      this.encodedTemplateValuesView = new ImmutableMultivaluedMap(this.encodedTemplateValues);
      this.paths = new LinkedList();
      this.matchedRuntimeResources = new LinkedList();
      this.matchedLocators = new LinkedList();
      this.locatorSubResources = new LinkedList();
      this.matchedResourceMethod = null;
      this.mappedThrowable = null;
      this.requestContext = requestContext;
      this.tracingLogger = Values.lazy(() -> TracingLogger.getInstance(requestContext));
   }

   public void pushMatchResult(MatchResult matchResult) {
      this.matchResults.push(matchResult);
   }

   public void pushMatchedResource(Object resource) {
      ((TracingLogger)this.tracingLogger.get()).log(ServerTraceEvent.MATCH_RESOURCE, new Object[]{resource});
      this.matchedResources.push(resource);
   }

   public Object peekMatchedResource() {
      return this.matchedResources.peek();
   }

   public void pushMatchedLocator(ResourceMethod resourceLocator) {
      ((TracingLogger)this.tracingLogger.get()).log(ServerTraceEvent.MATCH_LOCATOR, new Object[]{resourceLocator.getInvocable().getHandlingMethod()});
      this.matchedLocators.push(resourceLocator);
   }

   public void pushLeftHandPath() {
      String rightHandPath = this.getFinalMatchingGroup();
      int rhpLength = rightHandPath != null ? rightHandPath.length() : 0;
      String encodedRequestPath = this.getPath(false);
      int length = encodedRequestPath.length() - rhpLength;
      if (length <= 0) {
         this.paths.addFirst("");
      } else {
         this.paths.addFirst(encodedRequestPath.substring(0, length));
      }

   }

   public void pushTemplates(UriTemplate resourceTemplate, UriTemplate methodTemplate) {
      Iterator<MatchResult> matchResultIterator = this.matchResults.iterator();
      this.templates.push(resourceTemplate);
      if (methodTemplate != null) {
         this.templates.push(methodTemplate);
         matchResultIterator.next();
      }

      this.pushMatchedTemplateValues(resourceTemplate, (MatchResult)matchResultIterator.next());
      if (methodTemplate != null) {
         this.pushMatchedTemplateValues(methodTemplate, (MatchResult)this.matchResults.peek());
      }

   }

   private void pushMatchedTemplateValues(UriTemplate template, MatchResult matchResult) {
      int i = 1;

      for(String templateVariable : template.getTemplateVariables()) {
         String value = matchResult.group(i++);
         this.encodedTemplateValues.addFirst(templateVariable, value);
         if (this.decodedTemplateValues != null) {
            this.decodedTemplateValues.addFirst(UriComponent.decode(templateVariable, Type.PATH_SEGMENT), UriComponent.decode(value, Type.PATH));
         }
      }

   }

   public String getFinalMatchingGroup() {
      MatchResult mr = (MatchResult)this.matchResults.peek();
      if (mr == null) {
         return null;
      } else {
         String finalGroup = mr.group(mr.groupCount());
         return finalGroup == null ? "" : finalGroup;
      }
   }

   public LinkedList getMatchedResults() {
      return this.matchResults;
   }

   public void setEndpoint(Endpoint endpoint) {
      this.endpoint = endpoint;
   }

   public Endpoint getEndpoint() {
      return this.endpoint;
   }

   public void setMatchedResourceMethod(ResourceMethod resourceMethod) {
      ((TracingLogger)this.tracingLogger.get()).log(ServerTraceEvent.MATCH_RESOURCE_METHOD, new Object[]{resourceMethod.getInvocable().getHandlingMethod()});
      this.matchedResourceMethod = resourceMethod;
   }

   public void pushMatchedRuntimeResource(RuntimeResource runtimeResource) {
      if (((TracingLogger)this.tracingLogger.get()).isLogEnabled(ServerTraceEvent.MATCH_RUNTIME_RESOURCE)) {
         ((TracingLogger)this.tracingLogger.get()).log(ServerTraceEvent.MATCH_RUNTIME_RESOURCE, new Object[]{((Resource)runtimeResource.getResources().get(0)).getPath(), ((Resource)runtimeResource.getResources().get(0)).getPathPattern().getRegex(), ((MatchResult)this.matchResults.peek()).group().substring(0, ((MatchResult)this.matchResults.peek()).group().length() - this.getFinalMatchingGroup().length()), ((MatchResult)this.matchResults.peek()).group()});
      }

      this.matchedRuntimeResources.push(runtimeResource);
   }

   public void pushLocatorSubResource(Resource subResourceFromLocator) {
      this.locatorSubResources.push(subResourceFromLocator);
   }

   public URI getAbsolutePath() {
      return this.requestContext.getAbsolutePath();
   }

   public UriBuilder getAbsolutePathBuilder() {
      return (new JerseyUriBuilder()).uri(this.getAbsolutePath());
   }

   public URI getBaseUri() {
      return this.requestContext.getBaseUri();
   }

   public UriBuilder getBaseUriBuilder() {
      return (new JerseyUriBuilder()).uri(this.getBaseUri());
   }

   public List getMatchedResources() {
      return Collections.unmodifiableList(this.matchedResources);
   }

   public List getMatchedURIs() {
      return this.getMatchedURIs(true);
   }

   public List getMatchedURIs(boolean decode) {
      List<String> result;
      if (decode) {
         result = (List)this.paths.stream().map(PATH_DECODER).collect(Collectors.toList());
      } else {
         result = this.paths;
      }

      return Collections.unmodifiableList(result);
   }

   public String getPath() {
      return this.requestContext.getPath(true);
   }

   public String getPath(boolean decode) {
      return this.requestContext.getPath(decode);
   }

   public MultivaluedMap getPathParameters() {
      return this.getPathParameters(true);
   }

   public MultivaluedMap getPathParameters(boolean decode) {
      if (decode) {
         if (this.decodedTemplateValuesView != null) {
            return this.decodedTemplateValuesView;
         } else {
            if (this.decodedTemplateValues == null) {
               this.decodedTemplateValues = new MultivaluedHashMap();

               for(Map.Entry e : this.encodedTemplateValues.entrySet()) {
                  this.decodedTemplateValues.put(UriComponent.decode((String)e.getKey(), Type.PATH_SEGMENT), (List)((List)e.getValue()).stream().map((s) -> UriComponent.decode(s, Type.PATH)).collect(Collectors.toCollection(ArrayList::new)));
               }
            }

            this.decodedTemplateValuesView = new ImmutableMultivaluedMap(this.decodedTemplateValues);
            return this.decodedTemplateValuesView;
         }
      } else {
         return this.encodedTemplateValuesView;
      }
   }

   public List getPathSegments() {
      return this.getPathSegments(true);
   }

   public List getPathSegments(boolean decode) {
      String requestPath = this.requestContext.getPath(false);
      return Collections.unmodifiableList(UriComponent.decodePath(requestPath, decode));
   }

   public MultivaluedMap getQueryParameters() {
      return this.getQueryParameters(true);
   }

   public MultivaluedMap getQueryParameters(boolean decode) {
      if (decode) {
         if (this.decodedQueryParamsView != null) {
            return this.decodedQueryParamsView;
         } else {
            this.decodedQueryParamsView = new ImmutableMultivaluedMap(UriComponent.decodeQuery(this.getRequestUri(), true));
            return this.decodedQueryParamsView;
         }
      } else if (this.encodedQueryParamsView != null) {
         return this.encodedQueryParamsView;
      } else {
         this.encodedQueryParamsView = new ImmutableMultivaluedMap(UriComponent.decodeQuery(this.getRequestUri(), false));
         return this.encodedQueryParamsView;
      }
   }

   public void invalidateUriComponentViews() {
      this.decodedQueryParamsView = null;
      this.encodedQueryParamsView = null;
   }

   public URI getRequestUri() {
      return this.requestContext.getRequestUri();
   }

   public UriBuilder getRequestUriBuilder() {
      return UriBuilder.fromUri(this.getRequestUri());
   }

   public Throwable getMappedThrowable() {
      return this.mappedThrowable;
   }

   public void setMappedThrowable(Throwable mappedThrowable) {
      this.mappedThrowable = mappedThrowable;
   }

   public List getMatchedTemplates() {
      return Collections.unmodifiableList(this.templates);
   }

   public List getPathSegments(String name) {
      return this.getPathSegments(name, true);
   }

   public List getPathSegments(String name, boolean decode) {
      int[] bounds = this.getPathParameterBounds(name);
      if (bounds != null) {
         String path = ((MatchResult)this.matchResults.getLast()).group();
         int segmentsStart = 0;

         for(int x = 0; x < bounds[0]; ++x) {
            if (path.charAt(x) == '/') {
               ++segmentsStart;
            }
         }

         int segmentsEnd = segmentsStart;

         for(int x = bounds[0]; x < bounds[1]; ++x) {
            if (path.charAt(x) == '/') {
               ++segmentsEnd;
            }
         }

         return this.getPathSegments(decode).subList(segmentsStart - 1, segmentsEnd);
      } else {
         return Collections.emptyList();
      }
   }

   private int[] getPathParameterBounds(String name) {
      Iterator<UriTemplate> templatesIterator = this.templates.iterator();
      Iterator<MatchResult> matchResultsIterator = this.matchResults.iterator();

      while(templatesIterator.hasNext()) {
         MatchResult mr = (MatchResult)matchResultsIterator.next();
         int pIndex = this.getLastPathParameterIndex(name, (UriTemplate)templatesIterator.next());
         if (pIndex != -1) {
            int pathLength = mr.group().length();
            int segmentIndex = mr.end(pIndex + 1);

            int groupLength;
            for(groupLength = segmentIndex - mr.start(pIndex + 1); matchResultsIterator.hasNext(); pathLength = mr.group().length()) {
               mr = (MatchResult)matchResultsIterator.next();
               segmentIndex += mr.group().length() - pathLength;
            }

            return new int[]{segmentIndex - groupLength, segmentIndex};
         }
      }

      return null;
   }

   private int getLastPathParameterIndex(String name, UriTemplate t) {
      int i = 0;
      int pIndex = -1;

      for(String parameterName : t.getTemplateVariables()) {
         if (parameterName.equals(name)) {
            pIndex = i;
         }

         ++i;
      }

      return pIndex;
   }

   public Method getResourceMethod() {
      return this.endpoint instanceof ResourceMethodInvoker ? ((ResourceMethodInvoker)this.endpoint).getResourceMethod() : null;
   }

   public Class getResourceClass() {
      return this.endpoint instanceof ResourceMethodInvoker ? ((ResourceMethodInvoker)this.endpoint).getResourceClass() : null;
   }

   public List getMatchedRuntimeResources() {
      return this.matchedRuntimeResources;
   }

   public ResourceMethod getMatchedResourceMethod() {
      return this.matchedResourceMethod;
   }

   public List getMatchedResourceLocators() {
      return this.matchedLocators;
   }

   public List getLocatorSubResources() {
      return this.locatorSubResources;
   }

   public Resource getMatchedModelResource() {
      return this.matchedResourceMethod == null ? null : this.matchedResourceMethod.getParent();
   }

   public URI resolve(URI uri) {
      return UriTemplate.resolve(this.getBaseUri(), uri);
   }

   public URI relativize(URI uri) {
      if (!uri.isAbsolute()) {
         uri = this.resolve(uri);
      }

      return UriTemplate.relativize(this.getRequestUri(), uri);
   }
}
