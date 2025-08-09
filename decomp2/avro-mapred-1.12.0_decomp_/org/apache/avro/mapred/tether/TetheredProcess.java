package org.apache.avro.mapred.tether;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.avro.ipc.HttpTransceiver;
import org.apache.avro.ipc.SaslSocketServer;
import org.apache.avro.ipc.SaslSocketTransceiver;
import org.apache.avro.ipc.Server;
import org.apache.avro.ipc.Transceiver;
import org.apache.avro.ipc.jetty.HttpServer;
import org.apache.avro.ipc.specific.SpecificRequestor;
import org.apache.avro.ipc.specific.SpecificResponder;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TaskAttemptID;
import org.apache.hadoop.mapred.TaskLog;
import org.apache.hadoop.mapred.TaskLog.LogName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TetheredProcess {
   static final Logger LOG = LoggerFactory.getLogger(TetheredProcess.class);
   private JobConf job;
   TetherOutputService outputService;
   Server outputServer;
   Process subprocess;
   Transceiver clientTransceiver;
   InputProtocol inputClient;
   Protocol proto;

   public TetheredProcess(JobConf job, OutputCollector collector, Reporter reporter) throws Exception {
      try {
         this.outputService = new TetherOutputService(collector, reporter);
         this.proto = TetherJob.getProtocol(job);
         switch (this.proto.ordinal()) {
            case 0:
               InetSocketAddress iaddress = new InetSocketAddress(0);
               this.outputServer = new HttpServer(new SpecificResponder(OutputProtocol.class, this.outputService), iaddress.getPort());
               break;
            case 1:
               InetSocketAddress iaddress = new InetSocketAddress(0);
               this.outputServer = new SaslSocketServer(new SpecificResponder(OutputProtocol.class, this.outputService), iaddress);
               break;
            case 2:
            default:
               throw new RuntimeException("No transport protocol was specified in the job configuration");
         }

         this.outputServer.start();
         this.subprocess = this.startSubprocess(job);
         boolean hasexited = false;

         try {
            this.subprocess.exitValue();
            hasexited = true;
         } catch (IllegalThreadStateException var7) {
         }

         if (hasexited) {
            LOG.error("Could not start subprocess");
            throw new RuntimeException("Could not start subprocess");
         } else {
            switch (this.proto.ordinal()) {
               case 0:
                  this.clientTransceiver = new HttpTransceiver(new URL("http://127.0.0.1:" + this.outputService.inputPort()));
                  break;
               case 1:
                  this.clientTransceiver = new SaslSocketTransceiver(new InetSocketAddress(this.outputService.inputPort()));
                  break;
               default:
                  throw new RuntimeException("Error: code to handle this protocol is not implemented");
            }

            this.inputClient = (InputProtocol)SpecificRequestor.getClient(InputProtocol.class, this.clientTransceiver);
         }
      } catch (Exception t) {
         this.close();
         throw t;
      }
   }

   public void close() {
      if (this.clientTransceiver != null) {
         try {
            this.clientTransceiver.close();
         } catch (IOException var2) {
         }
      }

      if (this.subprocess != null) {
         this.subprocess.destroy();
      }

      if (this.outputServer != null) {
         this.outputServer.close();
      }

   }

   private Process startSubprocess(JobConf job) throws IOException, InterruptedException {
      List<String> command = new ArrayList();
      String executable = "";
      if (job.getBoolean("avro.tether.executable_cached", false)) {
         Path[] localFiles = DistributedCache.getLocalCacheFiles(job);
         if (localFiles == null) {
            URI[] files = DistributedCache.getCacheFiles(job);
            localFiles = new Path[]{new Path(files[0].toString())};
         }

         executable = localFiles[0].toString();
         FileUtil.chmod(executable.toString(), "a+x");
      } else {
         executable = job.get("avro.tether.executable");
      }

      command.add(executable);
      String args = job.get("avro.tether.executable_args");
      if (args != null) {
         String[] aparams = args.split("\n");

         for(int i = 0; i < aparams.length; ++i) {
            aparams[i] = aparams[i].trim();
            if (aparams[i].length() > 0) {
               command.add(aparams[i]);
            }
         }
      }

      if (System.getProperty("hadoop.log.dir") == null && System.getenv("HADOOP_LOG_DIR") != null) {
         System.setProperty("hadoop.log.dir", System.getenv("HADOOP_LOG_DIR"));
      }

      TaskAttemptID taskid = TaskAttemptID.forName(job.get("mapred.task.id"));
      File stdout = TaskLog.getTaskLogFile(taskid, false, LogName.STDOUT);
      File stderr = TaskLog.getTaskLogFile(taskid, false, LogName.STDERR);
      long logLength = TaskLog.getTaskLogLength(job);
      command = TaskLog.captureOutAndError((List)null, command, stdout, stderr, logLength, false);
      stdout.getParentFile().mkdirs();
      stderr.getParentFile().mkdirs();
      Map<String, String> env = new HashMap();
      env.put("AVRO_TETHER_OUTPUT_PORT", Integer.toString(this.outputServer.getPort()));
      env.put("AVRO_TETHER_PROTOCOL", job.get("avro.tether.protocol"));
      String imsg = "";

      for(String s : command) {
         imsg = s + " ";
      }

      LOG.info("TetheredProcess.startSubprocess: command: " + imsg);
      LOG.info("Tetheredprocess.startSubprocess: stdout logged to: " + stdout.toString());
      LOG.info("Tetheredprocess.startSubprocess: stderr logged to: " + stderr.toString());
      ProcessBuilder builder = new ProcessBuilder(command);
      builder.environment().putAll(env);
      return builder.start();
   }

   public static enum Protocol {
      HTTP,
      SASL,
      NONE;

      // $FF: synthetic method
      private static Protocol[] $values() {
         return new Protocol[]{HTTP, SASL, NONE};
      }
   }
}
