package org.sparkproject.guava.graph;

import com.google.errorprone.annotations.Immutable;
import java.util.Comparator;
import java.util.Map;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.Beta;
import org.sparkproject.guava.base.MoreObjects;
import org.sparkproject.guava.base.Objects;
import org.sparkproject.guava.base.Preconditions;
import org.sparkproject.guava.collect.Maps;
import org.sparkproject.guava.collect.Ordering;

@Immutable
@ElementTypesAreNonnullByDefault
@Beta
public final class ElementOrder {
   private final Type type;
   @CheckForNull
   private final Comparator comparator;

   private ElementOrder(Type type, @CheckForNull Comparator comparator) {
      this.type = (Type)Preconditions.checkNotNull(type);
      this.comparator = comparator;
      Preconditions.checkState(type == ElementOrder.Type.SORTED == (comparator != null));
   }

   public static ElementOrder unordered() {
      return new ElementOrder(ElementOrder.Type.UNORDERED, (Comparator)null);
   }

   public static ElementOrder stable() {
      return new ElementOrder(ElementOrder.Type.STABLE, (Comparator)null);
   }

   public static ElementOrder insertion() {
      return new ElementOrder(ElementOrder.Type.INSERTION, (Comparator)null);
   }

   public static ElementOrder natural() {
      return new ElementOrder(ElementOrder.Type.SORTED, Ordering.natural());
   }

   public static ElementOrder sorted(Comparator comparator) {
      return new ElementOrder(ElementOrder.Type.SORTED, (Comparator)Preconditions.checkNotNull(comparator));
   }

   public Type type() {
      return this.type;
   }

   public Comparator comparator() {
      if (this.comparator != null) {
         return this.comparator;
      } else {
         throw new UnsupportedOperationException("This ordering does not define a comparator.");
      }
   }

   public boolean equals(@CheckForNull Object obj) {
      if (obj == this) {
         return true;
      } else if (!(obj instanceof ElementOrder)) {
         return false;
      } else {
         ElementOrder<?> other = (ElementOrder)obj;
         return this.type == other.type && Objects.equal(this.comparator, other.comparator);
      }
   }

   public int hashCode() {
      return Objects.hashCode(this.type, this.comparator);
   }

   public String toString() {
      MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper((Object)this).add("type", this.type);
      if (this.comparator != null) {
         helper.add("comparator", this.comparator);
      }

      return helper.toString();
   }

   Map createMap(int expectedSize) {
      switch (this.type.ordinal()) {
         case 0:
            return Maps.newHashMapWithExpectedSize(expectedSize);
         case 1:
         case 2:
            return Maps.newLinkedHashMapWithExpectedSize(expectedSize);
         case 3:
            return Maps.newTreeMap(this.comparator());
         default:
            throw new AssertionError();
      }
   }

   ElementOrder cast() {
      return this;
   }

   public static enum Type {
      UNORDERED,
      STABLE,
      INSERTION,
      SORTED;

      // $FF: synthetic method
      private static Type[] $values() {
         return new Type[]{UNORDERED, STABLE, INSERTION, SORTED};
      }
   }
}
