package org.apache.ivy.ant;

import java.io.File;
import java.util.Date;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.deliver.DefaultPublishingDRResolver;
import org.apache.ivy.core.deliver.DeliverOptions;
import org.apache.ivy.core.deliver.PublishingDependencyRevisionResolver;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.module.status.StatusManager;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.util.DateUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.CallTarget;
import org.apache.tools.ant.taskdefs.Echo;
import org.apache.tools.ant.taskdefs.Input;
import org.apache.tools.ant.taskdefs.Property;

public class IvyDeliver extends IvyTask {
   private String organisation;
   private String module;
   private String revision;
   private String pubRevision;
   private String deliverpattern;
   private String status;
   private String pubdate;
   private String deliverTarget;
   private File deliveryList;
   private boolean replacedynamicrev = true;
   private boolean replaceForcedRev = false;
   private String resolveId;
   private String conf;
   private String pubBranch;
   private boolean generateRevConstraint = true;
   private boolean merge = true;

   public void setCache(File cache) {
      this.cacheAttributeNotSupported();
   }

   public String getDeliverpattern() {
      return this.deliverpattern;
   }

   public void setDeliverpattern(String destivypattern) {
      this.deliverpattern = destivypattern;
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

   public void setDelivertarget(String deliverTarget) {
      this.deliverTarget = deliverTarget;
   }

   public void setDeliveryList(File deliveryList) {
      this.deliveryList = deliveryList;
   }

   public boolean isReplacedynamicrev() {
      return this.replacedynamicrev;
   }

   public void setReplacedynamicrev(boolean replacedynamicrev) {
      this.replacedynamicrev = replacedynamicrev;
   }

   public boolean isReplaceForcedRev() {
      return this.replaceForcedRev;
   }

   public void setReplaceForcedRev(boolean replaceForcedRev) {
      this.replaceForcedRev = replaceForcedRev;
   }

   public String getResolveId() {
      return this.resolveId;
   }

   public void setResolveId(String resolveId) {
      this.resolveId = resolveId;
   }

   public String getConf() {
      return this.conf;
   }

   public void setConf(String confs) {
      this.conf = confs;
   }

   public boolean isGenerateRevConstraint() {
      return this.generateRevConstraint;
   }

   public void setGenerateRevConstraint(boolean generateRevConstraint) {
      this.generateRevConstraint = generateRevConstraint;
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
      this.organisation = this.getProperty(this.organisation, settings, "ivy.organisation", this.resolveId);
      this.module = this.getProperty(this.module, settings, "ivy.module", this.resolveId);
      this.revision = this.getProperty(this.revision, settings, "ivy.revision", this.resolveId);
      this.pubBranch = this.getProperty(this.pubBranch, settings, "ivy.deliver.branch");
      this.pubRevision = this.getProperty(this.pubRevision, settings, "ivy.deliver.revision");
      this.deliverpattern = this.getProperty(this.deliverpattern, settings, "ivy.deliver.ivy.pattern");
      this.status = this.getProperty(this.status, settings, "ivy.status");
      if (this.deliveryList == null) {
         String deliveryListPath = this.getProperty(settings, "ivy.delivery.list.file");
         if (deliveryListPath == null) {
            this.deliveryList = new File(System.getProperty("java.io.tmpdir") + "/delivery.properties");
         } else {
            this.deliveryList = this.getProject().resolveFile(settings.substitute(deliveryListPath));
         }
      }

      if (this.resolveId == null) {
         if (this.organisation == null) {
            throw new BuildException("no organisation provided for ivy deliver task: It can either be set explicitly via the attribute 'organisation' or via 'ivy.organisation' property or a prior call to <resolve/>");
         }

         if (this.module == null) {
            throw new BuildException("no module name provided for ivy deliver task: It can either be set explicitly via the attribute 'module' or via 'ivy.module' property or a prior call to <resolve/>");
         }
      }

      if (this.revision == null) {
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

      if (this.deliverpattern == null) {
         throw new BuildException("deliver ivy pattern is missing: either provide it as parameters or through ivy.deliver.ivy.pattern properties");
      } else if (this.status == null) {
         throw new BuildException("no status provided: either provide it as parameter or through the ivy.status.default property");
      } else {
         ModuleRevisionId mrid = null;
         if (this.resolveId == null) {
            mrid = ModuleRevisionId.newInstance(this.organisation, this.module, this.revision);
         }

         boolean isLeading = false;

         try {
            if (!this.deliveryList.exists()) {
               isLeading = true;
            }

            this.loadDeliveryList();
            PublishingDependencyRevisionResolver drResolver;
            if (StringUtils.isNullOrEmpty(this.deliverTarget)) {
               drResolver = new DefaultPublishingDRResolver();
            } else {
               drResolver = new DeliverDRResolver();
            }

            DeliverOptions options = (new DeliverOptions(this.status, pubdate, drResolver, this.doValidate(settings), this.replacedynamicrev, StringUtils.splitToArray(this.conf))).setResolveId(this.resolveId).setReplaceForcedRevisions(this.isReplaceForcedRev()).setGenerateRevConstraint(this.generateRevConstraint).setMerge(this.merge).setPubBranch(this.pubBranch);
            if (mrid == null) {
               ivy.deliver(this.pubRevision, this.deliverpattern, options);
            } else {
               ivy.deliver(mrid, this.pubRevision, this.deliverpattern, options);
            }
         } catch (Exception e) {
            throw new BuildException("impossible to deliver " + (mrid == null ? this.resolveId : mrid) + ": " + e, e);
         } finally {
            if (isLeading && this.deliveryList.exists()) {
               this.deliveryList.delete();
            }

         }

      }
   }

   private void loadDeliveryList() {
      Property property = (Property)this.getProject().createTask("property");
      property.setOwningTarget(this.getOwningTarget());
      property.init();
      property.setFile(this.deliveryList);
      property.perform();
   }

   private void appendDeliveryList(String msg) {
      Echo echo = (Echo)this.getProject().createTask("echo");
      echo.setOwningTarget(this.getOwningTarget());
      echo.init();
      echo.setFile(this.deliveryList);
      echo.setMessage(msg + "\n");
      echo.setAppend(true);
      echo.perform();
   }

   private final class DeliverDRResolver extends DefaultPublishingDRResolver {
      private DeliverDRResolver() {
      }

      public String resolve(ModuleDescriptor published, String publishedStatus, ModuleRevisionId depMrid, String depStatus) {
         if (StatusManager.getCurrent().isIntegration(publishedStatus)) {
            return super.resolve(published, publishedStatus, depMrid, depStatus);
         } else if (!StatusManager.getCurrent().isIntegration(depStatus)) {
            return super.resolve(published, publishedStatus, depMrid, depStatus);
         } else {
            String statusProperty = depMrid.getName() + "." + depMrid.getRevision() + ".status";
            String versionProperty = depMrid.getName() + "." + depMrid.getRevision() + ".version";
            String deliveredProperty = depMrid.getName() + "." + depMrid.getRevision() + ".delivered";
            String version = IvyDeliver.this.getProject().getProperty(versionProperty);
            String status = IvyDeliver.this.getProject().getProperty(statusProperty);
            String delivered = IvyDeliver.this.getProject().getProperty(deliveredProperty);
            Message.debug("found version = " + version + " status=" + status + " delivered=" + delivered);
            if (version != null && status != null) {
               if ("true".equals(delivered)) {
                  return version;
               } else {
                  this.deliverDependency(depMrid, version, status, depStatus);
                  IvyDeliver.this.loadDeliveryList();
                  return version;
               }
            } else {
               String globalStatusProperty = "recursive.delivery.status";
               String globalVersionProperty = "recursive.delivery.version";
               version = IvyDeliver.this.getProject().getProperty(globalVersionProperty);
               status = IvyDeliver.this.getProject().getProperty(globalStatusProperty);
               if (version != null && status != null) {
                  delivered = IvyDeliver.this.getProject().getProperty("recursive." + depMrid.getName() + ".delivered");
                  Message.debug("found global version = " + version + " and global status=" + status + " - delivered = " + delivered);
                  if ("true".equals(delivered)) {
                     return version;
                  } else {
                     IvyDeliver.this.getProject().setProperty(statusProperty, status);
                     this.deliverDependency(depMrid, version, status, depStatus);
                     IvyDeliver.this.loadDeliveryList();
                     return version;
                  }
               } else {
                  Input input = (Input)IvyDeliver.this.getProject().createTask("input");
                  input.setOwningTarget(IvyDeliver.this.getOwningTarget());
                  input.init();
                  input.setMessage(depMrid.getName() + " " + depMrid.getRevision() + ": please enter a status: ");
                  input.setValidargs(StatusManager.getCurrent().getDeliveryStatusListString());
                  input.setAddproperty(statusProperty);
                  input.perform();
                  status = IvyDeliver.this.getProject().getProperty(statusProperty);
                  IvyDeliver.this.appendDeliveryList(statusProperty + " = " + status);
                  input.setMessage(depMrid.getName() + " " + depMrid.getRevision() + ": please enter a version: ");
                  input.setValidargs((String)null);
                  input.setAddproperty(versionProperty);
                  input.perform();
                  version = IvyDeliver.this.getProject().getProperty(versionProperty);
                  IvyDeliver.this.appendDeliveryList(versionProperty + " = " + version);
                  this.deliverDependency(depMrid, version, status, depStatus);
                  IvyDeliver.this.loadDeliveryList();
                  return version;
               }
            }
         }
      }

      public void deliverDependency(ModuleRevisionId depMrid, String version, String status, String depStatus) {
         if (!StringUtils.isNullOrEmpty(IvyDeliver.this.deliverTarget)) {
            CallTarget ct = (CallTarget)IvyDeliver.this.getProject().createTask("antcall");
            ct.setOwningTarget(IvyDeliver.this.getOwningTarget());
            ct.init();
            ct.setTarget(IvyDeliver.this.deliverTarget);
            ct.setInheritAll(true);
            ct.setInheritRefs(true);
            Property param = ct.createParam();
            param.setName("dependency.name");
            param.setValue(depMrid.getName());
            param = ct.createParam();
            param.setName("dependency.published.status");
            param.setValue(status);
            param = ct.createParam();
            param.setName("dependency.published.version");
            param.setValue(version);
            param = ct.createParam();
            param.setName("dependency.version");
            param.setValue(depMrid.getRevision());
            param = ct.createParam();
            param.setName("dependency.status");
            param.setValue(depStatus == null ? "null" : depStatus);
            ct.perform();
            String deliveredProperty = depMrid.getName() + "." + depMrid.getRevision() + ".delivered";
            IvyDeliver.this.getProject().setProperty(deliveredProperty, "true");
            IvyDeliver.this.appendDeliveryList(deliveredProperty + " = true");
            IvyDeliver.this.getProject().setProperty("recursive." + depMrid.getName() + ".delivered", "true");
            IvyDeliver.this.appendDeliveryList("recursive." + depMrid.getName() + ".delivered = true");
         }
      }
   }
}
