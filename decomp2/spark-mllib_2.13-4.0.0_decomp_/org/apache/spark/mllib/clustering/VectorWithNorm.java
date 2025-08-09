package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A4Q\u0001E\t\u0001#mA\u0001b\f\u0001\u0003\u0006\u0004%\t\u0001\r\u0005\to\u0001\u0011\t\u0011)A\u0005c!A\u0001\b\u0001BC\u0002\u0013\u0005\u0011\b\u0003\u0005>\u0001\t\u0005\t\u0015!\u0003;\u0011!q\u0004A!b\u0001\n\u0003I\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u000b\u0001\u0003A\u0011A!\t\u000b\u0001\u0003A\u0011A$\t\u000b\u0001\u0003A\u0011A%\t\u000b=\u0003A\u0011\u0001)\b\u0011E\u000b\u0012\u0011!E\u0001#I3\u0001\u0002E\t\u0002\u0002#\u0005\u0011c\u0015\u0005\u0006\u00012!\ta\u0017\u0005\b92\t\n\u0011\"\u0001^\u0011\u001dAG\"!A\u0005\n%\u0014aBV3di>\u0014x+\u001b;i\u001d>\u0014XN\u0003\u0002\u0013'\u0005Q1\r\\;ti\u0016\u0014\u0018N\\4\u000b\u0005Q)\u0012!B7mY&\u0014'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0014\u0007\u0001a\"\u0005\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcD\u0001\u0004B]f\u0014VM\u001a\t\u0003G1r!\u0001\n\u0016\u000f\u0005\u0015JS\"\u0001\u0014\u000b\u0005\u001dB\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003}I!a\u000b\u0010\u0002\u000fA\f7m[1hK&\u0011QF\f\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003Wy\taA^3di>\u0014X#A\u0019\u0011\u0005I*T\"A\u001a\u000b\u0005Q\u001a\u0012A\u00027j]\u0006dw-\u0003\u00027g\t1a+Z2u_J\fqA^3di>\u0014\b%\u0001\u0003o_JlW#\u0001\u001e\u0011\u0005uY\u0014B\u0001\u001f\u001f\u0005\u0019!u.\u001e2mK\u0006)an\u001c:nA\u00051q/Z5hQR\fqa^3jO\"$\b%\u0001\u0004=S:LGO\u0010\u000b\u0005\u0005\u0012+e\t\u0005\u0002D\u00015\t\u0011\u0003C\u00030\u000f\u0001\u0007\u0011\u0007C\u00039\u000f\u0001\u0007!\bC\u0004?\u000fA\u0005\t\u0019\u0001\u001e\u0015\u0005\tC\u0005\"B\u0018\t\u0001\u0004\tDC\u0001\"K\u0011\u0015Y\u0015\u00021\u0001M\u0003\u0015\t'O]1z!\riRJO\u0005\u0003\u001dz\u0011Q!\u0011:sCf\fq\u0001^8EK:\u001cX-F\u0001C\u000391Vm\u0019;pe^KG\u000f\u001b(pe6\u0004\"a\u0011\u0007\u0014\u00071aB\u000b\u0005\u0002V56\taK\u0003\u0002X1\u0006\u0011\u0011n\u001c\u0006\u00023\u0006!!.\u0019<b\u0013\tic\u000bF\u0001S\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU\taL\u000b\u0002;?.\n\u0001\r\u0005\u0002bM6\t!M\u0003\u0002dI\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003Kz\t!\"\u00198o_R\fG/[8o\u0013\t9'MA\tv]\u000eDWmY6fIZ\u000b'/[1oG\u0016\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012A\u001b\t\u0003W:l\u0011\u0001\u001c\u0006\u0003[b\u000bA\u0001\\1oO&\u0011q\u000e\u001c\u0002\u0007\u001f\nTWm\u0019;"
)
public class VectorWithNorm implements Serializable {
   private final Vector vector;
   private final double norm;
   private final double weight;

   public static double $lessinit$greater$default$3() {
      return VectorWithNorm$.MODULE$.$lessinit$greater$default$3();
   }

   public Vector vector() {
      return this.vector;
   }

   public double norm() {
      return this.norm;
   }

   public double weight() {
      return this.weight;
   }

   public VectorWithNorm toDense() {
      return new VectorWithNorm(Vectors$.MODULE$.dense(this.vector().toArray()), this.norm(), this.weight());
   }

   public VectorWithNorm(final Vector vector, final double norm, final double weight) {
      this.vector = vector;
      this.norm = norm;
      this.weight = weight;
   }

   public VectorWithNorm(final Vector vector) {
      this(vector, Vectors$.MODULE$.norm(vector, (double)2.0F), VectorWithNorm$.MODULE$.$lessinit$greater$default$3());
   }

   public VectorWithNorm(final double[] array) {
      this(Vectors$.MODULE$.dense(array));
   }
}
