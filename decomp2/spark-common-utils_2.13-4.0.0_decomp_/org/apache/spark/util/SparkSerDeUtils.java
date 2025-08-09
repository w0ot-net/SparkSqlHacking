package org.apache.spark.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E3\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0005\u0005\u00061\u0001!\tA\u0007\u0005\u0006=\u0001!\ta\b\u0005\u0006i\u0001!\t!\u000e\u0005\u0006i\u0001!\taO\u0004\u0007\u0015&A\taC&\u0007\r!I\u0001\u0012A\u0006N\u0011\u0015ye\u0001\"\u0001Q\u0005=\u0019\u0006/\u0019:l'\u0016\u0014H)Z+uS2\u001c(B\u0001\u0006\f\u0003\u0011)H/\u001b7\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\"\u0001\u0001\n\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uI\r\u0001A#A\u000e\u0011\u0005Ma\u0012BA\u000f\u0015\u0005\u0011)f.\u001b;\u0002\u0013M,'/[1mSj,WC\u0001\u0011,)\t\ts\u0005E\u0002\u0014E\u0011J!a\t\u000b\u0003\u000b\u0005\u0013(/Y=\u0011\u0005M)\u0013B\u0001\u0014\u0015\u0005\u0011\u0011\u0015\u0010^3\t\u000b!\u0012\u0001\u0019A\u0015\u0002\u0003=\u0004\"AK\u0016\r\u0001\u0011)AF\u0001b\u0001[\t\tA+\u0005\u0002/cA\u00111cL\u0005\u0003aQ\u0011qAT8uQ&tw\r\u0005\u0002\u0014e%\u00111\u0007\u0006\u0002\u0004\u0003:L\u0018a\u00033fg\u0016\u0014\u0018.\u00197ju\u0016,\"A\u000e\u001d\u0015\u0005]J\u0004C\u0001\u00169\t\u0015a3A1\u0001.\u0011\u0015Q4\u00011\u0001\"\u0003\u0015\u0011\u0017\u0010^3t+\tad\bF\u0002>\u007f\u0001\u0003\"A\u000b \u0005\u000b1\"!\u0019A\u0017\t\u000bi\"\u0001\u0019A\u0011\t\u000b\u0005#\u0001\u0019\u0001\"\u0002\r1|\u0017\rZ3s!\t\u0019\u0005*D\u0001E\u0015\t)e)\u0001\u0003mC:<'\"A$\u0002\t)\fg/Y\u0005\u0003\u0013\u0012\u00131b\u00117bgNdu.\u00193fe\u0006y1\u000b]1sWN+'\u000fR3Vi&d7\u000f\u0005\u0002M\r5\t\u0011bE\u0002\u0007%9\u0003\"\u0001\u0014\u0001\u0002\rqJg.\u001b;?)\u0005Y\u0005"
)
public interface SparkSerDeUtils {
   // $FF: synthetic method
   static byte[] serialize$(final SparkSerDeUtils $this, final Object o) {
      return $this.serialize(o);
   }

   default byte[] serialize(final Object o) {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(o);
      oos.close();
      return bos.toByteArray();
   }

   // $FF: synthetic method
   static Object deserialize$(final SparkSerDeUtils $this, final byte[] bytes) {
      return $this.deserialize(bytes);
   }

   default Object deserialize(final byte[] bytes) {
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bis);
      return ois.readObject();
   }

   // $FF: synthetic method
   static Object deserialize$(final SparkSerDeUtils $this, final byte[] bytes, final ClassLoader loader) {
      return $this.deserialize(bytes, loader);
   }

   default Object deserialize(final byte[] bytes, final ClassLoader loader) {
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      ObjectInputStream ois = new ObjectInputStream(bis, loader) {
         private final ClassLoader loader$1;

         public Class resolveClass(final ObjectStreamClass desc) {
            return Class.forName(desc.getName(), false, this.loader$1);
         }

         public {
            this.loader$1 = loader$1;
         }
      };
      return ois.readObject();
   }

   static void $init$(final SparkSerDeUtils $this) {
   }
}
