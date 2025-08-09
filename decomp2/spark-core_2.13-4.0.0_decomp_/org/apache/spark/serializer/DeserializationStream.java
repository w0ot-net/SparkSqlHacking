package org.apache.spark.serializer;

import java.io.Closeable;
import java.io.EOFException;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.util.NextIterator;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005E4Q\u0001C\u0005\u0002\u0002IAQ!\t\u0001\u0005\u0002\tBQ!\n\u0001\u0007\u0002\u0019BQa\u0010\u0001\u0005\u0002\u0001CQ\u0001\u0013\u0001\u0005\u0002%CQ!\u0015\u0001\u0007BICQA\u0016\u0001\u0005\u0002]CQ\u0001\u001a\u0001\u0005\u0002\u0015\u0014Q\u0003R3tKJL\u0017\r\\5{CRLwN\\*ue\u0016\fWN\u0003\u0002\u000b\u0017\u0005Q1/\u001a:jC2L'0\u001a:\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\u0001aE\u0002\u0001'm\u0001\"\u0001F\r\u000e\u0003UQ!AF\f\u0002\t1\fgn\u001a\u0006\u00021\u0005!!.\u0019<b\u0013\tQRC\u0001\u0004PE*,7\r\u001e\t\u00039}i\u0011!\b\u0006\u0003=]\t!![8\n\u0005\u0001j\"!C\"m_N,\u0017M\u00197f\u0003\u0019a\u0014N\\5u}Q\t1\u0005\u0005\u0002%\u00015\t\u0011\"\u0001\u0006sK\u0006$wJ\u00196fGR,\"aJ\u0016\u0015\u0003!\"\"!K\u001c\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\t\u0011\r!\f\u0002\u0002)F\u0011a\u0006\u000e\t\u0003_Ij\u0011\u0001\r\u0006\u0002c\u0005)1oY1mC&\u00111\u0007\r\u0002\b\u001d>$\b.\u001b8h!\tyS'\u0003\u00027a\t\u0019\u0011I\\=\t\u000fa\u0012\u0011\u0011!a\u0002s\u0005QQM^5eK:\u001cW\r\n\u001d\u0011\u0007ij\u0014&D\u0001<\u0015\ta\u0004'A\u0004sK\u001adWm\u0019;\n\u0005yZ$\u0001C\"mCN\u001cH+Y4\u0002\u000fI,\u0017\rZ&fsV\u0011\u0011\t\u0012\u000b\u0002\u0005R\u00111)\u0012\t\u0003U\u0011#Q\u0001L\u0002C\u00025BqAR\u0002\u0002\u0002\u0003\u000fq)\u0001\u0006fm&$WM\\2fIe\u00022AO\u001fD\u0003%\u0011X-\u00193WC2,X-\u0006\u0002K\u001bR\t1\n\u0006\u0002M\u001dB\u0011!&\u0014\u0003\u0006Y\u0011\u0011\r!\f\u0005\b\u001f\u0012\t\t\u0011q\u0001Q\u0003-)g/\u001b3f]\u000e,G%\r\u0019\u0011\u0007ijD*A\u0003dY>\u001cX\rF\u0001T!\tyC+\u0003\u0002Va\t!QK\\5u\u0003)\t7/\u0013;fe\u0006$xN]\u000b\u00021B\u0019\u0011,\u0019\u001b\u000f\u0005i{fBA._\u001b\u0005a&BA/\u0012\u0003\u0019a$o\\8u}%\t\u0011'\u0003\u0002aa\u00059\u0001/Y2lC\u001e,\u0017B\u00012d\u0005!IE/\u001a:bi>\u0014(B\u000111\u0003I\t7oS3z-\u0006dW/Z%uKJ\fGo\u001c:\u0016\u0003\u0019\u00042!W1h!\u0011y\u0003\u000e\u000e\u001b\n\u0005%\u0004$A\u0002+va2,'\u0007\u000b\u0002\u0001WB\u0011An\\\u0007\u0002[*\u0011anC\u0001\u000bC:tw\u000e^1uS>t\u0017B\u00019n\u00051!UM^3m_B,'/\u00119j\u0001"
)
public abstract class DeserializationStream implements Closeable {
   public abstract Object readObject(final ClassTag evidence$8);

   public Object readKey(final ClassTag evidence$9) {
      return this.readObject(evidence$9);
   }

   public Object readValue(final ClassTag evidence$10) {
      return this.readObject(evidence$10);
   }

   public abstract void close();

   public Iterator asIterator() {
      return new NextIterator() {
         // $FF: synthetic field
         private final DeserializationStream $outer;

         public Object getNext() {
            Object var10000;
            try {
               var10000 = this.$outer.readObject(.MODULE$.Any());
            } catch (EOFException var2) {
               this.finished_$eq(true);
               var10000 = null;
            }

            return var10000;
         }

         public void close() {
            this.$outer.close();
         }

         public {
            if (DeserializationStream.this == null) {
               throw null;
            } else {
               this.$outer = DeserializationStream.this;
            }
         }
      };
   }

   public Iterator asKeyValueIterator() {
      return new NextIterator() {
         // $FF: synthetic field
         private final DeserializationStream $outer;

         public Tuple2 getNext() {
            Tuple2 var10000;
            try {
               var10000 = new Tuple2(this.$outer.readKey(.MODULE$.Any()), this.$outer.readValue(.MODULE$.Any()));
            } catch (EOFException var2) {
               this.finished_$eq(true);
               var10000 = null;
            }

            return var10000;
         }

         public void close() {
            this.$outer.close();
         }

         public {
            if (DeserializationStream.this == null) {
               throw null;
            } else {
               this.$outer = DeserializationStream.this;
            }
         }
      };
   }
}
