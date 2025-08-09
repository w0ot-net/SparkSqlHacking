package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Predef;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.IterableOnceOps;
import scala.collection.MapFactory;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.StrictOptimizedSeqOps;
import scala.math.Ordering;
import scala.runtime.ScalaRunTime;

public final class Mode$ {
   public static final Mode$ MODULE$ = new Mode$();
   private static final int NOmode;
   private static final int EXPRmode;
   private static final int PATTERNmode;
   private static final int TYPEmode;
   private static final int SCCmode;
   private static final int FUNmode;
   private static final int POLYmode;
   private static final int QUALmode;
   private static final int TAPPmode;
   private static final int LHSmode;
   private static final int BYVALmode;
   private static final int TYPEPATmode;
   private static final int APPSELmode;
   private static final int StickyModes;
   private static final int StickyModesForFun;
   private static final int MonoQualifierModes;
   private static final int PolyQualifierModes;
   private static final int OperatorModes;
   private static final Map modeNameMap;

   static {
      Mode$ var10000 = MODULE$;
      NOmode = 0;
      var10000 = MODULE$;
      EXPRmode = 1;
      var10000 = MODULE$;
      PATTERNmode = 2;
      var10000 = MODULE$;
      TYPEmode = 4;
      var10000 = MODULE$;
      SCCmode = 8;
      var10000 = MODULE$;
      FUNmode = 16;
      var10000 = MODULE$;
      POLYmode = 32;
      var10000 = MODULE$;
      QUALmode = 64;
      var10000 = MODULE$;
      TAPPmode = 128;
      var10000 = MODULE$;
      LHSmode = 1024;
      var10000 = MODULE$;
      BYVALmode = 32768;
      var10000 = MODULE$;
      TYPEPATmode = 65536;
      var10000 = MODULE$;
      APPSELmode = 131072;
      var10000 = MODULE$;
      var10000 = MODULE$;
      StickyModes = MODULE$.EXPRmode() | MODULE$.PATTERNmode() | MODULE$.TYPEmode();
      var10000 = MODULE$;
      StickyModesForFun = MODULE$.StickyModes() | MODULE$.SCCmode();
      var10000 = MODULE$;
      var10000 = MODULE$;
      MonoQualifierModes = MODULE$.EXPRmode() | MODULE$.QUALmode() | MODULE$.APPSELmode();
      var10000 = MODULE$;
      PolyQualifierModes = MODULE$.MonoQualifierModes() | MODULE$.POLYmode();
      var10000 = MODULE$;
      var10000 = MODULE$;
      var10000 = MODULE$;
      OperatorModes = MODULE$.EXPRmode() | MODULE$.POLYmode() | MODULE$.TAPPmode() | MODULE$.FUNmode();
      Map var69 = .MODULE$.Map();
      ScalaRunTime var10001 = scala.runtime.ScalaRunTime..MODULE$;
      Tuple2[] var10002 = new Tuple2[12];
      Predef.ArrowAssoc var10005 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var70 = new Mode(MODULE$.EXPRmode());
      String $minus$greater$extension_y = "EXPRmode";
      Object $minus$greater$extension_$this = var70;
      Tuple2 var71 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var25 = null;
      var10002[0] = var71;
      Predef.ArrowAssoc var72 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var73 = new Mode(MODULE$.PATTERNmode());
      String $minus$greater$extension_y = "PATTERNmode";
      Object $minus$greater$extension_$this = var73;
      Tuple2 var74 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var27 = null;
      var10002[1] = var74;
      Predef.ArrowAssoc var75 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var76 = new Mode(MODULE$.TYPEmode());
      String $minus$greater$extension_y = "TYPEmode";
      Object $minus$greater$extension_$this = var76;
      Tuple2 var77 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var29 = null;
      var10002[2] = var77;
      Predef.ArrowAssoc var78 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var79 = new Mode(MODULE$.SCCmode());
      String $minus$greater$extension_y = "SCCmode";
      Object $minus$greater$extension_$this = var79;
      Tuple2 var80 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var31 = null;
      var10002[3] = var80;
      Predef.ArrowAssoc var81 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var82 = new Mode(MODULE$.FUNmode());
      String $minus$greater$extension_y = "FUNmode";
      Object $minus$greater$extension_$this = var82;
      Tuple2 var83 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var33 = null;
      var10002[4] = var83;
      Predef.ArrowAssoc var84 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var85 = new Mode(MODULE$.POLYmode());
      String $minus$greater$extension_y = "POLYmode";
      Object $minus$greater$extension_$this = var85;
      Tuple2 var86 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var35 = null;
      var10002[5] = var86;
      Predef.ArrowAssoc var87 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var88 = new Mode(MODULE$.QUALmode());
      String $minus$greater$extension_y = "QUALmode";
      Object $minus$greater$extension_$this = var88;
      Tuple2 var89 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var37 = null;
      var10002[6] = var89;
      Predef.ArrowAssoc var90 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var91 = new Mode(MODULE$.TAPPmode());
      String $minus$greater$extension_y = "TAPPmode";
      Object $minus$greater$extension_$this = var91;
      Tuple2 var92 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var39 = null;
      var10002[7] = var92;
      Predef.ArrowAssoc var93 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var94 = new Mode(MODULE$.LHSmode());
      String $minus$greater$extension_y = "LHSmode";
      Object $minus$greater$extension_$this = var94;
      Tuple2 var95 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var41 = null;
      var10002[8] = var95;
      Predef.ArrowAssoc var96 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var97 = new Mode(MODULE$.BYVALmode());
      String $minus$greater$extension_y = "BYVALmode";
      Object $minus$greater$extension_$this = var97;
      Tuple2 var98 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var43 = null;
      var10002[9] = var98;
      Predef.ArrowAssoc var99 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var100 = new Mode(MODULE$.TYPEPATmode());
      String $minus$greater$extension_y = "TYPEPATmode";
      Object $minus$greater$extension_$this = var100;
      Tuple2 var101 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var45 = null;
      var10002[10] = var101;
      Predef.ArrowAssoc var102 = scala.Predef.ArrowAssoc..MODULE$;
      Mode var103 = new Mode(MODULE$.APPSELmode());
      String $minus$greater$extension_y = "APPSELmode";
      Object $minus$greater$extension_$this = var103;
      Tuple2 var104 = new Tuple2($minus$greater$extension_$this, $minus$greater$extension_y);
      $minus$greater$extension_$this = null;
      Object var47 = null;
      var10002[11] = var104;
      modeNameMap = (Map)MapFactory.apply$(var69, var10001.wrapRefArray(var10002));
   }

   public int apply(final int bits) {
      return bits;
   }

   public final int NOmode() {
      return NOmode;
   }

   public final int EXPRmode() {
      return EXPRmode;
   }

   public final int PATTERNmode() {
      return PATTERNmode;
   }

   public final int TYPEmode() {
      return TYPEmode;
   }

   public final int SCCmode() {
      return SCCmode;
   }

   public final int FUNmode() {
      return FUNmode;
   }

   public final int POLYmode() {
      return POLYmode;
   }

   public final int QUALmode() {
      return QUALmode;
   }

   public final int TAPPmode() {
      return TAPPmode;
   }

   public final int LHSmode() {
      return LHSmode;
   }

   public final int BYVALmode() {
      return BYVALmode;
   }

   public final int TYPEPATmode() {
      return TYPEPATmode;
   }

   public final int APPSELmode() {
      return APPSELmode;
   }

   private int StickyModes() {
      return StickyModes;
   }

   private int StickyModesForFun() {
      return StickyModesForFun;
   }

   public final int MonoQualifierModes() {
      return MonoQualifierModes;
   }

   public final int PolyQualifierModes() {
      return PolyQualifierModes;
   }

   public final int OperatorModes() {
      return OperatorModes;
   }

   private Map modeNameMap() {
      return modeNameMap;
   }

   public final int $amp$extension(final int $this, final int other) {
      return $this & other;
   }

   public final int $bar$extension(final int $this, final int other) {
      return $this | other;
   }

   public final int $amp$tilde$extension(final int $this, final int other) {
      return $this & ~other;
   }

   public final int onlyTypePat$extension(final int $this) {
      int $amp$extension_other = this.TYPEPATmode();
      return $this & $amp$extension_other;
   }

   public final int onlySticky$extension(final int $this) {
      int $amp$extension_other = this.StickyModes();
      return $this & $amp$extension_other;
   }

   public final int forFunMode$extension(final int $this) {
      int $amp$extension_other = this.StickyModesForFun();
      return $this & $amp$extension_other | this.FUNmode() | this.POLYmode() | this.BYVALmode() | this.APPSELmode();
   }

   public final int forTypeMode$extension(final int $this) {
      return this.typingPatternOrTypePat$extension($this) ? this.TYPEmode() | this.TYPEPATmode() : this.TYPEmode();
   }

   public final boolean inAll$extension(final int $this, final int required) {
      return ($this & required) == required;
   }

   public final boolean inAny$extension(final int $this, final int required) {
      return ($this & required) != this.NOmode();
   }

   public final boolean inNone$extension(final int $this, final int prohibited) {
      return ($this & prohibited) == this.NOmode();
   }

   public final boolean in$extension(final int $this, final int all, final int none) {
      return this.inAll$extension($this, all) && this.inNone$extension($this, none);
   }

   public final int in$default$1$extension(final int $this) {
      return this.NOmode();
   }

   public final int in$default$2$extension(final int $this) {
      return this.NOmode();
   }

   public final boolean inByValMode$extension(final int $this) {
      return this.inAll$extension($this, this.BYVALmode());
   }

   public final boolean inExprMode$extension(final int $this) {
      return this.inAll$extension($this, this.EXPRmode());
   }

   public final boolean inFunMode$extension(final int $this) {
      return this.inAll$extension($this, this.FUNmode());
   }

   public final boolean inPatternMode$extension(final int $this) {
      return this.inAll$extension($this, this.PATTERNmode());
   }

   public final boolean inPolyMode$extension(final int $this) {
      return this.inAll$extension($this, this.POLYmode());
   }

   public final boolean inQualMode$extension(final int $this) {
      return this.inAll$extension($this, this.QUALmode());
   }

   public final boolean inSccMode$extension(final int $this) {
      return this.inAll$extension($this, this.SCCmode());
   }

   public final boolean inTappMode$extension(final int $this) {
      return this.inAll$extension($this, this.TAPPmode());
   }

   public final boolean inTypeMode$extension(final int $this) {
      return this.inAll$extension($this, this.TYPEmode());
   }

   public final boolean typingExprByValue$extension(final int $this) {
      return this.inAll$extension($this, this.EXPRmode() | this.BYVALmode());
   }

   public final boolean typingExprFun$extension(final int $this) {
      return this.inAll$extension($this, this.EXPRmode() | this.FUNmode());
   }

   public final boolean typingExprNotFun$extension(final int $this) {
      return this.in$extension($this, this.EXPRmode(), this.FUNmode());
   }

   public final boolean typingExprNotFunNotLhs$extension(final int $this) {
      return this.in$extension($this, this.EXPRmode(), this.FUNmode() | this.LHSmode());
   }

   public final boolean typingExprNotLhs$extension(final int $this) {
      return this.in$extension($this, this.EXPRmode(), this.LHSmode());
   }

   public final boolean typingExprNotValue$extension(final int $this) {
      return this.in$extension($this, this.EXPRmode(), this.BYVALmode());
   }

   public final boolean typingMonoExprByValue$extension(final int $this) {
      return this.in$extension($this, this.EXPRmode() | this.BYVALmode(), this.POLYmode());
   }

   public final boolean typingConstructorPattern$extension(final int $this) {
      return this.inAll$extension($this, this.PATTERNmode() | this.FUNmode());
   }

   public final boolean typingPatternNotConstructor$extension(final int $this) {
      return this.in$extension($this, this.PATTERNmode(), this.FUNmode());
   }

   public final boolean typingPatternOrTypePat$extension(final int $this) {
      return this.inAny$extension($this, this.PATTERNmode() | this.TYPEPATmode());
   }

   public final String toString$extension(final int $this) {
      if ($this == this.NOmode()) {
         return "NOmode";
      } else {
         List var10000 = this.modeNameMap().view().filterKeys((required) -> $anonfun$toString$1($this, ((Mode)required).bits())).values().toList();
         Ordering.String sorted_ord = scala.math.Ordering.String..MODULE$;
         if (var10000 == null) {
            throw null;
         } else {
            Object var5 = StrictOptimizedSeqOps.sorted$(var10000, sorted_ord);
            Object var4 = null;
            IterableOnceOps var6 = (IterableOnceOps)var5;
            String mkString_sep = "-";
            if (var6 == null) {
               throw null;
            } else {
               return var6.mkString("", mkString_sep, "");
            }
         }
      }
   }

   public final int hashCode$extension(final int $this) {
      return Integer.hashCode($this);
   }

   public final boolean equals$extension(final int $this, final Object x$1) {
      if (x$1 instanceof Mode) {
         int var3 = ((Mode)x$1).bits();
         if ($this == var3) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$toString$1(final int $this$1, final int required) {
      return MODULE$.inAll$extension($this$1, required);
   }

   private Mode$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
