package scala.reflect.internal;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function2;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055e!C\r\u001b!\u0003\r\t!IAD\u0011\u00151\u0003\u0001\"\u0001(\r\u001dY\u0003\u0001%A\u0002\u00021BQA\n\u0002\u0005\u0002\u001dBQ!\f\u0002\u0005\u00029BQA\r\u0002\u0007\u0002MBQA\u0010\u0002\u0005\u0002}BQa\u0013\u0002\u0005\u00021CQa\u0014\u0002\u0005\u0002ACQ!\u0019\u0002\u0005\u0002\tDQA\u001e\u0002\u0005\u0002]Dq!!\u0003\u0003\t\u0003\tY\u0001C\u0004\u0002\u001a\t!\t!a\u0007\t\u0013\u0005E\u0002\u00011Q\u0005\n\u0005M\u0002\"CA\u001d\u0001\u0001\u0007K\u0011BA\u001e\u0011\u001d\t\t\u0005\u0001C\u0001\u0003\u0007Ba!!\u0013\u0001\t\u00039\u0003B\u0002\u001a\u0001\t\u0003\tY\u0005\u0003\u0004?\u0001\u0011\u0005\u0011Q\u000b\u0005\u0007\u0017\u0002!\t!a\u0017\t\r=\u0003A\u0011AA1\u0011\u0019\t\u0007\u0001\"\u0001\u0002j!1a\u000f\u0001C\u0001\u0003_Bq!!\u0003\u0001\t\u0003\t9\bC\u0004\u0002\u001a\u0001!\t!a \u0003%\u0005sgn\u001c;bi&|gn\u00115fG.,'o\u001d\u0006\u00037q\t\u0001\"\u001b8uKJt\u0017\r\u001c\u0006\u0003;y\tqA]3gY\u0016\u001cGOC\u0001 \u0003\u0015\u00198-\u00197b\u0007\u0001\u0019\"\u0001\u0001\u0012\u0011\u0005\r\"S\"\u0001\u0010\n\u0005\u0015r\"AB!osJ+g-\u0001\u0004%S:LG\u000f\n\u000b\u0002QA\u00111%K\u0005\u0003Uy\u0011A!\u00168ji\n\t\u0012I\u001c8pi\u0006$\u0018n\u001c8DQ\u0016\u001c7.\u001a:\u0014\u0005\t\u0011\u0013\u0001C5t\u0003\u000e$\u0018N^3\u0015\u0003=\u0002\"a\t\u0019\n\u0005Er\"a\u0002\"p_2,\u0017M\\\u0001\u0013C:tw\u000e^1uS>t7oQ8oM>\u0014X\u000eF\u00020iqBQ!N\u0003A\u0002Y\nA\u0001\u001e9fcA\u0011q\u0007O\u0007\u0002\u0001%\u0011\u0011H\u000f\u0002\u0005)f\u0004X-\u0003\u0002<5\t)A+\u001f9fg\")Q(\u0002a\u0001m\u0005!A\u000f]33\u00039\tgN\\8uCRLwN\\:Mk\n$2A\u000e!C\u0011\u0015\te\u00011\u00017\u0003\t!\b\u000fC\u0003D\r\u0001\u0007A)\u0001\u0002ugB\u0019Q\t\u0013\u001c\u000f\u0005\r2\u0015BA$\u001f\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0013&\u0003\t1K7\u000f\u001e\u0006\u0003\u000fz\ta\"\u00198o_R\fG/[8og\u001ec'\rF\u00027\u001b:CQ!Q\u0004A\u0002YBQaQ\u0004A\u0002\u0011\u000b\u0001$\u00193baR\u0014u.\u001e8egR{\u0017I\u001c8pi\u0006$\u0018n\u001c8t)\u0011\tVkV0\u0011\u0007\u0015C%\u000b\u0005\u00028'&\u0011AK\u000f\u0002\u000b)f\u0004XMQ8v]\u0012\u001c\b\"\u0002,\t\u0001\u0004\t\u0016A\u00022pk:$7\u000fC\u0003Y\u0011\u0001\u0007\u0011,A\u0004ua\u0006\u0014\u0018-\\:\u0011\u0007\u0015C%\f\u0005\u000287&\u0011A,\u0018\u0002\u0007'fl'm\u001c7\n\u0005yS\"aB*z[\n|Gn\u001d\u0005\u0006A\"\u0001\r\u0001R\u0001\u0006i\u0006\u0014xm]\u0001\u000fC\u0012$\u0017I\u001c8pi\u0006$\u0018n\u001c8t)\r14M\u001b\u0005\u0006I&\u0001\r!Z\u0001\u0005iJ,W\r\u0005\u00028M&\u0011q\r\u001b\u0002\u0005)J,W-\u0003\u0002j5\t)AK]3fg\")1.\u0003a\u0001m\u0005\u0019A\u000f]3)\r%i\u0007/]:u!\t\u0019c.\u0003\u0002p=\t!B-\u001a9sK\u000e\fG/\u001a3Pm\u0016\u0014(/\u001b3j]\u001e\fq!\\3tg\u0006<W-I\u0001s\u00035\u001a'/Z1uK\u0002\ng\u000eI!oC2L(0\u001a:QYV<\u0017N\u001c\u0011b]\u0012\u0004So]3!a2,x-\u001b8t)f\u0004X\rZ\u0001\u0006g&t7-Z\u0011\u0002k\u00061!GL\u00191]E\n1cY1o\u0003\u0012\f\u0007\u000f^!o]>$\u0018\r^5p]N$Ba\f=z\u007f\")AM\u0003a\u0001K\")!P\u0003a\u0001w\u0006!Qn\u001c3f!\taX0D\u0001\u001b\u0013\tq(D\u0001\u0003N_\u0012,\u0007BBA\u0001\u0015\u0001\u0007a'\u0001\u0002qi\":!\"\u001c9\u0002\u0006M$\u0018EAA\u0004\u0003Q\u001a'/Z1uK\u0002\ng\u000eI!oC2L(0\u001a:QYV<\u0017N\u001c\u0011b]\u0012\u0004So]3!G\u0006t\u0017\tZ1qi\u0006sgn\u001c;bi&|gn]\u0001\u0011C\u0012\f\u0007\u000f^!o]>$\u0018\r^5p]N$r!ZA\u0007\u0003\u001f\t\t\u0002C\u0003e\u0017\u0001\u0007Q\rC\u0003{\u0017\u0001\u00071\u0010\u0003\u0004\u0002\u0002-\u0001\rA\u000e\u0015\b\u00175\u0004\u0018QC:uC\t\t9\"A\u0019de\u0016\fG/\u001a\u0011b]\u0002\ne.\u00197zu\u0016\u0014\b\u000b\\;hS:\u0004\u0013M\u001c3!kN,\u0007%\u00193baR\feN\\8uCRLwN\\:\u0002#\u0005$\u0017\r\u001d;UsB,wJ\u001a*fiV\u0014h\u000eF\u00047\u0003;\ty\"!\t\t\u000b\u0011d\u0001\u0019A3\t\r\u0005\u0005A\u00021\u00017\u0011!\t\u0019\u0003\u0004CA\u0002\u0005\u0015\u0012a\u00023fM\u0006,H\u000e\u001e\t\u0005G\u0005\u001db'C\u0002\u0002*y\u0011\u0001\u0002\u00102z]\u0006lWM\u0010\u0015\b\u00195\u0004\u0018QF:uC\t\ty#AA2\u0007J,\u0017\r^3!C:\u0004\u0013I\\1msj,'\u000f\u00157vO&t\u0007%\u00198eAU\u001cX\r\t9mk\u001eLgn\u001d+za\u0016$'+\u001a;ve:t\u0003ET8uKj\u0002C\u000f[3!OQ\u0014X-Z\u0014!CJ<W/\\3oi\u0002BWM]3!SNTA\u000f[3!O\u0015D\bO]\u0014!_\u001a\u0004\u0013\r\t*fiV\u0014h\u000e\t;sK\u0016\\\u0004e\n9mk\u001eLgn\u001d+za\u0016$'+\u001a;ve:<\u0003\u0005^1lKN\u0004C\u000f[3!%\u0016$XO\u001d8!iJ,W\rI5ug\u0016dg\rI1tA\u0005\u0014x-^7f]R\f!#\u00198o_R\fG/[8o\u0007\",7m[3sgV\u0011\u0011Q\u0007\t\u0005\u000b\"\u000b9\u0004\u0005\u00028\u0005\u00051\u0012M\u001c8pi\u0006$\u0018n\u001c8DQ\u0016\u001c7.\u001a:t?\u0012*\u0017\u000fF\u0002)\u0003{A\u0011\"a\u0010\u000f\u0003\u0003\u0005\r!!\u000e\u0002\u0007a$\u0013'\u0001\u000bbI\u0012\feN\\8uCRLwN\\\"iK\u000e\\WM\u001d\u000b\u0004Q\u0005\u0015\u0003bBA$\u001f\u0001\u0007\u0011qG\u0001\bG\",7m[3s\u0003m\u0011X-\\8wK\u0006cG.\u00118o_R\fG/[8o\u0007\",7m[3sgR)q&!\u0014\u0002R!1\u0011qJ\tA\u0002Y\n1\u0001\u001e92\u0011\u0019\t\u0019&\u0005a\u0001m\u0005\u0019A\u000f\u001d\u001a\u0015\u000bY\n9&!\u0017\t\u000b-\u0014\u0002\u0019\u0001\u001c\t\u000b\r\u0013\u0002\u0019\u0001#\u0015\u000bY\ni&a\u0018\t\u000b-\u001c\u0002\u0019\u0001\u001c\t\u000b\r\u001b\u0002\u0019\u0001#\u0015\u000fE\u000b\u0019'!\u001a\u0002h!)a\u000b\u0006a\u0001#\")\u0001\f\u0006a\u00013\")\u0001\r\u0006a\u0001\tR)a'a\u001b\u0002n!)A-\u0006a\u0001K\")1.\u0006a\u0001mQ9q&!\u001d\u0002t\u0005U\u0004\"\u00023\u0017\u0001\u0004)\u0007\"\u0002>\u0017\u0001\u0004Y\bBBA\u0001-\u0001\u0007a\u0007F\u0004f\u0003s\nY(! \t\u000b\u0011<\u0002\u0019A3\t\u000bi<\u0002\u0019A>\t\r\u0005\u0005q\u00031\u00017)\u001d1\u0014\u0011QAB\u0003\u000bCQ\u0001\u001a\rA\u0002\u0015Da!!\u0001\u0019\u0001\u00041\u0004\u0002CA\u00121\u0011\u0005\r!!\n\u0011\u0007q\fI)C\u0002\u0002\fj\u00111bU=nE>dG+\u00192mK\u0002"
)
public interface AnnotationCheckers {
   List scala$reflect$internal$AnnotationCheckers$$annotationCheckers();

   void scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(final List x$1);

   // $FF: synthetic method
   static void addAnnotationChecker$(final AnnotationCheckers $this, final AnnotationChecker checker) {
      $this.addAnnotationChecker(checker);
   }

   default void addAnnotationChecker(final AnnotationChecker checker) {
      if (!this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().contains(checker)) {
         List var10001 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         if (var10001 == null) {
            throw null;
         } else {
            List $colon$colon_this = var10001;
            .colon.colon var4 = new .colon.colon(checker, $colon$colon_this);
            Object var3 = null;
            this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(var4);
         }
      }
   }

   // $FF: synthetic method
   static void removeAllAnnotationCheckers$(final AnnotationCheckers $this) {
      $this.removeAllAnnotationCheckers();
   }

   default void removeAllAnnotationCheckers() {
      this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   static boolean annotationsConform$(final AnnotationCheckers $this, final Types.Type tp1, final Types.Type tp2) {
      return $this.annotationsConform(tp1, tp2);
   }

   default boolean annotationsConform(final Types.Type tp1, final Types.Type tp2) {
      if (!this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty() && (!tp1.annotations().isEmpty() || !tp2.annotations().isEmpty())) {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         if (var10000 == null) {
            throw null;
         } else {
            for(List forall_these = var10000; !forall_these.isEmpty(); forall_these = (List)forall_these.tail()) {
               AnnotationChecker var4 = (AnnotationChecker)forall_these.head();
               if (!$anonfun$annotationsConform$1(tp1, tp2, var4)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return true;
      }
   }

   // $FF: synthetic method
   static Types.Type annotationsLub$(final AnnotationCheckers $this, final Types.Type tpe, final List ts) {
      return $this.annotationsLub(tpe, ts);
   }

   default Types.Type annotationsLub(final Types.Type tpe, final List ts) {
      if (this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty()) {
         return tpe;
      } else {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         Function2 foldLeft_op = (tpex, checker) -> !checker.isActive() ? tpex : checker.annotationsLub(tpex, ts);
         if (var10000 == null) {
            throw null;
         } else {
            return (Types.Type)LinearSeqOps.foldLeft$(var10000, tpe, foldLeft_op);
         }
      }
   }

   // $FF: synthetic method
   static Types.Type annotationsGlb$(final AnnotationCheckers $this, final Types.Type tpe, final List ts) {
      return $this.annotationsGlb(tpe, ts);
   }

   default Types.Type annotationsGlb(final Types.Type tpe, final List ts) {
      if (this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty()) {
         return tpe;
      } else {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         Function2 foldLeft_op = (tpex, checker) -> !checker.isActive() ? tpex : checker.annotationsGlb(tpex, ts);
         if (var10000 == null) {
            throw null;
         } else {
            return (Types.Type)LinearSeqOps.foldLeft$(var10000, tpe, foldLeft_op);
         }
      }
   }

   // $FF: synthetic method
   static List adaptBoundsToAnnotations$(final AnnotationCheckers $this, final List bounds, final List tparams, final List targs) {
      return $this.adaptBoundsToAnnotations(bounds, tparams, targs);
   }

   default List adaptBoundsToAnnotations(final List bounds, final List tparams, final List targs) {
      if (this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty()) {
         return bounds;
      } else {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         Function2 foldLeft_op = (boundsx, checker) -> !checker.isActive() ? boundsx : checker.adaptBoundsToAnnotations(boundsx, tparams, targs);
         if (var10000 == null) {
            throw null;
         } else {
            return (List)LinearSeqOps.foldLeft$(var10000, bounds, foldLeft_op);
         }
      }
   }

   // $FF: synthetic method
   static Types.Type addAnnotations$(final AnnotationCheckers $this, final Trees.Tree tree, final Types.Type tpe) {
      return $this.addAnnotations(tree, tpe);
   }

   default Types.Type addAnnotations(final Trees.Tree tree, final Types.Type tpe) {
      if (this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty()) {
         return tpe;
      } else {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         Function2 foldLeft_op = (tpex, checker) -> !checker.isActive() ? tpex : checker.addAnnotations(tree, tpex);
         if (var10000 == null) {
            throw null;
         } else {
            return (Types.Type)LinearSeqOps.foldLeft$(var10000, tpe, foldLeft_op);
         }
      }
   }

   // $FF: synthetic method
   static boolean canAdaptAnnotations$(final AnnotationCheckers $this, final Trees.Tree tree, final int mode, final Types.Type pt) {
      return $this.canAdaptAnnotations(tree, mode, pt);
   }

   default boolean canAdaptAnnotations(final Trees.Tree tree, final int mode, final Types.Type pt) {
      if (this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty()) {
         return false;
      } else {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         if (var10000 == null) {
            throw null;
         } else {
            for(List exists_these = var10000; !exists_these.isEmpty(); exists_these = (List)exists_these.tail()) {
               AnnotationChecker var5 = (AnnotationChecker)exists_these.head();
               if ($anonfun$canAdaptAnnotations$1(tree, mode, pt, var5)) {
                  return true;
               }
            }

            return false;
         }
      }
   }

   // $FF: synthetic method
   static Trees.Tree adaptAnnotations$(final AnnotationCheckers $this, final Trees.Tree tree, final int mode, final Types.Type pt) {
      return $this.adaptAnnotations(tree, mode, pt);
   }

   default Trees.Tree adaptAnnotations(final Trees.Tree tree, final int mode, final Types.Type pt) {
      if (this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty()) {
         return tree;
      } else {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         Function2 foldLeft_op = (treex, checker) -> !checker.isActive() ? treex : checker.adaptAnnotations(treex, mode, pt);
         if (var10000 == null) {
            throw null;
         } else {
            return (Trees.Tree)LinearSeqOps.foldLeft$(var10000, tree, foldLeft_op);
         }
      }
   }

   // $FF: synthetic method
   static Types.Type adaptTypeOfReturn$(final AnnotationCheckers $this, final Trees.Tree tree, final Types.Type pt, final Function0 default) {
      return $this.adaptTypeOfReturn(tree, pt, default);
   }

   default Types.Type adaptTypeOfReturn(final Trees.Tree tree, final Types.Type pt, final Function0 default) {
      if (this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers().isEmpty()) {
         return (Types.Type)default.apply();
      } else {
         List var10000 = this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers();
         Object var10001 = default.apply();
         Function2 foldLeft_op = (tpe, checker) -> !checker.isActive() ? tpe : checker.adaptTypeOfReturn(tree, pt, () -> tpe);
         Object foldLeft_z = var10001;
         if (var10000 == null) {
            throw null;
         } else {
            return (Types.Type)LinearSeqOps.foldLeft$(var10000, foldLeft_z, foldLeft_op);
         }
      }
   }

   // $FF: synthetic method
   static boolean $anonfun$annotationsConform$1(final Types.Type tp1$1, final Types.Type tp2$1, final AnnotationChecker checker) {
      return !checker.isActive() || checker.annotationsConform(tp1$1, tp2$1);
   }

   // $FF: synthetic method
   static boolean $anonfun$canAdaptAnnotations$1(final Trees.Tree tree$2, final int mode$1, final Types.Type pt$1, final AnnotationChecker checker) {
      return checker.isActive() && checker.canAdaptAnnotations(tree$2, mode$1, pt$1);
   }

   static void $init$(final AnnotationCheckers $this) {
      $this.scala$reflect$internal$AnnotationCheckers$$annotationCheckers_$eq(scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   static Object $anonfun$annotationsConform$1$adapted(final Types.Type tp1$1, final Types.Type tp2$1, final AnnotationChecker checker) {
      return BoxesRunTime.boxToBoolean($anonfun$annotationsConform$1(tp1$1, tp2$1, checker));
   }

   // $FF: synthetic method
   static Object $anonfun$canAdaptAnnotations$1$adapted(final Trees.Tree tree$2, final int mode$1, final Types.Type pt$1, final AnnotationChecker checker) {
      return BoxesRunTime.boxToBoolean($anonfun$canAdaptAnnotations$1(tree$2, mode$1, pt$1, checker));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public interface AnnotationChecker {
      // $FF: synthetic method
      static boolean isActive$(final AnnotationChecker $this) {
         return $this.isActive();
      }

      default boolean isActive() {
         return true;
      }

      boolean annotationsConform(final Types.Type tpe1, final Types.Type tpe2);

      // $FF: synthetic method
      static Types.Type annotationsLub$(final AnnotationChecker $this, final Types.Type tp, final List ts) {
         return $this.annotationsLub(tp, ts);
      }

      default Types.Type annotationsLub(final Types.Type tp, final List ts) {
         return tp;
      }

      // $FF: synthetic method
      static Types.Type annotationsGlb$(final AnnotationChecker $this, final Types.Type tp, final List ts) {
         return $this.annotationsGlb(tp, ts);
      }

      default Types.Type annotationsGlb(final Types.Type tp, final List ts) {
         return tp;
      }

      // $FF: synthetic method
      static List adaptBoundsToAnnotations$(final AnnotationChecker $this, final List bounds, final List tparams, final List targs) {
         return $this.adaptBoundsToAnnotations(bounds, tparams, targs);
      }

      default List adaptBoundsToAnnotations(final List bounds, final List tparams, final List targs) {
         return bounds;
      }

      // $FF: synthetic method
      static Types.Type addAnnotations$(final AnnotationChecker $this, final Trees.Tree tree, final Types.Type tpe) {
         return $this.addAnnotations(tree, tpe);
      }

      default Types.Type addAnnotations(final Trees.Tree tree, final Types.Type tpe) {
         return tpe;
      }

      // $FF: synthetic method
      static boolean canAdaptAnnotations$(final AnnotationChecker $this, final Trees.Tree tree, final int mode, final Types.Type pt) {
         return $this.canAdaptAnnotations(tree, mode, pt);
      }

      default boolean canAdaptAnnotations(final Trees.Tree tree, final int mode, final Types.Type pt) {
         return false;
      }

      // $FF: synthetic method
      static Trees.Tree adaptAnnotations$(final AnnotationChecker $this, final Trees.Tree tree, final int mode, final Types.Type pt) {
         return $this.adaptAnnotations(tree, mode, pt);
      }

      default Trees.Tree adaptAnnotations(final Trees.Tree tree, final int mode, final Types.Type pt) {
         return tree;
      }

      // $FF: synthetic method
      static Types.Type adaptTypeOfReturn$(final AnnotationChecker $this, final Trees.Tree tree, final Types.Type pt, final Function0 default) {
         return $this.adaptTypeOfReturn(tree, pt, default);
      }

      default Types.Type adaptTypeOfReturn(final Trees.Tree tree, final Types.Type pt, final Function0 default) {
         return (Types.Type)default.apply();
      }

      // $FF: synthetic method
      AnnotationCheckers scala$reflect$internal$AnnotationCheckers$AnnotationChecker$$$outer();

      static void $init$(final AnnotationChecker $this) {
      }
   }
}
