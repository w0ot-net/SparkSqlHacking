package org.apache.logging.log4j.layout.template.json.resolver;

import org.apache.logging.log4j.core.lookup.StrSubstitutor;

public interface TemplateResolverStringSubstitutor {
   StrSubstitutor getInternalSubstitutor();

   boolean isStable();

   String replace(Object value, String source);
}
