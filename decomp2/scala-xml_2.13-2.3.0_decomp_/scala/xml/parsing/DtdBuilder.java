package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.collection.IterableOps;
import scala.collection.immutable.List;
import scala.collection.immutable.Seq;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.xml.dtd.AttListDecl;
import scala.xml.dtd.AttrDecl;
import scala.xml.dtd.DEFAULT;
import scala.xml.dtd.DTD;
import scala.xml.dtd.DefaultDecl;
import scala.xml.dtd.ElemDecl;
import scala.xml.dtd.EntityDecl;
import scala.xml.dtd.EntityDef;
import scala.xml.dtd.ExtDef;
import scala.xml.dtd.ExternalID;
import scala.xml.dtd.IMPLIED$;
import scala.xml.dtd.IntDef;
import scala.xml.dtd.NotationDecl;
import scala.xml.dtd.PEReference;
import scala.xml.dtd.ParameterEntityDecl;
import scala.xml.dtd.ParsedEntityDecl;
import scala.xml.dtd.REQUIRED$;
import scala.xml.dtd.UnparsedEntityDecl;

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0005b!B\u001a5\u0005QR\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011B!\t\u00111\u0003!\u0011!Q\u0001\n5CQa\u0015\u0001\u0005\u0002QCq!\u0017\u0001A\u0002\u0013%!\fC\u0004f\u0001\u0001\u0007I\u0011\u00024\t\r1\u0004\u0001\u0015)\u0003\\\u0011\u001di\u0007\u00011A\u0005\n9Dqa\u001d\u0001A\u0002\u0013%A\u000f\u0003\u0004w\u0001\u0001\u0006Ka\u001c\u0005\bo\u0002\u0001\r\u0011\"\u0003y\u0011\u001di\b\u00011A\u0005\nyDq!!\u0001\u0001A\u0003&\u0011\u0010C\u0005\u0002\u0004\u0001\u0001\r\u0011\"\u0003\u0002\u0006!I\u0011q\u0002\u0001A\u0002\u0013%\u0011\u0011\u0003\u0005\t\u0003+\u0001\u0001\u0015)\u0003\u0002\b!I\u0011q\u0003\u0001A\u0002\u0013%\u0011\u0011\u0004\u0005\n\u0003G\u0001\u0001\u0019!C\u0005\u0003KA\u0001\"!\u000b\u0001A\u0003&\u00111\u0004\u0005\n\u0003W\u0001\u0001\u0019!C\u0005\u0003[A\u0011\"a\u000e\u0001\u0001\u0004%I!!\u000f\t\u0011\u0005u\u0002\u0001)Q\u0005\u0003_A\u0011\"a\u0010\u0001\u0001\u0004%I!!\u0011\t\u0013\u0005%\u0003\u00011A\u0005\n\u0005-\u0003\u0002CA(\u0001\u0001\u0006K!a\u0011\t\u0013\u0005E\u0003\u00011A\u0005\n\u0005M\u0003\"CA/\u0001\u0001\u0007I\u0011BA0\u0011!\t\u0019\u0007\u0001Q!\n\u0005U\u0003bBA3\u0001\u0011%\u0011q\r\u0005\n\u0003S\u0002\u0001\u0019!C\u0005\u0003WB\u0011\"a\u001d\u0001\u0001\u0004%I!!\u001e\t\u0011\u0005e\u0004\u0001)Q\u0005\u0003[Bq!a\u001f\u0001\t\u0003\tY\u0007C\u0004\u0002~\u0001!\t!a\u001a\t\rA\u0003A\u0011AA@\u0011\u001d\t9\t\u0001C\u0001\u0003\u0013Cq!!%\u0001\t\u0003\t\u0019\nC\u0004\u0002*\u0002!\t!a+\t\u000f\u0005=\u0006\u0001\"\u0001\u00022\"9\u0011Q\u0017\u0001\u0005\u0002\u0005]\u0006bBAb\u0001\u0011\u0005\u0011Q\u0019\u0005\b\u0003#\u0004A\u0011AAj\u0011\u001d\tI\u000e\u0001C\u0001\u00037Dq!a9\u0001\t\u0013\t)\u000fC\u0004\u0002t\u0002!\t!!>\t\u000f\u0005}\b\u0001\"\u0001\u0003\u0002\u001dA!q\u0001\u001b\t\u0002Q\u0012IAB\u00044i!\u0005AGa\u0003\t\rM{C\u0011\u0001B\u0007\u0011\u001d\u0011ya\fC\u0001\u0005#AqA!\u00070\t\u0013\u0011YB\u0001\u0006Ei\u0012\u0014U/\u001b7eKJT!!\u000e\u001c\u0002\u000fA\f'o]5oO*\u0011q\u0007O\u0001\u0004q6d'\"A\u001d\u0002\u000bM\u001c\u0017\r\\1\u0014\u0005\u0001Y\u0004C\u0001\u001f>\u001b\u0005A\u0014B\u0001 9\u0005\u0019\te.\u001f*fM\u0006!a.Y7f\u0007\u0001\u0001\"AQ%\u000f\u0005\r;\u0005C\u0001#9\u001b\u0005)%B\u0001$A\u0003\u0019a$o\\8u}%\u0011\u0001\nO\u0001\u0007!J,G-\u001a4\n\u0005)[%AB*ue&twM\u0003\u0002Iq\u0005QQ\r\u001f;fe:\fG.\u0013#\u0011\u00059\u000bV\"A(\u000b\u0005A3\u0014a\u00013uI&\u0011!k\u0014\u0002\u000b\u000bb$XM\u001d8bY&#\u0015A\u0002\u001fj]&$h\bF\u0002V/b\u0003\"A\u0016\u0001\u000e\u0003QBQaP\u0002A\u0002\u0005CQ\u0001T\u0002A\u00025\u000b\u0001\"\u001a7f[\u0016tGo]\u000b\u00027B\u0019Al\u00182\u000f\u0005qj\u0016B\u000109\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001Y1\u0003\t1K7\u000f\u001e\u0006\u0003=b\u0002\"AT2\n\u0005\u0011|%\u0001C#mK6$Um\u00197\u0002\u0019\u0015dW-\\3oiN|F%Z9\u0015\u0005\u001dT\u0007C\u0001\u001fi\u0013\tI\u0007H\u0001\u0003V]&$\bbB6\u0006\u0003\u0003\u0005\raW\u0001\u0004q\u0012\n\u0014!C3mK6,g\u000e^:!\u00039\tG\u000f\u001e:jEV$X\rT5tiN,\u0012a\u001c\t\u00049~\u0003\bC\u0001(r\u0013\t\u0011xJA\u0006BiRd\u0015n\u001d;EK\u000ed\u0017AE1uiJL'-\u001e;f\u0019&\u001cHo]0%KF$\"aZ;\t\u000f-D\u0011\u0011!a\u0001_\u0006y\u0011\r\u001e;sS\n,H/\u001a'jgR\u001c\b%\u0001\u0005f]RLG/[3t+\u0005I\bc\u0001/`uB\u0011aj_\u0005\u0003y>\u0013!\"\u00128uSRLH)Z2m\u00031)g\u000e^5uS\u0016\u001cx\fJ3r)\t9w\u0010C\u0004l\u0017\u0005\u0005\t\u0019A=\u0002\u0013\u0015tG/\u001b;jKN\u0004\u0013!\u00038pi\u0006$\u0018n\u001c8t+\t\t9\u0001\u0005\u0003]?\u0006%\u0001c\u0001(\u0002\f%\u0019\u0011QB(\u0003\u00199{G/\u0019;j_:$Um\u00197\u0002\u001b9|G/\u0019;j_:\u001cx\fJ3r)\r9\u00171\u0003\u0005\tW:\t\t\u00111\u0001\u0002\b\u0005Qan\u001c;bi&|gn\u001d\u0011\u0002!Ut\u0007/\u0019:tK\u0012,e\u000e^5uS\u0016\u001cXCAA\u000e!\u0011av,!\b\u0011\u00079\u000by\"C\u0002\u0002\"=\u0013!#\u00168qCJ\u001cX\rZ#oi&$\u0018\u0010R3dY\u0006!RO\u001c9beN,G-\u00128uSRLWm]0%KF$2aZA\u0014\u0011!Y\u0017#!AA\u0002\u0005m\u0011!E;oa\u0006\u00148/\u001a3F]RLG/[3tA\u0005\u0019\u0002/\u0019:b[\u0016$XM\u001d*fM\u0016\u0014XM\\2fgV\u0011\u0011q\u0006\t\u00059~\u000b\t\u0004E\u0002O\u0003gI1!!\u000eP\u0005-\u0001VIU3gKJ,gnY3\u0002/A\f'/Y7fi\u0016\u0014(+\u001a4fe\u0016t7-Z:`I\u0015\fHcA4\u0002<!A1\u000eFA\u0001\u0002\u0004\ty#\u0001\u000bqCJ\fW.\u001a;feJ+g-\u001a:f]\u000e,7\u000fI\u0001\fK2,W.\u001a8u\u001d\u0006lW-\u0006\u0002\u0002DA!A(!\u0012B\u0013\r\t9\u0005\u000f\u0002\u0007\u001fB$\u0018n\u001c8\u0002\u001f\u0015dW-\\3oi:\u000bW.Z0%KF$2aZA'\u0011!Yw#!AA\u0002\u0005\r\u0013\u0001D3mK6,g\u000e\u001e(b[\u0016\u0004\u0013AC1uiJL'-\u001e;fgV\u0011\u0011Q\u000b\t\u00059~\u000b9\u0006E\u0002O\u00033J1!a\u0017P\u0005!\tE\u000f\u001e:EK\u000ed\u0017AD1uiJL'-\u001e;fg~#S-\u001d\u000b\u0004O\u0006\u0005\u0004\u0002C6\u001b\u0003\u0003\u0005\r!!\u0016\u0002\u0017\u0005$HO]5ckR,7\u000fI\u0001\u0010M2,8\u000f[!uiJL'-\u001e;fgR\tq-\u0001\u0003e_:,WCAA7!\ra\u0014qN\u0005\u0004\u0003cB$a\u0002\"p_2,\u0017M\\\u0001\tI>tWm\u0018\u0013fcR\u0019q-a\u001e\t\u0011-t\u0012\u0011!a\u0001\u0003[\nQ\u0001Z8oK\u0002\na![:E_:,\u0017AB3oI\u0012#F)\u0006\u0002\u0002\u0002B\u0019a*a!\n\u0007\u0005\u0015uJA\u0002E)\u0012\u000b1\"\u001a7f[\u0016tG\u000fR3dYR)q-a#\u0002\u000e\")qh\ta\u0001\u0003\"1\u0011qR\u0012A\u0002\u0005\u000bQ!\\8eK2\fQ\"\u0019;ue&\u0014W\u000f^3EK\u000edGcC4\u0002\u0016\u0006e\u0015QTAQ\u0003KCa!a&%\u0001\u0004\t\u0015!B3OC6,\u0007BBANI\u0001\u0007\u0011)A\u0003b\u001d\u0006lW\r\u0003\u0004\u0002 \u0012\u0002\r!Q\u0001\u0005if\u0004X\r\u0003\u0004\u0002$\u0012\u0002\r!Q\u0001\u0005[>$W\r\u0003\u0004\u0002(\u0012\u0002\r!Q\u0001\u0006m\u0006dW/Z\u0001\fgR\f'\u000f^#oi&$\u0018\u0010F\u0002h\u0003[CQaP\u0013A\u0002\u0005\u000b\u0011\"\u001a8e\u000b:$\u0018\u000e^=\u0015\u0007\u001d\f\u0019\fC\u0003@M\u0001\u0007\u0011)\u0001\u0007o_R\fG/[8o\t\u0016\u001cG\u000eF\u0004h\u0003s\u000bY,a0\t\u000b}:\u0003\u0019A!\t\r\u0005uv\u00051\u0001B\u0003!\u0001XO\u00197jG&#\u0007BBAaO\u0001\u0007\u0011)\u0001\u0005tsN$X-\\%e\u0003I)h\u000e]1sg\u0016$WI\u001c;jif$Um\u00197\u0015\u0013\u001d\f9-!3\u0002L\u00065\u0007\"B )\u0001\u0004\t\u0005BBA_Q\u0001\u0007\u0011\t\u0003\u0004\u0002B\"\u0002\r!\u0011\u0005\u0007\u0003\u001fD\u0003\u0019A!\u0002\u00199|G/\u0019;j_:t\u0015-\\3\u0002%%tG/\u001a:oC2,e\u000e^5us\u0012+7\r\u001c\u000b\u0006O\u0006U\u0017q\u001b\u0005\u0006\u007f%\u0002\r!\u0011\u0005\u0007\u0003OK\u0003\u0019A!\u0002%\u0015DH/\u001a:oC2,e\u000e^5us\u0012+7\r\u001c\u000b\bO\u0006u\u0017q\\Aq\u0011\u0015y$\u00061\u0001B\u0011\u0019\tiL\u000ba\u0001\u0003\"1\u0011\u0011\u0019\u0016A\u0002\u0005\u000b!\"\u001a8uSRLH)Z2m)\u00159\u0017q]Au\u0011\u0015y4\u00061\u0001B\u0011\u001d\tYo\u000ba\u0001\u0003[\f\u0011\"\u001a8uSRLH)\u001a4\u0011\u00079\u000by/C\u0002\u0002r>\u0013\u0011\"\u00128uSRLH)\u001a4\u0002+A\u0014xnY3tg&tw-\u00138tiJ,8\r^5p]R)q-a>\u0002|\"1\u0011\u0011 \u0017A\u0002\u0005\u000ba\u0001^1sO\u0016$\bBBA\u007fY\u0001\u0007\u0011)\u0001\u0003eCR\f\u0017aB2p[6,g\u000e\u001e\u000b\u0004O\n\r\u0001B\u0002B\u0003[\u0001\u0007\u0011)A\u0006d_6lWM\u001c;UKb$\u0018A\u0003#uI\n+\u0018\u000e\u001c3feB\u0011akL\n\u0003_m\"\"A!\u0003\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u000fU\u0013\u0019B!\u0006\u0003\u0018!)q(\ra\u0001\u0003\"1\u0011QX\u0019A\u0002\u0005Ca!!12\u0001\u0004\t\u0015\u0001D7l\u000bb$XM\u001d8bY&#E#B'\u0003\u001e\t}\u0001BBA_e\u0001\u0007\u0011\t\u0003\u0004\u0002BJ\u0002\r!\u0011"
)
public final class DtdBuilder {
   public final ExternalID scala$xml$parsing$DtdBuilder$$externalID;
   private List scala$xml$parsing$DtdBuilder$$elements;
   private List scala$xml$parsing$DtdBuilder$$attributeLists;
   private List scala$xml$parsing$DtdBuilder$$entities;
   private List scala$xml$parsing$DtdBuilder$$notations;
   private List scala$xml$parsing$DtdBuilder$$unparsedEntities;
   private List scala$xml$parsing$DtdBuilder$$parameterReferences;
   private Option elementName;
   private List attributes;
   private boolean done;

   public static DtdBuilder apply(final String name, final String publicId, final String systemId) {
      return DtdBuilder$.MODULE$.apply(name, publicId, systemId);
   }

   public List scala$xml$parsing$DtdBuilder$$elements() {
      return this.scala$xml$parsing$DtdBuilder$$elements;
   }

   private void elements_$eq(final List x$1) {
      this.scala$xml$parsing$DtdBuilder$$elements = x$1;
   }

   public List scala$xml$parsing$DtdBuilder$$attributeLists() {
      return this.scala$xml$parsing$DtdBuilder$$attributeLists;
   }

   private void attributeLists_$eq(final List x$1) {
      this.scala$xml$parsing$DtdBuilder$$attributeLists = x$1;
   }

   public List scala$xml$parsing$DtdBuilder$$entities() {
      return this.scala$xml$parsing$DtdBuilder$$entities;
   }

   private void entities_$eq(final List x$1) {
      this.scala$xml$parsing$DtdBuilder$$entities = x$1;
   }

   public List scala$xml$parsing$DtdBuilder$$notations() {
      return this.scala$xml$parsing$DtdBuilder$$notations;
   }

   private void notations_$eq(final List x$1) {
      this.scala$xml$parsing$DtdBuilder$$notations = x$1;
   }

   public List scala$xml$parsing$DtdBuilder$$unparsedEntities() {
      return this.scala$xml$parsing$DtdBuilder$$unparsedEntities;
   }

   private void unparsedEntities_$eq(final List x$1) {
      this.scala$xml$parsing$DtdBuilder$$unparsedEntities = x$1;
   }

   public List scala$xml$parsing$DtdBuilder$$parameterReferences() {
      return this.scala$xml$parsing$DtdBuilder$$parameterReferences;
   }

   private void parameterReferences_$eq(final List x$1) {
      this.scala$xml$parsing$DtdBuilder$$parameterReferences = x$1;
   }

   private Option elementName() {
      return this.elementName;
   }

   private void elementName_$eq(final Option x$1) {
      this.elementName = x$1;
   }

   private List attributes() {
      return this.attributes;
   }

   private void attributes_$eq(final List x$1) {
      this.attributes = x$1;
   }

   private void flushAttributes() {
      if (this.elementName().isDefined()) {
         this.attributeLists_$eq(this.scala$xml$parsing$DtdBuilder$$attributeLists().$colon$colon(new AttListDecl((String)this.elementName().get(), this.attributes().reverse())));
         this.attributes_$eq(.MODULE$.List().empty());
         this.elementName_$eq(scala.None..MODULE$);
      }
   }

   private boolean done() {
      return this.done;
   }

   private void done_$eq(final boolean x$1) {
      this.done = x$1;
   }

   public boolean isDone() {
      return this.done();
   }

   public void endDTD() {
      this.flushAttributes();
      this.done_$eq(true);
   }

   public DTD dtd() {
      return new DTD() {
         private final Seq notations;
         private final Seq unparsedEntities;

         public Seq notations() {
            return this.notations;
         }

         public Seq unparsedEntities() {
            return this.unparsedEntities;
         }

         public {
            this.externalID_$eq(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$externalID);
            this.elem().$plus$plus$eq(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$elements().map((d) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(d.name()), d)).toMap(scala..less.colon.less..MODULE$.refl()));
            this.attr().$plus$plus$eq(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$attributeLists().map((d) -> scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(d.name()), d)).toMap(scala..less.colon.less..MODULE$.refl()));
            this.ent().$plus$plus$eq(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$entities().map((d) -> {
               String var10000;
               if (d instanceof ParsedEntityDecl) {
                  ParsedEntityDecl var4 = (ParsedEntityDecl)d;
                  String name = var4.name();
                  var10000 = name;
               } else if (d instanceof ParameterEntityDecl) {
                  ParameterEntityDecl var6 = (ParameterEntityDecl)d;
                  String name = var6.name();
                  var10000 = name;
               } else {
                  if (!(d instanceof UnparsedEntityDecl)) {
                     throw new MatchError(d);
                  }

                  UnparsedEntityDecl var8 = (UnparsedEntityDecl)d;
                  String name = var8.name();
                  var10000 = name;
               }

               String name = var10000;
               return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(name), d);
            }).toMap(scala..less.colon.less..MODULE$.refl()));
            this.decls_$eq((List)((IterableOps)((IterableOps)((IterableOps)DtdBuilder.this.scala$xml$parsing$DtdBuilder$$elements().reverse().$plus$plus(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$attributeLists().reverse())).$plus$plus(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$entities().reverse())).$plus$plus(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$notations().reverse())).$plus$plus(DtdBuilder.this.scala$xml$parsing$DtdBuilder$$parameterReferences().reverse()));
            this.notations = DtdBuilder.this.scala$xml$parsing$DtdBuilder$$notations().reverse();
            this.unparsedEntities = DtdBuilder.this.scala$xml$parsing$DtdBuilder$$unparsedEntities().reverse();
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   public void elementDecl(final String name, final String model) {
      this.flushAttributes();
      this.elements_$eq(this.scala$xml$parsing$DtdBuilder$$elements().$colon$colon(new ElemDecl(name, ElementContentModel$.MODULE$.parseContentModel(model))));
   }

   public void attributeDecl(final String eName, final String aName, final String type, final String mode, final String value) {
      if (!this.elementName().contains(eName)) {
         this.flushAttributes();
         this.elementName_$eq(new Some(eName));
      }

      AttrDecl var10000;
      Object var10004;
      label30: {
         var10000 = new AttrDecl;
         switch (mode == null ? 0 : mode.hashCode()) {
            case -782225659:
               if ("#IMPLIED".equals(mode)) {
                  var10004 = IMPLIED$.MODULE$;
                  break label30;
               }
               break;
            case 1068928273:
               if ("#FIXED".equals(mode)) {
                  var10004 = new DEFAULT(true, value);
                  break label30;
               }
               break;
            case 1553090754:
               if ("#REQUIRED".equals(mode)) {
                  var10004 = REQUIRED$.MODULE$;
                  break label30;
               }
         }

         var10004 = new DEFAULT(false, value);
      }

      var10000.<init>(aName, type, (DefaultDecl)var10004);
      AttrDecl attribute = var10000;
      this.attributes_$eq(this.attributes().$colon$colon(attribute));
   }

   public void startEntity(final String name) {
      this.flushAttributes();
      if (name.startsWith("%")) {
         this.parameterReferences_$eq(this.scala$xml$parsing$DtdBuilder$$parameterReferences().$colon$colon(new PEReference(scala.collection.StringOps..MODULE$.tail$extension(scala.Predef..MODULE$.augmentString(name)).trim())));
      }
   }

   public void endEntity(final String name) {
   }

   public void notationDecl(final String name, final String publicId, final String systemId) {
      this.flushAttributes();
      this.notations_$eq(this.scala$xml$parsing$DtdBuilder$$notations().$colon$colon(new NotationDecl(name, DtdBuilder$.MODULE$.scala$xml$parsing$DtdBuilder$$mkExternalID(publicId, systemId))));
   }

   public void unparsedEntityDecl(final String name, final String publicId, final String systemId, final String notationName) {
      this.flushAttributes();
      UnparsedEntityDecl unparsedEntity = new UnparsedEntityDecl(name, DtdBuilder$.MODULE$.scala$xml$parsing$DtdBuilder$$mkExternalID(publicId, systemId), notationName);
      this.entities_$eq(this.scala$xml$parsing$DtdBuilder$$entities().$colon$colon(unparsedEntity));
      this.unparsedEntities_$eq(this.scala$xml$parsing$DtdBuilder$$unparsedEntities().$colon$colon(unparsedEntity));
   }

   public void internalEntityDecl(final String name, final String value) {
      this.flushAttributes();
      this.entityDecl(name, new IntDef(value));
   }

   public void externalEntityDecl(final String name, final String publicId, final String systemId) {
      this.flushAttributes();
      this.entityDecl(name, new ExtDef(DtdBuilder$.MODULE$.scala$xml$parsing$DtdBuilder$$mkExternalID(publicId, systemId)));
   }

   private void entityDecl(final String name, final EntityDef entityDef) {
      EntityDecl entity = (EntityDecl)(name.startsWith("%") ? new ParameterEntityDecl(scala.collection.StringOps..MODULE$.tail$extension(scala.Predef..MODULE$.augmentString(name)).trim(), entityDef) : new ParsedEntityDecl(name, entityDef));
      this.entities_$eq(this.scala$xml$parsing$DtdBuilder$$entities().$colon$colon(entity));
   }

   public void processingInstruction(final String target, final String data) {
   }

   public void comment(final String commentText) {
   }

   public DtdBuilder(final String name, final ExternalID externalID) {
      this.scala$xml$parsing$DtdBuilder$$externalID = externalID;
      this.scala$xml$parsing$DtdBuilder$$elements = .MODULE$.List().empty();
      this.scala$xml$parsing$DtdBuilder$$attributeLists = .MODULE$.List().empty();
      this.scala$xml$parsing$DtdBuilder$$entities = .MODULE$.List().empty();
      this.scala$xml$parsing$DtdBuilder$$notations = .MODULE$.List().empty();
      this.scala$xml$parsing$DtdBuilder$$unparsedEntities = .MODULE$.List().empty();
      this.scala$xml$parsing$DtdBuilder$$parameterReferences = .MODULE$.List().empty();
      this.elementName = scala.None..MODULE$;
      this.attributes = .MODULE$.List().empty();
      this.done = false;
   }
}
