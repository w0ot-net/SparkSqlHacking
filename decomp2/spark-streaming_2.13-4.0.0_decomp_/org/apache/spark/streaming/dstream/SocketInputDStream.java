package org.apache.spark.streaming.dstream;

import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.receiver.Receiver;
import scala.Function1;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M4Q!\u0003\u0006\u0001\u0019QA\u0001\"\u000b\u0001\u0003\u0002\u0003\u0006IA\u000b\u0005\t]\u0001\u0011\t\u0011)A\u0005_!A!\b\u0001B\u0001B\u0003%1\b\u0003\u0005?\u0001\t\u0005\t\u0015!\u0003@\u0011!\u0019\u0006A!A!\u0002\u0013!\u0006\u0002\u0003.\u0001\u0005\u0007\u0005\u000b1B.\t\u000b\u0005\u0004A\u0011\u00012\t\u000b-\u0004A\u0011\u00017\u0003%M{7m[3u\u0013:\u0004X\u000f\u001e#TiJ,\u0017-\u001c\u0006\u0003\u00171\tq\u0001Z:ue\u0016\fWN\u0003\u0002\u000e\u001d\u0005I1\u000f\u001e:fC6Lgn\u001a\u0006\u0003\u001fA\tQa\u001d9be.T!!\u0005\n\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\u0019\u0012aA8sOV\u0011Q\u0003H\n\u0003\u0001Y\u00012a\u0006\r\u001b\u001b\u0005Q\u0011BA\r\u000b\u0005Q\u0011VmY3jm\u0016\u0014\u0018J\u001c9vi\u0012\u001bFO]3b[B\u00111\u0004\b\u0007\u0001\t\u0015i\u0002A1\u0001 \u0005\u0005!6\u0001A\t\u0003A\u0019\u0002\"!\t\u0013\u000e\u0003\tR\u0011aI\u0001\u0006g\u000e\fG.Y\u0005\u0003K\t\u0012qAT8uQ&tw\r\u0005\u0002\"O%\u0011\u0001F\t\u0002\u0004\u0003:L\u0018\u0001B0tg\u000e\u0004\"a\u000b\u0017\u000e\u00031I!!\f\u0007\u0003!M#(/Z1nS:<7i\u001c8uKb$\u0018\u0001\u00025pgR\u0004\"\u0001M\u001c\u000f\u0005E*\u0004C\u0001\u001a#\u001b\u0005\u0019$B\u0001\u001b\u001f\u0003\u0019a$o\\8u}%\u0011aGI\u0001\u0007!J,G-\u001a4\n\u0005aJ$AB*ue&twM\u0003\u00027E\u0005!\u0001o\u001c:u!\t\tC(\u0003\u0002>E\t\u0019\u0011J\u001c;\u0002\u001d\tLH/Z:U_>\u0013'.Z2ugB!\u0011\u0005\u0011\"K\u0013\t\t%EA\u0005Gk:\u001cG/[8ocA\u00111\tS\u0007\u0002\t*\u0011QIR\u0001\u0003S>T\u0011aR\u0001\u0005U\u00064\u0018-\u0003\u0002J\t\nY\u0011J\u001c9viN#(/Z1n!\rY\u0005K\u0007\b\u0003\u0019:s!AM'\n\u0003\rJ!a\u0014\u0012\u0002\u000fA\f7m[1hK&\u0011\u0011K\u0015\u0002\t\u0013R,'/\u0019;pe*\u0011qJI\u0001\rgR|'/Y4f\u0019\u00164X\r\u001c\t\u0003+bk\u0011A\u0016\u0006\u0003/:\tqa\u001d;pe\u0006<W-\u0003\u0002Z-\na1\u000b^8sC\u001e,G*\u001a<fY\u0006QQM^5eK:\u001cW\rJ\u0019\u0011\u0007q{&$D\u0001^\u0015\tq&%A\u0004sK\u001adWm\u0019;\n\u0005\u0001l&\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\u0019\u0019gm\u001a5jUR\u0011A-\u001a\t\u0004/\u0001Q\u0002\"\u0002.\b\u0001\bY\u0006\"B\u0015\b\u0001\u0004Q\u0003\"\u0002\u0018\b\u0001\u0004y\u0003\"\u0002\u001e\b\u0001\u0004Y\u0004\"\u0002 \b\u0001\u0004y\u0004\"B*\b\u0001\u0004!\u0016aC4fiJ+7-Z5wKJ$\u0012!\u001c\t\u0004]FTR\"A8\u000b\u0005Ad\u0011\u0001\u0003:fG\u0016Lg/\u001a:\n\u0005I|'\u0001\u0003*fG\u0016Lg/\u001a:"
)
public class SocketInputDStream extends ReceiverInputDStream {
   private final String host;
   private final int port;
   private final Function1 bytesToObjects;
   private final StorageLevel storageLevel;
   private final ClassTag evidence$1;

   public Receiver getReceiver() {
      return new SocketReceiver(this.host, this.port, this.bytesToObjects, this.storageLevel, this.evidence$1);
   }

   public SocketInputDStream(final StreamingContext _ssc, final String host, final int port, final Function1 bytesToObjects, final StorageLevel storageLevel, final ClassTag evidence$1) {
      super(_ssc, evidence$1);
      this.host = host;
      this.port = port;
      this.bytesToObjects = bytesToObjects;
      this.storageLevel = storageLevel;
      this.evidence$1 = evidence$1;
   }
}
