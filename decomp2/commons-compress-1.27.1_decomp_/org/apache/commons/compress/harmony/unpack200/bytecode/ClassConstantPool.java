package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.compress.harmony.unpack200.Segment;

public class ClassConstantPool {
   protected HashSet entriesContainsSet = new HashSet();
   protected HashSet othersContainsSet = new HashSet();
   private final HashSet mustStartClassPool = new HashSet();
   protected Map indexCache;
   private final List others = new ArrayList(500);
   private final List entries = new ArrayList(500);
   private boolean resolved;

   public ClassFileEntry add(ClassFileEntry entry) {
      if (entry instanceof ByteCode) {
         return null;
      } else {
         if (entry instanceof ConstantPoolEntry) {
            if (this.entriesContainsSet.add(entry)) {
               this.entries.add(entry);
            }
         } else if (this.othersContainsSet.add(entry)) {
            this.others.add(entry);
         }

         return entry;
      }
   }

   public void addNestedEntries() {
      boolean added = true;
      List<ClassFileEntry> parents = new ArrayList(512);
      List<ClassFileEntry> children = new ArrayList(512);
      parents.addAll(this.entries);
      parents.addAll(this.others);

      while(added || parents.size() > 0) {
         children.clear();
         int entriesOriginalSize = this.entries.size();
         int othersOriginalSize = this.others.size();

         for(int indexParents = 0; indexParents < parents.size(); ++indexParents) {
            ClassFileEntry entry = (ClassFileEntry)parents.get(indexParents);
            ClassFileEntry[] entryChildren = entry.getNestedClassFileEntries();
            children.addAll(Arrays.asList(entryChildren));
            boolean isAtStart = entry instanceof ByteCode && ((ByteCode)entry).nestedMustStartClassPool();
            if (isAtStart) {
               this.mustStartClassPool.addAll(Arrays.asList(entryChildren));
            }

            this.add(entry);
         }

         added = this.entries.size() != entriesOriginalSize || this.others.size() != othersOriginalSize;
         parents.clear();
         parents.addAll(children);
      }

   }

   public ClassFileEntry addWithNestedEntries(ClassFileEntry entry) {
      this.add(entry);

      for(ClassFileEntry nestedEntry : entry.getNestedClassFileEntries()) {
         this.addWithNestedEntries(nestedEntry);
      }

      return entry;
   }

   public List entries() {
      return Collections.unmodifiableList(this.entries);
   }

   public ClassFileEntry get(int i) {
      if (!this.resolved) {
         throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
      } else {
         --i;
         return (ClassFileEntry)this.entries.get(i);
      }
   }

   public int indexOf(ClassFileEntry entry) {
      if (!this.resolved) {
         throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
      } else if (null == this.indexCache) {
         throw new IllegalStateException("Index cache is not initialized!");
      } else {
         Integer entryIndex = (Integer)this.indexCache.get(entry);
         return entryIndex != null ? entryIndex + 1 : -1;
      }
   }

   private void initialSort() {
      TreeSet<ClassFileEntry> inCpAll = new TreeSet(Comparator.comparingInt((arg0) -> ((ConstantPoolEntry)arg0).getGlobalIndex()));
      TreeSet<ClassFileEntry> cpUtf8sNotInCpAll = new TreeSet(Comparator.comparing((arg0) -> ((CPUTF8)arg0).underlyingString()));
      TreeSet<ClassFileEntry> cpClassesNotInCpAll = new TreeSet(Comparator.comparing((arg0) -> ((CPClass)arg0).getName()));

      for(ClassFileEntry entry2 : this.entries) {
         ConstantPoolEntry entry = (ConstantPoolEntry)entry2;
         if (entry.getGlobalIndex() == -1) {
            if (entry instanceof CPUTF8) {
               cpUtf8sNotInCpAll.add(entry);
            } else {
               if (!(entry instanceof CPClass)) {
                  throw new Error("error");
               }

               cpClassesNotInCpAll.add(entry);
            }
         } else {
            inCpAll.add(entry);
         }
      }

      this.entries.clear();
      this.entries.addAll(inCpAll);
      this.entries.addAll(cpUtf8sNotInCpAll);
      this.entries.addAll(cpClassesNotInCpAll);
   }

   public void resolve(Segment segment) {
      this.initialSort();
      this.sortClassPool();
      this.resolved = true;
      this.entries.forEach((entry) -> entry.resolve(this));
      this.others.forEach((entry) -> entry.resolve(this));
   }

   public int size() {
      return this.entries.size();
   }

   protected void sortClassPool() {
      List<ClassFileEntry> startOfPool = new ArrayList(this.entries.size());
      List<ClassFileEntry> finalSort = new ArrayList(this.entries.size());

      for(ClassFileEntry entry : this.entries) {
         if (this.mustStartClassPool.contains(entry)) {
            startOfPool.add(entry);
         } else {
            finalSort.add(entry);
         }
      }

      this.indexCache = new HashMap(this.entries.size());
      int index = 0;
      this.entries.clear();

      for(ClassFileEntry entry : startOfPool) {
         this.indexCache.put(entry, index);
         if (!(entry instanceof CPLong) && !(entry instanceof CPDouble)) {
            this.entries.add(entry);
            ++index;
         } else {
            this.entries.add(entry);
            this.entries.add(entry);
            index += 2;
         }
      }

      for(ClassFileEntry entry : finalSort) {
         this.indexCache.put(entry, index);
         if (!(entry instanceof CPLong) && !(entry instanceof CPDouble)) {
            this.entries.add(entry);
            ++index;
         } else {
            this.entries.add(entry);
            this.entries.add(entry);
            index += 2;
         }
      }

   }
}
