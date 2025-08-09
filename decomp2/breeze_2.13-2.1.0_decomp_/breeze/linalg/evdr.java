package breeze.linalg;

import breeze.generic.UFunc;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=r!B\f\u0019\u0011\u0003ib!B\u0010\u0019\u0011\u0003\u0001\u0003\"B\u0017\u0002\t\u0003qs!B\u0018\u0002\u0011\u0007\u0001d!\u0002\u001a\u0002\u0011\u0003\u0019\u0004\"B\u0017\u0005\t\u0003q\u0005\"B(\u0005\t\u0003\u0001\u0006bB+\u0005\u0003\u0003%IAV\u0004\u0006?\u0006A\u0019\u0001\u0019\u0004\u0006C\u0006A\tA\u0019\u0005\u0006[%!\tA\u001a\u0005\u0006\u001f&!\ta\u001a\u0005\b+&\t\t\u0011\"\u0003W\u000f\u0015a\u0017\u0001c\u0001n\r\u0015q\u0017\u0001#\u0001p\u0011\u0015ic\u0002\"\u0001t\u0011\u0015ye\u0002\"\u0001u\u0011\u001d)f\"!A\u0005\nYCQA_\u0001\u0005\nmD\u0011\"!\u0001\u0002#\u0003%I!a\u0001\t\u0013\u0005e\u0011!%A\u0005\n\u0005\r\u0001bBA\u000e\u0003\u0011%\u0011Q\u0004\u0005\b\u0003O\tA\u0011BA\u0015\u0003\u0011)g\u000f\u001a:\u000b\u0005eQ\u0012A\u00027j]\u0006dwMC\u0001\u001c\u0003\u0019\u0011'/Z3{K\u000e\u0001\u0001C\u0001\u0010\u0002\u001b\u0005A\"\u0001B3wIJ\u001c2!A\u0011(!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0011\u0001fK\u0007\u0002S)\u0011!FG\u0001\bO\u0016tWM]5d\u0013\ta\u0013FA\u0003V\rVt7-\u0001\u0004=S:LGO\u0010\u000b\u0002;\u0005iQI\u0016#S?\u0012ku,S7qYJ\u0002\"!\r\u0003\u000e\u0003\u0005\u0011Q\"\u0012,E%~#UjX%na2\u00144c\u0001\u0003\"iA)\u0011'N\u001c>\u0001&\u0011ag\u000b\u0002\u0006\u00136\u0004HN\r\t\u0004=aR\u0014BA\u001d\u0019\u0005-!UM\\:f\u001b\u0006$(/\u001b=\u0011\u0005\tZ\u0014B\u0001\u001f$\u0005\u0019!u.\u001e2mKB\u0011!EP\u0005\u0003\u007f\r\u00121!\u00138u!\t\t5J\u0004\u0002C\u0013:\u00111\t\u0013\b\u0003\t\u001ek\u0011!\u0012\u0006\u0003\rr\ta\u0001\u0010:p_Rt\u0014\"A\u000e\n\u0005eQ\u0012B\u0001&\u0019\u0003\u0019)\u0017nZ*z[&\u0011A*\u0014\u0002\f\t\u0016t7/Z#jONKXN\u0003\u0002K1Q\t\u0001'A\u0003baBd\u0017\u0010F\u0002A#NCQA\u0015\u0004A\u0002]\n\u0011!\u0014\u0005\u0006)\u001a\u0001\r!P\u0001\u0002g\u0006aqO]5uKJ+\u0007\u000f\\1dKR\tq\u000b\u0005\u0002Y;6\t\u0011L\u0003\u0002[7\u0006!A.\u00198h\u0015\u0005a\u0016\u0001\u00026bm\u0006L!AX-\u0003\r=\u0013'.Z2u\u00035)e\u000b\u0012*`\t6{\u0016*\u001c9mgA\u0011\u0011'\u0003\u0002\u000e\u000bZ#%k\u0018#N?&k\u0007\u000f\\\u001a\u0014\u0007%\t3\r\u0005\u00042I^jT\bQ\u0005\u0003K.\u0012Q!S7qYN\"\u0012\u0001\u0019\u000b\u0005\u0001\"L'\u000eC\u0003S\u0017\u0001\u0007q\u0007C\u0003U\u0017\u0001\u0007Q\bC\u0003l\u0017\u0001\u0007Q(\u0001\u0007o\u001fZ,'o]1na2,7/A\u0007F-\u0012\u0013v\fR'`\u00136\u0004H\u000e\u000e\t\u0003c9\u0011Q\"\u0012,E%~#UjX%na2$4c\u0001\b\"aB9\u0011']\u001c>{u\u0002\u0015B\u0001:,\u0005\u0015IU\u000e\u001d75)\u0005iG#\u0002!vm^D\b\"\u0002*\u0011\u0001\u00049\u0004\"\u0002+\u0011\u0001\u0004i\u0004\"B6\u0011\u0001\u0004i\u0004\"B=\u0011\u0001\u0004i\u0014!\u00028Ji\u0016\u0014\u0018A\u00043p\u000b&<7+_7E_V\u0014G.\u001a\u000b\u0006\u0001rlhp \u0005\u0006%J\u0001\ra\u000e\u0005\u0006)J\u0001\r!\u0010\u0005\bWJ\u0001\n\u00111\u0001>\u0011\u001dI(\u0003%AA\u0002u\n\u0001\u0004Z8FS\u001e\u001c\u00160\u001c#pk\ndW\r\n3fM\u0006,H\u000e\u001e\u00134+\t\t)AK\u0002>\u0003\u000fY#!!\u0003\u0011\t\u0005-\u0011QC\u0007\u0003\u0003\u001bQA!a\u0004\u0002\u0012\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003'\u0019\u0013AC1o]>$\u0018\r^5p]&!\u0011qCA\u0007\u0005E)hn\u00195fG.,GMV1sS\u0006t7-Z\u0001\u0019I>,\u0015nZ*z[\u0012{WO\u00197fI\u0011,g-Y;mi\u0012\"\u0014!\u0006:b]\u0012|W.\u001b>fIN#\u0018\r^3GS:$WM\u001d\u000b\bo\u0005}\u0011\u0011EA\u0013\u0011\u0015\u0011V\u00031\u00018\u0011\u0019\t\u0019#\u0006a\u0001{\u0005!1/\u001b>f\u0011\u0015IX\u00031\u0001>\u0003%1G.\u001b9TS\u001et7\u000fF\u00028\u0003WAa!!\f\u0017\u0001\u00049\u0014!A;"
)
public final class evdr {
   public static Object withSink(final Object s) {
      return evdr$.MODULE$.withSink(s);
   }

   public static Object inPlace(final Object v, final Object v2, final Object v3, final UFunc.InPlaceImpl3 impl) {
      return evdr$.MODULE$.inPlace(v, v2, v3, impl);
   }

   public static Object inPlace(final Object v, final Object v2, final UFunc.InPlaceImpl2 impl) {
      return evdr$.MODULE$.inPlace(v, v2, impl);
   }

   public static Object inPlace(final Object v, final UFunc.InPlaceImpl impl) {
      return evdr$.MODULE$.inPlace(v, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final Object v4, final UFunc.UImpl4 impl) {
      return evdr$.MODULE$.apply(v1, v2, v3, v4, impl);
   }

   public static Object apply(final Object v1, final Object v2, final Object v3, final UFunc.UImpl3 impl) {
      return evdr$.MODULE$.apply(v1, v2, v3, impl);
   }

   public static Object apply(final Object v1, final Object v2, final UFunc.UImpl2 impl) {
      return evdr$.MODULE$.apply(v1, v2, impl);
   }

   public static Object apply(final Object v, final UFunc.UImpl impl) {
      return evdr$.MODULE$.apply(v, impl);
   }

   public static class EVDR_DM_Impl2$ implements UFunc.UImpl2 {
      public static final EVDR_DM_Impl2$ MODULE$ = new EVDR_DM_Impl2$();

      public double apply$mcDDD$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
      }

      public float apply$mcDDF$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
      }

      public int apply$mcDDI$sp(final double v, final double v2) {
         return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
      }

      public double apply$mcDFD$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
      }

      public float apply$mcDFF$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
      }

      public int apply$mcDFI$sp(final double v, final float v2) {
         return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
      }

      public double apply$mcDID$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
      }

      public float apply$mcDIF$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
      }

      public int apply$mcDII$sp(final double v, final int v2) {
         return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
      }

      public double apply$mcFDD$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
      }

      public float apply$mcFDF$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
      }

      public int apply$mcFDI$sp(final float v, final double v2) {
         return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
      }

      public double apply$mcFFD$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
      }

      public float apply$mcFFF$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
      }

      public int apply$mcFFI$sp(final float v, final float v2) {
         return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
      }

      public double apply$mcFID$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
      }

      public float apply$mcFIF$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
      }

      public int apply$mcFII$sp(final float v, final int v2) {
         return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
      }

      public double apply$mcIDD$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
      }

      public float apply$mcIDF$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
      }

      public int apply$mcIDI$sp(final int v, final double v2) {
         return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
      }

      public double apply$mcIFD$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
      }

      public float apply$mcIFF$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
      }

      public int apply$mcIFI$sp(final int v, final float v2) {
         return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
      }

      public double apply$mcIID$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
      }

      public float apply$mcIIF$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
      }

      public int apply$mcIII$sp(final int v, final int v2) {
         return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
      }

      public eigSym.EigSym apply(final DenseMatrix M, final int s) {
         return evdr$.MODULE$.breeze$linalg$evdr$$doEigSymDouble(M, s, 10, 0);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EVDR_DM_Impl2$.class);
      }
   }

   public static class EVDR_DM_Impl3$ implements UFunc.UImpl3 {
      public static final EVDR_DM_Impl3$ MODULE$ = new EVDR_DM_Impl3$();

      public double apply$mcDDDD$sp(final double v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcDDDD$sp$(this, v, v2, v3);
      }

      public float apply$mcDDDF$sp(final double v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcDDDF$sp$(this, v, v2, v3);
      }

      public int apply$mcDDDI$sp(final double v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcDDDI$sp$(this, v, v2, v3);
      }

      public double apply$mcDDFD$sp(final double v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcDDFD$sp$(this, v, v2, v3);
      }

      public float apply$mcDDFF$sp(final double v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcDDFF$sp$(this, v, v2, v3);
      }

      public int apply$mcDDFI$sp(final double v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcDDFI$sp$(this, v, v2, v3);
      }

      public double apply$mcDDID$sp(final double v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcDDID$sp$(this, v, v2, v3);
      }

      public float apply$mcDDIF$sp(final double v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcDDIF$sp$(this, v, v2, v3);
      }

      public int apply$mcDDII$sp(final double v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcDDII$sp$(this, v, v2, v3);
      }

      public double apply$mcDFDD$sp(final double v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcDFDD$sp$(this, v, v2, v3);
      }

      public float apply$mcDFDF$sp(final double v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcDFDF$sp$(this, v, v2, v3);
      }

      public int apply$mcDFDI$sp(final double v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcDFDI$sp$(this, v, v2, v3);
      }

      public double apply$mcDFFD$sp(final double v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcDFFD$sp$(this, v, v2, v3);
      }

      public float apply$mcDFFF$sp(final double v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcDFFF$sp$(this, v, v2, v3);
      }

      public int apply$mcDFFI$sp(final double v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcDFFI$sp$(this, v, v2, v3);
      }

      public double apply$mcDFID$sp(final double v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcDFID$sp$(this, v, v2, v3);
      }

      public float apply$mcDFIF$sp(final double v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcDFIF$sp$(this, v, v2, v3);
      }

      public int apply$mcDFII$sp(final double v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcDFII$sp$(this, v, v2, v3);
      }

      public double apply$mcDIDD$sp(final double v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcDIDD$sp$(this, v, v2, v3);
      }

      public float apply$mcDIDF$sp(final double v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcDIDF$sp$(this, v, v2, v3);
      }

      public int apply$mcDIDI$sp(final double v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcDIDI$sp$(this, v, v2, v3);
      }

      public double apply$mcDIFD$sp(final double v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcDIFD$sp$(this, v, v2, v3);
      }

      public float apply$mcDIFF$sp(final double v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcDIFF$sp$(this, v, v2, v3);
      }

      public int apply$mcDIFI$sp(final double v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcDIFI$sp$(this, v, v2, v3);
      }

      public double apply$mcDIID$sp(final double v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcDIID$sp$(this, v, v2, v3);
      }

      public float apply$mcDIIF$sp(final double v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcDIIF$sp$(this, v, v2, v3);
      }

      public int apply$mcDIII$sp(final double v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcDIII$sp$(this, v, v2, v3);
      }

      public double apply$mcFDDD$sp(final float v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcFDDD$sp$(this, v, v2, v3);
      }

      public float apply$mcFDDF$sp(final float v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcFDDF$sp$(this, v, v2, v3);
      }

      public int apply$mcFDDI$sp(final float v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcFDDI$sp$(this, v, v2, v3);
      }

      public double apply$mcFDFD$sp(final float v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcFDFD$sp$(this, v, v2, v3);
      }

      public float apply$mcFDFF$sp(final float v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcFDFF$sp$(this, v, v2, v3);
      }

      public int apply$mcFDFI$sp(final float v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcFDFI$sp$(this, v, v2, v3);
      }

      public double apply$mcFDID$sp(final float v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcFDID$sp$(this, v, v2, v3);
      }

      public float apply$mcFDIF$sp(final float v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcFDIF$sp$(this, v, v2, v3);
      }

      public int apply$mcFDII$sp(final float v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcFDII$sp$(this, v, v2, v3);
      }

      public double apply$mcFFDD$sp(final float v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcFFDD$sp$(this, v, v2, v3);
      }

      public float apply$mcFFDF$sp(final float v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcFFDF$sp$(this, v, v2, v3);
      }

      public int apply$mcFFDI$sp(final float v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcFFDI$sp$(this, v, v2, v3);
      }

      public double apply$mcFFFD$sp(final float v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcFFFD$sp$(this, v, v2, v3);
      }

      public float apply$mcFFFF$sp(final float v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcFFFF$sp$(this, v, v2, v3);
      }

      public int apply$mcFFFI$sp(final float v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcFFFI$sp$(this, v, v2, v3);
      }

      public double apply$mcFFID$sp(final float v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcFFID$sp$(this, v, v2, v3);
      }

      public float apply$mcFFIF$sp(final float v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcFFIF$sp$(this, v, v2, v3);
      }

      public int apply$mcFFII$sp(final float v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcFFII$sp$(this, v, v2, v3);
      }

      public double apply$mcFIDD$sp(final float v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcFIDD$sp$(this, v, v2, v3);
      }

      public float apply$mcFIDF$sp(final float v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcFIDF$sp$(this, v, v2, v3);
      }

      public int apply$mcFIDI$sp(final float v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcFIDI$sp$(this, v, v2, v3);
      }

      public double apply$mcFIFD$sp(final float v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcFIFD$sp$(this, v, v2, v3);
      }

      public float apply$mcFIFF$sp(final float v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcFIFF$sp$(this, v, v2, v3);
      }

      public int apply$mcFIFI$sp(final float v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcFIFI$sp$(this, v, v2, v3);
      }

      public double apply$mcFIID$sp(final float v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcFIID$sp$(this, v, v2, v3);
      }

      public float apply$mcFIIF$sp(final float v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcFIIF$sp$(this, v, v2, v3);
      }

      public int apply$mcFIII$sp(final float v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcFIII$sp$(this, v, v2, v3);
      }

      public double apply$mcIDDD$sp(final int v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcIDDD$sp$(this, v, v2, v3);
      }

      public float apply$mcIDDF$sp(final int v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcIDDF$sp$(this, v, v2, v3);
      }

      public int apply$mcIDDI$sp(final int v, final double v2, final double v3) {
         return UFunc.UImpl3.apply$mcIDDI$sp$(this, v, v2, v3);
      }

      public double apply$mcIDFD$sp(final int v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcIDFD$sp$(this, v, v2, v3);
      }

      public float apply$mcIDFF$sp(final int v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcIDFF$sp$(this, v, v2, v3);
      }

      public int apply$mcIDFI$sp(final int v, final double v2, final float v3) {
         return UFunc.UImpl3.apply$mcIDFI$sp$(this, v, v2, v3);
      }

      public double apply$mcIDID$sp(final int v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcIDID$sp$(this, v, v2, v3);
      }

      public float apply$mcIDIF$sp(final int v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcIDIF$sp$(this, v, v2, v3);
      }

      public int apply$mcIDII$sp(final int v, final double v2, final int v3) {
         return UFunc.UImpl3.apply$mcIDII$sp$(this, v, v2, v3);
      }

      public double apply$mcIFDD$sp(final int v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcIFDD$sp$(this, v, v2, v3);
      }

      public float apply$mcIFDF$sp(final int v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcIFDF$sp$(this, v, v2, v3);
      }

      public int apply$mcIFDI$sp(final int v, final float v2, final double v3) {
         return UFunc.UImpl3.apply$mcIFDI$sp$(this, v, v2, v3);
      }

      public double apply$mcIFFD$sp(final int v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcIFFD$sp$(this, v, v2, v3);
      }

      public float apply$mcIFFF$sp(final int v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcIFFF$sp$(this, v, v2, v3);
      }

      public int apply$mcIFFI$sp(final int v, final float v2, final float v3) {
         return UFunc.UImpl3.apply$mcIFFI$sp$(this, v, v2, v3);
      }

      public double apply$mcIFID$sp(final int v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcIFID$sp$(this, v, v2, v3);
      }

      public float apply$mcIFIF$sp(final int v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcIFIF$sp$(this, v, v2, v3);
      }

      public int apply$mcIFII$sp(final int v, final float v2, final int v3) {
         return UFunc.UImpl3.apply$mcIFII$sp$(this, v, v2, v3);
      }

      public double apply$mcIIDD$sp(final int v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcIIDD$sp$(this, v, v2, v3);
      }

      public float apply$mcIIDF$sp(final int v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcIIDF$sp$(this, v, v2, v3);
      }

      public int apply$mcIIDI$sp(final int v, final int v2, final double v3) {
         return UFunc.UImpl3.apply$mcIIDI$sp$(this, v, v2, v3);
      }

      public double apply$mcIIFD$sp(final int v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcIIFD$sp$(this, v, v2, v3);
      }

      public float apply$mcIIFF$sp(final int v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcIIFF$sp$(this, v, v2, v3);
      }

      public int apply$mcIIFI$sp(final int v, final int v2, final float v3) {
         return UFunc.UImpl3.apply$mcIIFI$sp$(this, v, v2, v3);
      }

      public double apply$mcIIID$sp(final int v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcIIID$sp$(this, v, v2, v3);
      }

      public float apply$mcIIIF$sp(final int v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcIIIF$sp$(this, v, v2, v3);
      }

      public int apply$mcIIII$sp(final int v, final int v2, final int v3) {
         return UFunc.UImpl3.apply$mcIIII$sp$(this, v, v2, v3);
      }

      public eigSym.EigSym apply(final DenseMatrix M, final int s, final int nOversamples) {
         return evdr$.MODULE$.breeze$linalg$evdr$$doEigSymDouble(M, s, nOversamples, 0);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EVDR_DM_Impl3$.class);
      }
   }

   public static class EVDR_DM_Impl4$ implements UFunc.UImpl4 {
      public static final EVDR_DM_Impl4$ MODULE$ = new EVDR_DM_Impl4$();

      public double apply$mcDDDDD$sp(final double v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDDDF$sp(final double v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDDDI$sp(final double v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDDFD$sp(final double v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDDFF$sp(final double v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDDFI$sp(final double v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDDID$sp(final double v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDDIF$sp(final double v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDDII$sp(final double v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDFDD$sp(final double v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDFDF$sp(final double v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDFDI$sp(final double v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDFFD$sp(final double v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDFFF$sp(final double v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDFFI$sp(final double v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDFID$sp(final double v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDFIF$sp(final double v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDFII$sp(final double v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDIDD$sp(final double v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDIDF$sp(final double v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDIDI$sp(final double v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDDIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDIFD$sp(final double v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDIFF$sp(final double v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDIFI$sp(final double v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDDIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDDIID$sp(final double v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDDIIF$sp(final double v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDDIII$sp(final double v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDDIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFDDD$sp(final double v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFDDF$sp(final double v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFDDI$sp(final double v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFDFD$sp(final double v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFDFF$sp(final double v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFDFI$sp(final double v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFDID$sp(final double v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFDIF$sp(final double v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFDII$sp(final double v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFFDD$sp(final double v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFFDF$sp(final double v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFFDI$sp(final double v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFFFD$sp(final double v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFFFF$sp(final double v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFFFI$sp(final double v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFFID$sp(final double v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFFIF$sp(final double v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFFII$sp(final double v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFIDD$sp(final double v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFIDF$sp(final double v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFIDI$sp(final double v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDFIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFIFD$sp(final double v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFIFF$sp(final double v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFIFI$sp(final double v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDFIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDFIID$sp(final double v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDFIIF$sp(final double v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDFIII$sp(final double v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDFIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIDDD$sp(final double v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIDDF$sp(final double v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIDDI$sp(final double v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIDFD$sp(final double v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIDFF$sp(final double v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIDFI$sp(final double v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIDID$sp(final double v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIDIF$sp(final double v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIDII$sp(final double v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIFDD$sp(final double v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIFDF$sp(final double v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIFDI$sp(final double v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIFFD$sp(final double v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIFFF$sp(final double v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIFFI$sp(final double v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIFID$sp(final double v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIFIF$sp(final double v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIFII$sp(final double v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIIDD$sp(final double v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIIDF$sp(final double v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIIDI$sp(final double v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcDIIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIIFD$sp(final double v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIIFF$sp(final double v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIIFI$sp(final double v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcDIIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcDIIID$sp(final double v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcDIIIF$sp(final double v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcDIIII$sp(final double v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcDIIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDDDD$sp(final float v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDDDF$sp(final float v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDDDI$sp(final float v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDDFD$sp(final float v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDDFF$sp(final float v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDDFI$sp(final float v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDDID$sp(final float v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDDIF$sp(final float v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDDII$sp(final float v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDFDD$sp(final float v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDFDF$sp(final float v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDFDI$sp(final float v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDFFD$sp(final float v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDFFF$sp(final float v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDFFI$sp(final float v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDFID$sp(final float v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDFIF$sp(final float v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDFII$sp(final float v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDIDD$sp(final float v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDIDF$sp(final float v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDIDI$sp(final float v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFDIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDIFD$sp(final float v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDIFF$sp(final float v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDIFI$sp(final float v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFDIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFDIID$sp(final float v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFDIIF$sp(final float v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFDIII$sp(final float v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFDIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFDDD$sp(final float v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFDDF$sp(final float v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFDDI$sp(final float v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFDFD$sp(final float v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFDFF$sp(final float v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFDFI$sp(final float v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFDID$sp(final float v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFDIF$sp(final float v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFDII$sp(final float v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFFDD$sp(final float v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFFDF$sp(final float v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFFDI$sp(final float v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFFFD$sp(final float v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFFFF$sp(final float v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFFFI$sp(final float v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFFID$sp(final float v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFFIF$sp(final float v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFFII$sp(final float v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFIDD$sp(final float v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFIDF$sp(final float v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFIDI$sp(final float v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFFIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFIFD$sp(final float v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFIFF$sp(final float v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFIFI$sp(final float v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFFIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFFIID$sp(final float v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFFIIF$sp(final float v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFFIII$sp(final float v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFFIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIDDD$sp(final float v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIDDF$sp(final float v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIDDI$sp(final float v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIDFD$sp(final float v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIDFF$sp(final float v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIDFI$sp(final float v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIDID$sp(final float v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIDIF$sp(final float v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIDII$sp(final float v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIFDD$sp(final float v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIFDF$sp(final float v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIFDI$sp(final float v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIFFD$sp(final float v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIFFF$sp(final float v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIFFI$sp(final float v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIFID$sp(final float v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIFIF$sp(final float v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIFII$sp(final float v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIIDD$sp(final float v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIIDF$sp(final float v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIIDI$sp(final float v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcFIIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIIFD$sp(final float v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIIFF$sp(final float v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIIFI$sp(final float v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcFIIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcFIIID$sp(final float v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcFIIIF$sp(final float v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcFIIII$sp(final float v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcFIIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDDDD$sp(final int v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDDDF$sp(final int v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDDDI$sp(final int v, final double v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDDFD$sp(final int v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDDFF$sp(final int v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDDFI$sp(final int v, final double v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDDID$sp(final int v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDDIF$sp(final int v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDDII$sp(final int v, final double v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDFDD$sp(final int v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDFDF$sp(final int v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDFDI$sp(final int v, final double v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDFFD$sp(final int v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDFFF$sp(final int v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDFFI$sp(final int v, final double v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDFID$sp(final int v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDFIF$sp(final int v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDFII$sp(final int v, final double v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDIDD$sp(final int v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDIDF$sp(final int v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDIDI$sp(final int v, final double v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIDIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDIFD$sp(final int v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDIFF$sp(final int v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDIFI$sp(final int v, final double v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIDIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIDIID$sp(final int v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIDIIF$sp(final int v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIDIII$sp(final int v, final double v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIDIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFDDD$sp(final int v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFDDF$sp(final int v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFDDI$sp(final int v, final float v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFDFD$sp(final int v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFDFF$sp(final int v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFDFI$sp(final int v, final float v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFDID$sp(final int v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFDIF$sp(final int v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFDII$sp(final int v, final float v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFFDD$sp(final int v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFFDF$sp(final int v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFFDI$sp(final int v, final float v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFFFD$sp(final int v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFFFF$sp(final int v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFFFI$sp(final int v, final float v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFFID$sp(final int v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFFIF$sp(final int v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFFII$sp(final int v, final float v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFIDD$sp(final int v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFIDF$sp(final int v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFIDI$sp(final int v, final float v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIFIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFIFD$sp(final int v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFIFF$sp(final int v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFIFI$sp(final int v, final float v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIFIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIFIID$sp(final int v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIFIIF$sp(final int v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIFIII$sp(final int v, final float v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIFIII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIDDD$sp(final int v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIDDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIDDF$sp(final int v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIDDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIDDI$sp(final int v, final int v2, final double v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIDDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIDFD$sp(final int v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIDFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIDFF$sp(final int v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIDFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIDFI$sp(final int v, final int v2, final double v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIDFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIDID$sp(final int v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIDID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIDIF$sp(final int v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIDIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIDII$sp(final int v, final int v2, final double v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIDII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIFDD$sp(final int v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIFDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIFDF$sp(final int v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIFDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIFDI$sp(final int v, final int v2, final float v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIFDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIFFD$sp(final int v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIFFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIFFF$sp(final int v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIFFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIFFI$sp(final int v, final int v2, final float v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIFFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIFID$sp(final int v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIFID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIFIF$sp(final int v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIFIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIFII$sp(final int v, final int v2, final float v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIFII$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIIDD$sp(final int v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIIDD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIIDF$sp(final int v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIIDF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIIDI$sp(final int v, final int v2, final int v3, final double v4) {
         return UFunc.UImpl4.apply$mcIIIDI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIIFD$sp(final int v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIIFD$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIIFF$sp(final int v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIIFF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIIFI$sp(final int v, final int v2, final int v3, final float v4) {
         return UFunc.UImpl4.apply$mcIIIFI$sp$(this, v, v2, v3, v4);
      }

      public double apply$mcIIIID$sp(final int v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIIID$sp$(this, v, v2, v3, v4);
      }

      public float apply$mcIIIIF$sp(final int v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIIIF$sp$(this, v, v2, v3, v4);
      }

      public int apply$mcIIIII$sp(final int v, final int v2, final int v3, final int v4) {
         return UFunc.UImpl4.apply$mcIIIII$sp$(this, v, v2, v3, v4);
      }

      public eigSym.EigSym apply(final DenseMatrix M, final int s, final int nOversamples, final int nIter) {
         return evdr$.MODULE$.breeze$linalg$evdr$$doEigSymDouble(M, s, nOversamples, nIter);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(EVDR_DM_Impl4$.class);
      }
   }
}
