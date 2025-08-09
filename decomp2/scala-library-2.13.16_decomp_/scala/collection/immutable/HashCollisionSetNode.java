package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Predef$;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ec\u0001B\u0010!\r\u001dB\u0001\"\u000f\u0001\u0003\u0006\u0004%\tA\u000f\u0005\t}\u0001\u0011\t\u0011)A\u0005w!Aq\b\u0001BC\u0002\u0013\u0005!\b\u0003\u0005A\u0001\t\u0005\t\u0015!\u0003<\u0011!\t\u0005A!a\u0001\n\u0003\u0011\u0005\u0002\u0003$\u0001\u0005\u0003\u0007I\u0011A$\t\u00115\u0003!\u0011!Q!\n\rCQA\u0014\u0001\u0005\u0002=CQ\u0001\u0016\u0001\u0005\u0002UCQa\u0018\u0001\u0005\u0002\u0001DQ!\u001a\u0001\u0005\u0002\u0019DQa\u001b\u0001\u0005\u00021DQ!\u001c\u0001\u0005\u0002iBQA\u001c\u0001\u0005\u0002=DQA\u001d\u0001\u0005\u00021DQa\u001d\u0001\u0005\u0002iBQ\u0001\u001e\u0001\u0005\u0002UDQa\u001e\u0001\u0005BaDQA\u001f\u0001\u0005\u0002iBQa\u001f\u0001\u0005\u0002qDa!!\u0004\u0001\t\u0003R\u0004bBA\b\u0001\u0011\u0005\u0011\u0011\u0003\u0005\b\u00033\u0001A\u0011IA\u000e\u0011\u001d\t9\u0003\u0001C!\u0003SAq!a\f\u0001\t\u0003\n\t\u0004C\u0004\u00026\u0001!\t%a\u000e\t\u000f\u0005e\u0002\u0001\"\u0011\u0002<!9\u0011Q\b\u0001\u0005B\u0005}\u0002bBA#\u0001\u0011\u0005\u0013q\t\u0005\b\u0003#\u0002A\u0011IA*\u0005QA\u0015m\u001d5D_2d\u0017n]5p]N+GOT8eK*\u0011\u0011EI\u0001\nS6lW\u000f^1cY\u0016T!a\t\u0013\u0002\u0015\r|G\u000e\\3di&|gNC\u0001&\u0003\u0015\u00198-\u00197b\u0007\u0001)\"\u0001K\u0018\u0014\u0005\u0001I\u0003c\u0001\u0016,[5\t\u0001%\u0003\u0002-A\t91+\u001a;O_\u0012,\u0007C\u0001\u00180\u0019\u0001!Q\u0001\r\u0001C\u0002E\u0012\u0011!Q\t\u0003eY\u0002\"a\r\u001b\u000e\u0003\u0011J!!\u000e\u0013\u0003\u000f9{G\u000f[5oOB\u00111gN\u0005\u0003q\u0011\u00121!\u00118z\u00031y'/[4j]\u0006d\u0007*Y:i+\u0005Y\u0004CA\u001a=\u0013\tiDEA\u0002J]R\fQb\u001c:jO&t\u0017\r\u001c%bg\"\u0004\u0013\u0001\u00025bg\"\fQ\u0001[1tQ\u0002\nqaY8oi\u0016tG/F\u0001D!\rQC)L\u0005\u0003\u000b\u0002\u0012aAV3di>\u0014\u0018aC2p]R,g\u000e^0%KF$\"\u0001S&\u0011\u0005MJ\u0015B\u0001&%\u0005\u0011)f.\u001b;\t\u000f13\u0011\u0011!a\u0001\u0007\u0006\u0019\u0001\u0010J\u0019\u0002\u0011\r|g\u000e^3oi\u0002\na\u0001P5oSRtD\u0003\u0002)R%N\u00032A\u000b\u0001.\u0011\u0015I\u0004\u00021\u0001<\u0011\u0015y\u0004\u00021\u0001<\u0011\u0015\t\u0005\u00021\u0001D\u0003!\u0019wN\u001c;bS:\u001cH#\u0002,Z7rk\u0006CA\u001aX\u0013\tAFEA\u0004C_>dW-\u00198\t\u000biK\u0001\u0019A\u0017\u0002\u000f\u0015dW-\\3oi\")\u0011(\u0003a\u0001w!)q(\u0003a\u0001w!)a,\u0003a\u0001w\u0005)1\u000f[5gi\u00069Q\u000f\u001d3bi\u0016$G#B\u0015bE\u000e$\u0007\"\u0002.\u000b\u0001\u0004i\u0003\"B\u001d\u000b\u0001\u0004Y\u0004\"B \u000b\u0001\u0004Y\u0004\"\u00020\u000b\u0001\u0004Y\u0014a\u0002:f[>4X\r\u001a\u000b\u0006S\u001dD\u0017N\u001b\u0005\u00065.\u0001\r!\f\u0005\u0006s-\u0001\ra\u000f\u0005\u0006\u007f-\u0001\ra\u000f\u0005\u0006=.\u0001\raO\u0001\tQ\u0006\u001chj\u001c3fgV\ta+A\u0005o_\u0012,\u0017I]5us\u00069q-\u001a;O_\u0012,GCA\u0015q\u0011\u0015\th\u00021\u0001<\u0003\u0015Ig\u000eZ3y\u0003)A\u0017m\u001d)bs2|\u0017\rZ\u0001\ra\u0006LHn\\1e\u0003JLG/_\u0001\u000bO\u0016$\b+Y=m_\u0006$GCA\u0017w\u0011\u0015\t\u0018\u00031\u0001<\u0003\u001d9W\r\u001e%bg\"$\"aO=\t\u000bE\u0014\u0002\u0019A\u001e\u0002\tML'0Z\u0001\bM>\u0014X-Y2i+\ri\u0018\u0011\u0002\u000b\u0003\u0011zDaa \u000bA\u0002\u0005\u0005\u0011!\u00014\u0011\rM\n\u0019!LA\u0004\u0013\r\t)\u0001\n\u0002\n\rVt7\r^5p]F\u00022ALA\u0005\t\u0019\tY\u0001\u0006b\u0001c\t\tQ+\u0001\rdC\u000eDW\r\u001a&bm\u0006\\U-_*fi\"\u000b7\u000f[\"pI\u0016\f\u0001b];cg\u0016$xJ\u001a\u000b\u0006-\u0006M\u0011q\u0003\u0005\u0007\u0003+1\u0002\u0019A\u0015\u0002\tQD\u0017\r\u001e\u0005\u0006=Z\u0001\raO\u0001\u000bM&dG/\u001a:J[BdG#B\u0015\u0002\u001e\u0005\r\u0002bBA\u0010/\u0001\u0007\u0011\u0011E\u0001\u0005aJ,G\rE\u00034\u0003\u0007ic\u000b\u0003\u0004\u0002&]\u0001\rAV\u0001\bM2L\u0007\u000f]3e\u0003\u0011!\u0017N\u001a4\u0015\u000b%\nY#!\f\t\r\u0005U\u0001\u00041\u0001*\u0011\u0015q\u0006\u00041\u0001<\u0003\u0019)\u0017/^1mgR\u0019a+a\r\t\r\u0005U\u0011\u00041\u00017\u0003!A\u0017m\u001d5D_\u0012,G#A\u001e\u0002\t\r|\u0007/\u001f\u000b\u0002!\u000611m\u001c8dCR$R!KA!\u0003\u0007Ba!!\u0006\u001d\u0001\u0004I\u0003\"\u00020\u001d\u0001\u0004Y\u0014a\u00044pe\u0016\f7\r[,ji\"D\u0015m\u001d5\u0015\u0007!\u000bI\u0005\u0003\u0004\u0000;\u0001\u0007\u00111\n\t\u0007g\u00055Sf\u000f%\n\u0007\u0005=CEA\u0005Gk:\u001cG/[8oe\u0005!bm\u001c:fC\u000eDw+\u001b;i\u0011\u0006\u001c\bn\u00165jY\u0016$2AVA+\u0011\u0019yh\u00041\u0001\u0002XA11'!\u0014.wY\u0003"
)
public final class HashCollisionSetNode extends SetNode {
   private final int originalHash;
   private final int hash;
   private Vector content;

   public int originalHash() {
      return this.originalHash;
   }

   public int hash() {
      return this.hash;
   }

   public Vector content() {
      return this.content;
   }

   public void content_$eq(final Vector x$1) {
      this.content = x$1;
   }

   public boolean contains(final Object element, final int originalHash, final int hash, final int shift) {
      return this.hash() == hash && this.content().contains(element);
   }

   public SetNode updated(final Object element, final int originalHash, final int hash, final int shift) {
      return this.contains(element, originalHash, hash, shift) ? this : new HashCollisionSetNode(originalHash, hash, this.content().appended(element));
   }

   public SetNode removed(final Object element, final int originalHash, final int hash, final int shift) {
      if (!this.contains(element, originalHash, hash, shift)) {
         return this;
      } else {
         Vector var10000 = this.content();
         Function1 filterNot_pred = (element0) -> BoxesRunTime.boxToBoolean($anonfun$removed$1(element, element0));
         if (var10000 == null) {
            throw null;
         } else {
            Vector filterNot_this = var10000;
            boolean filterImpl_isFlipped = true;
            int filterImpl_i = 0;
            int filterImpl_len = filterNot_this.prefix1().length;

            while(true) {
               if (filterImpl_i == filterImpl_len) {
                  if (filterNot_this instanceof BigVector) {
                     VectorBuilder filterImpl_b = new VectorBuilder();
                     filterImpl_b.initFrom(filterNot_this.prefix1());
                     BigVector var102 = (BigVector)filterNot_this;
                     Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$2;
                     BigVector filterImpl_foreachRest_this = var102;
                     int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                     for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                        VectorStatics$ var103 = VectorStatics$.MODULE$;
                        VectorInline$ var105 = VectorInline$.MODULE$;
                        int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                        int var106 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                        Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                        int filterImpl_foreachRest_foreachRec_level = var106;
                        VectorStatics$ filterImpl_foreachRest_foreachRec_this = var103;
                        int filterImpl_foreachRest_foreachRec_i = 0;
                        int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                        if (filterImpl_foreachRest_foreachRec_level == 0) {
                           for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object var98 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              if ($anonfun$removed$1(element, var98) != filterImpl_isFlipped) {
                                 filterImpl_b.addOne(var98);
                              }
                           }
                        } else {
                           for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object[] filterImpl_foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              int filterImpl_foreachRec_i = 0;
                              int filterImpl_foreachRec_len = filterImpl_foreachRec_a.length;
                              if (filterImpl_foreachRest_foreachRec_l == 0) {
                                 for(; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object var97 = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    if ($anonfun$removed$1(element, var97) != filterImpl_isFlipped) {
                                       filterImpl_b.addOne(var97);
                                    }
                                 }
                              } else {
                                 for(int filterImpl_foreachRec_l = filterImpl_foreachRest_foreachRec_l - 1; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object[] foreachRec_a = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    int foreachRec_i = 0;
                                    int foreachRec_len = foreachRec_a.length;
                                    if (filterImpl_foreachRec_l == 0) {
                                       for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                          Object var61 = foreachRec_a[foreachRec_i];
                                          if ($anonfun$removed$1(element, var61) != filterImpl_isFlipped) {
                                             filterImpl_b.addOne(var61);
                                          }
                                       }
                                    } else {
                                       for(int foreachRec_l = filterImpl_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                          filterImpl_foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], filterImpl_foreachRest_f);
                                       }
                                    }

                                    foreachRec_a = null;
                                 }
                              }

                              filterImpl_foreachRec_a = null;
                           }
                        }

                        Object var82 = null;
                        filterImpl_foreachRest_foreachRec_a = null;
                     }

                     Object var78 = null;
                     filterImpl_foreachRest_f = null;
                     Object var83 = null;
                     Object var86 = null;
                     var10000 = filterImpl_b.result();
                  } else {
                     var10000 = filterNot_this;
                  }
                  break;
               }

               Object var59 = filterNot_this.prefix1()[filterImpl_i];
               if ($anonfun$removed$1(element, var59) == filterImpl_isFlipped) {
                  int filterImpl_bitmap = 0;

                  for(int filterImpl_j = filterImpl_i + 1; filterImpl_j < filterImpl_len; ++filterImpl_j) {
                     var59 = filterNot_this.prefix1()[filterImpl_j];
                     if ($anonfun$removed$1(element, var59) != filterImpl_isFlipped) {
                        filterImpl_bitmap |= 1 << filterImpl_j;
                     }
                  }

                  int filterImpl_newLen = filterImpl_i + Integer.bitCount(filterImpl_bitmap);
                  if (filterNot_this instanceof BigVector) {
                     VectorBuilder filterImpl_b = new VectorBuilder();

                     for(int filterImpl_k = 0; filterImpl_k < filterImpl_i; ++filterImpl_k) {
                        filterImpl_b.addOne(filterNot_this.prefix1()[filterImpl_k]);
                     }

                     for(int var65 = filterImpl_i + 1; filterImpl_i != filterImpl_newLen; ++var65) {
                        if ((1 << var65 & filterImpl_bitmap) != 0) {
                           filterImpl_b.addOne(filterNot_this.prefix1()[var65]);
                           ++filterImpl_i;
                        }
                     }

                     BigVector var99 = (BigVector)filterNot_this;
                     Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$1;
                     BigVector filterImpl_foreachRest_this = var99;
                     int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                     for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                        VectorStatics$ var100 = VectorStatics$.MODULE$;
                        VectorInline$ var10001 = VectorInline$.MODULE$;
                        int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                        int var104 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                        Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                        int filterImpl_foreachRest_foreachRec_level = var104;
                        VectorStatics$ filterImpl_foreachRest_foreachRec_this = var100;
                        int filterImpl_foreachRest_foreachRec_i = 0;
                        int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                        if (filterImpl_foreachRest_foreachRec_level == 0) {
                           for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object var96 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              if ($anonfun$removed$1(element, var96) != filterImpl_isFlipped) {
                                 filterImpl_b.addOne(var96);
                              }
                           }
                        } else {
                           for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object[] filterImpl_foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              int filterImpl_foreachRec_i = 0;
                              int filterImpl_foreachRec_len = filterImpl_foreachRec_a.length;
                              if (filterImpl_foreachRest_foreachRec_l == 0) {
                                 for(; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object var95 = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    if ($anonfun$removed$1(element, var95) != filterImpl_isFlipped) {
                                       filterImpl_b.addOne(var95);
                                    }
                                 }
                              } else {
                                 for(int filterImpl_foreachRec_l = filterImpl_foreachRest_foreachRec_l - 1; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object[] foreachRec_a = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    int foreachRec_i = 0;
                                    int foreachRec_len = foreachRec_a.length;
                                    if (filterImpl_foreachRec_l == 0) {
                                       for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                          Object var60 = foreachRec_a[foreachRec_i];
                                          if ($anonfun$removed$1(element, var60) != filterImpl_isFlipped) {
                                             filterImpl_b.addOne(var60);
                                          }
                                       }
                                    } else {
                                       for(int foreachRec_l = filterImpl_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                          filterImpl_foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], filterImpl_foreachRest_f);
                                       }
                                    }

                                    foreachRec_a = null;
                                 }
                              }

                              filterImpl_foreachRec_a = null;
                           }
                        }

                        Object var72 = null;
                        filterImpl_foreachRest_foreachRec_a = null;
                     }

                     Object var68 = null;
                     filterImpl_foreachRest_f = null;
                     Object var73 = null;
                     Object var76 = null;
                     var10000 = filterImpl_b.result();
                  } else if (filterImpl_newLen == 0) {
                     var10000 = Vector0$.MODULE$;
                  } else {
                     Object[] filterImpl_newData = new Object[filterImpl_newLen];
                     System.arraycopy(filterNot_this.prefix1(), 0, filterImpl_newData, 0, filterImpl_i);

                     for(int filterImpl_k = filterImpl_i + 1; filterImpl_i != filterImpl_newLen; ++filterImpl_k) {
                        if ((1 << filterImpl_k & filterImpl_bitmap) != 0) {
                           filterImpl_newData[filterImpl_i] = filterNot_this.prefix1()[filterImpl_k];
                           ++filterImpl_i;
                        }
                     }

                     var10000 = new Vector1(filterImpl_newData);
                  }
                  break;
               }

               ++filterImpl_i;
            }

            Object var64 = null;
            Object var66 = null;
            Object var67 = null;
            Object var69 = null;
            Object var71 = null;
            Object var74 = null;
            Object var77 = null;
            Object var79 = null;
            Object var81 = null;
            Object var84 = null;
            Object var87 = null;
            Object var89 = null;
            Object var91 = null;
            Object var62 = null;
            filterNot_pred = null;
            Vector updatedContent = var10000;
            if (updatedContent == null) {
               throw null;
            } else {
               switch (updatedContent.length()) {
                  case 1:
                     Node$ var10002 = Node$.MODULE$;
                     var10002 = Node$.MODULE$;
                     int maskFrom_shift = 0;
                     int bitposFrom_mask = hash >>> maskFrom_shift & 31;
                     return new BitmapIndexedSetNode(1 << bitposFrom_mask, 0, new Object[]{updatedContent.apply(0)}, new int[]{originalHash}, 1, hash);
                  default:
                     return new HashCollisionSetNode(originalHash, hash, updatedContent);
               }
            }
         }
      }
   }

   public boolean hasNodes() {
      return false;
   }

   public int nodeArity() {
      return 0;
   }

   public SetNode getNode(final int index) {
      throw new IndexOutOfBoundsException("No sub-nodes present in hash-collision leaf node.");
   }

   public boolean hasPayload() {
      return true;
   }

   public int payloadArity() {
      return this.content().length();
   }

   public Object getPayload(final int index) {
      return this.content().apply(index);
   }

   public int getHash(final int index) {
      return this.originalHash();
   }

   public int size() {
      return this.content().length();
   }

   public void foreach(final Function1 f) {
      Iterator iter = this.content().iterator();

      while(iter.hasNext()) {
         f.apply(iter.next());
      }

   }

   public int cachedJavaKeySetHashCode() {
      return this.size() * this.hash();
   }

   public boolean subsetOf(final SetNode that, final int shift) {
      if (this == that) {
         return true;
      } else if (that instanceof HashCollisionSetNode) {
         HashCollisionSetNode var3 = (HashCollisionSetNode)that;
         return this.payloadArity() <= var3.payloadArity() && this.content().forall((elem) -> BoxesRunTime.boxToBoolean($anonfun$subsetOf$1(eta$0$1$1, elem)));
      } else {
         return false;
      }
   }

   public SetNode filterImpl(final Function1 pred, final boolean flipped) {
      Vector var10000 = this.content();
      if (var10000 == null) {
         throw null;
      } else {
         Vector filterImpl_this = var10000;
         int filterImpl_i = 0;
         int filterImpl_len = filterImpl_this.prefix1().length;

         while(true) {
            if (filterImpl_i == filterImpl_len) {
               if (filterImpl_this instanceof BigVector) {
                  VectorBuilder filterImpl_b = new VectorBuilder();
                  filterImpl_b.initFrom(filterImpl_this.prefix1());
                  BigVector var81 = (BigVector)filterImpl_this;
                  Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$2;
                  BigVector filterImpl_foreachRest_this = var81;
                  int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                  for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                     VectorStatics$ var82 = VectorStatics$.MODULE$;
                     VectorInline$ var84 = VectorInline$.MODULE$;
                     int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                     int var85 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                     Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                     int filterImpl_foreachRest_foreachRec_level = var85;
                     VectorStatics$ filterImpl_foreachRest_foreachRec_this = var82;
                     int filterImpl_foreachRest_foreachRec_i = 0;
                     int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                     if (filterImpl_foreachRest_foreachRec_level == 0) {
                        for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object var77 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           if (BoxesRunTime.unboxToBoolean(pred.apply(var77)) != flipped) {
                              filterImpl_b.addOne(var77);
                           }
                        }
                     } else {
                        for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object[] foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           int foreachRec_i = 0;
                           int foreachRec_len = foreachRec_a.length;
                           if (filterImpl_foreachRest_foreachRec_l == 0) {
                              for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 Object var48 = foreachRec_a[foreachRec_i];
                                 if (BoxesRunTime.unboxToBoolean(pred.apply(var48)) != flipped) {
                                    filterImpl_b.addOne(var48);
                                 }
                              }
                           } else {
                              for(int foreachRec_l = filterImpl_foreachRest_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 filterImpl_foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], filterImpl_foreachRest_f);
                              }
                           }

                           foreachRec_a = null;
                        }
                     }

                     Object var68 = null;
                     filterImpl_foreachRest_foreachRec_a = null;
                  }

                  Object var64 = null;
                  filterImpl_foreachRest_f = null;
                  Object var69 = null;
                  Object var72 = null;
                  var10000 = filterImpl_b.result();
               } else {
                  var10000 = filterImpl_this;
               }
               break;
            }

            if (BoxesRunTime.unboxToBoolean(pred.apply(filterImpl_this.prefix1()[filterImpl_i])) == flipped) {
               int filterImpl_bitmap = 0;

               for(int filterImpl_j = filterImpl_i + 1; filterImpl_j < filterImpl_len; ++filterImpl_j) {
                  if (BoxesRunTime.unboxToBoolean(pred.apply(filterImpl_this.prefix1()[filterImpl_j])) != flipped) {
                     filterImpl_bitmap |= 1 << filterImpl_j;
                  }
               }

               int filterImpl_newLen = filterImpl_i + Integer.bitCount(filterImpl_bitmap);
               if (filterImpl_this instanceof BigVector) {
                  VectorBuilder filterImpl_b = new VectorBuilder();

                  for(int filterImpl_k = 0; filterImpl_k < filterImpl_i; ++filterImpl_k) {
                     filterImpl_b.addOne(filterImpl_this.prefix1()[filterImpl_k]);
                  }

                  for(int var51 = filterImpl_i + 1; filterImpl_i != filterImpl_newLen; ++var51) {
                     if ((1 << var51 & filterImpl_bitmap) != 0) {
                        filterImpl_b.addOne(filterImpl_this.prefix1()[var51]);
                        ++filterImpl_i;
                     }
                  }

                  BigVector var78 = (BigVector)filterImpl_this;
                  Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$1;
                  BigVector filterImpl_foreachRest_this = var78;
                  int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                  for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                     VectorStatics$ var79 = VectorStatics$.MODULE$;
                     VectorInline$ var10001 = VectorInline$.MODULE$;
                     int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                     int var83 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                     Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                     int filterImpl_foreachRest_foreachRec_level = var83;
                     VectorStatics$ filterImpl_foreachRest_foreachRec_this = var79;
                     int filterImpl_foreachRest_foreachRec_i = 0;
                     int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                     if (filterImpl_foreachRest_foreachRec_level == 0) {
                        for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object var76 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           if (BoxesRunTime.unboxToBoolean(pred.apply(var76)) != flipped) {
                              filterImpl_b.addOne(var76);
                           }
                        }
                     } else {
                        for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object[] foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           int foreachRec_i = 0;
                           int foreachRec_len = foreachRec_a.length;
                           if (filterImpl_foreachRest_foreachRec_l == 0) {
                              for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 Object var47 = foreachRec_a[foreachRec_i];
                                 if (BoxesRunTime.unboxToBoolean(pred.apply(var47)) != flipped) {
                                    filterImpl_b.addOne(var47);
                                 }
                              }
                           } else {
                              for(int foreachRec_l = filterImpl_foreachRest_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 filterImpl_foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], filterImpl_foreachRest_f);
                              }
                           }

                           foreachRec_a = null;
                        }
                     }

                     Object var58 = null;
                     filterImpl_foreachRest_foreachRec_a = null;
                  }

                  Object var54 = null;
                  filterImpl_foreachRest_f = null;
                  Object var59 = null;
                  Object var62 = null;
                  var10000 = filterImpl_b.result();
               } else if (filterImpl_newLen == 0) {
                  var10000 = Vector0$.MODULE$;
               } else {
                  Object[] filterImpl_newData = new Object[filterImpl_newLen];
                  System.arraycopy(filterImpl_this.prefix1(), 0, filterImpl_newData, 0, filterImpl_i);

                  for(int filterImpl_k = filterImpl_i + 1; filterImpl_i != filterImpl_newLen; ++filterImpl_k) {
                     if ((1 << filterImpl_k & filterImpl_bitmap) != 0) {
                        filterImpl_newData[filterImpl_i] = filterImpl_this.prefix1()[filterImpl_k];
                        ++filterImpl_i;
                     }
                  }

                  var10000 = new Vector1(filterImpl_newData);
               }
               break;
            }

            ++filterImpl_i;
         }

         Object var49 = null;
         Object var50 = null;
         Object var52 = null;
         Object var53 = null;
         Object var55 = null;
         Object var57 = null;
         Object var60 = null;
         Object var63 = null;
         Object var65 = null;
         Object var67 = null;
         Object var70 = null;
         Object var73 = null;
         Vector newContent = var10000;
         int newContentLength = newContent.length();
         if (newContentLength == 0) {
            return SetNode$.MODULE$.empty();
         } else if (newContentLength == 1) {
            Node$ var10002 = Node$.MODULE$;
            var10002 = Node$.MODULE$;
            int bitposFrom_mask = this.hash() >>> 0 & 31;
            return new BitmapIndexedSetNode(1 << bitposFrom_mask, 0, new Object[]{newContent.head()}, new int[]{this.originalHash()}, 1, this.hash());
         } else {
            return newContent.length() == this.content().length() ? this : new HashCollisionSetNode(this.originalHash(), this.hash(), newContent);
         }
      }
   }

   public SetNode diff(final SetNode that, final int shift) {
      Function1 var10000 = (x$1) -> BoxesRunTime.boxToBoolean($anonfun$diff$2(this, that, shift, x$1));
      boolean filterImpl_flipped = true;
      Function1 filterImpl_pred = var10000;
      Vector var82 = this.content();
      if (var82 == null) {
         throw null;
      } else {
         Vector filterImpl_filterImpl_this = var82;
         int filterImpl_filterImpl_i = 0;
         int filterImpl_filterImpl_len = filterImpl_filterImpl_this.prefix1().length;

         while(true) {
            if (filterImpl_filterImpl_i == filterImpl_filterImpl_len) {
               if (filterImpl_filterImpl_this instanceof BigVector) {
                  VectorBuilder filterImpl_filterImpl_b = new VectorBuilder();
                  filterImpl_filterImpl_b.initFrom(filterImpl_filterImpl_this.prefix1());
                  BigVector var86 = (BigVector)filterImpl_filterImpl_this;
                  Function1 filterImpl_filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$2;
                  BigVector filterImpl_filterImpl_foreachRest_this = var86;
                  int filterImpl_filterImpl_foreachRest_c = filterImpl_filterImpl_foreachRest_this.vectorSliceCount();

                  for(int filterImpl_filterImpl_foreachRest_i = 1; filterImpl_filterImpl_foreachRest_i < filterImpl_filterImpl_foreachRest_c; ++filterImpl_filterImpl_foreachRest_i) {
                     VectorStatics$ var87 = VectorStatics$.MODULE$;
                     VectorInline$ var89 = VectorInline$.MODULE$;
                     int filterImpl_filterImpl_foreachRest_vectorSliceDim_c = filterImpl_filterImpl_foreachRest_c / 2;
                     int var90 = filterImpl_filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_filterImpl_foreachRest_i - filterImpl_filterImpl_foreachRest_vectorSliceDim_c) - 1;
                     Object[] filterImpl_filterImpl_foreachRest_foreachRec_a = filterImpl_filterImpl_foreachRest_this.vectorSlice(filterImpl_filterImpl_foreachRest_i);
                     int filterImpl_filterImpl_foreachRest_foreachRec_level = var90;
                     VectorStatics$ filterImpl_filterImpl_foreachRest_foreachRec_this = var87;
                     int filterImpl_filterImpl_foreachRest_foreachRec_i = 0;
                     int filterImpl_filterImpl_foreachRest_foreachRec_len = filterImpl_filterImpl_foreachRest_foreachRec_a.length;
                     if (filterImpl_filterImpl_foreachRest_foreachRec_level == 0) {
                        for(; filterImpl_filterImpl_foreachRest_foreachRec_i < filterImpl_filterImpl_foreachRest_foreachRec_len; ++filterImpl_filterImpl_foreachRest_foreachRec_i) {
                           Object var81 = filterImpl_filterImpl_foreachRest_foreachRec_a[filterImpl_filterImpl_foreachRest_foreachRec_i];
                           if ($anonfun$diff$2(this, that, shift, var81) != filterImpl_flipped) {
                              filterImpl_filterImpl_b.addOne(var81);
                           }
                        }
                     } else {
                        for(int filterImpl_filterImpl_foreachRest_foreachRec_l = filterImpl_filterImpl_foreachRest_foreachRec_level - 1; filterImpl_filterImpl_foreachRest_foreachRec_i < filterImpl_filterImpl_foreachRest_foreachRec_len; ++filterImpl_filterImpl_foreachRest_foreachRec_i) {
                           Object[] foreachRec_a = filterImpl_filterImpl_foreachRest_foreachRec_a[filterImpl_filterImpl_foreachRest_foreachRec_i];
                           int foreachRec_i = 0;
                           int foreachRec_len = foreachRec_a.length;
                           if (filterImpl_filterImpl_foreachRest_foreachRec_l == 0) {
                              for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 Object var51 = foreachRec_a[foreachRec_i];
                                 if ($anonfun$diff$2(this, that, shift, var51) != filterImpl_flipped) {
                                    filterImpl_filterImpl_b.addOne(var51);
                                 }
                              }
                           } else {
                              for(int foreachRec_l = filterImpl_filterImpl_foreachRest_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 filterImpl_filterImpl_foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], filterImpl_filterImpl_foreachRest_f);
                              }
                           }

                           foreachRec_a = null;
                        }
                     }

                     Object var71 = null;
                     filterImpl_filterImpl_foreachRest_foreachRec_a = null;
                  }

                  Object var67 = null;
                  filterImpl_filterImpl_foreachRest_f = null;
                  Object var72 = null;
                  Object var75 = null;
                  var82 = filterImpl_filterImpl_b.result();
               } else {
                  var82 = filterImpl_filterImpl_this;
               }
               break;
            }

            Object var49 = filterImpl_filterImpl_this.prefix1()[filterImpl_filterImpl_i];
            if ($anonfun$diff$2(this, that, shift, var49) == filterImpl_flipped) {
               int filterImpl_filterImpl_bitmap = 0;

               for(int filterImpl_filterImpl_j = filterImpl_filterImpl_i + 1; filterImpl_filterImpl_j < filterImpl_filterImpl_len; ++filterImpl_filterImpl_j) {
                  var49 = filterImpl_filterImpl_this.prefix1()[filterImpl_filterImpl_j];
                  if ($anonfun$diff$2(this, that, shift, var49) != filterImpl_flipped) {
                     filterImpl_filterImpl_bitmap |= 1 << filterImpl_filterImpl_j;
                  }
               }

               int filterImpl_filterImpl_newLen = filterImpl_filterImpl_i + Integer.bitCount(filterImpl_filterImpl_bitmap);
               if (filterImpl_filterImpl_this instanceof BigVector) {
                  VectorBuilder filterImpl_filterImpl_b = new VectorBuilder();

                  for(int filterImpl_filterImpl_k = 0; filterImpl_filterImpl_k < filterImpl_filterImpl_i; ++filterImpl_filterImpl_k) {
                     filterImpl_filterImpl_b.addOne(filterImpl_filterImpl_this.prefix1()[filterImpl_filterImpl_k]);
                  }

                  for(int var54 = filterImpl_filterImpl_i + 1; filterImpl_filterImpl_i != filterImpl_filterImpl_newLen; ++var54) {
                     if ((1 << var54 & filterImpl_filterImpl_bitmap) != 0) {
                        filterImpl_filterImpl_b.addOne(filterImpl_filterImpl_this.prefix1()[var54]);
                        ++filterImpl_filterImpl_i;
                     }
                  }

                  BigVector var83 = (BigVector)filterImpl_filterImpl_this;
                  Function1 filterImpl_filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$1;
                  BigVector filterImpl_filterImpl_foreachRest_this = var83;
                  int filterImpl_filterImpl_foreachRest_c = filterImpl_filterImpl_foreachRest_this.vectorSliceCount();

                  for(int filterImpl_filterImpl_foreachRest_i = 1; filterImpl_filterImpl_foreachRest_i < filterImpl_filterImpl_foreachRest_c; ++filterImpl_filterImpl_foreachRest_i) {
                     VectorStatics$ var84 = VectorStatics$.MODULE$;
                     VectorInline$ var10001 = VectorInline$.MODULE$;
                     int filterImpl_filterImpl_foreachRest_vectorSliceDim_c = filterImpl_filterImpl_foreachRest_c / 2;
                     int var88 = filterImpl_filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_filterImpl_foreachRest_i - filterImpl_filterImpl_foreachRest_vectorSliceDim_c) - 1;
                     Object[] filterImpl_filterImpl_foreachRest_foreachRec_a = filterImpl_filterImpl_foreachRest_this.vectorSlice(filterImpl_filterImpl_foreachRest_i);
                     int filterImpl_filterImpl_foreachRest_foreachRec_level = var88;
                     VectorStatics$ filterImpl_filterImpl_foreachRest_foreachRec_this = var84;
                     int filterImpl_filterImpl_foreachRest_foreachRec_i = 0;
                     int filterImpl_filterImpl_foreachRest_foreachRec_len = filterImpl_filterImpl_foreachRest_foreachRec_a.length;
                     if (filterImpl_filterImpl_foreachRest_foreachRec_level == 0) {
                        for(; filterImpl_filterImpl_foreachRest_foreachRec_i < filterImpl_filterImpl_foreachRest_foreachRec_len; ++filterImpl_filterImpl_foreachRest_foreachRec_i) {
                           Object var80 = filterImpl_filterImpl_foreachRest_foreachRec_a[filterImpl_filterImpl_foreachRest_foreachRec_i];
                           if ($anonfun$diff$2(this, that, shift, var80) != filterImpl_flipped) {
                              filterImpl_filterImpl_b.addOne(var80);
                           }
                        }
                     } else {
                        for(int filterImpl_filterImpl_foreachRest_foreachRec_l = filterImpl_filterImpl_foreachRest_foreachRec_level - 1; filterImpl_filterImpl_foreachRest_foreachRec_i < filterImpl_filterImpl_foreachRest_foreachRec_len; ++filterImpl_filterImpl_foreachRest_foreachRec_i) {
                           Object[] foreachRec_a = filterImpl_filterImpl_foreachRest_foreachRec_a[filterImpl_filterImpl_foreachRest_foreachRec_i];
                           int foreachRec_i = 0;
                           int foreachRec_len = foreachRec_a.length;
                           if (filterImpl_filterImpl_foreachRest_foreachRec_l == 0) {
                              for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 Object var50 = foreachRec_a[foreachRec_i];
                                 if ($anonfun$diff$2(this, that, shift, var50) != filterImpl_flipped) {
                                    filterImpl_filterImpl_b.addOne(var50);
                                 }
                              }
                           } else {
                              for(int foreachRec_l = filterImpl_filterImpl_foreachRest_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 filterImpl_filterImpl_foreachRest_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], filterImpl_filterImpl_foreachRest_f);
                              }
                           }

                           foreachRec_a = null;
                        }
                     }

                     Object var61 = null;
                     filterImpl_filterImpl_foreachRest_foreachRec_a = null;
                  }

                  Object var57 = null;
                  filterImpl_filterImpl_foreachRest_f = null;
                  Object var62 = null;
                  Object var65 = null;
                  var82 = filterImpl_filterImpl_b.result();
               } else if (filterImpl_filterImpl_newLen == 0) {
                  var82 = Vector0$.MODULE$;
               } else {
                  Object[] filterImpl_filterImpl_newData = new Object[filterImpl_filterImpl_newLen];
                  System.arraycopy(filterImpl_filterImpl_this.prefix1(), 0, filterImpl_filterImpl_newData, 0, filterImpl_filterImpl_i);

                  for(int filterImpl_filterImpl_k = filterImpl_filterImpl_i + 1; filterImpl_filterImpl_i != filterImpl_filterImpl_newLen; ++filterImpl_filterImpl_k) {
                     if ((1 << filterImpl_filterImpl_k & filterImpl_filterImpl_bitmap) != 0) {
                        filterImpl_filterImpl_newData[filterImpl_filterImpl_i] = filterImpl_filterImpl_this.prefix1()[filterImpl_filterImpl_k];
                        ++filterImpl_filterImpl_i;
                     }
                  }

                  var82 = new Vector1(filterImpl_filterImpl_newData);
               }
               break;
            }

            ++filterImpl_filterImpl_i;
         }

         Object var52 = null;
         Object var53 = null;
         Object var55 = null;
         Object var56 = null;
         Object var58 = null;
         Object var60 = null;
         Object var63 = null;
         Object var66 = null;
         Object var68 = null;
         Object var70 = null;
         Object var73 = null;
         Object var76 = null;
         Vector filterImpl_newContent = var82;
         int filterImpl_newContentLength = filterImpl_newContent.length();
         if (filterImpl_newContentLength == 0) {
            return SetNode$.MODULE$.empty();
         } else if (filterImpl_newContentLength == 1) {
            Node$ var10002 = Node$.MODULE$;
            var10002 = Node$.MODULE$;
            int filterImpl_bitposFrom_mask = this.hash() >>> 0 & 31;
            return new BitmapIndexedSetNode(1 << filterImpl_bitposFrom_mask, 0, new Object[]{filterImpl_newContent.head()}, new int[]{this.originalHash()}, 1, this.hash());
         } else {
            return filterImpl_newContent.length() == this.content().length() ? this : new HashCollisionSetNode(this.originalHash(), this.hash(), filterImpl_newContent);
         }
      }
   }

   public boolean equals(final Object that) {
      if (!(that instanceof HashCollisionSetNode)) {
         return false;
      } else {
         HashCollisionSetNode var2 = (HashCollisionSetNode)that;
         if (this != var2) {
            if (this.hash() == var2.hash()) {
               Vector var10000 = this.content();
               if (var10000 == null) {
                  throw null;
               }

               int var3 = var10000.length();
               Vector var10001 = var2.content();
               if (var10001 == null) {
                  throw null;
               }

               if (var3 == var10001.length() && this.content().forall((elem) -> BoxesRunTime.boxToBoolean($anonfun$equals$1(eta$0$1$2, elem)))) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      }
   }

   public int hashCode() {
      throw new UnsupportedOperationException("Trie nodes do not support hashing.");
   }

   public HashCollisionSetNode copy() {
      return new HashCollisionSetNode(this.originalHash(), this.hash(), this.content());
   }

   public SetNode concat(final SetNode that, final int shift) {
      if (that instanceof HashCollisionSetNode) {
         HashCollisionSetNode var3 = (HashCollisionSetNode)that;
         if (var3 == this) {
            return this;
         } else {
            VectorBuilder newContent = null;
            Iterator iter = var3.content().iterator();

            while(iter.hasNext()) {
               Object nextPayload = iter.next();
               if (!this.content().contains(nextPayload)) {
                  if (newContent == null) {
                     newContent = new VectorBuilder();
                     newContent.addAll(this.content());
                  }

                  newContent.addOne(nextPayload);
               }
            }

            if (newContent == null) {
               return this;
            } else {
               return new HashCollisionSetNode(this.originalHash(), this.hash(), newContent.result());
            }
         }
      } else if (that instanceof BitmapIndexedSetNode) {
         throw new UnsupportedOperationException("Cannot concatenate a HashCollisionSetNode with a BitmapIndexedSetNode");
      } else {
         throw new MatchError(that);
      }
   }

   public void foreachWithHash(final Function2 f) {
      Iterator iter = this.content().iterator();

      while(iter.hasNext()) {
         Object next = iter.next();
         f.apply(next, this.originalHash());
      }

   }

   public boolean foreachWithHashWhile(final Function2 f) {
      boolean stillGoing = true;

      Object next;
      for(Iterator iter = this.content().iterator(); iter.hasNext() && stillGoing; stillGoing = stillGoing && BoxesRunTime.unboxToBoolean(f.apply(next, this.originalHash()))) {
         next = iter.next();
      }

      return stillGoing;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removed$1(final Object element$1, final Object element0) {
      return BoxesRunTime.equals(element0, element$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$subsetOf$1(final Vector eta$0$1$1, final Object elem) {
      return eta$0$1$1.contains(elem);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$diff$2(final HashCollisionSetNode $this, final SetNode that$1, final int shift$1, final Object x$1) {
      return that$1.contains(x$1, $this.originalHash(), $this.hash(), shift$1);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$equals$1(final Vector eta$0$1$2, final Object elem) {
      return eta$0$1$2.contains(elem);
   }

   public HashCollisionSetNode(final int originalHash, final int hash, final Vector content) {
      this.originalHash = originalHash;
      this.hash = hash;
      this.content = content;
      super();
      Predef$.MODULE$.require(this.content().length() >= 2);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
