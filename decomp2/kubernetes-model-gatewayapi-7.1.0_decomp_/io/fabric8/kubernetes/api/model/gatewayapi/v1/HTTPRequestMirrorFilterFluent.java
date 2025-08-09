package io.fabric8.kubernetes.api.model.gatewayapi.v1;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class HTTPRequestMirrorFilterFluent extends BaseFluent {
   private BackendObjectReferenceBuilder backendRef;
   private FractionBuilder fraction;
   private Integer percent;
   private Map additionalProperties;

   public HTTPRequestMirrorFilterFluent() {
   }

   public HTTPRequestMirrorFilterFluent(HTTPRequestMirrorFilter instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(HTTPRequestMirrorFilter instance) {
      instance = instance != null ? instance : new HTTPRequestMirrorFilter();
      if (instance != null) {
         this.withBackendRef(instance.getBackendRef());
         this.withFraction(instance.getFraction());
         this.withPercent(instance.getPercent());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public BackendObjectReference buildBackendRef() {
      return this.backendRef != null ? this.backendRef.build() : null;
   }

   public HTTPRequestMirrorFilterFluent withBackendRef(BackendObjectReference backendRef) {
      this._visitables.remove("backendRef");
      if (backendRef != null) {
         this.backendRef = new BackendObjectReferenceBuilder(backendRef);
         this._visitables.get("backendRef").add(this.backendRef);
      } else {
         this.backendRef = null;
         this._visitables.get("backendRef").remove(this.backendRef);
      }

      return this;
   }

   public boolean hasBackendRef() {
      return this.backendRef != null;
   }

   public HTTPRequestMirrorFilterFluent withNewBackendRef(String group, String kind, String name, String namespace, Integer port) {
      return this.withBackendRef(new BackendObjectReference(group, kind, name, namespace, port));
   }

   public BackendRefNested withNewBackendRef() {
      return new BackendRefNested((BackendObjectReference)null);
   }

   public BackendRefNested withNewBackendRefLike(BackendObjectReference item) {
      return new BackendRefNested(item);
   }

   public BackendRefNested editBackendRef() {
      return this.withNewBackendRefLike((BackendObjectReference)Optional.ofNullable(this.buildBackendRef()).orElse((Object)null));
   }

   public BackendRefNested editOrNewBackendRef() {
      return this.withNewBackendRefLike((BackendObjectReference)Optional.ofNullable(this.buildBackendRef()).orElse((new BackendObjectReferenceBuilder()).build()));
   }

   public BackendRefNested editOrNewBackendRefLike(BackendObjectReference item) {
      return this.withNewBackendRefLike((BackendObjectReference)Optional.ofNullable(this.buildBackendRef()).orElse(item));
   }

   public Fraction buildFraction() {
      return this.fraction != null ? this.fraction.build() : null;
   }

   public HTTPRequestMirrorFilterFluent withFraction(Fraction fraction) {
      this._visitables.remove("fraction");
      if (fraction != null) {
         this.fraction = new FractionBuilder(fraction);
         this._visitables.get("fraction").add(this.fraction);
      } else {
         this.fraction = null;
         this._visitables.get("fraction").remove(this.fraction);
      }

      return this;
   }

   public boolean hasFraction() {
      return this.fraction != null;
   }

   public HTTPRequestMirrorFilterFluent withNewFraction(Integer denominator, Integer numerator) {
      return this.withFraction(new Fraction(denominator, numerator));
   }

   public FractionNested withNewFraction() {
      return new FractionNested((Fraction)null);
   }

   public FractionNested withNewFractionLike(Fraction item) {
      return new FractionNested(item);
   }

   public FractionNested editFraction() {
      return this.withNewFractionLike((Fraction)Optional.ofNullable(this.buildFraction()).orElse((Object)null));
   }

   public FractionNested editOrNewFraction() {
      return this.withNewFractionLike((Fraction)Optional.ofNullable(this.buildFraction()).orElse((new FractionBuilder()).build()));
   }

   public FractionNested editOrNewFractionLike(Fraction item) {
      return this.withNewFractionLike((Fraction)Optional.ofNullable(this.buildFraction()).orElse(item));
   }

   public Integer getPercent() {
      return this.percent;
   }

   public HTTPRequestMirrorFilterFluent withPercent(Integer percent) {
      this.percent = percent;
      return this;
   }

   public boolean hasPercent() {
      return this.percent != null;
   }

   public HTTPRequestMirrorFilterFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public HTTPRequestMirrorFilterFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public HTTPRequestMirrorFilterFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public HTTPRequestMirrorFilterFluent removeFromAdditionalProperties(Map map) {
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

   public HTTPRequestMirrorFilterFluent withAdditionalProperties(Map additionalProperties) {
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
            HTTPRequestMirrorFilterFluent that = (HTTPRequestMirrorFilterFluent)o;
            if (!Objects.equals(this.backendRef, that.backendRef)) {
               return false;
            } else if (!Objects.equals(this.fraction, that.fraction)) {
               return false;
            } else if (!Objects.equals(this.percent, that.percent)) {
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
      return Objects.hash(new Object[]{this.backendRef, this.fraction, this.percent, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.backendRef != null) {
         sb.append("backendRef:");
         sb.append(this.backendRef + ",");
      }

      if (this.fraction != null) {
         sb.append("fraction:");
         sb.append(this.fraction + ",");
      }

      if (this.percent != null) {
         sb.append("percent:");
         sb.append(this.percent + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class BackendRefNested extends BackendObjectReferenceFluent implements Nested {
      BackendObjectReferenceBuilder builder;

      BackendRefNested(BackendObjectReference item) {
         this.builder = new BackendObjectReferenceBuilder(this, item);
      }

      public Object and() {
         return HTTPRequestMirrorFilterFluent.this.withBackendRef(this.builder.build());
      }

      public Object endBackendRef() {
         return this.and();
      }
   }

   public class FractionNested extends FractionFluent implements Nested {
      FractionBuilder builder;

      FractionNested(Fraction item) {
         this.builder = new FractionBuilder(this, item);
      }

      public Object and() {
         return HTTPRequestMirrorFilterFluent.this.withFraction(this.builder.build());
      }

      public Object endFraction() {
         return this.and();
      }
   }
}
