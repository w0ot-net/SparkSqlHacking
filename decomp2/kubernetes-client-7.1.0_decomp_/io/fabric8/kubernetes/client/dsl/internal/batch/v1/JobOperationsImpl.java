package io.fabric8.kubernetes.client.dsl.internal.batch.v1;

import io.fabric8.kubernetes.api.model.batch.v1.Job;
import io.fabric8.kubernetes.api.model.batch.v1.JobList;
import io.fabric8.kubernetes.client.Client;
import io.fabric8.kubernetes.client.dsl.BytesLimitTerminateTimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.LogWatch;
import io.fabric8.kubernetes.client.dsl.Loggable;
import io.fabric8.kubernetes.client.dsl.PodResource;
import io.fabric8.kubernetes.client.dsl.PrettyLoggable;
import io.fabric8.kubernetes.client.dsl.ScalableResource;
import io.fabric8.kubernetes.client.dsl.TailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.TimeTailPrettyLoggable;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperation;
import io.fabric8.kubernetes.client.dsl.internal.HasMetadataOperationsImpl;
import io.fabric8.kubernetes.client.dsl.internal.OperationContext;
import io.fabric8.kubernetes.client.dsl.internal.PodOperationContext;
import io.fabric8.kubernetes.client.utils.internal.PodOperationUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobOperationsImpl extends HasMetadataOperation implements ScalableResource {
   static final transient Logger LOG = LoggerFactory.getLogger(JobOperationsImpl.class);
   private final PodOperationContext podControllerOperationContext;

   public JobOperationsImpl(Client client) {
      this(new PodOperationContext(), HasMetadataOperationsImpl.defaultContext(client));
   }

   public JobOperationsImpl(PodOperationContext context, OperationContext superContext) {
      super(superContext.withApiGroupName("batch").withApiGroupVersion("v1").withPlural("jobs"), Job.class, JobList.class);
      this.podControllerOperationContext = context;
   }

   public JobOperationsImpl newInstance(OperationContext context) {
      return new JobOperationsImpl(this.podControllerOperationContext, context);
   }

   public Job scale(int count) {
      Job res = (Job)this.accept((b) -> b.getSpec().setParallelism(count));
      if (this.context.getTimeout() > 0L) {
         this.waitUntilJobIsScaled();
         res = (Job)this.get();
      }

      return res;
   }

   private void waitUntilJobIsScaled() {
      AtomicReference<Job> atomicJob = new AtomicReference();
      this.waitUntilCondition((job) -> {
         atomicJob.set(job);
         Integer activeJobs = job.getStatus().getActive();
         if (activeJobs == null) {
            activeJobs = 0;
         }

         if (Objects.equals(job.getSpec().getParallelism(), activeJobs)) {
            return true;
         } else {
            LOG.debug("Only {}/{} pods scheduled for Job: {} in namespace: {} so waiting...", new Object[]{job.getStatus().getActive(), job.getSpec().getParallelism(), job.getMetadata().getName(), this.namespace});
            return false;
         }
      }, this.context.getTimeout(), this.context.getTimeoutUnit());
   }

   public String getLog() {
      return this.getLog(this.podControllerOperationContext.isPrettyOutput());
   }

   public String getLog(boolean isPretty) {
      StringBuilder stringBuilder = new StringBuilder();

      for(PodResource podOperation : this.doGetLog()) {
         stringBuilder.append(podOperation.getLog(isPretty));
      }

      return stringBuilder.toString();
   }

   private List doGetLog() {
      Job job = (Job)this.requireFromServer();
      return PodOperationUtil.getPodOperationsForController(this.context, this.podControllerOperationContext, job.getMetadata().getUid(), getJobPodLabels(job));
   }

   public Reader getLogReader() {
      return PodOperationUtil.getLogReader(this.doGetLog());
   }

   public InputStream getLogInputStream() {
      return PodOperationUtil.getLogInputStream(this.doGetLog());
   }

   public LogWatch watchLog() {
      return this.watchLog((OutputStream)null);
   }

   public LogWatch watchLog(OutputStream out) {
      return PodOperationUtil.watchLog(this.doGetLog(), out);
   }

   public Loggable withLogWaitTimeout(Integer logWaitTimeout) {
      return this.withReadyWaitTimeout(logWaitTimeout);
   }

   public Loggable withReadyWaitTimeout(Integer timeout) {
      return new JobOperationsImpl(this.podControllerOperationContext.withReadyWaitTimeout(timeout), this.context);
   }

   protected Job modifyItemForReplaceOrPatch(Supplier current, Job job) {
      Job jobFromServer = (Job)current.get();
      if (job.getSpec().getSelector() == null) {
         job.getSpec().setSelector(jobFromServer.getSpec().getSelector());
      }

      if (job.getSpec().getTemplate().getMetadata() != null) {
         job.getSpec().getTemplate().getMetadata().setLabels(jobFromServer.getSpec().getTemplate().getMetadata().getLabels());
      } else {
         job.getSpec().getTemplate().setMetadata(jobFromServer.getSpec().getTemplate().getMetadata());
      }

      return job;
   }

   static Map getJobPodLabels(Job job) {
      Map<String, String> labels = new HashMap();
      if (job != null && job.getMetadata() != null && job.getMetadata().getUid() != null) {
         labels.put("controller-uid", job.getMetadata().getUid());
      }

      return labels;
   }

   public Loggable inContainer(String id) {
      return new JobOperationsImpl(this.podControllerOperationContext.withContainerId(id), this.context);
   }

   public TimeTailPrettyLoggable limitBytes(int limitBytes) {
      return new JobOperationsImpl(this.podControllerOperationContext.withLimitBytes(limitBytes), this.context);
   }

   public TimeTailPrettyLoggable terminated() {
      return new JobOperationsImpl(this.podControllerOperationContext.withTerminatedStatus(true), this.context);
   }

   public Loggable withPrettyOutput() {
      return new JobOperationsImpl(this.podControllerOperationContext.withPrettyOutput(true), this.context);
   }

   public PrettyLoggable tailingLines(int lines) {
      return new JobOperationsImpl(this.podControllerOperationContext.withTailingLines(lines), this.context);
   }

   public TailPrettyLoggable sinceTime(String timestamp) {
      return new JobOperationsImpl(this.podControllerOperationContext.withSinceTimestamp(timestamp), this.context);
   }

   public TailPrettyLoggable sinceSeconds(int seconds) {
      return new JobOperationsImpl(this.podControllerOperationContext.withSinceSeconds(seconds), this.context);
   }

   public BytesLimitTerminateTimeTailPrettyLoggable usingTimestamps() {
      return new JobOperationsImpl(this.podControllerOperationContext.withTimestamps(true), this.context);
   }
}
