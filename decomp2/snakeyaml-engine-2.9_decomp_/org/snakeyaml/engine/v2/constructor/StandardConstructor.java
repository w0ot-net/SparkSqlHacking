package org.snakeyaml.engine.v2.constructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import org.snakeyaml.engine.v2.api.ConstructNode;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.env.EnvConfig;
import org.snakeyaml.engine.v2.exceptions.ConstructorException;
import org.snakeyaml.engine.v2.exceptions.DuplicateKeyException;
import org.snakeyaml.engine.v2.exceptions.Mark;
import org.snakeyaml.engine.v2.exceptions.MissingEnvironmentVariableException;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.nodes.MappingNode;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.NodeTuple;
import org.snakeyaml.engine.v2.nodes.SequenceNode;
import org.snakeyaml.engine.v2.nodes.Tag;
import org.snakeyaml.engine.v2.resolver.JsonScalarResolver;

public class StandardConstructor extends BaseConstructor {
   public StandardConstructor(LoadSettings settings) {
      super(settings);
      this.tagConstructors.put(Tag.SET, new ConstructYamlSet());
      this.tagConstructors.put(Tag.STR, new ConstructYamlStr());
      this.tagConstructors.put(Tag.SEQ, new ConstructYamlSeq());
      this.tagConstructors.put(Tag.MAP, new ConstructYamlMap());
      this.tagConstructors.put(Tag.ENV_TAG, new ConstructEnv());
      this.tagConstructors.putAll(settings.getSchema().getSchemaTagConstructors());
      this.tagConstructors.putAll(settings.getTagConstructors());
   }

   protected void flattenMapping(MappingNode node) {
      this.processDuplicateKeys(node);
   }

   protected void processDuplicateKeys(MappingNode node) {
      List<NodeTuple> nodeValue = node.getValue();
      Map<Object, Integer> keys = new HashMap(nodeValue.size());
      TreeSet<Integer> toRemove = new TreeSet();
      int i = 0;

      for(NodeTuple tuple : nodeValue) {
         Node keyNode = tuple.getKeyNode();
         Object key = this.constructKey(keyNode, node.getStartMark(), tuple.getKeyNode().getStartMark());
         Integer prevIndex = (Integer)keys.put(key, i);
         if (prevIndex != null) {
            if (!this.settings.getAllowDuplicateKeys()) {
               throw new DuplicateKeyException(node.getStartMark(), key, tuple.getKeyNode().getStartMark());
            }

            toRemove.add(prevIndex);
         }

         ++i;
      }

      Iterator<Integer> indices2remove = toRemove.descendingIterator();

      while(indices2remove.hasNext()) {
         nodeValue.remove((Integer)indices2remove.next());
      }

   }

   private Object constructKey(Node keyNode, Optional contextMark, Optional problemMark) {
      Object key = this.constructObject(keyNode);
      if (key != null) {
         try {
            key.hashCode();
         } catch (Exception e) {
            throw new ConstructorException("while constructing a mapping", contextMark, "found unacceptable key " + key, problemMark, e);
         }
      }

      return key;
   }

   protected void constructMapping2ndStep(MappingNode node, Map mapping) {
      this.flattenMapping(node);
      super.constructMapping2ndStep(node, mapping);
   }

   protected void constructSet2ndStep(MappingNode node, Set set) {
      this.flattenMapping(node);
      super.constructSet2ndStep(node, set);
   }

   public class ConstructYamlSet implements ConstructNode {
      public Object construct(Node node) {
         if (node.isRecursive()) {
            return StandardConstructor.this.constructedObjects.containsKey(node) ? StandardConstructor.this.constructedObjects.get(node) : StandardConstructor.this.createEmptySetForNode((MappingNode)node);
         } else {
            return StandardConstructor.this.constructSet((MappingNode)node);
         }
      }

      public void constructRecursive(Node node, Object object) {
         if (node.isRecursive()) {
            StandardConstructor.this.constructSet2ndStep((MappingNode)node, (Set)object);
         } else {
            throw new YamlEngineException("Unexpected recursive set structure. Node: " + node);
         }
      }
   }

   public class ConstructYamlStr extends ConstructScalar {
      public Object construct(Node node) {
         return this.constructScalar(node);
      }
   }

   public class ConstructYamlSeq implements ConstructNode {
      public Object construct(Node node) {
         SequenceNode seqNode = (SequenceNode)node;
         return node.isRecursive() ? StandardConstructor.this.createEmptyListForNode(seqNode) : StandardConstructor.this.constructSequence(seqNode);
      }

      public void constructRecursive(Node node, Object data) {
         if (node.isRecursive()) {
            StandardConstructor.this.constructSequenceStep2((SequenceNode)node, (List)data);
         } else {
            throw new YamlEngineException("Unexpected recursive sequence structure. Node: " + node);
         }
      }
   }

   public class ConstructYamlMap implements ConstructNode {
      public Object construct(Node node) {
         MappingNode mappingNode = (MappingNode)node;
         return node.isRecursive() ? StandardConstructor.this.createEmptyMapFor(mappingNode) : StandardConstructor.this.constructMapping(mappingNode);
      }

      public void constructRecursive(Node node, Object object) {
         if (node.isRecursive()) {
            StandardConstructor.this.constructMapping2ndStep((MappingNode)node, (Map)object);
         } else {
            throw new YamlEngineException("Unexpected recursive mapping structure. Node: " + node);
         }
      }
   }

   public class ConstructEnv extends ConstructScalar {
      public Object construct(Node node) {
         String val = this.constructScalar(node);
         Optional<EnvConfig> opt = StandardConstructor.this.settings.getEnvConfig();
         if (opt.isPresent()) {
            EnvConfig config = (EnvConfig)opt.get();
            Matcher matcher = JsonScalarResolver.ENV_FORMAT.matcher(val);
            matcher.matches();
            String name = matcher.group(1);
            String value = matcher.group(3);
            String nonNullValue = value != null ? value : "";
            String separator = matcher.group(2);
            String env = this.getEnv(name);
            Optional<String> overruled = config.getValueFor(name, separator, nonNullValue, env);
            return overruled.orElseGet(() -> this.apply(name, separator, nonNullValue, env));
         } else {
            return val;
         }
      }

      public String apply(String name, String separator, String value, String environment) {
         if (environment != null && !environment.isEmpty()) {
            return environment;
         } else {
            if (separator != null) {
               if (separator.equals("?") && environment == null) {
                  throw new MissingEnvironmentVariableException("Missing mandatory variable " + name + ": " + value);
               }

               if (separator.equals(":?")) {
                  if (environment == null) {
                     throw new MissingEnvironmentVariableException("Missing mandatory variable " + name + ": " + value);
                  }

                  if (environment.isEmpty()) {
                     throw new MissingEnvironmentVariableException("Empty mandatory variable " + name + ": " + value);
                  }
               }

               if (separator.startsWith(":")) {
                  if (environment == null || environment.isEmpty()) {
                     return value;
                  }
               } else if (environment == null) {
                  return value;
               }
            }

            return "";
         }
      }

      public String getEnv(String key) {
         return System.getenv(key);
      }
   }
}
