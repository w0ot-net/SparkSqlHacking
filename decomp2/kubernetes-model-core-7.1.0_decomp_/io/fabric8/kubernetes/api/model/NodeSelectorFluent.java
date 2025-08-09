package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class NodeSelectorFluent extends BaseFluent {
   private ArrayList nodeSelectorTerms = new ArrayList();
   private Map additionalProperties;

   public NodeSelectorFluent() {
   }

   public NodeSelectorFluent(NodeSelector instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeSelector instance) {
      instance = instance != null ? instance : new NodeSelector();
      if (instance != null) {
         this.withNodeSelectorTerms(instance.getNodeSelectorTerms());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeSelectorFluent addToNodeSelectorTerms(int index, NodeSelectorTerm item) {
      if (this.nodeSelectorTerms == null) {
         this.nodeSelectorTerms = new ArrayList();
      }

      NodeSelectorTermBuilder builder = new NodeSelectorTermBuilder(item);
      if (index >= 0 && index < this.nodeSelectorTerms.size()) {
         this._visitables.get("nodeSelectorTerms").add(index, builder);
         this.nodeSelectorTerms.add(index, builder);
      } else {
         this._visitables.get("nodeSelectorTerms").add(builder);
         this.nodeSelectorTerms.add(builder);
      }

      return this;
   }

   public NodeSelectorFluent setToNodeSelectorTerms(int index, NodeSelectorTerm item) {
      if (this.nodeSelectorTerms == null) {
         this.nodeSelectorTerms = new ArrayList();
      }

      NodeSelectorTermBuilder builder = new NodeSelectorTermBuilder(item);
      if (index >= 0 && index < this.nodeSelectorTerms.size()) {
         this._visitables.get("nodeSelectorTerms").set(index, builder);
         this.nodeSelectorTerms.set(index, builder);
      } else {
         this._visitables.get("nodeSelectorTerms").add(builder);
         this.nodeSelectorTerms.add(builder);
      }

      return this;
   }

   public NodeSelectorFluent addToNodeSelectorTerms(NodeSelectorTerm... items) {
      if (this.nodeSelectorTerms == null) {
         this.nodeSelectorTerms = new ArrayList();
      }

      for(NodeSelectorTerm item : items) {
         NodeSelectorTermBuilder builder = new NodeSelectorTermBuilder(item);
         this._visitables.get("nodeSelectorTerms").add(builder);
         this.nodeSelectorTerms.add(builder);
      }

      return this;
   }

   public NodeSelectorFluent addAllToNodeSelectorTerms(Collection items) {
      if (this.nodeSelectorTerms == null) {
         this.nodeSelectorTerms = new ArrayList();
      }

      for(NodeSelectorTerm item : items) {
         NodeSelectorTermBuilder builder = new NodeSelectorTermBuilder(item);
         this._visitables.get("nodeSelectorTerms").add(builder);
         this.nodeSelectorTerms.add(builder);
      }

      return this;
   }

   public NodeSelectorFluent removeFromNodeSelectorTerms(NodeSelectorTerm... items) {
      if (this.nodeSelectorTerms == null) {
         return this;
      } else {
         for(NodeSelectorTerm item : items) {
            NodeSelectorTermBuilder builder = new NodeSelectorTermBuilder(item);
            this._visitables.get("nodeSelectorTerms").remove(builder);
            this.nodeSelectorTerms.remove(builder);
         }

         return this;
      }
   }

   public NodeSelectorFluent removeAllFromNodeSelectorTerms(Collection items) {
      if (this.nodeSelectorTerms == null) {
         return this;
      } else {
         for(NodeSelectorTerm item : items) {
            NodeSelectorTermBuilder builder = new NodeSelectorTermBuilder(item);
            this._visitables.get("nodeSelectorTerms").remove(builder);
            this.nodeSelectorTerms.remove(builder);
         }

         return this;
      }
   }

   public NodeSelectorFluent removeMatchingFromNodeSelectorTerms(Predicate predicate) {
      if (this.nodeSelectorTerms == null) {
         return this;
      } else {
         Iterator<NodeSelectorTermBuilder> each = this.nodeSelectorTerms.iterator();
         List visitables = this._visitables.get("nodeSelectorTerms");

         while(each.hasNext()) {
            NodeSelectorTermBuilder builder = (NodeSelectorTermBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildNodeSelectorTerms() {
      return this.nodeSelectorTerms != null ? build(this.nodeSelectorTerms) : null;
   }

   public NodeSelectorTerm buildNodeSelectorTerm(int index) {
      return ((NodeSelectorTermBuilder)this.nodeSelectorTerms.get(index)).build();
   }

   public NodeSelectorTerm buildFirstNodeSelectorTerm() {
      return ((NodeSelectorTermBuilder)this.nodeSelectorTerms.get(0)).build();
   }

   public NodeSelectorTerm buildLastNodeSelectorTerm() {
      return ((NodeSelectorTermBuilder)this.nodeSelectorTerms.get(this.nodeSelectorTerms.size() - 1)).build();
   }

   public NodeSelectorTerm buildMatchingNodeSelectorTerm(Predicate predicate) {
      for(NodeSelectorTermBuilder item : this.nodeSelectorTerms) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingNodeSelectorTerm(Predicate predicate) {
      for(NodeSelectorTermBuilder item : this.nodeSelectorTerms) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public NodeSelectorFluent withNodeSelectorTerms(List nodeSelectorTerms) {
      if (this.nodeSelectorTerms != null) {
         this._visitables.get("nodeSelectorTerms").clear();
      }

      if (nodeSelectorTerms != null) {
         this.nodeSelectorTerms = new ArrayList();

         for(NodeSelectorTerm item : nodeSelectorTerms) {
            this.addToNodeSelectorTerms(item);
         }
      } else {
         this.nodeSelectorTerms = null;
      }

      return this;
   }

   public NodeSelectorFluent withNodeSelectorTerms(NodeSelectorTerm... nodeSelectorTerms) {
      if (this.nodeSelectorTerms != null) {
         this.nodeSelectorTerms.clear();
         this._visitables.remove("nodeSelectorTerms");
      }

      if (nodeSelectorTerms != null) {
         for(NodeSelectorTerm item : nodeSelectorTerms) {
            this.addToNodeSelectorTerms(item);
         }
      }

      return this;
   }

   public boolean hasNodeSelectorTerms() {
      return this.nodeSelectorTerms != null && !this.nodeSelectorTerms.isEmpty();
   }

   public NodeSelectorTermsNested addNewNodeSelectorTerm() {
      return new NodeSelectorTermsNested(-1, (NodeSelectorTerm)null);
   }

   public NodeSelectorTermsNested addNewNodeSelectorTermLike(NodeSelectorTerm item) {
      return new NodeSelectorTermsNested(-1, item);
   }

   public NodeSelectorTermsNested setNewNodeSelectorTermLike(int index, NodeSelectorTerm item) {
      return new NodeSelectorTermsNested(index, item);
   }

   public NodeSelectorTermsNested editNodeSelectorTerm(int index) {
      if (this.nodeSelectorTerms.size() <= index) {
         throw new RuntimeException("Can't edit nodeSelectorTerms. Index exceeds size.");
      } else {
         return this.setNewNodeSelectorTermLike(index, this.buildNodeSelectorTerm(index));
      }
   }

   public NodeSelectorTermsNested editFirstNodeSelectorTerm() {
      if (this.nodeSelectorTerms.size() == 0) {
         throw new RuntimeException("Can't edit first nodeSelectorTerms. The list is empty.");
      } else {
         return this.setNewNodeSelectorTermLike(0, this.buildNodeSelectorTerm(0));
      }
   }

   public NodeSelectorTermsNested editLastNodeSelectorTerm() {
      int index = this.nodeSelectorTerms.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last nodeSelectorTerms. The list is empty.");
      } else {
         return this.setNewNodeSelectorTermLike(index, this.buildNodeSelectorTerm(index));
      }
   }

   public NodeSelectorTermsNested editMatchingNodeSelectorTerm(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.nodeSelectorTerms.size(); ++i) {
         if (predicate.test((NodeSelectorTermBuilder)this.nodeSelectorTerms.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching nodeSelectorTerms. No match found.");
      } else {
         return this.setNewNodeSelectorTermLike(index, this.buildNodeSelectorTerm(index));
      }
   }

   public NodeSelectorFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeSelectorFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeSelectorFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeSelectorFluent removeFromAdditionalProperties(Map map) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.additionalProperties != null) {
                  this.additionalProperties.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getAdditionalProperties() {
      return this.additionalProperties;
   }

   public NodeSelectorFluent withAdditionalProperties(Map additionalProperties) {
      if (additionalProperties == null) {
         this.additionalProperties = null;
      } else {
         this.additionalProperties = new LinkedHashMap(additionalProperties);
      }

      return this;
   }

   public boolean hasAdditionalProperties() {
      return this.additionalProperties != null;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         if (!super.equals(o)) {
            return false;
         } else {
            NodeSelectorFluent that = (NodeSelectorFluent)o;
            if (!Objects.equals(this.nodeSelectorTerms, that.nodeSelectorTerms)) {
               return false;
            } else {
               return Objects.equals(this.additionalProperties, that.additionalProperties);
            }
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Objects.hash(new Object[]{this.nodeSelectorTerms, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.nodeSelectorTerms != null && !this.nodeSelectorTerms.isEmpty()) {
         sb.append("nodeSelectorTerms:");
         sb.append(this.nodeSelectorTerms + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class NodeSelectorTermsNested extends NodeSelectorTermFluent implements Nested {
      NodeSelectorTermBuilder builder;
      int index;

      NodeSelectorTermsNested(int index, NodeSelectorTerm item) {
         this.index = index;
         this.builder = new NodeSelectorTermBuilder(this, item);
      }

      public Object and() {
         return NodeSelectorFluent.this.setToNodeSelectorTerms(this.index, this.builder.build());
      }

      public Object endNodeSelectorTerm() {
         return this.and();
      }
   }
}
