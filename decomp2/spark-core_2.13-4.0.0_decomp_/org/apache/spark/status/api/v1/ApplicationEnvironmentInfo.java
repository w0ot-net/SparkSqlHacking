package org.apache.spark.status.api.v1;

import scala.collection.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3A\u0001E\t\u0001=!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u0005,\u0001\t\u0005\t\u0015!\u0003(\u0011!a\u0003A!b\u0001\n\u0003i\u0003\u0002\u0003\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0018\t\u0011\r\u0003!Q1A\u0005\u00025B\u0001\u0002\u0012\u0001\u0003\u0002\u0003\u0006IA\f\u0005\t\u000b\u0002\u0011)\u0019!C\u0001[!Aa\t\u0001B\u0001B\u0003%a\u0006\u0003\u0005H\u0001\t\u0015\r\u0011\"\u0001.\u0011!A\u0005A!A!\u0002\u0013q\u0003\u0002C%\u0001\u0005\u000b\u0007I\u0011A\u0017\t\u0011)\u0003!\u0011!Q\u0001\n9B\u0001b\u0013\u0001\u0003\u0006\u0004%\t\u0001\u0014\u0005\t#\u0002\u0011\t\u0011)A\u0005\u001b\"1!\u000b\u0001C\u0001/M\u0013!$\u00119qY&\u001c\u0017\r^5p]\u0016sg/\u001b:p]6,g\u000e^%oM>T!AE\n\u0002\u0005Y\f$B\u0001\u000b\u0016\u0003\r\t\u0007/\u001b\u0006\u0003-]\taa\u001d;biV\u001c(B\u0001\r\u001a\u0003\u0015\u0019\b/\u0019:l\u0015\tQ2$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00029\u0005\u0019qN]4\u0004\u0001M\u0011\u0001a\b\t\u0003A\rj\u0011!\t\u0006\u0002E\u0005)1oY1mC&\u0011A%\t\u0002\u0007\u0003:L(+\u001a4\u0002\u000fI,h\u000e^5nKV\tq\u0005\u0005\u0002)S5\t\u0011#\u0003\u0002+#\tY!+\u001e8uS6,\u0017J\u001c4p\u0003!\u0011XO\u001c;j[\u0016\u0004\u0013aD:qCJ\\\u0007K]8qKJ$\u0018.Z:\u0016\u00039\u00022a\f\u001a5\u001b\u0005\u0001$BA\u0019\"\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003gA\u00121aU3r!\u0011\u0001SgN\u001c\n\u0005Y\n#A\u0002+va2,'\u0007\u0005\u00029\u007f9\u0011\u0011(\u0010\t\u0003u\u0005j\u0011a\u000f\u0006\u0003yu\ta\u0001\u0010:p_Rt\u0014B\u0001 \"\u0003\u0019\u0001&/\u001a3fM&\u0011\u0001)\u0011\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005y\n\u0013\u0001E:qCJ\\\u0007K]8qKJ$\u0018.Z:!\u0003AA\u0017\rZ8paB\u0013x\u000e]3si&,7/A\tiC\u0012|w\u000e\u001d)s_B,'\u000f^5fg\u0002\n\u0001c]=ti\u0016l\u0007K]8qKJ$\u0018.Z:\u0002#ML8\u000f^3n!J|\u0007/\u001a:uS\u0016\u001c\b%A\tnKR\u0014\u0018nY:Qe>\u0004XM\u001d;jKN\f!#\\3ue&\u001c7\u000f\u0015:pa\u0016\u0014H/[3tA\u0005\u00012\r\\1tgB\fG\u000f[#oiJLWm]\u0001\u0012G2\f7o\u001d9bi\",e\u000e\u001e:jKN\u0004\u0013\u0001\u0005:fg>,(oY3Qe>4\u0017\u000e\\3t+\u0005i\u0005cA\u00183\u001dB\u0011\u0001fT\u0005\u0003!F\u00111CU3t_V\u00148-\u001a)s_\u001aLG.Z%oM>\f\u0011C]3t_V\u00148-\u001a)s_\u001aLG.Z:!\u0003\u0019a\u0014N\\5u}QAA+\u0016,X1fS6\f\u0005\u0002)\u0001!)Qe\u0004a\u0001O!)Af\u0004a\u0001]!)1i\u0004a\u0001]!)Qi\u0004a\u0001]!)qi\u0004a\u0001]!)\u0011j\u0004a\u0001]!)1j\u0004a\u0001\u001b\u0002"
)
public class ApplicationEnvironmentInfo {
   private final RuntimeInfo runtime;
   private final Seq sparkProperties;
   private final Seq hadoopProperties;
   private final Seq systemProperties;
   private final Seq metricsProperties;
   private final Seq classpathEntries;
   private final Seq resourceProfiles;

   public RuntimeInfo runtime() {
      return this.runtime;
   }

   public Seq sparkProperties() {
      return this.sparkProperties;
   }

   public Seq hadoopProperties() {
      return this.hadoopProperties;
   }

   public Seq systemProperties() {
      return this.systemProperties;
   }

   public Seq metricsProperties() {
      return this.metricsProperties;
   }

   public Seq classpathEntries() {
      return this.classpathEntries;
   }

   public Seq resourceProfiles() {
      return this.resourceProfiles;
   }

   public ApplicationEnvironmentInfo(final RuntimeInfo runtime, final Seq sparkProperties, final Seq hadoopProperties, final Seq systemProperties, final Seq metricsProperties, final Seq classpathEntries, final Seq resourceProfiles) {
      this.runtime = runtime;
      this.sparkProperties = sparkProperties;
      this.hadoopProperties = hadoopProperties;
      this.systemProperties = systemProperties;
      this.metricsProperties = metricsProperties;
      this.classpathEntries = classpathEntries;
      this.resourceProfiles = resourceProfiles;
   }
}
