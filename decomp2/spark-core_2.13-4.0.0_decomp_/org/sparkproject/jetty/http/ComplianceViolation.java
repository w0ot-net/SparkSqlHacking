package org.sparkproject.jetty.http;

import java.util.Set;

public interface ComplianceViolation {
   String getName();

   String getURL();

   String getDescription();

   default boolean isAllowedBy(Mode mode) {
      return mode.allows(this);
   }

   public interface Listener {
      default void onComplianceViolation(Mode mode, ComplianceViolation violation, String details) {
      }
   }

   public interface Mode {
      String getName();

      boolean allows(ComplianceViolation var1);

      Set getKnown();

      Set getAllowed();
   }
}
