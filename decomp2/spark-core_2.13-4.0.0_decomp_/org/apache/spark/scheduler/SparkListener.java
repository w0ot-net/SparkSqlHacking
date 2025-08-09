package org.apache.spark.scheduler;

import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\t]d!B\u0013'\u0003\u0003y\u0003\"\u0002\u001e\u0001\t\u0003Y\u0004\"B\u001f\u0001\t\u0003r\u0004\"B$\u0001\t\u0003B\u0005\"\u0002(\u0001\t\u0003z\u0005\"B+\u0001\t\u00032\u0006\"\u0002/\u0001\t\u0003j\u0006\"B2\u0001\t\u0003\"\u0007\"\u00026\u0001\t\u0003Z\u0007\"B9\u0001\t\u0003\u0012\b\"\u0002=\u0001\t\u0003J\bBB@\u0001\t\u0003\n\t\u0001C\u0004\u0002\u000e\u0001!\t%a\u0004\t\u000f\u0005m\u0001\u0001\"\u0011\u0002\u001e!9\u0011\u0011\u0006\u0001\u0005B\u0005-\u0002bBA\u001c\u0001\u0011\u0005\u0013\u0011\b\u0005\b\u0003\u000b\u0002A\u0011IA$\u0011\u001d\t\u0019\u0006\u0001C!\u0003+Bq!!\u0019\u0001\t\u0003\n\u0019\u0007C\u0004\u0002p\u0001!\t%!\u001d\t\u000f\u0005u\u0004\u0001\"\u0011\u0002\u0000!9\u00111\u0012\u0001\u0005B\u00055\u0005bBAM\u0001\u0011\u0005\u00131\u0014\u0005\b\u0003O\u0003A\u0011IAU\u0011\u001d\t)\f\u0001C!\u0003oCq!a1\u0001\t\u0003\n)\rC\u0004\u0002R\u0002!\t%a5\t\u000f\u0005}\u0007\u0001\"\u0011\u0002b\"9\u0011Q\u001e\u0001\u0005B\u0005=\bbBA~\u0001\u0011\u0005\u0013Q \u0005\b\u0005\u0013\u0001A\u0011\tB\u0006\u0011\u001d\u00119\u0002\u0001C!\u00053AqA!\n\u0001\t\u0003\u00129\u0003C\u0004\u00034\u0001!\tE!\u000e\t\u000f\t\u0005\u0003\u0001\"\u0011\u0003D!9!q\n\u0001\u0005B\tE\u0003b\u0002B/\u0001\u0011\u0005#q\f\u0002\u000e'B\f'o\u001b'jgR,g.\u001a:\u000b\u0005\u001dB\u0013!C:dQ\u0016$W\u000f\\3s\u0015\tI#&A\u0003ta\u0006\u00148N\u0003\u0002,Y\u00051\u0011\r]1dQ\u0016T\u0011!L\u0001\u0004_J<7\u0001A\n\u0004\u0001A2\u0004CA\u00195\u001b\u0005\u0011$\"A\u001a\u0002\u000bM\u001c\u0017\r\\1\n\u0005U\u0012$AB!osJ+g\r\u0005\u00028q5\ta%\u0003\u0002:M\t12\u000b]1sW2K7\u000f^3oKJLe\u000e^3sM\u0006\u001cW-\u0001\u0004=S:LGO\u0010\u000b\u0002yA\u0011q\u0007A\u0001\u0011_:\u001cF/Y4f\u0007>l\u0007\u000f\\3uK\u0012$\"a\u0010\"\u0011\u0005E\u0002\u0015BA!3\u0005\u0011)f.\u001b;\t\u000b\r\u0013\u0001\u0019\u0001#\u0002\u001dM$\u0018mZ3D_6\u0004H.\u001a;fIB\u0011q'R\u0005\u0003\r\u001a\u00121d\u00159be.d\u0015n\u001d;f]\u0016\u00148\u000b^1hK\u000e{W\u000e\u001d7fi\u0016$\u0017\u0001E8o'R\fw-Z*vE6LG\u000f^3e)\ty\u0014\nC\u0003K\u0007\u0001\u00071*\u0001\bti\u0006<WmU;c[&$H/\u001a3\u0011\u0005]b\u0015BA''\u0005m\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8feN#\u0018mZ3Tk\nl\u0017\u000e\u001e;fI\u0006YqN\u001c+bg.\u001cF/\u0019:u)\ty\u0004\u000bC\u0003R\t\u0001\u0007!+A\u0005uCN\\7\u000b^1siB\u0011qgU\u0005\u0003)\u001a\u0012ac\u00159be.d\u0015n\u001d;f]\u0016\u0014H+Y:l'R\f'\u000f^\u0001\u0014_:$\u0016m]6HKR$\u0018N\\4SKN,H\u000e\u001e\u000b\u0003\u007f]CQ\u0001W\u0003A\u0002e\u000b\u0011\u0003^1tW\u001e+G\u000f^5oOJ+7/\u001e7u!\t9$,\u0003\u0002\\M\tq2\u000b]1sW2K7\u000f^3oKJ$\u0016m]6HKR$\u0018N\\4SKN,H\u000e^\u0001\n_:$\u0016m]6F]\u0012$\"a\u00100\t\u000b}3\u0001\u0019\u00011\u0002\u000fQ\f7o[#oIB\u0011q'Y\u0005\u0003E\u001a\u0012Ac\u00159be.d\u0015n\u001d;f]\u0016\u0014H+Y:l\u000b:$\u0017AC8o\u0015>\u00147\u000b^1siR\u0011q(\u001a\u0005\u0006M\u001e\u0001\raZ\u0001\tU>\u00147\u000b^1siB\u0011q\u0007[\u0005\u0003S\u001a\u0012Qc\u00159be.d\u0015n\u001d;f]\u0016\u0014(j\u001c2Ti\u0006\u0014H/\u0001\u0005p]*{'-\u00128e)\tyD\u000eC\u0003n\u0011\u0001\u0007a.\u0001\u0004k_\n,e\u000e\u001a\t\u0003o=L!\u0001\u001d\u0014\u0003'M\u0003\u0018M]6MSN$XM\\3s\u0015>\u0014WI\u001c3\u0002'=tWI\u001c<je>tW.\u001a8u+B$\u0017\r^3\u0015\u0005}\u001a\b\"\u0002;\n\u0001\u0004)\u0018!E3om&\u0014xN\\7f]R,\u0006\u000fZ1uKB\u0011qG^\u0005\u0003o\u001a\u0012ad\u00159be.d\u0015n\u001d;f]\u0016\u0014XI\u001c<je>tW.\u001a8u+B$\u0017\r^3\u0002'=t'\t\\8dW6\u000bg.Y4fe\u0006#G-\u001a3\u0015\u0005}R\b\"B>\u000b\u0001\u0004a\u0018!\u00052m_\u000e\\W*\u00198bO\u0016\u0014\u0018\t\u001a3fIB\u0011q'`\u0005\u0003}\u001a\u0012ad\u00159be.d\u0015n\u001d;f]\u0016\u0014(\t\\8dW6\u000bg.Y4fe\u0006#G-\u001a3\u0002+=t'\t\\8dW6\u000bg.Y4feJ+Wn\u001c<fIR\u0019q(a\u0001\t\u000f\u0005\u00151\u00021\u0001\u0002\b\u0005\u0019\"\r\\8dW6\u000bg.Y4feJ+Wn\u001c<fIB\u0019q'!\u0003\n\u0007\u0005-aE\u0001\u0011Ta\u0006\u00148\u000eT5ti\u0016tWM\u001d\"m_\u000e\\W*\u00198bO\u0016\u0014(+Z7pm\u0016$\u0017AD8o+:\u0004XM]:jgR\u0014F\t\u0012\u000b\u0004\u007f\u0005E\u0001bBA\n\u0019\u0001\u0007\u0011QC\u0001\rk:\u0004XM]:jgR\u0014F\t\u0012\t\u0004o\u0005]\u0011bAA\rM\tI2\u000b]1sW2K7\u000f^3oKJ,f\u000e]3sg&\u001cHO\u0015#E\u0003Iyg.\u00119qY&\u001c\u0017\r^5p]N#\u0018M\u001d;\u0015\u0007}\ny\u0002C\u0004\u0002\"5\u0001\r!a\t\u0002!\u0005\u0004\b\u000f\\5dCRLwN\\*uCJ$\bcA\u001c\u0002&%\u0019\u0011q\u0005\u0014\u0003;M\u0003\u0018M]6MSN$XM\\3s\u0003B\u0004H.[2bi&|gn\u0015;beR\f\u0001c\u001c8BaBd\u0017nY1uS>tWI\u001c3\u0015\u0007}\ni\u0003C\u0004\u000209\u0001\r!!\r\u0002\u001d\u0005\u0004\b\u000f\\5dCRLwN\\#oIB\u0019q'a\r\n\u0007\u0005UbEA\u000eTa\u0006\u00148\u000eT5ti\u0016tWM]!qa2L7-\u0019;j_:,e\u000eZ\u0001\u0018_:,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001cX\u000b\u001d3bi\u0016$2aPA\u001e\u0011\u001d\tid\u0004a\u0001\u0003\u007f\tQ#\u001a=fGV$xN]'fiJL7m]+qI\u0006$X\rE\u00028\u0003\u0003J1!a\u0011'\u0005\t\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_JlU\r\u001e:jGN,\u0006\u000fZ1uK\u00061rN\\*uC\u001e,W\t_3dkR|'/T3ue&\u001c7\u000fF\u0002@\u0003\u0013Bq!a\u0013\u0011\u0001\u0004\ti%A\bfq\u0016\u001cW\u000f^8s\u001b\u0016$(/[2t!\r9\u0014qJ\u0005\u0004\u0003#2#!I*qCJ\\G*[:uK:,'o\u0015;bO\u0016,\u00050Z2vi>\u0014X*\u001a;sS\u000e\u001c\u0018aD8o\u000bb,7-\u001e;pe\u0006#G-\u001a3\u0015\u0007}\n9\u0006C\u0004\u0002ZE\u0001\r!a\u0017\u0002\u001b\u0015DXmY;u_J\fE\rZ3e!\r9\u0014QL\u0005\u0004\u0003?2#AG*qCJ\\G*[:uK:,'/\u0012=fGV$xN]!eI\u0016$\u0017!E8o\u000bb,7-\u001e;peJ+Wn\u001c<fIR\u0019q(!\u001a\t\u000f\u0005\u001d$\u00031\u0001\u0002j\u0005yQ\r_3dkR|'OU3n_Z,G\rE\u00028\u0003WJ1!!\u001c'\u0005q\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J\u0014V-\\8wK\u0012\fQc\u001c8Fq\u0016\u001cW\u000f^8s\u00052\f7m\u001b7jgR,G\rF\u0002@\u0003gBq!!\u001e\u0014\u0001\u0004\t9(A\nfq\u0016\u001cW\u000f^8s\u00052\f7m\u001b7jgR,G\rE\u00028\u0003sJ1!a\u001f'\u0005\u0001\u001a\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J\u0014E.Y2lY&\u001cH/\u001a3\u0002%=tW\t_3dkR|'/\u0012=dYV$W\r\u001a\u000b\u0004\u007f\u0005\u0005\u0005bBAB)\u0001\u0007\u0011QQ\u0001\u0011Kb,7-\u001e;pe\u0016C8\r\\;eK\u0012\u00042aNAD\u0013\r\tII\n\u0002\u001e'B\f'o\u001b'jgR,g.\u001a:Fq\u0016\u001cW\u000f^8s\u000bb\u001cG.\u001e3fI\u0006irN\\#yK\u000e,Ho\u001c:CY\u0006\u001c7\u000e\\5ti\u0016$gi\u001c:Ti\u0006<W\rF\u0002@\u0003\u001fCq!!%\u0016\u0001\u0004\t\u0019*A\u000efq\u0016\u001cW\u000f^8s\u00052\f7m\u001b7jgR,GMR8s'R\fw-\u001a\t\u0004o\u0005U\u0015bAALM\tA3\u000b]1sW2K7\u000f^3oKJ,\u00050Z2vi>\u0014(\t\\1dW2L7\u000f^3e\r>\u00148\u000b^1hK\u0006QrN\\#yK\u000e,Ho\u001c:Fq\u000edW\u000fZ3e\r>\u00148\u000b^1hKR\u0019q(!(\t\u000f\u0005}e\u00031\u0001\u0002\"\u0006AR\r_3dkR|'/\u0012=dYV$W\r\u001a$peN#\u0018mZ3\u0011\u0007]\n\u0019+C\u0002\u0002&\u001a\u0012Qe\u00159be.d\u0015n\u001d;f]\u0016\u0014X\t_3dkR|'/\u0012=dYV$W\r\u001a$peN#\u0018mZ3\u00023=tgj\u001c3f\u00052\f7m\u001b7jgR,GMR8s'R\fw-\u001a\u000b\u0004\u007f\u0005-\u0006bBAW/\u0001\u0007\u0011qV\u0001\u0018]>$WM\u00117bG.d\u0017n\u001d;fI\u001a{'o\u0015;bO\u0016\u00042aNAY\u0013\r\t\u0019L\n\u0002%'B\f'o\u001b'jgR,g.\u001a:O_\u0012,'\t\\1dW2L7\u000f^3e\r>\u00148\u000b^1hK\u00061rN\u001c(pI\u0016,\u0005p\u00197vI\u0016$gi\u001c:Ti\u0006<W\rF\u0002@\u0003sCq!a/\u0019\u0001\u0004\ti,\u0001\u000bo_\u0012,W\t_2mk\u0012,GMR8s'R\fw-\u001a\t\u0004o\u0005}\u0016bAAaM\t\t3\u000b]1sW2K7\u000f^3oKJtu\u000eZ3Fq\u000edW\u000fZ3e\r>\u00148\u000b^1hK\u00069rN\\#yK\u000e,Ho\u001c:V]\nd\u0017mY6mSN$X\r\u001a\u000b\u0004\u007f\u0005\u001d\u0007bBAe3\u0001\u0007\u00111Z\u0001\u0016Kb,7-\u001e;peVs'\r\\1dW2L7\u000f^3e!\r9\u0014QZ\u0005\u0004\u0003\u001f4#AI*qCJ\\G*[:uK:,'/\u0012=fGV$xN]+oE2\f7m\u001b7jgR,G-\u0001\u000bp]\u0016CXmY;u_J,f.\u001a=dYV$W\r\u001a\u000b\u0004\u007f\u0005U\u0007bBAl5\u0001\u0007\u0011\u0011\\\u0001\u0013Kb,7-\u001e;peVsW\r_2mk\u0012,G\rE\u00028\u00037L1!!8'\u0005}\u0019\u0006/\u0019:l\u0019&\u001cH/\u001a8fe\u0016CXmY;u_J,f.\u001a=dYV$W\rZ\u0001\u0012_:tu\u000eZ3CY\u0006\u001c7\u000e\\5ti\u0016$GcA \u0002d\"9\u0011Q]\u000eA\u0002\u0005\u001d\u0018a\u00048pI\u0016\u0014E.Y2lY&\u001cH/\u001a3\u0011\u0007]\nI/C\u0002\u0002l\u001a\u0012Ad\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f\u00052\f7m\u001b7jgR,G-\u0001\bp]:{G-Z#yG2,H-\u001a3\u0015\u0007}\n\t\u0010C\u0004\u0002tr\u0001\r!!>\u0002\u00199|G-Z#yG2,H-\u001a3\u0011\u0007]\n90C\u0002\u0002z\u001a\u0012\u0011d\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f\u000bb\u001cG.\u001e3fI\u0006\u0019rN\u001c(pI\u0016,fN\u00197bG.d\u0017n\u001d;fIR\u0019q(a@\t\u000f\t\u0005Q\u00041\u0001\u0003\u0004\u0005\tbn\u001c3f+:\u0014G.Y2lY&\u001cH/\u001a3\u0011\u0007]\u0012)!C\u0002\u0003\b\u0019\u0012ad\u00159be.d\u0015n\u001d;f]\u0016\u0014hj\u001c3f+:\u0014G.Y2lY&\u001cH/\u001a3\u0002!=tgj\u001c3f+:,\u0007p\u00197vI\u0016$GcA \u0003\u000e!9!q\u0002\u0010A\u0002\tE\u0011A\u00048pI\u0016,f.\u001a=dYV$W\r\u001a\t\u0004o\tM\u0011b\u0001B\u000bM\tY2\u000b]1sW2K7\u000f^3oKJtu\u000eZ3V]\u0016D8\r\\;eK\u0012\f1d\u001c8V]N\u001c\u0007.\u001a3vY\u0006\u0014G.\u001a+bg.\u001cV\r^!eI\u0016$GcA \u0003\u001c!9!QD\u0010A\u0002\t}\u0011!G;og\u000eDW\rZ;mC\ndW\rV1tWN+G/\u00113eK\u0012\u00042a\u000eB\u0011\u0013\r\u0011\u0019C\n\u0002''B\f'o\u001b'jgR,g.\u001a:V]N\u001c\u0007.\u001a3vY\u0006\u0014G.\u001a+bg.\u001cV\r^!eI\u0016$\u0017!H8o+:\u001c8\r[3ek2\f'\r\\3UCN\\7+\u001a;SK6|g/\u001a3\u0015\u0007}\u0012I\u0003C\u0004\u0003,\u0001\u0002\rA!\f\u00027Ut7o\u00195fIVd\u0017M\u00197f)\u0006\u001c8nU3u%\u0016lwN^3e!\r9$qF\u0005\u0004\u0005c1#\u0001K*qCJ\\G*[:uK:,'/\u00168tG\",G-\u001e7bE2,G+Y:l'\u0016$(+Z7pm\u0016$\u0017AD8o\u00052|7m[+qI\u0006$X\r\u001a\u000b\u0004\u007f\t]\u0002b\u0002B\u001dC\u0001\u0007!1H\u0001\rE2|7m[+qI\u0006$X\r\u001a\t\u0004o\tu\u0012b\u0001B M\tI2\u000b]1sW2K7\u000f^3oKJ\u0014En\\2l+B$\u0017\r^3e\u0003iygn\u00159fGVd\u0017\r^5wKR\u000b7o[*vE6LG\u000f^3e)\ry$Q\t\u0005\b\u0005\u000f\u0012\u0003\u0019\u0001B%\u0003=\u0019\b/Z2vY\u0006$\u0018N^3UCN\\\u0007cA\u001c\u0003L%\u0019!Q\n\u0014\u0003KM\u0003\u0018M]6MSN$XM\\3s'B,7-\u001e7bi&4X\rV1tWN+(-\\5ui\u0016$\u0017\u0001D8o\u001fRDWM]#wK:$HcA \u0003T!9!QK\u0012A\u0002\t]\u0013!B3wK:$\bcA\u001c\u0003Z%\u0019!1\f\u0014\u0003%M\u0003\u0018M]6MSN$XM\\3s\u000bZ,g\u000e^\u0001\u0017_:\u0014Vm]8ve\u000e,\u0007K]8gS2,\u0017\t\u001a3fIR\u0019qH!\u0019\t\u000f\tUC\u00051\u0001\u0003dA\u0019qG!\u001a\n\u0007\t\u001ddEA\u0011Ta\u0006\u00148\u000eT5ti\u0016tWM\u001d*fg>,(oY3Qe>4\u0017\u000e\\3BI\u0012,G\rK\u0002\u0001\u0005W\u0002BA!\u001c\u0003t5\u0011!q\u000e\u0006\u0004\u0005cB\u0013AC1o]>$\u0018\r^5p]&!!Q\u000fB8\u00051!UM^3m_B,'/\u00119j\u0001"
)
public abstract class SparkListener implements SparkListenerInterface {
   public void onStageCompleted(final SparkListenerStageCompleted stageCompleted) {
   }

   public void onStageSubmitted(final SparkListenerStageSubmitted stageSubmitted) {
   }

   public void onTaskStart(final SparkListenerTaskStart taskStart) {
   }

   public void onTaskGettingResult(final SparkListenerTaskGettingResult taskGettingResult) {
   }

   public void onTaskEnd(final SparkListenerTaskEnd taskEnd) {
   }

   public void onJobStart(final SparkListenerJobStart jobStart) {
   }

   public void onJobEnd(final SparkListenerJobEnd jobEnd) {
   }

   public void onEnvironmentUpdate(final SparkListenerEnvironmentUpdate environmentUpdate) {
   }

   public void onBlockManagerAdded(final SparkListenerBlockManagerAdded blockManagerAdded) {
   }

   public void onBlockManagerRemoved(final SparkListenerBlockManagerRemoved blockManagerRemoved) {
   }

   public void onUnpersistRDD(final SparkListenerUnpersistRDD unpersistRDD) {
   }

   public void onApplicationStart(final SparkListenerApplicationStart applicationStart) {
   }

   public void onApplicationEnd(final SparkListenerApplicationEnd applicationEnd) {
   }

   public void onExecutorMetricsUpdate(final SparkListenerExecutorMetricsUpdate executorMetricsUpdate) {
   }

   public void onStageExecutorMetrics(final SparkListenerStageExecutorMetrics executorMetrics) {
   }

   public void onExecutorAdded(final SparkListenerExecutorAdded executorAdded) {
   }

   public void onExecutorRemoved(final SparkListenerExecutorRemoved executorRemoved) {
   }

   public void onExecutorBlacklisted(final SparkListenerExecutorBlacklisted executorBlacklisted) {
   }

   public void onExecutorExcluded(final SparkListenerExecutorExcluded executorExcluded) {
   }

   public void onExecutorBlacklistedForStage(final SparkListenerExecutorBlacklistedForStage executorBlacklistedForStage) {
   }

   public void onExecutorExcludedForStage(final SparkListenerExecutorExcludedForStage executorExcludedForStage) {
   }

   public void onNodeBlacklistedForStage(final SparkListenerNodeBlacklistedForStage nodeBlacklistedForStage) {
   }

   public void onNodeExcludedForStage(final SparkListenerNodeExcludedForStage nodeExcludedForStage) {
   }

   public void onExecutorUnblacklisted(final SparkListenerExecutorUnblacklisted executorUnblacklisted) {
   }

   public void onExecutorUnexcluded(final SparkListenerExecutorUnexcluded executorUnexcluded) {
   }

   public void onNodeBlacklisted(final SparkListenerNodeBlacklisted nodeBlacklisted) {
   }

   public void onNodeExcluded(final SparkListenerNodeExcluded nodeExcluded) {
   }

   public void onNodeUnblacklisted(final SparkListenerNodeUnblacklisted nodeUnblacklisted) {
   }

   public void onNodeUnexcluded(final SparkListenerNodeUnexcluded nodeUnexcluded) {
   }

   public void onUnschedulableTaskSetAdded(final SparkListenerUnschedulableTaskSetAdded unschedulableTaskSetAdded) {
   }

   public void onUnschedulableTaskSetRemoved(final SparkListenerUnschedulableTaskSetRemoved unschedulableTaskSetRemoved) {
   }

   public void onBlockUpdated(final SparkListenerBlockUpdated blockUpdated) {
   }

   public void onSpeculativeTaskSubmitted(final SparkListenerSpeculativeTaskSubmitted speculativeTask) {
   }

   public void onOtherEvent(final SparkListenerEvent event) {
   }

   public void onResourceProfileAdded(final SparkListenerResourceProfileAdded event) {
   }
}
