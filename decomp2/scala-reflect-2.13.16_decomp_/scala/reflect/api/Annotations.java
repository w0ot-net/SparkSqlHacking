package scala.reflect.api;

import scala.Option;
import scala.collection.immutable.List;
import scala.collection.immutable.ListMap;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb!C\n\u0015!\u0003\r\taGA\u0015\u0011\u0015\u0001\u0003\u0001\"\u0001\"\t\u0015)\u0003A!\u0001'\u0011\u001d\t\bA1A\u0007\u0002I4Q\u0001\u001e\u0001\u0002\u0002UDQA\u001e\u0003\u0005\u0002]DQ\u0001\u001f\u0003\u0005\u0002eDQ\u0001\u001f\u0003\u0007\u0002qDq!a\u0002\u0005\r\u0003\tIAB\u00040\u0001A\u0005\u0019\u0011\u0001\u0019\t\u000b\u0001JA\u0011A\u0011\t\u000bEJA\u0011\u0001\u001a\t\u000baJa\u0011A\u001d\t\u000b%Ka\u0011\u0001&\t\u000bUKa\u0011\u0001,\t\u0011\u0005u\u0001A\"\u0005\u0019\u0003?A\u0001\"a\t\u0001\r#A\u0012Q\u0005\u0003\u0006K\u0002\u0011\tA\u001a\u0004\bU\u0002\u0001\n1%\u0001l\u0005-\teN\\8uCRLwN\\:\u000b\u0005U1\u0012aA1qS*\u0011q\u0003G\u0001\be\u00164G.Z2u\u0015\u0005I\u0012!B:dC2\f7\u0001A\n\u0003\u0001q\u0001\"!\b\u0010\u000e\u0003aI!a\b\r\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t!\u0005\u0005\u0002\u001eG%\u0011A\u0005\u0007\u0002\u0005+:LGO\u0001\u0006B]:|G/\u0019;j_:\f\"a\n\u0016\u0011\u0005uA\u0013BA\u0015\u0019\u0005\u0011qU\u000f\u001c7\u0013\u0007-bRF\u0002\u0003-\u0001\u0001Q#\u0001\u0004\u001fsK\u001aLg.Z7f]Rt\u0004C\u0001\u0018\n\u001b\u0005\u0001!!D!o]>$\u0018\r^5p]\u0006\u0003\u0018n\u0005\u0002\n9\u0005!AO]3f+\u0005\u0019\u0004C\u0001\u00185\u0013\t)dG\u0001\u0003Ue\u0016,\u0017BA\u001c\u0015\u0005\u0015!&/Z3t\u0003\r!\b/Z\u000b\u0002uA\u0011afO\u0005\u0003yu\u0012A\u0001V=qK&\u0011a\b\u0006\u0002\u0006)f\u0004Xm\u001d\u0015\u0007\u0019\u0001\u001bEIR$\u0011\u0005u\t\u0015B\u0001\"\u0019\u0005)!W\r\u001d:fG\u0006$X\rZ\u0001\b[\u0016\u001c8/Y4fC\u0005)\u0015AF;tK\u0002\u0002GO]3f]Q\u0004X\r\u0019\u0011j]N$X-\u00193\u0002\u000bMLgnY3\"\u0003!\u000baA\r\u00182c9\u0002\u0014!C:dC2\f\u0017I]4t+\u0005Y\u0005c\u0001'Pg9\u0011Q$T\u0005\u0003\u001db\tq\u0001]1dW\u0006<W-\u0003\u0002Q#\n!A*[:u\u0015\tq\u0005\u0004\u000b\u0004\u000e\u0001\u000e\u001bfiR\u0011\u0002)\u0006\u0001So]3!AR\u0014X-\u001a\u0018dQ&dGM]3o]Q\f\u0017\u000e\u001c1!S:\u001cH/Z1e\u0003!Q\u0017M^1Be\u001e\u001cX#A,\u0011\takv\fZ\u0007\u00023*\u0011!lW\u0001\nS6lW\u000f^1cY\u0016T!\u0001\u0018\r\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002_3\n9A*[:u\u001b\u0006\u0004\bC\u0001\u0018a\u0013\t\t'M\u0001\u0003OC6,\u0017BA2\u0015\u0005\u0015q\u0015-\\3t!\tq\u0013C\u0001\u0007KCZ\f\u0017I]4v[\u0016tG/\u0005\u0002(OJ\u0019\u0001\u000eH5\u0007\t1\u0002\u0001a\u001a\t\u0003]I\u0011qBS1wC\u0006\u0013x-^7f]R\f\u0005/[\n\u0003%qAcA\u0005!D[\u001a;\u0015%\u00018\u0002kU\u001cX\r\t1B]:|G/\u0019;j_:tCO]3fA\u0002\"x\u000eI5ogB,7\r\u001e\u0011b]:|G/\u0019;j_:\u0004\u0013M]4v[\u0016tGo\u001d\u0015\u0007#\u0001\u001bUNR$)\r9\u00015i\u0015$H\u0003)\teN\\8uCRLwN\\\u000b\u0002gB\u0011a\u0006\u0002\u0002\u0014\u0003:tw\u000e^1uS>tW\t\u001f;sC\u000e$xN]\n\u0003\tq\ta\u0001P5oSRtD#A:\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005i\\\bC\u0001\u0018\u0003\u0011\u0015\td\u00011\u00014)\u0011QXP`@\t\u000ba:\u0001\u0019\u0001\u001e\t\u000b%;\u0001\u0019A&\t\u000bU;\u0001\u0019A,)\u000f\u001d\u00015)a\u0001G\u000f\u0006\u0012\u0011QA\u0001,kN,\u0007\u0005Y1qa2L\b\u0006\u001e:fKj\u0002CK]3fSi\u0002\u0013I\u001c8pi\u0006$\u0018n\u001c8aA%t7\u000f^3bI\u00069QO\\1qa2LH\u0003BA\u0006\u0003/\u0001R!HA\u0007\u0003#I1!a\u0004\u0019\u0005\u0019y\u0005\u000f^5p]B1Q$a\u0005;\u0017^K1!!\u0006\u0019\u0005\u0019!V\u000f\u001d7fg!1\u0011\u0011\u0004\u0005A\u0002i\f1!\u00198oQ\u0019A\u0001iQ7G\u000f\u0006\u0001\u0012M\u001c8pi\u0006$\u0018n\u001c8U_R\u0013X-\u001a\u000b\u0004g\u0005\u0005\u0002BBA\r\u001f\u0001\u0007!0\u0001\tue\u0016,Gk\\!o]>$\u0018\r^5p]R\u0019!0a\n\t\u000bE\u0002\u0002\u0019A\u001a\u0011\t\u0005-\u0012QF\u0007\u0002)%\u0019\u0011q\u0006\u000b\u0003\u0011Us\u0017N^3sg\u0016\u0004"
)
public interface Annotations {
   AnnotationExtractor Annotation();

   Trees.TreeApi annotationToTree(final AnnotationApi ann);

   AnnotationApi treeToAnnotation(final Trees.TreeApi tree);

   static void $init$(final Annotations $this) {
   }

   public abstract class AnnotationExtractor {
      // $FF: synthetic field
      public final Universe $outer;

      public AnnotationApi apply(final Trees.TreeApi tree) {
         return this.scala$reflect$api$Annotations$AnnotationExtractor$$$outer().treeToAnnotation(tree);
      }

      /** @deprecated */
      public abstract AnnotationApi apply(final Types.TypeApi tpe, final List scalaArgs, final ListMap javaArgs);

      /** @deprecated */
      public abstract Option unapply(final AnnotationApi ann);

      // $FF: synthetic method
      public Universe scala$reflect$api$Annotations$AnnotationExtractor$$$outer() {
         return this.$outer;
      }

      public AnnotationExtractor() {
         if (Annotations.this == null) {
            throw null;
         } else {
            this.$outer = Annotations.this;
            super();
         }
      }
   }

   public interface AnnotationApi {
      // $FF: synthetic method
      static Trees.TreeApi tree$(final AnnotationApi $this) {
         return $this.tree();
      }

      default Trees.TreeApi tree() {
         return this.scala$reflect$api$Annotations$AnnotationApi$$$outer().annotationToTree(this);
      }

      /** @deprecated */
      Types.TypeApi tpe();

      /** @deprecated */
      List scalaArgs();

      /** @deprecated */
      ListMap javaArgs();

      // $FF: synthetic method
      Annotations scala$reflect$api$Annotations$AnnotationApi$$$outer();

      static void $init$(final AnnotationApi $this) {
      }
   }

   /** @deprecated */
   public interface JavaArgumentApi {
   }
}
