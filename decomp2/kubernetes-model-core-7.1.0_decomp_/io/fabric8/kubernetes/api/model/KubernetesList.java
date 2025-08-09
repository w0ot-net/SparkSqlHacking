package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "items"})
@Version("v1")
@Group("")
@Kind("List")
@JsonDeserialize(
   using = JsonDeserializer.None.class
)
public class KubernetesList extends DefaultKubernetesResourceList implements KubernetesResource {
   @JsonIgnore
   private Map additionalProperties;

   public KubernetesList() {
      this("v1", new ArrayList(), "List", (ListMeta)null);
   }

   public KubernetesList(String apiVersion, List items, String kind, ListMeta metadata) {
      this.additionalProperties = new LinkedHashMap();
      this.setMetadata(metadata);
      this.setApiVersion(apiVersion);
      this.setKind(kind);
      this.setItems(items);
   }

   @JsonAnyGetter
   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   @JsonAnySetter
   public void setAdditionalProperty(String name, Object value) {
      this.additionalProperties.put(name, value);
   }
}
