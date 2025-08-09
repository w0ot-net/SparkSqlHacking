package org.apache.spark.util.collection;

import java.util.Comparator;

class TimSort {
   private static final int MIN_MERGE = 32;
   private final SortDataFormat s;

   public TimSort(SortDataFormat sortDataFormat) {
      this.s = sortDataFormat;
   }

   public void sort(Object a, int lo, int hi, Comparator c) {
      assert c != null;

      int nRemaining = hi - lo;
      if (nRemaining >= 2) {
         if (nRemaining < 32) {
            int initRunLen = this.countRunAndMakeAscending(a, lo, hi, c);
            this.binarySort(a, lo, hi, lo + initRunLen, c);
         } else {
            TimSort<K, Buffer>.SortState sortState = new SortState(a, c, hi - lo);
            int minRun = this.minRunLength(nRemaining);

            do {
               int runLen = this.countRunAndMakeAscending(a, lo, hi, c);
               if (runLen < minRun) {
                  int force = nRemaining <= minRun ? nRemaining : minRun;
                  this.binarySort(a, lo, lo + force, lo + runLen, c);
                  runLen = force;
               }

               sortState.pushRun(lo, runLen);
               sortState.mergeCollapse();
               lo += runLen;
               nRemaining -= runLen;
            } while(nRemaining != 0);

            assert lo == hi;

            sortState.mergeForceCollapse();

            assert sortState.stackSize == 1;

         }
      }
   }

   private void binarySort(Object a, int lo, int hi, int start, Comparator c) {
      assert lo <= start && start <= hi;

      if (start == lo) {
         ++start;
      }

      K key0 = (K)this.s.newKey();
      K key1 = (K)this.s.newKey();

      for(Buffer pivotStore = (Buffer)this.s.allocate(1); start < hi; ++start) {
         this.s.copyElement(a, start, pivotStore, 0);
         K pivot = (K)this.s.getKey(pivotStore, 0, key0);
         int left = lo;
         int right = start;

         assert lo <= start;

         while(left < right) {
            int mid = left + right >>> 1;
            if (c.compare(pivot, this.s.getKey(a, mid, key1)) < 0) {
               right = mid;
            } else {
               left = mid + 1;
            }
         }

         assert left == right;

         int n = start - left;
         switch (n) {
            case 2:
               this.s.copyElement(a, left + 1, a, left + 2);
            case 1:
               this.s.copyElement(a, left, a, left + 1);
               break;
            default:
               this.s.copyRange(a, left, a, left + 1, n);
         }

         this.s.copyElement(pivotStore, 0, a, left);
      }

   }

   private int countRunAndMakeAscending(Object a, int lo, int hi, Comparator c) {
      assert lo < hi;

      int runHi = lo + 1;
      if (runHi == hi) {
         return 1;
      } else {
         K key0 = (K)this.s.newKey();
         K key1 = (K)this.s.newKey();
         if (c.compare(this.s.getKey(a, runHi++, key0), this.s.getKey(a, lo, key1)) >= 0) {
            while(runHi < hi && c.compare(this.s.getKey(a, runHi, key0), this.s.getKey(a, runHi - 1, key1)) >= 0) {
               ++runHi;
            }
         } else {
            while(runHi < hi && c.compare(this.s.getKey(a, runHi, key0), this.s.getKey(a, runHi - 1, key1)) < 0) {
               ++runHi;
            }

            this.reverseRange(a, lo, runHi);
         }

         return runHi - lo;
      }
   }

   private void reverseRange(Object a, int lo, int hi) {
      --hi;

      while(lo < hi) {
         this.s.swap(a, lo, hi);
         ++lo;
         --hi;
      }

   }

   private int minRunLength(int n) {
      assert n >= 0;

      int r;
      for(r = 0; n >= 32; n >>= 1) {
         r |= n & 1;
      }

      return n + r;
   }

   private class SortState {
      private final Object a;
      private final int aLength;
      private final Comparator c;
      private static final int MIN_GALLOP = 7;
      private int minGallop = 7;
      private static final int INITIAL_TMP_STORAGE_LENGTH = 256;
      private Object tmp;
      private int tmpLength = 0;
      private int stackSize = 0;
      private final int[] runBase;
      private final int[] runLen;

      private SortState(Object a, Comparator c, int len) {
         this.aLength = len;
         this.a = a;
         this.c = c;
         this.tmpLength = len < 512 ? len >>> 1 : 256;
         this.tmp = TimSort.this.s.allocate(this.tmpLength);
         int stackLen = len < 120 ? 5 : (len < 1542 ? 10 : (len < 119151 ? 24 : 49));
         this.runBase = new int[stackLen];
         this.runLen = new int[stackLen];
      }

      private void pushRun(int runBase, int runLen) {
         this.runBase[this.stackSize] = runBase;
         this.runLen[this.stackSize] = runLen;
         ++this.stackSize;
      }

      private void mergeCollapse() {
         while(true) {
            int n;
            label38: {
               if (this.stackSize > 1) {
                  n = this.stackSize - 2;
                  if (n > 0 && this.runLen[n - 1] <= this.runLen[n] + this.runLen[n + 1] || n > 1 && this.runLen[n - 2] <= this.runLen[n] + this.runLen[n - 1]) {
                     if (this.runLen[n - 1] < this.runLen[n + 1]) {
                        --n;
                     }
                     break label38;
                  }

                  if (n >= 0 && this.runLen[n] <= this.runLen[n + 1]) {
                     break label38;
                  }
               }

               return;
            }

            this.mergeAt(n);
         }
      }

      private void mergeForceCollapse() {
         int n;
         for(; this.stackSize > 1; this.mergeAt(n)) {
            n = this.stackSize - 2;
            if (n > 0 && this.runLen[n - 1] < this.runLen[n + 1]) {
               --n;
            }
         }

      }

      private void mergeAt(int i) {
         assert this.stackSize >= 2;

         assert i >= 0;

         assert i == this.stackSize - 2 || i == this.stackSize - 3;

         int base1 = this.runBase[i];
         int len1 = this.runLen[i];
         int base2 = this.runBase[i + 1];
         int len2 = this.runLen[i + 1];

         assert len1 > 0 && len2 > 0;

         assert base1 + len1 == base2;

         this.runLen[i] = len1 + len2;
         if (i == this.stackSize - 3) {
            this.runBase[i + 1] = this.runBase[i + 2];
            this.runLen[i + 1] = this.runLen[i + 2];
         }

         --this.stackSize;
         K key0 = (K)TimSort.this.s.newKey();
         int k = this.gallopRight(TimSort.this.s.getKey(this.a, base2, key0), this.a, base1, len1, 0, this.c);

         assert k >= 0;

         base1 += k;
         len1 -= k;
         if (len1 != 0) {
            len2 = this.gallopLeft(TimSort.this.s.getKey(this.a, base1 + len1 - 1, key0), this.a, base2, len2, len2 - 1, this.c);

            assert len2 >= 0;

            if (len2 != 0) {
               if (len1 <= len2) {
                  this.mergeLo(base1, len1, base2, len2);
               } else {
                  this.mergeHi(base1, len1, base2, len2);
               }

            }
         }
      }

      private int gallopLeft(Object key, Object a, int base, int len, int hint, Comparator c) {
         assert len > 0 && hint >= 0 && hint < len;

         int lastOfs = 0;
         int ofs = 1;
         K key0 = (K)TimSort.this.s.newKey();
         if (c.compare(key, TimSort.this.s.getKey(a, base + hint, key0)) > 0) {
            int maxOfs = len - hint;

            while(ofs < maxOfs && c.compare(key, TimSort.this.s.getKey(a, base + hint + ofs, key0)) > 0) {
               lastOfs = ofs;
               ofs = (ofs << 1) + 1;
               if (ofs <= 0) {
                  ofs = maxOfs;
               }
            }

            if (ofs > maxOfs) {
               ofs = maxOfs;
            }

            lastOfs += hint;
            ofs += hint;
         } else {
            int maxOfs = hint + 1;

            while(ofs < maxOfs && c.compare(key, TimSort.this.s.getKey(a, base + hint - ofs, key0)) <= 0) {
               lastOfs = ofs;
               ofs = (ofs << 1) + 1;
               if (ofs <= 0) {
                  ofs = maxOfs;
               }
            }

            if (ofs > maxOfs) {
               ofs = maxOfs;
            }

            lastOfs = hint - ofs;
            ofs = hint - lastOfs;
         }

         assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

         ++lastOfs;

         while(lastOfs < ofs) {
            int m = lastOfs + (ofs - lastOfs >>> 1);
            if (c.compare(key, TimSort.this.s.getKey(a, base + m, key0)) > 0) {
               lastOfs = m + 1;
            } else {
               ofs = m;
            }
         }

         assert lastOfs == ofs;

         return ofs;
      }

      private int gallopRight(Object key, Object a, int base, int len, int hint, Comparator c) {
         assert len > 0 && hint >= 0 && hint < len;

         int ofs = 1;
         int lastOfs = 0;
         K key1 = (K)TimSort.this.s.newKey();
         if (c.compare(key, TimSort.this.s.getKey(a, base + hint, key1)) < 0) {
            int maxOfs = hint + 1;

            while(ofs < maxOfs && c.compare(key, TimSort.this.s.getKey(a, base + hint - ofs, key1)) < 0) {
               lastOfs = ofs;
               ofs = (ofs << 1) + 1;
               if (ofs <= 0) {
                  ofs = maxOfs;
               }
            }

            if (ofs > maxOfs) {
               ofs = maxOfs;
            }

            lastOfs = hint - ofs;
            ofs = hint - lastOfs;
         } else {
            int maxOfs = len - hint;

            while(ofs < maxOfs && c.compare(key, TimSort.this.s.getKey(a, base + hint + ofs, key1)) >= 0) {
               lastOfs = ofs;
               ofs = (ofs << 1) + 1;
               if (ofs <= 0) {
                  ofs = maxOfs;
               }
            }

            if (ofs > maxOfs) {
               ofs = maxOfs;
            }

            lastOfs += hint;
            ofs += hint;
         }

         assert -1 <= lastOfs && lastOfs < ofs && ofs <= len;

         ++lastOfs;

         while(lastOfs < ofs) {
            int m = lastOfs + (ofs - lastOfs >>> 1);
            if (c.compare(key, TimSort.this.s.getKey(a, base + m, key1)) < 0) {
               ofs = m;
            } else {
               lastOfs = m + 1;
            }
         }

         assert lastOfs == ofs;

         return ofs;
      }

      private void mergeLo(int base1, int len1, int base2, int len2) {
         assert len1 > 0 && len2 > 0 && base1 + len1 == base2;

         Buffer a = (Buffer)this.a;
         Buffer tmp = (Buffer)this.ensureCapacity(len1);
         TimSort.this.s.copyRange(a, base1, tmp, 0, len1);
         int cursor1 = 0;
         int cursor2 = base2 + 1;
         int dest = base1 + 1;
         TimSort.this.s.copyElement(a, base2, a, base1);
         --len2;
         if (len2 == 0) {
            TimSort.this.s.copyRange(tmp, cursor1, a, dest, len1);
         } else if (len1 == 1) {
            TimSort.this.s.copyRange(a, cursor2, a, dest, len2);
            TimSort.this.s.copyElement(tmp, cursor1, a, dest + len2);
         } else {
            K key0 = (K)TimSort.this.s.newKey();
            K key1 = (K)TimSort.this.s.newKey();
            Comparator<? super K> c = this.c;
            int minGallop = this.minGallop;

            label132:
            while(true) {
               int count1 = 0;
               int count2 = 0;

               while($assertionsDisabled || len1 > 1 && len2 > 0) {
                  label148: {
                     if (c.compare(TimSort.this.s.getKey(a, cursor2, key0), TimSort.this.s.getKey(tmp, cursor1, key1)) < 0) {
                        TimSort.this.s.copyElement(a, cursor2++, a, dest++);
                        ++count2;
                        count1 = 0;
                        --len2;
                        if (len2 == 0) {
                           break label148;
                        }
                     } else {
                        TimSort.this.s.copyElement(tmp, cursor1++, a, dest++);
                        ++count1;
                        count2 = 0;
                        --len1;
                        if (len1 == 1) {
                           break label148;
                        }
                     }

                     if ((count1 | count2) < minGallop) {
                        continue;
                     }

                     while(true) {
                        assert len1 > 1 && len2 > 0;

                        count1 = this.gallopRight(TimSort.this.s.getKey(a, cursor2, key0), tmp, cursor1, len1, 0, c);
                        if (count1 != 0) {
                           TimSort.this.s.copyRange(tmp, cursor1, a, dest, count1);
                           dest += count1;
                           cursor1 += count1;
                           len1 -= count1;
                           if (len1 <= 1) {
                              break;
                           }
                        }

                        TimSort.this.s.copyElement(a, cursor2++, a, dest++);
                        --len2;
                        if (len2 == 0) {
                           break;
                        }

                        count2 = this.gallopLeft(TimSort.this.s.getKey(tmp, cursor1, key0), a, cursor2, len2, 0, c);
                        if (count2 != 0) {
                           TimSort.this.s.copyRange(a, cursor2, a, dest, count2);
                           dest += count2;
                           cursor2 += count2;
                           len2 -= count2;
                           if (len2 == 0) {
                              break;
                           }
                        }

                        TimSort.this.s.copyElement(tmp, cursor1++, a, dest++);
                        --len1;
                        if (len1 == 1) {
                           break;
                        }

                        --minGallop;
                        if (!(count1 >= 7 | count2 >= 7)) {
                           if (minGallop < 0) {
                              minGallop = 0;
                           }

                           minGallop += 2;
                           continue label132;
                        }
                     }
                  }

                  this.minGallop = minGallop < 1 ? 1 : minGallop;
                  if (len1 == 1) {
                     assert len2 > 0;

                     TimSort.this.s.copyRange(a, cursor2, a, dest, len2);
                     TimSort.this.s.copyElement(tmp, cursor1, a, dest + len2);
                  } else {
                     if (len1 == 0) {
                        throw new IllegalArgumentException("Comparison method violates its general contract!");
                     }

                     assert len2 == 0;

                     assert len1 > 1;

                     TimSort.this.s.copyRange(tmp, cursor1, a, dest, len1);
                  }

                  return;
               }

               throw new AssertionError();
            }
         }
      }

      private void mergeHi(int base1, int len1, int base2, int len2) {
         assert len1 > 0 && len2 > 0 && base1 + len1 == base2;

         Buffer a = (Buffer)this.a;
         Buffer tmp = (Buffer)this.ensureCapacity(len2);
         TimSort.this.s.copyRange(a, base2, tmp, 0, len2);
         int cursor1 = base1 + len1 - 1;
         int cursor2 = len2 - 1;
         int dest = base2 + len2 - 1;
         K key0 = (K)TimSort.this.s.newKey();
         K key1 = (K)TimSort.this.s.newKey();
         TimSort.this.s.copyElement(a, cursor1--, a, dest--);
         --len1;
         if (len1 == 0) {
            TimSort.this.s.copyRange(tmp, 0, a, dest - (len2 - 1), len2);
         } else if (len2 == 1) {
            dest -= len1;
            cursor1 -= len1;
            TimSort.this.s.copyRange(a, cursor1 + 1, a, dest + 1, len1);
            TimSort.this.s.copyElement(tmp, cursor2, a, dest);
         } else {
            Comparator<? super K> c = this.c;
            int minGallop = this.minGallop;

            label132:
            while(true) {
               int count1 = 0;
               int count2 = 0;

               while($assertionsDisabled || len1 > 0 && len2 > 1) {
                  label148: {
                     if (c.compare(TimSort.this.s.getKey(tmp, cursor2, key0), TimSort.this.s.getKey(a, cursor1, key1)) < 0) {
                        TimSort.this.s.copyElement(a, cursor1--, a, dest--);
                        ++count1;
                        count2 = 0;
                        --len1;
                        if (len1 == 0) {
                           break label148;
                        }
                     } else {
                        TimSort.this.s.copyElement(tmp, cursor2--, a, dest--);
                        ++count2;
                        count1 = 0;
                        --len2;
                        if (len2 == 1) {
                           break label148;
                        }
                     }

                     if ((count1 | count2) < minGallop) {
                        continue;
                     }

                     while(true) {
                        assert len1 > 0 && len2 > 1;

                        count1 = len1 - this.gallopRight(TimSort.this.s.getKey(tmp, cursor2, key0), a, base1, len1, len1 - 1, c);
                        if (count1 != 0) {
                           dest -= count1;
                           cursor1 -= count1;
                           len1 -= count1;
                           TimSort.this.s.copyRange(a, cursor1 + 1, a, dest + 1, count1);
                           if (len1 == 0) {
                              break;
                           }
                        }

                        TimSort.this.s.copyElement(tmp, cursor2--, a, dest--);
                        --len2;
                        if (len2 == 1) {
                           break;
                        }

                        count2 = len2 - this.gallopLeft(TimSort.this.s.getKey(a, cursor1, key0), tmp, 0, len2, len2 - 1, c);
                        if (count2 != 0) {
                           dest -= count2;
                           cursor2 -= count2;
                           len2 -= count2;
                           TimSort.this.s.copyRange(tmp, cursor2 + 1, a, dest + 1, count2);
                           if (len2 <= 1) {
                              break;
                           }
                        }

                        TimSort.this.s.copyElement(a, cursor1--, a, dest--);
                        --len1;
                        if (len1 == 0) {
                           break;
                        }

                        --minGallop;
                        if (!(count1 >= 7 | count2 >= 7)) {
                           if (minGallop < 0) {
                              minGallop = 0;
                           }

                           minGallop += 2;
                           continue label132;
                        }
                     }
                  }

                  this.minGallop = minGallop < 1 ? 1 : minGallop;
                  if (len2 == 1) {
                     assert len1 > 0;

                     dest -= len1;
                     cursor1 -= len1;
                     TimSort.this.s.copyRange(a, cursor1 + 1, a, dest + 1, len1);
                     TimSort.this.s.copyElement(tmp, cursor2, a, dest);
                  } else {
                     if (len2 == 0) {
                        throw new IllegalArgumentException("Comparison method violates its general contract!");
                     }

                     assert len1 == 0;

                     assert len2 > 0;

                     TimSort.this.s.copyRange(tmp, 0, a, dest - (len2 - 1), len2);
                  }

                  return;
               }

               throw new AssertionError();
            }
         }
      }

      private Object ensureCapacity(int minCapacity) {
         if (this.tmpLength < minCapacity) {
            int newSize = minCapacity | minCapacity >> 1;
            newSize |= newSize >> 2;
            newSize |= newSize >> 4;
            newSize |= newSize >> 8;
            newSize |= newSize >> 16;
            ++newSize;
            if (newSize < 0) {
               newSize = minCapacity;
            } else {
               newSize = Math.min(newSize, this.aLength >>> 1);
            }

            this.tmp = TimSort.this.s.allocate(newSize);
            this.tmpLength = newSize;
         }

         return this.tmp;
      }
   }
}
