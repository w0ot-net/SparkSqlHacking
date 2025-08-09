package spire.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Exprs;
import scala.reflect.api.TypeTags;
import scala.reflect.macros.whitebox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}x!B\u0007\u000f\u0011\u0003\u0019b!B\u000b\u000f\u0011\u00031\u0002\"B\u000f\u0002\t\u0003q\u0002\"B\u0010\u0002\t\u0003\u0001\u0003\"B,\u0002\t\u0003A\u0006\"\u00027\u0002\t\u0003i\u0007\"\u0002@\u0002\t\u0003y\bbBA\u0012\u0003\u0011\u0005\u0011Q\u0005\u0005\b\u0003/\nA\u0011AA-\u0011\u001d\t))\u0001C\u0001\u0003\u000fCq!a(\u0002\t\u0003\t\t\u000bC\u0004\u0002>\u0006!\t!a0\t\u000f\u0005m\u0017\u0001\"\u0001\u0002^\u0006y1kY1mC\u0006+Ho\\'bGJ|7O\u0003\u0002\u0010!\u00051Q.Y2s_NT\u0011!E\u0001\u0006gBL'/Z\u0002\u0001!\t!\u0012!D\u0001\u000f\u0005=\u00196-\u00197b\u0003V$x.T1de>\u001c8CA\u0001\u0018!\tA2$D\u0001\u001a\u0015\u0005Q\u0012!B:dC2\f\u0017B\u0001\u000f\u001a\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012aE\u0001\rg\u0016l\u0017N]5oO&k\u0007\u000f\\\u000b\u0003C%#\"A\t\u0014\u0015\u0005\r\u0012\u0006c\u0001\u00137{9\u0011QE\n\u0007\u0001\u0011\u001593\u00011\u0001)\u0003\u0005\u0019\u0007CA\u00154\u001d\tQ\u0013G\u0004\u0002,a9\u0011AfL\u0007\u0002[)\u0011aFE\u0001\u0007yI|w\u000e\u001e \n\u0003EI!a\u0004\t\n\u0005Ir\u0011AB2p[B\fG/\u0003\u00025k\t91i\u001c8uKb$(B\u0001\u001a\u000f\u0013\t9\u0004H\u0001\u0003FqB\u0014\u0018BA\u001d;\u0005\u001d\tE.[1tKNT!aD\u001e\u000b\u0005qJ\u0012a\u0002:fM2,7\r\u001e\t\u0004}\u0015CeBA C\u001d\tY\u0003)\u0003\u0002B!\u00059\u0011\r\\4fEJ\f\u0017BA\"E\u0003\u001d\u0001\u0018mY6bO\u0016T!!\u0011\t\n\u0005\u0019;%\u0001C*f[&\u0014\u0018N\\4\u000b\u0005\r#\u0005CA\u0013J\t\u0015Q5A1\u0001L\u0005\u0005\t\u0015C\u0001'P!\tAR*\u0003\u0002O3\t9aj\u001c;iS:<\u0007C\u0001\rQ\u0013\t\t\u0016DA\u0002B]fDqaU\u0002\u0002\u0002\u0003\u000fA+A\u0006fm&$WM\\2fIQ\u0002\u0004c\u0001\u0013V\u0011&\u0011a\u000b\u000f\u0002\f/\u0016\f7\u000eV=qKR\u000bw-A\u0004sS\u001eLU\u000e\u001d7\u0016\u0005e\u001bGC\u0001._)\rYvM\u001b\u000b\u00039\u0012\u00042!\u0018\u001c`\u001d\t)c\fC\u0003(\t\u0001\u0007\u0001\u0006E\u0002?A\nL!!Y$\u0003\u0007IKw\r\u0005\u0002&G\u0012)!\n\u0002b\u0001\u0017\"9Q\rBA\u0001\u0002\b1\u0017aC3wS\u0012,gnY3%iE\u00022!X+c\u0011\u0015AG\u00011\u0001j\u0003\u0005Q\bcA/7E\")1\u000e\u0002a\u0001S\u0006\tq.A\u0004s]\u001eLU\u000e\u001d7\u0016\u00059DHCA8t)\t\u0001H\u0010\u0006\u0002rsB\u0019!O\u000e;\u000f\u0005\u0015\u001a\b\"B\u0014\u0006\u0001\u0004A\u0003c\u0001 vo&\u0011ao\u0012\u0002\u0004%:<\u0007CA\u0013y\t\u0015QUA1\u0001L\u0011\u001dQX!!AA\u0004m\f1\"\u001a<jI\u0016t7-\u001a\u00135eA\u0019!/V<\t\u000b!,\u0001\u0019A?\u0011\u0007I4t/\u0001\u0005sS:<\u0017*\u001c9m+\u0011\t\t!!\u0006\u0015\t\u0005\r\u00111\u0002\u000b\u0007\u0003\u000b\ti\"!\t\u0015\t\u0005\u001d\u0011q\u0003\t\u0006\u0003\u00131\u0014Q\u0002\b\u0004K\u0005-\u0001\"B\u0014\u0007\u0001\u0004A\u0003#\u0002 \u0002\u0010\u0005M\u0011bAA\t\u000f\n!!+\u001b8h!\r)\u0013Q\u0003\u0003\u0006\u0015\u001a\u0011\ra\u0013\u0005\n\u000331\u0011\u0011!a\u0002\u00037\t1\"\u001a<jI\u0016t7-\u001a\u00135gA)\u0011\u0011B+\u0002\u0014!1\u0001N\u0002a\u0001\u0003?\u0001R!!\u00037\u0003'Aaa\u001b\u0004A\u0002\u0005}\u0011!E3vG2LG-Z1o%&tw-S7qYV!\u0011qEA\u001f)\u0011\tI#a\r\u0015\r\u0005-\u0012\u0011KA+)\u0011\ti#!\u0012\u0015\t\u0005=\u0012q\b\t\u0006\u0003c1\u0014Q\u0007\b\u0004K\u0005M\u0002\"B\u0014\b\u0001\u0004A\u0003#\u0002 \u00028\u0005m\u0012bAA\u001d\u000f\niQ)^2mS\u0012,\u0017M\u001c*j]\u001e\u00042!JA\u001f\t\u0015QuA1\u0001L\u0011%\t\teBA\u0001\u0002\b\t\u0019%A\u0006fm&$WM\\2fIQ\"\u0004#BA\u0019+\u0006m\u0002bBA$\u000f\u0001\u0007\u0011\u0011J\u0001\u0003KZ\u0004R!!\r7\u0003\u0017\u0002RAPA'\u0003wI1!a\u0014H\u0005\t)\u0015\u000f\u0003\u0004i\u000f\u0001\u0007\u00111\u000b\t\u0006\u0003c1\u00141\b\u0005\u0007W\u001e\u0001\r!a\u0015\u0002\u0013\u0019LW\r\u001c3J[BdW\u0003BA.\u0003c\"B!!\u0018\u0002hQ1\u0011qLA@\u0003\u0007#B!!\u0019\u0002zQ!\u00111MA:!\u0015\t)GNA5\u001d\r)\u0013q\r\u0005\u0006O!\u0001\r\u0001\u000b\t\u0006}\u0005-\u0014qN\u0005\u0004\u0003[:%!\u0002$jK2$\u0007cA\u0013\u0002r\u0011)!\n\u0003b\u0001\u0017\"I\u0011Q\u000f\u0005\u0002\u0002\u0003\u000f\u0011qO\u0001\fKZLG-\u001a8dK\u0012\"T\u0007E\u0003\u0002fU\u000by\u0007C\u0004\u0002H!\u0001\r!a\u001f\u0011\u000b\u0005\u0015d'! \u0011\u000by\ni%a\u001c\t\r!D\u0001\u0019AAA!\u0015\t)GNA8\u0011\u0019Y\u0007\u00021\u0001\u0002\u0002\u00061Q-]%na2,B!!#\u0002\u0018R!\u00111RAI)\u0011\ti)!'\u0011\u000b\u0005=e'a%\u000f\u0007\u0015\n\t\nC\u0003(\u0013\u0001\u0007\u0001\u0006E\u0003?\u0003\u001b\n)\nE\u0002&\u0003/#QAS\u0005C\u0002-C\u0011\"a'\n\u0003\u0003\u0005\u001d!!(\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$CG\u000e\t\u0006\u0003\u001f+\u0016QS\u0001\n_J$WM]%na2,B!a)\u00026R!\u0011QUAV)\u0011\t9+a.\u0011\u000b\u0005%f'!,\u000f\u0007\u0015\nY\u000bC\u0003(\u0015\u0001\u0007\u0001\u0006E\u0003?\u0003_\u000b\u0019,C\u0002\u00022\u001e\u0013Qa\u0014:eKJ\u00042!JA[\t\u0015Q%B1\u0001L\u0011%\tILCA\u0001\u0002\b\tY,A\u0006fm&$WM\\2fIQ:\u0004#BAU+\u0006M\u0016aF2pY2,7\r^5p]N+W.[4s_V\u0004\u0018*\u001c9m+\u0011\t\t-a5\u0015\t\u0005\r\u0017\u0011\u001a\u000b\u0005\u0003\u000b\f)\u000eE\u0003\u0002HZ\nYMD\u0002&\u0003\u0013DQaJ\u0006A\u0002!\u0002RAPAg\u0003#L1!a4H\u0005%\u0019V-\\5he>,\b\u000fE\u0002&\u0003'$QAS\u0006C\u0002-C\u0011\"a6\f\u0003\u0003\u0005\u001d!!7\u0002\u0017\u00154\u0018\u000eZ3oG\u0016$C\u0007\u000f\t\u0006\u0003\u000f,\u0016\u0011[\u0001\u0015G>dG.Z2uS>tWj\u001c8pS\u0012LU\u000e\u001d7\u0016\t\u0005}\u00171\u001f\u000b\u0005\u0003C\fI\u000f\u0006\u0003\u0002d\u0006mH\u0003BAs\u0003k\u0004R!a:7\u0003Wt1!JAu\u0011\u00159C\u00021\u0001)!\u0015q\u0014Q^Ay\u0013\r\tyo\u0012\u0002\u0007\u001b>tw.\u001b3\u0011\u0007\u0015\n\u0019\u0010B\u0003K\u0019\t\u00071\nC\u0005\u0002x2\t\t\u0011q\u0001\u0002z\u0006YQM^5eK:\u001cW\r\n\u001b:!\u0015\t9/VAy\u0011\u0019AG\u00021\u0001\u0002~B)\u0011q\u001d\u001c\u0002r\u0002"
)
public final class ScalaAutoMacros {
   public static Exprs.Expr collectionMonoidImpl(final Context c, final Exprs.Expr z, final TypeTags.WeakTypeTag evidence$49) {
      return ScalaAutoMacros$.MODULE$.collectionMonoidImpl(c, z, evidence$49);
   }

   public static Exprs.Expr collectionSemigroupImpl(final Context c, final TypeTags.WeakTypeTag evidence$48) {
      return ScalaAutoMacros$.MODULE$.collectionSemigroupImpl(c, evidence$48);
   }

   public static Exprs.Expr orderImpl(final Context c, final TypeTags.WeakTypeTag evidence$47) {
      return ScalaAutoMacros$.MODULE$.orderImpl(c, evidence$47);
   }

   public static Exprs.Expr eqImpl(final Context c, final TypeTags.WeakTypeTag evidence$46) {
      return ScalaAutoMacros$.MODULE$.eqImpl(c, evidence$46);
   }

   public static Exprs.Expr fieldImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$45) {
      return ScalaAutoMacros$.MODULE$.fieldImpl(c, z, o, ev, evidence$45);
   }

   public static Exprs.Expr euclideanRingImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final Exprs.Expr ev, final TypeTags.WeakTypeTag evidence$44) {
      return ScalaAutoMacros$.MODULE$.euclideanRingImpl(c, z, o, ev, evidence$44);
   }

   public static Exprs.Expr ringImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$43) {
      return ScalaAutoMacros$.MODULE$.ringImpl(c, z, o, evidence$43);
   }

   public static Exprs.Expr rngImpl(final Context c, final Exprs.Expr z, final TypeTags.WeakTypeTag evidence$42) {
      return ScalaAutoMacros$.MODULE$.rngImpl(c, z, evidence$42);
   }

   public static Exprs.Expr rigImpl(final Context c, final Exprs.Expr z, final Exprs.Expr o, final TypeTags.WeakTypeTag evidence$41) {
      return ScalaAutoMacros$.MODULE$.rigImpl(c, z, o, evidence$41);
   }

   public static Exprs.Expr semiringImpl(final Context c, final TypeTags.WeakTypeTag evidence$40) {
      return ScalaAutoMacros$.MODULE$.semiringImpl(c, evidence$40);
   }
}
