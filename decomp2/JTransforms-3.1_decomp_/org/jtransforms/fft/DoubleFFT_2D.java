package org.jtransforms.fft;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class DoubleFFT_2D {
   private int rows;
   private int columns;
   private long rowsl;
   private long columnsl;
   private DoubleFFT_1D fftColumns;
   private DoubleFFT_1D fftRows;
   private boolean isPowerOfTwo = false;
   private boolean useThreads = false;

   public DoubleFFT_2D(long rows, long columns) {
      if (rows > 1L && columns > 1L) {
         this.rows = (int)rows;
         this.columns = (int)columns;
         this.rowsl = rows;
         this.columnsl = columns;
         if (rows * columns >= CommonUtils.getThreadsBeginN_2D()) {
            this.useThreads = true;
         }

         if (CommonUtils.isPowerOf2(rows) && CommonUtils.isPowerOf2(columns)) {
            this.isPowerOfTwo = true;
         }

         CommonUtils.setUseLargeArrays(2L * rows * columns > (long)LargeArray.getMaxSizeOf32bitArray());
         this.fftRows = new DoubleFFT_1D(rows);
         if (rows == columns) {
            this.fftColumns = this.fftRows;
         } else {
            this.fftColumns = new DoubleFFT_1D(columns);
         }

      } else {
         throw new IllegalArgumentException("rows and columns must be greater than 1");
      }
   }

   public void complexForward(final double[] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         this.columns = 2 * this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(0, -1, (double[])a, true);
            this.cdft2d_subth(-1, (double[])a, true);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexForward(a, r * this.columns);
            }

            this.cdft2d_sub(-1, (double[])a, true);
         }

         this.columns /= 2;
      } else {
         final int rowStride = 2 * this.columns;
         if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int r = firstRow; r < lastRow; ++r) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a, r * rowStride);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columns / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstColumn = l * p;
               final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     double[] temp = new double[2 * DoubleFFT_2D.this.rows];

                     for(int c = firstColumn; c < lastColumn; ++c) {
                        int idx0 = 2 * c;

                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx1 = 2 * r;
                           int idx2 = r * rowStride + idx0;
                           temp[idx1] = a[idx2];
                           temp[idx1 + 1] = a[idx2 + 1];
                        }

                        DoubleFFT_2D.this.fftRows.complexForward(temp);

                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx1 = 2 * r;
                           int idx2 = r * rowStride + idx0;
                           a[idx2] = temp[idx1];
                           a[idx2 + 1] = temp[idx1 + 1];
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexForward(a, r * rowStride);
            }

            double[] temp = new double[2 * this.rows];

            for(int c = 0; c < this.columns; ++c) {
               int idx0 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = 2 * r;
                  int idx2 = r * rowStride + idx0;
                  temp[idx1] = a[idx2];
                  temp[idx1 + 1] = a[idx2 + 1];
               }

               this.fftRows.complexForward(temp);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = 2 * r;
                  int idx2 = r * rowStride + idx0;
                  a[idx2] = temp[idx1];
                  a[idx2 + 1] = temp[idx1 + 1];
               }
            }
         }
      }

   }

   public void complexForward(final DoubleLargeArray a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         this.columnsl = 2L * this.columnsl;
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(0L, -1, a, true);
            this.cdft2d_subth(-1, (DoubleLargeArray)a, true);
         } else {
            for(int r = 0; (long)r < this.rowsl; ++r) {
               this.fftColumns.complexForward(a, (long)r * this.columnsl);
            }

            this.cdft2d_sub(-1, (DoubleLargeArray)a, true);
         }

         this.columnsl /= 2L;
      } else {
         final long rowStride = 2L * this.columnsl;
         if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstRow = (long)l * p;
               final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long r = firstRow; r < lastRow; ++r) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a, r * rowStride);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columnsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstColumn = (long)l * p;
               final long lastColumn = l == nthreads - 1 ? this.columnsl : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_2D.this.rowsl, false);

                     for(long c = firstColumn; c < lastColumn; ++c) {
                        long idx0 = 2L * c;

                        for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                           long idx1 = 2L * r;
                           long idx2 = r * rowStride + idx0;
                           temp.setDouble(idx1, a.getDouble(idx2));
                           temp.setDouble(idx1 + 1L, a.getDouble(idx2 + 1L));
                        }

                        DoubleFFT_2D.this.fftRows.complexForward(temp);

                        for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                           long idx1 = 2L * r;
                           long idx2 = r * rowStride + idx0;
                           a.setDouble(idx2, temp.getDouble(idx1));
                           a.setDouble(idx2 + 1L, temp.getDouble(idx1 + 1L));
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               this.fftColumns.complexForward(a, r * rowStride);
            }

            DoubleLargeArray temp = new DoubleLargeArray(2L * this.rowsl, false);

            for(long c = 0L; c < this.columnsl; ++c) {
               long idx0 = 2L * c;

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = 2L * r;
                  long idx2 = r * rowStride + idx0;
                  temp.setDouble(idx1, a.getDouble(idx2));
                  temp.setDouble(idx1 + 1L, a.getDouble(idx2 + 1L));
               }

               this.fftRows.complexForward(temp);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = 2L * r;
                  long idx2 = r * rowStride + idx0;
                  a.setDouble(idx2, temp.getDouble(idx1));
                  a.setDouble(idx2 + 1L, temp.getDouble(idx1 + 1L));
               }
            }
         }
      }

   }

   public void complexForward(final double[][] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         this.columns = 2 * this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(0, -1, (double[][])a, true);
            this.cdft2d_subth(-1, (double[][])a, true);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexForward(a[r]);
            }

            this.cdft2d_sub(-1, (double[][])a, true);
         }

         this.columns /= 2;
      } else if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     DoubleFFT_2D.this.fftColumns.complexForward(a[r]);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columns / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = l * p;
            final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_2D.this.rows];

                  for(int c = firstColumn; c < lastColumn; ++c) {
                     int idx1 = 2 * c;

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        temp[idx2] = a[r][idx1];
                        temp[idx2 + 1] = a[r][idx1 + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(temp);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        a[r][idx1] = temp[idx2];
                        a[r][idx1 + 1] = temp[idx2 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 0; r < this.rows; ++r) {
            this.fftColumns.complexForward(a[r]);
         }

         double[] temp = new double[2 * this.rows];

         for(int c = 0; c < this.columns; ++c) {
            int idx1 = 2 * c;

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               temp[idx2] = a[r][idx1];
               temp[idx2 + 1] = a[r][idx1 + 1];
            }

            this.fftRows.complexForward(temp);

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               a[r][idx1] = temp[idx2];
               a[r][idx1 + 1] = temp[idx2 + 1];
            }
         }
      }

   }

   public void complexInverse(final double[] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         this.columns = 2 * this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(0, 1, (double[])a, scale);
            this.cdft2d_subth(1, (double[])a, scale);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexInverse(a, r * this.columns, scale);
            }

            this.cdft2d_sub(1, (double[])a, scale);
         }

         this.columns /= 2;
      } else {
         final int rowspan = 2 * this.columns;
         if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int r = firstRow; r < lastRow; ++r) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a, r * rowspan, scale);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columns / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstColumn = l * p;
               final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     double[] temp = new double[2 * DoubleFFT_2D.this.rows];

                     for(int c = firstColumn; c < lastColumn; ++c) {
                        int idx1 = 2 * c;

                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = r * rowspan + idx1;
                           temp[idx2] = a[idx3];
                           temp[idx2 + 1] = a[idx3 + 1];
                        }

                        DoubleFFT_2D.this.fftRows.complexInverse(temp, scale);

                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = r * rowspan + idx1;
                           a[idx3] = temp[idx2];
                           a[idx3 + 1] = temp[idx2 + 1];
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexInverse(a, r * rowspan, scale);
            }

            double[] temp = new double[2 * this.rows];

            for(int c = 0; c < this.columns; ++c) {
               int idx1 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = r * rowspan + idx1;
                  temp[idx2] = a[idx3];
                  temp[idx2 + 1] = a[idx3 + 1];
               }

               this.fftRows.complexInverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = r * rowspan + idx1;
                  a[idx3] = temp[idx2];
                  a[idx3 + 1] = temp[idx2 + 1];
               }
            }
         }
      }

   }

   public void complexInverse(final DoubleLargeArray a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         this.columnsl = 2L * this.columnsl;
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(0L, 1, a, scale);
            this.cdft2d_subth(1, (DoubleLargeArray)a, scale);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               this.fftColumns.complexInverse(a, r * this.columnsl, scale);
            }

            this.cdft2d_sub(1, (DoubleLargeArray)a, scale);
         }

         this.columnsl /= 2L;
      } else {
         final long rowspan = 2L * this.columnsl;
         if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstRow = (long)l * p;
               final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long r = firstRow; r < lastRow; ++r) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a, r * rowspan, scale);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columnsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstColumn = (long)l * p;
               final long lastColumn = l == nthreads - 1 ? this.columnsl : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_2D.this.rowsl, false);

                     for(long c = firstColumn; c < lastColumn; ++c) {
                        long idx1 = 2L * c;

                        for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                           long idx2 = 2L * r;
                           long idx3 = r * rowspan + idx1;
                           temp.setDouble(idx2, a.getDouble(idx3));
                           temp.setDouble(idx2 + 1L, a.getDouble(idx3 + 1L));
                        }

                        DoubleFFT_2D.this.fftRows.complexInverse(temp, scale);

                        for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                           long idx2 = 2L * r;
                           long idx3 = r * rowspan + idx1;
                           a.setDouble(idx3, temp.getDouble(idx2));
                           a.setDouble(idx3 + 1L, temp.getDouble(idx2 + 1L));
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               this.fftColumns.complexInverse(a, r * rowspan, scale);
            }

            DoubleLargeArray temp = new DoubleLargeArray(2L * this.rowsl, false);

            for(long c = 0L; c < this.columnsl; ++c) {
               long idx1 = 2L * c;

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx2 = 2L * r;
                  long idx3 = r * rowspan + idx1;
                  temp.setDouble(idx2, a.getDouble(idx3));
                  temp.setDouble(idx2 + 1L, a.getDouble(idx3 + 1L));
               }

               this.fftRows.complexInverse(temp, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx2 = 2L * r;
                  long idx3 = r * rowspan + idx1;
                  a.setDouble(idx3, temp.getDouble(idx2));
                  a.setDouble(idx3 + 1L, temp.getDouble(idx2 + 1L));
               }
            }
         }
      }

   }

   public void complexInverse(final double[][] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         this.columns = 2 * this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(0, 1, (double[][])a, scale);
            this.cdft2d_subth(1, (double[][])a, scale);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexInverse(a[r], scale);
            }

            this.cdft2d_sub(1, (double[][])a, scale);
         }

         this.columns /= 2;
      } else if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     DoubleFFT_2D.this.fftColumns.complexInverse(a[r], scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columns / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = l * p;
            final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_2D.this.rows];

                  for(int c = firstColumn; c < lastColumn; ++c) {
                     int idx1 = 2 * c;

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        temp[idx2] = a[r][idx1];
                        temp[idx2 + 1] = a[r][idx1 + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexInverse(temp, scale);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        a[r][idx1] = temp[idx2];
                        a[r][idx1 + 1] = temp[idx2 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 0; r < this.rows; ++r) {
            this.fftColumns.complexInverse(a[r], scale);
         }

         double[] temp = new double[2 * this.rows];

         for(int c = 0; c < this.columns; ++c) {
            int idx1 = 2 * c;

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               temp[idx2] = a[r][idx1];
               temp[idx2 + 1] = a[r][idx1 + 1];
            }

            this.fftRows.complexInverse(temp, scale);

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               a[r][idx1] = temp[idx2];
               a[r][idx1 + 1] = temp[idx2 + 1];
            }
         }
      }

   }

   public void realForward(double[] a) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(1, 1, (double[])a, true);
            this.cdft2d_subth(-1, (double[])a, true);
            this.rdft2d_sub(1, (double[])a);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realForward(a, r * this.columns);
            }

            this.cdft2d_sub(-1, (double[])a, true);
            this.rdft2d_sub(1, (double[])a);
         }

      }
   }

   public void realForward(DoubleLargeArray a) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(1L, 1, a, true);
            this.cdft2d_subth(-1, (DoubleLargeArray)a, true);
            this.rdft2d_sub(1, (DoubleLargeArray)a);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               this.fftColumns.realForward(a, r * this.columnsl);
            }

            this.cdft2d_sub(-1, (DoubleLargeArray)a, true);
            this.rdft2d_sub(1, (DoubleLargeArray)a);
         }

      }
   }

   public void realForward(double[][] a) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(1, 1, (double[][])a, true);
            this.cdft2d_subth(-1, (double[][])a, true);
            this.rdft2d_sub(1, (double[][])a);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realForward(a[r]);
            }

            this.cdft2d_sub(-1, (double[][])a, true);
            this.rdft2d_sub(1, (double[][])a);
         }

      }
   }

   public void realForwardFull(double[] a) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(1, 1, (double[])a, true);
            this.cdft2d_subth(-1, (double[])a, true);
            this.rdft2d_sub(1, (double[])a);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realForward(a, r * this.columns);
            }

            this.cdft2d_sub(-1, (double[])a, true);
            this.rdft2d_sub(1, (double[])a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealForwardFull(a);
      }

   }

   public void realForwardFull(DoubleLargeArray a) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(1L, 1, a, true);
            this.cdft2d_subth(-1, (DoubleLargeArray)a, true);
            this.rdft2d_sub(1, (DoubleLargeArray)a);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               this.fftColumns.realForward(a, r * this.columnsl);
            }

            this.cdft2d_sub(-1, (DoubleLargeArray)a, true);
            this.rdft2d_sub(1, (DoubleLargeArray)a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealForwardFull(a);
      }

   }

   public void realForwardFull(double[][] a) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth1(1, 1, (double[][])a, true);
            this.cdft2d_subth(-1, (double[][])a, true);
            this.rdft2d_sub(1, (double[][])a);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realForward(a[r]);
            }

            this.cdft2d_sub(-1, (double[][])a, true);
            this.rdft2d_sub(1, (double[][])a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealForwardFull(a);
      }

   }

   public void realInverse(double[] a, boolean scale) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.rdft2d_sub(-1, (double[])a);
            this.cdft2d_subth(1, (double[])a, scale);
            this.xdft2d0_subth1(1, -1, (double[])a, scale);
         } else {
            this.rdft2d_sub(-1, (double[])a);
            this.cdft2d_sub(1, (double[])a, scale);

            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realInverse(a, r * this.columns, scale);
            }
         }

      }
   }

   public void realInverse(DoubleLargeArray a, boolean scale) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.rdft2d_sub(-1, (DoubleLargeArray)a);
            this.cdft2d_subth(1, (DoubleLargeArray)a, scale);
            this.xdft2d0_subth1(1L, -1, a, scale);
         } else {
            this.rdft2d_sub(-1, (DoubleLargeArray)a);
            this.cdft2d_sub(1, (DoubleLargeArray)a, scale);

            for(long r = 0L; r < this.rowsl; ++r) {
               this.fftColumns.realInverse(a, r * this.columnsl, scale);
            }
         }

      }
   }

   public void realInverse(double[][] a, boolean scale) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.rdft2d_sub(-1, (double[][])a);
            this.cdft2d_subth(1, (double[][])a, scale);
            this.xdft2d0_subth1(1, -1, (double[][])a, scale);
         } else {
            this.rdft2d_sub(-1, (double[][])a);
            this.cdft2d_sub(1, (double[][])a, scale);

            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realInverse(a[r], scale);
            }
         }

      }
   }

   public void realInverseFull(double[] a, boolean scale) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth2(1, -1, (double[])a, scale);
            this.cdft2d_subth(1, (double[])a, scale);
            this.rdft2d_sub(1, (double[])a);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realInverse2(a, r * this.columns, scale);
            }

            this.cdft2d_sub(1, (double[])a, scale);
            this.rdft2d_sub(1, (double[])a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealInverseFull(a, scale);
      }

   }

   public void realInverseFull(DoubleLargeArray a, boolean scale) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth2(1L, -1, a, scale);
            this.cdft2d_subth(1, (DoubleLargeArray)a, scale);
            this.rdft2d_sub(1, (DoubleLargeArray)a);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               this.fftColumns.realInverse2(a, r * this.columnsl, scale);
            }

            this.cdft2d_sub(1, (DoubleLargeArray)a, scale);
            this.rdft2d_sub(1, (DoubleLargeArray)a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealInverseFull(a, scale);
      }

   }

   public void realInverseFull(double[][] a, boolean scale) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft2d0_subth2(1, -1, (double[][])a, scale);
            this.cdft2d_subth(1, (double[][])a, scale);
            this.rdft2d_sub(1, (double[][])a);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realInverse2(a[r], 0, scale);
            }

            this.cdft2d_sub(1, (double[][])a, scale);
            this.rdft2d_sub(1, (double[][])a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealInverseFull(a, scale);
      }

   }

   private void mixedRadixRealForwardFull(final double[][] a) {
      final int n2d2 = this.columns / 2 + 1;
      final double[][] temp = new double[n2d2][2 * this.rows];
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.rows >= nthreads && n2d2 - 2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleFFT_2D.this.fftColumns.realForward(a[i]);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r][0];
         }

         this.fftRows.realForwardFull(temp[0]);
         p = (n2d2 - 2) / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = 1 + l * p;
            final int lastColumn = l == nthreads - 1 ? n2d2 - 1 : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int c = firstColumn; c < lastColumn; ++c) {
                     int idx2 = 2 * c;

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = 2 * r;
                        temp[c][idx1] = a[r][idx2];
                        temp[c][idx1 + 1] = a[r][idx2 + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(temp[c]);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r][1];
            }

            this.fftRows.realForwardFull(temp[n2d2 - 1]);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = n2d2 - 1;
               temp[idx2][idx1] = a[r][2 * idx2];
               temp[idx2][idx1 + 1] = a[r][1];
            }

            this.fftRows.complexForward(temp[n2d2 - 1]);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx1 = 2 * r;

                     for(int c = 0; c < n2d2; ++c) {
                        int idx2 = 2 * c;
                        a[r][idx2] = temp[c][idx1];
                        a[r][idx2 + 1] = temp[c][idx1 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = 1 + l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx3 = DoubleFFT_2D.this.rows - r;

                     for(int c = n2d2; c < DoubleFFT_2D.this.columns; ++c) {
                        int idx1 = 2 * c;
                        int idx2 = 2 * (DoubleFFT_2D.this.columns - c);
                        a[0][idx1] = a[0][idx2];
                        a[0][idx1 + 1] = -a[0][idx2 + 1];
                        a[r][idx1] = a[idx3][idx2];
                        a[r][idx1 + 1] = -a[idx3][idx2 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 0; r < this.rows; ++r) {
            this.fftColumns.realForward(a[r]);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r][0];
         }

         this.fftRows.realForwardFull(temp[0]);

         for(int c = 1; c < n2d2 - 1; ++c) {
            int idx2 = 2 * c;

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               temp[c][idx1] = a[r][idx2];
               temp[c][idx1 + 1] = a[r][idx2 + 1];
            }

            this.fftRows.complexForward(temp[c]);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r][1];
            }

            this.fftRows.realForwardFull(temp[n2d2 - 1]);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = n2d2 - 1;
               temp[idx2][idx1] = a[r][2 * idx2];
               temp[idx2][idx1 + 1] = a[r][1];
            }

            this.fftRows.complexForward(temp[n2d2 - 1]);
         }

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = 2 * r;

            for(int c = 0; c < n2d2; ++c) {
               int idx2 = 2 * c;
               a[r][idx2] = temp[c][idx1];
               a[r][idx2 + 1] = temp[c][idx1 + 1];
            }
         }

         for(int r = 1; r < this.rows; ++r) {
            int idx3 = this.rows - r;

            for(int c = n2d2; c < this.columns; ++c) {
               int idx1 = 2 * c;
               int idx2 = 2 * (this.columns - c);
               a[0][idx1] = a[0][idx2];
               a[0][idx1 + 1] = -a[0][idx2 + 1];
               a[r][idx1] = a[idx3][idx2];
               a[r][idx1 + 1] = -a[idx3][idx2 + 1];
            }
         }
      }

   }

   private void mixedRadixRealForwardFull(final double[] a) {
      final int rowStride = 2 * this.columns;
      final int n2d2 = this.columns / 2 + 1;
      final double[][] temp = new double[n2d2][2 * this.rows];
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.rows >= nthreads && n2d2 - 2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleFFT_2D.this.fftColumns.realForward(a, i * DoubleFFT_2D.this.columns);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r * this.columns];
         }

         this.fftRows.realForwardFull(temp[0]);
         p = (n2d2 - 2) / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = 1 + l * p;
            final int lastColumn = l == nthreads - 1 ? n2d2 - 1 : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int c = firstColumn; c < lastColumn; ++c) {
                     int idx0 = 2 * c;

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = 2 * r;
                        int idx2 = r * DoubleFFT_2D.this.columns + idx0;
                        temp[c][idx1] = a[idx2];
                        temp[c][idx1 + 1] = a[idx2 + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(temp[c]);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r * this.columns + 1];
            }

            this.fftRows.realForwardFull(temp[n2d2 - 1]);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = r * this.columns;
               int idx3 = n2d2 - 1;
               temp[idx3][idx1] = a[idx2 + 2 * idx3];
               temp[idx3][idx1 + 1] = a[idx2 + 1];
            }

            this.fftRows.complexForward(temp[n2d2 - 1]);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx1 = 2 * r;

                     for(int c = 0; c < n2d2; ++c) {
                        int idx0 = 2 * c;
                        int idx2 = r * rowStride + idx0;
                        a[idx2] = temp[c][idx1];
                        a[idx2 + 1] = temp[c][idx1 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = 1 + l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx5 = r * rowStride;
                     int idx6 = (DoubleFFT_2D.this.rows - r + 1) * rowStride;

                     for(int c = n2d2; c < DoubleFFT_2D.this.columns; ++c) {
                        int idx1 = 2 * c;
                        int idx2 = 2 * (DoubleFFT_2D.this.columns - c);
                        a[idx1] = a[idx2];
                        a[idx1 + 1] = -a[idx2 + 1];
                        int idx3 = idx5 + idx1;
                        int idx4 = idx6 - idx1;
                        a[idx3] = a[idx4];
                        a[idx3 + 1] = -a[idx4 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 0; r < this.rows; ++r) {
            this.fftColumns.realForward(a, r * this.columns);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r * this.columns];
         }

         this.fftRows.realForwardFull(temp[0]);

         for(int c = 1; c < n2d2 - 1; ++c) {
            int idx0 = 2 * c;

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = r * this.columns + idx0;
               temp[c][idx1] = a[idx2];
               temp[c][idx1 + 1] = a[idx2 + 1];
            }

            this.fftRows.complexForward(temp[c]);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r * this.columns + 1];
            }

            this.fftRows.realForwardFull(temp[n2d2 - 1]);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = r * this.columns;
               int idx3 = n2d2 - 1;
               temp[idx3][idx1] = a[idx2 + 2 * idx3];
               temp[idx3][idx1 + 1] = a[idx2 + 1];
            }

            this.fftRows.complexForward(temp[n2d2 - 1]);
         }

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = 2 * r;

            for(int c = 0; c < n2d2; ++c) {
               int idx0 = 2 * c;
               int idx2 = r * rowStride + idx0;
               a[idx2] = temp[c][idx1];
               a[idx2 + 1] = temp[c][idx1 + 1];
            }
         }

         for(int r = 1; r < this.rows; ++r) {
            int idx5 = r * rowStride;
            int idx6 = (this.rows - r + 1) * rowStride;

            for(int c = n2d2; c < this.columns; ++c) {
               int idx1 = 2 * c;
               int idx2 = 2 * (this.columns - c);
               a[idx1] = a[idx2];
               a[idx1 + 1] = -a[idx2 + 1];
               int idx3 = idx5 + idx1;
               int idx4 = idx6 - idx1;
               a[idx3] = a[idx4];
               a[idx3 + 1] = -a[idx4 + 1];
            }
         }
      }

   }

   private void mixedRadixRealForwardFull(final DoubleLargeArray a) {
      final long rowStride = 2L * this.columnsl;
      final long n2d2 = this.columnsl / 2L + 1L;
      final DoubleLargeArray temp = new DoubleLargeArray(n2d2 * 2L * this.rowsl);
      final long temp_stride = 2L * this.rowsl;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && n2d2 - 2L >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstRow; i < lastRow; ++i) {
                     DoubleFFT_2D.this.fftColumns.realForward(a, i * DoubleFFT_2D.this.columnsl);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            temp.setDouble(r, a.getDouble(r * this.columnsl));
         }

         this.fftRows.realForwardFull(temp);
         p = (n2d2 - 2L) / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstColumn = 1L + (long)l * p;
            final long lastColumn = l == nthreads - 1 ? n2d2 - 1L : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long c = firstColumn; c < lastColumn; ++c) {
                     long idx0 = 2L * c;

                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = 2L * r;
                        long idx2 = r * DoubleFFT_2D.this.columnsl + idx0;
                        temp.setDouble(c * temp_stride + idx1, a.getDouble(idx2));
                        temp.setDouble(c * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(temp, c * temp_stride);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         if (this.columnsl % 2L == 0L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               temp.setDouble((n2d2 - 1L) * temp_stride + r, a.getDouble(r * this.columnsl + 1L));
            }

            this.fftRows.realForwardFull(temp, (n2d2 - 1L) * temp_stride);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = 2L * r;
               long idx2 = r * this.columnsl;
               long idx3 = n2d2 - 1L;
               temp.setDouble(idx3 * temp_stride + idx1, a.getDouble(idx2 + 2L * idx3));
               temp.setDouble(idx3 * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
            }

            this.fftRows.complexForward(temp, (n2d2 - 1L) * temp_stride);
         }

         p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx1 = 2L * r;

                     for(long c = 0L; c < n2d2; ++c) {
                        long idx0 = 2L * c;
                        long idx2 = r * rowStride + idx0;
                        a.setDouble(idx2, temp.getDouble(c * temp_stride + idx1));
                        a.setDouble(idx2 + 1L, temp.getDouble(c * temp_stride + idx1 + 1L));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = 1L + (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx5 = r * rowStride;
                     long idx6 = (DoubleFFT_2D.this.rowsl - r + 1L) * rowStride;

                     for(long c = n2d2; c < DoubleFFT_2D.this.columnsl; ++c) {
                        long idx1 = 2L * c;
                        long idx2 = 2L * (DoubleFFT_2D.this.columnsl - c);
                        a.setDouble(idx1, a.getDouble(idx2));
                        a.setDouble(idx1 + 1L, -a.getDouble(idx2 + 1L));
                        long idx3 = idx5 + idx1;
                        long idx4 = idx6 - idx1;
                        a.setDouble(idx3, a.getDouble(idx4));
                        a.setDouble(idx3 + 1L, -a.getDouble(idx4 + 1L));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long r = 0L; r < this.rowsl; ++r) {
            this.fftColumns.realForward(a, r * this.columnsl);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            temp.setDouble(r, a.getDouble(r * this.columnsl));
         }

         this.fftRows.realForwardFull(temp);

         for(long c = 1L; c < n2d2 - 1L; ++c) {
            long idx0 = 2L * c;

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = 2L * r;
               long idx2 = r * this.columnsl + idx0;
               temp.setDouble(c * temp_stride + idx1, a.getDouble(idx2));
               temp.setDouble(c * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
            }

            this.fftRows.complexForward(temp, c * temp_stride);
         }

         if (this.columnsl % 2L == 0L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               temp.setDouble((n2d2 - 1L) * temp_stride + r, a.getDouble(r * this.columnsl + 1L));
            }

            this.fftRows.realForwardFull(temp, (n2d2 - 1L) * temp_stride);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = 2L * r;
               long idx2 = r * this.columnsl;
               long idx3 = n2d2 - 1L;
               temp.setDouble(idx3 * temp_stride + idx1, a.getDouble(idx2 + 2L * idx3));
               temp.setDouble(idx3 * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
            }

            this.fftRows.complexForward(temp, (n2d2 - 1L) * temp_stride);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = 2L * r;

            for(long c = 0L; c < n2d2; ++c) {
               long idx0 = 2L * c;
               long idx2 = r * rowStride + idx0;
               a.setDouble(idx2, temp.getDouble(c * temp_stride + idx1));
               a.setDouble(idx2 + 1L, temp.getDouble(c * temp_stride + idx1 + 1L));
            }
         }

         for(long r = 1L; r < this.rowsl; ++r) {
            long idx5 = r * rowStride;
            long idx6 = (this.rowsl - r + 1L) * rowStride;

            for(long c = n2d2; c < this.columnsl; ++c) {
               long idx1 = 2L * c;
               long idx2 = 2L * (this.columnsl - c);
               a.setDouble(idx1, a.getDouble(idx2));
               a.setDouble(idx1 + 1L, -a.getDouble(idx2 + 1L));
               long idx3 = idx5 + idx1;
               long idx4 = idx6 - idx1;
               a.setDouble(idx3, a.getDouble(idx4));
               a.setDouble(idx3 + 1L, -a.getDouble(idx4 + 1L));
            }
         }
      }

   }

   private void mixedRadixRealInverseFull(final double[][] a, final boolean scale) {
      final int n2d2 = this.columns / 2 + 1;
      final double[][] temp = new double[n2d2][2 * this.rows];
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.rows >= nthreads && n2d2 - 2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleFFT_2D.this.fftColumns.realInverse2(a[i], 0, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r][0];
         }

         this.fftRows.realInverseFull(temp[0], scale);
         p = (n2d2 - 2) / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = 1 + l * p;
            final int lastColumn = l == nthreads - 1 ? n2d2 - 1 : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int c = firstColumn; c < lastColumn; ++c) {
                     int idx2 = 2 * c;

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = 2 * r;
                        temp[c][idx1] = a[r][idx2];
                        temp[c][idx1 + 1] = a[r][idx2 + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexInverse(temp[c], scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r][1];
            }

            this.fftRows.realInverseFull(temp[n2d2 - 1], scale);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = n2d2 - 1;
               temp[idx2][idx1] = a[r][2 * idx2];
               temp[idx2][idx1 + 1] = a[r][1];
            }

            this.fftRows.complexInverse(temp[n2d2 - 1], scale);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx1 = 2 * r;

                     for(int c = 0; c < n2d2; ++c) {
                        int idx2 = 2 * c;
                        a[r][idx2] = temp[c][idx1];
                        a[r][idx2 + 1] = temp[c][idx1 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = 1 + l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx3 = DoubleFFT_2D.this.rows - r;

                     for(int c = n2d2; c < DoubleFFT_2D.this.columns; ++c) {
                        int idx1 = 2 * c;
                        int idx2 = 2 * (DoubleFFT_2D.this.columns - c);
                        a[0][idx1] = a[0][idx2];
                        a[0][idx1 + 1] = -a[0][idx2 + 1];
                        a[r][idx1] = a[idx3][idx2];
                        a[r][idx1 + 1] = -a[idx3][idx2 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 0; r < this.rows; ++r) {
            this.fftColumns.realInverse2(a[r], 0, scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r][0];
         }

         this.fftRows.realInverseFull(temp[0], scale);

         for(int c = 1; c < n2d2 - 1; ++c) {
            int idx2 = 2 * c;

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               temp[c][idx1] = a[r][idx2];
               temp[c][idx1 + 1] = a[r][idx2 + 1];
            }

            this.fftRows.complexInverse(temp[c], scale);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r][1];
            }

            this.fftRows.realInverseFull(temp[n2d2 - 1], scale);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = n2d2 - 1;
               temp[idx2][idx1] = a[r][2 * idx2];
               temp[idx2][idx1 + 1] = a[r][1];
            }

            this.fftRows.complexInverse(temp[n2d2 - 1], scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = 2 * r;

            for(int c = 0; c < n2d2; ++c) {
               int idx2 = 2 * c;
               a[r][idx2] = temp[c][idx1];
               a[r][idx2 + 1] = temp[c][idx1 + 1];
            }
         }

         for(int r = 1; r < this.rows; ++r) {
            int idx3 = this.rows - r;

            for(int c = n2d2; c < this.columns; ++c) {
               int idx1 = 2 * c;
               int idx2 = 2 * (this.columns - c);
               a[0][idx1] = a[0][idx2];
               a[0][idx1 + 1] = -a[0][idx2 + 1];
               a[r][idx1] = a[idx3][idx2];
               a[r][idx1 + 1] = -a[idx3][idx2 + 1];
            }
         }
      }

   }

   private void mixedRadixRealInverseFull(final double[] a, final boolean scale) {
      final int rowStride = 2 * this.columns;
      final int n2d2 = this.columns / 2 + 1;
      final double[][] temp = new double[n2d2][2 * this.rows];
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.rows >= nthreads && n2d2 - 2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleFFT_2D.this.fftColumns.realInverse2(a, i * DoubleFFT_2D.this.columns, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r * this.columns];
         }

         this.fftRows.realInverseFull(temp[0], scale);
         p = (n2d2 - 2) / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = 1 + l * p;
            final int lastColumn = l == nthreads - 1 ? n2d2 - 1 : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int c = firstColumn; c < lastColumn; ++c) {
                     int idx0 = 2 * c;

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = 2 * r;
                        int idx2 = r * DoubleFFT_2D.this.columns + idx0;
                        temp[c][idx1] = a[idx2];
                        temp[c][idx1 + 1] = a[idx2 + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexInverse(temp[c], scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r * this.columns + 1];
            }

            this.fftRows.realInverseFull(temp[n2d2 - 1], scale);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = r * this.columns;
               int idx3 = n2d2 - 1;
               temp[idx3][idx1] = a[idx2 + 2 * idx3];
               temp[idx3][idx1 + 1] = a[idx2 + 1];
            }

            this.fftRows.complexInverse(temp[n2d2 - 1], scale);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx1 = 2 * r;

                     for(int c = 0; c < n2d2; ++c) {
                        int idx0 = 2 * c;
                        int idx2 = r * rowStride + idx0;
                        a[idx2] = temp[c][idx1];
                        a[idx2 + 1] = temp[c][idx1 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = 1 + l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx5 = r * rowStride;
                     int idx6 = (DoubleFFT_2D.this.rows - r + 1) * rowStride;

                     for(int c = n2d2; c < DoubleFFT_2D.this.columns; ++c) {
                        int idx1 = 2 * c;
                        int idx2 = 2 * (DoubleFFT_2D.this.columns - c);
                        a[idx1] = a[idx2];
                        a[idx1 + 1] = -a[idx2 + 1];
                        int idx3 = idx5 + idx1;
                        int idx4 = idx6 - idx1;
                        a[idx3] = a[idx4];
                        a[idx3 + 1] = -a[idx4 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 0; r < this.rows; ++r) {
            this.fftColumns.realInverse2(a, r * this.columns, scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            temp[0][r] = a[r * this.columns];
         }

         this.fftRows.realInverseFull(temp[0], scale);

         for(int c = 1; c < n2d2 - 1; ++c) {
            int idx0 = 2 * c;

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = r * this.columns + idx0;
               temp[c][idx1] = a[idx2];
               temp[c][idx1 + 1] = a[idx2 + 1];
            }

            this.fftRows.complexInverse(temp[c], scale);
         }

         if (this.columns % 2 == 0) {
            for(int r = 0; r < this.rows; ++r) {
               temp[n2d2 - 1][r] = a[r * this.columns + 1];
            }

            this.fftRows.realInverseFull(temp[n2d2 - 1], scale);
         } else {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = 2 * r;
               int idx2 = r * this.columns;
               int idx3 = n2d2 - 1;
               temp[idx3][idx1] = a[idx2 + 2 * idx3];
               temp[idx3][idx1 + 1] = a[idx2 + 1];
            }

            this.fftRows.complexInverse(temp[n2d2 - 1], scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = 2 * r;

            for(int c = 0; c < n2d2; ++c) {
               int idx0 = 2 * c;
               int idx2 = r * rowStride + idx0;
               a[idx2] = temp[c][idx1];
               a[idx2 + 1] = temp[c][idx1 + 1];
            }
         }

         for(int r = 1; r < this.rows; ++r) {
            int idx5 = r * rowStride;
            int idx6 = (this.rows - r + 1) * rowStride;

            for(int c = n2d2; c < this.columns; ++c) {
               int idx1 = 2 * c;
               int idx2 = 2 * (this.columns - c);
               a[idx1] = a[idx2];
               a[idx1 + 1] = -a[idx2 + 1];
               int idx3 = idx5 + idx1;
               int idx4 = idx6 - idx1;
               a[idx3] = a[idx4];
               a[idx3 + 1] = -a[idx4 + 1];
            }
         }
      }

   }

   private void mixedRadixRealInverseFull(final DoubleLargeArray a, final boolean scale) {
      final long rowStride = 2L * this.columnsl;
      final long n2d2 = this.columnsl / 2L + 1L;
      final DoubleLargeArray temp = new DoubleLargeArray(n2d2 * 2L * this.rowsl);
      final long temp_stride = 2L * this.rowsl;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && n2d2 - 2L >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstRow; i < lastRow; ++i) {
                     DoubleFFT_2D.this.fftColumns.realInverse2(a, i * DoubleFFT_2D.this.columnsl, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            temp.setDouble(r, a.getDouble(r * this.columnsl));
         }

         this.fftRows.realInverseFull(temp, scale);
         p = (n2d2 - 2L) / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstColumn = 1L + (long)l * p;
            final long lastColumn = l == nthreads - 1 ? n2d2 - 1L : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long c = firstColumn; c < lastColumn; ++c) {
                     long idx0 = 2L * c;

                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = 2L * r;
                        long idx2 = r * DoubleFFT_2D.this.columnsl + idx0;
                        temp.setDouble(c * temp_stride + idx1, a.getDouble(idx2));
                        temp.setDouble(c * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
                     }

                     DoubleFFT_2D.this.fftRows.complexInverse(temp, c * temp_stride, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         if (this.columnsl % 2L == 0L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               temp.setDouble((n2d2 - 1L) * temp_stride + r, a.getDouble(r * this.columnsl + 1L));
            }

            this.fftRows.realInverseFull(temp, (n2d2 - 1L) * temp_stride, scale);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = 2L * r;
               long idx2 = r * this.columnsl;
               long idx3 = n2d2 - 1L;
               temp.setDouble(idx3 * temp_stride + idx1, a.getDouble(idx2 + 2L * idx3));
               temp.setDouble(idx3 * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
            }

            this.fftRows.complexInverse(temp, (n2d2 - 1L) * temp_stride, scale);
         }

         p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx1 = 2L * r;

                     for(long c = 0L; c < n2d2; ++c) {
                        long idx0 = 2L * c;
                        long idx2 = r * rowStride + idx0;
                        a.setDouble(idx2, temp.getDouble(c * temp_stride + idx1));
                        a.setDouble(idx2 + 1L, temp.getDouble(c * temp_stride + idx1 + 1L));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = 1L + (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx5 = r * rowStride;
                     long idx6 = (DoubleFFT_2D.this.rowsl - r + 1L) * rowStride;

                     for(long c = n2d2; c < DoubleFFT_2D.this.columnsl; ++c) {
                        long idx1 = 2L * c;
                        long idx2 = 2L * (DoubleFFT_2D.this.columnsl - c);
                        a.setDouble(idx1, a.getDouble(idx2));
                        a.setDouble(idx1 + 1L, -a.getDouble(idx2 + 1L));
                        long idx3 = idx5 + idx1;
                        long idx4 = idx6 - idx1;
                        a.setDouble(idx3, a.getDouble(idx4));
                        a.setDouble(idx3 + 1L, -a.getDouble(idx4 + 1L));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long r = 0L; r < this.rowsl; ++r) {
            this.fftColumns.realInverse2(a, r * this.columnsl, scale);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            temp.setDouble(r, a.getDouble(r * this.columnsl));
         }

         this.fftRows.realInverseFull(temp, scale);

         for(long c = 1L; c < n2d2 - 1L; ++c) {
            long idx0 = 2L * c;

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = 2L * r;
               long idx2 = r * this.columnsl + idx0;
               temp.setDouble(c * temp_stride + idx1, a.getDouble(idx2));
               temp.setDouble(c * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
            }

            this.fftRows.complexInverse(temp, c * temp_stride, scale);
         }

         if (this.columnsl % 2L == 0L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               temp.setDouble((n2d2 - 1L) * temp_stride + r, a.getDouble(r * this.columnsl + 1L));
            }

            this.fftRows.realInverseFull(temp, (n2d2 - 1L) * temp_stride, scale);
         } else {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = 2L * r;
               long idx2 = r * this.columnsl;
               long idx3 = n2d2 - 1L;
               temp.setDouble(idx3 * temp_stride + idx1, a.getDouble(idx2 + 2L * idx3));
               temp.setDouble(idx3 * temp_stride + idx1 + 1L, a.getDouble(idx2 + 1L));
            }

            this.fftRows.complexInverse(temp, (n2d2 - 1L) * temp_stride, scale);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = 2L * r;

            for(long c = 0L; c < n2d2; ++c) {
               long idx0 = 2L * c;
               long idx2 = r * rowStride + idx0;
               a.setDouble(idx2, temp.getDouble(c * temp_stride + idx1));
               a.setDouble(idx2 + 1L, temp.getDouble(c * temp_stride + idx1 + 1L));
            }
         }

         for(long r = 1L; r < this.rowsl; ++r) {
            long idx5 = r * rowStride;
            long idx6 = (this.rowsl - r + 1L) * rowStride;

            for(long c = n2d2; c < this.columnsl; ++c) {
               long idx1 = 2L * c;
               long idx2 = 2L * (this.columnsl - c);
               a.setDouble(idx1, a.getDouble(idx2));
               a.setDouble(idx1 + 1L, -a.getDouble(idx2 + 1L));
               long idx3 = idx5 + idx1;
               long idx4 = idx6 - idx1;
               a.setDouble(idx3, a.getDouble(idx4));
               a.setDouble(idx3 + 1L, -a.getDouble(idx4 + 1L));
            }
         }
      }

   }

   private void rdft2d_sub(int isgn, double[] a) {
      int n1h = this.rows >> 1;
      if (isgn < 0) {
         for(int i = 1; i < n1h; ++i) {
            int j = this.rows - i;
            int idx1 = i * this.columns;
            int idx2 = j * this.columns;
            double xi = a[idx1] - a[idx2];
            a[idx1] += a[idx2];
            a[idx2] = xi;
            xi = a[idx2 + 1] - a[idx1 + 1];
            a[idx1 + 1] += a[idx2 + 1];
            a[idx2 + 1] = xi;
         }
      } else {
         for(int i = 1; i < n1h; ++i) {
            int j = this.rows - i;
            int idx1 = i * this.columns;
            int idx2 = j * this.columns;
            a[idx2] = (double)0.5F * (a[idx1] - a[idx2]);
            a[idx1] -= a[idx2];
            a[idx2 + 1] = (double)0.5F * (a[idx1 + 1] + a[idx2 + 1]);
            a[idx1 + 1] -= a[idx2 + 1];
         }
      }

   }

   private void rdft2d_sub(int isgn, DoubleLargeArray a) {
      long n1h = this.rowsl >> 1;
      if (isgn < 0) {
         for(long i = 1L; i < n1h; ++i) {
            long j = this.rowsl - i;
            long idx1 = i * this.columnsl;
            long idx2 = j * this.columnsl;
            double xi = a.getDouble(idx1) - a.getDouble(idx2);
            a.setDouble(idx1, a.getDouble(idx1) + a.getDouble(idx2));
            a.setDouble(idx2, xi);
            xi = a.getDouble(idx2 + 1L) - a.getDouble(idx1 + 1L);
            a.setDouble(idx1 + 1L, a.getDouble(idx1 + 1L) + a.getDouble(idx2 + 1L));
            a.setDouble(idx2 + 1L, xi);
         }
      } else {
         for(long i = 1L; i < n1h; ++i) {
            long j = this.rowsl - i;
            long idx1 = i * this.columnsl;
            long idx2 = j * this.columnsl;
            a.setDouble(idx2, (double)0.5F * (a.getDouble(idx1) - a.getDouble(idx2)));
            a.setDouble(idx1, a.getDouble(idx1) - a.getDouble(idx2));
            a.setDouble(idx2 + 1L, (double)0.5F * (a.getDouble(idx1 + 1L) + a.getDouble(idx2 + 1L)));
            a.setDouble(idx1 + 1L, a.getDouble(idx1 + 1L) - a.getDouble(idx2 + 1L));
         }
      }

   }

   private void rdft2d_sub(int isgn, double[][] a) {
      int n1h = this.rows >> 1;
      if (isgn < 0) {
         for(int i = 1; i < n1h; ++i) {
            int j = this.rows - i;
            double xi = a[i][0] - a[j][0];
            a[i][0] += a[j][0];
            a[j][0] = xi;
            xi = a[j][1] - a[i][1];
            a[i][1] += a[j][1];
            a[j][1] = xi;
         }
      } else {
         for(int i = 1; i < n1h; ++i) {
            int j = this.rows - i;
            a[j][0] = (double)0.5F * (a[i][0] - a[j][0]);
            a[i][0] -= a[j][0];
            a[j][1] = (double)0.5F * (a[i][1] + a[j][1]);
            a[i][1] -= a[j][1];
         }
      }

   }

   private void cdft2d_sub(int isgn, double[] a, boolean scale) {
      int nt = 8 * this.rows;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         if (this.columns > 4) {
            for(int c = 0; c < this.columns; c += 8) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = r * this.columns + c;
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  int idx4 = idx3 + 2 * this.rows;
                  int idx5 = idx4 + 2 * this.rows;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
                  t[idx3] = a[idx1 + 2];
                  t[idx3 + 1] = a[idx1 + 3];
                  t[idx4] = a[idx1 + 4];
                  t[idx4 + 1] = a[idx1 + 5];
                  t[idx5] = a[idx1 + 6];
                  t[idx5 + 1] = a[idx1 + 7];
               }

               this.fftRows.complexForward(t, 0);
               this.fftRows.complexForward(t, 2 * this.rows);
               this.fftRows.complexForward(t, 4 * this.rows);
               this.fftRows.complexForward(t, 6 * this.rows);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = r * this.columns + c;
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  int idx4 = idx3 + 2 * this.rows;
                  int idx5 = idx4 + 2 * this.rows;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
                  a[idx1 + 2] = t[idx3];
                  a[idx1 + 3] = t[idx3 + 1];
                  a[idx1 + 4] = t[idx4];
                  a[idx1 + 5] = t[idx4 + 1];
                  a[idx1 + 6] = t[idx5];
                  a[idx1 + 7] = t[idx5 + 1];
               }
            }
         } else if (this.columns == 4) {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.columns;
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               t[idx2] = a[idx1];
               t[idx2 + 1] = a[idx1 + 1];
               t[idx3] = a[idx1 + 2];
               t[idx3 + 1] = a[idx1 + 3];
            }

            this.fftRows.complexForward(t, 0);
            this.fftRows.complexForward(t, 2 * this.rows);

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.columns;
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               a[idx1] = t[idx2];
               a[idx1 + 1] = t[idx2 + 1];
               a[idx1 + 2] = t[idx3];
               a[idx1 + 3] = t[idx3 + 1];
            }
         } else if (this.columns == 2) {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.columns;
               int idx2 = 2 * r;
               t[idx2] = a[idx1];
               t[idx2 + 1] = a[idx1 + 1];
            }

            this.fftRows.complexForward(t, 0);

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.columns;
               int idx2 = 2 * r;
               a[idx1] = t[idx2];
               a[idx1 + 1] = t[idx2 + 1];
            }
         }
      } else if (this.columns > 4) {
         for(int c = 0; c < this.columns; c += 8) {
            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.columns + c;
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               int idx4 = idx3 + 2 * this.rows;
               int idx5 = idx4 + 2 * this.rows;
               t[idx2] = a[idx1];
               t[idx2 + 1] = a[idx1 + 1];
               t[idx3] = a[idx1 + 2];
               t[idx3 + 1] = a[idx1 + 3];
               t[idx4] = a[idx1 + 4];
               t[idx4 + 1] = a[idx1 + 5];
               t[idx5] = a[idx1 + 6];
               t[idx5 + 1] = a[idx1 + 7];
            }

            this.fftRows.complexInverse(t, 0, scale);
            this.fftRows.complexInverse(t, 2 * this.rows, scale);
            this.fftRows.complexInverse(t, 4 * this.rows, scale);
            this.fftRows.complexInverse(t, 6 * this.rows, scale);

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.columns + c;
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               int idx4 = idx3 + 2 * this.rows;
               int idx5 = idx4 + 2 * this.rows;
               a[idx1] = t[idx2];
               a[idx1 + 1] = t[idx2 + 1];
               a[idx1 + 2] = t[idx3];
               a[idx1 + 3] = t[idx3 + 1];
               a[idx1 + 4] = t[idx4];
               a[idx1 + 5] = t[idx4 + 1];
               a[idx1 + 6] = t[idx5];
               a[idx1 + 7] = t[idx5 + 1];
            }
         }
      } else if (this.columns == 4) {
         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.columns;
            int idx2 = 2 * r;
            int idx3 = 2 * this.rows + 2 * r;
            t[idx2] = a[idx1];
            t[idx2 + 1] = a[idx1 + 1];
            t[idx3] = a[idx1 + 2];
            t[idx3 + 1] = a[idx1 + 3];
         }

         this.fftRows.complexInverse(t, 0, scale);
         this.fftRows.complexInverse(t, 2 * this.rows, scale);

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.columns;
            int idx2 = 2 * r;
            int idx3 = 2 * this.rows + 2 * r;
            a[idx1] = t[idx2];
            a[idx1 + 1] = t[idx2 + 1];
            a[idx1 + 2] = t[idx3];
            a[idx1 + 3] = t[idx3 + 1];
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.columns;
            int idx2 = 2 * r;
            t[idx2] = a[idx1];
            t[idx2 + 1] = a[idx1 + 1];
         }

         this.fftRows.complexInverse(t, 0, scale);

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.columns;
            int idx2 = 2 * r;
            a[idx1] = t[idx2];
            a[idx1 + 1] = t[idx2 + 1];
         }
      }

   }

   private void cdft2d_sub(int isgn, DoubleLargeArray a, boolean scale) {
      long nt = 8L * this.rowsl;
      if (this.columnsl == 4L) {
         nt >>= 1;
      } else if (this.columnsl < 4L) {
         nt >>= 2;
      }

      DoubleLargeArray t = new DoubleLargeArray(nt);
      if (isgn == -1) {
         if (this.columnsl > 4L) {
            for(long c = 0L; c < this.columnsl; c += 8L) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = 2L * r;
                  long idx3 = 2L * this.rowsl + 2L * r;
                  long idx4 = idx3 + 2L * this.rowsl;
                  long idx5 = idx4 + 2L * this.rowsl;
                  t.setDouble(idx2, a.getDouble(idx1));
                  t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
                  t.setDouble(idx3, a.getDouble(idx1 + 2L));
                  t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
                  t.setDouble(idx4, a.getDouble(idx1 + 4L));
                  t.setDouble(idx4 + 1L, a.getDouble(idx1 + 5L));
                  t.setDouble(idx5, a.getDouble(idx1 + 6L));
                  t.setDouble(idx5 + 1L, a.getDouble(idx1 + 7L));
               }

               this.fftRows.complexForward(t, 0L);
               this.fftRows.complexForward(t, 2L * this.rowsl);
               this.fftRows.complexForward(t, 4L * this.rowsl);
               this.fftRows.complexForward(t, 6L * this.rowsl);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = 2L * r;
                  long idx3 = 2L * this.rowsl + 2L * r;
                  long idx4 = idx3 + 2L * this.rowsl;
                  long idx5 = idx4 + 2L * this.rowsl;
                  a.setDouble(idx1, t.getDouble(idx2));
                  a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
                  a.setDouble(idx1 + 2L, t.getDouble(idx3));
                  a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
                  a.setDouble(idx1 + 4L, t.getDouble(idx4));
                  a.setDouble(idx1 + 5L, t.getDouble(idx4 + 1L));
                  a.setDouble(idx1 + 6L, t.getDouble(idx5));
                  a.setDouble(idx1 + 7L, t.getDouble(idx5 + 1L));
               }
            }
         } else if (this.columnsl == 4L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.columnsl;
               long idx2 = 2L * r;
               long idx3 = 2L * this.rowsl + 2L * r;
               t.setDouble(idx2, a.getDouble(idx1));
               t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
               t.setDouble(idx3, a.getDouble(idx1 + 2L));
               t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
            }

            this.fftRows.complexForward(t, 0L);
            this.fftRows.complexForward(t, 2L * this.rowsl);

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.columnsl;
               long idx2 = 2L * r;
               long idx3 = 2L * this.rowsl + 2L * r;
               a.setDouble(idx1, t.getDouble(idx2));
               a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
               a.setDouble(idx1 + 2L, t.getDouble(idx3));
               a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
            }
         } else if (this.columnsl == 2L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.columnsl;
               long idx2 = 2L * r;
               t.setDouble(idx2, a.getDouble(idx1));
               t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
            }

            this.fftRows.complexForward(t, 0L);

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.columnsl;
               long idx2 = 2L * r;
               a.setDouble(idx1, t.getDouble(idx2));
               a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
            }
         }
      } else if (this.columnsl > 4L) {
         for(long c = 0L; c < this.columnsl; c += 8L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.columnsl + c;
               long idx2 = 2L * r;
               long idx3 = 2L * this.rowsl + 2L * r;
               long idx4 = idx3 + 2L * this.rowsl;
               long idx5 = idx4 + 2L * this.rowsl;
               t.setDouble(idx2, a.getDouble(idx1));
               t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
               t.setDouble(idx3, a.getDouble(idx1 + 2L));
               t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
               t.setDouble(idx4, a.getDouble(idx1 + 4L));
               t.setDouble(idx4 + 1L, a.getDouble(idx1 + 5L));
               t.setDouble(idx5, a.getDouble(idx1 + 6L));
               t.setDouble(idx5 + 1L, a.getDouble(idx1 + 7L));
            }

            this.fftRows.complexInverse(t, 0L, scale);
            this.fftRows.complexInverse(t, 2L * this.rowsl, scale);
            this.fftRows.complexInverse(t, 4L * this.rowsl, scale);
            this.fftRows.complexInverse(t, 6L * this.rowsl, scale);

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.columnsl + c;
               long idx2 = 2L * r;
               long idx3 = 2L * this.rowsl + 2L * r;
               long idx4 = idx3 + 2L * this.rowsl;
               long idx5 = idx4 + 2L * this.rowsl;
               a.setDouble(idx1, t.getDouble(idx2));
               a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
               a.setDouble(idx1 + 2L, t.getDouble(idx3));
               a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
               a.setDouble(idx1 + 4L, t.getDouble(idx4));
               a.setDouble(idx1 + 5L, t.getDouble(idx4 + 1L));
               a.setDouble(idx1 + 6L, t.getDouble(idx5));
               a.setDouble(idx1 + 7L, t.getDouble(idx5 + 1L));
            }
         }
      } else if (this.columnsl == 4L) {
         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            long idx2 = 2L * r;
            long idx3 = 2L * this.rowsl + 2L * r;
            t.setDouble(idx2, a.getDouble(idx1));
            t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
            t.setDouble(idx3, a.getDouble(idx1 + 2L));
            t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
         }

         this.fftRows.complexInverse(t, 0L, scale);
         this.fftRows.complexInverse(t, 2L * this.rowsl, scale);

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            long idx2 = 2L * r;
            long idx3 = 2L * this.rowsl + 2L * r;
            a.setDouble(idx1, t.getDouble(idx2));
            a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
            a.setDouble(idx1 + 2L, t.getDouble(idx3));
            a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
         }
      } else if (this.columnsl == 2L) {
         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            long idx2 = 2L * r;
            t.setDouble(idx2, a.getDouble(idx1));
            t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
         }

         this.fftRows.complexInverse(t, 0L, scale);

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            long idx2 = 2L * r;
            a.setDouble(idx1, t.getDouble(idx2));
            a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
         }
      }

   }

   private void cdft2d_sub(int isgn, double[][] a, boolean scale) {
      int nt = 8 * this.rows;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         if (this.columns > 4) {
            for(int c = 0; c < this.columns; c += 8) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  int idx4 = idx3 + 2 * this.rows;
                  int idx5 = idx4 + 2 * this.rows;
                  t[idx2] = a[r][c];
                  t[idx2 + 1] = a[r][c + 1];
                  t[idx3] = a[r][c + 2];
                  t[idx3 + 1] = a[r][c + 3];
                  t[idx4] = a[r][c + 4];
                  t[idx4 + 1] = a[r][c + 5];
                  t[idx5] = a[r][c + 6];
                  t[idx5 + 1] = a[r][c + 7];
               }

               this.fftRows.complexForward(t, 0);
               this.fftRows.complexForward(t, 2 * this.rows);
               this.fftRows.complexForward(t, 4 * this.rows);
               this.fftRows.complexForward(t, 6 * this.rows);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  int idx4 = idx3 + 2 * this.rows;
                  int idx5 = idx4 + 2 * this.rows;
                  a[r][c] = t[idx2];
                  a[r][c + 1] = t[idx2 + 1];
                  a[r][c + 2] = t[idx3];
                  a[r][c + 3] = t[idx3 + 1];
                  a[r][c + 4] = t[idx4];
                  a[r][c + 5] = t[idx4 + 1];
                  a[r][c + 6] = t[idx5];
                  a[r][c + 7] = t[idx5 + 1];
               }
            }
         } else if (this.columns == 4) {
            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               t[idx2] = a[r][0];
               t[idx2 + 1] = a[r][1];
               t[idx3] = a[r][2];
               t[idx3 + 1] = a[r][3];
            }

            this.fftRows.complexForward(t, 0);
            this.fftRows.complexForward(t, 2 * this.rows);

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               a[r][0] = t[idx2];
               a[r][1] = t[idx2 + 1];
               a[r][2] = t[idx3];
               a[r][3] = t[idx3 + 1];
            }
         } else if (this.columns == 2) {
            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               t[idx2] = a[r][0];
               t[idx2 + 1] = a[r][1];
            }

            this.fftRows.complexForward(t, 0);

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               a[r][0] = t[idx2];
               a[r][1] = t[idx2 + 1];
            }
         }
      } else if (this.columns > 4) {
         for(int c = 0; c < this.columns; c += 8) {
            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               int idx4 = idx3 + 2 * this.rows;
               int idx5 = idx4 + 2 * this.rows;
               t[idx2] = a[r][c];
               t[idx2 + 1] = a[r][c + 1];
               t[idx3] = a[r][c + 2];
               t[idx3 + 1] = a[r][c + 3];
               t[idx4] = a[r][c + 4];
               t[idx4 + 1] = a[r][c + 5];
               t[idx5] = a[r][c + 6];
               t[idx5 + 1] = a[r][c + 7];
            }

            this.fftRows.complexInverse(t, 0, scale);
            this.fftRows.complexInverse(t, 2 * this.rows, scale);
            this.fftRows.complexInverse(t, 4 * this.rows, scale);
            this.fftRows.complexInverse(t, 6 * this.rows, scale);

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = 2 * r;
               int idx3 = 2 * this.rows + 2 * r;
               int idx4 = idx3 + 2 * this.rows;
               int idx5 = idx4 + 2 * this.rows;
               a[r][c] = t[idx2];
               a[r][c + 1] = t[idx2 + 1];
               a[r][c + 2] = t[idx3];
               a[r][c + 3] = t[idx3 + 1];
               a[r][c + 4] = t[idx4];
               a[r][c + 5] = t[idx4 + 1];
               a[r][c + 6] = t[idx5];
               a[r][c + 7] = t[idx5 + 1];
            }
         }
      } else if (this.columns == 4) {
         for(int r = 0; r < this.rows; ++r) {
            int idx2 = 2 * r;
            int idx3 = 2 * this.rows + 2 * r;
            t[idx2] = a[r][0];
            t[idx2 + 1] = a[r][1];
            t[idx3] = a[r][2];
            t[idx3 + 1] = a[r][3];
         }

         this.fftRows.complexInverse(t, 0, scale);
         this.fftRows.complexInverse(t, 2 * this.rows, scale);

         for(int r = 0; r < this.rows; ++r) {
            int idx2 = 2 * r;
            int idx3 = 2 * this.rows + 2 * r;
            a[r][0] = t[idx2];
            a[r][1] = t[idx2 + 1];
            a[r][2] = t[idx3];
            a[r][3] = t[idx3 + 1];
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            int idx2 = 2 * r;
            t[idx2] = a[r][0];
            t[idx2 + 1] = a[r][1];
         }

         this.fftRows.complexInverse(t, 0, scale);

         for(int r = 0; r < this.rows; ++r) {
            int idx2 = 2 * r;
            a[r][0] = t[idx2];
            a[r][1] = t[idx2 + 1];
         }
      }

   }

   private void xdft2d0_subth1(final int icr, final int isgn, final double[] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (icr == 0) {
                  if (isgn == -1) {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a, r * DoubleFFT_2D.this.columns);
                     }
                  } else {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a, r * DoubleFFT_2D.this.columns, scale);
                     }
                  }
               } else if (isgn == 1) {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realForward(a, r * DoubleFFT_2D.this.columns);
                  }
               } else {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realInverse(a, r * DoubleFFT_2D.this.columns, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft2d0_subth1(final long icr, final int isgn, final DoubleLargeArray a, final boolean scale) {
      final int nthreads = (int)((long)ConcurrencyUtils.getNumberOfThreads() > this.rowsl ? this.rowsl : (long)ConcurrencyUtils.getNumberOfThreads());
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (icr == 0L) {
                  if (isgn == -1) {
                     for(long r = (long)i; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a, r * DoubleFFT_2D.this.columnsl);
                     }
                  } else {
                     for(long r = (long)i; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a, r * DoubleFFT_2D.this.columnsl, scale);
                     }
                  }
               } else if (isgn == 1) {
                  for(long r = (long)i; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                     DoubleFFT_2D.this.fftColumns.realForward(a, r * DoubleFFT_2D.this.columnsl);
                  }
               } else {
                  for(long r = (long)i; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                     DoubleFFT_2D.this.fftColumns.realInverse(a, r * DoubleFFT_2D.this.columnsl, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft2d0_subth2(final int icr, final int isgn, final double[] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (icr == 0) {
                  if (isgn == -1) {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a, r * DoubleFFT_2D.this.columns);
                     }
                  } else {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a, r * DoubleFFT_2D.this.columns, scale);
                     }
                  }
               } else if (isgn == 1) {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realForward(a, r * DoubleFFT_2D.this.columns);
                  }
               } else {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realInverse2(a, r * DoubleFFT_2D.this.columns, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft2d0_subth2(final long icr, final int isgn, final DoubleLargeArray a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(int i = 0; i < nthreads; ++i) {
         final long n0 = (long)i;
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (icr == 0L) {
                  if (isgn == -1) {
                     for(long r = n0; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a, r * DoubleFFT_2D.this.columnsl);
                     }
                  } else {
                     for(long r = n0; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a, r * DoubleFFT_2D.this.columnsl, scale);
                     }
                  }
               } else if (isgn == 1) {
                  for(long r = n0; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                     DoubleFFT_2D.this.fftColumns.realForward(a, r * DoubleFFT_2D.this.columnsl);
                  }
               } else {
                  for(long r = n0; r < DoubleFFT_2D.this.rowsl; r += (long)nthreads) {
                     DoubleFFT_2D.this.fftColumns.realInverse2(a, r * DoubleFFT_2D.this.columnsl, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft2d0_subth1(final int icr, final int isgn, final double[][] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (icr == 0) {
                  if (isgn == -1) {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a[r]);
                     }
                  } else {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a[r], scale);
                     }
                  }
               } else if (isgn == 1) {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realForward(a[r]);
                  }
               } else {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realInverse(a[r], scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft2d0_subth2(final int icr, final int isgn, final double[][] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (icr == 0) {
                  if (isgn == -1) {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexForward(a[r]);
                     }
                  } else {
                     for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                        DoubleFFT_2D.this.fftColumns.complexInverse(a[r], scale);
                     }
                  }
               } else if (isgn == 1) {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realForward(a[r]);
                  }
               } else {
                  for(int r = i; r < DoubleFFT_2D.this.rows; r += nthreads) {
                     DoubleFFT_2D.this.fftColumns.realInverse2(a[r], 0, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void cdft2d_subth(final int isgn, final double[] a, final boolean scale) {
      int nthread = FastMath.min(this.columns / 2, ConcurrencyUtils.getNumberOfThreads());
      int nt = 8 * this.rows;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthread];
      final int nthreads = nthread;

      for(final int i = 0; i < nthread; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  if (DoubleFFT_2D.this.columns > 4 * nthreads) {
                     for(int c = 8 * i; c < DoubleFFT_2D.this.columns; c += 8 * nthreads) {
                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx1 = r * DoubleFFT_2D.this.columns + c;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                           int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                           int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                           t[idx3] = a[idx1 + 2];
                           t[idx3 + 1] = a[idx1 + 3];
                           t[idx4] = a[idx1 + 4];
                           t[idx4 + 1] = a[idx1 + 5];
                           t[idx5] = a[idx1 + 6];
                           t[idx5 + 1] = a[idx1 + 7];
                        }

                        DoubleFFT_2D.this.fftRows.complexForward(t, 0);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 2 * DoubleFFT_2D.this.rows);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 4 * DoubleFFT_2D.this.rows);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 6 * DoubleFFT_2D.this.rows);

                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx1 = r * DoubleFFT_2D.this.columns + c;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                           int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                           int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                           a[idx1 + 2] = t[idx3];
                           a[idx1 + 3] = t[idx3 + 1];
                           a[idx1 + 4] = t[idx4];
                           a[idx1 + 5] = t[idx4 + 1];
                           a[idx1 + 6] = t[idx5];
                           a[idx1 + 7] = t[idx5 + 1];
                        }
                     }
                  } else if (DoubleFFT_2D.this.columns == 4 * nthreads) {
                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = r * DoubleFFT_2D.this.columns + 4 * i;
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        t[idx2] = a[idx1];
                        t[idx2 + 1] = a[idx1 + 1];
                        t[idx3] = a[idx1 + 2];
                        t[idx3 + 1] = a[idx1 + 3];
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(t, 0);
                     DoubleFFT_2D.this.fftRows.complexForward(t, 2 * DoubleFFT_2D.this.rows);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = r * DoubleFFT_2D.this.columns + 4 * i;
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        a[idx1] = t[idx2];
                        a[idx1 + 1] = t[idx2 + 1];
                        a[idx1 + 2] = t[idx3];
                        a[idx1 + 3] = t[idx3 + 1];
                     }
                  } else if (DoubleFFT_2D.this.columns == 2 * nthreads) {
                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = r * DoubleFFT_2D.this.columns + 2 * i;
                        int idx2 = 2 * r;
                        t[idx2] = a[idx1];
                        t[idx2 + 1] = a[idx1 + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(t, 0);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = r * DoubleFFT_2D.this.columns + 2 * i;
                        int idx2 = 2 * r;
                        a[idx1] = t[idx2];
                        a[idx1 + 1] = t[idx2 + 1];
                     }
                  }
               } else if (DoubleFFT_2D.this.columns > 4 * nthreads) {
                  for(int c = 8 * i; c < DoubleFFT_2D.this.columns; c += 8 * nthreads) {
                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = r * DoubleFFT_2D.this.columns + c;
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                        int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                        t[idx2] = a[idx1];
                        t[idx2 + 1] = a[idx1 + 1];
                        t[idx3] = a[idx1 + 2];
                        t[idx3 + 1] = a[idx1 + 3];
                        t[idx4] = a[idx1 + 4];
                        t[idx4 + 1] = a[idx1 + 5];
                        t[idx5] = a[idx1 + 6];
                        t[idx5 + 1] = a[idx1 + 7];
                     }

                     DoubleFFT_2D.this.fftRows.complexInverse(t, 0, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 2 * DoubleFFT_2D.this.rows, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 4 * DoubleFFT_2D.this.rows, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 6 * DoubleFFT_2D.this.rows, scale);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx1 = r * DoubleFFT_2D.this.columns + c;
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                        int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                        a[idx1] = t[idx2];
                        a[idx1 + 1] = t[idx2 + 1];
                        a[idx1 + 2] = t[idx3];
                        a[idx1 + 3] = t[idx3 + 1];
                        a[idx1 + 4] = t[idx4];
                        a[idx1 + 5] = t[idx4 + 1];
                        a[idx1 + 6] = t[idx5];
                        a[idx1 + 7] = t[idx5 + 1];
                     }
                  }
               } else if (DoubleFFT_2D.this.columns == 4 * nthreads) {
                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx1 = r * DoubleFFT_2D.this.columns + 4 * i;
                     int idx2 = 2 * r;
                     int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                     t[idx2] = a[idx1];
                     t[idx2 + 1] = a[idx1 + 1];
                     t[idx3] = a[idx1 + 2];
                     t[idx3 + 1] = a[idx1 + 3];
                  }

                  DoubleFFT_2D.this.fftRows.complexInverse(t, 0, scale);
                  DoubleFFT_2D.this.fftRows.complexInverse(t, 2 * DoubleFFT_2D.this.rows, scale);

                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx1 = r * DoubleFFT_2D.this.columns + 4 * i;
                     int idx2 = 2 * r;
                     int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                     a[idx1] = t[idx2];
                     a[idx1 + 1] = t[idx2 + 1];
                     a[idx1 + 2] = t[idx3];
                     a[idx1 + 3] = t[idx3 + 1];
                  }
               } else if (DoubleFFT_2D.this.columns == 2 * nthreads) {
                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx1 = r * DoubleFFT_2D.this.columns + 2 * i;
                     int idx2 = 2 * r;
                     t[idx2] = a[idx1];
                     t[idx2 + 1] = a[idx1 + 1];
                  }

                  DoubleFFT_2D.this.fftRows.complexInverse(t, 0, scale);

                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx1 = r * DoubleFFT_2D.this.columns + 2 * i;
                     int idx2 = 2 * r;
                     a[idx1] = t[idx2];
                     a[idx1 + 1] = t[idx2 + 1];
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void cdft2d_subth(final int isgn, final DoubleLargeArray a, final boolean scale) {
      int nthread = (int)FastMath.min(this.columnsl / 2L, (long)ConcurrencyUtils.getNumberOfThreads());
      long nt = 8L * this.rowsl;
      if (this.columnsl == 4L) {
         nt >>= 1;
      } else if (this.columnsl < 4L) {
         nt >>= 2;
      }

      final long ntf = nt;
      Future<?>[] futures = new Future[nthread];
      final int nthreads = nthread;

      for(int i = 0; i < nthread; ++i) {
         final long n0 = (long)i;
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               DoubleLargeArray t = new DoubleLargeArray(ntf);
               if (isgn == -1) {
                  if (DoubleFFT_2D.this.columnsl > (long)(4 * nthreads)) {
                     for(long c = 8L * n0; c < DoubleFFT_2D.this.columnsl; c += (long)(8 * nthreads)) {
                        for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                           long idx1 = r * DoubleFFT_2D.this.columnsl + c;
                           long idx2 = 2L * r;
                           long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                           long idx4 = idx3 + 2L * DoubleFFT_2D.this.rowsl;
                           long idx5 = idx4 + 2L * DoubleFFT_2D.this.rowsl;
                           t.setDouble(idx2, a.getDouble(idx1));
                           t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
                           t.setDouble(idx3, a.getDouble(idx1 + 2L));
                           t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
                           t.setDouble(idx4, a.getDouble(idx1 + 4L));
                           t.setDouble(idx4 + 1L, a.getDouble(idx1 + 5L));
                           t.setDouble(idx5, a.getDouble(idx1 + 6L));
                           t.setDouble(idx5 + 1L, a.getDouble(idx1 + 7L));
                        }

                        DoubleFFT_2D.this.fftRows.complexForward(t, 0L);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 2L * DoubleFFT_2D.this.rowsl);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 4L * DoubleFFT_2D.this.rowsl);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 6L * DoubleFFT_2D.this.rowsl);

                        for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                           long idx1 = r * DoubleFFT_2D.this.columnsl + c;
                           long idx2 = 2L * r;
                           long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                           long idx4 = idx3 + 2L * DoubleFFT_2D.this.rowsl;
                           long idx5 = idx4 + 2L * DoubleFFT_2D.this.rowsl;
                           a.setDouble(idx1, t.getDouble(idx2));
                           a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
                           a.setDouble(idx1 + 2L, t.getDouble(idx3));
                           a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
                           a.setDouble(idx1 + 4L, t.getDouble(idx4));
                           a.setDouble(idx1 + 5L, t.getDouble(idx4 + 1L));
                           a.setDouble(idx1 + 6L, t.getDouble(idx5));
                           a.setDouble(idx1 + 7L, t.getDouble(idx5 + 1L));
                        }
                     }
                  } else if (DoubleFFT_2D.this.columnsl == (long)(4 * nthreads)) {
                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = r * DoubleFFT_2D.this.columnsl + 4L * n0;
                        long idx2 = 2L * r;
                        long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                        t.setDouble(idx2, a.getDouble(idx1));
                        t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
                        t.setDouble(idx3, a.getDouble(idx1 + 2L));
                        t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(t, 0L);
                     DoubleFFT_2D.this.fftRows.complexForward(t, 2L * DoubleFFT_2D.this.rowsl);

                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = r * DoubleFFT_2D.this.columnsl + 4L * n0;
                        long idx2 = 2L * r;
                        long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                        a.setDouble(idx1, t.getDouble(idx2));
                        a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
                        a.setDouble(idx1 + 2L, t.getDouble(idx3));
                        a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
                     }
                  } else if (DoubleFFT_2D.this.columnsl == (long)(2 * nthreads)) {
                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = r * DoubleFFT_2D.this.columnsl + 2L * n0;
                        long idx2 = 2L * r;
                        t.setDouble(idx2, a.getDouble(idx1));
                        t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(t, 0L);

                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = r * DoubleFFT_2D.this.columnsl + 2L * n0;
                        long idx2 = 2L * r;
                        a.setDouble(idx1, t.getDouble(idx2));
                        a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
                     }
                  }
               } else if (DoubleFFT_2D.this.columnsl > (long)(4 * nthreads)) {
                  for(long c = 8L * n0; c < DoubleFFT_2D.this.columnsl; c += (long)(8 * nthreads)) {
                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = r * DoubleFFT_2D.this.columnsl + c;
                        long idx2 = 2L * r;
                        long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                        long idx4 = idx3 + 2L * DoubleFFT_2D.this.rowsl;
                        long idx5 = idx4 + 2L * DoubleFFT_2D.this.rowsl;
                        t.setDouble(idx2, a.getDouble(idx1));
                        t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
                        t.setDouble(idx3, a.getDouble(idx1 + 2L));
                        t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
                        t.setDouble(idx4, a.getDouble(idx1 + 4L));
                        t.setDouble(idx4 + 1L, a.getDouble(idx1 + 5L));
                        t.setDouble(idx5, a.getDouble(idx1 + 6L));
                        t.setDouble(idx5 + 1L, a.getDouble(idx1 + 7L));
                     }

                     DoubleFFT_2D.this.fftRows.complexInverse(t, 0L, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 2L * DoubleFFT_2D.this.rowsl, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 4L * DoubleFFT_2D.this.rowsl, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 6L * DoubleFFT_2D.this.rowsl, scale);

                     for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                        long idx1 = r * DoubleFFT_2D.this.columnsl + c;
                        long idx2 = 2L * r;
                        long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                        long idx4 = idx3 + 2L * DoubleFFT_2D.this.rowsl;
                        long idx5 = idx4 + 2L * DoubleFFT_2D.this.rowsl;
                        a.setDouble(idx1, t.getDouble(idx2));
                        a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
                        a.setDouble(idx1 + 2L, t.getDouble(idx3));
                        a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
                        a.setDouble(idx1 + 4L, t.getDouble(idx4));
                        a.setDouble(idx1 + 5L, t.getDouble(idx4 + 1L));
                        a.setDouble(idx1 + 6L, t.getDouble(idx5));
                        a.setDouble(idx1 + 7L, t.getDouble(idx5 + 1L));
                     }
                  }
               } else if (DoubleFFT_2D.this.columnsl == (long)(4 * nthreads)) {
                  for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                     long idx1 = r * DoubleFFT_2D.this.columnsl + 4L * n0;
                     long idx2 = 2L * r;
                     long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                     t.setDouble(idx2, a.getDouble(idx1));
                     t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
                     t.setDouble(idx3, a.getDouble(idx1 + 2L));
                     t.setDouble(idx3 + 1L, a.getDouble(idx1 + 3L));
                  }

                  DoubleFFT_2D.this.fftRows.complexInverse(t, 0L, scale);
                  DoubleFFT_2D.this.fftRows.complexInverse(t, 2L * DoubleFFT_2D.this.rowsl, scale);

                  for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                     long idx1 = r * DoubleFFT_2D.this.columnsl + 4L * n0;
                     long idx2 = 2L * r;
                     long idx3 = 2L * DoubleFFT_2D.this.rowsl + 2L * r;
                     a.setDouble(idx1, t.getDouble(idx2));
                     a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
                     a.setDouble(idx1 + 2L, t.getDouble(idx3));
                     a.setDouble(idx1 + 3L, t.getDouble(idx3 + 1L));
                  }
               } else if (DoubleFFT_2D.this.columnsl == (long)(2 * nthreads)) {
                  for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                     long idx1 = r * DoubleFFT_2D.this.columnsl + 2L * n0;
                     long idx2 = 2L * r;
                     t.setDouble(idx2, a.getDouble(idx1));
                     t.setDouble(idx2 + 1L, a.getDouble(idx1 + 1L));
                  }

                  DoubleFFT_2D.this.fftRows.complexInverse(t, 0L, scale);

                  for(long r = 0L; r < DoubleFFT_2D.this.rowsl; ++r) {
                     long idx1 = r * DoubleFFT_2D.this.columnsl + 2L * n0;
                     long idx2 = 2L * r;
                     a.setDouble(idx1, t.getDouble(idx2));
                     a.setDouble(idx1 + 1L, t.getDouble(idx2 + 1L));
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void cdft2d_subth(final int isgn, final double[][] a, final boolean scale) {
      int nthread = FastMath.min(this.columns / 2, ConcurrencyUtils.getNumberOfThreads());
      int nt = 8 * this.rows;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthread];
      final int nthreads = nthread;

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  if (DoubleFFT_2D.this.columns > 4 * nthreads) {
                     for(int c = 8 * i; c < DoubleFFT_2D.this.columns; c += 8 * nthreads) {
                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                           int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                           int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                           t[idx2] = a[r][c];
                           t[idx2 + 1] = a[r][c + 1];
                           t[idx3] = a[r][c + 2];
                           t[idx3 + 1] = a[r][c + 3];
                           t[idx4] = a[r][c + 4];
                           t[idx4 + 1] = a[r][c + 5];
                           t[idx5] = a[r][c + 6];
                           t[idx5 + 1] = a[r][c + 7];
                        }

                        DoubleFFT_2D.this.fftRows.complexForward(t, 0);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 2 * DoubleFFT_2D.this.rows);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 4 * DoubleFFT_2D.this.rows);
                        DoubleFFT_2D.this.fftRows.complexForward(t, 6 * DoubleFFT_2D.this.rows);

                        for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                           int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                           int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                           a[r][c] = t[idx2];
                           a[r][c + 1] = t[idx2 + 1];
                           a[r][c + 2] = t[idx3];
                           a[r][c + 3] = t[idx3 + 1];
                           a[r][c + 4] = t[idx4];
                           a[r][c + 5] = t[idx4 + 1];
                           a[r][c + 6] = t[idx5];
                           a[r][c + 7] = t[idx5 + 1];
                        }
                     }
                  } else if (DoubleFFT_2D.this.columns == 4 * nthreads) {
                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        t[idx2] = a[r][4 * i];
                        t[idx2 + 1] = a[r][4 * i + 1];
                        t[idx3] = a[r][4 * i + 2];
                        t[idx3 + 1] = a[r][4 * i + 3];
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(t, 0);
                     DoubleFFT_2D.this.fftRows.complexForward(t, 2 * DoubleFFT_2D.this.rows);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        a[r][4 * i] = t[idx2];
                        a[r][4 * i + 1] = t[idx2 + 1];
                        a[r][4 * i + 2] = t[idx3];
                        a[r][4 * i + 3] = t[idx3 + 1];
                     }
                  } else if (DoubleFFT_2D.this.columns == 2 * nthreads) {
                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        t[idx2] = a[r][2 * i];
                        t[idx2 + 1] = a[r][2 * i + 1];
                     }

                     DoubleFFT_2D.this.fftRows.complexForward(t, 0);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        a[r][2 * i] = t[idx2];
                        a[r][2 * i + 1] = t[idx2 + 1];
                     }
                  }
               } else if (DoubleFFT_2D.this.columns > 4 * nthreads) {
                  for(int c = 8 * i; c < DoubleFFT_2D.this.columns; c += 8 * nthreads) {
                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                        int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                        t[idx2] = a[r][c];
                        t[idx2 + 1] = a[r][c + 1];
                        t[idx3] = a[r][c + 2];
                        t[idx3 + 1] = a[r][c + 3];
                        t[idx4] = a[r][c + 4];
                        t[idx4 + 1] = a[r][c + 5];
                        t[idx5] = a[r][c + 6];
                        t[idx5 + 1] = a[r][c + 7];
                     }

                     DoubleFFT_2D.this.fftRows.complexInverse(t, 0, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 2 * DoubleFFT_2D.this.rows, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 4 * DoubleFFT_2D.this.rows, scale);
                     DoubleFFT_2D.this.fftRows.complexInverse(t, 6 * DoubleFFT_2D.this.rows, scale);

                     for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                        int idx2 = 2 * r;
                        int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                        int idx4 = idx3 + 2 * DoubleFFT_2D.this.rows;
                        int idx5 = idx4 + 2 * DoubleFFT_2D.this.rows;
                        a[r][c] = t[idx2];
                        a[r][c + 1] = t[idx2 + 1];
                        a[r][c + 2] = t[idx3];
                        a[r][c + 3] = t[idx3 + 1];
                        a[r][c + 4] = t[idx4];
                        a[r][c + 5] = t[idx4 + 1];
                        a[r][c + 6] = t[idx5];
                        a[r][c + 7] = t[idx5 + 1];
                     }
                  }
               } else if (DoubleFFT_2D.this.columns == 4 * nthreads) {
                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx2 = 2 * r;
                     int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                     t[idx2] = a[r][4 * i];
                     t[idx2 + 1] = a[r][4 * i + 1];
                     t[idx3] = a[r][4 * i + 2];
                     t[idx3 + 1] = a[r][4 * i + 3];
                  }

                  DoubleFFT_2D.this.fftRows.complexInverse(t, 0, scale);
                  DoubleFFT_2D.this.fftRows.complexInverse(t, 2 * DoubleFFT_2D.this.rows, scale);

                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx2 = 2 * r;
                     int idx3 = 2 * DoubleFFT_2D.this.rows + 2 * r;
                     a[r][4 * i] = t[idx2];
                     a[r][4 * i + 1] = t[idx2 + 1];
                     a[r][4 * i + 2] = t[idx3];
                     a[r][4 * i + 3] = t[idx3 + 1];
                  }
               } else if (DoubleFFT_2D.this.columns == 2 * nthreads) {
                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx2 = 2 * r;
                     t[idx2] = a[r][2 * i];
                     t[idx2 + 1] = a[r][2 * i + 1];
                  }

                  DoubleFFT_2D.this.fftRows.complexInverse(t, 0, scale);

                  for(int r = 0; r < DoubleFFT_2D.this.rows; ++r) {
                     int idx2 = 2 * r;
                     a[r][2 * i] = t[idx2];
                     a[r][2 * i + 1] = t[idx2 + 1];
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void fillSymmetric(final double[] a) {
      int twon2 = 2 * this.columns;
      int n1d2 = this.rows / 2;

      for(int r = this.rows - 1; r >= 1; --r) {
         int idx1 = r * this.columns;
         int idx2 = 2 * idx1;

         for(int c = 0; c < this.columns; c += 2) {
            a[idx2 + c] = a[idx1 + c];
            a[idx1 + c] = (double)0.0F;
            a[idx2 + c + 1] = a[idx1 + c + 1];
            a[idx1 + c + 1] = (double)0.0F;
         }
      }

      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && n1d2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int l1k = n1d2 / nthreads;
         final int newn2 = 2 * this.columns;

         for(int i = 0; i < nthreads; ++i) {
            final int l1offa;
            if (i == 0) {
               l1offa = i * l1k + 1;
            } else {
               l1offa = i * l1k;
            }

            final int l1stopa = i * l1k + l1k;
            final int l2offa = i * l1k;
            final int l2stopa;
            if (i == nthreads - 1) {
               l2stopa = i * l1k + l1k + 1;
            } else {
               l2stopa = i * l1k + l1k;
            }

            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = l1offa; r < l1stopa; ++r) {
                     int idx1 = r * newn2;
                     int idx2 = (DoubleFFT_2D.this.rows - r) * newn2;
                     int idx3 = idx1 + DoubleFFT_2D.this.columns;
                     a[idx3] = a[idx2 + 1];
                     a[idx3 + 1] = -a[idx2];
                  }

                  for(int r = l1offa; r < l1stopa; ++r) {
                     int idx1 = r * newn2;
                     int idx3 = (DoubleFFT_2D.this.rows - r + 1) * newn2;

                     for(int c = DoubleFFT_2D.this.columns + 2; c < newn2; c += 2) {
                        int idx2 = idx3 - c;
                        int idx4 = idx1 + c;
                        a[idx4] = a[idx2];
                        a[idx4 + 1] = -a[idx2 + 1];
                     }
                  }

                  for(int r = l2offa; r < l2stopa; ++r) {
                     int idx3 = (DoubleFFT_2D.this.rows - r) % DoubleFFT_2D.this.rows * newn2;
                     int idx4 = r * newn2;

                     for(int c = 0; c < newn2; c += 2) {
                        int idx1 = idx3 + (newn2 - c) % newn2;
                        int idx2 = idx4 + c;
                        a[idx1] = a[idx2];
                        a[idx1 + 1] = -a[idx2 + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 1; r < n1d2; ++r) {
            int idx2 = r * twon2;
            int idx3 = (this.rows - r) * twon2;
            a[idx2 + this.columns] = a[idx3 + 1];
            a[idx2 + this.columns + 1] = -a[idx3];
         }

         for(int r = 1; r < n1d2; ++r) {
            int idx2 = r * twon2;
            int idx3 = (this.rows - r + 1) * twon2;

            for(int c = this.columns + 2; c < twon2; c += 2) {
               a[idx2 + c] = a[idx3 - c];
               a[idx2 + c + 1] = -a[idx3 - c + 1];
            }
         }

         for(int r = 0; r <= this.rows / 2; ++r) {
            int idx1 = r * twon2;
            int idx4 = (this.rows - r) % this.rows * twon2;

            for(int c = 0; c < twon2; c += 2) {
               int idx2 = idx1 + c;
               int idx3 = idx4 + (twon2 - c) % twon2;
               a[idx3] = a[idx2];
               a[idx3 + 1] = -a[idx2 + 1];
            }
         }
      }

      a[this.columns] = -a[1];
      a[1] = (double)0.0F;
      int idx1 = n1d2 * twon2;
      a[idx1 + this.columns] = -a[idx1 + 1];
      a[idx1 + 1] = (double)0.0F;
      a[idx1 + this.columns + 1] = (double)0.0F;
   }

   private void fillSymmetric(final DoubleLargeArray a) {
      long twon2 = 2L * this.columnsl;
      long n1d2 = this.rowsl / 2L;

      for(long r = this.rowsl - 1L; r >= 1L; --r) {
         long idx1 = r * this.columnsl;
         long idx2 = 2L * idx1;

         for(long c = 0L; c < this.columnsl; c += 2L) {
            a.setDouble(idx2 + c, a.getDouble(idx1 + c));
            a.setDouble(idx1 + c, (double)0.0F);
            a.setDouble(idx2 + c + 1L, a.getDouble(idx1 + c + 1L));
            a.setDouble(idx1 + c + 1L, (double)0.0F);
         }
      }

      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && n1d2 >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long l1k = n1d2 / (long)nthreads;
         final long newn2 = 2L * this.columnsl;

         for(int i = 0; i < nthreads; ++i) {
            final long l1offa;
            if (i == 0) {
               l1offa = (long)i * l1k + 1L;
            } else {
               l1offa = (long)i * l1k;
            }

            final long l1stopa = (long)i * l1k + l1k;
            final long l2offa = (long)i * l1k;
            final long l2stopa;
            if (i == nthreads - 1) {
               l2stopa = (long)i * l1k + l1k + 1L;
            } else {
               l2stopa = (long)i * l1k + l1k;
            }

            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long r = l1offa; r < l1stopa; ++r) {
                     long idx1 = r * newn2;
                     long idx2 = (DoubleFFT_2D.this.rowsl - r) * newn2;
                     long idx3 = idx1 + DoubleFFT_2D.this.columnsl;
                     a.setDouble(idx3, a.getDouble(idx2 + 1L));
                     a.setDouble(idx3 + 1L, -a.getDouble(idx2));
                  }

                  for(long r = l1offa; r < l1stopa; ++r) {
                     long idx1 = r * newn2;
                     long idx3 = (DoubleFFT_2D.this.rowsl - r + 1L) * newn2;

                     for(long c = DoubleFFT_2D.this.columnsl + 2L; c < newn2; c += 2L) {
                        long idx2 = idx3 - c;
                        long idx4 = idx1 + c;
                        a.setDouble(idx4, a.getDouble(idx2));
                        a.setDouble(idx4 + 1L, -a.getDouble(idx2 + 1L));
                     }
                  }

                  for(long r = l2offa; r < l2stopa; ++r) {
                     long idx3 = (DoubleFFT_2D.this.rowsl - r) % DoubleFFT_2D.this.rowsl * newn2;
                     long idx4 = r * newn2;

                     for(long c = 0L; c < newn2; c += 2L) {
                        long idx1 = idx3 + (newn2 - c) % newn2;
                        long idx2 = idx4 + c;
                        a.setDouble(idx1, a.getDouble(idx2));
                        a.setDouble(idx1 + 1L, -a.getDouble(idx2 + 1L));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long r = 1L; r < n1d2; ++r) {
            long idx2 = r * twon2;
            long idx3 = (this.rowsl - r) * twon2;
            a.setDouble(idx2 + this.columnsl, a.getDouble(idx3 + 1L));
            a.setDouble(idx2 + this.columnsl + 1L, -a.getDouble(idx3));
         }

         for(long r = 1L; r < n1d2; ++r) {
            long idx2 = r * twon2;
            long idx3 = (this.rowsl - r + 1L) * twon2;

            for(long c = this.columnsl + 2L; c < twon2; c += 2L) {
               a.setDouble(idx2 + c, a.getDouble(idx3 - c));
               a.setDouble(idx2 + c + 1L, -a.getDouble(idx3 - c + 1L));
            }
         }

         for(long r = 0L; r <= this.rowsl / 2L; ++r) {
            long idx1 = r * twon2;
            long idx4 = (this.rowsl - r) % this.rowsl * twon2;

            for(long c = 0L; c < twon2; c += 2L) {
               long idx2 = idx1 + c;
               long idx3 = idx4 + (twon2 - c) % twon2;
               a.setDouble(idx3, a.getDouble(idx2));
               a.setDouble(idx3 + 1L, -a.getDouble(idx2 + 1L));
            }
         }
      }

      a.setDouble(this.columnsl, -a.getDouble(1L));
      a.setDouble(1L, (double)0.0F);
      long idx1 = n1d2 * twon2;
      a.setDouble(idx1 + this.columnsl, -a.getDouble(idx1 + 1L));
      a.setDouble(idx1 + 1L, (double)0.0F);
      a.setDouble(idx1 + this.columnsl + 1L, (double)0.0F);
   }

   private void fillSymmetric(final double[][] a) {
      final int newn2 = 2 * this.columns;
      int n1d2 = this.rows / 2;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && n1d2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int l1k = n1d2 / nthreads;

         for(int i = 0; i < nthreads; ++i) {
            final int l1offa;
            if (i == 0) {
               l1offa = i * l1k + 1;
            } else {
               l1offa = i * l1k;
            }

            final int l1stopa = i * l1k + l1k;
            final int l2offa = i * l1k;
            final int l2stopa;
            if (i == nthreads - 1) {
               l2stopa = i * l1k + l1k + 1;
            } else {
               l2stopa = i * l1k + l1k;
            }

            futures[i] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int r = l1offa; r < l1stopa; ++r) {
                     int idx1 = DoubleFFT_2D.this.rows - r;
                     a[r][DoubleFFT_2D.this.columns] = a[idx1][1];
                     a[r][DoubleFFT_2D.this.columns + 1] = -a[idx1][0];
                  }

                  for(int r = l1offa; r < l1stopa; ++r) {
                     int idx1 = DoubleFFT_2D.this.rows - r;

                     for(int c = DoubleFFT_2D.this.columns + 2; c < newn2; c += 2) {
                        int idx2 = newn2 - c;
                        a[r][c] = a[idx1][idx2];
                        a[r][c + 1] = -a[idx1][idx2 + 1];
                     }
                  }

                  for(int r = l2offa; r < l2stopa; ++r) {
                     int idx1 = (DoubleFFT_2D.this.rows - r) % DoubleFFT_2D.this.rows;

                     for(int c = 0; c < newn2; c += 2) {
                        int idx2 = (newn2 - c) % newn2;
                        a[idx1][idx2] = a[r][c];
                        a[idx1][idx2 + 1] = -a[r][c + 1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int r = 1; r < n1d2; ++r) {
            int idx1 = this.rows - r;
            a[r][this.columns] = a[idx1][1];
            a[r][this.columns + 1] = -a[idx1][0];
         }

         for(int r = 1; r < n1d2; ++r) {
            int idx1 = this.rows - r;

            for(int c = this.columns + 2; c < newn2; c += 2) {
               int idx2 = newn2 - c;
               a[r][c] = a[idx1][idx2];
               a[r][c + 1] = -a[idx1][idx2 + 1];
            }
         }

         for(int r = 0; r <= this.rows / 2; ++r) {
            int idx1 = (this.rows - r) % this.rows;

            for(int c = 0; c < newn2; c += 2) {
               int idx2 = (newn2 - c) % newn2;
               a[idx1][idx2] = a[r][c];
               a[idx1][idx2 + 1] = -a[r][c + 1];
            }
         }
      }

      a[0][this.columns] = -a[0][1];
      a[0][1] = (double)0.0F;
      a[n1d2][this.columns] = -a[n1d2][1];
      a[n1d2][1] = (double)0.0F;
      a[n1d2][this.columns + 1] = (double)0.0F;
   }
}
