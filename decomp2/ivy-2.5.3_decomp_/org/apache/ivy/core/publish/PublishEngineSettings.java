package org.apache.ivy.core.publish;

import org.apache.ivy.plugins.parser.ParserSettings;
import org.apache.ivy.plugins.resolver.DependencyResolver;

public interface PublishEngineSettings extends ParserSettings {
   String substitute(String var1);

   DependencyResolver getResolver(String var1);
}
