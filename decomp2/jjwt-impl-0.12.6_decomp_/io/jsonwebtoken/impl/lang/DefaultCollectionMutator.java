package io.jsonwebtoken.impl.lang;

import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.lang.CollectionMutator;
import io.jsonwebtoken.lang.Collections;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.lang.Strings;
import java.util.Collection;
import java.util.LinkedHashSet;

public class DefaultCollectionMutator implements CollectionMutator {
   private final Collection collection;

   public DefaultCollectionMutator(Collection seed) {
      this.collection = new LinkedHashSet(Collections.nullSafe(seed));
   }

   protected final CollectionMutator self() {
      return this;
   }

   private boolean doAdd(Object e) {
      if (Objects.isEmpty(e)) {
         return false;
      } else if (e instanceof Identifiable && !Strings.hasText(((Identifiable)e).getId())) {
         String msg = e.getClass() + " getId() value cannot be null or empty.";
         throw new IllegalArgumentException(msg);
      } else {
         return this.collection.add(e);
      }
   }

   public CollectionMutator add(Object e) {
      if (this.doAdd(e)) {
         this.changed();
      }

      return this.self();
   }

   public CollectionMutator remove(Object e) {
      if (this.collection.remove(e)) {
         this.changed();
      }

      return this.self();
   }

   public CollectionMutator add(Collection c) {
      boolean changed = false;

      for(Object element : Collections.nullSafe(c)) {
         changed = this.doAdd(element) || changed;
      }

      if (changed) {
         this.changed();
      }

      return this.self();
   }

   public CollectionMutator clear() {
      boolean changed = !Collections.isEmpty(this.collection);
      this.collection.clear();
      if (changed) {
         this.changed();
      }

      return this.self();
   }

   protected void changed() {
   }

   protected Collection getCollection() {
      return Collections.immutable(this.collection);
   }
}
