package io.fabric8.kubernetes.api.model.gatewayapi.v1alpha2;

import io.fabric8.kubernetes.api.builder.BaseFluent;
import io.fabric8.kubernetes.api.builder.Nested;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.SessionPersistence;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.SessionPersistenceBuilder;
import io.fabric8.kubernetes.api.model.gatewayapi.v1.SessionPersistenceFluent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

public class BackendLBPolicySpecFluent extends BaseFluent {
   private SessionPersistenceBuilder sessionPersistence;
   private ArrayList targetRefs = new ArrayList();
   private Map additionalProperties;

   public BackendLBPolicySpecFluent() {
   }

   public BackendLBPolicySpecFluent(BackendLBPolicySpec instance) {
      this.copyInstance(instance);
   }

   protected void copyInstance(BackendLBPolicySpec instance) {
      instance = instance != null ? instance : new BackendLBPolicySpec();
      if (instance != null) {
         this.withSessionPersistence(instance.getSessionPersistence());
         this.withTargetRefs(instance.getTargetRefs());
         this.withAdditionalProperties(instance.getAdditionalProperties());
      }

   }

   public SessionPersistence buildSessionPersistence() {
      return this.sessionPersistence != null ? this.sessionPersistence.build() : null;
   }

   public BackendLBPolicySpecFluent withSessionPersistence(SessionPersistence sessionPersistence) {
      this._visitables.remove("sessionPersistence");
      if (sessionPersistence != null) {
         this.sessionPersistence = new SessionPersistenceBuilder(sessionPersistence);
         this._visitables.get("sessionPersistence").add(this.sessionPersistence);
      } else {
         this.sessionPersistence = null;
         this._visitables.get("sessionPersistence").remove(this.sessionPersistence);
      }

      return this;
   }

   public boolean hasSessionPersistence() {
      return this.sessionPersistence != null;
   }

   public SessionPersistenceNested withNewSessionPersistence() {
      return new SessionPersistenceNested((SessionPersistence)null);
   }

   public SessionPersistenceNested withNewSessionPersistenceLike(SessionPersistence item) {
      return new SessionPersistenceNested(item);
   }

   public SessionPersistenceNested editSessionPersistence() {
      return this.withNewSessionPersistenceLike((SessionPersistence)Optional.ofNullable(this.buildSessionPersistence()).orElse((Object)null));
   }

   public SessionPersistenceNested editOrNewSessionPersistence() {
      return this.withNewSessionPersistenceLike((SessionPersistence)Optional.ofNullable(this.buildSessionPersistence()).orElse((new SessionPersistenceBuilder()).build()));
   }

   public SessionPersistenceNested editOrNewSessionPersistenceLike(SessionPersistence item) {
      return this.withNewSessionPersistenceLike((SessionPersistence)Optional.ofNullable(this.buildSessionPersistence()).orElse(item));
   }

   public BackendLBPolicySpecFluent addToTargetRefs(int index, LocalPolicyTargetReference item) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      LocalPolicyTargetReferenceBuilder builder = new LocalPolicyTargetReferenceBuilder(item);
      if (index >= 0 && index < this.targetRefs.size()) {
         this._visitables.get("targetRefs").add(index, builder);
         this.targetRefs.add(index, builder);
      } else {
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendLBPolicySpecFluent setToTargetRefs(int index, LocalPolicyTargetReference item) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      LocalPolicyTargetReferenceBuilder builder = new LocalPolicyTargetReferenceBuilder(item);
      if (index >= 0 && index < this.targetRefs.size()) {
         this._visitables.get("targetRefs").set(index, builder);
         this.targetRefs.set(index, builder);
      } else {
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendLBPolicySpecFluent addToTargetRefs(LocalPolicyTargetReference... items) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      for(LocalPolicyTargetReference item : items) {
         LocalPolicyTargetReferenceBuilder builder = new LocalPolicyTargetReferenceBuilder(item);
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendLBPolicySpecFluent addAllToTargetRefs(Collection items) {
      if (this.targetRefs == null) {
         this.targetRefs = new ArrayList();
      }

      for(LocalPolicyTargetReference item : items) {
         LocalPolicyTargetReferenceBuilder builder = new LocalPolicyTargetReferenceBuilder(item);
         this._visitables.get("targetRefs").add(builder);
         this.targetRefs.add(builder);
      }

      return this;
   }

   public BackendLBPolicySpecFluent removeFromTargetRefs(LocalPolicyTargetReference... items) {
      if (this.targetRefs == null) {
         return this;
      } else {
         for(LocalPolicyTargetReference item : items) {
            LocalPolicyTargetReferenceBuilder builder = new LocalPolicyTargetReferenceBuilder(item);
            this._visitables.get("targetRefs").remove(builder);
            this.targetRefs.remove(builder);
         }

         return this;
      }
   }

   public BackendLBPolicySpecFluent removeAllFromTargetRefs(Collection items) {
      if (this.targetRefs == null) {
         return this;
      } else {
         for(LocalPolicyTargetReference item : items) {
            LocalPolicyTargetReferenceBuilder builder = new LocalPolicyTargetReferenceBuilder(item);
            this._visitables.get("targetRefs").remove(builder);
            this.targetRefs.remove(builder);
         }

         return this;
      }
   }

   public BackendLBPolicySpecFluent removeMatchingFromTargetRefs(Predicate predicate) {
      if (this.targetRefs == null) {
         return this;
      } else {
         Iterator<LocalPolicyTargetReferenceBuilder> each = this.targetRefs.iterator();
         List visitables = this._visitables.get("targetRefs");

         while(each.hasNext()) {
            LocalPolicyTargetReferenceBuilder builder = (LocalPolicyTargetReferenceBuilder)each.next();
            if (predicate.test(builder)) {
               visitables.remove(builder);
               each.remove();
            }
         }

         return this;
      }
   }

   public List buildTargetRefs() {
      return this.targetRefs != null ? build(this.targetRefs) : null;
   }

   public LocalPolicyTargetReference buildTargetRef(int index) {
      return ((LocalPolicyTargetReferenceBuilder)this.targetRefs.get(index)).build();
   }

   public LocalPolicyTargetReference buildFirstTargetRef() {
      return ((LocalPolicyTargetReferenceBuilder)this.targetRefs.get(0)).build();
   }

   public LocalPolicyTargetReference buildLastTargetRef() {
      return ((LocalPolicyTargetReferenceBuilder)this.targetRefs.get(this.targetRefs.size() - 1)).build();
   }

   public LocalPolicyTargetReference buildMatchingTargetRef(Predicate predicate) {
      for(LocalPolicyTargetReferenceBuilder item : this.targetRefs) {
         if (predicate.test(item)) {
            return item.build();
         }
      }

      return null;
   }

   public boolean hasMatchingTargetRef(Predicate predicate) {
      for(LocalPolicyTargetReferenceBuilder item : this.targetRefs) {
         if (predicate.test(item)) {
            return true;
         }
      }

      return false;
   }

   public BackendLBPolicySpecFluent withTargetRefs(List targetRefs) {
      if (this.targetRefs != null) {
         this._visitables.get("targetRefs").clear();
      }

      if (targetRefs != null) {
         this.targetRefs = new ArrayList();

         for(LocalPolicyTargetReference item : targetRefs) {
            this.addToTargetRefs(item);
         }
      } else {
         this.targetRefs = null;
      }

      return this;
   }

   public BackendLBPolicySpecFluent withTargetRefs(LocalPolicyTargetReference... targetRefs) {
      if (this.targetRefs != null) {
         this.targetRefs.clear();
         this._visitables.remove("targetRefs");
      }

      if (targetRefs != null) {
         for(LocalPolicyTargetReference item : targetRefs) {
            this.addToTargetRefs(item);
         }
      }

      return this;
   }

   public boolean hasTargetRefs() {
      return this.targetRefs != null && !this.targetRefs.isEmpty();
   }

   public BackendLBPolicySpecFluent addNewTargetRef(String group, String kind, String name) {
      return this.addToTargetRefs(new LocalPolicyTargetReference(group, kind, name));
   }

   public TargetRefsNested addNewTargetRef() {
      return new TargetRefsNested(-1, (LocalPolicyTargetReference)null);
   }

   public TargetRefsNested addNewTargetRefLike(LocalPolicyTargetReference item) {
      return new TargetRefsNested(-1, item);
   }

   public TargetRefsNested setNewTargetRefLike(int index, LocalPolicyTargetReference item) {
      return new TargetRefsNested(index, item);
   }

   public TargetRefsNested editTargetRef(int index) {
      if (this.targetRefs.size() <= index) {
         throw new RuntimeException("Can't edit targetRefs. Index exceeds size.");
      } else {
         return this.setNewTargetRefLike(index, this.buildTargetRef(index));
      }
   }

   public TargetRefsNested editFirstTargetRef() {
      if (this.targetRefs.size() == 0) {
         throw new RuntimeException("Can't edit first targetRefs. The list is empty.");
      } else {
         return this.setNewTargetRefLike(0, this.buildTargetRef(0));
      }
   }

   public TargetRefsNested editLastTargetRef() {
      int index = this.targetRefs.size() - 1;
      if (index < 0) {
         throw new RuntimeException("Can't edit last targetRefs. The list is empty.");
      } else {
         return this.setNewTargetRefLike(index, this.buildTargetRef(index));
      }
   }

   public TargetRefsNested editMatchingTargetRef(Predicate predicate) {
      int index = -1;

      for(int i = 0; i < this.targetRefs.size(); ++i) {
         if (predicate.test((LocalPolicyTargetReferenceBuilder)this.targetRefs.get(i))) {
            index = i;
            break;
         }
      }

      if (index < 0) {
         throw new RuntimeException("Can't edit matching targetRefs. No match found.");
      } else {
         return this.setNewTargetRefLike(index, this.buildTargetRef(index));
      }
   }

   public BackendLBPolicySpecFluent addToAdditionalProperties(String key, Object value) {
      if (this.additionalProperties == null && key != null && value != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (key != null && value != null) {
         this.additionalProperties.put(key, value);
      }

      return this;
   }

   public BackendLBPolicySpecFluent addToAdditionalProperties(Map map) {
      if (this.additionalProperties == null && map != null) {
         this.additionalProperties = new LinkedHashMap();
      }

      if (map != null) {
         this.additionalProperties.putAll(map);
      }

      return this;
   }

   public BackendLBPolicySpecFluent removeFromAdditionalProperties(String key) {
      if (this.additionalProperties == null) {
         return this;
      } else {
         if (key != null && this.additionalProperties != null) {
            this.additionalProperties.remove(key);
         }

         return this;
      }
   }

   public BackendLBPolicySpecFluent removeFromAdditionalProperties(Map map) {
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

   public BackendLBPolicySpecFluent withAdditionalProperties(Map additionalProperties) {
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
            BackendLBPolicySpecFluent that = (BackendLBPolicySpecFluent)o;
            if (!Objects.equals(this.sessionPersistence, that.sessionPersistence)) {
               return false;
            } else if (!Objects.equals(this.targetRefs, that.targetRefs)) {
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
      return Objects.hash(new Object[]{this.sessionPersistence, this.targetRefs, this.additionalProperties, super.hashCode()});
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("{");
      if (this.sessionPersistence != null) {
         sb.append("sessionPersistence:");
         sb.append(this.sessionPersistence + ",");
      }

      if (this.targetRefs != null && !this.targetRefs.isEmpty()) {
         sb.append("targetRefs:");
         sb.append(this.targetRefs + ",");
      }

      if (this.additionalProperties != null && !this.additionalProperties.isEmpty()) {
         sb.append("additionalProperties:");
         sb.append(this.additionalProperties);
      }

      sb.append("}");
      return sb.toString();
   }

   public class SessionPersistenceNested extends SessionPersistenceFluent implements Nested {
      SessionPersistenceBuilder builder;

      SessionPersistenceNested(SessionPersistence item) {
         this.builder = new SessionPersistenceBuilder(this, item);
      }

      public Object and() {
         return BackendLBPolicySpecFluent.this.withSessionPersistence(this.builder.build());
      }

      public Object endSessionPersistence() {
         return this.and();
      }
   }

   public class TargetRefsNested extends LocalPolicyTargetReferenceFluent implements Nested {
      LocalPolicyTargetReferenceBuilder builder;
      int index;

      TargetRefsNested(int index, LocalPolicyTargetReference item) {
         this.index = index;
         this.builder = new LocalPolicyTargetReferenceBuilder(this, item);
      }

      public Object and() {
         return BackendLBPolicySpecFluent.this.setToTargetRefs(this.index, this.builder.build());
      }

      public Object endTargetRef() {
         return this.and();
      }
   }
}
