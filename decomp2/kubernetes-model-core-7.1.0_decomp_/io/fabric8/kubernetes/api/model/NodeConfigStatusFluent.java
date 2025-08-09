package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class NodeConfigStatusFluent extends BaseFluent {
   private NodeConfigSourceBuilder active;
   private NodeConfigSourceBuilder assigned;
   private String error;
   private NodeConfigSourceBuilder lastKnownGood;
   private Map additionalProperties;

   public NodeConfigStatusFluent() {
   }

   public NodeConfigStatusFluent(NodeConfigStatus instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(NodeConfigStatus instance) {
      instance = instance != null ? instance : new NodeConfigStatus();
      if (instance != null) {
         this.withActive(instance.getActive());
         this.withAssigned(instance.getAssigned());
         this.withError(instance.getError());
         this.withLastKnownGood(instance.getLastKnownGood());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public NodeConfigSource buildActive() {
      return this.active != null ? this.active.build() : null;
   }

   public NodeConfigStatusFluent withActive(NodeConfigSource active) {
      this._visitables.remove("active");
      if (active != null) {
         this.active = new NodeConfigSourceBuilder(active);
         this._visitables.get("active").add(this.active);
      } else {
         this.active = null;
         this._visitables.get("active").remove(this.active);
      }

      return this;
   }

   public boolean hasActive() {
      return this.active != null;
   }

   public ActiveNested withNewActive() {
      return new ActiveNested((NodeConfigSource)null);
   }

   public ActiveNested withNewActiveLike(NodeConfigSource item) {
      return new ActiveNested(item);
   }

   public ActiveNested editActive() {
      return this.withNewActiveLike((NodeConfigSource)Optional.ofNullable(this.buildActive()).orElse((Object)null));
   }

   public ActiveNested editOrNewActive() {
      return this.withNewActiveLike((NodeConfigSource)Optional.ofNullable(this.buildActive()).orElse((new NodeConfigSourceBuilder()).build()));
   }

   public ActiveNested editOrNewActiveLike(NodeConfigSource item) {
      return this.withNewActiveLike((NodeConfigSource)Optional.ofNullable(this.buildActive()).orElse(item));
   }

   public NodeConfigSource buildAssigned() {
      return this.assigned != null ? this.assigned.build() : null;
   }

   public NodeConfigStatusFluent withAssigned(NodeConfigSource assigned) {
      this._visitables.remove("assigned");
      if (assigned != null) {
         this.assigned = new NodeConfigSourceBuilder(assigned);
         this._visitables.get("assigned").add(this.assigned);
      } else {
         this.assigned = null;
         this._visitables.get("assigned").remove(this.assigned);
      }

      return this;
   }

   public boolean hasAssigned() {
      return this.assigned != null;
   }

   public AssignedNested withNewAssigned() {
      return new AssignedNested((NodeConfigSource)null);
   }

   public AssignedNested withNewAssignedLike(NodeConfigSource item) {
      return new AssignedNested(item);
   }

   public AssignedNested editAssigned() {
      return this.withNewAssignedLike((NodeConfigSource)Optional.ofNullable(this.buildAssigned()).orElse((Object)null));
   }

   public AssignedNested editOrNewAssigned() {
      return this.withNewAssignedLike((NodeConfigSource)Optional.ofNullable(this.buildAssigned()).orElse((new NodeConfigSourceBuilder()).build()));
   }

   public AssignedNested editOrNewAssignedLike(NodeConfigSource item) {
      return this.withNewAssignedLike((NodeConfigSource)Optional.ofNullable(this.buildAssigned()).orElse(item));
   }

   public String getError() {
      return this.error;
   }

   public NodeConfigStatusFluent withError(String error) {
      this.error = error;
      return this;
   }

   public boolean hasError() {
      return this.error != null;
   }

   public NodeConfigSource buildLastKnownGood() {
      return this.lastKnownGood != null ? this.lastKnownGood.build() : null;
   }

   public NodeConfigStatusFluent withLastKnownGood(NodeConfigSource lastKnownGood) {
      this._visitables.remove("lastKnownGood");
      if (lastKnownGood != null) {
         this.lastKnownGood = new NodeConfigSourceBuilder(lastKnownGood);
         this._visitables.get("lastKnownGood").add(this.lastKnownGood);
      } else {
         this.lastKnownGood = null;
         this._visitables.get("lastKnownGood").remove(this.lastKnownGood);
      }

      return this;
   }

   public boolean hasLastKnownGood() {
      return this.lastKnownGood != null;
   }

   public LastKnownGoodNested withNewLastKnownGood() {
      return new LastKnownGoodNested((NodeConfigSource)null);
   }

   public LastKnownGoodNested withNewLastKnownGoodLike(NodeConfigSource item) {
      return new LastKnownGoodNested(item);
   }

   public LastKnownGoodNested editLastKnownGood() {
      return this.withNewLastKnownGoodLike((NodeConfigSource)Optional.ofNullable(this.buildLastKnownGood()).orElse((Object)null));
   }

   public LastKnownGoodNested editOrNewLastKnownGood() {
      return this.withNewLastKnownGoodLike((NodeConfigSource)Optional.ofNullable(this.buildLastKnownGood()).orElse((new NodeConfigSourceBuilder()).build()));
   }

   public LastKnownGoodNested editOrNewLastKnownGoodLike(NodeConfigSource item) {
      return this.withNewLastKnownGoodLike((NodeConfigSource)Optional.ofNullable(this.buildLastKnownGood()).orElse(item));
   }

   public NodeConfigStatusFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public NodeConfigStatusFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public NodeConfigStatusFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public NodeConfigStatusFluent removeFromAdditionalProperties(Map map) {
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

   public NodeConfigStatusFluent withAdditionalProperties(Map additionalProperties) {
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
            NodeConfigStatusFluent that = (NodeConfigStatusFluent)o;
            if (!Objects.equals(this.active, that.active)) {
               return false;
            } else if (!Objects.equals(this.assigned, that.assigned)) {
               return false;
            } else if (!Objects.equals(this.error, that.error)) {
               return false;
            } else if (!Objects.equals(this.lastKnownGood, that.lastKnownGood)) {
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
      return Objects.hash(new Object[]{this.active, this.assigned, this.error, this.lastKnownGood, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.active != null) {
         sb.append("active:");
         sb.append(this.active + ",");
      }

      if (this.assigned != null) {
         sb.append("assigned:");
         sb.append(this.assigned + ",");
      }

      if (this.error != null) {
         sb.append("error:");
         sb.append(this.error + ",");
      }

      if (this.lastKnownGood != null) {
         sb.append("lastKnownGood:");
         sb.append(this.lastKnownGood + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ActiveNested extends NodeConfigSourceFluent implements Nested {
      NodeConfigSourceBuilder builder;

      ActiveNested(NodeConfigSource item) {
         this.builder = new NodeConfigSourceBuilder(this, item);
      }

      public Object and() {
         return NodeConfigStatusFluent.this.withActive(this.builder.build());
      }

      public Object endActive() {
         return this.and();
      }
   }

   public class AssignedNested extends NodeConfigSourceFluent implements Nested {
      NodeConfigSourceBuilder builder;

      AssignedNested(NodeConfigSource item) {
         this.builder = new NodeConfigSourceBuilder(this, item);
      }

      public Object and() {
         return NodeConfigStatusFluent.this.withAssigned(this.builder.build());
      }

      public Object endAssigned() {
         return this.and();
      }
   }

   public class LastKnownGoodNested extends NodeConfigSourceFluent implements Nested {
      NodeConfigSourceBuilder builder;

      LastKnownGoodNested(NodeConfigSource item) {
         this.builder = new NodeConfigSourceBuilder(this, item);
      }

      public Object and() {
         return NodeConfigStatusFluent.this.withLastKnownGood(this.builder.build());
      }

      public Object endLastKnownGood() {
         return this.and();
      }
   }
}
