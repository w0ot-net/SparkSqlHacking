package org.apache.spark.deploy.worker;

import java.lang.invoke.SerializedLambda;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import org.apache.spark.SparkConf;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.util.IntParam$;
import org.apache.spark.util.MemoryParam$;
import org.apache.spark.util.Utils$;
import scala.Option;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005eb!B\u0011#\u0001\tb\u0003\u0002C\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001b\t\u0011\r\u0003!\u0011!Q\u0001\n\u0011CQ\u0001\u0013\u0001\u0005\u0002%CqA\u0014\u0001A\u0002\u0013\u0005q\nC\u0004Q\u0001\u0001\u0007I\u0011A)\t\r]\u0003\u0001\u0015)\u00039\u0011\u001dA\u0006\u00011A\u0005\u0002eCq!\u0018\u0001A\u0002\u0013\u0005a\f\u0003\u0004a\u0001\u0001\u0006KA\u0017\u0005\bC\u0002\u0001\r\u0011\"\u0001Z\u0011\u001d\u0011\u0007\u00011A\u0005\u0002\rDa!\u001a\u0001!B\u0013Q\u0006b\u00024\u0001\u0001\u0004%\t!\u0017\u0005\bO\u0002\u0001\r\u0011\"\u0001i\u0011\u0019Q\u0007\u0001)Q\u00055\"91\u000e\u0001a\u0001\n\u0003I\u0006b\u00027\u0001\u0001\u0004%\t!\u001c\u0005\u0007_\u0002\u0001\u000b\u0015\u0002.\t\u000fA\u0004\u0001\u0019!C\u0001c\"9!\u000f\u0001a\u0001\n\u0003\u0019\bBB;\u0001A\u0003&Q\u0007C\u0004w\u0001\u0001\u0007I\u0011A(\t\u000f]\u0004\u0001\u0019!C\u0001q\"1!\u0010\u0001Q!\naBqa\u001f\u0001A\u0002\u0013\u0005q\nC\u0004}\u0001\u0001\u0007I\u0011A?\t\r}\u0004\u0001\u0015)\u00039\u0011\u001d\t\t\u0001\u0001C\u0005\u0003\u0007Aq!a\n\u0001\t\u0003\tI\u0003C\u0004\u00020\u0001!\t!!\r\t\u000f\u0005M\u0002\u0001\"\u0001\u00022!9\u0011Q\u0007\u0001\u0005\u0002\u0005]\"aD,pe.,'/\u0011:hk6,g\u000e^:\u000b\u0005\r\"\u0013AB<pe.,'O\u0003\u0002&M\u00051A-\u001a9m_fT!a\n\u0015\u0002\u000bM\u0004\u0018M]6\u000b\u0005%R\u0013AB1qC\u000eDWMC\u0001,\u0003\ry'oZ\n\u0003\u00015\u0002\"AL\u0019\u000e\u0003=R\u0011\u0001M\u0001\u0006g\u000e\fG.Y\u0005\u0003e=\u0012a!\u00118z%\u00164\u0017\u0001B1sON\u001c\u0001\u0001E\u0002/maJ!aN\u0018\u0003\u000b\u0005\u0013(/Y=\u0011\u0005e\u0002eB\u0001\u001e?!\tYt&D\u0001=\u0015\tiD'\u0001\u0004=e>|GOP\u0005\u0003\u007f=\na\u0001\u0015:fI\u00164\u0017BA!C\u0005\u0019\u0019FO]5oO*\u0011qhL\u0001\u0005G>tg\r\u0005\u0002F\r6\ta%\u0003\u0002HM\tI1\u000b]1sW\u000e{gNZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0007)cU\n\u0005\u0002L\u00015\t!\u0005C\u00034\u0007\u0001\u0007Q\u0007C\u0003D\u0007\u0001\u0007A)\u0001\u0003i_N$X#\u0001\u001d\u0002\u0011!|7\u000f^0%KF$\"AU+\u0011\u00059\u001a\u0016B\u0001+0\u0005\u0011)f.\u001b;\t\u000fY+\u0011\u0011!a\u0001q\u0005\u0019\u0001\u0010J\u0019\u0002\u000b!|7\u000f\u001e\u0011\u0002\tA|'\u000f^\u000b\u00025B\u0011afW\u0005\u00039>\u00121!\u00138u\u0003!\u0001xN\u001d;`I\u0015\fHC\u0001*`\u0011\u001d1\u0006\"!AA\u0002i\u000bQ\u0001]8si\u0002\n\u0011b^3c+&\u0004vN\u001d;\u0002\u001b],'-V5Q_J$x\fJ3r)\t\u0011F\rC\u0004W\u0017\u0005\u0005\t\u0019\u0001.\u0002\u0015],'-V5Q_J$\b%A\u0003d_J,7/A\u0005d_J,7o\u0018\u0013fcR\u0011!+\u001b\u0005\b-:\t\t\u00111\u0001[\u0003\u0019\u0019wN]3tA\u00051Q.Z7pef\f!\"\\3n_JLx\fJ3r)\t\u0011f\u000eC\u0004W#\u0005\u0005\t\u0019\u0001.\u0002\u000f5,Wn\u001c:zA\u00059Q.Y:uKJ\u001cX#A\u001b\u0002\u00175\f7\u000f^3sg~#S-\u001d\u000b\u0003%RDqA\u0016\u000b\u0002\u0002\u0003\u0007Q'\u0001\u0005nCN$XM]:!\u0003\u001d9xN]6ESJ\f1b^8sW\u0012K'o\u0018\u0013fcR\u0011!+\u001f\u0005\b-^\t\t\u00111\u00019\u0003!9xN]6ESJ\u0004\u0013A\u00049s_B,'\u000f^5fg\u001aKG.Z\u0001\u0013aJ|\u0007/\u001a:uS\u0016\u001ch)\u001b7f?\u0012*\u0017\u000f\u0006\u0002S}\"9aKGA\u0001\u0002\u0004A\u0014a\u00049s_B,'\u000f^5fg\u001aKG.\u001a\u0011\u0002\u000bA\f'o]3\u0015\u0007I\u000b)\u0001\u0003\u000449\u0001\u0007\u0011q\u0001\t\u0006\u0003\u0013\t\u0019\u0002\u000f\b\u0005\u0003\u0017\tyAD\u0002<\u0003\u001bI\u0011\u0001M\u0005\u0004\u0003#y\u0013a\u00029bG.\fw-Z\u0005\u0005\u0003+\t9B\u0001\u0003MSN$(bAA\t_!\u001aA$a\u0007\u0011\t\u0005u\u00111E\u0007\u0003\u0003?Q1!!\t0\u0003)\tgN\\8uCRLwN\\\u0005\u0005\u0003K\tyBA\u0004uC&d'/Z2\u0002#A\u0014\u0018N\u001c;Vg\u0006<W-\u00118e\u000bbLG\u000fF\u0002S\u0003WAa!!\f\u001e\u0001\u0004Q\u0016\u0001C3ySR\u001cu\u000eZ3\u0002#%tg-\u001a:EK\u001a\fW\u000f\u001c;D_J,7\u000fF\u0001[\u0003IIgNZ3s\t\u00164\u0017-\u001e7u\u001b\u0016lwN]=\u0002#\rDWmY6X_J\\WM]'f[>\u0014\u0018\u0010F\u0001S\u0001"
)
public class WorkerArguments {
   private String host;
   private int port;
   private int webUiPort;
   private int cores;
   private int memory;
   private String[] masters;
   private String workDir;
   private String propertiesFile;

   public String host() {
      return this.host;
   }

   public void host_$eq(final String x$1) {
      this.host = x$1;
   }

   public int port() {
      return this.port;
   }

   public void port_$eq(final int x$1) {
      this.port = x$1;
   }

   public int webUiPort() {
      return this.webUiPort;
   }

   public void webUiPort_$eq(final int x$1) {
      this.webUiPort = x$1;
   }

   public int cores() {
      return this.cores;
   }

   public void cores_$eq(final int x$1) {
      this.cores = x$1;
   }

   public int memory() {
      return this.memory;
   }

   public void memory_$eq(final int x$1) {
      this.memory = x$1;
   }

   public String[] masters() {
      return this.masters;
   }

   public void masters_$eq(final String[] x$1) {
      this.masters = x$1;
   }

   public String workDir() {
      return this.workDir;
   }

   public void workDir_$eq(final String x$1) {
      this.workDir = x$1;
   }

   public String propertiesFile() {
      return this.propertiesFile;
   }

   public void propertiesFile_$eq(final String x$1) {
      this.propertiesFile = x$1;
   }

   private void parse(final List args) {
      while(true) {
         boolean var9 = false;
         .colon.colon var10 = null;
         if (args instanceof .colon.colon) {
            var9 = true;
            var10 = (.colon.colon)args;
            String var12 = (String)var10.head();
            List var13 = var10.next$access$1();
            if (("--host".equals(var12) ? true : "-h".equals(var12)) && var13 instanceof .colon.colon) {
               .colon.colon var14 = (.colon.colon)var13;
               String value = (String)var14.head();
               List tail = var14.next$access$1();
               Utils$.MODULE$.checkHost(value);
               this.host_$eq(value);
               args = tail;
               continue;
            }
         }

         if (var9) {
            String var17 = (String)var10.head();
            List var18 = var10.next$access$1();
            if (("--port".equals(var17) ? true : "-p".equals(var17)) && var18 instanceof .colon.colon) {
               .colon.colon var19 = (.colon.colon)var18;
               String var20 = (String)var19.head();
               List tail = var19.next$access$1();
               if (var20 != null) {
                  Option var22 = IntParam$.MODULE$.unapply(var20);
                  if (!var22.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var22.get());
                     this.port_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var9) {
            String var24 = (String)var10.head();
            List var25 = var10.next$access$1();
            if (("--cores".equals(var24) ? true : "-c".equals(var24)) && var25 instanceof .colon.colon) {
               .colon.colon var26 = (.colon.colon)var25;
               String var27 = (String)var26.head();
               List tail = var26.next$access$1();
               if (var27 != null) {
                  Option var29 = IntParam$.MODULE$.unapply(var27);
                  if (!var29.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var29.get());
                     this.cores_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var9) {
            String var31 = (String)var10.head();
            List var32 = var10.next$access$1();
            if (("--memory".equals(var31) ? true : "-m".equals(var31)) && var32 instanceof .colon.colon) {
               .colon.colon var33 = (.colon.colon)var32;
               String var34 = (String)var33.head();
               List tail = var33.next$access$1();
               if (var34 != null) {
                  Option var36 = MemoryParam$.MODULE$.unapply(var34);
                  if (!var36.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var36.get());
                     this.memory_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var9) {
            String var38 = (String)var10.head();
            List var39 = var10.next$access$1();
            if (("--work-dir".equals(var38) ? true : "-d".equals(var38)) && var39 instanceof .colon.colon) {
               .colon.colon var40 = (.colon.colon)var39;
               String value = (String)var40.head();
               List tail = var40.next$access$1();
               this.workDir_$eq(value);
               args = tail;
               continue;
            }
         }

         if (var9) {
            String var43 = (String)var10.head();
            List var44 = var10.next$access$1();
            if ("--webui-port".equals(var43) && var44 instanceof .colon.colon) {
               .colon.colon var45 = (.colon.colon)var44;
               String var46 = (String)var45.head();
               List tail = var45.next$access$1();
               if (var46 != null) {
                  Option var48 = IntParam$.MODULE$.unapply(var46);
                  if (!var48.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var48.get());
                     this.webUiPort_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var9) {
            String var50 = (String)var10.head();
            List var51 = var10.next$access$1();
            if ("--properties-file".equals(var50) && var51 instanceof .colon.colon) {
               .colon.colon var52 = (.colon.colon)var51;
               String value = (String)var52.head();
               List tail = var52.next$access$1();
               this.propertiesFile_$eq(value);
               args = tail;
               continue;
            }
         }

         label178: {
            if (var9) {
               String var55 = (String)var10.head();
               if ("--help".equals(var55)) {
                  this.printUsageAndExit(0);
                  BoxedUnit var60 = BoxedUnit.UNIT;
                  break label178;
               }
            }

            if (var9) {
               String value = (String)var10.head();
               List tail = var10.next$access$1();
               if (this.masters() != null) {
                  this.printUsageAndExit(1);
               }

               this.masters_$eq(Utils$.MODULE$.parseStandaloneMasterUrls(value));
               args = tail;
               continue;
            }

            if (scala.collection.immutable.Nil..MODULE$.equals(args)) {
               if (this.masters() == null) {
                  this.printUsageAndExit(1);
                  BoxedUnit var10000 = BoxedUnit.UNIT;
               } else {
                  BoxedUnit var58 = BoxedUnit.UNIT;
               }
            } else {
               this.printUsageAndExit(1);
               BoxedUnit var59 = BoxedUnit.UNIT;
            }
         }

         BoxedUnit var61 = BoxedUnit.UNIT;
         return;
      }
   }

   public void printUsageAndExit(final int exitCode) {
      System.err.println("Usage: Worker [options] <master>\n\nMaster must be a URL of the form spark://hostname:port\n\nOptions:\n  -c CORES, --cores CORES  Number of cores to use\n  -m MEM, --memory MEM     Amount of memory to use (e.g. 1000M, 2G)\n  -d DIR, --work-dir DIR   Directory to run apps in (default: SPARK_HOME/work)\n  -h HOST, --host HOST     Hostname to listen on\n  -p PORT, --port PORT     Port to listen on (default: random)\n  --webui-port PORT        Port for web UI (default: 8081)\n  --properties-file FILE   Path to a custom Spark properties file.\n                           Default is conf/spark-defaults.conf.");
      System.exit(exitCode);
   }

   public int inferDefaultCores() {
      return Runtime.getRuntime().availableProcessors();
   }

   public int inferDefaultMemory() {
      int totalMb = 0;

      try {
         OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();
         Class beanClass = Class.forName("com.sun.management.OperatingSystemMXBean");
         Method method = beanClass.getDeclaredMethod("getTotalMemorySize");
         totalMb = (int)(BoxesRunTime.unboxToLong(method.invoke(bean)) / 1024L / 1024L);
      } catch (Exception var6) {
         totalMb = 2048;
         System.out.println("Failed to get total physical memory. Using " + totalMb + " MB");
      }

      return scala.math.package..MODULE$.max(totalMb - 1024, Utils$.MODULE$.DEFAULT_DRIVER_MEM_MB());
   }

   public void checkWorkerMemory() {
      if (this.memory() <= 0) {
         String message = "Memory is below 1MB, or missing a M/G at the end of the memory specification?";
         throw new IllegalStateException(message);
      }
   }

   public WorkerArguments(final String[] args, final SparkConf conf) {
      this.host = Utils$.MODULE$.localHostName();
      this.port = 0;
      this.webUiPort = 8081;
      this.cores = this.inferDefaultCores();
      this.memory = this.inferDefaultMemory();
      this.masters = null;
      this.workDir = null;
      this.propertiesFile = null;
      if (System.getenv("SPARK_WORKER_PORT") != null) {
         this.port_$eq(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(System.getenv("SPARK_WORKER_PORT"))));
      }

      if (System.getenv("SPARK_WORKER_CORES") != null) {
         this.cores_$eq(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(System.getenv("SPARK_WORKER_CORES"))));
      }

      if (conf.getenv("SPARK_WORKER_MEMORY") != null) {
         this.memory_$eq(Utils$.MODULE$.memoryStringToMb(conf.getenv("SPARK_WORKER_MEMORY")));
      }

      if (System.getenv("SPARK_WORKER_WEBUI_PORT") != null) {
         this.webUiPort_$eq(scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(System.getenv("SPARK_WORKER_WEBUI_PORT"))));
      }

      if (System.getenv("SPARK_WORKER_DIR") != null) {
         this.workDir_$eq(System.getenv("SPARK_WORKER_DIR"));
      }

      this.parse(scala.Predef..MODULE$.wrapRefArray((Object[])args).toList());
      this.propertiesFile_$eq(Utils$.MODULE$.loadDefaultSparkProperties(conf, this.propertiesFile()));
      Utils$.MODULE$.resetStructuredLogging(conf);
      org.apache.spark.internal.Logging..MODULE$.uninitialize();
      ((Option)conf.get((ConfigEntry)org.apache.spark.internal.config.Worker$.MODULE$.WORKER_UI_PORT())).foreach((JFunction1.mcVI.sp)(x$1) -> this.webUiPort_$eq(x$1));
      this.checkWorkerMemory();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
