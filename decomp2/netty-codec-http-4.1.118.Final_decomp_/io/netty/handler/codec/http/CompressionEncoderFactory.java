package io.netty.handler.codec.http;

import io.netty.handler.codec.MessageToByteEncoder;

interface CompressionEncoderFactory {
   MessageToByteEncoder createEncoder();
}
