package cn.guangchen233.adequacy.module;

import cn.guangchen233.adequacy.features.modules.gui.ActiveModules;
import cn.guangchen233.adequacy.features.modules.gui.Logo;
import cn.guangchen233.adequacy.features.modules.render.FullBright;
import cn.guangchen233.adequacy.module.interfaces.AbstractModule;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleManager {
    private final List<AbstractModule> modules = new ArrayList<>();

    public ModuleManager() {
        registerModule(new Logo());
        registerModule(new ActiveModules());

        registerModule(new FullBright());
    }

    private void registerModule(AbstractModule module) {
        if (!modules.contains(module)) modules.add(module);
    }

    public List<AbstractModule> getModules() {
        return modules;
    }

    public List<AbstractModule> getShouldShowModules() {
        return this.getEnableModules().stream().filter(AbstractModule::isShow).collect(Collectors.toList());
    }

    public List<AbstractModule> getEnableModules() {
        return modules.stream().filter(AbstractModule::isEnabled).collect(Collectors.toList());
    }
}
