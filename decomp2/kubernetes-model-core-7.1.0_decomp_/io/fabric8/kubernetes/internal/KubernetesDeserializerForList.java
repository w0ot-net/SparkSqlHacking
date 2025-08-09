package io.fabric8.kubernetes.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KubernetesDeserializerForList extends JsonDeserializer {
   private final KubernetesDeserializer kubernetesDeserializer = new KubernetesDeserializer();

   public List deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = (JsonNode)p.readValueAsTree();
      if (!node.isArray()) {
         throw new JsonMappingException(p, "Expected array but found " + node.getNodeType());
      } else {
         List<KubernetesResource> ret = new ArrayList();

         for(JsonNode item : node) {
            ret.add(this.kubernetesDeserializer.deserialize(p, item));
         }

         return ret;
      }
   }
}
