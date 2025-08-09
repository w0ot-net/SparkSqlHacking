package org.apache.spark.streaming.ui;

import java.util.concurrent.TimeUnit;
import org.apache.spark.ui.UIUtils.;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.runtime.BoxesRunTime;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

public final class UIUtils$ {
   public static final UIUtils$ MODULE$ = new UIUtils$();

   public String shortTimeUnitString(final TimeUnit unit) {
      if (TimeUnit.NANOSECONDS.equals(unit)) {
         return "ns";
      } else if (TimeUnit.MICROSECONDS.equals(unit)) {
         return "us";
      } else if (TimeUnit.MILLISECONDS.equals(unit)) {
         return "ms";
      } else if (TimeUnit.SECONDS.equals(unit)) {
         return "sec";
      } else if (TimeUnit.MINUTES.equals(unit)) {
         return "min";
      } else if (TimeUnit.HOURS.equals(unit)) {
         return "hrs";
      } else if (TimeUnit.DAYS.equals(unit)) {
         return "days";
      } else {
         throw new MatchError(unit);
      }
   }

   public Tuple2 normalizeDuration(final long milliseconds) {
      if (milliseconds < 1000L) {
         return new Tuple2(BoxesRunTime.boxToDouble((double)milliseconds), TimeUnit.MILLISECONDS);
      } else {
         double seconds = (double)milliseconds / (double)1000;
         if (seconds < (double)60) {
            return new Tuple2(BoxesRunTime.boxToDouble(seconds), TimeUnit.SECONDS);
         } else {
            double minutes = seconds / (double)60;
            if (minutes < (double)60) {
               return new Tuple2(BoxesRunTime.boxToDouble(minutes), TimeUnit.MINUTES);
            } else {
               double hours = minutes / (double)60;
               if (hours < (double)24) {
                  return new Tuple2(BoxesRunTime.boxToDouble(hours), TimeUnit.HOURS);
               } else {
                  double days = hours / (double)24;
                  return new Tuple2(BoxesRunTime.boxToDouble(days), TimeUnit.DAYS);
               }
            }
         }
      }
   }

   public double convertToTimeUnit(final long milliseconds, final TimeUnit unit) {
      if (TimeUnit.NANOSECONDS.equals(unit)) {
         return (double)milliseconds * (double)1000 * (double)1000;
      } else if (TimeUnit.MICROSECONDS.equals(unit)) {
         return (double)milliseconds * (double)1000;
      } else if (TimeUnit.MILLISECONDS.equals(unit)) {
         return (double)milliseconds;
      } else if (TimeUnit.SECONDS.equals(unit)) {
         return (double)milliseconds / (double)1000.0F;
      } else if (TimeUnit.MINUTES.equals(unit)) {
         return (double)milliseconds / (double)1000.0F / (double)60.0F;
      } else if (TimeUnit.HOURS.equals(unit)) {
         return (double)milliseconds / (double)1000.0F / (double)60.0F / (double)60.0F;
      } else if (TimeUnit.DAYS.equals(unit)) {
         return (double)milliseconds / (double)1000.0F / (double)60.0F / (double)60.0F / (double)24.0F;
      } else {
         throw new MatchError(unit);
      }
   }

   public String createOutputOperationFailureForUI(final String failure) {
      if (failure.startsWith("org.apache.spark.Spark")) {
         return "Failed due to Spark job error\n" + failure;
      } else {
         int nextLineIndex = failure.indexOf("\n");
         if (nextLineIndex < 0) {
            nextLineIndex = failure.length();
         }

         String firstLine = failure.substring(0, nextLineIndex);
         return "Failed due to error: " + firstLine + "\n" + failure;
      }
   }

   public Seq failureReasonCell(final String failureReason, final int rowspan, final boolean includeFirstLineInExpandDetails) {
      Tuple2 var6 = .MODULE$.errorSummary(failureReason);
      if (var6 == null) {
         throw new MatchError(var6);
      } else {
         String failureReasonSummary = (String)var6._1();
         boolean isMultiline = var6._2$mcZ$sp();
         Tuple2 var5 = new Tuple2(failureReasonSummary, BoxesRunTime.boxToBoolean(isMultiline));
         String failureReasonSummary = (String)var5._1();
         boolean isMultiline = var5._2$mcZ$sp();
         String failureDetails = isMultiline && !includeFirstLineInExpandDetails ? failureReason.substring(failureReason.indexOf(10) + 1) : failureReason;
         Seq details = .MODULE$.detailsUINode(isMultiline, failureDetails);
         if (rowspan == 1) {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var17 = new UnprefixedAttribute("style", new Text("max-width: 300px"), $md);
            var17 = new UnprefixedAttribute("valign", new Text("middle"), var17);
            TopScope var22 = scala.xml.TopScope..MODULE$;
            NodeSeq var23 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(failureReasonSummary);
            $buf.$amp$plus(details);
            return new Elem((String)null, "td", var17, var22, false, var23.seqToNodeSeq($buf));
         } else {
            MetaData $md = scala.xml.Null..MODULE$;
            MetaData var19 = new UnprefixedAttribute("rowspan", Integer.toString(rowspan), $md);
            var19 = new UnprefixedAttribute("style", new Text("max-width: 300px"), var19);
            var19 = new UnprefixedAttribute("valign", new Text("middle"), var19);
            TopScope var10005 = scala.xml.TopScope..MODULE$;
            NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
            NodeBuffer $buf = new NodeBuffer();
            $buf.$amp$plus(new Text("\n        "));
            $buf.$amp$plus(failureReasonSummary);
            $buf.$amp$plus(details);
            $buf.$amp$plus(new Text("\n      "));
            return new Elem((String)null, "td", var19, var10005, false, var10007.seqToNodeSeq($buf));
         }
      }
   }

   public int failureReasonCell$default$2() {
      return 1;
   }

   public boolean failureReasonCell$default$3() {
      return true;
   }

   private UIUtils$() {
   }
}
