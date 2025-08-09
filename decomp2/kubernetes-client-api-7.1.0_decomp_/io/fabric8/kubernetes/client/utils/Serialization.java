package io.fabric8.kubernetes.client.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator.Feature;
import io.fabric8.kubernetes.model.jackson.GoCompatibilityModule;
import io.fabric8.kubernetes.model.jackson.UnmatchedFieldTypeModule;
import java.io.InputStream;

public class Serialization {
   private static final KubernetesSerialization kubernetesSerialization = new KubernetesSerialization();
   /** @deprecated */
   @Deprecated
   public static final UnmatchedFieldTypeModule UNMATCHED_FIELD_TYPE_MODULE;
   private static volatile ObjectMapper YAML_MAPPER;

   private Serialization() {
   }

   /** @deprecated */
   @Deprecated
   public static ObjectMapper jsonMapper() {
      return kubernetesSerialization.getMapper();
   }

   /** @deprecated */
   @Deprecated
   public static ObjectMapper yamlMapper() {
      if (YAML_MAPPER == null) {
         synchronized(Serialization.class) {
            if (YAML_MAPPER == null) {
               YAML_MAPPER = new ObjectMapper((new YAMLFactory()).disable(Feature.USE_NATIVE_TYPE_ID));
               YAML_MAPPER.registerModules(new Module[]{new GoCompatibilityModule(), UNMATCHED_FIELD_TYPE_MODULE});
            }
         }
      }

      return YAML_MAPPER;
   }

   /** @deprecated */
   @Deprecated
   public static void clearYamlMapper() {
      YAML_MAPPER = null;
   }

   public static String asJson(Object object) {
      return kubernetesSerialization.asJson(object);
   }

   public static String asYaml(Object object) {
      return kubernetesSerialization.asYaml(object);
   }

   /** @deprecated */
   @Deprecated
   public static Object unmarshal(InputStream is) {
      return kubernetesSerialization.unmarshal(is);
   }

   /** @deprecated */
   @Deprecated
   public static Object unmarshal(String str) {
      return kubernetesSerialization.unmarshal(str);
   }

   public static Object unmarshal(String str, Class type) {
      return kubernetesSerialization.unmarshal(str, type);
   }

   public static Object unmarshal(InputStream is, Class type) {
      return kubernetesSerialization.unmarshal(is, type);
   }

   /** @deprecated */
   @Deprecated
   public static Object unmarshal(InputStream is, TypeReference type) {
      return kubernetesSerialization.unmarshal(is, type);
   }

   public static Object clone(Object resource) {
      return kubernetesSerialization.clone(resource);
   }

   static {
      UNMATCHED_FIELD_TYPE_MODULE = kubernetesSerialization.getUnmatchedFieldTypeModule();
   }
}
