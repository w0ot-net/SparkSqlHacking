package org.apache.avro.mapred;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.avro.Schema;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.OutputFormat;
import org.apache.hadoop.mapred.RecordWriter;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.util.Progressable;

public class AvroMultipleOutputs {
   private static final String NAMED_OUTPUTS = "mo.namedOutputs";
   private static final String MO_PREFIX = "mo.namedOutput.";
   private static final String FORMAT = ".avro";
   private static final String MULTI = ".multi";
   private static final String COUNTERS_ENABLED = "mo.counters";
   private static final String COUNTERS_GROUP = AvroMultipleOutputs.class.getName();
   private JobConf conf;
   private OutputFormat outputFormat;
   private Set namedOutputs;
   private Map recordWriters;
   private boolean countersEnabled;

   private static void checkNamedOutput(JobConf conf, String namedOutput, boolean alreadyDefined) {
      List<String> definedChannels = getNamedOutputsList(conf);
      if (alreadyDefined && definedChannels.contains(namedOutput)) {
         throw new IllegalArgumentException("Named output '" + namedOutput + "' already alreadyDefined");
      } else if (!alreadyDefined && !definedChannels.contains(namedOutput)) {
         throw new IllegalArgumentException("Named output '" + namedOutput + "' not defined");
      }
   }

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

   private static void checkNamedOutputName(String namedOutput) {
      checkTokenName(namedOutput);
      if (namedOutput.equals("part")) {
         throw new IllegalArgumentException("Named output name cannot be 'part'");
      }
   }

   public static List getNamedOutputsList(JobConf conf) {
      List<String> names = new ArrayList();
      StringTokenizer st = new StringTokenizer(conf.get("mo.namedOutputs", ""), " ");

      while(st.hasMoreTokens()) {
         names.add(st.nextToken());
      }

      return names;
   }

   public static boolean isMultiNamedOutput(JobConf conf, String namedOutput) {
      checkNamedOutput(conf, namedOutput, false);
      return conf.getBoolean("mo.namedOutput." + namedOutput + ".multi", false);
   }

   public static Class getNamedOutputFormatClass(JobConf conf, String namedOutput) {
      checkNamedOutput(conf, namedOutput, false);
      return conf.getClass("mo.namedOutput." + namedOutput + ".avro", (Class)null, OutputFormat.class);
   }

   public static void addNamedOutput(JobConf conf, String namedOutput, Class outputFormatClass, Schema schema) {
      addNamedOutput(conf, namedOutput, false, outputFormatClass, schema);
   }

   public static void addMultiNamedOutput(JobConf conf, String namedOutput, Class outputFormatClass, Schema schema) {
      addNamedOutput(conf, namedOutput, true, outputFormatClass, schema);
   }

   private static void addNamedOutput(JobConf conf, String namedOutput, boolean multi, Class outputFormatClass, Schema schema) {
      checkNamedOutputName(namedOutput);
      checkNamedOutput(conf, namedOutput, true);
      if (schema != null) {
         conf.set("mo.namedOutput." + namedOutput + ".schema", schema.toString());
      }

      conf.set("mo.namedOutputs", conf.get("mo.namedOutputs", "") + " " + namedOutput);
      conf.setClass("mo.namedOutput." + namedOutput + ".avro", outputFormatClass, OutputFormat.class);
      conf.setBoolean("mo.namedOutput." + namedOutput + ".multi", multi);
   }

   public static void setCountersEnabled(JobConf conf, boolean enabled) {
      conf.setBoolean("mo.counters", enabled);
   }

   public static boolean getCountersEnabled(JobConf conf) {
      return conf.getBoolean("mo.counters", false);
   }

   public AvroMultipleOutputs(JobConf job) {
      this.conf = job;
      this.outputFormat = new InternalFileOutputFormat();
      this.namedOutputs = Collections.unmodifiableSet(new HashSet(getNamedOutputsList(job)));
      this.recordWriters = new HashMap();
      this.countersEnabled = getCountersEnabled(job);
   }

   public Iterator getNamedOutputs() {
      return this.namedOutputs.iterator();
   }

   private synchronized RecordWriter getRecordWriter(String namedOutput, String baseFileName, final Reporter reporter, Schema schema) throws IOException {
      RecordWriter writer = (RecordWriter)this.recordWriters.get(baseFileName);
      if (writer == null) {
         if (this.countersEnabled && reporter == null) {
            throw new IllegalArgumentException("Counters are enabled, Reporter cannot be NULL");
         }

         if (schema != null) {
            this.conf.set("mo.namedOutput." + namedOutput + ".schema", schema.toString());
         }

         JobConf jobConf = new JobConf(this.conf);
         jobConf.set("mo.config.namedOutput", namedOutput);
         FileSystem fs = FileSystem.get(this.conf);
         writer = this.outputFormat.getRecordWriter(fs, jobConf, baseFileName, reporter);
         if (this.countersEnabled) {
            if (reporter == null) {
               throw new IllegalArgumentException("Counters are enabled, Reporter cannot be NULL");
            }

            writer = new RecordWriterWithCounter(writer, baseFileName, reporter);
         }

         this.recordWriters.put(baseFileName, writer);
      }

      return writer;
   }

   public void collect(String namedOutput, Reporter reporter, Object datum) throws IOException {
      this.getCollector(namedOutput, reporter).collect(datum);
   }

   public void collect(String namedOutput, Reporter reporter, Schema schema, Object datum) throws IOException {
      this.getCollector(namedOutput, reporter, schema).collect(datum);
   }

   public void collect(String namedOutput, Reporter reporter, Schema schema, Object datum, String baseOutputPath) throws IOException {
      this.getCollector(namedOutput, (String)null, reporter, baseOutputPath, schema).collect(datum);
   }

   /** @deprecated */
   public AvroCollector getCollector(String namedOutput, Reporter reporter) throws IOException {
      return this.getCollector(namedOutput, (String)null, reporter, namedOutput, (Schema)null);
   }

   private AvroCollector getCollector(String namedOutput, Reporter reporter, Schema schema) throws IOException {
      return this.getCollector(namedOutput, (String)null, reporter, namedOutput, schema);
   }

   public AvroCollector getCollector(String namedOutput, String multiName, Reporter reporter) throws IOException {
      return this.getCollector(namedOutput, multiName, reporter, namedOutput, (Schema)null);
   }

   private AvroCollector getCollector(String namedOutput, String multiName, Reporter reporter, String baseOutputFileName, Schema schema) throws IOException {
      checkNamedOutputName(namedOutput);
      if (!this.namedOutputs.contains(namedOutput)) {
         throw new IllegalArgumentException("Undefined named output '" + namedOutput + "'");
      } else {
         boolean multi = isMultiNamedOutput(this.conf, namedOutput);
         if (!multi && multiName != null) {
            throw new IllegalArgumentException("Name output '" + namedOutput + "' has not been defined as multi");
         } else {
            if (multi) {
               checkTokenName(multiName);
            }

            String baseFileName = multi ? namedOutput + "_" + multiName : baseOutputFileName;
            final RecordWriter writer = this.getRecordWriter(namedOutput, baseFileName, reporter, schema);
            return new AvroCollector() {
               public void collect(Object key) throws IOException {
                  AvroWrapper wrapper = new AvroWrapper(key);
                  writer.write(wrapper, NullWritable.get());
               }
            };
         }
      }
   }

   public void close() throws IOException {
      for(RecordWriter writer : this.recordWriters.values()) {
         writer.close((Reporter)null);
      }

   }

   private static class RecordWriterWithCounter implements RecordWriter {
      private RecordWriter writer;
      private String counterName;
      private Reporter reporter;

      public RecordWriterWithCounter(RecordWriter writer, String counterName, Reporter reporter) {
         this.writer = writer;
         this.counterName = counterName;
         this.reporter = reporter;
      }

      public void write(Object key, Object value) throws IOException {
         this.reporter.incrCounter(AvroMultipleOutputs.COUNTERS_GROUP, this.counterName, 1L);
         this.writer.write(key, value);
      }

      public void close(Reporter reporter) throws IOException {
         this.writer.close(reporter);
      }
   }

   private static class InternalFileOutputFormat extends FileOutputFormat {
      public static final String CONFIG_NAMED_OUTPUT = "mo.config.namedOutput";

      private InternalFileOutputFormat() {
      }

      public RecordWriter getRecordWriter(FileSystem fs, JobConf job, String baseFileName, Progressable arg3) throws IOException {
         String nameOutput = job.get("mo.config.namedOutput", (String)null);
         String fileName = getUniqueName(job, baseFileName);
         Schema schema = null;
         String schemastr = job.get("mo.namedOutput." + nameOutput + ".schema", (String)null);
         if (schemastr != null) {
            schema = Schema.parse(schemastr);
         }

         JobConf outputConf = new JobConf(job);
         outputConf.setOutputFormat(AvroMultipleOutputs.getNamedOutputFormatClass(job, nameOutput));
         boolean isMapOnly = job.getNumReduceTasks() == 0;
         if (schema != null) {
            if (isMapOnly) {
               AvroJob.setMapOutputSchema(outputConf, schema);
            } else {
               AvroJob.setOutputSchema(outputConf, schema);
            }
         }

         OutputFormat outputFormat = outputConf.getOutputFormat();
         return outputFormat.getRecordWriter(fs, outputConf, fileName, arg3);
      }
   }
}
