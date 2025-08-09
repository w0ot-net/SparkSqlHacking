package org.apache.spark.input;

import java.lang.invoke.SerializedLambda;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import scala.collection.IterableOnceOps;
import scala.collection.mutable.Buffer;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005]3Q!\u0002\u0004\u0001\u00119AQ\u0001\n\u0001\u0005\u0002\u0019BQ\u0001\u000b\u0001\u0005R%BQA\u0010\u0001\u0005B}BQ\u0001\u0014\u0001\u0005\u00025\u0013\u0001d\u00165pY\u0016$V\r\u001f;GS2,\u0017J\u001c9vi\u001a{'/\\1u\u0015\t9\u0001\"A\u0003j]B,HO\u0003\u0002\n\u0015\u0005)1\u000f]1sW*\u00111\u0002D\u0001\u0007CB\f7\r[3\u000b\u00035\t1a\u001c:h'\r\u0001q\u0002\t\t\u0005!aQ\"$D\u0001\u0012\u0015\t9!C\u0003\u0002\u0014)\u0005\u0019A.\u001b2\u000b\u0005U1\u0012!C7baJ,G-^2f\u0015\t9\"\"\u0001\u0004iC\u0012|w\u000e]\u0005\u00033E\u0011acQ8nE&tWMR5mK&s\u0007/\u001e;G_Jl\u0017\r\u001e\t\u00037yi\u0011\u0001\b\u0006\u0003;Y\t!![8\n\u0005}a\"\u0001\u0002+fqR\u0004\"!\t\u0012\u000e\u0003\u0019I!a\t\u0004\u0003\u0019\r{gNZ5hkJ\f'\r\\3\u0002\rqJg.\u001b;?\u0007\u0001!\u0012a\n\t\u0003C\u0001\t1\"[:Ta2LG/\u00192mKR\u0019!\u0006\r\u001c\u0011\u0005-rS\"\u0001\u0017\u000b\u00035\nQa]2bY\u0006L!a\f\u0017\u0003\u000f\t{w\u000e\\3b]\")\u0011G\u0001a\u0001e\u000591m\u001c8uKb$\bCA\u001a5\u001b\u0005!\u0012BA\u001b\u0015\u0005)QuNY\"p]R,\u0007\u0010\u001e\u0005\u0006o\t\u0001\r\u0001O\u0001\u0005M&dW\r\u0005\u0002:y5\t!H\u0003\u0002<-\u0005\u0011am]\u0005\u0003{i\u0012A\u0001U1uQ\u0006\u00112M]3bi\u0016\u0014VmY8sIJ+\u0017\rZ3s)\r\u00015\t\u0013\t\u0005g\u0005S\"$\u0003\u0002C)\ta!+Z2pe\u0012\u0014V-\u00193fe\")Ai\u0001a\u0001\u000b\u0006)1\u000f\u001d7jiB\u00111GR\u0005\u0003\u000fR\u0011!\"\u00138qkR\u001c\u0006\u000f\\5u\u0011\u0015\t4\u00011\u0001J!\t\u0019$*\u0003\u0002L)\t\u0011B+Y:l\u0003R$X-\u001c9u\u0007>tG/\u001a=u\u0003A\u0019X\r^'j]B\u000b'\u000f^5uS>t7\u000fF\u0002O#J\u0003\"aK(\n\u0005Ac#\u0001B+oSRDQ!\r\u0003A\u0002IBQa\u0015\u0003A\u0002Q\u000bQ\"\\5o!\u0006\u0014H/\u001b;j_:\u001c\bCA\u0016V\u0013\t1FFA\u0002J]R\u0004"
)
public class WholeTextFileInputFormat extends CombineFileInputFormat implements Configurable {
   private Configuration org$apache$spark$input$Configurable$$conf;

   public void setConf(final Configuration c) {
      Configurable.setConf$(this, c);
   }

   public Configuration getConf() {
      return Configurable.getConf$(this);
   }

   public Configuration org$apache$spark$input$Configurable$$conf() {
      return this.org$apache$spark$input$Configurable$$conf;
   }

   public void org$apache$spark$input$Configurable$$conf_$eq(final Configuration x$1) {
      this.org$apache$spark$input$Configurable$$conf = x$1;
   }

   public boolean isSplitable(final JobContext context, final Path file) {
      return false;
   }

   public RecordReader createRecordReader(final InputSplit split, final TaskAttemptContext context) {
      ConfigurableCombineFileRecordReader reader = new ConfigurableCombineFileRecordReader(split, context, WholeTextFileRecordReader.class);
      reader.setConf(this.getConf());
      return reader;
   }

   public void setMinPartitions(final JobContext context, final int minPartitions) {
      Buffer files = .MODULE$.ListHasAsScala(this.listStatus(context)).asScala();
      long totalLen = BoxesRunTime.unboxToLong(((IterableOnceOps)files.map((file) -> BoxesRunTime.boxToLong($anonfun$setMinPartitions$1(file)))).sum(scala.math.Numeric.LongIsIntegral..MODULE$));
      long maxSplitSize = (long)Math.ceil((double)totalLen * (double)1.0F / (double)(minPartitions == 0 ? 1 : minPartitions));
      Configuration config = context.getConfiguration();
      long minSplitSizePerNode = config.getLong("mapreduce.input.fileinputformat.split.minsize.per.node", 0L);
      long minSplitSizePerRack = config.getLong("mapreduce.input.fileinputformat.split.minsize.per.rack", 0L);
      if (maxSplitSize < minSplitSizePerNode) {
         super.setMinSplitSizeNode(maxSplitSize);
      }

      if (maxSplitSize < minSplitSizePerRack) {
         super.setMinSplitSizeRack(maxSplitSize);
      }

      super.setMaxSplitSize(maxSplitSize);
   }

   // $FF: synthetic method
   public static final long $anonfun$setMinPartitions$1(final FileStatus file) {
      return file.isDirectory() ? 0L : file.getLen();
   }

   public WholeTextFileInputFormat() {
      Configurable.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
