package com.minhaz.productmanagement.component.route;

public abstract class ApiProvider {

    public static final String SEPARATOR = "/";
    public static final String BASEPATH = SEPARATOR + "api";
    public static final String VERSION = SEPARATOR + "v1.0";

    public static final String OPEN_PARENTHESIS = "{";
    public static final String CLOSE_PARENTHESIS = "}";
    public static final String IDENTIFIER = SEPARATOR + OPEN_PARENTHESIS + "id" + CLOSE_PARENTHESIS;

    public static class ChargeConfig {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "charge-configs";
        public static final String CHARGE_CONFIG_IDENTIFIER = IDENTIFIER;
    }

    public static class Keyword {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "keywords";
        public static final String KEYWORD_IDENTIFIER = IDENTIFIER;
    }

    public static class ChargeFailureLog {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "charges-failure-logs";
        public static final String CHARGE_FAILURE_LOG_IDENTIFIER = IDENTIFIER;
    }

    public static class ChargeSuccessLog {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "charge-success-logs";
        public static final String CHARGE_SUCCESS_LOG_IDENTIFIER = IDENTIFIER;
    }

    public static class InboxLog {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "inbox";
        public static final String INBOX_IDENTIFIER = IDENTIFIER;
    }

    //    ================== Third Party Apis =========================
    public static class ThirdPartyApi {
        public static final String CONTENT_FETCHING_URL = "http://demo.webmanza.com" + SEPARATOR + "a55dbz923ace647v" + BASEPATH + VERSION + SEPARATOR + "services" + SEPARATOR + "content";
    }


}