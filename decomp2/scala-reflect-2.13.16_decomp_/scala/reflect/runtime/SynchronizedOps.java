package scala.reflect.runtime;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.Depth;
import scala.reflect.internal.Names;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.Types;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\t}eAC\u00193!\u0003\r\t\u0001\u000e\u001d\u0003\u001c\")a\t\u0001C\u0001\u0011\")Q\n\u0001C)\u001d\")!\u000b\u0001C)'\")Q\u000e\u0001C)]\u001a9a\u000f\u0001I\u0001\u0004\u00039\b\"\u0002$\u0006\t\u0003A\u0005\"\u0002=\u0006\t\u0003J\bBB@\u0006\t\u0003\n\t\u0001C\u0004\u0002\u0006\u0015!\t%a\u0002\t\u000f\u0005UQ\u0001\"\u0011\u0002\u0018!9\u0011\u0011D\u0003\u0005B\u0005m\u0001bBA\u0013\u000b\u0011\u0005\u0013q\u0005\u0005\b\u0003g)A\u0011IA\u001b\u0011)\ti$\u0002EC\u0002\u0013\u0005\u0013q\b\u0005\b\u0003\u000f*A\u0011IA%\u00119\t\t'\u0002I\u0001\u0004\u0003\u0005I\u0011BA2\u0003OBa\"!\u001b\u0006!\u0003\r\t\u0011!C\u0005\u0003W\ny\u0007\u0003\b\u0002r\u0015\u0001\n1!A\u0001\n\u0013\t\u0019(a\u001e\t\u001d\u0005eT\u0001%A\u0002\u0002\u0003%I!a\u0006\u0002|!q\u0011QP\u0003\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0002\u0000\u0005\u0015\u0005BDAD\u000bA\u0005\u0019\u0011!A\u0005\n\u0005%\u0015Q\u0012\u0005\u000f\u0003\u001f+\u0001\u0013aA\u0001\u0002\u0013%\u0011\u0011SAK\u00119\t9*\u0002I\u0001\u0004\u0003\u0005I\u0011BA%\u00033Cq!!)\u0001\t\u0003\n\u0019KB\u0005\u00026\u0002\u0001\n1!\u0001\u00028\")a)\u0007C\u0001\u0011\"Q\u0011\u0011X\r\t\u0006\u0004%I!a/\t\u000f\u00055\u0017\u0004\"\u0001\u0002P\"1\u00111_\r\u0005B9Cq!!>\u001a\t\u0003\n9\u0010C\u0004\u0002zf!\t%a?\t\u000f\t-\u0011\u0004\"\u0011\u0003\u000e!9!qD\r\u0005B\t\u0005\u0002b\u0002B\u00103\u0011\u0005#Q\u0006\u0005\b\u0005cIB\u0011\tB\u001a\u0011\u001d\u0011y$\u0007C!\u0005\u0003BqA!\u0012\u001a\t\u0003\u00129\u0005C\u0004\u0002\u0016e!\tE!\u0014\t\u001b\tE\u0013\u0004%A\u0002\u0002\u0003%IA\u0014B*\u00119\u0011)&\u0007I\u0001\u0004\u0003\u0005I\u0011BA|\u0005/BaB!\u0017\u001a!\u0003\r\t\u0011!C\u0005\u00057\u00129\u0007\u0003\b\u0003je\u0001\n1!A\u0001\n\u0013\u0011YG!\u001d\t\u001d\tM\u0014\u0004%A\u0002\u0002\u0003%IA!\u001e\u0003z!q!1O\r\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u0003|\t}\u0004B\u0004BA3A\u0005\u0019\u0011!A\u0005\n\t\r%q\u0011\u0005\u000f\u0005\u0013K\u0002\u0013aA\u0001\u0002\u0013%!1\u0012BH\u00119\u0011\t*\u0007I\u0001\u0004\u0003\u0005I\u0011\u0002BJ\u0005/Ca\"!\u001f\u001a!\u0003\r\t\u0011!C\u0005\u0005\u001b\u0012IJA\bTs:\u001c\u0007N]8oSj,Gm\u00149t\u0015\t\u0019D'A\u0004sk:$\u0018.\\3\u000b\u0005U2\u0014a\u0002:fM2,7\r\u001e\u0006\u0002o\u0005)1oY1mCN!\u0001!O D!\tQT(D\u0001<\u0015\taD'\u0001\u0005j]R,'O\\1m\u0013\tq4HA\u0006Ts6\u0014w\u000e\u001c+bE2,\u0007C\u0001!B\u001b\u0005\u0011\u0014B\u0001\"3\u0005M\u0019\u0016P\\2ie>t\u0017N_3e'fl'm\u001c7t!\t\u0001E)\u0003\u0002Fe\t\t2+\u001f8dQJ|g.\u001b>fIRK\b/Z:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012!\u0013\t\u0003\u0015.k\u0011AN\u0005\u0003\u0019Z\u0012A!\u00168ji\u0006\u00012/\u001f8dQJ|g.\u001b>f\u001d\u0006lWm]\u000b\u0002\u001fB\u0011!\nU\u0005\u0003#Z\u0012qAQ8pY\u0016\fg.\u0001\boK^\u0014\u0015m]3UsB,7+Z9\u0015\u0007QS\u0006\u000e\u0005\u0002V-6\t\u0001!\u0003\u0002X1\nY!)Y:f)f\u0004XmU3r\u0013\tI6H\u0001\u0007CCN,G+\u001f9f'\u0016\f8\u000fC\u0003\\\u0007\u0001\u0007A,A\u0004qCJ,g\u000e^:\u0011\u0007u\u00037M\u0004\u0002K=&\u0011qLN\u0001\ba\u0006\u001c7.Y4f\u0013\t\t'M\u0001\u0003MSN$(BA07!\t)F-\u0003\u0002fM\n!A+\u001f9f\u0013\t97HA\u0003UsB,7\u000fC\u0003j\u0007\u0001\u0007!.A\u0003fY\u0016l7\u000fE\u0002KW\u000eL!\u0001\u001c\u001c\u0003\u000b\u0005\u0013(/Y=\u0002)9,w/T1qa\u0016$')Y:f)f\u0004XmU3r)\u0015y\u00171TAP%\r\u0001(/\u001e\u0004\u0005c\u0012\u0001qN\u0001\u0007=e\u00164\u0017N\\3nK:$h\b\u0005\u0002Vg&\u0011A\u000f\u0017\u0002\u0012\u001b\u0006\u0004\b/\u001a3CCN,G+\u001f9f'\u0016\f\bCA+\u0006\u0005]\u0019\u0016P\\2ie>t\u0017N_3e\u0005\u0006\u001cX\rV=qKN+\u0017o\u0005\u0002\u0006)\u0006)\u0011\r\u001d9msR\u00111M\u001f\u0005\u0006w\u001e\u0001\r\u0001`\u0001\u0002SB\u0011!*`\u0005\u0003}Z\u00121!\u00138u\u0003\u001d\u0011\u0018m^#mK6$2aYA\u0002\u0011\u0015Y\b\u00021\u0001}\u0003)!\u0018\u0010]3Ts6\u0014w\u000e\u001c\u000b\u0005\u0003\u0013\t\u0019\u0002E\u0002V\u0003\u0017IA!!\u0004\u0002\u0010\t11+_7c_2L1!!\u0005<\u0005\u001d\u0019\u00160\u001c2pYNDQa_\u0005A\u0002q\fa\u0001^8MSN$X#\u0001/\u0002\t\r|\u0007/\u001f\u000b\u0006)\u0006u\u0011\u0011\u0005\u0005\u0007\u0003?Y\u0001\u0019A2\u0002\t!,\u0017\r\u001a\u0005\u0007\u0003GY\u0001\u0019\u0001?\u0002\r=4gm]3u\u0003\ri\u0017\r\u001d\u000b\u0004)\u0006%\u0002bBA\u0016\u0019\u0001\u0007\u0011QF\u0001\u0002MB)!*a\fdG&\u0019\u0011\u0011\u0007\u001c\u0003\u0013\u0019+hn\u0019;j_:\f\u0014AB3ySN$8\u000fF\u0002P\u0003oAq!!\u000f\u000e\u0001\u0004\tY$A\u0001q!\u0015Q\u0015qF2P\u0003!i\u0017\r\u001f#faRDWCAA!!\rQ\u00141I\u0005\u0004\u0003\u000bZ$!\u0002#faRD\u0017\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005-\u0003\u0003BA'\u00037rA!a\u0014\u0002XA\u0019\u0011\u0011\u000b\u001c\u000e\u0005\u0005M#bAA+\u000f\u00061AH]8pizJ1!!\u00177\u0003\u0019\u0001&/\u001a3fM&!\u0011QLA0\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011\f\u001c\u0002\u0017M,\b/\u001a:%CB\u0004H.\u001f\u000b\u0004G\u0006\u0015\u0004\"B>\u0011\u0001\u0004a\u0018B\u0001=W\u00035\u0019X\u000f]3sII\fw/\u00127f[R\u00191-!\u001c\t\u000bm\f\u0002\u0019\u0001?\n\u0005}4\u0016\u0001E:va\u0016\u0014H\u0005^=qKNKXNY8m)\u0011\tI!!\u001e\t\u000bm\u0014\u0002\u0019\u0001?\n\u0007\u0005\u0015a+\u0001\u0007tkB,'\u000f\n;p\u0019&\u001cH/C\u0002\u0002\u0016Y\u000b!b];qKJ$3m\u001c9z)\u0015!\u0016\u0011QAB\u0011\u0019\ty\u0002\u0006a\u0001G\"1\u00111\u0005\u000bA\u0002qL1!!\u0007W\u0003%\u0019X\u000f]3sI5\f\u0007\u000fF\u0002U\u0003\u0017Cq!a\u000b\u0016\u0001\u0004\ti#C\u0002\u0002&Y\u000bAb];qKJ$S\r_5tiN$2aTAJ\u0011\u001d\tID\u0006a\u0001\u0003wI1!a\rW\u00039\u0019X\u000f]3sIQ|7\u000b\u001e:j]\u001eL1!a\u0012W\u0011\u0019\ti\n\u0002a\u0001)\u0006!qN]5h\u0011\u001d\tY\u0003\u0002a\u0001\u0003[\t\u0001B\\3x'\u000e|\u0007/Z\u000b\u0003\u0003K\u0013b!a*\u0002*\u0006Mf!B9\u0019\u0001\u0005\u0015\u0006cA+\u0002,&!\u0011QVAX\u0005\u0015\u00196m\u001c9f\u0013\r\t\tl\u000f\u0002\u0007'\u000e|\u0007/Z:\u0011\u0005UK\"!E*z]\u000eD'o\u001c8ju\u0016$7kY8qKN\u0019\u0011$!+\u0002\u0011MLhn\u0019'pG.,\"!!0\u0011\t\u0005}\u0016\u0011Z\u0007\u0003\u0003\u0003TA!a1\u0002F\u0006!A.\u00198h\u0015\t\t9-\u0001\u0003kCZ\f\u0017\u0002BAf\u0003\u0003\u0014aa\u00142kK\u000e$\u0018\u0001F:z]\u000edunY6Ts:\u001c\u0007N]8oSj,G-\u0006\u0003\u0002R\u0006]G\u0003BAj\u0003S\u0004B!!6\u0002X2\u0001AaBAm9\t\u0007\u00111\u001c\u0002\u0002)F!\u0011Q\\Ar!\rQ\u0015q\\\u0005\u0004\u0003C4$a\u0002(pi\"Lgn\u001a\t\u0004\u0015\u0006\u0015\u0018bAAtm\t\u0019\u0011I\\=\t\u0011\u0005-H\u0004\"a\u0001\u0003[\fAAY8esB)!*a<\u0002T&\u0019\u0011\u0011\u001f\u001c\u0003\u0011q\u0012\u0017P\\1nKz\nq![:F[B$\u00180\u0001\u0003tSj,W#\u0001?\u0002\u000b\u0015tG/\u001a:\u0016\t\u0005u(q\u0001\u000b\u0005\u0003\u007f\u0014\tA\u0004\u0003\u0002V\n\u0005\u0001b\u0002B\u0002?\u0001\u0007!QA\u0001\u0004gfl\u0007\u0003BAk\u0005\u000f!q!!7 \u0005\u0004\u0011I!\u0005\u0003\u0002^\u0006%\u0011A\u0002:fQ\u0006\u001c\b\u000eF\u0003J\u0005\u001f\u0011\t\u0002C\u0004\u0003\u0004\u0001\u0002\r!!\u0003\t\u000f\tM\u0001\u00051\u0001\u0003\u0016\u00059a.Z<oC6,\u0007cA+\u0003\u0018%!!\u0011\u0004B\u000e\u0005\u0011q\u0015-\\3\n\u0007\tu1HA\u0003OC6,7/\u0001\u0004v]2Lgn\u001b\u000b\u0004\u0013\n\r\u0002b\u0002B\u0013C\u0001\u0007!qE\u0001\u0002KB\u0019QK!\u000b\n\t\t-\u0012q\u0016\u0002\u000b'\u000e|\u0007/Z#oiJLHcA%\u00030!9!1\u0001\u0012A\u0002\u0005%\u0011!\u00037p_.,\b/\u00117m)\u0011\u0011)Da\u000f\u0011\u000bu\u00139$!\u0003\n\u0007\te\"M\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001d\u0011id\ta\u0001\u0005+\tAA\\1nK\u0006YAn\\8lkB,e\u000e\u001e:z)\u0011\u00119Ca\u0011\t\u000f\tuB\u00051\u0001\u0003\u0016\u0005yAn\\8lkBtU\r\u001f;F]R\u0014\u0018\u0010\u0006\u0003\u0003(\t%\u0003b\u0002B&K\u0001\u0007!qE\u0001\u0006K:$(/_\u000b\u0003\u0005\u001f\u0002B!\u00181\u0002\n\u0005i1/\u001e9fe\u0012J7/R7qifLA!a=\u0002,\u0006Q1/\u001e9fe\u0012\u001a\u0018N_3\n\t\u0005U\u00181V\u0001\fgV\u0004XM\u001d\u0013f]R,'/\u0006\u0003\u0003^\t\u0015D\u0003\u0002B0\u0005CrA!!6\u0003b!9!1A\u0015A\u0002\t\r\u0004\u0003BAk\u0005K\"q!!7*\u0005\u0004\u0011I!\u0003\u0003\u0002z\u0006-\u0016\u0001D:va\u0016\u0014HE]3iCNDG#B%\u0003n\t=\u0004b\u0002B\u0002U\u0001\u0007\u0011\u0011\u0002\u0005\b\u0005'Q\u0003\u0019\u0001B\u000b\u0013\u0011\u0011Y!a+\u0002\u0019M,\b/\u001a:%k:d\u0017N\\6\u0015\u0007%\u00139\bC\u0004\u0003&-\u0002\rAa\n\n\t\t}\u00111\u0016\u000b\u0004\u0013\nu\u0004b\u0002B\u0002Y\u0001\u0007\u0011\u0011B\u0005\u0005\u0005?\tY+A\btkB,'\u000f\n7p_.,\b/\u00117m)\u0011\u0011)D!\"\t\u000f\tuR\u00061\u0001\u0003\u0016%!!\u0011GAV\u0003E\u0019X\u000f]3sI1|wn[;q\u000b:$(/\u001f\u000b\u0005\u0005O\u0011i\tC\u0004\u0003>9\u0002\rA!\u0006\n\t\t}\u00121V\u0001\u0016gV\u0004XM\u001d\u0013m_>\\W\u000f\u001d(fqR,e\u000e\u001e:z)\u0011\u00119C!&\t\u000f\t-s\u00061\u0001\u0003(%!!QIAV\u0013\u0011\t)\"a+\u0011\u0007\u0001\u0013i*\u0003\u0002?e\u0001"
)
public interface SynchronizedOps extends SynchronizedSymbols, SynchronizedTypes {
   // $FF: synthetic method
   static boolean synchronizeNames$(final SynchronizedOps $this) {
      return $this.synchronizeNames();
   }

   default boolean synchronizeNames() {
      return true;
   }

   // $FF: synthetic method
   static BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq$(final SynchronizedOps $this, final List parents, final Types.Type[] elems) {
      return $this.newBaseTypeSeq(parents, elems);
   }

   default BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(final List parents, final Types.Type[] elems) {
      int exists$extension_indexWhere$extension_i = 0;

      int var10000;
      while(true) {
         if (exists$extension_indexWhere$extension_i >= elems.length) {
            var10000 = -1;
            break;
         }

         if (elems[exists$extension_indexWhere$extension_i] instanceof Types.RefinedType) {
            var10000 = exists$extension_indexWhere$extension_i;
            break;
         }

         ++exists$extension_indexWhere$extension_i;
      }

      return (BaseTypeSeqs.BaseTypeSeq)(var10000 >= 0 ? (SymbolTable)this.new SynchronizedBaseTypeSeq(parents, elems) {
         private int maxDepth;
         private volatile boolean bitmap$0;
         // $FF: synthetic field
         private final SymbolTable $outer;

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(final int i) {
            return super.apply(i);
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(final int i) {
            return super.rawElem(i);
         }

         // $FF: synthetic method
         public Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(final int i) {
            return super.typeSymbol(i);
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList() {
            return super.toList();
         }

         // $FF: synthetic method
         public BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(final Types.Type head, final int offset) {
            return super.copy(head, offset);
         }

         // $FF: synthetic method
         public BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(final Function1 f) {
            return super.map(f);
         }

         // $FF: synthetic method
         public boolean scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(final Function1 p) {
            return super.exists(p);
         }

         // $FF: synthetic method
         public String scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString() {
            return super.toString();
         }

         public Types.Type apply(final int i) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.apply(i);
         }

         public Types.Type rawElem(final int i) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.rawElem(i);
         }

         public Symbols.Symbol typeSymbol(final int i) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.typeSymbol(i);
         }

         public List toList() {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.toList();
         }

         public BaseTypeSeqs.BaseTypeSeq copy(final Types.Type head, final int offset) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.copy(head, offset);
         }

         public BaseTypeSeqs.BaseTypeSeq map(final Function1 f) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.map(f);
         }

         public boolean exists(final Function1 p) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.exists(p);
         }

         public String toString() {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.toString();
         }

         private int maxDepth$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.maxDepth = SynchronizedOps.SynchronizedBaseTypeSeq.super.maxDepth();
                  this.bitmap$0 = true;
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this.maxDepth;
         }

         public int maxDepth() {
            return !this.bitmap$0 ? this.maxDepth$lzycompute() : this.maxDepth;
         }

         // $FF: synthetic method
         public SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer() {
            return this.$outer;
         }

         public {
            if (SynchronizedOps.this == null) {
               throw null;
            } else {
               this.$outer = SynchronizedOps.this;
            }
         }
      } : (scala.reflect.internal.SymbolTable)this.new BaseTypeSeq(parents, elems));
   }

   // $FF: synthetic method
   static BaseTypeSeqs.MappedBaseTypeSeq newMappedBaseTypeSeq$(final SynchronizedOps $this, final BaseTypeSeqs.BaseTypeSeq orig, final Function1 f) {
      return $this.newMappedBaseTypeSeq(orig, f);
   }

   default BaseTypeSeqs.MappedBaseTypeSeq newMappedBaseTypeSeq(final BaseTypeSeqs.BaseTypeSeq orig, final Function1 f) {
      return (SymbolTable)this.new SynchronizedBaseTypeSeq(orig, f) {
         private int maxDepth;
         private volatile boolean bitmap$0;
         // $FF: synthetic field
         private final SymbolTable $outer;

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(final int i) {
            return super.apply(i);
         }

         // $FF: synthetic method
         public Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(final int i) {
            return super.rawElem(i);
         }

         // $FF: synthetic method
         public Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(final int i) {
            return super.typeSymbol(i);
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList() {
            return super.toList();
         }

         // $FF: synthetic method
         public BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(final Types.Type head, final int offset) {
            return super.copy(head, offset);
         }

         // $FF: synthetic method
         public BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(final Function1 f) {
            return super.map(f);
         }

         // $FF: synthetic method
         public boolean scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(final Function1 p) {
            return super.exists(p);
         }

         // $FF: synthetic method
         public String scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString() {
            return super.toString();
         }

         public Types.Type apply(final int i) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.apply(i);
         }

         public Types.Type rawElem(final int i) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.rawElem(i);
         }

         public Symbols.Symbol typeSymbol(final int i) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.typeSymbol(i);
         }

         public List toList() {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.toList();
         }

         public BaseTypeSeqs.BaseTypeSeq copy(final Types.Type head, final int offset) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.copy(head, offset);
         }

         public BaseTypeSeqs.BaseTypeSeq map(final Function1 f) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.map(f);
         }

         public boolean exists(final Function1 p) {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.exists(p);
         }

         public String toString() {
            return SynchronizedOps.SynchronizedBaseTypeSeq.super.toString();
         }

         private int maxDepth$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.maxDepth = SynchronizedOps.SynchronizedBaseTypeSeq.super.maxDepth();
                  this.bitmap$0 = true;
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this.maxDepth;
         }

         public int maxDepth() {
            return !this.bitmap$0 ? this.maxDepth$lzycompute() : this.maxDepth;
         }

         // $FF: synthetic method
         public SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer() {
            return this.$outer;
         }

         public {
            if (SynchronizedOps.this == null) {
               throw null;
            } else {
               this.$outer = SynchronizedOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static SynchronizedScope newScope$(final SynchronizedOps $this) {
      return $this.newScope();
   }

   default SynchronizedScope newScope() {
      return (SymbolTable)this.new SynchronizedScope() {
         private Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
         private volatile boolean bitmap$0;
         // $FF: synthetic field
         private final SymbolTable $outer;

         // $FF: synthetic method
         public boolean scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty() {
            return super.isEmpty();
         }

         // $FF: synthetic method
         public int scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size() {
            return super.size();
         }

         // $FF: synthetic method
         public Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(final Symbols.Symbol sym) {
            return super.enter(sym);
         }

         // $FF: synthetic method
         public void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(final Symbols.Symbol sym, final Names.Name newname) {
            super.rehash(sym, newname);
         }

         // $FF: synthetic method
         public void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(final Scopes.ScopeEntry e) {
            super.unlink(e);
         }

         // $FF: synthetic method
         public void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(final Symbols.Symbol sym) {
            super.unlink(sym);
         }

         // $FF: synthetic method
         public Iterator scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(final Names.Name name) {
            return super.lookupAll(name);
         }

         // $FF: synthetic method
         public Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(final Names.Name name) {
            return super.lookupEntry(name);
         }

         // $FF: synthetic method
         public Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(final Scopes.ScopeEntry entry) {
            return super.lookupNextEntry(entry);
         }

         // $FF: synthetic method
         public List scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList() {
            return super.toList();
         }

         public Object syncLockSynchronized(final Function0 body) {
            return SynchronizedOps.SynchronizedScope.super.syncLockSynchronized(body);
         }

         public boolean isEmpty() {
            return SynchronizedOps.SynchronizedScope.super.isEmpty();
         }

         public int size() {
            return SynchronizedOps.SynchronizedScope.super.size();
         }

         public Symbols.Symbol enter(final Symbols.Symbol sym) {
            return SynchronizedOps.SynchronizedScope.super.enter(sym);
         }

         public void rehash(final Symbols.Symbol sym, final Names.Name newname) {
            SynchronizedOps.SynchronizedScope.super.rehash(sym, newname);
         }

         public void unlink(final Scopes.ScopeEntry e) {
            SynchronizedOps.SynchronizedScope.super.unlink(e);
         }

         public void unlink(final Symbols.Symbol sym) {
            SynchronizedOps.SynchronizedScope.super.unlink(sym);
         }

         public Iterator lookupAll(final Names.Name name) {
            return SynchronizedOps.SynchronizedScope.super.lookupAll(name);
         }

         public Scopes.ScopeEntry lookupEntry(final Names.Name name) {
            return SynchronizedOps.SynchronizedScope.super.lookupEntry(name);
         }

         public Scopes.ScopeEntry lookupNextEntry(final Scopes.ScopeEntry entry) {
            return SynchronizedOps.SynchronizedScope.super.lookupNextEntry(entry);
         }

         public List toList() {
            return SynchronizedOps.SynchronizedScope.super.toList();
         }

         private Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock = new Object();
                  this.bitmap$0 = true;
               }
            } catch (Throwable var2) {
               throw var2;
            }

            return this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
         }

         public Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock() {
            return !this.bitmap$0 ? this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$lzycompute() : this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock;
         }

         // $FF: synthetic method
         public SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer() {
            return this.$outer;
         }

         public {
            if (SynchronizedOps.this == null) {
               throw null;
            } else {
               this.$outer = SynchronizedOps.this;
            }
         }
      };
   }

   // $FF: synthetic method
   static boolean $anonfun$newBaseTypeSeq$1(final Types.Type x$1) {
      return x$1 instanceof Types.RefinedType;
   }

   static void $init$(final SynchronizedOps $this) {
   }

   // $FF: synthetic method
   static Object $anonfun$newBaseTypeSeq$1$adapted(final Types.Type x$1) {
      return BoxesRunTime.boxToBoolean($anonfun$newBaseTypeSeq$1(x$1));
   }

   public interface SynchronizedBaseTypeSeq {
      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(final int i);

      // $FF: synthetic method
      Types.Type scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(final int i);

      // $FF: synthetic method
      Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(final int i);

      // $FF: synthetic method
      List scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList();

      // $FF: synthetic method
      BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(final Types.Type head, final int offset);

      // $FF: synthetic method
      BaseTypeSeqs.BaseTypeSeq scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(final Function1 f);

      // $FF: synthetic method
      boolean scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(final Function1 p);

      // $FF: synthetic method
      String scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString();

      default Types.Type apply(final int i) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(i);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var6 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(i);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var6;
            }
         }
      }

      default Types.Type rawElem(final int i) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(i);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var6 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(i);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var6;
            }
         }
      }

      default Symbols.Symbol typeSymbol(final int i) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(i);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var6 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(i);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var6;
            }
         }
      }

      default List toList() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList();
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList();
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      default BaseTypeSeqs.BaseTypeSeq copy(final Types.Type head, final int offset) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(head, offset);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var7 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(head, offset);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var7;
            }
         }
      }

      default BaseTypeSeqs.BaseTypeSeq map(final Function1 f) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(f);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var6 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(f);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var6;
            }
         }
      }

      default boolean exists(final Function1 p) {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(p);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var6 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(p);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var6;
            }
         }
      }

      default int maxDepth() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               var10000 = (Gil)$anonfun$maxDepth$1$adapted(this);
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var10000 = (Gil)$anonfun$maxDepth$1$adapted(this);
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }
            }

            gilSynchronized_this = null;
            return ((Depth)var10000).depth();
         }
      }

      default String toString() {
         Gil var10000 = (Gil)this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();
         if (var10000 == null) {
            throw null;
         } else {
            Gil gilSynchronized_this = var10000;
            if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
               return this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString();
            } else {
               try {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
                  var5 = this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString();
               } finally {
                  gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
               }

               return var5;
            }
         }
      }

      // $FF: synthetic method
      SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$$outer();

      // $FF: synthetic method
      static Types.Type $anonfun$apply$1(final SynchronizedBaseTypeSeq $this, final int i$1) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$apply(i$1);
      }

      // $FF: synthetic method
      static Types.Type $anonfun$rawElem$1(final SynchronizedBaseTypeSeq $this, final int i$2) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$rawElem(i$2);
      }

      // $FF: synthetic method
      static Symbols.Symbol $anonfun$typeSymbol$1(final SynchronizedBaseTypeSeq $this, final int i$3) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$typeSymbol(i$3);
      }

      // $FF: synthetic method
      static List $anonfun$toList$1(final SynchronizedBaseTypeSeq $this) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toList();
      }

      // $FF: synthetic method
      static BaseTypeSeqs.BaseTypeSeq $anonfun$copy$1(final SynchronizedBaseTypeSeq $this, final Types.Type head$1, final int offset$1) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$copy(head$1, offset$1);
      }

      // $FF: synthetic method
      static BaseTypeSeqs.BaseTypeSeq $anonfun$map$1(final SynchronizedBaseTypeSeq $this, final Function1 f$2) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$map(f$2);
      }

      // $FF: synthetic method
      static boolean $anonfun$exists$1(final SynchronizedBaseTypeSeq $this, final Function1 p$1) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$exists(p$1);
      }

      // $FF: synthetic method
      static int $anonfun$maxDepth$1(final SynchronizedBaseTypeSeq $this) {
         return ((BaseTypeSeqs.BaseTypeSeq)$this).maxDepthOfElems();
      }

      // $FF: synthetic method
      static String $anonfun$toString$1(final SynchronizedBaseTypeSeq $this) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedBaseTypeSeq$$super$toString();
      }

      static void $init$(final SynchronizedBaseTypeSeq $this) {
      }

      // $FF: synthetic method
      static Object $anonfun$maxDepth$1$adapted(final SynchronizedBaseTypeSeq $this) {
         return new Depth(((BaseTypeSeqs.BaseTypeSeq)$this).maxDepthOfElems());
      }
   }

   public interface SynchronizedScope {
      // $FF: synthetic method
      boolean scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty();

      // $FF: synthetic method
      int scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size();

      // $FF: synthetic method
      Symbols.Symbol scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(final Symbols.Symbol sym);

      // $FF: synthetic method
      void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(final Symbols.Symbol sym, final Names.Name newname);

      // $FF: synthetic method
      void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(final Scopes.ScopeEntry e);

      // $FF: synthetic method
      void scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(final Symbols.Symbol sym);

      // $FF: synthetic method
      Iterator scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(final Names.Name name);

      // $FF: synthetic method
      Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(final Names.Name name);

      // $FF: synthetic method
      Scopes.ScopeEntry scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(final Scopes.ScopeEntry entry);

      // $FF: synthetic method
      List scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList();

      // $FF: synthetic method
      static Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock$(final SynchronizedScope $this) {
         return $this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock();
      }

      default Object scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock() {
         return new Object();
      }

      default Object syncLockSynchronized(final Function0 body) {
         if (((SymbolTable)this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer()).isCompilerUniverse()) {
            return body.apply();
         } else {
            synchronized(this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$syncLock()){}

            Object var3;
            try {
               var3 = body.apply();
            } catch (Throwable var5) {
               throw var5;
            }

            return var3;
         }
      }

      default boolean isEmpty() {
         return BoxesRunTime.unboxToBoolean(this.syncLockSynchronized((JFunction0.mcZ.sp)() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$isEmpty()));
      }

      default int size() {
         return BoxesRunTime.unboxToInt(this.syncLockSynchronized((JFunction0.mcI.sp)() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$size()));
      }

      default Symbols.Symbol enter(final Symbols.Symbol sym) {
         return (Symbols.Symbol)this.syncLockSynchronized(() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$enter(sym));
      }

      default void rehash(final Symbols.Symbol sym, final Names.Name newname) {
         this.syncLockSynchronized((JFunction0.mcV.sp)() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$rehash(sym, newname));
      }

      default void unlink(final Scopes.ScopeEntry e) {
         this.syncLockSynchronized((JFunction0.mcV.sp)() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(e));
      }

      default void unlink(final Symbols.Symbol sym) {
         this.syncLockSynchronized((JFunction0.mcV.sp)() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$unlink(sym));
      }

      default Iterator lookupAll(final Names.Name name) {
         return (Iterator)this.syncLockSynchronized(() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupAll(name));
      }

      default Scopes.ScopeEntry lookupEntry(final Names.Name name) {
         return (Scopes.ScopeEntry)this.syncLockSynchronized(() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupEntry(name));
      }

      default Scopes.ScopeEntry lookupNextEntry(final Scopes.ScopeEntry entry) {
         return (Scopes.ScopeEntry)this.syncLockSynchronized(() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$lookupNextEntry(entry));
      }

      default List toList() {
         return (List)this.syncLockSynchronized(() -> this.scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$super$toList());
      }

      // $FF: synthetic method
      SynchronizedOps scala$reflect$runtime$SynchronizedOps$SynchronizedScope$$$outer();

      static void $init$(final SynchronizedScope $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
