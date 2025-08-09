package org.apache.spark.ui.storage;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.ui.PagedTable;
import org.apache.spark.util.Utils$;
import scala.MatchError;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b!\u0002\r\u001a\u0001m\u0019\u0003\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001b\t\u0011y\u0002!\u0011!Q\u0001\n}B\u0001B\u0013\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u0017\u0002\u0011\t\u0011)A\u0005\u0019\"AA\f\u0001B\u0001B\u0003%Q\fC\u0003i\u0001\u0011\u0005\u0011\u000e\u0003\u0006q\u0001A\u0005\t1!Q\u0001\nEDqA\u001f\u0001C\u0002\u0013%1\u0010\u0003\u0004}\u0001\u0001\u0006Ia\u0010\u0005\b{\u0002\u0011\r\u0011\"\u0003\u007f\u0011\u0019y\b\u0001)A\u0005i\"I\u0011\u0011\u0001\u0001C\u0002\u0013%\u00111\u0001\u0005\b\u0003\u000b\u0001\u0001\u0015!\u0003x\u0011\u0019\t9\u0001\u0001C!w\"1\u0011\u0011\u0002\u0001\u0005BmDa!a\u0003\u0001\t\u0003Z\bBBA\u0007\u0001\u0011\u00053\u0010C\u0005\u0002\u0010\u0001\u0011\r\u0011\"\u0011\u0002\u0012!A\u0011\u0011\u0004\u0001!\u0002\u0013\t\u0019\u0002C\u0004\u0002\u001c\u0001!\t%!\b\t\r\u0005\r\u0002\u0001\"\u0011|\u0011\u001d\t)\u0003\u0001C!\u0003OAq!a\u000e\u0001\t\u0003\nIDA\bCY>\u001c7\u000eU1hK\u0012$\u0016M\u00197f\u0015\tQ2$A\u0004ti>\u0014\u0018mZ3\u000b\u0005qi\u0012AA;j\u0015\tqr$A\u0003ta\u0006\u00148N\u0003\u0002!C\u00051\u0011\r]1dQ\u0016T\u0011AI\u0001\u0004_J<7c\u0001\u0001%UA\u0011Q\u0005K\u0007\u0002M)\tq%A\u0003tG\u0006d\u0017-\u0003\u0002*M\t1\u0011I\\=SK\u001a\u00042a\u000b\u0017/\u001b\u0005Y\u0012BA\u0017\u001c\u0005)\u0001\u0016mZ3e)\u0006\u0014G.\u001a\t\u0003_Aj\u0011!G\u0005\u0003ce\u0011\u0011C\u00117pG.$\u0016M\u00197f%><H)\u0019;b\u0003\u001d\u0011X-];fgR\u001c\u0001\u0001\u0005\u00026y5\taG\u0003\u00028q\u0005!\u0001\u000e\u001e;q\u0015\tI$(A\u0004tKJ4H.\u001a;\u000b\u0003m\nqA[1lCJ$\u0018-\u0003\u0002>m\t\u0011\u0002\n\u001e;q'\u0016\u0014h\u000f\\3u%\u0016\fX/Z:u\u0003\u0019\u0011H\r\u001a+bOB\u0011\u0001i\u0012\b\u0003\u0003\u0016\u0003\"A\u0011\u0014\u000e\u0003\rS!\u0001R\u001a\u0002\rq\u0012xn\u001c;?\u0013\t1e%\u0001\u0004Qe\u0016$WMZ\u0005\u0003\u0011&\u0013aa\u0015;sS:<'B\u0001$'\u0003!\u0011\u0017m]3QCRD\u0017!\u0004:eIB\u000b'\u000f^5uS>t7\u000fE\u0002N!Jk\u0011A\u0014\u0006\u0003\u001f\u001a\n!bY8mY\u0016\u001cG/[8o\u0013\t\tfJA\u0002TKF\u0004\"a\u0015.\u000e\u0003QS!!\u0016,\u0002\u0005Y\f$BA,Y\u0003\r\t\u0007/\u001b\u0006\u00033v\taa\u001d;biV\u001c\u0018BA.U\u0005A\u0011F\t\u0012)beRLG/[8o\u0013:4w.A\tfq\u0016\u001cW\u000f^8s'VlW.\u0019:jKN\u00042AX2f\u001d\ty\u0016M\u0004\u0002CA&\tq%\u0003\u0002cM\u00059\u0001/Y2lC\u001e,\u0017BA)e\u0015\t\u0011g\u0005\u0005\u0002TM&\u0011q\r\u0016\u0002\u0010\u000bb,7-\u001e;peN+X.\\1ss\u00061A(\u001b8jiz\"bA[6m[:|\u0007CA\u0018\u0001\u0011\u0015\u0011d\u00011\u00015\u0011\u0015qd\u00011\u0001@\u0011\u0015Qe\u00011\u0001@\u0011\u0015Ye\u00011\u0001M\u0011\u0015af\u00011\u0001^\u0003\rAH%\u000f\t\u0006KI|Do^\u0005\u0003g\u001a\u0012a\u0001V;qY\u0016\u001c\u0004CA\u0013v\u0013\t1hEA\u0004C_>dW-\u00198\u0011\u0005\u0015B\u0018BA='\u0005\rIe\u000e^\u0001\u000bg>\u0014HoQ8mk6tW#A \u0002\u0017M|'\u000f^\"pYVlg\u000eI\u0001\u0005I\u0016\u001c8-F\u0001u\u0003\u0015!Wm]2!\u0003!\u0001\u0018mZ3TSj,W#A<\u0002\u0013A\fw-Z*ju\u0016\u0004\u0013a\u0002;bE2,\u0017\nZ\u0001\u000ei\u0006\u0014G.Z\"tg\u000ec\u0017m]:\u0002#A\fw-Z*ju\u00164uN]7GS\u0016dG-A\nqC\u001e,g*^7cKJ4uN]7GS\u0016dG-\u0001\u0006eCR\f7k\\;sG\u0016,\"!a\u0005\u0011\u0007=\n)\"C\u0002\u0002\u0018e\u0011qB\u00117pG.$\u0015\r^1T_V\u00148-Z\u0001\fI\u0006$\u0018mU8ve\u000e,\u0007%\u0001\u0005qC\u001e,G*\u001b8l)\ry\u0014q\u0004\u0005\u0007\u0003C!\u0002\u0019A<\u0002\tA\fw-Z\u0001\u0011O>\u0014U\u000f\u001e;p]\u001a{'/\u001c)bi\"\fq\u0001[3bI\u0016\u00148/\u0006\u0002\u0002*A!alYA\u0016!\u0011\ti#a\r\u000e\u0005\u0005=\"bAA\u0019M\u0005\u0019\u00010\u001c7\n\t\u0005U\u0012q\u0006\u0002\u0005\u001d>$W-A\u0002s_^$B!!\u000b\u0002<!1\u0011QH\fA\u00029\nQA\u00197pG.\u0004"
)
public class BlockPagedTable implements PagedTable {
   private final String rddTag;
   private final String basePath;
   // $FF: synthetic field
   private final Tuple3 x$9;
   private final String sortColumn;
   private final boolean desc;
   private final int pageSize;
   private final BlockDataSource dataSource;

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

   public String tableId() {
      return "rdd-storage-by-block-table";
   }

   public String tableCssClass() {
      return "table table-bordered table-sm table-striped table-head-clickable";
   }

   public String pageSizeFormField() {
      return this.rddTag + ".pageSize";
   }

   public String pageNumberFormField() {
      return this.rddTag + ".page";
   }

   public BlockDataSource dataSource() {
      return this.dataSource;
   }

   public String pageLink(final int page) {
      String encodedSortColumn = URLEncoder.encode(this.sortColumn(), StandardCharsets.UTF_8.name());
      String var10000 = this.basePath;
      return var10000 + "&" + this.pageNumberFormField() + "=" + page + "&block.sort=" + encodedSortColumn + "&block.desc=" + this.desc() + "&" + this.pageSizeFormField() + "=" + this.pageSize();
   }

   public String goButtonFormPath() {
      String encodedSortColumn = URLEncoder.encode(this.sortColumn(), StandardCharsets.UTF_8.name());
      return this.basePath + "&block.sort=" + encodedSortColumn + "&block.desc=" + this.desc();
   }

   public Seq headers() {
      Seq blockHeaders = (Seq)(new .colon.colon("Block Name", new .colon.colon("Storage Level", new .colon.colon("Size in Memory", new .colon.colon("Size on Disk", new .colon.colon("Executors", scala.collection.immutable.Nil..MODULE$)))))).map((x) -> new Tuple3(x, BoxesRunTime.boxToBoolean(true), scala.None..MODULE$));
      this.isSortColumnValid(blockHeaders, this.sortColumn());
      return this.headerRow(blockHeaders, this.desc(), this.pageSize(), this.sortColumn(), this.basePath, this.rddTag, "block");
   }

   public Seq row(final BlockTableRowData block) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(block.blockName());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(block.storageLevel());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(block.memoryUsed()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(Utils$.MODULE$.bytesToString(block.diskUsed()));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(block.executors());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   public BlockPagedTable(final HttpServletRequest request, final String rddTag, final String basePath, final scala.collection.Seq rddPartitions, final Seq executorSummaries) {
      this.rddTag = rddTag;
      this.basePath = basePath;
      PagedTable.$init$(this);
      Tuple3 var7 = this.getTableParameters(request, rddTag, "Block Name");
      if (var7 != null) {
         String sortColumn = (String)var7._1();
         boolean desc = BoxesRunTime.unboxToBoolean(var7._2());
         int pageSize = BoxesRunTime.unboxToInt(var7._3());
         this.x$9 = new Tuple3(sortColumn, BoxesRunTime.boxToBoolean(desc), BoxesRunTime.boxToInteger(pageSize));
         this.sortColumn = (String)this.x$9._1();
         this.desc = BoxesRunTime.unboxToBoolean(this.x$9._2());
         this.pageSize = BoxesRunTime.unboxToInt(this.x$9._3());
         this.dataSource = new BlockDataSource(rddPartitions, this.pageSize(), this.sortColumn(), this.desc(), ((IterableOnceOps)executorSummaries.map((ex) -> new Tuple2(ex.id(), ex.hostPort()))).toMap(scala..less.colon.less..MODULE$.refl()));
      } else {
         throw new MatchError(var7);
      }
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
