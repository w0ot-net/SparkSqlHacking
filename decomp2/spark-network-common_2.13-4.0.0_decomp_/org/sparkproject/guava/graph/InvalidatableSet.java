package org.sparkproject.guava.graph;

import java.util.Set;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.base.Supplier;
import org.sparkproject.guava.collect.ForwardingSet;

@ElementTypesAreNonnullByDefault
final class InvalidatableSet extends ForwardingSet {
   private final Supplier validator;
   private final Set delegate;
   private final Supplier errorMessage;

   public static final InvalidatableSet of(Set delegate, Supplier validator, Supplier errorMessage) {
      return new InvalidatableSet((Set)Preconditions.checkNotNull(delegate), (Supplier)Preconditions.checkNotNull(validator), (Supplier)Preconditions.checkNotNull(errorMessage));
   }

   protected Set delegate() {
      this.validate();
      return this.delegate;
   }

   private InvalidatableSet(Set delegate, Supplier validator, Supplier errorMessage) {
      this.delegate = delegate;
      this.validator = validator;
      this.errorMessage = errorMessage;
   }

   public int hashCode() {
      return this.delegate.hashCode();
   }

   private void validate() {
      if (!(Boolean)this.validator.get()) {
         throw new IllegalStateException((String)this.errorMessage.get());
      }
   }
}
