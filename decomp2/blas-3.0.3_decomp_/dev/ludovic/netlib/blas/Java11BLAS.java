package dev.ludovic.netlib.blas;

class Java11BLAS extends Java8BLAS {
   private static final Java11BLAS instance = new Java11BLAS();

   protected Java11BLAS() {
   }

   public static JavaBLAS getInstance() {
      return instance;
   }

   protected void daxpyK(int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      if (incx == 1 && incy == 1) {
         int ix = 0;

         for(int iy = 0; ix < n && iy < n; ++iy) {
            y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
            ++ix;
         }
      } else {
         int ix = incx < 0 ? (n - 1) * -incx : 0;
         int iy = incy < 0 ? (n - 1) * -incy : 0;

         while(true) {
            if (incx < 0) {
               if (ix < 0) {
                  break;
               }
            } else if (ix >= n * incx) {
               break;
            }

            if (incy < 0) {
               if (iy < 0) {
                  break;
               }
            } else if (iy >= n * incy) {
               break;
            }

            y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
            ix += incx;
            iy += incy;
         }
      }

   }

   protected void saxpyK(int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      if (incx == 1 && incy == 1) {
         int ix = 0;

         for(int iy = 0; ix < n && iy < n; ++iy) {
            y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
            ++ix;
         }
      } else {
         int ix = incx < 0 ? (n - 1) * -incx : 0;
         int iy = incy < 0 ? (n - 1) * -incy : 0;

         while(true) {
            if (incx < 0) {
               if (ix < 0) {
                  break;
               }
            } else if (ix >= n * incx) {
               break;
            }

            if (incy < 0) {
               if (iy < 0) {
                  break;
               }
            } else if (iy >= n * incy) {
               break;
            }

            y[offsety + iy] = Math.fma(alpha, x[offsetx + ix], y[offsety + iy]);
            ix += incx;
            iy += incy;
         }
      }

   }

   protected double ddotK(int n, double[] x, int offsetx, int incx, double[] y, int offsety, int incy) {
      double sum = (double)0.0F;
      if (incx == 1 && incy == 1) {
         int ix = 0;
         int iy = 0;
         double sum0 = (double)0.0F;
         double sum1 = (double)0.0F;
         double sum2 = (double)0.0F;

         double sum3;
         for(sum3 = (double)0.0F; ix < this.loopBound(n, 4) && iy < this.loopBound(n, 4); iy += 4) {
            sum0 = Math.fma(x[offsetx + ix + 0], y[offsety + iy + 0], sum0);
            sum1 = Math.fma(x[offsetx + ix + 1], y[offsety + iy + 1], sum1);
            sum2 = Math.fma(x[offsetx + ix + 2], y[offsety + iy + 2], sum2);
            sum3 = Math.fma(x[offsetx + ix + 3], y[offsety + iy + 3], sum3);
            ix += 4;
         }

         for(sum += sum0 + sum1 + sum2 + sum3; ix < n && iy < n; ++iy) {
            sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
            ++ix;
         }
      } else {
         int ix = incx < 0 ? (n - 1) * -incx : 0;
         int iy = incy < 0 ? (n - 1) * -incy : 0;

         while(true) {
            if (incx < 0) {
               if (ix < 0) {
                  break;
               }
            } else if (ix >= n * incx) {
               break;
            }

            if (incy < 0) {
               if (iy < 0) {
                  break;
               }
            } else if (iy >= n * incy) {
               break;
            }

            sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
            ix += incx;
            iy += incy;
         }
      }

      return sum;
   }

   protected float sdotK(int n, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      float sum = 0.0F;
      if (incx == 1 && incy == 1) {
         int ix = 0;
         int iy = 0;
         float sum0 = 0.0F;
         float sum1 = 0.0F;
         float sum2 = 0.0F;

         float sum3;
         for(sum3 = 0.0F; ix < this.loopBound(n, 4) && iy < this.loopBound(n, 4); iy += 4) {
            sum0 = Math.fma(x[offsetx + ix + 0], y[offsety + iy + 0], sum0);
            sum1 = Math.fma(x[offsetx + ix + 1], y[offsety + iy + 1], sum1);
            sum2 = Math.fma(x[offsetx + ix + 2], y[offsety + iy + 2], sum2);
            sum3 = Math.fma(x[offsetx + ix + 3], y[offsety + iy + 3], sum3);
            ix += 4;
         }

         for(sum += sum0 + sum1 + sum2 + sum3; ix < n && iy < n; ++iy) {
            sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
            ++ix;
         }
      } else {
         int ix = incx < 0 ? (n - 1) * -incx : 0;
         int iy = incy < 0 ? (n - 1) * -incy : 0;

         while(true) {
            if (incx < 0) {
               if (ix < 0) {
                  break;
               }
            } else if (ix >= n * incx) {
               break;
            }

            if (incy < 0) {
               if (iy < 0) {
                  break;
               }
            } else if (iy >= n * incy) {
               break;
            }

            sum = Math.fma(x[offsetx + ix], y[offsety + iy], sum);
            ix += incx;
            iy += incy;
         }
      }

      return sum;
   }

   protected float sdsdotK(int n, float sb, float[] x, int offsetx, int incx, float[] y, int offsety, int incy) {
      double sum = (double)sb;
      if (incx == 1 && incy == 1) {
         int ix = 0;
         int iy = 0;
         double sum0 = (double)0.0F;
         double sum1 = (double)0.0F;
         double sum2 = (double)0.0F;

         double sum3;
         for(sum3 = (double)0.0F; ix < this.loopBound(n, 4) && iy < this.loopBound(n, 4); iy += 4) {
            sum0 = Math.fma((double)x[offsetx + ix + 0], (double)y[offsety + iy + 0], sum0);
            sum1 = Math.fma((double)x[offsetx + ix + 1], (double)y[offsety + iy + 1], sum1);
            sum2 = Math.fma((double)x[offsetx + ix + 2], (double)y[offsety + iy + 2], sum2);
            sum3 = Math.fma((double)x[offsetx + ix + 3], (double)y[offsety + iy + 3], sum3);
            ix += 4;
         }

         for(sum += sum0 + sum1 + sum2 + sum3; ix < n && iy < n; ++iy) {
            sum = Math.fma((double)x[offsetx + ix], (double)y[offsety + iy], sum);
            ++ix;
         }
      } else {
         int ix = incx < 0 ? (n - 1) * -incx : 0;
         int iy = incy < 0 ? (n - 1) * -incy : 0;

         while(true) {
            if (incx < 0) {
               if (ix < 0) {
                  break;
               }
            } else if (ix >= n * incx) {
               break;
            }

            if (incy < 0) {
               if (iy < 0) {
                  break;
               }
            } else if (iy >= n * incy) {
               break;
            }

            sum = Math.fma((double)x[offsetx + ix], (double)y[offsety + iy], sum);
            ix += incx;
            iy += incy;
         }
      }

      return (float)sum;
   }

   protected void dgebpTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      int Tcol = 3;
      int Trow = 3;

      int col;
      for(col = cols; col < this.loopAlign(cols, cole, 3); ++col) {
         int row;
         for(row = rows; row < this.loopAlign(rows, rowe, 3); ++row) {
            double sum00 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
         }

         while(row < this.loopBound(rowe, 3)) {
            double sum00 = (double)0.0F;
            double sum10 = (double)0.0F;
            double sum20 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double a1 = a[offseta + i + (row + 1) * lda];
               double a2 = a[offseta + i + (row + 2) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum10 = Math.fma(a1, b0, sum10);
               sum20 = Math.fma(a2, b0, sum20);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + row + 1 + (col + 0) * ldc]);
            c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + row + 2 + (col + 0) * ldc]);
            row += 3;
         }

         while(row < rowe) {
            double sum00 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            ++row;
         }
      }

      while(col < this.loopBound(cole, 3)) {
         int row;
         for(row = rows; row < this.loopAlign(rows, rowe, 3); ++row) {
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;
            double sum02 = (double)0.0F;
            double sum03 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               double b1 = b[offsetb + i + (col + 1) * ldb];
               double b2 = b[offsetb + i + (col + 2) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum01 = Math.fma(a0, b1, sum01);
               sum02 = Math.fma(a0, b2, sum02);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + row + 0 + (col + 1) * ldc]);
            c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + row + 0 + (col + 2) * ldc]);
         }

         while(row < this.loopBound(rowe, 3)) {
            this.dgepdotTN(m, row, row + 3, n, col, col + 3, k, is, ie, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
            row += 3;
         }

         while(row < rowe) {
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;
            double sum02 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               double b1 = b[offsetb + i + (col + 1) * ldb];
               double b2 = b[offsetb + i + (col + 2) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum01 = Math.fma(a0, b1, sum01);
               sum02 = Math.fma(a0, b2, sum02);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + row + 0 + (col + 1) * ldc]);
            c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + row + 0 + (col + 2) * ldc]);
            ++row;
         }

         col += 3;
      }

      while(col < cole) {
         int row;
         for(row = rows; row < this.loopAlign(rows, rowe, 3); ++row) {
            double sum00 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
         }

         while(row < this.loopBound(rowe, 3)) {
            double sum00 = (double)0.0F;
            double sum10 = (double)0.0F;
            double sum20 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double a1 = a[offseta + i + (row + 1) * lda];
               double a2 = a[offseta + i + (row + 2) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum10 = Math.fma(a1, b0, sum10);
               sum20 = Math.fma(a2, b0, sum20);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + row + 1 + (col + 0) * ldc]);
            c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + row + 2 + (col + 0) * ldc]);
            row += 3;
         }

         while(row < rowe) {
            double sum00 = (double)0.0F;

            for(int i = is; i < ie; ++i) {
               double a0 = a[offseta + i + (row + 0) * lda];
               double b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            ++row;
         }

         ++col;
      }

   }

   protected void dgepdotTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      int Ti = 2;

      assert rowe - rows == 3;

      assert cole - cols == 3;

      int row = rows;
      int col = cols;
      int i = is;
      double sum00 = (double)0.0F;
      double sum01 = (double)0.0F;
      double sum02 = (double)0.0F;
      double sum10 = (double)0.0F;
      double sum11 = (double)0.0F;
      double sum12 = (double)0.0F;
      double sum20 = (double)0.0F;
      double sum21 = (double)0.0F;

      double sum22;
      for(sum22 = (double)0.0F; i < this.loopAlign(is, ie, 2); ++i) {
         double a0 = a[offseta + i + (row + 0) * lda];
         double a1 = a[offseta + i + (row + 1) * lda];
         double a2 = a[offseta + i + (row + 2) * lda];
         double b0 = b[offsetb + i + (col + 0) * ldb];
         sum00 = Math.fma(a0, b0, sum00);
         sum10 = Math.fma(a1, b0, sum10);
         sum20 = Math.fma(a2, b0, sum20);
         double b1 = b[offsetb + i + (col + 1) * ldb];
         sum01 = Math.fma(a0, b1, sum01);
         sum11 = Math.fma(a1, b1, sum11);
         sum21 = Math.fma(a2, b1, sum21);
         double b2 = b[offsetb + i + (col + 2) * ldb];
         sum02 = Math.fma(a0, b2, sum02);
         sum12 = Math.fma(a1, b2, sum12);
         sum22 = Math.fma(a2, b2, sum22);
      }

      while(i < this.loopBound(ie, 2)) {
         double a00 = a[offseta + i + 0 + (row + 0) * lda];
         double a01 = a[offseta + i + 0 + (row + 1) * lda];
         double a02 = a[offseta + i + 0 + (row + 2) * lda];
         double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
         sum00 = Math.fma(a00, b00, sum00);
         sum10 = Math.fma(a01, b00, sum10);
         sum20 = Math.fma(a02, b00, sum20);
         double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
         sum01 = Math.fma(a00, b01, sum01);
         sum11 = Math.fma(a01, b01, sum11);
         sum21 = Math.fma(a02, b01, sum21);
         double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
         sum02 = Math.fma(a00, b02, sum02);
         sum12 = Math.fma(a01, b02, sum12);
         sum22 = Math.fma(a02, b02, sum22);
         double a10 = a[offseta + i + 1 + (row + 0) * lda];
         double a11 = a[offseta + i + 1 + (row + 1) * lda];
         double a12 = a[offseta + i + 1 + (row + 2) * lda];
         double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
         sum00 = Math.fma(a10, b10, sum00);
         sum10 = Math.fma(a11, b10, sum10);
         sum20 = Math.fma(a12, b10, sum20);
         double b11 = b[offsetb + i + 1 + (col + 1) * ldb];
         sum01 = Math.fma(a10, b11, sum01);
         sum11 = Math.fma(a11, b11, sum11);
         sum21 = Math.fma(a12, b11, sum21);
         double b12 = b[offsetb + i + 1 + (col + 2) * ldb];
         sum02 = Math.fma(a10, b12, sum02);
         sum12 = Math.fma(a11, b12, sum12);
         sum22 = Math.fma(a12, b12, sum22);
         i += 2;
      }

      while(i < ie) {
         double a0 = a[offseta + i + (row + 0) * lda];
         double a1 = a[offseta + i + (row + 1) * lda];
         double a2 = a[offseta + i + (row + 2) * lda];
         double b0 = b[offsetb + i + (col + 0) * ldb];
         sum00 = Math.fma(a0, b0, sum00);
         sum10 = Math.fma(a1, b0, sum10);
         sum20 = Math.fma(a2, b0, sum20);
         double b1 = b[offsetb + i + (col + 1) * ldb];
         sum01 = Math.fma(a0, b1, sum01);
         sum11 = Math.fma(a1, b1, sum11);
         sum21 = Math.fma(a2, b1, sum21);
         double b2 = b[offsetb + i + (col + 2) * ldb];
         sum02 = Math.fma(a0, b2, sum02);
         sum12 = Math.fma(a1, b2, sum12);
         sum22 = Math.fma(a2, b2, sum22);
         ++i;
      }

      c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
      c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + row + 0 + (col + 1) * ldc]);
      c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + row + 0 + (col + 2) * ldc]);
      c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + row + 1 + (col + 0) * ldc]);
      c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, c[offsetc + row + 1 + (col + 1) * ldc]);
      c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, c[offsetc + row + 1 + (col + 2) * ldc]);
      c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + row + 2 + (col + 0) * ldc]);
      c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, c[offsetc + row + 2 + (col + 1) * ldc]);
      c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, c[offsetc + row + 2 + (col + 2) * ldc]);
   }

   protected void dgemmNN(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;
            double sum02 = (double)0.0F;
            double sum10 = (double)0.0F;
            double sum11 = (double)0.0F;
            double sum12 = (double)0.0F;
            double sum20 = (double)0.0F;
            double sum21 = (double)0.0F;

            double sum22;
            for(sum22 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double a11 = a[offseta + row + 1 + (i + 1) * lda];
               double a21 = a[offseta + row + 2 + (i + 1) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               double b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               double b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;

            double sum02;
            for(sum02 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               double b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               double b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum10 = (double)0.0F;

            double sum20;
            for(sum20 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double a11 = a[offseta + row + 1 + (i + 1) * lda];
               double a21 = a[offseta + row + 2 + (i + 1) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            double sum00;
            for(sum00 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void dgemmNT(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;
            double sum02 = (double)0.0F;
            double sum10 = (double)0.0F;
            double sum11 = (double)0.0F;
            double sum12 = (double)0.0F;
            double sum20 = (double)0.0F;
            double sum21 = (double)0.0F;

            double sum22;
            for(sum22 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double a11 = a[offseta + row + 1 + (i + 1) * lda];
               double a21 = a[offseta + row + 2 + (i + 1) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               double b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               double b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;

            double sum02;
            for(sum02 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               double b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               double b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum10 = (double)0.0F;

            double sum20;
            for(sum20 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double a11 = a[offseta + row + 1 + (i + 1) * lda];
               double a21 = a[offseta + row + 2 + (i + 1) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double a10 = a[offseta + row + 1 + (i + 0) * lda];
               double a20 = a[offseta + row + 2 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            double sum00;
            for(sum00 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               double a01 = a[offseta + row + 0 + (i + 1) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               double a00 = a[offseta + row + 0 + (i + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void dgemmTN(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;
            double sum02 = (double)0.0F;
            double sum10 = (double)0.0F;
            double sum11 = (double)0.0F;
            double sum12 = (double)0.0F;
            double sum20 = (double)0.0F;
            double sum21 = (double)0.0F;

            double sum22;
            for(sum22 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double a11 = a[offseta + i + 1 + (row + 1) * lda];
               double a21 = a[offseta + i + 1 + (row + 2) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               double b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               double b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;

            double sum02;
            for(sum02 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               double b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               double b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               double b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               double b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum10 = (double)0.0F;

            double sum20;
            for(sum20 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double a11 = a[offseta + i + 1 + (row + 1) * lda];
               double a21 = a[offseta + i + 1 + (row + 2) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            double sum00;
            for(sum00 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void dgemmTT(int m, int n, int k, double alpha, double[] a, int offseta, int lda, double[] b, int offsetb, int ldb, double beta, double[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;
            double sum02 = (double)0.0F;
            double sum10 = (double)0.0F;
            double sum11 = (double)0.0F;
            double sum12 = (double)0.0F;
            double sum20 = (double)0.0F;
            double sum21 = (double)0.0F;

            double sum22;
            for(sum22 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double a11 = a[offseta + i + 1 + (row + 1) * lda];
               double a21 = a[offseta + i + 1 + (row + 2) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               double b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               double b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum01 = (double)0.0F;

            double sum02;
            for(sum02 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               double b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               double b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               double b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               double b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            double sum00 = (double)0.0F;
            double sum10 = (double)0.0F;

            double sum20;
            for(sum20 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double a11 = a[offseta + i + 1 + (row + 1) * lda];
               double a21 = a[offseta + i + 1 + (row + 2) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double a10 = a[offseta + i + 0 + (row + 1) * lda];
               double a20 = a[offseta + i + 0 + (row + 2) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            double sum00;
            for(sum00 = (double)0.0F; i < this.loopBound(k, 2); i += 2) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               double a01 = a[offseta + i + 1 + (row + 0) * lda];
               double b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               double a00 = a[offseta + i + 0 + (row + 0) * lda];
               double b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != (double)0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void sgebpTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      int Tcol = 3;
      int Trow = 3;
      int Ti = 2;

      int col;
      for(col = cols; col < this.loopAlign(cols, cole, 3); ++col) {
         int row;
         for(row = rows; row < this.loopAlign(rows, rowe, 3); ++row) {
            float sum00 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
         }

         while(row < this.loopBound(rowe, 3)) {
            float sum00 = 0.0F;
            float sum10 = 0.0F;
            float sum20 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float a1 = a[offseta + i + (row + 1) * lda];
               float a2 = a[offseta + i + (row + 2) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum10 = Math.fma(a1, b0, sum10);
               sum20 = Math.fma(a2, b0, sum20);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + row + 1 + (col + 0) * ldc]);
            c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + row + 2 + (col + 0) * ldc]);
            row += 3;
         }

         while(row < rowe) {
            float sum00 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            ++row;
         }
      }

      while(col < this.loopBound(cole, 3)) {
         int row;
         for(row = rows; row < this.loopAlign(rows, rowe, 3); ++row) {
            float sum00 = 0.0F;
            float sum01 = 0.0F;
            float sum02 = 0.0F;
            float sum03 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               float b1 = b[offsetb + i + (col + 1) * ldb];
               float b2 = b[offsetb + i + (col + 2) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum01 = Math.fma(a0, b1, sum01);
               sum02 = Math.fma(a0, b2, sum02);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + row + 0 + (col + 1) * ldc]);
            c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + row + 0 + (col + 2) * ldc]);
         }

         while(row < this.loopBound(rowe, 3)) {
            this.sgepdotTN(m, row, row + 3, n, col, col + 3, k, is, ie, alpha, a, offseta, lda, b, offsetb, ldb, beta, c, offsetc, ldc);
            row += 3;
         }

         while(row < rowe) {
            float sum00 = 0.0F;
            float sum01 = 0.0F;
            float sum02 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               float b1 = b[offsetb + i + (col + 1) * ldb];
               float b2 = b[offsetb + i + (col + 2) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum01 = Math.fma(a0, b1, sum01);
               sum02 = Math.fma(a0, b2, sum02);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + row + 0 + (col + 1) * ldc]);
            c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + row + 0 + (col + 2) * ldc]);
            ++row;
         }

         col += 3;
      }

      while(col < cole) {
         int row;
         for(row = rows; row < this.loopAlign(rows, rowe, 3); ++row) {
            float sum00 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
         }

         while(row < this.loopBound(rowe, 3)) {
            float sum00 = 0.0F;
            float sum10 = 0.0F;
            float sum20 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float a1 = a[offseta + i + (row + 1) * lda];
               float a2 = a[offseta + i + (row + 2) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
               sum10 = Math.fma(a1, b0, sum10);
               sum20 = Math.fma(a2, b0, sum20);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + row + 1 + (col + 0) * ldc]);
            c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + row + 2 + (col + 0) * ldc]);
            row += 3;
         }

         while(row < rowe) {
            float sum00 = 0.0F;

            for(int i = is; i < ie; ++i) {
               float a0 = a[offseta + i + (row + 0) * lda];
               float b0 = b[offsetb + i + (col + 0) * ldb];
               sum00 = Math.fma(a0, b0, sum00);
            }

            c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
            ++row;
         }

         ++col;
      }

   }

   protected void sgepdotTN(int m, int rows, int rowe, int n, int cols, int cole, int k, int is, int ie, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      int Ti = 2;

      assert rowe - rows == 3;

      assert cole - cols == 3;

      int row = rows;
      int col = cols;
      int i = is;
      float sum00 = 0.0F;
      float sum01 = 0.0F;
      float sum02 = 0.0F;
      float sum10 = 0.0F;
      float sum11 = 0.0F;
      float sum12 = 0.0F;
      float sum20 = 0.0F;
      float sum21 = 0.0F;

      float sum22;
      for(sum22 = 0.0F; i < this.loopAlign(is, ie, 2); ++i) {
         float a0 = a[offseta + i + (row + 0) * lda];
         float a1 = a[offseta + i + (row + 1) * lda];
         float a2 = a[offseta + i + (row + 2) * lda];
         float b0 = b[offsetb + i + (col + 0) * ldb];
         sum00 = Math.fma(a0, b0, sum00);
         sum10 = Math.fma(a1, b0, sum10);
         sum20 = Math.fma(a2, b0, sum20);
         float b1 = b[offsetb + i + (col + 1) * ldb];
         sum01 = Math.fma(a0, b1, sum01);
         sum11 = Math.fma(a1, b1, sum11);
         sum21 = Math.fma(a2, b1, sum21);
         float b2 = b[offsetb + i + (col + 2) * ldb];
         sum02 = Math.fma(a0, b2, sum02);
         sum12 = Math.fma(a1, b2, sum12);
         sum22 = Math.fma(a2, b2, sum22);
      }

      while(i < this.loopBound(ie, 2)) {
         float a00 = a[offseta + i + 0 + (row + 0) * lda];
         float a01 = a[offseta + i + 0 + (row + 1) * lda];
         float a02 = a[offseta + i + 0 + (row + 2) * lda];
         float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
         sum00 = Math.fma(a00, b00, sum00);
         sum10 = Math.fma(a01, b00, sum10);
         sum20 = Math.fma(a02, b00, sum20);
         float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
         sum01 = Math.fma(a00, b01, sum01);
         sum11 = Math.fma(a01, b01, sum11);
         sum21 = Math.fma(a02, b01, sum21);
         float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
         sum02 = Math.fma(a00, b02, sum02);
         sum12 = Math.fma(a01, b02, sum12);
         sum22 = Math.fma(a02, b02, sum22);
         float a10 = a[offseta + i + 1 + (row + 0) * lda];
         float a11 = a[offseta + i + 1 + (row + 1) * lda];
         float a12 = a[offseta + i + 1 + (row + 2) * lda];
         float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
         sum00 = Math.fma(a10, b10, sum00);
         sum10 = Math.fma(a11, b10, sum10);
         sum20 = Math.fma(a12, b10, sum20);
         float b11 = b[offsetb + i + 1 + (col + 1) * ldb];
         sum01 = Math.fma(a10, b11, sum01);
         sum11 = Math.fma(a11, b11, sum11);
         sum21 = Math.fma(a12, b11, sum21);
         float b12 = b[offsetb + i + 1 + (col + 2) * ldb];
         sum02 = Math.fma(a10, b12, sum02);
         sum12 = Math.fma(a11, b12, sum12);
         sum22 = Math.fma(a12, b12, sum22);
         i += 2;
      }

      while(i < ie) {
         float a0 = a[offseta + i + (row + 0) * lda];
         float a1 = a[offseta + i + (row + 1) * lda];
         float a2 = a[offseta + i + (row + 2) * lda];
         float b0 = b[offsetb + i + (col + 0) * ldb];
         sum00 = Math.fma(a0, b0, sum00);
         sum10 = Math.fma(a1, b0, sum10);
         sum20 = Math.fma(a2, b0, sum20);
         float b1 = b[offsetb + i + (col + 1) * ldb];
         sum01 = Math.fma(a0, b1, sum01);
         sum11 = Math.fma(a1, b1, sum11);
         sum21 = Math.fma(a2, b1, sum21);
         float b2 = b[offsetb + i + (col + 2) * ldb];
         sum02 = Math.fma(a0, b2, sum02);
         sum12 = Math.fma(a1, b2, sum12);
         sum22 = Math.fma(a2, b2, sum22);
         ++i;
      }

      c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, c[offsetc + row + 0 + (col + 0) * ldc]);
      c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, c[offsetc + row + 0 + (col + 1) * ldc]);
      c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, c[offsetc + row + 0 + (col + 2) * ldc]);
      c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, c[offsetc + row + 1 + (col + 0) * ldc]);
      c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, c[offsetc + row + 1 + (col + 1) * ldc]);
      c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, c[offsetc + row + 1 + (col + 2) * ldc]);
      c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, c[offsetc + row + 2 + (col + 0) * ldc]);
      c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, c[offsetc + row + 2 + (col + 1) * ldc]);
      c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, c[offsetc + row + 2 + (col + 2) * ldc]);
   }

   protected void sgemmNN(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;
            float sum02 = 0.0F;
            float sum10 = 0.0F;
            float sum11 = 0.0F;
            float sum12 = 0.0F;
            float sum20 = 0.0F;
            float sum21 = 0.0F;

            float sum22;
            for(sum22 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float a11 = a[offseta + row + 1 + (i + 1) * lda];
               float a21 = a[offseta + row + 2 + (i + 1) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               float b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               float b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;

            float sum02;
            for(sum02 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               float b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               float b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum10 = 0.0F;

            float sum20;
            for(sum20 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float a11 = a[offseta + row + 1 + (i + 1) * lda];
               float a21 = a[offseta + row + 2 + (i + 1) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            float sum00;
            for(sum00 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void sgemmNT(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;
            float sum02 = 0.0F;
            float sum10 = 0.0F;
            float sum11 = 0.0F;
            float sum12 = 0.0F;
            float sum20 = 0.0F;
            float sum21 = 0.0F;

            float sum22;
            for(sum22 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float a11 = a[offseta + row + 1 + (i + 1) * lda];
               float a21 = a[offseta + row + 2 + (i + 1) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               float b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               float b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;

            float sum02;
            for(sum02 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               float b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               float b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum10 = 0.0F;

            float sum20;
            for(sum20 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float a11 = a[offseta + row + 1 + (i + 1) * lda];
               float a21 = a[offseta + row + 2 + (i + 1) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float a10 = a[offseta + row + 1 + (i + 0) * lda];
               float a20 = a[offseta + row + 2 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            float sum00;
            for(sum00 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               float a01 = a[offseta + row + 0 + (i + 1) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               float a00 = a[offseta + row + 0 + (i + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void sgemmTN(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;
            float sum02 = 0.0F;
            float sum10 = 0.0F;
            float sum11 = 0.0F;
            float sum12 = 0.0F;
            float sum20 = 0.0F;
            float sum21 = 0.0F;

            float sum22;
            for(sum22 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float a11 = a[offseta + i + 1 + (row + 1) * lda];
               float a21 = a[offseta + i + 1 + (row + 2) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               float b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               float b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;

            float sum02;
            for(sum02 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               float b11 = b[offsetb + i + 1 + (col + 1) * ldb];
               float b12 = b[offsetb + i + 1 + (col + 2) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               float b01 = b[offsetb + i + 0 + (col + 1) * ldb];
               float b02 = b[offsetb + i + 0 + (col + 2) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum10 = 0.0F;

            float sum20;
            for(sum20 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float a11 = a[offseta + i + 1 + (row + 1) * lda];
               float a21 = a[offseta + i + 1 + (row + 2) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            float sum00;
            for(sum00 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float b10 = b[offsetb + i + 1 + (col + 0) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + i + 0 + (col + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void sgemmTT(int m, int n, int k, float alpha, float[] a, int offseta, int lda, float[] b, int offsetb, int ldb, float beta, float[] c, int offsetc, int ldc) {
      int Trow = 3;
      int Tcol = 3;
      int Ti = 2;

      int col;
      for(col = 0; col < this.loopBound(n, 3); col += 3) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;
            float sum02 = 0.0F;
            float sum10 = 0.0F;
            float sum11 = 0.0F;
            float sum12 = 0.0F;
            float sum20 = 0.0F;
            float sum21 = 0.0F;

            float sum22;
            for(sum22 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float a11 = a[offseta + i + 1 + (row + 1) * lda];
               float a21 = a[offseta + i + 1 + (row + 2) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               float b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               float b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
               sum10 = Math.fma(a11, b10, sum10);
               sum11 = Math.fma(a11, b11, sum11);
               sum12 = Math.fma(a11, b12, sum12);
               sum20 = Math.fma(a21, b10, sum20);
               sum21 = Math.fma(a21, b11, sum21);
               sum22 = Math.fma(a21, b12, sum22);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               sum10 = Math.fma(a10, b00, sum10);
               sum11 = Math.fma(a10, b01, sum11);
               sum12 = Math.fma(a10, b02, sum12);
               sum20 = Math.fma(a20, b00, sum20);
               sum21 = Math.fma(a20, b01, sum21);
               sum22 = Math.fma(a20, b02, sum22);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 1) * ldc] = Math.fma(alpha, sum11, beta * c[offsetc + row + 1 + (col + 1) * ldc]);
               c[offsetc + row + 1 + (col + 2) * ldc] = Math.fma(alpha, sum12, beta * c[offsetc + row + 1 + (col + 2) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 1) * ldc] = Math.fma(alpha, sum21, beta * c[offsetc + row + 2 + (col + 1) * ldc]);
               c[offsetc + row + 2 + (col + 2) * ldc] = Math.fma(alpha, sum22, beta * c[offsetc + row + 2 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 1 + (col + 1) * ldc] = alpha * sum11;
               c[offsetc + row + 1 + (col + 2) * ldc] = alpha * sum12;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
               c[offsetc + row + 2 + (col + 1) * ldc] = alpha * sum21;
               c[offsetc + row + 2 + (col + 2) * ldc] = alpha * sum22;
            }
         }

         for(; row < m; ++row) {
            int i = 0;
            float sum00 = 0.0F;
            float sum01 = 0.0F;

            float sum02;
            for(sum02 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               float b11 = b[offsetb + col + 1 + (i + 1) * ldb];
               float b12 = b[offsetb + col + 2 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum01 = Math.fma(a01, b11, sum01);
               sum02 = Math.fma(a01, b12, sum02);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               float b01 = b[offsetb + col + 1 + (i + 0) * ldb];
               float b02 = b[offsetb + col + 2 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum01 = Math.fma(a00, b01, sum01);
               sum02 = Math.fma(a00, b02, sum02);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 0 + (col + 1) * ldc] = Math.fma(alpha, sum01, beta * c[offsetc + row + 0 + (col + 1) * ldc]);
               c[offsetc + row + 0 + (col + 2) * ldc] = Math.fma(alpha, sum02, beta * c[offsetc + row + 0 + (col + 2) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 0 + (col + 1) * ldc] = alpha * sum01;
               c[offsetc + row + 0 + (col + 2) * ldc] = alpha * sum02;
            }
         }
      }

      while(col < n) {
         int row;
         for(row = 0; row < this.loopBound(m, 3); row += 3) {
            int i = 0;
            float sum00 = 0.0F;
            float sum10 = 0.0F;

            float sum20;
            for(sum20 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float a11 = a[offseta + i + 1 + (row + 1) * lda];
               float a21 = a[offseta + i + 1 + (row + 2) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
               sum10 = Math.fma(a11, b10, sum10);
               sum20 = Math.fma(a21, b10, sum20);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float a10 = a[offseta + i + 0 + (row + 1) * lda];
               float a20 = a[offseta + i + 0 + (row + 2) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               sum10 = Math.fma(a10, b00, sum10);
               sum20 = Math.fma(a20, b00, sum20);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
               c[offsetc + row + 1 + (col + 0) * ldc] = Math.fma(alpha, sum10, beta * c[offsetc + row + 1 + (col + 0) * ldc]);
               c[offsetc + row + 2 + (col + 0) * ldc] = Math.fma(alpha, sum20, beta * c[offsetc + row + 2 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
               c[offsetc + row + 1 + (col + 0) * ldc] = alpha * sum10;
               c[offsetc + row + 2 + (col + 0) * ldc] = alpha * sum20;
            }
         }

         for(; row < m; ++row) {
            int i = 0;

            float sum00;
            for(sum00 = 0.0F; i < this.loopBound(k, 2); i += 2) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               float a01 = a[offseta + i + 1 + (row + 0) * lda];
               float b10 = b[offsetb + col + 0 + (i + 1) * ldb];
               sum00 = Math.fma(a01, b10, sum00);
            }

            while(i < k) {
               float a00 = a[offseta + i + 0 + (row + 0) * lda];
               float b00 = b[offsetb + col + 0 + (i + 0) * ldb];
               sum00 = Math.fma(a00, b00, sum00);
               ++i;
            }

            if (beta != 0.0F) {
               c[offsetc + row + 0 + (col + 0) * ldc] = Math.fma(alpha, sum00, beta * c[offsetc + row + 0 + (col + 0) * ldc]);
            } else {
               c[offsetc + row + 0 + (col + 0) * ldc] = alpha * sum00;
            }
         }

         ++col;
      }

   }

   protected void dgemvN(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      if (beta != (double)1.0F) {
         int row = 0;

         for(int iy = incy < 0 ? (m - 1) * -incy : 0; row < m; iy += incy) {
            if (beta != (double)0.0F) {
               y[offsety + iy] = beta * y[offsety + iy];
            } else {
               y[offsety + iy] = (double)0.0F;
            }

            ++row;
         }
      }

      int col = 0;

      int ix;
      for(ix = incx < 0 ? (n - 1) * -incx : 0; col < this.loopBound(n, 4); ix += incx * 4) {
         int row = 0;
         int iy = incy < 0 ? (m - 1) * -incy : 0;
         double alphax0 = alpha * x[offsetx + ix + incx * 0];
         double alphax1 = alpha * x[offsetx + ix + incx * 1];
         double alphax2 = alpha * x[offsetx + ix + incx * 2];

         for(double alphax3 = alpha * x[offsetx + ix + incx * 3]; row < m; iy += incy) {
            y[offsety + iy] = Math.fma(alphax0, a[offseta + row + (col + 0) * lda], Math.fma(alphax1, a[offseta + row + (col + 1) * lda], Math.fma(alphax2, a[offseta + row + (col + 2) * lda], Math.fma(alphax3, a[offseta + row + (col + 3) * lda], y[offsety + iy]))));
            ++row;
         }

         col += 4;
      }

      while(col < n) {
         int row = 0;
         int iy = incy < 0 ? (m - 1) * -incy : 0;

         for(double alphax = alpha * x[offsetx + ix]; row < m; iy += incy) {
            y[offsety + iy] = Math.fma(alphax, a[offseta + row + col * lda], y[offsety + iy]);
            ++row;
         }

         ++col;
         ix += incx;
      }

   }

   protected void dgemvT(int m, int n, double alpha, double[] a, int offseta, int lda, double[] x, int offsetx, int incx, double beta, double[] y, int offsety, int incy) {
      int col = 0;

      int iy;
      for(iy = incy < 0 ? (n - 1) * -incy : 0; col < this.loopBound(n, 4); iy += incy * 4) {
         int row = 0;
         int ix = incx < 0 ? (m - 1) * -incx : 0;
         double sum0 = (double)0.0F;
         double sum1 = (double)0.0F;
         double sum2 = (double)0.0F;

         double sum3;
         for(sum3 = (double)0.0F; row < m; ix += incx) {
            double xix = x[offsetx + ix];
            sum0 = Math.fma(xix, a[offseta + row + (col + 0) * lda], sum0);
            sum1 = Math.fma(xix, a[offseta + row + (col + 1) * lda], sum1);
            sum2 = Math.fma(xix, a[offseta + row + (col + 2) * lda], sum2);
            sum3 = Math.fma(xix, a[offseta + row + (col + 3) * lda], sum3);
            ++row;
         }

         if (beta != (double)0.0F) {
            y[offsety + iy + incy * 0] = alpha * sum0 + beta * y[offsety + iy + incy * 0];
            y[offsety + iy + incy * 1] = alpha * sum1 + beta * y[offsety + iy + incy * 1];
            y[offsety + iy + incy * 2] = alpha * sum2 + beta * y[offsety + iy + incy * 2];
            y[offsety + iy + incy * 3] = alpha * sum3 + beta * y[offsety + iy + incy * 3];
         } else {
            y[offsety + iy + incy * 0] = alpha * sum0;
            y[offsety + iy + incy * 1] = alpha * sum1;
            y[offsety + iy + incy * 2] = alpha * sum2;
            y[offsety + iy + incy * 3] = alpha * sum3;
         }

         col += 4;
      }

      while(col < n) {
         int row = 0;
         int ix = incx < 0 ? (m - 1) * -incx : 0;

         double sum;
         for(sum = (double)0.0F; row < m; ix += incx) {
            sum = Math.fma(x[offsetx + ix], a[offseta + row + col * lda], sum);
            ++row;
         }

         if (beta != (double)0.0F) {
            y[offsety + iy] = alpha * sum + beta * y[offsety + iy];
         } else {
            y[offsety + iy] = alpha * sum;
         }

         ++col;
         iy += incy;
      }

   }

   protected void sgemvN(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      int row = 0;

      for(int iy = incy < 0 ? (m - 1) * -incy : 0; row < m; iy += incy) {
         if (beta != 0.0F) {
            y[offsety + iy] = beta * y[offsety + iy];
         } else {
            y[offsety + iy] = 0.0F;
         }

         ++row;
      }

      row = 0;

      int ix;
      for(ix = incx < 0 ? (n - 1) * -incx : 0; row < this.loopBound(n, 8); ix += incx * 8) {
         float alphax0 = alpha * x[offsetx + ix + incx * 0];
         float alphax1 = alpha * x[offsetx + ix + incx * 1];
         float alphax2 = alpha * x[offsetx + ix + incx * 2];
         float alphax3 = alpha * x[offsetx + ix + incx * 3];
         float alphax4 = alpha * x[offsetx + ix + incx * 4];
         float alphax5 = alpha * x[offsetx + ix + incx * 5];
         float alphax6 = alpha * x[offsetx + ix + incx * 6];
         float alphax7 = alpha * x[offsetx + ix + incx * 7];
         int row = 0;

         for(int iy = incy < 0 ? (m - 1) * -incy : 0; row < m; iy += incy) {
            y[offsety + iy] = Math.fma(alphax0, a[offseta + row + (row + 0) * lda], Math.fma(alphax1, a[offseta + row + (row + 1) * lda], Math.fma(alphax2, a[offseta + row + (row + 2) * lda], Math.fma(alphax3, a[offseta + row + (row + 3) * lda], Math.fma(alphax4, a[offseta + row + (row + 4) * lda], Math.fma(alphax5, a[offseta + row + (row + 5) * lda], Math.fma(alphax6, a[offseta + row + (row + 6) * lda], Math.fma(alphax7, a[offseta + row + (row + 7) * lda], y[offsety + iy]))))))));
            ++row;
         }

         row += 8;
      }

      while(row < n) {
         float alphax = alpha * x[offsetx + ix];
         int row = 0;

         for(int iy = incy < 0 ? (m - 1) * -incy : 0; row < m; iy += incy) {
            y[offsety + iy] = Math.fma(alphax, a[offseta + row + row * lda], y[offsety + iy]);
            ++row;
         }

         ++row;
         ix += incx;
      }

   }

   protected void sgemvT(int m, int n, float alpha, float[] a, int offseta, int lda, float[] x, int offsetx, int incx, float beta, float[] y, int offsety, int incy) {
      int col = 0;

      int iy;
      for(iy = incy < 0 ? (n - 1) * -incy : 0; col < this.loopBound(n, 8); iy += incy * 8) {
         float sum0 = 0.0F;
         float sum1 = 0.0F;
         float sum2 = 0.0F;
         float sum3 = 0.0F;
         float sum4 = 0.0F;
         float sum5 = 0.0F;
         float sum6 = 0.0F;
         float sum7 = 0.0F;
         int row = 0;

         for(int ix = incx < 0 ? (m - 1) * -incx : 0; row < m; ix += incx) {
            sum0 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 0) * lda], sum0);
            sum1 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 1) * lda], sum1);
            sum2 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 2) * lda], sum2);
            sum3 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 3) * lda], sum3);
            sum4 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 4) * lda], sum4);
            sum5 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 5) * lda], sum5);
            sum6 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 6) * lda], sum6);
            sum7 = Math.fma(x[offsetx + ix], a[offseta + row + (col + 7) * lda], sum7);
            ++row;
         }

         if (beta != 0.0F) {
            y[offsety + iy + incy * 0] = alpha * sum0 + beta * y[offsety + iy + incy * 0];
            y[offsety + iy + incy * 1] = alpha * sum1 + beta * y[offsety + iy + incy * 1];
            y[offsety + iy + incy * 2] = alpha * sum2 + beta * y[offsety + iy + incy * 2];
            y[offsety + iy + incy * 3] = alpha * sum3 + beta * y[offsety + iy + incy * 3];
            y[offsety + iy + incy * 4] = alpha * sum4 + beta * y[offsety + iy + incy * 4];
            y[offsety + iy + incy * 5] = alpha * sum5 + beta * y[offsety + iy + incy * 5];
            y[offsety + iy + incy * 6] = alpha * sum6 + beta * y[offsety + iy + incy * 6];
            y[offsety + iy + incy * 7] = alpha * sum7 + beta * y[offsety + iy + incy * 7];
         } else {
            y[offsety + iy + incy * 0] = alpha * sum0;
            y[offsety + iy + incy * 1] = alpha * sum1;
            y[offsety + iy + incy * 2] = alpha * sum2;
            y[offsety + iy + incy * 3] = alpha * sum3;
            y[offsety + iy + incy * 4] = alpha * sum4;
            y[offsety + iy + incy * 5] = alpha * sum5;
            y[offsety + iy + incy * 6] = alpha * sum6;
            y[offsety + iy + incy * 7] = alpha * sum7;
         }

         col += 8;
      }

      while(col < n) {
         float sum = 0.0F;
         int row = 0;

         for(int ix = incx < 0 ? (m - 1) * -incx : 0; row < m; ix += incx) {
            sum = Math.fma(x[offsetx + ix], a[offseta + row + col * lda], sum);
            ++row;
         }

         if (beta != 0.0F) {
            y[offsety + iy] = alpha * sum + beta * y[offsety + iy];
         } else {
            y[offsety + iy] = alpha * sum;
         }

         ++col;
         iy += incy;
      }

   }

   protected void dgerK(int m, int n, double alpha, double[] x, int offsetx, int incx, double[] y, int offsety, int incy, double[] a, int offseta, int lda) {
      int col = 0;

      int iy;
      for(iy = incy < 0 ? (n - 1) * -incy : 0; col < this.loopBound(n, 4); iy += incy * 4) {
         double alphayiy0 = alpha * y[offsety + iy + incy * 0];
         double alphayiy1 = alpha * y[offsety + iy + incy * 1];
         double alphayiy2 = alpha * y[offsety + iy + incy * 2];
         double alphayiy3 = alpha * y[offsety + iy + incy * 3];
         int row = 0;

         for(int jx = incx < 0 ? (n - 1) * -incx : 0; row < m; jx += incx) {
            double xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * lda] = Math.fma(alphayiy0, xjx, a[offseta + row + (col + 0) * lda]);
            a[offseta + row + (col + 1) * lda] = Math.fma(alphayiy1, xjx, a[offseta + row + (col + 1) * lda]);
            a[offseta + row + (col + 2) * lda] = Math.fma(alphayiy2, xjx, a[offseta + row + (col + 2) * lda]);
            a[offseta + row + (col + 3) * lda] = Math.fma(alphayiy3, xjx, a[offseta + row + (col + 3) * lda]);
            ++row;
         }

         col += 4;
      }

      while(col < n) {
         double alphayiy = alpha * y[offsety + iy];
         int row = 0;

         for(int jx = incx < 0 ? (n - 1) * -incx : 0; row < m; jx += incx) {
            a[offseta + row + col * lda] = Math.fma(alphayiy, x[offsetx + jx], a[offseta + row + col * lda]);
            ++row;
         }

         ++col;
         iy += incy;
      }

   }

   protected void sgerK(int m, int n, float alpha, float[] x, int offsetx, int incx, float[] y, int offsety, int incy, float[] a, int offseta, int lda) {
      int col = 0;

      int iy;
      for(iy = incy < 0 ? (n - 1) * -incy : 0; col < this.loopBound(n, 4); iy += incy * 4) {
         float alphayiy0 = alpha * y[offsety + iy + incy * 0];
         float alphayiy1 = alpha * y[offsety + iy + incy * 1];
         float alphayiy2 = alpha * y[offsety + iy + incy * 2];
         float alphayiy3 = alpha * y[offsety + iy + incy * 3];
         int row = 0;

         for(int jx = incx < 0 ? (n - 1) * -incx : 0; row < m; jx += incx) {
            float xjx = x[offsetx + jx];
            a[offseta + row + (col + 0) * lda] = Math.fma(alphayiy0, xjx, a[offseta + row + (col + 0) * lda]);
            a[offseta + row + (col + 1) * lda] = Math.fma(alphayiy1, xjx, a[offseta + row + (col + 1) * lda]);
            a[offseta + row + (col + 2) * lda] = Math.fma(alphayiy2, xjx, a[offseta + row + (col + 2) * lda]);
            a[offseta + row + (col + 3) * lda] = Math.fma(alphayiy3, xjx, a[offseta + row + (col + 3) * lda]);
            ++row;
         }

         col += 4;
      }

      while(col < n) {
         float alphayiy = alpha * y[offsety + iy];
         int row = 0;

         for(int jx = incx < 0 ? (n - 1) * -incx : 0; row < m; jx += incx) {
            a[offseta + row + col * lda] = Math.fma(alphayiy, x[offsetx + jx], a[offseta + row + col * lda]);
            ++row;
         }

         ++col;
         iy += incy;
      }

   }

   protected double dnrm2K(int n, double[] x, int offsetx, int incx) {
      int ix = 0;
      double sum0 = (double)0.0F;
      double sum1 = (double)0.0F;
      double sum2 = (double)0.0F;
      double sum3 = (double)0.0F;
      if (incx == 1) {
         while(ix < this.loopBound(n, 4)) {
            double x0 = x[offsetx + ix + 0];
            double x1 = x[offsetx + ix + 1];
            double x2 = x[offsetx + ix + 2];
            double x3 = x[offsetx + ix + 3];
            sum0 = Math.fma(x0, x0, sum0);
            sum1 = Math.fma(x1, x1, sum1);
            sum2 = Math.fma(x2, x2, sum2);
            sum3 = Math.fma(x3, x3, sum3);
            ix += 4;
         }
      } else {
         while(ix < this.loopBound(n, 4) * incx) {
            double x0 = x[offsetx + ix + 0 * incx];
            double x1 = x[offsetx + ix + 1 * incx];
            double x2 = x[offsetx + ix + 2 * incx];
            double x3 = x[offsetx + ix + 3 * incx];
            sum0 = Math.fma(x0, x0, sum0);
            sum1 = Math.fma(x1, x1, sum1);
            sum2 = Math.fma(x2, x2, sum2);
            sum3 = Math.fma(x3, x3, sum3);
            ix += 4 * incx;
         }
      }

      double sum;
      for(sum = sum0 + sum1 + sum2 + sum3; ix < n * incx; ix += incx) {
         double x0 = x[offsetx + ix + 0];
         sum = Math.fma(x0, x0, sum);
      }

      return Math.sqrt(sum);
   }

   protected float snrm2K(int n, float[] x, int offsetx, int incx) {
      int ix = 0;
      float sum0 = 0.0F;
      float sum1 = 0.0F;
      float sum2 = 0.0F;
      float sum3 = 0.0F;
      if (incx == 1) {
         while(ix < this.loopBound(n, 4)) {
            float x0 = x[offsetx + ix + 0];
            float x1 = x[offsetx + ix + 1];
            float x2 = x[offsetx + ix + 2];
            float x3 = x[offsetx + ix + 3];
            sum0 = Math.fma(x0, x0, sum0);
            sum1 = Math.fma(x1, x1, sum1);
            sum2 = Math.fma(x2, x2, sum2);
            sum3 = Math.fma(x3, x3, sum3);
            ix += 4;
         }
      } else {
         while(ix < this.loopBound(n, 4) * incx) {
            float x0 = x[offsetx + ix + 0 * incx];
            float x1 = x[offsetx + ix + 1 * incx];
            float x2 = x[offsetx + ix + 2 * incx];
            float x3 = x[offsetx + ix + 3 * incx];
            sum0 = Math.fma(x0, x0, sum0);
            sum1 = Math.fma(x1, x1, sum1);
            sum2 = Math.fma(x2, x2, sum2);
            sum3 = Math.fma(x3, x3, sum3);
            ix += 4 * incx;
         }
      }

      float sum;
      for(sum = sum0 + sum1 + sum2 + sum3; ix < n * incx; ix += incx) {
         float x0 = x[offsetx + ix + 0];
         sum = Math.fma(x0, x0, sum);
      }

      return (float)Math.sqrt((double)sum);
   }
}
