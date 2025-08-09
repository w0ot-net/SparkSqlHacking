package org.sparkproject.spark_core.protobuf;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public class LazyStringArrayList extends AbstractProtobufList implements LazyStringList, RandomAccess {
   private static final LazyStringArrayList EMPTY_LIST = new LazyStringArrayList(false);
   /** @deprecated */
   @Deprecated
   public static final LazyStringList EMPTY;
   private final List list;

   public static LazyStringArrayList emptyList() {
      return EMPTY_LIST;
   }

   public LazyStringArrayList() {
      this(10);
   }

   private LazyStringArrayList(boolean isMutable) {
      super(isMutable);
      this.list = Collections.emptyList();
   }

   public LazyStringArrayList(int initialCapacity) {
      this(new ArrayList(initialCapacity));
   }

   public LazyStringArrayList(LazyStringList from) {
      this.list = new ArrayList(from.size());
      this.addAll(from);
   }

   public LazyStringArrayList(List from) {
      this(new ArrayList(from));
   }

   private LazyStringArrayList(ArrayList list) {
      this.list = list;
   }

   public LazyStringArrayList mutableCopyWithCapacity(int capacity) {
      if (capacity < this.size()) {
         throw new IllegalArgumentException();
      } else {
         ArrayList<Object> newList = new ArrayList(capacity);
         newList.addAll(this.list);
         return new LazyStringArrayList(newList);
      }
   }

   public String get(int index) {
      Object o = this.list.get(index);
      if (o instanceof String) {
         return (String)o;
      } else if (o instanceof ByteString) {
         ByteString bs = (ByteString)o;
         String s = bs.toStringUtf8();
         if (bs.isValidUtf8()) {
            this.list.set(index, s);
         }

         return s;
      } else {
         byte[] ba = (byte[])o;
         String s = Internal.toStringUtf8(ba);
         if (Internal.isValidUtf8(ba)) {
            this.list.set(index, s);
         }

         return s;
      }
   }

   public int size() {
      return this.list.size();
   }

   public String set(int index, String s) {
      this.ensureIsMutable();
      Object o = this.list.set(index, s);
      return asString(o);
   }

   public void add(int index, String element) {
      this.ensureIsMutable();
      this.list.add(index, element);
      ++this.modCount;
   }

   private void add(int index, ByteString element) {
      this.ensureIsMutable();
      this.list.add(index, element);
      ++this.modCount;
   }

   private void add(int index, byte[] element) {
      this.ensureIsMutable();
      this.list.add(index, element);
      ++this.modCount;
   }

   @CanIgnoreReturnValue
   public boolean add(String element) {
      this.ensureIsMutable();
      this.list.add(element);
      ++this.modCount;
      return true;
   }

   public void add(ByteString element) {
      this.ensureIsMutable();
      this.list.add(element);
      ++this.modCount;
   }

   public void add(byte[] element) {
      this.ensureIsMutable();
      this.list.add(element);
      ++this.modCount;
   }

   public boolean addAll(Collection c) {
      return this.addAll(this.size(), c);
   }

   public boolean addAll(int index, Collection c) {
      this.ensureIsMutable();
      Collection<?> collection = (Collection<?>)(c instanceof LazyStringList ? ((LazyStringList)c).getUnderlyingElements() : c);
      boolean ret = this.list.addAll(index, collection);
      ++this.modCount;
      return ret;
   }

   public boolean addAllByteString(Collection values) {
      this.ensureIsMutable();
      boolean ret = this.list.addAll(values);
      ++this.modCount;
      return ret;
   }

   public boolean addAllByteArray(Collection c) {
      this.ensureIsMutable();
      boolean ret = this.list.addAll(c);
      ++this.modCount;
      return ret;
   }

   public String remove(int index) {
      this.ensureIsMutable();
      Object o = this.list.remove(index);
      ++this.modCount;
      return asString(o);
   }

   public void clear() {
      this.ensureIsMutable();
      this.list.clear();
      ++this.modCount;
   }

   public Object getRaw(int index) {
      return this.list.get(index);
   }

   public ByteString getByteString(int index) {
      Object o = this.list.get(index);
      ByteString b = asByteString(o);
      if (b != o) {
         this.list.set(index, b);
      }

      return b;
   }

   public byte[] getByteArray(int index) {
      Object o = this.list.get(index);
      byte[] b = asByteArray(o);
      if (b != o) {
         this.list.set(index, b);
      }

      return b;
   }

   public void set(int index, ByteString s) {
      this.setAndReturn(index, s);
   }

   private Object setAndReturn(int index, ByteString s) {
      this.ensureIsMutable();
      return this.list.set(index, s);
   }

   public void set(int index, byte[] s) {
      this.setAndReturn(index, s);
   }

   private Object setAndReturn(int index, byte[] s) {
      this.ensureIsMutable();
      return this.list.set(index, s);
   }

   private static String asString(Object o) {
      if (o instanceof String) {
         return (String)o;
      } else {
         return o instanceof ByteString ? ((ByteString)o).toStringUtf8() : Internal.toStringUtf8((byte[])o);
      }
   }

   private static ByteString asByteString(Object o) {
      if (o instanceof ByteString) {
         return (ByteString)o;
      } else {
         return o instanceof String ? ByteString.copyFromUtf8((String)o) : ByteString.copyFrom((byte[])o);
      }
   }

   private static byte[] asByteArray(Object o) {
      if (o instanceof byte[]) {
         return (byte[])o;
      } else {
         return o instanceof String ? Internal.toByteArray((String)o) : ((ByteString)o).toByteArray();
      }
   }

   public List getUnderlyingElements() {
      return Collections.unmodifiableList(this.list);
   }

   public void mergeFrom(LazyStringList other) {
      this.ensureIsMutable();

      for(Object o : other.getUnderlyingElements()) {
         if (o instanceof byte[]) {
            byte[] b = (byte[])o;
            this.list.add(Arrays.copyOf(b, b.length));
         } else {
            this.list.add(o);
         }
      }

   }

   public List asByteArrayList() {
      return new ByteArrayListView(this);
   }

   public List asByteStringList() {
      return new ByteStringListView(this);
   }

   public LazyStringList getUnmodifiableView() {
      return (LazyStringList)(this.isModifiable() ? new UnmodifiableLazyStringList(this) : this);
   }

   static {
      EMPTY = EMPTY_LIST;
   }

   private static class ByteArrayListView extends AbstractList implements RandomAccess {
      private final LazyStringArrayList list;

      ByteArrayListView(LazyStringArrayList list) {
         this.list = list;
      }

      public byte[] get(int index) {
         return this.list.getByteArray(index);
      }

      public int size() {
         return this.list.size();
      }

      public byte[] set(int index, byte[] s) {
         Object o = this.list.setAndReturn(index, s);
         ++this.modCount;
         return LazyStringArrayList.asByteArray(o);
      }

      public void add(int index, byte[] s) {
         this.list.add(index, s);
         ++this.modCount;
      }

      public byte[] remove(int index) {
         Object o = this.list.remove(index);
         ++this.modCount;
         return LazyStringArrayList.asByteArray(o);
      }
   }

   private static class ByteStringListView extends AbstractList implements RandomAccess {
      private final LazyStringArrayList list;

      ByteStringListView(LazyStringArrayList list) {
         this.list = list;
      }

      public ByteString get(int index) {
         return this.list.getByteString(index);
      }

      public int size() {
         return this.list.size();
      }

      public ByteString set(int index, ByteString s) {
         Object o = this.list.setAndReturn(index, s);
         ++this.modCount;
         return LazyStringArrayList.asByteString(o);
      }

      public void add(int index, ByteString s) {
         this.list.add(index, s);
         ++this.modCount;
      }

      public ByteString remove(int index) {
         Object o = this.list.remove(index);
         ++this.modCount;
         return LazyStringArrayList.asByteString(o);
      }
   }
}
