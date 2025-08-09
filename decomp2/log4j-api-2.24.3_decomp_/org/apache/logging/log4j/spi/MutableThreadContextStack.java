package org.apache.logging.log4j.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.StringBuilderFormattable;

public class MutableThreadContextStack implements ThreadContextStack, StringBuilderFormattable {
   private static final long serialVersionUID = 50505011L;
   private final List list;
   private boolean frozen;

   public MutableThreadContextStack() {
      this((List)(new ArrayList()));
   }

   public MutableThreadContextStack(final List list) {
      this.list = new ArrayList(list);
   }

   private MutableThreadContextStack(final MutableThreadContextStack stack) {
      this.list = new ArrayList(stack.list);
   }

   private void checkInvariants() {
      if (this.frozen) {
         throw new UnsupportedOperationException("context stack has been frozen");
      }
   }

   public String pop() {
      this.checkInvariants();
      if (this.list.isEmpty()) {
         return null;
      } else {
         int last = this.list.size() - 1;
         String result = (String)this.list.remove(last);
         return result;
      }
   }

   public String peek() {
      if (this.list.isEmpty()) {
         return null;
      } else {
         int last = this.list.size() - 1;
         return (String)this.list.get(last);
      }
   }

   public void push(final String message) {
      this.checkInvariants();
      this.list.add(message);
   }

   public int getDepth() {
      return this.list.size();
   }

   public List asList() {
      return this.list;
   }

   public void trim(final int depth) {
      this.checkInvariants();
      if (depth < 0) {
         throw new IllegalArgumentException("Maximum stack depth cannot be negative");
      } else if (this.list != null) {
         List<String> copy = new ArrayList(this.list.size());
         int count = Math.min(depth, this.list.size());

         for(int i = 0; i < count; ++i) {
            copy.add((String)this.list.get(i));
         }

         this.list.clear();
         this.list.addAll(copy);
      }
   }

   public ThreadContextStack copy() {
      return new MutableThreadContextStack(this);
   }

   public void clear() {
      this.checkInvariants();
      this.list.clear();
   }

   public int size() {
      return this.list.size();
   }

   public boolean isEmpty() {
      return this.list.isEmpty();
   }

   public boolean contains(final Object o) {
      return this.list.contains(o);
   }

   public Iterator iterator() {
      return this.list.iterator();
   }

   public Object[] toArray() {
      return this.list.toArray();
   }

   public Object[] toArray(final Object[] ts) {
      return this.list.toArray(ts);
   }

   public boolean add(final String s) {
      this.checkInvariants();
      return this.list.add(s);
   }

   public boolean remove(final Object o) {
      this.checkInvariants();
      return this.list.remove(o);
   }

   public boolean containsAll(final Collection objects) {
      return this.list.containsAll(objects);
   }

   public boolean addAll(final Collection strings) {
      this.checkInvariants();
      return this.list.addAll(strings);
   }

   public boolean removeAll(final Collection objects) {
      this.checkInvariants();
      return this.list.removeAll(objects);
   }

   public boolean retainAll(final Collection objects) {
      this.checkInvariants();
      return this.list.retainAll(objects);
   }

   public String toString() {
      return String.valueOf(this.list);
   }

   public void formatTo(final StringBuilder buffer) {
      buffer.append('[');

      for(int i = 0; i < this.list.size(); ++i) {
         if (i > 0) {
            buffer.append(',').append(' ');
         }

         buffer.append((String)this.list.get(i));
      }

      buffer.append(']');
   }

   public int hashCode() {
      return 31 + Objects.hashCode(this.list);
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!(obj instanceof ThreadContextStack)) {
         return false;
      } else {
         ThreadContextStack other = (ThreadContextStack)obj;
         List<String> otherAsList = other.asList();
         return Objects.equals(this.list, otherAsList);
      }
   }

   public ThreadContext.ContextStack getImmutableStackOrNull() {
      return this.copy();
   }

   public void freeze() {
      this.frozen = true;
   }

   public boolean isFrozen() {
      return this.frozen;
   }
}
