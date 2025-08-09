package org.apache.spark.input;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import org.sparkproject.guava.io.ByteStreams;
import org.sparkproject.guava.io.Closeables;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E4Q\u0001E\t\u0001'eA\u0001\u0002\f\u0001\u0003\u0002\u0003\u0006IA\f\u0005\tk\u0001\u0011\t\u0011)A\u0005m!A\u0011\b\u0001B\u0001B\u0003%!\bC\u0003C\u0001\u0011\u00051\t\u0003\u0004I\u0001\u0001\u0006I!\u0013\u0005\u0007\u0019\u0002\u0001\u000b\u0011B(\t\rI\u0003\u0001\u0015)\u0003T\u0011\u0019I\u0006\u0001)A\u0005E!1!\f\u0001Q!\n\tBQa\u0017\u0001\u0005BqCQ!\u001a\u0001\u0005B\u0019DQa\u001a\u0001\u0005B!DQ\u0001\u001c\u0001\u0005B5DQA\u001c\u0001\u0005B5DQa\u001c\u0001\u0005BA\u0014\u0011d\u00165pY\u0016$V\r\u001f;GS2,'+Z2pe\u0012\u0014V-\u00193fe*\u0011!cE\u0001\u0006S:\u0004X\u000f\u001e\u0006\u0003)U\tQa\u001d9be.T!AF\f\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005A\u0012aA8sON\u0019\u0001A\u0007\u0015\u0011\tm\u0001#EI\u0007\u00029)\u0011QDH\u0001\n[\u0006\u0004(/\u001a3vG\u0016T!aH\u000b\u0002\r!\fGm\\8q\u0013\t\tCD\u0001\u0007SK\u000e|'\u000f\u001a*fC\u0012,'\u000f\u0005\u0002$M5\tAE\u0003\u0002&=\u0005\u0011\u0011n\\\u0005\u0003O\u0011\u0012A\u0001V3yiB\u0011\u0011FK\u0007\u0002#%\u00111&\u0005\u0002\r\u0007>tg-[4ve\u0006\u0014G.Z\u0001\u0006gBd\u0017\u000e^\u0002\u0001!\ty3'D\u00011\u0015\t\u0011\u0012G\u0003\u000239\u0005\u0019A.\u001b2\n\u0005Q\u0002$\u0001E\"p[\nLg.\u001a$jY\u0016\u001c\u0006\u000f\\5u\u0003\u001d\u0019wN\u001c;fqR\u0004\"aG\u001c\n\u0005ab\"A\u0005+bg.\fE\u000f^3naR\u001cuN\u001c;fqR\fQ!\u001b8eKb\u0004\"a\u000f!\u000e\u0003qR!!\u0010 \u0002\t1\fgn\u001a\u0006\u0002\u007f\u0005!!.\u0019<b\u0013\t\tEHA\u0004J]R,w-\u001a:\u0002\rqJg.\u001b;?)\u0011!UIR$\u0011\u0005%\u0002\u0001\"\u0002\u0017\u0005\u0001\u0004q\u0003\"B\u001b\u0005\u0001\u00041\u0004\"B\u001d\u0005\u0001\u0004Q\u0014\u0001\u00029bi\"\u0004\"AS'\u000e\u0003-S!\u0001\u0014\u0010\u0002\u0005\u0019\u001c\u0018B\u0001(L\u0005\u0011\u0001\u0016\r\u001e5\u0011\u0005)\u0003\u0016BA)L\u0005)1\u0015\u000e\\3TsN$X-\\\u0001\naJ|7-Z:tK\u0012\u0004\"\u0001V,\u000e\u0003US\u0011AV\u0001\u0006g\u000e\fG.Y\u0005\u00031V\u0013qAQ8pY\u0016\fg.A\u0002lKf\fQA^1mk\u0016\f!\"\u001b8ji&\fG.\u001b>f)\ri\u0006\r\u001a\t\u0003)zK!aX+\u0003\tUs\u0017\u000e\u001e\u0005\u0006Y)\u0001\r!\u0019\t\u00037\tL!a\u0019\u000f\u0003\u0015%s\u0007/\u001e;Ta2LG\u000fC\u00036\u0015\u0001\u0007a'A\u0003dY>\u001cX\rF\u0001^\u0003-9W\r\u001e)s_\u001e\u0014Xm]:\u0015\u0003%\u0004\"\u0001\u00166\n\u0005-,&!\u0002$m_\u0006$\u0018!D4fi\u000e+(O]3oi.+\u0017\u0010F\u0001#\u0003=9W\r^\"veJ,g\u000e\u001e,bYV,\u0017\u0001\u00048fqR\\U-\u001f,bYV,G#A*"
)
public class WholeTextFileRecordReader extends RecordReader implements Configurable {
   private final Path path;
   private final FileSystem fs;
   private boolean processed;
   private final Text key;
   private Text value;
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

   public void initialize(final InputSplit split, final TaskAttemptContext context) {
   }

   public void close() {
   }

   public float getProgress() {
      return this.processed ? 1.0F : 0.0F;
   }

   public Text getCurrentKey() {
      return this.key;
   }

   public Text getCurrentValue() {
      return this.value;
   }

   public boolean nextKeyValue() {
      if (!this.processed) {
         Configuration conf = this.getConf();
         CompressionCodecFactory factory = new CompressionCodecFactory(conf);
         CompressionCodec codec = factory.getCodec(this.path);
         FSDataInputStream fileIn = this.fs.open(this.path);
         byte[] innerBuffer = codec != null ? ByteStreams.toByteArray(codec.createInputStream(fileIn)) : ByteStreams.toByteArray(fileIn);
         this.value = new Text(innerBuffer);
         Closeables.close(fileIn, false);
         this.processed = true;
         return true;
      } else {
         return false;
      }
   }

   public WholeTextFileRecordReader(final CombineFileSplit split, final TaskAttemptContext context, final Integer index) {
      Configurable.$init$(this);
      this.path = split.getPath(.MODULE$.Integer2int(index));
      this.fs = this.path.getFileSystem(context.getConfiguration());
      this.processed = false;
      this.key = new Text(this.path.toString());
      this.value = null;
   }
}
