package shaded.parquet.com.fasterxml.jackson.databind.node;

import java.math.BigDecimal;
import java.math.BigInteger;
import shaded.parquet.com.fasterxml.jackson.core.JsonPointer;
import shaded.parquet.com.fasterxml.jackson.core.JsonToken;
import shaded.parquet.com.fasterxml.jackson.databind.JsonNode;
import shaded.parquet.com.fasterxml.jackson.databind.util.RawValue;

public abstract class ContainerNode extends BaseJsonNode implements JsonNodeCreator {
   private static final long serialVersionUID = 1L;
   protected final JsonNodeFactory _nodeFactory;

   protected ContainerNode(JsonNodeFactory nc) {
      this._nodeFactory = nc;
   }

   protected ContainerNode() {
      this._nodeFactory = null;
   }

   public abstract JsonToken asToken();

   public String asText() {
      return "";
   }

   public abstract int size();

   public abstract JsonNode get(int var1);

   public abstract JsonNode get(String var1);

   protected abstract ObjectNode _withObject(JsonPointer var1, JsonPointer var2, JsonNode.OverwriteMode var3, boolean var4);

   public final BooleanNode booleanNode(boolean v) {
      return this._nodeFactory.booleanNode(v);
   }

   public JsonNode missingNode() {
      return this._nodeFactory.missingNode();
   }

   public final NullNode nullNode() {
      return this._nodeFactory.nullNode();
   }

   public final ArrayNode arrayNode() {
      return this._nodeFactory.arrayNode();
   }

   public final ArrayNode arrayNode(int capacity) {
      return this._nodeFactory.arrayNode(capacity);
   }

   public final ObjectNode objectNode() {
      return this._nodeFactory.objectNode();
   }

   public final NumericNode numberNode(byte v) {
      return this._nodeFactory.numberNode(v);
   }

   public final NumericNode numberNode(short v) {
      return this._nodeFactory.numberNode(v);
   }

   public final NumericNode numberNode(int v) {
      return this._nodeFactory.numberNode(v);
   }

   public final NumericNode numberNode(long v) {
      return this._nodeFactory.numberNode(v);
   }

   public final NumericNode numberNode(float v) {
      return this._nodeFactory.numberNode(v);
   }

   public final NumericNode numberNode(double v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(BigInteger v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(BigDecimal v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(Byte v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(Short v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(Integer v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(Long v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(Float v) {
      return this._nodeFactory.numberNode(v);
   }

   public final ValueNode numberNode(Double v) {
      return this._nodeFactory.numberNode(v);
   }

   public final TextNode textNode(String text) {
      return this._nodeFactory.textNode(text);
   }

   public final BinaryNode binaryNode(byte[] data) {
      return this._nodeFactory.binaryNode(data);
   }

   public final BinaryNode binaryNode(byte[] data, int offset, int length) {
      return this._nodeFactory.binaryNode(data, offset, length);
   }

   public final ValueNode pojoNode(Object pojo) {
      return this._nodeFactory.pojoNode(pojo);
   }

   public final ValueNode rawValueNode(RawValue value) {
      return this._nodeFactory.rawValueNode(value);
   }

   public abstract ContainerNode removeAll();
}
