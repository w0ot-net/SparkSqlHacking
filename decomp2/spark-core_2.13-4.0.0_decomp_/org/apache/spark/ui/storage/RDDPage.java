package org.apache.spark.ui.storage;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.NoSuchElementException;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.RDDDataDistribution;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import org.apache.spark.ui.SparkUITab;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Some;
import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005!4Qa\u0002\u0005\u0001\u0015IA\u0001b\u0006\u0001\u0003\u0002\u0003\u0006I!\u0007\u0005\t9\u0001\u0011\t\u0011)A\u0005;!)1\u0005\u0001C\u0001I!)\u0011\u0006\u0001C\u0001U!)1\n\u0001C\u0005\u0019\")A\f\u0001C\u0005;\n9!\u000b\u0012#QC\u001e,'BA\u0005\u000b\u0003\u001d\u0019Ho\u001c:bO\u0016T!a\u0003\u0007\u0002\u0005UL'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0014\u0005\u0001\u0019\u0002C\u0001\u000b\u0016\u001b\u0005Q\u0011B\u0001\f\u000b\u0005%9VMY+J!\u0006<W-\u0001\u0004qCJ,g\u000e^\u0002\u0001!\t!\"$\u0003\u0002\u001c\u0015\tQ1\u000b]1sWVKE+\u00192\u0002\u000bM$xN]3\u0011\u0005y\tS\"A\u0010\u000b\u0005\u0001b\u0011AB:uCR,8/\u0003\u0002#?\tq\u0011\t\u001d9Ti\u0006$Xo]*u_J,\u0017A\u0002\u001fj]&$h\bF\u0002&O!\u0002\"A\n\u0001\u000e\u0003!AQaF\u0002A\u0002eAQ\u0001H\u0002A\u0002u\taA]3oI\u0016\u0014HCA\u0016@!\rac'\u000f\b\u0003[Mr!AL\u0019\u000e\u0003=R!\u0001\r\r\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0011\u0014!B:dC2\f\u0017B\u0001\u001b6\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AM\u0005\u0003oa\u00121aU3r\u0015\t!T\u0007\u0005\u0002;{5\t1H\u0003\u0002=k\u0005\u0019\u00010\u001c7\n\u0005yZ$\u0001\u0002(pI\u0016DQ\u0001\u0011\u0003A\u0002\u0005\u000bqA]3rk\u0016\u001cH\u000f\u0005\u0002C\u00136\t1I\u0003\u0002E\u000b\u0006!\u0001\u000e\u001e;q\u0015\t1u)A\u0004tKJ4H.\u001a;\u000b\u0003!\u000bqA[1lCJ$\u0018-\u0003\u0002K\u0007\n\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u000319xN]6fe\"+\u0017\rZ3s+\u0005i\u0005c\u0001(T)6\tqJ\u0003\u0002Q#\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003%V\n!bY8mY\u0016\u001cG/[8o\u0013\t9t\n\u0005\u0002V56\taK\u0003\u0002X1\u0006!A.\u00198h\u0015\u0005I\u0016\u0001\u00026bm\u0006L!a\u0017,\u0003\rM#(/\u001b8h\u0003%9xN]6feJ{w\u000f\u0006\u0002,=\")qL\u0002a\u0001A\u00061qo\u001c:lKJ\u0004\"!\u00194\u000e\u0003\tT!a\u00193\u0002\u0005Y\f$BA3 \u0003\r\t\u0007/[\u0005\u0003O\n\u00141C\u0015#E\t\u0006$\u0018\rR5tiJL'-\u001e;j_:\u0004"
)
public class RDDPage extends WebUIPage {
   private final SparkUITab parent;
   private final AppStatusStore store;

   public Seq render(final HttpServletRequest request) {
      String parameterId = request.getParameter("id");
      .MODULE$.require(parameterId != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(parameterId)), () -> "Missing id parameter");
      int blockPage = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter("block.page")).map((x$1x) -> BoxesRunTime.boxToInteger($anonfun$render$2(x$1x))).getOrElse((JFunction0.mcI.sp)() -> 1));
      int rddId = scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(parameterId));

      RDDStorageInfo var10000;
      try {
         var10000 = this.store.rdd(rddId);
      } catch (NoSuchElementException var52) {
         return UIUtils$.MODULE$.headerSparkPage(request, "RDD Not Found", () -> (Seq)scala.package..MODULE$.Seq().empty(), this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
      }

      RDDStorageInfo rddStorageInfo = var10000;
      Seq x$1 = this.workerHeader();
      Function1 x$2 = (worker) -> this.workerRow(worker);
      scala.collection.Seq x$3 = (scala.collection.Seq)rddStorageInfo.dataDistribution().get();
      Some x$4 = new Some("rdd-storage-by-worker-table");
      boolean x$5 = UIUtils$.MODULE$.listingTable$default$4();
      Seq x$6 = UIUtils$.MODULE$.listingTable$default$6();
      boolean x$7 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$8 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$9 = UIUtils$.MODULE$.listingTable$default$9();
      Seq workerTable = UIUtils$.MODULE$.listingTable(x$1, x$2, x$3, x$5, x$4, x$6, x$7, x$8, x$9);

      try {
         UIUtils$ var10004 = UIUtils$.MODULE$;
         BlockPagedTable _blockTable = new BlockPagedTable(request, "block", var10004.prependBaseUri(request, this.parent.basePath(), UIUtils$.MODULE$.prependBaseUri$default$3()) + "/storage/rdd/?id=" + rddId, (scala.collection.Seq)rddStorageInfo.partitions().get(), this.store.executorList(true));
         var10000 = _blockTable.table(blockPage);
      } catch (Throwable var53) {
         if (!(var53 instanceof IllegalArgumentException ? true : var53 instanceof IndexOutOfBoundsException)) {
            throw var53;
         }

         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var54 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(var53.getMessage());
         var10000 = new Elem((String)null, "div", var54, var10005, false, var10007.seqToNodeSeq($buf));
      }

      Seq blockTableHTML = var10000;
      Null var62 = scala.xml.Null..MODULE$;
      TopScope var63 = scala.xml.TopScope..MODULE$;
      NodeSeq var67 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(scala.collection.StringOps..MODULE$.stripMargin$extension(.MODULE$.augmentString("\n              |$(function() {\n              |  if (/.*&block.sort=.*$/.test(location.search)) {\n              |    var topOffset = $(\"#blocks-section\").offset().top;\n              |    $(\"html,body\").animate({scrollTop: topOffset}, 200);\n              |  }\n              |});\n            "))));
      $buf.$amp$plus(new Text("\n      "));
      Elem jsForScrollingDownToBlockTable = new Elem((String)null, "script", var62, var63, false, var67.seqToNodeSeq($buf));
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var55 = new UnprefixedAttribute("class", new Text("row"), $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var56 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      TopScope var10015 = scala.xml.TopScope..MODULE$;
      NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var57 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
      TopScope var10024 = scala.xml.TopScope..MODULE$;
      NodeSeq var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      Null var10032 = scala.xml.Null..MODULE$;
      TopScope var10033 = scala.xml.TopScope..MODULE$;
      NodeSeq var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      Null var10041 = scala.xml.Null..MODULE$;
      TopScope var10042 = scala.xml.TopScope..MODULE$;
      NodeSeq var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Storage Level:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(rddStorageInfo.storageLevel());
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Cached Partitions:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(rddStorageInfo.numCachedPartitions()));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Total Partitions:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(rddStorageInfo.numPartitions()));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Memory Size:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(rddStorageInfo.memoryUsed()));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n            "));
      var10032 = scala.xml.Null..MODULE$;
      var10033 = scala.xml.TopScope..MODULE$;
      var10035 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n              "));
      var10041 = scala.xml.Null..MODULE$;
      var10042 = scala.xml.TopScope..MODULE$;
      var10044 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Disk Size:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10041, var10042, false, var10044.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n              "));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(rddStorageInfo.diskUsed()));
      $buf.$amp$plus(new Text("\n            "));
      $buf.$amp$plus(new Elem((String)null, "li", var10032, var10033, false, var10035.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "ul", var57, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var56, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "div", var55, var10006, false, var10008.seqToNodeSeq($buf)));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var58 = new UnprefixedAttribute("class", new Text("row"), $md);
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var59 = new UnprefixedAttribute("class", new Text("col-12"), $md);
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Null var10023 = scala.xml.Null..MODULE$;
      var10024 = scala.xml.TopScope..MODULE$;
      var10026 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            Data Distribution on "));
      $buf.$amp$plus(rddStorageInfo.dataDistribution().map((x$2x) -> BoxesRunTime.boxToInteger($anonfun$render$6(x$2x))).getOrElse((JFunction0.mcI.sp)() -> 0));
      $buf.$amp$plus(new Text("\n            Executors\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10023, var10024, false, var10026.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(workerTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var59, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "div", var58, var10006, false, var10008.seqToNodeSeq($buf)));
      Null var64 = scala.xml.Null..MODULE$;
      var10006 = scala.xml.TopScope..MODULE$;
      var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var60 = new UnprefixedAttribute("id", new Text("blocks-section"), $md);
      var10015 = scala.xml.TopScope..MODULE$;
      var10017 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(rddStorageInfo.partitions().map((x$3x) -> BoxesRunTime.boxToInteger($anonfun$render$8(x$3x))).getOrElse((JFunction0.mcI.sp)() -> 0));
      $buf.$amp$plus(new Text(" Partitions\n        "));
      $buf.$amp$plus(new Elem((String)null, "h4", var60, var10015, false, var10017.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(blockTableHTML.$plus$plus(jsForScrollingDownToBlockTable));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "div", var64, var10006, false, var10008.seqToNodeSeq($buf)));
      return UIUtils$.MODULE$.headerSparkPage(request, "RDD Storage Info for " + rddStorageInfo.name(), () -> scala.xml.NodeSeq..MODULE$.seqToNodeSeq($buf), this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
   }

   private Seq workerHeader() {
      return new scala.collection.immutable..colon.colon("Host", new scala.collection.immutable..colon.colon("On Heap Memory Usage", new scala.collection.immutable..colon.colon("Off Heap Memory Usage", new scala.collection.immutable..colon.colon("Disk Usage", scala.collection.immutable.Nil..MODULE$))));
   }

   private Seq workerRow(final RDDDataDistribution worker) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(worker.address());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(BoxesRunTime.unboxToLong(worker.onHeapMemoryUsed().getOrElse((JFunction0.mcJ.sp)() -> 0L))));
      $buf.$amp$plus(new Text("\n        ("));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(BoxesRunTime.unboxToLong(worker.onHeapMemoryRemaining().getOrElse((JFunction0.mcJ.sp)() -> 0L))));
      $buf.$amp$plus(new Text(" Remaining)\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(BoxesRunTime.unboxToLong(worker.offHeapMemoryUsed().getOrElse((JFunction0.mcJ.sp)() -> 0L))));
      $buf.$amp$plus(new Text("\n        ("));
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(BoxesRunTime.unboxToLong(worker.offHeapMemoryRemaining().getOrElse((JFunction0.mcJ.sp)() -> 0L))));
      $buf.$amp$plus(new Text(" Remaining)\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(worker.diskUsed()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   // $FF: synthetic method
   public static final int $anonfun$render$2(final String x$1) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   public static final int $anonfun$render$6(final scala.collection.Seq x$2) {
      return x$2.size();
   }

   // $FF: synthetic method
   public static final int $anonfun$render$8(final scala.collection.Seq x$3) {
      return x$3.size();
   }

   public RDDPage(final SparkUITab parent, final AppStatusStore store) {
      super("rdd");
      this.parent = parent;
      this.store = store;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
