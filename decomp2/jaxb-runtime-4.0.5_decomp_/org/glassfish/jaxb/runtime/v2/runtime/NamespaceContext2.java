package org.glassfish.jaxb.runtime.v2.runtime;

import com.sun.istack.NotNull;
import javax.xml.namespace.NamespaceContext;

public interface NamespaceContext2 extends NamespaceContext {
   String declareNamespace(String var1, String var2, boolean var3);

   int force(@NotNull String var1, @NotNull String var2);
}
