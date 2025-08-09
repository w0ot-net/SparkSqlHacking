package io.fabric8.kubernetes.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class KubernetesDeserializerForMap extends JsonDeserializer {
   private final KubernetesDeserializer kubernetesDeserializer = new KubernetesDeserializer();

   public Map deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = (JsonNode)p.readValueAsTree();
      if (!node.isObject()) {
         throw new JsonMappingException(p, "Expected map but found " + node.getNodeType());
      } else {
         Map<String, KubernetesResource> ret = new LinkedHashMap();
         Iterator<Map.Entry<String, JsonNode>> it = node.fields();

         while(it.hasNext()) {
            Map.Entry<String, JsonNode> entry = (Map.Entry)it.next();
            ret.put((String)entry.getKey(), this.kubernetesDeserializer.deserialize(p, (JsonNode)entry.getValue()));
         }

         return ret;
      }
   }
}
