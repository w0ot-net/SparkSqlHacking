package org.apache.logging.log4j.core.config.plugins.visitors;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;

public interface PluginVisitor {
   PluginVisitor setAnnotation(Annotation annotation);

   PluginVisitor setAliases(String... aliases);

   PluginVisitor setConversionType(Class conversionType);

   PluginVisitor setStrSubstitutor(StrSubstitutor substitutor);

   PluginVisitor setMember(Member member);

   Object visit(Configuration configuration, Node node, LogEvent event, StringBuilder log);
}
