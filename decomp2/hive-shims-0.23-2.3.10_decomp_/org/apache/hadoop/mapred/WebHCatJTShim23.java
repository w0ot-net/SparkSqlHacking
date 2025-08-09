package org.apache.hadoop.mapred;

import java.io.IOException;
import java.net.URI;
import java.security.PrivilegedExceptionAction;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.shims.HadoopShims;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.TypeConverter;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.yarn.api.ApplicationClientProtocol;
import org.apache.hadoop.yarn.api.protocolrecords.ApplicationsRequestScope;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationsRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetApplicationsResponse;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.client.ClientRMProxy;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebHCatJTShim23 implements HadoopShims.WebHCatJTShim {
   private static final Logger LOG = LoggerFactory.getLogger(WebHCatJTShim23.class);
   private JobClient jc;
   private final Configuration conf;

   public WebHCatJTShim23(final Configuration conf, UserGroupInformation ugi) throws IOException {
      try {
         this.conf = conf;
         this.jc = (JobClient)ugi.doAs(new PrivilegedExceptionAction() {
            public JobClient run() throws IOException, InterruptedException {
               return new JobClient(conf);
            }
         });
      } catch (InterruptedException ex) {
         throw new RuntimeException("Failed to create JobClient", ex);
      }
   }

   public JobProfile getJobProfile(JobID jobid) throws IOException {
      RunningJob rj = this.getJob(jobid);
      if (rj == null) {
         return null;
      } else {
         JobStatus jobStatus = rj.getJobStatus();
         return new JobProfile(jobStatus.getUsername(), jobStatus.getJobID(), jobStatus.getJobFile(), jobStatus.getTrackingUrl(), jobStatus.getJobName());
      }
   }

   public JobStatus getJobStatus(JobID jobid) throws IOException {
      RunningJob rj = this.getJob(jobid);
      return rj == null ? null : rj.getJobStatus();
   }

   public void killJob(JobID jobid) throws IOException {
      RunningJob rj = this.getJob(jobid);
      if (rj != null) {
         rj.killJob();
      }
   }

   public JobStatus[] getAllJobs() throws IOException {
      return this.jc.getAllJobs();
   }

   public void close() {
      try {
         this.jc.close();
      } catch (IOException var2) {
      }

   }

   public void addCacheFile(URI uri, Job job) {
      job.addCacheFile(uri);
   }

   private RunningJob getJob(JobID jobid) throws IOException {
      try {
         return this.jc.getJob(jobid);
      } catch (IOException ex) {
         String msg = ex.getMessage();
         if (msg == null || !msg.contains("ApplicationNotFoundException") && !Pattern.compile("History file.*not found").matcher(msg).find()) {
            throw ex;
         } else {
            LOG.info("Job(" + jobid + ") not found: " + msg);
            return null;
         }
      }
   }

   public void killJobs(String tag, long timestamp) {
      try {
         LOG.info("Looking for jobs to kill...");
         Set<ApplicationId> childJobs = this.getYarnChildJobs(tag, timestamp);
         if (childJobs.isEmpty()) {
            LOG.info("No jobs found from");
         } else {
            LOG.info(String.format("Found MR jobs count: %d", childJobs.size()));
            LOG.info("Killing all found jobs");
            YarnClient yarnClient = YarnClient.createYarnClient();
            yarnClient.init(this.conf);
            yarnClient.start();

            for(ApplicationId app : childJobs) {
               LOG.info(String.format("Killing job: %s ...", app));
               yarnClient.killApplication(app);
               LOG.info(String.format("Job %s killed", app));
            }

         }
      } catch (YarnException ye) {
         throw new RuntimeException("Exception occurred while killing child job(s)", ye);
      } catch (IOException ioe) {
         throw new RuntimeException("Exception occurred while killing child job(s)", ioe);
      }
   }

   public Set getJobs(String tag, long timestamp) {
      Set<ApplicationId> childYarnJobs = this.getYarnChildJobs(tag, timestamp);
      Set<String> childJobs = new HashSet();

      for(ApplicationId id : childYarnJobs) {
         String childJobId = TypeConverter.fromYarn(id).toString();
         childJobs.add(childJobId);
      }

      return childJobs;
   }

   private Set getYarnChildJobs(String tag, long timestamp) {
      Set<ApplicationId> childYarnJobs = new HashSet();
      LOG.info(String.format("Querying RM for tag = %s, starting with ts = %s", tag, timestamp));
      GetApplicationsRequest gar = GetApplicationsRequest.newInstance();
      gar.setScope(ApplicationsRequestScope.OWN);
      gar.setStartRange(timestamp, System.currentTimeMillis());
      gar.setApplicationTags(Collections.singleton(tag));

      try {
         ApplicationClientProtocol proxy = (ApplicationClientProtocol)ClientRMProxy.createRMProxy(this.conf, ApplicationClientProtocol.class);
         GetApplicationsResponse apps = proxy.getApplications(gar);

         for(ApplicationReport appReport : apps.getApplicationList()) {
            childYarnJobs.add(appReport.getApplicationId());
         }

         return childYarnJobs;
      } catch (IOException ioe) {
         throw new RuntimeException("Exception occurred while finding child jobs", ioe);
      } catch (YarnException ye) {
         throw new RuntimeException("Exception occurred while finding child jobs", ye);
      }
   }
}
