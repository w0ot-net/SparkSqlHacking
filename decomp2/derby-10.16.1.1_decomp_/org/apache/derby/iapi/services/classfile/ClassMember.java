package org.apache.derby.iapi.services.classfile;

import java.io.IOException;

public class ClassMember {
   protected ClassHolder cpt;
   protected int access_flags;
   protected int name_index;
   protected int descriptor_index;
   protected Attributes attribute_info;

   ClassMember(ClassHolder var1, int var2, int var3, int var4) {
      this.cpt = var1;
      this.name_index = var3;
      this.descriptor_index = var4;
      this.access_flags = var2;
   }

   public int getModifier() {
      return this.access_flags;
   }

   public String getDescriptor() {
      return this.cpt.nameIndexToString(this.descriptor_index);
   }

   public String getName() {
      return this.cpt.nameIndexToString(this.name_index);
   }

   public void addAttribute(String var1, ClassFormatOutput var2) {
      if (this.attribute_info == null) {
         this.attribute_info = new Attributes(1);
      }

      this.attribute_info.addEntry(new AttributeEntry(this.cpt.addUtf8(var1), var2));
   }

   void put(ClassFormatOutput var1) throws IOException {
      var1.putU2(this.access_flags);
      var1.putU2(this.name_index);
      var1.putU2(this.descriptor_index);
      if (this.attribute_info != null) {
         var1.putU2(this.attribute_info.size());
         this.attribute_info.put(var1);
      } else {
         var1.putU2(0);
      }

   }

   int classFileSize() {
      int var1 = 8;
      if (this.attribute_info != null) {
         var1 += this.attribute_info.classFileSize();
      }

      return var1;
   }
}
