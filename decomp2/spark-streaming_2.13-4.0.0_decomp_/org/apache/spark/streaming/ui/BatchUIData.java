package org.apache.spark.streaming.ui;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.scheduler.BatchInfo;
import org.apache.spark.streaming.scheduler.OutputOperationInfo;
import org.apache.spark.streaming.scheduler.StreamInputInfo;
import scala.Option;
import scala.Predef;
import scala.Product;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.immutable.Map;
import scala.collection.mutable.HashMap;
import scala.math.Numeric.LongIsIntegral.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\t\re!\u0002\u001c8\u0001^\n\u0005\u0002\u0003-\u0001\u0005+\u0007I\u0011A-\t\u0011y\u0003!\u0011#Q\u0001\niC\u0001b\u0018\u0001\u0003\u0016\u0004%\t\u0001\u0019\u0005\te\u0002\u0011\t\u0012)A\u0005C\"A1\u000f\u0001BK\u0002\u0013\u0005A\u000f\u0003\u0005y\u0001\tE\t\u0015!\u0003v\u0011!I\bA!f\u0001\n\u0003Q\b\u0002\u0003@\u0001\u0005#\u0005\u000b\u0011B>\t\u0011}\u0004!Q3A\u0005\u0002iD\u0011\"!\u0001\u0001\u0005#\u0005\u000b\u0011B>\t\u0015\u0005\r\u0001A!f\u0001\n\u0003\t)\u0001\u0003\u0006\u0002B\u0001\u0011\t\u0012)A\u0005\u0003\u000fA!\"a\u0011\u0001\u0005#\u0007I\u0011AA#\u0011)\t\u0019\u0006\u0001BA\u0002\u0013\u0005\u0011Q\u000b\u0005\u000b\u0003C\u0002!\u0011#Q!\n\u0005\u001d\u0003bBA2\u0001\u0011\u0005\u0011Q\r\u0005\u0007\u0003o\u0002A\u0011\u0001>\t\r\u0005e\u0004\u0001\"\u0001{\u0011\u0019\tY\b\u0001C\u0001u\"1\u0011Q\u0010\u0001\u0005\u0002QDq!a \u0001\t\u0003\t\t\tC\u0004\u0002\u000e\u0002!\t!a$\t\u000f\u0005E\u0005\u0001\"\u0001\u0002\u0010\"9\u00111\u0013\u0001\u0005\u0002\u0005=\u0005bBAK\u0001\u0011\u0005\u0011q\u0013\u0005\n\u0003?\u0003\u0011\u0011!C\u0001\u0003CC\u0011\"!-\u0001#\u0003%\t!a-\t\u0013\u0005%\u0007!%A\u0005\u0002\u0005-\u0007\"CAh\u0001E\u0005I\u0011AAi\u0011%\t)\u000eAI\u0001\n\u0003\t9\u000eC\u0005\u0002\\\u0002\t\n\u0011\"\u0001\u0002X\"I\u0011Q\u001c\u0001\u0012\u0002\u0013\u0005\u0011q\u001c\u0005\n\u0003G\u0004\u0011\u0013!C\u0001\u0003KD\u0011\"!;\u0001\u0003\u0003%\t%a;\t\u0013\u0005u\b!!A\u0005\u0002\u0005=\u0005\"CA\u0000\u0001\u0005\u0005I\u0011\u0001B\u0001\u0011%\u0011Y\u0001AA\u0001\n\u0003\u0012i\u0001C\u0005\u0003\u0018\u0001\t\t\u0011\"\u0001\u0003\u001a!I!Q\u0004\u0001\u0002\u0002\u0013\u0005#q\u0004\u0005\n\u0005G\u0001\u0011\u0011!C!\u0005KA\u0011Ba\n\u0001\u0003\u0003%\tE!\u000b\t\u0013\t-\u0002!!A\u0005B\t5r\u0001\u0003B\u0019o!\u0005qGa\r\u0007\u000fY:\u0004\u0012A\u001c\u00036!9\u00111\r\u0017\u0005\u0002\t\u0005\u0003b\u0002B\"Y\u0011\u0005!Q\t\u0005\n\u0005\u0007b\u0013\u0011!CA\u0005#B\u0011B!\u0019-#\u0003%\t!a8\t\u0013\t\rD&%A\u0005\u0002\u0005\u0015\b\"\u0003B3Y\u0005\u0005I\u0011\u0011B4\u0011%\u0011)\bLI\u0001\n\u0003\ty\u000eC\u0005\u0003x1\n\n\u0011\"\u0001\u0002f\"I!\u0011\u0010\u0017\u0002\u0002\u0013%!1\u0010\u0002\f\u0005\u0006$8\r[+J\t\u0006$\u0018M\u0003\u00029s\u0005\u0011Q/\u001b\u0006\u0003um\n\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u0005qj\u0014!B:qCJ\\'B\u0001 @\u0003\u0019\t\u0007/Y2iK*\t\u0001)A\u0002pe\u001e\u001cB\u0001\u0001\"I\u0017B\u00111IR\u0007\u0002\t*\tQ)A\u0003tG\u0006d\u0017-\u0003\u0002H\t\n1\u0011I\\=SK\u001a\u0004\"aQ%\n\u0005)#%a\u0002)s_\u0012,8\r\u001e\t\u0003\u0019Vs!!T*\u000f\u00059\u0013V\"A(\u000b\u0005A\u000b\u0016A\u0002\u001fs_>$hh\u0001\u0001\n\u0003\u0015K!\u0001\u0016#\u0002\u000fA\f7m[1hK&\u0011ak\u0016\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003)\u0012\u000b\u0011BY1uG\"$\u0016.\\3\u0016\u0003i\u0003\"a\u0017/\u000e\u0003eJ!!X\u001d\u0003\tQKW.Z\u0001\u000bE\u0006$8\r\u001b+j[\u0016\u0004\u0013aE:ue\u0016\fW.\u00133U_&s\u0007/\u001e;J]\u001a|W#A1\u0011\t\t4\u0017\u000e\u001c\b\u0003G\u0012\u0004\"A\u0014#\n\u0005\u0015$\u0015A\u0002)sK\u0012,g-\u0003\u0002hQ\n\u0019Q*\u00199\u000b\u0005\u0015$\u0005CA\"k\u0013\tYGIA\u0002J]R\u0004\"!\u001c9\u000e\u00039T!a\\\u001d\u0002\u0013M\u001c\u0007.\u001a3vY\u0016\u0014\u0018BA9o\u0005=\u0019FO]3b[&s\u0007/\u001e;J]\u001a|\u0017\u0001F:ue\u0016\fW.\u00133U_&s\u0007/\u001e;J]\u001a|\u0007%\u0001\btk\nl\u0017n]:j_:$\u0016.\\3\u0016\u0003U\u0004\"a\u0011<\n\u0005]$%\u0001\u0002'p]\u001e\fqb];c[&\u001c8/[8o)&lW\rI\u0001\u0014aJ|7-Z:tS:<7\u000b^1siRKW.Z\u000b\u0002wB\u00191\t`;\n\u0005u$%AB(qi&|g.\u0001\u000bqe>\u001cWm]:j]\u001e\u001cF/\u0019:u)&lW\rI\u0001\u0012aJ|7-Z:tS:<WI\u001c3US6,\u0017A\u00059s_\u000e,7o]5oO\u0016sG\rV5nK\u0002\n\u0001c\\;uaV$x\n]3sCRLwN\\:\u0016\u0005\u0005\u001d\u0001\u0003CA\u0005\u0003'\t9\"!\u000f\u000e\u0005\u0005-!\u0002BA\u0007\u0003\u001f\tq!\\;uC\ndWMC\u0002\u0002\u0012\u0011\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\t)\"a\u0003\u0003\u000f!\u000b7\u000f['baB!\u0011\u0011DA\u001a\u001d\u0011\tY\"a\f\u000f\t\u0005u\u0011Q\u0006\b\u0005\u0003?\tYC\u0004\u0003\u0002\"\u0005%b\u0002BA\u0012\u0003Oq1ATA\u0013\u0013\u0005\u0001\u0015B\u0001 @\u0013\taT(\u0003\u0002;w%\u0011\u0001(O\u0005\u0004\u0003c9\u0014\u0001H*ue\u0016\fW.\u001b8h\u0015>\u0014\u0007K]8he\u0016\u001c8\u000fT5ti\u0016tWM]\u0005\u0005\u0003k\t9D\u0001\u0006PkR\u0004X\u000f^(q\u0013\u0012T1!!\r8!\u0011\tY$!\u0010\u000e\u0003]J1!a\u00108\u0005UyU\u000f\u001e9vi>\u0003XM]1uS>tW+\u0013#bi\u0006\f\u0011c\\;uaV$x\n]3sCRLwN\\:!\u0003eyW\u000f\u001e9vi>\u0003\u0018\nZ*qCJ\\'j\u001c2JIB\u000b\u0017N]:\u0016\u0005\u0005\u001d\u0003#\u0002'\u0002J\u00055\u0013bAA&/\nA\u0011\n^3sC\ndW\r\u0005\u0003\u0002<\u0005=\u0013bAA)o\t9r*\u001e;qkR|\u0005/\u00133B]\u0012\u001c\u0006/\u0019:l\u0015>\u0014\u0017\nZ\u0001\u001e_V$\b/\u001e;Pa&#7\u000b]1sW*{'-\u00133QC&\u00148o\u0018\u0013fcR!\u0011qKA/!\r\u0019\u0015\u0011L\u0005\u0004\u00037\"%\u0001B+oSRD\u0011\"a\u0018\u000f\u0003\u0003\u0005\r!a\u0012\u0002\u0007a$\u0013'\u0001\u000epkR\u0004X\u000f^(q\u0013\u0012\u001c\u0006/\u0019:l\u0015>\u0014\u0017\n\u001a)bSJ\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0011\u0003O\nI'a\u001b\u0002n\u0005=\u0014\u0011OA:\u0003k\u00022!a\u000f\u0001\u0011\u0015A\u0006\u00031\u0001[\u0011\u0015y\u0006\u00031\u0001b\u0011\u0015\u0019\b\u00031\u0001v\u0011\u0015I\b\u00031\u0001|\u0011\u0015y\b\u00031\u0001|\u0011%\t\u0019\u0001\u0005I\u0001\u0002\u0004\t9\u0001C\u0005\u0002DA\u0001\n\u00111\u0001\u0002H\u0005y1o\u00195fIVd\u0017N\\4EK2\f\u00170A\bqe>\u001cWm]:j]\u001e$U\r\\1z\u0003)!x\u000e^1m\t\u0016d\u0017-_\u0001\u000b]Vl'+Z2pe\u0012\u001c\u0018!G;qI\u0006$XmT;uaV$x\n]3sCRLwN\\%oM>$B!a\u0016\u0002\u0004\"9\u0011QQ\u000bA\u0002\u0005\u001d\u0015aE8viB,Ho\u00149fe\u0006$\u0018n\u001c8J]\u001a|\u0007cA7\u0002\n&\u0019\u00111\u00128\u0003'=+H\u000f];u\u001fB,'/\u0019;j_:LeNZ8\u0002#9,XNR1jY\u0016$w*\u001e;qkR|\u0005/F\u0001j\u0003EqW/\\!di&4XmT;uaV$x\n]\u0001\u0015]Vl7i\\7qY\u0016$X\rZ(viB,Ho\u00149\u0002\u0011%\u001ch)Y5mK\u0012,\"!!'\u0011\u0007\r\u000bY*C\u0002\u0002\u001e\u0012\u0013qAQ8pY\u0016\fg.\u0001\u0003d_BLH\u0003EA4\u0003G\u000b)+a*\u0002*\u0006-\u0016QVAX\u0011\u001dA&\u0004%AA\u0002iCqa\u0018\u000e\u0011\u0002\u0003\u0007\u0011\rC\u0004t5A\u0005\t\u0019A;\t\u000feT\u0002\u0013!a\u0001w\"9qP\u0007I\u0001\u0002\u0004Y\b\"CA\u00025A\u0005\t\u0019AA\u0004\u0011%\t\u0019E\u0007I\u0001\u0002\u0004\t9%\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005U&f\u0001.\u00028.\u0012\u0011\u0011\u0018\t\u0005\u0003w\u000b)-\u0004\u0002\u0002>*!\u0011qXAa\u0003%)hn\u00195fG.,GMC\u0002\u0002D\u0012\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t9-!0\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u00055'fA1\u00028\u0006q1m\u001c9zI\u0011,g-Y;mi\u0012\u001aTCAAjU\r)\u0018qW\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00135+\t\tINK\u0002|\u0003o\u000babY8qs\u0012\"WMZ1vYR$S'\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001c\u0016\u0005\u0005\u0005(\u0006BA\u0004\u0003o\u000babY8qs\u0012\"WMZ1vYR$s'\u0006\u0002\u0002h*\"\u0011qIA\\\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u001e\t\u0005\u0003_\fI0\u0004\u0002\u0002r*!\u00111_A{\u0003\u0011a\u0017M\\4\u000b\u0005\u0005]\u0018\u0001\u00026bm\u0006LA!a?\u0002r\n11\u000b\u001e:j]\u001e\fA\u0002\u001d:pIV\u001cG/\u0011:jif\fa\u0002\u001d:pIV\u001cG/\u00127f[\u0016tG\u000f\u0006\u0003\u0003\u0004\t%\u0001cA\"\u0003\u0006%\u0019!q\u0001#\u0003\u0007\u0005s\u0017\u0010\u0003\u0005\u0002`\u0011\n\t\u00111\u0001j\u0003=\u0001(o\u001c3vGRLE/\u001a:bi>\u0014XC\u0001B\b!\u0019\u0011\tBa\u0005\u0003\u00045\u0011\u0011qB\u0005\u0005\u0005+\tyA\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BAM\u00057A\u0011\"a\u0018'\u0003\u0003\u0005\rAa\u0001\u0002%A\u0014x\u000eZ;di\u0016cW-\\3oi:\u000bW.\u001a\u000b\u0005\u0003[\u0014\t\u0003\u0003\u0005\u0002`\u001d\n\t\u00111\u0001j\u0003!A\u0017m\u001d5D_\u0012,G#A5\u0002\u0011Q|7\u000b\u001e:j]\u001e$\"!!<\u0002\r\u0015\fX/\u00197t)\u0011\tIJa\f\t\u0013\u0005}#&!AA\u0002\t\r\u0011a\u0003\"bi\u000eDW+\u0013#bi\u0006\u00042!a\u000f-'\u0011a#Ia\u000e\u0011\t\te\"qH\u0007\u0003\u0005wQAA!\u0010\u0002v\u0006\u0011\u0011n\\\u0005\u0004-\nmBC\u0001B\u001a\u0003\u0015\t\u0007\u000f\u001d7z)\u0011\t9Ga\u0012\t\u000f\t%c\u00061\u0001\u0003L\u0005I!-\u0019;dQ&sgm\u001c\t\u0004[\n5\u0013b\u0001B(]\nI!)\u0019;dQ&sgm\u001c\u000b\u0011\u0003O\u0012\u0019F!\u0016\u0003X\te#1\fB/\u0005?BQ\u0001W\u0018A\u0002iCQaX\u0018A\u0002\u0005DQa]\u0018A\u0002UDQ!_\u0018A\u0002mDQa`\u0018A\u0002mD\u0011\"a\u00010!\u0003\u0005\r!a\u0002\t\u0013\u0005\rs\u0006%AA\u0002\u0005\u001d\u0013aD1qa2LH\u0005Z3gCVdG\u000f\n\u001c\u0002\u001f\u0005\u0004\b\u000f\\=%I\u00164\u0017-\u001e7uI]\nq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003j\tE\u0004\u0003B\"}\u0005W\u0002Bb\u0011B75\u0006,8p_A\u0004\u0003\u000fJ1Aa\u001cE\u0005\u0019!V\u000f\u001d7fo!I!1\u000f\u001a\u0002\u0002\u0003\u0007\u0011qM\u0001\u0004q\u0012\u0002\u0014a\u0007\u0013mKN\u001c\u0018N\\5uI\u001d\u0014X-\u0019;fe\u0012\"WMZ1vYR$c'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$HeN\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005{\u0002B!a<\u0003\u0000%!!\u0011QAy\u0005\u0019y%M[3di\u0002"
)
public class BatchUIData implements Product, Serializable {
   private final Time batchTime;
   private final Map streamIdToInputInfo;
   private final long submissionTime;
   private final Option processingStartTime;
   private final Option processingEndTime;
   private final HashMap outputOperations;
   private Iterable outputOpIdSparkJobIdPairs;

   public static Iterable $lessinit$greater$default$7() {
      return BatchUIData$.MODULE$.$lessinit$greater$default$7();
   }

   public static HashMap $lessinit$greater$default$6() {
      return BatchUIData$.MODULE$.$lessinit$greater$default$6();
   }

   public static Option unapply(final BatchUIData x$0) {
      return BatchUIData$.MODULE$.unapply(x$0);
   }

   public static Iterable apply$default$7() {
      return BatchUIData$.MODULE$.apply$default$7();
   }

   public static HashMap apply$default$6() {
      return BatchUIData$.MODULE$.apply$default$6();
   }

   public static BatchUIData apply(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final HashMap outputOperations, final Iterable outputOpIdSparkJobIdPairs) {
      return BatchUIData$.MODULE$.apply(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, outputOperations, outputOpIdSparkJobIdPairs);
   }

   public static BatchUIData apply(final BatchInfo batchInfo) {
      return BatchUIData$.MODULE$.apply(batchInfo);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public Time batchTime() {
      return this.batchTime;
   }

   public Map streamIdToInputInfo() {
      return this.streamIdToInputInfo;
   }

   public long submissionTime() {
      return this.submissionTime;
   }

   public Option processingStartTime() {
      return this.processingStartTime;
   }

   public Option processingEndTime() {
      return this.processingEndTime;
   }

   public HashMap outputOperations() {
      return this.outputOperations;
   }

   public Iterable outputOpIdSparkJobIdPairs() {
      return this.outputOpIdSparkJobIdPairs;
   }

   public void outputOpIdSparkJobIdPairs_$eq(final Iterable x$1) {
      this.outputOpIdSparkJobIdPairs = x$1;
   }

   public Option schedulingDelay() {
      return this.processingStartTime().map((JFunction1.mcJJ.sp)(x$1) -> x$1 - this.submissionTime());
   }

   public Option processingDelay() {
      return this.processingStartTime().flatMap((start) -> $anonfun$processingDelay$1(this, BoxesRunTime.unboxToLong(start)));
   }

   public Option totalDelay() {
      return this.processingEndTime().map((JFunction1.mcJJ.sp)(x$2) -> x$2 - this.submissionTime());
   }

   public long numRecords() {
      return BoxesRunTime.unboxToLong(((IterableOnceOps)this.streamIdToInputInfo().values().map((x$3) -> BoxesRunTime.boxToLong($anonfun$numRecords$1(x$3)))).sum(.MODULE$));
   }

   public void updateOutputOperationInfo(final OutputOperationInfo outputOperationInfo) {
      boolean var3;
      Predef var10000;
      label17: {
         label16: {
            var10000 = scala.Predef..MODULE$;
            Time var10001 = this.batchTime();
            Time var2 = outputOperationInfo.batchTime();
            if (var10001 == null) {
               if (var2 == null) {
                  break label16;
               }
            } else if (var10001.equals(var2)) {
               break label16;
            }

            var3 = false;
            break label17;
         }

         var3 = true;
      }

      var10000.assert(var3);
      this.outputOperations().update(BoxesRunTime.boxToInteger(outputOperationInfo.id()), OutputOperationUIData$.MODULE$.apply(outputOperationInfo));
   }

   public int numFailedOutputOp() {
      return this.outputOperations().values().count((x$4) -> BoxesRunTime.boxToBoolean($anonfun$numFailedOutputOp$1(x$4)));
   }

   public int numActiveOutputOp() {
      return this.outputOperations().values().count((x$5) -> BoxesRunTime.boxToBoolean($anonfun$numActiveOutputOp$1(x$5)));
   }

   public int numCompletedOutputOp() {
      return this.outputOperations().values().count((op) -> BoxesRunTime.boxToBoolean($anonfun$numCompletedOutputOp$1(op)));
   }

   public boolean isFailed() {
      return this.numFailedOutputOp() != 0;
   }

   public BatchUIData copy(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final HashMap outputOperations, final Iterable outputOpIdSparkJobIdPairs) {
      return new BatchUIData(batchTime, streamIdToInputInfo, submissionTime, processingStartTime, processingEndTime, outputOperations, outputOpIdSparkJobIdPairs);
   }

   public Time copy$default$1() {
      return this.batchTime();
   }

   public Map copy$default$2() {
      return this.streamIdToInputInfo();
   }

   public long copy$default$3() {
      return this.submissionTime();
   }

   public Option copy$default$4() {
      return this.processingStartTime();
   }

   public Option copy$default$5() {
      return this.processingEndTime();
   }

   public HashMap copy$default$6() {
      return this.outputOperations();
   }

   public Iterable copy$default$7() {
      return this.outputOpIdSparkJobIdPairs();
   }

   public String productPrefix() {
      return "BatchUIData";
   }

   public int productArity() {
      return 7;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return this.batchTime();
         }
         case 1 -> {
            return this.streamIdToInputInfo();
         }
         case 2 -> {
            return BoxesRunTime.boxToLong(this.submissionTime());
         }
         case 3 -> {
            return this.processingStartTime();
         }
         case 4 -> {
            return this.processingEndTime();
         }
         case 5 -> {
            return this.outputOperations();
         }
         case 6 -> {
            return this.outputOpIdSparkJobIdPairs();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof BatchUIData;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "batchTime";
         }
         case 1 -> {
            return "streamIdToInputInfo";
         }
         case 2 -> {
            return "submissionTime";
         }
         case 3 -> {
            return "processingStartTime";
         }
         case 4 -> {
            return "processingEndTime";
         }
         case 5 -> {
            return "outputOperations";
         }
         case 6 -> {
            return "outputOpIdSparkJobIdPairs";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.anyHash(this.batchTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.streamIdToInputInfo()));
      var1 = Statics.mix(var1, Statics.longHash(this.submissionTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.processingStartTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.processingEndTime()));
      var1 = Statics.mix(var1, Statics.anyHash(this.outputOperations()));
      var1 = Statics.mix(var1, Statics.anyHash(this.outputOpIdSparkJobIdPairs()));
      return Statics.finalizeHash(var1, 7);
   }

   public String toString() {
      return scala.runtime.ScalaRunTime..MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var16;
      if (this != x$1) {
         label91: {
            if (x$1 instanceof BatchUIData) {
               BatchUIData var4 = (BatchUIData)x$1;
               if (this.submissionTime() == var4.submissionTime()) {
                  label84: {
                     Time var10000 = this.batchTime();
                     Time var5 = var4.batchTime();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label84;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label84;
                     }

                     Map var11 = this.streamIdToInputInfo();
                     Map var6 = var4.streamIdToInputInfo();
                     if (var11 == null) {
                        if (var6 != null) {
                           break label84;
                        }
                     } else if (!var11.equals(var6)) {
                        break label84;
                     }

                     Option var12 = this.processingStartTime();
                     Option var7 = var4.processingStartTime();
                     if (var12 == null) {
                        if (var7 != null) {
                           break label84;
                        }
                     } else if (!var12.equals(var7)) {
                        break label84;
                     }

                     var12 = this.processingEndTime();
                     Option var8 = var4.processingEndTime();
                     if (var12 == null) {
                        if (var8 != null) {
                           break label84;
                        }
                     } else if (!var12.equals(var8)) {
                        break label84;
                     }

                     HashMap var14 = this.outputOperations();
                     HashMap var9 = var4.outputOperations();
                     if (var14 == null) {
                        if (var9 != null) {
                           break label84;
                        }
                     } else if (!var14.equals(var9)) {
                        break label84;
                     }

                     Iterable var15 = this.outputOpIdSparkJobIdPairs();
                     Iterable var10 = var4.outputOpIdSparkJobIdPairs();
                     if (var15 == null) {
                        if (var10 != null) {
                           break label84;
                        }
                     } else if (!var15.equals(var10)) {
                        break label84;
                     }

                     if (var4.canEqual(this)) {
                        break label91;
                     }
                  }
               }
            }

            var16 = false;
            return var16;
         }
      }

      var16 = true;
      return var16;
   }

   // $FF: synthetic method
   public static final Option $anonfun$processingDelay$1(final BatchUIData $this, final long start) {
      return $this.processingEndTime().map((JFunction1.mcJJ.sp)(end) -> end - start);
   }

   // $FF: synthetic method
   public static final long $anonfun$numRecords$1(final StreamInputInfo x$3) {
      return x$3.numRecords();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$numFailedOutputOp$1(final OutputOperationUIData x$4) {
      return x$4.failureReason().nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$numActiveOutputOp$1(final OutputOperationUIData x$5) {
      return x$5.endTime().isEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$numCompletedOutputOp$1(final OutputOperationUIData op) {
      return op.failureReason().isEmpty() && op.endTime().nonEmpty();
   }

   public BatchUIData(final Time batchTime, final Map streamIdToInputInfo, final long submissionTime, final Option processingStartTime, final Option processingEndTime, final HashMap outputOperations, final Iterable outputOpIdSparkJobIdPairs) {
      this.batchTime = batchTime;
      this.streamIdToInputInfo = streamIdToInputInfo;
      this.submissionTime = submissionTime;
      this.processingStartTime = processingStartTime;
      this.processingEndTime = processingEndTime;
      this.outputOperations = outputOperations;
      this.outputOpIdSparkJobIdPairs = outputOpIdSparkJobIdPairs;
      super();
      Product.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
