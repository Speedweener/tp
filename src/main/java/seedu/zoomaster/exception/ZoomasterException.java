package seedu.zoomaster.exception;

/**
 * The class DukeException is a subclass of Exception.
 * It is thrown when an error specific to Duke occurs.
 */
//@@author xingrong123
public class ZoomasterException extends Exception {
    private ZoomasterExceptionType zoomasterExceptionType;
    private String info;

    /**
     * Constructs a new DukeException instance by storing the given DukeExceptionType.
     *
     * @param zoomasterExceptionType The type of dukeException.
     */
    public ZoomasterException(ZoomasterExceptionType zoomasterExceptionType) {
        this.zoomasterExceptionType = zoomasterExceptionType;
    }

    /**
     * Constructs a new DukeException instance by storing the given DukeExceptionType and message.
     *
     * @param zoomasterExceptionType The type of dukeException.
     * @param info Additional information of the exception.
     */
    public ZoomasterException(ZoomasterExceptionType zoomasterExceptionType, String info) {
        this.zoomasterExceptionType = zoomasterExceptionType;
        this.info = info;
    }

    /**
     * Returns the dukeExceptionType of the dukeException.
     *
     * @return the dukeExceptionType.
     */
    public ZoomasterExceptionType getError() {
        return zoomasterExceptionType;
    }

    public String getInfo() {
        return info;
    }


}
