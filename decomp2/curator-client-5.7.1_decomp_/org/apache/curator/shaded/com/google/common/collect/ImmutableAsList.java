package org.apache.curator.shaded.com.google.common.collect;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.annotations.GwtIncompatible;
import org.apache.curator.shaded.com.google.common.annotations.J2ktIncompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true,
   emulated = true
)
abstract class ImmutableAsList extends ImmutableList {
   abstract ImmutableCollection delegateCollection();

   public boolean contains(@CheckForNull Object target) {
      return this.delegateCollection().contains(target);
   }

   public int size() {
      return this.delegateCollection().size();
   }

   public boolean isEmpty() {
      return this.delegateCollection().isEmpty();
   }

   boolean isPartialView() {
      return this.delegateCollection().isPartialView();
   }

   @GwtIncompatible
   @J2ktIncompatible
   private void readObject(ObjectInputStream stream) throws InvalidObjectException {
      throw new InvalidObjectException("Use SerializedForm");
   }

   @GwtIncompatible
   @J2ktIncompatible
   Object writeReplace() {
      return new SerializedForm(this.delegateCollection());
   }

   @GwtIncompatible
   @J2ktIncompatible
   static class SerializedForm implements Serializable {
      final ImmutableCollection collection;
      private static final long serialVersionUID = 0L;

      SerializedForm(ImmutableCollection collection) {
         this.collection = collection;
      }

      Object readResolve() {
         return this.collection.asList();
      }
   }
}
