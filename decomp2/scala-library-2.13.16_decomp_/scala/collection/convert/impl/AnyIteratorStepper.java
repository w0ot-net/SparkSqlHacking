package scala.collection.convert.impl;

import java.util.Iterator;
import java.util.Spliterator;
import scala.collection.AnyStepper;
import scala.collection.Stepper;
import scala.collection.StepperShape;
import scala.collection.StepperShape$;
import scala.jdk.Accumulator;
import scala.jdk.AnyAccumulator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2QAB\u0004\u0001\u0017=A\u0011b\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u001b\t\u000bY\u0002A\u0011A\u001c\t\u000be\u0002A\u0011\u0003\u001e\t\u000bm\u0002A\u0011\u0001\u001f\t\u000bu\u0002A\u0011\u0001 \u0003%\u0005s\u00170\u0013;fe\u0006$xN]*uKB\u0004XM\u001d\u0006\u0003\u0011%\tA![7qY*\u0011!bC\u0001\bG>tg/\u001a:u\u0015\taQ\"\u0001\u0006d_2dWm\u0019;j_:T\u0011AD\u0001\u0006g\u000e\fG.Y\u000b\u0003!]\u00192\u0001A\t#!\u0015\u00112#\u0006\u0012'\u001b\u00059\u0011B\u0001\u000b\b\u0005MIE/\u001a:bi>\u00148\u000b^3qa\u0016\u0014()Y:f!\t1r\u0003\u0004\u0001\u0005\u000ba\u0001!\u0019\u0001\u000e\u0003\u0003\u0005\u001b\u0001!\u0005\u0002\u001c?A\u0011A$H\u0007\u0002\u001b%\u0011a$\u0004\u0002\b\u001d>$\b.\u001b8h!\ta\u0002%\u0003\u0002\"\u001b\t\u0019\u0011I\\=\u0011\u0007\r\"S#D\u0001\f\u0013\t)3B\u0001\u0006B]f\u001cF/\u001a9qKJ\u00042A\u0005\u0001\u0016\u0003-yVO\u001c3fe2L\u0018N\\4\u0011\u0007%\nTC\u0004\u0002+_9\u00111FL\u0007\u0002Y)\u0011Q&G\u0001\u0007yI|w\u000e\u001e \n\u00039I!\u0001M\u0007\u0002\u000fA\f7m[1hK&\u0011!g\r\u0002\t\u0013R,'/\u0019;pe*\u0011\u0001'D\u0005\u0003kM\t!\"\u001e8eKJd\u00170\u001b8h\u0003\u0019a\u0014N\\5u}Q\u0011a\u0005\u000f\u0005\u0006O\t\u0001\r\u0001K\u0001\ng\u0016l\u0017n\u00197p]\u0016$\u0012AJ\u0001\t]\u0016DHo\u0015;faR\tQ#\u0001\u0005uef\u001c\u0006\u000f\\5u)\u0005\u0011\u0003"
)
public class AnyIteratorStepper extends IteratorStepperBase implements AnyStepper {
   public Spliterator spliterator() {
      return AnyStepper.spliterator$(this);
   }

   public Iterator javaIterator() {
      return AnyStepper.javaIterator$(this);
   }

   public double nextStep$mcD$sp() {
      return Stepper.nextStep$mcD$sp$(this);
   }

   public int nextStep$mcI$sp() {
      return Stepper.nextStep$mcI$sp$(this);
   }

   public long nextStep$mcJ$sp() {
      return Stepper.nextStep$mcJ$sp$(this);
   }

   public Stepper trySplit$mcD$sp() {
      return Stepper.trySplit$mcD$sp$(this);
   }

   public Stepper trySplit$mcI$sp() {
      return Stepper.trySplit$mcI$sp$(this);
   }

   public Stepper trySplit$mcJ$sp() {
      return Stepper.trySplit$mcJ$sp$(this);
   }

   public Spliterator spliterator$mcD$sp() {
      return Stepper.spliterator$mcD$sp$(this);
   }

   public Spliterator spliterator$mcI$sp() {
      return Stepper.spliterator$mcI$sp$(this);
   }

   public Spliterator spliterator$mcJ$sp() {
      return Stepper.spliterator$mcJ$sp$(this);
   }

   public Iterator javaIterator$mcD$sp() {
      return Stepper.javaIterator$mcD$sp$(this);
   }

   public Iterator javaIterator$mcI$sp() {
      return Stepper.javaIterator$mcI$sp$(this);
   }

   public Iterator javaIterator$mcJ$sp() {
      return Stepper.javaIterator$mcJ$sp$(this);
   }

   public scala.collection.Iterator iterator() {
      return Stepper.iterator$(this);
   }

   public AnyIteratorStepper semiclone() {
      return new AnyIteratorStepper((scala.collection.Iterator)null);
   }

   public Object nextStep() {
      return this.proxied() != null ? this.proxied().nextStep() : this.underlying().next();
   }

   public AnyStepper trySplit() {
      if (this.proxied() != null) {
         return ((AnyStepper)this.proxied()).trySplit();
      } else {
         AnyAccumulator acc = new AnyAccumulator();
         int i = 0;

         int n;
         for(n = this.nextChunkSize() & -4; i < n && this.underlying().hasNext(); ++i) {
            Object $plus$eq_elem = this.underlying().next();
            acc.addOne($plus$eq_elem);
            $plus$eq_elem = null;
         }

         if (i >= n && this.underlying().hasNext()) {
            AnyIteratorStepper ans = this.semiclone();
            StepperShape$ anyStepperShape_this = StepperShape$.MODULE$;
            StepperShape var16 = anyStepperShape_this.anyStepperShapePrototype();
            Object var12 = null;
            StepperShape stepper_shape = var16;
            Stepper var17 = ((Accumulator)acc).efficientStepper(stepper_shape);
            stepper_shape = null;
            ans.proxied_$eq(var17);
            this.nextChunkSize_$eq((this.nextChunkSize() & 3) == 3 ? (n < 1073741824 ? n * 2 : n) : this.nextChunkSize() + 1);
            return ans;
         } else {
            StepperShape$ anyStepperShape_this = StepperShape$.MODULE$;
            StepperShape var10001 = anyStepperShape_this.anyStepperShapePrototype();
            Object var11 = null;
            StepperShape stepper_shape = var10001;
            Stepper var15 = ((Accumulator)acc).efficientStepper(stepper_shape);
            stepper_shape = null;
            this.proxied_$eq(var15);
            return ((AnyStepper)this.proxied()).trySplit();
         }
      }
   }

   public AnyIteratorStepper(final scala.collection.Iterator _underlying) {
      super(_underlying);
   }
}
