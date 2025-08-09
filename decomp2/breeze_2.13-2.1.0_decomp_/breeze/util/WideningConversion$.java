package breeze.util;

import scala.Int.;

public final class WideningConversion$ {
   public static final WideningConversion$ MODULE$ = new WideningConversion$();
   private static final WideningConversion int2Double = new WideningConversion$mcID$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final double apply(final int f) {
         return this.apply$mcID$sp(f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$int2Double$1(f);
      }
   };
   private static final WideningConversion int2Long = new WideningConversion$mcIJ$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final long apply(final int f) {
         return this.apply$mcIJ$sp(f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$int2Long$1(f);
      }
   };
   private static final WideningConversion float2Double = new WideningConversion$mcFD$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final double apply(final float f) {
         return this.apply$mcFD$sp(f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$float2Double$1(f);
      }
   };
   private static final WideningConversion short2Int = new WideningConversion$mcSI$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final int apply(final short f) {
         return this.apply$mcSI$sp(f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$short2Int$1(f);
      }
   };
   private static final WideningConversion short2Long = new WideningConversion$mcSJ$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public final long apply(final short f) {
         return this.apply$mcSJ$sp(f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$short2Long$1(f);
      }
   };
   private static final WideningConversion short2Float = new WideningConversion() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final float apply(final short f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$short2Float$1(f);
      }
   };
   private static final WideningConversion short2Double = new WideningConversion$mcSD$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final double apply(final short f) {
         return this.apply$mcSD$sp(f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$short2Double$1(f);
      }
   };
   private static final WideningConversion byte2Int = new WideningConversion$mcBI$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final int apply(final byte f) {
         return this.apply$mcBI$sp(f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$byte2Int$1(f);
      }
   };
   private static final WideningConversion byte2Short = new WideningConversion() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final short apply(final byte f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$byte2Short$1(f);
      }
   };
   private static final WideningConversion byte2Long = new WideningConversion$mcBJ$sp() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final long apply(final byte f) {
         return this.apply$mcBJ$sp(f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$byte2Long$1(f);
      }
   };
   private static final WideningConversion byte2Float = new WideningConversion() {
      public double apply$mcBD$sp(final byte f) {
         return WideningConversion.apply$mcBD$sp$(this, f);
      }

      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final float apply(final byte f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$byte2Float$1(f);
      }
   };
   private static final WideningConversion byte2Double = new WideningConversion$mcBD$sp() {
      public int apply$mcBI$sp(final byte f) {
         return WideningConversion.apply$mcBI$sp$(this, f);
      }

      public long apply$mcBJ$sp(final byte f) {
         return WideningConversion.apply$mcBJ$sp$(this, f);
      }

      public double apply$mcFD$sp(final float f) {
         return WideningConversion.apply$mcFD$sp$(this, f);
      }

      public int apply$mcFI$sp(final float f) {
         return WideningConversion.apply$mcFI$sp$(this, f);
      }

      public long apply$mcFJ$sp(final float f) {
         return WideningConversion.apply$mcFJ$sp$(this, f);
      }

      public double apply$mcID$sp(final int f) {
         return WideningConversion.apply$mcID$sp$(this, f);
      }

      public int apply$mcII$sp(final int f) {
         return WideningConversion.apply$mcII$sp$(this, f);
      }

      public long apply$mcIJ$sp(final int f) {
         return WideningConversion.apply$mcIJ$sp$(this, f);
      }

      public double apply$mcSD$sp(final short f) {
         return WideningConversion.apply$mcSD$sp$(this, f);
      }

      public int apply$mcSI$sp(final short f) {
         return WideningConversion.apply$mcSI$sp$(this, f);
      }

      public long apply$mcSJ$sp(final short f) {
         return WideningConversion.apply$mcSJ$sp$(this, f);
      }

      public final double apply(final byte f) {
         return this.apply$mcBD$sp(f);
      }

      public double apply$mcBD$sp(final byte f) {
         return WideningConversion$.breeze$util$WideningConversion$$$anonfun$byte2Double$1(f);
      }
   };

   public WideningConversion wc() {
      return (t) -> t;
   }

   public WideningConversion int2Double() {
      return int2Double;
   }

   public WideningConversion int2Long() {
      return int2Long;
   }

   public WideningConversion float2Double() {
      return float2Double;
   }

   public WideningConversion short2Int() {
      return short2Int;
   }

   public WideningConversion short2Long() {
      return short2Long;
   }

   public WideningConversion short2Float() {
      return short2Float;
   }

   public WideningConversion short2Double() {
      return short2Double;
   }

   public WideningConversion byte2Int() {
      return byte2Int;
   }

   public WideningConversion byte2Short() {
      return byte2Short;
   }

   public WideningConversion byte2Long() {
      return byte2Long;
   }

   public WideningConversion byte2Float() {
      return byte2Float;
   }

   public WideningConversion byte2Double() {
      return byte2Double;
   }

   // $FF: synthetic method
   public static final double breeze$util$WideningConversion$$$anonfun$int2Double$1(final int x) {
      return .MODULE$.int2double(x);
   }

   // $FF: synthetic method
   public static final long breeze$util$WideningConversion$$$anonfun$int2Long$1(final int x) {
      return .MODULE$.int2long(x);
   }

   // $FF: synthetic method
   public static final double breeze$util$WideningConversion$$$anonfun$float2Double$1(final float x) {
      return scala.Float..MODULE$.float2double(x);
   }

   // $FF: synthetic method
   public static final int breeze$util$WideningConversion$$$anonfun$short2Int$1(final short x) {
      return scala.Short..MODULE$.short2int(x);
   }

   // $FF: synthetic method
   public static final long breeze$util$WideningConversion$$$anonfun$short2Long$1(final short x) {
      return scala.Short..MODULE$.short2long(x);
   }

   // $FF: synthetic method
   public static final float breeze$util$WideningConversion$$$anonfun$short2Float$1(final short x) {
      return scala.Short..MODULE$.short2float(x);
   }

   // $FF: synthetic method
   public static final double breeze$util$WideningConversion$$$anonfun$short2Double$1(final short x) {
      return scala.Short..MODULE$.short2double(x);
   }

   // $FF: synthetic method
   public static final int breeze$util$WideningConversion$$$anonfun$byte2Int$1(final byte x) {
      return scala.Byte..MODULE$.byte2int(x);
   }

   // $FF: synthetic method
   public static final short breeze$util$WideningConversion$$$anonfun$byte2Short$1(final byte x) {
      return scala.Byte..MODULE$.byte2short(x);
   }

   // $FF: synthetic method
   public static final long breeze$util$WideningConversion$$$anonfun$byte2Long$1(final byte x) {
      return scala.Byte..MODULE$.byte2long(x);
   }

   // $FF: synthetic method
   public static final float breeze$util$WideningConversion$$$anonfun$byte2Float$1(final byte x) {
      return scala.Byte..MODULE$.byte2float(x);
   }

   // $FF: synthetic method
   public static final double breeze$util$WideningConversion$$$anonfun$byte2Double$1(final byte x) {
      return scala.Byte..MODULE$.byte2double(x);
   }

   private WideningConversion$() {
   }
}
