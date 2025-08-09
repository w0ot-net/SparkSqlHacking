package org.apache.ivy.plugins.parser;

import java.io.File;
import java.util.Map;
import org.apache.ivy.core.RelativeUrlResolver;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.core.settings.TimeoutConstraint;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.matcher.PatternMatcher;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public interface ParserSettings {
   String substitute(String var1);

   Map substitute(Map var1);

   ResolutionCacheManager getResolutionCacheManager();

   ConflictManager getConflictManager(String var1);

   PatternMatcher getMatcher(String var1);

   Namespace getNamespace(String var1);

   StatusManager getStatusManager();

   RelativeUrlResolver getRelativeUrlResolver();

   DependencyResolver getResolver(ModuleRevisionId var1);

   File resolveFile(String var1);

   String getDefaultBranch(ModuleId var1);

   Namespace getContextNamespace();

   String getVariable(String var1);

   TimeoutConstraint getTimeoutConstraint(String var1);
}
