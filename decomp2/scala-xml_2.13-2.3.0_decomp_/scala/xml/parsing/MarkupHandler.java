package scala.xml.parsing;

import java.lang.invoke.SerializedLambda;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala..less.colon.less.;
import scala.collection.LinearSeqOps;
import scala.collection.immutable.List;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.Map;
import scala.io.Source;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.xml.MetaData;
import scala.xml.NamespaceBinding;
import scala.xml.NodeSeq;
import scala.xml.dtd.Decl;
import scala.xml.dtd.ElemDecl;
import scala.xml.dtd.EntityDecl;
import scala.xml.dtd.EntityDef;
import scala.xml.dtd.ExtDef;
import scala.xml.dtd.ExternalID;
import scala.xml.dtd.IntDef;
import scala.xml.dtd.PEReference;
import scala.xml.dtd.ParameterEntityDecl;
import scala.xml.dtd.ParsedEntityDecl;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0015h!B\u000f\u001f\u0003\u0003)\u0003\"\u0002\u0016\u0001\t\u0003Y\u0003b\u0002\u0018\u0001\u0005\u0004%\ta\f\u0005\u0007g\u0001\u0001\u000b\u0011\u0002\u0019\t\u000fQ\u0002\u0001\u0019!C\u0001k!91\t\u0001a\u0001\n\u0003!\u0005B\u0002&\u0001A\u0003&a\u0007C\u0004L\u0001\u0001\u0007I\u0011\u0001'\t\u000f\r\u0004\u0001\u0019!C\u0001I\"1a\r\u0001Q!\n5CQa\u001a\u0001\u0005\u0002!DQA\u001c\u0001\u0005\u0002=DQ\u0001\u001f\u0001\u0005\u0002eDQ\u0001 \u0001\u0005\u0002uDq!!\n\u0001\t\u0003\t9\u0003C\u0004\u00020\u00011\t!!\r\t\u000f\u0005-\u0003A\"\u0001\u0002N!9\u0011\u0011\f\u0001\u0007\u0002\u0005m\u0003bBA1\u0001\u0019\u0005\u00111\r\u0005\b\u0003S\u0002a\u0011AA6\u0011\u001d\t\t\b\u0001C\u0001\u0003gBq!a\u001f\u0001\t\u0003\ti\bC\u0004\u0002\u0010\u0002!I!!%\t\u000f\u0005%\u0006\u0001\"\u0001\u0002,\"9\u0011\u0011\u0017\u0001\u0005\u0002\u0005M\u0006bBA]\u0001\u0011\u0005\u00111\u0018\u0005\b\u0003\u007f\u0003A\u0011AAa\u0011\u001d\t\u0019\u000e\u0001C\u0001\u0003+Dq!a7\u0001\r\u0003\tiNA\u0007NCJ\\W\u000f\u001d%b]\u0012dWM\u001d\u0006\u0003?\u0001\nq\u0001]1sg&twM\u0003\u0002\"E\u0005\u0019\u00010\u001c7\u000b\u0003\r\nQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001MA\u0011q\u0005K\u0007\u0002E%\u0011\u0011F\t\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005a\u0003CA\u0017\u0001\u001b\u0005q\u0012\u0001D5t-\u0006d\u0017\u000eZ1uS:<W#\u0001\u0019\u0011\u0005\u001d\n\u0014B\u0001\u001a#\u0005\u001d\u0011un\u001c7fC:\fQ\"[:WC2LG-\u0019;j]\u001e\u0004\u0013!\u00023fG2\u001cX#\u0001\u001c\u0011\u0007]RTH\u0004\u0002(q%\u0011\u0011HI\u0001\ba\u0006\u001c7.Y4f\u0013\tYDH\u0001\u0003MSN$(BA\u001d#!\tq\u0014)D\u0001@\u0015\t\u0001\u0005%A\u0002ei\u0012L!AQ \u0003\t\u0011+7\r\\\u0001\nI\u0016\u001cGn]0%KF$\"!\u0012%\u0011\u0005\u001d2\u0015BA$#\u0005\u0011)f.\u001b;\t\u000f%+\u0011\u0011!a\u0001m\u0005\u0019\u0001\u0010J\u0019\u0002\r\u0011,7\r\\:!\u0003\r)g\u000e^\u000b\u0002\u001bB!ajU+a\u001b\u0005y%B\u0001)R\u0003\u001diW\u000f^1cY\u0016T!A\u0015\u0012\u0002\u0015\r|G\u000e\\3di&|g.\u0003\u0002U\u001f\n\u0019Q*\u00199\u0011\u0005YkfBA,\\!\tA&%D\u0001Z\u0015\tQF%\u0001\u0004=e>|GOP\u0005\u00039\n\na\u0001\u0015:fI\u00164\u0017B\u00010`\u0005\u0019\u0019FO]5oO*\u0011AL\t\t\u0003}\u0005L!AY \u0003\u0015\u0015sG/\u001b;z\t\u0016\u001cG.A\u0004f]R|F%Z9\u0015\u0005\u0015+\u0007bB%\t\u0003\u0003\u0005\r!T\u0001\u0005K:$\b%\u0001\bm_>\\W\u000f]#mK6$Um\u00197\u0015\u0005%d\u0007C\u0001 k\u0013\tYwH\u0001\u0005FY\u0016lG)Z2m\u0011\u0015i'\u00021\u0001V\u0003\u0015a\u0015MY3m\u0003=\u0011X\r\u001d7bG\u0016lWM\u001c;UKb$HC\u00019w!\t\tH/D\u0001s\u0015\t\u0019(%\u0001\u0002j_&\u0011QO\u001d\u0002\u0007'>,(oY3\t\u000b]\\\u0001\u0019A+\u0002\u0015\u0015tG/\u001b;z\u001d\u0006lW-\u0001\u0004f]\u0012$E\u000b\u0012\u000b\u0003\u000bjDQa\u001f\u0007A\u0002U\u000b\u0011A\\\u0001\nK2,Wn\u0015;beR$\"\"\u0012@\u0002\b\u0005-\u0011qBA\u000e\u0011\u0019yX\u00021\u0001\u0002\u0002\u0005\u0019\u0001o\\:\u0011\u0007\u001d\n\u0019!C\u0002\u0002\u0006\t\u00121!\u00138u\u0011\u0019\tI!\u0004a\u0001+\u0006\u0019\u0001O]3\t\r\u00055Q\u00021\u0001V\u0003\u0015a\u0017MY3m\u0011\u001d\t\t\"\u0004a\u0001\u0003'\tQ!\u0019;ueN\u0004B!!\u0006\u0002\u00185\t\u0001%C\u0002\u0002\u001a\u0001\u0012\u0001\"T3uC\u0012\u000bG/\u0019\u0005\b\u0003;i\u0001\u0019AA\u0010\u0003\u0015\u00198m\u001c9f!\u0011\t)\"!\t\n\u0007\u0005\r\u0002E\u0001\tOC6,7\u000f]1dK\nKg\u000eZ5oO\u00069Q\r\\3n\u000b:$GcB#\u0002*\u0005-\u0012Q\u0006\u0005\u0007\u007f:\u0001\r!!\u0001\t\r\u0005%a\u00021\u0001V\u0011\u0019\tiA\u0004a\u0001+\u0006!Q\r\\3n)A\t\u0019$!\u000f\u0002<\u0005u\u0012qHA!\u0003\u0007\n9\u0005\u0005\u0003\u0002\u0016\u0005U\u0012bAA\u001cA\t9aj\u001c3f'\u0016\f\bBB@\u0010\u0001\u0004\t\t\u0001\u0003\u0004\u0002\n=\u0001\r!\u0016\u0005\u0007\u0003\u001by\u0001\u0019A+\t\u000f\u0005Eq\u00021\u0001\u0002\u0014!9\u0011QD\bA\u0002\u0005}\u0001BBA#\u001f\u0001\u0007\u0001'A\u0003f[B$\u0018\u0010C\u0004\u0002J=\u0001\r!a\r\u0002\t\u0005\u0014xm]\u0001\naJ|7-\u00138tiJ$\u0002\"a\r\u0002P\u0005E\u0013Q\u000b\u0005\u0007\u007fB\u0001\r!!\u0001\t\r\u0005M\u0003\u00031\u0001V\u0003\u0019!\u0018M]4fi\"1\u0011q\u000b\tA\u0002U\u000b1\u0001\u001e=u\u0003\u001d\u0019w.\\7f]R$b!a\r\u0002^\u0005}\u0003BB@\u0012\u0001\u0004\t\t\u0001\u0003\u0004\u0002ZE\u0001\r!V\u0001\nK:$\u0018\u000e^=SK\u001a$b!a\r\u0002f\u0005\u001d\u0004BB@\u0013\u0001\u0004\t\t\u0001C\u0003|%\u0001\u0007Q+\u0001\u0003uKb$HCBA\u001a\u0003[\ny\u0007\u0003\u0004\u0000'\u0001\u0007\u0011\u0011\u0001\u0005\u0007\u0003/\u001a\u0002\u0019A+\u0002\u0011\u0015dW-\u001c#fG2$R!RA;\u0003oBQa\u001f\u000bA\u0002UCa!!\u001f\u0015\u0001\u0004)\u0016!B2ngR\u0014\u0018aC1ui2K7\u000f\u001e#fG2$R!RA@\u0003\u0007Ca!!!\u0016\u0001\u0004)\u0016\u0001\u00028b[\u0016Dq!!\"\u0016\u0001\u0004\t9)A\u0004biRd\u0015n\u001d;\u0011\t]R\u0014\u0011\u0012\t\u0004}\u0005-\u0015bAAG\u007f\tA\u0011\t\u001e;s\t\u0016\u001cG.\u0001\bt_6,WI\u001c;jif$Um\u00197\u0015\u000f\u0015\u000b\u0019*!&\u0002 \"1\u0011\u0011\u0011\fA\u0002UCq!a&\u0017\u0001\u0004\tI*\u0001\u0003fI\u00164\u0007c\u0001 \u0002\u001c&\u0019\u0011QT \u0003\u0013\u0015sG/\u001b;z\t\u00164\u0007bBAQ-\u0001\u0007\u00111U\u0001\u0002MB9q%!*V\u00033\u0003\u0017bAATE\tIa)\u001e8di&|gNM\u0001\u0014a\u0006\u0014\u0018-\\3uKJ,e\u000e^5us\u0012+7\r\u001c\u000b\u0006\u000b\u00065\u0016q\u0016\u0005\u0007\u0003\u0003;\u0002\u0019A+\t\u000f\u0005]u\u00031\u0001\u0002\u001a\u0006\u0001\u0002/\u0019:tK\u0012,e\u000e^5us\u0012+7\r\u001c\u000b\u0006\u000b\u0006U\u0016q\u0017\u0005\u0007\u0003\u0003C\u0002\u0019A+\t\u000f\u0005]\u0005\u00041\u0001\u0002\u001a\u0006Y\u0001/\u001a*fM\u0016\u0014XM\\2f)\r)\u0015Q\u0018\u0005\u0007\u0003\u0003K\u0002\u0019A+\u0002%Ut\u0007/\u0019:tK\u0012,e\u000e^5us\u0012+7\r\u001c\u000b\b\u000b\u0006\r\u0017QYAh\u0011\u0019\t\tI\u0007a\u0001+\"9\u0011q\u0019\u000eA\u0002\u0005%\u0017!B3yi&#\u0005c\u0001 \u0002L&\u0019\u0011QZ \u0003\u0015\u0015CH/\u001a:oC2LE\t\u0003\u0004\u0002Rj\u0001\r!V\u0001\u0006]>$\u0018\r^\u0001\r]>$\u0018\r^5p]\u0012+7\r\u001c\u000b\u0006\u000b\u0006]\u0017\u0011\u001c\u0005\u0007\u0003#\\\u0002\u0019A+\t\u000f\u0005\u001d7\u00041\u0001\u0002J\u0006\t\"/\u001a9peR\u001c\u0016P\u001c;bq\u0016\u0013(o\u001c:\u0015\u000b\u0015\u000by.!9\t\r}d\u0002\u0019AA\u0001\u0011\u0019\t\u0019\u000f\ba\u0001+\u0006\u00191\u000f\u001e:"
)
public abstract class MarkupHandler {
   private final boolean isValidating = false;
   private List decls;
   private Map ent;

   public boolean isValidating() {
      return this.isValidating;
   }

   public List decls() {
      return this.decls;
   }

   public void decls_$eq(final List x$1) {
      this.decls = x$1;
   }

   public Map ent() {
      return this.ent;
   }

   public void ent_$eq(final Map x$1) {
      this.ent = x$1;
   }

   public ElemDecl lookupElemDecl(final String Label) {
      return (ElemDecl)((LinearSeqOps)this.decls().withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$lookupElemDecl$1(Label, check$ifrefutable$1))).map((x$1) -> {
         if (x$1 instanceof ElemDecl) {
            ElemDecl var4 = (ElemDecl)x$1;
            String var5 = var4.name();
            if (Label == null) {
               if (var5 == null) {
                  return var4;
               }
            } else if (Label.equals(var5)) {
               return var4;
            }
         }

         throw new MatchError(x$1);
      })).headOption().orNull(.MODULE$.refl());
   }

   public Source replacementText(final String entityName) {
      Source var10000 = scala.io.Source..MODULE$;
      boolean var3 = false;
      Some var4 = null;
      Option var5 = this.ent().get(entityName);
      String var10001;
      if (var5 instanceof Some) {
         var3 = true;
         var4 = (Some)var5;
         EntityDecl var6 = (EntityDecl)var4.value();
         if (var6 instanceof ParsedEntityDecl) {
            ParsedEntityDecl var7 = (ParsedEntityDecl)var6;
            EntityDef var8 = var7.entdef();
            if (var8 instanceof IntDef) {
               IntDef var9 = (IntDef)var8;
               String value = var9.value();
               var10001 = value;
               return var10000.fromString(var10001);
            }
         }
      }

      if (var3) {
         EntityDecl var11 = (EntityDecl)var4.value();
         if (var11 instanceof ParameterEntityDecl) {
            ParameterEntityDecl var12 = (ParameterEntityDecl)var11;
            EntityDef var13 = var12.entdef();
            if (var13 instanceof IntDef) {
               var10001 = " value ";
               return var10000.fromString(var10001);
            }
         }
      }

      if (var3) {
         var10001 = (new StringBuilder(10)).append("<!-- ").append(entityName).append("; -->").toString();
      } else {
         if (!scala.None..MODULE$.equals(var5)) {
            throw new MatchError(var5);
         }

         var10001 = (new StringBuilder(25)).append("<!-- unknown entity ").append(entityName).append("; -->").toString();
      }

      return var10000.fromString(var10001);
   }

   public void endDTD(final String n) {
   }

   public void elemStart(final int pos, final String pre, final String label, final MetaData attrs, final NamespaceBinding scope) {
   }

   public void elemEnd(final int pos, final String pre, final String label) {
   }

   public abstract NodeSeq elem(final int pos, final String pre, final String label, final MetaData attrs, final NamespaceBinding scope, final boolean empty, final NodeSeq args);

   public abstract NodeSeq procInstr(final int pos, final String target, final String txt);

   public abstract NodeSeq comment(final int pos, final String comment);

   public abstract NodeSeq entityRef(final int pos, final String n);

   public abstract NodeSeq text(final int pos, final String txt);

   public void elemDecl(final String n, final String cmstr) {
   }

   public void attListDecl(final String name, final List attList) {
   }

   private void someEntityDecl(final String name, final EntityDef edef, final Function2 f) {
      if (edef instanceof ExtDef && !this.isValidating()) {
         BoxedUnit var7 = BoxedUnit.UNIT;
      } else {
         EntityDecl y = (EntityDecl)f.apply(name, edef);
         this.decls_$eq(this.decls().$colon$colon(y));
         this.ent().update(name, y);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }
   }

   public void parameterEntityDecl(final String name, final EntityDef edef) {
      this.someEntityDecl(name, edef, (namex, entdef) -> new ParameterEntityDecl(namex, entdef));
   }

   public void parsedEntityDecl(final String name, final EntityDef edef) {
      this.someEntityDecl(name, edef, (namex, entdef) -> new ParsedEntityDecl(namex, entdef));
   }

   public void peReference(final String name) {
      this.decls_$eq(this.decls().$colon$colon(new PEReference(name)));
   }

   public void unparsedEntityDecl(final String name, final ExternalID extID, final String notat) {
   }

   public void notationDecl(final String notat, final ExternalID extID) {
   }

   public abstract void reportSyntaxError(final int pos, final String str);

   // $FF: synthetic method
   public static final boolean $anonfun$lookupElemDecl$1(final String Label$1, final Decl check$ifrefutable$1) {
      if (check$ifrefutable$1 instanceof ElemDecl) {
         ElemDecl var4 = (ElemDecl)check$ifrefutable$1;
         String var5 = var4.name();
         if (Label$1 == null) {
            if (var5 == null) {
               return true;
            }
         } else if (Label$1.equals(var5)) {
            return true;
         }
      }

      return false;
   }

   public MarkupHandler() {
      this.decls = scala.collection.immutable.Nil..MODULE$;
      this.ent = new HashMap();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
