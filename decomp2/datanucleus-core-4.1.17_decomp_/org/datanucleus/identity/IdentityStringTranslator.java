package org.datanucleus.identity;

import java.io.Serializable;
import org.datanucleus.ExecutionContext;

public interface IdentityStringTranslator extends Serializable {
   Object getIdentity(ExecutionContext var1, String var2);
}
