package pl.edu.icm.jlargearrays;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;
import sun.misc.Unsafe;

public class LargeArrayUtils {
   public static final Unsafe UNSAFE;

   private LargeArrayUtils() {
   }

   public static void arraycopy(LargeArray src, long srcPos, LargeArray dest, long destPos, long length) {
      if (src.getType() != dest.getType()) {
         throw new IllegalArgumentException("The type of source array is different than the type of destimation array.");
      } else {
         switch (src.getType()) {
            case LOGIC:
               arraycopy((LogicLargeArray)src, srcPos, (LogicLargeArray)dest, destPos, length);
               break;
            case BYTE:
               arraycopy((UnsignedByteLargeArray)src, srcPos, (UnsignedByteLargeArray)dest, destPos, length);
               break;
            case SHORT:
               arraycopy((ShortLargeArray)src, srcPos, (ShortLargeArray)dest, destPos, length);
               break;
            case INT:
               arraycopy((IntLargeArray)src, srcPos, (IntLargeArray)dest, destPos, length);
               break;
            case LONG:
               arraycopy((LongLargeArray)src, srcPos, (LongLargeArray)dest, destPos, length);
               break;
            case FLOAT:
               arraycopy((FloatLargeArray)src, srcPos, (FloatLargeArray)dest, destPos, length);
               break;
            case DOUBLE:
               arraycopy((DoubleLargeArray)src, srcPos, (DoubleLargeArray)dest, destPos, length);
               break;
            case COMPLEX_FLOAT:
               arraycopy((ComplexFloatLargeArray)src, srcPos, (ComplexFloatLargeArray)dest, destPos, length);
               break;
            case COMPLEX_DOUBLE:
               arraycopy((ComplexDoubleLargeArray)src, srcPos, (ComplexDoubleLargeArray)dest, destPos, length);
               break;
            case STRING:
               arraycopy((StringLargeArray)src, srcPos, (StringLargeArray)dest, destPos, length);
               break;
            case OBJECT:
               arraycopy((ObjectLargeArray)src, srcPos, (ObjectLargeArray)dest, destPos, length);
               break;
            default:
               throw new IllegalArgumentException("Invalid array type.");
         }

      }
   }

   public static void arraycopy(Object src, long srcPos, LargeArray dest, long destPos, long length) {
      switch (dest.getType()) {
         case LOGIC:
            arraycopy((boolean[])src, (int)srcPos, (LogicLargeArray)dest, destPos, length);
            break;
         case BYTE:
            arraycopy((byte[])src, (int)srcPos, (ByteLargeArray)dest, destPos, length);
            break;
         case SHORT:
            arraycopy((short[])src, (int)srcPos, (ShortLargeArray)dest, destPos, length);
            break;
         case INT:
            arraycopy((int[])src, (int)srcPos, (IntLargeArray)dest, destPos, length);
            break;
         case LONG:
            arraycopy((long[])src, (int)srcPos, (LongLargeArray)dest, destPos, length);
            break;
         case FLOAT:
            arraycopy((float[])src, (int)srcPos, (FloatLargeArray)dest, destPos, length);
            break;
         case DOUBLE:
            arraycopy((double[])src, (int)srcPos, (DoubleLargeArray)dest, destPos, length);
            break;
         case COMPLEX_FLOAT:
            arraycopy((float[])src, (int)srcPos, (ComplexFloatLargeArray)dest, destPos, length);
            break;
         case COMPLEX_DOUBLE:
            arraycopy((double[])src, (int)srcPos, (ComplexDoubleLargeArray)dest, destPos, length);
            break;
         case STRING:
            arraycopy((String[])src, (int)srcPos, (StringLargeArray)dest, destPos, length);
            break;
         case OBJECT:
            arraycopy(src, (int)srcPos, (ObjectLargeArray)dest, destPos, length);
            break;
         case UNSIGNED_BYTE:
            Class dataClass = src.getClass();
            Class componentClass = dataClass.getComponentType();
            if (componentClass == Byte.TYPE) {
               arraycopy((byte[])src, (int)srcPos, (UnsignedByteLargeArray)dest, destPos, length);
            } else {
               arraycopy((short[])src, (int)srcPos, (UnsignedByteLargeArray)dest, destPos, length);
            }
            break;
         default:
            throw new IllegalArgumentException("Invalid array type.");
      }

   }

   public static void arraycopy(final LogicLargeArray src, final long srcPos, final LogicLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setByte(destPos + k, src.getByte(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setByte(j, src.getByte(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setByte(j, src.getByte(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setByte(j, src.getByte(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final boolean[] src, final int srcPos, final LogicLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setBoolean(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setBoolean(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setBoolean(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setBoolean(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final ByteLargeArray src, final long srcPos, final ByteLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setByte(destPos + k, src.getByte(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setByte(j, src.getByte(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setByte(j, src.getByte(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setByte(j, src.getByte(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final byte[] src, final int srcPos, final ByteLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setByte(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setByte(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setByte(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setByte(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final UnsignedByteLargeArray src, final long srcPos, final UnsignedByteLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setByte(destPos + k, src.getByte(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setByte(j, src.getByte(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setByte(j, src.getByte(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setByte(j, src.getByte(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final byte[] src, final int srcPos, final UnsignedByteLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setByte(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setByte(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setByte(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setByte(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final short[] src, final int srcPos, final UnsignedByteLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setUnsignedByte(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setUnsignedByte(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setUnsignedByte(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setUnsignedByte(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final ShortLargeArray src, final long srcPos, final ShortLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setShort(destPos + k, src.getShort(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setShort(j, src.getShort(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setShort(j, src.getShort(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setShort(j, src.getShort(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final short[] src, final int srcPos, final ShortLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setShort(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setShort(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setShort(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setShort(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final IntLargeArray src, final long srcPos, final IntLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setInt(destPos + k, src.getInt(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setInt(j, src.getInt(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setInt(j, src.getInt(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setInt(j, src.getInt(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final int[] src, final int srcPos, final IntLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setInt(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setInt(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setInt(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setInt(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final LongLargeArray src, final long srcPos, final LongLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setLong(destPos + k, src.getLong(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setLong(j, src.getLong(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setLong(j, src.getLong(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setLong(j, src.getLong(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final long[] src, final int srcPos, final LongLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setLong(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setLong(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setLong(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setLong(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final FloatLargeArray src, final long srcPos, final FloatLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setFloat(destPos + k, src.getFloat(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setFloat(j, src.getFloat(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setFloat(j, src.getFloat(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setFloat(j, src.getFloat(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final float[] src, final int srcPos, final FloatLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setFloat(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setFloat(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setFloat(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setFloat(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final DoubleLargeArray src, final long srcPos, final DoubleLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setDouble(destPos + k, src.getDouble(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setDouble(j, src.getDouble(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setDouble(j, src.getDouble(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setDouble(j, src.getDouble(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final double[] src, final int srcPos, final DoubleLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.setDouble(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setDouble(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.setDouble(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.setDouble(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final ComplexFloatLargeArray src, final long srcPos, final ComplexFloatLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setComplexFloat(destPos + k, src.getComplexFloat(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setComplexFloat(j, src.getComplexFloat(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setComplexFloat(j, src.getComplexFloat(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setComplexFloat(j, src.getComplexFloat(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final float[] src, final int srcPos, final ComplexFloatLargeArray dest, final long destPos, long length) {
      if (src.length % 2 != 0) {
         throw new IllegalArgumentException("The length of the source array must be even.");
      } else if (srcPos >= 0 && srcPos < src.length / 2) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           float[] elem = new float[2];

                           for(long k = firstIdx; k < lastIdx; ++k) {
                              elem[0] = src[2 * (srcPos + (int)k)];
                              elem[1] = src[2 * (srcPos + (int)k) + 1];
                              dest.setComplexFloat(destPos + k, elem);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     float[] elem = new float[2];

                     for(long j = destPos; j < destPos + length; ++j) {
                        elem[0] = src[2 * i];
                        elem[1] = src[2 * i + 1];
                        dest.setComplexFloat(j, elem);
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     float[] elem = new float[2];

                     for(long j = destPos; j < destPos + length; ++j) {
                        elem[0] = src[2 * i];
                        elem[1] = src[2 * i + 1];
                        dest.setComplexFloat(j, elem);
                        ++i;
                     }
                  }
               } else {
                  float[] elem = new float[2];

                  for(long j = destPos; j < destPos + length; ++j) {
                     elem[0] = src[2 * i];
                     elem[1] = src[2 * i + 1];
                     dest.setComplexFloat(j, elem);
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length / 2");
      }
   }

   public static void arraycopy(final ComplexDoubleLargeArray src, final long srcPos, final ComplexDoubleLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.setComplexDouble(destPos + k, src.getComplexDouble(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setComplexDouble(j, src.getComplexDouble(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.setComplexDouble(j, src.getComplexDouble(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.setComplexDouble(j, src.getComplexDouble(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final double[] src, final int srcPos, final ComplexDoubleLargeArray dest, final long destPos, long length) {
      if (src.length % 2 != 0) {
         throw new IllegalArgumentException("The length of the source array must be even.");
      } else if (srcPos >= 0 && srcPos < src.length / 2) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
               int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
               if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
                  long k = length / (long)nthreads;
                  Future[] threads = new Future[nthreads];

                  for(int j = 0; j < nthreads; ++j) {
                     final long firstIdx = (long)j * k;
                     final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
                     threads[j] = ConcurrencyUtils.submit(new Runnable() {
                        public void run() {
                           double[] elem = new double[2];

                           for(long k = firstIdx; k < lastIdx; ++k) {
                              elem[0] = src[2 * (srcPos + (int)k)];
                              elem[1] = src[2 * (srcPos + (int)k) + 1];
                              dest.setComplexDouble(destPos + k, elem);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     double[] elem = new double[2];

                     for(long j = destPos; j < destPos + length; ++j) {
                        elem[0] = src[2 * i];
                        elem[1] = src[2 * i + 1];
                        dest.setComplexDouble(j, elem);
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     double[] elem = new double[2];

                     for(long j = destPos; j < destPos + length; ++j) {
                        elem[0] = src[2 * i];
                        elem[1] = src[2 * i + 1];
                        dest.setComplexDouble(j, elem);
                        ++i;
                     }
                  }
               } else {
                  double[] elem = new double[2];

                  for(long j = destPos; j < destPos + length; ++j) {
                     elem[0] = src[2 * i];
                     elem[1] = src[2 * i + 1];
                     dest.setComplexDouble(j, elem);
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length / 2");
      }
   }

   public static void arraycopy(final StringLargeArray src, final long srcPos, final StringLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.set(destPos + k, src.get(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.set(j, src.get(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.set(j, src.get(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.set(j, src.get(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final String[] src, final int srcPos, final StringLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.set(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.set(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.set(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.set(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static void arraycopy(final ObjectLargeArray src, final long srcPos, final ObjectLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0L && srcPos < src.length()) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
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
                              dest.set(destPos + k, src.get(srcPos + k));
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.set(j, src.get(i));
                        ++i;
                     }
                  } catch (ExecutionException var18) {
                     long i = srcPos;

                     for(long j = destPos; i < srcPos + length; ++j) {
                        dest.set(j, src.get(i));
                        ++i;
                     }
                  }
               } else {
                  long i = srcPos;

                  for(long j = destPos; i < srcPos + length; ++j) {
                     dest.set(j, src.get(i));
                     ++i;
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length()");
      }
   }

   public static void arraycopy(final Object[] src, final int srcPos, final ObjectLargeArray dest, final long destPos, long length) {
      if (srcPos >= 0 && srcPos < src.length) {
         if (destPos >= 0L && destPos < dest.length()) {
            if (length < 0L) {
               throw new IllegalArgumentException("length < 0");
            } else if (dest.isConstant()) {
               throw new IllegalArgumentException("Constant arrays cannot be modified.");
            } else {
               int i = srcPos;
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
                              dest.set(destPos + k, src[srcPos + (int)k]);
                           }

                        }
                     });
                  }

                  try {
                     ConcurrencyUtils.waitForCompletion(threads);
                  } catch (InterruptedException var17) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.set(j, src[i++]);
                     }
                  } catch (ExecutionException var18) {
                     for(long j = destPos; j < destPos + length; ++j) {
                        dest.set(j, src[i++]);
                     }
                  }
               } else {
                  for(long j = destPos; j < destPos + length; ++j) {
                     dest.set(j, src[i++]);
                  }
               }

            }
         } else {
            throw new ArrayIndexOutOfBoundsException("destPos < 0 || destPos >= dest.length()");
         }
      } else {
         throw new ArrayIndexOutOfBoundsException("srcPos < 0 || srcPos >= src.length");
      }
   }

   public static LargeArray createConstant(LargeArrayType type, long length, Object value) {
      switch (type) {
         case LOGIC:
            byte v;
            if (value instanceof Boolean) {
               v = (byte)((Boolean)value ? 1 : 0);
            } else if (value instanceof Byte) {
               v = (Byte)value;
            } else if (value instanceof Short) {
               v = ((Short)value).byteValue();
            } else if (value instanceof Integer) {
               v = ((Integer)value).byteValue();
            } else if (value instanceof Long) {
               v = ((Long)value).byteValue();
            } else if (value instanceof Float) {
               v = ((Float)value).byteValue();
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = ((Double)value).byteValue();
            }

            return new LogicLargeArray(length, v);
         case BYTE:
            byte v;
            if (value instanceof Boolean) {
               v = (byte)((Boolean)value ? 1 : 0);
            } else if (value instanceof Byte) {
               v = (Byte)value;
            } else if (value instanceof Short) {
               v = ((Short)value).byteValue();
            } else if (value instanceof Integer) {
               v = ((Integer)value).byteValue();
            } else if (value instanceof Long) {
               v = ((Long)value).byteValue();
            } else if (value instanceof Float) {
               v = ((Float)value).byteValue();
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = ((Double)value).byteValue();
            }

            return new ByteLargeArray(length, v);
         case SHORT:
            short v;
            if (value instanceof Boolean) {
               v = (short)((Boolean)value ? 1 : 0);
            } else if (value instanceof Byte) {
               v = ((Byte)value).shortValue();
            } else if (value instanceof Short) {
               v = (Short)value;
            } else if (value instanceof Integer) {
               v = ((Integer)value).shortValue();
            } else if (value instanceof Long) {
               v = ((Long)value).shortValue();
            } else if (value instanceof Float) {
               v = ((Float)value).shortValue();
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = ((Double)value).shortValue();
            }

            return new ShortLargeArray(length, v);
         case INT:
            int v;
            if (value instanceof Boolean) {
               v = (Boolean)value ? 1 : 0;
            } else if (value instanceof Byte) {
               v = ((Byte)value).intValue();
            } else if (value instanceof Short) {
               v = ((Short)value).intValue();
            } else if (value instanceof Integer) {
               v = (Integer)value;
            } else if (value instanceof Long) {
               v = ((Long)value).intValue();
            } else if (value instanceof Float) {
               v = ((Float)value).intValue();
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = ((Double)value).intValue();
            }

            return new IntLargeArray(length, v);
         case LONG:
            long v;
            if (value instanceof Boolean) {
               v = (Boolean)value ? 1L : 0L;
            } else if (value instanceof Byte) {
               v = ((Byte)value).longValue();
            } else if (value instanceof Short) {
               v = ((Short)value).longValue();
            } else if (value instanceof Integer) {
               v = ((Integer)value).longValue();
            } else if (value instanceof Long) {
               v = (Long)value;
            } else if (value instanceof Float) {
               v = ((Float)value).longValue();
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = ((Double)value).longValue();
            }

            return new LongLargeArray(length, v);
         case FLOAT:
            float v;
            if (value instanceof Boolean) {
               v = (Boolean)value ? 1.0F : 0.0F;
            } else if (value instanceof Byte) {
               v = ((Byte)value).floatValue();
            } else if (value instanceof Short) {
               v = ((Short)value).floatValue();
            } else if (value instanceof Integer) {
               v = ((Integer)value).floatValue();
            } else if (value instanceof Long) {
               v = ((Long)value).floatValue();
            } else if (value instanceof Float) {
               v = (Float)value;
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = ((Double)value).floatValue();
            }

            return new FloatLargeArray(length, v);
         case DOUBLE:
            double v;
            if (value instanceof Boolean) {
               v = (Boolean)value ? (double)1.0F : (double)0.0F;
            } else if (value instanceof Byte) {
               v = ((Byte)value).doubleValue();
            } else if (value instanceof Short) {
               v = ((Short)value).doubleValue();
            } else if (value instanceof Integer) {
               v = ((Integer)value).doubleValue();
            } else if (value instanceof Long) {
               v = ((Long)value).doubleValue();
            } else if (value instanceof Float) {
               v = ((Float)value).doubleValue();
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = (Double)value;
            }

            return new DoubleLargeArray(length, v);
         case COMPLEX_FLOAT:
            Class dataClass = value.getClass();
            Class componentClass = dataClass.getComponentType();
            if (componentClass == Float.TYPE) {
               float[] v = (float[])value;
               return new ComplexFloatLargeArray(length, v);
            }

            throw new IllegalArgumentException("Invalid value type.");
         case COMPLEX_DOUBLE:
            Class dataClass = value.getClass();
            Class componentClass = dataClass.getComponentType();
            if (componentClass == Double.TYPE) {
               double[] v = (double[])value;
               return new ComplexDoubleLargeArray(length, v);
            }

            throw new IllegalArgumentException("Invalid value type.");
         case STRING:
            if (value instanceof String) {
               String v = (String)value;
               return new StringLargeArray(length, v);
            }

            throw new IllegalArgumentException("Invalid value type.");
         case OBJECT:
            return new ObjectLargeArray(length, value);
         case UNSIGNED_BYTE:
            short v;
            if (value instanceof Boolean) {
               v = (short)((Boolean)value ? 1 : 0);
            } else if (value instanceof Byte) {
               v = ((Byte)value).shortValue();
            } else if (value instanceof Short) {
               v = (Short)value;
            } else if (value instanceof Integer) {
               v = ((Integer)value).shortValue();
            } else if (value instanceof Long) {
               v = ((Long)value).shortValue();
            } else if (value instanceof Float) {
               v = ((Float)value).shortValue();
            } else {
               if (!(value instanceof Double)) {
                  throw new IllegalArgumentException("Invalid value type.");
               }

               v = ((Double)value).shortValue();
            }

            return new UnsignedByteLargeArray(length, v);
         default:
            throw new IllegalArgumentException("Invalid array type.");
      }
   }

   public static LargeArray create(LargeArrayType type, long length) {
      return create(type, length, true);
   }

   public static LargeArray create(LargeArrayType type, long length, boolean zeroNativeMemory) {
      switch (type) {
         case LOGIC:
            return new LogicLargeArray(length, zeroNativeMemory);
         case BYTE:
            return new ByteLargeArray(length, zeroNativeMemory);
         case SHORT:
            return new ShortLargeArray(length, zeroNativeMemory);
         case INT:
            return new IntLargeArray(length, zeroNativeMemory);
         case LONG:
            return new LongLargeArray(length, zeroNativeMemory);
         case FLOAT:
            return new FloatLargeArray(length, zeroNativeMemory);
         case DOUBLE:
            return new DoubleLargeArray(length, zeroNativeMemory);
         case COMPLEX_FLOAT:
            return new ComplexFloatLargeArray(length, zeroNativeMemory);
         case COMPLEX_DOUBLE:
            return new ComplexDoubleLargeArray(length, zeroNativeMemory);
         case STRING:
            return new StringLargeArray(length, 100, zeroNativeMemory);
         case OBJECT:
            return new ObjectLargeArray(length, 100, zeroNativeMemory);
         case UNSIGNED_BYTE:
            return new UnsignedByteLargeArray(length, zeroNativeMemory);
         default:
            throw new IllegalArgumentException("Invalid array type.");
      }
   }

   public static LargeArray generateRandom(LargeArrayType type, long length) {
      LargeArray res = create(type, length, false);
      Random rand = new Random();
      switch (type) {
         case LOGIC:
            for(long i = 0L; i < length; ++i) {
               res.setBoolean(i, rand.nextBoolean());
            }
            break;
         case BYTE:
         case UNSIGNED_BYTE:
            long i;
            for(i = 0L; i < length / 4L; i += 4L) {
               int r = rand.nextInt();
               int var26;
               res.setByte(i, (byte)(var26 = r >> 8));
               res.setByte(i + 1L, (byte)(r = var26 >> 8));
               int var28;
               res.setByte(i + 2L, (byte)(var28 = r >> 8));
               res.setByte(i + 3L, (byte)(r = var28 >> 8));
            }

            for(int r = rand.nextInt(); i < length; ++i) {
               res.setByte(i, (byte)(r >>= 8));
            }
            break;
         case SHORT:
            long i;
            for(i = 0L; i < length / 2L; i += 2L) {
               int r = rand.nextInt();
               int i;
               res.setShort(i, (short)(i = r >> 16));
               res.setShort(i + 1L, (short)(r = i >> 16));
            }

            for(int r = rand.nextInt(); i < length; ++i) {
               res.setShort(i, (short)(r >>= 16));
            }
            break;
         case INT:
            for(long i = 0L; i < length; ++i) {
               res.setInt(i, rand.nextInt());
            }
            break;
         case LONG:
            for(long i = 0L; i < length; ++i) {
               res.setLong(i, rand.nextLong());
            }
            break;
         case FLOAT:
            for(long i = 0L; i < length; ++i) {
               res.setFloat(i, rand.nextFloat());
            }
            break;
         case DOUBLE:
            for(long i = 0L; i < length; ++i) {
               res.setDouble(i, rand.nextDouble());
            }
            break;
         case COMPLEX_FLOAT:
            ComplexFloatLargeArray res_c = (ComplexFloatLargeArray)res;
            float[] elem_res = new float[2];

            for(long i = 0L; i < length; ++i) {
               elem_res[0] = rand.nextFloat();
               elem_res[1] = rand.nextFloat();
               res_c.setComplexFloat(i, elem_res);
            }
            break;
         case COMPLEX_DOUBLE:
            ComplexDoubleLargeArray res_c = (ComplexDoubleLargeArray)res;
            double[] elem_res = new double[2];

            for(long i = 0L; i < length; ++i) {
               elem_res[0] = rand.nextDouble();
               elem_res[1] = rand.nextDouble();
               res_c.setComplexDouble(i, elem_res);
            }
            break;
         case STRING:
            for(long i = 0L; i < length; ++i) {
               res.setFloat(i, rand.nextFloat());
            }
            break;
         case OBJECT:
            for(long i = 0L; i < length; ++i) {
               res.set(i, rand.nextFloat());
            }
            break;
         default:
            throw new IllegalArgumentException("Invalid array type.");
      }

      return res;
   }

   public static LargeArray convert(final LargeArray src, final LargeArrayType type) {
      if (src.getType() == type) {
         return src;
      } else if (src.isConstant()) {
         switch (type) {
            case LOGIC:
               return new LogicLargeArray(src.length(), src.getByte(0L));
            case BYTE:
               return new ByteLargeArray(src.length(), src.getByte(0L));
            case SHORT:
               return new ShortLargeArray(src.length(), src.getShort(0L));
            case INT:
               return new IntLargeArray(src.length(), src.getInt(0L));
            case LONG:
               return new LongLargeArray(src.length(), src.getLong(0L));
            case FLOAT:
               return new FloatLargeArray(src.length(), src.getFloat(0L));
            case DOUBLE:
               return new DoubleLargeArray(src.length(), src.getDouble(0L));
            case COMPLEX_FLOAT:
               return new ComplexFloatLargeArray(src.length(), ((ComplexFloatLargeArray)src).getComplexFloat(0L));
            case COMPLEX_DOUBLE:
               return new ComplexDoubleLargeArray(src.length(), ((ComplexDoubleLargeArray)src).getComplexDouble(0L));
            case STRING:
               return new StringLargeArray(src.length(), src.get(0L).toString());
            case OBJECT:
               return new ObjectLargeArray(src.length(), src.get(0L));
            case UNSIGNED_BYTE:
               return new UnsignedByteLargeArray(src.length(), src.getUnsignedByte(0L));
            default:
               throw new IllegalArgumentException("Invalid array type.");
         }
      } else {
         long length = src.length;
         final LargeArray out = create(type, length, false);
         int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
         if (nthreads >= 2 && length >= ConcurrencyUtils.getConcurrentThreshold()) {
            long k = length / (long)nthreads;
            Future[] threads = new Future[nthreads];

            for(int j = 0; j < nthreads; ++j) {
               final long firstIdx = (long)j * k;
               final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
               threads[j] = ConcurrencyUtils.submit(new Runnable() {
                  public void run() {
                     switch (type) {
                        case BYTE:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.setByte(i, src.getByte(i));
                           }
                           break;
                        case SHORT:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.setShort(i, src.getShort(i));
                           }
                           break;
                        case INT:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.setInt(i, src.getInt(i));
                           }
                           break;
                        case LONG:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.setLong(i, src.getLong(i));
                           }
                           break;
                        case FLOAT:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.setFloat(i, src.getFloat(i));
                           }
                           break;
                        case DOUBLE:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.setDouble(i, src.getDouble(i));
                           }
                           break;
                        case COMPLEX_FLOAT:
                           if (src.getType() == LargeArrayType.COMPLEX_DOUBLE) {
                              for(long i = firstIdx; i < lastIdx; ++i) {
                                 ((ComplexFloatLargeArray)out).setComplexDouble(i, ((ComplexDoubleLargeArray)src).getComplexDouble(i));
                              }
                           } else {
                              for(long i = firstIdx; i < lastIdx; ++i) {
                                 out.setFloat(i, src.getFloat(i));
                              }
                           }
                           break;
                        case COMPLEX_DOUBLE:
                           if (src.getType() == LargeArrayType.COMPLEX_FLOAT) {
                              for(long i = firstIdx; i < lastIdx; ++i) {
                                 ((ComplexDoubleLargeArray)out).setComplexFloat(i, ((ComplexFloatLargeArray)src).getComplexFloat(i));
                              }
                           } else {
                              for(long i = firstIdx; i < lastIdx; ++i) {
                                 out.setDouble(i, src.getDouble(i));
                              }
                           }
                           break;
                        case STRING:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.set(i, src.get(i).toString());
                           }
                           break;
                        case OBJECT:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.set(i, src.get(i));
                           }
                           break;
                        case UNSIGNED_BYTE:
                           for(long i = firstIdx; i < lastIdx; ++i) {
                              out.setUnsignedByte(i, src.getUnsignedByte(i));
                           }
                           break;
                        default:
                           throw new IllegalArgumentException("Invalid array type.");
                     }

                  }
               });
            }

            try {
               ConcurrencyUtils.waitForCompletion(threads);
            } catch (InterruptedException var14) {
               switch (type) {
                  case LOGIC:
                  case BYTE:
                     for(long i = 0L; i < length; ++i) {
                        out.setByte(i, src.getByte(i));
                     }
                     break;
                  case SHORT:
                     for(long i = 0L; i < length; ++i) {
                        out.setShort(i, src.getShort(i));
                     }
                     break;
                  case INT:
                     for(long i = 0L; i < length; ++i) {
                        out.setInt(i, src.getInt(i));
                     }
                     break;
                  case LONG:
                     for(long i = 0L; i < length; ++i) {
                        out.setLong(i, src.getLong(i));
                     }
                     break;
                  case FLOAT:
                     for(long i = 0L; i < length; ++i) {
                        out.setFloat(i, src.getFloat(i));
                     }
                     break;
                  case DOUBLE:
                     for(long i = 0L; i < length; ++i) {
                        out.setDouble(i, src.getDouble(i));
                     }
                     break;
                  case COMPLEX_FLOAT:
                     if (src.getType() == LargeArrayType.COMPLEX_DOUBLE) {
                        for(long i = 0L; i < length; ++i) {
                           ((ComplexFloatLargeArray)out).setComplexDouble(i, ((ComplexDoubleLargeArray)src).getComplexDouble(i));
                        }
                     } else {
                        for(long i = 0L; i < length; ++i) {
                           out.setFloat(i, src.getFloat(i));
                        }
                     }
                     break;
                  case COMPLEX_DOUBLE:
                     if (src.getType() == LargeArrayType.COMPLEX_FLOAT) {
                        for(long i = 0L; i < length; ++i) {
                           ((ComplexDoubleLargeArray)out).setComplexFloat(i, ((ComplexFloatLargeArray)src).getComplexFloat(i));
                        }
                     } else {
                        for(long i = 0L; i < length; ++i) {
                           out.setDouble(i, src.getDouble(i));
                        }
                     }
                     break;
                  case STRING:
                     for(long i = 0L; i < length; ++i) {
                        out.set(i, src.get(i).toString());
                     }
                     break;
                  case OBJECT:
                     for(long i = 0L; i < length; ++i) {
                        out.set(i, src.get(i));
                     }
                     break;
                  case UNSIGNED_BYTE:
                     for(long i = 0L; i < length; ++i) {
                        out.setUnsignedByte(i, src.getUnsignedByte(i));
                     }
                     break;
                  default:
                     throw new IllegalArgumentException("Invalid array type.");
               }
            } catch (ExecutionException var15) {
               switch (type) {
                  case LOGIC:
                  case BYTE:
                     for(long i = 0L; i < length; ++i) {
                        out.setByte(i, src.getByte(i));
                     }
                     break;
                  case SHORT:
                     for(long i = 0L; i < length; ++i) {
                        out.setShort(i, src.getShort(i));
                     }
                     break;
                  case INT:
                     for(long i = 0L; i < length; ++i) {
                        out.setInt(i, src.getInt(i));
                     }
                     break;
                  case LONG:
                     for(long i = 0L; i < length; ++i) {
                        out.setLong(i, src.getLong(i));
                     }
                     break;
                  case FLOAT:
                     for(long i = 0L; i < length; ++i) {
                        out.setFloat(i, src.getFloat(i));
                     }
                     break;
                  case DOUBLE:
                     for(long i = 0L; i < length; ++i) {
                        out.setDouble(i, src.getDouble(i));
                     }
                     break;
                  case COMPLEX_FLOAT:
                     if (src.getType() == LargeArrayType.COMPLEX_DOUBLE) {
                        for(long i = 0L; i < length; ++i) {
                           ((ComplexFloatLargeArray)out).setComplexDouble(i, ((ComplexDoubleLargeArray)src).getComplexDouble(i));
                        }
                     } else {
                        for(long i = 0L; i < length; ++i) {
                           out.setFloat(i, src.getFloat(i));
                        }
                     }
                     break;
                  case COMPLEX_DOUBLE:
                     if (src.getType() == LargeArrayType.COMPLEX_FLOAT) {
                        for(long i = 0L; i < length; ++i) {
                           ((ComplexDoubleLargeArray)out).setComplexFloat(i, ((ComplexFloatLargeArray)src).getComplexFloat(i));
                        }
                     } else {
                        for(long i = 0L; i < length; ++i) {
                           out.setDouble(i, src.getDouble(i));
                        }
                     }
                     break;
                  case STRING:
                     for(long i = 0L; i < length; ++i) {
                        out.set(i, src.get(i).toString());
                     }
                     break;
                  case OBJECT:
                     for(long i = 0L; i < length; ++i) {
                        out.set(i, src.get(i));
                     }
                     break;
                  case UNSIGNED_BYTE:
                     for(long i = 0L; i < length; ++i) {
                        out.setUnsignedByte(i, src.getUnsignedByte(i));
                     }
                     break;
                  default:
                     throw new IllegalArgumentException("Invalid array type.");
               }
            }
         } else {
            switch (type) {
               case LOGIC:
               case BYTE:
                  for(long i = 0L; i < length; ++i) {
                     out.setByte(i, src.getByte(i));
                  }
                  break;
               case SHORT:
                  for(long i = 0L; i < length; ++i) {
                     out.setShort(i, src.getShort(i));
                  }
                  break;
               case INT:
                  for(long i = 0L; i < length; ++i) {
                     out.setInt(i, src.getInt(i));
                  }
                  break;
               case LONG:
                  for(long i = 0L; i < length; ++i) {
                     out.setLong(i, src.getLong(i));
                  }
                  break;
               case FLOAT:
                  for(long i = 0L; i < length; ++i) {
                     out.setFloat(i, src.getFloat(i));
                  }
                  break;
               case DOUBLE:
                  for(long i = 0L; i < length; ++i) {
                     out.setDouble(i, src.getDouble(i));
                  }
                  break;
               case COMPLEX_FLOAT:
                  if (src.getType() == LargeArrayType.COMPLEX_DOUBLE) {
                     for(long i = 0L; i < length; ++i) {
                        ((ComplexFloatLargeArray)out).setComplexDouble(i, ((ComplexDoubleLargeArray)src).getComplexDouble(i));
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        out.setFloat(i, src.getFloat(i));
                     }
                  }
                  break;
               case COMPLEX_DOUBLE:
                  if (src.getType() == LargeArrayType.COMPLEX_FLOAT) {
                     for(long i = 0L; i < length; ++i) {
                        ((ComplexDoubleLargeArray)out).setComplexFloat(i, ((ComplexFloatLargeArray)src).getComplexFloat(i));
                     }
                  } else {
                     for(long i = 0L; i < length; ++i) {
                        out.setDouble(i, src.getDouble(i));
                     }
                  }
                  break;
               case STRING:
                  for(long i = 0L; i < length; ++i) {
                     out.set(i, src.get(i).toString());
                  }
                  break;
               case OBJECT:
                  for(long i = 0L; i < length; ++i) {
                     out.set(i, src.get(i));
                  }
                  break;
               case UNSIGNED_BYTE:
                  for(long i = 0L; i < length; ++i) {
                     out.setUnsignedByte(i, src.getUnsignedByte(i));
                  }
                  break;
               default:
                  throw new IllegalArgumentException("Invalid array type.");
            }
         }

         return out;
      }
   }

   public static LargeArray select(LargeArray src, final LogicLargeArray mask) {
      if (src.length != mask.length) {
         throw new IllegalArgumentException("src.length != mask.length");
      } else {
         long length = src.length;
         long count = 0L;
         int nthreads = (int)FastMath.min(length, (long)ConcurrencyUtils.getNumberOfThreads());
         long k = length / (long)nthreads;
         ExecutorService pool = Executors.newCachedThreadPool();
         Future[] futures = new Future[nthreads];

         for(int j = 0; j < nthreads; ++j) {
            final long firstIdx = (long)j * k;
            final long lastIdx = j == nthreads - 1 ? length : firstIdx + k;
            futures[j] = pool.submit(new Callable() {
               public Long call() {
                  long count = 0L;

                  for(long k = firstIdx; k < lastIdx; ++k) {
                     if (mask.getByte(k) == 1) {
                        ++count;
                     }
                  }

                  return count;
               }
            });
         }

         try {
            for(int j = 0; j < nthreads; ++j) {
               count += (Long)((Long)futures[j].get());
            }
         } catch (Exception var16) {
            for(long j = 0L; j < length; ++j) {
               if (mask.getByte(j) == 1) {
                  ++count;
               }
            }
         }

         if (count <= 0L) {
            return null;
         } else {
            LargeArray res = create(src.getType(), count, false);
            k = 0L;

            for(long j = 0L; j < length; ++j) {
               if (mask.getByte(j) == 1) {
                  res.set(k++, src.get(j));
               }
            }

            return res;
         }
      }
   }

   static {
      Object theUnsafe = null;
      Exception exception = null;

      try {
         Class<?> uc = Class.forName("sun.misc.Unsafe");
         Field f = uc.getDeclaredField("theUnsafe");
         f.setAccessible(true);
         theUnsafe = f.get(uc);
      } catch (ClassNotFoundException e) {
         exception = e;
      } catch (IllegalAccessException e) {
         exception = e;
      } catch (IllegalArgumentException e) {
         exception = e;
      } catch (NoSuchFieldException e) {
         exception = e;
      } catch (SecurityException e) {
         exception = e;
      }

      UNSAFE = (Unsafe)theUnsafe;
      if (UNSAFE == null) {
         throw new Error("Could not obtain access to sun.misc.Unsafe", exception);
      }
   }
}
