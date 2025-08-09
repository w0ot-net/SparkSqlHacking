package org.apache.derby.vti;

import java.sql.SQLException;
import org.apache.derby.iapi.store.access.Qualifier;

public interface IQualifyable {
   void setQualifiers(VTIEnvironment var1, Qualifier[][] var2) throws SQLException;
}
