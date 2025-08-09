package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class EndpointAddressFluent extends BaseFluent {
   private String hostname;
   private String ip;
   private String nodeName;
   private ObjectReferenceBuilder targetRef;
   private Map additionalProperties;

   public EndpointAddressFluent() {
   }

   public EndpointAddressFluent(EndpointAddress instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EndpointAddress instance) {
      instance = instance != null ? instance : new EndpointAddress();
      if (instance != null) {
         this.withHostname(instance.getHostname());
         this.withIp(instance.getIp());
         this.withNodeName(instance.getNodeName());
         this.withTargetRef(instance.getTargetRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getHostname() {
      return this.hostname;
   }

   public EndpointAddressFluent withHostname(String hostname) {
      this.hostname = hostname;
      return this;
   }

   public boolean hasHostname() {
      return this.hostname != null;
   }

   public String getIp() {
      return this.ip;
   }

   public EndpointAddressFluent withIp(String ip) {
      this.ip = ip;
      return this;
   }

   public boolean hasIp() {
      return this.ip != null;
   }

   public String getNodeName() {
      return this.nodeName;
   }

   public EndpointAddressFluent withNodeName(String nodeName) {
      this.nodeName = nodeName;
      return this;
   }

   public boolean hasNodeName() {
      return this.nodeName != null;
   }

   public ObjectReference buildTargetRef() {
      return this.targetRef != null ? this.targetRef.build() : null;
   }

   public EndpointAddressFluent withTargetRef(ObjectReference targetRef) {
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

   public EndpointAddressFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointAddressFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointAddressFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointAddressFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointAddressFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointAddressFluent that = (EndpointAddressFluent)o;
            if (!Objects.equals(this.hostname, that.hostname)) {
               return false;
            } else if (!Objects.equals(this.ip, that.ip)) {
               return false;
            } else if (!Objects.equals(this.nodeName, that.nodeName)) {
               return false;
            } else if (!Objects.equals(this.targetRef, that.targetRef)) {
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
      return Objects.hash(new Object[]{this.hostname, this.ip, this.nodeName, this.targetRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.hostname != null) {
         sb.append("hostname:");
         sb.append(this.hostname + ",");
      }

      if (this.ip != null) {
         sb.append("ip:");
         sb.append(this.ip + ",");
      }

      if (this.nodeName != null) {
         sb.append("nodeName:");
         sb.append(this.nodeName + ",");
      }

      if (this.targetRef != null) {
         sb.append("targetRef:");
         sb.append(this.targetRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class TargetRefNested extends ObjectReferenceFluent implements Nested {
      ObjectReferenceBuilder builder;

      TargetRefNested(ObjectReference item) {
         this.builder = new ObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return EndpointAddressFluent.this.withTargetRef(this.builder.build());
      }

      public Object endTargetRef() {
         return this.and();
      }
   }
}
