package scala.collection.concurrent;

import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Some;
import scala.collection.StringOps$;
import scala.math.Equiv;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ScalaRunTime$;

@ScalaSignature(
   bytes = "\u0006\u0005\tEa!\u0002\u0012$\u0005\u0015J\u0003\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u0011\r\u0003!\u0011!Q\u0001\n\u0011C\u0001b\u0012\u0001\u0003\u0002\u0003\u0006I\u0001\u0013\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006\u001f\u0002!\t!\u0016\u0005\u00061\u0002!\t!\u0017\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006Q\u0002!\t!\u001b\u0005\u0006_\u0002!\t\u0001\u001d\u0005\u0006e\u0002!Ia\u001d\u0005\u0006}\u0002!\ta \u0005\b\u0003\u000f\u0001A\u0011BA\u0005\u0011\u001d\t)\u0002\u0001C\u0005\u0003/Aq!!\b\u0001\t\u0003\ty\u0002C\u0004\u0002(\u0001!\t!!\u000b\t\u000f\u00055\u0003\u0001\"\u0001\u0002P!9\u0011Q\u000f\u0001\u0005\u0002\u0005]\u0004bBAD\u0001\u0011\u0005\u0011\u0011\u0012\u0005\b\u0003;\u0003A\u0011BAP\u0011\u001d\tI\u000b\u0001C\u0001\u0003WCq!a,\u0001\t\u0003\t\t\fC\u0004\u00026\u0002!\t!a.\t\u000f\u0005m\u0006\u0001\"\u0001\u0002>\u001eA\u0011q[\u0012\t\u0002\r\nINB\u0004#G!\u00051%a7\t\r=KB\u0011AAo\u0011%\ty.\u0007b\u0001\n\u000b\t\t\u000f\u0003\u0005\u0002tf\u0001\u000bQBAr\u0011%\t)0\u0007b\u0001\n\u000b\t\t\u000f\u0003\u0005\u0002xf\u0001\u000bQBAr\u0011%\tI0\u0007b\u0001\n\u000b\t\t\u000f\u0003\u0005\u0002|f\u0001\u000bQBAr\u0011\u001d\ti0\u0007C\u0001\u0003\u007f\u0014Q!\u0013(pI\u0016T!\u0001J\u0013\u0002\u0015\r|gnY;se\u0016tGO\u0003\u0002'O\u0005Q1m\u001c7mK\u000e$\u0018n\u001c8\u000b\u0003!\nQa]2bY\u0006,2AK\u0019>'\t\u00011\u0006\u0005\u0003-[=bT\"A\u0012\n\u00059\u001a#!C%O_\u0012,')Y:f!\t\u0001\u0014\u0007\u0004\u0001\u0005\u000bI\u0002!\u0019\u0001\u001b\u0003\u0003-\u001b\u0001!\u0005\u00026sA\u0011agN\u0007\u0002O%\u0011\u0001h\n\u0002\b\u001d>$\b.\u001b8h!\t1$(\u0003\u0002<O\t\u0019\u0011I\\=\u0011\u0005AjD!\u0002 \u0001\u0005\u0004!$!\u0001,\u0002\u0005\tt\u0007\u0003\u0002\u0017B_qJ!AQ\u0012\u0003\u00115\u000b\u0017N\u001c(pI\u0016\f\u0011a\u001a\t\u0003Y\u0015K!AR\u0012\u0003\u0007\u001d+g.A\u0003fcVLg\u000fE\u0002J\u0019>r!A\u000e&\n\u0005-;\u0013a\u00029bG.\fw-Z\u0005\u0003\u001b:\u0013Q!R9vSZT!aS\u0014\u0002\rqJg.\u001b;?)\u0011\t&k\u0015+\u0011\t1\u0002q\u0006\u0010\u0005\u0006\u007f\u0011\u0001\r\u0001\u0011\u0005\u0006\u0007\u0012\u0001\r\u0001\u0012\u0005\u0006\u000f\u0012\u0001\r\u0001\u0013\u000b\u0004#Z;\u0006\"B\"\u0006\u0001\u0004!\u0005\"B$\u0006\u0001\u0004A\u0015!B,S\u0013R+EC\u0001.^!\t14,\u0003\u0002]O\t!QK\\5u\u0011\u0015qf\u00011\u0001A\u0003\u0011qg/\u00197\u0002\u0007\r\u000b5\u000bF\u0002bI\u001a\u0004\"A\u000e2\n\u0005\r<#a\u0002\"p_2,\u0017M\u001c\u0005\u0006K\u001e\u0001\r\u0001Q\u0001\u0004_2$\u0007\"B4\b\u0001\u0004\u0001\u0015!\u00018\u0002\u0011\u001d\u001c\u0017m\u001d*fC\u0012$\"\u0001\u00116\t\u000b-D\u0001\u0019\u00017\u0002\u0005\r$\b\u0003\u0002\u0017n_qJ!A\\\u0012\u0003\u000fQ\u0013\u0018.Z'ba\u0006IqiQ!T?J+\u0015\t\u0012\u000b\u0003\u0001FDQa[\u0005A\u00021\fQbR\"B'~\u001bu.\u001c9mKR,Gc\u0001!um\")QO\u0003a\u0001\u0001\u0006\tQ\u000eC\u0003l\u0015\u0001\u0007A\u000e\u000b\u0002\u000bqB\u0011\u0011\u0010`\u0007\u0002u*\u00111pJ\u0001\u000bC:tw\u000e^1uS>t\u0017BA?{\u0005\u001d!\u0018-\u001b7sK\u000e\fAaR\"B'R9\u0011-!\u0001\u0002\u0004\u0005\u0015\u0001\"B3\f\u0001\u0004\u0001\u0005\"B4\f\u0001\u0004\u0001\u0005\"B6\f\u0001\u0004a\u0017!B3rk\u0006dGcB1\u0002\f\u0005=\u00111\u0003\u0005\u0007\u0003\u001ba\u0001\u0019A\u0018\u0002\u0005-\f\u0004BBA\t\u0019\u0001\u0007q&\u0001\u0002le!)1\u000e\u0004a\u0001Y\u0006)\u0011N\\8eKR\u0019\u0011+!\u0007\t\r\u0005mQ\u00021\u0001A\u0003\t\u0019g.A\u0005d_BLHk\\$f]R)\u0011+!\t\u0002&!1\u00111\u0005\bA\u0002\u0011\u000bAA\\4f]\")1N\u0004a\u0001Y\u0006Q!/Z2`S:\u001cXM\u001d;\u0015\u001f\u0005\fY#a\f\u00024\u0005u\u0012\u0011IA#\u0003\u0013Ba!!\f\u0010\u0001\u0004y\u0013!A6\t\r\u0005Er\u00021\u0001=\u0003\u00051\bbBA\u001b\u001f\u0001\u0007\u0011qG\u0001\u0003Q\u000e\u00042ANA\u001d\u0013\r\tYd\n\u0002\u0004\u0013:$\bbBA \u001f\u0001\u0007\u0011qG\u0001\u0004Y\u00164\bBBA\"\u001f\u0001\u0007\u0011+\u0001\u0004qCJ,g\u000e\u001e\u0005\u0007\u0003\u000fz\u0001\u0019\u0001#\u0002\u0011M$\u0018M\u001d;hK:DQa[\bA\u00021D#a\u0004=\u0002\u0019I,7mX5og\u0016\u0014H/\u001b4\u0015)\u0005E\u0013qKA-\u00037\ni&a\u001a\u0002l\u00055\u0014qNA9!\u00111\u00141\u000b\u001f\n\u0007\u0005UsE\u0001\u0004PaRLwN\u001c\u0005\u0007\u0003[\u0001\u0002\u0019A\u0018\t\r\u0005E\u0002\u00031\u0001=\u0011\u001d\t)\u0004\u0005a\u0001\u0003oAq!a\u0018\u0011\u0001\u0004\t\t'\u0001\u0003d_:$\u0007c\u0001\u001c\u0002d%\u0019\u0011QM\u0014\u0003\r\u0005s\u0017PU3g\u0011\u0019\tI\u0007\u0005a\u0001C\u0006Qa-\u001e7m\u000bF,\u0018\r\\:\t\u000f\u0005}\u0002\u00031\u0001\u00028!1\u00111\t\tA\u0002ECa!a\u0012\u0011\u0001\u0004!\u0005\"B6\u0011\u0001\u0004a\u0007F\u0001\ty\u0003)\u0011XmY0m_>\\W\u000f\u001d\u000b\u000f\u0003C\nI(a\u001f\u0002~\u0005}\u0014\u0011QAB\u0011\u0019\ti#\u0005a\u0001_!9\u0011QG\tA\u0002\u0005]\u0002bBA #\u0001\u0007\u0011q\u0007\u0005\u0007\u0003\u0007\n\u0002\u0019A)\t\r\u0005\u001d\u0013\u00031\u0001E\u0011\u0015Y\u0017\u00031\u0001mQ\t\t\u00020\u0001\u0006sK\u000e|&/Z7pm\u0016$\"#!\u0015\u0002\f\u00065\u0015qRAJ\u0003+\u000b9*!'\u0002\u001c\"1\u0011Q\u0006\nA\u0002=Ba!!\r\u0013\u0001\u0004a\u0004bBAI%\u0001\u0007\u0011qG\u0001\u000ee\u0016lwN^1m!>d\u0017nY=\t\u000f\u0005U\"\u00031\u0001\u00028!9\u0011q\b\nA\u0002\u0005]\u0002BBA\"%\u0001\u0007\u0011\u000b\u0003\u0004\u0002HI\u0001\r\u0001\u0012\u0005\u0006WJ\u0001\r\u0001\\\u0001\u0006G2,\u0017M\u001c\u000b\b5\u0006\u0005\u0016QUAT\u0011\u0019\t\u0019k\u0005a\u0001#\u0006\u0011a\u000e\u001a\u0005\u0006WN\u0001\r\u0001\u001c\u0005\b\u0003\u007f\u0019\u0002\u0019AA\u001c\u0003-I7OT;mY&sw\u000eZ3\u0015\u0007\u0005\fi\u000bC\u0003l)\u0001\u0007A.\u0001\u0006dC\u000eDW\rZ*ju\u0016$B!a\u000e\u00024\")1.\u0006a\u0001Y\u0006I1N\\8x]NK'0\u001a\u000b\u0005\u0003o\tI\fC\u0003l-\u0001\u0007A.\u0001\u0004tiJLgn\u001a\u000b\u0005\u0003\u007f\u000b)\u000e\u0005\u0003\u0002B\u0006=g\u0002BAb\u0003\u0017\u00042!!2(\u001b\t\t9MC\u0002\u0002JN\na\u0001\u0010:p_Rt\u0014bAAgO\u00051\u0001K]3eK\u001aLA!!5\u0002T\n11\u000b\u001e:j]\u001eT1!!4(\u0011\u001d\tyd\u0006a\u0001\u0003o\tQ!\u0013(pI\u0016\u0004\"\u0001L\r\u0014\u0007e\t\t\u0007\u0006\u0002\u0002Z\u0006Y1*R-`!J+5+\u0012(U+\t\t\u0019\u000f\u0005\u0003\u0002f\u0006=XBAAt\u0015\u0011\tI/a;\u0002\t1\fgn\u001a\u0006\u0003\u0003[\fAA[1wC&!\u0011\u0011_At\u0005\u0019y%M[3di\u0006a1*R-`!J+5+\u0012(UA\u0005Q1*R-`\u0003\n\u001bVI\u0014+\u0002\u0017-+\u0015lX!C'\u0016sE\u000bI\u0001\u0016\u0017\u0016Kv\f\u0015*F'\u0016sEkX(S?\u0006\u00135+\u0012(U\u0003YYU)W0Q%\u0016\u001bVI\u0014+`\u001fJ{\u0016IQ*F\u001dR\u0003\u0013a\u00038foJ{w\u000e\u001e(pI\u0016,bA!\u0001\u0003\b\t-A\u0003\u0002B\u0002\u0005\u001b\u0001b\u0001\f\u0001\u0003\u0006\t%\u0001c\u0001\u0019\u0003\b\u0011)!'\tb\u0001iA\u0019\u0001Ga\u0003\u0005\u000by\n#\u0019\u0001\u001b\t\r\u001d\u000b\u0003\u0019\u0001B\b!\u0011IEJ!\u0002"
)
public final class INode extends INodeBase {
   private final Equiv equiv;

   public static INode newRootNode(final Equiv equiv) {
      return INode$.MODULE$.newRootNode(equiv);
   }

   public static Object KEY_PRESENT_OR_ABSENT() {
      return INode$.MODULE$.KEY_PRESENT_OR_ABSENT();
   }

   public static Object KEY_ABSENT() {
      return INode$.MODULE$.KEY_ABSENT();
   }

   public static Object KEY_PRESENT() {
      return INode$.MODULE$.KEY_PRESENT();
   }

   public void WRITE(final MainNode nval) {
      INodeBase.updater.set(this, nval);
   }

   public boolean CAS(final MainNode old, final MainNode n) {
      return INodeBase.updater.compareAndSet(this, old, n);
   }

   public MainNode gcasRead(final TrieMap ct) {
      return this.GCAS_READ(ct);
   }

   public MainNode GCAS_READ(final TrieMap ct) {
      MainNode m = this.mainnode;
      return m.prev == null ? m : this.GCAS_Complete(m, ct);
   }

   private MainNode GCAS_Complete(final MainNode m, final TrieMap ct) {
      while(m != null) {
         MainNode prev = m.prev;
         boolean readRoot_abort = true;
         if (ct == null) {
            throw null;
         }

         INode ctr = ct.RDCSS_READ_ROOT(readRoot_abort);
         if (prev == null) {
            return m;
         }

         if (prev instanceof FailedNode) {
            FailedNode var5 = (FailedNode)prev;
            MainNode CAS_n = var5.prev;
            boolean var10000 = INodeBase.updater.compareAndSet(this, m, CAS_n);
            CAS_n = null;
            if (var10000) {
               return var5.prev;
            }

            ct = ct;
            m = this.mainnode;
         } else if (ctr.gen == this.gen && ct.nonReadOnly()) {
            if (m.CAS_PREV(prev, (MainNode)null)) {
               return m;
            }

            ct = ct;
            m = m;
         } else {
            m.CAS_PREV(prev, new FailedNode(prev));
            ct = ct;
            m = this.mainnode;
         }
      }

      return null;
   }

   public boolean GCAS(final MainNode old, final MainNode n, final TrieMap ct) {
      n.WRITE_PREV(old);
      if (INodeBase.updater.compareAndSet(this, old, n)) {
         this.GCAS_Complete(n, ct);
         return n.prev == null;
      } else {
         return false;
      }
   }

   private boolean equal(final Object k1, final Object k2, final TrieMap ct) {
      return ct.equality().equiv(k1, k2);
   }

   private INode inode(final MainNode cn) {
      INode nin = new INode(this.gen, this.equiv);
      INodeBase.updater.set(nin, cn);
      return nin;
   }

   public INode copyToGen(final Gen ngen, final TrieMap ct) {
      INode nin = new INode(ngen, this.equiv);
      MainNode main = this.GCAS_READ(ct);
      INodeBase.updater.set(nin, main);
      return nin;
   }

   public boolean rec_insert(final Object k, final Object v, final int hc, final int lev, final INode parent, final Gen startgen, final TrieMap ct) {
      while(true) {
         MainNode m = this.GCAS_READ(ct);
         if (m instanceof CNode) {
            CNode var9 = (CNode)m;
            int idx = hc >>> lev & 31;
            int flag = 1 << idx;
            int bmp = var9.bitmap();
            int mask = flag - 1;
            int pos = Integer.bitCount(bmp & mask);
            if ((bmp & flag) != 0) {
               BasicNode var15 = var9.array()[pos];
               if (var15 instanceof INode) {
                  INode var16 = (INode)var15;
                  if (startgen == var16.gen) {
                     int var10004 = lev + 5;
                     ct = ct;
                     startgen = startgen;
                     parent = this;
                     lev = var10004;
                     hc = hc;
                     v = v;
                     k = k;
                     this = var16;
                     continue;
                  }

                  if (this.GCAS(var9, var9.renewed(startgen, ct), ct)) {
                     ct = ct;
                     startgen = startgen;
                     parent = parent;
                     lev = lev;
                     hc = hc;
                     v = v;
                     k = k;
                     continue;
                  }

                  return false;
               }

               if (var15 instanceof SNode) {
                  SNode var17 = (SNode)var15;
                  if (var17.hc() == hc && this.equal(var17.k(), k, ct)) {
                     return this.GCAS(var9, var9.updatedAt(pos, new SNode(var17.k(), v, hc), this.gen), ct);
                  }

                  CNode nn = (var9.gen() == this.gen ? var9 : var9.renewed(this.gen, ct)).updatedAt(pos, this.inode(CNode$.MODULE$.dual(var17, var17.hc(), new SNode(k, v, hc), hc, lev + 5, this.gen, this.equiv)), this.gen);
                  return this.GCAS(var9, nn, ct);
               }

               throw new MatchError(var15);
            }

            CNode ncnode = (var9.gen() == this.gen ? var9 : var9.renewed(this.gen, ct)).insertedAt(pos, flag, k, v, hc, this.gen);
            return this.GCAS(var9, ncnode, ct);
         }

         if (m instanceof TNode) {
            this.clean(parent, ct, lev - 5);
            return false;
         }

         if (m instanceof LNode) {
            LNode var20 = (LNode)m;
            LNode nn = var20.inserted(k, v);
            return this.GCAS(var20, nn, ct);
         }

         throw new MatchError(m);
      }
   }

   public Option rec_insertif(final Object k, final Object v, final int hc, final Object cond, final boolean fullEquals, final int lev, final INode parent, final Gen startgen, final TrieMap ct) {
      while(true) {
         MainNode m = this.GCAS_READ(ct);
         if (m instanceof CNode) {
            CNode var11 = (CNode)m;
            int idx = hc >>> lev & 31;
            int flag = 1 << idx;
            int bmp = var11.bitmap();
            int mask = flag - 1;
            int pos = Integer.bitCount(bmp & mask);
            if ((bmp & flag) != 0) {
               BasicNode var17 = var11.array()[pos];
               if (var17 instanceof INode) {
                  INode var18 = (INode)var17;
                  if (startgen == var18.gen) {
                     int var10006 = lev + 5;
                     ct = ct;
                     startgen = startgen;
                     parent = this;
                     lev = var10006;
                     fullEquals = fullEquals;
                     cond = cond;
                     hc = hc;
                     v = v;
                     k = k;
                     this = var18;
                     continue;
                  }

                  if (this.GCAS(var11, var11.renewed(startgen, ct), ct)) {
                     ct = ct;
                     startgen = startgen;
                     parent = parent;
                     lev = lev;
                     fullEquals = fullEquals;
                     cond = cond;
                     hc = hc;
                     v = v;
                     k = k;
                     continue;
                  }

                  return null;
               }

               if (var17 instanceof SNode) {
                  SNode var19 = (SNode)var17;
                  if (BoxesRunTime.equals(INode$.MODULE$.KEY_PRESENT_OR_ABSENT(), cond)) {
                     if (var19.hc() == hc && this.equal(var19.k(), k, ct)) {
                        if (this.GCAS(var11, var11.updatedAt(pos, new SNode(var19.k(), v, hc), this.gen), ct)) {
                           return new Some(var19.v());
                        }

                        return null;
                     }

                     CNode nn = (var11.gen() == this.gen ? var11 : var11.renewed(this.gen, ct)).updatedAt(pos, this.inode(CNode$.MODULE$.dual(var19, var19.hc(), new SNode(k, v, hc), hc, lev + 5, this.gen, this.equiv)), this.gen);
                     if (this.GCAS(var11, nn, ct)) {
                        return None$.MODULE$;
                     }

                     return null;
                  }

                  if (BoxesRunTime.equals(INode$.MODULE$.KEY_ABSENT(), cond)) {
                     if (var19.hc() == hc && this.equal(var19.k(), k, ct)) {
                        return new Some(var19.v());
                     }

                     CNode nn = (var11.gen() == this.gen ? var11 : var11.renewed(this.gen, ct)).updatedAt(pos, this.inode(CNode$.MODULE$.dual(var19, var19.hc(), new SNode(k, v, hc), hc, lev + 5, this.gen, this.equiv)), this.gen);
                     if (this.GCAS(var11, nn, ct)) {
                        return None$.MODULE$;
                     }

                     return null;
                  }

                  if (BoxesRunTime.equals(INode$.MODULE$.KEY_PRESENT(), cond)) {
                     if (var19.hc() == hc && this.equal(var19.k(), k, ct)) {
                        if (this.GCAS(var11, var11.updatedAt(pos, new SNode(k, v, hc), this.gen), ct)) {
                           return new Some(var19.v());
                        }

                        return null;
                     }

                     return None$.MODULE$;
                  }

                  if (var19.hc() == hc && this.equal(var19.k(), k, ct)) {
                     if (fullEquals) {
                        if (!BoxesRunTime.equals(var19.v(), cond)) {
                           return None$.MODULE$;
                        }
                     } else if (var19.v() != cond) {
                        return None$.MODULE$;
                     }

                     if (this.GCAS(var11, var11.updatedAt(pos, new SNode(k, v, hc), this.gen), ct)) {
                        return new Some(var19.v());
                     }

                     return null;
                  }

                  return None$.MODULE$;
               }

               throw new MatchError(var17);
            }

            if (BoxesRunTime.equals(INode$.MODULE$.KEY_PRESENT_OR_ABSENT(), cond) ? true : BoxesRunTime.equals(INode$.MODULE$.KEY_ABSENT(), cond)) {
               CNode ncnode = (var11.gen() == this.gen ? var11 : var11.renewed(this.gen, ct)).insertedAt(pos, flag, k, v, hc, this.gen);
               if (this.GCAS(var11, ncnode, ct)) {
                  return None$.MODULE$;
               }

               return null;
            }

            if (BoxesRunTime.equals(INode$.MODULE$.KEY_PRESENT(), cond)) {
               return None$.MODULE$;
            }

            return None$.MODULE$;
         }

         if (m instanceof TNode) {
            this.clean(parent, ct, lev - 5);
            return null;
         }

         if (m instanceof LNode) {
            LNode var23 = (LNode)m;
            if (BoxesRunTime.equals(INode$.MODULE$.KEY_PRESENT_OR_ABSENT(), cond)) {
               Option optv = var23.get(k);
               if (this.insertln$1(var23, k, v, ct)) {
                  return optv;
               }

               return null;
            }

            if (BoxesRunTime.equals(INode$.MODULE$.KEY_ABSENT(), cond)) {
               Option var25 = var23.get(k);
               if (None$.MODULE$.equals(var25)) {
                  if (this.insertln$1(var23, k, v, ct)) {
                     return None$.MODULE$;
                  }

                  return null;
               }

               return var25;
            }

            if (BoxesRunTime.equals(INode$.MODULE$.KEY_PRESENT(), cond)) {
               Option var26 = var23.get(k);
               if (var26 instanceof Some) {
                  Object v0 = ((Some)var26).value();
                  if (this.insertln$1(var23, k, v, ct)) {
                     return new Some(v0);
                  }

                  return null;
               }

               if (None$.MODULE$.equals(var26)) {
                  return None$.MODULE$;
               }

               throw new MatchError(var26);
            }

            Option var28 = var23.get(k);
            if (var28 instanceof Some) {
               Object v0 = ((Some)var28).value();
               if (fullEquals) {
                  if (!BoxesRunTime.equals(v0, cond)) {
                     return None$.MODULE$;
                  }
               } else if (v0 != cond) {
                  return None$.MODULE$;
               }

               if (this.insertln$1(var23, k, v, ct)) {
                  return new Some(cond);
               }

               return null;
            }

            return None$.MODULE$;
         }

         throw new MatchError(m);
      }
   }

   public Object rec_lookup(final Object k, final int hc, final int lev, final INode parent, final Gen startgen, final TrieMap ct) {
      while(true) {
         MainNode m = this.GCAS_READ(ct);
         if (m instanceof CNode) {
            CNode var8 = (CNode)m;
            int idx = hc >>> lev & 31;
            int flag = 1 << idx;
            int bmp = var8.bitmap();
            if ((bmp & flag) == 0) {
               return INodeBase.NO_SUCH_ELEMENT_SENTINEL;
            }

            int pos = bmp == -1 ? idx : Integer.bitCount(bmp & flag - 1);
            BasicNode sub = var8.array()[pos];
            if (sub instanceof INode) {
               INode var14 = (INode)sub;
               if (!ct.isReadOnly() && startgen != var14.gen) {
                  if (this.GCAS(var8, var8.renewed(startgen, ct), ct)) {
                     ct = ct;
                     startgen = startgen;
                     parent = parent;
                     lev = lev;
                     hc = hc;
                     k = k;
                     continue;
                  }

                  return INodeBase.RESTART;
               }

               int var10003 = lev + 5;
               ct = ct;
               startgen = startgen;
               parent = this;
               lev = var10003;
               hc = hc;
               k = k;
               this = var14;
               continue;
            }

            if (sub instanceof SNode) {
               SNode var15 = (SNode)sub;
               if (var15.hc() == hc && this.equal(var15.k(), k, ct)) {
                  return var15.v();
               }

               return INodeBase.NO_SUCH_ELEMENT_SENTINEL;
            }

            throw new MatchError(sub);
         }

         if (m instanceof TNode) {
            TNode var16 = (TNode)m;
            return this.cleanReadOnly$1(var16, ct, parent, lev, hc, k);
         }

         if (m instanceof LNode) {
            Option var10000 = ((LNode)m).get(k);
            if (var10000 == null) {
               throw null;
            }

            Option getOrElse_this = var10000;
            if (getOrElse_this.isEmpty()) {
               return INodeBase.NO_SUCH_ELEMENT_SENTINEL;
            }

            return getOrElse_this.get();
         }

         throw new MatchError(m);
      }
   }

   public Option rec_remove(final Object k, final Object v, final int removalPolicy, final int hc, final int lev, final INode parent, final Gen startgen, final TrieMap ct) {
      MainNode var9 = this.GCAS_READ(ct);
      if (var9 instanceof CNode) {
         CNode var10 = (CNode)var9;
         int idx = hc >>> lev & 31;
         int bmp = var10.bitmap();
         int flag = 1 << idx;
         if ((bmp & flag) == 0) {
            return None$.MODULE$;
         } else {
            int pos = Integer.bitCount(bmp & flag - 1);
            BasicNode sub = var10.array()[pos];
            Object var10000;
            if (sub instanceof INode) {
               INode var17 = (INode)sub;
               var10000 = startgen == var17.gen ? var17.rec_remove(k, v, removalPolicy, hc, lev + 5, this, startgen, ct) : (this.GCAS(var10, var10.renewed(startgen, ct), ct) ? this.rec_remove(k, v, removalPolicy, hc, lev, parent, startgen, ct) : null);
            } else {
               if (!(sub instanceof SNode)) {
                  throw new MatchError(sub);
               }

               SNode var18 = (SNode)sub;
               if (var18.hc() == hc && this.equal(var18.k(), k, ct) && TrieMap.RemovalPolicy$.MODULE$.shouldRemove(removalPolicy, var18.v(), v)) {
                  MainNode ncn = var10.removedAt(pos, flag, this.gen).toContracted(lev);
                  var10000 = this.GCAS(var10, ncn, ct) ? new Some(var18.v()) : null;
               } else {
                  var10000 = None$.MODULE$;
               }
            }

            Option res = (Option)var10000;
            None$ var20 = None$.MODULE$;
            if (res != null) {
               if (res.equals(var20)) {
                  return res;
               }
            }

            if (res != null) {
               if (parent != null) {
                  MainNode n = this.GCAS_READ(ct);
                  if (n instanceof TNode) {
                     this.cleanParent$1(n, parent, ct, hc, lev, startgen);
                  }
               }

               return res;
            } else {
               return res;
            }
         }
      } else if (var9 instanceof TNode) {
         this.clean(parent, ct, lev - 5);
         return null;
      } else if (var9 instanceof LNode) {
         LNode var22 = (LNode)var9;
         if (removalPolicy == 0) {
            Option optv = var22.get(k);
            MainNode nn = var22.removed(k, ct);
            return this.GCAS(var22, nn, ct) ? optv : null;
         } else {
            Option var25 = var22.get(k);
            if (var25 instanceof Some) {
               Some var26 = (Some)var25;
               Object v0 = var26.value();
               if (TrieMap.RemovalPolicy$.MODULE$.shouldRemove(removalPolicy, v, v0)) {
                  MainNode nn = var22.removed(k, ct);
                  if (this.GCAS(var22, nn, ct)) {
                     return var26;
                  }

                  return null;
               }
            }

            return None$.MODULE$;
         }
      } else {
         throw new MatchError(var9);
      }
   }

   private void clean(final INode nd, final TrieMap ct, final int lev) {
      MainNode m = nd.GCAS_READ(ct);
      if (m instanceof CNode) {
         CNode var5 = (CNode)m;
         nd.GCAS(var5, var5.toCompressed(ct, lev, this.gen), ct);
      }
   }

   public boolean isNullInode(final TrieMap ct) {
      return this.GCAS_READ(ct) == null;
   }

   public int cachedSize(final TrieMap ct) {
      return this.GCAS_READ(ct).cachedSize(ct);
   }

   public int knownSize(final TrieMap ct) {
      return this.GCAS_READ(ct).knownSize();
   }

   public String string(final int lev) {
      StringOps$ var10000 = StringOps$.MODULE$;
      ScalaRunTime$ var10002 = ScalaRunTime$.MODULE$;
      Object[] var10003 = new Object[]{StringOps$.MODULE$.$times$extension("  ", lev), null};
      MainNode var2 = this.mainnode;
      String var10006;
      if (var2 == null) {
         var10006 = "<null>";
      } else if (var2 instanceof TNode) {
         TNode var3 = (TNode)var2;
         var10006 = StringOps$.MODULE$.format$extension("TNode(%s, %s, %d, !)", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{var3.k(), var3.v(), var3.hc()}));
      } else {
         var10006 = var2 instanceof CNode ? ((CNode)var2).string(lev) : (var2 instanceof LNode ? ((LNode)var2).string(lev) : StringOps$.MODULE$.format$extension("<elem: %s>", ScalaRunTime$.MODULE$.genericWrapArray(new Object[]{var2})));
      }

      var10003[1] = var10006;
      return var10000.format$extension("%sINode -> %s", var10002.genericWrapArray(var10003));
   }

   private final boolean insertln$1(final LNode x4$1, final Object k$1, final Object v$1, final TrieMap ct$1) {
      LNode nn = x4$1.inserted(k$1, v$1);
      return this.GCAS(x4$1, nn, ct$1);
   }

   private final Object cleanReadOnly$1(final TNode tn, final TrieMap ct$2, final INode parent$1, final int lev$1, final int hc$1, final Object k$2) {
      if (ct$2.nonReadOnly()) {
         this.clean(parent$1, ct$2, lev$1 - 5);
         return INodeBase.RESTART;
      } else {
         return tn.hc() == hc$1 && BoxesRunTime.equals(tn.k(), k$2) ? tn.v() : INodeBase.NO_SUCH_ELEMENT_SENTINEL;
      }
   }

   // $FF: synthetic method
   public static final Object $anonfun$rec_lookup$1() {
      return INodeBase.NO_SUCH_ELEMENT_SENTINEL;
   }

   private final void cleanParent$1(final Object nonlive, final INode parent$2, final TrieMap ct$3, final int hc$2, final int lev$2, final Gen startgen$1) {
      while(true) {
         label31: {
            MainNode cn = parent$2.GCAS_READ(ct$3);
            if (cn instanceof CNode) {
               CNode var8 = (CNode)cn;
               int idx = hc$2 >>> lev$2 - 5 & 31;
               int bmp = var8.bitmap();
               int flag = 1 << idx;
               if ((bmp & flag) != 0) {
                  int pos = Integer.bitCount(bmp & flag - 1);
                  if (var8.array()[pos] == this) {
                     if (!(nonlive instanceof TNode)) {
                        throw new MatchError(nonlive);
                     }

                     TNode var13 = (TNode)nonlive;
                     MainNode ncn = var8.updatedAt(pos, var13.copyUntombed(), this.gen).toContracted(lev$2 - 5);
                     if (!parent$2.GCAS(var8, ncn, ct$3)) {
                        if (ct$3 == null) {
                           throw null;
                        }

                        boolean readRoot_abort = false;
                        Gen var10000 = ct$3.RDCSS_READ_ROOT(readRoot_abort).gen;
                        if (var10000 == null) {
                           if (startgen$1 != null) {
                              return;
                           }
                           break label31;
                        }

                        if (var10000.equals(startgen$1)) {
                           break label31;
                        }
                     }
                  }
               }
            }

            return;
         }

         nonlive = nonlive;
      }
   }

   public INode(final MainNode bn, final Gen g, final Equiv equiv) {
      super(g);
      this.equiv = equiv;
      INodeBase.updater.set(this, bn);
   }

   public INode(final Gen g, final Equiv equiv) {
      this((MainNode)null, g, equiv);
   }
}
