package org.apache.spark.graphx.impl;

import org.apache.spark.util.collection.BitSet;
import org.apache.spark.util.collection.OpenHashSet;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001duAB\n\u0015\u0011\u00031bD\u0002\u0004!)!\u0005a#\t\u0005\u0006a\u0005!\tA\r\u0005\u0006g\u0005!\t\u0001\u000e\u0005\b\u0003S\tA1AA\u0016\u000f\u001d\t9%\u0001E\u0002\u0003\u00132q!!\u0014\u0002\u0011\u0003\ty\u0005\u0003\u00041\r\u0011\u0005\u0011\u0011\f\u0005\b\u000372A\u0011AA/\u0011%\t9(AA\u0001\n\u0013\tIHB\u0003!)\u00011\u0002\b\u0003\u0005I\u0015\t\u0015\r\u0011\"\u0001J\u0011!\t&B!A!\u0002\u0013Q\u0005\u0002\u0003*\u000b\u0005\u000b\u0007I\u0011A*\t\u0011]S!\u0011!Q\u0001\nQC\u0001\u0002\u0017\u0006\u0003\u0006\u0004%\t!\u0017\u0005\tE*\u0011\t\u0011)A\u00055\"A1M\u0003B\u0002B\u0003-A\rC\u00031\u0015\u0011\u0005!.A\bWKJ$X\r\u001f)beRLG/[8o\u0015\t)b#\u0001\u0003j[Bd'BA\f\u0019\u0003\u00199'/\u00199iq*\u0011\u0011DG\u0001\u0006gB\f'o\u001b\u0006\u00037q\ta!\u00199bG\",'\"A\u000f\u0002\u0007=\u0014x\r\u0005\u0002 \u00035\tACA\bWKJ$X\r\u001f)beRLG/[8o'\r\t!\u0005\u000b\t\u0003G\u0019j\u0011\u0001\n\u0006\u0002K\u0005)1oY1mC&\u0011q\u0005\n\u0002\u0007\u0003:L(+\u001a4\u0011\u0005%rS\"\u0001\u0016\u000b\u0005-b\u0013AA5p\u0015\u0005i\u0013\u0001\u00026bm\u0006L!a\f\u0016\u0003\u0019M+'/[1mSj\f'\r\\3\u0002\rqJg.\u001b;?\u0007\u0001!\u0012AH\u0001\u0006CB\u0004H._\u000b\u0003kI$\"A\u000e<\u0015\u0005]\u001a\bcA\u0010\u000bcV\u0011\u0011hP\n\u0003\u0015i\u00022aH\u001e>\u0013\taDCA\nWKJ$X\r\u001f)beRLG/[8o\u0005\u0006\u001cX\r\u0005\u0002?\u007f1\u0001A!\u0002!\u000b\u0005\u0004\t%A\u0001,E#\t\u0011U\t\u0005\u0002$\u0007&\u0011A\t\n\u0002\b\u001d>$\b.\u001b8h!\t\u0019c)\u0003\u0002HI\t\u0019\u0011I\\=\u0002\u000b%tG-\u001a=\u0016\u0003)\u0003\"a\u0013(\u000f\u0005}a\u0015BA'\u0015\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u0014)\u0003%Y+'\u000f^3y\u0013\u0012$v.\u00138eKbl\u0015\r\u001d\u0006\u0003\u001bR\ta!\u001b8eKb\u0004\u0013A\u0002<bYV,7/F\u0001U!\r\u0019S+P\u0005\u0003-\u0012\u0012Q!\u0011:sCf\fqA^1mk\u0016\u001c\b%\u0001\u0003nCN\\W#\u0001.\u0011\u0005m\u0003W\"\u0001/\u000b\u0005us\u0016AC2pY2,7\r^5p]*\u0011q\fG\u0001\u0005kRLG.\u0003\u0002b9\n1!)\u001b;TKR\fQ!\\1tW\u0002\n!\"\u001a<jI\u0016t7-\u001a\u00135!\r)\u0007.P\u0007\u0002M*\u0011q\rJ\u0001\be\u00164G.Z2u\u0013\tIgM\u0001\u0005DY\u0006\u001c8\u000fV1h)\u0011Ygn\u001c9\u0015\u00051l\u0007cA\u0010\u000b{!)1M\u0005a\u0002I\")\u0001J\u0005a\u0001\u0015\")!K\u0005a\u0001)\")\u0001L\u0005a\u00015B\u0011aH\u001d\u0003\u0006\u0001\u000e\u0011\r!\u0011\u0005\bi\u000e\t\t\u0011q\u0001v\u0003))g/\u001b3f]\u000e,G%\r\t\u0004K\"\f\b\"B<\u0004\u0001\u0004A\u0018\u0001B5uKJ\u0004R!_A\u0001\u0003\u000fq!A_@\u000f\u0005mtX\"\u0001?\u000b\u0005u\f\u0014A\u0002\u001fs_>$h(C\u0001&\u0013\tiE%\u0003\u0003\u0002\u0004\u0005\u0015!\u0001C%uKJ\fGo\u001c:\u000b\u00055#\u0003CB\u0012\u0002\n\u00055\u0011/C\u0002\u0002\f\u0011\u0012a\u0001V;qY\u0016\u0014\u0004\u0003BA\b\u0003GqA!!\u0005\u0002\"9!\u00111CA\u0010\u001d\u0011\t)\"!\b\u000f\t\u0005]\u00111\u0004\b\u0004w\u0006e\u0011\"A\u000f\n\u0005ma\u0012BA\r\u001b\u0013\t9\u0002$\u0003\u0002N-%!\u0011QEA\u0014\u0005!1VM\u001d;fq&#'BA'\u0017\u00039\u0001\u0018M\u001d;ji&|g\u000eV8PaN,B!!\f\u0002:Q!\u0011qFA!)\u0011\t\t$a\u000f\u0011\u000b}\t\u0019$a\u000e\n\u0007\u0005UBC\u0001\nWKJ$X\r\u001f)beRLG/[8o\u001fB\u001c\bc\u0001 \u0002:\u0011)\u0001\t\u0002b\u0001\u0003\"I\u0011Q\b\u0003\u0002\u0002\u0003\u000f\u0011qH\u0001\u000bKZLG-\u001a8dK\u0012\u0012\u0004\u0003B3i\u0003oAq!a\u0011\u0005\u0001\u0004\t)%A\u0005qCJ$\u0018\u000e^5p]B!qDCA\u001c\u0003u1VM\u001d;fqB\u000b'\u000f^5uS>tw\n]:D_:\u001cHO];di>\u0014\bcAA&\r5\t\u0011AA\u000fWKJ$X\r\u001f)beRLG/[8o\u001fB\u001c8i\u001c8tiJ,8\r^8s'\u00111!%!\u0015\u0011\u000b}\t\u0019&a\u0016\n\u0007\u0005UCCA\u0011WKJ$X\r\u001f)beRLG/[8o\u0005\u0006\u001cXm\u00149t\u0007>t7\u000f\u001e:vGR|'\u000f\u0005\u0002 \u0015Q\u0011\u0011\u0011J\u0001\u0006i>|\u0005o]\u000b\u0005\u0003?\nY\u0007\u0006\u0003\u0002b\u0005MD\u0003BA2\u0003[\u0002raHA3\u0003S\n9&C\u0002\u0002hQ\u0011aCV3si\u0016D\b+\u0019:uSRLwN\u001c\"bg\u0016|\u0005o\u001d\t\u0004}\u0005-D!\u0002!\t\u0005\u0004\t\u0005\"CA8\u0011\u0005\u0005\t9AA9\u0003))g/\u001b3f]\u000e,Ge\r\t\u0005K\"\fI\u0007C\u0004\u0002D!\u0001\r!!\u001e\u0011\t}Q\u0011\u0011N\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003w\u0002B!! \u0002\u00046\u0011\u0011q\u0010\u0006\u0004\u0003\u0003c\u0013\u0001\u00027b]\u001eLA!!\"\u0002\u0000\t1qJ\u00196fGR\u0004"
)
public class VertexPartition extends VertexPartitionBase {
   private final OpenHashSet index;
   private final Object values;
   private final BitSet mask;

   public static VertexPartitionOps partitionToOps(final VertexPartition partition, final ClassTag evidence$2) {
      return VertexPartition$.MODULE$.partitionToOps(partition, evidence$2);
   }

   public OpenHashSet index() {
      return this.index;
   }

   public Object values() {
      return this.values;
   }

   public BitSet mask() {
      return this.mask;
   }

   public VertexPartition(final OpenHashSet index, final Object values, final BitSet mask, final ClassTag evidence$4) {
      super(evidence$4);
      this.index = index;
      this.values = values;
      this.mask = mask;
   }

   public static class VertexPartitionOpsConstructor$ implements VertexPartitionBaseOpsConstructor {
      public static final VertexPartitionOpsConstructor$ MODULE$ = new VertexPartitionOpsConstructor$();

      public VertexPartitionBaseOps toOps(final VertexPartition partition, final ClassTag evidence$3) {
         return VertexPartition$.MODULE$.partitionToOps(partition, evidence$3);
      }
   }
}
