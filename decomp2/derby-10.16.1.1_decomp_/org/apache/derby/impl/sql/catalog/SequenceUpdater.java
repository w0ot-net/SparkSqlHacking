package org.apache.derby.impl.sql.catalog;

import java.lang.reflect.InvocationTargetException;
import org.apache.derby.catalog.SequencePreallocator;
import org.apache.derby.iapi.db.Database;
import org.apache.derby.iapi.services.cache.Cacheable;
import org.apache.derby.iapi.services.context.Context;
import org.apache.derby.iapi.services.context.ContextManager;
import org.apache.derby.iapi.services.context.ContextService;
import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.iapi.services.property.PropertyUtil;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.dictionary.BulkInsertCounter;
import org.apache.derby.iapi.sql.dictionary.SequenceDescriptor;
import org.apache.derby.iapi.store.access.AccessFactory;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.types.NumberDataValue;
import org.apache.derby.iapi.types.RowLocation;
import org.apache.derby.shared.common.error.StandardException;
import org.apache.derby.shared.common.i18n.MessageService;

public abstract class SequenceUpdater implements Cacheable {
   protected DataDictionaryImpl _dd;
   protected String _uuidString;
   protected SequenceGenerator _sequenceGenerator;

   public SequenceUpdater() {
   }

   public SequenceUpdater(DataDictionaryImpl var1) {
      this();
      this._dd = var1;
   }

   protected abstract SequenceGenerator createSequenceGenerator(TransactionController var1) throws StandardException;

   protected abstract boolean updateCurrentValueOnDisk(TransactionController var1, Long var2, Long var3, boolean var4) throws StandardException;

   private StandardException tooMuchContentionException() {
      return "SYS".equals(this._sequenceGenerator.getSchemaName()) ? StandardException.newException("40XL1", new Object[0]) : StandardException.newException("X0Y84.T", new Object[]{this._sequenceGenerator.getName()});
   }

   public void clean(boolean var1) throws StandardException {
      boolean var2 = false;

      try {
         if (this._sequenceGenerator == null) {
            var2 = true;
         } else {
            var2 = this.updateCurrentValueOnDisk((Long)null, this.peekAtCurrentValue());
         }
      } catch (StandardException var8) {
         if (!"X0Y84.T".equals(var8.getMessageId())) {
            throw var8;
         }
      } finally {
         if (!var2) {
            String var5 = MessageService.getTextMessage("X0Y86.S", new Object[]{this._sequenceGenerator.getSchemaName(), this._sequenceGenerator.getName()});
            Monitor.getStream().println(var5);
         }

         this._uuidString = null;
         this._sequenceGenerator = null;
      }

   }

   public boolean isDirty() {
      return false;
   }

   public Object getIdentity() {
      return this._uuidString;
   }

   public void clearIdentity() {
      try {
         this.clean(false);
      } catch (StandardException var5) {
         LanguageConnectionContext var2 = getLCC();
         if (var2 != null) {
            Database var3 = var2.getDatabase();
            boolean var4 = var3 != null ? var3.isActive() : false;
            var2.getContextManager().cleanupOnError(var5, var4);
         }
      }

   }

   public Cacheable createIdentity(Object var1, Object var2) throws StandardException {
      return this.setIdentity(var1);
   }

   public Cacheable setIdentity(Object var1) throws StandardException {
      this._uuidString = (String)var1;
      if (this._sequenceGenerator == null) {
         TransactionController var2 = getLCC().getTransactionExecute();
         TransactionController var3 = var2.startNestedUserTransaction(true, true);

         try {
            this._sequenceGenerator = this.createSequenceGenerator(var3);
         } finally {
            if (this._sequenceGenerator == null) {
               this._uuidString = null;
            }

            var3.commit();
            var3.destroy();
         }
      }

      return this._sequenceGenerator != null ? this : null;
   }

   public synchronized void reset(Long var1) throws StandardException {
      this.updateCurrentValueOnDisk((Long)null, var1);
      this._sequenceGenerator = this._sequenceGenerator.clone(var1);
   }

   public synchronized BulkInsertUpdater getBulkInsertUpdater(boolean var1) throws StandardException {
      return new BulkInsertUpdater(this, var1);
   }

   public synchronized void getCurrentValueAndAdvance(NumberDataValue var1) throws StandardException {
      for(int var2 = 0; var2 < 2; ++var2) {
         long[] var3 = this._sequenceGenerator.getCurrentValueAndAdvance();
         int var4 = (int)var3[0];
         long var5 = var3[1];
         long var7 = var3[2];
         long var9 = var3[3];
         switch (var4) {
            case 1:
               var1.setValue(var5);
               return;
            case 2:
               this.updateCurrentValueOnDisk(var5, (Long)null);
               var1.setValue(var5);
               return;
            case 3:
               if (this.updateCurrentValueOnDisk(var5, var7)) {
                  this._sequenceGenerator.allocateNewRange(var5, var9);
               }
               break;
            default:
               throw this.unimplementedFeature();
         }
      }

      throw this.tooMuchContentionException();
   }

   public Long peekAtCurrentValue() throws StandardException {
      return this._sequenceGenerator.peekAtCurrentValue();
   }

   public synchronized boolean updateCurrentValueOnDisk(Long var1, Long var2) throws StandardException {
      LanguageConnectionContext var3 = getLCC();
      if (var3 == null) {
         ContextService var14 = getContextService();
         ContextManager var15 = var14.getCurrentContextManager();
         AccessFactory var16 = this._dd.af;
         TransactionController var17 = var16.getTransaction(var15);
         boolean var8 = this.updateCurrentValueOnDisk(var17, var1, var2, false);
         var17.commit();
         var17.destroy();
         return var8;
      } else {
         TransactionController var4 = var3.getTransactionExecute();
         TransactionController var5 = var4.startNestedUserTransaction(false, true);
         if (var5 != null) {
            boolean var6 = false;
            boolean var7 = false;

            try {
               var6 = this.updateCurrentValueOnDisk(var5, var1, var2, false);
            } catch (StandardException var12) {
               if (!var12.isLockTimeout()) {
                  if (var12.isSelfDeadlock()) {
                     var7 = true;
                  } else {
                     Monitor.logThrowable(var12);
                     throw var12;
                  }
               }
            } finally {
               var5.commit();
               var5.destroy();
               if (var7) {
                  var6 = this.updateCurrentValueOnDisk(var4, var1, var2, false);
               }

               return var6;
            }
         } else {
            throw this.tooMuchContentionException();
         }
      }
   }

   protected SequencePreallocator makePreallocator(TransactionController var1) throws StandardException {
      String var2 = "derby.language.sequence.preallocator";
      String var3 = PropertyUtil.getServiceProperty(var1, var2);
      if (var3 == null) {
         return new SequenceRange();
      } else {
         try {
            if (this.isNumber(var3)) {
               return new SequenceRange(Integer.parseInt(var3));
            } else {
               Class var4 = Class.forName(var3);
               if (!SequencePreallocator.class.isAssignableFrom(var4)) {
                  throw StandardException.newException("X0Y85.S.1", new Object[]{var2});
               } else {
                  return (SequencePreallocator)var4.getConstructor().newInstance();
               }
            }
         } catch (ClassNotFoundException var5) {
            throw this.missingAllocator(var2, var3, var5);
         } catch (ClassCastException var6) {
            throw this.missingAllocator(var2, var3, var6);
         } catch (InstantiationException var7) {
            throw this.missingAllocator(var2, var3, var7);
         } catch (IllegalAccessException var8) {
            throw this.missingAllocator(var2, var3, var8);
         } catch (NumberFormatException var9) {
            throw this.missingAllocator(var2, var3, var9);
         } catch (NoSuchMethodException var10) {
            throw this.missingAllocator(var2, var3, var10);
         } catch (InvocationTargetException var11) {
            throw this.missingAllocator(var2, var3, var11);
         }
      }
   }

   private StandardException missingAllocator(String var1, String var2, Exception var3) {
      return StandardException.newException("X0Y85.S", var3, new Object[]{var1, var2});
   }

   private boolean isNumber(String var1) {
      int var2 = var1.length();

      for(int var3 = 0; var3 < var2; ++var3) {
         if (!Character.isDigit(var1.charAt(var3))) {
            return false;
         }
      }

      return true;
   }

   private static LanguageConnectionContext getLCC() {
      return (LanguageConnectionContext)getContextOrNull("LanguageConnectionContext");
   }

   private StandardException unimplementedFeature() {
      return StandardException.newException("XSCB3.S", new Object[0]);
   }

   private static ContextService getContextService() {
      return ContextService.getFactory();
   }

   private static Context getContextOrNull(String var0) {
      return ContextService.getContextOrNull(var0);
   }

   public static final class SyssequenceUpdater extends SequenceUpdater {
      private RowLocation _sequenceRowLocation;

      public SyssequenceUpdater() {
      }

      public SyssequenceUpdater(DataDictionaryImpl var1) {
         super(var1);
      }

      protected SequenceGenerator createSequenceGenerator(TransactionController var1) throws StandardException {
         RowLocation[] var2 = new RowLocation[1];
         SequenceDescriptor[] var3 = new SequenceDescriptor[1];
         this._dd.computeSequenceRowLocation(var1, this._uuidString, var2, var3);
         this._sequenceRowLocation = var2[0];
         SequenceDescriptor var4 = var3[0];
         return new SequenceGenerator(var4.getCurrentValue(), var4.canCycle(), var4.getIncrement(), var4.getMaximumValue(), var4.getMinimumValue(), var4.getStartValue(), var4.getSchemaDescriptor().getSchemaName(), var4.getSequenceName(), this.makePreallocator(var1));
      }

      protected boolean updateCurrentValueOnDisk(TransactionController var1, Long var2, Long var3, boolean var4) throws StandardException {
         return this._dd.updateCurrentSequenceValue(var1, this._sequenceRowLocation, var4, var2, var3);
      }
   }

   public static final class BulkInsertUpdater extends SequenceUpdater implements BulkInsertCounter {
      public BulkInsertUpdater() {
      }

      public BulkInsertUpdater(SequenceUpdater var1, boolean var2) {
         this._sequenceGenerator = var1._sequenceGenerator.clone(var2);
      }

      protected SequenceGenerator createSequenceGenerator(TransactionController var1) throws StandardException {
         return this._sequenceGenerator;
      }

      protected boolean updateCurrentValueOnDisk(TransactionController var1, Long var2, Long var3, boolean var4) throws StandardException {
         return true;
      }
   }
}
