package org.apache.ivy.core.module.descriptor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ivy.core.module.id.ArtifactId;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.id.ModuleRules;
import org.apache.ivy.plugins.conflict.ConflictManager;
import org.apache.ivy.plugins.latest.ArtifactInfo;
import org.apache.ivy.plugins.parser.ModuleDescriptorParser;
import org.apache.ivy.plugins.repository.Resource;
import org.apache.ivy.plugins.version.VersionMatcher;
import org.apache.ivy.util.extendable.ExtendableItem;

public interface ModuleDescriptor extends ExtendableItem, ArtifactInfo, DependencyDescriptorMediator {
   String DEFAULT_CONFIGURATION = "default";
   String CALLER_ALL_CONFIGURATION = "all";

   boolean isDefault();

   ModuleRevisionId getModuleRevisionId();

   ModuleRevisionId getResolvedModuleRevisionId();

   void setResolvedModuleRevisionId(ModuleRevisionId var1);

   ExtendsDescriptor[] getInheritedDescriptors();

   void setResolvedPublicationDate(Date var1);

   String getStatus();

   Date getPublicationDate();

   Date getResolvedPublicationDate();

   Configuration[] getConfigurations();

   String[] getConfigurationsNames();

   String[] getPublicConfigurationsNames();

   Artifact[] getArtifacts(String var1);

   Artifact[] getAllArtifacts();

   DependencyDescriptor[] getDependencies();

   boolean dependsOn(VersionMatcher var1, ModuleDescriptor var2);

   Configuration getConfiguration(String var1);

   ConflictManager getConflictManager(ModuleId var1);

   License[] getLicenses();

   String getHomePage();

   String getDescription();

   long getLastModified();

   void toIvyFile(File var1) throws ParseException, IOException;

   ModuleDescriptorParser getParser();

   Resource getResource();

   Artifact getMetadataArtifact();

   boolean canExclude();

   boolean doesExclude(String[] var1, ArtifactId var2);

   ExcludeRule[] getAllExcludeRules();

   ModuleRules getAllDependencyDescriptorMediators();

   Map getExtraAttributesNamespaces();

   /** @deprecated */
   @Deprecated
   Map getExtraInfo();

   List getExtraInfos();

   String getExtraInfoContentByTagName(String var1);

   ExtraInfoHolder getExtraInfoByTagName(String var1);
}
