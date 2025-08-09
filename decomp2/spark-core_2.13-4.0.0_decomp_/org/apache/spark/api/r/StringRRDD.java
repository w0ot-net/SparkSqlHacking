package org.apache.spark.api.r;

import java.io.Serializable;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaRDD$;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00194A!\u0003\u0006\u0005+!AA\u0007\u0001B\u0001B\u0003%Q\u0007\u0003\u0005<\u0001\t\u0005\t\u0015!\u0003=\u0011!\u0011\u0005A!A!\u0002\u0013I\u0003\u0002C\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001f\t\u0011\u0011\u0003!\u0011!Q\u0001\n\u0015C\u0001B\u0014\u0001\u0003\u0004\u0003\u0006Ya\u0014\u0005\u0006+\u0002!\tA\u0016\u0005\t?\u0002A)\u0019!C\u0001A\nQ1\u000b\u001e:j]\u001e\u0014&\u000b\u0012#\u000b\u0005-a\u0011!\u0001:\u000b\u00055q\u0011aA1qS*\u0011q\u0002E\u0001\u0006gB\f'o\u001b\u0006\u0003#I\ta!\u00199bG\",'\"A\n\u0002\u0007=\u0014xm\u0001\u0001\u0016\u0005Yi2C\u0001\u0001\u0018!\u0011A\u0012dG\u0015\u000e\u0003)I!A\u0007\u0006\u0003\u0011\t\u000b7/\u001a*S\t\u0012\u0003\"\u0001H\u000f\r\u0001\u0011)a\u0004\u0001b\u0001?\t\tA+\u0005\u0002!MA\u0011\u0011\u0005J\u0007\u0002E)\t1%A\u0003tG\u0006d\u0017-\u0003\u0002&E\t9aj\u001c;iS:<\u0007CA\u0011(\u0013\tA#EA\u0002B]f\u0004\"AK\u0019\u000f\u0005-z\u0003C\u0001\u0017#\u001b\u0005i#B\u0001\u0018\u0015\u0003\u0019a$o\\8u}%\u0011\u0001GI\u0001\u0007!J,G-\u001a4\n\u0005I\u001a$AB*ue&twM\u0003\u00021E\u00051\u0001/\u0019:f]R\u00042AN\u001d\u001c\u001b\u00059$B\u0001\u001d\u000f\u0003\r\u0011H\rZ\u0005\u0003u]\u00121A\u0015#E\u0003\u00111WO\\2\u0011\u0007\u0005jt(\u0003\u0002?E\t)\u0011I\u001d:bsB\u0011\u0011\u0005Q\u0005\u0003\u0003\n\u0012AAQ=uK\u0006aA-Z:fe&\fG.\u001b>fe\u0006a\u0001/Y2lC\u001e,g*Y7fg\u0006i!M]8bI\u000e\f7\u000f\u001e,beN\u00042!I\u001fG!\t9E*D\u0001I\u0015\tI%*\u0001\u0003mC:<'\"A&\u0002\t)\fg/Y\u0005\u0003\u001b\"\u0013aa\u00142kK\u000e$\u0018AC3wS\u0012,gnY3%kA\u0019\u0001kU\u000e\u000e\u0003ES!A\u0015\u0012\u0002\u000fI,g\r\\3di&\u0011A+\u0015\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"ba\u0016.\\9vsFC\u0001-Z!\rA\u0002a\u0007\u0005\u0006\u001d\u001e\u0001\u001da\u0014\u0005\u0006i\u001d\u0001\r!\u000e\u0005\u0006w\u001d\u0001\r\u0001\u0010\u0005\u0006\u0005\u001e\u0001\r!\u000b\u0005\u0006\u0007\u001e\u0001\r\u0001\u0010\u0005\u0006\t\u001e\u0001\r!R\u0001\nCNT\u0015M^1S\t\u0012+\u0012!\u0019\t\u0004E\u0012LS\"A2\u000b\u0005-c\u0011BA3d\u0005\u001dQ\u0015M^1S\t\u0012\u0003"
)
public class StringRRDD extends BaseRRDD {
   private JavaRDD asJavaRDD;
   private volatile boolean bitmap$0;

   private JavaRDD asJavaRDD$lzycompute() {
      synchronized(this){}

      try {
         if (!this.bitmap$0) {
            this.asJavaRDD = JavaRDD$.MODULE$.fromRDD(this, .MODULE$.apply(String.class));
            this.bitmap$0 = true;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.asJavaRDD;
   }

   public JavaRDD asJavaRDD() {
      return !this.bitmap$0 ? this.asJavaRDD$lzycompute() : this.asJavaRDD;
   }

   public StringRRDD(final RDD parent, final byte[] func, final String deserializer, final byte[] packageNames, final Object[] broadcastVars, final ClassTag evidence$5) {
      super(parent, -1, func, deserializer, SerializationFormats$.MODULE$.STRING(), packageNames, (Broadcast[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(broadcastVars), new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Broadcast apply(final Object x) {
            return (Broadcast)x;
         }
      }, .MODULE$.apply(Broadcast.class)), evidence$5, .MODULE$.apply(String.class));
   }
}
