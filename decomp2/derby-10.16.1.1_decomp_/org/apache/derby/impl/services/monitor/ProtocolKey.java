package org.apache.derby.impl.services.monitor;

import org.apache.derby.iapi.services.monitor.Monitor;
import org.apache.derby.shared.common.error.StandardException;

class ProtocolKey {
   protected Class factoryInterface;
   protected String identifier;

   protected ProtocolKey(Class var1, String var2) {
      this.factoryInterface = var1;
      this.identifier = var2;
   }

   static ProtocolKey create(String var0, String var1) throws StandardException {
      Object var2;
      try {
         return new ProtocolKey(Class.forName(var0), var1);
      } catch (ClassNotFoundException var4) {
         var2 = var4;
      } catch (IllegalArgumentException var5) {
         var2 = var5;
      } catch (LinkageError var6) {
         var2 = var6;
      }

      throw Monitor.exceptionStartingModule((Throwable)var2);
   }

   protected Class getFactoryInterface() {
      return this.factoryInterface;
   }

   protected String getIdentifier() {
      return this.identifier;
   }

   public int hashCode() {
      return this.factoryInterface.hashCode() + (this.identifier == null ? 0 : this.identifier.hashCode());
   }

   public boolean equals(Object var1) {
      if (var1 instanceof ProtocolKey var2) {
         if (this.factoryInterface != var2.factoryInterface) {
            return false;
         } else {
            if (this.identifier == null) {
               if (var2.identifier != null) {
                  return false;
               }
            } else {
               if (var2.identifier == null) {
                  return false;
               }

               if (!this.identifier.equals(var2.identifier)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public String toString() {
      String var10000 = this.factoryInterface.getName();
      return var10000 + " (" + this.identifier + ")";
   }
}
