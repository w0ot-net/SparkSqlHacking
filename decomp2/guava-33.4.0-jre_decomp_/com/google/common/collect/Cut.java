package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
@GwtCompatible
abstract class Cut implements Comparable, Serializable {
   final Comparable endpoint;
   private static final long serialVersionUID = 0L;

   Cut(Comparable endpoint) {
      this.endpoint = endpoint;
   }

   abstract boolean isLessThan(Comparable value);

   abstract BoundType typeAsLowerBound();

   abstract BoundType typeAsUpperBound();

   abstract Cut withLowerBoundType(BoundType boundType, DiscreteDomain domain);

   abstract Cut withUpperBoundType(BoundType boundType, DiscreteDomain domain);

   abstract void describeAsLowerBound(StringBuilder sb);

   abstract void describeAsUpperBound(StringBuilder sb);

   @CheckForNull
   abstract Comparable leastValueAbove(DiscreteDomain domain);

   @CheckForNull
   abstract Comparable greatestValueBelow(DiscreteDomain domain);

   Cut canonical(DiscreteDomain domain) {
      return this;
   }

   public int compareTo(Cut that) {
      if (that == belowAll()) {
         return 1;
      } else if (that == aboveAll()) {
         return -1;
      } else {
         int result = Range.compareOrThrow(this.endpoint, that.endpoint);
         return result != 0 ? result : Boolean.compare(this instanceof AboveValue, that instanceof AboveValue);
      }
   }

   Comparable endpoint() {
      return this.endpoint;
   }

   public boolean equals(@CheckForNull Object obj) {
      if (obj instanceof Cut) {
         Cut<C> that = (Cut)obj;

         try {
            int compareResult = this.compareTo(that);
            return compareResult == 0;
         } catch (ClassCastException var4) {
            return false;
         }
      } else {
         return false;
      }
   }

   public abstract int hashCode();

   static Cut belowAll() {
      return Cut.BelowAll.INSTANCE;
   }

   static Cut aboveAll() {
      return Cut.AboveAll.INSTANCE;
   }

   static Cut belowValue(Comparable endpoint) {
      return new BelowValue(endpoint);
   }

   static Cut aboveValue(Comparable endpoint) {
      return new AboveValue(endpoint);
   }

   private static final class BelowAll extends Cut {
      private static final BelowAll INSTANCE = new BelowAll();
      private static final long serialVersionUID = 0L;

      private BelowAll() {
         super("");
      }

      Comparable endpoint() {
         throw new IllegalStateException("range unbounded on this side");
      }

      boolean isLessThan(Comparable value) {
         return true;
      }

      BoundType typeAsLowerBound() {
         throw new IllegalStateException();
      }

      BoundType typeAsUpperBound() {
         throw new AssertionError("this statement should be unreachable");
      }

      Cut withLowerBoundType(BoundType boundType, DiscreteDomain domain) {
         throw new IllegalStateException();
      }

      Cut withUpperBoundType(BoundType boundType, DiscreteDomain domain) {
         throw new AssertionError("this statement should be unreachable");
      }

      void describeAsLowerBound(StringBuilder sb) {
         sb.append("(-∞");
      }

      void describeAsUpperBound(StringBuilder sb) {
         throw new AssertionError();
      }

      Comparable leastValueAbove(DiscreteDomain domain) {
         return domain.minValue();
      }

      Comparable greatestValueBelow(DiscreteDomain domain) {
         throw new AssertionError();
      }

      Cut canonical(DiscreteDomain domain) {
         try {
            return Cut.belowValue(domain.minValue());
         } catch (NoSuchElementException var3) {
            return this;
         }
      }

      public int compareTo(Cut o) {
         return o == this ? 0 : -1;
      }

      public int hashCode() {
         return System.identityHashCode(this);
      }

      public String toString() {
         return "-∞";
      }

      private Object readResolve() {
         return INSTANCE;
      }
   }

   private static final class AboveAll extends Cut {
      private static final AboveAll INSTANCE = new AboveAll();
      private static final long serialVersionUID = 0L;

      private AboveAll() {
         super("");
      }

      Comparable endpoint() {
         throw new IllegalStateException("range unbounded on this side");
      }

      boolean isLessThan(Comparable value) {
         return false;
      }

      BoundType typeAsLowerBound() {
         throw new AssertionError("this statement should be unreachable");
      }

      BoundType typeAsUpperBound() {
         throw new IllegalStateException();
      }

      Cut withLowerBoundType(BoundType boundType, DiscreteDomain domain) {
         throw new AssertionError("this statement should be unreachable");
      }

      Cut withUpperBoundType(BoundType boundType, DiscreteDomain domain) {
         throw new IllegalStateException();
      }

      void describeAsLowerBound(StringBuilder sb) {
         throw new AssertionError();
      }

      void describeAsUpperBound(StringBuilder sb) {
         sb.append("+∞)");
      }

      Comparable leastValueAbove(DiscreteDomain domain) {
         throw new AssertionError();
      }

      Comparable greatestValueBelow(DiscreteDomain domain) {
         return domain.maxValue();
      }

      public int compareTo(Cut o) {
         return o == this ? 0 : 1;
      }

      public int hashCode() {
         return System.identityHashCode(this);
      }

      public String toString() {
         return "+∞";
      }

      private Object readResolve() {
         return INSTANCE;
      }
   }

   private static final class BelowValue extends Cut {
      private static final long serialVersionUID = 0L;

      BelowValue(Comparable endpoint) {
         super((Comparable)Preconditions.checkNotNull(endpoint));
      }

      boolean isLessThan(Comparable value) {
         return Range.compareOrThrow(this.endpoint, value) <= 0;
      }

      BoundType typeAsLowerBound() {
         return BoundType.CLOSED;
      }

      BoundType typeAsUpperBound() {
         return BoundType.OPEN;
      }

      Cut withLowerBoundType(BoundType boundType, DiscreteDomain domain) {
         switch (boundType) {
            case CLOSED:
               return this;
            case OPEN:
               C previous = (C)domain.previous(this.endpoint);
               return (Cut)(previous == null ? Cut.belowAll() : new AboveValue(previous));
            default:
               throw new AssertionError();
         }
      }

      Cut withUpperBoundType(BoundType boundType, DiscreteDomain domain) {
         switch (boundType) {
            case CLOSED:
               C previous = (C)domain.previous(this.endpoint);
               return (Cut)(previous == null ? Cut.aboveAll() : new AboveValue(previous));
            case OPEN:
               return this;
            default:
               throw new AssertionError();
         }
      }

      void describeAsLowerBound(StringBuilder sb) {
         sb.append('[').append(this.endpoint);
      }

      void describeAsUpperBound(StringBuilder sb) {
         sb.append(this.endpoint).append(')');
      }

      Comparable leastValueAbove(DiscreteDomain domain) {
         return this.endpoint;
      }

      @CheckForNull
      Comparable greatestValueBelow(DiscreteDomain domain) {
         return domain.previous(this.endpoint);
      }

      public int hashCode() {
         return this.endpoint.hashCode();
      }

      public String toString() {
         return "\\" + this.endpoint + "/";
      }
   }

   private static final class AboveValue extends Cut {
      private static final long serialVersionUID = 0L;

      AboveValue(Comparable endpoint) {
         super((Comparable)Preconditions.checkNotNull(endpoint));
      }

      boolean isLessThan(Comparable value) {
         return Range.compareOrThrow(this.endpoint, value) < 0;
      }

      BoundType typeAsLowerBound() {
         return BoundType.OPEN;
      }

      BoundType typeAsUpperBound() {
         return BoundType.CLOSED;
      }

      Cut withLowerBoundType(BoundType boundType, DiscreteDomain domain) {
         switch (boundType) {
            case CLOSED:
               C next = (C)domain.next(this.endpoint);
               return next == null ? Cut.belowAll() : belowValue(next);
            case OPEN:
               return this;
            default:
               throw new AssertionError();
         }
      }

      Cut withUpperBoundType(BoundType boundType, DiscreteDomain domain) {
         switch (boundType) {
            case CLOSED:
               return this;
            case OPEN:
               C next = (C)domain.next(this.endpoint);
               return next == null ? Cut.aboveAll() : belowValue(next);
            default:
               throw new AssertionError();
         }
      }

      void describeAsLowerBound(StringBuilder sb) {
         sb.append('(').append(this.endpoint);
      }

      void describeAsUpperBound(StringBuilder sb) {
         sb.append(this.endpoint).append(']');
      }

      @CheckForNull
      Comparable leastValueAbove(DiscreteDomain domain) {
         return domain.next(this.endpoint);
      }

      Comparable greatestValueBelow(DiscreteDomain domain) {
         return this.endpoint;
      }

      Cut canonical(DiscreteDomain domain) {
         C next = (C)this.leastValueAbove(domain);
         return next != null ? belowValue(next) : Cut.aboveAll();
      }

      public int hashCode() {
         return ~this.endpoint.hashCode();
      }

      public String toString() {
         return "/" + this.endpoint + "\\";
      }
   }
}
