package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import java.io.Serializable;
import java.util.List;

@JsonDeserialize(
   using = KubernetesDeserializer.class
)
public interface KubernetesResourceList extends Serializable {
   ListMeta getMetadata();

   List getItems();
}
