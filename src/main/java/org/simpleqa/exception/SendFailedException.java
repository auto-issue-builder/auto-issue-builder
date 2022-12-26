package org.simpleqa.exception;

public class SendFailedException extends RuntimeException {
    private static final String MESSAGE = "이슈등록에 실패하였습니다.\n";

    public SendFailedException(String message) {
        super(MESSAGE + message);
    }
}
