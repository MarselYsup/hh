package com.technokratos.validation.constants;

public final class WebsiteUrlFormatConst {

    public static final String HTTP_REGEX = "http";

    public static final String HTTPS_REGEX = "https";

    public static final String BEGIN_PROTOCOL_REGEX = "^(";

    public static final String SEPARATOR_PROTOCOL="|";

    public static final String END_PROTOCOL_REGEX = ")://";

    public static final String DOMAIN_REGEX = "[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

    public static final String ERROR_PROTOCOL = "At least one protocol must be selected!";
}
