package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import org.apache.spark.status.AppSummary;
import org.apache.spark.status.PoolData;
import org.apache.spark.status.api.v1.StageData;
import org.apache.spark.status.api.v1.StageStatus;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.WebUIPage;
import scala.MatchError;
import scala.Option;
import scala.Predef;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.SeqOps;
import scala.collection.StringOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.package.;
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
   bytes = "\u0006\u0005\u0005uc!B\b\u0011\u0001IQ\u0002\u0002C\u0010\u0001\u0005\u0003\u0005\u000b\u0011B\u0011\t\u000b\u0015\u0002A\u0011\u0001\u0014\t\u000f%\u0002!\u0019!C\u0005U!1Q\u0007\u0001Q\u0001\n-BqA\u000e\u0001C\u0002\u0013%q\u0007\u0003\u0004A\u0001\u0001\u0006I\u0001\u000f\u0005\u0006\u0003\u0002!\tA\u0011\u0005\u0006C\u0002!IA\u0019\u0005\b\u0003\u001b\u0001A\u0011BA\b\u0011\u001d\t\t\u0003\u0001C\u0005\u0003GAq!a\n\u0001\t\u0013\tI\u0003C\u0004\u0002.\u0001!I!a\f\t\u000f\u0005}\u0002\u0001\"\u0003\u0002B!9\u0011\u0011\n\u0001\u0005\n\u0005-#!D!mYN#\u0018mZ3t!\u0006<WM\u0003\u0002\u0012%\u0005!!n\u001c2t\u0015\t\u0019B#\u0001\u0002vS*\u0011QCF\u0001\u0006gB\f'o\u001b\u0006\u0003/a\ta!\u00199bG\",'\"A\r\u0002\u0007=\u0014xm\u0005\u0002\u00017A\u0011A$H\u0007\u0002%%\u0011aD\u0005\u0002\n/\u0016\u0014W+\u0013)bO\u0016\fa\u0001]1sK:$8\u0001\u0001\t\u0003E\rj\u0011\u0001E\u0005\u0003IA\u0011\u0011b\u0015;bO\u0016\u001cH+\u00192\u0002\rqJg.\u001b;?)\t9\u0003\u0006\u0005\u0002#\u0001!)qD\u0001a\u0001C\u0005\u00111oY\u000b\u0002WA\u0019AfL\u0019\u000e\u00035R\u0011AL\u0001\u0006g\u000e\fG.Y\u0005\u0003a5\u0012aa\u00149uS>t\u0007C\u0001\u001a4\u001b\u0005!\u0012B\u0001\u001b\u0015\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\r\u00198\rI\u0001\bgV\u0014\u0007+\u0019;i+\u0005A\u0004CA\u001d?\u001b\u0005Q$BA\u001e=\u0003\u0011a\u0017M\\4\u000b\u0003u\nAA[1wC&\u0011qH\u000f\u0002\u0007'R\u0014\u0018N\\4\u0002\u0011M,(\rU1uQ\u0002\naA]3oI\u0016\u0014HCA\"V!\r!Ej\u0014\b\u0003\u000b*s!AR%\u000e\u0003\u001dS!\u0001\u0013\u0011\u0002\rq\u0012xn\u001c;?\u0013\u0005q\u0013BA&.\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0014(\u0003\u0007M+\u0017O\u0003\u0002L[A\u0011\u0001kU\u0007\u0002#*\u0011!+L\u0001\u0004q6d\u0017B\u0001+R\u0005\u0011qu\u000eZ3\t\u000bY;\u0001\u0019A,\u0002\u000fI,\u0017/^3tiB\u0011\u0001lX\u0007\u00023*\u0011!lW\u0001\u0005QR$\bO\u0003\u0002];\u000691/\u001a:wY\u0016$(\"\u00010\u0002\u000f)\f7.\u0019:uC&\u0011\u0001-\u0017\u0002\u0013\u0011R$\boU3sm2,GOU3rk\u0016\u001cH/\u0001\rtk6l\u0017M]=B]\u0012$\u0016M\u00197f\r>\u00148\u000b^1ukN$ra\u00198|\u0003\u0007\tY\u0001\u0005\u0003-I\u001aT\u0017BA3.\u0005\u0019!V\u000f\u001d7feA\u0019AfL4\u0011\u0005AC\u0017BA5R\u0005\u0011)E.Z7\u0011\u00071z3\u000e\u0005\u0002QY&\u0011Q.\u0015\u0002\b\u001d>$WmU3r\u0011\u0015y\u0007\u00021\u0001q\u0003%\tG\u000e\\*uC\u001e,7\u000fE\u0002E\u0019F\u0004\"A]=\u000e\u0003MT!\u0001^;\u0002\u0005Y\f$B\u0001<x\u0003\r\t\u0007/\u001b\u0006\u0003qR\taa\u001d;biV\u001c\u0018B\u0001>t\u0005%\u0019F/Y4f\t\u0006$\u0018\rC\u0003}\u0011\u0001\u0007Q0\u0001\u0006baB\u001cV/\\7bef\u0004\"A`@\u000e\u0003]L1!!\u0001x\u0005)\t\u0005\u000f]*v[6\f'/\u001f\u0005\u0007q\"\u0001\r!!\u0002\u0011\u0007I\f9!C\u0002\u0002\nM\u00141b\u0015;bO\u0016\u001cF/\u0019;vg\")a\u000b\u0003a\u0001/\u0006Q1\u000f^1ukNt\u0015-\\3\u0015\t\u0005E\u0011q\u0004\t\u0005\u0003'\tYB\u0004\u0003\u0002\u0016\u0005]\u0001C\u0001$.\u0013\r\tI\"L\u0001\u0007!J,G-\u001a4\n\u0007}\niBC\u0002\u0002\u001a5Ba\u0001_\u0005A\u0002\u0005\u0015\u0011\u0001C:uC\u001e,G+Y4\u0015\t\u0005E\u0011Q\u0005\u0005\u0007q*\u0001\r!!\u0002\u0002#!,\u0017\rZ3s\t\u0016\u001c8M]5qi&|g\u000e\u0006\u0003\u0002\u0012\u0005-\u0002B\u0002=\f\u0001\u0004\t)!\u0001\btk6l\u0017M]=D_:$XM\u001c;\u0015\u0011\u0005E\u0011\u0011GA\u001a\u0003kAQ\u0001 \u0007A\u0002uDa\u0001\u001f\u0007A\u0002\u0005\u0015\u0001bBA\u001c\u0019\u0001\u0007\u0011\u0011H\u0001\u0005g&TX\rE\u0002-\u0003wI1!!\u0010.\u0005\rIe\u000e^\u0001\bgVlW.\u0019:z)\u001d9\u00171IA#\u0003\u000fBQ\u0001`\u0007A\u0002uDa\u0001_\u0007A\u0002\u0005\u0015\u0001bBA\u001c\u001b\u0001\u0007\u0011\u0011H\u0001\u0006i\u0006\u0014G.\u001a\u000b\nW\u00065\u0013qJA)\u00037BQ\u0001 \bA\u0002uDa\u0001\u001f\bA\u0002\u0005\u0015\u0001bBA*\u001d\u0001\u0007\u0011QK\u0001\fgR\fw-Z:UC\ndW\rE\u0002#\u0003/J1!!\u0017\u0011\u00059\u0019F/Y4f)\u0006\u0014G.\u001a\"bg\u0016Dq!a\u000e\u000f\u0001\u0004\tI\u0004"
)
public class AllStagesPage extends WebUIPage {
   private final StagesTab parent;
   private final Option sc;
   private final String subPath;

   private Option sc() {
      return this.sc;
   }

   private String subPath() {
      return this.subPath;
   }

   public Seq render(final HttpServletRequest request) {
      Map pools = ((IterableOnceOps)((IterableOps)this.sc().map((x$1) -> x$1.getAllPools()).getOrElse(() -> (Seq).MODULE$.Seq().empty())).map((pool) -> {
         PoolData uiPool = (PoolData)this.parent.store().asOption(() -> this.parent.store().pool(pool.name())).getOrElse(() -> new PoolData(pool.name(), (Set)scala.Predef..MODULE$.Set().apply(scala.collection.immutable.Nil..MODULE$)));
         return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(pool), uiPool);
      })).toMap(scala..less.colon.less..MODULE$.refl());
      PoolTable poolTable = new PoolTable(pools, this.parent);
      Seq allStatuses = new scala.collection.immutable..colon.colon(StageStatus.ACTIVE, new scala.collection.immutable..colon.colon(StageStatus.PENDING, new scala.collection.immutable..colon.colon(StageStatus.COMPLETE, new scala.collection.immutable..colon.colon(StageStatus.SKIPPED, new scala.collection.immutable..colon.colon(StageStatus.FAILED, scala.collection.immutable.Nil..MODULE$)))));
      Seq allStages = this.parent.store().stageList((List)null, this.parent.store().stageList$default$2(), this.parent.store().stageList$default$3(), this.parent.store().stageList$default$4(), this.parent.store().stageList$default$5());
      AppSummary appSummary = this.parent.store().appSummary();
      Tuple2 var9 = ((IterableOps)allStatuses.map((x$2) -> this.summaryAndTableForStatus(allStages, appSummary, x$2, request))).unzip(scala.Predef..MODULE$.$conforms());
      if (var9 != null) {
         Seq summaries = (Seq)var9._1();
         Seq tables = (Seq)var9._2();
         Tuple2 var8 = new Tuple2(summaries, tables);
         Seq summaries = (Seq)var8._1();
         Seq tables = (Seq)var8._2();
         Null var10004 = scala.xml.Null..MODULE$;
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         Null $md = scala.xml.Null..MODULE$;
         MetaData var27 = new UnprefixedAttribute("class", new Text("list-unstyled"), $md);
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(summaries.flatten(scala.Predef..MODULE$.$conforms()));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "ul", var27, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         Elem summary = new Elem((String)null, "div", var10004, var10005, false, var10007.seqToNodeSeq($buf));
         Object var32;
         if (this.parent.isFairScheduler()) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var28 = new UnprefixedAttribute("onClick", new Text("collapseTable('collapse-aggregated-poolTable','aggregated-poolTable')"), $md);
            var28 = new UnprefixedAttribute("class", new Text("collapse-aggregated-poolTable collapse-table"), var28);
            var10005 = scala.xml.TopScope..MODULE$;
            var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            Null var10013 = scala.xml.Null..MODULE$;
            var10014 = scala.xml.TopScope..MODULE$;
            var10016 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n            "));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var30 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
            $buf.$amp$plus(new Elem((String)null, "span", var30, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
            $buf.$amp$plus(new Text("\n            "));
            Null var10022 = scala.xml.Null..MODULE$;
            TopScope var10023 = scala.xml.TopScope..MODULE$;
            NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("Fair Scheduler Pools ("));
            $buf.$amp$plus(BoxesRunTime.boxToInteger(pools.size()));
            $buf.$amp$plus(new Text(")"));
            $buf.$amp$plus(new Elem((String)null, "a", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(new Elem((String)null, "h4", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
            $buf.$amp$plus(new Text("\n        "));
            Elem var10000 = new Elem((String)null, "span", var28, var10005, false, var10007.seqToNodeSeq($buf));
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var31 = new UnprefixedAttribute("class", new Text("aggregated-poolTable collapsible-table"), $md);
            TopScope var10006 = scala.xml.TopScope..MODULE$;
            NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n          "));
            $buf.$amp$plus(poolTable.toNodeSeq(request));
            $buf.$amp$plus(new Text("\n        "));
            var32 = var10000.$plus$plus(new Elem((String)null, "div", var31, var10006, false, var10008.seqToNodeSeq($buf)));
         } else {
            var32 = (Seq).MODULE$.Seq().empty();
         }

         Seq poolsDescription = (Seq)var32;
         NodeSeq content = ((NodeSeq)summary).$plus$plus(poolsDescription).$plus$plus((Seq)((IterableOps)tables.flatten(scala.Predef..MODULE$.$conforms())).flatten(scala.Predef..MODULE$.$conforms()));
         return UIUtils$.MODULE$.headerSparkPage(request, "Stages for All Jobs", () -> content, this.parent, UIUtils$.MODULE$.headerSparkPage$default$5(), UIUtils$.MODULE$.headerSparkPage$default$6(), UIUtils$.MODULE$.headerSparkPage$default$7());
      } else {
         throw new MatchError(var9);
      }
   }

   private Tuple2 summaryAndTableForStatus(final Seq allStages, final AppSummary appSummary, final StageStatus status, final HttpServletRequest request) {
      Seq var10000;
      label51: {
         label50: {
            StageStatus var6 = StageStatus.FAILED;
            if (status == null) {
               if (var6 == null) {
                  break label50;
               }
            } else if (status.equals(var6)) {
               break label50;
            }

            var10000 = (Seq)allStages.filter((x$5) -> BoxesRunTime.boxToBoolean($anonfun$summaryAndTableForStatus$2(status, x$5)));
            break label51;
         }

         var10000 = (Seq)((SeqOps)allStages.filter((x$4) -> BoxesRunTime.boxToBoolean($anonfun$summaryAndTableForStatus$1(status, x$4)))).reverse();
      }

      Seq stages = var10000;
      if (stages.isEmpty()) {
         return new Tuple2(scala.None..MODULE$, scala.None..MODULE$);
      } else {
         label42: {
            label41: {
               StageStatus var8 = StageStatus.ACTIVE;
               if (status == null) {
                  if (var8 != null) {
                     break label41;
                  }
               } else if (!status.equals(var8)) {
                  break label41;
               }

               if (this.parent.killEnabled()) {
                  var13 = true;
                  break label42;
               }
            }

            var13 = false;
         }

         boolean killEnabled;
         label33: {
            label32: {
               killEnabled = var13;
               StageStatus var10 = StageStatus.FAILED;
               if (status == null) {
                  if (var10 == null) {
                     break label32;
                  }
               } else if (status.equals(var10)) {
                  break label32;
               }

               var14 = false;
               break label33;
            }

            var14 = true;
         }

         boolean isFailedStage = var14;
         StageTableBase stagesTable = new StageTableBase(this.parent.store(), request, stages, this.statusName(status), this.stageTag(status), this.parent.basePath(), this.subPath(), this.parent.isFairScheduler(), killEnabled, isFailedStage);
         int stagesSize = stages.size();
         return new Tuple2(new Some(this.summary(appSummary, status, stagesSize)), new Some(this.table(appSummary, status, stagesTable, stagesSize)));
      }
   }

   private String statusName(final StageStatus status) {
      if (StageStatus.ACTIVE.equals(status)) {
         return "active";
      } else if (StageStatus.COMPLETE.equals(status)) {
         return "completed";
      } else if (StageStatus.FAILED.equals(status)) {
         return "failed";
      } else if (StageStatus.PENDING.equals(status)) {
         return "pending";
      } else if (StageStatus.SKIPPED.equals(status)) {
         return "skipped";
      } else {
         throw new MatchError(status);
      }
   }

   private String stageTag(final StageStatus status) {
      String var10000 = this.statusName(status);
      return var10000 + "Stage";
   }

   private String headerDescription(final StageStatus status) {
      return scala.collection.StringOps..MODULE$.capitalize$extension(scala.Predef..MODULE$.augmentString(this.statusName(status)));
   }

   private String summaryContent(final AppSummary appSummary, final StageStatus status, final int size) {
      StageStatus var4 = StageStatus.COMPLETE;
      if (status == null) {
         if (var4 != null) {
            return String.valueOf(BoxesRunTime.boxToInteger(size));
         }
      } else if (!status.equals(var4)) {
         return String.valueOf(BoxesRunTime.boxToInteger(size));
      }

      if (appSummary.numCompletedStages() != size) {
         int var10000 = appSummary.numCompletedStages();
         return var10000 + ", only showing " + size;
      } else {
         return String.valueOf(BoxesRunTime.boxToInteger(size));
      }
   }

   private Elem summary(final AppSummary appSummary, final StageStatus status, final int size) {
      Null var10004 = scala.xml.Null..MODULE$;
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var10 = new UnprefixedAttribute("href", "#" + this.statusName(status), $md);
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n          "));
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(this.headerDescription(status));
      $buf.$amp$plus(new Text(" Stages:"));
      $buf.$amp$plus(new Elem((String)null, "strong", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(new Elem((String)null, "a", var10, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(this.summaryContent(appSummary, status, size));
      $buf.$amp$plus(new Text("\n      "));
      Elem summary = new Elem((String)null, "li", var10004, var10005, false, var10007.seqToNodeSeq($buf));
      StageStatus var9 = StageStatus.COMPLETE;
      if (status == null) {
         if (var9 == null) {
            return summary.$percent((MetaData)scala.xml.Attribute..MODULE$.apply(scala.None..MODULE$, "id", scala.xml.Text..MODULE$.apply("completed-summary"), scala.xml.Null..MODULE$));
         }
      } else if (status.equals(var9)) {
         return summary.$percent((MetaData)scala.xml.Attribute..MODULE$.apply(scala.None..MODULE$, "id", scala.xml.Text..MODULE$.apply("completed-summary"), scala.xml.Null..MODULE$));
      }

      return summary;
   }

   private NodeSeq table(final AppSummary appSummary, final StageStatus status, final StageTableBase stagesTable, final int size) {
      StringOps var10000 = scala.collection.StringOps..MODULE$;
      Predef var10001 = scala.Predef..MODULE$;
      String classSuffix = var10000.capitalize$extension(var10001.augmentString(this.statusName(status))) + "Stages";
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var13 = new UnprefixedAttribute("onClick", "collapseTable('collapse-aggregated-all" + classSuffix + "', 'aggregated-all" + classSuffix + "')", $md);
      var13 = new UnprefixedAttribute("class", "collapse-aggregated-all" + classSuffix + " collapse-table", var13);
      var13 = new UnprefixedAttribute("id", this.statusName(status), var13);
      TopScope var10005 = scala.xml.TopScope..MODULE$;
      NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n      "));
      Null var10013 = scala.xml.Null..MODULE$;
      TopScope var10014 = scala.xml.TopScope..MODULE$;
      NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var16 = new UnprefixedAttribute("class", new Text("collapse-table-arrow arrow-open"), $md);
      $buf.$amp$plus(new Elem((String)null, "span", var16, scala.xml.TopScope..MODULE$, false, scala.collection.immutable.Nil..MODULE$));
      $buf.$amp$plus(new Text("\n        "));
      Null var10022 = scala.xml.Null..MODULE$;
      TopScope var10023 = scala.xml.TopScope..MODULE$;
      NodeSeq var10025 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(this.headerDescription(status));
      $buf.$amp$plus(new Text(" Stages ("));
      $buf.$amp$plus(this.summaryContent(appSummary, status, size));
      $buf.$amp$plus(new Text(")"));
      $buf.$amp$plus(new Elem((String)null, "a", var10022, var10023, false, var10025.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n      "));
      $buf.$amp$plus(new Elem((String)null, "h4", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
      $buf.$amp$plus(new Text("\n    "));
      Elem var18 = new Elem((String)null, "span", var13, var10005, false, var10007.seqToNodeSeq($buf));
      MetaData $md = scala.xml.Null..MODULE$;
      MetaData var17 = new UnprefixedAttribute("class", "aggregated-all" + classSuffix + " collapsible-table", $md);
      TopScope var10006 = scala.xml.TopScope..MODULE$;
      NodeSeq var10008 = scala.xml.NodeSeq..MODULE$;
      NodeBuffer $buf = new NodeBuffer();
      $buf.$amp$plus(new Text("\n        "));
      $buf.$amp$plus(stagesTable.toNodeSeq());
      $buf.$amp$plus(new Text("\n      "));
      return var18.$plus$plus(new Elem((String)null, "div", var17, var10006, false, var10008.seqToNodeSeq($buf)));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$summaryAndTableForStatus$1(final StageStatus status$1, final StageData x$4) {
      boolean var3;
      label23: {
         StageStatus var10000 = x$4.status();
         if (var10000 == null) {
            if (status$1 == null) {
               break label23;
            }
         } else if (var10000.equals(status$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$summaryAndTableForStatus$2(final StageStatus status$1, final StageData x$5) {
      boolean var3;
      label23: {
         StageStatus var10000 = x$5.status();
         if (var10000 == null) {
            if (status$1 == null) {
               break label23;
            }
         } else if (var10000.equals(status$1)) {
            break label23;
         }

         var3 = false;
         return var3;
      }

      var3 = true;
      return var3;
   }

   public AllStagesPage(final StagesTab parent) {
      super("");
      this.parent = parent;
      this.sc = parent.sc();
      this.subPath = "stages";
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
