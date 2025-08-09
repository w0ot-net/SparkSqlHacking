package scala.collection.immutable;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.MatchError;
import scala.None$;
import scala.Option;
import scala.Predef$;
import scala.Some;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.Iterator$;
import scala.collection.mutable.ReusableBuilder;
import scala.reflect.ClassTag$;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Nothing$;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\tec\u0001\u0002\u0015*\rAB\u0001\"\u0012\u0001\u0003\u0006\u0004%\tA\u0012\u0005\t\u0015\u0002\u0011\t\u0011)A\u0005\u000f\"A1\n\u0001BC\u0002\u0013\u0005a\t\u0003\u0005M\u0001\t\u0005\t\u0015!\u0003H\u0011!i\u0005A!a\u0001\n\u0003q\u0005\u0002C0\u0001\u0005\u0003\u0007I\u0011\u00011\t\u0011\u0019\u0004!\u0011!Q!\n=CQa\u001a\u0001\u0005\u0002!Da!\u001c\u0001\u0005\u0002%r\u0007\"B9\u0001\t\u00031\u0005\"\u0002:\u0001\t\u0003\u0019\b\"B=\u0001\t\u0003Q\bbBA\u0003\u0001\u0011\u0005\u0013q\u0001\u0005\b\u0003'\u0001A\u0011AA\u000b\u0011\u001d\t\u0019\u0004\u0001C!\u0003kAq!!\u0012\u0001\t\u0003\t9\u0005C\u0004\u0002Z\u0001!\t!a\u0017\t\u000f\u0005M\u0004\u0001\"\u0001\u0002v!9\u0011q\u0011\u0001\u0005\u0002\u0005%\u0005BBAF\u0001\u0011\u0005a\tC\u0004\u0002\u000e\u0002!\t!a$\t\u000f\u0005U\u0005\u0001\"\u0001\u0002\n\"1\u0011q\u0013\u0001\u0005\u0002\u0019Cq!!'\u0001\t\u0003\tY\nC\u0004\u0002 \u0002!\t!!)\t\u000f\u0005\u0015\u0006\u0001\"\u0001\u0002(\"9\u00111\u0016\u0001\u0005B\u00055\u0006bBAY\u0001\u0011\u0005\u00111\u0017\u0005\b\u0003\u000b\u0004A\u0011AAd\u0011\u001d\t9\u000e\u0001C!\u00033Dq!a9\u0001\t\u0003\n)\u000fC\u0004\u0002v\u0002!\t%a>\t\u000f\u0005u\b\u0001\"\u0011\u0002\u0000\"9!q\u0002\u0001\u0005B\tE\u0001b\u0002B\u001a\u0001\u0011\u0005#Q\u0007\u0005\b\u0005\u0003\u0002A\u0011\tB\"\u0011\u001d\u0011y\u0005\u0001C!\u0005#BqAa\u0015\u0001\t\u0003\u0012)\u0006\u0003\u0004\u0003X\u0001!\tE\u0012\u0002\u0015\u0011\u0006\u001c\bnQ8mY&\u001c\u0018n\u001c8NCBtu\u000eZ3\u000b\u0005)Z\u0013!C5n[V$\u0018M\u00197f\u0015\taS&\u0001\u0006d_2dWm\u0019;j_:T\u0011AL\u0001\u0006g\u000e\fG.Y\u0002\u0001+\r\t\u0004hQ\n\u0003\u0001I\u0002Ba\r\u001b7\u00056\t\u0011&\u0003\u00026S\t9Q*\u00199O_\u0012,\u0007CA\u001c9\u0019\u0001!Q!\u000f\u0001C\u0002i\u0012\u0011aS\t\u0003w}\u0002\"\u0001P\u001f\u000e\u00035J!AP\u0017\u0003\u000f9{G\u000f[5oOB\u0011A\bQ\u0005\u0003\u00036\u00121!\u00118z!\t94\t\u0002\u0004E\u0001\u0011\u0015\rA\u000f\u0002\u0002-\u0006aqN]5hS:\fG\u000eS1tQV\tq\t\u0005\u0002=\u0011&\u0011\u0011*\f\u0002\u0004\u0013:$\u0018!D8sS\u001eLg.\u00197ICND\u0007%\u0001\u0003iCND\u0017!\u00025bg\"\u0004\u0013aB2p]R,g\u000e^\u000b\u0002\u001fB\u00191\u0007\u0015*\n\u0005EK#A\u0002,fGR|'\u000f\u0005\u0003='Z*\u0016B\u0001+.\u0005\u0019!V\u000f\u001d7fe)\u0012!IV\u0016\u0002/B\u0011\u0001,X\u0007\u00023*\u0011!lW\u0001\nk:\u001c\u0007.Z2lK\u0012T!\u0001X\u0017\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002_3\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0017\r|g\u000e^3oi~#S-\u001d\u000b\u0003C\u0012\u0004\"\u0001\u00102\n\u0005\rl#\u0001B+oSRDq!\u001a\u0004\u0002\u0002\u0003\u0007q*A\u0002yIE\n\u0001bY8oi\u0016tG\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\t%T7\u000e\u001c\t\u0005g\u00011$\tC\u0003F\u0011\u0001\u0007q\tC\u0003L\u0011\u0001\u0007q\tC\u0003N\u0011\u0001\u0007q*A\u0004j]\u0012,\u0007p\u00144\u0015\u0005\u001d{\u0007\"\u00029\n\u0001\u0004y\u0014aA6fs\u0006!1/\u001b>f\u0003\u0015\t\u0007\u000f\u001d7z)\u0015\u0011E/\u001e<x\u0011\u0015\u00018\u00021\u00017\u0011\u0015)5\u00021\u0001H\u0011\u0015Y5\u00021\u0001H\u0011\u0015A8\u00021\u0001H\u0003\u0015\u0019\b.\u001b4u\u0003\r9W\r\u001e\u000b\bwz|\u0018\u0011AA\u0002!\raDPQ\u0005\u0003{6\u0012aa\u00149uS>t\u0007\"\u00029\r\u0001\u00041\u0004\"B#\r\u0001\u00049\u0005\"B&\r\u0001\u00049\u0005\"\u0002=\r\u0001\u00049\u0015\u0001C4fiR+\b\u000f\\3\u0015\u0015\u0005%\u00111BA\u0007\u0003\u001f\t\t\u0002\u0005\u0003='Z\u0012\u0005\"\u00029\u000e\u0001\u00041\u0004\"B#\u000e\u0001\u00049\u0005\"B&\u000e\u0001\u00049\u0005\"\u0002=\u000e\u0001\u00049\u0015!C4fi>\u0013X\t\\:f+\u0011\t9\"a\u0007\u0015\u0019\u0005e\u0011\u0011EA\u0012\u0003K\t9#!\u000b\u0011\u0007]\nY\u0002B\u0004\u0002\u001e9\u0011\r!a\b\u0003\u0005Y\u000b\u0014C\u0001\"@\u0011\u0015\u0001h\u00021\u00017\u0011\u0015)e\u00021\u0001H\u0011\u0015Ye\u00021\u0001H\u0011\u0015Ah\u00021\u0001H\u0011!\tYC\u0004CA\u0002\u00055\u0012!\u00014\u0011\u000bq\ny#!\u0007\n\u0007\u0005ERF\u0001\u0005=Eft\u0017-\\3?\u0003-\u0019wN\u001c;bS:\u001c8*Z=\u0015\u0015\u0005]\u0012QHA \u0003\u0003\n\u0019\u0005E\u0002=\u0003sI1!a\u000f.\u0005\u001d\u0011un\u001c7fC:DQ\u0001]\bA\u0002YBQ!R\bA\u0002\u001dCQaS\bA\u0002\u001dCQ\u0001_\bA\u0002\u001d\u000b\u0001bY8oi\u0006Lgn]\u000b\u0005\u0003\u0013\n\u0019\u0006\u0006\u0006\u00028\u0005-\u0013QJA+\u0003/BQ\u0001\u001d\tA\u0002YBq!a\u0014\u0011\u0001\u0004\t\t&A\u0003wC2,X\rE\u00028\u0003'\"q!!\b\u0011\u0005\u0004\ty\u0002C\u0003L!\u0001\u0007q\tC\u0003y!\u0001\u0007q)A\u0004va\u0012\fG/\u001a3\u0016\t\u0005u\u00131\r\u000b\u000f\u0003?\n)'a\u001a\u0002j\u0005-\u0014QNA8!\u0015\u0019DGNA1!\r9\u00141\r\u0003\b\u0003;\t\"\u0019AA\u0010\u0011\u0015\u0001\u0018\u00031\u00017\u0011\u001d\ty%\u0005a\u0001\u0003CBQ!R\tA\u0002\u001dCQaS\tA\u0002\u001dCQ\u0001_\tA\u0002\u001dCq!!\u001d\u0012\u0001\u0004\t9$\u0001\u0007sKBd\u0017mY3WC2,X-A\u0004sK6|g/\u001a3\u0016\t\u0005]\u0014Q\u0010\u000b\u000b\u0003s\ny(!!\u0002\u0004\u0006\u0015\u0005#B\u001a5m\u0005m\u0004cA\u001c\u0002~\u00119\u0011Q\u0004\nC\u0002\u0005}\u0001\"\u00029\u0013\u0001\u00041\u0004\"B#\u0013\u0001\u00049\u0005\"B&\u0013\u0001\u00049\u0005\"\u0002=\u0013\u0001\u00049\u0015\u0001\u00035bg:{G-Z:\u0016\u0005\u0005]\u0012!\u00038pI\u0016\f%/\u001b;z\u0003\u001d9W\r\u001e(pI\u0016$2AMAI\u0011\u0019\t\u0019*\u0006a\u0001\u000f\u0006)\u0011N\u001c3fq\u0006Q\u0001.Y:QCfdw.\u00193\u0002\u0019A\f\u0017\u0010\\8bI\u0006\u0013\u0018\u000e^=\u0002\r\u001d,GoS3z)\r1\u0014Q\u0014\u0005\u0007\u0003'C\u0002\u0019A$\u0002\u0011\u001d,GOV1mk\u0016$2AQAR\u0011\u0019\t\u0019*\u0007a\u0001\u000f\u0006Qq-\u001a;QCfdw.\u00193\u0015\t\u0005%\u0011\u0011\u0016\u0005\u0007\u0003'S\u0002\u0019A$\u0002\u000f\u001d,G\u000fS1tQR\u0019q)a,\t\r\u0005M5\u00041\u0001H\u0003\u001d1wN]3bG\",B!!.\u0002BR\u0019\u0011-a.\t\u000f\u0005-B\u00041\u0001\u0002:B9A(a/\u0002\n\u0005}\u0016bAA_[\tIa)\u001e8di&|g.\r\t\u0004o\u0005\u0005GABAb9\t\u0007!HA\u0001V\u000311wN]3bG\",e\u000e\u001e:z+\u0011\tI-!6\u0015\u0007\u0005\fY\rC\u0004\u0002,u\u0001\r!!4\u0011\u000fq\nyM\u000e\"\u0002T&\u0019\u0011\u0011[\u0017\u0003\u0013\u0019+hn\u0019;j_:\u0014\u0004cA\u001c\u0002V\u00121\u00111Y\u000fC\u0002i\nqBZ8sK\u0006\u001c\u0007nV5uQ\"\u000b7\u000f\u001b\u000b\u0004C\u0006m\u0007bBA\u0016=\u0001\u0007\u0011Q\u001c\t\by\u0005}gGQ$b\u0013\r\t\t/\f\u0002\n\rVt7\r^5p]N\n\u0011\u0002\u001e:b]N4wN]7\u0016\t\u0005\u001d\u0018Q\u001e\u000b\u0005\u0003S\f\t\u0010E\u00034\u0001Y\nY\u000fE\u00028\u0003[$a!a< \u0005\u0004Q$!A,\t\u000f\u0005-r\u00041\u0001\u0002tB9A(a47\u0005\u0006-\u0018AB3rk\u0006d7\u000f\u0006\u0003\u00028\u0005e\bBBA~A\u0001\u0007q(\u0001\u0003uQ\u0006$\u0018AB2p]\u000e\fG/\u0006\u0003\u0003\u0002\t\u001dAC\u0002B\u0002\u0005\u0013\u0011i\u0001E\u00034\u0001Y\u0012)\u0001E\u00028\u0005\u000f!q!!\b\"\u0005\u0004\ty\u0002C\u0004\u0002|\u0006\u0002\rAa\u0003\u0011\u000bM\"dG!\u0002\t\u000ba\f\u0003\u0019A$\u0002\u00135,'oZ3J]R|W\u0003\u0002B\n\u0005C!\u0002B!\u0006\u0003$\t\u001d\"\u0011\u0007\u000b\u0004C\n]\u0001b\u0002B\rE\u0001\u0007!1D\u0001\u0007[\u0016\u0014x-\u001a4\u0011\u0013q\ny-!\u0003\u0003\u001e\tu\u0001#\u0002\u001fTm\t}\u0001cA\u001c\u0003\"\u00119\u0011Q\u0004\u0012C\u0002\u0005}\u0001bBA~E\u0001\u0007!Q\u0005\t\u0006gQ2$q\u0004\u0005\b\u0005S\u0011\u0003\u0019\u0001B\u0016\u0003\u001d\u0011W/\u001b7eKJ\u0004ba\rB\u0017m\t}\u0011b\u0001B\u0018S\tq\u0001*Y:i\u001b\u0006\u0004()^5mI\u0016\u0014\b\"\u0002=#\u0001\u00049\u0015a\u00022vS2$Gk\\\u000b\u0005\u0005o\u0011y\u0004F\u0002b\u0005sAqA!\u000b$\u0001\u0004\u0011Y\u0004\u0005\u00044\u0005[1$Q\b\t\u0004o\t}BaBA\u000fG\t\u0007\u0011qD\u0001\u000bM&dG/\u001a:J[BdG#\u0002\u001a\u0003F\t-\u0003b\u0002B$I\u0001\u0007!\u0011J\u0001\u0005aJ,G\rE\u0004=\u0003w\u000bI!a\u000e\t\u000f\t5C\u00051\u0001\u00028\u00059a\r\\5qa\u0016$\u0017\u0001B2paf$\u0012![\u0001\tQ\u0006\u001c\bnQ8eKR\tq)\u0001\rdC\u000eDW\r\u001a&bm\u0006\\U-_*fi\"\u000b7\u000f[\"pI\u0016\u0004"
)
public final class HashCollisionMapNode extends MapNode {
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

   public int indexOf(final Object key) {
      Iterator iter = this.content().iterator();

      for(int i = 0; iter.hasNext(); ++i) {
         if (BoxesRunTime.equals(((Tuple2)iter.next())._1(), key)) {
            return i;
         }
      }

      return -1;
   }

   public int size() {
      return this.content().length();
   }

   public Object apply(final Object key, final int originalHash, final int hash, final int shift) {
      Option var10000 = this.get(key, originalHash, hash, shift);
      if (var10000 == null) {
         throw null;
      } else {
         Option getOrElse_this = var10000;
         if (getOrElse_this.isEmpty()) {
            throw $anonfun$apply$1();
         } else {
            return getOrElse_this.get();
         }
      }
   }

   public Option get(final Object key, final int originalHash, final int hash, final int shift) {
      if (this.hash() == hash) {
         int index = this.indexOf(key);
         return (Option)(index >= 0 ? new Some(((Tuple2)this.content().apply(index))._2()) : None$.MODULE$);
      } else {
         return None$.MODULE$;
      }
   }

   public Tuple2 getTuple(final Object key, final int originalHash, final int hash, final int shift) {
      int index = this.indexOf(key);
      if (index >= 0) {
         return (Tuple2)this.content().apply(index);
      } else {
         Iterator$ var10000 = Iterator$.MODULE$;
         return (Tuple2)Iterator$.scala$collection$Iterator$$_empty.next();
      }
   }

   public Object getOrElse(final Object key, final int originalHash, final int hash, final int shift, final Function0 f) {
      if (this.hash() == hash) {
         int var6 = this.indexOf(key);
         switch (var6) {
            case -1:
               return f.apply();
            default:
               return ((Tuple2)this.content().apply(var6))._2();
         }
      } else {
         return f.apply();
      }
   }

   public boolean containsKey(final Object key, final int originalHash, final int hash, final int shift) {
      return this.hash() == hash && this.indexOf(key) >= 0;
   }

   public boolean contains(final Object key, final Object value, final int hash, final int shift) {
      if (this.hash() == hash) {
         int index = this.indexOf(key);
         if (index >= 0 && ((Tuple2)this.content().apply(index))._2() == value) {
            return true;
         }
      }

      return false;
   }

   public MapNode updated(final Object key, final Object value, final int originalHash, final int hash, final int shift, final boolean replaceValue) {
      int index = this.indexOf(key);
      if (index >= 0) {
         if (replaceValue) {
            return ((Tuple2)this.content().apply(index))._2() == value ? this : new HashCollisionMapNode(originalHash, hash, this.content().updated(index, new Tuple2(key, value)));
         } else {
            return this;
         }
      } else {
         return new HashCollisionMapNode(originalHash, hash, this.content().appended(new Tuple2(key, value)));
      }
   }

   public MapNode removed(final Object key, final int originalHash, final int hash, final int shift) {
      if (!this.containsKey(key, originalHash, hash, shift)) {
         return this;
      } else {
         Vector var10000 = this.content();
         Function1 filterNot_pred = (keyValuePair) -> BoxesRunTime.boxToBoolean($anonfun$removed$1(key, keyValuePair));
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
                     BigVector var111 = (BigVector)filterNot_this;
                     Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$2;
                     BigVector filterImpl_foreachRest_this = var111;
                     int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                     for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                        VectorStatics$ var112 = VectorStatics$.MODULE$;
                        VectorInline$ var114 = VectorInline$.MODULE$;
                        int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                        int var115 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                        Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                        int filterImpl_foreachRest_foreachRec_level = var115;
                        VectorStatics$ filterImpl_foreachRest_foreachRec_this = var112;
                        int filterImpl_foreachRest_foreachRec_i = 0;
                        int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                        if (filterImpl_foreachRest_foreachRec_level == 0) {
                           for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object var102 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              Tuple2 var107 = (Tuple2)var102;
                              if ($anonfun$removed$1(key, var107) != filterImpl_isFlipped) {
                                 filterImpl_b.addOne(var102);
                              }
                           }
                        } else {
                           for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object[] filterImpl_foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              int filterImpl_foreachRec_i = 0;
                              int filterImpl_foreachRec_len = filterImpl_foreachRec_a.length;
                              if (filterImpl_foreachRest_foreachRec_l == 0) {
                                 for(; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object var101 = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    Tuple2 var106 = (Tuple2)var101;
                                    if ($anonfun$removed$1(key, var106) != filterImpl_isFlipped) {
                                       filterImpl_b.addOne(var101);
                                    }
                                 }
                              } else {
                                 for(int filterImpl_foreachRec_l = filterImpl_foreachRest_foreachRec_l - 1; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object[] foreachRec_a = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    int foreachRec_i = 0;
                                    int foreachRec_len = foreachRec_a.length;
                                    if (filterImpl_foreachRec_l == 0) {
                                       for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                          Object var64 = foreachRec_a[foreachRec_i];
                                          Tuple2 var105 = (Tuple2)var64;
                                          if ($anonfun$removed$1(key, var105) != filterImpl_isFlipped) {
                                             filterImpl_b.addOne(var64);
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

                        Object var86 = null;
                        filterImpl_foreachRest_foreachRec_a = null;
                     }

                     Object var82 = null;
                     filterImpl_foreachRest_f = null;
                     Object var87 = null;
                     Object var90 = null;
                     var10000 = filterImpl_b.result();
                  } else {
                     var10000 = filterNot_this;
                  }
                  break;
               }

               Tuple2 var62 = (Tuple2)filterNot_this.prefix1()[filterImpl_i];
               if ($anonfun$removed$1(key, var62) == filterImpl_isFlipped) {
                  int filterImpl_bitmap = 0;

                  for(int filterImpl_j = filterImpl_i + 1; filterImpl_j < filterImpl_len; ++filterImpl_j) {
                     var62 = (Tuple2)filterNot_this.prefix1()[filterImpl_j];
                     if ($anonfun$removed$1(key, var62) != filterImpl_isFlipped) {
                        filterImpl_bitmap |= 1 << filterImpl_j;
                     }
                  }

                  int filterImpl_newLen = filterImpl_i + Integer.bitCount(filterImpl_bitmap);
                  if (filterNot_this instanceof BigVector) {
                     VectorBuilder filterImpl_b = new VectorBuilder();

                     for(int filterImpl_k = 0; filterImpl_k < filterImpl_i; ++filterImpl_k) {
                        filterImpl_b.addOne(filterNot_this.prefix1()[filterImpl_k]);
                     }

                     for(int var69 = filterImpl_i + 1; filterImpl_i != filterImpl_newLen; ++var69) {
                        if ((1 << var69 & filterImpl_bitmap) != 0) {
                           filterImpl_b.addOne(filterNot_this.prefix1()[var69]);
                           ++filterImpl_i;
                        }
                     }

                     BigVector var108 = (BigVector)filterNot_this;
                     Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$1;
                     BigVector filterImpl_foreachRest_this = var108;
                     int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                     for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                        VectorStatics$ var109 = VectorStatics$.MODULE$;
                        VectorInline$ var10001 = VectorInline$.MODULE$;
                        int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                        int var113 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                        Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                        int filterImpl_foreachRest_foreachRec_level = var113;
                        VectorStatics$ filterImpl_foreachRest_foreachRec_this = var109;
                        int filterImpl_foreachRest_foreachRec_i = 0;
                        int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                        if (filterImpl_foreachRest_foreachRec_level == 0) {
                           for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object var100 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              Tuple2 var104 = (Tuple2)var100;
                              if ($anonfun$removed$1(key, var104) != filterImpl_isFlipped) {
                                 filterImpl_b.addOne(var100);
                              }
                           }
                        } else {
                           for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                              Object[] filterImpl_foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                              int filterImpl_foreachRec_i = 0;
                              int filterImpl_foreachRec_len = filterImpl_foreachRec_a.length;
                              if (filterImpl_foreachRest_foreachRec_l == 0) {
                                 for(; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object var99 = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    Tuple2 var103 = (Tuple2)var99;
                                    if ($anonfun$removed$1(key, var103) != filterImpl_isFlipped) {
                                       filterImpl_b.addOne(var99);
                                    }
                                 }
                              } else {
                                 for(int filterImpl_foreachRec_l = filterImpl_foreachRest_foreachRec_l - 1; filterImpl_foreachRec_i < filterImpl_foreachRec_len; ++filterImpl_foreachRec_i) {
                                    Object[] foreachRec_a = filterImpl_foreachRec_a[filterImpl_foreachRec_i];
                                    int foreachRec_i = 0;
                                    int foreachRec_len = foreachRec_a.length;
                                    if (filterImpl_foreachRec_l == 0) {
                                       for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                          Object var63 = foreachRec_a[foreachRec_i];
                                          Tuple2 var65 = (Tuple2)var63;
                                          if ($anonfun$removed$1(key, var65) != filterImpl_isFlipped) {
                                             filterImpl_b.addOne(var63);
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

                        Object var76 = null;
                        filterImpl_foreachRest_foreachRec_a = null;
                     }

                     Object var72 = null;
                     filterImpl_foreachRest_f = null;
                     Object var77 = null;
                     Object var80 = null;
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

            Object var68 = null;
            Object var70 = null;
            Object var71 = null;
            Object var73 = null;
            Object var75 = null;
            Object var78 = null;
            Object var81 = null;
            Object var83 = null;
            Object var85 = null;
            Object var88 = null;
            Object var91 = null;
            Object var93 = null;
            Object var95 = null;
            Object var66 = null;
            filterNot_pred = null;
            Vector updatedContent = var10000;
            if (updatedContent == null) {
               throw null;
            } else {
               switch (updatedContent.length()) {
                  case 1:
                     Tuple2 var6 = (Tuple2)updatedContent.apply(0);
                     if (var6 != null) {
                        Object k = var6._1();
                        Object v = var6._2();
                        Node$ var10002 = Node$.MODULE$;
                        var10002 = Node$.MODULE$;
                        byte maskFrom_shift = 0;
                        int bitposFrom_mask = hash >>> maskFrom_shift & 31;
                        return new BitmapIndexedMapNode(1 << bitposFrom_mask, 0, new Object[]{k, v}, new int[]{originalHash}, 1, hash);
                     }

                     throw new MatchError((Object)null);
                  default:
                     return new HashCollisionMapNode(originalHash, hash, updatedContent);
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

   public MapNode getNode(final int index) {
      throw new IndexOutOfBoundsException("No sub-nodes present in hash-collision leaf node.");
   }

   public boolean hasPayload() {
      return true;
   }

   public int payloadArity() {
      return this.content().length();
   }

   public Object getKey(final int index) {
      return this.getPayload(index)._1();
   }

   public Object getValue(final int index) {
      return this.getPayload(index)._2();
   }

   public Tuple2 getPayload(final int index) {
      return (Tuple2)this.content().apply(index);
   }

   public int getHash(final int index) {
      return this.originalHash();
   }

   public void foreach(final Function1 f) {
      Vector var10000 = this.content();
      if (var10000 == null) {
         throw null;
      } else {
         Vector foreach_this = var10000;
         int foreach_c = foreach_this.vectorSliceCount();

         for(int foreach_i = 0; foreach_i < foreach_c; ++foreach_i) {
            VectorStatics$ var19 = VectorStatics$.MODULE$;
            VectorInline$ var10001 = VectorInline$.MODULE$;
            int foreach_vectorSliceDim_c = foreach_c / 2;
            int var20 = foreach_vectorSliceDim_c + 1 - Math.abs(foreach_i - foreach_vectorSliceDim_c) - 1;
            Object[] foreach_foreachRec_a = foreach_this.vectorSlice(foreach_i);
            int foreach_foreachRec_level = var20;
            VectorStatics$ foreach_foreachRec_this = var19;
            int foreach_foreachRec_i = 0;
            int foreach_foreachRec_len = foreach_foreachRec_a.length;
            if (foreach_foreachRec_level == 0) {
               while(foreach_foreachRec_i < foreach_foreachRec_len) {
                  f.apply(foreach_foreachRec_a[foreach_foreachRec_i]);
                  ++foreach_foreachRec_i;
               }
            } else {
               for(int foreach_foreachRec_l = foreach_foreachRec_level - 1; foreach_foreachRec_i < foreach_foreachRec_len; ++foreach_foreachRec_i) {
                  Object[] foreachRec_a = foreach_foreachRec_a[foreach_foreachRec_i];
                  int foreachRec_i = 0;
                  int foreachRec_len = foreachRec_a.length;
                  if (foreach_foreachRec_l == 0) {
                     while(foreachRec_i < foreachRec_len) {
                        f.apply(foreachRec_a[foreachRec_i]);
                        ++foreachRec_i;
                     }
                  } else {
                     for(int foreachRec_l = foreach_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                        foreach_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], f);
                     }
                  }

                  foreachRec_a = null;
               }
            }

            Object var16 = null;
            foreach_foreachRec_a = null;
         }

      }
   }

   public void foreachEntry(final Function2 f) {
      Vector var10000 = this.content();
      Function1 foreach_f = (x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            return f.apply(k, v);
         } else {
            throw new MatchError((Object)null);
         }
      };
      if (var10000 == null) {
         throw null;
      } else {
         Vector foreach_this = var10000;
         int foreach_c = foreach_this.vectorSliceCount();

         for(int foreach_i = 0; foreach_i < foreach_c; ++foreach_i) {
            VectorStatics$ var30 = VectorStatics$.MODULE$;
            VectorInline$ var10001 = VectorInline$.MODULE$;
            int foreach_vectorSliceDim_c = foreach_c / 2;
            int var31 = foreach_vectorSliceDim_c + 1 - Math.abs(foreach_i - foreach_vectorSliceDim_c) - 1;
            Object[] foreach_foreachRec_a = foreach_this.vectorSlice(foreach_i);
            int foreach_foreachRec_level = var31;
            VectorStatics$ foreach_foreachRec_this = var30;
            int foreach_foreachRec_i = 0;
            int foreach_foreachRec_len = foreach_foreachRec_a.length;
            if (foreach_foreachRec_level == 0) {
               while(foreach_foreachRec_i < foreach_foreachRec_len) {
                  Tuple2 var25 = (Tuple2)foreach_foreachRec_a[foreach_foreachRec_i];
                  if (var25 == null) {
                     throw new MatchError((Object)null);
                  }

                  Object $anonfun$foreachEntry$1_k = var25._1();
                  Object $anonfun$foreachEntry$1_v = var25._2();
                  f.apply($anonfun$foreachEntry$1_k, $anonfun$foreachEntry$1_v);
                  $anonfun$foreachEntry$1_k = null;
                  $anonfun$foreachEntry$1_v = null;
                  ++foreach_foreachRec_i;
               }
            } else {
               for(int foreach_foreachRec_l = foreach_foreachRec_level - 1; foreach_foreachRec_i < foreach_foreachRec_len; ++foreach_foreachRec_i) {
                  Object[] foreachRec_a = foreach_foreachRec_a[foreach_foreachRec_i];
                  int foreachRec_i = 0;
                  int foreachRec_len = foreachRec_a.length;
                  if (foreach_foreachRec_l == 0) {
                     while(foreachRec_i < foreachRec_len) {
                        Tuple2 var17 = (Tuple2)foreachRec_a[foreachRec_i];
                        if (var17 == null) {
                           throw new MatchError((Object)null);
                        }

                        Object $anonfun$foreachEntry$1_k = var17._1();
                        Object $anonfun$foreachEntry$1_v = var17._2();
                        f.apply($anonfun$foreachEntry$1_k, $anonfun$foreachEntry$1_v);
                        $anonfun$foreachEntry$1_k = null;
                        $anonfun$foreachEntry$1_v = null;
                        ++foreachRec_i;
                     }
                  } else {
                     for(int foreachRec_l = foreach_foreachRec_l - 1; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                        foreach_foreachRec_this.foreachRec(foreachRec_l, foreachRec_a[foreachRec_i], foreach_f);
                     }
                  }

                  foreachRec_a = null;
               }
            }

            Object var22 = null;
            foreach_foreachRec_a = null;
         }

      }
   }

   public void foreachWithHash(final Function3 f) {
      Iterator iter = this.content().iterator();

      while(iter.hasNext()) {
         Tuple2 next = (Tuple2)iter.next();
         f.apply(next._1(), next._2(), this.originalHash());
      }

   }

   public HashCollisionMapNode transform(final Function2 f) {
      Vector$ var10000 = Vector$.MODULE$;
      ReusableBuilder newContent = new VectorBuilder();
      Iterator contentIter = this.content().iterator();

      boolean anyChanges;
      Object v;
      Object newValue;
      for(anyChanges = false; contentIter.hasNext(); anyChanges = anyChanges || v != newValue) {
         Tuple2 var5 = (Tuple2)contentIter.next();
         if (var5 == null) {
            throw new MatchError((Object)null);
         }

         Object k = var5._1();
         v = var5._2();
         newValue = f.apply(k, v);
         Tuple2 addOne_elem = new Tuple2(k, newValue);
         ((VectorBuilder)newContent).addOne(addOne_elem);
         addOne_elem = null;
      }

      if (anyChanges) {
         return new HashCollisionMapNode(this.originalHash(), this.hash(), ((VectorBuilder)newContent).result());
      } else {
         return this;
      }
   }

   public boolean equals(final Object that) {
      if (!(that instanceof HashCollisionMapNode)) {
         return false;
      } else {
         HashCollisionMapNode var2 = (HashCollisionMapNode)that;
         if (this != var2) {
            if (this.hash() == var2.hash() && this.content().length() == var2.content().length()) {
               Iterator iter = this.content().iterator();

               Object value;
               int index;
               do {
                  if (!iter.hasNext()) {
                     return true;
                  }

                  Tuple2 var4 = (Tuple2)iter.next();
                  if (var4 == null) {
                     throw new MatchError((Object)null);
                  }

                  Object key = var4._1();
                  value = var4._2();
                  index = var2.indexOf(key);
               } while(index >= 0 && BoxesRunTime.equals(value, ((Tuple2)var2.content().apply(index))._2()));

               return false;
            } else {
               return false;
            }
         } else {
            return true;
         }
      }
   }

   public HashCollisionMapNode concat(final MapNode that, final int shift) {
      if (that instanceof HashCollisionMapNode) {
         HashCollisionMapNode var3 = (HashCollisionMapNode)that;
         if (var3 == this) {
            return this;
         } else {
            VectorBuilder newContent = null;
            Iterator iter = this.content().iterator();

            while(iter.hasNext()) {
               Tuple2 nextPayload = (Tuple2)iter.next();
               if (var3.indexOf(nextPayload._1()) < 0) {
                  if (newContent == null) {
                     newContent = new VectorBuilder();
                     newContent.addAll(var3.content());
                  }

                  newContent.addOne(nextPayload);
               }
            }

            if (newContent == null) {
               return var3;
            } else {
               return new HashCollisionMapNode(this.originalHash(), this.hash(), newContent.result());
            }
         }
      } else if (that instanceof BitmapIndexedMapNode) {
         throw new UnsupportedOperationException("Cannot concatenate a HashCollisionMapNode with a BitmapIndexedMapNode");
      } else {
         throw new MatchError(that);
      }
   }

   public void mergeInto(final MapNode that, final HashMapBuilder builder, final int shift, final Function2 mergef) {
      if (that instanceof HashCollisionMapNode) {
         HashCollisionMapNode var5 = (HashCollisionMapNode)that;
         Iterator iter = this.content().iterator();
         Object[] rightArray = var5.content().toArray(ClassTag$.MODULE$.AnyRef());

         while(iter.hasNext()) {
            Tuple2 nextPayload = (Tuple2)iter.next();
            int index = rightIndexOf$1(nextPayload._1(), rightArray);
            if (index == -1) {
               builder.addOne(nextPayload);
            } else {
               Tuple2 rightPayload = (Tuple2)rightArray[index];
               rightArray[index] = null;
               builder.addOne((Tuple2)mergef.apply(nextPayload, rightPayload));
            }
         }

         for(int i = 0; i < rightArray.length; ++i) {
            Object elem = rightArray[i];
            if (elem != null) {
               builder.addOne((Tuple2)elem);
            }
         }

      } else if (that instanceof BitmapIndexedMapNode) {
         throw new RuntimeException("Cannot merge HashCollisionMapNode with BitmapIndexedMapNode");
      } else {
         throw new MatchError(that);
      }
   }

   public void buildTo(final HashMapBuilder builder) {
      Iterator iter = this.content().iterator();

      while(iter.hasNext()) {
         Tuple2 var3 = (Tuple2)iter.next();
         if (var3 == null) {
            throw new MatchError((Object)null);
         }

         Object k = var3._1();
         Object v = var3._2();
         builder.addOne(k, v, this.originalHash(), this.hash());
      }

   }

   public MapNode filterImpl(final Function1 pred, final boolean flipped) {
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
                  BigVector var84 = (BigVector)filterImpl_this;
                  Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$2;
                  BigVector filterImpl_foreachRest_this = var84;
                  int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                  for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                     VectorStatics$ var85 = VectorStatics$.MODULE$;
                     VectorInline$ var87 = VectorInline$.MODULE$;
                     int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                     int var88 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                     Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                     int filterImpl_foreachRest_foreachRec_level = var88;
                     VectorStatics$ filterImpl_foreachRest_foreachRec_this = var85;
                     int filterImpl_foreachRest_foreachRec_i = 0;
                     int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                     if (filterImpl_foreachRest_foreachRec_level == 0) {
                        for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object var80 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           if (BoxesRunTime.unboxToBoolean(pred.apply(var80)) != flipped) {
                              filterImpl_b.addOne(var80);
                           }
                        }
                     } else {
                        for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object[] foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           int foreachRec_i = 0;
                           int foreachRec_len = foreachRec_a.length;
                           if (filterImpl_foreachRest_foreachRec_l == 0) {
                              for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 Object var51 = foreachRec_a[foreachRec_i];
                                 if (BoxesRunTime.unboxToBoolean(pred.apply(var51)) != flipped) {
                                    filterImpl_b.addOne(var51);
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

                     Object var71 = null;
                     filterImpl_foreachRest_foreachRec_a = null;
                  }

                  Object var67 = null;
                  filterImpl_foreachRest_f = null;
                  Object var72 = null;
                  Object var75 = null;
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

                  for(int var54 = filterImpl_i + 1; filterImpl_i != filterImpl_newLen; ++var54) {
                     if ((1 << var54 & filterImpl_bitmap) != 0) {
                        filterImpl_b.addOne(filterImpl_this.prefix1()[var54]);
                        ++filterImpl_i;
                     }
                  }

                  BigVector var81 = (BigVector)filterImpl_this;
                  Function1 filterImpl_foreachRest_f = Vector::$anonfun$filterImpl$1;
                  BigVector filterImpl_foreachRest_this = var81;
                  int filterImpl_foreachRest_c = filterImpl_foreachRest_this.vectorSliceCount();

                  for(int filterImpl_foreachRest_i = 1; filterImpl_foreachRest_i < filterImpl_foreachRest_c; ++filterImpl_foreachRest_i) {
                     VectorStatics$ var82 = VectorStatics$.MODULE$;
                     VectorInline$ var10001 = VectorInline$.MODULE$;
                     int filterImpl_foreachRest_vectorSliceDim_c = filterImpl_foreachRest_c / 2;
                     int var86 = filterImpl_foreachRest_vectorSliceDim_c + 1 - Math.abs(filterImpl_foreachRest_i - filterImpl_foreachRest_vectorSliceDim_c) - 1;
                     Object[] filterImpl_foreachRest_foreachRec_a = filterImpl_foreachRest_this.vectorSlice(filterImpl_foreachRest_i);
                     int filterImpl_foreachRest_foreachRec_level = var86;
                     VectorStatics$ filterImpl_foreachRest_foreachRec_this = var82;
                     int filterImpl_foreachRest_foreachRec_i = 0;
                     int filterImpl_foreachRest_foreachRec_len = filterImpl_foreachRest_foreachRec_a.length;
                     if (filterImpl_foreachRest_foreachRec_level == 0) {
                        for(; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object var79 = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           if (BoxesRunTime.unboxToBoolean(pred.apply(var79)) != flipped) {
                              filterImpl_b.addOne(var79);
                           }
                        }
                     } else {
                        for(int filterImpl_foreachRest_foreachRec_l = filterImpl_foreachRest_foreachRec_level - 1; filterImpl_foreachRest_foreachRec_i < filterImpl_foreachRest_foreachRec_len; ++filterImpl_foreachRest_foreachRec_i) {
                           Object[] foreachRec_a = filterImpl_foreachRest_foreachRec_a[filterImpl_foreachRest_foreachRec_i];
                           int foreachRec_i = 0;
                           int foreachRec_len = foreachRec_a.length;
                           if (filterImpl_foreachRest_foreachRec_l == 0) {
                              for(; foreachRec_i < foreachRec_len; ++foreachRec_i) {
                                 Object var50 = foreachRec_a[foreachRec_i];
                                 if (BoxesRunTime.unboxToBoolean(pred.apply(var50)) != flipped) {
                                    filterImpl_b.addOne(var50);
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

                     Object var61 = null;
                     filterImpl_foreachRest_foreachRec_a = null;
                  }

                  Object var57 = null;
                  filterImpl_foreachRest_f = null;
                  Object var62 = null;
                  Object var65 = null;
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
         Vector newContent = var10000;
         int newContentLength = newContent.length();
         if (newContentLength == 0) {
            return MapNode$.MODULE$.empty();
         } else if (newContentLength == 1) {
            Tuple2 var5 = (Tuple2)newContent.head();
            if (var5 != null) {
               Object k = var5._1();
               Object v = var5._2();
               Node$ var10002 = Node$.MODULE$;
               var10002 = Node$.MODULE$;
               int bitposFrom_mask = this.hash() >>> 0 & 31;
               return new BitmapIndexedMapNode(1 << bitposFrom_mask, 0, new Object[]{k, v}, new int[]{this.originalHash()}, 1, this.hash());
            } else {
               throw new MatchError((Object)null);
            }
         } else {
            return newContentLength == this.content().length() ? this : new HashCollisionMapNode(this.originalHash(), this.hash(), newContent);
         }
      }
   }

   public HashCollisionMapNode copy() {
      return new HashCollisionMapNode(this.originalHash(), this.hash(), this.content());
   }

   public int hashCode() {
      throw new UnsupportedOperationException("Trie nodes do not support hashing.");
   }

   public int cachedJavaKeySetHashCode() {
      return this.size() * this.hash();
   }

   // $FF: synthetic method
   public static final Nothing$ $anonfun$apply$1() {
      Iterator$ var10000 = Iterator$.MODULE$;
      return (Nothing$)Iterator$.scala$collection$Iterator$$_empty.next();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$removed$1(final Object key$1, final Tuple2 keyValuePair) {
      return BoxesRunTime.equals(keyValuePair._1(), key$1);
   }

   private static final int rightIndexOf$1(final Object key, final Object[] rightArray$1) {
      for(int i = 0; i < rightArray$1.length; ++i) {
         Object elem = rightArray$1[i];
         if (elem != null && BoxesRunTime.equals(((Tuple2)elem)._1(), key)) {
            return i;
         }
      }

      return -1;
   }

   public HashCollisionMapNode(final int originalHash, final int hash, final Vector content) {
      this.originalHash = originalHash;
      this.hash = hash;
      this.content = content;
      super();
      Predef$.MODULE$.require(this.content().length() >= 2);
      Statics.releaseFence();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
