package org.apache.spark.deploy.history;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.api.v1.ApplicationAttemptInfo;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import scala.MatchError;
import scala.Predef;
import scala.collection.StringOps;
import scala.collection.StringOps.;
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

@ScalaSignature(
   bytes = "\u0006\u0005!4Qa\u0002\u0005\u0001\u0011IA\u0001\"\u0007\u0001\u0003\u0002\u0003\u0006Ia\u0007\u0005\u0006?\u0001!\t\u0001\t\u0005\u0006G\u0001!\t\u0001\n\u0005\u0006\u000b\u0002!\tA\u0012\u0005\u0006\u001b\u0002!IA\u0014\u0005\u00065\u0002!Ia\u0017\u0002\f\u0011&\u001cHo\u001c:z!\u0006<WM\u0003\u0002\n\u0015\u00059\u0001.[:u_JL(BA\u0006\r\u0003\u0019!W\r\u001d7ps*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014xm\u0005\u0002\u0001'A\u0011AcF\u0007\u0002+)\u0011a\u0003D\u0001\u0003k&L!\u0001G\u000b\u0003\u0013]+'-V%QC\u001e,\u0017A\u00029be\u0016tGo\u0001\u0001\u0011\u0005qiR\"\u0001\u0005\n\u0005yA!!\u0004%jgR|'/_*feZ,'/\u0001\u0004=S:LGO\u0010\u000b\u0003C\t\u0002\"\u0001\b\u0001\t\u000be\u0011\u0001\u0019A\u000e\u0002\rI,g\u000eZ3s)\t)\u0013\bE\u0002'aMr!aJ\u0017\u000f\u0005!ZS\"A\u0015\u000b\u0005)R\u0012A\u0002\u001fs_>$h(C\u0001-\u0003\u0015\u00198-\u00197b\u0013\tqs&A\u0004qC\u000e\\\u0017mZ3\u000b\u00031J!!\r\u001a\u0003\u0007M+\u0017O\u0003\u0002/_A\u0011AgN\u0007\u0002k)\u0011agL\u0001\u0004q6d\u0017B\u0001\u001d6\u0005\u0011qu\u000eZ3\t\u000bi\u001a\u0001\u0019A\u001e\u0002\u000fI,\u0017/^3tiB\u0011AhQ\u0007\u0002{)\u0011ahP\u0001\u0005QR$\bO\u0003\u0002A\u0003\u000691/\u001a:wY\u0016$(\"\u0001\"\u0002\u000f)\f7.\u0019:uC&\u0011A)\u0010\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH/A\rtQ>,H\u000e\u001a#jgBd\u0017-_!qa2L7-\u0019;j_:\u001cHCA$L!\tA\u0015*D\u00010\u0013\tQuFA\u0004C_>dW-\u00198\t\u000b1#\u0001\u0019A$\u0002'I,\u0017/^3ti\u0016$\u0017J\\2p[BdW\r^3\u0002\u00195\f7.\u001a)bO\u0016d\u0015N\\6\u0015\u0007=;\u0006\f\u0005\u0002Q):\u0011\u0011K\u0015\t\u0003Q=J!aU\u0018\u0002\rA\u0013X\rZ3g\u0013\t)fK\u0001\u0004TiJLgn\u001a\u0006\u0003'>BQAO\u0003A\u0002mBQ!W\u0003A\u0002\u001d\u000bab\u001d5po&s7m\\7qY\u0016$X-\u0001\fjg\u0006\u0003\b\u000f\\5dCRLwN\\\"p[BdW\r^3e)\t9E\fC\u0003^\r\u0001\u0007a,A\u0004baBLeNZ8\u0011\u0005}3W\"\u00011\u000b\u0005\u0005\u0014\u0017A\u0001<2\u0015\t\u0019G-A\u0002ba&T!!\u001a\u0007\u0002\rM$\u0018\r^;t\u0013\t9\u0007MA\bBaBd\u0017nY1uS>t\u0017J\u001c4p\u0001"
)
public class HistoryPage extends WebUIPage {
   private final HistoryServer parent;

   public Seq render(final HttpServletRequest request) {
      boolean requestedIncomplete = .MODULE$.toBoolean$extension(scala.Predef..MODULE$.augmentString((String)scala.Option..MODULE$.apply(request.getParameter("showIncomplete")).getOrElse(() -> "false")));
      boolean displayApplications = this.shouldDisplayApplications(requestedIncomplete);
      int eventLogsUnderProcessCount = this.parent.getEventLogsUnderProcess();
      long lastUpdatedTime = this.parent.getLastUpdatedTime();
      Map providerConfig = this.parent.getProviderConfig();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var42 = new UnprefixedAttribute("class", new Text("container-fluid"), $md);
      Elem var10000 = new Elem;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var43 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(providerConfig.map((x0$1) -> {
         if (x0$1 != null) {
            String k = (String)x0$1._1();
            String v = (String)x0$1._2();
            Null var10004 = scala.xml.Null..MODULE$;
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            Null var10013 = scala.xml.Null..MODULE$;
            TopScope var10014 = scala.xml.TopScope..MODULE$;
            NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(k);
            $buf.$amp$plus(new Text(":"));
            $buf.$amp$plus(new Elem((String)null, "strong", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text(" "));
            $buf.$amp$plus(v);
            return new Elem((String)null, "li", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         } else {
            throw new MatchError(x0$1);
         }
      }));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "ul", var43, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      Object var10009;
      if (eventLogsUnderProcessCount > 0) {
         Null var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("There are "));
         $buf.$amp$plus(BoxesRunTime.boxToInteger(eventLogsUnderProcessCount));
         $buf.$amp$plus(new Text(" event log(s) currently being\n            processed which may result in additional applications getting listed on this page.\n            Refresh the page to view updates. "));
         var10009 = new Elem((String)null, "p", var10013, var10014, false, var10016.seqToNodeSeq($buf));
      } else {
         var10009 = scala.package..MODULE$.Seq().empty();
      }

      $buf.$amp$plus(var10009);
      $buf.$amp$plus(new Text("\n        "));
      if (lastUpdatedTime > 0L) {
         Null var71 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Last updated: "));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var44 = new UnprefixedAttribute("id", new Text("last-updated"), $md);
         TopScope var10023 = scala.xml.TopScope..MODULE$;
         NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(BoxesRunTime.boxToLong(lastUpdatedTime));
         $buf.$amp$plus(new Elem((String)null, "span", var44, var10023, false, var10025.seqToNodeSeq($buf)));
         var10009 = new Elem((String)null, "p", var71, var10014, false, var10016.seqToNodeSeq($buf));
      } else {
         var10009 = scala.package..MODULE$.Seq().empty();
      }

      $buf.$amp$plus(var10009);
      $buf.$amp$plus(new Text("\n        "));
      Null var72 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Client local time zone: "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var45 = new UnprefixedAttribute("id", new Text("time-zone"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var45, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Elem((String)null, "p", var72, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      var10000.<init>((String)null, "div", var42, var10005, false, var10007.seqToNodeSeq($buf));
      Elem summary = var10000;
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var46 = new UnprefixedAttribute("class", new Text("container-fluid"), $md);
      var10000 = new Elem;
      var10005 = scala.xml.TopScope..MODULE$;
      var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      StringOps var66 = .MODULE$;
      Predef var10010 = scala.Predef..MODULE$;
      String var10011 = UIUtils$.MODULE$.formatImportJavaScript(request, "/static/historypage.js", scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new String[]{"setAppLimit"})));
      String js = var66.stripMargin$extension(var10010.augmentString("\n               |" + var10011 + "\n               |\n               |setAppLimit(" + this.parent.maxApplications() + ");\n               |"));
      Object var70;
      if (displayApplications) {
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var47 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/dataTables.rowsGroup.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
         Elem var67 = new Elem((String)null, "script", var47, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var48 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/historypage.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
         var48 = new UnprefixedAttribute("type", new Text("module"), var48);
         NodeSeq var68 = var67.$plus$plus(new Elem((String)null, "script", var48, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var50 = new UnprefixedAttribute("type", new Text("module"), $md);
         TopScope var10015 = scala.xml.TopScope..MODULE$;
         NodeSeq var10017 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(js));
         var68 = var68.$plus$plus(new Elem((String)null, "script", var50, var10015, false, var10017.seqToNodeSeq($buf)));
         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var51 = new UnprefixedAttribute("id", new Text("history-summary"), $md);
         var70 = var68.$plus$plus(new Elem((String)null, "div", var51, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      } else if (requestedIncomplete) {
         var72 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("No incomplete applications found!"));
         var70 = new Elem((String)null, "h4", var72, var10014, false, var10016.seqToNodeSeq($buf));
      } else if (eventLogsUnderProcessCount > 0) {
         var72 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("No completed applications found!"));
         var70 = new Elem((String)null, "h4", var72, var10014, false, var10016.seqToNodeSeq($buf));
      } else {
         var72 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("No completed applications found!"));
         var70 = (new Elem((String)null, "h4", var72, var10014, false, var10016.seqToNodeSeq($buf))).$plus$plus(this.parent.emptyListingHtml());
      }

      $buf.$amp$plus(var70);
      $buf.$amp$plus(new Text("\n      "));
      var10000.<init>((String)null, "div", var46, var10005, false, var10007.seqToNodeSeq($buf));
      Elem appList = var10000;
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var52 = new UnprefixedAttribute("class", new Text("container-fluid"), $md);
      var10005 = scala.xml.TopScope..MODULE$;
      var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var53 = new UnprefixedAttribute("href", this.makePageLink(request, !requestedIncomplete), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(requestedIncomplete ? "Back to completed applications" : "Show incomplete applications");
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "a", var53, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      var72 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var54 = new UnprefixedAttribute("href", UIUtils$.MODULE$.prependBaseUri(request, "/logPage/?self&logType=out", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
      TopScope var93 = scala.xml.TopScope..MODULE$;
      NodeSeq var94 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          Show server log"));
      $buf.$amp$plus(new Elem((String)null, "a", var54, var93, false, var94.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "p", var72, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Elem pageLink = new Elem((String)null, "div", var52, var10005, false, var10007.seqToNodeSeq($buf));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var55 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/historypage-common.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
      var55 = new UnprefixedAttribute("type", new Text("module"), var55);
      var10000 = new Elem((String)null, "script", var55, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var57 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/utils.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
      var57 = new UnprefixedAttribute("type", new Text("module"), var57);
      NodeSeq content = var10000.$plus$plus(new Elem((String)null, "script", var57, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$)).$plus$plus(summary).$plus$plus(appList).$plus$plus(pageLink);
      return UIUtils$.MODULE$.basicSparkPage(request, () -> content, this.parent.title(), true);
   }

   public boolean shouldDisplayApplications(final boolean requestedIncomplete) {
      return this.parent.getApplicationList().exists((x$1) -> BoxesRunTime.boxToBoolean($anonfun$shouldDisplayApplications$1(this, requestedIncomplete, x$1)));
   }

   private String makePageLink(final HttpServletRequest request, final boolean showIncomplete) {
      return UIUtils$.MODULE$.prependBaseUri(request, "/?showIncomplete=" + showIncomplete, UIUtils$.MODULE$.prependBaseUri$default$3());
   }

   private boolean isApplicationCompleted(final ApplicationInfo appInfo) {
      return appInfo.attempts().nonEmpty() && ((ApplicationAttemptInfo)appInfo.attempts().head()).completed();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$shouldDisplayApplications$1(final HistoryPage $this, final boolean requestedIncomplete$1, final ApplicationInfo x$1) {
      return $this.isApplicationCompleted(x$1) != requestedIncomplete$1;
   }

   public HistoryPage(final HistoryServer parent) {
      super("");
      this.parent = parent;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
