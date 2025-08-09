package org.apache.spark.ui.env;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkConf;
import org.apache.spark.resource.ExecutorResourceRequest;
import org.apache.spark.resource.TaskResourceRequest;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.status.api.v1.ApplicationEnvironmentInfo;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.SeqOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.StringBuilder;
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
   bytes = "\u0006\u0005\u0005Ua!B\b\u0011\u0001IQ\u0002\u0002C\u0010\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u0011\u0015\u0002!\u0011!Q\u0001\n\u0019B\u0001B\u000b\u0001\u0003\u0002\u0003\u0006Ia\u000b\u0005\u0006c\u0001!\tA\r\u0005\u0006o\u0001!\t\u0001\u000f\u0005\u00063\u0002!IA\u0017\u0005\u0006U\u0002!IA\u0017\u0005\u0006W\u0002!IA\u0017\u0005\u0006Y\u0002!IA\u0017\u0005\u0006[\u0002!IA\u0017\u0005\u0006]\u0002!Ia\u001c\u0005\b\u0003\u0003\u0001A\u0011BA\u0002\u0011\u001d\t9\u0001\u0001C\u0005\u0003\u0013Aq!!\u0004\u0001\t\u0013\tyAA\bF]ZL'o\u001c8nK:$\b+Y4f\u0015\t\t\"#A\u0002f]ZT!a\u0005\u000b\u0002\u0005UL'BA\u000b\u0017\u0003\u0015\u0019\b/\u0019:l\u0015\t9\u0002$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00023\u0005\u0019qN]4\u0014\u0005\u0001Y\u0002C\u0001\u000f\u001e\u001b\u0005\u0011\u0012B\u0001\u0010\u0013\u0005%9VMY+J!\u0006<W-\u0001\u0004qCJ,g\u000e^\u0002\u0001!\t\u00113%D\u0001\u0011\u0013\t!\u0003C\u0001\bF]ZL'o\u001c8nK:$H+\u00192\u0002\t\r|gN\u001a\t\u0003O!j\u0011\u0001F\u0005\u0003SQ\u0011\u0011b\u00159be.\u001cuN\u001c4\u0002\u000bM$xN]3\u0011\u00051zS\"A\u0017\u000b\u00059\"\u0012AB:uCR,8/\u0003\u00021[\tq\u0011\t\u001d9Ti\u0006$Xo]*u_J,\u0017A\u0002\u001fj]&$h\b\u0006\u00034iU2\u0004C\u0001\u0012\u0001\u0011\u0015yB\u00011\u0001\"\u0011\u0015)C\u00011\u0001'\u0011\u0015QC\u00011\u0001,\u0003\u0019\u0011XM\u001c3feR\u0011\u0011(\u0014\t\u0004u\u0011;eBA\u001eB\u001d\tat(D\u0001>\u0015\tq\u0004%\u0001\u0004=e>|GOP\u0005\u0002\u0001\u0006)1oY1mC&\u0011!iQ\u0001\ba\u0006\u001c7.Y4f\u0015\u0005\u0001\u0015BA#G\u0005\r\u0019V-\u001d\u0006\u0003\u0005\u000e\u0003\"\u0001S&\u000e\u0003%S!AS\"\u0002\u0007alG.\u0003\u0002M\u0013\n!aj\u001c3f\u0011\u0015qU\u00011\u0001P\u0003\u001d\u0011X-];fgR\u0004\"\u0001U,\u000e\u0003ES!AU*\u0002\t!$H\u000f\u001d\u0006\u0003)V\u000bqa]3sm2,GOC\u0001W\u0003\u001dQ\u0017m[1si\u0006L!\u0001W)\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f^\u0001\u0016e\u0016\u001cx.\u001e:dKB\u0013xNZ5mK\"+\u0017\rZ3s+\u0005Y\u0006c\u0001/bE6\tQL\u0003\u0002_?\u0006I\u0011.\\7vi\u0006\u0014G.\u001a\u0006\u0003A\u000e\u000b!bY8mY\u0016\u001cG/[8o\u0013\t)U\f\u0005\u0002dQ6\tAM\u0003\u0002fM\u0006!A.\u00198h\u0015\u00059\u0017\u0001\u00026bm\u0006L!!\u001b3\u0003\rM#(/\u001b8h\u00039\u0001(o\u001c9feRL\b*Z1eKJ\fqb\u00197bgN\u0004\u0016\r\u001e5IK\u0006$WM]\u0001\u000eQ\u0016\fG-\u001a:DY\u0006\u001c8/Z:\u00023!,\u0017\rZ3s\u00072\f7o]3t\u001d>\u001cvN\u001d;WC2,Xm]\u0001\u000eUZl'k\\<ECR\f\u0007K]3\u0015\u0005A\u001c\bC\u0001%r\u0013\t\u0011\u0018J\u0001\u0003FY\u0016l\u0007\"\u0002;\f\u0001\u0004)\u0018AA6w!\u00111x/_=\u000e\u0003\rK!\u0001_\"\u0003\rQ+\b\u000f\\33!\tQhP\u0004\u0002|yB\u0011AhQ\u0005\u0003{\u000e\u000ba\u0001\u0015:fI\u00164\u0017BA5\u0000\u0015\ti8)\u0001\u0004km6\u0014vn\u001e\u000b\u0004a\u0006\u0015\u0001\"\u0002;\r\u0001\u0004)\u0018a\u00039s_B,'\u000f^=S_^$2\u0001]A\u0006\u0011\u0015!X\u00021\u0001v\u00031\u0019G.Y:t!\u0006$\bNU8x)\r\u0001\u0018\u0011\u0003\u0005\u0007\u0003'q\u0001\u0019A;\u0002\t\u0011\fG/\u0019"
)
public class EnvironmentPage extends WebUIPage {
   private final EnvironmentTab parent;
   private final SparkConf conf;
   private final AppStatusStore store;

   public Seq render(final HttpServletRequest request) {
      ApplicationEnvironmentInfo appEnv = this.store.environmentInfo();
      Map jvmInformation = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Java Version"), appEnv.runtime().javaVersion()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Java Home"), appEnv.runtime().javaHome()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("Scala Version"), appEnv.runtime().scalaVersion())})));
      Map resourceProfileInfo = ((IterableOnceOps)this.store.resourceProfileInfo().map((rinfo) -> {
         String einfo = constructExecutorRequestString$1(rinfo.executorResources());
         String tinfo = constructTaskRequestString$1(rinfo.taskResources());
         String res = "Executor Reqs:\n" + einfo + "\nTask Reqs:\n" + tinfo;
         return new Tuple2(Integer.toString(rinfo.id()), res);
      })).toMap(scala..less.colon.less..MODULE$.refl());
      Seq x$1 = this.resourceProfileHeader();
      Function1 x$2 = (kv) -> this.jvmRowDataPre(kv);
      Seq x$3 = (Seq)resourceProfileInfo.toSeq().sortWith((x$1x, x$2x) -> BoxesRunTime.boxToBoolean($anonfun$render$5(x$1x, x$2x)));
      boolean x$4 = true;
      Seq x$5 = this.headerClassesNoSortValues();
      Option x$6 = UIUtils$.MODULE$.listingTable$default$5();
      boolean x$7 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$8 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$9 = UIUtils$.MODULE$.listingTable$default$9();
      Seq resourceProfileInformationTable = UIUtils$.MODULE$.listingTable(x$1, x$2, x$3, true, x$6, x$5, x$7, x$8, x$9);
      Seq x$10 = this.propertyHeader();
      Function1 x$11 = (kv) -> this.jvmRow(kv);
      Seq x$12 = (Seq)jvmInformation.toSeq().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
      boolean x$13 = true;
      Seq x$14 = this.headerClasses();
      Option x$15 = UIUtils$.MODULE$.listingTable$default$5();
      boolean x$16 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$17 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$18 = UIUtils$.MODULE$.listingTable$default$9();
      Seq runtimeInformationTable = UIUtils$.MODULE$.listingTable(x$10, x$11, x$12, true, x$15, x$14, x$16, x$17, x$18);
      Seq x$19 = this.propertyHeader();
      Function1 x$20 = (kv) -> this.propertyRow(kv);
      scala.collection.Seq x$21 = Utils$.MODULE$.redact(this.conf, (scala.collection.Seq)appEnv.sparkProperties().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$)));
      boolean x$22 = true;
      Seq x$23 = this.headerClasses();
      Option x$24 = UIUtils$.MODULE$.listingTable$default$5();
      boolean x$25 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$26 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$27 = UIUtils$.MODULE$.listingTable$default$9();
      Seq sparkPropertiesTable = UIUtils$.MODULE$.listingTable(x$19, x$20, x$21, true, x$24, x$23, x$25, x$26, x$27);
      scala.collection.Seq emptyProperties = (scala.collection.Seq)scala.collection.Seq..MODULE$.empty();
      Seq x$28 = this.propertyHeader();
      Function1 x$29 = (kv) -> this.propertyRow(kv);
      scala.collection.Seq x$30 = Utils$.MODULE$.redact(this.conf, (scala.collection.Seq)((SeqOps)scala.Option..MODULE$.apply(appEnv.hadoopProperties()).getOrElse(() -> emptyProperties)).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$)));
      boolean x$31 = true;
      Seq x$32 = this.headerClasses();
      Option x$33 = UIUtils$.MODULE$.listingTable$default$5();
      boolean x$34 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$35 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$36 = UIUtils$.MODULE$.listingTable$default$9();
      Seq hadoopPropertiesTable = UIUtils$.MODULE$.listingTable(x$28, x$29, x$30, true, x$33, x$32, x$34, x$35, x$36);
      Seq x$37 = this.propertyHeader();
      Function1 x$38 = (kv) -> this.propertyRow(kv);
      scala.collection.Seq x$39 = Utils$.MODULE$.redact(this.conf, (scala.collection.Seq)appEnv.systemProperties().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$)));
      boolean x$40 = true;
      Seq x$41 = this.headerClasses();
      Option x$42 = UIUtils$.MODULE$.listingTable$default$5();
      boolean x$43 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$44 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$45 = UIUtils$.MODULE$.listingTable$default$9();
      Seq systemPropertiesTable = UIUtils$.MODULE$.listingTable(x$37, x$38, x$39, true, x$42, x$41, x$43, x$44, x$45);
      Seq x$46 = this.propertyHeader();
      Function1 x$47 = (kv) -> this.propertyRow(kv);
      scala.collection.Seq x$48 = Utils$.MODULE$.redact(this.conf, (scala.collection.Seq)((SeqOps)scala.Option..MODULE$.apply(appEnv.metricsProperties()).getOrElse(() -> emptyProperties)).sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$)));
      boolean x$49 = true;
      Seq x$50 = this.headerClasses();
      Option x$51 = UIUtils$.MODULE$.listingTable$default$5();
      boolean x$52 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$53 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$54 = UIUtils$.MODULE$.listingTable$default$9();
      Seq metricsPropertiesTable = UIUtils$.MODULE$.listingTable(x$46, x$47, x$48, true, x$51, x$50, x$52, x$53, x$54);
      Seq x$55 = this.classPathHeader();
      Function1 x$56 = (data) -> this.classPathRow(data);
      scala.collection.Seq x$57 = (scala.collection.Seq)appEnv.classpathEntries().sorted(scala.math.Ordering..MODULE$.Tuple2(scala.math.Ordering.String..MODULE$, scala.math.Ordering.String..MODULE$));
      boolean x$58 = true;
      Seq x$59 = this.headerClasses();
      Option x$60 = UIUtils$.MODULE$.listingTable$default$5();
      boolean x$61 = UIUtils$.MODULE$.listingTable$default$7();
      boolean x$62 = UIUtils$.MODULE$.listingTable$default$8();
      Seq x$63 = UIUtils$.MODULE$.listingTable$default$9();
      Seq classpathEntriesTable = UIUtils$.MODULE$.listingTable(x$55, x$56, x$57, true, x$60, x$59, x$61, x$62, x$63);
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var127 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-runtimeInformation',\n            'aggregated-runtimeInformation')"), $md);
      var127 = new UnprefixedAttribute("class", new Text("collapse-aggregated-runtimeInformation collapse-table"), var127);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var129 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var129, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n            "));
      Null var10031 = scala.xml.Null..MODULE$;
      TopScope var10032 = scala.xml.TopScope..MODULE$;
      NodeSeq var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Runtime Information"));
      $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var127, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var130 = new UnprefixedAttribute("class", new Text("aggregated-runtimeInformation collapsible-table"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(runtimeInformationTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var130, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var131 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-sparkProperties',\n            'aggregated-sparkProperties')"), $md);
      var131 = new UnprefixedAttribute("class", new Text("collapse-aggregated-sparkProperties collapse-table"), var131);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      var10022 = scala.xml.Null..MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var133 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var133, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n            "));
      var10031 = scala.xml.Null..MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Spark Properties"));
      $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var131, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var134 = new UnprefixedAttribute("class", new Text("aggregated-sparkProperties collapsible-table"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(sparkPropertiesTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var134, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var135 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-execResourceProfileInformation',\n            'aggregated-execResourceProfileInformation')"), $md);
      var135 = new UnprefixedAttribute("class", new Text("collapse-aggregated-execResourceProfileInformation collapse-table"), var135);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      var10022 = scala.xml.Null..MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var137 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var137, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n            "));
      var10031 = scala.xml.Null..MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Resource Profiles"));
      $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var135, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var138 = new UnprefixedAttribute("class", new Text("aggregated-execResourceProfileInformation collapsible-table"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(resourceProfileInformationTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var138, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var139 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-hadoopProperties',\n            'aggregated-hadoopProperties')"), $md);
      var139 = new UnprefixedAttribute("class", new Text("collapse-aggregated-hadoopProperties collapse-table"), var139);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      var10022 = scala.xml.Null..MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var141 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-closed"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var141, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n            "));
      var10031 = scala.xml.Null..MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Hadoop Properties"));
      $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var139, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var142 = new UnprefixedAttribute("class", new Text("aggregated-hadoopProperties collapsible-table collapsed"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(hadoopPropertiesTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var142, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var143 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-systemProperties',\n            'aggregated-systemProperties')"), $md);
      var143 = new UnprefixedAttribute("class", new Text("collapse-aggregated-systemProperties collapse-table"), var143);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      var10022 = scala.xml.Null..MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var145 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-closed"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var145, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n            "));
      var10031 = scala.xml.Null..MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("System Properties"));
      $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var143, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var146 = new UnprefixedAttribute("class", new Text("aggregated-systemProperties collapsible-table collapsed"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(systemPropertiesTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var146, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var147 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-metricsProperties',\n            'aggregated-metricsProperties')"), $md);
      var147 = new UnprefixedAttribute("class", new Text("collapse-aggregated-metricsProperties collapse-table"), var147);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      var10022 = scala.xml.Null..MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var149 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-closed"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var149, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n            "));
      var10031 = scala.xml.Null..MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Metrics Properties"));
      $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var147, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var150 = new UnprefixedAttribute("class", new Text("aggregated-metricsProperties collapsible-table collapsed"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(metricsPropertiesTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var150, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var151 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-classpathEntries',\n            'aggregated-classpathEntries')"), $md);
      var151 = new UnprefixedAttribute("class", new Text("collapse-aggregated-classpathEntries collapse-table"), var151);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      var10022 = scala.xml.Null..MODULE$;
      var10023 = scala.xml.TopScope..MODULE$;
      var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n            "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var153 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-closed"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var153, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n            "));
      var10031 = scala.xml.Null..MODULE$;
      var10032 = scala.xml.TopScope..MODULE$;
      var10034 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("Classpath Entries"));
      $buf.$amp$plus(new Elem((String)null, "a", var10031, var10032, false, var10034.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "span", var151, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var154 = new UnprefixedAttribute("class", new Text("aggregated-classpathEntries collapsible-table collapsed"), $md);
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      $buf.$amp$plus(classpathEntriesTable);
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "div", var154, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      Elem content = new Elem((String)null, "span", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      return UIUtils$.MODULE$.headerSparkPage(request, "Environment", () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
   }

   private Seq resourceProfileHeader() {
      return new scala.collection.immutable..colon.colon("Resource Profile Id", new scala.collection.immutable..colon.colon("Resource Profile Contents", scala.collection.immutable.Nil..MODULE$));
   }

   private Seq propertyHeader() {
      return new scala.collection.immutable..colon.colon("Name", new scala.collection.immutable..colon.colon("Value", scala.collection.immutable.Nil..MODULE$));
   }

   private Seq classPathHeader() {
      return new scala.collection.immutable..colon.colon("Resource", new scala.collection.immutable..colon.colon("Source", scala.collection.immutable.Nil..MODULE$));
   }

   private Seq headerClasses() {
      return new scala.collection.immutable..colon.colon("sorttable_alpha", new scala.collection.immutable..colon.colon("sorttable_alpha", scala.collection.immutable.Nil..MODULE$));
   }

   private Seq headerClassesNoSortValues() {
      return new scala.collection.immutable..colon.colon("sorttable_numeric", new scala.collection.immutable..colon.colon("sorttable_nosort", scala.collection.immutable.Nil..MODULE$));
   }

   private Elem jvmRowDataPre(final Tuple2 kv) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(kv._1());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(kv._2());
      $buf.$amp$plus(new Elem((String)null, "pre", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Elem jvmRow(final Tuple2 kv) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(kv._1());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(kv._2());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Elem propertyRow(final Tuple2 kv) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(kv._1());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(kv._2());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private Elem classPathRow(final Tuple2 data) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(data._1());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      var10013 = scala.xml.Null..MODULE$;
      var10014 = scala.xml.TopScope..MODULE$;
      var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(data._2());
      $buf.$amp$plus(new Elem((String)null, "td", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      return new Elem((String)null, "tr", var10004, var10005, false, var10007.seqToNodeSeq($buf));
   }

   private static final String constructExecutorRequestString$1(final Map execReqs) {
      return ((IterableOnceOps)execReqs.map((x0$1) -> {
         if (x0$1 != null) {
            ExecutorResourceRequest execReq = (ExecutorResourceRequest)x0$1._2();
            String var10002 = execReq.resourceName();
            StringBuilder execStr = new StringBuilder("\t" + var10002 + ": [amount: " + execReq.amount());
            if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(execReq.discoveryScript()))) {
               execStr.$plus$plus$eq(", discovery: " + execReq.discoveryScript());
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            if (scala.collection.StringOps..MODULE$.nonEmpty$extension(.MODULE$.augmentString(execReq.vendor()))) {
               execStr.$plus$plus$eq(", vendor: " + execReq.vendor());
            } else {
               BoxedUnit var5 = BoxedUnit.UNIT;
            }

            execStr.$plus$plus$eq("]");
            return execStr.toString();
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("\n");
   }

   private static final String constructTaskRequestString$1(final Map taskReqs) {
      return ((IterableOnceOps)taskReqs.map((x0$1) -> {
         if (x0$1 != null) {
            TaskResourceRequest taskReq = (TaskResourceRequest)x0$1._2();
            String var10000 = taskReq.resourceName();
            return "\t" + var10000 + ": [amount: " + taskReq.amount() + "]";
         } else {
            throw new MatchError(x0$1);
         }
      })).mkString("\n");
   }

   // $FF: synthetic method
   public static final boolean $anonfun$render$5(final Tuple2 x$1, final Tuple2 x$2) {
      return scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString((String)x$1._1())) < scala.collection.StringOps..MODULE$.toInt$extension(.MODULE$.augmentString((String)x$2._1()));
   }

   public EnvironmentPage(final EnvironmentTab parent, final SparkConf conf, final AppStatusStore store) {
      super("");
      this.parent = parent;
      this.conf = conf;
      this.store = store;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
