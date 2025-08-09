package org.apache.spark.network.shuffle;

public record ShuffleIndexRecord(long offset, long length) {
}
