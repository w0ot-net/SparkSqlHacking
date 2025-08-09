package org.apache.curator.shaded.com.google.common.base;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.DoNotMock;

@DoNotMock("Use Optional.of(value) or Optional.absent()")
@ElementTypesAreNonnullByDefault
@GwtCompatible(
   serializable = true
)
public abstract class Optional implements Serializable {
   private static final long serialVersionUID = 0L;

   public static Optional absent() {
      return Absent.withType();
   }

   public static Optional of(Object reference) {
      return new Present(Preconditions.checkNotNull(reference));
   }

   public static Optional fromNullable(@CheckForNull Object nullableReference) {
      return (Optional)(nullableReference == null ? absent() : new Present(nullableReference));
   }

   @CheckForNull
   public static Optional fromJavaUtil(@CheckForNull java.util.Optional javaUtilOptional) {
      return javaUtilOptional == null ? null : fromNullable(javaUtilOptional.orElse((Object)null));
   }

   @CheckForNull
   public static java.util.Optional toJavaUtil(@CheckForNull Optional googleOptional) {
      return googleOptional == null ? null : googleOptional.toJavaUtil();
   }

   public java.util.Optional toJavaUtil() {
      return java.util.Optional.ofNullable(this.orNull());
   }

   Optional() {
   }

   public abstract boolean isPresent();

   public abstract Object get();

   public abstract Object or(Object defaultValue);

   public abstract Optional or(Optional secondChoice);

   public abstract Object or(Supplier supplier);

   @CheckForNull
   public abstract Object orNull();

   public abstract Set asSet();

   public abstract Optional transform(Function function);

   public abstract boolean equals(@CheckForNull Object object);

   public abstract int hashCode();

   public abstract String toString();

   public static Iterable presentInstances(final Iterable optionals) {
      Preconditions.checkNotNull(optionals);
      return new Iterable() {
         public Iterator iterator() {
            return new AbstractIterator() {
               private final Iterator iterator = (Iterator)Preconditions.checkNotNull(optionals.iterator());

               @CheckForNull
               protected Object computeNext() {
                  while(true) {
                     if (this.iterator.hasNext()) {
                        Optional<? extends T> optional = (Optional)this.iterator.next();
                        if (!optional.isPresent()) {
                           continue;
                        }

                        return optional.get();
                     }

                     return this.endOfData();
                  }
               }
            };
         }
      };
   }
}
