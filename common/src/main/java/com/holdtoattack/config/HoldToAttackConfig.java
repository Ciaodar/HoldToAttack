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

    private static boolean enabled = true;
    private static boolean requireTarget = true;
    private static boolean keepAttackFocus = true;

    // GSON fallback data class
    public static class Data {
        public boolean enabled = true;
        public boolean requireTarget = true;
        public boolean keepAttackFocus = true;
    }

    private static Data data = new Data();

    public static Data get() {
        return data;
    }

    public static void setProxy(IConfigProxy configProxy) {
        proxy = configProxy;
    }

    public static boolean isEnabled() {
        return proxy != null ? proxy.isEnabled() : (data != null ? data.enabled : enabled);
    }

    public static boolean isRequireTarget() {
        return proxy != null ? proxy.isRequireTarget() : (data != null ? data.requireTarget : requireTarget);
    }

    public static boolean isKeepAttackFocus() {
        return proxy != null ? proxy.isKeepAttackFocus() : keepAttackFocus;
    }

    public static void setKeepAttackFocus(boolean value) {
        keepAttackFocus = value;
        if (data != null) {
            data.keepAttackFocus = value;
        }
    }

    public static void setEnabled(boolean value) {
        enabled = value;
        if (data != null) {
            data.enabled = value;
        }
    }

    public static void setRequireTarget(boolean value) {
        requireTarget = value;
        if (data != null) {
            data.requireTarget = value;
        }
    }

    public static void init(File configDir) {
        file = new File(configDir, "holdtoattack.json");
        load();
    }

    public static void load() {
        if (file == null) {
            data = new Data();
            return;
        }
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                Data loaded = GSON.fromJson(reader, Data.class);
                if (loaded != null) {
                    data = loaded;
                    enabled = data.enabled;
                    requireTarget = data.requireTarget;
                    keepAttackFocus = data.keepAttackFocus;
                }
            } catch (Exception e) {
                e.printStackTrace();
                data = new Data();
            }
        } else {
            data = new Data();
            save();
        }
    }

    public static void save() {
        if (file == null) return;
        try (FileWriter writer = new FileWriter(file)) {
            if (data == null) {
                data = new Data();
            }
            data.enabled = enabled;
            data.requireTarget = requireTarget;
            data.keepAttackFocus = keepAttackFocus;
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
