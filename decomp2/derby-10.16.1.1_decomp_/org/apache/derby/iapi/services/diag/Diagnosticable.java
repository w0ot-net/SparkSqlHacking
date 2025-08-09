package org.apache.derby.iapi.services.diag;

import java.util.Properties;
import org.apache.derby.shared.common.error.StandardException;

public interface Diagnosticable {
   void init(Object var1);

   String diag() throws StandardException;

   void diag_detail(Properties var1) throws StandardException;
}
