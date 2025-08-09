package io.fabric8.kubernetes.api.model.rbac;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class RoleBindingFluent extends BaseFluent {
   private String apiVersion;
   private String kind;
   private ObjectMetaBuilder metadata;
   private RoleRefBuilder roleRef;
   private ArrayList subjects = new ArrayList();
   private Map additionalProperties;

   public RoleBindingFluent() {
   }

   public RoleBindingFluent(RoleBinding instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(RoleBinding instance) {
      instance = instance != null ? instance : new RoleBinding();
      if (instance != null) {
         this.withApiVersion(instance.getApiVersion());
         this.withKind(instance.getKind());
         this.withMetadata(instance.getMetadata());
         this.withRoleRef(instance.getRoleRef());
         this.withSubjects(instance.getSubjects());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public String getApiVersion() {
      return this.apiVersion;
   }

   public RoleBindingFluent withApiVersion(String apiVersion) {
      this.apiVersion = apiVersion;
      return this;
   }

   public boolean hasApiVersion() {
      return this.apiVersion != null;
   }

   public String getKind() {
      return this.kind;
   }

   public RoleBindingFluent withKind(String kind) {
      this.kind = kind;
      return this;
   }

   public boolean hasKind() {
      return this.kind != null;
   }

   public ObjectMeta buildMetadata() {
      return this.metadata != null ? this.metadata.build() : null;
   }

   public RoleBindingFluent withMetadata(ObjectMeta metadata) {
      this._visitables.remove("metadata");
      if (metadata != null) {
         this.metadata = new ObjectMetaBuilder(metadata);
         this._visitables.get("metadata").add(this.metadata);
      } else {
         this.metadata = null;
         this._visitables.get("metadata").remove(this.metadata);
      }

      return this;
   }

   public boolean hasMetadata() {
      return this.metadata != null;
   }

   public MetadataNested withNewMetadata() {
      return new MetadataNested((ObjectMeta)null);
   }

   public MetadataNested withNewMetadataLike(ObjectMeta item) {
      return new MetadataNested(item);
   }

   public MetadataNested editMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((Object)null));
   }

   public MetadataNested editOrNewMetadata() {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse((new ObjectMetaBuilder()).build()));
   }

   public MetadataNested editOrNewMetadataLike(ObjectMeta item) {
      return this.withNewMetadataLike((ObjectMeta)Optional.ofNullable(this.buildMetadata()).orElse(item));
   }

   public RoleRef buildRoleRef() {
      return this.roleRef != null ? this.roleRef.build() : null;
   }

   public RoleBindingFluent withRoleRef(RoleRef roleRef) {
      this._visitables.remove("roleRef");
      if (roleRef != null) {
         this.roleRef = new RoleRefBuilder(roleRef);
         this._visitables.get("roleRef").add(this.roleRef);
      } else {
         this.roleRef = null;
         this._visitables.get("roleRef").remove(this.roleRef);
      }

      return this;
   }

   public boolean hasRoleRef() {
      return this.roleRef != null;
   }

   public RoleBindingFluent withNewRoleRef(String apiGroup, String kind, String name) {
      return this.withRoleRef(new RoleRef(apiGroup, kind, name));
   }

   public RoleRefNested withNewRoleRef() {
      return new RoleRefNested((RoleRef)null);
   }

   public RoleRefNested withNewRoleRefLike(RoleRef item) {
      return new RoleRefNested(item);
   }

   public RoleRefNested editRoleRef() {
      return this.withNewRoleRefLike((RoleRef)Optional.ofNullable(this.buildRoleRef()).orElse((Object)null));
   }

   public RoleRefNested editOrNewRoleRef() {
      return this.withNewRoleRefLike((RoleRef)Optional.ofNullable(this.buildRoleRef()).orElse((new RoleRefBuilder()).build()));
   }

   public RoleRefNested editOrNewRoleRefLike(RoleRef item) {
      return this.withNewRoleRefLike((RoleRef)Optional.ofNullable(this.buildRoleRef()).orElse(item));
   }

   public RoleBindingFluent addToSubjects(int index, Subject item) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      SubjectBuilder builder = new SubjectBuilder(item);
      if (index >= 0 && index < this.subjects.size()) {
         this._visitables.get("subjects").add(index, builder);
         this.subjects.add(index, builder);
      } else {
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public RoleBindingFluent setToSubjects(int index, Subject item) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      SubjectBuilder builder = new SubjectBuilder(item);
      if (index >= 0 && index < this.subjects.size()) {
         this._visitables.get("subjects").set(index, builder);
         this.subjects.set(index, builder);
      } else {
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public RoleBindingFluent addToSubjects(Subject... items) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      for(Subject item : items) {
         SubjectBuilder builder = new SubjectBuilder(item);
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public RoleBindingFluent addAllToSubjects(Collection items) {
      if (this.subjects == null) {
         this.subjects = new ArrayList();
      }

      for(Subject item : items) {
         SubjectBuilder builder = new SubjectBuilder(item);
         this._visitables.get("subjects").add(builder);
         this.subjects.add(builder);
      }

      return this;
   }

   public RoleBindingFluent removeFromSubjects(Subject... items) {
      if (this.subjects == null) {
         return this;
      } else {
         for(Subject item : items) {
            SubjectBuilder builder = new SubjectBuilder(item);
            this._visitables.get("subjects").remove(builder);
            this.subjects.remove(builder);
         }

         return this;
      }
   }

   public RoleBindingFluent removeAllFromSubjects(Collection items) {
      if (this.subjects == null) {
         return this;
      } else {
         for(Subject item : items) {
            SubjectBuilder builder = new SubjectBuilder(item);
            this._visitables.get("subjects").remove(builder);
            this.subjects.remove(builder);
         }

         return this;
      }
   }

   public RoleBindingFluent removeMatchingFromSubjects(Predicate predicate) {
      if (this.subjects == null) {
         return this;
      } else {
         Iterator<SubjectBuilder> each = this.subjects.iterator();
         List visitables = this._visitables.get("subjects");

         while(each.hasNext()) {
            SubjectBuilder builder = (SubjectBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildSubjects() {
      return this.subjects != null ? build(this.subjects) : null;
   }

   public Subject buildSubject(int index) {
      return ((SubjectBuilder)this.subjects.get(index)).build();
   }

   public Subject buildFirstSubject() {
      return ((SubjectBuilder)this.subjects.get(0)).build();
   }

   public Subject buildLastSubject() {
      return ((SubjectBuilder)this.subjects.get(this.subjects.size() - 1)).build();
   }

   public Subject buildMatchingSubject(Predicate predicate) {
      for(SubjectBuilder item : this.subjects) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingSubject(Predicate predicate) {
      for(SubjectBuilder item : this.subjects) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public RoleBindingFluent withSubjects(List subjects) {
      if (this.subjects != null) {
         this._visitables.get("subjects").clear();
      }

      if (subjects != null) {
         this.subjects = new ArrayList();

         for(Subject item : subjects) {
            this.addToSubjects(item);
         }
      } else {
         this.subjects = null;
      }

      return this;
   }

   public RoleBindingFluent withSubjects(Subject... subjects) {
      if (this.subjects != null) {
         this.subjects.clear();
         this._visitables.remove("subjects");
      }

      if (subjects != null) {
         for(Subject item : subjects) {
            this.addToSubjects(item);
         }
      }

      return this;
   }

   public boolean hasSubjects() {
      return this.subjects != null && !this.subjects.isEmpty();
   }

   public RoleBindingFluent addNewSubject(String apiGroup, String kind, String name, String namespace) {
      return this.addToSubjects(new Subject(apiGroup, kind, name, namespace));
   }

   public SubjectsNested addNewSubject() {
      return new SubjectsNested(-1, (Subject)null);
   }

   public SubjectsNested addNewSubjectLike(Subject item) {
      return new SubjectsNested(-1, item);
   }

   public SubjectsNested setNewSubjectLike(int index, Subject item) {
      return new SubjectsNested(index, item);
   }

   public SubjectsNested editSubject(int index) {
      if (this.subjects.size() <= index) {
         throw new RuntimeException("Can't edit subjects. Index exceeds size.");
      } else {
         return this.setNewSubjectLike(index, this.buildSubject(index));
      }
   }

   public SubjectsNested editFirstSubject() {
      if (this.subjects.size() == 0) {
         throw new RuntimeException("Can't edit first subjects. The list is empty.");
      } else {
         return this.setNewSubjectLike(0, this.buildSubject(0));
      }
   }

   public SubjectsNested editLastSubject() {
      int index = this.subjects.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last subjects. The list is empty.");
      } else {
         return this.setNewSubjectLike(index, this.buildSubject(index));
      }
   }

   public SubjectsNested editMatchingSubject(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.subjects.size(); ++i) {
         if (predicate.test((SubjectBuilder)this.subjects.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching subjects. No match found.");
      } else {
         return this.setNewSubjectLike(index, this.buildSubject(index));
      }
   }

   public RoleBindingFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public RoleBindingFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public RoleBindingFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public RoleBindingFluent removeFromAdditionalProperties(Map map) {
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

   public RoleBindingFluent withAdditionalProperties(Map additionalProperties) {
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
            RoleBindingFluent that = (RoleBindingFluent)o;
            if (!Objects.equals(this.apiVersion, that.apiVersion)) {
               return false;
            } else if (!Objects.equals(this.kind, that.kind)) {
               return false;
            } else if (!Objects.equals(this.metadata, that.metadata)) {
               return false;
            } else if (!Objects.equals(this.roleRef, that.roleRef)) {
               return false;
            } else if (!Objects.equals(this.subjects, that.subjects)) {
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
      return Objects.hash(new Object[]{this.apiVersion, this.kind, this.metadata, this.roleRef, this.subjects, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.apiVersion != null) {
         sb.append("apiVersion:");
         sb.append(this.apiVersion + ",");
      }

      if (this.kind != null) {
         sb.append("kind:");
         sb.append(this.kind + ",");
      }

      if (this.metadata != null) {
         sb.append("metadata:");
         sb.append(this.metadata + ",");
      }

      if (this.roleRef != null) {
         sb.append("roleRef:");
         sb.append(this.roleRef + ",");
      }

      if (this.subjects != null && !this.subjects.isEmpty()) {
         sb.append("subjects:");
         sb.append(this.subjects + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class MetadataNested extends ObjectMetaFluent implements Nested {
      ObjectMetaBuilder builder;

      MetadataNested(ObjectMeta item) {
         this.builder = new ObjectMetaBuilder(this, item);
      }

      public Object and() {
         return RoleBindingFluent.this.withMetadata(this.builder.build());
      }

      public Object endMetadata() {
         return this.and();
      }
   }

   public class RoleRefNested extends RoleRefFluent implements Nested {
      RoleRefBuilder builder;

      RoleRefNested(RoleRef item) {
         this.builder = new RoleRefBuilder(this, item);
      }

      public Object and() {
         return RoleBindingFluent.this.withRoleRef(this.builder.build());
      }

      public Object endRoleRef() {
         return this.and();
      }
   }

   public class SubjectsNested extends SubjectFluent implements Nested {
      SubjectBuilder builder;
      int index;

      SubjectsNested(int index, Subject item) {
         this.index = index;
         this.builder = new SubjectBuilder(this, item);
      }

      public Object and() {
         return RoleBindingFluent.this.setToSubjects(this.index, this.builder.build());
      }

      public Object endSubject() {
         return this.and();
      }
   }
}
