package org.jtransforms.dst;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class FloatDST_3D {
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
   private FloatDST_1D dstSlices;
   private FloatDST_1D dstRows;
   private FloatDST_1D dstColumns;
   private boolean isPowerOfTwo = false;
   private boolean useThreads = false;

   public FloatDST_3D(long slices, long rows, long columns) {
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

         CommonUtils.setUseLargeArrays(slices * rows * columns > (long)LargeArray.getMaxSizeOf32bitArray());
         this.dstSlices = new FloatDST_1D(slices);
         if (slices == rows) {
            this.dstRows = this.dstSlices;
         } else {
            this.dstRows = new FloatDST_1D(rows);
         }

         if (slices == columns) {
            this.dstColumns = this.dstSlices;
         } else if (rows == columns) {
            this.dstColumns = this.dstRows;
         } else {
            this.dstColumns = new FloatDST_1D(columns);
         }

      } else {
         throw new IllegalArgumentException("slices, rows and columns must be greater than 1");
      }
   }

   public void forward(final float[] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(-1, (float[])a, scale);
            this.ddxt3db_subth(-1, (float[])a, scale);
         } else {
            this.ddxt3da_sub(-1, (float[])a, scale);
            this.ddxt3db_sub(-1, (float[])a, scale);
         }
      } else if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * FloatDST_3D.this.sliceStride;

                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.forward(a, idx1 + r * FloatDST_3D.this.rowStride, scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * FloatDST_3D.this.sliceStride;

                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * FloatDST_3D.this.rowStride + c;
                           temp[r] = a[idx3];
                        }

                        FloatDST_3D.this.dstRows.forward(temp, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * FloatDST_3D.this.rowStride + c;
                           a[idx3] = temp[r];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx1 = r * FloatDST_3D.this.rowStride;

                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx3 = s * FloatDST_3D.this.sliceStride + idx1 + c;
                           temp[s] = a[idx3];
                        }

                        FloatDST_3D.this.dstSlices.forward(temp, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx3 = s * FloatDST_3D.this.sliceStride + idx1 + c;
                           a[idx3] = temp[s];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            int idx1 = s * this.sliceStride;

            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.forward(a, idx1 + r * this.rowStride, scale);
            }
         }

         float[] temp = new float[this.rows];

         for(int s = 0; s < this.slices; ++s) {
            int idx1 = s * this.sliceStride;

            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx3 = idx1 + r * this.rowStride + c;
                  temp[r] = a[idx3];
               }

               this.dstRows.forward(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx3 = idx1 + r * this.rowStride + c;
                  a[idx3] = temp[r];
               }
            }
         }

         temp = new float[this.slices];

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.rowStride;

            for(int c = 0; c < this.columns; ++c) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx3 = s * this.sliceStride + idx1 + c;
                  temp[s] = a[idx3];
               }

               this.dstSlices.forward(temp, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx3 = s * this.sliceStride + idx1 + c;
                  a[idx3] = temp[s];
               }
            }
         }
      }

   }

   public void forward(final FloatLargeArray a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(-1, (FloatLargeArray)a, scale);
            this.ddxt3db_subth(-1, (FloatLargeArray)a, scale);
         } else {
            this.ddxt3da_sub(-1, (FloatLargeArray)a, scale);
            this.ddxt3db_sub(-1, (FloatLargeArray)a, scale);
         }
      } else if (nthreads > 1 && this.useThreads && this.slicesl >= (long)nthreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = this.slicesl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * FloatDST_3D.this.sliceStridel;

                     for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                        FloatDST_3D.this.dstColumns.forward(a, idx1 + r * FloatDST_3D.this.rowStridel, scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  FloatLargeArray temp = new FloatLargeArray(FloatDST_3D.this.rowsl, false);

                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * FloatDST_3D.this.sliceStridel;

                     for(long c = 0L; c < FloatDST_3D.this.columnsl; ++c) {
                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * FloatDST_3D.this.rowStridel + c;
                           temp.setFloat(r, a.getFloat(idx3));
                        }

                        FloatDST_3D.this.dstRows.forward(temp, scale);

                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * FloatDST_3D.this.rowStridel + c;
                           a.setFloat(idx3, temp.getFloat(r));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  FloatLargeArray temp = new FloatLargeArray(FloatDST_3D.this.slicesl, false);

                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx1 = r * FloatDST_3D.this.rowStridel;

                     for(long c = 0L; c < FloatDST_3D.this.columnsl; ++c) {
                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx3 = s * FloatDST_3D.this.sliceStridel + idx1 + c;
                           temp.setFloat(s, a.getFloat(idx3));
                        }

                        FloatDST_3D.this.dstSlices.forward(temp, scale);

                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx3 = s * FloatDST_3D.this.sliceStridel + idx1 + c;
                           a.setFloat(idx3, temp.getFloat(s));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long s = 0L; s < this.slicesl; ++s) {
            long idx1 = s * this.sliceStridel;

            for(long r = 0L; r < this.rowsl; ++r) {
               this.dstColumns.forward(a, idx1 + r * this.rowStridel, scale);
            }
         }

         FloatLargeArray temp = new FloatLargeArray(this.rowsl, false);

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx1 = s * this.sliceStridel;

            for(long c = 0L; c < this.columnsl; ++c) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx3 = idx1 + r * this.rowStridel + c;
                  temp.setFloat(r, a.getFloat(idx3));
               }

               this.dstRows.forward(temp, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx3 = idx1 + r * this.rowStridel + c;
                  a.setFloat(idx3, temp.getFloat(r));
               }
            }
         }

         temp = new FloatLargeArray(this.slicesl, false);

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * this.rowStridel;

            for(long c = 0L; c < this.columnsl; ++c) {
               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx3 = s * this.sliceStridel + idx1 + c;
                  temp.setFloat(s, a.getFloat(idx3));
               }

               this.dstSlices.forward(temp, scale);

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx3 = s * this.sliceStridel + idx1 + c;
                  a.setFloat(idx3, temp.getFloat(s));
               }
            }
         }
      }

   }

   public void forward(final float[][][] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(-1, (float[][][])a, scale);
            this.ddxt3db_subth(-1, (float[][][])a, scale);
         } else {
            this.ddxt3da_sub(-1, (float[][][])a, scale);
            this.ddxt3db_sub(-1, (float[][][])a, scale);
         }
      } else if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.forward(a[s][r], scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           temp[r] = a[s][r][c];
                        }

                        FloatDST_3D.this.dstRows.forward(temp, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           a[s][r][c] = temp[r];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           temp[s] = a[s][r][c];
                        }

                        FloatDST_3D.this.dstSlices.forward(temp, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           a[s][r][c] = temp[s];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.forward(a[s][r], scale);
            }
         }

         float[] temp = new float[this.rows];

         for(int s = 0; s < this.slices; ++s) {
            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  temp[r] = a[s][r][c];
               }

               this.dstRows.forward(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  a[s][r][c] = temp[r];
               }
            }
         }

         temp = new float[this.slices];

         for(int r = 0; r < this.rows; ++r) {
            for(int c = 0; c < this.columns; ++c) {
               for(int s = 0; s < this.slices; ++s) {
                  temp[s] = a[s][r][c];
               }

               this.dstSlices.forward(temp, scale);

               for(int s = 0; s < this.slices; ++s) {
                  a[s][r][c] = temp[s];
               }
            }
         }
      }

   }

   public void inverse(final float[] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(1, (float[])a, scale);
            this.ddxt3db_subth(1, (float[])a, scale);
         } else {
            this.ddxt3da_sub(1, (float[])a, scale);
            this.ddxt3db_sub(1, (float[])a, scale);
         }
      } else if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * FloatDST_3D.this.sliceStride;

                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.inverse(a, idx1 + r * FloatDST_3D.this.rowStride, scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     int idx1 = s * FloatDST_3D.this.sliceStride;

                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * FloatDST_3D.this.rowStride + c;
                           temp[r] = a[idx3];
                        }

                        FloatDST_3D.this.dstRows.inverse(temp, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx3 = idx1 + r * FloatDST_3D.this.rowStride + c;
                           a[idx3] = temp[r];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     int idx1 = r * FloatDST_3D.this.rowStride;

                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx3 = s * FloatDST_3D.this.sliceStride + idx1 + c;
                           temp[s] = a[idx3];
                        }

                        FloatDST_3D.this.dstSlices.inverse(temp, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx3 = s * FloatDST_3D.this.sliceStride + idx1 + c;
                           a[idx3] = temp[s];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            int idx1 = s * this.sliceStride;

            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.inverse(a, idx1 + r * this.rowStride, scale);
            }
         }

         float[] temp = new float[this.rows];

         for(int s = 0; s < this.slices; ++s) {
            int idx1 = s * this.sliceStride;

            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx3 = idx1 + r * this.rowStride + c;
                  temp[r] = a[idx3];
               }

               this.dstRows.inverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx3 = idx1 + r * this.rowStride + c;
                  a[idx3] = temp[r];
               }
            }
         }

         temp = new float[this.slices];

         for(int r = 0; r < this.rows; ++r) {
            int idx1 = r * this.rowStride;

            for(int c = 0; c < this.columns; ++c) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx3 = s * this.sliceStride + idx1 + c;
                  temp[s] = a[idx3];
               }

               this.dstSlices.inverse(temp, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx3 = s * this.sliceStride + idx1 + c;
                  a[idx3] = temp[s];
               }
            }
         }
      }

   }

   public void inverse(final FloatLargeArray a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(1, (FloatLargeArray)a, scale);
            this.ddxt3db_subth(1, (FloatLargeArray)a, scale);
         } else {
            this.ddxt3da_sub(1, (FloatLargeArray)a, scale);
            this.ddxt3db_sub(1, (FloatLargeArray)a, scale);
         }
      } else if (nthreads > 1 && this.useThreads && this.slicesl >= (long)nthreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
         Future<?>[] futures = new Future[nthreads];
         long p = this.slicesl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * (long)FloatDST_3D.this.sliceStride;

                     for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                        FloatDST_3D.this.dstColumns.inverse(a, idx1 + r * (long)FloatDST_3D.this.rowStride, scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final long firstSlice = (long)l * p;
            final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  FloatLargeArray temp = new FloatLargeArray(FloatDST_3D.this.rowsl, false);

                  for(long s = firstSlice; s < lastSlice; ++s) {
                     long idx1 = s * (long)FloatDST_3D.this.sliceStride;

                     for(long c = 0L; c < FloatDST_3D.this.columnsl; ++c) {
                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * (long)FloatDST_3D.this.rowStride + c;
                           temp.setFloat(r, a.getFloat(idx3));
                        }

                        FloatDST_3D.this.dstRows.inverse(temp, scale);

                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx3 = idx1 + r * (long)FloatDST_3D.this.rowStride + c;
                           a.setFloat(idx3, temp.getFloat(r));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rowsl / (long)nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final long firstRow = (long)l * p;
            final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  FloatLargeArray temp = new FloatLargeArray(FloatDST_3D.this.slicesl, false);

                  for(long r = firstRow; r < lastRow; ++r) {
                     long idx1 = r * (long)FloatDST_3D.this.rowStride;

                     for(long c = 0L; c < FloatDST_3D.this.columnsl; ++c) {
                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx3 = s * (long)FloatDST_3D.this.sliceStride + idx1 + c;
                           temp.setFloat(s, a.getFloat(idx3));
                        }

                        FloatDST_3D.this.dstSlices.inverse(temp, scale);

                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx3 = s * (long)FloatDST_3D.this.sliceStride + idx1 + c;
                           a.setFloat(idx3, temp.getFloat(s));
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(long s = 0L; s < this.slicesl; ++s) {
            long idx1 = s * (long)this.sliceStride;

            for(long r = 0L; r < this.rowsl; ++r) {
               this.dstColumns.inverse(a, idx1 + r * (long)this.rowStride, scale);
            }
         }

         FloatLargeArray temp = new FloatLargeArray(this.rowsl, false);

         for(long s = 0L; s < this.slicesl; ++s) {
            long idx1 = s * (long)this.sliceStride;

            for(long c = 0L; c < this.columnsl; ++c) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx3 = idx1 + r * (long)this.rowStride + c;
                  temp.setFloat(r, a.getFloat(idx3));
               }

               this.dstRows.inverse(temp, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx3 = idx1 + r * (long)this.rowStride + c;
                  a.setFloat(idx3, temp.getFloat(r));
               }
            }
         }

         temp = new FloatLargeArray(this.slicesl, false);

         for(long r = 0L; r < this.rowsl; ++r) {
            long idx1 = r * (long)this.rowStride;

            for(long c = 0L; c < this.columnsl; ++c) {
               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx3 = s * (long)this.sliceStride + idx1 + c;
                  temp.setFloat(s, a.getFloat(idx3));
               }

               this.dstSlices.inverse(temp, scale);

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx3 = s * (long)this.sliceStride + idx1 + c;
                  a.setFloat(idx3, temp.getFloat(s));
               }
            }
         }
      }

   }

   public void inverse(final float[][][] a, final boolean scale) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(1, (float[][][])a, scale);
            this.ddxt3db_subth(1, (float[][][])a, scale);
         } else {
            this.ddxt3da_sub(1, (float[][][])a, scale);
            this.ddxt3db_sub(1, (float[][][])a, scale);
         }
      } else if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
         Future<?>[] futures = new Future[nthreads];
         int p = this.slices / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.inverse(a[s][r], scale);
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         for(int l = 0; l < nthreads; ++l) {
            final int firstSlice = l * p;
            final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.rows];

                  for(int s = firstSlice; s < lastSlice; ++s) {
                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           temp[r] = a[s][r][c];
                        }

                        FloatDST_3D.this.dstRows.inverse(temp, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           a[s][r][c] = temp[r];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }

         p = this.rows / nthreads;

         for(int l = 0; l < nthreads; ++l) {
            final int firstRow = l * p;
            final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
            futures[l] = ConcurrencyUtils.submit(new Runnable() {
               public void run() {
                  float[] temp = new float[FloatDST_3D.this.slices];

                  for(int r = firstRow; r < lastRow; ++r) {
                     for(int c = 0; c < FloatDST_3D.this.columns; ++c) {
                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           temp[s] = a[s][r][c];
                        }

                        FloatDST_3D.this.dstSlices.inverse(temp, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           a[s][r][c] = temp[s];
                        }
                     }
                  }

               }
            });
         }

         try {
            ConcurrencyUtils.waitForCompletion(futures);
         } catch (InterruptedException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         } catch (ExecutionException ex) {
            Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.inverse(a[s][r], scale);
            }
         }

         float[] temp = new float[this.rows];

         for(int s = 0; s < this.slices; ++s) {
            for(int c = 0; c < this.columns; ++c) {
               for(int r = 0; r < this.rows; ++r) {
                  temp[r] = a[s][r][c];
               }

               this.dstRows.inverse(temp, scale);

               for(int r = 0; r < this.rows; ++r) {
                  a[s][r][c] = temp[r];
               }
            }
         }

         temp = new float[this.slices];

         for(int r = 0; r < this.rows; ++r) {
            for(int c = 0; c < this.columns; ++c) {
               for(int s = 0; s < this.slices; ++s) {
                  temp[s] = a[s][r][c];
               }

               this.dstSlices.inverse(temp, scale);

               for(int s = 0; s < this.slices; ++s) {
                  a[s][r][c] = temp[s];
               }
            }
         }
      }

   }

   private void ddxt3da_sub(int isgn, float[] a, boolean scale) {
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      }

      float[] t = new float[nt];
      if (isgn == -1) {
         for(int s = 0; s < this.slices; ++s) {
            int idx0 = s * this.sliceStride;

            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.forward(a, idx0 + r * this.rowStride, scale);
            }

            if (this.columns > 2) {
               for(int c = 0; c < this.columns; c += 4) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx1 = idx0 + r * this.rowStride + c;
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
                     int idx1 = idx0 + r * this.rowStride + c;
                     int idx2 = this.rows + r;
                     a[idx1] = t[r];
                     a[idx1 + 1] = t[idx2];
                     a[idx1 + 2] = t[idx2 + this.rows];
                     a[idx1 + 3] = t[idx2 + 2 * this.rows];
                  }
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  t[r] = a[idx1];
                  t[this.rows + r] = a[idx1 + 1];
               }

               this.dstRows.forward(t, 0, scale);
               this.dstRows.forward(t, this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  a[idx1] = t[r];
                  a[idx1 + 1] = t[this.rows + r];
               }
            }
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            int idx0 = s * this.sliceStride;

            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.inverse(a, idx0 + r * this.rowStride, scale);
            }

            if (this.columns > 2) {
               for(int c = 0; c < this.columns; c += 4) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx1 = idx0 + r * this.rowStride + c;
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
                     int idx1 = idx0 + r * this.rowStride + c;
                     int idx2 = this.rows + r;
                     a[idx1] = t[r];
                     a[idx1 + 1] = t[idx2];
                     a[idx1 + 2] = t[idx2 + this.rows];
                     a[idx1 + 3] = t[idx2 + 2 * this.rows];
                  }
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  t[r] = a[idx1];
                  t[this.rows + r] = a[idx1 + 1];
               }

               this.dstRows.inverse(t, 0, scale);
               this.dstRows.inverse(t, this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  int idx1 = idx0 + r * this.rowStride;
                  a[idx1] = t[r];
                  a[idx1 + 1] = t[this.rows + r];
               }
            }
         }
      }

   }

   private void ddxt3da_sub(int isgn, FloatLargeArray a, boolean scale) {
      long nt = 4L * this.rowsl;
      if (this.columnsl == 2L) {
         nt >>= 1;
      }

      FloatLargeArray t = new FloatLargeArray(nt);
      if (isgn == -1) {
         for(long s = 0L; s < this.slicesl; ++s) {
            long idx0 = s * this.sliceStridel;

            for(long r = 0L; r < this.rowsl; ++r) {
               this.dstColumns.forward(a, idx0 + r * (long)this.rowStride, scale);
            }

            if (this.columnsl > 2L) {
               for(long c = 0L; c < this.columnsl; c += 4L) {
                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx1 = idx0 + r * this.rowStridel + c;
                     long idx2 = this.rowsl + r;
                     t.setFloat(r, a.getFloat(idx1));
                     t.setFloat(idx2, a.getFloat(idx1 + 1L));
                     t.setFloat(idx2 + this.rowsl, a.getFloat(idx1 + 2L));
                     t.setFloat(idx2 + 2L * this.rowsl, a.getFloat(idx1 + 3L));
                  }

                  this.dstRows.forward(t, 0L, scale);
                  this.dstRows.forward(t, this.rowsl, scale);
                  this.dstRows.forward(t, 2L * this.rowsl, scale);
                  this.dstRows.forward(t, 3L * this.rowsl, scale);

                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx1 = idx0 + r * this.rowStridel + c;
                     long idx2 = this.rowsl + r;
                     a.setFloat(idx1, t.getFloat(r));
                     a.setFloat(idx1 + 1L, t.getFloat(idx2));
                     a.setFloat(idx1 + 2L, t.getFloat(idx2 + this.rowsl));
                     a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * this.rowsl));
                  }
               }
            } else if (this.columnsl == 2L) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = idx0 + r * this.rowStridel;
                  t.setFloat(r, a.getFloat(idx1));
                  t.setFloat(this.rowsl + r, a.getFloat(idx1 + 1L));
               }

               this.dstRows.forward(t, 0L, scale);
               this.dstRows.forward(t, this.rowsl, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = idx0 + r * this.rowStridel;
                  a.setFloat(idx1, t.getFloat(r));
                  a.setFloat(idx1 + 1L, t.getFloat(this.rowsl + r));
               }
            }
         }
      } else {
         for(long s = 0L; s < this.slicesl; ++s) {
            long idx0 = s * this.sliceStridel;

            for(long r = 0L; r < this.rowsl; ++r) {
               this.dstColumns.inverse(a, idx0 + r * this.rowStridel, scale);
            }

            if (this.columnsl > 2L) {
               for(long c = 0L; c < this.columnsl; c += 4L) {
                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx1 = idx0 + r * this.rowStridel + c;
                     long idx2 = this.rowsl + r;
                     t.setFloat(r, a.getFloat(idx1));
                     t.setFloat(idx2, a.getFloat(idx1 + 1L));
                     t.setFloat(idx2 + this.rowsl, a.getFloat(idx1 + 2L));
                     t.setFloat(idx2 + 2L * this.rowsl, a.getFloat(idx1 + 3L));
                  }

                  this.dstRows.inverse(t, 0L, scale);
                  this.dstRows.inverse(t, this.rowsl, scale);
                  this.dstRows.inverse(t, 2L * this.rowsl, scale);
                  this.dstRows.inverse(t, 3L * this.rowsl, scale);

                  for(long r = 0L; r < this.rowsl; ++r) {
                     long idx1 = idx0 + r * this.rowStridel + c;
                     long idx2 = this.rowsl + r;
                     a.setFloat(idx1, t.getFloat(r));
                     a.setFloat(idx1 + 1L, t.getFloat(idx2));
                     a.setFloat(idx1 + 2L, t.getFloat(idx2 + this.rowsl));
                     a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * this.rowsl));
                  }
               }
            } else if (this.columnsl == 2L) {
               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = idx0 + r * this.rowStridel;
                  t.setFloat(r, a.getFloat(idx1));
                  t.setFloat(this.rowsl + r, a.getFloat(idx1 + 1L));
               }

               this.dstRows.inverse(t, 0L, scale);
               this.dstRows.inverse(t, this.rowsl, scale);

               for(long r = 0L; r < this.rowsl; ++r) {
                  long idx1 = idx0 + r * this.rowStridel;
                  a.setFloat(idx1, t.getFloat(r));
                  a.setFloat(idx1 + 1L, t.getFloat(this.rowsl + r));
               }
            }
         }
      }

   }

   private void ddxt3da_sub(int isgn, float[][][] a, boolean scale) {
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      }

      float[] t = new float[nt];
      if (isgn == -1) {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.forward(a[s][r], scale);
            }

            if (this.columns > 2) {
               for(int c = 0; c < this.columns; c += 4) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = this.rows + r;
                     t[r] = a[s][r][c];
                     t[idx2] = a[s][r][c + 1];
                     t[idx2 + this.rows] = a[s][r][c + 2];
                     t[idx2 + 2 * this.rows] = a[s][r][c + 3];
                  }

                  this.dstRows.forward(t, 0, scale);
                  this.dstRows.forward(t, this.rows, scale);
                  this.dstRows.forward(t, 2 * this.rows, scale);
                  this.dstRows.forward(t, 3 * this.rows, scale);

                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = this.rows + r;
                     a[s][r][c] = t[r];
                     a[s][r][c + 1] = t[idx2];
                     a[s][r][c + 2] = t[idx2 + this.rows];
                     a[s][r][c + 3] = t[idx2 + 2 * this.rows];
                  }
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  t[r] = a[s][r][0];
                  t[this.rows + r] = a[s][r][1];
               }

               this.dstRows.forward(t, 0, scale);
               this.dstRows.forward(t, this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  a[s][r][0] = t[r];
                  a[s][r][1] = t[this.rows + r];
               }
            }
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.dstColumns.inverse(a[s][r], scale);
            }

            if (this.columns > 2) {
               for(int c = 0; c < this.columns; c += 4) {
                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = this.rows + r;
                     t[r] = a[s][r][c];
                     t[idx2] = a[s][r][c + 1];
                     t[idx2 + this.rows] = a[s][r][c + 2];
                     t[idx2 + 2 * this.rows] = a[s][r][c + 3];
                  }

                  this.dstRows.inverse(t, 0, scale);
                  this.dstRows.inverse(t, this.rows, scale);
                  this.dstRows.inverse(t, 2 * this.rows, scale);
                  this.dstRows.inverse(t, 3 * this.rows, scale);

                  for(int r = 0; r < this.rows; ++r) {
                     int idx2 = this.rows + r;
                     a[s][r][c] = t[r];
                     a[s][r][c + 1] = t[idx2];
                     a[s][r][c + 2] = t[idx2 + this.rows];
                     a[s][r][c + 3] = t[idx2 + 2 * this.rows];
                  }
               }
            } else if (this.columns == 2) {
               for(int r = 0; r < this.rows; ++r) {
                  t[r] = a[s][r][0];
                  t[this.rows + r] = a[s][r][1];
               }

               this.dstRows.inverse(t, 0, scale);
               this.dstRows.inverse(t, this.rows, scale);

               for(int r = 0; r < this.rows; ++r) {
                  a[s][r][0] = t[r];
                  a[s][r][1] = t[this.rows + r];
               }
            }
         }
      }

   }

   private void ddxt3db_sub(int isgn, float[] a, boolean scale) {
      int nt = 4 * this.slices;
      if (this.columns == 2) {
         nt >>= 1;
      }

      float[] t = new float[nt];
      if (isgn == -1) {
         if (this.columns > 2) {
            for(int r = 0; r < this.rows; ++r) {
               int idx0 = r * this.rowStride;

               for(int c = 0; c < this.columns; c += 4) {
                  for(int s = 0; s < this.slices; ++s) {
                     int idx1 = s * this.sliceStride + idx0 + c;
                     int idx2 = this.slices + s;
                     t[s] = a[idx1];
                     t[idx2] = a[idx1 + 1];
                     t[idx2 + this.slices] = a[idx1 + 2];
                     t[idx2 + 2 * this.slices] = a[idx1 + 3];
                  }

                  this.dstSlices.forward(t, 0, scale);
                  this.dstSlices.forward(t, this.slices, scale);
                  this.dstSlices.forward(t, 2 * this.slices, scale);
                  this.dstSlices.forward(t, 3 * this.slices, scale);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx1 = s * this.sliceStride + idx0 + c;
                     int idx2 = this.slices + s;
                     a[idx1] = t[s];
                     a[idx1 + 1] = t[idx2];
                     a[idx1 + 2] = t[idx2 + this.slices];
                     a[idx1 + 3] = t[idx2 + 2 * this.slices];
                  }
               }
            }
         } else if (this.columns == 2) {
            for(int r = 0; r < this.rows; ++r) {
               int idx0 = r * this.rowStride;

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0;
                  t[s] = a[idx1];
                  t[this.slices + s] = a[idx1 + 1];
               }

               this.dstSlices.forward(t, 0, scale);
               this.dstSlices.forward(t, this.slices, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0;
                  a[idx1] = t[s];
                  a[idx1 + 1] = t[this.slices + s];
               }
            }
         }
      } else if (this.columns > 2) {
         for(int r = 0; r < this.rows; ++r) {
            int idx0 = r * this.rowStride;

            for(int c = 0; c < this.columns; c += 4) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0 + c;
                  int idx2 = this.slices + s;
                  t[s] = a[idx1];
                  t[idx2] = a[idx1 + 1];
                  t[idx2 + this.slices] = a[idx1 + 2];
                  t[idx2 + 2 * this.slices] = a[idx1 + 3];
               }

               this.dstSlices.inverse(t, 0, scale);
               this.dstSlices.inverse(t, this.slices, scale);
               this.dstSlices.inverse(t, 2 * this.slices, scale);
               this.dstSlices.inverse(t, 3 * this.slices, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx1 = s * this.sliceStride + idx0 + c;
                  int idx2 = this.slices + s;
                  a[idx1] = t[s];
                  a[idx1 + 1] = t[idx2];
                  a[idx1 + 2] = t[idx2 + this.slices];
                  a[idx1 + 3] = t[idx2 + 2 * this.slices];
               }
            }
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            int idx0 = r * this.rowStride;

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride + idx0;
               t[s] = a[idx1];
               t[this.slices + s] = a[idx1 + 1];
            }

            this.dstSlices.inverse(t, 0, scale);
            this.dstSlices.inverse(t, this.slices, scale);

            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride + idx0;
               a[idx1] = t[s];
               a[idx1 + 1] = t[this.slices + s];
            }
         }
      }

   }

   private void ddxt3db_sub(int isgn, FloatLargeArray a, boolean scale) {
      long nt = 4L * this.slicesl;
      if (this.columnsl == 2L) {
         nt >>= 1;
      }

      FloatLargeArray t = new FloatLargeArray(nt);
      if (isgn == -1) {
         if (this.columnsl > 2L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx0 = r * this.rowStridel;

               for(long c = 0L; c < this.columnsl; c += 4L) {
                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx1 = s * this.sliceStridel + idx0 + c;
                     long idx2 = this.slicesl + s;
                     t.setFloat(s, a.getFloat(idx1));
                     t.setFloat(idx2, a.getFloat(idx1 + 1L));
                     t.setFloat(idx2 + this.slicesl, a.getFloat(idx1 + 2L));
                     t.setFloat(idx2 + 2L * this.slicesl, a.getFloat(idx1 + 3L));
                  }

                  this.dstSlices.forward(t, 0L, scale);
                  this.dstSlices.forward(t, this.slicesl, scale);
                  this.dstSlices.forward(t, 2L * this.slicesl, scale);
                  this.dstSlices.forward(t, 3L * this.slicesl, scale);

                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx1 = s * this.sliceStridel + idx0 + c;
                     long idx2 = this.slicesl + s;
                     a.setFloat(idx1, t.getFloat(s));
                     a.setFloat(idx1 + 1L, t.getFloat(idx2));
                     a.setFloat(idx1 + 2L, t.getFloat(idx2 + this.slicesl));
                     a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * this.slicesl));
                  }
               }
            }
         } else if (this.columnsl == 2L) {
            for(long r = 0L; r < this.rowsl; ++r) {
               long idx0 = r * this.rowStridel;

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx1 = s * this.sliceStridel + idx0;
                  t.setFloat(s, a.getFloat(idx1));
                  t.setFloat(this.slicesl + s, a.getFloat(idx1 + 1L));
               }

               this.dstSlices.forward(t, 0L, scale);
               this.dstSlices.forward(t, this.slicesl, scale);

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx1 = s * this.sliceStridel + idx0;
                  a.setFloat(idx1, t.getFloat(s));
                  a.setFloat(idx1 + 1L, t.getFloat(this.slicesl + s));
               }
            }
         }
      } else if (this.columnsl > 2L) {
         for(long r = 0L; r < this.rowsl; ++r) {
            long idx0 = r * this.rowStridel;

            for(long c = 0L; c < this.columnsl; c += 4L) {
               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx1 = s * this.sliceStridel + idx0 + c;
                  long idx2 = this.slicesl + s;
                  t.setFloat(s, a.getFloat(idx1));
                  t.setFloat(idx2, a.getFloat(idx1 + 1L));
                  t.setFloat(idx2 + this.slicesl, a.getFloat(idx1 + 2L));
                  t.setFloat(idx2 + 2L * this.slicesl, a.getFloat(idx1 + 3L));
               }

               this.dstSlices.inverse(t, 0L, scale);
               this.dstSlices.inverse(t, this.slicesl, scale);
               this.dstSlices.inverse(t, 2L * this.slicesl, scale);
               this.dstSlices.inverse(t, 3L * this.slicesl, scale);

               for(long s = 0L; s < this.slicesl; ++s) {
                  long idx1 = s * this.sliceStridel + idx0 + c;
                  long idx2 = this.slicesl + s;
                  a.setFloat(idx1, t.getFloat(s));
                  a.setFloat(idx1 + 1L, t.getFloat(idx2));
                  a.setFloat(idx1 + 2L, t.getFloat(idx2 + this.slicesl));
                  a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * this.slicesl));
               }
            }
         }
      } else if (this.columnsl == 2L) {
         for(long r = 0L; r < this.rowsl; ++r) {
            long idx0 = r * this.rowStridel;

            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * this.sliceStridel + idx0;
               t.setFloat(s, a.getFloat(idx1));
               t.setFloat(this.slicesl + s, a.getFloat(idx1 + 1L));
            }

            this.dstSlices.inverse(t, 0L, scale);
            this.dstSlices.inverse(t, this.slicesl, scale);

            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * this.sliceStridel + idx0;
               a.setFloat(idx1, t.getFloat(s));
               a.setFloat(idx1 + 1L, t.getFloat(this.slicesl + s));
            }
         }
      }

   }

   private void ddxt3db_sub(int isgn, float[][][] a, boolean scale) {
      int nt = 4 * this.slices;
      if (this.columns == 2) {
         nt >>= 1;
      }

      float[] t = new float[nt];
      if (isgn == -1) {
         if (this.columns > 2) {
            for(int r = 0; r < this.rows; ++r) {
               for(int c = 0; c < this.columns; c += 4) {
                  for(int s = 0; s < this.slices; ++s) {
                     int idx2 = this.slices + s;
                     t[s] = a[s][r][c];
                     t[idx2] = a[s][r][c + 1];
                     t[idx2 + this.slices] = a[s][r][c + 2];
                     t[idx2 + 2 * this.slices] = a[s][r][c + 3];
                  }

                  this.dstSlices.forward(t, 0, scale);
                  this.dstSlices.forward(t, this.slices, scale);
                  this.dstSlices.forward(t, 2 * this.slices, scale);
                  this.dstSlices.forward(t, 3 * this.slices, scale);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx2 = this.slices + s;
                     a[s][r][c] = t[s];
                     a[s][r][c + 1] = t[idx2];
                     a[s][r][c + 2] = t[idx2 + this.slices];
                     a[s][r][c + 3] = t[idx2 + 2 * this.slices];
                  }
               }
            }
         } else if (this.columns == 2) {
            for(int r = 0; r < this.rows; ++r) {
               for(int s = 0; s < this.slices; ++s) {
                  t[s] = a[s][r][0];
                  t[this.slices + s] = a[s][r][1];
               }

               this.dstSlices.forward(t, 0, scale);
               this.dstSlices.forward(t, this.slices, scale);

               for(int s = 0; s < this.slices; ++s) {
                  a[s][r][0] = t[s];
                  a[s][r][1] = t[this.slices + s];
               }
            }
         }
      } else if (this.columns > 2) {
         for(int r = 0; r < this.rows; ++r) {
            for(int c = 0; c < this.columns; c += 4) {
               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = this.slices + s;
                  t[s] = a[s][r][c];
                  t[idx2] = a[s][r][c + 1];
                  t[idx2 + this.slices] = a[s][r][c + 2];
                  t[idx2 + 2 * this.slices] = a[s][r][c + 3];
               }

               this.dstSlices.inverse(t, 0, scale);
               this.dstSlices.inverse(t, this.slices, scale);
               this.dstSlices.inverse(t, 2 * this.slices, scale);
               this.dstSlices.inverse(t, 3 * this.slices, scale);

               for(int s = 0; s < this.slices; ++s) {
                  int idx2 = this.slices + s;
                  a[s][r][c] = t[s];
                  a[s][r][c + 1] = t[idx2];
                  a[s][r][c + 2] = t[idx2 + this.slices];
                  a[s][r][c + 3] = t[idx2 + 2 * this.slices];
               }
            }
         }
      } else if (this.columns == 2) {
         for(int r = 0; r < this.rows; ++r) {
            for(int s = 0; s < this.slices; ++s) {
               t[s] = a[s][r][0];
               t[this.slices + s] = a[s][r][1];
            }

            this.dstSlices.inverse(t, 0, scale);
            this.dstSlices.inverse(t, this.slices, scale);

            for(int s = 0; s < this.slices; ++s) {
               a[s][r][0] = t[s];
               a[s][r][1] = t[this.slices + s];
            }
         }
      }

   }

   private void ddxt3da_subth(final int isgn, final float[] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.slices ? this.slices : ConcurrencyUtils.getNumberOfThreads();
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               float[] t = new float[ntf];
               if (isgn == -1) {
                  for(int s = i; s < FloatDST_3D.this.slices; s += nthreads) {
                     int idx0 = s * FloatDST_3D.this.sliceStride;

                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.forward(a, idx0 + r * FloatDST_3D.this.rowStride, scale);
                     }

                     if (FloatDST_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDST_3D.this.rowStride + c;
                              int idx2 = FloatDST_3D.this.rows + r;
                              t[r] = a[idx1];
                              t[idx2] = a[idx1 + 1];
                              t[idx2 + FloatDST_3D.this.rows] = a[idx1 + 2];
                              t[idx2 + 2 * FloatDST_3D.this.rows] = a[idx1 + 3];
                           }

                           FloatDST_3D.this.dstRows.forward(t, 0, scale);
                           FloatDST_3D.this.dstRows.forward(t, FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.forward(t, 2 * FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.forward(t, 3 * FloatDST_3D.this.rows, scale);

                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDST_3D.this.rowStride + c;
                              int idx2 = FloatDST_3D.this.rows + r;
                              a[idx1] = t[r];
                              a[idx1 + 1] = t[idx2];
                              a[idx1 + 2] = t[idx2 + FloatDST_3D.this.rows];
                              a[idx1 + 3] = t[idx2 + 2 * FloatDST_3D.this.rows];
                           }
                        }
                     } else if (FloatDST_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDST_3D.this.rowStride;
                           t[r] = a[idx1];
                           t[FloatDST_3D.this.rows + r] = a[idx1 + 1];
                        }

                        FloatDST_3D.this.dstRows.forward(t, 0, scale);
                        FloatDST_3D.this.dstRows.forward(t, FloatDST_3D.this.rows, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDST_3D.this.rowStride;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[FloatDST_3D.this.rows + r];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < FloatDST_3D.this.slices; s += nthreads) {
                     int idx0 = s * FloatDST_3D.this.sliceStride;

                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.inverse(a, idx0 + r * FloatDST_3D.this.rowStride, scale);
                     }

                     if (FloatDST_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDST_3D.this.rowStride + c;
                              int idx2 = FloatDST_3D.this.rows + r;
                              t[r] = a[idx1];
                              t[idx2] = a[idx1 + 1];
                              t[idx2 + FloatDST_3D.this.rows] = a[idx1 + 2];
                              t[idx2 + 2 * FloatDST_3D.this.rows] = a[idx1 + 3];
                           }

                           FloatDST_3D.this.dstRows.inverse(t, 0, scale);
                           FloatDST_3D.this.dstRows.inverse(t, FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.inverse(t, 2 * FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.inverse(t, 3 * FloatDST_3D.this.rows, scale);

                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDST_3D.this.rowStride + c;
                              int idx2 = FloatDST_3D.this.rows + r;
                              a[idx1] = t[r];
                              a[idx1 + 1] = t[idx2];
                              a[idx1 + 2] = t[idx2 + FloatDST_3D.this.rows];
                              a[idx1 + 3] = t[idx2 + 2 * FloatDST_3D.this.rows];
                           }
                        }
                     } else if (FloatDST_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDST_3D.this.rowStride;
                           t[r] = a[idx1];
                           t[FloatDST_3D.this.rows + r] = a[idx1 + 1];
                        }

                        FloatDST_3D.this.dstRows.inverse(t, 0, scale);
                        FloatDST_3D.this.dstRows.inverse(t, FloatDST_3D.this.rows, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDST_3D.this.rowStride;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[FloatDST_3D.this.rows + r];
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
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt3da_subth(final int isgn, final FloatLargeArray a, final boolean scale) {
      final int nthreads = (int)((long)ConcurrencyUtils.getNumberOfThreads() > this.slicesl ? this.slicesl : (long)ConcurrencyUtils.getNumberOfThreads());
      long nt = 4L * this.rowsl;
      if (this.columnsl == 2L) {
         nt >>= 1;
      }

      final long ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(int i = 0; i < nthreads; ++i) {
         final long n0 = (long)i;
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               FloatLargeArray t = new FloatLargeArray(ntf);
               if (isgn == -1) {
                  for(long s = n0; s < FloatDST_3D.this.slicesl; s += (long)nthreads) {
                     long idx0 = s * FloatDST_3D.this.sliceStridel;

                     for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                        FloatDST_3D.this.dstColumns.forward(a, idx0 + r * FloatDST_3D.this.rowStridel, scale);
                     }

                     if (FloatDST_3D.this.columnsl > 2L) {
                        for(long c = 0L; c < FloatDST_3D.this.columnsl; c += 4L) {
                           for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * FloatDST_3D.this.rowStridel + c;
                              long idx2 = FloatDST_3D.this.rowsl + r;
                              t.setFloat(r, a.getFloat(idx1));
                              t.setFloat(idx2, a.getFloat(idx1 + 1L));
                              t.setFloat(idx2 + FloatDST_3D.this.rowsl, a.getFloat(idx1 + 2L));
                              t.setFloat(idx2 + 2L * FloatDST_3D.this.rowsl, a.getFloat(idx1 + 3L));
                           }

                           FloatDST_3D.this.dstRows.forward(t, 0L, scale);
                           FloatDST_3D.this.dstRows.forward(t, FloatDST_3D.this.rowsl, scale);
                           FloatDST_3D.this.dstRows.forward(t, 2L * FloatDST_3D.this.rowsl, scale);
                           FloatDST_3D.this.dstRows.forward(t, 3L * FloatDST_3D.this.rowsl, scale);

                           for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * FloatDST_3D.this.rowStridel + c;
                              long idx2 = FloatDST_3D.this.rowsl + r;
                              a.setFloat(idx1, t.getFloat(r));
                              a.setFloat(idx1 + 1L, t.getFloat(idx2));
                              a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDST_3D.this.rowsl));
                              a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDST_3D.this.rowsl));
                           }
                        }
                     } else if (FloatDST_3D.this.columnsl == 2L) {
                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * FloatDST_3D.this.rowStridel;
                           t.setFloat(r, a.getFloat(idx1));
                           t.setFloat(FloatDST_3D.this.rowsl + r, a.getFloat(idx1 + 1L));
                        }

                        FloatDST_3D.this.dstRows.forward(t, 0L, scale);
                        FloatDST_3D.this.dstRows.forward(t, FloatDST_3D.this.rowsl, scale);

                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * FloatDST_3D.this.rowStridel;
                           a.setFloat(idx1, t.getFloat(r));
                           a.setFloat(idx1 + 1L, t.getFloat(FloatDST_3D.this.rowsl + r));
                        }
                     }
                  }
               } else {
                  for(long s = n0; s < FloatDST_3D.this.slicesl; s += (long)nthreads) {
                     long idx0 = s * FloatDST_3D.this.sliceStridel;

                     for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                        FloatDST_3D.this.dstColumns.inverse(a, idx0 + r * FloatDST_3D.this.rowStridel, scale);
                     }

                     if (FloatDST_3D.this.columnsl > 2L) {
                        for(long c = 0L; c < FloatDST_3D.this.columnsl; c += 4L) {
                           for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * FloatDST_3D.this.rowStridel + c;
                              long idx2 = FloatDST_3D.this.rowsl + r;
                              t.setFloat(r, a.getFloat(idx1));
                              t.setFloat(idx2, a.getFloat(idx1 + 1L));
                              t.setFloat(idx2 + FloatDST_3D.this.rowsl, a.getFloat(idx1 + 2L));
                              t.setFloat(idx2 + 2L * FloatDST_3D.this.rowsl, a.getFloat(idx1 + 3L));
                           }

                           FloatDST_3D.this.dstRows.inverse(t, 0L, scale);
                           FloatDST_3D.this.dstRows.inverse(t, FloatDST_3D.this.rowsl, scale);
                           FloatDST_3D.this.dstRows.inverse(t, 2L * FloatDST_3D.this.rowsl, scale);
                           FloatDST_3D.this.dstRows.inverse(t, 3L * FloatDST_3D.this.rowsl, scale);

                           for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * FloatDST_3D.this.rowStridel + c;
                              long idx2 = FloatDST_3D.this.rowsl + r;
                              a.setFloat(idx1, t.getFloat(r));
                              a.setFloat(idx1 + 1L, t.getFloat(idx2));
                              a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDST_3D.this.rowsl));
                              a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDST_3D.this.rowsl));
                           }
                        }
                     } else if (FloatDST_3D.this.columnsl == 2L) {
                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * FloatDST_3D.this.rowStridel;
                           t.setFloat(r, a.getFloat(idx1));
                           t.setFloat(FloatDST_3D.this.rowsl + r, a.getFloat(idx1 + 1L));
                        }

                        FloatDST_3D.this.dstRows.inverse(t, 0L, scale);
                        FloatDST_3D.this.dstRows.inverse(t, FloatDST_3D.this.rowsl, scale);

                        for(long r = 0L; r < FloatDST_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * FloatDST_3D.this.rowStridel;
                           a.setFloat(idx1, t.getFloat(r));
                           a.setFloat(idx1 + 1L, t.getFloat(FloatDST_3D.this.rowsl + r));
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
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt3da_subth(final int isgn, final float[][][] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.slices ? this.slices : ConcurrencyUtils.getNumberOfThreads();
      int nt = 4 * this.rows;
      if (this.columns == 2) {
         nt >>= 1;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               float[] t = new float[ntf];
               if (isgn == -1) {
                  for(int s = i; s < FloatDST_3D.this.slices; s += nthreads) {
                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.forward(a[s][r], scale);
                     }

                     if (FloatDST_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx2 = FloatDST_3D.this.rows + r;
                              t[r] = a[s][r][c];
                              t[idx2] = a[s][r][c + 1];
                              t[idx2 + FloatDST_3D.this.rows] = a[s][r][c + 2];
                              t[idx2 + 2 * FloatDST_3D.this.rows] = a[s][r][c + 3];
                           }

                           FloatDST_3D.this.dstRows.forward(t, 0, scale);
                           FloatDST_3D.this.dstRows.forward(t, FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.forward(t, 2 * FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.forward(t, 3 * FloatDST_3D.this.rows, scale);

                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx2 = FloatDST_3D.this.rows + r;
                              a[s][r][c] = t[r];
                              a[s][r][c + 1] = t[idx2];
                              a[s][r][c + 2] = t[idx2 + FloatDST_3D.this.rows];
                              a[s][r][c + 3] = t[idx2 + 2 * FloatDST_3D.this.rows];
                           }
                        }
                     } else if (FloatDST_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           t[r] = a[s][r][0];
                           t[FloatDST_3D.this.rows + r] = a[s][r][1];
                        }

                        FloatDST_3D.this.dstRows.forward(t, 0, scale);
                        FloatDST_3D.this.dstRows.forward(t, FloatDST_3D.this.rows, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           a[s][r][0] = t[r];
                           a[s][r][1] = t[FloatDST_3D.this.rows + r];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < FloatDST_3D.this.slices; s += nthreads) {
                     for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                        FloatDST_3D.this.dstColumns.inverse(a[s][r], scale);
                     }

                     if (FloatDST_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx2 = FloatDST_3D.this.rows + r;
                              t[r] = a[s][r][c];
                              t[idx2] = a[s][r][c + 1];
                              t[idx2 + FloatDST_3D.this.rows] = a[s][r][c + 2];
                              t[idx2 + 2 * FloatDST_3D.this.rows] = a[s][r][c + 3];
                           }

                           FloatDST_3D.this.dstRows.inverse(t, 0, scale);
                           FloatDST_3D.this.dstRows.inverse(t, FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.inverse(t, 2 * FloatDST_3D.this.rows, scale);
                           FloatDST_3D.this.dstRows.inverse(t, 3 * FloatDST_3D.this.rows, scale);

                           for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                              int idx2 = FloatDST_3D.this.rows + r;
                              a[s][r][c] = t[r];
                              a[s][r][c + 1] = t[idx2];
                              a[s][r][c + 2] = t[idx2 + FloatDST_3D.this.rows];
                              a[s][r][c + 3] = t[idx2 + 2 * FloatDST_3D.this.rows];
                           }
                        }
                     } else if (FloatDST_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           t[r] = a[s][r][0];
                           t[FloatDST_3D.this.rows + r] = a[s][r][1];
                        }

                        FloatDST_3D.this.dstRows.inverse(t, 0, scale);
                        FloatDST_3D.this.dstRows.inverse(t, FloatDST_3D.this.rows, scale);

                        for(int r = 0; r < FloatDST_3D.this.rows; ++r) {
                           a[s][r][0] = t[r];
                           a[s][r][1] = t[FloatDST_3D.this.rows + r];
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
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt3db_subth(final int isgn, final float[] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      int nt = 4 * this.slices;
      if (this.columns == 2) {
         nt >>= 1;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               float[] t = new float[ntf];
               if (isgn == -1) {
                  if (FloatDST_3D.this.columns > 2) {
                     for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                        int idx0 = r * FloatDST_3D.this.rowStride;

                        for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                           for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                              int idx1 = s * FloatDST_3D.this.sliceStride + idx0 + c;
                              int idx2 = FloatDST_3D.this.slices + s;
                              t[s] = a[idx1];
                              t[idx2] = a[idx1 + 1];
                              t[idx2 + FloatDST_3D.this.slices] = a[idx1 + 2];
                              t[idx2 + 2 * FloatDST_3D.this.slices] = a[idx1 + 3];
                           }

                           FloatDST_3D.this.dstSlices.forward(t, 0, scale);
                           FloatDST_3D.this.dstSlices.forward(t, FloatDST_3D.this.slices, scale);
                           FloatDST_3D.this.dstSlices.forward(t, 2 * FloatDST_3D.this.slices, scale);
                           FloatDST_3D.this.dstSlices.forward(t, 3 * FloatDST_3D.this.slices, scale);

                           for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                              int idx1 = s * FloatDST_3D.this.sliceStride + idx0 + c;
                              int idx2 = FloatDST_3D.this.slices + s;
                              a[idx1] = t[s];
                              a[idx1 + 1] = t[idx2];
                              a[idx1 + 2] = t[idx2 + FloatDST_3D.this.slices];
                              a[idx1 + 3] = t[idx2 + 2 * FloatDST_3D.this.slices];
                           }
                        }
                     }
                  } else if (FloatDST_3D.this.columns == 2) {
                     for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                        int idx0 = r * FloatDST_3D.this.rowStride;

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx1 = s * FloatDST_3D.this.sliceStride + idx0;
                           t[s] = a[idx1];
                           t[FloatDST_3D.this.slices + s] = a[idx1 + 1];
                        }

                        FloatDST_3D.this.dstSlices.forward(t, 0, scale);
                        FloatDST_3D.this.dstSlices.forward(t, FloatDST_3D.this.slices, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx1 = s * FloatDST_3D.this.sliceStride + idx0;
                           a[idx1] = t[s];
                           a[idx1 + 1] = t[FloatDST_3D.this.slices + s];
                        }
                     }
                  }
               } else if (FloatDST_3D.this.columns > 2) {
                  for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                     int idx0 = r * FloatDST_3D.this.rowStride;

                     for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx1 = s * FloatDST_3D.this.sliceStride + idx0 + c;
                           int idx2 = FloatDST_3D.this.slices + s;
                           t[s] = a[idx1];
                           t[idx2] = a[idx1 + 1];
                           t[idx2 + FloatDST_3D.this.slices] = a[idx1 + 2];
                           t[idx2 + 2 * FloatDST_3D.this.slices] = a[idx1 + 3];
                        }

                        FloatDST_3D.this.dstSlices.inverse(t, 0, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, FloatDST_3D.this.slices, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, 2 * FloatDST_3D.this.slices, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, 3 * FloatDST_3D.this.slices, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx1 = s * FloatDST_3D.this.sliceStride + idx0 + c;
                           int idx2 = FloatDST_3D.this.slices + s;
                           a[idx1] = t[s];
                           a[idx1 + 1] = t[idx2];
                           a[idx1 + 2] = t[idx2 + FloatDST_3D.this.slices];
                           a[idx1 + 3] = t[idx2 + 2 * FloatDST_3D.this.slices];
                        }
                     }
                  }
               } else if (FloatDST_3D.this.columns == 2) {
                  for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                     int idx0 = r * FloatDST_3D.this.rowStride;

                     for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                        int idx1 = s * FloatDST_3D.this.sliceStride + idx0;
                        t[s] = a[idx1];
                        t[FloatDST_3D.this.slices + s] = a[idx1 + 1];
                     }

                     FloatDST_3D.this.dstSlices.inverse(t, 0, scale);
                     FloatDST_3D.this.dstSlices.inverse(t, FloatDST_3D.this.slices, scale);

                     for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                        int idx1 = s * FloatDST_3D.this.sliceStride + idx0;
                        a[idx1] = t[s];
                        a[idx1 + 1] = t[FloatDST_3D.this.slices + s];
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt3db_subth(final int isgn, final FloatLargeArray a, final boolean scale) {
      final int nthreads = (int)((long)ConcurrencyUtils.getNumberOfThreads() > this.rowsl ? this.rowsl : (long)ConcurrencyUtils.getNumberOfThreads());
      long nt = 4L * this.slicesl;
      if (this.columnsl == 2L) {
         nt >>= 1;
      }

      final long ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(int i = 0; i < nthreads; ++i) {
         final long n0 = (long)i;
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               FloatLargeArray t = new FloatLargeArray(ntf);
               if (isgn == -1) {
                  if (FloatDST_3D.this.columnsl > 2L) {
                     for(long r = n0; r < FloatDST_3D.this.rowsl; r += (long)nthreads) {
                        long idx0 = r * FloatDST_3D.this.rowStridel;

                        for(long c = 0L; c < FloatDST_3D.this.columnsl; c += 4L) {
                           for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                              long idx1 = s * FloatDST_3D.this.sliceStridel + idx0 + c;
                              long idx2 = FloatDST_3D.this.slicesl + s;
                              t.setFloat(s, a.getFloat(idx1));
                              t.setFloat(idx2, a.getFloat(idx1 + 1L));
                              t.setFloat(idx2 + FloatDST_3D.this.slicesl, a.getFloat(idx1 + 2L));
                              t.setFloat(idx2 + 2L * FloatDST_3D.this.slicesl, a.getFloat(idx1 + 3L));
                           }

                           FloatDST_3D.this.dstSlices.forward(t, 0L, scale);
                           FloatDST_3D.this.dstSlices.forward(t, FloatDST_3D.this.slicesl, scale);
                           FloatDST_3D.this.dstSlices.forward(t, 2L * FloatDST_3D.this.slicesl, scale);
                           FloatDST_3D.this.dstSlices.forward(t, 3L * FloatDST_3D.this.slicesl, scale);

                           for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                              long idx1 = s * FloatDST_3D.this.sliceStridel + idx0 + c;
                              long idx2 = FloatDST_3D.this.slicesl + s;
                              a.setFloat(idx1, t.getFloat(s));
                              a.setFloat(idx1 + 1L, t.getFloat(idx2));
                              a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDST_3D.this.slicesl));
                              a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDST_3D.this.slicesl));
                           }
                        }
                     }
                  } else if (FloatDST_3D.this.columnsl == 2L) {
                     for(long r = n0; r < FloatDST_3D.this.rowsl; r += (long)nthreads) {
                        long idx0 = r * FloatDST_3D.this.rowStridel;

                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDST_3D.this.sliceStridel + idx0;
                           t.setFloat(s, a.getFloat(idx1));
                           t.setFloat(FloatDST_3D.this.slicesl + s, a.getFloat(idx1 + 1L));
                        }

                        FloatDST_3D.this.dstSlices.forward(t, 0L, scale);
                        FloatDST_3D.this.dstSlices.forward(t, FloatDST_3D.this.slicesl, scale);

                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDST_3D.this.sliceStridel + idx0;
                           a.setFloat(idx1, t.getFloat(s));
                           a.setFloat(idx1 + 1L, t.getFloat(FloatDST_3D.this.slicesl + s));
                        }
                     }
                  }
               } else if (FloatDST_3D.this.columnsl > 2L) {
                  for(long r = n0; r < FloatDST_3D.this.rowsl; r += (long)nthreads) {
                     long idx0 = r * FloatDST_3D.this.rowStridel;

                     for(long c = 0L; c < FloatDST_3D.this.columnsl; c += 4L) {
                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDST_3D.this.sliceStridel + idx0 + c;
                           long idx2 = FloatDST_3D.this.slicesl + s;
                           t.setFloat(s, a.getFloat(idx1));
                           t.setFloat(idx2, a.getFloat(idx1 + 1L));
                           t.setFloat(idx2 + FloatDST_3D.this.slicesl, a.getFloat(idx1 + 2L));
                           t.setFloat(idx2 + 2L * FloatDST_3D.this.slicesl, a.getFloat(idx1 + 3L));
                        }

                        FloatDST_3D.this.dstSlices.inverse(t, 0L, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, FloatDST_3D.this.slicesl, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, 2L * FloatDST_3D.this.slicesl, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, 3L * FloatDST_3D.this.slicesl, scale);

                        for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDST_3D.this.sliceStridel + idx0 + c;
                           long idx2 = FloatDST_3D.this.slicesl + s;
                           a.setFloat(idx1, t.getFloat(s));
                           a.setFloat(idx1 + 1L, t.getFloat(idx2));
                           a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDST_3D.this.slicesl));
                           a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDST_3D.this.slicesl));
                        }
                     }
                  }
               } else if (FloatDST_3D.this.columnsl == 2L) {
                  for(long r = n0; r < FloatDST_3D.this.rowsl; r += (long)nthreads) {
                     long idx0 = r * FloatDST_3D.this.rowStridel;

                     for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                        long idx1 = s * FloatDST_3D.this.sliceStridel + idx0;
                        t.setFloat(s, a.getFloat(idx1));
                        t.setFloat(FloatDST_3D.this.slicesl + s, a.getFloat(idx1 + 1L));
                     }

                     FloatDST_3D.this.dstSlices.inverse(t, 0L, scale);
                     FloatDST_3D.this.dstSlices.inverse(t, FloatDST_3D.this.slicesl, scale);

                     for(long s = 0L; s < FloatDST_3D.this.slicesl; ++s) {
                        long idx1 = s * FloatDST_3D.this.sliceStridel + idx0;
                        a.setFloat(idx1, t.getFloat(s));
                        a.setFloat(idx1 + 1L, t.getFloat(FloatDST_3D.this.slicesl + s));
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void ddxt3db_subth(final int isgn, final float[][][] a, final boolean scale) {
      final int nthreads = ConcurrencyUtils.getNumberOfThreads() > this.rows ? this.rows : ConcurrencyUtils.getNumberOfThreads();
      int nt = 4 * this.slices;
      if (this.columns == 2) {
         nt >>= 1;
      }

      final int ntf = nt;
      Future<?>[] futures = new Future[nthreads];

      for(final int i = 0; i < nthreads; ++i) {
         futures[i] = ConcurrencyUtils.submit(new Runnable() {
            public void run() {
               float[] t = new float[ntf];
               if (isgn == -1) {
                  if (FloatDST_3D.this.columns > 2) {
                     for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                        for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                           for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                              int idx2 = FloatDST_3D.this.slices + s;
                              t[s] = a[s][r][c];
                              t[idx2] = a[s][r][c + 1];
                              t[idx2 + FloatDST_3D.this.slices] = a[s][r][c + 2];
                              t[idx2 + 2 * FloatDST_3D.this.slices] = a[s][r][c + 3];
                           }

                           FloatDST_3D.this.dstSlices.forward(t, 0, scale);
                           FloatDST_3D.this.dstSlices.forward(t, FloatDST_3D.this.slices, scale);
                           FloatDST_3D.this.dstSlices.forward(t, 2 * FloatDST_3D.this.slices, scale);
                           FloatDST_3D.this.dstSlices.forward(t, 3 * FloatDST_3D.this.slices, scale);

                           for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                              int idx2 = FloatDST_3D.this.slices + s;
                              a[s][r][c] = t[s];
                              a[s][r][c + 1] = t[idx2];
                              a[s][r][c + 2] = t[idx2 + FloatDST_3D.this.slices];
                              a[s][r][c + 3] = t[idx2 + 2 * FloatDST_3D.this.slices];
                           }
                        }
                     }
                  } else if (FloatDST_3D.this.columns == 2) {
                     for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           t[s] = a[s][r][0];
                           t[FloatDST_3D.this.slices + s] = a[s][r][1];
                        }

                        FloatDST_3D.this.dstSlices.forward(t, 0, scale);
                        FloatDST_3D.this.dstSlices.forward(t, FloatDST_3D.this.slices, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           a[s][r][0] = t[s];
                           a[s][r][1] = t[FloatDST_3D.this.slices + s];
                        }
                     }
                  }
               } else if (FloatDST_3D.this.columns > 2) {
                  for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                     for(int c = 0; c < FloatDST_3D.this.columns; c += 4) {
                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx2 = FloatDST_3D.this.slices + s;
                           t[s] = a[s][r][c];
                           t[idx2] = a[s][r][c + 1];
                           t[idx2 + FloatDST_3D.this.slices] = a[s][r][c + 2];
                           t[idx2 + 2 * FloatDST_3D.this.slices] = a[s][r][c + 3];
                        }

                        FloatDST_3D.this.dstSlices.inverse(t, 0, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, FloatDST_3D.this.slices, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, 2 * FloatDST_3D.this.slices, scale);
                        FloatDST_3D.this.dstSlices.inverse(t, 3 * FloatDST_3D.this.slices, scale);

                        for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                           int idx2 = FloatDST_3D.this.slices + s;
                           a[s][r][c] = t[s];
                           a[s][r][c + 1] = t[idx2];
                           a[s][r][c + 2] = t[idx2 + FloatDST_3D.this.slices];
                           a[s][r][c + 3] = t[idx2 + 2 * FloatDST_3D.this.slices];
                        }
                     }
                  }
               } else if (FloatDST_3D.this.columns == 2) {
                  for(int r = i; r < FloatDST_3D.this.rows; r += nthreads) {
                     for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                        t[s] = a[s][r][0];
                        t[FloatDST_3D.this.slices + s] = a[s][r][1];
                     }

                     FloatDST_3D.this.dstSlices.inverse(t, 0, scale);
                     FloatDST_3D.this.dstSlices.inverse(t, FloatDST_3D.this.slices, scale);

                     for(int s = 0; s < FloatDST_3D.this.slices; ++s) {
                        a[s][r][0] = t[s];
                        a[s][r][1] = t[FloatDST_3D.this.slices + s];
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDST_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }
}
