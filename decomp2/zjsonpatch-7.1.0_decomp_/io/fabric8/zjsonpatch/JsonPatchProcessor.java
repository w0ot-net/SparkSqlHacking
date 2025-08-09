package io.fabric8.zjsonpatch;

import com.fasterxml.jackson.databind.JsonNode;

public interface JsonPatchProcessor {
   void remove(JsonPointer var1) throws JsonPointerEvaluationException;

   void replace(JsonPointer var1, JsonNode var2) throws JsonPointerEvaluationException;

   void add(JsonPointer var1, JsonNode var2) throws JsonPointerEvaluationException;

   void move(JsonPointer var1, JsonPointer var2) throws JsonPointerEvaluationException;

   void copy(JsonPointer var1, JsonPointer var2) throws JsonPointerEvaluationException;

   void test(JsonPointer var1, JsonNode var2) throws JsonPointerEvaluationException;
}
