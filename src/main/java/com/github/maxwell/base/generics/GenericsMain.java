package com.github.maxwell.base.generics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * Test Main
 */
@Slf4j
public class GenericsMain {
    public static void main(String[] args) {
        List<ModuleWrapper> modules = new LinkedList<>();
        ModuleWrapper rootModule = new ModuleWrapper();
        rootModule.setId(1);
        rootModule.setModuleName("CMS");
        rootModule.setParentId(0);
        modules.add(rootModule);
        TreeNode<ModuleWrapper> root = new TreeNode<>(rootModule);

        ModuleWrapper tmpModule = new ModuleWrapper();
        tmpModule.setId(2);
        tmpModule.setModuleName("审核");
        tmpModule.setParentId(1);
        modules.add(tmpModule);

        tmpModule = new ModuleWrapper();
        tmpModule.setId(3);
        tmpModule.setModuleName("运营");
        tmpModule.setParentId(1);
        modules.add(tmpModule);

        tmpModule = new ModuleWrapper();
        tmpModule.setId(4);
        tmpModule.setModuleName("直播");
        tmpModule.setParentId(1);
        modules.add(tmpModule);

        tmpModule = new ModuleWrapper();
        tmpModule.setId(5);
        tmpModule.setModuleName("积分");
        tmpModule.setParentId(1);
        modules.add(tmpModule);

        tmpModule = new ModuleWrapper();
        tmpModule.setId(6);
        tmpModule.setModuleName("用户信息审核");
        tmpModule.setParentId(2);
        modules.add(tmpModule);

        tmpModule = new ModuleWrapper();
        tmpModule.setId(7);
        tmpModule.setModuleName("push消息");
        tmpModule.setParentId(3);
        modules.add(tmpModule);

        tmpModule = new ModuleWrapper();
        tmpModule.setId(8);
        tmpModule.setModuleName("角标配置");
        tmpModule.setParentId(4);
        modules.add(tmpModule);

        tmpModule = new ModuleWrapper();
        tmpModule.setId(9);
        tmpModule.setModuleName("消费积分配置");
        tmpModule.setParentId(5);
        modules.add(tmpModule);

        df(root, modules);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        log.info("v {}", gson.toJson(root));
    }

    private static void df(TreeNode<ModuleWrapper> node, List<ModuleWrapper> modules) {
        modules.stream().filter(module -> module.getParentId().intValue() == node.node.getId()).forEach(m -> {
            TreeNode<ModuleWrapper> newNode = new TreeNode<>(m);
            node.add(newNode);
            df(newNode, modules);
        });
    }


}
