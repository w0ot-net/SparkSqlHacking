package org.roaringbitmap.buffer;

import java.io.DataInput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.roaringbitmap.AppendableStorage;
import org.roaringbitmap.BitmapDataProvider;
import org.roaringbitmap.CharIterator;
import org.roaringbitmap.ContainerPointer;
import org.roaringbitmap.InvalidRoaringFormat;
import org.roaringbitmap.RoaringBitmap;

public class MutableRoaringBitmap extends ImmutableRoaringBitmap implements Cloneable, Serializable, Iterable, Externalizable, BitmapDataProvider, AppendableStorage {
   private static final long serialVersionUID = 4L;

   public static MutableRoaringBitmap addOffset(ImmutableRoaringBitmap x, long offset) {
      long container_offset_long = offset < 0L ? (offset - 65536L + 1L) / 65536L : offset / 65536L;
      if (container_offset_long >= -65536L && container_offset_long < 65536L) {
         int container_offset = (int)container_offset_long;
         int in_container_offset = (int)(offset - container_offset_long * 65536L);
         if (in_container_offset == 0) {
            MutableRoaringBitmap answer = new MutableRoaringBitmap();

            for(int pos = 0; pos < x.highLowContainer.size(); ++pos) {
               int key = x.highLowContainer.getKeyAtIndex(pos);
               key += container_offset;
               answer.getMappeableRoaringArray().append((char)key, x.highLowContainer.getContainerAtIndex(pos).clone());
            }

            return answer;
         } else {
            MutableRoaringBitmap answer = new MutableRoaringBitmap();

            for(int pos = 0; pos < x.highLowContainer.size(); ++pos) {
               int key = x.highLowContainer.getKeyAtIndex(pos);
               key += container_offset;
               if (key + 1 >= 0 && key <= 65535) {
                  MappeableContainer c = x.highLowContainer.getContainerAtIndex(pos);
                  MappeableContainer[] offsetted = BufferUtil.addOffset(c, (char)in_container_offset);
                  boolean keyok = key >= 0;
                  boolean keypok = key + 1 <= 65535;
                  if (!offsetted[0].isEmpty() && keyok) {
                     int current_size = answer.highLowContainer.size();
                     int lastkey = 0;
                     if (current_size > 0) {
                        lastkey = answer.highLowContainer.getKeyAtIndex(current_size - 1);
                     }

                     if (current_size > 0 && lastkey == key) {
                        MappeableContainer prev = answer.highLowContainer.getContainerAtIndex(current_size - 1);
                        MappeableContainer orresult = prev.ior(offsetted[0]);
                        answer.getMappeableRoaringArray().setContainerAtIndex(current_size - 1, orresult);
                     } else {
                        answer.getMappeableRoaringArray().append((char)key, offsetted[0]);
                     }
                  }

                  if (!offsetted[1].isEmpty() && keypok) {
                     answer.getMappeableRoaringArray().append((char)(key + 1), offsetted[1]);
                  }
               }
            }

            answer.repairAfterLazy();
            return answer;
         }
      } else {
         return new MutableRoaringBitmap();
      }
   }

   public static MutableRoaringBitmap add(MutableRoaringBitmap rb, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart >= rangeEnd) {
         return rb.clone();
      } else {
         int hbStart = BufferUtil.highbits(rangeStart);
         int lbStart = BufferUtil.lowbits(rangeStart);
         int hbLast = BufferUtil.highbits(rangeEnd - 1L);
         int lbLast = BufferUtil.lowbits(rangeEnd - 1L);
         MutableRoaringBitmap answer = new MutableRoaringBitmap();
         ((MutableRoaringArray)answer.highLowContainer).appendCopiesUntil(rb.highLowContainer, (char)hbStart);
         if (hbStart == hbLast) {
            int i = rb.highLowContainer.getIndex((char)hbStart);
            MappeableContainer c = i >= 0 ? rb.highLowContainer.getContainerAtIndex(i).add(lbStart, lbLast + 1) : MappeableContainer.rangeOfOnes(lbStart, lbLast + 1);
            ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c);
            ((MutableRoaringArray)answer.highLowContainer).appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         } else {
            int ifirst = rb.highLowContainer.getIndex((char)hbStart);
            int ilast = rb.highLowContainer.getIndex((char)hbLast);
            MappeableContainer c = ifirst >= 0 ? rb.highLowContainer.getContainerAtIndex(ifirst).add(lbStart, BufferUtil.maxLowBitAsInteger() + 1) : MappeableContainer.rangeOfOnes(lbStart, BufferUtil.maxLowBitAsInteger() + 1);
            ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c);

            for(int hb = hbStart + 1; hb < hbLast; ++hb) {
               MappeableContainer c = MappeableContainer.rangeOfOnes(0, BufferUtil.maxLowBitAsInteger() + 1);
               ((MutableRoaringArray)answer.highLowContainer).append((char)hb, c);
            }

            c = ilast >= 0 ? rb.highLowContainer.getContainerAtIndex(ilast).add(0, lbLast + 1) : MappeableContainer.rangeOfOnes(0, lbLast + 1);
            ((MutableRoaringArray)answer.highLowContainer).append((char)hbLast, c);
            ((MutableRoaringArray)answer.highLowContainer).appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap add(MutableRoaringBitmap rb, int rangeStart, int rangeEnd) {
      return rangeStart >= 0 ? add(rb, (long)rangeStart, (long)rangeEnd) : add(rb, (long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   public static MutableRoaringBitmap and(MutableRoaringBitmap x1, MutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            MappeableContainer c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            MappeableContainer c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            MappeableContainer c = c1.and(c2);
            if (!c.isEmpty()) {
               answer.getMappeableRoaringArray().append(s1, c);
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

   public static MutableRoaringBitmap andNot(MutableRoaringBitmap x1, MutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      int pos1 = 0;
      int pos2 = 0;
      int length1 = x1.highLowContainer.size();
      int length2 = x2.highLowContainer.size();

      while(pos1 < length1 && pos2 < length2) {
         char s1 = x1.highLowContainer.getKeyAtIndex(pos1);
         char s2 = x2.highLowContainer.getKeyAtIndex(pos2);
         if (s1 == s2) {
            MappeableContainer c1 = x1.highLowContainer.getContainerAtIndex(pos1);
            MappeableContainer c2 = x2.highLowContainer.getContainerAtIndex(pos2);
            MappeableContainer c = c1.andNot(c2);
            if (!c.isEmpty()) {
               answer.getMappeableRoaringArray().append(s1, c);
            }

            ++pos1;
            ++pos2;
         } else if (s1 < s2) {
            int nextPos1 = x1.highLowContainer.advanceUntil(s2, pos1);
            answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer, pos1, nextPos1);
            pos1 = nextPos1;
         } else {
            pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
         }
      }

      if (pos2 == length2) {
         answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer, pos1, length1);
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
               MutableRoaringArray mra = (MutableRoaringArray)this.highLowContainer;
               MappeableContainer currentcont = null;
               int j = 0;
               int val = dat[j + offset];
               char currenthb = BufferUtil.highbits(val);
               int currentcontainerindex = this.highLowContainer.getIndex(currenthb);
               if (currentcontainerindex >= 0) {
                  currentcont = this.highLowContainer.getContainerAtIndex(currentcontainerindex);
                  MappeableContainer newcont = currentcont.add(BufferUtil.lowbits(val));
                  if (newcont != currentcont) {
                     mra.setContainerAtIndex(currentcontainerindex, newcont);
                     currentcont = newcont;
                  }
               } else {
                  currentcontainerindex = -currentcontainerindex - 1;
                  MappeableArrayContainer newac = new MappeableArrayContainer();
                  currentcont = newac.add(BufferUtil.lowbits(val));
                  mra.insertNewKeyValueAt(currentcontainerindex, currenthb, currentcont);
               }

               ++j;

               for(; j < n; ++j) {
                  val = dat[j + offset];
                  char newhb = BufferUtil.highbits(val);
                  if (currenthb == newhb) {
                     MappeableContainer newcont = currentcont.add(BufferUtil.lowbits(val));
                     if (newcont != currentcont) {
                        mra.setContainerAtIndex(currentcontainerindex, newcont);
                        currentcont = newcont;
                     }
                  } else {
                     currenthb = newhb;
                     currentcontainerindex = this.highLowContainer.getIndex(newhb);
                     if (currentcontainerindex >= 0) {
                        currentcont = this.highLowContainer.getContainerAtIndex(currentcontainerindex);
                        MappeableContainer newcont = currentcont.add(BufferUtil.lowbits(val));
                        if (newcont != currentcont) {
                           mra.setContainerAtIndex(currentcontainerindex, newcont);
                           currentcont = newcont;
                        }
                     } else {
                        currentcontainerindex = -currentcontainerindex - 1;
                        MappeableArrayContainer newac = new MappeableArrayContainer();
                        currentcont = newac.add(BufferUtil.lowbits(val));
                        mra.insertNewKeyValueAt(currentcontainerindex, newhb, currentcont);
                     }
                  }
               }

            }
         }
      } else {
         throw new IllegalArgumentException("Negative values do not make sense.");
      }
   }

   public static MutableRoaringBitmap bitmapOf(int... dat) {
      MutableRoaringBitmap ans = new MutableRoaringBitmap();
      ans.add(dat);
      return ans;
   }

   public static MutableRoaringBitmap bitmapOfRange(long min, long max) {
      rangeSanityCheck(min, max);
      if (min >= max) {
         return new MutableRoaringBitmap();
      } else {
         int hbStart = BufferUtil.highbits(min);
         int lbStart = BufferUtil.lowbits(min);
         int hbLast = BufferUtil.highbits(max - 1L);
         int lbLast = BufferUtil.lowbits(max - 1L);
         MutableRoaringArray array = new MutableRoaringArray(hbLast - hbStart + 1);
         MutableRoaringBitmap bitmap = new MutableRoaringBitmap(array);
         int firstEnd = hbStart < hbLast ? 65536 : lbLast + 1;
         MappeableContainer firstContainer = MappeableContainer.rangeOfOnes(lbStart, firstEnd);
         bitmap.append((char)hbStart, firstContainer);
         if (hbStart < hbLast) {
            for(int i = hbStart + 1; i < hbLast; ++i) {
               MappeableContainer runContainer = MappeableContainer.rangeOfOnes(0, 65536);
               bitmap.append((char)i, runContainer);
            }

            MappeableContainer lastContainer = MappeableContainer.rangeOfOnes(0, lbLast + 1);
            bitmap.append((char)hbLast, lastContainer);
         }

         return bitmap;
      }
   }

   protected static void rangeSanityCheck(long rangeStart, long rangeEnd) {
      if (rangeStart >= 0L && rangeStart <= 4294967295L) {
         if (rangeEnd > 4294967296L || rangeEnd < 0L) {
            throw new IllegalArgumentException("rangeEnd=" + rangeEnd + " should be in [0, 0xffffffff + 1]");
         }
      } else {
         throw new IllegalArgumentException("rangeStart=" + rangeStart + " should be in [0, 0xffffffff]");
      }
   }

   public static MutableRoaringBitmap flip(MutableRoaringBitmap bm, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart >= rangeEnd) {
         return bm.clone();
      } else {
         MutableRoaringBitmap answer = new MutableRoaringBitmap();
         int hbStart = BufferUtil.highbits(rangeStart);
         int lbStart = BufferUtil.lowbits(rangeStart);
         int hbLast = BufferUtil.highbits(rangeEnd - 1L);
         int lbLast = BufferUtil.lowbits(rangeEnd - 1L);
         answer.getMappeableRoaringArray().appendCopiesUntil(bm.highLowContainer, (char)hbStart);

         for(int hb = hbStart; hb <= hbLast; ++hb) {
            int containerStart = hb == hbStart ? lbStart : 0;
            int containerLast = hb == hbLast ? lbLast : BufferUtil.maxLowBitAsInteger();
            int i = bm.highLowContainer.getIndex((char)hb);
            int j = answer.highLowContainer.getIndex((char)hb);

            assert j < 0;

            if (i >= 0) {
               MappeableContainer c = bm.highLowContainer.getContainerAtIndex(i).not(containerStart, containerLast + 1);
               if (!c.isEmpty()) {
                  answer.getMappeableRoaringArray().insertNewKeyValueAt(-j - 1, (char)hb, c);
               }
            } else {
               answer.getMappeableRoaringArray().insertNewKeyValueAt(-j - 1, (char)hb, MappeableContainer.rangeOfOnes(containerStart, containerLast + 1));
            }
         }

         answer.getMappeableRoaringArray().appendCopiesAfter(bm.highLowContainer, (char)hbLast);
         return answer;
      }
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap flip(MutableRoaringBitmap rb, int rangeStart, int rangeEnd) {
      return rangeStart >= 0 ? flip(rb, (long)rangeStart, (long)rangeEnd) : flip(rb, (long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   protected static MutableRoaringBitmap lazyorfromlazyinputs(MutableRoaringBitmap x1, MutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
      MappeableContainerPointer i1 = x1.highLowContainer.getContainerPointer();
      MappeableContainerPointer i2 = x2.highLowContainer.getContainerPointer();
      if (i1.hasContainer() && i2.hasContainer()) {
         label55:
         do {
            while(i1.key() != i2.key()) {
               if (i1.key() < i2.key()) {
                  answer.getMappeableRoaringArray().append(i1.key(), i1.getContainer());
                  i1.advance();
                  if (!i1.hasContainer()) {
                     break label55;
                  }
               } else {
                  answer.getMappeableRoaringArray().append(i2.key(), i2.getContainer());
                  i2.advance();
                  if (!i2.hasContainer()) {
                     break label55;
                  }
               }
            }

            MappeableContainer c1 = i1.getContainer();
            MappeableContainer c2 = i2.getContainer();
            if (c2 instanceof MappeableBitmapContainer && !(c1 instanceof MappeableBitmapContainer)) {
               MappeableContainer tmp = c1;
               c1 = c2;
               c2 = tmp;
            }

            answer.getMappeableRoaringArray().append(i1.key(), c1.lazyIOR(c2));
            i1.advance();
            i2.advance();
         } while(i1.hasContainer() && i2.hasContainer());
      }

      if (!i1.hasContainer()) {
         while(i2.hasContainer()) {
            answer.getMappeableRoaringArray().append(i2.key(), i2.getContainer());
            i2.advance();
         }
      } else if (!i2.hasContainer()) {
         while(i1.hasContainer()) {
            answer.getMappeableRoaringArray().append(i1.key(), i1.getContainer());
            i1.advance();
         }
      }

      return answer;
   }

   public static MutableRoaringBitmap or(ImmutableRoaringBitmap... bitmaps) {
      return BufferFastAggregation.or(bitmaps);
   }

   public static MutableRoaringBitmap or(MutableRoaringBitmap x1, MutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
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
                  answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer.getKeyAtIndex(pos1), x1.highLowContainer.getContainerAtIndex(pos1));
                  ++pos1;
                  if (pos1 == length1) {
                     break label42;
                  }

                  s1 = x1.highLowContainer.getKeyAtIndex(pos1);
               } else {
                  answer.getMappeableRoaringArray().appendCopy(x2.highLowContainer.getKeyAtIndex(pos2), x2.highLowContainer.getContainerAtIndex(pos2));
                  ++pos2;
                  if (pos2 == length2) {
                     break label42;
                  }

                  s2 = x2.highLowContainer.getKeyAtIndex(pos2);
               }
            }

            answer.getMappeableRoaringArray().append(s1, x1.highLowContainer.getContainerAtIndex(pos1).or(x2.highLowContainer.getContainerAtIndex(pos2)));
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
         answer.getMappeableRoaringArray().appendCopy(x2.highLowContainer, pos2, length2);
      } else if (pos2 == length2) {
         answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   public static MutableRoaringBitmap remove(MutableRoaringBitmap rb, long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart >= rangeEnd) {
         return rb.clone();
      } else {
         int hbStart = BufferUtil.highbits(rangeStart);
         int lbStart = BufferUtil.lowbits(rangeStart);
         int hbLast = BufferUtil.highbits(rangeEnd - 1L);
         int lbLast = BufferUtil.lowbits(rangeEnd - 1L);
         MutableRoaringBitmap answer = new MutableRoaringBitmap();
         ((MutableRoaringArray)answer.highLowContainer).appendCopiesUntil(rb.highLowContainer, (char)hbStart);
         if (hbStart == hbLast) {
            int i = rb.highLowContainer.getIndex((char)hbStart);
            if (i >= 0) {
               MappeableContainer c = rb.highLowContainer.getContainerAtIndex(i).remove(lbStart, lbLast + 1);
               if (!c.isEmpty()) {
                  ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c);
               }
            }

            ((MutableRoaringArray)answer.highLowContainer).appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         } else {
            int ifirst = rb.highLowContainer.getIndex((char)hbStart);
            int ilast = rb.highLowContainer.getIndex((char)hbLast);
            if (ifirst >= 0 && lbStart != 0) {
               MappeableContainer c = rb.highLowContainer.getContainerAtIndex(ifirst).remove(lbStart, BufferUtil.maxLowBitAsInteger() + 1);
               if (!c.isEmpty()) {
                  ((MutableRoaringArray)answer.highLowContainer).append((char)hbStart, c);
               }
            }

            if (ilast >= 0 && lbLast != BufferUtil.maxLowBitAsInteger()) {
               MappeableContainer c = rb.highLowContainer.getContainerAtIndex(ilast).remove(0, lbLast + 1);
               if (!c.isEmpty()) {
                  ((MutableRoaringArray)answer.highLowContainer).append((char)hbLast, c);
               }
            }

            ((MutableRoaringArray)answer.highLowContainer).appendCopiesAfter(rb.highLowContainer, (char)hbLast);
            return answer;
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static MutableRoaringBitmap remove(MutableRoaringBitmap rb, int rangeStart, int rangeEnd) {
      return rangeStart >= 0 ? remove(rb, (long)rangeStart, (long)rangeEnd) : remove(rb, (long)rangeStart & 4294967295L, (long)rangeEnd & 4294967295L);
   }

   public static MutableRoaringBitmap xor(MutableRoaringBitmap x1, MutableRoaringBitmap x2) {
      MutableRoaringBitmap answer = new MutableRoaringBitmap();
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
                  answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer.getKeyAtIndex(pos1), x1.highLowContainer.getContainerAtIndex(pos1));
                  ++pos1;
                  if (pos1 == length1) {
                     break label48;
                  }

                  s1 = x1.highLowContainer.getKeyAtIndex(pos1);
               } else if (s1 - s2 > 0) {
                  answer.getMappeableRoaringArray().appendCopy(x2.highLowContainer.getKeyAtIndex(pos2), x2.highLowContainer.getContainerAtIndex(pos2));
                  ++pos2;
                  if (pos2 == length2) {
                     break label48;
                  }

                  s2 = x2.highLowContainer.getKeyAtIndex(pos2);
               }
            }

            MappeableContainer c = x1.highLowContainer.getContainerAtIndex(pos1).xor(x2.highLowContainer.getContainerAtIndex(pos2));
            if (!c.isEmpty()) {
               answer.getMappeableRoaringArray().append(s1, c);
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
         answer.getMappeableRoaringArray().appendCopy(x2.highLowContainer, pos2, length2);
      } else if (pos2 == length2) {
         answer.getMappeableRoaringArray().appendCopy(x1.highLowContainer, pos1, length1);
      }

      return answer;
   }

   public MutableRoaringBitmap() {
      this(new MutableRoaringArray());
   }

   public MutableRoaringBitmap(MutableRoaringArray highLowContainer) {
      this.highLowContainer = highLowContainer;
   }

   public MutableRoaringBitmap(RoaringBitmap rb) {
      this.highLowContainer = new MutableRoaringArray();
      ContainerPointer cp = rb.getContainerPointer();

      while(cp.getContainer() != null) {
         ((MutableRoaringArray)this.highLowContainer).append(cp.key(), cp.getContainer().toMappeableContainer());
         cp.advance();
      }

   }

   public void add(int x) {
      char hb = BufferUtil.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         this.getMappeableRoaringArray().setContainerAtIndex(i, this.highLowContainer.getContainerAtIndex(i).add(BufferUtil.lowbits(x)));
      } else {
         MappeableArrayContainer newac = new MappeableArrayContainer();
         this.getMappeableRoaringArray().insertNewKeyValueAt(-i - 1, hb, newac.add(BufferUtil.lowbits(x)));
      }

   }

   public void add(long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart < rangeEnd) {
         int hbStart = BufferUtil.highbits(rangeStart);
         int lbStart = BufferUtil.lowbits(rangeStart);
         int hbLast = BufferUtil.highbits(rangeEnd - 1L);
         int lbLast = BufferUtil.lowbits(rangeEnd - 1L);

         for(int hb = hbStart; hb <= hbLast; ++hb) {
            int containerStart = hb == hbStart ? lbStart : 0;
            int containerLast = hb == hbLast ? lbLast : BufferUtil.maxLowBitAsInteger();
            int i = this.highLowContainer.getIndex((char)hb);
            if (i >= 0) {
               MappeableContainer c = this.highLowContainer.getContainerAtIndex(i).iadd(containerStart, containerLast + 1);
               ((MutableRoaringArray)this.highLowContainer).setContainerAtIndex(i, c);
            } else {
               ((MutableRoaringArray)this.highLowContainer).insertNewKeyValueAt(-i - 1, (char)hb, MappeableContainer.rangeOfOnes(containerStart, containerLast + 1));
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

   public void and(ImmutableRoaringBitmap array) {
      if (array != this) {
         int pos1 = 0;
         int pos2 = 0;
         int intersectionSize = 0;
         int length1 = this.highLowContainer.size();
         int length2 = array.highLowContainer.size();

         while(pos1 < length1 && pos2 < length2) {
            char s1 = this.highLowContainer.getKeyAtIndex(pos1);
            char s2 = array.highLowContainer.getKeyAtIndex(pos2);
            if (s1 == s2) {
               MappeableContainer c1 = this.highLowContainer.getContainerAtIndex(pos1);
               MappeableContainer c2 = array.highLowContainer.getContainerAtIndex(pos2);
               MappeableContainer c = c1.iand(c2);
               if (!c.isEmpty()) {
                  this.getMappeableRoaringArray().replaceKeyAndContainerAtIndex(intersectionSize++, s1, c);
               }

               ++pos1;
               ++pos2;
            } else if (s1 < s2) {
               pos1 = this.highLowContainer.advanceUntil(s2, pos1);
            } else {
               pos2 = array.highLowContainer.advanceUntil(s1, pos2);
            }
         }

         this.getMappeableRoaringArray().resize(intersectionSize);
      }
   }

   public void andNot(ImmutableRoaringBitmap x2) {
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
               MappeableContainer c1 = this.highLowContainer.getContainerAtIndex(pos1);
               MappeableContainer c2 = x2.highLowContainer.getContainerAtIndex(pos2);
               MappeableContainer c = c1.iandNot(c2);
               if (!c.isEmpty()) {
                  this.getMappeableRoaringArray().replaceKeyAndContainerAtIndex(intersectionSize++, s1, c);
               }

               ++pos1;
               ++pos2;
            } else if (s1 < s2) {
               if (pos1 != intersectionSize) {
                  MappeableContainer c1 = this.highLowContainer.getContainerAtIndex(pos1);
                  this.getMappeableRoaringArray().replaceKeyAndContainerAtIndex(intersectionSize, s1, c1);
               }

               ++intersectionSize;
               ++pos1;
            } else {
               pos2 = x2.highLowContainer.advanceUntil(s1, pos2);
            }
         }

         if (pos1 < length1) {
            this.getMappeableRoaringArray().copyRange(pos1, length1, intersectionSize);
            intersectionSize += length1 - pos1;
         }

         this.getMappeableRoaringArray().resize(intersectionSize);
      }
   }

   public void orNot(ImmutableRoaringBitmap other, long rangeEnd) {
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

         for(int i = this.highLowContainer.size() - 1; i >= 0 && this.highLowContainer.getKeyAtIndex(i) > maxKey; --i) {
            ++remainder;
         }

         int correction = 0;

         for(int i = 0; i < other.highLowContainer.size() - remainder; ++i) {
            correction += other.highLowContainer.getContainerAtIndex(i).isFull() ? 1 : 0;
            if (other.highLowContainer.getKeyAtIndex(i) >= maxKey) {
               break;
            }
         }

         int maxSize = Math.min(maxKey + 1 + remainder - correction + this.highLowContainer.size(), 65536);
         if (maxSize != 0) {
            char[] newKeys = new char[maxSize];
            MappeableContainer[] newValues = new MappeableContainer[maxSize];

            for(int key = 0; key <= maxKey && size < maxSize; ++key) {
               if (key == s1 && key == s2) {
                  newValues[size] = this.highLowContainer.getContainerAtIndex(pos1).iorNot(other.highLowContainer.getContainerAtIndex(pos2), key == maxKey ? lastRun : 65536);
                  ++pos1;
                  ++pos2;
                  s1 = pos1 < length1 ? this.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
                  s2 = pos2 < length2 ? other.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
               } else if (key == s1) {
                  newValues[size] = (MappeableContainer)(key == maxKey ? this.highLowContainer.getContainerAtIndex(pos1).ior(MappeableRunContainer.rangeOfOnes(0, lastRun)) : MappeableRunContainer.full());
                  ++pos1;
                  s1 = pos1 < length1 ? this.highLowContainer.getKeyAtIndex(pos1) : maxKey + 1;
               } else if (key == s2) {
                  newValues[size] = other.highLowContainer.getContainerAtIndex(pos2).not(0, key == maxKey ? lastRun : 65536);
                  ++pos2;
                  s2 = pos2 < length2 ? other.highLowContainer.getKeyAtIndex(pos2) : maxKey + 1;
               } else {
                  newValues[size] = (MappeableContainer)(key == maxKey ? MappeableRunContainer.rangeOfOnes(0, lastRun) : MappeableRunContainer.full());
               }

               if (newValues[size].isEmpty()) {
                  newValues[size] = null;
               } else {
                  newKeys[size] = (char)key;
                  ++size;
               }
            }

            if (remainder > 0) {
               System.arraycopy(((MutableRoaringArray)this.highLowContainer).keys, this.highLowContainer.size() - remainder, newKeys, size, remainder);
               System.arraycopy(((MutableRoaringArray)this.highLowContainer).values, this.highLowContainer.size() - remainder, newValues, size, remainder);
            }

            ((MutableRoaringArray)this.highLowContainer).keys = newKeys;
            ((MutableRoaringArray)this.highLowContainer).values = newValues;
            ((MutableRoaringArray)this.highLowContainer).size = size + remainder;
         }
      }
   }

   public boolean checkedAdd(int x) {
      char hb = BufferUtil.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         MappeableContainer C = this.highLowContainer.getContainerAtIndex(i);
         char lowX = BufferUtil.lowbits(x);
         if (C instanceof MappeableRunContainer) {
            if (!C.contains(lowX)) {
               MappeableContainer newCont = C.add(lowX);
               this.getMappeableRoaringArray().setContainerAtIndex(i, newCont);
               return true;
            } else {
               return false;
            }
         } else {
            int oldCard = C.getCardinality();
            MappeableContainer newCont = C.add(lowX);
            this.getMappeableRoaringArray().setContainerAtIndex(i, newCont);
            return newCont.getCardinality() > oldCard;
         }
      } else {
         MappeableArrayContainer newac = new MappeableArrayContainer();
         this.getMappeableRoaringArray().insertNewKeyValueAt(-i - 1, hb, newac.add(BufferUtil.lowbits(x)));
         return true;
      }
   }

   public boolean checkedRemove(int x) {
      char hb = BufferUtil.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i < 0) {
         return false;
      } else {
         MappeableContainer C = this.highLowContainer.getContainerAtIndex(i);
         int oldcard = C.getCardinality();
         C.remove(BufferUtil.lowbits(x));
         int newcard = C.getCardinality();
         if (newcard == oldcard) {
            return false;
         } else {
            if (newcard > 0) {
               ((MutableRoaringArray)this.highLowContainer).setContainerAtIndex(i, C);
            } else {
               ((MutableRoaringArray)this.highLowContainer).removeAtIndex(i);
            }

            return true;
         }
      }
   }

   public void clear() {
      this.highLowContainer = new MutableRoaringArray();
   }

   public MutableRoaringBitmap clone() {
      MutableRoaringBitmap x = (MutableRoaringBitmap)super.clone();
      x.highLowContainer = this.highLowContainer.clone();
      return x;
   }

   public void deserialize(DataInput in) throws IOException {
      try {
         this.getMappeableRoaringArray().deserialize(in);
      } catch (InvalidRoaringFormat cookie) {
         throw cookie.toIOException();
      }
   }

   public void deserialize(ByteBuffer buffer) throws IOException {
      try {
         this.getMappeableRoaringArray().deserialize(buffer);
      } catch (InvalidRoaringFormat cookie) {
         throw cookie.toIOException();
      }
   }

   public void flip(int x) {
      char hb = BufferUtil.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         MappeableContainer c = this.highLowContainer.getContainerAtIndex(i);
         c = c.flip(BufferUtil.lowbits(x));
         if (!c.isEmpty()) {
            ((MutableRoaringArray)this.highLowContainer).setContainerAtIndex(i, c);
         } else {
            ((MutableRoaringArray)this.highLowContainer).removeAtIndex(i);
         }
      } else {
         MappeableArrayContainer newac = new MappeableArrayContainer();
         ((MutableRoaringArray)this.highLowContainer).insertNewKeyValueAt(-i - 1, hb, newac.add(BufferUtil.lowbits(x)));
      }

   }

   public void flip(long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart < rangeEnd) {
         int hbStart = BufferUtil.highbits(rangeStart);
         int lbStart = BufferUtil.lowbits(rangeStart);
         int hbLast = BufferUtil.highbits(rangeEnd - 1L);
         int lbLast = BufferUtil.lowbits(rangeEnd - 1L);

         for(int hb = hbStart; hb <= hbLast; ++hb) {
            int containerStart = hb == hbStart ? lbStart : 0;
            int containerLast = hb == hbLast ? lbLast : BufferUtil.maxLowBitAsInteger();
            int i = this.highLowContainer.getIndex((char)hb);
            if (i >= 0) {
               MappeableContainer c = this.highLowContainer.getContainerAtIndex(i).inot(containerStart, containerLast + 1);
               if (!c.isEmpty()) {
                  this.getMappeableRoaringArray().setContainerAtIndex(i, c);
               } else {
                  this.getMappeableRoaringArray().removeAtIndex(i);
               }
            } else {
               this.getMappeableRoaringArray().insertNewKeyValueAt(-i - 1, (char)hb, MappeableContainer.rangeOfOnes(containerStart, containerLast + 1));
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

   public MutableRoaringArray getMappeableRoaringArray() {
      return (MutableRoaringArray)this.highLowContainer;
   }

   public Iterator iterator() {
      return (new Iterator() {
         private int hs = 0;
         private CharIterator iter;
         private int pos = 0;
         private int x;

         public boolean hasNext() {
            return this.pos < MutableRoaringBitmap.this.highLowContainer.size();
         }

         private Iterator init() {
            if (this.pos < MutableRoaringBitmap.this.highLowContainer.size()) {
               this.iter = MutableRoaringBitmap.this.highLowContainer.getContainerAtIndex(this.pos).getCharIterator();
               this.hs = MutableRoaringBitmap.this.highLowContainer.getKeyAtIndex(this.pos) << 16;
            }

            return this;
         }

         public Integer next() {
            this.x = this.iter.next() | this.hs;
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

   protected void lazyor(ImmutableRoaringBitmap x2) {
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
                     this.getMappeableRoaringArray().insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label42;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               this.getMappeableRoaringArray().setContainerAtIndex(pos1, this.highLowContainer.getContainerAtIndex(pos1).lazyIOR(x2.highLowContainer.getContainerAtIndex(pos2)));
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
            this.getMappeableRoaringArray().appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   protected void naivelazyor(ImmutableRoaringBitmap x2) {
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
                     this.getMappeableRoaringArray().insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label42;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               MappeableBitmapContainer c1 = this.highLowContainer.getContainerAtIndex(pos1).toBitmapContainer();
               this.getMappeableRoaringArray().setContainerAtIndex(pos1, c1.lazyIOR(x2.highLowContainer.getContainerAtIndex(pos2)));
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
            this.getMappeableRoaringArray().appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   public void or(ImmutableRoaringBitmap x2) {
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
                     this.getMappeableRoaringArray().insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label42;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               this.getMappeableRoaringArray().setContainerAtIndex(pos1, this.highLowContainer.getContainerAtIndex(pos1).ior(x2.highLowContainer.getContainerAtIndex(pos2)));
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
            this.getMappeableRoaringArray().appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
      this.getMappeableRoaringArray().readExternal(in);
   }

   public void remove(int x) {
      char hb = BufferUtil.highbits(x);
      int i = this.highLowContainer.getIndex(hb);
      if (i >= 0) {
         this.getMappeableRoaringArray().setContainerAtIndex(i, this.highLowContainer.getContainerAtIndex(i).remove(BufferUtil.lowbits(x)));
         if (this.highLowContainer.getContainerAtIndex(i).isEmpty()) {
            this.getMappeableRoaringArray().removeAtIndex(i);
         }

      }
   }

   public void remove(long rangeStart, long rangeEnd) {
      rangeSanityCheck(rangeStart, rangeEnd);
      if (rangeStart < rangeEnd) {
         int hbStart = BufferUtil.highbits(rangeStart);
         int lbStart = BufferUtil.lowbits(rangeStart);
         int hbLast = BufferUtil.highbits(rangeEnd - 1L);
         int lbLast = BufferUtil.lowbits(rangeEnd - 1L);
         if (hbStart == hbLast) {
            int i = this.highLowContainer.getIndex((char)hbStart);
            if (i >= 0) {
               MappeableContainer c = this.highLowContainer.getContainerAtIndex(i).iremove(lbStart, lbLast + 1);
               if (!c.isEmpty()) {
                  ((MutableRoaringArray)this.highLowContainer).setContainerAtIndex(i, c);
               } else {
                  ((MutableRoaringArray)this.highLowContainer).removeAtIndex(i);
               }

            }
         } else {
            int ifirst = this.highLowContainer.getIndex((char)hbStart);
            int ilast = this.highLowContainer.getIndex((char)hbLast);
            if (ifirst >= 0) {
               if (lbStart != 0) {
                  MappeableContainer c = this.highLowContainer.getContainerAtIndex(ifirst).iremove(lbStart, BufferUtil.maxLowBitAsInteger() + 1);
                  if (!c.isEmpty()) {
                     ((MutableRoaringArray)this.highLowContainer).setContainerAtIndex(ifirst, c);
                     ++ifirst;
                  }
               }
            } else {
               ifirst = -ifirst - 1;
            }

            if (ilast >= 0) {
               if (lbLast != BufferUtil.maxLowBitAsInteger()) {
                  MappeableContainer c = this.highLowContainer.getContainerAtIndex(ilast).iremove(0, lbLast + 1);
                  if (!c.isEmpty()) {
                     ((MutableRoaringArray)this.highLowContainer).setContainerAtIndex(ilast, c);
                  } else {
                     ++ilast;
                  }
               } else {
                  ++ilast;
               }
            } else {
               ilast = -ilast - 1;
            }

            ((MutableRoaringArray)this.highLowContainer).removeIndexRange(ifirst, ilast);
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
         MappeableContainer c = this.getMappeableRoaringArray().getContainerAtIndex(i);
         if (c instanceof MappeableRunContainer) {
            MappeableContainer mc = ((MappeableRunContainer)c).toBitmapOrArrayContainer(c.getCardinality());
            this.getMappeableRoaringArray().setContainerAtIndex(i, mc);
            answer = true;
         }
      }

      return answer;
   }

   protected void repairAfterLazy() {
      for(int k = 0; k < this.highLowContainer.size(); ++k) {
         MappeableContainer c = this.highLowContainer.getContainerAtIndex(k);
         ((MutableRoaringArray)this.highLowContainer).setContainerAtIndex(k, c.repairAfterLazy());
      }

   }

   public boolean runOptimize() {
      boolean answer = false;

      for(int i = 0; i < this.highLowContainer.size(); ++i) {
         MappeableContainer c = this.getMappeableRoaringArray().getContainerAtIndex(i).runOptimize();
         if (c instanceof MappeableRunContainer) {
            answer = true;
         }

         this.getMappeableRoaringArray().setContainerAtIndex(i, c);
      }

      return answer;
   }

   public ImmutableRoaringBitmap toImmutableRoaringBitmap() {
      return this;
   }

   public void trim() {
      this.getMappeableRoaringArray().trim();
   }

   public void writeExternal(ObjectOutput out) throws IOException {
      this.getMappeableRoaringArray().writeExternal(out);
   }

   public void xor(ImmutableRoaringBitmap x2) {
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
                     this.getMappeableRoaringArray().insertNewKeyValueAt(pos1, s2, x2.highLowContainer.getContainerAtIndex(pos2).clone());
                     ++pos1;
                     ++length1;
                     ++pos2;
                     if (pos2 == length2) {
                        break label47;
                     }

                     s2 = x2.highLowContainer.getKeyAtIndex(pos2);
                  }
               }

               MappeableContainer c = this.highLowContainer.getContainerAtIndex(pos1).ixor(x2.highLowContainer.getContainerAtIndex(pos2));
               if (!c.isEmpty()) {
                  this.getMappeableRoaringArray().setContainerAtIndex(pos1, c);
                  ++pos1;
               } else {
                  this.getMappeableRoaringArray().removeAtIndex(pos1);
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
            this.getMappeableRoaringArray().appendCopy(x2.highLowContainer, pos2, length2);
         }

      }
   }

   public static long maximumSerializedSize(int cardinality, int universe_size) {
      return RoaringBitmap.maximumSerializedSize((long)cardinality, (long)universe_size);
   }

   public void append(char key, MappeableContainer container) {
      ((MutableRoaringArray)this.highLowContainer).append(key, container);
   }
}
