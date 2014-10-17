package com.SinfulPixel.RPCore.Permissions;

import com.SinfulPixel.RPCore.RPCore;

/**
 * Created by Min3 on 10/16/2014.
 */
public class PermCfgMgr {
    private RPCore plugin;
    public PermCfgMgr(RPCore pl){this.plugin=pl;}
    public String getDefaultGroup(){
        return this.plugin.getConfig().getString("RPCore.Permissions.DefaultGroup");
    }
    public String getMsg(String id){
        if(this.plugin.getConfig().getString("RPCore.Permissions.Msgs."+id)==null){
            return new PermUtils(this.plugin).format("&cCheck config.yml (Permission Settings)");
        }
        return new PermUtils(this.plugin).format(this.plugin.getConfig().getString("RPCore.Permissions.Msgs."+id));
    }
}
