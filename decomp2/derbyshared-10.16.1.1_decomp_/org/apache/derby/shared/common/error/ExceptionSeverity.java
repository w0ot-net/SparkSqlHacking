package org.apache.derby.shared.common.error;

public interface ExceptionSeverity {
   int NO_APPLICABLE_SEVERITY = 0;
   int WARNING_SEVERITY = 10000;
   int STATEMENT_SEVERITY = 20000;
   int TRANSACTION_SEVERITY = 30000;
   int SESSION_SEVERITY = 40000;
   int DATABASE_SEVERITY = 45000;
   int SYSTEM_SEVERITY = 50000;
}
