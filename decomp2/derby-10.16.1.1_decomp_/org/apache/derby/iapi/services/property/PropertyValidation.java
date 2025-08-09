package org.apache.derby.iapi.services.property;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.derby.iapi.services.daemon.Serviceable;
import org.apache.derby.iapi.store.access.TransactionController;
import org.apache.derby.iapi.store.access.conglomerate.TransactionManager;
import org.apache.derby.shared.common.error.StandardException;

public class PropertyValidation implements PropertyFactory {
   private Vector notifyOnSet;

   public Serializable doValidateApplyAndMap(TransactionController var1, String var2, Serializable var3, Dictionary var4, boolean var5) throws StandardException {
      Serializable var6 = null;
      if (this.notifyOnSet != null) {
         synchronized(this) {
            for(int var8 = 0; var8 < this.notifyOnSet.size(); ++var8) {
               PropertySetCallback var9 = (PropertySetCallback)this.notifyOnSet.get(var8);
               if (var9.validate(var2, var3, var4)) {
                  if (var6 == null) {
                     var6 = var9.map(var2, var3, var4);
                  }

                  Serviceable var10;
                  if ((var5 || !var2.startsWith("derby.") || PropertyUtil.whereSet(var2, var4) != 0) && (var10 = var9.apply(var2, var3, var4)) != null) {
                     ((TransactionManager)var1).addPostCommitWork(var10);
                  }
               }
            }
         }
      }

      return var6;
   }

   public Serializable doMap(String var1, Serializable var2, Dictionary var3) throws StandardException {
      Serializable var4 = null;
      if (this.notifyOnSet != null) {
         for(int var5 = 0; var5 < this.notifyOnSet.size() && var4 == null; ++var5) {
            PropertySetCallback var6 = (PropertySetCallback)this.notifyOnSet.get(var5);
            var4 = var6.map(var1, var2, var3);
         }
      }

      return var4 == null ? var2 : var4;
   }

   public void validateSingleProperty(String var1, Serializable var2, Dictionary var3) throws StandardException {
      if (var1.equals("logDevice")) {
         throw StandardException.newException("XSRS8.S", new Object[0]);
      } else {
         if (this.notifyOnSet != null) {
            for(int var4 = 0; var4 < this.notifyOnSet.size(); ++var4) {
               PropertySetCallback var5 = (PropertySetCallback)this.notifyOnSet.get(var4);
               var5.validate(var1, var2, var3);
            }
         }

      }
   }

   public synchronized void addPropertySetNotification(PropertySetCallback var1) {
      if (this.notifyOnSet == null) {
         this.notifyOnSet = new Vector(1, 1);
      }

      this.notifyOnSet.add(var1);
   }

   public synchronized void verifyPropertySet(Properties var1, Properties var2) throws StandardException {
      Enumeration var3 = var1.propertyNames();

      while(var3.hasMoreElements()) {
         String var4 = (String)var3.nextElement();
         if (var2.getProperty(var4) == null) {
            String var5 = var1.getProperty(var4);
            this.validateSingleProperty(var4, var5, var1);
         }
      }

   }
}
