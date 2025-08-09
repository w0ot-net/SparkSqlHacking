package io.fabric8.kubernetes.model.jackson;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.ResolvableDeserializer;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JsonUnwrappedDeserializer extends JsonDeserializer implements ContextualDeserializer {
   private static final JsonUnwrapped cancelUnwrappedAnnotation;
   private JsonDeserializer beanDeserializer;
   private Set ownPropertyNames;
   private List unwrappedInfos;

   public JsonUnwrappedDeserializer() {
   }

   public JsonUnwrappedDeserializer(DeserializationContext deserializationContext) throws JsonMappingException {
      JavaType type = deserializationContext.getContextualType();
      BeanDescription description = deserializationContext.getConfig().introspect(type);
      List<BeanPropertyDefinition> unwrappedProperties = (List)description.findProperties().stream().filter((prop) -> Stream.of(prop.getConstructorParameter(), prop.getMutator(), prop.getField()).filter(Objects::nonNull).anyMatch((member) -> {
            JsonUnwrapped unwrappedAnnotation = (JsonUnwrapped)member.getAnnotation(JsonUnwrapped.class);
            if (unwrappedAnnotation != null) {
               member.getAllAnnotations().add(cancelUnwrappedAnnotation);
            }

            return unwrappedAnnotation != null;
         })).collect(Collectors.toList());
      if (unwrappedProperties.isEmpty()) {
         throw new UnsupportedOperationException("@JsonUnwrapped properties not found in " + type.getTypeName());
      } else {
         this.ownPropertyNames = (Set)description.findProperties().stream().map(BeanPropertyDefinition::getName).collect(Collectors.toSet());
         this.ownPropertyNames.removeAll(description.getIgnoredPropertyNames());
         JsonDeserializer<Object> rawBeanDeserializer = deserializationContext.getFactory().createBeanDeserializer(deserializationContext, type, description);
         ((ResolvableDeserializer)rawBeanDeserializer).resolve(deserializationContext);
         this.beanDeserializer = rawBeanDeserializer;
         this.unwrappedInfos = new ArrayList();

         for(BeanPropertyDefinition unwrappedProperty : unwrappedProperties) {
            this.unwrappedInfos.add(new UnwrappedInfo(deserializationContext, unwrappedProperty));
            this.ownPropertyNames.remove(unwrappedProperty.getName());
         }

      }
   }

   public JsonDeserializer createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
      return new JsonUnwrappedDeserializer(deserializationContext);
   }

   public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
      ObjectNode node = (ObjectNode)jsonParser.readValueAsTree();
      ObjectNode ownNode = deserializationContext.getNodeFactory().objectNode();
      Map<UnwrappedInfo, ObjectNode> unwrappedNodes = new HashMap();
      node.fields().forEachRemaining((entryx) -> {
         String key = (String)entryx.getKey();
         JsonNode value = (JsonNode)entryx.getValue();
         boolean replaced = false;

         for(UnwrappedInfo unwrapped : this.unwrappedInfos) {
            String transformed = unwrapped.nameTransformer.reverse(key);
            ObjectNode unwrappedNode = (ObjectNode)unwrappedNodes.getOrDefault(unwrapped, deserializationContext.getNodeFactory().objectNode());
            if (transformed != null && !this.ownPropertyNames.contains(key) && unwrapped.beanPropertyNames.contains(transformed)) {
               unwrappedNodes.putIfAbsent(unwrapped, unwrappedNode);
               unwrappedNode.replace(transformed, value);
               replaced = true;
            }
         }

         if (!replaced && this.ownPropertyNames.contains(key)) {
            ownNode.replace(key, value);
         }

      });

      for(Map.Entry entry : unwrappedNodes.entrySet()) {
         ownNode.replace(((UnwrappedInfo)entry.getKey()).propertyName, (JsonNode)entry.getValue());
      }

      TreeTraversingParser syntheticParser = new TreeTraversingParser(ownNode, jsonParser.getCodec());

      Object var12;
      try {
         syntheticParser.nextToken();
         var12 = this.beanDeserializer.deserialize(syntheticParser, deserializationContext);
      } catch (Throwable var10) {
         try {
            syntheticParser.close();
         } catch (Throwable var9) {
            var10.addSuppressed(var9);
         }

         throw var10;
      }

      syntheticParser.close();
      return var12;
   }

   static {
      try {
         cancelUnwrappedAnnotation = (JsonUnwrapped)CancelUnwrapped.class.getField("dummy").getAnnotation(JsonUnwrapped.class);
      } catch (NoSuchFieldException ex) {
         throw new RuntimeException(ex);
      }
   }

   private static final class UnwrappedInfo {
      final String propertyName;
      final NameTransformer nameTransformer;
      final Set beanPropertyNames;

      public UnwrappedInfo(DeserializationContext context, BeanPropertyDefinition unwrappedProperty) {
         this.propertyName = unwrappedProperty.getName();
         JsonUnwrapped annotation = (JsonUnwrapped)unwrappedProperty.getField().getAnnotation(JsonUnwrapped.class);
         this.nameTransformer = NameTransformer.simpleTransformer(annotation.prefix(), annotation.suffix());
         this.beanPropertyNames = new HashSet();
         Set<Class<?>> processedTypes = new HashSet();
         extractPropertiesDeep(context, processedTypes, this.beanPropertyNames, unwrappedProperty);
      }

      private static void extractPropertiesDeep(DeserializationContext context, Set processedTypes, Set properties, BeanPropertyDefinition bean) {
         for(NamedType type : context.getConfig().getSubtypeResolver().collectAndResolveSubtypesByClass(context.getConfig(), context.getConfig().introspect(bean.getPrimaryType()).getClassInfo())) {
            if (processedTypes.add(type.getType())) {
               for(BeanPropertyDefinition property : context.getConfig().introspect(context.constructType(type.getType())).findProperties()) {
                  properties.add(property.getName());
                  extractPropertiesDeep(context, processedTypes, properties, property);
               }
            }
         }

      }
   }

   private static class CancelUnwrapped {
      @JsonUnwrapped(
         enabled = false
      )
      public Object dummy;
   }
}
