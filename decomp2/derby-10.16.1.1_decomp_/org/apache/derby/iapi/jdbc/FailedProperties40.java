package org.apache.derby.iapi.jdbc;

import java.sql.ClientInfoStatus;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FailedProperties40 {
   private final HashMap failedProps_ = new HashMap();
   private final String firstKey_;
   private final String firstValue_;

   public static Properties makeProperties(String var0, String var1) {
      Properties var2 = new Properties();
      if (var0 != null || var1 != null) {
         var2.setProperty(var0, var1);
      }

      return var2;
   }

   public FailedProperties40(Properties var1) {
      if (var1 != null && !var1.isEmpty()) {
         Enumeration var2 = var1.keys();
         this.firstKey_ = (String)var2.nextElement();
         this.firstValue_ = var1.getProperty(this.firstKey_);
         this.failedProps_.put(this.firstKey_, ClientInfoStatus.REASON_UNKNOWN_PROPERTY);

         while(var2.hasMoreElements()) {
            this.failedProps_.put((String)var2.nextElement(), ClientInfoStatus.REASON_UNKNOWN_PROPERTY);
         }

      } else {
         this.firstKey_ = null;
         this.firstValue_ = null;
      }
   }

   public Map getProperties() {
      return this.failedProps_;
   }

   public String getFirstKey() {
      return this.firstKey_;
   }

   public String getFirstValue() {
      return this.firstValue_;
   }
}
