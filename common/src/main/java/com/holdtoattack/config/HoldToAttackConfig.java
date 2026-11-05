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

    public boolean enabled = true;
    public boolean requireTarget = true;

    private static HoldToAttackConfig instance;

    public static void setProxy(IConfigProxy configProxy) {
        proxy = configProxy;
    }

    public static void init(File configDir) {
        file = new File(configDir, "holdtoattack.json");
        load();
    }

    public static void load() {
        if (file == null) {
            instance = new HoldToAttackConfig();
            return;
        }
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                instance = GSON.fromJson(reader, HoldToAttackConfig.class);
            } catch (Exception e) {
                e.printStackTrace();
                instance = new HoldToAttackConfig();
            }
        } else {
            instance = new HoldToAttackConfig();
            save();
        }
        if (instance == null) {
            instance = new HoldToAttackConfig();
        }
    }

    public static void save() {
        if (file == null) return;
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(instance, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HoldToAttackConfig get() {
        if (instance == null) {
            instance = new HoldToAttackConfig();
        }
        return instance;
    }

    public static boolean isEnabled() {
        if (proxy != null) {
            return proxy.isEnabled();
        }
        return get().enabled;
    }

    public static boolean isRequireTarget() {
        if (proxy != null) {
            return proxy.isRequireTarget();
        }
        return get().requireTarget;
    }
}
