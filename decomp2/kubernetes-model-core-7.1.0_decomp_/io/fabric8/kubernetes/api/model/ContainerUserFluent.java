package io.fabric8.kubernetes.api.model;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ContainerUserFluent extends BaseFluent {
   private LinuxContainerUserBuilder linux;
   private Map additionalProperties;

   public ContainerUserFluent() {
   }

   public ContainerUserFluent(ContainerUser instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(ContainerUser instance) {
      instance = instance != null ? instance : new ContainerUser();
      if (instance != null) {
         this.withLinux(instance.getLinux());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public LinuxContainerUser buildLinux() {
      return this.linux != null ? this.linux.build() : null;
   }

   public ContainerUserFluent withLinux(LinuxContainerUser linux) {
      this._visitables.remove("linux");
      if (linux != null) {
         this.linux = new LinuxContainerUserBuilder(linux);
         this._visitables.get("linux").add(this.linux);
      } else {
         this.linux = null;
         this._visitables.get("linux").remove(this.linux);
      }

      return this;
   }

   public boolean hasLinux() {
      return this.linux != null;
   }

   public LinuxNested withNewLinux() {
      return new LinuxNested((LinuxContainerUser)null);
   }

   public LinuxNested withNewLinuxLike(LinuxContainerUser item) {
      return new LinuxNested(item);
   }

   public LinuxNested editLinux() {
      return this.withNewLinuxLike((LinuxContainerUser)Optional.ofNullable(this.buildLinux()).orElse((Object)null));
   }

   public LinuxNested editOrNewLinux() {
      return this.withNewLinuxLike((LinuxContainerUser)Optional.ofNullable(this.buildLinux()).orElse((new LinuxContainerUserBuilder()).build()));
   }

   public LinuxNested editOrNewLinuxLike(LinuxContainerUser item) {
      return this.withNewLinuxLike((LinuxContainerUser)Optional.ofNullable(this.buildLinux()).orElse(item));
   }

   public ContainerUserFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public ContainerUserFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public ContainerUserFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public ContainerUserFluent removeFromAdditionalProperties(Map map) {
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

   public ContainerUserFluent withAdditionalProperties(Map additionalProperties) {
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
            ContainerUserFluent that = (ContainerUserFluent)o;
            if (!Objects.equals(this.linux, that.linux)) {
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
      return Objects.hash(new Object[]{this.linux, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.linux != null) {
         sb.append("linux:");
         sb.append(this.linux + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class LinuxNested extends LinuxContainerUserFluent implements Nested {
      LinuxContainerUserBuilder builder;

      LinuxNested(LinuxContainerUser item) {
         this.builder = new LinuxContainerUserBuilder(this, item);
      }

      public Object and() {
         return ContainerUserFluent.this.withLinux(this.builder.build());
      }

      public Object endLinux() {
         return this.and();
      }
   }
}
