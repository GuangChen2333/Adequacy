package cn.guangchen233.adequacy.features.modules.render;

import cn.guangchen233.adequacy.module.ModuleCategory;
import cn.guangchen233.adequacy.module.annotations.ModuleDetails;
import cn.guangchen233.adequacy.module.bases.BaseModule;

@ModuleDetails(
        name = "FullBright",
        description = "There will always be light that will guide you on your way",
        category = ModuleCategory.RENDER,
        defaultEnable = true
)
public class FullBright extends BaseModule {
    @Override
    public void onEnable() {
        //TODO
    }
}
