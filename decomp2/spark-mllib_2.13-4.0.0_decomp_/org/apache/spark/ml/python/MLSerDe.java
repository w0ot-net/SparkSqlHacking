package org.apache.spark.ml.python;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import net.razorvine.pickle.PickleException;
import net.razorvine.pickle.PickleUtils;
import net.razorvine.pickle.Pickler;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.linalg.DenseMatrix;
import org.apache.spark.ml.linalg.DenseVector;
import org.apache.spark.ml.linalg.SparseMatrix;
import org.apache.spark.ml.linalg.SparseVector;
import org.apache.spark.ml.linalg.Vectors.;
import org.apache.spark.mllib.api.python.SerDeBase;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005UtA\u0002\u000e\u001c\u0011\u0003yRE\u0002\u0004(7!\u0005q\u0004\u000b\u0005\u0006\u0003\u0006!\tA\u0011\u0005\b\u0007\u0006\u0011\r\u0011\"\u0011E\u0011\u0019i\u0015\u0001)A\u0005\u000b\u001a)a*\u0001\u0001\u001c\u001f\")\u0011)\u0002C\u00015\")A,\u0002C\u0001;\")10\u0002C\u0001y\u001a9\u0011QA\u0001\u00017\u0005\u001d\u0001BB!\n\t\u0003\t\t\u0002\u0003\u0004]\u0013\u0011\u0005\u0011Q\u0003\u0005\u0007w&!\t!!\b\u0007\u000f\u0005\u0005\u0012\u0001A\u000e\u0002$!1\u0011)\u0004C\u0001\u0003[Aa\u0001X\u0007\u0005\u0002\u0005E\u0002BB>\u000e\t\u0003\tIDB\u0004\u0002>\u0005\u00011$a\u0010\t\r\u0005\u000bB\u0011AA%\u0011\u0019a\u0016\u0003\"\u0001\u0002N!110\u0005C\u0001\u0003+B\u0011\"!\u0017\u0002\u0001\u0004%\t!a\u0017\t\u0013\u0005\r\u0014\u00011A\u0005\u0002\u0005\u0015\u0004\u0002CA6\u0003\u0001\u0006K!!\u0018\t\u000f\u00055\u0014\u0001\"\u0011\u0002p!I\u0011\u0011O\u0001\u0002\u0002\u0013%\u00111O\u0001\b\u001b2\u001bVM\u001d#f\u0015\taR$\u0001\u0004qsRDwN\u001c\u0006\u0003=}\t!!\u001c7\u000b\u0005\u0001\n\u0013!B:qCJ\\'B\u0001\u0012$\u0003\u0019\t\u0007/Y2iK*\tA%A\u0002pe\u001e\u0004\"AJ\u0001\u000e\u0003m\u0011q!\u0014'TKJ$UmE\u0002\u0002SI\u0002\"A\u000b\u0019\u000e\u0003-R!\u0001\b\u0017\u000b\u00055r\u0013aA1qS*\u0011qfH\u0001\u0006[2d\u0017NY\u0005\u0003c-\u0012\u0011bU3s\t\u0016\u0014\u0015m]3\u0011\u0005MrdB\u0001\u001b<\u001d\t)\u0014(D\u00017\u0015\t9\u0004(\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005Q\u0014!B:dC2\f\u0017B\u0001\u001f>\u0003\u001d\u0001\u0018mY6bO\u0016T\u0011AO\u0005\u0003\u007f\u0001\u0013AbU3sS\u0006d\u0017N_1cY\u0016T!\u0001P\u001f\u0002\rqJg.\u001b;?)\u0005)\u0013a\u0004)Z'B\u000b%kS0Q\u0003\u000e[\u0015iR#\u0016\u0003\u0015\u0003\"AR&\u000e\u0003\u001dS!\u0001S%\u0002\t1\fgn\u001a\u0006\u0002\u0015\u0006!!.\u0019<b\u0013\tauI\u0001\u0004TiJLgnZ\u0001\u0011!f\u001b\u0006+\u0011*L?B\u000b5iS!H\u000b\u0002\u0012!\u0003R3og\u00164Vm\u0019;peBK7m\u001b7feN\u0011Q\u0001\u0015\t\u0004#J#V\"A\u0001\n\u0005M\u0003$a\u0003\"bg\u0016\u0004\u0016nY6mKJ\u0004\"!\u0016-\u000e\u0003YS!aV\u000f\u0002\r1Lg.\u00197h\u0013\tIfKA\u0006EK:\u001cXMV3di>\u0014H#A.\u0011\u0005E+\u0011!C:bm\u0016\u001cF/\u0019;f)\u0011q&mZ8\u0011\u0005}\u0003W\"A\u001f\n\u0005\u0005l$\u0001B+oSRDQaY\u0004A\u0002\u0011\f1a\u001c2k!\t1U-\u0003\u0002g\u000f\n1qJ\u00196fGRDQ\u0001[\u0004A\u0002%\f1a\\;u!\tQW.D\u0001l\u0015\ta\u0017*\u0001\u0002j_&\u0011an\u001b\u0002\r\u001fV$\b/\u001e;TiJ,\u0017-\u001c\u0005\u0006a\u001e\u0001\r!]\u0001\ba&\u001c7\u000e\\3s!\t\u0011\u00180D\u0001t\u0015\t!X/\u0001\u0004qS\u000e\\G.\u001a\u0006\u0003m^\f\u0011B]1{_J4\u0018N\\3\u000b\u0003a\f1A\\3u\u0013\tQ8OA\u0004QS\u000e\\G.\u001a:\u0002\u0013\r|gn\u001d;sk\u000e$HC\u00013~\u0011\u0015q\b\u00021\u0001\u0000\u0003\u0011\t'oZ:\u0011\t}\u000b\t\u0001Z\u0005\u0004\u0003\u0007i$!B!se\u0006L(A\u0005#f]N,W*\u0019;sSb\u0004\u0016nY6mKJ\u001c2!CA\u0005!\u0011\t&+a\u0003\u0011\u0007U\u000bi!C\u0002\u0002\u0010Y\u00131\u0002R3og\u0016l\u0015\r\u001e:jqR\u0011\u00111\u0003\t\u0003#&!rAXA\f\u00033\tY\u0002C\u0003d\u0017\u0001\u0007A\rC\u0003i\u0017\u0001\u0007\u0011\u000eC\u0003q\u0017\u0001\u0007\u0011\u000fF\u0002e\u0003?AQA \u0007A\u0002}\u00141c\u00159beN,W*\u0019;sSb\u0004\u0016nY6mKJ\u001c2!DA\u0013!\u0011\t&+a\n\u0011\u0007U\u000bI#C\u0002\u0002,Y\u0013Ab\u00159beN,W*\u0019;sSb$\"!a\f\u0011\u0005EkAc\u00020\u00024\u0005U\u0012q\u0007\u0005\u0006G>\u0001\r\u0001\u001a\u0005\u0006Q>\u0001\r!\u001b\u0005\u0006a>\u0001\r!\u001d\u000b\u0004I\u0006m\u0002\"\u0002@\u0011\u0001\u0004y(aE*qCJ\u001cXMV3di>\u0014\b+[2lY\u0016\u00148cA\t\u0002BA!\u0011KUA\"!\r)\u0016QI\u0005\u0004\u0003\u000f2&\u0001D*qCJ\u001cXMV3di>\u0014HCAA&!\t\t\u0016\u0003F\u0004_\u0003\u001f\n\t&a\u0015\t\u000b\r\u001c\u0002\u0019\u00013\t\u000b!\u001c\u0002\u0019A5\t\u000bA\u001c\u0002\u0019A9\u0015\u0007\u0011\f9\u0006C\u0003\u007f)\u0001\u0007q0A\u0006j]&$\u0018.\u00197ju\u0016$WCAA/!\ry\u0016qL\u0005\u0004\u0003Cj$a\u0002\"p_2,\u0017M\\\u0001\u0010S:LG/[1mSj,Gm\u0018\u0013fcR\u0019a,a\u001a\t\u0013\u0005%d#!AA\u0002\u0005u\u0013a\u0001=%c\u0005a\u0011N\\5uS\u0006d\u0017N_3eA\u0005Q\u0011N\\5uS\u0006d\u0017N_3\u0015\u0003y\u000bAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012\u0001\u001a"
)
public final class MLSerDe {
   public static void initialize() {
      MLSerDe$.MODULE$.initialize();
   }

   public static void initialized_$eq(final boolean x$1) {
      MLSerDe$.MODULE$.initialized_$eq(x$1);
   }

   public static boolean initialized() {
      return MLSerDe$.MODULE$.initialized();
   }

   public static String PYSPARK_PACKAGE() {
      return MLSerDe$.MODULE$.PYSPARK_PACKAGE();
   }

   public static JavaRDD pythonToJava(final JavaRDD pyRDD, final boolean batched) {
      return MLSerDe$.MODULE$.pythonToJava(pyRDD, batched);
   }

   public static JavaRDD javaToPython(final JavaRDD jRDD) {
      return MLSerDe$.MODULE$.javaToPython(jRDD);
   }

   public static RDD fromTuple2RDD(final RDD rdd) {
      return MLSerDe$.MODULE$.fromTuple2RDD(rdd);
   }

   public static RDD asTupleRDD(final RDD rdd) {
      return MLSerDe$.MODULE$.asTupleRDD(rdd);
   }

   public static Object loads(final byte[] bytes) {
      return MLSerDe$.MODULE$.loads(bytes);
   }

   public static byte[] dumps(final Object obj) {
      return MLSerDe$.MODULE$.dumps(obj);
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
         if (args.length != 1) {
            throw new PickleException("length of args should be 1");
         } else {
            byte[] bytes = this.getBytes(args[0]);
            ByteBuffer bb = ByteBuffer.wrap(bytes, 0, bytes.length);
            bb.order(ByteOrder.nativeOrder());
            DoubleBuffer db = bb.asDoubleBuffer();
            double[] ans = new double[bytes.length / 8];
            db.get(ans);
            return .MODULE$.dense(ans);
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
            throw new PickleException("length of args should be 4");
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
            throw new PickleException("length of args should be 6");
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
            throw new PickleException("length of args should be 3");
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
}
