package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.scheduler.Schedulable;
import org.apache.spark.status.PoolData;
import org.apache.spark.ui.UIUtils$;
import scala.MatchError;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;
import scala.xml.Null.;

@ScalaSignature(
   bytes = "\u0006\u0005}3QAB\u0004\u0001\u0013EA\u0001\u0002\u0007\u0001\u0003\u0002\u0003\u0006IA\u0007\u0005\tc\u0001\u0011\t\u0011)A\u0005e!)a\u0007\u0001C\u0001o!)1\b\u0001C\u0001y!)\u0001\f\u0001C\u00053\nI\u0001k\\8m)\u0006\u0014G.\u001a\u0006\u0003\u0011%\tAA[8cg*\u0011!bC\u0001\u0003k&T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0003\u0001I\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0017!\u00029p_2\u001c8\u0001\u0001\t\u00057\t*3F\u0004\u0002\u001dAA\u0011Q\u0004F\u0007\u0002=)\u0011q$G\u0001\u0007yI|w\u000e\u001e \n\u0005\u0005\"\u0012A\u0002)sK\u0012,g-\u0003\u0002$I\t\u0019Q*\u00199\u000b\u0005\u0005\"\u0002C\u0001\u0014*\u001b\u00059#B\u0001\u0015\f\u0003%\u00198\r[3ek2,'/\u0003\u0002+O\tY1k\u00195fIVd\u0017M\u00197f!\tas&D\u0001.\u0015\tq3\"\u0001\u0004ti\u0006$Xo]\u0005\u0003a5\u0012\u0001\u0002U8pY\u0012\u000bG/Y\u0001\u0007a\u0006\u0014XM\u001c;\u0011\u0005M\"T\"A\u0004\n\u0005U:!!C*uC\u001e,7\u000fV1c\u0003\u0019a\u0014N\\5u}Q\u0019\u0001(\u000f\u001e\u0011\u0005M\u0002\u0001\"\u0002\r\u0004\u0001\u0004Q\u0002\"B\u0019\u0004\u0001\u0004\u0011\u0014!\u0003;p\u001d>$WmU3r)\tiD\nE\u0002?\u0007\u001as!aP!\u000f\u0005u\u0001\u0015\"A\u000b\n\u0005\t#\u0012a\u00029bG.\fw-Z\u0005\u0003\t\u0016\u00131aU3r\u0015\t\u0011E\u0003\u0005\u0002H\u00156\t\u0001J\u0003\u0002J)\u0005\u0019\u00010\u001c7\n\u0005-C%\u0001\u0002(pI\u0016DQ!\u0014\u0003A\u00029\u000bqA]3rk\u0016\u001cH\u000f\u0005\u0002P-6\t\u0001K\u0003\u0002R%\u0006!\u0001\u000e\u001e;q\u0015\t\u0019F+A\u0004tKJ4H.\u001a;\u000b\u0003U\u000bqA[1lCJ$\u0018-\u0003\u0002X!\n\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0003\u001d\u0001xn\u001c7S_^$B!\u0010.\\;\")Q*\u0002a\u0001\u001d\")A,\u0002a\u0001K\u0005\t1\u000fC\u0003_\u000b\u0001\u00071&A\u0001q\u0001"
)
public class PoolTable {
   private final Map pools;
   private final StagesTab parent;

   public Seq toNodeSeq(final HttpServletRequest request) {
      MetaData $md = .MODULE$;
      MetaData var17 = new UnprefixedAttribute("class", new Text("table table-bordered table-striped table-sm sortable table-fixed"), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = .MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Null var10031 = .MODULE$;
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Pool Name"));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = .MODULE$;
      MetaData var18 = new UnprefixedAttribute("title", new Text("Pool's minimum share of CPU\n             cores"), $md);
      var18 = new UnprefixedAttribute("data-placement", new Text("top"), var18);
      var18 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var18);
      TopScope var10041 = scala.xml.TopScope..MODULE$;
      NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Minimum Share"));
      $buf.$amp$plus(new Elem((String)null, "span", var18, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = .MODULE$;
      MetaData var21 = new UnprefixedAttribute("title", new Text("Pool's share of cluster\n             resources relative to others"), $md);
      var21 = new UnprefixedAttribute("data-placement", new Text("top"), var21);
      var21 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var21);
      var10041 = scala.xml.TopScope..MODULE$;
      var10043 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Pool Weight"));
      $buf.$amp$plus(new Elem((String)null, "span", var21, var10041, false, var10043.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Active Stages"));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Running Tasks"));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("SchedulingMode"));
      $buf.$amp$plus(new Elem((String)null, "th", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "tr", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "thead", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(this.pools.map((x0$1) -> {
         if (x0$1 != null) {
            Schedulable s = (Schedulable)x0$1._1();
            PoolData p = (PoolData)x0$1._2();
            return this.poolRow(request, s, p);
         } else {
            throw new MatchError(x0$1);
         }
      }));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "tbody", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "table", var17, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq poolRow(final HttpServletRequest request, final Schedulable s, final PoolData p) {
      int activeStages = p.stageIds().size();
      String href = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s/stages/pool?poolname=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{UIUtils$.MODULE$.prependBaseUri(request, this.parent.basePath(), UIUtils$.MODULE$.prependBaseUri$default$3()), URLEncoder.encode(p.name(), StandardCharsets.UTF_8.name())}));
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = .MODULE$;
      MetaData var15 = new UnprefixedAttribute("href", href, $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(p.name());
      $buf.$amp$plus(new Elem((String)null, "a", var15, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(s.minShare()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(s.weight()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(activeStages));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(s.runningTasks()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(s.schedulingMode());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public PoolTable(final Map pools, final StagesTab parent) {
      this.pools = pools;
      this.parent = parent;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
