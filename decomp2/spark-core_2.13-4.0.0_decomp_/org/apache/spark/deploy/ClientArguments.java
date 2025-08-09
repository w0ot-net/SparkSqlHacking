package org.apache.spark.deploy;

import org.apache.logging.log4j.Level;
import org.apache.spark.util.IntParam$;
import org.apache.spark.util.MemoryParam$;
import scala.Option;
import scala.Predef;
import scala.collection.StringOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e!B\u0017/\u000192\u0004\u0002C\u001f\u0001\u0005\u0003\u0005\u000b\u0011B \t\u000b5\u0003A\u0011\u0001(\t\u000fI\u0003\u0001\u0019!C\u0001'\"9A\u000b\u0001a\u0001\n\u0003)\u0006BB.\u0001A\u0003&!\tC\u0004]\u0001\u0001\u0007I\u0011A/\t\u000f\u0019\u0004\u0001\u0019!C\u0001O\"1\u0011\u000e\u0001Q!\nyCqA\u001b\u0001A\u0002\u0013\u00051\u000eC\u0004m\u0001\u0001\u0007I\u0011A7\t\r=\u0004\u0001\u0015)\u0003@\u0011\u001d\u0001\b\u00011A\u0005\u0002MCq!\u001d\u0001A\u0002\u0013\u0005!\u000f\u0003\u0004u\u0001\u0001\u0006KA\u0011\u0005\bk\u0002\u0001\r\u0011\"\u0001T\u0011\u001d1\b\u00011A\u0005\u0002]Da!\u001f\u0001!B\u0013\u0011\u0005b\u0002>\u0001\u0001\u0004%\ta\u001f\u0005\t\u007f\u0002\u0001\r\u0011\"\u0001\u0002\u0002!9\u0011Q\u0001\u0001!B\u0013a\b\"CA\u0004\u0001\u0001\u0007I\u0011AA\u0005\u0011%\t\t\u0002\u0001a\u0001\n\u0003\t\u0019\u0002\u0003\u0005\u0002\u0018\u0001\u0001\u000b\u0015BA\u0006\u0011%\tI\u0002\u0001a\u0001\n\u0003\tI\u0001C\u0005\u0002\u001c\u0001\u0001\r\u0011\"\u0001\u0002\u001e!A\u0011\u0011\u0005\u0001!B\u0013\tY\u0001C\u0005\u0002$\u0001\u0011\r\u0011\"\u0003\u0002&!A\u0011q\u0007\u0001!\u0002\u0013\t9\u0003C\u0004\u0002:\u0001!\t!a\u000f\t\u0011\u0005=\u0003\u00011A\u0005\u0002MC\u0011\"!\u0015\u0001\u0001\u0004%\t!a\u0015\t\u000f\u0005]\u0003\u0001)Q\u0005\u0005\"9\u0011\u0011\f\u0001\u0005\n\u0005m\u0003bBA:\u0001\u0011%\u0011QO\u0004\t\u0003wr\u0003\u0012\u0001\u0018\u0002~\u00199QF\fE\u0001]\u0005}\u0004BB'%\t\u0003\t\t\tC\u0005\u0002\u0004\u0012\u0012\r\u0011\"\u0001\u0002\n!A\u0011Q\u0011\u0013!\u0002\u0013\tY\u0001C\u0005\u0002\b\u0012\u0012\r\u0011\"\u0001\u0002\n!A\u0011\u0011\u0012\u0013!\u0002\u0013\tY\u0001\u0003\u0005\u0002\f\u0012\u0012\r\u0011\"\u0001|\u0011\u001d\ti\t\nQ\u0001\nqDq!a$%\t\u0003\t\tJA\bDY&,g\u000e^!sOVlWM\u001c;t\u0015\ty\u0003'\u0001\u0004eKBdw.\u001f\u0006\u0003cI\nQa\u001d9be.T!a\r\u001b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0014aA8sON\u0011\u0001a\u000e\t\u0003qmj\u0011!\u000f\u0006\u0002u\u0005)1oY1mC&\u0011A(\u000f\u0002\u0007\u0003:L(+\u001a4\u0002\t\u0005\u0014xm]\u0002\u0001!\rA\u0004IQ\u0005\u0003\u0003f\u0012Q!\u0011:sCf\u0004\"a\u0011&\u000f\u0005\u0011C\u0005CA#:\u001b\u00051%BA$?\u0003\u0019a$o\\8u}%\u0011\u0011*O\u0001\u0007!J,G-\u001a4\n\u0005-c%AB*ue&twM\u0003\u0002Js\u00051A(\u001b8jiz\"\"aT)\u0011\u0005A\u0003Q\"\u0001\u0018\t\u000bu\u0012\u0001\u0019A \u0002\u0007\rlG-F\u0001C\u0003\u001d\u0019W\u000eZ0%KF$\"AV-\u0011\u0005a:\u0016B\u0001-:\u0005\u0011)f.\u001b;\t\u000fi#\u0011\u0011!a\u0001\u0005\u0006\u0019\u0001\u0010J\u0019\u0002\t\rlG\rI\u0001\tY><G*\u001a<fYV\ta\f\u0005\u0002`I6\t\u0001M\u0003\u0002bE\u0006)An\\45U*\u00111MM\u0001\bY><w-\u001b8h\u0013\t)\u0007MA\u0003MKZ,G.\u0001\u0007m_\u001edUM^3m?\u0012*\u0017\u000f\u0006\u0002WQ\"9!lBA\u0001\u0002\u0004q\u0016!\u00037pO2+g/\u001a7!\u0003\u001di\u0017m\u001d;feN,\u0012aP\u0001\f[\u0006\u001cH/\u001a:t?\u0012*\u0017\u000f\u0006\u0002W]\"9!LCA\u0001\u0002\u0004y\u0014\u0001C7bgR,'o\u001d\u0011\u0002\r)\f'/\u0016:m\u0003)Q\u0017M]+sY~#S-\u001d\u000b\u0003-NDqAW\u0007\u0002\u0002\u0003\u0007!)A\u0004kCJ,&\u000f\u001c\u0011\u0002\u00135\f\u0017N\\\"mCN\u001c\u0018!D7bS:\u001cE.Y:t?\u0012*\u0017\u000f\u0006\u0002Wq\"9!\fEA\u0001\u0002\u0004\u0011\u0015AC7bS:\u001cE.Y:tA\u0005I1/\u001e9feZL7/Z\u000b\u0002yB\u0011\u0001(`\u0005\u0003}f\u0012qAQ8pY\u0016\fg.A\u0007tkB,'O^5tK~#S-\u001d\u000b\u0004-\u0006\r\u0001b\u0002.\u0014\u0003\u0003\u0005\r\u0001`\u0001\u000bgV\u0004XM\u001d<jg\u0016\u0004\u0013AB7f[>\u0014\u00180\u0006\u0002\u0002\fA\u0019\u0001(!\u0004\n\u0007\u0005=\u0011HA\u0002J]R\f!\"\\3n_JLx\fJ3r)\r1\u0016Q\u0003\u0005\t5Z\t\t\u00111\u0001\u0002\f\u00059Q.Z7pef\u0004\u0013!B2pe\u0016\u001c\u0018!C2pe\u0016\u001cx\fJ3r)\r1\u0016q\u0004\u0005\t5f\t\t\u00111\u0001\u0002\f\u000511m\u001c:fg\u0002\nab\u00183sSZ,'o\u00149uS>t7/\u0006\u0002\u0002(A)\u0011\u0011FA\u001a\u00056\u0011\u00111\u0006\u0006\u0005\u0003[\ty#A\u0004nkR\f'\r\\3\u000b\u0007\u0005E\u0012(\u0001\u0006d_2dWm\u0019;j_:LA!!\u000e\u0002,\tQA*[:u\u0005V4g-\u001a:\u0002\u001f}#'/\u001b<fe>\u0003H/[8og\u0002\nQ\u0002\u001a:jm\u0016\u0014x\n\u001d;j_:\u001cXCAA\u001f!\u0015\ty$!\u0013C\u001d\u0011\t\t%!\u0012\u000f\u0007\u0015\u000b\u0019%C\u0001;\u0013\r\t9%O\u0001\ba\u0006\u001c7.Y4f\u0013\u0011\tY%!\u0014\u0003\u0007M+\u0017OC\u0002\u0002He\n\u0001\u0002\u001a:jm\u0016\u0014\u0018\nZ\u0001\rIJLg/\u001a:JI~#S-\u001d\u000b\u0004-\u0006U\u0003b\u0002. \u0003\u0003\u0005\rAQ\u0001\nIJLg/\u001a:JI\u0002\nQ\u0001]1sg\u0016$2AVA/\u0011\u0019i\u0014\u00051\u0001\u0002`A)\u0011qHA1\u0005&!\u00111MA'\u0005\u0011a\u0015n\u001d;)\u0007\u0005\n9\u0007\u0005\u0003\u0002j\u0005=TBAA6\u0015\r\ti'O\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA9\u0003W\u0012q\u0001^1jYJ,7-A\tqe&tG/V:bO\u0016\fe\u000eZ#ySR$2AVA<\u0011\u001d\tIH\ta\u0001\u0003\u0017\t\u0001\"\u001a=ji\u000e{G-Z\u0001\u0010\u00072LWM\u001c;Be\u001e,X.\u001a8ugB\u0011\u0001\u000bJ\n\u0003I]\"\"!! \u0002\u001b\u0011+e)Q+M)~\u001buJU#T\u00039!UIR!V\u0019R{6i\u0014*F'\u0002\na\u0002R#G\u0003VcEkX'F\u001b>\u0013\u0016,A\bE\u000b\u001a\u000bU\u000b\u0014+`\u001b\u0016kuJU-!\u0003E!UIR!V\u0019R{6+\u0016)F%ZK5+R\u0001\u0013\t\u00163\u0015)\u0016'U?N+\u0006+\u0012*W\u0013N+\u0005%A\u0007jgZ\u000bG.\u001b3KCJ,&\u000f\u001c\u000b\u0004y\u0006M\u0005BBAKY\u0001\u0007!)A\u0001t\u0001"
)
public class ClientArguments {
   private String cmd = "";
   private Level logLevel;
   private String[] masters;
   private String jarUrl;
   private String mainClass;
   private boolean supervise;
   private int memory;
   private int cores;
   private final ListBuffer _driverOptions;
   private String driverId;

   public static boolean isValidJarUrl(final String s) {
      return ClientArguments$.MODULE$.isValidJarUrl(s);
   }

   public static boolean DEFAULT_SUPERVISE() {
      return ClientArguments$.MODULE$.DEFAULT_SUPERVISE();
   }

   public static int DEFAULT_MEMORY() {
      return ClientArguments$.MODULE$.DEFAULT_MEMORY();
   }

   public static int DEFAULT_CORES() {
      return ClientArguments$.MODULE$.DEFAULT_CORES();
   }

   public String cmd() {
      return this.cmd;
   }

   public void cmd_$eq(final String x$1) {
      this.cmd = x$1;
   }

   public Level logLevel() {
      return this.logLevel;
   }

   public void logLevel_$eq(final Level x$1) {
      this.logLevel = x$1;
   }

   public String[] masters() {
      return this.masters;
   }

   public void masters_$eq(final String[] x$1) {
      this.masters = x$1;
   }

   public String jarUrl() {
      return this.jarUrl;
   }

   public void jarUrl_$eq(final String x$1) {
      this.jarUrl = x$1;
   }

   public String mainClass() {
      return this.mainClass;
   }

   public void mainClass_$eq(final String x$1) {
      this.mainClass = x$1;
   }

   public boolean supervise() {
      return this.supervise;
   }

   public void supervise_$eq(final boolean x$1) {
      this.supervise = x$1;
   }

   public int memory() {
      return this.memory;
   }

   public void memory_$eq(final int x$1) {
      this.memory = x$1;
   }

   public int cores() {
      return this.cores;
   }

   public void cores_$eq(final int x$1) {
      this.cores = x$1;
   }

   private ListBuffer _driverOptions() {
      return this._driverOptions;
   }

   public Seq driverOptions() {
      return this._driverOptions().toSeq();
   }

   public String driverId() {
      return this.driverId;
   }

   public void driverId_$eq(final String x$1) {
      this.driverId = x$1;
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
            if (("--cores".equals(var12) ? true : "-c".equals(var12)) && var13 instanceof .colon.colon) {
               .colon.colon var14 = (.colon.colon)var13;
               String var15 = (String)var14.head();
               List tail = var14.next$access$1();
               if (var15 != null) {
                  Option var17 = IntParam$.MODULE$.unapply(var15);
                  if (!var17.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var17.get());
                     this.cores_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var9) {
            String var19 = (String)var10.head();
            List var20 = var10.next$access$1();
            if (("--memory".equals(var19) ? true : "-m".equals(var19)) && var20 instanceof .colon.colon) {
               .colon.colon var21 = (.colon.colon)var20;
               String var22 = (String)var21.head();
               List tail = var21.next$access$1();
               if (var22 != null) {
                  Option var24 = MemoryParam$.MODULE$.unapply(var22);
                  if (!var24.isEmpty()) {
                     int value = BoxesRunTime.unboxToInt(var24.get());
                     this.memory_$eq(value);
                     args = tail;
                     continue;
                  }
               }
            }
         }

         if (var9) {
            String var26 = (String)var10.head();
            List tail = var10.next$access$1();
            if ("--supervise".equals(var26) ? true : "-s".equals(var26)) {
               this.supervise_$eq(true);
               args = tail;
               continue;
            }
         }

         label151: {
            if (var9) {
               String var28 = (String)var10.head();
               if ("--help".equals(var28) ? true : "-h".equals(var28)) {
                  this.printUsageAndExit(0);
                  BoxedUnit var51 = BoxedUnit.UNIT;
                  break label151;
               }
            }

            if (var9) {
               String var29 = (String)var10.head();
               List tail = var10.next$access$1();
               if ("--verbose".equals(var29) ? true : "-v".equals(var29)) {
                  this.logLevel_$eq(Level.INFO);
                  args = tail;
                  continue;
               }
            }

            if (var9) {
               String var31 = (String)var10.head();
               List var32 = var10.next$access$1();
               if ("launch".equals(var31) && var32 instanceof .colon.colon) {
                  .colon.colon var33 = (.colon.colon)var32;
                  String _master = (String)var33.head();
                  List var35 = var33.next$access$1();
                  if (var35 instanceof .colon.colon) {
                     .colon.colon var36 = (.colon.colon)var35;
                     String _jarUrl = (String)var36.head();
                     List var38 = var36.next$access$1();
                     if (var38 instanceof .colon.colon) {
                        .colon.colon var39 = (.colon.colon)var38;
                        String _mainClass = (String)var39.head();
                        List tail = var39.next$access$1();
                        this.cmd_$eq("launch");
                        if (!ClientArguments$.MODULE$.isValidJarUrl(_jarUrl)) {
                           scala.Predef..MODULE$.println("Jar url '" + _jarUrl + "' is not in valid format.");
                           scala.Predef..MODULE$.println("Must be a jar file path in URL format (e.g. hdfs://host:port/XX.jar, file:///XX.jar)");
                           this.printUsageAndExit(-1);
                        }

                        this.jarUrl_$eq(_jarUrl);
                        this.masters_$eq(org.apache.spark.util.Utils$.MODULE$.parseStandaloneMasterUrls(_master));
                        this.mainClass_$eq(_mainClass);
                        this._driverOptions().$plus$plus$eq(tail);
                        BoxedUnit var50 = BoxedUnit.UNIT;
                        break label151;
                     }
                  }
               }
            }

            if (var9) {
               String var42 = (String)var10.head();
               List var43 = var10.next$access$1();
               if ("kill".equals(var42) && var43 instanceof .colon.colon) {
                  .colon.colon var44 = (.colon.colon)var43;
                  String _master = (String)var44.head();
                  List var46 = var44.next$access$1();
                  if (var46 instanceof .colon.colon) {
                     .colon.colon var47 = (.colon.colon)var46;
                     String _driverId = (String)var47.head();
                     this.cmd_$eq("kill");
                     this.masters_$eq(org.apache.spark.util.Utils$.MODULE$.parseStandaloneMasterUrls(_master));
                     this.driverId_$eq(_driverId);
                     BoxedUnit var49 = BoxedUnit.UNIT;
                     break label151;
                  }
               }
            }

            this.printUsageAndExit(1);
            BoxedUnit var10000 = BoxedUnit.UNIT;
         }

         BoxedUnit var52 = BoxedUnit.UNIT;
         return;
      }
   }

   private void printUsageAndExit(final int exitCode) {
      StringOps var10000 = scala.collection.StringOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      int var10002 = ClientArguments$.MODULE$.DEFAULT_CORES();
      String usage = var10000.stripMargin$extension(var10001.augmentString("\n      |Usage: DriverClient [options] launch <active-master> <jar-url> <main-class> [driver options]\n      |Usage: DriverClient kill <active-master> <driver-id>\n      |\n      |Options:\n      |   -c CORES, --cores CORES        Number of cores to request (default: " + var10002 + ")\n      |   -m MEMORY, --memory MEMORY     Megabytes of memory to request (default: " + ClientArguments$.MODULE$.DEFAULT_MEMORY() + ")\n      |   -s, --supervise                Whether to restart the driver on failure\n      |                                  (default: " + ClientArguments$.MODULE$.DEFAULT_SUPERVISE() + ")\n      |   -v, --verbose                  Print more debugging output\n     "));
      System.err.println(usage);
      System.exit(exitCode);
   }

   public ClientArguments(final String[] args) {
      this.logLevel = Level.WARN;
      this.masters = null;
      this.jarUrl = "";
      this.mainClass = "";
      this.supervise = ClientArguments$.MODULE$.DEFAULT_SUPERVISE();
      this.memory = ClientArguments$.MODULE$.DEFAULT_MEMORY();
      this.cores = ClientArguments$.MODULE$.DEFAULT_CORES();
      this._driverOptions = (ListBuffer)scala.collection.mutable.ListBuffer..MODULE$.apply(scala.collection.immutable.Nil..MODULE$);
      this.driverId = "";
      this.parse(scala.Predef..MODULE$.wrapRefArray((Object[])args).toList());
   }
}
