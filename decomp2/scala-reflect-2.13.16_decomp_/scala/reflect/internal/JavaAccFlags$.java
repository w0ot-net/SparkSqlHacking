package scala.reflect.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil.;

public final class JavaAccFlags$ {
   public static final JavaAccFlags$ MODULE$ = new JavaAccFlags$();
   private static final int Unknown = 0;
   private static final int Class = 1;
   private static final int Field = 2;
   private static final int Method = 3;
   private static final int Constructor = 4;

   private int Unknown() {
      return Unknown;
   }

   private int Class() {
      return Class;
   }

   private int Field() {
      return Field;
   }

   private int Method() {
      return Method;
   }

   private int Constructor() {
      return Constructor;
   }

   private int create(final int flagCarrier, final int access_flags) {
      return flagCarrier << 16 | access_flags & '\uffff';
   }

   public int classFlags(final int flags) {
      return this.Class() << 16 | flags & '\uffff';
   }

   public int methodFlags(final int flags) {
      return this.Method() << 16 | flags & '\uffff';
   }

   public int fieldFlags(final int flags) {
      return this.Field() << 16 | flags & '\uffff';
   }

   public int constructorFlags(final int flags) {
      return this.Constructor() << 16 | flags & '\uffff';
   }

   public int apply(final int access_flags) {
      return this.Unknown() << 16 | access_flags & '\uffff';
   }

   public int apply(final Class clazz) {
      return this.classFlags(clazz.getModifiers());
   }

   public int apply(final Member member) {
      if (member instanceof Constructor) {
         Constructor var2 = (Constructor)member;
         return this.constructorFlags(var2.getModifiers());
      } else if (member instanceof Method) {
         Method var3 = (Method)member;
         return this.methodFlags(var3.getModifiers());
      } else if (member instanceof Field) {
         Field var4 = (Field)member;
         return this.fieldFlags(var4.getModifiers());
      } else {
         return this.apply(member.getModifiers());
      }
   }

   public final boolean has$extension(final int $this, final int mask) {
      return ($this & '\uffff' & mask) != 0;
   }

   public final int flagCarrierId$extension(final int $this) {
      return $this >>> 16;
   }

   public final int flags$extension(final int $this) {
      return $this & '\uffff';
   }

   public final boolean isAbstract$extension(final int $this) {
      return this.has$extension($this, 1024);
   }

   public final boolean isAnnotation$extension(final int $this) {
      return this.has$extension($this, 8192);
   }

   public final boolean isBridge$extension(final int $this) {
      return this.has$extension($this, 64);
   }

   public final boolean isEnum$extension(final int $this) {
      return this.has$extension($this, 16384);
   }

   public final boolean isFinal$extension(final int $this) {
      return this.has$extension($this, 16);
   }

   public final boolean isInterface$extension(final int $this) {
      return this.has$extension($this, 512);
   }

   public final boolean isNative$extension(final int $this) {
      return this.has$extension($this, 256);
   }

   public final boolean isPrivate$extension(final int $this) {
      return this.has$extension($this, 2);
   }

   public final boolean isProtected$extension(final int $this) {
      return this.has$extension($this, 4);
   }

   public final boolean isPublic$extension(final int $this) {
      return this.has$extension($this, 1);
   }

   public final boolean isStatic$extension(final int $this) {
      return this.has$extension($this, 8);
   }

   public final boolean isStrictFp$extension(final int $this) {
      return this.has$extension($this, 2048);
   }

   public final boolean isSuper$extension(final int $this) {
      return this.has$extension($this, 32);
   }

   public final boolean isSynchronized$extension(final int $this) {
      return this.has$extension($this, 32);
   }

   public final boolean isSynthetic$extension(final int $this) {
      return this.has$extension($this, 4096);
   }

   public final boolean isTransient$extension(final int $this) {
      return this.has$extension($this, 128);
   }

   public final boolean isVarargs$extension(final int $this) {
      return this.has$extension($this, 128);
   }

   public final boolean isVolatile$extension(final int $this) {
      return this.has$extension($this, 64);
   }

   public final boolean hasPackageAccessBoundary$extension(final int $this) {
      return !this.has$extension($this, 3);
   }

   public final boolean isPackageProtected$extension(final int $this) {
      return !this.has$extension($this, 7);
   }

   public final int toJavaFlags$extension(final int $this) {
      return $this & '\uffff';
   }

   public final long toScalaFlags$extension(final int $this) {
      int var2 = $this >>> 16;
      if (this.Method() == var2 ? true : this.Constructor() == var2) {
         return ClassfileConstants.FlagTranslation$.MODULE$.methodFlags($this & '\uffff');
      } else {
         return this.Class() == var2 ? ClassfileConstants.FlagTranslation$.MODULE$.classFlags($this & '\uffff') : ClassfileConstants.FlagTranslation$.MODULE$.fieldFlags($this & '\uffff');
      }
   }

   public final List toScalaAnnotations$extension(final int $this, final SymbolTable syms) {
      List anns = .MODULE$;
      if (this.has$extension($this, 256)) {
         Object $colon$colon_elem = annInfo$1(syms.definitions().NativeAttr(), syms);
         scala.collection.immutable..colon.colon var10000 = new scala.collection.immutable..colon.colon($colon$colon_elem, anns);
         $colon$colon_elem = null;
         anns = var10000;
      }

      if (this.has$extension($this, 128)) {
         anns = anns.$colon$colon(annInfo$1(syms.definitions().TransientAttr(), syms));
      }

      if (this.has$extension($this, 64)) {
         anns = anns.$colon$colon(annInfo$1(syms.definitions().VolatileAttr(), syms));
      }

      return anns;
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      if (x$1 instanceof JavaAccFlags) {
         int var3 = ((JavaAccFlags)x$1).coded();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   private static final AnnotationInfos.AnnotationInfo annInfo$1(final Symbols.ClassSymbol asym, final SymbolTable syms$1) {
      return syms$1.AnnotationInfo().apply(((Symbols.Symbol)asym).tpe_$times(), .MODULE$, .MODULE$);
   }

   private JavaAccFlags$() {
   }
}
