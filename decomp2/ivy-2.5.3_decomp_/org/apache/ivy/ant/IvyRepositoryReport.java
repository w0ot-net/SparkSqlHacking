package org.apache.ivy.ant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.ivy.Ivy;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.descriptor.DefaultModuleDescriptor;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleId;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.plugins.report.XmlReportOutputter;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.XSLTProcess;

public class IvyRepositoryReport extends IvyTask {
   private String organisation = "*";
   private String module;
   private String branch;
   private String revision = "latest.integration";
   private String matcher = "exactOrRegexp";
   private File todir;
   private boolean graph = false;
   private boolean dot = false;
   private boolean xml = true;
   private boolean xsl = false;
   private String xslFile;
   private String outputname = "ivy-repository-report";
   private String xslext = "html";
   private final List params = new ArrayList();

   public void doExecute() throws BuildException {
      Ivy ivy = this.getIvyInstance();
      IvySettings settings = ivy.getSettings();
      if (this.xsl && this.xslFile == null) {
         throw new BuildException("xsl file is mandatory when using xsl generation");
      } else if (this.module == null && "exact".equals(this.matcher)) {
         throw new BuildException("no module name provided for ivy repository graph task: It can either be set explicitly via the attribute 'module' or via 'ivy.module' property or a prior call to <resolve/>");
      } else {
         if (this.module == null && !"exact".equals(this.matcher)) {
            this.module = "*";
         }

         ModuleRevisionId moduleRevisionId = ModuleRevisionId.newInstance(this.organisation, this.module, this.revision);

         try {
            ModuleRevisionId criteria = this.revision != null && !settings.getVersionMatcher().isDynamic(moduleRevisionId) ? new ModuleRevisionId(new ModuleId(this.organisation, this.module), this.branch, this.revision) : new ModuleRevisionId(new ModuleId(this.organisation, this.module), this.branch, "*");
            ModuleRevisionId[] mrids = ivy.listModules(criteria, settings.getMatcher(this.matcher));
            Set<ModuleRevisionId> modules = new HashSet();

            for(ModuleRevisionId mrid : mrids) {
               modules.add(ModuleRevisionId.newInstance(mrid, this.revision));
            }

            mrids = (ModuleRevisionId[])modules.toArray(new ModuleRevisionId[modules.size()]);
            ModuleDescriptor md = DefaultModuleDescriptor.newCallerInstance(mrids, true, false);
            String resolveId = ResolveOptions.getDefaultResolveId(md);
            ResolveReport report = ivy.resolve(md, (new ResolveOptions()).setResolveId(resolveId).setValidate(this.doValidate(settings)));
            ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();
            (new XmlReportOutputter()).output(report, cacheMgr, new ResolveOptions());
            if (this.graph) {
               this.gengraph(cacheMgr, md.getModuleRevisionId().getOrganisation(), md.getModuleRevisionId().getName());
            }

            if (this.dot) {
               this.gendot(cacheMgr, md.getModuleRevisionId().getOrganisation(), md.getModuleRevisionId().getName());
            }

            if (this.xml) {
               FileUtil.copy((File)cacheMgr.getConfigurationResolveReportInCache(resolveId, "default"), (File)(new File(this.getTodir(), this.outputname + ".xml")), (CopyProgressListener)null);
            }

            if (this.xsl) {
               this.genreport(cacheMgr, md.getModuleRevisionId().getOrganisation(), md.getModuleRevisionId().getName());
            }

         } catch (Exception e) {
            throw new BuildException("impossible to generate graph for " + moduleRevisionId + ": " + e, e);
         }
      }
   }

   private void genreport(ResolutionCacheManager cache, String organisation, String module) {
      XSLTProcess xslt = new XSLTProcess();
      xslt.setTaskName(this.getTaskName());
      xslt.setProject(this.getProject());
      xslt.init();
      String resolveId = ResolveOptions.getDefaultResolveId(new ModuleId(organisation, module));
      xslt.setIn(cache.getConfigurationResolveReportInCache(resolveId, "default"));
      xslt.setOut(new File(this.getTodir(), this.outputname + "." + this.xslext));
      xslt.setStyle(this.xslFile);
      XSLTProcess.Param xslExt = xslt.createParam();
      xslExt.setName("extension");
      xslExt.setExpression(this.xslext);

      for(XSLTProcess.Param param : this.params) {
         XSLTProcess.Param realParam = xslt.createParam();
         realParam.setName(param.getName());
         realParam.setExpression(param.getExpression());
      }

      xslt.execute();
   }

   private void gengraph(ResolutionCacheManager cache, String organisation, String module) throws IOException {
      this.gen(cache, organisation, module, this.getGraphStylePath(cache.getResolutionCacheRoot()), "graphml");
   }

   private String getGraphStylePath(File cache) throws IOException {
      File style = new File(cache, "ivy-report-graph-all.xsl");
      FileUtil.copy((InputStream)XmlReportOutputter.class.getResourceAsStream("ivy-report-graph-all.xsl"), (File)style, (CopyProgressListener)null);
      return style.getAbsolutePath();
   }

   private void gendot(ResolutionCacheManager cache, String organisation, String module) throws IOException {
      this.gen(cache, organisation, module, this.getDotStylePath(cache.getResolutionCacheRoot()), "dot");
   }

   private String getDotStylePath(File cache) throws IOException {
      File style = new File(cache, "ivy-report-dot-all.xsl");
      FileUtil.copy((InputStream)XmlReportOutputter.class.getResourceAsStream("ivy-report-dot-all.xsl"), (File)style, (CopyProgressListener)null);
      return style.getAbsolutePath();
   }

   private void gen(ResolutionCacheManager cache, String organisation, String module, String style, String ext) {
      XSLTProcess xslt = new XSLTProcess();
      xslt.setTaskName(this.getTaskName());
      xslt.setProject(this.getProject());
      xslt.init();
      String resolveId = ResolveOptions.getDefaultResolveId(new ModuleId(organisation, module));
      xslt.setIn(cache.getConfigurationResolveReportInCache(resolveId, "default"));
      xslt.setOut(new File(this.getTodir(), this.outputname + "." + ext));
      xslt.setBasedir(cache.getResolutionCacheRoot());
      xslt.setStyle(style);
      xslt.execute();
   }

   public File getTodir() {
      return this.todir == null && this.getProject() != null ? this.getProject().getBaseDir() : this.todir;
   }

   public void setTodir(File todir) {
      this.todir = todir;
   }

   public boolean isGraph() {
      return this.graph;
   }

   public void setGraph(boolean graph) {
      this.graph = graph;
   }

   public String getXslfile() {
      return this.xslFile;
   }

   public void setXslfile(String xslFile) {
      this.xslFile = xslFile;
   }

   public boolean isXml() {
      return this.xml;
   }

   public void setXml(boolean xml) {
      this.xml = xml;
   }

   public boolean isXsl() {
      return this.xsl;
   }

   public void setXsl(boolean xsl) {
      this.xsl = xsl;
   }

   public String getXslext() {
      return this.xslext;
   }

   public void setXslext(String xslext) {
      this.xslext = xslext;
   }

   public XSLTProcess.Param createParam() {
      XSLTProcess.Param result = new XSLTProcess.Param();
      this.params.add(result);
      return result;
   }

   public String getOutputname() {
      return this.outputname;
   }

   public void setOutputname(String outputpattern) {
      this.outputname = outputpattern;
   }

   public void setCache(File cache) {
      this.cacheAttributeNotSupported();
   }

   public String getMatcher() {
      return this.matcher;
   }

   public void setMatcher(String matcher) {
      this.matcher = matcher;
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

   public String getRevision() {
      return this.revision;
   }

   public void setRevision(String revision) {
      this.revision = revision;
   }

   public String getBranch() {
      return this.branch;
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public boolean isDot() {
      return this.dot;
   }

   public void setDot(boolean dot) {
      this.dot = dot;
   }
}
