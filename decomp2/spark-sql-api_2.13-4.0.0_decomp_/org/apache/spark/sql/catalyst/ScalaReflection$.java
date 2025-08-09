package org.apache.spark.sql.catalyst;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Constructor;
import java.util.Map;
import javax.lang.model.SourceVersion;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.spark.internal.LogEntry;
import org.apache.spark.internal.Logging;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoder;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders;
import org.apache.spark.sql.catalyst.encoders.AgnosticEncoders$;
import org.apache.spark.sql.catalyst.encoders.OuterScopes$;
import org.apache.spark.sql.errors.ExecutionErrors$;
import org.apache.spark.sql.types.Metadata$;
import org.apache.spark.sql.types.SQLUserDefinedType;
import org.apache.spark.sql.types.UDTRegistration$;
import org.apache.spark.sql.types.UserDefinedType;
import org.slf4j.Logger;
import scala.Function0;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.StringContext;
import scala.Tuple2;
import scala.Tuple3;
import scala.collection.SeqFactory;
import scala.collection.SeqOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.reflect.api.Annotations;
import scala.reflect.api.ImplicitTags;
import scala.reflect.api.JavaUniverse;
import scala.reflect.api.Mirror;
import scala.reflect.api.Mirrors;
import scala.reflect.api.Names;
import scala.reflect.api.StandardDefinitions;
import scala.reflect.api.Symbols;
import scala.reflect.api.TypeCreator;
import scala.reflect.api.TypeTags;
import scala.reflect.api.Types;
import scala.reflect.api.Universe;
import scala.reflect.runtime.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public final class ScalaReflection$ implements ScalaReflection {
   public static final ScalaReflection$ MODULE$ = new ScalaReflection$();
   private static final JavaUniverse universe;
   private static transient Logger org$apache$spark$internal$Logging$$log_;

   static {
      Logging.$init$(MODULE$);
      ScalaReflection.$init$(MODULE$);
      universe = .MODULE$.universe();
   }

   public Object cleanUpReflectionObjects(final Function0 func) {
      return ScalaReflection.cleanUpReflectionObjects$(this, func);
   }

   public Types.TypeApi localTypeOf(final TypeTags.TypeTag evidence$4) {
      return ScalaReflection.localTypeOf$(this, evidence$4);
   }

   public Seq getConstructorParameters(final Types.TypeApi tpe) {
      return ScalaReflection.getConstructorParameters$(this, tpe);
   }

   public Seq constructParams(final Types.TypeApi tpe) {
      return ScalaReflection.constructParams$(this, tpe);
   }

   public String logName() {
      return Logging.logName$(this);
   }

   public Logger log() {
      return Logging.log$(this);
   }

   public Logging.LogStringContext LogStringContext(final StringContext sc) {
      return Logging.LogStringContext$(this, sc);
   }

   public void withLogContext(final Map context, final Function0 body) {
      Logging.withLogContext$(this, context, body);
   }

   public void logInfo(final Function0 msg) {
      Logging.logInfo$(this, msg);
   }

   public void logInfo(final LogEntry entry) {
      Logging.logInfo$(this, entry);
   }

   public void logInfo(final LogEntry entry, final Throwable throwable) {
      Logging.logInfo$(this, entry, throwable);
   }

   public void logDebug(final Function0 msg) {
      Logging.logDebug$(this, msg);
   }

   public void logDebug(final LogEntry entry) {
      Logging.logDebug$(this, entry);
   }

   public void logDebug(final LogEntry entry, final Throwable throwable) {
      Logging.logDebug$(this, entry, throwable);
   }

   public void logTrace(final Function0 msg) {
      Logging.logTrace$(this, msg);
   }

   public void logTrace(final LogEntry entry) {
      Logging.logTrace$(this, entry);
   }

   public void logTrace(final LogEntry entry, final Throwable throwable) {
      Logging.logTrace$(this, entry, throwable);
   }

   public void logWarning(final Function0 msg) {
      Logging.logWarning$(this, msg);
   }

   public void logWarning(final LogEntry entry) {
      Logging.logWarning$(this, entry);
   }

   public void logWarning(final LogEntry entry, final Throwable throwable) {
      Logging.logWarning$(this, entry, throwable);
   }

   public void logError(final Function0 msg) {
      Logging.logError$(this, msg);
   }

   public void logError(final LogEntry entry) {
      Logging.logError$(this, entry);
   }

   public void logError(final LogEntry entry, final Throwable throwable) {
      Logging.logError$(this, entry, throwable);
   }

   public void logInfo(final Function0 msg, final Throwable throwable) {
      Logging.logInfo$(this, msg, throwable);
   }

   public void logDebug(final Function0 msg, final Throwable throwable) {
      Logging.logDebug$(this, msg, throwable);
   }

   public void logTrace(final Function0 msg, final Throwable throwable) {
      Logging.logTrace$(this, msg, throwable);
   }

   public void logWarning(final Function0 msg, final Throwable throwable) {
      Logging.logWarning$(this, msg, throwable);
   }

   public void logError(final Function0 msg, final Throwable throwable) {
      Logging.logError$(this, msg, throwable);
   }

   public boolean isTraceEnabled() {
      return Logging.isTraceEnabled$(this);
   }

   public void initializeLogIfNecessary(final boolean isInterpreter) {
      Logging.initializeLogIfNecessary$(this, isInterpreter);
   }

   public boolean initializeLogIfNecessary(final boolean isInterpreter, final boolean silent) {
      return Logging.initializeLogIfNecessary$(this, isInterpreter, silent);
   }

   public boolean initializeLogIfNecessary$default$2() {
      return Logging.initializeLogIfNecessary$default$2$(this);
   }

   public void initializeForcefully(final boolean isInterpreter, final boolean silent) {
      Logging.initializeForcefully$(this, isInterpreter, silent);
   }

   public Logger org$apache$spark$internal$Logging$$log_() {
      return org$apache$spark$internal$Logging$$log_;
   }

   public void org$apache$spark$internal$Logging$$log__$eq(final Logger x$1) {
      org$apache$spark$internal$Logging$$log_ = x$1;
   }

   public JavaUniverse universe() {
      return universe;
   }

   public JavaUniverse.JavaMirror mirror() {
      return this.universe().runtimeMirror(Thread.currentThread().getContextClassLoader());
   }

   public boolean isSubtype(final Types.TypeApi tpe1, final Types.TypeApi tpe2) {
      synchronized(ScalaSubtypeLock$.MODULE$){}

      boolean var4;
      try {
         var4 = tpe1.$less$colon$less(tpe2);
      } catch (Throwable var6) {
         throw var6;
      }

      return var4;
   }

   private Types.TypeApi baseType(final Types.TypeApi tpe) {
      Types.TypeApi var3 = tpe.dealias();
      if (var3 != null) {
         Option var4 = ((ImplicitTags)this.universe()).AnnotatedTypeTag().unapply(var3);
         if (!var4.isEmpty() && var4.get() != null) {
            return ((Types.AnnotatedTypeApi)var3).underlying();
         }
      }

      return var3;
   }

   public Seq getConstructorParameterNames(final Class cls) {
      JavaUniverse.JavaMirror m = this.universe().runtimeMirror(cls.getClassLoader());
      Symbols.ClassSymbolApi classSymbol = ((Mirror)m).staticClass(cls.getName());
      Types.TypeApi t = this.selfType(classSymbol, this.selfType$default$2());
      return (Seq)this.constructParams(t).map((x$1) -> x$1.name().decodedName().toString());
   }

   private Types.TypeApi selfType(final Symbols.ClassSymbolApi clsSymbol, final int tries) {
      while(true) {
         boolean var5 = false;
         Failure var6 = null;
         Try var7 = scala.util.Try..MODULE$.apply(() -> clsSymbol.selfType());
         if (var7 instanceof Success var8) {
            Types.TypeApi x = (Types.TypeApi)var8.value();
            return x;
         }

         if (var7 instanceof Failure) {
            var5 = true;
            var6 = (Failure)var7;
            if (var6.exception() instanceof scala.reflect.internal.Symbols.CyclicReference && tries > 1) {
               --tries;
               clsSymbol = clsSymbol;
               continue;
            }
         }

         if (var5) {
            Throwable e = var6.exception();
            if (e instanceof RuntimeException) {
               RuntimeException var11 = (RuntimeException)e;
               if (var11.getMessage().contains("illegal cyclic reference") && tries > 1) {
                  --tries;
                  clsSymbol = clsSymbol;
                  continue;
               }
            }
         }

         if (var5) {
            Throwable e = var6.exception();
            throw e;
         }

         throw new MatchError(var7);
      }
   }

   private int selfType$default$2() {
      return 5;
   }

   private Types.TypeApi erasure(final Types.TypeApi tpe) {
      return this.isSubtype(tpe, this.localTypeOf(((TypeTags)this.universe()).TypeTag().AnyVal())) && !tpe.toString().startsWith("scala") ? tpe : tpe.erasure();
   }

   public String getClassNameFromType(final Types.TypeApi tpe) {
      return this.erasure(tpe).dealias().typeSymbol().asClass().fullName();
   }

   public Class getClassFromType(final Types.TypeApi tpe) {
      return (Class)this.mirror().runtimeClass(this.erasure(tpe).dealias().typeSymbol().asClass());
   }

   public ScalaReflection.Schema schemaFor(final TypeTags.TypeTag evidence$1) {
      return this.schemaFor(this.localTypeOf(evidence$1));
   }

   public ScalaReflection.Schema schemaFor(final Types.TypeApi tpe) {
      AgnosticEncoder enc = this.encoderFor(tpe, this.encoderFor$default$2());
      return new ScalaReflection.Schema(enc.dataType(), enc.nullable());
   }

   public Option findConstructor(final Class cls, final Seq paramTypes) {
      Option var4 = scala.Option..MODULE$.apply(ConstructorUtils.getMatchingAccessibleConstructor(cls, (Class[])paramTypes.toArray(scala.reflect.ClassTag..MODULE$.apply(Class.class))));
      if (var4 instanceof Some var5) {
         Constructor c = (Constructor)var5.value();
         return new Some((Function1)(x) -> c.newInstance(x.toArray(scala.reflect.ClassTag..MODULE$.AnyRef())));
      } else if (scala.None..MODULE$.equals(var4)) {
         Symbols.SymbolApi companion = ((Mirror)this.mirror()).staticClass(cls.getName()).companion();
         Mirrors.ModuleMirror moduleMirror = this.mirror().reflectModule(companion.asModule());
         List applyMethods = companion.asTerm().typeSignature().member((Names.NameApi)((Names)this.universe()).TermName().apply("apply")).asTerm().alternatives();
         return applyMethods.find((method) -> BoxesRunTime.boxToBoolean($anonfun$findConstructor$2(paramTypes, method))).map((applyMethodSymbol) -> {
            int expectedArgsCount = ((SeqOps)applyMethodSymbol.typeSignature().paramLists().head()).size();
            Mirrors.InstanceMirror instanceMirror = MODULE$.mirror().reflect(moduleMirror.instance(), scala.reflect.ClassTag..MODULE$.Any());
            Mirrors.MethodMirror method = instanceMirror.reflectMethod(applyMethodSymbol.asMethod());
            return (_args) -> {
               Seq args = _args.size() == expectedArgsCount ? _args : (Seq)_args.tail();
               return method.apply(args);
            };
         });
      } else {
         throw new MatchError(var4);
      }
   }

   public boolean definedByConstructorParams(final Types.TypeApi tpe) {
      return BoxesRunTime.unboxToBoolean(this.cleanUpReflectionObjects((JFunction0.mcZ.sp)() -> {
         Types.TypeApi var2 = tpe.dealias();
         ScalaReflection$ var10000 = MODULE$;
         ScalaReflection$ var10002 = MODULE$;
         JavaUniverse $u = MODULE$.universe();
         JavaUniverse.JavaMirror $m = MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());

         final class $typecreator1$1 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               Symbols.SymbolApi symdef$_$41 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectTerm($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "definedByConstructorParams"), (Names.NameApi)$u.TypeName().apply("_$4"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
               $u.internal().reificationSupport().setInfo(symdef$_$41, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
               return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$41, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Option"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$41, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)));
            }

            public $typecreator1$1() {
            }
         }

         if (var10000.isSubtype(var2, var10002.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$1())))) {
            return MODULE$.definedByConstructorParams((Types.TypeApi)var2.typeArgs().head());
         } else {
            var10000 = MODULE$;
            Types.TypeApi var10001 = tpe.dealias();
            var10002 = MODULE$;
            JavaUniverse $u = MODULE$.universe();
            JavaUniverse.JavaMirror $m = MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());
            boolean var11;

            final class $typecreator2$1 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("scala.Product").asType().toTypeConstructor();
               }

               public $typecreator2$1() {
               }
            }

            if (!var10000.isSubtype(var10001, var10002.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$1())))) {
               ScalaReflection$ var10 = MODULE$;
               var10001 = tpe.dealias();
               var10002 = MODULE$;
               JavaUniverse $u = MODULE$.universe();
               JavaUniverse.JavaMirror $m = MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());

               final class $typecreator3$1 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $m$untyped.staticClass("org.apache.spark.sql.catalyst.DefinedByConstructorParams").asType().toTypeConstructor();
                  }

                  public $typecreator3$1() {
                  }
               }

               if (!var10.isSubtype(var10001, var10002.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$1())))) {
                  var11 = false;
                  return var11;
               }
            }

            var11 = true;
            return var11;
         }
      }));
   }

   public String encodeFieldNameToIdentifier(final String fieldName) {
      return ((Names.NameApi)((Names)this.universe()).TermName().apply(fieldName)).encodedName().toString();
   }

   public AgnosticEncoder encoderFor(final TypeTags.TypeTag evidence$2) {
      return this.encoderFor(((TypeTags)this.universe()).typeTag(evidence$2).in((Mirror)this.mirror()).tpe(), this.encoderFor$default$2());
   }

   public AgnosticEncoder encoderForWithRowEncoderSupport(final TypeTags.TypeTag evidence$3) {
      return this.encoderFor(((TypeTags)this.universe()).typeTag(evidence$3).in((Mirror)this.mirror()).tpe(), true);
   }

   public AgnosticEncoder encoderFor(final Types.TypeApi tpe, final boolean isRowEncoderSupported) {
      return (AgnosticEncoder)this.cleanUpReflectionObjects(() -> {
         String clsName = MODULE$.getClassNameFromType(tpe);
         WalkedTypePath walkedTypePath = (new WalkedTypePath(WalkedTypePath$.MODULE$.apply$default$1())).recordRoot(clsName);
         return MODULE$.encoderFor(tpe, scala.Predef..MODULE$.Set().empty(), walkedTypePath, isRowEncoderSupported);
      });
   }

   private AgnosticEncoder encoderFor(final Types.TypeApi tpe, final Set seenTypeSet, final WalkedTypePath path, final boolean isRowEncoderSupported) {
      Types.TypeApi var9 = this.baseType(tpe);
      if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().NullTpe())) {
         return AgnosticEncoders.NullEncoder$.MODULE$;
      } else if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().BooleanTpe())) {
         return AgnosticEncoders.PrimitiveBooleanEncoder$.MODULE$;
      } else if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().ByteTpe())) {
         return AgnosticEncoders.PrimitiveByteEncoder$.MODULE$;
      } else if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().ShortTpe())) {
         return AgnosticEncoders.PrimitiveShortEncoder$.MODULE$;
      } else if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().IntTpe())) {
         return AgnosticEncoders.PrimitiveIntEncoder$.MODULE$;
      } else if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().LongTpe())) {
         return AgnosticEncoders.PrimitiveLongEncoder$.MODULE$;
      } else if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().FloatTpe())) {
         return AgnosticEncoders.PrimitiveFloatEncoder$.MODULE$;
      } else if (this.isSubtype(var9, ((StandardDefinitions)this.universe()).definitions().DoubleTpe())) {
         return AgnosticEncoders.PrimitiveDoubleEncoder$.MODULE$;
      } else {
         JavaUniverse $u = this.universe();
         JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

         final class $typecreator1$2 extends TypeCreator {
            public Types.TypeApi apply(final Mirror $m$untyped) {
               Universe $u = $m$untyped.universe();
               return $m$untyped.staticClass("java.lang.Boolean").asType().toTypeConstructor();
            }

            public $typecreator1$2() {
            }
         }

         if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator1$2())))) {
            return AgnosticEncoders.BoxedBooleanEncoder$.MODULE$;
         } else {
            JavaUniverse $u = this.universe();
            JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

            final class $typecreator2$2 extends TypeCreator {
               public Types.TypeApi apply(final Mirror $m$untyped) {
                  Universe $u = $m$untyped.universe();
                  return $m$untyped.staticClass("java.lang.Byte").asType().toTypeConstructor();
               }

               public $typecreator2$2() {
               }
            }

            if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator2$2())))) {
               return AgnosticEncoders.BoxedByteEncoder$.MODULE$;
            } else {
               JavaUniverse $u = this.universe();
               JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

               final class $typecreator3$2 extends TypeCreator {
                  public Types.TypeApi apply(final Mirror $m$untyped) {
                     Universe $u = $m$untyped.universe();
                     return $m$untyped.staticClass("java.lang.Short").asType().toTypeConstructor();
                  }

                  public $typecreator3$2() {
                  }
               }

               if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator3$2())))) {
                  return AgnosticEncoders.BoxedShortEncoder$.MODULE$;
               } else {
                  JavaUniverse $u = this.universe();
                  JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                  final class $typecreator4$1 extends TypeCreator {
                     public Types.TypeApi apply(final Mirror $m$untyped) {
                        Universe $u = $m$untyped.universe();
                        return $m$untyped.staticClass("java.lang.Integer").asType().toTypeConstructor();
                     }

                     public $typecreator4$1() {
                     }
                  }

                  if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator4$1())))) {
                     return AgnosticEncoders.BoxedIntEncoder$.MODULE$;
                  } else {
                     JavaUniverse $u = this.universe();
                     JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                     final class $typecreator5$1 extends TypeCreator {
                        public Types.TypeApi apply(final Mirror $m$untyped) {
                           Universe $u = $m$untyped.universe();
                           return $m$untyped.staticClass("java.lang.Long").asType().toTypeConstructor();
                        }

                        public $typecreator5$1() {
                        }
                     }

                     if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator5$1())))) {
                        return AgnosticEncoders.BoxedLongEncoder$.MODULE$;
                     } else {
                        JavaUniverse $u = this.universe();
                        JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                        final class $typecreator6$1 extends TypeCreator {
                           public Types.TypeApi apply(final Mirror $m$untyped) {
                              Universe $u = $m$untyped.universe();
                              return $m$untyped.staticClass("java.lang.Float").asType().toTypeConstructor();
                           }

                           public $typecreator6$1() {
                           }
                        }

                        if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator6$1())))) {
                           return AgnosticEncoders.BoxedFloatEncoder$.MODULE$;
                        } else {
                           JavaUniverse $u = this.universe();
                           JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                           final class $typecreator7$1 extends TypeCreator {
                              public Types.TypeApi apply(final Mirror $m$untyped) {
                                 Universe $u = $m$untyped.universe();
                                 return $m$untyped.staticClass("java.lang.Double").asType().toTypeConstructor();
                              }

                              public $typecreator7$1() {
                              }
                           }

                           if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator7$1())))) {
                              return AgnosticEncoders.BoxedDoubleEncoder$.MODULE$;
                           } else {
                              JavaUniverse $u = this.universe();
                              JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                              final class $typecreator8$1 extends TypeCreator {
                                 public Types.TypeApi apply(final Mirror $m$untyped) {
                                    Universe $u = $m$untyped.universe();
                                    return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($m$untyped.staticClass("scala.Byte").asType().toTypeConstructor(), scala.collection.immutable.Nil..MODULE$));
                                 }

                                 public $typecreator8$1() {
                                 }
                              }

                              if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator8$1())))) {
                                 return AgnosticEncoders.BinaryEncoder$.MODULE$;
                              } else {
                                 JavaUniverse $u = this.universe();
                                 JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                 final class $typecreator9$1 extends TypeCreator {
                                    public Types.TypeApi apply(final Mirror $m$untyped) {
                                       Universe $u = $m$untyped.universe();
                                       Symbols.SymbolApi symdef$_$91 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectOverloadedMethod($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "encoderFor", 2), (Names.NameApi)$u.TypeName().apply("_$9"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
                                       $u.internal().reificationSupport().setInfo(symdef$_$91, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
                                       return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$91, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("java.lang").asModule().moduleClass()), $m$untyped.staticClass("java.lang.Enum"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$91, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)));
                                    }

                                    public $typecreator9$1() {
                                    }
                                 }

                                 if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator9$1())))) {
                                    return new AgnosticEncoders.JavaEnumEncoder(scala.reflect.ClassTag..MODULE$.apply(this.getClassFromType(var9)));
                                 } else {
                                    JavaUniverse $u = this.universe();
                                    JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                    final class $typecreator10$1 extends TypeCreator {
                                       public Types.TypeApi apply(final Mirror $m$untyped) {
                                          Universe $u = $m$untyped.universe();
                                          return $u.internal().reificationSupport().TypeRef($m$untyped.staticClass("scala.Enumeration").asType().toTypeConstructor(), $u.internal().reificationSupport().selectType($m$untyped.staticClass("scala.Enumeration"), "Value"), scala.collection.immutable.Nil..MODULE$);
                                       }

                                       public $typecreator10$1() {
                                       }
                                    }

                                    if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator10$1())))) {
                                       Class parent = this.getClassFromType(((Types.TypeRefApi)var9).pre());
                                       return new AgnosticEncoders.ScalaEnumEncoder(parent, scala.reflect.ClassTag..MODULE$.apply(this.getClassFromType(var9)));
                                    } else {
                                       JavaUniverse $u = this.universe();
                                       JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                       final class $typecreator11$1 extends TypeCreator {
                                          public Types.TypeApi apply(final Mirror $m$untyped) {
                                             Universe $u = $m$untyped.universe();
                                             return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($m$untyped.staticPackage("scala").asModule().moduleClass().asType().toTypeConstructor(), $m$untyped.staticModule("scala.Predef")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.Predef").asModule().moduleClass(), "String"), scala.collection.immutable.Nil..MODULE$);
                                          }

                                          public $typecreator11$1() {
                                          }
                                       }

                                       if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator11$1())))) {
                                          return AgnosticEncoders.StringEncoder$.MODULE$;
                                       } else {
                                          JavaUniverse $u = this.universe();
                                          JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                          final class $typecreator12$1 extends TypeCreator {
                                             public Types.TypeApi apply(final Mirror $m$untyped) {
                                                Universe $u = $m$untyped.universe();
                                                return $m$untyped.staticClass("org.apache.spark.sql.types.Decimal").asType().toTypeConstructor();
                                             }

                                             public $typecreator12$1() {
                                             }
                                          }

                                          if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator12$1())))) {
                                             return AgnosticEncoders$.MODULE$.DEFAULT_SPARK_DECIMAL_ENCODER();
                                          } else {
                                             JavaUniverse $u = this.universe();
                                             JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                             final class $typecreator13$1 extends TypeCreator {
                                                public Types.TypeApi apply(final Mirror $m$untyped) {
                                                   Universe $u = $m$untyped.universe();
                                                   return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "BigDecimal"), scala.collection.immutable.Nil..MODULE$);
                                                }

                                                public $typecreator13$1() {
                                                }
                                             }

                                             if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator13$1())))) {
                                                return AgnosticEncoders$.MODULE$.DEFAULT_SCALA_DECIMAL_ENCODER();
                                             } else {
                                                JavaUniverse $u = this.universe();
                                                JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                final class $typecreator14$1 extends TypeCreator {
                                                   public Types.TypeApi apply(final Mirror $m$untyped) {
                                                      Universe $u = $m$untyped.universe();
                                                      return $m$untyped.staticClass("java.math.BigDecimal").asType().toTypeConstructor();
                                                   }

                                                   public $typecreator14$1() {
                                                   }
                                                }

                                                if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator14$1())))) {
                                                   return AgnosticEncoders$.MODULE$.DEFAULT_JAVA_DECIMAL_ENCODER();
                                                } else {
                                                   JavaUniverse $u = this.universe();
                                                   JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                   final class $typecreator15$1 extends TypeCreator {
                                                      public Types.TypeApi apply(final Mirror $m$untyped) {
                                                         Universe $u = $m$untyped.universe();
                                                         return $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().SingleType($u.internal().reificationSupport().thisPrefix($m$untyped.RootClass()), $m$untyped.staticPackage("scala")), $m$untyped.staticModule("scala.package")), $u.internal().reificationSupport().selectType($m$untyped.staticModule("scala.package").asModule().moduleClass(), "BigInt"), scala.collection.immutable.Nil..MODULE$);
                                                      }

                                                      public $typecreator15$1() {
                                                      }
                                                   }

                                                   if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator15$1())))) {
                                                      return AgnosticEncoders.ScalaBigIntEncoder$.MODULE$;
                                                   } else {
                                                      JavaUniverse $u = this.universe();
                                                      JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                      final class $typecreator16$1 extends TypeCreator {
                                                         public Types.TypeApi apply(final Mirror $m$untyped) {
                                                            Universe $u = $m$untyped.universe();
                                                            return $m$untyped.staticClass("java.math.BigInteger").asType().toTypeConstructor();
                                                         }

                                                         public $typecreator16$1() {
                                                         }
                                                      }

                                                      if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator16$1())))) {
                                                         return AgnosticEncoders.JavaBigIntEncoder$.MODULE$;
                                                      } else {
                                                         JavaUniverse $u = this.universe();
                                                         JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                         final class $typecreator17$1 extends TypeCreator {
                                                            public Types.TypeApi apply(final Mirror $m$untyped) {
                                                               Universe $u = $m$untyped.universe();
                                                               return $m$untyped.staticClass("org.apache.spark.unsafe.types.CalendarInterval").asType().toTypeConstructor();
                                                            }

                                                            public $typecreator17$1() {
                                                            }
                                                         }

                                                         if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator17$1())))) {
                                                            return AgnosticEncoders.CalendarIntervalEncoder$.MODULE$;
                                                         } else {
                                                            JavaUniverse $u = this.universe();
                                                            JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                            final class $typecreator18$1 extends TypeCreator {
                                                               public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                  Universe $u = $m$untyped.universe();
                                                                  return $m$untyped.staticClass("java.time.Duration").asType().toTypeConstructor();
                                                               }

                                                               public $typecreator18$1() {
                                                               }
                                                            }

                                                            if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator18$1())))) {
                                                               return AgnosticEncoders.DayTimeIntervalEncoder$.MODULE$;
                                                            } else {
                                                               JavaUniverse $u = this.universe();
                                                               JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                               final class $typecreator19$1 extends TypeCreator {
                                                                  public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                     Universe $u = $m$untyped.universe();
                                                                     return $m$untyped.staticClass("java.time.Period").asType().toTypeConstructor();
                                                                  }

                                                                  public $typecreator19$1() {
                                                                  }
                                                               }

                                                               if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator19$1())))) {
                                                                  return AgnosticEncoders.YearMonthIntervalEncoder$.MODULE$;
                                                               } else {
                                                                  JavaUniverse $u = this.universe();
                                                                  JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                  final class $typecreator20$1 extends TypeCreator {
                                                                     public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                        Universe $u = $m$untyped.universe();
                                                                        return $m$untyped.staticClass("java.sql.Date").asType().toTypeConstructor();
                                                                     }

                                                                     public $typecreator20$1() {
                                                                     }
                                                                  }

                                                                  if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator20$1())))) {
                                                                     return AgnosticEncoders$.MODULE$.STRICT_DATE_ENCODER();
                                                                  } else {
                                                                     JavaUniverse $u = this.universe();
                                                                     JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                     final class $typecreator21$1 extends TypeCreator {
                                                                        public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                           Universe $u = $m$untyped.universe();
                                                                           return $m$untyped.staticClass("java.time.LocalDate").asType().toTypeConstructor();
                                                                        }

                                                                        public $typecreator21$1() {
                                                                        }
                                                                     }

                                                                     if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator21$1())))) {
                                                                        return AgnosticEncoders$.MODULE$.STRICT_LOCAL_DATE_ENCODER();
                                                                     } else {
                                                                        JavaUniverse $u = this.universe();
                                                                        JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                        final class $typecreator22$1 extends TypeCreator {
                                                                           public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                              Universe $u = $m$untyped.universe();
                                                                              return $m$untyped.staticClass("java.sql.Timestamp").asType().toTypeConstructor();
                                                                           }

                                                                           public $typecreator22$1() {
                                                                           }
                                                                        }

                                                                        if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator22$1())))) {
                                                                           return AgnosticEncoders$.MODULE$.STRICT_TIMESTAMP_ENCODER();
                                                                        } else {
                                                                           JavaUniverse $u = this.universe();
                                                                           JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                           final class $typecreator23$1 extends TypeCreator {
                                                                              public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                 Universe $u = $m$untyped.universe();
                                                                                 return $m$untyped.staticClass("java.time.Instant").asType().toTypeConstructor();
                                                                              }

                                                                              public $typecreator23$1() {
                                                                              }
                                                                           }

                                                                           if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator23$1())))) {
                                                                              return AgnosticEncoders$.MODULE$.STRICT_INSTANT_ENCODER();
                                                                           } else {
                                                                              JavaUniverse $u = this.universe();
                                                                              JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                              final class $typecreator24$1 extends TypeCreator {
                                                                                 public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                    Universe $u = $m$untyped.universe();
                                                                                    return $m$untyped.staticClass("java.time.LocalDateTime").asType().toTypeConstructor();
                                                                                 }

                                                                                 public $typecreator24$1() {
                                                                                 }
                                                                              }

                                                                              if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator24$1())))) {
                                                                                 return AgnosticEncoders.LocalDateTimeEncoder$.MODULE$;
                                                                              } else {
                                                                                 JavaUniverse $u = this.universe();
                                                                                 JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                                 final class $typecreator25$1 extends TypeCreator {
                                                                                    public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                       Universe $u = $m$untyped.universe();
                                                                                       return $m$untyped.staticClass("org.apache.spark.unsafe.types.VariantVal").asType().toTypeConstructor();
                                                                                    }

                                                                                    public $typecreator25$1() {
                                                                                    }
                                                                                 }

                                                                                 if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator25$1())))) {
                                                                                    return AgnosticEncoders.VariantEncoder$.MODULE$;
                                                                                 } else {
                                                                                    JavaUniverse $u = this.universe();
                                                                                    JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                                    final class $typecreator26$1 extends TypeCreator {
                                                                                       public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                          Universe $u = $m$untyped.universe();
                                                                                          return $m$untyped.staticClass("org.apache.spark.sql.Row").asType().toTypeConstructor();
                                                                                       }

                                                                                       public $typecreator26$1() {
                                                                                       }
                                                                                    }

                                                                                    if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator26$1())))) {
                                                                                       return AgnosticEncoders.UnboundRowEncoder$.MODULE$;
                                                                                    } else if (var9.typeSymbol().annotations().exists((x$2) -> BoxesRunTime.boxToBoolean($anonfun$encoderFor$2(x$2)))) {
                                                                                       UserDefinedType udt = (UserDefinedType)((SQLUserDefinedType)this.getClassFromType(var9).getAnnotation(SQLUserDefinedType.class)).udt().getConstructor().newInstance();
                                                                                       Class udtClass = ((SQLUserDefinedType)udt.userClass().getAnnotation(SQLUserDefinedType.class)).udt();
                                                                                       return new AgnosticEncoders.UDTEncoder(udt, udtClass);
                                                                                    } else if (UDTRegistration$.MODULE$.exists(this.getClassNameFromType(var9))) {
                                                                                       UserDefinedType udt = (UserDefinedType)((Class)UDTRegistration$.MODULE$.getUDTFor(this.getClassNameFromType(var9)).get()).getConstructor().newInstance();
                                                                                       return new AgnosticEncoders.UDTEncoder(udt, udt.getClass());
                                                                                    } else {
                                                                                       JavaUniverse $u = this.universe();
                                                                                       JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                                       final class $typecreator28$1 extends TypeCreator {
                                                                                          public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                             Universe $u = $m$untyped.universe();
                                                                                             Symbols.SymbolApi symdef$_$101 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectOverloadedMethod($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "encoderFor", 2), (Names.NameApi)$u.TypeName().apply("_$10"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
                                                                                             $u.internal().reificationSupport().setInfo(symdef$_$101, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
                                                                                             return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$101, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Option"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$101, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)));
                                                                                          }

                                                                                          public $typecreator28$1() {
                                                                                          }
                                                                                       }

                                                                                       if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator28$1())))) {
                                                                                          if (var9 != null) {
                                                                                             Option var70 = ((ImplicitTags)this.universe()).TypeRefTag().unapply(var9);
                                                                                             if (!var70.isEmpty()) {
                                                                                                Types.TypeRefApi var71 = (Types.TypeRefApi)var70.get();
                                                                                                if (var71 != null) {
                                                                                                   Option var72 = ((Types)this.universe()).TypeRef().unapply(var71);
                                                                                                   if (!var72.isEmpty()) {
                                                                                                      List var73 = (List)((Tuple3)var72.get())._3();
                                                                                                      if (var73 != null) {
                                                                                                         SeqOps var74 = scala.package..MODULE$.Seq().unapplySeq(var73);
                                                                                                         if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var74) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var74)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var74), 1) == 0) {
                                                                                                            Types.TypeApi optType = (Types.TypeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var74), 0);
                                                                                                            AgnosticEncoder encoder = this.encoderFor(optType, seenTypeSet, path.recordOption(this.getClassNameFromType(optType)), isRowEncoderSupported);
                                                                                                            return new AgnosticEncoders.OptionEncoder(encoder);
                                                                                                         }
                                                                                                      }
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                          }

                                                                                          throw new MatchError(var9);
                                                                                       } else {
                                                                                          JavaUniverse $u = this.universe();
                                                                                          JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                                          final class $typecreator29$1 extends TypeCreator {
                                                                                             public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                                Universe $u = $m$untyped.universe();
                                                                                                Symbols.SymbolApi symdef$_$111 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectOverloadedMethod($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "encoderFor", 2), (Names.NameApi)$u.TypeName().apply("_$11"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
                                                                                                $u.internal().reificationSupport().setInfo(symdef$_$111, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
                                                                                                return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$111, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala").asModule().moduleClass()), $m$untyped.staticClass("scala.Array"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$111, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)));
                                                                                             }

                                                                                             public $typecreator29$1() {
                                                                                             }
                                                                                          }

                                                                                          if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator29$1())))) {
                                                                                             if (var9 != null) {
                                                                                                Option var81 = ((ImplicitTags)this.universe()).TypeRefTag().unapply(var9);
                                                                                                if (!var81.isEmpty()) {
                                                                                                   Types.TypeRefApi var82 = (Types.TypeRefApi)var81.get();
                                                                                                   if (var82 != null) {
                                                                                                      Option var83 = ((Types)this.universe()).TypeRef().unapply(var82);
                                                                                                      if (!var83.isEmpty()) {
                                                                                                         List var84 = (List)((Tuple3)var83.get())._3();
                                                                                                         if (var84 != null) {
                                                                                                            SeqOps var85 = scala.package..MODULE$.Seq().unapplySeq(var84);
                                                                                                            if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var85) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var85)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var85), 1) == 0) {
                                                                                                               Types.TypeApi elementType = (Types.TypeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var85), 0);
                                                                                                               AgnosticEncoder encoder = this.encoderFor(elementType, seenTypeSet, path.recordArray(this.getClassNameFromType(elementType)), isRowEncoderSupported);
                                                                                                               return new AgnosticEncoders.ArrayEncoder(encoder, encoder.nullable());
                                                                                                            }
                                                                                                         }
                                                                                                      }
                                                                                                   }
                                                                                                }
                                                                                             }

                                                                                             throw new MatchError(var9);
                                                                                          } else {
                                                                                             JavaUniverse $u = this.universe();
                                                                                             JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                                             final class $typecreator30$1 extends TypeCreator {
                                                                                                public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                                   Universe $u = $m$untyped.universe();
                                                                                                   Symbols.SymbolApi symdef$_$121 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectOverloadedMethod($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "encoderFor", 2), (Names.NameApi)$u.TypeName().apply("_$12"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
                                                                                                   $u.internal().reificationSupport().setInfo(symdef$_$121, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
                                                                                                   return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$121, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.Seq"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$121, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)));
                                                                                                }

                                                                                                public $typecreator30$1() {
                                                                                                }
                                                                                             }

                                                                                             if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator30$1())))) {
                                                                                                return this.createIterableEncoder$1(var9, scala.collection.Seq.class, seenTypeSet, path, isRowEncoderSupported);
                                                                                             } else {
                                                                                                JavaUniverse $u = this.universe();
                                                                                                JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                                                final class $typecreator31$1 extends TypeCreator {
                                                                                                   public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                                      Universe $u = $m$untyped.universe();
                                                                                                      Symbols.SymbolApi symdef$_$141 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectOverloadedMethod($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "encoderFor", 2), (Names.NameApi)$u.TypeName().apply("_$14"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
                                                                                                      $u.internal().reificationSupport().setInfo(symdef$_$141, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
                                                                                                      return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$141, scala.collection.immutable.Nil..MODULE$), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.Set"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$141, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$)));
                                                                                                   }

                                                                                                   public $typecreator31$1() {
                                                                                                   }
                                                                                                }

                                                                                                if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator31$1())))) {
                                                                                                   return this.createIterableEncoder$1(var9, scala.collection.Set.class, seenTypeSet, path, isRowEncoderSupported);
                                                                                                } else {
                                                                                                   JavaUniverse $u = this.universe();
                                                                                                   JavaUniverse.JavaMirror $m = this.universe().runtimeMirror(this.getClass().getClassLoader());

                                                                                                   final class $typecreator32$1 extends TypeCreator {
                                                                                                      public Types.TypeApi apply(final Mirror $m$untyped) {
                                                                                                         Universe $u = $m$untyped.universe();
                                                                                                         Symbols.SymbolApi symdef$_$161 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectOverloadedMethod($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "encoderFor", 2), (Names.NameApi)$u.TypeName().apply("_$16"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
                                                                                                         Symbols.SymbolApi symdef$_$171 = $u.internal().reificationSupport().newNestedSymbol($u.internal().reificationSupport().selectOverloadedMethod($m$untyped.staticModule("org.apache.spark.sql.catalyst.ScalaReflection").asModule().moduleClass(), "encoderFor", 2), (Names.NameApi)$u.TypeName().apply("_$17"), $u.NoPosition(), $u.internal().reificationSupport().FlagsRepr().apply(34359738384L), false);
                                                                                                         $u.internal().reificationSupport().setInfo(symdef$_$161, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
                                                                                                         $u.internal().reificationSupport().setInfo(symdef$_$171, (Types.TypeApi)$u.internal().reificationSupport().TypeBounds($m$untyped.staticClass("scala.Nothing").asType().toTypeConstructor(), $m$untyped.staticClass("scala.Any").asType().toTypeConstructor()));
                                                                                                         return (Types.TypeApi)$u.internal().reificationSupport().ExistentialType(new scala.collection.immutable..colon.colon(symdef$_$161, new scala.collection.immutable..colon.colon(symdef$_$171, scala.collection.immutable.Nil..MODULE$)), $u.internal().reificationSupport().TypeRef($u.internal().reificationSupport().ThisType($m$untyped.staticPackage("scala.collection").asModule().moduleClass()), $m$untyped.staticClass("scala.collection.Map"), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$161, scala.collection.immutable.Nil..MODULE$), new scala.collection.immutable..colon.colon($u.internal().reificationSupport().TypeRef($u.NoPrefix(), symdef$_$171, scala.collection.immutable.Nil..MODULE$), scala.collection.immutable.Nil..MODULE$))));
                                                                                                      }

                                                                                                      public $typecreator32$1() {
                                                                                                      }
                                                                                                   }

                                                                                                   if (this.isSubtype(var9, this.localTypeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator32$1())))) {
                                                                                                      if (var9 != null) {
                                                                                                         Option var96 = ((ImplicitTags)this.universe()).TypeRefTag().unapply(var9);
                                                                                                         if (!var96.isEmpty()) {
                                                                                                            Types.TypeRefApi var97 = (Types.TypeRefApi)var96.get();
                                                                                                            if (var97 != null) {
                                                                                                               Option var98 = ((Types)this.universe()).TypeRef().unapply(var97);
                                                                                                               if (!var98.isEmpty()) {
                                                                                                                  List var99 = (List)((Tuple3)var98.get())._3();
                                                                                                                  if (var99 != null) {
                                                                                                                     SeqOps var100 = scala.package..MODULE$.Seq().unapplySeq(var99);
                                                                                                                     if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var100) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var100)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var100), 2) == 0) {
                                                                                                                        Types.TypeApi keyType = (Types.TypeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var100), 0);
                                                                                                                        Types.TypeApi valueType = (Types.TypeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var100), 1);
                                                                                                                        Tuple2 var94 = new Tuple2(keyType, valueType);
                                                                                                                        Types.TypeApi keyType = (Types.TypeApi)var94._1();
                                                                                                                        Types.TypeApi valueType = (Types.TypeApi)var94._2();
                                                                                                                        AgnosticEncoder keyEncoder = this.encoderFor(keyType, seenTypeSet, path.recordKeyForMap(this.getClassNameFromType(keyType)), isRowEncoderSupported);
                                                                                                                        AgnosticEncoder valueEncoder = this.encoderFor(valueType, seenTypeSet, path.recordValueForMap(this.getClassNameFromType(valueType)), isRowEncoderSupported);
                                                                                                                        return new AgnosticEncoders.MapEncoder(scala.reflect.ClassTag..MODULE$.apply(this.getClassFromType(var9)), keyEncoder, valueEncoder, valueEncoder.nullable());
                                                                                                                     }
                                                                                                                  }
                                                                                                               }
                                                                                                            }
                                                                                                         }
                                                                                                      }

                                                                                                      throw new MatchError(var9);
                                                                                                   } else if (this.definedByConstructorParams(var9)) {
                                                                                                      if (seenTypeSet.contains(var9)) {
                                                                                                         throw ExecutionErrors$.MODULE$.cannotHaveCircularReferencesInClassError(var9.toString());
                                                                                                      } else {
                                                                                                         Seq params = (Seq)this.getConstructorParameters(var9).map((x0$1) -> {
                                                                                                            if (x0$1 != null) {
                                                                                                               String fieldName = (String)x0$1._1();
                                                                                                               Types.TypeApi fieldType = (Types.TypeApi)x0$1._2();
                                                                                                               if (!SourceVersion.isKeyword(fieldName) && SourceVersion.isIdentifier(MODULE$.encodeFieldNameToIdentifier(fieldName))) {
                                                                                                                  AgnosticEncoder encoder = MODULE$.encoderFor(fieldType, (Set)seenTypeSet.$plus(var9), path.recordField(MODULE$.getClassNameFromType(fieldType), fieldName), isRowEncoderSupported);
                                                                                                                  return new AgnosticEncoders.EncoderField(fieldName, encoder, encoder.nullable(), Metadata$.MODULE$.empty(), AgnosticEncoders.EncoderField$.MODULE$.apply$default$5(), AgnosticEncoders.EncoderField$.MODULE$.apply$default$6());
                                                                                                               } else {
                                                                                                                  throw ExecutionErrors$.MODULE$.cannotUseInvalidJavaIdentifierAsFieldNameError(fieldName, path);
                                                                                                               }
                                                                                                            } else {
                                                                                                               throw new MatchError(x0$1);
                                                                                                            }
                                                                                                         });
                                                                                                         Class cls = this.getClassFromType(var9);
                                                                                                         return new AgnosticEncoders.ProductEncoder(scala.reflect.ClassTag..MODULE$.apply(cls), params, scala.Option..MODULE$.apply(OuterScopes$.MODULE$.getOuterScope(cls)));
                                                                                                      }
                                                                                                   } else {
                                                                                                      throw ExecutionErrors$.MODULE$.cannotFindEncoderForTypeError(tpe.toString());
                                                                                                   }
                                                                                                }
                                                                                             }
                                                                                          }
                                                                                       }
                                                                                    }
                                                                                 }
                                                                              }
                                                                           }
                                                                        }
                                                                     }
                                                                  }
                                                               }
                                                            }
                                                         }
                                                      }
                                                   }
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean encoderFor$default$2() {
      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findConstructor$3(final Tuple2 x0$1) {
      if (x0$1 == null) {
         throw new MatchError(x0$1);
      } else {
         boolean var6;
         label30: {
            Symbols.SymbolApi ps = (Symbols.SymbolApi)x0$1._1();
            Class pc = (Class)x0$1._2();
            Symbols.SymbolApi var10000 = ps.typeSignature().typeSymbol();
            Symbols.ClassSymbolApi var5 = MODULE$.mirror().classSymbol(pc);
            if (var10000 == null) {
               if (var5 == null) {
                  break label30;
               }
            } else if (var10000.equals(var5)) {
               break label30;
            }

            var6 = false;
            return var6;
         }

         var6 = true;
         return var6;
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findConstructor$2(final Seq paramTypes$1, final Symbols.SymbolApi method) {
      List params = (List)method.typeSignature().paramLists().head();
      return params.size() == paramTypes$1.size() && ((List)params.zip(paramTypes$1)).forall((x0$1) -> BoxesRunTime.boxToBoolean($anonfun$findConstructor$3(x0$1)));
   }

   private final AgnosticEncoder createIterableEncoder$1(final Types.TypeApi t, final Class fallbackClass, final Set seenTypeSet$1, final WalkedTypePath path$1, final boolean isRowEncoderSupported$2) {
      if (t != null) {
         Option var10 = ((ImplicitTags)this.universe()).TypeRefTag().unapply(t);
         if (!var10.isEmpty()) {
            Types.TypeRefApi var11 = (Types.TypeRefApi)var10.get();
            if (var11 != null) {
               Option var12 = ((Types)this.universe()).TypeRef().unapply(var11);
               if (!var12.isEmpty()) {
                  List var13 = (List)((Tuple3)var12.get())._3();
                  if (var13 != null) {
                     SeqOps var14 = scala.package..MODULE$.Seq().unapplySeq(var13);
                     if (!scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.isEmpty$extension(var14) && new SeqFactory.UnapplySeqWrapper(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14)) != null && scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.lengthCompare$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14), 1) == 0) {
                        AgnosticEncoder encoder;
                        Class var21;
                        label34: {
                           label33: {
                              Types.TypeApi elementType = (Types.TypeApi)scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.apply$extension(scala.collection.SeqFactory.UnapplySeqWrapper..MODULE$.get$extension(var14), 0);
                              encoder = this.encoderFor(elementType, seenTypeSet$1, path$1.recordArray(this.getClassNameFromType(elementType)), isRowEncoderSupported$2);
                              Types.TypeApi companion = t.dealias().typeSymbol().companion().typeSignature();
                              Symbols.SymbolApi var19 = companion.member((Names.NameApi)((Names)this.universe()).TermName().apply("newBuilder"));
                              Symbols.SymbolApi var10000 = ((Symbols)this.universe()).NoSymbol();
                              if (var10000 == null) {
                                 if (var19 == null) {
                                    break label33;
                                 }
                              } else if (var10000.equals(var19)) {
                                 break label33;
                              }

                              var21 = (Class)this.mirror().runtimeClass(t.typeSymbol().asClass());
                              break label34;
                           }

                           var21 = fallbackClass;
                        }

                        Class targetClass = var21;
                        return new AgnosticEncoders.IterableEncoder(scala.reflect.ClassTag..MODULE$.apply(targetClass), encoder, encoder.nullable(), false);
                     }
                  }
               }
            }
         }
      }

      throw new MatchError(t);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$encoderFor$2(final Annotations.AnnotationApi x$2) {
      Types.TypeApi var10000 = x$2.tree().tpe();
      TypeTags var10001 = (TypeTags)MODULE$.universe();
      JavaUniverse $u = MODULE$.universe();
      JavaUniverse.JavaMirror $m = MODULE$.universe().runtimeMirror(MODULE$.getClass().getClassLoader());

      final class $typecreator27$1 extends TypeCreator {
         public Types.TypeApi apply(final Mirror $m$untyped) {
            Universe $u = $m$untyped.universe();
            return $m$untyped.staticClass("org.apache.spark.sql.types.SQLUserDefinedType").asType().toTypeConstructor();
         }

         public $typecreator27$1() {
         }
      }

      return var10000.$eq$colon$eq(var10001.typeOf(((TypeTags)$u).TypeTag().apply((Mirror)$m, new $typecreator27$1())));
   }

   private ScalaReflection$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
