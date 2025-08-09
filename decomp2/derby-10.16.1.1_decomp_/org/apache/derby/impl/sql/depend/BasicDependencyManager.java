package org.apache.derby.impl.sql.depend;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.derby.catalog.DependableFinder;
import org.apache.derby.catalog.UUID;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.io.FormatableBitSet;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.conn.StatementContext;
import org.apache.derby.iapi.sql.depend.Dependency;
import org.apache.derby.iapi.sql.depend.DependencyManager;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.sql.depend.Provider;
import org.apache.derby.iapi.sql.depend.ProviderInfo;
import org.apache.derby.iapi.sql.depend.ProviderList;
import org.apache.derby.iapi.sql.dictionary.DataDictionary;
import org.apache.derby.iapi.sql.dictionary.DependencyDescriptor;
import org.apache.derby.iapi.sql.dictionary.TableDescriptor;
import org.apache.derby.iapi.sql.dictionary.TupleDescriptor;
import org.apache.derby.iapi.sql.dictionary.ViewDescriptor;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.shared.common.error.StandardException;

public class BasicDependencyManager implements DependencyManager {
   private final DataDictionary dd;
   private final Map dependents = new HashMap();
   private final Map providers = new HashMap();
   private static final ProviderInfo[] EMPTY_PROVIDER_INFO = new ProviderInfo[0];

   public void addDependency(Dependent var1, Provider var2, ContextManager var3) throws StandardException {
      this.addDependency(var1, var2, var3, (TransactionController)null);
   }

   private void addDependency(Dependent var1, Provider var2, ContextManager var3, TransactionController var4) throws StandardException {
      if (var1.isPersistent() && var2.isPersistent()) {
         this.addStoredDependency(var1, var2, var3, var4);
      } else {
         this.addInMemoryDependency(var1, var2, var3);
      }

   }

   private synchronized void addInMemoryDependency(Dependent var1, Provider var2, ContextManager var3) throws StandardException {
      BasicDependency var4 = new BasicDependency(var1, var2);
      boolean var5 = false;
      boolean var6 = this.addDependencyToTable(this.dependents, var1.getObjectID(), var4);
      if (var6) {
         this.addDependencyToTable(this.providers, var2.getObjectID(), var4);
      }

      StatementContext var7 = (StatementContext)var3.getContext("StatementContext");
      var7.addDependency(var4);
   }

   private void addStoredDependency(Dependent var1, Provider var2, ContextManager var3, TransactionController var4) throws StandardException {
      LanguageConnectionContext var5 = this.getLanguageConnectionContext(var3);
      TransactionController var6 = var4 == null ? var5.getTransactionExecute() : var4;
      this.dd.addDescriptor(new DependencyDescriptor(var1, var2), (TupleDescriptor)null, 6, true, var6);
   }

   private void dropDependency(LanguageConnectionContext var1, Dependent var2, Provider var3) throws StandardException {
      DependencyDescriptor var4 = new DependencyDescriptor(var2, var3);
      this.dd.dropStoredDependency(var4, var1.getTransactionExecute());
   }

   public void invalidateFor(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      if (var1.isPersistent()) {
         this.coreInvalidateFor(var1, var2, var3);
      } else {
         synchronized(this) {
            this.coreInvalidateFor(var1, var2, var3);
         }
      }

   }

   private void coreInvalidateFor(Provider var1, int var2, LanguageConnectionContext var3) throws StandardException {
      List var4 = this.getDependents(var1);
      if (!var4.isEmpty()) {
         FormatableBitSet var5 = null;
         FormatableBitSet var6 = null;
         if (var1 instanceof TableDescriptor) {
            var5 = ((TableDescriptor)var1).getReferencedColumnMap();
            if (var5 != null) {
               var6 = new FormatableBitSet(var5.getLength());
            }
         }

         StandardException var7 = null;

         for(int var8 = var4.size() - 1; var8 >= 0; --var8) {
            if (var8 < var4.size()) {
               Dependency var9 = (Dependency)var4.get(var8);
               Dependent var10 = var9.getDependent();
               if (var5 != null) {
                  TableDescriptor var11 = (TableDescriptor)var9.getProvider();
                  FormatableBitSet var12 = var11.getReferencedColumnMap();
                  if (var12 == null) {
                     if (!(var10 instanceof ViewDescriptor)) {
                        ((TableDescriptor)var1).setReferencedColumnMap((FormatableBitSet)null);
                     }
                  } else {
                     var6.copyFrom(var5);
                     var6.and(var12);
                     if (var6.anySetBit() == -1) {
                        continue;
                     }

                     ((TableDescriptor)var1).setReferencedColumnMap(var6);
                  }
               }

               try {
                  var10.prepareToInvalidate(var1, var2, var3);
               } catch (StandardException var14) {
                  StandardException var15 = var14;
                  if (var7 == null) {
                     var7 = var14;
                  } else {
                     try {
                        var15.initCause(var7);
                        var7 = var15;
                     } catch (IllegalStateException var13) {
                     }
                  }
               }

               if (var7 == null) {
                  if (var5 != null) {
                     ((TableDescriptor)var1).setReferencedColumnMap(var5);
                  }

                  var10.makeInvalid(var2, var3);
               }
            }
         }

         if (var7 != null) {
            throw var7;
         }
      }
   }

   public void clearDependencies(LanguageConnectionContext var1, Dependent var2) throws StandardException {
      this.clearDependencies(var1, var2, (TransactionController)null);
   }

   public void clearDependencies(LanguageConnectionContext var1, Dependent var2, TransactionController var3) throws StandardException {
      UUID var4 = var2.getObjectID();
      if (var2.isPersistent()) {
         boolean var5 = var3 == null;
         this.dd.dropDependentsStoredDependencies(var4, var5 ? var1.getTransactionExecute() : var3, var5);
      }

      synchronized(this) {
         List var6 = (List)this.dependents.get(var4);
         if (var6 != null) {
            for(Dependency var8 : var6) {
               this.clearProviderDependency(var8.getProviderKey(), var8);
            }

            this.dependents.remove(var4);
         }

      }
   }

   public synchronized void clearInMemoryDependency(Dependency var1) {
      UUID var2 = var1.getDependent().getObjectID();
      UUID var3 = var1.getProviderKey();
      List var4 = (List)this.dependents.get(var2);
      if (var4 != null) {
         List var5 = (List)this.providers.get(var3);
         if (var5 != null) {
            var4.remove(var1);
            if (var4.isEmpty()) {
               this.dependents.remove(var2);
            }

            var5.remove(var1);
            if (var5.isEmpty()) {
               this.providers.remove(var3);
            }

         }
      }
   }

   public ProviderInfo[] getPersistentProviderInfos(Dependent var1) throws StandardException {
      List var2 = this.getProviders(var1);
      if (var2.isEmpty()) {
         return EMPTY_PROVIDER_INFO;
      } else {
         ArrayList var3 = new ArrayList();

         for(Provider var5 : var2) {
            if (var5.isPersistent()) {
               var3.add(new BasicProviderInfo(var5.getObjectID(), var5.getDependableFinder(), var5.getObjectName()));
            }
         }

         return (ProviderInfo[])var3.toArray(EMPTY_PROVIDER_INFO);
      }
   }

   public ProviderInfo[] getPersistentProviderInfos(ProviderList var1) throws StandardException {
      Enumeration var2 = var1.elements();
      int var3 = 0;

      while(var2 != null && var2.hasMoreElements()) {
         Provider var5 = (Provider)var2.nextElement();
         if (var5.isPersistent()) {
            ++var3;
         }
      }

      var2 = var1.elements();
      ProviderInfo[] var4 = new ProviderInfo[var3];
      int var8 = 0;

      while(var2 != null && var2.hasMoreElements()) {
         Provider var6 = (Provider)var2.nextElement();
         if (var6.isPersistent()) {
            var4[var8++] = new BasicProviderInfo(var6.getObjectID(), var6.getDependableFinder(), var6.getObjectName());
         }
      }

      return var4;
   }

   public void clearColumnInfoInProviders(ProviderList var1) throws StandardException {
      Enumeration var2 = var1.elements();

      while(var2.hasMoreElements()) {
         Provider var3 = (Provider)var2.nextElement();
         if (var3 instanceof TableDescriptor) {
            ((TableDescriptor)var3).setReferencedColumnMap((FormatableBitSet)null);
         }
      }

   }

   public void copyDependencies(Dependent var1, Dependent var2, boolean var3, ContextManager var4) throws StandardException {
      this.copyDependencies(var1, var2, var3, var4, (TransactionController)null);
   }

   public void copyDependencies(Dependent var1, Dependent var2, boolean var3, ContextManager var4, TransactionController var5) throws StandardException {
      for(Provider var8 : this.getProviders(var1)) {
         if (!var3 || var8.isPersistent()) {
            this.addDependency(var2, var8, var4, var5);
         }
      }

   }

   public String getActionString(int var1) {
      switch (var1) {
         case 0:
            return "COMPILE FAILED";
         case 1:
            return "DROP TABLE";
         case 2:
            return "DROP INDEX";
         case 3:
            return "CREATE INDEX";
         case 4:
            return "ROLLBACK";
         case 5:
            return "CHANGED CURSOR";
         case 6:
            return "DROP ROUTINE";
         case 7:
         case 8:
         case 16:
         case 24:
         case 25:
         case 26:
         case 32:
         case 35:
         case 36:
         case 38:
         default:
            return "UNKNOWN";
         case 9:
            return "DROP VIEW";
         case 10:
            return "CREATE_VIEW";
         case 11:
            return "PREPARED STATEMENT RELEASE";
         case 12:
            return "ALTER TABLE";
         case 13:
            return "DROP STORED PREPARED STATEMENT";
         case 14:
            return "USER REQUESTED INVALIDATION";
         case 15:
            return "BULK INSERT";
         case 17:
            return "DROP_JAR";
         case 18:
            return "REPLACE_JAR";
         case 19:
            return "DROP CONSTRAINT";
         case 20:
            return "SET_CONSTRAINTS_ENABLE";
         case 21:
            return "SET_CONSTRAINTS_DISABLE";
         case 22:
            return "CREATE CONSTRAINT";
         case 23:
            return "INTERNAL RECOMPILE REQUEST";
         case 27:
            return "DROP TRIGGER";
         case 28:
            return "CREATE TRIGGER";
         case 29:
            return "SET TRIGGERS ENABLED";
         case 30:
            return "SET TRIGGERS DISABLED";
         case 31:
            return "MODIFY COLUMN DEFAULT";
         case 33:
            return "COMPRESS TABLE";
         case 34:
            return "RENAME";
         case 37:
            return "DROP COLUMN";
         case 39:
            return "DROP STATISTICS";
         case 40:
            return "UPDATE STATISTICS";
         case 41:
            return "RENAME INDEX";
         case 42:
            return "TRUNCATE TABLE";
         case 43:
            return "DROP SYNONYM";
         case 44:
            return "REVOKE PRIVILEGE";
         case 45:
            return "REVOKE PRIVILEGE RESTRICT";
         case 46:
            return "DROP COLUMN RESTRICT";
         case 47:
            return "REVOKE ROLE";
         case 48:
            return "RECHECK PRIVILEGES";
         case 49:
            return "DROP SEQUENCE";
         case 50:
            return "DROP TYPE";
         case 51:
            return "DROP DERBY AGGREGATE";
      }
   }

   public int countDependencies() throws StandardException {
      List var1 = this.dd.getAllDependencyDescriptorsList();
      int var2 = var1.size();
      synchronized(this) {
         Iterator var4 = this.dependents.values().iterator();

         Iterator var5;
         for(var5 = this.providers.values().iterator(); var4.hasNext(); var2 += ((List)var4.next()).size()) {
         }

         while(var5.hasNext()) {
            var2 += ((List)var5.next()).size();
         }

         return var2;
      }
   }

   public BasicDependencyManager(DataDictionary var1) {
      this.dd = var1;
   }

   private boolean addDependencyToTable(Map var1, UUID var2, Dependency var3) {
      List var4 = (List)var1.get(var2);
      if (var4 == null) {
         var4 = new ArrayList();
         var4.add(var3);
         var1.put(var2, var4);
      } else {
         UUID var5 = var3.getProvider().getObjectID();
         UUID var6 = var3.getDependent().getObjectID();
         ListIterator var7 = var4.listIterator();

         while(var7.hasNext()) {
            Dependency var8 = (Dependency)var7.next();
            if (var8.getProvider().getObjectID() != null && var8.getProvider().getObjectID().equals(var5) && var8.getDependent().getObjectID().equals(var6)) {
               return false;
            }
         }

         var4.add(var3);
      }

      return true;
   }

   private void clearProviderDependency(UUID var1, Dependency var2) {
      List var3 = (List)this.providers.get(var1);
      if (var3 != null) {
         var3.remove(var2);
         if (var3.isEmpty()) {
            this.providers.remove(var1);
         }

      }
   }

   private List getDependencyDescriptorList(List var1, Provider var2) throws StandardException {
      ArrayList var3 = new ArrayList();
      if (!var1.isEmpty()) {
         for(DependencyDescriptor var5 : var1) {
            DependableFinder var8 = var5.getDependentFinder();
            Dependent var6 = (Dependent)var8.getDependable(this.dd, var5.getUUID());
            Provider var7;
            if (var2 != null) {
               var7 = var2;
            } else {
               var8 = var5.getProviderFinder();
               var7 = (Provider)var8.getDependable(this.dd, var5.getProviderID());
            }

            var3.add(new BasicDependency(var6, var7));
         }
      }

      return var3;
   }

   private LanguageConnectionContext getLanguageConnectionContext(ContextManager var1) {
      return (LanguageConnectionContext)var1.getContext("LanguageConnectionContext");
   }

   private List getProviders(Dependent var1) throws StandardException {
      ArrayList var2 = new ArrayList();
      synchronized(this) {
         List var4 = (List)this.dependents.get(var1.getObjectID());
         if (var4 != null) {
            Iterator var5 = var4.iterator();

            while(var5.hasNext()) {
               var2.add(((Dependency)var5.next()).getProvider());
            }
         }
      }

      if (var1.isPersistent()) {
         List var3 = this.getDependencyDescriptorList(this.dd.getDependentsDescriptorList(var1.getObjectID().toString()), (Provider)null);
         Iterator var8 = var3.iterator();

         while(var8.hasNext()) {
            var2.add(((Dependency)var8.next()).getProvider());
         }
      }

      return var2;
   }

   private List getDependents(Provider var1) throws StandardException {
      ArrayList var2 = new ArrayList();
      synchronized(this) {
         List var4 = (List)this.providers.get(var1.getObjectID());
         if (var4 != null) {
            var2.addAll(var4);
         }
      }

      if (var1.isPersistent()) {
         List var3 = this.getDependencyDescriptorList(this.dd.getProvidersDescriptorList(var1.getObjectID().toString()), var1);
         var2.addAll(var3);
      }

      return var2;
   }
}
