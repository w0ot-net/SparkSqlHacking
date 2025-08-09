package org.datanucleus.flush;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.datanucleus.ExecutionContext;
import org.datanucleus.exceptions.NucleusOptimisticException;
import org.datanucleus.state.ObjectProvider;
import org.datanucleus.util.Localiser;
import org.datanucleus.util.NucleusLogger;

public class FlushOrdered implements FlushProcess {
   public List execute(ExecutionContext ec, List primaryOPs, List secondaryOPs, OperationQueue opQueue) {
      List<NucleusOptimisticException> optimisticFailures = null;
      Object[] toFlushPrimary = null;
      Object[] toFlushSecondary = null;

      try {
         if (ec.getMultithreaded()) {
            ec.getLock().lock();
         }

         if (primaryOPs != null) {
            toFlushPrimary = primaryOPs.toArray();
            primaryOPs.clear();
         }

         if (secondaryOPs != null) {
            toFlushSecondary = secondaryOPs.toArray();
            secondaryOPs.clear();
         }
      } finally {
         if (ec.getMultithreaded()) {
            ec.getLock().unlock();
         }

      }

      if (NucleusLogger.PERSISTENCE.isDebugEnabled()) {
         int total = 0;
         if (toFlushPrimary != null) {
            total += toFlushPrimary.length;
         }

         if (toFlushSecondary != null) {
            total += toFlushSecondary.length;
         }

         NucleusLogger.PERSISTENCE.debug(Localiser.msg("010003", (long)total));
      }

      Set<Class> classesToFlush = null;
      if (ec.getNucleusContext().getStoreManager().getQueryManager().getQueryResultsCache() != null) {
         classesToFlush = new HashSet();
      }

      if (toFlushPrimary != null) {
         for(int i = 0; i < toFlushPrimary.length; ++i) {
            ObjectProvider op = (ObjectProvider)toFlushPrimary[i];

            try {
               op.flush();
               if (classesToFlush != null && op.getObject() != null) {
                  classesToFlush.add(op.getObject().getClass());
               }
            } catch (NucleusOptimisticException oe) {
               if (optimisticFailures == null) {
                  optimisticFailures = new ArrayList();
               }

               optimisticFailures.add(oe);
            }
         }
      }

      if (toFlushSecondary != null) {
         for(int i = 0; i < toFlushSecondary.length; ++i) {
            ObjectProvider op = (ObjectProvider)toFlushSecondary[i];

            try {
               op.flush();
               if (classesToFlush != null && op.getObject() != null) {
                  classesToFlush.add(op.getObject().getClass());
               }
            } catch (NucleusOptimisticException oe) {
               if (optimisticFailures == null) {
                  optimisticFailures = new ArrayList();
               }

               optimisticFailures.add(oe);
            }
         }
      }

      if (opQueue != null) {
         if (!ec.getStoreManager().usesBackedSCOWrappers()) {
            opQueue.processOperationsForNoBackingStoreSCOs(ec);
         }

         opQueue.clearPersistDeleteUpdateOperations();
      }

      if (classesToFlush != null) {
         for(Class cls : classesToFlush) {
            ec.getNucleusContext().getStoreManager().getQueryManager().evictQueryResultsForType(cls);
         }
      }

      return optimisticFailures;
   }
}
