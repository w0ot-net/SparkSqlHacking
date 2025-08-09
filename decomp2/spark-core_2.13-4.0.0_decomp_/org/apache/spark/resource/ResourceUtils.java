package org.apache.spark.resource;

import org.apache.spark.SparkConf;
import org.apache.spark.internal.Logging;
import scala.Function1;
import scala.Option;
import scala.StringContext;
import scala.Tuple2;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t5tA\u0002\u0014(\u0011\u0003IsF\u0002\u00042O!\u0005\u0011F\r\u0005\u0006\u007f\u0005!\t!\u0011\u0005\b\u0005\u0006\u0011\r\u0011\"\u0001D\u0011\u0019a\u0015\u0001)A\u0005\t\"9Q*\u0001b\u0001\n\u0003\u0019\u0005B\u0002(\u0002A\u0003%A\tC\u0004P\u0003\t\u0007I\u0011A\"\t\rA\u000b\u0001\u0015!\u0003E\u0011\u0015\t\u0016\u0001\"\u0001S\u0011\u0015\t\u0017\u0001\"\u0001c\u0011\u0015I\u0018\u0001\"\u0001{\u0011\u0015q\u0018\u0001\"\u0001\u0000\u0011\u001d\t9\"\u0001C\u0001\u00033Aq!!\f\u0002\t\u0003\ty\u0003C\u0004\u0002>\u0005!\t!a\u0010\t\u000f\u00055\u0013\u0001\"\u0001\u0002P!9\u0011QM\u0001\u0005\u0002\u0005\u001d\u0004bBAJ\u0003\u0011\u0005\u0011Q\u0013\u0005\b\u0003C\u000bA\u0011AAR\u0011\u001d\t\t,\u0001C\u0005\u0003gCq!a/\u0002\t\u0013\ti\fC\u0004\u0002H\u0006!I!!3\t\u000f\u0005M\u0017\u0001\"\u0003\u0002V\"9\u0011q]\u0001\u0005\u0002\u0005%\bbBAy\u0003\u0011%\u00111\u001f\u0005\b\u0005\u000b\tA\u0011\u0001B\u0004\u0011\u001d\u0011I\"\u0001C\u0001\u00057A\u0001Ba\t\u0002\t\u0003I#Q\u0005\u0005\b\u0005W\tA\u0011\u0001B\u0017\u0011\u001d\u0011I$\u0001C\u0001\u0005wA\u0011Ba\u0012\u0002#\u0003%\tA!\u0013\t\u0013\t}\u0013A1A\u0005\u0006\t\u0005\u0004b\u0002B2\u0003\u0001\u0006iA\u001d\u0005\n\u0005K\n!\u0019!C\u0003\u0005CBqAa\u001a\u0002A\u00035!\u000fC\u0005\u0003j\u0005\u0011\r\u0011\"\u0002\u0003b!9!1N\u0001!\u0002\u001b\u0011\u0018!\u0004*fg>,(oY3Vi&d7O\u0003\u0002)S\u0005A!/Z:pkJ\u001cWM\u0003\u0002+W\u0005)1\u000f]1sW*\u0011A&L\u0001\u0007CB\f7\r[3\u000b\u00039\n1a\u001c:h!\t\u0001\u0014!D\u0001(\u00055\u0011Vm]8ve\u000e,W\u000b^5mgN\u0019\u0011aM\u001d\u0011\u0005Q:T\"A\u001b\u000b\u0003Y\nQa]2bY\u0006L!\u0001O\u001b\u0003\r\u0005s\u0017PU3g!\tQT(D\u0001<\u0015\ta\u0014&\u0001\u0005j]R,'O\\1m\u0013\tq4HA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012aL\u0001\u0011\t&\u001b6i\u0014,F%f{6k\u0011*J!R+\u0012\u0001\u0012\t\u0003\u000b*k\u0011A\u0012\u0006\u0003\u000f\"\u000bA\u0001\\1oO*\t\u0011*\u0001\u0003kCZ\f\u0017BA&G\u0005\u0019\u0019FO]5oO\u0006\tB)S*D\u001fZ+%+W0T\u0007JK\u0005\u000b\u0016\u0011\u0002\rY+e\nR(S\u0003\u001d1VI\u0014#P%\u0002\na!Q'P+:#\u0016aB!N\u001fVsE\u000bI\u0001\u0015a\u0006\u00148/\u001a*fg>,(oY3SKF,Xm\u001d;\u0015\u0007M3F\f\u0005\u00021)&\u0011Qk\n\u0002\u0010%\u0016\u001cx.\u001e:dKJ+\u0017/^3ti\")q+\u0003a\u00011\u0006I1\u000f]1sW\u000e{gN\u001a\t\u00033jk\u0011!K\u0005\u00037&\u0012\u0011b\u00159be.\u001cuN\u001c4\t\u000buK\u0001\u0019\u00010\u0002\u0015I,7o\\;sG\u0016LE\r\u0005\u00021?&\u0011\u0001m\n\u0002\u000b%\u0016\u001cx.\u001e:dK&#\u0015a\u00047jgR\u0014Vm]8ve\u000e,\u0017\nZ:\u0015\u0007\r|\u0007\u000fE\u0002eYzs!!\u001a6\u000f\u0005\u0019LW\"A4\u000b\u0005!\u0004\u0015A\u0002\u001fs_>$h(C\u00017\u0013\tYW'A\u0004qC\u000e\\\u0017mZ3\n\u00055t'aA*fc*\u00111.\u000e\u0005\u0006/*\u0001\r\u0001\u0017\u0005\u0006c*\u0001\rA]\u0001\u000eG>l\u0007o\u001c8f]Rt\u0015-\\3\u0011\u0005M<hB\u0001;v!\t1W'\u0003\u0002wk\u00051\u0001K]3eK\u001aL!a\u0013=\u000b\u0005Y,\u0014\u0001\u00079beN,\u0017\t\u001c7SKN|WO]2f%\u0016\fX/Z:ugR\u00191\u0010`?\u0011\u0007\u0011d7\u000bC\u0003X\u0017\u0001\u0007\u0001\fC\u0003r\u0017\u0001\u0007!/\u0001\u0012dC2\u001cW\u000f\\1uK\u0006kw.\u001e8u\u0003:$\u0007+\u0019:ug\u001a{'O\u0012:bGRLwN\u001c\u000b\u0005\u0003\u0003\ti\u0001E\u00045\u0003\u0007\t9!a\u0002\n\u0007\u0005\u0015QG\u0001\u0004UkBdWM\r\t\u0004i\u0005%\u0011bAA\u0006k\t\u0019\u0011J\u001c;\t\u000f\u0005=A\u00021\u0001\u0002\u0012\u0005aAm\\;cY\u0016\fUn\\;oiB\u0019A'a\u0005\n\u0007\u0005UQG\u0001\u0004E_V\u0014G.Z\u0001\u0018C\u0012$G+Y:l%\u0016\u001cx.\u001e:dKJ+\u0017/^3tiN$b!a\u0007\u0002\"\u0005\r\u0002c\u0001\u001b\u0002\u001e%\u0019\u0011qD\u001b\u0003\tUs\u0017\u000e\u001e\u0005\u0006/6\u0001\r\u0001\u0017\u0005\b\u0003Ki\u0001\u0019AA\u0014\u0003\u0015!(/Z9t!\r\u0001\u0014\u0011F\u0005\u0004\u0003W9#\u0001\u0006+bg.\u0014Vm]8ve\u000e,'+Z9vKN$8/A\rqCJ\u001cXMU3t_V\u00148-\u001a*fcVL'/Z7f]R\u001cHCBA\u0019\u0003s\tY\u0004\u0005\u0003eY\u0006M\u0002c\u0001\u0019\u00026%\u0019\u0011qG\u0014\u0003'I+7o\\;sG\u0016\u0014V-];je\u0016lWM\u001c;\t\u000b]s\u0001\u0019\u0001-\t\u000bEt\u0001\u0019\u0001:\u0002I\u0015DXmY;u_J\u0014Vm]8ve\u000e,'+Z9vKN$Hk\u001c*fcVL'/Z7f]R$B!!\r\u0002B!9\u00111I\bA\u0002\u0005\u0015\u0013a\u0004:fg>,(oY3SKF,Xm\u001d;\u0011\t\u0011d\u0017q\t\t\u0004a\u0005%\u0013bAA&O\t9R\t_3dkR|'OU3t_V\u00148-\u001a*fcV,7\u000f^\u0001\u001ae\u0016\u001cx.\u001e:dKNlU-\u001a;SKF,\u0018N]3nK:$8\u000f\u0006\u0004\u0002R\u0005]\u0013\u0011\r\t\u0004i\u0005M\u0013bAA+k\t9!i\\8mK\u0006t\u0007bBA-!\u0001\u0007\u00111L\u0001\u000ee\u0016\u001cx.\u001e:dKN4%/Z3\u0011\rM\fiF]A\u0004\u0013\r\ty\u0006\u001f\u0002\u0004\u001b\u0006\u0004\bbBA2!\u0001\u0007\u0011\u0011G\u0001\u0015e\u0016\u001cx.\u001e:dKJ+\u0017/^5sK6,g\u000e^:\u0002#]LG\u000f\u001b*fg>,(oY3t\u0015N|g.\u0006\u0003\u0002j\u0005MD\u0003BA6\u0003\u001f#B!!\u001c\u0002\u0006B!A\r\\A8!\u0011\t\t(a\u001d\r\u0001\u00119\u0011QO\tC\u0002\u0005]$!\u0001+\u0012\t\u0005e\u0014q\u0010\t\u0004i\u0005m\u0014bAA?k\t9aj\u001c;iS:<\u0007c\u0001\u001b\u0002\u0002&\u0019\u00111Q\u001b\u0003\u0007\u0005s\u0017\u0010C\u0004\u0002\bF\u0001\r!!#\u0002\u000f\u0015DHO]1diB1A'a#s\u0003[J1!!$6\u0005%1UO\\2uS>t\u0017\u0007\u0003\u0004\u0002\u0012F\u0001\rA]\u0001\u000ee\u0016\u001cx.\u001e:dKN4\u0015\u000e\\3\u00025A\f'o]3BY2|7-\u0019;fI\u001a\u0013x.\u001c&t_:4\u0015\u000e\\3\u0015\t\u0005]\u0015q\u0014\t\u0005I2\fI\nE\u00021\u00037K1!!((\u0005I\u0011Vm]8ve\u000e,\u0017\t\u001c7pG\u0006$\u0018n\u001c8\t\r\u0005E%\u00031\u0001s\u00039\u0001\u0018M]:f\u00032dwnY1uK\u0012$b!a&\u0002&\u0006=\u0006bBAT'\u0001\u0007\u0011\u0011V\u0001\u0011e\u0016\u001cx.\u001e:dKN4\u0015\u000e\\3PaR\u0004B\u0001NAVe&\u0019\u0011QV\u001b\u0003\r=\u0003H/[8o\u0011\u0015\t8\u00031\u0001s\u0003\u0005\u0002\u0018M]:f\u00032dwnY1uK\u0012|%\u000fR5tG>4XM\u001d*fg>,(oY3t)!\t9*!.\u00028\u0006e\u0006\"B,\u0015\u0001\u0004A\u0006\"B9\u0015\u0001\u0004\u0011\bbBAT)\u0001\u0007\u0011\u0011V\u0001%CN\u001cXM\u001d;SKN|WO]2f\u00032dwnY1uS>tW*Z3ugJ+\u0017/^3tiR1\u00111DA`\u0003\u0007Dq!!1\u0016\u0001\u0004\tI*\u0001\u0006bY2|7-\u0019;j_:Da!!2\u0016\u0001\u0004\u0019\u0016a\u0002:fcV,7\u000f^\u0001)CN\u001cXM\u001d;BY2\u0014Vm]8ve\u000e,\u0017\t\u001c7pG\u0006$\u0018n\u001c8t\u001b\u0016,GOU3rk\u0016\u001cHo\u001d\u000b\u0007\u00037\tY-a4\t\u000f\u00055g\u00031\u0001\u0002\u0018\u0006Y\u0011\r\u001c7pG\u0006$\u0018n\u001c8t\u0011\u0019\t\tN\u0006a\u0001w\u0006A!/Z9vKN$8/\u0001\u0019bgN,'\u000f^!mYJ+7o\\;sG\u0016\fE\u000e\\8dCRLwN\\:NCR\u001c\u0007NU3t_V\u00148-\u001a)s_\u001aLG.\u001a\u000b\u0007\u00037\t9.!9\t\u000f\u00055w\u00031\u0001\u0002ZB11/!\u0018s\u00037\u00042\u0001MAo\u0013\r\tyn\n\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\u001c\u0005\b\u0003G<\u0002\u0019AAs\u0003!)\u00070Z2SKF\u001c\bCB:\u0002^I\f9%A\rhKR|%\u000fR5tG>4XM]!mYJ+7o\\;sG\u0016\u001cH\u0003CAm\u0003W\fi/a<\t\u000b]C\u0002\u0019\u0001-\t\u000bED\u0002\u0019\u0001:\t\u000f\u0005\u001d\u0006\u00041\u0001\u0002*\u0006)R-\u001c9usN#(/\u001b8h)>|\u0005\u000f^5p]\u0006dG\u0003BA{\u0005\u0003\u0001R!a>\u0002~Jl!!!?\u000b\u0007\u0005m\b*\u0001\u0003vi&d\u0017\u0002BA\u0000\u0003s\u0014\u0001b\u00149uS>t\u0017\r\u001c\u0005\u0007\u0005\u0007I\u0002\u0019\u0001:\u0002\r=\u0004Ho\u0015;s\u0003-:W\r^(s\t&\u001c8m\u001c<fe\u0006cGNU3t_V\u00148-Z:G_J\u0014Vm]8ve\u000e,\u0007K]8gS2,GCCAm\u0005\u0013\u0011YA!\u0004\u0003\u0018!9\u0011q\u0015\u000eA\u0002\u0005%\u0006\"B9\u001b\u0001\u0004\u0011\bb\u0002B\b5\u0001\u0007!\u0011C\u0001\u0010e\u0016\u001cx.\u001e:dKB\u0013xNZ5mKB\u0019\u0001Ga\u0005\n\u0007\tUqEA\bSKN|WO]2f!J|g-\u001b7f\u0011\u00159&\u00041\u0001Y\u0003=awn\u001a*fg>,(oY3J]\u001a|GCBA\u000e\u0005;\u0011y\u0002C\u0003r7\u0001\u0007!\u000fC\u0004\u0003\"m\u0001\r!!7\u0002\u0013I,7o\\;sG\u0016\u001c\u0018\u0001\u00053jg\u000e|g/\u001a:SKN|WO]2f)\u0019\tYNa\n\u0003*!)q\u000b\ba\u00011\"1\u00111\t\u000fA\u0002M\u000b1D^1mS\u0012\fG/\u001a+bg.\u001c\u0005/^:MCJ<W-\u00128pk\u001eDG\u0003CA)\u0005_\u0011\tD!\u000e\t\u000b]k\u0002\u0019\u0001-\t\u000f\tMR\u00041\u0001\u0002\b\u0005IQ\r_3d\u0007>\u0014Xm\u001d\u0005\b\u0005oi\u0002\u0019AA\u0004\u0003!!\u0018m]6DaV\u001c\u0018!F<be:|enV1ti\u0016$'+Z:pkJ\u001cWm\u001d\u000b\t\u00037\u0011iD!\u0011\u0003D!9!q\b\u0010A\u0002\tE\u0011A\u0001:q\u0011\u00159f\u00041\u0001Y\u0011%\u0011\u0019D\bI\u0001\u0002\u0004\u0011)\u0005E\u00035\u0003W\u000b9!A\u0010xCJtwJ\\,bgR,GMU3t_V\u00148-Z:%I\u00164\u0017-\u001e7uIM*\"Aa\u0013+\t\t\u0015#QJ\u0016\u0003\u0005\u001f\u0002BA!\u0015\u0003\\5\u0011!1\u000b\u0006\u0005\u0005+\u00129&A\u0005v]\u000eDWmY6fI*\u0019!\u0011L\u001b\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0003^\tM#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u0019q\tU+\u0016\u0003I\fAa\u0012)VA\u0005!a\tU$B\u0003\u00151\u0005kR!!\u0003=\u0011ViU(V%\u000e+u\f\u0015*F\r&C\u0016\u0001\u0005*F'>+&kQ#`!J+e)\u0013-!\u0001"
)
public final class ResourceUtils {
   public static String RESOURCE_PREFIX() {
      return ResourceUtils$.MODULE$.RESOURCE_PREFIX();
   }

   public static String FPGA() {
      return ResourceUtils$.MODULE$.FPGA();
   }

   public static String GPU() {
      return ResourceUtils$.MODULE$.GPU();
   }

   public static Option warnOnWastedResources$default$3() {
      return ResourceUtils$.MODULE$.warnOnWastedResources$default$3();
   }

   public static void warnOnWastedResources(final ResourceProfile rp, final SparkConf sparkConf, final Option execCores) {
      ResourceUtils$.MODULE$.warnOnWastedResources(rp, sparkConf, execCores);
   }

   public static boolean validateTaskCpusLargeEnough(final SparkConf sparkConf, final int execCores, final int taskCpus) {
      return ResourceUtils$.MODULE$.validateTaskCpusLargeEnough(sparkConf, execCores, taskCpus);
   }

   public static void logResourceInfo(final String componentName, final Map resources) {
      ResourceUtils$.MODULE$.logResourceInfo(componentName, resources);
   }

   public static Map getOrDiscoverAllResourcesForResourceProfile(final Option resourcesFileOpt, final String componentName, final ResourceProfile resourceProfile, final SparkConf sparkConf) {
      return ResourceUtils$.MODULE$.getOrDiscoverAllResourcesForResourceProfile(resourcesFileOpt, componentName, resourceProfile, sparkConf);
   }

   public static Map getOrDiscoverAllResources(final SparkConf sparkConf, final String componentName, final Option resourcesFileOpt) {
      return ResourceUtils$.MODULE$.getOrDiscoverAllResources(sparkConf, componentName, resourcesFileOpt);
   }

   public static Seq parseAllocated(final Option resourcesFileOpt, final String componentName) {
      return ResourceUtils$.MODULE$.parseAllocated(resourcesFileOpt, componentName);
   }

   public static Seq parseAllocatedFromJsonFile(final String resourcesFile) {
      return ResourceUtils$.MODULE$.parseAllocatedFromJsonFile(resourcesFile);
   }

   public static Seq withResourcesJson(final String resourcesFile, final Function1 extract) {
      return ResourceUtils$.MODULE$.withResourcesJson(resourcesFile, extract);
   }

   public static boolean resourcesMeetRequirements(final Map resourcesFree, final Seq resourceRequirements) {
      return ResourceUtils$.MODULE$.resourcesMeetRequirements(resourcesFree, resourceRequirements);
   }

   public static Seq executorResourceRequestToRequirement(final Seq resourceRequest) {
      return ResourceUtils$.MODULE$.executorResourceRequestToRequirement(resourceRequest);
   }

   public static Seq parseResourceRequirements(final SparkConf sparkConf, final String componentName) {
      return ResourceUtils$.MODULE$.parseResourceRequirements(sparkConf, componentName);
   }

   public static void addTaskResourceRequests(final SparkConf sparkConf, final TaskResourceRequests treqs) {
      ResourceUtils$.MODULE$.addTaskResourceRequests(sparkConf, treqs);
   }

   public static Tuple2 calculateAmountAndPartsForFraction(final double doubleAmount) {
      return ResourceUtils$.MODULE$.calculateAmountAndPartsForFraction(doubleAmount);
   }

   public static Seq parseAllResourceRequests(final SparkConf sparkConf, final String componentName) {
      return ResourceUtils$.MODULE$.parseAllResourceRequests(sparkConf, componentName);
   }

   public static Seq listResourceIds(final SparkConf sparkConf, final String componentName) {
      return ResourceUtils$.MODULE$.listResourceIds(sparkConf, componentName);
   }

   public static ResourceRequest parseResourceRequest(final SparkConf sparkConf, final ResourceID resourceId) {
      return ResourceUtils$.MODULE$.parseResourceRequest(sparkConf, resourceId);
   }

   public static String AMOUNT() {
      return ResourceUtils$.MODULE$.AMOUNT();
   }

   public static String VENDOR() {
      return ResourceUtils$.MODULE$.VENDOR();
   }

   public static String DISCOVERY_SCRIPT() {
      return ResourceUtils$.MODULE$.DISCOVERY_SCRIPT();
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return ResourceUtils$.MODULE$.LogStringContext(sc);
   }
}
