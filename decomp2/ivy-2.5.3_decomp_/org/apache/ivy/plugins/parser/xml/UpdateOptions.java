package org.apache.ivy.plugins.parser.xml;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.plugins.namespace.Namespace;
import org.apache.ivy.plugins.parser.ParserSettings;

public class UpdateOptions {
   private ParserSettings settings = null;
   private Namespace namespace = null;
   private Map resolvedRevisions = Collections.emptyMap();
   private Map resolvedBranches = Collections.emptyMap();
   private String status = null;
   private String revision = null;
   private Date pubdate = null;
   private boolean replaceInclude = true;
   private boolean merge = true;
   private ModuleDescriptor mergedDescriptor = null;
   private String[] confsToExclude = null;
   private boolean updateBranch = true;
   private String branch;
   private boolean generateRevConstraint = true;

   public ParserSettings getSettings() {
      return this.settings;
   }

   public UpdateOptions setSettings(ParserSettings settings) {
      this.settings = settings;
      return this;
   }

   public Namespace getNamespace() {
      return this.namespace;
   }

   public UpdateOptions setNamespace(Namespace ns) {
      this.namespace = ns;
      return this;
   }

   public Map getResolvedRevisions() {
      return this.resolvedRevisions;
   }

   public UpdateOptions setResolvedRevisions(Map resolvedRevisions) {
      this.resolvedRevisions = resolvedRevisions;
      return this;
   }

   public String getStatus() {
      return this.status;
   }

   public UpdateOptions setStatus(String status) {
      this.status = status;
      return this;
   }

   public String getRevision() {
      return this.revision;
   }

   public UpdateOptions setRevision(String revision) {
      this.revision = revision;
      return this;
   }

   public Date getPubdate() {
      return this.pubdate;
   }

   public UpdateOptions setPubdate(Date pubdate) {
      this.pubdate = pubdate;
      return this;
   }

   public boolean isReplaceInclude() {
      return this.replaceInclude;
   }

   public UpdateOptions setReplaceInclude(boolean replaceInclude) {
      this.replaceInclude = replaceInclude;
      return this;
   }

   public boolean isMerge() {
      return this.merge && this.mergedDescriptor != null && this.mergedDescriptor.getInheritedDescriptors().length > 0;
   }

   public UpdateOptions setMerge(boolean merge) {
      this.merge = merge;
      return this;
   }

   public ModuleDescriptor getMergedDescriptor() {
      return this.mergedDescriptor;
   }

   public UpdateOptions setMergedDescriptor(ModuleDescriptor mergedDescriptor) {
      this.mergedDescriptor = mergedDescriptor;
      return this;
   }

   public String[] getConfsToExclude() {
      return this.confsToExclude;
   }

   public UpdateOptions setConfsToExclude(String[] confsToExclude) {
      this.confsToExclude = confsToExclude;
      return this;
   }

   public boolean isUpdateBranch() {
      return this.updateBranch;
   }

   public UpdateOptions setUpdateBranch(boolean updateBranch) {
      this.updateBranch = updateBranch;
      return this;
   }

   public String getBranch() {
      return this.branch;
   }

   public UpdateOptions setBranch(String pubBranch) {
      this.branch = pubBranch;
      return this;
   }

   public boolean isGenerateRevConstraint() {
      return this.generateRevConstraint;
   }

   public UpdateOptions setGenerateRevConstraint(boolean generateRevConstraint) {
      this.generateRevConstraint = generateRevConstraint;
      return this;
   }

   public Map getResolvedBranches() {
      return this.resolvedBranches;
   }

   public UpdateOptions setResolvedBranches(Map resolvedBranches) {
      this.resolvedBranches = resolvedBranches;
      return this;
   }
}
