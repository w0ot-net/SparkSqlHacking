package org.apache.curator.shaded.com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.errorprone.annotations.CanIgnoreReturnValue;

@ElementTypesAreNonnullByDefault
@GwtCompatible
public abstract class ForwardingList extends ForwardingCollection implements List {
   protected ForwardingList() {
   }

   protected abstract List delegate();

   public void add(int index, @ParametricNullness Object element) {
      this.delegate().add(index, element);
   }

   @CanIgnoreReturnValue
   public boolean addAll(int index, Collection elements) {
      return this.delegate().addAll(index, elements);
   }

   @ParametricNullness
   public Object get(int index) {
      return this.delegate().get(index);
   }

   public int indexOf(@CheckForNull Object element) {
      return this.delegate().indexOf(element);
   }

   public int lastIndexOf(@CheckForNull Object element) {
      return this.delegate().lastIndexOf(element);
   }

   public ListIterator listIterator() {
      return this.delegate().listIterator();
   }

   public ListIterator listIterator(int index) {
      return this.delegate().listIterator(index);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object remove(int index) {
      return this.delegate().remove(index);
   }

   @ParametricNullness
   @CanIgnoreReturnValue
   public Object set(int index, @ParametricNullness Object element) {
      return this.delegate().set(index, element);
   }

   public List subList(int fromIndex, int toIndex) {
      return this.delegate().subList(fromIndex, toIndex);
   }

   public boolean equals(@CheckForNull Object object) {
      return object == this || this.delegate().equals(object);
   }

   public int hashCode() {
      return this.delegate().hashCode();
   }

   protected boolean standardAdd(@ParametricNullness Object element) {
      this.add(this.size(), element);
      return true;
   }

   protected boolean standardAddAll(int index, Iterable elements) {
      return Lists.addAllImpl(this, index, elements);
   }

   protected int standardIndexOf(@CheckForNull Object element) {
      return Lists.indexOfImpl(this, element);
   }

   protected int standardLastIndexOf(@CheckForNull Object element) {
      return Lists.lastIndexOfImpl(this, element);
   }

   protected Iterator standardIterator() {
      return this.listIterator();
   }

   protected ListIterator standardListIterator() {
      return this.listIterator(0);
   }

   protected ListIterator standardListIterator(int start) {
      return Lists.listIteratorImpl(this, start);
   }

   protected List standardSubList(int fromIndex, int toIndex) {
      return Lists.subListImpl(this, fromIndex, toIndex);
   }

   protected boolean standardEquals(@CheckForNull Object object) {
      return Lists.equalsImpl(this, object);
   }

   protected int standardHashCode() {
      return Lists.hashCodeImpl(this);
   }
}
