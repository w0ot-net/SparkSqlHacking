package org.apache.derby.impl.services.locks;

import java.util.Hashtable;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.ConglomerateDescriptor;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class TableNameInfo {
   private DataDictionary dd;
   private Hashtable ddCache;
   private Hashtable tdCache;
   private Hashtable tableCache = new Hashtable(31);
   private Hashtable indexCache;

   public TableNameInfo(LanguageConnectionContext var1, boolean var2) throws StandardException {
      if (var2) {
         this.indexCache = new Hashtable(13);
      }

      TransactionController var3 = var1.getTransactionExecute();
      this.dd = var1.getDataDictionary();
      this.ddCache = this.dd.hashAllConglomerateDescriptorsByNumber(var3);
      this.tdCache = this.dd.hashAllTableDescriptorsByTableId(var3);
   }

   public String getTableName(Long var1) {
      if (var1 == null) {
         return "?";
      } else {
         TableDescriptor var2 = (TableDescriptor)this.tableCache.get(var1);
         if (var2 == null) {
            ConglomerateDescriptor var3 = (ConglomerateDescriptor)this.ddCache.get(var1);
            if (var3 != null) {
               var2 = (TableDescriptor)this.tdCache.get(var3.getTableID());
            }

            if (var3 == null || var2 == null) {
               String var4;
               if (var1 > 20L) {
                  var4 = "*** TRANSIENT_" + var1;
               } else {
                  switch (var1.intValue()) {
                     case 0 -> var4 = "*** INVALID CONGLOMERATE ***";
                     case 1 -> var4 = "ConglomerateDirectory";
                     case 2 -> var4 = "PropertyConglomerate";
                     default -> var4 = "*** INTERNAL TABLE " + var1;
                  }
               }

               return var4;
            }

            this.tableCache.put(var1, var2);
            if (this.indexCache != null && var3.isIndex()) {
               this.indexCache.put(var1, var3.getConglomerateName());
            }
         }

         return var2.getName();
      }
   }

   public String getTableType(Long var1) {
      if (var1 == null) {
         return "?";
      } else {
         TableDescriptor var3 = (TableDescriptor)this.tableCache.get(var1);
         String var2;
         if (var3 != null) {
            switch (var3.getTableType()) {
               case 0 -> var2 = "T";
               case 1 -> var2 = "S";
               default -> var2 = "?";
            }
         } else if (var1 > 20L) {
            var2 = "T";
         } else {
            var2 = "S";
         }

         return var2;
      }
   }

   public String getIndexName(Long var1) {
      return var1 == null ? "?" : (String)this.indexCache.get(var1);
   }
}
