package io.fabric8.kubernetes.internal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.fabric8.kubernetes.api.model.GenericKubernetesResource;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.KubernetesListBuilder;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.runtime.RawExtension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class KubernetesDeserializer extends JsonDeserializer {
   private static final String KIND = "kind";
   private static final String API_VERSION = "apiVersion";
   private final Mapping mapping;
   private static Mapping DEFAULT_MAPPING;

   public KubernetesDeserializer() {
      this(true);
   }

   public KubernetesDeserializer(boolean scanClassloaders) {
      this.mapping = new Mapping();
      if (scanClassloaders) {
         synchronized(KubernetesDeserializer.class) {
            if (DEFAULT_MAPPING == null) {
               DEFAULT_MAPPING = new Mapping();
               DEFAULT_MAPPING.registerClassesFromClassLoaders();
            }
         }

         this.mapping.mappings.putAll(DEFAULT_MAPPING.mappings);
      }

   }

   public KubernetesResource deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      JsonNode node = (JsonNode)jp.readValueAsTree();
      return this.deserialize(jp, node);
   }

   final KubernetesResource deserialize(JsonParser jp, JsonNode node) throws IOException {
      if (node.isObject()) {
         return this.fromObjectNode(jp, node);
      } else if (node.isArray()) {
         return this.fromArrayNode(jp, node);
      } else {
         Object object = node.traverse(jp.getCodec()).readValueAs(Object.class);
         return object == null ? null : new RawExtension(object);
      }
   }

   private KubernetesResource fromArrayNode(JsonParser jp, JsonNode node) throws IOException {
      Iterator<JsonNode> iterator = node.elements();
      List<HasMetadata> list = new ArrayList();

      while(iterator.hasNext()) {
         JsonNode jsonNode = (JsonNode)iterator.next();
         if (!jsonNode.isObject()) {
            throw new JsonMappingException(jp, "Cannot parse a nested array containing non-object resource");
         }

         KubernetesResource resource = this.fromObjectNode(jp, jsonNode);
         if (!(resource instanceof HasMetadata)) {
            throw new JsonMappingException(jp, "Cannot parse a nested array containing a non-HasMetadata resource");
         }

         list.add((HasMetadata)resource);
      }

      return ((KubernetesListBuilder)(new KubernetesListBuilder()).withItems(list)).build();
   }

   private KubernetesResource fromObjectNode(JsonParser jp, JsonNode node) throws IOException {
      TypeKey key = this.createKey(node);
      Class<? extends KubernetesResource> resourceType = this.mapping.getForKey(key);
      if (resourceType == null) {
         return key == null ? (KubernetesResource)jp.getCodec().treeToValue(node, RawExtension.class) : (KubernetesResource)jp.getCodec().treeToValue(node, GenericKubernetesResource.class);
      } else if (KubernetesResource.class.isAssignableFrom(resourceType)) {
         return (KubernetesResource)jp.getCodec().treeToValue(node, resourceType);
      } else {
         throw new JsonMappingException(jp, String.format("There's a class loading issue, %s is registered as a KubernetesResource, but is not an instance of KubernetesResource", resourceType.getName()));
      }
   }

   private TypeKey createKey(JsonNode node) {
      JsonNode apiVersion = node.get("apiVersion");
      JsonNode kind = node.get("kind");
      return this.mapping.createKey(apiVersion != null ? apiVersion.textValue() : null, kind != null ? kind.textValue() : null);
   }

   public void registerCustomKind(String apiVersion, String kind, Class clazz) {
      this.mapping.registerKind(apiVersion, kind, clazz);
   }

   public void registerKubernetesResource(Class clazz) {
      this.mapping.addMapping(clazz);
   }

   public Class getRegisteredKind(String apiVersion, String kind) {
      return this.mapping.getForKey(this.mapping.createKey(apiVersion, kind));
   }

   static class TypeKey {
      final String kind;
      final String apiGroup;
      final String version;

      TypeKey(String kind, String apiGroup, String version) {
         this.kind = kind;
         this.apiGroup = apiGroup;
         this.version = version;
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.kind, this.apiGroup, this.version});
      }

      public boolean equals(Object obj) {
         if (this == obj) {
            return true;
         } else if (!(obj instanceof TypeKey)) {
            return false;
         } else {
            TypeKey o = (TypeKey)obj;
            return Objects.equals(this.kind, o.kind) && Objects.equals(this.apiGroup, o.apiGroup) && Objects.equals(this.version, o.version);
         }
      }
   }

   static class Mapping {
      private final Map mappings = new ConcurrentHashMap();

      public Class getForKey(TypeKey key) {
         return key == null ? null : (Class)this.mappings.get(key);
      }

      public void registerKind(String apiVersion, String kind, Class clazz) {
         Optional.ofNullable(this.createKey(apiVersion, kind)).ifPresent((k) -> this.mappings.put(k, clazz));
      }

      TypeKey createKey(String apiVersion, String kind) {
         if (kind != null && apiVersion != null) {
            String[] versionParts = new String[]{null, apiVersion};
            if (apiVersion.contains("/")) {
               versionParts = apiVersion.split("/", 2);
            }

            return new TypeKey(kind, versionParts[0], versionParts[1]);
         } else {
            return null;
         }
      }

      void registerClassesFromClassLoaders() {
         Stream.of(Thread.currentThread().getContextClassLoader(), KubernetesDeserializer.class.getClassLoader()).filter(Objects::nonNull).map((cl) -> ServiceLoader.load(KubernetesResource.class, cl)).map(ServiceLoader::iterator).map((i) -> () -> i).flatMap((i) -> StreamSupport.stream(i.spliterator(), false)).map(Object::getClass).forEach(this::addMapping);
      }

      TypeKey getKeyFromClass(Class clazz) {
         String apiGroup = HasMetadata.getGroup(clazz);
         String apiVersion = HasMetadata.getVersion(clazz);
         String kind = HasMetadata.getKind(clazz);
         if (apiGroup != null && !apiGroup.isEmpty() && apiVersion != null && !apiVersion.isEmpty()) {
            return new TypeKey(kind, apiGroup, apiVersion);
         } else {
            return apiVersion != null && !apiVersion.isEmpty() ? this.createKey(apiVersion, kind) : null;
         }
      }

      private void addMapping(Class clazz) {
         TypeKey keyFromClass = this.getKeyFromClass(clazz);
         if (keyFromClass != null) {
            this.mappings.put(keyFromClass, clazz);
            if (keyFromClass.apiGroup != null && keyFromClass.apiGroup.endsWith(".openshift.io")) {
               this.mappings.putIfAbsent(new TypeKey(keyFromClass.kind, (String)null, keyFromClass.version), clazz);
            }

         }
      }
   }
}
