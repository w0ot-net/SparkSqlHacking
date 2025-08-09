package org.antlr.runtime;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializedGrammar {
   public static final String COOKIE = "$ANTLR";
   public static final int FORMAT_VERSION = 1;
   public String name;
   public char type;
   public List rules;

   public SerializedGrammar(String filename) throws IOException {
      System.out.println("loading " + filename);
      FileInputStream fis = new FileInputStream(filename);
      BufferedInputStream bos = new BufferedInputStream(fis);
      DataInputStream in = new DataInputStream(bos);
      this.readFile(in);
      in.close();
   }

   protected void readFile(DataInputStream in) throws IOException {
      String cookie = this.readString(in);
      if (!cookie.equals("$ANTLR")) {
         throw new IOException("not a serialized grammar file");
      } else {
         int version = in.readByte();
         char grammarType = (char)in.readByte();
         this.type = grammarType;
         String grammarName = this.readString(in);
         this.name = grammarName;
         System.out.println(grammarType + " grammar " + grammarName);
         int numRules = in.readShort();
         System.out.println("num rules = " + numRules);
         this.rules = this.readRules(in, numRules);
      }
   }

   protected List readRules(DataInputStream in, int numRules) throws IOException {
      List<Rule> rules = new ArrayList();

      for(int i = 0; i < numRules; ++i) {
         Rule r = this.readRule(in);
         rules.add(r);
      }

      return rules;
   }

   protected Rule readRule(DataInputStream in) throws IOException {
      byte R = in.readByte();
      if (R != 82) {
         throw new IOException("missing R on start of rule");
      } else {
         String name = this.readString(in);
         System.out.println("rule: " + name);
         byte B = in.readByte();
         Block b = this.readBlock(in);
         byte period = in.readByte();
         if (period != 46) {
            throw new IOException("missing . on end of rule");
         } else {
            return new Rule(name, b);
         }
      }
   }

   protected Block readBlock(DataInputStream in) throws IOException {
      int nalts = in.readShort();
      List<Node>[] alts = new List[nalts];

      for(int i = 0; i < nalts; ++i) {
         List<Node> alt = this.readAlt(in);
         alts[i] = alt;
      }

      return new Block(alts);
   }

   protected List readAlt(DataInputStream in) throws IOException {
      List<Node> alt = new ArrayList();
      byte A = in.readByte();
      if (A != 65) {
         throw new IOException("missing A on start of alt");
      } else {
         for(byte cmd = in.readByte(); cmd != 59; cmd = in.readByte()) {
            switch (cmd) {
               case 45:
                  int from = in.readChar();
                  int to = in.readChar();
               case 46:
               default:
                  break;
               case 66:
                  Block b = this.readBlock(in);
                  alt.add(b);
                  break;
               case 114:
                  int ruleIndex = in.readShort();
                  alt.add(new RuleRef(ruleIndex));
                  break;
               case 116:
                  int ttype = in.readShort();
                  alt.add(new TokenRef(ttype));
                  break;
               case 126:
                  int notThisTokenType = in.readShort();
            }
         }

         return alt;
      }
   }

   protected String readString(DataInputStream in) throws IOException {
      byte c = in.readByte();

      StringBuilder buf;
      for(buf = new StringBuilder(); c != 59; c = in.readByte()) {
         buf.append((char)c);
      }

      return buf.toString();
   }

   public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append(this.type).append(" grammar ").append(this.name);
      buf.append(this.rules);
      return buf.toString();
   }

   protected class Rule {
      String name;
      Block block;

      public Rule(String name, Block block) {
         this.name = name;
         this.block = block;
      }

      public String toString() {
         return this.name + ":" + this.block;
      }
   }

   protected abstract class Node {
      public abstract String toString();
   }

   protected class Block extends Node {
      List[] alts;

      public Block(List[] alts) {
         this.alts = alts;
      }

      public String toString() {
         StringBuilder buf = new StringBuilder();
         buf.append("(");

         for(int i = 0; i < this.alts.length; ++i) {
            List<?> alt = this.alts[i];
            if (i > 0) {
               buf.append("|");
            }

            buf.append(alt.toString());
         }

         buf.append(")");
         return buf.toString();
      }
   }

   protected class TokenRef extends Node {
      int ttype;

      public TokenRef(int ttype) {
         this.ttype = ttype;
      }

      public String toString() {
         return String.valueOf(this.ttype);
      }
   }

   protected class RuleRef extends Node {
      int ruleIndex;

      public RuleRef(int ruleIndex) {
         this.ruleIndex = ruleIndex;
      }

      public String toString() {
         return String.valueOf(this.ruleIndex);
      }
   }
}
