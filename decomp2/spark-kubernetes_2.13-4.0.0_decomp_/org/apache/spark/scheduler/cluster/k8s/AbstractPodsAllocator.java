package org.apache.spark.scheduler.cluster.k8s;

import org.apache.spark.annotation.DeveloperApi;
import scala.Option;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005-4Qa\u0002\u0005\u0002\u0002UAQ\u0001\b\u0001\u0005\u0002uAQ\u0001\t\u0001\u0007\u0002\u0005BQa\u000f\u0001\u0007\u0002qBQA\u0014\u0001\u0007\u0002=CQ\u0001\u0017\u0001\u0007\u0002eCQ!\u0019\u0001\u0007\u0002\t\u0014Q#\u00112tiJ\f7\r\u001e)pIN\fE\u000e\\8dCR|'O\u0003\u0002\n\u0015\u0005\u00191\u000eO:\u000b\u0005-a\u0011aB2mkN$XM\u001d\u0006\u0003\u001b9\t\u0011b]2iK\u0012,H.\u001a:\u000b\u0005=\u0001\u0012!B:qCJ\\'BA\t\u0013\u0003\u0019\t\u0007/Y2iK*\t1#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u0001-A\u0011qCG\u0007\u00021)\t\u0011$A\u0003tG\u0006d\u0017-\u0003\u0002\u001c1\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u0010\u0011\u0005}\u0001Q\"\u0001\u0005\u00023M,G\u000fV8uC2,\u0005\u0010]3di\u0016$W\t_3dkR|'o\u001d\u000b\u0003E\u0015\u0002\"aF\u0012\n\u0005\u0011B\"\u0001B+oSRDQA\n\u0002A\u0002\u001d\n1D]3t_V\u00148-\u001a)s_\u001aLG.\u001a+p)>$\u0018\r\\#yK\u000e\u001c\b\u0003\u0002\u00150ear!!K\u0017\u0011\u0005)BR\"A\u0016\u000b\u00051\"\u0012A\u0002\u001fs_>$h(\u0003\u0002/1\u00051\u0001K]3eK\u001aL!\u0001M\u0019\u0003\u00075\u000b\u0007O\u0003\u0002/1A\u00111GN\u0007\u0002i)\u0011QGD\u0001\te\u0016\u001cx.\u001e:dK&\u0011q\u0007\u000e\u0002\u0010%\u0016\u001cx.\u001e:dKB\u0013xNZ5mKB\u0011q#O\u0005\u0003ua\u00111!\u00138u\u0003%!'/\u001b<feB{G-F\u0001>!\r9b\bQ\u0005\u0003\u007fa\u0011aa\u00149uS>t\u0007CA!M\u001b\u0005\u0011%BA\"E\u0003\u0015iw\u000eZ3m\u0015\t)e)A\u0002ba&T!a\u0012%\u0002\u0015-,(-\u001a:oKR,7O\u0003\u0002J\u0015\u00069a-\u00192sS\u000eD$\"A&\u0002\u0005%|\u0017BA'C\u0005\r\u0001v\u000eZ\u0001\nSN$U\r\\3uK\u0012$\"\u0001U*\u0011\u0005]\t\u0016B\u0001*\u0019\u0005\u001d\u0011un\u001c7fC:DQ\u0001\u0016\u0003A\u0002U\u000b!\"\u001a=fGV$xN]%e!\tAc+\u0003\u0002Xc\t11\u000b\u001e:j]\u001e\fQa\u001d;beR$2A\t.]\u0011\u0015YV\u00011\u0001V\u00035\t\u0007\u000f\u001d7jG\u0006$\u0018n\u001c8JI\")Q,\u0002a\u0001=\u0006\u00012o\u00195fIVdWM\u001d\"bG.,g\u000e\u001a\t\u0003?}K!\u0001\u0019\u0005\u0003C-+(-\u001a:oKR,7o\u00117vgR,'oU2iK\u0012,H.\u001a:CC\u000e\\WM\u001c3\u0002\tM$x\u000e\u001d\u000b\u0003E\rDQa\u0017\u0004A\u0002UC#\u0001A3\u0011\u0005\u0019LW\"A4\u000b\u0005!t\u0011AC1o]>$\u0018\r^5p]&\u0011!n\u001a\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b"
)
public abstract class AbstractPodsAllocator {
   public abstract void setTotalExpectedExecutors(final Map resourceProfileToTotalExecs);

   public abstract Option driverPod();

   public abstract boolean isDeleted(final String executorId);

   public abstract void start(final String applicationId, final KubernetesClusterSchedulerBackend schedulerBackend);

   public abstract void stop(final String applicationId);
}
