package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.J2ktIncompatible;
import java.util.function.Consumer;
import javax.annotation.CheckForNull;
import org.checkerframework.checker.nullness.qual.Nullable;

@ElementTypesAreNonnullByDefault
@GwtCompatible(
   emulated = true
)
class RegularImmutableAsList extends ImmutableAsList {
   private final ImmutableCollection delegate;
   private final ImmutableList delegateList;

   RegularImmutableAsList(ImmutableCollection delegate, ImmutableList delegateList) {
      this.delegate = delegate;
      this.delegateList = delegateList;
   }

   RegularImmutableAsList(ImmutableCollection delegate, Object[] array) {
      this(delegate, ImmutableList.asImmutableList(array));
   }

   ImmutableCollection delegateCollection() {
      return this.delegate;
   }

   ImmutableList delegateList() {
      return this.delegateList;
   }

   public UnmodifiableListIterator listIterator(int index) {
      return this.delegateList.listIterator(index);
   }

   @GwtIncompatible
   public void forEach(Consumer action) {
      this.delegateList.forEach(action);
   }

   @GwtIncompatible
   int copyIntoArray(@Nullable Object[] dst, int offset) {
      return this.delegateList.copyIntoArray(dst, offset);
   }

   @CheckForNull
   Object[] internalArray() {
      return this.delegateList.internalArray();
   }

   int internalArrayStart() {
      return this.delegateList.internalArrayStart();
   }

   int internalArrayEnd() {
      return this.delegateList.internalArrayEnd();
   }

   public Object get(int index) {
      return this.delegateList.get(index);
   }

   @J2ktIncompatible
   @GwtIncompatible
   Object writeReplace() {
      return super.writeReplace();
   }
}
