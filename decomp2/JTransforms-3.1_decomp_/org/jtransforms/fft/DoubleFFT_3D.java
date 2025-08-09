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
import pl.edu.icm.jlargearrays.LargeArrayUtils;

public class DoubleFFT_3D {
   private int slices;
   private long slicesl;
   private int rows;
   private long rowsl;
   private int columns;
   private long columnsl;
   private int sliceStride;
   private long sliceStridel;
   private int rowStride;
   private long rowStridel;
   private DoubleFFT_1D fftSlices;
   private DoubleFFT_1D fftRows;
   private DoubleFFT_1D fftColumns;
   private boolean isPowerOfTwo = false;
   private boolean useThreads = false;

   public DoubleFFT_3D(long slices, long rows, long columns) {
      if (slices > 1L && rows > 1L && columns > 1L) {
         this.slices = (int)slices;
         this.rows = (int)rows;
         this.columns = (int)columns;
         this.slicesl = slices;
         this.rowsl = rows;
         this.columnsl = columns;
         this.sliceStride = (int)(rows * columns);
         this.rowStride = (int)columns;
         this.sliceStridel = rows * columns;
         this.rowStridel = columns;
         if (slices * rows * columns >= CommonUtils.getThreadsBeginN_3D()) {
            this.useThreads = true;
         }

         if (CommonUtils.isPowerOf2(slices) && CommonUtils.isPowerOf2(rows) && CommonUtils.isPowerOf2(columns)) {
            this.isPowerOfTwo = true;
         }

         CommonUtils.setUseLargeArrays(2L * slices * rows * columns > (long)LargeArray.getMaxSizeOf32bitArray());
         this.fftSlices = new DoubleFFT_1D(slices);
         if (slices == rows) {
            this.fftRows = this.fftSlices;
         } else {
            this.fftRows = new DoubleFFT_1D(rows);
         }

         if (slices == columns) {
            this.fftColumns = this.fftSlices;
         } else if (rows == columns) {
            this.fftColumns = this.fftRows;
         } else {
            this.fftColumns = new DoubleFFT_1D(columns);
         }

      } else {
         throw new IllegalArgumentException("slices, rows and columns must be greater than 1");
      }
   }

   public void complexForward(final double[] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         int oldn3 = this.columns;
         this.columns = 2 * this.columns;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(0, -1, (double[])a, true);
            this.cdft3db_subth(-1, (double[])a, true);
         } else {
            this.xdft3da_sub2(0, -1, (double[])a, true);
            this.cdft3db_sub(-1, (double[])a, true);
         }

         this.columns = oldn3;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
      } else {
         this.sliceStride = 2 * this.rows * this.columns;
         this.rowStride = 2 * this.columns;
         if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.slices / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexForward(a, idx1 + r * DoubleFFT_3D.this.rowStride);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx2 = 2 * c;

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStride;
                              int idx4 = 2 * r;
                              temp[idx4] = a[idx3];
                              temp[idx4 + 1] = a[idx3 + 1];
                           }

                           DoubleFFT_3D.this.fftRows.complexForward(temp);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStride;
                              int idx4 = 2 * r;
                              a[idx3] = temp[idx4];
                              a[idx3 + 1] = temp[idx4 + 1];
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                     for(int r = firstRow; r < lastRow; ++r) {
                        int idx1 = r * DoubleFFT_3D.this.rowStride;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx2 = 2 * c;

                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx3 = s * DoubleFFT_3D.this.sliceStride + idx1 + idx2;
                              int idx4 = 2 * s;
                              temp[idx4] = a[idx3];
                              temp[idx4 + 1] = a[idx3 + 1];
                           }

                           DoubleFFT_3D.this.fftSlices.complexForward(temp);

                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx3 = s * DoubleFFT_3D.this.sliceStride + idx1 + idx2;
                              int idx4 = 2 * s;
                              a[idx3] = temp[idx4];
                              a[idx3 + 1] = temp[idx4 + 1];
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride;

               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexForward(a, idx1 + r * this.rowStride);
               }
            }

            double[] temp = new double[2 * this.rows];

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride;

               for(int c = 0; c < this.columns; ++c) {
                  int idx2 = 2 * c;

                  for(int r = 0; r < this.rows; ++r) {
                     int idx3 = idx1 + idx2 + r * this.rowStride;
                     int idx4 = 2 * r;
                     temp[idx4] = a[idx3];
                     temp[idx4 + 1] = a[idx3 + 1];
                  }

                  this.fftRows.complexForward(temp);

                  for(int r = 0; r < this.rows; ++r) {
                     int idx3 = idx1 + idx2 + r * this.rowStride;
                     int idx4 = 2 * r;
                     a[idx3] = temp[idx4];
                     a[idx3 + 1] = temp[idx4 + 1];
                  }
               }
            }

            temp = new double[2 * this.slices];

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.rowStride;

               for(int c = 0; c < this.columns; ++c) {
                  int idx2 = 2 * c;

                  for(int s = 0; s < this.slices; ++s) {
                     int idx3 = s * this.sliceStride + idx1 + idx2;
                     int idx4 = 2 * s;
                     temp[idx4] = a[idx3];
                     temp[idx4 + 1] = a[idx3 + 1];
                  }

                  this.fftSlices.complexForward(temp);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx3 = s * this.sliceStride + idx1 + idx2;
                     int idx4 = 2 * s;
                     a[idx3] = temp[idx4];
                     a[idx3 + 1] = temp[idx4 + 1];
                  }
               }
            }
         }

         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
      }

   }

   public void complexForward(final DoubleLargeArray a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         long oldn3 = this.columnsl;
         this.columnsl = 2L * this.columnsl;
         this.sliceStridel = this.rowsl * this.columnsl;
         this.rowStridel = this.columnsl;
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(0L, -1, a, true);
            this.cdft3db_subth(-1, (DoubleLargeArray)a, true);
         } else {
            this.xdft3da_sub2(0L, -1, a, true);
            this.cdft3db_sub(-1, (DoubleLargeArray)a, true);
         }

         this.columnsl = oldn3;
         this.sliceStridel = this.rowsl * this.columnsl;
         this.rowStridel = this.columnsl;
      } else {
         this.sliceStridel = 2L * this.rowsl * this.columnsl;
         this.rowStridel = 2L * this.columnsl;
         if (nthreads > 1 && this.useThreads && this.slicesl >= (long)nthreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.slicesl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * DoubleFFT_3D.this.sliceStridel;

                        for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexForward(a, idx1 + r * DoubleFFT_3D.this.rowStridel);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.rowsl, false);

                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * DoubleFFT_3D.this.sliceStridel;

                        for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                           long idx2 = 2L * c;

                           for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStridel;
                              long idx4 = 2L * r;
                              temp.setDouble(idx4, a.getDouble(idx3));
                              temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                           }

                           DoubleFFT_3D.this.fftRows.complexForward(temp);

                           for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStridel;
                              long idx4 = 2L * r;
                              a.setDouble(idx3, temp.getDouble(idx4));
                              a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstRow = (long)l * p;
               final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.slicesl, false);

                     for(long r = firstRow; r < lastRow; ++r) {
                        long idx1 = r * DoubleFFT_3D.this.rowStridel;

                        for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                           long idx2 = 2L * c;

                           for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                              long idx3 = s * DoubleFFT_3D.this.sliceStridel + idx1 + idx2;
                              long idx4 = 2L * s;
                              temp.setDouble(idx4, a.getDouble(idx3));
                              temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                           }

                           DoubleFFT_3D.this.fftSlices.complexForward(temp);

                           for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                              long idx3 = s * DoubleFFT_3D.this.sliceStridel + idx1 + idx2;
                              long idx4 = 2L * s;
                              a.setDouble(idx3, temp.getDouble(idx4));
                              a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * this.sliceStridel;

               for(long r = 0L; r < this.rowsl; ++r) {
                  this.fftColumns.complexForward(a, idx1 + r * this.rowStridel);
               }
            }

            DoubleLargeArray temp = new DoubleLargeArray(2L * this.rowsl, false);

            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * this.sliceStridel;

               for(long c = 0L; c < this.columnsl; ++c) {
                  long idx2 = 2L * c;

                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx3 = idx1 + idx2 + r * this.rowStridel;
                     long idx4 = 2L * r;
                     temp.setDouble(idx4, a.getDouble(idx3));
                     temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                  }

                  this.fftRows.complexForward(temp);

                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx3 = idx1 + idx2 + r * this.rowStridel;
                     long idx4 = 2L * r;
                     a.setDouble(idx3, temp.getDouble(idx4));
                     a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                  }
               }
            }

            temp = new DoubleLargeArray(2L * this.slicesl, false);

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.rowStridel;

               for(long c = 0L; c < this.columnsl; ++c) {
                  long idx2 = 2L * c;

                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx3 = s * this.sliceStridel + idx1 + idx2;
                     long idx4 = 2L * s;
                     temp.setDouble(idx4, a.getDouble(idx3));
                     temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                  }

                  this.fftSlices.complexForward(temp);

                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx3 = s * this.sliceStridel + idx1 + idx2;
                     long idx4 = 2L * s;
                     a.setDouble(idx3, temp.getDouble(idx4));
                     a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                  }
               }
            }
         }

         this.sliceStridel = this.rowsl * this.columnsl;
         this.rowStridel = this.columnsl;
      }

   }

   public void complexForward(final double[][][] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         int oldn3 = this.columns;
         this.columns = 2 * this.columns;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(0, -1, (double[][][])a, true);
            this.cdft3db_subth(-1, (double[][][])a, true);
         } else {
            this.xdft3da_sub2(0, -1, (double[][][])a, true);
            this.cdft3db_sub(-1, (double[][][])a, true);
         }

         this.columns = oldn3;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
      } else if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        DoubleFFT_3D.this.fftColumns.complexForward(a[s][r]);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           temp[idx4] = a[s][r][idx2];
                           temp[idx4 + 1] = a[s][r][idx2 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(temp);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           a[s][r][idx2] = temp[idx4];
                           a[s][r][idx2 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx4 = 2 * s;
                           temp[idx4] = a[s][r][idx2];
                           temp[idx4 + 1] = a[s][r][idx2 + 1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(temp);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx4 = 2 * s;
                           a[s][r][idx2] = temp[idx4];
                           a[s][r][idx2 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexForward(a[s][r]);
            }
         }

         double[] temp = new double[2 * this.rows];

         for(int s = 0; s < this.slices; ++s) {
            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  temp[idx4] = a[s][r][idx2];
                  temp[idx4 + 1] = a[s][r][idx2 + 1];
               }

               this.fftRows.complexForward(temp);

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  a[s][r][idx2] = temp[idx4];
                  a[s][r][idx2 + 1] = temp[idx4 + 1];
               }
            }
         }

         temp = new double[2 * this.slices];

         for(int r = 0; r < this.rows; ++r) {
            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int s = 0; s < this.slices; ++s) {
                  int idx4 = 2 * s;
                  temp[idx4] = a[s][r][idx2];
                  temp[idx4 + 1] = a[s][r][idx2 + 1];
               }

               this.fftSlices.complexForward(temp);

               for(int s = 0; s < this.slices; ++s) {
                  int idx4 = 2 * s;
                  a[s][r][idx2] = temp[idx4];
                  a[s][r][idx2 + 1] = temp[idx4 + 1];
               }
            }
         }
      }

   }

   public void complexInverse(final double[] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         int oldn3 = this.columns;
         this.columns = 2 * this.columns;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(0, 1, (double[])a, scale);
            this.cdft3db_subth(1, (double[])a, scale);
         } else {
            this.xdft3da_sub2(0, 1, (double[])a, scale);
            this.cdft3db_sub(1, (double[])a, scale);
         }

         this.columns = oldn3;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
      } else {
         this.sliceStride = 2 * this.rows * this.columns;
         this.rowStride = 2 * this.columns;
         if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.slices / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexInverse(a, idx1 + r * DoubleFFT_3D.this.rowStride, scale);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx2 = 2 * c;

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStride;
                              int idx4 = 2 * r;
                              temp[idx4] = a[idx3];
                              temp[idx4 + 1] = a[idx3 + 1];
                           }

                           DoubleFFT_3D.this.fftRows.complexInverse(temp, scale);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStride;
                              int idx4 = 2 * r;
                              a[idx3] = temp[idx4];
                              a[idx3 + 1] = temp[idx4 + 1];
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                     for(int r = firstRow; r < lastRow; ++r) {
                        int idx1 = r * DoubleFFT_3D.this.rowStride;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx2 = 2 * c;

                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx3 = s * DoubleFFT_3D.this.sliceStride + idx1 + idx2;
                              int idx4 = 2 * s;
                              temp[idx4] = a[idx3];
                              temp[idx4 + 1] = a[idx3 + 1];
                           }

                           DoubleFFT_3D.this.fftSlices.complexInverse(temp, scale);

                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx3 = s * DoubleFFT_3D.this.sliceStride + idx1 + idx2;
                              int idx4 = 2 * s;
                              a[idx3] = temp[idx4];
                              a[idx3 + 1] = temp[idx4 + 1];
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride;

               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexInverse(a, idx1 + r * this.rowStride, scale);
               }
            }

            double[] temp = new double[2 * this.rows];

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride;

               for(int c = 0; c < this.columns; ++c) {
                  int idx2 = 2 * c;

                  for(int r = 0; r < this.rows; ++r) {
                     int idx3 = idx1 + idx2 + r * this.rowStride;
                     int idx4 = 2 * r;
                     temp[idx4] = a[idx3];
                     temp[idx4 + 1] = a[idx3 + 1];
                  }

                  this.fftRows.complexInverse(temp, scale);

                  for(int r = 0; r < this.rows; ++r) {
                     int idx3 = idx1 + idx2 + r * this.rowStride;
                     int idx4 = 2 * r;
                     a[idx3] = temp[idx4];
                     a[idx3 + 1] = temp[idx4 + 1];
                  }
               }
            }

            temp = new double[2 * this.slices];

            for(int r = 0; r < this.rows; ++r) {
               int idx1 = r * this.rowStride;

               for(int c = 0; c < this.columns; ++c) {
                  int idx2 = 2 * c;

                  for(int s = 0; s < this.slices; ++s) {
                     int idx3 = s * this.sliceStride + idx1 + idx2;
                     int idx4 = 2 * s;
                     temp[idx4] = a[idx3];
                     temp[idx4 + 1] = a[idx3 + 1];
                  }

                  this.fftSlices.complexInverse(temp, scale);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx3 = s * this.sliceStride + idx1 + idx2;
                     int idx4 = 2 * s;
                     a[idx3] = temp[idx4];
                     a[idx3 + 1] = temp[idx4 + 1];
                  }
               }
            }
         }

         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
      }

   }

   public void complexInverse(final DoubleLargeArray a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         long oldn3 = this.columnsl;
         this.columnsl = 2L * this.columnsl;
         this.sliceStridel = this.rowsl * this.columnsl;
         this.rowStridel = this.columnsl;
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(0L, 1, a, scale);
            this.cdft3db_subth(1, (DoubleLargeArray)a, scale);
         } else {
            this.xdft3da_sub2(0L, 1, a, scale);
            this.cdft3db_sub(1, (DoubleLargeArray)a, scale);
         }

         this.columnsl = oldn3;
         this.sliceStridel = this.rowsl * this.columnsl;
         this.rowStridel = this.columnsl;
      } else {
         this.sliceStridel = 2L * this.rowsl * this.columnsl;
         this.rowStridel = 2L * this.columnsl;
         if (nthreads > 1 && this.useThreads && this.slicesl >= (long)nthreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.slicesl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * DoubleFFT_3D.this.sliceStridel;

                        for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexInverse(a, idx1 + r * DoubleFFT_3D.this.rowStridel, scale);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.rowsl, false);

                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * DoubleFFT_3D.this.sliceStridel;

                        for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                           long idx2 = 2L * c;

                           for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStridel;
                              long idx4 = 2L * r;
                              temp.setDouble(idx4, a.getDouble(idx3));
                              temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                           }

                           DoubleFFT_3D.this.fftRows.complexInverse(temp, scale);

                           for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + idx2 + r * DoubleFFT_3D.this.rowStridel;
                              long idx4 = 2L * r;
                              a.setDouble(idx3, temp.getDouble(idx4));
                              a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstRow = (long)l * p;
               final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.slicesl, false);

                     for(long r = firstRow; r < lastRow; ++r) {
                        long idx1 = r * DoubleFFT_3D.this.rowStridel;

                        for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                           long idx2 = 2L * c;

                           for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                              long idx3 = s * DoubleFFT_3D.this.sliceStridel + idx1 + idx2;
                              long idx4 = 2L * s;
                              temp.setDouble(idx4, a.getDouble(idx3));
                              temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                           }

                           DoubleFFT_3D.this.fftSlices.complexInverse(temp, scale);

                           for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                              long idx3 = s * DoubleFFT_3D.this.sliceStridel + idx1 + idx2;
                              long idx4 = 2L * s;
                              a.setDouble(idx3, temp.getDouble(idx4));
                              a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                           }
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * this.sliceStridel;

               for(long r = 0L; r < this.rowsl; ++r) {
                  this.fftColumns.complexInverse(a, idx1 + r * this.rowStridel, scale);
               }
            }

            DoubleLargeArray temp = new DoubleLargeArray(2L * this.rowsl, false);

            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * this.sliceStridel;

               for(long c = 0L; c < this.columnsl; ++c) {
                  long idx2 = 2L * c;

                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx3 = idx1 + idx2 + r * this.rowStridel;
                     long idx4 = 2L * r;
                     temp.setDouble(idx4, a.getDouble(idx3));
                     temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                  }

                  this.fftRows.complexInverse(temp, scale);

                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx3 = idx1 + idx2 + r * this.rowStridel;
                     long idx4 = 2L * r;
                     a.setDouble(idx3, temp.getDouble(idx4));
                     a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                  }
               }
            }

            temp = new DoubleLargeArray(2L * this.slicesl, false);

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx1 = r * this.rowStridel;

               for(long c = 0L; c < this.columnsl; ++c) {
                  long idx2 = 2L * c;

                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx3 = s * this.sliceStridel + idx1 + idx2;
                     long idx4 = 2L * s;
                     temp.setDouble(idx4, a.getDouble(idx3));
                     temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                  }

                  this.fftSlices.complexInverse(temp, scale);

                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx3 = s * this.sliceStridel + idx1 + idx2;
                     long idx4 = 2L * s;
                     a.setDouble(idx3, temp.getDouble(idx4));
                     a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                  }
               }
            }
         }

         this.sliceStridel = this.rowsl * this.columnsl;
         this.rowStridel = this.columnsl;
      }

   }

   public void complexInverse(final double[][][] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         int oldn3 = this.columns;
         this.columns = 2 * this.columns;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(0, 1, (double[][][])a, scale);
            this.cdft3db_subth(1, (double[][][])a, scale);
         } else {
            this.xdft3da_sub2(0, 1, (double[][][])a, scale);
            this.cdft3db_sub(1, (double[][][])a, scale);
         }

         this.columns = oldn3;
         this.sliceStride = this.rows * this.columns;
         this.rowStride = this.columns;
      } else if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        DoubleFFT_3D.this.fftColumns.complexInverse(a[s][r], scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           temp[idx4] = a[s][r][idx2];
                           temp[idx4 + 1] = a[s][r][idx2 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(temp, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           a[s][r][idx2] = temp[idx4];
                           a[s][r][idx2 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx4 = 2 * s;
                           temp[idx4] = a[s][r][idx2];
                           temp[idx4 + 1] = a[s][r][idx2 + 1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexInverse(temp, scale);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx4 = 2 * s;
                           a[s][r][idx2] = temp[idx4];
                           a[s][r][idx2 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.complexInverse(a[s][r], scale);
            }
         }

         double[] temp = new double[2 * this.rows];

         for(int s = 0; s < this.slices; ++s) {
            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  temp[idx4] = a[s][r][idx2];
                  temp[idx4 + 1] = a[s][r][idx2 + 1];
               }

               this.fftRows.complexInverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  a[s][r][idx2] = temp[idx4];
                  a[s][r][idx2 + 1] = temp[idx4 + 1];
               }
            }
         }

         temp = new double[2 * this.slices];

         for(int r = 0; r < this.rows; ++r) {
            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int s = 0; s < this.slices; ++s) {
                  int idx4 = 2 * s;
                  temp[idx4] = a[s][r][idx2];
                  temp[idx4 + 1] = a[s][r][idx2 + 1];
               }

               this.fftSlices.complexInverse(temp, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx4 = 2 * s;
                  a[s][r][idx2] = temp[idx4];
                  a[s][r][idx2 + 1] = temp[idx4 + 1];
               }
            }
         }
      }

   }

   public void realForward(double[] a) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth1(1, -1, (double[])a, true);
            this.cdft3db_subth(-1, (double[])a, true);
            this.rdft3d_sub(1, (double[])a);
         } else {
            this.xdft3da_sub1(1, -1, (double[])a, true);
            this.cdft3db_sub(-1, (double[])a, true);
            this.rdft3d_sub(1, (double[])a);
         }

      }
   }

   public void realForward(DoubleLargeArray a) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth1(1L, -1, a, true);
            this.cdft3db_subth(-1, (DoubleLargeArray)a, true);
            this.rdft3d_sub(1, (DoubleLargeArray)a);
         } else {
            this.xdft3da_sub1(1L, -1, a, true);
            this.cdft3db_sub(-1, (DoubleLargeArray)a, true);
            this.rdft3d_sub(1, (DoubleLargeArray)a);
         }

      }
   }

   public void realForward(double[][][] a) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth1(1, -1, (double[][][])a, true);
            this.cdft3db_subth(-1, (double[][][])a, true);
            this.rdft3d_sub(1, (double[][][])a);
         } else {
            this.xdft3da_sub1(1, -1, (double[][][])a, true);
            this.cdft3db_sub(-1, (double[][][])a, true);
            this.rdft3d_sub(1, (double[][][])a);
         }

      }
   }

   public void realForwardFull(double[] a) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(1, -1, (double[])a, true);
            this.cdft3db_subth(-1, (double[])a, true);
            this.rdft3d_sub(1, (double[])a);
         } else {
            this.xdft3da_sub2(1, -1, (double[])a, true);
            this.cdft3db_sub(-1, (double[])a, true);
            this.rdft3d_sub(1, (double[])a);
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
            this.xdft3da_subth2(1L, -1, a, true);
            this.cdft3db_subth(-1, (DoubleLargeArray)a, true);
            this.rdft3d_sub(1, (DoubleLargeArray)a);
         } else {
            this.xdft3da_sub2(1L, -1, a, true);
            this.cdft3db_sub(-1, (DoubleLargeArray)a, true);
            this.rdft3d_sub(1, (DoubleLargeArray)a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealForwardFull(a);
      }

   }

   public void realForwardFull(double[][][] a) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(1, -1, (double[][][])a, true);
            this.cdft3db_subth(-1, (double[][][])a, true);
            this.rdft3d_sub(1, (double[][][])a);
         } else {
            this.xdft3da_sub2(1, -1, (double[][][])a, true);
            this.cdft3db_sub(-1, (double[][][])a, true);
            this.rdft3d_sub(1, (double[][][])a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealForwardFull(a);
      }

   }

   public void realInverse(double[] a, boolean scale) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.rdft3d_sub(-1, (double[])a);
            this.cdft3db_subth(1, (double[])a, scale);
            this.xdft3da_subth1(1, 1, (double[])a, scale);
         } else {
            this.rdft3d_sub(-1, (double[])a);
            this.cdft3db_sub(1, (double[])a, scale);
            this.xdft3da_sub1(1, 1, (double[])a, scale);
         }

      }
   }

   public void realInverse(DoubleLargeArray a, boolean scale) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.rdft3d_sub(-1, (DoubleLargeArray)a);
            this.cdft3db_subth(1, (DoubleLargeArray)a, scale);
            this.xdft3da_subth1(1L, 1, a, scale);
         } else {
            this.rdft3d_sub(-1, (DoubleLargeArray)a);
            this.cdft3db_sub(1, (DoubleLargeArray)a, scale);
            this.xdft3da_sub1(1L, 1, a, scale);
         }

      }
   }

   public void realInverse(double[][][] a, boolean scale) {
      if (!this.isPowerOfTwo) {
         throw new IllegalArgumentException("slices, rows and columns must be power of two numbers");
      } else {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.rdft3d_sub(-1, (double[][][])a);
            this.cdft3db_subth(1, (double[][][])a, scale);
            this.xdft3da_subth1(1, 1, (double[][][])a, scale);
         } else {
            this.rdft3d_sub(-1, (double[][][])a);
            this.cdft3db_sub(1, (double[][][])a, scale);
            this.xdft3da_sub1(1, 1, (double[][][])a, scale);
         }

      }
   }

   public void realInverseFull(double[] a, boolean scale) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(1, 1, (double[])a, scale);
            this.cdft3db_subth(1, (double[])a, scale);
            this.rdft3d_sub(1, (double[])a);
         } else {
            this.xdft3da_sub2(1, 1, (double[])a, scale);
            this.cdft3db_sub(1, (double[])a, scale);
            this.rdft3d_sub(1, (double[])a);
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
            this.xdft3da_subth2(1L, 1, a, scale);
            this.cdft3db_subth(1, (DoubleLargeArray)a, scale);
            this.rdft3d_sub(1, (DoubleLargeArray)a);
         } else {
            this.xdft3da_sub2(1L, 1, a, scale);
            this.cdft3db_sub(1, (DoubleLargeArray)a, scale);
            this.rdft3d_sub(1, (DoubleLargeArray)a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealInverseFull(a, scale);
      }

   }

   public void realInverseFull(double[][][] a, boolean scale) {
      if (this.isPowerOfTwo) {
         int nthreads = ConcurrencyUtils.getNumberOfThreads();
         if (nthreads > 1 && this.useThreads) {
            this.xdft3da_subth2(1, 1, (double[][][])a, scale);
            this.cdft3db_subth(1, (double[][][])a, scale);
            this.rdft3d_sub(1, (double[][][])a);
         } else {
            this.xdft3da_sub2(1, 1, (double[][][])a, scale);
            this.cdft3db_sub(1, (double[][][])a, scale);
            this.rdft3d_sub(1, (double[][][])a);
         }

         this.fillSymmetric(a);
      } else {
         this.mixedRadixRealInverseFull(a, scale);
      }

   }

   private void mixedRadixRealForwardFull(final double[][][] a) {
      double[] temp = new double[2 * this.rows];
      int ldimn2 = this.rows / 2 + 1;
      final int newn3 = 2 * this.columns;
      final int n2d2;
      if (this.rows % 2 == 0) {
         n2d2 = this.rows / 2;
      } else {
         n2d2 = (this.rows + 1) / 2;
      }

      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.columns >= nthreads && ldimn2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        DoubleFFT_3D.this.fftColumns.realForwardFull(a[s][r]);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           temp[idx4] = a[s][r][idx2];
                           temp[idx4 + 1] = a[s][r][idx2 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(temp);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           a[s][r][idx2] = temp[idx4];
                           a[s][r][idx2 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = ldimn2 / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? ldimn2 : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx1 = 2 * c;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           temp[idx2] = a[s][r][idx1];
                           temp[idx2 + 1] = a[s][r][idx1 + 1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(temp);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           a[s][r][idx1] = temp[idx2];
                           a[s][r][idx1 + 1] = temp[idx2 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx2 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx4 = DoubleFFT_3D.this.rows - r;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx1 = 2 * c;
                           int idx3 = newn3 - idx1;
                           a[idx2][idx4][idx3 % newn3] = a[s][r][idx1];
                           a[idx2][idx4][(idx3 + 1) % newn3] = -a[s][r][idx1 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realForwardFull(a[s][r]);
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  temp[idx4] = a[s][r][idx2];
                  temp[idx4 + 1] = a[s][r][idx2 + 1];
               }

               this.fftRows.complexForward(temp);

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  a[s][r][idx2] = temp[idx4];
                  a[s][r][idx2 + 1] = temp[idx4 + 1];
               }
            }
         }

         temp = new double[2 * this.slices];

         for(int r = 0; r < ldimn2; ++r) {
            for(int c = 0; c < this.columns; ++c) {
               int idx1 = 2 * c;

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  temp[idx2] = a[s][r][idx1];
                  temp[idx2 + 1] = a[s][r][idx1 + 1];
               }

               this.fftSlices.complexForward(temp);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  a[s][r][idx1] = temp[idx2];
                  a[s][r][idx1 + 1] = temp[idx2 + 1];
               }
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx2 = (this.slices - s) % this.slices;

            for(int r = 1; r < n2d2; ++r) {
               int idx4 = this.rows - r;

               for(int c = 0; c < this.columns; ++c) {
                  int idx1 = 2 * c;
                  int idx3 = newn3 - idx1;
                  a[idx2][idx4][idx3 % newn3] = a[s][r][idx1];
                  a[idx2][idx4][(idx3 + 1) % newn3] = -a[s][r][idx1 + 1];
               }
            }
         }
      }

   }

   private void mixedRadixRealInverseFull(final double[][][] a, final boolean scale) {
      double[] temp = new double[2 * this.rows];
      int ldimn2 = this.rows / 2 + 1;
      final int newn3 = 2 * this.columns;
      final int n2d2;
      if (this.rows % 2 == 0) {
         n2d2 = this.rows / 2;
      } else {
         n2d2 = (this.rows + 1) / 2;
      }

      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.columns >= nthreads && ldimn2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        DoubleFFT_3D.this.fftColumns.realInverseFull(a[s][r], scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           temp[idx4] = a[s][r][idx2];
                           temp[idx4 + 1] = a[s][r][idx2 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(temp, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx4 = 2 * r;
                           a[s][r][idx2] = temp[idx4];
                           a[s][r][idx2 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = ldimn2 / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? ldimn2 : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx1 = 2 * c;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           temp[idx2] = a[s][r][idx1];
                           temp[idx2 + 1] = a[s][r][idx1 + 1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexInverse(temp, scale);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           a[s][r][idx1] = temp[idx2];
                           a[s][r][idx1 + 1] = temp[idx2 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx2 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx4 = DoubleFFT_3D.this.rows - r;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx1 = 2 * c;
                           int idx3 = newn3 - idx1;
                           a[idx2][idx4][idx3 % newn3] = a[s][r][idx1];
                           a[idx2][idx4][(idx3 + 1) % newn3] = -a[s][r][idx1 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.fftColumns.realInverseFull(a[s][r], scale);
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  temp[idx4] = a[s][r][idx2];
                  temp[idx4 + 1] = a[s][r][idx2 + 1];
               }

               this.fftRows.complexInverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  a[s][r][idx2] = temp[idx4];
                  a[s][r][idx2 + 1] = temp[idx4 + 1];
               }
            }
         }

         temp = new double[2 * this.slices];

         for(int r = 0; r < ldimn2; ++r) {
            for(int c = 0; c < this.columns; ++c) {
               int idx1 = 2 * c;

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  temp[idx2] = a[s][r][idx1];
                  temp[idx2 + 1] = a[s][r][idx1 + 1];
               }

               this.fftSlices.complexInverse(temp, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  a[s][r][idx1] = temp[idx2];
                  a[s][r][idx1 + 1] = temp[idx2 + 1];
               }
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx2 = (this.slices - s) % this.slices;

            for(int r = 1; r < n2d2; ++r) {
               int idx4 = this.rows - r;

               for(int c = 0; c < this.columns; ++c) {
                  int idx1 = 2 * c;
                  int idx3 = newn3 - idx1;
                  a[idx2][idx4][idx3 % newn3] = a[s][r][idx1];
                  a[idx2][idx4][(idx3 + 1) % newn3] = -a[s][r][idx1 + 1];
               }
            }
         }
      }

   }

   private void mixedRadixRealForwardFull(final double[] a) {
      final int twon3 = 2 * this.columns;
      double[] temp = new double[twon3];
      int ldimn2 = this.rows / 2 + 1;
      final int n2d2;
      if (this.rows % 2 == 0) {
         n2d2 = this.rows / 2;
      } else {
         n2d2 = (this.rows + 1) / 2;
      }

      final int twoSliceStride = 2 * this.sliceStride;
      final int twoRowStride = 2 * this.rowStride;
      int n1d2 = this.slices / 2;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && n1d2 >= nthreads && this.columns >= nthreads && ldimn2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = n1d2 / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = this.slices - 1 - l * p;
            final int lastSlice = l == nthreads - 1 ? n1d2 + 1 : firstSlice - p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[twon3];

                  for(int s = firstSlice; s >= lastSlice; --s) {
                     int idx1 = s * DoubleFFT_3D.this.sliceStride;
                     int idx2 = s * twoSliceStride;

                     for(int r = DoubleFFT_3D.this.rows - 1; r >= 0; --r) {
                        System.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStride, temp, 0, DoubleFFT_3D.this.columns);
                        DoubleFFT_3D.this.fftColumns.realForwardFull(temp);
                        System.arraycopy(temp, 0, a, idx2 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         final double[][][] temp2 = new double[n1d2 + 1][this.rows][twon3];

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? n1d2 + 1 : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * DoubleFFT_3D.this.sliceStride;

                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        System.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStride, temp2[s][r], 0, DoubleFFT_3D.this.columns);
                        DoubleFFT_3D.this.fftColumns.realForwardFull(temp2[s][r]);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? n1d2 + 1 : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * twoSliceStride;

                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        System.arraycopy(temp2[s][r], 0, a, idx1 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * twoSliceStride;

                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * twoRowStride + idx2;
                           int idx4 = 2 * r;
                           temp[idx4] = a[idx3];
                           temp[idx4 + 1] = a[idx3 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(temp);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * twoRowStride + idx2;
                           int idx4 = 2 * r;
                           a[idx3] = temp[idx4];
                           a[idx3 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = ldimn2 / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? ldimn2 : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx3 = r * twoRowStride;

                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx1 = 2 * c;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx4 = s * twoSliceStride + idx3 + idx1;
                           temp[idx2] = a[idx4];
                           temp[idx2 + 1] = a[idx4 + 1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(temp);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx4 = s * twoSliceStride + idx3 + idx1;
                           a[idx4] = temp[idx2];
                           a[idx4 + 1] = temp[idx2 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx2 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices;
                     int idx5 = idx2 * twoSliceStride;
                     int idx6 = s * twoSliceStride;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx4 = DoubleFFT_3D.this.rows - r;
                        int idx7 = idx4 * twoRowStride;
                        int idx8 = r * twoRowStride;
                        int idx9 = idx5 + idx7;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx1 = 2 * c;
                           int idx3 = twon3 - idx1;
                           int idx10 = idx6 + idx8 + idx1;
                           a[idx9 + idx3 % twon3] = a[idx10];
                           a[idx9 + (idx3 + 1) % twon3] = -a[idx10 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = this.slices - 1; s >= 0; --s) {
            int idx1 = s * this.sliceStride;
            int idx2 = s * twoSliceStride;

            for(int r = this.rows - 1; r >= 0; --r) {
               System.arraycopy(a, idx1 + r * this.rowStride, temp, 0, this.columns);
               this.fftColumns.realForwardFull(temp);
               System.arraycopy(temp, 0, a, idx2 + r * twoRowStride, twon3);
            }
         }

         temp = new double[2 * this.rows];

         for(int s = 0; s < this.slices; ++s) {
            int idx1 = s * twoSliceStride;

            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  int idx3 = idx1 + r * twoRowStride + idx2;
                  temp[idx4] = a[idx3];
                  temp[idx4 + 1] = a[idx3 + 1];
               }

               this.fftRows.complexForward(temp);

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  int idx3 = idx1 + r * twoRowStride + idx2;
                  a[idx3] = temp[idx4];
                  a[idx3 + 1] = temp[idx4 + 1];
               }
            }
         }

         temp = new double[2 * this.slices];

         for(int r = 0; r < ldimn2; ++r) {
            int idx3 = r * twoRowStride;

            for(int c = 0; c < this.columns; ++c) {
               int idx1 = 2 * c;

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx4 = s * twoSliceStride + idx3 + idx1;
                  temp[idx2] = a[idx4];
                  temp[idx2 + 1] = a[idx4 + 1];
               }

               this.fftSlices.complexForward(temp);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx4 = s * twoSliceStride + idx3 + idx1;
                  a[idx4] = temp[idx2];
                  a[idx4 + 1] = temp[idx2 + 1];
               }
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx2 = (this.slices - s) % this.slices;
            int idx5 = idx2 * twoSliceStride;
            int idx6 = s * twoSliceStride;

            for(int r = 1; r < n2d2; ++r) {
               int idx4 = this.rows - r;
               int idx7 = idx4 * twoRowStride;
               int idx8 = r * twoRowStride;
               int idx9 = idx5 + idx7;

               for(int c = 0; c < this.columns; ++c) {
                  int idx1 = 2 * c;
                  int idx3 = twon3 - idx1;
                  int idx10 = idx6 + idx8 + idx1;
                  a[idx9 + idx3 % twon3] = a[idx10];
                  a[idx9 + (idx3 + 1) % twon3] = -a[idx10 + 1];
               }
            }
         }
      }

   }

   private void mixedRadixRealForwardFull(final DoubleLargeArray a) {
      final long twon3 = 2L * this.columnsl;
      DoubleLargeArray temp = new DoubleLargeArray(twon3);
      long ldimn2 = this.rowsl / 2L + 1L;
      final long n2d2;
      if (this.rowsl % 2L == 0L) {
         n2d2 = this.rowsl / 2L;
      } else {
         n2d2 = (this.rowsl + 1L) / 2L;
      }

      final long twoSliceStride = 2L * this.sliceStridel;
      final long twoRowStride = 2L * this.rowStridel;
      long n1d2 = this.slicesl / 2L;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && n1d2 >= (long)nthreads && this.columnsl >= (long)nthreads && ldimn2 >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = n1d2 / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = this.slicesl - 1L - (long)l * p;
            final long lastSlice = l == nthreads - 1 ? n1d2 + 1L : firstSlice - p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(twon3);

                  for(long s = firstSlice; s >= lastSlice; --s) {
                     long idx1 = s * DoubleFFT_3D.this.sliceStridel;
                     long idx2 = s * twoSliceStride;

                     for(long r = DoubleFFT_3D.this.rowsl - 1L; r >= 0L; --r) {
                        LargeArrayUtils.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStridel, temp, 0L, DoubleFFT_3D.this.columnsl);
                        DoubleFFT_3D.this.fftColumns.realForwardFull(temp);
                        LargeArrayUtils.arraycopy(temp, 0L, a, idx2 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         final DoubleLargeArray temp2 = new DoubleLargeArray((n1d2 + 1L) * this.rowsl * twon3);

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? n1d2 + 1L : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * DoubleFFT_3D.this.sliceStridel;

                     for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                        LargeArrayUtils.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStridel, temp2, s * DoubleFFT_3D.this.rowsl * twon3 + r * twon3, DoubleFFT_3D.this.columnsl);
                        DoubleFFT_3D.this.fftColumns.realForwardFull(temp2, s * DoubleFFT_3D.this.rowsl * twon3 + r * twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? n1d2 + 1L : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * twoSliceStride;

                     for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                        LargeArrayUtils.arraycopy(temp2, s * DoubleFFT_3D.this.rowsl * twon3 + r * twon3, a, idx1 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slicesl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.rowsl, false);

                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * twoSliceStride;

                     for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                        long idx2 = 2L * c;

                        for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * twoRowStride + idx2;
                           long idx4 = 2L * r;
                           temp.setDouble(idx4, a.getDouble(idx3));
                           temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(temp);

                        for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * twoRowStride + idx2;
                           long idx4 = 2L * r;
                           a.setDouble(idx3, temp.getDouble(idx4));
                           a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = ldimn2 / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? ldimn2 : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.slicesl, false);

                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx3 = r * twoRowStride;

                     for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                        long idx1 = 2L * c;

                        for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                           long idx2 = 2L * s;
                           long idx4 = s * twoSliceStride + idx3 + idx1;
                           temp.setDouble(idx2, a.getDouble(idx4));
                           temp.setDouble(idx2 + 1L, a.getDouble(idx4 + 1L));
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(temp);

                        for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                           long idx2 = 2L * s;
                           long idx4 = s * twoSliceStride + idx3 + idx1;
                           a.setDouble(idx4, temp.getDouble(idx2));
                           a.setDouble(idx4 + 1L, temp.getDouble(idx2 + 1L));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slicesl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx2 = (DoubleFFT_3D.this.slicesl - s) % DoubleFFT_3D.this.slicesl;
                     long idx5 = idx2 * twoSliceStride;
                     long idx6 = s * twoSliceStride;

                     for(long r = 1L; r < n2d2; ++r) {
                        long idx4 = DoubleFFT_3D.this.rowsl - r;
                        long idx7 = idx4 * twoRowStride;
                        long idx8 = r * twoRowStride;
                        long idx9 = idx5 + idx7;

                        for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                           long idx1 = 2L * c;
                           long idx3 = twon3 - idx1;
                           long idx10 = idx6 + idx8 + idx1;
                           a.setDouble(idx9 + idx3 % twon3, a.getDouble(idx10));
                           a.setDouble(idx9 + (idx3 + 1L) % twon3, -a.getDouble(idx10 + 1L));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long s = this.slicesl - 1L; s >= 0L; --s) {
            long idx1 = s * this.sliceStridel;
            long idx2 = s * twoSliceStride;

            for(long r = this.rowsl - 1L; r >= 0L; --r) {
               LargeArrayUtils.arraycopy(a, idx1 + r * this.rowStridel, temp, 0L, this.columnsl);
               this.fftColumns.realForwardFull(temp);
               LargeArrayUtils.arraycopy(temp, 0L, a, idx2 + r * twoRowStride, twon3);
            }
         }

         temp = new DoubleLargeArray(2L * this.rowsl, false);

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx1 = s * twoSliceStride;

            for(long c = 0L; c < this.columnsl; ++c) {
               long idx2 = 2L * c;

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx4 = 2L * r;
                  long idx3 = idx1 + r * twoRowStride + idx2;
                  temp.setDouble(idx4, a.getDouble(idx3));
                  temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
               }

               this.fftRows.complexForward(temp);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx4 = 2L * r;
                  long idx3 = idx1 + r * twoRowStride + idx2;
                  a.setDouble(idx3, temp.getDouble(idx4));
                  a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
               }
            }
         }

         temp = new DoubleLargeArray(2L * this.slicesl, false);

         for(long r = 0L; r < ldimn2; ++r) {
            long idx3 = r * twoRowStride;

            for(long c = 0L; c < this.columnsl; ++c) {
               long idx1 = 2L * c;

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx2 = 2L * s;
                  long idx4 = s * twoSliceStride + idx3 + idx1;
                  temp.setDouble(idx2, a.getDouble(idx4));
                  temp.setDouble(idx2 + 1L, a.getDouble(idx4 + 1L));
               }

               this.fftSlices.complexForward(temp);

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx2 = 2L * s;
                  long idx4 = s * twoSliceStride + idx3 + idx1;
                  a.setDouble(idx4, temp.getDouble(idx2));
                  a.setDouble(idx4 + 1L, temp.getDouble(idx2 + 1L));
               }
            }
         }

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx2 = (this.slicesl - s) % this.slicesl;
            long idx5 = idx2 * twoSliceStride;
            long idx6 = s * twoSliceStride;

            for(long r = 1L; r < n2d2; ++r) {
               long idx4 = this.rowsl - r;
               long idx7 = idx4 * twoRowStride;
               long idx8 = r * twoRowStride;
               long idx9 = idx5 + idx7;

               for(long c = 0L; c < this.columnsl; ++c) {
                  long idx1 = 2L * c;
                  long idx3 = twon3 - idx1;
                  long idx10 = idx6 + idx8 + idx1;
                  a.setDouble(idx9 + idx3 % twon3, a.getDouble(idx10));
                  a.setDouble(idx9 + (idx3 + 1L) % twon3, -a.getDouble(idx10 + 1L));
               }
            }
         }
      }

   }

   private void mixedRadixRealInverseFull(final double[] a, final boolean scale) {
      final int twon3 = 2 * this.columns;
      double[] temp = new double[twon3];
      int ldimn2 = this.rows / 2 + 1;
      final int n2d2;
      if (this.rows % 2 == 0) {
         n2d2 = this.rows / 2;
      } else {
         n2d2 = (this.rows + 1) / 2;
      }

      final int twoSliceStride = 2 * this.sliceStride;
      final int twoRowStride = 2 * this.rowStride;
      int n1d2 = this.slices / 2;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && n1d2 >= nthreads && this.columns >= nthreads && ldimn2 >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = n1d2 / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = this.slices - 1 - l * p;
            final int lastSlice = l == nthreads - 1 ? n1d2 + 1 : firstSlice - p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[twon3];

                  for(int s = firstSlice; s >= lastSlice; --s) {
                     int idx1 = s * DoubleFFT_3D.this.sliceStride;
                     int idx2 = s * twoSliceStride;

                     for(int r = DoubleFFT_3D.this.rows - 1; r >= 0; --r) {
                        System.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStride, temp, 0, DoubleFFT_3D.this.columns);
                        DoubleFFT_3D.this.fftColumns.realInverseFull(temp, scale);
                        System.arraycopy(temp, 0, a, idx2 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         final double[][][] temp2 = new double[n1d2 + 1][this.rows][twon3];

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? n1d2 + 1 : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * DoubleFFT_3D.this.sliceStride;

                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        System.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStride, temp2[s][r], 0, DoubleFFT_3D.this.columns);
                        DoubleFFT_3D.this.fftColumns.realInverseFull(temp2[s][r], scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? n1d2 + 1 : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * twoSliceStride;

                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        System.arraycopy(temp2[s][r], 0, a, idx1 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * twoSliceStride;

                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx2 = 2 * c;

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * twoRowStride + idx2;
                           int idx4 = 2 * r;
                           temp[idx4] = a[idx3];
                           temp[idx4 + 1] = a[idx3 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(temp, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * twoRowStride + idx2;
                           int idx4 = 2 * r;
                           a[idx3] = temp[idx4];
                           a[idx3 + 1] = temp[idx4 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = ldimn2 / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? ldimn2 : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  double[] temp = new double[2 * DoubleFFT_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx3 = r * twoRowStride;

                     for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                        int idx1 = 2 * c;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx4 = s * twoSliceStride + idx3 + idx1;
                           temp[idx2] = a[idx4];
                           temp[idx2 + 1] = a[idx4 + 1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexInverse(temp, scale);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx4 = s * twoSliceStride + idx3 + idx1;
                           a[idx4] = temp[idx2];
                           a[idx4 + 1] = temp[idx2 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx2 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices;
                     int idx5 = idx2 * twoSliceStride;
                     int idx6 = s * twoSliceStride;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx4 = DoubleFFT_3D.this.rows - r;
                        int idx7 = idx4 * twoRowStride;
                        int idx8 = r * twoRowStride;
                        int idx9 = idx5 + idx7;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; ++c) {
                           int idx1 = 2 * c;
                           int idx3 = twon3 - idx1;
                           int idx10 = idx6 + idx8 + idx1;
                           a[idx9 + idx3 % twon3] = a[idx10];
                           a[idx9 + (idx3 + 1) % twon3] = -a[idx10 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = this.slices - 1; s >= 0; --s) {
            int idx1 = s * this.sliceStride;
            int idx2 = s * twoSliceStride;

            for(int r = this.rows - 1; r >= 0; --r) {
               System.arraycopy(a, idx1 + r * this.rowStride, temp, 0, this.columns);
               this.fftColumns.realInverseFull(temp, scale);
               System.arraycopy(temp, 0, a, idx2 + r * twoRowStride, twon3);
            }
         }

         temp = new double[2 * this.rows];

         for(int s = 0; s < this.slices; ++s) {
            int idx1 = s * twoSliceStride;

            for(int c = 0; c < this.columns; ++c) {
               int idx2 = 2 * c;

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  int idx3 = idx1 + r * twoRowStride + idx2;
                  temp[idx4] = a[idx3];
                  temp[idx4 + 1] = a[idx3 + 1];
               }

               this.fftRows.complexInverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx4 = 2 * r;
                  int idx3 = idx1 + r * twoRowStride + idx2;
                  a[idx3] = temp[idx4];
                  a[idx3 + 1] = temp[idx4 + 1];
               }
            }
         }

         temp = new double[2 * this.slices];

         for(int r = 0; r < ldimn2; ++r) {
            int idx3 = r * twoRowStride;

            for(int c = 0; c < this.columns; ++c) {
               int idx1 = 2 * c;

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx4 = s * twoSliceStride + idx3 + idx1;
                  temp[idx2] = a[idx4];
                  temp[idx2 + 1] = a[idx4 + 1];
               }

               this.fftSlices.complexInverse(temp, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx4 = s * twoSliceStride + idx3 + idx1;
                  a[idx4] = temp[idx2];
                  a[idx4 + 1] = temp[idx2 + 1];
               }
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx2 = (this.slices - s) % this.slices;
            int idx5 = idx2 * twoSliceStride;
            int idx6 = s * twoSliceStride;

            for(int r = 1; r < n2d2; ++r) {
               int idx4 = this.rows - r;
               int idx7 = idx4 * twoRowStride;
               int idx8 = r * twoRowStride;
               int idx9 = idx5 + idx7;

               for(int c = 0; c < this.columns; ++c) {
                  int idx1 = 2 * c;
                  int idx3 = twon3 - idx1;
                  int idx10 = idx6 + idx8 + idx1;
                  a[idx9 + idx3 % twon3] = a[idx10];
                  a[idx9 + (idx3 + 1) % twon3] = -a[idx10 + 1];
               }
            }
         }
      }

   }

   private void mixedRadixRealInverseFull(final DoubleLargeArray a, final boolean scale) {
      final long twon3 = 2L * this.columnsl;
      DoubleLargeArray temp = new DoubleLargeArray(twon3);
      long ldimn2 = this.rowsl / 2L + 1L;
      final long n2d2;
      if (this.rowsl % 2L == 0L) {
         n2d2 = this.rowsl / 2L;
      } else {
         n2d2 = (this.rowsl + 1L) / 2L;
      }

      final long twoSliceStride = 2L * this.sliceStridel;
      final long twoRowStride = 2L * this.rowStridel;
      long n1d2 = this.slicesl / 2L;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && n1d2 >= (long)nthreads && this.columnsl >= (long)nthreads && ldimn2 >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = n1d2 / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = this.slicesl - 1L - (long)l * p;
            final long lastSlice = l == nthreads - 1 ? n1d2 + 1L : firstSlice - p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(twon3);

                  for(long s = firstSlice; s >= lastSlice; --s) {
                     long idx1 = s * DoubleFFT_3D.this.sliceStridel;
                     long idx2 = s * twoSliceStride;

                     for(long r = DoubleFFT_3D.this.rowsl - 1L; r >= 0L; --r) {
                        LargeArrayUtils.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStridel, temp, 0L, DoubleFFT_3D.this.columnsl);
                        DoubleFFT_3D.this.fftColumns.realInverseFull(temp, scale);
                        LargeArrayUtils.arraycopy(temp, 0L, a, idx2 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         final DoubleLargeArray temp2 = new DoubleLargeArray((n1d2 + 1L) * this.rowsl * twon3);

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? n1d2 + 1L : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * DoubleFFT_3D.this.sliceStridel;

                     for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                        LargeArrayUtils.arraycopy(a, idx1 + r * DoubleFFT_3D.this.rowStridel, temp2, s * DoubleFFT_3D.this.rowsl * twon3 + r * twon3, DoubleFFT_3D.this.columnsl);
                        DoubleFFT_3D.this.fftColumns.realInverseFull(temp2, s * DoubleFFT_3D.this.rowsl * twon3 + r * twon3, scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? n1d2 + 1L : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * twoSliceStride;

                     for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                        LargeArrayUtils.arraycopy(temp2, s * DoubleFFT_3D.this.rowsl * twon3 + r * twon3, a, idx1 + r * twoRowStride, twon3);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slicesl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.rowsl, false);

                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * twoSliceStride;

                     for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                        long idx2 = 2L * c;

                        for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * twoRowStride + idx2;
                           long idx4 = 2L * r;
                           temp.setDouble(idx4, a.getDouble(idx3));
                           temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(temp, scale);

                        for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * twoRowStride + idx2;
                           long idx4 = 2L * r;
                           a.setDouble(idx3, temp.getDouble(idx4));
                           a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = ldimn2 / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? ldimn2 : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  DoubleLargeArray temp = new DoubleLargeArray(2L * DoubleFFT_3D.this.slicesl, false);

                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx3 = r * twoRowStride;

                     for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                        long idx1 = 2L * c;

                        for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                           long idx2 = 2L * s;
                           long idx4 = s * twoSliceStride + idx3 + idx1;
                           temp.setDouble(idx2, a.getDouble(idx4));
                           temp.setDouble(idx2 + 1L, a.getDouble(idx4 + 1L));
                        }

                        DoubleFFT_3D.this.fftSlices.complexInverse(temp, scale);

                        for(long s = 0L; s < DoubleFFT_3D.this.slicesl; ++s) {
                           long idx2 = 2L * s;
                           long idx4 = s * twoSliceStride + idx3 + idx1;
                           a.setDouble(idx4, temp.getDouble(idx2));
                           a.setDouble(idx4 + 1L, temp.getDouble(idx2 + 1L));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.slicesl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx2 = (DoubleFFT_3D.this.slicesl - s) % DoubleFFT_3D.this.slicesl;
                     long idx5 = idx2 * twoSliceStride;
                     long idx6 = s * twoSliceStride;

                     for(long r = 1L; r < n2d2; ++r) {
                        long idx4 = DoubleFFT_3D.this.rowsl - r;
                        long idx7 = idx4 * twoRowStride;
                        long idx8 = r * twoRowStride;
                        long idx9 = idx5 + idx7;

                        for(long c = 0L; c < DoubleFFT_3D.this.columnsl; ++c) {
                           long idx1 = 2L * c;
                           long idx3 = twon3 - idx1;
                           long idx10 = idx6 + idx8 + idx1;
                           a.setDouble(idx9 + idx3 % twon3, a.getDouble(idx10));
                           a.setDouble(idx9 + (idx3 + 1L) % twon3, -a.getDouble(idx10 + 1L));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long s = this.slicesl - 1L; s >= 0L; --s) {
            long idx1 = s * this.sliceStridel;
            long idx2 = s * twoSliceStride;

            for(long r = this.rowsl - 1L; r >= 0L; --r) {
               LargeArrayUtils.arraycopy(a, idx1 + r * this.rowStridel, temp, 0L, this.columnsl);
               this.fftColumns.realInverseFull(temp, scale);
               LargeArrayUtils.arraycopy(temp, 0L, a, idx2 + r * twoRowStride, twon3);
            }
         }

         temp = new DoubleLargeArray(2L * this.rowsl, false);

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx1 = s * twoSliceStride;

            for(long c = 0L; c < this.columnsl; ++c) {
               long idx2 = 2L * c;

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx4 = 2L * r;
                  long idx3 = idx1 + r * twoRowStride + idx2;
                  temp.setDouble(idx4, a.getDouble(idx3));
                  temp.setDouble(idx4 + 1L, a.getDouble(idx3 + 1L));
               }

               this.fftRows.complexInverse(temp, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx4 = 2L * r;
                  long idx3 = idx1 + r * twoRowStride + idx2;
                  a.setDouble(idx3, temp.getDouble(idx4));
                  a.setDouble(idx3 + 1L, temp.getDouble(idx4 + 1L));
               }
            }
         }

         temp = new DoubleLargeArray(2L * this.slicesl, false);

         for(long r = 0L; r < ldimn2; ++r) {
            long idx3 = r * twoRowStride;

            for(long c = 0L; c < this.columnsl; ++c) {
               long idx1 = 2L * c;

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx2 = 2L * s;
                  long idx4 = s * twoSliceStride + idx3 + idx1;
                  temp.setDouble(idx2, a.getDouble(idx4));
                  temp.setDouble(idx2 + 1L, a.getDouble(idx4 + 1L));
               }

               this.fftSlices.complexInverse(temp, scale);

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx2 = 2L * s;
                  long idx4 = s * twoSliceStride + idx3 + idx1;
                  a.setDouble(idx4, temp.getDouble(idx2));
                  a.setDouble(idx4 + 1L, temp.getDouble(idx2 + 1L));
               }
            }
         }

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx2 = (this.slicesl - s) % this.slicesl;
            long idx5 = idx2 * twoSliceStride;
            long idx6 = s * twoSliceStride;

            for(long r = 1L; r < n2d2; ++r) {
               long idx4 = this.rowsl - r;
               long idx7 = idx4 * twoRowStride;
               long idx8 = r * twoRowStride;
               long idx9 = idx5 + idx7;

               for(long c = 0L; c < this.columnsl; ++c) {
                  long idx1 = 2L * c;
                  long idx3 = twon3 - idx1;
                  long idx10 = idx6 + idx8 + idx1;
                  a.setDouble(idx9 + idx3 % twon3, a.getDouble(idx10));
                  a.setDouble(idx9 + (idx3 + 1L) % twon3, -a.getDouble(idx10 + 1L));
               }
            }
         }
      }

   }

   private void xdft3da_sub1(int icr, int isgn, double[] a, boolean scale) {
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         for(int s = 0; s < this.slices; ++s) {
            int idx0 = s * this.sliceStride;
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexForward(a, idx0 + r * this.rowStride);
               }
            } else {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realForward(a, idx0 + r * this.rowStride);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx1 = idx0 + r * this.rowStride + c;
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
                     int idx1 = idx0 + r * this.rowStride + c;
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
                  int idx1 = idx0 + r * this.rowStride;
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
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
                  a[idx1 + 2] = t[idx3];
                  a[idx1 + 3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
               }

               this.fftRows.complexForward(t, 0);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
               }
            }
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            int idx0 = s * this.sliceStride;
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexInverse(a, idx0 + r * this.rowStride, scale);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx1 = idx0 + r * this.rowStride + c;
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
                     int idx1 = idx0 + r * this.rowStride + c;
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
                  int idx1 = idx0 + r * this.rowStride;
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
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
                  a[idx1 + 2] = t[idx3];
                  a[idx1 + 3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
               }

               this.fftRows.complexInverse(t, 0, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
               }
            }

            if (icr != 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realInverse(a, idx0 + r * this.rowStride, scale);
               }
            }
         }
      }

   }

   private void xdft3da_sub1(long icr, int isgn, DoubleLargeArray a, boolean scale) {
      // $FF: Couldn't be decompiled
   }

   private void xdft3da_sub2(int icr, int isgn, double[] a, boolean scale) {
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         for(int s = 0; s < this.slices; ++s) {
            int idx0 = s * this.sliceStride;
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexForward(a, idx0 + r * this.rowStride);
               }
            } else {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realForward(a, idx0 + r * this.rowStride);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx1 = idx0 + r * this.rowStride + c;
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
                     int idx1 = idx0 + r * this.rowStride + c;
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
                  int idx1 = idx0 + r * this.rowStride;
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
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
                  a[idx1 + 2] = t[idx3];
                  a[idx1 + 3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
               }

               this.fftRows.complexForward(t, 0);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
               }
            }
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            int idx0 = s * this.sliceStride;
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexInverse(a, idx0 + r * this.rowStride, scale);
               }
            } else {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realInverse2(a, idx0 + r * this.rowStride, scale);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx1 = idx0 + r * this.rowStride + c;
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
                     int idx1 = idx0 + r * this.rowStride + c;
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
                  int idx1 = idx0 + r * this.rowStride;
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
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
                  a[idx1 + 2] = t[idx3];
                  a[idx1 + 3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
               }

               this.fftRows.complexInverse(t, 0, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  int idx2 = 2 * r;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
               }
            }
         }
      }

   }

   private void xdft3da_sub2(long icr, int isgn, DoubleLargeArray a, boolean scale) {
      // $FF: Couldn't be decompiled
   }

   private void xdft3da_sub1(int icr, int isgn, double[][][] a, boolean scale) {
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         for(int s = 0; s < this.slices; ++s) {
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexForward(a[s][r]);
               }
            } else {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realForward(a[s][r], 0);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = 2 * r;
                     int idx3 = 2 * this.rows + 2 * r;
                     int idx4 = idx3 + 2 * this.rows;
                     int idx5 = idx4 + 2 * this.rows;
                     t[idx2] = a[s][r][c];
                     t[idx2 + 1] = a[s][r][c + 1];
                     t[idx3] = a[s][r][c + 2];
                     t[idx3 + 1] = a[s][r][c + 3];
                     t[idx4] = a[s][r][c + 4];
                     t[idx4 + 1] = a[s][r][c + 5];
                     t[idx5] = a[s][r][c + 6];
                     t[idx5 + 1] = a[s][r][c + 7];
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
                     a[s][r][c] = t[idx2];
                     a[s][r][c + 1] = t[idx2 + 1];
                     a[s][r][c + 2] = t[idx3];
                     a[s][r][c + 3] = t[idx3 + 1];
                     a[s][r][c + 4] = t[idx4];
                     a[s][r][c + 5] = t[idx4 + 1];
                     a[s][r][c + 6] = t[idx5];
                     a[s][r][c + 7] = t[idx5 + 1];
                  }
               }
            } else if (this.columns == 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
                  t[idx3] = a[s][r][2];
                  t[idx3 + 1] = a[s][r][3];
               }

               this.fftRows.complexForward(t, 0);
               this.fftRows.complexForward(t, 2 * this.rows);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
                  a[s][r][2] = t[idx3];
                  a[s][r][3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
               }

               this.fftRows.complexForward(t, 0);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
               }
            }
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexInverse(a[s][r], scale);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = 2 * r;
                     int idx3 = 2 * this.rows + 2 * r;
                     int idx4 = idx3 + 2 * this.rows;
                     int idx5 = idx4 + 2 * this.rows;
                     t[idx2] = a[s][r][c];
                     t[idx2 + 1] = a[s][r][c + 1];
                     t[idx3] = a[s][r][c + 2];
                     t[idx3 + 1] = a[s][r][c + 3];
                     t[idx4] = a[s][r][c + 4];
                     t[idx4 + 1] = a[s][r][c + 5];
                     t[idx5] = a[s][r][c + 6];
                     t[idx5 + 1] = a[s][r][c + 7];
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
                     a[s][r][c] = t[idx2];
                     a[s][r][c + 1] = t[idx2 + 1];
                     a[s][r][c + 2] = t[idx3];
                     a[s][r][c + 3] = t[idx3 + 1];
                     a[s][r][c + 4] = t[idx4];
                     a[s][r][c + 5] = t[idx4 + 1];
                     a[s][r][c + 6] = t[idx5];
                     a[s][r][c + 7] = t[idx5 + 1];
                  }
               }
            } else if (this.columns == 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
                  t[idx3] = a[s][r][2];
                  t[idx3 + 1] = a[s][r][3];
               }

               this.fftRows.complexInverse(t, 0, scale);
               this.fftRows.complexInverse(t, 2 * this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
                  a[s][r][2] = t[idx3];
                  a[s][r][3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
               }

               this.fftRows.complexInverse(t, 0, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
               }
            }

            if (icr != 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realInverse(a[s][r], scale);
               }
            }
         }
      }

   }

   private void xdft3da_sub2(int icr, int isgn, double[][][] a, boolean scale) {
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         for(int s = 0; s < this.slices; ++s) {
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexForward(a[s][r]);
               }
            } else {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realForward(a[s][r]);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = 2 * r;
                     int idx3 = 2 * this.rows + 2 * r;
                     int idx4 = idx3 + 2 * this.rows;
                     int idx5 = idx4 + 2 * this.rows;
                     t[idx2] = a[s][r][c];
                     t[idx2 + 1] = a[s][r][c + 1];
                     t[idx3] = a[s][r][c + 2];
                     t[idx3 + 1] = a[s][r][c + 3];
                     t[idx4] = a[s][r][c + 4];
                     t[idx4 + 1] = a[s][r][c + 5];
                     t[idx5] = a[s][r][c + 6];
                     t[idx5 + 1] = a[s][r][c + 7];
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
                     a[s][r][c] = t[idx2];
                     a[s][r][c + 1] = t[idx2 + 1];
                     a[s][r][c + 2] = t[idx3];
                     a[s][r][c + 3] = t[idx3 + 1];
                     a[s][r][c + 4] = t[idx4];
                     a[s][r][c + 5] = t[idx4 + 1];
                     a[s][r][c + 6] = t[idx5];
                     a[s][r][c + 7] = t[idx5 + 1];
                  }
               }
            } else if (this.columns == 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
                  t[idx3] = a[s][r][2];
                  t[idx3 + 1] = a[s][r][3];
               }

               this.fftRows.complexForward(t, 0);
               this.fftRows.complexForward(t, 2 * this.rows);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
                  a[s][r][2] = t[idx3];
                  a[s][r][3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
               }

               this.fftRows.complexForward(t, 0);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
               }
            }
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            if (icr == 0) {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.complexInverse(a[s][r], scale);
               }
            } else {
               for(int r = 0; r < this.rows; ++r) {
                  this.fftColumns.realInverse2(a[s][r], 0, scale);
               }
            }

            if (this.columns > 4) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = 2 * r;
                     int idx3 = 2 * this.rows + 2 * r;
                     int idx4 = idx3 + 2 * this.rows;
                     int idx5 = idx4 + 2 * this.rows;
                     t[idx2] = a[s][r][c];
                     t[idx2 + 1] = a[s][r][c + 1];
                     t[idx3] = a[s][r][c + 2];
                     t[idx3 + 1] = a[s][r][c + 3];
                     t[idx4] = a[s][r][c + 4];
                     t[idx4 + 1] = a[s][r][c + 5];
                     t[idx5] = a[s][r][c + 6];
                     t[idx5 + 1] = a[s][r][c + 7];
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
                     a[s][r][c] = t[idx2];
                     a[s][r][c + 1] = t[idx2 + 1];
                     a[s][r][c + 2] = t[idx3];
                     a[s][r][c + 3] = t[idx3 + 1];
                     a[s][r][c + 4] = t[idx4];
                     a[s][r][c + 5] = t[idx4 + 1];
                     a[s][r][c + 6] = t[idx5];
                     a[s][r][c + 7] = t[idx5 + 1];
                  }
               }
            } else if (this.columns == 4) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
                  t[idx3] = a[s][r][2];
                  t[idx3 + 1] = a[s][r][3];
               }

               this.fftRows.complexInverse(t, 0, scale);
               this.fftRows.complexInverse(t, 2 * this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  int idx3 = 2 * this.rows + 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
                  a[s][r][2] = t[idx3];
                  a[s][r][3] = t[idx3 + 1];
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
               }

               this.fftRows.complexInverse(t, 0, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx2 = 2 * r;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
               }
            }
         }
      }

   }

   private void cdft3db_sub(int isgn, double[] a, boolean scale) {
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         if (this.columns > 4) {
            for(int r = 0; r < this.rows; ++r) {
               int idx0 = r * this.rowStride;

               for(int c = 0; c < this.columns; c += 8) {
                  for(int s = 0; s < this.slices; ++s) {
                     int idx1 = s * this.sliceStride + idx0 + c;
                     int idx2 = 2 * s;
                     int idx3 = 2 * this.slices + 2 * s;
                     int idx4 = idx3 + 2 * this.slices;
                     int idx5 = idx4 + 2 * this.slices;
                     t[idx2] = a[idx1];
                     t[idx2 + 1] = a[idx1 + 1];
                     t[idx3] = a[idx1 + 2];
                     t[idx3 + 1] = a[idx1 + 3];
                     t[idx4] = a[idx1 + 4];
                     t[idx4 + 1] = a[idx1 + 5];
                     t[idx5] = a[idx1 + 6];
                     t[idx5 + 1] = a[idx1 + 7];
                  }

                  this.fftSlices.complexForward(t, 0);
                  this.fftSlices.complexForward(t, 2 * this.slices);
                  this.fftSlices.complexForward(t, 4 * this.slices);
                  this.fftSlices.complexForward(t, 6 * this.slices);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx1 = s * this.sliceStride + idx0 + c;
                     int idx2 = 2 * s;
                     int idx3 = 2 * this.slices + 2 * s;
                     int idx4 = idx3 + 2 * this.slices;
                     int idx5 = idx4 + 2 * this.slices;
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
            }
         } else if (this.columns == 4) {
            for(int r = 0; r < this.rows; ++r) {
               int idx0 = r * this.rowStride;

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0;
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
                  t[idx3] = a[idx1 + 2];
                  t[idx3 + 1] = a[idx1 + 3];
               }

               this.fftSlices.complexForward(t, 0);
               this.fftSlices.complexForward(t, 2 * this.slices);

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0;
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
                  a[idx1 + 2] = t[idx3];
                  a[idx1 + 3] = t[idx3 + 1];
               }
            }
         } else if (this.columns == 2) {
            for(int r = 0; r < this.rows; ++r) {
               int idx0 = r * this.rowStride;

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0;
                  int idx2 = 2 * s;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
               }

               this.fftSlices.complexForward(t, 0);

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0;
                  int idx2 = 2 * s;
                  a[idx1] = t[idx2];
                  a[idx1 + 1] = t[idx2 + 1];
               }
            }
         }
      } else if (this.columns > 4) {
         for(int r = 0; r < this.rows; ++r) {
            int idx0 = r * this.rowStride;

            for(int c = 0; c < this.columns; c += 8) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0 + c;
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  int idx4 = idx3 + 2 * this.slices;
                  int idx5 = idx4 + 2 * this.slices;
                  t[idx2] = a[idx1];
                  t[idx2 + 1] = a[idx1 + 1];
                  t[idx3] = a[idx1 + 2];
                  t[idx3 + 1] = a[idx1 + 3];
                  t[idx4] = a[idx1 + 4];
                  t[idx4 + 1] = a[idx1 + 5];
                  t[idx5] = a[idx1 + 6];
                  t[idx5 + 1] = a[idx1 + 7];
               }

               this.fftSlices.complexInverse(t, 0, scale);
               this.fftSlices.complexInverse(t, 2 * this.slices, scale);
               this.fftSlices.complexInverse(t, 4 * this.slices, scale);
               this.fftSlices.complexInverse(t, 6 * this.slices, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0 + c;
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  int idx4 = idx3 + 2 * this.slices;
                  int idx5 = idx4 + 2 * this.slices;
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
         }
      } else if (this.columns == 4) {
         for(int r = 0; r < this.rows; ++r) {
            int idx0 = r * this.rowStride;

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride + idx0;
               int idx2 = 2 * s;
               int idx3 = 2 * this.slices + 2 * s;
               t[idx2] = a[idx1];
               t[idx2 + 1] = a[idx1 + 1];
               t[idx3] = a[idx1 + 2];
               t[idx3 + 1] = a[idx1 + 3];
            }

            this.fftSlices.complexInverse(t, 0, scale);
            this.fftSlices.complexInverse(t, 2 * this.slices, scale);

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride + idx0;
               int idx2 = 2 * s;
               int idx3 = 2 * this.slices + 2 * s;
               a[idx1] = t[idx2];
               a[idx1 + 1] = t[idx2 + 1];
               a[idx1 + 2] = t[idx3];
               a[idx1 + 3] = t[idx3 + 1];
            }
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            int idx0 = r * this.rowStride;

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride + idx0;
               int idx2 = 2 * s;
               t[idx2] = a[idx1];
               t[idx2 + 1] = a[idx1 + 1];
            }

            this.fftSlices.complexInverse(t, 0, scale);

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride + idx0;
               int idx2 = 2 * s;
               a[idx1] = t[idx2];
               a[idx1 + 1] = t[idx2 + 1];
            }
         }
      }

   }

   private void cdft3db_sub(int isgn, DoubleLargeArray a, boolean scale) {
      // $FF: Couldn't be decompiled
   }

   private void cdft3db_sub(int isgn, double[][][] a, boolean scale) {
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      double[] t = new double[nt];
      if (isgn == -1) {
         if (this.columns > 4) {
            for(int r = 0; r < this.rows; ++r) {
               for(int c = 0; c < this.columns; c += 8) {
                  for(int s = 0; s < this.slices; ++s) {
                     int idx2 = 2 * s;
                     int idx3 = 2 * this.slices + 2 * s;
                     int idx4 = idx3 + 2 * this.slices;
                     int idx5 = idx4 + 2 * this.slices;
                     t[idx2] = a[s][r][c];
                     t[idx2 + 1] = a[s][r][c + 1];
                     t[idx3] = a[s][r][c + 2];
                     t[idx3 + 1] = a[s][r][c + 3];
                     t[idx4] = a[s][r][c + 4];
                     t[idx4 + 1] = a[s][r][c + 5];
                     t[idx5] = a[s][r][c + 6];
                     t[idx5 + 1] = a[s][r][c + 7];
                  }

                  this.fftSlices.complexForward(t, 0);
                  this.fftSlices.complexForward(t, 2 * this.slices);
                  this.fftSlices.complexForward(t, 4 * this.slices);
                  this.fftSlices.complexForward(t, 6 * this.slices);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx2 = 2 * s;
                     int idx3 = 2 * this.slices + 2 * s;
                     int idx4 = idx3 + 2 * this.slices;
                     int idx5 = idx4 + 2 * this.slices;
                     a[s][r][c] = t[idx2];
                     a[s][r][c + 1] = t[idx2 + 1];
                     a[s][r][c + 2] = t[idx3];
                     a[s][r][c + 3] = t[idx3 + 1];
                     a[s][r][c + 4] = t[idx4];
                     a[s][r][c + 5] = t[idx4 + 1];
                     a[s][r][c + 6] = t[idx5];
                     a[s][r][c + 7] = t[idx5 + 1];
                  }
               }
            }
         } else if (this.columns == 4) {
            for(int r = 0; r < this.rows; ++r) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
                  t[idx3] = a[s][r][2];
                  t[idx3 + 1] = a[s][r][3];
               }

               this.fftSlices.complexForward(t, 0);
               this.fftSlices.complexForward(t, 2 * this.slices);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
                  a[s][r][2] = t[idx3];
                  a[s][r][3] = t[idx3 + 1];
               }
            }
         } else if (this.columns == 2) {
            for(int r = 0; r < this.rows; ++r) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  t[idx2] = a[s][r][0];
                  t[idx2 + 1] = a[s][r][1];
               }

               this.fftSlices.complexForward(t, 0);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  a[s][r][0] = t[idx2];
                  a[s][r][1] = t[idx2 + 1];
               }
            }
         }
      } else if (this.columns > 4) {
         for(int r = 0; r < this.rows; ++r) {
            for(int c = 0; c < this.columns; c += 8) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  int idx4 = idx3 + 2 * this.slices;
                  int idx5 = idx4 + 2 * this.slices;
                  t[idx2] = a[s][r][c];
                  t[idx2 + 1] = a[s][r][c + 1];
                  t[idx3] = a[s][r][c + 2];
                  t[idx3 + 1] = a[s][r][c + 3];
                  t[idx4] = a[s][r][c + 4];
                  t[idx4 + 1] = a[s][r][c + 5];
                  t[idx5] = a[s][r][c + 6];
                  t[idx5 + 1] = a[s][r][c + 7];
               }

               this.fftSlices.complexInverse(t, 0, scale);
               this.fftSlices.complexInverse(t, 2 * this.slices, scale);
               this.fftSlices.complexInverse(t, 4 * this.slices, scale);
               this.fftSlices.complexInverse(t, 6 * this.slices, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = 2 * s;
                  int idx3 = 2 * this.slices + 2 * s;
                  int idx4 = idx3 + 2 * this.slices;
                  int idx5 = idx4 + 2 * this.slices;
                  a[s][r][c] = t[idx2];
                  a[s][r][c + 1] = t[idx2 + 1];
                  a[s][r][c + 2] = t[idx3];
                  a[s][r][c + 3] = t[idx3 + 1];
                  a[s][r][c + 4] = t[idx4];
                  a[s][r][c + 5] = t[idx4 + 1];
                  a[s][r][c + 6] = t[idx5];
                  a[s][r][c + 7] = t[idx5 + 1];
               }
            }
         }
      } else if (this.columns == 4) {
         for(int r = 0; r < this.rows; ++r) {
            for(int s = 0; s < this.slices; ++s) {
               int idx2 = 2 * s;
               int idx3 = 2 * this.slices + 2 * s;
               t[idx2] = a[s][r][0];
               t[idx2 + 1] = a[s][r][1];
               t[idx3] = a[s][r][2];
               t[idx3 + 1] = a[s][r][3];
            }

            this.fftSlices.complexInverse(t, 0, scale);
            this.fftSlices.complexInverse(t, 2 * this.slices, scale);

            for(int s = 0; s < this.slices; ++s) {
               int idx2 = 2 * s;
               int idx3 = 2 * this.slices + 2 * s;
               a[s][r][0] = t[idx2];
               a[s][r][1] = t[idx2 + 1];
               a[s][r][2] = t[idx3];
               a[s][r][3] = t[idx3 + 1];
            }
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            for(int s = 0; s < this.slices; ++s) {
               int idx2 = 2 * s;
               t[idx2] = a[s][r][0];
               t[idx2 + 1] = a[s][r][1];
            }

            this.fftSlices.complexInverse(t, 0, scale);

            for(int s = 0; s < this.slices; ++s) {
               int idx2 = 2 * s;
               a[s][r][0] = t[idx2];
               a[s][r][1] = t[idx2 + 1];
            }
         }
      }

   }

   private void xdft3da_subth1(final int icr, final int isgn, final double[] a, final boolean scale) {
      final int nthreads = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     int idx0 = s * DoubleFFT_3D.this.sliceStride;
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexForward(a, idx0 + r * DoubleFFT_3D.this.rowStride);
                        }
                     } else {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realForward(a, idx0 + r * DoubleFFT_3D.this.rowStride);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[idx1];
                              t[idx2 + 1] = a[idx1 + 1];
                              t[idx3] = a[idx1 + 2];
                              t[idx3 + 1] = a[idx1 + 3];
                              t[idx4] = a[idx1 + 4];
                              t[idx4 + 1] = a[idx1 + 5];
                              t[idx5] = a[idx1 + 6];
                              t[idx5 + 1] = a[idx1 + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 4 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 6 * DoubleFFT_3D.this.rows);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
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
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                           t[idx3] = a[idx1 + 2];
                           t[idx3 + 1] = a[idx1 + 3];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                        DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                           a[idx1 + 2] = t[idx3];
                           a[idx1 + 3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     int idx0 = s * DoubleFFT_3D.this.sliceStride;
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexInverse(a, idx0 + r * DoubleFFT_3D.this.rowStride, scale);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[idx1];
                              t[idx2 + 1] = a[idx1 + 1];
                              t[idx3] = a[idx1 + 2];
                              t[idx3 + 1] = a[idx1 + 3];
                              t[idx4] = a[idx1 + 4];
                              t[idx4 + 1] = a[idx1 + 5];
                              t[idx5] = a[idx1 + 6];
                              t[idx5 + 1] = a[idx1 + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 4 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 6 * DoubleFFT_3D.this.rows, scale);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
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
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                           t[idx3] = a[idx1 + 2];
                           t[idx3 + 1] = a[idx1 + 3];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                        DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                           a[idx1 + 2] = t[idx3];
                           a[idx1 + 3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                        }
                     }

                     if (icr != 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realInverse(a, idx0 + r * DoubleFFT_3D.this.rowStride, scale);
                        }
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft3da_subth1(final long icr, final int isgn, final DoubleLargeArray a, final boolean scale) {
      // $FF: Couldn't be decompiled
   }

   private void xdft3da_subth2(final int icr, final int isgn, final double[] a, final boolean scale) {
      final int nthreads = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     int idx0 = s * DoubleFFT_3D.this.sliceStride;
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexForward(a, idx0 + r * DoubleFFT_3D.this.rowStride);
                        }
                     } else {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realForward(a, idx0 + r * DoubleFFT_3D.this.rowStride);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[idx1];
                              t[idx2 + 1] = a[idx1 + 1];
                              t[idx3] = a[idx1 + 2];
                              t[idx3 + 1] = a[idx1 + 3];
                              t[idx4] = a[idx1 + 4];
                              t[idx4 + 1] = a[idx1 + 5];
                              t[idx5] = a[idx1 + 6];
                              t[idx5 + 1] = a[idx1 + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 4 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 6 * DoubleFFT_3D.this.rows);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
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
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                           t[idx3] = a[idx1 + 2];
                           t[idx3 + 1] = a[idx1 + 3];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                        DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                           a[idx1 + 2] = t[idx3];
                           a[idx1 + 3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     int idx0 = s * DoubleFFT_3D.this.sliceStride;
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexInverse(a, idx0 + r * DoubleFFT_3D.this.rowStride, scale);
                        }
                     } else {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realInverse2(a, idx0 + r * DoubleFFT_3D.this.rowStride, scale);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[idx1];
                              t[idx2 + 1] = a[idx1 + 1];
                              t[idx3] = a[idx1 + 2];
                              t[idx3 + 1] = a[idx1 + 3];
                              t[idx4] = a[idx1 + 4];
                              t[idx4 + 1] = a[idx1 + 5];
                              t[idx5] = a[idx1 + 6];
                              t[idx5 + 1] = a[idx1 + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 4 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 6 * DoubleFFT_3D.this.rows, scale);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride + c;
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
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
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                           t[idx3] = a[idx1 + 2];
                           t[idx3 + 1] = a[idx1 + 3];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                        DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                           a[idx1 + 2] = t[idx3];
                           a[idx1 + 3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * DoubleFFT_3D.this.rowStride;
                           int idx2 = 2 * r;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                        }
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft3da_subth2(final long icr, final int isgn, final DoubleLargeArray a, final boolean scale) {
      // $FF: Couldn't be decompiled
   }

   private void xdft3da_subth1(final int icr, final int isgn, final double[][][] a, final boolean scale) {
      final int nthreads = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexForward(a[s][r]);
                        }
                     } else {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realForward(a[s][r], 0);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[s][r][c];
                              t[idx2 + 1] = a[s][r][c + 1];
                              t[idx3] = a[s][r][c + 2];
                              t[idx3 + 1] = a[s][r][c + 3];
                              t[idx4] = a[s][r][c + 4];
                              t[idx4 + 1] = a[s][r][c + 5];
                              t[idx5] = a[s][r][c + 6];
                              t[idx5 + 1] = a[s][r][c + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 4 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 6 * DoubleFFT_3D.this.rows);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              a[s][r][c] = t[idx2];
                              a[s][r][c + 1] = t[idx2 + 1];
                              a[s][r][c + 2] = t[idx3];
                              a[s][r][c + 3] = t[idx3 + 1];
                              a[s][r][c + 4] = t[idx4];
                              a[s][r][c + 5] = t[idx4 + 1];
                              a[s][r][c + 6] = t[idx5];
                              a[s][r][c + 7] = t[idx5 + 1];
                           }
                        }
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                           t[idx3] = a[s][r][2];
                           t[idx3 + 1] = a[s][r][3];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                        DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                           a[s][r][2] = t[idx3];
                           a[s][r][3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexInverse(a[s][r], scale);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[s][r][c];
                              t[idx2 + 1] = a[s][r][c + 1];
                              t[idx3] = a[s][r][c + 2];
                              t[idx3 + 1] = a[s][r][c + 3];
                              t[idx4] = a[s][r][c + 4];
                              t[idx4 + 1] = a[s][r][c + 5];
                              t[idx5] = a[s][r][c + 6];
                              t[idx5 + 1] = a[s][r][c + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 4 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 6 * DoubleFFT_3D.this.rows, scale);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              a[s][r][c] = t[idx2];
                              a[s][r][c + 1] = t[idx2 + 1];
                              a[s][r][c + 2] = t[idx3];
                              a[s][r][c + 3] = t[idx3 + 1];
                              a[s][r][c + 4] = t[idx4];
                              a[s][r][c + 5] = t[idx4 + 1];
                              a[s][r][c + 6] = t[idx5];
                              a[s][r][c + 7] = t[idx5 + 1];
                           }
                        }
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                           t[idx3] = a[s][r][2];
                           t[idx3 + 1] = a[s][r][3];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                        DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                           a[s][r][2] = t[idx3];
                           a[s][r][3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                        }
                     }

                     if (icr != 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realInverse(a[s][r], scale);
                        }
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void xdft3da_subth2(final int icr, final int isgn, final double[][][] a, final boolean scale) {
      final int nthreads = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.slices);
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexForward(a[s][r]);
                        }
                     } else {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realForward(a[s][r]);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[s][r][c];
                              t[idx2 + 1] = a[s][r][c + 1];
                              t[idx3] = a[s][r][c + 2];
                              t[idx3 + 1] = a[s][r][c + 3];
                              t[idx4] = a[s][r][c + 4];
                              t[idx4 + 1] = a[s][r][c + 5];
                              t[idx5] = a[s][r][c + 6];
                              t[idx5 + 1] = a[s][r][c + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 4 * DoubleFFT_3D.this.rows);
                           DoubleFFT_3D.this.fftRows.complexForward(t, 6 * DoubleFFT_3D.this.rows);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              a[s][r][c] = t[idx2];
                              a[s][r][c + 1] = t[idx2 + 1];
                              a[s][r][c + 2] = t[idx3];
                              a[s][r][c + 3] = t[idx3 + 1];
                              a[s][r][c + 4] = t[idx4];
                              a[s][r][c + 5] = t[idx4 + 1];
                              a[s][r][c + 6] = t[idx5];
                              a[s][r][c + 7] = t[idx5 + 1];
                           }
                        }
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                           t[idx3] = a[s][r][2];
                           t[idx3 + 1] = a[s][r][3];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);
                        DoubleFFT_3D.this.fftRows.complexForward(t, 2 * DoubleFFT_3D.this.rows);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                           a[s][r][2] = t[idx3];
                           a[s][r][3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                        }

                        DoubleFFT_3D.this.fftRows.complexForward(t, 0);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < DoubleFFT_3D.this.slices; s += nthreads) {
                     if (icr == 0) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.complexInverse(a[s][r], scale);
                        }
                     } else {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           DoubleFFT_3D.this.fftColumns.realInverse2(a[s][r], 0, scale);
                        }
                     }

                     if (DoubleFFT_3D.this.columns > 4) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              t[idx2] = a[s][r][c];
                              t[idx2 + 1] = a[s][r][c + 1];
                              t[idx3] = a[s][r][c + 2];
                              t[idx3 + 1] = a[s][r][c + 3];
                              t[idx4] = a[s][r][c + 4];
                              t[idx4 + 1] = a[s][r][c + 5];
                              t[idx5] = a[s][r][c + 6];
                              t[idx5 + 1] = a[s][r][c + 7];
                           }

                           DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 4 * DoubleFFT_3D.this.rows, scale);
                           DoubleFFT_3D.this.fftRows.complexInverse(t, 6 * DoubleFFT_3D.this.rows, scale);

                           for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                              int idx2 = 2 * r;
                              int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.rows;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.rows;
                              a[s][r][c] = t[idx2];
                              a[s][r][c + 1] = t[idx2 + 1];
                              a[s][r][c + 2] = t[idx3];
                              a[s][r][c + 3] = t[idx3 + 1];
                              a[s][r][c + 4] = t[idx4];
                              a[s][r][c + 5] = t[idx4 + 1];
                              a[s][r][c + 6] = t[idx5];
                              a[s][r][c + 7] = t[idx5 + 1];
                           }
                        }
                     } else if (DoubleFFT_3D.this.columns == 4) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                           t[idx3] = a[s][r][2];
                           t[idx3 + 1] = a[s][r][3];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);
                        DoubleFFT_3D.this.fftRows.complexInverse(t, 2 * DoubleFFT_3D.this.rows, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           int idx3 = 2 * DoubleFFT_3D.this.rows + 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                           a[s][r][2] = t[idx3];
                           a[s][r][3] = t[idx3 + 1];
                        }
                     } else if (DoubleFFT_3D.this.columns == 2) {
                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                        }

                        DoubleFFT_3D.this.fftRows.complexInverse(t, 0, scale);

                        for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                           int idx2 = 2 * r;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                        }
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void cdft3db_subth(final int isgn, final double[] a, final boolean scale) {
      final int nthreads = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.rows);
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  if (DoubleFFT_3D.this.columns > 4) {
                     for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                        int idx0 = r * DoubleFFT_3D.this.rowStride;

                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0 + c;
                              int idx2 = 2 * s;
                              int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
                              t[idx2] = a[idx1];
                              t[idx2 + 1] = a[idx1 + 1];
                              t[idx3] = a[idx1 + 2];
                              t[idx3 + 1] = a[idx1 + 3];
                              t[idx4] = a[idx1 + 4];
                              t[idx4 + 1] = a[idx1 + 5];
                              t[idx5] = a[idx1 + 6];
                              t[idx5 + 1] = a[idx1 + 7];
                           }

                           DoubleFFT_3D.this.fftSlices.complexForward(t, 0);
                           DoubleFFT_3D.this.fftSlices.complexForward(t, 2 * DoubleFFT_3D.this.slices);
                           DoubleFFT_3D.this.fftSlices.complexForward(t, 4 * DoubleFFT_3D.this.slices);
                           DoubleFFT_3D.this.fftSlices.complexForward(t, 6 * DoubleFFT_3D.this.slices);

                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0 + c;
                              int idx2 = 2 * s;
                              int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
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
                     }
                  } else if (DoubleFFT_3D.this.columns == 4) {
                     for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                        int idx0 = r * DoubleFFT_3D.this.rowStride;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                           t[idx3] = a[idx1 + 2];
                           t[idx3 + 1] = a[idx1 + 3];
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(t, 0);
                        DoubleFFT_3D.this.fftSlices.complexForward(t, 2 * DoubleFFT_3D.this.slices);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                           a[idx1 + 2] = t[idx3];
                           a[idx1 + 3] = t[idx3 + 1];
                        }
                     }
                  } else if (DoubleFFT_3D.this.columns == 2) {
                     for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                        int idx0 = r * DoubleFFT_3D.this.rowStride;

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                           int idx2 = 2 * s;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(t, 0);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                           int idx2 = 2 * s;
                           a[idx1] = t[idx2];
                           a[idx1 + 1] = t[idx2 + 1];
                        }
                     }
                  }
               } else if (DoubleFFT_3D.this.columns > 4) {
                  for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                     int idx0 = r * DoubleFFT_3D.this.rowStride;

                     for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0 + c;
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                           int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
                           t[idx2] = a[idx1];
                           t[idx2 + 1] = a[idx1 + 1];
                           t[idx3] = a[idx1 + 2];
                           t[idx3 + 1] = a[idx1 + 3];
                           t[idx4] = a[idx1 + 4];
                           t[idx4 + 1] = a[idx1 + 5];
                           t[idx5] = a[idx1 + 6];
                           t[idx5 + 1] = a[idx1 + 7];
                        }

                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 0, scale);
                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 2 * DoubleFFT_3D.this.slices, scale);
                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 4 * DoubleFFT_3D.this.slices, scale);
                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 6 * DoubleFFT_3D.this.slices, scale);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0 + c;
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                           int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
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
                  }
               } else if (DoubleFFT_3D.this.columns == 4) {
                  for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                     int idx0 = r * DoubleFFT_3D.this.rowStride;

                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                        int idx2 = 2 * s;
                        int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                        t[idx2] = a[idx1];
                        t[idx2 + 1] = a[idx1 + 1];
                        t[idx3] = a[idx1 + 2];
                        t[idx3 + 1] = a[idx1 + 3];
                     }

                     DoubleFFT_3D.this.fftSlices.complexInverse(t, 0, scale);
                     DoubleFFT_3D.this.fftSlices.complexInverse(t, 2 * DoubleFFT_3D.this.slices, scale);

                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                        int idx2 = 2 * s;
                        int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                        a[idx1] = t[idx2];
                        a[idx1 + 1] = t[idx2 + 1];
                        a[idx1 + 2] = t[idx3];
                        a[idx1 + 3] = t[idx3 + 1];
                     }
                  }
               } else if (DoubleFFT_3D.this.columns == 2) {
                  for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                     int idx0 = r * DoubleFFT_3D.this.rowStride;

                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                        int idx2 = 2 * s;
                        t[idx2] = a[idx1];
                        t[idx2 + 1] = a[idx1 + 1];
                     }

                     DoubleFFT_3D.this.fftSlices.complexInverse(t, 0, scale);

                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx1 = s * DoubleFFT_3D.this.sliceStride + idx0;
                        int idx2 = 2 * s;
                        a[idx1] = t[idx2];
                        a[idx1 + 1] = t[idx2 + 1];
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void cdft3db_subth(final int isgn, final DoubleLargeArray a, final boolean scale) {
      // $FF: Couldn't be decompiled
   }

   private void cdft3db_subth(final int isgn, final double[][][] a, final boolean scale) {
      final int nthreads = FastMath.min(ConcurrencyUtils.getNumberOfThreads(), this.rows);
      int nt = this.slices;
      if (nt < this.rows) {
         nt = this.rows;
      }

      nt *= 8;
      if (this.columns == 4) {
         nt >>= 1;
      } else if (this.columns < 4) {
         nt >>= 2;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               double[] t = new double[ntf];
               if (isgn == -1) {
                  if (DoubleFFT_3D.this.columns > 4) {
                     for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                        for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx2 = 2 * s;
                              int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
                              t[idx2] = a[s][r][c];
                              t[idx2 + 1] = a[s][r][c + 1];
                              t[idx3] = a[s][r][c + 2];
                              t[idx3 + 1] = a[s][r][c + 3];
                              t[idx4] = a[s][r][c + 4];
                              t[idx4 + 1] = a[s][r][c + 5];
                              t[idx5] = a[s][r][c + 6];
                              t[idx5 + 1] = a[s][r][c + 7];
                           }

                           DoubleFFT_3D.this.fftSlices.complexForward(t, 0);
                           DoubleFFT_3D.this.fftSlices.complexForward(t, 2 * DoubleFFT_3D.this.slices);
                           DoubleFFT_3D.this.fftSlices.complexForward(t, 4 * DoubleFFT_3D.this.slices);
                           DoubleFFT_3D.this.fftSlices.complexForward(t, 6 * DoubleFFT_3D.this.slices);

                           for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                              int idx2 = 2 * s;
                              int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                              int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                              int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
                              a[s][r][c] = t[idx2];
                              a[s][r][c + 1] = t[idx2 + 1];
                              a[s][r][c + 2] = t[idx3];
                              a[s][r][c + 3] = t[idx3 + 1];
                              a[s][r][c + 4] = t[idx4];
                              a[s][r][c + 5] = t[idx4 + 1];
                              a[s][r][c + 6] = t[idx5];
                              a[s][r][c + 7] = t[idx5 + 1];
                           }
                        }
                     }
                  } else if (DoubleFFT_3D.this.columns == 4) {
                     for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                           t[idx3] = a[s][r][2];
                           t[idx3 + 1] = a[s][r][3];
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(t, 0);
                        DoubleFFT_3D.this.fftSlices.complexForward(t, 2 * DoubleFFT_3D.this.slices);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                           a[s][r][2] = t[idx3];
                           a[s][r][3] = t[idx3 + 1];
                        }
                     }
                  } else if (DoubleFFT_3D.this.columns == 2) {
                     for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           t[idx2] = a[s][r][0];
                           t[idx2 + 1] = a[s][r][1];
                        }

                        DoubleFFT_3D.this.fftSlices.complexForward(t, 0);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           a[s][r][0] = t[idx2];
                           a[s][r][1] = t[idx2 + 1];
                        }
                     }
                  }
               } else if (DoubleFFT_3D.this.columns > 4) {
                  for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                     for(int c = 0; c < DoubleFFT_3D.this.columns; c += 8) {
                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                           int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
                           t[idx2] = a[s][r][c];
                           t[idx2 + 1] = a[s][r][c + 1];
                           t[idx3] = a[s][r][c + 2];
                           t[idx3 + 1] = a[s][r][c + 3];
                           t[idx4] = a[s][r][c + 4];
                           t[idx4 + 1] = a[s][r][c + 5];
                           t[idx5] = a[s][r][c + 6];
                           t[idx5 + 1] = a[s][r][c + 7];
                        }

                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 0, scale);
                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 2 * DoubleFFT_3D.this.slices, scale);
                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 4 * DoubleFFT_3D.this.slices, scale);
                        DoubleFFT_3D.this.fftSlices.complexInverse(t, 6 * DoubleFFT_3D.this.slices, scale);

                        for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                           int idx2 = 2 * s;
                           int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                           int idx4 = idx3 + 2 * DoubleFFT_3D.this.slices;
                           int idx5 = idx4 + 2 * DoubleFFT_3D.this.slices;
                           a[s][r][c] = t[idx2];
                           a[s][r][c + 1] = t[idx2 + 1];
                           a[s][r][c + 2] = t[idx3];
                           a[s][r][c + 3] = t[idx3 + 1];
                           a[s][r][c + 4] = t[idx4];
                           a[s][r][c + 5] = t[idx4 + 1];
                           a[s][r][c + 6] = t[idx5];
                           a[s][r][c + 7] = t[idx5 + 1];
                        }
                     }
                  }
               } else if (DoubleFFT_3D.this.columns == 4) {
                  for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx2 = 2 * s;
                        int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                        t[idx2] = a[s][r][0];
                        t[idx2 + 1] = a[s][r][1];
                        t[idx3] = a[s][r][2];
                        t[idx3 + 1] = a[s][r][3];
                     }

                     DoubleFFT_3D.this.fftSlices.complexInverse(t, 0, scale);
                     DoubleFFT_3D.this.fftSlices.complexInverse(t, 2 * DoubleFFT_3D.this.slices, scale);

                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx2 = 2 * s;
                        int idx3 = 2 * DoubleFFT_3D.this.slices + 2 * s;
                        a[s][r][0] = t[idx2];
                        a[s][r][1] = t[idx2 + 1];
                        a[s][r][2] = t[idx3];
                        a[s][r][3] = t[idx3 + 1];
                     }
                  }
               } else if (DoubleFFT_3D.this.columns == 2) {
                  for(int r = i; r < DoubleFFT_3D.this.rows; r += nthreads) {
                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx2 = 2 * s;
                        t[idx2] = a[s][r][0];
                        t[idx2 + 1] = a[s][r][1];
                     }

                     DoubleFFT_3D.this.fftSlices.complexInverse(t, 0, scale);

                     for(int s = 0; s < DoubleFFT_3D.this.slices; ++s) {
                        int idx2 = 2 * s;
                        a[s][r][0] = t[idx2];
                        a[s][r][1] = t[idx2 + 1];
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void rdft3d_sub(int isgn, double[] a) {
      int n1h = this.slices >> 1;
      int n2h = this.rows >> 1;
      if (isgn < 0) {
         for(int i = 1; i < n1h; ++i) {
            int j = this.slices - i;
            int idx1 = i * this.sliceStride;
            int idx2 = j * this.sliceStride;
            int idx3 = i * this.sliceStride + n2h * this.rowStride;
            int idx4 = j * this.sliceStride + n2h * this.rowStride;
            double xi = a[idx1] - a[idx2];
            a[idx1] += a[idx2];
            a[idx2] = xi;
            xi = a[idx2 + 1] - a[idx1 + 1];
            a[idx1 + 1] += a[idx2 + 1];
            a[idx2 + 1] = xi;
            xi = a[idx3] - a[idx4];
            a[idx3] += a[idx4];
            a[idx4] = xi;
            xi = a[idx4 + 1] - a[idx3 + 1];
            a[idx3 + 1] += a[idx4 + 1];
            a[idx4 + 1] = xi;

            for(int k = 1; k < n2h; ++k) {
               int l = this.rows - k;
               idx1 = i * this.sliceStride + k * this.rowStride;
               idx2 = j * this.sliceStride + l * this.rowStride;
               xi = a[idx1] - a[idx2];
               a[idx1] += a[idx2];
               a[idx2] = xi;
               xi = a[idx2 + 1] - a[idx1 + 1];
               a[idx1 + 1] += a[idx2 + 1];
               a[idx2 + 1] = xi;
               idx3 = j * this.sliceStride + k * this.rowStride;
               idx4 = i * this.sliceStride + l * this.rowStride;
               xi = a[idx3] - a[idx4];
               a[idx3] += a[idx4];
               a[idx4] = xi;
               xi = a[idx4 + 1] - a[idx3 + 1];
               a[idx3 + 1] += a[idx4 + 1];
               a[idx4 + 1] = xi;
            }
         }

         for(int k = 1; k < n2h; ++k) {
            int l = this.rows - k;
            int idx1 = k * this.rowStride;
            int idx2 = l * this.rowStride;
            double xi = a[idx1] - a[idx2];
            a[idx1] += a[idx2];
            a[idx2] = xi;
            xi = a[idx2 + 1] - a[idx1 + 1];
            a[idx1 + 1] += a[idx2 + 1];
            a[idx2 + 1] = xi;
            int idx3 = n1h * this.sliceStride + k * this.rowStride;
            int idx4 = n1h * this.sliceStride + l * this.rowStride;
            xi = a[idx3] - a[idx4];
            a[idx3] += a[idx4];
            a[idx4] = xi;
            xi = a[idx4 + 1] - a[idx3 + 1];
            a[idx3 + 1] += a[idx4 + 1];
            a[idx4 + 1] = xi;
         }
      } else {
         for(int i = 1; i < n1h; ++i) {
            int j = this.slices - i;
            int idx1 = j * this.sliceStride;
            int idx2 = i * this.sliceStride;
            a[idx1] = (double)0.5F * (a[idx2] - a[idx1]);
            a[idx2] -= a[idx1];
            a[idx1 + 1] = (double)0.5F * (a[idx2 + 1] + a[idx1 + 1]);
            a[idx2 + 1] -= a[idx1 + 1];
            int idx3 = j * this.sliceStride + n2h * this.rowStride;
            int idx4 = i * this.sliceStride + n2h * this.rowStride;
            a[idx3] = (double)0.5F * (a[idx4] - a[idx3]);
            a[idx4] -= a[idx3];
            a[idx3 + 1] = (double)0.5F * (a[idx4 + 1] + a[idx3 + 1]);
            a[idx4 + 1] -= a[idx3 + 1];

            for(int k = 1; k < n2h; ++k) {
               int l = this.rows - k;
               idx1 = j * this.sliceStride + l * this.rowStride;
               idx2 = i * this.sliceStride + k * this.rowStride;
               a[idx1] = (double)0.5F * (a[idx2] - a[idx1]);
               a[idx2] -= a[idx1];
               a[idx1 + 1] = (double)0.5F * (a[idx2 + 1] + a[idx1 + 1]);
               a[idx2 + 1] -= a[idx1 + 1];
               idx3 = i * this.sliceStride + l * this.rowStride;
               idx4 = j * this.sliceStride + k * this.rowStride;
               a[idx3] = (double)0.5F * (a[idx4] - a[idx3]);
               a[idx4] -= a[idx3];
               a[idx3 + 1] = (double)0.5F * (a[idx4 + 1] + a[idx3 + 1]);
               a[idx4 + 1] -= a[idx3 + 1];
            }
         }

         for(int k = 1; k < n2h; ++k) {
            int l = this.rows - k;
            int idx1 = l * this.rowStride;
            int idx2 = k * this.rowStride;
            a[idx1] = (double)0.5F * (a[idx2] - a[idx1]);
            a[idx2] -= a[idx1];
            a[idx1 + 1] = (double)0.5F * (a[idx2 + 1] + a[idx1 + 1]);
            a[idx2 + 1] -= a[idx1 + 1];
            int idx3 = n1h * this.sliceStride + l * this.rowStride;
            int idx4 = n1h * this.sliceStride + k * this.rowStride;
            a[idx3] = (double)0.5F * (a[idx4] - a[idx3]);
            a[idx4] -= a[idx3];
            a[idx3 + 1] = (double)0.5F * (a[idx4 + 1] + a[idx3 + 1]);
            a[idx4 + 1] -= a[idx3 + 1];
         }
      }

   }

   private void rdft3d_sub(int isgn, DoubleLargeArray a) {
      long n1h = this.slicesl >> 1;
      long n2h = this.rowsl >> 1;
      if (isgn < 0) {
         for(long i = 1L; i < n1h; ++i) {
            long j = this.slicesl - i;
            long idx1 = i * this.sliceStridel;
            long idx2 = j * this.sliceStridel;
            long idx3 = i * this.sliceStridel + n2h * this.rowStridel;
            long idx4 = j * this.sliceStridel + n2h * this.rowStridel;
            double xi = a.getDouble(idx1) - a.getDouble(idx2);
            a.setDouble(idx1, a.getDouble(idx1) + a.getDouble(idx2));
            a.setDouble(idx2, xi);
            xi = a.getDouble(idx2 + 1L) - a.getDouble(idx1 + 1L);
            a.setDouble(idx1 + 1L, a.getDouble(idx1 + 1L) + a.getDouble(idx2 + 1L));
            a.setDouble(idx2 + 1L, xi);
            xi = a.getDouble(idx3) - a.getDouble(idx4);
            a.setDouble(idx3, a.getDouble(idx3) + a.getDouble(idx4));
            a.setDouble(idx4, xi);
            xi = a.getDouble(idx4 + 1L) - a.getDouble(idx3 + 1L);
            a.setDouble(idx3 + 1L, a.getDouble(idx3 + 1L) + a.getDouble(idx4 + 1L));
            a.setDouble(idx4 + 1L, xi);

            for(long k = 1L; k < n2h; ++k) {
               long l = this.rowsl - k;
               idx1 = i * this.sliceStridel + k * this.rowStridel;
               idx2 = j * this.sliceStridel + l * this.rowStridel;
               xi = a.getDouble(idx1) - a.getDouble(idx2);
               a.setDouble(idx1, a.getDouble(idx1) + a.getDouble(idx2));
               a.setDouble(idx2, xi);
               xi = a.getDouble(idx2 + 1L) - a.getDouble(idx1 + 1L);
               a.setDouble(idx1 + 1L, a.getDouble(idx1 + 1L) + a.getDouble(idx2 + 1L));
               a.setDouble(idx2 + 1L, xi);
               idx3 = j * this.sliceStridel + k * this.rowStridel;
               idx4 = i * this.sliceStridel + l * this.rowStridel;
               xi = a.getDouble(idx3) - a.getDouble(idx4);
               a.setDouble(idx3, a.getDouble(idx3) + a.getDouble(idx4));
               a.setDouble(idx4, xi);
               xi = a.getDouble(idx4 + 1L) - a.getDouble(idx3 + 1L);
               a.setDouble(idx3 + 1L, a.getDouble(idx3 + 1L) + a.getDouble(idx4 + 1L));
               a.setDouble(idx4 + 1L, xi);
            }
         }

         for(long k = 1L; k < n2h; ++k) {
            long l = this.rowsl - k;
            long idx1 = k * this.rowStridel;
            long idx2 = l * this.rowStridel;
            double xi = a.getDouble(idx1) - a.getDouble(idx2);
            a.setDouble(idx1, a.getDouble(idx1) + a.getDouble(idx2));
            a.setDouble(idx2, xi);
            xi = a.getDouble(idx2 + 1L) - a.getDouble(idx1 + 1L);
            a.setDouble(idx1 + 1L, a.getDouble(idx1 + 1L) + a.getDouble(idx2 + 1L));
            a.setDouble(idx2 + 1L, xi);
            long idx3 = n1h * this.sliceStridel + k * this.rowStridel;
            long idx4 = n1h * this.sliceStridel + l * this.rowStridel;
            xi = a.getDouble(idx3) - a.getDouble(idx4);
            a.setDouble(idx3, a.getDouble(idx3) + a.getDouble(idx4));
            a.setDouble(idx4, xi);
            xi = a.getDouble(idx4 + 1L) - a.getDouble(idx3 + 1L);
            a.setDouble(idx3 + 1L, a.getDouble(idx3 + 1L) + a.getDouble(idx4 + 1L));
            a.setDouble(idx4 + 1L, xi);
         }
      } else {
         for(long i = 1L; i < n1h; ++i) {
            long j = this.slicesl - i;
            long idx1 = j * this.sliceStridel;
            long idx2 = i * this.sliceStridel;
            a.setDouble(idx1, (double)0.5F * (a.getDouble(idx2) - a.getDouble(idx1)));
            a.setDouble(idx2, a.getDouble(idx2) - a.getDouble(idx1));
            a.setDouble(idx1 + 1L, (double)0.5F * (a.getDouble(idx2 + 1L) + a.getDouble(idx1 + 1L)));
            a.setDouble(idx2 + 1L, a.getDouble(idx2 + 1L) - a.getDouble(idx1 + 1L));
            long idx3 = j * this.sliceStridel + n2h * this.rowStridel;
            long idx4 = i * this.sliceStridel + n2h * this.rowStridel;
            a.setDouble(idx3, (double)0.5F * (a.getDouble(idx4) - a.getDouble(idx3)));
            a.setDouble(idx4, a.getDouble(idx4) - a.getDouble(idx3));
            a.setDouble(idx3 + 1L, (double)0.5F * (a.getDouble(idx4 + 1L) + a.getDouble(idx3 + 1L)));
            a.setDouble(idx4 + 1L, a.getDouble(idx4 + 1L) - a.getDouble(idx3 + 1L));

            for(long k = 1L; k < n2h; ++k) {
               long l = this.rowsl - k;
               idx1 = j * this.sliceStridel + l * this.rowStridel;
               idx2 = i * this.sliceStridel + k * this.rowStridel;
               a.setDouble(idx1, (double)0.5F * (a.getDouble(idx2) - a.getDouble(idx1)));
               a.setDouble(idx2, a.getDouble(idx2) - a.getDouble(idx1));
               a.setDouble(idx1 + 1L, (double)0.5F * (a.getDouble(idx2 + 1L) + a.getDouble(idx1 + 1L)));
               a.setDouble(idx2 + 1L, a.getDouble(idx2 + 1L) - a.getDouble(idx1 + 1L));
               idx3 = i * this.sliceStridel + l * this.rowStridel;
               idx4 = j * this.sliceStridel + k * this.rowStridel;
               a.setDouble(idx3, (double)0.5F * (a.getDouble(idx4) - a.getDouble(idx3)));
               a.setDouble(idx4, a.getDouble(idx4) - a.getDouble(idx3));
               a.setDouble(idx3 + 1L, (double)0.5F * (a.getDouble(idx4 + 1L) + a.getDouble(idx3 + 1L)));
               a.setDouble(idx4 + 1L, a.getDouble(idx4 + 1L) - a.getDouble(idx3 + 1L));
            }
         }

         for(long k = 1L; k < n2h; ++k) {
            long l = this.rowsl - k;
            long idx1 = l * this.rowStridel;
            long idx2 = k * this.rowStridel;
            a.setDouble(idx1, (double)0.5F * (a.getDouble(idx2) - a.getDouble(idx1)));
            a.setDouble(idx2, a.getDouble(idx2) - a.getDouble(idx1));
            a.setDouble(idx1 + 1L, (double)0.5F * (a.getDouble(idx2 + 1L) + a.getDouble(idx1 + 1L)));
            a.setDouble(idx2 + 1L, a.getDouble(idx2 + 1L) - a.getDouble(idx1 + 1L));
            long idx3 = n1h * this.sliceStridel + l * this.rowStridel;
            long idx4 = n1h * this.sliceStridel + k * this.rowStridel;
            a.setDouble(idx3, (double)0.5F * (a.getDouble(idx4) - a.getDouble(idx3)));
            a.setDouble(idx4, a.getDouble(idx4) - a.getDouble(idx3));
            a.setDouble(idx3 + 1L, (double)0.5F * (a.getDouble(idx4 + 1L) + a.getDouble(idx3 + 1L)));
            a.setDouble(idx4 + 1L, a.getDouble(idx4 + 1L) - a.getDouble(idx3 + 1L));
         }
      }

   }

   private void rdft3d_sub(int isgn, double[][][] a) {
      int n1h = this.slices >> 1;
      int n2h = this.rows >> 1;
      if (isgn < 0) {
         for(int i = 1; i < n1h; ++i) {
            int j = this.slices - i;
            double xi = a[i][0][0] - a[j][0][0];
            a[i][0][0] += a[j][0][0];
            a[j][0][0] = xi;
            xi = a[j][0][1] - a[i][0][1];
            a[i][0][1] += a[j][0][1];
            a[j][0][1] = xi;
            xi = a[i][n2h][0] - a[j][n2h][0];
            a[i][n2h][0] += a[j][n2h][0];
            a[j][n2h][0] = xi;
            xi = a[j][n2h][1] - a[i][n2h][1];
            a[i][n2h][1] += a[j][n2h][1];
            a[j][n2h][1] = xi;

            for(int k = 1; k < n2h; ++k) {
               int l = this.rows - k;
               xi = a[i][k][0] - a[j][l][0];
               a[i][k][0] += a[j][l][0];
               a[j][l][0] = xi;
               xi = a[j][l][1] - a[i][k][1];
               a[i][k][1] += a[j][l][1];
               a[j][l][1] = xi;
               xi = a[j][k][0] - a[i][l][0];
               a[j][k][0] += a[i][l][0];
               a[i][l][0] = xi;
               xi = a[i][l][1] - a[j][k][1];
               a[j][k][1] += a[i][l][1];
               a[i][l][1] = xi;
            }
         }

         for(int k = 1; k < n2h; ++k) {
            int l = this.rows - k;
            double xi = a[0][k][0] - a[0][l][0];
            a[0][k][0] += a[0][l][0];
            a[0][l][0] = xi;
            xi = a[0][l][1] - a[0][k][1];
            a[0][k][1] += a[0][l][1];
            a[0][l][1] = xi;
            xi = a[n1h][k][0] - a[n1h][l][0];
            a[n1h][k][0] += a[n1h][l][0];
            a[n1h][l][0] = xi;
            xi = a[n1h][l][1] - a[n1h][k][1];
            a[n1h][k][1] += a[n1h][l][1];
            a[n1h][l][1] = xi;
         }
      } else {
         for(int i = 1; i < n1h; ++i) {
            int j = this.slices - i;
            a[j][0][0] = (double)0.5F * (a[i][0][0] - a[j][0][0]);
            a[i][0][0] -= a[j][0][0];
            a[j][0][1] = (double)0.5F * (a[i][0][1] + a[j][0][1]);
            a[i][0][1] -= a[j][0][1];
            a[j][n2h][0] = (double)0.5F * (a[i][n2h][0] - a[j][n2h][0]);
            a[i][n2h][0] -= a[j][n2h][0];
            a[j][n2h][1] = (double)0.5F * (a[i][n2h][1] + a[j][n2h][1]);
            a[i][n2h][1] -= a[j][n2h][1];

            for(int k = 1; k < n2h; ++k) {
               int l = this.rows - k;
               a[j][l][0] = (double)0.5F * (a[i][k][0] - a[j][l][0]);
               a[i][k][0] -= a[j][l][0];
               a[j][l][1] = (double)0.5F * (a[i][k][1] + a[j][l][1]);
               a[i][k][1] -= a[j][l][1];
               a[i][l][0] = (double)0.5F * (a[j][k][0] - a[i][l][0]);
               a[j][k][0] -= a[i][l][0];
               a[i][l][1] = (double)0.5F * (a[j][k][1] + a[i][l][1]);
               a[j][k][1] -= a[i][l][1];
            }
         }

         for(int k = 1; k < n2h; ++k) {
            int l = this.rows - k;
            a[0][l][0] = (double)0.5F * (a[0][k][0] - a[0][l][0]);
            a[0][k][0] -= a[0][l][0];
            a[0][l][1] = (double)0.5F * (a[0][k][1] + a[0][l][1]);
            a[0][k][1] -= a[0][l][1];
            a[n1h][l][0] = (double)0.5F * (a[n1h][k][0] - a[n1h][l][0]);
            a[n1h][k][0] -= a[n1h][l][0];
            a[n1h][l][1] = (double)0.5F * (a[n1h][k][1] + a[n1h][l][1]);
            a[n1h][k][1] -= a[n1h][l][1];
         }
      }

   }

   private void fillSymmetric(final double[][][] a) {
      final int twon3 = 2 * this.columns;
      final int n2d2 = this.rows / 2;
      int n1d2 = this.slices / 2;
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.slices >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices;

                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        int idx2 = (DoubleFFT_3D.this.rows - r) % DoubleFFT_3D.this.rows;

                        for(int c = 1; c < DoubleFFT_3D.this.columns; c += 2) {
                           int idx3 = twon3 - c;
                           a[idx1][idx2][idx3] = -a[s][r][c + 2];
                           a[idx1][idx2][idx3 - 1] = a[s][r][c + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx2 = DoubleFFT_3D.this.rows - r;
                        a[idx1][r][DoubleFFT_3D.this.columns] = a[s][idx2][1];
                        a[s][idx2][DoubleFFT_3D.this.columns] = a[s][idx2][1];
                        a[idx1][r][DoubleFFT_3D.this.columns + 1] = -a[s][idx2][0];
                        a[s][idx2][DoubleFFT_3D.this.columns + 1] = a[s][idx2][0];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx2 = DoubleFFT_3D.this.rows - r;
                        a[idx1][idx2][0] = a[s][r][0];
                        a[idx1][idx2][1] = -a[s][r][1];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            int idx1 = (this.slices - s) % this.slices;

            for(int r = 0; r < this.rows; ++r) {
               int idx2 = (this.rows - r) % this.rows;

               for(int c = 1; c < this.columns; c += 2) {
                  int idx3 = twon3 - c;
                  a[idx1][idx2][idx3] = -a[s][r][c + 2];
                  a[idx1][idx2][idx3 - 1] = a[s][r][c + 1];
               }
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx1 = (this.slices - s) % this.slices;

            for(int r = 1; r < n2d2; ++r) {
               int idx2 = this.rows - r;
               a[idx1][r][this.columns] = a[s][idx2][1];
               a[s][idx2][this.columns] = a[s][idx2][1];
               a[idx1][r][this.columns + 1] = -a[s][idx2][0];
               a[s][idx2][this.columns + 1] = a[s][idx2][0];
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx1 = (this.slices - s) % this.slices;

            for(int r = 1; r < n2d2; ++r) {
               int idx2 = this.rows - r;
               a[idx1][idx2][0] = a[s][r][0];
               a[idx1][idx2][1] = -a[s][r][1];
            }
         }
      }

      for(int s = 1; s < n1d2; ++s) {
         int idx1 = this.slices - s;
         a[s][0][this.columns] = a[idx1][0][1];
         a[idx1][0][this.columns] = a[idx1][0][1];
         a[s][0][this.columns + 1] = -a[idx1][0][0];
         a[idx1][0][this.columns + 1] = a[idx1][0][0];
         a[s][n2d2][this.columns] = a[idx1][n2d2][1];
         a[idx1][n2d2][this.columns] = a[idx1][n2d2][1];
         a[s][n2d2][this.columns + 1] = -a[idx1][n2d2][0];
         a[idx1][n2d2][this.columns + 1] = a[idx1][n2d2][0];
         a[idx1][0][0] = a[s][0][0];
         a[idx1][0][1] = -a[s][0][1];
         a[idx1][n2d2][0] = a[s][n2d2][0];
         a[idx1][n2d2][1] = -a[s][n2d2][1];
      }

      a[0][0][this.columns] = a[0][0][1];
      a[0][0][1] = (double)0.0F;
      a[0][n2d2][this.columns] = a[0][n2d2][1];
      a[0][n2d2][1] = (double)0.0F;
      a[n1d2][0][this.columns] = a[n1d2][0][1];
      a[n1d2][0][1] = (double)0.0F;
      a[n1d2][n2d2][this.columns] = a[n1d2][n2d2][1];
      a[n1d2][n2d2][1] = (double)0.0F;
      a[n1d2][0][this.columns + 1] = (double)0.0F;
      a[n1d2][n2d2][this.columns + 1] = (double)0.0F;
   }

   private void fillSymmetric(final double[] a) {
      final int twon3 = 2 * this.columns;
      final int n2d2 = this.rows / 2;
      int n1d2 = this.slices / 2;
      final int twoSliceStride = this.rows * twon3;
      final int twoRowStride = twon3;

      for(int s = this.slices - 1; s >= 1; --s) {
         int idx3 = s * this.sliceStride;
         int idx4 = 2 * idx3;

         for(int r = 0; r < this.rows; ++r) {
            int idx5 = r * this.rowStride;
            int idx6 = 2 * idx5;

            for(int c = 0; c < this.columns; c += 2) {
               int idx1 = idx3 + idx5 + c;
               int idx2 = idx4 + idx6 + c;
               a[idx2] = a[idx1];
               a[idx1] = (double)0.0F;
               ++idx1;
               ++idx2;
               a[idx2] = a[idx1];
               a[idx1] = (double)0.0F;
            }
         }
      }

      for(int r = 1; r < this.rows; ++r) {
         int idx3 = (this.rows - r) * this.rowStride;
         int idx4 = (this.rows - r) * twoRowStride;

         for(int c = 0; c < this.columns; c += 2) {
            int idx1 = idx3 + c;
            int idx2 = idx4 + c;
            a[idx2] = a[idx1];
            a[idx1] = (double)0.0F;
            ++idx1;
            ++idx2;
            a[idx2] = a[idx1];
            a[idx1] = (double)0.0F;
         }
      }

      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.slices >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx3 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices * twoSliceStride;
                     int idx5 = s * twoSliceStride;

                     for(int r = 0; r < DoubleFFT_3D.this.rows; ++r) {
                        int idx4 = (DoubleFFT_3D.this.rows - r) % DoubleFFT_3D.this.rows * twoRowStride;
                        int idx6 = r * twoRowStride;

                        for(int c = 1; c < DoubleFFT_3D.this.columns; c += 2) {
                           int idx1 = idx3 + idx4 + twon3 - c;
                           int idx2 = idx5 + idx6 + c;
                           a[idx1] = -a[idx2 + 2];
                           a[idx1 - 1] = a[idx2 + 1];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx5 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices * twoSliceStride;
                     int idx6 = s * twoSliceStride;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx4 = idx6 + (DoubleFFT_3D.this.rows - r) * twoRowStride;
                        int idx1 = idx5 + r * twoRowStride + DoubleFFT_3D.this.columns;
                        int idx2 = idx4 + DoubleFFT_3D.this.columns;
                        int idx3 = idx4 + 1;
                        a[idx1] = a[idx3];
                        a[idx2] = a[idx3];
                        a[idx1 + 1] = -a[idx4];
                        a[idx2 + 1] = a[idx4];
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx3 = (DoubleFFT_3D.this.slices - s) % DoubleFFT_3D.this.slices * twoSliceStride;
                     int idx4 = s * twoSliceStride;

                     for(int r = 1; r < n2d2; ++r) {
                        int idx1 = idx3 + (DoubleFFT_3D.this.rows - r) * twoRowStride;
                        int idx2 = idx4 + r * twoRowStride;
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
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            int idx3 = (this.slices - s) % this.slices * twoSliceStride;
            int idx5 = s * twoSliceStride;

            for(int r = 0; r < this.rows; ++r) {
               int idx4 = (this.rows - r) % this.rows * twoRowStride;
               int idx6 = r * twoRowStride;

               for(int c = 1; c < this.columns; c += 2) {
                  int idx1 = idx3 + idx4 + twon3 - c;
                  int idx2 = idx5 + idx6 + c;
                  a[idx1] = -a[idx2 + 2];
                  a[idx1 - 1] = a[idx2 + 1];
               }
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx5 = (this.slices - s) % this.slices * twoSliceStride;
            int idx6 = s * twoSliceStride;

            for(int r = 1; r < n2d2; ++r) {
               int idx4 = idx6 + (this.rows - r) * twoRowStride;
               int idx1 = idx5 + r * twoRowStride + this.columns;
               int idx2 = idx4 + this.columns;
               int idx3 = idx4 + 1;
               a[idx1] = a[idx3];
               a[idx2] = a[idx3];
               a[idx1 + 1] = -a[idx4];
               a[idx2 + 1] = a[idx4];
            }
         }

         for(int s = 0; s < this.slices; ++s) {
            int idx3 = (this.slices - s) % this.slices * twoSliceStride;
            int idx4 = s * twoSliceStride;

            for(int r = 1; r < n2d2; ++r) {
               int idx1 = idx3 + (this.rows - r) * twoRowStride;
               int idx2 = idx4 + r * twoRowStride;
               a[idx1] = a[idx2];
               a[idx1 + 1] = -a[idx2 + 1];
            }
         }
      }

      for(int s = 1; s < n1d2; ++s) {
         int idx1 = s * twoSliceStride;
         int idx2 = (this.slices - s) * twoSliceStride;
         int idx3 = n2d2 * twoRowStride;
         int idx4 = idx1 + idx3;
         int idx5 = idx2 + idx3;
         a[idx1 + this.columns] = a[idx2 + 1];
         a[idx2 + this.columns] = a[idx2 + 1];
         a[idx1 + this.columns + 1] = -a[idx2];
         a[idx2 + this.columns + 1] = a[idx2];
         a[idx4 + this.columns] = a[idx5 + 1];
         a[idx5 + this.columns] = a[idx5 + 1];
         a[idx4 + this.columns + 1] = -a[idx5];
         a[idx5 + this.columns + 1] = a[idx5];
         a[idx2] = a[idx1];
         a[idx2 + 1] = -a[idx1 + 1];
         a[idx5] = a[idx4];
         a[idx5 + 1] = -a[idx4 + 1];
      }

      a[this.columns] = a[1];
      a[1] = (double)0.0F;
      int idx1 = n2d2 * twoRowStride;
      int idx2 = n1d2 * twoSliceStride;
      int idx3 = idx1 + idx2;
      a[idx1 + this.columns] = a[idx1 + 1];
      a[idx1 + 1] = (double)0.0F;
      a[idx2 + this.columns] = a[idx2 + 1];
      a[idx2 + 1] = (double)0.0F;
      a[idx3 + this.columns] = a[idx3 + 1];
      a[idx3 + 1] = (double)0.0F;
      a[idx2 + this.columns + 1] = (double)0.0F;
      a[idx3 + this.columns + 1] = (double)0.0F;
   }

   private void fillSymmetric(final DoubleLargeArray a) {
      final long twon3 = 2L * this.columnsl;
      final long n2d2 = this.rowsl / 2L;
      long n1d2 = this.slicesl / 2L;
      final long twoSliceStride = this.rowsl * twon3;
      final long twoRowStride = twon3;

      for(long s = this.slicesl - 1L; s >= 1L; --s) {
         long idx3 = s * this.sliceStridel;
         long idx4 = 2L * idx3;

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx5 = r * this.rowStridel;
            long idx6 = 2L * idx5;

            for(long c = 0L; c < this.columnsl; c += 2L) {
               long idx1 = idx3 + idx5 + c;
               long idx2 = idx4 + idx6 + c;
               a.setDouble(idx2, a.getDouble(idx1));
               a.setDouble(idx1, (double)0.0F);
               ++idx1;
               ++idx2;
               a.setDouble(idx2, a.getDouble(idx1));
               a.setDouble(idx1, (double)0.0F);
            }
         }
      }

      for(long r = 1L; r < this.rowsl; ++r) {
         long idx3 = (this.rowsl - r) * this.rowStridel;
         long idx4 = (this.rowsl - r) * twoRowStride;

         for(long c = 0L; c < this.columnsl; c += 2L) {
            long idx1 = idx3 + c;
            long idx2 = idx4 + c;
            a.setDouble(idx2, a.getDouble(idx1));
            a.setDouble(idx1, (double)0.0F);
            ++idx1;
            ++idx2;
            a.setDouble(idx2, a.getDouble(idx1));
            a.setDouble(idx1, (double)0.0F);
         }
      }

      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (nthreads > 1 && this.useThreads && this.slicesl >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = this.slicesl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx3 = (DoubleFFT_3D.this.slicesl - s) % DoubleFFT_3D.this.slicesl * twoSliceStride;
                     long idx5 = s * twoSliceStride;

                     for(long r = 0L; r < DoubleFFT_3D.this.rowsl; ++r) {
                        long idx4 = (DoubleFFT_3D.this.rowsl - r) % DoubleFFT_3D.this.rowsl * twoRowStride;
                        long idx6 = r * twoRowStride;

                        for(long c = 1L; c < DoubleFFT_3D.this.columnsl; c += 2L) {
                           long idx1 = idx3 + idx4 + twon3 - c;
                           long idx2 = idx5 + idx6 + c;
                           a.setDouble(idx1, -a.getDouble(idx2 + 2L));
                           a.setDouble(idx1 - 1L, a.getDouble(idx2 + 1L));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx5 = (DoubleFFT_3D.this.slicesl - s) % DoubleFFT_3D.this.slicesl * twoSliceStride;
                     long idx6 = s * twoSliceStride;

                     for(long r = 1L; r < n2d2; ++r) {
                        long idx4 = idx6 + (DoubleFFT_3D.this.rowsl - r) * twoRowStride;
                        long idx1 = idx5 + r * twoRowStride + DoubleFFT_3D.this.columnsl;
                        long idx2 = idx4 + DoubleFFT_3D.this.columnsl;
                        long idx3 = idx4 + 1L;
                        a.setDouble(idx1, a.getDouble(idx3));
                        a.setDouble(idx2, a.getDouble(idx3));
                        a.setDouble(idx1 + 1L, -a.getDouble(idx4));
                        a.setDouble(idx2 + 1L, a.getDouble(idx4));
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx3 = (DoubleFFT_3D.this.slicesl - s) % DoubleFFT_3D.this.slicesl * twoSliceStride;
                     long idx4 = s * twoSliceStride;

                     for(long r = 1L; r < n2d2; ++r) {
                        long idx1 = idx3 + (DoubleFFT_3D.this.rowsl - r) * twoRowStride;
                        long idx2 = idx4 + r * twoRowStride;
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
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(DoubleFFT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long s = 0L; s < this.slicesl; ++s) {
            long idx3 = (this.slicesl - s) % this.slicesl * twoSliceStride;
            long idx5 = s * twoSliceStride;

            for(long r = 0L; r < this.rowsl; ++r) {
               long idx4 = (this.rowsl - r) % this.rowsl * twoRowStride;
               long idx6 = r * twoRowStride;

               for(long c = 1L; c < this.columnsl; c += 2L) {
                  long idx1 = idx3 + idx4 + twon3 - c;
                  long idx2 = idx5 + idx6 + c;
                  a.setDouble(idx1, -a.getDouble(idx2 + 2L));
                  a.setDouble(idx1 - 1L, a.getDouble(idx2 + 1L));
               }
            }
         }

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx5 = (this.slicesl - s) % this.slicesl * twoSliceStride;
            long idx6 = s * twoSliceStride;

            for(long r = 1L; r < n2d2; ++r) {
               long idx4 = idx6 + (this.rowsl - r) * twoRowStride;
               long idx1 = idx5 + r * twoRowStride + this.columnsl;
               long idx2 = idx4 + this.columnsl;
               long idx3 = idx4 + 1L;
               a.setDouble(idx1, a.getDouble(idx3));
               a.setDouble(idx2, a.getDouble(idx3));
               a.setDouble(idx1 + 1L, -a.getDouble(idx4));
               a.setDouble(idx2 + 1L, a.getDouble(idx4));
            }
         }

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx3 = (this.slicesl - s) % this.slicesl * twoSliceStride;
            long idx4 = s * twoSliceStride;

            for(long r = 1L; r < n2d2; ++r) {
               long idx1 = idx3 + (this.rowsl - r) * twoRowStride;
               long idx2 = idx4 + r * twoRowStride;
               a.setDouble(idx1, a.getDouble(idx2));
               a.setDouble(idx1 + 1L, -a.getDouble(idx2 + 1L));
            }
         }
      }

      for(long s = 1L; s < n1d2; ++s) {
         long idx1 = s * twoSliceStride;
         long idx2 = (this.slicesl - s) * twoSliceStride;
         long idx3 = n2d2 * twoRowStride;
         long idx4 = idx1 + idx3;
         long idx5 = idx2 + idx3;
         a.setDouble(idx1 + this.columnsl, a.getDouble(idx2 + 1L));
         a.setDouble(idx2 + this.columnsl, a.getDouble(idx2 + 1L));
         a.setDouble(idx1 + this.columnsl + 1L, -a.getDouble(idx2));
         a.setDouble(idx2 + this.columnsl + 1L, a.getDouble(idx2));
         a.setDouble(idx4 + this.columnsl, a.getDouble(idx5 + 1L));
         a.setDouble(idx5 + this.columnsl, a.getDouble(idx5 + 1L));
         a.setDouble(idx4 + this.columnsl + 1L, -a.getDouble(idx5));
         a.setDouble(idx5 + this.columnsl + 1L, a.getDouble(idx5));
         a.setDouble(idx2, a.getDouble(idx1));
         a.setDouble(idx2 + 1L, -a.getDouble(idx1 + 1L));
         a.setDouble(idx5, a.getDouble(idx4));
         a.setDouble(idx5 + 1L, -a.getDouble(idx4 + 1L));
      }

      a.setDouble(this.columnsl, a.getDouble(1L));
      a.setDouble(1L, (double)0.0F);
      long idx1 = n2d2 * twoRowStride;
      long idx2 = n1d2 * twoSliceStride;
      long idx3 = idx1 + idx2;
      a.setDouble(idx1 + this.columnsl, a.getDouble(idx1 + 1L));
      a.setDouble(idx1 + 1L, (double)0.0F);
      a.setDouble(idx2 + this.columnsl, a.getDouble(idx2 + 1L));
      a.setDouble(idx2 + 1L, (double)0.0F);
      a.setDouble(idx3 + this.columnsl, a.getDouble(idx3 + 1L));
      a.setDouble(idx3 + 1L, (double)0.0F);
      a.setDouble(idx2 + this.columnsl + 1L, (double)0.0F);
      a.setDouble(idx3 + this.columnsl + 1L, (double)0.0F);
   }
}
