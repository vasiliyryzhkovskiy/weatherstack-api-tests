package helpers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:application.properties")
public interface ConfigVars extends Config {

    @Key("host")
    String host();

    @Key("access_key")
    String accessKey();

    @Key("access_key_invalid")
    String accessKeyInavalid();

}
