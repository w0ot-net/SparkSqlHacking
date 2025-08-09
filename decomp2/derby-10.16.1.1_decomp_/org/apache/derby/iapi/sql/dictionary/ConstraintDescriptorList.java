package org.apache.derby.iapi.sql.dictionary;

import java.util.ArrayList;
import org.apache.derby.catalog.UUID;

public class ConstraintDescriptorList extends ArrayList {
   private boolean scanned;

   public void setScanned(boolean var1) {
      this.scanned = var1;
   }

   public boolean getScanned() {
      return this.scanned;
   }

   public ConstraintDescriptor getConstraintDescriptor(UUID var1) {
      ConstraintDescriptor var2 = null;
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ConstraintDescriptor var5 = this.elementAt(var4);
         if (var5 instanceof KeyConstraintDescriptor var6) {
            if (var6.getIndexId().equals(var1)) {
               var2 = var5;
               break;
            }
         }
      }

      return var2;
   }

   public ConstraintDescriptor getConstraintDescriptorById(UUID var1) {
      ConstraintDescriptor var2 = null;
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ConstraintDescriptor var5 = this.elementAt(var4);
         if (var5.getUUID().equals(var1)) {
            var2 = var5;
            break;
         }
      }

      return var2;
   }

   public ConstraintDescriptor dropConstraintDescriptorById(UUID var1) {
      ConstraintDescriptor var2 = null;
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         var2 = this.elementAt(var4);
         if (var2.getUUID().equals(var1)) {
            this.remove(var2);
            break;
         }
      }

      return var2;
   }

   public ConstraintDescriptor getConstraintDescriptorByName(SchemaDescriptor var1, String var2) {
      ConstraintDescriptor var3 = null;
      int var4 = this.size();

      for(int var5 = 0; var5 < var4; ++var5) {
         ConstraintDescriptor var6 = this.elementAt(var5);
         if (var6.getConstraintName().equals(var2) && (var1 == null || var1.equals(var6.getSchemaDescriptor()))) {
            var3 = var6;
            break;
         }
      }

      return var3;
   }

   public ReferencedKeyConstraintDescriptor getPrimaryKey() {
      int var1 = this.size();

      for(int var2 = 0; var2 < var1; ++var2) {
         ConstraintDescriptor var3 = this.elementAt(var2);
         if (var3.getConstraintType() == 2) {
            return (ReferencedKeyConstraintDescriptor)var3;
         }
      }

      return (ReferencedKeyConstraintDescriptor)null;
   }

   public ConstraintDescriptorList getConstraintDescriptorList(boolean var1) {
      ConstraintDescriptorList var2 = new ConstraintDescriptorList();
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ConstraintDescriptor var5 = this.elementAt(var4);
         if (var5.enforced() == var1) {
            var2.add(var5);
         }
      }

      return var2;
   }

   public ConstraintDescriptor elementAt(int var1) {
      return (ConstraintDescriptor)this.get(var1);
   }

   public ConstraintDescriptorList getSubList(int var1) {
      ConstraintDescriptorList var2 = new ConstraintDescriptorList();
      int var3 = this.size();

      for(int var4 = 0; var4 < var3; ++var4) {
         ConstraintDescriptor var5 = this.elementAt(var4);
         if (var5.getConstraintType() == var1) {
            var2.add(var5);
         }
      }

      return var2;
   }
}
