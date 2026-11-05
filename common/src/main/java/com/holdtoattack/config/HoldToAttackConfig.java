package com.holdtoattack.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HoldToAttackConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static File file;
    private static IConfigProxy proxy;

    public static class ConfigData {
        public boolean enabled = true;
        public boolean requireTarget = true;
        public boolean keepAttackFocus = true;
    }

    private static ConfigData fallback = new ConfigData();
    private static boolean keepAttackFocus = true;

    public static void setProxy(IConfigProxy configProxy) {
        proxy = configProxy;
    }

    public static void init(File configDir) {
        file = new File(configDir, "holdtoattack.json");
        load();
    }

    public static void load() {
        if (file != null && file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                ConfigData loaded = GSON.fromJson(reader, ConfigData.class);
                if (loaded != null) {
                    fallback = loaded;
                    keepAttackFocus = fallback.keepAttackFocus;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            save();
        }
    }

    public static void save() {
        if (file == null) return;
        fallback.keepAttackFocus = keepAttackFocus;
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(fallback, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigData get() {
        fallback.keepAttackFocus = keepAttackFocus;
        return fallback;
    }

    public static boolean isEnabled() {
        if (proxy != null) {
            return proxy.isEnabled();
        }
        return fallback.enabled;
    }

    public static void setEnabled(boolean value) {
        fallback.enabled = value;
    }

    public static boolean isRequireTarget() {
        if (proxy != null) {
            return proxy.isRequireTarget();
        }
        return fallback.requireTarget;
    }

    public static void setRequireTarget(boolean value) {
        fallback.requireTarget = value;
    }

    public static boolean isKeepAttackFocus() {
        return proxy != null ? proxy.isKeepAttackFocus() : keepAttackFocus;
    }

    public static void setKeepAttackFocus(boolean value) {
        keepAttackFocus = value;
        fallback.keepAttackFocus = value;
    }
}
