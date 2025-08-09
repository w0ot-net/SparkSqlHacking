package org.apache.spark.scheduler;

import java.io.Serializable;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.rdd.RDD;
import org.apache.spark.util.CallSite;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Product;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import scala.runtime.ScalaRunTime.;

@ScalaSignature(
   bytes = "\u0006\u0005\t\rg!B\u0017/\u0001:2\u0004\u0002C)\u0001\u0005+\u0007I\u0011\u0001*\t\u0011Y\u0003!\u0011#Q\u0001\nMC\u0001b\u0016\u0001\u0003\u0016\u0004%\t\u0001\u0017\u0005\tI\u0002\u0011\t\u0012)A\u00053\"AA\u000e\u0001BK\u0002\u0013\u0005Q\u000e\u0003\u0005~\u0001\tE\t\u0015!\u0003o\u0011)\t\u0019\u0001\u0001BK\u0002\u0013\u0005\u0011Q\u0001\u0005\u000b\u0003\u001b\u0001!\u0011#Q\u0001\n\u0005\u001d\u0001BCA\b\u0001\tU\r\u0011\"\u0001\u0002\u0012!Q\u0011q\u0004\u0001\u0003\u0012\u0003\u0006I!a\u0005\t\u0015\u0005\u0005\u0002A!f\u0001\n\u0003\t\u0019\u0003\u0003\u0006\u0002,\u0001\u0011\t\u0012)A\u0005\u0003KA!\"!\f\u0001\u0005+\u0007I\u0011AA\u0018\u0011)\t9\u0004\u0001B\tB\u0003%\u0011\u0011\u0007\u0005\u000b\u0003s\u0001!Q3A\u0005\u0002\u0005m\u0002BCA&\u0001\tE\t\u0015!\u0003\u0002>!9\u0011Q\n\u0001\u0005\u0002\u0005=\u0003\"CA>\u0001\u0005\u0005I\u0011AA?\u0011%\ty\tAI\u0001\n\u0003\t\t\nC\u0005\u0002(\u0002\t\n\u0011\"\u0001\u0002*\"I\u0011Q\u0017\u0001\u0012\u0002\u0013\u0005\u0011q\u0017\u0005\n\u0003\u0013\u0004\u0011\u0013!C\u0001\u0003\u0017D\u0011\"a4\u0001#\u0003%\t!!5\t\u0013\u0005U\u0007!%A\u0005\u0002\u0005]\u0007\"CAn\u0001E\u0005I\u0011AAo\u0011%\t\t\u000fAI\u0001\n\u0003\t\u0019\u000fC\u0005\u0002h\u0002\t\t\u0011\"\u0011\u0002j\"A\u0011q\u001f\u0001\u0002\u0002\u0013\u0005!\u000bC\u0005\u0002z\u0002\t\t\u0011\"\u0001\u0002|\"I!\u0011\u0001\u0001\u0002\u0002\u0013\u0005#1\u0001\u0005\n\u0005\u000f\u0001\u0011\u0011!C\u0001\u0005\u0013A\u0011Ba\u0005\u0001\u0003\u0003%\tE!\u0006\t\u0013\te\u0001!!A\u0005B\tm\u0001\"\u0003B\u000f\u0001\u0005\u0005I\u0011\tB\u0010\u0011%\u0011\t\u0003AA\u0001\n\u0003\u0012\u0019c\u0002\u0006\u0003(9\n\t\u0011#\u0001/\u0005S1\u0011\"\f\u0018\u0002\u0002#\u0005aFa\u000b\t\u000f\u00055S\u0005\"\u0001\u0003\\!I!QD\u0013\u0002\u0002\u0013\u0015#q\u0004\u0005\n\u0005;*\u0013\u0011!CA\u0005?B\u0011B!#&#\u0003%\t!a9\t\u0013\t-U%!A\u0005\u0002\n5\u0005\"\u0003B\\KE\u0005I\u0011AAr\u0011%\u0011I,JA\u0001\n\u0013\u0011YL\u0001\u0007K_\n\u001cVOY7jiR,GM\u0003\u00020a\u0005I1o\u00195fIVdWM\u001d\u0006\u0003cI\nQa\u001d9be.T!a\r\u001b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005)\u0014aA8sON)\u0001aN\u001fB\tB\u0011\u0001hO\u0007\u0002s)\t!(A\u0003tG\u0006d\u0017-\u0003\u0002=s\t1\u0011I\\=SK\u001a\u0004\"AP \u000e\u00039J!\u0001\u0011\u0018\u0003#\u0011\u000buiU2iK\u0012,H.\u001a:Fm\u0016tG\u000f\u0005\u00029\u0005&\u00111)\u000f\u0002\b!J|G-^2u!\t)eJ\u0004\u0002G\u0019:\u0011qiS\u0007\u0002\u0011*\u0011\u0011JS\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\t!(\u0003\u0002Ns\u00059\u0001/Y2lC\u001e,\u0017BA(Q\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\ti\u0015(A\u0003k_\nLE-F\u0001T!\tAD+\u0003\u0002Vs\t\u0019\u0011J\u001c;\u0002\r)|'-\u00133!\u0003!1\u0017N\\1m%\u0012#U#A-1\u0005i\u0013\u0007cA._A6\tAL\u0003\u0002^a\u0005\u0019!\u000f\u001a3\n\u0005}c&a\u0001*E\tB\u0011\u0011M\u0019\u0007\u0001\t%\u0019G!!A\u0001\u0002\u000b\u0005QMA\u0002`IE\n\u0011BZ5oC2\u0014F\t\u0012\u0011\u0012\u0005\u0019L\u0007C\u0001\u001dh\u0013\tA\u0017HA\u0004O_RD\u0017N\\4\u0011\u0005aR\u0017BA6:\u0005\r\te._\u0001\u0005MVt7-F\u0001oa\tyw\u0010E\u00039aJ4h0\u0003\u0002rs\tIa)\u001e8di&|gN\r\t\u0003gRl\u0011\u0001M\u0005\u0003kB\u00121\u0002V1tW\u000e{g\u000e^3yiB\u0012qo\u001f\t\u0004\u000bbT\u0018BA=Q\u0005!IE/\u001a:bi>\u0014\bCA1|\t%ah!!A\u0001\u0002\u000b\u0005QMA\u0002`II\nQAZ;oG\u0002\u0002\"!Y@\u0005\u0015\u0005\u0005a!!A\u0001\u0002\u000b\u0005QMA\u0002`IM\n!\u0002]1si&$\u0018n\u001c8t+\t\t9\u0001\u0005\u00039\u0003\u0013\u0019\u0016bAA\u0006s\t)\u0011I\u001d:bs\u0006Y\u0001/\u0019:uSRLwN\\:!\u0003!\u0019\u0017\r\u001c7TSR,WCAA\n!\u0011\t)\"a\u0007\u000e\u0005\u0005]!bAA\ra\u0005!Q\u000f^5m\u0013\u0011\ti\"a\u0006\u0003\u0011\r\u000bG\u000e\\*ji\u0016\f\u0011bY1mYNKG/\u001a\u0011\u0002\u00111L7\u000f^3oKJ,\"!!\n\u0011\u0007y\n9#C\u0002\u0002*9\u00121BS8c\u0019&\u001cH/\u001a8fe\u0006IA.[:uK:,'\u000fI\u0001\fCJ$\u0018NZ1diN+G/\u0006\u0002\u00022A\u00191/a\r\n\u0007\u0005U\u0002G\u0001\bK_\n\f%\u000f^5gC\u000e$8+\u001a;\u0002\u0019\u0005\u0014H/\u001b4bGR\u001cV\r\u001e\u0011\u0002\u0015A\u0014x\u000e]3si&,7/\u0006\u0002\u0002>A!\u0011qHA$\u001b\t\t\tE\u0003\u0003\u0002\u001a\u0005\r#BAA#\u0003\u0011Q\u0017M^1\n\t\u0005%\u0013\u0011\t\u0002\u000b!J|\u0007/\u001a:uS\u0016\u001c\u0018a\u00039s_B,'\u000f^5fg\u0002\na\u0001P5oSRtDCEA)\u0003'\n)&a\u0018\u0002r\u0005M\u0014QOA<\u0003s\u0002\"A\u0010\u0001\t\u000bE\u000b\u0002\u0019A*\t\r]\u000b\u0002\u0019AA,a\u0011\tI&!\u0018\u0011\tms\u00161\f\t\u0004C\u0006uCAC2\u0002V\u0005\u0005\t\u0011!B\u0001K\"1A.\u0005a\u0001\u0003C\u0002D!a\u0019\u0002pA9\u0001\b\u001d:\u0002f\u00055\u0004\u0007BA4\u0003W\u0002B!\u0012=\u0002jA\u0019\u0011-a\u001b\u0005\u0015q\fy&!A\u0001\u0002\u000b\u0005Q\rE\u0002b\u0003_\"1\"!\u0001\u0002`\u0005\u0005\t\u0011!B\u0001K\"9\u00111A\tA\u0002\u0005\u001d\u0001bBA\b#\u0001\u0007\u00111\u0003\u0005\b\u0003C\t\u0002\u0019AA\u0013\u0011\u001d\ti#\u0005a\u0001\u0003cA\u0011\"!\u000f\u0012!\u0003\u0005\r!!\u0010\u0002\t\r|\u0007/\u001f\u000b\u0013\u0003#\ny(!!\u0002\u0004\u0006\u0015\u0015qQAE\u0003\u0017\u000bi\tC\u0004R%A\u0005\t\u0019A*\t\u0011]\u0013\u0002\u0013!a\u0001\u0003/B\u0001\u0002\u001c\n\u0011\u0002\u0003\u0007\u0011\u0011\r\u0005\n\u0003\u0007\u0011\u0002\u0013!a\u0001\u0003\u000fA\u0011\"a\u0004\u0013!\u0003\u0005\r!a\u0005\t\u0013\u0005\u0005\"\u0003%AA\u0002\u0005\u0015\u0002\"CA\u0017%A\u0005\t\u0019AA\u0019\u0011%\tID\u0005I\u0001\u0002\u0004\ti$\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005M%fA*\u0002\u0016.\u0012\u0011q\u0013\t\u0005\u00033\u000b\u0019+\u0004\u0002\u0002\u001c*!\u0011QTAP\u0003%)hn\u00195fG.,GMC\u0002\u0002\"f\n!\"\u00198o_R\fG/[8o\u0013\u0011\t)+a'\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001a\u0016\u0005\u0005-\u0006\u0007BAW\u0003gSC!a,\u0002\u0016B!1LXAY!\r\t\u00171\u0017\u0003\nGR\t\t\u0011!A\u0003\u0002\u0015\fabY8qs\u0012\"WMZ1vYR$3'\u0006\u0002\u0002:*\"\u00111XAK!\u0019A\u0004O]A_SB\u001a\u0011qX>\u0011\u000b\u0005\u0005\u0017q\u0019>\u000e\u0005\u0005\r'bAAcs\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\u0007e\f\u0019-\u0001\bd_BLH\u0005Z3gCVdG\u000f\n\u001b\u0016\u0005\u00055'\u0006BA\u0004\u0003+\u000babY8qs\u0012\"WMZ1vYR$S'\u0006\u0002\u0002T*\"\u00111CAK\u00039\u0019w\u000e]=%I\u00164\u0017-\u001e7uIY*\"!!7+\t\u0005\u0015\u0012QS\u0001\u000fG>\u0004\u0018\u0010\n3fM\u0006,H\u000e\u001e\u00138+\t\tyN\u000b\u0003\u00022\u0005U\u0015AD2paf$C-\u001a4bk2$H\u0005O\u000b\u0003\u0003KTC!!\u0010\u0002\u0016\u0006i\u0001O]8ek\u000e$\bK]3gSb,\"!a;\u0011\t\u00055\u00181_\u0007\u0003\u0003_TA!!=\u0002D\u0005!A.\u00198h\u0013\u0011\t)0a<\u0003\rM#(/\u001b8h\u00031\u0001(o\u001c3vGR\f%/\u001b;z\u00039\u0001(o\u001c3vGR,E.Z7f]R$2![A\u007f\u0011!\ty0HA\u0001\u0002\u0004\u0019\u0016a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003\u0006A)\u0011\u0011YAdS\u0006A1-\u00198FcV\fG\u000e\u0006\u0003\u0003\f\tE\u0001c\u0001\u001d\u0003\u000e%\u0019!qB\u001d\u0003\u000f\t{w\u000e\\3b]\"A\u0011q`\u0010\u0002\u0002\u0003\u0007\u0011.\u0001\nqe>$Wo\u0019;FY\u0016lWM\u001c;OC6,G\u0003BAv\u0005/A\u0001\"a@!\u0003\u0003\u0005\raU\u0001\tQ\u0006\u001c\bnQ8eKR\t1+\u0001\u0005u_N#(/\u001b8h)\t\tY/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0005\u0017\u0011)\u0003\u0003\u0005\u0002\u0000\u000e\n\t\u00111\u0001j\u00031QuNY*vE6LG\u000f^3e!\tqTeE\u0003&\u0005[\u0011\t\u0006E\u000b\u00030\tU2K!\u000f\u0003B\u0005\u001d\u00111CA\u0013\u0003c\ti$!\u0015\u000e\u0005\tE\"b\u0001B\u001as\u00059!/\u001e8uS6,\u0017\u0002\u0002B\u001c\u0005c\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c89a\u0011\u0011YDa\u0010\u0011\tms&Q\b\t\u0004C\n}B!C2&\u0003\u0003\u0005\tQ!\u0001fa\u0011\u0011\u0019Ea\u0014\u0011\u000fa\u0002(O!\u0012\u0003NA\"!q\tB&!\u0011)\u0005P!\u0013\u0011\u0007\u0005\u0014Y\u0005B\u0005}K\u0005\u0005\t\u0011!B\u0001KB\u0019\u0011Ma\u0014\u0005\u0015\u0005\u0005Q%!A\u0001\u0002\u000b\u0005Q\r\u0005\u0003\u0003T\teSB\u0001B+\u0015\u0011\u00119&a\u0011\u0002\u0005%|\u0017bA(\u0003VQ\u0011!\u0011F\u0001\u0006CB\u0004H.\u001f\u000b\u0013\u0003#\u0012\tGa\u0019\u0003n\t}$\u0011\u0011BB\u0005\u000b\u00139\tC\u0003RQ\u0001\u00071\u000b\u0003\u0004XQ\u0001\u0007!Q\r\u0019\u0005\u0005O\u0012Y\u0007\u0005\u0003\\=\n%\u0004cA1\u0003l\u0011Q1Ma\u0019\u0002\u0002\u0003\u0005)\u0011A3\t\r1D\u0003\u0019\u0001B8a\u0011\u0011\tH! \u0011\u000fa\u0002(Oa\u001d\u0003|A\"!Q\u000fB=!\u0011)\u0005Pa\u001e\u0011\u0007\u0005\u0014I\b\u0002\u0006}\u0005[\n\t\u0011!A\u0003\u0002\u0015\u00042!\u0019B?\t-\t\tA!\u001c\u0002\u0002\u0003\u0005)\u0011A3\t\u000f\u0005\r\u0001\u00061\u0001\u0002\b!9\u0011q\u0002\u0015A\u0002\u0005M\u0001bBA\u0011Q\u0001\u0007\u0011Q\u0005\u0005\b\u0003[A\u0003\u0019AA\u0019\u0011%\tI\u0004\u000bI\u0001\u0002\u0004\ti$A\bbaBd\u0017\u0010\n3fM\u0006,H\u000e\u001e\u00139\u0003\u001d)h.\u00199qYf$BAa$\u00034B)\u0001H!%\u0003\u0016&\u0019!1S\u001d\u0003\r=\u0003H/[8o!IA$qS*\u0003\u001c\n\r\u0016qAA\n\u0003K\t\t$!\u0010\n\u0007\te\u0015H\u0001\u0004UkBdW\r\u000f\u0019\u0005\u0005;\u0013\t\u000b\u0005\u0003\\=\n}\u0005cA1\u0003\"\u0012I1MKA\u0001\u0002\u0003\u0015\t!\u001a\u0019\u0005\u0005K\u0013\t\fE\u00049aJ\u00149Ka,1\t\t%&Q\u0016\t\u0005\u000bb\u0014Y\u000bE\u0002b\u0005[#\u0011\u0002 \u0016\u0002\u0002\u0003\u0005)\u0011A3\u0011\u0007\u0005\u0014\t\f\u0002\u0006\u0002\u0002)\n\t\u0011!A\u0003\u0002\u0015D\u0011B!.+\u0003\u0003\u0005\r!!\u0015\u0002\u0007a$\u0003'A\u000e%Y\u0016\u001c8/\u001b8ji\u0012:'/Z1uKJ$C-\u001a4bk2$H\u0005O\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0005{\u0003B!!<\u0003@&!!\u0011YAx\u0005\u0019y%M[3di\u0002"
)
public class JobSubmitted implements DAGSchedulerEvent, Product, Serializable {
   private final int jobId;
   private final RDD finalRDD;
   private final Function2 func;
   private final int[] partitions;
   private final CallSite callSite;
   private final JobListener listener;
   private final JobArtifactSet artifactSet;
   private final Properties properties;

   public static Properties $lessinit$greater$default$8() {
      return JobSubmitted$.MODULE$.$lessinit$greater$default$8();
   }

   public static Option unapply(final JobSubmitted x$0) {
      return JobSubmitted$.MODULE$.unapply(x$0);
   }

   public static Properties apply$default$8() {
      return JobSubmitted$.MODULE$.apply$default$8();
   }

   public static JobSubmitted apply(final int jobId, final RDD finalRDD, final Function2 func, final int[] partitions, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      return JobSubmitted$.MODULE$.apply(jobId, finalRDD, func, partitions, callSite, listener, artifactSet, properties);
   }

   public static Function1 tupled() {
      return JobSubmitted$.MODULE$.tupled();
   }

   public static Function1 curried() {
      return JobSubmitted$.MODULE$.curried();
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public int jobId() {
      return this.jobId;
   }

   public RDD finalRDD() {
      return this.finalRDD;
   }

   public Function2 func() {
      return this.func;
   }

   public int[] partitions() {
      return this.partitions;
   }

   public CallSite callSite() {
      return this.callSite;
   }

   public JobListener listener() {
      return this.listener;
   }

   public JobArtifactSet artifactSet() {
      return this.artifactSet;
   }

   public Properties properties() {
      return this.properties;
   }

   public JobSubmitted copy(final int jobId, final RDD finalRDD, final Function2 func, final int[] partitions, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      return new JobSubmitted(jobId, finalRDD, func, partitions, callSite, listener, artifactSet, properties);
   }

   public int copy$default$1() {
      return this.jobId();
   }

   public RDD copy$default$2() {
      return this.finalRDD();
   }

   public Function2 copy$default$3() {
      return this.func();
   }

   public int[] copy$default$4() {
      return this.partitions();
   }

   public CallSite copy$default$5() {
      return this.callSite();
   }

   public JobListener copy$default$6() {
      return this.listener();
   }

   public JobArtifactSet copy$default$7() {
      return this.artifactSet();
   }

   public Properties copy$default$8() {
      return this.properties();
   }

   public String productPrefix() {
      return "JobSubmitted";
   }

   public int productArity() {
      return 8;
   }

   public Object productElement(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return BoxesRunTime.boxToInteger(this.jobId());
         }
         case 1 -> {
            return this.finalRDD();
         }
         case 2 -> {
            return this.func();
         }
         case 3 -> {
            return this.partitions();
         }
         case 4 -> {
            return this.callSite();
         }
         case 5 -> {
            return this.listener();
         }
         case 6 -> {
            return this.artifactSet();
         }
         case 7 -> {
            return this.properties();
         }
         default -> {
            return Statics.ioobe(x$1);
         }
      }
   }

   public Iterator productIterator() {
      return .MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof JobSubmitted;
   }

   public String productElementName(final int x$1) {
      switch (x$1) {
         case 0 -> {
            return "jobId";
         }
         case 1 -> {
            return "finalRDD";
         }
         case 2 -> {
            return "func";
         }
         case 3 -> {
            return "partitions";
         }
         case 4 -> {
            return "callSite";
         }
         case 5 -> {
            return "listener";
         }
         case 6 -> {
            return "artifactSet";
         }
         case 7 -> {
            return "properties";
         }
         default -> {
            return (String)Statics.ioobe(x$1);
         }
      }
   }

   public int hashCode() {
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, this.jobId());
      var1 = Statics.mix(var1, Statics.anyHash(this.finalRDD()));
      var1 = Statics.mix(var1, Statics.anyHash(this.func()));
      var1 = Statics.mix(var1, Statics.anyHash(this.partitions()));
      var1 = Statics.mix(var1, Statics.anyHash(this.callSite()));
      var1 = Statics.mix(var1, Statics.anyHash(this.listener()));
      var1 = Statics.mix(var1, Statics.anyHash(this.artifactSet()));
      var1 = Statics.mix(var1, Statics.anyHash(this.properties()));
      return Statics.finalizeHash(var1, 8);
   }

   public String toString() {
      return .MODULE$._toString(this);
   }

   public boolean equals(final Object x$1) {
      boolean var16;
      if (this != x$1) {
         label95: {
            if (x$1 instanceof JobSubmitted) {
               JobSubmitted var4 = (JobSubmitted)x$1;
               if (this.jobId() == var4.jobId()) {
                  label87: {
                     RDD var10000 = this.finalRDD();
                     RDD var5 = var4.finalRDD();
                     if (var10000 == null) {
                        if (var5 != null) {
                           break label87;
                        }
                     } else if (!var10000.equals(var5)) {
                        break label87;
                     }

                     Function2 var11 = this.func();
                     Function2 var6 = var4.func();
                     if (var11 == null) {
                        if (var6 != null) {
                           break label87;
                        }
                     } else if (!var11.equals(var6)) {
                        break label87;
                     }

                     if (this.partitions() == var4.partitions()) {
                        label88: {
                           CallSite var12 = this.callSite();
                           CallSite var7 = var4.callSite();
                           if (var12 == null) {
                              if (var7 != null) {
                                 break label88;
                              }
                           } else if (!var12.equals(var7)) {
                              break label88;
                           }

                           JobListener var13 = this.listener();
                           JobListener var8 = var4.listener();
                           if (var13 == null) {
                              if (var8 != null) {
                                 break label88;
                              }
                           } else if (!var13.equals(var8)) {
                              break label88;
                           }

                           JobArtifactSet var14 = this.artifactSet();
                           JobArtifactSet var9 = var4.artifactSet();
                           if (var14 == null) {
                              if (var9 != null) {
                                 break label88;
                              }
                           } else if (!var14.equals(var9)) {
                              break label88;
                           }

                           Properties var15 = this.properties();
                           Properties var10 = var4.properties();
                           if (var15 == null) {
                              if (var10 != null) {
                                 break label88;
                              }
                           } else if (!var15.equals(var10)) {
                              break label88;
                           }

                           if (var4.canEqual(this)) {
                              break label95;
                           }
                        }
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

   public JobSubmitted(final int jobId, final RDD finalRDD, final Function2 func, final int[] partitions, final CallSite callSite, final JobListener listener, final JobArtifactSet artifactSet, final Properties properties) {
      this.jobId = jobId;
      this.finalRDD = finalRDD;
      this.func = func;
      this.partitions = partitions;
      this.callSite = callSite;
      this.listener = listener;
      this.artifactSet = artifactSet;
      this.properties = properties;
      Product.$init$(this);
   }
}
