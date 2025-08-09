package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GatewayBackendTLSFluent extends BaseFluent {
   private SecretObjectReferenceBuilder clientCertificateRef;
   private Map additionalProperties;

   public GatewayBackendTLSFluent() {
   }

   public GatewayBackendTLSFluent(GatewayBackendTLS instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(GatewayBackendTLS instance) {
      instance = instance != null ? instance : new GatewayBackendTLS();
      if (instance != null) {
         this.withClientCertificateRef(instance.getClientCertificateRef());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public SecretObjectReference buildClientCertificateRef() {
      return this.clientCertificateRef != null ? this.clientCertificateRef.build() : null;
   }

   public GatewayBackendTLSFluent withClientCertificateRef(SecretObjectReference clientCertificateRef) {
      this._visitables.remove("clientCertificateRef");
      if (clientCertificateRef != null) {
         this.clientCertificateRef = new SecretObjectReferenceBuilder(clientCertificateRef);
         this._visitables.get("clientCertificateRef").add(this.clientCertificateRef);
      } else {
         this.clientCertificateRef = null;
         this._visitables.get("clientCertificateRef").remove(this.clientCertificateRef);
      }

      return this;
   }

   public boolean hasClientCertificateRef() {
      return this.clientCertificateRef != null;
   }

   public GatewayBackendTLSFluent withNewClientCertificateRef(String group, String kind, String name, String namespace) {
      return this.withClientCertificateRef(new SecretObjectReference(group, kind, name, namespace));
   }

   public ClientCertificateRefNested withNewClientCertificateRef() {
      return new ClientCertificateRefNested((SecretObjectReference)null);
   }

   public ClientCertificateRefNested withNewClientCertificateRefLike(SecretObjectReference item) {
      return new ClientCertificateRefNested(item);
   }

   public ClientCertificateRefNested editClientCertificateRef() {
      return this.withNewClientCertificateRefLike((SecretObjectReference)Optional.ofNullable(this.buildClientCertificateRef()).orElse((Object)null));
   }

   public ClientCertificateRefNested editOrNewClientCertificateRef() {
      return this.withNewClientCertificateRefLike((SecretObjectReference)Optional.ofNullable(this.buildClientCertificateRef()).orElse((new SecretObjectReferenceBuilder()).build()));
   }

   public ClientCertificateRefNested editOrNewClientCertificateRefLike(SecretObjectReference item) {
      return this.withNewClientCertificateRefLike((SecretObjectReference)Optional.ofNullable(this.buildClientCertificateRef()).orElse(item));
   }

   public GatewayBackendTLSFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public GatewayBackendTLSFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public GatewayBackendTLSFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public GatewayBackendTLSFluent removeFromAdditionalProperties(Map map) {
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

   public GatewayBackendTLSFluent withAdditionalProperties(Map additionalProperties) {
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
            GatewayBackendTLSFluent that = (GatewayBackendTLSFluent)o;
            if (!Objects.equals(this.clientCertificateRef, that.clientCertificateRef)) {
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
      return Objects.hash(new Object[]{this.clientCertificateRef, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.clientCertificateRef != null) {
         sb.append("clientCertificateRef:");
         sb.append(this.clientCertificateRef + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class ClientCertificateRefNested extends SecretObjectReferenceFluent implements Nested {
      SecretObjectReferenceBuilder builder;

      ClientCertificateRefNested(SecretObjectReference item) {
         this.builder = new SecretObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return GatewayBackendTLSFluent.this.withClientCertificateRef(this.builder.build());
      }

      public Object endClientCertificateRef() {
         return this.and();
      }
   }
}
