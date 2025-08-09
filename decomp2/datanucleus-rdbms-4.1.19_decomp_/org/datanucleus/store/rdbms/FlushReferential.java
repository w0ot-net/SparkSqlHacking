package org.datanucleus.store.rdbms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.flush.FlushNonReferential;
import org.datanucleus.flush.FlushOrdered;
import org.datanucleus.flush.OperationQueue;
import org.datanucleus.metadata.AbstractClassMetaData;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.rdbms.table.ClassTable;

public class FlushReferential extends FlushOrdered {
   public List execute(ExecutionContext ec, List primaryOPs, List secondaryOPs, OperationQueue opQueue) {
      List<NucleusOptimisticException> flushExcps = null;
      Set<ObjectProvider> unrelatedOPs = null;
      if (primaryOPs != null) {
         Iterator<ObjectProvider> opIter = primaryOPs.iterator();

         while(opIter.hasNext()) {
            ObjectProvider op = (ObjectProvider)opIter.next();
            if (!op.isEmbedded() && this.isClassSuitableForBatching(ec, op.getClassMetaData())) {
               if (unrelatedOPs == null) {
                  unrelatedOPs = new HashSet();
               }

               unrelatedOPs.add(op);
               opIter.remove();
            }
         }
      }

      if (secondaryOPs != null) {
         Iterator<ObjectProvider> opIter = secondaryOPs.iterator();

         while(opIter.hasNext()) {
            ObjectProvider op = (ObjectProvider)opIter.next();
            if (!op.isEmbedded() && this.isClassSuitableForBatching(ec, op.getClassMetaData())) {
               if (unrelatedOPs == null) {
                  unrelatedOPs = new HashSet();
               }

               unrelatedOPs.add(op);
               opIter.remove();
            }
         }
      }

      if (unrelatedOPs != null) {
         FlushNonReferential groupedFlush = new FlushNonReferential();
         flushExcps = groupedFlush.flushDeleteInsertUpdateGrouped(unrelatedOPs, ec);
      }

      List<NucleusOptimisticException> excps = super.execute(ec, primaryOPs, secondaryOPs, opQueue);
      if (excps != null) {
         if (flushExcps == null) {
            flushExcps = excps;
         } else {
            flushExcps.addAll(excps);
         }
      }

      return flushExcps;
   }

   private boolean isClassSuitableForBatching(ExecutionContext ec, AbstractClassMetaData cmd) {
      if (cmd.hasRelations(ec.getClassLoaderResolver(), ec.getMetaDataManager())) {
         return false;
      } else {
         RDBMSStoreManager storeMgr = (RDBMSStoreManager)ec.getStoreManager();
         ClassTable table = (ClassTable)storeMgr.getDatastoreClass(cmd.getFullClassName(), ec.getClassLoaderResolver());

         while(this.isTableSuitableForBatching(table)) {
            table = (ClassTable)table.getSuperDatastoreClass();
            if (table == null) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean isTableSuitableForBatching(ClassTable table) {
      if (table.hasExternalFkMappings()) {
         return false;
      } else {
         return !table.isObjectIdDatastoreAttributed();
      }
   }
}
