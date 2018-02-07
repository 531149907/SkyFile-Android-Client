package work.hin.skyfileclient.model.net;

import work.hin.skyfileclient.config.net.RetrofitConfig;

/**
 * Created by zhouzhixuan on 2018/2/5.
 */

public class NetService {
    private static NetService service = RetrofitConfig.createService(NetService.class);

    private NetService() {
    }

    public static NetService getService() {
        return service;
    }
}
