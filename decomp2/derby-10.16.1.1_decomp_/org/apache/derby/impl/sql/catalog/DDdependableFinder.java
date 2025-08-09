package org.apache.derby.impl.sql.catalog;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.derby.catalog.Dependable;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.io.Formatable;
import org.apache.derby.iapi.sql.dictionary.ColumnDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DefaultDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class DDdependableFinder implements DependableFinder, Formatable {
   private final int formatId;

   public DDdependableFinder(int var1) {
      this.formatId = var1;
   }

   public String toString() {
      return this.getSQLObjectType();
   }

   public void readExternal(ObjectInput var1) throws IOException, ClassNotFoundException {
   }

   public void writeExternal(ObjectOutput var1) throws IOException {
   }

   public final int getTypeFormatId() {
      return this.formatId;
   }

   public String getSQLObjectType() {
      switch (this.formatId) {
         case 135 -> {
            return "Conglomerate";
         }
         case 136 -> {
            return "Alias";
         }
         case 137 -> {
            return "Table";
         }
         case 145 -> {
            return "View";
         }
         case 208 -> {
            return "Constraint";
         }
         case 226 -> {
            return "StoredPreparedStatement";
         }
         case 273 -> {
            return "File";
         }
         case 320 -> {
            return "Trigger";
         }
         case 325 -> {
            return "Default";
         }
         case 371 -> {
            return "Schema";
         }
         case 393 -> {
            return "ColumnsInTable";
         }
         case 461 -> {
            return "RoutinePrivilege";
         }
         case 462 -> {
            return "TablePrivilege";
         }
         case 463 -> {
            return "ColumnsPrivilege";
         }
         case 471 -> {
            return "RoleGrant";
         }
         case 472 -> {
            return "Sequence";
         }
         case 473 -> {
            return "Perm";
         }
         default -> {
            return null;
         }
      }
   }

   public final Dependable getDependable(DataDictionary var1, UUID var2) throws StandardException {
      Dependable var3 = this.findDependable(var1, var2);
      if (var3 == null) {
         throw StandardException.newException("42X94", new Object[]{this.getSQLObjectType(), var2});
      } else {
         return var3;
      }
   }

   Dependable findDependable(DataDictionary var1, UUID var2) throws StandardException {
      switch (this.formatId) {
         case 135:
            return var1.getConglomerateDescriptor(var2);
         case 136:
            return var1.getAliasDescriptor(var2);
         case 137:
            return var1.getTableDescriptor(var2);
         case 145:
            return var1.getViewDescriptor(var2);
         case 208:
            return var1.getConstraintDescriptor(var2);
         case 226:
            return var1.getSPSDescriptor(var2);
         case 273:
            return var1.getFileInfoDescriptor(var2);
         case 320:
            return var1.getTriggerDescriptor(var2);
         case 325:
            ColumnDescriptor var3 = var1.getColumnDescriptorByDefaultId(var2);
            if (var3 != null) {
               return new DefaultDescriptor(var1, var3.getDefaultUUID(), var3.getReferencingUUID(), var3.getPosition());
            }

            return null;
         case 371:
            return var1.getSchemaDescriptor(var2, (TransactionController)null);
         case 461:
            return var1.getRoutinePermissions(var2);
         case 462:
            return var1.getTablePermissions(var2);
         case 463:
            return var1.getColumnPermissions(var2);
         case 471:
            return var1.getRoleGrantDescriptor(var2);
         case 472:
            return var1.getSequenceDescriptor(var2);
         case 473:
            return var1.getGenericPermissions(var2);
         default:
            return null;
      }
   }
}
