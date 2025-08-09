package org.apache.ivy.plugins.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.ivy.core.IvyContext;
import org.apache.ivy.core.cache.ResolutionCacheManager;
import org.apache.ivy.core.report.ArtifactDownloadReport;
import org.apache.ivy.core.report.ConfigurationResolveReport;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.resolve.IvyNode;
import org.apache.ivy.core.resolve.IvyNodeEviction;
import org.apache.ivy.core.resolve.ResolveOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.apache.ivy.util.Message;

public class LogReportOutputter implements ReportOutputter {
   public String getName() {
      return "console";
   }

   public void output(ResolveReport report, ResolutionCacheManager cacheMgr, ResolveOptions options) throws IOException {
      IvySettings settings = IvyContext.getContext().getSettings();
      if (settings.logModulesInUse() && "default".equals(options.getLog())) {
         Message.info("\t:: modules in use:");
         List<IvyNode> dependencies = new ArrayList(report.getDependencies());
         Collections.sort(dependencies);
         if (dependencies.size() > 0) {
            String[] confs = report.getConfigurations();

            for(IvyNode node : dependencies) {
               if (!node.isCompletelyEvicted() && !node.hasProblem()) {
                  List<String> nodeConfs = new ArrayList(confs.length);

                  for(String conf : confs) {
                     if (report.getConfigurationReport(conf).getModuleRevisionIds().contains(node.getResolvedId())) {
                        nodeConfs.add(conf);
                     }
                  }

                  Message.info("\t" + node + " from " + node.getModuleRevision().getResolver().getName() + " in " + nodeConfs);
               }
            }
         }
      }

      IvyNode[] evicted = report.getEvictedNodes();
      if (evicted.length > 0 && "default".equals(options.getLog())) {
         Message.info("\t:: evicted modules:");

         for(IvyNode evictedNode : evicted) {
            Collection<String> allEvictingNodes = evictedNode.getAllEvictingNodesDetails();
            if (allEvictingNodes == null) {
               Message.info("\t" + evictedNode + " transitively in " + Arrays.asList(evictedNode.getEvictedConfs()));
            } else if (allEvictingNodes.isEmpty()) {
               Message.info("\t" + evictedNode + " by [] (" + evictedNode.getAllEvictingConflictManagers() + ") in " + Arrays.asList(evictedNode.getEvictedConfs()));
            } else {
               Message.info("\t" + evictedNode + " by " + allEvictingNodes + " in " + Arrays.asList(evictedNode.getEvictedConfs()));
            }

            for(String conf : evictedNode.getEvictedConfs()) {
               IvyNodeEviction.EvictionData evictedData = evictedNode.getEvictedData(conf);
               if (evictedData.getParent() != null) {
                  Message.verbose("\t  in " + evictedData.getParent() + " with " + evictedData.getConflictManager());
               }
            }
         }
      }

      if ("default".equals(options.getLog())) {
         char[] sep = new char[69];
         Arrays.fill(sep, '-');
         Message.rawinfo("\t" + new String(sep));
         StringBuilder line = new StringBuilder("\t");
         this.append(line, "", 18);
         this.append(line, "modules", 31);
         line.append("|");
         this.append(line, "artifacts", 15);
         line.append("|");
         Message.rawinfo(line.toString());
         line = new StringBuilder("\t");
         this.append(line, "conf", 18);
         this.append(line, "number", 7);
         this.append(line, "search", 7);
         this.append(line, "dwnlded", 7);
         this.append(line, "evicted", 7);
         line.append("|");
         this.append(line, "number", 7);
         this.append(line, "dwnlded", 7);
         line.append("|");
         Message.rawinfo(line.toString());
         Message.rawinfo("\t" + new String(sep));

         for(String conf : report.getConfigurations()) {
            this.output(report.getConfigurationReport(conf));
         }

         Message.rawinfo("\t" + new String(sep));
      }

      IvyNode[] unresolved = report.getUnresolvedDependencies();
      if (unresolved.length > 0) {
         Message.warn("\t::::::::::::::::::::::::::::::::::::::::::::::");
         Message.warn("\t::          UNRESOLVED DEPENDENCIES         ::");
         Message.warn("\t::::::::::::::::::::::::::::::::::::::::::::::");
      }

      for(IvyNode anUnresolved : unresolved) {
         Message.warn("\t:: " + anUnresolved + ": " + anUnresolved.getProblemMessage());
      }

      if (unresolved.length > 0) {
         Message.warn("\t::::::::::::::::::::::::::::::::::::::::::::::\n");
      }

      ArtifactDownloadReport[] errors = report.getFailedArtifactsReports();
      if (errors.length > 0) {
         Message.warn("\t::::::::::::::::::::::::::::::::::::::::::::::");
         Message.warn("\t::              FAILED DOWNLOADS            ::");
         Message.warn("\t:: ^ see resolution messages for details  ^ ::");
         Message.warn("\t::::::::::::::::::::::::::::::::::::::::::::::");
      }

      for(ArtifactDownloadReport error : errors) {
         Message.warn("\t:: " + error.getArtifact());
      }

      if (errors.length > 0) {
         Message.warn("\t::::::::::::::::::::::::::::::::::::::::::::::\n");
      }

   }

   public void output(ConfigurationResolveReport report) {
      StringBuilder line = new StringBuilder("\t");
      this.append(line, report.getConfiguration(), 18);
      this.append(line, String.valueOf(report.getNodesNumber()), 7);
      this.append(line, String.valueOf(report.getSearchedNodes().length), 7);
      this.append(line, String.valueOf(report.getDownloadedNodes().length), 7);
      this.append(line, String.valueOf(report.getEvictedNodes().length), 7);
      line.append("|");
      this.append(line, String.valueOf(report.getArtifactsNumber()), 7);
      this.append(line, String.valueOf(report.getDownloadedArtifactsReports().length), 7);
      line.append("|");
      Message.rawinfo(line.toString());
   }

   private void append(StringBuilder line, Object o, int limit) {
      String v = String.valueOf(o);
      if (v.length() >= limit) {
         v = v.substring(0, limit);
      } else {
         int missing = limit - v.length();
         int half = missing / 2;
         char[] c = new char[limit];
         Arrays.fill(c, ' ');
         System.arraycopy(v.toCharArray(), 0, c, missing - half, v.length());
         v = new String(c);
      }

      line.append("|");
      line.append(v);
   }
}
