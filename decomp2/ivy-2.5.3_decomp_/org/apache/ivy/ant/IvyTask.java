package org.apache.ivy.ant;

import java.util.Date;
import java.util.Locale;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.util.DateUtil;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Reference;

public abstract class IvyTask extends Task {
   public static final String ANT_PROJECT_CONTEXT_KEY = "ant-project";
   private Boolean validate = null;
   private Reference antIvyEngineRef = null;

   protected boolean doValidate(IvySettings ivy) {
      return this.validate == null ? ivy.doValidate() : this.validate;
   }

   public boolean isValidate() {
      return this.validate == null || this.validate;
   }

   public void setValidate(boolean validate) {
      this.validate = validate;
   }

   public void setSettingsRef(Reference ref) {
      this.antIvyEngineRef = ref;
   }

   public Reference getSettingsRef() {
      return this.antIvyEngineRef;
   }

   protected IvySettings getSettings() {
      return this.getIvyInstance().getSettings();
   }

   protected Ivy getIvyInstance() {
      Object antIvyEngine;
      if (this.antIvyEngineRef == null) {
         antIvyEngine = IvyAntSettings.getDefaultInstance((Task)this);
      } else {
         antIvyEngine = this.antIvyEngineRef.getReferencedObject(this.getProject());
         if (!antIvyEngine.getClass().getName().equals(IvyAntSettings.class.getName())) {
            throw new BuildException(this.antIvyEngineRef.getRefId() + " doesn't reference an ivy:settings", this.getLocation());
         }

         if (!(antIvyEngine instanceof IvyAntSettings)) {
            throw new BuildException(this.antIvyEngineRef.getRefId() + " has been defined in a different classloader.  Please use the same loader when defining your task, or redeclare your ivy:settings in this classloader", this.getLocation());
         }
      }

      Ivy ivy = ((IvyAntSettings)antIvyEngine).getConfiguredIvyInstance((Task)this);
      AntMessageLogger.register(this, ivy);
      return ivy;
   }

   protected void setResolved(ResolveReport report, boolean keep) {
      ModuleDescriptor md = report.getModuleDescriptor();
      String[] confs = report.getConfigurations();
      if (keep) {
         this.getProject().addReference("ivy.resolved.report", report);
         this.getProject().addReference("ivy.resolved.configurations.ref", confs);
         this.getProject().addReference("ivy.resolved.descriptor", md);
      }

      String suffix = md.getModuleRevisionId().getModuleId().getOrganisation() + "." + md.getModuleRevisionId().getModuleId().getName();
      this.getProject().addReference("ivy.resolved.report." + suffix, report);
      this.getProject().addReference("ivy.resolved.descriptor." + suffix, md);
      this.getProject().addReference("ivy.resolved.configurations.ref." + suffix, confs);
   }

   protected void setResolved(ResolveReport report, String resolveId, boolean keep) {
      this.setResolved(report, keep);
      if (resolveId != null) {
         ModuleDescriptor md = report.getModuleDescriptor();
         String[] confs = report.getConfigurations();
         this.getProject().addReference("ivy.resolved.report." + resolveId, report);
         this.getProject().addReference("ivy.resolved.descriptor." + resolveId, md);
         this.getProject().addReference("ivy.resolved.configurations.ref." + resolveId, confs);
      }
   }

   protected String[] getResolvedConfigurations(String org, String module, boolean strict) {
      return (String[])this.getReference("ivy.resolved.configurations.ref", org, module, strict);
   }

   protected Object getResolvedDescriptor(String resolveId) {
      return this.getResolvedDescriptor(resolveId, true);
   }

   protected Object getResolvedDescriptor(String resolveId, boolean strict) {
      T result = (T)this.getProject().getReference("ivy.resolved.descriptor." + resolveId);
      if (strict && result == null) {
         throw new BuildException("ModuleDescriptor for resolve with id '" + resolveId + "' not found.");
      } else {
         return result;
      }
   }

   protected Object getResolvedDescriptor(String org, String module) {
      return this.getResolvedDescriptor(org, module, false);
   }

   protected Object getResolvedDescriptor(String org, String module, boolean strict) {
      return this.getReference("ivy.resolved.descriptor", org, module, strict);
   }

   private Object getReference(String prefix, String org, String module, boolean strict) {
      T reference = (T)null;
      if (org != null && module != null) {
         reference = (T)this.getProject().getReference(prefix + "." + org + "." + module);
      }

      if (!strict && reference == null) {
         reference = (T)this.getProject().getReference(prefix);
      }

      return reference;
   }

   protected ResolveReport getResolvedReport(String org, String module, String resolveId) {
      return resolveId == null ? (ResolveReport)this.getReference("ivy.resolved.report", org, module, false) : (ResolveReport)this.getReference("ivy.resolved.report." + resolveId, (String)null, (String)null, false);
   }

   protected String[] splitConfs(String conf) {
      return StringUtils.splitToArray(conf);
   }

   protected String mergeConfs(String[] conf) {
      return StringUtils.joinArray(conf, ", ");
   }

   protected static Date getPubDate(String date, Date def) {
      if (date == null) {
         return def;
      } else if ("now".equals(date.toLowerCase(Locale.US))) {
         return new Date();
      } else {
         try {
            return DateUtil.parse(date);
         } catch (Exception var3) {
            throw new BuildException("Publication date provided in bad format. Should be 'yyyyMMddHHmmss' and not '" + date + "'!");
         }
      }
   }

   protected String getProperty(String value, IvySettings ivy, String name) {
      if (value == null) {
         return this.getProperty(ivy, name);
      } else {
         value = ivy.substitute(value);
         Message.debug("parameter found as attribute value: " + name + "=" + value);
         return value;
      }
   }

   protected String getProperty(String value, IvySettings ivy, String name, String resolveId) {
      return resolveId == null ? this.getProperty(value, ivy, name) : this.getProperty(value, ivy, name + "." + resolveId);
   }

   protected String getProperty(IvySettings ivy, String name, String resolveId) {
      return resolveId == null ? this.getProperty(ivy, name) : this.getProperty(ivy, name + "." + resolveId);
   }

   protected String getProperty(IvySettings ivy, String name) {
      String val = ivy.getVariable(name);
      if (val == null) {
         val = ivy.substitute(this.getProject().getProperty(name));
         if (val == null) {
            Message.debug("parameter not found: " + name);
         } else {
            Message.debug("parameter found as ant project property: " + name + "=" + val);
         }
      } else {
         val = ivy.substitute(val);
         Message.debug("parameter found as ivy variable: " + name + "=" + val);
      }

      return val;
   }

   protected void prepareTask() {
      this.getProject().setProperty("ivy.version", Ivy.getIvyVersion());
      IvyContext.pushNewCopyContext();
      IvyContext.getContext().setIvy(this.getIvyInstance());
      IvyContext.getContext().push("ant-project", this.getProject());
   }

   protected void finalizeTask() {
      if (!IvyContext.getContext().pop("ant-project", this.getProject())) {
         Message.error("ANT project popped from stack not equals current! Ignoring");
      }

      IvyContext.popContext();
   }

   public final void execute() throws BuildException {
      try {
         this.prepareTask();
         this.doExecute();
      } finally {
         this.finalizeTask();
      }

   }

   public abstract void doExecute() throws BuildException;

   public String toString() {
      return this.getClass().getName() + ":" + this.getTaskName();
   }

   protected void cacheAttributeNotSupported() {
      throw new BuildException("cache attribute is not supported any more. See IVY-685 for details.");
   }
}
