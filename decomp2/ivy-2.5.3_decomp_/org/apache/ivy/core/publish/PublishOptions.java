package org.apache.ivy.core.publish;

import java.util.Date;
import org.apache.ivy.core.module.descriptor.Artifact;

public class PublishOptions {
   private String srcIvyPattern;
   private String pubrevision;
   private String status;
   private Date pubdate;
   private Artifact[] extraArtifacts;
   private boolean validate;
   private boolean overwrite;
   private boolean update;
   private boolean merge = true;
   private String[] confs;
   private boolean haltonmissing;
   private String pubBranch;
   private boolean warnonmissing;

   public String[] getConfs() {
      return this.confs;
   }

   public PublishOptions setConfs(String[] confs) {
      this.confs = confs;
      return this;
   }

   public Artifact[] getExtraArtifacts() {
      return this.extraArtifacts;
   }

   public PublishOptions setExtraArtifacts(Artifact[] extraArtifacts) {
      this.extraArtifacts = extraArtifacts;
      return this;
   }

   public boolean isOverwrite() {
      return this.overwrite;
   }

   public PublishOptions setOverwrite(boolean overwrite) {
      this.overwrite = overwrite;
      return this;
   }

   public Date getPubdate() {
      return this.pubdate;
   }

   public PublishOptions setPubdate(Date pubdate) {
      this.pubdate = pubdate;
      return this;
   }

   public String getPubrevision() {
      return this.pubrevision;
   }

   public PublishOptions setPubrevision(String pubrevision) {
      this.pubrevision = pubrevision;
      return this;
   }

   public String getSrcIvyPattern() {
      return this.srcIvyPattern;
   }

   public PublishOptions setSrcIvyPattern(String srcIvyPattern) {
      this.srcIvyPattern = srcIvyPattern;
      return this;
   }

   public String getStatus() {
      return this.status;
   }

   public PublishOptions setStatus(String status) {
      this.status = status;
      return this;
   }

   public boolean isUpdate() {
      return this.update;
   }

   public PublishOptions setUpdate(boolean update) {
      this.update = update;
      return this;
   }

   public boolean isMerge() {
      return this.merge;
   }

   public PublishOptions setMerge(boolean merge) {
      this.merge = merge;
      return this;
   }

   public boolean isValidate() {
      return this.validate;
   }

   public PublishOptions setValidate(boolean validate) {
      this.validate = validate;
      return this;
   }

   public boolean isHaltOnMissing() {
      return this.haltonmissing;
   }

   public PublishOptions setHaltOnMissing(boolean haltonmissing) {
      this.haltonmissing = haltonmissing;
      return this;
   }

   public String getPubBranch() {
      return this.pubBranch;
   }

   public PublishOptions setPubbranch(String pubbranch) {
      this.pubBranch = pubbranch;
      return this;
   }

   public boolean isWarnOnMissing() {
      return this.warnonmissing;
   }

   public PublishOptions setWarnOnMissing(boolean warnonmissing) {
      this.warnonmissing = warnonmissing;
      return this;
   }
}
