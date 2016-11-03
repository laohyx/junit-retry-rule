package com.kevinmost.junit_retry_rule;

import org.jetbrains.annotations.NotNull;

/**
 * An exception thrown to signal that a retry operation (executed via {@link RetryRule}) has retried more than the
 * allowed number of times, and has still failed.
 */
public final class RetryException extends RuntimeException {

  /**
   * @param errors the errors for each attempt at running this test-case
   */
  @NotNull public static RetryException from(@NotNull Throwable[] errors) {
    final StringBuilder msg = new StringBuilder("Invoked methods still failed after " + errors.length + " attempts.");
    for (int i = 0; i < errors.length; i++) {
      final Throwable error = errors[i];
      msg.append('\n');
      msg.append("Attempt #").append(i).append(" threw exception:");
      msg.append(error);
    }
    return new RetryException(msg.toString());
  }

  private RetryException(@NotNull String message) {
    super(message);
  }
}