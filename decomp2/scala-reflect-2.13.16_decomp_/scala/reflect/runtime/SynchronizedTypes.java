package scala.reflect.runtime;

import java.lang.invoke.SerializedLambda;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import scala.Function0;
import scala.Option;
import scala.collection.MapFactory;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.WeakHashMap;
import scala.collection.mutable.WeakHashMap.;
import scala.reflect.ScalaSignature;
import scala.reflect.internal.Types;
import scala.reflect.internal.tpe.CommonOwners;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\tucA\u0003\u00192!\u0003\r\taM\u001c\u0003V!)!\t\u0001C\u0001\t\")\u0001\n\u0001C)\u0013\"A!\u000b\u0001EC\u0002\u0013%1\u000bC\u0004]\u0001\t\u0007K\u0011B/\t\u000b=\u0004A\u0011\t9\t\u0011q\u0004\u0001R1A\u0005\nuDq!a\u0003\u0001\t\u0003\ni\u0001C\u0004\u0002\u0010\u0001!\t%!\u0005\t\u0015\u0005]\u0001\u0001#b\u0001\n\u0013\tI\u0002C\u0004\u0002(\u0001!\t%!\u000b\t\u0015\u0005-\u0002\u0001#b\u0001\n\u0013\ti\u0003C\u0004\u0002J\u0001!\t%a\u0013\t\u0013\u00055\u0003\u0001#b\u0001\n\u0013i\bbBA(\u0001\u0011\u0005\u0013Q\u0002\u0005\b\u0003#\u0002A\u0011IA*\u0011)\t9\u0006\u0001EC\u0002\u0013%\u0011\u0011\f\u0005\b\u0003[\u0002A\u0011IA8\u0011%\t\t\b\u0001EC\u0002\u0013%Q\u0010C\u0004\u0002t\u0001!\t%!\u0004\t\u000f\u0005U\u0004\u0001\"\u0011\u0002x!Q\u00111\u0010\u0001\t\u0006\u0004%I!! \t\u000f\u0005\r\u0005\u0001\"\u0011\u0002\u0006\"Q\u0011q\u0011\u0001\t\u0006\u0004%I!!#\t\u000f\u0005}\u0005\u0001\"\u0011\u0002\"\"Q\u00111\u0015\u0001\t\u0006\u0004%I!!#\t\u000f\u0005\u0015\u0006\u0001\"\u0011\u0002\"\"Q\u0011q\u0015\u0001\t\u0006\u0004%I!!+\t\u000f\u0005M\u0006\u0001\"\u0011\u00026\"9\u0011q\u0017\u0001\u0005B\u0005e\u0006\"CAi\u0001!\u0015\r\u0011\"\u0003~\u0011\u001d\t\u0019\u000e\u0001C!\u0003\u001bAq!!6\u0001\t\u0003\n9\u000e\u0003\u0006\u0002\\\u0002A)\u0019!C\u0005\u0003{Bq!!8\u0001\t\u0003\n)\tC\u0004\u0002`\u0002!\t&!9\t\u000f\u0005-\b\u0001\"\u0015\u0002n\"9\u0011q\u001f\u0001\u0005R\u0005e\bbBA\u007f\u0001\u0011E\u0013q \u0005\b\u0005\u0013\u0001A\u0011\u000bB\u0006\u0011\u001d\u0011y\u0001\u0001C)\u0005#AaBa\u0006\u0001!\u0003\r\t\u0011!C\u0005\u00053\u0011\u0019\u0003\u0003\b\u0003&\u0001\u0001\n1!A\u0001\n\u0013\u00119Ca\u000b\t\u001d\t5\u0002\u0001%A\u0002\u0002\u0003%IAa\f\u00034!q!Q\u0007\u0001\u0011\u0002\u0007\u0005\t\u0011\"\u0003\u00038\tm\u0002B\u0004B\u001f\u0001A\u0005\u0019\u0011!A\u0005\n\t}\"1\t\u0005\u000f\u0005\u000b\u0002\u0001\u0013aA\u0001\u0002\u0013%!q\tB&\u00119\u0011i\u0005\u0001I\u0001\u0004\u0003\u0005I\u0011\u0002B(\u0005'\u0012\u0011cU=oG\"\u0014xN\\5{K\u0012$\u0016\u0010]3t\u0015\t\u00114'A\u0004sk:$\u0018.\\3\u000b\u0005Q*\u0014a\u0002:fM2,7\r\u001e\u0006\u0002m\u0005)1oY1mCN\u0019\u0001\u0001\u000f\u001f\u0011\u0005eRT\"A\u001b\n\u0005m*$AB!osJ+g\r\u0005\u0002>\u00016\taH\u0003\u0002@g\u0005A\u0011N\u001c;fe:\fG.\u0003\u0002B}\t)A+\u001f9fg\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001F!\tId)\u0003\u0002Hk\t!QK\\5u\u00039\u0019w.\\7p]>;h.\u001a:NCB,\u0012A\u0013\t\u0003\u00172k\u0011\u0001A\u0005\u0003\u001b:\u0013abQ8n[>twj\u001e8fe6\u000b\u0007/\u0003\u0002P!\na1i\\7n_:|uO\\3sg*\u0011\u0011KP\u0001\u0004iB,\u0017AC;oSF,X\rT8dWV\tA\u000b\u0005\u0002V56\taK\u0003\u0002X1\u0006!A.\u00198h\u0015\u0005I\u0016\u0001\u00026bm\u0006L!a\u0017,\u0003\r=\u0013'.Z2u\u0003\u001d)h.[9vKN,\u0012A\u0018\t\u0005?\u00124\u0017.D\u0001a\u0015\t\t'-A\u0004nkR\f'\r\\3\u000b\u0005\r,\u0014AC2pY2,7\r^5p]&\u0011Q\r\u0019\u0002\f/\u0016\f7\u000eS1tQ6\u000b\u0007\u000f\u0005\u0002LO&\u0011\u0001\u000e\u0011\u0002\u0005)f\u0004X\rE\u0002k[\u001al\u0011a\u001b\u0006\u0003YZ\u000b1A]3g\u0013\tq7NA\u0007XK\u0006\\'+\u001a4fe\u0016t7-Z\u0001\u0007k:L\u0017/^3\u0016\u0005E$HC\u0001:{!\t\u0019H\u000f\u0004\u0001\u0005\u000bU,!\u0019\u0001<\u0003\u0003Q\u000b\"a\u001e4\u0011\u0005eB\u0018BA=6\u0005\u001dqu\u000e\u001e5j]\u001eDQa_\u0003A\u0002I\f!\u0001\u001e9\u0002'}\u001b8n\u001c7f[&T\u0018\r^5p]2+g/\u001a7\u0016\u0003y\u0004BaS@\u0002\u0006%!\u0011\u0011AA\u0002\u0005I!\u0006N]3bI2{7-\u00197Ti>\u0014\u0018mZ3\n\u0007\u0005\u0005\u0011\u0007E\u0002:\u0003\u000fI1!!\u00036\u0005\rIe\u000e^\u0001\u0013g.|G.Z7ju\u0006$\u0018n\u001c8MKZ,G.\u0006\u0002\u0002\u0006\u000512o[8mK6L'0\u0019;j_:dUM^3m?\u0012*\u0017\u000fF\u0002F\u0003'Aq!!\u0006\t\u0001\u0004\t)!A\u0003wC2,X-\u0001\u0005`k:$w\u000eT8h+\t\tY\u0002\u0005\u0003L\u007f\u0006u\u0001cA&\u0002 %!\u0011\u0011EA\u0012\u0005\u001d)f\u000eZ8M_\u001eL1!!\nQ\u0005=!\u0016\u0010]3D_:\u001cHO]1j]R\u001c\u0018aB;oI>dunZ\u000b\u0003\u0003;\tAcX5oi\u0016\u00148/Z2uS>tw+\u001b;oKN\u001cXCAA\u0018!\u0011Yu0!\r\u0011\r}#\u00171GA!!\u0015\t)$a\u000fg\u001d\rI\u0014qG\u0005\u0004\u0003s)\u0014a\u00029bG.\fw-Z\u0005\u0005\u0003{\tyD\u0001\u0003MSN$(bAA\u001dkA)\u00111IA$M6\u0011\u0011Q\t\u0006\u0003YVJ1A\\A#\u0003MIg\u000e^3sg\u0016\u001cG/[8o/&$h.Z:t+\t\t\t$\u0001\f`gV\u00147/Y7fif\u0004XMU3dkJ\u001c\u0018n\u001c8t\u0003U\u0019XOY:b[\u0016$\u0018\u0010]3SK\u000e,(o]5p]N\f\u0011d];cg\u0006lW\r^=qKJ+7-\u001e:tS>t7o\u0018\u0013fcR\u0019Q)!\u0016\t\u000f\u0005Uq\u00021\u0001\u0002\u0006\u0005\u0001r\f]3oI&twmU;c)f\u0004Xm]\u000b\u0003\u00037\u0002BaS@\u0002^A)q,a\u0018\u0002d%\u0019\u0011\u0011\r1\u0003\u000f!\u000b7\u000f[*fiB\u00191*!\u001a\n\t\u0005\u001d\u0014\u0011\u000e\u0002\f'V\u0014G+\u001f9f!\u0006L'/C\u0002\u0002lA\u0013Q\u0002V=qK\u000e{W\u000e]1sKJ\u001c\u0018a\u00049f]\u0012LgnZ*vERK\b/Z:\u0016\u0005\u0005u\u0013aE0cCN,G/\u001f9f%\u0016\u001cWO]:j_:\u001c\u0018A\u00052bg\u0016$\u0018\u0010]3SK\u000e,(o]5p]N\faCY1tKRL\b/\u001a*fGV\u00148/[8og~#S-\u001d\u000b\u0004\u000b\u0006e\u0004bBA\u000b)\u0001\u0007\u0011QA\u0001\u0012?B,g\u000eZ5oO\n\u000b7/\u001a+za\u0016\u001cXCAA@!\u0011Yu0!!\u0011\t}\u000byFZ\u0001\u0011a\u0016tG-\u001b8h\u0005\u0006\u001cX\rV=qKN,\"!!!\u0002\u0017}cWO\u0019*fgVdGo]\u000b\u0003\u0003\u0017\u0003BaS@\u0002\u000eB1q,a$\u0002\u0014\u001aL1!!%a\u0005\u001dA\u0015m\u001d5NCB\u0004r!OAK\u00033\u000b\u0019$C\u0002\u0002\u0018V\u0012a\u0001V;qY\u0016\u0014\u0004cA\u001f\u0002\u001c&\u0019\u0011Q\u0014 \u0003\u000b\u0011+\u0007\u000f\u001e5\u0002\u00151,(MU3tk2$8/\u0006\u0002\u0002\u000e\u0006Yql\u001a7c%\u0016\u001cX\u000f\u001c;t\u0003)9GN\u0019*fgVdGo]\u0001\b?&tG-\u001a8u+\t\tY\u000b\u0005\u0003L\u007f\u00065\u0006cA+\u00020&\u0019\u0011\u0011\u0017,\u0003\rM#(/\u001b8h\u0003\u0019Ig\u000eZ3oiV\u0011\u0011QV\u0001\u000bS:$WM\u001c;`I\u0015\fHcA#\u0002<\"9\u0011QC\u000fA\u0002\u0005u\u0006\u0003BA`\u0003\u001btA!!1\u0002JB\u0019\u00111Y\u001b\u000e\u0005\u0005\u0015'bAAd\u0007\u00061AH]8pizJ1!a36\u0003\u0019\u0001&/\u001a3fM&!\u0011\u0011WAh\u0015\r\tY-N\u0001\u0014?R|7\u000b\u001e:j]\u001e\u0014VmY;sg&|gn]\u0001\u0013i>\u001cFO]5oOJ+7-\u001e:tS>t7/\u0001\fu_N#(/\u001b8h%\u0016\u001cWO]:j_:\u001cx\fJ3r)\r)\u0015\u0011\u001c\u0005\b\u0003+\u0001\u0003\u0019AA\u0003\u0003EyFo\\*ue&twmU;cU\u0016\u001cGo]\u0001\u0011i>\u001cFO]5oON+(M[3diN\fA\u0004Z3gS:,WK\u001c3fe2L\u0018N\\4PMNKgn\u001a7f)f\u0004X\rF\u0002F\u0003GDa!U\u0012A\u0002\u0005\u0015\bcA&\u0002h&\u0019\u0011\u0011\u001e!\u0003\u0015MKgn\u001a7f)f\u0004X-A\u0010eK\u001aLg.\u001a\"bg\u0016$\u0016\u0010]3TKF|emQ8na>,h\u000e\u001a+za\u0016$2!RAx\u0011\u0019\tF\u00051\u0001\u0002rB\u00191*a=\n\u0007\u0005U\bI\u0001\u0007D_6\u0004x.\u001e8e)f\u0004X-A\u0010eK\u001aLg.\u001a\"bg\u0016\u001cE.Y:tKN|emQ8na>,h\u000e\u001a+za\u0016$2!RA~\u0011\u0019\tV\u00051\u0001\u0002r\u00061B-\u001a4j]\u0016\u0004\u0016M]3oiN|e\rV=qKJ+g\rF\u0002F\u0005\u0003Aa!\u0015\u0014A\u0002\t\r\u0001cA&\u0003\u0006%\u0019!q\u0001!\u0003\u000fQK\b/\u001a*fM\u0006QB-\u001a4j]\u0016\u0014\u0015m]3UsB,7+Z9PMRK\b/\u001a*fMR\u0019QI!\u0004\t\rE;\u0003\u0019\u0001B\u0002\u0003A!WMZ5oK:{'/\\1mSj,G\rF\u0002F\u0005'AqA!\u0006)\u0001\u0004\u0011\u0019!\u0001\u0002ue\u0006a1/\u001e9fe\u0012*h.[9vKV!!1\u0004B\u0010)\u0011\u0011iB!\t\u0011\u0007M\u0014y\u0002B\u0003vS\t\u0007a\u000f\u0003\u0004|S\u0001\u0007!QD\u0005\u0003_\u0002\u000b!e];qKJ$C-\u001a4j]\u0016,f\u000eZ3sYfLgnZ(g'&tw\r\\3UsB,GcA#\u0003*!1\u0011K\u000ba\u0001\u0003KL1!a8A\u0003\u0015\u001aX\u000f]3sI\u0011,g-\u001b8f\u0005\u0006\u001cX\rV=qKN+\u0017o\u00144D_6\u0004x.\u001e8e)f\u0004X\rF\u0002F\u0005cAa!U\u0016A\u0002\u0005E\u0018bAAv\u0001\u0006)3/\u001e9fe\u0012\"WMZ5oK\n\u000b7/Z\"mCN\u001cXm](g\u0007>l\u0007o\\;oIRK\b/\u001a\u000b\u0004\u000b\ne\u0002BB)-\u0001\u0004\t\t0C\u0002\u0002x\u0002\u000bAd];qKJ$C-\u001a4j]\u0016\u0004\u0016M]3oiN|e\rV=qKJ+g\rF\u0002F\u0005\u0003Ba!U\u0017A\u0002\t\r\u0011bAA\u007f\u0001\u0006\u00013/\u001e9fe\u0012\"WMZ5oK\n\u000b7/\u001a+za\u0016\u001cV-](g)f\u0004XMU3g)\r)%\u0011\n\u0005\u0007#:\u0002\rAa\u0001\n\u0007\t%\u0001)\u0001\ftkB,'\u000f\n3fM&tWMT8s[\u0006d\u0017N_3e)\r)%\u0011\u000b\u0005\b\u0005+y\u0003\u0019\u0001B\u0002\u0013\r\u0011y\u0001\u0011\t\u0005\u0005/\u0012I&D\u00012\u0013\r\u0011Y&\r\u0002\f'fl'm\u001c7UC\ndW\r"
)
public interface SynchronizedTypes extends Types {
   void scala$reflect$runtime$SynchronizedTypes$_setter_$scala$reflect$runtime$SynchronizedTypes$$uniques_$eq(final WeakHashMap x$1);

   // $FF: synthetic method
   Types.Type scala$reflect$runtime$SynchronizedTypes$$super$unique(final Types.Type tp);

   // $FF: synthetic method
   void scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(final Types.SingleType tpe);

   // $FF: synthetic method
   void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(final Types.CompoundType tpe);

   // $FF: synthetic method
   void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(final Types.CompoundType tpe);

   // $FF: synthetic method
   void scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(final Types.TypeRef tpe);

   // $FF: synthetic method
   void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(final Types.TypeRef tpe);

   // $FF: synthetic method
   void scala$reflect$runtime$SynchronizedTypes$$super$defineNormalized(final Types.TypeRef tr);

   // $FF: synthetic method
   static CommonOwners.CommonOwnerMap commonOwnerMap$(final SynchronizedTypes $this) {
      return $this.commonOwnerMap();
   }

   default CommonOwners.CommonOwnerMap commonOwnerMap() {
      return (scala.reflect.internal.SymbolTable)this.new CommonOwnerMap();
   }

   // $FF: synthetic method
   static Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock();
   }

   default Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock() {
      return new Object();
   }

   WeakHashMap scala$reflect$runtime$SynchronizedTypes$$uniques();

   // $FF: synthetic method
   static Types.Type unique$(final SynchronizedTypes $this, final Types.Type tp) {
      return $this.unique(tp);
   }

   default Types.Type unique(final Types.Type tp) {
      synchronized(this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock()){}

      Types.Type var3;
      try {
         Types.Type var10000;
         if (!((SymbolTable)this).isCompilerUniverse()) {
            Option inCache = this.scala$reflect$runtime$SynchronizedTypes$$uniques().get(tp);
            Types.Type result = inCache.isDefined() ? (Types.Type)((Reference)inCache.get()).get() : null;
            if (result != null) {
               var10000 = result;
            } else {
               this.scala$reflect$runtime$SynchronizedTypes$$uniques().update(tp, new WeakReference(tp));
               var10000 = tp;
            }
         } else {
            var10000 = this.scala$reflect$runtime$SynchronizedTypes$$super$unique(tp);
         }

         var3 = var10000;
      } catch (Throwable var7) {
         throw var7;
      }

      return var3;
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      JFunction0.mcI.sp mkThreadLocalStorage_x = () -> 0;
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static int skolemizationLevel$(final SynchronizedTypes $this) {
      return $this.skolemizationLevel();
   }

   default int skolemizationLevel() {
      return BoxesRunTime.unboxToInt(this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel().get());
   }

   // $FF: synthetic method
   static void skolemizationLevel_$eq$(final SynchronizedTypes $this, final int value) {
      $this.skolemizationLevel_$eq(value);
   }

   default void skolemizationLevel_$eq(final int value) {
      this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel().set(value);
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_undoLog$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_undoLog();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_undoLog() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> (scala.reflect.internal.SymbolTable)this.new UndoLog();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static TypeConstraints.UndoLog undoLog$(final SynchronizedTypes $this) {
      return $this.undoLog();
   }

   default TypeConstraints.UndoLog undoLog() {
      return (TypeConstraints.UndoLog)this.scala$reflect$runtime$SynchronizedTypes$$_undoLog().get();
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> ((scala.reflect.internal.SymbolTable)this).perRunCaches().newWeakMap();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static WeakHashMap intersectionWitness$(final SynchronizedTypes $this) {
      return $this.intersectionWitness();
   }

   default WeakHashMap intersectionWitness() {
      return (WeakHashMap)this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness().get();
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      JFunction0.mcI.sp mkThreadLocalStorage_x = () -> 0;
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static int subsametypeRecursions$(final SynchronizedTypes $this) {
      return $this.subsametypeRecursions();
   }

   default int subsametypeRecursions() {
      return BoxesRunTime.unboxToInt(this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions().get());
   }

   // $FF: synthetic method
   static void subsametypeRecursions_$eq$(final SynchronizedTypes $this, final int value) {
      $this.subsametypeRecursions_$eq(value);
   }

   default void subsametypeRecursions_$eq(final int value) {
      this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions().set(value);
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> new HashSet();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static HashSet pendingSubTypes$(final SynchronizedTypes $this) {
      return $this.pendingSubTypes();
   }

   default HashSet pendingSubTypes() {
      return (HashSet)this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes().get();
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      JFunction0.mcI.sp mkThreadLocalStorage_x = () -> 0;
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static int basetypeRecursions$(final SynchronizedTypes $this) {
      return $this.basetypeRecursions();
   }

   default int basetypeRecursions() {
      return BoxesRunTime.unboxToInt(this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions().get());
   }

   // $FF: synthetic method
   static void basetypeRecursions_$eq$(final SynchronizedTypes $this, final int value) {
      $this.basetypeRecursions_$eq(value);
   }

   default void basetypeRecursions_$eq(final int value) {
      this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions().set(value);
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> new HashSet();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static HashSet pendingBaseTypes$(final SynchronizedTypes $this) {
      return $this.pendingBaseTypes();
   }

   default HashSet pendingBaseTypes() {
      return (HashSet)this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes().get();
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_lubResults$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_lubResults();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_lubResults() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> new HashMap();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static HashMap lubResults$(final SynchronizedTypes $this) {
      return $this.lubResults();
   }

   default HashMap lubResults() {
      return (HashMap)this.scala$reflect$runtime$SynchronizedTypes$$_lubResults().get();
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_glbResults$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_glbResults();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_glbResults() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> new HashMap();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static HashMap glbResults$(final SynchronizedTypes $this) {
      return $this.glbResults();
   }

   default HashMap glbResults() {
      return (HashMap)this.scala$reflect$runtime$SynchronizedTypes$$_glbResults().get();
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_indent$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_indent();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_indent() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> "";
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static String indent$(final SynchronizedTypes $this) {
      return $this.indent();
   }

   default String indent() {
      return (String)this.scala$reflect$runtime$SynchronizedTypes$$_indent().get();
   }

   // $FF: synthetic method
   static void indent_$eq$(final SynchronizedTypes $this, final String value) {
      $this.indent_$eq(value);
   }

   default void indent_$eq(final String value) {
      this.scala$reflect$runtime$SynchronizedTypes$$_indent().set(value);
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      JFunction0.mcI.sp mkThreadLocalStorage_x = () -> 0;
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static int toStringRecursions$(final SynchronizedTypes $this) {
      return $this.toStringRecursions();
   }

   default int toStringRecursions() {
      return BoxesRunTime.unboxToInt(this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions().get());
   }

   // $FF: synthetic method
   static void toStringRecursions_$eq$(final SynchronizedTypes $this, final int value) {
      $this.toStringRecursions_$eq(value);
   }

   default void toStringRecursions_$eq(final int value) {
      this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions().set(value);
   }

   // $FF: synthetic method
   static ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects$(final SynchronizedTypes $this) {
      return $this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects();
   }

   default ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects() {
      ThreadLocalStorage var10000 = (ThreadLocalStorage)this;
      Function0 mkThreadLocalStorage_x = () -> new HashSet();
      ThreadLocalStorage mkThreadLocalStorage_this = var10000;
      return (SymbolTable)mkThreadLocalStorage_this.new MyThreadLocalStorage(mkThreadLocalStorage_x);
   }

   // $FF: synthetic method
   static HashSet toStringSubjects$(final SynchronizedTypes $this) {
      return $this.toStringSubjects();
   }

   default HashSet toStringSubjects() {
      return (HashSet)this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects().get();
   }

   // $FF: synthetic method
   static void defineUnderlyingOfSingleType$(final SynchronizedTypes $this, final Types.SingleType tpe) {
      $this.defineUnderlyingOfSingleType(tpe);
   }

   default void defineUnderlyingOfSingleType(final Types.SingleType tpe) {
      Gil gilSynchronized_this = (Gil)this;
      if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
         this.scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(tpe);
      } else {
         try {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
            this.scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(tpe);
         } finally {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
         }

      }
   }

   // $FF: synthetic method
   static void defineBaseTypeSeqOfCompoundType$(final SynchronizedTypes $this, final Types.CompoundType tpe) {
      $this.defineBaseTypeSeqOfCompoundType(tpe);
   }

   default void defineBaseTypeSeqOfCompoundType(final Types.CompoundType tpe) {
      Gil gilSynchronized_this = (Gil)this;
      if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
         this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(tpe);
      } else {
         try {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
            this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(tpe);
         } finally {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
         }

      }
   }

   // $FF: synthetic method
   static void defineBaseClassesOfCompoundType$(final SynchronizedTypes $this, final Types.CompoundType tpe) {
      $this.defineBaseClassesOfCompoundType(tpe);
   }

   default void defineBaseClassesOfCompoundType(final Types.CompoundType tpe) {
      Gil gilSynchronized_this = (Gil)this;
      if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
         this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(tpe);
      } else {
         try {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
            this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(tpe);
         } finally {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
         }

      }
   }

   // $FF: synthetic method
   static void defineParentsOfTypeRef$(final SynchronizedTypes $this, final Types.TypeRef tpe) {
      $this.defineParentsOfTypeRef(tpe);
   }

   default void defineParentsOfTypeRef(final Types.TypeRef tpe) {
      Gil gilSynchronized_this = (Gil)this;
      if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
         this.scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(tpe);
      } else {
         try {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
            this.scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(tpe);
         } finally {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
         }

      }
   }

   // $FF: synthetic method
   static void defineBaseTypeSeqOfTypeRef$(final SynchronizedTypes $this, final Types.TypeRef tpe) {
      $this.defineBaseTypeSeqOfTypeRef(tpe);
   }

   default void defineBaseTypeSeqOfTypeRef(final Types.TypeRef tpe) {
      Gil gilSynchronized_this = (Gil)this;
      if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
         this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(tpe);
      } else {
         try {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
            this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(tpe);
         } finally {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
         }

      }
   }

   // $FF: synthetic method
   static void defineNormalized$(final SynchronizedTypes $this, final Types.TypeRef tr) {
      $this.defineNormalized(tr);
   }

   default void defineNormalized(final Types.TypeRef tr) {
      Gil gilSynchronized_this = (Gil)this;
      if (((SymbolTable)gilSynchronized_this).isCompilerUniverse()) {
         this.scala$reflect$runtime$SynchronizedTypes$$super$defineNormalized(tr);
      } else {
         try {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().lock();
            this.scala$reflect$runtime$SynchronizedTypes$$super$defineNormalized(tr);
         } finally {
            gilSynchronized_this.scala$reflect$runtime$Gil$$gil().unlock();
         }

      }
   }

   // $FF: synthetic method
   static void $anonfun$defineUnderlyingOfSingleType$1(final SynchronizedTypes $this, final Types.SingleType tpe$1) {
      $this.scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(tpe$1);
   }

   // $FF: synthetic method
   static void $anonfun$defineBaseTypeSeqOfCompoundType$1(final SynchronizedTypes $this, final Types.CompoundType tpe$2) {
      $this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(tpe$2);
   }

   // $FF: synthetic method
   static void $anonfun$defineBaseClassesOfCompoundType$1(final SynchronizedTypes $this, final Types.CompoundType tpe$3) {
      $this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(tpe$3);
   }

   // $FF: synthetic method
   static void $anonfun$defineParentsOfTypeRef$1(final SynchronizedTypes $this, final Types.TypeRef tpe$4) {
      $this.scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(tpe$4);
   }

   // $FF: synthetic method
   static void $anonfun$defineBaseTypeSeqOfTypeRef$1(final SynchronizedTypes $this, final Types.TypeRef tpe$5) {
      $this.scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(tpe$5);
   }

   // $FF: synthetic method
   static void $anonfun$defineNormalized$1(final SynchronizedTypes $this, final Types.TypeRef tr$1) {
      $this.scala$reflect$runtime$SynchronizedTypes$$super$defineNormalized(tr$1);
   }

   static void $init$(final SynchronizedTypes $this) {
      $this.scala$reflect$runtime$SynchronizedTypes$_setter_$scala$reflect$runtime$SynchronizedTypes$$uniques_$eq((WeakHashMap)MapFactory.apply$(.MODULE$, scala.collection.immutable.Nil..MODULE$));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
