package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.ArrayList;
import java.util.List;
import lombok.Generated;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
public class DefaultKubernetesResourceList implements KubernetesResource, KubernetesResourceList {
   @JsonProperty("apiVersion")
   private String apiVersion;
   @JsonProperty("items")
   private List items = new ArrayList();
   @JsonProperty("kind")
   private String kind;
   @JsonProperty("metadata")
   private ListMeta metadata;

   @Generated
   public String getApiVersion() {
      return this.apiVersion;
   }

   @Generated
   public List getItems() {
      return this.items;
   }

   @Generated
   public String getKind() {
      return this.kind;
   }

   @Generated
   public ListMeta getMetadata() {
      return this.metadata;
   }

   @JsonProperty("apiVersion")
   @Generated
   public void setApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
   }

   @JsonProperty("items")
   @Generated
   public void setItems(List items) {
      this.items = items;
   }

   @JsonProperty("kind")
   @Generated
   public void setKind(String kind) {
      this.kind = kind;
   }

   @JsonProperty("metadata")
   @Generated
   public void setMetadata(ListMeta metadata) {
      this.metadata = metadata;
   }

   @Generated
   public String toString() {
      String var10000 = this.getApiVersion();
      return "DefaultKubernetesResourceList(apiVersion=" + var10000 + ", items=" + this.getItems() + ", kind=" + this.getKind() + ", metadata=" + this.getMetadata() + ")";
   }
}
