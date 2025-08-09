package org.apache.spark.mllib.clustering;

import java.lang.invoke.SerializedLambda;
import java.util.Locale;
import java.util.Map;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015h\u0001B\u001e=\u0001\u001dC\u0001\u0002\u0016\u0001\u0003\u0002\u0004%I!\u0016\u0005\t3\u0002\u0011\t\u0019!C\u00055\"A\u0001\r\u0001B\u0001B\u0003&a\u000b\u0003\u0005b\u0001\t\u0005\r\u0011\"\u0003V\u0011!\u0011\u0007A!a\u0001\n\u0013\u0019\u0007\u0002C3\u0001\u0005\u0003\u0005\u000b\u0015\u0002,\t\u0011\u0019\u0004!\u00111A\u0005\n\u001dD\u0001B\u001c\u0001\u0003\u0002\u0004%Ia\u001c\u0005\tc\u0002\u0011\t\u0011)Q\u0005Q\"A!\u000f\u0001BA\u0002\u0013%1\u000f\u0003\u0005x\u0001\t\u0005\r\u0011\"\u0003y\u0011!Q\bA!A!B\u0013!\b\u0002C>\u0001\u0005\u0003\u0007I\u0011\u0002?\t\u0015\u0005\u0005\u0001A!a\u0001\n\u0013\t\u0019\u0001C\u0005\u0002\b\u0001\u0011\t\u0011)Q\u0005{\"I\u0011\u0011\u0002\u0001\u0003\u0002\u0004%I!\u0016\u0005\u000b\u0003\u0017\u0001!\u00111A\u0005\n\u00055\u0001\"CA\t\u0001\t\u0005\t\u0015)\u0003W\u0011)\t\u0019\u0002\u0001BA\u0002\u0013%\u0011Q\u0003\u0005\u000b\u0003?\u0001!\u00111A\u0005\n\u0005\u0005\u0002BCA\u0013\u0001\t\u0005\t\u0015)\u0003\u0002\u0018!9\u0011q\u0005\u0001\u0005\n\u0005%\u0002bBA\u0014\u0001\u0011\u0005\u00111\b\u0005\u0007\u0003\u001f\u0002A\u0011A+\t\u000f\u0005M\u0003\u0001\"\u0001\u0002V!1\u0011Q\f\u0001\u0005\u0002\u001dDa!!\u001a\u0001\t\u0003\u0019\bbBA5\u0001\u0011\u0005\u00111\u000e\u0005\b\u0003S\u0002A\u0011AA9\u0011\u0019\t9\b\u0001C\u0001O\"1\u00111\u0010\u0001\u0005\u0002MDq!a \u0001\t\u0003\t\t\tC\u0004\u0002\u0000\u0001!\t!!#\t\r\u0005=\u0005\u0001\"\u0001t\u0011\u001d\t\u0019\n\u0001C\u0001\u0003+Ca!a'\u0001\t\u0003\u0019\bbBAP\u0001\u0011\u0005\u0011\u0011\u0015\u0005\u0007\u0003S\u0003A\u0011A+\t\u000f\u00055\u0006\u0001\"\u0001\u00020\"1\u0011Q\u0017\u0001\u0005\u0002qDq!!/\u0001\t\u0003\tY\f\u0003\u0004\u0002B\u0002!\t!\u0016\u0005\b\u0003\u000b\u0004A\u0011AAd\u0011\u001d\ti\r\u0001C\u0001\u0003+Aq!!6\u0001\t\u0003\t9\u000eC\u0004\u0002V\u0002!\t!a8\t\u000f\u0005u\b\u0001\"\u0001\u0002\u0000\"9\u0011Q \u0001\u0005\u0002\t}q\u0001\u0003B\"y!\u0005AH!\u0012\u0007\u000fmb\u0004\u0012\u0001\u001f\u0003H!9\u0011q\u0005\u001a\u0005\u0002\t%Sa\u0002B&e\u0001a$QJ\u0003\u0007\u00057\u0012\u0004\u0001\u0010;\t\u0011\tu#\u0007\"\u0001=\u0005?B\u0001B!\u001a3\t\u0003a$q\r\u0005\t\u0005[\u0012D\u0011\u0001\u001f\u0003p!A!q\u0017\u001a\u0005\u0002q\u0012I\f\u0003\u0005\u0003HJ\"\t\u0001\u0010Be\u0005\raE)\u0011\u0006\u0003{y\n!b\u00197vgR,'/\u001b8h\u0015\ty\u0004)A\u0003nY2L'M\u0003\u0002B\u0005\u0006)1\u000f]1sW*\u00111\tR\u0001\u0007CB\f7\r[3\u000b\u0003\u0015\u000b1a\u001c:h\u0007\u0001\u00192\u0001\u0001%O!\tIE*D\u0001K\u0015\u0005Y\u0015!B:dC2\f\u0017BA'K\u0005\u0019\te.\u001f*fMB\u0011qJU\u0007\u0002!*\u0011\u0011\u000bQ\u0001\tS:$XM\u001d8bY&\u00111\u000b\u0015\u0002\b\u0019><w-\u001b8h\u0003\u0005YW#\u0001,\u0011\u0005%;\u0016B\u0001-K\u0005\rIe\u000e^\u0001\u0006W~#S-\u001d\u000b\u00037z\u0003\"!\u0013/\n\u0005uS%\u0001B+oSRDqa\u0018\u0002\u0002\u0002\u0003\u0007a+A\u0002yIE\n!a\u001b\u0011\u0002\u001b5\f\u00070\u0013;fe\u0006$\u0018n\u001c8t\u0003Ei\u0017\r_%uKJ\fG/[8og~#S-\u001d\u000b\u00037\u0012DqaX\u0003\u0002\u0002\u0003\u0007a+\u0001\bnCbLE/\u001a:bi&|gn\u001d\u0011\u0002!\u0011|7mQ8oG\u0016tGO]1uS>tW#\u00015\u0011\u0005%dW\"\u00016\u000b\u0005-t\u0014A\u00027j]\u0006dw-\u0003\u0002nU\n1a+Z2u_J\fA\u0003Z8d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8`I\u0015\fHCA.q\u0011\u001dy\u0006\"!AA\u0002!\f\u0011\u0003Z8d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8!\u0003I!x\u000e]5d\u0007>t7-\u001a8ue\u0006$\u0018n\u001c8\u0016\u0003Q\u0004\"!S;\n\u0005YT%A\u0002#pk\ndW-\u0001\fu_BL7mQ8oG\u0016tGO]1uS>tw\fJ3r)\tY\u0016\u0010C\u0004`\u0017\u0005\u0005\t\u0019\u0001;\u0002'Q|\u0007/[2D_:\u001cWM\u001c;sCRLwN\u001c\u0011\u0002\tM,W\rZ\u000b\u0002{B\u0011\u0011J`\u0005\u0003\u007f*\u0013A\u0001T8oO\u0006A1/Z3e?\u0012*\u0017\u000fF\u0002\\\u0003\u000bAqa\u0018\b\u0002\u0002\u0003\u0007Q0A\u0003tK\u0016$\u0007%\u0001\ndQ\u0016\u001c7\u000e]8j]RLe\u000e^3sm\u0006d\u0017AF2iK\u000e\\\u0007o\\5oi&sG/\u001a:wC2|F%Z9\u0015\u0007m\u000by\u0001C\u0004`#\u0005\u0005\t\u0019\u0001,\u0002'\rDWmY6q_&tG/\u00138uKJ4\u0018\r\u001c\u0011\u0002\u00191$\u0017m\u00149uS6L'0\u001a:\u0016\u0005\u0005]\u0001\u0003BA\r\u00037i\u0011\u0001P\u0005\u0004\u0003;a$\u0001\u0004'E\u0003>\u0003H/[7ju\u0016\u0014\u0018\u0001\u00057eC>\u0003H/[7ju\u0016\u0014x\fJ3r)\rY\u00161\u0005\u0005\t?R\t\t\u00111\u0001\u0002\u0018\u0005iA\u000eZ1PaRLW.\u001b>fe\u0002\na\u0001P5oSRtD\u0003EA\u0016\u0003[\ty#!\r\u00024\u0005U\u0012qGA\u001d!\r\tI\u0002\u0001\u0005\u0006)Z\u0001\rA\u0016\u0005\u0006CZ\u0001\rA\u0016\u0005\u0006MZ\u0001\r\u0001\u001b\u0005\u0006eZ\u0001\r\u0001\u001e\u0005\u0006wZ\u0001\r! \u0005\u0007\u0003\u00131\u0002\u0019\u0001,\t\u000f\u0005Ma\u00031\u0001\u0002\u0018Q\u0011\u00111\u0006\u0015\u0006/\u0005}\u00121\n\t\u0005\u0003\u0003\n9%\u0004\u0002\u0002D)\u0019\u0011Q\t!\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002J\u0005\r#!B*j]\u000e,\u0017EAA'\u0003\u0015\tdf\r\u00181\u0003\u00119W\r^&)\u000ba\ty$a\u0013\u0002\tM,Go\u0013\u000b\u0005\u0003/\nI&D\u0001\u0001\u0011\u0015!\u0016\u00041\u0001WQ\u0015I\u0012qHA&\u0003u9W\r^!ts6lW\r\u001e:jG\u0012{7mQ8oG\u0016tGO]1uS>t\u0007&\u0002\u000e\u0002@\u0005\u0005\u0014EAA2\u0003\u0015\td&\u000e\u00181\u0003M9W\r\u001e#pG\u000e{gnY3oiJ\fG/[8oQ\u0015Y\u0012qHA&\u0003M\u0019X\r\u001e#pG\u000e{gnY3oiJ\fG/[8o)\u0011\t9&!\u001c\t\u000b\u0019d\u0002\u0019\u00015)\u000bq\ty$!\u0019\u0015\t\u0005]\u00131\u000f\u0005\u0006Mv\u0001\r\u0001\u001e\u0015\u0006;\u0005}\u00121J\u0001\u0013O\u0016$\u0018i]=n[\u0016$(/[2BYBD\u0017\rK\u0003\u001f\u0003\u007f\t\t'\u0001\u0005hKR\fE\u000e\u001d5bQ\u0015y\u0012qHA&\u0003!\u0019X\r^!ma\"\fG\u0003BA,\u0003\u0007Ca!!\"!\u0001\u0004A\u0017!B1ma\"\f\u0007&\u0002\u0011\u0002@\u0005\u0005D\u0003BA,\u0003\u0017Ca!!\"\"\u0001\u0004!\b&B\u0011\u0002@\u0005-\u0013!F4fiR{\u0007/[2D_:\u001cWM\u001c;sCRLwN\u001c\u0015\u0006E\u0005}\u00121J\u0001\u0016g\u0016$Hk\u001c9jG\u000e{gnY3oiJ\fG/[8o)\u0011\t9&a&\t\u000bI\u001c\u0003\u0019\u0001;)\u000b\r\ny$a\u0013\u0002\u000f\u001d,GOQ3uC\"*A%a\u0010\u0002L\u000591/\u001a;CKR\fG\u0003BA,\u0003GCa!!*&\u0001\u0004!\u0018\u0001\u00022fi\u0006DS!JA \u0003\u0017\n\u0001cZ3u\u001b\u0006D\u0018\n^3sCRLwN\\:)\u000b\u0019\ny$a\u0013\u0002!M,G/T1y\u0013R,'/\u0019;j_:\u001cH\u0003BA,\u0003cCQ!Y\u0014A\u0002YCSaJA \u0003\u0017\nqaZ3u'\u0016,G\rK\u0003)\u0003\u007f\tY%A\u0004tKR\u001cV-\u001a3\u0015\t\u0005]\u0013Q\u0018\u0005\u0006w&\u0002\r! \u0015\u0006S\u0005}\u00121J\u0001\u0016O\u0016$8\t[3dWB|\u0017N\u001c;J]R,'O^1mQ\u0015Q\u0013qHA&\u0003U\u0019X\r^\"iK\u000e\\\u0007o\\5oi&sG/\u001a:wC2$B!a\u0016\u0002J\"1\u0011\u0011B\u0016A\u0002YCSaKA \u0003\u0017\nAbZ3u\u001fB$\u0018.\\5{KJDS\u0001LA \u0003#\f#!a5\u0002\u000bErCG\f\u0019\u0002\u0019M,Go\u00149uS6L'0\u001a:\u0015\t\u0005]\u0013\u0011\u001c\u0005\b\u00037l\u0003\u0019AA\f\u0003%y\u0007\u000f^5nSj,'\u000fK\u0003.\u0003\u007f\t\t\u000e\u0006\u0003\u0002X\u0005\u0005\bbBAr]\u0001\u0007\u0011Q]\u0001\u000e_B$\u0018.\\5{KJt\u0015-\\3\u0011\t\u0005\u001d\u0018Q\u001f\b\u0005\u0003S\f\t\u0010E\u0002\u0002l*k!!!<\u000b\u0007\u0005=h)\u0001\u0004=e>|GOP\u0005\u0004\u0003gT\u0015A\u0002)sK\u0012,g-\u0003\u0003\u0002x\u0006e(AB*ue&twMC\u0002\u0002t*CSALA \u0003#\f1A];o)\u0011\u0011\tAa\u0002\u0011\t\u0005e!1A\u0005\u0004\u0005\u000ba$\u0001\u0003'E\u00036{G-\u001a7\t\u000f\t%q\u00061\u0001\u0003\f\u0005IAm\\2v[\u0016tGo\u001d\t\u0007\u0005\u001b\u0011\u0019Ba\u0006\u000e\u0005\t=!b\u0001B\t\u0001\u0006\u0019!\u000f\u001a3\n\t\tU!q\u0002\u0002\u0004%\u0012#\u0005#B%\u0003\u001auD\u0017b\u0001B\u000e\u0015\n1A+\u001e9mKJBSaLA \u0003\u0017\"BA!\u0001\u0003\"!9!\u0011\u0002\u0019A\u0002\t\r\u0002c\u0002B\u0013\u0005_\u0011\u0019\u0004[\u0007\u0003\u0005OQAA!\u000b\u0003,\u0005!!.\u0019<b\u0015\r\u0011i\u0003Q\u0001\u0004CBL\u0017\u0002\u0002B\u0019\u0005O\u00111BS1wCB\u000b\u0017N\u001d*E\tB!!Q\u0007B\u001f\u001b\t\u00119D\u0003\u0003\u0003:\tm\u0012\u0001\u00027b]\u001eT!A!\u000b\n\u0007}\u00149\u0004K\u00031\u0003\u007f\tY\u0005K\u0003\u0001\u0003\u007f\tY%A\u0002M\t\u0006\u00032!!\u00073'\t\u0011\u0004\n\u0006\u0002\u0003F\tYAk\u001c9jG\u000e{WO\u001c;t!\u0015\u0011yEa\u0016u\u001b\t\u0011\tFC\u0002l\u0005'R!A!\u0016\u0002\r\t\u0014X-\u001a>f\u0013\u0011\u0011IF!\u0015\u0003\u0017\u0011+gn]3WK\u000e$xN\u001d\u0002\u000b)>\\WM\\\"pk:$\u0018A\u0003;fe6\u0014\u0014N\u001c3fqR\u0019QP!\u0019\t\r\t\rd\u00071\u0001W\u0003\u0011!XM]7\u0002\u0015%tG-\u001a=3i\u0016\u0014X\u000eF\u0002W\u0005SBaAa\u001b8\u0001\u0004i\u0018!\u0003;fe6Le\u000eZ3y\u0003AI7\u000fR8dk6,g\u000e\u001e,feR,\u0007\u0010\u0006\u0003\u0003r\t]\u0004cA%\u0003t%\u0019!Q\u000f&\u0003\u000f\t{w\u000e\\3b]\"9!\u0011\u0010\u001dA\u0002\tm\u0014!\u0001<1\t\tu$Q\u0015\t\b\u0013\ne!q\u0010BQ!\u0011\u0011\tIa'\u000f\t\t\r%Q\u0013\b\u0005\u0005\u000b\u0013\tJ\u0004\u0003\u0003\b\n=e\u0002\u0002BE\u0005\u001bsA!a;\u0003\f&\tQ)\u0003\u0002D\t&\u0011\u0011IQ\u0005\u0004\u0005'\u0003\u0015AB4sCBD\u00070\u0003\u0003\u0003\u0018\ne\u0015a\u00029bG.\fw-\u001a\u0006\u0004\u0005'\u0003\u0015\u0002\u0002BO\u0005?\u0013\u0001BV3si\u0016D\u0018\n\u001a\u0006\u0005\u0005/\u0013I\n\u0005\u0003\u0003$\n\u0015F\u0002\u0001\u0003\r\u0005O\u00139(!A\u0001\u0002\u000b\u0005!\u0011\u0016\u0002\u0004?\u0012\n\u0014\u0003\u0002BV\u0005c\u00032!\u0013BW\u0013\r\u0011yK\u0013\u0002\b\u001d>$\b.\u001b8h!\rI%1W\u0005\u0004\u0005kS%aA!os\u0006a\u0011n\u001d+fe64VM\u001d;fqR!!\u0011\u000fB^\u0011\u001d\u0011I(\u000fa\u0001\u0005{\u0003DAa0\u0003DB9\u0011J!\u0007\u0003\u0000\t\u0005\u0007\u0003\u0002BR\u0005\u0007$AB!2\u0003<\u0006\u0005\t\u0011!B\u0001\u0005S\u00131a\u0018\u00133\u00035\u0019w.\u001c9vi\u0016\u0004Fk\u001c9jGRq!1\u001aBh\u0005'\u00149Na7\u0003`\n\r\bc\u0001Bgi5\t!\u0007C\u0004\u0003Rj\u0002\rAa3\u0002\u001d\u0011|7\rV8qS\u000e\u001cu.\u001e8ug\"9!Q\u001b\u001eA\u0002\t-\u0017a\u0004;fe6$v\u000e]5d\u0007>,h\u000e^:\t\u000f\te'\b1\u0001\u0003L\u0006\u0001Bo\u001c;bYR{\u0007/[2D_VtGo\u001d\u0005\u0007\u0005;T\u0004\u0019\u0001,\u0002\u0013Y|7-\u00192TSj,\u0007B\u0002Bqu\u0001\u0007A/A\u0002fi\u0006Da!!\";\u0001\u0004!\b"
)
public class LDA implements Logging {
   private int k;
   private int maxIterations;
   private Vector docConcentration;
   private double topicConcentration;
   private long seed;
   private int checkpointInterval;
   private LDAOptimizer ldaOptimizer;
   private transient Logger org$apache$spark$internal$Logging$$log_;

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return this.org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      this.org$apache$spark$internal$Logging$$log_ = x$1;
   }

   private int k() {
      return this.k;
   }

   private void k_$eq(final int x$1) {
      this.k = x$1;
   }

   private int maxIterations() {
      return this.maxIterations;
   }

   private void maxIterations_$eq(final int x$1) {
      this.maxIterations = x$1;
   }

   private Vector docConcentration() {
      return this.docConcentration;
   }

   private void docConcentration_$eq(final Vector x$1) {
      this.docConcentration = x$1;
   }

   private double topicConcentration() {
      return this.topicConcentration;
   }

   private void topicConcentration_$eq(final double x$1) {
      this.topicConcentration = x$1;
   }

   private long seed() {
      return this.seed;
   }

   private void seed_$eq(final long x$1) {
      this.seed = x$1;
   }

   private int checkpointInterval() {
      return this.checkpointInterval;
   }

   private void checkpointInterval_$eq(final int x$1) {
      this.checkpointInterval = x$1;
   }

   private LDAOptimizer ldaOptimizer() {
      return this.ldaOptimizer;
   }

   private void ldaOptimizer_$eq(final LDAOptimizer x$1) {
      this.ldaOptimizer = x$1;
   }

   public int getK() {
      return this.k();
   }

   public LDA setK(final int k) {
      .MODULE$.require(k > 0, () -> "LDA k (number of clusters) must be > 0, but was set to " + k);
      this.k_$eq(k);
      return this;
   }

   public Vector getAsymmetricDocConcentration() {
      return this.docConcentration();
   }

   public double getDocConcentration() {
      double parameter = this.docConcentration().apply(0);
      if (this.docConcentration().size() == 1) {
         return parameter;
      } else {
         .MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(.MODULE$.doubleArrayOps(this.docConcentration().toArray()), (JFunction1.mcZD.sp)(x$1) -> x$1 == parameter));
         return parameter;
      }
   }

   public LDA setDocConcentration(final Vector docConcentration) {
      .MODULE$.require(docConcentration.size() == 1 || docConcentration.size() == this.k(), () -> {
         int var10000 = this.k();
         return "Size of docConcentration must be 1 or " + var10000 + " but got " + docConcentration.size();
      });
      this.docConcentration_$eq(docConcentration);
      return this;
   }

   public LDA setDocConcentration(final double docConcentration) {
      this.docConcentration_$eq(Vectors$.MODULE$.dense(docConcentration, (Seq)scala.collection.immutable.Nil..MODULE$));
      return this;
   }

   public Vector getAsymmetricAlpha() {
      return this.getAsymmetricDocConcentration();
   }

   public double getAlpha() {
      return this.getDocConcentration();
   }

   public LDA setAlpha(final Vector alpha) {
      return this.setDocConcentration(alpha);
   }

   public LDA setAlpha(final double alpha) {
      return this.setDocConcentration(alpha);
   }

   public double getTopicConcentration() {
      return this.topicConcentration();
   }

   public LDA setTopicConcentration(final double topicConcentration) {
      this.topicConcentration_$eq(topicConcentration);
      return this;
   }

   public double getBeta() {
      return this.getTopicConcentration();
   }

   public LDA setBeta(final double beta) {
      return this.setTopicConcentration(beta);
   }

   public int getMaxIterations() {
      return this.maxIterations();
   }

   public LDA setMaxIterations(final int maxIterations) {
      .MODULE$.require(maxIterations >= 0, () -> "Maximum of iterations must be nonnegative but got " + maxIterations);
      this.maxIterations_$eq(maxIterations);
      return this;
   }

   public long getSeed() {
      return this.seed();
   }

   public LDA setSeed(final long seed) {
      this.seed_$eq(seed);
      return this;
   }

   public int getCheckpointInterval() {
      return this.checkpointInterval();
   }

   public LDA setCheckpointInterval(final int checkpointInterval) {
      .MODULE$.require(checkpointInterval == -1 || checkpointInterval > 0, () -> "Period between checkpoints must be -1 or positive but got " + checkpointInterval);
      this.checkpointInterval_$eq(checkpointInterval);
      return this;
   }

   public LDAOptimizer getOptimizer() {
      return this.ldaOptimizer();
   }

   public LDA setOptimizer(final LDAOptimizer optimizer) {
      this.ldaOptimizer_$eq(optimizer);
      return this;
   }

   public LDA setOptimizer(final String optimizerName) {
      String var3 = optimizerName.toLowerCase(Locale.ROOT);
      Object var10001;
      switch (var3 == null ? 0 : var3.hashCode()) {
         case -1012222381:
            if (!"online".equals(var3)) {
               throw new IllegalArgumentException("Only em, online are supported but got " + var3 + ".");
            }

            var10001 = new OnlineLDAOptimizer();
            break;
         case 3240:
            if ("em".equals(var3)) {
               var10001 = new EMLDAOptimizer();
               break;
            }

            throw new IllegalArgumentException("Only em, online are supported but got " + var3 + ".");
         default:
            throw new IllegalArgumentException("Only em, online are supported but got " + var3 + ".");
      }

      this.ldaOptimizer_$eq((LDAOptimizer)var10001);
      return this;
   }

   public LDAModel run(final RDD documents) {
      LDAOptimizer state = this.ldaOptimizer().initialize(documents, this);
      int iter = 0;

      double[] iterationTimes;
      for(iterationTimes = (double[])scala.Array..MODULE$.ofDim(this.maxIterations(), scala.reflect.ClassTag..MODULE$.Double()); iter < this.maxIterations(); ++iter) {
         long start = System.nanoTime();
         state.next();
         double elapsedSeconds = (double)(System.nanoTime() - start) / (double)1.0E9F;
         iterationTimes[iter] = elapsedSeconds;
      }

      return state.getLDAModel(iterationTimes);
   }

   public LDAModel run(final JavaPairRDD documents) {
      return this.run(documents.rdd());
   }

   private LDA(final int k, final int maxIterations, final Vector docConcentration, final double topicConcentration, final long seed, final int checkpointInterval, final LDAOptimizer ldaOptimizer) {
      this.k = k;
      this.maxIterations = maxIterations;
      this.docConcentration = docConcentration;
      this.topicConcentration = topicConcentration;
      this.seed = seed;
      this.checkpointInterval = checkpointInterval;
      this.ldaOptimizer = ldaOptimizer;
      super();
      Logging.$init$(this);
   }

   public LDA() {
      this(10, 20, Vectors$.MODULE$.dense((double)-1.0F, (Seq)scala.collection.immutable.Nil..MODULE$), (double)-1.0F, org.apache.spark.util.Utils..MODULE$.random().nextLong(), 10, new EMLDAOptimizer());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
