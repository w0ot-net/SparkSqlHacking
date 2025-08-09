package org.apache.ivy.plugins.resolver;

import java.util.Collection;
import org.apache.ivy.core.cache.RepositoryCacheManager;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.plugins.latest.LatestStrategy;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.signer.SignatureGenerator;
import org.apache.ivy.plugins.version.VersionMatcher;

public interface ResolverSettings extends ParserSettings {
   LatestStrategy getLatestStrategy(String var1);

   LatestStrategy getDefaultLatestStrategy();

   RepositoryCacheManager getRepositoryCacheManager(String var1);

   RepositoryCacheManager getDefaultRepositoryCacheManager();

   RepositoryCacheManager[] getRepositoryCacheManagers();

   Namespace getNamespace(String var1);

   Namespace getSystemNamespace();

   String getVariable(String var1);

   void configureRepositories(boolean var1);

   VersionMatcher getVersionMatcher();

   String getResolveMode(ModuleId var1);

   void filterIgnore(Collection var1);

   SignatureGenerator getSignatureGenerator(String var1);
}
