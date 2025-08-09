package org.apache.spark.sql.hive.thriftserver.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.ui.PagedTable;
import scala.MatchError;
import scala.Some;
import scala.Tuple3;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.Comment;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!\u0002\u0010 \u0001}i\u0003\u0002C\u001f\u0001\u0005\u0003\u0005\u000b\u0011B \t\u0011%\u0003!\u0011!Q\u0001\n)C\u0001\"\u0014\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\t;\u0002\u0011\t\u0011)A\u0005=\"Aa\r\u0001B\u0001B\u0003%a\f\u0003\u0005h\u0001\t\u0005\t\u0015!\u0003_\u0011\u0015A\u0007\u0001\"\u0001j\u0011)\t\b\u0001%A\u0001\u0004\u0003\u0006IA\u001d\u0005\bw\u0002\u0011\r\u0011\"\u0003}\u0011\u0019i\b\u0001)A\u0005=\"9a\u0010\u0001b\u0001\n\u0013y\bbBA\u0001\u0001\u0001\u0006I!\u001e\u0005\n\u0003\u0007\u0001!\u0019!C\u0005\u0003\u000bAq!a\u0002\u0001A\u0003%\u0001\u0010C\u0005\u0002\n\u0001\u0011\r\u0011\"\u0003\u0002\f!A\u00111\u0004\u0001!\u0002\u0013\ti\u0001C\u0005\u0002\u001e\u0001\u0011\r\u0011\"\u0003\u0002\f!A\u0011q\u0004\u0001!\u0002\u0013\ti\u0001C\u0005\u0002\"\u0001\u0011\r\u0011\"\u0011\u0002$!A\u00111\u0006\u0001!\u0002\u0013\t)\u0003\u0003\u0004\u0002.\u0001!\t\u0005 \u0005\u0007\u0003_\u0001A\u0011\t?\t\u000f\u0005E\u0002\u0001\"\u0011\u00024!1\u0011\u0011\b\u0001\u0005BqDa!a\u000f\u0001\t\u0003b\bBBA\u001f\u0001\u0011\u0005C\u0010C\u0004\u0002@\u0001!\t%!\u0011\t\u000f\u0005E\u0003\u0001\"\u0011\u0002T!9\u0011\u0011\f\u0001\u0005\n\u0005m#AE*rYN#\u0018\r^:QC\u001e,G\rV1cY\u0016T!\u0001I\u0011\u0002\u0005UL'B\u0001\u0012$\u00031!\bN]5giN,'O^3s\u0015\t!S%\u0001\u0003iSZ,'B\u0001\u0014(\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003Q%\nQa\u001d9be.T!AK\u0016\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005a\u0013aA8sON\u0019\u0001A\f\u001b\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0003E\nQa]2bY\u0006L!a\r\u0019\u0003\r\u0005s\u0017PU3g!\r)t'O\u0007\u0002m)\u0011\u0001eJ\u0005\u0003qY\u0012!\u0002U1hK\u0012$\u0016M\u00197f!\tQ4(D\u0001 \u0013\tatD\u0001\tTc2\u001cF/\u0019;t)\u0006\u0014G.\u001a*po\u00069!/Z9vKN$8\u0001\u0001\t\u0003\u0001\u001ek\u0011!\u0011\u0006\u0003\u0005\u000e\u000bA\u0001\u001b;ua*\u0011A)R\u0001\bg\u0016\u0014h\u000f\\3u\u0015\u00051\u0015a\u00026bW\u0006\u0014H/Y\u0005\u0003\u0011\u0006\u0013!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\u00061\u0001/\u0019:f]R\u0004\"AO&\n\u00051{\"a\u0004+ie&4GoU3sm\u0016\u0014H+\u00192\u0002\t\u0011\fG/\u0019\t\u0004\u001f^SfB\u0001)V\u001d\t\tF+D\u0001S\u0015\t\u0019f(\u0001\u0004=e>|GOP\u0005\u0002c%\u0011a\u000bM\u0001\ba\u0006\u001c7.Y4f\u0013\tA\u0016LA\u0002TKFT!A\u0016\u0019\u0011\u0005iZ\u0016B\u0001/ \u00055)\u00050Z2vi&|g.\u00138g_\u000691/\u001e2QCRD\u0007CA0d\u001d\t\u0001\u0017\r\u0005\u0002Ra%\u0011!\rM\u0001\u0007!J,G-\u001a4\n\u0005\u0011,'AB*ue&twM\u0003\u0002ca\u0005A!-Y:f!\u0006$\b.\u0001\ttc2\u001cF/\u0019;t)\u0006\u0014G.\u001a+bO\u00061A(\u001b8jiz\"rA[6m[:|\u0007\u000f\u0005\u0002;\u0001!)Qh\u0002a\u0001\u007f!)\u0011j\u0002a\u0001\u0015\")Qj\u0002a\u0001\u001d\")Ql\u0002a\u0001=\")am\u0002a\u0001=\")qm\u0002a\u0001=\u0006\u0019\u0001\u0010J\u001a\u0011\u000b=\u001ah,\u001e=\n\u0005Q\u0004$A\u0002+va2,7\u0007\u0005\u00020m&\u0011q\u000f\r\u0002\b\u0005>|G.Z1o!\ty\u00130\u0003\u0002{a\t\u0019\u0011J\u001c;\u0002\u0015M|'\u000f^\"pYVlg.F\u0001_\u0003-\u0019xN\u001d;D_2,XN\u001c\u0011\u0002\t\u0011,7oY\u000b\u0002k\u0006)A-Z:dA\u0005A\u0001/Y4f'&TX-F\u0001y\u0003%\u0001\u0018mZ3TSj,\u0007%A\tf]\u000e|G-\u001a3T_J$8i\u001c7v[:,\"!!\u0004\u0011\t\u0005=\u0011\u0011D\u0007\u0003\u0003#QA!a\u0005\u0002\u0016\u0005!A.\u00198h\u0015\t\t9\"\u0001\u0003kCZ\f\u0017b\u00013\u0002\u0012\u0005\u0011RM\\2pI\u0016$7k\u001c:u\u0007>dW/\u001c8!\u00035\u0001\u0018M]1nKR,'\u000fU1uQ\u0006q\u0001/\u0019:b[\u0016$XM\u001d)bi\"\u0004\u0013A\u00033bi\u0006\u001cv.\u001e:dKV\u0011\u0011Q\u0005\t\u0004u\u0005\u001d\u0012bAA\u0015?\t92+\u001d7Ti\u0006$8\u000fV1cY\u0016$\u0015\r^1T_V\u00148-Z\u0001\fI\u0006$\u0018mU8ve\u000e,\u0007%A\u0004uC\ndW-\u00133\u0002\u001bQ\f'\r\\3DgN\u001cE.Y:t\u0003!\u0001\u0018mZ3MS:\\Gc\u00010\u00026!1\u0011qG\fA\u0002a\fA\u0001]1hK\u0006\t\u0002/Y4f'&TXMR8s[\u001aKW\r\u001c3\u0002'A\fw-\u001a(v[\n,'OR8s[\u001aKW\r\u001c3\u0002!\u001d|')\u001e;u_:4uN]7QCRD\u0017a\u00025fC\u0012,'o]\u000b\u0003\u0003\u0007\u0002BaT,\u0002FA!\u0011qIA'\u001b\t\tIEC\u0002\u0002LA\n1\u0001_7m\u0013\u0011\ty%!\u0013\u0003\t9{G-Z\u0001\u0004e><H\u0003BA\"\u0003+Ba!a\u0016\u001d\u0001\u0004I\u0014\u0001E:rYN#\u0018\r^:UC\ndWMU8x\u0003\u0019QwNY+S\u0019R)a,!\u0018\u0002`!)Q(\ba\u0001\u007f!1\u0011\u0011M\u000fA\u0002y\u000bQA[8c\u0013\u0012\u0004"
)
public class SqlStatsPagedTable implements PagedTable {
   private final HttpServletRequest request;
   private final ThriftServerTab parent;
   private final String sqlStatsTableTag;
   // $FF: synthetic field
   private final Tuple3 x$3;
   private final String sortColumn;
   private final boolean desc;
   private final int pageSize;
   private final String encodedSortColumn;
   private final String parameterPath;
   private final SqlStatsTableDataSource dataSource;

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

   private String encodedSortColumn() {
      return this.encodedSortColumn;
   }

   private String parameterPath() {
      return this.parameterPath;
   }

   public SqlStatsTableDataSource dataSource() {
      return this.dataSource;
   }

   public String tableId() {
      return this.sqlStatsTableTag;
   }

   public String tableCssClass() {
      return "table table-bordered table-sm table-striped table-head-clickable table-cell-width-limited";
   }

   public String pageLink(final int page) {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.pageNumberFormField() + "=" + page + "&" + this.sqlStatsTableTag + ".sort=" + this.encodedSortColumn() + "&" + this.sqlStatsTableTag + ".desc=" + this.desc() + "&" + this.pageSizeFormField() + "=" + this.pageSize() + "#" + this.sqlStatsTableTag;
   }

   public String pageSizeFormField() {
      return this.sqlStatsTableTag + ".pageSize";
   }

   public String pageNumberFormField() {
      return this.sqlStatsTableTag + ".page";
   }

   public String goButtonFormPath() {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.sqlStatsTableTag + ".sort=" + this.encodedSortColumn() + "&" + this.sqlStatsTableTag + ".desc=" + this.desc() + "#" + this.sqlStatsTableTag;
   }

   public Seq headers() {
      Seq sqlTableHeadersAndTooltips = (Seq).MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple3[]{new Tuple3("User", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("JobID", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("GroupID", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("Start Time", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("Finish Time", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.THRIFT_SERVER_FINISH_TIME())), new Tuple3("Close Time", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.THRIFT_SERVER_CLOSE_TIME())), new Tuple3("Execution Time", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.THRIFT_SERVER_EXECUTION())), new Tuple3("Duration", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.THRIFT_SERVER_DURATION())), new Tuple3("Statement", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("State", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("Detail", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$)})));
      this.isSortColumnValid(sqlTableHeadersAndTooltips, this.sortColumn());
      return this.headerRow(sqlTableHeadersAndTooltips, this.desc(), this.pageSize(), this.sortColumn(), this.parameterPath(), this.sqlStatsTableTag, this.sqlStatsTableTag);
   }

   public Seq row(final SqlStatsTableRow sqlStatsTableRow) {
      ExecutionInfo info = sqlStatsTableRow.executionInfo();
      long startTime = info.startTimestamp();
      long executionTime = sqlStatsTableRow.executionTime();
      long duration = sqlStatsTableRow.duration();
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
      $buf.$amp$plus(info.userName());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(this.jobLinks$1(sqlStatsTableRow.jobId()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(info.groupId());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDate(startTime));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(info.finishTimestamp() > 0L ? org.apache.spark.ui.UIUtils..MODULE$.formatDate(info.finishTimestamp()) : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(info.closeTimestamp() > 0L ? org.apache.spark.ui.UIUtils..MODULE$.formatDate(info.closeTimestamp()) : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Comment(" Returns a human-readable string representing a duration such as \"5 second 35 ms\""));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDurationVerbose(executionTime));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDurationVerbose(duration));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var22 = new UnprefixedAttribute("class", new Text("description-input"), $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(info.statement());
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var22, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(info.state());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.errorMessageCell(sqlStatsTableRow.detail()));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private String jobURL(final HttpServletRequest request, final String jobId) {
      return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s/jobs/job/?id=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri(request, this.parent.basePath(), org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri$default$3()), jobId}));
   }

   private final Seq jobLinks$1(final Seq jobData) {
      return (Seq)jobData.map((jobId) -> {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var4 = new UnprefixedAttribute("href", this.jobURL(this.request, jobId), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("["));
         $buf.$amp$plus(jobId);
         $buf.$amp$plus(new Text("]"));
         return new Elem((String)null, "a", var4, var10005, false, var10007.seqToNodeSeq($buf));
      });
   }

   public SqlStatsPagedTable(final HttpServletRequest request, final ThriftServerTab parent, final Seq data, final String subPath, final String basePath, final String sqlStatsTableTag) {
      this.request = request;
      this.parent = parent;
      this.sqlStatsTableTag = sqlStatsTableTag;
      PagedTable.$init$(this);
      Tuple3 var8 = this.getTableParameters(request, sqlStatsTableTag, "Start Time");
      if (var8 != null) {
         String sortColumn = (String)var8._1();
         boolean desc = BoxesRunTime.unboxToBoolean(var8._2());
         int pageSize = BoxesRunTime.unboxToInt(var8._3());
         this.x$3 = new Tuple3(sortColumn, BoxesRunTime.boxToBoolean(desc), BoxesRunTime.boxToInteger(pageSize));
         this.sortColumn = (String)this.x$3._1();
         this.desc = BoxesRunTime.unboxToBoolean(this.x$3._2());
         this.pageSize = BoxesRunTime.unboxToInt(this.x$3._3());
         this.encodedSortColumn = URLEncoder.encode(this.sortColumn(), StandardCharsets.UTF_8.name());
         this.parameterPath = basePath + "/" + subPath + "/?" + this.getParameterOtherTable(request, sqlStatsTableTag);
         this.dataSource = new SqlStatsTableDataSource(data, this.pageSize(), this.sortColumn(), this.desc());
      } else {
         throw new MatchError(var8);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
