package org.apache.orc.impl.filter;

import org.apache.orc.OrcFilterContext;

public interface VectorFilter {
   void filter(OrcFilterContext var1, Selected var2, Selected var3);
}
