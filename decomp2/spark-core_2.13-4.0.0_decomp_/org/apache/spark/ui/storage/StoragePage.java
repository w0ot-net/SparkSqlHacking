package org.apache.spark.ui.storage;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.StreamBlockData;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import org.apache.spark.ui.SparkUITab;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.MatchError;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterable;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005%e!\u0002\f\u0018\u0001e\t\u0003\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011-\u0002!\u0011!Q\u0001\n1BQA\r\u0001\u0005\u0002MBQ\u0001\u000f\u0001\u0005\u0002eBaA\u0017\u0001\u0005\u0002]Y\u0006b\u00025\u0001\u0005\u0004%I!\u001b\u0005\u0007s\u0002\u0001\u000b\u0011\u00026\t\u000fi\u0004!\u0019!C\u0001w\"9\u00111\u0001\u0001!\u0002\u0013a\bbBA\u0003\u0001\u0011%\u0011q\u0001\u0005\t\u0003\u001f\u0001A\u0011A\f\u0002\u0012!9\u0011q\u0004\u0001\u0005\n\u0005\u0005\u0002\u0002CA\u0013\u0001\t\u0007I\u0011B5\t\u000f\u0005\u001d\u0002\u0001)A\u0005U\"9\u0011\u0011\u0006\u0001\u0005\n\u0005-\u0002bBA\u001b\u0001\u0011%\u0011q\u0007\u0005\t\u0003#\u0002!\u0019!C\u0005S\"9\u00111\u000b\u0001!\u0002\u0013Q\u0007bBA+\u0001\u0011%\u0011q\u000b\u0005\b\u0003;\u0002A\u0011BA0\u0011!\tY\b\u0001C\u0001/\u0005u$aC*u_J\fw-\u001a)bO\u0016T!\u0001G\r\u0002\u000fM$xN]1hK*\u0011!dG\u0001\u0003k&T!\u0001H\u000f\u0002\u000bM\u0004\u0018M]6\u000b\u0005yy\u0012AB1qC\u000eDWMC\u0001!\u0003\ry'oZ\n\u0003\u0001\t\u0002\"a\t\u0013\u000e\u0003eI!!J\r\u0003\u0013]+'-V%QC\u001e,\u0017A\u00029be\u0016tGo\u0001\u0001\u0011\u0005\rJ\u0013B\u0001\u0016\u001a\u0005)\u0019\u0006/\u0019:l+&#\u0016MY\u0001\u0006gR|'/\u001a\t\u0003[Aj\u0011A\f\u0006\u0003_m\taa\u001d;biV\u001c\u0018BA\u0019/\u00059\t\u0005\u000f]*uCR,8o\u0015;pe\u0016\fa\u0001P5oSRtDc\u0001\u001b7oA\u0011Q\u0007A\u0007\u0002/!)ae\u0001a\u0001Q!)1f\u0001a\u0001Y\u00051!/\u001a8eKJ$\"A\u000f(\u0011\u0007m*\u0005J\u0004\u0002=\u0005:\u0011Q\bQ\u0007\u0002})\u0011qhJ\u0001\u0007yI|w\u000e\u001e \n\u0003\u0005\u000bQa]2bY\u0006L!a\u0011#\u0002\u000fA\f7m[1hK*\t\u0011)\u0003\u0002G\u000f\n\u00191+Z9\u000b\u0005\r#\u0005CA%M\u001b\u0005Q%BA&E\u0003\rAX\u000e\\\u0005\u0003\u001b*\u0013AAT8eK\")q\n\u0002a\u0001!\u00069!/Z9vKN$\bCA)Y\u001b\u0005\u0011&BA*U\u0003\u0011AG\u000f\u001e9\u000b\u0005U3\u0016aB:feZdW\r\u001e\u0006\u0002/\u00069!.Y6beR\f\u0017BA-S\u0005IAE\u000f\u001e9TKJ4H.\u001a;SKF,Xm\u001d;\u0002\u0011I$G\rV1cY\u0016$2A\u000f/^\u0011\u0015yU\u00011\u0001Q\u0011\u0015qV\u00011\u0001`\u0003\u0011\u0011H\rZ:\u0011\u0007m*\u0005\r\u0005\u0002bM6\t!M\u0003\u0002dI\u0006\u0011a/\r\u0006\u0003K:\n1!\u00199j\u0013\t9'M\u0001\bS\t\u0012\u001bFo\u001c:bO\u0016LeNZ8\u0002\u0013I$G\rS3bI\u0016\u0014X#\u00016\u0011\u0007-\u0004\u0018/D\u0001m\u0015\tig.A\u0005j[6,H/\u00192mK*\u0011q\u000eR\u0001\u000bG>dG.Z2uS>t\u0017B\u0001$m!\t\u0011x/D\u0001t\u0015\t!X/\u0001\u0003mC:<'\"\u0001<\u0002\t)\fg/Y\u0005\u0003qN\u0014aa\u0015;sS:<\u0017A\u0003:eI\"+\u0017\rZ3sA\u0005AAo\\8mi&\u00048/F\u0001}!\rY\u0007/ \t\u0004}~\fX\"\u0001#\n\u0007\u0005\u0005AI\u0001\u0004PaRLwN\\\u0001\ni>|G\u000e^5qg\u0002\naA\u001d3e%><H#\u0002\u001e\u0002\n\u0005-\u0001\"B(\u000b\u0001\u0004\u0001\u0006BBA\u0007\u0015\u0001\u0007\u0001-A\u0002sI\u0012\f1C]3dK&4XM\u001d\"m_\u000e\\G+\u00192mKN$2AOA\n\u0011\u001d\t)b\u0003a\u0001\u0003/\taA\u00197pG.\u001c\b\u0003B\u001eF\u00033\u00012!LA\u000e\u0013\r\tiB\f\u0002\u0010'R\u0014X-Y7CY>\u001c7\u000eR1uC\u0006!R\r_3dkR|'/T3ue&\u001c7\u000fV1cY\u0016$2AOA\u0012\u0011\u001d\t)\u0002\u0004a\u0001\u0003/\t!$\u001a=fGV$xN]'fiJL7m\u001d+bE2,\u0007*Z1eKJ\f1$\u001a=fGV$xN]'fiJL7m\u001d+bE2,\u0007*Z1eKJ\u0004\u0013aF3yK\u000e,Ho\u001c:NKR\u0014\u0018nY:UC\ndWMU8x)\rQ\u0014Q\u0006\u0005\u0007_=\u0001\r!a\f\u0011\u0007U\n\t$C\u0002\u00024]\u0011Q#\u0012=fGV$xN]*ue\u0016\fWnU;n[\u0006\u0014\u00180\u0001\ttiJ,\u0017-\u001c\"m_\u000e\\G+\u00192mKR\u0019!(!\u000f\t\u000f\u0005U\u0001\u00031\u0001\u0002<A!1(RA\u001f!\u001dq\u0018qHA\"\u0003/I1!!\u0011E\u0005\u0019!V\u000f\u001d7feA!\u0011QIA'\u001d\u0011\t9%!\u0013\u0011\u0005u\"\u0015bAA&\t\u00061\u0001K]3eK\u001aL1\u0001_A(\u0015\r\tY\u0005R\u0001\u0017gR\u0014X-Y7CY>\u001c7\u000eV1cY\u0016DU-\u00193fe\u000692\u000f\u001e:fC6\u0014En\\2l)\u0006\u0014G.\u001a%fC\u0012,'\u000fI\u0001\u0014gR\u0014X-Y7CY>\u001c7\u000eV1cY\u0016\u0014vn\u001e\u000b\u0004u\u0005e\u0003bBA.'\u0001\u0007\u0011QH\u0001\u0006E2|7m[\u0001\u0017gR\u0014X-Y7CY>\u001c7\u000eV1cY\u0016\u001cVO\u0019:poRI!(!\u0019\u0002f\u0005\u001d\u0014\u0011\u000f\u0005\b\u0003G\"\u0002\u0019AA\"\u0003\u001d\u0011Gn\\2l\u0013\u0012Dq!a\u0017\u0015\u0001\u0004\tI\u0002C\u0004\u0002jQ\u0001\r!a\u001b\u0002\u0017I,\u0007\u000f\\5dCRLwN\u001c\t\u0004}\u00065\u0014bAA8\t\n\u0019\u0011J\u001c;\t\u000f\u0005MD\u00031\u0001\u0002v\u0005Ya-\u001b:tiN+(M]8x!\rq\u0018qO\u0005\u0004\u0003s\"%a\u0002\"p_2,\u0017M\\\u0001*gR\u0014X-Y7CY>\u001c7n\u0015;pe\u0006<W\rT3wK2$Um]2sSB$\u0018n\u001c8B]\u0012\u001c\u0016N_3\u0015\t\u0005}\u0014q\u0011\t\b}\u0006}\u00121IAA!\rq\u00181Q\u0005\u0004\u0003\u000b#%\u0001\u0002'p]\u001eDq!a\u0017\u0016\u0001\u0004\tI\u0002"
)
public class StoragePage extends WebUIPage {
   private final SparkUITab parent;
   private final AppStatusStore store;
   private final Seq rddHeader;
   private final Seq tooltips;
   private final Seq executorMetricsTableHeader;
   private final Seq streamBlockTableHeader;

   public Seq render(final HttpServletRequest request) {
      Seq content = (Seq)this.rddTable(request, this.store.rddList(this.store.rddList$default$1())).$plus$plus(this.receiverBlockTables(this.store.streamBlocksList()));
      return UIUtils$.MODULE$.headerSparkPage(request, "Storage", () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
   }

   public Seq rddTable(final HttpServletRequest request, final Seq rdds) {
      if (rdds.isEmpty()) {
         return .MODULE$;
      } else {
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var20 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-rdds','aggregated-rdds')"), $md);
         var20 = new UnprefixedAttribute("class", new Text("collapse-aggregated-rdds collapse-table"), var20);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Null var10022 = scala.xml.Null..MODULE$;
         TopScope var10023 = scala.xml.TopScope..MODULE$;
         NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var22 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
         $buf.$amp$plus(new Elem((String)null, "span", var22, scala.xml.TopScope..MODULE$, false, .MODULE$));
         $buf.$amp$plus(new Text("\n            "));
         Null var10031 = scala.xml.Null..MODULE$;
         TopScope var10032 = scala.xml.TopScope..MODULE$;
         NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("RDDs ("));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(rdds.length()));
         $buf.$amp$plus(new Text(")"));
         $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "span", var20, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var23 = new UnprefixedAttribute("class", new Text("aggregated-rdds collapsible-table"), $md);
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         Seq x$1 = this.rddHeader();
         Function1 x$2 = (x$1x) -> this.rddRow(request, x$1x);
         Some x$4 = new Some("storage-by-rdd-table");
         Seq x$5 = this.tooltips();
         boolean x$6 = UIUtils$.MODULE$.listingTable$default$4();
         Seq x$7 = UIUtils$.MODULE$.listingTable$default$6();
         boolean x$8 = UIUtils$.MODULE$.listingTable$default$7();
         boolean x$9 = UIUtils$.MODULE$.listingTable$default$8();
         $buf.$amp$plus(UIUtils$.MODULE$.listingTable(x$1, x$2, rdds, x$6, x$4, x$7, x$8, x$9, x$5));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "div", var23, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         return new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      }
   }

   private Seq rddHeader() {
      return this.rddHeader;
   }

   public Seq tooltips() {
      return this.tooltips;
   }

   private Seq rddRow(final HttpServletRequest request, final RDDStorageInfo rdd) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(rdd.id()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var15 = new UnprefixedAttribute("href", scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s/storage/rdd/?id=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{UIUtils$.MODULE$.prependBaseUri(request, this.parent.basePath(), UIUtils$.MODULE$.prependBaseUri$default$3()), BoxesRunTime.boxToInteger(rdd.id())})), $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(rdd.name());
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "a", var15, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(rdd.storageLevel());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Integer.toString(rdd.numCachedPartitions()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%.2f%%"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble((double)rdd.numCachedPartitions() * (double)100.0F / (double)rdd.numPartitions())})));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var16 = new UnprefixedAttribute("sorttable_customkey", Long.toString(rdd.memoryUsed()), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(rdd.memoryUsed()));
      $buf.$amp$plus(new Elem((String)null, "td", var16, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var17 = new UnprefixedAttribute("sorttable_customkey", Long.toString(rdd.diskUsed()), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(rdd.diskUsed()));
      $buf.$amp$plus(new Elem((String)null, "td", var17, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public Seq receiverBlockTables(final Seq blocks) {
      if (blocks.isEmpty()) {
         return .MODULE$;
      } else {
         Seq sorted = (Seq)blocks.groupBy((x$2) -> x$2.name()).toSeq().sortBy((x$3) -> (String)x$3._1(), scala.math.Ordering.String..MODULE$);
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Receiver Blocks"));
         $buf.$amp$plus(new Elem((String)null, "h4", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(this.executorMetricsTable(blocks));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(this.streamBlockTable(sorted));
         $buf.$amp$plus(new Text("\n      "));
         return new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      }
   }

   private Seq executorMetricsTable(final Seq blocks) {
      Iterable blockManagers = (Iterable)((IterableOps)scala.collection.SortedMap..MODULE$.apply(blocks.groupBy((x$4x) -> x$4x.executorId()).toSeq(), scala.math.Ordering.String..MODULE$)).map((x0$1) -> {
         if (x0$1 != null) {
            Seq blocks = (Seq)x0$1._2();
            return new ExecutorStreamSummary(blocks);
         } else {
            throw new MatchError(x0$1);
         }
      });
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Aggregated Block Metrics by Executor"));
      $buf.$amp$plus(new Elem((String)null, "h5", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Seq x$1 = this.executorMetricsTableHeader();
      Function1 x$2 = (status) -> this.executorMetricsTableRow(status);
      Some x$4 = new Some("storage-by-executor-stream-blocks");
      boolean x$5 = UIUtils$.MODULE$.listingTable$default$4();
      Seq x$6 = UIUtils$.MODULE$.listingTable$default$6();
      boolean x$7 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$8 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$9 = UIUtils$.MODULE$.listingTable$default$9();
      $buf.$amp$plus(UIUtils$.MODULE$.listingTable(x$1, x$2, blockManagers, x$5, x$4, x$6, x$7, x$8, x$9));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq executorMetricsTableHeader() {
      return this.executorMetricsTableHeader;
   }

   private Seq executorMetricsTableRow(final ExecutorStreamSummary status) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(status.executorId());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(status.location());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var10 = new UnprefixedAttribute("sorttable_customkey", Long.toString(status.totalMemSize()), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(status.totalMemSize()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var11 = new UnprefixedAttribute("sorttable_customkey", Long.toString(status.totalDiskSize()), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(status.totalDiskSize()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var11, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(Integer.toString(status.numStreamBlocks()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq streamBlockTable(final Seq blocks) {
      if (blocks.isEmpty()) {
         return .MODULE$;
      } else {
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Blocks"));
         $buf.$amp$plus(new Elem((String)null, "h5", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         Seq x$1 = this.streamBlockTableHeader();
         Function1 x$2 = (block) -> this.streamBlockTableRow(block);
         Some x$4 = new Some("storage-by-block-table");
         boolean x$5 = false;
         boolean x$6 = UIUtils$.MODULE$.listingTable$default$4();
         Seq x$7 = UIUtils$.MODULE$.listingTable$default$6();
         boolean x$8 = UIUtils$.MODULE$.listingTable$default$7();
         Seq x$9 = UIUtils$.MODULE$.listingTable$default$9();
         $buf.$amp$plus(UIUtils$.MODULE$.listingTable(x$1, x$2, blocks, x$6, x$4, x$7, x$8, false, x$9));
         $buf.$amp$plus(new Text("\n      "));
         return new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      }
   }

   private Seq streamBlockTableHeader() {
      return this.streamBlockTableHeader;
   }

   private Seq streamBlockTableRow(final Tuple2 block) {
      Seq replications = (Seq)block._2();
      scala.Predef..MODULE$.assert(replications.nonEmpty());
      return replications.size() == 1 ? this.streamBlockTableSubrow((String)block._1(), (StreamBlockData)replications.head(), replications.size(), true) : (Seq)this.streamBlockTableSubrow((String)block._1(), (StreamBlockData)replications.head(), replications.size(), true).$plus$plus((IterableOnce)((IterableOps)replications.tail()).flatMap((x$5) -> this.streamBlockTableSubrow((String)block._1(), x$5, replications.size(), false)));
   }

   private Seq streamBlockTableSubrow(final String blockId, final StreamBlockData block, final int replication, final boolean firstSubrow) {
      Tuple2 var7 = this.streamBlockStorageLevelDescriptionAndSize(block);
      if (var7 != null) {
         String storageLevel = (String)var7._1();
         long size = var7._2$mcJ$sp();
         Tuple2 var6 = new Tuple2(storageLevel, BoxesRunTime.boxToLong(size));
         String storageLevel = (String)var6._1();
         long size = var6._2$mcJ$sp();
         Elem var10000 = new Elem;
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n      "));
         Object var10009;
         if (firstSubrow) {
            NodeBuffer $buf = new NodeBuffer();
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var23 = new UnprefixedAttribute("rowspan", Integer.toString(replication), $md);
            TopScope var10015 = scala.xml.TopScope..MODULE$;
            NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(block.name());
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "td", var23, var10015, false, var10017.seqToNodeSeq($buf)));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var24 = new UnprefixedAttribute("rowspan", Integer.toString(replication), $md);
            var10015 = scala.xml.TopScope..MODULE$;
            var10017 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(Integer.toString(replication));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "td", var24, var10015, false, var10017.seqToNodeSeq($buf)));
            var10009 = $buf;
         } else {
            var10009 = BoxedUnit.UNIT;
         }

         $buf.$amp$plus(var10009);
         $buf.$amp$plus(new Text("\n      "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(block.hostPort());
         $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(storageLevel);
         $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(Utils$.MODULE$.bytesToString(size));
         $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n    "));
         var10000.<init>((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         return var10000;
      } else {
         throw new MatchError(var7);
      }
   }

   public Tuple2 streamBlockStorageLevelDescriptionAndSize(final StreamBlockData block) {
      if (block.useDisk()) {
         return new Tuple2("Disk", BoxesRunTime.boxToLong(block.diskSize()));
      } else if (block.useMemory() && block.deserialized()) {
         return new Tuple2("Memory", BoxesRunTime.boxToLong(block.memSize()));
      } else if (block.useMemory() && !block.deserialized()) {
         return new Tuple2("Memory Serialized", BoxesRunTime.boxToLong(block.memSize()));
      } else {
         throw new IllegalStateException("Invalid Storage Level: " + block.storageLevel());
      }
   }

   public StoragePage(final SparkUITab parent, final AppStatusStore store) {
      super("");
      this.parent = parent;
      this.store = store;
      this.rddHeader = new scala.collection.immutable..colon.colon("ID", new scala.collection.immutable..colon.colon("RDD Name", new scala.collection.immutable..colon.colon("Storage Level", new scala.collection.immutable..colon.colon("Cached Partitions", new scala.collection.immutable..colon.colon("Fraction Cached", new scala.collection.immutable..colon.colon("Size in Memory", new scala.collection.immutable..colon.colon("Size on Disk", .MODULE$)))))));
      this.tooltips = new scala.collection.immutable..colon.colon(scala.None..MODULE$, new scala.collection.immutable..colon.colon(new Some(ToolTips$.MODULE$.RDD_NAME()), new scala.collection.immutable..colon.colon(new Some(ToolTips$.MODULE$.STORAGE_LEVEL()), new scala.collection.immutable..colon.colon(new Some(ToolTips$.MODULE$.CACHED_PARTITIONS()), new scala.collection.immutable..colon.colon(new Some(ToolTips$.MODULE$.FRACTION_CACHED()), new scala.collection.immutable..colon.colon(new Some(ToolTips$.MODULE$.SIZE_IN_MEMORY()), new scala.collection.immutable..colon.colon(new Some(ToolTips$.MODULE$.SIZE_ON_DISK()), .MODULE$)))))));
      this.executorMetricsTableHeader = new scala.collection.immutable..colon.colon("Executor ID", new scala.collection.immutable..colon.colon("Address", new scala.collection.immutable..colon.colon("Total Size in Memory", new scala.collection.immutable..colon.colon("Total Size on Disk", new scala.collection.immutable..colon.colon("Stream Blocks", .MODULE$)))));
      this.streamBlockTableHeader = new scala.collection.immutable..colon.colon("Block ID", new scala.collection.immutable..colon.colon("Replication Level", new scala.collection.immutable..colon.colon("Location", new scala.collection.immutable..colon.colon("Storage Level", new scala.collection.immutable..colon.colon("Size", .MODULE$)))));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
