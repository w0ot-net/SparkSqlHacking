package io.fabric8.kubernetes.client.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.fabric8.kubernetes.api.model.KubernetesResource;
import io.fabric8.kubernetes.api.model.KubernetesResourceList;
import io.fabric8.kubernetes.api.model.runtime.RawExtension;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.internal.KubernetesDeserializer;
import io.fabric8.kubernetes.model.jackson.GoCompatibilityModule;
import io.fabric8.kubernetes.model.jackson.UnmatchedFieldTypeModule;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.snakeyaml.engine.v2.api.Dump;
import org.snakeyaml.engine.v2.api.DumpSettings;
import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.common.FlowStyle;
import org.snakeyaml.engine.v2.common.ScalarStyle;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.NodeTuple;
import org.snakeyaml.engine.v2.nodes.ScalarNode;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.representer.StandardRepresenter;

public class KubernetesSerialization {
   private final ObjectMapper mapper;
   private final UnmatchedFieldTypeModule unmatchedFieldTypeModule;
   private KubernetesDeserializer kubernetesDeserializer;
   private final boolean searchClassloaders;

   public KubernetesSerialization() {
      this(new ObjectMapper(), true);
   }

   public KubernetesSerialization(ObjectMapper mapper, boolean searchClassloaders) {
      this.unmatchedFieldTypeModule = new UnmatchedFieldTypeModule();
      this.mapper = mapper;
      this.searchClassloaders = searchClassloaders;
      this.configureMapper(mapper);
   }

   protected void configureMapper(ObjectMapper mapper) {
      mapper.registerModules(new Module[]{new JavaTimeModule(), new GoCompatibilityModule(), this.unmatchedFieldTypeModule});
      mapper.disable(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE);
      mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      mapper.disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS);
      mapper.setDefaultPropertyInclusion(Value.construct(Include.NON_NULL, Include.ALWAYS));
      final HandlerInstantiator instanciator = mapper.getDeserializationConfig().getHandlerInstantiator();
      mapper.setConfig((DeserializationConfig)mapper.getDeserializationConfig().with(new HandlerInstantiator() {
         public JsonDeserializer deserializerInstance(DeserializationConfig config, Annotated annotated, Class deserClass) {
            if (deserClass == KubernetesDeserializer.class) {
               return KubernetesSerialization.this.getKubernetesDeserializer();
            } else {
               return instanciator == null ? null : instanciator.deserializerInstance(config, annotated, deserClass);
            }
         }

         public KeyDeserializer keyDeserializerInstance(DeserializationConfig config, Annotated annotated, Class keyDeserClass) {
            return instanciator == null ? null : instanciator.keyDeserializerInstance(config, annotated, keyDeserClass);
         }

         public JsonSerializer serializerInstance(SerializationConfig config, Annotated annotated, Class serClass) {
            return instanciator == null ? null : instanciator.serializerInstance(config, annotated, serClass);
         }

         public TypeResolverBuilder typeResolverBuilderInstance(MapperConfig config, Annotated annotated, Class builderClass) {
            return instanciator == null ? null : instanciator.typeResolverBuilderInstance(config, annotated, builderClass);
         }

         public TypeIdResolver typeIdResolverInstance(MapperConfig config, Annotated annotated, Class resolverClass) {
            return instanciator == null ? null : instanciator.typeIdResolverInstance(config, annotated, resolverClass);
         }
      }));
   }

   private synchronized KubernetesDeserializer getKubernetesDeserializer() {
      if (this.kubernetesDeserializer == null) {
         this.kubernetesDeserializer = new KubernetesDeserializer(this.searchClassloaders);
      }

      return this.kubernetesDeserializer;
   }

   public String asJson(Object object) {
      try {
         return this.mapper.writeValueAsString(object);
      } catch (JsonProcessingException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public String asYaml(Object object) {
      DumpSettings settings = DumpSettings.builder().setExplicitStart(true).setDefaultFlowStyle(FlowStyle.BLOCK).build();
      Dump yaml = new Dump(settings, new StandardRepresenter(settings) {
         private boolean quote = true;

         protected NodeTuple representMappingEntry(Map.Entry entry) {
            Object key = entry.getKey();
            if (key instanceof String) {
               this.quote = false;
               String str = (String)key;
               if (str.length() == 1) {
                  char start = str.charAt(0);
                  this.quote = start == 'y' || start == 'Y' || start == 'n' || start == 'N';
               }
            }

            Node nodeKey = this.representData(key);
            this.quote = true;
            return new NodeTuple(nodeKey, this.representData(entry.getValue()));
         }

         protected Node representScalar(Tag tag, String value, ScalarStyle style) {
            if (style == ScalarStyle.PLAIN) {
               style = this.quote && tag == Tag.STR ? ScalarStyle.DOUBLE_QUOTED : this.defaultScalarStyle;
            }

            return new ScalarNode(tag, value, style);
         }
      });
      return yaml.dumpToString(this.mapper.convertValue(object, Object.class));
   }

   public Object unmarshal(InputStream is) {
      return this.unmarshal(is, new TypeReference() {
         public Type getType() {
            return KubernetesResource.class;
         }
      });
   }

   public Object unmarshal(InputStream is, TypeReference type) {
      try {
         BufferedInputStream bis = new BufferedInputStream(is);

         Object var6;
         try {
            bis.mark(-1);

            int intch;
            do {
               intch = bis.read();
            } while(intch > -1 && Character.isWhitespace(intch));

            bis.reset();
            T result;
            if (intch != 123 && intch != 91) {
               result = (T)this.parseYaml(bis, type);
            } else {
               result = (T)this.mapper.readerFor(type).readValue(bis);
            }

            var6 = result;
         } catch (Throwable var8) {
            try {
               bis.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         bis.close();
         return var6;
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   private Object parseYaml(BufferedInputStream bis, TypeReference type) {
      T result = (T)null;
      List<KubernetesResource> listResult = null;
      Load yaml = new Load(LoadSettings.builder().build());

      for(Object obj : yaml.loadAllFromInputStream(bis)) {
         Object value = null;
         if (obj instanceof Map) {
            value = this.mapper.convertValue(obj, type);
         } else if (obj != null) {
            value = this.mapper.convertValue(new RawExtension(obj), type);
         }

         if (value != null) {
            if (result == null) {
               result = (T)value;
            } else {
               if (listResult == null) {
                  listResult = new ArrayList();
                  accumulateResult(result, listResult);
               }

               accumulateResult(value, listResult);
            }
         }
      }

      if (listResult != null) {
         return listResult;
      } else {
         return result;
      }
   }

   private static void accumulateResult(Object result, List listResult) {
      if (result instanceof KubernetesResourceList) {
         listResult.addAll(((KubernetesResourceList)result).getItems());
      } else {
         listResult.add((KubernetesResource)result);
      }

   }

   public Object unmarshal(String str) {
      return this.unmarshal(str, KubernetesResource.class);
   }

   public Object unmarshal(String str, Class type) {
      try {
         InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));

         Object var4;
         try {
            var4 = this.unmarshal(is, type);
         } catch (Throwable var7) {
            try {
               is.close();
            } catch (Throwable var6) {
               var7.addSuppressed(var6);
            }

            throw var7;
         }

         is.close();
         return var4;
      } catch (IOException e) {
         throw KubernetesClientException.launderThrowable(e);
      }
   }

   public Object unmarshal(InputStream is, final Class type) {
      return this.unmarshal(is, new TypeReference() {
         public Type getType() {
            return type;
         }
      });
   }

   public Object clone(Object resource) {
      try {
         return this.mapper.readValue(this.mapper.writeValueAsString(resource), resource.getClass());
      } catch (JsonProcessingException e) {
         throw new IllegalStateException(e);
      }
   }

   public Object convertValue(Object value, Class type) {
      return this.mapper.convertValue(value, type);
   }

   public Type constructParametricType(Class parameterizedClass, Class... parameterClasses) {
      return this.mapper.getTypeFactory().constructParametricType(parameterizedClass, parameterClasses);
   }

   public Class getRegisteredKubernetesResource(String apiVersion, String kind) {
      return this.getKubernetesDeserializer().getRegisteredKind(apiVersion, kind);
   }

   public UnmatchedFieldTypeModule getUnmatchedFieldTypeModule() {
      return this.unmatchedFieldTypeModule;
   }

   ObjectMapper getMapper() {
      return this.mapper;
   }

   public void registerKubernetesResource(Class clazz) {
      this.getKubernetesDeserializer().registerKubernetesResource(clazz);
   }

   public void registerKubernetesResource(String apiVersion, String kind, Class clazz) {
      this.getKubernetesDeserializer().registerCustomKind(apiVersion, kind, clazz);
   }

   public String convertToJson(String input) {
      try {
         this.mapper.readTree(input);
         return input;
      } catch (JsonProcessingException var3) {
         return this.asJson((JsonNode)this.unmarshal(input, JsonNode.class));
      }
   }
}
