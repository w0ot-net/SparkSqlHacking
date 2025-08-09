package org.apache.spark.ui;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.spark.util.Utils$;
import org.sparkproject.guava.base.Splitter;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.Iterable;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.MapOps;
import scala.collection.SeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.xml.Elem;
import scala.xml.EntityRef;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.Unparsed;
import scala.xml.UnprefixedAttribute;
import scala.xml.Null.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015d\u0001\u0003\n\u0014!\u0003\r\t!F\u000e\t\u000b\r\u0002A\u0011A\u0013\t\u000b%\u0002a\u0011\u0001\u0016\t\u000bY\u0002a\u0011\u0001\u0016\t\u000b]\u0002a\u0011\u0001\u0016\t\u000ba\u0002a\u0011\u0001\u0016\t\u000be\u0002a\u0011\u0001\u001e\t\u000b)\u0003a\u0011A&\t\u000bm\u0003a\u0011\u0001/\t\u000b}\u0003A\u0011\u00011\t\r\u0019\u0004A\u0011A\nh\u0011!y\u0007!%A\u0005\u0002M\u0001\b\"B>\u0001\r\u0003a\b\"\u0002@\u0001\r\u0003Q\u0003BB@\u0001\t\u0003\t\t\u0001C\u0004\u0002 \u0001!\t!!\t\t\u000f\u0005]\u0002\u0001\"\u0001\u0002:!9\u0011Q\n\u0001\u0005\u0002\u0005=#A\u0003)bO\u0016$G+\u00192mK*\u0011A#F\u0001\u0003k&T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u000b\u00039\u0005\u001b\"\u0001A\u000f\u0011\u0005y\tS\"A\u0010\u000b\u0003\u0001\nQa]2bY\u0006L!AI\u0010\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u0014\u0011\u0005y9\u0013B\u0001\u0015 \u0005\u0011)f.\u001b;\u0002\u000fQ\f'\r\\3JIV\t1\u0006\u0005\u0002-g9\u0011Q&\r\t\u0003]}i\u0011a\f\u0006\u0003a\u0011\na\u0001\u0010:p_Rt\u0014B\u0001\u001a \u0003\u0019\u0001&/\u001a3fM&\u0011A'\u000e\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005Iz\u0012!\u0004;bE2,7i]:DY\u0006\u001c8/A\tqC\u001e,7+\u001b>f\r>\u0014XNR5fY\u0012\f1\u0003]1hK:+XNY3s\r>\u0014XNR5fY\u0012\f!\u0002Z1uCN{WO]2f+\u0005Y\u0004c\u0001\u001f>\u007f5\t1#\u0003\u0002?'\ty\u0001+Y4fI\u0012\u000bG/Y*pkJ\u001cW\r\u0005\u0002A\u00032\u0001A!\u0002\"\u0001\u0005\u0004\u0019%!\u0001+\u0012\u0005\u0011;\u0005C\u0001\u0010F\u0013\t1uDA\u0004O_RD\u0017N\\4\u0011\u0005yA\u0015BA% \u0005\r\te._\u0001\bQ\u0016\fG-\u001a:t+\u0005a\u0005cA'S+:\u0011a\n\u0015\b\u0003]=K\u0011\u0001I\u0005\u0003#~\tq\u0001]1dW\u0006<W-\u0003\u0002T)\n\u00191+Z9\u000b\u0005E{\u0002C\u0001,Z\u001b\u00059&B\u0001- \u0003\rAX\u000e\\\u0005\u00035^\u0013AAT8eK\u0006\u0019!o\\<\u0015\u00051k\u0006\"\u00020\t\u0001\u0004y\u0014!\u0001;\u0002\u000bQ\f'\r\\3\u0015\u00051\u000b\u0007\"\u00022\n\u0001\u0004\u0019\u0017\u0001\u00029bO\u0016\u0004\"A\b3\n\u0005\u0015|\"aA%oi\u0006q\u0001/Y4f\u001d\u00064\u0018nZ1uS>tG#\u0002'iS.l\u0007\"\u00022\u000b\u0001\u0004\u0019\u0007\"\u00026\u000b\u0001\u0004\u0019\u0017\u0001\u00039bO\u0016\u001c\u0016N_3\t\u000b1T\u0001\u0019A2\u0002\u0015Q|G/\u00197QC\u001e,7\u000fC\u0004o\u0015A\u0005\t\u0019A\u0016\u0002\u00199\fg/[4bi&|g.\u00133\u00021A\fw-\u001a(bm&<\u0017\r^5p]\u0012\"WMZ1vYR$C'F\u0001rU\tY#oK\u0001t!\t!\u00180D\u0001v\u0015\t1x/A\u0005v]\u000eDWmY6fI*\u0011\u0001pH\u0001\u000bC:tw\u000e^1uS>t\u0017B\u0001>v\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\ta\u0006<W\rT5oWR\u00111& \u0005\u0006E2\u0001\raY\u0001\u0011O>\u0014U\u000f\u001e;p]\u001a{'/\u001c)bi\"\facZ3u!\u0006\u0014\u0018-\\3uKJ|E\u000f[3s)\u0006\u0014G.\u001a\u000b\u0006W\u0005\r\u00111\u0004\u0005\b\u0003\u000bq\u0001\u0019AA\u0004\u0003\u001d\u0011X-];fgR\u0004B!!\u0003\u0002\u00185\u0011\u00111\u0002\u0006\u0005\u0003\u001b\ty!\u0001\u0003iiR\u0004(\u0002BA\t\u0003'\tqa]3sm2,GO\u0003\u0002\u0002\u0016\u00059!.Y6beR\f\u0017\u0002BA\r\u0003\u0017\u0011!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\"1\u0011Q\u0004\bA\u0002-\n\u0001\u0002^1cY\u0016$\u0016mZ\u0001\u0013O\u0016$H+\u00192mKB\u000b'/Y7fi\u0016\u00148\u000f\u0006\u0005\u0002$\u0005=\u0012\u0011GA\u001a!\u001dq\u0012QE\u0016\u0002*\rL1!a\n \u0005\u0019!V\u000f\u001d7fgA\u0019a$a\u000b\n\u0007\u00055rDA\u0004C_>dW-\u00198\t\u000f\u0005\u0015q\u00021\u0001\u0002\b!1\u0011QD\bA\u0002-Ba!!\u000e\u0010\u0001\u0004Y\u0013!\u00053fM\u0006,H\u000e^*peR\u001cu\u000e\\;n]\u0006\t\u0012n]*peR\u001cu\u000e\\;n]Z\u000bG.\u001b3\u0015\u000b\u0019\nY$!\u0013\t\u000f\u0005u\u0002\u00031\u0001\u0002@\u0005Q\u0001.Z1eKJLeNZ8\u0011\t5\u0013\u0016\u0011\t\t\t=\u0005\u00152&!\u000b\u0002DA!a$!\u0012,\u0013\r\t9e\b\u0002\u0007\u001fB$\u0018n\u001c8\t\r\u0005-\u0003\u00031\u0001,\u0003)\u0019xN\u001d;D_2,XN\\\u0001\nQ\u0016\fG-\u001a:S_^$r\u0002TA)\u0003'\n9&!\u0017\u0002\\\u0005}\u0013\u0011\r\u0005\b\u0003{\t\u0002\u0019AA \u0011\u001d\t)&\u0005a\u0001\u0003S\tA\u0001Z3tG\")!.\u0005a\u0001G\"1\u00111J\tA\u0002-Ba!!\u0018\u0012\u0001\u0004Y\u0013!\u00049be\u0006lW\r^3s!\u0006$\b\u000e\u0003\u0004\u0002\u001eE\u0001\ra\u000b\u0005\u0007\u0003G\n\u0002\u0019A\u0016\u0002\u0011!,\u0017\rZ3s\u0013\u0012\u0004"
)
public interface PagedTable {
   String tableId();

   String tableCssClass();

   String pageSizeFormField();

   String pageNumberFormField();

   PagedDataSource dataSource();

   Seq headers();

   Seq row(final Object t);

   // $FF: synthetic method
   static Seq table$(final PagedTable $this, final int page) {
      return $this.table(page);
   }

   default Seq table(final int page) {
      PagedDataSource _dataSource = this.dataSource();

      Elem var10000;
      try {
         PageData var6 = _dataSource.pageData(page);
         if (var6 == null) {
            throw new MatchError(var6);
         }

         int totalPages = var6.totalPage();
         scala.collection.Seq data = var6.data();
         Tuple2 var5 = new Tuple2(BoxesRunTime.boxToInteger(totalPages), data);
         int totalPages = var5._1$mcI$sp();
         scala.collection.Seq data = (scala.collection.Seq)var5._2();
         int pageToShow = page <= 0 ? 1 : (page > totalPages ? totalPages : page);
         int pageSize = _dataSource.pageSize() <= 0 ? data.size() : _dataSource.pageSize();
         Seq pageNaviTop = this.pageNavigation(pageToShow, pageSize, totalPages, this.tableId() + "-top");
         Seq pageNaviBottom = this.pageNavigation(pageToShow, pageSize, totalPages, this.tableId() + "-bottom");
         Null var32 = .MODULE$;
         TopScope var33 = scala.xml.TopScope..MODULE$;
         NodeSeq var34 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(pageNaviTop);
         $buf.$amp$plus(new Text("\n        "));
         MetaData $md = .MODULE$;
         MetaData var29 = new UnprefixedAttribute("id", this.tableId(), $md);
         var29 = new UnprefixedAttribute("class", this.tableCssClass(), var29);
         TopScope var35 = scala.xml.TopScope..MODULE$;
         NodeSeq var36 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(this.headers());
         $buf.$amp$plus(new Text("\n          "));
         Null var38 = .MODULE$;
         TopScope var40 = scala.xml.TopScope..MODULE$;
         NodeSeq var42 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(data.map((t) -> this.row(t)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "tbody", var38, var40, false, var42.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "table", var29, var35, false, var36.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(pageNaviBottom);
         $buf.$amp$plus(new Text("\n      "));
         var10000 = new Elem((String)null, "div", var32, var33, false, var34.seqToNodeSeq($buf));
      } catch (IndexOutOfBoundsException var28) {
         PageData var21 = _dataSource.pageData(1);
         if (var21 == null) {
            throw new MatchError(var21);
         }

         int totalPages = var21.totalPage();
         Null var10004 = .MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(this.pageNavigation(1, _dataSource.pageSize(), totalPages, this.pageNavigation$default$4()));
         $buf.$amp$plus(new Text("\n          "));
         Null $md = .MODULE$;
         MetaData var31 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         Null var10022 = .MODULE$;
         TopScope var10023 = scala.xml.TopScope..MODULE$;
         NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Error while rendering table:"));
         $buf.$amp$plus(new Elem((String)null, "p", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         var10022 = .MODULE$;
         var10023 = scala.xml.TopScope..MODULE$;
         var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(Utils$.MODULE$.exceptionString(var28));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "pre", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "div", var31, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10000 = new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      }

      return var10000;
   }

   // $FF: synthetic method
   static Seq pageNavigation$(final PagedTable $this, final int page, final int pageSize, final int totalPages, final String navigationId) {
      return $this.pageNavigation(page, pageSize, totalPages, navigationId);
   }

   default Seq pageNavigation(final int page, final int pageSize, final int totalPages, final String navigationId) {
      int groupSize = 10;
      int firstGroup = 0;
      int lastGroup = (totalPages - 1) / groupSize;
      int currentGroup = (page - 1) / groupSize;
      int startPage = currentGroup * groupSize + 1;
      int endPage = scala.runtime.RichInt..MODULE$.min$extension(scala.Predef..MODULE$.intWrapper(totalPages), startPage + groupSize - 1);
      IndexedSeq pageTags = scala.runtime.RichInt..MODULE$.to$extension(scala.Predef..MODULE$.intWrapper(startPage), endPage).map((p) -> $anonfun$pageNavigation$1(this, page, BoxesRunTime.unboxToInt(p)));
      Iterable var10000;
      if (scala.collection.StringOps..MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(this.goButtonFormPath()), '?')) {
         String queryString = this.goButtonFormPath().split("\\?", 2)[1];
         String search = queryString.split("#")[0];
         var10000 = (Iterable)((MapOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(Splitter.on('&').trimResults().omitEmptyStrings().withKeyValueSeparator("=").split(search)).asScala().filter((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$pageNavigation$2(this, x0$1)))).filter((x0$2) -> BoxesRunTime.boxToBoolean($anonfun$pageNavigation$3(this, x0$2)))).map((x0$3) -> {
            if (x0$3 != null) {
               String k = (String)x0$3._1();
               String v = (String)x0$3._2();
               return new Tuple2(k, URLDecoder.decode(v, StandardCharsets.UTF_8.name()));
            } else {
               throw new MatchError(x0$3);
            }
         }).map((x0$4) -> {
            if (x0$4 != null) {
               String k = (String)x0$4._1();
               String v = (String)x0$4._2();
               MetaData $md = .MODULE$;
               MetaData var6 = new UnprefixedAttribute("value", v, $md);
               var6 = new UnprefixedAttribute("name", k, var6);
               var6 = new UnprefixedAttribute("type", new Text("hidden"), var6);
               return new Elem((String)null, "input", var6, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$);
            } else {
               throw new MatchError(x0$4);
            }
         });
      } else {
         var10000 = (Iterable)scala.package..MODULE$.Seq().empty();
      }

      Iterable hiddenFormFields = var10000;
      Elem var94 = new Elem;
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
      MetaData var55 = new UnprefixedAttribute("style", new Text("margin-bottom: 0px;"), $md);
      var55 = new UnprefixedAttribute("class", new Text("form-inline float-right justify-content-end"), var55);
      var55 = new UnprefixedAttribute("action", scala.xml.Unparsed..MODULE$.apply(this.goButtonFormPath()), var55);
      var55 = new UnprefixedAttribute("method", new Text("get"), var55);
      var55 = new UnprefixedAttribute("id", "form-" + navigationId + "-page", var55);
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(hiddenFormFields);
      $buf.$amp$plus(new Text("\n          "));
      Null var10031 = .MODULE$;
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(BoxesRunTime.boxToInteger(totalPages));
      $buf.$amp$plus(new Text(" Pages. Jump to"));
      $buf.$amp$plus(new Elem((String)null, "label", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = .MODULE$;
      MetaData var60 = new UnprefixedAttribute("class", new Text("col-1 form-control"), $md);
      var60 = new UnprefixedAttribute("value", Integer.toString(page), var60);
      var60 = new UnprefixedAttribute("id", "form-" + navigationId + "-page-no", var60);
      var60 = new UnprefixedAttribute("name", this.pageNumberFormField(), var60);
      var60 = new UnprefixedAttribute("type", new Text("text"), var60);
      $buf.$amp$plus(new Elem((String)null, "input", var60, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text(". Show "));
      $buf.$amp$plus(new Elem((String)null, "label", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      MetaData $md = .MODULE$;
      MetaData var65 = new UnprefixedAttribute("class", new Text("col-1 form-control"), $md);
      var65 = new UnprefixedAttribute("value", Integer.toString(pageSize), var65);
      var65 = new UnprefixedAttribute("name", this.pageSizeFormField(), var65);
      var65 = new UnprefixedAttribute("id", "form-" + navigationId + "-page-size", var65);
      var65 = new UnprefixedAttribute("type", new Text("text"), var65);
      $buf.$amp$plus(new Elem((String)null, "input", var65, scala.xml.TopScope..MODULE$, true, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n          "));
      var10031 = .MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("items in a page."));
      $buf.$amp$plus(new Elem((String)null, "label", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n\n          "));
      MetaData $md = .MODULE$;
      MetaData var70 = new UnprefixedAttribute("class", new Text("btn btn-spark"), $md);
      var70 = new UnprefixedAttribute("type", new Text("submit"), var70);
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Go"));
      $buf.$amp$plus(new Elem((String)null, "button", var70, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "form", var55, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "div", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Elem var10009 = new Elem;
      var10013 = .MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = .MODULE$;
      MetaData var72 = new UnprefixedAttribute("style", new Text("float: left; padding-top: 4px; padding-right: 4px;"), $md);
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Page: "));
      $buf.$amp$plus(new Elem((String)null, "span", var72, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = .MODULE$;
      MetaData var73 = new UnprefixedAttribute("class", new Text("pagination"), $md);
      Elem var10018 = new Elem;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Object var10027;
      if (currentGroup > firstGroup) {
         MetaData $md = .MODULE$;
         MetaData var74 = new UnprefixedAttribute("class", new Text("page-item"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = .MODULE$;
         MetaData var75 = new UnprefixedAttribute("aria-label", new Text("Previous Group"), $md);
         var75 = new UnprefixedAttribute("class", new Text("page-link"), var75);
         var75 = new UnprefixedAttribute("href", scala.xml.Unparsed..MODULE$.apply(this.pageLink(startPage - groupSize)), var75);
         TopScope var10041 = scala.xml.TopScope..MODULE$;
         NodeSeq var10043 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         MetaData $md = .MODULE$;
         MetaData var78 = new UnprefixedAttribute("aria-hidden", new Text("true"), $md);
         TopScope var10050 = scala.xml.TopScope..MODULE$;
         NodeSeq var10052 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         $buf.$amp$plus(new EntityRef("lt"));
         $buf.$amp$plus(new EntityRef("lt"));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new Elem((String)null, "span", var78, var10050, false, var10052.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "a", var75, var10041, false, var10043.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10027 = new Elem((String)null, "li", var74, var10032, false, var10034.seqToNodeSeq($buf));
      } else {
         var10027 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10027);
      $buf.$amp$plus(new Text("\n          "));
      if (page > 1) {
         MetaData $md = .MODULE$;
         MetaData var79 = new UnprefixedAttribute("class", new Text("page-item"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         MetaData $md = .MODULE$;
         MetaData var80 = new UnprefixedAttribute("aria-label", new Text("Previous"), $md);
         var80 = new UnprefixedAttribute("class", new Text("page-link"), var80);
         var80 = new UnprefixedAttribute("href", scala.xml.Unparsed..MODULE$.apply(this.pageLink(page - 1)), var80);
         TopScope var121 = scala.xml.TopScope..MODULE$;
         NodeSeq var124 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = .MODULE$;
         MetaData var83 = new UnprefixedAttribute("aria-hidden", new Text("true"), $md);
         TopScope var127 = scala.xml.TopScope..MODULE$;
         NodeSeq var130 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new EntityRef("lt"));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "span", var83, var127, false, var130.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(new Elem((String)null, "a", var80, var121, false, var124.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10027 = new Elem((String)null, "li", var79, var10032, false, var10034.seqToNodeSeq($buf));
      } else {
         var10027 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10027);
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(pageTags);
      $buf.$amp$plus(new Text("\n          "));
      if (page < totalPages) {
         MetaData $md = .MODULE$;
         MetaData var84 = new UnprefixedAttribute("class", new Text("page-item"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = .MODULE$;
         MetaData var85 = new UnprefixedAttribute("aria-label", new Text("Next"), $md);
         var85 = new UnprefixedAttribute("class", new Text("page-link"), var85);
         var85 = new UnprefixedAttribute("href", scala.xml.Unparsed..MODULE$.apply(this.pageLink(page + 1)), var85);
         TopScope var122 = scala.xml.TopScope..MODULE$;
         NodeSeq var125 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         MetaData $md = .MODULE$;
         MetaData var88 = new UnprefixedAttribute("aria-hidden", new Text("true"), $md);
         TopScope var128 = scala.xml.TopScope..MODULE$;
         NodeSeq var131 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new EntityRef("gt"));
         $buf.$amp$plus(new Elem((String)null, "span", var88, var128, false, var131.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "a", var85, var122, false, var125.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10027 = new Elem((String)null, "li", var84, var10032, false, var10034.seqToNodeSeq($buf));
      } else {
         var10027 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10027);
      $buf.$amp$plus(new Text("\n          "));
      if (currentGroup < lastGroup) {
         MetaData $md = .MODULE$;
         MetaData var89 = new UnprefixedAttribute("class", new Text("page-item"), $md);
         var10032 = scala.xml.TopScope..MODULE$;
         var10034 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n            "));
         MetaData $md = .MODULE$;
         MetaData var90 = new UnprefixedAttribute("aria-label", new Text("Next Group"), $md);
         var90 = new UnprefixedAttribute("class", new Text("page-link"), var90);
         var90 = new UnprefixedAttribute("href", scala.xml.Unparsed..MODULE$.apply(this.pageLink(startPage + groupSize)), var90);
         TopScope var123 = scala.xml.TopScope..MODULE$;
         NodeSeq var126 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n              "));
         MetaData $md = .MODULE$;
         MetaData var93 = new UnprefixedAttribute("aria-hidden", new Text("true"), $md);
         TopScope var129 = scala.xml.TopScope..MODULE$;
         NodeSeq var132 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n                "));
         $buf.$amp$plus(new EntityRef("gt"));
         $buf.$amp$plus(new EntityRef("gt"));
         $buf.$amp$plus(new Text("\n              "));
         $buf.$amp$plus(new Elem((String)null, "span", var93, var129, false, var132.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n            "));
         $buf.$amp$plus(new Elem((String)null, "a", var90, var123, false, var126.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n          "));
         var10027 = new Elem((String)null, "li", var89, var10032, false, var10034.seqToNodeSeq($buf));
      } else {
         var10027 = BoxedUnit.UNIT;
      }

      $buf.$amp$plus(var10027);
      $buf.$amp$plus(new Text("\n        "));
      var10018.<init>((String)null, "ul", var73, var10023, false, var10025.seqToNodeSeq($buf));
      $buf.$amp$plus(var10018);
      $buf.$amp$plus(new Text("\n      "));
      var10009.<init>((String)null, "div", var10013, var10014, false, var10016.seqToNodeSeq($buf));
      $buf.$amp$plus(var10009);
      $buf.$amp$plus(new Text("\n    "));
      var94.<init>((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      return var94;
   }

   // $FF: synthetic method
   static String pageNavigation$default$4$(final PagedTable $this) {
      return $this.pageNavigation$default$4();
   }

   default String pageNavigation$default$4() {
      return this.tableId();
   }

   String pageLink(final int page);

   String goButtonFormPath();

   // $FF: synthetic method
   static String getParameterOtherTable$(final PagedTable $this, final HttpServletRequest request, final String tableTag) {
      return $this.getParameterOtherTable(request, tableTag);
   }

   default String getParameterOtherTable(final HttpServletRequest request, final String tableTag) {
      return ((IterableOnceOps)((IterableOps)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(request.getParameterMap()).asScala().filterNot((x$3) -> BoxesRunTime.boxToBoolean($anonfun$getParameterOtherTable$1(tableTag, x$3)))).map((parameter) -> {
         String var10000 = (String)parameter._1();
         return var10000 + "=" + ((String[])parameter._2())[0];
      })).mkString("&");
   }

   // $FF: synthetic method
   static Tuple3 getTableParameters$(final PagedTable $this, final HttpServletRequest request, final String tableTag, final String defaultSortColumn) {
      return $this.getTableParameters(request, tableTag, defaultSortColumn);
   }

   default Tuple3 getTableParameters(final HttpServletRequest request, final String tableTag, final String defaultSortColumn) {
      String parameterSortColumn = request.getParameter(tableTag + ".sort");
      String parameterSortDesc = request.getParameter(tableTag + ".desc");
      String parameterPageSize = request.getParameter(tableTag + ".pageSize");
      String sortColumn = (String)scala.Option..MODULE$.apply(parameterSortColumn).map((sortColumnx) -> UIUtils$.MODULE$.decodeURLParameter(sortColumnx)).getOrElse(() -> defaultSortColumn);
      boolean desc = BoxesRunTime.unboxToBoolean(scala.Option..MODULE$.apply(parameterSortDesc).map((x$4) -> BoxesRunTime.boxToBoolean($anonfun$getTableParameters$3(x$4))).getOrElse((JFunction0.mcZ.sp)() -> {
         boolean var10000;
         label23: {
            if (sortColumn == null) {
               if (defaultSortColumn == null) {
                  break label23;
               }
            } else if (sortColumn.equals(defaultSortColumn)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }));
      int pageSize = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(parameterPageSize).map((x$5) -> BoxesRunTime.boxToInteger($anonfun$getTableParameters$5(x$5))).getOrElse((JFunction0.mcI.sp)() -> 100));
      return new Tuple3(sortColumn, BoxesRunTime.boxToBoolean(desc), BoxesRunTime.boxToInteger(pageSize));
   }

   // $FF: synthetic method
   static void isSortColumnValid$(final PagedTable $this, final Seq headerInfo, final String sortColumn) {
      $this.isSortColumnValid(headerInfo, sortColumn);
   }

   default void isSortColumnValid(final Seq headerInfo, final String sortColumn) {
      if (!((SeqOps)((IterableOps)headerInfo.filter((x$6) -> BoxesRunTime.boxToBoolean($anonfun$isSortColumnValid$1(x$6)))).map((x$7) -> (String)x$7._1())).contains(sortColumn)) {
         throw new IllegalArgumentException("Unknown column: " + sortColumn);
      }
   }

   // $FF: synthetic method
   static Seq headerRow$(final PagedTable $this, final Seq headerInfo, final boolean desc, final int pageSize, final String sortColumn, final String parameterPath, final String tableTag, final String headerId) {
      return $this.headerRow(headerInfo, desc, pageSize, sortColumn, parameterPath, tableTag, headerId);
   }

   default Seq headerRow(final Seq headerInfo, final boolean desc, final int pageSize, final String sortColumn, final String parameterPath, final String tableTag, final String headerId) {
      Seq row = (Seq)headerInfo.map((x0$1) -> {
         if (x0$1 == null) {
            throw new MatchError(x0$1);
         } else {
            String header;
            Option tooltip;
            label37: {
               header = (String)x0$1._1();
               boolean sortable = BoxesRunTime.unboxToBoolean(x0$1._2());
               tooltip = (Option)x0$1._3();
               if (header == null) {
                  if (sortColumn == null) {
                     break label37;
                  }
               } else if (header.equals(sortColumn)) {
                  break label37;
               }

               if (sortable) {
                  Unparsed headerLink = scala.xml.Unparsed..MODULE$.apply(parameterPath + "&" + tableTag + ".sort=" + URLEncoder.encode(header, StandardCharsets.UTF_8.name()) + "&" + tableTag + ".pageSize=" + pageSize + "#" + headerId);
                  Null var40 = .MODULE$;
                  TopScope var42 = scala.xml.TopScope..MODULE$;
                  NodeSeq var44 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(new Text("\n              "));
                  MetaData $md = .MODULE$;
                  MetaData var33 = new UnprefixedAttribute("href", headerLink, $md);
                  TopScope var46 = scala.xml.TopScope..MODULE$;
                  NodeSeq var48 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(new Text("\n                "));
                  MetaData $md = .MODULE$;
                  MetaData var34 = new UnprefixedAttribute("title", (String)tooltip.getOrElse(() -> ""), $md);
                  var34 = new UnprefixedAttribute("data-placement", new Text("top"), var34);
                  var34 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var34);
                  TopScope var10023 = scala.xml.TopScope..MODULE$;
                  NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
                  NodeBuffer $buf = new NodeBuffer();
                  $buf.$amp$plus(new Text("\n                  "));
                  $buf.$amp$plus(header);
                  $buf.$amp$plus(new Text("\n                "));
                  $buf.$amp$plus(new Elem((String)null, "span", var34, var10023, false, var10025.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n              "));
                  $buf.$amp$plus(new Elem((String)null, "a", var33, var46, false, var48.seqToNodeSeq($buf)));
                  $buf.$amp$plus(new Text("\n            "));
                  return new Elem((String)null, "th", var40, var42, false, var44.seqToNodeSeq($buf));
               }

               Null var10004 = .MODULE$;
               TopScope var10005 = scala.xml.TopScope..MODULE$;
               NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               $buf.$amp$plus(new Text("\n              "));
               MetaData $md = .MODULE$;
               MetaData var37 = new UnprefixedAttribute("title", (String)tooltip.getOrElse(() -> ""), $md);
               var37 = new UnprefixedAttribute("data-placement", new Text("top"), var37);
               var37 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var37);
               TopScope var10014 = scala.xml.TopScope..MODULE$;
               NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
               NodeBuffer $buf = new NodeBuffer();
               $buf.$amp$plus(new Text("\n                "));
               $buf.$amp$plus(header);
               $buf.$amp$plus(new Text("\n              "));
               $buf.$amp$plus(new Elem((String)null, "span", var37, var10014, false, var10016.seqToNodeSeq($buf)));
               $buf.$amp$plus(new Text("\n            "));
               return new Elem((String)null, "th", var10004, var10005, false, var10007.seqToNodeSeq($buf));
            }

            Unparsed headerLink = scala.xml.Unparsed..MODULE$.apply(parameterPath + "&" + tableTag + ".sort=" + URLEncoder.encode(header, StandardCharsets.UTF_8.name()) + "&" + tableTag + ".desc=" + !desc + "&" + tableTag + ".pageSize=" + pageSize + "#" + headerId);
            String arrow = desc ? "&#x25BE;" : "&#x25B4;";
            Null var41 = .MODULE$;
            TopScope var43 = scala.xml.TopScope..MODULE$;
            NodeSeq var45 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = .MODULE$;
            MetaData var29 = new UnprefixedAttribute("href", headerLink, $md);
            TopScope var47 = scala.xml.TopScope..MODULE$;
            NodeSeq var49 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n              "));
            MetaData $md = .MODULE$;
            MetaData var30 = new UnprefixedAttribute("title", (String)tooltip.getOrElse(() -> ""), $md);
            var30 = new UnprefixedAttribute("data-placement", new Text("top"), var30);
            var30 = new UnprefixedAttribute("data-toggle", new Text("tooltip"), var30);
            TopScope var50 = scala.xml.TopScope..MODULE$;
            NodeSeq var51 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n                "));
            $buf.$amp$plus(header);
            $buf.$amp$plus(new EntityRef("nbsp"));
            $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(arrow));
            $buf.$amp$plus(new Text("\n              "));
            $buf.$amp$plus(new Elem((String)null, "span", var30, var50, false, var51.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n            "));
            $buf.$amp$plus(new Elem((String)null, "a", var29, var47, false, var49.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            return new Elem((String)null, "th", var41, var43, false, var45.seqToNodeSeq($buf));
         }
      });
      Null var10004 = .MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = .MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(row);
      $buf.$amp$plus(new Elem((String)null, "tr", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      return new Elem((String)null, "thead", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   // $FF: synthetic method
   static Elem $anonfun$pageNavigation$1(final PagedTable $this, final int page$1, final int p) {
      if (p == page$1) {
         MetaData $md = .MODULE$;
         MetaData var11 = new UnprefixedAttribute("class", new Text("page-item disabled"), $md);
         TopScope var17 = scala.xml.TopScope..MODULE$;
         NodeSeq var18 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         MetaData $md = .MODULE$;
         MetaData var12 = new UnprefixedAttribute("class", new Text("page-link"), $md);
         var12 = new UnprefixedAttribute("href", new Text("#"), var12);
         TopScope var19 = scala.xml.TopScope..MODULE$;
         NodeSeq var20 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(BoxesRunTime.boxToInteger(p));
         $buf.$amp$plus(new Elem((String)null, "a", var12, var19, false, var20.seqToNodeSeq($buf)));
         return new Elem((String)null, "li", var11, var17, false, var18.seqToNodeSeq($buf));
      } else {
         MetaData $md = .MODULE$;
         MetaData var14 = new UnprefixedAttribute("class", new Text("page-item"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         MetaData $md = .MODULE$;
         MetaData var15 = new UnprefixedAttribute("class", new Text("page-link"), $md);
         var15 = new UnprefixedAttribute("href", scala.xml.Unparsed..MODULE$.apply($this.pageLink(p)), var15);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(BoxesRunTime.boxToInteger(p));
         $buf.$amp$plus(new Elem((String)null, "a", var15, var10014, false, var10016.seqToNodeSeq($buf)));
         return new Elem((String)null, "li", var14, var10005, false, var10007.seqToNodeSeq($buf));
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$pageNavigation$2(final PagedTable $this, final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var10000;
         label30: {
            String k = (String)x0$1._1();
            String var5 = $this.pageSizeFormField();
            if (k == null) {
               if (var5 != null) {
                  break label30;
               }
            } else if (!k.equals(var5)) {
               break label30;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$pageNavigation$3(final PagedTable $this, final Tuple2 x0$2) {
      if (x0$2 == null) {
         throw new MatchError(x0$2);
      } else {
         boolean var10000;
         label30: {
            String k = (String)x0$2._1();
            String var5 = $this.pageNumberFormField();
            if (k == null) {
               if (var5 != null) {
                  break label30;
               }
            } else if (!k.equals(var5)) {
               break label30;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$getParameterOtherTable$1(final String tableTag$1, final Tuple2 x$3) {
      return ((String)x$3._1()).startsWith(tableTag$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$getTableParameters$3(final String x$4) {
      return scala.collection.StringOps..MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString(x$4));
   }

   // $FF: synthetic method
   static int $anonfun$getTableParameters$5(final String x$5) {
      return scala.collection.StringOps..MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$5));
   }

   // $FF: synthetic method
   static boolean $anonfun$isSortColumnValid$1(final Tuple3 x$6) {
      return BoxesRunTime.unboxToBoolean(x$6._2());
   }

   static void $init$(final PagedTable $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
