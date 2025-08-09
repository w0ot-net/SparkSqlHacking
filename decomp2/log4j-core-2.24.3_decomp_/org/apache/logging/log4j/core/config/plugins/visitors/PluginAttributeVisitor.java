package org.apache.logging.log4j.core.config.plugins.visitors;

import java.util.Map;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.util.StringBuilders;

public class PluginAttributeVisitor extends AbstractPluginVisitor {
   public PluginAttributeVisitor() {
      super(PluginAttribute.class);
   }

   public Object visit(final Configuration configuration, final Node node, final LogEvent event, final StringBuilder log) {
      String name = ((PluginAttribute)this.annotation).value();
      Map<String, String> attributes = node.getAttributes();
      String rawValue = removeAttributeValue(attributes, name, this.aliases);
      String replacedValue = this.substitutor.replace(event, rawValue);
      Object defaultValue = this.findDefaultValue(event);
      Object value = this.convert(replacedValue, defaultValue);
      Object debugValue = ((PluginAttribute)this.annotation).sensitive() ? "*****" : value;
      StringBuilders.appendKeyDqValue(log, name, debugValue);
      return value;
   }

   private Object findDefaultValue(final LogEvent event) {
      if (this.conversionType != Integer.TYPE && this.conversionType != Integer.class) {
         if (this.conversionType != Long.TYPE && this.conversionType != Long.class) {
            if (this.conversionType != Boolean.TYPE && this.conversionType != Boolean.class) {
               if (this.conversionType != Float.TYPE && this.conversionType != Float.class) {
                  if (this.conversionType != Double.TYPE && this.conversionType != Double.class) {
                     if (this.conversionType != Byte.TYPE && this.conversionType != Byte.class) {
                        if (this.conversionType != Character.TYPE && this.conversionType != Character.class) {
                           if (this.conversionType != Short.TYPE && this.conversionType != Short.class) {
                              return this.conversionType == Class.class ? ((PluginAttribute)this.annotation).defaultClass() : this.substitutor.replace(event, ((PluginAttribute)this.annotation).defaultString());
                           } else {
                              return ((PluginAttribute)this.annotation).defaultShort();
                           }
                        } else {
                           return ((PluginAttribute)this.annotation).defaultChar();
                        }
                     } else {
                        return ((PluginAttribute)this.annotation).defaultByte();
                     }
                  } else {
                     return ((PluginAttribute)this.annotation).defaultDouble();
                  }
               } else {
                  return ((PluginAttribute)this.annotation).defaultFloat();
               }
            } else {
               return ((PluginAttribute)this.annotation).defaultBoolean();
            }
         } else {
            return ((PluginAttribute)this.annotation).defaultLong();
         }
      } else {
         return ((PluginAttribute)this.annotation).defaultInt();
      }
   }
}
