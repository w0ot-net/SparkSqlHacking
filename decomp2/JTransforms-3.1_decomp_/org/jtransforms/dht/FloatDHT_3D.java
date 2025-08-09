package org.jtransforms.dht;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.utils.CommonUtils;
import pl.edu.icm.jlargearrays.ConcurrencyUtils;
import pl.edu.icm.jlargearrays.FloatLargeArray;
import pl.edu.icm.jlargearrays.LargeArray;

public class FloatDHT_3D {
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
   private FloatDHT_1D dhtSlices;
   private FloatDHT_1D dhtRows;
   private FloatDHT_1D dhtColumns;
   private boolean isPowerOfTwo = false;
   private boolean useThreads = false;

   public FloatDHT_3D(long slices, long rows, long columns) {
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
         this.dhtSlices = new FloatDHT_1D(slices);
         if (slices == rows) {
            this.dhtRows = this.dhtSlices;
         } else {
            this.dhtRows = new FloatDHT_1D(rows);
         }

         if (slices == columns) {
            this.dhtColumns = this.dhtSlices;
         } else if (rows == columns) {
            this.dhtColumns = this.dhtRows;
         } else {
            this.dhtColumns = new FloatDHT_1D(columns);
         }

      } else {
         throw new IllegalArgumentException("slices, rows and columns must be greater than 1");
      }
   }

   public void forward(final float[] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(-1, (float[])a, true);
            this.ddxt3db_subth(-1, (float[])a, true);
         } else {
            this.ddxt3da_sub(-1, (float[])a, true);
            this.ddxt3db_sub(-1, (float[])a, true);
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.slices / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * FloatDHT_3D.this.sliceStride;

                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           FloatDHT_3D.this.dhtColumns.forward(a, idx1 + r * FloatDHT_3D.this.rowStride);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.rows];

                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * FloatDHT_3D.this.sliceStride;

                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx3 = idx1 + r * FloatDHT_3D.this.rowStride + c;
                              temp[r] = a[idx3];
                           }

                           FloatDHT_3D.this.dhtRows.forward(temp);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx3 = idx1 + r * FloatDHT_3D.this.rowStride + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int startRow = l * p;
               final int stopRow;
               if (l == nthreads - 1) {
                  stopRow = this.rows;
               } else {
                  stopRow = startRow + p;
               }

               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.slices];

                     for(int r = startRow; r < stopRow; ++r) {
                        int idx1 = r * FloatDHT_3D.this.rowStride;

                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx3 = s * FloatDHT_3D.this.sliceStride + idx1 + c;
                              temp[s] = a[idx3];
                           }

                           FloatDHT_3D.this.dhtSlices.forward(temp);

                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx3 = s * FloatDHT_3D.this.sliceStride + idx1 + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride;

               for(int r = 0; r < this.rows; ++r) {
                  this.dhtColumns.forward(a, idx1 + r * this.rowStride);
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

                  this.dhtRows.forward(temp);

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

                  this.dhtSlices.forward(temp);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx3 = s * this.sliceStride + idx1 + c;
                     a[idx3] = temp[s];
                  }
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
            this.ddxt3da_subth(-1, (FloatLargeArray)a, true);
            this.ddxt3db_subth(-1, (FloatLargeArray)a, true);
         } else {
            this.ddxt3da_sub(-1, (FloatLargeArray)a, true);
            this.ddxt3db_sub(-1, (FloatLargeArray)a, true);
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.slicesl >= (long)nthreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.slicesl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * (long)FloatDHT_3D.this.sliceStride;

                        for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                           FloatDHT_3D.this.dhtColumns.forward(a, idx1 + r * (long)FloatDHT_3D.this.rowStride);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     FloatLargeArray temp = new FloatLargeArray(FloatDHT_3D.this.rowsl, false);

                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * (long)FloatDHT_3D.this.sliceStride;

                        for(long c = 0L; c < FloatDHT_3D.this.columnsl; ++c) {
                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + r * (long)FloatDHT_3D.this.rowStride + c;
                              temp.setFloat(r, a.getFloat(idx3));
                           }

                           FloatDHT_3D.this.dhtRows.forward(temp);

                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + r * (long)FloatDHT_3D.this.rowStride + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long startRow = (long)l * p;
               final long stopRow;
               if (l == nthreads - 1) {
                  stopRow = this.rowsl;
               } else {
                  stopRow = startRow + p;
               }

               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     FloatLargeArray temp = new FloatLargeArray(FloatDHT_3D.this.slicesl, false);

                     for(long r = startRow; r < stopRow; ++r) {
                        long idx1 = r * (long)FloatDHT_3D.this.rowStride;

                        for(long c = 0L; c < FloatDHT_3D.this.columnsl; ++c) {
                           for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                              long idx3 = s * (long)FloatDHT_3D.this.sliceStride + idx1 + c;
                              temp.setFloat(s, a.getFloat(idx3));
                           }

                           FloatDHT_3D.this.dhtSlices.forward(temp);

                           for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                              long idx3 = s * (long)FloatDHT_3D.this.sliceStride + idx1 + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * (long)this.sliceStride;

               for(long r = 0L; r < this.rowsl; ++r) {
                  this.dhtColumns.forward(a, idx1 + r * (long)this.rowStride);
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

                  this.dhtRows.forward(temp);

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

                  this.dhtSlices.forward(temp);

                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx3 = s * (long)this.sliceStride + idx1 + c;
                     a.setFloat(idx3, temp.getFloat(s));
                  }
               }
            }
         }

         this.yTransform(a);
      }

   }

   public void forward(final float[][][] a) {
      int nthreads = ConcurrencyUtils.getNumberOfThreads();
      if (this.isPowerOfTwo) {
         if (nthreads > 1 && this.useThreads) {
            this.ddxt3da_subth(-1, (float[][][])a, true);
            this.ddxt3db_subth(-1, (float[][][])a, true);
         } else {
            this.ddxt3da_sub(-1, (float[][][])a, true);
            this.ddxt3db_sub(-1, (float[][][])a, true);
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.slices / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int s = firstSlice; s < lastSlice; ++s) {
                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           FloatDHT_3D.this.dhtColumns.forward(a[s][r]);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.rows];

                     for(int s = firstSlice; s < lastSlice; ++s) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              temp[r] = a[s][r][c];
                           }

                           FloatDHT_3D.this.dhtRows.forward(temp);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.slices];

                     for(int r = firstRow; r < lastRow; ++r) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              temp[s] = a[s][r][c];
                           }

                           FloatDHT_3D.this.dhtSlices.forward(temp);

                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int s = 0; s < this.slices; ++s) {
               for(int r = 0; r < this.rows; ++r) {
                  this.dhtColumns.forward(a[s][r]);
               }
            }

            float[] temp = new float[this.rows];

            for(int s = 0; s < this.slices; ++s) {
               for(int c = 0; c < this.columns; ++c) {
                  for(int r = 0; r < this.rows; ++r) {
                     temp[r] = a[s][r][c];
                  }

                  this.dhtRows.forward(temp);

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

                  this.dhtSlices.forward(temp);

                  for(int s = 0; s < this.slices; ++s) {
                     a[s][r][c] = temp[s];
                  }
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
            this.ddxt3da_subth(1, (float[])a, scale);
            this.ddxt3db_subth(1, (float[])a, scale);
         } else {
            this.ddxt3da_sub(1, (float[])a, scale);
            this.ddxt3db_sub(1, (float[])a, scale);
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.slices / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * FloatDHT_3D.this.sliceStride;

                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           FloatDHT_3D.this.dhtColumns.inverse(a, idx1 + r * FloatDHT_3D.this.rowStride, scale);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.rows];

                     for(int s = firstSlice; s < lastSlice; ++s) {
                        int idx1 = s * FloatDHT_3D.this.sliceStride;

                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx3 = idx1 + r * FloatDHT_3D.this.rowStride + c;
                              temp[r] = a[idx3];
                           }

                           FloatDHT_3D.this.dhtRows.inverse(temp, scale);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx3 = idx1 + r * FloatDHT_3D.this.rowStride + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.slices];

                     for(int r = firstRow; r < lastRow; ++r) {
                        int idx1 = r * FloatDHT_3D.this.rowStride;

                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx3 = s * FloatDHT_3D.this.sliceStride + idx1 + c;
                              temp[s] = a[idx3];
                           }

                           FloatDHT_3D.this.dhtSlices.inverse(temp, scale);

                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx3 = s * FloatDHT_3D.this.sliceStride + idx1 + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int s = 0; s < this.slices; ++s) {
               int idx1 = s * this.sliceStride;

               for(int r = 0; r < this.rows; ++r) {
                  this.dhtColumns.inverse(a, idx1 + r * this.rowStride, scale);
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

                  this.dhtRows.inverse(temp, scale);

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

                  this.dhtSlices.inverse(temp, scale);

                  for(int s = 0; s < this.slices; ++s) {
                     int idx3 = s * this.sliceStride + idx1 + c;
                     a[idx3] = temp[s];
                  }
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
            this.ddxt3da_subth(1, (FloatLargeArray)a, scale);
            this.ddxt3db_subth(1, (FloatLargeArray)a, scale);
         } else {
            this.ddxt3da_sub(1, (FloatLargeArray)a, scale);
            this.ddxt3db_sub(1, (FloatLargeArray)a, scale);
         }

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.slicesl >= (long)nthreads && this.rowsl >= (long)nthreads && this.columnsl >= (long)nthreads) {
            Future<?>[] futures = new Future[nthreads];
            long p = this.slicesl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * FloatDHT_3D.this.sliceStridel;

                        for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                           FloatDHT_3D.this.dhtColumns.inverse(a, idx1 + r * FloatDHT_3D.this.rowStridel, scale);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final long firstSlice = (long)l * p;
               final long lastSlice = l == nthreads - 1 ? this.slicesl : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     FloatLargeArray temp = new FloatLargeArray(FloatDHT_3D.this.rowsl, false);

                     for(long s = firstSlice; s < lastSlice; ++s) {
                        long idx1 = s * FloatDHT_3D.this.sliceStridel;

                        for(long c = 0L; c < FloatDHT_3D.this.columnsl; ++c) {
                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + r * FloatDHT_3D.this.rowStridel + c;
                              temp.setFloat(r, a.getFloat(idx3));
                           }

                           FloatDHT_3D.this.dhtRows.inverse(temp, scale);

                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx3 = idx1 + r * FloatDHT_3D.this.rowStridel + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rowsl / (long)nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final long firstRow = (long)l * p;
               final long lastRow = l == nthreads - 1 ? this.rowsl : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     FloatLargeArray temp = new FloatLargeArray(FloatDHT_3D.this.slicesl, false);

                     for(long r = firstRow; r < lastRow; ++r) {
                        long idx1 = r * FloatDHT_3D.this.rowStridel;

                        for(long c = 0L; c < FloatDHT_3D.this.columnsl; ++c) {
                           for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                              long idx3 = s * FloatDHT_3D.this.sliceStridel + idx1 + c;
                              temp.setFloat(s, a.getFloat(idx3));
                           }

                           FloatDHT_3D.this.dhtSlices.inverse(temp, scale);

                           for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                              long idx3 = s * FloatDHT_3D.this.sliceStridel + idx1 + c;
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(long s = 0L; s < this.slicesl; ++s) {
               long idx1 = s * this.sliceStridel;

               for(long r = 0L; r < this.rowsl; ++r) {
                  this.dhtColumns.inverse(a, idx1 + r * this.rowStridel, scale);
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

                  this.dhtRows.inverse(temp, scale);

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

                  this.dhtSlices.inverse(temp, scale);

                  for(long s = 0L; s < this.slicesl; ++s) {
                     long idx3 = s * this.sliceStridel + idx1 + c;
                     a.setFloat(idx3, temp.getFloat(s));
                  }
               }
            }
         }

         this.yTransform(a);
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

         this.yTransform(a);
      } else {
         if (nthreads > 1 && this.useThreads && this.slices >= nthreads && this.rows >= nthreads && this.columns >= nthreads) {
            Future<?>[] futures = new Future[nthreads];
            int p = this.slices / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     for(int s = firstSlice; s < lastSlice; ++s) {
                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           FloatDHT_3D.this.dhtColumns.inverse(a[s][r], scale);
                        }
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(futures);
            } catch (InterruptedException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            for(int l = 0; l < nthreads; ++l) {
               final int firstSlice = l * p;
               final int lastSlice = l == nthreads - 1 ? this.slices : firstSlice + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.rows];

                     for(int s = firstSlice; s < lastSlice; ++s) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              temp[r] = a[s][r][c];
                           }

                           FloatDHT_3D.this.dhtRows.inverse(temp, scale);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }

            p = this.rows / nthreads;

            for(int l = 0; l < nthreads; ++l) {
               final int firstRow = l * p;
               final int lastRow = l == nthreads - 1 ? this.rows : firstRow + p;
               futures[l] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     float[] temp = new float[FloatDHT_3D.this.slices];

                     for(int r = firstRow; r < lastRow; ++r) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; ++c) {
                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              temp[s] = a[s][r][c];
                           }

                           FloatDHT_3D.this.dhtSlices.inverse(temp, scale);

                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
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
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            } catch (ExecutionException ex) {
               Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
            }
         } else {
            for(int s = 0; s < this.slices; ++s) {
               for(int r = 0; r < this.rows; ++r) {
                  this.dhtColumns.inverse(a[s][r], scale);
               }
            }

            float[] temp = new float[this.rows];

            for(int s = 0; s < this.slices; ++s) {
               for(int c = 0; c < this.columns; ++c) {
                  for(int r = 0; r < this.rows; ++r) {
                     temp[r] = a[s][r][c];
                  }

                  this.dhtRows.inverse(temp, scale);

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

                  this.dhtSlices.inverse(temp, scale);

                  for(int s = 0; s < this.slices; ++s) {
                     a[s][r][c] = temp[s];
                  }
               }
            }
         }

         this.yTransform(a);
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
               this.dhtColumns.forward(a, idx0 + r * this.rowStride);
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

                  this.dhtRows.forward(t, 0);
                  this.dhtRows.forward(t, this.rows);
                  this.dhtRows.forward(t, 2 * this.rows);
                  this.dhtRows.forward(t, 3 * this.rows);

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

               this.dhtRows.forward(t, 0);
               this.dhtRows.forward(t, this.rows);

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
               this.dhtColumns.inverse(a, idx0 + r * this.rowStride, scale);
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

                  this.dhtRows.inverse(t, 0, scale);
                  this.dhtRows.inverse(t, this.rows, scale);
                  this.dhtRows.inverse(t, 2 * this.rows, scale);
                  this.dhtRows.inverse(t, 3 * this.rows, scale);

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

               this.dhtRows.inverse(t, 0, scale);
               this.dhtRows.inverse(t, this.rows, scale);

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
               this.dhtColumns.forward(a, idx0 + r * this.rowStridel);
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

                  this.dhtRows.forward(t, 0L);
                  this.dhtRows.forward(t, this.rowsl);
                  this.dhtRows.forward(t, 2L * this.rowsl);
                  this.dhtRows.forward(t, 3L * this.rowsl);

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

               this.dhtRows.forward(t, 0L);
               this.dhtRows.forward(t, this.rowsl);

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
               this.dhtColumns.inverse(a, idx0 + r * this.rowStridel, scale);
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

                  this.dhtRows.inverse(t, 0L, scale);
                  this.dhtRows.inverse(t, this.rowsl, scale);
                  this.dhtRows.inverse(t, 2L * this.rowsl, scale);
                  this.dhtRows.inverse(t, 3L * this.rowsl, scale);

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

               this.dhtRows.inverse(t, 0L, scale);
               this.dhtRows.inverse(t, this.rowsl, scale);

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
      if (this.columnsl == 2L) {
         nt >>= 1;
      }

      float[] t = new float[nt];
      if (isgn == -1) {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.dhtColumns.forward(a[s][r]);
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

                  this.dhtRows.forward(t, 0);
                  this.dhtRows.forward(t, this.rows);
                  this.dhtRows.forward(t, 2 * this.rows);
                  this.dhtRows.forward(t, 3 * this.rows);

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

               this.dhtRows.forward(t, 0);
               this.dhtRows.forward(t, this.rows);

               for(int r = 0; r < this.rows; ++r) {
                  a[s][r][0] = t[r];
                  a[s][r][1] = t[this.rows + r];
               }
            }
         }
      } else {
         for(int s = 0; s < this.slices; ++s) {
            for(int r = 0; r < this.rows; ++r) {
               this.dhtColumns.inverse(a[s][r], scale);
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

                  this.dhtRows.inverse(t, 0, scale);
                  this.dhtRows.inverse(t, this.rows, scale);
                  this.dhtRows.inverse(t, 2 * this.rows, scale);
                  this.dhtRows.inverse(t, 3 * this.rows, scale);

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

               this.dhtRows.inverse(t, 0, scale);
               this.dhtRows.inverse(t, this.rows, scale);

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

                  this.dhtSlices.forward(t, 0);
                  this.dhtSlices.forward(t, this.slices);
                  this.dhtSlices.forward(t, 2 * this.slices);
                  this.dhtSlices.forward(t, 3 * this.slices);

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

               this.dhtSlices.forward(t, 0);
               this.dhtSlices.forward(t, this.slices);

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

               this.dhtSlices.inverse(t, 0, scale);
               this.dhtSlices.inverse(t, this.slices, scale);
               this.dhtSlices.inverse(t, 2 * this.slices, scale);
               this.dhtSlices.inverse(t, 3 * this.slices, scale);

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

            this.dhtSlices.inverse(t, 0, scale);
            this.dhtSlices.inverse(t, this.slices, scale);

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

                  this.dhtSlices.forward(t, 0L);
                  this.dhtSlices.forward(t, this.slicesl);
                  this.dhtSlices.forward(t, 2L * this.slicesl);
                  this.dhtSlices.forward(t, 3L * this.slicesl);

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

               this.dhtSlices.forward(t, 0L);
               this.dhtSlices.forward(t, this.slicesl);

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

               this.dhtSlices.inverse(t, 0L, scale);
               this.dhtSlices.inverse(t, this.slicesl, scale);
               this.dhtSlices.inverse(t, 2L * this.slicesl, scale);
               this.dhtSlices.inverse(t, 3L * this.slicesl, scale);

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

            this.dhtSlices.inverse(t, 0L, scale);
            this.dhtSlices.inverse(t, this.slicesl, scale);

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

                  this.dhtSlices.forward(t, 0);
                  this.dhtSlices.forward(t, this.slices);
                  this.dhtSlices.forward(t, 2 * this.slices);
                  this.dhtSlices.forward(t, 3 * this.slices);

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

               this.dhtSlices.forward(t, 0);
               this.dhtSlices.forward(t, this.slices);

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

               this.dhtSlices.inverse(t, 0, scale);
               this.dhtSlices.inverse(t, this.slices, scale);
               this.dhtSlices.inverse(t, 2 * this.slices, scale);
               this.dhtSlices.inverse(t, 3 * this.slices, scale);

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

            this.dhtSlices.inverse(t, 0, scale);
            this.dhtSlices.inverse(t, this.slices, scale);

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
                  for(int s = i; s < FloatDHT_3D.this.slices; s += nthreads) {
                     int idx0 = s * FloatDHT_3D.this.sliceStride;

                     for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                        FloatDHT_3D.this.dhtColumns.forward(a, idx0 + r * FloatDHT_3D.this.rowStride);
                     }

                     if (FloatDHT_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDHT_3D.this.rowStride + c;
                              int idx2 = FloatDHT_3D.this.rows + r;
                              t[r] = a[idx1];
                              t[idx2] = a[idx1 + 1];
                              t[idx2 + FloatDHT_3D.this.rows] = a[idx1 + 2];
                              t[idx2 + 2 * FloatDHT_3D.this.rows] = a[idx1 + 3];
                           }

                           FloatDHT_3D.this.dhtRows.forward(t, 0);
                           FloatDHT_3D.this.dhtRows.forward(t, FloatDHT_3D.this.rows);
                           FloatDHT_3D.this.dhtRows.forward(t, 2 * FloatDHT_3D.this.rows);
                           FloatDHT_3D.this.dhtRows.forward(t, 3 * FloatDHT_3D.this.rows);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDHT_3D.this.rowStride + c;
                              int idx2 = FloatDHT_3D.this.rows + r;
                              a[idx1] = t[r];
                              a[idx1 + 1] = t[idx2];
                              a[idx1 + 2] = t[idx2 + FloatDHT_3D.this.rows];
                              a[idx1 + 3] = t[idx2 + 2 * FloatDHT_3D.this.rows];
                           }
                        }
                     } else if (FloatDHT_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDHT_3D.this.rowStride;
                           t[r] = a[idx1];
                           t[FloatDHT_3D.this.rows + r] = a[idx1 + 1];
                        }

                        FloatDHT_3D.this.dhtRows.forward(t, 0);
                        FloatDHT_3D.this.dhtRows.forward(t, FloatDHT_3D.this.rows);

                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDHT_3D.this.rowStride;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[FloatDHT_3D.this.rows + r];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < FloatDHT_3D.this.slices; s += nthreads) {
                     int idx0 = s * FloatDHT_3D.this.sliceStride;

                     for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                        FloatDHT_3D.this.dhtColumns.inverse(a, idx0 + r * FloatDHT_3D.this.rowStride, scale);
                     }

                     if (FloatDHT_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDHT_3D.this.rowStride + c;
                              int idx2 = FloatDHT_3D.this.rows + r;
                              t[r] = a[idx1];
                              t[idx2] = a[idx1 + 1];
                              t[idx2 + FloatDHT_3D.this.rows] = a[idx1 + 2];
                              t[idx2 + 2 * FloatDHT_3D.this.rows] = a[idx1 + 3];
                           }

                           FloatDHT_3D.this.dhtRows.inverse(t, 0, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, FloatDHT_3D.this.rows, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, 2 * FloatDHT_3D.this.rows, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, 3 * FloatDHT_3D.this.rows, scale);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx1 = idx0 + r * FloatDHT_3D.this.rowStride + c;
                              int idx2 = FloatDHT_3D.this.rows + r;
                              a[idx1] = t[r];
                              a[idx1 + 1] = t[idx2];
                              a[idx1 + 2] = t[idx2 + FloatDHT_3D.this.rows];
                              a[idx1 + 3] = t[idx2 + 2 * FloatDHT_3D.this.rows];
                           }
                        }
                     } else if (FloatDHT_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDHT_3D.this.rowStride;
                           t[r] = a[idx1];
                           t[FloatDHT_3D.this.rows + r] = a[idx1 + 1];
                        }

                        FloatDHT_3D.this.dhtRows.inverse(t, 0, scale);
                        FloatDHT_3D.this.dhtRows.inverse(t, FloatDHT_3D.this.rows, scale);

                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           int idx1 = idx0 + r * FloatDHT_3D.this.rowStride;
                           a[idx1] = t[r];
                           a[idx1 + 1] = t[FloatDHT_3D.this.rows + r];
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
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
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
                  for(long s = n0; s < FloatDHT_3D.this.slicesl; s += (long)nthreads) {
                     long idx0 = s * (long)FloatDHT_3D.this.sliceStride;

                     for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                        FloatDHT_3D.this.dhtColumns.forward(a, idx0 + r * (long)FloatDHT_3D.this.rowStride);
                     }

                     if (FloatDHT_3D.this.columnsl > 2L) {
                        for(long c = 0L; c < FloatDHT_3D.this.columnsl; c += 4L) {
                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride + c;
                              long idx2 = FloatDHT_3D.this.rowsl + r;
                              t.setFloat(r, a.getFloat(idx1));
                              t.setFloat(idx2, a.getFloat(idx1 + 1L));
                              t.setFloat(idx2 + FloatDHT_3D.this.rowsl, a.getFloat(idx1 + 2L));
                              t.setFloat(idx2 + 2L * FloatDHT_3D.this.rowsl, a.getFloat(idx1 + 3L));
                           }

                           FloatDHT_3D.this.dhtRows.forward(t, 0L);
                           FloatDHT_3D.this.dhtRows.forward(t, FloatDHT_3D.this.rowsl);
                           FloatDHT_3D.this.dhtRows.forward(t, 2L * FloatDHT_3D.this.rowsl);
                           FloatDHT_3D.this.dhtRows.forward(t, 3L * FloatDHT_3D.this.rowsl);

                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride + c;
                              long idx2 = FloatDHT_3D.this.rowsl + r;
                              a.setFloat(idx1, t.getFloat(r));
                              a.setFloat(idx1 + 1L, t.getFloat(idx2));
                              a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDHT_3D.this.rowsl));
                              a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDHT_3D.this.rowsl));
                           }
                        }
                     } else if (FloatDHT_3D.this.columnsl == 2L) {
                        for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride;
                           t.setFloat(r, a.getFloat(idx1));
                           t.setFloat(FloatDHT_3D.this.rowsl + r, a.getFloat(idx1 + 1L));
                        }

                        FloatDHT_3D.this.dhtRows.forward(t, 0L);
                        FloatDHT_3D.this.dhtRows.forward(t, FloatDHT_3D.this.rowsl);

                        for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride;
                           a.setFloat(idx1, t.getFloat(r));
                           a.setFloat(idx1 + 1L, t.getFloat(FloatDHT_3D.this.rowsl + r));
                        }
                     }
                  }
               } else {
                  for(long s = n0; s < FloatDHT_3D.this.slicesl; s += (long)nthreads) {
                     long idx0 = s * (long)FloatDHT_3D.this.sliceStride;

                     for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                        FloatDHT_3D.this.dhtColumns.inverse(a, idx0 + r * (long)FloatDHT_3D.this.rowStride, scale);
                     }

                     if (FloatDHT_3D.this.columnsl > 2L) {
                        for(long c = 0L; c < FloatDHT_3D.this.columnsl; c += 4L) {
                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride + c;
                              long idx2 = FloatDHT_3D.this.rowsl + r;
                              t.setFloat(r, a.getFloat(idx1));
                              t.setFloat(idx2, a.getFloat(idx1 + 1L));
                              t.setFloat(idx2 + FloatDHT_3D.this.rowsl, a.getFloat(idx1 + 2L));
                              t.setFloat(idx2 + 2L * FloatDHT_3D.this.rowsl, a.getFloat(idx1 + 3L));
                           }

                           FloatDHT_3D.this.dhtRows.inverse(t, 0L, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, FloatDHT_3D.this.rowsl, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, 2L * FloatDHT_3D.this.rowsl, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, 3L * FloatDHT_3D.this.rowsl, scale);

                           for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                              long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride + c;
                              long idx2 = FloatDHT_3D.this.rowsl + r;
                              a.setFloat(idx1, t.getFloat(r));
                              a.setFloat(idx1 + 1L, t.getFloat(idx2));
                              a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDHT_3D.this.rowsl));
                              a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDHT_3D.this.rowsl));
                           }
                        }
                     } else if (FloatDHT_3D.this.columnsl == 2L) {
                        for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride;
                           t.setFloat(r, a.getFloat(idx1));
                           t.setFloat(FloatDHT_3D.this.rowsl + r, a.getFloat(idx1 + 1L));
                        }

                        FloatDHT_3D.this.dhtRows.inverse(t, 0L, scale);
                        FloatDHT_3D.this.dhtRows.inverse(t, FloatDHT_3D.this.rowsl, scale);

                        for(long r = 0L; r < FloatDHT_3D.this.rowsl; ++r) {
                           long idx1 = idx0 + r * (long)FloatDHT_3D.this.rowStride;
                           a.setFloat(idx1, t.getFloat(r));
                           a.setFloat(idx1 + 1L, t.getFloat(FloatDHT_3D.this.rowsl + r));
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
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
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
                  for(int s = i; s < FloatDHT_3D.this.slices; s += nthreads) {
                     for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                        FloatDHT_3D.this.dhtColumns.forward(a[s][r]);
                     }

                     if (FloatDHT_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx2 = FloatDHT_3D.this.rows + r;
                              t[r] = a[s][r][c];
                              t[idx2] = a[s][r][c + 1];
                              t[idx2 + FloatDHT_3D.this.rows] = a[s][r][c + 2];
                              t[idx2 + 2 * FloatDHT_3D.this.rows] = a[s][r][c + 3];
                           }

                           FloatDHT_3D.this.dhtRows.forward(t, 0);
                           FloatDHT_3D.this.dhtRows.forward(t, FloatDHT_3D.this.rows);
                           FloatDHT_3D.this.dhtRows.forward(t, 2 * FloatDHT_3D.this.rows);
                           FloatDHT_3D.this.dhtRows.forward(t, 3 * FloatDHT_3D.this.rows);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx2 = FloatDHT_3D.this.rows + r;
                              a[s][r][c] = t[r];
                              a[s][r][c + 1] = t[idx2];
                              a[s][r][c + 2] = t[idx2 + FloatDHT_3D.this.rows];
                              a[s][r][c + 3] = t[idx2 + 2 * FloatDHT_3D.this.rows];
                           }
                        }
                     } else if (FloatDHT_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           t[r] = a[s][r][0];
                           t[FloatDHT_3D.this.rows + r] = a[s][r][1];
                        }

                        FloatDHT_3D.this.dhtRows.forward(t, 0);
                        FloatDHT_3D.this.dhtRows.forward(t, FloatDHT_3D.this.rows);

                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           a[s][r][0] = t[r];
                           a[s][r][1] = t[FloatDHT_3D.this.rows + r];
                        }
                     }
                  }
               } else {
                  for(int s = i; s < FloatDHT_3D.this.slices; s += nthreads) {
                     for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                        FloatDHT_3D.this.dhtColumns.inverse(a[s][r], scale);
                     }

                     if (FloatDHT_3D.this.columns > 2) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx2 = FloatDHT_3D.this.rows + r;
                              t[r] = a[s][r][c];
                              t[idx2] = a[s][r][c + 1];
                              t[idx2 + FloatDHT_3D.this.rows] = a[s][r][c + 2];
                              t[idx2 + 2 * FloatDHT_3D.this.rows] = a[s][r][c + 3];
                           }

                           FloatDHT_3D.this.dhtRows.inverse(t, 0, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, FloatDHT_3D.this.rows, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, 2 * FloatDHT_3D.this.rows, scale);
                           FloatDHT_3D.this.dhtRows.inverse(t, 3 * FloatDHT_3D.this.rows, scale);

                           for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                              int idx2 = FloatDHT_3D.this.rows + r;
                              a[s][r][c] = t[r];
                              a[s][r][c + 1] = t[idx2];
                              a[s][r][c + 2] = t[idx2 + FloatDHT_3D.this.rows];
                              a[s][r][c + 3] = t[idx2 + 2 * FloatDHT_3D.this.rows];
                           }
                        }
                     } else if (FloatDHT_3D.this.columns == 2) {
                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           t[r] = a[s][r][0];
                           t[FloatDHT_3D.this.rows + r] = a[s][r][1];
                        }

                        FloatDHT_3D.this.dhtRows.inverse(t, 0, scale);
                        FloatDHT_3D.this.dhtRows.inverse(t, FloatDHT_3D.this.rows, scale);

                        for(int r = 0; r < FloatDHT_3D.this.rows; ++r) {
                           a[s][r][0] = t[r];
                           a[s][r][1] = t[FloatDHT_3D.this.rows + r];
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
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
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
                  if (FloatDHT_3D.this.columns > 2) {
                     for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                        int idx0 = r * FloatDHT_3D.this.rowStride;

                        for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx1 = s * FloatDHT_3D.this.sliceStride + idx0 + c;
                              int idx2 = FloatDHT_3D.this.slices + s;
                              t[s] = a[idx1];
                              t[idx2] = a[idx1 + 1];
                              t[idx2 + FloatDHT_3D.this.slices] = a[idx1 + 2];
                              t[idx2 + 2 * FloatDHT_3D.this.slices] = a[idx1 + 3];
                           }

                           FloatDHT_3D.this.dhtSlices.forward(t, 0);
                           FloatDHT_3D.this.dhtSlices.forward(t, FloatDHT_3D.this.slices);
                           FloatDHT_3D.this.dhtSlices.forward(t, 2 * FloatDHT_3D.this.slices);
                           FloatDHT_3D.this.dhtSlices.forward(t, 3 * FloatDHT_3D.this.slices);

                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx1 = s * FloatDHT_3D.this.sliceStride + idx0 + c;
                              int idx2 = FloatDHT_3D.this.slices + s;
                              a[idx1] = t[s];
                              a[idx1 + 1] = t[idx2];
                              a[idx1 + 2] = t[idx2 + FloatDHT_3D.this.slices];
                              a[idx1 + 3] = t[idx2 + 2 * FloatDHT_3D.this.slices];
                           }
                        }
                     }
                  } else if (FloatDHT_3D.this.columns == 2) {
                     for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                        int idx0 = r * FloatDHT_3D.this.rowStride;

                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           int idx1 = s * FloatDHT_3D.this.sliceStride + idx0;
                           t[s] = a[idx1];
                           t[FloatDHT_3D.this.slices + s] = a[idx1 + 1];
                        }

                        FloatDHT_3D.this.dhtSlices.forward(t, 0);
                        FloatDHT_3D.this.dhtSlices.forward(t, FloatDHT_3D.this.slices);

                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           int idx1 = s * FloatDHT_3D.this.sliceStride + idx0;
                           a[idx1] = t[s];
                           a[idx1 + 1] = t[FloatDHT_3D.this.slices + s];
                        }
                     }
                  }
               } else if (FloatDHT_3D.this.columns > 2) {
                  for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                     int idx0 = r * FloatDHT_3D.this.rowStride;

                     for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           int idx1 = s * FloatDHT_3D.this.sliceStride + idx0 + c;
                           int idx2 = FloatDHT_3D.this.slices + s;
                           t[s] = a[idx1];
                           t[idx2] = a[idx1 + 1];
                           t[idx2 + FloatDHT_3D.this.slices] = a[idx1 + 2];
                           t[idx2 + 2 * FloatDHT_3D.this.slices] = a[idx1 + 3];
                        }

                        FloatDHT_3D.this.dhtSlices.inverse(t, 0, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, FloatDHT_3D.this.slices, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, 2 * FloatDHT_3D.this.slices, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, 3 * FloatDHT_3D.this.slices, scale);

                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           int idx1 = s * FloatDHT_3D.this.sliceStride + idx0 + c;
                           int idx2 = FloatDHT_3D.this.slices + s;
                           a[idx1] = t[s];
                           a[idx1 + 1] = t[idx2];
                           a[idx1 + 2] = t[idx2 + FloatDHT_3D.this.slices];
                           a[idx1 + 3] = t[idx2 + 2 * FloatDHT_3D.this.slices];
                        }
                     }
                  }
               } else if (FloatDHT_3D.this.columns == 2) {
                  for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                     int idx0 = r * FloatDHT_3D.this.rowStride;

                     for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                        int idx1 = s * FloatDHT_3D.this.sliceStride + idx0;
                        t[s] = a[idx1];
                        t[FloatDHT_3D.this.slices + s] = a[idx1 + 1];
                     }

                     FloatDHT_3D.this.dhtSlices.inverse(t, 0, scale);
                     FloatDHT_3D.this.dhtSlices.inverse(t, FloatDHT_3D.this.slices, scale);

                     for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                        int idx1 = s * FloatDHT_3D.this.sliceStride + idx0;
                        a[idx1] = t[s];
                        a[idx1 + 1] = t[FloatDHT_3D.this.slices + s];
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
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
                  if (FloatDHT_3D.this.columnsl > 2L) {
                     for(long r = n0; r < FloatDHT_3D.this.rowsl; r += (long)nthreads) {
                        long idx0 = r * FloatDHT_3D.this.rowStridel;

                        for(long c = 0L; c < FloatDHT_3D.this.columnsl; c += 4L) {
                           for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                              long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0 + c;
                              long idx2 = FloatDHT_3D.this.slicesl + s;
                              t.setFloat(s, a.getFloat(idx1));
                              t.setFloat(idx2, a.getFloat(idx1 + 1L));
                              t.setFloat(idx2 + FloatDHT_3D.this.slicesl, a.getFloat(idx1 + 2L));
                              t.setFloat(idx2 + 2L * FloatDHT_3D.this.slicesl, a.getFloat(idx1 + 3L));
                           }

                           FloatDHT_3D.this.dhtSlices.forward(t, 0L);
                           FloatDHT_3D.this.dhtSlices.forward(t, FloatDHT_3D.this.slicesl);
                           FloatDHT_3D.this.dhtSlices.forward(t, 2L * FloatDHT_3D.this.slicesl);
                           FloatDHT_3D.this.dhtSlices.forward(t, 3L * FloatDHT_3D.this.slicesl);

                           for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                              long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0 + c;
                              long idx2 = FloatDHT_3D.this.slicesl + s;
                              a.setFloat(idx1, t.getFloat(s));
                              a.setFloat(idx1 + 1L, t.getFloat(idx2));
                              a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDHT_3D.this.slicesl));
                              a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDHT_3D.this.slicesl));
                           }
                        }
                     }
                  } else if (FloatDHT_3D.this.columnsl == 2L) {
                     for(long r = n0; r < FloatDHT_3D.this.rowsl; r += (long)nthreads) {
                        long idx0 = r * FloatDHT_3D.this.rowStridel;

                        for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0;
                           t.setFloat(s, a.getFloat(idx1));
                           t.setFloat(FloatDHT_3D.this.slicesl + s, a.getFloat(idx1 + 1L));
                        }

                        FloatDHT_3D.this.dhtSlices.forward(t, 0L);
                        FloatDHT_3D.this.dhtSlices.forward(t, FloatDHT_3D.this.slicesl);

                        for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0;
                           a.setFloat(idx1, t.getFloat(s));
                           a.setFloat(idx1 + 1L, t.getFloat(FloatDHT_3D.this.slicesl + s));
                        }
                     }
                  }
               } else if (FloatDHT_3D.this.columnsl > 2L) {
                  for(long r = n0; r < FloatDHT_3D.this.rowsl; r += (long)nthreads) {
                     long idx0 = r * FloatDHT_3D.this.rowStridel;

                     for(long c = 0L; c < FloatDHT_3D.this.columnsl; c += 4L) {
                        for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0 + c;
                           long idx2 = FloatDHT_3D.this.slicesl + s;
                           t.setFloat(s, a.getFloat(idx1));
                           t.setFloat(idx2, a.getFloat(idx1 + 1L));
                           t.setFloat(idx2 + FloatDHT_3D.this.slicesl, a.getFloat(idx1 + 2L));
                           t.setFloat(idx2 + 2L * FloatDHT_3D.this.slicesl, a.getFloat(idx1 + 3L));
                        }

                        FloatDHT_3D.this.dhtSlices.inverse(t, 0L, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, FloatDHT_3D.this.slicesl, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, 2L * FloatDHT_3D.this.slicesl, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, 3L * FloatDHT_3D.this.slicesl, scale);

                        for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                           long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0 + c;
                           long idx2 = FloatDHT_3D.this.slicesl + s;
                           a.setFloat(idx1, t.getFloat(s));
                           a.setFloat(idx1 + 1L, t.getFloat(idx2));
                           a.setFloat(idx1 + 2L, t.getFloat(idx2 + FloatDHT_3D.this.slicesl));
                           a.setFloat(idx1 + 3L, t.getFloat(idx2 + 2L * FloatDHT_3D.this.slicesl));
                        }
                     }
                  }
               } else if (FloatDHT_3D.this.columnsl == 2L) {
                  for(long r = n0; r < FloatDHT_3D.this.rowsl; r += (long)nthreads) {
                     long idx0 = r * FloatDHT_3D.this.rowStridel;

                     for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                        long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0;
                        t.setFloat(s, a.getFloat(idx1));
                        t.setFloat(FloatDHT_3D.this.slicesl + s, a.getFloat(idx1 + 1L));
                     }

                     FloatDHT_3D.this.dhtSlices.inverse(t, 0L, scale);
                     FloatDHT_3D.this.dhtSlices.inverse(t, FloatDHT_3D.this.slicesl, scale);

                     for(long s = 0L; s < FloatDHT_3D.this.slicesl; ++s) {
                        long idx1 = s * FloatDHT_3D.this.sliceStridel + idx0;
                        a.setFloat(idx1, t.getFloat(s));
                        a.setFloat(idx1 + 1L, t.getFloat(FloatDHT_3D.this.slicesl + s));
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
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
                  if (FloatDHT_3D.this.columns > 2) {
                     for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                        for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx2 = FloatDHT_3D.this.slices + s;
                              t[s] = a[s][r][c];
                              t[idx2] = a[s][r][c + 1];
                              t[idx2 + FloatDHT_3D.this.slices] = a[s][r][c + 2];
                              t[idx2 + 2 * FloatDHT_3D.this.slices] = a[s][r][c + 3];
                           }

                           FloatDHT_3D.this.dhtSlices.forward(t, 0);
                           FloatDHT_3D.this.dhtSlices.forward(t, FloatDHT_3D.this.slices);
                           FloatDHT_3D.this.dhtSlices.forward(t, 2 * FloatDHT_3D.this.slices);
                           FloatDHT_3D.this.dhtSlices.forward(t, 3 * FloatDHT_3D.this.slices);

                           for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                              int idx2 = FloatDHT_3D.this.slices + s;
                              a[s][r][c] = t[s];
                              a[s][r][c + 1] = t[idx2];
                              a[s][r][c + 2] = t[idx2 + FloatDHT_3D.this.slices];
                              a[s][r][c + 3] = t[idx2 + 2 * FloatDHT_3D.this.slices];
                           }
                        }
                     }
                  } else if (FloatDHT_3D.this.columns == 2) {
                     for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           t[s] = a[s][r][0];
                           t[FloatDHT_3D.this.slices + s] = a[s][r][1];
                        }

                        FloatDHT_3D.this.dhtSlices.forward(t, 0);
                        FloatDHT_3D.this.dhtSlices.forward(t, FloatDHT_3D.this.slices);

                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           a[s][r][0] = t[s];
                           a[s][r][1] = t[FloatDHT_3D.this.slices + s];
                        }
                     }
                  }
               } else if (FloatDHT_3D.this.columns > 2) {
                  for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                     for(int c = 0; c < FloatDHT_3D.this.columns; c += 4) {
                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           int idx2 = FloatDHT_3D.this.slices + s;
                           t[s] = a[s][r][c];
                           t[idx2] = a[s][r][c + 1];
                           t[idx2 + FloatDHT_3D.this.slices] = a[s][r][c + 2];
                           t[idx2 + 2 * FloatDHT_3D.this.slices] = a[s][r][c + 3];
                        }

                        FloatDHT_3D.this.dhtSlices.inverse(t, 0, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, FloatDHT_3D.this.slices, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, 2 * FloatDHT_3D.this.slices, scale);
                        FloatDHT_3D.this.dhtSlices.inverse(t, 3 * FloatDHT_3D.this.slices, scale);

                        for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                           int idx2 = FloatDHT_3D.this.slices + s;
                           a[s][r][c] = t[s];
                           a[s][r][c + 1] = t[idx2];
                           a[s][r][c + 2] = t[idx2 + FloatDHT_3D.this.slices];
                           a[s][r][c + 3] = t[idx2 + 2 * FloatDHT_3D.this.slices];
                        }
                     }
                  }
               } else if (FloatDHT_3D.this.columns == 2) {
                  for(int r = i; r < FloatDHT_3D.this.rows; r += nthreads) {
                     for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                        t[s] = a[s][r][0];
                        t[FloatDHT_3D.this.slices + s] = a[s][r][1];
                     }

                     FloatDHT_3D.this.dhtSlices.inverse(t, 0, scale);
                     FloatDHT_3D.this.dhtSlices.inverse(t, FloatDHT_3D.this.slices, scale);

                     for(int s = 0; s < FloatDHT_3D.this.slices; ++s) {
                        a[s][r][0] = t[s];
                        a[s][r][1] = t[FloatDHT_3D.this.slices + s];
                     }
                  }
               }

            }
         });
      }

      try {
         ConcurrencyUtils.waitForCompletion(futures);
      } catch (InterruptedException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      } catch (ExecutionException ex) {
         Logger.getLogger(FloatDHT_3D.class.getName()).log(Level.SEVERE, (String)null, ex);
      }

   }

   private void yTransform(float[] a) {
      for(int s = 0; s <= this.slices / 2; ++s) {
         int sC = (this.slices - s) % this.slices;
         int idx9 = s * this.sliceStride;
         int idx10 = sC * this.sliceStride;

         for(int r = 0; r <= this.rows / 2; ++r) {
            int rC = (this.rows - r) % this.rows;
            int idx11 = r * this.rowStride;
            int idx12 = rC * this.rowStride;

            for(int c = 0; c <= this.columns / 2; ++c) {
               int cC = (this.columns - c) % this.columns;
               int idx1 = idx9 + idx12 + c;
               int idx2 = idx9 + idx11 + cC;
               int idx3 = idx10 + idx11 + c;
               int idx4 = idx10 + idx12 + cC;
               int idx5 = idx10 + idx12 + c;
               int idx6 = idx10 + idx11 + cC;
               int idx7 = idx9 + idx11 + c;
               int idx8 = idx9 + idx12 + cC;
               float A = a[idx1];
               float B = a[idx2];
               float C = a[idx3];
               float D = a[idx4];
               float E = a[idx5];
               float F = a[idx6];
               float G = a[idx7];
               float H = a[idx8];
               a[idx7] = (A + B + C - D) / 2.0F;
               a[idx3] = (E + F + G - H) / 2.0F;
               a[idx1] = (G + H + E - F) / 2.0F;
               a[idx5] = (C + D + A - B) / 2.0F;
               a[idx2] = (H + G + F - E) / 2.0F;
               a[idx6] = (D + C + B - A) / 2.0F;
               a[idx8] = (B + A + D - C) / 2.0F;
               a[idx4] = (F + E + H - G) / 2.0F;
            }
         }
      }

   }

   private void yTransform(FloatLargeArray a) {
      for(long s = 0L; s <= this.slicesl / 2L; ++s) {
         long sC = (this.slicesl - s) % this.slicesl;
         long idx9 = s * this.sliceStridel;
         long idx10 = sC * this.sliceStridel;

         for(long r = 0L; r <= this.rowsl / 2L; ++r) {
            long rC = (this.rowsl - r) % this.rowsl;
            long idx11 = r * this.rowStridel;
            long idx12 = rC * this.rowStridel;

            for(long c = 0L; c <= this.columnsl / 2L; ++c) {
               long cC = (this.columnsl - c) % this.columnsl;
               long idx1 = idx9 + idx12 + c;
               long idx2 = idx9 + idx11 + cC;
               long idx3 = idx10 + idx11 + c;
               long idx4 = idx10 + idx12 + cC;
               long idx5 = idx10 + idx12 + c;
               long idx6 = idx10 + idx11 + cC;
               long idx7 = idx9 + idx11 + c;
               long idx8 = idx9 + idx12 + cC;
               float A = a.getFloat(idx1);
               float B = a.getFloat(idx2);
               float C = a.getFloat(idx3);
               float D = a.getFloat(idx4);
               float E = a.getFloat(idx5);
               float F = a.getFloat(idx6);
               float G = a.getFloat(idx7);
               float H = a.getFloat(idx8);
               a.setFloat(idx7, (A + B + C - D) / 2.0F);
               a.setFloat(idx3, (E + F + G - H) / 2.0F);
               a.setFloat(idx1, (G + H + E - F) / 2.0F);
               a.setFloat(idx5, (C + D + A - B) / 2.0F);
               a.setFloat(idx2, (H + G + F - E) / 2.0F);
               a.setFloat(idx6, (D + C + B - A) / 2.0F);
               a.setFloat(idx8, (B + A + D - C) / 2.0F);
               a.setFloat(idx4, (F + E + H - G) / 2.0F);
            }
         }
      }

   }

   private void yTransform(float[][][] a) {
      for(int s = 0; s <= this.slices / 2; ++s) {
         int sC = (this.slices - s) % this.slices;

         for(int r = 0; r <= this.rows / 2; ++r) {
            int rC = (this.rows - r) % this.rows;

            for(int c = 0; c <= this.columns / 2; ++c) {
               int cC = (this.columns - c) % this.columns;
               float A = a[s][rC][c];
               float B = a[s][r][cC];
               float C = a[sC][r][c];
               float D = a[sC][rC][cC];
               float E = a[sC][rC][c];
               float F = a[sC][r][cC];
               float G = a[s][r][c];
               float H = a[s][rC][cC];
               a[s][r][c] = (A + B + C - D) / 2.0F;
               a[sC][r][c] = (E + F + G - H) / 2.0F;
               a[s][rC][c] = (G + H + E - F) / 2.0F;
               a[sC][rC][c] = (C + D + A - B) / 2.0F;
               a[s][r][cC] = (H + G + F - E) / 2.0F;
               a[sC][r][cC] = (D + C + B - A) / 2.0F;
               a[s][rC][cC] = (B + A + D - C) / 2.0F;
               a[sC][rC][cC] = (F + E + H - G) / 2.0F;
            }
         }
      }

   }
}
