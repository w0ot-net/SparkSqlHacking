package org.apache.spark.ui.exec;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.ui.SparkUITab;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import scala.Option;
import scala.Option.;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.util.matching.Regex;
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
   bytes = "\u0006\u0005a3Qa\u0002\u0005\u0001\u0015IA\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\t9\u0001\u0011\t\u0011)A\u0005;!)q\u0005\u0001C\u0001Q!9Q\u0006\u0001b\u0001\n\u0003q\u0003BB\u001c\u0001A\u0003%q\u0006C\u00039\u0001\u0011\u0005\u0011HA\rFq\u0016\u001cW\u000f^8s\u0011\u0016\f\u0007\u000fS5ti><'/Y7QC\u001e,'BA\u0005\u000b\u0003\u0011)\u00070Z2\u000b\u0005-a\u0011AA;j\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7C\u0001\u0001\u0014!\t!R#D\u0001\u000b\u0013\t1\"BA\u0005XK\n,\u0016\nU1hK\u00061\u0001/\u0019:f]R\u001c\u0001\u0001\u0005\u0002\u00155%\u00111D\u0003\u0002\u000b'B\f'o[+J)\u0006\u0014\u0017AA:d!\rq\u0012eI\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1q\n\u001d;j_:\u0004\"\u0001J\u0013\u000e\u00031I!A\n\u0007\u0003\u0019M\u0003\u0018M]6D_:$X\r\u001f;\u0002\rqJg.\u001b;?)\rI3\u0006\f\t\u0003U\u0001i\u0011\u0001\u0003\u0005\u0006/\r\u0001\r!\u0007\u0005\u00069\r\u0001\r!H\u0001\ba\u0006$H/\u001a:o+\u0005y\u0003C\u0001\u00196\u001b\u0005\t$B\u0001\u001a4\u0003!i\u0017\r^2iS:<'B\u0001\u001b \u0003\u0011)H/\u001b7\n\u0005Y\n$!\u0002*fO\u0016D\u0018\u0001\u00039biR,'O\u001c\u0011\u0002\rI,g\u000eZ3s)\tQD\nE\u0002<\u0007\u001as!\u0001P!\u000f\u0005u\u0002U\"\u0001 \u000b\u0005}B\u0012A\u0002\u001fs_>$h(C\u0001!\u0013\t\u0011u$A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0011+%aA*fc*\u0011!i\b\t\u0003\u000f*k\u0011\u0001\u0013\u0006\u0003\u0013~\t1\u0001_7m\u0013\tY\u0005J\u0001\u0003O_\u0012,\u0007\"B'\u0007\u0001\u0004q\u0015a\u0002:fcV,7\u000f\u001e\t\u0003\u001fZk\u0011\u0001\u0015\u0006\u0003#J\u000bA\u0001\u001b;ua*\u00111\u000bV\u0001\bg\u0016\u0014h\u000f\\3u\u0015\u0005)\u0016a\u00026bW\u0006\u0014H/Y\u0005\u0003/B\u0013!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\u0002"
)
public class ExecutorHeapHistogramPage extends WebUIPage {
   private final SparkUITab parent;
   private final Option sc;
   private final Regex pattern;

   public Regex pattern() {
      return this.pattern;
   }

   public Seq render(final HttpServletRequest request) {
      String executorId = (String).MODULE$.apply(request.getParameter("executorId")).map((executorIdx) -> UIUtils$.MODULE$.decodeURLParameter(executorIdx)).getOrElse(() -> {
         throw new IllegalArgumentException("Missing executorId parameter");
      });
      long time = System.currentTimeMillis();
      Option maybeHeapHistogram = ((SparkContext)this.sc.get()).getExecutorHeapHistogram(executorId);
      Node content = (Node)maybeHeapHistogram.map((heapHistogram) -> {
         Object[] rows = scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])heapHistogram), (row) -> {
            if (row != null) {
               Option var4 = this.pattern().unapplySeq(row);
               if (!var4.isEmpty() && var4.get() != null && ((List)var4.get()).lengthCompare(5) == 0) {
                  String rank = (String)((LinearSeqOps)var4.get()).apply(0);
                  String instances = (String)((LinearSeqOps)var4.get()).apply(1);
                  String bytes = (String)((LinearSeqOps)var4.get()).apply(2);
                  String name = (String)((LinearSeqOps)var4.get()).apply(3);
                  String module = (String)((LinearSeqOps)var4.get()).apply(4);
                  MetaData $md = scala.xml.Null..MODULE$;
                  MetaData var28 = new UnprefixedAttribute("class", new Text("accordion-heading"), $md);
                  TopScope var30 = scala.xml.TopScope..MODULE$;
                  NodeSeq var31 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(new Text("\n              "));
                  Null var35 = scala.xml.Null..MODULE$;
                  TopScope var43 = scala.xml.TopScope..MODULE$;
                  NodeSeq var51 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(rank);
                  $buf.$amp$plus(new Elem((String)null, "td", var35, var43, false, var51.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  var35 = scala.xml.Null..MODULE$;
                  var43 = scala.xml.TopScope..MODULE$;
                  var51 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(instances);
                  $buf.$amp$plus(new Elem((String)null, "td", var35, var43, false, var51.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  var35 = scala.xml.Null..MODULE$;
                  var43 = scala.xml.TopScope..MODULE$;
                  var51 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(bytes);
                  $buf.$amp$plus(new Elem((String)null, "td", var35, var43, false, var51.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  var35 = scala.xml.Null..MODULE$;
                  var43 = scala.xml.TopScope..MODULE$;
                  var51 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(name);
                  $buf.$amp$plus(new Elem((String)null, "td", var35, var43, false, var51.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  var35 = scala.xml.Null..MODULE$;
                  var43 = scala.xml.TopScope..MODULE$;
                  var51 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(module);
                  $buf.$amp$plus(new Elem((String)null, "td", var35, var43, false, var51.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n            "));
                  return new Elem((String)null, "tr", var28, var30, false, var31.seqToNodeSeq($buf));
               }
            }

            if (row != null) {
               Option var17 = this.pattern().unapplySeq(row);
               if (!var17.isEmpty() && var17.get() != null && ((List)var17.get()).lengthCompare(4) == 0) {
                  String rank = (String)((LinearSeqOps)var17.get()).apply(0);
                  String instances = (String)((LinearSeqOps)var17.get()).apply(1);
                  String bytes = (String)((LinearSeqOps)var17.get()).apply(2);
                  String name = (String)((LinearSeqOps)var17.get()).apply(3);
                  MetaData $md = scala.xml.Null..MODULE$;
                  MetaData var29 = new UnprefixedAttribute("class", new Text("accordion-heading"), $md);
                  TopScope var10005 = scala.xml.TopScope..MODULE$;
                  NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(new Text("\n              "));
                  Null var10013 = scala.xml.Null..MODULE$;
                  TopScope var10014 = scala.xml.TopScope..MODULE$;
                  NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(rank);
                  $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  var10013 = scala.xml.Null..MODULE$;
                  var10014 = scala.xml.TopScope..MODULE$;
                  var10016 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(instances);
                  $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  var10013 = scala.xml.Null..MODULE$;
                  var10014 = scala.xml.TopScope..MODULE$;
                  var10016 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(bytes);
                  $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  var10013 = scala.xml.Null..MODULE$;
                  var10014 = scala.xml.TopScope..MODULE$;
                  var10016 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(name);
                  $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  $buf.$amp$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
                  $buf.$amp$plus(new Text("\n            "));
                  return new Elem((String)null, "tr", var29, var10005, false, var10007.seqToNodeSeq($buf));
               }
            }

            return BoxedUnit.UNIT;
         }, scala.reflect.ClassTag..MODULE$.Any());
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var19 = new UnprefixedAttribute("class", new Text("row"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var20 = new UnprefixedAttribute("class", new Text("col-12"), $md);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
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
         MetaData var21 = new UnprefixedAttribute("class", UIUtils$.MODULE$.TABLE_CLASS_STRIPED() + " accordion-group sortable", $md);
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
         $buf.$amp$plus(new Text("Rank"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Instances"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Bytes"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Class Name"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n              "));
         var10040 = scala.xml.Null..MODULE$;
         var10041 = scala.xml.TopScope..MODULE$;
         var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Module"));
         $buf.$amp$plus(new Elem((String)null, "th", var10040, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "thead", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10031 = scala.xml.Null..MODULE$;
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(rows);
         $buf.$amp$plus(new Elem((String)null, "tbody", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "table", var21, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var20, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         return new Elem((String)null, "div", var19, var10005, false, var10007.seqToNodeSeq($buf));
      }).getOrElse(() -> scala.xml.Text..MODULE$.apply("Error fetching heap histogram"));
      return UIUtils$.MODULE$.headerSparkPage(request, "Heap Histogram for Executor " + executorId, () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
   }

   public ExecutorHeapHistogramPage(final SparkUITab parent, final Option sc) {
      super("heapHistogram");
      this.parent = parent;
      this.sc = sc;
      this.pattern = scala.collection.StringOps..MODULE$.r$extension(scala.Predef..MODULE$.augmentString("\\s*([0-9]+):\\s+([0-9]+)\\s+([0-9]+)\\s+(\\S+)(.*)"));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
