package scala.collection.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import scala.Array$;
import scala.MatchError;
import scala.Predef$;
import scala.Some;
import scala.collection.AbstractIterable;
import scala.collection.AbstractIterator;
import scala.collection.Iterable;
import scala.collection.Iterable$;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.Seq;
import scala.collection.StringOps$;
import scala.collection.View;
import scala.collection.mutable.ArrayBuilder;
import scala.collection.mutable.ArrayBuilder$;
import scala.collection.mutable.ArraySeq;
import scala.math.Equiv;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001de!\u0002\r\u001a\u0005my\u0002\u0002C\u001b\u0001\u0005\u000b\u0007I\u0011\u0001\u001c\t\u0011i\u0002!\u0011!Q\u0001\n]B\u0001b\u000f\u0001\u0003\u0006\u0004%\t\u0001\u0010\u0005\t\u0007\u0002\u0011\t\u0011)A\u0005{!AA\t\u0001BC\u0002\u0013\u0005Q\t\u0003\u0005J\u0001\t\u0005\t\u0015!\u0003G\u0011\u0015Q\u0005\u0001\"\u0001L\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u00159\u0006\u0001\"\u0001Y\u0011\u0015I\u0006\u0001\"\u0003[\u0011\u0015y\u0006\u0001\"\u0001a\u0011\u00151\u0007\u0001\"\u0001h\u0011\u0015a\u0007\u0001\"\u0001n\u0011\u00159\b\u0001\"\u0001y\u0011\u0015a\b\u0001\"\u0003~\u0011\u001d\tY\u0001\u0001C\u0001\u0003\u001bAq!!\u0007\u0001\t\u0003\tY\u0002C\u0004\u0002$\u0001!\t!!\n\t\u000f\u0005}\u0002\u0001\"\u0011\u0002B\u001dA\u00111I\r\t\u0002e\t)EB\u0004\u00193!\u0005\u0011$a\u0012\t\r)+B\u0011AA%\u0011\u001d\tY%\u0006C\u0001\u0003\u001b\u0012Qa\u0011(pI\u0016T!AG\u000e\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002\u001d;\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003y\tQa]2bY\u0006,2\u0001I\u00144'\t\u0001\u0011\u0005\u0005\u0003#G\u0015\u0012T\"A\r\n\u0005\u0011J\"!C\"O_\u0012,')Y:f!\t1s\u0005\u0004\u0001\u0005\u000b!\u0002!\u0019\u0001\u0016\u0003\u0003-\u001b\u0001!\u0005\u0002,_A\u0011A&L\u0007\u0002;%\u0011a&\b\u0002\b\u001d>$\b.\u001b8h!\ta\u0003'\u0003\u00022;\t\u0019\u0011I\\=\u0011\u0005\u0019\u001aD!\u0002\u001b\u0001\u0005\u0004Q#!\u0001,\u0002\r\tLG/\\1q+\u00059\u0004C\u0001\u00179\u0013\tITDA\u0002J]R\fqAY5u[\u0006\u0004\b%A\u0003beJ\f\u00170F\u0001>!\rac\bQ\u0005\u0003\u007fu\u0011Q!\u0011:sCf\u0004\"AI!\n\u0005\tK\"!\u0003\"bg&\u001cgj\u001c3f\u0003\u0019\t'O]1zA\u0005\u0019q-\u001a8\u0016\u0003\u0019\u0003\"AI$\n\u0005!K\"aA$f]\u0006!q-\u001a8!\u0003\u0019a\u0014N\\5u}Q!A*\u0014(P!\u0011\u0011\u0003!\n\u001a\t\u000bU:\u0001\u0019A\u001c\t\u000bm:\u0001\u0019A\u001f\t\u000b\u0011;\u0001\u0019\u0001$\u0002\u0015\r\f7\r[3e'&TX\r\u0006\u00028%\")1\u000b\u0003a\u0001)\u0006\u00111\r\u001e\t\u0003YUK!AV\u000f\u0003\r\u0005s\u0017PU3g\u0003%Ygn\\<o'&TX\rF\u00018\u0003-\u0019w.\u001c9vi\u0016\u001c\u0016N_3\u0015\u0005]Z\u0006\"B*\u000b\u0001\u0004a\u0006\u0003\u0002\u0012^KIJ!AX\r\u0003\u000fQ\u0013\u0018.Z'ba\u0006IQ\u000f\u001d3bi\u0016$\u0017\t\u001e\u000b\u0005\u0019\u0006\u001cW\rC\u0003c\u0017\u0001\u0007q'A\u0002q_NDQ\u0001Z\u0006A\u0002\u0001\u000b!A\u001c8\t\u000b\u0011[\u0001\u0019\u0001$\u0002\u0013I,Wn\u001c<fI\u0006#H\u0003\u0002'iS.DQA\u0019\u0007A\u0002]BQA\u001b\u0007A\u0002]\nAA\u001a7bO\")A\t\u0004a\u0001\r\u0006Q\u0011N\\:feR,G-\u0011;\u0015\u000f1sw\u000e\u001d:um\")!-\u0004a\u0001o!)!.\u0004a\u0001o!)\u0011/\u0004a\u0001K\u0005\t1\u000eC\u0003t\u001b\u0001\u0007!'A\u0001w\u0011\u0015)X\u00021\u00018\u0003\tA7\rC\u0003E\u001b\u0001\u0007a)A\u0004sK:,w/\u001a3\u0015\u00071K8\u0010C\u0003{\u001d\u0001\u0007a)\u0001\u0003oO\u0016t\u0007\"B*\u000f\u0001\u0004a\u0016!\u0003:fgV\u0014(/Z2u)\u0011\u0001e0a\u0002\t\r}|\u0001\u0019AA\u0001\u0003\u0015Ign\u001c3f!\u0015\u0011\u00131A\u00133\u0013\r\t)!\u0007\u0002\u0006\u0013:{G-\u001a\u0005\u0007\u0003\u0013y\u0001\u0019\u0001+\u0002\u0013%tw\u000eZ3nC&t\u0017\u0001\u0004;p\u0007>tGO]1di\u0016$G\u0003BA\b\u0003+\u0001RAIA\tKIJ1!a\u0005\u001a\u0005!i\u0015-\u001b8O_\u0012,\u0007BBA\f!\u0001\u0007q'A\u0002mKZ\fA\u0002^8D_6\u0004(/Z:tK\u0012$\u0002\"a\u0004\u0002\u001e\u0005}\u0011\u0011\u0005\u0005\u0006'F\u0001\r\u0001\u0018\u0005\u0007\u0003/\t\u0002\u0019A\u001c\t\u000b\u0011\u000b\u0002\u0019\u0001$\u0002\rM$(/\u001b8h)\u0011\t9#!\u0010\u0011\t\u0005%\u0012q\u0007\b\u0005\u0003W\t\u0019\u0004E\u0002\u0002.ui!!a\f\u000b\u0007\u0005E\u0012&\u0001\u0004=e>|GOP\u0005\u0004\u0003ki\u0012A\u0002)sK\u0012,g-\u0003\u0003\u0002:\u0005m\"AB*ue&twMC\u0002\u00026uAa!a\u0006\u0013\u0001\u00049\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005\u001d\u0012!B\"O_\u0012,\u0007C\u0001\u0012\u0016'\t)B\u000b\u0006\u0002\u0002F\u0005!A-^1m+\u0019\ty%!\u0016\u0002ZQ\u0001\u0012\u0011KA.\u0003K\nI'!\u001c\u0002r\u0005M\u0014Q\u000f\t\bE\u0005E\u00111KA,!\r1\u0013Q\u000b\u0003\u0006Q]\u0011\rA\u000b\t\u0004M\u0005eC!\u0002\u001b\u0018\u0005\u0004Q\u0003bBA//\u0001\u0007\u0011qL\u0001\u0002qB9!%!\u0019\u0002T\u0005]\u0013bAA23\t)1KT8eK\"1\u0011qM\fA\u0002]\n1\u0001\u001f5d\u0011\u001d\tYg\u0006a\u0001\u0003?\n\u0011!\u001f\u0005\u0007\u0003_:\u0002\u0019A\u001c\u0002\u0007eD7\r\u0003\u0004\u0002\u0018]\u0001\ra\u000e\u0005\u0006\t^\u0001\rA\u0012\u0005\b\u0003o:\u0002\u0019AA=\u0003\u0015)\u0017/^5w!\u0019\tY(!!\u0002T9\u0019A&! \n\u0007\u0005}T$A\u0004qC\u000e\\\u0017mZ3\n\t\u0005\r\u0015Q\u0011\u0002\u0006\u000bF,\u0018N\u001e\u0006\u0004\u0003\u007fj\u0002"
)
public final class CNode extends CNodeBase {
   private final int bitmap;
   private final BasicNode[] array;
   private final Gen gen;

   public static MainNode dual(final SNode x, final int xhc, final SNode y, final int yhc, final int lev, final Gen gen, final Equiv equiv) {
      return CNode$.MODULE$.dual(x, xhc, y, yhc, lev, gen, equiv);
   }

   public int bitmap() {
      return this.bitmap;
   }

   public BasicNode[] array() {
      return this.array;
   }

   public Gen gen() {
      return this.gen;
   }

   public int cachedSize(final Object ct) {
      int currsz = this.READ_SIZE();
      if (currsz != -1) {
         return currsz;
      } else {
         int sz = this.computeSize((TrieMap)ct);

         while(this.READ_SIZE() == -1) {
            this.CAS_SIZE(-1, sz);
         }

         return this.READ_SIZE();
      }
   }

   public int knownSize() {
      return this.READ_SIZE();
   }

   private int computeSize(final TrieMap ct) {
      int i = 0;
      int sz = 0;

      for(int offset = this.array().length > 0 ? ThreadLocalRandom.current().nextInt(0, this.array().length) : 0; i < this.array().length; ++i) {
         int pos = (i + offset) % this.array().length;
         BasicNode var6 = this.array()[pos];
         if (var6 instanceof SNode) {
            ++sz;
         } else {
            if (!(var6 instanceof INode)) {
               throw new MatchError(var6);
            }

            INode var7 = (INode)var6;
            sz += var7.cachedSize(ct);
         }
      }

      return sz;
   }

   public CNode updatedAt(final int pos, final BasicNode nn, final Gen gen) {
      int len = this.array().length;
      BasicNode[] narr = new BasicNode[len];
      Array$.MODULE$.copy(this.array(), 0, narr, 0, len);
      narr[pos] = nn;
      return new CNode(this.bitmap(), narr, gen);
   }

   public CNode removedAt(final int pos, final int flag, final Gen gen) {
      BasicNode[] arr = this.array();
      int len = arr.length;
      BasicNode[] narr = new BasicNode[len - 1];
      Array$.MODULE$.copy(arr, 0, narr, 0, pos);
      Array$.MODULE$.copy(arr, pos + 1, narr, pos, len - pos - 1);
      return new CNode(this.bitmap() ^ flag, narr, gen);
   }

   public CNode insertedAt(final int pos, final int flag, final Object k, final Object v, final int hc, final Gen gen) {
      int len = this.array().length;
      int bmp = this.bitmap();
      BasicNode[] narr = new BasicNode[len + 1];
      Array$.MODULE$.copy(this.array(), 0, narr, 0, pos);
      narr[pos] = new SNode(k, v, hc);
      Array$.MODULE$.copy(this.array(), pos, narr, pos + 1, len - pos);
      return new CNode(bmp | flag, narr, gen);
   }

   public CNode renewed(final Gen ngen, final TrieMap ct) {
      int i = 0;
      BasicNode[] arr = this.array();
      int len = arr.length;

      BasicNode[] narr;
      for(narr = new BasicNode[len]; i < len; ++i) {
         BasicNode var7 = arr[i];
         if (var7 instanceof INode) {
            INode var8 = (INode)var7;
            narr[i] = var8.copyToGen(ngen, ct);
         } else {
            if (var7 == null) {
               throw new MatchError((Object)null);
            }

            narr[i] = var7;
         }
      }

      return new CNode(this.bitmap(), narr, ngen);
   }

   private BasicNode resurrect(final INode inode, final Object inodemain) {
      return (BasicNode)(inodemain instanceof TNode ? ((TNode)inodemain).copyUntombed() : inode);
   }

   public MainNode toContracted(final int lev) {
      if (this.array().length == 1 && lev > 0) {
         BasicNode var2 = this.array()[0];
         return (MainNode)(var2 instanceof SNode ? ((SNode)var2).copyTombed() : this);
      } else {
         return this;
      }
   }

   public MainNode toCompressed(final TrieMap ct, final int lev, final Gen gen) {
      int bmp = this.bitmap();
      int i = 0;
      BasicNode[] arr = this.array();

      BasicNode[] tmparray;
      for(tmparray = new BasicNode[arr.length]; i < arr.length; ++i) {
         BasicNode sub = arr[i];
         if (sub instanceof INode) {
            INode var9 = (INode)sub;
            if (var9 == null) {
               throw null;
            }

            MainNode inodemain = var9.GCAS_READ(ct);
            Predef$.MODULE$.assert(inodemain != null);
            tmparray[i] = this.resurrect(var9, inodemain);
         } else {
            if (!(sub instanceof SNode)) {
               throw new MatchError(sub);
            }

            SNode var11 = (SNode)sub;
            tmparray[i] = var11;
         }
      }

      return (new CNode(bmp, tmparray, gen)).toContracted(lev);
   }

   public String string(final int lev) {
      StringOps$ var10000 = StringOps$.MODULE$;
      ScalaRunTime$ var10002 = ScalaRunTime$.MODULE$;
      Object[] var10003 = new Object[]{this.bitmap(), null};
      Predef$ var10006 = Predef$.MODULE$;
      Object map$extension_$this = this.array();
      int map$extension_len = ((Object[])map$extension_$this).length;
      Object map$extension_ys = new String[map$extension_len];
      if (map$extension_len > 0) {
         for(int map$extension_i = 0; map$extension_i < map$extension_len; ++map$extension_i) {
            Object array_update_value = ((BasicNode)((Object[])map$extension_$this)[map$extension_i]).string(lev + 1);
            ((Object[])map$extension_ys)[map$extension_i] = array_update_value;
            array_update_value = null;
         }
      }

      map$extension_$this = null;
      Object var14 = null;
      ArraySeq.ofRef var18 = var10006.wrapRefArray((Object[])map$extension_ys);
      String mkString_sep = "\n";
      if (var18 == null) {
         throw null;
      } else {
         AbstractIterable mkString_this = var18;
         String mkString_end = "";
         String mkString_start = "";
         String var19 = IterableOnceOps.mkString$(mkString_this, mkString_start, mkString_sep, mkString_end);
         Object var15 = null;
         Object var16 = null;
         mkString_this = null;
         Object var12 = null;
         var10003[1] = var19;
         return var10000.format$extension("CNode %x\n%s", var10002.genericWrapArray(var10003));
      }
   }

   public String toString() {
      StringOps$ var10000 = StringOps$.MODULE$;
      ScalaRunTime$ var10002 = ScalaRunTime$.MODULE$;
      Object[] var10003 = new Object[2];
      Seq var10006 = this.elems$1();
      if (var10006 == null) {
         throw null;
      } else {
         var10003[0] = var10006.length();
         IterableOnceOps var3 = (IterableOnceOps)this.elems$1().sorted(Ordering.String$.MODULE$);
         String mkString_sep = ", ";
         if (var3 == null) {
            throw null;
         } else {
            String var4 = var3.mkString("", mkString_sep, "");
            Object var2 = null;
            var10003[1] = var4;
            return var10000.format$extension("CNode(sz: %d; %s)", var10002.genericWrapArray(var10003));
         }
      }
   }

   // $FF: synthetic method
   public static final String $anonfun$string$1(final int lev$3, final BasicNode x$3) {
      return x$3.string(lev$3 + 1);
   }

   // $FF: synthetic method
   public static final Iterable $anonfun$toString$1(final BasicNode x0$1) {
      if (x0$1 instanceof SNode) {
         SNode var1 = (SNode)x0$1;
         Iterable$ var5 = Iterable$.MODULE$;
         Object single_a = var1.kvPair()._2().toString();
         return new AbstractIterable(single_a) {
            private final Object a$1;

            public Iterator iterator() {
               Iterator$ var10000 = Iterator$.MODULE$;
               Object single_a = this.a$1;
               return new AbstractIterator(single_a) {
                  private boolean consumed;
                  private final Object a$1;

                  public boolean hasNext() {
                     return !this.consumed;
                  }

                  public Object next() {
                     if (this.consumed) {
                        Iterator$ var10000 = Iterator$.MODULE$;
                        return Iterator$.scala$collection$Iterator$$_empty.next();
                     } else {
                        this.consumed = true;
                        return this.a$1;
                     }
                  }

                  public Iterator sliceIterator(final int from, final int until) {
                     if (!this.consumed && from <= 0 && until != 0) {
                        return this;
                     } else {
                        Iterator$ var10000 = Iterator$.MODULE$;
                        return Iterator$.scala$collection$Iterator$$_empty;
                     }
                  }

                  public {
                     this.a$1 = a$1;
                     this.consumed = false;
                  }
               };
            }

            public int knownSize() {
               return 1;
            }

            public Object head() {
               return this.a$1;
            }

            public Some headOption() {
               return new Some(this.a$1);
            }

            public Object last() {
               return this.a$1;
            }

            public Some lastOption() {
               return new Some(this.a$1);
            }

            public View.Single view() {
               return new View.Single(this.a$1);
            }

            public Iterable take(final int n) {
               return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
            }

            public Iterable takeRight(final int n) {
               return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
            }

            public Iterable drop(final int n) {
               return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
            }

            public Iterable dropRight(final int n) {
               return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
            }

            public Iterable tail() {
               return (Iterable)Iterable$.MODULE$.empty();
            }

            public Iterable init() {
               return (Iterable)Iterable$.MODULE$.empty();
            }

            public {
               this.a$1 = a$1;
            }
         };
      } else if (x0$1 instanceof INode) {
         INode var2 = (INode)x0$1;
         Iterable$ var10000 = Iterable$.MODULE$;
         Object single_a = (new StringBuilder(2)).append(StringOps$.MODULE$.drop$extension(var2.toString(), 14)).append("(").append(var2.gen).append(")").toString();
         return new AbstractIterable(single_a) {
            private final Object a$1;

            public Iterator iterator() {
               Iterator$ var10000 = Iterator$.MODULE$;
               Object single_a = this.a$1;
               return new AbstractIterator(single_a) {
                  private boolean consumed;
                  private final Object a$1;

                  public boolean hasNext() {
                     return !this.consumed;
                  }

                  public Object next() {
                     if (this.consumed) {
                        Iterator$ var10000 = Iterator$.MODULE$;
                        return Iterator$.scala$collection$Iterator$$_empty.next();
                     } else {
                        this.consumed = true;
                        return this.a$1;
                     }
                  }

                  public Iterator sliceIterator(final int from, final int until) {
                     if (!this.consumed && from <= 0 && until != 0) {
                        return this;
                     } else {
                        Iterator$ var10000 = Iterator$.MODULE$;
                        return Iterator$.scala$collection$Iterator$$_empty;
                     }
                  }

                  public {
                     this.a$1 = a$1;
                     this.consumed = false;
                  }
               };
            }

            public int knownSize() {
               return 1;
            }

            public Object head() {
               return this.a$1;
            }

            public Some headOption() {
               return new Some(this.a$1);
            }

            public Object last() {
               return this.a$1;
            }

            public Some lastOption() {
               return new Some(this.a$1);
            }

            public View.Single view() {
               return new View.Single(this.a$1);
            }

            public Iterable take(final int n) {
               return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
            }

            public Iterable takeRight(final int n) {
               return (Iterable)(n > 0 ? this : (Iterable)Iterable$.MODULE$.empty());
            }

            public Iterable drop(final int n) {
               return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
            }

            public Iterable dropRight(final int n) {
               return (Iterable)(n > 0 ? (Iterable)Iterable$.MODULE$.empty() : this);
            }

            public Iterable tail() {
               return (Iterable)Iterable$.MODULE$.empty();
            }

            public Iterable init() {
               return (Iterable)Iterable$.MODULE$.empty();
            }

            public {
               this.a$1 = a$1;
            }
         };
      } else {
         throw new MatchError(x0$1);
      }
   }

   private final Seq elems$1() {
      Object flatMap$extension_$this;
      Object var23;
      Predef$ var10000;
      label122: {
         label125: {
            var10000 = Predef$.MODULE$;
            BasicNode[] var10001 = this.array();
            ClassTag flatMap$extension_evidence$8 = ClassTag$.MODULE$.apply(String.class);
            flatMap$extension_$this = var10001;
            ArrayBuilder$ var13 = ArrayBuilder$.MODULE$;
            Class var5 = flatMap$extension_evidence$8.runtimeClass();
            Class var14 = Byte.TYPE;
            if (var14 == null) {
               if (var5 == null) {
                  break label125;
               }
            } else if (var14.equals(var5)) {
               break label125;
            }

            label126: {
               var14 = Short.TYPE;
               if (var14 == null) {
                  if (var5 == null) {
                     break label126;
                  }
               } else if (var14.equals(var5)) {
                  break label126;
               }

               label127: {
                  var14 = Character.TYPE;
                  if (var14 == null) {
                     if (var5 == null) {
                        break label127;
                     }
                  } else if (var14.equals(var5)) {
                     break label127;
                  }

                  label128: {
                     var14 = Integer.TYPE;
                     if (var14 == null) {
                        if (var5 == null) {
                           break label128;
                        }
                     } else if (var14.equals(var5)) {
                        break label128;
                     }

                     label129: {
                        var14 = Long.TYPE;
                        if (var14 == null) {
                           if (var5 == null) {
                              break label129;
                           }
                        } else if (var14.equals(var5)) {
                           break label129;
                        }

                        label130: {
                           var14 = Float.TYPE;
                           if (var14 == null) {
                              if (var5 == null) {
                                 break label130;
                              }
                           } else if (var14.equals(var5)) {
                              break label130;
                           }

                           label131: {
                              var14 = Double.TYPE;
                              if (var14 == null) {
                                 if (var5 == null) {
                                    break label131;
                                 }
                              } else if (var14.equals(var5)) {
                                 break label131;
                              }

                              label132: {
                                 var14 = Boolean.TYPE;
                                 if (var14 == null) {
                                    if (var5 == null) {
                                       break label132;
                                    }
                                 } else if (var14.equals(var5)) {
                                    break label132;
                                 }

                                 label65: {
                                    var14 = Void.TYPE;
                                    if (var14 == null) {
                                       if (var5 == null) {
                                          break label65;
                                       }
                                    } else if (var14.equals(var5)) {
                                       break label65;
                                    }

                                    var23 = new ArrayBuilder.ofRef(flatMap$extension_evidence$8);
                                    break label122;
                                 }

                                 var23 = new ArrayBuilder.ofUnit();
                                 break label122;
                              }

                              var23 = new ArrayBuilder.ofBoolean();
                              break label122;
                           }

                           var23 = new ArrayBuilder.ofDouble();
                           break label122;
                        }

                        var23 = new ArrayBuilder.ofFloat();
                        break label122;
                     }

                     var23 = new ArrayBuilder.ofLong();
                     break label122;
                  }

                  var23 = new ArrayBuilder.ofInt();
                  break label122;
               }

               var23 = new ArrayBuilder.ofChar();
               break label122;
            }

            var23 = new ArrayBuilder.ofShort();
            break label122;
         }

         var23 = new ArrayBuilder.ofByte();
      }

      Object var10 = null;
      ArrayBuilder flatMap$extension_b = (ArrayBuilder)var23;

      for(int flatMap$extension_i = 0; flatMap$extension_i < ((Object[])flatMap$extension_$this).length; ++flatMap$extension_i) {
         IterableOnce flatMap$extension_$plus$plus$eq_elems = $anonfun$toString$1((BasicNode)((Object[])flatMap$extension_$this)[flatMap$extension_i]);
         flatMap$extension_b.addAll(flatMap$extension_$plus$plus$eq_elems);
         flatMap$extension_$plus$plus$eq_elems = null;
      }

      var23 = flatMap$extension_b.result();
      flatMap$extension_$this = null;
      Object var8 = null;
      flatMap$extension_b = null;
      Object var12 = null;
      return var10000.wrapRefArray(var23);
   }

   public CNode(final int bitmap, final BasicNode[] array, final Gen gen) {
      this.bitmap = bitmap;
      this.array = array;
      this.gen = gen;
   }
}
