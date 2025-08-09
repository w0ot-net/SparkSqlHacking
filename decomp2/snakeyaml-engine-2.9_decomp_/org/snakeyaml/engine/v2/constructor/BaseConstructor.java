package org.snakeyaml.engine.v2.constructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.snakeyaml.engine.v2.api.ConstructNode;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.snakeyaml.engine.v2.exceptions.ConstructorException;
import org.snakeyaml.engine.v2.exceptions.YamlEngineException;
import org.snakeyaml.engine.v2.nodes.MappingNode;
import org.snakeyaml.engine.v2.nodes.Node;
import org.snakeyaml.engine.v2.nodes.NodeTuple;
import org.snakeyaml.engine.v2.nodes.ScalarNode;
import org.snakeyaml.engine.v2.nodes.SequenceNode;
import org.snakeyaml.engine.v2.nodes.Tag;

public abstract class BaseConstructor {
   protected final Map tagConstructors;
   final Map constructedObjects;
   private final Set recursiveObjects;
   private final ArrayList maps2fill;
   private final ArrayList sets2fill;
   protected LoadSettings settings;

   public BaseConstructor(LoadSettings settings) {
      this.settings = settings;
      this.tagConstructors = new HashMap();
      this.constructedObjects = new HashMap();
      this.recursiveObjects = new HashSet();
      this.maps2fill = new ArrayList();
      this.sets2fill = new ArrayList();
   }

   public Object constructSingleDocument(Optional optionalNode) {
      if (optionalNode.isPresent() && !Tag.NULL.equals(((Node)optionalNode.get()).getTag())) {
         return this.construct((Node)optionalNode.get());
      } else {
         ConstructNode construct = (ConstructNode)this.tagConstructors.get(Tag.NULL);
         return construct.construct((Node)optionalNode.orElse((Object)null));
      }
   }

   protected Object construct(Node node) {
      Object var3;
      try {
         Object data = this.constructObject(node);
         this.fillRecursive();
         var3 = data;
      } catch (YamlEngineException e) {
         throw e;
      } catch (RuntimeException e) {
         throw new YamlEngineException(e);
      } finally {
         this.constructedObjects.clear();
         this.recursiveObjects.clear();
      }

      return var3;
   }

   private void fillRecursive() {
      if (!this.maps2fill.isEmpty()) {
         for(RecursiveTuple entry : this.maps2fill) {
            RecursiveTuple<Object, Object> keyValueTuple = (RecursiveTuple)entry.getValue2();
            ((Map)entry.getValue1()).put(keyValueTuple.getValue1(), keyValueTuple.getValue2());
         }

         this.maps2fill.clear();
      }

      if (!this.sets2fill.isEmpty()) {
         for(RecursiveTuple value : this.sets2fill) {
            ((Set)value.getValue1()).add(value.getValue2());
         }

         this.sets2fill.clear();
      }

   }

   protected Object constructObject(Node node) {
      Objects.requireNonNull(node, "Node cannot be null");
      return this.constructedObjects.containsKey(node) ? this.constructedObjects.get(node) : this.constructObjectNoCheck(node);
   }

   protected Object constructObjectNoCheck(Node node) {
      if (this.recursiveObjects.contains(node)) {
         throw new ConstructorException((String)null, Optional.empty(), "found unconstructable recursive node", node.getStartMark());
      } else {
         this.recursiveObjects.add(node);
         ConstructNode constructor = (ConstructNode)this.findConstructorFor(node).orElseThrow(() -> new ConstructorException((String)null, Optional.empty(), "could not determine a constructor for the tag " + node.getTag(), node.getStartMark()));
         Object data = this.constructedObjects.containsKey(node) ? this.constructedObjects.get(node) : constructor.construct(node);
         this.constructedObjects.put(node, data);
         this.recursiveObjects.remove(node);
         if (node.isRecursive()) {
            constructor.constructRecursive(node, data);
         }

         return data;
      }
   }

   protected Optional findConstructorFor(Node node) {
      Tag tag = node.getTag();
      if (this.settings.getTagConstructors().containsKey(tag)) {
         return Optional.of((ConstructNode)this.settings.getTagConstructors().get(tag));
      } else {
         return this.tagConstructors.containsKey(tag) ? Optional.of((ConstructNode)this.tagConstructors.get(tag)) : Optional.empty();
      }
   }

   protected String constructScalar(ScalarNode node) {
      return node.getValue();
   }

   protected List createEmptyListForNode(SequenceNode node) {
      return (List)this.settings.getDefaultList().apply(node.getValue().size());
   }

   protected Set createEmptySetForNode(MappingNode node) {
      return (Set)this.settings.getDefaultSet().apply(node.getValue().size());
   }

   protected Map createEmptyMapFor(MappingNode node) {
      return (Map)this.settings.getDefaultMap().apply(node.getValue().size());
   }

   protected List constructSequence(SequenceNode node) {
      List<Object> result = (List)this.settings.getDefaultList().apply(node.getValue().size());
      this.constructSequenceStep2(node, result);
      return result;
   }

   protected void constructSequenceStep2(SequenceNode node, Collection collection) {
      for(Node child : node.getValue()) {
         collection.add(this.constructObject(child));
      }

   }

   protected Set constructSet(MappingNode node) {
      Set<Object> set = (Set)this.settings.getDefaultSet().apply(node.getValue().size());
      this.constructSet2ndStep(node, set);
      return set;
   }

   protected Map constructMapping(MappingNode node) {
      Map<Object, Object> mapping = (Map)this.settings.getDefaultMap().apply(node.getValue().size());
      this.constructMapping2ndStep(node, mapping);
      return mapping;
   }

   protected void constructMapping2ndStep(MappingNode node, Map mapping) {
      for(NodeTuple tuple : node.getValue()) {
         Node keyNode = tuple.getKeyNode();
         Node valueNode = tuple.getValueNode();
         Object key = this.constructObject(keyNode);
         if (key != null) {
            try {
               key.hashCode();
            } catch (Exception e) {
               throw new ConstructorException("while constructing a mapping", node.getStartMark(), "found unacceptable key " + key, tuple.getKeyNode().getStartMark(), e);
            }
         }

         Object value = this.constructObject(valueNode);
         if (keyNode.isRecursive()) {
            if (!this.settings.getAllowRecursiveKeys()) {
               throw new YamlEngineException("Recursive key for mapping is detected but it is not configured to be allowed.");
            }

            this.postponeMapFilling(mapping, key, value);
         } else {
            mapping.put(key, value);
         }
      }

   }

   protected void postponeMapFilling(Map mapping, Object key, Object value) {
      this.maps2fill.add(0, new RecursiveTuple(mapping, new RecursiveTuple(key, value)));
   }

   protected void constructSet2ndStep(MappingNode node, Set set) {
      for(NodeTuple tuple : node.getValue()) {
         Node keyNode = tuple.getKeyNode();
         Object key = this.constructObject(keyNode);
         if (key != null) {
            try {
               key.hashCode();
            } catch (Exception e) {
               throw new ConstructorException("while constructing a Set", node.getStartMark(), "found unacceptable key " + key, tuple.getKeyNode().getStartMark(), e);
            }
         }

         if (keyNode.isRecursive()) {
            if (!this.settings.getAllowRecursiveKeys()) {
               throw new YamlEngineException("Recursive key for mapping is detected but it is not configured to be allowed.");
            }

            this.postponeSetFilling(set, key);
         } else {
            set.add(key);
         }
      }

   }

   protected void postponeSetFilling(Set set, Object key) {
      this.sets2fill.add(0, new RecursiveTuple(set, key));
   }

   private static class RecursiveTuple {
      private final Object value1;
      private final Object value2;

      public RecursiveTuple(Object value1, Object value2) {
         this.value1 = value1;
         this.value2 = value2;
      }

      public Object getValue2() {
         return this.value2;
      }

      public Object getValue1() {
         return this.value1;
      }
   }
}
