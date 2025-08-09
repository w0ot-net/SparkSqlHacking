package org.apache.avro.mapred.tether;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;

public class TetherJob extends Configured {
   public static final String TETHER_EXEC = "avro.tether.executable";
   public static final String TETHER_EXEC_ARGS = "avro.tether.executable_args";
   public static final String TETHER_EXEC_CACHED = "avro.tether.executable_cached";
   public static final String TETHER_PROTOCOL = "avro.tether.protocol";

   public static URI getExecutable(JobConf job) {
      try {
         return new URI(job.get("avro.tether.executable"));
      } catch (URISyntaxException e) {
         throw new RuntimeException(e);
      }
   }

   public static void setExecutable(JobConf job, File executable) {
      setExecutable(job, executable, Collections.emptyList(), false);
   }

   public static void setExecutable(JobConf job, File executable, List args, boolean cached) {
      job.set("avro.tether.executable", executable.toString());
      if (args != null) {
         StringBuilder sb = new StringBuilder();

         for(String a : args) {
            sb.append(a);
            sb.append('\n');
         }

         job.set("avro.tether.executable_args", sb.toString());
      }

      job.set("avro.tether.executable_cached", Boolean.valueOf(cached).toString());
   }

   public static TetheredProcess.Protocol getProtocol(JobConf job) {
      if (job.get("avro.tether.protocol") == null) {
         return TetheredProcess.Protocol.NONE;
      } else if (job.get("avro.tether.protocol").equals("http")) {
         return TetheredProcess.Protocol.HTTP;
      } else if (job.get("avro.tether.protocol").equals("sasl")) {
         return TetheredProcess.Protocol.SASL;
      } else {
         throw new RuntimeException("Unknown value for protocol: " + job.get("avro.tether.protocol"));
      }
   }

   public static RunningJob runJob(JobConf job) throws IOException {
      setupTetherJob(job);
      return JobClient.runJob(job);
   }

   public static RunningJob submitJob(JobConf conf) throws IOException {
      setupTetherJob(conf);
      return (new JobClient(conf)).submitJob(conf);
   }

   public static void setProtocol(JobConf job, String proto) throws IOException {
      proto = proto.trim().toLowerCase();
      if (!proto.equals("http") && !proto.equals("sasl")) {
         throw new IOException("protocol must be 'http' or 'sasl'");
      } else {
         job.set("avro.tether.protocol", proto);
      }
   }

   private static void setupTetherJob(JobConf job) throws IOException {
      job.setMapRunnerClass(TetherMapRunner.class);
      job.setPartitionerClass(TetherPartitioner.class);
      job.setReducerClass(TetherReducer.class);
      job.setInputFormat(TetherInputFormat.class);
      job.setOutputFormat(TetherOutputFormat.class);
      job.setOutputKeyClass(TetherData.class);
      job.setOutputKeyComparatorClass(TetherKeyComparator.class);
      job.setMapOutputValueClass(NullWritable.class);
      job.setMapOutputKeyClass(TetherData.class);
      if (job.getStrings("avro.tether.protocol") == null) {
         job.set("avro.tether.protocol", "sasl");
      }

      Collection<String> serializations = job.getStringCollection("io.serializations");
      if (!serializations.contains(TetherKeySerialization.class.getName())) {
         serializations.add(TetherKeySerialization.class.getName());
         job.setStrings("io.serializations", (String[])serializations.toArray(new String[0]));
      }

      if (job.getBoolean("avro.tether.executable_cached", false)) {
         DistributedCache.addCacheFile(getExecutable(job), job);
      }

   }
}
