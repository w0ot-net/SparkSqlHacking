package org.datanucleus.flush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.store.StorePersistenceHandler;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class FlushNonReferential implements FlushProcess {
   public List execute(ExecutionContext ec, List primaryOPs, List secondaryOPs, OperationQueue opQueue) {
      Set<ObjectProvider> opsToFlush = new HashSet();
      if (primaryOPs != null) {
         opsToFlush.addAll(primaryOPs);
         primaryOPs.clear();
      }

      if (secondaryOPs != null) {
         opsToFlush.addAll(secondaryOPs);
         secondaryOPs.clear();
      }

      List<NucleusOptimisticException> excptns = this.flushDeleteInsertUpdateGrouped(opsToFlush, ec);
      if (opQueue != null) {
         if (!ec.getStoreManager().usesBackedSCOWrappers()) {
            opQueue.processOperationsForNoBackingStoreSCOs(ec);
         }

         opQueue.clearPersistDeleteUpdateOperations();
      }

      return excptns;
   }

   public List flushDeleteInsertUpdateGrouped(Set opsToFlush, ExecutionContext ec) {
      List<NucleusOptimisticException> optimisticFailures = null;
      Set<Class> classesToFlush = null;
      if (ec.getNucleusContext().getStoreManager().getQueryManager().getQueryResultsCache() != null) {
         classesToFlush = new HashSet();
      }

      Set<ObjectProvider> opsToDelete = new HashSet();
      Set<ObjectProvider> opsToInsert = new HashSet();
      Iterator<ObjectProvider> opIter = opsToFlush.iterator();

      while(opIter.hasNext()) {
         ObjectProvider op = (ObjectProvider)opIter.next();
         if (op.isEmbedded()) {
            op.markAsFlushed();
            opIter.remove();
         } else {
            if (classesToFlush != null && op.getObject() != null) {
               classesToFlush.add(op.getObject().getClass());
            }

            if (op.getLifecycleState().isNew() && !op.isFlushedToDatastore() && !op.isFlushedNew()) {
               opsToInsert.add(op);
               opIter.remove();
            } else if (op.getLifecycleState().isDeleted() && !op.isFlushedToDatastore()) {
               if (!op.getLifecycleState().isNew()) {
                  opsToDelete.add(op);
                  opIter.remove();
               } else if (op.getLifecycleState().isNew() && op.isFlushedNew()) {
                  opsToDelete.add(op);
                  opIter.remove();
               }
            }
         }
      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         NucleusLogger.PERSISTENCE.debug(Localiser.msg("010046", opsToDelete.size(), opsToInsert.size(), opsToFlush.size()));
      }

      StorePersistenceHandler persistenceHandler = ec.getStoreManager().getPersistenceHandler();
      if (!opsToDelete.isEmpty()) {
         for(ObjectProvider op : opsToDelete) {
            op.setFlushing(true);
            ec.getCallbackHandler().preDelete(op.getObject());
         }

         try {
            persistenceHandler.deleteObjects((ObjectProvider[])opsToDelete.toArray(new ObjectProvider[opsToDelete.size()]));
         } catch (NucleusOptimisticException noe) {
            optimisticFailures = new ArrayList();
            Throwable[] nestedExcs = noe.getNestedExceptions();
            if (nestedExcs != null && nestedExcs.length > 1) {
               NucleusOptimisticException[] noes = (NucleusOptimisticException[])nestedExcs;

               for(int i = 0; i < nestedExcs.length; ++i) {
                  optimisticFailures.add(noes[i]);
               }
            } else {
               optimisticFailures.add(noe);
            }
         }

         for(ObjectProvider op : opsToDelete) {
            ec.getCallbackHandler().postDelete(op.getObject());
            op.setFlushedNew(false);
            op.markAsFlushed();
            op.setFlushing(false);
         }
      }

      if (!opsToInsert.isEmpty()) {
         for(ObjectProvider op : opsToInsert) {
            op.setFlushing(true);
            ec.getCallbackHandler().preStore(op.getObject());
         }

         persistenceHandler.insertObjects((ObjectProvider[])opsToInsert.toArray(new ObjectProvider[opsToInsert.size()]));

         for(ObjectProvider op : opsToInsert) {
            ec.getCallbackHandler().postStore(op.getObject());
            op.setFlushedNew(true);
            op.markAsFlushed();
            op.setFlushing(false);
            ec.putObjectIntoLevel1Cache(op);
         }
      }

      if (!opsToFlush.isEmpty()) {
         for(ObjectProvider op : opsToFlush) {
            try {
               op.flush();
            } catch (NucleusOptimisticException oe) {
               if (optimisticFailures == null) {
                  optimisticFailures = new ArrayList();
               }

               optimisticFailures.add(oe);
            }
         }
      }

      if (classesToFlush != null) {
         for(Class cls : classesToFlush) {
            ec.getNucleusContext().getStoreManager().getQueryManager().evictQueryResultsForType(cls);
         }
      }

      return optimisticFailures;
   }
}
