package org.apache.ivy.plugins.report;

import java.io.IOException;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;

public interface ReportOutputter {
   String CONSOLE = "console";
   String XML = "xml";

   void output(ResolveReport var1, ResolutionCacheManager var2, ResolveOptions var3) throws IOException;

   String getName();
}
