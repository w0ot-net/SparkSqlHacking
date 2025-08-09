package org.apache.avro.mapred;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configurable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.util.ReflectionUtils;

class TaggedInputSplit implements Configurable, InputSplit {
   private Class inputSplitClass;
   private InputSplit inputSplit;
   private Class inputFormatClass;
   private Class mapperClass;
   private Schema schema;
   private Schema.Parser schemaParser = new Schema.Parser();
   private Configuration conf;

   public TaggedInputSplit() {
   }

   public TaggedInputSplit(InputSplit inputSplit, Configuration conf, Class inputFormatClass, Class mapperClass, Schema inputSchema) {
      this.inputSplitClass = inputSplit.getClass();
      this.inputSplit = inputSplit;
      this.conf = conf;
      this.inputFormatClass = inputFormatClass;
      this.mapperClass = mapperClass;
      this.schema = inputSchema;
   }

   public InputSplit getInputSplit() {
      return this.inputSplit;
   }

   public Class getInputFormatClass() {
      return this.inputFormatClass;
   }

   public Class getMapperClass() {
      return this.mapperClass;
   }

   public Schema getSchema() {
      return this.schema;
   }

   public long getLength() throws IOException {
      return this.inputSplit.getLength();
   }

   public String[] getLocations() throws IOException {
      return this.inputSplit.getLocations();
   }

   public void readFields(DataInput in) throws IOException {
      this.inputSplitClass = this.readClass(in);
      this.inputSplit = (InputSplit)ReflectionUtils.newInstance(this.inputSplitClass, this.conf);
      this.inputSplit.readFields(in);
      this.inputFormatClass = this.readClass(in);
      this.mapperClass = this.readClass(in);
      String schemaString = Text.readString(in);
      this.schema = this.schemaParser.parse(schemaString);
   }

   private Class readClass(DataInput in) throws IOException {
      String className = Text.readString(in);

      try {
         return this.conf.getClassByName(className);
      } catch (ClassNotFoundException e) {
         throw new RuntimeException("readObject can't find class", e);
      }
   }

   public void write(DataOutput out) throws IOException {
      Text.writeString(out, this.inputSplitClass.getName());
      this.inputSplit.write(out);
      Text.writeString(out, this.inputFormatClass.getName());
      Text.writeString(out, this.mapperClass.getName());
      Text.writeString(out, this.schema.toString());
   }

   public Configuration getConf() {
      return this.conf;
   }

   public void setConf(Configuration conf) {
      this.conf = conf;
   }

   public String toString() {
      return this.inputSplit.toString();
   }
}
