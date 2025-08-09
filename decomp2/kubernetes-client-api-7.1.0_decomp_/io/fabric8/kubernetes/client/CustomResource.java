package io.fabric8.kubernetes.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.utils.Utils;
import io.fabric8.kubernetes.model.Scope;
import io.fabric8.kubernetes.model.annotation.Categories;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.ShortNames;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonDeserialize(
   using = JsonDeserializer.None.class
)
@JsonPropertyOrder({"apiVersion", "kind", "metadata", "spec", "status"})
@JsonInclude(Include.NON_NULL)
public abstract class CustomResource implements HasMetadata {
   private static final Logger LOG = LoggerFactory.getLogger(CustomResource.class);
   private ObjectMeta metadata = new ObjectMeta();
   @JsonProperty("spec")
   protected Object spec;
   @JsonProperty("status")
   protected Object status;
   private final String singular;
   private final String crdName;
   @JsonProperty("kind")
   private final String kind;
   @JsonProperty("apiVersion")
   private final String apiVersion;
   private final String scope;
   private final String plural;
   private final boolean served;
   private final boolean storage;
   private final boolean deprecated;
   private final String deprecationWarning;

   public CustomResource() {
      String version = super.getApiVersion();
      Class<? extends CustomResource> clazz = this.getClass();
      if (Utils.isNullOrEmpty(version)) {
         String var10002 = clazz.getName();
         throw new IllegalArgumentException(var10002 + " CustomResource must provide an API version using @" + Group.class.getName() + " and @" + io.fabric8.kubernetes.model.annotation.Version.class.getName() + " annotations");
      } else {
         this.apiVersion = version;
         this.kind = super.getKind();
         this.scope = this instanceof Namespaced ? Scope.NAMESPACED.value() : Scope.CLUSTER.value();
         this.singular = super.getSingular();
         this.plural = super.getPlural();
         this.crdName = getCRDName(clazz);
         this.served = getServed(clazz);
         this.storage = getStorage(clazz);
         this.deprecated = getDeprecated(clazz);
         this.deprecationWarning = getDeprecationWarning(clazz);
         this.spec = this.initSpec();
         this.status = this.initStatus();
      }
   }

   public static boolean getServed(Class clazz) {
      io.fabric8.kubernetes.model.annotation.Version annotation = (io.fabric8.kubernetes.model.annotation.Version)clazz.getAnnotation(io.fabric8.kubernetes.model.annotation.Version.class);
      return annotation == null || annotation.served();
   }

   public static boolean getStorage(Class clazz) {
      io.fabric8.kubernetes.model.annotation.Version annotation = (io.fabric8.kubernetes.model.annotation.Version)clazz.getAnnotation(io.fabric8.kubernetes.model.annotation.Version.class);
      return annotation == null || annotation.storage();
   }

   public static boolean getDeprecated(Class clazz) {
      io.fabric8.kubernetes.model.annotation.Version annotation = (io.fabric8.kubernetes.model.annotation.Version)clazz.getAnnotation(io.fabric8.kubernetes.model.annotation.Version.class);
      return annotation == null || annotation.deprecated();
   }

   public static String getDeprecationWarning(Class clazz) {
      io.fabric8.kubernetes.model.annotation.Version annotation = (io.fabric8.kubernetes.model.annotation.Version)clazz.getAnnotation(io.fabric8.kubernetes.model.annotation.Version.class);
      return annotation != null && Utils.isNotNullOrEmpty(annotation.deprecationWarning()) ? annotation.deprecationWarning() : null;
   }

   protected Object initSpec() {
      return null;
   }

   protected Object initStatus() {
      return null;
   }

   public String toString() {
      String var10000 = this.getKind();
      return "CustomResource{kind='" + var10000 + "', apiVersion='" + this.getApiVersion() + "', metadata=" + this.metadata + ", spec=" + this.spec + ", status=" + this.status + ", deprecated=" + this.deprecated + ", deprecationWarning=" + this.deprecationWarning + "}";
   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public void setApiVersion(String version) {
      LOG.debug("Calling CustomResource#setApiVersion doesn't do anything because the API version is computed and shouldn't be changed");
   }

   public String getKind() {
      return this.kind;
   }

   public void setKind(String kind) {
      LOG.debug("Calling CustomResource#setKind doesn't do anything because the Kind is computed and shouldn't be changed");
   }

   public ObjectMeta getMetadata() {
      return this.metadata;
   }

   public void setMetadata(ObjectMeta metadata) {
      this.metadata = metadata;
   }

   @JsonIgnore
   public String getPlural() {
      return this.plural;
   }

   @JsonIgnore
   public String getSingular() {
      return this.singular;
   }

   public static String getCRDName(Class clazz) {
      return HasMetadata.getFullResourceName(clazz);
   }

   @JsonIgnore
   public String getCRDName() {
      return this.crdName;
   }

   public static String[] getShortNames(Class clazz) {
      return (String[])Optional.ofNullable((ShortNames)clazz.getAnnotation(ShortNames.class)).map(ShortNames::value).orElse(new String[0]);
   }

   public static String[] getCategories(Class clazz) {
      return (String[])Optional.ofNullable((Categories)clazz.getAnnotation(Categories.class)).map(Categories::value).orElse(new String[0]);
   }

   @JsonIgnore
   public String getScope() {
      return this.scope;
   }

   @JsonIgnore
   public String getGroup() {
      return HasMetadata.getGroup(this.getClass());
   }

   @JsonIgnore
   public String getVersion() {
      return HasMetadata.getVersion(this.getClass());
   }

   @JsonIgnore
   public boolean isServed() {
      return this.served;
   }

   @JsonIgnore
   public boolean isStorage() {
      return this.storage;
   }

   @JsonIgnore
   public boolean isDeprecated() {
      return this.deprecated;
   }

   @JsonIgnore
   public String getDeprecationWarning() {
      return this.deprecationWarning;
   }

   public Object getSpec() {
      return this.spec;
   }

   public void setSpec(Object spec) {
      this.spec = spec;
   }

   public Object getStatus() {
      return this.status;
   }

   public void setStatus(Object status) {
      this.status = status;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof CustomResource)) {
         return false;
      } else {
         CustomResource<?, ?> that = (CustomResource)o;
         if (this.served != that.served) {
            return false;
         } else if (this.storage != that.storage) {
            return false;
         } else if (this.deprecated != that.deprecated) {
            return false;
         } else if (!Objects.equals(this.deprecationWarning, that.deprecationWarning)) {
            return false;
         } else if (!this.metadata.equals(that.metadata)) {
            return false;
         } else if (!Objects.equals(this.spec, that.spec)) {
            return false;
         } else if (!Objects.equals(this.status, that.status)) {
            return false;
         } else if (!this.singular.equals(that.singular)) {
            return false;
         } else if (!this.crdName.equals(that.crdName)) {
            return false;
         } else if (!this.kind.equals(that.kind)) {
            return false;
         } else if (!this.apiVersion.equals(that.apiVersion)) {
            return false;
         } else {
            return !this.scope.equals(that.scope) ? false : this.plural.equals(that.plural);
         }
      }
   }

   public int hashCode() {
      int result = this.metadata.hashCode();
      result = 31 * result + (this.spec != null ? this.spec.hashCode() : 0);
      result = 31 * result + (this.status != null ? this.status.hashCode() : 0);
      result = 31 * result + this.singular.hashCode();
      result = 31 * result + this.crdName.hashCode();
      result = 31 * result + this.kind.hashCode();
      result = 31 * result + this.apiVersion.hashCode();
      result = 31 * result + this.scope.hashCode();
      result = 31 * result + this.plural.hashCode();
      result = 31 * result + (this.served ? 1 : 0);
      result = 31 * result + (this.storage ? 1 : 0);
      result = 31 * result + (this.deprecated ? 1 : 0);
      result = 31 * result + (this.deprecationWarning != null ? this.deprecationWarning.hashCode() : 0);
      return result;
   }
}
