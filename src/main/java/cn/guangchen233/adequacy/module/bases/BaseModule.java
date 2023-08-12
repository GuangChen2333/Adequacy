package cn.guangchen233.adequacy.module.bases;

import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.interfaces.AbstractModule;
import org.apache.logging.log4j.LogManager;

public class BaseModule extends AbstractModule {
    public BaseModule() {
        this.name = getDetails().name();
        this.logger = LogManager.getLogger("Adequacy/" + name);
        this.description = getDetails().description();
        this.category = getDetails().category();
        this.show = getDetails().showOnList();
        this.keyBind = getDetails().defaultKeyBind();
        this.setToggle(getDetails().defaultEnable());
    }

    private ModuleDetails getDetails() {
        if (getClass().isAnnotationPresent(ModuleDetails.class)) {
            return getClass().getAnnotation(ModuleDetails.class);
        }
        throw new IllegalStateException("No annotation on target class " + this.getClass().getCanonicalName() + "!");
    }
}
