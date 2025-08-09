package org.apache.ivy.ant;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyPatternHelper;
import org.apache.ivy.core.module.descriptor.Artifact;
import org.apache.ivy.core.module.id.ArtifactRevisionId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.publish.PublishOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.util.DateUtil;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DynamicAttribute;

public class IvyPublish extends IvyTask {
   private String organisation;
   private String module;
   private String revision;
   private String pubRevision;
   private String srcivypattern;
   private String status;
   private String conf = null;
   private String pubdate;
   private String deliverTarget;
   private String publishResolverName = null;
   private List artifactspattern = new ArrayList();
   private File deliveryList;
   private boolean publishivy = true;
   private boolean warnonmissing = true;
   private boolean haltonmissing = true;
   private boolean overwrite = false;
   private boolean update = false;
   private boolean merge = true;
   private boolean replacedynamicrev = true;
   private boolean forcedeliver;
   private Collection artifacts = new ArrayList();
   private String pubBranch;

   public void setCache(File cache) {
      this.cacheAttributeNotSupported();
   }

   public String getSrcivypattern() {
      return this.srcivypattern;
   }

   public void setSrcivypattern(String destivypattern) {
      this.srcivypattern = destivypattern;
   }

   /** @deprecated */
   @Deprecated
   public String getDeliverivypattern() {
      return this.srcivypattern;
   }

   /** @deprecated */
   @Deprecated
   public void setDeliverivypattern(String destivypattern) {
      this.srcivypattern = destivypattern;
   }

   public String getModule() {
      return this.module;
   }

   public void setModule(String module) {
      this.module = module;
   }

   public String getOrganisation() {
      return this.organisation;
   }

   public void setOrganisation(String organisation) {
      this.organisation = organisation;
   }

   public String getPubdate() {
      return this.pubdate;
   }

   public void setPubdate(String pubdate) {
      this.pubdate = pubdate;
   }

   public String getPubrevision() {
      return this.pubRevision;
   }

   public void setPubrevision(String pubRevision) {
      this.pubRevision = pubRevision;
   }

   public String getPubbranch() {
      return this.pubBranch;
   }

   public void setPubbranch(String pubBranch) {
      this.pubBranch = pubBranch;
   }

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public String getStatus() {
      return this.status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public void setConf(String conf) {
      this.conf = conf;
   }

   public void setDelivertarget(String deliverTarget) {
      this.deliverTarget = deliverTarget;
   }

   public void setDeliveryList(File deliveryList) {
      this.deliveryList = deliveryList;
   }

   public String getResolver() {
      return this.publishResolverName;
   }

   public void setResolver(String publishResolverName) {
      this.publishResolverName = publishResolverName;
   }

   public String getArtifactspattern() {
      return this.artifactspattern.isEmpty() ? null : (String)this.artifactspattern.get(0);
   }

   public void setArtifactspattern(String artifactsPattern) {
      this.artifactspattern.clear();
      this.artifactspattern.add(artifactsPattern);
   }

   public void addArtifactspattern(String artifactsPattern) {
      this.artifactspattern.add(artifactsPattern);
   }

   public void addConfiguredArtifacts(ArtifactsPattern p) {
      this.artifactspattern.add(p.getPattern());
   }

   public boolean isReplacedynamicrev() {
      return this.replacedynamicrev;
   }

   public void setReplacedynamicrev(boolean replacedynamicrev) {
      this.replacedynamicrev = replacedynamicrev;
   }

   public boolean isMerge() {
      return this.merge;
   }

   public void setMerge(boolean merge) {
      this.merge = merge;
   }

   public void doExecute() throws BuildException {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();
      this.organisation = this.getProperty(this.organisation, settings, "ivy.organisation");
      this.module = this.getProperty(this.module, settings, "ivy.module");
      this.revision = this.getProperty(this.revision, settings, "ivy.revision");
      this.pubBranch = this.getProperty(this.pubBranch, settings, "ivy.deliver.branch");
      this.pubRevision = this.getProperty(this.pubRevision, settings, "ivy.deliver.revision");
      if (this.artifactspattern.isEmpty()) {
         String p = this.getProperty((String)null, settings, "ivy.publish.src.artifacts.pattern");
         if (p != null) {
            this.artifactspattern.add(p);
         }
      }

      if (this.srcivypattern == null) {
         this.srcivypattern = this.getArtifactspattern();
      }

      this.status = this.getProperty(this.status, settings, "ivy.status");
      if (this.organisation == null) {
         throw new BuildException("no organisation provided for ivy publish task: It can either be set explicitly via the attribute 'organisation' or via 'ivy.organisation' property or a prior call to <resolve/>");
      } else if (this.module == null) {
         throw new BuildException("no module name provided for ivy publish task: It can either be set explicitly via the attribute 'module' or via 'ivy.module' property or a prior call to <resolve/>");
      } else if (this.revision == null) {
         throw new BuildException("no module revision provided for ivy publish task: It can either be set explicitly via the attribute 'revision' or via 'ivy.revision' property or a prior call to <resolve/>");
      } else if (this.artifactspattern.isEmpty()) {
         throw new BuildException("no artifacts pattern: either provide it through parameter or through ivy.publish.src.artifacts.pattern property");
      } else if (this.publishResolverName == null) {
         throw new BuildException("no publish deliver name: please provide it through parameter 'resolver'");
      } else {
         if ("working".equals(this.revision)) {
            this.revision = Ivy.getWorkingRevision();
         }

         Date pubdate = getPubDate(this.pubdate, new Date());
         if (this.pubRevision == null) {
            if (this.revision.startsWith("working@")) {
               this.pubRevision = DateUtil.format(pubdate);
            } else {
               this.pubRevision = this.revision;
            }
         }

         if (this.status == null) {
            throw new BuildException("no status provided: either provide it as parameter or through the ivy.status.default property");
         } else {
            ModuleRevisionId mrid = ModuleRevisionId.newInstance(this.organisation, this.module, this.revision);

            try {
               File ivyFile = this.getProject().resolveFile(IvyPatternHelper.substitute(this.srcivypattern, this.organisation, this.module, this.pubRevision, "ivy", "ivy", "xml"));
               if (this.publishivy && (!ivyFile.exists() || this.forcedeliver)) {
                  IvyDeliver deliver = new IvyDeliver();
                  deliver.setSettingsRef(this.getSettingsRef());
                  deliver.setTaskName(this.getTaskName());
                  deliver.setProject(this.getProject());
                  deliver.setDeliverpattern(this.getSrcivypattern());
                  deliver.setDelivertarget(this.deliverTarget);
                  deliver.setDeliveryList(this.deliveryList);
                  deliver.setModule(this.getModule());
                  deliver.setOrganisation(this.getOrganisation());
                  deliver.setPubdate(DateUtil.format(pubdate));
                  deliver.setPubrevision(this.getPubrevision());
                  deliver.setPubbranch(this.getPubbranch());
                  deliver.setRevision(this.getRevision());
                  deliver.setStatus(this.getStatus());
                  deliver.setValidate(this.doValidate(settings));
                  deliver.setReplacedynamicrev(this.isReplacedynamicrev());
                  deliver.setMerge(this.merge);
                  deliver.setConf(this.conf);
                  deliver.execute();
               }

               ivy.publish(mrid, this.artifactspattern, this.publishResolverName, (new PublishOptions()).setPubrevision(this.getPubrevision()).setPubbranch(this.getPubbranch()).setSrcIvyPattern(this.publishivy ? this.srcivypattern : null).setStatus(this.getStatus()).setPubdate(pubdate).setExtraArtifacts((Artifact[])this.artifacts.toArray(new Artifact[this.artifacts.size()])).setValidate(this.doValidate(settings)).setOverwrite(this.overwrite).setUpdate(this.update).setMerge(this.merge).setWarnOnMissing(this.warnonmissing).setHaltOnMissing(this.haltonmissing).setConfs(StringUtils.splitToArray(this.conf)));
            } catch (Exception e) {
               if (e instanceof BuildException) {
                  throw (BuildException)e;
               } else {
                  throw new BuildException("impossible to publish artifacts for " + mrid + ": " + e, e);
               }
            }
         }
      }
   }

   public PublishArtifact createArtifact() {
      PublishArtifact art = new PublishArtifact();
      this.artifacts.add(art);
      return art;
   }

   public boolean isPublishivy() {
      return this.publishivy;
   }

   public void setPublishivy(boolean publishivy) {
      this.publishivy = publishivy;
   }

   public boolean isWarnonmissing() {
      return this.warnonmissing;
   }

   public void setWarnonmissing(boolean warnonmissing) {
      this.warnonmissing = warnonmissing;
   }

   public boolean isHaltonmissing() {
      return this.haltonmissing;
   }

   public void setHaltonmissing(boolean haltonmissing) {
      this.haltonmissing = haltonmissing;
   }

   public boolean isOverwrite() {
      return this.overwrite;
   }

   public void setOverwrite(boolean overwrite) {
      this.overwrite = overwrite;
   }

   public void setForcedeliver(boolean b) {
      this.forcedeliver = b;
   }

   public boolean isForcedeliver() {
      return this.forcedeliver;
   }

   public boolean isUpdate() {
      return this.update;
   }

   public void setUpdate(boolean update) {
      this.update = update;
   }

   public class PublishArtifact implements Artifact, DynamicAttribute {
      private String ext;
      private String name;
      private String type;
      private Map extra = new HashMap();

      public String[] getConfigurations() {
         return null;
      }

      public String getExt() {
         return this.ext == null ? this.type : this.ext;
      }

      public ArtifactRevisionId getId() {
         return null;
      }

      public ModuleRevisionId getModuleRevisionId() {
         return null;
      }

      public String getName() {
         return this.name;
      }

      public Date getPublicationDate() {
         return null;
      }

      public String getType() {
         return this.type;
      }

      public URL getUrl() {
         return null;
      }

      public void setExt(String ext) {
         this.ext = ext;
      }

      public void setName(String name) {
         this.name = name;
      }

      public void setType(String type) {
         this.type = type;
      }

      public String getAttribute(String attName) {
         return (String)this.extra.get(attName);
      }

      public Map getAttributes() {
         return this.extra;
      }

      public String getExtraAttribute(String attName) {
         return (String)this.extra.get(attName);
      }

      public Map getExtraAttributes() {
         return this.extra;
      }

      public Map getQualifiedExtraAttributes() {
         return this.extra;
      }

      public boolean isMetadata() {
         return false;
      }

      public void setDynamicAttribute(String name, String value) {
         this.extra.put(name, value);
      }
   }

   public static class ArtifactsPattern {
      private String pattern;

      public String getPattern() {
         return this.pattern;
      }

      public void setPattern(String pattern) {
         this.pattern = pattern;
      }
   }
}
