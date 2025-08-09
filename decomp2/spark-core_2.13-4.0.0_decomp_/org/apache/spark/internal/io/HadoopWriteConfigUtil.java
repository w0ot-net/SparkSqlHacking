package org.apache.spark.internal.io;

import java.io.Serializable;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.spark.SparkConf;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a!B\u0006\r\u0003\u00039\u0002\u0002C\u0016\u0001\u0005\u0007\u0005\u000b1\u0002\u0017\t\u000bu\u0002A\u0011\u0001 \t\u000b\u0019\u0003a\u0011A$\t\u000b}\u0003a\u0011\u00011\t\u000b)\u0004a\u0011A6\t\u000bA\u0004a\u0011A9\t\u000ba\u0004a\u0011A=\t\r}\u0004a\u0011AA\u0001\u0011\u001d\t)\u0001\u0001D\u0001\u0003\u000fAq!!\u0004\u0001\r\u0003\tyAA\u000bIC\u0012|w\u000e],sSR,7i\u001c8gS\u001e,F/\u001b7\u000b\u00055q\u0011AA5p\u0015\ty\u0001#\u0001\u0005j]R,'O\\1m\u0015\t\t\"#A\u0003ta\u0006\u00148N\u0003\u0002\u0014)\u00051\u0011\r]1dQ\u0016T\u0011!F\u0001\u0004_J<7\u0001A\u000b\u00041\r#4c\u0001\u0001\u001a?A\u0011!$H\u0007\u00027)\tA$A\u0003tG\u0006d\u0017-\u0003\u0002\u001f7\t1\u0011I\\=SK\u001a\u0004\"\u0001\t\u0015\u000f\u0005\u00052cB\u0001\u0012&\u001b\u0005\u0019#B\u0001\u0013\u0017\u0003\u0019a$o\\8u}%\tA$\u0003\u0002(7\u00059\u0001/Y2lC\u001e,\u0017BA\u0015+\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t93$\u0001\u0006fm&$WM\\2fIE\u00022!\f\u00193\u001b\u0005q#BA\u0018\u001c\u0003\u001d\u0011XM\u001a7fGRL!!\r\u0018\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"a\r\u001b\r\u0001\u0011)Q\u0007\u0001b\u0001m\t\ta+\u0005\u00028uA\u0011!\u0004O\u0005\u0003sm\u0011qAT8uQ&tw\r\u0005\u0002\u001bw%\u0011Ah\u0007\u0002\u0004\u0003:L\u0018A\u0002\u001fj]&$h\bF\u0001@)\t\u0001U\t\u0005\u0003B\u0001\t\u0013T\"\u0001\u0007\u0011\u0005M\u001aE!\u0002#\u0001\u0005\u00041$!A&\t\u000b-\u0012\u00019\u0001\u0017\u0002!\r\u0014X-\u0019;f\u0015>\u00147i\u001c8uKb$Hc\u0001%Q5B\u0011\u0011JT\u0007\u0002\u0015*\u00111\nT\u0001\n[\u0006\u0004(/\u001a3vG\u0016T!!\u0014\n\u0002\r!\fGm\\8q\u0013\ty%J\u0001\u0006K_\n\u001cuN\u001c;fqRDQ!U\u0002A\u0002I\u000bAB[8c)J\f7m[3s\u0013\u0012\u0004\"aU,\u000f\u0005Q+\u0006C\u0001\u0012\u001c\u0013\t16$\u0001\u0004Qe\u0016$WMZ\u0005\u00031f\u0013aa\u0015;sS:<'B\u0001,\u001c\u0011\u0015Y6\u00011\u0001]\u0003\u0015QwNY%e!\tQR,\u0003\u0002_7\t\u0019\u0011J\u001c;\u00021\r\u0014X-\u0019;f)\u0006\u001c8.\u0011;uK6\u0004HoQ8oi\u0016DH\u000fF\u0003bI\u00164\u0007\u000e\u0005\u0002JE&\u00111M\u0013\u0002\u0013)\u0006\u001c8.\u0011;uK6\u0004HoQ8oi\u0016DH\u000fC\u0003R\t\u0001\u0007!\u000bC\u0003\\\t\u0001\u0007A\fC\u0003h\t\u0001\u0007A,A\u0004ta2LG/\u00133\t\u000b%$\u0001\u0019\u0001/\u0002\u001bQ\f7o[!ui\u0016l\u0007\u000f^%e\u0003=\u0019'/Z1uK\u000e{W.\\5ui\u0016\u0014HC\u00017p!\t\tU.\u0003\u0002o\u0019\ti\u0002*\u00193p_Bl\u0015\r\u001d*fIV\u001cWmQ8n[&$\bK]8u_\u000e|G\u000eC\u0003\\\u000b\u0001\u0007A,\u0001\u0006j]&$xK]5uKJ$2A];x!\tQ2/\u0003\u0002u7\t!QK\\5u\u0011\u00151h\u00011\u0001b\u0003-!\u0018m]6D_:$X\r\u001f;\t\u000b\u001d4\u0001\u0019\u0001/\u0002\u000b]\u0014\u0018\u000e^3\u0015\u0005IT\b\"B>\b\u0001\u0004a\u0018\u0001\u00029bSJ\u0004BAG?Ce%\u0011ap\u0007\u0002\u0007)V\u0004H.\u001a\u001a\u0002\u0017\rdwn]3Xe&$XM\u001d\u000b\u0004e\u0006\r\u0001\"\u0002<\t\u0001\u0004\t\u0017\u0001E5oSR|U\u000f\u001e9vi\u001a{'/\\1u)\r\u0011\u0018\u0011\u0002\u0005\u0007\u0003\u0017I\u0001\u0019\u0001%\u0002\u0015)|'mQ8oi\u0016DH/\u0001\u0006bgN,'\u000f^\"p]\u001a$RA]A\t\u0003'Aa!a\u0003\u000b\u0001\u0004A\u0005bBA\u000b\u0015\u0001\u0007\u0011qC\u0001\u0005G>tg\r\u0005\u0003\u0002\u001a\u0005mQ\"\u0001\t\n\u0007\u0005u\u0001CA\u0005Ta\u0006\u00148nQ8oM\u0002"
)
public abstract class HadoopWriteConfigUtil implements Serializable {
   public abstract JobContext createJobContext(final String jobTrackerId, final int jobId);

   public abstract TaskAttemptContext createTaskAttemptContext(final String jobTrackerId, final int jobId, final int splitId, final int taskAttemptId);

   public abstract HadoopMapReduceCommitProtocol createCommitter(final int jobId);

   public abstract void initWriter(final TaskAttemptContext taskContext, final int splitId);

   public abstract void write(final Tuple2 pair);

   public abstract void closeWriter(final TaskAttemptContext taskContext);

   public abstract void initOutputFormat(final JobContext jobContext);

   public abstract void assertConf(final JobContext jobContext, final SparkConf conf);

   public HadoopWriteConfigUtil(final ClassTag evidence$1) {
   }
}
