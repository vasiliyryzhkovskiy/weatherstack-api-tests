package helpers;

import org.aeonbits.owner.ConfigFactory;

public class TestHelper {

    private static ConfigVars configVars;

    public static ConfigVars getConfigVars() {
        if (configVars == null) {
            configVars = ConfigFactory.create(ConfigVars.class);
        }
        return configVars;
    }

}
