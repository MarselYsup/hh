package com.technokratos.validation.constants;

public final class PasswordFormatConst {
    public static final String DIGIT_REGEX = "(?=.*[0-9])";

    public static final String LOWER_CASE_REGEX = "(?=.*[a-z])";

    public static final String UPPER_CASE_REGEX = "(?=.*[A-Z])";

    public static final String PREFIX_SIZE = ".{";

    public static final char SEPARATOR_SIZE = ',';

    public  static final char SUFFIX_SIZE='}';

    public static final String PREFIX_SPECIAL_CHAR = "(?=.*[";

    public static final String SUFFIX_SPECIAL_CHAR = "])";

    public static final char BEGIN_PASSWORD_REGEX = '^';

    public static final char END_PASSWORD_REGEX = '$';
}
