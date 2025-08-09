package io.fabric8.kubernetes.api.model.apiextensions.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ConversionReviewFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ConversionRequestBuilder request;
   private ConversionResponseBuilder response;
   private Map additionalProperties;

   public ConversionReviewFluent() {
   }

   public ConversionReviewFluent(ConversionReview instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ConversionReview instance) {
      instance = instance != null ? instance : new ConversionReview();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withRequest(instance.getRequest());
         this.withResponse(instance.getResponse());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public ConversionReviewFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public ConversionReviewFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ConversionRequest buildRequest() {
      return this.request != null ? this.request.build() : null;
   }

   public ConversionReviewFluent withRequest(ConversionRequest request) {
      this._visitables.remove("request");
      if (request != null) {
         this.request = new ConversionRequestBuilder(request);
         this._visitables.get("request").add(this.request);
      } else {
         this.request = null;
         this._visitables.get("request").remove(this.request);
      }

      return this;
   }

   public boolean hasRequest() {
      return this.request != null;
   }

   public RequestNested withNewRequest() {
      return new RequestNested((ConversionRequest)null);
   }

   public RequestNested withNewRequestLike(ConversionRequest item) {
      return new RequestNested(item);
   }

   public RequestNested editRequest() {
      return this.withNewRequestLike((ConversionRequest)Optional.ofNullable(this.buildRequest()).orElse((Object)null));
   }

   public RequestNested editOrNewRequest() {
      return this.withNewRequestLike((ConversionRequest)Optional.ofNullable(this.buildRequest()).orElse((new ConversionRequestBuilder()).build()));
   }

   public RequestNested editOrNewRequestLike(ConversionRequest item) {
      return this.withNewRequestLike((ConversionRequest)Optional.ofNullable(this.buildRequest()).orElse(item));
   }

   public ConversionResponse buildResponse() {
      return this.response != null ? this.response.build() : null;
   }

   public ConversionReviewFluent withResponse(ConversionResponse response) {
      this._visitables.remove("response");
      if (response != null) {
         this.response = new ConversionResponseBuilder(response);
         this._visitables.get("response").add(this.response);
      } else {
         this.response = null;
         this._visitables.get("response").remove(this.response);
      }

      return this;
   }

   public boolean hasResponse() {
      return this.response != null;
   }

   public ResponseNested withNewResponse() {
      return new ResponseNested((ConversionResponse)null);
   }

   public ResponseNested withNewResponseLike(ConversionResponse item) {
      return new ResponseNested(item);
   }

   public ResponseNested editResponse() {
      return this.withNewResponseLike((ConversionResponse)Optional.ofNullable(this.buildResponse()).orElse((Object)null));
   }

   public ResponseNested editOrNewResponse() {
      return this.withNewResponseLike((ConversionResponse)Optional.ofNullable(this.buildResponse()).orElse((new ConversionResponseBuilder()).build()));
   }

   public ResponseNested editOrNewResponseLike(ConversionResponse item) {
      return this.withNewResponseLike((ConversionResponse)Optional.ofNullable(this.buildResponse()).orElse(item));
   }

   public ConversionReviewFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ConversionReviewFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ConversionReviewFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ConversionReviewFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public ConversionReviewFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            ConversionReviewFluent that = (ConversionReviewFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.request, that.request)) {
               return false;
            } else if (!Objects.equals(this.response, that.response)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.request, this.response, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.request != null) {
         sb.append("request:");
         sb.append(this.request + ",");
      }

      if (this.response != null) {
         sb.append("response:");
         sb.append(this.response + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class RequestNested extends ConversionRequestFluent implements Nested {
      ConversionRequestBuilder builder;

      RequestNested(ConversionRequest item) {
         this.builder = new ConversionRequestBuilder(this, item);
      }

      public Object and() {
         return ConversionReviewFluent.this.withRequest(this.builder.build());
      }

      public Object endRequest() {
         return this.and();
      }
   }

   public class ResponseNested extends ConversionResponseFluent implements Nested {
      ConversionResponseBuilder builder;

      ResponseNested(ConversionResponse item) {
         this.builder = new ConversionResponseBuilder(this, item);
      }

      public Object and() {
         return ConversionReviewFluent.this.withResponse(this.builder.build());
      }

      public Object endResponse() {
         return this.and();
      }
   }
}
