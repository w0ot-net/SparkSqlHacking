package org.apache.spark.status.api.v1;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.UriInfo;
import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import org.apache.spark.ui.UIUtils$;
import org.apache.spark.ui.jobs.ApiHelper$;
import org.apache.spark.util.Utils$;
import org.glassfish.jersey.internal.util.collection.MultivaluedStringMap;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ObjectRef;

@Produces({"application/json"})
@ScalaSignature(
   bytes = "\u0006\u0005\t5d!B\u0006\r\u00011A\u0002\"B\u0012\u0001\t\u0003)\u0003\"B\u0014\u0001\t\u0003A\u0003bBA\u0001\u0001\u0011\u0005\u00111\u0001\u0005\b\u0003\u0003\u0002A\u0011AA\"\u0011\u001d\ty\u0007\u0001C\u0001\u0003cBq!a%\u0001\t\u0003\t)\nC\u0004\u0002d\u0002!\t!!:\t\u000f\t\u001d\u0002\u0001\"\u0001\u0003*!9!\u0011\t\u0001\u0005\u0002\t\r\u0003b\u0002B'\u0001\u0011\u0005!q\n\u0002\u000f'R\fw-Z:SKN|WO]2f\u0015\tia\"\u0001\u0002wc)\u0011q\u0002E\u0001\u0004CBL'BA\t\u0013\u0003\u0019\u0019H/\u0019;vg*\u00111\u0003F\u0001\u0006gB\f'o\u001b\u0006\u0003+Y\ta!\u00199bG\",'\"A\f\u0002\u0007=\u0014xmE\u0002\u00013}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0007C\u0001\u0011\"\u001b\u0005a\u0011B\u0001\u0012\r\u0005=\u0011\u0015m]3BaB\u0014Vm]8ve\u000e,\u0017A\u0002\u001fj]&$hh\u0001\u0001\u0015\u0003\u0019\u0002\"\u0001\t\u0001\u0002\u0013M$\u0018mZ3MSN$HCB\u00159%~#G\u000fE\u0002+eUr!a\u000b\u0019\u000f\u00051zS\"A\u0017\u000b\u00059\"\u0013A\u0002\u001fs_>$h(C\u0001\u001d\u0013\t\t4$A\u0004qC\u000e\\\u0017mZ3\n\u0005M\"$aA*fc*\u0011\u0011g\u0007\t\u0003AYJ!a\u000e\u0007\u0003\u0013M#\u0018mZ3ECR\f\u0007\"B\u001d\u0003\u0001\u0004Q\u0014\u0001C:uCR,8/Z:\u0011\u0007m\u0002%)D\u0001=\u0015\tid(\u0001\u0003vi&d'\"A \u0002\t)\fg/Y\u0005\u0003\u0003r\u0012A\u0001T5tiB\u0011\u0001eQ\u0005\u0003\t2\u00111b\u0015;bO\u0016\u001cF/\u0019;vg\"\"\u0001H\u0012)R!\t9e*D\u0001I\u0015\tI%*\u0001\u0002sg*\u00111\nT\u0001\u0003oNT\u0011!T\u0001\bU\u0006\\\u0017M\u001d;b\u0013\ty\u0005J\u0001\u0006Rk\u0016\u0014\u0018\u0010U1sC6\fQA^1mk\u0016\f\u0013!\u0005\u0005\u0006'\n\u0001\r\u0001V\u0001\bI\u0016$\u0018-\u001b7t!\tQR+\u0003\u0002W7\t9!i\\8mK\u0006t\u0007\u0006\u0002*G!b\u000b\u0013a\u0015\u0015\u0005%j\u0003V\f\u0005\u0002H7&\u0011A\f\u0013\u0002\r\t\u00164\u0017-\u001e7u-\u0006dW/Z\u0011\u0002=\u0006)a-\u00197tK\")\u0001M\u0001a\u0001)\u0006iq/\u001b;i'VlW.\u0019:jKNDCa\u0018$QE\u0006\n\u0001\r\u000b\u0003`5Bk\u0006\"B3\u0003\u0001\u00041\u0017AD9vC:$\u0018\u000e\\3TiJLgn\u001a\t\u0003O.t!\u0001[5\u0011\u00051Z\u0012B\u00016\u001c\u0003\u0019\u0001&/\u001a3fM&\u0011A.\u001c\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005)\\\u0002\u0006\u00023G!>\f\u0013\u0001]\u0001\ncV\fg\u000e^5mKNDC\u0001\u001a.Qe\u0006\n1/A\u000b1]Ab\u0003G\f\u001a6YArS\u0007\f\u0019/oUb\u0013G\f\u0019\t\u000bU\u0014\u0001\u0019\u0001<\u0002\u0015Q\f7o[*uCR,8\u000fE\u0002<\u0001^\u0004\"\u0001\t=\n\u0005ed!A\u0003+bg.\u001cF/\u0019;vg\"\"AO\u0012)|C\u0005)\bF\u0001\u0002~!\t9e0\u0003\u0002\u0000\u0011\n\u0019q)\u0012+\u0002\u0013M$\u0018mZ3ECR\fGcC\u0015\u0002\u0006\u0005e\u00111EA\u0014\u0003[Aq!a\u0002\u0004\u0001\u0004\tI!A\u0004ti\u0006<W-\u00133\u0011\u0007i\tY!C\u0002\u0002\u000em\u00111!\u00138uQ\u001d\t)!!\u0005Q\u0003/\u00012aRA\n\u0013\r\t)\u0002\u0013\u0002\n!\u0006$\b\u000eU1sC6\f#!a\u0002\t\u000bM\u001b\u0001\u0019\u0001+)\u000b\u0005ea\t\u0015-)\r\u0005e!\fUA\u0010C\t\t\t#\u0001\u0003ueV,\u0007\"B;\u0004\u0001\u00041\b&BA\u0012\rB[\b\"\u00021\u0004\u0001\u0004!\u0006&BA\u0014\rB\u0013\u0007&BA\u00145Bk\u0006\"B3\u0004\u0001\u00041\u0007&BA\u0017\rB{\u0007&BA\u00175B\u0013\bFA\u0002~Q\u0019\u0019\u0011q\u0007)\u0002>A\u0019q)!\u000f\n\u0007\u0005m\u0002J\u0001\u0003QCRD\u0017EAA \u00039Y8\u000f^1hK&#'\b\t/eWu\fab\u001c8f\u0003R$X-\u001c9u\t\u0006$\u0018\rF\u00076\u0003\u000b\nI%!\u0015\u0002X\u0005m\u0013\u0011\r\u0005\b\u0003\u000f!\u0001\u0019AA\u0005Q\u001d\t)%!\u0005Q\u0003/Aq!a\u0013\u0005\u0001\u0004\tI!\u0001\bti\u0006<W-\u0011;uK6\u0004H/\u00133)\u000f\u0005%\u0013\u0011\u0003)\u0002P\u0005\u0012\u00111\n\u0005\u0006'\u0012\u0001\r\u0001\u0016\u0015\u0006\u0003#2\u0005\u000b\u0017\u0015\u0007\u0003#R\u0006+a\b\t\u000bU$\u0001\u0019\u0001<)\u000b\u0005]c\tU>\t\u000b\u0001$\u0001\u0019\u0001+)\u000b\u0005mc\t\u00152)\u000b\u0005m#\fU/\t\u000b\u0015$\u0001\u0019\u00014)\u000b\u0005\u0005d\tU8)\u000b\u0005\u0005$\f\u0015:)\u0005\u0011i\bF\u0002\u0003\u00028A\u000bY'\t\u0002\u0002n\u0005!3p\u001d;bO\u0016LEM\u000f\u0011]I.jxf_:uC\u001e,\u0017\t\u001e;f[B$\u0018\n\u001a\u001e!9\u0012\\S0A\u0006uCN\\7+^7nCJLH\u0003CA:\u0003s\ni(!!\u0011\u0007\u0001\n)(C\u0002\u0002x1\u0011q\u0003V1tW6+GO]5d\t&\u001cHO]5ckRLwN\\:\t\u000f\u0005\u001dQ\u00011\u0001\u0002\n!:\u0011\u0011PA\t!\u0006]\u0001bBA&\u000b\u0001\u0007\u0011\u0011\u0002\u0015\b\u0003{\n\t\u0002UA(\u0011\u0015)W\u00011\u0001gQ\u0019\t\tI\u0017)\u0002\u0006\u0006\u0012\u0011qQ\u0001\u0018a9\u0002T\u0007\f\u0019/eUb\u0003GL\u001b-a9:T\u0007\f\u0019/sUBS!!!G!>D#!B?)\r\u0015\t9\u0004UAHC\t\t\t*\u0001\u0019|gR\fw-Z%eu\u0001bFmK?0wN$\u0018mZ3BiR,W\u000e\u001d;JIj\u0002C\fZ\u0016~_Q\f7o[*v[6\f'/_\u0001\ti\u0006\u001c8\u000eT5tiRq\u0011qSAP\u0003G\u000b9+!.\u0002D\u0006]\u0007\u0003\u0002\u00163\u00033\u00032\u0001IAN\u0013\r\ti\n\u0004\u0002\t)\u0006\u001c8\u000eR1uC\"9\u0011q\u0001\u0004A\u0002\u0005%\u0001fBAP\u0003#\u0001\u0016q\u0003\u0005\b\u0003\u00172\u0001\u0019AA\u0005Q\u001d\t\u0019+!\u0005Q\u0003\u001fBq!!+\u0007\u0001\u0004\tI!\u0001\u0004pM\u001a\u001cX\r\u001e\u0015\u0007\u0003OS\u0006+!,\"\u0005\u0005=\u0016!\u0001\u0019)\r\u0005\u001df\tUAZC\t\tI\u000bC\u0004\u00028\u001a\u0001\r!!\u0003\u0002\r1,gn\u001a;iQ\u0019\t)L\u0017)\u0002<\u0006\u0012\u0011QX\u0001\u0003eABc!!.G!\u0006\u0005\u0017EAA\\\u0011\u001d\t)M\u0002a\u0001\u0003\u000f\faa]8si\nK\bc\u0001\u0011\u0002J&\u0019\u00111\u001a\u0007\u0003\u0017Q\u000b7o[*peRLgn\u001a\u0015\u0007\u0003\u0007T\u0006+a4\"\u0005\u0005E\u0017AA%EQ\u0019\t\u0019M\u0012)\u0002V\u0006\u0012\u0011Q\u0019\u0005\u0006s\u0019\u0001\rA\u001e\u0015\u0006\u0003/4\u0005+\u0015\u0015\u0003\ruDcABA\u001c!\u0006}\u0017EAAq\u00035Z8\u000f^1hK&#'\b\t/eWu|3p\u001d;bO\u0016\fE\u000f^3naRLEM\u000f\u0011]I.jx\u0006^1tW2K7\u000f^\u0001\ni\u0006\u001c8\u000eV1cY\u0016$\"\"a:\u0002z\u0006u(\u0011\u0001B\u0004!\u0019Y\u0014\u0011\u001e4\u0002n&\u0019\u00111\u001e\u001f\u0003\u000f!\u000b7\u000f['baB!\u0011q^A{\u001b\t\t\tPC\u0002\u0002tz\nA\u0001\\1oO&!\u0011q_Ay\u0005\u0019y%M[3di\"9\u0011qA\u0004A\u0002\u0005%\u0001fBA}\u0003#\u0001\u0016q\u0003\u0005\b\u0003\u0017:\u0001\u0019AA\u0005Q\u001d\ti0!\u0005Q\u0003\u001fBQaU\u0004A\u0002QCSA!\u0001G!bCcA!\u0001[!\u0006}\u0001b\u0002B\u0005\u000f\u0001\u0007!1B\u0001\bkJL\u0017J\u001c4p!\u0011\u0011iAa\u0005\u000e\u0005\t=!b\u0001B\t\u0011\u0006!1m\u001c:f\u0013\u0011\u0011)Ba\u0004\u0003\u000fU\u0013\u0018.\u00138g_\"\"!q\u0001B\r!\u0011\u0011iAa\u0007\n\t\tu!q\u0002\u0002\b\u0007>tG/\u001a=uQ\t9Q\u0010\u000b\u0004\b\u0003o\u0001&1E\u0011\u0003\u0005K\taf_:uC\u001e,\u0017\n\u001a\u001e!9\u0012\\SpL>ti\u0006<W-\u0011;uK6\u0004H/\u00133;Aq#7&`\u0018uCN\\G+\u00192mK\u0006aAm\u001c)bO&t\u0017\r^5p]Ra\u0011q\u0013B\u0016\u0005k\u00119D!\u000f\u0003>!9!Q\u0006\u0005A\u0002\t=\u0012aD9vKJL\b+\u0019:b[\u0016$XM]:\u0011\r\t5!\u0011\u00074g\u0013\u0011\u0011\u0019Da\u0004\u0003\u001d5+H\u000e^5wC2,X\rZ'ba\"9\u0011q\u0001\u0005A\u0002\u0005%\u0001bBA&\u0011\u0001\u0007\u0011\u0011\u0002\u0005\u0007\u0005wA\u0001\u0019\u0001+\u0002\u0011%\u001c8+Z1sG\"DqAa\u0010\t\u0001\u0004\tI!\u0001\u0007u_R\fGNU3d_J$7/\u0001\bgS2$XM\u001d+bg.d\u0015n\u001d;\u0015\r\u0005]%Q\tB%\u0011\u001d\u00119%\u0003a\u0001\u0003/\u000bA\u0002^1tW\u0012\u000bG/\u0019'jgRDaAa\u0013\n\u0001\u00041\u0017aC:fCJ\u001c\u0007NV1mk\u0016\f1\u0003]1sg\u0016\fV/\u00198uS2,7\u000b\u001e:j]\u001e$BA!\u0015\u0003^A)!Da\u0015\u0003X%\u0019!QK\u000e\u0003\u000b\u0005\u0013(/Y=\u0011\u0007i\u0011I&C\u0002\u0003\\m\u0011a\u0001R8vE2,\u0007\"B3\u000b\u0001\u00041\u0007F\u0002\u0001\u0003bA\u00139\u0007E\u0002H\u0005GJ1A!\u001aI\u0005!\u0001&o\u001c3vG\u0016\u001cHF\u0001B5C\t\u0011Y'\u0001\tbaBd\u0017nY1uS>twF[:p]\u0002"
)
public class StagesResource implements BaseAppResource {
   @PathParam("appId")
   private String appId;
   @PathParam("attemptId")
   private String attemptId;
   @Context
   private ServletContext servletContext;
   @Context
   private HttpServletRequest httpRequest;

   public Object withUI(final Function1 fn) {
      return BaseAppResource.withUI$(this, fn);
   }

   public void checkUIViewPermissions() {
      BaseAppResource.checkUIViewPermissions$(this);
   }

   public UIRoot uiRoot() {
      return ApiRequestContext.uiRoot$(this);
   }

   public String appId() {
      return this.appId;
   }

   public void appId_$eq(final String x$1) {
      this.appId = x$1;
   }

   public String attemptId() {
      return this.attemptId;
   }

   public void attemptId_$eq(final String x$1) {
      this.attemptId = x$1;
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
   public Seq stageList(@QueryParam("status") final List statuses, @QueryParam("details") @DefaultValue("false") final boolean details, @QueryParam("withSummaries") @DefaultValue("false") final boolean withSummaries, @QueryParam("quantiles") @DefaultValue("0.0,0.25,0.5,0.75,1.0") final String quantileString, @QueryParam("taskStatus") final List taskStatus) {
      double[] quantiles = this.parseQuantileString(quantileString);
      return (Seq)this.withUI((ui) -> (Seq)ui.store().stageList(statuses, details, withSummaries, quantiles, taskStatus).filter((stage) -> BoxesRunTime.boxToBoolean($anonfun$stageList$2(details, taskStatus, stage))));
   }

   @GET
   @Path("{stageId: \\d+}")
   public Seq stageData(@PathParam("stageId") final int stageId, @QueryParam("details") @DefaultValue("true") final boolean details, @QueryParam("taskStatus") final List taskStatus, @QueryParam("withSummaries") @DefaultValue("false") final boolean withSummaries, @QueryParam("quantiles") @DefaultValue("0.0,0.25,0.5,0.75,1.0") final String quantileString) {
      return (Seq)this.withUI((ui) -> {
         double[] quantiles = this.parseQuantileString(quantileString);
         Seq ret = ui.store().stageData(stageId, details, taskStatus, withSummaries, quantiles);
         if (ret.nonEmpty()) {
            return ret;
         } else {
            throw new NotFoundException("unknown stage: " + stageId);
         }
      });
   }

   @GET
   @Path("{stageId: \\d+}/{stageAttemptId: \\d+}")
   public StageData oneAttemptData(@PathParam("stageId") final int stageId, @PathParam("stageAttemptId") final int stageAttemptId, @QueryParam("details") @DefaultValue("true") final boolean details, @QueryParam("taskStatus") final List taskStatus, @QueryParam("withSummaries") @DefaultValue("false") final boolean withSummaries, @QueryParam("quantiles") @DefaultValue("0.0,0.25,0.5,0.75,1.0") final String quantileString) {
      return (StageData)this.withUI((ui) -> {
         try {
            double[] quantiles = this.parseQuantileString(quantileString);
            return (StageData)ui.store().stageAttempt(stageId, stageAttemptId, details, taskStatus, withSummaries, quantiles)._1();
         } catch (NoSuchElementException var12) {
            Seq all = ui.store().stageData(stageId, false, taskStatus, ui.store().stageData$default$4(), ui.store().stageData$default$5());
            String var10000;
            if (all.nonEmpty()) {
               Seq ids = (Seq)all.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$oneAttemptData$2(x$1)));
               var10000 = "unknown attempt for stage " + stageId + ".  Found attempts: [" + ids.mkString(",") + "]";
            } else {
               var10000 = "unknown stage: " + stageId;
            }

            String msg = var10000;
            throw new NotFoundException(msg);
         }
      });
   }

   @GET
   @Path("{stageId: \\d+}/{stageAttemptId: \\d+}/taskSummary")
   public TaskMetricDistributions taskSummary(@PathParam("stageId") final int stageId, @PathParam("stageAttemptId") final int stageAttemptId, @DefaultValue("0.05,0.25,0.5,0.75,0.95") @QueryParam("quantiles") final String quantileString) {
      return (TaskMetricDistributions)this.withUI((ui) -> {
         double[] quantiles = this.parseQuantileString(quantileString);
         return (TaskMetricDistributions)ui.store().taskSummary(stageId, stageAttemptId, quantiles).getOrElse(() -> {
            throw new NotFoundException("No tasks reported metrics for " + stageId + " / " + stageAttemptId + " yet.");
         });
      });
   }

   @GET
   @Path("{stageId: \\d+}/{stageAttemptId: \\d+}/taskList")
   public Seq taskList(@PathParam("stageId") final int stageId, @PathParam("stageAttemptId") final int stageAttemptId, @DefaultValue("0") @QueryParam("offset") final int offset, @DefaultValue("20") @QueryParam("length") final int length, @DefaultValue("ID") @QueryParam("sortBy") final TaskSorting sortBy, @QueryParam("status") final List statuses) {
      return (Seq)this.withUI((x$2) -> x$2.store().taskList(stageId, stageAttemptId, offset, length, sortBy, statuses));
   }

   @GET
   @Path("{stageId: \\d+}/{stageAttemptId: \\d+}/taskTable")
   public HashMap taskTable(@PathParam("stageId") final int stageId, @PathParam("stageAttemptId") final int stageAttemptId, @QueryParam("details") @DefaultValue("true") final boolean details, @Context final UriInfo uriInfo) {
      return (HashMap)this.withUI((ui) -> {
         MultivaluedStringMap uriQueryParameters = UIUtils$.MODULE$.decodeURLParameter(uriInfo.getQueryParameters(true));
         String totalRecords = (String)uriQueryParameters.getFirst("numTasks");
         boolean isSearch = false;
         String searchValue = null;
         String filteredRecords = totalRecords;
         if (uriQueryParameters.getFirst("search[value]") != null && ((String)uriQueryParameters.getFirst("search[value]")).length() > 0) {
            isSearch = true;
            searchValue = (String)uriQueryParameters.getFirst("search[value]");
         }

         Seq _tasksToShow = this.doPagination(uriQueryParameters, stageId, stageAttemptId, isSearch, .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(totalRecords)));
         HashMap ret = new HashMap();
         if (_tasksToShow.nonEmpty()) {
            if (isSearch) {
               Seq filteredTaskList = this.filterTaskList(_tasksToShow, searchValue);
               filteredRecords = Integer.toString(filteredTaskList.length());
               if (filteredTaskList.length() > 0) {
                  int pageStartIndex = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)uriQueryParameters.getFirst("start")));
                  int pageLength = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)uriQueryParameters.getFirst("length")));
                  ret.put("aaData", filteredTaskList.slice(pageStartIndex, pageStartIndex + pageLength));
               } else {
                  ret.put("aaData", filteredTaskList);
               }
            } else {
               ret.put("aaData", _tasksToShow);
            }
         } else {
            ret.put("aaData", _tasksToShow);
         }

         ret.put("recordsTotal", totalRecords);
         ret.put("recordsFiltered", filteredRecords);
         return ret;
      });
   }

   public Seq doPagination(final MultivaluedMap queryParameters, final int stageId, final int stageAttemptId, final boolean isSearch, final int totalRecords) {
      ObjectRef columnNameToSort = ObjectRef.create((String)queryParameters.getFirst("columnNameToSort"));
      if (((String)columnNameToSort.elem).equalsIgnoreCase("Logs")) {
         columnNameToSort.elem = "Index";
      }

      String isAscendingStr = (String)queryParameters.getFirst("order[0][dir]");
      IntRef pageStartIndex = IntRef.create(0);
      IntRef pageLength = IntRef.create(totalRecords);
      if (!isSearch) {
         pageStartIndex.elem = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)queryParameters.getFirst("start")));
         pageLength.elem = .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString((String)queryParameters.getFirst("length")));
      }

      return (Seq)this.withUI((x$3) -> x$3.store().taskList(stageId, stageAttemptId, pageStartIndex.elem, pageLength.elem, ApiHelper$.MODULE$.indexName((String)columnNameToSort.elem), "asc".equalsIgnoreCase(isAscendingStr), x$3.store().taskList$default$7()));
   }

   public Seq filterTaskList(final Seq taskDataList, final String searchValue) {
      String defaultOptionString = "d";
      String searchValueLowerCase = searchValue.toLowerCase(Locale.ROOT);
      Function1 containsValue = (taskDataParams) -> BoxesRunTime.boxToBoolean($anonfun$filterTaskList$1(searchValueLowerCase, taskDataParams));
      Function1 taskMetricsContainsValue = (task) -> BoxesRunTime.boxToBoolean($anonfun$filterTaskList$2(containsValue, task));
      Seq filteredTaskDataSequence = (Seq)taskDataList.filter((f) -> BoxesRunTime.boxToBoolean($anonfun$filterTaskList$3(containsValue, defaultOptionString, taskMetricsContainsValue, f)));
      return filteredTaskDataSequence;
   }

   public double[] parseQuantileString(final String quantileString) {
      return (double[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])quantileString.split(",")), (s) -> BoxesRunTime.boxToDouble($anonfun$parseQuantileString$1(s)), scala.reflect.ClassTag..MODULE$.Double());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stageList$3(final StageData stage$1, final TaskStatus x0$1) {
      if (TaskStatus.FAILED.equals(x0$1)) {
         return stage$1.numFailedTasks() > 0;
      } else if (TaskStatus.KILLED.equals(x0$1)) {
         return stage$1.numKilledTasks() > 0;
      } else if (TaskStatus.RUNNING.equals(x0$1)) {
         return stage$1.numActiveTasks() > 0;
      } else if (TaskStatus.SUCCESS.equals(x0$1)) {
         return stage$1.numCompleteTasks() > 0;
      } else if (TaskStatus.UNKNOWN.equals(x0$1)) {
         return stage$1.numTasks() - stage$1.numFailedTasks() - stage$1.numKilledTasks() - stage$1.numActiveTasks() - stage$1.numCompleteTasks() > 0;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$stageList$2(final boolean details$1, final List taskStatus$1, final StageData stage) {
      return details$1 && scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(taskStatus$1).asScala().nonEmpty() ? scala.jdk.CollectionConverters..MODULE$.ListHasAsScala(taskStatus$1).asScala().exists((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$stageList$3(stage, x0$1))) : true;
   }

   // $FF: synthetic method
   public static final int $anonfun$oneAttemptData$2(final StageData x$1) {
      return x$1.attemptId();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterTaskList$1(final String searchValueLowerCase$1, final Object taskDataParams) {
      return taskDataParams.toString().toLowerCase(Locale.ROOT).contains(searchValueLowerCase$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterTaskList$2(final Function1 containsValue$1, final TaskData task) {
      Option var3 = task.taskMetrics();
      if (scala.None..MODULE$.equals(var3)) {
         return false;
      } else if (!(var3 instanceof Some)) {
         throw new MatchError(var3);
      } else {
         return BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(((TaskMetrics)task.taskMetrics().get()).executorDeserializeTime()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(((TaskMetrics)task.taskMetrics().get()).executorRunTime()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(((TaskMetrics)task.taskMetrics().get()).jvmGcTime()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(((TaskMetrics)task.taskMetrics().get()).resultSerializationTime()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).memoryBytesSpilled()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).diskBytesSpilled()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).peakExecutionMemory()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).inputMetrics().bytesRead()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToLong(((TaskMetrics)task.taskMetrics().get()).inputMetrics().recordsRead()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).outputMetrics().bytesWritten()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToLong(((TaskMetrics)task.taskMetrics().get()).outputMetrics().recordsWritten()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(((TaskMetrics)task.taskMetrics().get()).shuffleReadMetrics().fetchWaitTime()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).shuffleReadMetrics().remoteBytesRead()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).shuffleReadMetrics().localBytesRead() + ((TaskMetrics)task.taskMetrics().get()).shuffleReadMetrics().remoteBytesRead()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToLong(((TaskMetrics)task.taskMetrics().get()).shuffleReadMetrics().recordsRead()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(Utils$.MODULE$.bytesToString(((TaskMetrics)task.taskMetrics().get()).shuffleWriteMetrics().bytesWritten()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToLong(((TaskMetrics)task.taskMetrics().get()).shuffleWriteMetrics().recordsWritten()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(((TaskMetrics)task.taskMetrics().get()).shuffleWriteMetrics().writeTime() / 1000000L)));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$filterTaskList$3(final Function1 containsValue$1, final String defaultOptionString$1, final Function1 taskMetricsContainsValue$1, final TaskData f) {
      return BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToLong(f.taskId()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToInteger(f.index()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToInteger(f.attempt()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDate(f.launchTime()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(f.resultFetchStart().getOrElse(() -> defaultOptionString$1))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(f.executorId())) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(f.host())) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(f.status())) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(f.taskLocality())) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(BoxesRunTime.boxToBoolean(f.speculative()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(f.errorMessage().getOrElse(() -> defaultOptionString$1))) || BoxesRunTime.unboxToBoolean(taskMetricsContainsValue$1.apply(f)) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(f.schedulerDelay()))) || BoxesRunTime.unboxToBoolean(containsValue$1.apply(UIUtils$.MODULE$.formatDuration(f.gettingResultTime())));
   }

   // $FF: synthetic method
   public static final double $anonfun$parseQuantileString$1(final String s) {
      try {
         return .MODULE$.toDouble$extension(scala.Predef..MODULE$.augmentString(s));
      } catch (NumberFormatException var2) {
         throw new BadParameterException("quantiles", "double", s);
      }
   }

   public StagesResource() {
      ApiRequestContext.$init$(this);
      BaseAppResource.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
