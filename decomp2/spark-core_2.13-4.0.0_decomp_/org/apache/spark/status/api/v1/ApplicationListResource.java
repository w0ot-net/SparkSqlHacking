package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import scala.Option.;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@Produces({"application/json"})
@ScalaSignature(
   bytes = "\u0006\u0005\u0005}a!\u0002\u0003\u0006\u0001\u0015\t\u0002\"\u0002\u000f\u0001\t\u0003q\u0002\"\u0002\u0011\u0001\t\u0003\t\u0003\"\u0002<\u0001\t\u00139(aF!qa2L7-\u0019;j_:d\u0015n\u001d;SKN|WO]2f\u0015\t1q!\u0001\u0002wc)\u0011\u0001\"C\u0001\u0004CBL'B\u0001\u0006\f\u0003\u0019\u0019H/\u0019;vg*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xmE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007CA\r\u001b\u001b\u0005)\u0011BA\u000e\u0006\u0005E\t\u0005/\u001b*fcV,7\u000f^\"p]R,\u0007\u0010^\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq\u0004\u0005\u0002\u001a\u0001\u00059\u0011\r\u001d9MSN$Hc\u0002\u00122\u0015^s6\r\u001b\t\u0004G-rcB\u0001\u0013*\u001d\t)\u0003&D\u0001'\u0015\t9S$\u0001\u0004=e>|GOP\u0005\u0002+%\u0011!\u0006F\u0001\ba\u0006\u001c7.Y4f\u0013\taSF\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tQC\u0003\u0005\u0002\u001a_%\u0011\u0001'\u0002\u0002\u0010\u0003B\u0004H.[2bi&|g.\u00138g_\")!B\u0001a\u0001eA\u00191\u0007\u000f\u001e\u000e\u0003QR!!\u000e\u001c\u0002\tU$\u0018\u000e\u001c\u0006\u0002o\u0005!!.\u0019<b\u0013\tIDG\u0001\u0003MSN$\bCA\r<\u0013\taTAA\tBaBd\u0017nY1uS>t7\u000b^1ukNDC!\r I\u0013B\u0011qHR\u0007\u0002\u0001*\u0011\u0011IQ\u0001\u0003eNT!a\u0011#\u0002\u0005]\u001c(\"A#\u0002\u000f)\f7.\u0019:uC&\u0011q\t\u0011\u0002\u000b#V,'/\u001f)be\u0006l\u0017!\u0002<bYV,\u0017%\u0001\u0006\t\u000b-\u0013\u0001\u0019\u0001'\u0002\u000f5Lg\u000eR1uKB\u0011\u0011$T\u0005\u0003\u001d\u0016\u0011qbU5na2,G)\u0019;f!\u0006\u0014\u0018-\u001c\u0015\u0005\u0015BC5\u000b\u0005\u0002@#&\u0011!\u000b\u0011\u0002\r\t\u00164\u0017-\u001e7u-\u0006dW/Z\u0011\u0002)\u0006Q!\u0007M\u00191[A\nT\u0006M\u0019)\t)s\u0004JV\u0011\u0002\u0017\")\u0001L\u0001a\u0001\u0019\u00069Q.\u0019=ECR,\u0007\u0006B,Q\u0011j\u000b\u0013aW\u0001\u000bgA\u0002\u0004'\f\u00192[A\n\u0004\u0006B,?\u0011v\u000b\u0013\u0001\u0017\u0005\u0006?\n\u0001\r\u0001T\u0001\u000b[&tWI\u001c3ECR,\u0007\u0006\u00020Q\u0011NCCA\u0018 IE\u0006\nq\fC\u0003e\u0005\u0001\u0007A*\u0001\u0006nCb,e\u000e\u001a#bi\u0016DCa\u0019)I5\"\"1M\u0010%hC\u0005!\u0007\"B5\u0003\u0001\u0004Q\u0017!\u00027j[&$\bCA6o\u001b\u0005a'BA77\u0003\u0011a\u0017M\\4\n\u0005=d'aB%oi\u0016<WM\u001d\u0015\u0005QzB\u0015/I\u0001jQ\t\u00111\u000f\u0005\u0002@i&\u0011Q\u000f\u0011\u0002\u0004\u000f\u0016#\u0016\u0001E5t\u0003R$X-\u001c9u\u0013:\u0014\u0016M\\4f)1A80!\u0001\u0002\u0006\u0005%\u00111BA\u0007!\t\u0019\u00120\u0003\u0002{)\t9!i\\8mK\u0006t\u0007\"\u0002?\u0004\u0001\u0004i\u0018aB1ui\u0016l\u0007\u000f\u001e\t\u00033yL!a`\u0003\u0003-\u0005\u0003\b\u000f\\5dCRLwN\\!ui\u0016l\u0007\u000f^%oM>Da!a\u0001\u0004\u0001\u0004a\u0015\u0001D7j]N#\u0018M\u001d;ECR,\u0007BBA\u0004\u0007\u0001\u0007A*\u0001\u0007nCb\u001cF/\u0019:u\t\u0006$X\rC\u0003`\u0007\u0001\u0007A\nC\u0003e\u0007\u0001\u0007A\n\u0003\u0004\u0002\u0010\r\u0001\r\u0001_\u0001\u000bC:L(+\u001e8oS:<\u0007F\u0002\u0001\u0002\u0014!\u000bI\u0002E\u0002@\u0003+I1!a\u0006A\u0005!\u0001&o\u001c3vG\u0016\u001cHFAA\u000eC\t\ti\"\u0001\tbaBd\u0017nY1uS>twF[:p]\u0002"
)
public class ApplicationListResource implements ApiRequestContext {
   @Context
   private ServletContext servletContext;
   @Context
   private HttpServletRequest httpRequest;

   public UIRoot uiRoot() {
      return ApiRequestContext.uiRoot$(this);
   }

   public ServletContext servletContext() {
      return this.servletContext;
   }

   public void servletContext_$eq(final ServletContext x$1) {
      this.servletContext = x$1;
   }

   public HttpServletRequest httpRequest() {
      return this.httpRequest;
   }

   public void httpRequest_$eq(final HttpServletRequest x$1) {
      this.httpRequest = x$1;
   }

   @GET
   public Iterator appList(@QueryParam("status") final List status, @DefaultValue("2010-01-01") @QueryParam("minDate") final SimpleDateParam minDate, @DefaultValue("3000-01-01") @QueryParam("maxDate") final SimpleDateParam maxDate, @DefaultValue("2010-01-01") @QueryParam("minEndDate") final SimpleDateParam minEndDate, @DefaultValue("3000-01-01") @QueryParam("maxEndDate") final SimpleDateParam maxEndDate, @QueryParam("limit") final Integer limit) {
      int numApps = BoxesRunTime.unboxToInt(.MODULE$.apply(limit).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$appList$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> Integer.MAX_VALUE));
      boolean includeCompleted = status.isEmpty() || status.contains(ApplicationStatus.COMPLETED);
      boolean includeRunning = status.isEmpty() || status.contains(ApplicationStatus.RUNNING);
      return this.uiRoot().getApplicationInfoList().filter((app) -> BoxesRunTime.boxToBoolean($anonfun$appList$3(this, includeCompleted, includeRunning, minDate, maxDate, minEndDate, maxEndDate, app))).take(numApps);
   }

   private boolean isAttemptInRange(final ApplicationAttemptInfo attempt, final SimpleDateParam minStartDate, final SimpleDateParam maxStartDate, final SimpleDateParam minEndDate, final SimpleDateParam maxEndDate, final boolean anyRunning) {
      boolean startTimeOk = attempt.startTime().getTime() >= minStartDate.timestamp() && attempt.startTime().getTime() <= maxStartDate.timestamp();
      boolean endTimeOkForRunning = anyRunning && maxEndDate.timestamp() > System.currentTimeMillis();
      boolean endTimeOkForCompleted = !anyRunning && attempt.endTime().getTime() >= minEndDate.timestamp() && attempt.endTime().getTime() <= maxEndDate.timestamp();
      boolean endTimeOk = endTimeOkForRunning || endTimeOkForCompleted;
      return startTimeOk && endTimeOk;
   }

   // $FF: synthetic method
   public static final int $anonfun$appList$1(final Integer x$1) {
      return scala.Predef..MODULE$.Integer2int(x$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appList$4(final ApplicationListResource $this, final SimpleDateParam minDate$1, final SimpleDateParam maxDate$1, final SimpleDateParam minEndDate$1, final SimpleDateParam maxEndDate$1, final boolean anyRunning$1, final ApplicationAttemptInfo attempt) {
      return $this.isAttemptInRange(attempt, minDate$1, maxDate$1, minEndDate$1, maxEndDate$1, anyRunning$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$appList$3(final ApplicationListResource $this, final boolean includeCompleted$1, final boolean includeRunning$1, final SimpleDateParam minDate$1, final SimpleDateParam maxDate$1, final SimpleDateParam minEndDate$1, final SimpleDateParam maxEndDate$1, final ApplicationInfo app) {
      boolean anyRunning = app.attempts().isEmpty() || !((ApplicationAttemptInfo)app.attempts().head()).completed();
      return (!anyRunning && includeCompleted$1 || anyRunning && includeRunning$1) && app.attempts().exists((attempt) -> BoxesRunTime.boxToBoolean($anonfun$appList$4($this, minDate$1, maxDate$1, minEndDate$1, maxEndDate$1, anyRunning, attempt)));
   }

   public ApplicationListResource() {
      ApiRequestContext.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
