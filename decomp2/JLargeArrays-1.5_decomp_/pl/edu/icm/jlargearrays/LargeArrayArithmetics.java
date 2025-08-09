package pl.edu.icm.jlargearrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;

public class LargeArrayArithmetics {
   private LargeArrayArithmetics() {
   }

   public static float[] complexSin(float[] a) {
      float[] res = new float[2];
      res[0] = (float)(FastMath.sin((double)a[0]) * FastMath.cosh((double)a[1]));
      res[1] = (float)(FastMath.cos((double)a[0]) * FastMath.sinh((double)a[1]));
      return res;
   }

   public static double[] complexSin(double[] a) {
      double[] res = new double[2];
      res[0] = FastMath.sin(a[0]) * FastMath.cosh(a[1]);
      res[1] = FastMath.cos(a[0]) * FastMath.sinh(a[1]);
      return res;
   }

   public static float[] complexCos(float[] a) {
      float[] res = new float[2];
      res[0] = (float)(FastMath.cos((double)a[0]) * FastMath.cosh((double)a[1]));
      res[1] = (float)(-FastMath.sin((double)a[0]) * FastMath.sinh((double)a[1]));
      return res;
   }

   public static double[] complexCos(double[] a) {
      double[] res = new double[2];
      res[0] = FastMath.cos(a[0]) * FastMath.cosh(a[1]);
      res[1] = -FastMath.sin(a[0]) * FastMath.sinh(a[1]);
      return res;
   }

   public static float[] complexTan(float[] a) {
      float[] s = complexSin(a);
      float[] c = complexCos(a);
      return complexDiv(s, c);
   }

   public static double[] complexTan(double[] a) {
      double[] s = complexSin(a);
      double[] c = complexCos(a);
      return complexDiv(s, c);
   }

   public static float[] complexMult(float[] a, float[] b) {
      float[] res = new float[2];
      res[0] = a[0] * b[0] - a[1] * b[1];
      res[1] = a[1] * b[0] + a[0] * b[1];
      return res;
   }

   public static double[] complexMult(double[] a, double[] b) {
      double[] res = new double[2];
      res[0] = a[0] * b[0] - a[1] * b[1];
      res[1] = a[1] * b[0] + a[0] * b[1];
      return res;
   }

   public static float[] complexDiv(float[] a, float[] b) {
      float r = b[0] * b[0] + b[1] * b[1];
      float[] res = new float[2];
      res[0] = (a[0] * b[0] + a[1] * b[1]) / r;
      res[1] = (a[1] * b[0] - a[0] * b[1]) / r;
      return res;
   }

   public static double[] complexDiv(double[] a, double[] b) {
      double r = b[0] * b[0] + b[1] * b[1];
      double[] res = new double[2];
      res[0] = (a[0] * b[0] + a[1] * b[1]) / r;
      res[1] = (a[1] * b[0] - a[0] * b[1]) / r;
      return res;
   }

   public static float[] complexPow(float[] a, double n) {
      float[] res = new float[2];
      double mod = FastMath.pow(FastMath.sqrt((double)(a[0] * a[0] + a[1] * a[1])), n);
      double arg = FastMath.atan2((double)a[1], (double)a[0]);
      res[0] = (float)(mod * FastMath.cos(n * arg));
      res[1] = (float)(mod * FastMath.sin(n * arg));
      return res;
   }

   public static double[] complexPow(double[] a, double n) {
      double[] res = new double[2];
      double mod = FastMath.pow(FastMath.sqrt(a[0] * a[0] + a[1] * a[1]), n);
      double arg = FastMath.atan2(a[1], a[0]);
      res[0] = mod * FastMath.cos(n * arg);
      res[1] = mod * FastMath.sin(n * arg);
      return res;
   }

   public static float[] complexSqrt(float[] a) {
      float[] res = new float[2];
      double mod = FastMath.sqrt((double)(a[0] * a[0] + a[1] * a[1]));
      res[0] = (float)FastMath.sqrt(((double)a[0] + mod) / (double)2.0F);
      res[1] = (float)((double)FastMath.signum(a[1]) * FastMath.sqrt(((double)(-a[0]) + mod) / (double)2.0F));
      return res;
   }

   public static double[] complexSqrt(double[] a) {
      double[] res = new double[2];
      double mod = FastMath.sqrt(a[0] * a[0] + a[1] * a[1]);
      res[0] = FastMath.sqrt((a[0] + mod) / (double)2.0F);
      res[1] = FastMath.signum(a[1]) * FastMath.sqrt((-a[0] + mod) / (double)2.0F);
      return res;
   }

   public static float complexAbs(float[] a) {
      return (float)FastMath.sqrt((double)(a[0] * a[0] + a[1] * a[1]));
   }

   public static double complexAbs(double[] a) {
      return FastMath.sqrt(a[0] * a[0] + a[1] * a[1]);
   }

   public static float[] complexLog(float[] a) {
      float[] res = new float[2];
      double mod = FastMath.sqrt((double)(a[0] * a[0] + a[1] * a[1]));
      double arg = FastMath.atan2((double)a[1], (double)a[0]);
      res[0] = (float)FastMath.log(mod);
      res[1] = (float)arg;
      return res;
   }

   public static double[] complexLog(double[] a) {
      double[] res = new double[2];
      double mod = FastMath.sqrt(a[0] * a[0] + a[1] * a[1]);
      double arg = FastMath.atan2(a[1], a[0]);
      res[0] = FastMath.log(mod);
      res[1] = arg;
      return res;
   }

   public static float[] complexLog10(float[] a) {
      float[] res = new float[2];
      double scale = FastMath.log((double)10.0F);
      double mod = FastMath.sqrt((double)(a[0] * a[0] + a[1] * a[1]));
      double arg = FastMath.atan2((double)a[1], (double)a[0]) / scale;
      res[0] = (float)(FastMath.log(mod) / scale);
      res[1] = (float)arg;
      return res;
   }

   public static double[] complexLog10(double[] a) {
      double[] res = new double[2];
      double scale = FastMath.log((double)10.0F);
      double mod = FastMath.sqrt(a[0] * a[0] + a[1] * a[1]);
      double arg = FastMath.atan2(a[1], a[0]) / scale;
      res[0] = FastMath.log(mod) / scale;
      res[1] = arg;
      return res;
   }

   public static float[] complexExp(float[] a) {
      float[] res = new float[2];
      res[0] = (float)(FastMath.exp((double)a[0]) * FastMath.cos((double)a[1]));
      res[1] = (float)(FastMath.exp((double)a[0]) * FastMath.sin((double)a[1]));
      return res;
   }

   public static double[] complexExp(double[] a) {
      double[] res = new double[2];
      res[0] = FastMath.exp(a[0]) * FastMath.cos(a[1]);
      res[1] = FastMath.exp(a[0]) * FastMath.sin(a[1]);
      return res;
   }

   public static float[] complexAsin(float[] a) {
      float[] i = new float[]{0.0F, 1.0F};
      float[] mi = new float[]{0.0F, -1.0F};
      float[] res = complexMult(a, a);
      res[0] = 1.0F - res[0];
      res[1] = 1.0F - res[1];
      res = complexLog(res);
      i = complexMult(i, a);
      res[0] += i[0];
      res[1] += i[1];
      return complexMult(mi, res);
   }

   public static double[] complexAsin(double[] a) {
      double[] i = new double[]{(double)0.0F, (double)1.0F};
      double[] mi = new double[]{(double)0.0F, (double)-1.0F};
      double[] res = complexMult(a, a);
      res[0] = (double)1.0F - res[0];
      res[1] = (double)1.0F - res[1];
      res = complexLog(res);
      i = complexMult(i, a);
      res[0] += i[0];
      res[1] += i[1];
      return complexMult(mi, res);
   }

   public static float[] complexAcos(float[] a) {
      float[] i = new float[]{0.0F, 1.0F};
      float[] mi = new float[]{0.0F, -1.0F};
      float[] res = complexMult(a, a);
      res[0] = 1.0F - res[0];
      res[1] = 1.0F - res[1];
      res = complexMult(i, res);
      res[0] += a[0];
      res[1] += a[1];
      res = complexLog(res);
      return complexMult(mi, res);
   }

   public static double[] complexAcos(double[] a) {
      double[] i = new double[]{(double)0.0F, (double)1.0F};
      double[] mi = new double[]{(double)0.0F, (double)-1.0F};
      double[] res = complexMult(a, a);
      res[0] = (double)1.0F - res[0];
      res[1] = (double)1.0F - res[1];
      res = complexMult(i, res);
      res[0] += a[0];
      res[1] += a[1];
      res = complexLog(res);
      return complexMult(mi, res);
   }

   public static float[] complexAtan(float[] a) {
      float[] res = new float[2];
      float[] tmp = new float[2];
      float[] i = new float[]{0.0F, 1.0F};
      res[0] = i[0] + a[0];
      res[1] = i[1] + a[1];
      tmp[0] = i[0] - a[0];
      tmp[1] = i[1] - a[1];
      res = complexLog(complexDiv(res, tmp));
      i[1] = (float)((double)i[1] / (double)2.0F);
      return complexMult(i, res);
   }

   public static double[] complexAtan(double[] a) {
      double[] res = new double[2];
      double[] tmp = new double[2];
      double[] i = new double[]{(double)0.0F, (double)1.0F};
      res[0] = i[0] + a[0];
      res[1] = i[1] + a[1];
      tmp[0] = i[0] - a[0];
      tmp[1] = i[1] - a[1];
      res = complexLog(complexDiv(res, tmp));
      i[1] /= (double)2.0F;
      return complexMult(i, res);
   }

   public static LargeArray add(LargeArray a, LargeArray b) {
      LargeArrayType out_type = a.getType().compareTo(b.getType()) >= 0 ? a.getType() : b.getType();
      return add(a, b, out_type);
   }

   public static LargeArray add(final LargeArray a, final LargeArray b, LargeArrayType out_type) {
      if (a != null && b != null && a.length() == b.length() && a.isNumeric() && b.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant() && b.isConstant()) {
               if (out_type.isIntegerNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getLong(0L) + b.getLong(0L));
               } else if (out_type.isRealNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getDouble(0L) + b.getDouble(0L));
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                  float[] elem_b = ((ComplexFloatLargeArray)b).getComplexFloat(0L);
                  return LargeArrayUtils.createConstant(out_type, length, new float[]{elem_a[0] + elem_b[0], elem_a[1] + elem_b[1]});
               } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                  double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                  double[] elem_b = ((ComplexDoubleLargeArray)b).getComplexDouble(0L);
                  return LargeArrayUtils.createConstant(out_type, length, new double[]{elem_a[0] + elem_b[0], elem_a[1] + elem_b[1]});
               } else {
                  throw new IllegalArgumentException("Invalid array type.");
               }
            } else {
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               final LargeArray res;
               if (out_type.isIntegerNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setLong(k, a.getLong(k) + b.getLong(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var22) {
                        for(long i = 0L; i < length; ++i) {
                           res.setLong(i, a.getLong(i) + b.getLong(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setLong(i, a.getLong(i) + b.getLong(i));
                     }
                  }
               } else if (out_type.isRealNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setDouble(k, a.getDouble(k) + b.getDouble(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var21) {
                        for(long i = 0L; i < length; ++i) {
                           res.setDouble(i, a.getDouble(i) + b.getDouble(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, a.getDouble(i) + b.getDouble(i));
                     }
                  }
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexFloatLargeArray _bc = (ComplexFloatLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              float[] elem_res = new float[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 float[] elem_a = _ac.getComplexFloat(k);
                                 float[] elem_b = _bc.getComplexFloat(k);
                                 elem_res[0] = elem_a[0] + elem_b[0];
                                 elem_res[1] = elem_a[1] + elem_b[1];
                                 resc.setComplexFloat(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var20) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           float[] elem_b = _bc.getComplexFloat(i);
                           elem_res[0] = elem_a[0] + elem_b[0];
                           elem_res[1] = elem_a[1] + elem_b[1];
                           resc.setComplexFloat(i, elem_res);
                        }
                     }
                  } else {
                     float[] elem_res = new float[2];

                     for(long i = 0L; i < length; ++i) {
                        float[] elem_a = _ac.getComplexFloat(i);
                        float[] elem_b = _bc.getComplexFloat(i);
                        elem_res[0] = elem_a[0] + elem_b[0];
                        elem_res[1] = elem_a[1] + elem_b[1];
                        resc.setComplexFloat(i, elem_res);
                     }
                  }
               } else {
                  if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                     throw new IllegalArgumentException("Invalid array type.");
                  }

                  final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexDoubleLargeArray _bc = (ComplexDoubleLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              double[] elem_res = new double[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 double[] elem_a = _ac.getComplexDouble(k);
                                 double[] elem_b = _bc.getComplexDouble(k);
                                 elem_res[0] = elem_a[0] + elem_b[0];
                                 elem_res[1] = elem_a[1] + elem_b[1];
                                 resc.setComplexDouble(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var19) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double[] elem_b = _bc.getComplexDouble(i);
                           elem_res[0] = elem_a[0] + elem_b[0];
                           elem_res[1] = elem_a[1] + elem_b[1];
                           resc.setComplexDouble(i, elem_res);
                        }
                     }
                  } else {
                     double[] elem_res = new double[2];

                     for(long i = 0L; i < length; ++i) {
                        double[] elem_a = _ac.getComplexDouble(i);
                        double[] elem_b = _bc.getComplexDouble(i);
                        elem_res[0] = elem_a[0] + elem_b[0];
                        elem_res[1] = elem_a[1] + elem_b[1];
                        resc.setComplexDouble(i, elem_res);
                     }
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
      }
   }

   public static LargeArray diff(LargeArray a, LargeArray b) {
      LargeArrayType out_type = a.getType().compareTo(b.getType()) >= 0 ? a.getType() : b.getType();
      return diff(a, b, out_type);
   }

   public static LargeArray diff(final LargeArray a, final LargeArray b, LargeArrayType out_type) {
      if (a != null && b != null && a.length() == b.length() && a.isNumeric() && b.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant() && b.isConstant()) {
               if (out_type.isIntegerNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getLong(0L) - b.getLong(0L));
               } else if (out_type.isRealNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getDouble(0L) - b.getDouble(0L));
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                  float[] elem_b = ((ComplexFloatLargeArray)b).getComplexFloat(0L);
                  return LargeArrayUtils.createConstant(out_type, length, new float[]{elem_a[0] - elem_b[0], elem_a[1] - elem_b[1]});
               } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                  double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                  double[] elem_b = ((ComplexDoubleLargeArray)b).getComplexDouble(0L);
                  return LargeArrayUtils.createConstant(out_type, length, new double[]{elem_a[0] - elem_b[0], elem_a[1] - elem_b[1]});
               } else {
                  throw new IllegalArgumentException("Invalid array type.");
               }
            } else {
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               final LargeArray res;
               if (out_type.isIntegerNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setLong(k, a.getLong(k) - b.getLong(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var22) {
                        for(long i = 0L; i < length; ++i) {
                           res.setLong(i, a.getLong(i) - b.getLong(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setLong(i, a.getLong(i) - b.getLong(i));
                     }
                  }
               } else if (out_type.isRealNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setDouble(k, a.getDouble(k) - b.getDouble(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var21) {
                        for(long i = 0L; i < length; ++i) {
                           res.setDouble(i, a.getDouble(i) - b.getDouble(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, a.getDouble(i) - b.getDouble(i));
                     }
                  }
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexFloatLargeArray _bc = (ComplexFloatLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              float[] elem_res = new float[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 float[] elem_a = _ac.getComplexFloat(k);
                                 float[] elem_b = _bc.getComplexFloat(k);
                                 elem_res[0] = elem_a[0] - elem_b[0];
                                 elem_res[1] = elem_a[1] - elem_b[1];
                                 resc.setComplexFloat(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var20) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           float[] elem_b = _bc.getComplexFloat(i);
                           elem_res[0] = elem_a[0] - elem_b[0];
                           elem_res[1] = elem_a[1] - elem_b[1];
                           resc.setComplexFloat(i, elem_res);
                        }
                     }
                  } else {
                     float[] elem_res = new float[2];

                     for(long i = 0L; i < length; ++i) {
                        float[] elem_a = _ac.getComplexFloat(i);
                        float[] elem_b = _bc.getComplexFloat(i);
                        elem_res[0] = elem_a[0] - elem_b[0];
                        elem_res[1] = elem_a[1] - elem_b[1];
                        resc.setComplexFloat(i, elem_res);
                     }
                  }
               } else {
                  if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                     throw new IllegalArgumentException("Invalid array type.");
                  }

                  final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexDoubleLargeArray _bc = (ComplexDoubleLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              double[] elem_res = new double[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 double[] elem_a = _ac.getComplexDouble(k);
                                 double[] elem_b = _bc.getComplexDouble(k);
                                 elem_res[0] = elem_a[0] - elem_b[0];
                                 elem_res[1] = elem_a[1] - elem_b[1];
                                 resc.setComplexDouble(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var19) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double[] elem_b = _bc.getComplexDouble(i);
                           elem_res[0] = elem_a[0] - elem_b[0];
                           elem_res[1] = elem_a[1] - elem_b[1];
                           resc.setComplexDouble(i, elem_res);
                        }
                     }
                  } else {
                     double[] elem_res = new double[2];

                     for(long i = 0L; i < length; ++i) {
                        double[] elem_a = _ac.getComplexDouble(i);
                        double[] elem_b = _bc.getComplexDouble(i);
                        elem_res[0] = elem_a[0] - elem_b[0];
                        elem_res[1] = elem_a[1] - elem_b[1];
                        resc.setComplexDouble(i, elem_res);
                     }
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
      }
   }

   public static LargeArray mult(LargeArray a, LargeArray b) {
      LargeArrayType out_type = a.getType().compareTo(b.getType()) >= 0 ? a.getType() : b.getType();
      return mult(a, b, out_type);
   }

   public static LargeArray mult(final LargeArray a, final LargeArray b, LargeArrayType out_type) {
      if (a != null && b != null && a.length() == b.length() && a.isNumeric() && b.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant() && b.isConstant()) {
               if (out_type.isIntegerNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getLong(0L) * b.getLong(0L));
               } else if (out_type.isRealNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getDouble(0L) * b.getDouble(0L));
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                  float[] elem_b = ((ComplexFloatLargeArray)b).getComplexFloat(0L);
                  return LargeArrayUtils.createConstant(out_type, length, complexMult(elem_a, elem_b));
               } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                  double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                  double[] elem_b = ((ComplexDoubleLargeArray)b).getComplexDouble(0L);
                  return LargeArrayUtils.createConstant(out_type, length, complexMult(elem_a, elem_b));
               } else {
                  throw new IllegalArgumentException("Invalid array type.");
               }
            } else {
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               final LargeArray res;
               if (out_type.isIntegerNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setLong(k, a.getLong(k) * b.getLong(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var22) {
                        for(long i = 0L; i < length; ++i) {
                           res.setLong(i, a.getLong(i) * b.getLong(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setLong(i, a.getLong(i) * b.getLong(i));
                     }
                  }
               } else if (out_type.isRealNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setDouble(k, a.getDouble(k) * b.getDouble(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var21) {
                        for(long i = 0L; i < length; ++i) {
                           res.setDouble(i, a.getDouble(i) * b.getDouble(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, a.getDouble(i) * b.getDouble(i));
                     }
                  }
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexFloatLargeArray _bc = (ComplexFloatLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              float[] elem_res = new float[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 float[] elem_a = _ac.getComplexFloat(k);
                                 float[] elem_b = _bc.getComplexFloat(k);
                                 elem_res[0] = elem_a[0] * elem_b[0] - elem_a[1] * elem_b[1];
                                 elem_res[1] = elem_a[1] * elem_b[0] + elem_a[0] * elem_b[1];
                                 resc.setComplexFloat(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var20) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           float[] elem_b = _bc.getComplexFloat(i);
                           elem_res[0] = elem_a[0] * elem_b[0] - elem_a[1] * elem_b[1];
                           elem_res[1] = elem_a[1] * elem_b[0] + elem_a[0] * elem_b[1];
                           resc.setComplexFloat(i, elem_res);
                        }
                     }
                  } else {
                     float[] elem_res = new float[2];

                     for(long i = 0L; i < length; ++i) {
                        float[] elem_a = _ac.getComplexFloat(i);
                        float[] elem_b = _bc.getComplexFloat(i);
                        elem_res[0] = elem_a[0] * elem_b[0] - elem_a[1] * elem_b[1];
                        elem_res[1] = elem_a[1] * elem_b[0] + elem_a[0] * elem_b[1];
                        resc.setComplexFloat(i, elem_res);
                     }
                  }
               } else {
                  if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                     throw new IllegalArgumentException("Invalid array type.");
                  }

                  final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexDoubleLargeArray _bc = (ComplexDoubleLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              double[] elem_res = new double[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 double[] elem_a = _ac.getComplexDouble(k);
                                 double[] elem_b = _bc.getComplexDouble(k);
                                 elem_res[0] = elem_a[0] * elem_b[0] - elem_a[1] * elem_b[1];
                                 elem_res[1] = elem_a[1] * elem_b[0] + elem_a[0] * elem_b[1];
                                 resc.setComplexDouble(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var19) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double[] elem_b = _bc.getComplexDouble(i);
                           elem_res[0] = elem_a[0] * elem_b[0] - elem_a[1] * elem_b[1];
                           elem_res[1] = elem_a[1] * elem_b[0] + elem_a[0] * elem_b[1];
                           resc.setComplexDouble(i, elem_res);
                        }
                     }
                  } else {
                     double[] elem_res = new double[2];

                     for(long i = 0L; i < length; ++i) {
                        double[] elem_a = _ac.getComplexDouble(i);
                        double[] elem_b = _bc.getComplexDouble(i);
                        elem_res[0] = elem_a[0] * elem_b[0] - elem_a[1] * elem_b[1];
                        elem_res[1] = elem_a[1] * elem_b[0] + elem_a[0] * elem_b[1];
                        resc.setComplexDouble(i, elem_res);
                     }
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
      }
   }

   public static LargeArray div(LargeArray a, LargeArray b) {
      LargeArrayType out_type = a.getType().compareTo(b.getType()) >= 0 ? a.getType() : b.getType();
      return div(a, b, out_type);
   }

   public static LargeArray div(final LargeArray a, final LargeArray b, LargeArrayType out_type) {
      if (a != null && b != null && a.length() == b.length() && a.isNumeric() && b.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant() && b.isConstant()) {
               if (out_type.isIntegerNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getLong(0L) / b.getLong(0L));
               } else if (out_type.isRealNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, a.getDouble(0L) / b.getDouble(0L));
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                  float[] elem_b = ((ComplexFloatLargeArray)b).getComplexFloat(0L);
                  return LargeArrayUtils.createConstant(out_type, length, complexDiv(elem_a, elem_b));
               } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                  double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                  double[] elem_b = ((ComplexDoubleLargeArray)b).getComplexDouble(0L);
                  return LargeArrayUtils.createConstant(out_type, length, complexDiv(elem_a, elem_b));
               } else {
                  throw new IllegalArgumentException("Invalid array type.");
               }
            } else {
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               final LargeArray res;
               if (out_type.isIntegerNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setLong(k, a.getLong(k) / b.getLong(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var24) {
                        for(long i = 0L; i < length; ++i) {
                           res.setLong(i, a.getLong(i) / b.getLong(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setLong(i, a.getLong(i) / b.getLong(i));
                     }
                  }
               } else if (out_type.isRealNumericType()) {
                  res = LargeArrayUtils.create(out_type, length, false);
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setDouble(k, a.getDouble(k) / b.getDouble(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var23) {
                        for(long i = 0L; i < length; ++i) {
                           res.setDouble(i, a.getDouble(i) / b.getDouble(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, a.getDouble(i) / b.getDouble(i));
                     }
                  }
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexFloatLargeArray _bc = (ComplexFloatLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              float[] elem_res = new float[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 float[] elem_a = _ac.getComplexFloat(k);
                                 float[] elem_b = _bc.getComplexFloat(k);
                                 float r = elem_b[0] * elem_b[0] + elem_b[1] * elem_b[1];
                                 elem_res[0] = (elem_a[0] * elem_b[0] + elem_a[1] * elem_b[1]) / r;
                                 elem_res[1] = (elem_a[1] * elem_b[0] - elem_a[0] * elem_b[1]) / r;
                                 resc.setComplexFloat(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var22) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           float[] elem_b = _bc.getComplexFloat(i);
                           float r = elem_b[0] * elem_b[0] + elem_b[1] * elem_b[1];
                           elem_res[0] = (elem_a[0] * elem_b[0] + elem_a[1] * elem_b[1]) / r;
                           elem_res[1] = (elem_a[1] * elem_b[0] - elem_a[0] * elem_b[1]) / r;
                           resc.setComplexFloat(i, elem_res);
                        }
                     }
                  } else {
                     float[] elem_res = new float[2];

                     for(long i = 0L; i < length; ++i) {
                        float[] elem_a = _ac.getComplexFloat(i);
                        float[] elem_b = _bc.getComplexFloat(i);
                        float r = elem_b[0] * elem_b[0] + elem_b[1] * elem_b[1];
                        elem_res[0] = (elem_a[0] * elem_b[0] + elem_a[1] * elem_b[1]) / r;
                        elem_res[1] = (elem_a[1] * elem_b[0] - elem_a[0] * elem_b[1]) / r;
                        resc.setComplexFloat(i, elem_res);
                     }
                  }
               } else {
                  if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                     throw new IllegalArgumentException("Invalid array type.");
                  }

                  final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)LargeArrayUtils.convert(a, out_type);
                  final ComplexDoubleLargeArray _bc = (ComplexDoubleLargeArray)LargeArrayUtils.convert(b, out_type);
                  if (_ac.getType() == a.getType() && _bc.getType() == b.getType()) {
                     res = LargeArrayUtils.create(out_type, length, false);
                  } else if (_ac.getType() != a.getType()) {
                     res = _ac;
                  } else {
                     res = _bc;
                  }

                  final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              double[] elem_res = new double[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 double[] elem_a = _ac.getComplexDouble(k);
                                 double[] elem_b = _bc.getComplexDouble(k);
                                 double r = elem_b[0] * elem_b[0] + elem_b[1] * elem_b[1];
                                 elem_res[0] = (elem_a[0] * elem_b[0] + elem_a[1] * elem_b[1]) / r;
                                 elem_res[1] = (elem_a[1] * elem_b[0] - elem_a[0] * elem_b[1]) / r;
                                 resc.setComplexDouble(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var21) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double[] elem_b = _bc.getComplexDouble(i);
                           double r = elem_b[0] * elem_b[0] + elem_b[1] * elem_b[1];
                           elem_res[0] = (elem_a[0] * elem_b[0] + elem_a[1] * elem_b[1]) / r;
                           elem_res[1] = (elem_a[1] * elem_b[0] - elem_a[0] * elem_b[1]) / r;
                           resc.setComplexDouble(i, elem_res);
                        }
                     }
                  } else {
                     double[] elem_res = new double[2];

                     for(long i = 0L; i < length; ++i) {
                        double[] elem_a = _ac.getComplexDouble(i);
                        double[] elem_b = _bc.getComplexDouble(i);
                        double r = elem_b[0] * elem_b[0] + elem_b[1] * elem_b[1];
                        elem_res[0] = (elem_a[0] * elem_b[0] + elem_a[1] * elem_b[1]) / r;
                        elem_res[1] = (elem_a[1] * elem_b[0] - elem_a[0] * elem_b[1]) / r;
                        resc.setComplexDouble(i, elem_res);
                     }
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || b == null || a.length() != b.length() || !a.isNumeric() || !b.isNumeric()");
      }
   }

   public static LargeArray pow(LargeArray a, double n) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return pow(a, n, out_type);
   }

   public static LargeArray pow(final LargeArray a, final double n, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexPow(elem_a, n));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexPow(elem_a, n));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.pow(a.getDouble(0L), n));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           double mod = FastMath.pow(FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1])), n);
                           double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]);
                           elem_res[0] = (float)(mod * FastMath.cos(n * arg));
                           elem_res[1] = (float)(mod * FastMath.sin(n * arg));
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 float[] elem_res = new float[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    double mod = FastMath.pow(FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1])), n);
                                    double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]);
                                    elem_res[0] = (float)(mod * FastMath.cos(n * arg));
                                    elem_res[1] = (float)(mod * FastMath.sin(n * arg));
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var23) {
                           float[] elem_res = new float[2];

                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              double mod = FastMath.pow(FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1])), n);
                              double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]);
                              elem_res[0] = (float)(mod * FastMath.cos(n * arg));
                              elem_res[1] = (float)(mod * FastMath.sin(n * arg));
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double mod = FastMath.pow(FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]), n);
                           double arg = FastMath.atan2(elem_a[1], elem_a[0]);
                           elem_res[0] = mod * FastMath.cos(n * arg);
                           elem_res[1] = mod * FastMath.sin(n * arg);
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 double[] elem_res = new double[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    double mod = FastMath.pow(FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]), n);
                                    double arg = FastMath.atan2(elem_a[1], elem_a[0]);
                                    elem_res[0] = mod * FastMath.cos(n * arg);
                                    elem_res[1] = mod * FastMath.sin(n * arg);
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var22) {
                           double[] elem_res = new double[2];

                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              double mod = FastMath.pow(FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]), n);
                              double arg = FastMath.atan2(elem_a[1], elem_a[0]);
                              elem_res[0] = mod * FastMath.cos(n * arg);
                              elem_res[1] = mod * FastMath.sin(n * arg);
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.pow(a.getDouble(k), n));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var24) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.pow(a.getDouble(i), n));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.pow(a.getDouble(i), n));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray neg(LargeArray a) {
      LargeArrayType out_type = a.getType();
      return neg(a, out_type);
   }

   public static LargeArray neg(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (out_type.isIntegerNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, -a.getLong(0L));
               } else if (out_type.isRealNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, -a.getDouble(0L));
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                  return LargeArrayUtils.createConstant(out_type, length, new float[]{-elem_a[0], -elem_a[1]});
               } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                  double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                  return LargeArrayUtils.createConstant(out_type, length, new double[]{-elem_a[0], -elem_a[1]});
               } else {
                  throw new IllegalArgumentException("Invalid array type.");
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (out_type.isIntegerNumericType()) {
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setLong(k, -a.getLong(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var19) {
                        for(long i = 0L; i < length; ++i) {
                           res.setLong(i, -a.getLong(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setLong(i, -a.getLong(i));
                     }
                  }
               } else if (out_type.isRealNumericType()) {
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setDouble(k, -a.getDouble(k));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var18) {
                        for(long i = 0L; i < length; ++i) {
                           res.setDouble(i, -a.getDouble(i));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, -a.getDouble(i));
                     }
                  }
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                  final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              float[] elem_res = new float[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 float[] elem_a = _ac.getComplexFloat(k);
                                 elem_res[0] = -elem_a[0];
                                 elem_res[1] = -elem_a[1];
                                 resc.setComplexFloat(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var17) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           elem_res[0] = -elem_a[0];
                           elem_res[1] = -elem_a[1];
                           resc.setComplexFloat(i, elem_res);
                        }
                     }
                  } else {
                     float[] elem_res = new float[2];

                     for(long i = 0L; i < length; ++i) {
                        float[] elem_a = _ac.getComplexFloat(i);
                        elem_res[0] = -elem_a[0];
                        elem_res[1] = -elem_a[1];
                        resc.setComplexFloat(i, elem_res);
                     }
                  }
               } else {
                  if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                     throw new IllegalArgumentException("Invalid array type.");
                  }

                  final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                  final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              double[] elem_res = new double[2];

                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 double[] elem_a = _ac.getComplexDouble(k);
                                 elem_res[0] = -elem_a[0];
                                 elem_res[1] = -elem_a[1];
                                 resc.setComplexDouble(k, elem_res);
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var16) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           elem_res[0] = -elem_a[0];
                           elem_res[1] = -elem_a[1];
                           resc.setComplexDouble(i, elem_res);
                        }
                     }
                  } else {
                     double[] elem_res = new double[2];

                     for(long i = 0L; i < length; ++i) {
                        double[] elem_a = _ac.getComplexDouble(i);
                        elem_res[0] = -elem_a[0];
                        elem_res[1] = -elem_a[1];
                        resc.setComplexDouble(i, elem_res);
                     }
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray sqrt(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return sqrt(a, out_type);
   }

   public static LargeArray sqrt(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexSqrt(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexSqrt(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.sqrt(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                           elem_res[0] = (float)FastMath.sqrt(((double)elem_a[0] + mod) / (double)2.0F);
                           elem_res[1] = (float)((double)FastMath.signum(elem_a[1]) * FastMath.sqrt(((double)(-elem_a[0]) + mod) / (double)2.0F));
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 float[] elem_res = new float[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                                    elem_res[0] = (float)FastMath.sqrt(((double)elem_a[0] + mod) / (double)2.0F);
                                    elem_res[1] = (float)((double)FastMath.signum(elem_a[1]) * FastMath.sqrt(((double)(-elem_a[0]) + mod) / (double)2.0F));
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var19) {
                           float[] elem_res = new float[2];

                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                              elem_res[0] = (float)FastMath.sqrt(((double)elem_a[0] + mod) / (double)2.0F);
                              elem_res[1] = (float)((double)FastMath.signum(elem_a[1]) * FastMath.sqrt(((double)(-elem_a[0]) + mod) / (double)2.0F));
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                           elem_res[0] = FastMath.sqrt((elem_a[0] + mod) / (double)2.0F);
                           elem_res[1] = FastMath.signum(elem_a[1]) * FastMath.sqrt((-elem_a[0] + mod) / (double)2.0F);
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 double[] elem_res = new double[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                                    elem_res[0] = FastMath.sqrt((elem_a[0] + mod) / (double)2.0F);
                                    elem_res[1] = FastMath.signum(elem_a[1]) * FastMath.sqrt((-elem_a[0] + mod) / (double)2.0F);
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var18) {
                           double[] elem_res = new double[2];

                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                              elem_res[0] = FastMath.sqrt((elem_a[0] + mod) / (double)2.0F);
                              elem_res[1] = FastMath.signum(elem_a[1]) * FastMath.sqrt((-elem_a[0] + mod) / (double)2.0F);
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.sqrt(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var20) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.sqrt(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.sqrt(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray log(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return log(a, out_type);
   }

   public static LargeArray log(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexLog(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexLog(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.log(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                           double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]);
                           elem_res[0] = (float)FastMath.log(mod);
                           elem_res[1] = (float)arg;
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 float[] elem_res = new float[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                                    double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]);
                                    elem_res[0] = (float)FastMath.log(mod);
                                    elem_res[1] = (float)arg;
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var21) {
                           float[] elem_res = new float[2];

                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                              double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]);
                              elem_res[0] = (float)FastMath.log(mod);
                              elem_res[1] = (float)arg;
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                           double arg = FastMath.atan2(elem_a[1], elem_a[0]);
                           elem_res[0] = FastMath.log(mod);
                           elem_res[1] = arg;
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 double[] elem_res = new double[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                                    double arg = FastMath.atan2(elem_a[1], elem_a[0]);
                                    elem_res[0] = FastMath.log(mod);
                                    elem_res[1] = arg;
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var20) {
                           double[] elem_res = new double[2];

                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                              double arg = FastMath.atan2(elem_a[1], elem_a[0]);
                              elem_res[0] = FastMath.log(mod);
                              elem_res[1] = arg;
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.log(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var22) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.log(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.log(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray log10(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return log10(a, out_type);
   }

   public static LargeArray log10(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexLog10(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexLog10(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.log10(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final double scale = FastMath.log((double)10.0F);
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                           double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]) / scale;
                           elem_res[0] = (float)(FastMath.log(mod) / scale);
                           elem_res[1] = (float)arg;
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 float[] elem_res = new float[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                                    double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]) / scale;
                                    elem_res[0] = (float)(FastMath.log(mod) / scale);
                                    elem_res[1] = (float)arg;
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var23) {
                           float[] elem_res = new float[2];

                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              double mod = FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                              double arg = FastMath.atan2((double)elem_a[1], (double)elem_a[0]) / scale;
                              elem_res[0] = (float)(FastMath.log(mod) / scale);
                              elem_res[1] = (float)arg;
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final double scale = FastMath.log((double)10.0F);
                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                           double arg = FastMath.atan2(elem_a[1], elem_a[0]) / scale;
                           elem_res[0] = FastMath.log(mod) / scale;
                           elem_res[1] = arg;
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 double[] elem_res = new double[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                                    double arg = FastMath.atan2(elem_a[1], elem_a[0]) / scale;
                                    elem_res[0] = FastMath.log(mod) / scale;
                                    elem_res[1] = arg;
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var22) {
                           double[] elem_res = new double[2];

                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              double mod = FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]);
                              double arg = FastMath.atan2(elem_a[1], elem_a[0]) / scale;
                              elem_res[0] = FastMath.log(mod) / scale;
                              elem_res[1] = arg;
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.log10(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var24) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.log10(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.log10(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray exp(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return exp(a, out_type);
   }

   public static LargeArray exp(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexExp(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexExp(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.exp(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           elem_res[0] = (float)(FastMath.exp((double)elem_a[0]) * FastMath.cos((double)elem_a[1]));
                           elem_res[1] = (float)(FastMath.exp((double)elem_a[0]) * FastMath.sin((double)elem_a[1]));
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 float[] elem_res = new float[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    elem_res[0] = (float)(FastMath.exp((double)elem_a[0]) * FastMath.cos((double)elem_a[1]));
                                    elem_res[1] = (float)(FastMath.exp((double)elem_a[0]) * FastMath.sin((double)elem_a[1]));
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var17) {
                           float[] elem_res = new float[2];

                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              elem_res[0] = (float)(FastMath.exp((double)elem_a[0]) * FastMath.cos((double)elem_a[1]));
                              elem_res[1] = (float)(FastMath.exp((double)elem_a[0]) * FastMath.sin((double)elem_a[1]));
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           elem_res[0] = FastMath.exp(elem_a[0]) * FastMath.cos(elem_a[1]);
                           elem_res[1] = FastMath.exp(elem_a[0]) * FastMath.sin(elem_a[1]);
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 double[] elem_res = new double[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    elem_res[0] = FastMath.exp(elem_a[0]) * FastMath.cos(elem_a[1]);
                                    elem_res[1] = FastMath.exp(elem_a[0]) * FastMath.sin(elem_a[1]);
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var16) {
                           double[] elem_res = new double[2];

                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              elem_res[0] = FastMath.exp(elem_a[0]) * FastMath.cos(elem_a[1]);
                              elem_res[1] = FastMath.exp(elem_a[0]) * FastMath.sin(elem_a[1]);
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.exp(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var18) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.exp(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.exp(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray abs(LargeArray a) {
      LargeArrayType out_type = a.getType() == LargeArrayType.COMPLEX_FLOAT ? LargeArrayType.FLOAT : (a.getType() == LargeArrayType.COMPLEX_DOUBLE ? LargeArrayType.DOUBLE : a.getType());
      return abs(a, out_type);
   }

   public static LargeArray abs(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (out_type.isIntegerNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.abs(a.getLong(0L)));
               } else if (out_type.isRealNumericType()) {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.abs(a.getDouble(0L)));
               } else if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                  float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                  return LargeArrayUtils.createConstant(out_type, length, complexAbs(elem_a));
               } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                  double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                  return LargeArrayUtils.createConstant(out_type, length, complexAbs(elem_a));
               } else {
                  throw new IllegalArgumentException("Invalid array type.");
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (a.getType().isIntegerNumericType()) {
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setLong(k, FastMath.abs(a.getLong(k)));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var18) {
                        for(long i = 0L; i < length; ++i) {
                           res.setDouble(i, FastMath.abs(a.getDouble(i)));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.abs(a.getDouble(i)));
                     }
                  }
               } else if (a.getType().isRealNumericType()) {
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 res.setDouble(k, FastMath.abs(a.getDouble(k)));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var17) {
                        for(long i = 0L; i < length; ++i) {
                           res.setDouble(i, FastMath.abs(a.getDouble(i)));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.abs(a.getDouble(i)));
                     }
                  }
               } else if (a.getType() == LargeArrayType.COMPLEX_FLOAT) {
                  final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 float[] elem_a = _ac.getComplexFloat(k);
                                 res.setFloat(k, (float)FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1])));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var16) {
                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           res.setFloat(i, (float)FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1])));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        float[] elem_a = _ac.getComplexFloat(i);
                        res.setFloat(i, (float)FastMath.sqrt((double)(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1])));
                     }
                  }
               } else {
                  if (a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
                     throw new IllegalArgumentException("Invalid array type.");
                  }

                  final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                  if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                     long k = length / (long)nthreads;
                     Future[] threads = new Future[nthreads];

                     for(int j = 0; j < nthreads; ++j) {
                        final long firstIdx = (long)j * k;
                        final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                        threads[j] = ConcurrencyUtils.submit(new Runnable() {
                           public void run() {
                              for(long k = firstIdx; k < lastIdx; ++k) {
                                 double[] elem_a = _ac.getComplexDouble(k);
                                 res.setDouble(k, FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                              }

                           }
                        });
                     }

                     try {
                        ConcurrencyUtils.waitForCompletion(threads);
                     } catch (ExecutionException | InterruptedException var15) {
                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           res.setDouble(i, FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                        }
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        double[] elem_a = _ac.getComplexDouble(i);
                        res.setDouble(i, FastMath.sqrt(elem_a[0] * elem_a[0] + elem_a[1] * elem_a[1]));
                     }
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray sin(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return sin(a, out_type);
   }

   public static LargeArray sin(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexSin(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexSin(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.sin(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           elem_res[0] = (float)(FastMath.sin((double)elem_a[0]) * FastMath.cosh((double)elem_a[1]));
                           elem_res[1] = (float)(FastMath.cos((double)elem_a[0]) * FastMath.sinh((double)elem_a[1]));
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 float[] elem_res = new float[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    elem_res[0] = (float)(FastMath.sin((double)elem_a[0]) * FastMath.cosh((double)elem_a[1]));
                                    elem_res[1] = (float)(FastMath.cos((double)elem_a[0]) * FastMath.sinh((double)elem_a[1]));
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var17) {
                           float[] elem_res = new float[2];

                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              elem_res[0] = (float)(FastMath.sin((double)elem_a[0]) * FastMath.cosh((double)elem_a[1]));
                              elem_res[1] = (float)(FastMath.cos((double)elem_a[0]) * FastMath.sinh((double)elem_a[1]));
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           elem_res[0] = FastMath.sin(elem_a[0]) * FastMath.cosh(elem_a[1]);
                           elem_res[1] = FastMath.cos(elem_a[0]) * FastMath.sinh(elem_a[1]);
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 double[] elem_res = new double[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    elem_res[0] = FastMath.sin(elem_a[0]) * FastMath.cosh(elem_a[1]);
                                    elem_res[1] = FastMath.cos(elem_a[0]) * FastMath.sinh(elem_a[1]);
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var16) {
                           double[] elem_res = new double[2];

                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              elem_res[0] = FastMath.sin(elem_a[0]) * FastMath.cosh(elem_a[1]);
                              elem_res[1] = FastMath.cos(elem_a[0]) * FastMath.sinh(elem_a[1]);
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.sin(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var18) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.sin(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.sin(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray cos(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return cos(a, out_type);
   }

   public static LargeArray cos(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexCos(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexCos(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.cos(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        float[] elem_res = new float[2];

                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           elem_res[0] = (float)(FastMath.cos((double)elem_a[0]) * FastMath.cosh((double)elem_a[1]));
                           elem_res[1] = (float)(-FastMath.sin((double)elem_a[0]) * FastMath.sinh((double)elem_a[1]));
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 float[] elem_res = new float[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    elem_res[0] = (float)(FastMath.cos((double)elem_a[0]) * FastMath.cosh((double)elem_a[1]));
                                    elem_res[1] = (float)(-FastMath.sin((double)elem_a[0]) * FastMath.sinh((double)elem_a[1]));
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var17) {
                           float[] elem_res = new float[2];

                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              elem_res[0] = (float)(FastMath.cos((double)elem_a[0]) * FastMath.cosh((double)elem_a[1]));
                              elem_res[1] = (float)(-FastMath.sin((double)elem_a[0]) * FastMath.sinh((double)elem_a[1]));
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        double[] elem_res = new double[2];

                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           elem_res[0] = FastMath.cos(elem_a[0]) * FastMath.cosh(elem_a[1]);
                           elem_res[1] = -FastMath.sin(elem_a[0]) * FastMath.sinh(elem_a[1]);
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 double[] elem_res = new double[2];

                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    elem_res[0] = FastMath.cos(elem_a[0]) * FastMath.cosh(elem_a[1]);
                                    elem_res[1] = -FastMath.sin(elem_a[0]) * FastMath.sinh(elem_a[1]);
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var16) {
                           double[] elem_res = new double[2];

                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              elem_res[0] = FastMath.cos(elem_a[0]) * FastMath.cosh(elem_a[1]);
                              elem_res[1] = -FastMath.sin(elem_a[0]) * FastMath.sinh(elem_a[1]);
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.cos(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var18) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.cos(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.cos(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray tan(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return tan(a, out_type);
   }

   public static LargeArray tan(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexTan(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexTan(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.tan(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           float[] s = complexSin(elem_a);
                           float[] c = complexCos(elem_a);
                           float[] elem_res = complexDiv(s, c);
                           resc.setComplexFloat(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    float[] s = LargeArrayArithmetics.complexSin(elem_a);
                                    float[] c = LargeArrayArithmetics.complexCos(elem_a);
                                    float[] elem_res = LargeArrayArithmetics.complexDiv(s, c);
                                    resc.setComplexFloat(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var19) {
                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              float[] s = complexSin(elem_a);
                              float[] c = complexCos(elem_a);
                              float[] elem_res = complexDiv(s, c);
                              resc.setComplexFloat(i, elem_res);
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           double[] s = complexSin(elem_a);
                           double[] c = complexCos(elem_a);
                           double[] elem_res = complexDiv(s, c);
                           resc.setComplexDouble(i, elem_res);
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    double[] s = LargeArrayArithmetics.complexSin(elem_a);
                                    double[] c = LargeArrayArithmetics.complexCos(elem_a);
                                    double[] elem_res = LargeArrayArithmetics.complexDiv(s, c);
                                    resc.setComplexDouble(k, elem_res);
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var18) {
                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              double[] s = complexSin(elem_a);
                              double[] c = complexCos(elem_a);
                              double[] elem_res = complexDiv(s, c);
                              resc.setComplexDouble(i, elem_res);
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.tan(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var20) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.tan(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.tan(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray asin(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return asin(a, out_type);
   }

   public static LargeArray asin(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexAsin(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexAsin(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.asin(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           resc.setComplexFloat(i, complexAsin(elem_a));
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    resc.setComplexFloat(k, LargeArrayArithmetics.complexAsin(elem_a));
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var17) {
                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              resc.setComplexFloat(i, complexAsin(elem_a));
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           resc.setComplexDouble(i, complexAsin(elem_a));
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    resc.setComplexDouble(k, LargeArrayArithmetics.complexAsin(elem_a));
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var16) {
                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              resc.setComplexDouble(i, complexAsin(elem_a));
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.asin(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var18) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.asin(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.asin(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray acos(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return acos(a, out_type);
   }

   public static LargeArray acos(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexAcos(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexAcos(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.acos(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           resc.setComplexFloat(i, complexAcos(elem_a));
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    resc.setComplexFloat(k, LargeArrayArithmetics.complexAcos(elem_a));
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var17) {
                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              resc.setComplexFloat(i, complexAcos(elem_a));
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           resc.setComplexDouble(i, complexAcos(elem_a));
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    resc.setComplexDouble(k, LargeArrayArithmetics.complexAcos(elem_a));
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var16) {
                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              resc.setComplexDouble(i, complexAcos(elem_a));
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.acos(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var18) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.acos(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.acos(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray atan(LargeArray a) {
      LargeArrayType out_type = a.getType().isIntegerNumericType() ? LargeArrayType.FLOAT : a.getType();
      return atan(a, out_type);
   }

   public static LargeArray atan(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric()) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     float[] elem_a = ((ComplexFloatLargeArray)a).getComplexFloat(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexAtan(elem_a));
                  } else if (out_type == LargeArrayType.COMPLEX_DOUBLE) {
                     double[] elem_a = ((ComplexDoubleLargeArray)a).getComplexDouble(0L);
                     return LargeArrayUtils.createConstant(out_type, length, complexAtan(elem_a));
                  } else {
                     throw new IllegalArgumentException("Invalid array type.");
                  }
               } else {
                  return LargeArrayUtils.createConstant(out_type, length, FastMath.atan(a.getDouble(0L)));
               }
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (!out_type.isIntegerNumericType() && !out_type.isRealNumericType()) {
                  if (out_type == LargeArrayType.COMPLEX_FLOAT) {
                     final ComplexFloatLargeArray _ac = (ComplexFloatLargeArray)a;
                     final ComplexFloatLargeArray resc = (ComplexFloatLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           float[] elem_a = _ac.getComplexFloat(i);
                           resc.setComplexFloat(i, complexAtan(elem_a));
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    float[] elem_a = _ac.getComplexFloat(k);
                                    resc.setComplexFloat(k, LargeArrayArithmetics.complexAtan(elem_a));
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var17) {
                           for(long i = 0L; i < length; ++i) {
                              float[] elem_a = _ac.getComplexFloat(i);
                              resc.setComplexFloat(i, complexAtan(elem_a));
                           }
                        }
                     }
                  } else {
                     if (out_type != LargeArrayType.COMPLEX_DOUBLE) {
                        throw new IllegalArgumentException("Invalid array type.");
                     }

                     final ComplexDoubleLargeArray _ac = (ComplexDoubleLargeArray)a;
                     final ComplexDoubleLargeArray resc = (ComplexDoubleLargeArray)res;
                     if (nthreads < 2 || length < ConcurrencyUtils.getConcurrentThreshold()) {
                        for(long i = 0L; i < length; ++i) {
                           double[] elem_a = _ac.getComplexDouble(i);
                           resc.setComplexDouble(i, complexAtan(elem_a));
                        }
                     } else {
                        long k = length / (long)nthreads;
                        Future[] threads = new Future[nthreads];

                        for(int j = 0; j < nthreads; ++j) {
                           final long firstIdx = (long)j * k;
                           final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                           threads[j] = ConcurrencyUtils.submit(new Runnable() {
                              public void run() {
                                 for(long k = firstIdx; k < lastIdx; ++k) {
                                    double[] elem_a = _ac.getComplexDouble(k);
                                    resc.setComplexDouble(k, LargeArrayArithmetics.complexAtan(elem_a));
                                 }

                              }
                           });
                        }

                        try {
                           ConcurrencyUtils.waitForCompletion(threads);
                        } catch (ExecutionException | InterruptedException var16) {
                           for(long i = 0L; i < length; ++i) {
                              double[] elem_a = _ac.getComplexDouble(i);
                              resc.setComplexDouble(i, complexAtan(elem_a));
                           }
                        }
                     }
                  }
               } else if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setDouble(k, FastMath.atan(a.getDouble(k)));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var18) {
                     for(long i = 0L; i < length; ++i) {
                        res.setDouble(i, FastMath.atan(a.getDouble(i)));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setDouble(i, FastMath.atan(a.getDouble(i)));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric()");
      }
   }

   public static LargeArray signum(LargeArray a) {
      LargeArrayType out_type = LargeArrayType.BYTE;
      return signum(a, out_type);
   }

   public static LargeArray signum(final LargeArray a, LargeArrayType out_type) {
      if (a != null && a.isNumeric() && a.getType() != LargeArrayType.COMPLEX_FLOAT && a.getType() != LargeArrayType.COMPLEX_DOUBLE) {
         if (!out_type.isNumericType()) {
            throw new IllegalArgumentException("Output type must be numeric.");
         } else {
            long length = a.length();
            if (a.isConstant()) {
               return LargeArrayUtils.createConstant(out_type, length, (byte)((int)FastMath.signum(a.getDouble(0L))));
            } else {
               final LargeArray res = LargeArrayUtils.create(out_type, length, false);
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           for(long k = firstIdx; k < lastIdx; ++k) {
                              res.setByte(k, (byte)((int)FastMath.signum(a.getDouble(k))));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (ExecutionException | InterruptedException var14) {
                     for(long i = 0L; i < length; ++i) {
                        res.setByte(i, (byte)((int)FastMath.signum(a.getDouble(i))));
                     }
                  }
               } else {
                  for(long i = 0L; i < length; ++i) {
                     res.setByte(i, (byte)((int)FastMath.signum(a.getDouble(i))));
                  }
               }

               return res;
            }
         }
      } else {
         throw new IllegalArgumentException("a == null || !a.isNumeric() || a.getType() == LargeArrayType.COMPLEX_FLOAT || a.getType() == LargeArrayType.COMPLEX_DOUBLE");
      }
   }
}
