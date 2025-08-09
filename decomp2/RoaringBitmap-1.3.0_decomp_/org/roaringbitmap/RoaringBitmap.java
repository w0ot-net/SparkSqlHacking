package org.roaringbitmap;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;
import org.roaringbitmap.buffer.MappeableContainerPointer;
import org.roaringbitmap.buffer.MutableRoaringBitmap;
import org.roaringbitmap.longlong.LongUtils;

public class RoaringBitmap implements Cloneable, Serializable, Iterable, Externalizable, ImmutableBitmapDataProvider, BitmapDataProvider, AppendableStorage {
   private static final long serialVersionUID = 6L;
   RoaringArray highLowContainer = null;

   private static void rangeSanityCheck(long rangeStart, long rangeEnd) {
      if (rangeStart >= 0L && rangeStart <= 4294967295L) {
         if (rangeEnd > 4294967296L || rangeEnd < 0L) {
            throw new IllegalArgumentException("rangeEnd=" + rangeEnd + " should be in [0, 0xffffffff + 1]");
         }
      } else {
         throw new IllegalArgumentException("rangeStart=" + rangeStart + " should be in [0, 0xffffffff]");
      }
   }

   public static RoaringBitmap addOffset(RoaringBitmap x, long offset) {
      long container_offset_long = offset < 0L ? (offset - 65536L + 1L) / 65536L : offset / 65536L;
      if (container_offset_long >= -65536L && container_offset_long < 65536L) {
         int container_offset = (int)container_offset_long;
         int in_container_offset = (int)(offset - container_offset_long * 65536L);
         if (in_container_offset == 0) {
            RoaringBitmap answer = new RoaringBitmap();

            for(int pos = 0; pos < x.highLowContainer.size(); ++pos) {
               int key = x.highLowContainer.getKeyAtIndex(pos);
               key += container_offset;
               answer.highLowContainer.append((char)key, x.highLowContainer.getContainerAtIndex(pos).clone());
            }

            return answer;
         } else {
            RoaringBitmap answer = new RoaringBitmap();

            for(int pos = 0; pos < x.highLowContainer.size(); ++pos) {
               int key = x.highLowContainer.getKeyAtIndex(pos);
               key += container_offset;
               if (key + 1 >= 0 && key <= 65535) {
                  Container c = x.highLowContainer.getContainerAtIndex(pos);
                  Container[] offsetted = Util.addOffset(c, (char)in_container_offset);
                  boolean keyok = key >= 0;
                  boolean keypok = key + 1 <= 65535;
                  if (!offsetted[0].isEmpty() && keyok) {
                     int current_size = answer.highLowContainer.size();
                     int lastkey = 0;
                     if (current_size > 0) {
                        lastkey = answer.highLowContainer.getKeyAtIndex(current_size - 1);
                     }

                     if (current_size > 0 && lastkey == key) {
                        Container prev = answer.highLowContainer.getContainerAtIndex(current_size - 1);
                        Container orresult = prev.ior(offsetted[0]);
                        answer.highLowContainer.setContainerAtIndex(current_size - 1, orresult);
                     } else {
                        answer.highLowContainer.append((char)key, offsetted[0]);
                     }
                  }

                  if (!offsetted[1].isEmpty() && keypok) {
                     answer.highLowContainer.append((char)(key + 1), offsetted[1]);
                  }
               }
            }

            answer.repairAfterLazy();
            return answer;
         }
      } else {
         return new RoaringBitmap();
      }
   }

   public static RoaringBitmap add(RoaringBitmap rb, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart >= rangeEnd) {
         return rb.clone();
      } else {
         int hbStart = Util.highbits(rangeStart);
         int lbStart = Util.lowbits(rangeStart);
         int hbLast = Util.highbits(rangeEnd - 1L);
         int lbLast = Util.lowbits(rangeEnd - 1L);
         RoaringBitmap answer = new RoaringBitmap();
         answer.highLowContainer.appendCopiesUntil(rb.highLowContainer, (char)hbStart);
         if (hbStart == hbLast) {
            int i = rb.highLowContainer.getIndex((char)hbStart);
            Container c = i >= 0 ? rb.highLowContainer.getContainerAtIndex(i).add(lbStart, lbLast + 1) : Container.rangeOfOnes(lbStart, lbLast + 1);
            answer.highLowContainer.append((char)hbStart, c);
            answer.highLowContainer.appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         } else {
            int ifirst = rb.highLowContainer.getIndex((char)hbStart);
            int ilast = rb.highLowContainer.getIndex((char)hbLast);
            Container c = ifirst >= 0 ? rb.highLowContainer.getContainerAtIndex(ifirst).add(lbStart, Util.maxLowBitAsInteger() + 1) : Container.rangeOfOnes(lbStart, Util.maxLowBitAsInteger() + 1);
            answer.highLowContainer.append((char)hbStart, c);

            for(int hb = hbStart + 1; hb < hbLast; ++hb) {
               Container c = Container.rangeOfOnes(0, Util.maxLowBitAsInteger() + 1);
               answer.highLowContainer.append((char)hb, c);
            }

            c = ilast >= 0 ? rb.highLowContainer.getContainerAtIndex(ilast).add(0, lbLast + 1) : Container.rangeOfOnes(0, lbLast + 1);
            answer.highLowContainer.append((char)hbLast, c);
            answer.highLowContainer.appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap add(RoaringBitmap rb, int rangeStart, int rangeEnd) {
      return rangeStart >= 0 ? add(rb, (long)rangeStart, (long)rangeEnd) : add(rb, (long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   public static RoaringBitmap and(RoaringBitmap x1, RoaringBitmap x2) {
      RoaringBitmap answer = new RoaringBitmap();
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      int pos1 = 0;
      int pos2 = 0;

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            Container c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            Container c = c1.and(c2);
            if (!c.isEmpty()) {
               answer.highLowContainer.append(s1, c);
            }

            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            pos1 = x1.highLowContainer.advanceUntil(s2, pos1);
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      return answer;
   }

   public static int andCardinality(RoaringBitmap x1, RoaringBitmap x2) {
      int answer = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      int pos1 = 0;
      int pos2 = 0;

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            Container c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            answer += c1.andCardinality(c2);
            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            pos1 = x1.highLowContainer.advanceUntil(s2, pos1);
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      return answer;
   }

   public static RoaringBitmap andNot(RoaringBitmap x1, RoaringBitmap x2) {
      RoaringBitmap answer = new RoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            Container c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            Container c = c1.andNot(c2);
            if (!c.isEmpty()) {
               answer.highLowContainer.append(s1, c);
            }

            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            int nextPos1 = x1.highLowContainer.advanceUntil(s2, pos1);
            answer.highLowContainer.appendCopy(x1.highLowContainer, pos1, nextPos1);
            pos1 = nextPos1;
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      if (pos2 == length2) {
         answer.highLowContainer.appendCopy(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   public void add(int... dat) {
      this.addN(dat, 0, dat.length);
   }

   public void addN(int[] dat, int offset, int n) {
      if (n >= 0 && offset >= 0) {
         if (n != 0) {
            if (offset + n > dat.length) {
               throw new IllegalArgumentException("Data source is too small.");
            } else {
               Container currentcont = null;
               int j = 0;
               int val = dat[j + offset];
               char currenthb = Util.highbits(val);
               int currentcontainerindex = this.highLowContainer.getIndex(currenthb);
               if (currentcontainerindex >= 0) {
                  currentcont = this.highLowContainer.getContainerAtIndex(currentcontainerindex);
                  Container newcont = currentcont.add(Util.lowbits(val));
                  if (newcont != currentcont) {
                     this.highLowContainer.setContainerAtIndex(currentcontainerindex, newcont);
                     currentcont = newcont;
                  }
               } else {
                  currentcontainerindex = -currentcontainerindex - 1;
                  ArrayContainer newac = new ArrayContainer();
                  currentcont = newac.add(Util.lowbits(val));
                  this.highLowContainer.insertNewKeyValueAt(currentcontainerindex, currenthb, currentcont);
               }

               ++j;

               for(; j < n; ++j) {
                  val = dat[j + offset];
                  char newhb = Util.highbits(val);
                  if (currenthb == newhb) {
                     Container newcont = currentcont.add(Util.lowbits(val));
                     if (newcont != currentcont) {
                        this.highLowContainer.setContainerAtIndex(currentcontainerindex, newcont);
                        currentcont = newcont;
                     }
                  } else {
                     currenthb = newhb;
                     currentcontainerindex = this.highLowContainer.getIndex(newhb);
                     if (currentcontainerindex >= 0) {
                        currentcont = this.highLowContainer.getContainerAtIndex(currentcontainerindex);
                        Container newcont = currentcont.add(Util.lowbits(val));
                        if (newcont != currentcont) {
                           this.highLowContainer.setContainerAtIndex(currentcontainerindex, newcont);
                           currentcont = newcont;
                        }
                     } else {
                        currentcontainerindex = -currentcontainerindex - 1;
                        ArrayContainer newac = new ArrayContainer();
                        currentcont = newac.add(Util.lowbits(val));
                        this.highLowContainer.insertNewKeyValueAt(currentcontainerindex, newhb, currentcont);
                     }
                  }
               }

            }
         }
      } else {
         throw new IllegalArgumentException("Negative values do not make sense.");
      }
   }

   public static RoaringBitmap bitmapOf(int... dat) {
      RoaringBitmap ans = new RoaringBitmap();
      ans.add(dat);
      return ans;
   }

   public static RoaringBitmap bitmapOfUnordered(int... data) {
      RoaringBitmapWriter<RoaringBitmap> writer = RoaringBitmapWriter.writer().constantMemory().doPartialRadixSort().get();
      writer.addMany(data);
      writer.flush();
      return (RoaringBitmap)writer.getUnderlying();
   }

   public static RoaringBitmap bitmapOfRange(long min, long max) {
      rangeSanityCheck(min, max);
      if (min >= max) {
         return new RoaringBitmap();
      } else {
         int hbStart = Util.highbits(min);
         int lbStart = Util.lowbits(min);
         int hbLast = Util.highbits(max - 1L);
         int lbLast = Util.lowbits(max - 1L);
         RoaringArray array = new RoaringArray(hbLast - hbStart + 1);
         RoaringBitmap bitmap = new RoaringBitmap(array);
         int firstEnd = hbStart < hbLast ? 65536 : lbLast + 1;
         Container firstContainer = RunContainer.rangeOfOnes(lbStart, firstEnd);
         bitmap.append((char)hbStart, firstContainer);
         if (hbStart < hbLast) {
            for(int i = hbStart + 1; i < hbLast; ++i) {
               Container runContainer = RunContainer.rangeOfOnes(0, 65536);
               bitmap.append((char)i, runContainer);
            }

            Container lastContainer = RunContainer.rangeOfOnes(0, lbLast + 1);
            bitmap.append((char)hbLast, lastContainer);
         }

         return bitmap;
      }
   }

   public static RoaringBitmap flip(RoaringBitmap bm, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart >= rangeEnd) {
         return bm.clone();
      } else {
         RoaringBitmap answer = new RoaringBitmap();
         int hbStart = Util.highbits(rangeStart);
         int lbStart = Util.lowbits(rangeStart);
         int hbLast = Util.highbits(rangeEnd - 1L);
         int lbLast = Util.lowbits(rangeEnd - 1L);
         answer.highLowContainer.appendCopiesUntil(bm.highLowContainer, (char)hbStart);

         for(int hb = hbStart; hb <= hbLast; ++hb) {
            int containerStart = hb == hbStart ? lbStart : 0;
            int containerLast = hb == hbLast ? lbLast : Util.maxLowBitAsInteger();
            int i = bm.highLowContainer.getIndex((char)hb);
            int j = answer.highLowContainer.getIndex((char)hb);

            assert j < 0;

            if (i >= 0) {
               Container c = bm.highLowContainer.getContainerAtIndex(i).not(containerStart, containerLast + 1);
               if (!c.isEmpty()) {
                  answer.highLowContainer.insertNewKeyValueAt(-j - 1, (char)hb, c);
               }
            } else {
               answer.highLowContainer.insertNewKeyValueAt(-j - 1, (char)hb, Container.rangeOfOnes(containerStart, containerLast + 1));
            }
         }

         answer.highLowContainer.appendCopiesAfter(bm.highLowContainer, (char)hbLast);
         return answer;
      }
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap flip(RoaringBitmap rb, int rangeStart, int rangeEnd) {
      return rangeStart >= 0 ? flip(rb, (long)rangeStart, (long)rangeEnd) : flip(rb, (long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   public static boolean intersects(RoaringBitmap x1, RoaringBitmap x2) {
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      int pos1 = 0;
      int pos2 = 0;

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            Container c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            if (c1.intersects(c2)) {
               return true;
            }

            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            pos1 = x1.highLowContainer.advanceUntil(s2, pos1);
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      return false;
   }

   protected static RoaringBitmap lazyor(RoaringBitmap x1, RoaringBitmap x2) {
      RoaringBitmap answer = new RoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      if (pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

         label42:
         while(true) {
            while(s1 != s2) {
               if (s1 < s2) {
                  answer.highLowContainer.appendCopy(x1.highLowContainer, pos1);
                  ++pos1;
                  if (pos1 == length1) {
                     break label42;
                  }

                  s1 = x1.highLowContainer.getKeyAtIndex(pos1);
               } else {
                  answer.highLowContainer.appendCopy(x2.highLowContainer, pos2);
                  ++pos2;
                  if (pos2 == length2) {
                     break label42;
                  }

                  s2 = x2.highLowContainer.getKeyAtIndex(pos2);
               }
            }

            answer.highLowContainer.append(s1, x1.highLowContainer.getContainerAtIndex(pos1).lazyOR(x2.highLowContainer.getContainerAtIndex(pos2)));
            ++pos1;
            ++pos2;
            if (pos1 == length1 || pos2 == length2) {
               break;
            }

            s1 = x1.highLowContainer.getKeyAtIndex(pos1);
            s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         }
      }

      if (pos1 == length1) {
         answer.highLowContainer.appendCopy(x2.highLowContainer, pos2, length2);
      } else if (pos2 == length2) {
         answer.highLowContainer.appendCopy(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   protected static RoaringBitmap lazyorfromlazyinputs(RoaringBitmap x1, RoaringBitmap x2) {
      RoaringBitmap answer = new RoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      if (pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

         label48:
         while(true) {
            while(s1 != s2) {
               if (s1 < s2) {
                  Container c1 = x1.highLowContainer.getContainerAtIndex(pos1);
                  answer.highLowContainer.append(s1, c1);
                  ++pos1;
                  if (pos1 == length1) {
                     break label48;
                  }

                  s1 = x1.highLowContainer.getKeyAtIndex(pos1);
               } else {
                  Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
                  answer.highLowContainer.append(s2, c2);
                  ++pos2;
                  if (pos2 == length2) {
                     break label48;
                  }

                  s2 = x2.highLowContainer.getKeyAtIndex(pos2);
               }
            }

            Container c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            if (c2 instanceof BitmapContainer && !(c1 instanceof BitmapContainer)) {
               Container tmp = c1;
               c1 = c2;
               c2 = tmp;
            }

            answer.highLowContainer.append(s1, c1.lazyIOR(c2));
            ++pos1;
            ++pos2;
            if (pos1 == length1 || pos2 == length2) {
               break;
            }

            s1 = x1.highLowContainer.getKeyAtIndex(pos1);
            s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         }
      }

      if (pos1 == length1) {
         answer.highLowContainer.append(x2.highLowContainer, pos2, length2);
      } else if (pos2 == length2) {
         answer.highLowContainer.append(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   public static RoaringBitmap or(Iterator bitmaps) {
      return FastAggregation.or(bitmaps);
   }

   public static RoaringBitmap or(RoaringBitmap... bitmaps) {
      return FastAggregation.or(bitmaps);
   }

   public static RoaringBitmap or(RoaringBitmap x1, RoaringBitmap x2) {
      RoaringBitmap answer = new RoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      if (pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

         label42:
         while(true) {
            while(s1 != s2) {
               if (s1 < s2) {
                  answer.highLowContainer.appendCopy(x1.highLowContainer, pos1);
                  ++pos1;
                  if (pos1 == length1) {
                     break label42;
                  }

                  s1 = x1.highLowContainer.getKeyAtIndex(pos1);
               } else {
                  answer.highLowContainer.appendCopy(x2.highLowContainer, pos2);
                  ++pos2;
                  if (pos2 == length2) {
                     break label42;
                  }

                  s2 = x2.highLowContainer.getKeyAtIndex(pos2);
               }
            }

            answer.highLowContainer.append(s1, x1.highLowContainer.getContainerAtIndex(pos1).or(x2.highLowContainer.getContainerAtIndex(pos2)));
            ++pos1;
            ++pos2;
            if (pos1 == length1 || pos2 == length2) {
               break;
            }

            s1 = x1.highLowContainer.getKeyAtIndex(pos1);
            s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         }
      }

      if (pos1 == length1) {
         answer.highLowContainer.appendCopy(x2.highLowContainer, pos2, length2);
      } else if (pos2 == length2) {
         answer.highLowContainer.appendCopy(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   public static int orCardinality(RoaringBitmap x1, RoaringBitmap x2) {
      return x1.getCardinality() + x2.getCardinality() - andCardinality(x1, x2);
   }

   public static int xorCardinality(RoaringBitmap x1, RoaringBitmap x2) {
      return x1.getCardinality() + x2.getCardinality() - 2 * andCardinality(x1, x2);
   }

   public static int andNotCardinality(RoaringBitmap x1, RoaringBitmap x2) {
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      if (length2 > 4 * length1) {
         return x1.getCardinality() - andCardinality(x1, x2);
      } else {
         long cardinality = 0L;
         int pos1 = 0;
         int pos2 = 0;

         while(pos1 < length1 && pos2 < length2) {
            char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
            char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
            if (s1 == s2) {
               Container c1 = x1.highLowContainer.getContainerAtIndex(pos1);
               Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
               cardinality += (long)(c1.getCardinality() - c1.andCardinality(c2));
               ++pos1;
               ++pos2;
            } else if (s1 < s2) {
               while(s1 < s2 && pos1 < length1) {
                  cardinality += (long)x1.highLowContainer.getContainerAtIndex(pos1).getCardinality();
                  ++pos1;
                  if (pos1 == length1) {
                     break;
                  }

                  s1 = x1.highLowContainer.getKeyAtIndex(pos1);
               }
            } else {
               pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
            }
         }

         if (pos2 == length2) {
            while(pos1 < length1) {
               cardinality += (long)x1.highLowContainer.getContainerAtIndex(pos1).getCardinality();
               ++pos1;
            }
         }

         return (int)cardinality;
      }
   }

   public static RoaringBitmap remove(RoaringBitmap rb, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart >= rangeEnd) {
         return rb.clone();
      } else {
         int hbStart = Util.highbits(rangeStart);
         int lbStart = Util.lowbits(rangeStart);
         int hbLast = Util.highbits(rangeEnd - 1L);
         int lbLast = Util.lowbits(rangeEnd - 1L);
         RoaringBitmap answer = new RoaringBitmap();
         answer.highLowContainer.appendCopiesUntil(rb.highLowContainer, (char)hbStart);
         if (hbStart == hbLast) {
            int i = rb.highLowContainer.getIndex((char)hbStart);
            if (i >= 0) {
               Container c = rb.highLowContainer.getContainerAtIndex(i).remove(lbStart, lbLast + 1);
               if (!c.isEmpty()) {
                  answer.highLowContainer.append((char)hbStart, c);
               }
            }

            answer.highLowContainer.appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         } else {
            int ifirst = rb.highLowContainer.getIndex((char)hbStart);
            int ilast = rb.highLowContainer.getIndex((char)hbLast);
            if (ifirst >= 0 && lbStart != 0) {
               Container c = rb.highLowContainer.getContainerAtIndex(ifirst).remove(lbStart, Util.maxLowBitAsInteger() + 1);
               if (!c.isEmpty()) {
                  answer.highLowContainer.append((char)hbStart, c);
               }
            }

            if (ilast >= 0 && lbLast != Util.maxLowBitAsInteger()) {
               Container c = rb.highLowContainer.getContainerAtIndex(ilast).remove(0, lbLast + 1);
               if (!c.isEmpty()) {
                  answer.highLowContainer.append((char)hbLast, c);
               }
            }

            answer.highLowContainer.appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap remove(RoaringBitmap rb, int rangeStart, int rangeEnd) {
      return rangeStart >= 0 ? remove(rb, (long)rangeStart, (long)rangeEnd) : remove(rb, (long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   public static RoaringBitmap xor(RoaringBitmap x1, RoaringBitmap x2) {
      RoaringBitmap answer = new RoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      if (pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

         label46:
         while(true) {
            while(s1 != s2) {
               if (s1 < s2) {
                  answer.highLowContainer.appendCopy(x1.highLowContainer, pos1);
                  ++pos1;
                  if (pos1 == length1) {
                     break label46;
                  }

                  s1 = x1.highLowContainer.getKeyAtIndex(pos1);
               } else {
                  answer.highLowContainer.appendCopy(x2.highLowContainer, pos2);
                  ++pos2;
                  if (pos2 == length2) {
                     break label46;
                  }

                  s2 = x2.highLowContainer.getKeyAtIndex(pos2);
               }
            }

            Container c = x1.highLowContainer.getContainerAtIndex(pos1).xor(x2.highLowContainer.getContainerAtIndex(pos2));
            if (!c.isEmpty()) {
               answer.highLowContainer.append(s1, c);
            }

            ++pos1;
            ++pos2;
            if (pos1 == length1 || pos2 == length2) {
               break;
            }

            s1 = x1.highLowContainer.getKeyAtIndex(pos1);
            s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         }
      }

      if (pos1 == length1) {
         answer.highLowContainer.appendCopy(x2.highLowContainer, pos2, length2);
      } else if (pos2 == length2) {
         answer.highLowContainer.appendCopy(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   public RoaringBitmap() {
      this.highLowContainer = new RoaringArray();
   }

   RoaringBitmap(RoaringArray highLowContainer) {
      this.highLowContainer = highLowContainer;
   }

   public RoaringBitmap(ImmutableRoaringBitmap rb) {
      this.highLowContainer = new RoaringArray();
      MappeableContainerPointer cp = rb.getContainerPointer();

      while(cp.getContainer() != null) {
         this.highLowContainer.append(cp.key(), cp.getContainer().toContainer());
         cp.advance();
      }

   }

   public void add(int x) {
      char hb = Util.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         this.highLowContainer.setContainerAtIndex(i, this.highLowContainer.getContainerAtIndex(i).add(Util.lowbits(x)));
      } else {
         ArrayContainer newac = new ArrayContainer();
         this.highLowContainer.insertNewKeyValueAt(-i - 1, hb, newac.add(Util.lowbits(x)));
      }

   }

   public void add(long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart < rangeEnd) {
         int hbStart = Util.highbits(rangeStart);
         int lbStart = Util.lowbits(rangeStart);
         int hbLast = Util.highbits(rangeEnd - 1L);
         int lbLast = Util.lowbits(rangeEnd - 1L);

         for(int hb = hbStart; hb <= hbLast; ++hb) {
            int containerStart = hb == hbStart ? lbStart : 0;
            int containerLast = hb == hbLast ? lbLast : Util.maxLowBitAsInteger();
            int i = this.highLowContainer.getIndex((char)hb);
            if (i >= 0) {
               Container c = this.highLowContainer.getContainerAtIndex(i).iadd(containerStart, containerLast + 1);
               this.highLowContainer.setContainerAtIndex(i, c);
            } else {
               this.highLowContainer.insertNewKeyValueAt(-i - 1, (char)hb, Container.rangeOfOnes(containerStart, containerLast + 1));
            }
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public void add(int rangeStart, int rangeEnd) {
      if (rangeStart >= 0) {
         this.add((long)rangeStart, (long)rangeEnd);
      }

      this.add((long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   public boolean intersects(long minimum, long supremum) {
      rangeSanityCheck(minimum, supremum);
      if (supremum <= minimum) {
         return false;
      } else {
         int minKey = (int)(minimum >>> 16);
         int supKey = (int)(supremum >>> 16);
         int length = this.highLowContainer.size;
         char[] keys = this.highLowContainer.keys;
         int limit = Util.lowbitsAsInteger(supremum);
         int index = Util.unsignedBinarySearch(keys, 0, length, (char)minKey);
         int pos = index >= 0 ? index : -index - 1;
         int offset = index >= 0 ? Util.lowbitsAsInteger(minimum) : 0;
         if (pos < length && supKey == keys[pos]) {
            if (supKey > minKey) {
               offset = 0;
            }

            return this.highLowContainer.getContainerAtIndex(pos).intersects(offset, limit);
         } else {
            while(pos < length && supKey > keys[pos]) {
               Container container = this.highLowContainer.getContainerAtIndex(pos);
               if (container.intersects(offset, 65536)) {
                  return true;
               }

               offset = 0;
               ++pos;
            }

            return pos < length && supKey == keys[pos] && this.highLowContainer.getContainerAtIndex(pos).intersects(offset, limit);
         }
      }
   }

   public void and(RoaringBitmap x2) {
      if (x2 != this) {
         int pos1 = 0;
         int pos2 = 0;
         int intersectionSize = 0;
         int length1 = this.highLowContainer.size();
         int length2 = x2.highLowContainer.size();

         while(pos1 < length1 && pos2 < length2) {
            char s1 = this.highLowContainer.getKeyAtIndex(pos1);
            char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
            if (s1 == s2) {
               Container c1 = this.highLowContainer.getContainerAtIndex(pos1);
               Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
               Container c = c1.iand(c2);
               if (!c.isEmpty()) {
                  this.highLowContainer.replaceKeyAndContainerAtIndex(intersectionSize++, s1, c);
               }

               ++pos1;
               ++pos2;
            } else if (s1 < s2) {
               pos1 = this.highLowContainer.advanceUntil(s2, pos1);
            } else {
               pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
            }
         }

         this.highLowContainer.resize(intersectionSize);
      }
   }

   public static RoaringBitmap and(Iterator bitmaps, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      Iterator<RoaringBitmap> bitmapsIterator = selectRangeWithoutCopy(bitmaps, rangeStart, rangeEnd);
      return FastAggregation.and(bitmapsIterator);
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap and(Iterator bitmaps, int rangeStart, int rangeEnd) {
      return and(bitmaps, (long)rangeStart, (long)rangeEnd);
   }

   public void andNot(RoaringBitmap x2) {
      if (x2 == this) {
         this.clear();
      } else {
         int pos1 = 0;
         int pos2 = 0;
         int intersectionSize = 0;
         int length1 = this.highLowContainer.size();
         int length2 = x2.highLowContainer.size();

         while(pos1 < length1 && pos2 < length2) {
            char s1 = this.highLowContainer.getKeyAtIndex(pos1);
            char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
            if (s1 == s2) {
               Container c1 = this.highLowContainer.getContainerAtIndex(pos1);
               Container c2 = x2.highLowContainer.getContainerAtIndex(pos2);
               Container c = c1.iandNot(c2);
               if (!c.isEmpty()) {
                  this.highLowContainer.replaceKeyAndContainerAtIndex(intersectionSize++, s1, c);
               }

               ++pos1;
               ++pos2;
            } else if (s1 < s2) {
               if (pos1 != intersectionSize) {
                  Container c1 = this.highLowContainer.getContainerAtIndex(pos1);
                  this.highLowContainer.replaceKeyAndContainerAtIndex(intersectionSize, s1, c1);
               }

               ++intersectionSize;
               ++pos1;
            } else {
               pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
            }
         }

         if (pos1 < length1) {
            this.highLowContainer.copyRange(pos1, length1, intersectionSize);
            intersectionSize += length1 - pos1;
         }

         this.highLowContainer.resize(intersectionSize);
      }
   }

   public static RoaringBitmap andNot(RoaringBitmap x1, RoaringBitmap x2, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      RoaringBitmap rb1 = selectRangeWithoutCopy(x1, rangeStart, rangeEnd);
      RoaringBitmap rb2 = selectRangeWithoutCopy(x2, rangeStart, rangeEnd);
      return andNot(rb1, rb2);
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap andNot(RoaringBitmap x1, RoaringBitmap x2, int rangeStart, int rangeEnd) {
      return andNot(x1, x2, (long)rangeStart, (long)rangeEnd);
   }

   public void orNot(RoaringBitmap other, long rangeEnd) {
      if (other == this) {
         throw new UnsupportedOperationException("orNot between a bitmap and itself?");
      } else {
         rangeSanityCheck(0L, rangeEnd);
         int maxKey = (int)(rangeEnd - 1L >>> 16);
         int lastRun = (rangeEnd & 65535L) == 0L ? 65536 : (int)(rangeEnd & 65535L);
         int size = 0;
         int pos1 = 0;
         int pos2 = 0;
         int length1 = this.highLowContainer.size();
         int length2 = other.highLowContainer.size();
         int s1 = length1 > 0 ? this.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
         int s2 = length2 > 0 ? other.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
         int remainder = 0;

         for(int i = this.highLowContainer.size - 1; i >= 0 && this.highLowContainer.keys[i] > maxKey; --i) {
            ++remainder;
         }

         int correction = 0;

         for(int i = 0; i < other.highLowContainer.size - remainder; ++i) {
            correction += other.highLowContainer.getContainerAtIndex(i).isFull() ? 1 : 0;
            if (other.highLowContainer.getKeyAtIndex(i) >= maxKey) {
               break;
            }
         }

         int maxSize = Math.min(maxKey + 1 + remainder - correction + this.highLowContainer.size, 65536);
         if (maxSize != 0) {
            char[] newKeys = new char[maxSize];
            Container[] newValues = new Container[maxSize];

            for(int key = 0; key <= maxKey && size < maxSize; ++key) {
               if (key == s1 && key == s2) {
                  newValues[size] = this.highLowContainer.getContainerAtIndex(pos1).iorNot(other.highLowContainer.getContainerAtIndex(pos2), key == maxKey ? lastRun : 65536);
                  ++pos1;
                  ++pos2;
                  s1 = pos1 < length1 ? this.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
                  s2 = pos2 < length2 ? other.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
               } else if (key == s1) {
                  newValues[size] = (Container)(key == maxKey ? this.highLowContainer.getContainerAtIndex(pos1).ior(RunContainer.rangeOfOnes(0, lastRun)) : RunContainer.full());
                  ++pos1;
                  s1 = pos1 < length1 ? this.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
               } else if (key == s2) {
                  newValues[size] = other.highLowContainer.getContainerAtIndex(pos2).not(0, key == maxKey ? lastRun : 65536);
                  ++pos2;
                  s2 = pos2 < length2 ? other.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
               } else {
                  newValues[size] = (Container)(key == maxKey ? RunContainer.rangeOfOnes(0, lastRun) : RunContainer.full());
               }

               if (newValues[size].isEmpty()) {
                  newValues[size] = null;
               } else {
                  newKeys[size] = (char)key;
                  ++size;
               }
            }

            if (remainder > 0) {
               System.arraycopy(this.highLowContainer.keys, this.highLowContainer.size - remainder, newKeys, size, remainder);
               System.arraycopy(this.highLowContainer.values, this.highLowContainer.size - remainder, newValues, size, remainder);
            }

            this.highLowContainer.keys = newKeys;
            this.highLowContainer.values = newValues;
            this.highLowContainer.size = size + remainder;
         }
      }
   }

   public static RoaringBitmap orNot(RoaringBitmap x1, RoaringBitmap x2, long rangeEnd) {
      rangeSanityCheck(0L, rangeEnd);
      int maxKey = (int)(rangeEnd - 1L >>> 16);
      int lastRun = (rangeEnd & 65535L) == 0L ? 65536 : (int)(rangeEnd & 65535L);
      int size = 0;
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();
      int s1 = length1 > 0 ? x1.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
      int s2 = length2 > 0 ? x2.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
      int remainder = 0;

      for(int i = x1.highLowContainer.size - 1; i >= 0 && x1.highLowContainer.keys[i] > maxKey; --i) {
         ++remainder;
      }

      int correction = 0;

      for(int i = 0; i < x2.highLowContainer.size - remainder; ++i) {
         correction += x2.highLowContainer.getContainerAtIndex(i).isFull() ? 1 : 0;
         if (x2.highLowContainer.getKeyAtIndex(i) >= maxKey) {
            break;
         }
      }

      int maxSize = Math.min(maxKey + 1 + remainder - correction + x1.highLowContainer.size, 65536);
      if (maxSize == 0) {
         return new RoaringBitmap();
      } else {
         char[] newKeys = new char[maxSize];
         Container[] newValues = new Container[maxSize];

         for(int key = 0; key <= maxKey && size < maxSize; ++key) {
            if (key == s1 && key == s2) {
               newValues[size] = x1.highLowContainer.getContainerAtIndex(pos1).orNot(x2.highLowContainer.getContainerAtIndex(pos2), key == maxKey ? lastRun : 65536);
               ++pos1;
               ++pos2;
               s1 = pos1 < length1 ? x1.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
               s2 = pos2 < length2 ? x2.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
            } else if (key == s1) {
               newValues[size] = (Container)(key == maxKey ? x1.highLowContainer.getContainerAtIndex(pos1).ior(RunContainer.rangeOfOnes(0, lastRun)) : RunContainer.full());
               ++pos1;
               s1 = pos1 < length1 ? x1.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
            } else if (key == s2) {
               newValues[size] = x2.highLowContainer.getContainerAtIndex(pos2).not(0, key == maxKey ? lastRun : 65536);
               ++pos2;
               s2 = pos2 < length2 ? x2.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
            } else {
               newValues[size] = (Container)(key == maxKey ? RunContainer.rangeOfOnes(0, lastRun) : RunContainer.full());
            }

            if (newValues[size].isEmpty()) {
               newValues[size] = null;
            } else {
               newKeys[size++] = (char)key;
            }
         }

         if (remainder > 0) {
            System.arraycopy(x1.highLowContainer.keys, x1.highLowContainer.size - remainder, newKeys, size, remainder);
            System.arraycopy(x1.highLowContainer.values, x1.highLowContainer.size - remainder, newValues, size, remainder);

            for(int i = size; i < size + remainder; ++i) {
               newValues[i] = newValues[i].clone();
            }
         }

         RoaringBitmap result = new RoaringBitmap();
         result.highLowContainer.keys = newKeys;
         result.highLowContainer.values = newValues;
         result.highLowContainer.size = size + remainder;
         return result;
      }
   }

   public boolean checkedAdd(int x) {
      char hb = Util.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         Container c = this.highLowContainer.getContainerAtIndex(i);
         if (c instanceof RunContainer) {
            if (!c.contains(Util.lowbits(x))) {
               Container newCont = c.add(Util.lowbits(x));
               this.highLowContainer.setContainerAtIndex(i, newCont);
               return true;
            }
         } else {
            int oldCard = c.getCardinality();
            Container newCont = c.add(Util.lowbits(x));
            this.highLowContainer.setContainerAtIndex(i, newCont);
            if (newCont.getCardinality() > oldCard) {
               return true;
            }
         }

         return false;
      } else {
         ArrayContainer newac = new ArrayContainer();
         this.highLowContainer.insertNewKeyValueAt(-i - 1, hb, newac.add(Util.lowbits(x)));
         return true;
      }
   }

   public boolean checkedRemove(int x) {
      char hb = Util.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i < 0) {
         return false;
      } else {
         Container C = this.highLowContainer.getContainerAtIndex(i);
         int oldcard = C.getCardinality();
         C.remove(Util.lowbits(x));
         int newcard = C.getCardinality();
         if (newcard == oldcard) {
            return false;
         } else {
            if (newcard > 0) {
               this.highLowContainer.setContainerAtIndex(i, C);
            } else {
               this.highLowContainer.removeAtIndex(i);
            }

            return true;
         }
      }
   }

   public void clear() {
      this.highLowContainer = new RoaringArray();
   }

   public RoaringBitmap clone() {
      try {
         RoaringBitmap x = (RoaringBitmap)super.clone();
         x.highLowContainer = this.highLowContainer.clone();
         return x;
      } catch (CloneNotSupportedException e) {
         throw new RuntimeException("shouldn't happen with clone", e);
      }
   }

   public boolean contains(int x) {
      char hb = Util.highbits(x);
      int index = this.highLowContainer.getContainerIndex(hb);
      if (index < 0) {
         return false;
      } else {
         Container c = this.highLowContainer.getContainerAtIndex(index);
         return c.contains(Util.lowbits(x));
      }
   }

   public boolean contains(long minimum, long supremum) {
      rangeSanityCheck(minimum, supremum);
      if (supremum <= minimum) {
         return false;
      } else {
         char firstKey = Util.highbits(minimum);
         char lastKey = Util.highbits(supremum);
         int span = lastKey - firstKey;
         int len = this.highLowContainer.size;
         if (len < span) {
            return false;
         } else {
            int begin = this.highLowContainer.getIndex(firstKey);
            int end = this.highLowContainer.getIndex(lastKey);
            end = end < 0 ? -end - 1 : end;
            if (begin >= 0 && end - begin == span) {
               int min = (char)((int)minimum);
               int sup = (char)((int)supremum);
               if (firstKey == lastKey) {
                  return this.highLowContainer.getContainerAtIndex(begin).contains(min, (supremum & 65535L) == 0L ? 65536 : sup);
               } else if (!this.highLowContainer.getContainerAtIndex(begin).contains(min, 65536)) {
                  return false;
               } else if (sup != 0 && end < len && !this.highLowContainer.getContainerAtIndex(end).contains(0, sup)) {
                  return false;
               } else {
                  for(int i = begin + 1; i < end; ++i) {
                     if (this.highLowContainer.getContainerAtIndex(i).getCardinality() != 65536) {
                        return false;
                     }
                  }

                  return true;
               }
            } else {
               return false;
            }
         }
      }
   }

   public void deserialize(DataInput in, byte[] buffer) throws IOException {
      try {
         this.highLowContainer.deserialize(in, buffer);
      } catch (InvalidRoaringFormat cookie) {
         throw cookie.toIOException();
      }
   }

   public void deserialize(DataInput in) throws IOException {
      try {
         this.highLowContainer.deserialize(in);
      } catch (InvalidRoaringFormat cookie) {
         throw cookie.toIOException();
      }
   }

   public void deserialize(ByteBuffer bbf) throws IOException {
      try {
         this.highLowContainer.deserialize(bbf);
      } catch (InvalidRoaringFormat cookie) {
         throw cookie.toIOException();
      }
   }

   public boolean equals(Object o) {
      if (o instanceof RoaringBitmap) {
         RoaringBitmap srb = (RoaringBitmap)o;
         return srb.highLowContainer.equals(this.highLowContainer);
      } else {
         return false;
      }
   }

   public boolean isHammingSimilar(RoaringBitmap other, int tolerance) {
      int size1 = this.highLowContainer.size();
      int size2 = other.highLowContainer.size();
      int pos1 = 0;
      int pos2 = 0;
      int budget = tolerance;

      while(budget >= 0 && pos1 < size1 && pos2 < size2) {
         char key1 = this.highLowContainer.getKeyAtIndex(pos1);
         char key2 = other.highLowContainer.getKeyAtIndex(pos2);
         Container left = this.highLowContainer.getContainerAtIndex(pos1);
         Container right = other.highLowContainer.getContainerAtIndex(pos2);
         if (key1 == key2) {
            budget -= left.xorCardinality(right);
            ++pos1;
            ++pos2;
         } else if (key1 < key2) {
            budget -= left.getCardinality();
            ++pos1;
         } else {
            budget -= right.getCardinality();
            ++pos2;
         }
      }

      while(budget >= 0 && pos1 < size1) {
         Container container = this.highLowContainer.getContainerAtIndex(pos1++);
         budget -= container.getCardinality();
      }

      while(budget >= 0 && pos2 < size2) {
         Container container = other.highLowContainer.getContainerAtIndex(pos2++);
         budget -= container.getCardinality();
      }

      return budget >= 0;
   }

   public void flip(int x) {
      char hb = Util.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         Container c = this.highLowContainer.getContainerAtIndex(i).flip(Util.lowbits(x));
         if (!c.isEmpty()) {
            this.highLowContainer.setContainerAtIndex(i, c);
         } else {
            this.highLowContainer.removeAtIndex(i);
         }
      } else {
         ArrayContainer newac = new ArrayContainer();
         this.highLowContainer.insertNewKeyValueAt(-i - 1, hb, newac.add(Util.lowbits(x)));
      }

   }

   public void flip(long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart < rangeEnd) {
         int hbStart = Util.highbits(rangeStart);
         int lbStart = Util.lowbits(rangeStart);
         int hbLast = Util.highbits(rangeEnd - 1L);
         int lbLast = Util.lowbits(rangeEnd - 1L);

         for(int hb = hbStart; hb <= hbLast; ++hb) {
            int containerStart = hb == hbStart ? lbStart : 0;
            int containerLast = hb == hbLast ? lbLast : Util.maxLowBitAsInteger();
            int i = this.highLowContainer.getIndex((char)hb);
            if (i >= 0) {
               Container c = this.highLowContainer.getContainerAtIndex(i).inot(containerStart, containerLast + 1);
               if (!c.isEmpty()) {
                  this.highLowContainer.setContainerAtIndex(i, c);
               } else {
                  this.highLowContainer.removeAtIndex(i);
               }
            } else {
               this.highLowContainer.insertNewKeyValueAt(-i - 1, (char)hb, Container.rangeOfOnes(containerStart, containerLast + 1));
            }
         }

      }
   }

   /** @deprecated */
   @Deprecated
   public void flip(int rangeStart, int rangeEnd) {
      if (rangeStart >= 0) {
         this.flip((long)rangeStart, (long)rangeEnd);
      } else {
         this.flip((long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
      }

   }

   public long getLongCardinality() {
      long size = 0L;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         size += (long)this.highLowContainer.getContainerAtIndex(i).getCardinality();
      }

      return size;
   }

   public int getCardinality() {
      return (int)this.getLongCardinality();
   }

   public boolean cardinalityExceeds(long threshold) {
      long size = 0L;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         size += (long)this.highLowContainer.getContainerAtIndex(i).getCardinality();
         if (size > threshold) {
            return true;
         }
      }

      return false;
   }

   public void forEach(IntConsumer ic) {
      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         this.highLowContainer.getContainerAtIndex(i).forEach(this.highLowContainer.keys[i], ic);
      }

   }

   public void forAllInRange(int uStart, int length, RelativeRangeConsumer rrc) {
      if (length < 0) {
         throw new IllegalArgumentException("length must be >= 0 but was " + length);
      } else if (length != 0) {
         char startHigh = Util.highbits(uStart);
         long startL = Integer.toUnsignedLong(uStart);
         long endInclusiveL = startL + (long)(length - 1);
         if (endInclusiveL > LongUtils.MAX_UNSIGNED_INT) {
            long remainingLength = LongUtils.MAX_UNSIGNED_INT - startL + 1L;
            throw new IllegalArgumentException("Cannot read past the end of the unsigned integer range. " + remainingLength + " values remaining; requested " + length);
         } else {
            int uEndInclusive = (int)endInclusiveL;
            char endHigh = Util.highbits(uEndInclusive);
            int startIndex = this.highLowContainer.getContainerIndex(startHigh);
            startIndex = startIndex < 0 ? -startIndex - 1 : startIndex;
            int uFilledUntil = uStart;

            for(int containerIndex = startIndex; containerIndex < this.highLowContainer.size(); ++containerIndex) {
               if (Integer.compareUnsigned(uEndInclusive, uFilledUntil) < 0) {
                  return;
               }

               char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
               int uContainerStart = containerKey << 16;
               int uContainerEndInclusive = uContainerStart + '\uffff';
               if (endHigh < containerKey) {
                  if (Integer.compareUnsigned(uFilledUntil, uContainerStart) < 0) {
                     int fillFromRelative = uFilledUntil - uStart;

                     assert fillFromRelative >= 0;

                     rrc.acceptAllAbsent(fillFromRelative, length);
                  }

                  return;
               }

               if (Integer.compareUnsigned(uFilledUntil, uContainerStart) < 0) {
                  int fillFromRelative = uFilledUntil - uStart;
                  int fillToRelative = uContainerStart - uStart;

                  assert fillFromRelative >= 0;

                  assert fillToRelative >= 0;

                  rrc.acceptAllAbsent(fillFromRelative, fillToRelative);
                  uFilledUntil = uContainerStart;
               }

               Container container = this.highLowContainer.getContainerAtIndex(containerIndex);
               int containerRangeStartOffset = uFilledUntil - uStart;

               assert containerRangeStartOffset >= 0;

               boolean startInContainer = Integer.compareUnsigned(uContainerStart, uStart) < 0;
               boolean endInContainer = Integer.compareUnsigned(uEndInclusive, uContainerEndInclusive) < 0;
               if (startInContainer && endInContainer) {
                  char containerRangeStart = LongUtils.lowPart((long)uStart);
                  int uEndExclusive = uEndInclusive + 1;

                  assert Integer.compareUnsigned(uEndInclusive, uEndExclusive) < 0;

                  char containerRangeEnd = LongUtils.lowPart((long)uEndExclusive);
                  container.forAllInRange(containerRangeStart, containerRangeEnd, rrc);
                  return;
               }

               if (startInContainer) {
                  char containerRangeStart = LongUtils.lowPart((long)uStart);
                  container.forAllFrom(containerRangeStart, rrc);
                  int numValuesAdded = 65536 - containerRangeStart;

                  assert numValuesAdded >= 0;

                  int uOldFilledUntil = uFilledUntil;
                  uFilledUntil += numValuesAdded;
                  if (Integer.compareUnsigned(uFilledUntil, uOldFilledUntil) < 0) {
                     return;
                  }
               } else {
                  if (endInContainer) {
                     int uEndExclusive = uEndInclusive + 1;

                     assert Integer.compareUnsigned(uEndInclusive, uEndExclusive) < 0;

                     char containerRangeEnd = LongUtils.lowPart((long)uEndExclusive);
                     container.forAllUntil(containerRangeStartOffset, containerRangeEnd, rrc);
                     return;
                  }

                  container.forAll(containerRangeStartOffset, rrc);
                  int uOldFilledUntil = uFilledUntil;
                  uFilledUntil += 65536;
                  if (Integer.compareUnsigned(uFilledUntil, uOldFilledUntil) < 0) {
                     return;
                  }
               }
            }

            if (Integer.compareUnsigned(uFilledUntil, uEndInclusive) <= 0) {
               int fillFromRelative = uFilledUntil - uStart;

               assert fillFromRelative >= 0;

               rrc.acceptAllAbsent(fillFromRelative, length);
            }

         }
      }
   }

   public void forEachInRange(int start, int length, IntConsumer ic) {
      this.forAllInRange(start, length, new IntConsumerRelativeRangeAdapter(start, ic));
   }

   public ContainerPointer getContainerPointer() {
      return this.highLowContainer.getContainerPointer();
   }

   public PeekableIntIterator getIntIterator() {
      return new RoaringIntIterator();
   }

   public PeekableIntIterator getSignedIntIterator() {
      return new RoaringSignedIntIterator();
   }

   public IntIterator getReverseIntIterator() {
      return new RoaringReverseIntIterator();
   }

   public RoaringBatchIterator getBatchIterator() {
      return new RoaringBatchIterator(this.highLowContainer);
   }

   public long getLongSizeInBytes() {
      long size = 8L;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         Container c = this.highLowContainer.getContainerAtIndex(i);
         size += (long)(2 + c.getSizeInBytes());
      }

      return size;
   }

   public int getSizeInBytes() {
      return (int)this.getLongSizeInBytes();
   }

   public int hashCode() {
      return this.highLowContainer.hashCode();
   }

   public boolean hasRunCompression() {
      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         Container c = this.highLowContainer.getContainerAtIndex(i);
         if (c instanceof RunContainer) {
            return true;
         }
      }

      return false;
   }

   public boolean isEmpty() {
      return this.highLowContainer.size() == 0;
   }

   public Iterator iterator() {
      return (new Iterator() {
         private int hs = 0;
         private CharIterator iter;
         private int pos = 0;
         private int x;

         public boolean hasNext() {
            return this.pos < RoaringBitmap.this.highLowContainer.size();
         }

         private Iterator init() {
            if (this.pos < RoaringBitmap.this.highLowContainer.size()) {
               this.iter = RoaringBitmap.this.highLowContainer.getContainerAtIndex(this.pos).getCharIterator();
               this.hs = RoaringBitmap.this.highLowContainer.getKeyAtIndex(this.pos) << 16;
            }

            return this;
         }

         public Integer next() {
            this.x = this.iter.nextAsInt() | this.hs;
            if (!this.iter.hasNext()) {
               ++this.pos;
               this.init();
            }

            return this.x;
         }

         public void remove() {
            throw new UnsupportedOperationException();
         }
      }).init();
   }

   protected void lazyor(RoaringBitmap x2) {
      if (this != x2) {
         int pos1 = 0;
         int pos2 = 0;
         int length1 = this.highLowContainer.size();
         int length2 = x2.highLowContainer.size();
         if (pos1 < length1 && pos2 < length2) {
            char s1 = this.highLowContainer.getKeyAtIndex(pos1);
            char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

            label42:
            while(true) {
               while(s1 != s2) {
                  if (s1 < s2) {
                     ++pos1;
                     if (pos1 == length1) {
                        break label42;
                     }

                     s1 = this.highLowContainer.getKeyAtIndex(pos1);
                  } else {
                     this.highLowContainer.insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label42;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               this.highLowContainer.setContainerAtIndex(pos1, this.highLowContainer.getContainerAtIndex(pos1).lazyIOR(x2.highLowContainer.getContainerAtIndex(pos2)));
               ++pos1;
               ++pos2;
               if (pos1 == length1 || pos2 == length2) {
                  break;
               }

               s1 = this.highLowContainer.getKeyAtIndex(pos1);
               s2 = x2.highLowContainer.getKeyAtIndex(pos2);
            }
         }

         if (pos1 == length1) {
            this.highLowContainer.appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   protected void naivelazyor(RoaringBitmap x2) {
      if (this != x2) {
         int pos1 = 0;
         int pos2 = 0;
         int length1 = this.highLowContainer.size();
         int length2 = x2.highLowContainer.size();
         if (pos1 < length1 && pos2 < length2) {
            char s1 = this.highLowContainer.getKeyAtIndex(pos1);
            char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

            label42:
            while(true) {
               while(s1 != s2) {
                  if (s1 < s2) {
                     ++pos1;
                     if (pos1 == length1) {
                        break label42;
                     }

                     s1 = this.highLowContainer.getKeyAtIndex(pos1);
                  } else {
                     this.highLowContainer.insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label42;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               BitmapContainer c1 = this.highLowContainer.getContainerAtIndex(pos1).toBitmapContainer();
               this.highLowContainer.setContainerAtIndex(pos1, c1.lazyIOR(x2.highLowContainer.getContainerAtIndex(pos2)));
               ++pos1;
               ++pos2;
               if (pos1 == length1 || pos2 == length2) {
                  break;
               }

               s1 = this.highLowContainer.getKeyAtIndex(pos1);
               s2 = x2.highLowContainer.getKeyAtIndex(pos2);
            }
         }

         if (pos1 == length1) {
            this.highLowContainer.appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   public RoaringBitmap limit(int maxcardinality) {
      RoaringBitmap answer = new RoaringBitmap();
      int currentcardinality = 0;

      for(int i = 0; currentcardinality < maxcardinality && i < this.highLowContainer.size(); ++i) {
         Container c = this.highLowContainer.getContainerAtIndex(i);
         if (c.getCardinality() + currentcardinality > maxcardinality) {
            int leftover = maxcardinality - currentcardinality;
            Container limited = c.limit(leftover);
            answer.highLowContainer.append(this.highLowContainer.getKeyAtIndex(i), limited);
            break;
         }

         answer.highLowContainer.appendCopy(this.highLowContainer, i);
         currentcardinality += c.getCardinality();
      }

      return answer;
   }

   public void or(RoaringBitmap x2) {
      if (this != x2) {
         int pos1 = 0;
         int pos2 = 0;
         int length1 = this.highLowContainer.size();
         int length2 = x2.highLowContainer.size();
         if (pos1 < length1 && pos2 < length2) {
            char s1 = this.highLowContainer.getKeyAtIndex(pos1);
            char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

            label42:
            while(true) {
               while(s1 != s2) {
                  if (s1 < s2) {
                     ++pos1;
                     if (pos1 == length1) {
                        break label42;
                     }

                     s1 = this.highLowContainer.getKeyAtIndex(pos1);
                  } else {
                     this.highLowContainer.insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label42;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               this.highLowContainer.setContainerAtIndex(pos1, this.highLowContainer.getContainerAtIndex(pos1).ior(x2.highLowContainer.getContainerAtIndex(pos2)));
               ++pos1;
               ++pos2;
               if (pos1 == length1 || pos2 == length2) {
                  break;
               }

               s1 = this.highLowContainer.getKeyAtIndex(pos1);
               s2 = x2.highLowContainer.getKeyAtIndex(pos2);
            }
         }

         if (pos1 == length1) {
            this.highLowContainer.appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   public static RoaringBitmap or(Iterator bitmaps, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      Iterator<RoaringBitmap> bitmapsIterator = selectRangeWithoutCopy(bitmaps, rangeStart, rangeEnd);
      return or(bitmapsIterator);
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap or(Iterator bitmaps, int rangeStart, int rangeEnd) {
      return or(bitmaps, (long)rangeStart, (long)rangeEnd);
   }

   public long rankLong(int x) {
      long size = 0L;
      char xhigh = Util.highbits(x);

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         char key = this.highLowContainer.getKeyAtIndex(i);
         if (key < xhigh) {
            size += (long)this.highLowContainer.getContainerAtIndex(i).getCardinality();
         } else if (key == xhigh) {
            return size + (long)this.highLowContainer.getContainerAtIndex(i).rank(Util.lowbits(x));
         }
      }

      return size;
   }

   public long rangeCardinality(long start, long end) {
      if (Long.compareUnsigned(start, end) >= 0) {
         return 0L;
      } else {
         long size = 0L;
         int startIndex = this.highLowContainer.getIndex(Util.highbits(start));
         if (startIndex < 0) {
            startIndex = -startIndex - 1;
         } else {
            int inContainerStart = Util.lowbits(start);
            if (inContainerStart != 0) {
               size -= (long)this.highLowContainer.getContainerAtIndex(startIndex).rank((char)(inContainerStart - 1));
            }
         }

         char xhigh = Util.highbits(end - 1L);

         for(int i = startIndex; i < this.highLowContainer.size(); ++i) {
            char key = this.highLowContainer.getKeyAtIndex(i);
            if (key < xhigh) {
               size += (long)this.highLowContainer.getContainerAtIndex(i).getCardinality();
            } else if (key == xhigh) {
               return size + (long)this.highLowContainer.getContainerAtIndex(i).rank(Util.lowbits((int)(end - 1L)));
            }
         }

         return size;
      }
   }

   public int rank(int x) {
      return (int)this.rankLong(x);
   }

   public void readExternal(ObjectInput in) throws IOException {
      this.highLowContainer.readExternal(in);
   }

   public void remove(int x) {
      char hb = Util.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         this.highLowContainer.setContainerAtIndex(i, this.highLowContainer.getContainerAtIndex(i).remove(Util.lowbits(x)));
         if (this.highLowContainer.getContainerAtIndex(i).isEmpty()) {
            this.highLowContainer.removeAtIndex(i);
         }

      }
   }

   public void remove(long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart < rangeEnd) {
         int hbStart = Util.highbits(rangeStart);
         int lbStart = Util.lowbits(rangeStart);
         int hbLast = Util.highbits(rangeEnd - 1L);
         int lbLast = Util.lowbits(rangeEnd - 1L);
         if (hbStart == hbLast) {
            int i = this.highLowContainer.getIndex((char)hbStart);
            if (i >= 0) {
               Container c = this.highLowContainer.getContainerAtIndex(i).iremove(lbStart, lbLast + 1);
               if (!c.isEmpty()) {
                  this.highLowContainer.setContainerAtIndex(i, c);
               } else {
                  this.highLowContainer.removeAtIndex(i);
               }

            }
         } else {
            int ifirst = this.highLowContainer.getIndex((char)hbStart);
            int ilast = this.highLowContainer.getIndex((char)hbLast);
            if (ifirst >= 0) {
               if (lbStart != 0) {
                  Container c = this.highLowContainer.getContainerAtIndex(ifirst).iremove(lbStart, Util.maxLowBitAsInteger() + 1);
                  if (!c.isEmpty()) {
                     this.highLowContainer.setContainerAtIndex(ifirst, c);
                     ++ifirst;
                  }
               }
            } else {
               ifirst = -ifirst - 1;
            }

            if (ilast >= 0) {
               if (lbLast != Util.maxLowBitAsInteger()) {
                  Container c = this.highLowContainer.getContainerAtIndex(ilast).iremove(0, lbLast + 1);
                  if (!c.isEmpty()) {
                     this.highLowContainer.setContainerAtIndex(ilast, c);
                  } else {
                     ++ilast;
                  }
               } else {
                  ++ilast;
               }
            } else {
               ilast = -ilast - 1;
            }

            this.highLowContainer.removeIndexRange(ifirst, ilast);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public void remove(int rangeStart, int rangeEnd) {
      if (rangeStart >= 0) {
         this.remove((long)rangeStart, (long)rangeEnd);
      }

      this.remove((long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   public boolean removeRunCompression() {
      boolean answer = false;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         Container c = this.highLowContainer.getContainerAtIndex(i);
         if (c instanceof RunContainer) {
            Container newc = ((RunContainer)c).toBitmapOrArrayContainer(c.getCardinality());
            this.highLowContainer.setContainerAtIndex(i, newc);
            answer = true;
         }
      }

      return answer;
   }

   protected void repairAfterLazy() {
      for(int k = 0; k < this.highLowContainer.size(); ++k) {
         Container c = this.highLowContainer.getContainerAtIndex(k);
         this.highLowContainer.setContainerAtIndex(k, c.repairAfterLazy());
      }

   }

   public boolean runOptimize() {
      boolean answer = false;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         Container c = this.highLowContainer.getContainerAtIndex(i).runOptimize();
         if (c instanceof RunContainer) {
            answer = true;
         }

         this.highLowContainer.setContainerAtIndex(i, c);
      }

      return answer;
   }

   public boolean contains(RoaringBitmap subset) {
      int length1 = this.highLowContainer.size;
      int length2 = subset.highLowContainer.size;
      int pos1 = 0;
      int pos2 = 0;

      while(pos1 < length1 && pos2 < length2) {
         char s1 = this.highLowContainer.getKeyAtIndex(pos1);
         char s2 = subset.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            Container c1 = this.highLowContainer.getContainerAtIndex(pos1);
            Container c2 = subset.highLowContainer.getContainerAtIndex(pos2);
            if (!c1.contains(c2)) {
               return false;
            }

            ++pos1;
            ++pos2;
         } else {
            if (s1 - s2 > 0) {
               return false;
            }

            pos1 = subset.highLowContainer.advanceUntil(s2, pos1);
         }
      }

      return pos2 == length2;
   }

   public int select(int j) {
      long leftover = Util.toUnsignedLong(j);

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         Container c = this.highLowContainer.getContainerAtIndex(i);
         int thiscard = c.getCardinality();
         if ((long)thiscard > leftover) {
            int keycontrib = this.highLowContainer.getKeyAtIndex(i) << 16;
            int lowcontrib = c.select((int)leftover);
            return lowcontrib + keycontrib;
         }

         leftover -= (long)thiscard;
      }

      throw new IllegalArgumentException("You are trying to select the " + j + "th value when the cardinality is " + this.getCardinality() + ".");
   }

   public long nextValue(int fromValue) {
      char key = Util.highbits(fromValue);
      int containerIndex = this.highLowContainer.advanceUntil(key, -1);

      long nextSetBit;
      for(nextSetBit = -1L; containerIndex < this.highLowContainer.size() && nextSetBit == -1L; ++containerIndex) {
         char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
         Container container = this.highLowContainer.getContainerAtIndex(containerIndex);
         int bit = containerKey - key > 0 ? container.first() : container.nextValue(Util.lowbits(fromValue));
         nextSetBit = bit == -1 ? -1L : Util.toUnsignedLong(containerKey << 16 | bit);
      }

      assert nextSetBit <= 4294967295L;

      assert nextSetBit == -1L || nextSetBit >= Util.toUnsignedLong(fromValue);

      return nextSetBit;
   }

   public long previousValue(int fromValue) {
      if (this.isEmpty()) {
         return -1L;
      } else {
         char key = Util.highbits(fromValue);
         int containerIndex = this.highLowContainer.advanceUntil(key, -1);
         if (containerIndex == this.highLowContainer.size()) {
            return Util.toUnsignedLong(this.last());
         } else {
            if (this.highLowContainer.getKeyAtIndex(containerIndex) > key) {
               --containerIndex;
            }

            long prevSetBit;
            for(prevSetBit = -1L; containerIndex != -1 && prevSetBit == -1L; --containerIndex) {
               char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
               Container container = this.highLowContainer.getContainerAtIndex(containerIndex);
               int bit = containerKey < key ? container.last() : container.previousValue(Util.lowbits(fromValue));
               prevSetBit = bit == -1 ? -1L : Util.toUnsignedLong(containerKey << 16 | bit);
            }

            assert prevSetBit <= 4294967295L;

            assert prevSetBit <= Util.toUnsignedLong(fromValue);

            return prevSetBit;
         }
      }
   }

   public long nextAbsentValue(int fromValue) {
      long nextAbsentBit = this.computeNextAbsentValue(fromValue);
      return nextAbsentBit == 4294967296L ? -1L : nextAbsentBit;
   }

   private long computeNextAbsentValue(int fromValue) {
      char key = Util.highbits(fromValue);
      int containerIndex = this.highLowContainer.advanceUntil(key, -1);
      int size = this.highLowContainer.size();
      if (containerIndex == size) {
         return Util.toUnsignedLong(fromValue);
      } else {
         char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
         if (fromValue < containerKey << 16) {
            return Util.toUnsignedLong(fromValue);
         } else {
            Container container = this.highLowContainer.getContainerAtIndex(containerIndex);

            int bit;
            for(bit = container.nextAbsentValue(Util.lowbits(fromValue)); bit == 65536; bit = container.nextAbsentValue('\u0000')) {
               assert container.last() == 65535;

               if (containerIndex == size - 1) {
                  return Util.toUnsignedLong(this.highLowContainer.last()) + 1L;
               }

               ++containerIndex;
               char nextContainerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
               if (containerKey + 1 < nextContainerKey) {
                  return Util.toUnsignedLong(containerKey + 1 << 16);
               }

               containerKey = nextContainerKey;
               container = this.highLowContainer.getContainerAtIndex(containerIndex);
            }

            return Util.toUnsignedLong(containerKey << 16 | bit);
         }
      }
   }

   public long previousAbsentValue(int fromValue) {
      long prevAbsentBit = this.computePreviousAbsentValue(fromValue);

      assert prevAbsentBit <= 4294967295L;

      assert prevAbsentBit <= Util.toUnsignedLong(fromValue);

      assert !this.contains((int)prevAbsentBit);

      return prevAbsentBit;
   }

   private long computePreviousAbsentValue(int fromValue) {
      char key = Util.highbits(fromValue);
      int containerIndex = this.highLowContainer.advanceUntil(key, -1);
      if (containerIndex == this.highLowContainer.size()) {
         return Util.toUnsignedLong(fromValue);
      } else {
         char containerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
         if (fromValue < containerKey << 16) {
            return Util.toUnsignedLong(fromValue);
         } else {
            Container container = this.highLowContainer.getContainerAtIndex(containerIndex);

            int bit;
            for(bit = container.previousAbsentValue(Util.lowbits(fromValue)); bit == -1; bit = container.previousAbsentValue('\uffff')) {
               assert container.first() == 0;

               if (containerIndex == 0) {
                  return Util.toUnsignedLong(this.highLowContainer.first()) - 1L;
               }

               --containerIndex;
               char nextContainerKey = this.highLowContainer.getKeyAtIndex(containerIndex);
               if (nextContainerKey < containerKey - 1) {
                  return Util.toUnsignedLong(containerKey << 16) - 1L;
               }

               containerKey = nextContainerKey;
               container = this.highLowContainer.getContainerAtIndex(containerIndex);
            }

            return Util.toUnsignedLong(containerKey << 16 | bit);
         }
      }
   }

   public int first() {
      return this.highLowContainer.first();
   }

   public int last() {
      return this.highLowContainer.last();
   }

   public int firstSigned() {
      return this.highLowContainer.firstSigned();
   }

   public int lastSigned() {
      return this.highLowContainer.lastSigned();
   }

   public void serialize(DataOutput out) throws IOException {
      this.highLowContainer.serialize(out);
   }

   public void serialize(ByteBuffer buffer) {
      this.highLowContainer.serialize(buffer);
   }

   public static long maximumSerializedSize(long cardinality, long universe_size) {
      long contnbr = (universe_size + 65535L) / 65536L;
      if (contnbr > cardinality) {
         contnbr = cardinality;
      }

      long headermax = Math.max(8L, 4L + (contnbr + 7L) / 8L) + 8L * contnbr;
      long valsarray = 2L * cardinality;
      long valsbitmap = contnbr * 8192L;
      long valsbest = Math.min(valsarray, valsbitmap);
      return valsbest + headermax;
   }

   public int serializedSizeInBytes() {
      return this.highLowContainer.serializedSizeInBytes();
   }

   private static Iterator selectRangeWithoutCopy(final Iterator bitmaps, final long rangeStart, final long rangeEnd) {
      Iterator<RoaringBitmap> bitmapsIterator = new Iterator() {
         public boolean hasNext() {
            return bitmaps.hasNext();
         }

         public RoaringBitmap next() {
            RoaringBitmap next = (RoaringBitmap)bitmaps.next();
            return RoaringBitmap.selectRangeWithoutCopy(next, rangeStart, rangeEnd);
         }

         public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
         }
      };
      return bitmapsIterator;
   }

   public RoaringBitmap selectRange(long rangeStart, long rangeEnd) {
      int hbStart = Util.highbits(rangeStart);
      int lbStart = Util.lowbits(rangeStart);
      int hbLast = Util.highbits(rangeEnd - 1L);
      int lbLast = Util.lowbits(rangeEnd - 1L);
      RoaringBitmap answer = new RoaringBitmap();

      assert rangeStart >= 0L && rangeEnd >= 0L;

      if (rangeEnd <= rangeStart) {
         return answer;
      } else if (hbStart == hbLast) {
         int i = this.highLowContainer.getIndex((char)hbStart);
         if (i >= 0) {
            Container c = this.highLowContainer.getContainerAtIndex(i).remove(0, lbStart).iremove(lbLast + 1, Util.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               answer.highLowContainer.append((char)hbStart, c);
            }
         }

         return answer;
      } else {
         int ifirst = this.highLowContainer.getIndex((char)hbStart);
         int ilast = this.highLowContainer.getIndex((char)hbLast);
         if (ifirst >= 0) {
            Container c = this.highLowContainer.getContainerAtIndex(ifirst).remove(0, lbStart);
            if (!c.isEmpty()) {
               answer.highLowContainer.append((char)hbStart, c.clone());
            }
         }

         for(int hb = hbStart + 1; hb <= hbLast - 1; ++hb) {
            int i = this.highLowContainer.getIndex((char)hb);
            int j = answer.highLowContainer.getIndex((char)hb);

            assert j < 0;

            if (i >= 0) {
               Container c = this.highLowContainer.getContainerAtIndex(i);
               answer.highLowContainer.insertNewKeyValueAt(-j - 1, (char)hb, c.clone());
            }
         }

         if (ilast >= 0) {
            Container c = this.highLowContainer.getContainerAtIndex(ilast).remove(lbLast + 1, Util.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               answer.highLowContainer.append((char)hbLast, c);
            }
         }

         return answer;
      }
   }

   private static RoaringBitmap selectRangeWithoutCopy(RoaringBitmap rb, long rangeStart, long rangeEnd) {
      int hbStart = Util.highbits(rangeStart);
      int lbStart = Util.lowbits(rangeStart);
      int hbLast = Util.highbits(rangeEnd - 1L);
      int lbLast = Util.lowbits(rangeEnd - 1L);
      RoaringBitmap answer = new RoaringBitmap();

      assert rangeStart >= 0L && rangeEnd >= 0L;

      if (rangeEnd <= rangeStart) {
         return answer;
      } else if (hbStart == hbLast) {
         int i = rb.highLowContainer.getIndex((char)hbStart);
         if (i >= 0) {
            Container c = rb.highLowContainer.getContainerAtIndex(i).remove(0, lbStart).iremove(lbLast + 1, Util.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               answer.highLowContainer.append((char)hbStart, c);
            }
         }

         return answer;
      } else {
         int ifirst = rb.highLowContainer.getIndex((char)hbStart);
         int ilast = rb.highLowContainer.getIndex((char)hbLast);
         if (ifirst >= 0) {
            Container c = rb.highLowContainer.getContainerAtIndex(ifirst).remove(0, lbStart);
            if (!c.isEmpty()) {
               answer.highLowContainer.append((char)hbStart, c);
            }
         }

         for(int hb = hbStart + 1; hb <= hbLast - 1; ++hb) {
            int i = rb.highLowContainer.getIndex((char)hb);
            int j = answer.highLowContainer.getIndex((char)hb);

            assert j < 0;

            if (i >= 0) {
               Container c = rb.highLowContainer.getContainerAtIndex(i);
               answer.highLowContainer.insertNewKeyValueAt(-j - 1, (char)hb, c);
            }
         }

         if (ilast >= 0) {
            Container c = rb.highLowContainer.getContainerAtIndex(ilast).remove(lbLast + 1, Util.maxLowBitAsInteger() + 1);
            if (!c.isEmpty()) {
               answer.highLowContainer.append((char)hbLast, c);
            }
         }

         return answer;
      }
   }

   public int[] toArray() {
      int[] array = new int[this.getCardinality()];
      int pos = 0;

      Container c;
      for(int pos2 = 0; pos < this.highLowContainer.size(); pos2 += c.getCardinality()) {
         int hs = this.highLowContainer.getKeyAtIndex(pos) << 16;
         c = this.highLowContainer.getContainerAtIndex(pos++);
         c.fillLeastSignificant16bits(array, pos2, hs);
      }

      return array;
   }

   public void append(char key, Container container) {
      this.highLowContainer.append(key, container);
   }

   public MutableRoaringBitmap toMutableRoaringBitmap() {
      return new MutableRoaringBitmap(this);
   }

   public String toString() {
      StringBuilder answer = new StringBuilder("{}".length() + "-123456789,".length() * 256);
      IntIterator i = this.getIntIterator();
      answer.append('{');
      if (i.hasNext()) {
         answer.append((long)i.next() & 4294967295L);
      }

      while(i.hasNext()) {
         answer.append(',');
         if (answer.length() > 524288) {
            answer.append('.').append('.').append('.');
            break;
         }

         answer.append((long)i.next() & 4294967295L);
      }

      answer.append('}');
      return answer.toString();
   }

   public void trim() {
      this.highLowContainer.trim();
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.highLowContainer.writeExternal(out);
   }

   public void xor(RoaringBitmap x2) {
      if (x2 == this) {
         this.clear();
      } else {
         int pos1 = 0;
         int pos2 = 0;
         int length1 = this.highLowContainer.size();
         int length2 = x2.highLowContainer.size();
         if (pos1 < length1 && pos2 < length2) {
            char s1 = this.highLowContainer.getKeyAtIndex(pos1);
            char s2 = x2.highLowContainer.getKeyAtIndex(pos2);

            label47:
            while(true) {
               while(s1 != s2) {
                  if (s1 < s2) {
                     ++pos1;
                     if (pos1 == length1) {
                        break label47;
                     }

                     s1 = this.highLowContainer.getKeyAtIndex(pos1);
                  } else {
                     this.highLowContainer.insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label47;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               Container c = this.highLowContainer.getContainerAtIndex(pos1).ixor(x2.highLowContainer.getContainerAtIndex(pos2));
               if (!c.isEmpty()) {
                  this.highLowContainer.setContainerAtIndex(pos1, c);
                  ++pos1;
               } else {
                  this.highLowContainer.removeAtIndex(pos1);
                  --length1;
               }

               ++pos2;
               if (pos1 == length1 || pos2 == length2) {
                  break;
               }

               s1 = this.highLowContainer.getKeyAtIndex(pos1);
               s2 = x2.highLowContainer.getKeyAtIndex(pos2);
            }
         }

         if (pos1 == length1) {
            this.highLowContainer.appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   public static RoaringBitmap xor(Iterator bitmaps, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      Iterator<RoaringBitmap> bitmapsIterator = selectRangeWithoutCopy(bitmaps, rangeStart, rangeEnd);
      return FastAggregation.xor(bitmapsIterator);
   }

   /** @deprecated */
   @Deprecated
   public static RoaringBitmap xor(Iterator bitmaps, int rangeStart, int rangeEnd) {
      return xor(bitmaps, (long)rangeStart, (long)rangeEnd);
   }

   public int getContainerCount() {
      return this.highLowContainer.size();
   }

   private class RoaringIntIterator implements PeekableIntIterator {
      private final char startingContainerIndex = this.findStartingContainerIndex();
      private int hs = 0;
      private PeekableCharIterator iter;
      private int pos = 0;

      public RoaringIntIterator() {
         this.nextContainer();
      }

      char findStartingContainerIndex() {
         return '\u0000';
      }

      public PeekableIntIterator clone() {
         try {
            RoaringIntIterator x = (RoaringIntIterator)super.clone();
            if (this.iter != null) {
               x.iter = this.iter.clone();
            }

            return x;
         } catch (CloneNotSupportedException var2) {
            return null;
         }
      }

      public boolean hasNext() {
         return this.pos < RoaringBitmap.this.highLowContainer.size();
      }

      public int next() {
         int x = this.iter.nextAsInt() | this.hs;
         if (!this.iter.hasNext()) {
            ++this.pos;
            this.nextContainer();
         }

         return x;
      }

      private void nextContainer() {
         int containerSize = RoaringBitmap.this.highLowContainer.size();
         if (this.pos < containerSize) {
            int index = (this.pos + this.startingContainerIndex) % containerSize;
            this.iter = RoaringBitmap.this.highLowContainer.getContainerAtIndex(index).getCharIterator();
            this.hs = RoaringBitmap.this.highLowContainer.getKeyAtIndex(index) << 16;
         }

      }

      public void advanceIfNeeded(int minval) {
         while(this.hasNext() && this.shouldAdvanceContainer(this.hs, minval)) {
            ++this.pos;
            this.nextContainer();
         }

         if (this.hasNext() && this.hs >>> 16 == minval >>> 16) {
            this.iter.advanceIfNeeded(Util.lowbits(minval));
            if (!this.iter.hasNext()) {
               ++this.pos;
               this.nextContainer();
            }
         }

      }

      boolean shouldAdvanceContainer(int hs, int minval) {
         return hs >>> 16 < minval >>> 16;
      }

      public int peekNext() {
         return this.iter.peekNext() | this.hs;
      }
   }

   private final class RoaringSignedIntIterator extends RoaringIntIterator {
      private RoaringSignedIntIterator() {
      }

      char findStartingContainerIndex() {
         char index = (char)RoaringBitmap.this.highLowContainer.advanceUntil('耀', -1);
         if (index == RoaringBitmap.this.highLowContainer.size()) {
            index = 0;
         }

         return index;
      }

      boolean shouldAdvanceContainer(int hs, int minval) {
         return hs >> 16 < minval >> 16;
      }
   }

   private final class RoaringReverseIntIterator implements IntIterator {
      int hs;
      CharIterator iter;
      int pos;

      private RoaringReverseIntIterator() {
         this.hs = 0;
         this.pos = RoaringBitmap.this.highLowContainer.size() - 1;
         this.nextContainer();
      }

      public IntIterator clone() {
         try {
            RoaringReverseIntIterator clone = (RoaringReverseIntIterator)super.clone();
            if (this.iter != null) {
               clone.iter = this.iter.clone();
            }

            return clone;
         } catch (CloneNotSupportedException var2) {
            return null;
         }
      }

      public boolean hasNext() {
         return this.pos >= 0;
      }

      public int next() {
         int x = this.iter.nextAsInt() | this.hs;
         if (!this.iter.hasNext()) {
            --this.pos;
            this.nextContainer();
         }

         return x;
      }

      private void nextContainer() {
         if (this.pos >= 0) {
            this.iter = RoaringBitmap.this.highLowContainer.getContainerAtIndex(this.pos).getReverseCharIterator();
            this.hs = RoaringBitmap.this.highLowContainer.getKeyAtIndex(this.pos) << 16;
         }

      }
   }
}
