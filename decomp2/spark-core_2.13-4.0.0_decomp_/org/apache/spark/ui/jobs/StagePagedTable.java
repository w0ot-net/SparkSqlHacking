package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.ui.PagedTable;
import org.apache.spark.ui.ToolTips$;
import org.apache.spark.ui.UIUtils$;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
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
   bytes = "\u0006\u0005\u0005\u001df!B\u0013'\u0001!\u0002\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011B!\t\u0011\u001d\u0003!\u0011!Q\u0001\n!C\u0001\u0002\u0018\u0001\u0003\u0002\u0003\u0006I!\u0018\u0005\tK\u0002\u0011\t\u0011)A\u0005;\"Aa\r\u0001B\u0001B\u0003%Q\f\u0003\u0005h\u0001\t\u0005\t\u0015!\u0003^\u0011!A\u0007A!A!\u0002\u0013I\u0007\u0002\u00037\u0001\u0005\u0003\u0005\u000b\u0011B5\t\u00115\u0004!\u0011!Q\u0001\n9D\u0001\"\u001d\u0001\u0003\u0002\u0003\u0006I!\u001b\u0005\te\u0002\u0011\t\u0011)A\u0005g\")Q\u0010\u0001C\u0001}\"9\u0011q\u0003\u0001\u0005B\u0005e\u0001bBA\u000e\u0001\u0011\u0005\u0013\u0011\u0004\u0005\b\u0003;\u0001A\u0011IA\r\u0011\u001d\ty\u0002\u0001C!\u00033AA\"!\t\u0001!\u0003\u0005\u0019\u0011)A\u0005\u0003GA\u0011\"a\f\u0001\u0005\u0004%I!!\u0007\t\u000f\u0005E\u0002\u0001)A\u0005;\"I\u00111\u0007\u0001C\u0002\u0013%\u0011Q\u0007\u0005\b\u0003o\u0001\u0001\u0015!\u0003j\u0011%\tI\u0004\u0001b\u0001\n\u0013\tY\u0004\u0003\u0005\u0002>\u0001\u0001\u000b\u0011BA\u0015\u0011%\ty\u0004\u0001b\u0001\n\u0013\t\t\u0005\u0003\u0005\u0002R\u0001\u0001\u000b\u0011BA\"\u0011%\t\u0019\u0006\u0001b\u0001\n\u0013\t\t\u0005\u0003\u0005\u0002V\u0001\u0001\u000b\u0011BA\"\u0011%\t9\u0006\u0001b\u0001\n\u0003\nI\u0006\u0003\u0005\u0002b\u0001\u0001\u000b\u0011BA.\u0011\u001d\t\u0019\u0007\u0001C!\u0003KBq!a\u001b\u0001\t\u0003\nI\u0002C\u0004\u0002n\u0001!\t%a\u001c\t\u000f\u0005}\u0004\u0001\"\u0011\u0002\u0002\"9\u0011q\u0011\u0001\u0005\n\u0005%\u0005bBAG\u0001\u0011%\u0011q\u0012\u0005\b\u0003?\u0003A\u0011CAQ\u0005=\u0019F/Y4f!\u0006<W\r\u001a+bE2,'BA\u0014)\u0003\u0011QwNY:\u000b\u0005%R\u0013AA;j\u0015\tYC&A\u0003ta\u0006\u00148N\u0003\u0002.]\u00051\u0011\r]1dQ\u0016T\u0011aL\u0001\u0004_J<7c\u0001\u00012oA\u0011!'N\u0007\u0002g)\tA'A\u0003tG\u0006d\u0017-\u0003\u00027g\t1\u0011I\\=SK\u001a\u00042\u0001O\u001d<\u001b\u0005A\u0013B\u0001\u001e)\u0005)\u0001\u0016mZ3e)\u0006\u0014G.\u001a\t\u0003yuj\u0011AJ\u0005\u0003}\u0019\u0012\u0011c\u0015;bO\u0016$\u0016M\u00197f%><H)\u0019;b\u0003\u0015\u0019Ho\u001c:f\u0007\u0001\u0001\"AQ#\u000e\u0003\rS!\u0001\u0012\u0016\u0002\rM$\u0018\r^;t\u0013\t15I\u0001\bBaB\u001cF/\u0019;vgN#xN]3\u0002\rM$\u0018mZ3t!\rI\u0015\u000b\u0016\b\u0003\u0015>s!a\u0013(\u000e\u00031S!!\u0014!\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0014B\u0001)4\u0003\u001d\u0001\u0018mY6bO\u0016L!AU*\u0003\u0007M+\u0017O\u0003\u0002QgA\u0011QKW\u0007\u0002-*\u0011q\u000bW\u0001\u0003mFR!!W\"\u0002\u0007\u0005\u0004\u0018.\u0003\u0002\\-\nI1\u000b^1hK\u0012\u000bG/Y\u0001\u000ei\u0006\u0014G.\u001a%fC\u0012,'/\u00133\u0011\u0005y\u0013gBA0a!\tY5'\u0003\u0002bg\u00051\u0001K]3eK\u001aL!a\u00193\u0003\rM#(/\u001b8h\u0015\t\t7'\u0001\u0005ti\u0006<W\rV1h\u0003!\u0011\u0017m]3QCRD\u0017aB:vEB\u000bG\u000f[\u0001\u0010SN4\u0015-\u001b:TG\",G-\u001e7feB\u0011!G[\u0005\u0003WN\u0012qAQ8pY\u0016\fg.A\u0006lS2dWI\\1cY\u0016$\u0017aC2veJ,g\u000e\u001e+j[\u0016\u0004\"AM8\n\u0005A\u001c$\u0001\u0002'p]\u001e\fQ\"[:GC&dW\rZ*uC\u001e,\u0017a\u0002:fcV,7\u000f\u001e\t\u0003inl\u0011!\u001e\u0006\u0003m^\fA\u0001\u001b;ua*\u0011\u00010_\u0001\bg\u0016\u0014h\u000f\\3u\u0015\u0005Q\u0018a\u00026bW\u0006\u0014H/Y\u0005\u0003yV\u0014!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\u00061A(\u001b8jiz\"rc`A\u0001\u0003\u0007\t)!a\u0002\u0002\n\u0005-\u0011QBA\b\u0003#\t\u0019\"!\u0006\u0011\u0005q\u0002\u0001\"B \r\u0001\u0004\t\u0005\"B$\r\u0001\u0004A\u0005\"\u0002/\r\u0001\u0004i\u0006\"B3\r\u0001\u0004i\u0006\"\u00024\r\u0001\u0004i\u0006\"B4\r\u0001\u0004i\u0006\"\u00025\r\u0001\u0004I\u0007\"\u00027\r\u0001\u0004I\u0007\"B7\r\u0001\u0004q\u0007\"B9\r\u0001\u0004I\u0007\"\u0002:\r\u0001\u0004\u0019\u0018a\u0002;bE2,\u0017\nZ\u000b\u0002;\u0006iA/\u00192mK\u000e\u001b8o\u00117bgN\f\u0011\u0003]1hKNK'0\u001a$pe64\u0015.\u001a7e\u0003M\u0001\u0018mZ3Ok6\u0014WM\u001d$pe64\u0015.\u001a7e\u0003\rAHE\r\t\be\u0005\u0015R,[A\u0015\u0013\r\t9c\r\u0002\u0007)V\u0004H.Z\u001a\u0011\u0007I\nY#C\u0002\u0002.M\u00121!\u00138u\u0003)\u0019xN\u001d;D_2,XN\\\u0001\fg>\u0014HoQ8mk6t\u0007%\u0001\u0003eKN\u001cW#A5\u0002\u000b\u0011,7o\u0019\u0011\u0002\u0011A\fw-Z*ju\u0016,\"!!\u000b\u0002\u0013A\fw-Z*ju\u0016\u0004\u0013!E3oG>$W\rZ*peR\u001cu\u000e\\;n]V\u0011\u00111\t\t\u0005\u0003\u000b\ny%\u0004\u0002\u0002H)!\u0011\u0011JA&\u0003\u0011a\u0017M\\4\u000b\u0005\u00055\u0013\u0001\u00026bm\u0006L1aYA$\u0003I)gnY8eK\u0012\u001cvN\u001d;D_2,XN\u001c\u0011\u0002\u001bA\f'/Y7fi\u0016\u0014\b+\u0019;i\u00039\u0001\u0018M]1nKR,'\u000fU1uQ\u0002\n!\u0002Z1uCN{WO]2f+\t\tY\u0006E\u0002=\u0003;J1!a\u0018'\u0005=\u0019F/Y4f\t\u0006$\u0018mU8ve\u000e,\u0017a\u00033bi\u0006\u001cv.\u001e:dK\u0002\n\u0001\u0002]1hK2Kgn\u001b\u000b\u0004;\u0006\u001d\u0004bBA5=\u0001\u0007\u0011\u0011F\u0001\u0005a\u0006<W-\u0001\th_\n+H\u000f^8o\r>\u0014X\u000eU1uQ\u00069\u0001.Z1eKJ\u001cXCAA9!\u0011I\u0015+a\u001d\u0011\t\u0005U\u00141P\u0007\u0003\u0003oR1!!\u001f4\u0003\rAX\u000e\\\u0005\u0005\u0003{\n9H\u0001\u0003O_\u0012,\u0017a\u0001:poR!\u0011\u0011OAB\u0011\u0019\t))\ta\u0001w\u0005!A-\u0019;b\u0003)\u0011xn^\"p]R,g\u000e\u001e\u000b\u0005\u0003c\nY\t\u0003\u0004\u0002\u0006\n\u0002\raO\u0001\u0010[\u0006\\W\rR3tGJL\u0007\u000f^5p]R1\u0011\u0011OAI\u0003+Ca!a%$\u0001\u0004!\u0016!A:\t\u000f\u0005]5\u00051\u0001\u0002\u001a\u0006\tB-Z:de&\u0004H/[8o\u001fB$\u0018n\u001c8\u0011\tI\nY*X\u0005\u0004\u0003;\u001b$AB(qi&|g.A\bnSN\u001c\u0018N\\4Ti\u0006<WMU8x)\u0011\t\t(a)\t\u000f\u0005\u0015F\u00051\u0001\u0002*\u000591\u000f^1hK&#\u0007"
)
public class StagePagedTable implements PagedTable {
   private final AppStatusStore store;
   private final String tableHeaderId;
   private final String stageTag;
   private final String basePath;
   private final boolean isFairScheduler;
   private final boolean killEnabled;
   private final boolean isFailedStage;
   private final HttpServletRequest request;
   // $FF: synthetic field
   private final Tuple3 x$2;
   private final String sortColumn;
   private final boolean desc;
   private final int pageSize;
   private final String encodedSortColumn;
   private final String parameterPath;
   private final StageDataSource dataSource;

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

   public String tableId() {
      return this.stageTag + "-table";
   }

   public String tableCssClass() {
      return "table table-bordered table-sm table-striped table-head-clickable table-cell-width-limited";
   }

   public String pageSizeFormField() {
      return this.stageTag + ".pageSize";
   }

   public String pageNumberFormField() {
      return this.stageTag + ".page";
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

   public StageDataSource dataSource() {
      return this.dataSource;
   }

   public String pageLink(final int page) {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.pageNumberFormField() + "=" + page + "&" + this.stageTag + ".sort=" + this.encodedSortColumn() + "&" + this.stageTag + ".desc=" + this.desc() + "&" + this.pageSizeFormField() + "=" + this.pageSize() + "#" + this.tableHeaderId;
   }

   public String goButtonFormPath() {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.stageTag + ".sort=" + this.encodedSortColumn() + "&" + this.stageTag + ".desc=" + this.desc() + "#" + this.tableHeaderId;
   }

   public Seq headers() {
      Seq stageHeadersAndCssClasses = (Seq)((IterableOps)((IterableOps)(new .colon.colon(new Tuple3("Stage Id", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), scala.collection.immutable.Nil..MODULE$)).$plus$plus((IterableOnce)(this.isFairScheduler ? new .colon.colon(new Tuple3("Pool Name", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), scala.collection.immutable.Nil..MODULE$) : scala.package..MODULE$.Seq().empty()))).$plus$plus(scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple3[]{new Tuple3("Description", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("Submitted", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new Tuple3("Duration", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.DURATION())), new Tuple3("Tasks: Succeeded/Total", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), new Tuple3("Input", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.INPUT())), new Tuple3("Output", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.OUTPUT())), new Tuple3("Shuffle Read", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.SHUFFLE_READ())), new Tuple3("Shuffle Write", BoxesRunTime.boxToBoolean(true), new Some(ToolTips$.MODULE$.SHUFFLE_WRITE()))}))))).$plus$plus((IterableOnce)(this.isFailedStage ? new .colon.colon(new Tuple3("Failure Reason", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), scala.collection.immutable.Nil..MODULE$) : scala.package..MODULE$.Seq().empty()));
      this.isSortColumnValid(stageHeadersAndCssClasses, this.sortColumn());
      return this.headerRow(stageHeadersAndCssClasses, this.desc(), this.pageSize(), this.sortColumn(), this.parameterPath(), this.stageTag, this.tableHeaderId);
   }

   public Seq row(final StageTableRowData data) {
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var4 = new UnprefixedAttribute("id", "stage-" + data.stageId() + "-" + data.attemptId(), $md);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(this.rowContent(data));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var4, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Seq rowContent(final StageTableRowData data) {
      Option var3 = data.option();
      if (scala.None..MODULE$.equals(var3)) {
         return this.missingStageRow(data.stageId());
      } else if (var3 instanceof Some) {
         Some var4 = (Some)var3;
         StageData stageData = (StageData)var4.value();
         StageData info = data.stage();
         Elem var10000;
         if (data.attemptId() > 0) {
            Null var10004 = scala.xml.Null..MODULE$;
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(BoxesRunTime.boxToInteger(data.stageId()));
            $buf.$amp$plus(new Text(" (retry "));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(data.attemptId()));
            $buf.$amp$plus(new Text(")"));
            var10000 = new Elem((String)null, "td", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         } else {
            Null var27 = scala.xml.Null..MODULE$;
            TopScope var28 = scala.xml.TopScope..MODULE$;
            NodeSeq var36 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(BoxesRunTime.boxToInteger(data.stageId()));
            var10000 = new Elem((String)null, "td", var27, var28, false, var36.seqToNodeSeq($buf));
         }

         Object var10001;
         if (this.isFairScheduler) {
            Null var29 = scala.xml.Null..MODULE$;
            TopScope var10006 = scala.xml.TopScope..MODULE$;
            NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var23 = new UnprefixedAttribute("href", scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s/stages/pool?poolname=%s"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{UIUtils$.MODULE$.prependBaseUri(this.request, this.basePath, UIUtils$.MODULE$.prependBaseUri$default$3()), data.schedulingPool()})), $md);
            TopScope var10015 = scala.xml.TopScope..MODULE$;
            NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(data.schedulingPool());
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "a", var23, var10015, false, var10017.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            var10001 = new Elem((String)null, "td", var29, var10006, false, var10008.seqToNodeSeq($buf));
         } else {
            var10001 = (Seq)scala.package..MODULE$.Seq().empty();
         }

         NodeSeq var26 = var10000.$plus$plus((Seq)var10001);
         NodeBuffer $buf = new NodeBuffer();
         Null var30 = scala.xml.Null..MODULE$;
         TopScope var37 = scala.xml.TopScope..MODULE$;
         NodeSeq var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(this.makeDescription(info, data.descriptionOption()));
         $buf.$amp$plus(new Elem((String)null, "td", var30, var37, false, var10009.seqToNodeSeq($buf)));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var24 = new UnprefixedAttribute("valign", new Text("middle"), $md);
         var37 = scala.xml.TopScope..MODULE$;
         var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(data.formattedSubmissionTime());
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "td", var24, var37, false, var10009.seqToNodeSeq($buf)));
         var30 = scala.xml.Null..MODULE$;
         var37 = scala.xml.TopScope..MODULE$;
         var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(data.formattedDuration());
         $buf.$amp$plus(new Elem((String)null, "td", var30, var37, false, var10009.seqToNodeSeq($buf)));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var25 = new UnprefixedAttribute("class", new Text("progress-cell"), $md);
         var37 = scala.xml.TopScope..MODULE$;
         var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(UIUtils$.MODULE$.makeProgressBar(stageData.numActiveTasks(), stageData.numCompleteTasks(), stageData.numFailedTasks(), 0, stageData.killedTasksSummary(), info.numTasks()));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "td", var25, var37, false, var10009.seqToNodeSeq($buf)));
         var30 = scala.xml.Null..MODULE$;
         var37 = scala.xml.TopScope..MODULE$;
         var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(data.inputReadWithUnit());
         $buf.$amp$plus(new Elem((String)null, "td", var30, var37, false, var10009.seqToNodeSeq($buf)));
         var30 = scala.xml.Null..MODULE$;
         var37 = scala.xml.TopScope..MODULE$;
         var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(data.outputWriteWithUnit());
         $buf.$amp$plus(new Elem((String)null, "td", var30, var37, false, var10009.seqToNodeSeq($buf)));
         var30 = scala.xml.Null..MODULE$;
         var37 = scala.xml.TopScope..MODULE$;
         var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(data.shuffleReadWithUnit());
         $buf.$amp$plus(new Elem((String)null, "td", var30, var37, false, var10009.seqToNodeSeq($buf)));
         var30 = scala.xml.Null..MODULE$;
         var37 = scala.xml.TopScope..MODULE$;
         var10009 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(data.shuffleWriteWithUnit());
         $buf.$amp$plus(new Elem((String)null, "td", var30, var37, false, var10009.seqToNodeSeq($buf)));
         return (Seq)((IterableOps)var26.$plus$plus($buf)).$plus$plus((IterableOnce)(this.isFailedStage ? UIUtils$.MODULE$.errorMessageCell((String)info.failureReason().getOrElse(() -> "")) : scala.package..MODULE$.Seq().empty()));
      } else {
         throw new MatchError(var3);
      }
   }

   private Seq makeDescription(final StageData s, final Option descriptionOption) {
      String basePathUri = UIUtils$.MODULE$.prependBaseUri(this.request, this.basePath, UIUtils$.MODULE$.prependBaseUri$default$3());
      Object var10000;
      if (this.killEnabled) {
         String confirm = "if (window.confirm('Are you sure you want to kill stage " + s.stageId() + " ?')) { this.parentNode.submit(); return true; } else { return false; }";
         String killLinkUri = basePathUri + "/stages/stage/kill/?id=" + s.stageId();
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var22 = new UnprefixedAttribute("class", new Text("kill-link"), $md);
         var22 = new UnprefixedAttribute("onclick", confirm, var22);
         var22 = new UnprefixedAttribute("href", killLinkUri, var22);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("(kill)"));
         var10000 = new Elem((String)null, "a", var22, var10005, false, var10007.seqToNodeSeq($buf));
      } else {
         var10000 = (Seq)scala.package..MODULE$.Seq().empty();
      }

      Seq killLink = (Seq)var10000;
      String nameLinkUri = basePathUri + "/stages/stage/?id=" + s.stageId() + "&attempt=" + s.attemptId();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var25 = new UnprefixedAttribute("class", new Text("name-link"), $md);
      var25 = new UnprefixedAttribute("href", nameLinkUri, var25);
      TopScope var32 = scala.xml.TopScope..MODULE$;
      NodeSeq var35 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(s.name());
      Elem nameLink = new Elem((String)null, "a", var25, var32, false, var35.seqToNodeSeq($buf));
      Seq cachedRddInfos = (Seq)this.store.rddList(this.store.rddList$default$1()).filter((rdd) -> BoxesRunTime.boxToBoolean($anonfun$makeDescription$1(s, rdd)));
      if (s.details() != null && scala.collection.StringOps..MODULE$.nonEmpty$extension(scala.Predef..MODULE$.augmentString(s.details()))) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var27 = new UnprefixedAttribute("class", new Text("expand-details"), $md);
         var27 = new UnprefixedAttribute("onclick", new Text("this.parentNode.querySelector('.stage-details').classList.toggle('collapsed')"), var27);
         var32 = scala.xml.TopScope..MODULE$;
         var35 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        +details\n      "));
         Elem var31 = new Elem((String)null, "span", var27, var32, false, var35.seqToNodeSeq($buf));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var29 = new UnprefixedAttribute("class", new Text("stage-details collapsed"), $md);
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(cachedRddInfos.nonEmpty() ? scala.xml.Text..MODULE$.apply("RDD: ").$plus$plus((Seq)cachedRddInfos.map((i) -> {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var4 = new UnprefixedAttribute("href", basePathUri + "/storage/rdd/?id=" + i.id(), $md);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(i.name());
            return new Elem((String)null, "a", var4, var10005, false, var10007.seqToNodeSeq($buf));
         })) : BoxedUnit.UNIT);
         $buf.$amp$plus(new Text("\n        "));
         Null var10014 = scala.xml.Null..MODULE$;
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(s.details());
         $buf.$amp$plus(new Elem((String)null, "pre", var10014, var10015, false, var10017.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var10000 = var31.$plus$plus(new Elem((String)null, "div", var29, var10006, false, var10008.seqToNodeSeq($buf)));
      } else {
         var10000 = BoxedUnit.UNIT;
      }

      Object details = var10000;
      Option stageDesc = descriptionOption.map((x$3) -> UIUtils$.MODULE$.makeDescription(x$3, basePathUri, UIUtils$.MODULE$.makeDescription$default$3()));
      Null var10004 = scala.xml.Null..MODULE$;
      var32 = scala.xml.TopScope..MODULE$;
      var35 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(stageDesc.getOrElse(() -> ""));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(killLink);
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(nameLink);
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(details);
      return new Elem((String)null, "div", var10004, var32, false, var35.seqToNodeSeq($buf));
   }

   public Seq missingStageRow(final int stageId) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(stageId));
      Elem var10000 = new Elem((String)null, "td", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      Object var10001;
      if (this.isFairScheduler) {
         Null var6 = scala.xml.Null..MODULE$;
         TopScope var10006 = scala.xml.TopScope..MODULE$;
         NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("-"));
         var10001 = new Elem((String)null, "td", var6, var10006, false, var10008.seqToNodeSeq($buf));
      } else {
         var10001 = (Seq)scala.package..MODULE$.Seq().empty();
      }

      NodeSeq var5 = var10000.$plus$plus((Seq)var10001);
      Null var7 = scala.xml.Null..MODULE$;
      TopScope var8 = scala.xml.TopScope..MODULE$;
      NodeSeq var9 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("No data available for this stage"));
      return var5.$plus$plus(new Elem((String)null, "td", var7, var8, false, var9.seqToNodeSeq($buf))).$plus$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(new Elem((String)null, "td", scala.xml.Null..MODULE$, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$makeDescription$1(final StageData s$1, final RDDStorageInfo rdd) {
      return s$1.rddIds().contains(BoxesRunTime.boxToInteger(rdd.id()));
   }

   public StagePagedTable(final AppStatusStore store, final Seq stages, final String tableHeaderId, final String stageTag, final String basePath, final String subPath, final boolean isFairScheduler, final boolean killEnabled, final long currentTime, final boolean isFailedStage, final HttpServletRequest request) {
      this.store = store;
      this.tableHeaderId = tableHeaderId;
      this.stageTag = stageTag;
      this.basePath = basePath;
      this.isFairScheduler = isFairScheduler;
      this.killEnabled = killEnabled;
      this.isFailedStage = isFailedStage;
      this.request = request;
      PagedTable.$init$(this);
      Tuple3 var14 = this.getTableParameters(request, stageTag, "Stage Id");
      if (var14 != null) {
         String sortColumn = (String)var14._1();
         boolean desc = BoxesRunTime.unboxToBoolean(var14._2());
         int pageSize = BoxesRunTime.unboxToInt(var14._3());
         this.x$2 = new Tuple3(sortColumn, BoxesRunTime.boxToBoolean(desc), BoxesRunTime.boxToInteger(pageSize));
         this.sortColumn = (String)this.x$2._1();
         this.desc = BoxesRunTime.unboxToBoolean(this.x$2._2());
         this.pageSize = BoxesRunTime.unboxToInt(this.x$2._3());
         this.encodedSortColumn = URLEncoder.encode(this.sortColumn(), StandardCharsets.UTF_8.name());
         this.parameterPath = UIUtils$.MODULE$.prependBaseUri(request, basePath, UIUtils$.MODULE$.prependBaseUri$default$3()) + "/" + subPath + "/?" + this.getParameterOtherTable(request, stageTag);
         this.dataSource = new StageDataSource(store, stages, currentTime, this.pageSize(), this.sortColumn(), this.desc());
      } else {
         throw new MatchError(var14);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
