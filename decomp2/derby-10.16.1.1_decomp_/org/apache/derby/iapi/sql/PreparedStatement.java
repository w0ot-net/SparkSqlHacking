package org.apache.derby.iapi.sql;

import java.sql.SQLWarning;
import java.sql.Timestamp;
import org.apache.derby.iapi.sql.conn.LanguageConnectionContext;
import org.apache.derby.iapi.sql.depend.Dependent;
import org.apache.derby.iapi.types.DataTypeDescriptor;
import org.apache.derby.shared.common.error.StandardException;

public interface PreparedStatement extends Dependent {
   boolean upToDate() throws StandardException;

   void rePrepare(LanguageConnectionContext var1) throws StandardException;

   Activation getActivation(LanguageConnectionContext var1, boolean var2) throws StandardException;

   ResultSet execute(Activation var1, boolean var2, long var3) throws StandardException;

   ResultSet executeSubStatement(Activation var1, Activation var2, boolean var3, long var4) throws StandardException;

   ResultSet executeSubStatement(LanguageConnectionContext var1, boolean var2, long var3) throws StandardException;

   ResultDescription getResultDescription();

   boolean referencesSessionSchema();

   DataTypeDescriptor[] getParameterTypes();

   DataTypeDescriptor getParameterType(int var1) throws StandardException;

   String getSource();

   String getSPSName();

   long getCompileTimeInMillis();

   long getParseTimeInMillis();

   long getBindTimeInMillis();

   long getOptimizeTimeInMillis();

   long getGenerateTimeInMillis();

   Timestamp getBeginCompileTimestamp();

   Timestamp getEndCompileTimestamp();

   boolean isAtomic();

   SQLWarning getCompileTimeWarnings();

   long getVersionCounter();
}
