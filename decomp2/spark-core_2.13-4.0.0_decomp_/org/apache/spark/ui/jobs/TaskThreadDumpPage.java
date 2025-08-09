package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.ui.SparkUITab;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Option.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.Node;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005y3QAB\u0004\u0001\u0017EA\u0001B\u0006\u0001\u0003\u0002\u0003\u0006I\u0001\u0007\u0005\t7\u0001\u0011\t\u0011)A\u00059!)a\u0005\u0001C\u0001O!)A\u0006\u0001C\u0005[!)A\n\u0001C!\u001b\n\u0011B+Y:l)\"\u0014X-\u00193Ek6\u0004\b+Y4f\u0015\tA\u0011\"\u0001\u0003k_\n\u001c(B\u0001\u0006\f\u0003\t)\u0018N\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h'\t\u0001!\u0003\u0005\u0002\u0014)5\t\u0011\"\u0003\u0002\u0016\u0013\tIq+\u001a2V\u0013B\u000bw-Z\u0001\u0007a\u0006\u0014XM\u001c;\u0004\u0001A\u00111#G\u0005\u00035%\u0011!b\u00159be.,\u0016\nV1c\u0003\t\u00198\rE\u0002\u001eA\tj\u0011A\b\u0006\u0002?\u0005)1oY1mC&\u0011\u0011E\b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005\r\"S\"A\u0006\n\u0005\u0015Z!\u0001D*qCJ\\7i\u001c8uKb$\u0018A\u0002\u001fj]&$h\bF\u0002)U-\u0002\"!\u000b\u0001\u000e\u0003\u001dAQAF\u0002A\u0002aAQaG\u0002A\u0002q\tQ#\u001a=fGV$xN\u001d+ie\u0016\fG\rR;naV\u0013H\u000e\u0006\u0003/s\u0015;\u0005CA\u00187\u001d\t\u0001D\u0007\u0005\u00022=5\t!G\u0003\u00024/\u00051AH]8pizJ!!\u000e\u0010\u0002\rA\u0013X\rZ3g\u0013\t9\u0004H\u0001\u0004TiJLgn\u001a\u0006\u0003kyAQA\u000f\u0003A\u0002m\nqA]3rk\u0016\u001cH\u000f\u0005\u0002=\u00076\tQH\u0003\u0002?\u007f\u0005!\u0001\u000e\u001e;q\u0015\t\u0001\u0015)A\u0004tKJ4H.\u001a;\u000b\u0003\t\u000bqA[1lCJ$\u0018-\u0003\u0002E{\t\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0011\u00151E\u00011\u0001/\u0003))\u00070Z2vi>\u0014\u0018\n\u001a\u0005\u0006\u0011\u0012\u0001\r!S\u0001\u0011E2|7m[5oORC'/Z1e\u0013\u0012\u0004\"!\b&\n\u0005-s\"\u0001\u0002'p]\u001e\faA]3oI\u0016\u0014HC\u0001(^!\ryEk\u0016\b\u0003!Js!!M)\n\u0003}I!a\u0015\u0010\u0002\u000fA\f7m[1hK&\u0011QK\u0016\u0002\u0004'\u0016\f(BA*\u001f!\tA6,D\u0001Z\u0015\tQf$A\u0002y[2L!\u0001X-\u0003\t9{G-\u001a\u0005\u0006u\u0015\u0001\ra\u000f"
)
public class TaskThreadDumpPage extends WebUIPage {
   private final SparkUITab parent;
   private final Option sc;

   private String executorThreadDumpUrl(final HttpServletRequest request, final String executorId, final long blockingThreadId) {
      String uiRoot = UIUtils$.MODULE$.prependBaseUri(request, this.parent.basePath(), UIUtils$.MODULE$.prependBaseUri$default$3());
      return uiRoot + "/executors/?executorId=" + executorId + "#" + blockingThreadId + "_td_id";
   }

   public Seq render(final HttpServletRequest request) {
      String executorId = (String).MODULE$.apply(request.getParameter("executorId")).map((executorIdx) -> UIUtils$.MODULE$.decodeURLParameter(executorIdx)).getOrElse(() -> {
         throw new IllegalArgumentException("Missing executorId parameter");
      });
      long taskId = BoxesRunTime.unboxToLong(.MODULE$.apply(request.getParameter("taskId")).map((taskIdx) -> BoxesRunTime.boxToLong($anonfun$render$3(taskIdx))).getOrElse(() -> {
         throw new IllegalArgumentException("Missing taskId parameter");
      }));
      long time = System.currentTimeMillis();
      Option maybeThreadDump = ((SparkContext)this.sc.get()).getTaskThreadDump(taskId, executorId);
      Node content = (Node)maybeThreadDump.map((thread) -> {
         long threadId = thread.threadId();
         Option var10 = thread.blockedByThreadId();
         Object var10000;
         if (var10 instanceof Some var11) {
            long blockingThreadId = BoxesRunTime.unboxToLong(var11.value());
            Null var10004 = scala.xml.Null..MODULE$;
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            Blocked by\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var43 = new UnprefixedAttribute("href", this.executorThreadDumpUrl(request, executorId, blockingThreadId), $md);
            TopScope var10014 = scala.xml.TopScope..MODULE$;
            NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              Thread\n              "));
            $buf.$amp$plus(BoxesRunTime.boxToLong(blockingThreadId));
            $buf.$amp$plus(thread.blockedByLock());
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "a", var43, var10014, false, var10016.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            var10000 = new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         } else {
            if (!scala.None..MODULE$.equals(var10)) {
               throw new MatchError(var10);
            }

            var10000 = scala.xml.Text..MODULE$.apply("");
         }

         Node blockedBy = (Node)var10000;
         Seq synchronizers = (Seq)thread.synchronizers().map((l) -> "Lock(" + l + ")");
         Seq monitors = (Seq)thread.monitors().map((m) -> "Monitor(" + m + ")");
         String heldLocks = ((IterableOnceOps)synchronizers.$plus$plus(monitors)).mkString(", ");
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var44 = new UnprefixedAttribute("class", new Text("row"), $md);
         TopScope var50 = scala.xml.TopScope..MODULE$;
         NodeSeq var51 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var45 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         TopScope var52 = scala.xml.TopScope..MODULE$;
         NodeSeq var54 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10022 = scala.xml.Null..MODULE$;
         TopScope var10023 = scala.xml.TopScope..MODULE$;
         NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Updated at "));
         $buf.$amp$plus(UIUtils$.MODULE$.formatDate(time));
         $buf.$amp$plus(new Elem((String)null, "p", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var46 = new UnprefixedAttribute("class", UIUtils$.MODULE$.TABLE_CLASS_NOT_STRIPED() + " accordion-group", $md);
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         Null var10031 = scala.xml.Null..MODULE$;
         TopScope var10032 = scala.xml.TopScope..MODULE$;
         NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         Null var10040 = scala.xml.Null..MODULE$;
         TopScope var10041 = scala.xml.TopScope..MODULE$;
         NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Thread ID"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Thread Name"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Thread State"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var47 = new UnprefixedAttribute("title", new Text("Objects whose lock the thread currently holds"), $md);
         var47 = new UnprefixedAttribute("data-placement", new Text("top"), var47);
         var47 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var47);
         TopScope var10050 = scala.xml.TopScope..MODULE$;
         NodeSeq var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                  Thread Locks\n                "));
         $buf.$amp$plus(new Elem((String)null, "span", var47, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "thead", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10031 = scala.xml.Null..MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         Null var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(BoxesRunTime.boxToLong(threadId));
         $buf.$amp$plus(new Elem((String)null, "td", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(thread.threadName());
         $buf.$amp$plus(new Elem((String)null, "td", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(thread.threadState());
         $buf.$amp$plus(new Elem((String)null, "td", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n                "));
         var10049 = scala.xml.Null..MODULE$;
         var10050 = scala.xml.TopScope..MODULE$;
         var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(blockedBy);
         $buf.$amp$plus(heldLocks);
         $buf.$amp$plus(new Elem((String)null, "td", var10049, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new Elem((String)null, "tr", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "tbody", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "table", var46, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var45, var52, false, var54.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n\n        "));
         Null var10013 = scala.xml.Null..MODULE$;
         var52 = scala.xml.TopScope..MODULE$;
         var54 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         var10022 = scala.xml.Null..MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         var10031 = scala.xml.Null..MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(thread.toString());
         $buf.$amp$plus(new Elem((String)null, "pre", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "div", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var10013, var52, false, var54.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         return new Elem((String)null, "div", var44, var50, false, var51.seqToNodeSeq($buf));
      }).getOrElse(() -> scala.xml.Text..MODULE$.apply("Task " + taskId + " finished or some error occurred during dumping thread"));
      return UIUtils$.MODULE$.headerSparkPage(request, "Thread dump for task " + taskId, () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
   }

   // $FF: synthetic method
   public static final long $anonfun$render$3(final String taskId) {
      String decoded = UIUtils$.MODULE$.decodeURLParameter(taskId);
      return scala.collection.StringOps..MODULE$.toLong$extension(scala.Predef..MODULE$.augmentString(decoded));
   }

   public TaskThreadDumpPage(final SparkUITab parent, final Option sc) {
      super("taskThreadDump");
      this.parent = parent;
      this.sc = sc;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
