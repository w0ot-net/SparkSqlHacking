package org.apache.spark.ui.jobs;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.status.AppStatusStore;
import org.apache.spark.util.Utils$;
import scala.collection.StringOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.xml.Elem;
import scala.xml.MetaData;
import scala.xml.NodeBuffer;
import scala.xml.NodeSeq;
import scala.xml.Null;
import scala.xml.Text;
import scala.xml.TopScope;
import scala.xml.UnprefixedAttribute;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a!\u0002\n\u0014\u0001Ui\u0002\u0002\u0003\u0013\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u00111\u0002!\u0011!Q\u0001\n5B\u0001b\u000e\u0001\u0003\u0002\u0003\u0006I\u0001\u000f\u0005\t\u0019\u0002\u0011\t\u0011)A\u0005\u001b\"AQ\u000b\u0001B\u0001B\u0003%Q\n\u0003\u0005W\u0001\t\u0005\t\u0015!\u0003N\u0011!9\u0006A!A!\u0002\u0013i\u0005\u0002\u0003-\u0001\u0005\u0003\u0005\u000b\u0011B-\t\u0011q\u0003!\u0011!Q\u0001\neC\u0001\"\u0018\u0001\u0003\u0002\u0003\u0006I!\u0017\u0005\u0006=\u0002!\ta\u0018\u0005\bY\u0002\u0011\r\u0011\"\u0001n\u0011\u0019\t\b\u0001)A\u0005]\"9!\u000f\u0001b\u0001\n\u0003\u0019\bBB<\u0001A\u0003%A\u000fC\u0004y\u0001\t\u0007I\u0011A=\t\u000f\u0005\r\u0001\u0001)A\u0005u\nq1\u000b^1hKR\u000b'\r\\3CCN,'B\u0001\u000b\u0016\u0003\u0011QwNY:\u000b\u0005Y9\u0012AA;j\u0015\tA\u0012$A\u0003ta\u0006\u00148N\u0003\u0002\u001b7\u00051\u0011\r]1dQ\u0016T\u0011\u0001H\u0001\u0004_J<7C\u0001\u0001\u001f!\ty\"%D\u0001!\u0015\u0005\t\u0013!B:dC2\f\u0017BA\u0012!\u0005\u0019\te.\u001f*fM\u0006)1\u000f^8sK\u000e\u0001\u0001CA\u0014+\u001b\u0005A#BA\u0015\u0018\u0003\u0019\u0019H/\u0019;vg&\u00111\u0006\u000b\u0002\u000f\u0003B\u00048\u000b^1ukN\u001cFo\u001c:f\u0003\u001d\u0011X-];fgR\u0004\"AL\u001b\u000e\u0003=R!\u0001M\u0019\u0002\t!$H\u000f\u001d\u0006\u0003eM\nqa]3sm2,GOC\u00015\u0003\u001dQ\u0017m[1si\u0006L!AN\u0018\u0003%!#H\u000f]*feZdW\r\u001e*fcV,7\u000f^\u0001\u0007gR\fw-Z:\u0011\u0007e\nEI\u0004\u0002;\u007f9\u00111HP\u0007\u0002y)\u0011Q(J\u0001\u0007yI|w\u000e\u001e \n\u0003\u0005J!\u0001\u0011\u0011\u0002\u000fA\f7m[1hK&\u0011!i\u0011\u0002\u0004'\u0016\f(B\u0001!!!\t)%*D\u0001G\u0015\t9\u0005*\u0001\u0002wc)\u0011\u0011\nK\u0001\u0004CBL\u0017BA&G\u0005%\u0019F/Y4f\t\u0006$\u0018-A\u0007uC\ndW\rS3bI\u0016\u0014\u0018\n\u0012\t\u0003\u001dJs!a\u0014)\u0011\u0005m\u0002\u0013BA)!\u0003\u0019\u0001&/\u001a3fM&\u00111\u000b\u0016\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005E\u0003\u0013\u0001C:uC\u001e,G+Y4\u0002\u0011\t\f7/\u001a)bi\"\fqa];c!\u0006$\b.A\bjg\u001a\u000b\u0017N]*dQ\u0016$W\u000f\\3s!\ty\",\u0003\u0002\\A\t9!i\\8mK\u0006t\u0017aC6jY2,e.\u00192mK\u0012\fQ\"[:GC&dW\rZ*uC\u001e,\u0017A\u0002\u001fj]&$h\bF\u0006aE\u000e$WMZ4iS*\\\u0007CA1\u0001\u001b\u0005\u0019\u0002\"\u0002\u0013\f\u0001\u00041\u0003\"\u0002\u0017\f\u0001\u0004i\u0003\"B\u001c\f\u0001\u0004A\u0004\"\u0002'\f\u0001\u0004i\u0005\"B+\f\u0001\u0004i\u0005\"\u0002,\f\u0001\u0004i\u0005\"B,\f\u0001\u0004i\u0005\"\u0002-\f\u0001\u0004I\u0006\"\u0002/\f\u0001\u0004I\u0006\"B/\f\u0001\u0004I\u0016!C:uC\u001e,\u0007+Y4f+\u0005q\u0007CA\u0010p\u0013\t\u0001\bEA\u0002J]R\f!b\u001d;bO\u0016\u0004\u0016mZ3!\u0003-\u0019WO\u001d:f]R$\u0016.\\3\u0016\u0003Q\u0004\"aH;\n\u0005Y\u0004#\u0001\u0002'p]\u001e\fAbY;se\u0016tG\u000fV5nK\u0002\n\u0011\u0002^8O_\u0012,7+Z9\u0016\u0003i\u00042!O!|!\tax0D\u0001~\u0015\tq\b%A\u0002y[2L1!!\u0001~\u0005\u0011qu\u000eZ3\u0002\u0015Q|gj\u001c3f'\u0016\f\b\u0005"
)
public class StageTableBase {
   private final AppStatusStore store;
   private final HttpServletRequest request;
   private final Seq stages;
   private final String tableHeaderID;
   private final String stageTag;
   private final String basePath;
   private final String subPath;
   private final boolean isFairScheduler;
   private final boolean killEnabled;
   private final boolean isFailedStage;
   private final int stagePage;
   private final long currentTime;
   private final Seq toNodeSeq;

   public int stagePage() {
      return this.stagePage;
   }

   public long currentTime() {
      return this.currentTime;
   }

   public Seq toNodeSeq() {
      return this.toNodeSeq;
   }

   // $FF: synthetic method
   public static final int $anonfun$stagePage$1(final String x$1) {
      return .MODULE$.toInt$extension(scala.Predef..MODULE$.augmentString(x$1));
   }

   // $FF: synthetic method
   private final Seq liftedTree1$1() {
      Object var10000;
      try {
         var10000 = (new StagePagedTable(this.store, this.stages, this.tableHeaderID, this.stageTag, this.basePath, this.subPath, this.isFairScheduler, this.killEnabled, this.currentTime(), this.isFailedStage, this.request)).table(this.stagePage());
      } catch (Throwable var9) {
         if (!(var9 instanceof IllegalArgumentException ? true : var9 instanceof IndexOutOfBoundsException)) {
            throw var9;
         }

         MetaData $md = scala.xml.Null..MODULE$;
         MetaData var10 = new UnprefixedAttribute("class", new Text("alert alert-error"), $md);
         TopScope var10005 = scala.xml.TopScope..MODULE$;
         NodeSeq var10007 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n        "));
         Null var10013 = scala.xml.Null..MODULE$;
         TopScope var10014 = scala.xml.TopScope..MODULE$;
         NodeSeq var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("Error while rendering stage table:"));
         $buf.$amp$plus(new Elem((String)null, "p", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n        "));
         var10013 = scala.xml.Null..MODULE$;
         var10014 = scala.xml.TopScope..MODULE$;
         var10016 = scala.xml.NodeSeq..MODULE$;
         NodeBuffer $buf = new NodeBuffer();
         $buf.$amp$plus(new Text("\n          "));
         $buf.$amp$plus(Utils$.MODULE$.exceptionString(var9));
         $buf.$amp$plus(new Text("\n        "));
         $buf.$amp$plus(new Elem((String)null, "pre", var10013, var10014, false, var10016.seqToNodeSeq($buf)));
         $buf.$amp$plus(new Text("\n      "));
         var10000 = new Elem((String)null, "div", var10, var10005, false, var10007.seqToNodeSeq($buf));
      }

      return (Seq)var10000;
   }

   public StageTableBase(final AppStatusStore store, final HttpServletRequest request, final Seq stages, final String tableHeaderID, final String stageTag, final String basePath, final String subPath, final boolean isFairScheduler, final boolean killEnabled, final boolean isFailedStage) {
      this.store = store;
      this.request = request;
      this.stages = stages;
      this.tableHeaderID = tableHeaderID;
      this.stageTag = stageTag;
      this.basePath = basePath;
      this.subPath = subPath;
      this.isFairScheduler = isFairScheduler;
      this.killEnabled = killEnabled;
      this.isFailedStage = isFailedStage;
      this.stagePage = BoxesRunTime.unboxToInt(scala.Option..MODULE$.apply(request.getParameter(stageTag + ".page")).map((x$1) -> BoxesRunTime.boxToInteger($anonfun$stagePage$1(x$1))).getOrElse((JFunction0.mcI.sp)() -> 1));
      this.currentTime = System.currentTimeMillis();
      this.toNodeSeq = this.liftedTree1$1();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
