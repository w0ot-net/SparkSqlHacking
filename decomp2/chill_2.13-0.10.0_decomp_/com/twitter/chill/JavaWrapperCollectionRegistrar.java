package com.twitter.chill;

import com.esotericsoftware.kryo.Kryo;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512Aa\u0001\u0003\u0001\u0017!)\u0001\u0004\u0001C\u00013!)1\u0004\u0001C\u00019\tq\"*\u0019<b/J\f\u0007\u000f]3s\u0007>dG.Z2uS>t'+Z4jgR\u0014\u0018M\u001d\u0006\u0003\u000b\u0019\tQa\u00195jY2T!a\u0002\u0005\u0002\u000fQ<\u0018\u000e\u001e;fe*\t\u0011\"A\u0002d_6\u001c\u0001aE\u0002\u0001\u0019Q\u0001\"!\u0004\n\u000e\u00039Q!a\u0004\t\u0002\t1\fgn\u001a\u0006\u0002#\u0005!!.\u0019<b\u0013\t\u0019bB\u0001\u0004PE*,7\r\u001e\t\u0003+Yi\u0011\u0001B\u0005\u0003/\u0011\u0011a\"S&ss>\u0014VmZ5tiJ\f'/\u0001\u0004=S:LGO\u0010\u000b\u00025A\u0011Q\u0003A\u0001\u0006CB\u0004H.\u001f\u000b\u0003;\r\u0002\"AH\u0011\u000e\u0003}Q\u0011\u0001I\u0001\u0006g\u000e\fG.Y\u0005\u0003E}\u0011A!\u00168ji\")AE\u0001a\u0001K\u0005!a.Z<L!\t1\u0013F\u0004\u0002\u0016O%\u0011\u0001\u0006B\u0001\ba\u0006\u001c7.Y4f\u0013\tQ3F\u0001\u0003Lef|'B\u0001\u0015\u0005\u0001"
)
public class JavaWrapperCollectionRegistrar implements IKryoRegistrar {
   public void apply(final Kryo newK) {
      newK.register(JavaIterableWrapperSerializer$.MODULE$.wrapperClass(), new JavaIterableWrapperSerializer());
   }
}
