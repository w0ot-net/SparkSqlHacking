package io.fabric8.kubernetes.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.fabric8.kubernetes.api.Pluralize;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Kind;
import io.fabric8.kubernetes.model.annotation.Plural;
import io.fabric8.kubernetes.model.annotation.Singular;
import io.fabric8.kubernetes.model.annotation.Version;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface HasMetadata extends KubernetesResource {
   String DNS_LABEL_START = "(?!-)[A-Za-z0-9-]{";
   String DNS_LABEL_END = ",63}(?<!-)";
   String DNS_LABEL_REGEXP = "(?!-)[A-Za-z0-9-]{1,63}(?<!-)";
   Pattern FINALIZER_NAME_MATCHER = Pattern.compile("^(((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+(?!-)[A-Za-z0-9-]{2,63}(?<!-))/(?!-)[A-Za-z0-9-]{1,63}(?<!-)");
   String REQUIRES_NON_NULL_METADATA = "requires non-null metadata";
   String REQUIRES_NON_NULL_NAME = "requires non-null name";
   String REQUIRES_NON_NULL_NAMESPACE = "requires non-null namespace";

   static String getKind(Class clazz) {
      Kind kind = (Kind)clazz.getAnnotation(Kind.class);
      return kind != null ? kind.value() : clazz.getSimpleName();
   }

   static String getApiVersion(Class clazz) {
      String group = getGroup(clazz);
      String version = getVersion(clazz);
      if (group != null && version != null) {
         return group + "/" + version;
      } else if (group == null && version == null) {
         return null;
      } else {
         String var10002 = Group.class.getSimpleName();
         throw new IllegalArgumentException("You need to specify both @" + var10002 + " and @" + Version.class.getSimpleName() + " annotations if you specify either");
      }
   }

   static String getGroup(Class clazz) {
      Group group = (Group)clazz.getAnnotation(Group.class);
      return group != null ? group.value() : null;
   }

   static String getVersion(Class clazz) {
      Version version = (Version)clazz.getAnnotation(Version.class);
      return version != null ? version.value() : null;
   }

   static String getPlural(Class clazz) {
      Plural fromAnnotation = (Plural)clazz.getAnnotation(Plural.class);
      return fromAnnotation != null ? fromAnnotation.value().toLowerCase(Locale.ROOT) : Pluralize.toPlural(getSingular(clazz));
   }

   static String getSingular(Class clazz) {
      Singular fromAnnotation = (Singular)clazz.getAnnotation(Singular.class);
      return (fromAnnotation != null ? fromAnnotation.value() : getKind(clazz)).toLowerCase(Locale.ROOT);
   }

   static String getFullResourceName(Class clazz) {
      String plural = getPlural(clazz);
      String group = getGroup(clazz);
      if (group == null) {
         String var10002 = clazz.getName();
         throw new IllegalArgumentException("Should provide non-null group. Is " + var10002 + " properly annotated with @" + Group.class.getSimpleName() + " and/or @" + Version.class.getSimpleName() + "?");
      } else {
         return getFullResourceName(plural, group);
      }
   }

   static String getFullResourceName(String plural, String group) {
      Objects.requireNonNull(plural);
      Objects.requireNonNull(group);
      return group.isEmpty() ? plural : plural + "." + group;
   }

   static boolean validateFinalizer(String finalizer) {
      if (finalizer == null) {
         return false;
      } else {
         Matcher matcher = FINALIZER_NAME_MATCHER.matcher(finalizer);
         if (matcher.matches()) {
            String group = matcher.group(1);
            return group.length() < 256;
         } else {
            return false;
         }
      }
   }

   static OwnerReference sanitizeAndValidate(OwnerReference ownerReference) {
      StringBuilder error = new StringBuilder(100);
      error.append("Owner is missing required field(s): ");
      BiFunction<String, String, Optional<String>> trimmedFieldIfValid = (field, value) -> {
         boolean isError = false;
         if (value == null) {
            isError = true;
         } else {
            value = value.trim();
            if (value.isEmpty()) {
               isError = true;
            }
         }

         if (isError) {
            error.append(field).append(" ");
            return Optional.empty();
         } else {
            return Optional.of(value);
         }
      };
      Supplier<IllegalArgumentException> exceptionSupplier = () -> new IllegalArgumentException(error.toString());
      Optional<String> uid = (Optional)trimmedFieldIfValid.apply("uid", ownerReference.getUid());
      Optional<String> apiVersion = (Optional)trimmedFieldIfValid.apply("apiVersion", ownerReference.getApiVersion());
      Optional<String> name = (Optional)trimmedFieldIfValid.apply("name", ownerReference.getName());
      Optional<String> kind = (Optional)trimmedFieldIfValid.apply("kind", ownerReference.getKind());
      ownerReference = ((OwnerReferenceBuilder)((OwnerReferenceBuilder)((OwnerReferenceBuilder)((OwnerReferenceBuilder)(new OwnerReferenceBuilder(ownerReference)).withUid((String)uid.orElseThrow(exceptionSupplier))).withApiVersion((String)apiVersion.orElseThrow(exceptionSupplier))).withName((String)name.orElseThrow(exceptionSupplier))).withKind((String)kind.orElseThrow(exceptionSupplier))).build();
      return ownerReference;
   }

   ObjectMeta getMetadata();

   void setMetadata(ObjectMeta var1);

   default String getKind() {
      return getKind(this.getClass());
   }

   default String getApiVersion() {
      return getApiVersion(this.getClass());
   }

   void setApiVersion(String var1);

   @JsonIgnore
   default String getPlural() {
      return getPlural(this.getClass());
   }

   @JsonIgnore
   default String getSingular() {
      return getSingular(this.getClass());
   }

   @JsonIgnore
   default String getFullResourceName() {
      return getFullResourceName(this.getClass());
   }

   @JsonIgnore
   default boolean isMarkedForDeletion() {
      String deletionTimestamp = (String)this.optionalMetadata().map(ObjectMeta::getDeletionTimestamp).orElse((Object)null);
      return deletionTimestamp != null && !deletionTimestamp.isEmpty();
   }

   default boolean hasFinalizer(String finalizer) {
      return this.getFinalizers().contains(finalizer);
   }

   @JsonIgnore
   default List getFinalizers() {
      return (List)this.optionalMetadata().map(ObjectMeta::getFinalizers).orElse(Collections.emptyList());
   }

   default boolean addFinalizer(String finalizer) {
      if (finalizer != null && !finalizer.trim().isEmpty()) {
         if (!this.isMarkedForDeletion() && !this.hasFinalizer(finalizer)) {
            if (this.isFinalizerValid(finalizer)) {
               ObjectMeta metadata = this.getMetadata();
               if (metadata == null) {
                  metadata = new ObjectMeta();
                  this.setMetadata(metadata);
               }

               return metadata.getFinalizers().add(finalizer);
            } else {
               throw new IllegalArgumentException("Invalid finalizer name: '" + finalizer + "'. Must consist of a domain name, a forward slash and the valid kubernetes name.");
            }
         } else {
            return false;
         }
      } else {
         throw new IllegalArgumentException("Must pass a non-null, non-blank finalizer.");
      }
   }

   default boolean isFinalizerValid(String finalizer) {
      return validateFinalizer(finalizer);
   }

   default boolean removeFinalizer(String finalizer) {
      List<String> finalizers = this.getFinalizers();
      return finalizers.contains(finalizer) && finalizers.remove(finalizer);
   }

   default boolean hasOwnerReferenceFor(HasMetadata owner) {
      return this.getOwnerReferenceFor(owner).isPresent();
   }

   default boolean hasOwnerReferenceFor(String ownerUid) {
      return this.getOwnerReferenceFor(ownerUid).isPresent();
   }

   default Optional getOwnerReferenceFor(HasMetadata owner) {
      if (owner == null) {
         return Optional.empty();
      } else {
         String ownerUID = (String)owner.optionalMetadata().map(ObjectMeta::getUid).orElse((Object)null);
         return this.getOwnerReferenceFor(ownerUID);
      }
   }

   default Optional getOwnerReferenceFor(String ownerUid) {
      return ownerUid != null && !ownerUid.isEmpty() ? ((List)this.optionalMetadata().map((m) -> (List)Optional.ofNullable(m.getOwnerReferences()).orElse(Collections.emptyList())).orElse(Collections.emptyList())).stream().filter((o) -> ownerUid.equals(o.getUid())).findFirst() : Optional.empty();
   }

   default OwnerReference addOwnerReference(HasMetadata owner) {
      if (owner == null) {
         String var6 = (String)this.optionalMetadata().map((m) -> "'" + m.getName() + "' ").orElse("unnamed ");
         throw new IllegalArgumentException("Cannot add a reference to a null owner to " + var6 + this.getKind());
      } else {
         ObjectMeta metadata = owner.getMetadata();
         if (metadata == null) {
            String var5 = (String)this.optionalMetadata().map((m) -> "'" + m.getName() + "' ").orElse("unnamed ");
            throw new IllegalArgumentException("Cannot add a reference to an owner without metadata to " + var5 + this.getKind());
         } else {
            if (!(owner instanceof GenericKubernetesResource) && !(this instanceof GenericKubernetesResource) && owner instanceof Namespaced) {
               if (!(this instanceof Namespaced)) {
                  String var4 = (String)this.optionalMetadata().map((m) -> "'" + m.getName() + "' ").orElse("unnamed ");
                  throw new IllegalArgumentException("Cannot add owner reference from a cluster scoped to a namespace scoped resource: " + var4 + this.getKind());
               }

               if ((Boolean)this.optionalMetadata().map((m) -> !Objects.equals(m.getNamespace(), owner.getMetadata().getNamespace())).orElse(false)) {
                  String var10002 = (String)this.optionalMetadata().map((m) -> "'" + m.getName() + "' ").orElse("unnamed ");
                  throw new IllegalArgumentException("Cannot add owner reference between two resources in different namespaces:" + var10002 + this.getKind());
               }
            }

            OwnerReference ownerReference = ((OwnerReferenceBuilder)((OwnerReferenceBuilder)((OwnerReferenceBuilder)((OwnerReferenceBuilder)(new OwnerReferenceBuilder()).withUid(metadata.getUid())).withApiVersion(owner.getApiVersion())).withName(metadata.getName())).withKind(owner.getKind())).build();
            return this.addOwnerReference(sanitizeAndValidate(ownerReference));
         }
      }
   }

   default OwnerReference addOwnerReference(OwnerReference ownerReference) {
      if (ownerReference == null) {
         String var10002 = (String)this.optionalMetadata().map((m) -> "'" + m.getName() + "' ").orElse("unnamed ");
         throw new IllegalArgumentException("Cannot add a null reference to " + var10002 + this.getKind());
      } else {
         Optional<OwnerReference> existing = this.getOwnerReferenceFor(ownerReference.getUid());
         if (existing.isPresent()) {
            return (OwnerReference)existing.get();
         } else {
            ObjectMeta metadata = this.getMetadata();
            if (metadata == null) {
               metadata = new ObjectMeta();
               this.setMetadata(metadata);
            }

            List<OwnerReference> ownerReferences = metadata.getOwnerReferences();
            if (ownerReferences == null) {
               ownerReferences = new ArrayList();
               metadata.setOwnerReferences(ownerReferences);
            }

            ownerReferences.add(ownerReference);
            return ownerReference;
         }
      }
   }

   default void removeOwnerReference(String ownerUid) {
      if (ownerUid != null && !ownerUid.isEmpty()) {
         ((List)this.optionalMetadata().map((m) -> (List)Optional.ofNullable(m.getOwnerReferences()).orElse(Collections.emptyList())).orElse(Collections.emptyList())).removeIf((o) -> ownerUid.equals(o.getUid()));
      }

   }

   default void removeOwnerReference(HasMetadata owner) {
      if (owner != null) {
         this.removeOwnerReference(owner.getMetadata().getUid());
      }

   }

   default Optional optionalMetadata() {
      return Optional.ofNullable(this.getMetadata());
   }

   default void initNameAndNamespaceFrom(HasMetadata original) {
      Objects.requireNonNull(original);
      ObjectMeta meta = initMetadataBuilderNameAndNamespaceFrom(original).build();
      this.setMetadata(meta);
   }

   static ObjectMetaBuilder initMetadataBuilderNameAndNamespaceFrom(HasMetadata original) {
      Objects.requireNonNull(original);
      ObjectMeta metadata = (ObjectMeta)Objects.requireNonNull(original.getMetadata(), "requires non-null metadata");
      ObjectMetaBuilder metaBuilder = new ObjectMetaBuilder();
      metaBuilder.withName((String)Objects.requireNonNull(metadata.getName(), "requires non-null name"));
      if (original instanceof Namespaced) {
         metaBuilder.withNamespace((String)Objects.requireNonNull(metadata.getNamespace(), "requires non-null namespace"));
      }

      return metaBuilder;
   }
}
