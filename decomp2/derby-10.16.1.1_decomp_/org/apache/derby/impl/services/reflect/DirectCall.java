package org.apache.derby.impl.services.reflect;

import org.apache.derby.iapi.services.loader.GeneratedByteCode;
import org.apache.derby.iapi.services.loader.GeneratedMethod;
import org.apache.derby.shared.common.error.StandardException;

class DirectCall implements GeneratedMethod {
   private final int which;

   DirectCall(int var1) {
      this.which = var1;
   }

   public Object invoke(Object var1) throws StandardException {
      try {
         GeneratedByteCode var2 = (GeneratedByteCode)var1;
         switch (this.which) {
            case 0 -> {
               return var2.e0();
            }
            case 1 -> {
               return var2.e1();
            }
            case 2 -> {
               return var2.e2();
            }
            case 3 -> {
               return var2.e3();
            }
            case 4 -> {
               return var2.e4();
            }
            case 5 -> {
               return var2.e5();
            }
            case 6 -> {
               return var2.e6();
            }
            case 7 -> {
               return var2.e7();
            }
            case 8 -> {
               return var2.e8();
            }
            case 9 -> {
               return var2.e9();
            }
            default -> {
               return null;
            }
         }
      } catch (StandardException var3) {
         throw var3;
      } catch (Throwable var4) {
         throw StandardException.unexpectedUserException(var4);
      }
   }
}
