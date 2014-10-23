package com.SinfulPixel.RPCore.ServerMgnt;

import com.SinfulPixel.RPCore.RPCore;

/**
 * Created by Min3 on 10/23/2014.
 */
public class ReportMgr {
    static RPCore plugin;
    public ReportMgr(RPCore plugin){this.plugin=plugin;}
    public static void sendReport(String s){
        PastebinReporter.Paste report = new PastebinReporter.Paste("Header");
        report.appendLine(s);
        plugin.getReporter().post("Error Report",report,PastebinReporter.ReportFormat.YAML,PastebinReporter.ExpireDate.ONE_MONTH);
    }
}
