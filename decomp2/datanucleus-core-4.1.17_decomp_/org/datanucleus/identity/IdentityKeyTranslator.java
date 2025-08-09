package org.datanucleus.identity;

import java.io.Serializable;
import org.datanucleus.ExecutionContext;

public interface IdentityKeyTranslator extends Serializable {
   Object getKey(ExecutionContext var1, Class var2, Object var3);
}
