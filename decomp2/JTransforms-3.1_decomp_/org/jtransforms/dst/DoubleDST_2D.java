package org.jtransforms.dst;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.DoubleLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class DoubleDST_2D {
   private int rows;
   private int columns;
   private long rowsl;
   private long columnsl;
   private DoubleDST_1D dstColumns;
   private DoubleDST_1D dstRows;
   private boolean isPowerOfTwo = false;
   private boolean useThreads = false;

   public DoubleDST_2D(long rows, long columns) {
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

         CommonUtils.setUseLargeArrays(rows * columns > (long)LargeArray.getMaxSizeOf32bitArray());
         this.dstRows = new DoubleDST_1D(rows);
         if (rows == columns) {
            this.dstColumns = this.dstRows;
         } else {
            this.dstColumns = new DoubleDST_1D(columns);
         }

      } else {
         throw new IllegalArgumentException("rows and columns must be greater than 1");
      }
   }

   public void forward(final double[] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(-1, (double[])a, scale);
            this.ddxt2d0_subth(-1, (double[])a, scale);
         } else {
            this.ddxt2d_sub(-1, (double[])a, scale);

            for(int i = 0; i < this.rows; ++i) {
               this.dstColumns.forward(a, i * this.columns, scale);
            }
         }
      } else if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleDST_2D.this.dstColumns.forward(a, i * DoubleDST_2D.this.columns, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columns / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = l * p;
            final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[DoubleDST_2D.this.rows];

                  for(int c = firstColumn; c < lastColumn; ++c) {
                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        temp[r] = a[r * DoubleDST_2D.this.columns + c];
                     }

                     DoubleDST_2D.this.dstRows.forward(temp, scale);

                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        a[r * DoubleDST_2D.this.columns + c] = temp[r];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int i = 0; i < this.rows; ++i) {
            this.dstColumns.forward(a, i * this.columns, scale);
         }

         double[] temp = new double[this.rows];

         for(int c = 0; c < this.columns; ++c) {
            for(int r = 0; r < this.rows; ++r) {
               temp[r] = a[r * this.columns + c];
            }

            this.dstRows.forward(temp, scale);

            for(int r = 0; r < this.rows; ++r) {
               a[r * this.columns + c] = temp[r];
            }
         }
      }

   }

   public void forward(final DoubleLargeArray a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(-1, (DoubleLargeArray)a, scale);
            this.ddxt2d0_subth(-1, (DoubleLargeArray)a, scale);
         } else {
            this.ddxt2d_sub(-1, (DoubleLargeArray)a, scale);

            for(long i = 0L; i < this.rowsl; ++i) {
               this.dstColumns.forward(a, i * this.columnsl, scale);
            }
         }
      } else if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstRow; i < lastRow; ++i) {
                     DoubleDST_2D.this.dstColumns.forward(a, i * DoubleDST_2D.this.columnsl, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columnsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstColumn = (long)l * p;
            final long lastColumn = l == nthreads - 1 ? this.columnsl : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(DoubleDST_2D.this.rowsl, false);

                  for(long c = firstColumn; c < lastColumn; ++c) {
                     for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                        temp.setDouble(r, a.getDouble(r * DoubleDST_2D.this.columnsl + c));
                     }

                     DoubleDST_2D.this.dstRows.forward(temp, scale);

                     for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                        a.setDouble(r * DoubleDST_2D.this.columnsl + c, temp.getDouble(r));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long i = 0L; i < this.rowsl; ++i) {
            this.dstColumns.forward(a, i * this.columnsl, scale);
         }

         DoubleLargeArray temp = new DoubleLargeArray(this.rowsl, false);

         for(long c = 0L; c < this.columnsl; ++c) {
            for(long r = 0L; r < this.rowsl; ++r) {
               temp.setDouble(r, a.getDouble(r * this.columnsl + c));
            }

            this.dstRows.forward(temp, scale);

            for(long r = 0L; r < this.rowsl; ++r) {
               a.setDouble(r * this.columnsl + c, temp.getDouble(r));
            }
         }
      }

   }

   public void forward(final double[][] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(-1, (double[][])a, scale);
            this.ddxt2d0_subth(-1, (double[][])a, scale);
         } else {
            this.ddxt2d_sub(-1, (double[][])a, scale);

            for(int i = 0; i < this.rows; ++i) {
               this.dstColumns.forward(a[i], scale);
            }
         }
      } else if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleDST_2D.this.dstColumns.forward(a[i], scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columns / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = l * p;
            final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[DoubleDST_2D.this.rows];

                  for(int c = firstColumn; c < lastColumn; ++c) {
                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        temp[r] = a[r][c];
                     }

                     DoubleDST_2D.this.dstRows.forward(temp, scale);

                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        a[r][c] = temp[r];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int i = 0; i < this.rows; ++i) {
            this.dstColumns.forward(a[i], scale);
         }

         double[] temp = new double[this.rows];

         for(int c = 0; c < this.columns; ++c) {
            for(int r = 0; r < this.rows; ++r) {
               temp[r] = a[r][c];
            }

            this.dstRows.forward(temp, scale);

            for(int r = 0; r < this.rows; ++r) {
               a[r][c] = temp[r];
            }
         }
      }

   }

   public void inverse(final double[] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(1, (double[])a, scale);
            this.ddxt2d0_subth(1, (double[])a, scale);
         } else {
            this.ddxt2d_sub(1, (double[])a, scale);

            for(int i = 0; i < this.rows; ++i) {
               this.dstColumns.inverse(a, i * this.columns, scale);
            }
         }
      } else if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleDST_2D.this.dstColumns.inverse(a, i * DoubleDST_2D.this.columns, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columns / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = l * p;
            final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[DoubleDST_2D.this.rows];

                  for(int c = firstColumn; c < lastColumn; ++c) {
                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        temp[r] = a[r * DoubleDST_2D.this.columns + c];
                     }

                     DoubleDST_2D.this.dstRows.inverse(temp, scale);

                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        a[r * DoubleDST_2D.this.columns + c] = temp[r];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int i = 0; i < this.rows; ++i) {
            this.dstColumns.inverse(a, i * this.columns, scale);
         }

         double[] temp = new double[this.rows];

         for(int c = 0; c < this.columns; ++c) {
            for(int r = 0; r < this.rows; ++r) {
               temp[r] = a[r * this.columns + c];
            }

            this.dstRows.inverse(temp, scale);

            for(int r = 0; r < this.rows; ++r) {
               a[r * this.columns + c] = temp[r];
            }
         }
      }

   }

   public void inverse(final DoubleLargeArray a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(1, (DoubleLargeArray)a, scale);
            this.ddxt2d0_subth(1, (DoubleLargeArray)a, scale);
         } else {
            this.ddxt2d_sub(1, (DoubleLargeArray)a, scale);

            for(long i = 0L; i < this.rowsl; ++i) {
               this.dstColumns.inverse(a, i * this.columnsl, scale);
            }
         }
      } else if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long i = firstRow; i < lastRow; ++i) {
                     DoubleDST_2D.this.dstColumns.inverse(a, i * DoubleDST_2D.this.columnsl, scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columnsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstColumn = (long)l * p;
            final long lastColumn = l == nthreads - 1 ? this.columnsl : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(DoubleDST_2D.this.rowsl, false);

                  for(long c = firstColumn; c < lastColumn; ++c) {
                     for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                        temp.setDouble(r, a.getDouble(r * DoubleDST_2D.this.columnsl + c));
                     }

                     DoubleDST_2D.this.dstRows.inverse(temp, scale);

                     for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                        a.setDouble(r * DoubleDST_2D.this.columnsl + c, temp.getDouble(r));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long i = 0L; i < this.rowsl; ++i) {
            this.dstColumns.inverse(a, i * this.columnsl, scale);
         }

         DoubleLargeArray temp = new DoubleLargeArray(this.rowsl, false);

         for(long c = 0L; c < this.columnsl; ++c) {
            for(long r = 0L; r < this.rowsl; ++r) {
               temp.setDouble(r, a.getDouble(r * this.columnsl + c));
            }

            this.dstRows.inverse(temp, scale);

            for(long r = 0L; r < this.rowsl; ++r) {
               a.setDouble(r * this.columnsl + c, temp.getDouble(r));
            }
         }
      }

   }

   public void inverse(final double[][] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(1, (double[][])a, scale);
            this.ddxt2d0_subth(1, (double[][])a, scale);
         } else {
            this.ddxt2d_sub(1, (double[][])a, scale);

            for(int i = 0; i < this.rows; ++i) {
               this.dstColumns.inverse(a[i], scale);
            }
         }
      } else if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int i = firstRow; i < lastRow; ++i) {
                     DoubleDST_2D.this.dstColumns.inverse(a[i], scale);
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.columns / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstColumn = l * p;
            final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[DoubleDST_2D.this.rows];

                  for(int c = firstColumn; c < lastColumn; ++c) {
                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        temp[r] = a[r][c];
                     }

                     DoubleDST_2D.this.dstRows.inverse(temp, scale);

                     for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                        a[r][c] = temp[r];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int i = 0; i < this.rows; ++i) {
            this.dstColumns.inverse(a[i], scale);
         }

         double[] temp = new double[this.rows];

         for(int c = 0; c < this.columns; ++c) {
            for(int r = 0; r < this.rows; ++r) {
               temp[r] = a[r][c];
            }

            this.dstRows.inverse(temp, scale);

            for(int r = 0; r < this.rows; ++r) {
               a[r][c] = temp[r];
            }
         }
      }

   }

   private void ddxt2d_subth(final int isgn, final double[] a, final boolean scale) {
      int nthread = FastMath.min(this.columns, ConcurrencyUtils.getNumberOfThreads());
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      } else if (this.columns < 2) {
         nt >>= 2;
      }

      final int ntf = nt;
      final int nthreads = nthread;
      Future<?>[] futures = new Future[nthread];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (DoubleDST_2D.this.columns > 2) {
                  if (isgn == -1) {
                     for(int c = 4 * i; c < DoubleDST_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx1 = r * DoubleDST_2D.this.columns + c;
                           int idx2 = DoubleDST_2D.this.rows + r;
                           t[r] = a[idx1];
                           t[idx2] = a[idx1 + 1];
                           t[idx2 + DoubleDST_2D.this.rows] = a[idx1 + 2];
                           t[idx2 + 2 * DoubleDST_2D.this.rows] = a[idx1 + 3];
                        }

                        DoubleDST_2D.this.dstRows.forward(t, 0, scale);
                        DoubleDST_2D.this.dstRows.forward(t, DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.forward(t, 2 * DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.forward(t, 3 * DoubleDST_2D.this.rows, scale);

                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx1 = r * DoubleDST_2D.this.columns + c;
                           int idx2 = DoubleDST_2D.this.rows + r;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[idx2];
                           a[idx1 + 2] = t[idx2 + DoubleDST_2D.this.rows];
                           a[idx1 + 3] = t[idx2 + 2 * DoubleDST_2D.this.rows];
                        }
                     }
                  } else {
                     for(int c = 4 * i; c < DoubleDST_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx1 = r * DoubleDST_2D.this.columns + c;
                           int idx2 = DoubleDST_2D.this.rows + r;
                           t[r] = a[idx1];
                           t[idx2] = a[idx1 + 1];
                           t[idx2 + DoubleDST_2D.this.rows] = a[idx1 + 2];
                           t[idx2 + 2 * DoubleDST_2D.this.rows] = a[idx1 + 3];
                        }

                        DoubleDST_2D.this.dstRows.inverse(t, 0, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, 2 * DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, 3 * DoubleDST_2D.this.rows, scale);

                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx1 = r * DoubleDST_2D.this.columns + c;
                           int idx2 = DoubleDST_2D.this.rows + r;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[idx2];
                           a[idx1 + 2] = t[idx2 + DoubleDST_2D.this.rows];
                           a[idx1 + 3] = t[idx2 + 2 * DoubleDST_2D.this.rows];
                        }
                     }
                  }
               } else if (DoubleDST_2D.this.columns == 2) {
                  for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                     int idx1 = r * DoubleDST_2D.this.columns + 2 * i;
                     t[r] = a[idx1];
                     t[r + DoubleDST_2D.this.rows] = a[idx1 + 1];
                  }

                  if (isgn == -1) {
                     DoubleDST_2D.this.dstRows.forward(t, 0, scale);
                     DoubleDST_2D.this.dstRows.forward(t, DoubleDST_2D.this.rows, scale);
                  } else {
                     DoubleDST_2D.this.dstRows.inverse(t, 0, scale);
                     DoubleDST_2D.this.dstRows.inverse(t, DoubleDST_2D.this.rows, scale);
                  }

                  for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                     int idx1 = r * DoubleDST_2D.this.columns + 2 * i;
                     a[idx1] = t[r];
                     a[idx1 + 1] = t[r + DoubleDST_2D.this.rows];
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d_subth(final int isgn, final DoubleLargeArray a, final boolean scale) {
      int nthread = (int)FastMath.min(this.columnsl, (long)ConcurrencyUtils.getNumberOfThreads());
      long nt = 4L * this.rowsl;
      if (this.columnsl == 2L) {
         nt >>= 1;
      } else if (this.columnsl < 2L) {
         nt >>= 2;
      }

      final long ntf = nt;
      final int nthreads = nthread;
      Future<?>[] futures = new Future[nthread];

      for(int i = 0; i < nthreads; ++i) {
         final long n0 = (long)i;
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               DoubleLargeArray t = new DoubleLargeArray(ntf);
               if (DoubleDST_2D.this.columnsl > 2L) {
                  if (isgn == -1) {
                     for(long c = 4L * n0; c < DoubleDST_2D.this.columnsl; c += (long)(4 * nthreads)) {
                        for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                           long idx1 = r * DoubleDST_2D.this.columnsl + c;
                           long idx2 = DoubleDST_2D.this.rowsl + r;
                           t.setDouble(r, a.getDouble(idx1));
                           t.setDouble(idx2, a.getDouble(idx1 + 1L));
                           t.setDouble(idx2 + DoubleDST_2D.this.rowsl, a.getDouble(idx1 + 2L));
                           t.setDouble(idx2 + 2L * DoubleDST_2D.this.rowsl, a.getDouble(idx1 + 3L));
                        }

                        DoubleDST_2D.this.dstRows.forward(t, 0L, scale);
                        DoubleDST_2D.this.dstRows.forward(t, DoubleDST_2D.this.rowsl, scale);
                        DoubleDST_2D.this.dstRows.forward(t, 2L * DoubleDST_2D.this.rowsl, scale);
                        DoubleDST_2D.this.dstRows.forward(t, 3L * DoubleDST_2D.this.rowsl, scale);

                        for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                           long idx1 = r * DoubleDST_2D.this.columnsl + c;
                           long idx2 = DoubleDST_2D.this.rowsl + r;
                           a.setDouble(idx1, t.getDouble(r));
                           a.setDouble(idx1 + 1L, t.getDouble(idx2));
                           a.setDouble(idx1 + 2L, t.getDouble(idx2 + DoubleDST_2D.this.rowsl));
                           a.setDouble(idx1 + 3L, t.getDouble(idx2 + 2L * DoubleDST_2D.this.rowsl));
                        }
                     }
                  } else {
                     for(long c = 4L * n0; c < DoubleDST_2D.this.columnsl; c += (long)(4 * nthreads)) {
                        for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                           long idx1 = r * DoubleDST_2D.this.columnsl + c;
                           long idx2 = DoubleDST_2D.this.rowsl + r;
                           t.setDouble(r, a.getDouble(idx1));
                           t.setDouble(idx2, a.getDouble(idx1 + 1L));
                           t.setDouble(idx2 + DoubleDST_2D.this.rowsl, a.getDouble(idx1 + 2L));
                           t.setDouble(idx2 + 2L * DoubleDST_2D.this.rowsl, a.getDouble(idx1 + 3L));
                        }

                        DoubleDST_2D.this.dstRows.inverse(t, 0L, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, DoubleDST_2D.this.rowsl, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, 2L * DoubleDST_2D.this.rowsl, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, 3L * DoubleDST_2D.this.rowsl, scale);

                        for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                           long idx1 = r * DoubleDST_2D.this.columnsl + c;
                           long idx2 = DoubleDST_2D.this.rowsl + r;
                           a.setDouble(idx1, t.getDouble(r));
                           a.setDouble(idx1 + 1L, t.getDouble(idx2));
                           a.setDouble(idx1 + 2L, t.getDouble(idx2 + DoubleDST_2D.this.rowsl));
                           a.setDouble(idx1 + 3L, t.getDouble(idx2 + 2L * DoubleDST_2D.this.rowsl));
                        }
                     }
                  }
               } else if (DoubleDST_2D.this.columnsl == 2L) {
                  for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                     long idx1 = r * DoubleDST_2D.this.columnsl + 2L * n0;
                     t.setDouble(r, a.getDouble(idx1));
                     t.setDouble(r + DoubleDST_2D.this.rowsl, a.getDouble(idx1 + 1L));
                  }

                  if (isgn == -1) {
                     DoubleDST_2D.this.dstRows.forward(t, 0L, scale);
                     DoubleDST_2D.this.dstRows.forward(t, DoubleDST_2D.this.rowsl, scale);
                  } else {
                     DoubleDST_2D.this.dstRows.inverse(t, 0L, scale);
                     DoubleDST_2D.this.dstRows.inverse(t, DoubleDST_2D.this.rowsl, scale);
                  }

                  for(long r = 0L; r < DoubleDST_2D.this.rowsl; ++r) {
                     long idx1 = r * DoubleDST_2D.this.columnsl + 2L * n0;
                     a.setDouble(idx1, t.getDouble(r));
                     a.setDouble(idx1 + 1L, t.getDouble(r + DoubleDST_2D.this.rowsl));
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d_subth(final int isgn, final double[][] a, final boolean scale) {
      int nthread = FastMath.min(this.columns, ConcurrencyUtils.getNumberOfThreads());
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      } else if (this.columns < 2) {
         nt >>= 2;
      }

      final int ntf = nt;
      final int nthreads = nthread;
      Future<?>[] futures = new Future[nthread];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (DoubleDST_2D.this.columns > 2) {
                  if (isgn == -1) {
                     for(int c = 4 * i; c < DoubleDST_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx2 = DoubleDST_2D.this.rows + r;
                           t[r] = a[r][c];
                           t[idx2] = a[r][c + 1];
                           t[idx2 + DoubleDST_2D.this.rows] = a[r][c + 2];
                           t[idx2 + 2 * DoubleDST_2D.this.rows] = a[r][c + 3];
                        }

                        DoubleDST_2D.this.dstRows.forward(t, 0, scale);
                        DoubleDST_2D.this.dstRows.forward(t, DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.forward(t, 2 * DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.forward(t, 3 * DoubleDST_2D.this.rows, scale);

                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx2 = DoubleDST_2D.this.rows + r;
                           a[r][c] = t[r];
                           a[r][c + 1] = t[idx2];
                           a[r][c + 2] = t[idx2 + DoubleDST_2D.this.rows];
                           a[r][c + 3] = t[idx2 + 2 * DoubleDST_2D.this.rows];
                        }
                     }
                  } else {
                     for(int c = 4 * i; c < DoubleDST_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx2 = DoubleDST_2D.this.rows + r;
                           t[r] = a[r][c];
                           t[idx2] = a[r][c + 1];
                           t[idx2 + DoubleDST_2D.this.rows] = a[r][c + 2];
                           t[idx2 + 2 * DoubleDST_2D.this.rows] = a[r][c + 3];
                        }

                        DoubleDST_2D.this.dstRows.inverse(t, 0, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, 2 * DoubleDST_2D.this.rows, scale);
                        DoubleDST_2D.this.dstRows.inverse(t, 3 * DoubleDST_2D.this.rows, scale);

                        for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                           int idx2 = DoubleDST_2D.this.rows + r;
                           a[r][c] = t[r];
                           a[r][c + 1] = t[idx2];
                           a[r][c + 2] = t[idx2 + DoubleDST_2D.this.rows];
                           a[r][c + 3] = t[idx2 + 2 * DoubleDST_2D.this.rows];
                        }
                     }
                  }
               } else if (DoubleDST_2D.this.columns == 2) {
                  for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                     t[r] = a[r][2 * i];
                     t[r + DoubleDST_2D.this.rows] = a[r][2 * i + 1];
                  }

                  if (isgn == -1) {
                     DoubleDST_2D.this.dstRows.forward(t, 0, scale);
                     DoubleDST_2D.this.dstRows.forward(t, DoubleDST_2D.this.rows, scale);
                  } else {
                     DoubleDST_2D.this.dstRows.inverse(t, 0, scale);
                     DoubleDST_2D.this.dstRows.inverse(t, DoubleDST_2D.this.rows, scale);
                  }

                  for(int r = 0; r < DoubleDST_2D.this.rows; ++r) {
                     a[r][2 * i] = t[r];
                     a[r][2 * i + 1] = t[r + DoubleDST_2D.this.rows];
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d0_subth(final int isgn, final double[] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (isgn == -1) {
                  for(int r = i; r < DoubleDST_2D.this.rows; r += nthreads) {
                     DoubleDST_2D.this.dstColumns.forward(a, r * DoubleDST_2D.this.columns, scale);
                  }
               } else {
                  for(int r = i; r < DoubleDST_2D.this.rows; r += nthreads) {
                     DoubleDST_2D.this.dstColumns.inverse(a, r * DoubleDST_2D.this.columns, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d0_subth(final int isgn, final DoubleLargeArray a, final boolean scale) {
      final int nthreads = (int)((long)ConcurrencyUtils.getNumberOfThreads() > this.rowsl ? this.rowsl : (long)ConcurrencyUtils.getNumberOfThreads());
      Future<?>[] futures = new Future[nthreads];

      for(int i = 0; i < nthreads; ++i) {
         final long n0 = (long)i;
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (isgn == -1) {
                  for(long r = n0; r < DoubleDST_2D.this.rowsl; r += (long)nthreads) {
                     DoubleDST_2D.this.dstColumns.forward(a, r * DoubleDST_2D.this.columnsl, scale);
                  }
               } else {
                  for(long r = n0; r < (long)DoubleDST_2D.this.rows; r += (long)nthreads) {
                     DoubleDST_2D.this.dstColumns.inverse(a, r * DoubleDST_2D.this.columnsl, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d0_subth(final int isgn, final double[][] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (isgn == -1) {
                  for(int r = i; r < DoubleDST_2D.this.rows; r += nthreads) {
                     DoubleDST_2D.this.dstColumns.forward(a[r], scale);
                  }
               } else {
                  for(int r = i; r < DoubleDST_2D.this.rows; r += nthreads) {
                     DoubleDST_2D.this.dstColumns.inverse(a[r], scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleDST_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d_sub(int isgn, double[] a, boolean scale) {
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      } else if (this.columns < 2) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (this.columns > 2) {
         if (isgn == -1) {
            for(int c = 0; c < this.columns; c += 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = r * this.columns + c;
                  int idx2 = this.rows + r;
                  t[r] = a[idx1];
                  t[idx2] = a[idx1 + 1];
                  t[idx2 + this.rows] = a[idx1 + 2];
                  t[idx2 + 2 * this.rows] = a[idx1 + 3];
               }

               this.dstRows.forward(t, 0, scale);
               this.dstRows.forward(t, this.rows, scale);
               this.dstRows.forward(t, 2 * this.rows, scale);
               this.dstRows.forward(t, 3 * this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = r * this.columns + c;
                  int idx2 = this.rows + r;
                  a[idx1] = t[r];
                  a[idx1 + 1] = t[idx2];
                  a[idx1 + 2] = t[idx2 + this.rows];
                  a[idx1 + 3] = t[idx2 + 2 * this.rows];
               }
            }
         } else {
            for(int c = 0; c < this.columns; c += 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = r * this.columns + c;
                  int idx2 = this.rows + r;
                  t[r] = a[idx1];
                  t[idx2] = a[idx1 + 1];
                  t[idx2 + this.rows] = a[idx1 + 2];
                  t[idx2 + 2 * this.rows] = a[idx1 + 3];
               }

               this.dstRows.inverse(t, 0, scale);
               this.dstRows.inverse(t, this.rows, scale);
               this.dstRows.inverse(t, 2 * this.rows, scale);
               this.dstRows.inverse(t, 3 * this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = r * this.columns + c;
                  int idx2 = this.rows + r;
                  a[idx1] = t[r];
                  a[idx1 + 1] = t[idx2];
                  a[idx1 + 2] = t[idx2 + this.rows];
                  a[idx1 + 3] = t[idx2 + 2 * this.rows];
               }
            }
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.columns;
            t[r] = a[idx1];
            t[this.rows + r] = a[idx1 + 1];
         }

         if (isgn == -1) {
            this.dstRows.forward(t, 0, scale);
            this.dstRows.forward(t, this.rows, scale);
         } else {
            this.dstRows.inverse(t, 0, scale);
            this.dstRows.inverse(t, this.rows, scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.columns;
            a[idx1] = t[r];
            a[idx1 + 1] = t[this.rows + r];
         }
      }

   }

   private void ddxt2d_sub(int isgn, DoubleLargeArray a, boolean scale) {
      long nt = 4L * this.rowsl;
      if (this.columnsl == 2L) {
         nt >>= 1;
      } else if (this.columnsl < 2L) {
         nt >>= 2;
      }

      DoubleLargeArray t = new DoubleLargeArray(nt);
      if (this.columnsl > 2L) {
         if (isgn == -1) {
            for(long c = 0L; c < this.columnsl; c += 4L) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  t.setDouble(r, a.getDouble(idx1));
                  t.setDouble(idx2, a.getDouble(idx1 + 1L));
                  t.setDouble(idx2 + this.rowsl, a.getDouble(idx1 + 2L));
                  t.setDouble(idx2 + 2L * this.rowsl, a.getDouble(idx1 + 3L));
               }

               this.dstRows.forward(t, 0L, scale);
               this.dstRows.forward(t, this.rowsl, scale);
               this.dstRows.forward(t, 2L * this.rowsl, scale);
               this.dstRows.forward(t, 3L * this.rowsl, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  a.setDouble(idx1, t.getDouble(r));
                  a.setDouble(idx1 + 1L, t.getDouble(idx2));
                  a.setDouble(idx1 + 2L, t.getDouble(idx2 + this.rowsl));
                  a.setDouble(idx1 + 3L, t.getDouble(idx2 + 2L * this.rowsl));
               }
            }
         } else {
            for(long c = 0L; c < this.columnsl; c += 4L) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  t.setDouble(r, a.getDouble(idx1));
                  t.setDouble(idx2, a.getDouble(idx1 + 1L));
                  t.setDouble(idx2 + this.rowsl, a.getDouble(idx1 + 2L));
                  t.setDouble(idx2 + 2L * this.rowsl, a.getDouble(idx1 + 3L));
               }

               this.dstRows.inverse(t, 0L, scale);
               this.dstRows.inverse(t, this.rowsl, scale);
               this.dstRows.inverse(t, 2L * this.rowsl, scale);
               this.dstRows.inverse(t, 3L * this.rowsl, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  a.setDouble(idx1, t.getDouble(r));
                  a.setDouble(idx1 + 1L, t.getDouble(idx2));
                  a.setDouble(idx1 + 2L, t.getDouble(idx2 + this.rowsl));
                  a.setDouble(idx1 + 3L, t.getDouble(idx2 + 2L * this.rowsl));
               }
            }
         }
      } else if (this.columnsl == 2L) {
         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            t.setDouble(r, a.getDouble(idx1));
            t.setDouble(this.rowsl + r, a.getDouble(idx1 + 1L));
         }

         if (isgn == -1) {
            this.dstRows.forward(t, 0L, scale);
            this.dstRows.forward(t, this.rowsl, scale);
         } else {
            this.dstRows.inverse(t, 0L, scale);
            this.dstRows.inverse(t, this.rowsl, scale);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            a.setDouble(idx1, t.getDouble(r));
            a.setDouble(idx1 + 1L, t.getDouble(this.rowsl + r));
         }
      }

   }

   private void ddxt2d_sub(int isgn, double[][] a, boolean scale) {
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      } else if (this.columns < 2) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (this.columns > 2) {
         if (isgn == -1) {
            for(int c = 0; c < this.columns; c += 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = this.rows + r;
                  t[r] = a[r][c];
                  t[idx2] = a[r][c + 1];
                  t[idx2 + this.rows] = a[r][c + 2];
                  t[idx2 + 2 * this.rows] = a[r][c + 3];
               }

               this.dstRows.forward(t, 0, scale);
               this.dstRows.forward(t, this.rows, scale);
               this.dstRows.forward(t, 2 * this.rows, scale);
               this.dstRows.forward(t, 3 * this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = this.rows + r;
                  a[r][c] = t[r];
                  a[r][c + 1] = t[idx2];
                  a[r][c + 2] = t[idx2 + this.rows];
                  a[r][c + 3] = t[idx2 + 2 * this.rows];
               }
            }
         } else {
            for(int c = 0; c < this.columns; c += 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = this.rows + r;
                  t[r] = a[r][c];
                  t[idx2] = a[r][c + 1];
                  t[idx2 + this.rows] = a[r][c + 2];
                  t[idx2 + 2 * this.rows] = a[r][c + 3];
               }

               this.dstRows.inverse(t, 0, scale);
               this.dstRows.inverse(t, this.rows, scale);
               this.dstRows.inverse(t, 2 * this.rows, scale);
               this.dstRows.inverse(t, 3 * this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = this.rows + r;
                  a[r][c] = t[r];
                  a[r][c + 1] = t[idx2];
                  a[r][c + 2] = t[idx2 + this.rows];
                  a[r][c + 3] = t[idx2 + 2 * this.rows];
               }
            }
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            t[r] = a[r][0];
            t[this.rows + r] = a[r][1];
         }

         if (isgn == -1) {
            this.dstRows.forward(t, 0, scale);
            this.dstRows.forward(t, this.rows, scale);
         } else {
            this.dstRows.inverse(t, 0, scale);
            this.dstRows.inverse(t, this.rows, scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            a[r][0] = t[r];
            a[r][1] = t[this.rows + r];
         }
      }

   }
}
