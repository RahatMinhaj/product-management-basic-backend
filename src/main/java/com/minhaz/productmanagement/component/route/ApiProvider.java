package com.minhaz.productmanagement.component.route;

public abstract class ApiProvider {

    public static final String SEPARATOR = "/";
    public static final String DASH = "-";
    public static final String BASEPATH = SEPARATOR + "api";
    public static final String OPENBASE_PATH = "open";
    public static final String VERSION = "/v1";
    public static final String OPEN_PATH = BASEPATH + SEPARATOR + "v1/open/**";
    public static final String SWAGGER_PATH = SEPARATOR + "swagger-ui/**";
    public static final String API_DOCS_PATH = SEPARATOR + "v3/api-docs/**";

    public static final String OPEN_PARENTHESIS = "{";
    public static final String CLOSE_PARENTHESIS = "}";
    public static final String IDENTIFIER = SEPARATOR + OPEN_PARENTHESIS + "id" + CLOSE_PARENTHESIS;
    public static final String IDENTIFIER_USERNAME = SEPARATOR + OPEN_PARENTHESIS + "username" + CLOSE_PARENTHESIS;
    public static final String IDENTIFIER_CODE = SEPARATOR + OPEN_PARENTHESIS + "code" + CLOSE_PARENTHESIS;

    public static class ChargeConfig {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "charge-configs";
        public static final String CHARGE_CONFIG_IDENTIFIER = IDENTIFIER;
    }

    public static class Store {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "stores";
        public static final String STORE_IDENTIFIER = IDENTIFIER;
    }
    public static class Purchase {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "purchases";
        public static final String PURCHASE_IDENTIFIER = IDENTIFIER;
    }
    public static class Sale {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "sales";
        public static final String SALE_IDENTIFIER = IDENTIFIER;
    }

}