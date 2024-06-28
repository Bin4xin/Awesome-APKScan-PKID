import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApkScanWin apkScanWin = new ApkScanWin();
        Map<String, String> markNameMap = new HashMap<>();

        markNameMap.put("libchaosvmp.so", "娜迦");
        markNameMap.put("libddog.so", "娜迦");
        markNameMap.put("libfdog.so", "娜迦");
        markNameMap.put("libedog.so", "娜迦企业版");

        markNameMap.put("libexec.so", "爱加密");
        markNameMap.put("libexecmain.so", "爱加密");
        markNameMap.put("ijiami.dat", "爱加密");
        markNameMap.put("ijiami.ajm", "爱加密企业版");

        markNameMap.put("libsecexe.so", "梆梆免费版");
        markNameMap.put("libsecmain.so", "梆梆免费版");
        markNameMap.put("libSecShell.so", "梆梆免费版");

        markNameMap.put("libDexHelper.so", "梆梆企业版");
        markNameMap.put("libDexHelper-x86.so", "梆梆企业版");

        markNameMap.put("libprotectClass.so", "360");
        markNameMap.put("libjiagu.so", "360");
        markNameMap.put("libjiagu_art.so", "360");
        markNameMap.put("libjiagu_x86.so", "360");

        markNameMap.put("libegis.so", "通付盾");
        markNameMap.put("libNSaferOnly.so", "通付盾");

        markNameMap.put("libnqshield.so", "网秦");

        markNameMap.put("libbaiduprotect.so", "百度");

        markNameMap.put("aliprotect.dat", "阿里聚安全");
        markNameMap.put("libsgmain.so", "阿里聚安全");
        markNameMap.put("libsgsecuritybody.so", "阿里聚安全");
        markNameMap.put("libmobisec.so", "阿里聚安全");

        markNameMap.put("libtup.so", "腾讯");
        markNameMap.put("libexec.so", "腾讯");
        markNameMap.put("libshell.so", "腾讯");
        markNameMap.put("mix.dex", "腾讯");
        markNameMap.put("lib/armeabi/mix.dex", "腾讯");
        markNameMap.put("lib/armeabi/mixz.dex", "腾讯");

        markNameMap.put("libtosprotection.armeabi.so", "腾讯御安全");
        markNameMap.put("libtosprotection.armeabi-v7a.so", "腾讯御安全");
        markNameMap.put("libtosprotection.x86.so", "腾讯御安全");

        markNameMap.put("libnesec.so", "网易易盾");

        markNameMap.put("libAPKProtect.so", "APKProtect");

        markNameMap.put("libkwscmm.so", "几维安全");
        markNameMap.put("libkwscr.so", "几维安全");
        markNameMap.put("libkwslinker.so", "几维安全");

        markNameMap.put("libx3g.so", "顶像科技");

        markNameMap.put("libapssec.so", "盛大");

        markNameMap.put("librsprotect.so", "瑞星");

        apkScanWin.setMarkNameMap(markNameMap);
        apkScanWin.init();
    }
}





