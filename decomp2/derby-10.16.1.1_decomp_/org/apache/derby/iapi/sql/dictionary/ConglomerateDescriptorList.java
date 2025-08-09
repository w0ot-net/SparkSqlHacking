package org.apache.derby.iapi.sql.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.derby.catalog.UUID;
import org.apache.derby.shared.common.error.StandardException;

public class ConglomerateDescriptorList extends ArrayList {
   public ConglomerateDescriptor getConglomerateDescriptor(long var1) {
      ConglomerateDescriptor var3 = null;

      for(ConglomerateDescriptor var5 : this) {
         if (var1 == var5.getConglomerateNumber()) {
            var3 = var5;
            break;
         }
      }

      return var3;
   }

   public ConglomerateDescriptor[] getConglomerateDescriptors(long var1) {
      int var3 = this.size();
      int var4 = 0;
      ConglomerateDescriptor[] var5 = new ConglomerateDescriptor[var3];

      for(ConglomerateDescriptor var7 : this) {
         if (var1 == var7.getConglomerateNumber()) {
            var5[var4++] = var7;
         }
      }

      if (var4 == var3) {
         return var5;
      } else {
         return (ConglomerateDescriptor[])Arrays.copyOf(var5, var4);
      }
   }

   public ConglomerateDescriptor getConglomerateDescriptor(String var1) {
      ConglomerateDescriptor var2 = null;

      for(ConglomerateDescriptor var4 : this) {
         if (var1.equals(var4.getConglomerateName())) {
            var2 = var4;
            break;
         }
      }

      return var2;
   }

   public ConglomerateDescriptor getConglomerateDescriptor(UUID var1) throws StandardException {
      ConglomerateDescriptor var2 = null;

      for(ConglomerateDescriptor var4 : this) {
         if (var1.equals(var4.getUUID())) {
            var2 = var4;
            break;
         }
      }

      return var2;
   }

   public ConglomerateDescriptor[] getConglomerateDescriptors(UUID var1) {
      int var2 = this.size();
      int var3 = 0;
      ConglomerateDescriptor[] var4 = new ConglomerateDescriptor[var2];

      for(ConglomerateDescriptor var6 : this) {
         if (var1.equals(var6.getUUID())) {
            var4[var3++] = var6;
         }
      }

      if (var3 == var2) {
         return var4;
      } else {
         return (ConglomerateDescriptor[])Arrays.copyOf(var4, var3);
      }
   }

   public void dropConglomerateDescriptor(UUID var1, ConglomerateDescriptor var2) throws StandardException {
      Iterator var3 = this.iterator();

      while(var3.hasNext()) {
         ConglomerateDescriptor var4 = (ConglomerateDescriptor)var3.next();
         if (var4.getConglomerateNumber() == var2.getConglomerateNumber() && var4.getConglomerateName().equals(var2.getConglomerateName()) && var4.getSchemaID().equals(var2.getSchemaID())) {
            var3.remove();
            break;
         }
      }

   }

   public void dropConglomerateDescriptorByUUID(UUID var1) throws StandardException {
      Iterator var2 = this.iterator();

      while(var2.hasNext()) {
         ConglomerateDescriptor var3 = (ConglomerateDescriptor)var2.next();
         if (var1.equals(var3.getUUID())) {
            var2.remove();
            break;
         }
      }

   }
}
