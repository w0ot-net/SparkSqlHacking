package scala.reflect.runtime;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Option;
import scala.Tuple2;
import scala.Console.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.WeakHashMap;
import scala.reflect.ClassTag;
import scala.reflect.Manifest;
import scala.reflect.ManifestFactory;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Mirror;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.internal.BaseTypeSeqs;
import scala.reflect.internal.BaseTypeSeqsStats;
import scala.reflect.internal.Constants;
import scala.reflect.internal.Internals;
import scala.reflect.internal.Names;
import scala.reflect.internal.Reporter;
import scala.reflect.internal.Reporting;
import scala.reflect.internal.ScopeStats;
import scala.reflect.internal.Scopes;
import scala.reflect.internal.SomePhase$;
import scala.reflect.internal.SymbolTableStats;
import scala.reflect.internal.Symbols;
import scala.reflect.internal.SymbolsStats;
import scala.reflect.internal.TreeInfo;
import scala.reflect.internal.Trees;
import scala.reflect.internal.TreesStats;
import scala.reflect.internal.Types;
import scala.reflect.internal.TypesStats;
import scala.reflect.internal.tpe.CommonOwners;
import scala.reflect.internal.tpe.TypeConstraints;
import scala.reflect.internal.util.FreshNameCreator;
import scala.reflect.internal.util.Position;
import scala.reflect.internal.util.Statistics;
import scala.reflect.macros.Attachments;
import scala.reflect.macros.Universe;
import scala.runtime.Statics;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]e\u0001\u0002\u000f\u001e\u0001\u0011BQ\u0001\u000e\u0001\u0005\u0002UBQa\u000e\u0001\u0005\u0002aBQ\u0001\u0010\u0001\u0005\u0002aB\u0001\"\u0010\u0001\t\u0006\u0004%\tA\u0010\u0005\b\u0005\u0002\u0011\r\u0011\"\u0012D\u0011\u00199\u0005\u0001)A\u0007\t\"1!\u000b\u0001Q\u0001\nMCQa\u0016\u0001\u0005\u0002aCQ\u0001\u001a\u0001\u0005B\u0015DQ!\u001b\u0001\u0005\u0002)4AA\u001d\u0001\u0001g\")Ag\u0003C\u0001o\")\u0011p\u0003C\u0001u\"9\u0011\u0011\t\u0001\u0005\u0012\u0005\rSABA#\u0001\u0001\t9\u0005C\u0005\u0002R\u0001\u0011\r\u0011b\u0001\u0002T!A\u0011q\f\u0001!\u0002\u0013\t)\u0006C\u0004\u0002b\u0001!\t!a\u0019\t\u000f\u0005\u0015\u0004\u0001\"\u0001\u0002d!9\u0011q\r\u0001\u0005\u0002\u0005%\u0004\"\u0003\u0015\u0001\u0011\u000b\u0007I\u0011IA9\u000f\u001d\ti\b\u0001E\u0001\u0003\u007f2q!!!\u0001\u0011\u0003\t\u0019\tC\u0005\u0002\f^\u0011\r\u0011\"\u0001\u0002\u000e\"Q\u0011qR\f\u0005\u0002\u0003\u0005\u000b\u0011B(\t\rQ:B\u0011AAI\u0011\u001d\t\u0019\n\u0001C\u0001\u0003+\u0013ABS1wCVs\u0017N^3sg\u0016T!AH\u0010\u0002\u000fI,h\u000e^5nK*\u0011\u0001%I\u0001\be\u00164G.Z2u\u0015\u0005\u0011\u0013!B:dC2\f7\u0001A\n\u0006\u0001\u0015ZsF\r\t\u0003M%j\u0011a\n\u0006\u0003Q}\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003U\u001d\u00121bU=nE>dG+\u00192mKB\u0011A&L\u0007\u0002;%\u0011a&\b\u0002\u0012\u0015\u00064\u0018-\u00168jm\u0016\u00148/\u001a$pe\u000e,\u0007C\u0001\u00171\u0013\t\tTD\u0001\u0007SK\u001adWm\u0019;TKR,\b\u000f\u0005\u0002-g%\u0011!&H\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Y\u0002\"\u0001\f\u0001\u0002\u0019AL7m\u001b7feBC\u0017m]3\u0016\u0003e\u0002\"A\n\u001e\u000b\u0005m:\u0013!C*p[\u0016\u0004\u0006.Y:f\u00031)'/Y:ve\u0016\u0004\u0006.Y:f\u0003!\u0019X\r\u001e;j]\u001e\u001cX#A \u0011\u00051\u0002\u0015BA!\u001e\u0005!\u0019V\r\u001e;j]\u001e\u001c\u0018AC:uCRL7\u000f^5dgV\tAIE\u0002F\u0011:3AA\u0012\u0004\u0001\t\naAH]3gS:,W.\u001a8u}\u0005Y1\u000f^1uSN$\u0018nY:!!\tIE*D\u0001K\u0015\tYu%\u0001\u0003vi&d\u0017BA'K\u0005)\u0019F/\u0019;jgRL7m\u001d\t\u0003\u001fBk\u0011\u0001A\u0005\u0003#&\u0012ABU3gY\u0016\u001cGo\u0015;biN\f\u0011\"[:M_\u001e<\u0017N\\4\u0011\u0005Q+V\"A\u0011\n\u0005Y\u000b#a\u0002\"p_2,\u0017M\\\u0001\u0004Y><GCA-]!\t!&,\u0003\u0002\\C\t!QK\\5u\u0011\u0019i\u0006\u0002\"a\u0001=\u0006\u0019Qn]4\u0011\u0007Q{\u0016-\u0003\u0002aC\tAAHY=oC6,g\b\u0005\u0002UE&\u00111-\t\u0002\u0007\u0003:L(+\u001a4\u0002\u0011I,\u0007o\u001c:uKJ,\u0012A\u001a\t\u0003M\u001dL!\u0001[\u0014\u0003\u0011I+\u0007o\u001c:uKJ\f!bY;se\u0016tGOU;o+\u0005Y'c\u00017b[\u001a!aI\u0003\u0001l!\tye.\u0003\u0002pa\na!+\u001e8SKB|'\u000f^5oO&\u0011\u0011o\n\u0002\n%\u0016\u0004xN\u001d;j]\u001e\u0014q\u0002U3s%Vt'+\u001a9peRLgnZ\n\u0003\u0017Q\u0004\"aT;\n\u0005Y\u0004(a\u0005)feJ+hNU3q_J$\u0018N\\4CCN,G#\u0001=\u0011\u0005=[\u0011A\u00053faJ,7-\u0019;j_:<\u0016M\u001d8j]\u001e$B\"W>\u0002\u0006\u0005u\u0011\u0011EA\u0013\u0003SAQ\u0001`\u0007A\u0002u\f1\u0001]8t!\tye0C\u0002\u0000\u0003\u0003\u0011\u0001\u0002U8tSRLwN\\\u0005\u0004\u0003\u00079#!\u0003)pg&$\u0018n\u001c8t\u0011\u0019iV\u00021\u0001\u0002\bA!\u0011\u0011BA\f\u001d\u0011\tY!a\u0005\u0011\u0007\u00055\u0011%\u0004\u0002\u0002\u0010)\u0019\u0011\u0011C\u0012\u0002\rq\u0012xn\u001c;?\u0013\r\t)\"I\u0001\u0007!J,G-\u001a4\n\t\u0005e\u00111\u0004\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u0005U\u0011\u0005C\u0004\u0002 5\u0001\r!a\u0002\u0002\u000bMLgnY3\t\u000f\u0005\rR\u00021\u0001\u0002\b\u0005!1/\u001b;f\u0011\u001d\t9#\u0004a\u0001\u0003\u000f\taa\u001c:jO&t\u0007\"CA\u0016\u001bA\u0005\t\u0019AA\u0017\u0003\u001d\t7\r^5p]N\u0004b!a\f\u00026\u0005mbb\u0001+\u00022%\u0019\u00111G\u0011\u0002\u000fA\f7m[1hK&!\u0011qGA\u001d\u0005\u0011a\u0015n\u001d;\u000b\u0007\u0005M\u0012\u0005E\u0002J\u0003{I1!a\u0010K\u0005)\u0019u\u000eZ3BGRLwN\\\u0001\u0010!\u0016\u0014(+\u001e8SKB|'\u000f^5oOV\t\u0001P\u0001\u0006Ue\u0016,7i\u001c9jKJ\u00042aTA%\u0013\u0011\tY%!\u0014\u0003+%sG/\u001a:oC2$&/Z3D_BLWM](qg&\u0019\u0011qJ\u0014\u0003\u000bQ\u0013X-Z:\u0002\u001bQ\u0013X-Z\"pa&,'\u000fV1h+\t\t)\u0006\u0005\u0004\u0002X\u0005e\u0013QL\u0007\u0002?%\u0019\u00111L\u0010\u0003\u0011\rc\u0017m]:UC\u001e\u0004\"aT\b\u0002\u001dQ\u0013X-Z\"pa&,'\u000fV1hA\u0005\u0019b.Z<TiJL7\r\u001e+sK\u0016\u001cu\u000e]5feV\u0011\u0011QL\u0001\u0012]\u0016<H*\u0019>z)J,WmQ8qS\u0016\u0014\u0018aF2veJ,g\u000e\u001e$sKNDg*Y7f\u0007J,\u0017\r^8s+\t\tY\u0007E\u0002J\u0003[J1!a\u001cK\u0005A1%/Z:i\u001d\u0006lWm\u0011:fCR|'/\u0006\u0002\u0002tA\u0019q*!\u001e\n\t\u0005]\u0014\u0011\u0010\u0002\t\u0013:$XM\u001d8bY&\u0019\u00111P\u0014\u0003\u0013%sG/\u001a:oC2\u001c\u0018\u0001\u0003;sK\u0016LeNZ8\u0011\u0005=;\"\u0001\u0003;sK\u0016LeNZ8\u0014\u0007]\t)\tE\u0002'\u0003\u000fK1!!#(\u0005!!&/Z3J]\u001a|\u0017AB4m_\n\fG.F\u0001P\u0003\u001d9Gn\u001c2bY\u0002\"\"!a \u0002\t%t\u0017\u000e\u001e\u000b\u00023\u0002"
)
public class JavaUniverse extends scala.reflect.internal.SymbolTable implements JavaUniverseForce, ReflectSetup, SymbolTable {
   private Settings settings;
   private Universe.MacroInternalApi internal;
   private volatile treeInfo$ treeInfo$module;
   private final Statistics statistics;
   private final boolean isLogging;
   private final ClassTag TreeCopierTag;
   private ReentrantLock scala$reflect$runtime$Gil$$gil;
   private Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock;
   private WeakHashMap scala$reflect$runtime$SynchronizedTypes$$uniques;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_undoLog;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_lubResults;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_glbResults;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_indent;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects;
   private AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds;
   /** @deprecated */
   private AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds;
   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedSymbols$$_recursionTable;
   private WeakHashMap scala$reflect$runtime$JavaMirrors$$mirrors;
   private ClassTag MirrorTag;
   private JavaMirrors.JavaMirror rootMirror;
   private ClassTag RuntimeClassTag;
   private int currentRunId;
   private volatile int bitmap$0;

   public void info(final Function0 msg) {
      SymbolTable.info$(this, msg);
   }

   public void debugInfo(final Function0 msg) {
      SymbolTable.debugInfo$(this, msg);
   }

   public boolean isCompilerUniverse() {
      return SymbolTable.isCompilerUniverse$(this);
   }

   public final scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage mkThreadLocalStorage(final Function0 x) {
      return scala.reflect.runtime.ThreadLocalStorage.mkThreadLocalStorage$(this, x);
   }

   public final Object gilSynchronized(final Function0 body) {
      return Gil.gilSynchronized$(this, body);
   }

   public boolean synchronizeNames() {
      return SynchronizedOps.synchronizeNames$(this);
   }

   public BaseTypeSeqs.BaseTypeSeq newBaseTypeSeq(final List parents, final Types.Type[] elems) {
      return SynchronizedOps.newBaseTypeSeq$(this, parents, elems);
   }

   public BaseTypeSeqs.MappedBaseTypeSeq newMappedBaseTypeSeq(final BaseTypeSeqs.BaseTypeSeq orig, final Function1 f) {
      return SynchronizedOps.newMappedBaseTypeSeq$(this, orig, f);
   }

   public SynchronizedOps.SynchronizedScope newScope() {
      return SynchronizedOps.newScope$(this);
   }

   // $FF: synthetic method
   public Types.Type scala$reflect$runtime$SynchronizedTypes$$super$unique(final Types.Type tp) {
      return Types.unique$(this, tp);
   }

   // $FF: synthetic method
   public void scala$reflect$runtime$SynchronizedTypes$$super$defineUnderlyingOfSingleType(final Types.SingleType tpe) {
      Types.defineUnderlyingOfSingleType$(this, tpe);
   }

   // $FF: synthetic method
   public void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfCompoundType(final Types.CompoundType tpe) {
      Types.defineBaseTypeSeqOfCompoundType$(this, tpe);
   }

   // $FF: synthetic method
   public void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseClassesOfCompoundType(final Types.CompoundType tpe) {
      Types.defineBaseClassesOfCompoundType$(this, tpe);
   }

   // $FF: synthetic method
   public void scala$reflect$runtime$SynchronizedTypes$$super$defineParentsOfTypeRef(final Types.TypeRef tpe) {
      Types.defineParentsOfTypeRef$(this, tpe);
   }

   // $FF: synthetic method
   public void scala$reflect$runtime$SynchronizedTypes$$super$defineBaseTypeSeqOfTypeRef(final Types.TypeRef tpe) {
      Types.defineBaseTypeSeqOfTypeRef$(this, tpe);
   }

   // $FF: synthetic method
   public void scala$reflect$runtime$SynchronizedTypes$$super$defineNormalized(final Types.TypeRef tr) {
      Types.defineNormalized$(this, tr);
   }

   public CommonOwners.CommonOwnerMap commonOwnerMap() {
      return SynchronizedTypes.commonOwnerMap$(this);
   }

   public Types.Type unique(final Types.Type tp) {
      return SynchronizedTypes.unique$(this, tp);
   }

   public int skolemizationLevel() {
      return SynchronizedTypes.skolemizationLevel$(this);
   }

   public void skolemizationLevel_$eq(final int value) {
      SynchronizedTypes.skolemizationLevel_$eq$(this, value);
   }

   public TypeConstraints.UndoLog undoLog() {
      return SynchronizedTypes.undoLog$(this);
   }

   public WeakHashMap intersectionWitness() {
      return SynchronizedTypes.intersectionWitness$(this);
   }

   public int subsametypeRecursions() {
      return SynchronizedTypes.subsametypeRecursions$(this);
   }

   public void subsametypeRecursions_$eq(final int value) {
      SynchronizedTypes.subsametypeRecursions_$eq$(this, value);
   }

   public HashSet pendingSubTypes() {
      return SynchronizedTypes.pendingSubTypes$(this);
   }

   public int basetypeRecursions() {
      return SynchronizedTypes.basetypeRecursions$(this);
   }

   public void basetypeRecursions_$eq(final int value) {
      SynchronizedTypes.basetypeRecursions_$eq$(this, value);
   }

   public HashSet pendingBaseTypes() {
      return SynchronizedTypes.pendingBaseTypes$(this);
   }

   public HashMap lubResults() {
      return SynchronizedTypes.lubResults$(this);
   }

   public HashMap glbResults() {
      return SynchronizedTypes.glbResults$(this);
   }

   public String indent() {
      return SynchronizedTypes.indent$(this);
   }

   public void indent_$eq(final String value) {
      SynchronizedTypes.indent_$eq$(this, value);
   }

   public int toStringRecursions() {
      return SynchronizedTypes.toStringRecursions$(this);
   }

   public void toStringRecursions_$eq(final int value) {
      SynchronizedTypes.toStringRecursions_$eq$(this, value);
   }

   public HashSet toStringSubjects() {
      return SynchronizedTypes.toStringSubjects$(this);
   }

   public void defineUnderlyingOfSingleType(final Types.SingleType tpe) {
      SynchronizedTypes.defineUnderlyingOfSingleType$(this, tpe);
   }

   public void defineBaseTypeSeqOfCompoundType(final Types.CompoundType tpe) {
      SynchronizedTypes.defineBaseTypeSeqOfCompoundType$(this, tpe);
   }

   public void defineBaseClassesOfCompoundType(final Types.CompoundType tpe) {
      SynchronizedTypes.defineBaseClassesOfCompoundType$(this, tpe);
   }

   public void defineParentsOfTypeRef(final Types.TypeRef tpe) {
      SynchronizedTypes.defineParentsOfTypeRef$(this, tpe);
   }

   public void defineBaseTypeSeqOfTypeRef(final Types.TypeRef tpe) {
      SynchronizedTypes.defineBaseTypeSeqOfTypeRef$(this, tpe);
   }

   public void defineNormalized(final Types.TypeRef tr) {
      SynchronizedTypes.defineNormalized$(this, tr);
   }

   // $FF: synthetic method
   public Symbols.ModuleSymbol scala$reflect$runtime$SynchronizedSymbols$$super$connectModuleToClass(final Symbols.ModuleSymbol m, final Symbols.ClassSymbol moduleClass) {
      return Symbols.connectModuleToClass$(this, m, moduleClass);
   }

   public int nextId() {
      return SynchronizedSymbols.nextId$(this);
   }

   /** @deprecated */
   public int nextExistentialId() {
      return SynchronizedSymbols.nextExistentialId$(this);
   }

   public Map recursionTable() {
      return SynchronizedSymbols.recursionTable$(this);
   }

   public void recursionTable_$eq(final Map value) {
      SynchronizedSymbols.recursionTable_$eq$(this, value);
   }

   public Symbols.ModuleSymbol connectModuleToClass(final Symbols.ModuleSymbol m, final Symbols.ClassSymbol moduleClass) {
      return SynchronizedSymbols.connectModuleToClass$(this, m, moduleClass);
   }

   public Symbols.FreeTermSymbol newFreeTermSymbol(final Names.TermName name, final Function0 value, final long flags, final String origin) {
      return SynchronizedSymbols.newFreeTermSymbol$(this, name, value, flags, origin);
   }

   public long newFreeTermSymbol$default$3() {
      return SynchronizedSymbols.newFreeTermSymbol$default$3$(this);
   }

   public String newFreeTermSymbol$default$4() {
      return SynchronizedSymbols.newFreeTermSymbol$default$4$(this);
   }

   public Symbols.FreeTypeSymbol newFreeTypeSymbol(final Names.TypeName name, final long flags, final String origin) {
      return SynchronizedSymbols.newFreeTypeSymbol$(this, name, flags, origin);
   }

   public long newFreeTypeSymbol$default$2() {
      return SynchronizedSymbols.newFreeTypeSymbol$default$2$(this);
   }

   public String newFreeTypeSymbol$default$3() {
      return SynchronizedSymbols.newFreeTypeSymbol$default$3$(this);
   }

   public Symbols.NoSymbol makeNoSymbol() {
      return SynchronizedSymbols.makeNoSymbol$(this);
   }

   public Tuple2 initAndEnterClassAndModule(final Symbols.Symbol owner, final Names.TypeName name, final Function2 completer) {
      return SymbolLoaders.initAndEnterClassAndModule$(this, owner, name, completer);
   }

   public void setAllInfos(final Symbols.Symbol clazz, final Symbols.Symbol module, final Types.Type info) {
      SymbolLoaders.setAllInfos$(this, clazz, module, info);
   }

   public void initClassAndModule(final Symbols.Symbol clazz, final Symbols.Symbol module, final Types.LazyType completer) {
      SymbolLoaders.initClassAndModule$(this, clazz, module, completer);
   }

   public void validateClassInfo(final Types.ClassInfoType tp) {
      SymbolLoaders.validateClassInfo$(this, tp);
   }

   public SymbolLoaders.PackageScope newPackageScope(final Symbols.Symbol pkgClass) {
      return SymbolLoaders.newPackageScope$(this, pkgClass);
   }

   public Scopes.Scope scopeTransform(final Symbols.Symbol owner, final Function0 op) {
      return SymbolLoaders.scopeTransform$(this, owner, op);
   }

   // $FF: synthetic method
   public Symbols.Symbol scala$reflect$runtime$JavaMirrors$$super$missingHook(final Symbols.Symbol owner, final Names.Name name) {
      return super.missingHook(owner, name);
   }

   public ClassLoader rootClassLoader() {
      return JavaMirrors.rootClassLoader$(this);
   }

   public JavaMirrors.JavaMirror runtimeMirror(final ClassLoader cl) {
      return JavaMirrors.runtimeMirror$(this, cl);
   }

   public JavaMirrors.JavaMirror mirrorThatLoaded(final Symbols.Symbol sym) {
      return JavaMirrors.mirrorThatLoaded$(this, sym);
   }

   public Symbols.Symbol missingHook(final Symbols.Symbol owner, final Names.Name name) {
      return JavaMirrors.missingHook$(this, owner, name);
   }

   public void force() {
      JavaUniverseForce.force$(this);
   }

   public treeInfo$ treeInfo() {
      if (this.treeInfo$module == null) {
         this.treeInfo$lzycompute$1();
      }

      return this.treeInfo$module;
   }

   private ReentrantLock scala$reflect$runtime$Gil$$gil$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4) == 0) {
            this.scala$reflect$runtime$Gil$$gil = new ReentrantLock();
            this.bitmap$0 |= 4;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$Gil$$gil;
   }

   public ReentrantLock scala$reflect$runtime$Gil$$gil() {
      return (this.bitmap$0 & 4) == 0 ? this.scala$reflect$runtime$Gil$$gil$lzycompute() : this.scala$reflect$runtime$Gil$$gil;
   }

   private Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock = new Object();
            this.bitmap$0 |= 8;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock;
   }

   public Object scala$reflect$runtime$SynchronizedTypes$$uniqueLock() {
      return (this.bitmap$0 & 8) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$uniqueLock;
   }

   public WeakHashMap scala$reflect$runtime$SynchronizedTypes$$uniques() {
      return this.scala$reflect$runtime$SynchronizedTypes$$uniques;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel$(this);
            this.bitmap$0 |= 16;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel() {
      return (this.bitmap$0 & 16) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_skolemizationLevel;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_undoLog$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 32) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_undoLog = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_undoLog$(this);
            this.bitmap$0 |= 32;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_undoLog;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_undoLog() {
      return (this.bitmap$0 & 32) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_undoLog$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_undoLog;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 64) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness$(this);
            this.bitmap$0 |= 64;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness() {
      return (this.bitmap$0 & 64) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_intersectionWitness;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 128) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions$(this);
            this.bitmap$0 |= 128;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions() {
      return (this.bitmap$0 & 128) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_subsametypeRecursions;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 256) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes$(this);
            this.bitmap$0 |= 256;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes() {
      return (this.bitmap$0 & 256) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_pendingSubTypes;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 512) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions$(this);
            this.bitmap$0 |= 512;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions() {
      return (this.bitmap$0 & 512) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_basetypeRecursions;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1024) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes$(this);
            this.bitmap$0 |= 1024;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes() {
      return (this.bitmap$0 & 1024) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_pendingBaseTypes;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_lubResults$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2048) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_lubResults = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_lubResults$(this);
            this.bitmap$0 |= 2048;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_lubResults;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_lubResults() {
      return (this.bitmap$0 & 2048) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_lubResults$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_lubResults;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_glbResults$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 4096) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_glbResults = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_glbResults$(this);
            this.bitmap$0 |= 4096;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_glbResults;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_glbResults() {
      return (this.bitmap$0 & 4096) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_glbResults$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_glbResults;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_indent$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 8192) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_indent = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_indent$(this);
            this.bitmap$0 |= 8192;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_indent;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_indent() {
      return (this.bitmap$0 & 8192) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_indent$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_indent;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 16384) == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions$(this);
            this.bitmap$0 |= 16384;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions() {
      return (this.bitmap$0 & 16384) == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_toStringRecursions;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & '耀') == 0) {
            this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects = SynchronizedTypes.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects$(this);
            this.bitmap$0 |= 32768;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects() {
      return (this.bitmap$0 & '耀') == 0 ? this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects$lzycompute() : this.scala$reflect$runtime$SynchronizedTypes$$_toStringSubjects;
   }

   public final void scala$reflect$runtime$SynchronizedTypes$_setter_$scala$reflect$runtime$SynchronizedTypes$$uniques_$eq(final WeakHashMap x$1) {
      this.scala$reflect$runtime$SynchronizedTypes$$uniques = x$1;
   }

   private AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 65536) == 0) {
            this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds = new AtomicInteger(0);
            this.bitmap$0 |= 65536;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds;
   }

   public AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicIds() {
      return (this.bitmap$0 & 65536) == 0 ? this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$$atomicIds;
   }

   private AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 131072) == 0) {
            this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds = new AtomicInteger(0);
            this.bitmap$0 |= 131072;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds;
   }

   /** @deprecated */
   public AtomicInteger scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds() {
      return (this.bitmap$0 & 131072) == 0 ? this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$$atomicExistentialIds;
   }

   private scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedSymbols$$_recursionTable$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 262144) == 0) {
            this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable = SynchronizedSymbols.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable$(this);
            this.bitmap$0 |= 262144;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable;
   }

   public scala.reflect.runtime.ThreadLocalStorage.ThreadLocalStorage scala$reflect$runtime$SynchronizedSymbols$$_recursionTable() {
      return (this.bitmap$0 & 262144) == 0 ? this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable$lzycompute() : this.scala$reflect$runtime$SynchronizedSymbols$$_recursionTable;
   }

   private WeakHashMap scala$reflect$runtime$JavaMirrors$$mirrors$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 524288) == 0) {
            this.scala$reflect$runtime$JavaMirrors$$mirrors = new WeakHashMap();
            this.bitmap$0 |= 524288;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.scala$reflect$runtime$JavaMirrors$$mirrors;
   }

   public WeakHashMap scala$reflect$runtime$JavaMirrors$$mirrors() {
      return (this.bitmap$0 & 524288) == 0 ? this.scala$reflect$runtime$JavaMirrors$$mirrors$lzycompute() : this.scala$reflect$runtime$JavaMirrors$$mirrors;
   }

   public ClassTag MirrorTag() {
      return this.MirrorTag;
   }

   private JavaMirrors.JavaMirror rootMirror$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1048576) == 0) {
            this.rootMirror = JavaMirrors.rootMirror$(this);
            this.bitmap$0 |= 1048576;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.rootMirror;
   }

   public JavaMirrors.JavaMirror rootMirror() {
      return (this.bitmap$0 & 1048576) == 0 ? this.rootMirror$lzycompute() : this.rootMirror;
   }

   public void scala$reflect$runtime$JavaMirrors$_setter_$MirrorTag_$eq(final ClassTag x$1) {
      this.MirrorTag = x$1;
   }

   public ClassTag RuntimeClassTag() {
      return this.RuntimeClassTag;
   }

   public void scala$reflect$api$JavaUniverse$_setter_$RuntimeClassTag_$eq(final ClassTag x$1) {
      this.RuntimeClassTag = x$1;
   }

   public int currentRunId() {
      return this.currentRunId;
   }

   public void scala$reflect$runtime$ReflectSetup$_setter_$currentRunId_$eq(final int x$1) {
      this.currentRunId = x$1;
   }

   public SomePhase$ picklerPhase() {
      return SomePhase$.MODULE$;
   }

   public SomePhase$ erasurePhase() {
      return SomePhase$.MODULE$;
   }

   private Settings settings$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 1) == 0) {
            this.settings = new Settings();
            this.bitmap$0 |= 1;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.settings;
   }

   public Settings settings() {
      return (this.bitmap$0 & 1) == 0 ? this.settings$lzycompute() : this.settings;
   }

   public final Statistics statistics() {
      return this.statistics;
   }

   public void log(final Function0 msg) {
      if (this.isLogging) {
         .MODULE$.err().println((new StringBuilder(10)).append("[reflect] ").append(msg.apply()).toString());
      }
   }

   public Reporter reporter() {
      return new Reporter() {
         // $FF: synthetic field
         private final JavaUniverse $outer;

         public void info0(final Position pos, final String msg, final Reporter.Severity severity, final boolean force) {
            this.$outer.log(() -> msg);
         }

         public {
            if (JavaUniverse.this == null) {
               throw null;
            } else {
               this.$outer = JavaUniverse.this;
            }
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public Reporting.RunReporting currentRun() {
      return new Reporting.RunReporting() {
         private PerRunReporting reporting;
         // $FF: synthetic field
         private final JavaUniverse $outer;

         public PerRunReporting reporting() {
            return this.reporting;
         }

         public void scala$reflect$internal$Reporting$RunReporting$_setter_$reporting_$eq(final PerRunReporting x$1) {
            this.reporting = x$1;
         }

         // $FF: synthetic method
         public Reporting scala$reflect$internal$Reporting$RunReporting$$$outer() {
            return this.$outer;
         }

         public {
            if (JavaUniverse.this == null) {
               throw null;
            } else {
               this.$outer = JavaUniverse.this;
               Reporting.RunReporting.$init$(this);
               Statics.releaseFence();
            }
         }
      };
   }

   public PerRunReporting PerRunReporting() {
      return new PerRunReporting();
   }

   public ClassTag TreeCopierTag() {
      return this.TreeCopierTag;
   }

   public Trees.InternalTreeCopierOps newStrictTreeCopier() {
      return new Trees.StrictTreeCopier();
   }

   public Trees.InternalTreeCopierOps newLazyTreeCopier() {
      return new Trees.LazyTreeCopier();
   }

   public FreshNameCreator currentFreshNameCreator() {
      return this.globalFreshNameCreator();
   }

   private Universe.MacroInternalApi internal$lzycompute() {
      synchronized(this){}

      try {
         if ((this.bitmap$0 & 2) == 0) {
            this.internal = new Internals.SymbolTableInternal() {
               private scala.reflect.api.Internals.ReificationSupportApi reificationSupport;
               private Universe.TreeGen gen;
               private Universe.MacroInternalApi.MacroDecoratorApi decorators;
               private volatile byte bitmap$0;
               // $FF: synthetic field
               private final JavaUniverse $outer;

               public scala.reflect.api.Internals.Importer createImporter(final scala.reflect.api.Universe from0) {
                  return Internals.SymbolTableInternal.createImporter$(this, from0);
               }

               public Scopes.Scope newScopeWith(final Seq elems) {
                  return Internals.SymbolTableInternal.newScopeWith$(this, elems);
               }

               public Scopes.Scope enter(final Scopes.Scope scope, final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.enter$(this, scope, sym);
               }

               public Scopes.Scope unlink(final Scopes.Scope scope, final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.unlink$(this, scope, sym);
               }

               public List freeTerms(final Trees.Tree tree) {
                  return Internals.SymbolTableInternal.freeTerms$(this, tree);
               }

               public List freeTypes(final Trees.Tree tree) {
                  return Internals.SymbolTableInternal.freeTypes$(this, tree);
               }

               public Trees.Tree substituteSymbols(final Trees.Tree tree, final List from, final List to) {
                  return Internals.SymbolTableInternal.substituteSymbols$(this, tree, from, to);
               }

               public Trees.Tree substituteTypes(final Trees.Tree tree, final List from, final List to) {
                  return Internals.SymbolTableInternal.substituteTypes$(this, tree, from, to);
               }

               public Trees.Tree substituteThis(final Trees.Tree tree, final Symbols.Symbol clazz, final Function0 to) {
                  return Internals.SymbolTableInternal.substituteThis$(this, tree, clazz, to);
               }

               public Attachments attachments(final Trees.Tree tree) {
                  return Internals.SymbolTableInternal.attachments$(this, (Trees.Tree)tree);
               }

               public Trees.Tree updateAttachment(final Trees.Tree tree, final Object attachment, final ClassTag evidence$1) {
                  return Internals.SymbolTableInternal.updateAttachment$(this, (Trees.Tree)tree, attachment, evidence$1);
               }

               public Trees.Tree removeAttachment(final Trees.Tree tree, final ClassTag evidence$2) {
                  return Internals.SymbolTableInternal.removeAttachment$(this, (Trees.Tree)tree, evidence$2);
               }

               public Trees.Tree setPos(final Trees.Tree tree, final Position newpos) {
                  return Internals.SymbolTableInternal.setPos$(this, tree, newpos);
               }

               public Trees.Tree setType(final Trees.Tree tree, final Types.Type tp) {
                  return Internals.SymbolTableInternal.setType$(this, tree, tp);
               }

               public Trees.Tree defineType(final Trees.Tree tree, final Types.Type tp) {
                  return Internals.SymbolTableInternal.defineType$(this, tree, tp);
               }

               public Trees.Tree setSymbol(final Trees.Tree tree, final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.setSymbol$(this, tree, sym);
               }

               public Trees.TypeTree setOriginal(final Trees.TypeTree tt, final Trees.Tree tree) {
                  return Internals.SymbolTableInternal.setOriginal$(this, tt, tree);
               }

               public void captureVariable(final Symbols.Symbol vble) {
                  Internals.SymbolTableInternal.captureVariable$(this, vble);
               }

               public Trees.Tree referenceCapturedVariable(final Symbols.Symbol vble) {
                  return Internals.SymbolTableInternal.referenceCapturedVariable$(this, vble);
               }

               public Types.Type capturedVariableType(final Symbols.Symbol vble) {
                  return Internals.SymbolTableInternal.capturedVariableType$(this, vble);
               }

               public Trees.ClassDef classDef(final Symbols.Symbol sym, final Trees.Template impl) {
                  return Internals.SymbolTableInternal.classDef$(this, sym, impl);
               }

               public Trees.ModuleDef moduleDef(final Symbols.Symbol sym, final Trees.Template impl) {
                  return Internals.SymbolTableInternal.moduleDef$(this, sym, impl);
               }

               public Trees.ValDef valDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
                  return Internals.SymbolTableInternal.valDef$(this, sym, rhs);
               }

               public Trees.ValDef valDef(final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.valDef$(this, sym);
               }

               public Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Modifiers mods, final List vparamss, final Trees.Tree rhs) {
                  return Internals.SymbolTableInternal.defDef$(this, sym, mods, vparamss, rhs);
               }

               public Trees.DefDef defDef(final Symbols.Symbol sym, final List vparamss, final Trees.Tree rhs) {
                  return Internals.SymbolTableInternal.defDef$(this, sym, (List)vparamss, rhs);
               }

               public Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Modifiers mods, final Trees.Tree rhs) {
                  return Internals.SymbolTableInternal.defDef$(this, sym, (Trees.Modifiers)mods, rhs);
               }

               public Trees.DefDef defDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
                  return Internals.SymbolTableInternal.defDef$(this, sym, (Trees.Tree)rhs);
               }

               public Trees.DefDef defDef(final Symbols.Symbol sym, final Function1 rhs) {
                  return Internals.SymbolTableInternal.defDef$(this, sym, (Function1)rhs);
               }

               public Trees.TypeDef typeDef(final Symbols.Symbol sym, final Trees.Tree rhs) {
                  return Internals.SymbolTableInternal.typeDef$(this, sym, rhs);
               }

               public Trees.TypeDef typeDef(final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.typeDef$(this, sym);
               }

               public Trees.LabelDef labelDef(final Symbols.Symbol sym, final List params, final Trees.Tree rhs) {
                  return Internals.SymbolTableInternal.labelDef$(this, sym, params, rhs);
               }

               public Trees.Tree changeOwner(final Trees.Tree tree, final Symbols.Symbol prev, final Symbols.Symbol next) {
                  return Internals.SymbolTableInternal.changeOwner$(this, tree, prev, next);
               }

               public boolean isFreeTerm(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.isFreeTerm$(this, symbol);
               }

               public Symbols.FreeTermSymbol asFreeTerm(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.asFreeTerm$(this, symbol);
               }

               public boolean isFreeType(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.isFreeType$(this, symbol);
               }

               public Symbols.FreeTypeSymbol asFreeType(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.asFreeType$(this, symbol);
               }

               public Symbols.TermSymbol newTermSymbol(final Symbols.Symbol symbol, final Names.TermName name, final Position pos, final long flags) {
                  return Internals.SymbolTableInternal.newTermSymbol$(this, symbol, name, pos, flags);
               }

               public Position newTermSymbol$default$3() {
                  return Internals.SymbolTableInternal.newTermSymbol$default$3$(this);
               }

               public long newTermSymbol$default$4() {
                  return Internals.SymbolTableInternal.newTermSymbol$default$4$(this);
               }

               public Tuple2 newModuleAndClassSymbol(final Symbols.Symbol symbol, final Names.Name name, final Position pos, final long flags) {
                  return Internals.SymbolTableInternal.newModuleAndClassSymbol$(this, symbol, name, pos, flags);
               }

               public Position newModuleAndClassSymbol$default$3() {
                  return Internals.SymbolTableInternal.newModuleAndClassSymbol$default$3$(this);
               }

               public long newModuleAndClassSymbol$default$4() {
                  return Internals.SymbolTableInternal.newModuleAndClassSymbol$default$4$(this);
               }

               public Symbols.MethodSymbol newMethodSymbol(final Symbols.Symbol symbol, final Names.TermName name, final Position pos, final long flags) {
                  return Internals.SymbolTableInternal.newMethodSymbol$(this, symbol, name, pos, flags);
               }

               public Position newMethodSymbol$default$3() {
                  return Internals.SymbolTableInternal.newMethodSymbol$default$3$(this);
               }

               public long newMethodSymbol$default$4() {
                  return Internals.SymbolTableInternal.newMethodSymbol$default$4$(this);
               }

               public Symbols.TypeSymbol newTypeSymbol(final Symbols.Symbol symbol, final Names.TypeName name, final Position pos, final long flags) {
                  return Internals.SymbolTableInternal.newTypeSymbol$(this, symbol, name, pos, flags);
               }

               public Position newTypeSymbol$default$3() {
                  return Internals.SymbolTableInternal.newTypeSymbol$default$3$(this);
               }

               public long newTypeSymbol$default$4() {
                  return Internals.SymbolTableInternal.newTypeSymbol$default$4$(this);
               }

               public Symbols.ClassSymbol newClassSymbol(final Symbols.Symbol symbol, final Names.TypeName name, final Position pos, final long flags) {
                  return Internals.SymbolTableInternal.newClassSymbol$(this, symbol, name, pos, flags);
               }

               public Position newClassSymbol$default$3() {
                  return Internals.SymbolTableInternal.newClassSymbol$default$3$(this);
               }

               public long newClassSymbol$default$4() {
                  return Internals.SymbolTableInternal.newClassSymbol$default$4$(this);
               }

               public Symbols.FreeTermSymbol newFreeTerm(final String name, final Function0 value, final long flags, final String origin) {
                  return Internals.SymbolTableInternal.newFreeTerm$(this, name, value, flags, origin);
               }

               public long newFreeTerm$default$3() {
                  return Internals.SymbolTableInternal.newFreeTerm$default$3$(this);
               }

               public String newFreeTerm$default$4() {
                  return Internals.SymbolTableInternal.newFreeTerm$default$4$(this);
               }

               public Symbols.FreeTypeSymbol newFreeType(final String name, final long flags, final String origin) {
                  return Internals.SymbolTableInternal.newFreeType$(this, name, flags, origin);
               }

               public long newFreeType$default$2() {
                  return Internals.SymbolTableInternal.newFreeType$default$2$(this);
               }

               public String newFreeType$default$3() {
                  return Internals.SymbolTableInternal.newFreeType$default$3$(this);
               }

               public boolean isErroneous(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.isErroneous$(this, symbol);
               }

               public boolean isSkolem(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.isSkolem$(this, symbol);
               }

               public Symbols.Symbol deSkolemize(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.deSkolemize$(this, symbol);
               }

               public Symbols.Symbol initialize(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.initialize$(this, symbol);
               }

               public Symbols.Symbol fullyInitialize(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.fullyInitialize$(this, (Symbols.Symbol)symbol);
               }

               public Types.Type fullyInitialize(final Types.Type tp) {
                  return Internals.SymbolTableInternal.fullyInitialize$(this, (Types.Type)tp);
               }

               public Scopes.Scope fullyInitialize(final Scopes.Scope scope) {
                  return Internals.SymbolTableInternal.fullyInitialize$(this, (Scopes.Scope)scope);
               }

               public long flags(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.flags$(this, symbol);
               }

               public Attachments attachments(final Symbols.Symbol symbol) {
                  return Internals.SymbolTableInternal.attachments$(this, (Symbols.Symbol)symbol);
               }

               public Symbols.Symbol updateAttachment(final Symbols.Symbol symbol, final Object attachment, final ClassTag evidence$3) {
                  return Internals.SymbolTableInternal.updateAttachment$(this, (Symbols.Symbol)symbol, attachment, evidence$3);
               }

               public Symbols.Symbol removeAttachment(final Symbols.Symbol symbol, final ClassTag evidence$4) {
                  return Internals.SymbolTableInternal.removeAttachment$(this, (Symbols.Symbol)symbol, evidence$4);
               }

               public Symbols.Symbol setOwner(final Symbols.Symbol symbol, final Symbols.Symbol newowner) {
                  return Internals.SymbolTableInternal.setOwner$(this, symbol, newowner);
               }

               public Symbols.Symbol setInfo(final Symbols.Symbol symbol, final Types.Type tpe) {
                  return Internals.SymbolTableInternal.setInfo$(this, symbol, tpe);
               }

               public Symbols.Symbol setAnnotations(final Symbols.Symbol symbol, final Seq annots) {
                  return Internals.SymbolTableInternal.setAnnotations$(this, symbol, annots);
               }

               public Symbols.Symbol setName(final Symbols.Symbol symbol, final Names.Name name) {
                  return Internals.SymbolTableInternal.setName$(this, symbol, name);
               }

               public Symbols.Symbol setPrivateWithin(final Symbols.Symbol symbol, final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.setPrivateWithin$(this, symbol, sym);
               }

               public Symbols.Symbol setFlag(final Symbols.Symbol symbol, final long flags) {
                  return Internals.SymbolTableInternal.setFlag$(this, symbol, flags);
               }

               public Symbols.Symbol resetFlag(final Symbols.Symbol symbol, final long flags) {
                  return Internals.SymbolTableInternal.resetFlag$(this, symbol, flags);
               }

               public Types.Type thisType(final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.thisType$(this, sym);
               }

               public Types.Type singleType(final Types.Type pre, final Symbols.Symbol sym) {
                  return Internals.SymbolTableInternal.singleType$(this, pre, sym);
               }

               public Types.Type superType(final Types.Type thistpe, final Types.Type supertpe) {
                  return Internals.SymbolTableInternal.superType$(this, thistpe, supertpe);
               }

               public Types.ConstantType constantType(final Constants.Constant value) {
                  return Internals.SymbolTableInternal.constantType$(this, value);
               }

               public Types.Type typeRef(final Types.Type pre, final Symbols.Symbol sym, final List args) {
                  return Internals.SymbolTableInternal.typeRef$(this, pre, sym, args);
               }

               public Types.RefinedType refinedType(final List parents, final Scopes.Scope decls) {
                  return Internals.SymbolTableInternal.refinedType$(this, parents, (Scopes.Scope)decls);
               }

               public Types.RefinedType refinedType(final List parents, final Scopes.Scope decls, final Symbols.Symbol clazz) {
                  return Internals.SymbolTableInternal.refinedType$(this, parents, (Scopes.Scope)decls, (Symbols.Symbol)clazz);
               }

               public Types.Type refinedType(final List parents, final Symbols.Symbol owner) {
                  return Internals.SymbolTableInternal.refinedType$(this, parents, (Symbols.Symbol)owner);
               }

               public Types.Type refinedType(final List parents, final Symbols.Symbol owner, final Scopes.Scope decls) {
                  return Internals.SymbolTableInternal.refinedType$(this, parents, (Symbols.Symbol)owner, (Scopes.Scope)decls);
               }

               public Types.Type refinedType(final List parents, final Symbols.Symbol owner, final Scopes.Scope decls, final Position pos) {
                  return Internals.SymbolTableInternal.refinedType$(this, parents, owner, decls, pos);
               }

               public Types.Type intersectionType(final List tps) {
                  return Internals.SymbolTableInternal.intersectionType$(this, tps);
               }

               public Types.Type intersectionType(final List tps, final Symbols.Symbol owner) {
                  return Internals.SymbolTableInternal.intersectionType$(this, tps, owner);
               }

               public Types.ClassInfoType classInfoType(final List parents, final Scopes.Scope decls, final Symbols.Symbol typeSymbol) {
                  return Internals.SymbolTableInternal.classInfoType$(this, parents, decls, typeSymbol);
               }

               public Types.MethodType methodType(final List params, final Types.Type resultType) {
                  return Internals.SymbolTableInternal.methodType$(this, params, resultType);
               }

               public Types.NullaryMethodType nullaryMethodType(final Types.Type resultType) {
                  return Internals.SymbolTableInternal.nullaryMethodType$(this, resultType);
               }

               public Types.PolyType polyType(final List typeParams, final Types.Type resultType) {
                  return Internals.SymbolTableInternal.polyType$(this, typeParams, resultType);
               }

               public Types.ExistentialType existentialType(final List quantified, final Types.Type underlying) {
                  return Internals.SymbolTableInternal.existentialType$(this, quantified, underlying);
               }

               public Types.Type existentialAbstraction(final List tparams, final Types.Type tpe0) {
                  return Internals.SymbolTableInternal.existentialAbstraction$(this, tparams, tpe0);
               }

               public Types.AnnotatedType annotatedType(final List annotations, final Types.Type underlying) {
                  return Internals.SymbolTableInternal.annotatedType$(this, annotations, underlying);
               }

               public Types.TypeBounds typeBounds(final Types.Type lo, final Types.Type hi) {
                  return Internals.SymbolTableInternal.typeBounds$(this, lo, hi);
               }

               public Types.BoundedWildcardType boundedWildcardType(final Types.TypeBounds bounds) {
                  return Internals.SymbolTableInternal.boundedWildcardType$(this, bounds);
               }

               public Option subpatterns(final Trees.Tree tree) {
                  return Internals.SymbolTableInternal.subpatterns$(this, tree);
               }

               public scala.reflect.api.Trees.DefDefApi markForAsyncTransform(final scala.reflect.api.Symbols.SymbolApi owner, final scala.reflect.api.Trees.DefDefApi method, final scala.reflect.api.Symbols.SymbolApi awaitSymbol, final Map config) {
                  return scala.reflect.api.Internals.InternalApi.markForAsyncTransform$(this, owner, method, awaitSymbol, config);
               }

               private scala.reflect.api.Internals.ReificationSupportApi reificationSupport$lzycompute() {
                  synchronized(this){}

                  try {
                     if ((byte)(this.bitmap$0 & 1) == 0) {
                        this.reificationSupport = Internals.SymbolTableInternal.reificationSupport$(this);
                        this.bitmap$0 = (byte)(this.bitmap$0 | 1);
                     }
                  } catch (Throwable var2) {
                     throw var2;
                  }

                  return this.reificationSupport;
               }

               public scala.reflect.api.Internals.ReificationSupportApi reificationSupport() {
                  return (byte)(this.bitmap$0 & 1) == 0 ? this.reificationSupport$lzycompute() : this.reificationSupport;
               }

               private Universe.TreeGen gen$lzycompute() {
                  synchronized(this){}

                  try {
                     if ((byte)(this.bitmap$0 & 2) == 0) {
                        this.gen = Internals.SymbolTableInternal.gen$(this);
                        this.bitmap$0 = (byte)(this.bitmap$0 | 2);
                     }
                  } catch (Throwable var2) {
                     throw var2;
                  }

                  return this.gen;
               }

               public Universe.TreeGen gen() {
                  return (byte)(this.bitmap$0 & 2) == 0 ? this.gen$lzycompute() : this.gen;
               }

               private Universe.MacroInternalApi.MacroDecoratorApi decorators$lzycompute() {
                  synchronized(this){}

                  try {
                     if ((byte)(this.bitmap$0 & 4) == 0) {
                        this.decorators = new Universe.MacroInternalApi.MacroDecoratorApi() {
                           // $FF: synthetic field
                           private final SymbolTableInternal $outer;

                           public scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi TypeDecoratorApi(final scala.reflect.api.Types.TypeApi tp) {
                              return scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi$(this, tp);
                           }

                           public Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi scopeDecorator(final Scopes.Scope scope) {
                              return new Universe.MacroInternalApi.MacroDecoratorApi.MacroScopeDecoratorApi(scope);
                           }

                           public Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi treeDecorator(final Trees.Tree tree) {
                              return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTreeDecoratorApi(tree);
                           }

                           public Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi typeTreeDecorator(final Trees.TypeTree tt) {
                              return new Universe.MacroInternalApi.MacroDecoratorApi.MacroTypeTreeDecoratorApi(tt);
                           }

                           public Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi symbolDecorator(final Symbols.Symbol symbol) {
                              return new Universe.MacroInternalApi.MacroDecoratorApi.MacroSymbolDecoratorApi(symbol);
                           }

                           public scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi typeDecorator(final Types.Type tp) {
                              return new scala.reflect.api.Internals.InternalApi.DecoratorApi.TypeDecoratorApi(tp);
                           }

                           // $FF: synthetic method
                           public Universe.MacroInternalApi scala$reflect$macros$Universe$MacroInternalApi$MacroDecoratorApi$$$outer() {
                              return this.$outer;
                           }

                           // $FF: synthetic method
                           public scala.reflect.api.Internals.InternalApi scala$reflect$api$Internals$InternalApi$DecoratorApi$$$outer() {
                              return this.$outer;
                           }

                           public {
                              if (<VAR_NAMELESS_ENCLOSURE> == null) {
                                 throw null;
                              } else {
                                 this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                              }
                           }
                        };
                        this.bitmap$0 = (byte)(this.bitmap$0 | 4);
                     }
                  } catch (Throwable var2) {
                     throw var2;
                  }

                  return this.decorators;
               }

               public Universe.MacroInternalApi.MacroDecoratorApi decorators() {
                  return (byte)(this.bitmap$0 & 4) == 0 ? this.decorators$lzycompute() : this.decorators;
               }

               public Manifest typeTagToManifest(final Object mirror0, final TypeTags.TypeTag tag, final ClassTag evidence$1) {
                  JavaMirrors.JavaMirror mirror = (JavaMirrors.JavaMirror)mirror0;
                  Class runtimeClass = mirror.runtimeClass((Types.Type)tag.in(mirror).tpe());
                  Manifest var10000 = scala.reflect.Manifest..MODULE$;
                  return new ManifestFactory.ClassTypeManifest(scala.None..MODULE$, runtimeClass, scala.collection.immutable.Nil..MODULE$);
               }

               public TypeTags.TypeTag manifestToTypeTag(final Object mirror0, final Manifest manifest) {
                  return this.$outer.TypeTag().apply((JavaMirrors.JavaMirror)mirror0, new TypeCreator(manifest) {
                     private final Manifest manifest$1;

                     public scala.reflect.api.Types.TypeApi apply(final Mirror mirror) {
                        scala.reflect.api.Universe var2 = mirror.universe();
                        if (!(var2 instanceof JavaUniverse)) {
                           return var2.internal().manifestToTypeTag(mirror, this.manifest$1).in(mirror).tpe();
                        } else {
                           JavaUniverse var3 = (JavaUniverse)var2;
                           JavaMirrors.JavaMirror jm = (JavaMirrors.JavaMirror)mirror;
                           Symbols.ClassSymbol sym = jm.classSymbol(this.manifest$1.runtimeClass());
                           if (this.manifest$1.typeArguments().isEmpty()) {
                              return sym.toType();
                           } else {
                              List var10000 = this.manifest$1.typeArguments();
                              if (var10000 == null) {
                                 throw null;
                              } else {
                                 List map_this = var10000;
                                 Object var29;
                                 if (map_this == scala.collection.immutable.Nil..MODULE$) {
                                    var29 = scala.collection.immutable.Nil..MODULE$;
                                 } else {
                                    Manifest var16 = (Manifest)map_this.head();
                                    scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$apply$1(var3, jm, var16), scala.collection.immutable.Nil..MODULE$);
                                    scala.collection.immutable..colon.colon map_t = map_h;

                                    for(List map_rest = (List)map_this.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                                       var16 = (Manifest)map_rest.head();
                                       scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$apply$1(var3, jm, var16), scala.collection.immutable.Nil..MODULE$);
                                       map_t.next_$eq(map_nx);
                                       map_t = map_nx;
                                    }

                                    Statics.releaseFence();
                                    var29 = map_h;
                                 }

                                 Object var18 = null;
                                 Object var19 = null;
                                 Object var20 = null;
                                 Object var21 = null;
                                 Object var22 = null;
                                 List tags = (List)var29;
                                 Types.Type var10001 = sym.toTypeConstructor();
                                 Object var10002;
                                 if (tags == scala.collection.immutable.Nil..MODULE$) {
                                    var10002 = scala.collection.immutable.Nil..MODULE$;
                                 } else {
                                    TypeTags.TypeTag var17 = (TypeTags.TypeTag)tags.head();
                                    scala.collection.immutable..colon.colon map_h = new scala.collection.immutable..colon.colon($anonfun$apply$2(jm, var17), scala.collection.immutable.Nil..MODULE$);
                                    scala.collection.immutable..colon.colon map_t = map_h;

                                    for(List map_rest = (List)tags.tail(); map_rest != scala.collection.immutable.Nil..MODULE$; map_rest = (List)map_rest.tail()) {
                                       var17 = (TypeTags.TypeTag)map_rest.head();
                                       scala.collection.immutable..colon.colon map_nx = new scala.collection.immutable..colon.colon($anonfun$apply$2(jm, var17), scala.collection.immutable.Nil..MODULE$);
                                       map_t.next_$eq(map_nx);
                                       map_t = map_nx;
                                    }

                                    Statics.releaseFence();
                                    var10002 = map_h;
                                 }

                                 Object var23 = null;
                                 Object var24 = null;
                                 Object var25 = null;
                                 Object var26 = null;
                                 return var3.appliedType(var10001, (List)var10002);
                              }
                           }
                        }
                     }

                     // $FF: synthetic method
                     public static final TypeTags.TypeTag $anonfun$apply$1(final JavaUniverse x2$1, final JavaMirrors.JavaMirror jm$1, final Manifest targ) {
                        return x2$1.internal().manifestToTypeTag(jm$1, targ);
                     }

                     // $FF: synthetic method
                     public static final Types.Type $anonfun$apply$2(final JavaMirrors.JavaMirror jm$1, final TypeTags.TypeTag x$1) {
                        return (Types.Type)x$1.in(jm$1).tpe();
                     }

                     public {
                        this.manifest$1 = manifest$1;
                     }
                  });
               }

               // $FF: synthetic method
               public Internals scala$reflect$internal$Internals$SymbolTableInternal$$$outer() {
                  return this.$outer;
               }

               // $FF: synthetic method
               public Universe scala$reflect$macros$Universe$MacroInternalApi$$$outer() {
                  return this.$outer;
               }

               // $FF: synthetic method
               public scala.reflect.api.Internals scala$reflect$api$Internals$InternalApi$$$outer() {
                  return this.$outer;
               }

               public {
                  if (JavaUniverse.this == null) {
                     throw null;
                  } else {
                     this.$outer = JavaUniverse.this;
                  }
               }
            };
            this.bitmap$0 |= 2;
         }
      } catch (Throwable var2) {
         throw var2;
      }

      return this.internal;
   }

   public Universe.MacroInternalApi internal() {
      return (this.bitmap$0 & 2) == 0 ? this.internal$lzycompute() : this.internal;
   }

   public void init() {
      this.definitions().init();
      this.force();
   }

   private final void treeInfo$lzycompute$1() {
      synchronized(this){}

      try {
         if (this.treeInfo$module == null) {
            this.treeInfo$module = new treeInfo$();
         }
      } catch (Throwable var2) {
         throw var2;
      }

   }

   public JavaUniverse() {
      ReflectSetup.$init$(this);
      scala.reflect.api.JavaUniverse.$init$(this);
      JavaMirrors.$init$(this);
      SynchronizedTypes.$init$(this);
      this.statistics = new scala.reflect.internal.SymbolTable.ReflectStats() {
         private Statistics.View scopeCountView;
         private Statistics.Timer scopePopulationTime;
         private Statistics.View symbolsCount;
         private Statistics.Counter typeSymbolCount;
         private Statistics.Counter classSymbolCount;
         private Statistics.View treeNodeCount;
         private Statistics.Counter retainedCount;
         private Statistics.QuantMap retainedByType;
         private Statistics.Timer classReadNanos;
         private Statistics.View uniqueTypesView;
         private Statistics.Counter rawTypeCount;
         private Statistics.Counter subtypeCount;
         private Statistics.Counter sametypeCount;
         private Statistics.Counter lubCount;
         private Statistics.Counter nestedLubCount;
         private Statistics.Counter findMemberCount;
         private Statistics.Counter findMembersCount;
         private Statistics.SubCounter noMemberCount;
         private Statistics.SubCounter multMemberCount;
         private Statistics.Timer typerNanos;
         private Statistics.StackableTimer lubNanos;
         private Statistics.StackableTimer subtypeNanos;
         private Statistics.StackableTimer findMemberNanos;
         private Statistics.StackableTimer findMembersNanos;
         private Statistics.StackableTimer asSeenFromNanos;
         private Statistics.StackableTimer baseTypeSeqNanos;
         private Statistics.StackableTimer baseClassesNanos;
         private Statistics.SubCounter compoundBaseTypeSeqCount;
         private Statistics.SubCounter typerefBaseTypeSeqCount;
         private Statistics.SubCounter singletonBaseTypeSeqCount;
         private Statistics.TimerStack typeOpsStack;
         private Statistics.Counter baseTypeSeqCount;
         private Statistics.Counter baseTypeSeqLenTotal;

         public Statistics.View scopeCountView() {
            return this.scopeCountView;
         }

         public Statistics.Timer scopePopulationTime() {
            return this.scopePopulationTime;
         }

         public void scala$reflect$internal$ScopeStats$_setter_$scopeCountView_$eq(final Statistics.View x$1) {
            this.scopeCountView = x$1;
         }

         public void scala$reflect$internal$ScopeStats$_setter_$scopePopulationTime_$eq(final Statistics.Timer x$1) {
            this.scopePopulationTime = x$1;
         }

         public Statistics.View symbolsCount() {
            return this.symbolsCount;
         }

         public Statistics.Counter typeSymbolCount() {
            return this.typeSymbolCount;
         }

         public Statistics.Counter classSymbolCount() {
            return this.classSymbolCount;
         }

         public void scala$reflect$internal$SymbolsStats$_setter_$symbolsCount_$eq(final Statistics.View x$1) {
            this.symbolsCount = x$1;
         }

         public void scala$reflect$internal$SymbolsStats$_setter_$typeSymbolCount_$eq(final Statistics.Counter x$1) {
            this.typeSymbolCount = x$1;
         }

         public void scala$reflect$internal$SymbolsStats$_setter_$classSymbolCount_$eq(final Statistics.Counter x$1) {
            this.classSymbolCount = x$1;
         }

         public Statistics.View treeNodeCount() {
            return this.treeNodeCount;
         }

         public Statistics.Counter retainedCount() {
            return this.retainedCount;
         }

         public Statistics.QuantMap retainedByType() {
            return this.retainedByType;
         }

         public void scala$reflect$internal$TreesStats$_setter_$treeNodeCount_$eq(final Statistics.View x$1) {
            this.treeNodeCount = x$1;
         }

         public void scala$reflect$internal$TreesStats$_setter_$retainedCount_$eq(final Statistics.Counter x$1) {
            this.retainedCount = x$1;
         }

         public void scala$reflect$internal$TreesStats$_setter_$retainedByType_$eq(final Statistics.QuantMap x$1) {
            this.retainedByType = x$1;
         }

         public Statistics.Timer classReadNanos() {
            return this.classReadNanos;
         }

         public void scala$reflect$internal$SymbolTableStats$_setter_$classReadNanos_$eq(final Statistics.Timer x$1) {
            this.classReadNanos = x$1;
         }

         public Statistics.View uniqueTypesView() {
            return this.uniqueTypesView;
         }

         public Statistics.Counter rawTypeCount() {
            return this.rawTypeCount;
         }

         public Statistics.Counter subtypeCount() {
            return this.subtypeCount;
         }

         public Statistics.Counter sametypeCount() {
            return this.sametypeCount;
         }

         public Statistics.Counter lubCount() {
            return this.lubCount;
         }

         public Statistics.Counter nestedLubCount() {
            return this.nestedLubCount;
         }

         public Statistics.Counter findMemberCount() {
            return this.findMemberCount;
         }

         public Statistics.Counter findMembersCount() {
            return this.findMembersCount;
         }

         public Statistics.SubCounter noMemberCount() {
            return this.noMemberCount;
         }

         public Statistics.SubCounter multMemberCount() {
            return this.multMemberCount;
         }

         public Statistics.Timer typerNanos() {
            return this.typerNanos;
         }

         public Statistics.StackableTimer lubNanos() {
            return this.lubNanos;
         }

         public Statistics.StackableTimer subtypeNanos() {
            return this.subtypeNanos;
         }

         public Statistics.StackableTimer findMemberNanos() {
            return this.findMemberNanos;
         }

         public Statistics.StackableTimer findMembersNanos() {
            return this.findMembersNanos;
         }

         public Statistics.StackableTimer asSeenFromNanos() {
            return this.asSeenFromNanos;
         }

         public Statistics.StackableTimer baseTypeSeqNanos() {
            return this.baseTypeSeqNanos;
         }

         public Statistics.StackableTimer baseClassesNanos() {
            return this.baseClassesNanos;
         }

         public Statistics.SubCounter compoundBaseTypeSeqCount() {
            return this.compoundBaseTypeSeqCount;
         }

         public Statistics.SubCounter typerefBaseTypeSeqCount() {
            return this.typerefBaseTypeSeqCount;
         }

         public Statistics.SubCounter singletonBaseTypeSeqCount() {
            return this.singletonBaseTypeSeqCount;
         }

         public Statistics.TimerStack typeOpsStack() {
            return this.typeOpsStack;
         }

         public void scala$reflect$internal$TypesStats$_setter_$uniqueTypesView_$eq(final Statistics.View x$1) {
            this.uniqueTypesView = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$rawTypeCount_$eq(final Statistics.Counter x$1) {
            this.rawTypeCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$subtypeCount_$eq(final Statistics.Counter x$1) {
            this.subtypeCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$sametypeCount_$eq(final Statistics.Counter x$1) {
            this.sametypeCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$lubCount_$eq(final Statistics.Counter x$1) {
            this.lubCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$nestedLubCount_$eq(final Statistics.Counter x$1) {
            this.nestedLubCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$findMemberCount_$eq(final Statistics.Counter x$1) {
            this.findMemberCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$findMembersCount_$eq(final Statistics.Counter x$1) {
            this.findMembersCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$noMemberCount_$eq(final Statistics.SubCounter x$1) {
            this.noMemberCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$multMemberCount_$eq(final Statistics.SubCounter x$1) {
            this.multMemberCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$typerNanos_$eq(final Statistics.Timer x$1) {
            this.typerNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$lubNanos_$eq(final Statistics.StackableTimer x$1) {
            this.lubNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$subtypeNanos_$eq(final Statistics.StackableTimer x$1) {
            this.subtypeNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$findMemberNanos_$eq(final Statistics.StackableTimer x$1) {
            this.findMemberNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$findMembersNanos_$eq(final Statistics.StackableTimer x$1) {
            this.findMembersNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$asSeenFromNanos_$eq(final Statistics.StackableTimer x$1) {
            this.asSeenFromNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$baseTypeSeqNanos_$eq(final Statistics.StackableTimer x$1) {
            this.baseTypeSeqNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$baseClassesNanos_$eq(final Statistics.StackableTimer x$1) {
            this.baseClassesNanos = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$compoundBaseTypeSeqCount_$eq(final Statistics.SubCounter x$1) {
            this.compoundBaseTypeSeqCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$typerefBaseTypeSeqCount_$eq(final Statistics.SubCounter x$1) {
            this.typerefBaseTypeSeqCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$singletonBaseTypeSeqCount_$eq(final Statistics.SubCounter x$1) {
            this.singletonBaseTypeSeqCount = x$1;
         }

         public void scala$reflect$internal$TypesStats$_setter_$typeOpsStack_$eq(final Statistics.TimerStack x$1) {
            this.typeOpsStack = x$1;
         }

         public Statistics.Counter baseTypeSeqCount() {
            return this.baseTypeSeqCount;
         }

         public Statistics.Counter baseTypeSeqLenTotal() {
            return this.baseTypeSeqLenTotal;
         }

         public void scala$reflect$internal$BaseTypeSeqsStats$_setter_$baseTypeSeqCount_$eq(final Statistics.Counter x$1) {
            this.baseTypeSeqCount = x$1;
         }

         public void scala$reflect$internal$BaseTypeSeqsStats$_setter_$baseTypeSeqLenTotal_$eq(final Statistics.Counter x$1) {
            this.baseTypeSeqLenTotal = x$1;
         }

         public {
            BaseTypeSeqsStats.$init$(this);
            TypesStats.$init$(this);
            SymbolTableStats.$init$(this);
            TreesStats.$init$(this);
            SymbolsStats.$init$(this);
            ScopeStats.$init$(this);
            Statics.releaseFence();
         }
      };
      this.isLogging = System.getProperty("scala.debug.reflect") != null;
      this.TreeCopierTag = scala.reflect.ClassTag..MODULE$.apply(Trees.InternalTreeCopierOps.class);
      this.init();
      Statics.releaseFence();
   }

   public class PerRunReporting extends Reporting.PerRunReportingBase {
      public void deprecationWarning(final Position pos, final String msg, final String since, final String site, final String origin, final List actions) {
         Reporter qual$1 = this.scala$reflect$runtime$JavaUniverse$PerRunReporting$$$outer().reporter();
         if (qual$1 == null) {
            throw null;
         } else {
            List x$3 = scala.collection.immutable.Nil..MODULE$;
            qual$1.warning(pos, msg, x$3);
         }
      }

      // $FF: synthetic method
      public JavaUniverse scala$reflect$runtime$JavaUniverse$PerRunReporting$$$outer() {
         return (JavaUniverse)this.$outer;
      }
   }

   public class treeInfo$ extends TreeInfo {
      private final JavaUniverse global;

      public JavaUniverse global() {
         return this.global;
      }

      public treeInfo$() {
         this.global = JavaUniverse.this;
      }
   }
}
