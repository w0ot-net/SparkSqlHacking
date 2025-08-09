package org.apache.spark.sql.hive.thriftserver.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.ui.PagedTable;
import scala.MatchError;
import scala.Some;
import scala.Tuple3;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
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
   bytes = "\u0006\u0005\u0005Ec!B\u000f\u001f\u0001ya\u0003\u0002\u0003\u001f\u0001\u0005\u0003\u0005\u000b\u0011\u0002 \t\u0011!\u0003!\u0011!Q\u0001\n%C\u0001\u0002\u0014\u0001\u0003\u0002\u0003\u0006I!\u0014\u0005\t3\u0002\u0011\t\u0011)A\u00055\"A!\r\u0001B\u0001B\u0003%!\f\u0003\u0005d\u0001\t\u0005\t\u0015!\u0003[\u0011\u0015!\u0007\u0001\"\u0001f\u0011)i\u0007\u0001%A\u0001\u0004\u0003\u0006IA\u001c\u0005\bo\u0002\u0011\r\u0011\"\u0003y\u0011\u0019I\b\u0001)A\u00055\"9!\u0010\u0001b\u0001\n\u0013Y\bB\u0002?\u0001A\u0003%\u0011\u000fC\u0004~\u0001\t\u0007I\u0011\u0002@\t\r}\u0004\u0001\u0015!\u0003u\u0011%\t\t\u0001\u0001b\u0001\n\u0013\t\u0019\u0001\u0003\u0005\u0002\u0014\u0001\u0001\u000b\u0011BA\u0003\u0011%\t)\u0002\u0001b\u0001\n\u0013\t\u0019\u0001\u0003\u0005\u0002\u0018\u0001\u0001\u000b\u0011BA\u0003\u0011%\tI\u0002\u0001b\u0001\n\u0003\nY\u0002\u0003\u0005\u0002$\u0001\u0001\u000b\u0011BA\u000f\u0011\u0019\t)\u0003\u0001C!q\"1\u0011q\u0005\u0001\u0005BaDq!!\u000b\u0001\t\u0003\nY\u0003\u0003\u0004\u00022\u0001!\t\u0005\u001f\u0005\u0007\u0003g\u0001A\u0011\t=\t\r\u0005U\u0002\u0001\"\u0011y\u0011\u001d\t9\u0004\u0001C!\u0003sAq!!\u0013\u0001\t\u0003\nYE\u0001\fTKN\u001c\u0018n\u001c8Ti\u0006$8\u000fU1hK\u0012$\u0016M\u00197f\u0015\ty\u0002%\u0001\u0002vS*\u0011\u0011EI\u0001\ri\"\u0014\u0018N\u001a;tKJ4XM\u001d\u0006\u0003G\u0011\nA\u0001[5wK*\u0011QEJ\u0001\u0004gFd'BA\u0014)\u0003\u0015\u0019\b/\u0019:l\u0015\tI#&\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002W\u0005\u0019qN]4\u0014\u0007\u0001i3\u0007\u0005\u0002/c5\tqFC\u00011\u0003\u0015\u00198-\u00197b\u0013\t\u0011tF\u0001\u0004B]f\u0014VM\u001a\t\u0004iYBT\"A\u001b\u000b\u0005}1\u0013BA\u001c6\u0005)\u0001\u0016mZ3e)\u0006\u0014G.\u001a\t\u0003sij\u0011AH\u0005\u0003wy\u00111bU3tg&|g.\u00138g_\u00069!/Z9vKN$8\u0001\u0001\t\u0003\u007f\u0019k\u0011\u0001\u0011\u0006\u0003\u0003\n\u000bA\u0001\u001b;ua*\u00111\tR\u0001\bg\u0016\u0014h\u000f\\3u\u0015\u0005)\u0015a\u00026bW\u0006\u0014H/Y\u0005\u0003\u000f\u0002\u0013!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\u00061\u0001/\u0019:f]R\u0004\"!\u000f&\n\u0005-s\"a\u0004+ie&4GoU3sm\u0016\u0014H+\u00192\u0002\t\u0011\fG/\u0019\t\u0004\u001dZCdBA(U\u001d\t\u00016+D\u0001R\u0015\t\u0011V(\u0001\u0004=e>|GOP\u0005\u0002a%\u0011QkL\u0001\ba\u0006\u001c7.Y4f\u0013\t9\u0006LA\u0002TKFT!!V\u0018\u0002\u000fM,(\rU1uQB\u00111l\u0018\b\u00039v\u0003\"\u0001U\u0018\n\u0005y{\u0013A\u0002)sK\u0012,g-\u0003\u0002aC\n11\u000b\u001e:j]\u001eT!AX\u0018\u0002\u0011\t\f7/\u001a)bi\"\fAc]3tg&|gn\u0015;biN$\u0016M\u00197f)\u0006<\u0017A\u0002\u001fj]&$h\bF\u0004gO\"L'n\u001b7\u0011\u0005e\u0002\u0001\"\u0002\u001f\b\u0001\u0004q\u0004\"\u0002%\b\u0001\u0004I\u0005\"\u0002'\b\u0001\u0004i\u0005\"B-\b\u0001\u0004Q\u0006\"\u00022\b\u0001\u0004Q\u0006\"B2\b\u0001\u0004Q\u0016a\u0001=%iA)af\u001c.ri&\u0011\u0001o\f\u0002\u0007)V\u0004H.Z\u001a\u0011\u00059\u0012\u0018BA:0\u0005\u001d\u0011un\u001c7fC:\u0004\"AL;\n\u0005Y|#aA%oi\u0006Q1o\u001c:u\u0007>dW/\u001c8\u0016\u0003i\u000b1b]8si\u000e{G.^7oA\u0005!A-Z:d+\u0005\t\u0018!\u00023fg\u000e\u0004\u0013\u0001\u00039bO\u0016\u001c\u0016N_3\u0016\u0003Q\f\u0011\u0002]1hKNK'0\u001a\u0011\u0002#\u0015t7m\u001c3fIN{'\u000f^\"pYVlg.\u0006\u0002\u0002\u0006A!\u0011qAA\t\u001b\t\tIA\u0003\u0003\u0002\f\u00055\u0011\u0001\u00027b]\u001eT!!a\u0004\u0002\t)\fg/Y\u0005\u0004A\u0006%\u0011AE3oG>$W\rZ*peR\u001cu\u000e\\;n]\u0002\nQ\u0002]1sC6,G/\u001a:QCRD\u0017A\u00049be\u0006lW\r^3s!\u0006$\b\u000eI\u0001\u000bI\u0006$\u0018mU8ve\u000e,WCAA\u000f!\rI\u0014qD\u0005\u0004\u0003Cq\"aG*fgNLwN\\*uCR\u001cH+\u00192mK\u0012\u000bG/Y*pkJ\u001cW-A\u0006eCR\f7k\\;sG\u0016\u0004\u0013a\u0002;bE2,\u0017\nZ\u0001\u000ei\u0006\u0014G.Z\"tg\u000ec\u0017m]:\u0002\u0011A\fw-\u001a'j].$2AWA\u0017\u0011\u0019\tyc\u0006a\u0001i\u0006!\u0001/Y4f\u0003E\u0001\u0018mZ3TSj,gi\u001c:n\r&,G\u000eZ\u0001\u0014a\u0006<WMT;nE\u0016\u0014hi\u001c:n\r&,G\u000eZ\u0001\u0011O>\u0014U\u000f\u001e;p]\u001a{'/\u001c)bi\"\fq\u0001[3bI\u0016\u00148/\u0006\u0002\u0002<A!aJVA\u001f!\u0011\ty$!\u0012\u000e\u0005\u0005\u0005#bAA\"_\u0005\u0019\u00010\u001c7\n\t\u0005\u001d\u0013\u0011\t\u0002\u0005\u001d>$W-A\u0002s_^$B!a\u000f\u0002N!1\u0011q\n\u000fA\u0002a\nqa]3tg&|g\u000e"
)
public class SessionStatsPagedTable implements PagedTable {
   private final HttpServletRequest request;
   private final ThriftServerTab parent;
   private final String sessionStatsTableTag;
   // $FF: synthetic field
   private final Tuple3 x$4;
   private final String sortColumn;
   private final boolean desc;
   private final int pageSize;
   private final String encodedSortColumn;
   private final String parameterPath;
   private final SessionStatsTableDataSource dataSource;

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

   public SessionStatsTableDataSource dataSource() {
      return this.dataSource;
   }

   public String tableId() {
      return this.sessionStatsTableTag;
   }

   public String tableCssClass() {
      return "table table-bordered table-sm table-striped table-head-clickable table-cell-width-limited";
   }

   public String pageLink(final int page) {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.pageNumberFormField() + "=" + page + "&" + this.sessionStatsTableTag + ".sort=" + this.encodedSortColumn() + "&" + this.sessionStatsTableTag + ".desc=" + this.desc() + "&" + this.pageSizeFormField() + "=" + this.pageSize() + "#" + this.sessionStatsTableTag;
   }

   public String pageSizeFormField() {
      return this.sessionStatsTableTag + ".pageSize";
   }

   public String pageNumberFormField() {
      return this.sessionStatsTableTag + ".page";
   }

   public String goButtonFormPath() {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.sessionStatsTableTag + ".sort=" + this.encodedSortColumn() + "&" + this.sessionStatsTableTag + ".desc=" + this.desc() + "#" + this.sessionStatsTableTag;
   }

   public Seq headers() {
      Seq sessionTableHeadersAndTooltips = new .colon.colon(new Tuple3("User", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("IP", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("Session ID", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("Start Time", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("Finish Time", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("Duration", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.THRIFT_SESSION_DURATION())), new .colon.colon(new Tuple3("Total Execute", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.THRIFT_SESSION_TOTAL_EXECUTE())), scala.collection.immutable.Nil..MODULE$)))))));
      this.isSortColumnValid(sessionTableHeadersAndTooltips, this.sortColumn());
      return this.headerRow(sessionTableHeadersAndTooltips, this.desc(), this.pageSize(), this.sortColumn(), this.parameterPath(), this.sessionStatsTableTag, this.sessionStatsTableTag);
   }

   public Seq row(final SessionInfo session) {
      String sessionLink = scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s/%s/session/?id=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri(this.request, this.parent.basePath(), org.apache.spark.ui.UIUtils..MODULE$.prependBaseUri$default$3()), this.parent.prefix(), session.sessionId()}));
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(session.userName());
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(session.ip());
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var13 = new UnprefixedAttribute("href", sessionLink, $md);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(session.sessionId());
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "a", var13, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDate(session.startTimestamp()));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(session.finishTimestamp() > 0L ? org.apache.spark.ui.UIUtils..MODULE$.formatDate(session.finishTimestamp()) : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(org.apache.spark.ui.UIUtils..MODULE$.formatDurationVerbose(session.totalTime()));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(Long.toString(session.totalExecution()));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public SessionStatsPagedTable(final HttpServletRequest request, final ThriftServerTab parent, final Seq data, final String subPath, final String basePath, final String sessionStatsTableTag) {
      this.request = request;
      this.parent = parent;
      this.sessionStatsTableTag = sessionStatsTableTag;
      PagedTable.$init$(this);
      Tuple3 var8 = this.getTableParameters(request, sessionStatsTableTag, "Start Time");
      if (var8 != null) {
         String sortColumn = (String)var8._1();
         boolean desc = BoxesRunTime.unboxToBoolean(var8._2());
         int pageSize = BoxesRunTime.unboxToInt(var8._3());
         this.x$4 = new Tuple3(sortColumn, BoxesRunTime.boxToBoolean(desc), BoxesRunTime.boxToInteger(pageSize));
         this.sortColumn = (String)this.x$4._1();
         this.desc = BoxesRunTime.unboxToBoolean(this.x$4._2());
         this.pageSize = BoxesRunTime.unboxToInt(this.x$4._3());
         this.encodedSortColumn = URLEncoder.encode(this.sortColumn(), StandardCharsets.UTF_8.name());
         this.parameterPath = basePath + "/" + subPath + "/?" + this.getParameterOtherTable(request, sessionStatsTableTag);
         this.dataSource = new SessionStatsTableDataSource(data, this.pageSize(), this.sortColumn(), this.desc());
      } else {
         throw new MatchError(var8);
      }
   }
}
