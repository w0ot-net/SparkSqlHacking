package org.apache.spark.mllib.api.python;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import net.razorvine.pickle.PickleException;
import net.razorvine.pickle.PickleUtils;
import net.razorvine.pickle.Pickler;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.linalg.DenseMatrix;
import org.apache.spark.mllib.linalg.DenseVector;
import org.apache.spark.mllib.linalg.SparseMatrix;
import org.apache.spark.mllib.linalg.SparseVector;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.rdd.RDD;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=wAB\u0012%\u0011\u0003Q\u0003G\u0002\u00043I!\u0005!f\r\u0005\u0006\r\u0006!\ta\u0012\u0005\b\u0011\u0006\u0011\r\u0011\"\u0011J\u0011\u0019\u0011\u0016\u0001)A\u0005\u0015\u001a)1+\u0001\u0001%)\")a)\u0002C\u0001?\")\u0011-\u0002C\u0001E\"9\u0011\u0011A\u0003\u0005\u0002\u0005\raaBA\b\u0003\u0001!\u0013\u0011\u0003\u0005\u0007\r&!\t!a\u0007\t\r\u0005LA\u0011AA\u0010\u0011\u001d\t\t!\u0003C\u0001\u0003O1q!a\u000b\u0002\u0001\u0011\ni\u0003\u0003\u0004G\u001b\u0011\u0005\u0011q\u0007\u0005\u0007C6!\t!a\u000f\t\u000f\u0005\u0005Q\u0002\"\u0001\u0002D\u00199\u0011qI\u0001\u0001I\u0005%\u0003B\u0002$\u0012\t\u0003\t\u0019\u0006\u0003\u0004b#\u0011\u0005\u0011q\u000b\u0005\b\u0003\u0003\tB\u0011AA0\r\u001d\t\u0019'\u0001\u0001%\u0003KBaAR\u000b\u0005\u0002\u0005U\u0004BB1\u0016\t\u0003\tI\bC\u0004\u0002\u0002U!\t!!!\u0007\u000f\u0005\u0015\u0015\u0001\u0001\u0013\u0002\b\"1a)\u0007C\u0001\u0003/Ca!Y\r\u0005\u0002\u0005m\u0005bBA\u00013\u0011\u0005\u00111\u0015\u0005\b\u0003OKB\u0011BAU\u0011%\t\u0019,\u0001a\u0001\n\u0003\t)\fC\u0005\u0002>\u0006\u0001\r\u0011\"\u0001\u0002@\"A\u0011QY\u0001!B\u0013\t9\fC\u0004\u0002H\u0006!\t%!3\t\u0013\u0005-\u0017!!A\u0005\n\u00055\u0017!B*fe\u0012+'BA\u0013'\u0003\u0019\u0001\u0018\u0010\u001e5p]*\u0011q\u0005K\u0001\u0004CBL'BA\u0015+\u0003\u0015iG\u000e\\5c\u0015\tYC&A\u0003ta\u0006\u00148N\u0003\u0002.]\u00051\u0011\r]1dQ\u0016T\u0011aL\u0001\u0004_J<\u0007CA\u0019\u0002\u001b\u0005!#!B*fe\u0012+7cA\u00015oA\u0011\u0011'N\u0005\u0003m\u0011\u0012\u0011bU3s\t\u0016\u0014\u0015m]3\u0011\u0005a\u001aeBA\u001dA\u001d\tQd(D\u0001<\u0015\taT(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005y\u0014!B:dC2\f\u0017BA!C\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011aP\u0005\u0003\t\u0016\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!!\u0011\"\u0002\rqJg.\u001b;?)\u0005\u0001\u0014a\u0004)Z'B\u000b%kS0Q\u0003\u000e[\u0015iR#\u0016\u0003)\u0003\"a\u0013)\u000e\u00031S!!\u0014(\u0002\t1\fgn\u001a\u0006\u0002\u001f\u0006!!.\u0019<b\u0013\t\tFJ\u0001\u0004TiJLgnZ\u0001\u0011!f\u001b\u0006+\u0011*L?B\u000b5iS!H\u000b\u0002\u0012!\u0003R3og\u00164Vm\u0019;peBK7m\u001b7feN\u0011Q!\u0016\t\u0004-^KV\"A\u0001\n\u0005a+$a\u0003\"bg\u0016\u0004\u0016nY6mKJ\u0004\"AW/\u000e\u0003mS!\u0001\u0018\u0015\u0002\r1Lg.\u00197h\u0013\tq6LA\u0006EK:\u001cXMV3di>\u0014H#\u00011\u0011\u0005Y+\u0011!C:bm\u0016\u001cF/\u0019;f)\u0011\u0019w\r\u001c;\u0011\u0005\u0011,W\"\u0001\"\n\u0005\u0019\u0014%\u0001B+oSRDQ\u0001[\u0004A\u0002%\f1a\u001c2k!\tY%.\u0003\u0002l\u0019\n1qJ\u00196fGRDQ!\\\u0004A\u00029\f1a\\;u!\ty'/D\u0001q\u0015\t\th*\u0001\u0002j_&\u00111\u000f\u001d\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0005\u0006k\u001e\u0001\rA^\u0001\ba&\u001c7\u000e\\3s!\t9h0D\u0001y\u0015\tI(0\u0001\u0004qS\u000e\\G.\u001a\u0006\u0003wr\f\u0011B]1{_J4\u0018N\\3\u000b\u0003u\f1A\\3u\u0013\ty\bPA\u0004QS\u000e\\G.\u001a:\u0002\u0013\r|gn\u001d;sk\u000e$HcA5\u0002\u0006!9\u0011q\u0001\u0005A\u0002\u0005%\u0011\u0001B1sON\u0004B\u0001ZA\u0006S&\u0019\u0011Q\u0002\"\u0003\u000b\u0005\u0013(/Y=\u0003%\u0011+gn]3NCR\u0014\u0018\u000e\u001f)jG.dWM]\n\u0004\u0013\u0005M\u0001\u0003\u0002,X\u0003+\u00012AWA\f\u0013\r\tIb\u0017\u0002\f\t\u0016t7/Z'biJL\u0007\u0010\u0006\u0002\u0002\u001eA\u0011a+\u0003\u000b\bG\u0006\u0005\u00121EA\u0013\u0011\u0015A7\u00021\u0001j\u0011\u0015i7\u00021\u0001o\u0011\u0015)8\u00021\u0001w)\rI\u0017\u0011\u0006\u0005\b\u0003\u000fa\u0001\u0019AA\u0005\u0005M\u0019\u0006/\u0019:tK6\u000bGO]5y!&\u001c7\u000e\\3s'\ri\u0011q\u0006\t\u0005-^\u000b\t\u0004E\u0002[\u0003gI1!!\u000e\\\u00051\u0019\u0006/\u0019:tK6\u000bGO]5y)\t\tI\u0004\u0005\u0002W\u001bQ91-!\u0010\u0002@\u0005\u0005\u0003\"\u00025\u0010\u0001\u0004I\u0007\"B7\u0010\u0001\u0004q\u0007\"B;\u0010\u0001\u00041HcA5\u0002F!9\u0011q\u0001\tA\u0002\u0005%!aE*qCJ\u001cXMV3di>\u0014\b+[2lY\u0016\u00148cA\t\u0002LA!akVA'!\rQ\u0016qJ\u0005\u0004\u0003#Z&\u0001D*qCJ\u001cXMV3di>\u0014HCAA+!\t1\u0016\u0003F\u0004d\u00033\nY&!\u0018\t\u000b!\u001c\u0002\u0019A5\t\u000b5\u001c\u0002\u0019\u00018\t\u000bU\u001c\u0002\u0019\u0001<\u0015\u0007%\f\t\u0007C\u0004\u0002\bQ\u0001\r!!\u0003\u0003'1\u000b'-\u001a7fIB{\u0017N\u001c;QS\u000e\\G.\u001a:\u0014\u0007U\t9\u0007\u0005\u0003W/\u0006%\u0004\u0003BA6\u0003cj!!!\u001c\u000b\u0007\u0005=\u0004&\u0001\u0006sK\u001e\u0014Xm]:j_:LA!a\u001d\u0002n\taA*\u00192fY\u0016$\u0007k\\5oiR\u0011\u0011q\u000f\t\u0003-V!raYA>\u0003{\ny\bC\u0003i/\u0001\u0007\u0011\u000eC\u0003n/\u0001\u0007a\u000eC\u0003v/\u0001\u0007a\u000fF\u0002j\u0003\u0007Cq!a\u0002\u0019\u0001\u0004\tIAA\u0007SCRLgn\u001a)jG.dWM]\n\u00043\u0005%\u0005\u0003\u0002,X\u0003\u0017\u0003B!!$\u0002\u00146\u0011\u0011q\u0012\u0006\u0004\u0003#C\u0013A\u0004:fG>lW.\u001a8eCRLwN\\\u0005\u0005\u0003+\u000byI\u0001\u0004SCRLgn\u001a\u000b\u0003\u00033\u0003\"AV\r\u0015\u000f\r\fi*a(\u0002\"\")\u0001n\u0007a\u0001S\")Qn\u0007a\u0001]\")Qo\u0007a\u0001mR\u0019\u0011.!*\t\u000f\u0005\u001dA\u00041\u0001\u0002\n\u0005\u0011\"/\u0019;j]\u001e\u001c\u0018\nZ\"iK\u000e\\Gj\u001c8h)\u0011\tY+!-\u0011\u0007\u0011\fi+C\u0002\u00020\n\u00131!\u00138u\u0011\u0015AW\u00041\u0001j\u0003-Ig.\u001b;jC2L'0\u001a3\u0016\u0005\u0005]\u0006c\u00013\u0002:&\u0019\u00111\u0018\"\u0003\u000f\t{w\u000e\\3b]\u0006y\u0011N\\5uS\u0006d\u0017N_3e?\u0012*\u0017\u000fF\u0002d\u0003\u0003D\u0011\"a1 \u0003\u0003\u0005\r!a.\u0002\u0007a$\u0013'\u0001\u0007j]&$\u0018.\u00197ju\u0016$\u0007%\u0001\u0006j]&$\u0018.\u00197ju\u0016$\u0012aY\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002S\u0002"
)
public final class SerDe {
   public static void initialize() {
      SerDe$.MODULE$.initialize();
   }

   public static void initialized_$eq(final boolean x$1) {
      SerDe$.MODULE$.initialized_$eq(x$1);
   }

   public static boolean initialized() {
      return SerDe$.MODULE$.initialized();
   }

   public static String PYSPARK_PACKAGE() {
      return SerDe$.MODULE$.PYSPARK_PACKAGE();
   }

   public static JavaRDD pythonToJava(final JavaRDD pyRDD, final boolean batched) {
      return SerDe$.MODULE$.pythonToJava(pyRDD, batched);
   }

   public static JavaRDD javaToPython(final JavaRDD jRDD) {
      return SerDe$.MODULE$.javaToPython(jRDD);
   }

   public static RDD fromTuple2RDD(final RDD rdd) {
      return SerDe$.MODULE$.fromTuple2RDD(rdd);
   }

   public static RDD asTupleRDD(final RDD rdd) {
      return SerDe$.MODULE$.asTupleRDD(rdd);
   }

   public static Object loads(final byte[] bytes) {
      return SerDe$.MODULE$.loads(bytes);
   }

   public static byte[] dumps(final Object obj) {
      return SerDe$.MODULE$.dumps(obj);
   }

   public static class DenseVectorPickler extends SerDeBase.BasePickler {
      public void saveState(final Object obj, final OutputStream out, final Pickler pickler) {
         DenseVector vector = (DenseVector)obj;
         byte[] bytes = new byte[8 * vector.size()];
         ByteBuffer bb = ByteBuffer.wrap(bytes);
         bb.order(ByteOrder.nativeOrder());
         DoubleBuffer db = bb.asDoubleBuffer();
         db.put(vector.values());
         out.write(84);
         out.write(PickleUtils.integer_to_bytes(bytes.length));
         out.write(bytes);
         out.write(133);
      }

      public Object construct(final Object[] args) {
         .MODULE$.require(args.length == 1);
         if (args.length != 1) {
            throw new PickleException("should be 1");
         } else {
            byte[] bytes = this.getBytes(args[0]);
            ByteBuffer bb = ByteBuffer.wrap(bytes, 0, bytes.length);
            bb.order(ByteOrder.nativeOrder());
            DoubleBuffer db = bb.asDoubleBuffer();
            double[] ans = new double[bytes.length / 8];
            db.get(ans);
            return Vectors$.MODULE$.dense(ans);
         }
      }

      public DenseVectorPickler() {
         super(scala.reflect.ClassTag..MODULE$.apply(DenseVector.class));
      }
   }

   public static class DenseMatrixPickler extends SerDeBase.BasePickler {
      public void saveState(final Object obj, final OutputStream out, final Pickler pickler) {
         DenseMatrix m = (DenseMatrix)obj;
         byte[] bytes = new byte[8 * m.values().length];
         ByteOrder order = ByteOrder.nativeOrder();
         int isTransposed = m.isTransposed() ? 1 : 0;
         ByteBuffer.wrap(bytes).order(order).asDoubleBuffer().put(m.values());
         out.write(40);
         out.write(74);
         out.write(PickleUtils.integer_to_bytes(m.numRows()));
         out.write(74);
         out.write(PickleUtils.integer_to_bytes(m.numCols()));
         out.write(84);
         out.write(PickleUtils.integer_to_bytes(bytes.length));
         out.write(bytes);
         out.write(74);
         out.write(PickleUtils.integer_to_bytes(isTransposed));
         out.write(116);
      }

      public Object construct(final Object[] args) {
         if (args.length != 4) {
            throw new PickleException("should be 4");
         } else {
            byte[] bytes = this.getBytes(args[2]);
            int n = bytes.length / 8;
            double[] values = new double[n];
            ByteOrder order = ByteOrder.nativeOrder();
            ByteBuffer.wrap(bytes).order(order).asDoubleBuffer().get(values);
            boolean isTransposed = BoxesRunTime.unboxToInt(args[3]) == 1;
            return new DenseMatrix(BoxesRunTime.unboxToInt(args[0]), BoxesRunTime.unboxToInt(args[1]), values, isTransposed);
         }
      }

      public DenseMatrixPickler() {
         super(scala.reflect.ClassTag..MODULE$.apply(DenseMatrix.class));
      }
   }

   public static class SparseMatrixPickler extends SerDeBase.BasePickler {
      public void saveState(final Object obj, final OutputStream out, final Pickler pickler) {
         SparseMatrix s = (SparseMatrix)obj;
         ByteOrder order = ByteOrder.nativeOrder();
         byte[] colPtrsBytes = new byte[4 * s.colPtrs().length];
         byte[] indicesBytes = new byte[4 * s.rowIndices().length];
         byte[] valuesBytes = new byte[8 * s.values().length];
         int isTransposed = s.isTransposed() ? 1 : 0;
         ByteBuffer.wrap(colPtrsBytes).order(order).asIntBuffer().put(s.colPtrs());
         ByteBuffer.wrap(indicesBytes).order(order).asIntBuffer().put(s.rowIndices());
         ByteBuffer.wrap(valuesBytes).order(order).asDoubleBuffer().put(s.values());
         out.write(40);
         out.write(74);
         out.write(PickleUtils.integer_to_bytes(s.numRows()));
         out.write(74);
         out.write(PickleUtils.integer_to_bytes(s.numCols()));
         out.write(84);
         out.write(PickleUtils.integer_to_bytes(colPtrsBytes.length));
         out.write(colPtrsBytes);
         out.write(84);
         out.write(PickleUtils.integer_to_bytes(indicesBytes.length));
         out.write(indicesBytes);
         out.write(84);
         out.write(PickleUtils.integer_to_bytes(valuesBytes.length));
         out.write(valuesBytes);
         out.write(74);
         out.write(PickleUtils.integer_to_bytes(isTransposed));
         out.write(116);
      }

      public Object construct(final Object[] args) {
         if (args.length != 6) {
            throw new PickleException("should be 6");
         } else {
            ByteOrder order = ByteOrder.nativeOrder();
            byte[] colPtrsBytes = this.getBytes(args[2]);
            byte[] indicesBytes = this.getBytes(args[3]);
            byte[] valuesBytes = this.getBytes(args[4]);
            int[] colPtrs = new int[colPtrsBytes.length / 4];
            int[] rowIndices = new int[indicesBytes.length / 4];
            double[] values = new double[valuesBytes.length / 8];
            ByteBuffer.wrap(colPtrsBytes).order(order).asIntBuffer().get(colPtrs);
            ByteBuffer.wrap(indicesBytes).order(order).asIntBuffer().get(rowIndices);
            ByteBuffer.wrap(valuesBytes).order(order).asDoubleBuffer().get(values);
            boolean isTransposed = BoxesRunTime.unboxToInt(args[5]) == 1;
            return new SparseMatrix(BoxesRunTime.unboxToInt(args[0]), BoxesRunTime.unboxToInt(args[1]), colPtrs, rowIndices, values, isTransposed);
         }
      }

      public SparseMatrixPickler() {
         super(scala.reflect.ClassTag..MODULE$.apply(SparseMatrix.class));
      }
   }

   public static class SparseVectorPickler extends SerDeBase.BasePickler {
      public void saveState(final Object obj, final OutputStream out, final Pickler pickler) {
         SparseVector v = (SparseVector)obj;
         int n = v.indices().length;
         byte[] indiceBytes = new byte[4 * n];
         ByteOrder order = ByteOrder.nativeOrder();
         ByteBuffer.wrap(indiceBytes).order(order).asIntBuffer().put(v.indices());
         byte[] valueBytes = new byte[8 * n];
         ByteBuffer.wrap(valueBytes).order(order).asDoubleBuffer().put(v.values());
         out.write(74);
         out.write(PickleUtils.integer_to_bytes(v.size()));
         out.write(84);
         out.write(PickleUtils.integer_to_bytes(indiceBytes.length));
         out.write(indiceBytes);
         out.write(84);
         out.write(PickleUtils.integer_to_bytes(valueBytes.length));
         out.write(valueBytes);
         out.write(135);
      }

      public Object construct(final Object[] args) {
         if (args.length != 3) {
            throw new PickleException("should be 3");
         } else {
            int size = BoxesRunTime.unboxToInt(args[0]);
            byte[] indiceBytes = this.getBytes(args[1]);
            byte[] valueBytes = this.getBytes(args[2]);
            int n = indiceBytes.length / 4;
            int[] indices = new int[n];
            double[] values = new double[n];
            if (n > 0) {
               ByteOrder order = ByteOrder.nativeOrder();
               ByteBuffer.wrap(indiceBytes).order(order).asIntBuffer().get(indices);
               ByteBuffer.wrap(valueBytes).order(order).asDoubleBuffer().get(values);
            } else {
               BoxedUnit var10000 = BoxedUnit.UNIT;
            }

            return new SparseVector(size, indices, values);
         }
      }

      public SparseVectorPickler() {
         super(scala.reflect.ClassTag..MODULE$.apply(SparseVector.class));
      }
   }

   public static class LabeledPointPickler extends SerDeBase.BasePickler {
      public void saveState(final Object obj, final OutputStream out, final Pickler pickler) {
         LabeledPoint point = (LabeledPoint)obj;
         this.saveObjects(out, pickler, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToDouble(point.label()), point.features()}));
      }

      public Object construct(final Object[] args) {
         if (args.length != 2) {
            throw new PickleException("should be 2");
         } else {
            return new LabeledPoint(BoxesRunTime.unboxToDouble(args[0]), (Vector)args[1]);
         }
      }

      public LabeledPointPickler() {
         super(scala.reflect.ClassTag..MODULE$.apply(LabeledPoint.class));
      }
   }

   public static class RatingPickler extends SerDeBase.BasePickler {
      public void saveState(final Object obj, final OutputStream out, final Pickler pickler) {
         Rating rating = (Rating)obj;
         this.saveObjects(out, pickler, scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(rating.user()), BoxesRunTime.boxToInteger(rating.product()), BoxesRunTime.boxToDouble(rating.rating())}));
      }

      public Object construct(final Object[] args) {
         if (args.length != 3) {
            throw new PickleException("should be 3");
         } else {
            return new Rating(this.ratingsIdCheckLong(args[0]), this.ratingsIdCheckLong(args[1]), BoxesRunTime.unboxToDouble(args[2]));
         }
      }

      private int ratingsIdCheckLong(final Object obj) {
         try {
            return BoxesRunTime.unboxToInt(obj);
         } catch (ClassCastException var3) {
            throw new PickleException("Ratings id " + obj.toString() + " exceeds max integer value of " + Integer.MAX_VALUE, var3);
         }
      }

      public RatingPickler() {
         super(scala.reflect.ClassTag..MODULE$.apply(Rating.class));
      }
   }
}
