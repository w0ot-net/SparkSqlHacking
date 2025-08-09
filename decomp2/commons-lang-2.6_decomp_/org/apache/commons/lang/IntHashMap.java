package org.apache.commons.lang;

class IntHashMap {
   private transient Entry[] table;
   private transient int count;
   private int threshold;
   private final float loadFactor;

   public IntHashMap() {
      this(20, 0.75F);
   }

   public IntHashMap(int initialCapacity) {
      this(initialCapacity, 0.75F);
   }

   public IntHashMap(int initialCapacity, float loadFactor) {
      if (initialCapacity < 0) {
         throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
      } else if (loadFactor <= 0.0F) {
         throw new IllegalArgumentException("Illegal Load: " + loadFactor);
      } else {
         if (initialCapacity == 0) {
            initialCapacity = 1;
         }

         this.loadFactor = loadFactor;
         this.table = new Entry[initialCapacity];
         this.threshold = (int)((float)initialCapacity * loadFactor);
      }
   }

   public int size() {
      return this.count;
   }

   public boolean isEmpty() {
      return this.count == 0;
   }

   public boolean contains(Object value) {
      if (value == null) {
         throw new NullPointerException();
      } else {
         Entry[] tab = this.table;
         int i = tab.length;

         while(i-- > 0) {
            for(Entry e = tab[i]; e != null; e = e.next) {
               if (e.value.equals(value)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public boolean containsValue(Object value) {
      return this.contains(value);
   }

   public boolean containsKey(int key) {
      Entry[] tab = this.table;
      int hash = key;
      int index = (key & Integer.MAX_VALUE) % tab.length;

      for(Entry e = tab[index]; e != null; e = e.next) {
         if (e.hash == hash) {
            return true;
         }
      }

      return false;
   }

   public Object get(int key) {
      Entry[] tab = this.table;
      int hash = key;
      int index = (key & Integer.MAX_VALUE) % tab.length;

      for(Entry e = tab[index]; e != null; e = e.next) {
         if (e.hash == hash) {
            return e.value;
         }
      }

      return null;
   }

   protected void rehash() {
      int oldCapacity = this.table.length;
      Entry[] oldMap = this.table;
      int newCapacity = oldCapacity * 2 + 1;
      Entry[] newMap = new Entry[newCapacity];
      this.threshold = (int)((float)newCapacity * this.loadFactor);
      this.table = newMap;
      int i = oldCapacity;

      Entry e;
      int index;
      while(i-- > 0) {
         for(Entry old = oldMap[i]; old != null; newMap[index] = e) {
            e = old;
            old = old.next;
            index = (e.hash & Integer.MAX_VALUE) % newCapacity;
            e.next = newMap[index];
         }
      }

   }

   public Object put(int key, Object value) {
      Entry[] tab = this.table;
      int hash = key;
      int index = (key & Integer.MAX_VALUE) % tab.length;

      for(Entry e = tab[index]; e != null; e = e.next) {
         if (e.hash == hash) {
            Object old = e.value;
            e.value = value;
            return old;
         }
      }

      if (this.count >= this.threshold) {
         this.rehash();
         tab = this.table;
         index = (hash & Integer.MAX_VALUE) % tab.length;
      }

      Entry e = new Entry(hash, key, value, tab[index]);
      tab[index] = e;
      ++this.count;
      return null;
   }

   public Object remove(int key) {
      Entry[] tab = this.table;
      int hash = key;
      int index = (key & Integer.MAX_VALUE) % tab.length;
      Entry e = tab[index];

      for(Entry prev = null; e != null; e = e.next) {
         if (e.hash == hash) {
            if (prev != null) {
               prev.next = e.next;
            } else {
               tab[index] = e.next;
            }

            --this.count;
            Object oldValue = e.value;
            e.value = null;
            return oldValue;
         }

         prev = e;
      }

      return null;
   }

   public synchronized void clear() {
      Entry[] tab = this.table;
      int index = tab.length;

      while(true) {
         --index;
         if (index < 0) {
            this.count = 0;
            return;
         }

         tab[index] = null;
      }
   }

   private static class Entry {
      final int hash;
      final int key;
      Object value;
      Entry next;

      protected Entry(int hash, int key, Object value, Entry next) {
         this.hash = hash;
         this.key = key;
         this.value = value;
         this.next = next;
      }
   }
}
