package org.apache.spark.deploy;

import org.apache.spark.deploy.master.ApplicationInfo;
import org.apache.spark.deploy.master.DriverInfo;
import org.apache.spark.deploy.master.WorkerInfo;
import org.apache.spark.deploy.worker.ExecutorRunner;
import org.json4s.JObject;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}rA\u0002\b\u0010\u0011\u0003yqC\u0002\u0004\u001a\u001f!\u0005qB\u0007\u0005\u0006C\u0005!\ta\t\u0005\u0006I\u0005!I!\n\u0005\u0006\u0013\u0006!IA\u0013\u0005\u0006!\u0006!\t!\u0015\u0005\u00065\u0006!\ta\u0017\u0005\u0006A\u0006!\t!\u0019\u0005\u0006M\u0006!\ta\u001a\u0005\u0006_\u0006!\t\u0001\u001d\u0005\u0006k\u0006!\tA\u001e\u0005\n\u0003+\t\u0011\u0013!C\u0001\u0003/Aq!!\f\u0002\t\u0003\ty\u0003C\u0004\u0002:\u0005!\t!a\u000f\u0002\u0019)\u001bxN\u001c)s_R|7m\u001c7\u000b\u0005A\t\u0012A\u00023fa2|\u0017P\u0003\u0002\u0013'\u0005)1\u000f]1sW*\u0011A#F\u0001\u0007CB\f7\r[3\u000b\u0003Y\t1a\u001c:h!\tA\u0012!D\u0001\u0010\u00051Q5o\u001c8Qe>$xnY8m'\t\t1\u0004\u0005\u0002\u001d?5\tQDC\u0001\u001f\u0003\u0015\u00198-\u00197b\u0013\t\u0001SD\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\tq#\u0001\nxe&$XMU3t_V\u00148-Z:J]\u001a|GC\u0001\u00147!\t93G\u0004\u0002)a9\u0011\u0011F\f\b\u0003U5j\u0011a\u000b\u0006\u0003Y\t\na\u0001\u0010:p_Rt\u0014\"\u0001\f\n\u0005=*\u0012A\u00026t_:$4/\u0003\u00022e\u00059!j]8o\u0003N#&BA\u0018\u0016\u0013\t!TGA\u0004K\u001f\nTWm\u0019;\u000b\u0005E\u0012\u0004\"B\u001c\u0004\u0001\u0004A\u0014\u0001B5oM>\u0004B!O\u001fA\u0007:\u0011!h\u000f\t\u0003UuI!\u0001P\u000f\u0002\rA\u0013X\rZ3g\u0013\tqtHA\u0002NCBT!\u0001P\u000f\u0011\u0005e\n\u0015B\u0001\"@\u0005\u0019\u0019FO]5oOB\u0011AiR\u0007\u0002\u000b*\u0011a)E\u0001\te\u0016\u001cx.\u001e:dK&\u0011\u0001*\u0012\u0002\u0014%\u0016\u001cx.\u001e:dK&sgm\u001c:nCRLwN\\\u0001\u0019oJLG/\u001a*fg>,(oY3SKF,\u0018N]3nK:$HC\u0001\u0014L\u0011\u0015aE\u00011\u0001N\u0003\r\u0011X-\u001d\t\u0003\t:K!aT#\u0003'I+7o\\;sG\u0016\u0014V-];je\u0016lWM\u001c;\u0002\u001f]\u0014\u0018\u000e^3X_J\\WM]%oM>$\"A\n*\t\u000bM+\u0001\u0019\u0001+\u0002\u0007=\u0014'\u000e\u0005\u0002V16\taK\u0003\u0002X\u001f\u00051Q.Y:uKJL!!\u0017,\u0003\u0015]{'o[3s\u0013:4w.\u0001\u000bxe&$X-\u00119qY&\u001c\u0017\r^5p]&sgm\u001c\u000b\u0003MqCQa\u0015\u0004A\u0002u\u0003\"!\u00160\n\u0005}3&aD!qa2L7-\u0019;j_:LeNZ8\u00027]\u0014\u0018\u000e^3BaBd\u0017nY1uS>tG)Z:de&\u0004H/[8o)\t1#\rC\u0003T\u000f\u0001\u00071\r\u0005\u0002\u0019I&\u0011Qm\u0004\u0002\u0017\u0003B\u0004H.[2bi&|g\u000eR3tGJL\u0007\u000f^5p]\u0006\u0019rO]5uK\u0016CXmY;u_J\u0014VO\u001c8feR\u0011a\u0005\u001b\u0005\u0006'\"\u0001\r!\u001b\t\u0003U6l\u0011a\u001b\u0006\u0003Y>\taa^8sW\u0016\u0014\u0018B\u00018l\u00059)\u00050Z2vi>\u0014(+\u001e8oKJ\fqb\u001e:ji\u0016$%/\u001b<fe&sgm\u001c\u000b\u0003MEDQaU\u0005A\u0002I\u0004\"!V:\n\u0005Q4&A\u0003#sSZ,'/\u00138g_\u0006\u0001rO]5uK6\u000b7\u000f^3s'R\fG/\u001a\u000b\u0005M]\fY\u0001C\u0003T\u0015\u0001\u0007\u0001\u0010E\u0002z\u0003\u000bq1A_A\u0001\u001d\tYxP\u0004\u0002}}:\u0011\u0011&`\u0005\u0003)UI!AE\n\n\u0005A\t\u0012bAA\u0002\u001f\u0005qA)\u001a9m_flUm]:bO\u0016\u001c\u0018\u0002BA\u0004\u0003\u0013\u00111#T1ti\u0016\u00148\u000b^1uKJ+7\u000f]8og\u0016T1!a\u0001\u0010\u0011%\tiA\u0003I\u0001\u0002\u0004\ty!A\u0003gS\u0016dG\r\u0005\u0003\u001d\u0003#\u0001\u0015bAA\n;\t1q\n\u001d;j_:\f!d\u001e:ji\u0016l\u0015m\u001d;feN#\u0018\r^3%I\u00164\u0017-\u001e7uII*\"!!\u0007+\t\u0005=\u00111D\u0016\u0003\u0003;\u0001B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#A\u0005v]\u000eDWmY6fI*\u0019\u0011qE\u000f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002,\u0005\u0005\"!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006\u0001rO]5uK^{'o[3s'R\fG/\u001a\u000b\u0004M\u0005E\u0002BB*\r\u0001\u0004\t\u0019\u0004E\u0002z\u0003kIA!a\u000e\u0002\n\t\u0019rk\u001c:lKJ\u001cF/\u0019;f%\u0016\u001c\bo\u001c8tK\u00069rO]5uK\u000ecWo\u001d;feV#\u0018\u000e\\5{CRLwN\u001c\u000b\u0004M\u0005u\u0002\"B*\u000e\u0001\u0004A\b"
)
public final class JsonProtocol {
   public static JObject writeClusterUtilization(final DeployMessages.MasterStateResponse obj) {
      return JsonProtocol$.MODULE$.writeClusterUtilization(obj);
   }

   public static JObject writeWorkerState(final DeployMessages.WorkerStateResponse obj) {
      return JsonProtocol$.MODULE$.writeWorkerState(obj);
   }

   public static Option writeMasterState$default$2() {
      return JsonProtocol$.MODULE$.writeMasterState$default$2();
   }

   public static JObject writeMasterState(final DeployMessages.MasterStateResponse obj, final Option field) {
      return JsonProtocol$.MODULE$.writeMasterState(obj, field);
   }

   public static JObject writeDriverInfo(final DriverInfo obj) {
      return JsonProtocol$.MODULE$.writeDriverInfo(obj);
   }

   public static JObject writeExecutorRunner(final ExecutorRunner obj) {
      return JsonProtocol$.MODULE$.writeExecutorRunner(obj);
   }

   public static JObject writeApplicationDescription(final ApplicationDescription obj) {
      return JsonProtocol$.MODULE$.writeApplicationDescription(obj);
   }

   public static JObject writeApplicationInfo(final ApplicationInfo obj) {
      return JsonProtocol$.MODULE$.writeApplicationInfo(obj);
   }

   public static JObject writeWorkerInfo(final WorkerInfo obj) {
      return JsonProtocol$.MODULE$.writeWorkerInfo(obj);
   }
}
