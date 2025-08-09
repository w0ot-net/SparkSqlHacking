package org.apache.spark.deploy.master;

import scala.collection.immutable.Seq;
import scala.collection.immutable.Nil.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3Q!\u0002\u0004\u0001\rAAQ!\u0006\u0001\u0005\u0002]AQ!\u0007\u0001\u0005BiAQ\u0001\u000f\u0001\u0005BeBQa\u000f\u0001\u0005Bq\u0012!D\u00117bG.Du\u000e\\3QKJ\u001c\u0018n\u001d;f]\u000e,WI\\4j]\u0016T!a\u0002\u0005\u0002\r5\f7\u000f^3s\u0015\tI!\"\u0001\u0004eKBdw.\u001f\u0006\u0003\u00171\tQa\u001d9be.T!!\u0004\b\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0011aA8sON\u0011\u0001!\u0005\t\u0003%Mi\u0011AB\u0005\u0003)\u0019\u0011\u0011\u0003U3sg&\u001cH/\u001a8dK\u0016sw-\u001b8f\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\r\u0011\u0005I\u0001\u0011a\u00029feNL7\u000f\u001e\u000b\u00047\u0005r\u0003C\u0001\u000f \u001b\u0005i\"\"\u0001\u0010\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0001j\"\u0001B+oSRDQA\t\u0002A\u0002\r\nAA\\1nKB\u0011Ae\u000b\b\u0003K%\u0002\"AJ\u000f\u000e\u0003\u001dR!\u0001\u000b\f\u0002\rq\u0012xn\u001c;?\u0013\tQS$\u0001\u0004Qe\u0016$WMZ\u0005\u0003Y5\u0012aa\u0015;sS:<'B\u0001\u0016\u001e\u0011\u0015y#\u00011\u00011\u0003\ry'M\u001b\t\u0003cYj\u0011A\r\u0006\u0003gQ\nA\u0001\\1oO*\tQ'\u0001\u0003kCZ\f\u0017BA\u001c3\u0005\u0019y%M[3di\u0006IQO\u001c9feNL7\u000f\u001e\u000b\u00037iBQAI\u0002A\u0002\r\nAA]3bIV\u0011QH\u0013\u000b\u0003}m#\"aP*\u0011\u0007\u0001+\u0005J\u0004\u0002B\u0007:\u0011aEQ\u0005\u0002=%\u0011A)H\u0001\ba\u0006\u001c7.Y4f\u0013\t1uIA\u0002TKFT!\u0001R\u000f\u0011\u0005%SE\u0002\u0001\u0003\u0006\u0017\u0012\u0011\r\u0001\u0014\u0002\u0002)F\u0011Q\n\u0015\t\u000399K!aT\u000f\u0003\u000f9{G\u000f[5oOB\u0011A$U\u0005\u0003%v\u00111!\u00118z\u0011\u001d!F!!AA\u0004U\u000b!\"\u001a<jI\u0016t7-\u001a\u00133!\r1\u0016\fS\u0007\u0002/*\u0011\u0001,H\u0001\be\u00164G.Z2u\u0013\tQvK\u0001\u0005DY\u0006\u001c8\u000fV1h\u0011\u0015\u0011C\u00011\u0001$\u0001"
)
public class BlackHolePersistenceEngine extends PersistenceEngine {
   public void persist(final String name, final Object obj) {
   }

   public void unpersist(final String name) {
   }

   public Seq read(final String name, final ClassTag evidence$2) {
      return .MODULE$;
   }
}
