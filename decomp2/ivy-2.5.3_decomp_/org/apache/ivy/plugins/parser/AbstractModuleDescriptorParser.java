package org.apache.ivy.plugins.parser;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.descriptor.Configuration;
import org.apache.ivy.core.module.descriptor.DefaultArtifact;
import org.apache.ivy.core.module.descriptor.DefaultDependencyDescriptor;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.DependencyDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.repository.ResourceHelper;
import org.apache.ivy.plugins.repository.url.URLResource;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public abstract class AbstractModuleDescriptorParser implements ModuleDescriptorParser {
   public ModuleDescriptor parseDescriptor(ParserSettings ivySettings, URL descriptorURL, boolean validate) throws ParseException, IOException {
      return this.parseDescriptor(ivySettings, descriptorURL, new URLResource(descriptorURL), validate);
   }

   public String getType() {
      return "ivy";
   }

   public Artifact getMetadataArtifact(ModuleRevisionId mrid, Resource res) {
      return DefaultArtifact.newIvyArtifact(mrid, new Date(res.getLastModified()));
   }

   protected abstract static class AbstractParser extends DefaultHandler {
      private static final String DEFAULT_CONF_MAPPING = "*->*";
      private String defaultConf;
      private String defaultConfMapping;
      private DefaultDependencyDescriptor defaultConfMappingDescriptor;
      private Resource res;
      private List errors = new ArrayList();
      private DefaultModuleDescriptor md;
      private ModuleDescriptorParser parser;

      protected AbstractParser(ModuleDescriptorParser parser) {
         this.parser = parser;
      }

      public ModuleDescriptorParser getModuleDescriptorParser() {
         return this.parser;
      }

      protected void checkErrors() throws ParseException {
         if (!this.errors.isEmpty()) {
            throw new ParseException(this.errors.toString(), 0);
         }
      }

      public void setResource(Resource res) {
         this.res = res;
         this.md = new DefaultModuleDescriptor(this.parser, res);
         this.md.setLastModified(ResourceHelper.getLastModifiedOrDefault(res));
      }

      protected Resource getResource() {
         return this.res;
      }

      protected String getDefaultConfMapping() {
         return this.defaultConfMapping;
      }

      protected void setDefaultConfMapping(String defaultConf) {
         this.defaultConfMapping = defaultConf;
         this.getMd().setDefaultConfMapping(defaultConf);
      }

      protected void parseDepsConfs(String confs, DefaultDependencyDescriptor dd) {
         this.parseDepsConfs(confs, dd, this.defaultConfMapping != null);
      }

      protected void parseDepsConfs(String confs, DefaultDependencyDescriptor dd, boolean useDefaultMappingToGuessRightOperand) {
         this.parseDepsConfs(confs, dd, useDefaultMappingToGuessRightOperand, true);
      }

      protected void parseDepsConfs(String confs, DefaultDependencyDescriptor dd, boolean useDefaultMappingToGuessRightOperand, boolean evaluateConditions) {
         if (confs != null) {
            String[] conf = confs.trim().split("\\s*;\\s*");
            this.parseDepsConfs(conf, dd, useDefaultMappingToGuessRightOperand, evaluateConditions);
         }
      }

      protected void parseDepsConfs(String[] conf, DefaultDependencyDescriptor dd, boolean useDefaultMappingToGuessRightOperand) {
         this.parseDepsConfs(conf, dd, useDefaultMappingToGuessRightOperand, true);
      }

      protected void parseDepsConfs(String[] confs, DefaultDependencyDescriptor dd, boolean useDefaultMappingToGuessRightOperand, boolean evaluateConditions) {
         this.replaceConfigurationWildcards(this.md);

         for(String conf : confs) {
            String[] ops = conf.split("->");
            switch (ops.length) {
               case 1:
                  String[] var20 = StringUtils.splitToArray(ops[0]);
                  int var21 = var20.length;
                  int var22 = 0;

                  for(; var22 < var21; ++var22) {
                     String modConf = var20[var22];
                     if (useDefaultMappingToGuessRightOperand) {
                        String[] depConfs = this.getDefaultConfMappingDescriptor().getDependencyConfigurations(modConf);
                        if (depConfs.length > 0) {
                           for(String depConf : depConfs) {
                              String mappedDependency = evaluateConditions ? this.evaluateCondition(depConf.trim(), dd) : depConf.trim();
                              if (mappedDependency != null) {
                                 dd.addDependencyConfiguration(modConf, mappedDependency);
                              }
                           }
                        } else {
                           dd.addDependencyConfiguration(modConf, modConf);
                        }
                     } else {
                        dd.addDependencyConfiguration(modConf, modConf);
                     }
                  }
                  break;
               case 2:
                  for(String modConf : StringUtils.splitToArray(ops[0])) {
                     for(String depConf : StringUtils.splitToArray(ops[1])) {
                        String mappedDependency = evaluateConditions ? this.evaluateCondition(depConf, dd) : depConf;
                        if (mappedDependency != null) {
                           dd.addDependencyConfiguration(modConf, mappedDependency);
                        }
                     }
                  }
                  break;
               default:
                  this.addError("invalid conf " + conf + " for " + dd);
            }
         }

         if (this.md.isMappingOverride()) {
            this.addExtendingConfigurations(confs, dd, useDefaultMappingToGuessRightOperand);
         }

      }

      private String evaluateCondition(String conf, DefaultDependencyDescriptor dd) {
         if (conf.charAt(0) != '[') {
            return conf;
         } else {
            int endConditionIndex = conf.indexOf(93);
            if (endConditionIndex == -1) {
               this.addError("invalid conf " + conf + " for " + dd);
               return null;
            } else {
               String condition = conf.substring(1, endConditionIndex);
               int notEqualIndex = condition.indexOf("!=");
               if (notEqualIndex == -1) {
                  int equalIndex = condition.indexOf(61);
                  if (equalIndex == -1) {
                     this.addError("invalid conf " + conf + " for " + dd.getDependencyRevisionId());
                     return null;
                  }

                  String leftOp = condition.substring(0, equalIndex).trim();
                  String rightOp = condition.substring(equalIndex + 1).trim();
                  if (leftOp.equals("org") || leftOp.equals("organization")) {
                     leftOp = "organisation";
                  }

                  String attrValue = dd.getAttribute(leftOp);
                  if (!rightOp.equals(attrValue)) {
                     return null;
                  }
               } else {
                  String leftOp = condition.substring(0, notEqualIndex).trim();
                  String rightOp = condition.substring(notEqualIndex + 2).trim();
                  if (leftOp.equals("org") || leftOp.equals("organization")) {
                     leftOp = "organisation";
                  }

                  String attrValue = dd.getAttribute(leftOp);
                  if (rightOp.equals(attrValue)) {
                     return null;
                  }
               }

               return conf.substring(endConditionIndex + 1);
            }
         }
      }

      private void addExtendingConfigurations(String[] confs, DefaultDependencyDescriptor dd, boolean useDefaultMappingToGuessRightOperand) {
         for(String conf : confs) {
            this.addExtendingConfigurations(conf, dd, useDefaultMappingToGuessRightOperand);
         }

      }

      private void addExtendingConfigurations(String conf, DefaultDependencyDescriptor dd, boolean useDefaultMappingToGuessRightOperand) {
         Set<String> configsToAdd = new HashSet();

         for(Configuration config : this.md.getConfigurations()) {
            for(String ext : config.getExtends()) {
               if (conf.equals(ext)) {
                  String configName = config.getName();
                  configsToAdd.add(configName);
                  this.addExtendingConfigurations(configName, dd, useDefaultMappingToGuessRightOperand);
               }
            }
         }

         this.parseDepsConfs((String[])configsToAdd.toArray(new String[configsToAdd.size()]), dd, useDefaultMappingToGuessRightOperand);
      }

      protected DependencyDescriptor getDefaultConfMappingDescriptor() {
         if (this.defaultConfMappingDescriptor == null) {
            this.defaultConfMappingDescriptor = new DefaultDependencyDescriptor(ModuleRevisionId.newInstance("", "", ""), false);
            this.parseDepsConfs(this.defaultConfMapping, this.defaultConfMappingDescriptor, false, false);
         }

         return this.defaultConfMappingDescriptor;
      }

      protected void addError(String msg) {
         if (this.res != null) {
            this.errors.add(msg + " in " + this.res + "\n");
         } else {
            this.errors.add(msg + "\n");
         }

      }

      public void warning(SAXParseException ex) {
         Message.warn("xml parsing: " + this.getLocationString(ex) + ": " + ex.getMessage());
      }

      public void error(SAXParseException ex) {
         this.addError("xml parsing: " + this.getLocationString(ex) + ": " + ex.getMessage());
      }

      public void fatalError(SAXParseException ex) throws SAXException {
         this.addError("[Fatal Error] " + this.getLocationString(ex) + ": " + ex.getMessage());
      }

      private String getLocationString(SAXParseException ex) {
         StringBuilder str = new StringBuilder();
         String systemId = ex.getSystemId();
         if (systemId != null) {
            int index = systemId.lastIndexOf(47);
            if (index != -1) {
               systemId = systemId.substring(index + 1);
            }

            str.append(systemId);
         } else if (this.getResource() != null) {
            str.append(this.getResource().toString());
         }

         str.append(':');
         str.append(ex.getLineNumber());
         str.append(':');
         str.append(ex.getColumnNumber());
         return str.toString();
      }

      protected String getDefaultConf() {
         return this.defaultConf != null ? this.defaultConf : (this.defaultConfMapping != null ? this.defaultConfMapping : "*->*");
      }

      protected void setDefaultConf(String defaultConf) {
         this.defaultConf = defaultConf;
         this.getMd().setDefaultConf(defaultConf);
      }

      public ModuleDescriptor getModuleDescriptor() throws ParseException {
         this.checkErrors();
         return this.md;
      }

      protected Date getDefaultPubDate() {
         return new Date(this.md.getLastModified());
      }

      private void replaceConfigurationWildcards(ModuleDescriptor md) {
         for(Configuration config : md.getConfigurations()) {
            config.replaceWildcards(md);
         }

      }

      protected void setMd(DefaultModuleDescriptor md) {
         this.md = md;
      }

      protected DefaultModuleDescriptor getMd() {
         return this.md;
      }
   }
}
