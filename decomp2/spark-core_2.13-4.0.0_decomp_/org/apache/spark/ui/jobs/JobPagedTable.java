package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.JobData;
import org.apache.spark.ui.PagedTable;
import org.apache.spark.ui.UIUtils$;
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
   bytes = "\u0006\u0005\u0005=d!\u0002\u0011\"\u0001\rZ\u0003\u0002\u0003\u001e\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u0011\u0019\u0003!\u0011!Q\u0001\n\u001dC\u0001\"\u0014\u0001\u0003\u0002\u0003\u0006IA\u0014\u0005\tE\u0002\u0011\t\u0011)A\u0005G\"A1\u000e\u0001B\u0001B\u0003%1\r\u0003\u0005m\u0001\t\u0005\t\u0015!\u0003d\u0011!i\u0007A!A!\u0002\u0013\u0019\u0007\u0002\u00038\u0001\u0005\u0003\u0005\u000b\u0011B8\t\u0011I\u0004!\u0011!Q\u0001\n\rDQa\u001d\u0001\u0005\u0002QD1b \u0001\u0011\u0002\u0003\r\t\u0015!\u0003\u0002\u0002!I\u0011Q\u0002\u0001C\u0002\u0013%\u0011q\u0002\u0005\b\u0003#\u0001\u0001\u0015!\u0003d\u0011%\t\u0019\u0002\u0001b\u0001\n\u0013\t)\u0002C\u0004\u0002\u0018\u0001\u0001\u000b\u0011B8\t\u0013\u0005e\u0001A1A\u0005\n\u0005m\u0001\u0002CA\u000f\u0001\u0001\u0006I!a\u0002\t\u0013\u0005}\u0001A1A\u0005\n\u0005\u0005\u0002\u0002CA\u0019\u0001\u0001\u0006I!a\t\t\u0013\u0005M\u0002A1A\u0005\n\u0005\u0005\u0002\u0002CA\u001b\u0001\u0001\u0006I!a\t\t\u000f\u0005]\u0002\u0001\"\u0011\u0002\u0010!9\u0011\u0011\b\u0001\u0005B\u0005=\u0001bBA\u001e\u0001\u0011\u0005\u0013q\u0002\u0005\b\u0003{\u0001A\u0011IA\b\u0011%\ty\u0004\u0001b\u0001\n\u0003\n\t\u0005\u0003\u0005\u0002J\u0001\u0001\u000b\u0011BA\"\u0011\u001d\tY\u0005\u0001C!\u0003\u001bBq!a\u0015\u0001\t\u0003\ny\u0001C\u0004\u0002V\u0001!\t%a\u0016\t\u000f\u0005\u001d\u0004\u0001\"\u0011\u0002j\ti!j\u001c2QC\u001e,G\rV1cY\u0016T!AI\u0012\u0002\t)|'m\u001d\u0006\u0003I\u0015\n!!^5\u000b\u0005\u0019:\u0013!B:qCJ\\'B\u0001\u0015*\u0003\u0019\t\u0007/Y2iK*\t!&A\u0002pe\u001e\u001c2\u0001\u0001\u00173!\ti\u0003'D\u0001/\u0015\u0005y\u0013!B:dC2\f\u0017BA\u0019/\u0005\u0019\te.\u001f*fMB\u00191\u0007\u000e\u001c\u000e\u0003\rJ!!N\u0012\u0003\u0015A\u000bw-\u001a3UC\ndW\r\u0005\u00028q5\t\u0011%\u0003\u0002:C\ty!j\u001c2UC\ndWMU8x\t\u0006$\u0018-A\u0004sKF,Xm\u001d;\u0004\u0001A\u0011Q\bR\u0007\u0002})\u0011q\bQ\u0001\u0005QR$\bO\u0003\u0002B\u0005\u000691/\u001a:wY\u0016$(\"A\"\u0002\u000f)\f7.\u0019:uC&\u0011QI\u0010\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH/A\u0003ti>\u0014X\r\u0005\u0002I\u00176\t\u0011J\u0003\u0002KK\u000511\u000f^1ukNL!\u0001T%\u0003\u001d\u0005\u0003\bo\u0015;biV\u001c8\u000b^8sK\u0006!A-\u0019;b!\ryuK\u0017\b\u0003!Vs!!\u0015+\u000e\u0003IS!aU\u001e\u0002\rq\u0012xn\u001c;?\u0013\u0005y\u0013B\u0001,/\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001W-\u0003\u0007M+\u0017O\u0003\u0002W]A\u00111\fY\u0007\u00029*\u0011QLX\u0001\u0003mFR!aX%\u0002\u0007\u0005\u0004\u0018.\u0003\u0002b9\n9!j\u001c2ECR\f\u0017!\u0004;bE2,\u0007*Z1eKJLE\r\u0005\u0002eQ:\u0011QM\u001a\t\u0003#:J!a\u001a\u0018\u0002\rA\u0013X\rZ3g\u0013\tI'N\u0001\u0004TiJLgn\u001a\u0006\u0003O:\naA[8c)\u0006<\u0017\u0001\u00032bg\u0016\u0004\u0016\r\u001e5\u0002\u000fM,(\rU1uQ\u0006Y1.\u001b7m\u000b:\f'\r\\3e!\ti\u0003/\u0003\u0002r]\t9!i\\8mK\u0006t\u0017A\u00036pE&#G+\u001b;mK\u00061A(\u001b8jiz\"\"\"\u001e<xqfT8\u0010`?\u007f!\t9\u0004\u0001C\u0003;\u0015\u0001\u0007A\bC\u0003G\u0015\u0001\u0007q\tC\u0003N\u0015\u0001\u0007a\nC\u0003c\u0015\u0001\u00071\rC\u0003l\u0015\u0001\u00071\rC\u0003m\u0015\u0001\u00071\rC\u0003n\u0015\u0001\u00071\rC\u0003o\u0015\u0001\u0007q\u000eC\u0003s\u0015\u0001\u00071-\u0001\u0003yIE\u001a\u0004cB\u0017\u0002\u0004\r|\u0017qA\u0005\u0004\u0003\u000bq#A\u0002+va2,7\u0007E\u0002.\u0003\u0013I1!a\u0003/\u0005\rIe\u000e^\u0001\u000bg>\u0014HoQ8mk6tW#A2\u0002\u0017M|'\u000f^\"pYVlg\u000eI\u0001\u0005I\u0016\u001c8-F\u0001p\u0003\u0015!Wm]2!\u0003!\u0001\u0018mZ3TSj,WCAA\u0004\u0003%\u0001\u0018mZ3TSj,\u0007%A\u0007qCJ\fW.\u001a;feB\u000bG\u000f[\u000b\u0003\u0003G\u0001B!!\n\u000205\u0011\u0011q\u0005\u0006\u0005\u0003S\tY#\u0001\u0003mC:<'BAA\u0017\u0003\u0011Q\u0017M^1\n\u0007%\f9#\u0001\bqCJ\fW.\u001a;feB\u000bG\u000f\u001b\u0011\u0002#\u0015t7m\u001c3fIN{'\u000f^\"pYVlg.\u0001\nf]\u000e|G-\u001a3T_J$8i\u001c7v[:\u0004\u0013a\u0002;bE2,\u0017\nZ\u0001\u000ei\u0006\u0014G.Z\"tg\u000ec\u0017m]:\u0002#A\fw-Z*ju\u00164uN]7GS\u0016dG-A\nqC\u001e,g*^7cKJ4uN]7GS\u0016dG-\u0001\u0006eCR\f7k\\;sG\u0016,\"!a\u0011\u0011\u0007]\n)%C\u0002\u0002H\u0005\u0012QBS8c\t\u0006$\u0018mU8ve\u000e,\u0017a\u00033bi\u0006\u001cv.\u001e:dK\u0002\n\u0001\u0002]1hK2Kgn\u001b\u000b\u0004G\u0006=\u0003bBA)9\u0001\u0007\u0011qA\u0001\u0005a\u0006<W-\u0001\th_\n+H\u000f^8o\r>\u0014X\u000eU1uQ\u00069\u0001.Z1eKJ\u001cXCAA-!\u0011yu+a\u0017\u0011\t\u0005u\u00131M\u0007\u0003\u0003?R1!!\u0019/\u0003\rAX\u000e\\\u0005\u0005\u0003K\nyF\u0001\u0003O_\u0012,\u0017a\u0001:poR!\u0011\u0011LA6\u0011\u0019\tig\ba\u0001m\u0005Y!n\u001c2UC\ndWMU8x\u0001"
)
public class JobPagedTable implements PagedTable {
   private final String tableHeaderId;
   private final String jobTag;
   private final String basePath;
   private final boolean killEnabled;
   private final String jobIdTitle;
   // $FF: synthetic field
   private final Tuple3 x$13;
   private final String sortColumn;
   private final boolean desc;
   private final int pageSize;
   private final String parameterPath;
   private final String encodedSortColumn;
   private final JobDataSource dataSource;

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

   public String tableId() {
      return this.jobTag + "-table";
   }

   public String tableCssClass() {
      return "table table-bordered table-sm table-striped table-head-clickable table-cell-width-limited";
   }

   public String pageSizeFormField() {
      return this.jobTag + ".pageSize";
   }

   public String pageNumberFormField() {
      return this.jobTag + ".page";
   }

   public JobDataSource dataSource() {
      return this.dataSource;
   }

   public String pageLink(final int page) {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.pageNumberFormField() + "=" + page + "&" + this.jobTag + ".sort=" + this.encodedSortColumn() + "&" + this.jobTag + ".desc=" + this.desc() + "&" + this.pageSizeFormField() + "=" + this.pageSize() + "#" + this.tableHeaderId;
   }

   public String goButtonFormPath() {
      String var10000 = this.parameterPath();
      return var10000 + "&" + this.jobTag + ".sort=" + this.encodedSortColumn() + "&" + this.jobTag + ".desc=" + this.desc() + "#" + this.tableHeaderId;
   }

   public Seq headers() {
      Seq jobHeadersAndCssClasses = new .colon.colon(new Tuple3(this.jobIdTitle, BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("Description", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("Submitted", BoxesRunTime.boxToBoolean(true), scala.None..MODULE$), new .colon.colon(new Tuple3("Duration", BoxesRunTime.boxToBoolean(true), new Some("Elapsed time since the job was submitted until execution completion of all its stages.")), new .colon.colon(new Tuple3("Stages: Succeeded/Total", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), new .colon.colon(new Tuple3("Tasks (for all stages): Succeeded/Total", BoxesRunTime.boxToBoolean(false), scala.None..MODULE$), scala.collection.immutable.Nil..MODULE$))))));
      this.isSortColumnValid(jobHeadersAndCssClasses, this.sortColumn());
      return this.headerRow(jobHeadersAndCssClasses, this.desc(), this.pageSize(), this.sortColumn(), this.parameterPath(), this.jobTag, this.tableHeaderId);
   }

   public Seq row(final JobTableRowData jobTableRow) {
      JobData job = jobTableRow.jobData();
      Object var28;
      if (this.killEnabled) {
         String confirm = "if (window.confirm('Are you sure you want to kill job " + job.jobId() + " ?')) { this.parentNode.submit(); return true; } else { return false; }";
         String var10000 = this.basePath;
         String killLinkUri = var10000 + "/jobs/job/kill/?id=" + job.jobId();
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var20 = new UnprefixedAttribute("class", new Text("kill-link"), $md);
         var20 = new UnprefixedAttribute("onclick", confirm, var20);
         var20 = new UnprefixedAttribute("href", killLinkUri, var20);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("(kill)"));
         var28 = new Elem((String)null, "a", var20, var10005, false, var10007.seqToNodeSeq($buf));
      } else {
         var28 = (Seq)scala.package..MODULE$.Seq().empty();
      }

      Seq killLink = (Seq)var28;
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var23 = new UnprefixedAttribute("id", "job-" + job.jobId(), $md);
      TopScope var29 = scala.xml.TopScope..MODULE$;
      NodeSeq var30 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(job.jobId()));
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(job.jobGroup().map((id) -> "(" + id + ")").getOrElse(() -> ""));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(jobTableRow.jobDescription());
      $buf.$amp$plus(new Text(" "));
      $buf.$amp$plus(killLink);
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var24 = new UnprefixedAttribute("class", new Text("name-link"), $md);
      var24 = new UnprefixedAttribute("href", jobTableRow.detailUrl(), var24);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(jobTableRow.lastStageName());
      $buf.$amp$plus(new Elem((String)null, "a", var24, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(jobTableRow.formattedSubmissionTime());
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(jobTableRow.formattedDuration());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var26 = new UnprefixedAttribute("class", new Text("stage-progress-cell"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(job.numCompletedStages()));
      $buf.$amp$plus(new Text("/"));
      $buf.$amp$plus(BoxesRunTime.boxToInteger(job.stageIds().size() - job.numSkippedStages()));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(job.numFailedStages() > 0 ? "(" + job.numFailedStages() + " failed)" : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(job.numSkippedStages() > 0 ? "(" + job.numSkippedStages() + " skipped)" : BoxedUnit.UNIT);
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var26, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var27 = new UnprefixedAttribute("class", new Text("progress-cell"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(UIUtils$.MODULE$.makeProgressBar(job.numActiveTasks(), job.numCompletedIndices(), job.numFailedTasks(), job.numSkippedTasks(), job.killedTasksSummary(), job.numTasks() - job.numSkippedTasks()));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "td", var27, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var23, var29, false, var30.seqToNodeSeq($buf));
   }

   public JobPagedTable(final HttpServletRequest request, final AppStatusStore store, final Seq data, final String tableHeaderId, final String jobTag, final String basePath, final String subPath, final boolean killEnabled, final String jobIdTitle) {
      this.tableHeaderId = tableHeaderId;
      this.jobTag = jobTag;
      this.basePath = basePath;
      this.killEnabled = killEnabled;
      this.jobIdTitle = jobIdTitle;
      PagedTable.$init$(this);
      Tuple3 var11 = this.getTableParameters(request, jobTag, jobIdTitle);
      if (var11 != null) {
         String sortColumn = (String)var11._1();
         boolean desc = BoxesRunTime.unboxToBoolean(var11._2());
         int pageSize = BoxesRunTime.unboxToInt(var11._3());
         this.x$13 = new Tuple3(sortColumn, BoxesRunTime.boxToBoolean(desc), BoxesRunTime.boxToInteger(pageSize));
         this.sortColumn = (String)this.x$13._1();
         this.desc = BoxesRunTime.unboxToBoolean(this.x$13._2());
         this.pageSize = BoxesRunTime.unboxToInt(this.x$13._3());
         this.parameterPath = basePath + "/" + subPath + "/?" + this.getParameterOtherTable(request, jobTag);
         this.encodedSortColumn = URLEncoder.encode(this.sortColumn(), StandardCharsets.UTF_8.name());
         this.dataSource = new JobDataSource(store, data, basePath, this.pageSize(), this.sortColumn(), this.desc());
      } else {
         throw new MatchError(var11);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
