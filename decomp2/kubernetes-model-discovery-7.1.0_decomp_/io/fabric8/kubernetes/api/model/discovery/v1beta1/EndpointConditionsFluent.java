package io.fabric8.kubernetes.api.model.discovery.v1beta1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class EndpointConditionsFluent extends BaseFluent {
   private Boolean ready;
   private Boolean serving;
   private Boolean terminating;
   private Map additionalProperties;

   public EndpointConditionsFluent() {
   }

   public EndpointConditionsFluent(EndpointConditions instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(EndpointConditions instance) {
      instance = instance != null ? instance : new EndpointConditions();
      if (instance != null) {
         this.withReady(instance.getReady());
         this.withServing(instance.getServing());
         this.withTerminating(instance.getTerminating());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public Boolean getReady() {
      return this.ready;
   }

   public EndpointConditionsFluent withReady(Boolean ready) {
      this.ready = ready;
      return this;
   }

   public boolean hasReady() {
      return this.ready != null;
   }

   public Boolean getServing() {
      return this.serving;
   }

   public EndpointConditionsFluent withServing(Boolean serving) {
      this.serving = serving;
      return this;
   }

   public boolean hasServing() {
      return this.serving != null;
   }

   public Boolean getTerminating() {
      return this.terminating;
   }

   public EndpointConditionsFluent withTerminating(Boolean terminating) {
      this.terminating = terminating;
      return this;
   }

   public boolean hasTerminating() {
      return this.terminating != null;
   }

   public EndpointConditionsFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public EndpointConditionsFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public EndpointConditionsFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public EndpointConditionsFluent removeFromAdditionalProperties(Map map) {
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

   public EndpointConditionsFluent withAdditionalProperties(Map additionalProperties) {
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
            EndpointConditionsFluent that = (EndpointConditionsFluent)o;
            if (!Objects.equals(this.ready, that.ready)) {
               return false;
            } else if (!Objects.equals(this.serving, that.serving)) {
               return false;
            } else if (!Objects.equals(this.terminating, that.terminating)) {
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
      return Objects.hash(new Object[]{this.ready, this.serving, this.terminating, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.ready != null) {
         sb.append("ready:");
         sb.append(this.ready + ",");
      }

      if (this.serving != null) {
         sb.append("serving:");
         sb.append(this.serving + ",");
      }

      if (this.terminating != null) {
         sb.append("terminating:");
         sb.append(this.terminating + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public EndpointConditionsFluent withReady() {
      return this.withReady(true);
   }

   public EndpointConditionsFluent withServing() {
      return this.withServing(true);
   }

   public EndpointConditionsFluent withTerminating() {
      return this.withTerminating(true);
   }
}
