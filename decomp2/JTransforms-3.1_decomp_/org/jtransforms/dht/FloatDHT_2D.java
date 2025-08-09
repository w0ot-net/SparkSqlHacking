package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.util.FastMath;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class FloatDHT_2D {
   private int rows;
   private int columns;
   private long rowsl;
   private long columnsl;
   private FloatDHT_1D dhtColumns;
   private FloatDHT_1D dhtRows;
   private boolean isPowerOfTwo = false;
   private boolean useThreads = false;

   public FloatDHT_2D(long rows, long columns) {
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
         this.dhtRows = new FloatDHT_1D(rows);
         if (rows == columns) {
            this.dhtColumns = this.dhtRows;
         } else {
            this.dhtColumns = new FloatDHT_1D(columns);
         }

      } else {
         throw new IllegalArgumentException("rows and columns must be greater than 1");
      }
   }

   public void forward(final float[] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(-1, (float[])a, true);
            this.ddxt2d0_subth(-1, (float[])a, true);
         } else {
            this.ddxt2d_sub(-1, (float[])a, true);

            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.forward(a, i * this.columns);
            }
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int i = firstRow; i < lastRow; ++i) {
                        FloatDHT_2D.this.dhtColumns.forward(a, i * FloatDHT_2D.this.columns);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columns / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstColumn = l * p;
               final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_2D.this.rows];

                     for(int c = firstColumn; c < lastColumn; ++c) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           temp[r] = a[r * FloatDHT_2D.this.columns + c];
                        }

                        FloatDHT_2D.this.dhtRows.forward(temp);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           a[r * FloatDHT_2D.this.columns + c] = temp[r];
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.forward(a, i * this.columns);
            }

            float[] temp = new float[this.rows];

            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  temp[r] = a[r * this.columns + c];
               }

               this.dhtRows.forward(temp);

               for(int r = 0; r < this.rows; ++r) {
                  a[r * this.columns + c] = temp[r];
               }
            }
         }

         this.yTransform(a);
      }

   }

   public void forward(final FloatLargeArray a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(-1, (FloatLargeArray)a, true);
            this.ddxt2d0_subth(-1, (FloatLargeArray)a, true);
         } else {
            this.ddxt2d_sub(-1, (FloatLargeArray)a, true);

            for(long i = 0L; i < this.rowsl; ++i) {
               this.dhtColumns.forward(a, i * this.columnsl);
            }
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstRow = (long)l * p;
               final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long i = firstRow; i < lastRow; ++i) {
                        FloatDHT_2D.this.dhtColumns.forward(a, i * FloatDHT_2D.this.columnsl);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columnsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstColumn = (long)l * p;
               final long lastColumn = l == nthreads - 1 ? this.columnsl : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     FloatLargeArray temp = new FloatLargeArray(FloatDHT_2D.this.rowsl, false);

                     for(long c = firstColumn; c < lastColumn; ++c) {
                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           temp.setFloat(r, a.getFloat(r * FloatDHT_2D.this.columnsl + c));
                        }

                        FloatDHT_2D.this.dhtRows.forward(temp);

                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           a.setFloat(r * FloatDHT_2D.this.columnsl + c, temp.getFloat(r));
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long i = 0L; i < this.rowsl; ++i) {
               this.dhtColumns.forward(a, i * this.columnsl);
            }

            FloatLargeArray temp = new FloatLargeArray(this.rowsl, false);

            for(long c = 0L; c < this.columnsl; ++c) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  temp.setFloat(r, a.getFloat(r * this.columnsl + c));
               }

               this.dhtRows.forward(temp);

               for(long r = 0L; r < this.rowsl; ++r) {
                  a.setFloat(r * this.columnsl + c, temp.getFloat(r));
               }
            }
         }

         this.yTransform(a);
      }

   }

   public void forward(final float[][] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(-1, (float[][])a, true);
            this.ddxt2d0_subth(-1, (float[][])a, true);
         } else {
            this.ddxt2d_sub(-1, (float[][])a, true);

            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.forward(a[i]);
            }
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int i = firstRow; i < lastRow; ++i) {
                        FloatDHT_2D.this.dhtColumns.forward(a[i]);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columns / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstColumn = l * p;
               final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_2D.this.rows];

                     for(int c = firstColumn; c < lastColumn; ++c) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           temp[r] = a[r][c];
                        }

                        FloatDHT_2D.this.dhtRows.forward(temp);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           a[r][c] = temp[r];
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.forward(a[i]);
            }

            float[] temp = new float[this.rows];

            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  temp[r] = a[r][c];
               }

               this.dhtRows.forward(temp);

               for(int r = 0; r < this.rows; ++r) {
                  a[r][c] = temp[r];
               }
            }
         }

         this.yTransform(a);
      }

   }

   public void inverse(final float[] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(1, (float[])a, scale);
            this.ddxt2d0_subth(1, (float[])a, scale);
         } else {
            this.ddxt2d_sub(1, (float[])a, scale);

            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.inverse(a, i * this.columns, scale);
            }
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int i = firstRow; i < lastRow; ++i) {
                        FloatDHT_2D.this.dhtColumns.inverse(a, i * FloatDHT_2D.this.columns, scale);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columns / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstColumn = l * p;
               final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_2D.this.rows];

                     for(int c = firstColumn; c < lastColumn; ++c) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           temp[r] = a[r * FloatDHT_2D.this.columns + c];
                        }

                        FloatDHT_2D.this.dhtRows.inverse(temp, scale);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           a[r * FloatDHT_2D.this.columns + c] = temp[r];
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.inverse(a, i * this.columns, scale);
            }

            float[] temp = new float[this.rows];

            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  temp[r] = a[r * this.columns + c];
               }

               this.dhtRows.inverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  a[r * this.columns + c] = temp[r];
               }
            }
         }

         this.yTransform(a);
      }

   }

   public void inverse(final FloatLargeArray a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(1, (FloatLargeArray)a, scale);
            this.ddxt2d0_subth(1, (FloatLargeArray)a, scale);
         } else {
            this.ddxt2d_sub(1, (FloatLargeArray)a, scale);

            for(long i = 0L; i < this.rowsl; ++i) {
               this.dhtColumns.inverse(a, i * this.columnsl, scale);
            }
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstRow = (long)l * p;
               final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long i = firstRow; i < lastRow; ++i) {
                        FloatDHT_2D.this.dhtColumns.inverse(a, i * FloatDHT_2D.this.columnsl, scale);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columnsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstColumn = (long)l * p;
               final long lastColumn = l == nthreads - 1 ? this.columnsl : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     FloatLargeArray temp = new FloatLargeArray(FloatDHT_2D.this.rowsl, false);

                     for(long c = firstColumn; c < lastColumn; ++c) {
                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           temp.setFloat(r, a.getFloat(r * FloatDHT_2D.this.columnsl + c));
                        }

                        FloatDHT_2D.this.dhtRows.inverse(temp, scale);

                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           a.setFloat(r * FloatDHT_2D.this.columnsl + c, temp.getFloat(r));
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long i = 0L; i < this.rowsl; ++i) {
               this.dhtColumns.inverse(a, i * this.columnsl, scale);
            }

            FloatLargeArray temp = new FloatLargeArray(this.rowsl, false);

            for(long c = 0L; c < this.columnsl; ++c) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  temp.setFloat(r, a.getFloat(r * this.columnsl + c));
               }

               this.dhtRows.inverse(temp, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  a.setFloat(r * this.columnsl + c, temp.getFloat(r));
               }
            }
         }

         this.yTransform(a);
      }

   }

   public void inverse(final float[][] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt2d_subth(1, (float[][])a, scale);
            this.ddxt2d0_subth(1, (float[][])a, scale);
         } else {
            this.ddxt2d_sub(1, (float[][])a, scale);

            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.inverse(a[i], scale);
            }
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int i = firstRow; i < lastRow; ++i) {
                        FloatDHT_2D.this.dhtColumns.inverse(a[i], scale);
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.columns / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstColumn = l * p;
               final int lastColumn = l == nthreads - 1 ? this.columns : firstColumn + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_2D.this.rows];

                     for(int c = firstColumn; c < lastColumn; ++c) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           temp[r] = a[r][c];
                        }

                        FloatDHT_2D.this.dhtRows.inverse(temp, scale);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           a[r][c] = temp[r];
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int i = 0; i < this.rows; ++i) {
               this.dhtColumns.inverse(a[i], scale);
            }

            float[] temp = new float[this.rows];

            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  temp[r] = a[r][c];
               }

               this.dhtRows.inverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  a[r][c] = temp[r];
               }
            }
         }

         this.yTransform(a);
      }

   }

   private void ddxt2d_subth(final int isgn, final float[] a, final boolean scale) {
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
               float[] t = new float[ntf];
               if (FloatDHT_2D.this.columns > 2) {
                  if (isgn == -1) {
                     for(int c = 4 * i; c < FloatDHT_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx1 = r * FloatDHT_2D.this.columns + c;
                           int idx2 = FloatDHT_2D.this.rows + r;
                           t[r] = a[idx1];
                           t[idx2] = a[idx1 + 1];
                           t[idx2 + FloatDHT_2D.this.rows] = a[idx1 + 2];
                           t[idx2 + 2 * FloatDHT_2D.this.rows] = a[idx1 + 3];
                        }

                        FloatDHT_2D.this.dhtRows.forward(t, 0);
                        FloatDHT_2D.this.dhtRows.forward(t, FloatDHT_2D.this.rows);
                        FloatDHT_2D.this.dhtRows.forward(t, 2 * FloatDHT_2D.this.rows);
                        FloatDHT_2D.this.dhtRows.forward(t, 3 * FloatDHT_2D.this.rows);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx1 = r * FloatDHT_2D.this.columns + c;
                           int idx2 = FloatDHT_2D.this.rows + r;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[idx2];
                           a[idx1 + 2] = t[idx2 + FloatDHT_2D.this.rows];
                           a[idx1 + 3] = t[idx2 + 2 * FloatDHT_2D.this.rows];
                        }
                     }
                  } else {
                     for(int c = 4 * i; c < FloatDHT_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx1 = r * FloatDHT_2D.this.columns + c;
                           int idx2 = FloatDHT_2D.this.rows + r;
                           t[r] = a[idx1];
                           t[idx2] = a[idx1 + 1];
                           t[idx2 + FloatDHT_2D.this.rows] = a[idx1 + 2];
                           t[idx2 + 2 * FloatDHT_2D.this.rows] = a[idx1 + 3];
                        }

                        FloatDHT_2D.this.dhtRows.inverse(t, 0, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, FloatDHT_2D.this.rows, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, 2 * FloatDHT_2D.this.rows, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, 3 * FloatDHT_2D.this.rows, scale);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx1 = r * FloatDHT_2D.this.columns + c;
                           int idx2 = FloatDHT_2D.this.rows + r;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[idx2];
                           a[idx1 + 2] = t[idx2 + FloatDHT_2D.this.rows];
                           a[idx1 + 3] = t[idx2 + 2 * FloatDHT_2D.this.rows];
                        }
                     }
                  }
               } else if (FloatDHT_2D.this.columns == 2) {
                  for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                     int idx1 = r * FloatDHT_2D.this.columns + 2 * i;
                     t[r] = a[idx1];
                     t[r + FloatDHT_2D.this.rows] = a[idx1 + 1];
                  }

                  if (isgn == -1) {
                     FloatDHT_2D.this.dhtRows.forward(t, 0);
                     FloatDHT_2D.this.dhtRows.forward(t, FloatDHT_2D.this.rows);
                  } else {
                     FloatDHT_2D.this.dhtRows.inverse(t, 0, scale);
                     FloatDHT_2D.this.dhtRows.inverse(t, FloatDHT_2D.this.rows, scale);
                  }

                  for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                     int idx1 = r * FloatDHT_2D.this.columns + 2 * i;
                     a[idx1] = t[r];
                     a[idx1 + 1] = t[r + FloatDHT_2D.this.rows];
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d_subth(final int isgn, final FloatLargeArray a, final boolean scale) {
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
               FloatLargeArray t = new FloatLargeArray(ntf);
               if (FloatDHT_2D.this.columnsl > 2L) {
                  if (isgn == -1) {
                     for(long c = 4L * n0; c < FloatDHT_2D.this.columnsl; c += (long)(4 * nthreads)) {
                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           long idx1 = r * FloatDHT_2D.this.columnsl + c;
                           long idx2 = FloatDHT_2D.this.rowsl + r;
                           t.setFloat(r, a.getFloat(idx1));
                           t.setFloat(idx2, a.getFloat(idx1 + 1L));
                           t.setFloat(idx2 + FloatDHT_2D.this.rowsl, a.getFloat(idx1 + 2L));
                           t.setFloat(idx2 + 2L * FloatDHT_2D.this.rowsl, a.getFloat(idx1 + 3L));
                        }

                        FloatDHT_2D.this.dhtRows.forward(t, 0L);
                        FloatDHT_2D.this.dhtRows.forward(t, FloatDHT_2D.this.rowsl);
                        FloatDHT_2D.this.dhtRows.forward(t, 2L * FloatDHT_2D.this.rowsl);
                        FloatDHT_2D.this.dhtRows.forward(t, 3L * FloatDHT_2D.this.rowsl);

                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           long idx1 = r * FloatDHT_2D.this.columnsl + c;
                           long idx2 = FloatDHT_2D.this.rowsl + r;
                           a.setFloat(idx1, t.getFloat(r));
                           a.setFloat(idx1 + 1L, t.getFloat(idx2));
                           a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDHT_2D.this.rowsl));
                           a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDHT_2D.this.rowsl));
                        }
                     }
                  } else {
                     for(long c = 4L * n0; c < FloatDHT_2D.this.columnsl; c += (long)(4 * nthreads)) {
                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           long idx1 = r * FloatDHT_2D.this.columnsl + c;
                           long idx2 = FloatDHT_2D.this.rowsl + r;
                           t.setFloat(r, a.getFloat(idx1));
                           t.setFloat(idx2, a.getFloat(idx1 + 1L));
                           t.setFloat(idx2 + FloatDHT_2D.this.rowsl, a.getFloat(idx1 + 2L));
                           t.setFloat(idx2 + 2L * FloatDHT_2D.this.rowsl, a.getFloat(idx1 + 3L));
                        }

                        FloatDHT_2D.this.dhtRows.inverse(t, 0L, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, FloatDHT_2D.this.rowsl, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, 2L * FloatDHT_2D.this.rowsl, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, 3L * FloatDHT_2D.this.rowsl, scale);

                        for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                           long idx1 = r * FloatDHT_2D.this.columnsl + c;
                           long idx2 = FloatDHT_2D.this.rowsl + r;
                           a.setFloat(idx1, t.getFloat(r));
                           a.setFloat(idx1 + 1L, t.getFloat(idx2));
                           a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDHT_2D.this.rowsl));
                           a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDHT_2D.this.rowsl));
                        }
                     }
                  }
               } else if (FloatDHT_2D.this.columnsl == 2L) {
                  for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                     long idx1 = r * FloatDHT_2D.this.columnsl + 2L * n0;
                     t.setFloat(r, a.getFloat(idx1));
                     t.setFloat(r + FloatDHT_2D.this.rowsl, a.getFloat(idx1 + 1L));
                  }

                  if (isgn == -1) {
                     FloatDHT_2D.this.dhtRows.forward(t, 0L);
                     FloatDHT_2D.this.dhtRows.forward(t, FloatDHT_2D.this.rowsl);
                  } else {
                     FloatDHT_2D.this.dhtRows.inverse(t, 0L, scale);
                     FloatDHT_2D.this.dhtRows.inverse(t, FloatDHT_2D.this.rowsl, scale);
                  }

                  for(long r = 0L; r < FloatDHT_2D.this.rowsl; ++r) {
                     long idx1 = r * FloatDHT_2D.this.columnsl + 2L * n0;
                     a.setFloat(idx1, t.getFloat(r));
                     a.setFloat(idx1 + 1L, t.getFloat(r + FloatDHT_2D.this.rowsl));
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d_subth(final int isgn, final float[][] a, final boolean scale) {
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
               float[] t = new float[ntf];
               if (FloatDHT_2D.this.columns > 2) {
                  if (isgn == -1) {
                     for(int c = 4 * i; c < FloatDHT_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx2 = FloatDHT_2D.this.rows + r;
                           t[r] = a[r][c];
                           t[idx2] = a[r][c + 1];
                           t[idx2 + FloatDHT_2D.this.rows] = a[r][c + 2];
                           t[idx2 + 2 * FloatDHT_2D.this.rows] = a[r][c + 3];
                        }

                        FloatDHT_2D.this.dhtRows.forward(t, 0);
                        FloatDHT_2D.this.dhtRows.forward(t, FloatDHT_2D.this.rows);
                        FloatDHT_2D.this.dhtRows.forward(t, 2 * FloatDHT_2D.this.rows);
                        FloatDHT_2D.this.dhtRows.forward(t, 3 * FloatDHT_2D.this.rows);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx2 = FloatDHT_2D.this.rows + r;
                           a[r][c] = t[r];
                           a[r][c + 1] = t[idx2];
                           a[r][c + 2] = t[idx2 + FloatDHT_2D.this.rows];
                           a[r][c + 3] = t[idx2 + 2 * FloatDHT_2D.this.rows];
                        }
                     }
                  } else {
                     for(int c = 4 * i; c < FloatDHT_2D.this.columns; c += 4 * nthreads) {
                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx2 = FloatDHT_2D.this.rows + r;
                           t[r] = a[r][c];
                           t[idx2] = a[r][c + 1];
                           t[idx2 + FloatDHT_2D.this.rows] = a[r][c + 2];
                           t[idx2 + 2 * FloatDHT_2D.this.rows] = a[r][c + 3];
                        }

                        FloatDHT_2D.this.dhtRows.inverse(t, 0, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, FloatDHT_2D.this.rows, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, 2 * FloatDHT_2D.this.rows, scale);
                        FloatDHT_2D.this.dhtRows.inverse(t, 3 * FloatDHT_2D.this.rows, scale);

                        for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                           int idx2 = FloatDHT_2D.this.rows + r;
                           a[r][c] = t[r];
                           a[r][c + 1] = t[idx2];
                           a[r][c + 2] = t[idx2 + FloatDHT_2D.this.rows];
                           a[r][c + 3] = t[idx2 + 2 * FloatDHT_2D.this.rows];
                        }
                     }
                  }
               } else if (FloatDHT_2D.this.columns == 2) {
                  for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                     t[r] = a[r][2 * i];
                     t[r + FloatDHT_2D.this.rows] = a[r][2 * i + 1];
                  }

                  if (isgn == -1) {
                     FloatDHT_2D.this.dhtRows.forward(t, 0);
                     FloatDHT_2D.this.dhtRows.forward(t, FloatDHT_2D.this.rows);
                  } else {
                     FloatDHT_2D.this.dhtRows.inverse(t, 0, scale);
                     FloatDHT_2D.this.dhtRows.inverse(t, FloatDHT_2D.this.rows, scale);
                  }

                  for(int r = 0; r < FloatDHT_2D.this.rows; ++r) {
                     a[r][2 * i] = t[r];
                     a[r][2 * i + 1] = t[r + FloatDHT_2D.this.rows];
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d0_subth(final int isgn, final float[] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (isgn == -1) {
                  for(int r = i; r < FloatDHT_2D.this.rows; r += nthreads) {
                     FloatDHT_2D.this.dhtColumns.forward(a, r * FloatDHT_2D.this.columns);
                  }
               } else {
                  for(int r = i; r < FloatDHT_2D.this.rows; r += nthreads) {
                     FloatDHT_2D.this.dhtColumns.inverse(a, r * FloatDHT_2D.this.columns, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d0_subth(final int isgn, final FloatLargeArray a, final boolean scale) {
      final int nthreads = (int)((long)ConcurrencyUtils.getNumberOfThreads() > this.rowsl ? this.rowsl : (long)ConcurrencyUtils.getNumberOfThreads());
      Future<?>[] futures = new Future[nthreads];

      for(int i = 0; i < nthreads; ++i) {
         final long n0 = (long)i;
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (isgn == -1) {
                  for(long r = n0; r < FloatDHT_2D.this.rowsl; r += (long)nthreads) {
                     FloatDHT_2D.this.dhtColumns.forward(a, r * FloatDHT_2D.this.columnsl);
                  }
               } else {
                  for(long r = n0; r < FloatDHT_2D.this.rowsl; r += (long)nthreads) {
                     FloatDHT_2D.this.dhtColumns.inverse(a, r * FloatDHT_2D.this.columnsl, scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d0_subth(final int isgn, final float[][] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               if (isgn == -1) {
                  for(int r = i; r < FloatDHT_2D.this.rows; r += nthreads) {
                     FloatDHT_2D.this.dhtColumns.forward(a[r]);
                  }
               } else {
                  for(int r = i; r < FloatDHT_2D.this.rows; r += nthreads) {
                     FloatDHT_2D.this.dhtColumns.inverse(a[r], scale);
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_2D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt2d_sub(int isgn, float[] a, boolean scale) {
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      } else if (this.columns < 2) {
         nt >>= 2;
      }

      float[] t = new float[nt];
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

               this.dhtRows.forward(t, 0);
               this.dhtRows.forward(t, this.rows);
               this.dhtRows.forward(t, 2 * this.rows);
               this.dhtRows.forward(t, 3 * this.rows);

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

               this.dhtRows.inverse(t, 0, scale);
               this.dhtRows.inverse(t, this.rows, scale);
               this.dhtRows.inverse(t, 2 * this.rows, scale);
               this.dhtRows.inverse(t, 3 * this.rows, scale);

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
            this.dhtRows.forward(t, 0);
            this.dhtRows.forward(t, this.rows);
         } else {
            this.dhtRows.inverse(t, 0, scale);
            this.dhtRows.inverse(t, this.rows, scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.columns;
            a[idx1] = t[r];
            a[idx1 + 1] = t[this.rows + r];
         }
      }

   }

   private void ddxt2d_sub(int isgn, FloatLargeArray a, boolean scale) {
      long nt = 4L * this.rowsl;
      if (this.columnsl == 2L) {
         nt >>= 1;
      } else if (this.columnsl < 2L) {
         nt >>= 2;
      }

      FloatLargeArray t = new FloatLargeArray(nt);
      if (this.columnsl > 2L) {
         if (isgn == -1) {
            for(long c = 0L; c < this.columnsl; c += 4L) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  t.setFloat(r, a.getFloat(idx1));
                  t.setFloat(idx2, a.getFloat(idx1 + 1L));
                  t.setFloat(idx2 + this.rowsl, a.getFloat(idx1 + 2L));
                  t.setFloat(idx2 + 2L * this.rowsl, a.getFloat(idx1 + 3L));
               }

               this.dhtRows.forward(t, 0L);
               this.dhtRows.forward(t, this.rowsl);
               this.dhtRows.forward(t, 2L * this.rowsl);
               this.dhtRows.forward(t, 3L * this.rowsl);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  a.setFloat(idx1, t.getFloat(r));
                  a.setFloat(idx1 + 1L, t.getFloat(idx2));
                  a.setFloat(idx1 + 2L, t.getFloat(idx2 + this.rowsl));
                  a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * this.rowsl));
               }
            }
         } else {
            for(long c = 0L; c < this.columnsl; c += 4L) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  t.setFloat(r, a.getFloat(idx1));
                  t.setFloat(idx2, a.getFloat(idx1 + 1L));
                  t.setFloat(idx2 + this.rowsl, a.getFloat(idx1 + 2L));
                  t.setFloat(idx2 + 2L * this.rowsl, a.getFloat(idx1 + 3L));
               }

               this.dhtRows.inverse(t, 0L, scale);
               this.dhtRows.inverse(t, this.rowsl, scale);
               this.dhtRows.inverse(t, 2L * this.rowsl, scale);
               this.dhtRows.inverse(t, 3L * this.rowsl, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = r * this.columnsl + c;
                  long idx2 = this.rowsl + r;
                  a.setFloat(idx1, t.getFloat(r));
                  a.setFloat(idx1 + 1L, t.getFloat(idx2));
                  a.setFloat(idx1 + 2L, t.getFloat(idx2 + this.rowsl));
                  a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * this.rowsl));
               }
            }
         }
      } else if (this.columnsl == 2L) {
         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            t.setFloat(r, a.getFloat(idx1));
            t.setFloat(this.rowsl + r, a.getFloat(idx1 + 1L));
         }

         if (isgn == -1) {
            this.dhtRows.forward(t, 0L);
            this.dhtRows.forward(t, this.rowsl);
         } else {
            this.dhtRows.inverse(t, 0L, scale);
            this.dhtRows.inverse(t, this.rowsl, scale);
         }

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.columnsl;
            a.setFloat(idx1, t.getFloat(r));
            a.setFloat(idx1 + 1L, t.getFloat(this.rowsl + r));
         }
      }

   }

   private void ddxt2d_sub(int isgn, float[][] a, boolean scale) {
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      } else if (this.columns < 2) {
         nt >>= 2;
      }

      float[] t = new float[nt];
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

               this.dhtRows.forward(t, 0);
               this.dhtRows.forward(t, this.rows);
               this.dhtRows.forward(t, 2 * this.rows);
               this.dhtRows.forward(t, 3 * this.rows);

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

               this.dhtRows.inverse(t, 0, scale);
               this.dhtRows.inverse(t, this.rows, scale);
               this.dhtRows.inverse(t, 2 * this.rows, scale);
               this.dhtRows.inverse(t, 3 * this.rows, scale);

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
            this.dhtRows.forward(t, 0);
            this.dhtRows.forward(t, this.rows);
         } else {
            this.dhtRows.inverse(t, 0, scale);
            this.dhtRows.inverse(t, this.rows, scale);
         }

         for(int r = 0; r < this.rows; ++r) {
            a[r][0] = t[r];
            a[r][1] = t[this.rows + r];
         }
      }

   }

   private void yTransform(float[] a) {
      for(int r = 0; r <= this.rows / 2; ++r) {
         int mRow = (this.rows - r) % this.rows;
         int idx1 = r * this.columns;
         int idx2 = mRow * this.columns;

         for(int c = 0; c <= this.columns / 2; ++c) {
            int mCol = (this.columns - c) % this.columns;
            float A = a[idx1 + c];
            float B = a[idx2 + c];
            float C = a[idx1 + mCol];
            float D = a[idx2 + mCol];
            float E = (A + D - (B + C)) / 2.0F;
            a[idx1 + c] = A - E;
            a[idx2 + c] = B + E;
            a[idx1 + mCol] = C + E;
            a[idx2 + mCol] = D - E;
         }
      }

   }

   private void yTransform(FloatLargeArray a) {
      for(long r = 0L; r <= this.rowsl / 2L; ++r) {
         long mRow = (this.rowsl - r) % this.rowsl;
         long idx1 = r * this.columnsl;
         long idx2 = mRow * this.columnsl;

         for(long c = 0L; c <= this.columnsl / 2L; ++c) {
            long mCol = (this.columnsl - c) % this.columnsl;
            float A = a.getFloat(idx1 + c);
            float B = a.getFloat(idx2 + c);
            float C = a.getFloat(idx1 + mCol);
            float D = a.getFloat(idx2 + mCol);
            float E = (A + D - (B + C)) / 2.0F;
            a.setFloat(idx1 + c, A - E);
            a.setFloat(idx2 + c, B + E);
            a.setFloat(idx1 + mCol, C + E);
            a.setFloat(idx2 + mCol, D - E);
         }
      }

   }

   private void yTransform(float[][] a) {
      for(int r = 0; r <= this.rows / 2; ++r) {
         int mRow = (this.rows - r) % this.rows;

         for(int c = 0; c <= this.columns / 2; ++c) {
            int mCol = (this.columns - c) % this.columns;
            float A = a[r][c];
            float B = a[mRow][c];
            float C = a[r][mCol];
            float D = a[mRow][mCol];
            float E = (A + D - (B + C)) / 2.0F;
            a[r][c] = A - E;
            a[mRow][c] = B + E;
            a[r][mCol] = C + E;
            a[mRow][mCol] = D - E;
         }
      }

   }
}
