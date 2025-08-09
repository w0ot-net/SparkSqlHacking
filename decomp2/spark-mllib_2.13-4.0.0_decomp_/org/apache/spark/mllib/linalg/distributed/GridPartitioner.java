package org.apache.spark.mllib.linalg.distributed;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partitioner;
import org.sparkproject.guava.base.Objects;
import scala.Tuple2;
import scala.Tuple3;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015a!\u0002\u000e\u001c\u0001}9\u0003\u0002\u0003\u0017\u0001\u0005\u000b\u0007I\u0011\u0001\u0018\t\u0011U\u0002!\u0011!Q\u0001\n=B\u0001B\u000e\u0001\u0003\u0006\u0004%\tA\f\u0005\to\u0001\u0011\t\u0011)A\u0005_!A\u0001\b\u0001BC\u0002\u0013\u0005a\u0006\u0003\u0005:\u0001\t\u0005\t\u0015!\u00030\u0011!Q\u0004A!b\u0001\n\u0003q\u0003\u0002C\u001e\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u000bq\u0002A\u0011A\u001f\t\u000f\u0011\u0003!\u0019!C\u0005]!1Q\t\u0001Q\u0001\n=BqA\u0012\u0001C\u0002\u0013%a\u0006\u0003\u0004H\u0001\u0001\u0006Ia\f\u0005\b\u0011\u0002\u0011\r\u0011\"\u0011/\u0011\u0019I\u0005\u0001)A\u0005_!)!\n\u0001C!\u0017\")\u0011\u000b\u0001C\u0005%\")q\u000b\u0001C!1\")a\f\u0001C!?\u001e1\u0001m\u0007E\u0001?\u00054aAG\u000e\t\u0002}\u0011\u0007\"\u0002\u001f\u0016\t\u0003q\u0007\"B8\u0016\t\u0003\u0001\b\"B8\u0016\t\u0003)\bb\u0002>\u0016\u0003\u0003%Ia\u001f\u0002\u0010\u000fJLG\rU1si&$\u0018n\u001c8fe*\u0011A$H\u0001\fI&\u001cHO]5ckR,GM\u0003\u0002\u001f?\u00051A.\u001b8bY\u001eT!\u0001I\u0011\u0002\u000b5dG.\u001b2\u000b\u0005\t\u001a\u0013!B:qCJ\\'B\u0001\u0013&\u0003\u0019\t\u0007/Y2iK*\ta%A\u0002pe\u001e\u001c\"\u0001\u0001\u0015\u0011\u0005%RS\"A\u0011\n\u0005-\n#a\u0003)beRLG/[8oKJ\fAA]8xg\u000e\u0001Q#A\u0018\u0011\u0005A\u001aT\"A\u0019\u000b\u0003I\nQa]2bY\u0006L!\u0001N\u0019\u0003\u0007%sG/A\u0003s_^\u001c\b%\u0001\u0003d_2\u001c\u0018!B2pYN\u0004\u0013a\u0003:poN\u0004VM\u001d)beR\fAB]8xgB+'\u000fU1si\u0002\n1bY8mgB+'\u000fU1si\u0006a1m\u001c7t!\u0016\u0014\b+\u0019:uA\u00051A(\u001b8jiz\"RA\u0010!B\u0005\u000e\u0003\"a\u0010\u0001\u000e\u0003mAQ\u0001L\u0005A\u0002=BQAN\u0005A\u0002=BQ\u0001O\u0005A\u0002=BQAO\u0005A\u0002=\nQB]8x!\u0006\u0014H/\u001b;j_:\u001c\u0018A\u0004:poB\u000b'\u000f^5uS>t7\u000fI\u0001\u000eG>d\u0007+\u0019:uSRLwN\\:\u0002\u001d\r|G\u000eU1si&$\u0018n\u001c8tA\u0005ia.^7QCJ$\u0018\u000e^5p]N\faB\\;n!\u0006\u0014H/\u001b;j_:\u001c\b%\u0001\u0007hKR\u0004\u0016M\u001d;ji&|g\u000e\u0006\u00020\u0019\")Q\n\u0005a\u0001\u001d\u0006\u00191.Z=\u0011\u0005Az\u0015B\u0001)2\u0005\r\te._\u0001\u000fO\u0016$\b+\u0019:uSRLwN\\%e)\ry3+\u0016\u0005\u0006)F\u0001\raL\u0001\u0002S\")a+\u0005a\u0001_\u0005\t!.\u0001\u0004fcV\fGn\u001d\u000b\u00033r\u0003\"\u0001\r.\n\u0005m\u000b$a\u0002\"p_2,\u0017M\u001c\u0005\u0006;J\u0001\rAT\u0001\u0004_\nT\u0017\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003=\nqb\u0012:jIB\u000b'\u000f^5uS>tWM\u001d\t\u0003\u007fU\u00192!F2g!\t\u0001D-\u0003\u0002fc\t1\u0011I\\=SK\u001a\u0004\"a\u001a7\u000e\u0003!T!!\u001b6\u0002\u0005%|'\"A6\u0002\t)\fg/Y\u0005\u0003[\"\u0014AbU3sS\u0006d\u0017N_1cY\u0016$\u0012!Y\u0001\u0006CB\u0004H.\u001f\u000b\u0006}E\u00148\u000f\u001e\u0005\u0006Y]\u0001\ra\f\u0005\u0006m]\u0001\ra\f\u0005\u0006q]\u0001\ra\f\u0005\u0006u]\u0001\ra\f\u000b\u0005}Y<\b\u0010C\u0003-1\u0001\u0007q\u0006C\u000371\u0001\u0007q\u0006C\u0003z1\u0001\u0007q&\u0001\ftk\u001e<Wm\u001d;fI:+X\u000eU1si&$\u0018n\u001c8t\u000319(/\u001b;f%\u0016\u0004H.Y2f)\u0005a\bcA?\u0002\u00025\taP\u0003\u0002\u0000U\u0006!A.\u00198h\u0013\r\t\u0019A \u0002\u0007\u001f\nTWm\u0019;"
)
public class GridPartitioner extends Partitioner {
   private final int rows;
   private final int cols;
   private final int rowsPerPart;
   private final int colsPerPart;
   private final int rowPartitions;
   private final int colPartitions;
   private final int numPartitions;

   public static GridPartitioner apply(final int rows, final int cols, final int suggestedNumPartitions) {
      return GridPartitioner$.MODULE$.apply(rows, cols, suggestedNumPartitions);
   }

   public static GridPartitioner apply(final int rows, final int cols, final int rowsPerPart, final int colsPerPart) {
      return GridPartitioner$.MODULE$.apply(rows, cols, rowsPerPart, colsPerPart);
   }

   public int rows() {
      return this.rows;
   }

   public int cols() {
      return this.cols;
   }

   public int rowsPerPart() {
      return this.rowsPerPart;
   }

   public int colsPerPart() {
      return this.colsPerPart;
   }

   private int rowPartitions() {
      return this.rowPartitions;
   }

   private int colPartitions() {
      return this.colPartitions;
   }

   public int numPartitions() {
      return this.numPartitions;
   }

   public int getPartition(final Object key) {
      if (key instanceof Integer) {
         int var4 = BoxesRunTime.unboxToInt(key);
         return var4;
      } else {
         if (key instanceof Tuple2) {
            Tuple2 var5 = (Tuple2)key;
            Object i = var5._1();
            Object j = var5._2();
            if (i instanceof Integer) {
               int var8 = BoxesRunTime.unboxToInt(i);
               if (j instanceof Integer) {
                  int var9 = BoxesRunTime.unboxToInt(j);
                  return this.getPartitionId(var8, var9);
               }
            }
         }

         if (key instanceof Tuple3) {
            Tuple3 var10 = (Tuple3)key;
            Object i = var10._1();
            Object j = var10._2();
            if (i instanceof Integer) {
               int var13 = BoxesRunTime.unboxToInt(i);
               if (j instanceof Integer) {
                  int var14 = BoxesRunTime.unboxToInt(j);
                  if (var10._3() instanceof Integer) {
                     return this.getPartitionId(var13, var14);
                  }
               }
            }
         }

         throw new IllegalArgumentException("Unrecognized key: " + key + ".");
      }
   }

   private int getPartitionId(final int i, final int j) {
      .MODULE$.require(0 <= i && i < this.rows(), () -> "Row index " + i + " out of range [0, " + this.rows() + ").");
      .MODULE$.require(0 <= j && j < this.cols(), () -> "Column index " + j + " out of range [0, " + this.cols() + ").");
      return i / this.rowsPerPart() + j / this.colsPerPart() * this.rowPartitions();
   }

   public boolean equals(final Object obj) {
      if (!(obj instanceof GridPartitioner var4)) {
         return false;
      } else {
         return this.rows() == var4.rows() && this.cols() == var4.cols() && this.rowsPerPart() == var4.rowsPerPart() && this.colsPerPart() == var4.colsPerPart();
      }
   }

   public int hashCode() {
      return Objects.hashCode(new Object[]{.MODULE$.int2Integer(this.rows()), .MODULE$.int2Integer(this.cols()), .MODULE$.int2Integer(this.rowsPerPart()), .MODULE$.int2Integer(this.colsPerPart())});
   }

   public GridPartitioner(final int rows, final int cols, final int rowsPerPart, final int colsPerPart) {
      this.rows = rows;
      this.cols = cols;
      this.rowsPerPart = rowsPerPart;
      this.colsPerPart = colsPerPart;
      .MODULE$.require(rows > 0);
      .MODULE$.require(cols > 0);
      .MODULE$.require(rowsPerPart > 0);
      .MODULE$.require(colsPerPart > 0);
      this.rowPartitions = (int)scala.math.package..MODULE$.ceil((double)rows * (double)1.0F / (double)rowsPerPart);
      this.colPartitions = (int)scala.math.package..MODULE$.ceil((double)cols * (double)1.0F / (double)colsPerPart);
      this.numPartitions = this.rowPartitions() * this.colPartitions();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
