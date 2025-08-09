package org.apache.ivy.ant;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ConfigurationResolveReport;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.plugins.report.XmlReportParser;
import org.apache.ivy.util.Message;
import org.apache.ivy.util.StringUtils;
import org.apache.tools.ant.BuildException;

public abstract class IvyCacheTask extends IvyPostResolveTask {
   protected List getArtifactReports() throws BuildException, ParseException {
      List<ArtifactDownloadReport> ret = new ArrayList();

      for(ArtifactDownloadReport artifactReport : this.getAllArtifactReports()) {
         if (this.getArtifactFilter().accept(artifactReport.getArtifact())) {
            ret.add(artifactReport);
         }
      }

      return ret;
   }

   private Collection getAllArtifactReports() throws ParseException {
      String[] confs = StringUtils.splitToArray(this.getConf());
      Collection<ArtifactDownloadReport> all = new LinkedHashSet();
      ResolveReport report = this.getResolvedReport();
      if (report != null) {
         Message.debug("using internal report instance to get artifacts list");

         for(String conf : confs) {
            ConfigurationResolveReport configurationReport = report.getConfigurationReport(conf);
            if (configurationReport == null) {
               throw new BuildException("bad confs provided: " + conf + " not found among " + Arrays.asList(report.getConfigurations()));
            }

            for(ModuleRevisionId revId : configurationReport.getModuleRevisionIds()) {
               all.addAll(Arrays.asList(configurationReport.getDownloadReports(revId)));
            }
         }
      } else {
         Message.debug("using stored report to get artifacts list");
         XmlReportParser parser = new XmlReportParser();
         ResolutionCacheManager cacheMgr = this.getIvyInstance().getResolutionCacheManager();
         String resolvedId = this.getResolveId();
         if (resolvedId == null) {
            resolvedId = ResolveOptions.getDefaultResolveId(this.getResolvedModuleId());
         }

         for(String conf : confs) {
            File reportFile = cacheMgr.getConfigurationResolveReportInCache(resolvedId, conf);
            parser.parse(reportFile);
            all.addAll(Arrays.asList(parser.getArtifactReports()));
         }
      }

      return all;
   }
}
