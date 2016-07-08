package com.honliv.wechat.exception;

import com.dexcoder.commons.exceptions.DexcoderException;

/**
 * 自定义异常
 */
public class ScheduleException extends DexcoderException {

    /** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new ScheduleException.
     *
     * @param e the e
     */
    public ScheduleException(Throwable e) {
        super(e);
    }

    /**
     * Constructor
     *
     * @param message the message
     */
    public ScheduleException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param code the code
     * @param message the message
     */
    public ScheduleException(String code, String message) {
        super(code, message);
    }
}
