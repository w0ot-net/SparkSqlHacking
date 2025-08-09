package org.apache.spark.serializer;

import java.io.Closeable;
import org.apache.spark.annotation.DeveloperApi;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005m4Q\u0001C\u0005\u0002\u0002IAQ!\t\u0001\u0005\u0002\tBQ!\n\u0001\u0007\u0002\u0019BQ!\u0011\u0001\u0005\u0002\tCQ\u0001\u0014\u0001\u0005\u00025CQa\u0016\u0001\u0007\u0002aCQ\u0001\u0018\u0001\u0007BaCQ!\u0018\u0001\u0005\u0002y\u00131cU3sS\u0006d\u0017N_1uS>t7\u000b\u001e:fC6T!AC\u0006\u0002\u0015M,'/[1mSj,'O\u0003\u0002\r\u001b\u0005)1\u000f]1sW*\u0011abD\u0001\u0007CB\f7\r[3\u000b\u0003A\t1a\u001c:h\u0007\u0001\u00192\u0001A\n\u001c!\t!\u0012$D\u0001\u0016\u0015\t1r#\u0001\u0003mC:<'\"\u0001\r\u0002\t)\fg/Y\u0005\u00035U\u0011aa\u00142kK\u000e$\bC\u0001\u000f \u001b\u0005i\"B\u0001\u0010\u0018\u0003\tIw.\u0003\u0002!;\tI1\t\\8tK\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\r\u0002\"\u0001\n\u0001\u000e\u0003%\t1b\u001e:ji\u0016|%M[3diV\u0011q%\u000e\u000b\u0003Q}\"\"aI\u0015\t\u000f)\u0012\u0011\u0011!a\u0002W\u0005QQM^5eK:\u001cW\r\n\u001b\u0011\u00071\n4'D\u0001.\u0015\tqs&A\u0004sK\u001adWm\u0019;\u000b\u0003A\nQa]2bY\u0006L!AM\u0017\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"\u0001N\u001b\r\u0001\u0011)aG\u0001b\u0001o\t\tA+\u0005\u00029yA\u0011\u0011HO\u0007\u0002_%\u00111h\f\u0002\b\u001d>$\b.\u001b8h!\tIT(\u0003\u0002?_\t\u0019\u0011I\\=\t\u000b\u0001\u0013\u0001\u0019A\u001a\u0002\u0003Q\f\u0001b\u001e:ji\u0016\\U-_\u000b\u0003\u0007&#\"\u0001\u0012&\u0015\u0005\r*\u0005b\u0002$\u0004\u0003\u0003\u0005\u001daR\u0001\u000bKZLG-\u001a8dK\u0012*\u0004c\u0001\u00172\u0011B\u0011A'\u0013\u0003\u0006m\r\u0011\ra\u000e\u0005\u0006\u0017\u000e\u0001\r\u0001S\u0001\u0004W\u0016L\u0018AC<sSR,g+\u00197vKV\u0011a\n\u0016\u000b\u0003\u001fV#\"a\t)\t\u000fE#\u0011\u0011!a\u0002%\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\u00071\n4\u000b\u0005\u00025)\u0012)a\u0007\u0002b\u0001o!)a\u000b\u0002a\u0001'\u0006)a/\u00197vK\u0006)a\r\\;tQR\t\u0011\f\u0005\u0002:5&\u00111l\f\u0002\u0005+:LG/A\u0003dY>\u001cX-\u0001\u0005xe&$X-\u00117m+\tyV\r\u0006\u0002aMR\u00111%\u0019\u0005\bE\u001e\t\t\u0011q\u0001d\u0003))g/\u001b3f]\u000e,Ge\u000e\t\u0004YE\"\u0007C\u0001\u001bf\t\u00151tA1\u00018\u0011\u00159w\u00011\u0001i\u0003\u0011IG/\u001a:\u0011\u0007%\fHM\u0004\u0002k_:\u00111N\\\u0007\u0002Y*\u0011Q.E\u0001\u0007yI|w\u000e\u001e \n\u0003AJ!\u0001]\u0018\u0002\u000fA\f7m[1hK&\u0011!o\u001d\u0002\t\u0013R,'/\u0019;pe*\u0011\u0001o\f\u0015\u0003\u0001U\u0004\"A^=\u000e\u0003]T!\u0001_\u0006\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002{o\naA)\u001a<fY>\u0004XM]!qS\u0002"
)
public abstract class SerializationStream implements Closeable {
   public abstract SerializationStream writeObject(final Object t, final ClassTag evidence$4);

   public SerializationStream writeKey(final Object key, final ClassTag evidence$5) {
      return this.writeObject(key, evidence$5);
   }

   public SerializationStream writeValue(final Object value, final ClassTag evidence$6) {
      return this.writeObject(value, evidence$6);
   }

   public abstract void flush();

   public abstract void close();

   public SerializationStream writeAll(final Iterator iter, final ClassTag evidence$7) {
      while(iter.hasNext()) {
         this.writeObject(iter.next(), evidence$7);
      }

      return this;
   }
}
