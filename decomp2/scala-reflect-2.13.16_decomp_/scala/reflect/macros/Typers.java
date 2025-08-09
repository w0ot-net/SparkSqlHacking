package scala.reflect.macros;

import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.reflect.api.Trees;
import scala.reflect.api.Types;
import scala.reflect.macros.blackbox.Context;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-e\u0001\u0003\u0010 !\u0003\r\tAJ\u001d\t\u000b-\u0002A\u0011\u0001\u0017\t\u000bA\u0002a\u0011A\u0019\u0005\u000b}\u0002!\u0011\u0001!\t\u000f\u001d\u0003!\u0019!D\u0001\u0011\"91\n\u0001b\u0001\u000e\u0003A\u0005b\u0002'\u0001\u0005\u00045\t\u0001S\u0003\u0005\u001b\u0002\u0001a\nC\u0004R\u0001\t\u0007I\u0011\u0001*\t\u000by\u0003A\u0011A0\t\u0011}\u0004\u0011\u0013!C\u0001\u0003\u0003A\u0011\"a\u0006\u0001#\u0003%\t!!\u0007\t\u0013\u0005u\u0001!%A\u0005\u0002\u0005e\u0001\"CA\u0010\u0001E\u0005I\u0011AA\r\u0011\u001d\t\t\u0003\u0001D\u0001\u0003GA\u0011\"a\r\u0001#\u0003%\t!!\u000e\t\u0013\u0005e\u0002!%A\u0005\u0002\u0005\u0005\u0001\"CA\u001e\u0001E\u0005I\u0011AA\r\u0011%\ti\u0004AI\u0001\n\u0003\tI\u0002C\u0005\u0002@\u0001\t\n\u0011\"\u0001\u0002\u001a!9\u0011\u0011\t\u0001\u0007\u0002\u0005\r\u0003\"CA+\u0001E\u0005I\u0011AA\r\u0011%\t9\u0006AI\u0001\n\u0003\tI\u0002C\u0005\u0002Z\u0001\t\n\u0011\"\u0001\u0002\\!9\u0011q\f\u0001\u0007\u0002\u0005\u0005\u0004\"CA:\u0001E\u0005I\u0011AA\r\u0011%\t)\bAI\u0001\n\u0003\tI\u0002C\u0005\u0002x\u0001\t\n\u0011\"\u0001\u0002\\!9\u0011\u0011\u0010\u0001\u0007\u0002\u0005m\u0004bBAC\u0001\u0019\u0005\u0011q\u0011\u0002\u0007)f\u0004XM]:\u000b\u0005\u0001\n\u0013AB7bGJ|7O\u0003\u0002#G\u00059!/\u001a4mK\u000e$(\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001a\n\t\u0003Q%j\u0011aI\u0005\u0003U\r\u0012a!\u00118z%\u00164\u0017A\u0002\u0013j]&$H\u0005F\u0001.!\tAc&\u0003\u00020G\t!QK\\5u\u0003)y\u0007/\u001a8NC\u000e\u0014xn]\u000b\u0002eA\u00191GN\u001d\u000f\u0005!\"\u0014BA\u001b$\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000e\u001d\u0003\t1K7\u000f\u001e\u0006\u0003k\r\u0002\"AO\u001f\u000e\u0003mR!\u0001P\u0010\u0002\u0011\td\u0017mY6c_bL!AP\u001e\u0003\u000f\r{g\u000e^3yi\niA+\u001f9fG\",7m['pI\u0016\f\"!\u0011#\u0011\u0005!\u0012\u0015BA\"$\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001K#\n\u0005\u0019\u001b#aA!os\u0006AA+\u0012*N[>$W-F\u0001J!\tQ5!D\u0001\u0001\u0003!!\u0016\fU#n_\u0012,\u0017a\u0003)B)R+%KT7pI\u0016\u0014!\u0003V=qK\u000eDWmY6Fq\u000e,\u0007\u000f^5p]B\u0011q\nU\u0007\u0002?%\u0011QjH\u0001\u0013)f\u0004Xm\u00195fG.,\u0005pY3qi&|g.F\u0001T\u001d\t!VL\u0004\u0002V9:\u0011ak\u0017\b\u0003/jk\u0011\u0001\u0017\u0006\u00033\u0016\na\u0001\u0010:p_Rt\u0014\"\u0001\u0013\n\u0005\t\u001a\u0013B\u0001\u0011\"\u0013\t\tv$A\u0005usB,7\t[3dWR1\u0001-Z4mcN\u0004\"AS1\n\u0005\t\u001c'\u0001\u0002+sK\u0016L!\u0001Z\u0010\u0003\u000f\u0005c\u0017.Y:fg\")a-\u0003a\u0001A\u0006!AO]3f\u0011\u001dA\u0017\u0002%AA\u0002%\f!\u0001\u001d;\u0011\u0005)S\u0017BA6d\u0005\u0011!\u0016\u0010]3\t\u000f5L\u0001\u0013!a\u0001]\u000611/\u001b7f]R\u0004\"\u0001K8\n\u0005A\u001c#a\u0002\"p_2,\u0017M\u001c\u0005\be&\u0001\n\u00111\u0001o\u0003e9\u0018\u000e\u001e5J[Bd\u0017nY5u-&,wo\u001d#jg\u0006\u0014G.\u001a3\t\u000fQL\u0001\u0013!a\u0001]\u0006\u0011r/\u001b;i\u001b\u0006\u001c'o\\:ESN\f'\r\\3eQ\u0019Ia/\u001f>}{B\u0011\u0001f^\u0005\u0003q\u000e\u0012!\u0002Z3qe\u0016\u001c\u0017\r^3e\u0003\u001diWm]:bO\u0016\f\u0013a_\u0001\u001akN,\u0007\u0005Y2/if\u0004Xm\u00195fG.\u0004\u0007%\u001b8ti\u0016\fG-A\u0003tS:\u001cW-I\u0001\u007f\u0003\u0019\u0011d&M\u0019/a\u0005\u0019B/\u001f9f\u0007\",7m\u001b\u0013eK\u001a\fW\u000f\u001c;%eU\u0011\u00111\u0001\u0016\u0004S\u0006\u00151FAA\u0004!\u0011\tI!a\u0005\u000e\u0005\u0005-!\u0002BA\u0007\u0003\u001f\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005E1%\u0001\u0006b]:|G/\u0019;j_:LA!!\u0006\u0002\f\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002'QL\b/Z\"iK\u000e\\G\u0005Z3gCVdG\u000fJ\u001a\u0016\u0005\u0005m!f\u00018\u0002\u0006\u0005\u0019B/\u001f9f\u0007\",7m\u001b\u0013eK\u001a\fW\u000f\u001c;%i\u0005\u0019B/\u001f9f\u0007\",7m\u001b\u0013eK\u001a\fW\u000f\u001c;%k\u0005IA/\u001f9fG\",7m\u001b\u000b\u000eA\u0006\u0015\u0012qEA\u0016\u0003[\ty#!\r\t\u000b\u0019t\u0001\u0019\u00011\t\u0011\u0005%b\u0002%AA\u0002%\u000bA!\\8eK\"9\u0001N\u0004I\u0001\u0002\u0004I\u0007bB7\u000f!\u0003\u0005\rA\u001c\u0005\be:\u0001\n\u00111\u0001o\u0011\u001d!h\u0002%AA\u00029\f1\u0003^=qK\u000eDWmY6%I\u00164\u0017-\u001e7uII*\"!a\u000e+\u0007%\u000b)!A\nusB,7\r[3dW\u0012\"WMZ1vYR$3'A\nusB,7\r[3dW\u0012\"WMZ1vYR$C'A\nusB,7\r[3dW\u0012\"WMZ1vYR$S'A\nusB,7\r[3dW\u0012\"WMZ1vYR$c'\u0001\nj]\u001a,'/S7qY&\u001c\u0017\u000e\u001e,bYV,G#\u00031\u0002F\u0005\u001d\u0013\u0011JA&\u0011\u0015AG\u00031\u0001j\u0011\u001diG\u0003%AA\u00029Dq\u0001\u001e\u000b\u0011\u0002\u0003\u0007a\u000eC\u0005\u0002NQ\u0001\n\u00111\u0001\u0002P\u0005\u0019\u0001o\\:\u0011\u0007)\u000b\t&C\u0002\u0002T\r\u0014\u0001\u0002U8tSRLwN\\\u0001\u001dS:4WM]%na2L7-\u001b;WC2,X\r\n3fM\u0006,H\u000e\u001e\u00133\u0003qIgNZ3s\u00136\u0004H.[2jiZ\u000bG.^3%I\u00164\u0017-\u001e7uIM\nA$\u001b8gKJLU\u000e\u001d7jG&$h+\u00197vK\u0012\"WMZ1vYR$C'\u0006\u0002\u0002^)\"\u0011qJA\u0003\u0003EIgNZ3s\u00136\u0004H.[2jiZKWm\u001e\u000b\u000eA\u0006\r\u0014QMA5\u0003[\ny'!\u001d\t\u000b\u0019D\u0002\u0019\u00011\t\r\u0005\u001d\u0004\u00041\u0001j\u0003\u00111'o\\7\t\r\u0005-\u0004\u00041\u0001j\u0003\t!x\u000eC\u0004n1A\u0005\t\u0019\u00018\t\u000fQD\u0002\u0013!a\u0001]\"I\u0011Q\n\r\u0011\u0002\u0003\u0007\u0011qJ\u0001\u001cS:4WM]%na2L7-\u001b;WS\u0016<H\u0005Z3gCVdG\u000f\n\u001b\u00027%tg-\u001a:J[Bd\u0017nY5u-&,w\u000f\n3fM\u0006,H\u000e\u001e\u00136\u0003mIgNZ3s\u00136\u0004H.[2jiZKWm\u001e\u0013eK\u001a\fW\u000f\u001c;%m\u0005y!/Z:fi2{7-\u00197BiR\u00148\u000fF\u0002a\u0003{BQA\u001a\u000fA\u0002\u0001Ds\u0001\b<z\u0003\u0003cX0\t\u0002\u0002\u0004\u0006YRo]3!A\u000etSO\u001c;za\u0016\u001c\u0007.Z2lA\u0002Jgn\u001d;fC\u0012\f1\"\u001e8usB,7\r[3dWR\u0019\u0001-!#\t\u000b\u0019l\u0002\u0019\u00011"
)
public interface Typers {
   void scala$reflect$macros$Typers$_setter_$TypecheckException_$eq(final TypecheckException$ x$1);

   List openMacros();

   Object TERMmode();

   Object TYPEmode();

   Object PATTERNmode();

   TypecheckException$ TypecheckException();

   // $FF: synthetic method
   static Trees.TreeApi typeCheck$(final Typers $this, final Trees.TreeApi tree, final Types.TypeApi pt, final boolean silent, final boolean withImplicitViewsDisabled, final boolean withMacrosDisabled) {
      return $this.typeCheck(tree, pt, silent, withImplicitViewsDisabled, withMacrosDisabled);
   }

   /** @deprecated */
   default Trees.TreeApi typeCheck(final Trees.TreeApi tree, final Types.TypeApi pt, final boolean silent, final boolean withImplicitViewsDisabled, final boolean withMacrosDisabled) {
      return this.typecheck(tree, this.TERMmode(), pt, silent, withImplicitViewsDisabled, withMacrosDisabled);
   }

   // $FF: synthetic method
   static Types.TypeApi typeCheck$default$2$(final Typers $this) {
      return $this.typeCheck$default$2();
   }

   default Types.TypeApi typeCheck$default$2() {
      return ((Context)this).universe().WildcardType();
   }

   // $FF: synthetic method
   static boolean typeCheck$default$3$(final Typers $this) {
      return $this.typeCheck$default$3();
   }

   default boolean typeCheck$default$3() {
      return false;
   }

   // $FF: synthetic method
   static boolean typeCheck$default$4$(final Typers $this) {
      return $this.typeCheck$default$4();
   }

   default boolean typeCheck$default$4() {
      return false;
   }

   // $FF: synthetic method
   static boolean typeCheck$default$5$(final Typers $this) {
      return $this.typeCheck$default$5();
   }

   default boolean typeCheck$default$5() {
      return false;
   }

   Trees.TreeApi typecheck(final Trees.TreeApi tree, final Object mode, final Types.TypeApi pt, final boolean silent, final boolean withImplicitViewsDisabled, final boolean withMacrosDisabled);

   // $FF: synthetic method
   static Object typecheck$default$2$(final Typers $this) {
      return $this.typecheck$default$2();
   }

   default Object typecheck$default$2() {
      return this.TERMmode();
   }

   // $FF: synthetic method
   static Types.TypeApi typecheck$default$3$(final Typers $this) {
      return $this.typecheck$default$3();
   }

   default Types.TypeApi typecheck$default$3() {
      return ((Context)this).universe().WildcardType();
   }

   // $FF: synthetic method
   static boolean typecheck$default$4$(final Typers $this) {
      return $this.typecheck$default$4();
   }

   default boolean typecheck$default$4() {
      return false;
   }

   // $FF: synthetic method
   static boolean typecheck$default$5$(final Typers $this) {
      return $this.typecheck$default$5();
   }

   default boolean typecheck$default$5() {
      return false;
   }

   // $FF: synthetic method
   static boolean typecheck$default$6$(final Typers $this) {
      return $this.typecheck$default$6();
   }

   default boolean typecheck$default$6() {
      return false;
   }

   Trees.TreeApi inferImplicitValue(final Types.TypeApi pt, final boolean silent, final boolean withMacrosDisabled, final Position pos);

   // $FF: synthetic method
   static boolean inferImplicitValue$default$2$(final Typers $this) {
      return $this.inferImplicitValue$default$2();
   }

   default boolean inferImplicitValue$default$2() {
      return true;
   }

   // $FF: synthetic method
   static boolean inferImplicitValue$default$3$(final Typers $this) {
      return $this.inferImplicitValue$default$3();
   }

   default boolean inferImplicitValue$default$3() {
      return false;
   }

   // $FF: synthetic method
   static Position inferImplicitValue$default$4$(final Typers $this) {
      return $this.inferImplicitValue$default$4();
   }

   default Position inferImplicitValue$default$4() {
      return ((Enclosures)this).enclosingPosition();
   }

   Trees.TreeApi inferImplicitView(final Trees.TreeApi tree, final Types.TypeApi from, final Types.TypeApi to, final boolean silent, final boolean withMacrosDisabled, final Position pos);

   // $FF: synthetic method
   static boolean inferImplicitView$default$4$(final Typers $this) {
      return $this.inferImplicitView$default$4();
   }

   default boolean inferImplicitView$default$4() {
      return true;
   }

   // $FF: synthetic method
   static boolean inferImplicitView$default$5$(final Typers $this) {
      return $this.inferImplicitView$default$5();
   }

   default boolean inferImplicitView$default$5() {
      return false;
   }

   // $FF: synthetic method
   static Position inferImplicitView$default$6$(final Typers $this) {
      return $this.inferImplicitView$default$6();
   }

   default Position inferImplicitView$default$6() {
      return ((Enclosures)this).enclosingPosition();
   }

   /** @deprecated */
   Trees.TreeApi resetLocalAttrs(final Trees.TreeApi tree);

   Trees.TreeApi untypecheck(final Trees.TreeApi tree);

   static void $init$(final Typers $this) {
      $this.scala$reflect$macros$Typers$_setter_$TypecheckException_$eq(TypecheckException$.MODULE$);
   }
}
