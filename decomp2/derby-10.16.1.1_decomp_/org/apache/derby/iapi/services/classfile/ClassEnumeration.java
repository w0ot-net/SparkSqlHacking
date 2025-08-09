package org.apache.derby.iapi.services.classfile;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.StringTokenizer;

class ClassEnumeration implements Enumeration {
   ClassHolder cpt;
   Enumeration inner;
   CONSTANT_Index_info position;
   HashSet foundClasses;
   Enumeration classList;

   ClassEnumeration(ClassHolder var1, Enumeration var2, Enumeration var3, Enumeration var4) {
      this.cpt = var1;
      this.inner = var2;
      this.foundClasses = new HashSet(30, 0.8F);
      this.findMethodReferences(var3, this.foundClasses);
      this.findFieldReferences(var4, this.foundClasses);
      this.findClassReferences(this.foundClasses);
      this.classList = Collections.enumeration(this.foundClasses);
   }

   public boolean hasMoreElements() {
      return this.classList.hasMoreElements();
   }

   private void findClassReferences(HashSet var1) {
      while(this.inner.hasMoreElements()) {
         ConstantPoolEntry var2 = (ConstantPoolEntry)this.inner.nextElement();
         if (var2 != null && var2.getTag() == 7) {
            CONSTANT_Index_info var3 = (CONSTANT_Index_info)var2;
            String var4 = this.cpt.className(var3.getIndex());
            if (var4.startsWith("[")) {
               this.distillClasses(var4, var1);
            } else if (var4.length() > 1 && !var4.startsWith("java")) {
               var1.add(var4);
            }
         }
      }

   }

   private void findMethodReferences(Enumeration var1, HashSet var2) {
      while(var1.hasMoreElements()) {
         ClassMember var3 = (ClassMember)var1.nextElement();
         String var4 = var3.getDescriptor();
         this.distillClasses(var4, var2);
      }

   }

   private void findFieldReferences(Enumeration var1, HashSet var2) {
      while(var1.hasMoreElements()) {
         ClassMember var3 = (ClassMember)var1.nextElement();
         String var4 = var3.getDescriptor();
         this.distillClasses(var4, var2);
      }

   }

   void distillClasses(String var1, HashSet var2) {
      if (var1 != null && var1.length() >= 1) {
         if (var1.charAt(0) != '(') {
            int var6 = var1.indexOf(76);
            if (var6 != -1) {
               String var7 = var1.substring(var6 + 1).replace('/', '.');
               if (var7.endsWith(";")) {
                  var7 = var7.substring(0, var7.length() - 1);
               }

               if (!var7.startsWith("java")) {
                  var2.add(var7);
               }
            }
         } else {
            StringTokenizer var3 = new StringTokenizer(var1, "();[");

            while(var3.hasMoreElements()) {
               String var4 = var3.nextToken();
               int var5 = var4.indexOf(76);
               if (var5 != -1) {
                  this.distillClasses(var4, var2);
               }
            }

         }
      }
   }

   public Object nextElement() {
      return this.classList.nextElement();
   }
}
