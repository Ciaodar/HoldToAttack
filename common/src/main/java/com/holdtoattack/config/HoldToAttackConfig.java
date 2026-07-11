package com.holdtoattack.config;

public class HoldToAttackConfig {
    private static IConfigProxy proxy;

    public static void setProxy(IConfigProxy configProxy) {
        proxy = configProxy;
    }

    public static boolean isEnabled() {
        if (proxy != null) {
            return proxy.isEnabled();
        }
        return true; // Default fallback
    }

    public static boolean isRequireTarget() {
        if (proxy != null) {
            return proxy.isRequireTarget();
        }
        return true; // Default fallback
    }
}
