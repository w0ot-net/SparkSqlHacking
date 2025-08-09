package org.apache.ivy.core.install;

import java.util.Collection;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.report.ReportOutputter;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public interface InstallEngineSettings extends ParserSettings {
   DependencyResolver getResolver(String var1);

   Collection getResolverNames();

   ReportOutputter[] getReportOutputters();

   void setLogNotConvertedExclusionRule(boolean var1);

   StatusManager getStatusManager();

   boolean logNotConvertedExclusionRule();

   PatternMatcher getMatcher(String var1);

   Collection getMatcherNames();
}
