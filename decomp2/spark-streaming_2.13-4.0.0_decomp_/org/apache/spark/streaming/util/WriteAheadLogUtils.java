package org.apache.spark.streaming.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005MqAB\u0007\u000f\u0011\u0003\u0001\u0002D\u0002\u0004\u001b\u001d!\u0005\u0001c\u0007\u0005\u0006Q\u0005!\tA\u000b\u0005\u0006W\u0005!\t\u0001\f\u0005\u0006m\u0005!\ta\u000e\u0005\u0006}\u0005!\ta\u0010\u0005\u0006\u0005\u0006!\ta\u0011\u0005\u0006\r\u0006!\ta\u0012\u0005\u0006\u0019\u0006!\t!\u0014\u0005\u0006!\u0006!\t!\u0015\u0005\u0006[\u0006!\tA\u001c\u0005\u0006e\u0006!Ia\u001d\u0005\u0006q\u0006!I!_\u0001\u0013/JLG/Z!iK\u0006$Gj\\4Vi&d7O\u0003\u0002\u0010!\u0005!Q\u000f^5m\u0015\t\t\"#A\u0005tiJ,\u0017-\\5oO*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014x\r\u0005\u0002\u001a\u00035\taB\u0001\nXe&$X-\u00115fC\u0012dunZ+uS2\u001c8cA\u0001\u001dEA\u0011Q\u0004I\u0007\u0002=)\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"=\t1\u0011I\\=SK\u001a\u0004\"a\t\u0014\u000e\u0003\u0011R!!\n\n\u0002\u0011%tG/\u001a:oC2L!a\n\u0013\u0003\u000f1{wmZ5oO\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0019\u0003E)g.\u00192mKJ+7-Z5wKJdun\u001a\u000b\u0003[A\u0002\"!\b\u0018\n\u0005=r\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006c\r\u0001\rAM\u0001\u0005G>tg\r\u0005\u00024i5\t!#\u0003\u00026%\tI1\u000b]1sW\u000e{gNZ\u0001\u0017O\u0016$(k\u001c7mS:<\u0017J\u001c;feZ\fGnU3dgR\u0019\u0001h\u000f\u001f\u0011\u0005uI\u0014B\u0001\u001e\u001f\u0005\rIe\u000e\u001e\u0005\u0006c\u0011\u0001\rA\r\u0005\u0006{\u0011\u0001\r!L\u0001\tSN$%/\u001b<fe\u0006qq-\u001a;NCb4\u0015-\u001b7ve\u0016\u001cHc\u0001\u001dA\u0003\")\u0011'\u0002a\u0001e!)Q(\u0002a\u0001[\u0005\t\u0012n\u001d\"bi\u000eD\u0017N\\4F]\u0006\u0014G.\u001a3\u0015\u00075\"U\tC\u00032\r\u0001\u0007!\u0007C\u0003>\r\u0001\u0007Q&\u0001\nhKR\u0014\u0015\r^2iS:<G+[7f_V$HC\u0001%L!\ti\u0012*\u0003\u0002K=\t!Aj\u001c8h\u0011\u0015\tt\u00011\u00013\u0003e\u0019\bn\\;mI\u000ecwn]3GS2,\u0017I\u001a;fe^\u0013\u0018\u000e^3\u0015\u00075ru\nC\u00032\u0011\u0001\u0007!\u0007C\u0003>\u0011\u0001\u0007Q&\u0001\nde\u0016\fG/\u001a'pO\u001a{'\u000f\u0012:jm\u0016\u0014H\u0003\u0002*V/\u0012\u0004\"!G*\n\u0005Qs!!D,sSR,\u0017\t[3bI2{w\rC\u0003W\u0013\u0001\u0007!'A\u0005ta\u0006\u00148nQ8oM\")\u0001,\u0003a\u00013\u0006\u0019b-\u001b7f/\u0006dGj\\4ESJ,7\r^8ssB\u0011!,\u0019\b\u00037~\u0003\"\u0001\u0018\u0010\u000e\u0003uS!AX\u0015\u0002\rq\u0012xn\u001c;?\u0013\t\u0001g$\u0001\u0004Qe\u0016$WMZ\u0005\u0003E\u000e\u0014aa\u0015;sS:<'B\u00011\u001f\u0011\u0015)\u0017\u00021\u0001g\u0003E1\u0017\u000e\\3XC2D\u0015\rZ8pa\u000e{gN\u001a\t\u0003O.l\u0011\u0001\u001b\u0006\u0003c%T!A\u001b\u000b\u0002\r!\fGm\\8q\u0013\ta\u0007NA\u0007D_:4\u0017nZ;sCRLwN\\\u0001\u0015GJ,\u0017\r^3M_\u001e4uN\u001d*fG\u0016Lg/\u001a:\u0015\tI{\u0007/\u001d\u0005\u0006-*\u0001\rA\r\u0005\u00061*\u0001\r!\u0017\u0005\u0006K*\u0001\rAZ\u0001\nGJ,\u0017\r^3M_\u001e$RA\u0015;vm^DQ!P\u0006A\u00025BQAV\u0006A\u0002IBQ\u0001W\u0006A\u0002eCQ!Z\u0006A\u0002\u0019\f\u0001#\u001b8ti\u0006tG/[1uK\u000ec\u0017m]:\u0015\tIS\u0018\u0011\u0003\u0005\u0006w2\u0001\r\u0001`\u0001\u0004G2\u001c\bgA?\u0002\u0006A!!L`A\u0001\u0013\ty8MA\u0003DY\u0006\u001c8\u000f\u0005\u0003\u0002\u0004\u0005\u0015A\u0002\u0001\u0003\f\u0003\u000fQ\u0018\u0011!A\u0001\u0006\u0003\tIAA\u0002`IE\n2!a\u0003S!\ri\u0012QB\u0005\u0004\u0003\u001fq\"a\u0002(pi\"Lgn\u001a\u0005\u0006c1\u0001\rA\r"
)
public final class WriteAheadLogUtils {
   public static WriteAheadLog createLogForReceiver(final SparkConf sparkConf, final String fileWalLogDirectory, final Configuration fileWalHadoopConf) {
      return WriteAheadLogUtils$.MODULE$.createLogForReceiver(sparkConf, fileWalLogDirectory, fileWalHadoopConf);
   }

   public static WriteAheadLog createLogForDriver(final SparkConf sparkConf, final String fileWalLogDirectory, final Configuration fileWalHadoopConf) {
      return WriteAheadLogUtils$.MODULE$.createLogForDriver(sparkConf, fileWalLogDirectory, fileWalHadoopConf);
   }

   public static boolean shouldCloseFileAfterWrite(final SparkConf conf, final boolean isDriver) {
      return WriteAheadLogUtils$.MODULE$.shouldCloseFileAfterWrite(conf, isDriver);
   }

   public static long getBatchingTimeout(final SparkConf conf) {
      return WriteAheadLogUtils$.MODULE$.getBatchingTimeout(conf);
   }

   public static boolean isBatchingEnabled(final SparkConf conf, final boolean isDriver) {
      return WriteAheadLogUtils$.MODULE$.isBatchingEnabled(conf, isDriver);
   }

   public static int getMaxFailures(final SparkConf conf, final boolean isDriver) {
      return WriteAheadLogUtils$.MODULE$.getMaxFailures(conf, isDriver);
   }

   public static int getRollingIntervalSecs(final SparkConf conf, final boolean isDriver) {
      return WriteAheadLogUtils$.MODULE$.getRollingIntervalSecs(conf, isDriver);
   }

   public static boolean enableReceiverLog(final SparkConf conf) {
      return WriteAheadLogUtils$.MODULE$.enableReceiverLog(conf);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return WriteAheadLogUtils$.MODULE$.LogStringContext(sc);
   }
}
