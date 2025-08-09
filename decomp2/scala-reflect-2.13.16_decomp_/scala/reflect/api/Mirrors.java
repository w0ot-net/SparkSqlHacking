package scala.reflect.api;

import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ef!\u0003\u0016,!\u0003\r\tAMAV\u0011\u00159\u0004\u0001\"\u00019\t\u0015a\u0004A!\u0001>\u0011\u001d)\u0005A1A\u0007\u0002\u0019#Q\u0001\u0013\u0001\u0003\u0002%3qA\u0013\u0001\u0011\u0002G\u00051JB\u0004M\u0001A\u0005\u0019\u0013A'\t\u000b93a\u0011A(\t\u000bM3a\u0011\u0001+\t\u000bi3a\u0011A.\t\u000b=4a\u0011\u00019\t\u000f\u0005\u001daA\"\u0001\u0002\n!9\u0011Q\u0007\u0004\u0007\u0002\u0005]baB/\u0001!\u0003\r\nA\u0018\u0005\u0006?61\ta\u0014\u0005\u0006'61\t\u0001\u0019\u0005\u0006I61\ta\u0014\u0005\u0006K61\tA\u001a\u0005\u0006S61\tA\u001b\u0004\be\u0002\u0001\n1%\u0001t\u0011\u0015y6C\"\u0001P\u0011\u0015\u00196C\"\u0001u\u0011\u0015A8C\"\u0001z\u0011\u0015I7C\"\u0001\u0000\r%\t\u0019\u0002\u0001I\u0001$\u0003\t)\u0002C\u0004\u0002\u0018a1\t!!\u0007\t\rMCb\u0011AA\u0011\r%\tY\u0004\u0001I\u0001$\u0003\ti\u0004\u0003\u0004T7\u0019\u0005\u0013q\b\u0005\u0006\u001dn1\ta\u0014\u0004\n\u0003\u001b\u0001\u0001\u0013aI\u0001\u0003\u001fAQa\u0015\u0010\u0007BQCq!!\u000b\u001f\r\u0003\tYCB\u0005\u0002L\u0001\u0001\n1%\u0001\u0002N!1a&\tD\u0001\u0003\u001fBq!a\u0002\"\r\u0003\t9\bC\u0004\u00026\u00052\t!a\u001f\u0007\u0013\u0005}\u0004\u0001%A\u0012\u0002\u0005\u0005\u0005bBACK\u0019\u0005\u0011q\u0011\u0005\b\u0003\u000b+c\u0011AAM\u0011\u001d\ti*\nD\u0001\u0003?Cq!!*&\r\u0003\t9KA\u0004NSJ\u0014xN]:\u000b\u00051j\u0013aA1qS*\u0011afL\u0001\be\u00164G.Z2u\u0015\u0005\u0001\u0014!B:dC2\f7\u0001A\n\u0003\u0001M\u0002\"\u0001N\u001b\u000e\u0003=J!AN\u0018\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\t\u0011\b\u0005\u00025u%\u00111h\f\u0002\u0005+:LGO\u0001\u0004NSJ\u0014xN]\t\u0003}\u0005\u0003\"\u0001N \n\u0005\u0001{#\u0001\u0002(vY2\u00042AQ\"E\u001b\u0005Y\u0013B\u0001\u001f,\u001b\u0005\u0001\u0011A\u0003:p_Rl\u0015N\u001d:peV\tq\t\u0005\u0002E\u0005\ta!+\u001e8uS6,7\t\\1tgF\u0011ah\r\u0002\u0010%VtG/[7f\u00072\f7o]!qSN\u0011Qa\r\u0002\u000f\u0013:\u001cH/\u00198dK6K'O]8s'\t11'\u0001\u0005j]N$\u0018M\\2f+\u0005\u0001\u0006C\u0001\u001bR\u0013\t\u0011vFA\u0002B]f\faa]=nE>dW#A+\u0011\u0005\u00113\u0016BA,Y\u0005-\u0019E.Y:t'fl'm\u001c7\n\u0005e[#aB*z[\n|Gn]\u0001\re\u00164G.Z2u\r&,G\u000e\u001a\u000b\u000396\u0004\"\u0001R\u0007\u0003\u0017\u0019KW\r\u001c3NSJ\u0014xN]\n\u0003\u001bM\n\u0001B]3dK&4XM]\u000b\u0002CB\u0011AIY\u0005\u0003Gb\u0013!\u0002V3s[NKXNY8m\u0003\r9W\r^\u0001\u0004g\u0016$HCA\u001dh\u0011\u0015A\u0017\u00031\u0001Q\u0003\u00151\u0018\r\\;f\u0003\u0011\u0011\u0017N\u001c3\u0015\u0005q[\u0007\"\u00027\u0013\u0001\u0004\u0001\u0016a\u00038foJ+7-Z5wKJDQA\\\u0005A\u0002\u0005\fQAZ5fY\u0012\fQB]3gY\u0016\u001cG/T3uQ>$GcA9\u0002\u0004A\u0011Ai\u0005\u0002\r\u001b\u0016$\bn\u001c3NSJ\u0014xN]\n\u0003'M*\u0012!\u001e\t\u0003\tZL!a\u001e-\u0003\u00195+G\u000f[8e'fl'm\u001c7\u0002\u000b\u0005\u0004\b\u000f\\=\u0015\u0005AS\b\"B>\u0017\u0001\u0004a\u0018\u0001B1sON\u00042\u0001N?Q\u0013\tqxF\u0001\u0006=e\u0016\u0004X-\u0019;fIz\"2!]A\u0001\u0011\u0015aw\u00031\u0001Q\u0011\u0019\t)A\u0003a\u0001k\u00061Q.\u001a;i_\u0012\fAB]3gY\u0016\u001cGo\u00117bgN$B!a\u0003\u00022A\u0011AI\b\u0002\f\u00072\f7o]'jeJ|'o\u0005\u0003\u001fg\u0005E\u0001C\u0001#\u0019\u00059!V-\u001c9mCR,W*\u001b:s_J\u001c\"\u0001G\u001a\u0002\u0011%\u001c8\u000b^1uS\u000e,\"!a\u0007\u0011\u0007Q\ni\"C\u0002\u0002 =\u0012qAQ8pY\u0016\fg.\u0006\u0002\u0002$A\u0019A)!\n\n\u0007\u0005\u001d\u0002L\u0001\u0004Ts6\u0014w\u000e\\\u0001\u0013e\u00164G.Z2u\u0007>t7\u000f\u001e:vGR|'\u000fF\u0002r\u0003[Aa!a\f!\u0001\u0004)\u0018aC2p]N$(/^2u_JDa!a\r\f\u0001\u0004)\u0016aA2mg\u0006i!/\u001a4mK\u000e$Xj\u001c3vY\u0016$B!!\u000f\u0002HA\u0011Ai\u0007\u0002\r\u001b>$W\u000f\\3NSJ\u0014xN]\n\u00057M\n\t\"\u0006\u0002\u0002BA\u0019A)a\u0011\n\u0007\u0005\u0015\u0003L\u0001\u0007N_\u0012,H.Z*z[\n|G\u000eC\u0004\u0002J1\u0001\r!!\u0011\u0002\u00075|GM\u0001\tSK\u001adWm\u0019;jm\u0016l\u0015N\u001d:peN\u0011\u0011%Q\u000b\u0005\u0003#\n9\u0007\u0006\u0003\u0002T\u0005MD\u0003BA+\u0003/\u0002\"\u0001\u0012\u0004\t\u0013\u0005e#%!AA\u0004\u0005m\u0013AC3wS\u0012,gnY3%cA1\u0011QLA0\u0003Gj\u0011!L\u0005\u0004\u0003Cj#\u0001C\"mCN\u001cH+Y4\u0011\t\u0005\u0015\u0014q\r\u0007\u0001\t\u001d\tIG\tb\u0001\u0003W\u0012\u0011\u0001V\t\u0004\u0003[\u0002\u0006c\u0001\u001b\u0002p%\u0019\u0011\u0011O\u0018\u0003\u000f9{G\u000f[5oO\"9\u0011Q\u000f\u0012A\u0002\u0005\r\u0014aA8cUR!\u00111BA=\u0011\u0019\t\u0019d\ta\u0001+R!\u0011\u0011HA?\u0011\u001d\tI\u0005\na\u0001\u0003\u0003\u0012QBU;oi&lW-T5se>\u00148\u0003B\u0013B\u0003\u0007\u0003\"\u0001R\u0011\u0002\u0019I,h\u000e^5nK\u000ec\u0017m]:\u0015\t\u0005%\u00151\u0012\t\u0003\t\u0012Aq!!$'\u0001\u0004\ty)A\u0002ua\u0016\u00042\u0001RAI\u0013\u0011\t\u0019*!&\u0003\tQK\b/Z\u0005\u0004\u0003/[#!\u0002+za\u0016\u001cH\u0003BAE\u00037Ca!a\r(\u0001\u0004)\u0016aC2mCN\u001c8+_7c_2$2!VAQ\u0011\u001d\t\u0019\u000b\u000ba\u0001\u0003\u0013\u000bQA\u001d;dYN\fA\"\\8ek2,7+_7c_2$B!!\u0011\u0002*\"9\u00111U\u0015A\u0002\u0005%\u0005c\u0001\"\u0002.&\u0019\u0011qV\u0016\u0003\u0011Us\u0017N^3sg\u0016\u0004"
)
public interface Mirrors {
   Mirror rootMirror();

   static void $init$(final Mirrors $this) {
   }

   public interface ClassMirror extends TemplateMirror {
      Symbols.ClassSymbolApi symbol();

      MethodMirror reflectConstructor(final Symbols.MethodSymbolApi constructor);
   }

   public interface FieldMirror {
      Object receiver();

      Symbols.TermSymbolApi symbol();

      Object get();

      void set(final Object value);

      FieldMirror bind(final Object newReceiver);
   }

   public interface InstanceMirror {
      Object instance();

      Symbols.ClassSymbolApi symbol();

      FieldMirror reflectField(final Symbols.TermSymbolApi field);

      MethodMirror reflectMethod(final Symbols.MethodSymbolApi method);

      ClassMirror reflectClass(final Symbols.ClassSymbolApi cls);

      ModuleMirror reflectModule(final Symbols.ModuleSymbolApi mod);
   }

   public interface MethodMirror {
      Object receiver();

      Symbols.MethodSymbolApi symbol();

      Object apply(final Seq args);

      MethodMirror bind(final Object newReceiver);
   }

   public interface ModuleMirror extends TemplateMirror {
      Symbols.ModuleSymbolApi symbol();

      Object instance();
   }

   public interface ReflectiveMirror {
      InstanceMirror reflect(final Object obj, final ClassTag evidence$1);

      ClassMirror reflectClass(final Symbols.ClassSymbolApi cls);

      ModuleMirror reflectModule(final Symbols.ModuleSymbolApi mod);
   }

   public interface RuntimeClassApi {
   }

   public interface RuntimeMirror extends ReflectiveMirror {
      Object runtimeClass(final Types.TypeApi tpe);

      Object runtimeClass(final Symbols.ClassSymbolApi cls);

      Symbols.ClassSymbolApi classSymbol(final Object rtcls);

      Symbols.ModuleSymbolApi moduleSymbol(final Object rtcls);
   }

   public interface TemplateMirror {
      boolean isStatic();

      Symbols.SymbolApi symbol();
   }
}
