package org.apache.spark.mllib.optimization;

import java.util.Arrays;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u;aAF\f\t\u0002m\tcAB\u0012\u0018\u0011\u0003YB\u0005C\u0003,\u0003\u0011\u0005QF\u0002\u0003/\u0003\u0001y\u0003\u0002\u0003\u0019\u0004\u0005\u000b\u0007I\u0011A\u0019\t\u0011U\u001a!\u0011!Q\u0001\nIBQaK\u0002\u0005\u0002YBqAO\u0002C\u0002\u0013\u00051\b\u0003\u0004C\u0007\u0001\u0006I\u0001\u0010\u0005\b\u0007\u000e\u0011\r\u0011\"\u0001<\u0011\u0019!5\u0001)A\u0005y!9Qi\u0001b\u0001\n\u0003Y\u0004B\u0002$\u0004A\u0003%A\bC\u0004H\u0007\t\u0007I\u0011A\u001e\t\r!\u001b\u0001\u0015!\u0003=\u0011\u001dI5A1A\u0005\u0002mBaAS\u0002!\u0002\u0013a\u0004bB&\u0004\u0005\u0004%\ta\u000f\u0005\u0007\u0019\u000e\u0001\u000b\u0011\u0002\u001f\t\u000b5\u001bA\u0011\u0001(\t\u000bI\u000bA\u0011A*\t\u000bU\u000bA\u0011\u0001,\u0002\t9sEj\u0015\u0006\u00031e\tAb\u001c9uS6L'0\u0019;j_:T!AG\u000e\u0002\u000b5dG.\u001b2\u000b\u0005qi\u0012!B:qCJ\\'B\u0001\u0010 \u0003\u0019\t\u0007/Y2iK*\t\u0001%A\u0002pe\u001e\u0004\"AI\u0001\u000e\u0003]\u0011AA\u0014(M'N\u0011\u0011!\n\t\u0003M%j\u0011a\n\u0006\u0002Q\u0005)1oY1mC&\u0011!f\n\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012!\t\u0002\n/>\u00148n\u001d9bG\u0016\u001c\"aA\u0013\u0002\u00039,\u0012A\r\t\u0003MMJ!\u0001N\u0014\u0003\u0007%sG/\u0001\u0002oAQ\u0011q'\u000f\t\u0003q\ri\u0011!\u0001\u0005\u0006a\u0019\u0001\rAM\u0001\bg\u000e\u0014\u0018\r^2i+\u0005a\u0004c\u0001\u0014>\u007f%\u0011ah\n\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003M\u0001K!!Q\u0014\u0003\r\u0011{WO\u00197f\u0003!\u00198M]1uG\"\u0004\u0013\u0001B4sC\u0012\fQa\u001a:bI\u0002\n\u0011\u0001_\u0001\u0003q\u0002\n1\u0001Z5s\u0003\u0011!\u0017N\u001d\u0011\u0002\u000f1\f7\u000f\u001e#je\u0006AA.Y:u\t&\u0014\b%A\u0002sKN\fAA]3tA\u0005!q/\u001b9f)\u0005y\u0005C\u0001\u0014Q\u0013\t\tvE\u0001\u0003V]&$\u0018aD2sK\u0006$XmV8sWN\u0004\u0018mY3\u0015\u0005]\"\u0006\"\u0002\u0019\u0015\u0001\u0004\u0011\u0014!B:pYZ,G\u0003\u0002\u001fX3nCQ\u0001W\u000bA\u0002q\n1!\u0019;b\u0011\u0015QV\u00031\u0001=\u0003\r\tGO\u0019\u0005\u00069V\u0001\raN\u0001\u0003oN\u0004"
)
public final class NNLS {
   public static double[] solve(final double[] ata, final double[] atb, final Workspace ws) {
      return NNLS$.MODULE$.solve(ata, atb, ws);
   }

   public static Workspace createWorkspace(final int n) {
      return NNLS$.MODULE$.createWorkspace(n);
   }

   public static class Workspace {
      private final int n;
      private final double[] scratch;
      private final double[] grad;
      private final double[] x;
      private final double[] dir;
      private final double[] lastDir;
      private final double[] res;

      public int n() {
         return this.n;
      }

      public double[] scratch() {
         return this.scratch;
      }

      public double[] grad() {
         return this.grad;
      }

      public double[] x() {
         return this.x;
      }

      public double[] dir() {
         return this.dir;
      }

      public double[] lastDir() {
         return this.lastDir;
      }

      public double[] res() {
         return this.res;
      }

      public void wipe() {
         Arrays.fill(this.scratch(), (double)0.0F);
         Arrays.fill(this.grad(), (double)0.0F);
         Arrays.fill(this.x(), (double)0.0F);
         Arrays.fill(this.dir(), (double)0.0F);
         Arrays.fill(this.lastDir(), (double)0.0F);
         Arrays.fill(this.res(), (double)0.0F);
      }

      public Workspace(final int n) {
         this.n = n;
         this.scratch = new double[n];
         this.grad = new double[n];
         this.x = new double[n];
         this.dir = new double[n];
         this.lastDir = new double[n];
         this.res = new double[n];
      }
   }
}
