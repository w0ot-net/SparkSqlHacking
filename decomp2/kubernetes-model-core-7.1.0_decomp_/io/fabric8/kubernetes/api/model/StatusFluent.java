package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class StatusFluent extends BaseFluent {
   private String apiVersion;
   private Integer code;
   private StatusDetailsBuilder details;
   private String kind;
   private String message;
   private ListMetaBuilder metadata;
   private String reason;
   private String status;
   private Map additionalProperties;

   public StatusFluent() {
   }

   public StatusFluent(Status instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Status instance) {
      instance = instance != null ? instance : new Status();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withCode(instance.getCode());
         this.withDetails(instance.getDetails());
         this.withKind(instance.getKind());
         this.withMessage(instance.getMessage());
         this.withMetadata(instance.getMetadata());
         this.withReason(instance.getReason());
         this.withStatus(instance.getStatus());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public StatusFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public Integer getCode() {
      return this.code;
   }

   public StatusFluent withCode(Integer code) {
      this.code = code;
      return this;
   }

   public boolean hasCode() {
      return this.code != null;
   }

   public StatusDetails buildDetails() {
      return this.details != null ? this.details.build() : null;
   }

   public StatusFluent withDetails(StatusDetails details) {
      this._visitables.remove("details");
      if (details != null) {
         this.details = new StatusDetailsBuilder(details);
         this._visitables.get("details").add(this.details);
      } else {
         this.details = null;
         this._visitables.get("details").remove(this.details);
      }

      return this;
   }

   public boolean hasDetails() {
      return this.details != null;
   }

   public DetailsNested withNewDetails() {
      return new DetailsNested((StatusDetails)null);
   }

   public DetailsNested withNewDetailsLike(StatusDetails item) {
      return new DetailsNested(item);
   }

   public DetailsNested editDetails() {
      return this.withNewDetailsLike((StatusDetails)Optional.ofNullable(this.buildDetails()).orElse((Object)null));
   }

   public DetailsNested editOrNewDetails() {
      return this.withNewDetailsLike((StatusDetails)Optional.ofNullable(this.buildDetails()).orElse((new StatusDetailsBuilder()).build()));
   }

   public DetailsNested editOrNewDetailsLike(StatusDetails item) {
      return this.withNewDetailsLike((StatusDetails)Optional.ofNullable(this.buildDetails()).orElse(item));
   }

   public String getKind() {
      return this.kind;
   }

   public StatusFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public String getMessage() {
      return this.message;
   }

   public StatusFluent withMessage(String message) {
      this.message = message;
      return this;
   }

   public boolean hasMessage() {
      return this.message != null;
   }

   public ListMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public StatusFluent withMetadata(ListMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ListMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public StatusFluent withNewMetadata(String _continue, Long remainingItemCount, String resourceVersion, String selfLink) {
      return this.withMetadata(new ListMeta(_continue, remainingItemCount, resourceVersion, selfLink));
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ListMeta)null);
   }

   public MetadataNested withNewMetadataLike(ListMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ListMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ListMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ListMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ListMeta item) {
      return this.withNewMetadataLike((ListMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public String getReason() {
      return this.reason;
   }

   public StatusFluent withReason(String reason) {
      this.reason = reason;
      return this;
   }

   public boolean hasReason() {
      return this.reason != null;
   }

   public String getStatus() {
      return this.status;
   }

   public StatusFluent withStatus(String status) {
      this.status = status;
      return this;
   }

   public boolean hasStatus() {
      return this.status != null;
   }

   public StatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public StatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public StatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public StatusFluent removeFromAdditionalProperties(Map map) {
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

   public StatusFluent withAdditionalProperties(Map additionalProperties) {
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
            StatusFluent that = (StatusFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.code, that.code)) {
               return false;
            } else if (!Objects.equals(this.details, that.details)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.message, that.message)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.reason, that.reason)) {
               return false;
            } else if (!Objects.equals(this.status, that.status)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.code, this.details, this.kind, this.message, this.metadata, this.reason, this.status, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.code != null) {
         sb.append("code:");
         sb.append(this.code + ",");
      }

      if (this.details != null) {
         sb.append("details:");
         sb.append(this.details + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.message != null) {
         sb.append("message:");
         sb.append(this.message + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.reason != null) {
         sb.append("reason:");
         sb.append(this.reason + ",");
      }

      if (this.status != null) {
         sb.append("status:");
         sb.append(this.status + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class DetailsNested extends StatusDetailsFluent implements Nested {
      StatusDetailsBuilder builder;

      DetailsNested(StatusDetails item) {
         this.builder = new StatusDetailsBuilder(this, item);
      }

      public Object and() {
         return StatusFluent.this.withDetails(this.builder.build());
      }

      public Object endDetails() {
         return this.and();
      }
   }

   public class MetadataNested extends ListMetaFluent implements Nested {
      ListMetaBuilder builder;

      MetadataNested(ListMeta item) {
         this.builder = new ListMetaBuilder(this, item);
      }

      public Object and() {
         return StatusFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }
}
