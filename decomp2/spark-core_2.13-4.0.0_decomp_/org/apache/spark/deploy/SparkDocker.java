package org.apache.spark.deploy;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A;QAB\u0004\t\nA1QAE\u0004\t\nMAQAG\u0001\u0005\u0002mAQ\u0001H\u0001\u0005\u0002uAQAL\u0001\u0005\u0002=BQAN\u0001\u0005\n]\n1b\u00159be.$unY6fe*\u0011\u0001\"C\u0001\u0007I\u0016\u0004Hn\\=\u000b\u0005)Y\u0011!B:qCJ\\'B\u0001\u0007\u000e\u0003\u0019\t\u0007/Y2iK*\ta\"A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u0012\u00035\tqAA\u0006Ta\u0006\u00148\u000eR8dW\u0016\u00148CA\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012\u0001E\u0001\fgR\f'\u000f^'bgR,'\u000f\u0006\u0002\u001fCA\u0011\u0011cH\u0005\u0003A\u001d\u0011a\u0002V3ti6\u000b7\u000f^3s\u0013:4w\u000eC\u0003#\u0007\u0001\u00071%\u0001\u0005n_VtG\u000fR5s!\t!3F\u0004\u0002&SA\u0011aEF\u0007\u0002O)\u0011\u0001fD\u0001\u0007yI|w\u000e\u001e \n\u0005)2\u0012A\u0002)sK\u0012,g-\u0003\u0002-[\t11\u000b\u001e:j]\u001eT!A\u000b\f\u0002\u0017M$\u0018M\u001d;X_J\\WM\u001d\u000b\u0004aM\"\u0004CA\t2\u0013\t\u0011tA\u0001\bUKN$xk\u001c:lKJLeNZ8\t\u000b\t\"\u0001\u0019A\u0012\t\u000bU\"\u0001\u0019A\u0012\u0002\u000f5\f7\u000f^3sg\u0006I1\u000f^1si:{G-\u001a\u000b\u0003q\u0019\u0003R!F\u001d$wyJ!A\u000f\f\u0003\rQ+\b\u000f\\34!\t\tB(\u0003\u0002>\u000f\tAAi\\2lKJLE\r\u0005\u0002@\t6\t\u0001I\u0003\u0002B\u0005\u0006\u0011\u0011n\u001c\u0006\u0002\u0007\u0006!!.\u0019<b\u0013\t)\u0005I\u0001\u0003GS2,\u0007\"B$\u0006\u0001\u0004A\u0015!\u00033pG.,'oQ7e!\tIe*D\u0001K\u0015\tYE*A\u0004qe>\u001cWm]:\u000b\u000553\u0012aA:zg&\u0011qJ\u0013\u0002\u000f!J|7-Z:t\u0005VLG\u000eZ3s\u0001"
)
public final class SparkDocker {
   public static TestWorkerInfo startWorker(final String mountDir, final String masters) {
      return SparkDocker$.MODULE$.startWorker(mountDir, masters);
   }

   public static TestMasterInfo startMaster(final String mountDir) {
      return SparkDocker$.MODULE$.startMaster(mountDir);
   }
}
