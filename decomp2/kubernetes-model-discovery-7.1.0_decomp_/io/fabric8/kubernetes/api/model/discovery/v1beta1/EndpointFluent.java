package io.fabric8.kubernetes.api.model.discovery.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectReference;
import io.fabric8.kubernetes.api.model.ObjectReferenceBuilder;
import io.fabric8.kubernetes.api.model.ObjectReferenceFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class EndpointFluent extends BaseFluent {
   private List addresses = new ArrayList();
   private EndpointConditionsBuilder conditions;
   private EndpointHintsBuilder hints;
   private String hostname;
   private String nodeName;
   private ObjectReferenceBuilder targetRef;
   private Map topology;
   private Map additionalProperties;

   public EndpointFluent() {
   }

   public EndpointFluent(Endpoint instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(Endpoint instance) {
      instance = instance != null ? instance : new Endpoint();
      if (instance != null) {
         this.withAddresses(instance.getAddresses());
         this.withConditions(instance.getConditions());
         this.withHints(instance.getHints());
         this.withHostname(instance.getHostname());
         this.withNodeName(instance.getNodeName());
         this.withTargetRef(instance.getTargetRef());
         this.withTopology(instance.getTopology());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public EndpointFluent addToAddresses(int index, String item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      this.addresses.add(index, item);
      return this;
   }

   public EndpointFluent setToAddresses(int index, String item) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      this.addresses.set(index, item);
      return this;
   }

   public EndpointFluent addToAddresses(String... items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(String item : items) {
         this.addresses.add(item);
      }

      return this;
   }

   public EndpointFluent addAllToAddresses(Collection items) {
      if (this.addresses == null) {
         this.addresses = new ArrayList();
      }

      for(String item : items) {
         this.addresses.add(item);
      }

      return this;
   }

   public EndpointFluent removeFromAddresses(String... items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(String item : items) {
            this.addresses.remove(item);
         }

         return this;
      }
   }

   public EndpointFluent removeAllFromAddresses(Collection items) {
      if (this.addresses == null) {
         return this;
      } else {
         for(String item : items) {
            this.addresses.remove(item);
         }

         return this;
      }
   }

   public List getAddresses() {
      return this.addresses;
   }

   public String getAddress(int index) {
      return (String)this.addresses.get(index);
   }

   public String getFirstAddress() {
      return (String)this.addresses.get(0);
   }

   public String getLastAddress() {
      return (String)this.addresses.get(this.addresses.size() - 1);
   }

   public String getMatchingAddress(Predicate predicate) {
      for(String item : this.addresses) {
         if (predicate.test(item)) {
            return item;
         }
      }

      return null;
   }

   public boolean hasMatchingAddress(Predicate predicate) {
      for(String item : this.addresses) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public EndpointFluent withAddresses(List addresses) {
      if (addresses != null) {
         this.addresses = new ArrayList();

         for(String item : addresses) {
            this.addToAddresses(item);
         }
      } else {
         this.addresses = null;
      }

      return this;
   }

   public EndpointFluent withAddresses(String... addresses) {
      if (this.addresses != null) {
         this.addresses.clear();
         this._visitables.remove("addresses");
      }

      if (addresses != null) {
         for(String item : addresses) {
            this.addToAddresses(item);
         }
      }

      return this;
   }

   public boolean hasAddresses() {
      return this.addresses != null && !this.addresses.isEmpty();
   }

   public EndpointConditions buildConditions() {
      return this.conditions != null ? this.conditions.build() : null;
   }

   public EndpointFluent withConditions(EndpointConditions conditions) {
      this._visitables.remove("conditions");
      if (conditions != null) {
         this.conditions = new EndpointConditionsBuilder(conditions);
         this._visitables.get("conditions").add(this.conditions);
      } else {
         this.conditions = null;
         this._visitables.get("conditions").remove(this.conditions);
      }

      return this;
   }

   public boolean hasConditions() {
      return this.conditions != null;
   }

   public EndpointFluent withNewConditions(Boolean ready, Boolean serving, Boolean terminating) {
      return this.withConditions(new EndpointConditions(ready, serving, terminating));
   }

   public ConditionsNested withNewConditions() {
      return new ConditionsNested((EndpointConditions)null);
   }

   public ConditionsNested withNewConditionsLike(EndpointConditions item) {
      return new ConditionsNested(item);
   }

   public ConditionsNested editConditions() {
      return this.withNewConditionsLike((EndpointConditions)Optional.ofNullable(this.buildConditions()).orElse((Object)null));
   }

   public ConditionsNested editOrNewConditions() {
      return this.withNewConditionsLike((EndpointConditions)Optional.ofNullable(this.buildConditions()).orElse((new EndpointConditionsBuilder()).build()));
   }

   public ConditionsNested editOrNewConditionsLike(EndpointConditions item) {
      return this.withNewConditionsLike((EndpointConditions)Optional.ofNullable(this.buildConditions()).orElse(item));
   }

   public EndpointHints buildHints() {
      return this.hints != null ? this.hints.build() : null;
   }

   public EndpointFluent withHints(EndpointHints hints) {
      this._visitables.remove("hints");
      if (hints != null) {
         this.hints = new EndpointHintsBuilder(hints);
         this._visitables.get("hints").add(this.hints);
      } else {
         this.hints = null;
         this._visitables.get("hints").remove(this.hints);
      }

      return this;
   }

   public boolean hasHints() {
      return this.hints != null;
   }

   public HintsNested withNewHints() {
      return new HintsNested((EndpointHints)null);
   }

   public HintsNested withNewHintsLike(EndpointHints item) {
      return new HintsNested(item);
   }

   public HintsNested editHints() {
      return this.withNewHintsLike((EndpointHints)Optional.ofNullable(this.buildHints()).orElse((Object)null));
   }

   public HintsNested editOrNewHints() {
      return this.withNewHintsLike((EndpointHints)Optional.ofNullable(this.buildHints()).orElse((new EndpointHintsBuilder()).build()));
   }

   public HintsNested editOrNewHintsLike(EndpointHints item) {
      return this.withNewHintsLike((EndpointHints)Optional.ofNullable(this.buildHints()).orElse(item));
   }

   public String getHostname() {
      return this.hostname;
   }

   public EndpointFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public String getNodeName() {
      return this.nodeName;
   }

   public EndpointFluent withNodeName(String nodeName) {
      this.nodeName = nodeName;
      return this;
   }

   public boolean hasNodeName() {
      return this.nodeName != null;
   }

   public ObjectReference buildTargetRef() {
      return this.targetRef != null ? this.targetRef.build() : null;
   }

   public EndpointFluent withTargetRef(ObjectReference targetRef) {
      this._visitables.remove("targetRef");
      if (targetRef != null) {
         this.targetRef = new ObjectReferenceBuilder(targetRef);
         this._visitables.get("targetRef").add(this.targetRef);
      } else {
         this.targetRef = null;
         this._visitables.get("targetRef").remove(this.targetRef);
      }

      return this;
   }

   public boolean hasTargetRef() {
      return this.targetRef != null;
   }

   public TargetRefNested withNewTargetRef() {
      return new TargetRefNested((ObjectReference)null);
   }

   public TargetRefNested withNewTargetRefLike(ObjectReference item) {
      return new TargetRefNested(item);
   }

   public TargetRefNested editTargetRef() {
      return this.withNewTargetRefLike((ObjectReference)Optional.ofNullable(this.buildTargetRef()).orElse((Object)null));
   }

   public TargetRefNested editOrNewTargetRef() {
      return this.withNewTargetRefLike((ObjectReference)Optional.ofNullable(this.buildTargetRef()).orElse((new ObjectReferenceBuilder()).build()));
   }

   public TargetRefNested editOrNewTargetRefLike(ObjectReference item) {
      return this.withNewTargetRefLike((ObjectReference)Optional.ofNullable(this.buildTargetRef()).orElse(item));
   }

   public EndpointFluent addToTopology(String key, String value) {
      if (this.topology == null && key != null && value != null) {
         this.topology = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.topology.put(key, value);
      }

      return this;
   }

   public EndpointFluent addToTopology(Map map) {
      if (this.topology == null && map != null) {
         this.topology = new LinkedHashMap();
      }

      if (map != null) {
         this.topology.putAll(map);
      }

      return this;
   }

   public EndpointFluent removeFromTopology(String key) {
      if (this.topology == null) {
         return this;
      } else {
         if (key != null && this.topology != null) {
            this.topology.remove(key);
         }

         return this;
      }
   }

   public EndpointFluent removeFromTopology(Map map) {
      if (this.topology == null) {
         return this;
      } else {
         if (map != null) {
            for(Object key : map.keySet()) {
               if (this.topology != null) {
                  this.topology.remove(key);
               }
            }
         }

         return this;
      }
   }

   public Map getTopology() {
      return this.topology;
   }

   public EndpointFluent withTopology(Map topology) {
      if (topology == null) {
         this.topology = null;
      } else {
         this.topology = new LinkedHashMap(topology);
      }

      return this;
   }

   public boolean hasTopology() {
      return this.topology != null;
   }

   public EndpointFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointFluent that = (EndpointFluent)o;
            if (!Objects.equals(this.addresses, that.addresses)) {
               return false;
            } else if (!Objects.equals(this.conditions, that.conditions)) {
               return false;
            } else if (!Objects.equals(this.hints, that.hints)) {
               return false;
            } else if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.nodeName, that.nodeName)) {
               return false;
            } else if (!Objects.equals(this.targetRef, that.targetRef)) {
               return false;
            } else if (!Objects.equals(this.topology, that.topology)) {
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
      return Objects.hash(new Object[]{this.addresses, this.conditions, this.hints, this.hostname, this.nodeName, this.targetRef, this.topology, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.addresses != null && !this.addresses.isEmpty()) {
         sb.append("addresses:");
         sb.append(this.addresses + ",");
      }

      if (this.conditions != null) {
         sb.append("conditions:");
         sb.append(this.conditions + ",");
      }

      if (this.hints != null) {
         sb.append("hints:");
         sb.append(this.hints + ",");
      }

      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.nodeName != null) {
         sb.append("nodeName:");
         sb.append(this.nodeName + ",");
      }

      if (this.targetRef != null) {
         sb.append("targetRef:");
         sb.append(this.targetRef + ",");
      }

      if (this.topology != null && !this.topology.isEmpty()) {
         sb.append("topology:");
         sb.append(this.topology + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ConditionsNested extends EndpointConditionsFluent implements Nested {
      EndpointConditionsBuilder builder;

      ConditionsNested(EndpointConditions item) {
         this.builder = new EndpointConditionsBuilder(this, item);
      }

      public Object and() {
         return EndpointFluent.this.withConditions(this.builder.build());
      }

      public Object endConditions() {
         return this.and();
      }
   }

   public class HintsNested extends EndpointHintsFluent implements Nested {
      EndpointHintsBuilder builder;

      HintsNested(EndpointHints item) {
         this.builder = new EndpointHintsBuilder(this, item);
      }

      public Object and() {
         return EndpointFluent.this.withHints(this.builder.build());
      }

      public Object endHints() {
         return this.and();
      }
   }

   public class TargetRefNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      TargetRefNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return EndpointFluent.this.withTargetRef(this.builder.build());
      }

      public Object endTargetRef() {
         return this.and();
      }
   }
}
