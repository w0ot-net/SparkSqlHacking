package org.apache.spark.ui;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime.;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rc!\u0002\f\u0018\u0001ey\u0002\u0002\u0003\u0014\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u0011M\u0002!\u0011!Q\u0001\n!B\u0001\u0002\u000e\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\t\u000f\u0002\u0011\t\u0011)A\u0005\u0003\"A\u0001\n\u0001B\u0001B\u0003%\u0011\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003E\u0011!Q\u0005A!A!\u0002\u0013!\u0005\u0002C&\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0015\t\u00111\u0003!\u0011!Q\u0001\n5CQ\u0001\u0015\u0001\u0005\u0002EC\u0011\"\u0018\u0001A\u0002\u0003\u0007I\u0011\u00020\t\u0013}\u0003\u0001\u0019!a\u0001\n\u0013\u0001\u0007\"\u00034\u0001\u0001\u0004\u0005\t\u0015)\u0003)\u0011\u00159\u0007\u0001\"\u0001i\u0011\u0015q\u0007\u0001\"\u0001p\u0011\u0015A\b\u0001\"\u0001z\u0011\u0015Y\b\u0001\"\u0001}\u000f)\t\u0019cFA\u0001\u0012\u0003I\u0012Q\u0005\u0004\n-]\t\t\u0011#\u0001\u001a\u0003OAa\u0001U\n\u0005\u0002\u0005%\u0002\"CA\u0016'E\u0005I\u0011AA\u0017\u0005-9%/\u00199i+&#\u0015\r^1\u000b\u0005aI\u0012AA;j\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<7C\u0001\u0001!!\t\tC%D\u0001#\u0015\u0005\u0019\u0013!B:dC2\f\u0017BA\u0013#\u0005\u0019\te.\u001f*fM\u0006iA/[7fY&tW\rR5w\u0013\u0012\u001c\u0001\u0001\u0005\u0002*a9\u0011!F\f\t\u0003W\tj\u0011\u0001\f\u0006\u0003[\u001d\na\u0001\u0010:p_Rt\u0014BA\u0018#\u0003\u0019\u0001&/\u001a3fM&\u0011\u0011G\r\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005=\u0012\u0013A\u00045jgR|wM]1n\t&4\u0018\nZ\u0001\u0005I\u0006$\u0018\rE\u00027wyr!aN\u001d\u000f\u0005-B\u0014\"A\u0012\n\u0005i\u0012\u0013a\u00029bG.\fw-Z\u0005\u0003yu\u00121aU3r\u0015\tQ$\u0005\u0005\u0003\"\u007f\u0005#\u0015B\u0001!#\u0005\u0019!V\u000f\u001d7feA\u0011\u0011EQ\u0005\u0003\u0007\n\u0012A\u0001T8oOB\u0011\u0011%R\u0005\u0003\r\n\u0012a\u0001R8vE2,\u0017\u0001B7j]b\u000bA!\\1y1\u0006!Q.\u001b8Z\u0003\u0011i\u0017\r_-\u0002\u000bUt\u0017\u000e^-\u0002\u001b\t\fGo\u00195J]R,'O^1m!\r\tc\nR\u0005\u0003\u001f\n\u0012aa\u00149uS>t\u0017A\u0002\u001fj]&$h\b\u0006\u0006S)V3v\u000bW-[7r\u0003\"a\u0015\u0001\u000e\u0003]AQA\n\u0006A\u0002!BQa\r\u0006A\u0002!BQ\u0001\u000e\u0006A\u0002UBQa\u0012\u0006A\u0002\u0005CQ\u0001\u0013\u0006A\u0002\u0005CQ!\u0013\u0006A\u0002\u0011CQA\u0013\u0006A\u0002\u0011CQa\u0013\u0006A\u0002!Bq\u0001\u0014\u0006\u0011\u0002\u0003\u0007Q*\u0001\neCR\f'*\u0019<b'\u000e\u0014\u0018\u000e\u001d;OC6,W#\u0001\u0015\u0002-\u0011\fG/\u0019&bm\u0006\u001c6M]5qi:\u000bW.Z0%KF$\"!\u00193\u0011\u0005\u0005\u0012\u0017BA2#\u0005\u0011)f.\u001b;\t\u000f\u0015d\u0011\u0011!a\u0001Q\u0005\u0019\u0001\u0010J\u0019\u0002'\u0011\fG/\u0019&bm\u0006\u001c6M]5qi:\u000bW.\u001a\u0011\u0002\u001d\u001d,g.\u001a:bi\u0016$\u0015\r^1KgR\u0011\u0011-\u001b\u0005\u0006U:\u0001\ra[\u0001\fUN\u001cu\u000e\u001c7fGR|'\u000f\u0005\u0002TY&\u0011Qn\u0006\u0002\f\u0015N\u001cu\u000e\u001c7fGR|'/\u0001\u000bhK:,'/\u0019;f)&lW\r\\5oK\"#X\u000e\u001c\u000b\u0003a^\u00042AN\u001er!\t\u0011X/D\u0001t\u0015\t!(%A\u0002y[2L!A^:\u0003\t9{G-\u001a\u0005\u0006U>\u0001\ra[\u0001\u0016O\u0016tWM]1uK\"K7\u000f^8he\u0006l\u0007\n^7m)\t\u0001(\u0010C\u0003k!\u0001\u00071.A\u000fhK:,'/\u0019;f\u0003J,\u0017m\u0015;bG.DE/\u001c7XSRDG)\u0019;b)\r\u0001XP \u0005\u0006UF\u0001\ra\u001b\u0005\u0007\u007fF\u0001\r!!\u0001\u0002\rY\fG.^3t!\u0015\t\u00131AA\u0004\u0013\r\t)A\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0006C}\n\u0015\u0011\u0002\t\b\u0003\u0017\t)\u0002KA\r\u001b\t\tiA\u0003\u0003\u0002\u0010\u0005E\u0011\u0001B;uS2T!!a\u0005\u0002\t)\fg/Y\u0005\u0005\u0003/\tiAA\u0002NCB\u0004B!a\u0007\u0002\"5\u0011\u0011Q\u0004\u0006\u0005\u0003?\t\t\"\u0001\u0003mC:<\u0017bA\"\u0002\u001e\u0005YqI]1qQVKE)\u0019;b!\t\u00196c\u0005\u0002\u0014AQ\u0011\u0011QE\u0001\u001cI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u001d\u0016\u0005\u0005=\"fA'\u00022-\u0012\u00111\u0007\t\u0005\u0003k\ty$\u0004\u0002\u00028)!\u0011\u0011HA\u001e\u0003%)hn\u00195fG.,GMC\u0002\u0002>\t\n!\"\u00198o_R\fG/[8o\u0013\u0011\t\t%a\u000e\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class GraphUIData {
   private final String timelineDivId;
   private final String histogramDivId;
   private final Seq data;
   private final long minX;
   private final long maxX;
   private final double minY;
   private final double maxY;
   private final String unitY;
   private final Option batchInterval;
   private String dataJavaScriptName;

   public static Option $lessinit$greater$default$9() {
      return GraphUIData$.MODULE$.$lessinit$greater$default$9();
   }

   private String dataJavaScriptName() {
      return this.dataJavaScriptName;
   }

   private void dataJavaScriptName_$eq(final String x$1) {
      this.dataJavaScriptName = x$1;
   }

   public void generateDataJs(final JsCollector jsCollector) {
      String jsForData = ((IterableOnceOps)this.data.map((x0$1) -> {
         if (x0$1 != null) {
            long x = x0$1._1$mcJ$sp();
            double y = x0$1._2$mcD$sp();
            return "{\"x\": " + x + ", \"y\": " + y + "}";
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("[", ",", "]");
      this.dataJavaScriptName_$eq(jsCollector.nextVariableName());
      String var10001 = this.dataJavaScriptName();
      jsCollector.addPreparedStatement("var " + var10001 + " = " + jsForData + ";");
   }

   public Seq generateTimelineHtml(final JsCollector jsCollector) {
      jsCollector.addImports("/static/streaming-page.js", .MODULE$.wrapRefArray((Object[])(new String[]{"registerTimeline"})));
      jsCollector.addPreparedStatement("registerTimeline(" + this.minY + ", " + this.maxY + ");");
      jsCollector.addImports("/static/streaming-page.js", .MODULE$.wrapRefArray((Object[])(new String[]{"drawTimeline"})));
      if (this.batchInterval.isDefined()) {
         String var10001 = this.timelineDivId;
         jsCollector.addStatement("drawTimeline('#" + var10001 + "', " + this.dataJavaScriptName() + ", " + this.minX + ", " + this.maxX + ", " + this.minY + ", " + this.maxY + ", '" + this.unitY + "', " + this.batchInterval.get() + ");");
      } else {
         String var4 = this.timelineDivId;
         jsCollector.addStatement("drawTimeline('#" + var4 + "', " + this.dataJavaScriptName() + ", " + this.minX + ", " + this.maxX + ", " + this.minY + ", " + this.maxY + ", '" + this.unitY + "');");
      }

      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var3 = new UnprefixedAttribute("id", this.timelineDivId, $md);
      return new Elem((String)null, "div", var3, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
   }

   public Seq generateHistogramHtml(final JsCollector jsCollector) {
      String histogramData = this.dataJavaScriptName() + ".map(function(d) { return d.y; })";
      jsCollector.addImports("/static/streaming-page.js", .MODULE$.wrapRefArray((Object[])(new String[]{"registerHistogram"})));
      jsCollector.addPreparedStatement("registerHistogram(" + histogramData + ", " + this.minY + ", " + this.maxY + ");");
      jsCollector.addImports("/static/streaming-page.js", .MODULE$.wrapRefArray((Object[])(new String[]{"drawHistogram"})));
      if (this.batchInterval.isDefined()) {
         jsCollector.addStatement("drawHistogram('#" + this.histogramDivId + "', " + histogramData + ", " + this.minY + ", " + this.maxY + ", '" + this.unitY + "', " + this.batchInterval.get() + ");");
      } else {
         jsCollector.addStatement("drawHistogram('#" + this.histogramDivId + "', " + histogramData + ", " + this.minY + ", " + this.maxY + ", '" + this.unitY + "');");
      }

      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var4 = new UnprefixedAttribute("id", this.histogramDivId, $md);
      return new Elem((String)null, "div", var4, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
   }

   public Seq generateAreaStackHtmlWithData(final JsCollector jsCollector, final Tuple2[] values) {
      Set operationLabels = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.flatMap$extension(scala.Predef..MODULE$.refArrayOps((Object[])values), (x$2) -> scala.jdk.CollectionConverters..MODULE$.SetHasAsScala(((Map)x$2._2()).keySet()).asScala(), scala.reflect.ClassTag..MODULE$.apply(String.class))).toSet();
      Tuple2[] durationDataPadding = UIUtils$.MODULE$.durationDataPadding(values);
      String jsForData = scala.Predef..MODULE$.wrapRefArray(scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])durationDataPadding), (x0$1) -> {
         if (x0$1 != null) {
            long x = x0$1._1$mcJ$sp();
            scala.collection.immutable.Map y = (scala.collection.immutable.Map)x0$1._2();
            String s = ((IterableOnceOps)((IterableOps)y.toSeq().sortBy((x$3) -> (String)x$3._1(), scala.math.Ordering.String..MODULE$)).map((e) -> {
               Object var10000 = e._1();
               return "\"" + var10000 + "\": \"" + e._2$mcD$sp() + "\"";
            })).mkString(",");
            String var10000 = UIUtils$.MODULE$.formatBatchTime(x, 1L, false, UIUtils$.MODULE$.formatBatchTime$default$4());
            return "{x: \"" + var10000 + "\", " + s + "}";
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(String.class))).mkString("[", ",", "]");
      String jsForLabels = ((IterableOnceOps)operationLabels.toSeq().sorted(scala.math.Ordering.String..MODULE$)).mkString("[\"", "\",\"", "\"]");
      this.dataJavaScriptName_$eq(jsCollector.nextVariableName());
      String var10001 = this.dataJavaScriptName();
      jsCollector.addPreparedStatement("var " + var10001 + " = " + jsForData + ";");
      String labels = jsCollector.nextVariableName();
      jsCollector.addPreparedStatement("var " + labels + " = " + jsForLabels + ";");
      jsCollector.addImports("/static/structured-streaming-page.js", .MODULE$.wrapRefArray((Object[])(new String[]{"drawAreaStack"})));
      jsCollector.addStatement("drawAreaStack('#" + this.timelineDivId + "', " + labels + ", " + this.dataJavaScriptName() + ")");
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var9 = new UnprefixedAttribute("id", this.timelineDivId, $md);
      return new Elem((String)null, "div", var9, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$);
   }

   public GraphUIData(final String timelineDivId, final String histogramDivId, final Seq data, final long minX, final long maxX, final double minY, final double maxY, final String unitY, final Option batchInterval) {
      this.timelineDivId = timelineDivId;
      this.histogramDivId = histogramDivId;
      this.data = data;
      this.minX = minX;
      this.maxX = maxX;
      this.minY = minY;
      this.maxY = maxY;
      this.unitY = unitY;
      this.batchInterval = batchInterval;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
