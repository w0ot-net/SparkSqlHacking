package org.apache.spark.scheduler;

import java.util.concurrent.ConcurrentLinkedQueue;
import scala.Enumeration;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055b\u0001C\n\u0015!\u0003\r\nA\u0006\u000f\t\u000f\r\u0002\u0001\u0019!D\u0001K!9!\u0006\u0001a\u0001\u000e\u0003Y\u0003\"B\u0019\u0001\r\u0003\u0011\u0004\"\u0002 \u0001\r\u0003y\u0004\"\u0002*\u0001\r\u0003\u0019\u0006\"B,\u0001\r\u0003\u0019\u0006\"\u0002-\u0001\r\u0003\u0019\u0006\"B-\u0001\r\u0003\u0019\u0006\"\u0002.\u0001\r\u0003\u0019\u0006\"B.\u0001\r\u0003a\u0006\"B3\u0001\r\u00031\u0007\"\u00026\u0001\r\u0003Y\u0007\"\u00028\u0001\r\u0003y\u0007\"B9\u0001\r\u0003\u0011\b\"\u0002;\u0001\r\u0003)\bBB@\u0001\r\u0003\t\t\u0001C\u0004\u0002\u0006\u00011\t!a\u0002\t\u000f\u0005M\u0001A\"\u0001\u0002\u0016\tY1k\u00195fIVd\u0017M\u00197f\u0015\t)b#A\u0005tG\",G-\u001e7fe*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xm\u0005\u0002\u0001;A\u0011a$I\u0007\u0002?)\t\u0001%A\u0003tG\u0006d\u0017-\u0003\u0002#?\t1\u0011I\\=SK\u001a\fa\u0001]1sK:$8\u0001A\u000b\u0002MA\u0011q\u0005K\u0007\u0002)%\u0011\u0011\u0006\u0006\u0002\u0005!>|G.\u0001\u0006qCJ,g\u000e^0%KF$\"\u0001L\u0018\u0011\u0005yi\u0013B\u0001\u0018 \u0005\u0011)f.\u001b;\t\u000fA\u0012\u0011\u0011!a\u0001M\u0005\u0019\u0001\u0010J\u0019\u0002!M\u001c\u0007.\u001a3vY\u0006\u0014G.Z)vKV,W#A\u001a\u0011\u0007QZT(D\u00016\u0015\t1t'\u0001\u0006d_:\u001cWO\u001d:f]RT!\u0001O\u001d\u0002\tU$\u0018\u000e\u001c\u0006\u0002u\u0005!!.\u0019<b\u0013\taTGA\u000bD_:\u001cWO\u001d:f]Rd\u0015N\\6fIF+X-^3\u0011\u0005\u001d\u0002\u0011AD:dQ\u0016$W\u000f\\5oO6{G-Z\u000b\u0002\u0001B\u0011\u0011i\u0014\b\u0003\u00056s!a\u0011'\u000f\u0005\u0011[eBA#K\u001d\t1\u0015*D\u0001H\u0015\tAE%\u0001\u0004=e>|GOP\u0005\u00027%\u0011\u0011DG\u0005\u0003/aI!!\u0006\f\n\u00059#\u0012AD*dQ\u0016$W\u000f\\5oO6{G-Z\u0005\u0003!F\u0013abU2iK\u0012,H.\u001b8h\u001b>$WM\u0003\u0002O)\u00051q/Z5hQR,\u0012\u0001\u0016\t\u0003=UK!AV\u0010\u0003\u0007%sG/\u0001\u0005nS:\u001c\u0006.\u0019:f\u00031\u0011XO\u001c8j]\u001e$\u0016m]6t\u0003!\u0001(/[8sSRL\u0018aB:uC\u001e,\u0017\nZ\u0001\u0005]\u0006lW-F\u0001^!\tq&M\u0004\u0002`AB\u0011aiH\u0005\u0003C~\ta\u0001\u0015:fI\u00164\u0017BA2e\u0005\u0019\u0019FO]5oO*\u0011\u0011mH\u0001\u000eSN\u001c6\r[3ek2\f'\r\\3\u0016\u0003\u001d\u0004\"A\b5\n\u0005%|\"a\u0002\"p_2,\u0017M\\\u0001\u000fC\u0012$7k\u00195fIVd\u0017M\u00197f)\taC\u000eC\u0003n\u0019\u0001\u0007Q(A\u0006tG\",G-\u001e7bE2,\u0017!\u0005:f[>4XmU2iK\u0012,H.\u00192mKR\u0011A\u0006\u001d\u0005\u0006[6\u0001\r!P\u0001\u0015O\u0016$8k\u00195fIVd\u0017M\u00197f\u0005ft\u0015-\\3\u0015\u0005u\u001a\b\"B.\u000f\u0001\u0004i\u0016\u0001D3yK\u000e,Ho\u001c:M_N$H\u0003\u0002\u0017wqjDQa^\bA\u0002u\u000b!\"\u001a=fGV$xN]%e\u0011\u0015Ix\u00021\u0001^\u0003\u0011Awn\u001d;\t\u000bm|\u0001\u0019\u0001?\u0002\rI,\u0017m]8o!\t9S0\u0003\u0002\u007f)\t\u0011R\t_3dkR|'\u000fT8tgJ+\u0017m]8o\u0003Q)\u00070Z2vi>\u0014H)Z2p[6L7o]5p]R\u0019A&a\u0001\t\u000b]\u0004\u0002\u0019A/\u0002-\rDWmY6Ta\u0016\u001cW\u000f\\1uC\ndW\rV1tWN$2aZA\u0005\u0011\u001d\tY!\u0005a\u0001\u0003\u001b\tA#\\5o)&lW\rV8Ta\u0016\u001cW\u000f\\1uS>t\u0007c\u0001\u0010\u0002\u0010%\u0019\u0011\u0011C\u0010\u0003\t1{gnZ\u0001\u0016O\u0016$8k\u001c:uK\u0012$\u0016m]6TKR\fV/Z;f+\t\t9\u0002\u0005\u0004\u0002\u001a\u0005\r\u0012qE\u0007\u0003\u00037QA!!\b\u0002 \u00059Q.\u001e;bE2,'bAA\u0011?\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\n\t\u0005\u0015\u00121\u0004\u0002\f\u0003J\u0014\u0018-\u001f\"vM\u001a,'\u000fE\u0002(\u0003SI1!a\u000b\u0015\u00059!\u0016m]6TKRl\u0015M\\1hKJ\u0004"
)
public interface Schedulable {
   Pool parent();

   void parent_$eq(final Pool x$1);

   ConcurrentLinkedQueue schedulableQueue();

   Enumeration.Value schedulingMode();

   int weight();

   int minShare();

   int runningTasks();

   int priority();

   int stageId();

   String name();

   boolean isSchedulable();

   void addSchedulable(final Schedulable schedulable);

   void removeSchedulable(final Schedulable schedulable);

   Schedulable getSchedulableByName(final String name);

   void executorLost(final String executorId, final String host, final ExecutorLossReason reason);

   void executorDecommission(final String executorId);

   boolean checkSpeculatableTasks(final long minTimeToSpeculation);

   ArrayBuffer getSortedTaskSetQueue();
}
