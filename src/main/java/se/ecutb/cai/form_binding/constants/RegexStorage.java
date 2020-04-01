package se.ecutb.cai.form_binding.constants;

public class RegexStorage {
    public static final String EMAIL_REGEX = "\"^(\\\\D)+(\\\\w)*((\\\\.(\\\\w)+)?)+@(\\\\D)+(\\\\w)*((\\\\.(\\\\D)+(\\\\w)*)+)?(\\\\.)[a-z]{2,}$\"";
    public static final String PASSWORD_REGEX = "\"^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$\"";
}
