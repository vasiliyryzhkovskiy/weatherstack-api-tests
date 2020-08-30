package code;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
public enum ERROR_CODE {

    ERROR_CODE_101("101"),
    ERROR_CODE_105("105"),
    ERROR_CODE_603("603");

    private String code;

    private static Map<String, ERROR_CODE> ENUM_MAP;

    static {
        Map<String, ERROR_CODE> map = new ConcurrentHashMap<String, ERROR_CODE>();
        for (ERROR_CODE instance : ERROR_CODE.values()) {
            map.put(instance.getCode(), instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static ERROR_CODE get(String code) {
        return ENUM_MAP.get(code);
    }
}
