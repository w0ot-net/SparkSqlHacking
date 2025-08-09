package org.apache.derby.iapi.services.classfile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.derby.iapi.services.io.DataInputUtil;

public class ClassInvestigator extends ClassHolder {
   public static ClassInvestigator load(InputStream var0) throws IOException {
      ClassInput var1 = new ClassInput(var0);
      int var2 = var1.getU4();
      int var3 = var1.getU2();
      int var4 = var1.getU2();
      if (var2 != -889275714) {
         throw new ClassFormatError();
      } else {
         int var5 = var1.getU2();
         ClassInvestigator var6 = new ClassInvestigator(var5);
         var6.minor_version = var3;
         var6.major_version = var4;

         ConstantPoolEntry var8;
         for(int var7 = 1; var7 < var5; var7 += var6.addEntry(var8.getKey(), var8)) {
            var8 = getConstant(var1);
         }

         var6.access_flags = var1.getU2();
         var6.this_class = var1.getU2();
         var6.super_class = var1.getU2();
         int var12 = var1.getU2();
         if (var12 != 0) {
            var6.interfaces = new int[var12];

            for(int var13 = 0; var13 < var12; ++var13) {
               var6.interfaces[var13] = var1.getU2();
            }
         }

         int var14 = var1.getU2();
         if (var14 != 0) {
            var6.field_info = new MemberTable(var14);

            for(int var9 = 0; var9 < var14; ++var9) {
               var6.field_info.addEntry(readClassMember(var6, var1));
            }
         }

         int var15 = var1.getU2();
         if (var15 != 0) {
            var6.method_info = new MemberTable(var15);

            for(int var10 = 0; var10 < var15; ++var10) {
               var6.method_info.addEntry(readClassMember(var6, var1));
            }
         }

         int var16 = var1.getU2();
         if (var16 != 0) {
            var6.attribute_info = new Attributes(var16);

            for(int var11 = 0; var11 < var16; ++var11) {
               var6.attribute_info.addEntry(new AttributeEntry(var1));
            }
         }

         return var6;
      }
   }

   private static ClassMember readClassMember(ClassInvestigator var0, ClassInput var1) throws IOException {
      ClassMember var2 = new ClassMember(var0, var1.getU2(), var1.getU2(), var1.getU2());
      int var3 = var1.getU2();
      if (var3 != 0) {
         var2.attribute_info = new Attributes(var3);

         for(int var4 = 0; var4 < var3; ++var4) {
            var2.attribute_info.addEntry(new AttributeEntry(var1));
         }
      }

      return var2;
   }

   private ClassInvestigator(int var1) {
      super(var1);
   }

   public Enumeration implementedInterfaces() {
      int var1 = this.interfaces == null ? 0 : this.interfaces.length;
      Vector var2 = new Vector(var1);

      for(int var3 = 0; var3 < var1; ++var3) {
         var2.add(this.className(this.interfaces[var3]));
      }

      return var2.elements();
   }

   public Enumeration getFields() {
      return this.field_info == null ? Collections.enumeration(new Vector()) : this.field_info.entries.elements();
   }

   public Enumeration getMethods() {
      return this.method_info == null ? Collections.enumeration(new Vector()) : this.method_info.entries.elements();
   }

   public Enumeration referencedClasses() {
      return this.getClasses(this.getMethods(), this.getFields());
   }

   private Enumeration getClasses(Enumeration var1, Enumeration var2) {
      return new ClassEnumeration(this, this.cptEntries.elements(), var1, var2);
   }

   public Enumeration getStrings() {
      HashSet var1 = new HashSet(30, 0.8F);
      int var2 = this.cptEntries.size();

      for(int var3 = 1; var3 < var2; ++var3) {
         ConstantPoolEntry var4 = this.getEntry(var3);
         if (var4 != null && var4.getTag() == 8) {
            CONSTANT_Index_info var5 = (CONSTANT_Index_info)var4;
            var1.add(this.nameIndexToString(var5.getI1()));
         }
      }

      return Collections.enumeration(var1);
   }

   public ClassMember getMember(String var1, String var2) {
      if (var2.startsWith("(")) {
         return this.method_info == null ? null : this.method_info.find(var1, var2);
      } else {
         return this.field_info == null ? null : this.field_info.find(var1, var2);
      }
   }

   public void removeAttributes() throws IOException {
      if (this.attribute_info != null) {
         for(int var1 = this.attribute_info.size() - 1; var1 >= 0; --var1) {
            AttributeEntry var2 = (AttributeEntry)this.attribute_info.elementAt(var1);
            String var3 = this.nameIndexToString(var2.getNameIndex());
            if (var3.equals("SourceFile")) {
               this.attribute_info.removeElementAt(var1);
            } else if (!var3.equals("InnerClasses")) {
               System.err.println("WARNING - Unknown Class File attribute " + var3);
            }
         }

         if (this.attribute_info.size() == 0) {
            this.attribute_info = null;
         }
      }

      this.attribute_info = null;
      Enumeration var7 = this.getFields();

      while(var7.hasMoreElements()) {
         ClassMember var9 = (ClassMember)var7.nextElement();
         Attributes var11 = var9.attribute_info;
         if (var11 != null) {
            for(int var4 = var11.size() - 1; var4 >= 0; --var4) {
               AttributeEntry var5 = (AttributeEntry)var11.elementAt(var4);
               String var6 = this.nameIndexToString(var5.getNameIndex());
               if (!var6.equals("ConstantValue") && !var6.equals("Synthetic")) {
                  System.err.println("WARNING - Unknown Field attribute " + var6);
               }
            }

            if (var11.size() == 0) {
               var9.attribute_info = null;
            }
         }
      }

      var7 = this.getMethods();

      while(var7.hasMoreElements()) {
         ClassMember var10 = (ClassMember)var7.nextElement();
         Attributes var12 = var10.attribute_info;
         if (var12 != null) {
            for(int var13 = var12.size() - 1; var13 >= 0; --var13) {
               AttributeEntry var14 = (AttributeEntry)var12.elementAt(var13);
               String var15 = this.nameIndexToString(var14.getNameIndex());
               if (var15.equals("Code")) {
                  this.processCodeAttribute(var10, var14);
               } else if (!var15.equals("Exceptions") && !var15.equals("Deprecated") && !var15.equals("Synthetic")) {
                  System.err.println("WARNING - Unknown method attribute " + var15);
               }
            }

            if (var12.size() == 0) {
               var10.attribute_info = null;
            }
         }
      }

   }

   private void processCodeAttribute(ClassMember var1, AttributeEntry var2) throws IOException {
      ClassInput var3 = new ClassInput(new ByteArrayInputStream(var2.infoIn));
      DataInputUtil.skipFully(var3, 4);
      int var4 = var3.getU4();
      DataInputUtil.skipFully(var3, var4);
      int var5 = var3.getU2();
      if (var5 != 0) {
         DataInputUtil.skipFully(var3, 8 * var5);
      }

      int var6 = 8 + var4 + 2 + 8 * var5;
      var5 = var3.getU2();
      if (var5 != 0) {
         int var7 = var5;

         for(int var8 = 0; var8 < var5; ++var8) {
            int var9 = var3.getU2();
            String var10 = this.nameIndexToString(var9);
            if (!var10.equals("LineNumberTable") && !var10.equals("LocalVariableTable")) {
               System.err.println("ERROR - Unknown code attribute " + var10);
            } else {
               --var7;
            }

            var4 = var3.getU4();
            DataInputUtil.skipFully(var3, var4);
         }

         if (var7 != 0) {
            System.err.println("ERROR - expecting all code attributes to be removed");
            System.exit(1);
         }

         byte[] var13 = new byte[var6 + 2];
         System.arraycopy(var2.infoIn, 0, var13, 0, var6);
         var2.infoIn = var13;
      }
   }

   public void renameClassElements(Hashtable var1, Hashtable var2) {
      this.renameString(var1, (CONSTANT_Index_info)this.getEntry(this.this_class));
      this.renameString(var1, (CONSTANT_Index_info)this.getEntry(this.super_class));
      int var3 = this.cptEntries.size();

      for(int var4 = 1; var4 < var3; ++var4) {
         ConstantPoolEntry var5 = this.getEntry(var4);
         if (var5 != null) {
            switch (var5.getTag()) {
               case 7:
               case 8:
                  CONSTANT_Index_info var8 = (CONSTANT_Index_info)var5;
                  this.renameString(var1, var8);
                  break;
               case 12:
                  CONSTANT_Index_info var6 = (CONSTANT_Index_info)var5;
                  String var7 = newDescriptor(var1, this.nameIndexToString(var6.getI2()));
                  if (var7 != null) {
                     this.doRenameString(var6.getI2(), var7);
                  }
            }
         }
      }

      this.renameMembers(this.getFields(), var1, var2);
      this.renameMembers(this.getMethods(), var1, var2);
   }

   private void renameMembers(Enumeration var1, Hashtable var2, Hashtable var3) {
      while(var1.hasMoreElements()) {
         ClassMember var4 = (ClassMember)var1.nextElement();
         String var5 = this.nameIndexToString(var4.name_index);
         String var6 = (String)var3.get(var5);
         if (var6 != null) {
            this.doRenameString(var4.name_index, var6);
         }

         String var7 = newDescriptor(var2, this.nameIndexToString(var4.descriptor_index));
         if (var7 != null) {
            this.doRenameString(var4.descriptor_index, var7);
         }
      }

   }

   private void renameString(Hashtable var1, CONSTANT_Index_info var2) {
      int var3 = var2.getI1();
      String var4 = this.nameIndexToString(var3);
      String var5 = (String)var1.get(var4);
      if (var5 != null) {
         this.doRenameString(var3, var5);
      } else {
         if (var2.getTag() == 7 && var4.charAt(0) == '[') {
            int var6 = var4.indexOf(76) + 1;
            String var7 = var4.substring(var6, var4.length() - 1);
            var5 = (String)var1.get(var7);
            if (var5 != null) {
               String var10000 = var4.substring(0, var6);
               String var8 = var10000 + var5 + ";";
               this.doRenameString(var3, var8);
            }
         }

      }
   }

   private void doRenameString(int var1, String var2) {
      ConstantPoolEntry var3 = this.getEntry(var1);
      if (var3.getTag() != 1) {
         throw new RuntimeException("unexpected type " + var3);
      } else {
         CONSTANT_Utf8_info var4 = new CONSTANT_Utf8_info(var2);
         this.cptHashTable.remove(var3.getKey());
         this.cptHashTable.put(var4.getKey(), var4);
         var4.index = var1;
         this.cptEntries.set(var1, var4);
      }
   }

   private static ConstantPoolEntry getConstant(ClassInput var0) throws IOException {
      int var2 = var0.getU1();
      Object var1;
      switch (var2) {
         case 1:
            var1 = new CONSTANT_Utf8_info(var0.readUTF());
            break;
         case 2:
         case 13:
         case 14:
         case 17:
         default:
            throw new ClassFormatError("Unknown tag: " + var2);
         case 3:
            var1 = new CONSTANT_Integer_info(var0.getU4());
            break;
         case 4:
            var1 = new CONSTANT_Float_info(var0.readFloat());
            break;
         case 5:
            var1 = new CONSTANT_Long_info(var0.readLong());
            break;
         case 6:
            var1 = new CONSTANT_Double_info(var0.readDouble());
            break;
         case 7:
         case 8:
         case 16:
            var1 = new CONSTANT_Index_info(var2, var0.getU2(), 0);
            break;
         case 9:
         case 10:
         case 11:
         case 12:
         case 18:
            var1 = new CONSTANT_Index_info(var2, var0.getU2(), var0.getU2());
            break;
         case 15:
            var1 = new CONSTANT_Index_info(var2, var0.getU1(), var0.getU2());
      }

      return (ConstantPoolEntry)var1;
   }

   public static String newDescriptor(Hashtable var0, String var1) {
      String var2 = null;
      int var3 = var1.length();
      int var4 = 0;

      while(var4 < var3) {
         char var5 = var1.charAt(var4);
         switch (var5) {
            case '(':
            case ')':
            case '*':
            case '+':
            case ',':
            case '-':
            case '.':
            case '/':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
            case '[':
            default:
               ++var4;
               continue;
            case 'L':
         }

         int var6 = var4;

         while(var1.charAt(var4++) != ';') {
         }

         String var8 = var1.substring(var6, var4);
         String var9 = (String)var0.get(var8);
         if (var9 != null) {
            if (var2 == null) {
               var2 = var1;
            }

            int var10 = var2.indexOf(var8);
            String var11;
            if (var10 == 0) {
               var11 = var9;
            } else {
               String var10000 = var2.substring(0, var10);
               var11 = var10000 + var9;
            }

            int var12 = var10 + var8.length();
            if (var12 < var2.length()) {
               var11 = var11 + var2.substring(var12, var2.length());
            }

            var2 = var11;
         }
      }

      return var2;
   }
}
