package com.ibm.icu.impl;

import com.ibm.icu.text.StringTransform;
import com.ibm.icu.text.UTF16;
import com.ibm.icu.text.UnicodeSet;
import com.ibm.icu.text.UnicodeSetIterator;
import com.ibm.icu.util.Freezable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class UnicodeMap implements Cloneable, Freezable, StringTransform, Iterable {
   static final boolean ASSERTIONS = false;
   static final long GROWTH_PERCENT = 200L;
   static final long GROWTH_GAP = 10L;
   private int length;
   private int[] transitions;
   Object[] values;
   private LinkedHashSet availableValues = new LinkedHashSet();
   private transient boolean staleAvailableValues;
   private transient boolean errorOnReset;
   private transient volatile boolean locked;
   private int lastIndex;
   private TreeMap stringMap;
   static final boolean DEBUG_WRITE = false;

   public UnicodeMap() {
      this.clear();
   }

   public UnicodeMap(UnicodeMap other) {
      this.clear();
      this.putAll(other);
   }

   public UnicodeMap clear() {
      if (this.locked) {
         throw new UnsupportedOperationException("Attempt to modify locked object");
      } else {
         this.length = 2;
         this.transitions = new int[]{0, 1114112, 0, 0, 0, 0, 0, 0, 0, 0};
         this.values = new Object[10];
         this.availableValues.clear();
         this.staleAvailableValues = false;
         this.errorOnReset = false;
         this.lastIndex = 0;
         this.stringMap = null;
         return this;
      }
   }

   public boolean equals(Object other) {
      if (other == null) {
         return false;
      } else {
         try {
            UnicodeMap that = (UnicodeMap)other;
            if (this.length != that.length) {
               return false;
            } else {
               for(int i = 0; i < this.length - 1; ++i) {
                  if (this.transitions[i] != that.transitions[i]) {
                     return false;
                  }

                  if (!areEqual(this.values[i], that.values[i])) {
                     return false;
                  }
               }

               return true;
            }
         } catch (ClassCastException var4) {
            return false;
         }
      }
   }

   public static boolean areEqual(Object a, Object b) {
      if (a == b) {
         return true;
      } else {
         return a != null && b != null ? a.equals(b) : false;
      }
   }

   public int hashCode() {
      int result = this.length;

      for(int i = 0; i < this.length - 1; ++i) {
         result = 37 * result + this.transitions[i];
         result = 37 * result;
         if (this.values[i] != null) {
            result += this.values[i].hashCode();
         }
      }

      if (this.stringMap != null) {
         result = 37 * result + this.stringMap.hashCode();
      }

      return result;
   }

   public UnicodeMap cloneAsThawed() {
      UnicodeMap<T> that = new UnicodeMap();
      that.length = this.length;
      that.transitions = (int[])this.transitions.clone();
      that.values = this.values.clone();
      that.availableValues = new LinkedHashSet(this.availableValues);
      that.locked = false;
      that.stringMap = this.stringMap == null ? null : (TreeMap)this.stringMap.clone();
      return that;
   }

   void _checkInvariants() {
      if (this.length >= 2 && this.length <= this.transitions.length && this.transitions.length == this.values.length) {
         for(int i = 1; i < this.length - 1; ++i) {
            if (areEqual(this.values[i - 1], this.values[i])) {
               throw new IllegalArgumentException("Invariant failed: values shared at \t" + Utility.hex((long)(i - 1)) + ": <" + this.values[i - 1] + ">\t" + Utility.hex((long)i) + ": <" + this.values[i] + ">");
            }
         }

         if (this.transitions[0] == 0 && this.transitions[this.length - 1] == 1114112) {
            for(int i = 1; i < this.length - 1; ++i) {
               if (this.transitions[i - 1] >= this.transitions[i]) {
                  throw new IllegalArgumentException("Invariant failed: not monotonic\t" + Utility.hex((long)(i - 1)) + ": " + this.transitions[i - 1] + "\t" + Utility.hex((long)i) + ": " + this.transitions[i]);
               }
            }

         } else {
            throw new IllegalArgumentException("Invariant failed: bounds set wrong");
         }
      } else {
         throw new IllegalArgumentException("Invariant failed: Lengths bad");
      }
   }

   private int _findIndex(int c) {
      int lo = 0;
      int hi = this.length - 1;

      for(int i = lo + hi >>> 1; i != lo; i = lo + hi >>> 1) {
         if (c < this.transitions[i]) {
            hi = i;
         } else {
            lo = i;
         }
      }

      return lo;
   }

   private void _checkFind(int codepoint, int value) {
      int other = this.__findIndex(codepoint);
      if (other != value) {
         throw new IllegalArgumentException("Invariant failed: binary search\t" + Utility.hex((long)codepoint) + ": " + value + "\tshould be: " + other);
      }
   }

   private int __findIndex(int codepoint) {
      for(int i = this.length - 1; i > 0; --i) {
         if (this.transitions[i] <= codepoint) {
            return i;
         }
      }

      return 0;
   }

   private void _removeAt(int index, int count) {
      for(int i = index + count; i < this.length; ++i) {
         this.transitions[i - count] = this.transitions[i];
         this.values[i - count] = this.values[i];
      }

      this.length -= count;
   }

   private void _insertGapAt(int index, int count) {
      int newLength = this.length + count;
      int[] oldtransitions = this.transitions;
      T[] oldvalues = (T[])this.values;
      if (newLength > this.transitions.length) {
         int allocation = (int)(10L + (long)newLength * 200L / 100L);
         this.transitions = new int[allocation];
         this.values = new Object[allocation];

         for(int i = 0; i < index; ++i) {
            this.transitions[i] = oldtransitions[i];
            this.values[i] = oldvalues[i];
         }
      }

      for(int i = this.length - 1; i >= index; --i) {
         this.transitions[i + count] = oldtransitions[i];
         this.values[i + count] = oldvalues[i];
      }

      this.length = newLength;
   }

   private UnicodeMap _put(int codepoint, Object value) {
      int baseIndex;
      if (this.transitions[this.lastIndex] <= codepoint && codepoint < this.transitions[this.lastIndex + 1]) {
         baseIndex = this.lastIndex;
      } else {
         baseIndex = this._findIndex(codepoint);
      }

      int limitIndex = baseIndex + 1;
      if (areEqual(this.values[baseIndex], value)) {
         return this;
      } else if (this.locked) {
         throw new UnsupportedOperationException("Attempt to modify locked object");
      } else if (this.errorOnReset && this.values[baseIndex] != null) {
         throw new UnsupportedOperationException("Attempt to reset value for " + Utility.hex((long)codepoint) + " when that is disallowed. Old: " + this.values[baseIndex] + "; New: " + value);
      } else {
         this.staleAvailableValues = true;
         this.availableValues.add(value);
         int baseCP = this.transitions[baseIndex];
         int limitCP = this.transitions[limitIndex];
         if (baseCP == codepoint) {
            boolean connectsWithPrevious = baseIndex != 0 && areEqual(value, this.values[baseIndex - 1]);
            if (limitCP == codepoint + 1) {
               boolean connectsWithFollowing = baseIndex < this.length - 2 && areEqual(value, this.values[limitIndex]);
               if (connectsWithPrevious) {
                  if (connectsWithFollowing) {
                     this._removeAt(baseIndex, 2);
                  } else {
                     this._removeAt(baseIndex, 1);
                  }

                  --baseIndex;
               } else if (connectsWithFollowing) {
                  this._removeAt(baseIndex, 1);
                  this.transitions[baseIndex] = codepoint;
               } else {
                  this.values[baseIndex] = value;
               }
            } else if (connectsWithPrevious) {
               int var10002 = this.transitions[baseIndex]++;
            } else {
               this.transitions[baseIndex] = codepoint + 1;
               this._insertGapAt(baseIndex, 1);
               this.values[baseIndex] = value;
               this.transitions[baseIndex] = codepoint;
            }
         } else if (limitCP == codepoint + 1) {
            boolean connectsWithFollowing = baseIndex < this.length - 2 && areEqual(value, this.values[limitIndex]);
            if (connectsWithFollowing) {
               int var10 = this.transitions[limitIndex]--;
               return this;
            }

            this._insertGapAt(limitIndex, 1);
            this.transitions[limitIndex] = codepoint;
            this.values[limitIndex] = value;
         } else {
            ++baseIndex;
            this._insertGapAt(baseIndex, 2);
            this.transitions[baseIndex] = codepoint;
            this.values[baseIndex] = value;
            this.transitions[baseIndex + 1] = codepoint + 1;
            this.values[baseIndex + 1] = this.values[baseIndex - 1];
         }

         this.lastIndex = baseIndex;
         return this;
      }
   }

   private UnicodeMap _putAll(int startCodePoint, int endCodePoint, Object value) {
      for(int i = startCodePoint; i <= endCodePoint; ++i) {
         this._put(i, value);
      }

      return this;
   }

   public UnicodeMap put(int codepoint, Object value) {
      if (codepoint >= 0 && codepoint <= 1114111) {
         this._put(codepoint, value);
         return this;
      } else {
         throw new IllegalArgumentException("Codepoint out of range: " + codepoint);
      }
   }

   public UnicodeMap put(String string, Object value) {
      int v = UnicodeSet.getSingleCodePoint(string);
      if (v == Integer.MAX_VALUE) {
         if (this.locked) {
            throw new UnsupportedOperationException("Attempt to modify locked object");
         } else {
            if (value != null) {
               if (this.stringMap == null) {
                  this.stringMap = new TreeMap();
               }

               this.stringMap.put(string, value);
               this.staleAvailableValues = true;
            } else if (this.stringMap != null && this.stringMap.remove(string) != null) {
               this.staleAvailableValues = true;
            }

            return this;
         }
      } else {
         return this.put(v, value);
      }
   }

   public UnicodeMap putAll(UnicodeSet codepoints, Object value) {
      UnicodeSetIterator it = new UnicodeSetIterator(codepoints);

      while(it.nextRange()) {
         if (it.string == null) {
            this._putAll(it.codepoint, it.codepointEnd, value);
         } else {
            this.put(it.string, value);
         }
      }

      return this;
   }

   public UnicodeMap putAll(int startCodePoint, int endCodePoint, Object value) {
      if (this.locked) {
         throw new UnsupportedOperationException("Attempt to modify locked object");
      } else if (startCodePoint >= 0 && endCodePoint <= 1114111) {
         return this._putAll(startCodePoint, endCodePoint, value);
      } else {
         throw new IllegalArgumentException("Codepoint out of range: " + Utility.hex((long)startCodePoint) + ".." + Utility.hex((long)endCodePoint));
      }
   }

   public UnicodeMap putAll(UnicodeMap unicodeMap) {
      for(int i = 0; i < unicodeMap.length; ++i) {
         T value = (T)unicodeMap.values[i];
         if (value != null) {
            this._putAll(unicodeMap.transitions[i], unicodeMap.transitions[i + 1] - 1, value);
         }
      }

      if (unicodeMap.stringMap != null && !unicodeMap.stringMap.isEmpty()) {
         if (this.stringMap == null) {
            this.stringMap = new TreeMap();
         }

         this.stringMap.putAll(unicodeMap.stringMap);
      }

      return this;
   }

   public UnicodeMap putAllFiltered(UnicodeMap prop, UnicodeSet filter) {
      UnicodeSetIterator it = new UnicodeSetIterator(filter);

      while(it.next()) {
         if (it.codepoint != -1) {
            T value = (T)prop.getValue(it.codepoint);
            if (value != null) {
               this._put(it.codepoint, value);
            }
         }
      }

      for(String key : filter.strings()) {
         T value = (T)prop.get(key);
         if (value != null) {
            this.put(key, value);
         }
      }

      return this;
   }

   public UnicodeMap setMissing(Object value) {
      if (!this.getAvailableValues().contains(value)) {
         this.staleAvailableValues = true;
         this.availableValues.add(value);

         for(int i = 0; i < this.length; ++i) {
            if (this.values[i] == null) {
               this.values[i] = value;
            }
         }

         return this;
      } else {
         return this.putAll(this.keySet((Object)null), value);
      }
   }

   public UnicodeSet keySet(Object value, UnicodeSet result) {
      if (result == null) {
         result = new UnicodeSet();
      }

      for(int i = 0; i < this.length - 1; ++i) {
         if (areEqual(value, this.values[i])) {
            result.add(this.transitions[i], this.transitions[i + 1] - 1);
         }
      }

      if (value != null && this.stringMap != null) {
         for(String key : this.stringMap.keySet()) {
            T newValue = (T)this.stringMap.get(key);
            if (value.equals(newValue)) {
               result.add((CharSequence)key);
            }
         }
      }

      return result;
   }

   public UnicodeSet keySet(Object value) {
      return this.keySet(value, (UnicodeSet)null);
   }

   public UnicodeSet keySet() {
      UnicodeSet result = new UnicodeSet();

      for(int i = 0; i < this.length - 1; ++i) {
         if (this.values[i] != null) {
            result.add(this.transitions[i], this.transitions[i + 1] - 1);
         }
      }

      if (this.stringMap != null) {
         result.addAll((Iterable)this.stringMap.keySet());
      }

      return result;
   }

   public Collection values(Collection result) {
      if (this.staleAvailableValues) {
         Set<T> temp = new HashSet();

         for(int i = 0; i < this.length - 1; ++i) {
            if (this.values[i] != null) {
               temp.add(this.values[i]);
            }
         }

         this.availableValues.retainAll(temp);
         if (this.stringMap != null) {
            this.availableValues.addAll(this.stringMap.values());
         }

         this.staleAvailableValues = false;
      }

      if (result == null) {
         result = (U)(new LinkedHashSet(this.availableValues.size()));
      }

      result.addAll(this.availableValues);
      return result;
   }

   public Set values() {
      return (Set)this.getAvailableValues((Collection)null);
   }

   public Object get(int codepoint) {
      if (codepoint >= 0 && codepoint <= 1114111) {
         return this.values[this._findIndex(codepoint)];
      } else {
         throw new IllegalArgumentException("Codepoint out of range: " + codepoint);
      }
   }

   public Object get(String value) {
      if (UTF16.hasMoreCodePointsThan((String)value, 1)) {
         return this.stringMap == null ? null : this.stringMap.get(value);
      } else {
         return this.getValue(UTF16.charAt((String)value, 0));
      }
   }

   public String transform(String source) {
      StringBuffer result = new StringBuffer();

      int cp;
      for(int i = 0; i < source.length(); i += UTF16.getCharCount(cp)) {
         cp = UTF16.charAt(source, i);
         T mResult = (T)this.getValue(cp);
         if (mResult != null) {
            result.append(mResult);
         } else {
            UTF16.append(result, cp);
         }
      }

      return result.toString();
   }

   public UnicodeMap composeWith(UnicodeMap other, Composer composer) {
      for(Object value : other.getAvailableValues()) {
         UnicodeSet set = other.keySet(value);
         this.composeWith(set, value, composer);
      }

      return this;
   }

   public UnicodeMap composeWith(UnicodeSet set, Object value, Composer composer) {
      UnicodeSetIterator it = new UnicodeSetIterator(set);

      while(it.next()) {
         int i = it.codepoint;
         if (i == -1) {
            String s = it.string;
            T v1 = (T)this.getValue(s);
            T v3 = (T)composer.compose(-1, s, v1, value);
            if (v1 != v3 && (v1 == null || !v1.equals(v3))) {
               this.put(s, v3);
            }
         } else {
            T v1 = (T)this.getValue(i);
            T v3 = (T)composer.compose(i, (String)null, v1, value);
            if (v1 != v3 && (v1 == null || !v1.equals(v3))) {
               this.put(i, v3);
            }
         }
      }

      return this;
   }

   public String toString() {
      return this.toString((Comparator)null);
   }

   public String toString(Comparator collected) {
      StringBuffer result = new StringBuffer();
      if (collected == null) {
         for(int i = 0; i < this.length - 1; ++i) {
            T value = (T)this.values[i];
            if (value != null) {
               int start = this.transitions[i];
               int end = this.transitions[i + 1] - 1;
               result.append(Utility.hex((long)start));
               if (start != end) {
                  result.append("-").append(Utility.hex((long)end));
               }

               result.append("=").append(value.toString()).append("\n");
            }
         }

         if (this.stringMap != null) {
            for(String s : this.stringMap.keySet()) {
               result.append(Utility.hex(s)).append("=").append(this.stringMap.get(s).toString()).append("\n");
            }
         }
      } else {
         for(Object value : (Set)this.values(new TreeSet(collected))) {
            UnicodeSet s = this.keySet(value);
            result.append(value).append("=").append(s.toString()).append("\n");
         }
      }

      return result.toString();
   }

   public boolean getErrorOnReset() {
      return this.errorOnReset;
   }

   public UnicodeMap setErrorOnReset(boolean errorOnReset) {
      this.errorOnReset = errorOnReset;
      return this;
   }

   public boolean isFrozen() {
      return this.locked;
   }

   public UnicodeMap freeze() {
      this.locked = true;
      return this;
   }

   public static int findCommonPrefix(String last, String s) {
      int minLen = Math.min(last.length(), s.length());

      for(int i = 0; i < minLen; ++i) {
         if (last.charAt(i) != s.charAt(i)) {
            return i;
         }
      }

      return minLen;
   }

   public int getRangeCount() {
      return this.length - 1;
   }

   public int getRangeStart(int range) {
      return this.transitions[range];
   }

   public int getRangeEnd(int range) {
      return this.transitions[range + 1] - 1;
   }

   public Object getRangeValue(int range) {
      return this.values[range];
   }

   public Set getNonRangeStrings() {
      return this.stringMap != null && !this.stringMap.isEmpty() ? Collections.unmodifiableSet(this.stringMap.keySet()) : null;
   }

   public boolean containsKey(String key) {
      return this.getValue(key) != null;
   }

   public boolean containsKey(int key) {
      return this.getValue(key) != null;
   }

   public boolean containsValue(Object value) {
      return this.getAvailableValues().contains(value);
   }

   public boolean isEmpty() {
      return this.size() == 0;
   }

   public UnicodeMap putAll(Map map) {
      for(String key : map.keySet()) {
         this.put(key, map.get(key));
      }

      return this;
   }

   /** @deprecated */
   public UnicodeMap putAllIn(Map map) {
      for(String key : this.keySet()) {
         map.put(key, this.get(key));
      }

      return this;
   }

   public Map putAllInto(Map map) {
      for(EntryRange entry : this.entryRanges()) {
         if (entry.string != null) {
            break;
         }

         for(int cp = entry.codepoint; cp <= entry.codepointEnd; ++cp) {
            map.put(UTF16.valueOf(cp), entry.value);
         }
      }

      map.putAll(this.stringMap);
      return map;
   }

   public Map putAllCodepointsInto(Map map) {
      for(EntryRange entry : this.entryRanges()) {
         if (entry.string != null) {
            break;
         }

         for(int cp = entry.codepoint; cp <= entry.codepointEnd; ++cp) {
            map.put(cp, entry.value);
         }
      }

      return map;
   }

   public UnicodeMap remove(String key) {
      return this.put(key, (Object)null);
   }

   public UnicodeMap remove(int key) {
      return this.put(key, (Object)null);
   }

   public int size() {
      int result = this.stringMap == null ? 0 : this.stringMap.size();

      for(int i = 0; i < this.length - 1; ++i) {
         T value = (T)this.values[i];
         if (value != null) {
            result += this.transitions[i + 1] - this.transitions[i];
         }
      }

      return result;
   }

   public Iterable entrySet() {
      return new EntrySetX();
   }

   public Iterable entryRanges() {
      return new EntryRanges();
   }

   public Iterator iterator() {
      return this.keySet().iterator();
   }

   public Object getValue(String key) {
      return this.get(key);
   }

   public Object getValue(int key) {
      return this.get(key);
   }

   public Collection getAvailableValues() {
      return this.values();
   }

   public Collection getAvailableValues(Collection result) {
      return this.values(result);
   }

   public UnicodeSet getSet(Object value) {
      return this.keySet(value);
   }

   public UnicodeSet getSet(Object value, UnicodeSet result) {
      return this.keySet(value, result);
   }

   public final UnicodeMap removeAll(UnicodeSet set) {
      return this.putAll(set, (Object)null);
   }

   public final UnicodeMap removeAll(UnicodeMap reference) {
      return this.removeRetainAll(reference, true);
   }

   public final UnicodeMap retainAll(UnicodeSet set) {
      UnicodeSet toNuke = new UnicodeSet();

      for(EntryRange ae : this.entryRanges()) {
         if (ae.string != null) {
            if (!set.contains(ae.string)) {
               toNuke.add((CharSequence)ae.string);
            }
         } else {
            for(int i = ae.codepoint; i <= ae.codepointEnd; ++i) {
               if (!set.contains(i)) {
                  toNuke.add(i);
               }
            }
         }
      }

      return this.putAll(toNuke, (Object)null);
   }

   public final UnicodeMap retainAll(UnicodeMap reference) {
      return this.removeRetainAll(reference, false);
   }

   private final UnicodeMap removeRetainAll(UnicodeMap reference, boolean remove) {
      UnicodeSet toNuke = new UnicodeSet();

      for(EntryRange ae : this.entryRanges()) {
         if (ae.string != null) {
            if (ae.value.equals(reference.get(ae.string)) == remove) {
               toNuke.add((CharSequence)ae.string);
            }
         } else {
            for(int i = ae.codepoint; i <= ae.codepointEnd; ++i) {
               if (ae.value.equals(reference.get(i)) == remove) {
                  toNuke.add(i);
               }
            }
         }
      }

      return this.putAll(toNuke, (Object)null);
   }

   public final Set stringKeys() {
      return this.getNonRangeStrings();
   }

   public Map addInverseTo(Map target) {
      for(Object value : this.values()) {
         UnicodeSet uset = this.getSet(value);
         target.put(value, uset);
      }

      return target;
   }

   public static Map freeze(Map target) {
      for(UnicodeSet entry : target.values()) {
         entry.freeze();
      }

      return Collections.unmodifiableMap(target);
   }

   public UnicodeMap putAllInverse(Map source) {
      for(Map.Entry entry : source.entrySet()) {
         this.putAll((UnicodeSet)entry.getValue(), entry.getKey());
      }

      return this;
   }

   public abstract static class Composer {
      public abstract Object compose(int var1, String var2, Object var3, Object var4);
   }

   private class EntrySetX implements Iterable {
      private EntrySetX() {
      }

      public Iterator iterator() {
         return UnicodeMap.this.new IteratorX();
      }

      public String toString() {
         StringBuffer b = new StringBuffer();

         for(Object item : this) {
            b.append(item.toString()).append(' ');
         }

         return b.toString();
      }
   }

   private class IteratorX implements Iterator {
      Iterator iterator;

      private IteratorX() {
         this.iterator = UnicodeMap.this.keySet().iterator();
      }

      public boolean hasNext() {
         return this.iterator.hasNext();
      }

      public Map.Entry next() {
         String key = (String)this.iterator.next();
         return new ImmutableEntry(key, UnicodeMap.this.get(key));
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   public static class EntryRange {
      public int codepoint;
      public int codepointEnd;
      public String string;
      public Object value;

      public String toString() {
         return (this.string != null ? Utility.hex(this.string) : Utility.hex((long)this.codepoint) + (this.codepoint == this.codepointEnd ? "" : ".." + Utility.hex((long)this.codepointEnd))) + "=" + this.value;
      }
   }

   private class EntryRanges implements Iterable, Iterator {
      private int pos;
      private EntryRange result;
      private int lastRealRange;
      private Iterator stringIterator;

      private EntryRanges() {
         this.result = new EntryRange();
         this.lastRealRange = UnicodeMap.this.values[UnicodeMap.this.length - 2] == null ? UnicodeMap.this.length - 2 : UnicodeMap.this.length - 1;
         this.stringIterator = UnicodeMap.this.stringMap == null ? null : UnicodeMap.this.stringMap.entrySet().iterator();
      }

      public Iterator iterator() {
         return this;
      }

      public boolean hasNext() {
         return this.pos < this.lastRealRange || this.stringIterator != null && this.stringIterator.hasNext();
      }

      public EntryRange next() {
         if (this.pos < this.lastRealRange) {
            T temp = (T)UnicodeMap.this.values[this.pos];
            if (temp == null) {
               temp = (T)UnicodeMap.this.values[++this.pos];
            }

            this.result.codepoint = UnicodeMap.this.transitions[this.pos];
            this.result.codepointEnd = UnicodeMap.this.transitions[this.pos + 1] - 1;
            this.result.string = null;
            this.result.value = temp;
            ++this.pos;
         } else {
            Map.Entry<String, T> entry = (Map.Entry)this.stringIterator.next();
            this.result.codepoint = this.result.codepointEnd = -1;
            this.result.string = (String)entry.getKey();
            this.result.value = entry.getValue();
         }

         return this.result;
      }

      public void remove() {
         throw new UnsupportedOperationException();
      }
   }
}
