package org.apache.spark.streaming.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.ui.PagedDataSource;
import org.apache.spark.ui.PagedTable;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.mutable.Iterable;
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
   bytes = "\u0006\u0005\u00055d!B\u0011#\u0001\tb\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u0011!\u0003!\u0011!Q\u0001\n%C\u0001\u0002\u0016\u0001\u0003\u0002\u0003\u0006I!\u0016\u0005\t=\u0002\u0011\t\u0011)A\u0005\u0013\"Aq\f\u0001B\u0001B\u0003%\u0011\n\u0003\u0005a\u0001\t\u0005\t\u0015!\u0003b\u0011\u0015!\u0007\u0001\"\u0001f\u0011)i\u0007\u0001%A\u0001\u0004\u0003\u0006IA\u001c\u0005\bo\u0002\u0011\r\u0011\"\u0003y\u0011\u0019I\b\u0001)A\u0005\u0013\"9!\u0010\u0001b\u0001\n\u0013Y\bB\u0002?\u0001A\u0003%\u0011\u000fC\u0004~\u0001\t\u0007I\u0011\u0002@\t\r}\u0004\u0001\u0015!\u0003u\u0011%\t\t\u0001\u0001b\u0001\n\u0013\t\u0019\u0001\u0003\u0005\u0002\u0014\u0001\u0001\u000b\u0011BA\u0003\u0011%\t)\u0002\u0001b\u0001\n\u0013\t\u0019\u0001\u0003\u0005\u0002\u0018\u0001\u0001\u000b\u0011BA\u0003\u0011%\tI\u0002\u0001b\u0001\n\u0013\tY\u0002\u0003\u0005\u0002$\u0001\u0001\u000b\u0011BA\u000f\u0011\u001d\t)\u0003\u0001C\u0005\u0003OAq!a\u000b\u0001\t\u0013\ti\u0003C\u0004\u0002B\u0001!I!a\u0011\t\r\u0005\u001d\u0003\u0001\"\u0011y\u0011\u0019\tI\u0005\u0001C!q\"1\u00111\n\u0001\u0005BaDa!!\u0014\u0001\t\u0003B\bbBA(\u0001\u0011\u0005\u0013\u0011\u000b\u0005\u0007\u0003/\u0002A\u0011\t=\t\u000f\u0005e\u0003\u0001\"\u0011\u0002\\!9\u00111\r\u0001\u0005B\u0005\u0015\u0004bBA4\u0001\u0011\u0005\u0013\u0011\u000e\u0002\u0014'R\u0014X-Y7j]\u001e\u0004\u0016mZ3e)\u0006\u0014G.\u001a\u0006\u0003G\u0011\n!!^5\u000b\u0005\u00152\u0013!C:ue\u0016\fW.\u001b8h\u0015\t9\u0003&A\u0003ta\u0006\u00148N\u0003\u0002*U\u00051\u0011\r]1dQ\u0016T\u0011aK\u0001\u0004_J<7c\u0001\u0001.gA\u0011a&M\u0007\u0002_)\t\u0001'A\u0003tG\u0006d\u0017-\u0003\u00023_\t1\u0011I\\=SK\u001a\u00042\u0001\u000e\u001c9\u001b\u0005)$BA\u0012'\u0013\t9TG\u0001\u0006QC\u001e,G\rV1cY\u0016\u0004\"!\u000f\u001e\u000e\u0003\tJ!a\u000f\u0012\u0003\u0017\t\u000bGo\u00195V\u0013\u0012\u000bG/Y\u0001\be\u0016\fX/Z:u\u0007\u0001\u0001\"a\u0010$\u000e\u0003\u0001S!!\u0011\"\u0002\t!$H\u000f\u001d\u0006\u0003\u0007\u0012\u000bqa]3sm2,GOC\u0001F\u0003\u001dQ\u0017m[1si\u0006L!a\u0012!\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f^\u0001\ti\u0006\u0014G.\u001a+bOB\u0011!*\u0015\b\u0003\u0017>\u0003\"\u0001T\u0018\u000e\u00035S!AT\u001f\u0002\rq\u0012xn\u001c;?\u0013\t\u0001v&\u0001\u0004Qe\u0016$WMZ\u0005\u0003%N\u0013aa\u0015;sS:<'B\u0001)0\u0003\u001d\u0011\u0017\r^2iKN\u00042AV.9\u001d\t9\u0016L\u0004\u0002M1&\t\u0001'\u0003\u0002[_\u00059\u0001/Y2lC\u001e,\u0017B\u0001/^\u0005\r\u0019V-\u001d\u0006\u00035>\n\u0001BY1tKB\u000bG\u000f[\u0001\bgV\u0014\u0007+\u0019;i\u00035\u0011\u0017\r^2i\u0013:$XM\u001d<bYB\u0011aFY\u0005\u0003G>\u0012A\u0001T8oO\u00061A(\u001b8jiz\"rAZ4iS*\\G\u000e\u0005\u0002:\u0001!)Ah\u0002a\u0001}!)\u0001j\u0002a\u0001\u0013\")Ak\u0002a\u0001+\")al\u0002a\u0001\u0013\")ql\u0002a\u0001\u0013\")\u0001m\u0002a\u0001C\u0006\u0019\u0001\u0010J\u0019\u0011\u000b9z\u0017*\u001d;\n\u0005A|#A\u0002+va2,7\u0007\u0005\u0002/e&\u00111o\f\u0002\b\u0005>|G.Z1o!\tqS/\u0003\u0002w_\t\u0019\u0011J\u001c;\u0002\u0015M|'\u000f^\"pYVlg.F\u0001J\u0003-\u0019xN\u001d;D_2,XN\u001c\u0011\u0002\t\u0011,7oY\u000b\u0002c\u0006)A-Z:dA\u0005A\u0001/Y4f'&TX-F\u0001u\u0003%\u0001\u0018mZ3TSj,\u0007%A\u0007qCJ\fW.\u001a;feB\u000bG\u000f[\u000b\u0003\u0003\u000b\u0001B!a\u0002\u0002\u00125\u0011\u0011\u0011\u0002\u0006\u0005\u0003\u0017\ti!\u0001\u0003mC:<'BAA\b\u0003\u0011Q\u0017M^1\n\u0007I\u000bI!\u0001\bqCJ\fW.\u001a;feB\u000bG\u000f\u001b\u0011\u0002#\u0015t7m\u001c3fIN{'\u000f^\"pYVlg.\u0001\nf]\u000e|G-\u001a3T_J$8i\u001c7v[:\u0004\u0013A\u00054jeN$h)Y5mkJ,'+Z1t_:,\"!!\b\u0011\t9\ny\"S\u0005\u0004\u0003Cy#AB(qi&|g.A\ngSJ\u001cHOR1jYV\u0014XMU3bg>t\u0007%A\u000bhKR4\u0015N]:u\r\u0006LG.\u001e:f%\u0016\f7o\u001c8\u0015\t\u0005u\u0011\u0011\u0006\u0005\u0006)V\u0001\r!V\u0001\u0019O\u0016$h)\u001b:ti\u001a\u000b\u0017\u000e\\;sKR\u000b'\r\\3DK2dG\u0003BA\u0018\u0003{\u0001BAV.\u00022A!\u00111GA\u001d\u001b\t\t)DC\u0002\u00028=\n1\u0001_7m\u0013\u0011\tY$!\u000e\u0003\t9{G-\u001a\u0005\u0007\u0003\u007f1\u0002\u0019\u0001\u001d\u0002\u000b\t\fGo\u00195\u0002A\r\u0014X-\u0019;f\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]B\u0013xn\u001a:fgN\u0014\u0015M\u001d\u000b\u0005\u0003_\t)\u0005\u0003\u0004\u0002@]\u0001\r\u0001O\u0001\bi\u0006\u0014G.Z%e\u00035!\u0018M\u00197f\u0007N\u001c8\t\\1tg\u0006\t\u0002/Y4f'&TXMR8s[\u001aKW\r\u001c3\u0002'A\fw-\u001a(v[\n,'OR8s[\u001aKW\r\u001c3\u0002\u0011A\fw-\u001a'j].$2!SA*\u0011\u0019\t)\u0006\ba\u0001i\u0006!\u0001/Y4f\u0003A9wNQ;ui>tgi\u001c:n!\u0006$\b.\u0001\u0006eCR\f7k\\;sG\u0016,\"!!\u0018\u0011\tQ\ny\u0006O\u0005\u0004\u0003C*$a\u0004)bO\u0016$G)\u0019;b'>,(oY3\u0002\u000f!,\u0017\rZ3sgV\u0011\u0011qF\u0001\u0004e><H\u0003BA\u0018\u0003WBa!a\u0010!\u0001\u0004A\u0004"
)
public class StreamingPagedTable implements PagedTable {
   private final String tableTag;
   private final Seq batches;
   private final long batchInterval;
   // $FF: synthetic field
   private final Tuple3 x$1;
   private final String sortColumn;
   private final boolean desc;
   private final int pageSize;
   private final String parameterPath;
   private final String encodedSortColumn;
   private final Option firstFailureReason;

   public Seq table(final int page) {
      return PagedTable.table$(this, page);
   }

   public Seq pageNavigation(final int page, final int pageSize, final int totalPages, final String navigationId) {
      return PagedTable.pageNavigation$(this, page, pageSize, totalPages, navigationId);
   }

   public String pageNavigation$default$4() {
      return PagedTable.pageNavigation$default$4$(this);
   }

   public String getParameterOtherTable(final HttpServletRequest request, final String tableTag) {
      return PagedTable.getParameterOtherTable$(this, request, tableTag);
   }

   public Tuple3 getTableParameters(final HttpServletRequest request, final String tableTag, final String defaultSortColumn) {
      return PagedTable.getTableParameters$(this, request, tableTag, defaultSortColumn);
   }

   public void isSortColumnValid(final Seq headerInfo, final String sortColumn) {
      PagedTable.isSortColumnValid$(this, headerInfo, sortColumn);
   }

   public Seq headerRow(final Seq headerInfo, final boolean desc, final int pageSize, final String sortColumn, final String parameterPath, final String tableTag, final String headerId) {
      return PagedTable.headerRow$(this, headerInfo, desc, pageSize, sortColumn, parameterPath, tableTag, headerId);
   }

   private String sortColumn() {
      return this.sortColumn;
   }

   private boolean desc() {
      return this.desc;
   }

   private int pageSize() {
      return this.pageSize;
   }

   private String parameterPath() {
      return this.parameterPath;
   }

   private String encodedSortColumn() {
      return this.encodedSortColumn;
   }

   private Option firstFailureReason() {
      return this.firstFailureReason;
   }

   private Option getFirstFailureReason(final Seq batches) {
      return ((IterableOps)batches.flatMap((x$2) -> (Iterable)x$2.outputOperations().flatMap((x$3) -> ((OutputOperationUIData)x$3._2()).failureReason()))).headOption();
   }

   private Seq getFirstFailureTableCell(final BatchUIData batch) {
      Option firstFailureReason = ((IterableOps)batch.outputOperations().flatMap((x$4) -> ((OutputOperationUIData)x$4._2()).failureReason())).headOption();
      return (Seq)firstFailureReason.map((failureReason) -> {
         String failureReasonForUI = UIUtils$.MODULE$.createOutputOperationFailureForUI(failureReason);
         return UIUtils$.MODULE$.failureReasonCell(failureReasonForUI, 1, false);
      }).getOrElse(() -> {
         Null var10004 = .MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("-"));
         return new Elem((String)null, "td", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      });
   }

   private Seq createOutputOperationProgressBar(final BatchUIData batch) {
      MetaData $md = .MODULE$;
      MetaData var4 = new UnprefixedAttribute("class", new Text("progress-cell"), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.makeProgressBar(batch.numActiveOutputOp(), batch.numCompletedOutputOp(), batch.numFailedOutputOp(), 0, scala.Predef..MODULE$.Map().empty(), batch.outputOperations().size()));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "td", var4, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public String tableId() {
      return this.tableTag + "-table";
   }

   public String tableCssClass() {
      return "table table-bordered table-sm table-striped table-head-clickable table-cell-width-limited";
   }

   public String pageSizeFormField() {
      return this.tableTag + ".pageSize";
   }

   public String pageNumberFormField() {
      return this.tableTag + ".page";
   }

   public String pageLink(final int page) {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.tableTag + ".sort=" + this.encodedSortColumn() + "&" + this.tableTag + ".desc=" + this.desc() + "&" + this.pageNumberFormField() + "=" + page + "&" + this.pageSizeFormField() + "=" + this.pageSize() + "#" + this.tableTag;
   }

   public String goButtonFormPath() {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.tableTag + ".sort=" + this.encodedSortColumn() + "&" + this.tableTag + ".desc=" + this.desc() + "#" + this.tableTag;
   }

   public PagedDataSource dataSource() {
      return new StreamingDataSource(this.batches, this.pageSize(), this.sortColumn(), this.desc());
   }

   public Seq headers() {
      Seq headersAndCssClasses = (Seq)((IterableOps)(new scala.collection.immutable..colon.colon(new Tuple3("Batch Time", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new scala.collection.immutable..colon.colon(new Tuple3("Records", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new scala.collection.immutable..colon.colon(new Tuple3("Scheduling Delay", BoxesRunTime.boxToBoolean(true), new Some("Time taken by Streaming scheduler to submit jobs of a batch")), new scala.collection.immutable..colon.colon(new Tuple3("Processing Time", BoxesRunTime.boxToBoolean(true), new Some("Time taken to process all jobs of a batch")), scala.collection.immutable.Nil..MODULE$))))).$plus$plus(this.tableTag.equals("completedBatches") ? new scala.collection.immutable..colon.colon(new Tuple3("Total Delay", BoxesRunTime.boxToBoolean(true), new Some("Total time taken to handle a batch")), new scala.collection.immutable..colon.colon(new Tuple3("Output Ops: Succeeded/Total", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), scala.collection.immutable.Nil..MODULE$)) : new scala.collection.immutable..colon.colon(new Tuple3("Output Ops: Succeeded/Total", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), new scala.collection.immutable..colon.colon(new Tuple3("Status", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), scala.collection.immutable.Nil..MODULE$)))).$plus$plus((IterableOnce)(this.firstFailureReason().nonEmpty() ? new scala.collection.immutable..colon.colon(new Tuple3("Error", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), scala.collection.immutable.Nil..MODULE$) : scala.collection.immutable.Nil..MODULE$));
      this.isSortColumnValid(headersAndCssClasses, this.sortColumn());
      return this.headerRow(headersAndCssClasses, this.desc(), this.pageSize(), this.sortColumn(), this.parameterPath(), this.tableTag, this.tableTag);
   }

   public Seq row(final BatchUIData batch) {
      long batchTime = batch.batchTime().milliseconds();
      String formattedBatchTime = org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime(batchTime, this.batchInterval, org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime$default$3(), org.apache.spark.ui.UIUtils..MODULE$.formatBatchTime$default$4());
      long numRecords = batch.numRecords();
      Option schedulingDelay = batch.schedulingDelay();
      String formattedSchedulingDelay = (String)schedulingDelay.map((milliseconds) -> $anonfun$row$1(BoxesRunTime.unboxToLong(milliseconds))).getOrElse(() -> "-");
      Option processingTime = batch.processingDelay();
      String formattedProcessingTime = (String)processingTime.map((milliseconds) -> $anonfun$row$3(BoxesRunTime.unboxToLong(milliseconds))).getOrElse(() -> "-");
      String batchTimeId = "batch-" + batchTime;
      Option totalDelay = batch.totalDelay();
      String formattedTotalDelay = (String)totalDelay.map((milliseconds) -> $anonfun$row$5(BoxesRunTime.unboxToLong(milliseconds))).getOrElse(() -> "-");
      Elem var10000 = new Elem;
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = .MODULE$;
      MetaData var26 = new UnprefixedAttribute("isFailed", Boolean.toString(batch.isFailed()), $md);
      var26 = new UnprefixedAttribute("id", batchTimeId, var26);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = .MODULE$;
      MetaData var28 = new UnprefixedAttribute("href", "batch?id=" + batchTime, $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(formattedBatchTime);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "a", var28, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var26, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(Long.toString(numRecords));
      $buf.$amp$plus(new Text(" records "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(formattedSchedulingDelay);
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(formattedProcessingTime);
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Object var10009;
      if (this.tableTag.equals("completedBatches")) {
         var10013 = .MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text(" "));
         $buf.$amp$plus(formattedTotalDelay);
         $buf.$amp$plus(new Text(" "));
         var10009 = (new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf))).$plus$plus(this.createOutputOperationProgressBar(batch)).$plus$plus((Seq)(this.firstFailureReason().nonEmpty() ? this.getFirstFailureTableCell(batch) : scala.collection.immutable.Nil..MODULE$));
      } else if (this.tableTag.equals("runningBatches")) {
         Seq var29 = this.createOutputOperationProgressBar(batch);
         Null var39 = .MODULE$;
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text(" processing "));
         var10009 = ((IterableOps)var29.$plus$plus(new Elem((String)null, "td", var39, var10015, false, var10017.seqToNodeSeq($buf)))).$plus$plus((IterableOnce)(this.firstFailureReason().nonEmpty() ? this.getFirstFailureTableCell(batch) : scala.collection.immutable.Nil..MODULE$));
      } else {
         Seq var30 = this.createOutputOperationProgressBar(batch);
         Null var40 = .MODULE$;
         TopScope var42 = scala.xml.TopScope..MODULE$;
         NodeSeq var48 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text(" queued "));
         IterableOps var31 = (IterableOps)var30.$plus$plus(new Elem((String)null, "td", var40, var42, false, var48.seqToNodeSeq($buf)));
         Object var10010;
         if (this.firstFailureReason().nonEmpty()) {
            var40 = .MODULE$;
            var42 = scala.xml.TopScope..MODULE$;
            var48 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("-"));
            var10010 = new Elem((String)null, "td", var40, var42, false, var48.seqToNodeSeq($buf));
         } else {
            var10010 = scala.collection.immutable.Nil..MODULE$;
         }

         var10009 = var31.$plus$plus((IterableOnce)var10010);
      }

      $buf.$amp$plus(var10009);
      $buf.$amp$plus(new Text("\n    "));
      var10000.<init>((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      return var10000;
   }

   // $FF: synthetic method
   public static final String $anonfun$row$1(final long milliseconds) {
      return org.apache.spark.ui.UIUtils..MODULE$.formatDuration(milliseconds);
   }

   // $FF: synthetic method
   public static final String $anonfun$row$3(final long milliseconds) {
      return org.apache.spark.ui.UIUtils..MODULE$.formatDuration(milliseconds);
   }

   // $FF: synthetic method
   public static final String $anonfun$row$5(final long milliseconds) {
      return org.apache.spark.ui.UIUtils..MODULE$.formatDuration(milliseconds);
   }

   public StreamingPagedTable(final HttpServletRequest request, final String tableTag, final Seq batches, final String basePath, final String subPath, final long batchInterval) {
      this.tableTag = tableTag;
      this.batches = batches;
      this.batchInterval = batchInterval;
      PagedTable.$init$(this);
      Tuple3 var9 = this.getTableParameters(request, tableTag, "Batch Time");
      if (var9 != null) {
         String sortColumn = (String)var9._1();
         boolean desc = BoxesRunTime.unboxToBoolean(var9._2());
         int pageSize = BoxesRunTime.unboxToInt(var9._3());
         this.x$1 = new Tuple3(sortColumn, BoxesRunTime.boxToBoolean(desc), BoxesRunTime.boxToInteger(pageSize));
         this.sortColumn = (String)this.x$1._1();
         this.desc = BoxesRunTime.unboxToBoolean(this.x$1._2());
         this.pageSize = BoxesRunTime.unboxToInt(this.x$1._3());
         this.parameterPath = basePath + "/" + subPath + "/?" + this.getParameterOtherTable(request, tableTag);
         this.encodedSortColumn = URLEncoder.encode(this.sortColumn(), StandardCharsets.UTF_8.name());
         this.firstFailureReason = (Option)(!tableTag.equals("waitingBatches") ? this.getFirstFailureReason(batches) : scala.None..MODULE$);
      } else {
         throw new MatchError(var9);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
