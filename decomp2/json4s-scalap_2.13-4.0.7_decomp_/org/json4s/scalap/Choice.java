package org.json4s.scalap;

import scala.Function0;
import scala.Function1;
import scala.Function2;
import scala.Function3;
import scala.Function4;
import scala.Function5;
import scala.Function6;
import scala.Function7;
import scala.MatchError;
import scala.PartialFunction;
import scala.collection.immutable.List;
import scala.collection.immutable.Nil;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014q!\u0002\u0004\u0011\u0002\u0007\u0005Q\u0002C\u0003.\u0001\u0011\u0005a\u0006C\u00033\u0001\u0019\u00051\u0007C\u0003A\u0001\u0011\u0005\u0011\tC\u0003H\u0001\u0011\u0005\u0003J\u0001\u0004DQ>L7-\u001a\u0006\u0003\u000f!\taa]2bY\u0006\u0004(BA\u0005\u000b\u0003\u0019Q7o\u001c85g*\t1\"A\u0002pe\u001e\u001c\u0001!F\u0003\u000f7\u0015B3fE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0002\f\u00183\u0011:#&D\u0001\u0007\u0013\tAbA\u0001\u0003Sk2,\u0007C\u0001\u000e\u001c\u0019\u0001!a\u0001\b\u0001\t\u0006\u0004i\"AA%o#\tq\u0012\u0005\u0005\u0002\u0011?%\u0011\u0001%\u0005\u0002\b\u001d>$\b.\u001b8h!\t\u0001\"%\u0003\u0002$#\t\u0019\u0011I\\=\u0011\u0005i)CA\u0002\u0014\u0001\t\u000b\u0007QDA\u0002PkR\u0004\"A\u0007\u0015\u0005\r%\u0002AQ1\u0001\u001e\u0005\u0005\t\u0005C\u0001\u000e,\t\u0019a\u0003\u0001\"b\u0001;\t\t\u0001,\u0001\u0004%S:LG\u000f\n\u000b\u0002_A\u0011\u0001\u0003M\u0005\u0003cE\u0011A!\u00168ji\u000691\r[8jG\u0016\u001cX#\u0001\u001b\u0011\u0007UjTC\u0004\u00027w9\u0011qGO\u0007\u0002q)\u0011\u0011\bD\u0001\u0007yI|w\u000e\u001e \n\u0003II!\u0001P\t\u0002\u000fA\f7m[1hK&\u0011ah\u0010\u0002\u0005\u0019&\u001cHO\u0003\u0002=#\u0005)\u0011\r\u001d9msR\u0011!)\u0012\t\u0006-\r#sEK\u0005\u0003\t\u001a\u0011aAU3tk2$\b\"\u0002$\u0004\u0001\u0004I\u0012AA5o\u0003\u0019y'/\u00127tKV)\u0011\n\u0014)U1R\u0011!j\u0017\t\u0007-]YujU,\u0011\u0005iaE!B'\u0005\u0005\u0004q%aA%oeE\u0011a$\u0007\t\u00035A#Q!\u0015\u0003C\u0002I\u0013AaT;ueE\u0011A%\t\t\u00035Q#Q!\u0016\u0003C\u0002Y\u0013!!\u0011\u001a\u0012\u0005\u001d\n\u0003C\u0001\u000eY\t\u0015IFA1\u0001[\u0005\tA&'\u0005\u0002+C!1A\f\u0002CA\u0002u\u000bQa\u001c;iKJ\u00042\u0001\u00050K\u0013\ty\u0016C\u0001\u0005=Eft\u0017-\\3?\u0001"
)
public interface Choice extends Rule {
   List choices();

   default Result apply(final Object in) {
      return this.oneOf$1(this.choices(), in);
   }

   default Rule orElse(final Function0 other) {
      return new Choice(other) {
         private List choices;
         private final Rules factory;
         private volatile boolean bitmap$0;
         // $FF: synthetic field
         private final Choice $outer;
         private final Function0 other$2;

         public Result apply(final Object in) {
            return Choice.super.apply(in);
         }

         public Rule orElse(final Function0 other) {
            return Choice.super.orElse(other);
         }

         public Rule as(final String name) {
            return Rule.as$(this, name);
         }

         public Rule flatMap(final Function1 fa2ruleb) {
            return Rule.flatMap$(this, fa2ruleb);
         }

         public Rule map(final Function1 fa2b) {
            return Rule.map$(this, fa2b);
         }

         public Rule filter(final Function1 f) {
            return Rule.filter$(this, f);
         }

         public Rule mapResult(final Function1 f) {
            return Rule.mapResult$(this, f);
         }

         public Rule orError() {
            return Rule.orError$(this);
         }

         public Rule $bar(final Function0 other) {
            return Rule.$bar$(this, other);
         }

         public Rule $up$up(final Function1 fa2b) {
            return Rule.$up$up$(this, fa2b);
         }

         public Rule $up$up$qmark(final PartialFunction pf) {
            return Rule.$up$up$qmark$(this, pf);
         }

         public Rule $qmark$qmark(final PartialFunction pf) {
            return Rule.$qmark$qmark$(this, pf);
         }

         public Rule $minus$up(final Object b) {
            return Rule.$minus$up$(this, b);
         }

         public Rule $bang$up(final Function1 fx2y) {
            return Rule.$bang$up$(this, fx2y);
         }

         public Rule $greater$greater(final Function1 fa2ruleb) {
            return Rule.$greater$greater$(this, fa2ruleb);
         }

         public Rule $greater$minus$greater(final Function1 fa2resultb) {
            return Rule.$greater$minus$greater$(this, fa2resultb);
         }

         public Rule $greater$greater$qmark(final PartialFunction pf) {
            return Rule.$greater$greater$qmark$(this, pf);
         }

         public Rule $greater$greater$amp(final Function1 fa2ruleb) {
            return Rule.$greater$greater$amp$(this, fa2ruleb);
         }

         public Rule $tilde(final Function0 next) {
            return Rule.$tilde$(this, next);
         }

         public Rule $tilde$minus(final Function0 next) {
            return Rule.$tilde$minus$(this, next);
         }

         public Rule $minus$tilde(final Function0 next) {
            return Rule.$minus$tilde$(this, next);
         }

         public Rule $tilde$plus$plus(final Function0 next) {
            return Rule.$tilde$plus$plus$(this, next);
         }

         public Rule $tilde$greater(final Function0 next) {
            return Rule.$tilde$greater$(this, next);
         }

         public Rule $less$tilde$colon(final Function0 prev) {
            return Rule.$less$tilde$colon$(this, prev);
         }

         public Rule $tilde$bang(final Function0 next) {
            return Rule.$tilde$bang$(this, next);
         }

         public Rule $tilde$minus$bang(final Function0 next) {
            return Rule.$tilde$minus$bang$(this, next);
         }

         public Rule $minus$tilde$bang(final Function0 next) {
            return Rule.$minus$tilde$bang$(this, next);
         }

         public Rule $minus(final Function0 exclude) {
            return Rule.$minus$(this, exclude);
         }

         public Rule $up$tilde$up(final Function2 f, final Function1 A) {
            return Rule.$up$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$up(final Function3 f, final Function1 A) {
            return Rule.$up$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$up(final Function4 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$tilde$up(final Function5 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$tilde$tilde$up(final Function6 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $up$tilde$tilde$tilde$tilde$tilde$tilde$up(final Function7 f, final Function1 A) {
            return Rule.$up$tilde$tilde$tilde$tilde$tilde$tilde$up$(this, f, A);
         }

         public Rule $greater$tilde$greater(final Function2 f, final Function1 A) {
            return Rule.$greater$tilde$greater$(this, f, A);
         }

         public Rule $up$minus$up(final Function2 f) {
            return Rule.$up$minus$up$(this, f);
         }

         public Rule $up$tilde$greater$tilde$up(final Function3 f, final Function1 A) {
            return Rule.$up$tilde$greater$tilde$up$(this, f, A);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public Function1 andThen(final Function1 g) {
            return Function1.andThen$(this, g);
         }

         public String toString() {
            return Function1.toString$(this);
         }

         public Rules factory() {
            return this.factory;
         }

         private List choices$lzycompute() {
            synchronized(this){}

            try {
               if (!this.bitmap$0) {
                  List var2 = this.$outer.choices();
                  Rule var3 = (Rule)this.other$2.apply();
                  this.choices = .MODULE$.Nil().$colon$colon(var3).$colon$colon$colon(var2);
                  this.bitmap$0 = true;
               }
            } catch (Throwable var5) {
               throw var5;
            }

            return this.choices;
         }

         public List choices() {
            return !this.bitmap$0 ? this.choices$lzycompute() : this.choices;
         }

         public {
            if (Choice.this == null) {
               throw null;
            } else {
               this.$outer = Choice.this;
               this.other$2 = other$2;
               Function1.$init$(this);
               Rule.$init$(this);
               Choice.$init$(this);
               this.factory = Choice.this.factory();
            }
         }
      };
   }

   private Result oneOf$1(final List list, final Object in$1) {
      Object var4;
      while(true) {
         Nil var10000 = .MODULE$.Nil();
         if (var10000 == null) {
            if (list == null) {
               break;
            }
         } else if (var10000.equals(list)) {
            break;
         }

         if (!(list instanceof scala.collection.immutable..colon.colon)) {
            throw new MatchError(list);
         }

         scala.collection.immutable..colon.colon var8 = (scala.collection.immutable..colon.colon)list;
         Rule first = (Rule)var8.head();
         List rest = var8.next$access$1();
         Result var11 = (Result)first.apply(in$1);
         if (!Failure$.MODULE$.equals(var11)) {
            var4 = var11;
            return (Result)var4;
         }

         list = rest;
      }

      var4 = Failure$.MODULE$;
      return (Result)var4;
   }

   static void $init$(final Choice $this) {
   }
}
