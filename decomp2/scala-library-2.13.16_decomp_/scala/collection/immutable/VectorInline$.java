package scala.collection.immutable;

import [Ljava.lang.Object;;
import java.util.Arrays;

public final class VectorInline$ {
   public static final VectorInline$ MODULE$ = new VectorInline$();

   public final int BITS() {
      return 5;
   }

   public final int WIDTH() {
      return 32;
   }

   public final int MASK() {
      return 31;
   }

   public final int BITS2() {
      return 10;
   }

   public final int WIDTH2() {
      return 1024;
   }

   public final int BITS3() {
      return 15;
   }

   public final int WIDTH3() {
      return 32768;
   }

   public final int BITS4() {
      return 20;
   }

   public final int WIDTH4() {
      return 1048576;
   }

   public final int BITS5() {
      return 25;
   }

   public final int WIDTH5() {
      return 33554432;
   }

   public final int LASTWIDTH() {
      return 64;
   }

   public final int Log2ConcatFaster() {
      return 5;
   }

   public final int AlignToFaster() {
      return 64;
   }

   public int vectorSliceDim(final int count, final int idx) {
      int c = count / 2;
      return c + 1 - Math.abs(idx - c);
   }

   public Object[] copyOrUse(final Object[] a, final int start, final int end) {
      return start == 0 && end == a.length ? a : Arrays.copyOfRange(a, start, end);
   }

   public final Object[] copyTail(final Object[] a) {
      return Arrays.copyOfRange(a, 1, a.length);
   }

   public final Object[] copyInit(final Object[] a) {
      return Arrays.copyOfRange(a, 0, a.length - 1);
   }

   public final Object[] copyIfDifferentSize(final Object[] a, final int len) {
      return a.length == len ? a : Arrays.copyOf(a, len);
   }

   public final Object[] wrap1(final Object x) {
      Object[] a = new Object[1];
      a[0] = x;
      return a;
   }

   public final Object[][] wrap2(final Object[] x) {
      Object[][] a = new Object[1][];
      a[0] = x;
      return a;
   }

   public final Object[][][] wrap3(final Object[][] x) {
      Object[][][] a = new Object[1][][];
      a[0] = x;
      return a;
   }

   public final Object[][][][] wrap4(final Object[][][] x) {
      Object[][][][] a = new Object[1][][][];
      a[0] = x;
      return a;
   }

   public final Object[][][][][] wrap5(final Object[][][][] x) {
      Object[][][][][] a = new Object[1][][][][];
      a[0] = x;
      return a;
   }

   public final Object[] copyUpdate(final Object[] a1, final int idx1, final Object elem) {
      Object[] a1c = ((Object;)a1).clone();
      a1c[idx1] = elem;
      return a1c;
   }

   public final Object[][] copyUpdate(final Object[][] a2, final int idx2, final int idx1, final Object elem) {
      Object[][] a2c = (([[Ljava.lang.Object;)a2).clone();
      Object[] copyUpdate_a1c = a2c[idx2].clone();
      copyUpdate_a1c[idx1] = elem;
      a2c[idx2] = copyUpdate_a1c;
      return a2c;
   }

   public final Object[][][] copyUpdate(final Object[][][] a3, final int idx3, final int idx2, final int idx1, final Object elem) {
      Object[][][] a3c = (([[[Ljava.lang.Object;)a3).clone();
      Object[][] copyUpdate_a2c = a3c[idx3].clone();
      Object[] copyUpdate_copyUpdate_a1c = copyUpdate_a2c[idx2].clone();
      copyUpdate_copyUpdate_a1c[idx1] = elem;
      copyUpdate_a2c[idx2] = copyUpdate_copyUpdate_a1c;
      a3c[idx3] = copyUpdate_a2c;
      return a3c;
   }

   public final Object[][][][] copyUpdate(final Object[][][][] a4, final int idx4, final int idx3, final int idx2, final int idx1, final Object elem) {
      Object[][][][] a4c = (([[[[Ljava.lang.Object;)a4).clone();
      Object[][][] copyUpdate_a3c = a4c[idx4].clone();
      Object[][] copyUpdate_copyUpdate_a2c = copyUpdate_a3c[idx3].clone();
      Object[] copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_a2c[idx2].clone();
      copyUpdate_copyUpdate_copyUpdate_a1c[idx1] = elem;
      copyUpdate_copyUpdate_a2c[idx2] = copyUpdate_copyUpdate_copyUpdate_a1c;
      copyUpdate_a3c[idx3] = copyUpdate_copyUpdate_a2c;
      a4c[idx4] = copyUpdate_a3c;
      return a4c;
   }

   public final Object[][][][][] copyUpdate(final Object[][][][][] a5, final int idx5, final int idx4, final int idx3, final int idx2, final int idx1, final Object elem) {
      Object[][][][][] a5c = (([[[[[Ljava.lang.Object;)a5).clone();
      Object[][][][] copyUpdate_a4c = a5c[idx5].clone();
      Object[][][] copyUpdate_copyUpdate_a3c = copyUpdate_a4c[idx4].clone();
      Object[][] copyUpdate_copyUpdate_copyUpdate_a2c = copyUpdate_copyUpdate_a3c[idx3].clone();
      Object[] copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_copyUpdate_a2c[idx2].clone();
      copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c[idx1] = elem;
      copyUpdate_copyUpdate_copyUpdate_a2c[idx2] = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c;
      copyUpdate_copyUpdate_a3c[idx3] = copyUpdate_copyUpdate_copyUpdate_a2c;
      copyUpdate_a4c[idx4] = copyUpdate_copyUpdate_a3c;
      a5c[idx5] = copyUpdate_a4c;
      return a5c;
   }

   public final Object[][][][][][] copyUpdate(final Object[][][][][][] a6, final int idx6, final int idx5, final int idx4, final int idx3, final int idx2, final int idx1, final Object elem) {
      Object[][][][][][] a6c = (([[[[[[Ljava.lang.Object;)a6).clone();
      Object[][][][][] copyUpdate_a5c = a6c[idx6].clone();
      Object[][][][] copyUpdate_copyUpdate_a4c = copyUpdate_a5c[idx5].clone();
      Object[][][] copyUpdate_copyUpdate_copyUpdate_a3c = copyUpdate_copyUpdate_a4c[idx4].clone();
      Object[][] copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c = copyUpdate_copyUpdate_copyUpdate_a3c[idx3].clone();
      Object[] copyUpdate_copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c[idx2].clone();
      copyUpdate_copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c[idx1] = elem;
      copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c[idx2] = copyUpdate_copyUpdate_copyUpdate_copyUpdate_copyUpdate_a1c;
      copyUpdate_copyUpdate_copyUpdate_a3c[idx3] = copyUpdate_copyUpdate_copyUpdate_copyUpdate_a2c;
      copyUpdate_copyUpdate_a4c[idx4] = copyUpdate_copyUpdate_copyUpdate_a3c;
      copyUpdate_a5c[idx5] = copyUpdate_copyUpdate_a4c;
      a6c[idx6] = copyUpdate_a5c;
      return a6c;
   }

   public final Object[] concatArrays(final Object[] a, final Object[] b) {
      Object[] dest = Arrays.copyOf(a, a.length + b.length);
      System.arraycopy(b, 0, dest, a.length, b.length);
      return dest;
   }

   private VectorInline$() {
   }
}
