package org.apache.spark.input;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.CombineFileRecordReader;
import org.apache.hadoop.mapreduce.lib.input.CombineFileSplit;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q4Qa\u0002\u0005\u0001\u0015AA\u0001b\r\u0001\u0003\u0002\u0003\u0006I\u0001\u000e\u0005\tq\u0001\u0011\t\u0011)A\u0005s!AA\b\u0001B\u0001B\u0003%Q\bC\u0003Y\u0001\u0011\u0005\u0011\fC\u0003f\u0001\u0011\u0005c\rC\u0003k\u0001\u0011\u00053NA\u0012D_:4\u0017nZ;sC\ndWmQ8nE&tWMR5mKJ+7m\u001c:e%\u0016\fG-\u001a:\u000b\u0005%Q\u0011!B5oaV$(BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0016\u0007EyRfE\u0002\u0001%=\u0002BaE\u000e\u001eY5\tAC\u0003\u0002\n+)\u0011acF\u0001\u0004Y&\u0014'B\u0001\r\u001a\u0003%i\u0017\r\u001d:fIV\u001cWM\u0003\u0002\u001b\u0019\u00051\u0001.\u00193p_BL!\u0001\b\u000b\u0003/\r{WNY5oK\u001aKG.\u001a*fG>\u0014HMU3bI\u0016\u0014\bC\u0001\u0010 \u0019\u0001!Q\u0001\t\u0001C\u0002\t\u0012\u0011aS\u0002\u0001#\t\u0019\u0013\u0006\u0005\u0002%O5\tQEC\u0001'\u0003\u0015\u00198-\u00197b\u0013\tASEA\u0004O_RD\u0017N\\4\u0011\u0005\u0011R\u0013BA\u0016&\u0005\r\te.\u001f\t\u0003=5\"QA\f\u0001C\u0002\t\u0012\u0011A\u0016\t\u0003aEj\u0011\u0001C\u0005\u0003e!\u0011AbQ8oM&<WO]1cY\u0016\fQa\u001d9mSR\u0004\"!\u000e\u001c\u000e\u0003]I!aN\f\u0003\u0015%s\u0007/\u001e;Ta2LG/A\u0004d_:$X\r\u001f;\u0011\u0005UR\u0014BA\u001e\u0018\u0005I!\u0016m]6BiR,W\u000e\u001d;D_:$X\r\u001f;\u0002#I,7m\u001c:e%\u0016\fG-\u001a:DY\u0006\u001c8\u000f\r\u0002?\u0015B\u0019qHR%\u000f\u0005\u0001#\u0005CA!&\u001b\u0005\u0011%BA\"\"\u0003\u0019a$o\\8u}%\u0011Q)J\u0001\u0007!J,G-\u001a4\n\u0005\u001dC%!B\"mCN\u001c(BA#&!\tq\"\nB\u0005L\u0007\u0005\u0005\t\u0011!B\u0001\u0019\n\u0019q\fJ\u0019\u0012\u0005\rj%c\u0001(Q'\u001a!q\n\u0001\u0001N\u00051a$/\u001a4j]\u0016lWM\u001c;?!\u0011)\u0014+\b\u0017\n\u0005I;\"\u0001\u0004*fG>\u0014HMU3bI\u0016\u0014\bC\u0001+X\u001b\u0005)&B\u0001,\u001a\u0003\u0011\u0019wN\u001c4\n\u0005I*\u0016A\u0002\u001fj]&$h\b\u0006\u0003[7rk\u0006\u0003\u0002\u0019\u0001;1BQa\r\u0003A\u0002QBQ\u0001\u000f\u0003A\u0002eBQ\u0001\u0010\u0003A\u0002y\u0003$aX1\u0011\u0007}2\u0005\r\u0005\u0002\u001fC\u0012I1*XA\u0001\u0002\u0003\u0015\tAY\t\u0003G\r\u00142\u0001\u001a)T\r\u0011y\u0005\u0001A2\u0002)%t\u0017\u000e\u001e(fqR\u0014VmY8sIJ+\u0017\rZ3s)\u00059\u0007C\u0001\u0013i\u0013\tIWEA\u0004C_>dW-\u00198\u0002\u000fM,GoQ8oMR\u0011An\u001c\t\u0003I5L!A\\\u0013\u0003\tUs\u0017\u000e\u001e\u0005\u0006a\u001a\u0001\r!]\u0001\u0002GB\u0011AK]\u0005\u0003gV\u0013QbQ8oM&<WO]1uS>t\u0007"
)
public class ConfigurableCombineFileRecordReader extends CombineFileRecordReader implements Configurable {
   private Configuration org$apache$spark$input$Configurable$$conf;

   public Configuration getConf() {
      return Configurable.getConf$(this);
   }

   public Configuration org$apache$spark$input$Configurable$$conf() {
      return this.org$apache$spark$input$Configurable$$conf;
   }

   public void org$apache$spark$input$Configurable$$conf_$eq(final Configuration x$1) {
      this.org$apache$spark$input$Configurable$$conf = x$1;
   }

   public boolean initNextRecordReader() {
      boolean r = super.initNextRecordReader();
      if (r && this.getConf() != null) {
         ((org.apache.hadoop.conf.Configurable)this.curReader).setConf(this.getConf());
      }

      return r;
   }

   public void setConf(final Configuration c) {
      Configurable.setConf$(this, c);
      if (this.curReader != null) {
         ((org.apache.hadoop.conf.Configurable)this.curReader).setConf(c);
      }
   }

   public ConfigurableCombineFileRecordReader(final InputSplit split, final TaskAttemptContext context, final Class recordReaderClass) {
      super((CombineFileSplit)split, context, recordReaderClass);
      Configurable.$init$(this);
   }
}
