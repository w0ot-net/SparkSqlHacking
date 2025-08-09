package org.apache.ivy.plugins.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.ivy.core.cache.DefaultResolutionCacheManager;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.report.ConfigurationResolveReport;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.util.CopyProgressListener;
import org.apache.ivy.util.FileUtil;
import org.apache.ivy.util.Message;

public class XmlReportOutputter implements ReportOutputter {
   private XmlReportWriter writer = new XmlReportWriter();

   public String getName() {
      return "xml";
   }

   public void output(ResolveReport report, ResolutionCacheManager cacheMgr, ResolveOptions options) throws IOException {
      String[] confs = report.getConfigurations();

      for(String conf : confs) {
         this.output(report.getConfigurationReport(conf), report.getResolveId(), confs, cacheMgr);
      }

   }

   public void output(ConfigurationResolveReport report, String resolveId, String[] confs, ResolutionCacheManager cacheMgr) throws IOException {
      File reportFile = cacheMgr.getConfigurationResolveReportInCache(resolveId, report.getConfiguration());
      if (cacheMgr instanceof DefaultResolutionCacheManager) {
         ((DefaultResolutionCacheManager)cacheMgr).assertInsideCache(reportFile);
      }

      File reportParentDir = reportFile.getParentFile();
      reportParentDir.mkdirs();
      OutputStream stream = new FileOutputStream(reportFile);
      this.writer.output(report, confs, stream);
      stream.close();
      Message.verbose("\treport for " + report.getModuleDescriptor().getModuleRevisionId() + " " + report.getConfiguration() + " produced in " + reportFile);
      File reportXsl = new File(reportParentDir, "ivy-report.xsl");
      File reportCss = new File(reportParentDir, "ivy-report.css");
      if (!reportXsl.exists()) {
         FileUtil.copy((InputStream)XmlReportOutputter.class.getResourceAsStream("ivy-report.xsl"), (File)reportXsl, (CopyProgressListener)null);
      }

      if (!reportCss.exists()) {
         FileUtil.copy((InputStream)XmlReportOutputter.class.getResourceAsStream("ivy-report.css"), (File)reportCss, (CopyProgressListener)null);
      }

   }
}
