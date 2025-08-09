package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ForwardingSet;
import java.util.Set;

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
