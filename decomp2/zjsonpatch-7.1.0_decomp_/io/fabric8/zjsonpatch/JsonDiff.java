package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.fabric8.zjsonpatch.internal.collections4.ListUtils;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonDiff {
   private final List diffs = new ArrayList();
   private final EnumSet flags;
   public static final String OP = "op";
   public static final String VALUE = "value";
   public static final String PATH = "path";
   public static final String FROM = "from";
   public static final String FROM_VALUE = "fromValue";

   private JsonDiff(EnumSet flags) {
      this.flags = flags.clone();
   }

   public static JsonNode asJson(JsonNode source, JsonNode target) {
      return asJson(source, target, DiffFlags.defaults());
   }

   public static JsonNode asJson(JsonNode source, JsonNode target, EnumSet flags) {
      JsonDiff diff = new JsonDiff(flags);
      if (source == null && target != null) {
         diff.diffs.add(Diff.generateDiff(Operation.ADD, JsonPointer.ROOT, target));
      }

      if (source != null && target == null) {
         diff.diffs.add(Diff.generateDiff(Operation.REMOVE, JsonPointer.ROOT, source));
      }

      if (source != null && target != null) {
         diff.generateDiffs(JsonPointer.ROOT, source, target);
         if (!flags.contains(DiffFlags.OMIT_MOVE_OPERATION)) {
            diff.introduceMoveOperation();
         }

         if (!flags.contains(DiffFlags.OMIT_COPY_OPERATION)) {
            diff.introduceCopyOperation(source, target);
         }

         if (flags.contains(DiffFlags.ADD_EXPLICIT_REMOVE_ADD_ON_REPLACE)) {
            diff.introduceExplicitRemoveAndAddOperation();
         }
      }

      return diff.getJsonNodes();
   }

   private static JsonPointer getMatchingValuePath(Map unchangedValues, JsonNode value) {
      return (JsonPointer)unchangedValues.get(value);
   }

   private void introduceCopyOperation(JsonNode source, JsonNode target) {
      Map<JsonNode, JsonPointer> unchangedValues = getUnchangedPart(source, target);

      for(int i = 0; i < this.diffs.size(); ++i) {
         Diff diff = (Diff)this.diffs.get(i);
         if (Operation.ADD == diff.getOperation()) {
            JsonPointer matchingValuePath = getMatchingValuePath(unchangedValues, diff.getValue());
            if (matchingValuePath != null && isAllowed(matchingValuePath, diff.getPath())) {
               if (this.flags.contains(DiffFlags.EMIT_TEST_OPERATIONS)) {
                  this.diffs.add(i, new Diff(Operation.TEST, matchingValuePath, diff.getValue()));
                  ++i;
               }

               this.diffs.set(i, new Diff(Operation.COPY, matchingValuePath, diff.getPath()));
            }
         }
      }

   }

   private static boolean isNumber(String str) {
      int size = str.length();

      for(int i = 0; i < size; ++i) {
         if (!Character.isDigit(str.charAt(i))) {
            return false;
         }
      }

      return size > 0;
   }

   private static boolean isAllowed(JsonPointer source, JsonPointer destination) {
      boolean isSame = source.equals(destination);
      int i = 0;

      for(int j = 0; i < source.size() && j < destination.size(); ++j) {
         JsonPointer.RefToken srcValue = source.get(i);
         JsonPointer.RefToken dstValue = destination.get(j);
         String srcStr = srcValue.toString();
         String dstStr = dstValue.toString();
         if (isNumber(srcStr) && isNumber(dstStr) && srcStr.compareTo(dstStr) > 0) {
            return false;
         }

         ++i;
      }

      return !isSame;
   }

   private static Map getUnchangedPart(JsonNode source, JsonNode target) {
      Map<JsonNode, JsonPointer> unchangedValues = new HashMap();
      computeUnchangedValues(unchangedValues, JsonPointer.ROOT, source, target);
      return unchangedValues;
   }

   private static void computeUnchangedValues(Map unchangedValues, JsonPointer path, JsonNode source, JsonNode target) {
      if (source.equals(target)) {
         if (!unchangedValues.containsKey(target)) {
            unchangedValues.put(target, path);
         }

      } else {
         NodeType firstType = NodeType.getNodeType(source);
         NodeType secondType = NodeType.getNodeType(target);
         if (firstType == secondType) {
            switch (firstType) {
               case OBJECT:
                  computeObject(unchangedValues, path, source, target);
                  break;
               case ARRAY:
                  computeArray(unchangedValues, path, source, target);
            }
         }

      }
   }

   private static void computeArray(Map unchangedValues, JsonPointer path, JsonNode source, JsonNode target) {
      int size = Math.min(source.size(), target.size());

      for(int i = 0; i < size; ++i) {
         JsonPointer currPath = path.append(i);
         computeUnchangedValues(unchangedValues, currPath, source.get(i), target.get(i));
      }

   }

   private static void computeObject(Map unchangedValues, JsonPointer path, JsonNode source, JsonNode target) {
      Iterator<String> firstFields = source.fieldNames();

      while(firstFields.hasNext()) {
         String name = (String)firstFields.next();
         if (target.has(name)) {
            JsonPointer currPath = path.append(name);
            computeUnchangedValues(unchangedValues, currPath, source.get(name), target.get(name));
         }
      }

   }

   private void introduceMoveOperation() {
      for(int i = 0; i < this.diffs.size(); ++i) {
         Diff diff1 = (Diff)this.diffs.get(i);
         if (Operation.REMOVE == diff1.getOperation() || Operation.ADD == diff1.getOperation()) {
            for(int j = i + 1; j < this.diffs.size(); ++j) {
               Diff diff2 = (Diff)this.diffs.get(j);
               if (diff1.getValue().equals(diff2.getValue())) {
                  Diff moveDiff = null;
                  if (Operation.REMOVE == diff1.getOperation() && Operation.ADD == diff2.getOperation()) {
                     JsonPointer relativePath = computeRelativePath(diff2.getPath(), i + 1, j - 1, this.diffs);
                     moveDiff = new Diff(Operation.MOVE, diff1.getPath(), relativePath);
                  } else if (Operation.ADD == diff1.getOperation() && Operation.REMOVE == diff2.getOperation()) {
                     JsonPointer relativePath = computeRelativePath(diff2.getPath(), i, j - 1, this.diffs);
                     moveDiff = new Diff(Operation.MOVE, relativePath, diff1.getPath());
                  }

                  if (moveDiff != null) {
                     this.diffs.remove(j);
                     this.diffs.set(i, moveDiff);
                     break;
                  }
               }
            }
         }
      }

   }

   private void introduceExplicitRemoveAndAddOperation() {
      List<Diff> updatedDiffs = new ArrayList();

      for(Diff diff : this.diffs) {
         if (diff.getOperation().equals(Operation.REPLACE) && diff.getSrcValue() != null) {
            updatedDiffs.add(new Diff(Operation.REMOVE, diff.getPath(), diff.getSrcValue()));
            updatedDiffs.add(new Diff(Operation.ADD, diff.getPath(), diff.getValue()));
         } else {
            updatedDiffs.add(diff);
         }
      }

      this.diffs.clear();
      this.diffs.addAll(updatedDiffs);
   }

   private static JsonPointer computeRelativePath(JsonPointer path, int startIdx, int endIdx, List diffs) {
      List<Integer> counters = new ArrayList(path.size());

      for(int i = 0; i < path.size(); ++i) {
         counters.add(0);
      }

      for(int i = startIdx; i <= endIdx; ++i) {
         Diff diff = (Diff)diffs.get(i);
         if (Operation.ADD == diff.getOperation() || Operation.REMOVE == diff.getOperation()) {
            updatePath(path, diff, counters);
         }
      }

      return updatePathWithCounters(counters, path);
   }

   private static JsonPointer updatePathWithCounters(List counters, JsonPointer path) {
      List<JsonPointer.RefToken> tokens = path.decompose();

      for(int i = 0; i < counters.size(); ++i) {
         int value = (Integer)counters.get(i);
         if (value != 0) {
            int currValue = ((JsonPointer.RefToken)tokens.get(i)).getIndex();
            tokens.set(i, new JsonPointer.RefToken(Integer.toString(currValue + value)));
         }
      }

      return new JsonPointer(tokens);
   }

   private static void updatePath(JsonPointer path, Diff pseudo, List counters) {
      if (pseudo.getPath().size() <= path.size()) {
         int idx = -1;

         for(int i = 0; i < pseudo.getPath().size() - 1 && pseudo.getPath().get(i).equals(path.get(i)); idx = i++) {
         }

         if (idx == pseudo.getPath().size() - 2 && pseudo.getPath().get(pseudo.getPath().size() - 1).isArrayIndex()) {
            updateCounters(pseudo, pseudo.getPath().size() - 1, counters);
         }
      }

   }

   private static void updateCounters(Diff pseudo, int idx, List counters) {
      if (Operation.ADD == pseudo.getOperation()) {
         counters.set(idx, (Integer)counters.get(idx) - 1);
      } else if (Operation.REMOVE == pseudo.getOperation()) {
         counters.set(idx, (Integer)counters.get(idx) + 1);
      }

   }

   private ArrayNode getJsonNodes() {
      JsonNodeFactory FACTORY = JsonNodeFactory.instance;
      ArrayNode patch = FACTORY.arrayNode();

      for(Diff diff : this.diffs) {
         ObjectNode jsonNode = getJsonNode(FACTORY, diff, this.flags);
         patch.add(jsonNode);
      }

      return patch;
   }

   private static ObjectNode getJsonNode(JsonNodeFactory FACTORY, Diff diff, EnumSet flags) {
      ObjectNode jsonNode = FACTORY.objectNode();
      jsonNode.put("op", diff.getOperation().rfcName());
      switch (diff.getOperation()) {
         case MOVE:
         case COPY:
            jsonNode.put("from", diff.getPath().toString());
            jsonNode.put("path", diff.getToPath().toString());
            break;
         case REMOVE:
            jsonNode.put("path", diff.getPath().toString());
            if (!flags.contains(DiffFlags.OMIT_VALUE_ON_REMOVE)) {
               jsonNode.set("value", diff.getValue());
            }
            break;
         case REPLACE:
            if (flags.contains(DiffFlags.ADD_ORIGINAL_VALUE_ON_REPLACE)) {
               jsonNode.set("fromValue", diff.getSrcValue());
            }
         case ADD:
         case TEST:
            jsonNode.put("path", diff.getPath().toString());
            jsonNode.set("value", diff.getValue());
            break;
         default:
            throw new IllegalArgumentException("Unknown operation specified:" + diff.getOperation());
      }

      return jsonNode;
   }

   private void generateDiffs(JsonPointer path, JsonNode source, JsonNode target) {
      if (!source.equals(target)) {
         NodeType sourceType = NodeType.getNodeType(source);
         NodeType targetType = NodeType.getNodeType(target);
         if (sourceType == NodeType.ARRAY && targetType == NodeType.ARRAY) {
            this.compareArray(path, source, target);
         } else if (sourceType == NodeType.OBJECT && targetType == NodeType.OBJECT) {
            this.compareObjects(path, source, target);
         } else {
            if (this.flags.contains(DiffFlags.EMIT_TEST_OPERATIONS)) {
               this.diffs.add(new Diff(Operation.TEST, path, source));
            }

            this.diffs.add(Diff.generateDiff(Operation.REPLACE, path, source, target));
         }
      }

   }

   private void compareArray(JsonPointer path, JsonNode source, JsonNode target) {
      List<JsonNode> lcs = getLCS(source, target);
      int srcIdx = 0;
      int targetIdx = 0;
      int lcsIdx = 0;
      int srcSize = source.size();
      int targetSize = target.size();
      int lcsSize = lcs.size();
      int pos = 0;

      while(lcsIdx < lcsSize) {
         JsonNode lcsNode = (JsonNode)lcs.get(lcsIdx);
         JsonNode srcNode = source.get(srcIdx);
         JsonNode targetNode = target.get(targetIdx);
         if (lcsNode.equals(srcNode) && lcsNode.equals(targetNode)) {
            ++srcIdx;
            ++targetIdx;
            ++lcsIdx;
            ++pos;
         } else if (lcsNode.equals(srcNode)) {
            JsonPointer currPath = path.append(pos);
            this.diffs.add(Diff.generateDiff(Operation.ADD, currPath, targetNode));
            ++pos;
            ++targetIdx;
         } else if (lcsNode.equals(targetNode)) {
            JsonPointer currPath = path.append(pos);
            if (this.flags.contains(DiffFlags.EMIT_TEST_OPERATIONS)) {
               this.diffs.add(new Diff(Operation.TEST, currPath, srcNode));
            }

            this.diffs.add(Diff.generateDiff(Operation.REMOVE, currPath, srcNode));
            ++srcIdx;
         } else {
            JsonPointer currPath = path.append(pos);
            this.generateDiffs(currPath, srcNode, targetNode);
            ++srcIdx;
            ++targetIdx;
            ++pos;
         }
      }

      while(srcIdx < srcSize && targetIdx < targetSize) {
         JsonNode srcNode = source.get(srcIdx);
         JsonNode targetNode = target.get(targetIdx);
         JsonPointer currPath = path.append(pos);
         this.generateDiffs(currPath, srcNode, targetNode);
         ++srcIdx;
         ++targetIdx;
         ++pos;
      }

      pos = this.addRemaining(path, target, pos, targetIdx, targetSize);
      this.removeRemaining(path, pos, srcIdx, srcSize, source);
   }

   private void removeRemaining(JsonPointer path, int pos, int srcIdx, int srcSize, JsonNode source) {
      while(srcIdx < srcSize) {
         JsonPointer currPath = path.append(pos);
         if (this.flags.contains(DiffFlags.EMIT_TEST_OPERATIONS)) {
            this.diffs.add(new Diff(Operation.TEST, currPath, source.get(srcIdx)));
         }

         this.diffs.add(Diff.generateDiff(Operation.REMOVE, currPath, source.get(srcIdx)));
         ++srcIdx;
      }

   }

   private int addRemaining(JsonPointer path, JsonNode target, int pos, int targetIdx, int targetSize) {
      while(targetIdx < targetSize) {
         JsonNode jsonNode = target.get(targetIdx);
         JsonPointer currPath = path.append(pos);
         this.diffs.add(Diff.generateDiff(Operation.ADD, currPath, jsonNode.deepCopy()));
         ++pos;
         ++targetIdx;
      }

      return pos;
   }

   private void compareObjects(JsonPointer path, JsonNode source, JsonNode target) {
      Iterator<String> keysFromSrc = source.fieldNames();

      while(keysFromSrc.hasNext()) {
         String key = (String)keysFromSrc.next();
         if (!target.has(key)) {
            JsonPointer currPath = path.append(key);
            if (this.flags.contains(DiffFlags.EMIT_TEST_OPERATIONS)) {
               this.diffs.add(new Diff(Operation.TEST, currPath, source.get(key)));
            }

            this.diffs.add(Diff.generateDiff(Operation.REMOVE, currPath, source.get(key)));
         } else {
            JsonPointer currPath = path.append(key);
            this.generateDiffs(currPath, source.get(key), target.get(key));
         }
      }

      Iterator<String> keysFromTarget = target.fieldNames();

      while(keysFromTarget.hasNext()) {
         String key = (String)keysFromTarget.next();
         if (!source.has(key)) {
            JsonPointer currPath = path.append(key);
            this.diffs.add(Diff.generateDiff(Operation.ADD, currPath, target.get(key)));
         }
      }

   }

   private static List getLCS(JsonNode first, JsonNode second) {
      return ListUtils.longestCommonSubsequence(toList((ArrayNode)first), toList((ArrayNode)second));
   }

   static List toList(ArrayNode input) {
      int size = input.size();
      List<JsonNode> toReturn = new ArrayList(size);

      for(int i = 0; i < size; ++i) {
         toReturn.add(input.get(i));
      }

      return toReturn;
   }
}
