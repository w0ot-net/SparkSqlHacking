package org.apache.avro.mapreduce;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.TaskInputOutputContext;
import org.apache.hadoop.util.ReflectionUtils;

public class AvroMultipleOutputs {
   private static final String MULTIPLE_OUTPUTS = "avro.mapreduce.multipleoutputs";
   private static final String MO_PREFIX = "avro.mapreduce.multipleoutputs.namedOutput.";
   private static final String FORMAT = ".format";
   private static final String COUNTERS_ENABLED = "avro.mapreduce.multipleoutputs.counters";
   private static final String COUNTERS_GROUP = AvroMultipleOutputs.class.getName();
   private Map taskContexts = new HashMap();
   private TaskInputOutputContext context;
   private Set namedOutputs;
   private Map recordWriters;
   private boolean countersEnabled;

   private static void checkTokenName(String namedOutput) {
      if (namedOutput != null && namedOutput.length() != 0) {
         for(char ch : namedOutput.toCharArray()) {
            if ((ch < 'A' || ch > 'Z') && (ch < 'a' || ch > 'z') && (ch < '0' || ch > '9')) {
               throw new IllegalArgumentException("Name cannot have a '" + ch + "' char");
            }
         }

      } else {
         throw new IllegalArgumentException("Name cannot be NULL or empty");
      }
   }

   private static void checkBaseOutputPath(String outputPath) {
      if (outputPath.equals("part")) {
         throw new IllegalArgumentException("output name cannot be 'part'");
      }
   }

   private static void checkNamedOutputName(JobContext job, String namedOutput, boolean alreadyDefined) {
      checkTokenName(namedOutput);
      checkBaseOutputPath(namedOutput);
      List<String> definedChannels = getNamedOutputsList(job);
      if (alreadyDefined && definedChannels.contains(namedOutput)) {
         throw new IllegalArgumentException("Named output '" + namedOutput + "' already alreadyDefined");
      } else if (!alreadyDefined && !definedChannels.contains(namedOutput)) {
         throw new IllegalArgumentException("Named output '" + namedOutput + "' not defined");
      }
   }

   private static List getNamedOutputsList(JobContext job) {
      List<String> names = new ArrayList();
      StringTokenizer st = new StringTokenizer(job.getConfiguration().get("avro.mapreduce.multipleoutputs", ""), " ");

      while(st.hasMoreTokens()) {
         names.add(st.nextToken());
      }

      return names;
   }

   private static Class getNamedOutputFormatClass(JobContext job, String namedOutput) {
      return job.getConfiguration().getClass("avro.mapreduce.multipleoutputs.namedOutput." + namedOutput + ".format", (Class)null, OutputFormat.class);
   }

   public static void addNamedOutput(Job job, String namedOutput, Class outputFormatClass, Schema keySchema) {
      addNamedOutput(job, namedOutput, outputFormatClass, keySchema, (Schema)null);
   }

   public static void addNamedOutput(Job job, String namedOutput, Class outputFormatClass, Schema keySchema, Schema valueSchema) {
      checkNamedOutputName(job, namedOutput, true);
      Configuration conf = job.getConfiguration();
      conf.set("avro.mapreduce.multipleoutputs", conf.get("avro.mapreduce.multipleoutputs", "") + " " + namedOutput);
      conf.setClass("avro.mapreduce.multipleoutputs.namedOutput." + namedOutput + ".format", outputFormatClass, OutputFormat.class);
      conf.set("avro.mapreduce.multipleoutputs.namedOutput." + namedOutput + ".keyschema", keySchema.toString());
      if (valueSchema != null) {
         conf.set("avro.mapreduce.multipleoutputs.namedOutput." + namedOutput + ".valueschema", valueSchema.toString());
      }

   }

   public static void setCountersEnabled(Job job, boolean enabled) {
      job.getConfiguration().setBoolean("avro.mapreduce.multipleoutputs.counters", enabled);
   }

   public static boolean getCountersEnabled(JobContext job) {
      return job.getConfiguration().getBoolean("avro.mapreduce.multipleoutputs.counters", false);
   }

   public AvroMultipleOutputs(TaskInputOutputContext context) {
      this.context = context;
      this.namedOutputs = Collections.unmodifiableSet(new HashSet(getNamedOutputsList(context)));
      this.recordWriters = new HashMap();
      this.countersEnabled = getCountersEnabled(context);
   }

   public void write(String namedOutput, Object key) throws IOException, InterruptedException {
      this.write(namedOutput, key, NullWritable.get(), namedOutput);
   }

   public void write(String namedOutput, Object key, Object value) throws IOException, InterruptedException {
      this.write(namedOutput, key, value, namedOutput);
   }

   public void write(String namedOutput, Object key, Object value, String baseOutputPath) throws IOException, InterruptedException {
      checkNamedOutputName(this.context, namedOutput, false);
      checkBaseOutputPath(baseOutputPath);
      if (!this.namedOutputs.contains(namedOutput)) {
         throw new IllegalArgumentException("Undefined named output '" + namedOutput + "'");
      } else {
         TaskAttemptContext taskContext = this.getContext(namedOutput);
         this.getRecordWriter(taskContext, baseOutputPath).write(key, value);
      }
   }

   public void write(Object key, Object value, String baseOutputPath) throws IOException, InterruptedException {
      this.write(key, value, (Schema)null, (Schema)null, baseOutputPath);
   }

   public void write(Object key, Object value, Schema keySchema, Schema valSchema, String baseOutputPath) throws IOException, InterruptedException {
      checkBaseOutputPath(baseOutputPath);
      Job job = Job.getInstance(this.context.getConfiguration());
      this.setSchema(job, keySchema, valSchema);
      TaskAttemptContext taskContext = this.createTaskAttemptContext(job.getConfiguration(), this.context.getTaskAttemptID());
      this.getRecordWriter(taskContext, baseOutputPath).write(key, value);
   }

   public long sync(String namedOutput, String baseOutputPath) throws IOException, InterruptedException {
      checkNamedOutputName(this.context, namedOutput, false);
      checkBaseOutputPath(baseOutputPath);
      if (!this.namedOutputs.contains(namedOutput)) {
         throw new IllegalArgumentException("Undefined named output '" + namedOutput + "'");
      } else {
         TaskAttemptContext taskContext = this.getContext(namedOutput);
         RecordWriter recordWriter = this.getRecordWriter(taskContext, baseOutputPath);
         long position = -1L;
         if (recordWriter instanceof Syncable) {
            Syncable syncableWriter = (Syncable)recordWriter;
            position = syncableWriter.sync();
         }

         return position;
      }
   }

   private synchronized RecordWriter getRecordWriter(TaskAttemptContext taskContext, String baseFileName) throws IOException, InterruptedException {
      RecordWriter writer = (RecordWriter)this.recordWriters.get(baseFileName);
      if (writer == null) {
         taskContext.getConfiguration().set("avro.mo.config.namedOutput", baseFileName);

         try {
            writer = ((OutputFormat)ReflectionUtils.newInstance(taskContext.getOutputFormatClass(), taskContext.getConfiguration())).getRecordWriter(taskContext);
         } catch (ClassNotFoundException e) {
            throw new IOException(e);
         }

         if (this.countersEnabled) {
            writer = new RecordWriterWithCounter(writer, baseFileName, this.context);
         }

         this.recordWriters.put(baseFileName, writer);
      }

      return writer;
   }

   private void setSchema(Job job, Schema keySchema, Schema valSchema) {
      boolean isMaponly = job.getNumReduceTasks() == 0;
      if (keySchema != null) {
         if (isMaponly) {
            AvroJob.setMapOutputKeySchema(job, keySchema);
         } else {
            AvroJob.setOutputKeySchema(job, keySchema);
         }
      }

      if (valSchema != null) {
         if (isMaponly) {
            AvroJob.setMapOutputValueSchema(job, valSchema);
         } else {
            AvroJob.setOutputValueSchema(job, valSchema);
         }
      }

   }

   private TaskAttemptContext getContext(String nameOutput) throws IOException {
      TaskAttemptContext taskContext = (TaskAttemptContext)this.taskContexts.get(nameOutput);
      if (taskContext != null) {
         return taskContext;
      } else {
         Job job = new Job(this.context.getConfiguration());
         job.setOutputFormatClass(getNamedOutputFormatClass(this.context, nameOutput));
         Schema keySchema = null;
         Schema valSchema = null;
         if (job.getConfiguration().get("avro.mapreduce.multipleoutputs.namedOutput." + nameOutput + ".keyschema", (String)null) != null) {
            keySchema = Schema.parse(job.getConfiguration().get("avro.mapreduce.multipleoutputs.namedOutput." + nameOutput + ".keyschema"));
         }

         if (job.getConfiguration().get("avro.mapreduce.multipleoutputs.namedOutput." + nameOutput + ".valueschema", (String)null) != null) {
            valSchema = Schema.parse(job.getConfiguration().get("avro.mapreduce.multipleoutputs.namedOutput." + nameOutput + ".valueschema"));
         }

         this.setSchema(job, keySchema, valSchema);
         taskContext = this.createTaskAttemptContext(job.getConfiguration(), this.context.getTaskAttemptID());
         this.taskContexts.put(nameOutput, taskContext);
         return taskContext;
      }
   }

   private TaskAttemptContext createTaskAttemptContext(Configuration conf, TaskAttemptID taskId) {
      try {
         Class<?> c = this.getTaskAttemptContextClass();
         Constructor<?> cons = c.getConstructor(Configuration.class, TaskAttemptID.class);
         return (TaskAttemptContext)cons.newInstance(conf, taskId);
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   private Class getTaskAttemptContextClass() {
      try {
         return Class.forName("org.apache.hadoop.mapreduce.task.TaskAttemptContextImpl");
      } catch (Exception var4) {
         try {
            return Class.forName("org.apache.hadoop.mapreduce.TaskAttemptContext");
         } catch (Exception ex) {
            throw new IllegalStateException(ex);
         }
      }
   }

   public void close() throws IOException, InterruptedException {
      for(RecordWriter writer : this.recordWriters.values()) {
         writer.close(this.context);
      }

   }

   private static class RecordWriterWithCounter extends RecordWriter {
      private RecordWriter writer;
      private String counterName;
      private TaskInputOutputContext context;

      public RecordWriterWithCounter(RecordWriter writer, String counterName, TaskInputOutputContext context) {
         this.writer = writer;
         this.counterName = counterName;
         this.context = context;
      }

      public void write(Object key, Object value) throws IOException, InterruptedException {
         this.context.getCounter(AvroMultipleOutputs.COUNTERS_GROUP, this.counterName).increment(1L);
         this.writer.write(key, value);
      }

      public void close(TaskAttemptContext context) throws IOException, InterruptedException {
         this.writer.close(context);
      }
   }
}
