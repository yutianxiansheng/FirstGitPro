package com.example.yikeyuanjianhuanglei.myhotdemo;

import com.taobao.sophix.SophixApplication;
import android.content.Context;
import android.support.annotation.Keep;
import android.util.Log;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.example.yikeyuanjianhuanglei.myhotdemo.MyRealApplication;
/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyRealApplication.class)
    static class RealApplicationStub {}
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }
    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
                .setAppVersion(appVersion)//应用的版本号
                .setSecretMetaData("25359413",
                        "7fd55d6cc958e77f41653beac394f3f0",
                        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCIuXhEwYu0GoQyzTimNtaaqpYJnZBsFKApNbgWaF1FSMtHMeHdgTnAprAEpyrqa5MgNXtqkgyq41A/RtJdTk70Yuux0wEkwFY6jn1eFnv5T1TKrvGIGGrLp4tgZWdgcyQAaCuuHNEXf3r7tiXXYib7rG4kkuHVEcl24STaZOEYbIVBG99wm4h+P/P8z6un/qnhEQqOOpuYeuhwckD2cVBjtX9rheX5VXFuhUR+3dRsyuiKDjbW+LwHjq0jC22EnPJ7NcmLuo2UgR61nc8FbwG6zKbQCDg/CFdnIWaGHMdX8mv9QveWaXpF9U/uISA5j92Xxr9lJxvZsXjUkNcXaB8ZAgMBAAECggEAW1J2kIYuSAgGEOCeW/b49KnIOXfyxR4ptBzbbqwa0HJExLL+yDLOXm132EPIGHft92+Blbcjmujl6r3lOMlbV0TTZR0pAGEvt0EBX9zoxadFXXPohBy7CHhKUgKpP/ToMRVjR5Kb3nQZsxMAx5IZs7SChQ9OpECTLkJHmVAUQXkHG0uWmq2ZiUSrETVQ49eWgmkQR622cn9smPJdYbs9XOZDd6PtGBOaEO1FL1SisW69RZ9IUE4BZ1r6nbuXFiLBfUquKAuQTKhgOBTyCBF9lqF+3oByLRmE09sdJ30R/51fxkPv/J/5MgOYMyMS6ve7Tbs+jKdmwKzFJJ9CmksnuQKBgQD205/RGC5g8T0VDOxp3Cjz/bHQ2Z8tYyksMkokN50Rxbkr3uVAt1SKkU3tjxog0wUcrFficZaWie+p26GFba8J61TuRN+v4Sp2Y9sIqL7cP9RNzFTw07RrrmWYuNezTwvYB3q7C3V6KAdul9rz9OZ3AzATL/VhEu8gSWT6Ig+f+wKBgQCNzk29HLEEGAWwDvaCP6B0XV5MH4A/SulijWT6iGPk1Z25MSQ56GqaCe9/2OMkNq8H7Ngb/4plRm9ik9BK3hvjrDHGl926yQ4KdidCILwjI+44tXqAIFIAxGUykykddUWaeiQKVp+cl4tQJRJJhVDbaFlzrfLXX65kjzdIi3uM+wKBgQDlAEsSsCC3iJpnYWomfUDxvrqvTnlEnu+gNbhcCc0MpCm87jw84trpBQ8MDOzs71ua0rtnOIPCf4+ARGmDZdQ0m8ZcmkH+6CWBWfvp+MW7iuzqkV1tYr3N+iwAmd5mWjxe3fGcgHEqOOFMVk+qb9kzKh38b052Fh8OEYpCGD/u6QKBgAYhz7bH0NfOeiB85SgIvFWL3BIKoq8Z/DGoz58N2tQAcMubVmeypzl2CIdMx7W/L552HXpIeKGchCCNJ8q1BBu6TIS7+HilTRgit9viMoCKgziskFmODD3AQGgJeROvCI1/jqNrz4cj/oA+uiW3wCFcx9F6yi+KEa6Diu52KaeVAoGAddxxiwkYT5H4lrDYFdS9BtT4PZHSEAbu1g+/y9ity1aKeC8fo8gaFKFnBj0mBvMRaF5YtdN2bowfMsNn9s28fFyMEoBI3Wt5rFyP2jOPfaVtMnjMMgzhYO7OdHhdNmvUFWBZVRtcSHGAtTszjKq8JnNTIpRYTnVqhP+5sEWC/bM=")//三个Secret分别对应AndroidManifest里面的三个，可以不在AndroidManifest设置而是用此函数来设置Secret。放到代码里面进行设置可以自定义混淆代码，更加安全，此函数的设置会覆盖AndroidManifest里面的设置，如果对应的值设为null，默认会在使用AndroidManifest里面的
                .setEnableDebug(true)//是否调试模式
                .setAesKey("")//自定义aes秘钥, 会对补丁包采用对称加密。这个参数值必须是16位数字或字母的组合，是和补丁工具设置里面AES Key保持完全一致, 补丁才能正确被解密进而加载。此时平台无感知这个秘钥, 所以不用担心阿里云移动平台会利用你们的补丁做一些非法的事情
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }else{
                            // 其它错误信息, 查看PatchStatus类说明
                            Log.d("常见状态码:",""+code);
                        }
                    }
                }).initialize();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
