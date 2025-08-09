package com.ibm.icu.text;

import com.ibm.icu.impl.Assert;
import com.ibm.icu.impl.ICUBinary;
import com.ibm.icu.impl.ICUDebug;
import com.ibm.icu.impl.RBBIDataWrapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RBBIRuleBuilder {
   String fDebugEnv = ICUDebug.enabled("rbbi") ? ICUDebug.value("rbbi") : null;
   String fRules;
   StringBuilder fStrippedRules;
   RBBIRuleScanner fScanner;
   RBBINode[] fTreeRoots = new RBBINode[4];
   static final int fForwardTree = 0;
   static final int fReverseTree = 1;
   static final int fSafeFwdTree = 2;
   static final int fSafeRevTree = 3;
   int fDefaultTree = 0;
   boolean fChainRules;
   boolean fLookAheadHardBreak;
   RBBISetBuilder fSetBuilder;
   List fUSetNodes;
   RBBITableBuilder fForwardTable;
   Map fStatusSets = new HashMap();
   List fRuleStatusVals;
   static final int U_ILLEGAL_CHAR_FOUND = 12;
   static final int U_BRK_ERROR_START = 66048;
   static final int U_BRK_INTERNAL_ERROR = 66049;
   static final int U_BRK_HEX_DIGITS_EXPECTED = 66050;
   static final int U_BRK_SEMICOLON_EXPECTED = 66051;
   static final int U_BRK_RULE_SYNTAX = 66052;
   static final int U_BRK_UNCLOSED_SET = 66053;
   static final int U_BRK_ASSIGN_ERROR = 66054;
   static final int U_BRK_VARIABLE_REDFINITION = 66055;
   static final int U_BRK_MISMATCHED_PAREN = 66056;
   static final int U_BRK_NEW_LINE_IN_QUOTED_STRING = 66057;
   static final int U_BRK_UNDEFINED_VARIABLE = 66058;
   static final int U_BRK_INIT_ERROR = 66059;
   static final int U_BRK_RULE_EMPTY_SET = 66060;
   static final int U_BRK_UNRECOGNIZED_OPTION = 66061;
   static final int U_BRK_MALFORMED_RULE_TAG = 66062;
   static final int U_BRK_MALFORMED_SET = 66063;
   static final int U_BRK_ERROR_LIMIT = 66064;

   RBBIRuleBuilder(String rules) {
      this.fRules = rules;
      this.fStrippedRules = new StringBuilder(rules);
      this.fUSetNodes = new ArrayList();
      this.fRuleStatusVals = new ArrayList();
      this.fScanner = new RBBIRuleScanner(this);
      this.fSetBuilder = new RBBISetBuilder(this);
   }

   static final int align8(int i) {
      return i + 7 & -8;
   }

   void flattenData(OutputStream os) throws IOException {
      DataOutputStream dos = new DataOutputStream(os);
      String strippedRules = RBBIRuleScanner.stripRules(this.fStrippedRules.toString());
      int headerSize = 80;
      int forwardTableSize = align8(this.fForwardTable.getTableSize());
      int reverseTableSize = align8(this.fForwardTable.getSafeTableSize());
      int trieSize = align8(this.fSetBuilder.getTrieSize());
      int statusTableSize = align8(this.fRuleStatusVals.size() * 4);
      byte[] strippedRulesUTF8 = strippedRules.getBytes(StandardCharsets.UTF_8);
      int rulesSize = align8(strippedRulesUTF8.length + 1);
      int totalSize = headerSize + forwardTableSize + reverseTableSize + statusTableSize + trieSize + rulesSize;
      int outputPos = 0;
      ICUBinary.writeHeader(1114794784, 100663296, 0, dos);
      int[] header = new int[20];
      header[0] = 45472;
      header[1] = 100663296;
      header[2] = totalSize;
      header[3] = this.fSetBuilder.getNumCharCategories();
      header[4] = headerSize;
      header[5] = forwardTableSize;
      header[6] = header[4] + forwardTableSize;
      header[7] = reverseTableSize;
      header[8] = header[6] + header[7];
      header[9] = this.fSetBuilder.getTrieSize();
      header[12] = header[8] + trieSize;
      header[13] = statusTableSize;
      header[10] = header[12] + statusTableSize;
      header[11] = strippedRulesUTF8.length;

      for(int i = 0; i < header.length; ++i) {
         dos.writeInt(header[i]);
         outputPos += 4;
      }

      RBBIDataWrapper.RBBIStateTable table = this.fForwardTable.exportTable();

      assert outputPos == header[4];

      outputPos += table.put(dos);
      table = this.fForwardTable.exportSafeTable();
      Assert.assrt(outputPos == header[6]);
      outputPos += table.put(dos);
      Assert.assrt(outputPos == header[8]);
      this.fSetBuilder.serializeTrie(os);

      for(outputPos += header[9]; outputPos % 8 != 0; ++outputPos) {
         dos.write(0);
      }

      Assert.assrt(outputPos == header[12]);

      for(Integer val : this.fRuleStatusVals) {
         dos.writeInt(val);
         outputPos += 4;
      }

      while(outputPos % 8 != 0) {
         dos.write(0);
         ++outputPos;
      }

      Assert.assrt(outputPos == header[10]);
      dos.write(strippedRulesUTF8, 0, strippedRulesUTF8.length);
      dos.write(0);

      for(int var21 = outputPos + strippedRulesUTF8.length + 1; var21 % 8 != 0; ++var21) {
         dos.write(0);
      }

   }

   static void compileRules(String rules, OutputStream os) throws IOException {
      RBBIRuleBuilder builder = new RBBIRuleBuilder(rules);
      builder.build(os);
   }

   void build(OutputStream os) throws IOException {
      this.fScanner.parse();
      this.fSetBuilder.buildRanges();
      this.fForwardTable = new RBBITableBuilder(this, 0);
      this.fForwardTable.buildForwardTable();
      this.optimizeTables();
      this.fForwardTable.buildSafeReverseTable();
      if (this.fDebugEnv != null && this.fDebugEnv.indexOf("states") >= 0) {
         this.fForwardTable.printStates();
         this.fForwardTable.printRuleStatusTable();
         this.fForwardTable.printReverseTable();
      }

      this.fSetBuilder.buildTrie();
      this.flattenData(os);
   }

   void optimizeTables() {
      boolean didSomething;
      do {
         didSomething = false;

         for(IntPair duplPair = new IntPair(3, 0); this.fForwardTable.findDuplCharClassFrom(duplPair); didSomething = true) {
            this.fSetBuilder.mergeCategories(duplPair);
            this.fForwardTable.removeColumn(duplPair.second);
         }

         while(this.fForwardTable.removeDuplicateStates() > 0) {
            didSomething = true;
         }
      } while(didSomething);

   }

   static class IntPair {
      int first = 0;
      int second = 0;

      IntPair() {
      }

      IntPair(int f, int s) {
         this.first = f;
         this.second = s;
      }
   }
}
