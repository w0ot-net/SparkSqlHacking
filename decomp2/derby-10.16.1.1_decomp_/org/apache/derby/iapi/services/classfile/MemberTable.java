package org.apache.derby.iapi.services.classfile;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

class MemberTable {
   protected Vector entries;
   private Hashtable hashtable;
   private MemberTableHash mutableMTH = null;

   public MemberTable(int var1) {
      this.entries = new Vector(var1);
      this.hashtable = new Hashtable(var1 > 50 ? var1 : 50);
      this.mutableMTH = new MemberTableHash((String)null, (String)null);
   }

   void addEntry(ClassMember var1) {
      MemberTableHash var2 = new MemberTableHash(var1.getName(), var1.getDescriptor(), this.entries.size());
      this.entries.add(var1);
      this.hashtable.put(var2, var2);
   }

   ClassMember find(String var1, String var2) {
      this.mutableMTH.name = var1;
      this.mutableMTH.descriptor = var2;
      this.mutableMTH.setHashCode();
      MemberTableHash var3 = (MemberTableHash)this.hashtable.get(this.mutableMTH);
      return var3 == null ? null : (ClassMember)this.entries.get(var3.index);
   }

   void put(ClassFormatOutput var1) throws IOException {
      Vector var2 = this.entries;
      int var3 = var2.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ((ClassMember)var2.get(var4)).put(var1);
      }

   }

   int size() {
      return this.entries.size();
   }

   int classFileSize() {
      int var1 = 0;
      Vector var2 = this.entries;
      int var3 = var2.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         var1 += ((ClassMember)var2.get(var4)).classFileSize();
      }

      return var1;
   }
}
