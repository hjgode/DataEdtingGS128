package hsm.dataeditgs128;

public class Consts {
    public static final String TAG = "DataEditGS128: ";

    // Result success, continue further processing, wedge
    public static final int DATA_EDIT_RESULT_SUCCESS = 0;
    // Result continue, continue further processing, wedge
    public static final int DATA_EDIT_RESULT_CONTINUE = 1;
    // Result handled, stop further processing, no wedge
    public static final int DATA_EDIT_RESULT_HANDLED = 2;
    // Result error, stop further processing and bad read notification, no wedge
    public static final int DATA_EDIT_RESULT_ERROR = 3;

}
