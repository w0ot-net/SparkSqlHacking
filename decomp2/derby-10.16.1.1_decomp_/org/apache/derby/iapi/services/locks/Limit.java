package org.apache.derby.iapi.services.locks;

import java.util.Enumeration;
import org.apache.derby.shared.common.error.StandardException;

public interface Limit {
   void reached(CompatibilitySpace var1, Object var2, int var3, Enumeration var4, int var5) throws StandardException;
}
