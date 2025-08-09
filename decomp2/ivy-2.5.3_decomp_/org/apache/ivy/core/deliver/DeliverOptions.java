package org.apache.ivy.core.deliver;

import java.util.Date;
import org.apache.ivy.core.settings.IvySettings;

public class DeliverOptions {
   private String status;
   private Date pubdate;
   private PublishingDependencyRevisionResolver pdrResolver = new DefaultPublishingDRResolver();
   private boolean validate = true;
   private boolean resolveDynamicRevisions = true;
   private boolean replaceForcedRevisions = false;
   private String resolveId;
   private String[] confs;
   private String pubBranch;
   private boolean generateRevConstraint = true;
   private boolean merge = true;

   public static DeliverOptions newInstance(IvySettings settings) {
      return new DeliverOptions((String)null, new Date(), new DefaultPublishingDRResolver(), settings.doValidate(), true, (String[])null);
   }

   public DeliverOptions() {
   }

   public DeliverOptions(String status, Date pubDate, PublishingDependencyRevisionResolver pdrResolver, boolean validate, boolean resolveDynamicRevisions, String[] confs) {
      this.status = status;
      this.pubdate = pubDate;
      this.pdrResolver = pdrResolver;
      this.validate = validate;
      this.resolveDynamicRevisions = resolveDynamicRevisions;
      this.confs = confs;
   }

   public PublishingDependencyRevisionResolver getPdrResolver() {
      return this.pdrResolver;
   }

   public DeliverOptions setPdrResolver(PublishingDependencyRevisionResolver pdrResolver) {
      this.pdrResolver = pdrResolver;
      return this;
   }

   public boolean isResolveDynamicRevisions() {
      return this.resolveDynamicRevisions;
   }

   public DeliverOptions setResolveDynamicRevisions(boolean resolveDynamicRevisions) {
      this.resolveDynamicRevisions = resolveDynamicRevisions;
      return this;
   }

   public boolean isReplaceForcedRevisions() {
      return this.replaceForcedRevisions;
   }

   public DeliverOptions setReplaceForcedRevisions(boolean replaceForcedRevisions) {
      this.replaceForcedRevisions = replaceForcedRevisions;
      return this;
   }

   public boolean isValidate() {
      return this.validate;
   }

   public DeliverOptions setValidate(boolean validate) {
      this.validate = validate;
      return this;
   }

   public Date getPubdate() {
      return this.pubdate;
   }

   public DeliverOptions setPubdate(Date pubdate) {
      this.pubdate = pubdate;
      return this;
   }

   public String getStatus() {
      return this.status;
   }

   public DeliverOptions setStatus(String status) {
      this.status = status;
      return this;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   public DeliverOptions setResolveId(String resolveId) {
      this.resolveId = resolveId;
      return this;
   }

   public String[] getConfs() {
      return this.confs;
   }

   public DeliverOptions setConfs(String[] confs) {
      this.confs = confs;
      return this;
   }

   public String getPubBranch() {
      return this.pubBranch;
   }

   public DeliverOptions setPubBranch(String pubBranch) {
      this.pubBranch = pubBranch;
      return this;
   }

   public boolean isGenerateRevConstraint() {
      return this.generateRevConstraint;
   }

   public DeliverOptions setGenerateRevConstraint(boolean generateRevConstraint) {
      this.generateRevConstraint = generateRevConstraint;
      return this;
   }

   public boolean isMerge() {
      return this.merge;
   }

   public DeliverOptions setMerge(boolean merge) {
      this.merge = merge;
      return this;
   }

   public String toString() {
      return "status=" + this.status + " pubdate=" + this.pubdate + " validate=" + this.validate + " resolveDynamicRevisions=" + this.resolveDynamicRevisions + " merge=" + this.merge + " resolveId=" + this.resolveId + " pubBranch=" + this.pubBranch;
   }
}
