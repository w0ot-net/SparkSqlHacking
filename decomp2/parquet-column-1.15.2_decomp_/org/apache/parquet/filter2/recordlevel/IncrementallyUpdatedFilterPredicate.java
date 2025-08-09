package org.apache.parquet.filter2.recordlevel;

import java.util.Arrays;
import java.util.Objects;
import org.apache.parquet.io.api.Binary;

public interface IncrementallyUpdatedFilterPredicate {
   boolean accept(Visitor var1);

   public abstract static class ValueInspector implements IncrementallyUpdatedFilterPredicate {
      private boolean result = false;
      private boolean isKnown = false;

      ValueInspector() {
      }

      public void updateNull() {
         throw new UnsupportedOperationException();
      }

      public void update(int value) {
         throw new UnsupportedOperationException();
      }

      public void update(long value) {
         throw new UnsupportedOperationException();
      }

      public void update(double value) {
         throw new UnsupportedOperationException();
      }

      public void update(float value) {
         throw new UnsupportedOperationException();
      }

      public void update(boolean value) {
         throw new UnsupportedOperationException();
      }

      public void update(Binary value) {
         throw new UnsupportedOperationException();
      }

      public void reset() {
         this.isKnown = false;
         this.result = false;
      }

      protected final void setResult(boolean result) {
         if (this.isKnown) {
            throw new IllegalStateException("setResult() called on a ValueInspector whose result is already known! Did you forget to call reset()?");
         } else {
            this.result = result;
            this.isKnown = true;
         }
      }

      public final boolean getResult() {
         if (!this.isKnown) {
            throw new IllegalStateException("getResult() called on a ValueInspector whose result is not yet known!");
         } else {
            return this.result;
         }
      }

      public final boolean isKnown() {
         return this.isKnown;
      }

      public boolean accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public abstract static class DelegatingValueInspector extends ValueInspector {
      private final Iterable delegates;

      DelegatingValueInspector(ValueInspector... delegates) {
         this.delegates = Arrays.asList(delegates);
      }

      abstract void onUpdate();

      abstract void onNull();

      Iterable getDelegates() {
         return this.delegates;
      }

      public void updateNull() {
         for(ValueInspector delegate : this.delegates) {
            if (!delegate.isKnown()) {
               delegate.updateNull();
            }
         }

         this.onNull();
      }

      public void update(int value) {
         this.delegates.forEach((d) -> d.update(value));
         this.onUpdate();
      }

      public void update(long value) {
         this.delegates.forEach((d) -> d.update(value));
         this.onUpdate();
      }

      public void update(boolean value) {
         this.delegates.forEach((d) -> d.update(value));
         this.onUpdate();
      }

      public void update(float value) {
         this.delegates.forEach((d) -> d.update(value));
         this.onUpdate();
      }

      public void update(double value) {
         this.delegates.forEach((d) -> d.update(value));
         this.onUpdate();
      }

      public void update(Binary value) {
         this.delegates.forEach((d) -> d.update(value));
         this.onUpdate();
      }

      public void reset() {
         this.delegates.forEach(ValueInspector::reset);
         super.reset();
      }
   }

   public abstract static class BinaryLogical implements IncrementallyUpdatedFilterPredicate {
      private final IncrementallyUpdatedFilterPredicate left;
      private final IncrementallyUpdatedFilterPredicate right;

      BinaryLogical(IncrementallyUpdatedFilterPredicate left, IncrementallyUpdatedFilterPredicate right) {
         this.left = (IncrementallyUpdatedFilterPredicate)Objects.requireNonNull(left, "left cannot be null");
         this.right = (IncrementallyUpdatedFilterPredicate)Objects.requireNonNull(right, "right cannot be null");
      }

      public final IncrementallyUpdatedFilterPredicate getLeft() {
         return this.left;
      }

      public final IncrementallyUpdatedFilterPredicate getRight() {
         return this.right;
      }
   }

   public static final class Or extends BinaryLogical {
      Or(IncrementallyUpdatedFilterPredicate left, IncrementallyUpdatedFilterPredicate right) {
         super(left, right);
      }

      public boolean accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class And extends BinaryLogical {
      And(IncrementallyUpdatedFilterPredicate left, IncrementallyUpdatedFilterPredicate right) {
         super(left, right);
      }

      public boolean accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public interface Visitor {
      boolean visit(ValueInspector var1);

      boolean visit(And var1);

      boolean visit(Or var1);
   }
}
