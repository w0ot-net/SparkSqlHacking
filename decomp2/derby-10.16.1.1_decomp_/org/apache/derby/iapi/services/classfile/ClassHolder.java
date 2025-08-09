package org.apache.derby.iapi.services.classfile;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.derby.iapi.util.ByteArray;

public class ClassHolder {
   protected int minor_version;
   protected int major_version;
   protected int access_flags;
   protected int this_class;
   protected int super_class;
   protected int[] interfaces;
   protected MemberTable field_info;
   protected MemberTable method_info;
   protected Attributes attribute_info;
   protected Hashtable cptHashTable;
   protected Vector cptEntries;
   private int cptEstimatedSize;
   private final CONSTANT_Index_info searchIndex;

   protected ClassHolder(int var1) {
      this.minor_version = 3;
      this.major_version = 45;
      this.searchIndex = new CONSTANT_Index_info(0, 0, 0);
      this.cptEntries = new Vector(var1);
      this.cptHashTable = new Hashtable(var1, 0.75F);
      this.cptEntries.setSize(1);
   }

   public ClassHolder(String var1, String var2, int var3) {
      this(100);
      this.access_flags = var3 | 32;
      this.this_class = this.addClassReference(var1);
      this.super_class = this.addClassReference(var2);
      this.method_info = new MemberTable(0);
   }

   private void put(ClassFormatOutput var1) throws IOException {
      var1.putU4(-889275714);
      var1.putU2(this.minor_version);
      var1.putU2(this.major_version);
      var1.putU2("constant_pool", this.cptEntries.size());
      this.cptPut(var1);
      var1.putU2(this.access_flags);
      var1.putU2(this.this_class);
      var1.putU2(this.super_class);
      if (this.interfaces != null) {
         int var2 = this.interfaces.length;
         var1.putU2(var2);

         for(int var3 = 0; var3 < var2; ++var3) {
            var1.putU2(this.interfaces[var3]);
         }
      } else {
         var1.putU2(0);
      }

      if (this.field_info != null) {
         var1.putU2(this.field_info.size());
         this.field_info.put(var1);
      } else {
         var1.putU2(0);
      }

      if (this.method_info != null) {
         var1.putU2(this.method_info.size());
         this.method_info.put(var1);
      } else {
         var1.putU2(0);
      }

      if (this.attribute_info != null) {
         var1.putU2(this.attribute_info.size());
         this.attribute_info.put(var1);
      } else {
         var1.putU2(0);
      }

   }

   public ByteArray getFileFormat() throws IOException {
      int var1 = 24;
      var1 += this.cptEstimatedSize;
      if (this.interfaces != null) {
         var1 += this.interfaces.length * 2;
      }

      if (this.field_info != null) {
         var1 += this.field_info.classFileSize();
      }

      if (this.method_info != null) {
         var1 += this.method_info.classFileSize();
      }

      if (this.attribute_info != null) {
         var1 += this.attribute_info.classFileSize();
      }

      ClassFormatOutput var2 = new ClassFormatOutput(var1 + 200);
      this.put(var2);
      return new ByteArray(var2.getData(), 0, var2.size());
   }

   public int getModifier() {
      return this.access_flags;
   }

   public String getName() {
      return this.className(this.this_class).replace('/', '.');
   }

   public ClassMember addMember(String var1, String var2, int var3) {
      CONSTANT_Utf8_info var4 = this.addUtf8Entry(var1);
      int var5 = var4.getIndex();
      int var6 = this.addUtf8Entry(var2).getIndex();
      ClassMember var7 = new ClassMember(this, var3, var5, var6);
      MemberTable var8;
      if (var2.startsWith("(")) {
         var8 = this.method_info;
         if (var8 == null) {
            var8 = this.method_info = new MemberTable(0);
         }
      } else {
         var8 = this.field_info;
         if (var8 == null) {
            var8 = this.field_info = new MemberTable(0);
         }
      }

      var8.addEntry(var7);
      return var7;
   }

   public int addFieldReference(String var1, String var2, String var3) {
      return this.addReference(9, var1, var2, var3);
   }

   public int addFieldReference(ClassMember var1) {
      return this.addReference(9, var1);
   }

   public int addMethodReference(String var1, String var2, String var3, boolean var4) {
      int var5 = var4 ? 11 : 10;
      return this.addReference(var5, var1, var2, var3);
   }

   private int addReference(int var1, String var2, String var3, String var4) {
      int var5 = this.addClassReference(var2);
      int var6 = this.addNameAndType(var3, var4);
      return this.addIndexReference(var1, var5, var6);
   }

   private int addReference(int var1, ClassMember var2) {
      int var3 = this.addIndexReference(12, var2.name_index, var2.descriptor_index);
      return this.addIndexReference(var1, this.this_class, var3);
   }

   public int addConstant(String var1) {
      return this.addString(var1);
   }

   public int addUtf8(String var1) {
      return this.addUtf8Entry(var1).getIndex();
   }

   public int addConstant(int var1) {
      return this.addDirectEntry(new CONSTANT_Integer_info(var1));
   }

   public int addConstant(float var1) {
      return this.addDirectEntry(new CONSTANT_Float_info(var1));
   }

   public int addConstant(long var1) {
      return this.addDirectEntry(new CONSTANT_Long_info(var1));
   }

   public int addConstant(double var1) {
      return this.addDirectEntry(new CONSTANT_Double_info(var1));
   }

   public int getConstantPoolIndex() {
      return this.this_class;
   }

   public void addAttribute(String var1, ClassFormatOutput var2) {
      if (this.attribute_info == null) {
         this.attribute_info = new Attributes(1);
      }

      CONSTANT_Utf8_info var3 = this.addUtf8Entry(var1);
      int var4 = var3.getIndex();
      this.attribute_info.addEntry(new AttributeEntry(var4, var2));
   }

   public String getSuperClassName() {
      return this.super_class == 0 ? null : this.className(this.super_class).replace('/', '.');
   }

   protected int addEntry(Object var1, ConstantPoolEntry var2) {
      var2.setIndex(this.cptEntries.size());
      if (var1 != null) {
         this.cptHashTable.put(var1, var2);
      }

      this.cptEntries.add(var2);
      this.cptEstimatedSize += var2.classFileSize();
      if (var2.doubleSlot()) {
         this.cptEntries.add((Object)null);
         return 2;
      } else {
         return 1;
      }
   }

   private int addDirectEntry(ConstantPoolEntry var1) {
      ConstantPoolEntry var2 = this.findMatchingEntry(var1);
      if (var2 != null) {
         var1 = var2;
      } else {
         this.addEntry(var1.getKey(), var1);
      }

      return var1.getIndex();
   }

   private int addIndexReference(int var1, int var2, int var3) {
      this.searchIndex.set(var1, var2, var3);
      Object var4 = this.findMatchingEntry(this.searchIndex);
      if (var4 == null) {
         var4 = new CONSTANT_Index_info(var1, var2, var3);
         this.addEntry(((ConstantPoolEntry)var4).getKey(), (ConstantPoolEntry)var4);
      }

      return ((ConstantPoolEntry)var4).getIndex();
   }

   public int addClassReference(String var1) {
      if (isExternalClassName(var1)) {
         var1 = convertToInternalClassName(var1);
      }

      int var2 = this.addUtf8Entry(var1).getIndex();
      return this.addIndexReference(7, var2, 0);
   }

   private int addNameAndType(String var1, String var2) {
      int var3 = this.addUtf8Entry(var1).getIndex();
      int var4 = this.addUtf8Entry(var2).getIndex();
      return this.addIndexReference(12, var3, var4);
   }

   private CONSTANT_Utf8_info addUtf8Entry(String var1) {
      CONSTANT_Utf8_info var2 = (CONSTANT_Utf8_info)this.findMatchingEntry(var1);
      if (var2 == null) {
         var2 = new CONSTANT_Utf8_info(var1);
         this.addEntry(var1, var2);
      }

      return var2;
   }

   private CONSTANT_Utf8_info addExtraUtf8(String var1) {
      CONSTANT_Utf8_info var2 = new CONSTANT_Utf8_info(var1);
      this.addEntry((Object)null, var2);
      return var2;
   }

   private int addString(String var1) {
      CONSTANT_Utf8_info var2 = this.addUtf8Entry(var1);
      int var3 = var2.setAsString();
      if (var3 == 0) {
         var3 = this.addExtraUtf8(var1).getIndex();
         var2.setAlternative(var3);
      }

      return this.addIndexReference(8, var3, 0);
   }

   protected void cptPut(ClassFormatOutput var1) throws IOException {
      Enumeration var2 = this.cptEntries.elements();

      while(var2.hasMoreElements()) {
         ConstantPoolEntry var3 = (ConstantPoolEntry)var2.nextElement();
         if (var3 != null) {
            var3.put(var1);
         }
      }

   }

   public ConstantPoolEntry getEntry(int var1) {
      return (ConstantPoolEntry)this.cptEntries.get(var1);
   }

   protected String className(int var1) {
      CONSTANT_Index_info var2 = (CONSTANT_Index_info)this.getEntry(var1);
      return this.nameIndexToString(var2.getI1()).replace('/', '.');
   }

   int findUtf8(String var1) {
      ConstantPoolEntry var2 = this.findMatchingEntry(var1);
      return var2 == null ? -1 : var2.getIndex();
   }

   public int findClass(String var1) {
      String var2 = convertToInternalClassName(var1);
      int var3 = this.findUtf8(var2);
      return var3 < 0 ? -1 : this.findIndexIndex(7, var3, 0);
   }

   public int findNameAndType(String var1, String var2) {
      int var3 = this.findUtf8(var1);
      if (var3 < 0) {
         return -1;
      } else {
         int var4 = this.findUtf8(var2);
         return var4 < 0 ? -1 : this.findIndexIndex(12, var3, var4);
      }
   }

   protected CONSTANT_Index_info findIndexEntry(int var1, int var2, int var3) {
      this.searchIndex.set(var1, var2, var3);
      return (CONSTANT_Index_info)this.findMatchingEntry(this.searchIndex);
   }

   protected int findIndexIndex(int var1, int var2, int var3) {
      CONSTANT_Index_info var4 = this.findIndexEntry(var1, var2, var3);
      return var4 == null ? -1 : var4.getIndex();
   }

   protected ConstantPoolEntry findMatchingEntry(Object var1) {
      return (ConstantPoolEntry)this.cptHashTable.get(var1);
   }

   String nameIndexToString(int var1) {
      return this.getEntry(var1).toString();
   }

   protected String getClassName(int var1) {
      return var1 == 0 ? "" : this.nameIndexToString(this.getEntry(var1).getI1());
   }

   public static boolean isExternalClassName(String var0) {
      if (var0.indexOf(46) != -1) {
         return true;
      } else {
         int var1;
         if ((var1 = var0.length()) == 0) {
            return false;
         } else {
            return var0.charAt(var1 - 1) == ']';
         }
      }
   }

   public static String convertToInternalClassName(String var0) {
      return convertToInternal(var0, false);
   }

   public static String convertToInternalDescriptor(String var0) {
      return convertToInternal(var0, true);
   }

   private static String convertToInternal(String var0, boolean var1) {
      int var2 = var0.length();
      String var4 = null;
      int var5 = var2;
      int var6 = 0;
      if (var0.charAt(var2 - 1) == ']') {
         while(var2 > 0 && var0.charAt(var2 - 1) == ']' && var0.charAt(var2 - 2) == '[') {
            var2 -= 2;
            ++var6;
         }
      }

      String var3 = var5 == var2 ? var0 : var0.substring(0, var2);
      switch (var2) {
         case 3:
            if ("int".equals(var3)) {
               var4 = makeDesc('I', var6);
            }
            break;
         case 4:
            if ("void".equals(var3)) {
               var4 = makeDesc('V', var6);
            } else if ("long".equals(var3)) {
               var4 = makeDesc('J', var6);
            } else if ("byte".equals(var3)) {
               var4 = makeDesc('B', var6);
            } else if ("char".equals(var3)) {
               var4 = makeDesc('C', var6);
            }
            break;
         case 5:
            if ("short".equals(var3)) {
               var4 = makeDesc('S', var6);
            } else if ("float".equals(var3)) {
               var4 = makeDesc('F', var6);
            }
            break;
         case 6:
            if ("double".equals(var3)) {
               var4 = makeDesc('D', var6);
            }
            break;
         case 7:
            if ("boolean".equals(var3)) {
               var4 = makeDesc('Z', var6);
            }
      }

      if (var4 == null) {
         var4 = makeDesc(var3, var6, var1);
      }

      return var4;
   }

   private static String makeDesc(char var0, int var1) {
      if (var1 == 0) {
         switch (var0) {
            case 'B':
               return "B";
            case 'C':
               return "C";
            case 'D':
               return "D";
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            default:
               return null;
            case 'F':
               return "F";
            case 'I':
               return "I";
            case 'J':
               return "J";
            case 'S':
               return "S";
            case 'V':
               return "V";
            case 'Z':
               return "Z";
         }
      } else {
         StringBuffer var2 = new StringBuffer(var1 + 3);

         for(int var3 = 0; var3 < var1; ++var3) {
            var2.append('[');
         }

         var2.append(makeDesc(var0, 0));
         return var2.toString();
      }
   }

   private static String makeDesc(String var0, int var1, boolean var2) {
      if (!var2 && var1 == 0) {
         return var0.replace('.', '/');
      } else {
         StringBuffer var3 = new StringBuffer(var1 + 2 + var0.length());

         for(int var4 = 0; var4 < var1; ++var4) {
            var3.append('[');
         }

         var3.append('L');
         var3.append(var0.replace('.', '/'));
         var3.append(';');
         return var3.toString();
      }
   }
}
