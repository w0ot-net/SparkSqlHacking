package org.apache.spark.ui.exec;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ui.SparkUITab;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import scala.Function0;
import scala.Option;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005)3QAB\u0004\u0001\u0013EA\u0001B\u0006\u0001\u0003\u0002\u0003\u0006I\u0001\u0007\u0005\t7\u0001\u0011\t\u0011)A\u00059!A!\u0005\u0001B\u0001B\u0003%A\u0004C\u0003$\u0001\u0011\u0005A\u0005C\u0003+\u0001\u0011\u00051FA\u0007Fq\u0016\u001cW\u000f^8sgB\u000bw-\u001a\u0006\u0003\u0011%\tA!\u001a=fG*\u0011!bC\u0001\u0003k&T!\u0001D\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00059y\u0011AB1qC\u000eDWMC\u0001\u0011\u0003\ry'oZ\n\u0003\u0001I\u0001\"a\u0005\u000b\u000e\u0003%I!!F\u0005\u0003\u0013]+'-V%QC\u001e,\u0017A\u00029be\u0016tGo\u0001\u0001\u0011\u0005MI\u0012B\u0001\u000e\n\u0005)\u0019\u0006/\u0019:l+&#\u0016MY\u0001\u0012i\"\u0014X-\u00193Ek6\u0004XI\\1cY\u0016$\u0007CA\u000f!\u001b\u0005q\"\"A\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0005r\"a\u0002\"p_2,\u0017M\\\u0001\u0015Q\u0016\f\u0007\u000fS5ti><'/Y7F]\u0006\u0014G.\u001a3\u0002\rqJg.\u001b;?)\u0011)s\u0005K\u0015\u0011\u0005\u0019\u0002Q\"A\u0004\t\u000bY!\u0001\u0019\u0001\r\t\u000bm!\u0001\u0019\u0001\u000f\t\u000b\t\"\u0001\u0019\u0001\u000f\u0002\rI,g\u000eZ3s)\tac\bE\u0002.kar!AL\u001a\u000f\u0005=\u0012T\"\u0001\u0019\u000b\u0005E:\u0012A\u0002\u001fs_>$h(C\u0001 \u0013\t!d$A\u0004qC\u000e\\\u0017mZ3\n\u0005Y:$aA*fc*\u0011AG\b\t\u0003sqj\u0011A\u000f\u0006\u0003wy\t1\u0001_7m\u0013\ti$H\u0001\u0003O_\u0012,\u0007\"B \u0006\u0001\u0004\u0001\u0015a\u0002:fcV,7\u000f\u001e\t\u0003\u0003\"k\u0011A\u0011\u0006\u0003\u0007\u0012\u000bA\u0001\u001b;ua*\u0011QIR\u0001\bg\u0016\u0014h\u000f\\3u\u0015\u00059\u0015a\u00026bW\u0006\u0014H/Y\u0005\u0003\u0013\n\u0013!\u0003\u0013;uaN+'O\u001e7fiJ+\u0017/^3ti\u0002"
)
public class ExecutorsPage extends WebUIPage {
   private final SparkUITab parent;
   private final boolean threadDumpEnabled;
   private final boolean heapHistogramEnabled;

   public Seq render(final HttpServletRequest request) {
      String imported = UIUtils$.MODULE$.formatImportJavaScript(request, "/static/executorspage.js", .MODULE$.wrapRefArray((Object[])(new String[]{"setThreadDumpEnabled", "setHeapHistogramEnabled"})));
      String js = scala.collection.StringOps..MODULE$.stripMargin$extension(scala.Predef..MODULE$.augmentString("\n         |" + imported + "\n         |\n         |setThreadDumpEnabled(" + this.threadDumpEnabled + ");\n         |setHeapHistogramEnabled(" + this.heapHistogramEnabled + ")\n         |"));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var17 = new UnprefixedAttribute("id", new Text("active-executors"), $md);
      Elem var10000 = new Elem((String)null, "div", var17, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var18 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/utils.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
      var18 = new UnprefixedAttribute("type", new Text("module"), var18);
      NodeSeq var23 = var10000.$plus$plus(new Elem((String)null, "script", var18, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var20 = new UnprefixedAttribute("src", UIUtils$.MODULE$.prependBaseUri(request, "/static/executorspage.js", UIUtils$.MODULE$.prependBaseUri$default$3()), $md);
      var20 = new UnprefixedAttribute("type", new Text("module"), var20);
      var23 = var23.$plus$plus(new Elem((String)null, "script", var20, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var22 = new UnprefixedAttribute("type", new Text("module"), $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(scala.xml.Unparsed..MODULE$.apply(js));
      NodeSeq content = var23.$plus$plus(new Elem((String)null, "script", var22, var10006, false, var10008.seqToNodeSeq($buf)));
      String x$2 = "Executors";
      Function0 x$3 = () -> content;
      SparkUITab x$4 = this.parent;
      boolean x$5 = true;
      Option x$6 = UIUtils$.MODULE$.headerSparkPage$default$5();
      boolean x$7 = UIUtils$.MODULE$.headerSparkPage$default$6();
      return UIUtils$.MODULE$.headerSparkPage(request, "Executors", x$3, x$4, x$6, x$7, true);
   }

   public ExecutorsPage(final SparkUITab parent, final boolean threadDumpEnabled, final boolean heapHistogramEnabled) {
      super("");
      this.parent = parent;
      this.threadDumpEnabled = threadDumpEnabled;
      this.heapHistogramEnabled = heapHistogramEnabled;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
