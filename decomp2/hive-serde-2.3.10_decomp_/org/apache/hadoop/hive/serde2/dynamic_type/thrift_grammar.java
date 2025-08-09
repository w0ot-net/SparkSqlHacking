package org.apache.hadoop.hive.serde2.dynamic_type;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class thrift_grammar implements thrift_grammarTreeConstants, thrift_grammarConstants {
   protected JJTthrift_grammarState jjtree;
   private List include_path;
   private int field_val;
   protected Map types;
   protected Map tables;
   private static final String[] default_include_path = new String[]{"/usr/local/include", "/usr/include", "/usr/local/include/thrift/if", "/usr/local/include/fb303/if"};
   public thrift_grammarTokenManager token_source;
   SimpleCharStream jj_input_stream;
   public Token token;
   public Token jj_nt;
   private int jj_ntk;
   private int jj_gen;
   private final int[] jj_la1;
   private static int[] jj_la1_0;
   private static int[] jj_la1_1;
   private static int[] jj_la1_2;
   private final List jj_expentries;
   private int[] jj_expentry;
   private int jj_kind;

   protected thrift_grammar(InputStream is, List include_path, boolean junk) {
      this(is, (String)null);
      this.types = new HashMap();
      this.tables = new HashMap();
      this.include_path = include_path;
      this.field_val = -1;
   }

   private static File findFile(String fname, List include_path) {
      for(String path : include_path) {
         String full = path + "/" + fname;
         File f = new File(full);
         if (f.exists()) {
            return f;
         }
      }

      return null;
   }

   public static void main(String[] args) {
      String filename = null;
      List<String> include_path = new ArrayList();

      for(String path : default_include_path) {
         include_path.add(path);
      }

      for(int i = 0; i < args.length; ++i) {
         String arg = args[i];
         if (arg.equals("--include") && i + 1 < args.length) {
            ++i;
            include_path.add(args[i]);
         }

         if (arg.equals("--file") && i + 1 < args.length) {
            ++i;
            filename = args[i];
         }
      }

      InputStream is = System.in;
      if (filename != null) {
         try {
            is = new FileInputStream(findFile(filename, include_path));
         } catch (IOException var8) {
         }
      }

      thrift_grammar t = new thrift_grammar(is, include_path, false);

      try {
         t.Start();
      } catch (Exception e) {
         System.out.println("Parse error.");
         System.out.println(e.getMessage());
         e.printStackTrace();
      }

   }

   public final SimpleNode Start() throws ParseException {
      DynamicSerDeStart jjtn000 = new DynamicSerDeStart(0);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         this.HeaderList();

         while(true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 59:
               case 60:
                  this.CommaOrSemicolon();
                  break;
               default:
                  this.jj_la1[0] = this.jj_gen;
            }

            this.Definition();
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 8:
               case 37:
               case 42:
               case 43:
               case 44:
               case 47:
               case 48:
               case 59:
               case 60:
                  break;
               default:
                  this.jj_la1[1] = this.jj_gen;
                  this.jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  DynamicSerDeStart var3 = jjtn000;
                  return var3;
            }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode HeaderList() throws ParseException {
      DynamicSerDeHeaderList jjtn000 = new DynamicSerDeHeaderList(1);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         while(true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 9:
               case 10:
               case 11:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 25:
               case 27:
                  this.Header();
                  break;
               case 12:
               case 22:
               case 23:
               case 24:
               case 26:
               default:
                  this.jj_la1[2] = this.jj_gen;
                  this.jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  DynamicSerDeHeaderList var3 = jjtn000;
                  return var3;
            }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode Header() throws ParseException {
      DynamicSerDeHeader jjtn000 = new DynamicSerDeHeader(2);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeHeader var3;
      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 25:
               this.Namespace();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               var3 = jjtn000;
               return var3;
            case 12:
            case 22:
            case 23:
            case 24:
            case 26:
            default:
               this.jj_la1[3] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
            case 27:
         }

         this.Include();
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode Namespace() throws ParseException {
      DynamicSerDeNamespace jjtn000 = new DynamicSerDeNamespace(3);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 9:
               this.jj_consume_token(9);
               this.jj_consume_token(54);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var18 = jjtn000;
               return var18;
            case 10:
               this.jj_consume_token(10);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var17 = jjtn000;
               return var17;
            case 11:
               this.jj_consume_token(11);
               this.jj_consume_token(57);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var16 = jjtn000;
               return var16;
            case 12:
            case 22:
            case 23:
            case 24:
            default:
               this.jj_la1[4] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
            case 13:
               this.jj_consume_token(13);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var15 = jjtn000;
               return var15;
            case 14:
               this.jj_consume_token(14);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var14 = jjtn000;
               return var14;
            case 15:
               this.jj_consume_token(15);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var13 = jjtn000;
               return var13;
            case 16:
               this.jj_consume_token(16);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var12 = jjtn000;
               return var12;
            case 17:
               this.jj_consume_token(17);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var11 = jjtn000;
               return var11;
            case 18:
               this.jj_consume_token(18);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var10 = jjtn000;
               return var10;
            case 19:
               this.jj_consume_token(19);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var9 = jjtn000;
               return var9;
            case 20:
               this.jj_consume_token(20);
               this.jj_consume_token(58);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var8 = jjtn000;
               return var8;
            case 21:
               this.jj_consume_token(21);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var7 = jjtn000;
               return var7;
            case 25:
               this.jj_consume_token(25);
               this.jj_consume_token(57);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeNamespace var3 = jjtn000;
               return var3;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode Include() throws ParseException {
      DynamicSerDeInclude jjtn000 = new DynamicSerDeInclude(4);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);
      boolean found = false;

      DynamicSerDeInclude e;
      try {
         this.jj_consume_token(27);
         String fname = this.jj_consume_token(57).image;
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         fname = fname.substring(1, fname.length() - 1);
         File f = findFile(fname, this.include_path);
         if (f != null) {
            found = true;

            try {
               FileInputStream fis = new FileInputStream(f);
               thrift_grammar t = new thrift_grammar(fis, this.include_path, false);
               t.Start();
               fis.close();
               found = true;
               this.tables.putAll(t.tables);
               this.types.putAll(t.types);
            } catch (Exception e) {
               System.out.println("File: " + fname + " - Oops.");
               System.out.println(e.getMessage());
               e.printStackTrace();
            }
         }

         if (!found) {
            throw new RuntimeException("include file not found: " + fname);
         }

         e = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return e;
   }

   public final SimpleNode Definition() throws ParseException {
      DynamicSerDeDefinition jjtn000 = new DynamicSerDeDefinition(5);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 8:
               this.Const();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinition var10 = jjtn000;
               return var10;
            case 37:
            case 42:
            case 43:
            case 44:
            case 48:
               this.TypeDefinition();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinition var9 = jjtn000;
               return var9;
            case 47:
               this.Service();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinition var3 = jjtn000;
               return var3;
            default:
               this.jj_la1[5] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode TypeDefinition() throws ParseException {
      DynamicSerDeTypeDefinition jjtn000 = new DynamicSerDeTypeDefinition(6);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 37:
               this.Senum();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeTypeDefinition var12 = jjtn000;
               return var12;
            case 38:
            case 39:
            case 40:
            case 41:
            case 45:
            case 46:
            case 47:
            default:
               this.jj_la1[6] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
            case 42:
               this.Typedef();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeTypeDefinition var11 = jjtn000;
               return var11;
            case 43:
               this.Struct();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeTypeDefinition var10 = jjtn000;
               return var10;
            case 44:
               this.Xception();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeTypeDefinition var9 = jjtn000;
               return var9;
            case 48:
               this.Enum();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeTypeDefinition var3 = jjtn000;
               return var3;
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final DynamicSerDeTypedef Typedef() throws ParseException {
      DynamicSerDeTypedef jjtn000 = new DynamicSerDeTypedef(7);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypedef var3;
      try {
         this.jj_consume_token(42);
         this.DefinitionType();
         jjtn000.name = this.jj_consume_token(54).image;
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         this.types.put(jjtn000.name, jjtn000);
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final void CommaOrSemicolon() throws ParseException {
      DynamicSerDeCommaOrSemicolon jjtn000 = new DynamicSerDeCommaOrSemicolon(8);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 59:
               this.jj_consume_token(59);
               break;
            case 60:
               this.jj_consume_token(60);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               break;
            default:
               this.jj_la1[7] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

   }

   public final SimpleNode Enum() throws ParseException {
      DynamicSerDeEnum jjtn000 = new DynamicSerDeEnum(9);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeEnum var3;
      try {
         this.jj_consume_token(48);
         this.jj_consume_token(54);
         this.jj_consume_token(61);
         this.EnumDefList();
         this.jj_consume_token(62);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode EnumDefList() throws ParseException {
      DynamicSerDeEnumDefList jjtn000 = new DynamicSerDeEnumDefList(10);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         while(true) {
            this.EnumDef();
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 54:
                  break;
               default:
                  this.jj_la1[8] = this.jj_gen;
                  this.jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  DynamicSerDeEnumDefList var3 = jjtn000;
                  return var3;
            }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode EnumDef() throws ParseException {
      DynamicSerDeEnumDef jjtn000 = new DynamicSerDeEnumDef(11);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeEnumDef var3;
      try {
         this.jj_consume_token(54);
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 63:
               this.jj_consume_token(63);
               this.jj_consume_token(52);
               break;
            default:
               this.jj_la1[9] = this.jj_gen;
         }

         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 59:
            case 60:
               this.CommaOrSemicolon();
               break;
            default:
               this.jj_la1[10] = this.jj_gen;
         }

         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode Senum() throws ParseException {
      DynamicSerDeSenum jjtn000 = new DynamicSerDeSenum(12);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeSenum var3;
      try {
         this.jj_consume_token(37);
         this.jj_consume_token(54);
         this.jj_consume_token(61);
         this.SenumDefList();
         this.jj_consume_token(62);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode SenumDefList() throws ParseException {
      DynamicSerDeSenumDefList jjtn000 = new DynamicSerDeSenumDefList(13);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         while(true) {
            this.SenumDef();
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 57:
                  break;
               default:
                  this.jj_la1[11] = this.jj_gen;
                  this.jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  DynamicSerDeSenumDefList var3 = jjtn000;
                  return var3;
            }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode SenumDef() throws ParseException {
      DynamicSerDeSenumDef jjtn000 = new DynamicSerDeSenumDef(14);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeSenumDef var3;
      try {
         this.jj_consume_token(57);
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 59:
            case 60:
               this.CommaOrSemicolon();
               break;
            default:
               this.jj_la1[12] = this.jj_gen;
         }

         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode Const() throws ParseException {
      DynamicSerDeConst jjtn000 = new DynamicSerDeConst(15);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeConst var3;
      try {
         this.jj_consume_token(8);
         this.FieldType();
         this.jj_consume_token(54);
         this.jj_consume_token(63);
         this.ConstValue();
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 59:
            case 60:
               this.CommaOrSemicolon();
               break;
            default:
               this.jj_la1[13] = this.jj_gen;
         }

         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode ConstValue() throws ParseException {
      DynamicSerDeConstValue jjtn000 = new DynamicSerDeConstValue(16);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 52:
               this.jj_consume_token(52);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               throw new Error("Missing return statement in function");
            case 53:
               this.jj_consume_token(53);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               throw new Error("Missing return statement in function");
            case 54:
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               throw new Error("Missing return statement in function");
            case 55:
            case 56:
            case 58:
            case 59:
            case 60:
            case 62:
            case 63:
            default:
               this.jj_la1[14] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
            case 57:
               this.jj_consume_token(57);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               throw new Error("Missing return statement in function");
            case 61:
               this.ConstMap();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeConstValue var3 = jjtn000;
               return var3;
            case 64:
               this.ConstList();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               throw new Error("Missing return statement in function");
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode ConstList() throws ParseException {
      DynamicSerDeConstList jjtn000 = new DynamicSerDeConstList(17);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeConstList var3;
      try {
         this.jj_consume_token(64);
         this.ConstListContents();
         this.jj_consume_token(65);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode ConstListContents() throws ParseException {
      DynamicSerDeConstListContents jjtn000 = new DynamicSerDeConstListContents(18);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         while(true) {
            this.ConstValue();
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 59:
               case 60:
                  this.CommaOrSemicolon();
                  break;
               default:
                  this.jj_la1[15] = this.jj_gen;
            }

            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 52:
               case 53:
               case 54:
               case 57:
               case 61:
               case 64:
                  break;
               case 55:
               case 56:
               case 58:
               case 59:
               case 60:
               case 62:
               case 63:
               default:
                  this.jj_la1[16] = this.jj_gen;
                  this.jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  DynamicSerDeConstListContents var3 = jjtn000;
                  return var3;
            }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode ConstMap() throws ParseException {
      DynamicSerDeConstMap jjtn000 = new DynamicSerDeConstMap(19);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeConstMap var3;
      try {
         this.jj_consume_token(61);
         this.ConstMapContents();
         this.jj_consume_token(62);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode ConstMapContents() throws ParseException {
      DynamicSerDeConstMapContents jjtn000 = new DynamicSerDeConstMapContents(20);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeConstMapContents var3;
      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 52:
            case 53:
            case 54:
            case 57:
            case 61:
            case 64:
               while(true) {
                  this.ConstValue();
                  this.jj_consume_token(66);
                  this.ConstValue();
                  switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
                     case 59:
                     case 60:
                        this.CommaOrSemicolon();
                        break;
                     default:
                        this.jj_la1[17] = this.jj_gen;
                  }

                  switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
                     case 52:
                     case 53:
                     case 54:
                     case 57:
                     case 61:
                     case 64:
                        break;
                     case 55:
                     case 56:
                     case 58:
                     case 59:
                     case 60:
                     case 62:
                     case 63:
                     default:
                        this.jj_la1[18] = this.jj_gen;
                        this.jjtree.closeNodeScope(jjtn000, true);
                        jjtc000 = false;
                        throw new Error("Missing return statement in function");
                  }
               }
            case 55:
            case 56:
            case 58:
            case 59:
            case 60:
            case 62:
            case 63:
         }

         this.jj_la1[19] = this.jj_gen;
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeStruct Struct() throws ParseException {
      DynamicSerDeStruct jjtn000 = new DynamicSerDeStruct(21);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeStruct var3;
      try {
         this.jj_consume_token(43);
         jjtn000.name = this.jj_consume_token(54).image;
         this.jj_consume_token(61);
         this.FieldList();
         this.jj_consume_token(62);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         this.types.put(jjtn000.name, jjtn000);
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode Xception() throws ParseException {
      DynamicSerDeXception jjtn000 = new DynamicSerDeXception(22);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeXception var3;
      try {
         this.jj_consume_token(44);
         this.jj_consume_token(54);
         this.jj_consume_token(61);
         this.FieldList();
         this.jj_consume_token(62);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode Service() throws ParseException {
      DynamicSerDeService jjtn000 = new DynamicSerDeService(23);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         this.jj_consume_token(47);
         this.jj_consume_token(54);
         this.Extends();
         this.jj_consume_token(61);
         this.FlagArgs();

         while(true) {
            this.Function();
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 28:
               case 29:
               case 31:
               case 32:
               case 33:
               case 34:
               case 35:
               case 38:
               case 39:
               case 40:
               case 41:
               case 54:
                  break;
               case 30:
               case 36:
               case 37:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 49:
               case 50:
               case 51:
               case 52:
               case 53:
               default:
                  this.jj_la1[20] = this.jj_gen;
                  this.UnflagArgs();
                  this.jj_consume_token(62);
                  this.jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  DynamicSerDeService var3 = jjtn000;
                  return var3;
            }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode FlagArgs() throws ParseException {
      DynamicSerDeFlagArgs jjtn000 = new DynamicSerDeFlagArgs(24);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeFlagArgs var3;
      try {
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode UnflagArgs() throws ParseException {
      DynamicSerDeUnflagArgs jjtn000 = new DynamicSerDeUnflagArgs(25);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeUnflagArgs var3;
      try {
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final SimpleNode Extends() throws ParseException {
      DynamicSerDeExtends jjtn000 = new DynamicSerDeExtends(26);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 45:
               this.jj_consume_token(45);
               this.jj_consume_token(54);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeExtends var3 = jjtn000;
               return var3;
            default:
               this.jj_la1[21] = this.jj_gen;
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeExtends var7 = jjtn000;
               return var7;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final DynamicSerDeFunction Function() throws ParseException {
      DynamicSerDeFunction jjtn000 = new DynamicSerDeFunction(27);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeFunction var3;
      try {
         this.Async();
         this.FunctionType();
         jjtn000.name = this.jj_consume_token(54).image;
         this.jj_consume_token(67);
         this.FieldList();
         this.jj_consume_token(68);
         this.Throws();
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 59:
            case 60:
               this.CommaOrSemicolon();
               break;
            default:
               this.jj_la1[22] = this.jj_gen;
         }

         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         this.tables.put(jjtn000.name, jjtn000);
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final void Async() throws ParseException {
      DynamicSerDeAsync jjtn000 = new DynamicSerDeAsync(28);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 41:
               this.jj_consume_token(41);
               break;
            default:
               this.jj_la1[23] = this.jj_gen;
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

   }

   public final void Throws() throws ParseException {
      DynamicSerDeThrows jjtn000 = new DynamicSerDeThrows(29);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 46:
               this.jj_consume_token(46);
               this.jj_consume_token(67);
               this.FieldList();
               this.jj_consume_token(68);
               break;
            default:
               this.jj_la1[24] = this.jj_gen;
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

   }

   public final DynamicSerDeFieldList FieldList() throws ParseException {
      DynamicSerDeFieldList jjtn000 = new DynamicSerDeFieldList(30);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);
      this.field_val = -1;

      try {
         while(true) {
            switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
               case 29:
               case 31:
               case 32:
               case 33:
               case 34:
               case 35:
               case 38:
               case 39:
               case 40:
               case 49:
               case 50:
               case 51:
               case 52:
               case 54:
                  this.Field();
                  break;
               case 30:
               case 36:
               case 37:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 48:
               case 53:
               default:
                  this.jj_la1[25] = this.jj_gen;
                  this.jjtree.closeNodeScope(jjtn000, true);
                  jjtc000 = false;
                  DynamicSerDeFieldList var3 = jjtn000;
                  return var3;
            }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final DynamicSerDeField Field() throws ParseException {
      DynamicSerDeField jjtn000 = new DynamicSerDeField(31);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);
      String fidnum = "";

      DynamicSerDeField jjte000;
      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 52:
               fidnum = this.jj_consume_token(52).image;
               this.jj_consume_token(66);
               break;
            default:
               this.jj_la1[26] = this.jj_gen;
         }

         this.FieldRequiredness();
         this.FieldType();
         jjtn000.name = this.jj_consume_token(54).image;
         this.FieldValue();
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 59:
            case 60:
               this.CommaOrSemicolon();
               break;
            default:
               this.jj_la1[27] = this.jj_gen;
         }

         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         if (fidnum.length() > 0) {
            int fidInt = Integer.parseInt(fidnum);
            jjtn000.fieldid = fidInt;
         } else {
            jjtn000.fieldid = this.field_val--;
         }

         jjte000 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return jjte000;
   }

   public final DynamicSerDeFieldRequiredness FieldRequiredness() throws ParseException {
      DynamicSerDeFieldRequiredness jjtn000 = new DynamicSerDeFieldRequiredness(32);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 49:
               this.jj_consume_token(49);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               jjtn000.requiredness = DynamicSerDeFieldRequiredness.RequirednessTypes.Required;
               DynamicSerDeFieldRequiredness var8 = jjtn000;
               return var8;
            case 50:
               this.jj_consume_token(50);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               jjtn000.requiredness = DynamicSerDeFieldRequiredness.RequirednessTypes.Optional;
               DynamicSerDeFieldRequiredness var7 = jjtn000;
               return var7;
            case 51:
               this.jj_consume_token(51);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               jjtn000.requiredness = DynamicSerDeFieldRequiredness.RequirednessTypes.Skippable;
               DynamicSerDeFieldRequiredness var3 = jjtn000;
               return var3;
            default:
               this.jj_la1[28] = this.jj_gen;
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldRequiredness var9 = jjtn000;
               return var9;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode FieldValue() throws ParseException {
      DynamicSerDeFieldValue jjtn000 = new DynamicSerDeFieldValue(33);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 63:
               this.jj_consume_token(63);
               this.ConstValue();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldValue var3 = jjtn000;
               return var3;
            default:
               this.jj_la1[29] = this.jj_gen;
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldValue var9 = jjtn000;
               return var9;
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final SimpleNode DefinitionType() throws ParseException {
      DynamicSerDeDefinitionType jjtn000 = new DynamicSerDeDefinitionType(34);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 29:
               this.TypeBool();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var16 = jjtn000;
               return var16;
            case 30:
            case 36:
            case 37:
            default:
               this.jj_la1[30] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
            case 31:
               this.Typei16();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var15 = jjtn000;
               return var15;
            case 32:
               this.Typei32();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var14 = jjtn000;
               return var14;
            case 33:
               this.Typei64();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var13 = jjtn000;
               return var13;
            case 34:
               this.TypeDouble();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var12 = jjtn000;
               return var12;
            case 35:
               this.TypeString();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var11 = jjtn000;
               return var11;
            case 38:
               this.TypeMap();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var10 = jjtn000;
               return var10;
            case 39:
               this.TypeList();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var9 = jjtn000;
               return var9;
            case 40:
               this.TypeSet();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeDefinitionType var3 = jjtn000;
               return var3;
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final void FunctionType() throws ParseException {
      DynamicSerDeFunctionType jjtn000 = new DynamicSerDeFunctionType(35);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 28:
               this.jj_consume_token(28);
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               break;
            case 29:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 38:
            case 39:
            case 40:
            case 54:
               this.FieldType();
               break;
            case 30:
            case 36:
            case 37:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            default:
               this.jj_la1[31] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

   }

   public final DynamicSerDeFieldType FieldType() throws ParseException {
      DynamicSerDeFieldType jjtn000 = new DynamicSerDeFieldType(36);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      try {
         switch (this.jj_ntk == -1 ? this.jj_ntk() : this.jj_ntk) {
            case 29:
               this.TypeBool();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var17 = jjtn000;
               return var17;
            case 30:
            case 36:
            case 37:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            default:
               this.jj_la1[32] = this.jj_gen;
               this.jj_consume_token(-1);
               throw new ParseException();
            case 31:
               this.Typei16();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var16 = jjtn000;
               return var16;
            case 32:
               this.Typei32();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var15 = jjtn000;
               return var15;
            case 33:
               this.Typei64();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var14 = jjtn000;
               return var14;
            case 34:
               this.TypeDouble();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var13 = jjtn000;
               return var13;
            case 35:
               this.TypeString();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var12 = jjtn000;
               return var12;
            case 38:
               this.TypeMap();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var11 = jjtn000;
               return var11;
            case 39:
               this.TypeList();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var10 = jjtn000;
               return var10;
            case 40:
               this.TypeSet();
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               DynamicSerDeFieldType var9 = jjtn000;
               return var9;
            case 54:
               jjtn000.name = this.jj_consume_token(54).image;
               this.jjtree.closeNodeScope(jjtn000, true);
               jjtc000 = false;
               if (this.types.get(jjtn000.name) == null) {
                  System.err.println("ERROR: DDL specifying type " + jjtn000.name + " which has not been defined");
                  throw new RuntimeException("specifying type " + jjtn000.name + " which has not been defined");
               } else {
                  jjtn000.jjtAddChild((Node)this.types.get(jjtn000.name), 0);
                  DynamicSerDeFieldType var3 = jjtn000;
                  return var3;
               }
         }
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         } else if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         } else {
            throw (Error)jjte000;
         }
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }
   }

   public final DynamicSerDeTypeString TypeString() throws ParseException {
      DynamicSerDeTypeString jjtn000 = new DynamicSerDeTypeString(37);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypeString var3;
      try {
         this.jj_consume_token(35);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypeByte TypeByte() throws ParseException {
      DynamicSerDeTypeByte jjtn000 = new DynamicSerDeTypeByte(38);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypeByte var3;
      try {
         this.jj_consume_token(30);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypei16 Typei16() throws ParseException {
      DynamicSerDeTypei16 jjtn000 = new DynamicSerDeTypei16(39);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypei16 var3;
      try {
         this.jj_consume_token(31);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypei32 Typei32() throws ParseException {
      DynamicSerDeTypei32 jjtn000 = new DynamicSerDeTypei32(40);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypei32 var3;
      try {
         this.jj_consume_token(32);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypei64 Typei64() throws ParseException {
      DynamicSerDeTypei64 jjtn000 = new DynamicSerDeTypei64(41);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypei64 var3;
      try {
         this.jj_consume_token(33);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypeDouble TypeDouble() throws ParseException {
      DynamicSerDeTypeDouble jjtn000 = new DynamicSerDeTypeDouble(42);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypeDouble var3;
      try {
         this.jj_consume_token(34);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypeBool TypeBool() throws ParseException {
      DynamicSerDeTypeBool jjtn000 = new DynamicSerDeTypeBool(43);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypeBool var3;
      try {
         this.jj_consume_token(29);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypeMap TypeMap() throws ParseException {
      DynamicSerDeTypeMap jjtn000 = new DynamicSerDeTypeMap(44);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypeMap var3;
      try {
         this.jj_consume_token(38);
         this.jj_consume_token(69);
         this.FieldType();
         this.jj_consume_token(59);
         this.FieldType();
         this.jj_consume_token(70);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypeSet TypeSet() throws ParseException {
      DynamicSerDeTypeSet jjtn000 = new DynamicSerDeTypeSet(45);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypeSet var3;
      try {
         this.jj_consume_token(40);
         this.jj_consume_token(69);
         this.FieldType();
         this.jj_consume_token(70);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   public final DynamicSerDeTypeList TypeList() throws ParseException {
      DynamicSerDeTypeList jjtn000 = new DynamicSerDeTypeList(46);
      boolean jjtc000 = true;
      this.jjtree.openNodeScope(jjtn000);

      DynamicSerDeTypeList var3;
      try {
         this.jj_consume_token(39);
         this.jj_consume_token(69);
         this.FieldType();
         this.jj_consume_token(70);
         this.jjtree.closeNodeScope(jjtn000, true);
         jjtc000 = false;
         var3 = jjtn000;
      } catch (Throwable jjte000) {
         if (jjtc000) {
            this.jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
         } else {
            this.jjtree.popNode();
         }

         if (jjte000 instanceof RuntimeException) {
            throw (RuntimeException)jjte000;
         }

         if (jjte000 instanceof ParseException) {
            throw (ParseException)jjte000;
         }

         throw (Error)jjte000;
      } finally {
         if (jjtc000) {
            this.jjtree.closeNodeScope(jjtn000, true);
         }

      }

      return var3;
   }

   private static void jj_la1_init_0() {
      jj_la1_0 = new int[]{0, 256, 171961856, 171961856, 37744128, 256, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1342177280, 0, 0, 0, 0, -1610612736, 0, 0, 0, 0, -1610612736, -1342177280, -1610612736};
   }

   private static void jj_la1_init_1() {
      jj_la1_1 = new int[]{402653184, 402758688, 0, 0, 0, 105504, 72736, 402653184, 4194304, Integer.MIN_VALUE, 402653184, 33554432, 402653184, 402653184, 577765376, 402653184, 577765376, 402653184, 577765376, 577765376, 4195279, 8192, 402653184, 512, 16384, 6160847, 1048576, 402653184, 917504, Integer.MIN_VALUE, 463, 4194767, 4194767};
   }

   private static void jj_la1_init_2() {
      jj_la1_2 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
   }

   public thrift_grammar(InputStream stream) {
      this(stream, (String)null);
   }

   public thrift_grammar(InputStream stream, String encoding) {
      this.jjtree = new JJTthrift_grammarState();
      this.include_path = null;
      this.jj_la1 = new int[33];
      this.jj_expentries = new ArrayList();
      this.jj_kind = -1;

      try {
         this.jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }

      this.token_source = new thrift_grammarTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 33; ++i) {
         this.jj_la1[i] = -1;
      }

   }

   public void ReInit(InputStream stream) {
      this.ReInit(stream, (String)null);
   }

   public void ReInit(InputStream stream, String encoding) {
      try {
         this.jj_input_stream.ReInit(stream, encoding, 1, 1);
      } catch (UnsupportedEncodingException e) {
         throw new RuntimeException(e);
      }

      this.token_source.ReInit(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jjtree.reset();
      this.jj_gen = 0;

      for(int i = 0; i < 33; ++i) {
         this.jj_la1[i] = -1;
      }

   }

   public thrift_grammar(Reader stream) {
      this.jjtree = new JJTthrift_grammarState();
      this.include_path = null;
      this.jj_la1 = new int[33];
      this.jj_expentries = new ArrayList();
      this.jj_kind = -1;
      this.jj_input_stream = new SimpleCharStream(stream, 1, 1);
      this.token_source = new thrift_grammarTokenManager(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 33; ++i) {
         this.jj_la1[i] = -1;
      }

   }

   public void ReInit(Reader stream) {
      this.jj_input_stream.ReInit((Reader)stream, 1, 1);
      this.token_source.ReInit(this.jj_input_stream);
      this.token = new Token();
      this.jj_ntk = -1;
      this.jjtree.reset();
      this.jj_gen = 0;

      for(int i = 0; i < 33; ++i) {
         this.jj_la1[i] = -1;
      }

   }

   public thrift_grammar(thrift_grammarTokenManager tm) {
      this.jjtree = new JJTthrift_grammarState();
      this.include_path = null;
      this.jj_la1 = new int[33];
      this.jj_expentries = new ArrayList();
      this.jj_kind = -1;
      this.token_source = tm;
      this.token = new Token();
      this.jj_ntk = -1;
      this.jj_gen = 0;

      for(int i = 0; i < 33; ++i) {
         this.jj_la1[i] = -1;
      }

   }

   public void ReInit(thrift_grammarTokenManager tm) {
      this.token_source = tm;
      this.token = new Token();
      this.jj_ntk = -1;
      this.jjtree.reset();
      this.jj_gen = 0;

      for(int i = 0; i < 33; ++i) {
         this.jj_la1[i] = -1;
      }

   }

   private Token jj_consume_token(int kind) throws ParseException {
      Token oldToken;
      if ((oldToken = this.token).next != null) {
         this.token = this.token.next;
      } else {
         this.token = this.token.next = this.token_source.getNextToken();
      }

      this.jj_ntk = -1;
      if (this.token.kind == kind) {
         ++this.jj_gen;
         return this.token;
      } else {
         this.token = oldToken;
         this.jj_kind = kind;
         throw this.generateParseException();
      }
   }

   public final Token getNextToken() {
      if (this.token.next != null) {
         this.token = this.token.next;
      } else {
         this.token = this.token.next = this.token_source.getNextToken();
      }

      this.jj_ntk = -1;
      ++this.jj_gen;
      return this.token;
   }

   public final Token getToken(int index) {
      Token t = this.token;

      for(int i = 0; i < index; ++i) {
         if (t.next != null) {
            t = t.next;
         } else {
            t = t.next = this.token_source.getNextToken();
         }
      }

      return t;
   }

   private int jj_ntk() {
      return (this.jj_nt = this.token.next) == null ? (this.jj_ntk = (this.token.next = this.token_source.getNextToken()).kind) : (this.jj_ntk = this.jj_nt.kind);
   }

   public ParseException generateParseException() {
      this.jj_expentries.clear();
      boolean[] la1tokens = new boolean[71];
      if (this.jj_kind >= 0) {
         la1tokens[this.jj_kind] = true;
         this.jj_kind = -1;
      }

      for(int i = 0; i < 33; ++i) {
         if (this.jj_la1[i] == this.jj_gen) {
            for(int j = 0; j < 32; ++j) {
               if ((jj_la1_0[i] & 1 << j) != 0) {
                  la1tokens[j] = true;
               }

               if ((jj_la1_1[i] & 1 << j) != 0) {
                  la1tokens[32 + j] = true;
               }

               if ((jj_la1_2[i] & 1 << j) != 0) {
                  la1tokens[64 + j] = true;
               }
            }
         }
      }

      for(int i = 0; i < 71; ++i) {
         if (la1tokens[i]) {
            this.jj_expentry = new int[1];
            this.jj_expentry[0] = i;
            this.jj_expentries.add(this.jj_expentry);
         }
      }

      int[][] exptokseq = new int[this.jj_expentries.size()][];

      for(int i = 0; i < this.jj_expentries.size(); ++i) {
         exptokseq[i] = (int[])this.jj_expentries.get(i);
      }

      return new ParseException(this.token, exptokseq, tokenImage);
   }

   public final void enable_tracing() {
   }

   public final void disable_tracing() {
   }

   static {
      jj_la1_init_0();
      jj_la1_init_1();
      jj_la1_init_2();
   }
}
